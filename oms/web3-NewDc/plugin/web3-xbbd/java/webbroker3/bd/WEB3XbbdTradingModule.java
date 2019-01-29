head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.27;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3XbbdTradingModule.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �g��������W���[��(WEB3XbbdTradingModule)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/08/23  �����@@(���u) �V�K�쐬
*/

package webbroker3.bd;

import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderManager;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductManager;
import com.fitechlabs.xtrade.plugin.tc.xbbd.stdimpls.BondTradingModuleImpl;

/**
 *�i�g��������W���[���j<BR>
 * �g��������W���[���N���X<BR>
 * xTrade��TradingModule���g�������N���X�B<BR>
 * @@author ���� (���u)
 * @@version 1.0
 */

public class WEB3XbbdTradingModule extends BondTradingModuleImpl
{
    /**
     * �R���X�g���N�^�B<BR>
     */
    public WEB3XbbdTradingModule()
    {
        super();
        super.m_finTranManager = new WEB3BondFinTransactionManager();
    }
    
    /**
     * overrideOrderManager��Override<BR>     
     */    
    public void overrideOrderManager(OrderManager l_newOrderManager)
    {
        if (l_newOrderManager instanceof WEB3BondOrderManager)
        {
            m_orderManager = l_newOrderManager;
        }
        else
        {
            throw new IllegalArgumentException("OrderManager is not an WEB3BondOrderManager");
        }
    }
    
    public void overrideProductManager(ProductManager l_newProductManager)
    {
        if (l_newProductManager instanceof WEB3BondProductManager)
        {
            super.m_productManager = l_newProductManager;
        }
        else
        {
            throw new UnsupportedOperationException("ProductManager is not WEB3BondProductManager");
        }
    }
    
    public ProductManager getProductManager()
    {
        return super.m_productManager;
    }
}
@
