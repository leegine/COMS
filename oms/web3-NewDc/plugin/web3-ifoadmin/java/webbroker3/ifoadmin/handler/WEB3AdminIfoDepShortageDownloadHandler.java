head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.17;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3AdminIfoDepShortageDownloadHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǘ��ҁE�؋����s���󋵃_�E�����[�h�n���h��(WEB3AdminIfoDepShortageDownloadHandler.java)
Author Name      : Daiwa Institute of Research
Revision History : 2009/02/27�@@����(���u) �V�K�쐬 ���f��No.006
*/
package webbroker3.ifoadmin.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.ifoadmin.message.WEB3AdminIfoDepShortageDownloadRequest;
import webbroker3.ifoadmin.message.WEB3AdminIfoDepShortageDownloadResponse;
import webbroker3.ifoadmin.service.delegate.WEB3AdminIfoDepShortageDownloadService;
import webbroker3.util.WEB3LogUtility;

/**
 * (�Ǘ��ҁE�؋����s���󋵃_�E�����[�h�n���h��)<BR>
 * �Ǘ��ҁE�؋����s���󋵃_�E�����[�h�n���h���N���X <BR>
 * <BR>
 * @@author ����
 * @@version 1.0
 */
public class WEB3AdminIfoDepShortageDownloadHandler implements MessageHandler
{
    /**
     * ���O���[�e�B���e�B<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminIfoDepShortageDownloadHandler.class);

    /**
     * @@roseuid 49A76E520242
     */
    public WEB3AdminIfoDepShortageDownloadHandler()
    {

    }

    /**
     * (get�_�E�����[�h�t�@@�C��)<BR>
     * �Ǘ��ҁE�؋����s���󋵃_�E�����[�h�t�@@�C���f�[�^�擾�������s���B <BR>
     * �Ǘ��ҁE�؋����s���󋵃_�E�����[�h�T�[�r�X���擾���Aexecute()���\�b�h���R�[������B<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * ���N�G�X�g�f�[�^<BR>
     * @@return WEB3AdminIfoDepShortageDownloadResponse
     * @@roseuid 499B570D006A
     */
    public WEB3AdminIfoDepShortageDownloadResponse getDownloadFile(WEB3AdminIfoDepShortageDownloadRequest l_request)
    {
        final String STR_METHOD_NAME = "getDownloadFile(WEB3AdminIfoDepShortageDownloadRequest)";
        log.entering(STR_METHOD_NAME);

        WEB3AdminIfoDepShortageDownloadResponse l_response = null;
        WEB3AdminIfoDepShortageDownloadService l_service = null;

        //�Ǘ��ҁE�؋����s���󋵃_�E�����[�h�T�[�r�X���擾
        try
        {
            l_service = (WEB3AdminIfoDepShortageDownloadService)Services.getService(
                WEB3AdminIfoDepShortageDownloadService.class);
        }
        catch (Exception l_ex)
        {
            l_response = (WEB3AdminIfoDepShortageDownloadResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "�Ǘ��ҁE�؋����s���󋵃_�E�����[�h�T�[�r�X���擾�Ɏ��s���܂����B",
                l_response.errorInfo,
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

        //execute()���\�b�h���R�[������
        try
        {
            l_response =
                (WEB3AdminIfoDepShortageDownloadResponse)l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response = (WEB3AdminIfoDepShortageDownloadResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request,
                "�Ǘ��ҁE�؋����s���󋵃_�E�����[�h�t�@@�C���f�[�^�擾�����Ɏ��s���܂����B",
                l_response.errorInfo,
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        catch (WEB3BaseRuntimeException l_ex)
        {
            l_response = (WEB3AdminIfoDepShortageDownloadResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request,
                "�Ǘ��ҁE�؋����s���󋵃_�E�����[�h�t�@@�C���f�[�^�擾�����Ɏ��s���܂����B",
                l_response.errorInfo,
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
}@
