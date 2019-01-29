head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.02.00.34;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8704d7ec30c0c9d;
filename	WEB3OptionOrderCarryOverHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : OP注文繰越ハンドラ(WEB3OptionOrderCarryOverHandler.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/06/13 張威 (中訊) 新規作成
              001: 2004/07/26 王暁傑 (中訊)対応バッグ WEB3_IFO_UT-000026  log、orderCarryOverRequest()を修正
Revesion History : 2007/07/18 趙林鵬 (中訊) WEB3-ES-IFO-A-FT-0059
*/
package webbroker3.triggerorder.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.triggerorder.message.WEB3OptionsOrderCarryOverRequest;
import webbroker3.triggerorder.message.WEB3OptionsOrderCarryOverResponse;
import webbroker3.triggerorder.service.delegate.WEB3OptionOrderCarryOverService;
import webbroker3.util.WEB3LogUtility;

/**
 * (OP注文繰越ハンドラ)<BR>
 * 株価指数オプション注文繰越ハンドラクラス<BR>
 * @@author 呉艶飛
 * @@version 1.0
 */
public class WEB3OptionOrderCarryOverHandler implements MessageHandler
{

    /**
     * ログ出力ユーティリティ。<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3OptionOrderCarryOverHandler.class);

    /**
     * @@roseuid 40C0754F0177
     */
    public WEB3OptionOrderCarryOverHandler()
    {

    }

    /**
     * 株価指数オプション注文繰越サービスを取得し、execute()メソッドをコールする。
     */
    public WEB3OptionsOrderCarryOverResponse orderCarryOverRequest(WEB3OptionsOrderCarryOverRequest l_request)
    {

        final String STR_METHOD_NAME =
            getClass().getName() + ".getContract(WEB3OptionsOrderCarryOverRequest)";

        log.debug(STR_METHOD_NAME + " : ENTER");

        WEB3OptionsOrderCarryOverResponse l_response = null;
        WEB3OptionOrderCarryOverService l_service = null;

        try
        {
            log.debug("株価指数オプション注文繰越サービスを取得: ENTER");

            l_service =
                (WEB3OptionOrderCarryOverService)Services.getService(
                    WEB3OptionOrderCarryOverService.class);
            log.debug("株価指数オプション注文繰越サービスを取得: END");
        }
        //サービスで例外が発生した場合は、エラー情報をレスポンスメッセージに設定する
        catch (Exception l_ex)
        {
            l_response = (WEB3OptionsOrderCarryOverResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(l_request, "株価指数オプション注文繰越サービスの取得に失敗しました。", l_response.errorInfo, l_ex);
            log.debug(STR_METHOD_NAME + " : END");
            return l_response;
        }

        try
        {
            log.debug("株価指数オプション注文繰越サービスオブジェクト.execute（): ENTER");
            l_response = (WEB3OptionsOrderCarryOverResponse)l_service.execute(l_request);
            log.debug("株価指数オプション注文繰越サービスオブジェクト.execute（): END");
        }
        catch (WEB3BaseException l_ex)
        {
            l_response = (WEB3OptionsOrderCarryOverResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request, "株価指数オプション注文繰越に失敗しました。", l_ex);
            log.debug(STR_METHOD_NAME + " : END");
            return l_response;
        }
        catch (WEB3BaseRuntimeException l_ex)
        {
            l_response =
                (WEB3OptionsOrderCarryOverResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request,
                "株価指数オプション注文繰越に失敗しました。",
                l_response.errorInfo,
                l_ex);

            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

        log.debug(STR_METHOD_NAME + " : END");

        //レスポンスオブジェクトを返却する。
        return l_response;
    }
}
@
