head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.02.40.51;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	WEB3AdminMCAdminPermGrpDeleteHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǘ��҃��j���[����Ǘ��Ҍ����O���[�v�폜�n���h��(WEB3AdminMCAdminPermGrpDeleteHandler.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/11/23  �� �� �@@ (���u) �V�K�쐬
*/

package webbroker3.adminmc.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.util.WEB3LogUtility;

import webbroker3.adminmc.message.WEB3AdminMCAdminPermGrpDeleteConfirmResponse;
import webbroker3.adminmc.message.WEB3AdminMCAdminPermGrpDeleteConfirmRequest;
import webbroker3.adminmc.message.WEB3AdminMCAdminPermGrpDeleteCompleteResponse;
import webbroker3.adminmc.message.WEB3AdminMCAdminPermGrpDeleteCompleteRequest;
import webbroker3.adminmc.service.delegate.WEB3AdminMCAdminPermGrpDeleteService;


/**
 * (�Ǘ��҃��j���[����Ǘ��Ҍ����O���[�v�폜�n���h��)<BR>
 * �Ǘ��҃��j���[����Ǘ��Ҍ����O���[�v�폜�n���h��<BR>
 * @@author �����@@
 * @@version 1.0
 */

public class WEB3AdminMCAdminPermGrpDeleteHandler implements MessageHandler 
{

    /**
     *���O�o�̓��[�e�B���e�B�B
     */
    private WEB3LogUtility log = WEB3LogUtility.getInstance(WEB3AdminMCAdminPermGrpDeleteHandler.class);
    
    /**
     * @@roseuid 4198642F01B5
     */
    public WEB3AdminMCAdminPermGrpDeleteHandler() 
    {
     
    }
    
    /**
     * (�����O���[�v�폜�m�F)<BR>
     * �����O���[�v�폜�m�F���������{����B<BR>
     * <BR>
     * �Ǘ��҃��j���[����Ǘ��Ҍ����O���[�v�폜�T�[�r�X���擾���Cexecute���\�b�h���R�[�E
     * ����B<BR>
     * <BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * �Ǘ��҃��j���[����Ǘ��Ҍ����O���[�v�폜�m�F���N�G�X�g�f�[�^�I�u�W�F�N�g<BR>
     * @@return webbroker3.adminmc.message.WEB3AdminMCAdminPermGrpDeleteConfirmResponse
     * @@roseuid 4177370E02FD
     */
    public WEB3AdminMCAdminPermGrpDeleteConfirmResponse permGrpDeleteConfirm(WEB3AdminMCAdminPermGrpDeleteConfirmRequest l_request) 
    {
        final String STR_METHOD_NAME =
                getClass().getName() + ".permGrpDeleteConfirm(WEB3AdminMCAdminPermGrpDeleteConfirmRequest l_request)";
        log.entering(STR_METHOD_NAME);
        WEB3AdminMCAdminPermGrpDeleteConfirmResponse l_response = null;
        WEB3AdminMCAdminPermGrpDeleteService l_service = null;     
        try
        {
            l_service= (WEB3AdminMCAdminPermGrpDeleteService)Services.getService(WEB3AdminMCAdminPermGrpDeleteService.class); 
        }
        catch (Exception l_ex)
        {
            l_response = (WEB3AdminMCAdminPermGrpDeleteConfirmResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(l_request, "�Ǘ��҃��j���[����Ǘ��Ҍ����O���[�v�폜�T�[�r�XImpl�̎擾�Ɏ��s���܂���.", l_response.errorInfo, l_ex);
            return l_response;
        }
                 
        try
        {
            //execute()���\�b�h���R�[������
            l_response = (WEB3AdminMCAdminPermGrpDeleteConfirmResponse)l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response =
                (WEB3AdminMCAdminPermGrpDeleteConfirmResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request,
                "�Ǘ��Ҍ����O���[�v�폜�m�F�Ɏ��s���܂���.",
                l_ex);
            return l_response;
        }
        
        log.exiting(STR_METHOD_NAME);      
        
        return l_response; 
    }
    
    /**
     * (�����O���[�v�폜����)<BR>
     * �����O���[�v�폜�������������{����B<BR>
     * <BR>
     * �Ǘ��҃��j���[����Ǘ��Ҍ����O���[�v�폜�T�[�r�X���擾���Cexecute���\�b�h���R�[�E
     * ����B<BR>
     * <BR>
     * <BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * �Ǘ��҃��j���[����Ǘ��Ҍ����O���[�v�폜�������N�G�X�g�f�[�^�I�u�W�F�N�g<BR>
     * @@return webbroker3.adminmc.message.WEB3AdminMCAdminPermGrpDeleteCompleteResponse
     * @@roseuid 4177370E02FF
     */
    public WEB3AdminMCAdminPermGrpDeleteCompleteResponse permGrpDeleteComplete(WEB3AdminMCAdminPermGrpDeleteCompleteRequest l_request) 
    {
        final String STR_METHOD_NAME =
                getClass().getName() + ".permGrpDeleteComplete(WEB3AdminMCAdminPermGrpDeleteCompleteRequest l_request)";
        log.entering(STR_METHOD_NAME);
        WEB3AdminMCAdminPermGrpDeleteCompleteResponse l_response = null;
        WEB3AdminMCAdminPermGrpDeleteService l_service = null;     
        try
        {
            l_service= (WEB3AdminMCAdminPermGrpDeleteService)Services.getService(WEB3AdminMCAdminPermGrpDeleteService.class); 
        }
        catch (Exception l_ex)
        {
            l_response = (WEB3AdminMCAdminPermGrpDeleteCompleteResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(l_request, "�Ǘ��҃��j���[����Ǘ��Ҍ����O���[�v�폜�T�[�r�XImpl�̎擾�Ɏ��s���܂���.", l_response.errorInfo, l_ex);
            return l_response;
        }
                 
        try
        {
            //execute()���\�b�h���R�[������
            l_response = (WEB3AdminMCAdminPermGrpDeleteCompleteResponse)l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response =
                (WEB3AdminMCAdminPermGrpDeleteCompleteResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request,
                "�Ǘ��Ҍ����O���[�v�폜�����Ɏ��s���܂���.",
                l_ex);
            return l_response;
        }
        
        log.exiting(STR_METHOD_NAME);      
        
        return l_response; 
    }
}
@
