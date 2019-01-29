head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.25.44;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3AdminFEqConAccountOpenMngHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �O�������J�݊Ǘ��n���h��(WEB3AdminFEqConAccountOpenMngHandler)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/3/21 ���z (���u) �V�K�쐬   
*/

package webbroker3.aio.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.aio.message.WEB3AdminFEqConAccountOpenMngListDownloadRequest;
import webbroker3.aio.message.WEB3AdminFEqConAccountOpenMngListDownloadResponse;
import webbroker3.aio.message.WEB3AdminFEqConAccountOpenMngListRequest;
import webbroker3.aio.message.WEB3AdminFEqConAccountOpenMngListResponse;
import webbroker3.aio.message.WEB3AdminFEqConAccountOpenMngSrcInputRequest;
import webbroker3.aio.message.WEB3AdminFEqConAccountOpenMngSrcInputResponse;
import webbroker3.aio.message.WEB3AdminFEqConAccountOpenMngStatusUpdCompleteRequest;
import webbroker3.aio.message.WEB3AdminFEqConAccountOpenMngStatusUpdCompleteResponse;
import webbroker3.aio.message.WEB3AdminFEqConAccountOpenMngStatusUpdConfirmRequest;
import webbroker3.aio.message.WEB3AdminFEqConAccountOpenMngStatusUpdConfirmResponse;
import webbroker3.aio.message.WEB3AdminFEqConAccountOpenMngStatusUpdInputRequest;
import webbroker3.aio.message.WEB3AdminFEqConAccountOpenMngStatusUpdInputResponse;
import webbroker3.aio.service.delegate.WEB3AdminFEqConAccountOpenMngService;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.util.WEB3LogUtility;

/**
 * (�O�������J�݊Ǘ��n���h��)<BR>
 * �O�������J�݊Ǘ��n���h���N���X
 * 
 * @@author ���z(���u)
 * @@version 1.0
 */
public class WEB3AdminFEqConAccountOpenMngHandler implements MessageHandler 
{
    /**
     * ���O�o�̓��[�e�B���e�B�B
     */    
     private static WEB3LogUtility log =
         WEB3LogUtility.getInstance(WEB3AdminFEqConAccountOpenMngHandler.class);
    
    /**
     * @@roseuid 423563E902DE
     */
    public WEB3AdminFEqConAccountOpenMngHandler() 
    {
     
    }
    
    /**
     * (���͉�ʕ\��)<BR>
     * ���͉�ʕ\�����s���B<BR>
     * <BR>
     * �O�������J�݊Ǘ��T�[�r�X���擾���Aexecute()���\�b�h���R�[������B
     * @@param l_request - ���N�G�X�g�f�[�^
     * 
     * @@return WEB3AdminFEqConAccountOpenMngSrcInputResponse
     * @@roseuid 41E3B25D0097
     */
    public WEB3AdminFEqConAccountOpenMngSrcInputResponse displayInputScreen(
                WEB3AdminFEqConAccountOpenMngSrcInputRequest l_request) 
    {
        String l_strMethodName = 
            "displayInputScreen(WEB3AdminFEqConAccountOpenMngSrcInputRequest l_request)";
        log.entering(l_strMethodName);
        
        WEB3AdminFEqConAccountOpenMngService l_service;
        WEB3AdminFEqConAccountOpenMngSrcInputResponse l_response;
        
        try
        {
            //�O�������J�݊Ǘ��T�[�r�X���擾��
            l_service = 
                (WEB3AdminFEqConAccountOpenMngService)Services.getService(
                    WEB3AdminFEqConAccountOpenMngService.class);
        }
        //�T�[�r�X�ŗ�O�����������ꍇ�́A�G���[�������X�|���X���b�Z�[�W�ɐݒ肷��
        catch (Exception l_ex)
        {
            l_response = 
                (WEB3AdminFEqConAccountOpenMngSrcInputResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request, 
                "__�O�������J�݊Ǘ��T�[�r�X�̎擾�Ɏ��s���܂���__",
                l_response.errorInfo,
                l_ex);
            return l_response;
        }
        
        try
        {
            //execute()���\�b�h���R�[������
            l_response = 
                (WEB3AdminFEqConAccountOpenMngSrcInputResponse)l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response =
                (WEB3AdminFEqConAccountOpenMngSrcInputResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request,
                "__error in call �O�������J�݊Ǘ��T�[�r�X.execute__",
                l_ex);
            return l_response;
        }
        
        log.exiting(l_strMethodName);
        
        return l_response;
    }
    
    /**
     * (�ꗗ��ʕ\��)<BR>
     * �ꗗ��ʕ\�����s���B<BR>
     * <BR>
     * �O�������J�݊Ǘ��T�[�r�X���擾���Aexecute()���\�b�h���R�[������B
     * @@param l_request - ���N�G�X�g�f�[�^
     * 
     * @@return WEB3AdminFEqConAccountOpenMngListResponse
     * @@roseuid 41E3B25D0087
     */
    public WEB3AdminFEqConAccountOpenMngListResponse displayListScreen(
                WEB3AdminFEqConAccountOpenMngListRequest l_request) 
    {
        String l_strMethodName = 
            "displayListScreen(WEB3AdminFEqConAccountOpenMngListRequest l_request)";
        log.entering(l_strMethodName);
        
        WEB3AdminFEqConAccountOpenMngService l_service;
        WEB3AdminFEqConAccountOpenMngListResponse l_response;
        
        try
        {
            //�O�������J�݊Ǘ��T�[�r�X���擾��
            l_service = 
                (WEB3AdminFEqConAccountOpenMngService)Services.getService(
                    WEB3AdminFEqConAccountOpenMngService.class);
        }
        //�T�[�r�X�ŗ�O�����������ꍇ�́A�G���[�������X�|���X���b�Z�[�W�ɐݒ肷��
        catch (Exception l_ex)
        {
            l_response = 
                (WEB3AdminFEqConAccountOpenMngListResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request, 
                "__�O�������J�݊Ǘ��T�[�r�X�̎擾�Ɏ��s���܂���__",
                l_response.errorInfo,
                l_ex);
            return l_response;
        }
        
        try
        {
            //execute()���\�b�h���R�[������
            l_response = 
                (WEB3AdminFEqConAccountOpenMngListResponse)l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response =
                (WEB3AdminFEqConAccountOpenMngListResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request,
                "__error in call �O�������J�݊Ǘ��T�[�r�X.execute__",
                l_ex);
            return l_response;
        }
        
        log.exiting(l_strMethodName);
        
        return l_response;
    }
    
    /**
     * (�X�e�[�^�X�X�V��ʕ\��)<BR>
     * �X�e�[�^�X�X�V��ʕ\�����s���B<BR>
     * <BR>
     * �O�������J�݊Ǘ��T�[�r�X���擾���Aexecute()���\�b�h���R�[������B
     * @@param l_request - ���N�G�X�g�f�[�^
     * 
     * @@return WEB3AdminFEqConAccountOpenMngStatusUpdInputResponse
     * @@roseuid 41E3B2C40172
     */
    public WEB3AdminFEqConAccountOpenMngStatusUpdInputResponse displayStatusUpdScreen(
                WEB3AdminFEqConAccountOpenMngStatusUpdInputRequest l_request) 
    {
        String l_strMethodName = 
            "displayStatusUpdScreen(WEB3AdminFEqConAccountOpenMngStatusUpdInputRequest l_request)";
        log.entering(l_strMethodName);
        
        WEB3AdminFEqConAccountOpenMngService l_service;
        WEB3AdminFEqConAccountOpenMngStatusUpdInputResponse l_response;
        
        try
        {
            //�O�������J�݊Ǘ��T�[�r�X���擾��
            l_service = 
                (WEB3AdminFEqConAccountOpenMngService)Services.getService(
                    WEB3AdminFEqConAccountOpenMngService.class);
        }
        //�T�[�r�X�ŗ�O�����������ꍇ�́A�G���[�������X�|���X���b�Z�[�W�ɐݒ肷��
        catch (Exception l_ex)
        {
            l_response = 
                (WEB3AdminFEqConAccountOpenMngStatusUpdInputResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request, 
                "__�O�������J�݊Ǘ��T�[�r�X�̎擾�Ɏ��s���܂���__",
                l_response.errorInfo,
                l_ex);
            return l_response;
        }
        
        try
        {
            //execute()���\�b�h���R�[������
            l_response = 
                (WEB3AdminFEqConAccountOpenMngStatusUpdInputResponse)l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response =
                (WEB3AdminFEqConAccountOpenMngStatusUpdInputResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request,
                "__error in call �O�������J�݊Ǘ��T�[�r�X.execute__",
                l_ex);
            return l_response;
        }
        
        log.exiting(l_strMethodName);
        
        return l_response;
    }
    
    /**
     * (�X�e�[�^�X�X�V�m�F)<BR>
     * �X�e�[�^�X�X�V�̊m�F�������s���B<BR>
     * <BR>
     * �O�������J�݊Ǘ��T�[�r�X���擾���Aexecute()���\�b�h���R�[������B
     * @@param l_request - ���N�G�X�g�f�[�^
     * 
     * @@return WEB3AdminFEqConAccountOpenMngStatusUpdConfirmResponse
     * @@roseuid 41E3B25D00B6
     */
    public WEB3AdminFEqConAccountOpenMngStatusUpdConfirmResponse statusUpdConfirm(
                WEB3AdminFEqConAccountOpenMngStatusUpdConfirmRequest l_request) 
    {
        String l_strMethodName = 
            "statusUpdConfirm(WEB3AdminFEqConAccountOpenMngStatusUpdConfirmRequest l_request)";
        log.entering(l_strMethodName);
        
        WEB3AdminFEqConAccountOpenMngService l_service;
        WEB3AdminFEqConAccountOpenMngStatusUpdConfirmResponse l_response;
        
        try
        {
            //�O�������J�݊Ǘ��T�[�r�X���擾��
            l_service = 
                (WEB3AdminFEqConAccountOpenMngService)Services.getService(
                    WEB3AdminFEqConAccountOpenMngService.class);
        }
        //�T�[�r�X�ŗ�O�����������ꍇ�́A�G���[�������X�|���X���b�Z�[�W�ɐݒ肷��
        catch (Exception l_ex)
        {
            l_response = 
                (WEB3AdminFEqConAccountOpenMngStatusUpdConfirmResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request, 
                "__�O�������J�݊Ǘ��T�[�r�X�̎擾�Ɏ��s���܂���__",
                l_response.errorInfo,
                l_ex);
            return l_response;
        }
        
        try
        {
            //execute()���\�b�h���R�[������
            l_response = 
                (WEB3AdminFEqConAccountOpenMngStatusUpdConfirmResponse)l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response =
                (WEB3AdminFEqConAccountOpenMngStatusUpdConfirmResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request,
                "__error in call �O�������J�݊Ǘ��T�[�r�X.execute__",
                l_ex);
            return l_response;
        }
        
        log.exiting(l_strMethodName);
        
        return l_response;
    }
    
    /**
     * (�X�e�[�^�X�X�V����)<BR>
     * �X�e�[�^�X�X�V�̊����������s���B<BR>
     * <BR>
     * �O�������J�݊Ǘ��T�[�r�X���擾���Aexecute()���\�b�h���R�[������B
     * @@param l_request - ���N�G�X�g�f�[�^
     * 
     * @@return WEB3AdminFEqConAccountOpenMngStatusUpdCompleteResponse
     * @@roseuid 41E3B25D00C6
     */
    public WEB3AdminFEqConAccountOpenMngStatusUpdCompleteResponse statusUpdComplete(
                WEB3AdminFEqConAccountOpenMngStatusUpdCompleteRequest l_request) 
    {
        String l_strMethodName = 
            "statusUpdComplete(WEB3AdminFEqConAccountMngStateChangeCommonRequest l_request)";
        log.entering(l_strMethodName);
        
        WEB3AdminFEqConAccountOpenMngService l_service;
        WEB3AdminFEqConAccountOpenMngStatusUpdCompleteResponse l_response;
        
        try
        {
            //�O�������J�݊Ǘ��T�[�r�X���擾��
            l_service = 
                (WEB3AdminFEqConAccountOpenMngService)Services.getService(
                    WEB3AdminFEqConAccountOpenMngService.class);
        }
        //�T�[�r�X�ŗ�O�����������ꍇ�́A�G���[�������X�|���X���b�Z�[�W�ɐݒ肷��
        catch (Exception l_ex)
        {
            l_response = 
                (WEB3AdminFEqConAccountOpenMngStatusUpdCompleteResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request, 
                "__�O�������J�݊Ǘ��T�[�r�X�̎擾�Ɏ��s���܂���__",
                l_response.errorInfo,
                l_ex);
            return l_response;
        }
        
        try
        {
            //execute()���\�b�h���R�[������
            l_response = 
                (WEB3AdminFEqConAccountOpenMngStatusUpdCompleteResponse)l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response =
                (WEB3AdminFEqConAccountOpenMngStatusUpdCompleteResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request,
                "__error in call �O�������J�݊Ǘ��T�[�r�X.execute__",
                l_ex);
            return l_response;
        }
        
        log.exiting(l_strMethodName);
        
        return l_response;
    }
    
    /**
     * (�_�E�����[�h�t�@@�C���̎擾)<BR>
     * �_�E�����[�h�t�@@�C���擾�������s���B <BR>
     * <BR>
     * �O�������J�݊Ǘ��T�[�r�X���擾���Aexecute()���\�b�h���R�[������B
     * @@param l_request - ���N�G�X�g�f�[�^
     * 
     * @@return WEB3AdminFEqConAccountOpenMngListDownloadResponse
     * @@roseuid 41E3B25D00C6
     */
    public WEB3AdminFEqConAccountOpenMngListDownloadResponse getDownloadFile(
            WEB3AdminFEqConAccountOpenMngListDownloadRequest l_request) 
    {
        String l_strMethodName = 
            "getDownloadFile(WEB3AdminFEqConAccountOpenMngListDownloadRequest l_request)";
        log.entering(l_strMethodName);
        
        WEB3AdminFEqConAccountOpenMngService l_service;
        WEB3AdminFEqConAccountOpenMngListDownloadResponse l_response;
        
        try
        {
            //�O�������J�݊Ǘ��T�[�r�X���擾��
            l_service = 
                (WEB3AdminFEqConAccountOpenMngService)Services.getService(
                    WEB3AdminFEqConAccountOpenMngService.class);
        }
        //�T�[�r�X�ŗ�O�����������ꍇ�́A�G���[�������X�|���X���b�Z�[�W�ɐݒ肷��
        catch (Exception l_ex)
        {
            l_response = 
                (WEB3AdminFEqConAccountOpenMngListDownloadResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request, 
                "__�O�������J�݊Ǘ��T�[�r�X�̎擾�Ɏ��s���܂���__",
                l_response.errorInfo,
                l_ex);
            return l_response;
        }
        
        try
        {
            //execute()���\�b�h���R�[������
            l_response = 
                (WEB3AdminFEqConAccountOpenMngListDownloadResponse)l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response =
                (WEB3AdminFEqConAccountOpenMngListDownloadResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request,
                "__error in call �O�������J�݊Ǘ��T�[�r�X.execute__",
                l_ex);
            return l_response;
        }
        
        log.exiting(l_strMethodName);
        
        return l_response;
    }
}
@
