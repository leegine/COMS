head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.24;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3RuitoTradeOrderNotifyUnitService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �ݓ����������ʒm�P���T�[�r�X�C���^�t�F�[�X(WEB3RuitoTradeOrderNotifyUnitService)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/06/11 �m�X (���u) �V�K�쐬
*/
package webbroker3.xbruito.service.delegate;

import com.fitechlabs.xtrade.kernel.boot.Service;

import webbroker3.common.WEB3BaseException;
import webbroker3.xbruito.data.HostRuitoOrderNotifyParams;
import webbroker3.xbruito.WEB3RuitoTradedOrderNotifyDecisionInterceptor;

/**
 * �ݓ����������ʒm�P���T�[�r�X�C���^�t�F�[�X<BR>
 * <BR>
 * �ݓ����������ʒm�P�����Ƃ̏��������{����B<BR>
 */
public interface WEB3RuitoTradeOrderNotifyUnitService extends Service
{

    /**
     * @@param l_orderNotifyCue - �ݓ������ʒm�L���[Params <BR>
     * @@param l_tradeOrderNotifyDecisionInterceptor - �ݓ����������ʒm�m��C���^�Z�v�^ 
     * <BR>
     * @@roseuid 408F4EF2026C
     */
    public abstract void notifyTradeOrderNotify(
        HostRuitoOrderNotifyParams l_orderNotifyCue,
        WEB3RuitoTradedOrderNotifyDecisionInterceptor l_tradeOrderNotifyDecisionInterceptor)throws WEB3BaseException;
}
@
