head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.03.31.26;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	WEB3SockPoolCtrlHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/*
 Copyright        : (株)大和総研 証券ソリューションシステム第二部
 File Name        :　@(WEB3SockPoolCtrlHandler.java)
 Author Name      : Daiwa Institute of Research
 Revesion History : 2006/08/06 劉(FLJ) 新規作成
 */
package webbroker3.sockpoolctrl.handler;

import com.fitechlabs.xtrade.kernel.boot.*;
import com.fitechlabs.xtrade.kernel.handler.*;
import webbroker3.sockpoolctrl.message.*;
import webbroker3.sockpoolctrl.service.delegate.*;
import webbroker3.util.*;

public class WEB3SockPoolCtrlHandler
    implements MessageHandler
{
    /**
     * ログ出力ユーティリティ。
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3SockPoolCtrlHandler.class);

    /**
     * @@roseuid 40AC7C5103A5
     */
    public WEB3SockPoolCtrlHandler()
    {

    }

    public WEB3SockPoolCtrlResponse handleWEB3SockPoolCtrlRequest(WEB3SockPoolCtrlRequest
        l_request)
    {

        final String STR_METHOD_NAME =
            "handleWEB3SockPoolCtrlRequest(WEB3SockPoolCtrlRequest)";
        log.entering(STR_METHOD_NAME);

        WEB3SockPoolCtrlResponse l_response = null;
        try
        {
            WEB3SockPoolControlService l_service = (WEB3SockPoolControlService) Services
                .getService(WEB3SockPoolControlService.class);

            log.exiting(STR_METHOD_NAME);
            return (WEB3SockPoolCtrlResponse) l_service.execute(l_request);
        }
        catch (Exception l_exception)
        {
            l_response = (WEB3SockPoolCtrlResponse) l_request.createResponse();
            log.error(l_exception.getMessage(), l_exception);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

    }
}
@
