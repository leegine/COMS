head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.02.00.39;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8704d7ec30c0c9d;
filename	WEB3ToSuccOptionOpenContractInputHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : （連続）OP新規建入力ハンドラ(WEB3ToSuccOptionOpenContractInputHandler.java)
Author Name      : Daiwa Institute of Research
Revision History : 2008/04/10 于瀟(中訊) 新規作成モデル266
*/

package webbroker3.triggerorder.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.triggerorder.message.WEB3SuccOptionsOpenInputRequest;
import webbroker3.triggerorder.message.WEB3SuccOptionsOpenInputResponse;
import webbroker3.triggerorder.service.delegate.WEB3ToSuccOptionOpenContractInputService;
import webbroker3.util.WEB3LogUtility;

/**
 * (（連続）OP新規建入力ハンドラ)<BR>
 * （連続）OP新規建入力ハンドラクラス<BR>
 *
 * @@author 于瀟
 * @@version 1.0
 */
public class WEB3ToSuccOptionOpenContractInputHandler implements MessageHandler
{
    /**
     * ログ出力ユーティリティ。
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3ToSuccOptionOpenContractInputHandler.class);


    /**
     * @@roseuid 47FDBE3D02BF
     */
    public WEB3ToSuccOptionOpenContractInputHandler()
    {

    }

    /**
     * (新規建入力リクエスト)<BR>
     * （連続）OP新規建入力表示処理を行う。<BR>
     * <BR>
     * （連続）OP新規建入力サービスを取得し、execute()メソッドをコールする。<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * リクエストデータ<BR>
     * @@return WEB3SuccOptionsOpenInputResponse
     * @@roseuid 47A919F400C8
     */
    public WEB3SuccOptionsOpenInputResponse openContractInputRequest(WEB3SuccOptionsOpenInputRequest l_request)
    {
        final String STR_METHOD_NAME = "openContractInputRequest(WEB3SuccOptionsOpenInputRequest)";
        log.entering(STR_METHOD_NAME);

        WEB3SuccOptionsOpenInputResponse l_response = null;
        WEB3ToSuccOptionOpenContractInputService l_service = null;
        try
        {
            l_service = (WEB3ToSuccOptionOpenContractInputService)Services.getService(
                WEB3ToSuccOptionOpenContractInputService.class);
        }
        // サービスで例外が発生した場合は、エラー情報をレスポンスメッセージに設定する
        catch (Exception l_ex)
        {
            l_response =
                (WEB3SuccOptionsOpenInputResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "（連続）OP新規建入力サービスの取得に失敗しました。 ",
                l_response.errorInfo,
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        try
        {
            l_response =
                (WEB3SuccOptionsOpenInputResponse)l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response =
                (WEB3SuccOptionsOpenInputResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request,
                "（連続）OP新規建入力表示処理の実施に失敗しました。",
                l_response.errorInfo,
                l_ex);

            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        catch (WEB3BaseRuntimeException l_ex)
        {
            l_response =
                (WEB3SuccOptionsOpenInputResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request,
                "（連続）OP新規建入力表示処理の実施に失敗しました。",
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
