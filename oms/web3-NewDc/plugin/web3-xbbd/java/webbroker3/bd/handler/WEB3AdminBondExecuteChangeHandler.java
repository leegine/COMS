head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.18;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3AdminBondExecuteChangeHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǘ��Җ��ύX�n���h��(WEB3AdminBondExecuteChangeHandler.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/08/17 ����(���u) �V�K�쐬
*/

package webbroker3.bd.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.bd.message.WEB3AdminBondExecChangeCompleteRequest;
import webbroker3.bd.message.WEB3AdminBondExecChangeCompleteResponse;
import webbroker3.bd.message.WEB3AdminBondExecChangeConfirmRequest;
import webbroker3.bd.message.WEB3AdminBondExecChangeConfirmResponse;
import webbroker3.bd.message.WEB3AdminBondExecChangeInputRequest;
import webbroker3.bd.message.WEB3AdminBondExecChangeInputResponse;
import webbroker3.bd.service.delegate.WEB3AdminBondExecuteChangeService;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.util.WEB3LogUtility;

/**
 * (�Ǘ��Җ��ύX�n���h��)<BR>
 * �Ǘ��Җ��ύX�n���h���N���X
 * 
 * @@author ����(���u)
 * @@version 1.0 
 */
public class WEB3AdminBondExecuteChangeHandler implements MessageHandler
{
    /**
     * ���O���[�e�B���e�B
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminBondExecuteChangeHandler.class); 
    
    /**
     * @@roseuid 44E3363102EE
     */
    public WEB3AdminBondExecuteChangeHandler() 
    {
     
    }
    
    /**
     * (�Ǘ��Җ��ύX���̓��N�G�X�g)<BR>
     * �Ǘ��Җ��ύX���͏������s���B <BR>
     * <BR>
     * �Ǘ��Җ��ύX�T�[�r�X���擾���Aexecute()���\�b�h���R�[������B<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * ���N�G�X�g�f�[�^<BR>
     * @@return WEB3AdminBondExecChangeInputResponse
     * @@roseuid 44C897F8037A
     */
    public WEB3AdminBondExecChangeInputResponse inputExecuteChange(
        WEB3AdminBondExecChangeInputRequest l_request) 
    {
        final String STR_METHOD_NAME = 
            "inputExecuteChange(WEB3AdminBondExecChangeInputRequest)";
        log.entering(STR_METHOD_NAME);

        //�Ǘ��Җ��ύX�T�[�r�X���擾��
        WEB3AdminBondExecuteChangeService l_service = null;
        WEB3AdminBondExecChangeInputResponse l_response = null;
        try
        {
            l_service =
                (WEB3AdminBondExecuteChangeService) 
                Services.getService(WEB3AdminBondExecuteChangeService.class);
        }
        // �T�[�r�X�ŗ�O�����������ꍇ�́A�G���[�������X�|���X���b�Z�[�W�ɐݒ肷��
        catch (Exception l_ex)
        {
            l_response =
                (WEB3AdminBondExecChangeInputResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "�Ǘ��Җ��ύX�T�[�r�X���擾�Ɏ��s���܂����B",
                l_response.errorInfo,
                l_ex);
            return l_response;
        }
        try
        {
            //execute()���\�b�h���R�[������
            l_response = (WEB3AdminBondExecChangeInputResponse) l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response =
                (WEB3AdminBondExecChangeInputResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request, 
                "�Ǘ��Җ��ύX���͏��������s���܂����B", 
                l_ex.getErrorInfo(), 
                l_ex);
            return l_response;
        }
        catch (WEB3BaseRuntimeException l_ex)
        {
            l_response = 
                (WEB3AdminBondExecChangeInputResponse) l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request, 
                "�Ǘ��Җ��ύX���͏��������s���܂����B", 
                l_response.errorInfo, 
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * (�Ǘ��Җ��ύX�m�F���N�G�X�g)<BR>
     * �Ǘ��Җ��ύX�m�F�������s���B <BR>
     * <BR>
     * �Ǘ��Җ��ύX�T�[�r�X���擾���Aexecute()���\�b�h���R�[������B<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * ���N�G�X�g�f�[�^<BR>
     * @@return WEB3AdminBondExecChangeConfirmResponse
     * @@roseuid 44CD965D0294
     */
    public WEB3AdminBondExecChangeConfirmResponse confirmExecuteChange(
        WEB3AdminBondExecChangeConfirmRequest l_request) 
    {
        final String STR_METHOD_NAME = 
            "confirmExecuteChange(WEB3AdminBondExecChangeConfirmRequest)";
        log.entering(STR_METHOD_NAME);

        //�Ǘ��Җ��ύX�T�[�r�X���擾��
        WEB3AdminBondExecuteChangeService l_service = null;
        WEB3AdminBondExecChangeConfirmResponse l_response = null;
        try
        {
            l_service =
                (WEB3AdminBondExecuteChangeService) 
                Services.getService(WEB3AdminBondExecuteChangeService.class);
        }
        // �T�[�r�X�ŗ�O�����������ꍇ�́A�G���[�������X�|���X���b�Z�[�W�ɐݒ肷��
        catch (Exception l_ex)
        {
            l_response =
                (WEB3AdminBondExecChangeConfirmResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "�Ǘ��Җ��ύX�T�[�r�X���擾�Ɏ��s���܂����B",
                l_response.errorInfo,
                l_ex);
            return l_response;
        }
        try
        {
            //execute()���\�b�h���R�[������
            l_response = (WEB3AdminBondExecChangeConfirmResponse) l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response =
                (WEB3AdminBondExecChangeConfirmResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request, 
                "�Ǘ��Җ��ύX�m�F���������s���܂����B", 
                l_ex.getErrorInfo(), 
                l_ex);
            return l_response;
        }
        catch (WEB3BaseRuntimeException l_ex)
        {
            l_response = 
                (WEB3AdminBondExecChangeConfirmResponse) l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request, 
                "�Ǘ��Җ��ύX�m�F���������s���܂����B", 
                l_response.errorInfo, 
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * (�Ǘ��Җ��ύX�������N�G�X�g)<BR>
     * �Ǘ��Җ��ύX�����������s���B <BR>
     * <BR>
     * �Ǘ��Җ��ύX�T�[�r�X���擾���Aexecute()���\�b�h���R�[������B<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * ���N�G�X�g�f�[�^<BR>
     * @@return WEB3AdminBondExecChangeCompleteResponse
     * @@roseuid 44CD97240105
     */
    public WEB3AdminBondExecChangeCompleteResponse completeExecuteChange(
        WEB3AdminBondExecChangeCompleteRequest l_request) 
    {
        final String STR_METHOD_NAME = 
            "completeExecuteChange(WEB3AdminBondExecChangeCompleteRequest)";
        log.entering(STR_METHOD_NAME);

        //�Ǘ��Җ��ύX�T�[�r�X���擾��
        WEB3AdminBondExecuteChangeService l_service = null;
        WEB3AdminBondExecChangeCompleteResponse l_response = null;
        try
        {
            l_service =
                (WEB3AdminBondExecuteChangeService) 
                Services.getService(WEB3AdminBondExecuteChangeService.class);
        }
        // �T�[�r�X�ŗ�O�����������ꍇ�́A�G���[�������X�|���X���b�Z�[�W�ɐݒ肷��
        catch (Exception l_ex)
        {
            l_response =
                (WEB3AdminBondExecChangeCompleteResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "�Ǘ��Җ��ύX�T�[�r�X���擾�Ɏ��s���܂����B",
                l_response.errorInfo,
                l_ex);
            return l_response;
        }
        try
        {
            //execute()���\�b�h���R�[������
            l_response = (WEB3AdminBondExecChangeCompleteResponse) l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response =
                (WEB3AdminBondExecChangeCompleteResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request, 
                "�Ǘ��Җ��ύX�������������s���܂����B", 
                l_ex.getErrorInfo(), 
                l_ex);
            return l_response;
        }
        catch (WEB3BaseRuntimeException l_ex)
        {
            l_response = 
                (WEB3AdminBondExecChangeCompleteResponse) l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request, 
                "�Ǘ��Җ��ύX�������������s���܂����B", 
                l_response.errorInfo, 
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
}
@
