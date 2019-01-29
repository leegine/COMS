head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.11;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3MarginCloseMarginListHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �M�p������ψꗗ�n���h��(WEB3MarginCloseMarginListHandler.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/09/30 ������ (���u) �V�K�쐬
                   2005/01/06 �����a��(SRA) JavaDoc�C��
*/
package webbroker3.equity.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.equity.message.WEB3MarginCloseMarginListRequest;
import webbroker3.equity.message.WEB3MarginCloseMarginListResponse;
import webbroker3.equity.message.WEB3MarginIndividualCloseMarginListRequest;
import webbroker3.equity.message.WEB3MarginIndividualCloseMarginListResponse;
import webbroker3.equity.service.delegate.WEB3MarginCloseMarginListService;
import webbroker3.util.WEB3LogUtility;

/**
 * �i�M�p������ψꗗ�n���h���j�B<BR>
 * <BR>
 * �M�p������ψꗗ�n���h���N���X
 * @@author ������(���u)
 * @@version 1.0
 */
public class WEB3MarginCloseMarginListHandler implements MessageHandler 
{
    /**
     * ���O�o�̓��[�e�B���e�B�B
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3MarginCloseMarginListHandler.class);
    
    /**
     * (get���ψꗗ)<BR>
     * �M�p������ψꗗ�\���������s���B<BR>
     * <BR>
     * �M�p������ψꗗ�T�[�r�X���擾���Aexecute()���\�b�h���R�[������B<BR>
     * @@param l_request - ���N�G�X�g�f�[�^
     * @@return WEB3MarginCloseMarginListResponse
     * @@roseuid 40E400EF003C
     */
    public WEB3MarginCloseMarginListResponse getCloseMarginList(WEB3MarginCloseMarginListRequest l_request) 
    {
        final String STR_METHOD_NAME = "getCloseMarginList(WEB3MarginCloseMarginListRequest)";
        log.entering(STR_METHOD_NAME);

        WEB3MarginCloseMarginListResponse l_response = null;
        WEB3MarginCloseMarginListService l_service = null;

        try
        {
            l_service =
                 (WEB3MarginCloseMarginListService)Services.getService(
            WEB3MarginCloseMarginListService.class);
        }
        // �T�[�r�X�ŗ�O�����������ꍇ�́A�G���[�������X�|���X���b�Z�[�W�ɐݒ肷��
        catch (Exception l_ex)
        {
            l_response =
                (WEB3MarginCloseMarginListResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "�M�p������ψꗗ�T�[�r�X�̎擾�Ɏ��s���܂����B",
                l_response.errorInfo,
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

        try
        {
            l_response =
                (WEB3MarginCloseMarginListResponse)l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response =
                (WEB3MarginCloseMarginListResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request, "�M�p������ψꗗ�\�������Ɏ��s���܂����B", l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        catch (WEB3BaseRuntimeException l_bre)
        {
            l_response =
                (WEB3MarginCloseMarginListResponse)l_request.createResponse();
            l_response.errorInfo = l_bre.getErrorInfo();
            log.error(l_request, "�M�p������ψꗗ�\�������Ɏ��s���܂����B", l_bre);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

        log.exiting(STR_METHOD_NAME);
        return l_response;

    }
    
    /**
     * (get�ʌ��ψꗗ)<BR>
     * �M�p����ʌ��ψꗗ�\���������s���B<BR>
     * <BR>
     * �M�p������ψꗗ�T�[�r�X���擾���Aexecute()���\�b�h���R�[������B<BR>
     * @@param l_request - ���N�G�X�g�f�[�^
     * @@return webbroker3.margin.message.WEB3MarginIndividualCloseMarginListResponse
     * @@roseuid 40FF204F0134
     */
    public WEB3MarginIndividualCloseMarginListResponse getIndividualCloseMarginList(WEB3MarginIndividualCloseMarginListRequest l_request) 
    {
        final String STR_METHOD_NAME = "getIndividualCloseMarginList(WEB3MarginIndividualCloseMarginListRequest)";
        log.entering(STR_METHOD_NAME);

        WEB3MarginIndividualCloseMarginListResponse l_response = null;
        WEB3MarginCloseMarginListService l_service = null;

        try
        {
            l_service =
                 (WEB3MarginCloseMarginListService)Services.getService(
            WEB3MarginCloseMarginListService.class);
        }
        // �T�[�r�X�ŗ�O�����������ꍇ�́A�G���[�������X�|���X���b�Z�[�W�ɐݒ肷��
        catch (Exception l_ex)
        {
            l_response =
                (WEB3MarginIndividualCloseMarginListResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "�M�p������ψꗗ�T�[�r�X�̎擾�Ɏ��s���܂����B",
                l_response.errorInfo,
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

        try
        {
            l_response =
                (WEB3MarginIndividualCloseMarginListResponse)l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response =
                (WEB3MarginIndividualCloseMarginListResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request, "�M�p����ʌ��ψꗗ�\���Ɏ��s���܂����B", l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        catch (WEB3BaseRuntimeException l_bre)
        {
            l_response =
                (WEB3MarginIndividualCloseMarginListResponse)l_request.createResponse();
            l_response.errorInfo = l_bre.getErrorInfo();
            log.error(l_request, "�M�p����ʌ��ψꗗ�\���Ɏ��s���܂����B", l_bre);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
}
@
