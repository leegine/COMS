head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.22.38;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3FEqConTransferCancelHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �O�������ւ̐U�֎���n���h���N���X(WEB3FEqConTransferCancelHandler)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/03/18 ��O�� (���u) �V�K�쐬       
*/

package webbroker3.aio.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.aio.message.WEB3FEqConTransferCancelCompleteRequest;
import webbroker3.aio.message.WEB3FEqConTransferCancelCompleteResponse;
import webbroker3.aio.message.WEB3FEqConTransferCancelConfirmRequest;
import webbroker3.aio.message.WEB3FEqConTransferCancelConfirmResponse;
import webbroker3.aio.message.WEB3FEqConTransferCancelSelectRequest;
import webbroker3.aio.message.WEB3FEqConTransferCancelSelectResponse;
import webbroker3.aio.service.delegate.WEB3FEqConTransferCancelService;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.util.WEB3LogUtility;

/**
 * (�O�������ւ̐U�֎���n���h��)<BR>
 * �O�������ւ̐U�֎���n���h���N���X
 * 
 * @@author ��O��(���u)
 * @@version 1.0
 */
public class WEB3FEqConTransferCancelHandler implements MessageHandler 
{
    /**
     * ���O�o�̓��[�e�B���e�B�B
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3FEqConTransferCancelHandler.class);
    
    /**
     * @@roseuid 42355E700138
     */
    public WEB3FEqConTransferCancelHandler() 
    {
     
    }
    
    /**
     * (�I����ʕ\��)<BR>
     * �I����ʕ\�����s���B<BR>
     * <BR>
     * �O�������ւ̐U�֎���T�[�r�X���擾���Aexecute()���\�b�h���R�[������B
     * @@param l_request - ���N�G�X�g�f�[�^
     * 
     * @@return WEB3FEqConTransferCancelSelectResponse
     * @@roseuid 41E394CF000A
     */
    public WEB3FEqConTransferCancelSelectResponse displaySelectScreen(WEB3FEqConTransferCancelSelectRequest l_request) 
    {
        final String STR_METHOD_NAME = 
            "displaySelectScreen(WEB3FEqConTransferCancelSelectRequest l_request)";
        log.entering(STR_METHOD_NAME);
        
        //�O�������ւ̐U�֎���T�[�r�X�C���^�[�t�F�C�X
        WEB3FEqConTransferCancelService l_service = null;          
         
        //�O�������ւ̐U�֎���I�����X�|���X
        WEB3FEqConTransferCancelSelectResponse l_response = null;     
         
        try
        {
            l_service = 
                (WEB3FEqConTransferCancelService) Services.getService(
                        WEB3FEqConTransferCancelService.class);
        }
        catch (Exception l_ex)
        {
            //�T�[�r�X�ŗ�O�����������ꍇ�́A
            //�G���[�������X�|���X���b�Z�[�W�ɐݒ肷��
            l_response =
                (WEB3FEqConTransferCancelSelectResponse) l_request.createResponse();
            l_response.errorInfo = 
                WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "�O�������ւ̐U�֎���T�[�r�X�̎擾�Ɏ��s���܂����B",
                l_response.errorInfo,
                l_ex);
            return l_response;
        }
        try
        {
            l_response =
                (WEB3FEqConTransferCancelSelectResponse) l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response =
                (WEB3FEqConTransferCancelSelectResponse) l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request, 
                "�I����ʕ\���Ɏ��s���܂����B", 
                l_ex);
            return l_response;
        }
         
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * (����m�F)<BR>
     * �U�ւ̎���m�F�������s���B<BR>
     * <BR>
     * �O�������ւ̐U�֎���T�[�r�X���擾���Aexecute()���\�b�h���R�[������B
     * @@param l_request - ���N�G�X�g�f�[�^
     * 
     * @@return WEB3FEqConTransferCancelConfirmResponse
     * @@roseuid 41E394CF0029
     */
    public WEB3FEqConTransferCancelConfirmResponse cancelConfirm(WEB3FEqConTransferCancelConfirmRequest l_request) 
    {
        final String STR_METHOD_NAME = 
            "cancelConfirm(WEB3FEqConTransferCancelConfirmRequest l_request)";
        log.entering(STR_METHOD_NAME);
        
        //�O�������ւ̐U�֎���T�[�r�X�C���^�[�t�F�C�X
        WEB3FEqConTransferCancelService l_service = null;          
         
        //�O�������ւ̐U�֎���m�F���X�|���X
        WEB3FEqConTransferCancelConfirmResponse l_response = null;     
         
        try
        {
            l_service = 
                (WEB3FEqConTransferCancelService) Services.getService(
                        WEB3FEqConTransferCancelService.class);
        }
        catch (Exception l_ex)
        {
            //�T�[�r�X�ŗ�O�����������ꍇ�́A
            //�G���[�������X�|���X���b�Z�[�W�ɐݒ肷��
            l_response =
                (WEB3FEqConTransferCancelConfirmResponse) l_request.createResponse();
            l_response.errorInfo = 
                WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "�O�������ւ̐U�֎���T�[�r�X�̎擾�Ɏ��s���܂����B",
                l_response.errorInfo,
                l_ex);
            return l_response;
        }
        try
        {
            l_response =
                (WEB3FEqConTransferCancelConfirmResponse) l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response =
                (WEB3FEqConTransferCancelConfirmResponse) l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request, 
                "�U�ւ̎���m�F�Ɏ��s���܂����B", 
                l_ex);
            return l_response;
        }
         
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * (�������)<BR>
     * �U�ւ̎�������������s���B<BR>
     * <BR>
     * �O�������ւ̐U�֎���T�[�r�X���擾���Aexecute()���\�b�h���R�[������B
     * @@param l_request - ���N�G�X�g�f�[�^
     * 
     * @@return WEB3FEqConTransferCancelCompleteResponse
     * @@roseuid 41E394CF0049
     */
    public WEB3FEqConTransferCancelCompleteResponse cancelCompelte(WEB3FEqConTransferCancelCompleteRequest l_request) 
    {
        final String STR_METHOD_NAME = 
            "cancelCompelte(WEB3FEqConTransferCancelCompleteRequest l_request)";
        log.entering(STR_METHOD_NAME);
        
        //�O�������ւ̐U�֎���T�[�r�X�C���^�[�t�F�C�X
        WEB3FEqConTransferCancelService l_service = null;          
         
        //�O�������ւ̐U�֎���������X�|���X
        WEB3FEqConTransferCancelCompleteResponse l_response = null;     
         
        try
        {
            l_service = 
                (WEB3FEqConTransferCancelService) Services.getService(
                        WEB3FEqConTransferCancelService.class);
        }
        catch (Exception l_ex)
        {
            //�T�[�r�X�ŗ�O�����������ꍇ�́A
            //�G���[�������X�|���X���b�Z�[�W�ɐݒ肷��
            l_response =
                (WEB3FEqConTransferCancelCompleteResponse) l_request.createResponse();
            l_response.errorInfo = 
                WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "�O�������ւ̐U�֎���T�[�r�X�̎擾�Ɏ��s���܂����B",
                l_response.errorInfo,
                l_ex);
            return l_response;
        }
        try
        {
            l_response =
                (WEB3FEqConTransferCancelCompleteResponse) l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response =
                (WEB3FEqConTransferCancelCompleteResponse) l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request, 
                "�U�ւ̎�������Ɏ��s���܂����B", 
                l_ex);
            return l_response;
        }
         
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
}
@
