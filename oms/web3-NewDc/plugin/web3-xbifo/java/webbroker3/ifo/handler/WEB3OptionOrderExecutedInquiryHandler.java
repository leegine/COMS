head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.13;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3OptionOrderExecutedInquiryHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : OP�������Ɖ�n���h��(WEB3OptionOrderExecutedInquiryHandler.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/06/10 �n�U�c (���u) �V�K�쐬
*/

package webbroker3.ifo.handler;

import com.fitechlabs.xtrade.kernel.handler.MessageHandler;
import com.fitechlabs.xtrade.kernel.boot.Services;
import webbroker3.util.WEB3LogUtility;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.ifo.message.WEB3OptionsExecuteReferenceResponse;
import webbroker3.ifo.message.WEB3OptionsExecuteReferenceRequest;
import webbroker3.ifo.message.WEB3OptionsExecuteDetailsResponse;
import webbroker3.ifo.message.WEB3OptionsExecuteDetailsRequest;
import webbroker3.ifo.message.WEB3OptionsOrderHistoryResponse;
import webbroker3.ifo.message.WEB3OptionsOrderHistoryRequest;
import webbroker3.ifo.message.WEB3OptionsCloseMarginContractListResponse;
import webbroker3.ifo.message.WEB3OptionsCloseMarginContractListRequest;
import webbroker3.ifo.service.delegate.WEB3OptionOrderExecutedInquiryService;

/**
 * OP�������Ɖ�n���h��<BR>
 * �����w���I�v�V�����������Ɖ�n���h���N���X<BR>
 * @@author �n�U�c
 * @@version 1.0
 */
public class WEB3OptionOrderExecutedInquiryHandler implements MessageHandler 
{
    
    /**
     * ���O�o�̓��[�e�B���e�B�B
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3OptionOrderExecutedInquiryHandler.class);
    
    /**
     * @@roseuid 40C075600261
     */
    public WEB3OptionOrderExecutedInquiryHandler() 
    {
     
    }
    
    /**
     * get�������Ɖ�<BR>
     * �����w���I�v�V�����������Ɖ�A<BR>
     * �����I�v�V���������ꗗ��ʂɕ\������f�[�^�̎擾�������s���B<BR>
     * <BR>
     * OP�������Ɖ�T�[�r�X���擾���Aexecute()���\�b�h���R�[������B<BR>
     * @@param l_request - ���N�G�X�g�f�[�^<BR>
     * �����w���I�v�V�����������Ɖ�N�G�X�g�I�u�W�F�N�g<BR>
     * @@return webbroker3.ifo.message.WEB3OptionsExecuteReferenceResponse
     * @@roseuid 4057FBE3010C
     */
    public WEB3OptionsExecuteReferenceResponse getOrderExecutedInquiry(WEB3OptionsExecuteReferenceRequest l_request) 
    {
        //OP�������Ɖ�T�[�r�X�I�u�W�F�N�g���擾����
        final String l_strMethodName =
            "getOrderExecutedInquiry(WEB3OptionsExecuteReferenceRequest)";

        log.entering(l_strMethodName);

        WEB3OptionsExecuteReferenceResponse l_response = null;
        WEB3OptionOrderExecutedInquiryService l_service = null;

        //OP�������Ɖ�T�[�r�X�I�u�W�F�N�g.execute�i�j���R�[�����A
        //�������ʂł��郌�X�|���X�I�u�W�F�N�g���擾����B
        try
        {
            l_service =
                (WEB3OptionOrderExecutedInquiryService)Services.getService(
                    WEB3OptionOrderExecutedInquiryService.class);
        }
        //�T�[�r�X�ŗ�O�����������ꍇ�́A�G���[�������X�|���X���b�Z�[�W�ɐݒ肷��
        catch (Exception e)
        {
            l_response =
                (WEB3OptionsExecuteReferenceResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "OP�������Ɖ�T�[�r�X�̎擾�Ɏ��s���܂����B",
                l_response.errorInfo,
                e);
            return l_response;
        }

        try
        {
            l_response =
                (WEB3OptionsExecuteReferenceResponse)l_service.execute(
                    l_request);
        }
        catch (WEB3BaseException e)
        {
            l_response =
                (WEB3OptionsExecuteReferenceResponse)l_request.createResponse();
            l_response.errorInfo = e.getErrorInfo();
            log.error(l_request, "OP�������Ɖ�Ɏ��s���܂����B", e);
            return l_response;
        }

        log.exiting(l_strMethodName);

        //���X�|���X�I�u�W�F�N�g��ԋp����B
        return l_response;
    }
    
    /**
     * get�������ڍ�<BR>
     * �����w���I�v�V���������������ڍ׉�ʂɕ\������f�[�^�̎擾�������s<BR>���B<BR>
     * <BR>
     * OP�������ڍ׃T�[�r�X���擾���Aexecute()���\�b�h���R�[������B<BR>
     * @@param l_request - ���N�G�X�g�f�[�^<BR>
     * �����w���I�v�V���������������ڍ׃��N�G�X�g�I�u�W�F�N�g<BR>
     * @@return webbroker3.ifo.message.WEB3OptionsExecuteDetailsResponse
     * @@roseuid 406A8AB9007C
     */
    public WEB3OptionsExecuteDetailsResponse getOrderExecutedDetail(WEB3OptionsExecuteDetailsRequest l_request) 
    {
        //OP�������ڍ׃T�[�r�X�I�u�W�F�N�g���擾����
        final String l_strMethodName =
            "getOrderExecutedDetail(WEB3OptionsExecuteDetailsRequest)";

        log.entering(l_strMethodName);

        WEB3OptionsExecuteDetailsResponse l_response = null;
        WEB3OptionOrderExecutedInquiryService l_service = null;

        //OP�������ڍ׃T�[�r�X�I�u�W�F�N�g.execute�i�j���R�[�����A
        //�������ʂł��郌�X�|���X�I�u�W�F�N�g���擾����B
        try
        {
            l_service =
                (WEB3OptionOrderExecutedInquiryService)Services.getService(
                    WEB3OptionOrderExecutedInquiryService.class);
        }
        //�T�[�r�X�ŗ�O�����������ꍇ�́A�G���[�������X�|���X���b�Z�[�W�ɐݒ肷��
        catch (Exception e)
        {
            l_response =
                (WEB3OptionsExecuteDetailsResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "OP�������ڍ׃T�[�r�X�̎擾�Ɏ��s���܂����B",
                l_response.errorInfo,
                e);
            return l_response;
        }

        try
        {
            l_response =
                (WEB3OptionsExecuteDetailsResponse)l_service.execute(
                    l_request);
        }
        catch (WEB3BaseException e)
        {
            l_response =
                (WEB3OptionsExecuteDetailsResponse)l_request.createResponse();
            l_response.errorInfo = e.getErrorInfo();
            log.error(l_request, "OP�������ڍׂɎ��s���܂����B", e);
            return l_response;
        }

        log.exiting(l_strMethodName);

        //���X�|���X�I�u�W�F�N�g��ԋp����B
        return l_response;
    }
    
    /**
     * get���������Ɖ�<BR>
     * �����w���I�v�V�������������Ɖ��ʂɕ\������f�[�^���擾����B<BR>
     * <BR>
     * OP���������Ɖ�T�[�r�X���擾���Aexecute()���\�b�h���R�[������B<BR>
     * @@param l_request - �����w���I�v�V�������������Ɖ�N�G�X�g�I�u�W�F�N�g
     * @@return webbroker3.ifo.message.WEB3OptionsOrderHistoryResponse
     * @@roseuid 409EDD0701B8
     */
    public WEB3OptionsOrderHistoryResponse getOrderActionInquiry(WEB3OptionsOrderHistoryRequest l_request) 
    {
        // OP���������Ɖ�T�[�r�X�I�u�W�F�N�g���擾����
        final String l_strMethodName =
            "getOrderActionInquiry(WEB3OptionsOrderHistoryRequest)";

        log.entering(l_strMethodName);

        WEB3OptionsOrderHistoryResponse l_response = null;
        WEB3OptionOrderExecutedInquiryService l_service = null;

        //OP���������Ɖ�T�[�r�X�I�u�W�F�N�g.execute�i�j���R�[�����A
        //�������ʂł��郌�X�|���X�I�u�W�F�N�g���擾����B
        try
        {
            l_service =
                (WEB3OptionOrderExecutedInquiryService)Services.getService(
                    WEB3OptionOrderExecutedInquiryService.class);
        }
        //�T�[�r�X�ŗ�O�����������ꍇ�́A�G���[�������X�|���X���b�Z�[�W�ɐݒ肷��
        catch (Exception e)
        {
            l_response =
                (WEB3OptionsOrderHistoryResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "OP���������Ɖ�T�[�r�X�̎擾�Ɏ��s���܂����B",
                l_response.errorInfo,
                e);
            return l_response;
        }

        try
        {
            l_response =
                (WEB3OptionsOrderHistoryResponse)l_service.execute(
                    l_request);
        }
        catch (WEB3BaseException e)
        {
            l_response =
                (WEB3OptionsOrderHistoryResponse)l_request.createResponse();
            l_response.errorInfo = e.getErrorInfo();
            log.error(l_request, "OP���������Ɖ�Ɏ��s���܂����B", e);
            return l_response;
        }

        log.exiting(l_strMethodName);

        //���X�|���X�I�u�W�F�N�g��ԋp����B
        return l_response;
    }
    
    /**
     * get�ԍό��ʈꗗ<BR>
     * �����w���I�v�V�����ԍό��ʈꗗ��ʂɕ\������f�[�^���擾����B<BR>
     * OP�ԍό��ʈꗗ�T�[�r�X���擾���Aexecute()���\�b�h���R�[������B<BR>
     * @@param l_request - ���N�G�X�g�f�[�^<BR>
     * �����w���I�v�V�����ԍό��ʈꗗ���N�G�X�g�I�u�W�F�N�g<BR>
     * @@return webbroker3.ifo.message.WEB3OptionsCloseMarginContractListResponse
     * @@roseuid 409EDDFC01C8
     */
    public WEB3OptionsCloseMarginContractListResponse getSettleContractList(WEB3OptionsCloseMarginContractListRequest l_request) 
    {
        //OP�ԍό��ʈꗗ�T�[�r�X�I�u�W�F�N�g���擾����
        final String l_strMethodName = 
            "getSettleContractList(WEB3OptionsCloseMarginContractListRequest)";

        log.entering(l_strMethodName);

        WEB3OptionsCloseMarginContractListResponse l_response = null;
        WEB3OptionOrderExecutedInquiryService l_service = null;

        //OP�ԍό��ʈꗗ�T�[�r�X�I�u�W�F�N�g.execute�i�j���R�[�����A
        //�������ʂł��郌�X�|���X�I�u�W�F�N�g���擾����B
        try
        {
            l_service =
                (WEB3OptionOrderExecutedInquiryService)Services.getService(
                    WEB3OptionOrderExecutedInquiryService.class);
        }
        //�T�[�r�X�ŗ�O�����������ꍇ�́A�G���[�������X�|���X���b�Z�[�W�ɐݒ肷��
        catch (Exception e)
        {
            l_response = 
                (WEB3OptionsCloseMarginContractListResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "OP�ԍό��ʈꗗ�T�[�r�X�̎擾�Ɏ��s���܂����B",
                l_response.errorInfo,
                e);
            return l_response;
        }

        try
        {
            l_response = (WEB3OptionsCloseMarginContractListResponse)
                l_service.execute(l_request);
        }
        catch (WEB3BaseException e)
        {
            l_response = 
                (WEB3OptionsCloseMarginContractListResponse)l_request.createResponse();
            l_response.errorInfo = e.getErrorInfo();
            log.error(l_request, "OP�ԍό��ʈꗗ�Ɏ��s���܂����B", e);
            return l_response;
        }

        log.exiting(l_strMethodName);

        //���X�|���X�I�u�W�F�N�g��ԋp����B
        return l_response;
    }
}
@
