head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.15;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3AdminEquityForcedSettleTempOrderCreateHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �������ω������쐬�n���h��(WEB3AdminEquityForcedSettleTempOrderCreateHandler.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/04/24 �����q(���u) �V�K�쐬 �d�l�ύX���f��No.131 No.145
*/
package webbroker3.eqtypeadmin.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.eqtypeadmin.message.WEB3AdminEquityForcedSettleTempOrderCreateRequest;
import webbroker3.eqtypeadmin.message.WEB3AdminEquityForcedSettleTempOrderCreateResponse;
import webbroker3.eqtypeadmin.service.delegate.WEB3AdminEquityForcedSettleTempOrderCreateService;
import webbroker3.util.WEB3LogUtility;

/**
 * (�������ω������쐬�n���h��)<BR>
 * �������ω������쐬�n���h��<BR>
 * <BR>
 * @@author �����q
 * @@version 1.0
 */
public class WEB3AdminEquityForcedSettleTempOrderCreateHandler implements MessageHandler
{
    /**
     * ���O���[�e�B���e�B<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminEquityForcedSettleTempOrderCreateHandler.class);

    /**
     * @@roseuid 447AC0CF01F4
     */
    public WEB3AdminEquityForcedSettleTempOrderCreateHandler()
    {

    }

    /**
     * (complete�������ω������쐬)<BR>
     * �������ω������쐬�������s���B<BR>
     * <BR>
     * �������ω������쐬�T�[�r�X���擾���Aexecute()���\�b�h���R�[������B<BR>
     * <BR>
     * @@param l_request - (���N�G�X�g�f�[�^)
     * @@return WEB3AdminEquityForcedSettleTempOrderCreateResponse
     * ���N�G�X�g�f�[�^
     */
    public WEB3AdminEquityForcedSettleTempOrderCreateResponse completeForcedSettleOrderCreate(
        WEB3AdminEquityForcedSettleTempOrderCreateRequest l_request)
    {
        final String STR_METHOD_NAME =
            "completeForcedSettleOrderCreate(WEB3AdminEquityForcedSettleTempOrderCreateRequest)";
        log.entering(STR_METHOD_NAME);

        WEB3AdminEquityForcedSettleTempOrderCreateResponse l_response = null;
        WEB3AdminEquityForcedSettleTempOrderCreateService l_service = null;

        try
        {
            l_service =
                (WEB3AdminEquityForcedSettleTempOrderCreateService)Services.getService(
                    WEB3AdminEquityForcedSettleTempOrderCreateService.class);

        }
        catch (Exception l_exp)
        {
            l_response =
                (WEB3AdminEquityForcedSettleTempOrderCreateResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;

            log.error(
                l_request,
                "�������ω������쐬�T�[�r�X�̎擾�Ɏ��s���܂����B",
                l_response.errorInfo,
                l_exp);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        try
        {
            l_response =
                (WEB3AdminEquityForcedSettleTempOrderCreateResponse)l_service.execute(l_request);
        }
        catch (WEB3BaseException l_exp)
        {
            log.error(l_request, "�������ω������쐬�������s���Ɏ��s���܂����B", l_exp);
            l_response =
                (WEB3AdminEquityForcedSettleTempOrderCreateResponse)l_request.createResponse();
            l_response.errorInfo = l_exp.getErrorInfo();
            return l_response;
        }
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
}
@
