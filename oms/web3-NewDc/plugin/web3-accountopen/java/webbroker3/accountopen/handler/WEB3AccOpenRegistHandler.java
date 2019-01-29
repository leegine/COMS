head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.03.46.56;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	WEB3AccOpenRegistHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �����J�ݐ\���n���h��(WEB3AccOpenRegistHandler.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/12/15 �A�C��(���u) �V�K�쐬
*/

package webbroker3.accountopen.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.accountopen.message.WEB3AccOpenApplyCompleteRequest;
import webbroker3.accountopen.message.WEB3AccOpenApplyCompleteResponse;
import webbroker3.accountopen.message.WEB3AccOpenApplyConfirmRequest;
import webbroker3.accountopen.message.WEB3AccOpenApplyConfirmResponse;
import webbroker3.accountopen.message.WEB3AccOpenApplyInputRequest;
import webbroker3.accountopen.message.WEB3AccOpenApplyInputResponse;
import webbroker3.accountopen.service.delegate.WEB3AccOpenRegistService;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.util.WEB3LogUtility;

/**
 * (�����J�ݐ\���n���h��)<BR>
 * �����J�ݐ\���n���h��<BR>
 *   
 * @@author �A�C��
 * @@version 1.0
 */
public class WEB3AccOpenRegistHandler implements MessageHandler 
{

    /**
     * (���O�o�̓��[�e�B���e�B�B)<BR>
     */
    private static WEB3LogUtility log = 
        WEB3LogUtility.getInstance(WEB3AccOpenRegistHandler.class);
    
    /**
     * @@roseuid 41B45E750157
     */
    public WEB3AccOpenRegistHandler() 
    {
     
    }
    
    /**
     * (���͉�ʕ\��)<BR>
     * �����J�ݐ\�����͉�ʕ\���������s���B <BR>
     * <BR>
     * �����J�ݐ\���T�[�r�X���擾���Aexecute()���\�b�h���R�[������B<BR>
     * @@param l_request - �����J�ݐ\�����̓��N�G�X�g�f�[�^�I�u�W�F�N�g
     * 
     * 
     * @@return webbroker3.accountopen.message.WEB3AccOpenApplyInputResponse
     * @@throws WEB3BaseException
     * @@roseuid 419C90400293
     */
    public WEB3AccOpenApplyInputResponse inputScreenDisplay(WEB3AccOpenApplyInputRequest l_request) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " inputScreenDisplay(WEB3AccOpenApplyInputRequest )";    
        log.entering(STR_METHOD_NAME);
        
        WEB3AccOpenApplyInputResponse l_response = null;
        WEB3AccOpenRegistService l_service = null;      
        try
        {
            //get service
            l_service = 
                (WEB3AccOpenRegistService)Services.getService(
                    WEB3AccOpenRegistService.class);
        }
        catch (Exception l_ex)
        {
            
            l_response = (WEB3AccOpenApplyInputResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "�����J�ݐ\���T�[�r�X�̎擾�Ɏ��s���܂����B",
                l_response.errorInfo,
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;                  
        }

        try
        {
            //execute()���\�b�h���R�[������
            l_response = (WEB3AccOpenApplyInputResponse)l_service.execute(l_request);//WEB3BaseException
        }
        catch (WEB3BaseException l_ex)
        {
            l_response = (WEB3AccOpenApplyInputResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            l_response.errorMessage = l_ex.getErrorMessage();
            log.error(l_request, "�����J�ݐ\�����͉�ʕ\�������Ɏ��s���܂����B", l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

        log.exiting(STR_METHOD_NAME);
        return l_response;    
    }
    
    /**
     * (�\���m�F)<BR>
     * �����J�ݐ\���m�F�������s���B<BR>
     * <BR>
     * �����J�ݐ\���T�[�r�X���擾���Aexecute()���\�b�h���R�[������B<BR>
     * @@param l_request - �����J�ݐ\���m�F���N�G�X�g�f�[�^�I�u�W�F�N�g
     * @@return webbroker3.accountopen.message.WEB3AccOpenApplyConfirmResponse
     * @@roseuid 419C904002A3
     */
    public WEB3AccOpenApplyConfirmResponse registConfirm(WEB3AccOpenApplyConfirmRequest l_request) 
    {
        final String STR_METHOD_NAME = " registConfirm(WEB3AccOpenApplyConfirmRequest )";    
        log.entering(STR_METHOD_NAME);
        
        WEB3AccOpenApplyConfirmResponse l_response = null;
        WEB3AccOpenRegistService l_service = null;      
        try
        {
            //get service
            l_service = 
                (WEB3AccOpenRegistService)Services.getService(
                    WEB3AccOpenRegistService.class);
        }
        catch (Exception l_ex)
        {
            
            l_response = (WEB3AccOpenApplyConfirmResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "�����J�ݐ\���T�[�r�X�̎擾�Ɏ��s���܂����B",
                l_response.errorInfo,
                l_ex);
            return l_response;                  
        }

        try
        {
            //execute()���\�b�h���R�[������
            l_response = (WEB3AccOpenApplyConfirmResponse)l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response = (WEB3AccOpenApplyConfirmResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            l_response.errorMessage = l_ex.getErrorMessage();
            log.error(l_request, "�����J�ݐ\���m�F�����Ɏ��s���܂����B", l_ex);
            return l_response;
        }

        log.exiting(STR_METHOD_NAME);
        return l_response;    
    }
    
    /**
     * (�\������)<BR>
     * �����J�ݐ\�������������s���B<BR>
     * <BR>
     * �����J�ݐ\���T�[�r�X���擾���Aexecute()���\�b�h���R�[������B<BR>
     * @@param l_request - �����J�ݐ\���������N�G�X�g�f�[�^�I�u�W�F�N�g
     * @@return webbroker3.accountopen.message.WEB3AccOpenApplyCompleteResponse
     * @@roseuid 419C904002A5
     */
    public WEB3AccOpenApplyCompleteResponse registComplete(WEB3AccOpenApplyCompleteRequest l_request) 
    {
        final String STR_METHOD_NAME = " registComplete(WEB3AccOpenApplyCompleteRequest )";    
        log.entering(STR_METHOD_NAME);
        
        WEB3AccOpenApplyCompleteResponse l_response = null;
        WEB3AccOpenRegistService l_service = null;      
        try
        {
            //get service
            l_service = 
                (WEB3AccOpenRegistService)Services.getService(
                    WEB3AccOpenRegistService.class);
        }
        catch (Exception l_ex)
        {
            
            l_response = (WEB3AccOpenApplyCompleteResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "�����J�ݐ\���T�[�r�X�̎擾�Ɏ��s���܂����B",
                l_response.errorInfo,
                l_ex);
            return l_response;                  
        }

        try
        {
            //execute()���\�b�h���R�[������
            l_response = (WEB3AccOpenApplyCompleteResponse)l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response = (WEB3AccOpenApplyCompleteResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            l_response.errorMessage = l_ex.getErrorMessage();
            log.error(l_request, "�����J�ݐ\�����������Ɏ��s���܂����B", l_ex);
            return l_response;
        }

        log.exiting(STR_METHOD_NAME);
        return l_response;    
    }
}
@
