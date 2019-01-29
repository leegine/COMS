head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.10.02.13.56;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8644d782f8a12fc;
filename	WEB3AccInfoPasswordChangeHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : ���q�l���Ïؔԍ��ύX�n���h��(WEB3AccInfoPasswordChangeHandler)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/11/12 ����� (���u) �V�K�쐬
*/

package webbroker3.accountinfo.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.accountinfo.message.WEB3AccInfoPasswordChangeCompleteRequest;
import webbroker3.accountinfo.message.WEB3AccInfoPasswordChangeCompleteResponse;
import webbroker3.accountinfo.message.WEB3AccInfoPasswordChangeInputRequest;
import webbroker3.accountinfo.message.WEB3AccInfoPasswordChangeInputResponse;
import webbroker3.accountinfo.service.delegate.WEB3AccInfoPasswordChangeService;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.util.WEB3LogUtility;

/**
 * (���q�l���Ïؔԍ��ύX�n���h��)<BR>
 * ���q�l���Ïؔԍ��ύX�n���h��<BR>
 * 
 * @@author �����(���u)
 * @@version 1.0
 */
public class WEB3AccInfoPasswordChangeHandler implements MessageHandler 
{
    /**
     * ���O�o�̓��[�e�B���e�B�B
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AccInfoPasswordChangeHandler.class);        
    
    /**
     * @@roseuid 418F3A0B0186
     */
    public WEB3AccInfoPasswordChangeHandler() 
    {
     
    }
    
    /**
     * (���͉�ʕ\��)<BR>
     * �Ïؔԍ��ύX���͉�ʕ\���������s���B<BR>
     * <BR>
     * ���q�l���Ïؔԍ��ύX�T�[�r�X���擾���Aexecute()���\�b�h���R�[������B <BR>
     * @@param l_request - ���q�l���Ïؔԍ��ύX���̓��N�G�X�g�f�[�^�I�u�W�F�N�g
     * 
     * @@return webbroker3.accountinfo.message.WEB3AccInfoPasswordChangeInputResponse
     * @@roseuid 416BBA6E03C4
     */
    public WEB3AccInfoPasswordChangeInputResponse inputScreenDisplay(WEB3AccInfoPasswordChangeInputRequest l_request) 
    {
        final String STR_METHOD_NAME = " inputScreenDisplay(WEB3AccInfoPasswordChangeInputRequest l_request)";
        log.entering(STR_METHOD_NAME);

        WEB3AccInfoPasswordChangeInputResponse l_response = null;
        WEB3AccInfoPasswordChangeService l_service = null;

        try
        {
            l_service = (WEB3AccInfoPasswordChangeService)Services.getService(WEB3AccInfoPasswordChangeService.class);
        }
        // �T�[�r�X�ŗ�O�����������ꍇ�́A�G���[�������X�|���X���b�Z�[�W�ɐݒ肷��
        catch (Exception l_ex)
        {
            l_response = (WEB3AccInfoPasswordChangeInputResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "���q�l���Ïؔԍ��ύX�T�[�r�X�̎擾�Ɏ��s���܂����B",
                l_response.errorInfo,
                l_ex);
            return l_response;
        }

        try
        {
            l_response = (WEB3AccInfoPasswordChangeInputResponse)l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response = (WEB3AccInfoPasswordChangeInputResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request, "���q�l���Ïؔԍ��ύX�̓��͉�ʕ\���Ɏ��s���܂����B", l_ex);
            return l_response;
        }

        log.exiting(STR_METHOD_NAME);

        return l_response;
    }
    
    /**
     * (�Ïؔԍ��ύX)<BR>
     * �Ïؔԍ��ύX�������s���B<BR>
     * <BR>
     * ���q�l���Ïؔԍ��ύX�T�[�r�X���擾���Aexecute()���\�b�h���R�[������B <BR>
     * @@param l_request - ���q�l���Ïؔԍ��ύX���N�G�X�g�f�[�^�I�u�W�F�N�g
     * 
     * @@return webbroker3.accountinfo.message.WEB3AccInfoPasswordChangeCompleteResponse
     * @@roseuid 416BBA6E03E3
     */
    public WEB3AccInfoPasswordChangeCompleteResponse changePassword(WEB3AccInfoPasswordChangeCompleteRequest l_request) 
    {
        final String STR_METHOD_NAME = " changePassword(WEB3AccInfoPasswordChangeCompleteRequest l_request)";
        log.entering(STR_METHOD_NAME);

        WEB3AccInfoPasswordChangeCompleteResponse l_response = null;
        WEB3AccInfoPasswordChangeService l_service = null;

        try
        {
            l_service = (WEB3AccInfoPasswordChangeService)Services.getService(WEB3AccInfoPasswordChangeService.class);
        }
        // �T�[�r�X�ŗ�O�����������ꍇ�́A�G���[�������X�|���X���b�Z�[�W�ɐݒ肷��
        catch (Exception l_ex)
        {
            l_response = (WEB3AccInfoPasswordChangeCompleteResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "���q�l���Ïؔԍ��ύX�T�[�r�X�̎擾�Ɏ��s���܂����B",
                l_response.errorInfo,
                l_ex);
            return l_response;
        }

        try
        {
            l_response = (WEB3AccInfoPasswordChangeCompleteResponse)l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response = (WEB3AccInfoPasswordChangeCompleteResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request, "���q�l���Ïؔԍ��ύX�̈Ïؔԍ��ύX�Ɏ��s���܂����B", l_ex);
            return l_response;
        }

        log.exiting(STR_METHOD_NAME);

        return l_response;
    }
}
@
