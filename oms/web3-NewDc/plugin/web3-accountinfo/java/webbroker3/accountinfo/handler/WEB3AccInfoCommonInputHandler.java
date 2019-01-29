head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.10.02.13.51;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8644d782f8a12fc;
filename	WEB3AccInfoCommonInputHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : ���q�l��񋤒ʓ��̓n���h��(WEB3AccInfoCommonInputHandler)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/11/11 ����� (���u) �V�K�쐬
*/

package webbroker3.accountinfo.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.accountinfo.message.WEB3AccInfoCommonInputRequest;
import webbroker3.accountinfo.message.WEB3AccInfoCommonInputResponse;
import webbroker3.accountinfo.service.delegate.WEB3AccInfoCommonInputService;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.util.WEB3LogUtility;

/**
 * (���q�l��񋤒ʓ��̓n���h��)<BR>
 * ���q�l��񋤒ʓ��̓n���h��<BR>
 * 
 * @@author �����(���u)
 * @@version 1.0
 */
public class WEB3AccInfoCommonInputHandler implements MessageHandler 
{
    /**
     * ���O�o�̓��[�e�B���e�B�B
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AccInfoCommonInputHandler.class);
            
    /**
     * @@roseuid 418F3A0A031C
     */
    public WEB3AccInfoCommonInputHandler() 
    {
     
    }
    
    /**
     * (���͉�ʕ\��)<BR>
     * ���͉�ʕ\�����ʏ��������{����B<BR>
     * <BR>
     * ���q�l��񋤒ʓ��̓T�[�r�X���擾���Aexecute()���\�b�h���R�[������B <BR>
     * @@param l_request - ���q�l��񋤒ʓ��̓��N�G�X�g�f�[�^�I�u�W�F�N�g
     * 
     * 
     * @@return webbroker3.accountinfo.message.WEB3AccInfoCommonInputResponse
     * @@roseuid 41456E630366
     */
    public WEB3AccInfoCommonInputResponse inputScreenDisplay(WEB3AccInfoCommonInputRequest l_request) 
    {
        final String STR_METHOD_NAME = " inputScreenDisplay(WEB3AccInfoCommonInputRequest l_request)";
        log.entering(STR_METHOD_NAME);

        WEB3AccInfoCommonInputResponse l_response = null;
        WEB3AccInfoCommonInputService l_service = null;

        try
        {
            l_service = (WEB3AccInfoCommonInputService)Services.getService(WEB3AccInfoCommonInputService.class);
        }
        // �T�[�r�X�ŗ�O�����������ꍇ�́A�G���[�������X�|���X���b�Z�[�W�ɐݒ肷��
        catch (Exception l_ex)
        {
            l_response = (WEB3AccInfoCommonInputResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "���q�l��񋤒ʓ��̓T�[�r�X�̎擾�Ɏ��s���܂����B",
                l_response.errorInfo,
                l_ex);
            return l_response;
        }

        try
        {
            l_response = (WEB3AccInfoCommonInputResponse)l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response = (WEB3AccInfoCommonInputResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request, "���q�l��񋤒ʓ��͂̓��͉�ʕ\���Ɏ��s���܂����B", l_ex);
            return l_response;
        }

        log.exiting(STR_METHOD_NAME);

        return l_response;
    }
}
@
