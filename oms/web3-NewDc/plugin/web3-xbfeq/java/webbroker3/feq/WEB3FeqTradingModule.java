head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.04;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3FeqTradingModule.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 拡張取引モジュール(WEB3FeqTradingModule.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/7/20 黄建(中訊) 新規作成
*/

package webbroker3.feq;

import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderManager;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductManager;
import com.fitechlabs.xtrade.plugin.tc.xbfeq.stdimpls.FeqTradingModuleImpl;

/**
 * （拡張取引モジュール）<BR>
 *<BR>
 * xTradeのTradingModuleを拡張したクラス。<BR>
 *<BR>
 * @@author 黄建(中訊)
 * @@version 1.0
 */

public class WEB3FeqTradingModule extends FeqTradingModuleImpl
{ 
    /**
     * コンストラクタ。<BR>
     */
    public WEB3FeqTradingModule()
    {
        super();
        super.m_finTranManager = new WEB3FeqFinTransactionManager();
    }
    
    /**
     * overrideOrderManagerをOverride<BR>     
     */    
    public void overrideOrderManager(OrderManager l_newOrderManager)
    {
        if (l_newOrderManager instanceof WEB3FeqOrderManager)
        {
            m_orderManager = l_newOrderManager;
        }
        else
        {
            throw new IllegalArgumentException(
                "OrderManager is not an WEB3FeqOrderManager");
        }
    }
    
    public void overrideProductManager(ProductManager l_newProductManager)
    {
        if (l_newProductManager instanceof WEB3FeqProductManager)
        {
            super.m_productManager = l_newProductManager;
        }
        else
        {
            throw new UnsupportedOperationException("ProductManager is not WEB3FeqProductManager");
        }
    }
    
    public ProductManager getProductManager()
    {
        return super.m_productManager;
    }
}
@
