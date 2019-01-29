head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.02.41.55;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	WEB3AdminMCCCOperatorDeleteHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǘ��҃��j���[����CC�I�y���[�^�폜�n���h��(WEB3AdminMCCCOperatorDeleteHandler.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/11/24 �͌d�� (���u) �V�K�쐬
*/

package webbroker3.adminmc.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;
import webbroker3.adminmc.message.WEB3AdminMCCCOperatorDeleteConfirmResponse;
import webbroker3.adminmc.message.WEB3AdminMCCCOperatorDeleteConfirmRequest;
import webbroker3.adminmc.message.WEB3AdminMCCCOperatorDeleteCompleteResponse;
import webbroker3.adminmc.message.WEB3AdminMCCCOperatorDeleteCompleteRequest;
import webbroker3.adminmc.service.delegate.WEB3AdminMCCCOperatorDeleteService;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.util.WEB3LogUtility;

/**
 * (�Ǘ��҃��j���[����CC�I�y���[�^�폜�n���h��)<BR>
 * �Ǘ��҃��j���[����CC�I�y���[�^�폜�n���h��<BR>
 * 
 * @@author �͌d��
 * @@version 1.0
 */
public class WEB3AdminMCCCOperatorDeleteHandler implements MessageHandler 
{
    
    /**         
     * ���O�o�̓��[�e�B���e�B�B         
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminMCCCOperatorDeleteHandler.class);  
        
    /**
     * @@roseuid 4198642B0119
     */
    public WEB3AdminMCCCOperatorDeleteHandler() 
    {
     
    }
    
    /**
     * (���ҍ폜�m�F)<BR>
     * CC�I�y���[�^�폜�m�F���������{����B<BR>
     * <BR>
     * �Ǘ��҃��j���[����CC�I�y���[�^�폜�T�[�r�X���擾���Aexecute()���\�b�h���R�[�����E
     * �B<BR>
     * <BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * �Ǘ��҃��j���[����CC���ڰ��폜�m�Fظ��ăf�[�^�I�u�W�F�N�g<BR>
     * @@return webbroker3.adminmc.message.WEB3AdminMCCCOperatorDeleteConfirmResponse
     * @@roseuid 417F793703B8
     */
    public WEB3AdminMCCCOperatorDeleteConfirmResponse traderDeleteConfirm(WEB3AdminMCCCOperatorDeleteConfirmRequest l_request) 
    {
        final String STR_METHOD_NAME = " traderDeleteConfirm(WEB3AdminMCCCOperatorDeleteConfirmRequest l_request)";
        log.entering(STR_METHOD_NAME);
        
        WEB3AdminMCCCOperatorDeleteConfirmResponse l_response = null;
        WEB3AdminMCCCOperatorDeleteService l_service = null;     
        try
        {
            l_service = (WEB3AdminMCCCOperatorDeleteService)Services.getService(WEB3AdminMCCCOperatorDeleteService.class); 
        }
        catch (Exception l_ex)
        {
            l_response = (WEB3AdminMCCCOperatorDeleteConfirmResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(l_request, "�Ǘ��҃��j���[����CC�I�y���[�^�폜�T�[�r�XImpl�̎擾�Ɏ��s���܂���", l_response.errorInfo, l_ex);
            return l_response;
        }
                 
        try
        {
            //execute()���\�b�h���R�[������
            l_response = (WEB3AdminMCCCOperatorDeleteConfirmResponse)l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response =
                (WEB3AdminMCCCOperatorDeleteConfirmResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request,
                "CC�I�y���[�^�폜�m�F�����Ɏ��s���܂���",
                l_ex);
            return l_response;
        }
        
        log.exiting(STR_METHOD_NAME);      
        
        return l_response; 

    }
    
    /**
     * (���ҍ폜����)<BR>
     * CC�I�y���[�^�폜�������������{����B<BR>
     * <BR>
     * �Ǘ��҃��j���[����CC�I�y���[�^�폜�T�[�r�X���擾���Aexecute()���\�b�h���R�[�����E
     * �B<BR>
     * <BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * �Ǘ��҃��j���[����CC���ڰ��폜����ظ��ăf�[�^�I�u�W�F�N�g<BR>
     * @@return webbroker3.adminmc.message.WEB3AdminMCCCOperatorDeleteCompleteResponse
     * @@roseuid 417F793703BA
     */
    public WEB3AdminMCCCOperatorDeleteCompleteResponse traderDeleteComplete(WEB3AdminMCCCOperatorDeleteCompleteRequest l_request) 
    {
        final String STR_METHOD_NAME = " traderDeleteComplete(WEB3AdminMCCCOperatorDeleteCompleteRequest l_request)";
        log.entering(STR_METHOD_NAME);
        
        WEB3AdminMCCCOperatorDeleteCompleteResponse l_response = null;
        WEB3AdminMCCCOperatorDeleteService l_service = null;     
        try
        {
            l_service = (WEB3AdminMCCCOperatorDeleteService)Services.getService(WEB3AdminMCCCOperatorDeleteService.class); 
        }
        catch (Exception l_ex)
        {
            l_response = (WEB3AdminMCCCOperatorDeleteCompleteResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(l_request, "�Ǘ��҃��j���[����CC�I�y���[�^�폜�T�[�r�XImpl�̎擾�Ɏ��s���܂���", l_response.errorInfo, l_ex);
            return l_response;
        }
                 
        try
        {
            //execute()���\�b�h���R�[������
            l_response = (WEB3AdminMCCCOperatorDeleteCompleteResponse)l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response =
                (WEB3AdminMCCCOperatorDeleteCompleteResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request,
                "CC�I�y���[�^�폜���������Ɏ��s���܂���",
                l_ex);
            return l_response;
        }
        
        log.exiting(STR_METHOD_NAME);      
        
        return l_response; 

    }
}
@
