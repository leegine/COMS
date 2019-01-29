head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.03.46.48;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	WEB3AdminAccOpenDataTransferHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǘ��Ҍ����J�݃f�[�^�ڊǃn���h��(WEB3AdminAccOpenDataTransferHandler.java)
Author Name      : Daiwa Institute of Research
Revision History : 2009/08/18 �И���(���u) �V�K�쐬 ���f�� 181
*/
package webbroker3.accountopen.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.accountopen.message.WEB3AdminAccOpenDataTransferCompleteRequest;
import webbroker3.accountopen.message.WEB3AdminAccOpenDataTransferCompleteResponse;
import webbroker3.accountopen.message.WEB3AdminAccOpenDataTransferInputRequest;
import webbroker3.accountopen.message.WEB3AdminAccOpenDataTransferInputResponse;
import webbroker3.accountopen.service.delegate.WEB3AdminAccOpenDataTransferService;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.util.WEB3LogUtility;

/**
 * (�Ǘ��Ҍ����J�݃f�[�^�ڊǃn���h��)<BR>
 * �Ǘ��Ҍ����J�݃f�[�^�ڊǃn���h���N���X<BR>
 * <BR>
 * @@author �И���
 * @@version 1.0
 */
public class WEB3AdminAccOpenDataTransferHandler implements MessageHandler
{
    /**
     * (���O�o�̓��[�e�B���e�B�B)<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminAccOpenDataTransferHandler.class);

    /**
     * @@roseuid 4A8A083401D4
     */
    public WEB3AdminAccOpenDataTransferHandler()
    {

    }

    /**
     * (get���͉��)<BR>
     * ���͉�ʂ̕\�����s���B <BR>
     * <BR>
     * �Ǘ��Ҍ����J�݃f�[�^�ڊǃT�[�r�X���擾���Aexecute()���\�b�h���R�[������B <BR>
     * <BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * ���N�G�X�g�f�[�^<BR>
     * @@return WEB3AdminAccOpenDataTransferInputResponse
     * @@roseuid 4A821F5A03D8
     */
    public WEB3AdminAccOpenDataTransferInputResponse getInputScreen(
        WEB3AdminAccOpenDataTransferInputRequest l_request)
    {
        final String STR_METHOD_NAME = "getInputScreen(WEB3AdminAccOpenDataTransferInputRequest)";
        log.entering(STR_METHOD_NAME);

        WEB3AdminAccOpenDataTransferInputResponse l_response = null;
        WEB3AdminAccOpenDataTransferService l_service = null;

        try
        {
            l_service = (WEB3AdminAccOpenDataTransferService)Services.getService(
                WEB3AdminAccOpenDataTransferService.class);
        }
        //�T�[�r�X�ŗ�O�����������ꍇ�́A�G���[�������X�|���X���b�Z�[�W�ɐݒ肷��
        catch (Exception l_ex)
        {
            l_response = (WEB3AdminAccOpenDataTransferInputResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "�Ǘ��Ҍ����J�݃f�[�^�ڊǃT�[�r�X�̎擾�Ɏ��s���܂����B",
                l_response.errorInfo,
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

        try
        {
            //execute
            l_response = (WEB3AdminAccOpenDataTransferInputResponse)l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response = (WEB3AdminAccOpenDataTransferInputResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request,
                "�Ǘ��Ҍ����J�݃f�[�^�ڊǓ��͉�ʂ̕\�����������s���܂����B",
                l_response.errorInfo,
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        catch (WEB3BaseRuntimeException l_ex)
        {
            l_response = (WEB3AdminAccOpenDataTransferInputResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request,
               "�Ǘ��Ҍ����J�݃f�[�^�ڊǓ��͉�ʂ̕\�����������s���܂����B",
                l_response.errorInfo,
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (submit�f�[�^�ڊ�)<BR>
     * �f�[�^�ڊǂ̏������s���B <BR>
     * <BR>
     * �Ǘ��Ҍ����J�݃f�[�^�ڊǃT�[�r�X���擾���Aexecute()���\�b�h���R�[������B <BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * ���N�G�X�g�f�[�^<BR>
     * @@return WEB3AdminAccOpenDataTransferCompleteResponse
     * @@roseuid 4A821F5C0399
     */
    public WEB3AdminAccOpenDataTransferCompleteResponse submitDataTransfer(
        WEB3AdminAccOpenDataTransferCompleteRequest l_request)
    {
        final String STR_METHOD_NAME = "submitDataTransfer(WEB3AdminAccOpenDataTransferCompleteRequest)";
        log.entering(STR_METHOD_NAME);

        WEB3AdminAccOpenDataTransferCompleteResponse l_response = null;
        WEB3AdminAccOpenDataTransferService l_service = null;

        try
        {
            l_service = (WEB3AdminAccOpenDataTransferService)Services.getService(
                WEB3AdminAccOpenDataTransferService.class);
        }
        //�T�[�r�X�ŗ�O�����������ꍇ�́A�G���[�������X�|���X���b�Z�[�W�ɐݒ肷��
        catch (Exception l_ex)
        {
            l_response = (WEB3AdminAccOpenDataTransferCompleteResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "�Ǘ��Ҍ����J�݃f�[�^�ڊǃT�[�r�X�̎擾�Ɏ��s���܂����B",
                l_response.errorInfo,
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

        try
        {
            //execute
            l_response = (WEB3AdminAccOpenDataTransferCompleteResponse)l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response = (WEB3AdminAccOpenDataTransferCompleteResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request,
                "�Ǘ��Ҍ����J�݃f�[�^�ڊǏ��������s���܂����B",
                l_response.errorInfo,
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        catch (WEB3BaseRuntimeException l_ex)
        {
            l_response = (WEB3AdminAccOpenDataTransferCompleteResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request,
               "�Ǘ��Ҍ����J�݃f�[�^�ڊǏ��������s���܂����B",
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
