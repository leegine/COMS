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
filename	WEB3AdminBondExecuteCancelHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǘ��ҍ�������n���h��(WEB3AdminBondExecuteCancelHandler.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/8/16 �����(���u) �V�K�쐬         
*/

package webbroker3.bd.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.bd.message.WEB3AdminBondExecCancelCompleteRequest;
import webbroker3.bd.message.WEB3AdminBondExecCancelCompleteResponse;
import webbroker3.bd.message.WEB3AdminBondExecCancelConfirmRequest;
import webbroker3.bd.message.WEB3AdminBondExecCancelConfirmResponse;
import webbroker3.bd.service.delegate.WEB3AdminBondExecuteCancelService;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.util.WEB3LogUtility;

/**
 * (�Ǘ��ҍ�������n���h��)<BR>
 * �Ǘ��ҍ�������n���h�� �N���X
 * 
 * @@author �����
 * @@version 1.0
 */
public class WEB3AdminBondExecuteCancelHandler implements MessageHandler 
{
    /**
     * ���O���[�e�B���e�B
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminBondExecuteCancelHandler.class); 
    
    /**
     * @@roseuid 44E336310177
     */
    public WEB3AdminBondExecuteCancelHandler() 
    {
     
    }
    
    /**
     * (�Ǘ��ҍ�������m�F���N�G�X�g)<BR>
     * ��������m�F�������s���B <BR>
     * <BR>
     * �Ǘ��ҍ�������T�[�r�X���擾���Aexecute()���\�b�h���R�[������B
     * @@param l_request - (���N�G�X�g)<BR>
     * �Ǘ��ҍ�������m�F���N�G�X�g
     * @@return WEB3AdminBondExecCancelConfirmResponse
     * @@roseuid 44B6FCE10321
     */
    public WEB3AdminBondExecCancelConfirmResponse confirmExecuteCancel(
        WEB3AdminBondExecCancelConfirmRequest l_request) 
    {
        final String STR_METHOD_NAME = 
            "confirmExecuteCancel(WEB3AdminBondExecCancelConfirmRequest)";
        log.entering(STR_METHOD_NAME);

        //���Ǘ��Җ����o�^�T�[�r�X���擾��
        WEB3AdminBondExecuteCancelService l_service = null;
        WEB3AdminBondExecCancelConfirmResponse l_response = null;
        try
        {
            l_service =
                (WEB3AdminBondExecuteCancelService) 
                Services.getService(WEB3AdminBondExecuteCancelService.class);
        }
        // �T�[�r�X�ŗ�O�����������ꍇ�́A�G���[�������X�|���X���b�Z�[�W�ɐݒ肷��
        catch (Exception l_ex)
        {
            l_response =
                (WEB3AdminBondExecCancelConfirmResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "�Ǘ��ҍ�������T�[�r�X���擾�Ɏ��s���܂����B",
                l_response.errorInfo,
                l_ex);
            return l_response;
        }
        try
        {
            //execute()���\�b�h���R�[������
            l_response = (WEB3AdminBondExecCancelConfirmResponse) l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response =
                (WEB3AdminBondExecCancelConfirmResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request, 
                "��������m�F���������s���܂����B", 
                l_ex.getErrorInfo(), 
                l_ex);
            return l_response;
        }
        catch (WEB3BaseRuntimeException l_ex)
        {
            l_response = 
                (WEB3AdminBondExecCancelConfirmResponse) l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request, 
                "��������m�F���������s���܂����B", 
                l_response.errorInfo, 
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * (�Ǘ��ҍ�������������N�G�X�g)<BR>
     * ������������������s���B <BR>
     * <BR>
     * �Ǘ��ҍ�������T�[�r�X���擾���Aexecute()���\�b�h���R�[������B
     * @@param l_request - (���N�G�X�g)<BR>
     * �Ǘ��ҍ�������������N�G�X�g
     * @@return WEB3AdminBondExecCancelCompleteResponse
     * @@roseuid 44B6FD2403DD
     */
    public WEB3AdminBondExecCancelCompleteResponse completeExecuteCancel(
        WEB3AdminBondExecCancelCompleteRequest l_request) 
    {
        final String STR_METHOD_NAME = 
            "completeExecuteCancel(WEB3AdminBondExecCancelCompleteRequest)";
        log.entering(STR_METHOD_NAME);

        //���Ǘ��Җ����o�^�T�[�r�X���擾��
        WEB3AdminBondExecuteCancelService l_service = null;
        WEB3AdminBondExecCancelCompleteResponse l_response = null;
        try
        {
            l_service =
                (WEB3AdminBondExecuteCancelService) 
                Services.getService(WEB3AdminBondExecuteCancelService.class);
        }
        // �T�[�r�X�ŗ�O�����������ꍇ�́A�G���[�������X�|���X���b�Z�[�W�ɐݒ肷��
        catch (Exception l_ex)
        {
            l_response =
                (WEB3AdminBondExecCancelCompleteResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "�Ǘ��ҍ�������T�[�r�X���擾�Ɏ��s���܂����B",
                l_response.errorInfo,
                l_ex);
            return l_response;
        }
        try
        {
            //execute()���\�b�h���R�[������
            l_response = (WEB3AdminBondExecCancelCompleteResponse) l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response =
                (WEB3AdminBondExecCancelCompleteResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request, 
                "��������������������s���܂����B", 
                l_ex.getErrorInfo(), 
                l_ex);
            return l_response;
        }
        catch (WEB3BaseRuntimeException l_ex)
        {
            l_response = 
                (WEB3AdminBondExecCancelCompleteResponse) l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request, 
                "��������������������s���܂����B", 
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
