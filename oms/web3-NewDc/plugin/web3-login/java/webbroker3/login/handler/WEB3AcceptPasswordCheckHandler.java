head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.05.28.54;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4b44d7ef6783489;
filename	WEB3AcceptPasswordCheckHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright           : (株)大和総研 証券ソリューションシステム第二部
File Name           : 顧客パスワードチェックハンドラ(WEB3AcceptPasswordCheckHandler.java)
Author Name         : Daiwa Institute of Research
Revesion History    : 2005/10/28 齋藤　@栄三(FLJ) 新規作成
*/

package webbroker3.login.handler;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.login.message.WEB3AcceptPasswordCheckRequest;
import webbroker3.login.message.WEB3AcceptPasswordCheckResponse;
import webbroker3.login.service.delegate.WEB3AcceptPasswordCheckService;
import webbroker3.util.WEB3LogUtility;

import com.fitechlabs.xtrade.kernel.message.Response;
import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

/**
 * (顧客パスワードチェックハンドラ)<BR>
 * 顧客パスワードチェックハンドラ<BR>
 * <BR> 
 * @@author      Eizo Saito(FLJ)
 * @@version     1.00
 * @@see com.fitechlabs.xtrade.kernel.handler.MessageHandler
 */
public class WEB3AcceptPasswordCheckHandler implements MessageHandler
{
    private static WEB3LogUtility l_log =
        WEB3LogUtility.getInstance(WEB3AcceptPasswordCheckHandler.class);

    /**
     * コンストラクタ
     */
    public WEB3AcceptPasswordCheckHandler()
    {

    }

    /**
     * (顧客パスワードチェックリクエスト)<BR>
     * @@param l_request
     * @@return Response
     */
    public Response acceptPasswordCheckRequest(WEB3AcceptPasswordCheckRequest l_request)
    {
        final String STR_METHOD_NAME =
            "acceptPasswordCheckRequest(WEB3AcceptPasswordCheckRequest)";
        l_log.entering(STR_METHOD_NAME);

        WEB3AcceptPasswordCheckResponse l_response = null;
        WEB3AcceptPasswordCheckService l_service = null;

        try
        {
            l_service =
                (WEB3AcceptPasswordCheckService) Services.getService(
                    WEB3AcceptPasswordCheckService.class);
        }
        catch (Exception e)
        {
            l_response = (WEB3AcceptPasswordCheckResponse) l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            l_log.error(
                l_request,
                STR_METHOD_NAME
                    + " .... Services.getService(WEB3AcceptPasswordCheckService.class) error.",
                l_response.errorInfo,
                e);
            return l_response;
        }

        try
        {
            l_response = (WEB3AcceptPasswordCheckResponse) l_service.execute(l_request);
        }
        catch (WEB3BaseException e)
        {
            l_response = (WEB3AcceptPasswordCheckResponse) l_request.createResponse();
            l_response.errorInfo = e.getErrorInfo();
            l_log.error(
                l_request,
                STR_METHOD_NAME + " .... WEB3AcceptPasswordCheckService.execute error.",
                e);
            return l_response;
        }

        l_log.exiting(STR_METHOD_NAME);
        return l_response;
    }
}
@
