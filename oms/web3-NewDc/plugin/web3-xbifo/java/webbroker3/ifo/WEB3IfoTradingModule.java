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
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �g��������W���[��(WEB3IfoTradingModule.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/06/15 ����� (���u) �V�K�쐬
*/

package webbroker3.ifo;

import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderManager;
import com.fitechlabs.xtrade.plugin.tc.xbifo.stdimpls.IfoTradingModuleImpl;

/**
 *�i�g��������W���[���j<BR>
 * �g��������W���[���N���X<BR>
 * xTrade��TradingModule���g�������N���X�B<BR>
 * @@author ����� (���u)
 * @@version 1.0
 */
public class WEB3IfoTradingModule extends IfoTradingModuleImpl
{
    /**
     * �R���X�g���N�^�B<BR>
     * �g�����U�N�V�����}�l�[�W����WEB3IfoFinTransactionManagerImpl��<BR>
     * �ݒ肷��B<BR>
     */
    public WEB3IfoTradingModule()
    {
        super();
        super.m_finTranManager = new WEB3IfoFinTransactionManagerImpl();
    }
    
    /**
     * overrideOrderManager��Override<BR>     
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
