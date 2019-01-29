head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.23;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3AdminFPTForceLogoutHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǘ��ҁE���ʖ����� �������O�A�E�g�n���h��(WEB3AdminFPTForceLogoutHandler.java)
Author Name      : Daiwa Institute of Research
Revision History : 2008/03/18 ��(FLJ) �V�K�쐬
*/
package webbroker3.docadmin.handler;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.docadmin.message.WEB3AdminFPTForceLogoutCompleteRequest;
import webbroker3.docadmin.message.WEB3AdminFPTForceLogoutCompleteResponse;
import webbroker3.docadmin.message.WEB3AdminFPTForceLogoutConfirmRequest;
import webbroker3.docadmin.message.WEB3AdminFPTForceLogoutInputRequest;
import webbroker3.docadmin.message.WEB3AdminFPTForceLogoutInputResponse;
import webbroker3.docadmin.message.WEB3AdminFPTForceLogoutReferenceRequest;
import webbroker3.docadmin.message.WEB3AdminFPTForceLogoutReferenceResponse;
import webbroker3.docadmin.message.WEB3AdminFPTForceLogoutConfirmResponse;
import webbroker3.docadmin.service.delegate.WEB3AdminFPTForceLogoutService;
import webbroker3.util.WEB3LogUtility;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

/**
 * �Ǘ��� ���ʖ����� �������O�A�E�g�n���h��
 * <BR>
 * @@author Kyo
 * @@version 1.0
 */
public class WEB3AdminFPTForceLogoutHandler implements MessageHandler
{
    /**
     * @@log WEB3LogUtility
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminFPTForceLogoutHandler.class);
    
    /**
     * @@roseuid 47DF467600C3
     */
    public WEB3AdminFPTForceLogoutHandler() 
    {
     
    }
    
    /**
     * get���͉��
     * 
     * �Ǘ��� ���ʖ����� �������O�A�E�g���͉�ʕ\���������s���B 
     * 
     * �Ǘ��� ���ʖ����� �������O�A�E�g�T�[�r�XImpl���擾���A 
     * execute()���\�b�h���R�[������B
     * @@param �Ǘ��� ���ʖ����� �������O�A�E�g���̓��N�G�X�g - �Ǘ��� ���ʖ����� 
     * �������O�A�E�g���̓��N�G�X�g
     * @@return 
     * docadmin.���ʖ������������O�A�E�g�T�[�r�X���f���i�Ǘ��ҁj.���b�Z�[�W.WEB3AdminFP
     * TForceLogoutInputResponse
     * @@roseuid 47D638950042
     */
    public WEB3AdminFPTForceLogoutInputResponse getInputPage(WEB3AdminFPTForceLogoutInputRequest l_request) 
    {
        final String STR_METHOD_NAME =
            "getInputPage(WEB3AdminFPTForceLogoutInputRequest)";
        log.entering(STR_METHOD_NAME);

        WEB3AdminFPTForceLogoutInputResponse l_response = null;
        WEB3AdminFPTForceLogoutService l_service = null;

        try
        {
            l_service =
                (WEB3AdminFPTForceLogoutService) Services.getService(
                        WEB3AdminFPTForceLogoutService.class);
        } 
        catch (Exception l_exp)
        {
            l_response =
                (WEB3AdminFPTForceLogoutInputResponse) l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;

            log.error(
                l_request,
                "Failed while acquiring WEB3AdminFPTForceLogoutServiceImpl ",
                l_response.errorInfo,
                l_exp);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        
        try
        {
            l_response =
                (WEB3AdminFPTForceLogoutInputResponse) l_service.execute(l_request);
        } 
        catch (WEB3BaseException l_exp)
        {
            log.error(l_request, "Failed to access getInputPage()", l_exp);
            l_response =
                (WEB3AdminFPTForceLogoutInputResponse) l_request.createResponse();
            l_response.errorInfo = l_exp.getErrorInfo();
        }
        
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * validate�������O�A�E�g
     * 
     * �Ǘ��� ���ʖ����� �������O�A�E�g�m�F��ʕ\���������s���B 
     * 
     * �Ǘ��� ���ʖ����� �������O�A�E�g�T�[�r�XImpl���擾���A 
     * execute()���\�b�h���R�[������B
     * @@param �Ǘ��� ���ʖ����� �������O�A�E�g�m�F���N�G�X�g - �Ǘ��� ���ʖ����� 
     * �������O�A�E�g�m�F���N�G�X�g
     * @@return 
     * docadmin.���ʖ������������O�A�E�g�T�[�r�X���f���i�Ǘ��ҁj.���b�Z�[�W.WEB3AdminFP
     * TForceLogoutconfirmResponse
     * @@roseuid 47D638F601B1
     */
    public WEB3AdminFPTForceLogoutConfirmResponse validateForceLogout(WEB3AdminFPTForceLogoutConfirmRequest l_request) 
    {
        final String STR_METHOD_NAME =
            "validateForceLogout(WEB3AdminFPTForceLogoutConfirmRequest)";
        log.entering(STR_METHOD_NAME);

        WEB3AdminFPTForceLogoutConfirmResponse l_response = null;
        WEB3AdminFPTForceLogoutService l_service = null;

        try
        {
            l_service =
                (WEB3AdminFPTForceLogoutService) Services.getService(
                        WEB3AdminFPTForceLogoutService.class);
        } 
        catch (Exception l_exp)
        {
            l_response =
                (WEB3AdminFPTForceLogoutConfirmResponse) l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;

            log.error(
                l_request,
                "Failed while acquiring WEB3AdminFPTForceLogoutServiceImpl ",
                l_response.errorInfo,
                l_exp);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        
        try
        {
            l_response =
                (WEB3AdminFPTForceLogoutConfirmResponse) l_service.execute(l_request);
        } 
        catch (WEB3BaseException l_exp)
        {
            log.error(l_request, "Failed to access validateForceLogout()", l_exp);
            l_response =
                (WEB3AdminFPTForceLogoutConfirmResponse) l_request.createResponse();
            l_response.errorInfo = l_exp.getErrorInfo();
        }
        
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * submit�������O�A�E�g
     * 
     * �Ǘ��� ���ʖ����� �������O�A�E�g������ʕ\���������s���B 
     * 
     * �Ǘ��� ���ʖ����� �������O�A�E�g�T�[�r�XImpl���擾���A 
     * execute()���\�b�h���R�[������B
     * @@param �Ǘ��� ���ʖ����� �������O�A�E�g�������N�G�X�g - �Ǘ��� ���ʖ����� 
     * �������O�A�E�g�������N�G�X�g
     * @@return 
     * docadmin.���ʖ������������O�A�E�g�T�[�r�X���f���i�Ǘ��ҁj.���b�Z�[�W.WEB3AdminFP
     * TForceLogoutCompleteResponse
     * @@roseuid 47D6393F0286
     */
    public WEB3AdminFPTForceLogoutCompleteResponse submitForceLogout(WEB3AdminFPTForceLogoutCompleteRequest l_request) 
    {
        final String STR_METHOD_NAME =
            "submitForceLogout(WEB3AdminFPTForceLogoutCompleteRequest)";
        log.entering(STR_METHOD_NAME);

        WEB3AdminFPTForceLogoutCompleteResponse l_response = null;
        WEB3AdminFPTForceLogoutService l_service = null;

        try
        {
            l_service =
                (WEB3AdminFPTForceLogoutService) Services.getService(
                        WEB3AdminFPTForceLogoutService.class);
        } 
        catch (Exception l_exp)
        {
            l_response =
                (WEB3AdminFPTForceLogoutCompleteResponse) l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;

            log.error(
                l_request,
                "Failed while acquiring WEB3AdminFPTForceLogoutServiceImpl ",
                l_response.errorInfo,
                l_exp);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        
        try
        {
            l_response =
                (WEB3AdminFPTForceLogoutCompleteResponse) l_service.execute(l_request);
        } 
        catch (WEB3BaseException l_exp)
        {
            log.error(l_request, "Failed to access submitForceLogout()", l_exp);
            l_response =
                (WEB3AdminFPTForceLogoutCompleteResponse) l_request.createResponse();
            l_response.errorInfo = l_exp.getErrorInfo();
        }
        
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * submit�������O�A�E�g
     * 
     * �Ǘ��� ���ʖ����� �������O�A�E�g������ʕ\���������s���B 
     * 
     * �Ǘ��� ���ʖ����� �������O�A�E�g�T�[�r�XImpl���擾���A 
     * execute()���\�b�h���R�[������B
     * @@param �Ǘ��� ���ʖ����� �������O�A�E�g���ʏƉ�N�G�X�g - �Ǘ��� ���ʖ����� 
     * �������O�A�E�g���ʏƉ�N�G�X�g
     * @@return 
     * docadmin.���ʖ������������O�A�E�g�T�[�r�X���f���i�Ǘ��ҁj.���b�Z�[�W.WEB3AdminFP
     * TForceLogoutReferenceResponse
     * @@roseuid 47DB26750220
     */
    public WEB3AdminFPTForceLogoutReferenceResponse getResultRefrence(WEB3AdminFPTForceLogoutReferenceRequest l_request) 
    {
        final String STR_METHOD_NAME =
            "getResultRefrence(WEB3AdminFPTForceLogoutReferenceRequest)";
        log.entering(STR_METHOD_NAME);

        WEB3AdminFPTForceLogoutReferenceResponse l_response = null;
        WEB3AdminFPTForceLogoutService l_service = null;

        try
        {
            l_service =
                (WEB3AdminFPTForceLogoutService) Services.getService(
                        WEB3AdminFPTForceLogoutService.class);
        } 
        catch (Exception l_exp)
        {
            l_response =
                (WEB3AdminFPTForceLogoutReferenceResponse) l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;

            log.error(
                l_request,
                "Failed while acquiring WEB3AdminFPTForceLogoutServiceImpl ",
                l_response.errorInfo,
                l_exp);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        
        try
        {
            l_response =
                (WEB3AdminFPTForceLogoutReferenceResponse) l_service.execute(l_request);
        } 
        catch (WEB3BaseException l_exp)
        {
            log.error(l_request, "Failed to access getResultRefrence()", l_exp);
            l_response =
                (WEB3AdminFPTForceLogoutReferenceResponse) l_request.createResponse();
            l_response.errorInfo = l_exp.getErrorInfo();
        }
        
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
}
@
