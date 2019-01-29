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
filename	WEB3EquityChangeCancelNotifyMainHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : ������������ʒm���C���n���h��(WEB3EquityChangeCancelNotifyMainHandler.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/12/14 �������F(SRA) �V�K�쐬
                   2005/01/06 �����a��(SRA) JavaDoc�C��
*/
package webbroker3.equity.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.equity.message.WEB3EquityChangeCancelNotifyMainRequest;
import webbroker3.equity.message.WEB3EquityChangeCancelNotifyMainResponse;
import webbroker3.equity.service.delegate.WEB3EquityChangeCancelNotifyMainService;
import webbroker3.util.WEB3LogUtility;

/**
 * �i������������ʒm���C���n���h���j�B
 * @@author �������F
 * @@version 1.0
 */
public class WEB3EquityChangeCancelNotifyMainHandler implements MessageHandler
{
    /**
     * ���O�o�̓��[�e�B���e�B�B
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3EquityChangeCancelNotifyMainHandler.class);

    /**
     * �f�t�H���g�R���X�g���N�^�B<BR>
     */
    public WEB3EquityChangeCancelNotifyMainHandler()
    {
    }

    /**
     * (��������ʒm���C�����N�G�X�g)<BR>
     * <BR>
     * �����E�M�p����̒�������ʒm�T�[�r�X�U�蕪�����������{����B<BR>
     * <BR>
     * ������������ʒm���C���T�[�r�X���擾���AchangeCancelute()���\�b�h���R�[������B<BR>
     * <BR>
     * @@param l_changeCancelNotifyMainRequest - ���N�G�X�g�f�[�^<BR>
     * @@return WEB3EquityChangeCancelNotifyMainResponse<BR>
     */
    public WEB3EquityChangeCancelNotifyMainResponse equityChangeCancelNotifyMainRequest(
        WEB3EquityChangeCancelNotifyMainRequest l_changeCancelNotifyMainRequest)
    {
        final String STR_METHOD_NAME =
            "equityChangeCancelNotifyMainRequest(WEB3EquityChangeCancelNotifyMainRequest)";
        log.entering(STR_METHOD_NAME);

        WEB3EquityChangeCancelNotifyMainResponse l_response = null;
        WEB3EquityChangeCancelNotifyMainService l_service = null;

        try
        {
            l_service =
                (WEB3EquityChangeCancelNotifyMainService) Services.getService(
                    WEB3EquityChangeCancelNotifyMainService.class);
        }
        catch (Exception l_exp)
        {
            l_response =
                (WEB3EquityChangeCancelNotifyMainResponse) l_changeCancelNotifyMainRequest.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_changeCancelNotifyMainRequest,
                "������������ʒm���C���T�[�r�X�擾�Ɏ��s���܂����B",
                l_response.errorInfo,
                l_exp);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

        try
        {
            l_response =
                (WEB3EquityChangeCancelNotifyMainResponse)l_service.execute(
                    l_changeCancelNotifyMainRequest);
        }
        catch (WEB3BaseException l_exp)
        {
            log.error(l_changeCancelNotifyMainRequest, "������������ʒm���C���Ɏ��s���܂����B", l_exp);
            l_response =
                (WEB3EquityChangeCancelNotifyMainResponse)l_changeCancelNotifyMainRequest.createResponse();
            l_response.errorInfo = l_exp.getErrorInfo();
        }
        catch (WEB3BaseRuntimeException l_bre)
        {
            log.error(l_changeCancelNotifyMainRequest, "������������ʒm���C���Ɏ��s���܂����B", l_bre);
            l_response =
                (WEB3EquityChangeCancelNotifyMainResponse)l_changeCancelNotifyMainRequest.createResponse();
            l_response.errorInfo = l_bre.getErrorInfo();
        }

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
}
@
