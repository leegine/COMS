head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.38.22;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3FeqAcceptUpdateServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 外国株式受付更新サービスImpl(WEB3FeqAcceptUpdateServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/07/19 呉艶飛(中訊) 新規作成
                 : 2005/07/26 王煜(中訊) レビュー
                 : 2006/12/19 徐宏偉(中訊) モデル　@No.314
Revesion History : 2008/10/02 大澤(SRA) 【外国株式】仕様変更管理台帳（モデル）No.480
*/
package webbroker3.feq.service.delegate.stdimpls;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderExpirationStatusEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderStatusEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule;
import com.fitechlabs.xtrade.plugin.tc.gentrade.market.messages.DefaultCancelOrderAcceptedMarketResponseMessage;
import com.fitechlabs.xtrade.plugin.tc.gentrade.market.messages.DefaultCancelOrderRejectedMarketResponseMessage;
import com.fitechlabs.xtrade.plugin.tc.gentrade.market.messages.DefaultCancelOrderSentMarketResponseMessage;
import com.fitechlabs.xtrade.plugin.tc.gentrade.market.messages.DefaultChangeOrderAcceptedMarketResponseMessage;
import com.fitechlabs.xtrade.plugin.tc.gentrade.market.messages.DefaultChangeOrderRejectedMarketResponseMessage;
import com.fitechlabs.xtrade.plugin.tc.gentrade.market.messages.DefaultChangeOrderSentMarketResponseMessage;
import com.fitechlabs.xtrade.plugin.tc.gentrade.market.messages.DefaultNewOrderAcceptedMarketResponseMessage;
import com.fitechlabs.xtrade.plugin.tc.gentrade.market.messages.DefaultNewOrderRejectedMarketResponseMessage;
import com.fitechlabs.xtrade.plugin.tc.gentrade.market.messages.DefaultNewOrderSentMarketResponseMessage;
import com.fitechlabs.xtrade.plugin.tc.gentrade.market.messages.DefaultOrderInvalidatedMarketResponseMessage;
import com.fitechlabs.xtrade.plugin.tc.gentrade.market.messages.DefaultUndoOrderInvalidatedMarketResponseMessage;
import com.fitechlabs.xtrade.plugin.tc.gentrade.market.messages.MarketResponseMessage;
import com.fitechlabs.xtrade.plugin.tc.xbfeq.FeqMarketResponseReceiverCallbackService;
import com.fitechlabs.xtrade.plugin.tc.xbfeq.FeqOrderUnit;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.feq.WEB3FeqOrderAcceptUpdateInterceptor;
import webbroker3.feq.WEB3FeqOrderManager;
import webbroker3.feq.WEB3FeqOrderUnit;
import webbroker3.feq.define.WEB3FeqAcceptTypeDef;
import webbroker3.feq.define.WEB3FeqExecStatusTypeDef;
import webbroker3.feq.service.delegate.WEB3FeqAcceptUpdateService;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.tradingpower.WEB3TPTradingPowerReCalcService;
import webbroker3.util.WEB3LogUtility;

/**
 * (外国株式受付更新サービスImpl) <BR>
 * 外国株式受付更新サービス実装クラス <BR>
 * TransactionalInterceptor.TX_JOIN_EXISTING <BR>
 *  <BR>
 * 外国株式受付更新サービスインタセプタをPluginする。 <BR>
 * <BR>
 * @@ author 呉艶飛 <BR>
 * @@ version 1.0<BR>
 */
public class WEB3FeqAcceptUpdateServiceImpl implements WEB3FeqAcceptUpdateService 
{
    /**
     * ログ出力ユーティリティ。
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3FeqAcceptUpdateServiceImpl.class);
    
    /**
     * @@roseuid 42CE39F4029F
     */
    public WEB3FeqAcceptUpdateServiceImpl() 
    {
     
    }
    
    /**
     * (update受付) <BR>
     * 注文受付更新処理を行う。 <BR>
     *  <BR>
     * シーケンス図 <BR>
     * 「（受付共通）update受付」参照。 <BR>
     * @@param l_marketResponseMessage - (市場レスポンスメッセージ)
     * @@throws WEB3BaseException
     * @@roseuid 42A578A601BC
     */
    public void updateAccept(MarketResponseMessage l_marketResponseMessage) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " updateAccept(MarketResponseMessage l_marketResponseMessage)";
        log.entering(STR_METHOD_NAME);
        
        if (l_marketResponseMessage == null)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        //1.1getOrderId( )
        long l_lngOrderId = l_marketResponseMessage.getOrderId();
        //1.2更新インタセプタを生成する。
        WEB3FeqOrderAcceptUpdateInterceptor l_updateInterception = 
            new WEB3FeqOrderAcceptUpdateInterceptor(l_marketResponseMessage);
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.FOREIGN_EQUITY);
        WEB3FeqOrderManager l_orderManger = (WEB3FeqOrderManager)l_tradingModule.getOrderManager();
        FeqMarketResponseReceiverCallbackService l_marketResponseReceiverCallbackService =
            (FeqMarketResponseReceiverCallbackService)
                l_tradingModule.getMarketAdapter().getMarketResponseReceiverCallbackService();
        
        //1.3更新イベントインタセプタを注文マネージャにセットする。 
        l_orderManger.setThreadLocalPersistenceEventInterceptor(l_updateInterception);
        
        //1.4注文単位を取得する。
        WEB3FeqOrderUnit l_feqOrderUnit = (WEB3FeqOrderUnit)l_orderManger.getOrderUnitByOrderId(l_lngOrderId);
        
        //1.5受付更新処理を行う。 
        l_marketResponseReceiverCallbackService.process(l_marketResponseMessage);
        
        //1.6円貨決済かを判定する。
        boolean l_blnIsJpySettle = l_feqOrderUnit.isJpySettle();
        
        //1.7買付かを判定する。 
        boolean l_blnIsBuy = l_feqOrderUnit.isBuy();
        
        //概算受渡代金の再計算が必要な場合
        // 注文が"一部約定" Or "全部約定"かつ、
        // 市場レスポンスメッセージ instanceof
        // 　@DefaultChangeOrderSentMarketResponseMessage（訂正受付の取消） Or
        // 　@DefaultCancelOrderSentMarketResponseMessage（取消受付の取消） Or
        // 　@DefaultUndoOrderInvalidatedMarketResponseMessage（失効取消）
        if ((WEB3FeqExecStatusTypeDef.EXEC_TYPE_ONE_COMPLETE.equals(l_feqOrderUnit.getExecStatusDiv()) ||
        	WEB3FeqExecStatusTypeDef.EXEC_TYPE_ALL_COMPLETE.equals(l_feqOrderUnit.getExecStatusDiv())) &&
        	(l_marketResponseMessage instanceof DefaultChangeOrderSentMarketResponseMessage ||
        	l_marketResponseMessage instanceof DefaultCancelOrderSentMarketResponseMessage ||
        	l_marketResponseMessage instanceof DefaultUndoOrderInvalidatedMarketResponseMessage))
        {
        	// get注文単位ByOrderId
        	WEB3FeqOrderUnit l_updatedFeqOrderUnit = 
        		(WEB3FeqOrderUnit)l_orderManger.getOrderUnitByOrderId(l_lngOrderId);
        	
        	// update概算受渡代金
        	l_orderManger.updateEstimatedPrice(
        			l_updatedFeqOrderUnit, 
        			WEB3GentradeTradingTimeManagement.getOrderBizDate());
        }
        
        //1.8余力更新が必要な場合、再計算処理をコールする。 
        //[余力更新が必要な場合の判定] 
        //買付注文（is買付() == true） && 
        //円貨決済（is円貨決済() == true） && 
        //市場レスポンスメッセージの型が以下に該当しない場合
        //　@・DefaultNewOrderAcceptedMarketResponseMessage（注文受付）
        //　@・DefaultCancelOrderRejectedMarketResponseMessage（取消エラー）
        if (l_blnIsJpySettle && l_blnIsBuy 
            && !(l_marketResponseMessage instanceof DefaultNewOrderAcceptedMarketResponseMessage
                || l_marketResponseMessage instanceof DefaultCancelOrderRejectedMarketResponseMessage))
        {
            //1.8.1補助口座を取得する。
            SubAccount l_subAccount = l_feqOrderUnit.getSubAccount();
            
            WEB3TPTradingPowerReCalcService l_tpTradingPowerReCalcService =
                (WEB3TPTradingPowerReCalcService) Services.getService(WEB3TPTradingPowerReCalcService.class);
            //1.8.2余力再計算を実施する。
            l_tpTradingPowerReCalcService.reCalcTradingPower((WEB3GentradeSubAccount)l_subAccount);
        }                   
    }

    /**
     * (get市場レスポンスメッセージ )<BR>
     * 以下の通り、変更後受付区分に対応する市場レスポンスメッセージを生成し返却する。<BR>
     * <BR>
     * ○01：注文受付済<BR>
     * 　@DefaultNewOrderAcceptedMarketResponseMessage<BR>
     * <BR>
     * 　@[コンストラクタの引数]<BR>
     * 　@orderId： 注文ＩＤ<BR>
     * <BR>
     * ○02：注文受付エラー <BR>
     * 　@DefaultNewOrderRejectedMarketResponseMessage<BR>
     * <BR>
     * 　@[コンストラクタの引数] <BR>
     * 　@orderId： 注文ＩＤ<BR>
     * <BR>
     * ○03：注文受付済取消<BR>
     * 　@－（注文単位.getExpirationStatus() == 3：マーケット拒否）の場合<BR>
     * 　@　@　@DefaultUndoOrderInvalidatedMarketResponseMessage<BR>
     * <BR>
     * 　@　@　@[コンストラクタの引数]<BR>
     * 　@　@　@orderId： 注文ＩＤ<BR>
     * <BR>
     * 　@－（注文単位.getOrderStatus() == 3:発注済（新規注文））の場合<BR>
     * 　@　@　@DefaultNewOrderSentMarketResponseMessage<BR>
     * <BR>
     * 　@　@　@[コンストラクタの引数] <BR>
     * 　@　@　@orderId： 注文ＩＤ<BR>
     * <BR>
     * 　@－（注文単位.getOrderStatus() == 10:発注済（変更注文））の場合<BR>
     * 　@　@　@DefaultChangeOrderSentMarketResponseMessage<BR>
     * <BR>
     * 　@　@　@[コンストラクタの引数]<BR>
     * 　@　@　@orderId： 注文ＩＤ<BR>
     * <BR>
     * 　@－（注文単位.getOrderStatus() == 14:発注済（取消注文））の場合<BR>
     * 　@　@　@DefaultCancelOrderSentMarketResponseMessage<BR>
     * <BR>
     * 　@　@　@[コンストラクタの引数] <BR>
     * 　@　@　@orderId： 注文ＩＤ<BR>
     * <BR>
     * ○11：訂正済 <BR>
     * 　@DefaultChangeOrderAcceptedMarketResponseMessage<BR>
     * <BR>
     * 　@[コンストラクタの引数] <BR>
     * 　@orderId： 注文ＩＤ<BR>
     * <BR>
     * ○12：訂正エラー <BR>
     * 　@DefaultChangeOrderRejectedMarketResponseMessage<BR>
     * <BR>
     * 　@[コンストラクタの引数] <BR>
     * 　@orderId： 注文ＩＤ<BR>
     * <BR>
     * ○21：取消済 <BR>
     * 　@DefaultCancelOrderAcceptedMarketResponseMessage<BR>
     * <BR>
     * 　@[コンストラクタの引数] <BR>
     * 　@orderId： 注文ＩＤ<BR>
     * <BR>
     * ○22：取消エラー <BR>
     * 　@DefaultCancelOrderRejectedMarketResponseMessage<BR>
     * <BR>
     * 　@[コンストラクタの引数] <BR>
     * 　@orderId： 注文ＩＤ<BR>
     * <BR>
     * ○31：出来ず <BR>
     * 　@DefaultOrderInvalidatedMarketResponseMessage<BR>
     * <BR>
     * 　@[コンストラクタの引数] <BR>
     * 　@orderId： 注文ＩＤ<BR>
     * <BR>
     * @@param l_lngOrderId - (注文ＩＤ)<BR>
     * 注文ＩＤ<BR>
     * @@param l_strAfterChangeAcceptDiv - (変更後受付区分)<BR>
     * 変更後受付区分<BR>
     * <BR>
     * 01：注文受付済 <BR>
     * 02：注文受付エラー <BR>
     * 03：注文受付済取消<BR>
     * <BR>
     * 11：訂正済 <BR>
     * 12：訂正エラー<BR>
     * <BR>
     * 21：取消済 <BR>
     * 22：取消エラー<BR>
     * <BR>
     * 31：出来ず<BR>
     * @@return MarketResponseMessage
     */
    public MarketResponseMessage getMarketResponseMessage(long l_lngOrderId, String l_strAfterChangeAcceptDiv)
    {
        final String STR_METHOD_NAME = "getMarketResponseMessage(long, String)";
        log.entering(STR_METHOD_NAME);

        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        WEB3FeqOrderManager l_feqOrderManager =
            (WEB3FeqOrderManager)l_finApp.getTradingModule(
                ProductTypeEnum.FOREIGN_EQUITY).getOrderManager();
        FeqOrderUnit l_feqOrderUnit = null;
        try
        {
            l_feqOrderUnit = l_feqOrderManager.getOrderUnitByOrderId(l_lngOrderId);
        }
        catch (WEB3BaseException l_ex)
        {
            log.error("テーブルに該当するデータがありません。", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        MarketResponseMessage l_marketResponseMessage = null;

        //01：注文受付済
        //DefaultNewOrderAcceptedMarketResponseMessage
        if (WEB3FeqAcceptTypeDef.ORDER_ACCEPT_COMPLETE.equals(l_strAfterChangeAcceptDiv))
        {
            l_marketResponseMessage = new DefaultNewOrderAcceptedMarketResponseMessage(l_lngOrderId);
        }

        //02：注文受付エラー
        //DefaultNewOrderRejectedMarketResponseMessage
        else if (WEB3FeqAcceptTypeDef.ORDER_ACCEPT_ERROR.equals(l_strAfterChangeAcceptDiv))
        {
            l_marketResponseMessage = new DefaultNewOrderRejectedMarketResponseMessage(l_lngOrderId);
        }

        //03：注文受付済取消
        else if (WEB3FeqAcceptTypeDef.ORDER_ACCEPT_COMPLETE_CANCEL.equals(l_strAfterChangeAcceptDiv))
        {
            //－（注文単位.getExpirationStatus() == 3：マーケット拒否）の場合
            //DefaultUndoOrderInvalidatedMarketResponseMessage
            if (OrderExpirationStatusEnum.INVALIDATED_BY_MKT.equals(l_feqOrderUnit.getExpirationStatus()))
            {
                l_marketResponseMessage = new DefaultUndoOrderInvalidatedMarketResponseMessage(l_lngOrderId);
            }

            //－（注文単位.getOrderStatus() == 3:発注済（新規注文））の場合
            //DefaultNewOrderSentMarketResponseMessage
            else if (OrderStatusEnum.ORDERED.equals(l_feqOrderUnit.getOrderStatus()))
            {
                l_marketResponseMessage = new DefaultNewOrderSentMarketResponseMessage(l_lngOrderId);
            }

            //－（注文単位.getOrderStatus() == 10:発注済（変更注文））の場合
            //DefaultChangeOrderSentMarketResponseMessage
            else if (OrderStatusEnum.MODIFIED.equals(l_feqOrderUnit.getOrderStatus()))
            {
                l_marketResponseMessage = new DefaultChangeOrderSentMarketResponseMessage(l_lngOrderId);
            }

            //－（注文単位.getOrderStatus() == 14:発注済（取消注文））の場合
            //DefaultCancelOrderSentMarketResponseMessage
            else if (OrderStatusEnum.CANCELLED.equals(l_feqOrderUnit.getOrderStatus()))
            {
                l_marketResponseMessage = new DefaultCancelOrderSentMarketResponseMessage(l_lngOrderId);
            }
        }

        //11：訂正済
        //DefaultChangeOrderAcceptedMarketResponseMessage
        else if (WEB3FeqAcceptTypeDef.CHANGED.equals(l_strAfterChangeAcceptDiv))
        {
            l_marketResponseMessage = new DefaultChangeOrderAcceptedMarketResponseMessage(l_lngOrderId);
        }

        //12：訂正エラー
        //DefaultChangeOrderRejectedMarketResponseMessage
        else if (WEB3FeqAcceptTypeDef.CHANGE_ERROR.equals(l_strAfterChangeAcceptDiv))
        {
            l_marketResponseMessage = new DefaultChangeOrderRejectedMarketResponseMessage(l_lngOrderId);
        }

        //21：取消済
        //DefaultCancelOrderAcceptedMarketResponseMessage
        else if (WEB3FeqAcceptTypeDef.CANCEL.equals(l_strAfterChangeAcceptDiv))
        {
            l_marketResponseMessage = new DefaultCancelOrderAcceptedMarketResponseMessage(l_lngOrderId);
        }

        //22：取消エラー
        //DefaultCancelOrderRejectedMarketResponseMessage
        else if (WEB3FeqAcceptTypeDef.CANCEL_ERROR.equals(l_strAfterChangeAcceptDiv))
        {
            l_marketResponseMessage = new DefaultCancelOrderRejectedMarketResponseMessage(l_lngOrderId);
        }

        //31：出来ず
        //DefaultOrderInvalidatedMarketResponseMessage
        else if (WEB3FeqAcceptTypeDef.NOT_EXECUTED.equals(l_strAfterChangeAcceptDiv))
        {
            l_marketResponseMessage = new DefaultOrderInvalidatedMarketResponseMessage(l_lngOrderId);
        }

        log.exiting(STR_METHOD_NAME);
        return l_marketResponseMessage;
    }

}
@
