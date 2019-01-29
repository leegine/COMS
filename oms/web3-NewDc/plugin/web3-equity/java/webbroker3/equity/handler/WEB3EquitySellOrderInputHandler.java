head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.11;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3EquitySellOrderInputHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 現物株式売付注文入力ハンドラクラス(WEB3EquitySellOrderInputHandler.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/05/10 鄭海良(中訊) 新規作成
                   2005/01/06 岡村和明(SRA) JavaDoc修正
*/
package webbroker3.equity.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.equity.message.WEB3EquitySellInputRequest;
import webbroker3.equity.message.WEB3EquitySellInputResponse;
import webbroker3.equity.service.delegate.WEB3EquitySellOrderInputService;
import webbroker3.util.WEB3LogUtility;

/**
 * （現物株式売付注文入力ハンドラ）。<BR>
 * <BR>
 * 現物株式売付注文入力ハンドラクラス
 * @@version 1.0
 */
public class WEB3EquitySellOrderInputHandler implements MessageHandler
{
    /**
     * ログ出力ユーティリティ。
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3EquitySellOrderInputHandler.class);

    /**
     * @@roseuid 4091F4290289
     */
    public WEB3EquitySellOrderInputHandler()
    {

    }

    /**
     * (売付注文入力リクエスト)<BR>
     * 現物株式売付注文入力表示処理を行う。<BR>
     * <BR>
     * 現物株式売付注文入力サービスを取得し、<BR>
     * execute()メソッドをコールする。<BR>
     * @@param l_request - 現物株式売付注文入力リクエストオブジェクト
     * @@return webbroker3.equity.message.WEB3EquitySellInputResponse
     * @@roseuid 40628C8000E5
     */
    public WEB3EquitySellInputResponse equitySellOrderInputRequest(WEB3EquitySellInputRequest l_request)
    {
        final String STR_METHOD_NAME = "equitySellOrderInputRequest(WEB3EquitySellInputRequest)";
        log.entering(STR_METHOD_NAME);

        WEB3EquitySellInputResponse l_response = null;
        WEB3EquitySellOrderInputService l_service = null;

        try
        {
            l_service =
                (WEB3EquitySellOrderInputService) Services.getService(
                    WEB3EquitySellOrderInputService.class);
        }
        catch (Exception e)
        {
            l_response =
                (WEB3EquitySellInputResponse) l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
            l_request,
                "株式売付注文入力サービスの取得に失敗しました。",
                l_response.errorInfo,
                e);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

        try
        {
            l_response =
                (WEB3EquitySellInputResponse) l_service.execute(l_request);
        }
        catch (WEB3BaseException e)
        {
            log.error(l_request, "株式売付注文入力に失敗しました。", e);
            l_response =
                (WEB3EquitySellInputResponse) l_request.createResponse();
            l_response.errorInfo = e.getErrorInfo();
        }
        catch (WEB3BaseRuntimeException l_bre)
        {
            log.error(l_request, "株式売付注文入力に失敗しました。", l_bre);
            l_response =
                (WEB3EquitySellInputResponse) l_request.createResponse();
            l_response.errorInfo = l_bre.getErrorInfo();
        }

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
}
@
