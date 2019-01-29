head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.10.12;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	WEB3TPAdditionalGenerationHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
 Copyright        : (株)大和総研 証券ソリューションシステム第二部
 File Name        : 追証発生画面表示ハンドラ (WEB3AdditionalGenerationScreenHandler.java)
 Author Name      : Daiwa Institute of Research
 Revision History : 2008/10/17 陸文静 新規作成モデルNo.312
 */
package webbroker3.tradingpower.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.tradingpower.message.WEB3TPAdditionalGenerationRequest;
import webbroker3.tradingpower.message.WEB3TPAdditionalGenerationResponse;
import webbroker3.tradingpower.service.delegate.WEB3TPAssetTradingPowerService;
import webbroker3.util.WEB3LogUtility;

/**
 * (追証発生画面表示ハンドラ )<BR>
 * 追証発生画面表示ハンドラ 。<BR>
 * <BR>
 * @@author 陸文静
 * @@version 1.0
 */
public class WEB3TPAdditionalGenerationHandler implements MessageHandler
{

    /**
     * ログ出力ユーティリティ。<BR>
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(
        WEB3TPAdditionalGenerationHandler.class);

    /**
     * (コンストラクタ)<BR>
     */
    public WEB3TPAdditionalGenerationHandler()
    {

    }

    /**
     * (create追証発生画面)<BR>
     * 資産余力情報画面表示処理を行う。<BR>
     * <BR>
     * 資産余力情報画面表示サービスを取得し、<BR>
     * execute()メソッドをコールする。<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * 追証発生画面表示リクエストクラス。<BR>
     * @@return WEB3TPAdditionalGenerationResponse
     */
    public WEB3TPAdditionalGenerationResponse createAdditionalGenerationScreen(
        WEB3TPAdditionalGenerationRequest l_request)
    {
        final String STR_METHOD_NAME = "createAdditionalGenerationScreen(WEB3TPAdditionalGenerationRequest)";
        log.entering(STR_METHOD_NAME);

        WEB3TPAssetTradingPowerService l_service = null;
        WEB3TPAdditionalGenerationResponse l_response = null;

        try
        {
            //資産余力情報画面表示サービスを取得し
            l_service =
                (WEB3TPAssetTradingPowerService)Services.getService(
                    WEB3TPAssetTradingPowerService.class);
        }
        catch (Exception  l_ex)
        {
            l_response = (WEB3TPAdditionalGenerationResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "資産余力情報画面表示サービスの取得に失敗しました。",
                l_response.errorInfo,
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

        try
        {
            //追証発生画面表示レスポンス
            l_response =
                (WEB3TPAdditionalGenerationResponse)l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response =
                (WEB3TPAdditionalGenerationResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request,
                "追証発生画面表示処理に失敗しました。",
                l_response.errorInfo,
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        catch (WEB3BaseRuntimeException l_ex)
        {
            l_response =
                (WEB3TPAdditionalGenerationResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request,
                "追証発生画面表示処理に失敗しました。",
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
