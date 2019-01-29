head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.04.56.35;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	88c4d7eeee91bda;
filename	WEB3InformAccSwElecDeliApplyHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 口座切替・電子交付申込ハンドラ(WEB3InformAccSwElecDeliApplyHandler.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/07/19 栄イ(中訊) 新規作成 仕様変更モデル098
*/
package webbroker3.inform.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.inform.message.WEB3InformAccSwElecDeliApplyCmpRequest;
import webbroker3.inform.message.WEB3InformAccSwElecDeliApplyCmpResponse;
import webbroker3.inform.message.WEB3InformAccSwElecDeliApplyConfRequest;
import webbroker3.inform.message.WEB3InformAccSwElecDeliApplyConfResponse;
import webbroker3.inform.message.WEB3InformAccSwElecDeliApplyInpRequest;
import webbroker3.inform.message.WEB3InformAccSwElecDeliApplyInpResponse;
import webbroker3.inform.service.delegate.WEB3InformAccSwElecDeliApplyService;
import webbroker3.util.WEB3LogUtility;

/**
 * (口座切替・電子交付申込ハンドラ)<BR>
 * 口座切替・電子交付申込ハンドラ<BR>
 * <BR>
 * @@author 栄イ(中訊)
 * @@version 1.0
 */
public class WEB3InformAccSwElecDeliApplyHandler implements MessageHandler
{
    /**
     * (ログ出力ユーティリティ)
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3InformAccSwElecDeliApplyHandler.class);

    /**
     * @@roseuid 4663A9D502AF
     */
    public WEB3InformAccSwElecDeliApplyHandler()
    {

    }

    /**
     * (入力画面表示)<BR>
     * 口座切替・電子交付申込入力画面表示処理を行う。  <BR>
     * <BR>
     * 口座切替・電子交付申込サービスを取得し、execute()メソッドをコールする。 <BR>
     * @@param l_request - リクエストデータ
     * @@return WEB3InformAccSwElecDeliApplyInpResponse
     */
    public WEB3InformAccSwElecDeliApplyInpResponse displayInputScreen(
        WEB3InformAccSwElecDeliApplyInpRequest l_request)
    {
        final String STR_METHOD_NAME =
            "displayInputScreen(WEB3InformAccSwElecDeliApplyInpRequest)";
        log.exiting(STR_METHOD_NAME);

        WEB3InformAccSwElecDeliApplyInpResponse l_response = null;
        WEB3InformAccSwElecDeliApplyService l_service = null;

        //口座切替・電子交付申込サービスオブジェクトを取得
        try
        {
            l_service = (WEB3InformAccSwElecDeliApplyService)Services.getService(
                WEB3InformAccSwElecDeliApplyService.class);
        }
        catch (Exception l_ex)
        {
            //サービスで例外が発生した場合は、エラー情報をレスポンスメッセージに設定する
            l_response = (WEB3InformAccSwElecDeliApplyInpResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "口座切替・電子交付申込サービスを取得に失敗しました。",
                l_response.errorInfo,
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

        //execute()メソッドをコールする
        try
        {
            l_response =
                (WEB3InformAccSwElecDeliApplyInpResponse)l_service.execute(
                    l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response =
                (WEB3InformAccSwElecDeliApplyInpResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request,
                "検索入力が失敗しました。", l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

        log.exiting(STR_METHOD_NAME);
        //レスポンスオブジェクトを返却する。
        return l_response;
    }

    /**
     * (申込確認)<BR>
     * 口座切替・電子交付申込確認処理を行う。  <BR>
     * <BR>
     * 口座切替・電子交付申込サービスを取得し、execute()メソッドをコールする。  <BR>
     * @@param l_request - リクエストデータ
     * @@return WEB3InformAccSwElecDeliApplyConfResponse
     */
    public WEB3InformAccSwElecDeliApplyConfResponse applyConfirm(
        WEB3InformAccSwElecDeliApplyConfRequest l_request)
    {
        final String STR_METHOD_NAME =
            "applyConfirm(WEB3InformAccSwElecDeliApplyConfRequest)";
        log.exiting(STR_METHOD_NAME);

        WEB3InformAccSwElecDeliApplyConfResponse l_response = null;
        WEB3InformAccSwElecDeliApplyService l_service = null;

        //口座切替・電子交付申込サービスオブジェクトを取得
        try
        {
            l_service = (WEB3InformAccSwElecDeliApplyService)Services.getService(
                WEB3InformAccSwElecDeliApplyService.class);
        }
        catch (Exception l_ex)
        {
            //サービスで例外が発生した場合は、エラー情報をレスポンスメッセージに設定する
            l_response = (WEB3InformAccSwElecDeliApplyConfResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "口座切替・電子交付申込サービスを取得に失敗しました。",
                l_response.errorInfo,
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

        //execute()メソッドをコールする
        try
        {
            l_response =
                (WEB3InformAccSwElecDeliApplyConfResponse)l_service.execute(
                    l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response =
                (WEB3InformAccSwElecDeliApplyConfResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request,
                "申込確認が失敗しました。", l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

        log.exiting(STR_METHOD_NAME);
        //レスポンスオブジェクトを返却する。
        return l_response;
    }

    /**
     * (申込完了)<BR>
     * 口座切替・電子交付申込完了処理を行う。  <BR>
     * <BR>
     * 口座切替・電子交付申込サービスを取得し、execute()メソッドをコールする。  <BR>
     * @@param l_request - リクエストデータ
     * @@return WEB3InformAccSwElecDeliApplyCmpResponse
     */
    public WEB3InformAccSwElecDeliApplyCmpResponse applyComplete(
        WEB3InformAccSwElecDeliApplyCmpRequest l_request)
    {
        final String STR_METHOD_NAME =
            "applyComplete(WEB3InformAccSwElecDeliApplyCmpRequest)";
        log.exiting(STR_METHOD_NAME);

        WEB3InformAccSwElecDeliApplyCmpResponse l_response = null;
        WEB3InformAccSwElecDeliApplyService l_service = null;

        //口座切替・電子交付申込サービスオブジェクトを取得
        try
        {
            l_service = (WEB3InformAccSwElecDeliApplyService)Services.getService(
                WEB3InformAccSwElecDeliApplyService.class);
        }
        catch (Exception l_ex)
        {
            //サービスで例外が発生した場合は、エラー情報をレスポンスメッセージに設定する
            l_response = (WEB3InformAccSwElecDeliApplyCmpResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "口座切替・電子交付申込サービスを取得に失敗しました。",
                l_response.errorInfo,
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

        //execute()メソッドをコールする
        try
        {
            l_response =
                (WEB3InformAccSwElecDeliApplyCmpResponse)l_service.execute(
                    l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response =
                (WEB3InformAccSwElecDeliApplyCmpResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request,
                "申込完了が失敗しました。", l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

        log.exiting(STR_METHOD_NAME);
        //レスポンスオブジェクトを返却する。
        return l_response;
    }
}
@
