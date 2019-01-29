head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.02;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3MutualCancelHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �����M������n���h���N���X(WEB3MutualCancelHandler)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/08/09 ������ (���u) �V�K�쐬
                   2004/08/20 ��O�� (���u) ���r���[    
*/

package webbroker3.mf.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.mf.message.WEB3MutualCancelCompleteRequest;
import webbroker3.mf.message.WEB3MutualCancelCompleteResponse;
import webbroker3.mf.message.WEB3MutualCancelConfirmRequest;
import webbroker3.mf.message.WEB3MutualCancelConfirmResponse;
import webbroker3.mf.service.delegate.WEB3MutualCancelService;
import webbroker3.util.WEB3LogUtility;

/**
 * �����M������n���h���N���X
 * 
 * @@author ������(���u)
 * @@version 1.0
 */
public class WEB3MutualCancelHandler implements MessageHandler
{
    /**
     * ���O���[�e�B���e�B
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3MutualCancelHandler.class);

    /**
     * (confirm���)<BR>
     * �����M���̎���R�����s���B<BR>
     * <BR>
     * �����M������T�[�r�X���擾���Aexecute()���\�b�h���R�[������B<BR>
     * @@param l_request - ���N�G�X�g�f�[�^
     * @@return webbroker3.mf.message.WEB3MutualCancelConfirmResponse
     * @@roseuid 4055775702CD
     */
    public WEB3MutualCancelConfirmResponse confirmCancel(WEB3MutualCancelConfirmRequest l_request)
        throws WEB3SystemLayerException
    {
        final String STR_METHOD_NAME =
            "confirmCancel(WEB3MutualCancelConfirmRequest l_request)";
        log.entering(STR_METHOD_NAME);

        WEB3MutualCancelService l_service = null;
        WEB3MutualCancelConfirmResponse l_response = null;

        try
        {
            l_service =
                (WEB3MutualCancelService) Services.getService(
                    WEB3MutualCancelService.class);
        }
        // �T�[�r�X�ŗ�O�����������ꍇ�́A�G���[�������X�|���X���b�Z�[�W�ɐݒ肷��
        catch (Exception l_ex)
        {
            l_response =
                (WEB3MutualCancelConfirmResponse) l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "�����M������T�[�r�X�̎擾�Ɏ��s���܂����B",
                l_response.errorInfo,
                l_ex);
            return l_response;
        }

        try
        {
            l_response =
                (WEB3MutualCancelConfirmResponse) l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response =
                (WEB3MutualCancelConfirmResponse) l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request,
                "�����M������R�����������s���܂����B",
                l_ex.getErrorInfo(),
                l_ex);
        }

        log.exiting(STR_METHOD_NAME);

        return l_response;
    }

    /**
     * (complete���)<BR>
     * �����M������o�^���s���B<BR>
     * <BR>
     * �����M������T�[�r�X���擾���Aexecute()���\�b�h���R�[������B<BR>
     * @@param l_request - ���N�G�X�g�f�[�^
     * @@return webbroker3.mf.message.WEB3MutualCancelCompleteResponse
     * @@roseuid 4055775F0137
     */
    public WEB3MutualCancelCompleteResponse completeCancel(WEB3MutualCancelCompleteRequest l_request)
        throws WEB3SystemLayerException
    {
        final String STR_METHOD_NAME =
            "completeCancel(WEB3MutualCancelCompleteRequest l_request)";
        log.entering(STR_METHOD_NAME);

        WEB3MutualCancelService l_service = null;
        WEB3MutualCancelCompleteResponse l_response = null;

        try
        {
            l_service =
                (WEB3MutualCancelService) Services.getService(
                    WEB3MutualCancelService.class);
        }
        // �T�[�r�X�ŗ�O�����������ꍇ�́A�G���[�������X�|���X���b�Z�[�W�ɐݒ肷��
        catch (Exception l_ex)
        {
            l_response =
                (WEB3MutualCancelCompleteResponse) l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "�����M������T�[�r�X�̎擾�Ɏ��s���܂����B",
                l_response.errorInfo,
                l_ex);
            return l_response;
        }

        try
        {
            l_response =
                (WEB3MutualCancelCompleteResponse) l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response =
                (WEB3MutualCancelCompleteResponse) l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request,
                "�����M������o�^���������s���܂����B",
                l_ex.getErrorInfo(),
                l_ex);
        }

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
}
@
