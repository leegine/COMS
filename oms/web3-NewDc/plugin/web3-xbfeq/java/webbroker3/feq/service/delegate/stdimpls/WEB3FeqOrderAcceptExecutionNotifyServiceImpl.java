head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.40.04;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3FeqOrderAcceptExecutionNotifyServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �O������������t�o���ʒm�T�[�r�XImpl(WEB3FeqOrderAcceptExecutionNotifyServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/09/12 ꎉ� (���u) �V�K�쐬
*/

package webbroker3.feq.service.delegate.stdimpls;

import com.fitechlabs.xtrade.kernel.boot.Services;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.message.WEB3BackRequest;
import webbroker3.common.message.WEB3BackResponse;
import webbroker3.feq.message.WEB3FeqOrderAcceptExecNotifyRequest;
import webbroker3.feq.message.WEB3FeqOrderAcceptExecNotifyResponse;
import webbroker3.feq.service.delegate.WEB3FeqOrderAcceptExecutionNotifyService;
import webbroker3.gentrade.WEB3GentradeDaemonTriggerManager;
import webbroker3.system.tune.threadpool.WEB3AsynExecuteService;
import webbroker3.util.WEB3LogUtility;

/**
 * (�O������������t�o���ʒm�T�[�r�XImpl)<BR>
 * �O������������t�o���ʒm�T�[�r�X�����N���X<BR>
 * 
 * @@author ꎉ�
 * @@version 1.0
 */
public class WEB3FeqOrderAcceptExecutionNotifyServiceImpl implements WEB3FeqOrderAcceptExecutionNotifyService
{
    /**
     * ���O�o�̓��[�e�B���e�B�B
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3FeqOrderAcceptExecutionNotifyServiceImpl.class);
    
    /**
     * �O������������t�o���ʒm�T�[�r�X���������{����B<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * <BR>
     * �u�i�O������������t�o���ʒm�T�[�r�X�j������t�o���ʒm�v�Q�ƁB<BR>
     * <BR>
     * @@param l_request - (���N�G�X�g)<BR>
     * ���N�G�X�g�I�u�W�F�N�g<BR>
     * @@return WEB3BackResponse
     * @@throws WEB3BaseException
     * @@roseuid 4214980A032E
     */
    public WEB3BackResponse execute(WEB3BackRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " execute(WEB3GenRequest )";
        log.entering(STR_METHOD_NAME);

        if (l_request == null)
        {
            log.debug("���N�G�X�g�����w��(null)�ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME, 
                "���N�G�X�g�����w��(null)�ł��B");
        }
        
        if (!(l_request instanceof WEB3FeqOrderAcceptExecNotifyRequest))
        {
            log.debug("�p�����[�^�^�C�v�s���B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80018,
                this.getClass().getName() + STR_METHOD_NAME, 
                "�p�����[�^�^�C�v�s���B");
        }
        
        WEB3FeqOrderAcceptExecNotifyRequest l_orderAcceptExecNotifyRequest = 
            (WEB3FeqOrderAcceptExecNotifyRequest)l_request;

        //1.1 ���N�G�X�g�f�[�^�̐������`�F�b�N���s��
        l_orderAcceptExecNotifyRequest.validate();
        
        WEB3FeqOrderAcceptExecNotifyResponse l_response = 
        	(WEB3FeqOrderAcceptExecNotifyResponse) l_orderAcceptExecNotifyRequest.createResponse();
        //1.2 �X���b�h�J�n
        new WEB3GentradeDaemonTriggerManager().startThread(
        	l_orderAcceptExecNotifyRequest.threadNo.longValue());
        
        //1.3 �񓯊����s
        WEB3AsynExecuteService l_service =
            (WEB3AsynExecuteService)Services.getService(WEB3AsynExecuteService.class);
        
        //1.4 �񓯊����������s����B
        l_service.execute(new WEB3AsynFeqOrderAcceptExecutionNotifyServiceImpl(
        	l_orderAcceptExecNotifyRequest));

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
}
@
