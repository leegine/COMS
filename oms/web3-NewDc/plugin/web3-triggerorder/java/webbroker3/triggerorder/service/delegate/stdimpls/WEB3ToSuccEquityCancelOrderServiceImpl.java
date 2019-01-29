head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.50.49;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8704d7ec30c0c9d;
filename	WEB3ToSuccEquityCancelOrderServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : （連続）株式注文取消サービスImpl(WEB3ToSuccEquityCancelOrderServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/11/7  魏馨(中訊) 新規作成
Revesion History : 2006/08/30 柴雙紅(中訊) 仕様変更モデル165
Revesion History : 2007/02/09 趙林鵬(中訊) モデル No.230
*/

package webbroker3.triggerorder.service.delegate.stdimpls;

import java.sql.Timestamp;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeTradedProduct;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeProductRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradedProduct;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3MarginTradingDivDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.equity.WEB3EquityClientRequestService;
import webbroker3.equity.WEB3EquityDataAdapter;
import webbroker3.equity.WEB3EquityProduct;
import webbroker3.equity.WEB3EquityProductManager;
import webbroker3.equity.WEB3EquityProductQuote;
import webbroker3.gentrade.WEB3GentradeMarket;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.triggerorder.WEB3ToSuccEqTypeOrderUnitImpl;
import webbroker3.triggerorder.WEB3ToSuccOrderManagerImpl;
import webbroker3.triggerorder.base.data.RsvEqOrderUnitRow;
import webbroker3.triggerorder.message.WEB3SuccEquityCancelCompleteRequest;
import webbroker3.triggerorder.message.WEB3SuccEquityCancelCompleteResponse;
import webbroker3.triggerorder.message.WEB3SuccEquityCancelConfirmRequest;
import webbroker3.triggerorder.message.WEB3SuccEquityCancelConfirmResponse;
import webbroker3.triggerorder.service.delegate.WEB3ToSuccEquityCancelOrderService;
import webbroker3.triggerorder.util.WEB3TriggerOrderTradingModel;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;


/**
 * (（連続）株式注文取消サービスImpl)<BR>
 * （連続）株式注文取消サービス実装クラス。<BR>
 * 
 * @@ author 魏馨 <BR>
 * @@ version 1.0 <BR>
 */
public class WEB3ToSuccEquityCancelOrderServiceImpl extends WEB3EquityClientRequestService implements WEB3ToSuccEquityCancelOrderService 
{

    /**
     * (ログ出力ユーティリティ)。<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3ToSuccEquityCancelOrderServiceImpl.class); 
    
    /**
     * @@roseuid 436ACF7102EE
     */
    public WEB3ToSuccEquityCancelOrderServiceImpl() 
    {
     
    }
    
    /**
     * （連続）株式注文取消処理を実行する。<BR>
     * <BR>
     * リクエストデータの型により、validate注文取消()またはsubmit注文取消()<BR>
     * メソッドをコールする。<BR>
     * @@param l_request - リクエストデータ。<BR>
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 433A06D60358
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws  WEB3BaseException 
    {
        final String STR_METHOD_NAME = " execute(WEB3GenRequest)";
        log.entering(STR_METHOD_NAME);
        
        WEB3GenResponse l_response = null;
        
        if (l_request instanceof WEB3SuccEquityCancelConfirmRequest)
        {
            l_response = this.validateCancelOrder((WEB3SuccEquityCancelConfirmRequest) l_request);
        }
        else if (l_request instanceof WEB3SuccEquityCancelCompleteRequest)
        {
            l_response = this.submitCancelOrder((WEB3SuccEquityCancelCompleteRequest) l_request);
        }
        else
        {
            log.debug(STR_METHOD_NAME + "パラメータタイプ不正。");
            log.exiting(STR_METHOD_NAME);

            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80018, 
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        log.exiting(STR_METHOD_NAME);

        return l_response;

    }
    
    /**
     * (validate注文取消)<BR>
     * （連続）現物株式注文取消確認処理を実施する。<BR>
     * <BR>
     * シーケンス図「（（連続）株式注文取消サービス）validate注文取消」参照。<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * リクエストデータ。<BR>
     * 
     * @@return WEB3SuccEquityCancelConfirmResponse
     * @@throws WEB3BaseException 
     * @@roseuid 4340BE1002B4
     */
    protected WEB3SuccEquityCancelConfirmResponse 
        validateCancelOrder(WEB3SuccEquityCancelConfirmRequest l_request)
        throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " validateCancelOrder(WEB3SuccEquityCancelConfirmRequest)";
        log.entering(STR_METHOD_NAME);
        
        if (l_request == null)
        {   
            log.debug("parameter is null");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017, 
                this.getClass().getName() + "." + STR_METHOD_NAME);                
        }

        // 1.1 validate()
        l_request.validate();
        
        // 1.2 get補助口座( )
        WEB3GentradeSubAccount l_subAccount = this.getSubAccount();
        
        // 1.3 get株式予約注文単位(long)
        WEB3ToSuccOrderManagerImpl l_managerImpl =
            (WEB3ToSuccOrderManagerImpl)WEB3TriggerOrderTradingModel.getOrderManager();

        WEB3ToSuccEqTypeOrderUnitImpl l_orderUnitImpl = null;
        try
        {
            l_orderUnitImpl = l_managerImpl.getReserveEqtypeOrderUnit(Long.parseLong(l_request.id));
        }
        catch (NotFoundException l_nfe)
        {
            log.error(WEB3ErrorCatalog.SYSTEM_ERROR_80005.getErrorMessage());
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
               WEB3ErrorCatalog.SYSTEM_ERROR_80005,
               this.getClass().getName() + "." + STR_METHOD_NAME,
               l_nfe.getMessage(),
               l_nfe);
        }                                    
        
        // 1.4 validate現物注文取消(補助口座, 株式予約注文単位Impl)
        l_managerImpl.validateEqtypeCancelOrder(l_subAccount, l_orderUnitImpl);
        
        // 1.5 get取引銘柄( )
        TradedProduct l_tradedProduct = l_orderUnitImpl.getTradedProduct();
        
        // 1.6 get表示用時価情報(取引銘柄 : EqTypeTradedProduct, 補助口座 : 補助口座)
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        
        WEB3EquityProductManager l_productManager =
            (WEB3EquityProductManager)
                l_finApp.getTradingModule(ProductTypeEnum.EQUITY).getProductManager();                        
        
        WEB3EquityProductQuote l_productQuote =
            l_productManager.getDisplayEquityProductQuote(
                (EqTypeTradedProduct) l_tradedProduct,
                l_subAccount);
                
        // 1.7  get時価区分( )        
        String l_strQuoteTypeDiv = l_productQuote.getQuoteTypeDiv();
        
        // 1.8 get時価( )
        double l_dblQuote = l_productQuote.getQuote();
        if (Double.isNaN(l_dblQuote))
        {
            l_dblQuote = 0;
        }
                
        // 1.9 get前日比( )
        double l_dblComparedPreviousDay = l_productQuote.getComparedPreviousDay();

        // 1.10 get時価発表時間( )
        Timestamp l_quoteTime = l_productQuote.getQuoteTime();

        // 1.11 get銘柄( )
        WEB3EquityProduct l_product = (WEB3EquityProduct)l_orderUnitImpl.getProduct();
        if (l_product == null)
        {
            log.debug("銘柄オブジェクトが存在しない。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                 WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                 this.getClass().getName() + STR_METHOD_NAME,
                 "銘柄オブジェクトが存在しない。");
        }
        
        // 1.12  get概算簿価単価( )
        String l_strEstimatedBookPrice = l_orderUnitImpl.getEstimatedBookPrice();
        
        // 1.13 get市場閉局警告市場(部店 : 部店, 銘柄タイプ : ProductTypeEnum, 信用取引区分 : String)
        String[] l_tradeOpenMarkets = null;
        l_tradeOpenMarkets =
            WEB3GentradeTradingTimeManagement.getTradeCloseMarket(
                l_subAccount.getWeb3GenBranch(),
                ProductTypeEnum.EQUITY,
                WEB3MarginTradingDivDef.DEFAULT);
                
        // 1.14 createResponse( )
        WEB3SuccEquityCancelConfirmResponse l_response =(WEB3SuccEquityCancelConfirmResponse)l_request.createResponse( );
        
        EqtypeProductRow l_row = (EqtypeProductRow)l_product.getDataSourceObject();
        // 確認時発注日       ＝　@取引時間管理.get発注日()
        l_response.checkDate = WEB3GentradeTradingTimeManagement.getOrderBizDate();
        // 概算受渡代金       ＝　@予約注文単位.概算受渡代金
        RsvEqOrderUnitRow l_rsvEqOrderUnitRow = (RsvEqOrderUnitRow)l_orderUnitImpl.getDataSourceObject();
        l_response.estimatedPrice = WEB3StringTypeUtility.formatNumber(l_rsvEqOrderUnitRow.getEstimatedPrice());
        // 取引終了警告市場コード一覧    ＝　@get市場閉局警告市場()の戻り値
        l_response.messageSuspension = l_tradeOpenMarkets;       
        // 銘柄コード            ＝　@get銘柄()の戻り値.銘柄コード
        l_response.productCode = l_product.getProductCode();
        // 銘柄名          ＝　@get銘柄()の戻り値.getDataSourceObject().getStandardName()
        l_response.productName = l_row.getStandardName();
        // 市場コード            ＝　@予約注文単位.get市場().市場コード
        WEB3GentradeMarket l_gentradeMarket = null;
        try
        {
            l_gentradeMarket = l_orderUnitImpl.getMarket();
        }
        catch (NotFoundException l_nfe)
        {
            log.error(WEB3ErrorCatalog.SYSTEM_ERROR_80005.getErrorMessage());
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
               WEB3ErrorCatalog.SYSTEM_ERROR_80005,
               this.getClass().getName() + "." + STR_METHOD_NAME,
               l_nfe.getMessage(),
               l_nfe);
        }                                            
        if (l_gentradeMarket == null)
        {
            l_response.marketCode = null;
        }
        else
        {
            l_response.marketCode = l_gentradeMarket.getMarketCode();
        }        
        //口座区分：　@株式データアダプタ.get口座区分(予約注文単位.getTaxType())
        l_response.taxType = 
            WEB3EquityDataAdapter.getTaxType(l_orderUnitImpl.getTaxType());
        // 取引区分         ＝　@予約注文単位.getメッセージ用取引区分()
        l_response.tradingType = l_orderUnitImpl.getMsgTradingType();
        // 注文株数         ＝　@予約注文単位.注文数量
        l_response.orderQuantity = WEB3StringTypeUtility.formatNumber(l_orderUnitImpl.getQuantity());
        // 内出来株数        ＝　@null
        l_response.partContQuantity = null;
        // 注文単価区分       ＝　@予約注文単位.getメッセージ用注文単価区分()
        l_response.orderPriceDiv = l_orderUnitImpl.getMsgOrderPriceDiv();
        // 注文単価         ＝　@予約注文単位.getメッセージ用注文単価()
        l_response.limitPrice = l_orderUnitImpl.getMsgLimitPrice();
        // 概算簿価単価       ＝　@get概算簿価単価()
        l_response.estimatedBookPrice = l_strEstimatedBookPrice;
        // 値段条件         ＝　@予約注文単位.getメッセージ用値段条件()
        l_response.priceCondType = l_orderUnitImpl.getMsgPriceCondType();
        // 執行条件         ＝　@予約注文単位.getメッセージ用執行条件()
        l_response.execCondType = l_orderUnitImpl.getMsgExecCondType();
        // 注文期限区分       ＝　@予約注文単位.getメッセージ用注文期限区分()
        l_response.expirationDateType = l_orderUnitImpl.getMsgExpirationDateType();
        // 注文有効期限       ＝　@予約注文単位.getメッセージ用注文有効期限()
        l_response.expirationDate = l_orderUnitImpl.getMsgExpirationDate();
        // 発注条件区分       ＝　@予約注文単位.getメッセージ用発注条件区分()
        l_response.orderCondType = l_orderUnitImpl.getMsgOrderCondType();
        // 逆指値用発注条件単価   ＝　@null
        l_response.stopOrderCondPrice = null;
        // 逆指値用発注条件演算子  ＝　@null
        l_response.stopOrderCondOperator = null;
        // W指値用発注条件単価   ＝　@null
        l_response.wlimitOrderCondPrice = null;
        // W指値用発注条件演算子  ＝　@null
        l_response.wlimitOrderCondOperator = null;
        // W指値用注文単価区分   ＝　@null
        l_response.wLimitOrderPriceDiv = null;
        // W指値用注文単価     ＝　@null
        l_response.wLimitPrice = null;
        // 時価区分         ＝　@get時価区分()の戻り値
        l_response.currentPriceDiv = l_strQuoteTypeDiv;
        // 時価（現在値）      ＝　@get時価()の戻り値
        l_response.currentPrice = WEB3StringTypeUtility.formatNumber(l_dblQuote);
        // 前日比          ＝　@get前日比()の戻り値
        l_response.comparedPreviousDay = WEB3StringTypeUtility.formatNumber(l_dblComparedPreviousDay);
        // 取引時間(時価発表時間) ＝　@get時価発表時間()の戻り値
        l_response.currentPriceTime = l_quoteTime;
        // 単価調整値情報      ＝　@予約注文単位.create単価調整値情報()
        l_response.priceAdjustmentValueInfo = l_orderUnitImpl.createSuccPriceAdjustmentValueInfo();
        
        log.exiting(STR_METHOD_NAME);
        
        return l_response;        
    }
    
    /**
     * (submit注文取消)<BR>
     * （連続）現物株式注文取消完了処理を実施する。<BR>
     * <BR>
     * シーケンス図「（（連続）株式注文取消サービス）submit注文取消」参照。<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * リクエストデータ。<BR>
     * 
     * @@return WEB3SuccEquityCancelCompleteResponse
     * @@throws WEB3BaseException 
     * @@roseuid 4340BE100341
     */
    protected WEB3SuccEquityCancelCompleteResponse 
        submitCancelOrder(WEB3SuccEquityCancelCompleteRequest l_request)
        throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " submitCancelOrder(WEB3SuccEquityCancelCompleteRequest)";
        log.entering(STR_METHOD_NAME);
        
        if (l_request == null)
        {   
            log.debug("parameter is null");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017, 
                this.getClass().getName() + "." + STR_METHOD_NAME);              
        }
        
        // 1.1 validate()
        l_request.validate();
        
        // 1.2 get発注日(確認時発注日 : Date)
        WEB3GentradeTradingTimeManagement.getOrderBizDate(l_request.checkDate);
        
        // 1.3 get補助口座( )
        WEB3GentradeSubAccount l_subAccount = this.getSubAccount();
 
        // 1.4 get株式予約注文単位(long)
        WEB3ToSuccOrderManagerImpl l_managerImpl =
            (WEB3ToSuccOrderManagerImpl)WEB3TriggerOrderTradingModel.getOrderManager();

        WEB3ToSuccEqTypeOrderUnitImpl l_orderUnitImpl = null;
        try
        {
            l_orderUnitImpl = l_managerImpl.getReserveEqtypeOrderUnit(Long.parseLong(l_request.id));
        }
        catch (NotFoundException l_nfe)
        {
            log.error(WEB3ErrorCatalog.SYSTEM_ERROR_80005.getErrorMessage());
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
               WEB3ErrorCatalog.SYSTEM_ERROR_80005,
               this.getClass().getName() + "." + STR_METHOD_NAME,
               l_nfe.getMessage(),
               l_nfe);
        }
        
        // 1.5 validate現物注文取消(補助口座, 株式予約注文単位Impl)
        l_managerImpl.validateEqtypeCancelOrder(l_subAccount, l_orderUnitImpl);
        
        // 1.6 submit予約注文取消(SubAccount, 株式予約注文単位Impl, String)
        l_managerImpl.submitEqtypeCancelOrder(l_subAccount, l_orderUnitImpl, l_request.password);
        
        // 1.7 createResponse( )
        WEB3SuccEquityCancelCompleteResponse l_response =(WEB3SuccEquityCancelCompleteResponse)l_request.createResponse( );
        // 更新時間 ＝　@現在日時（＝GtlUtils.getSystemTimestamp()）
        l_response.lastUpdatedTimestamp = GtlUtils.getSystemTimestamp();
        // 識別番号 ＝　@リクエスト.ID（＝予約注文単位.注文ID）
        l_response.orderActionId = l_request.id;
        // 連続注文設定フラグ　@＝　@false（固定）
        l_response.succSettingFlag = false;
        
        log.exiting(STR_METHOD_NAME);
        
        return l_response;        
    }
}
@
