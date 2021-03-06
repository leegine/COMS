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
Copyright        : ()åa¤ Ø\[VVXeæñ
File Name        : O®ótXVT[rXImpl(WEB3FeqAcceptUpdateServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/07/19 àò(u) VKì¬
                 : 2005/07/26 ¤ûU(u) r[
                 : 2006/12/19 GÌ(u) f@@No.314
Revesion History : 2008/10/02 åàV(SRA) yO®zdlÏXÇä ifjNo.480
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
 * (O®ótXVT[rXImpl) <BR>
 * O®ótXVT[rXÀNX <BR>
 * TransactionalInterceptor.TX_JOIN_EXISTING <BR>
 *  <BR>
 * O®ótXVT[rXC^Zv^ðPlugin·éB <BR>
 * <BR>
 * @@ author àò <BR>
 * @@ version 1.0<BR>
 */
public class WEB3FeqAcceptUpdateServiceImpl implements WEB3FeqAcceptUpdateService 
{
    /**
     * OoÍ[eBeBB
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
     * (updateót) <BR>
     * ¶ótXVðs¤B <BR>
     *  <BR>
     * V[PX} <BR>
     * uiót¤ÊjupdateótvQÆB <BR>
     * @@param l_marketResponseMessage - (sêX|XbZ[W)
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
        //1.2XVC^Zv^ð¶¬·éB
        WEB3FeqOrderAcceptUpdateInterceptor l_updateInterception = 
            new WEB3FeqOrderAcceptUpdateInterceptor(l_marketResponseMessage);
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.FOREIGN_EQUITY);
        WEB3FeqOrderManager l_orderManger = (WEB3FeqOrderManager)l_tradingModule.getOrderManager();
        FeqMarketResponseReceiverCallbackService l_marketResponseReceiverCallbackService =
            (FeqMarketResponseReceiverCallbackService)
                l_tradingModule.getMarketAdapter().getMarketResponseReceiverCallbackService();
        
        //1.3XVCxgC^Zv^ð¶}l[WÉZbg·éB 
        l_orderManger.setThreadLocalPersistenceEventInterceptor(l_updateInterception);
        
        //1.4¶PÊðæ¾·éB
        WEB3FeqOrderUnit l_feqOrderUnit = (WEB3FeqOrderUnit)l_orderManger.getOrderUnitByOrderId(l_lngOrderId);
        
        //1.5ótXVðs¤B 
        l_marketResponseReceiverCallbackService.process(l_marketResponseMessage);
        
        //1.6~ÝÏ©ð»è·éB
        boolean l_blnIsJpySettle = l_feqOrderUnit.isJpySettle();
        
        //1.7t©ð»è·éB 
        boolean l_blnIsBuy = l_feqOrderUnit.isBuy();
        
        //TZónãàÌÄvZªKvÈê
        // ¶ª"êñè" Or "Sñè"©ÂA
        // sêX|XbZ[W instanceof
        // @@DefaultChangeOrderSentMarketResponseMessageiù³ótÌæÁj Or
        // @@DefaultCancelOrderSentMarketResponseMessageiæÁótÌæÁj Or
        // @@DefaultUndoOrderInvalidatedMarketResponseMessagei¸øæÁj
        if ((WEB3FeqExecStatusTypeDef.EXEC_TYPE_ONE_COMPLETE.equals(l_feqOrderUnit.getExecStatusDiv()) ||
        	WEB3FeqExecStatusTypeDef.EXEC_TYPE_ALL_COMPLETE.equals(l_feqOrderUnit.getExecStatusDiv())) &&
        	(l_marketResponseMessage instanceof DefaultChangeOrderSentMarketResponseMessage ||
        	l_marketResponseMessage instanceof DefaultCancelOrderSentMarketResponseMessage ||
        	l_marketResponseMessage instanceof DefaultUndoOrderInvalidatedMarketResponseMessage))
        {
        	// get¶PÊByOrderId
        	WEB3FeqOrderUnit l_updatedFeqOrderUnit = 
        		(WEB3FeqOrderUnit)l_orderManger.getOrderUnitByOrderId(l_lngOrderId);
        	
        	// updateTZónãà
        	l_orderManger.updateEstimatedPrice(
        			l_updatedFeqOrderUnit, 
        			WEB3GentradeTradingTimeManagement.getOrderBizDate());
        }
        
        //1.8]ÍXVªKvÈêAÄvZðR[·éB 
        //[]ÍXVªKvÈêÌ»è] 
        //t¶iist() == truej && 
        //~ÝÏiis~ÝÏ() == truej && 
        //sêX|XbZ[WÌ^ªÈºÉYµÈ¢ê
        //@@EDefaultNewOrderAcceptedMarketResponseMessagei¶ótj
        //@@EDefaultCancelOrderRejectedMarketResponseMessageiæÁG[j
        if (l_blnIsJpySettle && l_blnIsBuy 
            && !(l_marketResponseMessage instanceof DefaultNewOrderAcceptedMarketResponseMessage
                || l_marketResponseMessage instanceof DefaultCancelOrderRejectedMarketResponseMessage))
        {
            //1.8.1âûÀðæ¾·éB
            SubAccount l_subAccount = l_feqOrderUnit.getSubAccount();
            
            WEB3TPTradingPowerReCalcService l_tpTradingPowerReCalcService =
                (WEB3TPTradingPowerReCalcService) Services.getService(WEB3TPTradingPowerReCalcService.class);
            //1.8.2]ÍÄvZðÀ{·éB
            l_tpTradingPowerReCalcService.reCalcTradingPower((WEB3GentradeSubAccount)l_subAccount);
        }                   
    }

    /**
     * (getsêX|XbZ[W )<BR>
     * ÈºÌÊèAÏXãótæªÉÎ·ésêX|XbZ[Wð¶¬µÔp·éB<BR>
     * <BR>
     * 01F¶ótÏ<BR>
     * @@DefaultNewOrderAcceptedMarketResponseMessage<BR>
     * <BR>
     * @@[RXgN^Ìø]<BR>
     * @@orderIdF ¶hc<BR>
     * <BR>
     * 02F¶ótG[ <BR>
     * @@DefaultNewOrderRejectedMarketResponseMessage<BR>
     * <BR>
     * @@[RXgN^Ìø] <BR>
     * @@orderIdF ¶hc<BR>
     * <BR>
     * 03F¶ótÏæÁ<BR>
     * @@|i¶PÊ.getExpirationStatus() == 3F}[PbgÛjÌê<BR>
     * @@@@@@DefaultUndoOrderInvalidatedMarketResponseMessage<BR>
     * <BR>
     * @@@@@@[RXgN^Ìø]<BR>
     * @@@@@@orderIdF ¶hc<BR>
     * <BR>
     * @@|i¶PÊ.getOrderStatus() == 3:­ÏiVK¶jjÌê<BR>
     * @@@@@@DefaultNewOrderSentMarketResponseMessage<BR>
     * <BR>
     * @@@@@@[RXgN^Ìø] <BR>
     * @@@@@@orderIdF ¶hc<BR>
     * <BR>
     * @@|i¶PÊ.getOrderStatus() == 10:­ÏiÏX¶jjÌê<BR>
     * @@@@@@DefaultChangeOrderSentMarketResponseMessage<BR>
     * <BR>
     * @@@@@@[RXgN^Ìø]<BR>
     * @@@@@@orderIdF ¶hc<BR>
     * <BR>
     * @@|i¶PÊ.getOrderStatus() == 14:­ÏiæÁ¶jjÌê<BR>
     * @@@@@@DefaultCancelOrderSentMarketResponseMessage<BR>
     * <BR>
     * @@@@@@[RXgN^Ìø] <BR>
     * @@@@@@orderIdF ¶hc<BR>
     * <BR>
     * 11Fù³Ï <BR>
     * @@DefaultChangeOrderAcceptedMarketResponseMessage<BR>
     * <BR>
     * @@[RXgN^Ìø] <BR>
     * @@orderIdF ¶hc<BR>
     * <BR>
     * 12Fù³G[ <BR>
     * @@DefaultChangeOrderRejectedMarketResponseMessage<BR>
     * <BR>
     * @@[RXgN^Ìø] <BR>
     * @@orderIdF ¶hc<BR>
     * <BR>
     * 21FæÁÏ <BR>
     * @@DefaultCancelOrderAcceptedMarketResponseMessage<BR>
     * <BR>
     * @@[RXgN^Ìø] <BR>
     * @@orderIdF ¶hc<BR>
     * <BR>
     * 22FæÁG[ <BR>
     * @@DefaultCancelOrderRejectedMarketResponseMessage<BR>
     * <BR>
     * @@[RXgN^Ìø] <BR>
     * @@orderIdF ¶hc<BR>
     * <BR>
     * 31Fo¸ <BR>
     * @@DefaultOrderInvalidatedMarketResponseMessage<BR>
     * <BR>
     * @@[RXgN^Ìø] <BR>
     * @@orderIdF ¶hc<BR>
     * <BR>
     * @@param l_lngOrderId - (¶hc)<BR>
     * ¶hc<BR>
     * @@param l_strAfterChangeAcceptDiv - (ÏXãótæª)<BR>
     * ÏXãótæª<BR>
     * <BR>
     * 01F¶ótÏ <BR>
     * 02F¶ótG[ <BR>
     * 03F¶ótÏæÁ<BR>
     * <BR>
     * 11Fù³Ï <BR>
     * 12Fù³G[<BR>
     * <BR>
     * 21FæÁÏ <BR>
     * 22FæÁG[<BR>
     * <BR>
     * 31Fo¸<BR>
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
            log.error("e[uÉY·éf[^ª èÜ¹ñB", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        MarketResponseMessage l_marketResponseMessage = null;

        //01F¶ótÏ
        //DefaultNewOrderAcceptedMarketResponseMessage
        if (WEB3FeqAcceptTypeDef.ORDER_ACCEPT_COMPLETE.equals(l_strAfterChangeAcceptDiv))
        {
            l_marketResponseMessage = new DefaultNewOrderAcceptedMarketResponseMessage(l_lngOrderId);
        }

        //02F¶ótG[
        //DefaultNewOrderRejectedMarketResponseMessage
        else if (WEB3FeqAcceptTypeDef.ORDER_ACCEPT_ERROR.equals(l_strAfterChangeAcceptDiv))
        {
            l_marketResponseMessage = new DefaultNewOrderRejectedMarketResponseMessage(l_lngOrderId);
        }

        //03F¶ótÏæÁ
        else if (WEB3FeqAcceptTypeDef.ORDER_ACCEPT_COMPLETE_CANCEL.equals(l_strAfterChangeAcceptDiv))
        {
            //|i¶PÊ.getExpirationStatus() == 3F}[PbgÛjÌê
            //DefaultUndoOrderInvalidatedMarketResponseMessage
            if (OrderExpirationStatusEnum.INVALIDATED_BY_MKT.equals(l_feqOrderUnit.getExpirationStatus()))
            {
                l_marketResponseMessage = new DefaultUndoOrderInvalidatedMarketResponseMessage(l_lngOrderId);
            }

            //|i¶PÊ.getOrderStatus() == 3:­ÏiVK¶jjÌê
            //DefaultNewOrderSentMarketResponseMessage
            else if (OrderStatusEnum.ORDERED.equals(l_feqOrderUnit.getOrderStatus()))
            {
                l_marketResponseMessage = new DefaultNewOrderSentMarketResponseMessage(l_lngOrderId);
            }

            //|i¶PÊ.getOrderStatus() == 10:­ÏiÏX¶jjÌê
            //DefaultChangeOrderSentMarketResponseMessage
            else if (OrderStatusEnum.MODIFIED.equals(l_feqOrderUnit.getOrderStatus()))
            {
                l_marketResponseMessage = new DefaultChangeOrderSentMarketResponseMessage(l_lngOrderId);
            }

            //|i¶PÊ.getOrderStatus() == 14:­ÏiæÁ¶jjÌê
            //DefaultCancelOrderSentMarketResponseMessage
            else if (OrderStatusEnum.CANCELLED.equals(l_feqOrderUnit.getOrderStatus()))
            {
                l_marketResponseMessage = new DefaultCancelOrderSentMarketResponseMessage(l_lngOrderId);
            }
        }

        //11Fù³Ï
        //DefaultChangeOrderAcceptedMarketResponseMessage
        else if (WEB3FeqAcceptTypeDef.CHANGED.equals(l_strAfterChangeAcceptDiv))
        {
            l_marketResponseMessage = new DefaultChangeOrderAcceptedMarketResponseMessage(l_lngOrderId);
        }

        //12Fù³G[
        //DefaultChangeOrderRejectedMarketResponseMessage
        else if (WEB3FeqAcceptTypeDef.CHANGE_ERROR.equals(l_strAfterChangeAcceptDiv))
        {
            l_marketResponseMessage = new DefaultChangeOrderRejectedMarketResponseMessage(l_lngOrderId);
        }

        //21FæÁÏ
        //DefaultCancelOrderAcceptedMarketResponseMessage
        else if (WEB3FeqAcceptTypeDef.CANCEL.equals(l_strAfterChangeAcceptDiv))
        {
            l_marketResponseMessage = new DefaultCancelOrderAcceptedMarketResponseMessage(l_lngOrderId);
        }

        //22FæÁG[
        //DefaultCancelOrderRejectedMarketResponseMessage
        else if (WEB3FeqAcceptTypeDef.CANCEL_ERROR.equals(l_strAfterChangeAcceptDiv))
        {
            l_marketResponseMessage = new DefaultCancelOrderRejectedMarketResponseMessage(l_lngOrderId);
        }

        //31Fo¸
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
