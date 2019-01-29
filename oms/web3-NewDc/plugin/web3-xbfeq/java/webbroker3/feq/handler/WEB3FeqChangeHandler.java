head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.44;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3FeqChangeHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 外国株式訂正ハンドラ(WEB3FeqChangeHandler)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/07/14 韋念瓊 (中訊) 新規作成       
                 : 2005/08/03 鄭海良(中訊) レビュー       
*/

package webbroker3.feq.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.feq.message.WEB3FeqChangeCompleteRequest;
import webbroker3.feq.message.WEB3FeqChangeCompleteResponse;
import webbroker3.feq.message.WEB3FeqChangeConfirmRequest;
import webbroker3.feq.message.WEB3FeqChangeConfirmResponse;
import webbroker3.feq.message.WEB3FeqChangeInputRequest;
import webbroker3.feq.message.WEB3FeqChangeInputResponse;
import webbroker3.feq.service.delegate.WEB3FeqChangeService;
import webbroker3.util.WEB3LogUtility;

/**
 * (外国株式訂正ハンドラ)<BR>
 * 外国株式訂正ハンドラクラス
 * 
 * @@author 韋念瓊(中訊)
 * @@version 1.0
 */
public class WEB3FeqChangeHandler implements MessageHandler 
{
    /**
     * ログ出力ユーティリティ。
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3FeqChangeHandler.class);
    
    /**
     * @@roseuid 42D0DA1902FD
     */
    public WEB3FeqChangeHandler() 
    {
     
    }
    
    /**
     * (get入力画面)<BR>
     * 入力画面の表示に必要なデータを取得する。<BR>
     * <BR>
     * 外国株式訂正サービスを取得し、execute()メソッドをコールする。<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * リクエストデータ
     * @@return WEB3FeqChangeInputResponse
     * @@roseuid 4295F0AF01F3
     */
    public WEB3FeqChangeInputResponse getInputScreen(
            WEB3FeqChangeInputRequest l_request)
    {
        final String STR_METHOD_NAME = 
            "getInputScreen(WEB3FeqChangeInputRequest l_request)";
        log.entering(STR_METHOD_NAME);
        
        //外国株式訂正サービスインターフェイス
        WEB3FeqChangeService l_service = null;
         
        //外国株式訂正入力レスポンス
        WEB3FeqChangeInputResponse l_response = null;
         
        try
        {
            l_service = 
                (WEB3FeqChangeService) Services.getService(
                        WEB3FeqChangeService.class);
        }
        catch (Exception l_ex)
        {
            //サービスで例外が発生した場合は、
            //エラー情報をレスポンスメッセージに設定する
            l_response =
                (WEB3FeqChangeInputResponse) l_request.createResponse();
            l_response.errorInfo = 
                WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "外国株式訂正サービスの取得に失敗しました。",
                l_response.errorInfo,
                l_ex);
            return l_response;
        }
        try
        {
            l_response =
                (WEB3FeqChangeInputResponse) l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response =
                (WEB3FeqChangeInputResponse) l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request, 
                "入力画面の表示処理に失敗しました。", 
                l_ex);
            return l_response;
        }
         
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * (validate注文)<BR>
     * 訂正注文の確認処理を行う。<BR>
     * <BR>
     * 外国株式訂正サービスを取得し、execute()メソッドをコールする。<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * リクエストデータ
     * @@return WEB3FeqChangeConfirmResponse
     * @@roseuid 4295F0AF01F5
     */
    public WEB3FeqChangeConfirmResponse validateOrder(
            WEB3FeqChangeConfirmRequest l_request)
    {
        final String STR_METHOD_NAME = 
            "validateOrder(WEB3FeqChangeConfirmRequest l_request)";
        log.entering(STR_METHOD_NAME);
        
        //外国株式訂正サービスインターフェイス
        WEB3FeqChangeService l_service = null;
         
        //外国株式訂正確認レスポンス
        WEB3FeqChangeConfirmResponse l_response = null;
         
        try
        {
            l_service = 
                (WEB3FeqChangeService) Services.getService(
                        WEB3FeqChangeService.class);
        }
        catch (Exception l_ex)
        {
            //サービスで例外が発生した場合は、
            //エラー情報をレスポンスメッセージに設定する
            l_response =
                (WEB3FeqChangeConfirmResponse) l_request.createResponse();
            l_response.errorInfo = 
                WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "外国株式訂正サービスの取得に失敗しました。",
                l_response.errorInfo,
                l_ex);
            return l_response;
        }
        try
        {
            l_response =
                (WEB3FeqChangeConfirmResponse) l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response =
                (WEB3FeqChangeConfirmResponse) l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request, 
                "訂正注文の確認処理に失敗しました。", 
                l_ex);
            return l_response;
        }
         
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * (submit注文)<BR>
     * 訂正注文の更新処理を行う。<BR>
     * <BR>
     * 外国株式訂正サービスを取得し、execute()メソッドをコールする。<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * リクエストデータ
     * @@return WEB3FeqChangeCompleteResponse
     * @@roseuid 4295F0AF01F7
     */
    public WEB3FeqChangeCompleteResponse submitOrder(
            WEB3FeqChangeCompleteRequest l_request)
    {
        final String STR_METHOD_NAME = 
            "submitOrder(WEB3FeqChangeCompleteRequest l_request)";
        log.entering(STR_METHOD_NAME);
        
        //外国株式訂正サービスインターフェイス
        WEB3FeqChangeService l_service = null;
         
        //外国株式訂正完了レスポンス
        WEB3FeqChangeCompleteResponse l_response = null;
         
        try
        {
            l_service = 
                (WEB3FeqChangeService) Services.getService(
                        WEB3FeqChangeService.class);
        }
        catch (Exception l_ex)
        {
            //サービスで例外が発生した場合は、
            //エラー情報をレスポンスメッセージに設定する
            l_response =
                (WEB3FeqChangeCompleteResponse) l_request.createResponse();
            l_response.errorInfo = 
                WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "外国株式訂正サービスの取得に失敗しました。",
                l_response.errorInfo,
                l_ex);
            return l_response;
        }
        try
        {
            l_response =
                (WEB3FeqChangeCompleteResponse) l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response =
                (WEB3FeqChangeCompleteResponse) l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request, 
                "訂正注文の更新処理に失敗しました。", 
                l_ex);
            return l_response;
        }
         
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
}
@
