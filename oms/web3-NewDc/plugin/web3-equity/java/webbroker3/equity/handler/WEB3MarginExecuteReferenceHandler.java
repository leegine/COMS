head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.10;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3MarginExecuteReferenceHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �M�p����������Ɖ�n���h���N���X(WEB3MarginExecuteReferenceHandler.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/9/21 䈋� (���u) �V�K�쐬
                   2005/01/06 �����a��(SRA) JavaDoc�C��
*/
package webbroker3.equity.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.equity.message.WEB3MarginCloseMarginContractListRequest;
import webbroker3.equity.message.WEB3MarginCloseMarginContractListResponse;
import webbroker3.equity.message.WEB3MarginExecuteDetailsRequest;
import webbroker3.equity.message.WEB3MarginExecuteDetailsResponse;
import webbroker3.equity.message.WEB3MarginExecuteReferenceRequest;
import webbroker3.equity.message.WEB3MarginExecuteReferenceResponse;
import webbroker3.equity.message.WEB3MarginOrderHistoryRequest;
import webbroker3.equity.message.WEB3MarginOrderHistoryResponse;
import webbroker3.equity.service.delegate.WEB3MarginExecuteReferenceService;
import webbroker3.util.WEB3LogUtility;

/**
 * �i�M�p����������Ɖ�n���h���j�B<BR>
 * <BR>
 * �M�p����������Ɖ�n���h���N���X
 * @@author 䈋�
 * @@version 1.0
 */
public class WEB3MarginExecuteReferenceHandler implements MessageHandler
{
    /**
     * (���O�o�̓��[�e�B���e�B�B)<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3MarginExecuteReferenceHandler.class);
    /**
     * @@roseuid 414184C501D3
     */
    public WEB3MarginExecuteReferenceHandler()
    {

    }

    /**
     * (search�������Ɖ�)<BR>
     * �M�p����������Ɖ�����s��<BR>
     * <BR>
     * �M�p����������Ɖ�T�[�r�X���擾���Aexecute()���\�b�h���R�[������B<BR>
     * <BR>
     * @@param l_request - ���N�G�X�g�f�[�^<BR>
     * �M�p����������Ɖ�N�G�X�g�f�[�^�I�u�W�F�N�g<BR>
     * <BR>
     * @@return WEB3MarginExecuteReferenceResponse
     * @@roseuid 40582D750126
     */
    public WEB3MarginExecuteReferenceResponse searchOrderExecuteReference(WEB3MarginExecuteReferenceRequest l_request)
    {
        //�M�p����������Ɖ�T�[�r�X�I�u�W�F�N�g���擾����
        final String STR_METHOD_NAME =
            "searchOrderExecuteReference(WEB3MarginExecuteReferenceRequest)";

        log.entering(STR_METHOD_NAME);

        WEB3MarginExecuteReferenceResponse l_response = null;
        WEB3MarginExecuteReferenceService l_service = null;

        //�M�p����������Ɖ�T�[�r�X�I�u�W�F�N�g.execute�i�j���R�[�����A
        //�������ʂł��郌�X�|���X�I�u�W�F�N�g���擾����B
        try
        {
            l_service =
                (WEB3MarginExecuteReferenceService)Services.getService(
                    WEB3MarginExecuteReferenceService.class);
        }
        //�T�[�r�X�ŗ�O�����������ꍇ�́A�G���[�������X�|���X���b�Z�[�W�ɐݒ肷��
        catch (Exception l_ex)
        {
            l_response =
                (WEB3MarginExecuteReferenceResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "�M�p����������Ɖ�T�[�r�X�̎擾�Ɏ��s���܂����B",
                l_response.errorInfo,
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

        try
        {
            l_response =
                (WEB3MarginExecuteReferenceResponse)l_service.execute(
                    l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response =
                (WEB3MarginExecuteReferenceResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request, "�M�p����������Ɖ�Ɏ��s���܂����B", l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        catch (WEB3BaseRuntimeException l_bre)
        {
            l_response =
                (WEB3MarginExecuteReferenceResponse)l_request.createResponse();
            l_response.errorInfo = l_bre.getErrorInfo();
            log.error(l_request, "�M�p����������Ɖ�Ɏ��s���܂����B", l_bre);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

        log.exiting(STR_METHOD_NAME);

        //���X�|���X�I�u�W�F�N�g��ԋp����B
        return l_response;
    }

    /**
     * (search�������ڍ�)<BR>
     * �M�p����������ڍ׏������s��<BR>
     * <BR>
     * �M�p����������Ɖ�T�[�r�X���擾���Aexecute()���\�b�h���R�[������B<BR>
     * <BR>
     * @@param l_request - ���N�G�X�g�f�[�^<BR>
     * �M�p����������ڍ׃��N�G�X�g�f�[�^�I�u�W�F�N�g<BR>
     * 
     * @@return WEB3MarginExecuteDetailsResponse
     * @@roseuid 40FC9F2B03B6
     */
    public WEB3MarginExecuteDetailsResponse searchOrderExecuteDetails(WEB3MarginExecuteDetailsRequest l_request)
    {

        //�M�p����������ڍ׃T�[�r�X�I�u�W�F�N�g���擾����
        final String STR_METHOD_NAME =
            "searchOrderExecuteDetails(WEB3MarginExecuteDetailsRequest) ";

        log.entering(STR_METHOD_NAME);

        WEB3MarginExecuteDetailsResponse l_response = null;
        WEB3MarginExecuteReferenceService l_service = null;

        //�M�p����������ڍ׃T�[�r�X�I�u�W�F�N�g.execute�i�j���R�[�����A
        //�������ʂł��郌�X�|���X�I�u�W�F�N�g���擾����B
        try
        {
            l_service =
                (WEB3MarginExecuteReferenceService)Services.getService(
                    WEB3MarginExecuteReferenceService.class);
        }
        //�T�[�r�X�ŗ�O�����������ꍇ�́A�G���[�������X�|���X���b�Z�[�W�ɐݒ肷��
        catch (Exception l_ex)
        {
            l_response =
                (WEB3MarginExecuteDetailsResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "�M�p����������ڍ׃T�[�r�X�̎擾�Ɏ��s���܂����B",
                l_response.errorInfo,
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

        try
        {
            l_response =
                (WEB3MarginExecuteDetailsResponse)l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response =
                (WEB3MarginExecuteDetailsResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request, "�M�p����������ڍׂɎ��s���܂����B", l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        catch (WEB3BaseRuntimeException l_bre)
        {
            l_response =
                (WEB3MarginExecuteDetailsResponse)l_request.createResponse();
            l_response.errorInfo = l_bre.getErrorInfo();
            log.error(l_request, "�M�p����������ڍׂɎ��s���܂����B", l_bre);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

        log.exiting(STR_METHOD_NAME);

        //���X�|���X�I�u�W�F�N�g��ԋp����B
        return l_response;
    }

    /**
     * (search���������Ɖ�)<BR>
     * �M�p������������Ɖ�����s��<BR>
     * <BR>
     * �M�p����������Ɖ�T�[�r�X���擾���Aexecute()���\�b�h���R�[������B<BR>
     * @@param l_request  - ���N�G�X�g�f�[�^<BR>
     * �M�p������������Ɖ�N�G�X�g�f�[�^�I�u�W�F�N�g<BR>
     * 
     * @@return WEB3MarginOrderHistoryResponse
     * @@roseuid 40FC9F430200
     */
    public WEB3MarginOrderHistoryResponse searchOrderHistoryInquiry(WEB3MarginOrderHistoryRequest l_request)
    {
        // �M�p������������Ɖ�T�[�r�X�I�u�W�F�N�g���擾����
        final String STR_METHOD_NAME =
            "searchOrderHistoryInquiry(WEB3MarginOrderHistoryRequest)";

        log.entering(STR_METHOD_NAME);

        WEB3MarginOrderHistoryResponse l_response = null;
        WEB3MarginExecuteReferenceService l_service = null;

        //�M�p������������Ɖ�T�[�r�X�I�u�W�F�N�g.execute�i�j���R�[�����A
        //�������ʂł��郌�X�|���X�I�u�W�F�N�g���擾����B
        try
        {
            l_service =
                (WEB3MarginExecuteReferenceService)Services.getService(
                    WEB3MarginExecuteReferenceService.class);
        }
        //�T�[�r�X�ŗ�O�����������ꍇ�́A�G���[�������X�|���X���b�Z�[�W�ɐݒ肷��
        catch (Exception l_ex)
        {
            l_response =
                (WEB3MarginOrderHistoryResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "�M�p������������Ɖ�T�[�r�X�̎擾�Ɏ��s���܂����B",
                l_response.errorInfo,
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

        try
        {
            l_response =
                (WEB3MarginOrderHistoryResponse)l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response =
                (WEB3MarginOrderHistoryResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request, "�M�p������������Ɖ�Ɏ��s���܂����B", l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        catch (WEB3BaseRuntimeException l_bre)
        {
            l_response =
                (WEB3MarginOrderHistoryResponse)l_request.createResponse();
            l_response.errorInfo = l_bre.getErrorInfo();
            log.error(l_request, "�M�p������������Ɖ�Ɏ��s���܂����B", l_bre);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

        log.exiting(STR_METHOD_NAME);

        //���X�|���X�I�u�W�F�N�g��ԋp����B
        return l_response;
    }

    /**
     * (search���ό����ꗗ)<BR>
     * �M�p������ό����ꗗ�\���������s��<BR>
     * <BR>
     * �M�p����������Ɖ�T�[�r�X���擾���Aexecute()���\�b�h���R�[������B<BR>
     * @@param l_request  - ���N�G�X�g�f�[�^<BR>
     * �M�p������ό����ꗗ���N�G�X�g�f�[�^�I�u�W�F�N�g<BR>
     * 
     * @@return WEB3MarginCloseMarginContractListResponse
     * @@roseuid 40FC9F5301A2
     */
    public WEB3MarginCloseMarginContractListResponse searchCloseMarginContractList(WEB3MarginCloseMarginContractListRequest l_request)
    {
        //�M�p����������Ɖ�T�[�r�X�I�u�W�F�N�g���擾����
        final String STR_METHOD_NAME =
            "searchCloseMarginContractList(WEB3MarginCloseMarginContractListRequest)";

        log.entering(STR_METHOD_NAME);

        WEB3MarginCloseMarginContractListResponse l_response = null;
        WEB3MarginExecuteReferenceService l_service = null;

        //�M�p������ό����ꗗ�T�[�r�X�I�u�W�F�N�g.execute�i�j���R�[�����A
        //�������ʂł��郌�X�|���X�I�u�W�F�N�g���擾����B
        try
        {
            l_service =
                (WEB3MarginExecuteReferenceService)Services.getService(
                    WEB3MarginExecuteReferenceService.class);
        }
        //�T�[�r�X�ŗ�O�����������ꍇ�́A�G���[�������X�|���X���b�Z�[�W�ɐݒ肷��
        catch (Exception l_ex)
        {
            l_response =
                (WEB3MarginCloseMarginContractListResponse)l_request
                    .createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "�M�p������ό����ꗗ�T�[�r�X�̎擾�Ɏ��s���܂����B",
                l_response.errorInfo,
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

        try
        {
            l_response =
                (WEB3MarginCloseMarginContractListResponse)l_service.execute(
                    l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response =
                (WEB3MarginCloseMarginContractListResponse)l_request
                    .createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request, "�M�p������ό����ꗗ�Ɏ��s���܂����B", l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        catch (WEB3BaseRuntimeException l_bre)
        {
            l_response =
                (WEB3MarginCloseMarginContractListResponse)l_request
                    .createResponse();
            l_response.errorInfo = l_bre.getErrorInfo();
            log.error(l_request, "�M�p������ό����ꗗ�Ɏ��s���܂����B", l_bre);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

        log.exiting(STR_METHOD_NAME);

        //���X�|���X�I�u�W�F�N�g��ԋp����B
        return l_response;
    }
}
@
