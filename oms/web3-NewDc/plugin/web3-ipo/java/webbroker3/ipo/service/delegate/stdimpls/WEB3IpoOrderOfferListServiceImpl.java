head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.05.45.46;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6f44d7efa104045;
filename	WEB3IpoOrderOfferListServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : IPO申告・購入申込一覧サービス実装クラス(WEB3IpoOrderOfferListServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/08/10 劉江涛(中訊) 新規作成
Revesion History : 2005/01/05 坂上(SRA) 残対応>>>048
*/
package webbroker3.ipo.service.delegate.stdimpls;

import java.util.ArrayList;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3EnableIpoQuantityChangeDef;
import webbroker3.common.define.WEB3IpoRegistDivDef;
import webbroker3.common.define.WEB3OrderPriceDivDef;
import webbroker3.common.define.WEB3TaxTypeSpecialDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.ipo.WEB3IpoClientRequestService;
import webbroker3.ipo.WEB3IpoOrderImpl;
import webbroker3.ipo.WEB3IpoOrderManagerImpl;
import webbroker3.ipo.WEB3IpoProductImpl;
import webbroker3.ipo.data.IpoOrderRow;
import webbroker3.ipo.data.IpoProductRow;
import webbroker3.ipo.define.WEB3ControlPossibleCodeListDef;
import webbroker3.ipo.define.WEB3UndecideDecideDivDef;
import webbroker3.ipo.message.WEB3IPOBookBuildingHistoryRequest;
import webbroker3.ipo.message.WEB3IPOBookBuildingHistoryResponse;
import webbroker3.ipo.message.WEB3IPODemandHistoryUnit;
import webbroker3.ipo.message.WEB3IPODemandOfferProductUnit;
import webbroker3.ipo.message.WEB3IPODemandOfferRequest;
import webbroker3.ipo.message.WEB3IPODemandOfferResponse;
import webbroker3.ipo.message.WEB3IPOTermUnit;
import webbroker3.ipo.service.delegate.WEB3IpoOrderActionUnitService;
import webbroker3.ipo.service.delegate.WEB3IpoOrderOfferListService;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Institution;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderStatusEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TaxTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.InstitutionRow;

/**
 * IPO申告・購入申込一覧サービス実装クラス
 * @@author 劉江涛(中訊)
 * @@version 1.0
 */
public class WEB3IpoOrderOfferListServiceImpl extends WEB3IpoClientRequestService implements WEB3IpoOrderOfferListService 
{
    
    /**
     * ログ出力ユーティリティ。
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3IpoOrderOfferListServiceImpl.class); 
    /**
     * @@roseuid 4112F1920126
     */
    public WEB3IpoOrderOfferListServiceImpl() 
    {
     
    }
    
    /**
     * IPO申告・購入申込一覧処理を実施する。<BR>
     * <BR>
     * １）　@リクエストデータの型により、以下の通りメソッドをコールする。<BR>
     * <BR>
     * ○ 引数のリクエストデータが、IPO申告購入申込リクエストの場合<BR>
     * 　@－get申告購入申込一覧()をコールする。<BR>
     * ○ 引数のリクエストデータが、IPO申告履歴リクエストの場合<BR>
     * 　@－get申告履歴一覧()をコールする。<BR>
     * @@param l_request - リクエスト
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 40DA5E500311
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException 
    {
        
        final String STR_METHOD_NAME = " execute(WEB3GenRequest)";
        log.entering(STR_METHOD_NAME );
        
        WEB3GenResponse l_response = null;
        if(l_request instanceof WEB3IPODemandOfferRequest)
        {
            l_response = (WEB3GenResponse)this.getOrderOfferList((WEB3IPODemandOfferRequest)l_request);
        }
        else if(l_request instanceof WEB3IPOBookBuildingHistoryRequest)
        {           
            l_response = (WEB3GenResponse)this.getOrderActionList((WEB3IPOBookBuildingHistoryRequest)l_request);
        }
        else
        {
            String l_strErrorMessage = 
                "パラメータの類型が不正、該当するWEB3IPOBookBuildingEnterRequest," + 
                "WEB3IPOProductInfoRequest類型。";
            log.error(l_strErrorMessage);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80002, 
                this.getClass().getName() + STR_METHOD_NAME,
                l_strErrorMessage);            
        }

        log.exiting(STR_METHOD_NAME);
        
        return l_response;
    }
    
    /**
     * (get申告購入申込一覧)<BR>
     * IPO申告・購入申込一覧一覧取得処理を行う。<BR>
     * <BR>
     * シーケンス図<BR>
     * 「IPO（申告・購入申込一覧）get申告購入申込一覧」参照。<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * IPO申告購入申込リクエストデータオブジェクト
     * @@return webbroker3.ipo.message.WEB3IpoOrderOfferResponse
     * @@roseuid 40DA5E500340
     */
    protected WEB3IPODemandOfferResponse getOrderOfferList(WEB3IPODemandOfferRequest l_request)
        throws WEB3BaseException 
    {

       
        //validate注文受付可能
        WEB3GentradeTradingTimeManagement.validateOrderAccept();
        
        //get補助口座()
        SubAccount l_subAccount = this.getSubAccount();
        
        //(新規上場) getIPO申告
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        
        WEB3IpoOrderManagerImpl l_orderManagerImpl = 
                             (WEB3IpoOrderManagerImpl)l_finApp.getTradingModule(ProductTypeEnum.IPO).getOrderManager();
                             
        WEB3IpoOrderImpl[] l_newOrders = l_orderManagerImpl.getOrderUnits(l_subAccount,WEB3IpoRegistDivDef.OPEN_LISTING);
        WEB3IPODemandOfferProductUnit[] l_newOrderOfferProductUnits = this.createOrderOfferUnits(l_newOrders);
        
        //既上場一覧
        WEB3IpoOrderImpl[] l_oldOrders = l_orderManagerImpl.getOrderUnits(l_subAccount,WEB3IpoRegistDivDef.LISTED);
        WEB3IPODemandOfferProductUnit[] l_oldOrderOfferProductUnits = this.createOrderOfferUnits(l_oldOrders);
        //IPO購入申込入力レスポンス(WEB3GenRequest)              
        WEB3IPODemandOfferResponse l_orderOfferResponse = (WEB3IPODemandOfferResponse) l_request.createResponse();
        
        l_orderOfferResponse.newListingList = l_newOrderOfferProductUnits;
        l_orderOfferResponse.listingList =l_oldOrderOfferProductUnits;
               
        return l_orderOfferResponse;
    }
    
    /**
     * (get申告履歴一覧)<BR>
     * IPO申告履歴一覧取得処理を行う。<BR>
     * <BR>
     * シーケンス図<BR>
     * 「IPO（申告・購入申込一覧）get申告履歴一覧」参照。<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * IPOブックビルディング申告履歴リクエストデータオブジェクト
     * @@return webbroker3.ipo.message.WEB3IpoBookbuildingOrderActionResponse
     * @@roseuid 40DA5E50036F
     */
    protected WEB3IPOBookBuildingHistoryResponse getOrderActionList(WEB3IPOBookBuildingHistoryRequest l_request) 
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getOrderHistoryList(WEB3IpoBookbuildingOrderActionRequest)";
        log.entering(STR_METHOD_NAME);
        
        //validate注文受付可能
        WEB3GentradeTradingTimeManagement.validateOrderAccept();
        
        //IPO申告::IPO申告
        long l_lngOrderId = Long.parseLong(l_request.id);
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
 
        WEB3IpoOrderImpl l_ipoOrder;
        try 
        {
            l_ipoOrder =
                (WEB3IpoOrderImpl) l_finApp
                    .getTradingModule(ProductTypeEnum.IPO)
                    .getOrderManager()
                    .getOrderUnit(l_lngOrderId);
                    
            //IPO申告::getIPO銘柄
            WEB3IpoProductImpl l_product;
            l_product = (WEB3IpoProductImpl) l_ipoOrder.getProduct();
        
            //IPO申告::get補助口座ID
            long l_lngSubAccountId = l_ipoOrder.getSubAccountId();
        
            //IPO申告履歴明細作成サービスImpl::createIPO申告履歴明細
            
            WEB3IpoOrderActionUnitService l_service = (WEB3IpoOrderActionUnitService)Services.getService(WEB3IpoOrderActionUnitService.class);
                    
            WEB3IPODemandHistoryUnit[] l_orderActionUnits = l_service.createOrderActionUnit(l_ipoOrder);        
            //IPOブックビルディング申告履歴レスポンス::IPOブックビルディング申告履歴レスポンス
            WEB3IPOBookBuildingHistoryResponse l_bookbuildingHistoryResponse = (WEB3IPOBookBuildingHistoryResponse) l_request.createResponse();
        
            //メッセージ (*2) プロパティセット
            l_bookbuildingHistoryResponse.productCode = ((IpoProductRow)l_product.getDataSourceObject()).getProductCode();
            l_bookbuildingHistoryResponse.productName = l_product.getStandardName();
            l_bookbuildingHistoryResponse.bookBuildingHistoryList =l_orderActionUnits ; 
            
            log.exiting(STR_METHOD_NAME);  
            return l_bookbuildingHistoryResponse;
                    
        } 
        catch (NotFoundException l_ex) 
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(WEB3ErrorCatalog.SYSTEM_ERROR_80005,STR_METHOD_NAME);
        }
        
    }
    
    /**
     * (create申告購入申込明細)<BR>
     * 引数のIPO申告[]の内容で、IPO申告購入申込銘柄明細メッセージデータを作成する。<BR>
     * <BR>
     * シーケンス図<BR>
     * 「IPO（申告・購入申込一覧）create申告購入申込明細」<BR>
     * 参照。<BR>
     * @@param l_ipoOrders - IPO申告オブジェクトの配列
     * @@return webbroker3.ipo.message.WEB3IpoOrderOfferProductUnit[]
     * @@roseuid 40DC00C10379
     */
    protected WEB3IPODemandOfferProductUnit[] createOrderOfferUnits(WEB3IpoOrderImpl[] l_ipoOrders) throws WEB3BaseException 
    {
        
        //メッセージ ArrayList( )
        ArrayList l_orderOfferProductList = new ArrayList();
        
        //メッセージ (*1) IPO申告　@各要素毎のLOOP
        
        int l_intLength = l_ipoOrders.length;
        for (int i = 0; i < l_intLength; i++)
        {
            //IPO申告::getIPO銘柄
            WEB3IpoProductImpl l_product;
            l_product = (WEB3IpoProductImpl) l_ipoOrders[i].getProduct();
        
            //メッセージ (*2) 購入申込期間（当社指定）インスタンス プロパティセット
            WEB3IPOTermUnit l_termUnit = new WEB3IPOTermUnit();
            
            IpoProductRow l_ipoProductRow = (IpoProductRow)l_product.getDataSourceObject();
            
            l_termUnit.startDate =l_ipoProductRow.getOfferStartDatetime();
            l_termUnit.endDate = l_ipoProductRow.getOfferEndDatetime();

            if(!l_ipoProductRow.getOfferStartDateCountIsNull())
            {
                l_termUnit.bizDateUpper = WEB3StringTypeUtility.formatNumber(l_ipoProductRow.getOfferStartDateCount());
            } 
            
            if(!l_ipoProductRow.getOfferEndDateCountIsNull())
            {
                l_termUnit.bizDateLower = WEB3StringTypeUtility.formatNumber(l_ipoProductRow.getOfferEndDateCount());
            } 
        
            //IPO銘柄::getIPOスケジュール
            String l_getIpoSchedule = l_product.getIpoSchedule();
        
            //IPO申告::get申告申込状況
            String l_strOrderOfferState = l_ipoOrders[i].getOrderOfferState();
        
            //メッセージ ArrayList( )
            ArrayList l_arrayList = new ArrayList();
            
            //IPO銘柄::isブックビルディング開始
            boolean l_blnStart = l_product.isBookbuildingStart();
        
            //IPO銘柄::isブックビルディング終了
            boolean l_blnEnd = l_product.isBookbuildingEnd();
            
            IpoOrderRow l_IpoOrderRow = (IpoOrderRow)l_ipoOrders[i].getDataSourceObject();
            
            if(l_blnStart && !l_blnEnd)
            {
                if( OrderStatusEnum.CANCELLED.equals(l_IpoOrderRow.getOrderStatus()) )
                {
                    l_arrayList.add(WEB3ControlPossibleCodeListDef.DAMAND);
                }
                else
                {
                    l_arrayList.add(WEB3ControlPossibleCodeListDef.CHANGE_CANCEL);
                }
            }
            
            //IPO銘柄::is新規抽選終了
            boolean l_blnLotteryEnd = l_product.isNewLotteryEnd();            
            
            //IPO申告::is購入申込
            boolean l_blnOffered = l_ipoOrders[i].isOffered();
            
            //IPO申告::is辞退
            boolean l_blnDecline = l_ipoOrders[i].isDecline();
            
            //IPO申告::is購入申込・辞退可能
            boolean l_blnOfferDeclinePossible = l_ipoOrders[i].isOfferDeclinePossible();
            
            if (!l_blnOffered && !l_blnDecline && l_blnOfferDeclinePossible)
            {
                if(l_blnLotteryEnd && !l_product.isOfferEnd())
                {
                    l_arrayList.add(WEB3ControlPossibleCodeListDef.DECLINE);
                }
            
                //(*5) 新規抽選終了後で、購入申込期間中の場合
                if(l_product.isNewLotteryEnd() && l_product.isOfferStart() && !l_product.isOfferEnd()) 
                {
                    l_arrayList.add(WEB3ControlPossibleCodeListDef.PURCHASE_APPLICATION);
                }
            }

            //is取扱中( )
            boolean l_blnDealtInProcess = l_product.isDealtInProcess();
            
            //(*) IPO銘柄が無効の場合（is取扱中 == false） 
            if (!l_blnDealtInProcess)
            {
                l_arrayList.clear();                     
            }
            
            //toArray( )
            Object[] l_controlPossibleCodeList = l_arrayList.toArray();
            
            //IPO申告購入申込銘柄明細::IPO申告購入申込銘柄明細
            WEB3IPODemandOfferProductUnit l_orderOfferProductUnit = new WEB3IPODemandOfferProductUnit();
           
            //ＩＤ
            l_orderOfferProductUnit.id =Long.toString(l_ipoOrders[i].getOrderId());
            
            //IPO銘柄ID
            l_orderOfferProductUnit.ipoProductId = Long.toString(l_ipoProductRow.getIpoProductId());
            
            //銘柄コード
            l_orderOfferProductUnit.productCode = l_ipoProductRow.getProductCode();
            
            //銘柄名
            l_orderOfferProductUnit.productName = l_product.getStandardName();
            
            //申告数量
            double l_dblQuantity = l_ipoOrders[i].getQuantity();
            l_orderOfferProductUnit.demandQuantity = WEB3StringTypeUtility.formatNumber(l_dblQuantity);
            
            //申告価格区分            
            if (l_ipoOrders[i].getLimitPrice() == 0)
            {
                l_orderOfferProductUnit.demandPriceDiv = WEB3OrderPriceDivDef.MARKET_PRICE;
            }
            else
            {
                l_orderOfferProductUnit.demandPriceDiv = WEB3OrderPriceDivDef.LIMIT_PRICE;
            }
            
            //申告価格
            double l_dblLimitPriceQuantity = l_ipoOrders[i].getLimitPrice();
            l_orderOfferProductUnit.demandPrice = WEB3StringTypeUtility.formatNumber(l_dblLimitPriceQuantity);
            
            //公開価格
            if(l_ipoProductRow.getPublicPriceIsNull())
            {
                l_orderOfferProductUnit.publicOfferingPrice = null;
            }
            else
            {
                l_orderOfferProductUnit.publicOfferingPrice = 
                    WEB3StringTypeUtility.formatNumber(l_ipoProductRow.getPublicPrice());
            }
            
            //公開価格ディスカウント率
            if(l_ipoProductRow.getPublicPriceDiscountRateIsNull())
            {
                l_orderOfferProductUnit.publicOfferingDiscountRate = null;
            }
            else
            {
                l_orderOfferProductUnit.publicOfferingDiscountRate = 
                    WEB3StringTypeUtility.formatNumber(l_ipoProductRow.getPublicPriceDiscountRate());
            }
            
            //公開価格決定日
            l_orderOfferProductUnit.publicOfferingPriceDetermDate = new WEB3IPOTermUnit();
            l_orderOfferProductUnit.publicOfferingPriceDetermDate.startDate = l_ipoProductRow.getPublicPriceSettleDate();
            
            //当選数量
            if(l_IpoOrderRow.getElectedQuantityIsNull())
            {
                l_orderOfferProductUnit.prizeQuantity  = null;
            }
            else
            {
                l_orderOfferProductUnit.prizeQuantity  = WEB3StringTypeUtility.formatNumber(l_IpoOrderRow.getElectedQuantity()); 
            }
            
            //購入申込数量
            if(l_IpoOrderRow.getApplicationQuantityIsNull())
            {
                l_orderOfferProductUnit.offerQuantity = null;
            }
            else
            {
                l_orderOfferProductUnit.offerQuantity =
                    WEB3StringTypeUtility.formatNumber(l_IpoOrderRow.getApplicationQuantity());
            }
            
            //申込辞退日時
            l_orderOfferProductUnit.offerCancelDate = l_IpoOrderRow.getOfferingTimestamp();
            
            //申告相当額 
            if(l_IpoOrderRow.getBookbuildingPriceIsNull())
            {
                l_orderOfferProductUnit.demandEquivalentPrice = null;
            }
            else
            {
                l_orderOfferProductUnit.demandEquivalentPrice = 
                    WEB3StringTypeUtility.formatNumber(l_IpoOrderRow.getBookbuildingPrice());
            }
            
            //購入申込代金
            if(l_IpoOrderRow.getPayAmountIsNull())
            {
                l_orderOfferProductUnit.offerPrice = null;
            }
            else
            {
                l_orderOfferProductUnit.offerPrice =
                    WEB3StringTypeUtility.formatNumber(l_IpoOrderRow.getPayAmount());
            }
            
            //ブックビルディング開始日時
            l_orderOfferProductUnit.bookBuildingStartDate = l_ipoProductRow.getBookbuildingStartDatetime();
            
            //ブックビルディング終了日時
            l_orderOfferProductUnit.bookBuildingEndDate = l_ipoProductRow.getBookbuildingEndDatetime();
            
            //未定決定区分
            if(l_product.isScheduleDecision())
            {
                l_orderOfferProductUnit.undecideDecideDiv = WEB3UndecideDecideDivDef.SCHEDULE_DECIDED;
            }
            else if( !(l_product.isScheduleDecision()) )
            {
                l_orderOfferProductUnit.undecideDecideDiv = WEB3UndecideDecideDivDef.SCHEDULE_UNDECIDED;
            }

            //購入申込期間（当社指定）
            l_orderOfferProductUnit.appointOfferTerm = l_termUnit;
            
            //IPOスケジュール
            l_orderOfferProductUnit.ipoScheduleDiv = l_getIpoSchedule;
            
            //申告申込状況区分
            l_orderOfferProductUnit.demandOfferStateDiv = l_strOrderOfferState;
            
            //口座区分
            //TaxTypeEnum
            if( TaxTypeEnum.NORMAL.equals(l_ipoOrders[i].getTaxType()))
            {
                l_orderOfferProductUnit.taxType = WEB3TaxTypeSpecialDef.NORMAL;
            }
            else if( TaxTypeEnum.SPECIAL.equals(l_ipoOrders[i].getTaxType()) 
                || TaxTypeEnum.SPECIAL_WITHHOLD.equals(l_ipoOrders[i].getTaxType()))
            {
                l_orderOfferProductUnit.taxType = WEB3TaxTypeSpecialDef.SPECIAL;
            }
            
            //操作可能コード一覧
            int l_intSize = l_controlPossibleCodeList.length;
            l_orderOfferProductUnit.controlPossibleCodeList = new String[l_intSize];
            for(int j = 0; j < l_intSize; j++)
            {
                l_orderOfferProductUnit.controlPossibleCodeList[j] = (String)l_controlPossibleCodeList[j];
            }
            
            //表示用単位区分
            l_orderOfferProductUnit.displayUnitDiv = l_ipoProductRow.getIpoUnitDiv();  
            
            //1.12.18.get証券会社()
            Institution l_instInstitution = l_product.getInstitution(); 
            InstitutionRow l_InstitutionRow = (InstitutionRow)l_instInstitution.getDataSourceObject(); 
            //購入申込数量変更可能フラグ
            if(l_InstitutionRow.getEnableIpoQuantityChange().equals(WEB3EnableIpoQuantityChangeDef.CAN_CHANGE))
            {
                l_orderOfferProductUnit.offerQuantityFlag = true ; 
            }
            else
            {
                l_orderOfferProductUnit.offerQuantityFlag = false ; 
            }
                           
            //仮条件区分
            l_orderOfferProductUnit.temporaryConditionDiv = l_ipoProductRow.getProvisionalValueDiv();
            
            //IPO停止
            l_orderOfferProductUnit.ipoStopDiv = l_ipoProductRow.getIpoStop();
            
            //add(IPO申告購入申込銘柄明細 : Object)
            l_orderOfferProductList.add(l_orderOfferProductUnit);            
        }
        
        WEB3IPODemandOfferProductUnit[] l_orderOfferProductUnit = new WEB3IPODemandOfferProductUnit[l_orderOfferProductList.size()];
        l_orderOfferProductList.toArray(l_orderOfferProductUnit);
        
        return l_orderOfferProductUnit;
    }
}
@
