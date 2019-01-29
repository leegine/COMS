head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.10.02.12.15;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8644d782f8a12fc;
filename	WEB3AdminAccInfoCampaignIndiviChangeHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �ʌڋq�w��ύX�n���h��(WEB3AdminAccInfoCampaignIndiviChangeHandler.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/2/1  ꎉ�(���u) �V�K�쐬
Revision History : 2007/2/1  ���f��No.168
*/
package webbroker3.accountinfo.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.accountinfo.message.WEB3AdminAccInfoCampaignIndiviCompleteRequest;
import webbroker3.accountinfo.message.WEB3AdminAccInfoCampaignIndiviCompleteResponse;
import webbroker3.accountinfo.message.WEB3AdminAccInfoCampaignIndiviConfirmRequest;
import webbroker3.accountinfo.message.WEB3AdminAccInfoCampaignIndiviConfirmResponse;
import webbroker3.accountinfo.message.WEB3AdminAccInfoCampaignIndiviInputRequest;
import webbroker3.accountinfo.message.WEB3AdminAccInfoCampaignIndiviInputResponse;
import webbroker3.accountinfo.service.delegate.WEB3AdminAccInfoCampaignIndiviChangeService;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.util.WEB3LogUtility;

/**
 * (�ʌڋq�w��ύX�n���h��)<BR>
 * �ʌڋq�w��ύX�n���h��<BR>
 * <BR>
 * @@author ꎉ�
 * @@version 1.0
 */
public class WEB3AdminAccInfoCampaignIndiviChangeHandler implements MessageHandler 
{
    /**
     * ���O�o�̓��[�e�B���e�B�B<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminAccInfoCampaignIndiviChangeHandler.class);
    
    /**
     * @@roseuid 45C08B510138
     */
    public WEB3AdminAccInfoCampaignIndiviChangeHandler() 
    {
     
    }
    
    /**
     * (���͉�ʕ\��)<BR>
     * �萔���L�����y�[���ڋq�ʓ��͉�ʕ\�����������{����B<BR>
     * <BR>
     * �ʌڋq�w��ύX�T�[�r�X���擾���Aexecute()���\�b�h���R�[������B<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * �ʌڋq�w����̓��N�G�X�g�f�[�^�I�u�W�F�N�g<BR>
     * @@return WEB3AdminAccInfoCampaignIndiviInputResponse
     * @@roseuid 459DAF890101
     */
    public WEB3AdminAccInfoCampaignIndiviInputResponse inputScreenDisplay(
        WEB3AdminAccInfoCampaignIndiviInputRequest l_request) 
    {
        final String STR_METHOD_NAME = 
            " inputScreenDisplay(WEB3AdminAccInfoCampaignIndiviInputRequest l_request)";
        log.entering(STR_METHOD_NAME);
        
        WEB3AdminAccInfoCampaignIndiviInputResponse l_response = null;
        WEB3AdminAccInfoCampaignIndiviChangeService l_service = null;
        
        // �ʌڋq�w��ύX�T�[�r�X���擾
        try
        {          
            l_service=(WEB3AdminAccInfoCampaignIndiviChangeService)Services.getService
                (WEB3AdminAccInfoCampaignIndiviChangeService.class);            
        }
        // �T�[�r�X�ŗ�O�����������ꍇ�́A�G���[�������X�|���X���b�Z�[�W�ɐݒ肷��
        catch(Exception l_ex)
        {
            l_response = (WEB3AdminAccInfoCampaignIndiviInputResponse)
                l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "�ʌڋq�w��ύX�T�[�r�X�̎擾�Ɏ��s���܂����B",
                l_response.errorInfo,l_ex);
            return l_response; 
        }
        
        // �ʌڋq�w��ύX�T�[�r�X���擾���Aexecute()���\�b�h���R�[������ 
        try
        {
            l_response = (WEB3AdminAccInfoCampaignIndiviInputResponse)
                l_service.execute(l_request);    
        }
        catch (WEB3BaseException l_ex)
        {
            l_response = (WEB3AdminAccInfoCampaignIndiviInputResponse)
                l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request,
                "�ʌڋq�w��ύX�T�[�r�X�Ɏ��s���܂����B",
                l_ex);
            return l_response;                    
        }
        log.exiting(STR_METHOD_NAME);
        return l_response; 
    }
    
    /**
     * (�ʌڋq�w��m�F)<BR>
     * �ʌڋq�w��m�F�������s���B <BR>
     * <BR>
     * �ʌڋq�w��ύX�T�[�r�X���擾���Aexecute()���\�b�h���R�[������B <BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * �ʌڋq�w��m�F���N�G�X�g�f�[�^�I�u�W�F�N�g<BR>
     * 
     * @@return WEB3AdminAccInfoCampaignIndiviConfirmResponse
     * @@roseuid 459DB143016E
     */
    public WEB3AdminAccInfoCampaignIndiviConfirmResponse IndiviChangeConfirm(
        WEB3AdminAccInfoCampaignIndiviConfirmRequest l_request) 
    {
        final String STR_METHOD_NAME = 
            " IndiviChangeConfirm(WEB3AdminAccInfoCampaignIndiviConfirmRequest l_request)";
        log.entering(STR_METHOD_NAME);
        
        WEB3AdminAccInfoCampaignIndiviConfirmResponse l_response = null;
        WEB3AdminAccInfoCampaignIndiviChangeService l_service = null;
        
        // �ʌڋq�w��ύX�T�[�r�X���擾
        try
        {           
            l_service=(WEB3AdminAccInfoCampaignIndiviChangeService)Services.getService
                (WEB3AdminAccInfoCampaignIndiviChangeService.class);            
        }
        // �T�[�r�X�ŗ�O�����������ꍇ�́A�G���[�������X�|���X���b�Z�[�W�ɐݒ肷��
        catch(Exception l_ex)
        {
            l_response = (WEB3AdminAccInfoCampaignIndiviConfirmResponse)
                l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "�ʌڋq�w��ύX�T�[�r�X�̎擾�Ɏ��s���܂����B",
                l_response.errorInfo,l_ex);
            return l_response; 
        }
        
        // �ʌڋq�w��ύX�T�[�r�X���擾���Aexecute()���\�b�h���R�[������ 
        try
        {
            l_response = (WEB3AdminAccInfoCampaignIndiviConfirmResponse)
                l_service.execute(l_request);     
        }
        catch (WEB3BaseException l_ex)
        {
            l_response = (WEB3AdminAccInfoCampaignIndiviConfirmResponse)
                l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request,
                "�ʌڋq�w��ύX�T�[�r�X�Ɏ��s���܂����B",
                l_ex);
            return l_response;                    
        }
        log.exiting(STR_METHOD_NAME);
        return l_response; 
    }
    
    /**
     * (�ʌڋq�w�芮��)<BR>
     * �ʌڋq�w�芮���������s���B <BR>
     * <BR>
     * �ʌڋq�w��ύX�T�[�r�X���擾���Aexecute()���\�b�h���R�[������B <BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * �ʌڋq�w�芮�����N�G�X�g�f�[�^�I�u�W�F�N�g<BR>
     * @@return WEB3AdminAccInfoCampaignIndiviCompleteResponse
     * @@roseuid 459DB1430170
     */
    public WEB3AdminAccInfoCampaignIndiviCompleteResponse IndiviChangeComplete(
        WEB3AdminAccInfoCampaignIndiviCompleteRequest l_request) 
    {
        final String STR_METHOD_NAME = 
            " IndiviChangeComplete(WEB3AdminAccInfoCampaignIndiviCompleteRequest l_request)";
        log.entering(STR_METHOD_NAME);
        
        WEB3AdminAccInfoCampaignIndiviCompleteResponse l_response = null;
        WEB3AdminAccInfoCampaignIndiviChangeService l_service = null;
        
        // �ʌڋq�w��ύX�T�[�r�X���擾
        try
        {           
            l_service=(WEB3AdminAccInfoCampaignIndiviChangeService)Services.getService
                (WEB3AdminAccInfoCampaignIndiviChangeService.class);            
        }
        // �T�[�r�X�ŗ�O�����������ꍇ�́A�G���[�������X�|���X���b�Z�[�W�ɐݒ肷��
        catch(Exception l_ex)
        {
            l_response = (WEB3AdminAccInfoCampaignIndiviCompleteResponse)
                l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "�ʌڋq�w��ύX�T�[�r�X�̎擾�Ɏ��s���܂����B",
                l_response.errorInfo,l_ex);
            return l_response; 
        }
        
        // �ʌڋq�w��ύX�T�[�r�X���擾���Aexecute()���\�b�h���R�[������ 
        try
        {
            l_response = (WEB3AdminAccInfoCampaignIndiviCompleteResponse)
                l_service.execute(l_request);     
        }
        catch (WEB3BaseException l_ex)
        {
            l_response = (WEB3AdminAccInfoCampaignIndiviCompleteResponse)
                l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request,
                "�ʌڋq�w��ύX�T�[�r�X�Ɏ��s���܂����B",
                l_ex);
            return l_response;                    
        }
        log.exiting(STR_METHOD_NAME);
        return l_response; 
    }
}
@
