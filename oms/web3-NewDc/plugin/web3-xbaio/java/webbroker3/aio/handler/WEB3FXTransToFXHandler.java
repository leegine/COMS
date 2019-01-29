head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.22.33;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3FXTransToFXHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : FX�ւ̐U�փn���h��(WEB3FXTransToFXHandler)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/1/20 ���z (���u) �V�K�쐬   
                 : 2006/02/24 ���_�O (���u) �d�l�ύX�E���f��460
*/

package webbroker3.aio.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.aio.message.WEB3FXTransToFXAskingRequest;
import webbroker3.aio.message.WEB3FXTransToFXAskingResponse;
import webbroker3.aio.message.WEB3FXTransToFXCompleteRequest;
import webbroker3.aio.message.WEB3FXTransToFXCompleteResponse;
import webbroker3.aio.message.WEB3FXTransToFXCompleteSoapRequest;
import webbroker3.aio.message.WEB3FXTransToFXCompleteSoapResponse;
import webbroker3.aio.message.WEB3FXTransToFXConfirmRequest;
import webbroker3.aio.message.WEB3FXTransToFXConfirmResponse;
import webbroker3.aio.service.delegate.WEB3FXTransToFXService;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.util.WEB3LogUtility;

/**
 * (FX�ւ̐U�փn���h��) <BR>
 * FX�ւ̐U�փn���h���N���X <BR>
 * 
 * @@author ���z(���u)
 * @@version 1.0
 */
public class WEB3FXTransToFXHandler implements MessageHandler
{
    /**
    * ���O�o�̓��[�e�B���e�B�B
    */    
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3FXTransToFXHandler.class);
    
    /**
     * @@roseuid 41E77F4A009C
     */
    public WEB3FXTransToFXHandler()
    {
    }

    /**
     * (�����m�F) <BR>
     * �U�֒����̔����R�����s���B <BR>
     * <BR>
     * FX�ւ̐U�փT�[�r�X���擾���Aexecute()���\�b�h���R�[������B <BR>
     * 
     * @@param l_request - ���N�G�X�g�f�[�^
     * @@return WEB3FXTransToFXConfirmResponse
     * @@roseuid 41BCF40E01DD
     */
    public WEB3FXTransToFXConfirmResponse orderConfirm(
        WEB3FXTransToFXConfirmRequest l_request)
    {
        String l_strMethodName = "orderConfirm(WEB3FXTransToFXConfirmRequest l_request)";
        log.entering(l_strMethodName);
        
        WEB3FXTransToFXService l_service;
        WEB3FXTransToFXConfirmResponse l_response;
        
        try
        {
            //FX�ւ̐U�փT�[�r�X���擾��
            l_service = 
                (WEB3FXTransToFXService)Services.getService(
                    WEB3FXTransToFXService.class);
        }
        //�T�[�r�X�ŗ�O�����������ꍇ�́A�G���[�������X�|���X���b�Z�[�W�ɐݒ肷��
        catch (Exception l_ex)
        {
            l_response = 
                (WEB3FXTransToFXConfirmResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request, 
                "__FX�ւ̐U�փT�[�r�X�̎擾�Ɏ��s���܂���__",
                l_response.errorInfo,
                l_ex);
            return l_response;
        }
        
        try
        {
            //execute()���\�b�h���R�[������
            l_response = (WEB3FXTransToFXConfirmResponse)l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response =
                (WEB3FXTransToFXConfirmResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request,
                "__error in call FX�ւ̐U�փT�[�r�X.execute__",
                l_ex);
            return l_response;
        }
        
        log.exiting(l_strMethodName);
        
        return l_response;
    }

    /**
     * (�����˗�) <BR>
     * �U�֒����̈˗��E�o�^�������s���B <BR>
     * <BR>
     * FX�ւ̐U�փT�[�r�X���擾���Aexecute()���\�b�h���R�[������B <BR>
     * 
     * @@param l_request - ���N�G�X�g�f�[�^
     * @@return WEB3FXTransToFXAskingResponse
     * @@roseuid 41BCF4F900C4
     */
    public WEB3FXTransToFXAskingResponse orderAsking(
        WEB3FXTransToFXAskingRequest l_request)
    {
        String l_strMethodName = "orderAsking(WEB3FXTransToFXAskingRequest l_request)";
        log.entering(l_strMethodName);
        
        WEB3FXTransToFXService l_service;
        WEB3FXTransToFXAskingResponse l_response;
        
        try
        {
            //FX�ւ̐U�փT�[�r�X���擾��
            l_service = 
                (WEB3FXTransToFXService)Services.getService(
                    WEB3FXTransToFXService.class);
        }
        //�T�[�r�X�ŗ�O�����������ꍇ�́A�G���[�������X�|���X���b�Z�[�W�ɐݒ肷��
        catch (Exception l_ex)
        {
            l_response = 
                (WEB3FXTransToFXAskingResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request, 
                "__FX�ւ̐U�փT�[�r�X�̎擾�Ɏ��s���܂���__",
                l_response.errorInfo,
                l_ex);
            return l_response;
        }
        
        try
        {
            //execute()���\�b�h���R�[������
            l_response = (WEB3FXTransToFXAskingResponse)l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response =
                (WEB3FXTransToFXAskingResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request,
                "__error in call FX�ւ̐U�փT�[�r�X.execute__",
                l_ex);
            return l_response;
        }
        
        log.exiting(l_strMethodName);
        
        return l_response;
    }

    /**
     * (��������) <BR>
     * �U�֒����̊����������s���B <BR>
     * <BR>
     * FX�ւ̐U�փT�[�r�X���擾���Aexecute()���\�b�h���R�[������B <BR>
     * 
     * @@param l_request - ���N�G�X�g�f�[�^
     * @@return WEB3FXTransToFXCompleteResponse
     * @@roseuid 41BCF5190150
     */
    public WEB3FXTransToFXCompleteResponse orderComplete(
        WEB3FXTransToFXCompleteRequest l_request)
    {
        String l_strMethodName = "orderComplete(WEB3FXTransToFXCompleteRequest l_request)";
        log.entering(l_strMethodName);
        
        WEB3FXTransToFXService l_service;
        WEB3FXTransToFXCompleteResponse l_response;
        
        try
        {
            //FX�ւ̐U�փT�[�r�X���擾��
            l_service = 
                (WEB3FXTransToFXService)Services.getService(
                    WEB3FXTransToFXService.class);
        }
        //�T�[�r�X�ŗ�O�����������ꍇ�́A�G���[�������X�|���X���b�Z�[�W�ɐݒ肷��
        catch (Exception l_ex)
        {
            l_response = 
                (WEB3FXTransToFXCompleteResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request, 
                "__FX�ւ̐U�փT�[�r�X�̎擾�Ɏ��s���܂���__",
                l_response.errorInfo,
                l_ex);
            return l_response;
        }
        
        try
        {
            //execute()���\�b�h���R�[������
            l_response = (WEB3FXTransToFXCompleteResponse)l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response =
                (WEB3FXTransToFXCompleteResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request,
                "__error in call FX�ւ̐U�փT�[�r�X.execute__",
                l_ex);
            return l_response;
        }
        
        log.exiting(l_strMethodName);
        
        return l_response;
    }

    /**
     * (���������iSOAP�ڑ��j) <BR>
     * �U�֒����̊����������s���B <BR>
     * ��SOAP�ڑ��ɂčs���B<BR>
     * <BR>
     * FX�ւ̐U�փT�[�r�X���擾���Aexecute()���\�b�h���R�[������B <BR>
     * 
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * @@return WEB3FXTransToFXCompleteResponse
     * @@roseuid 41BCF5190150
     */
    public WEB3FXTransToFXCompleteSoapResponse orderComplete(
        WEB3FXTransToFXCompleteSoapRequest l_request)
    {
        String STR_METHOD_NAME = "orderComplete(WEB3FXTransToFXCompleteSoapRequest l_request)";
        log.entering(STR_METHOD_NAME);
        
        WEB3FXTransToFXService l_service;
        WEB3FXTransToFXCompleteSoapResponse l_response;
        
        try
        {
            //FX�ւ̐U�փT�[�r�X���擾��
            l_service = 
                (WEB3FXTransToFXService) Services.getService(WEB3FXTransToFXService.class);
        }
        // �T�[�r�X�ŗ�O�����������ꍇ�́A�G���[�������X�|���X���b�Z�[�W�ɐݒ肷��
        catch (Exception l_ex)
        {
            l_response = (WEB3FXTransToFXCompleteSoapResponse) l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;    
            log.error(
                l_request,
                "FX�ւ̐U�փT�[�r�X�̎擾�Ɏ��s���܂����B",
                l_response.errorInfo,
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        
        try
        {
            //execute()���\�b�h���R�[������
            l_response = (WEB3FXTransToFXCompleteSoapResponse) l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response = (WEB3FXTransToFXCompleteSoapResponse) l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            l_response.errorMessage = l_ex.getErrorMessage();
            log.error(
                l_request,
                "(FX�ւ̐U��)�U�֒����̊��������iSOAP�ڑ��j�̎��{�Ɏ��s���܂����B",
                l_response.errorInfo,
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        catch (WEB3BaseRuntimeException l_ex)
        {
            l_response = (WEB3FXTransToFXCompleteSoapResponse) l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request,
                "(FX�ւ̐U��)�U�֒����̊��������iSOAP�ڑ��j�̎��{�Ɏ��s���܂����B",
                l_response.errorInfo,
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
}@
