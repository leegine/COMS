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
filename	WEB3FuturesOrderExecNotifyServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �����w���敨�o���ʒm�T�[�r�X�����N���X(WEB3FuturesOrderExecNotifyServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/7/23 䈋� (���u) �V�K�쐬
                   2005/03/10 ���u���i���{���u�j�L���[�e�[�u���ɂ�鉺�菈���̃g�����U�N�V��������ύX�A
 �@@�@@�@@�@@�@@�@@�@@�@@�@@ �񓯊���������WEB3AysnFuturesOrderExecNotifyServiceImpl�ֈڂ��i�V�K�N���X�j�iU01182�j
*/
package webbroker3.ifo.service.delegate.stdimpls;

import com.fitechlabs.xtrade.kernel.boot.Services;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.message.WEB3BackRequest;
import webbroker3.common.message.WEB3BackResponse;
import webbroker3.ifo.message.WEB3FuturesOrderExecNotifyRequest;
import webbroker3.ifo.message.WEB3FuturesOrderExecNotifyResponse;
import webbroker3.ifo.service.delegate.WEB3FuturesOrderExecNotifyService;
import webbroker3.util.WEB3LogUtility;

import webbroker3.gentrade.WEB3GentradeDaemonTriggerManager;
import webbroker3.system.tune.threadpool.WEB3AsynExecuteService;

/**
 * (�敨�o���ʒm�T�[�r�XImpl)<BR>
 * �����w���敨�o���ʒm�T�[�r�X�����N���X<BR>
 * @@author 䈋�
 * @@version 1.0
 */
public class WEB3FuturesOrderExecNotifyServiceImpl
    implements WEB3FuturesOrderExecNotifyService
{
    /**
     * ���O�o�̓��[�e�B���e�B�B
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3FuturesOrderExecNotifyServiceImpl.class);

    /**
     * @@roseuid 40F7A2C50000
     */
    public WEB3FuturesOrderExecNotifyServiceImpl()
    {

    }

    /**
     * �����w���敨�o���ʒm�T�[�r�X���������{����B<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �u�i�敨�T�[�r�X�j�敨�o���ʒm�v�Q�ƁB<BR>
     * @@param l_request - ���N�G�X�g�f�[�^
     * @@return WEB3FuturesOrderExecNotifyResponse
     * @@throws WEB3BaseException
     * @@roseuid 40A83F0D031F
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

        WEB3FuturesOrderExecNotifyRequest l_orderExecNotifyRequest =
            (WEB3FuturesOrderExecNotifyRequest)l_request;
        WEB3FuturesOrderExecNotifyResponse l_orderExecNotifyResponse =
            (WEB3FuturesOrderExecNotifyResponse)l_orderExecNotifyRequest.createResponse();

        // �X���b�h�J�n
        new WEB3GentradeDaemonTriggerManager().startThread(l_orderExecNotifyRequest.threadNo.longValue());
        // 1.3. �񓯊����s
        WEB3AsynExecuteService l_service =
            (WEB3AsynExecuteService) Services.getService(WEB3AsynExecuteService.class);
        l_service.execute(new WEB3AsynFuturesOrderExecNotifyServiceImpl(
            l_orderExecNotifyRequest));

        log.exiting(STR_METHOD_NAME);
        return l_orderExecNotifyResponse;
    }
}
@
