head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.05.11.27;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7844d7ef4f02f89;
filename	WEB3AdminMailInfoDeleteHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : ���[�����폜�n���h��(WEB3AdminMailInfoDeleteHandler.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/15  鰊](���u) �V�K�쐬
*/
package webbroker3.mailinfo.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.mailinfo.message.WEB3AdminMailInfoDeleteCompleteRequest;
import webbroker3.mailinfo.message.WEB3AdminMailInfoDeleteCompleteResponse;
import webbroker3.mailinfo.message.WEB3AdminMailInfoDeleteConfirmRequest;
import webbroker3.mailinfo.message.WEB3AdminMailInfoDeleteConfirmResponse;
import webbroker3.mailinfo.service.delegate.WEB3AdminMailInfoDeleteService;
import webbroker3.util.WEB3LogUtility;

/**
 * ( ���[�����폜�n���h�� )<BR>
 * <BR>
 * ���[�����폜�n���h���N���X<BR>
 * 
 * @@author 鰊]
 * @@version 1.0
 */
public class WEB3AdminMailInfoDeleteHandler implements MessageHandler 
{    
    /**
     * ���O�o�̓��[�e�B���e�B�B<BR>
     */
    private static  WEB3LogUtility log = WEB3LogUtility.getInstance(WEB3AdminMailInfoDeleteHandler.class);    
   
    /**
     * @@roseuid 416F1DD1035B
     */
    public WEB3AdminMailInfoDeleteHandler() 
    {
     
    }
    
    /**
     * ( confirm���[�����폜 )<BR>
     * <BR>
     * ���[�����폜�R���������s���B<BR>
     * <BR>
     * ���[�����폜�T�[�r�X���擾���Aexecute( )���\�b�h���R�[������B<BR>
     * @@param l_request - ( ���N�G�X�g�f�[�^ )<BR>
     * <BR>
     * ���[�����폜�m�F���N�G�X�g�I�u�W�F�N�g<BR>
     * @@return webbroker3.mailinfo.message.WEB3AdminMailInfoDeleteConfirmResponse
     * @@roseuid 413C3DA70271
     */
    public WEB3AdminMailInfoDeleteConfirmResponse confirmMailInfoDelete(WEB3AdminMailInfoDeleteConfirmRequest l_request) 
    {
        final String STR_METHOD_NAME = " confirmMailInfoDelete(WEB3AdminMailInfoDeleteConfirmRequest l_request)";
        log.entering(STR_METHOD_NAME);
        
        WEB3AdminMailInfoDeleteConfirmResponse l_response = null;
        WEB3AdminMailInfoDeleteService l_service = null;
        
        try
        {
            l_service = (WEB3AdminMailInfoDeleteService)Services.getService(WEB3AdminMailInfoDeleteService.class);
        }
        catch(Exception l_ex)
        {
            l_response = (WEB3AdminMailInfoDeleteConfirmResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002 ;
            log.error(l_request, "���[�����폜�n���h���T�[�r�X�Ɏ��s���܂����B", l_response.errorInfo, l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        
        try
        {
            l_response = (WEB3AdminMailInfoDeleteConfirmResponse)l_service.execute(l_request);
        }
        catch  (WEB3BaseException l_ex)
        {
            l_response = (WEB3AdminMailInfoDeleteConfirmResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();   
            log.error(l_request, "���[�����폜�Ɏ��s���܂����B", l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }     
        log.exiting(STR_METHOD_NAME);
        
        return l_response;
    }
    
    /**
     * ( complete���[�����폜 )<BR>
     * <BR>
     * ���[�����폜�������s���B<BR>
     * <BR>
     * ���[�����폜�T�[�r�X���擾���Aexecute( )���\�b�h���R�[������B<BR>
     * @@param l_request - ( ���N�G�X�g�f�[�^ )<BR>
     * <BR>
     * ���[�����폜�������N�G�X�g�I�u�W�F�N�g<BR>
     * @@return webbroker3.mailinfo.message.WEB3AdminMailInfoDeleteCompleteResponse
     * @@roseuid 413C3DB302BF
     */
    public WEB3AdminMailInfoDeleteCompleteResponse completeMailInfoDelete(WEB3AdminMailInfoDeleteCompleteRequest l_request) 
    {
        final String STR_METHOD_NAME = " completeMailInfoDelete(WEB3AdminMailInfoDeleteCompleteRequest l_request)";
        log.entering(STR_METHOD_NAME);
        WEB3AdminMailInfoDeleteCompleteResponse l_response = null;
        WEB3AdminMailInfoDeleteService l_service = null;
        try
        {
            l_service = (WEB3AdminMailInfoDeleteService)Services.getService(WEB3AdminMailInfoDeleteService.class);
        }
        catch(Exception l_ex)
        {
            l_response = (WEB3AdminMailInfoDeleteCompleteResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002 ;
            log.error(l_request, "���[�����폜�n���h���T�[�r�X�Ɏ��s���܂����B", l_response.errorInfo, l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        try
        {
            l_response = (WEB3AdminMailInfoDeleteCompleteResponse)l_service.execute(l_request);
        }
        catch  (WEB3BaseException l_ex)
        {
            l_response = (WEB3AdminMailInfoDeleteCompleteResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();   
            log.error(l_request, "���[�����폜�Ɏ��s���܂����B", l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }     
        log.exiting(STR_METHOD_NAME);
        return l_response;               
    }
}
@
