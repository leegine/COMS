head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.03.47.00;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	WEB3AdminAccOpenStateInquiryHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǘ��Ҍ����J�ݏ󋵖⍇���n���h��(WEB3AdminAccOpenStateInquiryHandler.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/12/21 �s�p (���u) �V�K�쐬
Revesion History : 2009/08/13 ���g (���u) �d�l�ύX ���f��No.175
*/

package webbroker3.accountopen.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.accountopen.message.WEB3AccOpenChangeRequest;
import webbroker3.accountopen.message.WEB3AccOpenChangeResponse;
import webbroker3.accountopen.message.WEB3AdminAccOpenApplyUpdateCompleteRequest;
import webbroker3.accountopen.message.WEB3AdminAccOpenApplyUpdateCompleteResponse;
import webbroker3.accountopen.message.WEB3AdminAccOpenApplyUpdateConfirmRequest;
import webbroker3.accountopen.message.WEB3AdminAccOpenApplyUpdateConfirmResponse;
import webbroker3.accountopen.message.WEB3AdminAccOpenStateInquiryDetailRequest;
import webbroker3.accountopen.message.WEB3AdminAccOpenStateInquiryDetailResponse;
import webbroker3.accountopen.message.WEB3AdminAccOpenStateInquiryInputRequest;
import webbroker3.accountopen.message.WEB3AdminAccOpenStateInquiryInputResponse;
import webbroker3.accountopen.message.WEB3AdminAccOpenStateInquiryListRequest;
import webbroker3.accountopen.message.WEB3AdminAccOpenStateInquiryListResponse;
import webbroker3.accountopen.message.WEB3AdminAccOpenVoucherCancelCompleteRequest;
import webbroker3.accountopen.message.WEB3AdminAccOpenVoucherCancelCompleteResponse;
import webbroker3.accountopen.message.WEB3AdminAccOpenVoucherCancelConfirmRequest;
import webbroker3.accountopen.message.WEB3AdminAccOpenVoucherCancelConfirmResponse;
import webbroker3.accountopen.message.WEB3AdminAccOpenVoucherMakeCompleteRequest;
import webbroker3.accountopen.message.WEB3AdminAccOpenVoucherMakeCompleteResponse;
import webbroker3.accountopen.message.WEB3AdminAccOpenVoucherMakeConfirmRequest;
import webbroker3.accountopen.message.WEB3AdminAccOpenVoucherMakeConfirmResponse;
import webbroker3.accountopen.service.delegate.WEB3AdminAccOpenStateInquiryService;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.util.WEB3LogUtility;

/**
 * (�Ǘ��Ҍ����J�ݏ󋵖⍇���n���h��)<BR>
 * �Ǘ��Ҍ����J�ݏ󋵖⍇���n���h��<BR>
 */
public class WEB3AdminAccOpenStateInquiryHandler implements MessageHandler 
{
    
    /**
     * ���O�o�̓��[�e�B���e�B�B<BR>
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance
        (WEB3AdminAccOpenStateInquiryHandler.class);

    /**
     * @@roseuid 41B45E7401B5
     */
    public WEB3AdminAccOpenStateInquiryHandler() 
    {
     
    }
    
    /**
     * (���͉�ʕ\��)<BR>
     * �����J�ݏ󋵖⍇�����͉�ʕ\���������s���B <BR>
     * <BR>
     * �Ǘ��Ҍ����J�ݏ󋵖⍇���T�[�r�X���擾���Aexecute()���\�b�h���R�[������B<BR>
     * @@param l_request - �Ǘ��Ҍ����J�ݏ󋵖⍇�����̓��N�G�X�g�f�[�^�I�u�W�F�N�g
     * 
     * 
     * @@return webbroker3.accountopen.message.WEB3AdminAccOpenStateInquiryInputResponse
     * @@throws WEB3BaseException
     * @@roseuid 41943C320215
     */
    public WEB3AdminAccOpenStateInquiryInputResponse inputScreenDisplay(WEB3AdminAccOpenStateInquiryInputRequest l_request) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " inputScreenDisplay(WEB3AdminAccOpenStateInquiryInputRequest)";
        log.entering(STR_METHOD_NAME);
        
        WEB3AdminAccOpenStateInquiryInputResponse l_response = null;
        WEB3AdminAccOpenStateInquiryService l_service = null;
        
        try
        {
            l_service = (WEB3AdminAccOpenStateInquiryService)Services.getService(WEB3AdminAccOpenStateInquiryService.class);//Exception
        }
        // �T�[�r�X�ŗ�O�����������ꍇ�́A�G���[�������X�|���X���b�Z�[�W�ɐݒ肷��
        catch (Exception l_ex)
        {
            l_response = (WEB3AdminAccOpenStateInquiryInputResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;  
            log.error(
                l_request, 
                " �Ǘ��Ҍ����J�ݏ󋵖⍇���T�[�r�X���擾�Ɏ��s���܂����B",
                l_response.errorInfo, 
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        
        //�Ǘ��Ҍ����J�ݏ󋵖⍇���T�[�r�X.execute()���\�b�h���R�[������
        try
        {
            l_response = (WEB3AdminAccOpenStateInquiryInputResponse)l_service.execute(l_request);//WEB3BaseException
        }
        catch (WEB3BaseException l_ex)
        {
            l_response = (WEB3AdminAccOpenStateInquiryInputResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            l_response.errorMessage = l_ex.getErrorMessage();
            log.error(l_request, " �����J�ݏ󋵖⍇�����͉�ʕ\�������Ɏ��s���܂����B", l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        
        log.exiting(STR_METHOD_NAME);
        
        return l_response; 
    }
    
    /**
     * (�����J�ݏ󋵈ꗗ�\��)<BR>
     * �����J�ݏ󋵈ꗗ�\���������s���B<BR>
     * <BR>
     * �Ǘ��Ҍ����J�ݏ󋵖⍇���T�[�r�X���擾���Aexecute()���\�b�h���R�[������B<BR>
     * @@param l_request - �Ǘ��Ҍ����J�ݏ󋵖⍇���ꗗ���N�G�X�g�f�[�^�I�u�W�F�N�g
     * @@return webbroker3.accountopen.message.WEB3AdminAccOpenStateInquiryListResponse
     * @@roseuid 41943C320217
     */
    public WEB3AdminAccOpenStateInquiryListResponse accOpenStatusListDisplay(WEB3AdminAccOpenStateInquiryListRequest l_request) 
    {
        final String STR_METHOD_NAME = " accOpenStatusListDisplay(WEB3AdminAccOpenStateInquiryListRequest)";
        log.entering(STR_METHOD_NAME);
        
        WEB3AdminAccOpenStateInquiryListResponse l_response = null;
        WEB3AdminAccOpenStateInquiryService l_service = null;
        
        try
        {
            l_service = (WEB3AdminAccOpenStateInquiryService)Services.getService(WEB3AdminAccOpenStateInquiryService.class);//Exception
        }
        // �T�[�r�X�ŗ�O�����������ꍇ�́A�G���[�������X�|���X���b�Z�[�W�ɐݒ肷��
        catch (Exception l_ex)
        {
            l_response = (WEB3AdminAccOpenStateInquiryListResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;    
            log.error(
                l_request, 
                " �Ǘ��Ҍ����J�ݏ󋵖⍇���T�[�r�X���擾�Ɏ��s���܂����B",
                l_response.errorInfo, 
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        
        //�Ǘ��Ҍ����J�ݏ󋵖⍇���T�[�r�X.execute()���\�b�h���R�[������
        try
        {
            l_response = (WEB3AdminAccOpenStateInquiryListResponse)l_service.execute(l_request);//WEB3BaseException
        }
        catch (WEB3BaseException l_ex)
        {
            l_response = (WEB3AdminAccOpenStateInquiryListResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            l_response.errorMessage = l_ex.getErrorMessage();
            log.error(l_request, " �����J�ݏ󋵈ꗗ�\�������Ɏ��s���܂����B", l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        
        log.exiting(STR_METHOD_NAME);
        
        return l_response; 
    }
    
    /**
     * (�����J�ݏ󋵏ڍו\��)<BR>
     * �����J�ݏ󋵏ڍו\���������s���B<BR>
     * <BR>
     * �Ǘ��Ҍ����J�ݏ󋵖⍇���T�[�r�X���擾���Aexecute()���\�b�h���R�[������B<BR>
     * @@param l_request - �Ǘ��Ҍ����J�ݏ󋵖⍇���ڍ׃��N�G�X�g�f�[�^�I�u�W�F�N�g
     * @@return webbroker3.accountopen.message.WEB3AdminAccOpenStateInquiryDetailResponse
     * @@roseuid 41943C320225
     */
    public WEB3AdminAccOpenStateInquiryDetailResponse accOpenStatusDetailDisplay(WEB3AdminAccOpenStateInquiryDetailRequest l_request) 
    {
        final String STR_METHOD_NAME = " accOpenStatusDetailDisplay(WEB3AdminAccOpenStateInquiryDetailRequest)";
        log.entering(STR_METHOD_NAME);
        
        WEB3AdminAccOpenStateInquiryDetailResponse l_response = null;
        WEB3AdminAccOpenStateInquiryService l_service = null;
        
        try
        {
            l_service = (WEB3AdminAccOpenStateInquiryService)Services.getService(WEB3AdminAccOpenStateInquiryService.class);//Exception
        }
        // �T�[�r�X�ŗ�O�����������ꍇ�́A�G���[�������X�|���X���b�Z�[�W�ɐݒ肷��
        catch (Exception l_ex)
        {
            l_response = (WEB3AdminAccOpenStateInquiryDetailResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;    
            log.error(
                l_request, 
                " �Ǘ��Ҍ����J�ݏ󋵖⍇���T�[�r�X���擾�Ɏ��s���܂����B",
                l_response.errorInfo, 
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        
        //�Ǘ��Ҍ����J�ݏ󋵖⍇���T�[�r�X.execute()���\�b�h���R�[������
        try
        {
            l_response = (WEB3AdminAccOpenStateInquiryDetailResponse)l_service.execute(l_request);//WEB3BaseException
        }
        catch (WEB3BaseException l_ex)
        {
            l_response = (WEB3AdminAccOpenStateInquiryDetailResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            l_response.errorMessage = l_ex.getErrorMessage();
            log.error(l_request, " �����J�ݏ󋵏ڍו\�������Ɏ��s���܂����B", l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        
        log.exiting(STR_METHOD_NAME);
        
        return l_response; 
    }
    
    /**
     * (�\���X�V�m�F)<BR>
     * �����J�ݐ\���X�V�m�F�������s���B<BR>
     * <BR>
     * �Ǘ��Ҍ����J�ݏ󋵖⍇���T�[�r�X���擾���Aexecute()���\�b�h���R�[������B<BR>
     * @@param l_request - �Ǘ��Ҍ����J�ݐ\���X�V�m�F���N�G�X�g�f�[�^�I�u�W�F�N�g
     * @@return webbroker3.accountopen.message.WEB3AdminAccOpenApplyUpdateConfirmResponse
     * @@roseuid 41943C320227
     */
    public WEB3AdminAccOpenApplyUpdateConfirmResponse registUpdatedConfirm(WEB3AdminAccOpenApplyUpdateConfirmRequest l_request) 
    {
        final String STR_METHOD_NAME = " registUpdatedConfirm(WEB3AdminAccOpenApplyUpdateConfirmRequest)";
        log.entering(STR_METHOD_NAME);
        
        WEB3AdminAccOpenApplyUpdateConfirmResponse l_response = null;
        WEB3AdminAccOpenStateInquiryService l_service = null;
        
        try
        {
            l_service = (WEB3AdminAccOpenStateInquiryService)Services.getService(WEB3AdminAccOpenStateInquiryService.class);//Exception
        }
        // �T�[�r�X�ŗ�O�����������ꍇ�́A�G���[�������X�|���X���b�Z�[�W�ɐݒ肷��
        catch (Exception l_ex)
        {
            l_response = (WEB3AdminAccOpenApplyUpdateConfirmResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;    
            log.error(
                l_request, 
                " �Ǘ��Ҍ����J�ݏ󋵖⍇���T�[�r�X���擾�Ɏ��s���܂����B",
                l_response.errorInfo, 
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        
        //�Ǘ��Ҍ����J�ݏ󋵖⍇���T�[�r�X.execute()���\�b�h���R�[������
        try
        {
            l_response = (WEB3AdminAccOpenApplyUpdateConfirmResponse)l_service.execute(l_request);//WEB3BaseException
        }
        catch (WEB3BaseException l_ex)
        {
            l_response = (WEB3AdminAccOpenApplyUpdateConfirmResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            l_response.errorMessage = l_ex.getErrorMessage();
            log.error(l_request, " �����J�ݐ\���X�V�m�F�����Ɏ��s���܂����B", l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        
        log.exiting(STR_METHOD_NAME);
        
        return l_response; 
    }
    
    /**
     * (�\���X�V����)<BR>
     * �����J�ݐ\���X�V�����������s���B<BR>
     * <BR>
     * �Ǘ��Ҍ����J�ݏ󋵖⍇���T�[�r�X���擾���Aexecute()���\�b�h���R�[������B<BR>
     * @@param l_request - �Ǘ��Ҍ����J�ݐ\���X�V�������N�G�X�g�f�[�^�I�u�W�F�N�g
     * @@return webbroker3.accountopen.message.WEB3AdminAccOpenApplyUpdateCompleteResponse
     * @@roseuid 41943C320229
     */
    public WEB3AdminAccOpenApplyUpdateCompleteResponse registUpdatedComplete(WEB3AdminAccOpenApplyUpdateCompleteRequest l_request) 
    {
        final String STR_METHOD_NAME = " registUpdatedComplete(WEB3AdminAccOpenApplyUpdateCompleteRequest)";
        log.entering(STR_METHOD_NAME);
        
        WEB3AdminAccOpenApplyUpdateCompleteResponse l_response = null;
        WEB3AdminAccOpenStateInquiryService l_service = null;
        
        try
        {
            l_service = (WEB3AdminAccOpenStateInquiryService)Services.getService(WEB3AdminAccOpenStateInquiryService.class);//Exception
        }
        // �T�[�r�X�ŗ�O�����������ꍇ�́A�G���[�������X�|���X���b�Z�[�W�ɐݒ肷��
        catch (Exception l_ex)
        {
            l_response = (WEB3AdminAccOpenApplyUpdateCompleteResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;    
            log.error(
                l_request, 
                " �Ǘ��Ҍ����J�ݏ󋵖⍇���T�[�r�X���擾�Ɏ��s���܂����B",
                l_response.errorInfo, 
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        
        //�Ǘ��Ҍ����J�ݏ󋵖⍇���T�[�r�X.execute()���\�b�h���R�[������
        try
        {
            l_response = (WEB3AdminAccOpenApplyUpdateCompleteResponse)l_service.execute(l_request);//WEB3BaseException
        }
        catch (WEB3BaseException l_ex)
        {
            l_response = (WEB3AdminAccOpenApplyUpdateCompleteResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            l_response.errorMessage = l_ex.getErrorMessage();
            log.error(l_request, " �����J�ݐ\���X�V���������Ɏ��s���܂����B", l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        
        log.exiting(STR_METHOD_NAME);
        
        return l_response; 
    }
    
    /**
     * (�`�[�쐬�m�F)<BR>
     * �����J�ݓ`�[�쐬�m�F�������s���B<BR>
     * <BR>
     * �Ǘ��Ҍ����J�ݏ󋵖⍇���T�[�r�X���擾���Aexecute()���\�b�h���R�[������B<BR>
     * @@param l_request - �Ǘ��Ҍ����J�ݓ`�[�쐬�m�F���N�G�X�g�f�[�^�I�u�W�F�N�g
     * @@return webbroker3.accountopen.message.WEB3AdminAccOpenVoucherMakeConfirmResponse
     * @@roseuid 41943C32022B
     */
    public WEB3AdminAccOpenVoucherMakeConfirmResponse voucherCreatedConfirm(WEB3AdminAccOpenVoucherMakeConfirmRequest l_request) 
    {
        final String STR_METHOD_NAME = " voucherCreatedConfirm(WEB3AdminAccOpenVoucherMakeConfirmRequest)";
        log.entering(STR_METHOD_NAME);
        
        WEB3AdminAccOpenVoucherMakeConfirmResponse l_response = null;
        WEB3AdminAccOpenStateInquiryService l_service = null;
        
        try
        {
            l_service = (WEB3AdminAccOpenStateInquiryService)Services.getService(WEB3AdminAccOpenStateInquiryService.class);//Exception
        }
        // �T�[�r�X�ŗ�O�����������ꍇ�́A�G���[�������X�|���X���b�Z�[�W�ɐݒ肷��
        catch (Exception l_ex)
        {
            l_response = (WEB3AdminAccOpenVoucherMakeConfirmResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;    
            log.error(
                l_request, 
                " �Ǘ��Ҍ����J�ݏ󋵖⍇���T�[�r�X���擾�Ɏ��s���܂����B",
                l_response.errorInfo, 
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        
        //�Ǘ��Ҍ����J�ݏ󋵖⍇���T�[�r�X.execute()���\�b�h���R�[������
        try
        {
            l_response = (WEB3AdminAccOpenVoucherMakeConfirmResponse)l_service.execute(l_request);//WEB3BaseException
        }
        catch (WEB3BaseException l_ex)
        {
            l_response = (WEB3AdminAccOpenVoucherMakeConfirmResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            l_response.errorMessage = l_ex.getErrorMessage();
            log.error(l_request, " �����J�ݓ`�[�쐬�m�F�����Ɏ��s���܂����B", l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        
        log.exiting(STR_METHOD_NAME);
        
        return l_response; 
    }
    
    /**
     * (�`�[�쐬����)<BR>
     * �����J�ݓ`�[�쐬�����������s���B<BR>
     * <BR>
     * �Ǘ��Ҍ����J�ݏ󋵖⍇���T�[�r�X���擾���Aexecute()���\�b�h���R�[������B<BR>
     * @@param l_request - �Ǘ��Ҍ����J�ݓ`�[�쐬�������N�G�X�g�f�[�^�I�u�W�F�N�g
     * @@return webbroker3.accountopen.message.WEB3AdminAccOpenVoucherMakeCompleteResponse
     * @@roseuid 41943C320235
     */
    public WEB3AdminAccOpenVoucherMakeCompleteResponse voucherCreatedComplete(WEB3AdminAccOpenVoucherMakeCompleteRequest l_request) 
    {
        final String STR_METHOD_NAME = " voucherCreatedComplete(WEB3AdminAccOpenVoucherMakeCompleteRequest)";
        log.entering(STR_METHOD_NAME);
        
        WEB3AdminAccOpenVoucherMakeCompleteResponse l_response = null;
        WEB3AdminAccOpenStateInquiryService l_service = null;
        
        try
        {
            l_service = (WEB3AdminAccOpenStateInquiryService)Services.getService(WEB3AdminAccOpenStateInquiryService.class);//Exception
        }
        // �T�[�r�X�ŗ�O�����������ꍇ�́A�G���[�������X�|���X���b�Z�[�W�ɐݒ肷��
        catch (Exception l_ex)
        {
            l_response = (WEB3AdminAccOpenVoucherMakeCompleteResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;    
            log.error(
                l_request, 
                " �Ǘ��Ҍ����J�ݏ󋵖⍇���T�[�r�X���擾�Ɏ��s���܂����B",
                l_response.errorInfo, 
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        
        //�Ǘ��Ҍ����J�ݏ󋵖⍇���T�[�r�X.execute()���\�b�h���R�[������
        try
        {
            l_response = (WEB3AdminAccOpenVoucherMakeCompleteResponse)l_service.execute(l_request);//WEB3BaseException
        }
        catch (WEB3BaseException l_ex)
        {
            l_response = (WEB3AdminAccOpenVoucherMakeCompleteResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            l_response.errorMessage = l_ex.getErrorMessage();
            log.error(l_request, " �����J�ݓ`�[�쐬���������Ɏ��s���܂����B", l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        
        log.exiting(STR_METHOD_NAME);
        
        return l_response; 
    }
    
    /**
     * (�`�[����m�F)<BR>
     * �����J�ݓ`�[����m�F�������s���B<BR>
     * <BR>
     * �Ǘ��Ҍ����J�ݏ󋵖⍇���T�[�r�X���擾���Aexecute()���\�b�h���R�[������B<BR>
     * @@param l_request - �Ǘ��Ҍ����J�ݓ`�[����m�F���N�G�X�g�f�[�^�I�u�W�F�N�g
     * @@return webbroker3.accountopen.message.WEB3AdminAccOpenVoucherCancelConfirmResponse
     * @@roseuid 41943C320237
     */
    public WEB3AdminAccOpenVoucherCancelConfirmResponse voucherCanceledConfirm(WEB3AdminAccOpenVoucherCancelConfirmRequest l_request) 
    {
        final String STR_METHOD_NAME = " voucherCanceledConfirm(WEB3AdminAccOpenVoucherCancelConfirmRequest)";
        log.entering(STR_METHOD_NAME);
        
        WEB3AdminAccOpenVoucherCancelConfirmResponse l_response = null;
        WEB3AdminAccOpenStateInquiryService l_service = null;
        
        try
        {
            l_service = (WEB3AdminAccOpenStateInquiryService)Services.getService(WEB3AdminAccOpenStateInquiryService.class);//Exception
        }
        // �T�[�r�X�ŗ�O�����������ꍇ�́A�G���[�������X�|���X���b�Z�[�W�ɐݒ肷��
        catch (Exception l_ex)
        {
            l_response = (WEB3AdminAccOpenVoucherCancelConfirmResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;    
            log.error(
                l_request, 
                " �Ǘ��Ҍ����J�ݏ󋵖⍇���T�[�r�X���擾�Ɏ��s���܂����B",
                l_response.errorInfo, 
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        
        //�Ǘ��Ҍ����J�ݏ󋵖⍇���T�[�r�X.execute()���\�b�h���R�[������
        try
        {
            l_response = (WEB3AdminAccOpenVoucherCancelConfirmResponse)l_service.execute(l_request);//WEB3BaseException
        }
        catch (WEB3BaseException l_ex)
        {
            l_response = (WEB3AdminAccOpenVoucherCancelConfirmResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            l_response.errorMessage = l_ex.getErrorMessage();
            log.error(l_request, " �����J�ݓ`�[����m�F�����Ɏ��s���܂����B", l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        
        log.exiting(STR_METHOD_NAME);
        
        return l_response; 
    }
    
    /**
     * (�`�[�������)<BR>
     * �����J�ݓ`�[��������������s���B<BR>
     * <BR>
     * �Ǘ��Ҍ����J�ݏ󋵖⍇���T�[�r�X���擾���Aexecute()���\�b�h���R�[������B<BR>
     * @@param l_request - �Ǘ��Ҍ����J�ݓ`�[����������N�G�X�g�f�[�^�I�u�W�F�N�g
     * @@return webbroker3.accountopen.message.WEB3AdminAccOpenVoucherCancelCompleteResponse
     * @@roseuid 41943C320239
     */
    public WEB3AdminAccOpenVoucherCancelCompleteResponse voucherCanceledComplete(WEB3AdminAccOpenVoucherCancelCompleteRequest l_request) 
    {
        final String STR_METHOD_NAME = " voucherCanceledComplete(WEB3AdminAccOpenVoucherCancelCompleteRequest)";
        log.entering(STR_METHOD_NAME);
        
        WEB3AdminAccOpenVoucherCancelCompleteResponse l_response = null;
        WEB3AdminAccOpenStateInquiryService l_service = null;
        
        try
        {
            l_service = (WEB3AdminAccOpenStateInquiryService)Services.getService(WEB3AdminAccOpenStateInquiryService.class);//Exception
        }
        // �T�[�r�X�ŗ�O�����������ꍇ�́A�G���[�������X�|���X���b�Z�[�W�ɐݒ肷��
        catch (Exception l_ex)
        {
            l_response = (WEB3AdminAccOpenVoucherCancelCompleteResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;    
            log.error(
                l_request, 
                " �Ǘ��Ҍ����J�ݏ󋵖⍇���T�[�r�X���擾�Ɏ��s���܂����B",
                l_response.errorInfo, 
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        
        //�Ǘ��Ҍ����J�ݏ󋵖⍇���T�[�r�X.execute()���\�b�h���R�[������
        try
        {
            l_response = (WEB3AdminAccOpenVoucherCancelCompleteResponse)l_service.execute(l_request);//WEB3BaseException
        }
        catch (WEB3BaseException l_ex)
        {
            l_response = (WEB3AdminAccOpenVoucherCancelCompleteResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            l_response.errorMessage = l_ex.getErrorMessage();
            log.error(l_request, " �����J�ݓ`�[������������Ɏ��s���܂����B", l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        
        log.exiting(STR_METHOD_NAME);
        
        return l_response; 
    }

    /**
     * (�ؑ�)<BR>
     * �����J�ݏ󋵖⍇���ؑ֏������s���B<BR>
     * <BR>
     * �Ǘ��Ҍ����J�ݏ󋵖⍇���T�[�r�X���擾���Aexecute()���\�b�h���R�[������B<BR>
     * @@param l_request - (�Ǘ��Ҍ����J�ݐؑփ��N�G�X�g)<BR>
     * �Ǘ��Ҍ����J�ݐؑփ��N�G�X�g�f�[�^�I�u�W�F�N�g<BR>
     * @@return WEB3AccOpenChangeResponse
     */
    public WEB3AccOpenChangeResponse change(WEB3AccOpenChangeRequest l_request) 
    {
        final String STR_METHOD_NAME = "change(WEB3AccOpenChangeRequest)";
        log.entering(STR_METHOD_NAME);

        WEB3AccOpenChangeResponse l_response = null;
        WEB3AdminAccOpenStateInquiryService l_service = null;

        try
        {
            l_service =
                (WEB3AdminAccOpenStateInquiryService)Services.getService(
                    WEB3AdminAccOpenStateInquiryService.class);
        }
        // �T�[�r�X�ŗ�O�����������ꍇ�́A�G���[�������X�|���X���b�Z�[�W�ɐݒ肷��
        catch (Exception l_ex)
        {
            l_response = (WEB3AccOpenChangeResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;    
            log.error(
                l_request, 
                "�Ǘ��Ҍ����J�ݏ󋵖⍇���T�[�r�X���擾�Ɏ��s���܂����B",
                l_response.errorInfo, 
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

        //�Ǘ��Ҍ����J�ݏ󋵖⍇���T�[�r�X.execute()���\�b�h���R�[������
        try
        {
            l_response = (WEB3AccOpenChangeResponse)l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response = (WEB3AccOpenChangeResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            l_response.errorMessage = l_ex.getErrorMessage();
            log.error(l_request, "�����J�ݏ󋵖⍇���ؑ֏���", l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        catch (WEB3BaseRuntimeException l_ex)
        {
            l_response = (WEB3AccOpenChangeResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request,
                "�����J�ݏ󋵖⍇���ؑ֏����Ɏ��s���܂����B",
                l_response.errorInfo,
                l_ex);

            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

        log.exiting(STR_METHOD_NAME);
        return l_response; 
    }
}
@
