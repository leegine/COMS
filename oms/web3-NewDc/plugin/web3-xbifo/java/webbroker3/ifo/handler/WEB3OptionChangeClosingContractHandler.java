head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.14;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3OptionChangeClosingContractHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 株価指数オプション訂正返済ハンドラクラス(WEB3OptionChangeClosingContractHandler.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/06/14 李海波 (中訊) 新規作成
*/

package webbroker3.ifo.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;

import webbroker3.ifo.message.WEB3OptionsCloseMarginChangeConfirmResponse;
import webbroker3.ifo.message.WEB3OptionsCloseMarginChangeConfirmRequest;
import webbroker3.ifo.message.WEB3OptionsCloseMarginChangeCompleteResponse;
import webbroker3.ifo.message.WEB3OptionsCloseMarginChangeCompleteRequest;
import webbroker3.ifo.service.delegate.WEB3OptionChangeClosingContractService;

import webbroker3.util.WEB3LogUtility;

/**
 * (OP訂正返済ハンドラ)<BR>
 * 株価指数オプション訂正返済ハンドラクラス<BR>
 * @@author 李海波
 * @@version 1.0
 */
public class WEB3OptionChangeClosingContractHandler implements MessageHandler
{

    /**
     * ログ出力ユーティリティ。
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3OptionChangeClosingContractHandler.class);

    /**
     * @@roseuid 40C075590222
     */
    public WEB3OptionChangeClosingContractHandler()
    {

    }

    /**
     * (confirm訂正返済)<BR>
     * 株価指数オプションの訂正返済発注審査を行う。<BR>
     * <BR>
     * 株価指数オプション訂正返済サービスを取得し、<BR>execute()メソッドをコールする。<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * 株価指数オプション訂正返済確認リクエスト<BR>
     * @@return webbroker3.ifo.message.WEB3OptionsCloseMarginChangeConfirmResponse
     * @@roseuid 4056D0FE004B
     */
    public WEB3OptionsCloseMarginChangeConfirmResponse confirmChangeClosingContract(
        WEB3OptionsCloseMarginChangeConfirmRequest l_request)
    {

        final String STR_METHOD_NAME =
            getClass().getName() +
            ".confirmChangeClosingContract(WEB3OptionsCloseMarginChangeConfirmRequest l_request)";

        log.entering(STR_METHOD_NAME);

        WEB3OptionsCloseMarginChangeConfirmResponse l_response = null;
        WEB3OptionChangeClosingContractService l_service = null;
        log.debug("l_response = " + l_response);
        log.debug("l_service = " + l_service);
        //株価指数オプション訂正新規建サービスを取得する
        try
        {

            l_service =
                (WEB3OptionChangeClosingContractService)Services.getService(
                WEB3OptionChangeClosingContractService.class);

        }

        //サービスで例外が発生した場合は、エラー情報をレスポンスメッセージに設定する
        catch (Exception l_ex)
        {

            l_response = (WEB3OptionsCloseMarginChangeConfirmResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "株価指数オプション訂正返済サービスの取得に失敗しました。",
                l_response.errorInfo,
                l_ex);

            return l_response;

        }

        //株価指数オプション訂正新規建サービス.execute()メソッドをコールする
        try
        {

            l_response =
                (WEB3OptionsCloseMarginChangeConfirmResponse)l_service.execute(l_request);

        }
        catch (WEB3BaseException l_ex)
        {

            l_response = (WEB3OptionsCloseMarginChangeConfirmResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request, "株価指数オプション訂正返済に失敗しました。", l_ex);
            return l_response;

        }
        catch (WEB3BaseRuntimeException l_rex)
        {
            l_response = (WEB3OptionsCloseMarginChangeConfirmResponse)l_request.createResponse();
            l_response.errorInfo = l_rex.getErrorInfo();
            log.error(l_request, "株価指数オプション訂正返済に失敗しました。", l_rex);
            return l_response;
        }

        log.exiting(STR_METHOD_NAME);

        //レスポンスオブジェクトを返却する。
        return l_response;

    }

    /**
     * (complete訂正返済)<BR>
     * 株価指数オプションの訂正返済注文を登録する。<BR>
     * <BR>
     * 株価指数オプション訂正返済サービスを取得し、<BR>execute()メソッドをコールする。<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * 株価指数オプション訂正返済完了リクエスト<BR>
     * @@return webbroker3.ifo.message.WEB3OptionsCloseMarginChangeCompleteResponse
     * @@roseuid 4056D0FE006B
     */
    public WEB3OptionsCloseMarginChangeCompleteResponse completeChangeClosingContract(
        WEB3OptionsCloseMarginChangeCompleteRequest l_request)
    {

        final String STR_METHOD_NAME =
            getClass().getName() +
            ".completeChangeClosingContract(WEB3OptionsCloseMarginChangeCompleteRequest l_request)";


        log.entering(STR_METHOD_NAME);

        WEB3OptionsCloseMarginChangeCompleteResponse l_response = null;
        WEB3OptionChangeClosingContractService l_service = null;

        //株価指数オプション訂正新規建サービスを取得する
        try
        {

            l_service =
                (WEB3OptionChangeClosingContractService)Services.getService(
                WEB3OptionChangeClosingContractService.class);

        }
        //サービスで例外が発生した場合は、エラー情報をレスポンスメッセージに設定する
        catch (Exception l_ex)
        {

            l_response = (WEB3OptionsCloseMarginChangeCompleteResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "株価指数オプション訂正返済サービスの取得に失敗しました。",
                l_response.errorInfo,
                l_ex);

            return l_response;

        }

        //株価指数オプション訂正新規建サービス.execute()メソッドをコールする
        //株価指数オプション訂正新規建サービス.execute()メソッドをコールする
        try
        {

            l_response =
                (WEB3OptionsCloseMarginChangeCompleteResponse)l_service.execute(l_request);

        }
        catch (WEB3BaseException l_ex)
        {

            l_response = (WEB3OptionsCloseMarginChangeCompleteResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request, "株価指数オプション訂正返済に失敗しました。", l_ex);
            return l_response;

        }
        catch (WEB3BaseRuntimeException l_rex)
        {
            l_response = (WEB3OptionsCloseMarginChangeCompleteResponse)l_request.createResponse();
            l_response.errorInfo = l_rex.getErrorInfo();
            log.error(l_request, "株価指数オプション訂正返済に失敗しました。", l_rex);
            return l_response;
        }

        log.exiting(STR_METHOD_NAME);

        //レスポンスオブジェクトを返却する。
        return l_response;

    }
}
@
