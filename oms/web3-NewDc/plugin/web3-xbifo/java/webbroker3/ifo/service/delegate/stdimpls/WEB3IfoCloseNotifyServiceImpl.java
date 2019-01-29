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
filename	WEB3IfoCloseNotifyServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �敨OP�����ʒm�T�[�r�XImpl(WEB3IfoCloseNotifyServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/6/18 Ḗ@@�� (���u) �V�K�쐬
              001: 2004/07/23 ���Ō� (���u) WEB3HostRequestCodeDef��WEB3IfoRequestCodeTypeDef�������ւ���
              002: 2004/07/29 ���Ō� (���u) �Ή��o�b�O WEB3_IFO_UT-000063 execute()���C��
              003: 2004/08/13 ������@@(���u) STBUG(IFO_ST-000079)��Ή�
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
import webbroker3.ifo.message.WEB3IfoCloseOrderRequest;
import webbroker3.ifo.service.delegate.WEB3IfoCloseNotifyService;

/**
 * (�敨OP�����ʒm�T�[�r�XImpl)<BR>
 * �敨OP�����ʒm�T�[�r�X�����N���X<BR>
 * @@author Ḗ@@��
 * @@version 1.0
 */
public class WEB3IfoCloseNotifyServiceImpl implements WEB3IfoCloseNotifyService 
{
    /**
     * ���O�o�̓��[�e�B���e�B�B
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3IfoCloseNotifyServiceImpl.class);    
    /**
     * @@roseuid 40C0752E02CE
     */
    public WEB3IfoCloseNotifyServiceImpl() 
    {
     
    }
    
    /**
     * �敨OP�����ʒm�T�[�r�X���������{����B<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �u�iOP�T�[�r�X�j�����ʒm�v�Q�ƁB<BR>
     * @@param l_request - ���N�G�X�g�f�[�^
     * @@return webbroker3.ifo.message.WEB3BackResponse
     * @@throws WEB3BaseException
     * @@roseuid 4088F4C60086
     */
    public WEB3BackResponse execute(WEB3BackRequest l_request) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = "WEB3IfoCloseOrderResponse execute(WEB3IfoCloseOrderRequest l_request)";
        log.entering(STR_METHOD_NAME);

        if (l_request == null)
        {
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME);
        }

        WEB3IfoCloseOrderRequest l_ifoCloseOrderRequest = (WEB3IfoCloseOrderRequest)l_request;

        // �X���b�h�J�n
        new WEB3GentradeDaemonTriggerManager().startThread(l_ifoCloseOrderRequest.threadNo.longValue());
        // 1.3. �񓯊����s
        WEB3AsynExecuteService l_service =
            (WEB3AsynExecuteService) Services.getService(WEB3AsynExecuteService.class);
        l_service.execute(new WEB3AsynIfoCloseNotifyServiceImpl(
            l_ifoCloseOrderRequest));

        log.exiting(STR_METHOD_NAME);
        return l_ifoCloseOrderRequest.createResponse();
    }   
 }

@
