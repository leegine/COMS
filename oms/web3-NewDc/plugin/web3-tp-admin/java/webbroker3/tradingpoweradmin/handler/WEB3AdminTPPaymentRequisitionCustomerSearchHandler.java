head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.08.05;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	WEB3AdminTPPaymentRequisitionCustomerSearchHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : ���������ڋq�����n���h��(WEB3AdminTPPaymentRequisitionCustomerSearchHandler.java)
Author Name      : Daiwa Institute of Research
Revision History : 2008/10/06 ���z(���u) �V�K�쐬 ���f��No.027
*/

package webbroker3.tradingpoweradmin.handler;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.tradingpoweradmin.message.WEB3AdminTPPaymentRequisitionDetailRequest;
import webbroker3.tradingpoweradmin.message.WEB3AdminTPPaymentRequisitionDetailResponse;
import webbroker3.tradingpoweradmin.message.WEB3AdminTPPaymentRequisitionDownLoadRequest;
import webbroker3.tradingpoweradmin.message.WEB3AdminTPPaymentRequisitionDownLoadResponse;
import webbroker3.tradingpoweradmin.message.WEB3AdminTPPaymentRequisitionInputRequest;
import webbroker3.tradingpoweradmin.message.WEB3AdminTPPaymentRequisitionInputResponse;
import webbroker3.tradingpoweradmin.message.WEB3AdminTPPaymentRequisitionListRequest;
import webbroker3.tradingpoweradmin.message.WEB3AdminTPPaymentRequisitionListResponse;
import webbroker3.tradingpoweradmin.service.delegate.WEB3AdminTPPaymentRequisitionCustomerSearchService;
import webbroker3.util.WEB3LogUtility;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

/**
 * (���������ڋq�����n���h��)<BR>
 * <BR>
 * @@author ���z
 * @@version 1.0
 */
public class WEB3AdminTPPaymentRequisitionCustomerSearchHandler implements MessageHandler
{
    /**
     * ���O���[�e�B���e�B<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminTPPaymentRequisitionCustomerSearchHandler.class);

    /**
     * @@roseuid 48E9E2530055
     */
    public WEB3AdminTPPaymentRequisitionCustomerSearchHandler()
    {

    }

    /**
     * (get���������ڋq��������)<BR>
     * @@roseuid 48CA07E40070
     * @@return WEB3AdminTPPaymentRequisitionInputResponse
     */
    public WEB3AdminTPPaymentRequisitionInputResponse getPaymentRequisitionCustomerSearchInput(
        WEB3AdminTPPaymentRequisitionInputRequest l_request)
    {
        final String STR_METHOD_NAME =
            "getPaymentRequisitionCustomerSearchInput(WEB3AdminTPPaymentRequisitionInputRequest)";
        log.entering(STR_METHOD_NAME);

        WEB3AdminTPPaymentRequisitionCustomerSearchService l_service = null;
        WEB3AdminTPPaymentRequisitionInputResponse l_response = null;

        try
        {
            //���������ڋq�����T�[�r�XImpl���擾
            l_service =
                (WEB3AdminTPPaymentRequisitionCustomerSearchService)Services.getService(
                    WEB3AdminTPPaymentRequisitionCustomerSearchService.class);
        }
        catch (Exception l_ex)
        {
            l_response =
                (WEB3AdminTPPaymentRequisitionInputResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;

            log.error(
                l_request,
                "���������ڋq�����T�[�r�XImpl�̎擾�Ɏ��s���܂����B",
                l_response.errorInfo,
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

        try
        {
            //execute()���\�b�h���R�[������B
            l_response =
                (WEB3AdminTPPaymentRequisitionInputResponse)l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response =
                (WEB3AdminTPPaymentRequisitionInputResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request, "���������ڋq�������͕\�������̎��{�Ɏ��s���܂����B", l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        catch (WEB3BaseRuntimeException l_ex)
        {
            l_response =
                (WEB3AdminTPPaymentRequisitionInputResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request, "���������ڋq�������͕\�������̎��{�Ɏ��s���܂����B", l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (get���������ڋq�����ꗗ)<BR>
     * @@roseuid 48CA080B02A2
     * @@return WEB3AdminTPPaymentRequisitionListResponse
     */
    public WEB3AdminTPPaymentRequisitionListResponse getPaymentRequisitionCustomerSearchList(
        WEB3AdminTPPaymentRequisitionListRequest l_request)
    {
        final String STR_METHOD_NAME =
            "getPaymentRequisitionCustomerSearchList(WEB3AdminTPPaymentRequisitionListRequest)";
        log.entering(STR_METHOD_NAME);

        WEB3AdminTPPaymentRequisitionCustomerSearchService l_service = null;
        WEB3AdminTPPaymentRequisitionListResponse l_response = null;

        try
        {
            //���������ڋq�����T�[�r�XImpl���擾
            l_service =
                (WEB3AdminTPPaymentRequisitionCustomerSearchService)Services.getService(
                    WEB3AdminTPPaymentRequisitionCustomerSearchService.class);
        }
        catch (Exception l_ex)
        {
            l_response =
                (WEB3AdminTPPaymentRequisitionListResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;

            log.error(
                l_request,
                "���������ڋq�����T�[�r�XImpl�̎擾�Ɏ��s���܂����B",
                l_response.errorInfo,
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

        try
        {
            //execute()���\�b�h���R�[������B
            l_response =
                (WEB3AdminTPPaymentRequisitionListResponse)l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response =
                (WEB3AdminTPPaymentRequisitionListResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request, "���������ڋq�����ꗗ�\�������̎��{�Ɏ��s���܂����B", l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        catch (WEB3BaseRuntimeException l_ex)
        {
            l_response =
                (WEB3AdminTPPaymentRequisitionListResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request, "���������ڋq�����ꗗ�\�������̎��{�Ɏ��s���܂����B", l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (get���������ڋq�����ڍ�)<BR>
     * @@roseuid 48CA080403AB
     * @@return WEB3AdminTPPaymentRequisitionDetailResponse
     */
    public WEB3AdminTPPaymentRequisitionDetailResponse getPaymentRequisitionCustomerSearchDetail(
        WEB3AdminTPPaymentRequisitionDetailRequest l_request)
    {
        final String STR_METHOD_NAME =
            "getPaymentRequisitionCustomerSearchDetail(WEB3AdminTPPaymentRequisitionDetailRequest)";
        log.entering(STR_METHOD_NAME);

        WEB3AdminTPPaymentRequisitionCustomerSearchService l_service = null;
        WEB3AdminTPPaymentRequisitionDetailResponse l_response = null;

        try
        {
            //���������ڋq�����T�[�r�XImpl���擾
            l_service =
                (WEB3AdminTPPaymentRequisitionCustomerSearchService)Services.getService(
                    WEB3AdminTPPaymentRequisitionCustomerSearchService.class);
        }
        catch (Exception l_ex)
        {
            l_response =
                (WEB3AdminTPPaymentRequisitionDetailResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;

            log.error(
                l_request,
                "���������ڋq�����T�[�r�XImpl�̎擾�Ɏ��s���܂����B",
                l_response.errorInfo,
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

        try
        {
            //execute()���\�b�h���R�[������B
            l_response =
                (WEB3AdminTPPaymentRequisitionDetailResponse)l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response =
                (WEB3AdminTPPaymentRequisitionDetailResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request, "���������ڋq�����ڍו\�������̎��{�Ɏ��s���܂����B", l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        catch (WEB3BaseRuntimeException l_ex)
        {
            l_response =
                (WEB3AdminTPPaymentRequisitionDetailResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request, "���������ڋq�����ڍו\�������̎��{�Ɏ��s���܂����B", l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (get���������ڋq�����_�E�����[�h)<BR>
     * @@roseuid 48CA081A01B7
     * @@return WEB3AdminTPPaymentRequisitionDownLoadResponse
     */
    public WEB3AdminTPPaymentRequisitionDownLoadResponse getPaymentRequisitionCustomerSearchDownLoad(
        WEB3AdminTPPaymentRequisitionDownLoadRequest l_request)
    {
        final String STR_METHOD_NAME =
            "getPaymentRequisitionCustomerSearchDownLoad(WEB3AdminTPPaymentRequisitionDownLoadRequest)";
        log.entering(STR_METHOD_NAME);

        WEB3AdminTPPaymentRequisitionCustomerSearchService l_service = null;
        WEB3AdminTPPaymentRequisitionDownLoadResponse l_response = null;

        try
        {
            //���������ڋq�����T�[�r�XImpl���擾
            l_service =
                (WEB3AdminTPPaymentRequisitionCustomerSearchService)Services.getService(
                    WEB3AdminTPPaymentRequisitionCustomerSearchService.class);
        }
        catch (Exception l_ex)
        {
            l_response =
                (WEB3AdminTPPaymentRequisitionDownLoadResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;

            log.error(
                l_request,
                "���������ڋq�����T�[�r�XImpl�̎擾�Ɏ��s���܂����B",
                l_response.errorInfo,
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

        try
        {
            //execute()���\�b�h���R�[������B
            l_response =
                (WEB3AdminTPPaymentRequisitionDownLoadResponse)l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response =
                (WEB3AdminTPPaymentRequisitionDownLoadResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request, "���������ڋq�����_�E�����[�h�\�������̎��{�Ɏ��s���܂����B", l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        catch (WEB3BaseRuntimeException l_ex)
        {
            l_response =
                (WEB3AdminTPPaymentRequisitionDownLoadResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request, "���������ڋq�����_�E�����[�h�\�������̎��{�Ɏ��s���܂����B", l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
}
@
