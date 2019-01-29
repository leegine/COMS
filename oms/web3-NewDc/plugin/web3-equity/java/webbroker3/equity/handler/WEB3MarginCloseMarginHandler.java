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
filename	WEB3MarginCloseMarginHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �M�p����ԍσn���h���N���X(WEB3MarginCloseMarginHandler.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/08 ������ (���u) �V�K�쐬
                   2005/01/06 �����a��(SRA) JavaDoc�C��
*/
package webbroker3.equity.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.equity.message.WEB3MarginCloseMarginCompleteRequest;
import webbroker3.equity.message.WEB3MarginCloseMarginCompleteResponse;
import webbroker3.equity.message.WEB3MarginCloseMarginConfirmRequest;
import webbroker3.equity.message.WEB3MarginCloseMarginConfirmResponse;
import webbroker3.equity.service.delegate.WEB3MarginCloseMarginService;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * �i�M�p����ԍσn���h���j�B<BR>
 * <BR>
 * �M�p����ԍσn���h���N���X
 * @@author ������(���u)
 * @@version 1.0
 */
public class WEB3MarginCloseMarginHandler implements MessageHandler
{
    /**
     * ���O�o�̓��[�e�B���e�B�B
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(WEB3MarginCloseMarginHandler.class);

    /**
     * (confirm�ԍ�)<BR>
     * �M�p����ԍϔ����R�����s���B<BR>
     * <BR>
     * �M�p����ԍσT�[�r�X���擾���Aexecute()���\�b�h���R�[������B<BR>
     * @@param l_request  - ���N�G�X�g�f�[�^<BR>
     * @@return WEB3MarginCloseMarginConfirmResponse
     * @@roseuid 40569B300086
     */
    public WEB3MarginCloseMarginConfirmResponse confirmCloseMargin(WEB3MarginCloseMarginConfirmRequest l_request)
    {
        final String STR_METHOD_NAME = "confirmCloseMargin(WEB3MarginCloseMarginConfirmRequest)";
        log.entering(STR_METHOD_NAME);

        WEB3MarginCloseMarginConfirmResponse l_response = null;
        WEB3MarginCloseMarginService l_service = null;

        try
        {
            l_service = (WEB3MarginCloseMarginService) Services.getService(WEB3MarginCloseMarginService.class);
        }
        // �T�[�r�X�ŗ�O�����������ꍇ�́A�G���[�������X�|���X���b�Z�[�W�ɐݒ肷��
        catch (Exception l_ex)
        {
            l_response = (WEB3MarginCloseMarginConfirmResponse) l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(l_request, "�M�p����ԍσT�[�r�X�̎擾�Ɏ��s���܂����B", l_response.errorInfo, l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

        try
        {
            l_response = (WEB3MarginCloseMarginConfirmResponse) l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response = (WEB3MarginCloseMarginConfirmResponse) l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            if (!WEB3StringTypeUtility.isEmpty(l_ex.getErrorMessage()))
            {
                l_response.errorMessage = l_ex.getErrorMessage();
            }
            log.error(l_request, "�M�p����ԍϔ����R���Ɏ��s���܂����B", l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        catch (WEB3BaseRuntimeException l_bre)
        {
            l_response = (WEB3MarginCloseMarginConfirmResponse) l_request.createResponse();
            l_response.errorInfo = l_bre.getErrorInfo();
            log.error(l_request, "�M�p����ԍϔ����R���Ɏ��s���܂����B", l_bre);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

        log.exiting(STR_METHOD_NAME);
        return l_response;

    }

    /**
     * (complete�ԍ�)<BR>
     * �M�p����ԍϒ����o�^���s���B<BR>
     * <BR>
     * �M�p����ԍσT�[�r�X���擾���Aexecute()���\�b�h���R�[������B<BR>
     * @@param l_request  - ���N�G�X�g�f�[�^<BR>
     * @@return WEB3MarginCloseMarginCompleteResponse
     * @@roseuid 40569B640151
     */
    public WEB3MarginCloseMarginCompleteResponse completeCloseMargin(WEB3MarginCloseMarginCompleteRequest l_request)
    {
        final String STR_METHOD_NAME = "completeCloseMargin(WEB3MarginCloseMarginCompleteRequest)";
        log.entering(STR_METHOD_NAME);

        WEB3MarginCloseMarginCompleteResponse l_response = null;
        WEB3MarginCloseMarginService l_service = null;

        try
        {
            l_service = (WEB3MarginCloseMarginService) Services.getService(WEB3MarginCloseMarginService.class);
        }
        // �T�[�r�X�ŗ�O�����������ꍇ�́A�G���[�������X�|���X���b�Z�[�W�ɐݒ肷��
        catch (Exception l_ex)
        {
            l_response = (WEB3MarginCloseMarginCompleteResponse) l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(l_request, "�M�p����ԍσT�[�r�X�̎擾�Ɏ��s���܂����B", l_response.errorInfo, l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

        try
        {
            l_response = (WEB3MarginCloseMarginCompleteResponse) l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response = (WEB3MarginCloseMarginCompleteResponse) l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            if (!WEB3StringTypeUtility.isEmpty(l_ex.getErrorMessage()))
            {
                l_response.errorMessage = l_ex.getErrorMessage();
            }
            log.error(l_request, "�M�p����ԍϒ����Ɏ��s���܂����B", l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        catch (WEB3BaseRuntimeException l_bre)
        {
            l_response = (WEB3MarginCloseMarginCompleteResponse) l_request.createResponse();
            l_response.errorInfo = l_bre.getErrorInfo();
            log.error(l_request, "�M�p����ԍϒ����Ɏ��s���܂����B", l_bre);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
}
@
