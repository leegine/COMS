head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.02.42.03;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	WEB3AdminMCAdminDeleteHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǘ��҃��j���[����Ǘ��ҍ폜�n���h��(WEB3AdminMCAdminDeleteHandler.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/11/25 Tongwei(���u) �V�K�쐬
*/

package webbroker3.adminmc.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.adminmc.service.delegate.WEB3AdminMCAdminDeleteService;
import webbroker3.adminmc.message.WEB3AdminMCAdminDeleteConfirmResponse;
import webbroker3.adminmc.message.WEB3AdminMCAdminDeleteConfirmRequest;
import webbroker3.adminmc.message.WEB3AdminMCAdminDeleteCompleteResponse;
import webbroker3.adminmc.message.WEB3AdminMCAdminDeleteCompleteRequest;
import webbroker3.util.WEB3LogUtility;

/**
 * (�Ǘ��҃��j���[����Ǘ��ҍ폜�n���h��)<BR>
 * �Ǘ��҃��j���[����Ǘ��ҍ폜�n���h��<BR>
 * 
 * @@author Tongwei
 * @@version 1.0 
 */
public class WEB3AdminMCAdminDeleteHandler implements MessageHandler 
{
    /**
     *���O�o�̓��[�e�B���e�B�B
     */
    private WEB3LogUtility log = WEB3LogUtility.getInstance(WEB3AdminMCAdminDeleteHandler.class);
    
    /**
     * @@roseuid 4198643001C5
     */
    public WEB3AdminMCAdminDeleteHandler() 
    {
     
    }
    
    /**
     * (�Ǘ��ҍ폜�m�F)<BR>
     * �Ǘ��ҍ폜�m�F���������{����B<BR>
     * <BR>
     * �Ǘ��҃��j���[����Ǘ��ҍ폜�T�[�r�X���擾���Aexecute()���\�b�h���R�[������B<BR>
     * <BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * �Ǘ��҃��j���[����Ǘ��ҍ폜�m�F���N�G�X�g�f�[�^�I�u�W�F�N�g<BR>
     * @@return webbroker3.adminmc.message.WEB3AdminMCAdminDeleteConfirmResponse
     * @@roseuid 417DAF1A00BB
     */
    public WEB3AdminMCAdminDeleteConfirmResponse adminDeleteConfirm(WEB3AdminMCAdminDeleteConfirmRequest l_request) 
    {
        final String STR_METHOD_NAME =
                getClass().getName() + ".adminDeleteConfirm(WEB3AdminMCAdminDeleteConfirmRequest l_request)";
        log.entering(STR_METHOD_NAME);
        
        WEB3AdminMCAdminDeleteConfirmResponse l_response = null;
        WEB3AdminMCAdminDeleteService l_service = null;
             
        try
        {
            l_service= (WEB3AdminMCAdminDeleteService)Services.getService(WEB3AdminMCAdminDeleteService.class); 
        }
        catch (Exception l_ex)
        {
            l_response = (WEB3AdminMCAdminDeleteConfirmResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(l_request, "�Ǘ��҃��j���[����Ǘ��ҍ폜�T�[�r�XImpl�̎擾�Ɏ��s���܂���", 
                l_response.errorInfo, l_ex);
            return l_response;
        }
                 
        try
        {
            //execute()���\�b�h���R�[������
            l_response = (WEB3AdminMCAdminDeleteConfirmResponse)l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response =
                (WEB3AdminMCAdminDeleteConfirmResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request,
                "�Ǘ��ҍ폜�m�F�����Ɏ��s���܂���",
                l_ex);
            return l_response;
        }
        
        log.exiting(STR_METHOD_NAME);           
        return l_response;
    }
    
    /**
     * (�Ǘ��ҍ폜����)<BR>
     * �Ǘ��ҍ폜�������������{����B<BR>
     * <BR>
     * �Ǘ��҃��j���[����Ǘ��ҍ폜�T�[�r�X���擾���Aexecute()���\�b�h���R�[������B<BR>
     * <BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * �Ǘ��҃��j���[����Ǘ��ҍ폜�������N�G�X�g�f�[�^�I�u�W�F�N�g<BR>
     * @@return webbroker3.adminmc.message.WEB3AdminMCAdminDeleteCompleteResponse
     * @@roseuid 417DAF1A00BD
     */
    public WEB3AdminMCAdminDeleteCompleteResponse adminDeleteComplete(WEB3AdminMCAdminDeleteCompleteRequest l_request) 
    {
        final String STR_METHOD_NAME =
                getClass().getName() + ".adminDeleteComplete(WEB3AdminMCAdminDeleteCompleteRequest l_request)";
        log.entering(STR_METHOD_NAME);
        
        WEB3AdminMCAdminDeleteCompleteResponse l_response = null;
        WEB3AdminMCAdminDeleteService l_service = null;
             
        try
        {
            l_service= (WEB3AdminMCAdminDeleteService)Services.getService(WEB3AdminMCAdminDeleteService.class); 
        }
        catch (Exception l_ex)
        {
            l_response = (WEB3AdminMCAdminDeleteCompleteResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(l_request, "�Ǘ��҃��j���[����Ǘ��ҍ폜�T�[�r�XImpl�̎擾�Ɏ��s���܂���", 
                l_response.errorInfo, l_ex);
            return l_response;
        }
                 
        try
        {
            //execute()���\�b�h���R�[������
            l_response = (WEB3AdminMCAdminDeleteCompleteResponse)l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response =
                (WEB3AdminMCAdminDeleteCompleteResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request,
                "�Ǘ��ҍ폜���������Ɏ��s���܂���",
                l_ex);
            return l_response;
        }
        
        log.exiting(STR_METHOD_NAME);           
        return l_response;
    }
}
@
