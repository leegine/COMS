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
filename	WEB3EquityOrderCarryOverSkipHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 株式注文繰越スキップ銘柄通知ハンドラ(WEB3EquityOrderCarryOverSkipHandler.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/05/26 鄒政 (中訊) 新規作成
                   2004/12/13 中尾寿彦(SRA) 残案件対応による修正
                   2005/01/06 岡村和明(SRA) JavaDoc修正
*/
package webbroker3.equity.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.equity.message.WEB3EquityCarryOverSkipRequest;
import webbroker3.equity.message.WEB3EquityCarryOverSkipResponse;
import webbroker3.equity.service.delegate.WEB3EquityOrderCarryOverSkipService;
import webbroker3.util.WEB3LogUtility;

/**
 * （株式注文繰越スキップ銘柄通知ハンドラ）。
 * @@version 1.0
 */
public class WEB3EquityOrderCarryOverSkipHandler implements MessageHandler
{

    /**
     * ログ出力ユーティリティ。
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3EquityOrderCarryOverSkipHandler.class);

    /**
     * @@roseuid 40B2E5C90102
     */
    public WEB3EquityOrderCarryOverSkipHandler()
    {

    }

    /**
     * (complete通知)<BR>
     * 注文繰越スキップ銘柄通知処理を行う。<BR>
     * <BR>
     * 注文繰越スキップ銘柄通知サービスを取得し、execute()メソッドをコールする。<BR>
     * <BR>
     * シーケンス図「（スキップ銘柄通知）繰越スキップ」参照。<BR>
     * @@param l_requestData - リクエストデータ<BR>
     * 注文繰越スキップ銘柄通知リクエストデータオブジェクト<BR>
     * @@return webbroker3.equity.message.WEB3EquityCarryOverSkipResponse
     * @@roseuid 4056CFB4002D
     */
    public WEB3EquityCarryOverSkipResponse completeNotify(WEB3EquityCarryOverSkipRequest l_requestData)
    {
        final String STR_METHOD_NAME =
            "completeNotify(WEB3EquityCarryOverSkipRequest)";
        log.entering(STR_METHOD_NAME);

        WEB3EquityCarryOverSkipResponse l_response = 
            (WEB3EquityCarryOverSkipResponse)l_requestData.createResponse();
        WEB3EquityOrderCarryOverSkipService l_service = null;
        try
        {
            //注文繰越スキップ銘柄通知サービスを取得し、execute()メソッドをコールする
            l_service =
                (WEB3EquityOrderCarryOverSkipService) Services.getService(
                    WEB3EquityOrderCarryOverSkipService.class);
        }
        catch (Exception e)
        {        
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            l_response.errorInfo.error_debug_info = "注文繰越スキップ銘柄通知サービスの取得に失敗しました。";
            log.error(STR_METHOD_NAME + "___注文繰越スキップ銘柄通知サービスの取得に失敗しました。");
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

        // サービスで例外が発生した場合は、エラー情報をレスポンスメッセージに設定する
        try
        {
            l_response =
                (WEB3EquityCarryOverSkipResponse)l_service.execute(
                    l_requestData);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_requestData, "注文繰越スキップ銘柄通知に失敗しました。", l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        catch (WEB3BaseRuntimeException l_bre)
        {
            l_response.errorInfo = l_bre.getErrorInfo();
            log.error(l_requestData, "注文繰越スキップ銘柄通知に失敗しました。", l_bre);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
}
@
