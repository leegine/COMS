head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.52.46;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8704d7ec30c0c9d;
filename	WEB3ToSuccEquityOrderServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : （連続）株式注文サービスImpl(WEB3ToSuccEquityOrderServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/10/14 鄭海良(中訊) 新規作成
Revesion History : 2007/01/12 徐宏偉(中訊) モデル216
Revesion History : 2007/01/17 徐宏偉(中訊) モデル222
*/

package webbroker3.triggerorder.service.delegate.stdimpls;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeAsset;
import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeOrderUnit;
import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeProduct;
import com.fitechlabs.xtrade.plugin.tc.eqtype.ordersubmitter.io.EqTypeNewCashBasedOrderSpec;
import com.fitechlabs.xtrade.plugin.tc.eqtype.ordersubmitter.io.EqTypeNewOrderValidationResult;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Asset;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderCategEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderManagerPersistenceContext;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3FuturesOptionDivDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.equity.WEB3EquityBizLogicProvider;
import webbroker3.equity.WEB3EquityNewCashBasedOrderSpec;
import webbroker3.equity.WEB3EquityOrderManager;
import webbroker3.equity.WEB3EquityPositionManager;
import webbroker3.equity.WEB3EquityTradedProduct;
import webbroker3.equity.WEB3EquityTradingModule;
import webbroker3.equity.service.delegate.WEB3EquityOrderRequestAdapter;
import webbroker3.equity.service.delegate.stdimpls.WEB3EquityOrderServiceImpl;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.tradingpower.WEB3TPTradingPowerResult;
import webbroker3.tradingpower.WEB3TPTradingPowerService;
import webbroker3.triggerorder.WEB3ToSuccEqTypeOrderUnitImpl;
import webbroker3.triggerorder.WEB3ToSuccOrderManagerImpl;
import webbroker3.triggerorder.message.WEB3SuccCommonInfo;
import webbroker3.triggerorder.message.WEB3SuccEquityBuyCompleteRequest;
import webbroker3.triggerorder.message.WEB3SuccEquityBuyConfirmRequest;
import webbroker3.triggerorder.message.WEB3SuccEquityBuyConfirmResponse;
import webbroker3.triggerorder.message.WEB3SuccEquitySellCompleteRequest;
import webbroker3.triggerorder.message.WEB3SuccEquitySellConfirmRequest;
import webbroker3.triggerorder.message.WEB3SuccEquitySellConfirmResponse;
import webbroker3.triggerorder.message.WEB3SuccPriceAdjustmentValueInfo;
import webbroker3.triggerorder.service.delegate.WEB3ToSuccEquityOrderRequestAdapter;
import webbroker3.triggerorder.service.delegate.WEB3ToSuccEquityOrderService;
import webbroker3.triggerorder.util.WEB3TriggerOrderTradingModel;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * (（連続）株式注文サービスImpl)<BR>
 * （連続）株式注文サービス実装クラス。
 *   
 * @@author 鄭海良
 * @@version 1.0
 */
public class WEB3ToSuccEquityOrderServiceImpl extends WEB3EquityOrderServiceImpl 
    implements WEB3ToSuccEquityOrderService 
{

    /**
     * (ログ出力ユーティリティ。)<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3ToSuccEquityOrderServiceImpl.class);
    
    /**
     * @@roseuid 4349D9C700FA
     */
    public WEB3ToSuccEquityOrderServiceImpl() 
    {
     
    }
    
    /**
     * (validate注文)<BR>
     * （連続）現物株式注文確認処理を実施する。<BR>
     * <BR>
     * シーケンス図「（（連続）株式注文サービス）validate注文」参照。<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * リクエストデータ。<BR>
     * 「（連続）現物株式買付注文確認リクエスト」または<BR>
     * 「（連続）現物株式売付注文確認リクエスト」が<BR>
     *  設定される。<BR>
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException 
     * @@roseuid 431D3A8A00AB
     */
    protected WEB3GenResponse validateOrder(WEB3GenRequest l_request) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME =" validateOrder(WEB3GenRequest)";
        log.entering(STR_METHOD_NAME);
        
        WEB3GenResponse l_response = null;
        
        WEB3SuccCommonInfo l_succCommonInfo = null;
        if (l_request instanceof WEB3SuccEquityBuyConfirmRequest)
        {
            WEB3SuccEquityBuyConfirmRequest l_buyConfirmRequest = (WEB3SuccEquityBuyConfirmRequest)l_request;
            
            //1.1 validate()
            l_buyConfirmRequest.validate(); //WEB3BusinessLayerException 
            l_succCommonInfo = l_buyConfirmRequest.succCommonInfo;
        }
        else if (l_request instanceof WEB3SuccEquitySellConfirmRequest)
        {
            WEB3SuccEquitySellConfirmRequest l_sellConfirmRequest = (WEB3SuccEquitySellConfirmRequest)l_request;

            //1.1 validate()
            l_sellConfirmRequest.validate(); //WEB3BusinessLayerException 
            l_succCommonInfo = l_sellConfirmRequest.succCommonInfo;
        }
        else
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80018,
                this.getClass().getName() + STR_METHOD_NAME, 
                "パラメータタイプ不正。");
        }

        //1.2 get株式親注文の注文単位(long)
        WEB3ToSuccOrderManagerImpl l_orderManager = 
            (WEB3ToSuccOrderManagerImpl)WEB3TriggerOrderTradingModel.getOrderManager();
        long l_lngParentOrderId = Long.parseLong(l_succCommonInfo.parentOrderId);
        EqTypeOrderUnit l_orderUnit = 
            l_orderManager.getEqtypeParentOrderUnit(l_lngParentOrderId);
            
        //1.3 validateリクエストデータat反対取引(WEB3GenRequest, 注文単位)
        this.validateRequestDataAtReversingTrade(l_request, l_orderUnit);   
            
        //1.4 validate連続注文(補助口座, ProductTypeEnum, String, String, OrderUnit)
        l_orderManager.validateSuccOrder(
            this.getSubAccount(),
            ProductTypeEnum.EQUITY,
            WEB3FuturesOptionDivDef.DEFAULT,
            l_succCommonInfo.succTradingType,
            l_orderUnit);
                
        //1.5 validate連続注文最大設定数(OrderUnit)
        this.validateSuccOrderMaxQuantity(l_orderUnit);
            
        //1.6 validate注文(入力データ : WEB3GenRequest)
        l_response = super.validateOrder(l_request); //WEB3BaseException

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (submit注文)<BR>
     * （連続）現物株式注文を登録する。<BR>
     * <BR>
     * シーケンス図「（（連続）株式注文サービス）submit注文」参照。<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * リクエストデータ。<BR>
     * 「（連続）現物株式買付注文完了リクエスト」または<BR>
     * 「（連続）現物株式売付注文完了リクエスト」が<BR>
     * 設定される。<BR>
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException 
     * @@roseuid 431D3A8A00BA
     */
    protected WEB3GenResponse submitOrder(WEB3GenRequest l_request) throws WEB3BaseException  
    {
        final String STR_METHOD_NAME =" submitOrder(WEB3GenRequest)";
        log.entering(STR_METHOD_NAME);
        
        WEB3GenResponse l_response = null;
        WEB3SuccCommonInfo l_succCommonInfo = null;
        long l_lngOrderId = 0;
        if (l_request instanceof WEB3SuccEquityBuyCompleteRequest)
        {
            WEB3SuccEquityBuyCompleteRequest l_buyCompleteRequest = (WEB3SuccEquityBuyCompleteRequest)l_request;
            
            //1.1 validate()
            l_buyCompleteRequest.validate(); //WEB3BusinessLayerException
            
            l_succCommonInfo = l_buyCompleteRequest.succCommonInfo;
            l_lngOrderId = Long.parseLong(l_buyCompleteRequest.orderId);
        }
        else if (l_request instanceof WEB3SuccEquitySellCompleteRequest)
        { 
            WEB3SuccEquitySellCompleteRequest l_sellCompleteRequest = (WEB3SuccEquitySellCompleteRequest)l_request;
            //1.1 validate()
            l_sellCompleteRequest.validate(); //WEB3BusinessLayerException
            
            l_succCommonInfo = l_sellCompleteRequest.succCommonInfo;
            l_lngOrderId = Long.parseLong(l_sellCompleteRequest.orderId);
        }
        else
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80018,
                this.getClass().getName() + STR_METHOD_NAME, 
                "パラメータタイプ不正。");
        }
        
        //1.2 get株式親注文の注文単位(long)
        WEB3ToSuccOrderManagerImpl l_orderManager = 
            (WEB3ToSuccOrderManagerImpl)WEB3TriggerOrderTradingModel.getOrderManager();
        long l_lngParentOrderId = Long.parseLong(l_succCommonInfo.parentOrderId);
        EqTypeOrderUnit l_orderUnit = 
            l_orderManager.getEqtypeParentOrderUnit(l_lngParentOrderId);
        if (l_orderUnit == null)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + STR_METHOD_NAME, 
                "テーブルに該当するデータがありません。");
        }
             
        //1.3 validateリクエストデータat反対取引(WEB3GenRequest, 注文単位)
        this.validateRequestDataAtReversingTrade(l_request, l_orderUnit);   
            
        //1.4 validate連続注文(補助口座, ProductTypeEnum, String, String, OrderUnit)
        l_orderManager.validateSuccOrder(
            this.getSubAccount(),
            ProductTypeEnum.EQUITY,
            WEB3FuturesOptionDivDef.DEFAULT,
            l_succCommonInfo.succTradingType,
            l_orderUnit);
            
        //1.5 validate連続注文最大設定数(OrderUnit)
        this.validateSuccOrderMaxQuantity(l_orderUnit);
            
        //1.6  submit注文(入力データ : WEB3GenRequest)
        l_response = super.submitOrder(l_request); //WEB3BaseException
            
        //1.7 notify予約注文登録(long)
        this.notifyRsvOrderRegister(l_lngOrderId);
            
        //1.8 （*）super.submit注文()の戻り値を返却する
        
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * （連続）株式注文処理を実行する。<BR>
     * <BR>
     * リクエストデータの型により、validate注文()またはsubmit注文()メソッド<BR>
     * をコールする。
     * @@param l_request - リクエストデータ。
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 431D3A8A00DA
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME =" execute(WEB3GenRequest)";
        log.entering(STR_METHOD_NAME);
        
        if (l_request == null)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME, 
                "リクエストが未指定(null)です。");
        }
        
        WEB3GenResponse l_response = null;

        if (l_request instanceof WEB3SuccEquityBuyConfirmRequest)
        {
            //validate注文()()
            l_response = 
                this.validateOrder((WEB3SuccEquityBuyConfirmRequest)l_request);
        }
        else if (l_request instanceof WEB3SuccEquitySellConfirmRequest)
        {
            //validate注文()()
            l_response = 
                this.validateOrder((WEB3SuccEquitySellConfirmRequest)l_request);
        }
        else if (l_request instanceof WEB3SuccEquityBuyCompleteRequest)
        {
            //submit注文()
            l_response = 
                this.submitOrder((WEB3SuccEquityBuyCompleteRequest)l_request);
        }
        else if (l_request instanceof WEB3SuccEquitySellCompleteRequest)
        {
            //submit注文()
            l_response = 
                this.submitOrder((WEB3SuccEquitySellCompleteRequest)l_request);
        }
        else
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80018,
                this.getClass().getName() + STR_METHOD_NAME, 
                "パラメータタイプ不正。");
        }
        
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * (createリクエストアダプタ)<BR>
     * リクエストアダプタのインスタンスを生成する。<BR>
     * （継承元クラスの同名メソッドのオーバーライド）<BR>
     * <BR>
     * （連続）株式注文リクエストアダプタ.create(引数のリクエスト)をコールする。<BR>
     * @@param l_request - (リクエスト)<BR>
     * リクエストデータ。
     * @@return WEB3EquityOrderRequestAdapter
     * @@roseuid 43278E38026D
     */
    protected WEB3EquityOrderRequestAdapter createRequestAdapter(WEB3GenRequest l_request) 
    {
        final String STR_METHOD_NAME =" createRequestAdapter(WEB3GenRequest)";
        log.entering(STR_METHOD_NAME);
        
        WEB3EquityOrderRequestAdapter l_adapter = WEB3ToSuccEquityOrderRequestAdapter.create(l_request);
        
        log.exiting(STR_METHOD_NAME);
        return l_adapter;
    }
    
    /**
     * (validate現物株式注文)<BR>
     * 現物株式発注審査メソッドの呼び出しを行う。<BR>
     * （継承元クラスの同名メソッドのオーバーライド）<BR>
     * <BR>
     * EQTYPEの拡張株式注文マネージャ.validate現物株式注文(<BR>
     * 補助口座, 株式注文内容, is連続反対売買)にdelegateする。<BR>
     * <BR>
     * ------------------------------------------------------<BR>
     * ＜validate現物株式注文()：引数設定仕様＞<BR>
     * <BR>
     * 補助口座：　@this.get補助口座()の戻り値<BR>
     * 株式注文内容：　@引数の株式注文内容オブジェクト<BR>
     * is連続反対売買：　@連続注文マネージャImpl.is反対売買取引(<BR>
     * 連続注文取引区分(*1), 親注文の注文単位(*2))の戻り値<BR>
     * <BR>
     * (*1)連続注文取引区分：　@引数のリクエストアダプタ.リクエスト.連続注文共通情報 の<BR>
     * 同項目<BR>
     * (*2)親注文の注文単位：　@引数のリクエストアダプタ.親注文の注文単位<BR>
     * ------------------------------------------------------<BR>
     * @@param l_orderSpec - (株式注文内容)<BR>
     * 
     * @@param l_requestAdaptor - (（連続）株式注文リクエストアダプタ)<BR>
     * （連続）株式注文リクエストアダプタ。
     * @@return EqTypeNewOrderValidationResult
     * @@throws WEB3BaseException
     * @@roseuid 43276F1C0309
     */
    protected EqTypeNewOrderValidationResult validateNewCashBasedOrder(
        EqTypeNewCashBasedOrderSpec l_orderSpec, 
        WEB3EquityOrderRequestAdapter l_requestAdaptor) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME =" validateNewCashBasedOrder(EqTypeNewCashBasedOrderSpec, " +
            "WEB3EquityOrderRequestAdapter)";
        log.entering(STR_METHOD_NAME);

        if (l_requestAdaptor == null)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME, 
                "（連続）株式注文リクエストアダプタが未指定(null)です。");
        }
        
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        WEB3EquityTradingModule l_tradingModule
            = (WEB3EquityTradingModule) l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
        WEB3EquityOrderManager l_equityOrderManager
            = (WEB3EquityOrderManager) l_tradingModule.getOrderManager();

        WEB3ToSuccOrderManagerImpl l_toOrderManager = 
            (WEB3ToSuccOrderManagerImpl)WEB3TriggerOrderTradingModel.getOrderManager();
        
        WEB3SuccCommonInfo l_succCommonInfo = null;
        if (l_requestAdaptor.request instanceof WEB3SuccEquityBuyConfirmRequest)
        {
            WEB3SuccEquityBuyConfirmRequest l_buyConfirmRequest = 
                (WEB3SuccEquityBuyConfirmRequest)l_requestAdaptor.request;
            l_succCommonInfo = l_buyConfirmRequest.succCommonInfo;
        }
        else if (l_requestAdaptor.request instanceof WEB3SuccEquityBuyCompleteRequest)
        {
            WEB3SuccEquityBuyCompleteRequest l_buyCompleteRequest = 
                (WEB3SuccEquityBuyCompleteRequest)l_requestAdaptor.request;
            l_succCommonInfo = l_buyCompleteRequest.succCommonInfo;
        }
        else if (l_requestAdaptor.request instanceof WEB3SuccEquitySellConfirmRequest)
        {
            WEB3SuccEquitySellConfirmRequest l_sellConfirmRequest = 
                (WEB3SuccEquitySellConfirmRequest)l_requestAdaptor.request;
            l_succCommonInfo = l_sellConfirmRequest.succCommonInfo;
        }
        else if (l_requestAdaptor.request instanceof WEB3SuccEquitySellCompleteRequest)
        {
            WEB3SuccEquitySellCompleteRequest l_sellCompleteRequest = 
                (WEB3SuccEquitySellCompleteRequest)l_requestAdaptor.request;
            l_succCommonInfo = l_sellCompleteRequest.succCommonInfo;
        }
        else
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80018,
                this.getClass().getName() + STR_METHOD_NAME, 
                "パラメータタイプ不正。");
        }
        
        WEB3ToSuccEquityOrderRequestAdapter l_toSuccEquityOrderRequestAdapter = null;
        if (l_requestAdaptor instanceof WEB3ToSuccEquityOrderRequestAdapter)
        {
            l_toSuccEquityOrderRequestAdapter = 
                (WEB3ToSuccEquityOrderRequestAdapter)l_requestAdaptor;
        }
        else
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80018,
                this.getClass().getName() + STR_METHOD_NAME, 
                "パラメータタイプ不正。");
        }
        
        boolean l_blnReversingTrade = l_toOrderManager.isReversingTrade(
            l_succCommonInfo.succTradingType, 
            l_toSuccEquityOrderRequestAdapter.parentOrderUnit);
        
        //validate現物株式注文()            
        EqTypeNewOrderValidationResult l_result = l_equityOrderManager.validateNewCashBasedOrder(
            this.getSubAccount(), //WEB3BaseException
            l_orderSpec,
            l_blnReversingTrade);
        
        log.exiting(STR_METHOD_NAME);
        return l_result;
    }
    
    /**
     * (validate取引余力)<BR>
     * 取引余力をチェックし、取引余力結果オブジェクトを返却する。<BR>
     * （継承元クラスの同名メソッドのオーバーライド）<BR>
     * <BR>
     * 引数の株式注文内容.isSellOrder()==true（売注文）の場合は、<BR>
     * 何もせずにreturnする。（nullを返却）<BR>
     * <BR>
     * 引数の株式注文内容.isSellOrder()==false（買注文）の場合は、<BR>
     * 余力チェックを実施する部店(*3)の場合のみ、<BR>
     * 買付可能額(*1)と概算受渡代金(*2)を比較し、<BR>
     * （概算受渡代金(*2) > 買付可能額(*1)）の場合は、<BR>
     * 「取引余力不足」の例外をthrowする。<BR>
     * 　@class: WEB3BusinessLayerException<BR>
     * 　@tag:   BUSINESS_ERROR_01306<BR>
     * <BR>
     * 以外、nullを返却する。<BR>
     * <BR>
     * (*1)買付可能額<BR>
     * 取引余力サービス.get株式買付可能額～連続注文～(引数の補助口座, 引数の<BR>
     * 取引銘柄.受渡日, null)<BR>
     * の戻り値を、買付可能額とする。<BR>
     * <BR>
     * (*2)概算受渡代金<BR>
     * 引数の株式注文内容.get概算受渡代金()で取得。<BR>
     * <BR>
     * (*3)余力チェックを実施する部店<BR>
     * 連続注文マネージャImpl.is余力チェック実施部店()==trueの場合は、<BR>
     * 余力チェックを実施する部店であると判定する。<BR>
     * @@param l_subAccount - (補助口座)<BR>
     * 補助口座オブジェクト。
     * @@param l_orderSpec - (株式注文内容)<BR>
     * 株式注文内容オブジェクト。
     * @@param l_blnUpdateFlg - (余力更新フラグ)<BR>
     * 余力更新フラグ。<BR>
     * （false：　@確認時、true：　@完了時）<BR>
     * @@param l_tradedProduct - (取引銘柄)<BR>
     * 取引銘柄オブジェクト。
     * @@return WEB3TPTradingPowerResult
     * @@throws WEB3BaseException
     * @@roseuid 43278E38026F
     */
    protected WEB3TPTradingPowerResult validateTradingPower(
        WEB3GentradeSubAccount l_subAccount, 
        WEB3EquityNewCashBasedOrderSpec l_orderSpec,
        boolean l_blnUpdateFlg,
        WEB3EquityTradedProduct l_tradedProduct) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME =" validateTradingPower(" +
            "WEB3GentradeSubAccount, WEB3EquityNewCashBasedOrderSpec, boolean, WEB3EquityTradedProduct)";
        log.entering(STR_METHOD_NAME);
        
        if (l_orderSpec == null)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME, 
                "株式注文内容オブジェクトが未指定(null)です。");
        }
        if (l_tradedProduct == null)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME, 
                "取引銘柄オブジェクトが未指定(null)です。");
        }

        if (l_orderSpec.isSellOrder())
        {
            log.exiting(STR_METHOD_NAME);
            return null;
        }
        
        WEB3ToSuccOrderManagerImpl l_toOrderManager = 
            (WEB3ToSuccOrderManagerImpl)WEB3TriggerOrderTradingModel.getOrderManager();
        if (l_toOrderManager.isCheckTradingPowerBranch(l_subAccount) == false)
        {
            log.exiting(STR_METHOD_NAME);
            return null;
        }

        WEB3TPTradingPowerService l_trdingPowerService = 
            (WEB3TPTradingPowerService)Services.getService(WEB3TPTradingPowerService.class);
        double l_dblBuyPower = l_trdingPowerService.getSuccEquityTradingPower(
            l_subAccount,
            l_tradedProduct.getDailyDeliveryDate(),
            null);
        
        double l_dblDeliveryAmount = l_orderSpec.getEstimateDeliveryAmount();
        
        if (l_dblDeliveryAmount > l_dblBuyPower)
        {
            String l_strMessage = "概算受渡代金「" + l_dblDeliveryAmount 
                + "」 > 買付可能額「" + l_dblBuyPower + "」。";
            log.debug(l_strMessage);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01306,
                getClass().getName() + STR_METHOD_NAME,
                l_strMessage);
        }
        
        log.exiting(STR_METHOD_NAME);
        return null;
    }
    
    /**
     * (get概算簿価単価)<BR>
     * 概算簿価単価を取得し返す。<BR>
     * （継承元クラスの同名メソッドのオーバーライド）<BR>
     * <BR>
     * １）　@引数のリクエストアダプタ.is売注文()==falseの場合は、nullを返却する。<BR>
     * 　@　@　@以外、以下の処理を行う。<BR>
     * <BR>
     * ２）　@親注文の反対売買取引かどうかを判定する。<BR>
     * <BR>
     * 　@　@連続注文マネージャImpl.is反対売買取引()==true（反対売買）の場合は、<BR>
     * 　@　@nullを返却する。<BR>
     * <BR>
     * 　@　@-------------------------------------------------------<BR>
     * 　@　@＜is反対売買取引()：引数設定仕様＞<BR>
     * <BR>
     * 　@　@連続注文取引区分：　@引数のリクエストアダプタのリクエスト.<BR>
     * 連続注文共通情報.連続注文取引区分<BR>
     * 　@　@親注文の注文単位：　@引数のリクエストアダプタの親注文の注文単位<BR>
     * 　@　@-------------------------------------------------------<BR>
     * <BR>
     * ３）　@反対売買でない場合、保有資産オブジェクトを取得する。<BR>
     * <BR>
     * 　@株式ポジションマネージャ.getAsset(引数のリクエストアダプタ.リクエスト.ID)<BR>
     * をコールする。<BR>
     * <BR>
     * ４）　@株式計算サービス.calc概算簿価単価(保有資産.銘柄ID, this.get補助口座(), <BR>
     * 保有資産.税区分)をコールし、<BR>
     * 　@　@　@戻り値を返却する。<BR>
     * @@param l_requestAdaptor - (（連続）株式注文リクエストアダプタ)<BR>
     * （連続）株式注文リクエストアダプタ。
     * @@return String
     * @@throws WEB3BaseException
     * @@roseuid 43278E3802BB
     */
    protected String getEstimatedBookPrice(WEB3EquityOrderRequestAdapter l_requestAdaptor) 
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME =" getEstimatedBookPrice(WEB3EquityOrderRequestAdapter)";
        log.entering(STR_METHOD_NAME);
        
        if (l_requestAdaptor == null)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME, 
                "（連続）株式注文リクエストアダプタが未指定(null)です。");
        }

        //１）　@引数のリクエストアダプタ.is売注文()==falseの場合は、nullを返却する。
        //　@　@　@以外、以下の処理を行う。
        if (!l_requestAdaptor.isSellOrder()) //WEB3BaseException
        {
            log.exiting(STR_METHOD_NAME);
            return null;
        }
        

        long l_lngAssetId = 0;
        //２）　@親注文の反対売買取引かどうかを判定する。
        //　@　@連続注文マネージャImpl.is反対売買取引()==true（反対売買）の場合は、
        //　@　@nullを返却する。
        WEB3SuccCommonInfo l_succCommonInfo = null;
        if (l_requestAdaptor.request instanceof WEB3SuccEquitySellConfirmRequest)
        {
            WEB3SuccEquitySellConfirmRequest l_sellConfirmRequest = 
                (WEB3SuccEquitySellConfirmRequest)l_requestAdaptor.request;
            l_succCommonInfo = l_sellConfirmRequest.succCommonInfo;
            if (!WEB3StringTypeUtility.isEmpty(l_sellConfirmRequest.id))
            {
                l_lngAssetId = Long.parseLong(l_sellConfirmRequest.id);
            }
        }
        else if (l_requestAdaptor.request instanceof WEB3SuccEquitySellCompleteRequest)
        {
            WEB3SuccEquitySellCompleteRequest l_sellCompleteRequest = 
                (WEB3SuccEquitySellCompleteRequest)l_requestAdaptor.request;
            l_succCommonInfo = l_sellCompleteRequest.succCommonInfo;
            if (!WEB3StringTypeUtility.isEmpty(l_sellCompleteRequest.id))
            {
                l_lngAssetId = Long.parseLong(l_sellCompleteRequest.id);
            }
        }
        else
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80018,
                this.getClass().getName() + STR_METHOD_NAME, 
                "パラメータタイプ不正。");
        }
        WEB3ToSuccEquityOrderRequestAdapter l_toSuccEquityOrderRequestAdapter = null;
        if (l_requestAdaptor instanceof WEB3ToSuccEquityOrderRequestAdapter)
        {
            l_toSuccEquityOrderRequestAdapter = 
                (WEB3ToSuccEquityOrderRequestAdapter)l_requestAdaptor;
        }
        else
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80018,
                this.getClass().getName() + STR_METHOD_NAME, 
                "パラメータタイプ不正。");
        }
        
        WEB3ToSuccOrderManagerImpl l_toOrderManager = 
            (WEB3ToSuccOrderManagerImpl)WEB3TriggerOrderTradingModel.getOrderManager();
        boolean l_blnReversingTrade = l_toOrderManager.isReversingTrade(
            l_succCommonInfo.succTradingType, 
            l_toSuccEquityOrderRequestAdapter.parentOrderUnit);

        if (l_blnReversingTrade)
        {
            log.exiting(STR_METHOD_NAME);
            return null;
        }

        //３）　@反対売買でない場合、保有資産オブジェクトを取得する。
        //　@株式ポジションマネージャ.getAsset(引数のリクエストアダプタ.リクエスト.ID)
        //をコールする。
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        TradingModule l_tradingModule =
            l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
        WEB3EquityPositionManager l_positionManager =
            (WEB3EquityPositionManager)l_tradingModule.getPositionManager();
        Asset l_asset = null;
        try
        {
            l_asset = l_positionManager.getAsset(l_lngAssetId);
        }
        catch (NotFoundException l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        //４）　@株式計算サービス.calc概算簿価単価(保有資産.銘柄ID, this.get補助口座(), 
        //保有資産.税区分)をコールし、
        //　@　@　@戻り値を返却する。
        WEB3EquityBizLogicProvider l_bizLogicProvider =
            (WEB3EquityBizLogicProvider)l_tradingModule.getBizLogicProvider();
        double l_dblEstimatedBookPrice =  l_bizLogicProvider.calcEstimatedBookPrice(
            l_asset.getProduct().getProductId(),
            this.getSubAccount(),
            l_asset.getTaxType());
        
        String l_strEstimatedBookPrice = WEB3StringTypeUtility.formatNumber(l_dblEstimatedBookPrice);
            
        log.exiting(STR_METHOD_NAME);
        return l_strEstimatedBookPrice;
    }
    
    /**
     * (validate売付可能数量)<BR>
     * 売注文の場合の、売付可能数量のチェックを行う。<BR>
     * （継承元クラスの同名メソッドのオーバーライド）<BR>
     * <BR>
     * １）　@引数のリクエストアダプタ.is売注文()==falseの場合は、何もせずにreturnする。<BR>
     * 　@　@　@以外、以下の処理を行う。 <BR>
     * <BR>
     * ２）　@親注文の反対売買取引かどうかを判定する。<BR>
     * <BR>
     * 　@　@連続注文マネージャImpl.is反対売買取引()==false（反対売買ではない）の場合は、<BR>
     * 　@　@何もせずにそのままreturnする。<BR>
     * <BR>
     * 　@　@-------------------------------------------------------<BR>
     * 　@　@＜is反対売買取引()：引数設定仕様＞<BR>
     * <BR>
     * 　@　@連続注文取引区分：　@引数のリクエストアダプタのリクエスト.連続注文共通情報<BR>
     * .連続注文取引区分<BR>
     * 　@　@親注文の注文単位：　@引数のリクエストアダプタの親注文の注文単位<BR>
     * 　@　@-------------------------------------------------------<BR>
     * <BR>
     * 　@　@戻り値がtrueの場合は、親注文の反対売買取引と判定し、以下の処理を行う。<BR>
     * <BR>
     * ３）　@確定残の数量(*1)を取得する。<BR>
     * <BR>
     * 　@　@株式ポジションマネージャ.get保有資産()により、保有資産オブジェクトを<BR>
     * 取得する。<BR>
     * <BR>
     * 　@　@-------------------------------------------------------<BR>
     * 　@　@＜get保有資産()：引数設定仕様＞<BR>
     * <BR>
     * 　@　@口座ID：　@get補助口座().口座ID<BR>
     * 　@　@補助口座ID：　@get補助口座().補助口座ID<BR>
     * 　@　@銘柄ID：　@引数のリクエストアダプタ.get銘柄().銘柄ID<BR>
     * 　@　@税区分：　@引数のリクエストアダプタ.get税区分()<BR>
     * 　@　@-------------------------------------------------------<BR>
     * <BR>
     * 　@　@確定残の数量(*1) = 保有資産.数量<BR>
     * 　@　@※該当する保有資産オブジェクトが存在しない場合は、0をセットする。<BR>
     * <BR>
     * ４）　@注文中の数量(*2)を取得する。<BR>
     * <BR>
     * 　@　@注文中の数量(*2) = ２）で取得した保有資産.getLockedQuantity()<BR>
     * 　@　@※該当する保有資産オブジェクトが存在しない場合は、0をセットする。<BR>
     * <BR>
     * ５）　@親注文の未約定数量(*3)を取得する。<BR>
     * <BR>
     * 　@　@親注文の未約定数量(*3) = 引数のリクエストアダプタ.親注文の注文単位.注文数量<BR>
     * 　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@ － 引数のリクエストアダプタ.親注文の<BR>
     * 　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@注文単位.約定数量<BR>
     * <BR>
     * ６）　@売付株数が売付可能数量の範囲内かどうかをチェックする。<BR>
     * <BR>
     * 　@　@（確定残の数量(*1) － 注文中の数量(*2) ＋ 親注文の未約定数量(*3)）<<BR>
     * 引数のリクエストアダプタ.リクエスト.注文株数<BR>
     * 　@　@ の場合は、「売付可能数量エラー」の例外をthrowする。<BR>
     * 　@　@ class: WEB3BusinessLayerException<BR>
     * 　@　@ tag:   BUSINESS_ERROR_00167<BR>
     * @@param l_requestAdaptor - (（連続）株式注文リクエストアダプタ)<BR>
     * （連続）株式注文リクエストアダプタ。
     * @@throws WEB3BaseException
     * @@roseuid 431E7B4E0200
     */
    protected void validateSellableAssetQuantity(WEB3EquityOrderRequestAdapter l_requestAdaptor)
        throws WEB3BaseException 
    {
        final String STR_METHOD_NAME =" validateSellableAssetQuantity(WEB3EquityOrderRequestAdapter)";
        log.entering(STR_METHOD_NAME);
        
        if (l_requestAdaptor == null)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME, 
                "（連続）株式注文リクエストアダプタが未指定(null)です。");
        }

        //１）　@引数のリクエストアダプタ.is売注文()==falseの場合は、何もせずにreturnする。
        //　@　@　@以外、以下の処理を行う。 
        if (!l_requestAdaptor.isSellOrder())
        {
            log.exiting(STR_METHOD_NAME);
            return;
        }

        //２）　@親注文の反対売買取引かどうかを判定する。
        //　@　@連続注文マネージャImpl.is反対売買取引()==false（反対売買ではない）の場合は、
        //　@　@何もせずにそのままreturnする。
        //　@　@戻り値がtrueの場合は、親注文の反対売買取引と判定し、以下の処理を行う。
        double l_dblRequestOrderQuantity = 0;
        WEB3SuccCommonInfo l_succCommonInfo = null;
        if (l_requestAdaptor.request instanceof WEB3SuccEquitySellConfirmRequest)
        {
            WEB3SuccEquitySellConfirmRequest l_sellConfirmRequest = 
                (WEB3SuccEquitySellConfirmRequest)l_requestAdaptor.request;
            l_succCommonInfo = l_sellConfirmRequest.succCommonInfo;
            l_dblRequestOrderQuantity = Double.parseDouble(l_sellConfirmRequest.orderQuantity);
        }
        else if (l_requestAdaptor.request instanceof WEB3SuccEquitySellCompleteRequest)
        {
            WEB3SuccEquitySellCompleteRequest l_sellCompleteRequest = 
                (WEB3SuccEquitySellCompleteRequest)l_requestAdaptor.request;
            l_succCommonInfo = l_sellCompleteRequest.succCommonInfo;
            l_dblRequestOrderQuantity = Double.parseDouble(l_sellCompleteRequest.orderQuantity);
        }
        else
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80018,
                this.getClass().getName() + STR_METHOD_NAME, 
                "パラメータタイプ不正。");
        }
        WEB3ToSuccEquityOrderRequestAdapter l_toSuccEquityOrderRequestAdapter = null;
        if (l_requestAdaptor instanceof WEB3ToSuccEquityOrderRequestAdapter)
        {
            l_toSuccEquityOrderRequestAdapter = 
                (WEB3ToSuccEquityOrderRequestAdapter)l_requestAdaptor;
        }
        else
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80018,
                this.getClass().getName() + STR_METHOD_NAME, 
                "パラメータタイプ不正。");
        }
        
        WEB3ToSuccOrderManagerImpl l_toOrderManager = 
            (WEB3ToSuccOrderManagerImpl)WEB3TriggerOrderTradingModel.getOrderManager();
        boolean l_blnReversingTrade = l_toOrderManager.isReversingTrade(
            l_succCommonInfo.succTradingType, 
            l_toSuccEquityOrderRequestAdapter.parentOrderUnit);

        if (!l_blnReversingTrade)
        {
            log.exiting(STR_METHOD_NAME);
            return;
        }

        //３）　@確定残の数量(*1)を取得する。
        //　@　@株式ポジションマネージャ.get保有資産()により、保有資産オブジェクトを
        //取得する。
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        TradingModule l_tradingModule =
            l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
        WEB3EquityPositionManager l_positionManager =
            (WEB3EquityPositionManager)l_tradingModule.getPositionManager();
        EqTypeAsset l_asset = l_positionManager.getAsset(
            this.getSubAccount().getAccountId(),
            this.getSubAccount().getSubAccountId(),
            l_toSuccEquityOrderRequestAdapter.getProduct().getProductId(),
            l_toSuccEquityOrderRequestAdapter.getTaxDivision()); //WEB3BaseException
        double l_dblRestQuantity = 0;
        if (l_asset != null)
        {
            l_dblRestQuantity = l_asset.getQuantity();
        }

        //４）　@注文中の数量(*2)を取得する。
        //　@　@注文中の数量(*2) = ２）で取得した保有資産.getLockedQuantity()
        //　@　@※該当する保有資産オブジェクトが存在しない場合は、0をセットする。
        double l_dblOrderQuantity = 0;
        if (l_asset != null)
        {
            l_dblOrderQuantity = l_asset.getLockedQuantity();
        }
       
        
        //５）　@親注文の未約定数量(*3)を取得する。
        //
        //　@　@親注文の未約定数量(*3) = 引数のリクエストアダプタ.親注文の注文単位.注文数量
        //　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@ － 引数のリクエストアダプタ.親注文の
        //　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@注文単位.約定数量
        EqTypeOrderUnit l_orderUnit = l_toSuccEquityOrderRequestAdapter.parentOrderUnit;
        if (l_orderUnit == null)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME, 
                "親注文の注文単位が未指定(null)です。");
        }
        
        double l_dblQuantity = l_orderUnit.getQuantity();
        if (Double.isNaN(l_dblQuantity))
        {
            l_dblQuantity = 0;
        }
        double l_dblExecutedQuantity = l_orderUnit.getExecutedQuantity();
        if (Double.isNaN(l_dblExecutedQuantity))
        {
            l_dblExecutedQuantity = 0;
        }
        double l_dblUnexecutedQuantity = l_dblQuantity - l_dblExecutedQuantity;
        
        //６）　@売付株数が売付可能数量の範囲内かどうかをチェックする。
        //　@　@（確定残の数量(*1) － 注文中の数量(*2) ＋ 親注文の未約定数量(*3)）
        //引数のリクエストアダプタ.リクエスト.注文株数
        //　@　@ の場合は、「売付可能数量エラー」の例外をthrowする。
        if (l_dblRequestOrderQuantity > (l_dblRestQuantity - l_dblOrderQuantity + l_dblUnexecutedQuantity))
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00167,
                getClass().getName() + STR_METHOD_NAME,
                "指定株数は売付可能株数を超えています。");
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * (submit現物株式注文)<BR>
     * 予約注文を登録する。<BR>
     * （継承元クラスの同名メソッドのオーバーライド）<BR>
     * <BR>
     * 連続注文マネージャImpl.submit現物株式新規予約注文()をコールする。<BR>
     * <BR>
     * ---------------------------------------------------------<BR>
     * ＜submit現物株式新規予約注文()：引数設定仕様＞<BR>
     * <BR>
     * 補助口座：　@引数の補助口座<BR>
     * 注文内容：　@引数の株式注文内容<BR>
     * 注文ID：　@引数の注文ID<BR>
     * 取引パスワード：　@引数のパスワード<BR>
     * 連続注文取引区分：　@<BR>
     * 引数のリクエストアダプタ.リクエスト.連続注文共通情報.連続注文取引区分<BR>
     * 単価調整値：　@引数のリクエストアダプタ.リクエスト.単価調整値情報.get単価調整値()<BR>
     * 　@　@※引数のリクエストアダプタ.リクエスト.単価調整値情報==nullの場合は、<BR>
     * 　@　@　@nullをセット<BR>
     * 親注文の注文単位：　@引数のリクエストアダプタ.親注文の注文単位<BR>
     * ---------------------------------------------------------<BR>
     * @@param l_subAccount - (補助口座)<BR>
     * 補助口座オブジェクト。
     * @@param l_orderSpec - (株式注文内容)<BR>
     * 株式注文内容オブジェクト。
     * @@param l_lngOrderId - (注文ID)<BR>
     * 予約注文の注文ID。
     * @@param l_strTradingPassword - (取引パスワード)<BR>
     * 取引パスワード。
     * @@param l_requestAdaptor - (（連続）株式注文リクエストアダプタ)<BR>
     * （連続）株式注文リクエストアダプタ。
     * @@throws WEB3BaseException
     * @@roseuid 431D550701F3
     */
    protected void submitNewCashBasedOrder(
        WEB3GentradeSubAccount l_subAccount, 
        WEB3EquityNewCashBasedOrderSpec l_orderSpec, 
        long l_lngOrderId, 
        String l_strTradingPassword, 
        WEB3EquityOrderRequestAdapter l_requestAdaptor)
        throws WEB3BaseException 
    {
        final String STR_METHOD_NAME =" submitNewCashBasedOrder(" +
            "WEB3GentradeSubAccount, WEB3EquityNewCashBasedOrderSpec, long, String, WEB3EquityOrderRequestAdapter)";
        log.entering(STR_METHOD_NAME);
        
        WEB3ToSuccEquityOrderRequestAdapter l_toSuccEquityOrderRequestAdapter = null;
        if (l_requestAdaptor instanceof WEB3ToSuccEquityOrderRequestAdapter)
        {
            l_toSuccEquityOrderRequestAdapter = 
                (WEB3ToSuccEquityOrderRequestAdapter)l_requestAdaptor;
        }
        else
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80018,
                this.getClass().getName() + STR_METHOD_NAME, 
                "パラメータタイプ不正。");
        }
        
        //連続注文マネージャImpl.submit現物株式新規予約注文()をコールする。
        WEB3SuccCommonInfo l_succCommonInfo = null;
        WEB3SuccPriceAdjustmentValueInfo l_priceAdjustmentValueInfo = null;
        if (l_requestAdaptor.request instanceof WEB3SuccEquityBuyConfirmRequest)
        {
            WEB3SuccEquityBuyConfirmRequest l_buyConfirmRequest = 
                (WEB3SuccEquityBuyConfirmRequest)l_requestAdaptor.request;
            l_succCommonInfo = l_buyConfirmRequest.succCommonInfo;
            l_priceAdjustmentValueInfo = l_buyConfirmRequest.priceAdjustmentValueInfo;
        }
        else if (l_requestAdaptor.request instanceof WEB3SuccEquityBuyCompleteRequest)
        {
            WEB3SuccEquityBuyCompleteRequest l_buyCompleteRequest = 
                (WEB3SuccEquityBuyCompleteRequest)l_requestAdaptor.request;
            l_succCommonInfo = l_buyCompleteRequest.succCommonInfo;
            l_priceAdjustmentValueInfo = l_buyCompleteRequest.priceAdjustmentValueInfo;
        }
        else if (l_requestAdaptor.request instanceof WEB3SuccEquitySellConfirmRequest)
        {
            WEB3SuccEquitySellConfirmRequest l_sellConfirmRequest = 
                (WEB3SuccEquitySellConfirmRequest)l_requestAdaptor.request;
            l_succCommonInfo = l_sellConfirmRequest.succCommonInfo;
            l_priceAdjustmentValueInfo = l_sellConfirmRequest.priceAdjustmentValueInfo;
        }
        else if (l_requestAdaptor.request instanceof WEB3SuccEquitySellCompleteRequest)
        {
            WEB3SuccEquitySellCompleteRequest l_sellCompleteRequest = 
                (WEB3SuccEquitySellCompleteRequest)l_requestAdaptor.request;
            l_succCommonInfo = l_sellCompleteRequest.succCommonInfo;
            l_priceAdjustmentValueInfo = l_sellCompleteRequest.priceAdjustmentValueInfo;
        }
        else
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80018,
                this.getClass().getName() + STR_METHOD_NAME, 
                "パラメータタイプ不正。");
        }
        Double l_priceAdjustmentValue = null;
        if (l_priceAdjustmentValueInfo != null)
        {
            l_priceAdjustmentValue = new Double(l_priceAdjustmentValueInfo.getPriceAdjustmentValue());
        }
        WEB3ToSuccOrderManagerImpl l_toOrderManager = 
            (WEB3ToSuccOrderManagerImpl)WEB3TriggerOrderTradingModel.getOrderManager();
        l_toOrderManager.submitEqtypeNewOrder(
            l_subAccount,
            l_orderSpec,
            l_lngOrderId,
            l_strTradingPassword,
            l_succCommonInfo.succTradingType,
            l_priceAdjustmentValue,
            l_toSuccEquityOrderRequestAdapter.parentOrderUnit);
        
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * (validateリクエストデータat反対取引)<BR>
     * 反対取引指定時に固有の、リクエストデータのプロパティチェックを行う。<BR>
     * <BR>
     * １）　@連続注文マネージャImpl.is反対売買取引(<BR>
     * 　@　@　@引数のリクエスト.連続注文共通情報.連続注文取引区分, 引数の親注文の<BR>
     * 　@　@　@注文単位)==false<BR>
     * 　@　@　@（＝反対取引でない）場合は、<BR>
     * 　@　@　@何もせずにreturnする。<BR>
     * <BR>
     * 　@　@　@以外、以下の処理を行う。<BR>
     * <BR>
     * ２）　@引数のリクエストデータの型が"（連続）現物株式買付注文確認リクエスト"または<BR>
     * 　@　@　@"（連続）現物株式買付注文完了リクエスト"の場合、<BR>
     * 　@　@　@引数のりクスとデータ.銘柄コード≠引数の親注文の注文単位.銘柄IDに該当する<BR>
     * 　@　@　@株式銘柄.銘柄コード の場合は<BR>
     * 　@　@　@「反対取引時の銘柄指定が、親注文と不整合」の例外をthrowする。<BR>
     * 　@　@　@class: WEB3BusinessLayerException<BR>
     * 　@　@　@tag:   BUSINESS_ERROR_02250<BR>
     * <BR>
     * ３）　@引数の親注文の注文単位==現引現渡注文の場合、<BR>
     *      「親注文が現引現渡注文の場合、±指値は指定不可」の例外をthrowする。<BR>
     * 　@　@　@class: WEB3BusinessLayerException<BR>
     * 　@　@　@tag:   BUSINESS_ERROR_02291<BR>
     *
     * @@param l_request - (リクエストデータ)<BR>
     * リクエストデータ。
     * @@param l_parentOrderUnit - (親注文の注文単位)<BR>
     * 親注文の注文単位。
     * @@throws WEB3BaseException
     * @@roseuid 4332436000BD
     */
    protected void validateRequestDataAtReversingTrade(WEB3GenRequest l_request, EqTypeOrderUnit l_parentOrderUnit)
        throws WEB3BaseException 
    {
        final String STR_METHOD_NAME =" validateRequestDataAtReversingTrade(WEB3GenRequest, EqTypeOrderUnit)";
        log.entering(STR_METHOD_NAME);
        
        if (l_request == null)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME, 
                "リクエストが未指定(null)です。");
        }
        if (l_parentOrderUnit == null)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME, 
                "親注文の注文単位が未指定(null)です。");
        }
        //１）
        WEB3SuccCommonInfo l_succCommonInfo = null;
        WEB3SuccPriceAdjustmentValueInfo l_succPriceInfo = null;
        if (l_request instanceof WEB3SuccEquityBuyConfirmRequest)
        {
            WEB3SuccEquityBuyConfirmRequest l_buyConfirmRequest = 
                (WEB3SuccEquityBuyConfirmRequest)l_request;
            l_succCommonInfo = l_buyConfirmRequest.succCommonInfo;
            l_succPriceInfo = l_buyConfirmRequest.priceAdjustmentValueInfo;
        }
        else if (l_request instanceof WEB3SuccEquityBuyCompleteRequest)
        {
            WEB3SuccEquityBuyCompleteRequest l_buyCompleteRequest = 
                (WEB3SuccEquityBuyCompleteRequest)l_request;
            l_succCommonInfo = l_buyCompleteRequest.succCommonInfo;
            l_succPriceInfo = l_buyCompleteRequest.priceAdjustmentValueInfo;
        }
        else if (l_request instanceof WEB3SuccEquitySellConfirmRequest)
        {
            WEB3SuccEquitySellConfirmRequest l_sellConfirmRequest = 
                (WEB3SuccEquitySellConfirmRequest)l_request;
            l_succCommonInfo = l_sellConfirmRequest.succCommonInfo;
            l_succPriceInfo = l_sellConfirmRequest.priceAdjustmentValueInfo;
        }
        else if (l_request instanceof WEB3SuccEquitySellCompleteRequest)
        {
            WEB3SuccEquitySellCompleteRequest l_sellCompleteRequest = 
                (WEB3SuccEquitySellCompleteRequest)l_request;
            l_succCommonInfo = l_sellCompleteRequest.succCommonInfo;
            l_succPriceInfo = l_sellCompleteRequest.priceAdjustmentValueInfo;
        }
        else
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80018,
                this.getClass().getName() + STR_METHOD_NAME, 
                "パラメータタイプ不正。");
        }
        WEB3ToSuccOrderManagerImpl l_toOrderManager = 
            (WEB3ToSuccOrderManagerImpl)WEB3TriggerOrderTradingModel.getOrderManager();
        boolean l_blnReversingTrade = l_toOrderManager.isReversingTrade(
            l_succCommonInfo.succTradingType, 
            l_parentOrderUnit);

        if (!l_blnReversingTrade)
        {
            log.exiting(STR_METHOD_NAME);
            return;
        }

        //２）
        String l_strProductCode = null;
        if (l_request instanceof WEB3SuccEquityBuyConfirmRequest)
        {
            WEB3SuccEquityBuyConfirmRequest l_buyConfirmRequest = 
                (WEB3SuccEquityBuyConfirmRequest)l_request;
            l_strProductCode = l_buyConfirmRequest.productCode;
        }
        else if (l_request instanceof WEB3SuccEquityBuyCompleteRequest)
        {
            WEB3SuccEquityBuyCompleteRequest l_buyCompleteRequest = 
                (WEB3SuccEquityBuyCompleteRequest)l_request;
            l_strProductCode = l_buyCompleteRequest.productCode;
        }
        if (l_strProductCode != null)
        {
            EqTypeProduct l_product = (EqTypeProduct)l_parentOrderUnit.getProduct();
            if (l_product == null || !l_strProductCode.equals(l_product.getProductCode()))
            {
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02250,
                    getClass().getName() + STR_METHOD_NAME, null);
            }
        }
        
        //３）
        if (OrderCategEnum.SWAP_MARGIN.equals(l_parentOrderUnit.getOrderCateg()))
        {
            if (l_succPriceInfo != null)
            {
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02291,
                    getClass().getName() + STR_METHOD_NAME, null);
            }
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * (validate連続注文最大設定数)<BR>
     * 連続注文の最大設定数を超過してしまわないかどうかをチェックする。<BR>
     * <BR>
     * 連続注文マネージャImpl.validate連続注文最大設定数(引数の親注文の注文単位)に<BR>
     * delegateする。<BR>
     * @@param l_parentOrderUnit - (親注文の注文単位)<BR>
     * 親注文の注文単位。
     * @@throws WEB3BaseException
     * @@roseuid 433B833C017F
     */
    protected void validateSuccOrderMaxQuantity(EqTypeOrderUnit l_parentOrderUnit)
        throws WEB3BaseException 
    {
        final String STR_METHOD_NAME =" validateSuccOrderMaxQuantity(EqTypeOrderUnit)";
        log.entering(STR_METHOD_NAME);
        
        WEB3ToSuccOrderManagerImpl l_toOrderManager = 
            (WEB3ToSuccOrderManagerImpl)WEB3TriggerOrderTradingModel.getOrderManager();
        l_toOrderManager.validateSuccOrderMaxQuantity(l_parentOrderUnit);
        
        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (notify予約注文登録)<BR>
     * 予約注文の登録をルールエンジンサーバに通知する。<BR>
     * <BR>
     * １）　@予約注文単位の取得<BR>
     * 　@連続注文マネージャ.get株式予約注文単位()にて、<BR>
     * 　@予約注文単位を取得する。<BR>
     * <BR>
     * 　@[get株式予約注文単位()に指定する引数]<BR>
     * 　@　@注文ID：　@子注文の注文ID<BR>
     * <BR>
     * ２）　@ルールエンジンに注文の登録を通知する。<BR>
     * 　@拡張株式注文マネージャ.notifyルールエンジンサーバ()<BR>
     * 　@をコールする。<BR>
     * <BR>
     * 　@[notifyルールエンジンサーバ()に指定する引数]<BR>
     * 　@　@注文単位：　@１）の戻り値<BR>
     * 　@　@処理：　@NEW_OPEN_CONTRACT_ORDER<BR>
     * @@param l_lngSubOrderId - (子注文の注文ID)<BR>
     * 子注文の注文ID。<BR>
     * @@throws WEB3BaseException
     * @@roseuid 435EDBFC0375
     */
    protected void notifyRsvOrderRegister(long l_lngSubOrderId) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " notifyRsvOrderRegister(long)";
        log.entering(STR_METHOD_NAME);
        
        WEB3ToSuccOrderManagerImpl l_toOrderManager = 
            (WEB3ToSuccOrderManagerImpl) WEB3TriggerOrderTradingModel.getOrderManager();
        WEB3ToSuccEqTypeOrderUnitImpl l_orderUnit = null;
        try
        {
            //１）予約注文単位の取得
            l_orderUnit = l_toOrderManager.getReserveEqtypeOrderUnit(l_lngSubOrderId);
        }
        catch (NotFoundException l_nft)
        {
            log.error(STR_METHOD_NAME, l_nft);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_nft.getMessage(),
                l_nft);
        }

        //２）ルールエンジンに注文の登録を通知する
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        TradingModule l_tradingModule =
            l_finApp.getTradingModule(ProductTypeEnum.EQUITY);

        WEB3EquityOrderManager l_orderMgr =
            (WEB3EquityOrderManager) l_tradingModule.getOrderManager();
        
        l_orderMgr.notifyRLS(l_orderUnit, OrderManagerPersistenceContext.NEW_OPEN_CONTRACT_ORDER);
            
        log.exiting(STR_METHOD_NAME);        
    }

    /**
     * （set単価）<BR>
     * 引数のレスポンス．調整後単価に単価を設定する。<BR>
     * <BR>
     * １）リクエスト.単価調整値情報≠null（±指値指定）の場合<BR>
     * 　@　@レスポンス．調整後単価に、引数のリクエストアダプタ．get単価()の戻り値をセットする。<BR>
     * <BR>
     * ２）上記以外の場合、<BR>
     * 　@　@何もせずリターンする。<BR>
     * <BR>
     * @@param l_requestAdaptor - (株式注文リクエストアダプタ)
     * @@param l_response - (レスポンス)
     * @@throws WEB3BaseException
     */
    protected void setPrice(
        WEB3EquityOrderRequestAdapter l_requestAdaptor,
        WEB3GenResponse l_response)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " setPrice(WEB3EquityOrderRequestAdapter, WEB3GenResponse)";
        log.entering(STR_METHOD_NAME);

        if (l_requestAdaptor == null || l_response == null)
        {
            log.debug("パラメータ値不正。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME);
        }

        //    １）リクエスト.単価調整値情報≠null（±指値指定）の場合 
        //  　@　@レスポンス．調整後単価に、引数のリクエストアダプタ．get単価()の戻り値をセットする
        WEB3SuccPriceAdjustmentValueInfo l_priceAdjustmentValueInfo = null;
        if (l_requestAdaptor.request instanceof WEB3SuccEquityBuyConfirmRequest)
        {
            WEB3SuccEquityBuyConfirmRequest l_buyConfirmRequest =
                (WEB3SuccEquityBuyConfirmRequest)l_requestAdaptor.request;

            WEB3SuccEquityBuyConfirmResponse l_equityBuyConfirmResponse =
                (WEB3SuccEquityBuyConfirmResponse)l_response;

            l_priceAdjustmentValueInfo = l_buyConfirmRequest.priceAdjustmentValueInfo;
            if (l_priceAdjustmentValueInfo != null)
            {
                l_equityBuyConfirmResponse.afterAdjustmentPrice =
                    WEB3StringTypeUtility.formatNumber(l_requestAdaptor.getPrice());
            }
        }
        else if (l_requestAdaptor.request instanceof WEB3SuccEquitySellConfirmRequest)
        {
            WEB3SuccEquitySellConfirmRequest l_sellConfirmRequest =
                (WEB3SuccEquitySellConfirmRequest)l_requestAdaptor.request;

            WEB3SuccEquitySellConfirmResponse l_equitySellConfirmResponse =
                (WEB3SuccEquitySellConfirmResponse)l_response;

            l_priceAdjustmentValueInfo = l_sellConfirmRequest.priceAdjustmentValueInfo;
            if (l_priceAdjustmentValueInfo != null)
            {
                l_equitySellConfirmResponse.afterAdjustmentPrice =
                    WEB3StringTypeUtility.formatNumber(l_requestAdaptor.getPrice());
            }
        }

        //２）上記以外の場合、
        //何もせずリターンする。
        log.exiting(STR_METHOD_NAME); 
    }
}
@
