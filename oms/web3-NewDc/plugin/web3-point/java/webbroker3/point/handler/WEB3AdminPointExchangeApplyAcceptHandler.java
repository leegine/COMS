head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.22.08.03.02;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4e44d8854634b20;
filename	WEB3AdminPointExchangeApplyAcceptHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �|�C���g������t�n���h��(WEB3AdminPointExchangeApplyAcceptHandler.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/01/05 �A�C��(���u) �V�K�쐬
*/
package webbroker3.point.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.point.message.WEB3AdminPointExchangeAcceptRequest;
import webbroker3.point.message.WEB3AdminPointExchangeAcceptResponse;
import webbroker3.point.message.WEB3AdminPointExchangeCancelCompleteRequest;
import webbroker3.point.message.WEB3AdminPointExchangeCancelCompleteResponse;
import webbroker3.point.message.WEB3AdminPointExchangeCancelConfirmRequest;
import webbroker3.point.message.WEB3AdminPointExchangeCancelConfirmResponse;
import webbroker3.point.message.WEB3AdminPointExchangeCancelReleaseCompleteRequest;
import webbroker3.point.message.WEB3AdminPointExchangeCancelReleaseCompleteResponse;
import webbroker3.point.message.WEB3AdminPointExchangeCancelReleaseConfirmRequest;
import webbroker3.point.message.WEB3AdminPointExchangeCancelReleaseConfirmResponse;
import webbroker3.point.message.WEB3AdminPointExchangeStateReferenceRequest;
import webbroker3.point.message.WEB3AdminPointExchangeStateReferenceResponse;
import webbroker3.point.service.delegate.WEB3AdminPointExchangeApplyAcceptService;
import webbroker3.util.WEB3LogUtility;

/**
 * (�|�C���g������t�n���h��)<BR>
 * �|�C���g������t�n���h���N���X<BR>
 *
 * @@author �A�C��
 * @@version 1.0
 */
public class WEB3AdminPointExchangeApplyAcceptHandler implements MessageHandler 
{

    /**
     * (���O�o�̓��[�e�B���e�B�B)<BR>
     */
    private static WEB3LogUtility log = 
        WEB3LogUtility.getInstance(WEB3AdminPointExchangeApplyAcceptHandler.class);

    /**
     * (�ꗗ��ʕ\��)<BR>
     * �ꗗ��ʂ̕\�����s���B<BR>
     * <BR>
     * �|�C���g������t�T�[�r�X���擾���Aexecute()���\�b�h���R�[������B<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * ���N�G�X�g�f�[�^<BR>
     * 
     * 
     * @@return webbroker3.point.message.WEB3AdminPointExchangeStateReferenceResponse
     * @@roseuid 419B0CE100F1
     */
    public WEB3AdminPointExchangeStateReferenceResponse listScreenDisplay(WEB3AdminPointExchangeStateReferenceRequest l_request) 
    {
        final String STR_METHOD_NAME = " listScreenDisplay(WEB3AdminPointExchangeStateReferenceRequest )";    
        log.entering(STR_METHOD_NAME);
        
        WEB3AdminPointExchangeStateReferenceResponse l_response = null;
        WEB3AdminPointExchangeApplyAcceptService l_service = null;      

        try
        {
            //get service
            l_service = 
                (WEB3AdminPointExchangeApplyAcceptService)Services.getService(
                    WEB3AdminPointExchangeApplyAcceptService.class);
        }
        catch (Exception l_ex)
        {
            if (l_request == null)
            {
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                    this.getClass().getName() + STR_METHOD_NAME);
            }
            l_response = (WEB3AdminPointExchangeStateReferenceResponse)l_request.createResponse();
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
                "�|�C���g������t�T�[�r�X�̎擾�Ɏ��s���܂����B",
                l_response.errorInfo,
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;                  
        }

        try
        {
            //execute()���\�b�h���R�[������
            if (l_service == null)
            {
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                    this.getClass().getName() + STR_METHOD_NAME);
            }
            l_response = (WEB3AdminPointExchangeStateReferenceResponse)l_service.execute(l_request);//WEB3BaseException
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
            l_response = (WEB3AdminPointExchangeStateReferenceResponse)l_request.createResponse();
            if (l_response == null)
            {
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                    this.getClass().getName() + STR_METHOD_NAME);
            }
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request, "�ꗗ��ʕ\���Ɏ��s���܂����B", l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

        log.exiting(STR_METHOD_NAME);
        return l_response;    
    }
    
    /**
     * (��t����)<BR>
     * ��t�̓o�^���s���B<BR>
     * <BR>
     * �|�C���g������t�T�[�r�X���擾���Aexecute()���\�b�h���R�[������B<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * ���N�G�X�g�f�[�^<BR>
     * 
     * @@return webbroker3.point.message.WEB3AdminPointExchangeAcceptResponse
     * @@roseuid 419B0CE10110
     */
    public WEB3AdminPointExchangeAcceptResponse acceptComplete(WEB3AdminPointExchangeAcceptRequest l_request) 
    {
        final String STR_METHOD_NAME = " acceptComplete(WEB3AdminPointExchangeAcceptRequest )";    
        log.entering(STR_METHOD_NAME);
        
        WEB3AdminPointExchangeAcceptResponse l_response = null;
        WEB3AdminPointExchangeApplyAcceptService l_service = null;      

        try
        {
            //get service
            l_service = 
                (WEB3AdminPointExchangeApplyAcceptService)Services.getService(
                    WEB3AdminPointExchangeApplyAcceptService.class);
        }
        catch (Exception l_ex)
        {
            if (l_request == null)
            {
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                    this.getClass().getName() + STR_METHOD_NAME);
            }
            l_response = (WEB3AdminPointExchangeAcceptResponse)l_request.createResponse();
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
                "�|�C���g������t�T�[�r�X�̎擾�Ɏ��s���܂����B",
                l_response.errorInfo,
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;                  
        }

        try
        {
            //execute()���\�b�h���R�[������
            if (l_service == null)
            {
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                    this.getClass().getName() + STR_METHOD_NAME);
            }
            l_response = (WEB3AdminPointExchangeAcceptResponse)l_service.execute(l_request);//WEB3BaseException
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
            l_response = (WEB3AdminPointExchangeAcceptResponse)l_request.createResponse();
            if (l_response == null)
            {
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                    this.getClass().getName() + STR_METHOD_NAME);
            }
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request, "��t�����Ɏ��s���܂����B", l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

        log.exiting(STR_METHOD_NAME);
        return l_response;    
    }
    
    /**
     * (����m�F)<BR>
     * ����̐R�����s���B<BR>
     * <BR>
     * �|�C���g������t�T�[�r�X���擾���Aexecute()���\�b�h���R�[������B<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * ���N�G�X�g�f�[�^<BR>
     * 
     * @@return webbroker3.point.message.WEB3AdminPointExchangeCancelConfirmResponse
     * @@roseuid 419B0CE10120
     */
    public WEB3AdminPointExchangeCancelConfirmResponse cancelConfirm(WEB3AdminPointExchangeCancelConfirmRequest l_request) 
    {
        final String STR_METHOD_NAME = " cancelConfirm(WEB3AdminPointExchangeCancelConfirmRequest )";    
        log.entering(STR_METHOD_NAME);
        
        WEB3AdminPointExchangeCancelConfirmResponse l_response = null;
        WEB3AdminPointExchangeApplyAcceptService l_service = null;      

        try
        {
            //get service
            l_service = 
                (WEB3AdminPointExchangeApplyAcceptService)Services.getService(
                    WEB3AdminPointExchangeApplyAcceptService.class);
        }
        catch (Exception l_ex)
        {
            if (l_request == null)
            {
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                    this.getClass().getName() + STR_METHOD_NAME);
            }
            l_response = (WEB3AdminPointExchangeCancelConfirmResponse)l_request.createResponse();
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
                "�|�C���g������t�T�[�r�X�̎擾�Ɏ��s���܂����B",
                l_response.errorInfo,
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;                  
        }

        try
        {
            //execute()���\�b�h���R�[������
            l_response = (WEB3AdminPointExchangeCancelConfirmResponse)l_service.execute(l_request);//WEB3BaseException
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
            l_response = (WEB3AdminPointExchangeCancelConfirmResponse)l_request.createResponse();
            if (l_response == null)
            {
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                    this.getClass().getName() + STR_METHOD_NAME);
            }
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request, "����m�F�Ɏ��s���܂����B", l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

        log.exiting(STR_METHOD_NAME);
        return l_response;    
    }
    
    /**
     * (�������)<BR>
     * ����̓o�^���s���B<BR>
     * <BR>
     * �|�C���g������t�T�[�r�X���擾���Aexecute()���\�b�h���R�[������B<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * ���N�G�X�g�f�[�^<BR>
     * 
     * @@return webbroker3.point.message.WEB3AdminPointExchangeCancelCompleteResponse
     * @@roseuid 419B0CE1013F
     */
    public WEB3AdminPointExchangeCancelCompleteResponse cancelComplete(WEB3AdminPointExchangeCancelCompleteRequest l_request) 
    {
        final String STR_METHOD_NAME = " cancelComplete(WEB3AdminPointExchangeCancelCompleteRequest )";    
        log.entering(STR_METHOD_NAME);
        
        WEB3AdminPointExchangeCancelCompleteResponse l_response = null;
        WEB3AdminPointExchangeApplyAcceptService l_service = null;      

        try
        {
            //get service
            l_service = 
                (WEB3AdminPointExchangeApplyAcceptService)Services.getService(
                    WEB3AdminPointExchangeApplyAcceptService.class);
        }
        catch (Exception l_ex)
        {
            if (l_request == null)
            {
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                    this.getClass().getName() + STR_METHOD_NAME);
            }
            l_response = (WEB3AdminPointExchangeCancelCompleteResponse)l_request.createResponse();
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
                "�|�C���g������t�T�[�r�X�̎擾�Ɏ��s���܂����B",
                l_response.errorInfo,
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;                  
        }

        try
        {
            //execute()���\�b�h���R�[������
            if (l_service == null)
            {
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                    this.getClass().getName() + STR_METHOD_NAME);
            }
            l_response = (WEB3AdminPointExchangeCancelCompleteResponse)l_service.execute(l_request);//WEB3BaseException
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
            l_response = (WEB3AdminPointExchangeCancelCompleteResponse)l_request.createResponse();
            if (l_response == null)
            {
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                    this.getClass().getName() + STR_METHOD_NAME);
            }
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request, "��������Ɏ��s���܂����B", l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

        log.exiting(STR_METHOD_NAME);
        return l_response;    
    }
    
    /**
     * (��������m�F)<BR>
     * ��������̐R�����s���B<BR>
     * <BR>
     * �|�C���g������t�T�[�r�X���擾���Aexecute()���\�b�h���R�[������B<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * ���N�G�X�g�f�[�^<BR>
     * 
     * @@return webbroker3.point.message.WEB3AdminPointExchangeCancelReleaseConfirmResponse
     * @@roseuid 419B0CE1014F
     */
    public WEB3AdminPointExchangeCancelReleaseConfirmResponse cancelReleaseConfirm(WEB3AdminPointExchangeCancelReleaseConfirmRequest l_request) 
    {
        final String STR_METHOD_NAME = " cancelReleaseConfirm(WEB3AdminPointExchangeCancelReleaseConfirmRequest )";    
        log.entering(STR_METHOD_NAME);
        
        WEB3AdminPointExchangeCancelReleaseConfirmResponse l_response = null;
        WEB3AdminPointExchangeApplyAcceptService l_service = null;      

        try
        {
            //get service
            l_service = 
                (WEB3AdminPointExchangeApplyAcceptService)Services.getService(
                    WEB3AdminPointExchangeApplyAcceptService.class);
        }
        catch (Exception l_ex)
        {
            if (l_request == null)
            {
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                    this.getClass().getName() + STR_METHOD_NAME);
            }           
            l_response = (WEB3AdminPointExchangeCancelReleaseConfirmResponse)l_request.createResponse();
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
                "�|�C���g������t�T�[�r�X�̎擾�Ɏ��s���܂����B",
                l_response.errorInfo,
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;                  
        }

        try
        {
            //execute()���\�b�h���R�[������
            if (l_service == null)
            {
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                    this.getClass().getName() + STR_METHOD_NAME);
            }
            l_response = (WEB3AdminPointExchangeCancelReleaseConfirmResponse)l_service.execute(l_request);//WEB3BaseException
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
            l_response = (WEB3AdminPointExchangeCancelReleaseConfirmResponse)l_request.createResponse();
            if (l_response == null)
            {
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                    this.getClass().getName() + STR_METHOD_NAME);
            }
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request, "��������m�F�Ɏ��s���܂����B", l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

        log.exiting(STR_METHOD_NAME);
        return l_response;    
    }
    
    /**
     * (�����������)<BR>
     * ��������̓o�^���s���B<BR>
     * <BR>
     * �|�C���g������t�T�[�r�X���擾���Aexecute()���\�b�h���R�[������B<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * ���N�G�X�g�f�[�^<BR>
     * 
     * @@return webbroker3.point.message.WEB3AdminPointExchangeCancelReleaseCompleteResponse
     * @@roseuid 419B0CE1016E
     */
    public WEB3AdminPointExchangeCancelReleaseCompleteResponse cancelReleaseComplete(WEB3AdminPointExchangeCancelReleaseCompleteRequest l_request) 
    {
        final String STR_METHOD_NAME = " cancelReleaseComplete(WEB3AdminPointExchangeCancelReleaseCompleteRequest )";    
        log.entering(STR_METHOD_NAME);
        
        WEB3AdminPointExchangeCancelReleaseCompleteResponse l_response = null;
        WEB3AdminPointExchangeApplyAcceptService l_service = null;      

        try
        {
            //get service
            l_service = 
                (WEB3AdminPointExchangeApplyAcceptService)Services.getService(
                    WEB3AdminPointExchangeApplyAcceptService.class);
        }
        catch (Exception l_ex)
        {
            if (l_request == null)
            {
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                    this.getClass().getName() + STR_METHOD_NAME);
            }
            l_response = (WEB3AdminPointExchangeCancelReleaseCompleteResponse)l_request.createResponse();
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
                "�|�C���g������t�T�[�r�X�̎擾�Ɏ��s���܂����B",
                l_response.errorInfo,
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;                  
        }

        try
        {
            //execute()���\�b�h���R�[������
            l_response = (WEB3AdminPointExchangeCancelReleaseCompleteResponse)l_service.execute(l_request);//WEB3BaseException
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
            l_response = (WEB3AdminPointExchangeCancelReleaseCompleteResponse)l_request.createResponse();
            if (l_response == null)
            {
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                    this.getClass().getName() + STR_METHOD_NAME);
            }
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request, "������������Ɏ��s���܂����B", l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

        log.exiting(STR_METHOD_NAME);
        return l_response;    
    }
}
@
