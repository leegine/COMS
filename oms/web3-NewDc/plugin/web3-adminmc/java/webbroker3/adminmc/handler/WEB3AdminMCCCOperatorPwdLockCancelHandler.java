head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.02.41.41;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	WEB3AdminMCCCOperatorPwdLockCancelHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǘ��҃��j���[����CC���ڰ��߽ܰ��ۯ����������(WEB3AdminMCCCOperatorPwdLockCancelHandler.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/11/19 Tongwei(���u) �V�K�쐬
*/

package webbroker3.adminmc.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.adminmc.service.delegate.WEB3AdminMCCCOperatorPwdLockCancelService;
import webbroker3.adminmc.message.WEB3AdminMCCCOperatorPwdLockCancelConfirmResponse;
import webbroker3.adminmc.message.WEB3AdminMCCCOperatorPwdLockCancelConfirmRequest;
import webbroker3.adminmc.message.WEB3AdminMCCCOperatorPwdLockCancelCompleteResponse;
import webbroker3.adminmc.message.WEB3AdminMCCCOperatorPwdLockCancelCompleteRequest;
import webbroker3.util.WEB3LogUtility;

/**
 * (�Ǘ��҃��j���[����CC���ڰ��߽ܰ��ۯ����������)<BR>
 * �Ǘ��҃��j���[����CC���ڰ��߽ܰ��ۯ����������<BR>
 * 
 * @@author Tongwei
 * @@version 1.0
 */
public class WEB3AdminMCCCOperatorPwdLockCancelHandler implements MessageHandler 
{
    
    /**
     *���O�o�̓��[�e�B���e�B�B
     */
    private WEB3LogUtility log = WEB3LogUtility.getInstance(WEB3AdminMCCCOperatorPwdLockCancelHandler.class);
   
    
    /**
     * @@roseuid 4198642A02AF
     */
    public WEB3AdminMCCCOperatorPwdLockCancelHandler() 
    {
     
    }
    
    /**
     * (�����m�F)<BR>
     * CC�I�y���[�^�p�X���[�h���b�N�����m�F���������{����B<BR>
     * <BR>
     * �Ǘ��҃��j���[����CC���ڰ��߽ܰ��ۯ��������޽���擾���Aexecute()���\�b�h���R�[��
     * ����B<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * �Ǘ��҃��j���[����CC���ڰ��߽ܰ��ۯ������m�Fظ��ăf�[�^�I�u�W�F�N�g<BR>
     * @@return webbroker3.adminmc.message.WEB3AdminMCCCOperatorPwdLockCancelConfirmResponse
     * @@roseuid 417F2419021E
     */
    public WEB3AdminMCCCOperatorPwdLockCancelConfirmResponse cancelConfirm(WEB3AdminMCCCOperatorPwdLockCancelConfirmRequest l_request) 
    {
        final String STR_METHOD_NAME =
                getClass().getName() + ".cancelConfirm(WEB3AdminMCCCOperatorPwdLockCancelConfirmRequest l_request)";
        log.entering(STR_METHOD_NAME);
        
        WEB3AdminMCCCOperatorPwdLockCancelConfirmResponse l_response = null;
        WEB3AdminMCCCOperatorPwdLockCancelService l_service = null;
             
        try
        {
            l_service= (WEB3AdminMCCCOperatorPwdLockCancelService)Services.getService(WEB3AdminMCCCOperatorPwdLockCancelService.class); 
        }
        catch (Exception l_ex)
        {
            l_response = (WEB3AdminMCCCOperatorPwdLockCancelConfirmResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(l_request, "�Ǘ��҃��j���[����CC���ڰ��߽ܰ��ۯ��������޽Impl�̎擾�Ɏ��s���܂���", l_response.errorInfo, l_ex);
            return l_response;
        }
                 
        try
        {
            //execute()���\�b�h���R�[������
            l_response = (WEB3AdminMCCCOperatorPwdLockCancelConfirmResponse)l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response =
                (WEB3AdminMCCCOperatorPwdLockCancelConfirmResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request,
                "CC�I�y���[�^�p�X���[�h���b�N�����m�F�����Ɏ��s���܂���",
                l_ex);
            return l_response;
        }
        
        log.exiting(STR_METHOD_NAME);           
        return l_response;
    }
    
    /**
     * (��������)<BR>
     * CC�I�y���[�^�p�X���[�h���b�N�����������������{����B<BR>
     * <BR>
     * �Ǘ��҃��j���[����CC���ڰ��߽ܰ��ۯ��������޽���擾���Aexecute()���\�b�h���R�[��
     * ����B<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * �Ǘ��҃��j���[����CC���ڰ��߽ܰ��ۯ���������ظ��ăf�[�^�I�u�W�F�N�g<BR>
     * @@return webbroker3.adminmc.message.WEB3AdminMCCCOperatorPwdLockCancelCompleteResponse
     * @@roseuid 417F241A002A
     */
    public WEB3AdminMCCCOperatorPwdLockCancelCompleteResponse cancelComplete(WEB3AdminMCCCOperatorPwdLockCancelCompleteRequest l_request) 
    {
        final String STR_METHOD_NAME =
                getClass().getName() + ".cancelComplete(WEB3AdminMCCCOperatorPwdLockCancelCompleteRequest l_request)";
        log.entering(STR_METHOD_NAME);
        
        WEB3AdminMCCCOperatorPwdLockCancelCompleteResponse l_response = null;
        WEB3AdminMCCCOperatorPwdLockCancelService l_service = null;
             
        try
        {
            l_service= (WEB3AdminMCCCOperatorPwdLockCancelService)Services.getService(WEB3AdminMCCCOperatorPwdLockCancelService.class); 
        }
        catch (Exception l_ex)
        {
            l_response = (WEB3AdminMCCCOperatorPwdLockCancelCompleteResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(l_request, "�Ǘ��҃��j���[����CC���ڰ��߽ܰ��ۯ��������޽Impl�̎擾�Ɏ��s���܂���", l_response.errorInfo, l_ex);
            return l_response;
        }
                 
        try
        {
            //execute()���\�b�h���R�[������
            l_response = (WEB3AdminMCCCOperatorPwdLockCancelCompleteResponse)l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response =
                (WEB3AdminMCCCOperatorPwdLockCancelCompleteResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request,
                "CC�I�y���[�^�p�X���[�h���b�N�������������Ɏ��s���܂���",
                l_ex);
            return l_response;
        }
        
        log.exiting(STR_METHOD_NAME);           
        return l_response;
    }
}
@
