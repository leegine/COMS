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
 Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
 File Name        : ���s�ꃊ�N�G�X�g���M�T�[�r�X(WEB3BondMarketRequestService.java)
 Author Name      : Daiwa Institute of Research
 Revesion History : 2006/08/17  ꎉ� (���u) �V�K�쐬
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
 * (���s�ꃊ�N�G�X�g���M�T�[�r�X)<BR>
 * ���s�ꃊ�N�G�X�g���M�T�[�r�X�N���X<BR>
 * 
 * @@author ꎉ�
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
     * (�V�K�������M)<BR>
     * �V�K�������M<BR>
     * <BR>
     * send(BondNewOrderMarketRequestMessage)�̃I�[�o�[���C�h<BR>
     * <BR>
     * DefaultMarketRequestSendResult.newSuccessResultInstance(0L)��Ԃ�<BR>
     * @@param l_newOrderRequest - (�s�ꑗ�M���b�Z�[�W)<BR>
     * �s�ꑗ�M���b�Z�[�W<BR>
     * @@return MarketRequestSendResult
     * @@roseuid 44CC8EB60222
     */
    public MarketRequestSendResult send(BondNewOrderMarketRequestMessage l_newOrderRequest) 
    {
        return DefaultMarketRequestSendResult.newSuccessResultInstance(0L);
    }
    
    /**
     * (�����������M)<BR>
     * �����������M<BR>
     * <BR>
     * send(BondChangeOrderMarketRequestMessage, boolean)�̃I�[�o�[���C�h<BR>
     * <BR>
     * �@@DefaultMarketRequestSendResult.newSuccessResultInstance(0L)��Ԃ�<BR>
     * @@param l_changeOrderRequest - (�������M���b�Z�[�W)<BR>
     * �������M���b�Z�[�W<BR>
     * @@param l_blnLocal - (���[�J��)<BR>
     * ���[�J��<BR>
     * @@return MarketRequestSendResult
     * @@roseuid 44CC903401A5
     */
    public MarketRequestSendResult send(BondChangeOrderMarketRequestMessage l_changeOrderRequest, boolean l_blnLocal) 
    {
        return DefaultMarketRequestSendResult.newSuccessResultInstance(0L);
    }
    
    /**
     * (����������M)<BR>
     * ����������M<BR>
     * <BR>
     * send(CancelOrderMarketRequestMessage, boolean)�̃I�[�o�[���C�h<BR>
     * <BR>
     * �@@DefaultMarketRequestSendResult.newSuccessResultInstance(0L)��Ԃ�<BR>
     * @@param l_cancelOrderRequest - (������M���b�Z�[�W)<BR>
     * ������M���b�Z�[�W<BR>
     * 
     * @@param l_blnLocal - (���[�J��)<BR>
     * ���[�J��<BR>
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
