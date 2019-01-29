head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.45.48;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3IfoTradingModule.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 拡張取引モジュール(WEB3IfoTradingModule.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/06/15 張宝楠 (中訊) 新規作成
*/

package webbroker3.ifo;

import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderManager;
import com.fitechlabs.xtrade.plugin.tc.xbifo.stdimpls.IfoTradingModuleImpl;

/**
 *（拡張取引モジュール）<BR>
 * 拡張取引モジュールクラス<BR>
 * xTradeのTradingModuleを拡張したクラス。<BR>
 * @@author 張宝楠 (中訊)
 * @@version 1.0
 */
public class WEB3IfoTradingModule extends IfoTradingModuleImpl
{
    /**
     * コンストラクタ。<BR>
     * トランザクションマネージャにWEB3IfoFinTransactionManagerImplを<BR>
     * 設定する。<BR>
     */
    public WEB3IfoTradingModule()
    {
        super();
        super.m_finTranManager = new WEB3IfoFinTransactionManagerImpl();
    }
    
    /**
     * overrideOrderManagerをOverride<BR>     
     */    
    public void overrideOrderManager(OrderManager l_newOrderManager)
    {
        if (l_newOrderManager instanceof WEB3OptionOrderManagerImpl)
        {
            m_orderManager = l_newOrderManager;
        }
        else
        {
            throw new IllegalArgumentException(
                "OrderManager is not an WEB3OptionOrderManagerImpl");
        }
    }
}
@
