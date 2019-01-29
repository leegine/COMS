head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.05.48.45;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6f44d7efa104045;
filename	WEB3AdminIpoLotResultUploadHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǘ���IPO���I���ʱ���۰������׃N���X(WEB3AdminIpoLotResultUploadHandler.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/08/24 ���C�g (���u) �V�K�쐬
*/

package webbroker3.ipo.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.ipo.message.WEB3AdminIPOLotResultUploadCancelRequest;
import webbroker3.ipo.message.WEB3AdminIPOLotResultUploadCancelResponse;
import webbroker3.ipo.message.WEB3AdminIPOLotResultUploadCompleteRequest;
import webbroker3.ipo.message.WEB3AdminIPOLotResultUploadCompleteResponse;
import webbroker3.ipo.message.WEB3AdminIPOLotResultUploadConfirmRequest;
import webbroker3.ipo.message.WEB3AdminIPOLotResultUploadConfirmResponse;
import webbroker3.ipo.message.WEB3AdminIPOLotResultUploadInputRequest;
import webbroker3.ipo.message.WEB3AdminIPOLotResultUploadInputResponse;
import webbroker3.ipo.service.delegate.WEB3AdminIpoLotResultUploadService;
import webbroker3.util.WEB3LogUtility;

/**
 * �Ǘ���IPO���I���ʱ���۰������׃N���X
 * 
 * @@author ���C�g
 * @@version 1.0
 */
public class WEB3AdminIpoLotResultUploadHandler implements MessageHandler 
{
    
    /**
     * ���O�o�̓��[�e�B���e�B�B
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminIpoLotResultUploadHandler.class);
    
    /**
     * @@roseuid 4112EEA7017C
     */
    public WEB3AdminIpoLotResultUploadHandler() 
    {
     
    }
    
    /**
     * IPO�Ǘ���IPO���I���ʃA�b�v���[�h��ʕ\���������s���B<BR>
     * <BR>
     * �Ǘ���IPO���I���ʃA�b�v���[�h�T�[�r�X���擾���Aexecute()���\�b�h���R�[������B
     * @@param l_request - �Ǘ���IPO���I���ʱ���۰�ޓ���ظ��ăf�[�^�I�u�W�F�N�g
     * @@return webbroker3.ipo.message.WEB3AdminIpoLotResultUploadInputResponse
     * @@roseuid 40E1478C0131
     */
    public WEB3AdminIPOLotResultUploadInputResponse lotResultUploadScreenIndication(WEB3AdminIPOLotResultUploadInputRequest l_request) 
    {

        final String STR_METHOD_NAME = " lotResultUploadScreenIndication(WEB3AdminIpoLotResultUploadInputRequest)";    
        log.entering(STR_METHOD_NAME);
        
        WEB3AdminIPOLotResultUploadInputResponse l_response = null;
        WEB3AdminIpoLotResultUploadService l_service = null;
        
        try
        {
            l_service =
                (WEB3AdminIpoLotResultUploadService)Services.getService(
                    WEB3AdminIpoLotResultUploadService.class);
        }
        catch (Exception l_ex)
        {
            
            l_response = (WEB3AdminIPOLotResultUploadInputResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "�Ǘ���IPO���I���ʱ���۰�޻��޽�T�[�r�X�̎擾�Ɏ��s���܂����B",
                l_response.errorInfo,
                l_ex);
            return l_response;   
               
        }
        
        //�Ǘ���IPO���I���ʱ���۰�޻��޽�T�[�r�X.execute()���\�b�h���R�[������
        try
        {
            
            l_response = (WEB3AdminIPOLotResultUploadInputResponse)l_service.execute(l_request);
            
        }
        catch (WEB3BaseException l_ex)
        {
            l_response = (WEB3AdminIPOLotResultUploadInputResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            l_response.errorMessage = l_ex.getErrorMessage();
            log.error(l_request, "�Ǘ���IPO���I���ʱ���۰�޻��޽�Ɏ��s���܂����B", l_ex);
            return l_response;
        }
        log.exiting(STR_METHOD_NAME);
 
        //���X�|���X�I�u�W�F�N�g��ԋp����B
        return l_response;

    }
    
    /**
     * (���I���ʃA�b�v���[�h�t�@@�C���m�F)<BR>
     * IPO�Ǘ��ҁE���I���ʃA�b�v���[�h�m�F�������s���B<BR>
     * <BR>
     * �Ǘ���IPO���I���ʃA�b�v���[�h�T�[�r�X���擾���Aexecute()���\�b�h���R�[������B
     * @@param l_request - �Ǘ���IPO���I���ʱ���۰�ފm�Fظ��ăf�[�^�I�u�W�F�N�g
     * @@return webbroker3.ipo.message.WEB3AdminIpoLotResultUploadConfirmResponse
     * @@roseuid 40E1478C0141
     */
    public WEB3AdminIPOLotResultUploadConfirmResponse lotResultUploadFileConfirm(WEB3AdminIPOLotResultUploadConfirmRequest l_request) 
    {

        final String STR_METHOD_NAME = " lotResultUploadFileConfirm(WEB3AdminIpoLotResultUploadConfirmRequest)";    
        log.entering(STR_METHOD_NAME);
        
        WEB3AdminIPOLotResultUploadConfirmResponse l_response = null;
        WEB3AdminIpoLotResultUploadService l_service = null;
        
        try
        {
            l_service =
                (WEB3AdminIpoLotResultUploadService)Services.getService(
                    WEB3AdminIpoLotResultUploadService.class);
        }
        catch (Exception l_ex)
        {
            
            l_response = (WEB3AdminIPOLotResultUploadConfirmResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "�Ǘ���IPO���I���ʱ���۰�޻��޽�T�[�r�X�̎擾�Ɏ��s���܂����B",
                l_response.errorInfo,
                l_ex);
            return l_response;   
               
        }
        
        //�Ǘ���IPO���I���ʱ���۰�޻��޽�T�[�r�X.execute()���\�b�h���R�[������
        try
        {
            
            l_response = (WEB3AdminIPOLotResultUploadConfirmResponse)l_service.execute(l_request);
            
        }
        catch (WEB3BaseException l_ex)
        {
            l_response = (WEB3AdminIPOLotResultUploadConfirmResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            l_response.errorMessage = l_ex.getErrorMessage();
            log.error(l_request, "�Ǘ���IPO���I���ʱ���۰�޻��޽�Ɏ��s���܂����B", l_ex);
            return l_response;
        }
        log.exiting(STR_METHOD_NAME);
 
        //���X�|���X�I�u�W�F�N�g��ԋp����B
        return l_response;

    }
    
    /**
     * (���I���ʃA�b�v���[�h)<BR>
     * IPO�Ǘ��ҁE���I���ʃA�b�v���[�h�����������s���B<BR>
     * <BR>
     * �Ǘ���IPO���I���ʃA�b�v���[�h�T�[�r�X���擾���Aexecute()���\�b�h���R�[������B
     * @@param l_request - �Ǘ���IPO���I���ʱ���۰�ފ���ظ��ăf�[�^�I�u�W�F�N�g
     * @@return webbroker3.ipo.message.WEB3AdminIpoLotResultUploadCompleteResponse
     * @@roseuid 40E1478C0150
     */
    public WEB3AdminIPOLotResultUploadCompleteResponse lotResultUpload(WEB3AdminIPOLotResultUploadCompleteRequest l_request) 
    {

        final String STR_METHOD_NAME = " lotResultUpload(WEB3AdminIpoLotResultUploadCompleteRequest)";    
        log.entering(STR_METHOD_NAME);
        
        WEB3AdminIPOLotResultUploadCompleteResponse l_response = null;
        WEB3AdminIpoLotResultUploadService l_service = null;
        
        try
        {
            l_service =
                (WEB3AdminIpoLotResultUploadService)Services.getService(
                    WEB3AdminIpoLotResultUploadService.class);
        }
        catch (Exception l_ex)
        {
            
            l_response = (WEB3AdminIPOLotResultUploadCompleteResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "�Ǘ���IPO���I���ʱ���۰�޻��޽�T�[�r�X�̎擾�Ɏ��s���܂����B",
                l_response.errorInfo,
                l_ex);
            return l_response;   
               
        }
        
        //�Ǘ���IPO���I���ʱ���۰�޻��޽�T�[�r�X.execute()���\�b�h���R�[������
        try
        {
            
            l_response = (WEB3AdminIPOLotResultUploadCompleteResponse)l_service.execute(l_request);
            
        }
        catch (WEB3BaseException l_ex)
        {
            l_response = (WEB3AdminIPOLotResultUploadCompleteResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            l_response.errorMessage = l_ex.getErrorMessage();
            log.error(l_request, "�Ǘ���IPO���I���ʱ���۰�޻��޽�Ɏ��s���܂����B", l_ex);
            return l_response;
        }
        log.exiting(STR_METHOD_NAME);
 
        //���X�|���X�I�u�W�F�N�g��ԋp����B
        return l_response;

    }
    
    /**
     * (���I���ʃA�b�v���[�h���~)<BR>
     * IPO�Ǘ��ҁE���I���ʃA�b�v���[�h���~�������s���B<BR>
     * <BR>
     * �Ǘ���IPO���I���ʃA�b�v���[�h�T�[�r�X���擾���Aexecute()���\�b�h���R�[������B
     * @@param l_request - �Ǘ���IPO���I���ʱ���۰�ޒ��~ظ��ăf�[�^�I�u�W�F�N�g
     * @@return webbroker3.ipo.message.WEB3AdminIpoLotResultUploadCancelResponse
     * @@roseuid 40F77BF4023B
     */
    public WEB3AdminIPOLotResultUploadCancelResponse lotResultUploadCancel(WEB3AdminIPOLotResultUploadCancelRequest l_request) 
    {

        final String STR_METHOD_NAME = " lotResultUploadCancel(WEB3AdminIpoLotResultUploadCancelRequest)";    
        log.entering(STR_METHOD_NAME);
        
        WEB3AdminIPOLotResultUploadCancelResponse l_response = null;
        WEB3AdminIpoLotResultUploadService l_service = null;
        
        try
        {
            l_service =
                (WEB3AdminIpoLotResultUploadService)Services.getService(
                    WEB3AdminIpoLotResultUploadService.class);
        }
        catch (Exception l_ex)
        {
            
            l_response = (WEB3AdminIPOLotResultUploadCancelResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "�Ǘ���IPO���I���ʱ���۰�޻��޽�T�[�r�X�̎擾�Ɏ��s���܂����B",
                l_response.errorInfo,
                l_ex);
            return l_response;   
               
        }
        
        //�Ǘ���IPO���I���ʱ���۰�޻��޽�T�[�r�X.execute()���\�b�h���R�[������
        try
        {
            
            l_response = (WEB3AdminIPOLotResultUploadCancelResponse)l_service.execute(l_request);
            
        }
        catch (WEB3BaseException l_ex)
        {
            l_response = (WEB3AdminIPOLotResultUploadCancelResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            l_response.errorMessage = l_ex.getErrorMessage();
            log.error(l_request, "�Ǘ���IPO���I���ʱ���۰�޻��޽�Ɏ��s���܂����B", l_ex);
            return l_response;
        }
        log.exiting(STR_METHOD_NAME);
 
        //���X�|���X�I�u�W�F�N�g��ԋp����B
        return l_response;

    }
}
@
