head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.25.48;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3AdminFEqConAccountMngHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
 Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
 File Name        : �O�������Ǘ��n���h���N���X(WEB3AdminFEqConAccountMngHandler)
 Author Name      : Daiwa Institute of Research
 Revesion History : 2005/03/21 ����(���u) �V�K�쐬
 */

package webbroker3.aio.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.aio.message.WEB3AdminFEqConAccountInfoSearchInputRequest;
import webbroker3.aio.message.WEB3AdminFEqConAccountInfoSearchInputResponse;
import webbroker3.aio.message.WEB3AdminFEqConAccountInfoSearchRequest;
import webbroker3.aio.message.WEB3AdminFEqConAccountInfoSearchResponse;
import webbroker3.aio.message.WEB3AdminFEqConAccountStateChangeCompleteRequest;
import webbroker3.aio.message.WEB3AdminFEqConAccountStateChangeCompleteResponse;
import webbroker3.aio.message.WEB3AdminFEqConAccountStateChangeConfirmRequest;
import webbroker3.aio.message.WEB3AdminFEqConAccountStateChangeConfirmResponse;
import webbroker3.aio.service.delegate.WEB3AdminFEqConAccountMngService;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.util.WEB3LogUtility;

/**
 * (�O�������Ǘ��n���h��)<BR>
 * �O�������Ǘ��n���h���N���X
 * 
 * @@author ����(���u)
 * @@version 1.0
 */

public class WEB3AdminFEqConAccountMngHandler implements MessageHandler 
{
    
    /**
     * ���O�o�̓��[�e�B���e�B�B
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminFEqConAccountMngHandler.class);
    
    /**
     * (���͉�ʕ\��)<BR>
     * ���͉�ʕ\�����s���B<BR>
     * <BR>
     * �O�������Ǘ��T�[�r�X���擾���Aexecute()���\�b�h���R�[������B
     * @@param l_request - ���N�G�X�g�f�[�^
     * 
     * @@return WEB3AdminFEqConAccountInfoSearchInputResponse
     * @@roseuid 41E3B8BC0194
     */
    public WEB3AdminFEqConAccountInfoSearchInputResponse displayInputScreen(
        WEB3AdminFEqConAccountInfoSearchInputRequest l_request) 
    {
        final String STR_METHOD_NAME =
            "displayInputScreen(WEB3AdminFEqConAccountInfoSearchInputRequest l_request)";
        log.entering(STR_METHOD_NAME);
        
        //�O�������Ǘ��T�[�r�X
        WEB3AdminFEqConAccountMngService l_service = null;
        //�O��������񌟍��������̓��X�|���X
        WEB3AdminFEqConAccountInfoSearchInputResponse l_response = null;
        try
        {
            l_service =
                (WEB3AdminFEqConAccountMngService) Services.getService(
                    WEB3AdminFEqConAccountMngService.class);
        }
        catch (Exception l_ex)
        {
            //�T�[�r�X�ŗ�O�����������ꍇ�́A
            //�G���[�������X�|���X���b�Z�[�W�ɐݒ肷��
            l_response =
                (WEB3AdminFEqConAccountInfoSearchInputResponse) l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "�O�������Ǘ��T�[�r�X�̎擾�Ɏ��s���܂����B",
                l_response.errorInfo,
                l_ex);
            return l_response;
        }
        try
        {
            l_response =
                (WEB3AdminFEqConAccountInfoSearchInputResponse) l_service.execute(
                    l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response =
                (WEB3AdminFEqConAccountInfoSearchInputResponse) l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request, 
                "�O��������񌟍��������̓��X�|���X�̏����Ɏ��s���܂����B",
                l_response.errorInfo,
                l_ex);
            return l_response;
        }
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * (������ʕ\��)<BR>
     * ������ʕ\�����s���B<BR>
     * <BR>
     * �O�������Ǘ��T�[�r�X���擾���Aexecute()���\�b�h���R�[������B
     * @@param l_request - ���N�G�X�g�f�[�^
     * 
     * @@return WEB3AdminFEqConAccountInfoSearchResponse
     * @@roseuid 41E3B8BC01B3
     */
    public WEB3AdminFEqConAccountInfoSearchResponse displaySearchScreen(
        WEB3AdminFEqConAccountInfoSearchRequest l_request) 
    {
        final String STR_METHOD_NAME =
            "displaySearchScreen(WEB3AdminFEqConAccountInfoSearchRequest l_request)";
        log.entering(STR_METHOD_NAME);
        
        //�O�������Ǘ��T�[�r�X
        WEB3AdminFEqConAccountMngService l_service = null;
        //�O��������񌟍����X�|���X
        WEB3AdminFEqConAccountInfoSearchResponse l_response = null;
        try
        {
            l_service =
                (WEB3AdminFEqConAccountMngService) Services.getService(
                    WEB3AdminFEqConAccountMngService.class);
        }
        catch (Exception l_ex)
        {
            //�T�[�r�X�ŗ�O�����������ꍇ�́A
            //�G���[�������X�|���X���b�Z�[�W�ɐݒ肷��
            l_response =
                (WEB3AdminFEqConAccountInfoSearchResponse) l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "�O�������Ǘ��T�[�r�X�̎擾�Ɏ��s���܂����B",
                l_response.errorInfo,
                l_ex);
            return l_response;
        }
        try
        {
            l_response =
                (WEB3AdminFEqConAccountInfoSearchResponse) l_service.execute(
                    l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response =
                (WEB3AdminFEqConAccountInfoSearchResponse) l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request, 
                "�O��������񌟍����X�|���X�̏����Ɏ��s���܂����B",
                l_response.errorInfo,
                l_ex);
            return l_response;
        }
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * (�����J�ݏ󋵕ύX�m�F)<BR>
     * �����J�ݏ󋵕ύX�̊m�F�������s���B<BR>
     * <BR>
     * �O�������Ǘ��T�[�r�X���擾���Aexecute()���\�b�h���R�[������B
     * @@param l_request - ���N�G�X�g�f�[�^
     * 
     * @@return WEB3AdminFEqConAccountStateChangeConfirmResponse
     * @@roseuid 41E3B8BC01E2
     */
    public WEB3AdminFEqConAccountStateChangeConfirmResponse accountStateChangeConfirm(
        WEB3AdminFEqConAccountStateChangeConfirmRequest l_request) 
    {
        final String STR_METHOD_NAME =
            "accountStateChangeConfirm(WEB3AdminFEqConAccountStateChangeConfirmRequest l_request)";
        log.entering(STR_METHOD_NAME);
        
        //�O�������Ǘ��T�[�r�X
        WEB3AdminFEqConAccountMngService l_service = null;
        //�O�������J�ݏ󋵕ύX�m�F���X�|���X
        WEB3AdminFEqConAccountStateChangeConfirmResponse l_response = null;
        try
        {
            l_service =
                (WEB3AdminFEqConAccountMngService) Services.getService(
                    WEB3AdminFEqConAccountMngService.class);
        }
        catch (Exception l_ex)
        {
            //�T�[�r�X�ŗ�O�����������ꍇ�́A
            //�G���[�������X�|���X���b�Z�[�W�ɐݒ肷��
            l_response =
                (WEB3AdminFEqConAccountStateChangeConfirmResponse) l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "�O�������Ǘ��T�[�r�X�̎擾�Ɏ��s���܂����B",
                l_response.errorInfo,
                l_ex);
            return l_response;
        }
        try
        {
            l_response =
                (WEB3AdminFEqConAccountStateChangeConfirmResponse) l_service.execute(
                    l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response =
                (WEB3AdminFEqConAccountStateChangeConfirmResponse) l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request, 
                "�O�������J�ݏ󋵕ύX�m�F���X�|���X�̏����Ɏ��s���܂����B",
                l_response.errorInfo,
                l_ex);
            return l_response;
        }
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * (�����J�ݏ󋵕ύX����)<BR>
     * �����J�ݏ󋵕ύX�̊����������s���B<BR>
     * <BR>
     * �O�������Ǘ��T�[�r�X���擾���Aexecute()���\�b�h���R�[������B
     * @@param l_request - ���N�G�X�g�f�[�^
     * 
     * @@return WEB3AdminFEqConAccountStateChangeCompleteResponse
     * @@roseuid 41E3B8BC0201
     */
    public WEB3AdminFEqConAccountStateChangeCompleteResponse accountStateChangeComplete(
        WEB3AdminFEqConAccountStateChangeCompleteRequest l_request) 
    {
        final String STR_METHOD_NAME =
            "accountStateChangeComplete(WEB3AdminFEqConAccountStateChangeCompleteRequest l_request)";
        log.entering(STR_METHOD_NAME);
        
        //�O�������Ǘ��T�[�r�X
        WEB3AdminFEqConAccountMngService l_service = null;
        //�O�������J�ݏ󋵕ύX�������X�|���X
        WEB3AdminFEqConAccountStateChangeCompleteResponse l_response = null;
        try
        {
            l_service =
                (WEB3AdminFEqConAccountMngService) Services.getService(
                    WEB3AdminFEqConAccountMngService.class);
        }
        catch (Exception l_ex)
        {
            //�T�[�r�X�ŗ�O�����������ꍇ�́A
            //�G���[�������X�|���X���b�Z�[�W�ɐݒ肷��
            l_response =
                (WEB3AdminFEqConAccountStateChangeCompleteResponse) l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "�O�������Ǘ��T�[�r�X�̎擾�Ɏ��s���܂����B",
                l_response.errorInfo,
                l_ex);
            return l_response;
        }
        try
        {
            l_response =
                (WEB3AdminFEqConAccountStateChangeCompleteResponse) l_service.execute(
                    l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response =
                (WEB3AdminFEqConAccountStateChangeCompleteResponse) l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request, 
                "�O�������J�ݏ󋵕ύX�������X�|���X�̏����Ɏ��s���܂����B",
                l_response.errorInfo,
                l_ex);
            return l_response;
        }
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
}
@
