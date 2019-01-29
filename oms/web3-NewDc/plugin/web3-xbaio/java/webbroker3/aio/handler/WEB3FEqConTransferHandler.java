head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.21.00;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3FEqConTransferHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �O�������ւ̐U�փn���h���N���X(WEB3FEqConTransferHandler)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/03/18 ��O�� (���u) �V�K�쐬       
*/

package webbroker3.aio.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.aio.message.WEB3FEqConTransferCompleteRequest;
import webbroker3.aio.message.WEB3FEqConTransferCompleteResponse;
import webbroker3.aio.message.WEB3FEqConTransferConfirmRequest;
import webbroker3.aio.message.WEB3FEqConTransferConfirmResponse;
import webbroker3.aio.message.WEB3FEqConTransferInputRequest;
import webbroker3.aio.message.WEB3FEqConTransferInputResponse;
import webbroker3.aio.service.delegate.WEB3FEqConTransferService;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.util.WEB3LogUtility;

/**
 * (�O�������ւ̐U�փn���h��)<BR>
 * �O�������ւ̐U�փn���h���N���X
 * 
 * @@author ��O��(���u)
 * @@version 1.0
 */
public class WEB3FEqConTransferHandler implements MessageHandler 
{
    /**
     * ���O�o�̓��[�e�B���e�B�B
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3FEqConTransferHandler.class);
    
    /**
     * @@roseuid 423559DA0000
     */
    public WEB3FEqConTransferHandler() 
    {
     
    }
    
    /**
     * (���͉�ʕ\��)<BR>
     * ���͉�ʕ\�����s���B<BR>
     * <BR>
     * �O�������ւ̐U�փT�[�r�X���擾���Aexecute()���\�b�h���R�[������B
     * @@param l_request - ���N�G�X�g�f�[�^
     * 
     * @@return WEB3FEqConTransferInputResponse
     * @@roseuid 41E38F5E029A
     */
    public WEB3FEqConTransferInputResponse displayInputScreen(WEB3FEqConTransferInputRequest l_request) 
    {
        final String STR_METHOD_NAME = 
            "displayInputScreen(WEB3FEqConTransferInputRequest l_request) ";
        log.entering(STR_METHOD_NAME);
        
        //�O�������ւ̐U�փT�[�r�X�C���^�[�t�F�C�X
        WEB3FEqConTransferService l_service = null;          
         
        //�O�������ւ̐U�֓��̓��X�|���X
        WEB3FEqConTransferInputResponse l_response = null;     
         
        try
        {
            l_service = 
                (WEB3FEqConTransferService) Services.getService(
                        WEB3FEqConTransferService.class);
        }
        catch (Exception l_ex)
        {
            //�T�[�r�X�ŗ�O�����������ꍇ�́A
            //�G���[�������X�|���X���b�Z�[�W�ɐݒ肷��
            l_response =
                (WEB3FEqConTransferInputResponse) l_request.createResponse();
            l_response.errorInfo = 
                WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "�O�������ւ̐U�փT�[�r�X�̎擾�Ɏ��s���܂����B",
                l_response.errorInfo,
                l_ex);
            return l_response;
        }
        try
        {
            l_response =
                (WEB3FEqConTransferInputResponse) l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response =
                (WEB3FEqConTransferInputResponse) l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request, 
                "���͉�ʕ\���Ɏ��s���܂����B", 
                l_ex);
            return l_response;
        }
         
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * (�U�֊m�F)<BR>
     * �U�ւ̊m�F�������s���B<BR>
     * <BR>
     * �O�������ւ̐U�փT�[�r�X���擾���Aexecute()���\�b�h���R�[������B
     * @@param l_request - ���N�G�X�g�f�[�^
     * 
     * @@return WEB3FEqConTransferConfirmResponse
     * @@roseuid 41E38F5E02AA
     */
    public WEB3FEqConTransferConfirmResponse transferConfirm(WEB3FEqConTransferConfirmRequest l_request) 
    {
        final String STR_METHOD_NAME = 
            "transferConfirm(WEB3FEqConTransferConfirmRequest l_request) ";
        log.entering(STR_METHOD_NAME);
        
        //�O�������ւ̐U�փT�[�r�X�C���^�[�t�F�C�X
        WEB3FEqConTransferService l_service = null;          
         
        //�O�������ւ̐U�֊m�F���X�|���X
        WEB3FEqConTransferConfirmResponse l_response = null;     
         
        try
        {
            l_service = 
                (WEB3FEqConTransferService) Services.getService(
                        WEB3FEqConTransferService.class);
        }
        catch (Exception l_ex)
        {
            //�T�[�r�X�ŗ�O�����������ꍇ�́A
            //�G���[�������X�|���X���b�Z�[�W�ɐݒ肷��
            l_response =
                (WEB3FEqConTransferConfirmResponse) l_request.createResponse();
            l_response.errorInfo = 
                WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "�O�������ւ̐U�փT�[�r�X�̎擾�Ɏ��s���܂����B",
                l_response.errorInfo,
                l_ex);
            return l_response;
        }
        try
        {
            l_response =
                (WEB3FEqConTransferConfirmResponse) l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response =
                (WEB3FEqConTransferConfirmResponse) l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request, 
                "�U�ւ̊m�F�Ɏ��s���܂����B", 
                l_ex);
            return l_response;
        }
         
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * (�U�֊���)<BR>
     * �U�ւ̊����������s���B<BR>
     * <BR>
     * �O�������ւ̐U�փT�[�r�X���擾���Aexecute()���\�b�h���R�[������B
     * @@param l_request - ���N�G�X�g�f�[�^
     * 
     * @@return WEB3FEqConTransferCompleteResponse
     * @@roseuid 41E38F5E02C9
     */
    public WEB3FEqConTransferCompleteResponse transferComplete(WEB3FEqConTransferCompleteRequest l_request) 
    {
        final String STR_METHOD_NAME = 
            "transferComplete(WEB3FEqConTransferCompleteRequest l_request)";
        log.entering(STR_METHOD_NAME);
        
        //�O�������ւ̐U�փT�[�r�X�C���^�[�t�F�C�X
        WEB3FEqConTransferService l_service = null;          
         
        //�O�������ւ̐U�֊������X�|���X
        WEB3FEqConTransferCompleteResponse l_response = null;     
         
        try
        {
            l_service = 
                (WEB3FEqConTransferService) Services.getService(
                        WEB3FEqConTransferService.class);
        }
        catch (Exception l_ex)
        {
            //�T�[�r�X�ŗ�O�����������ꍇ�́A
            //�G���[�������X�|���X���b�Z�[�W�ɐݒ肷��
            l_response =
                (WEB3FEqConTransferCompleteResponse) l_request.createResponse();
            l_response.errorInfo = 
                WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "�O�������ւ̐U�փT�[�r�X�̎擾�Ɏ��s���܂����B",
                l_response.errorInfo,
                l_ex);
            return l_response;
        }
        try
        {
            l_response =
                (WEB3FEqConTransferCompleteResponse) l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response =
                (WEB3FEqConTransferCompleteResponse) l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request, 
                "�U�ւ̊����Ɏ��s���܂����B", 
                l_ex);
            return l_response;
        }
         
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
}
@
