head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.05.49.35;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6f44d7efa104045;
filename	WEB3AdminIpoLotHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǘ���IPO���I�����n���h��(WEB3AdminIpoLotHandler.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/12/20 �A���� (���u) �V�K�쐬
*/

package webbroker3.ipo.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.ipo.message.WEB3AdminIPOLotCompleteRequest;
import webbroker3.ipo.message.WEB3AdminIPOLotCompleteResponse;
import webbroker3.ipo.message.WEB3AdminIPOLotConfirmRequest;
import webbroker3.ipo.message.WEB3AdminIPOLotConfirmResponse;
import webbroker3.ipo.message.WEB3AdminIPOLotInputRequest;
import webbroker3.ipo.message.WEB3AdminIPOLotInputResponse;
import webbroker3.ipo.service.delegate.WEB3AdminIpoLotService;
import webbroker3.util.WEB3LogUtility;

/**
 * (�Ǘ���IPO���I�����n���h��)<BR>
 * �Ǘ���IPO���I�����n���h���N���X<BR>
 * 
 * @@author �A����
 * @@version 1.0
 */
public class WEB3AdminIpoLotHandler implements MessageHandler
{
    
    /**
     * ���O�o�̓��[�e�B���e�B�B
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminIpoLotHandler.class);
    
    /**
     * @@roseuid 4112EEA7253C
     */
    public WEB3AdminIpoLotHandler()
    {
        
    }
    
    /**
     * (IPO���I��������)<BR>
     * �Ǘ���IPO���I�������͏���<BR>
     * <BR>
     * �Ǘ���IPO���I�����T�[�r�X���擾���Aexecute()���\�b�h���R�[������B<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * �Ǘ���IPO���I�������̓��N�G�X�g�I�u�W�F�N�g�B
     * @@return WEB3AdminIPOLotInputResponse
     * */
    public WEB3AdminIPOLotInputResponse getLotInput(WEB3AdminIPOLotInputRequest l_request)
    {
        final String STR_METHOD_NAME = " getLotInput(WEB3AdminIPOLotInputRequest)";
        log.entering(STR_METHOD_NAME);
        
        WEB3AdminIPOLotInputResponse l_response = null;
        WEB3AdminIpoLotService l_service = null;
        
        try
        {
            l_service =
                (WEB3AdminIpoLotService) Services.getService(
                    WEB3AdminIpoLotService.class);
        }
        catch (Exception l_ex)
        {
            l_response = (WEB3AdminIPOLotInputResponse) l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "�Ǘ���IPO���I�����T�[�r�X�̎擾�Ɏ��s���܂����B",
                l_response.errorInfo,
                l_ex);
            return l_response;
        }
        
        //�Ǘ���IPO���I�����T�[�r�X���擾���Aexecute()���\�b�h���R�[������B
        try
        {
            l_response = (WEB3AdminIPOLotInputResponse) l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response = (WEB3AdminIPOLotInputResponse) l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request, 
                "�Ǘ���IPO���I�������͏����̎��{�Ɏ��s���܂����B",
                l_response.errorInfo, 
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        catch (WEB3BaseRuntimeException l_ex)
        {
            l_response = (WEB3AdminIPOLotInputResponse) l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request, 
                "�Ǘ���IPO���I�������͏����̎��{�Ɏ��s���܂����B",
                l_response.errorInfo, 
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        
        log.exiting(STR_METHOD_NAME);
        
        //���X�|���X�I�u�W�F�N�g��ԋp����B
        return l_response;
    }
    
    /**
     * (IPO���I�����m�F)<BR>
     * �Ǘ���IPO���I�����m�F����<BR>
     * <BR>
     * �Ǘ���IPO���I�����T�[�r�X���擾���Aexecute()���\�b�h���R�[������B<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * �Ǘ���IPO���I�����m�F���N�G�X�g�I�u�W�F�N�g�B
     * @@return WEB3AdminIPOLotConfirmResponse
     * */
    public WEB3AdminIPOLotConfirmResponse getLotConfirm(WEB3AdminIPOLotConfirmRequest l_request)
    {
        final String STR_METHOD_NAME = " getLotConfirm(WEB3AdminIPOLotConfirmRequest)";
        log.entering(STR_METHOD_NAME);
        
        WEB3AdminIPOLotConfirmResponse l_response = null;
        WEB3AdminIpoLotService l_service = null;
        
        try
        {
            l_service =
                (WEB3AdminIpoLotService)Services.getService(
                    WEB3AdminIpoLotService.class);
        }
        catch (Exception l_ex)
        {
            l_response = (WEB3AdminIPOLotConfirmResponse) l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "�Ǘ���IPO���I�����T�[�r�X�̎擾�Ɏ��s���܂����B",
                l_response.errorInfo,
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        //�Ǘ���IPO���I�����T�[�r�X���擾���Aexecute()���\�b�h���R�[������B
        try
        {
            l_response = (WEB3AdminIPOLotConfirmResponse) l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response = (WEB3AdminIPOLotConfirmResponse) l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request, 
                "�Ǘ���IPO���I�����m�F�����̎��{�Ɏ��s���܂����B",
                l_response.errorInfo, 
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        catch (WEB3BaseRuntimeException l_ex)
        {
            l_response = (WEB3AdminIPOLotConfirmResponse) l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request, 
                "�Ǘ���IPO���I�����m�F�����̎��{�Ɏ��s���܂����B",
                l_response.errorInfo, 
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        
        log.exiting(STR_METHOD_NAME);
        
        //���X�|���X�I�u�W�F�N�g��ԋp����B
        return l_response;
    }
    
    /**
     * (IPO���I��������)<BR>
     * �Ǘ���IPO���I������������<BR>
     * <BR>
     * �Ǘ���IPO���I�����T�[�r�X���擾���Aexecute()���\�b�h���R�[������B<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * �Ǘ���IPO���I�����������N�G�X�g�I�u�W�F�N�g�B
     * @@return WEB3AdminIPOLotCompleteResponse
     * */
    public WEB3AdminIPOLotCompleteResponse getLotComplete(WEB3AdminIPOLotCompleteRequest l_request)
    {
        final String STR_METHOD_NAME = " getLotComplete(WEB3AdminIPOLotCompleteRequest)";
        log.entering(STR_METHOD_NAME);
        
        WEB3AdminIPOLotCompleteResponse l_response = null;
        WEB3AdminIpoLotService l_service = null;
        
        try
        {
            l_service =
                (WEB3AdminIpoLotService) Services.getService(
                    WEB3AdminIpoLotService.class);
        }
        catch (Exception l_ex)
        {
            l_response = (WEB3AdminIPOLotCompleteResponse) l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "�Ǘ���IPO���I�����T�[�r�X�̎擾�Ɏ��s���܂����B",
                l_response.errorInfo,
                l_ex);
            return l_response;
        }
        
        //�Ǘ���IPO���I�����T�[�r�X���擾���Aexecute()���\�b�h���R�[������B
        try
        {
            l_response = (WEB3AdminIPOLotCompleteResponse) l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response = (WEB3AdminIPOLotCompleteResponse) l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request, 
                "�Ǘ���IPO���I�������������̎��{�Ɏ��s���܂����B",
                l_response.errorInfo, 
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        catch (WEB3BaseRuntimeException l_ex)
        {
            l_response = (WEB3AdminIPOLotCompleteResponse) l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request, 
                "�Ǘ���IPO���I�������������̎��{�Ɏ��s���܂����B",
                l_response.errorInfo, 
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        
        log.exiting(STR_METHOD_NAME);
        
        //���X�|���X�I�u�W�F�N�g��ԋp����B
        return l_response;
    } 
}
@
