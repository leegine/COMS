head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.02.41.16;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	WEB3AdminMCAdminChangeHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright          : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name          : (�Ǘ��҃��j���[����Ǘ��ҕύX�n���h��(WEB3AdminMCAdminChangeHandler)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/11/24 ���q (���u) �V�K�쐬
*/

package webbroker3.adminmc.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.util.WEB3LogUtility;
import webbroker3.adminmc.service.delegate.WEB3AdminMCAdminChangeService;
import webbroker3.adminmc.message.WEB3AdminMCAdminChangeInputResponse;
import webbroker3.adminmc.message.WEB3AdminMCAdminChangeInputRequest;
import webbroker3.adminmc.message.WEB3AdminMCAdminChangeConfirmResponse;
import webbroker3.adminmc.message.WEB3AdminMCAdminChangeConfirmRequest;
import webbroker3.adminmc.message.WEB3AdminMCAdminChangeCompleteResponse;
import webbroker3.adminmc.message.WEB3AdminMCAdminChangeCompleteRequest;

/**
 * (�Ǘ��҃��j���[����Ǘ��ҕύX�n���h��)<BR>
 * �Ǘ��҃��j���[����Ǘ��ҕύX�n���h��<BR>
 * @@author ���q
 * @@version 1.0
 */
public class WEB3AdminMCAdminChangeHandler implements MessageHandler 
{
    /**
     *���O�o�̓��[�e�B���e�B�B
     */
    private WEB3LogUtility log = WEB3LogUtility.getInstance(WEB3AdminMCAdminChangeHandler.class);

    /**
     * @@roseuid 419864310109
     */
    public WEB3AdminMCAdminChangeHandler() 
    {
     
    }
    
    /**
     * (���͉�ʕ\��)<BR>
     * �Ǘ��ҕύX���͉�ʕ\�����������{����B<BR>
     * <BR>
     * �Ǘ��҃��j���[����Ǘ��ҕύX�T�[�r�X���擾���Aexecute()���\�b�h���R�[������B<BR>
     * <BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * �Ǘ��҃��j���[����Ǘ��ҕύX���̓��N�G�X�g�f�[�^�I�u�W�F�N�g<BR>
     * <BR>
     * @@return webbroker3.adminmc.message.WEB3AdminMCAdminChangeInputResponse
     * @@throws WEB3BaseException
     * @@roseuid 417DCA550192
     */
    public WEB3AdminMCAdminChangeInputResponse inputScreenDisplay(WEB3AdminMCAdminChangeInputRequest l_request) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME =
                getClass().getName() + ".inputScreenDisplay(WEB3AdminMCAdminChangeInputRequest l_request)";
        log.entering(STR_METHOD_NAME);
        WEB3AdminMCAdminChangeInputResponse l_response = null;
        WEB3AdminMCAdminChangeService l_service = null;     
        try
        {
            l_service= (WEB3AdminMCAdminChangeService)Services.getService(WEB3AdminMCAdminChangeService.class); 
        }
        catch (Exception l_ex)
        {
            l_response = (WEB3AdminMCAdminChangeInputResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(l_request, 
                        "�Ǘ��҃��j���[����Ǘ��ҕύX�T�[�r�X�̎擾�Ɏ��s���܂���", 
                        l_response.errorInfo,
                        l_ex);
            return l_response;
        }
                 
        try
        {
            //execute()���\�b�h���R�[������
            l_response = (WEB3AdminMCAdminChangeInputResponse)l_service.execute(l_request);

        }
        catch (WEB3BaseException l_ex)
        {
            l_response =
                (WEB3AdminMCAdminChangeInputResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error( l_request,
                          "�Ǘ��҃��j���[����Ǘ��ҕύX���͕\�������Ɏ��s���܂���",
                         l_ex);
            return l_response;
        }
        
        log.exiting(STR_METHOD_NAME);      
        
        return l_response; 
    }
    
    /**
     * (�Ǘ��ҕύX�m�F)<BR>
     * �Ǘ��ҕύX�m�F���������{����B<BR>
     * <BR>
     * �Ǘ��҃��j���[����Ǘ��ҕύX�T�[�r�X���擾���Aexecute()���\�b�h���R�[������B<BR>
     * <BR>
     * <BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * �Ǘ��҃��j���[����Ǘ��ҕύX�m�F���N�G�X�g�f�[�^�I�u�W�F�N�g<BR>
     * @@return webbroker3.adminmc.message.WEB3AdminMCAdminChangeConfirmResponse
     * @@roseuid 417DCA550194
     */
    public WEB3AdminMCAdminChangeConfirmResponse adminChangeConfirm(WEB3AdminMCAdminChangeConfirmRequest l_request) 
    {
        final String STR_METHOD_NAME =
                 getClass().getName() + ".adminChangeConfirm(WEB3AdminMCAdminChangeConfirmRequest l_request)";
        log.entering(STR_METHOD_NAME);
        WEB3AdminMCAdminChangeConfirmResponse l_response = null;
        WEB3AdminMCAdminChangeService l_service = null;     
        try
        {
            l_service= (WEB3AdminMCAdminChangeService)Services.getService(WEB3AdminMCAdminChangeService.class); 
        }
        catch (Exception l_ex)
        {
            l_response = (WEB3AdminMCAdminChangeConfirmResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(l_request, 
                        "�Ǘ��҃��j���[����Ǘ��ҕύX�T�[�r�X�̎擾�Ɏ��s���܂���", 
                        l_response.errorInfo,
                        l_ex);
            return l_response;
        }
                 
        try
        {
            //execute()���\�b�h���R�[������
            l_response = (WEB3AdminMCAdminChangeConfirmResponse)l_service.execute(l_request);

        }
        catch (WEB3BaseException l_ex)
        {
            l_response =
                (WEB3AdminMCAdminChangeConfirmResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error( l_request,
                          "�Ǘ��҃��j���[����Ǘ��ҕύX�m�F�\�������Ɏ��s���܂���",
                         l_ex);
            return l_response;
        }
        
        log.exiting(STR_METHOD_NAME);      
        
        return l_response; 
    }
    
    /**
     * (�Ǘ��ҕύX����)<BR>
     * �Ǘ��ҕύX�������������{����B<BR>
     * <BR>
     * �Ǘ��҃��j���[����Ǘ��ҕύX�T�[�r�X���擾���Aexecute()���\�b�h���R�[������B<BR>
     * <BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * �Ǘ��҃��j���[����Ǘ��ҕύX�������N�G�X�g�f�[�^�I�u�W�F�N�g<BR>
     * @@return webbroker3.adminmc.message.WEB3AdminMCAdminChangeCompleteResponse
     * @@roseuid 417DCA5501A2
     */
    public WEB3AdminMCAdminChangeCompleteResponse adminChangeComplete(WEB3AdminMCAdminChangeCompleteRequest l_request) 
    {
        final String STR_METHOD_NAME =
                 getClass().getName() + ".adminChangeComplete(WEB3AdminMCAdminChangeCompleteRequest l_request)";
        log.entering(STR_METHOD_NAME);
        WEB3AdminMCAdminChangeCompleteResponse l_response = null;
        WEB3AdminMCAdminChangeService l_service = null;     
        try
        {
            l_service= (WEB3AdminMCAdminChangeService)Services.getService(WEB3AdminMCAdminChangeService.class); 
        }
        catch (Exception l_ex)
        {
            l_response = (WEB3AdminMCAdminChangeCompleteResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(l_request, 
                        "�Ǘ��҃��j���[����Ǘ��ҕύX�T�[�r�X�̎擾�Ɏ��s���܂���", 
                        l_response.errorInfo,
                        l_ex);
            return l_response;
        }
                 
        try
        {
            //execute()���\�b�h���R�[������
            l_response = (WEB3AdminMCAdminChangeCompleteResponse)l_service.execute(l_request);

        }
        catch (WEB3BaseException l_ex)
        {
            l_response =
                (WEB3AdminMCAdminChangeCompleteResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error( l_request,
                          "�Ǘ��҃��j���[����Ǘ��ҕύX�����\�������Ɏ��s���܂���",
                         l_ex);
            return l_response;
        }
        
        log.exiting(STR_METHOD_NAME);      
        
        return l_response; 
    }
}
@
