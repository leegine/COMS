head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.02.41.20;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	WEB3AdminMCAdminPwdLockCancelHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǘ��҃��j���[����Ǘ��҃p�X���[�h���b�N�����n���h��(WEB3AdminMCAdminPwdLockCancelHandler.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/11/23  �� �� �@@ (���u) �V�K�쐬
*/

package webbroker3.adminmc.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.util.WEB3LogUtility;

import webbroker3.adminmc.service.delegate.WEB3AdminMCAdminPwdLockCancelService;
import webbroker3.adminmc.message.WEB3AdminMCAdminPwdLockCancelConfirmResponse;
import webbroker3.adminmc.message.WEB3AdminMCAdminPwdLockCancelConfirmRequest;
import webbroker3.adminmc.message.WEB3AdminMCAdminPwdLockCancelCompleteResponse;
import webbroker3.adminmc.message.WEB3AdminMCAdminPwdLockCancelCompleteRequest;


/**
 * (�Ǘ��҃��j���[����Ǘ��҃p�X���[�h���b�N�����n���h��)<BR>
 * �Ǘ��҃��j���[����Ǘ��҃p�X���[�h���b�N�����n���h��<BR>
 * @@author �����@@
 * @@version 1.0
 */

public class WEB3AdminMCAdminPwdLockCancelHandler implements MessageHandler 
{
    
    /**
     *���O�o�̓��[�e�B���e�B�B
     */
    private WEB3LogUtility log = WEB3LogUtility.getInstance(WEB3AdminMCAdminPwdLockCancelHandler.class);
    
    /**
     * @@roseuid 4198642E0280
     */
    public WEB3AdminMCAdminPwdLockCancelHandler() 
    {
     
    }
    
    /**
     * (�����m�F)<BR>
     * �Ǘ��҃p�X���[�h���b�N�����m�F���������{����B<BR>
     * <BR>
     * �Ǘ��҃��j���[����Ǘ��҃p�X���[�h���b�N�����T�[�r�X���擾���Aexecute()���\�b�h�E
     * �R�[������B<BR>
     * <BR>
     * <BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * �Ǘ��҃��j���[�����߽ܰ�މ����m�Fظ��ăf�[�^�I�u�W�F�N�g<BR>
     * @@return webbroker3.adminmc.message.WEB3AdminMCAdminPwdLockCancelConfirmResponse
     * @@roseuid 417DE4AA0154
     */
    public WEB3AdminMCAdminPwdLockCancelConfirmResponse cancelConfirm(WEB3AdminMCAdminPwdLockCancelConfirmRequest l_request) 
    {
        final String STR_METHOD_NAME =
                getClass().getName() + ".cancelConfirm(WEB3AdminMCAdminPwdLockCancelConfirmRequest l_request)";
        log.entering(STR_METHOD_NAME);
        WEB3AdminMCAdminPwdLockCancelConfirmResponse l_response = null;
        WEB3AdminMCAdminPwdLockCancelService l_service = null;     
        try
        {
            l_service= (WEB3AdminMCAdminPwdLockCancelService)Services.getService(WEB3AdminMCAdminPwdLockCancelService.class); 
        }
        catch (Exception l_ex)
        {
            l_response = (WEB3AdminMCAdminPwdLockCancelConfirmResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(l_request, "�Ǘ��҃��j���[����Ǘ��҃p�X���[�h���b�N�����T�[�r�XImpl�̎擾�Ɏ��s���܂���.", l_response.errorInfo, l_ex);
            log.exiting(STR_METHOD_NAME);      
            return l_response;
        }
                 
        try
        {
            //execute()���\�b�h���R�[������
            l_response = (WEB3AdminMCAdminPwdLockCancelConfirmResponse)l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response =
                (WEB3AdminMCAdminPwdLockCancelConfirmResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request,
                "�Ǘ��҃p�X���[�h���b�N�����m�F�Ɏ��s���܂���.",
                l_ex);
            log.exiting(STR_METHOD_NAME);      
            return l_response;
        }
        
        log.exiting(STR_METHOD_NAME);      
        
        return l_response; 
    }

    
    /**
     * (��������)<BR>
     * �Ǘ��҃p�X���[�h���b�N�����������������{����B<BR>
     * <BR>
     * �Ǘ��҃��j���[����Ǘ��҃p�X���[�h���b�N�����T�[�r�X���擾���Aexecute()���\�b�h�E
     * �R�[������B<BR>
     * <BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * �Ǘ��҃��j���[�����߽ܰ�މ�������ظ��ăf�[�^�I�u�W�F�N�g<BR>
     * @@return webbroker3.adminmc.message.WEB3AdminMCAdminPwdLockCancelCompleteResponse
     * @@roseuid 417DE4AA0163
     */
    public WEB3AdminMCAdminPwdLockCancelCompleteResponse cancelComplete(WEB3AdminMCAdminPwdLockCancelCompleteRequest l_request) 
    {
        final String STR_METHOD_NAME =
                getClass().getName() + ".cancelComplete(WEB3AdminMCAdminPwdLockCancelCompleteRequest l_request)";
        log.entering(STR_METHOD_NAME);
        WEB3AdminMCAdminPwdLockCancelCompleteResponse l_response = null;
        WEB3AdminMCAdminPwdLockCancelService l_service = null;     
        try
        {
            l_service= (WEB3AdminMCAdminPwdLockCancelService)Services.getService(WEB3AdminMCAdminPwdLockCancelService.class); 
        }
        catch (Exception l_ex)
        {
            l_response = (WEB3AdminMCAdminPwdLockCancelCompleteResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(l_request, "�Ǘ��҃��j���[����Ǘ��҃p�X���[�h���b�N�����T�[�r�XImpl�̎擾�Ɏ��s���܂���.", l_response.errorInfo, l_ex);
            log.exiting(STR_METHOD_NAME);      
            return l_response;
        }
                 
        try
        {
            //execute()���\�b�h���R�[������
            l_response = (WEB3AdminMCAdminPwdLockCancelCompleteResponse)l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response =
                (WEB3AdminMCAdminPwdLockCancelCompleteResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request,
                "�Ǘ��҃p�X���[�h���b�N���������Ɏ��s���܂���.",
                l_ex);
            log.exiting(STR_METHOD_NAME);      
            return l_response;
        }
        
        log.exiting(STR_METHOD_NAME);      
        
        return l_response; 
    }
}
@
