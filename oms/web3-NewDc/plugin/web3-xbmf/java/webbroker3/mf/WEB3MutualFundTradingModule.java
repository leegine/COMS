head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.21;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3MutualFundTradingModule.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �g��������W���[��(WEB3MutualFundTradingModule.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/08/25 �����(���u) �V�K�쐬
*/

package webbroker3.mf;

import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderManager;
import com.fitechlabs.xtrade.plugin.tc.xbmf.stdimpls.MutualFundTradingModuleImpl;

/**
 * �i�g��������W���[���j<BR>
 *<BR>
 * xTrade��TradingModule���g�������N���X�B<BR>
 *<BR>
 * @@author ����� (���u)
 * @@version 1.0
 */
public class WEB3MutualFundTradingModule extends MutualFundTradingModuleImpl
{

    /**
     * �R���X�g���N�^�B<BR>
     */
    public WEB3MutualFundTradingModule()
    {
        super();
    }
    
    /**
     * overrideOrderManager��Override<BR>     
     */    
    public void overrideOrderManager(OrderManager l_newOrderManager)
    {
        if (l_newOrderManager instanceof WEB3MutualFundOrderManager)
        {
            m_orderManager = l_newOrderManager;
        }
        else
        {
            throw new IllegalArgumentException(
                "OrderManager is not an WEB3MutualFundOrderManager");
        }
    }
}
@
