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
filename	WEB3OptionOrderExecNotifyServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        :  �����w���I�v�V�����o���ʒm�T�[�r�X����(WEB3OptionOrderExecNotifyServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/06/15 羉s (���u) �V�K�쐬
*/

package webbroker3.ifo.service.delegate.stdimpls;

import com.fitechlabs.xtrade.kernel.boot.Services;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.message.WEB3BackRequest;
import webbroker3.common.message.WEB3BackResponse;
import webbroker3.gentrade.WEB3GentradeDaemonTriggerManager;
import webbroker3.ifo.message.WEB3OptionOrderExecNotifyRequest;
import webbroker3.ifo.message.WEB3OptionOrderExecNotifyResponse;
import webbroker3.ifo.service.delegate.WEB3OptionOrderExecNotifyService;
import webbroker3.system.tune.threadpool.WEB3AsynExecuteService;
import webbroker3.util.WEB3LogUtility;

/**
 * (OP�o���ʒm�T�[�r�XImpl)<BR>
 * <BR>
 * �����w���I�v�V�����o���ʒm�T�[�r�X�����N���X<BR>
 *                                                                     
 * @@author 羉s
 * @@version 1.0
 */
public class WEB3OptionOrderExecNotifyServiceImpl
    implements WEB3OptionOrderExecNotifyService
{

    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3OptionOrderExecNotifyServiceImpl.class);

    /**
     * @@roseuid 40C0752F0186
     */
    public WEB3OptionOrderExecNotifyServiceImpl()
    {

    }

    /**
     * �����w���I�v�V�����o���ʒm�T�[�r�X���������{����B<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �u�iOP�T�[�r�X�j�I�v�V�����o���ʒm�v�Q�ƁB<BR>
     * @@param l_request - ���N�G�X�g�f�[�^
     * @@return webbroker3.common.message.WEB3BackResponse
     * @@throws WEB3BaseException
     * @@roseuid 4057BDE903DA
     */
    public WEB3BackResponse execute(WEB3BackRequest l_request)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "execute(WEB3BackRequest l_request)";
        log.entering(STR_METHOD_NAME);

        if (l_request == null)
        {
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME);
        }

        WEB3OptionOrderExecNotifyRequest l_orderExecNotifyRequest = (WEB3OptionOrderExecNotifyRequest)l_request;
        WEB3OptionOrderExecNotifyResponse l_orderExecNotifyResponse = 
            (WEB3OptionOrderExecNotifyResponse)l_orderExecNotifyRequest.createResponse();

        // �X���b�h�J�n
        new WEB3GentradeDaemonTriggerManager().startThread(l_orderExecNotifyRequest.threadNo.longValue());
        // 1.3. �񓯊����s
        WEB3AsynExecuteService l_service =
            (WEB3AsynExecuteService) Services.getService(WEB3AsynExecuteService.class);
        l_service.execute(new WEB3AsynOptionOrderExecNotifyServiceImpl(
            l_orderExecNotifyRequest));

        log.exiting(STR_METHOD_NAME);
        return l_orderExecNotifyResponse;
    }
}
@
