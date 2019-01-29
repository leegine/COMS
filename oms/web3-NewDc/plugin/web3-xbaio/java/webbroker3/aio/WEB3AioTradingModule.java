head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.02.21.30;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6a04d801c24738b;
filename	WEB3AioTradingModule.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 拡張取引モジュール(WEB3AioTradingModule.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/20 王蘭芬(中訊) 新規作成
*/

package webbroker3.aio;

import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderManager;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductManager;
import com.fitechlabs.xtrade.plugin.tc.xbaio.stdimpls.AioTradingModuleImpl;

/**
 * （拡張取引モジュール）<BR>
 *<BR>
 * xTradeのTradingModuleを拡張したクラス。<BR>
 *<BR>
 * @@author 王蘭芬 (中訊)
 * @@version 1.0
 */
public class WEB3AioTradingModule extends AioTradingModuleImpl
{

    /**
     * コンストラクタ。<BR>
     */
    public WEB3AioTradingModule()
    {
        super();
    }
    
    /**
     * overrideOrderManagerをOverride<BR>     
     */    
    public void overrideOrderManager(OrderManager l_newOrderManager)
    {
        if (l_newOrderManager instanceof WEB3AioOrderManager)
        {
            m_orderManager = l_newOrderManager;
        }
        else
        {
            throw new IllegalArgumentException(
                "OrderManager is not an WEB3AioOrderManager");
        }
    }
    
    public void overrideProductManager(ProductManager l_newProductManager)
    {
        if (l_newProductManager instanceof WEB3AioProductManager)
        {
            super.m_productManager = l_newProductManager;
        }
        else
        {
            throw new UnsupportedOperationException("ProductManager is not WEB3AioProductManager");
        }
    }
    
    public ProductManager getProductManager()
    {
        return super.m_productManager;
    }
}
@
