head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.03.47.04;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	WEB3AdminAccOpenJudgeHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǘ��Ҍ����J�ݐR���n���h�� (WEB3AdminAccOpenJudgeHandler.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/06/16 ����(���u) �V�K�쐬
*/

package webbroker3.accountopen.handler;

import webbroker3.accountopen.message.WEB3AdminAccOpenInspectConsentCompleteRequest;
import webbroker3.accountopen.message.WEB3AdminAccOpenInspectConsentCompleteResponse;
import webbroker3.accountopen.message.WEB3AdminAccOpenInspectConsentConfirmRequest;
import webbroker3.accountopen.message.WEB3AdminAccOpenInspectConsentConfirmResponse;
import webbroker3.accountopen.message.WEB3AdminAccOpenInspectDenyCompleteRequest;
import webbroker3.accountopen.message.WEB3AdminAccOpenInspectDenyCompleteResponse;
import webbroker3.accountopen.message.WEB3AdminAccOpenInspectDenyConfirmRequest;
import webbroker3.accountopen.message.WEB3AdminAccOpenInspectDenyConfirmResponse;
import webbroker3.accountopen.message.WEB3AdminAccOpenInspectListRequest;
import webbroker3.accountopen.message.WEB3AdminAccOpenInspectListResponse;
import webbroker3.accountopen.message.WEB3AdminAccOpenInspectListSearchRequest;
import webbroker3.accountopen.message.WEB3AdminAccOpenInspectListSearchResponse;
import webbroker3.accountopen.service.delegate.WEB3AdminAccOpenJudgeService;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.util.WEB3LogUtility;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;


/**
 * (�Ǘ��Ҍ����J�ݐR���n���h��)<BR>
 * �Ǘ��Ҍ����J�ݐR���n���h��
 *   
 * @@author ����
 * @@version 1.0
 */
public class WEB3AdminAccOpenJudgeHandler implements MessageHandler 
{
    /**
     * ���O�o�̓��[�e�B���e�B�B<BR>
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance
        (WEB3AdminAccOpenJudgeHandler.class);
    
    /**
     * @@roseuid 44912BFD02BF
     */
    public WEB3AdminAccOpenJudgeHandler() 
    {
     
    }
    
    /**
     * (�R���Ώۈꗗ������ʕ\��)<BR>
     * �R���Ώۈꗗ������ʕ\���������s���B<BR>
     * <BR>
     * �Ǘ��Ҍ����J�ݐR���T�[�r�X���擾���Aexecute()���\�b�h���R�[������B<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * �Ǘ��Ҍ����J�ݐR���Ώۈꗗ�������N�G�X�g�f�[�^�I�u�W�F�N�g<BR>
     * @@return WEB3AdminAccOpenInspectListSearchResponse
     * @@throws WEB3BaseException
     * @@roseuid 4473A7A102DE
     */
    public WEB3AdminAccOpenInspectListSearchResponse inspectObjectListSearchScreen(
        WEB3AdminAccOpenInspectListSearchRequest l_request) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " inspectObjectListSearchScreen(WEB3AdminAccOpenInspectListSearchResponse)";
        log.entering(STR_METHOD_NAME);
        
        WEB3AdminAccOpenInspectListSearchResponse l_response = null;
        WEB3AdminAccOpenJudgeService l_service = null;
        
        try
        {
            l_service = (WEB3AdminAccOpenJudgeService)Services.getService(WEB3AdminAccOpenJudgeService.class);
        }
        // �T�[�r�X�ŗ�O�����������ꍇ�́A�G���[�������X�|���X���b�Z�[�W�ɐݒ肷��
        catch (Exception l_ex)
        {
            l_response = (WEB3AdminAccOpenInspectListSearchResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;  
            log.error(
                l_request, 
                " �Ǘ��Ҍ����J�ݐR���T�[�r�X���擾�Ɏ��s���܂����B",
                l_response.errorInfo, 
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        
        //�Ǘ��Ҍ����J�ݐR���T�[�r�X���擾���Aexecute()���\�b�h���R�[������B
        try
        {
            l_response = (WEB3AdminAccOpenInspectListSearchResponse)l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response = (WEB3AdminAccOpenInspectListSearchResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            l_response.errorMessage = l_ex.getErrorMessage();
            log.error(l_request, " �Ǘ��Ҍ����J�ݐR���Ώۈꗗ������ʕ\�������Ɏ��s���܂����B", l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * (�ꗗ��ʕ\��)<BR>
     * �ꗗ��ʕ\���������s���B<BR>
     * <BR>
     * �Ǘ��Ҍ����J�ݐR���T�[�r�X���擾���Aexecute()���\�b�h���R�[������B<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * �Ǘ��Ҍ����J�ݐR���ꗗ���N�G�X�g�f�[�^�I�u�W�F�N�g<BR>
     * @@return WEB3AdminAccOpenInspectListResponse
     * @@throws WEB3BaseException
     * @@roseuid 4473A8DD005E
     */
    public WEB3AdminAccOpenInspectListResponse screenList(
        WEB3AdminAccOpenInspectListRequest l_request) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " screenList(WEB3AdminAccOpenInspectListRequest)";
        log.entering(STR_METHOD_NAME);
        
        WEB3AdminAccOpenInspectListResponse l_response = null;
        WEB3AdminAccOpenJudgeService l_service = null;
        
        try
        {
            l_service = (WEB3AdminAccOpenJudgeService)Services.getService(WEB3AdminAccOpenJudgeService.class);
        }
        // �T�[�r�X�ŗ�O�����������ꍇ�́A�G���[�������X�|���X���b�Z�[�W�ɐݒ肷��
        catch (Exception l_ex)
        {
            l_response = (WEB3AdminAccOpenInspectListResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;    
            log.error(
                l_request, 
                " �Ǘ��Ҍ����J�ݐR���T�[�r�X���擾�Ɏ��s���܂����B",
                l_response.errorInfo, 
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        
        //�Ǘ��Ҍ����J�ݐR���T�[�r�X���擾���Aexecute()���\�b�h���R�[������B
        try
        {
            l_response = (WEB3AdminAccOpenInspectListResponse)l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response = (WEB3AdminAccOpenInspectListResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            l_response.errorMessage = l_ex.getErrorMessage();
            log.error(l_request, " �Ǘ��Ҍ����J�ݐR���ꗗ��ʕ\�������Ɏ��s���܂����B", l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * (���F�m�F)<BR>
     * ���F�m�F�������s���B<BR>
     * <BR>
     * �Ǘ��Ҍ����J�ݐR���T�[�r�X���擾���Aexecute()���\�b�h���R�[������B<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * �Ǘ��Ҍ����J�ݐR�����F�m�F���N�G�X�g�f�[�^�I�u�W�F�N�g<BR>
     * @@return WEB3AdminAccOpenInspectConsentConfirmResponse
     * @@throws WEB3BaseException
     * @@roseuid 4473AA1F034F
     */
    public WEB3AdminAccOpenInspectConsentConfirmResponse consentConfirm(
        WEB3AdminAccOpenInspectConsentConfirmRequest l_request) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " consentConfirm(WEB3AdminAccOpenInspectConsentConfirmRequest)";
        log.entering(STR_METHOD_NAME);
        
        WEB3AdminAccOpenInspectConsentConfirmResponse l_response = null;
        WEB3AdminAccOpenJudgeService l_service = null;
        
        try
        {
            l_service = (WEB3AdminAccOpenJudgeService)Services.getService(WEB3AdminAccOpenJudgeService.class);
        }
        // �T�[�r�X�ŗ�O�����������ꍇ�́A�G���[�������X�|���X���b�Z�[�W�ɐݒ肷��
        catch (Exception l_ex)
        {
            l_response = (WEB3AdminAccOpenInspectConsentConfirmResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;    
            log.error(
                l_request, 
                " �Ǘ��Ҍ����J�ݐR���T�[�r�X���擾�Ɏ��s���܂����B",
                l_response.errorInfo, 
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        
        //�Ǘ��Ҍ����J�ݐR���T�[�r�X���擾���Aexecute()���\�b�h���R�[������B
        try
        {
            l_response = (WEB3AdminAccOpenInspectConsentConfirmResponse)l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response = (WEB3AdminAccOpenInspectConsentConfirmResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            l_response.errorMessage = l_ex.getErrorMessage();
            log.error(l_request, " �Ǘ��Ҍ����J�ݐR�����F�m�F�����Ɏ��s���܂����B", l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * (���F����)<BR>
     * ���F�����������s���B<BR>
     * <BR>
     * �Ǘ��Ҍ����J�ݐR���T�[�r�X���擾���Aexecute()���\�b�h���R�[������B<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * �Ǘ��Ҍ����J�ݐR�����F�������N�G�X�g�f�[�^�I�u�W�F�N�g<BR>
     * @@return WEB3AdminAccOpenInspectConsentCompleteResponse
     * @@throws WEB3BaseException
     * @@roseuid 4473AA8C01DD
     */
    public WEB3AdminAccOpenInspectConsentCompleteResponse consentComplete(
        WEB3AdminAccOpenInspectConsentCompleteRequest l_request) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " consentComplete(WEB3AdminAccOpenInspectConsentCompleteRequest)";
        log.entering(STR_METHOD_NAME);
        
        WEB3AdminAccOpenInspectConsentCompleteResponse l_response = null;
        WEB3AdminAccOpenJudgeService l_service = null;
        
        try
        {
            l_service = (WEB3AdminAccOpenJudgeService)Services.getService(WEB3AdminAccOpenJudgeService.class);
        }
        // �T�[�r�X�ŗ�O�����������ꍇ�́A�G���[�������X�|���X���b�Z�[�W�ɐݒ肷��
        catch (Exception l_ex)
        {
            l_response = (WEB3AdminAccOpenInspectConsentCompleteResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;    
            log.error(
                l_request, 
                " �Ǘ��Ҍ����J�ݐR���T�[�r�X���擾�Ɏ��s���܂����B",
                l_response.errorInfo, 
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        
        //�Ǘ��Ҍ����J�ݐR���T�[�r�X���擾���Aexecute()���\�b�h���R�[������B
        try
        {
            l_response = (WEB3AdminAccOpenInspectConsentCompleteResponse)l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response = (WEB3AdminAccOpenInspectConsentCompleteResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            l_response.errorMessage = l_ex.getErrorMessage();
            log.error(l_request, " �Ǘ��Ҍ����J�ݐR�����F���������Ɏ��s���܂����B", l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * (�۔F�m�F)<BR>
     * �۔F�m�F�������s���B<BR>
     * <BR>
     * �Ǘ��Ҍ����J�ݐR���T�[�r�X���擾���Aexecute()���\�b�h���R�[������B<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * �Ǘ��Ҍ����J�ݐR���۔F�m�F���N�G�X�g�f�[�^�I�u�W�F�N�g<BR>
     * @@return WEB3AdminAccOpenInspectDenyConfirmResponse
     * @@throws WEB3BaseException
     * @@roseuid 4473AB130090
     */
    public WEB3AdminAccOpenInspectDenyConfirmResponse denyConfirm(
        WEB3AdminAccOpenInspectDenyConfirmRequest l_request) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " denyConfirm(WEB3AdminAccOpenInspectDenyConfirmRequest)";
        log.entering(STR_METHOD_NAME);
        
        WEB3AdminAccOpenInspectDenyConfirmResponse l_response = null;
        WEB3AdminAccOpenJudgeService l_service = null;
        
        try
        {
            l_service = (WEB3AdminAccOpenJudgeService)Services.getService(WEB3AdminAccOpenJudgeService.class);
        }
        // �T�[�r�X�ŗ�O�����������ꍇ�́A�G���[�������X�|���X���b�Z�[�W�ɐݒ肷��
        catch (Exception l_ex)
        {
            l_response = (WEB3AdminAccOpenInspectDenyConfirmResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;    
            log.error(
                l_request, 
                " �Ǘ��Ҍ����J�ݐR���T�[�r�X���擾�Ɏ��s���܂����B",
                l_response.errorInfo, 
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        
        //�Ǘ��Ҍ����J�ݐR���T�[�r�X���擾���Aexecute()���\�b�h���R�[������B
        try
        {
            l_response = (WEB3AdminAccOpenInspectDenyConfirmResponse)l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response = (WEB3AdminAccOpenInspectDenyConfirmResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            l_response.errorMessage = l_ex.getErrorMessage();
            log.error(l_request, " �Ǘ��Ҍ����J�ݐR���۔F�m�F�����Ɏ��s���܂����B", l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * (�۔F����)<BR>
     * �۔F�����������s���B<BR>
     * <BR>
     * �Ǘ��Ҍ����J�ݐR���T�[�r�X���擾���Aexecute()���\�b�h���R�[������B<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * �Ǘ��Ҍ����J�ݐR�����F�������N�G�X�g �f�[�^�I�u�W�F�N�g<BR>
     * @@return WEB3AdminAccOpenInspectDenyCompleteResponse
     * @@throws WEB3BaseException
     * @@roseuid 4473AB2A031A
     */
    public WEB3AdminAccOpenInspectDenyCompleteResponse denyComplete(
        WEB3AdminAccOpenInspectDenyCompleteRequest l_request) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " denyComplete(WEB3AdminAccOpenInspectDenyCompleteRequest)";
        log.entering(STR_METHOD_NAME);
        
        WEB3AdminAccOpenInspectDenyCompleteResponse l_response = null;
        WEB3AdminAccOpenJudgeService l_service = null;
        
        try
        {
            l_service = (WEB3AdminAccOpenJudgeService)Services.getService(WEB3AdminAccOpenJudgeService.class);
        }
        // �T�[�r�X�ŗ�O�����������ꍇ�́A�G���[�������X�|���X���b�Z�[�W�ɐݒ肷��
        catch (Exception l_ex)
        {
            l_response = (WEB3AdminAccOpenInspectDenyCompleteResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;    
            log.error(
                l_request, 
                " �Ǘ��Ҍ����J�ݐR���T�[�r�X���擾�Ɏ��s���܂����B",
                l_response.errorInfo, 
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        
        //�Ǘ��Ҍ����J�ݐR���T�[�r�X���擾���Aexecute()���\�b�h���R�[������B
        try
        {
            l_response = (WEB3AdminAccOpenInspectDenyCompleteResponse)l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response = (WEB3AdminAccOpenInspectDenyCompleteResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            l_response.errorMessage = l_ex.getErrorMessage();
            log.error(l_request, " �Ǘ��Ҍ����J�ݐR���۔F���������Ɏ��s���܂����B", l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
}
@
