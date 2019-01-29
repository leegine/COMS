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
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �g��������W���[��(WEB3FeqTradingModule.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/7/20 ����(���u) �V�K�쐬
*/

package webbroker3.feq;

import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderManager;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductManager;
import com.fitechlabs.xtrade.plugin.tc.xbfeq.stdimpls.FeqTradingModuleImpl;

/**
 * �i�g��������W���[���j<BR>
 *<BR>
 * xTrade��TradingModule���g�������N���X�B<BR>
 *<BR>
 * @@author ����(���u)
 * @@version 1.0
 */

public class WEB3FeqTradingModule extends FeqTradingModuleImpl
{ 
    /**
     * �R���X�g���N�^�B<BR>
     */
    public WEB3FeqTradingModule()
    {
        super();
        super.m_finTranManager = new WEB3FeqFinTransactionManager();
    }
    
    /**
     * overrideOrderManager��Override<BR>     
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
