head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.03.51.45;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	WEB3QuoteCheckHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/*
 Copyright        : (株)大和総研 証券ソリューションシステム第二部
 File Name        :　@時価情報定点チェックハンドラ(WEB3QuoteCheckHandler.java)
 Author Name      : Daiwa Institute of Research
 Revesion History : 2009/02/03 許 (FLJ)新規作成
 */
package webbroker3.quotecheck.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.common.WEB3BaseException;
import webbroker3.quotecheck.message.WEB3QuoteCheckRequest;
import webbroker3.quotecheck.message.WEB3QuoteCheckResponse;
import webbroker3.quotecheck.service.delegate.WEB3QuoteCheckService;
import webbroker3.util.WEB3LogUtility;

/**
 * （時価情報定点チェックハンドラ）。
 * @@version 1.0
 */
public class WEB3QuoteCheckHandler
    implements MessageHandler
{
    /**
     * ログ出力ユーティリティ。
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3QuoteCheckHandler.class);

    /**
     * @@roseuid 40AC7C5103A5
     */
    public WEB3QuoteCheckHandler()
    {

    }

    /**
     * (handle時価情報定点チェック)<BR>
     * 時価情報定点チェックを実施する。<BR>
     * <BR>
     * 時価情報定点チェックサービスを取得し、<BR>
     * execute()メソッドをコールする。<BR>
     * @@param l_reqeust - (リクエストデータ)<BR>
     * 時価情報定点チェックリクエストオブジェクト<BR>
     * @@return WEB3QuoteCheckResponse
     */
    public WEB3QuoteCheckResponse handleWEB3QuoteCheckRequest(WEB3QuoteCheckRequest
        l_request)
    {

        final String STR_METHOD_NAME =
            "handleWEB3QuoteCheckRequest(WEB3QuoteCheckRequest)";
        log.entering(STR_METHOD_NAME);

        WEB3QuoteCheckResponse l_response = null;
        try
        {
            WEB3QuoteCheckService l_service = (WEB3QuoteCheckService) Services
                .getService(WEB3QuoteCheckService.class);
            l_response = (WEB3QuoteCheckResponse) l_service.execute(l_request);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        catch (Exception l_exception)
        {
            l_response = (WEB3QuoteCheckResponse) l_request.createResponse();
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
