head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.44;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3FeqNettingExchangeHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研ビジネス・イノベーション
File Name        : 外国株式為替ネッティングハンドラ(WEB3FeqNettingExchangeHandler.java)
Author Name      : Daiwa Institute of Research Business Innovation
Revision History : 2010/09/08 張騰宇 (中訊) 新規作成 モデル549
*/

package webbroker3.feq.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.feq.message.WEB3FeqNettingExchangeRequest;
import webbroker3.feq.message.WEB3FeqNettingExchangeResponse;
import webbroker3.feq.service.delegate.WEB3FeqNettingExchangeService;
import webbroker3.util.WEB3LogUtility;

/**
 * (外国株式為替ネッティングハンドラ)<BR>
 * 外国株式為替ネッティングハンドラクラス<BR>
 * <BR>
 * @@author 張騰宇
 * @@version 1.0
 */
public class WEB3FeqNettingExchangeHandler implements MessageHandler
{
    /**
     * ログ出力ユーティリティ。<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3FeqNettingExchangeHandler.class);

    /**
     * コンストラクタ。<BR>
     */
    public WEB3FeqNettingExchangeHandler()
    {

    }

    /**
     * (為替ネッティング)<BR>
     * 為替ネッティングを行う。<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * リクエストデータ<BR>
     * @@return WEB3FeqNettingExchangeResponse
     */
    public WEB3FeqNettingExchangeResponse nettingExchange(WEB3FeqNettingExchangeRequest l_request)
    {
        final String STR_METHOD_NAME = "nettingExchange(WEB3FeqNettingExchangeRequest)";
        log.entering(STR_METHOD_NAME);

        WEB3FeqNettingExchangeResponse l_response = null;
        WEB3FeqNettingExchangeService l_service = null;

        try
        {
            l_service = (WEB3FeqNettingExchangeService)Services.getService(
                WEB3FeqNettingExchangeService.class);
        }
        catch (Exception l_ex)
        {
            l_response = (WEB3FeqNettingExchangeResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "外国株式為替ネッティングサービスを取得に失敗しました。",
                l_response.errorInfo,
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

        //execute()メソッドをコールする
        try
        {
            l_response =
                (WEB3FeqNettingExchangeResponse)l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response = (WEB3FeqNettingExchangeResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request,
                "為替ネッティングを行う処理に失敗しました。",
                l_response.errorInfo,
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        catch (WEB3BaseRuntimeException l_ex)
        {
            l_response = (WEB3FeqNettingExchangeResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request,
                "為替ネッティングを行う処理に失敗しました。",
                l_response.errorInfo,
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
}
@
