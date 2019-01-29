head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.03.23.11;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	WEB3CacheMonitorHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/*
 Copyright        : (株)大和総研 証券ソリューションシステム第二部
 File Name        :　@(WEB3CacheMonitorHandler.java)
 Author Name      : Daiwa Institute of Research
 Revesion History : 2006/02/06 劉(FLJ) 新規作成
 */
package webbroker3.cachemonitor.handler;

import com.fitechlabs.xtrade.kernel.boot.*;
import com.fitechlabs.xtrade.kernel.handler.*;
import webbroker3.cachemonitor.message.*;
import webbroker3.cachemonitor.service.delegate.*;
import webbroker3.util.*;

public class WEB3CacheMonitorHandler
    implements MessageHandler
{
    /**
     * ログ出力ユーティリティ。
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3CacheMonitorHandler.class);

    /**
     * @@roseuid 40AC7C5103A5
     */
    public WEB3CacheMonitorHandler()
    {

    }

    public WEB3CacheMonitorResponse handleWEB3CacheMonitorRequest(WEB3CacheMonitorRequest
        l_request)
    {

        final String STR_METHOD_NAME =
            "handleWEB3CacheMonitorRequest(WEB3CacheMonitorRequest)";
        log.entering(STR_METHOD_NAME);

        WEB3CacheMonitorResponse l_response = null;
        try
        {
            WEB3CacheMonitorService l_service = (WEB3CacheMonitorService) Services
                .getService(WEB3CacheMonitorService.class);
            log.exiting(STR_METHOD_NAME);
            return (WEB3CacheMonitorResponse) l_service.execute(l_request);
        }
        catch (Exception l_exception)
        {
            l_response = (WEB3CacheMonitorResponse) l_request.createResponse();
            log.error(l_exception.getMessage(), l_exception);
            log.exiting(STR_METHOD_NAME);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

    }
}
@
