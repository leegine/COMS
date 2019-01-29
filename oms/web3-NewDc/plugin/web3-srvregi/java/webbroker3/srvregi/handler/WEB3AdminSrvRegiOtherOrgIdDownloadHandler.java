head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.06.44.30;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7984d7db5d91d86;
filename	WEB3AdminSrvRegiOtherOrgIdDownloadHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright           : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name           : �T�[�r�X���p�Ǘ��ҊO���A�gID�Ɖ��޳�۰�������(WEB3AdminSrvRegiOtherOrgIdDownloadHandler.java)
Author Name         : Daiwa Institute of Research
Revision History    : 2008/03/10 �đo�g(���u) �V�K�쐬 ���f��No.336
*/

package webbroker3.srvregi.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.srvregi.message.WEB3AdminSrvRegiOtherOrgIdDownloadRequest;
import webbroker3.srvregi.message.WEB3AdminSrvRegiOtherOrgIdDownloadResponse;
import webbroker3.srvregi.service.delegate.WEB3AdminSrvRegiOtherOrgIdDownloadService;
import webbroker3.util.WEB3LogUtility;

/**
 * (�T�[�r�X���p�Ǘ��ҊO���A�gID�Ɖ��޳�۰�������)<BR>
 * �T�[�r�X���p�Ǘ��ҊO���A�gID�Ɖ��޳�۰������׃N���X<BR>
 * <BR>
 * @@author �đo�g
 * @@version 1.0
 */
public class WEB3AdminSrvRegiOtherOrgIdDownloadHandler implements MessageHandler
{

    /**
     * ���O�o�̓��[�e�B���e�B
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminSrvRegiOtherOrgIdDownloadHandler.class);

    /**
     * @@roseuid 47D11137008F
     */
    public WEB3AdminSrvRegiOtherOrgIdDownloadHandler()
    {

    }

    /**
     * (�O���A�gID�Ɖ��޳�۰��)<BR>
     * �T�[�r�X���p�Ǘ��ҊO���A�gID�Ɖ��޳�۰�ޏ������s���B<BR>
     * <BR>
     * �T�[�r�X���p�Ǘ��ҊO���A�gID�Ɖ��޳�۰�ރT�[�r�X���擾���A<BR>
     * execute()���\�b�h���R�[������B<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * �T�[�r�X���p�Ǘ��ҊO���A�gID�Ɖ��޳�۰�ރ��N�G�X�g�@@�I�u�W�F�N�g<BR>
     * @@return WEB3AdminSrvRegiOtherOrgIdDownloadResponse
     * @@roseuid 47B944690201
     */
    public WEB3AdminSrvRegiOtherOrgIdDownloadResponse otherOrgIdDownload(
        WEB3AdminSrvRegiOtherOrgIdDownloadRequest l_request)
    {
        final String STR_METHOD_NAME =
            "otherOrgIdDownload(WEB3AdminSrvRegiOtherOrgIdDownloadRequest l_request)";
        log.entering(STR_METHOD_NAME);

        WEB3AdminSrvRegiOtherOrgIdDownloadResponse l_response = null;
        WEB3AdminSrvRegiOtherOrgIdDownloadService l_service = null;

        try
        {
            l_service = (WEB3AdminSrvRegiOtherOrgIdDownloadService)Services.getService(
                    WEB3AdminSrvRegiOtherOrgIdDownloadService.class);
        }
        catch (Exception l_ex)
        {
            l_response =
                (WEB3AdminSrvRegiOtherOrgIdDownloadResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(l_request,
                "�T�[�r�X���p�Ǘ��ҊO���A�gID�Ɖ��޳�۰�ރT�[�r�X���擾�Ɏ��s���܂����B",
                l_response.errorInfo,
                l_ex);

            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

        try
        {
            l_response =
                (WEB3AdminSrvRegiOtherOrgIdDownloadResponse)l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response =
                (WEB3AdminSrvRegiOtherOrgIdDownloadResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request, "�T�[�r�X���p�Ǘ��ҊO���A�gID�Ɖ�̎擾�ɃG���[���������܂����B", l_ex);

            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        catch (WEB3BaseRuntimeException l_ex)
        {
            l_response =
                (WEB3AdminSrvRegiOtherOrgIdDownloadResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request,
                "�T�[�r�X���p�Ǘ��ҊO���A�gID�Ɖ�̎擾�ɃG���[���������܂����B",
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
