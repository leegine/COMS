head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.02;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3MutualBuyInputHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : ���M���t�������̓n���h���N���X(WEB3MutualBuyInputHandler)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/08/11 ���E (���u) �V�K�쐬
                   2004/08/23 ���� (���u) ���r���[
*/
package webbroker3.mf.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.mf.message.WEB3MutualBuyInputRequest;
import webbroker3.mf.message.WEB3MutualBuyInputResponse;
import webbroker3.mf.service.delegate.WEB3MutualBuyInputService;
import webbroker3.util.WEB3LogUtility;

/**
 * ���M���t�������̓n���h���N���X<BR>
 * 
 * @@author ���E(���u)
 * @@version 1.0
 */
public class WEB3MutualBuyInputHandler implements MessageHandler
{

    /**
     * ���O�o�̓��[�e�B���e�B�B
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3MutualBuyInputHandler.class);
    /**
     * (���t�������̓��N�G�X�g)<BR>
     * �����M���̔��t�������͉�ʕ\���������s���B<BR>
     * <BR>
     * ���M���t�������̓T�[�r�X���擾���A<BR>
     * execute()���\�b�h���R�[������B<BR>
     * @@param l_request - ���M���t�������̓��N�G�X�g�I�u�W�F�N�g<BR>
     * @@return webbroker3.mf.message.WEB3MutualBuyInputResponse
     * @@roseuid 40B14D9502F3
     */
    public WEB3MutualBuyInputResponse buyInputRequest(WEB3MutualBuyInputRequest l_request)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "buyInputRequest(WEB3MutualBuyInputRequest l_request)";
        log.entering(STR_METHOD_NAME);
        if (l_request == null)
        {
            log.debug("the parametor method in null ");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        WEB3MutualBuyInputResponse l_response = null;
        WEB3MutualBuyInputService l_service = null;
        try
        {
            l_service =
                (WEB3MutualBuyInputService) Services.getService(
                    WEB3MutualBuyInputService.class);

        }
        catch (Exception l_ex)
        {
            l_response = (WEB3MutualBuyInputResponse) l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "���M���t�������̓T�[�r�X�̎擾�Ɏ��s���܂����B",
                l_response.errorInfo,
                l_ex);
            return l_response;
        }
        try
        {
            l_response =
                (WEB3MutualBuyInputResponse) l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response = (WEB3MutualBuyInputResponse) l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request, "���M���t�������͂̎擾�Ɏ��s���܂���", l_ex.getErrorInfo(), l_ex);
            return l_response;
        }
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
}
@
