head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.05.46.55;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6f44d7efa104045;
filename	WEB3AdminIpoManagementDetailsServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者IPO銘柄管理・詳細サービスImpl(WEB3AdminIpoManagementDetailsServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/08/17 李頴淵 新規作成
Revesion History : 2004/12/29 坂上(SRA) 残対応>>>043
Revesion History : 2005/01/07 坂上(SRA) 残対応>>>065
Revesion History : 2005/12/20 譚漢江 (中訊) 仕様変更No.101修正
*/

package webbroker3.ipo.service.delegate.stdimpls;

import java.util.ArrayList;
import java.util.List;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.util.ThreadLocalSystemAttributesRegistry;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingSystem;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3IpoRegistDivDef;
import webbroker3.common.define.WEB3TransactionCategoryDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.gentrade.WEB3Administrator;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.ipo.WEB3IpoProductImpl;
import webbroker3.ipo.WEB3IpoProductManagerImpl;
import webbroker3.ipo.data.IpoProductParams;
import webbroker3.ipo.data.IpoProductRow;
import webbroker3.ipo.define.WEB3ControlCodeListDef;
import webbroker3.ipo.define.WEB3UndecideDecideDivDef;
import webbroker3.ipo.message.WEB3AdminIPOManagementRequest;
import webbroker3.ipo.message.WEB3AdminIPOManagementResponse;
import webbroker3.ipo.message.WEB3AdminIPOProductDetailsRequest;
import webbroker3.ipo.message.WEB3AdminIPOProductDetailsResponse;
import webbroker3.ipo.message.WEB3AdminIPOPublicOfferingProductUnit;
import webbroker3.ipo.message.WEB3IPOProductInfo;
import webbroker3.ipo.message.WEB3IPOTermUnit;
import webbroker3.ipo.service.delegate.WEB3AdminIpoManagementDetailsService;
import webbroker3.ipo.service.delegate.WEB3IpoProductInfoService;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * (管理者IPO銘柄管理・詳細サービスImpl)<BR>
 * 管理者IPO銘柄管理・詳細サービス実装クラス
 * 
 * @@author 李頴淵
 * @@version 1.0
 */
public class WEB3AdminIpoManagementDetailsServiceImpl implements WEB3AdminIpoManagementDetailsService 
{
    
    /**
     * ログ出力ユーティリティ。<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminIpoManagementDetailsServiceImpl.class);
    
    /**
     * @@roseuid 4112EEE30395
     */
    public WEB3AdminIpoManagementDetailsServiceImpl() 
    {
     
    }
    
    /**
     * 管理者IPO銘柄管理・詳細処理を実施する。<BR>
     * <BR>
     * １）　@業務日時設定<BR>
     * 　@取引時間管理.setBusinessTimestamp()をコールする。<BR>
     * <BR>
     * ２）　@リクエストデータの型により、以下の通りメソッドをコールする。<BR>
     * <BR>
     * ○ 引数のリクエストデータが、管理者IPO銘柄管理リクエストの場合<BR>
     * 　@－get銘柄一覧()をコールする。<BR>
     * ○ 引数のリクエストデータが、管理者IPO銘柄詳細リクエストの場合<BR>
     * 　@－get銘柄詳細()をコールする。<BR>
     * @@param l_request - リクエスト
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 40C666B003B6
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " execute(WEB3GenRequest)";
        log.entering(STR_METHOD_NAME);
        
        // Timestampリセット
        ThreadLocalSystemAttributesRegistry.setAttribute(WEB3GentradeTradingTimeManagement.TIMESTAMP_TAG, null);
        
        // Timestamp設定
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        TradingSystem l_tradingSys = l_finApp.getTradingSystem();
        java.sql.Timestamp l_processTime = l_tradingSys.getSystemTimestamp();
        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3GentradeTradingTimeManagement.TIMESTAMP_TAG,
            l_processTime);
        
        if (l_request instanceof WEB3AdminIPOManagementRequest)
        {
            log.debug("WEB3AdminIpoManagementRequest");
            WEB3AdminIPOManagementResponse l_ipoManagementResponse = getProductList(
                (WEB3AdminIPOManagementRequest)l_request);
            
            log.exiting(STR_METHOD_NAME);
            return l_ipoManagementResponse;
        }
        else if (l_request instanceof WEB3AdminIPOProductDetailsRequest)
        {
            log.debug("WEB3AdminIpoProductDetailsRequest");
            WEB3AdminIPOProductDetailsResponse l_ipoProductDetailsResponse = getProductDetails(
                (WEB3AdminIPOProductDetailsRequest)l_request);
            
            log.exiting(STR_METHOD_NAME);
            return l_ipoProductDetailsResponse;
        }
        else
        {
            // Timestampリセット
            ThreadLocalSystemAttributesRegistry.setAttribute(WEB3GentradeTradingTimeManagement.TIMESTAMP_TAG, null);
            
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80018,
                getClass().getName() + STR_METHOD_NAME);
        }
    }
    
    /**
     * (get銘柄一覧)<BR>
     * IPO銘柄一覧取得処理を行う。<BR>
     * （管理者IPO銘柄管理画面に表示）<BR>
     * <BR>
     * シーケンス図<BR>
     * 「IPO（管理者・銘柄管理）get銘柄一覧」参照。<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * 管理者IPO銘柄管理リクエストデータオブジェクト
     * @@return webbroker3.ipo.message.WEB3AdminIPOManagementResponse
     * @@roseuid 40C6677200B8
     */
    protected WEB3AdminIPOManagementResponse getProductList(WEB3AdminIPOManagementRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " getProductList(WEB3AdminIPOManagementRequest)";
        log.entering(STR_METHOD_NAME);
        
        //1.getInstanceFromログイン情報
        WEB3Administrator l_administrator = WEB3Administrator.getInstanceFromLoginInfo();
        
        //2.validate権限
        l_administrator.validateAuthority(WEB3TransactionCategoryDef.ADMIN_IPO_PROD_OPE,false);
        
        //3. get証券会社コード
        String l_strInstitutionCode = l_administrator.getInstitutionCode();
        
        //4.get有効銘柄
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        WEB3IpoProductManagerImpl l_ipoProductManagerImpl = 
            (WEB3IpoProductManagerImpl)l_finApp.getTradingModule(ProductTypeEnum.IPO).getProductManager();
        WEB3IpoProductImpl[] l_ipoProductImpl = null; 
        l_ipoProductImpl = l_ipoProductManagerImpl.getOpenIpoProducts(l_strInstitutionCode,WEB3IpoRegistDivDef.OPEN_LISTING);
        
        //5.create銘柄明細
        WEB3AdminIPOPublicOfferingProductUnit[] l_ipoPublicOfferingProductUnit = null;
        if(l_ipoProductImpl != null)
        {
            l_ipoPublicOfferingProductUnit = this.createProductUnits(l_ipoProductImpl);
        
        }
        
        //6.get有効銘柄
        WEB3IpoProductImpl[] l_ipoProductImpl2 = null; 
        l_ipoProductImpl2 = l_ipoProductManagerImpl.getOpenIpoProducts(l_strInstitutionCode,WEB3IpoRegistDivDef.LISTED);
        
        //7.create銘柄明細
        WEB3AdminIPOPublicOfferingProductUnit[] l_ipoPublicOfferingProductUnit2 = null;
        if(l_ipoProductImpl2 != null)
        {
            l_ipoPublicOfferingProductUnit2 = this.createProductUnits(l_ipoProductImpl2);
         
        }
        
        //8.管理者IPO銘柄管理レスポンス
        WEB3AdminIPOManagementResponse l_ipoManagementResponse = null;
        l_ipoManagementResponse = (WEB3AdminIPOManagementResponse)l_request.createResponse();
        
        //9.プロパティセット
        l_ipoManagementResponse.newListingList = l_ipoPublicOfferingProductUnit;
        l_ipoManagementResponse.listingList = l_ipoPublicOfferingProductUnit2;
        
        log.exiting(STR_METHOD_NAME);
        return l_ipoManagementResponse;
    }
    
    /**
     * (get銘柄詳細)<BR>
     * IPO銘柄詳細取得処理を行う。<BR>
     * <BR>
     * シーケンス図<BR>
     * 「IPO（管理者・銘柄詳細）get銘柄詳細」参照。<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * 管理者IPO銘柄詳細リクエストデータオブジェクト
     * @@return webbroker3.ipo.message.WEB3AdminIPOProductDetailsResponse
     * @@roseuid 40C6677C01F0
     */
    protected WEB3AdminIPOProductDetailsResponse getProductDetails(WEB3AdminIPOProductDetailsRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " getProductDetails(WEB3AdminIPOProductDetailsRequest)";
        log.entering(STR_METHOD_NAME);
        
        //1.getInstanceFromログイン情報
        WEB3Administrator l_administrator = WEB3Administrator.getInstanceFromLoginInfo();
        
        //2.validate権限
        l_administrator.validateAuthority(WEB3TransactionCategoryDef.ADMIN_IPO_PROD_OPE,false);
        
        long l_ipoProductId = Long.parseLong(l_request.id);
            
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        
        try
        {
            //3.IPO銘柄
            WEB3IpoProductManagerImpl l_ipoProductManager 
                = (WEB3IpoProductManagerImpl)l_finApp.getTradingModule(ProductTypeEnum.IPO).getProductManager();
            WEB3IpoProductImpl l_ipoProduct 
                = (WEB3IpoProductImpl)(l_ipoProductManager.getProduct(l_ipoProductId));  //thorw NotFoundException
            
            //4.is削除銘柄()
            boolean l_blnDeletionProduct = l_ipoProduct.isDeletedProduct();
            log.debug("is削除銘柄() = " + l_blnDeletionProduct);        
        
            //5.(is削除銘柄() == true)の場合、例外をスローする。
            if(l_blnDeletionProduct)
            {
            
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00588,
                    this.getClass().getName() + STR_METHOD_NAME);            
            
            }       
        }
        catch(NotFoundException nf_ex)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(WEB3ErrorCatalog.SYSTEM_ERROR_80005,STR_METHOD_NAME);
        }
        
        //6.ArrayList
        List l_lisArrayList = new ArrayList();
        
        //7.add(操作コード.”訂正” : Object)
        l_lisArrayList.add(WEB3ControlCodeListDef.CHANGE);
        
        //8.add(操作コード.”削除” : Object)
        l_lisArrayList.add(WEB3ControlCodeListDef.CANCEL);
        
        //9.add(操作コード.”ブックビルディング状況ダウンロード” : Object)
        l_lisArrayList.add(WEB3ControlCodeListDef.BOOK_BUILDING_SITUATION_DOWNLOAD);
        
        //10.add(操作コード.”抽選結果アップロード” : Object)
        l_lisArrayList.add(WEB3ControlCodeListDef.LOT_RESULT_UPLOAD);
        
        //11.add(操作コード.”抽選結果／購入申込状況ダウンロード” : Object)
        l_lisArrayList.add(WEB3ControlCodeListDef.LOT_RESULT_OFFER_STATE_DOWNLOAD);
        
        //12.add(操作コード.”IPO募集停止／再開” : Object)
        l_lisArrayList.add(WEB3ControlCodeListDef.IPO_RECRUIT_STOP_RESUMPTION);
        
        //13.add(操作コード.”IPO中止” : Object)
        l_lisArrayList.add(WEB3ControlCodeListDef.IPO_DISCONTINUATION);
        
        //14.add(int, Object)
        l_lisArrayList.add(WEB3ControlCodeListDef.IPO_LOT_INPUT);

        //15.add(int, Object)
        l_lisArrayList.add(WEB3ControlCodeListDef.IPO_LOT_RESULT_CONFIRM);

        //16.add(int, Object)
        l_lisArrayList.add(WEB3ControlCodeListDef.IPO_LOT_RESULT_COMPLETE);
        
        //17.toArray( )
        String[] l_strControlCodes = null;
        l_strControlCodes = new String[l_lisArrayList.size()];
        l_lisArrayList.toArray(l_strControlCodes);
        
        //18. createIPO銘柄情報(long)
        WEB3IpoProductInfoService l_service =
                (WEB3IpoProductInfoService)Services.getService(WEB3IpoProductInfoService.class);
        WEB3IPOProductInfo l_ipoProductInfo = l_service.createIpoProductInfo(Long.parseLong(l_request.id));        
        
        //19.管理者IPO銘柄詳細レスポンス
        WEB3AdminIPOProductDetailsResponse l_ipoProductDetailsResponse = (WEB3AdminIPOProductDetailsResponse)l_request.createResponse();
               
        //20. プロパティセット
        l_ipoProductDetailsResponse.controlCodeList = l_strControlCodes;
        l_ipoProductDetailsResponse.ipoProductInfo = l_ipoProductInfo;
        
        log.exiting(STR_METHOD_NAME);
        return l_ipoProductDetailsResponse;
    }
    
    /**
     * (create銘柄明細)<BR>
     * 引数のIPO銘柄[]の内容で、管理者IPO公開銘柄明細メッセージデータを作成する。<BR>
     * <BR>
     * シーケンス図<BR>
     * 「IPO（管理者・銘柄管理）create銘柄明細」<BR>
     * 参照。<BR>
     * @@param l_ipoProducts - (IPO銘柄リスト)<BR>
     * IPO銘柄オブジェクトの配列
     * @@return WEB3AdminIpoPublicOfferingProductUnit[]
     * @@roseuid 40C68F290015
     */
    protected WEB3AdminIPOPublicOfferingProductUnit[] createProductUnits(WEB3IpoProductImpl[] l_ipoProducts) 
    {
        final String STR_METHOD_NAME = " createProductUnits(WEB3IpoProductImpl[])";
        log.entering(STR_METHOD_NAME);
        
        //1.ArrayList
        List l_arrayList = new ArrayList();
        
        //2.IPO銘柄 各要素毎のLOOP処理
        int l_intLength = l_ipoProducts.length;
        log.debug("l_intLength = " + l_intLength);
        for (int i = 0; i < l_intLength; i++)
        {
            IpoProductRow l_ipoProductRow =(IpoProductRow)l_ipoProducts[i].getDataSourceObject();
            IpoProductParams l_ipoProductParams = new IpoProductParams(l_ipoProductRow);
            
            //2.1isスケジュール決定
            boolean l_blnScheduleDecision = l_ipoProducts[i].isScheduleDecision();
        
            //2.2getIPOスケジュール
            String l_strIpoSchedule = l_ipoProducts[i].getIpoSchedule();
        
            //2.3IPO期間指定
            WEB3IPOTermUnit l_ipoTermUnit = new WEB3IPOTermUnit(); 
            
            //2.4抽選日インスタンス プロパティセット
            
            l_ipoTermUnit.startDate = l_ipoProductParams.getLotDate();
            if (l_ipoProductParams.getLotDateCountIsNull())
            {
                l_ipoTermUnit.bizDateUpper = null;
            }
            else
            {
                l_ipoTermUnit.bizDateUpper = Integer.toString(l_ipoProductParams.getLotDateCount());
            }           
            
            //2.5 IPO期間指定
            WEB3IPOTermUnit l_ipoTermUnitOfferDate = new WEB3IPOTermUnit();
            
            //2.6購入申込期間（当社指定）インスタンス プロパティセット
            l_ipoTermUnitOfferDate.startDate = l_ipoProductParams.getOfferStartDatetime();
            l_ipoTermUnitOfferDate.endDate = l_ipoProductParams.getOfferEndDatetime();
            // 2004/11/26 障害管理票No.U00480 営業日上限値と営業日下限値に逆の値がセットされる 水落@@SRA START
//            if (l_ipoProductParams.getOfferStartDateCountIsNull())
//            {
//                l_ipoTermUnitOfferDate.bizDateUpper = null;
//            }
//            else
//            {
//                l_ipoTermUnitOfferDate.bizDateUpper = Integer.toString(l_ipoProductParams.getOfferStartDateCount());
//            }
//            if (l_ipoProductParams.getOfferEndDateCountIsNull())
//            {
//                l_ipoTermUnitOfferDate.bizDateLower = null;
//            }
//            else
//            {
//                l_ipoTermUnitOfferDate.bizDateLower = Integer.toString(l_ipoProductParams.getOfferEndDateCount());
//            }          
            if (l_ipoProductParams.getOfferStartDateCountIsNull())
            {
                l_ipoTermUnitOfferDate.bizDateUpper = null;
            }
            else
            {
                l_ipoTermUnitOfferDate.bizDateUpper = Integer.toString(l_ipoProductParams.getOfferStartDateCount());
            }
            if (l_ipoProductParams.getOfferEndDateCountIsNull())
            {
                l_ipoTermUnitOfferDate.bizDateLower = null;
            }
            else
            {
                l_ipoTermUnitOfferDate.bizDateLower = Integer.toString(l_ipoProductParams.getOfferEndDateCount());
            }          
            // 2004/11/26 障害管理票No.U00480 営業日上限値と営業日下限値に逆の値がセットされる 水落@@SRA END
            
            //2.7 IPO期間指定
            WEB3IPOTermUnit l_ipoTermUnitPublicDate = new WEB3IPOTermUnit();
            
            //2.8公開日インスタンス プロパティセット
            l_ipoTermUnitPublicDate.startDate = l_ipoProductParams.getPublicOfferingDate();
            if (l_ipoProductParams.getPublicOfferingDateCountIsNull())
            {
                l_ipoTermUnitPublicDate.bizDateUpper = null;
            }
            else
            {
                l_ipoTermUnitPublicDate.bizDateUpper = Integer.toString(l_ipoProductParams.getPublicOfferingDateCount());
            }
                      
            //2.9 管理者IPO公開銘柄明細
            WEB3AdminIPOPublicOfferingProductUnit l_ipoPublicOfferingProductUnit = new WEB3AdminIPOPublicOfferingProductUnit();
            
            //2.10プロパティセット
            //ＩＤ：　@IPO銘柄.IPO銘柄ＩＤ
            l_ipoPublicOfferingProductUnit.id = Long.toString(l_ipoProductParams.getIpoProductId());
            
            //銘柄コード：　@IPO銘柄.銘柄コード
            l_ipoPublicOfferingProductUnit.productCode = l_ipoProductParams.getProductCode();   
                     
            //銘柄名：　@IPO銘柄.銘柄名
            l_ipoPublicOfferingProductUnit.productName = l_ipoProductParams.getStandardName();
            
            //公開市場コード：　@IPO銘柄.公開市場
            l_ipoPublicOfferingProductUnit.publicOfferingMarketCode = l_ipoProductParams.getPublicMarket();
            
            //ブックビルディング開始日時：　@IPO銘柄.ブックビルディング開始日時
            l_ipoPublicOfferingProductUnit.bookBuildingStartDate = l_ipoProductParams.getBookbuildingStartDatetime();
            
            //ブックビルディング終了日時：　@IPO銘柄.ブックビルディング終了日時
            l_ipoPublicOfferingProductUnit.bookBuildingEndDate = l_ipoProductParams.getBookbuildingEndDatetime();
            
            //公開価格：　@IPO銘柄.公開価格
            if (l_ipoProductParams.getPublicPriceIsNull())
            {
                l_ipoPublicOfferingProductUnit.publicOfferingPrice = null;
            }
            else
            {
                l_ipoPublicOfferingProductUnit.publicOfferingPrice = WEB3StringTypeUtility.formatNumber(l_ipoProductParams.getPublicPrice());
            }
            
            //公開価格ディスカウント率：　@IPO銘柄.公開価格（ディスカウント率）
            if (l_ipoProductParams.getPublicPriceDiscountRateIsNull())
            {
                l_ipoPublicOfferingProductUnit.publicOfferingDiscountRate = null;
            }
            else
            {
                l_ipoPublicOfferingProductUnit.publicOfferingDiscountRate = WEB3StringTypeUtility.formatNumber(l_ipoProductParams.getPublicPriceDiscountRate());
            }            
            
            //公開価格決定日：　@IPO銘柄.公開価格決定日
            l_ipoPublicOfferingProductUnit.publicOfferingPriceDetermDate = l_ipoProductParams.getPublicPriceSettleDate();
            
            //未定決定区分：　@
            //（IPO銘柄.isスケジュール決定() == true）の場合、”決定”
            //（IPO銘柄.isスケジュール決定() == false）の場合、”未定”
            if (l_blnScheduleDecision)
            {
                log.debug("isスケジュール決定() == true");
                l_ipoPublicOfferingProductUnit.undecideDecideDiv = WEB3UndecideDecideDivDef.SCHEDULE_DECIDED;
            }
            else if (!l_blnScheduleDecision)
            {
                log.debug("isスケジュール決定() == false");
                l_ipoPublicOfferingProductUnit.undecideDecideDiv = WEB3UndecideDecideDivDef.SCHEDULE_UNDECIDED;
            }
            
            //抽選日：　@抽選日（：IPO期間指定）
            l_ipoPublicOfferingProductUnit.lotDate = l_ipoTermUnit;
            
            //購入申込期間（当社指定）：　@購入申込期間（当社指定）（：IPO期間指定）
            l_ipoPublicOfferingProductUnit.appointOfferTerm = l_ipoTermUnitOfferDate;
            
            //公開日：　@公開日（：IPO期間指定）
            l_ipoPublicOfferingProductUnit.publicOfferingDate = l_ipoTermUnitPublicDate;
            
            //IPOスケジュール：　@IPO銘柄.getIPOスケジュール()の戻り値
            l_ipoPublicOfferingProductUnit.ipoScheduleDiv = l_strIpoSchedule;
            
            //IPO停止：　@IPO銘柄.IPO停止
            l_ipoPublicOfferingProductUnit.ipoStopDiv = l_ipoProductParams.getIpoStop();
            
            //2.11add(管理者IPO公開銘柄明細 : Object)
            l_arrayList.add(l_ipoPublicOfferingProductUnit);           
        }
        //3.toArray
        WEB3AdminIPOPublicOfferingProductUnit[] l_WEB3AdminIpoPublicOfferingProductUnit;
        l_WEB3AdminIpoPublicOfferingProductUnit = new WEB3AdminIPOPublicOfferingProductUnit[l_arrayList.size()];
        l_arrayList.toArray(l_WEB3AdminIpoPublicOfferingProductUnit);
        
        log.exiting(STR_METHOD_NAME);
        return l_WEB3AdminIpoPublicOfferingProductUnit;
    }
}
@
