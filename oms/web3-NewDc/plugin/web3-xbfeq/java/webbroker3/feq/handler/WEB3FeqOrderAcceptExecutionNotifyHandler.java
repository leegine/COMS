head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.43;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3FeqOrderAcceptExecutionNotifyHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �O������������t�o���ʒm�n���h��(WEB3FeqOrderAcceptExecutionNotifyHandler.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/09/12 ꎉ� (���u) �V�K�쐬
*/

package webbroker3.feq.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.feq.message.WEB3FeqOrderAcceptExecNotifyRequest;
import webbroker3.feq.message.WEB3FeqOrderAcceptExecNotifyResponse;
import webbroker3.feq.service.delegate.WEB3FeqOrderAcceptExecutionNotifyService;
import webbroker3.util.WEB3LogUtility;

/**
 * (�O������������t�o���ʒm�n���h��)<BR>
 * �O������������t�o���ʒm�n���h���N���X<BR>
 * 
 * @@author ꎉ�
 * @@version 1.0
 */
public class WEB3FeqOrderAcceptExecutionNotifyHandler implements MessageHandler 
{
    /**
     * ���O�o�̓��[�e�B���e�B�B
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3FeqOrderAcceptExecutionNotifyHandler.class);
    
    /**
     * (������t�o���ʒm)<BR>
     * �O������������t�o���ʒm�T�[�r�X���擾���Aexecute()���\�b�h���R�[������B<BR>
     * <BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * ���N�G�X�g�f�[�^<BR>
     * @@return WEB3FeqOrderAcceptExecNotifyResponse
     * @@roseuid 4214980A032E
     */
    public WEB3FeqOrderAcceptExecNotifyResponse orderAcceptExecNotify(
    	WEB3FeqOrderAcceptExecNotifyRequest l_request)
    {
        final String STR_METHOD_NAME = " orderAcceptExecNotify(" +
        		"WEB3FeqOrderAcceptExecNotifyRequest)";
        log.entering(STR_METHOD_NAME);

        WEB3FeqOrderAcceptExecNotifyResponse l_response = null;
        WEB3FeqOrderAcceptExecutionNotifyService l_service = null;
        
        try
        {            
            //get�Ǘ��ҊO������������t����F�؃T�[�r�X
            l_service = (WEB3FeqOrderAcceptExecutionNotifyService)
                Services.getService(WEB3FeqOrderAcceptExecutionNotifyService.class);
        }
        catch (Exception l_ex)
        {
            //�T�[�r�X�ŗ�O�����������ꍇ�́A
            //�G���[�������X�|���X���b�Z�[�W�ɐݒ肷��
            l_response =
                (WEB3FeqOrderAcceptExecNotifyResponse)l_request.createResponse();
            l_response.errorInfo = 
                WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "�O������������t�o���ʒm�Ɏ��s���܂����B",
                l_response.errorInfo,
                l_ex);
            return l_response;
        }
        try
        {
            l_response = (WEB3FeqOrderAcceptExecNotifyResponse)l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response =
                (WEB3FeqOrderAcceptExecNotifyResponse) l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request, 
                "�O������������t�o���ʒm�Ɏ��s���܂����B", 
                l_ex);
            return l_response;
        }
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
}
@
