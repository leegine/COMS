head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.22.08.27.02;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	2144d885d4c6839;
filename	WEB3RlsCondOrderNotifyMainHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
 Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
 File Name        : ���[���G���W���ʒm�n���h���N���X(WEB3RlsCondOrderNotifyMainHandler.java)
 Author Name      : Daiwa Institute of Research
 Revesion History : 2005/11/01 �� (FLJ)�V�K�쐬
 */
package webbroker3.omsadaptor.handler;

import com.fitechlabs.xtrade.kernel.boot.*;
import com.fitechlabs.xtrade.kernel.handler.*;
import webbroker3.common.*;
import webbroker3.omsadaptor.message.*;
import webbroker3.omsadaptor.service.delegate.*;
import webbroker3.util.*;

/**
 * �i���[���G���W���ʒm���C���n���h���j�B
 * @@author  ��
 * @@version 1.0
 */
public class WEB3RlsCondOrderNotifyMainHandler
    implements MessageHandler
{
    /**
     * ���O�o�̓��[�e�B���e�B�B
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3RlsCondOrderNotifyMainHandler.class);

    /**
     * �f�t�H���g�R���X�g���N�^�B<BR>
     */
    public WEB3RlsCondOrderNotifyMainHandler()
    {
    }

    /**
     * (���[���G���W���ʒm���C�����N�G�X�g)<BR>
     * ���[���G���W���ʒm���C���T�[�r�X���擾���Aexecute()���\�b�h���R�[������B<BR>
     * <BR>
     * @@param l_execNotifyMainRequest - ���N�G�X�g�f�[�^<BR>
     * @@return WEB3RlsCondOrderNotifyMainResponse<BR>
     */
    public WEB3RlsCondOrderNotifyMainResponse handleRlsCondOrderNotifyMainRequest(
        WEB3RlsCondOrderNotifyMainRequest l_execNotifyMainRequest)
    {
        final String STR_METHOD_NAME =
            "handleRlsCondOrderNotifyMainRequest(WEB3RlsCondOrderNotifyMainRequest)";
        log.entering(STR_METHOD_NAME);

        WEB3RlsCondOrderNotifyMainResponse l_response = new
            WEB3RlsCondOrderNotifyMainResponse();
        WEB33RlsCondOrderNotifyMainService l_service = null;

        try
        {
            l_service =
                (WEB33RlsCondOrderNotifyMainService) Services.getService(
                WEB33RlsCondOrderNotifyMainService.class);
        }
        catch (Exception l_exp)
        {
            l_response = new WEB3RlsCondOrderNotifyMainResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_execNotifyMainRequest,
                "���[���G���W���ʒm���C���T�[�r�X�擾�Ɏ��s���܂����B",
                l_response.errorInfo,
                l_exp);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

        try
        {
            l_response =
                (WEB3RlsCondOrderNotifyMainResponse) l_service.execute(
                l_execNotifyMainRequest);

        }
        catch (WEB3BaseException l_exp)
        {
            log.error(l_execNotifyMainRequest, "���[���G���W���ʒm���C�������Ɏ��s���܂����B", l_exp);
            l_response =
                (WEB3RlsCondOrderNotifyMainResponse) l_execNotifyMainRequest.
                createResponse();
            l_response.errorInfo = l_exp.getErrorInfo();
        }
        catch (WEB3BaseRuntimeException l_bre)
        {
            log.error(l_execNotifyMainRequest, "���[���G���W���ʒm���C�������Ɏ��s���܂����B", l_bre);
            l_response =
                (WEB3RlsCondOrderNotifyMainResponse) l_execNotifyMainRequest.
                createResponse();
            l_response.errorInfo = l_bre.getErrorInfo();
        }

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
}
@