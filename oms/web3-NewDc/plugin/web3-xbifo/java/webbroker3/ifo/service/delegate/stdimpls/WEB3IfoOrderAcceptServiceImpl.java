head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.10;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3IfoOrderAcceptServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright           : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name           :�敨OP������t�T�[�r�X�����N���X(WEB3IfoOrderAcceptServiceImpl)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/06/21   ���Ō� (Sinocom) �V�K�쐬
              001: 2004/07/28  ���Ō� (Sinocom) �Ή��o�b�O WEB3_IFO_UT-000046
              002: 2004/07/29  Ḗ@@�� (Sinocom) �Ή��o�b�O WEB3_IFO_UT-000061
**/
package webbroker3.ifo.service.delegate.stdimpls;

import com.fitechlabs.xtrade.kernel.boot.Services;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.message.WEB3BackRequest;
import webbroker3.common.message.WEB3BackResponse;
import webbroker3.gentrade.WEB3GentradeDaemonTriggerManager;
import webbroker3.ifo.message.WEB3IfoOrderAcceptRequest;
import webbroker3.ifo.service.delegate.WEB3IfoOrderAcceptService;
import webbroker3.system.tune.threadpool.WEB3AsynExecuteService;
import webbroker3.util.WEB3LogUtility;

/**
 * (�敨OP������t�T�[�r�XImpl)<BR>
 * �敨OP������t�T�[�r�X�����N���X<BR>
 */
public class WEB3IfoOrderAcceptServiceImpl implements WEB3IfoOrderAcceptService
{
    /**
    * ���O���[�e�B���e�B<BR>
    */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(
        WEB3IfoOrderAcceptServiceImpl.class);
    /**
     * @@roseuid 40C0752F0119
     */
    public WEB3IfoOrderAcceptServiceImpl()
    {

    }

    /**
     * �敨OP������t�T�[�r�X���������{����B<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �u�i�敨OP�T�[�r�X�j�敨�I�v�V����������t�v�Q�ƁB<BR>
     * @@param l_request - ���N�G�X�g�f�[�^
     *
     * @@return webbroker3.ifo.message.WEB3IfoOrderAcceptResponse
     * @@throws WEB3BaseException
     * @@roseuid 4057CF4501C7
     */
    public WEB3BackResponse execute(WEB3BackRequest l_request)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "execute(l_request)";
        log.entering( STR_METHOD_NAME);

        WEB3IfoOrderAcceptRequest l_orderAcceptRequest = (WEB3IfoOrderAcceptRequest)l_request;
        // �X���b�h�J�n
        new WEB3GentradeDaemonTriggerManager().startThread(l_orderAcceptRequest.threadNo.longValue());
        // 1.3. �񓯊����s
        WEB3AsynExecuteService l_service =
            (WEB3AsynExecuteService) Services.getService(WEB3AsynExecuteService.class);
        l_service.execute(new WEB3AsynIfoOrderAcceptServiceImpl(l_orderAcceptRequest));
        
        log.exiting(STR_METHOD_NAME);
        return l_orderAcceptRequest.createResponse();
    }
}
@
