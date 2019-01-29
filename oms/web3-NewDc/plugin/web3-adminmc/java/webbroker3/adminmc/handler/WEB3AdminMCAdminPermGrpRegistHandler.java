head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.02.41.34;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	WEB3AdminMCAdminPermGrpRegistHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǘ��҃��j���[����Ǘ��Ҍ����O���[�v�o�^�n���h��(WEB3AdminMCAdminPermGrpRegistHandler.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/11/23  �� �� �@@ (���u) �V�K�쐬
*/

package webbroker3.adminmc.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.util.WEB3LogUtility;
import webbroker3.adminmc.service.delegate.WEB3AdminMCAdminPermGrpRegistService;
import webbroker3.adminmc.message.WEB3AdminMCAdminPermGrpRegistInputResponse;
import webbroker3.adminmc.message.WEB3AdminMCAdminPermGrpRegistInputRequest;
import webbroker3.adminmc.message.WEB3AdminMCAdminPermGrpRegistConfirmResponse;
import webbroker3.adminmc.message.WEB3AdminMCAdminPermGrpRegistConfirmRequest;
import webbroker3.adminmc.message.WEB3AdminMCAdminPermGrpRegistCompleteResponse;
import webbroker3.adminmc.message.WEB3AdminMCAdminPermGrpRegistCompleteRequest;

/**
 * (�Ǘ��҃��j���[����Ǘ��Ҍ����O���[�v�o�^�n���h��)<BR>
 * �Ǘ��҃��j���[����Ǘ��Ҍ����O���[�v�o�^�n���h��<BR>
 * @@author �����@@
 * @@version 1.0
 */

public class WEB3AdminMCAdminPermGrpRegistHandler implements MessageHandler 
{
    
    /**
     *���O�o�̓��[�e�B���e�B�B
     */
    private WEB3LogUtility log = WEB3LogUtility.getInstance(WEB3AdminMCAdminPermGrpRegistHandler.class);
     
    /**
     * @@roseuid 4198642F02DE
     */
    public WEB3AdminMCAdminPermGrpRegistHandler() 
    {
     
    }
    
    /**
     * (���͉�ʕ\��)<BR>
     * �Ǘ��Ҍ����O���[�v���͉�ʕ\�����������{����B<BR>
     * <BR>
     * �Ǘ��҃��j���[����Ǘ��Ҍ����O���[�v�o�^�T�[�r�X���擾���Cexecute���\�b�h���R�[�E
     * ����B<BR>
     * <BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * �Ǘ��҃��j���[����Ǘ��҃O���[�v�o�^���̓��N�G�X�g�f�[�^�I�u�W�F�N�g<BR>
     * 
     * @@return webbroker3.adminmc.message.WEB3AdminMCAdminPermGrpRegistInputResponse
     * @@throws WEB3BaseException
     * @@roseuid 4175EF8903BB
     */
    public WEB3AdminMCAdminPermGrpRegistInputResponse inputScreenDisplay(WEB3AdminMCAdminPermGrpRegistInputRequest l_request) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME =
                getClass().getName() + " inputScreenDisplay(WEB3AdminMCAdminPermGrpRegistInputRequest l_request)";
        log.entering(STR_METHOD_NAME);
        WEB3AdminMCAdminPermGrpRegistInputResponse l_response = null;
        WEB3AdminMCAdminPermGrpRegistService l_service = null;     
        try
        {
            l_service= (WEB3AdminMCAdminPermGrpRegistService)Services.getService(WEB3AdminMCAdminPermGrpRegistService.class); 
        }
        catch (Exception l_ex)
        {
            l_response = (WEB3AdminMCAdminPermGrpRegistInputResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(l_request, "�Ǘ��҃��j���[����Ǘ��Ҍ����O���[�v�o�^�T�[�r�XImpl�̎擾�Ɏ��s���܂���.", l_response.errorInfo, l_ex);
            return l_response;
        }
                 
        try
        {
            //execute()���\�b�h���R�[������
            l_response = (WEB3AdminMCAdminPermGrpRegistInputResponse)l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response =
                (WEB3AdminMCAdminPermGrpRegistInputResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request,
                "�Ǘ��Ҍ����O���[�v���͉�ʕ\���Ɏ��s���܂���.",
                l_ex);
            return l_response;
        }
        
        log.exiting(STR_METHOD_NAME);      
        
        return l_response; 
    }
    
    /**
     * (�����O���[�v�o�^�m�F)<BR>
     * �����O���[�v�o�^�m�F���������{����B<BR>
     * <BR>
     * �Ǘ��҃��j���[����Ǘ��Ҍ����O���[�v�o�^�T�[�r�X���擾���Cexecute���\�b�h���R�[�E
     * ����B<BR>
     * <BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * �Ǘ��҃��j���[����Ǘ��҃O���[�v�o�^�m�F���N�G�X�g�f�[�^�I�u�W�F�N�g<BR>
     * @@return webbroker3.adminmc.message.WEB3AdminMCAdminPermGrpRegistConfirmResponse
     * @@roseuid 4175EF8903CB
     */
    public WEB3AdminMCAdminPermGrpRegistConfirmResponse permGrpRegistConfirm(WEB3AdminMCAdminPermGrpRegistConfirmRequest l_request) 
    {
        final String STR_METHOD_NAME =
                getClass().getName() + " permGrpRegistConfirm(WEB3AdminMCAdminPermGrpRegistConfirmRequest l_request) ";
        log.entering(STR_METHOD_NAME);
        WEB3AdminMCAdminPermGrpRegistConfirmResponse l_response = null;
        WEB3AdminMCAdminPermGrpRegistService l_service = null;     
        try
        {
            l_service= (WEB3AdminMCAdminPermGrpRegistService)Services.getService(WEB3AdminMCAdminPermGrpRegistService.class); 
        }
        catch (Exception l_ex)
        {
            l_response = (WEB3AdminMCAdminPermGrpRegistConfirmResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(l_request, "�Ǘ��҃��j���[����Ǘ��Ҍ����O���[�v�o�^�T�[�r�XImpl�̎擾�Ɏ��s���܂���.", l_response.errorInfo, l_ex);
            return l_response;
        }
                 
        try
        {
            //execute()���\�b�h���R�[������
            l_response = (WEB3AdminMCAdminPermGrpRegistConfirmResponse)l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response =
                (WEB3AdminMCAdminPermGrpRegistConfirmResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request,
                "�����O���[�v�o�^�m�F�Ɏ��s���܂���.",
                l_ex);
            return l_response;
        }
        
        log.exiting(STR_METHOD_NAME);      
        
        return l_response; 
    }
    
    /**
     * (�����O���[�v�o�^����)<BR>
     * �����O���[�v�o�^�������������{����B<BR>
     * <BR>
     * �Ǘ��҃��j���[����Ǘ��Ҍ����O���[�v�o�^�T�[�r�X���擾���Cexecute���\�b�h���R�[�E
     * ����B<BR>
     * <BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * �Ǘ��҃��j���[����Ǘ��҃O���[�v�o�^�������N�G�X�g�f�[�^�I�u�W�F�N�g<BR>
     * @@return webbroker3.adminmc.message.WEB3AdminMCAdminPermGrpRegistCompleteResponse
     * @@roseuid 4175EF8903CD
     */
    public WEB3AdminMCAdminPermGrpRegistCompleteResponse permGrpRegistComplete(WEB3AdminMCAdminPermGrpRegistCompleteRequest l_request) 
    {
        final String STR_METHOD_NAME =
                getClass().getName() + " permGrpRegistComplete(WEB3AdminMCAdminPermGrpRegistCompleteRequest l_request)";
        log.entering(STR_METHOD_NAME);
        WEB3AdminMCAdminPermGrpRegistCompleteResponse l_response = null;
        WEB3AdminMCAdminPermGrpRegistService l_service = null;     
        try
        {
            l_service= (WEB3AdminMCAdminPermGrpRegistService)Services.getService(WEB3AdminMCAdminPermGrpRegistService.class); 
        }
        catch (Exception l_ex)
        {
            l_response = (WEB3AdminMCAdminPermGrpRegistCompleteResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(l_request, "�Ǘ��҃��j���[����Ǘ��Ҍ����O���[�v�o�^�T�[�r�XImpl�̎擾�Ɏ��s���܂���.", l_response.errorInfo, l_ex);
            return l_response;
        }
                 
        try
        {
            //execute()���\�b�h���R�[������
            l_response = (WEB3AdminMCAdminPermGrpRegistCompleteResponse)l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response =
                (WEB3AdminMCAdminPermGrpRegistCompleteResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request,
                "�����O���[�v�o�^�����Ɏ��s���܂���.",
                l_ex);
            return l_response;
        }
        
        log.exiting(STR_METHOD_NAME);      
        
        return l_response; 
    }
}
@
