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
filename	WEB3FuturesExecuteReferenceHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �敨�������Ɖ�n���h��(WEB3FuturesExecuteReferenceHandler.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/07/21 ���Q (���u) �V�K�쐬
*/

package webbroker3.ifo.handler;

import com.fitechlabs.xtrade.kernel.handler.MessageHandler;
import com.fitechlabs.xtrade.kernel.boot.Services;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.ifo.message.WEB3FuturesExecuteReferenceResponse;
import webbroker3.ifo.message.WEB3FuturesExecuteReferenceRequest;
import webbroker3.ifo.message.WEB3FuturesExecuteDetailsResponse;
import webbroker3.ifo.message.WEB3FuturesExecuteDetailsRequest;
import webbroker3.ifo.message.WEB3FuturesOrderHistoryResponse;
import webbroker3.ifo.message.WEB3FuturesOrderHistoryRequest;
import webbroker3.ifo.message.WEB3FuturesCloseMarginContractListResponse;
import webbroker3.ifo.message.WEB3FuturesCloseMarginContractListRequest;
import webbroker3.ifo.service.delegate.WEB3FuturesExecuteReferenceService;
import webbroker3.util.WEB3LogUtility;

/**
 * (�敨�������Ɖ�n���h��)<BR>
 * �����w���敨�������Ɖ�n���h���N���X<BR>
 */
public class WEB3FuturesExecuteReferenceHandler implements MessageHandler 
{
    
    /**
     * ���O�o�̓��[�e�B���e�B�B
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3FuturesExecuteReferenceHandler.class);
    
    /**
     * @@roseuid 40C075600261
     */
    public WEB3FuturesExecuteReferenceHandler() 
    {
     
    }
    
    /**
     * (get�������Ɖ�)<BR>
     * �����w���敨�������Ɖ�A<BR>
     * �����敨�����ꗗ��ʂɕ\������f�[�^�̎擾�������s���B<BR>
     * <BR>
     * �敨�������Ɖ�T�[�r�X���擾���Aexecute()���\�b�h���R�[������B<BR>
     * @@param l_request - ���N�G�X�g�f�[�^<BR>
     * �����w���敨�������Ɖ�N�G�X�g�I�u�W�F�N�g<BR>
     * @@return WEB3FuturesExecuteReferenceResponse
     * @@roseuid 4057FBE3010C
     */
    public WEB3FuturesExecuteReferenceResponse getOrderExecutedInquiry(WEB3FuturesExecuteReferenceRequest l_request) 
    {
        //�敨�������Ɖ�T�[�r�X�I�u�W�F�N�g���擾����
        final String STR_METHOD_NAME =
            "getOrderExecutedInquiry(WEB3FuturesExecuteReferenceRequest)";

        log.entering(STR_METHOD_NAME);

        WEB3FuturesExecuteReferenceResponse l_response = null;
        WEB3FuturesExecuteReferenceService  l_service = null;

        //�����w���敨�������Ɖ�T�[�r�X�I�u�W�F�N�g.execute�i�j���R�[�����A
        //�������ʂł��郌�X�|���X�I�u�W�F�N�g���擾����B
        try
        {
            l_service =
                (WEB3FuturesExecuteReferenceService)Services.getService(
            WEB3FuturesExecuteReferenceService.class);
        }
        //�T�[�r�X�ŗ�O�����������ꍇ�́A�G���[�������X�|���X���b�Z�[�W�ɐݒ肷��
        catch (Exception l_ex)
        {
            l_response =
                (WEB3FuturesExecuteReferenceResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "�敨�������Ɖ�T�[�r�X�̎擾�Ɏ��s���܂����B",
                l_response.errorInfo,
                l_ex);
            return l_response;
        }

        try
        {
            l_response =
                (WEB3FuturesExecuteReferenceResponse)l_service.execute(
                    l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response =
                (WEB3FuturesExecuteReferenceResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request, "�敨�������Ɖ�Ɏ��s���܂����B", l_ex);
            return l_response;
        }

        log.exiting(STR_METHOD_NAME);

        //���X�|���X�I�u�W�F�N�g��ԋp����B
        return l_response;
    }
    
    /**
     * (get�������ڍ�)<BR>
     * �����w���敨�����������ڍ׉�ʂɕ\������f�[�^�̎擾�������s<BR>���B<BR>
     * <BR>
     * �敨�������ڍ׃T�[�r�X���擾���Aexecute()���\�b�h���R�[������B<BR>
     * @@param l_request - ���N�G�X�g�f�[�^<BR>
     * �����w���敨�����������ڍ׃��N�G�X�g�I�u�W�F�N�g<BR>
     * @@return WEB3FuturesExecuteDetailsResponse
     * @@roseuid 406A8AB9007C
     */
    public WEB3FuturesExecuteDetailsResponse getOrderExecutedDetail(WEB3FuturesExecuteDetailsRequest l_request) 
    {
        //�敨�������ڍ׃T�[�r�X�I�u�W�F�N�g���擾����
        final String STR_METHOD_NAME =
            "getOrderExecutedDetail(WEB3FuturesExecuteDetailsRequest)";

        log.entering(STR_METHOD_NAME);

        WEB3FuturesExecuteDetailsResponse  l_response = null;
        WEB3FuturesExecuteReferenceService l_service = null;

        //�敨�������ڍ׃T�[�r�X�I�u�W�F�N�g.execute�i�j���R�[�����A
        //�������ʂł��郌�X�|���X�I�u�W�F�N�g���擾����B
        try
        {
            l_service =
                (WEB3FuturesExecuteReferenceService)Services.getService(
                WEB3FuturesExecuteReferenceService.class);
        }
        //�T�[�r�X�ŗ�O�����������ꍇ�́A�G���[�������X�|���X���b�Z�[�W�ɐݒ肷��
        catch (Exception l_ex)
        {
            l_response =
                (WEB3FuturesExecuteDetailsResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "�敨�������ڍ׃T�[�r�X�̎擾�Ɏ��s���܂����B",
                l_response.errorInfo,
                l_ex);
            return l_response;
        }

        try
        {
            l_response =
                (WEB3FuturesExecuteDetailsResponse)l_service.execute(
                    l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response =
                (WEB3FuturesExecuteDetailsResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request, "�敨�������ڍׂɎ��s���܂����B", l_ex);
            return l_response;
        }

        log.exiting(STR_METHOD_NAME);

        //���X�|���X�I�u�W�F�N�g��ԋp����B
        return l_response;
    }
    
    /**
     * (get���������Ɖ�)<BR>
     * �����w���敨���������Ɖ��ʂɕ\������f�[�^���擾����B<BR>
     * <BR>
     * �敨���������Ɖ�T�[�r�X���擾���Aexecute()���\�b�h���R�[������B<BR>
     * @@param l_request - �����w���敨���������Ɖ�N�G�X�g�I�u�W�F�N�g
     * @@return WEB3FuturesOrderHistoryResponse
     * @@roseuid 409EDD0701B8
     */
    public WEB3FuturesOrderHistoryResponse getOrderActionInquiry(WEB3FuturesOrderHistoryRequest l_request) 
    {
        // �敨���������Ɖ�T�[�r�X�I�u�W�F�N�g���擾����
        final String STR_METHOD_NAME =
            "getOrderActionInquiry(WEB3FuturesOrderHistoryRequest)";

        log.entering(STR_METHOD_NAME);

        WEB3FuturesOrderHistoryResponse    l_response = null;
        WEB3FuturesExecuteReferenceService l_service = null;

        //�敨���������Ɖ�T�[�r�X�I�u�W�F�N�g.execute�i�j���R�[�����A
        //�������ʂł��郌�X�|���X�I�u�W�F�N�g���擾����B
        try
        {
            l_service =
                (WEB3FuturesExecuteReferenceService)Services.getService(
                WEB3FuturesExecuteReferenceService.class);
        }
        //�T�[�r�X�ŗ�O�����������ꍇ�́A�G���[�������X�|���X���b�Z�[�W�ɐݒ肷��
        catch (Exception l_ex)
        {
            l_response =
                (WEB3FuturesOrderHistoryResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "�敨���������Ɖ�T�[�r�X�̎擾�Ɏ��s���܂����B",
                l_response.errorInfo,
                l_ex);
            return l_response;
        }

        try
        {
            l_response =
                (WEB3FuturesOrderHistoryResponse)l_service.execute(
                    l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response =
                (WEB3FuturesOrderHistoryResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request, "�敨���������Ɖ�Ɏ��s���܂����B", l_ex);
            return l_response;
        }

        log.exiting(STR_METHOD_NAME);

        //���X�|���X�I�u�W�F�N�g��ԋp����B
        return l_response;
    }
    
    /**
     * (get�ԍό��ʈꗗ)<BR>
     * �����w���敨�ԍό��ʈꗗ��ʂɕ\������f�[�^���擾����B<BR>
     * �敨�ԍό��ʈꗗ�T�[�r�X���擾���Aexecute()���\�b�h���R�[������B<BR>
     * @@param l_request - ���N�G�X�g�f�[�^<BR>
     * �����w���敨�ԍό��ʈꗗ���N�G�X�g�I�u�W�F�N�g<BR>
     * @@return WEB3FuturesCloseMarginContractListResponse
     * @@roseuid 409EDDFC01C8
     */
    public WEB3FuturesCloseMarginContractListResponse getSettleContractList(WEB3FuturesCloseMarginContractListRequest l_request) 
    {
        //�敨�ԍό��ʈꗗ�T�[�r�X�I�u�W�F�N�g���擾����
        final String STR_METHOD_NAME = 
            "getSettleContractList(WEB3FuturesCloseMarginContractListRequest)";

        log.entering(STR_METHOD_NAME);

        WEB3FuturesCloseMarginContractListResponse l_response = null;
        WEB3FuturesExecuteReferenceService l_service = null;

        //�敨�ԍό��ʈꗗ�T�[�r�X�I�u�W�F�N�g.execute�i�j���R�[�����A
        //�������ʂł��郌�X�|���X�I�u�W�F�N�g���擾����B
        try
        {
            l_service =
                (WEB3FuturesExecuteReferenceService)Services.getService(
                WEB3FuturesExecuteReferenceService.class);
        }
        //�T�[�r�X�ŗ�O�����������ꍇ�́A�G���[�������X�|���X���b�Z�[�W�ɐݒ肷��
        catch (Exception l_ex)
        {
            l_response = 
                (WEB3FuturesCloseMarginContractListResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "�敨�ԍό��ʈꗗ�T�[�r�X�̎擾�Ɏ��s���܂����B",
                l_response.errorInfo,
                l_ex);
            return l_response;
        }

        try
        {
            l_response = (WEB3FuturesCloseMarginContractListResponse)
                l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response = 
                (WEB3FuturesCloseMarginContractListResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request, "�敨�ԍό��ʈꗗ�Ɏ��s���܂����B", l_ex);
            return l_response;
        }

        log.exiting(STR_METHOD_NAME);

        //���X�|���X�I�u�W�F�N�g��ԋp����B
        return l_response;
    }
}
@
