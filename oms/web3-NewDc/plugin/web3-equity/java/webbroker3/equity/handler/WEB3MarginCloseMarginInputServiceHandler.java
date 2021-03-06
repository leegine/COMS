head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.10;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3MarginCloseMarginInputServiceHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 信用取引返済入力ハンドラクラス(WEB3MarginCloseMarginInputServiceHandler.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/08 呉艶飛 (中訊) 新規作成
                   2005/01/06 岡村和明(SRA) JavaDoc修正
*/
package webbroker3.equity.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.equity.message.WEB3MarginCloseMarginInputRequest;
import webbroker3.equity.message.WEB3MarginCloseMarginInputResponse;
import webbroker3.equity.service.delegate.WEB3MarginCloseMarginInputService;
import webbroker3.util.WEB3LogUtility;

/**
 * （信用取引返済入力ハンドラ）。<BR>
 * <BR>
 * 信用取引返済入力ハンドラクラス
 * @@author 呉艶飛(中訊)
 * @@version 1.0
 */
public class WEB3MarginCloseMarginInputServiceHandler implements MessageHandler 
{
    
    /**
     * ログ出力ユーティリティ。
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3MarginCloseMarginInputServiceHandler.class);
    
    /**
     * (get返済入力画面)<BR>
     * 信用取引の返済入力画面表示処理を行う。<BR>
     * <BR>
     * 信用取引返済入力サービスを取得し、execute()メソッドをコールする。<BR>
     * @@param l_request - リクエストデータ<BR>
     * @@return WEB3MarginCloseMarginInputResponse
     * @@roseuid 40FF205B0134
     */
    public WEB3MarginCloseMarginInputResponse getCloseMarginInputScreen(WEB3MarginCloseMarginInputRequest l_request) 
    {
        final String STR_METHOD_NAME = "getCloseMarginInputScreen(WEB3MarginCloseMarginInputRequest)";
        log.entering(STR_METHOD_NAME);

        WEB3MarginCloseMarginInputResponse l_response = null;
        WEB3MarginCloseMarginInputService l_service = null;

        try
        {
            l_service =
                 (WEB3MarginCloseMarginInputService)Services.getService(
            WEB3MarginCloseMarginInputService.class);
        }
        // サービスで例外が発生した場合は、エラー情報をレスポンスメッセージに設定する
        catch (Exception l_ex)
        {
            l_response =
                (WEB3MarginCloseMarginInputResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "信用取引返済入力サービスの取得に失敗しました。",
                l_response.errorInfo,
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

        try
        {
            l_response =
                (WEB3MarginCloseMarginInputResponse)l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response =
                (WEB3MarginCloseMarginInputResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request, "信用取引返済入力表示に失敗しました。", l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        catch (WEB3BaseRuntimeException l_bre)
        {
            l_response =
                (WEB3MarginCloseMarginInputResponse)l_request.createResponse();
            l_response.errorInfo = l_bre.getErrorInfo();
            log.error(l_request, "信用取引返済入力表示に失敗しました。", l_bre);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

        log.exiting(STR_METHOD_NAME);

        return l_response;
    }
}
@
