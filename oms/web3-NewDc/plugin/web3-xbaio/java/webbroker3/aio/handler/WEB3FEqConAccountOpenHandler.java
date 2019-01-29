head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.22.29;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3FEqConAccountOpenHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �O�������J�݃n���h��(WEB3FEqConAccountOpenHandler)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/3/21 ���z (���u) �V�K�쐬   
*/

package webbroker3.aio.handler;

import webbroker3.aio.message.WEB3FEqConAccountOpenAgreementRequest;
import webbroker3.aio.message.WEB3FEqConAccountOpenAgreementResponse;
import webbroker3.aio.message.WEB3FEqConAccountOpenCompleteRequest;
import webbroker3.aio.message.WEB3FEqConAccountOpenCompleteResponse;
import webbroker3.aio.message.WEB3FEqConAccountOpenConfirmRequest;
import webbroker3.aio.message.WEB3FEqConAccountOpenConfirmResponse;
import webbroker3.aio.message.WEB3FEqConAccountOpenInputRequest;
import webbroker3.aio.message.WEB3FEqConAccountOpenInputResponse;
import webbroker3.aio.service.delegate.WEB3FEqConAccountOpenService;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.util.WEB3LogUtility;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

/**
 * (�O�������J�݃n���h��)<BR>
 * �O�������J�݃n���h���N���X
 * 
 * @@author ���z(���u)
 * @@version 1.0
 */
public class WEB3FEqConAccountOpenHandler implements MessageHandler 
{
    /**
     * ���O�o�̓��[�e�B���e�B�B
     */    
     private static WEB3LogUtility log =
         WEB3LogUtility.getInstance(WEB3FEqConAccountOpenHandler.class);
    
    /**
     * @@roseuid 423562E40242
     */
    public WEB3FEqConAccountOpenHandler() 
    {
     
    }
    
    /**
     * (������Ӊ�ʕ\��)<BR>
     * ������Ӊ�ʕ\�����s���B<BR>
     * <BR>
     * �O�������J�݃T�[�r�X���擾���Aexecute()���\�b�h���R�[������B
     * @@param l_request - ���N�G�X�g�f�[�^
     * 
     * @@return WEB3FEqConAccountOpenAgreementResponse
     * @@roseuid 41E386A40162
     */
    public WEB3FEqConAccountOpenAgreementResponse displayAgreementScreen(
                WEB3FEqConAccountOpenAgreementRequest l_request) 
    {
        String l_strMethodName = 
            "displayAgreementScreen(WEB3FEqConAccountOpenAgreementRequest l_request)";
        log.entering(l_strMethodName);
        
        WEB3FEqConAccountOpenService l_service;
        WEB3FEqConAccountOpenAgreementResponse l_response;
        
        try
        {
            //�O�������J�݃T�[�r�X���擾��
            l_service = 
                (WEB3FEqConAccountOpenService)Services.getService(
                    WEB3FEqConAccountOpenService.class);
        }
        //�T�[�r�X�ŗ�O�����������ꍇ�́A�G���[�������X�|���X���b�Z�[�W�ɐݒ肷��
        catch (Exception l_ex)
        {
            l_response = 
                (WEB3FEqConAccountOpenAgreementResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request, 
                "__�O�������J�݃T�[�r�X�̎擾�Ɏ��s���܂���__",
                l_response.errorInfo,
                l_ex);
            return l_response;
        }
        
        try
        {
            //execute()���\�b�h���R�[������
            l_response = 
                (WEB3FEqConAccountOpenAgreementResponse)l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response =
                (WEB3FEqConAccountOpenAgreementResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request,
                "__error in call �O�������J�݃T�[�r�X.execute__",
                l_ex);
            return l_response;
        }
        
        log.exiting(l_strMethodName);
        
        return l_response;
    }
    
    /**
     * (���͉�ʕ\��)<BR>
     * ���͉�ʕ\�����s���B<BR>
     * <BR>
     * �O�������J�݃T�[�r�X���擾���Aexecute()���\�b�h���R�[������B
     * @@param l_request - ���N�G�X�g�f�[�^
     * 
     * @@return WEB3FEqConAccountOpenInputResponse
     * @@roseuid 421ADF2B028A
     */
    public WEB3FEqConAccountOpenInputResponse displayInputScreen(
                WEB3FEqConAccountOpenInputRequest l_request) 
    {
        String l_strMethodName = 
            "displayInputScreen(WEB3FEqConAccountOpenInputRequest l_request)";
        log.entering(l_strMethodName);
        
        WEB3FEqConAccountOpenService l_service;
        WEB3FEqConAccountOpenInputResponse l_response;
        
        try
        {
            //�O�������J�݃T�[�r�X���擾��
            l_service = 
                (WEB3FEqConAccountOpenService)Services.getService(
                    WEB3FEqConAccountOpenService.class);
        }
        //�T�[�r�X�ŗ�O�����������ꍇ�́A�G���[�������X�|���X���b�Z�[�W�ɐݒ肷��
        catch (Exception l_ex)
        {
            l_response = 
                (WEB3FEqConAccountOpenInputResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request, 
                "__�O�������J�݃T�[�r�X�̎擾�Ɏ��s���܂���__",
                l_response.errorInfo,
                l_ex);
            return l_response;
        }
        
        try
        {
            //execute()���\�b�h���R�[������
            l_response = 
                (WEB3FEqConAccountOpenInputResponse)l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response =
                (WEB3FEqConAccountOpenInputResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request,
                "__error in call �O�������J�݃T�[�r�X.execute__",
                l_ex);
            return l_response;
        }
        
        log.exiting(l_strMethodName);
        
        return l_response;
    }
    
    /**
     * (�\���m�F)<BR>
     * �\���̊m�F�������s���B<BR>
     * <BR>
     * �O�������J�݃T�[�r�X���擾���Aexecute()���\�b�h���R�[������B
     * @@param l_request - ���N�G�X�g�f�[�^
     * 
     * @@return WEB3FEqConAccountOpenConfirmRespons
     * @@roseuid 41E386B1000A
     */
    public WEB3FEqConAccountOpenConfirmResponse applyConfirm(
                WEB3FEqConAccountOpenConfirmRequest l_request) 
    {
        String l_strMethodName = 
            "applyConfirm(WEB3FEqConAccountOpenConfirmRequest l_request)";
        log.entering(l_strMethodName);
        
        WEB3FEqConAccountOpenService l_service;
        WEB3FEqConAccountOpenConfirmResponse l_response;
        
        try
        {
            //�O�������J�݃T�[�r�X���擾��
            l_service = 
                (WEB3FEqConAccountOpenService)Services.getService(
                    WEB3FEqConAccountOpenService.class);
        }
        //�T�[�r�X�ŗ�O�����������ꍇ�́A�G���[�������X�|���X���b�Z�[�W�ɐݒ肷��
        catch (Exception l_ex)
        {
            l_response = 
                (WEB3FEqConAccountOpenConfirmResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request, 
                "__�O�������J�݃T�[�r�X�̎擾�Ɏ��s���܂���__",
                l_response.errorInfo,
                l_ex);
            return l_response;
        }
        
        try
        {
            //execute()���\�b�h���R�[������
            l_response = 
                (WEB3FEqConAccountOpenConfirmResponse)l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response =
                (WEB3FEqConAccountOpenConfirmResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request,
                "__error in call �O�������J�݃T�[�r�X.execute__",
                l_ex);
            return l_response;
        }
        
        log.exiting(l_strMethodName);
        
        return l_response;
    }
    
    /**
     * (�\������)<BR>
     * �\���̊����������s���B<BR>
     * <BR>
     * �O�������J�݃T�[�r�X���擾���Aexecute()���\�b�h���R�[������B
     * @@param l_request - ���N�G�X�g�f�[�^
     * 
     * @@return WEB3FEqConAccountOpenCompleteResponse
     * @@roseuid 41E386B90337
     */
    public WEB3FEqConAccountOpenCompleteResponse applyComplete(
                WEB3FEqConAccountOpenCompleteRequest l_request) 
    {
        String l_strMethodName = 
            "applyComplete(WEB3FEqConAccountOpenCompleteRequest l_request)";
        log.entering(l_strMethodName);
        
        WEB3FEqConAccountOpenService l_service;
        WEB3FEqConAccountOpenCompleteResponse l_response;
        
        try
        {
            //�O�������J�݃T�[�r�X���擾��
            l_service = 
                (WEB3FEqConAccountOpenService)Services.getService(
                    WEB3FEqConAccountOpenService.class);
        }
        //�T�[�r�X�ŗ�O�����������ꍇ�́A�G���[�������X�|���X���b�Z�[�W�ɐݒ肷��
        catch (Exception l_ex)
        {
            l_response = 
                (WEB3FEqConAccountOpenCompleteResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request, 
                "__�O�������J�݃T�[�r�X�̎擾�Ɏ��s���܂���__",
                l_response.errorInfo,
                l_ex);
            return l_response;
        }
        
        try
        {
            //execute()���\�b�h���R�[������
            l_response = 
                (WEB3FEqConAccountOpenCompleteResponse)l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response =
                (WEB3FEqConAccountOpenCompleteResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request,
                "__error in call �O�������J�݃T�[�r�X.execute__",
                l_ex);
            return l_response;
        }
        
        log.exiting(l_strMethodName);
        
        return l_response;
    }
}@
