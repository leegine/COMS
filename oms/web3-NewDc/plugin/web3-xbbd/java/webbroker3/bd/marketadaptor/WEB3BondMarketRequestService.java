head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.06;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3BondMarketRequestService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
 Copyright        : (株)大和総研 証券ソリューションシステム第二部
 File Name        : 債券市場リクエスト送信サービス(WEB3BondMarketRequestService.java)
 Author Name      : Daiwa Institute of Research
 Revesion History : 2006/08/17  齊珂 (中訊) 新規作成
 */
package webbroker3.bd.marketadaptor;

import com.fitechlabs.xtrade.plugin.tc.gentrade.DefaultMarketRequestSendResult;
import com.fitechlabs.xtrade.plugin.tc.gentrade.MarketRequestSendResult;
import com.fitechlabs.xtrade.plugin.tc.gentrade.market.messages.CancelOrderMarketRequestMessage;
import com.fitechlabs.xtrade.plugin.tc.gentrade.market.messages.MarketRequestMessage;
import com.fitechlabs.xtrade.plugin.tc.xbbd.BondMarketRequestSenderService;
import com.fitechlabs.xtrade.plugin.tc.xbbd.market.messages.BondChangeOrderMarketRequestMessage;
import com.fitechlabs.xtrade.plugin.tc.xbbd.market.messages.BondNewOrderMarketRequestMessage;

/**
 * (債券市場リクエスト送信サービス)<BR>
 * 債券市場リクエスト送信サービスクラス<BR>
 * 
 * @@author 齊珂
 * @@version 1.0
 */
public class WEB3BondMarketRequestService implements BondMarketRequestSenderService
{
    
    /**
     * @@roseuid 44E336150271
     */
    public WEB3BondMarketRequestService() 
    {
     
    }
    
    /**
     * (新規注文送信)<BR>
     * 新規注文送信<BR>
     * <BR>
     * send(BondNewOrderMarketRequestMessage)のオーバーライド<BR>
     * <BR>
     * DefaultMarketRequestSendResult.newSuccessResultInstance(0L)を返す<BR>
     * @@param l_newOrderRequest - (市場送信メッセージ)<BR>
     * 市場送信メッセージ<BR>
     * @@return MarketRequestSendResult
     * @@roseuid 44CC8EB60222
     */
    public MarketRequestSendResult send(BondNewOrderMarketRequestMessage l_newOrderRequest) 
    {
        return DefaultMarketRequestSendResult.newSuccessResultInstance(0L);
    }
    
    /**
     * (訂正注文送信)<BR>
     * 訂正注文送信<BR>
     * <BR>
     * send(BondChangeOrderMarketRequestMessage, boolean)のオーバーライド<BR>
     * <BR>
     * 　@DefaultMarketRequestSendResult.newSuccessResultInstance(0L)を返す<BR>
     * @@param l_changeOrderRequest - (訂正送信メッセージ)<BR>
     * 訂正送信メッセージ<BR>
     * @@param l_blnLocal - (ローカル)<BR>
     * ローカル<BR>
     * @@return MarketRequestSendResult
     * @@roseuid 44CC903401A5
     */
    public MarketRequestSendResult send(BondChangeOrderMarketRequestMessage l_changeOrderRequest, boolean l_blnLocal) 
    {
        return DefaultMarketRequestSendResult.newSuccessResultInstance(0L);
    }
    
    /**
     * (取消注文送信)<BR>
     * 取消注文送信<BR>
     * <BR>
     * send(CancelOrderMarketRequestMessage, boolean)のオーバーライド<BR>
     * <BR>
     * 　@DefaultMarketRequestSendResult.newSuccessResultInstance(0L)を返す<BR>
     * @@param l_cancelOrderRequest - (取消送信メッセージ)<BR>
     * 取消送信メッセージ<BR>
     * 
     * @@param l_blnLocal - (ローカル)<BR>
     * ローカル<BR>
     * @@return MarketRequestSendResult
     * @@roseuid 44CC90AB00AB
     */
    public MarketRequestSendResult send(CancelOrderMarketRequestMessage l_cancelOrderRequest, boolean l_blnLocal) 
    {
        return DefaultMarketRequestSendResult.newSuccessResultInstance(0L);
    }

    public MarketRequestSendResult send(MarketRequestMessage arg0)
    {
        return null;
    }
}
@
