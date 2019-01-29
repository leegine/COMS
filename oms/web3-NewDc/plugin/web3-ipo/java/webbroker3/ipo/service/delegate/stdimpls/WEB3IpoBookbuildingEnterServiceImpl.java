head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.05.45.54;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6f44d7efa104045;
filename	WEB3IpoBookbuildingEnterServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : IPOブックビルディング参加サービス実装クラス(WEB3IpoBookbuildingEnterServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/08/10 鄭海良(中訊) 新規作成
*/

package webbroker3.ipo.service.delegate.stdimpls;

import java.util.ArrayList;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Institution;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderStatusEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3IpoRegistDivDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.ipo.WEB3IpoClientRequestService;
import webbroker3.ipo.WEB3IpoOrderImpl;
import webbroker3.ipo.WEB3IpoOrderManagerImpl;
import webbroker3.ipo.WEB3IpoProductImpl;
import webbroker3.ipo.WEB3IpoProductManagerImpl;
import webbroker3.ipo.data.IpoProductRow;
import webbroker3.ipo.define.WEB3UndecideDecideDivDef;
import webbroker3.ipo.message.WEB3IPOBookBuildingEnterRequest;
import webbroker3.ipo.message.WEB3IPOBookBuildingEnterResponse;
import webbroker3.ipo.message.WEB3IPOProductInfo;
import webbroker3.ipo.message.WEB3IPOProductInfoRequest;
import webbroker3.ipo.message.WEB3IPOProductInfoResponse;
import webbroker3.ipo.message.WEB3IPOPublicOfferingProductUnit;
import webbroker3.ipo.message.WEB3IPOTermUnit;
import webbroker3.ipo.service.delegate.WEB3IpoBookbuildingEnterService;
import webbroker3.ipo.service.delegate.WEB3IpoProductInfoService;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * IPOブックビルディング参加サービス実装クラス
 * 
 * @@author 鄭海良
 * @@version 1.0*
 */
public class WEB3IpoBookbuildingEnterServiceImpl 
    extends WEB3IpoClientRequestService implements WEB3IpoBookbuildingEnterService 
{
    
    /**
     * ログ出力ユーティリティ。
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3IpoBookbuildingEnterServiceImpl.class);    
    /**
     * @@roseuid 4112F18F0149
     */
    public WEB3IpoBookbuildingEnterServiceImpl() 
    {
     
    }
    
    /**
     * IPOブックビルディング参加処理を実施する。<BR>
     * <BR>
     * １）　@リクエストデータの型により、以下の通りメソッドをコールする。<BR>
     * <BR>
     * ○ 引数のリクエストデータが、IPOブックビルディング参加リクエストの場合<BR>
     * 　@－get銘柄一覧()をコールする。<BR>
     * ○ 引数のリクエストデータが、IPO個別銘柄情報リクエストの場合<BR>
     * 　@－get銘柄詳細()をコールする。<BR>
     * @@param l_request - リクエスト
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 40D28008020A
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " execute(WEB3GenRequest)";
        log.entering(STR_METHOD_NAME );

        WEB3GenResponse l_response = null;
        if(l_request instanceof WEB3IPOBookBuildingEnterRequest)
        {
            //IPOブックビルディング参加
            l_response = getProductList(
                (WEB3IPOBookBuildingEnterRequest)l_request);
        }
        else if(l_request instanceof WEB3IPOProductInfoRequest)
        {
            //IPO個別銘柄情報
            l_response = getDetailedProduct(
                (WEB3IPOProductInfoRequest)l_request);
        }
        else
        {
            String l_strErrorMessage = 
                "パラメータの類型が不正、該当するWEB3IPOBookBuildingEnterRequest," + 
                "WEB3IPOProductInfoRequest類型。";
            log.error(l_strErrorMessage);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80002, 
                this.getClass().getName() + STR_METHOD_NAME,
                l_strErrorMessage);            
        }

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * (get銘柄一覧)<BR>
     * IPO銘柄一覧取得処理を行う。<BR>
     * （ブックビルディング参加画面に表示）<BR>
     * <BR>
     * シーケンス図<BR>
     * 「IPO（ﾌﾞｯｸﾋﾞﾙﾃﾞｨﾝｸﾞ参加）get銘柄一覧」参照。
     * @@param l_request - IPOブックビルディング参加リクエストデータオブジェクト
     * @@return webbroker3.ipo.message.WEB3IpoBookbuildingEnterResponse
     * @@roseuid 40D2801201DB
     */
    protected WEB3IPOBookBuildingEnterResponse getProductList(
        WEB3IPOBookBuildingEnterRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " getProductList(WEB3IpoBookbuildingEnterRequest)";
        log.entering(STR_METHOD_NAME);

        WEB3IPOBookBuildingEnterResponse l_response = null;

        //1.1 validate注文受付可能
        WEB3GentradeTradingTimeManagement.validateOrderAccept();//WEB3BaseException
        
        //1.2 get補助口座
        SubAccount l_subAccount = this.getSubAccount();//WEB3BaseException
        
        //1.3 getInstitution
        Institution l_institution = l_subAccount.getInstitution();
        
        //1.4 get取扱中有効銘柄
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.IPO);
        WEB3IpoProductManagerImpl l_ipoPorductManager = 
            (WEB3IpoProductManagerImpl)l_tradingModule.getProductManager();
        WEB3IpoProductImpl[] l_ipoProduct = l_ipoPorductManager.getDealtInProcessOpenIpoProducts(
            l_institution.getInstitutionCode(), 
            WEB3IpoRegistDivDef.OPEN_LISTING);//WEB3BaseException

        //1.5 create銘柄明細
        WEB3IPOPublicOfferingProductUnit[] l_ipoPublicOfferingProductUnit1 = null;
        if(l_ipoProduct != null)
        {
            l_ipoPublicOfferingProductUnit1 = this.createProductUnits(l_ipoProduct);   
        }
                 
        
        //1.6 get取扱中有効銘柄
        l_ipoProduct = l_ipoPorductManager.getDealtInProcessOpenIpoProducts(
            l_institution.getInstitutionCode(), 
            WEB3IpoRegistDivDef.LISTED);//WEB3BaseException
        
        //1.7 create銘柄明細
        WEB3IPOPublicOfferingProductUnit[] l_ipoPublicOfferingProductUnit2 = null;
        if(l_ipoProduct != null)
        {
            l_ipoPublicOfferingProductUnit2 = this.createProductUnits(l_ipoProduct);
        }
        
        //1.7.1 メッセージ IPO公開銘柄明細
        
        //1.8 IPOブックビルディング参加レスポンス
        l_response = (WEB3IPOBookBuildingEnterResponse)l_request.createResponse();
        
        //1.9 プロパティセット
        l_response.newListingList = l_ipoPublicOfferingProductUnit1;
        l_response.listingList = l_ipoPublicOfferingProductUnit2;
        
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * (get銘柄詳細)<BR>
     * IPO個別銘柄情報取得処理を行う。<BR>
     * <BR>
     * シーケンス図<BR>
     * 「IPO（ﾌﾞｯｸﾋﾞﾙﾃﾞｨﾝｸﾞ参加）get銘柄詳細」参照。
     * @@param l_request - IPO個別銘柄情報リクエストデータオブジェクト
     * @@return webbroker3.ipo.message.WEB3IpoProductInfoResponse
     * @@roseuid 40D280AA02C6
     */
    protected WEB3IPOProductInfoResponse getDetailedProduct(WEB3IPOProductInfoRequest l_request) 
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " getDetailedProduct(WEB3IpoProductInfoRequest)";
        log.entering(STR_METHOD_NAME);
        
        WEB3IPOProductInfoResponse l_response = null;
        try
        {
            //1.1 validate注文受付可能
            WEB3GentradeTradingTimeManagement.validateOrderAccept();

            //1.2 createIPO銘柄情報
            WEB3IpoProductInfoService l_ipoProductInfoService = 
                (WEB3IpoProductInfoService) Services.getService(WEB3IpoProductInfoService.class);

            long l_lngProductID = Long.parseLong(l_request.id);
            WEB3IPOProductInfo l_ipoProductInfo = 
                l_ipoProductInfoService.createIpoProductInfo(l_lngProductID);

            //1.3 IPO銘柄
            FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
            TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.IPO);
            WEB3IpoProductManagerImpl l_productManager = 
                (WEB3IpoProductManagerImpl)l_tradingModule.getProductManager();
            WEB3IpoProductImpl l_ipoProduct = 
                (WEB3IpoProductImpl)l_productManager.getProduct(l_lngProductID);

            //1.4 isブックビルディング申告可能
            boolean l_blnBookBindingOrderPossible = l_ipoProduct.isBookbuildingOrderPossible();
            
            //1.5 get補助口座
            SubAccount l_subAccount = this.getSubAccount();
            
            //1.6 getIPO申告
            WEB3IpoOrderManagerImpl l_ipoOrderManager = 
                (WEB3IpoOrderManagerImpl)l_tradingModule.getOrderManager();
            WEB3IpoOrderImpl l_ipoOrder = 
                (WEB3IpoOrderImpl)l_ipoOrderManager.getOrderUnit(l_subAccount, l_lngProductID);                     
            
            //1.7 IPO個別銘柄情報レスポンス
            l_response = (WEB3IPOProductInfoResponse)l_request.createResponse();
            
            //1.8 プロパティセット
            boolean l_blnUnDemand = false;
            //申告未済の判定
            if (l_ipoOrder == null) 
            {
                l_blnUnDemand = true;
            } else 
            {
                if (OrderStatusEnum.CANCELLED.equals(l_ipoOrder.getOrderStatus())) 
                {
                    l_blnUnDemand = true;
                }
            }
            
            l_response.demandFlag = l_blnBookBindingOrderPossible && l_blnUnDemand;
            l_response.ipoProductInfo = l_ipoProductInfo;

        }
        catch (NotFoundException l_e)
        {
            log.error(STR_METHOD_NAME,l_e);
            throw new WEB3BaseException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80002, 
                this.getClass().getName() + STR_METHOD_NAME, 
                l_e);
        }
   
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * (create銘柄明細)<BR>
     * 引数のIPO銘柄[]の内容で、IPO公開銘柄明細メッセージデータを作成する。<BR>
     * <BR>
     * シーケンス図<BR>
     * 「IPO（ﾌﾞｯｸﾋﾞﾙﾃﾞｨﾝｸﾞ参加）create銘柄明細」<BR>
     * 参照。
     * @@param l_products - (IPO銘柄リスト)<BR>
     * IPO銘柄オブジェクトの配列
     * @@return webbroker3.ipo.message.WEB3IpoPublicOfferingProductUnit[]
     * @@roseuid 40D2A2F4014F
     */
    protected WEB3IPOPublicOfferingProductUnit[] createProductUnits(WEB3IpoProductImpl[] l_products) 
    {
        final String STR_METHOD_NAME = " createProductUnits(WEB3IpoProductImpl[])";
        log.entering(STR_METHOD_NAME);
        
        if(l_products == null)
        {
            log.debug("create銘柄明細,parameter WEB3IpoProductImpl[] l_products == null.");
            log.exiting(STR_METHOD_NAME);
            return null;
        }
        
        int l_productCount = l_products.length ;
        //1.1 ArrayList
        ArrayList l_arrList = new ArrayList(l_productCount);
        
        //1.2 IPO銘柄 各要素毎のLOOP処理
        IpoProductRow l_ipoProductRow = null;
        WEB3IpoProductImpl l_ipoProductImpl = null;
        for(int i = 0; i < l_productCount; i++)
        {
            l_ipoProductImpl = l_products[i];
            l_ipoProductRow = 
                (IpoProductRow)l_ipoProductImpl.getDataSourceObject();
            
            //1.2.1 isスケジュール決定
            boolean l_scheduleDecision = l_ipoProductImpl.isScheduleDecision();
            
            //1.2.2 getIPOスケジュール
            String l_ipoSchedule = l_ipoProductImpl.getIpoSchedule();            
            
            //1.2.3 IPO期間指定
            WEB3IPOTermUnit l_ipoTermUnit = new WEB3IPOTermUnit();
            
            //1.2.4 公開日インスタンス プロパティセット
            l_ipoTermUnit.startDate = l_ipoProductRow.getPublicOfferingDate(); 
            if(!l_ipoProductRow.getPublicOfferingDateCountIsNull())
            {
                l_ipoTermUnit.bizDateUpper = 
                    WEB3StringTypeUtility.formatNumber(l_ipoProductRow.getPublicOfferingDateCount());
            }
                
            //1.2.5 IPO公開銘柄明細
            WEB3IPOPublicOfferingProductUnit l_ipoPublicOfferintProductUnit =
                new WEB3IPOPublicOfferingProductUnit();
                
            //1.2.6 プロパティセット
            
            //IPO銘柄ＩＤ
            l_ipoPublicOfferintProductUnit.id = WEB3StringTypeUtility.formatNumber(l_ipoProductImpl.getProductId());
            
            //銘柄コード
            l_ipoPublicOfferintProductUnit.productCode = l_ipoProductRow.getProductCode();
            
            //銘柄名
            l_ipoPublicOfferintProductUnit.productName = l_ipoProductImpl.getStandardName();
            
            //公開市場コード
            l_ipoPublicOfferintProductUnit.publicOfferingMarketCode = l_ipoProductImpl.getPublicMarket() ;
            
            //ブックビルディング開始日時
            l_ipoPublicOfferintProductUnit.bookBuildingStartDate = l_ipoProductRow.getBookbuildingStartDatetime();
            
            //ブックビルディング終了日時
            l_ipoPublicOfferintProductUnit.bookBuildingEndDate = l_ipoProductRow.getBookbuildingEndDatetime();
            
            //仮条件区分
            l_ipoPublicOfferintProductUnit.temporaryConditionDiv = l_ipoProductRow.getProvisionalValueDiv();
            
            //仮条件下限値
            if(!l_ipoProductRow.getProvisionalMinValueIsNull())
            {
                l_ipoPublicOfferintProductUnit.temporaryConditionLower = 
                    WEB3StringTypeUtility.formatNumber(l_ipoProductRow.getProvisionalMinValue());
            }
            
            //仮条件上限値
            if(!l_ipoProductRow.getProvisionalMaxValueIsNull())
            {
                l_ipoPublicOfferintProductUnit.temporaryConditionUpper = 
                    WEB3StringTypeUtility.formatNumber(l_ipoProductRow.getProvisionalMaxValue());
            }
            
            //仮条件提示日
            l_ipoPublicOfferintProductUnit.temporaryConditionPresentationDate = l_ipoProductRow.getProvisionalValueOpenDate();
            
            //公開価格
            if(!l_ipoProductRow.getPublicPriceIsNull())
            {
                l_ipoPublicOfferintProductUnit.publicOfferingPrice = 
                    WEB3StringTypeUtility.formatNumber(l_ipoProductRow.getPublicPrice());
            }
            
            //公開価格ディスカウント率
            if(!l_ipoProductRow.getPublicPriceDiscountRateIsNull())
            {
                l_ipoPublicOfferintProductUnit.publicOfferingDiscountRate = 
                    WEB3StringTypeUtility.formatNumber(l_ipoProductRow.getPublicPriceDiscountRate());
            }
            
            //公開価格決定日
            l_ipoPublicOfferintProductUnit.publicOfferingPriceDetermDate = l_ipoProductRow.getPublicPriceSettleDate();
            
            //未定決定区分
            if(l_scheduleDecision)
            {
                l_ipoPublicOfferintProductUnit.undecideDecideDiv = 
                    WEB3UndecideDecideDivDef.SCHEDULE_DECIDED;
            }
            else
            {
                l_ipoPublicOfferintProductUnit.undecideDecideDiv = 
                    WEB3UndecideDecideDivDef.SCHEDULE_UNDECIDED;
            }
            
            //公開日
            l_ipoPublicOfferintProductUnit.publicOfferingDate = 
                l_ipoTermUnit;
            
            //ipoスケジュール
            l_ipoPublicOfferintProductUnit.ipoScheduleDiv = l_ipoSchedule;
            
            //IPO停止
            l_ipoPublicOfferintProductUnit.ipoStopDiv = l_ipoProductRow.getIpoStop();
            
            //1.2.7 add
            l_arrList.add(l_ipoPublicOfferintProductUnit);
        }
        
        //1.3 toArray
        WEB3IPOPublicOfferingProductUnit[] l_offeringProducts =
            new WEB3IPOPublicOfferingProductUnit[l_arrList.size()];
            
        l_arrList.toArray(l_offeringProducts);
        
        log.exiting(STR_METHOD_NAME);
        return l_offeringProducts;
    }
}
@
