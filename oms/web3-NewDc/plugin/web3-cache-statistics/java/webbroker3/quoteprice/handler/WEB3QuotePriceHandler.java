head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.03.51.58;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	WEB3QuotePriceHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/*
 Copyright        : (株)大和総研 証券ソリューションシステム第二部
 File Name        :　@時価終値保存ハンドラ(WEB3QuotePriceHandler.java)
 Author Name      : Daiwa Institute of Research
 Revesion History : 2006/08/06 劉(FLJ) 新規作成
 */
package webbroker3.quoteprice.handler;

import com.fitechlabs.xtrade.kernel.boot.*;
import com.fitechlabs.xtrade.kernel.handler.*;

import webbroker3.common.WEB3BaseException;
import webbroker3.quoteprice.message.*;
import webbroker3.quoteprice.service.delegate.*;
import webbroker3.util.*;

/**
 * （時価終値保存ハンドラ）。
 * @@version 1.0
 */
public class WEB3QuotePriceHandler
    implements MessageHandler
{
    /**
     * ログ出力ユーティリティ。
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3QuotePriceHandler.class);

    /**
     * @@roseuid 40AC7C5103A5
     */
    public WEB3QuotePriceHandler()
    {

    }

    /**
     * (handle時価終値保存)<BR>
     * 時価終値保存を実施する。<BR>
     * <BR>
     * 時価終値保存サービスを取得し、<BR>
     * execute()メソッドをコールする。<BR>
     * @@param l_reqeust - (リクエストデータ)<BR>
     * 時価終値保存リクエストオブジェクト<BR>
     * @@return WEB3QuotePriceResponse
     */
    public WEB3QuotePriceResponse handleWEB3QuotePriceRequest(WEB3QuotePriceRequest
        l_request)
    {

        final String STR_METHOD_NAME =
            "handleWEB3QuotePriceRequest(WEB3QuotePriceRequest)";
        log.entering(STR_METHOD_NAME);

        WEB3QuotePriceResponse l_response = null;
        try
        {
            WEB3QuotePriceMultiInstitutionService l_service = (
                WEB3QuotePriceMultiInstitutionService) Services
                .getService(WEB3QuotePriceMultiInstitutionService.class);
            l_response = (WEB3QuotePriceResponse) l_service.execute(l_request);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        catch (Exception l_exception)
        {
            l_response = (WEB3QuotePriceResponse) l_request.createResponse();
            l_response.quote_table = l_request.quote_table;
            if(l_exception instanceof WEB3BaseException)
            {
                l_response.errorInfo = ((WEB3BaseException)l_exception).getErrorInfo();
            }
            log.error(l_exception.getMessage(), l_exception);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

    }
}
@
