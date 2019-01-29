head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.44;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3AdminFeqMarketLinkChangeHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǘ��ҊO�������s��A���敪�ύX�n���h��(WEB3AdminFeqMarketRequestDivChangeHandler.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/09/12 �����q (���u) �V�K�쐬 
*/
package webbroker3.feq.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.feq.message.WEB3AdminFeqMarketLinkChangeCompleteRequest;
import webbroker3.feq.message.WEB3AdminFeqMarketLinkChangeCompleteResponse;
import webbroker3.feq.message.WEB3AdminFeqMarketLinkChangeConfirmRequest;
import webbroker3.feq.message.WEB3AdminFeqMarketLinkChangeConfirmResponse;
import webbroker3.feq.message.WEB3AdminFeqMarketLinkChangeInputRequest;
import webbroker3.feq.message.WEB3AdminFeqMarketLinkChangeInputResponse;
import webbroker3.feq.service.delegate.WEB3AdminFeqMarketLinkChangeService;
import webbroker3.util.WEB3LogUtility;

/**
 * (�Ǘ��ҊO�������s��A���敪�ύX�n���h��)<BR>
 * �Ǘ��ҊO�������s��A���敪�ύX�n���h���N���X<BR>
 *   
 * @@author �����q
 * @@version 1.0
 */

public class WEB3AdminFeqMarketLinkChangeHandler implements MessageHandler 
{
    /**
     * ���O�o�̓��[�e�B���e�B�B
     */
    private static WEB3LogUtility log = 
        WEB3LogUtility.getInstance(WEB3AdminFeqMarketLinkChangeHandler.class);
    
    /**
     * @@roseuid 42D0DA1602DE
     */
    public WEB3AdminFeqMarketLinkChangeHandler() 
    {
     
    }
    
    /**
     * (get���͉��)<BR>
     * ���͉�ʕ\���������s�Ȃ��B<BR>
     * <BR>
     * �Ǘ��ҊO�������s��A���敪�ύX�T�[�r�X���擾���Aexecute()���\�b�h���R�[������B<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * ���N�G�X�g�f�[�^<BR>
     * 
     * @@return WEB3AdminFeqMarketLinkChangeInputResponse
     * @@roseuid 429FECBF005E
     */
    public WEB3AdminFeqMarketLinkChangeInputResponse getInputScreen(
    	WEB3AdminFeqMarketLinkChangeInputRequest l_request) 
    {
        final String STR_METHOD_NAME = "getInputScreen�iWEB3AdminFeqMarketLinkChangeInputRequest l_request) ";
        log.entering(STR_METHOD_NAME);
        
        if (l_request == null)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME,
                "���N�G�X�g��null�ł��B");
        }

        WEB3AdminFeqMarketLinkChangeInputResponse l_response = null;
        WEB3AdminFeqMarketLinkChangeService l_service = null;
        
        try
        {            
            //get�Ǘ��ҊO�������s��A���敪�ύX�T�[�r�X
            l_service = (WEB3AdminFeqMarketLinkChangeService)
                Services.getService(WEB3AdminFeqMarketLinkChangeService.class);
            if (l_service == null)
            {
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                    this.getClass().getName() + STR_METHOD_NAME,
                    "�Ǘ��ҊO�������s��A���敪�ύX�T�[�r�X���擾�Ɏ��s���܂����B");
            }
            
            //execute
            l_response = (WEB3AdminFeqMarketLinkChangeInputResponse)l_service.execute(l_request);
        }        
        catch (WEB3BaseException l_ex)
        {
            l_response = (WEB3AdminFeqMarketLinkChangeInputResponse)l_request.createResponse();
            if (l_response == null)
            {
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                    this.getClass().getName() + STR_METHOD_NAME);
            }
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request, 
                "���͉�ʕ\�����������{�Ɏ��s���܂����B",
                l_response.errorInfo,
                l_ex);
        }
        catch (Exception l_ex)
        {
            l_response = (WEB3AdminFeqMarketLinkChangeInputResponse) l_request.createResponse();
            if (l_response == null)
            {
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                    this.getClass().getName() + STR_METHOD_NAME);
            }
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;    
            log.error(
                l_request, 
                "���͉�ʕ\�����������{�Ɏ��s���܂����B",
                l_response.errorInfo, 
                l_ex);
        }
        
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * (validate�ύX)<BR>
     * �Ǘ��ҊO�������s��A���敪�ύX�m�F�������s�Ȃ��B <BR>
     * <BR>
     * �Ǘ��ҊO�������s��A���敪�ύX�T�[�r�X���擾���Aexecute()���\�b�h���R�[������B<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * ���N�G�X�g�f�[�^<BR>
     * 
     * @@return WEB3AdminFeqMarketLinkChangeConfirmResponse
     * @@throws WEB3BaseException
     * @@roseuid 42BBB6760108
     */
    public WEB3AdminFeqMarketLinkChangeConfirmResponse validateChange(
    	WEB3AdminFeqMarketLinkChangeConfirmRequest l_request)
    {
        final String STR_METHOD_NAME = "validateChange(WEB3AdminFeqMarketLinkChangeConfirmRequest l_request)";
        log.entering(STR_METHOD_NAME);
        
        if (l_request == null)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME,
                "���N�G�X�g��null�ł��B");
        }

        WEB3AdminFeqMarketLinkChangeConfirmResponse l_response = null;
        WEB3AdminFeqMarketLinkChangeService l_service = null;
        
        try
        {            
            //get�Ǘ��ҊO�������s��A���敪�ύX�T�[�r�X
            l_service = (WEB3AdminFeqMarketLinkChangeService)
                Services.getService(WEB3AdminFeqMarketLinkChangeService.class);
            if (l_service == null)
            {
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                    this.getClass().getName() + STR_METHOD_NAME,
                    "�Ǘ��ҊO�������s��A���敪�ύX�T�[�r�X���擾�Ɏ��s���܂����B");
            }
            
            //execute
            l_response = (WEB3AdminFeqMarketLinkChangeConfirmResponse)l_service.execute(l_request);
        }        
        catch (WEB3BaseException l_ex)
        {
            l_response = (WEB3AdminFeqMarketLinkChangeConfirmResponse)l_request.createResponse();
            if (l_response == null)
            {
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                    this.getClass().getName() + STR_METHOD_NAME);
            }
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request, 
                "validate�ύX�����{�Ɏ��s���܂����B",
                l_response.errorInfo,
                l_ex);
        }
        catch (Exception l_ex)
        {
            l_response = (WEB3AdminFeqMarketLinkChangeConfirmResponse) l_request.createResponse();
            if (l_response == null)
            {
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                    this.getClass().getName() + STR_METHOD_NAME);
            }
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;    
            log.error(
                l_request, 
                "validate�ύX�����{�Ɏ��s���܂����B",
                l_response.errorInfo, 
                l_ex);
        }
        
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * (submit�ύX)<BR>
     * �Ǘ��ҊO�������s��A���敪�ύX�����������s�Ȃ��B<BR>
     * <BR>
     * �Ǘ��ҊO�������s��A���敪�ύX�T�[�r�X���擾���Aexecute()���\�b�h���R�[������B<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * ���N�G�X�g�f�[�^<BR>
     * 
     * @@return WEB3AdminFeqMarketLinkChangeCompleteResponse
     * @@throws WEB3BaseException
     * @@roseuid 42BBB6760108
     */
    public WEB3AdminFeqMarketLinkChangeCompleteResponse submitChange(
    	WEB3AdminFeqMarketLinkChangeCompleteRequest l_request)
    {
        final String STR_METHOD_NAME = "submitChange(WEB3AdminFeqMarketLinkChangeCompleteRequest l_request)";
        log.entering(STR_METHOD_NAME);
        
        if (l_request == null)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME,
                "���N�G�X�g��null�ł��B");
        }

        WEB3AdminFeqMarketLinkChangeCompleteResponse l_response = null;
        WEB3AdminFeqMarketLinkChangeService l_service = null;
        
        try
        {            
            //get�Ǘ��ҊO�������s��A���敪�ύX�T�[�r�X
            l_service = (WEB3AdminFeqMarketLinkChangeService)
                Services.getService(WEB3AdminFeqMarketLinkChangeService.class);
            if (l_service == null)
            {
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                    this.getClass().getName() + STR_METHOD_NAME,
                    "�Ǘ��ҊO�������s��A���敪�ύX�T�[�r�X���擾�Ɏ��s���܂����B");
            }
            
            //execute
            l_response = (WEB3AdminFeqMarketLinkChangeCompleteResponse)l_service.execute(l_request);
        }        
        catch (WEB3BaseException l_ex)
        {
            l_response = (WEB3AdminFeqMarketLinkChangeCompleteResponse)l_request.createResponse();
            if (l_response == null)
            {
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                    this.getClass().getName() + STR_METHOD_NAME);
            }
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request, 
                "submit�ύX�����{�Ɏ��s���܂����B",
                l_response.errorInfo,
                l_ex);
        }
        catch (Exception l_ex)
        {
            l_response = (WEB3AdminFeqMarketLinkChangeCompleteResponse) l_request.createResponse();
            if (l_response == null)
            {
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                    this.getClass().getName() + STR_METHOD_NAME);
            }
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;    
            log.error(
                l_request, 
                "submit�ύX�����{�Ɏ��s���܂����B",
                l_response.errorInfo, 
                l_ex);
        }
        
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

}
@
