head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.12;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3IfoChangeCancelOrderAcceptServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright           : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name           :�敨OP���������t�T�[�r�X�����N���X(WEB3IfoChangeCancelOrderAcceptServiceImpl)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/06/22   ���Ō� (Sinocom) �V�K�쐬
              001: 2004/08/14 ���Ō� �Ή� �y�����w���I�v�V�����z�\�[�X�R�[�h�`�F�b�N�w�E����(JP)200408
*/
package webbroker3.ifo.service.delegate.stdimpls;


import com.fitechlabs.xtrade.kernel.boot.Services;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.message.WEB3BackRequest;
import webbroker3.common.message.WEB3BackResponse;
import webbroker3.ifo.message.WEB3IfoChangeCancelAcceptRequest;
import webbroker3.ifo.message.WEB3IfoChangeCancelAcceptResponse;
import webbroker3.ifo.service.delegate.WEB3IfoChangeCancelOrderAcceptService;
import webbroker3.system.tune.threadpool.WEB3AsynExecuteService;
import webbroker3.util.WEB3LogUtility;

import webbroker3.gentrade.WEB3GentradeDaemonTriggerManager;

/**
 * (�敨OP���������t�T�[�r�XImpl)<BR>
 * �敨OP���������t�T�[�r�X�����N���X<BR>
 */
public class WEB3IfoChangeCancelOrderAcceptServiceImpl
    implements WEB3IfoChangeCancelOrderAcceptService
{
    /**
     * ���O�o�̓��[�e�B���e�B�B
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(
            WEB3IfoChangeCancelOrderAcceptServiceImpl.class);

    /**
     * @@roseuid 40C0752F00BB
     */
    public WEB3IfoChangeCancelOrderAcceptServiceImpl()
    {

    }

    /**
     * �敨OP���������t�T�[�r�X���������{����B<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �u�i�敨OP�T�[�r�X�j�敨�I�v�V�������������t�v�Q�ƁB<BR>
     * @@param l_request ���N�G�X�g
     * @@return WEB3BackResponse
     * @@throws WEB3BaseException
     * @@roseuid 4083833A026D
     */
    public WEB3BackResponse execute(WEB3BackRequest l_request)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "execute(l_request)";
        log.entering(STR_METHOD_NAME);
        WEB3IfoChangeCancelAcceptRequest l_inRequest = (WEB3IfoChangeCancelAcceptRequest)l_request;
        WEB3IfoChangeCancelAcceptResponse l_response = (WEB3IfoChangeCancelAcceptResponse) l_inRequest.createResponse();

        // �X���b�h�J�n
        new WEB3GentradeDaemonTriggerManager().startThread(l_inRequest.threadNo.longValue());
        // 1.3. �񓯊����s
        WEB3AsynExecuteService l_service =
            (WEB3AsynExecuteService) Services.getService(WEB3AsynExecuteService.class);
        l_service.execute(new WEB3AysnIfoChangeCancelOrderAcceptServiceImpl(l_inRequest));

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
}
@
