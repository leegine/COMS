head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.22.08.27.06;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	2144d885d4c6839;
filename	WEB3RlsCondOrderSubmitHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
 Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
 File Name        : ���[���G���W������ꌏ�����ʒm�n���h���N���X(WEB3RlsCondOrderSubmitHandler.java)
 Author Name      : Daiwa Institute of Research
 Revesion History : 2005/09/15 �� (FLJ)�V�K�쐬
 */
package webbroker3.omsadaptor.handler;

import com.fitechlabs.xtrade.kernel.boot.*;
import com.fitechlabs.xtrade.kernel.data.*;
import com.fitechlabs.xtrade.kernel.handler.*;
import webbroker3.common.*;
import webbroker3.gentrade.*;
import webbroker3.omsadaptor.message.*;
import webbroker3.omsadaptor.service.delegate.*;
import webbroker3.util.*;

/**
 * �i���[���G���W������ꌏ�����ʒm�n���h���N���X�j�B<BR>
 * <BR>
 * ���[���G���W������ꌏ�����ʒm�n���h���N���X
 * @@author �� (FLJ)
 * @@version 1.0
 */
public class WEB3RlsCondOrderSubmitHandler
    implements MessageHandler
{
    /**
     * ���O�o�̓��[�e�B���e�B�B
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3RlsCondOrderSubmitHandler.class);

    /**
     * (���[���G���W������ꌏ�����ʒm)�B<BR>

     * @@param  l_request - ���[���G���W������ꌏ�����ʒm���N�G�X�g<BR>
     * @@return WEB3RlsCondOrderSubmitResponse
     */
    public WEB3RlsCondOrderSubmitResponse handleRlsCondOrderSubmitRequest(
        WEB3RlsCondOrderSubmitRequest l_request)
    {
        final String STR_METHOD_NAME =
            "handleRlsCondOrderSubmitRequest(WEB3RlsCondOrderSubmitRequest)";
        log.entering(STR_METHOD_NAME);

        WEB3RlsCondOrderSubmitService l_svr = (WEB3RlsCondOrderSubmitService)
            Services.
            getService(WEB3RlsCondOrderSubmitService.class);
        try
        {

            try
            {
                WEB3GentradeSubAccount l_subaccount = new WEB3GentradeSubAccount(
                    l_request.account_id,
                    l_request.sub_account_id);

                l_svr.submitRlsCondOrder(l_subaccount,
                                         l_request.order_type,
                                         l_request.con_order_id, l_request.product_type);
            }
            catch (DataException l_ex)
            {
                log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂���: ", l_ex);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
            }
        }
        catch (WEB3SystemLayerException ex)
        {
            WEB3RlsCondOrderSubmitResponse l_response = new
                WEB3RlsCondOrderSubmitResponse();
            l_response.errorInfo = ex.getErrorInfo();
            log.error("���[���G���W������ꌏ�����ʒm�B", ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        catch (WEB3BaseException ex)
        {
            WEB3RlsCondOrderSubmitResponse l_response = new
                WEB3RlsCondOrderSubmitResponse();
            l_response.errorInfo = ex.getErrorInfo();
            log.error("���[���G���W������ꌏ�����ʒm�B", ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

        WEB3RlsCondOrderSubmitResponse l_response = new
            WEB3RlsCondOrderSubmitResponse();

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

}
@
