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
filename	WEB3FuturesChangeCancelNotifyServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �����w���敨��������ʒm�T�[�r�X�����N���X(WEB3FuturesChangeCancelNotifyServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/7/21 Ḗ@@�� (���u) �V�K�쐬
*/

package webbroker3.ifo.service.delegate.stdimpls;

import com.fitechlabs.xtrade.kernel.boot.Services;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.message.WEB3BackRequest;
import webbroker3.common.message.WEB3BackResponse;
import webbroker3.system.tune.threadpool.WEB3AsynExecuteService;
import webbroker3.util.WEB3LogUtility;

import webbroker3.gentrade.WEB3GentradeDaemonTriggerManager;
import webbroker3.ifo.message.WEB3FuturesChangeCancelNotifyRequest;
import webbroker3.ifo.service.delegate.WEB3FuturesChangeCancelNotifyService;

/**
 * (�敨��������ʒm�T�[�r�XImpl)<BR>
 * �����w���敨��������ʒm�T�[�r�X�����N���X<BR>
 * @@author Ḗ@@��
 * @@version 1.0
 */
public class WEB3FuturesChangeCancelNotifyServiceImpl implements WEB3FuturesChangeCancelNotifyService
{
    /**
     * Logger
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3FuturesChangeCancelNotifyServiceImpl.class);

    /**
     * @@roseuid 40F7A2D003A9
     */
    public WEB3FuturesChangeCancelNotifyServiceImpl()
    {

    }

    /**
     * �����w���敨��������ʒm�T�[�r�X���������{����B<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �u�i�敨�T�[�r�X�j�敨��������ʒm�v�Q�ƁB<BR>
     * @@param l_request - ���N�G�X�g�f�[�^<BR>
     * @@return WEB3FuturesChangeCancelNotifyResponse
     * @@throws WEB3BaseException
     * @@roseuid 40A89E1702F6
     */
    public WEB3BackResponse execute(WEB3BackRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "WEB3FuturesChangeCancelNotifyResponse execute(WEB3FuturesChangeCancelNotifyRequest l_request)";
        log.entering(STR_METHOD_NAME);
        if (l_request == null)
        {
            throw new WEB3SystemLayerException
                (WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME);

        }
        //2: getDefaultProcessor
        //throws
        WEB3FuturesChangeCancelNotifyRequest l_changeCancelNotifyRequest = (WEB3FuturesChangeCancelNotifyRequest) l_request;
        WEB3BackResponse l_response = l_changeCancelNotifyRequest.createResponse();

        // �X���b�h�J�n
        new WEB3GentradeDaemonTriggerManager().startThread(l_changeCancelNotifyRequest.threadNo.longValue());
        // 1.3. �񓯊����s
        WEB3AsynExecuteService l_service =
            (WEB3AsynExecuteService) Services.getService(WEB3AsynExecuteService.class);
        l_service.execute(new WEB3AsynFuturesChangeCancelNotifyServiceImpl(l_changeCancelNotifyRequest));

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
}
@
