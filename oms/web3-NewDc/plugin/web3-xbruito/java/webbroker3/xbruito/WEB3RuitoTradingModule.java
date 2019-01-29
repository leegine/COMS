head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.42;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3RuitoTradingModule.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 拡張取引モジュール(WWEB3RuitoTradingModule.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/08/17 王蘭芬(中訊) 新規作成
*/

package webbroker3.xbruito;

import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderManager;
import com.fitechlabs.xtrade.plugin.tc.xbruito.stdimpls.RuitoTradingModuleImpl;

/**
 * （拡張取引モジュール）<BR>
 *<BR>
 * xTradeのTradingModuleを拡張したクラス。<BR>
 *<BR>
 * @@author 王蘭芬 (中訊)
 * @@version 1.0
 */
public class WEB3RuitoTradingModule extends RuitoTradingModuleImpl
{

    /**
     * コンストラクタ。<BR>
     */
    public WEB3RuitoTradingModule()
    {
        super();
    }
    
    /**
     * overrideOrderManagerをOverride<BR>     
     */    
    public void overrideOrderManager(OrderManager l_newOrderManager)
    {
        if (l_newOrderManager instanceof WEB3RuitoOrderManager)
        {
            m_orderManager = l_newOrderManager;
        }
        else
        {
            throw new IllegalArgumentException(
                "OrderManager is not an WEB3RuitoOrderManager");
        }
    }
}
@
