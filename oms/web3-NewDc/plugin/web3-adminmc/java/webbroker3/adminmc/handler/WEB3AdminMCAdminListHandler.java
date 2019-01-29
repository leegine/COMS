head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.02.42.13;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	WEB3AdminMCAdminListHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǘ��҃��j���[����Ǘ��҈ꗗ�n���h��(WEB3AdminMCAdminListHandler.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/11/23  �� �� �@@ (���u) �V�K�쐬
*/

package webbroker3.adminmc.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.util.WEB3LogUtility;

import webbroker3.adminmc.message.WEB3AdminMCAdminListInputResponse;
import webbroker3.adminmc.message.WEB3AdminMCAdminListInputRequest;
import webbroker3.adminmc.message.WEB3AdminMCAdminListResponse;
import webbroker3.adminmc.message.WEB3AdminMCAdminListRequest;
import webbroker3.adminmc.service.delegate.WEB3AdminMCAdminListService;


/**
 * (�Ǘ��҃��j���[����Ǘ��҈ꗗ�n���h��)<BR>
 * �Ǘ��҃��j���[����Ǘ��҈ꗗ�n���h��<BR>
 * @@author �����@@
 * @@version 1.0
 */

public class WEB3AdminMCAdminListHandler implements MessageHandler 
{
    
    /**
     *���O�o�̓��[�e�B���e�B�B
     */
    private WEB3LogUtility log = WEB3LogUtility.getInstance(WEB3AdminMCAdminListHandler.class);
    
    /**
     * @@roseuid 4198642E038A
     */
    public WEB3AdminMCAdminListHandler() 
    {
     
    }
    
    /**
     * (���͉�ʕ\��)<BR>
     * �Ǘ��҈ꗗ�������͉�ʕ\�����������{����B<BR>
     * <BR>
     * �Ǘ��҃��j���[����Ǘ��҈ꗗ�T�[�r�X���擾���Aexecute()���\�b�h���R�[������B<BR>
     * <BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * �Ǘ��҃��j���[����Ǘ��҈ꗗ���̓��N�G�X�g�f�[�^�I�u�W�F�N�g<BR>
     * @@return webbroker3.adminmc.message.WEB3AdminMCAdminListInputResponse
     * @@roseuid 417DEF8C02F9
     */
    public WEB3AdminMCAdminListInputResponse inputScreenDisplay(WEB3AdminMCAdminListInputRequest l_request) 
    {
        final String STR_METHOD_NAME =
                getClass().getName() + ".inputScreenDisplay(WEB3AdminMCAdminListInputRequest l_request)";
        log.entering(STR_METHOD_NAME);
        WEB3AdminMCAdminListInputResponse l_response = null;
        WEB3AdminMCAdminListService l_service = null;     
        try
        {
            l_service= (WEB3AdminMCAdminListService)Services.getService(WEB3AdminMCAdminListService.class); 
        }
        catch (Exception l_ex)
        {
            l_response = (WEB3AdminMCAdminListInputResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(l_request, "�Ǘ��҃��j���[����Ǘ��҈ꗗ�T�[�r�XImpl�̎擾�Ɏ��s���܂���.", l_response.errorInfo, l_ex);
            return l_response;
        }
                 
        try
        {
            //execute()���\�b�h���R�[������
            l_response = (WEB3AdminMCAdminListInputResponse)l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response =
                (WEB3AdminMCAdminListInputResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request,
                "�Ǘ��҈ꗗ�������͉�ʕ\���Ɏ��s���܂���.",
                l_ex);
            return l_response;
        }
        
        log.exiting(STR_METHOD_NAME);      
        
        return l_response; 
    }
    
    /**
     * (�Ǘ��҈ꗗ�\��)<BR>
     * �Ǘ��҈ꗗ�\�����������{����B<BR>
     * <BR>
     * �Ǘ��҃��j���[����Ǘ��҈ꗗ�T�[�r�X���擾���Aexecute()���\�b�h���R�[������B<BR>
     * <BR>
     * <BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * �Ǘ��҃��j���[����Ǘ��҈ꗗ���N�G�X�g�f�[�^�I�u�W�F�N�g<BR>
     * @@return webbroker3.adminmc.message.WEB3AdminMCAdminListResponse
     * @@roseuid 417DEF8C0309
     */
    public WEB3AdminMCAdminListResponse adminListDisplay(WEB3AdminMCAdminListRequest l_request) 
    {
        final String STR_METHOD_NAME =
                getClass().getName() + ".adminListDisplay(WEB3AdminMCAdminListRequest l_request)";
        log.entering(STR_METHOD_NAME);
        WEB3AdminMCAdminListResponse l_response = null;
        WEB3AdminMCAdminListService l_service = null;     
        try
        {
            l_service= (WEB3AdminMCAdminListService)Services.getService(WEB3AdminMCAdminListService.class); 
        }
        catch (Exception l_ex)
        {
            l_response = (WEB3AdminMCAdminListResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(l_request, "�Ǘ��҃��j���[����Ǘ��҈ꗗ�T�[�r�XImpl�̎擾�Ɏ��s���܂���.", l_response.errorInfo, l_ex);
            return l_response;
        }
                 
        try
        {
            //execute()���\�b�h���R�[������
            l_response = (WEB3AdminMCAdminListResponse)l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response =
                (WEB3AdminMCAdminListResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request,
                "�Ǘ��҈ꗗ�\���Ɏ��s���܂���.",
                l_ex);
            return l_response;
        }
        
        log.exiting(STR_METHOD_NAME);      
        
        return l_response; 

    }
}
@
