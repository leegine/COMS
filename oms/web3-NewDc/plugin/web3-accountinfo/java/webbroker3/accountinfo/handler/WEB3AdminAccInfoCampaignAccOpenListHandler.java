head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.10.02.14.00;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8644d782f8a12fc;
filename	WEB3AdminAccInfoCampaignAccOpenListHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �����J�ݏ����w��ꗗ�n���h�� (WEB3AdminAccInfoCampaignAccOpenListHandler.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/02/01 ���؎q (���u) �V�K�쐬
*/
package webbroker3.accountinfo.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.accountinfo.message.WEB3AdminAccInfoCampaignAccOpenListRequest;
import webbroker3.accountinfo.message.WEB3AdminAccInfoCampaignAccOpenListResponse;
import webbroker3.accountinfo.service.delegate.WEB3AdminAccInfoCampaignAccOpenListService;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.util.WEB3LogUtility;

/**
 * (�����J�ݏ����w��ꗗ�n���h��)<BR>
 * �����J�ݏ����w��ꗗ�n���h��<BR>
 * <BR>
 * @@author ���؎q<BR>
 * @@version 1.0<BR>
 */
public class WEB3AdminAccInfoCampaignAccOpenListHandler implements MessageHandler
{
    /**
     * Log Variable
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminAccInfoCampaignAccOpenListHandler.class);

    /**
     * @@roseuid 45C08B5101D4
     */
    public WEB3AdminAccInfoCampaignAccOpenListHandler()
    {

    }

    /**
     * (�ꗗ��ʕ\������)<BR>
     * �����J�ݏ����w��ꗗ�\�����������{����B<BR>
     * <BR>
     * �����J�ݏ����w��ꗗ�T�[�r�X���擾���A<BR>
     * execute()���\�b�h���R�[������B<BR>
     * @@param l_request - �Ǘ��҂��q�l���萔�������L�����y�[��<BR>
     * �����J�ݏ����ꗗ���N�G�X�g�I�u�W�F�N�g<BR>
     *
     * @@return WEB3AdminAccInfoCampaignAccOpenListResponse
     * @@throws WEB3BaseException
     * @@roseuid 45AB14AC027B
     */
    public WEB3AdminAccInfoCampaignAccOpenListResponse getListScreen(
        WEB3AdminAccInfoCampaignAccOpenListRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = ".getListScreen(l_request)";
        log.entering(STR_METHOD_NAME);

        WEB3AdminAccInfoCampaignAccOpenListResponse l_response = null;
        WEB3AdminAccInfoCampaignAccOpenListService l_service = null;

        try
        {
            // �ʌڋq�w��ꗗ�T�[�r�X
            l_service = (WEB3AdminAccInfoCampaignAccOpenListService)
                Services.getService(WEB3AdminAccInfoCampaignAccOpenListService.class);
        }
        catch (Exception l_ex)
        {
            l_response = (WEB3AdminAccInfoCampaignAccOpenListResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "�����J�ݏ����w��ꗗ�T�[�r�X�̎擾�Ɏ��s���܂����B",
                l_response.errorInfo,
                l_ex);
            return l_response;
        }

        try
        {
            l_response = (WEB3AdminAccInfoCampaignAccOpenListResponse)l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response = (WEB3AdminAccInfoCampaignAccOpenListResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request, "�����J�ݏ����w��ꗗ�T�[�r�X�̎擾�Ɏ��s���܂����B", l_ex);
            return l_response;
        }

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
}
@
