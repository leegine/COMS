head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.04.24;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	WEB3PLSProfitLossSpecsHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : ���v���׏Ɖ�n���h��(WEB3PLSProfitLossSpecsHandler.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/11/10 �͌d�� (���u) �V�K�쐬
                 : 2006/10/19  ��іQ (���u) ���f��056
*/

package webbroker3.tradehistory.handler;

import com.fitechlabs.xtrade.kernel.handler.MessageHandler;
import com.fitechlabs.xtrade.kernel.boot.Services;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.tradehistory.message.WEB3PLSProfitLossDownloadRequest;
import webbroker3.tradehistory.message.WEB3PLSProfitLossDownloadResponse;
import webbroker3.tradehistory.message.WEB3PLSProfitLossSpecsRequest;
import webbroker3.tradehistory.message.WEB3PLSProfitLossSpecsResponse;
import webbroker3.tradehistory.service.delegate.WEB3PLSProfitLossSpecsService;
import webbroker3.util.WEB3LogUtility;

/**
 * (���v���׏Ɖ�n���h��)<BR>
 * ���v���׏Ɖ�n���h���N���X<BR>
 *
 * @@author �͌d��
 * @@version 1.0
 */
public class WEB3PLSProfitLossSpecsHandler implements MessageHandler
{
    /**
     *���O�o�̓��[�e�B���e�B�B
     */
    private WEB3LogUtility log = WEB3LogUtility.getInstance(WEB3PLSProfitLossSpecsHandler.class);

    /**
     * @@roseuid 418877BD0213
     */
    public WEB3PLSProfitLossSpecsHandler()
    {

    }

    /**
     * (get���v���׏Ɖ�)<BR>
     * ���v���׏Ɖ�����s���B<BR>
     * <BR>
     * ���v���׏Ɖ�T�[�r�XImpl���擾���A<BR>
     * execute()���\�b�h���R�[������B<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * ���v���׏Ɖ�N�G�X�g�I�u�W�F�N�g<BR>
     * @@return webbroker3.plsbvs.message.WEB3PLSProfitLossSpecsResponse
     * @@roseuid 416CFCCE0333
     */
    public WEB3PLSProfitLossSpecsResponse getProfitLossSpecs(WEB3PLSProfitLossSpecsRequest l_request)
    {
        final String STR_METHOD_NAME = " getProfitLossSpecs(WEB3PLSProfitLossSpecsRequest l_request)";
        log.entering(STR_METHOD_NAME);

        WEB3PLSProfitLossSpecsResponse l_response = null;
        WEB3PLSProfitLossSpecsService l_service = null;

        //���v���׏Ɖ�T�[�r�XImpl���擾��
        try
        {
            l_service = (WEB3PLSProfitLossSpecsService)Services.getService(WEB3PLSProfitLossSpecsService.class);
        }
        catch (Exception l_ex)
        {
            l_response = (WEB3PLSProfitLossSpecsResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(l_request, "���v���׏Ɖ�T�[�r�XImpl�̎擾�Ɏ��s���܂���", l_response.errorInfo, l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

        try
        {
            //execute()���\�b�h���R�[������
            l_response = (WEB3PLSProfitLossSpecsResponse)l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response =
                (WEB3PLSProfitLossSpecsResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request,
                "���v���׏Ɖ���Ɏ��s���܂���",
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

        log.exiting(STR_METHOD_NAME);
        return l_response;

    }

    /**
     * (get���v���׃t�@@�C���_�E�����[�h)<BR>
     * ���v���׃t�@@�C���_�E�����[�h�������s���B<BR>
     * <BR>
     * ���v���׏Ɖ�T�[�r�XImpl���擾���A<BR>
     * execute()���\�b�h���R�[������B<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * ���v���׃t�@@�C���_�E�����[�h���N�G�X�g<BR>
     * @@return WEB3PLSProfitLossDownloadResponse
     */
    public WEB3PLSProfitLossDownloadResponse getProfitLossDownload(
        WEB3PLSProfitLossDownloadRequest l_request)
    {
        final String STR_METHOD_NAME = "getProfitLossDownload(WEB3PLSProfitLossDownloadRequest l_request)";
        log.entering(STR_METHOD_NAME);

        WEB3PLSProfitLossDownloadResponse l_response = null;
        WEB3PLSProfitLossSpecsService l_service = null;

        //���v���׏Ɖ�T�[�r�XImpl���擾��
        try
        {
            l_service = (WEB3PLSProfitLossSpecsService)Services.getService(WEB3PLSProfitLossSpecsService.class);
        }
        catch (Exception l_ex)
        {
            l_response = (WEB3PLSProfitLossDownloadResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "���v���׏Ɖ�T�[�r�XImpl�̎擾�Ɏ��s���܂���",
                l_response.errorInfo,
                l_ex);

            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

        try
        {
            //execute()���\�b�h���R�[������
            l_response = (WEB3PLSProfitLossDownloadResponse)l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response =
                (WEB3PLSProfitLossDownloadResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request,
                "execute()���\�b�h���R�[�����邱�Ƃ����s���܂���",
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

}
@
