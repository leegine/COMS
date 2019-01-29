head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.23.56;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3FXAccOpenInputHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : FX�����J�ݓ��̓n���h��(WEB3FXAccOpenInputHandler)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/1/26 ���E (���u) �V�K�쐬
*/
package webbroker3.aio.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;
import webbroker3.aio.message.WEB3FXAccOpenInputRequest;
import webbroker3.aio.message.WEB3FXAccOpenInputResponse;
import webbroker3.aio.message.WEB3FXAccOpenTradeAgreementRequest;
import webbroker3.aio.message.WEB3FXAccOpenTradeAgreementResponse;
import webbroker3.aio.service.delegate.WEB3FXAccOpenInputService;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.util.WEB3LogUtility;

/**
 * (FX�����J�ݓ��̓n���h��) <BR>
 * FX�����J�ݓ��̓n���h���N���X <BR>
 * @@author ���E(���u)
 * @@version 1.0 
 */
public class WEB3FXAccOpenInputHandler implements MessageHandler
{
    /**
     * ���O�o�̓��[�e�B���e�B�B
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3FXAccOpenInputHandler.class);
    
    /**
     * @@roseuid 41E789B400DA
     */
    public WEB3FXAccOpenInputHandler()
    {
    }

    /**
     * (������Ӊ�ʕ\��) <BR>
     * FX�����J�ݓ��̓T�[�r�X���擾���Aexecute()���\�b�h���R�[������B <BR>
     * 
     * @@param l_request - ���N�G�X�g�f�[�^
     * @@return WEB3FXAccOpenTradeAgreementResponse
     * @@roseuid 41C785B803CC
     */
    public WEB3FXAccOpenTradeAgreementResponse displayTradeAgreementScreen(
        WEB3FXAccOpenTradeAgreementRequest l_request)
    {
        final String STR_METHOD_NAME =
            "displayTradeAgreementScreen(WEB3FXAccOpenTradeAgreementRequest l_request)";
        log.entering(STR_METHOD_NAME);

        WEB3FXAccOpenInputService l_service = null;
        WEB3FXAccOpenTradeAgreementResponse l_response = null;
        try
        {
            l_service =
                (WEB3FXAccOpenInputService) Services.getService(
                    WEB3FXAccOpenInputService.class);
        }
        catch (Exception l_ex)
        {
            //�T�[�r�X�ŗ�O�����������ꍇ�́A
            //�G���[�������X�|���X���b�Z�[�W�ɐݒ肷��
            l_response =
                (WEB3FXAccOpenTradeAgreementResponse) l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "FX�����J�ݓ��̓T�[�r�X�̎擾�Ɏ��s���܂����B",
                l_response.errorInfo,
                l_ex);
            return l_response;
        }
        try
        {
            l_response =
                (WEB3FXAccOpenTradeAgreementResponse) l_service.execute(
                    l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response =
                (WEB3FXAccOpenTradeAgreementResponse) l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
			l_response.errorMessage = l_ex.getErrorMessage();
            log.error(
                l_request, 
                "FX�����J�ݎ�����Ӄ��N�G�X�g�̏����Ɏ��s���܂����B", 
                l_ex);
            return l_response;
        }
        
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (���͉�ʕ\��) <BR>
     * FX�����J�ݓ��̓T�[�r�X���擾���Aexecute()���\�b�h���R�[������B <BR>
     * 
     * @@param l_request - ���N�G�X�g�f�[�^
     * @@return WEB3FXAccOpenInputResponse
     * @@roseuid 41C786AF0245
     */
    public WEB3FXAccOpenInputResponse displayInputScreen(
        WEB3FXAccOpenInputRequest l_request)
    {
        final String STR_METHOD_NAME =
            "displayInputScreen(WEB3FXAccOpenInputRequest l_request)";
        log.entering(STR_METHOD_NAME);

        WEB3FXAccOpenInputService l_service = null;
        WEB3FXAccOpenInputResponse l_response = null;
        try
        {
            l_service =
                (WEB3FXAccOpenInputService) Services.getService(
                    WEB3FXAccOpenInputService.class);
        }
        catch (Exception l_ex)
        {
            //�T�[�r�X�ŗ�O�����������ꍇ�́A
            //�G���[�������X�|���X���b�Z�[�W�ɐݒ肷��
            l_response =
                (WEB3FXAccOpenInputResponse) l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "FX�����J�ݓ��̓T�[�r�X�̎擾�Ɏ��s���܂����B",
                l_response.errorInfo,
                l_ex);
            return l_response;
        }
        try
        {
            l_response =
                (WEB3FXAccOpenInputResponse) l_service.execute(
                    l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response =
                (WEB3FXAccOpenInputResponse) l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
			l_response.errorMessage = l_ex.getErrorMessage();
            log.error(
                l_request, 
                "FX�����J�ݓ��̓��X�|���X�̏����Ɏ��s���܂����B", 
                l_ex);
            return l_response;
        }
        
        log.exiting(STR_METHOD_NAME);
        return l_response;

    }
}@
