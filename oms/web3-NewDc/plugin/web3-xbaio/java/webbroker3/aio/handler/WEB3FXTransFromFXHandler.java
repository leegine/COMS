head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.24.09;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3FXTransFromFXHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
 Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
 File Name        : FX����U�փn���h��(WEB3FXTransFromFXHandler)
 Author Name      : Daiwa Institute of Research
 Revesion History : 2005/01/21 ����(���u) �V�K�쐬
 Revesion History : 2008/04/08 ���u��(���u) ���f��No.832
 */

package webbroker3.aio.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.aio.message.WEB3FXTransFromFXAskingRequest;
import webbroker3.aio.message.WEB3FXTransFromFXAskingResponse;
import webbroker3.aio.message.WEB3FXTransFromFXCompleteRequest;
import webbroker3.aio.message.WEB3FXTransFromFXCompleteResponse;
import webbroker3.aio.message.WEB3FXTransFromFXCompleteSoapRequest;
import webbroker3.aio.message.WEB3FXTransFromFXCompleteSoapResponse;
import webbroker3.aio.message.WEB3FXTransFromFXConfirmRequest;
import webbroker3.aio.message.WEB3FXTransFromFXConfirmResponse;
import webbroker3.aio.service.delegate.WEB3FXTransFromFXService;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.util.WEB3LogUtility;

/**
 * (FX����U�փn���h��) <BR>
 * FX����U�փn���h���N���X
 * 
 * @@author ����(���u)
 * @@version 1.0
 */

public class WEB3FXTransFromFXHandler implements MessageHandler
{
    /**
     * ���O�o�̓��[�e�B���e�B�B
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3FXTransFromFXHandler.class);
   
    /**
     * (�����m�F) <BR>
     * �U�֒����̔����R�����s���B <BR>
     * <BR>
     * FX����U�փT�[�r�X���擾���Aexecute()���\�b�h���R�[������B <BR>
     * 
     * @@param l_request - ���N�G�X�g�f�[�^
     * @@return WEB3FXTransFromFXConfirmResponse
     * @@roseuid 41BCF8760141
     */
    public WEB3FXTransFromFXConfirmResponse orderConfirm(
        WEB3FXTransFromFXConfirmRequest l_request)
    {
        final String STR_METHOD_NAME =
            "orderConfirm(WEB3FXTransFromFXConfirmRequest l_request) ";
        log.entering(STR_METHOD_NAME);
        
        //FX����U�փT�[�r�X
        WEB3FXTransFromFXService l_service = null;
        //FX����U�֊m�F���X�|���X
        WEB3FXTransFromFXConfirmResponse l_response = null;
        try
        {
            l_service =
                (WEB3FXTransFromFXService) Services.getService(
            WEB3FXTransFromFXService.class);
        }
        catch (Exception l_ex)
        {
            //�T�[�r�X�ŗ�O�����������ꍇ�́A
            //�G���[�������X�|���X���b�Z�[�W�ɐݒ肷��
            l_response =
                (WEB3FXTransFromFXConfirmResponse) l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "FX����U�փT�[�r�X�̎擾�Ɏ��s���܂����B",
                l_response.errorInfo,
                l_ex);
            return l_response;
        }
        try
        {
            l_response =
                (WEB3FXTransFromFXConfirmResponse) l_service.execute(
                    l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response =
                (WEB3FXTransFromFXConfirmResponse) l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request, 
                "FX����U�֊m�F���X�|���X�̏����Ɏ��s���܂����B", 
                l_ex);
            return l_response;
        }
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (�����˗�) <BR>
     * �U�֒����̈˗��������s���B <BR>
     * <BR>
     * FX����U�փT�[�r�X���擾���Aexecute()���\�b�h���R�[������B <BR>
     * 
     * @@param l_request - ���N�G�X�g�f�[�^
     * @@return WEB3FXTransFromFXAskingResponse
     * @@roseuid 41BCF8760143
     */
    public WEB3FXTransFromFXAskingResponse orderAsking(
        WEB3FXTransFromFXAskingRequest l_request)
    {
        final String STR_METHOD_NAME =
            "orderAsking(WEB3FXTransFromFXAskingRequest l_request)";
        log.entering(STR_METHOD_NAME);
        
        //FX����U�փT�[�r�X
        WEB3FXTransFromFXService l_service = null;
        //FX����U�ֈ˗����X�|���X�N���X
        WEB3FXTransFromFXAskingResponse l_response = null;
        try
        {
            l_service =
                (WEB3FXTransFromFXService) Services.getService(
            WEB3FXTransFromFXService.class);
        }
        catch (Exception l_ex)
        {
            //�T�[�r�X�ŗ�O�����������ꍇ�́A
            //�G���[�������X�|���X���b�Z�[�W�ɐݒ肷��
            l_response =
                (WEB3FXTransFromFXAskingResponse) l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "FX����U�փT�[�r�X�̎擾�Ɏ��s���܂����B",
                l_response.errorInfo,
                l_ex);
            return l_response;
        }
        try
        {
            l_response =
                (WEB3FXTransFromFXAskingResponse) l_service.execute(
                    l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response =
                (WEB3FXTransFromFXAskingResponse) l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request, 
                "FX����U�ֈ˗����X�|���X�N���X�̏����Ɏ��s���܂����B", 
                l_ex);
            return l_response;
        }
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (��������) <BR>
     * �U�֒����̓o�^�������s���B <BR>
     * <BR>
     * FX����U�փT�[�r�X���擾���Aexecute()���\�b�h���R�[������B <BR>
     * 
     * @@param l_request - ���N�G�X�g�f�[�^
     * @@return WEB3FXTransFromFXCompleteResponse
     * @@roseuid 41BCF8760145
     */
    public WEB3FXTransFromFXCompleteResponse orderComplete(
        WEB3FXTransFromFXCompleteRequest l_request)
    {
        final String STR_METHOD_NAME =
            "orderComplete(WEB3FXTransFromFXCompleteRequest l_request) ";
        log.entering(STR_METHOD_NAME);
        
        //FX����U�փT�[�r�X
        WEB3FXTransFromFXService l_service = null;
        //FX����U�֊������X�|���X�N���X
        WEB3FXTransFromFXCompleteResponse l_response = null;
        try
        {
            l_service =
                (WEB3FXTransFromFXService) Services.getService(
            WEB3FXTransFromFXService.class);
        }
        catch (Exception l_ex)
        {
            //�T�[�r�X�ŗ�O�����������ꍇ�́A
            //�G���[�������X�|���X���b�Z�[�W�ɐݒ肷��
            l_response =
                (WEB3FXTransFromFXCompleteResponse) l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "FX����U�փT�[�r�X�̎擾�Ɏ��s���܂����B",
                l_response.errorInfo,
                l_ex);
            return l_response;
        }
        try
        {
            l_response =
                (WEB3FXTransFromFXCompleteResponse) l_service.execute(
                    l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response =
                (WEB3FXTransFromFXCompleteResponse) l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request, 
                "FX����U�֊������X�|���X�N���X�̏����Ɏ��s���܂����B", 
                l_ex);
            return l_response;
        }
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (���������iSOAP�ڑ��j)<BR>
     * �U�֒����̊����������s���B<BR>
     * ��SOAP�ڑ��ɂčs���B<BR>
     * <BR>
     * FX����̐U�փT�[�r�X���擾���Aexecute()���\�b�h���R�[������B<BR>
     * <BR>
     * @@param l_request ���N�G�X�g�f�[�^
     * @@return WEB3FXTransFromFXCompleteSoapResponse
     */
    public WEB3FXTransFromFXCompleteSoapResponse orderCompleteSoap(
        WEB3FXTransFromFXCompleteSoapRequest l_request)
    {
        final String STR_METHOD_NAME =
            "orderCompleteSoap(WEB3FXTransFromFXCompleteSoapRequest)";
        log.entering(STR_METHOD_NAME);

        //FX����U�փT�[�r�X
        WEB3FXTransFromFXService l_service = null;
        //FX����U�֊������X�|���X�N���X
        WEB3FXTransFromFXCompleteSoapResponse l_response = null;
        try
        {
            l_service =
                (WEB3FXTransFromFXService)Services.getService(
                    WEB3FXTransFromFXService.class);
        }
        catch (Exception l_ex)
        {
            //�T�[�r�X�ŗ�O�����������ꍇ�́A
            //�G���[�������X�|���X���b�Z�[�W�ɐݒ肷��
            l_response =
                (WEB3FXTransFromFXCompleteSoapResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "FX����U�փT�[�r�X�̎擾�Ɏ��s���܂����B",
                l_response.errorInfo,
                l_ex);

            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

        try
        {
            l_response =
                (WEB3FXTransFromFXCompleteSoapResponse)l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response =
                (WEB3FXTransFromFXCompleteSoapResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request,
                "FX����U�֊������X�|���X�iSOAP�ڑ��j�N���X�̏����Ɏ��s���܂����B",
                l_ex);

            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        catch (WEB3BaseRuntimeException l_ex)
        {
            l_response =
                (WEB3FXTransFromFXCompleteSoapResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request,
                "FX����U�֊������X�|���X�iSOAP�ڑ��j�N���X�̏����Ɏ��s���܂����B",
                l_ex);

            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
}@
