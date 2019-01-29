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
filename	WEB3OptionChangeCancelNotifyServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : OP��������ʒm�T�[�r�XImpl(WEB3OptionChangeCancelNotifyServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/6/15 Ḗ@@�� (���u) �V�K�쐬
              001: 2004/07/23 ���Ō� (���u) IfoOrderExecutionConditionType��WEB3IfoModifiedExecutionTypeDef�������ւ���
              002: 2004/07/29 ���Ō� (���u) excute()�̈������C�� �Ή��o�b�O WEB3_IFO_UT-000053
              003: 2004/07/29 ���Ō� (���u) excute()�̈������C�� �Ή��o�b�O WEB3_IFO_UT-000062
                                                                            WEB3_IFO_UT-000053�̍ďC�� 
              004: 2004/07/29 ���Ō� (���u) process()���C�� �Ή��o�b�O WEB3_IFO_UT-000091
              005: 2004/8/5 Ḗ@@��   process()���C�� �Ή��o�b�O WEB3_IFO_UT-000115
              006: 2004/08/06 ���Ō� (���u) process()���C�� �Ή��o�b�O WEB3_IFO_UT-000120
              007: 2004/08/13 ������@@(���u) STBUG(IFO_ST-000079)��Ή�
              
**/
package webbroker3.ifo.service.delegate.stdimpls;

import com.fitechlabs.xtrade.kernel.boot.Services;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.message.WEB3BackRequest;
import webbroker3.common.message.WEB3BackResponse;
import webbroker3.gentrade.WEB3GentradeDaemonTriggerManager;
import webbroker3.ifo.message.WEB3OptionsChangeCancelNotifyRequest;
import webbroker3.ifo.message.WEB3OptionsChangeCancelNotifyResponse;
import webbroker3.ifo.service.delegate.WEB3OptionChangeCancelNotifyService;
import webbroker3.system.tune.threadpool.WEB3AsynExecuteService;
import webbroker3.util.WEB3LogUtility;

/**
 * (OP��������ʒm�T�[�r�XImpl)<BR>
 * �����w���I�v�V������������ʒm�T�[�r�X�����N���X<BR>
 * @@author Ḗ@@��
 * @@version 1.0
 */
public class WEB3OptionChangeCancelNotifyServiceImpl implements WEB3OptionChangeCancelNotifyService
{
    /**
     * Logger
     */
    private static WEB3LogUtility log = 
        WEB3LogUtility.getInstance(
            WEB3OptionChangeCancelNotifyServiceImpl.class);

    /**
    /**
     * @@roseuid 40C0752D033C
     */
    public WEB3OptionChangeCancelNotifyServiceImpl()
    {

    }

    /**
     * �����w���I�v�V������������ʒm�T�[�r�X���������{����B<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �u�iOP�T�[�r�X�j�I�v�V������������ʒm�v�Q�ƁB<BR>
     * @@param l_request - ���N�G�X�g�f�[�^
     * @@return WEB3BackResponse
     * @@throws WEB3BaseException
     * @@roseuid 4057D2570198
     */
    public WEB3BackResponse execute(WEB3BackRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "WEB3OptionsChangeCancelNotifyResponse execute(WEB3OptionsChangeCancelNotifyRequest l_request)";
        log.entering(STR_METHOD_NAME);

        if (l_request == null)
        {
            log.error("The request parameter is null");
            throw new WEB3BaseException
                (WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                STR_METHOD_NAME);
        }

        WEB3OptionsChangeCancelNotifyRequest l_optionsChangeCancelNotifyRequest =
            (WEB3OptionsChangeCancelNotifyRequest) l_request;

        WEB3OptionsChangeCancelNotifyResponse l_response =
            (WEB3OptionsChangeCancelNotifyResponse) l_optionsChangeCancelNotifyRequest.createResponse();

        // �X���b�h�J�n
        new WEB3GentradeDaemonTriggerManager().startThread(l_optionsChangeCancelNotifyRequest.threadNo.longValue());
        // 1.3. �񓯊����s
        WEB3AsynExecuteService l_service =
            (WEB3AsynExecuteService) Services.getService(WEB3AsynExecuteService.class);
        l_service.execute(new WEB3AsynOptionChangeCancelNotifyServiceImpl(
            l_optionsChangeCancelNotifyRequest));

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
}
@
