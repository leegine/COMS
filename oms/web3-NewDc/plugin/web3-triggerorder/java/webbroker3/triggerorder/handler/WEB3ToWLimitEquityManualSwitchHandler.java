head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.02.00.05;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8704d7ec30c0c9d;
filename	WEB3ToWLimitEquityManualSwitchHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 先株式W指値注文手動切替ハンドラ(WEB3ToWLimitEquityManualSwitchHandler.java)
Author Name      : Daiwa Institute of Research
Revision History : 2006/11/20　@齊珂(中訊) 新規作成 (モデル）No.184 191
*/

package webbroker3.triggerorder.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.triggerorder.message.WEB3EquityManualCompleteResponse;
import webbroker3.triggerorder.message.WEB3EquityManualConfirmResponse;
import webbroker3.triggerorder.message.WEB3EquityWLimitManualCompleteRequest;
import webbroker3.triggerorder.message.WEB3EquityWLimitManualConfirmRequest;
import webbroker3.triggerorder.service.delegate.WEB3ToWLimitEquityManualSwitchMainService;
import webbroker3.util.WEB3LogUtility;

/**
 * (株式W指値注文手動切替ハンドラ)<BR>
 *
 * @@author 齊珂
 * @@version 1.0
 */
public class WEB3ToWLimitEquityManualSwitchHandler implements MessageHandler
{
    /**
     * ログ出力ユーティリティ。<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3ToWLimitEquityManualSwitchHandler.class);

    /**
     * コンストラクタ<BR>
     * @@roseuid 43F491C700DA
     */
    public WEB3ToWLimitEquityManualSwitchHandler()
    {

    }

    /**
     * (confirm手動切替)<BR>
     * 株式W指値注文手動切替確認処理を行う。<BR>
     * <BR>
     * １）　@株式W指値注文手動切替メインサービスImplを取得し、<BR>
     * 　@　@　@execute()メソッドをコールする。 <BR>
     * <BR>
     * 　@　@　@　@[execute()に指定する引数] <BR>
     * 　@　@　@　@request：　@パラメータ.株式W指値注文手動切替確認リクエスト <BR>
     * <BR>
     * ２）　@execute()の戻り値を返す。<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * 株式W指値注文手動切替確認リクエスト
     * @@return WEB3EquityManualConfirmResponse
     */
    public WEB3EquityManualConfirmResponse confirmManualOrder(
        WEB3EquityWLimitManualConfirmRequest l_request)
    {
        final String STR_METHOD_NAME = "confirmManualOrder(" +
        "WEB3EquityWlimitManualConfirmRequest) ";

        WEB3EquityManualConfirmResponse l_response = null;
        WEB3ToWLimitEquityManualSwitchMainService l_service = null;

        try
        {
            //get株式W指値注文手動切替メインサービス
            l_service =
                (WEB3ToWLimitEquityManualSwitchMainService)Services.getService(
                    WEB3ToWLimitEquityManualSwitchMainService.class);
        }
        //サービスで例外が発生した場合は、エラー情報をレスポンスメッセージに設定する
        catch (Exception l_ex)
        {
            l_response = (WEB3EquityManualConfirmResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "株式W指値注文手動切替メインサービスの取得に失敗しました。",
                l_response.errorInfo,
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

        try
        {
            //execute()メソッドをコールする。
            l_response = (WEB3EquityManualConfirmResponse)l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response = (WEB3EquityManualConfirmResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request,
                "株式W指値注文手動切替確認処理の実施に失敗しました。",
                l_response.errorInfo,
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        catch (WEB3BaseRuntimeException l_ex)
        {
            l_response = (WEB3EquityManualConfirmResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request,
                "株式W指値注文手動切替確認処理の実施に失敗しました。",
                l_response.errorInfo,
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

        //execute()の戻り値を返す。
        log.entering(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (complete手動切替)<BR>
     * 株式W指値注文手動切替完了処理を行う。<BR>
     * <BR>
     * １）　@株式W指値注文手動切替メインサービスImplを取得し、<BR>
     * 　@　@　@execute()メソッドをコールする。<BR>
     * <BR>
     * 　@　@　@　@[execute()に指定する引数] <BR>
     * 　@　@　@　@request：　@パラメータ.株式W指値注文手動切替完了リクエスト <BR>
     * <BR>
     * ２）　@execute()の戻り値を返す。<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * 株式W指値注文手動切替完了リクエスト
     * @@return WEB3EquityManualCompleteResponse
     */
    public WEB3EquityManualCompleteResponse completeManualOrder(
        WEB3EquityWLimitManualCompleteRequest l_request)
    {
        final String STR_METHOD_NAME =
            "completeManualOrder(WEB3EquityWlimitManualCompleteRequest l_request) ";
        log.entering(STR_METHOD_NAME);

        WEB3EquityManualCompleteResponse l_response = null;
        WEB3ToWLimitEquityManualSwitchMainService l_service = null;

        try
        {
            //get株式W指値注文手動切替メインサービス
            l_service =
                (WEB3ToWLimitEquityManualSwitchMainService)Services.getService(
                    WEB3ToWLimitEquityManualSwitchMainService.class);
        }
        //サービスで例外が発生した場合は、エラー情報をレスポンスメッセージに設定する
        catch (Exception l_ex)
        {
            l_response = (WEB3EquityManualCompleteResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "株式W指値注文手動切替メインサービスの取得に失敗しました。",
                l_response.errorInfo,
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

        try
        {
            //execute()メソッドをコールする。
            l_response = (WEB3EquityManualCompleteResponse)l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response = (WEB3EquityManualCompleteResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request,
                "株式W指値注文手動切替完了処理の実施に失敗しました。",
                l_response.errorInfo,
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        catch (WEB3BaseRuntimeException l_ex)
        {
            l_response = (WEB3EquityManualCompleteResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request,
                "株式W指値注文手動切替完了処理の実施に失敗しました。",
                l_response.errorInfo,
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

        //execute()の戻り値を返す。
        log.entering(STR_METHOD_NAME);
        return l_response;
    }
}
@
