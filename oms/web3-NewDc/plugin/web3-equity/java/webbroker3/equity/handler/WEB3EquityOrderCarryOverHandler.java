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
filename	WEB3EquityOrderCarryOverHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        :  注文繰越ハンドラ(WEB3EquityOrderCarryOverHandler.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/06/01 鄒政 (中訊) 新規作成
                   2005/01/06 岡村和明(SRA) JavaDoc修正
*/
package webbroker3.equity.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.equity.message.WEB3EquityOrderCarryOverRequest;
import webbroker3.equity.message.WEB3EquityOrderCarryOverResponse;
import webbroker3.equity.service.delegate.WEB3EquityOrderCarryOverService;
import webbroker3.util.WEB3LogUtility;

/**
 * （注文繰越ハンドラ）。
 * @@version 1.0
 */
public class WEB3EquityOrderCarryOverHandler implements MessageHandler
{
    /**
     * ログ出力ユーティリティ。
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3EquityOrderCarryOverHandler.class);

    /**
     * @@roseuid 40B2A1700289
     */
    public WEB3EquityOrderCarryOverHandler()
    {

    }

    /**
     * (complete繰越)<BR>
     * 注文繰越処理を行う。<BR>
     * <BR>
     * 注文繰越サービスを取得し、execute()メソッドをコールする。<BR>
     * @@param l_requestData - 注文繰越リクエストデータオブジェクト
     * @@return webbroker3.equity.message.WEB3EquityOrderCarryOverResponse
     * @@roseuid 4057AA490015
     */
    public WEB3EquityOrderCarryOverResponse completeCarryOver(WEB3EquityOrderCarryOverRequest l_requestData)
    {
        final String STR_METHOD_NAME =
            "completeCarryOver(WEB3EquityOrderCarryOverRequest)";
        log.entering(STR_METHOD_NAME);

        WEB3EquityOrderCarryOverResponse l_response = 
            (WEB3EquityOrderCarryOverResponse) 
            
                l_requestData.createResponse();
        WEB3EquityOrderCarryOverService l_service = null;

        try
        {
            //注文繰越サービスを取得し、execute()メソッドをコールする
            l_service =
                (WEB3EquityOrderCarryOverService)Services.getService(
                    WEB3EquityOrderCarryOverService.class);
        }
        catch (Exception e)
        {
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            l_response.errorInfo.error_debug_info = "注文繰越サービスの取得に失敗しました。";
            log.error(STR_METHOD_NAME + "___注文繰越サービスの取得に失敗しました。");
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

        // サービスで例外が発生した場合は、エラー情報をレスポンスメッセージに設定する
        try
        {
            l_response =
                (WEB3EquityOrderCarryOverResponse)l_service.execute(
                    l_requestData);
        }
        catch (WEB3BaseException e)
        {
            l_response.errorInfo = e.getErrorInfo();
            log.error(l_requestData, "注文繰越に失敗しました。", e);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        catch (WEB3BaseRuntimeException l_bre)
        {
            l_response.errorInfo = l_bre.getErrorInfo();
            log.error(l_requestData, "注文繰越に失敗しました。", l_bre);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
}
@
