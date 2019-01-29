head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.21.42;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3AdminAioSettleReportHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : ���ϘA�g���|�[�g�n���h���N���X(WEB3AdminAioSettleReportHandler)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/13 ��O�� (���u) �V�K�쐬
                   2004/10/27 ���E(���u) ���r���[
*/

package webbroker3.aio.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.aio.message.WEB3AdminAioSettleReportListRequest;
import webbroker3.aio.message.WEB3AdminAioSettleReportListResponse;
import webbroker3.aio.service.delegate.WEB3AdminAioSettleReportService;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.util.WEB3LogUtility;

/**
 * (���ϘA�g���|�[�g�n���h��)<BR>
 * ���ϘA�g���|�[�g�n���h���N���X
 * 
 * @@author ��O��(���u)
 * @@version 1.0
 */

public class WEB3AdminAioSettleReportHandler implements MessageHandler 
{
    /**
     * ���O���[�e�B���e�B
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminAioSettleReportHandler.class);
       
    /**
     * (���|�[�g�\�����N�G�X�g)<BR>
     * ���ϘA�g���|�[�g�T�[�r�X���擾���Aexecute()���\�b�h���R�[������B
     * @@param l_request - ���N�G�X�g�f�[�^
     * @@return WEB3AdminAioSettleReportListResponse
     * @@throws WEB3BaseException
     * @@roseuid 410101AC00BB
     */
    public WEB3AdminAioSettleReportListResponse handleReportIndicationRequest(
        WEB3AdminAioSettleReportListRequest l_request) 
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = 
            "handleReportIndicationRequest(WEB3AdminAioSettleReportListRequest l_request)";
        log.entering(STR_METHOD_NAME);       
        
        //���ϘA�g���|�[�g�T�[�r�X���擾��
        WEB3AdminAioSettleReportService l_service = null;
        WEB3AdminAioSettleReportListResponse l_response = null;
        try
        {
            l_service =
                (WEB3AdminAioSettleReportService) 
                Services.getService(WEB3AdminAioSettleReportService.class);
        }
        // �T�[�r�X�ŗ�O�����������ꍇ�́A�G���[�������X�|���X���b�Z�[�W�ɐݒ肷��
        catch (Exception l_ex)
        {
            l_response =
                (WEB3AdminAioSettleReportListResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "���ϘA�g���|�[�g�T�[�r�X���擾�Ɏ��s���܂����B",
                l_response.errorInfo,
                l_ex);
            return l_response;
        }
        try
        {
            //execute()���\�b�h���R�[������
            l_response = (WEB3AdminAioSettleReportListResponse) l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response =
                (WEB3AdminAioSettleReportListResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request, 
                "���ϘA�g���|�[�g���������s���܂����B", 
                l_ex.getErrorInfo(), 
                l_ex);
            return l_response;
        }
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }    
}
@
