head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.10.03;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	WEB3TPShortfallGenerationHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 不足金発生画面表示ハンドラ(WEB3TPShortfallGenerationHandler.java)
Author Name      : Daiwa Institute of Research
Revision History : 2008/10/17 劉剣（中訊）新規作成 モデルNo.312
*/
package webbroker3.tradingpower.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.tradingpower.message.WEB3TPShortfallGenerationRequest;
import webbroker3.tradingpower.message.WEB3TPShortfallGenerationResponse;
import webbroker3.tradingpower.service.delegate.WEB3TPAssetTradingPowerService;
import webbroker3.util.WEB3LogUtility;

/**
 * (不足金発生画面表示ハンドラ)<BR>
 * 不足金発生画面表示ハンドラクラス。<BR>
 * <BR>
 * @@author 劉剣
 * @@version 1.0
 */
public class WEB3TPShortfallGenerationHandler implements MessageHandler
{
    /**
     * ログ出力ユーティリティ。<BR>
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(
        WEB3TPShortfallGenerationHandler.class);

    /**
     * (コンストラクタ)<BR>
     */
    public WEB3TPShortfallGenerationHandler()
    {

    }

    /**
     * (create不足金発生画面)<BR>
     * 資産余力情報画面表示処理を行う。<BR>
     * <BR>
     * 資産余力情報画面表示サービスを取得し、<BR>
     * execute()メソッドをコールする。<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * 不足金発生状況画面表示リクエストクラス。<BR>
     * @@return WEB3TPShortfallGenerationResponse
     */
    public WEB3TPShortfallGenerationResponse createShortfallGenerationScreen(
        WEB3TPShortfallGenerationRequest l_request)
    {
        final String STR_METHOD_NAME = "createShortfallGenerationScreen(WEB3TPShortfallGenerationRequest)";
        log.entering(STR_METHOD_NAME);

        WEB3TPShortfallGenerationResponse l_response = null;
        WEB3TPAssetTradingPowerService l_service = null;

        //資産余力情報画面表示サービスを取得
        try
        {
            l_service = (WEB3TPAssetTradingPowerService)Services.getService(
                WEB3TPAssetTradingPowerService.class);
        }
        catch(Exception l_ex)
        {
            l_response = (WEB3TPShortfallGenerationResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "資産余力情報画面表示サービスを取得に失敗しました。",
                l_response.errorInfo,
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

        try
        {
            l_response =
                (WEB3TPShortfallGenerationResponse)l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response = (WEB3TPShortfallGenerationResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request,
                "資産余力情報画面表示処理に失敗しました。",
                l_response.errorInfo,
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        catch (WEB3BaseRuntimeException l_ex)
        {
            l_response = (WEB3TPShortfallGenerationResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request,
                "資産余力情報画面表示処理に失敗しました。",
                l_response.errorInfo,
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
}@
