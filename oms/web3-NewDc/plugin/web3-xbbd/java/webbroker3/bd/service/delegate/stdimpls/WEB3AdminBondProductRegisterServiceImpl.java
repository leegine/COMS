head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.01;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3AdminBondProductRegisterServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 債券管理者銘柄登録サービスImpl(WEB3AdminBondProductRegisterServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/8/16 周捷(中訊) 新規作成
                   2006/10/09 趙林鵬(中訊) モデルNo.106.107.121 ＤＢ更新仕様No.017 
Revesion History : 2008/08/13 馮海濤 (中訊) 仕様変更・モデル260
Revesion History : 2009/07/24 武波 (中訊) 仕様変更・モデル261
*/

package webbroker3.bd.service.delegate.stdimpls;

import java.util.ArrayList;
import java.util.List;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Institution;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Market;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.xbbd.data.BondProductRow;

import webbroker3.bd.WEB3BondOrderManager;
import webbroker3.bd.WEB3BondProduct;
import webbroker3.bd.WEB3BondProductManager;
import webbroker3.bd.data.BondAutoExecLimitActionRow;
import webbroker3.bd.data.BondProductCouponRow;
import webbroker3.bd.define.WEB3BondRecruitAcceptDivDef;
import webbroker3.bd.define.WEB3BondRecruitInvitationFormDef;
import webbroker3.bd.message.WEB3AdminBondAutoExecLimitHistoryUnit;
import webbroker3.bd.message.WEB3AdminBondCustodianUnit;
import webbroker3.bd.message.WEB3AdminBondProductBasicInfo;
import webbroker3.bd.message.WEB3AdminBondProductCouponUnit;
import webbroker3.bd.message.WEB3AdminBondProductRegistCompleteRequest;
import webbroker3.bd.message.WEB3AdminBondProductRegistCompleteResponse;
import webbroker3.bd.message.WEB3AdminBondProductRegistConfirmRequest;
import webbroker3.bd.message.WEB3AdminBondProductRegistConfirmResponse;
import webbroker3.bd.message.WEB3AdminBondProductRegistInputRequest;
import webbroker3.bd.message.WEB3AdminBondProductRegistInputResponse;
import webbroker3.bd.message.WEB3AdminBondProductUpdateInfo;
import webbroker3.bd.service.delegate.WEB3AdminBondHelperService;
import webbroker3.bd.service.delegate.WEB3AdminBondProductRegisterService;
import webbroker3.bd.service.delegate.WEB3BondDataManagerService;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3AutoExecDivDef;
import webbroker3.common.define.WEB3BondTradeTypeDef;
import webbroker3.common.define.WEB3TradeHandleDivDef;
import webbroker3.common.define.WEB3TradingTimeCheckDivDef;
import webbroker3.common.define.WEB3TransactionCategoryDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.gentrade.WEB3Administrator;
import webbroker3.gentrade.WEB3GentradeFinObjectManager;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * (債券管理者銘柄登録サービスImpl)<BR>
 * 債券管理者銘柄登録サービス　@実装クラス
 * 
 * @@author 周捷(中訊)
 * @@version 1.0
 */
public class WEB3AdminBondProductRegisterServiceImpl 
    implements WEB3AdminBondProductRegisterService 
{
    /**
     * ログユーティリティ<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminBondProductRegisterServiceImpl.class);
    /**
     * @@roseuid 44E3362E01F4
     */
    public WEB3AdminBondProductRegisterServiceImpl() 
    {
     
    }
    
    /**
     * 管理者債券銘柄登録を実施する。 <BR>
     * <BR>
     * シーケンス図「(債券)銘柄登録execute」を参照
     * @@param l_request - (リクエストデータ)<BR>
     * リクエストデータ
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 44B601CA0104
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " execute(WEB3GenRequest)";
        log.entering(STR_METHOD_NAME);
        
        if (l_request == null)
        {
            log.debug("パラメータ値がNULL");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }   
        WEB3GenResponse l_response = null;
        //1.1.ｌ_requestが債券銘柄登録入力リクエストの場合
        if (l_request instanceof WEB3AdminBondProductRegistInputRequest)
        {
            //1.1.1.input銘柄登録(管理者債券銘柄登録入力リクエスト)
            l_response = inputProductRegister((WEB3AdminBondProductRegistInputRequest) l_request);         
        }
        
        //1.2. ｌ_requestが債券銘柄登録確認リクエストの場合
        else if (l_request instanceof WEB3AdminBondProductRegistConfirmRequest)
        {
            //1.2.1.validate銘柄登録(管理者債券銘柄登録確認リクエスト)
            l_response = validateProductRegister((WEB3AdminBondProductRegistConfirmRequest) l_request);         
        }
        
        //1.3.ｌ_requestが債券銘柄登録完了リクエストの場合
        else if (l_request instanceof WEB3AdminBondProductRegistCompleteRequest)
        {
            //1.3.1.submit銘柄登録(管理者債券銘柄登録完了リクエスト)
            l_response = submitProductRegister((WEB3AdminBondProductRegistCompleteRequest) l_request);         
        }
        else
        {
            log.debug("パラメータタイプ不正。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80018, 
                this.getClass().getName() + "." + STR_METHOD_NAME, 
                "パラメータタイプ不正。");
        }
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * (input銘柄登録)<BR>
     * 債券銘柄登録入力処理を行う。<BR>
     * <BR>
     * シーケンス図「(債券)input銘柄登録」を参照
     * @@param l_request - (リクエストデータ)<BR>
     * リクエストデータ
     * @@return webbroker3.bd.message.WEB3AdminBondProductRegistInputResponse
     * @@throws WEB3BaseException
     * @@roseuid 44B601CA0123
     */
    protected WEB3AdminBondProductRegistInputResponse inputProductRegister(
        WEB3AdminBondProductRegistInputRequest l_request) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = 
            " inputProductRegister(WEB3AdminBondProductRegistInputRequest)";
        log.entering(STR_METHOD_NAME);
        
        //1.1. validate( )
        l_request.validate();
        
        //1.2.getInstanceFromログイン情報( )
        WEB3Administrator l_admin = 
            WEB3Administrator.getInstanceFromLoginInfo();
        
        //1.3.validate権限(機@能カテゴリコード : String, is更新 : boolean)
        //管理者の権限チェックをする  
        //[validate権限()に指定する引数]  
        //機@能カテゴリコード：　@機@能カテゴリコード.債券銘柄管理 
        //is更新：　@false 
        l_admin.validateAuthority(
            WEB3TransactionCategoryDef.BOND_PRODUCT_MANAGE,
            false);
        
        //1.4.get証券会社( )
        Institution l_institution = l_admin.getInstitution();
        
        //1.5.get債券銘柄(Institution, String)
        //債券銘柄を取得する。 
        //[引数]  
        //証券会社：get証券会社 
        //銘柄コード(WEB3)：リクエストデータ.銘柄コード(WEB3)
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        WEB3BondProductManager l_bondProductManager = 
            (WEB3BondProductManager) l_finApp.getTradingModule(ProductTypeEnum.BOND).getProductManager();   
        WEB3BondProduct l_bondProduct = null;
        try
        {
            l_bondProduct = (WEB3BondProduct) l_bondProductManager.getBondProduct(
                l_institution, l_request.productCode);
        }
        catch (NotFoundException l_ex)
        {
            log.debug("テーブルに該当するデータがありません。", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        //1.6.債券銘柄基本情報
        WEB3AdminBondProductBasicInfo l_productBasicInfo = new WEB3AdminBondProductBasicInfo();
        
        //1.7.プロパティセット
        //※各項目がNULLでない場合のみ、セットする。
        // 銘柄コード(WEB3) = 債券銘柄.get銘柄コード（WEB3）()
        BondProductRow l_productRow = (BondProductRow) l_bondProduct.getDataSourceObject();
        if (l_productRow.getProductCodeIsSet())
        {
            l_productBasicInfo.productCode = l_bondProduct.getProductCode();
        }
        // 発行日 = 債券銘柄.getIssueDate() 
        if (l_productRow.getIssueDateIsSet())
        {
            l_productBasicInfo.issueDate = l_bondProduct.getIssueDate();
        }
        // 発行価格 = 債券銘柄.getIssuePrice()
        if (l_productRow.getIssuePriceIsSet())
        {
            l_productBasicInfo.issuePrice = 
                WEB3StringTypeUtility.formatNumber(l_bondProduct.getIssuePrice());
        }
        // 発行額 = 債券銘柄.get発行額()
        if (!l_productRow.getIssueAmountIsNull())
        {
            l_productBasicInfo.issueAmount = 
                WEB3StringTypeUtility.formatNumber(l_bondProduct.getIssueAmount());
        }
        // 単位額面 = 債券銘柄. getParValue()
        if (l_productRow.getParValueIsSet())
        {
            l_productBasicInfo.parValue = 
                WEB3StringTypeUtility.formatNumber(l_bondProduct.getParValue());
        }
        // 償還日 = 債券銘柄.getMaturityDate() 
        if (l_productRow.getMaturityDateIsSet())
        {
            l_productBasicInfo.maturityDate = l_bondProduct.getMaturityDate();
        }
        // 償還価格 = 債券銘柄.getRedemptionPrice() 
        if (l_productRow.getRedemptionPriceIsSet())
        {
            l_productBasicInfo.redemptionPrice = 
                WEB3StringTypeUtility.formatNumber(l_bondProduct.getRedemptionPrice());
        }
        // 利付タイプ = 債券銘柄.getCouponType()
        if (l_productRow.getCouponTypeIsSet())
        {
            l_productBasicInfo.couponType = l_bondProduct.getCouponType().intValue() + "";
        }
        // 利率 = 債券銘柄.getCoupon()
        if (l_productRow.getCouponIsSet())
        {
            l_productBasicInfo.coupon = WEB3StringTypeUtility.formatNumber(l_bondProduct.getCoupon());
        }
        // 年間利払回数 = 債券銘柄.getYearlyInterestPayments()  
        if (l_productRow.getYearlyInterestPaymentsIsSet())
        {
            l_productBasicInfo.yearlyInterestPayments = l_bondProduct.getYearlyInterestPayments() + "";
        }
        // 利払日1 = 債券銘柄.get利払日1() 
        if (l_bondProduct.getInterestPaymentDay1()  != null)
        {
            l_productBasicInfo.interestPaymentDay1 = l_bondProduct.getInterestPaymentDay1();
        }
        // 利払日2 = 債券銘柄.get利払日2()
        if (l_bondProduct.getInterestPaymentDay2() != null)
        {
            l_productBasicInfo.interestPaymentDay2 = l_bondProduct.getInterestPaymentDay2();
        }
        // HOST銘柄名1 = 債券銘柄.getHOST銘柄名1() 
        if (l_bondProduct.getHostProductName1() != null)
        {
            l_productBasicInfo.hostProductName1 = l_bondProduct.getHostProductName1();
        }
        // HOST銘柄名2 = 債券銘柄.getHOST銘柄名2()
        if (l_bondProduct.getHostProductName2() != null)
        {
            l_productBasicInfo.hostProductName2 = l_bondProduct.getHostProductName2();
        }
        // HOST簡略銘柄名 = 債券銘柄.getHOST簡略銘柄名()
        if (l_bondProduct.getHostShortProductName() != null)
        {
            l_productBasicInfo.hostShortProductName = l_bondProduct.getHostShortProductName();
        }
        // 種別コード = 債券銘柄.get種別コード()
        if (l_bondProduct.getBondCategCode() != null)
        {
            l_productBasicInfo.bondCategCode = l_bondProduct.getBondCategCode();
        }
        // 通貨コード=債券銘柄.get通貨コード()
        if (l_bondProduct.getCurrencyCode() != null)
        {
            l_productBasicInfo.currencyCode = l_bondProduct.getCurrencyCode();
        }
        // 発行市場コード = 債券銘柄.get発行市場コード()
        if (l_bondProduct.getIssueMarketCode() != null)
        {
            l_productBasicInfo.issueMarketCode = l_bondProduct.getIssueMarketCode();
        }
        // 発行体コード = 債券銘柄.get発行体コード()
        if (l_bondProduct.getIssueAssociationCode() != null)
        {
            l_productBasicInfo.issueAssociationCode = l_bondProduct.getIssueAssociationCode();
        }
        // 経過利子計算タイプ = 債券銘柄.get経過利子計算タイプ() 
        if (l_bondProduct.getAccruedInterestCalcType() != null)
        {
            l_productBasicInfo.accruedInterestCalcType = l_bondProduct.getAccruedInterestCalcType();
        }
        // 経過利子起算日 = 債券銘柄.get経過利子起算日()
        if (l_bondProduct.getAccruedInterestStartDay() != null)
        {
            l_productBasicInfo.accruedInterestStartDay = l_bondProduct.getAccruedInterestStartDay();
        }
        // 特殊利払区分 = 債券銘柄.特殊利払区分() 
        if (l_bondProduct.getSpecialPaymentDiv() != null)
        {
            l_productBasicInfo.specialPaymentDiv = l_bondProduct.getSpecialPaymentDiv();
        }
        // フローティングレート・金利期間区分 = 債券銘柄.getフローティングレート・金利期間区分()
        if (l_bondProduct.getFloatingInterestPeriodDiv() != null)
        {
            l_productBasicInfo.floatingInterestPeriodDiv = l_bondProduct.getFloatingInterestPeriodDiv();
        }
        // フローティングレート・金利期間 = 債券銘柄.getフローティングレート・金利期間()
        if (l_bondProduct.getFloatingInterestPeriod() != null)
        {
            l_productBasicInfo.floatingInterestPeriod = l_bondProduct.getFloatingInterestPeriod();
        }
        // フローティングレート・金利種類 = 債券銘柄.getフローティングレート・金利種類()
        if (l_bondProduct.getFloatingInterestType() != null)
        {
            l_productBasicInfo.floatingInterestType = l_bondProduct.getFloatingInterestType();
        }
        // フローティングレート・スプレッド = 債券銘柄.getフローティングレート・スプレッド()
        if (!l_productRow.getFloatingInterestSpreadIsNull())
        {
            l_productBasicInfo.floatingInterestSpread = 
                WEB3StringTypeUtility.formatNumber(l_bondProduct.getFloatingInterestSpread());
        }
        // フローティングレート・ミニマムクーポン = 債券銘柄.getフローティングレート・ミニマムクーポン()
        if (!l_productRow.getFloatingMinCouponIsNull())
        {
            l_productBasicInfo.floatingMinCoupon = 
                WEB3StringTypeUtility.formatNumber(l_bondProduct.getFloatingMinCoupon());
        }
        // 免税区分 = 債券銘柄.get免税区分()
        if (l_bondProduct.getTaxFreeDiv() != null)
        {
            l_productBasicInfo.taxFreeDiv = l_bondProduct.getTaxFreeDiv();
        }
        // S&P = 債券銘柄.getS&P()
        if (l_bondProduct.getSAndP() != null)
        {
            l_productBasicInfo.sAndP = l_bondProduct.getSAndP();
        }
        // MOODY'S = 債券銘柄.getMoody's()
        if (l_bondProduct.getMoodys() != null)
        {
            l_productBasicInfo.moodys = l_bondProduct.getMoodys();
        }
        // CUSIP = 債券銘柄.getCUSIP()
        if (l_bondProduct.getCUSIP() != null)
        {
            l_productBasicInfo.cusip = l_bondProduct.getCUSIP();
        }
        
        //1.8.債券銘柄更新情報()
        WEB3AdminBondProductUpdateInfo l_productUpdateInfo = 
            new WEB3AdminBondProductUpdateInfo();
        
        //1.9.プロパティセット
        //債券銘柄更新情報オブジェクトに以下のプロパティをセットする。     
        // 取扱区分 = 債券銘柄.get取扱区分() 
        if (l_productRow.getTradeHandleDivIsSet())
        {
            l_productUpdateInfo.tradeHandleDiv = l_bondProduct.getTradeHandleDiv();
        }
        // 取扱開始日時 = 債券銘柄.get取扱開始日時()
        if (l_bondProduct.getTradeStartDate() != null)
        {
            l_productUpdateInfo.tradeStartDate = l_bondProduct.getTradeStartDate();
        }
        // 取扱終了日時 = 債券銘柄.get取扱終了日時()
        if (l_bondProduct.getTradeEndDate() != null)
        {
            l_productUpdateInfo.tradeEndDate = l_bondProduct.getTradeEndDate();
        }
        // 募開始日 = 債券銘柄.get応募開始日()
        if (l_bondProduct.getRecruitStartDate() != null)
        {
            l_productUpdateInfo.recruitStartDate = l_bondProduct.getRecruitStartDate();
        }
        // 応募終了日 = 債券銘柄.get応募終了日()
        if (l_bondProduct.getRecruitEndDate() != null)
        {
            l_productUpdateInfo.recruitEndDate = l_bondProduct.getRecruitEndDate();
        }
        // 売買区分 = 債券銘柄.get売買区別()
        if (l_bondProduct.getTradeType() != null)
        {
            l_productUpdateInfo.buySellDiv = l_bondProduct.getTradeType();
        }
        // 銘柄名 = 債券銘柄.get銘柄名()
        if (l_bondProduct.getProductName() != null)
        {
            l_productUpdateInfo.productName = l_bondProduct.getProductName();
        }
        // 買付単価 = 債券銘柄.get買付単価()
        if (!l_productRow.getBuyPriceIsNull())
        {
            l_productUpdateInfo.buyPrice = WEB3StringTypeUtility.formatNumber(l_bondProduct.getBuyPrice());
        }
        // 売却単価 = 債券銘柄.get売却単価()
        if (!l_productRow.getSellPriceIsNull())
        {
            l_productUpdateInfo.sellPrice = WEB3StringTypeUtility.formatNumber(l_bondProduct.getSellPrice());
        }
        // 申込単位 = 債券銘柄.get申込単位()
        if (l_productRow.getTradeUnitIsSet())
        {
            l_productUpdateInfo.tradeUnit = WEB3StringTypeUtility.formatNumber(l_bondProduct.getTradeUnit());
        }
        // 最低額面 = 債券銘柄.get最低額面()
        if (l_productRow.getMinFaceAmountIsSet())
        {
            l_productUpdateInfo.minFaceAmount = 
                WEB3StringTypeUtility.formatNumber(l_bondProduct.getMinFaceAmount());
        }
        // 最高額面 = 債券銘柄.get最高額面()
        if (!l_productRow.getMaxFaceAmountIsNull())
        {
            l_productUpdateInfo.maxFaceAmount = 
                WEB3StringTypeUtility.formatNumber(l_bondProduct.getMaxFaceAmount());
        }
        // カレンダー連携市場コード = 債券銘柄.getカレンダー連携市場コード() 
        if (l_bondProduct.getCalLinkedMarketCode() != null)
        {
            l_productUpdateInfo.calendarLinkedDiv = l_bondProduct.getCalLinkedMarketCode();
        }
        // 買付受渡日移動日数 = 債券銘柄.get買付受渡日移動日数()
        if (!l_productRow.getBuyDeliveryDateShiftdaysIsNull())
        {
            l_productUpdateInfo.buyDeliveryMove = l_bondProduct.getBuyDeliveryDateShiftDays() + "";
        }
        // 売却受渡日移動日数 = 債券銘柄.get売却受渡日移動日数()
        if (!l_productRow.getSellDeliveryDateShiftdaysIsNull())
        {
            l_productUpdateInfo.sellDeliveryMove = l_bondProduct.getSellDeliveryDateShiftDays() + "";
        }
        // 自動約定区分 = 債券銘柄.get自動約定区分()
        if (l_productRow.getAutoExecDivIsSet())
        {
            l_productUpdateInfo.autoExecDiv = l_bondProduct.getAutoExecDiv();
        }
        // 自動約定枠 = 債券銘柄.get自動約定枠()
        if (!l_productRow.getAutoExecLimitIsNull())
        {
            l_productUpdateInfo.autoExecLimit = 
                WEB3StringTypeUtility.formatNumber(l_bondProduct.getAutoExecLimit());
        }
        // カストディアンコード=債券銘柄.getカストディアンコード()
        if (l_bondProduct.getCustodianCode() != null)
        {
            l_productUpdateInfo.custodianCode = l_bondProduct.getCustodianCode();
        }
        
        //仕入時の為替レート= 債券銘柄.get仕入時の為替レート()
        if (!l_productRow.getBuyingFxRateIsNull())
        {
            l_productUpdateInfo.fxRateAtStock = 
                WEB3StringTypeUtility.formatNumber(l_bondProduct.getBuyingFxRate());
        }
        
        //取引時間チェック区分 = 債券銘柄.get取引時間チェック区分()
        if(l_bondProduct.getTradingTimeCheckDiv() != null)
        {
            l_productUpdateInfo.tradeTimeCheckDiv = l_bondProduct.getTradingTimeCheckDiv();
        }
        
        // 仲介手数料率＝債券銘柄.get仲介手数料率
        if (!l_productRow.getMediatorCommissionRateIsNull())
        {
            l_productUpdateInfo.mediatorCommissionRate = 
                WEB3StringTypeUtility.formatNumber(l_bondProduct.getMediatorCommissionRate());
        }
        //応募勧誘形式＝債券銘柄.get応募勧誘形式()
        l_productUpdateInfo.recruitInvitationForm =
            l_productRow.getRecruitInvitationDiv();
        //応募引受け区分＝債券銘柄.get応募引受け区分()
        l_productUpdateInfo.recruitAcceptDiv = l_productRow.getRecruitAcceptDiv();

        //受渡日＝債券銘柄.get受渡日()
        l_productUpdateInfo.deliveryDate = l_bondProduct.getDeliveryDate();

        //1.10.getカストディアン一覧(証券会社)
        //カストディアン一覧を取得する。 
        //[引数]   
        //証券会社：get証券会社 
        WEB3BondDataManagerService l_dataManagerService = 
            (WEB3BondDataManagerService) Services.getService(WEB3BondDataManagerService.class);
        List l_lisCustodians = l_dataManagerService.getCustodianList(l_institution);
        
        //1.11.toカストディアン一覧(List)
        //カストディアン一覧を取得 
        //[toカストディアン一覧()の引数] 
        //カストディアンリスト：getカストディアン一覧
        WEB3AdminBondHelperService l_helperService = 
            (WEB3AdminBondHelperService) Services.getService(WEB3AdminBondHelperService.class);
        List l_lisCustodianLists = l_helperService.toCustodianList(l_lisCustodians);
        
        //1.12.get債券自動約定枠履歴一覧(BondProduct)
        //債券自動約定枠履歴一覧を取得する。 
        //[引数]  
        //債券銘柄： get債券銘柄
        List l_lisAutoExecLimitHistorys = l_bondProductManager.getBondAutoExecLimitHistoryList(l_bondProduct);
        
        //1.13.get自動約定枠履歴一覧()の戻り値の要素数分LOOP
        //get自動約定枠履歴一覧()の戻り値の要素数分LOOPして、 
        //自動約定枠履歴配列を作成する。
        //WEB3AdminBondAutoExecLimitHistoryUnit 
        List l_lisLimitHistoryUnits = new ArrayList(); 
        if (l_lisAutoExecLimitHistorys != null || !l_lisAutoExecLimitHistorys.isEmpty())
        {
            
            for (int i = 0; i < l_lisAutoExecLimitHistorys.size(); i++)
            {
                BondAutoExecLimitActionRow l_actionRow = 
                    (BondAutoExecLimitActionRow)l_lisAutoExecLimitHistorys.get(i);
                //1.13.1.to自動約定枠履歴(自動約定枠履歴Row)
                //[引数] 
                // 自動約定枠履歴Row：　@LOOP対象の自動約定枠履歴Row　@
                l_lisLimitHistoryUnits.add(toAutoExecLimitAction(l_actionRow));
            }
        }
        WEB3AdminBondAutoExecLimitHistoryUnit[] l_autoExecLimitHistoryUnit = 
            new WEB3AdminBondAutoExecLimitHistoryUnit[l_lisLimitHistoryUnits.size()];
        l_lisLimitHistoryUnits.toArray(l_autoExecLimitHistoryUnit);
        //1.14.get債券銘柄約定済残高(Institution, String)
        //債券銘柄約定済残高を取得する。 
        //[引数]  
        //証券会社： get証券会社 
        //銘柄コード(WEB3)：リクエストデータ.銘柄コード(WEB3)
        WEB3BondOrderManager l_bondOrderManager = 
            (WEB3BondOrderManager) l_finApp.getTradingModule(ProductTypeEnum.BOND).getOrderManager();
        double l_dblProductExecutedBalance = 
            l_bondOrderManager.getBondProductExecutedBalance(l_institution, l_request.productCode);
        
        //1.15.get債券銘柄利率一覧(BondProduct)
        //債券銘柄利率一覧を取得する。 
        //[引数]  
        //債券銘柄： get債券銘柄
        List l_lisCouponList = l_bondProductManager.getBondProductCouponList(l_bondProduct);
        
        //1.16.get債券銘柄利率一覧（）の戻り値の先頭から10件分までLOOP
        //get債券銘柄利率一覧（）の戻り値の先頭から10件分までLOOPして、 
        //債券銘柄利率配列を作成する。 
        //※11件目以降は以下の処理を行なわない。 
        List l_lisProductCouponUnit = new ArrayList(); 
        
        if (l_lisCouponList != null)
        {
            if (l_lisCouponList.size() > 10)
            {
                for (int j = 0; j < 10; j++)
                {
                    BondProductCouponRow l_couponRow = (BondProductCouponRow) l_lisCouponList.get(j);
                    //to債券銘柄利率(債券銘柄利率Row)
                    //[引数] 
                    //　@債券銘柄利率：　@LOOP対象の債券銘柄利率Row
                    l_lisProductCouponUnit.add(toBondProductCoupon(l_couponRow));
                }
            }
            else
            {
                for (int j = 0; j < l_lisCouponList.size(); j++)
                {
                    BondProductCouponRow l_couponRow = (BondProductCouponRow) l_lisCouponList.get(j);
                    //to債券銘柄利率(債券銘柄利率Row)
                    //[引数] 
                    //　@債券銘柄利率：　@LOOP対象の債券銘柄利率Row
                    l_lisProductCouponUnit.add(toBondProductCoupon(l_couponRow));
                }
            }
        }
        WEB3AdminBondProductCouponUnit[] l_productCouponUnits = 
            new WEB3AdminBondProductCouponUnit[l_lisProductCouponUnit.size()];
        l_lisProductCouponUnit.toArray(l_productCouponUnits);
        
        //1.17get取扱可能外株市場(String)
        //証券会社コード：管理者.get証券会社コード
        WEB3GentradeFinObjectManager l_finObjectManager = 
            (WEB3GentradeFinObjectManager) l_finApp.getFinObjectManager();
        
        Market[] l_markets = l_finObjectManager.getOpenFeqMarkets(l_admin.getInstitutionCode());
      
        //1.18.createレスポンス( )
        WEB3AdminBondProductRegistInputResponse l_response = null;
        l_response = (WEB3AdminBondProductRegistInputResponse) l_request.createResponse();
        
        //1.19.プロパティセット
        //レスポンスデータに以下のプロパティをセットする。 
        // 債券銘柄基本情報 = 債券銘柄基本情報オブジェクト
        l_response.basicInfo = l_productBasicInfo;
        // 債券銘柄更新情報 = 債券銘柄更新情報オブジェクト
        l_response.updateInfo = l_productUpdateInfo;
        // カストディアン一覧 = toカストディアン一覧（）の戻り値をセットする
        WEB3AdminBondCustodianUnit[] l_custodianUnits = null;
        if (l_lisCustodianLists != null && !l_lisCustodianLists.isEmpty())
        {
            l_custodianUnits = new WEB3AdminBondCustodianUnit[l_lisCustodianLists.size()];
            l_lisCustodianLists.toArray(l_custodianUnits);
        }
        l_response.custodianList = l_custodianUnits;
        // 自動約定枠履歴一覧 = 作成した自動約定枠履歴配列をセットする。
        l_response.autoExecLimitList = l_autoExecLimitHistoryUnit;
        // 約定済残高 = get債券銘柄約定済残高()
        l_response.executedBalance = 
            WEB3StringTypeUtility.formatNumber(l_dblProductExecutedBalance);
        // 債券銘柄利率一覧 = 作成した債券銘柄利率配列をセットする。
        l_response.productCouponList = l_productCouponUnits;
        // 管理者コード = 債券銘柄.get最終更新者コード()
        l_response.administratorCode = l_bondProduct.getLastUpdater();
        // 管理者最終更新日時 = 債券銘柄.get管理者更新日付()
        l_response.lastUpdateTime = l_bondProduct.getAdminLastUpdatedTimestamp();
        // 取扱区分リスト = {不可,  管理者,　@管理者/顧客}　@※コード値によるString配列
        String[] l_strTradeHandleDivs = 
            new String[]{
                WEB3TradeHandleDivDef.DISABLED,
                WEB3TradeHandleDivDef.MANAGER, 
                WEB3TradeHandleDivDef.MANAGER_CUSTOMER};
        l_response.tradeHandleDivList = l_strTradeHandleDivs;
        
        // 売買区分リスト = {買付,  売却, 応募, 買付/売却｝　@※コード値によるString配列
        String[] l_strBuySellDivs =
            new String[]{
                WEB3BondTradeTypeDef.BUY, 
                WEB3BondTradeTypeDef.SELL, 
                WEB3BondTradeTypeDef.RECRUIT, 
                WEB3BondTradeTypeDef.BUY_SELL};
        l_response.buySellDivList = l_strBuySellDivs;
        
        // カレンダー連携市場コードリスト 
        String[] l_strMarketCodes = new String[l_markets.length];
        int l_intIndex = l_markets.length;
        for (int i = 0; i < l_intIndex; i++)
        {
            l_strMarketCodes[i] = l_markets[i].getMarketCode();
        }
        
        l_response.calLinkedDivList = l_strMarketCodes;
        
        // 自動約定枠区分リスト = {非自動約定, 自動約定｝　@※コード値によるString配列
        String[] l_strAutoExecDivs = 
            new String[]{
                WEB3AutoExecDivDef.NOT_AUTO_EXECUTE, 
                WEB3AutoExecDivDef.AUTO_EXECUTE};
        l_response.autoExecDivList = l_strAutoExecDivs;
        
        //取引時間チェック区分リスト = {取引時間をチェックする, 取引時間をチェックしない}
        //※コード値によるString配列
        String[] l_strTradeTimeCheckDiv = 
            new String[]{
                WEB3TradingTimeCheckDivDef.TRADING_TIME_CHECK,
                WEB3TradingTimeCheckDivDef.TRADING_TIME_NOT_CHECK};
        l_response.tradeTimeCheckDivList = l_strTradeTimeCheckDiv;

        //応募勧誘形式リスト = {募集,　@売出し,　@私募,　@募集売出し}　@※コード値によるString配列
        String[] l_strRecruitInvitationDivs =
            new String[]{
                WEB3BondRecruitInvitationFormDef.RECRUIT,
                WEB3BondRecruitInvitationFormDef.SELL, 
                WEB3BondRecruitInvitationFormDef.PRIVATE_RECRUIT,
                WEB3BondRecruitInvitationFormDef.RECRUIT_SELL};
        l_response.recruitInvitationFormList = l_strRecruitInvitationDivs;
        //応募引受け区分リスト = {引受け,　@非引受け}　@※コード値によるString配列
        String[] l_strRecruitAcceptDivs =
            new String[]{
                WEB3BondRecruitAcceptDivDef.ACCEPT,
                WEB3BondRecruitAcceptDivDef.UNACCEPT};
        l_response.recruitAcceptDivList = l_strRecruitAcceptDivs;

        log.exiting(STR_METHOD_NAME);         
        return l_response;
    }
    
    /**
     * (validate銘柄登録)<BR>
     * 債券銘柄登録確認処理を行う。<BR>
     * <BR>
     * シーケンス図「(債券)validate銘柄登録」を参照
     * @@param l_request - (リクエストデータ)<BR>
     * リクエストデータ
     * @@return webbroker3.bd.message.WEB3AdminBondProductRegistConfirmResponse
     * @@throws WEB3BaseException
     * @@roseuid 44B601CA0125
     */
    protected WEB3AdminBondProductRegistConfirmResponse validateProductRegister(
        WEB3AdminBondProductRegistConfirmRequest l_request) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = 
            " validateProductRegister(WEB3AdminBondProductRegistConfirmRequest)";
        log.entering(STR_METHOD_NAME);
        
        //1.1. validate( )
        l_request.validate();
        
        //1.2.getInstanceFromログイン情報( )
        WEB3Administrator l_admin = 
            WEB3Administrator.getInstanceFromLoginInfo();
        
        //1.3.validate権限(機@能カテゴリコード : String, is更新 : boolean)
        //管理者の権限チェックをする  
        //[validate権限()に指定する引数]  
        //機@能カテゴリコード：　@機@能カテゴリコード.債券銘柄管理 
        //is更新：　@true 
        l_admin.validateAuthority(
            WEB3TransactionCategoryDef.BOND_PRODUCT_MANAGE,
            true);
        
        //1.4.get証券会社( )
        Institution l_institution = l_admin.getInstitution();
        
        //1.5.validate銘柄内容(Institution, 債券銘柄更新情報)
        //債券銘柄内容の整合性をチェックする。 
        //[引数] 
        //証券会社:get証券会社() 
        //債券銘柄内容：リクエスト.債券銘柄更新情報
        //銘柄コード(WEB3):　@リクエストデータ.銘柄コード(WEB3)
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        WEB3BondProductManager l_bondProductManager = 
            (WEB3BondProductManager) l_finApp.getTradingModule(ProductTypeEnum.BOND).getProductManager();   
        l_bondProductManager.validateProductSpec(l_institution, l_request.updateInfo, l_request.productCode);
        
        //1.6.createレスポンス( )
        WEB3AdminBondProductRegistConfirmResponse l_response = null;
        l_response = (WEB3AdminBondProductRegistConfirmResponse) l_request.createResponse();
        
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * (submit銘柄登録)<BR>
     * 債券銘柄登録完了処理を行う。<BR>
     * <BR>
     * シーケンス図「(債券)submit銘柄登録」を参照
     * @@param l_request - (リクエストデータ)<BR>
     * リクエストデータ
     * @@return webbroker3.bd.message.WEB3AdminBondProductRegistCompleteResponse
     * @@throws WEB3BaseException
     * @@roseuid 44B601CA0127
     */
    protected WEB3AdminBondProductRegistCompleteResponse submitProductRegister(
        WEB3AdminBondProductRegistCompleteRequest l_request) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = 
            " submitProductRegister(WEB3AdminBondProductRegistCompleteRequest)";
        log.entering(STR_METHOD_NAME);
        
        //1.1.validate( )
        l_request.validate();
        
        //1.2.getInstanceFromログイン情報( )
        WEB3Administrator l_admin = 
            WEB3Administrator.getInstanceFromLoginInfo();
        
        //1.3.validate権限(機@能カテゴリコード : String, is更新 : boolean)
        //管理者の権限チェックをする  
        //[validate権限()に指定する引数]  
        //機@能カテゴリコード：　@機@能カテゴリコード.債券（約定変更、約定取消）  
        //is更新：　@true
        l_admin.validateAuthority(
            WEB3TransactionCategoryDef.BOND_PRODUCT_MANAGE,
            true);
        
        //1.4.validate取引パスワード(パスワード : String)
        //取引パスワードが正しいかチェックする 
        //[validate取引パスワード()の引数] 
        //パスワード：リクエストデータ.暗証番号
        l_admin.validateTradingPassword(l_request.password);
        
        //1.5.get証券会社( )
        Institution l_institution = l_admin.getInstitution();
        
        //1.6.get管理者コード( )
        String l_strtAdministratorCode = l_admin.getAdministratorCode();
        
        //1.7.validate銘柄内容(Institution, 債券銘柄更新情報)
        //債券銘柄内容の整合性をチェックする。 
        //[引数] 
        //証券会社:get証券会社() 
        //債券銘柄内容：リクエスト.債券銘柄更新情報
        //銘柄コード(WEB3):　@リクエストデータ.銘柄コード(WEB3)
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        WEB3BondProductManager l_bondProductManager = 
            (WEB3BondProductManager) l_finApp.getTradingModule(ProductTypeEnum.BOND).getProductManager();   
        l_bondProductManager.validateProductSpec(l_institution, l_request.updateInfo, l_request.productCode);
        
        //1.8.update債券銘柄内容(Institution, String, 債券銘柄更新情報, String)
        //債券銘柄内容をDBへ反映する。 
        //[引数] 
        //証券会社:　@get証券会社（） 
        //銘柄コード(WEB3):　@リクエストデータ.銘柄コード(WEB3) 
        //債券銘柄更新情報:　@リクエストデータ.債券銘柄更新情報 
        //管理者コード:　@get管理者コード（） 
        l_bondProductManager.updateBondProductSpec(
                l_institution, l_request.productCode, l_request.updateInfo, l_strtAdministratorCode);
        
        //1.9.createレスポンス( )
        WEB3AdminBondProductRegistCompleteResponse l_response = null;
        l_response = (WEB3AdminBondProductRegistCompleteResponse) l_request.createResponse();
        
        //1.10.プロパティセット
        //更新時間 = 現在日時
        l_response.lastUpdatedTimestamp = GtlUtils.getSystemTimestamp(); 
        
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * (to自動約定枠履歴)<BR>
     * レスポンスデータ用に管理者メッセージ.自動約定枠履歴を生成する。<BR>
     * <BR>
     * １）自動約定履歴オブジェクトを生成する。<BR>
     * <BR>
     * ２）プロパティセット<BR>
     * 　@　@自動約定枠履歴.約定枠更新日付 = 債券自動約定枠履歴Rowオブジェクト.get約定枠更新日付()<BR>
     * 　@　@自動約定枠履歴.約定済残高　@　@　@ = 債券自動約定枠履歴Rowオブジェクト.get約定済残高()<BR>
     * 　@　@自動約定枠履歴.自動約定枠　@　@　@ = 債券自動約定枠履歴Rowオブジェクト.get自動約定枠()<BR>
     * <BR>
     * ３）作成した自動約定履歴オブジェクトを返却する。
     * @@param l_row - (自動約定枠履歴Row)<BR>
     * 自動約定枠履歴Row
     * @@return webbroker3.bd.message.WEB3AdminBondAutoExecLimitHistoryUnit
     * @@throws WEB3BaseException
     * @@roseuid 44D9C85E00F9
     */
    protected WEB3AdminBondAutoExecLimitHistoryUnit toAutoExecLimitAction(
        BondAutoExecLimitActionRow l_row) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = 
            " toAutoExecLimitAction(BondAutoExecLimitActionRow)";
        log.entering(STR_METHOD_NAME);
         
        //１）自動約定履歴オブジェクトを生成する。 
        WEB3AdminBondAutoExecLimitHistoryUnit l_limitHistoryUnit = 
            new WEB3AdminBondAutoExecLimitHistoryUnit();
        
        //２）プロパティセット 
        //　@　@自動約定枠履歴.約定枠更新日付 = 債券自動約定枠履歴Rowオブジェクト.get約定枠更新日付() 
        l_limitHistoryUnit.executionUpdateDate = l_row.getExecutionUpdateDate();
        //　@　@自動約定枠履歴.約定済残高　@　@　@ = 債券自動約定枠履歴Rowオブジェクト.get約定済残高() 
        l_limitHistoryUnit.executedBalance = WEB3StringTypeUtility.formatNumber(l_row.getAutoExecAmount());
        //　@　@自動約定枠履歴.自動約定枠　@　@　@ = 債券自動約定枠履歴Rowオブジェクト.get自動約定枠() 
        l_limitHistoryUnit.autoExecLimit = WEB3StringTypeUtility.formatNumber(l_row.getAutoExecLimit());

        //３）作成した自動約定履歴オブジェクトを返却する。
        
        log.exiting(STR_METHOD_NAME);
        return l_limitHistoryUnit;
    }
    
    /**
     * (to債券銘柄利率)<BR>
     * レスポンスデータ用に管理者メッセージ.債券銘柄利率を生成する。<BR>
     * <BR>
     * １）債券銘柄利率オブジェクトを生成する。<BR>
     * <BR>
     * ２）プロパティセット<BR>
     * 　@　@債券銘柄利率.利払日 = 債券銘柄利率Row.利払日<BR>
     * 　@　@債券銘柄利率.利率 　@= 債券銘柄利率Row.利率<BR>
     * <BR>
     * ３）作成した債券銘柄利率オブジェクトを返却する。
     * @@param l_row - (債券銘柄利率Row)<BR>
     * 債券銘柄利率Row
     * @@return WEB3AdminBondProductCouponUnit
     * @@throws WEB3BaseException
     * @@roseuid 44D9C8670185
     */
    protected WEB3AdminBondProductCouponUnit toBondProductCoupon(
        BondProductCouponRow l_row) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = "toBondProductCoupon(BondProductCouponRow)";
        log.entering(STR_METHOD_NAME);
        
        //１）債券銘柄利率オブジェクトを生成する。 
        WEB3AdminBondProductCouponUnit l_productCouponUnit = 
            new WEB3AdminBondProductCouponUnit();
        //２）プロパティセット 
        //　@　@債券銘柄利率.利払日 = 債券銘柄利率Row.利払日 
        l_productCouponUnit.interestPaymentDay = l_row.getInterestPaymentDay();
        
        //　@　@債券銘柄利率.利率 　@= 債券銘柄利率Row.利率 
        l_productCouponUnit.coupon = WEB3StringTypeUtility.formatNumber(l_row.getCoupon());
        //３）作成した債券銘柄利率オブジェクトを返却する。

        log.exiting(STR_METHOD_NAME);
        return l_productCouponUnit;
    }
}
@
