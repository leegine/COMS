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
filename	WEB3AdminMutualConditionsReferenceHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者投資信託　@銘柄条件登録照会ハンドラ(WEB3AdminMutualConditionsReferenceHandler.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/08/12 王蘭芬(中訊) 新規作成
                   2004/08/20 黄建 (中訊) レビュー
*/

package webbroker3.mf.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.mf.message.WEB3AdminMutualConditionsRefInputRequest;
import webbroker3.mf.message.WEB3AdminMutualConditionsRefInputResponse;
import webbroker3.mf.message.WEB3AdminMutualConditionsReferenceRequest;
import webbroker3.mf.message.WEB3AdminMutualConditionsReferenceResponse;
import webbroker3.mf.service.delegate.WEB3AdminMutualConditionsReferenceService;
import webbroker3.util.WEB3LogUtility;

/**
 * 管理者投資信託　@銘柄条件登録照会ハンドラクラス<BR>
 * 
 * @@author 王蘭芬(中訊)
 * @@version 1.0
 */
public class WEB3AdminMutualConditionsReferenceHandler implements MessageHandler 
{
    
    /**
     * ログユーティリティ
     */

    private static WEB3LogUtility log = 
        WEB3LogUtility.getInstance(WEB3AdminMutualConditionsReferenceHandler.class);

    /**
     * (銘柄条件登録照会リクエスト)<BR>
     * 管理者投資信託　@銘柄条件登録照会入力処理を行う。<BR>
     * <BR>
     * 管理者投信銘柄条件登録照会サービスを取得し、execute()メソッド<BR>
     * をコールする。<BR>
     * @@param l_request - リクエストデータ<BR>
     * @@return webbroker3.mf.message.WEB3AdminMutualConditionsRefInputResponse
     * @@roseuid 40E4BF8903E2
     */
    public WEB3AdminMutualConditionsRefInputResponse productConditionsRegistRefRequest(
        WEB3AdminMutualConditionsRefInputRequest l_request) 
    {
        final String l_strMethodName = "productConditionsRegistRefRequest("
            + "WEB3AdminMutualConditionsRefInputRequest l_request ";
        log.entering(l_strMethodName);
        WEB3AdminMutualConditionsReferenceService l_service;
        WEB3AdminMutualConditionsRefInputResponse l_response;
        try
        {
            l_service = 
                (WEB3AdminMutualConditionsReferenceService)Services.getService(
                    WEB3AdminMutualConditionsReferenceService.class);
        }
        // サービスで例外が発生した場合は、エラー情報をレスポンスメッセージに設定する
        catch (Exception l_ex)
        {
            l_response =
                (WEB3AdminMutualConditionsRefInputResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "管理者投資信託　@銘柄条件登録照会サービスの取得に失敗しました。",
                l_response.errorInfo,
                l_ex);
            return l_response;
        }
        try
        {
            l_response =
                (WEB3AdminMutualConditionsRefInputResponse)l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response =
                (WEB3AdminMutualConditionsRefInputResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request, "管理者投資信託　@銘柄条件登録照会入力に失敗しました。", l_ex);
            return l_response;
        }

        log.exiting(l_strMethodName);

        return l_response;
    }
    
    /**
     * (search銘柄条件登録)<BR>
     * 管理者投資信託　@銘柄条件登録照会処理を行う。<BR>
     * <BR>
     * 管理者投信銘柄条件登録照会サービスを取得し、execute()メソッド<BR>
     * をコールする。<BR>
     * @@param l_request - リクエストデータ<BR>
     * @@return webbroker3.mf.message.WEB3AdminMutualConditionsReferenceResponse
     * @@roseuid 40E4BF9901A0
     */
    public WEB3AdminMutualConditionsReferenceResponse searchProductConditionsRegist(
        WEB3AdminMutualConditionsReferenceRequest l_request) 
    {
        final String l_strMethodName = "searchProductConditionsRegist("
            + "WEB3AdminMutualConditionsReferenceRequest l_request ";
        log.entering(l_strMethodName);
        WEB3AdminMutualConditionsReferenceService l_service;
        WEB3AdminMutualConditionsReferenceResponse l_response;
        try
        {
            l_service = 
                (WEB3AdminMutualConditionsReferenceService)Services.getService(
                    WEB3AdminMutualConditionsReferenceService.class);
        }
        // サービスで例外が発生した場合は、エラー情報をレスポンスメッセージに設定する
        catch (Exception l_ex)
        {
            l_response =
                (WEB3AdminMutualConditionsReferenceResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "管理者投資信託　@銘柄条件登録照会サービスの取得に失敗しました。",
                l_response.errorInfo,
                l_ex);
            return l_response;
        }
        try
        {
            l_response =
                (WEB3AdminMutualConditionsReferenceResponse)l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response =
                (WEB3AdminMutualConditionsReferenceResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request, "管理者投資信託　@銘柄条件登録照会に失敗しました。", l_ex);
            return l_response;
        }

        log.exiting(l_strMethodName);

        return l_response;
    }
}
@
