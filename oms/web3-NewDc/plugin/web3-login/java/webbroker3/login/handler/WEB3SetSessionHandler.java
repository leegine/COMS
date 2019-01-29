head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.05.29.06;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4b44d7ef6783489;
filename	WEB3SetSessionHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright           : (株)大和総研 証券ソリューションシステム第二部
File Name           : セッション属性を設定するハンドラ(WEB3SetSessionHandler.java)
Author Name         : Daiwa Institute of Research
Revesion History    : 2004/02/25　@菊地(SRA) 新規作成
 */
package webbroker3.login.handler;

import java.util.Enumeration;

import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.login.message.WEB3SetSessionRequest;
import webbroker3.login.message.WEB3SetSessionResponse;
import webbroker3.util.WEB3LogUtility;

import com.fitechlabs.xtrade.kernel.message.Response;
import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;
import com.fitechlabs.xtrade.plugin.security.oplogin.OpLoginSecurityService;

/**
 * セッション属性を設定するハンドラ。<BR>
 * ログインサービスを実行するスレッドはアクティブ・セッションと関係付け<BR>
 * られていない為、ログイン成功（セッション生成）後であってもセッション<BR>
 * 属性を設定する事ができない（IllegalSessionStateExceptionが発<BR>
 * 生する）。その為、SessionRequestでこのサービスにディスパッチし、<BR>
 * ここでセッション属性を設定する。<BR>
 * 
 * @@author      菊地(SRA)
 * @@version     0.01
 */
public final class WEB3SetSessionHandler implements MessageHandler
{
    /**
     * Logger
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3SetSessionHandler.class);

    /**
     * リクエスト・パラメータで指定されたNVペアーをセッション属性として設定する。<BR>
     * @@return 成功：null、失敗：Exception
     */
    public Response setSessionRequest(WEB3SetSessionRequest l_request)
    {
        final String STR_METHOD_NAME =
            "setSessionRequest(WEB3SetSessionRequest)";
        log.entering(STR_METHOD_NAME);

        try
        {
            OpLoginSecurityService l_securityService =
                (OpLoginSecurityService) Services.getService(
                    OpLoginSecurityService.class);
            for (Enumeration l_enum = l_request.sessionAttributes.keys();
                l_enum.hasMoreElements();
                )
            {
                String l_key = (String) l_enum.nextElement();
                String l_value =
                    (String) l_request.sessionAttributes.get(l_key);
                l_securityService.setSessionProperty(l_key, l_value);
                log.debug(
                    STR_METHOD_NAME
                        + " .... key = "
                        + l_key
                        + ", value = "
                        + l_value);
            }
        }
        catch (Exception ex)
        {
            log.debug(STR_METHOD_NAME + " .... exception = " + ex.toString());
            WEB3SetSessionResponse l_response =
                (WEB3SetSessionResponse) l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            return l_response;
        }

        log.exiting(STR_METHOD_NAME);
        return l_request.createResponse();
    }
};
@
