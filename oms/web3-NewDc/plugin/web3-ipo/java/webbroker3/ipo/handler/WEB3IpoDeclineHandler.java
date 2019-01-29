head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.05.48.49;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6f44d7efa104045;
filename	WEB3IpoDeclineHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : IPO���ރn���h���N���X(WEB3IpoDeclineHandler.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/08/10 �d�� (���u) �V�K�쐬
*/
package webbroker3.ipo.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.ipo.message.WEB3IPODeclineCompleteRequest;
import webbroker3.ipo.message.WEB3IPODeclineConfirmRequest;
import webbroker3.ipo.message.WEB3IPODeclineConfirmResponse;
import webbroker3.ipo.message.WEB3IPODeclineCompleteResponse;
import webbroker3.ipo.service.delegate.WEB3IpoDeclineService;
import webbroker3.util.WEB3LogUtility;

/**
 * IPO���ރn���h���N���X
 * @@author �d��
 * @@version 1.0
 */
public class WEB3IpoDeclineHandler implements MessageHandler 
{
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(WEB3IpoDeclineHandler.class);
    
    /**
     * @@roseuid 4112EEA703D4
     */
    public WEB3IpoDeclineHandler() 
    {
     
    }
    
    /**
     * (���ފm�F)<BR>
     * IPO���ފm�F����<BR>
     * <BR>
     * IPO���ރT�[�r�X���擾���Aexecute()���\�b�h���R�[������B<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * IPO���ފm�F���N�G�X�g�f�[�^�I�u�W�F�N�g
     * @@return webbroker3.ipo.message.WEB3IPODeclineConfirmResponse
     * @@roseuid 40DB92B600A7
     */
    public WEB3IPODeclineConfirmResponse declineConfirm(WEB3IPODeclineConfirmRequest l_request) 
    {
        final String STR_METHOD_NAME = " declineConfirm(WEB3IPODeclineConfirmRequest)"; 
        log.entering(STR_METHOD_NAME);
        WEB3IPODeclineConfirmResponse l_response = null;
        WEB3IpoDeclineService l_service = null;
        
        try
        {
            l_service = (WEB3IpoDeclineService) Services.getService(WEB3IpoDeclineService.class);
        }
        catch (Exception l_exception)
        {              
            l_response = (WEB3IPODeclineConfirmResponse) l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(l_request, "IPO���ފm�F���X�|���X�̎擾�Ɏ��s���܂����B", l_response.errorInfo, l_exception);
            return l_response;
        }
         
        try
        {
            l_response = (WEB3IPODeclineConfirmResponse) l_service.execute(l_request);
        }
        catch (WEB3BaseException l_baseException)
        {
            l_response = (WEB3IPODeclineConfirmResponse) l_request.createResponse();
            l_response.errorInfo = l_baseException.getErrorInfo();
            log.error(l_request, "IPO���ފm�F�Ɏ��s���܂����B", l_response.errorInfo,l_baseException);
            return l_response;
        }
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * (���ފ���)<BR>
     * IPO���ފ�������<BR>
     * <BR>
     * IPO���ރT�[�r�X���擾���Aexecute()���\�b�h���R�[������B<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * IPO���ފ������N�G�X�g�f�[�^�I�u�W�F�N�g
     * @@return webbroker3.ipo.message.WEB3IPODeclineResponse
     * @@roseuid 40DB92B600C6
     */
    public WEB3IPODeclineCompleteResponse declineComplete(WEB3IPODeclineCompleteRequest l_request) 
    {
        
        final String STR_METHOD_NAME = " declineComplete(WEB3IPODeclineCompleteRequest)"; 
        log.entering(STR_METHOD_NAME);
        WEB3IPODeclineCompleteResponse l_response = null;
        WEB3IpoDeclineService l_service = null;
        try
        {
            l_service = (WEB3IpoDeclineService) Services.getService(WEB3IpoDeclineService.class);
        }
        catch (Exception l_exception)
        {              
            l_response = (WEB3IPODeclineCompleteResponse) l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(l_request, "IPO���ފ������N�G�X�g�̎擾�Ɏ��s���܂����B", l_response.errorInfo, l_exception);
            return l_response;
        }
        try
        {
            l_response = (WEB3IPODeclineCompleteResponse) l_service.execute(l_request);
        }
            catch (WEB3BaseException l_baseException)
        {
            l_response = (WEB3IPODeclineCompleteResponse) l_request.createResponse();
            l_response.errorInfo = l_baseException.getErrorInfo();
            log.error(l_request, "IPO���ފ����Ɏ��s���܂����B", l_baseException);
            return l_response;
        }
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
        
}
@
