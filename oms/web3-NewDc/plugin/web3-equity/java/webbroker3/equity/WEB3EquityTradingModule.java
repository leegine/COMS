head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.09;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3EquityTradingModule.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 拡張取引モジュールクラス(WEB3EquityTradingModule.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/02/12 中尾　@寿彦(SRA) 新規作成
*/
package webbroker3.equity;

import com.fitechlabs.xtrade.plugin.tc.eqtype.stdimpls.TradingModuleImpl;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderManager;

/**
 * （拡張取引モジュール）。<BR>
 * <BR>
 * xTradeのTradingModuleを拡張したクラス。
 * @@author 中尾　@寿彦(SRA)
 * @@version 1.0
 */
public class WEB3EquityTradingModule extends TradingModuleImpl
{

    /**
     * （コンストラクタ）。<BR>
     * トランザクションマネージャにWEB3EquityFinTransactionManagerを設定する。<BR>
     * 
     */
    public WEB3EquityTradingModule()
    {
        super();
        super.m_finTranManager = new WEB3EquityFinTransactionManager();
    }
    
    
    /* 
     * 
     * @@see com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule#overrideOrderManager(com.fitechlabs.xtrade.plugin.tc.gentrade.OrderManager)
     */
    public void overrideOrderManager(OrderManager l_newOrderManager)
    {
        if (l_newOrderManager instanceof WEB3EquityOrderManager)
        {
            m_orderManager = l_newOrderManager;
        }
        else
        {
            throw new IllegalArgumentException(
                "OrderManager is not an WEB3EquityOrderManager.");
        }
    }

}
@
