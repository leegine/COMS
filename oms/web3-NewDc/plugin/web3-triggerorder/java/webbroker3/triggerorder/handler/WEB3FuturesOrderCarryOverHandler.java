head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.58.29;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8704d7ec30c0c9d;
filename	WEB3FuturesOrderCarryOverHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 株価指数先物注文繰越ハンドラクラス(WEB3FuturesOrderCarryOverHandler.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/7/21 艾興 (中訊) 新規作成
Revesion History : 2007/7/18 趙林鵬 (中訊) WEB3-ES-IFO-A-FT-0059
*/
package webbroker3.triggerorder.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.triggerorder.message.WEB3FuturesOrderCarryOverRequest;
import webbroker3.triggerorder.message.WEB3FuturesOrderCarryOverResponse;
import webbroker3.triggerorder.service.delegate.WEB3FuturesOrderCarryOverService;
import webbroker3.util.WEB3LogUtility;

/**
 * (株価指数先物注文繰越ハンドラ)<BR>
 * 株価指数先物注文繰越ハンドラクラス<BR>
 */
public class WEB3FuturesOrderCarryOverHandler implements MessageHandler
{
    /**
       * ログ出力ユーティリティ。<BR>
       */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3FuturesOrderCarryOverHandler.class);
    /**
     * @@roseuid 40F7B07102CE
     */
    public WEB3FuturesOrderCarryOverHandler()
    {
    }

    /**
     * (先物注文繰越リクエスト)<BR>
     * 株価指数先物注文繰越サービスを取得し、execute()メソッドをコールする。<BR>
     * @@param l_request - リクエストデータ
     * @@return WEB3FuturesOrderCarryOverResponse
     */
    public WEB3FuturesOrderCarryOverResponse orderCarryOverRequest(WEB3FuturesOrderCarryOverRequest l_request)
    {

        final String STR_METHOD_NAME =
            getClass().getName()
                + ".orderCarryOverRequest(WEB3FuturesOrderCarryOverRequest l_request)";

        log.debug(STR_METHOD_NAME);

        WEB3FuturesOrderCarryOverResponse l_response = null;
        WEB3FuturesOrderCarryOverService l_service = null;

        try
        {
            l_service =
                (WEB3FuturesOrderCarryOverService)Services.getService(
                    WEB3FuturesOrderCarryOverService.class);
        }
        //サービスで例外が発生した場合は、エラー情報をレスポンスメッセージに設定する
        catch (Exception l_ex)
        {
            l_response =
                (WEB3FuturesOrderCarryOverResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "株価指数先物注文繰越サービスの取得に失敗しました。",
                l_response.errorInfo,
                l_ex);
            log.debug(STR_METHOD_NAME + " : END");
            return l_response;
        }

        try
        {
            l_response =
                (WEB3FuturesOrderCarryOverResponse)l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response =
                (WEB3FuturesOrderCarryOverResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request, "株価指数先物注文繰越に失敗しました。", l_ex);
            return l_response;
        }
        catch (WEB3BaseRuntimeException l_ex)
        {
            l_response =
                (WEB3FuturesOrderCarryOverResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request,
                "株価指数先物注文繰越に失敗しました。",
                l_response.errorInfo,
                l_ex);

            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

        log.debug(STR_METHOD_NAME);

        //レスポンスオブジェクトを返却する。
        return l_response;
    }
}
@
