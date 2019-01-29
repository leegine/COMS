head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.24.18;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3FXTransToFXInputHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : FX�ւ̐U�֓��̓n���h��(WEB3FXTransToFXInputHandler)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/1/20 ���z (���u) �V�K�쐬   
*/

package webbroker3.aio.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.aio.message.WEB3FXTransToFXInputRequest;
import webbroker3.aio.message.WEB3FXTransToFXInputResponse;
import webbroker3.aio.service.delegate.WEB3FXTransToFXInputService;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.util.WEB3LogUtility;

/**
 * (FX�ւ̐U�֓��̓n���h��) <BR>
 * FX�ւ̐U�֓��̓n���h���N���X <BR>
 * 
 * @@author ���z(���u)
 * @@version 1.0
 */
public class WEB3FXTransToFXInputHandler implements MessageHandler
{
    /**
    * ���O�o�̓��[�e�B���e�B�B
    */    
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3FXTransToFXInputHandler.class);
    
    /**
     * @@roseuid 41E780B100BB
     */
    public WEB3FXTransToFXInputHandler()
    {
    }

    /**
     * (���͉�ʕ\��) <BR>
     * FX�ւ̐U�֓��̓T�[�r�X���擾���Aexecute()���\�b�h���R�[������B <BR>
     * 
     * @@param l_request - ���N�G�X�g�f�[�^
     * @@return WEB3FXTransToFXInputResponse
     * @@roseuid 41BCF37E0279
     */
    public WEB3FXTransToFXInputResponse displayInputScreen(
        WEB3FXTransToFXInputRequest l_request)
    {
        String l_strMethodName = "displayInputScreen(WEB3FXTransToFXInputRequest l_request)";
        log.entering(l_strMethodName);
        
        WEB3FXTransToFXInputService l_service;
        WEB3FXTransToFXInputResponse l_response;
        
        try
        {
            //FX�ւ̐U�֓��̓T�[�r�X���擾��
            l_service = 
                (WEB3FXTransToFXInputService)Services.getService(
                    WEB3FXTransToFXInputService.class);
        }
        //�T�[�r�X�ŗ�O�����������ꍇ�́A�G���[�������X�|���X���b�Z�[�W�ɐݒ肷��
        catch (Exception l_ex)
        {
            l_response = 
                (WEB3FXTransToFXInputResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request, 
                "__FX�ւ̐U�֓��̓T�[�r�X�̎擾�Ɏ��s���܂���__",
                l_response.errorInfo,
                l_ex);
            return l_response;
        }
        
        try
        {
            //execute()���\�b�h���R�[������
            l_response = (WEB3FXTransToFXInputResponse)l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response =
                (WEB3FXTransToFXInputResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request,
                "__error in call FX�ւ̐U�֓��̓T�[�r�X.execute__",
                l_ex);
            return l_response;
        }
        
        log.exiting(l_strMethodName);
        
        return l_response;
    }
}@
