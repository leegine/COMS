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
filename	WEB3EquityReceiveCloseOrderHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : ���������ʒm�n���h��(WEB3EquityReceiveCloseOrderHandler.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/05/24 ���j (���u) �V�K�쐬
                   2004/12/13 ���� (SRA) �N���X�̐ړ����C��
                   2005/01/06 ���� (SRA) JavaDoc�C��
*/
package webbroker3.equity.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.equity.message.WEB3EquityCloseOrderRequest;
import webbroker3.equity.message.WEB3EquityCloseOrderResponse;
import webbroker3.equity.service.delegate.WEB3EquityReceiveCloseOrderService;
import webbroker3.util.WEB3LogUtility;

/**
 * �i���������ʒm�n���h���j�B<BR>
 * <BR>
 * ���������ʒm�n���h���N���X
 * @@version 1.0
 */
public class WEB3EquityReceiveCloseOrderHandler implements MessageHandler
{
    /**
     * ���O�o�̓��[�e�B���e�B�B<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3EquityReceiveCloseOrderHandler.class);

    /**
     * @@roseuid 40AC8A9E03A9
     */
    public WEB3EquityReceiveCloseOrderHandler()
    {

    }

    /**
     * (�����ʒm)<BR>
     * ���N�G�X�g���󂯁A���������ʒm���������s����B<BR>
     * �i�V�X�e���������j�K�C�h 4.5.�n���h���@@�Q�Ɓj<BR>
     * <BR>
     * �P�j�@@���������ʒm�T�[�r�X�I�u�W�F�N�g���擾����B<BR>
     * <BR>
     * �Q�j�@@���������ʒm�T�[�r�X�I�u�W�F�N�g.execute�i�j���R�[�����A�������ʂł���<BR>
     * ���������ʒm���X�|���X�I�u�W�F�N�g���擾����B<BR>
     * <BR>
     * �@@[����]<BR>
     * �@@�������N�G�X�g<BR>
     * <BR>
     * �R�j�@@�T�[�r�X�ŗ�O�����������ꍇ�́A<BR>
     * �G���[�������X�|���X���b�Z�[�W�ɐݒ肷��B<BR>
     * <BR>
     * �S�j�@@���������ʒm���X�|���X�I�u�W�F�N�g��ԋp����B<BR>
     * @@param l_request - (l_request)<BR>
     * �����ʒm���N�G�X�g�I�u�W�F�N�g<BR>
     * @@return webbroker3.equity.message.WEB3EquityCloseOrderResponse
     * @@throws webbroker3.common.WEB3BaseException
     * @@roseuid 403D8CE40251
     */
    public WEB3EquityCloseOrderResponse receiveCloseOrder(WEB3EquityCloseOrderRequest l_request)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "receiveCloseOrder(WEB3EquityCloseOrderRequest)";
        log.entering(STR_METHOD_NAME);

        WEB3EquityCloseOrderResponse l_response = null;
        WEB3EquityReceiveCloseOrderService l_service = null;

        try
        {
            l_service =
                (WEB3EquityReceiveCloseOrderService) Services.getService(
                    WEB3EquityReceiveCloseOrderService.class);
        }
        catch (Exception l_exp)
        {
            l_response =
                (WEB3EquityCloseOrderResponse) l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "�������������ʒm�T�[�r�X�̎擾�Ɏ��s���܂����B",
                l_response.errorInfo,
                l_exp);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

        try
        {
            l_response =
                (WEB3EquityCloseOrderResponse) l_service.execute(l_request);
        }
        catch (WEB3BaseException l_exp)
        {
            l_response =
                (WEB3EquityCloseOrderResponse) l_request.createResponse();
            l_response.errorInfo = l_exp.getErrorInfo();
            log.error(l_request, "�������������ʒm�Ɏ��s���܂����B", l_exp);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        catch (WEB3BaseRuntimeException l_bre)
        {
            l_response =
                (WEB3EquityCloseOrderResponse) l_request.createResponse();
            l_response.errorInfo = l_bre.getErrorInfo();
            log.error(l_request, "�������������ʒm�Ɏ��s���܂����B", l_bre);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

        log.exiting(STR_METHOD_NAME);

        return l_response;
    }
}
@
