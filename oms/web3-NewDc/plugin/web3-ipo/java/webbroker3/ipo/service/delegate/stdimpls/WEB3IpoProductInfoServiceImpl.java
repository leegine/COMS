head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.05.47.04;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6f44d7efa104045;
filename	WEB3IpoProductInfoServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : IPO銘柄情報作成サービスImpl(WEB3IpoProductInfoServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/08/23 李頴淵 新規作成
                 : 2006/11/22 何文敏 (中訊) 仕様変更No.167対応
                 : 2006/11/22 何文敏 (中訊) ＤＢ更新仕様 No.27
*/

package webbroker3.ipo.service.delegate.stdimpls;

import java.math.BigDecimal;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3DocReadingDivDef;
import webbroker3.gentrade.WEB3Administrator;
import webbroker3.ipo.WEB3IpoProductImpl;
import webbroker3.ipo.WEB3IpoProductManagerImpl;
import webbroker3.ipo.data.IpoProductParams;
import webbroker3.ipo.data.IpoProductRow;
import webbroker3.ipo.define.WEB3UndecideDecideDivDef;
import webbroker3.ipo.message.WEB3IPOProductInfo;
import webbroker3.ipo.message.WEB3IPOTermUnit;
import webbroker3.ipo.service.delegate.WEB3IpoProductInfoService;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * IPO銘柄情報作成サービス実装クラス
 * 
 * @@author 李頴淵
 * @@version 1.0
 */
public class WEB3IpoProductInfoServiceImpl implements WEB3IpoProductInfoService 
{
    
    /**
     * ログ出力ユーティリティ。<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3IpoProductInfoServiceImpl.class);
    
    /**
     * @@roseuid 4112F19100A2
     */
    public WEB3IpoProductInfoServiceImpl() 
    {
     
    }
    
    /**
     * (createIPO銘柄情報)<BR>
     * 引数のIPO銘柄IDに該当するIPO銘柄の内容で、<BR>
     * IPO銘柄情報メッセージデータインスタンスを作成する。<BR>
     * <BR>
     * シーケンス図<BR>
     * 「IPO（共通）createIPO銘柄情報」参照。<BR>
     * @@param l_lngProductId - IPO銘柄ＩＤ
     * @@return webbroker3.ipo.message.WEB3IpoProductInfo
     * @@throws WEB3BaseException
     * @@roseuid 40C66A8802EA
     */
    public WEB3IPOProductInfo createIpoProductInfo(long l_lngProductId) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " createIpoProductInfo(long)";
        log.entering(STR_METHOD_NAME);
        
        //1.IPO銘柄
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        WEB3IpoProductManagerImpl l_ipoProductManagerImpl = 
            (WEB3IpoProductManagerImpl)l_finApp.getTradingModule(ProductTypeEnum.IPO).getProductManager();

        try
        {
            WEB3IpoProductImpl l_ipoProductImpl = (WEB3IpoProductImpl)l_ipoProductManagerImpl.getProduct(l_lngProductId);
            IpoProductRow l_ipoProductRow = (IpoProductRow)l_ipoProductImpl.getDataSourceObject();
            IpoProductParams l_ipoProductParams = new IpoProductParams(l_ipoProductRow);
            
            //2.isスケジュール決定
            boolean l_blnScheduleDecision = l_ipoProductImpl.isScheduleDecision();
            
            //3.IPO期間指定
            WEB3IPOTermUnit l_ipoTermUnitPublicDate = new WEB3IPOTermUnit();
            
            //4.公開日インスタンス プロパティセット
            l_ipoTermUnitPublicDate.startDate = l_ipoProductParams.getPublicOfferingDate();
            if (l_ipoProductParams.getPublicOfferingDateCountIsNull())
            {
                l_ipoTermUnitPublicDate.bizDateUpper = null;
            }
            else
            {
                l_ipoTermUnitPublicDate.bizDateUpper = WEB3StringTypeUtility.formatNumber(l_ipoProductParams.getPublicOfferingDateCount());
            }
            
            //5.IPO期間指定
            WEB3IPOTermUnit l_ipoTermUnitPublicPriceSettleDate = new WEB3IPOTermUnit();
            
            //6.公開価格決定日インスタンス プロパティセット
            l_ipoTermUnitPublicPriceSettleDate.startDate = l_ipoProductParams.getPublicPriceSettleDate();
            // 200411/16 U00422 公開価格決定日.営業日上限のセット不要のため対応　@坂上@@SRA  START
//            if (l_ipoProductParams.getPublicOfferingDateCountIsNull())
//            {
//                l_ipoTermUnitPublicPriceSettleDate.bizDateUpper = null;
//            }
//            else
//            {
//                l_ipoTermUnitPublicPriceSettleDate.bizDateUpper = WEB3StringTypeUtility.formatNumber(l_ipoProductParams.getPublicOfferingDateCount());
//            }
			// 200411/16 U00422 公開価格決定日.営業日上限のセット不要のため対応　@坂上@@SRA  END
            
            //7.IPO期間指定
            WEB3IPOTermUnit l_ipoTermUnitLotDate = new WEB3IPOTermUnit();
            
            //8.抽選日インスタンス プロパティセット
            l_ipoTermUnitLotDate.startDate = l_ipoProductParams.getLotDate();
            if (l_ipoProductParams.getLotDateCountIsNull())
            {
                l_ipoTermUnitLotDate.bizDateUpper = null;
            }
            else
            {
                l_ipoTermUnitLotDate.bizDateUpper = WEB3StringTypeUtility.formatNumber(l_ipoProductParams.getLotDateCount());
            }            
            
            //9.IPO期間指定( )
            WEB3IPOTermUnit l_ipoTermUnitOfferDate = new WEB3IPOTermUnit();
            
            //10.購入申込期間（目論見書）インスタンス プロパティセット
            l_ipoTermUnitOfferDate.startDate = l_ipoProductParams.getOfferStartDateProspec();
            l_ipoTermUnitOfferDate.endDate = l_ipoProductParams.getOfferEndDateProspec();
			//// 2004/10/29 U00341の対応　@営業日上限下限のセットが逆のため修正　@坂上@@SRA　@START
			if (l_ipoProductParams.getOfferStartDateCountProspecIsNull())
            {
                l_ipoTermUnitOfferDate.bizDateUpper = null;
            }
            else
            {
                l_ipoTermUnitOfferDate.bizDateUpper = WEB3StringTypeUtility.formatNumber(l_ipoProductParams.getOfferStartDateCountProspec());
            }
            if (l_ipoProductParams.getOfferEndDateCountProspecIsNull())
            {
                l_ipoTermUnitOfferDate.bizDateLower = null;
            }
            else
            {
                l_ipoTermUnitOfferDate.bizDateLower = WEB3StringTypeUtility.formatNumber(l_ipoProductParams.getOfferEndDateCountProspec());
            }
//            if (l_ipoProductParams.getOfferStartDateCountProspecIsNull())
//            {
//                l_ipoTermUnitOfferDate.bizDateUpper = null;
//            }
//            else
//            {
//                l_ipoTermUnitOfferDate.bizDateUpper = WEB3StringTypeUtility.formatNumber(l_ipoProductParams.getOfferStartDateCountProspec());
//            }
//            if (l_ipoProductParams.getOfferEndDateCountProspecIsNull())
//            {
//                l_ipoTermUnitOfferDate.bizDateLower = null;
//            }
//            else
//            {
//                l_ipoTermUnitOfferDate.bizDateLower = WEB3StringTypeUtility.formatNumber(l_ipoProductParams.getOfferEndDateCountProspec());
//            }
			//// 2004/10/29 U00341の対応　@営業日上限下限のセットが逆のため修正　@坂上@@SRA　@END
            
            
            //11.IPO期間指定( )
            WEB3IPOTermUnit l_ipoTermUnitOfferdDtetime = new WEB3IPOTermUnit();
            
            //12.購入申込期間（当社指定）インスタンス プロパティセット
            l_ipoTermUnitOfferdDtetime.startDate = l_ipoProductParams.getOfferStartDatetime();
            l_ipoTermUnitOfferdDtetime.endDate = l_ipoProductParams.getOfferEndDatetime();
            ////// 2004/10/29 U00341の対応　@営業日上限下限のセットが逆のため修正　@坂上@@SRA　@START
            
            //U01313 bizDateLower -> bizDateUpper
			if (l_ipoProductParams.getOfferStartDateCountIsNull())
            {
                l_ipoTermUnitOfferdDtetime.bizDateUpper = null;
            }
            else
            {
                l_ipoTermUnitOfferdDtetime.bizDateUpper = WEB3StringTypeUtility.formatNumber(l_ipoProductParams.getOfferStartDateCount());
            }
            //U01313 bizDateUpper -> bizDateLower
            if (l_ipoProductParams.getOfferEndDateCountIsNull())
            {
                l_ipoTermUnitOfferdDtetime.bizDateLower = null;
            }
            else
            {
                l_ipoTermUnitOfferdDtetime.bizDateLower = WEB3StringTypeUtility.formatNumber(l_ipoProductParams.getOfferEndDateCount());
            }  
//            if (l_ipoProductParams.getOfferStartDateCountIsNull())
//            {
//                l_ipoTermUnitOfferdDtetime.bizDateUpper = null;
//            }
//            else
//            {
//                l_ipoTermUnitOfferdDtetime.bizDateUpper = WEB3StringTypeUtility.formatNumber(l_ipoProductParams.getOfferStartDateCount());
//            }
//            if (l_ipoProductParams.getOfferEndDateCountIsNull())
//            {
//                l_ipoTermUnitOfferdDtetime.bizDateLower = null;
//            }
//            else
//            {
//                l_ipoTermUnitOfferdDtetime.bizDateLower = WEB3StringTypeUtility.formatNumber(l_ipoProductParams.getOfferEndDateCount());
//            }          
//			//// 2004/10/29 U00341の対応　@営業日上限下限のセットが逆のため修正　@坂上@@SRA　@END



            //13.IPO銘柄情報
            WEB3IPOProductInfo l_ipoProductInfo = new WEB3IPOProductInfo();
            
            //14.IPO銘柄情報 プロパティセット                
            //IPO銘柄情報.IPO登録区分：　@IPO銘柄.IPO登録区分
            l_ipoProductInfo.ipoRegistDiv = l_ipoProductParams.getIpoRegistDiv();
            //IPO銘柄情報.IPO登録区分詳細：　@IPO銘柄.IPO登録区分詳細
            l_ipoProductInfo.ipoRegistDetailDiv = l_ipoProductParams.getIpoRegistDetailDiv();            
            //IPO銘柄情報.銘柄コード：　@IPO銘柄.銘柄コード
            l_ipoProductInfo.productCode = l_ipoProductParams.getProductCode();
            //IPO銘柄情報.銘柄名：　@IPO銘柄.銘柄名
            l_ipoProductInfo.productName = l_ipoProductParams.getStandardName();
            //IPO銘柄情報.未定決定区分：
                //（IPO銘柄.isスケジュール決定() == true）の場合、”決定”
                //（IPO銘柄.isスケジュール決定() == false）の場合、”未定”
            if (l_blnScheduleDecision)
            {
                log.debug("isスケジュール決定() == true");
                l_ipoProductInfo.undecideDecideDiv = WEB3UndecideDecideDivDef.SCHEDULE_DECIDED;   
            }
            else
            {
                log.debug("isスケジュール決定() == false");
                l_ipoProductInfo.undecideDecideDiv = WEB3UndecideDecideDivDef.SCHEDULE_UNDECIDED;
            }
            //IPO銘柄情報.公開日：　@公開日（：IPO期間指定）
            l_ipoProductInfo.publicOfferingDate = l_ipoTermUnitPublicDate;
            //IPO銘柄情報.公開市場コード：　@IPO銘柄.公開市場
            l_ipoProductInfo.publicOfferingMarketCode = l_ipoProductParams.getPublicMarket();
            //IPO銘柄情報.仮条件区分：　@IPO銘柄.仮条件区分
            l_ipoProductInfo.temporaryConditionDiv = l_ipoProductParams.getProvisionalValueDiv();
            
            //IPO銘柄情報.仮条件下限値：　@IPO銘柄.仮条件下限値
            log.debug("l_ipoProductParams.getProvisionalMinValue() = " + l_ipoProductParams.getProvisionalMinValue());
            if (l_ipoProductParams.getProvisionalMinValueIsNull())
            {
                log.debug("l_ipoProductParams.provisional_min_value.isNaN()");
                l_ipoProductInfo.temporaryConditionLower = null;
            }
            else
            {
                log.debug("IPO銘柄.仮条件下限値");
                l_ipoProductInfo.temporaryConditionLower = WEB3StringTypeUtility.formatNumber(l_ipoProductParams.getProvisionalMinValue());
            }            
            
            //IPO銘柄情報.仮条件上限値：　@IPO銘柄.仮条件上限値
            if (l_ipoProductParams.getProvisionalMaxValueIsNull())
            {
                log.debug("l_ipoProductParams.provisional_max_value.isNaN()");
                l_ipoProductInfo.temporaryConditionUpper = null;
            }
            else
            {
                log.debug("IPO銘柄.仮条件上限値");
                l_ipoProductInfo.temporaryConditionUpper = WEB3StringTypeUtility.formatNumber(l_ipoProductParams.getProvisionalMaxValue());
            }            
            
            //IPO銘柄情報.仮条件提示日：　@IPO銘柄.仮条件提示日
            l_ipoProductInfo.temporaryConditionPresentationDate = l_ipoProductParams.getProvisionalValueOpenDate();
            //IPO銘柄情報.公募数量：　@IPO銘柄.公募数量
            if (l_ipoProductParams.getPublicOfferingQuantityIsNull())
            {
                l_ipoProductInfo.issuedQuantity = null;
            }
            else
            {
                l_ipoProductInfo.issuedQuantity = WEB3StringTypeUtility.formatNumber(l_ipoProductParams.getPublicOfferingQuantity());
            }
            
            //IPO銘柄情報.売出数量：　@IPO銘柄.売出数量
            if (l_ipoProductParams.getPublicSalesQuantityIsNull())
            {
                l_ipoProductInfo.offeringQuantity = null;    
            }
            else
            {
                l_ipoProductInfo.offeringQuantity = WEB3StringTypeUtility.formatNumber(l_ipoProductParams.getPublicSalesQuantity());
            }
            
            //IPO銘柄情報.当社取扱数量：　@IPO銘柄.当社取扱数量
            if (l_ipoProductParams.getDealingQuantityIsNull())
            {
                l_ipoProductInfo.handlingQuantity = null;
            }
            else
            {
                l_ipoProductInfo.handlingQuantity = WEB3StringTypeUtility.formatNumber(l_ipoProductParams.getDealingQuantity());
            }
            
            //IPO銘柄情報.購入申込単位：　@IPO銘柄.購入申込単位
            if (l_ipoProductParams.getLotSizeIsNull())
            {
                l_ipoProductInfo.offerUnit = null;
            }
            else
            {
                l_ipoProductInfo.offerUnit = WEB3StringTypeUtility.formatNumber(l_ipoProductParams.getLotSize());
            }
            
            //IPO銘柄情報.刻み：　@IPO銘柄.刻み
            if (l_ipoProductParams.getTickValueIsNull())
            {
                log.debug("l_ipoProductParams.tick_value.isNaN()");
                l_ipoProductInfo.tickValue = null;
            }
            else
            {
                log.debug("IPO銘柄.刻み");
                l_ipoProductInfo.tickValue = WEB3StringTypeUtility.formatNumber(l_ipoProductParams.getTickValue());
            }            
            
            //IPO銘柄情報.表示用単位区分：　@IPO銘柄.表示用単位区分
            l_ipoProductInfo.displayUnitDiv = l_ipoProductParams.getIpoUnitDiv();
            //IPO銘柄情報.成行可能：　@IPO銘柄.成行可能
            l_ipoProductInfo.marketOrderFlag = l_ipoProductParams.getEnableMarketOrder();
            //IPO銘柄情報.主幹事証券会社名：　@IPO銘柄.主幹事証券会社名
            l_ipoProductInfo.leadManagingUnderwriter = l_ipoProductParams.getLeadManagingUnderwriter();
            //IPO銘柄情報.ブックビルディング開始日時：　@IPO銘柄.ブックビルディング開始日時
            l_ipoProductInfo.bookBuildingStartDate = l_ipoProductParams.getBookbuildingStartDatetime();
            //IPO銘柄情報.ブックビルディング終了日時：　@IPO銘柄.ブックビルディング終了日時
            l_ipoProductInfo.bookBuildingEndDate = l_ipoProductParams.getBookbuildingEndDatetime();
            //IPO銘柄情報.公開価格決定日：　@公開価格決定日（：IPO期間指定）
            l_ipoProductInfo.publicOfferingPriceDetermDate = l_ipoTermUnitPublicPriceSettleDate;
            //IPO銘柄情報.公開価格：　@IPO銘柄.公開価格
            if (l_ipoProductParams.getPublicPriceIsNull())
            {
                log.debug("l_ipoProductParams.public_price.isNaN()");
                l_ipoProductInfo.publicOfferingPrice = null;
            }
            else
            {
                log.debug("IPO銘柄.公開価格");
                l_ipoProductInfo.publicOfferingPrice = WEB3StringTypeUtility.formatNumber(l_ipoProductParams.getPublicPrice());
            }           
            
            //IPO銘柄情報.公開価格ディスカウント率：　@IPO銘柄.公開価格（ディスカウント率）
            if (l_ipoProductParams.getPublicPriceDiscountRateIsNull())
            {
                log.debug("l_ipoProductParams.public_price_discount_rate.isNaN()");
                l_ipoProductInfo.publicOfferingDiscountRate = null;
            }
            else
            {
                log.debug("IPO銘柄.公開価格（ディスカウント率）");
                l_ipoProductInfo.publicOfferingDiscountRate = WEB3StringTypeUtility.formatNumber(l_ipoProductParams.getPublicPriceDiscountRate());
            }            
            
            //IPO銘柄情報.抽選日：　@抽選日（：IPO期間指定）
            l_ipoProductInfo.lotDate = l_ipoTermUnitLotDate;
            //IPO銘柄情報.購入申込期間（目論見書）：　@購入申込期間（目論見書）（：IPO期間指定）
            l_ipoProductInfo.prospectusOfferTerm = l_ipoTermUnitOfferDate;
            //IPO銘柄情報.購入申込期間（当社指定）：　@購入申込期間（当社指定）（：IPO期間指定）
            l_ipoProductInfo.appointOfferTerm = l_ipoTermUnitOfferdDtetime;
            //IPO銘柄情報.発行会社ロゴファ@イルURL：　@IPO銘柄.発行会社ロゴファ@イルURL
            l_ipoProductInfo.issuerLogoFileURL = l_ipoProductParams.getCompanyLogoUrl();
            //IPO銘柄情報.発行会社ウェブサイトURL：　@IPO銘柄.発行会社ウェブサイトURL
            l_ipoProductInfo.issuerWebSiteURL = l_ipoProductParams.getCompanyUrl();
            //IPO銘柄情報.発行会社概要：　@IPO銘柄.発行会社概要
            l_ipoProductInfo.issuerCorporateOutline = l_ipoProductParams.getCompanyOutline();
            //IPO銘柄情報.備考：　@IPO銘柄.備考
            l_ipoProductInfo.note = l_ipoProductParams.getNote();
            //IPO銘柄情報.目論見書閲覧区分:   IPO銘柄.目論見書閲覧区分
            l_ipoProductInfo.prospectusReadDiv = l_ipoProductParams.getDocReadingDiv();
            
            log.exiting(STR_METHOD_NAME);
            return l_ipoProductInfo;  
        }
        catch (NotFoundException l_ex)
        {
            log.error(this.getClass().getName() + STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage());
        }
    }
    
    /**
     * (createIPO銘柄)<BR>
     * 画面入力値よりIPO銘柄情報を作成する。<BR>
     * <BR>
     * １）　@引数（IPO銘柄情報入力メッセージ／管理者オブジェクト）より、<BR>
     * 登録内容をセットする。<BR>
     * 　@引数.IPO銘柄行の項目に登録値をセットする。<BR>
     * <BR>
     * 　@セット内容については、<BR>
     * 　@補足資料.DB更新<BR>
     * 　@　@「管理者・銘柄登録_IPO銘柄テーブル仕様.xls」参照。<BR>
     * 　@※ここでは、上記ファ@イル内で<BR>
     * 　@”リクエストデータ.”または、”管理者.”より編集する指定がある部分の<BR>
     * みセット対象。<BR>
     * <BR>
     * ２） IPO銘柄オブジェクト生成<BR>
     * 　@IPO銘柄Paramsを引数に、IPO銘柄オブジェクトを生成し、返却する。<BR>
     * @@param l_ipoProductParams - IPO銘柄行オブジェクト
     * @@param l_productInfoInputMsg - IPO銘柄情報入力メッセージ
     * @@param l_admin - 管理者オブジェクト
     * @@return webbroker3.ipo.WEB3IpoProductImpl
     * @@roseuid 40CE77BF0384
     */
    public WEB3IpoProductImpl createIpoProduct(IpoProductParams l_ipoProductParams, WEB3IPOProductInfo l_productInfoInputMsg, WEB3Administrator l_admin) 
    {
        final String STR_METHOD_NAME = " createIpoProduct(IpoProductParams,WEB3IpoProductInfo,WEB3Administrator)";
        log.entering(STR_METHOD_NAME);
        //１）引数.IPO銘柄行の項目に登録値をセットする
        //証券会社コード
        l_ipoProductParams.setInstitutionCode(l_admin.getInstitutionCode());
        
        //銘柄コード
        l_ipoProductParams.setProductCode(l_productInfoInputMsg.productCode);
        
        //銘柄名
        l_ipoProductParams.setStandardName(l_productInfoInputMsg.productName);
        
        //IPO登録区分
        l_ipoProductParams.setIpoRegistDiv(l_productInfoInputMsg.ipoRegistDiv);
        
        //IPO登録区分詳細
        l_ipoProductParams.setIpoRegistDetailDiv(l_productInfoInputMsg.ipoRegistDetailDiv);
        
        //公開日
        l_ipoProductParams.setPublicOfferingDate(l_productInfoInputMsg.publicOfferingDate.startDate);
        
        //公開日日数
        if (l_productInfoInputMsg.publicOfferingDate.bizDateUpper == null)
        {
            l_ipoProductParams.setPublicOfferingDateCount(null);
        }
        else
        {
            l_ipoProductParams.setPublicOfferingDateCount(Integer.parseInt(l_productInfoInputMsg.publicOfferingDate.bizDateUpper));
        }       
               
        //公開市場
        l_ipoProductParams.setPublicMarket(l_productInfoInputMsg.publicOfferingMarketCode);
        
        //仮条件区分
        l_ipoProductParams.setProvisionalValueDiv(l_productInfoInputMsg.temporaryConditionDiv);
        
        //仮条件下限値
        //仮条件上限値
        if (WEB3StringTypeUtility.isEmptyOrBlank(l_productInfoInputMsg.temporaryConditionUpper)
            && WEB3StringTypeUtility.isEmptyOrBlank(l_productInfoInputMsg.temporaryConditionLower))
        {
            l_ipoProductParams.setProvisionalMinValue(null);
            l_ipoProductParams.setProvisionalMaxValue(null);
        }
        else if (WEB3StringTypeUtility.isEmptyOrBlank(l_productInfoInputMsg.temporaryConditionUpper))
        {
            l_ipoProductParams.setProvisionalMinValue(Double.valueOf(l_productInfoInputMsg.temporaryConditionLower));
            l_ipoProductParams.setProvisionalMaxValue(null);
        }
        else if (WEB3StringTypeUtility.isEmptyOrBlank(l_productInfoInputMsg.temporaryConditionLower))
        {
            l_ipoProductParams.setProvisionalMinValue(null);
            l_ipoProductParams.setProvisionalMaxValue(Double.valueOf(l_productInfoInputMsg.temporaryConditionUpper));
        }
        else
        {
            BigDecimal l_bdTempCondUpper = new BigDecimal(l_productInfoInputMsg.temporaryConditionUpper.trim());
            BigDecimal l_bdTempCondLower = new BigDecimal(l_productInfoInputMsg.temporaryConditionLower.trim());
            if (l_bdTempCondUpper.compareTo(l_bdTempCondLower) >= 0)
            {
                l_ipoProductParams.setProvisionalMinValue(Double.valueOf(l_productInfoInputMsg.temporaryConditionLower));

                l_ipoProductParams.setProvisionalMaxValue(Double.valueOf(l_productInfoInputMsg.temporaryConditionUpper));   
            }
            else
            {
                l_ipoProductParams.setProvisionalMinValue(Double.valueOf(l_productInfoInputMsg.temporaryConditionUpper));
            
                l_ipoProductParams.setProvisionalMaxValue(Double.valueOf(l_productInfoInputMsg.temporaryConditionLower));            
            }
        }
        
        //仮条件提示日
        l_ipoProductParams.setProvisionalValueOpenDate(l_productInfoInputMsg.temporaryConditionPresentationDate);
        
        //公募数量
        if (l_productInfoInputMsg.issuedQuantity == null)
        {
            log.debug("l_productInfoInputMsg.publicOfferingQuantity.equals(null)");
            l_ipoProductParams.setPublicOfferingQuantity(null);
        }
        else
        {
            l_ipoProductParams.setPublicOfferingQuantity(Long.parseLong(l_productInfoInputMsg.issuedQuantity));
        }
               
        //売出数量
        if (l_productInfoInputMsg.offeringQuantity == null)
        {
            log.debug("l_productInfoInputMsg.publicSalesQuantity.equals(null)");
            l_ipoProductParams.setPublicSalesQuantity(null);
        }
        else
        {
            l_ipoProductParams.setPublicSalesQuantity(Long.parseLong(l_productInfoInputMsg.offeringQuantity));
        }        
        
        //当社取扱数量
        if (l_productInfoInputMsg.handlingQuantity == null)
        {
            log.debug("l_productInfoInputMsg.dealingQuantity.equals(null)");
            l_ipoProductParams.setDealingQuantity(null);
        }
        else
        {
            l_ipoProductParams.setDealingQuantity(Long.parseLong(l_productInfoInputMsg.handlingQuantity));
        }       
        
        //主幹事証券会社名
        l_ipoProductParams.setLeadManagingUnderwriter(l_productInfoInputMsg.leadManagingUnderwriter);
        
        //購入申込単位
        if (l_productInfoInputMsg.offerUnit == null)
        {
            log.debug("l_productInfoInputMsg.lotSize.equals(null)");
            l_ipoProductParams.setLotSize(null);
        }
        else
        {
            l_ipoProductParams.setLotSize(Long.parseLong(l_productInfoInputMsg.offerUnit));
        }
        
        //刻み
        if (l_productInfoInputMsg.tickValue == null)
        {
            log.debug("l_productInfoInputMsg.tickValue.equals(null)");
            l_ipoProductParams.setTickValue(null);
        }
        else
        {
            l_ipoProductParams.setTickValue(Double.valueOf(l_productInfoInputMsg.tickValue));
        }        
        
        //表示用単位区分
        l_ipoProductParams.setIpoUnitDiv(l_productInfoInputMsg.displayUnitDiv);
        
        //成行可能
        l_ipoProductParams.setEnableMarketOrder(l_productInfoInputMsg.marketOrderFlag);
        
        //ブックビルディング開始日時
        l_ipoProductParams.setBookbuildingStartDatetime(l_productInfoInputMsg.bookBuildingStartDate);
        
        //ブックビルディング終了日時
        l_ipoProductParams.setBookbuildingEndDatetime(l_productInfoInputMsg.bookBuildingEndDate);
        
        //公開価格決定日
        if(l_productInfoInputMsg.publicOfferingPriceDetermDate == null) 
        {   
            l_ipoProductParams.setPublicPriceSettleDate(null);  
        }   
        else    
        {   
            l_ipoProductParams.setPublicPriceSettleDate(l_productInfoInputMsg.publicOfferingPriceDetermDate.startDate);
        }
        
        //公開価格
        if (l_productInfoInputMsg.publicOfferingPrice == null)
        {
            log.debug("l_productInfoInputMsg.publicPrice.equals(null)");
            l_ipoProductParams.setPublicPrice(null);
        }
        else
        {
            l_ipoProductParams.setPublicPrice(Double.valueOf(l_productInfoInputMsg.publicOfferingPrice));
        }
                
        //公開価格（ディスカウント率）
        if (l_productInfoInputMsg.publicOfferingDiscountRate == null)
        {
            log.debug("l_productInfoInputMsg.publicPriceDiscountRate.equals(null)");
            l_ipoProductParams.setPublicPriceDiscountRate(null);
        }
        else
        {
            l_ipoProductParams.setPublicPriceDiscountRate(Double.valueOf(l_productInfoInputMsg.publicOfferingDiscountRate));
        }
        
        //抽選日
        l_ipoProductParams.setLotDate(l_productInfoInputMsg.lotDate.startDate);
        
        //抽選日日数
        if (l_productInfoInputMsg.lotDate.bizDateUpper == null)
        {
            l_ipoProductParams.setLotDateCount(null);
        }
        else
        {
            l_ipoProductParams.setLotDateCount(Integer.parseInt(l_productInfoInputMsg.lotDate.bizDateUpper));
        }
        
        //購入申込開始日時(当社設定)
        l_ipoProductParams.setOfferStartDatetime(l_productInfoInputMsg.appointOfferTerm.startDate);
        
        //購入申込開始日日数(当社設定）
        
		///2004/10/29  U00341の営業日上限値下限値の設定を修正　@坂上@@SRA　@　@START
        
        //U01313 bizDateLower -> bizDateUpper
        if (l_productInfoInputMsg.appointOfferTerm.bizDateUpper == null)
        {
            l_ipoProductParams.setOfferStartDateCount(null);
        }
        else
        {
            l_ipoProductParams.setOfferStartDateCount(Integer.parseInt(l_productInfoInputMsg.appointOfferTerm.bizDateUpper));
        }
//		if (l_productInfoInputMsg.appointOfferTerm.bizDateUpper == null)
//				{
//					l_ipoProductParams.setOfferStartDateCount(null);
//				}
//				else
//				{
//					l_ipoProductParams.setOfferStartDateCount(Integer.parseInt(l_productInfoInputMsg.appointOfferTerm.bizDateUpper));
//				}
		///2004/10/29  U00341の営業日上限値下限値の設定を修正　@坂上@@SRA　@　@END
		
		        
        //購入申込終了日時(当社設定)
        l_ipoProductParams.setOfferEndDatetime(l_productInfoInputMsg.appointOfferTerm.endDate);
        
        //購入申込終了日日数(当社設定）
        ///2004/10/29  U00341の営業日上限値下限値の設定を修正　@坂上@@SRA　@　@START
        
        //U01313 bizDateUpper -> bizDateLower
        if (l_productInfoInputMsg.appointOfferTerm.bizDateLower == null)
        {
            l_ipoProductParams.setOfferEndDateCount(null);
        }
        else
        {
            l_ipoProductParams.setOfferEndDateCount(Integer.parseInt(l_productInfoInputMsg.appointOfferTerm.bizDateLower));
        }
//		if (l_productInfoInputMsg.appointOfferTerm.bizDateLower == null)
//				{
//					l_ipoProductParams.setOfferEndDateCount(null);
//				}
//				else
//				{
//					l_ipoProductParams.setOfferEndDateCount(Integer.parseInt(l_productInfoInputMsg.appointOfferTerm.bizDateLower));
//				}
		///2004/10/29  U00341の営業日上限値下限値の設定を修正　@坂上@@SRA　@　@END
        
        //購入申込開始日(目論見書記載)
        l_ipoProductParams.setOfferStartDateProspec(l_productInfoInputMsg.prospectusOfferTerm.startDate);
        
        //購入申込開始日日数(目論見書記載)
        
		///2004/10/29  U00341の営業日上限値下限値の設定を修正　@坂上@@SRA　@　@START
        if (l_productInfoInputMsg.prospectusOfferTerm.bizDateUpper == null)
        {
            l_ipoProductParams.setOfferStartDateCountProspec(null);
        }
        else
        {
            l_ipoProductParams.setOfferStartDateCountProspec(Integer.parseInt(l_productInfoInputMsg.prospectusOfferTerm.bizDateUpper));
        }
//		if (l_productInfoInputMsg.prospectusOfferTerm.bizDateUpper == null)
//				{
//					l_ipoProductParams.setOfferStartDateCountProspec(null);
//				}
//				else
//				{
//					l_ipoProductParams.setOfferStartDateCountProspec(Integer.parseInt(l_productInfoInputMsg.prospectusOfferTerm.bizDateUpper));
//				}
//		/2004/10/29  U00341の営業日上限値下限値の設定を修正　@坂上@@SRA　@　@END
        
        //購入申込終了日(目論見書記載)
        l_ipoProductParams.setOfferEndDateProspec(l_productInfoInputMsg.prospectusOfferTerm.endDate);
        
        //購入申込終了日日数(目論見書記載)
        
        ///2004/10/29  U00341の営業日上限値下限値の設定を修正　@坂上@@SRA　@　@START
		if (l_productInfoInputMsg.prospectusOfferTerm.bizDateLower == null)
        {
            l_ipoProductParams.setOfferEndDateCountProspec(null);
        }
        else
        {
            l_ipoProductParams.setOfferEndDateCountProspec(Integer.parseInt(l_productInfoInputMsg.prospectusOfferTerm.bizDateLower));
        }
//        if (l_productInfoInputMsg.prospectusOfferTerm.bizDateLower == null)
//        {
//            l_ipoProductParams.setOfferEndDateCountProspec(null);
//        }
//        else
//        {
//            l_ipoProductParams.setOfferEndDateCountProspec(Integer.parseInt(l_productInfoInputMsg.prospectusOfferTerm.bizDateLower));
//        }       
		///2004/10/29  U00341の営業日上限値下限値の設定を修正　@坂上@@SRA　@　@END
        
        
        
        //発行会社ロゴファ@イルURL
        l_ipoProductParams.setCompanyLogoUrl(l_productInfoInputMsg.issuerLogoFileURL);
        
        //発行会社ウェブサイトURL
        l_ipoProductParams.setCompanyUrl(l_productInfoInputMsg.issuerWebSiteURL);
        
        //発行会社概要
        l_ipoProductParams.setCompanyOutline(l_productInfoInputMsg.issuerCorporateOutline);
        
        //備考
        l_ipoProductParams.setNote(l_productInfoInputMsg.note);
        
        //更新者コード
        l_ipoProductParams.setLastUpdater(l_admin.getAdministratorCode());
        
        //目論見書閲覧区分
        if (l_productInfoInputMsg.prospectusReadDiv == null)
        {
            l_ipoProductParams.setDocReadingDiv(WEB3DocReadingDivDef.DEFAULT);
        }
        else
        {
            l_ipoProductParams.setDocReadingDiv(l_productInfoInputMsg.prospectusReadDiv);
        }

        //２）IPO銘柄オブジェクトを生成し、返却する
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        WEB3IpoProductManagerImpl l_ipoProductManagerImpl = 
            (WEB3IpoProductManagerImpl)l_finApp.getTradingModule(ProductTypeEnum.IPO).getProductManager();
        WEB3IpoProductImpl l_ipoProductImpl = (WEB3IpoProductImpl)l_ipoProductManagerImpl.toProduct(l_ipoProductParams);
        log.debug("l_ipoProductImpl" + l_ipoProductImpl.getDataSourceObject());
        
        log.exiting(STR_METHOD_NAME);
        return l_ipoProductImpl;
    }    
}
@
