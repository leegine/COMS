head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.05.28.50;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4b44d7ef6783489;
filename	WEB3LoginPwdChangeHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright           : (株)大和総研 証券ソリューションシステム第二部
File Name           : ログイン時パスワード変更ハンドラ(WEB3LoginPwdChangeHandler.java)
Author Name         : Daiwa Institute of Research
Revesion History    : 2004/03/30 菊地(SRA)
 */

package webbroker3.login.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.login.message.WEB3LoginPwdChangeRequest;
import webbroker3.login.message.WEB3LoginPwdChangeResponse;
import webbroker3.login.service.delegate.WEB3LoginPwdChangeService;
import webbroker3.util.WEB3LogUtility;

import com.fitechlabs.xtrade.kernel.message.Response;

/**
 * （ログイン時パスワード変更ハンドラ）<BR>
 * ログイン時パスワード変更ハンドラ<BR>
 * <BR> 
 * @@author      菊地(SRA)
 * @@version     1.00
 * @@see com.fitechlabs.xtrade.kernel.handler.MessageHandler
 */
public class WEB3LoginPwdChangeHandler implements MessageHandler
{
    private static WEB3LogUtility l_log =
        WEB3LogUtility.getInstance(WEB3LoginPwdChangeHandler.class);

    /**
     * @@roseuid 40692C5F002E
     */
    public WEB3LoginPwdChangeHandler()
    {

    }

    /**
     * （ログイン時パスワード変更リクエスト）
     * @@param l_request
     * @@return Response
     * @@roseuid 4063BEDE00E4
     */
    public Response loginPwdChangeRequest(WEB3LoginPwdChangeRequest l_request)
    {
        final String STR_METHOD_NAME =
            "loginPwdChangeRequest(WEB3LoginPwdChangeRequest)";
        l_log.entering(STR_METHOD_NAME);

        WEB3LoginPwdChangeResponse l_response = null;
        WEB3LoginPwdChangeService l_service = null;

        try
        {
            l_service =
                (WEB3LoginPwdChangeService) Services.getService(
                    WEB3LoginPwdChangeService.class);
        }
        catch (Exception e)
        {
            l_response =
                (WEB3LoginPwdChangeResponse) l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            l_log.error(
                l_request,
                STR_METHOD_NAME
                    + " .... Services.getService(WEB3LoginPwdChangeService.class) error.",
                l_response.errorInfo,
                e);
            return l_response;
        }

        try
        {
            l_response =
                (WEB3LoginPwdChangeResponse) l_service.execute(l_request);
        }
        catch (WEB3BaseException e)
        {
            l_response =
                (WEB3LoginPwdChangeResponse) l_request.createResponse();
            l_response.errorInfo = e.getErrorInfo();
            l_log.error(
                l_request,
                STR_METHOD_NAME
                    + " .... WEB3LoginPwdChangeService.execute error.",
                e);
            return l_response;
        }

        l_log.exiting(STR_METHOD_NAME);
        return l_response;
    }
}
@
