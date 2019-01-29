head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.05.11.31;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7844d7ef4f02f89;
filename	WEB3AdminMailInfoReferenceHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : ���[�����ꗗ�n���h��(WEB3AdminMailInfoReferenceHandler.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/15  鰊](���u) �V�K�쐬
*/
package webbroker3.mailinfo.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.mailinfo.message.WEB3AdminMailInfoReferenceRequest;
import webbroker3.mailinfo.message.WEB3AdminMailInfoReferenceResponse;
import webbroker3.mailinfo.service.delegate.WEB3AdminMailInfoReferenceService;
import webbroker3.util.WEB3LogUtility;

/**
 * (���[�����ꗗ�n���h��)<BR>
 * ���[�����ꗗ�n���h���N���X<BR>
 * 
 * @@author 鰊]
 * @@version 1.0
 */
public class WEB3AdminMailInfoReferenceHandler implements MessageHandler 
{
    /**
     * ���O�o�̓��[�e�B���e�B�B
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(WEB3AdminMailInfoReferenceHandler.class);
    
    /**
     * @@roseuid 416F1DD10148
     */
    public WEB3AdminMailInfoReferenceHandler() 
    {
     
    }
    
    /**
     * (���[�����ꗗ���N�G�X�g)<BR>
     * ���[�����ꗗ�Ɖ�����s���B<BR>
     * <BR>
     * ���[�����ꗗ�T�[�r�X���擾���Aexecute( )���\�b�h���R�[������B<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * ���[�����ꗗ���N�G�X�g�I�u�W�F�N�g<BR>
     * @@return webbroker3.mailinfo.message.WEB3AdminMailInfoReferenceResponse
     * @@roseuid 413C46410242
     */
    public WEB3AdminMailInfoReferenceResponse mailInfoReferenceRequest(WEB3AdminMailInfoReferenceRequest l_request) 
    {
        final String STR_METHOD_NAME = " mailInfoReferenceRequest(WEB3AdminMailInfoReferenceRequest l_request)";
        log.entering(STR_METHOD_NAME);
        
        WEB3AdminMailInfoReferenceResponse l_response = null; 
        WEB3AdminMailInfoReferenceService l_service = null;
          
        try
        {
            l_service = (WEB3AdminMailInfoReferenceService)Services.getService(WEB3AdminMailInfoReferenceService.class); 
        }
        catch(Exception l_ex)
        {
            l_response = (WEB3AdminMailInfoReferenceResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002 ;
            log.error(l_request, "���[�����ꗗ�n���h���T�[�r�X�Ɏ��s���܂����B", l_response.errorInfo, l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        
        try
        {
            l_response = (WEB3AdminMailInfoReferenceResponse)l_service.execute(l_request);
        }
        catch(WEB3BaseException l_ex)
        {
            l_response = (WEB3AdminMailInfoReferenceResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request, "���[�����ꗗ�Ɏ��s���܂����B", l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
}
@
