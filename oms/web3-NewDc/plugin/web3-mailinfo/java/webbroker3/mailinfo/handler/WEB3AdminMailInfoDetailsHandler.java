head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.05.11.18;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7844d7ef4f02f89;
filename	WEB3AdminMailInfoDetailsHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : ���[�����ڍ׃n���h��(WEB3AdminMailInfoDetailsHandler.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/15  鰊](���u) �V�K�쐬
Revesion History : 2004/10/19  ����(���u) �쐬
*/
package webbroker3.mailinfo.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3BaseException;
import webbroker3.mailinfo.message.WEB3AdminMailInfoDetailsRequest;
import webbroker3.mailinfo.message.WEB3AdminMailInfoDetailsResponse;
import webbroker3.mailinfo.service.delegate.WEB3AdminMailInfoDetailsService;
import webbroker3.util.WEB3LogUtility;

/**
 * (���[�����ڍ׃n���h��)<BR>
 * ���[�����ڍ׃n���h���N���X<BR>
 * 
 * @@author 鰊]
 * @@version 1.0
 */
public class WEB3AdminMailInfoDetailsHandler implements MessageHandler
{
    /**
     * ���O�o�̓��[�e�B���e�B�B
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(WEB3AdminMailInfoDetailsHandler.class);

    /**
     * @@roseuid 416F1DD201B5
     */
    public WEB3AdminMailInfoDetailsHandler()
    {

    }

    /**
     * (���[�����ڍ׃��N�G�X�g)<BR>
     * ���[�����ڍ׏Ɖ�����s���B<BR>
     * <BR>
     * ���[�����ڍ׃T�[�r�X���擾���Aexecute( )���\�b�h���R�[������B<BR>
     * @@param  l_request - (���N�G�X�g�f�[�^)<BR>
     * ���[�����ڍ׃��N�G�X�g�I�u�W�F�N�g<BR>
     * @@return webbroker3.mailinfo.message.WEB3AdminMailInfoDetailsResponse
     * @@roseuid 413C4EB803BE
     */
    public WEB3AdminMailInfoDetailsResponse mailInfoDetailsRequest(WEB3AdminMailInfoDetailsRequest l_request)
    {
        final String STR_METHOD_NAME = " mailInfoDetailsRequest(WEB3AdminMailInfoDetailsRequest l_request)";
        log.entering(STR_METHOD_NAME);

        WEB3AdminMailInfoDetailsResponse l_response = null;
        WEB3AdminMailInfoDetailsService l_service = null;
        
        try
        {
            l_service = (WEB3AdminMailInfoDetailsService) Services.getService(WEB3AdminMailInfoDetailsService.class);

        }
        catch (Exception l_ex)
        {
            l_response = (WEB3AdminMailInfoDetailsResponse) l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(l_request, "���[�����ڍ׃n���h���T�[�r�X�Ɏ��s���܂����B", l_response.errorInfo, l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        
        try
        {
            l_response = (WEB3AdminMailInfoDetailsResponse) l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response = (WEB3AdminMailInfoDetailsResponse) l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request, "���[�����ڍׂɎ��s���܂����B", l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
}
@
