head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.22.08.02.24;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4e44d8854634b20;
filename	WEB3AdminPointPremiumRegistHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �i�i�o�^�n���h��(WEB3AdminPointPremiumRegistHandler.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/01/05 ���w��(���u) �V�K�쐬
*/
package webbroker3.point.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.point.message.WEB3AdminPointPremiumRegistCompleteRequest;
import webbroker3.point.message.WEB3AdminPointPremiumRegistCompleteResponse;
import webbroker3.point.message.WEB3AdminPointPremiumRegistConfirmRequest;
import webbroker3.point.message.WEB3AdminPointPremiumRegistConfirmResponse;
import webbroker3.point.message.WEB3AdminPointPremiumRegistInputRequest;
import webbroker3.point.message.WEB3AdminPointPremiumRegistInputResponse;
import webbroker3.point.service.delegate.WEB3AdminPointPremiumRegistService;
import webbroker3.util.WEB3LogUtility;

/**
 * (�i�i�o�^�n���h��)<BR>
 * �i�i�o�^�n���h���N���X<BR>
 *
 * @@author ���w��
 * @@version 1.0
 */
public class WEB3AdminPointPremiumRegistHandler implements MessageHandler 
{
    /**
     * ���O�o�̓��[�e�B���e�B�B<BR>
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(WEB3AdminPointPremiumRegistHandler.class);

    /**
     * (���͉�ʕ\��)<BR>
     * ���͉�ʂ̕\�����s���B<BR>
     * <BR>
     * �i�i�o�^�T�[�r�X���擾���Aexecute()���\�b�h���R�[������B<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * ���N�G�X�g�f�[�^<BR>
     * @@return webbroker3.point.message.WEB3AdminPointPremiumRegistInputResponse
     * @@roseuid 4192EA7F030B
     */
    public WEB3AdminPointPremiumRegistInputResponse inputScreenDisplay(WEB3AdminPointPremiumRegistInputRequest l_request) 
    {
        final String STR_METHOD_NAME = " inputScreenDisplay(WEB3AdminPointPremiumRegistInputRequest)";
        log.entering(STR_METHOD_NAME);
        
        WEB3AdminPointPremiumRegistInputResponse l_response = null;
        WEB3AdminPointPremiumRegistService l_service = null;

        try
        {
            l_service = (WEB3AdminPointPremiumRegistService)
                Services.getService(WEB3AdminPointPremiumRegistService.class);//Exception
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
            l_response = (WEB3AdminPointPremiumRegistInputResponse) l_request.createResponse();
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
                " �i�i�o�^�T�[�r�X���擾�Ɏ��s���܂����B",
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

            l_response = (WEB3AdminPointPremiumRegistInputResponse) l_service.execute(l_request);//WEB3BaseException
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
            l_response = (WEB3AdminPointPremiumRegistInputResponse)l_request.createResponse();
            if (l_response == null)
            {
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                    this.getClass().getName() + STR_METHOD_NAME);
            }

            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request, " �i�i�o�^�T�[�r�X���擾���N�G�X�g�Ɏ��s���܂����B", l_ex);
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
     * �i�i�o�^�T�[�r�X���擾���Aexecute()���\�b�h���R�[������B<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * ���N�G�X�g�f�[�^<BR>
     * 
     * @@return webbroker3.point.message.WEB3AdminPointPremiumRegistConfirmResponse
     * @@roseuid 4192EA7F0379
     */
    public WEB3AdminPointPremiumRegistConfirmResponse registConfirm(WEB3AdminPointPremiumRegistConfirmRequest l_request) 
    {
        final String STR_METHOD_NAME = " registConfirm(WEB3AdminPointPremiumRegistConfirmRequest)";
        log.entering(STR_METHOD_NAME);
        
        WEB3AdminPointPremiumRegistConfirmResponse l_response = null;
        WEB3AdminPointPremiumRegistService l_service = null;

        try
        {
            l_service = (WEB3AdminPointPremiumRegistService)
                Services.getService(WEB3AdminPointPremiumRegistService.class);//Exception
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
            l_response = (WEB3AdminPointPremiumRegistConfirmResponse) l_request.createResponse();
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
                " �i�i�o�^�T�[�r�X���擾�Ɏ��s���܂����B",
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

            l_response = (WEB3AdminPointPremiumRegistConfirmResponse) l_service.execute(l_request);//WEB3BaseException
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
            l_response = (WEB3AdminPointPremiumRegistConfirmResponse)l_request.createResponse();
            if (l_response == null)
            {
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                    this.getClass().getName() + STR_METHOD_NAME);
            }

            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request, " �i�i�o�^�T�[�r�X���擾���N�G�X�g�Ɏ��s���܂����B", l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        
        log.exiting(STR_METHOD_NAME);
        
        return l_response;
    }
    
    /**
     * (�o�^����)<BR>
     * �o�^���s���B<BR>
     * <BR>
     * �i�i�o�^�T�[�r�X���擾���Aexecute()���\�b�h���R�[������B<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * ���N�G�X�g�f�[�^<BR>
     * @@return webbroker3.point.message.WEB3AdminPointPremiumRegistCompleteResponse
     * @@roseuid 4192EA80009A
     */
    public WEB3AdminPointPremiumRegistCompleteResponse registComplete(WEB3AdminPointPremiumRegistCompleteRequest l_request) 
    {
        final String STR_METHOD_NAME = " registComplete(WEB3AdminPointPremiumRegistCompleteRequest)";
        log.entering(STR_METHOD_NAME);
        
        WEB3AdminPointPremiumRegistCompleteResponse l_response = null;
        WEB3AdminPointPremiumRegistService l_service = null;

        try
        {
            l_service = (WEB3AdminPointPremiumRegistService)
                Services.getService(WEB3AdminPointPremiumRegistService.class);//Exception
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
            l_response = (WEB3AdminPointPremiumRegistCompleteResponse) l_request.createResponse();
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
                " �i�i�o�^�T�[�r�X���擾�Ɏ��s���܂����B",
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

            l_response = (WEB3AdminPointPremiumRegistCompleteResponse) l_service.execute(l_request);//WEB3BaseException
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
            l_response = (WEB3AdminPointPremiumRegistCompleteResponse)l_request.createResponse();
            if (l_response == null)
            {
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                    this.getClass().getName() + STR_METHOD_NAME);
            }

            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request, " �i�i�o�^�T�[�r�X���擾���N�G�X�g�Ɏ��s���܂����B", l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        
        log.exiting(STR_METHOD_NAME);
        
        return l_response;
    }
}
@
