head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.13;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3OptionOpenContractInputHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright           : (株)大和総研 証券ソリューションシステム第二部
File Name           : OP新規建入力ハンドラ(WEB3OptionOpenContractInputHandler.java)
Author Name         : Daiwa Institute of Research
Revesion History    : 2004/06/19 呉艶飛 (中訊) 新規作成
                 001: 2004/07/31 王暁傑 (中訊) 対応バッグ WEB3_IFO_UT-000097
*/

package webbroker3.ifo.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.util.WEB3LogUtility;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;

import webbroker3.ifo.message.WEB3OptionsOpenMarginInputResponse;
import webbroker3.ifo.message.WEB3OptionsOpenMarginInputRequest;
import webbroker3.ifo.message.WEB3OptionsProductSelectRequest;
import webbroker3.ifo.message.WEB3OptionsProductSelectResponse;
import webbroker3.ifo.service.delegate.WEB3OptionOpenContractInputService;

/**
 * (OP新規建入力ハンドラ)<BR>
 * 株価指数オプション新規建入力ハンドラクラス<BR>
 * @@author 呉艶飛
 * @@version 1.0
 */
public class WEB3OptionOpenContractInputHandler implements MessageHandler
{
    /**
     * ログ出力ユーティリティ。
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(WEB3OptionOpenContractInputHandler.class);

    /**
     * @@roseuid 40C0755701A5
     */
    public WEB3OptionOpenContractInputHandler()
    {

    }

    /**
     * (新規建入力リクエスト)<BR>
     * <BR>
     * 株価指数オプション新規建入力表示処理を行う。<BR>
     * <BR>
     * 株価指数オプション新規建入力サービスを取得し、<BR>
     * execute()メソッドをコールする。<BR>
     * @@param l_request - リクエストデータ
     * @@return WEB3OptionsOpenMarginInputResponse
     * @@roseuid 407A50B00261
     */
    public WEB3OptionsOpenMarginInputResponse openContractInputRequest(WEB3OptionsOpenMarginInputRequest l_request)
    {
        final String STR_METHOD_NAME = "openContractInputRequest(WEB3OptionsOpenMarginInputRequest l_request)";
        log.entering(STR_METHOD_NAME);
        WEB3OptionsOpenMarginInputResponse l_response = null;
        WEB3OptionOpenContractInputService l_service = null;

        //株価指数オプション新規建入力表示処理を行う
        try
        {
            l_service = (WEB3OptionOpenContractInputService)Services.getService(WEB3OptionOpenContractInputService.class);

        }
        catch (Exception l_ex)
        {
            l_response = (WEB3OptionsOpenMarginInputResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(l_request, "株価指数オプション新規建入力サービス取得に失敗しました。", l_response.errorInfo, l_ex);

            return l_response;
        }
        try
        {
            //株価指数オプション新規建入力サービスを取得し、execute()メソッドをコールする。
            l_response = (WEB3OptionsOpenMarginInputResponse)l_service.execute(l_request);
        }
        //サービスで例外が発生した場合は、エラー情報をレスポンスメッセージに設定する
        catch (WEB3BaseException l_ex)
        {
            l_response = (WEB3OptionsOpenMarginInputResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request, "株価指数オプション新規建入力の表示処理に失敗しました。", l_ex);
            
            return l_response;
        }

        log.exiting(STR_METHOD_NAME);

        //レスポンスオブジェクトを返却する。
        return l_response;
    }

    /**
     * (新規建銘柄選択リクエスト)<BR>
     * <BR>
     * 株価指数オプション新規建銘柄選択表示処理を行う。<BR>
     * <BR>
     * 株価指数オプション新規建入力サービスを取得し、<BR>
     * execute()メソッドをコールする。<BR>
     * @@param l_request - リクエストデータ
     * @@return WEB3OptionsProductSelectResponse
     * @@roseuid 40A888A300FC
     */
    public WEB3OptionsProductSelectResponse openContractProductSelectRequest(WEB3OptionsProductSelectRequest l_request)
    {
        final String STR_METHOD_NAME = ".openContractProductSelectRequest(WEB3OptionsProductSelectRequest l_request)";
        log.entering(STR_METHOD_NAME);
        WEB3OptionsProductSelectResponse l_response = null;
        WEB3OptionOpenContractInputService l_service = null;

        //株価指数オプション新規建銘柄選択表示処理を行う
        try
        {
            l_service = (WEB3OptionOpenContractInputService)Services.getService(WEB3OptionOpenContractInputService.class);
        }
        catch (Exception l_ex)
        {

            l_response = (WEB3OptionsProductSelectResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(l_request, "株価指数オプション新規建入力サービス取得に失敗しました。", l_response.errorInfo, l_ex);

            return l_response;
        }
        //株価指数オプション新規建入力サービスを取得し、 execute()メソッドをコールする。
        try
        {
            l_response = (WEB3OptionsProductSelectResponse)l_service.execute(l_request);
        }
        //サービスで例外が発生した場合は、エラー情報をレスポンスメッセージに設定する
        catch (WEB3BaseException l_ex)
        {
            l_response = (WEB3OptionsProductSelectResponse)l_request.createResponse();
            
            l_response.errorInfo = l_ex.getErrorInfo();
            
            log.error(l_request, "株価指数オプション新規建入力の銘柄選択表示処理に失敗しました。", l_ex);
            
            return l_response;
        }

        log.exiting(STR_METHOD_NAME);

        //レスポンスオブジェクトを返却する。
        return l_response;
    }
}
@
