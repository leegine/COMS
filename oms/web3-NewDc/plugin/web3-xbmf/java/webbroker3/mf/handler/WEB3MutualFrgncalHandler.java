head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.03;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3MutualFrgncalHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 投資信託海外市場カレンダー登録ハンドラ(WEB3MutualFrgncalHandler.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/08/12 王蘭芬(中訊) 新規作成
*/

package webbroker3.mf.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.mf.message.WEB3AdminMutualFrgncalCompleteRequest;
import webbroker3.mf.message.WEB3AdminMutualFrgncalCompleteResponse;
import webbroker3.mf.message.WEB3AdminMutualFrgncalInputRequest;
import webbroker3.mf.message.WEB3AdminMutualFrgncalInputResponse;
import webbroker3.mf.message.WEB3AdminMutualFrgncalReferenceRequest;
import webbroker3.mf.message.WEB3AdminMutualFrgncalReferenceResponse;
import webbroker3.mf.service.delegate.WEB3AdminMutualFrgncalService;
import webbroker3.util.WEB3LogUtility;

/**
 * 投資信託海外市場カレンダー登録ハンドラ　@クラス
 * 
 * @@author 王蘭芬(中訊)
 * @@version 1.0
 */
public class WEB3MutualFrgncalHandler implements MessageHandler 
{
    
    /**
     * ログユーティリティ
     */
    private static WEB3LogUtility log = 
        WEB3LogUtility.getInstance(WEB3MutualFrgncalHandler.class);

    /**
     * (海外市場カレンダー登録入力リクエスト)<BR>
     * 投資信託海外市場カレンダー登録入力処理を行う。<BR>
     * <BR>
     * 投信海外市場カレンダー登録サービスを取得し、execute()メソッド<BR>
     * をコールする。<BR>
     * @@param l_request - リクエストデータ
     * @@return webbroker3.mf.message.WEB3AdminMutualFrgncalInputResponse
     * @@roseuid 40D80F5B00B9
     */
    public WEB3AdminMutualFrgncalInputResponse frgncalInputRequest(
        WEB3AdminMutualFrgncalInputRequest l_request) 
    {
        final String l_strMethodName = "frgncalInputRequest("
            + "WEB3AdminMutualFrgncalInputRequest l_request ";
        log.entering(l_strMethodName);
        WEB3AdminMutualFrgncalService l_service;
        WEB3AdminMutualFrgncalInputResponse l_response;
        try
        {
            l_service = 
                (WEB3AdminMutualFrgncalService)Services.getService(
                    WEB3AdminMutualFrgncalService.class);
        }
        // サービスで例外が発生した場合は、エラー情報をレスポンスメッセージに設定する
        catch (Exception l_ex)
        {
            l_response =
                (WEB3AdminMutualFrgncalInputResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "投信海外市場カレンダー登録サービスの取得に失敗しました。",
                l_response.errorInfo,
                l_ex);
            return l_response;
        }
        try
        {
            l_response =
                (WEB3AdminMutualFrgncalInputResponse)l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response =
                (WEB3AdminMutualFrgncalInputResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request, "投信海外市場カレンダー登録入力に失敗しました。", l_ex);
            return l_response;
        }

        log.exiting(l_strMethodName);

        return l_response;
    }
    
    /**
     * (search海外市場カレンダー登録)<BR>
     * 投資信託海外市場カレンダー登録照会処理を行う。<BR>
     * <BR>
     * 投信海外市場カレンダー登録サービスを取得し、execute()メソッド<BR>
     * をコールする。<BR>
     * @@param l_request - リクエストデータ
     * @@return webbroker3.mf.message.WEB3AdminMutualFrgncalReferenceResponse
     * @@roseuid 40D80F5B00C9
     */
    public WEB3AdminMutualFrgncalReferenceResponse searchFrgncalReg(
        WEB3AdminMutualFrgncalReferenceRequest l_request) 
    {
        final String l_strMethodName = "searchFrgncalReg("
            + "WEB3AdminMutualFrgncalReferenceRequest l_request ";
        log.entering(l_strMethodName);
        WEB3AdminMutualFrgncalService l_service;
        WEB3AdminMutualFrgncalReferenceResponse l_response;
        try
        {
            l_service = 
                (WEB3AdminMutualFrgncalService)Services.getService(
                    WEB3AdminMutualFrgncalService.class);
        }
        // サービスで例外が発生した場合は、エラー情報をレスポンスメッセージに設定する
        catch (Exception l_ex)
        {
            l_response =
                (WEB3AdminMutualFrgncalReferenceResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "投信海外市場カレンダー登録サービスの取得に失敗しました。",
                l_response.errorInfo,
                l_ex);
            return l_response;
        }
        try
        {
            l_response =
                (WEB3AdminMutualFrgncalReferenceResponse)l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response =
                (WEB3AdminMutualFrgncalReferenceResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request, "投資信託海外市場カレンダー登録照会処理に失敗しました。", l_ex);
            return l_response;
        }

        log.exiting(l_strMethodName);

        return l_response;
    }
    
    /**
     * (complete海外市場カレンダー登録)<BR>
     * 投資信託海外市場カレンダー登録完了処理を行う。<BR>
     * <BR>
     * 投信海外市場カレンダー登録サービスを取得し、execute()メソッド<BR>
     * をコールする。<BR>
     * @@param l_request - リクエストデータ
     * 
     * @@return webbroker3.mf.message.WEB3AdminMutualFrgncalCompleteResponse
     * @@roseuid 40D80F5B00F8
     */
    public WEB3AdminMutualFrgncalCompleteResponse completeFrgncalReg(
        WEB3AdminMutualFrgncalCompleteRequest l_request) 
    {
        final String l_strMethodName = "completeFrgncalReg("
            + "WEB3AdminMutualFrgncalCompleteRequest l_request ";
        log.entering(l_strMethodName);
        WEB3AdminMutualFrgncalService l_service;
        WEB3AdminMutualFrgncalCompleteResponse l_response;
        try
        {
            l_service = 
                (WEB3AdminMutualFrgncalService)Services.getService(
                    WEB3AdminMutualFrgncalService.class);
        }
        // サービスで例外が発生した場合は、エラー情報をレスポンスメッセージに設定する
        catch (Exception l_ex)
        {
            l_response =
                (WEB3AdminMutualFrgncalCompleteResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "投信海外市場カレンダー登録サービスの取得に失敗しました。",
                l_response.errorInfo,
                l_ex);
            return l_response;
        }
        try
        {
            l_response =
                (WEB3AdminMutualFrgncalCompleteResponse)l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response =
                (WEB3AdminMutualFrgncalCompleteResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request, "投資信託海外市場カレンダー登録完了処理に失敗しました。", l_ex);
            return l_response;
        }

        log.exiting(l_strMethodName);

        return l_response;
    }
}
@
