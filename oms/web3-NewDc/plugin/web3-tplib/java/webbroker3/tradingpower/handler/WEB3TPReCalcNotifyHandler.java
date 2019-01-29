head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.10.28;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	WEB3TPReCalcNotifyHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 余力計算通知ハンドラ(WEB3TPReCalcNotifyHandler.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/03/24 nakazato(ACT) 新規作成
*/
package webbroker3.tradingpower.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.tradingpower.message.WEB3TPReCalcNotifyRequest;
import webbroker3.tradingpower.message.WEB3TPReCalcNotifyResponse;
import webbroker3.tradingpower.service.delegate.WEB3TPReCalcNotifyService;
import webbroker3.util.WEB3LogUtility;

/**
 * (余力計算通知ハンドラ)
 */
public class WEB3TPReCalcNotifyHandler implements MessageHandler
{
    /**
     * ログ出力ユーティリティ。
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(WEB3TPReCalcNotifyHandler.class);

    /**
     * @@roseuid 423541390077
     */
    public WEB3TPReCalcNotifyHandler()
    {

    }

    /**
     * (余力計算通知リクエスト)<BR>
     * <BR>
     * クライアントよりリクエストを受け、余力計算通知レスポンスを生成し返却する。 <BR>
     * <BR>
     * １）　@余力計算通知サービスオブジェクトを取得する。 <BR>
     * <BR>
     * ２）　@余力計算通知サービスオブジェクト.execute（）をコールし、 <BR>
     * 　@　@　@処理結果である余力計算通知レスポンスオブジェクトを取得する。<BR> 
     * <BR>
     * ３）　@サービスで例外が発生した場合は、エラー情報をレスポンスメッセージに設定する。<BR> 
     * <BR>
     * ４）　@余力計算通知レスポンスオブジェクトを返却する。<BR>
     * <BR>
     * @@param l_reCalcRequest - (余力計算通知リクエスト)
     * @@return webbroker3.tradingpower.message.WEB3TPReCalcNotifyResponse
     * @@roseuid 41F4A66002A6
     */
    public WEB3TPReCalcNotifyResponse reCalcNotifyRequest(WEB3TPReCalcNotifyRequest l_reCalcRequest)
    {
        final String STR_METHOD_NAME = "reCalcNotifyRequest(WEB3TPReCalcNotifyRequest)";
        log.entering(STR_METHOD_NAME);

        WEB3TPReCalcNotifyResponse l_response = null;
        WEB3TPReCalcNotifyService l_service = null;

        /*
         * 余力計算通知サービスオブジェクトを取得
         */
        try
        {
            l_service =
                (WEB3TPReCalcNotifyService)Services.getService(WEB3TPReCalcNotifyService.class);
        }
        catch (Exception e)
        {
            l_response = (WEB3TPReCalcNotifyResponse)l_reCalcRequest.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(l_reCalcRequest, "余力計算通知サービスの取得に失敗しました。", l_response.errorInfo, e);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

        /*
         * 余力計算通知サービスオブジェクト.execute（）をコール
         */
        try
        {
            l_response = (WEB3TPReCalcNotifyResponse)l_service.execute(l_reCalcRequest);
        }
        catch (WEB3BaseException e)
        {
            l_response = (WEB3TPReCalcNotifyResponse)l_reCalcRequest.createResponse();
            l_response.errorInfo = e.getErrorInfo();
            log.error(l_reCalcRequest, "余力再計算通知処理に失敗しました。", e);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        catch (WEB3BaseRuntimeException l_bre)
        {
            l_response = (WEB3TPReCalcNotifyResponse)l_reCalcRequest.createResponse();
            l_response.errorInfo = l_bre.getErrorInfo();
            log.error(l_reCalcRequest, "余力再計算通知処理に失敗しました。", l_bre);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

        // 余力計算通知レスポンスオブジェクトを返却
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
}
@
