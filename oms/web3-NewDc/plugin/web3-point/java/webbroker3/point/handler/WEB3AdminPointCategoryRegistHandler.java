head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.22.08.02.11;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4e44d8854634b20;
filename	WEB3AdminPointCategoryRegistHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �J�e�S���[�o�^�n���h��(WEB3AdminPointCategoryRegistHandler.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/01/05 ���w��(���u) �V�K�쐬
*/
package webbroker3.point.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.point.message.WEB3AdminPointCategoryRegistCompleteRequest;
import webbroker3.point.message.WEB3AdminPointCategoryRegistCompleteResponse;
import webbroker3.point.message.WEB3AdminPointCategoryRegistConfirmRequest;
import webbroker3.point.message.WEB3AdminPointCategoryRegistConfirmResponse;
import webbroker3.point.message.WEB3AdminPointCategoryRegistInputRequest;
import webbroker3.point.message.WEB3AdminPointCategoryRegistInputResponse;
import webbroker3.point.service.delegate.WEB3AdminPointCategoryRegistService;
import webbroker3.util.WEB3LogUtility;

/**
 * (�J�e�S���[�o�^�n���h��)<BR>
 * �J�e�S���[�o�^�n���h���N���X<BR>
 *
 * @@author ���w��
 * @@version 1.0
 */
public class WEB3AdminPointCategoryRegistHandler implements MessageHandler 
{
    /**
     * Logger
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(WEB3AdminPointCategoryRegistHandler.class);
    
    /**
     * (���͉�ʕ\��)<BR>
     * ���͉�ʂ̕\�����s���B<BR>
     * <BR>
     * �J�e�S���[�o�^�T�[�r�X���擾���Aexecute()���\�b�h���R�[������B<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * ���N�G�X�g�f�[�^<BR>
     * 
     * @@return webbroker3.point.message.WEB3AdminPointCategoryRegistInputResponse
     * @@roseuid 4191A8480214
     */
    public WEB3AdminPointCategoryRegistInputResponse inputScreenDisplay(WEB3AdminPointCategoryRegistInputRequest l_request) 
    {
        final String STR_METHOD_NAME = " inputScreenDisplay(WEB3AdminPointCategoryRegistInputRequest l_request)";
        log.entering(STR_METHOD_NAME);
        
        WEB3AdminPointCategoryRegistInputResponse l_response = null;
        WEB3AdminPointCategoryRegistService l_service = null;
        
        try
        {
            l_service = (WEB3AdminPointCategoryRegistService)
                Services.getService(WEB3AdminPointCategoryRegistService.class);//Exception
        }        
        // �T�[�r�X�ŗ�O�����������ꍇ�́A�G���[�������X�|���X���b�Z�[�W�ɐݒ肷��
        catch (Exception l_ex)
        {
            if (l_request == null)
            {
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                    this.getClass().getName() + STR_METHOD_NAME);
            }
            l_response = (WEB3AdminPointCategoryRegistInputResponse) l_request.createResponse();
            if (l_response == null)
            {
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                    this.getClass().getName() + STR_METHOD_NAME);
            }

            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;    
            log.error(
                l_request, 
                " �J�e�S���[�o�^�T�[�r�X���擾�Ɏ��s���܂����B",
                l_response.errorInfo, 
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        
        try
        {
            if (l_service == null)
            {
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                    this.getClass().getName() + STR_METHOD_NAME);
            }

            l_response = (WEB3AdminPointCategoryRegistInputResponse)l_service.execute(l_request);//WEB3BaseException
        }
        catch (WEB3BaseException l_ex)
        {
            if (l_request == null)
            {
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                    this.getClass().getName() + STR_METHOD_NAME);
            }
            l_response = (WEB3AdminPointCategoryRegistInputResponse)l_request.createResponse();
            if (l_response == null)
            {
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                    this.getClass().getName() + STR_METHOD_NAME);
            }

            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request, " �J�e�S���[�o�^�T�[�r�X���擾���N�G�X�g�Ɏ��s���܂����B", l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        
        log.exiting(STR_METHOD_NAME);
        
        return l_response;
    }
    
    /**
     * (�o�^�m�F)<BR>
     * �o�^�̐R�����s���B<BR>
     * <BR>
     * �J�e�S���[�o�^�T�[�r�X���擾���Aexecute()���\�b�h���R�[������B<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * ���N�G�X�g�f�[�^<BR>
     * 
     * @@return webbroker3.point.message.WEB3AdminPointCategoryRegistConfirmResponse
     * @@roseuid 41917B2F0017
     */
    public WEB3AdminPointCategoryRegistConfirmResponse registConfirm(WEB3AdminPointCategoryRegistConfirmRequest l_request) 
    {
        final String STR_METHOD_NAME = " registConfirm(WEB3AdminPointCategoryRegistConfirmRequest l_request)";
        log.entering(STR_METHOD_NAME);
        
        WEB3AdminPointCategoryRegistConfirmResponse l_response = null;
        WEB3AdminPointCategoryRegistService l_service = null;

        try
        {
            l_service = (WEB3AdminPointCategoryRegistService)
                Services.getService(WEB3AdminPointCategoryRegistService.class);//Exception
        }        
        // �T�[�r�X�ŗ�O�����������ꍇ�́A�G���[�������X�|���X���b�Z�[�W�ɐݒ肷��
        catch (Exception l_ex)
        {
            if (l_request == null)
            {
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                    this.getClass().getName() + STR_METHOD_NAME);
            }
            l_response = (WEB3AdminPointCategoryRegistConfirmResponse) l_request.createResponse();
            if (l_response == null)
            {
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                    this.getClass().getName() + STR_METHOD_NAME);
            }

            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;    
            log.error(
                l_request, 
                " �J�e�S���[�o�^�T�[�r�X���擾�Ɏ��s���܂����B",
                l_response.errorInfo, 
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        
        try
        {
            if (l_service == null)
            {
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                    this.getClass().getName() + STR_METHOD_NAME);
            }

            l_response = (WEB3AdminPointCategoryRegistConfirmResponse)l_service.execute(l_request);//WEB3BaseException
        }
        catch (WEB3BaseException l_ex)
        {
            if (l_request == null)
            {
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                    this.getClass().getName() + STR_METHOD_NAME);
            }
            l_response = (WEB3AdminPointCategoryRegistConfirmResponse)l_request.createResponse();
            if (l_response == null)
            {
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                    this.getClass().getName() + STR_METHOD_NAME);
            }

            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request, " �J�e�S���[�o�^�T�[�r�X���擾���N�G�X�g�Ɏ��s���܂����B", l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        
        log.exiting(STR_METHOD_NAME);
        
        return l_response;
    
    }
    
    /**
     * (�o�^����)<BR>
     * �J�e�S���[�̓o�^���s���B<BR>
     * <BR>
     * �J�e�S���[�o�^�T�[�r�X���擾���Aexecute()���\�b�h���R�[������B<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * ���N�G�X�g�f�[�^<BR>
     * 
     * @@return webbroker3.point.message.WEB3AdminPointCategoryRegistCompleteResponse
     * @@roseuid 41917BA603B1
     */
    public WEB3AdminPointCategoryRegistCompleteResponse registComplete(WEB3AdminPointCategoryRegistCompleteRequest l_request) 
    {
        final String STR_METHOD_NAME = " registComplete(WEB3AdminPointCategoryRegistCompleteRequest l_request)";
        log.entering(STR_METHOD_NAME);
        
        WEB3AdminPointCategoryRegistCompleteResponse l_response = null;
        WEB3AdminPointCategoryRegistService l_service = null;

        try
        {
            l_service = (WEB3AdminPointCategoryRegistService)
                Services.getService(WEB3AdminPointCategoryRegistService.class);//Exception
        }        
        // �T�[�r�X�ŗ�O�����������ꍇ�́A�G���[�������X�|���X���b�Z�[�W�ɐݒ肷��
        catch (Exception l_ex)
        {
            if (l_request == null)
            {
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                    this.getClass().getName() + STR_METHOD_NAME);
            }
            l_response = (WEB3AdminPointCategoryRegistCompleteResponse) l_request.createResponse();
            if (l_response == null)
            {
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                    this.getClass().getName() + STR_METHOD_NAME);
            }

            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;    
            log.error(
                l_request, 
                " �J�e�S���[�o�^�T�[�r�X���擾�Ɏ��s���܂����B",
                l_response.errorInfo, 
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        
        try
        {
            if (l_service == null)
            {
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                    this.getClass().getName() + STR_METHOD_NAME);
            }

            l_response = (WEB3AdminPointCategoryRegistCompleteResponse)l_service.execute(l_request);//WEB3BaseException
        }
        catch (WEB3BaseException l_ex)
        {
            if (l_request == null)
            {
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                    this.getClass().getName() + STR_METHOD_NAME);
            }
            l_response = (WEB3AdminPointCategoryRegistCompleteResponse)l_request.createResponse();
            if (l_response == null)
            {
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                    this.getClass().getName() + STR_METHOD_NAME);
            }

            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request, " �J�e�S���[�o�^�T�[�r�X���擾���N�G�X�g�Ɏ��s���܂����B", l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        
        log.exiting(STR_METHOD_NAME);
        
        return l_response;
    }
}
@
