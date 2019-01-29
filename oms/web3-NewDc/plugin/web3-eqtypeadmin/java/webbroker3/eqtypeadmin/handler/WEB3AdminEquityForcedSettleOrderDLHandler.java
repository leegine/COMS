head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.15;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3AdminEquityForcedSettleOrderDLHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǘ��ҁE�������ϒ���DL�n���h���iWEB3AdminEquityForcedSettleOrderDLHandler.java)
Author Name      : Daiwa Institute of Research
Revision History : 2008/01/21 ���n(���u) �V�K�쐬���f��171
*/
package webbroker3.eqtypeadmin.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.eqtypeadmin.message.WEB3AdminForcedSettleDownloadInputRequest;
import webbroker3.eqtypeadmin.message.WEB3AdminForcedSettleDownloadInputResponse;
import webbroker3.eqtypeadmin.message.WEB3AdminForcedSettleDownloadRequest;
import webbroker3.eqtypeadmin.message.WEB3AdminForcedSettleDownloadResponse;
import webbroker3.eqtypeadmin.service.delegate.WEB3AdminEquityForcedSettleOrderDLService;
import webbroker3.util.WEB3LogUtility;

/**
 * (�Ǘ��ҁE�������ϒ���DL�n���h��)<BR>
 * �Ǘ��ҁE�������ϒ���DL�n���h���N���X<BR>
 * <BR>
 * @@author ���n
 * @@version 1.0
 */
public class WEB3AdminEquityForcedSettleOrderDLHandler implements MessageHandler
{
    /**
     * ���O���[�e�B���e�B<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminEquityForcedSettleOrderDLHandler.class);

    /**
     * @@roseuid 479031F802BF
     */
    public WEB3AdminEquityForcedSettleOrderDLHandler()
    {

    }

    /**
     * (get���͉��)<BR>
     * �������ϒ����_�E�����[�h���͉�ʕ\���������s���B<BR>
     * <BR>
     * �Ǘ��ҁE�������ϒ���DL�T�[�r�X���擾���Aexecute()���\�b�h���R�[������B<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * �Ǘ��ҁE�������ϒ����_�E�����[�h���̓��N�G�X�g<BR>
     * @@return WEB3AdminForcedSettleDownloadInputResponse
     * @@roseuid 476612F30309
     */
    public WEB3AdminForcedSettleDownloadInputResponse getInputScreen(
        WEB3AdminForcedSettleDownloadInputRequest l_request)
    {
        final String STR_METHOD_NAME = "getInputScreen(WEB3AdminForcedSettleDownloadInputRequest)";
        log.entering(STR_METHOD_NAME);

        WEB3AdminForcedSettleDownloadInputResponse l_response = null;
        WEB3AdminEquityForcedSettleOrderDLService l_forcedSettleOrderDLService = null;
        try
        {
            //�Ǘ��ҁE�������ϒ���DL�T�[�r�X���擾
            l_forcedSettleOrderDLService =
                (WEB3AdminEquityForcedSettleOrderDLService)Services.getService(
                    WEB3AdminEquityForcedSettleOrderDLService.class);
        }
        catch (Exception l_ex)
        {
            l_response = (WEB3AdminForcedSettleDownloadInputResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "�Ǘ��ҁE�������ϒ���DL�T�[�r�X���擾�Ɏ��s���܂����B",
                l_response.errorInfo,
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

        try
        {
            //execute()���\�b�h���R�[������
            l_response =
                (WEB3AdminForcedSettleDownloadInputResponse)l_forcedSettleOrderDLService.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response = (WEB3AdminForcedSettleDownloadInputResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request,
                "�������ϒ����_�E�����[�h���͉�ʕ\�����������s���܂����B",
                l_response.errorInfo,
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        catch (WEB3BaseRuntimeException l_ex)
        {
            l_response = (WEB3AdminForcedSettleDownloadInputResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request,
                "�������ϒ����_�E�����[�h���͉�ʕ\�����������s���܂����B",
                l_response.errorInfo,
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (get�_�E�����[�h�t�@@�C��)<BR>
     * �������ϒ����_�E�����[�h�t�@@�C���f�[�^�擾�������s���B<BR>
     * <BR>
     * �Ǘ��ҁE�������ϒ���DL�T�[�r�X���擾���Aexecute()���\�b�h���R�[������B<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * �Ǘ��ҁE�������ϒ����_�E�����[�h���N�G�X�g<BR>
     * @@return WEB3AdminForcedSettleDownloadResponse
     * @@roseuid 476612E202CA
     */
    public WEB3AdminForcedSettleDownloadResponse getDownloadFile(
        WEB3AdminForcedSettleDownloadRequest l_request)
    {
        final String STR_METHOD_NAME = "getDownloadFile(WEB3AdminForcedSettleDownloadRequest)";
        log.entering(STR_METHOD_NAME);

        WEB3AdminForcedSettleDownloadResponse l_response = null;
        WEB3AdminEquityForcedSettleOrderDLService l_forcedSettleOrderDLService = null;
        try
        {
            //�Ǘ��ҁE�������ϒ���DL�T�[�r�X���擾
            l_forcedSettleOrderDLService =
                (WEB3AdminEquityForcedSettleOrderDLService)Services.getService(
                    WEB3AdminEquityForcedSettleOrderDLService.class);
        }
        catch (Exception l_ex)
        {
            l_response = (WEB3AdminForcedSettleDownloadResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "�Ǘ��ҁE�������ϒ���DL�T�[�r�X���擾�Ɏ��s���܂����B",
                l_response.errorInfo,
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

        try
        {
            //execute()���\�b�h���R�[������
            l_response =
                (WEB3AdminForcedSettleDownloadResponse)l_forcedSettleOrderDLService.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response = (WEB3AdminForcedSettleDownloadResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request,
                "�������ϒ����_�E�����[�h�t�@@�C���f�[�^�擾���������s���܂����B",
                l_response.errorInfo,
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        catch (WEB3BaseRuntimeException l_ex)
        {
            l_response = (WEB3AdminForcedSettleDownloadResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request,
                "�������ϒ����_�E�����[�h�t�@@�C���f�[�^�擾���������s���܂����B",
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
