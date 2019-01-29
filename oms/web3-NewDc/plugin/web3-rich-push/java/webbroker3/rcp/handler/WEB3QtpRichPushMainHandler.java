head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.22.08.16.15;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7444d885aca6009;
filename	WEB3QtpRichPushMainHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
 Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
 File Name        : QTP���b�`�N���C�A���g�v�b�V�����C���n���h��(WEB3QtpRichPushMainHandler.java)
 Author Name      : Daiwa Institute of Research
 Revesion History : 2006/02/06 �� (FLJ)�V�K�쐬
 */
package webbroker3.rcp.handler;

import com.fitechlabs.xtrade.kernel.boot.*;
import com.fitechlabs.xtrade.kernel.handler.*;
import webbroker3.common.*;
import webbroker3.rcp.message.*;
import webbroker3.rcp.service.delegate.*;
import webbroker3.util.*;

/**
 * �iQTP���b�`�N���C�A���g�v�b�V�����C���n���h���j�B
 * @@author  ��
 * @@version 1.0
 */
public class WEB3QtpRichPushMainHandler
    implements MessageHandler
{
    /**
     * ���O�o�̓��[�e�B���e�B�B
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3QtpRichPushMainHandler.class);

    /**
     * �f�t�H���g�R���X�g���N�^�B<BR>
     */
    public WEB3QtpRichPushMainHandler()
    {
    }

    /**
     * (���b�`�N���C�A���g�v�b�V�����C�����N�G�X�g)<BR>
     * ���b�`�N���C�A���g�v�b�V�����C���T�[�r�X���擾���Aexecute()���\�b�h���R�[������B<BR>
     * <BR>
     * @@param l_execNotifyMainRequest - ���N�G�X�g�f�[�^<BR>
     * @@return WEB3QtpRichPushMainResponse<BR>
     */
    public WEB3QtpRichPushMainResponse handleQtpRichPushMainRequest(
        WEB3QtpRichPushMainRequest l_execNotifyMainRequest)
    {
        final String STR_METHOD_NAME =
            "handleQtpRichPushMainRequest(WEB3QtpRichPushMainRequest)";
        log.entering(STR_METHOD_NAME);

        WEB3QtpRichPushMainResponse l_response = new
            WEB3QtpRichPushMainResponse();
        WEB3QtpRichPushMainService l_service = null;

        try
        {
            l_service =
                (WEB3QtpRichPushMainService) Services.getService(
                WEB3QtpRichPushMainService.class);
        }
        catch (Exception l_exp)
        {
            l_response = new WEB3QtpRichPushMainResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_execNotifyMainRequest,
                "���b�`�N���C�A���g�v�b�V�����C���T�[�r�X�擾�Ɏ��s���܂����B",
                l_response.errorInfo,
                l_exp);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

        try
        {
            l_response =
                (WEB3QtpRichPushMainResponse) l_service.execute(
                l_execNotifyMainRequest);

        }
        catch (WEB3BaseException l_exp)
        {
            log.error(l_execNotifyMainRequest, "���b�`�N���C�A���g�v�b�V�����C�������Ɏ��s���܂����B", l_exp);
            l_response =
                (WEB3QtpRichPushMainResponse) l_execNotifyMainRequest.
                createResponse();
            l_response.errorInfo = l_exp.getErrorInfo();
        }
        catch (WEB3BaseRuntimeException l_bre)
        {
            log.error(l_execNotifyMainRequest, "���b�`�N���C�A���g�v�b�V�����C�������Ɏ��s���܂����B", l_bre);
            l_response =
                (WEB3QtpRichPushMainResponse) l_execNotifyMainRequest.
                createResponse();
            l_response.errorInfo = l_bre.getErrorInfo();
        }
        catch (Throwable l_t)
		{
            l_response =
                (WEB3QtpRichPushMainResponse) l_execNotifyMainRequest.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error("Unexpected Error.", l_t);            	
		}

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
}
@
