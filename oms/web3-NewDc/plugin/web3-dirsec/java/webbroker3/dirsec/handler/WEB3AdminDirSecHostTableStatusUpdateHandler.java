head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.16;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3AdminDirSecHostTableStatusUpdateHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǘ��҃L���[�e�[�u���X�e�[�^�X�X�V�n���h��(WEB3AdminDirSecHostTableStatusUpdateHandler.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/03/29 ����(���u) �V�K�쐬
*/

package webbroker3.dirsec.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.dirsec.message.WEB3AdminDirSecHostTableReferenceRequest;
import webbroker3.dirsec.message.WEB3AdminDirSecHostTableReferenceResponse;
import webbroker3.dirsec.message.WEB3AdminDirSecHostTableSearchInputRequest;
import webbroker3.dirsec.message.WEB3AdminDirSecHostTableSearchInputResponse;
import webbroker3.dirsec.message.WEB3AdminDirSecHostTableSearchListRequest;
import webbroker3.dirsec.message.WEB3AdminDirSecHostTableSearchListResponse;
import webbroker3.dirsec.message.WEB3AdminDirSecHostTableStatusCompleteRequest;
import webbroker3.dirsec.message.WEB3AdminDirSecHostTableStatusCompleteResponse;
import webbroker3.dirsec.message.WEB3AdminDirSecHostTableStatusConfirmRequest;
import webbroker3.dirsec.message.WEB3AdminDirSecHostTableStatusConfirmResponse;
import webbroker3.dirsec.service.delegate.WEB3AdminDirSecHostTableStatusUpdateService;
import webbroker3.util.WEB3LogUtility;

/**
 * (�Ǘ��҃L���[�e�[�u���X�e�[�^�X�X�V�n���h��)<BR>
 * �Ǘ��҃L���[�e�[�u���X�e�[�^�X�X�V�n���h���N���X�B<BR>
 * 
 * @@author ����
 * @@version 1.0
 */
public class WEB3AdminDirSecHostTableStatusUpdateHandler implements MessageHandler 
{
    
    /**
     * ���O�o�̓��[�e�B���e�B�B<BR>
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(
        WEB3AdminDirSecHostTableStatusUpdateHandler.class);
    
    /**
     * @@roseuid 442A1C8A000F
     */
    public WEB3AdminDirSecHostTableStatusUpdateHandler() 
    {
     
    }
    
    /**
     * (get�L���[�e�[�u���ꗗ)<BR>
     * �L���[�e�[�u���ꗗ��ʕ\���������s���B <BR>
     * <BR>
     * �Ǘ��҃L���[�e�[�u���X�e�[�^�X�X�V�T�[�r�XImpl���擾���A <BR>
     * execute()���\�b�h���R�[������B<BR>
     * 
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * �Ǘ��ҁE�L���[�e�[�u���ꗗ���N�G�X�g�N���X�B<BR>
     * @@return WEB3AdminDirSecHostTableReferenceResponse
     * @@roseuid 4417D3D50183
     */
    public WEB3AdminDirSecHostTableReferenceResponse getHostTableList(
        WEB3AdminDirSecHostTableReferenceRequest l_request) 
    {
    
        final String STR_METHOD_NAME = 
            " getHostTableList(WEB3AdminDirSecHostTableReferenceRequest)";
        log.entering(STR_METHOD_NAME);
        
        WEB3AdminDirSecHostTableReferenceResponse l_response = null;
        WEB3AdminDirSecHostTableStatusUpdateService l_service = null;
        
        //�Ǘ��҃L���[�e�[�u���X�e�[�^�X�X�V�T�[�r�X���擾
        try
        {
            l_service = (WEB3AdminDirSecHostTableStatusUpdateService)Services.getService(
                WEB3AdminDirSecHostTableStatusUpdateService.class);
        }
        catch(Exception l_ex)
        {
            l_response = (WEB3AdminDirSecHostTableReferenceResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request, 
                "�Ǘ��҃L���[�e�[�u���X�e�[�^�X�X�V�T�[�r�XImpl���擾�Ɏ��s���܂����B",
                l_response.errorInfo, 
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        
        try
        {
            l_response =
                (WEB3AdminDirSecHostTableReferenceResponse)l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response =
                (WEB3AdminDirSecHostTableReferenceResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();

            log.error(
                l_request, 
                "�L���[�e�[�u���ꗗ��ʕ\�������̎��{�Ɏ��s���܂����B", 
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * (get�����������͉��)<BR>
     * �L���[�e�[�u�����R�[�h������ʕ\���������s���B <BR>
     * <BR>
     * �Ǘ��҃L���[�e�[�u���X�e�[�^�X�X�V�T�[�r�XImpl���擾���A <BR>
     * execute()���\�b�h���R�[������B<BR>
     * 
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * �Ǘ��ҁE�L���[�e�[�u���������̓��N�G�X�g�N���X�B<BR>
     * @@return WEB3AdminDirSecHostTableSearchInputResponse
     * @@roseuid 4417D67E007E
     */
    public WEB3AdminDirSecHostTableSearchInputResponse getQueryConditionInputScreen(
        WEB3AdminDirSecHostTableSearchInputRequest l_request) 
    {
        final String STR_METHOD_NAME = 
            " getQueryConditionInputScreen(WEB3AdminDirSecHostTableSearchInputRequest)";
        log.entering(STR_METHOD_NAME);
        
        WEB3AdminDirSecHostTableSearchInputResponse l_response = null;
        WEB3AdminDirSecHostTableStatusUpdateService l_service = null;
        
        //�Ǘ��҃L���[�e�[�u���X�e�[�^�X�X�V�T�[�r�X���擾
        try
        {
            l_service = (WEB3AdminDirSecHostTableStatusUpdateService)Services.getService(
                WEB3AdminDirSecHostTableStatusUpdateService.class);
        }
        catch(Exception l_ex)
        {
            l_response = (WEB3AdminDirSecHostTableSearchInputResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request, 
                "�Ǘ��҃L���[�e�[�u���X�e�[�^�X�X�V�T�[�r�XImpl���擾�Ɏ��s���܂����B", 
                l_response.errorInfo, 
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        
        try
        {
            l_response =
                (WEB3AdminDirSecHostTableSearchInputResponse)l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response =
                (WEB3AdminDirSecHostTableSearchInputResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();

            log.error(
                l_request, 
                "�L���[�e�[�u�����R�[�h������ʕ\�������̎��{�Ɏ��s���܂����B", 
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * (get�������ʈꗗ)<BR>
     * �L���[�e�[�u�����R�[�h�������ʉ�ʕ\���������s���B <BR>
     * <BR>
     * �Ǘ��҃L���[�e�[�u���X�e�[�^�X�X�V�T�[�r�XImpl���擾���A <BR>
     * execute()���\�b�h���R�[������B<BR>
     * 
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * �Ǘ��ҁE�L���[�e�[�u���������ʃ��N�G�X�g�N���X�B<BR>
     * @@return WEB3AdminDirSecHostTableSearchListResponse
     * @@roseuid 4417D780014A
     */
    public WEB3AdminDirSecHostTableSearchListResponse getQueryResultList
        (WEB3AdminDirSecHostTableSearchListRequest l_request) 
    {
        final String STR_METHOD_NAME = 
            " getQueryResultList(WEB3AdminDirSecHostTableSearchListRequest)";
        log.entering(STR_METHOD_NAME);
        
        WEB3AdminDirSecHostTableSearchListResponse l_response = null;
        WEB3AdminDirSecHostTableStatusUpdateService l_service = null;
        
        //�Ǘ��҃L���[�e�[�u���X�e�[�^�X�X�V�T�[�r�X���擾
        try
        {
            l_service = (WEB3AdminDirSecHostTableStatusUpdateService)Services.getService(
                WEB3AdminDirSecHostTableStatusUpdateService.class);
        }
        catch(Exception l_ex)
        {
            l_response = (WEB3AdminDirSecHostTableSearchListResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "�Ǘ��҃L���[�e�[�u���X�e�[�^�X�X�V�T�[�r�XImpl���擾�Ɏ��s���܂����B",
                l_response.errorInfo, 
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        
        try
        {
            l_response =
                (WEB3AdminDirSecHostTableSearchListResponse)l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response =
                (WEB3AdminDirSecHostTableSearchListResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();

            log.error(
                l_request,
                "�L���[�e�[�u�����R�[�h�������ʉ�ʕ\�������̎��{�Ɏ��s���܂����B", 
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * (get�X�V�m�F���)<BR>
     * �L���[�e�[�u���X�e�[�^�X�X�V�m�F��ʕ\���������s���B <BR>
     * <BR>
     * �Ǘ��҃L���[�e�[�u���X�e�[�^�X�X�V�T�[�r�XImpl���擾���A <BR>
     * execute()���\�b�h���R�[������B<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * �L���[�e�[�u���X�e�[�^�X�X�V�m�F���N�G�X�g�N���X�B<BR>
     * @@return WEB3AdminDirSecHostTableStatusConfirmResponse
     * @@roseuid 4417D7970002
     */
    public WEB3AdminDirSecHostTableStatusConfirmResponse getUpdateConfirmScreen
        (WEB3AdminDirSecHostTableStatusConfirmRequest l_request) 
    {
        final String STR_METHOD_NAME = 
            " getUpdateConfirmScreen(WEB3AdminDirSecHostTableStatusConfirmRequest)";
        log.entering(STR_METHOD_NAME);
        
        WEB3AdminDirSecHostTableStatusConfirmResponse l_response = null;
        WEB3AdminDirSecHostTableStatusUpdateService l_service = null;
        
        //�Ǘ��҃L���[�e�[�u���X�e�[�^�X�X�V�T�[�r�X���擾
        try
        {
            l_service = (WEB3AdminDirSecHostTableStatusUpdateService)Services.getService(
                WEB3AdminDirSecHostTableStatusUpdateService.class);
        }
        catch(Exception l_ex)
        {
            l_response = (WEB3AdminDirSecHostTableStatusConfirmResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request, 
                "�Ǘ��҃L���[�e�[�u���X�e�[�^�X�X�V�T�[�r�XImpl���擾�Ɏ��s���܂����B", 
                l_response.errorInfo, 
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        
        try
        {
            l_response =
                (WEB3AdminDirSecHostTableStatusConfirmResponse)l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response =
                (WEB3AdminDirSecHostTableStatusConfirmResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();

            log.error(
                l_request, 
                "�L���[�e�[�u���X�e�[�^�X�X�V�m�F��ʕ\�������̎��{�Ɏ��s���܂����B", 
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * (get�X�V�������)<BR>
     * �L���[�e�[�u���X�e�[�^�X�X�V������ʕ\���������s���B <BR>
     * <BR>
     * �Ǘ��҃L���[�e�[�u���X�e�[�^�X�X�V�T�[�r�XImpl���擾���A <BR>
     * execute()���\�b�h���R�[������B<BR>
     * 
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * �L���[�e�[�u���X�e�[�^�X�X�V�������N�G�X�g�N���X�B<BR>
     * @@return WEB3AdminDirSecHostTableStatusCompleteResponse
     * @@roseuid 4417D7A70070
     */
    public WEB3AdminDirSecHostTableStatusCompleteResponse getUpdateCompleteScreen
        (WEB3AdminDirSecHostTableStatusCompleteRequest l_request) 
    {
        final String STR_METHOD_NAME = 
            " getUpdateCompleteScreen(WEB3AdminDirSecHostTableStatusCompleteRequest)";
        log.entering(STR_METHOD_NAME);
        
        WEB3AdminDirSecHostTableStatusCompleteResponse l_response = null;
        WEB3AdminDirSecHostTableStatusUpdateService l_service = null;
        
        //�Ǘ��҃L���[�e�[�u���X�e�[�^�X�X�V�T�[�r�X���擾
        try
        {
            l_service = (WEB3AdminDirSecHostTableStatusUpdateService)Services.getService(
                WEB3AdminDirSecHostTableStatusUpdateService.class);
        }
        catch(Exception l_ex)
        {
            l_response = (WEB3AdminDirSecHostTableStatusCompleteResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "�Ǘ��҃L���[�e�[�u���X�e�[�^�X�X�V�T�[�r�XImpl���擾�Ɏ��s���܂����B",
                l_response.errorInfo,
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        
        try
        {
            l_response =
                (WEB3AdminDirSecHostTableStatusCompleteResponse)l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response =
                (WEB3AdminDirSecHostTableStatusCompleteResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();

            log.error(
                l_request, 
                "�L���[�e�[�u���X�e�[�^�X�X�V������ʕ\�������̎��{�Ɏ��s���܂����B",
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
}
@
