head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.43;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3FeqCancelHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 外国株式取消ハンドラ(WEB3FeqCancelHandler)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/07/14 韋念瓊 (中訊) 新規作成       
                 : 2005/08/03 鄭海良(中訊) レビュー       
*/

package webbroker3.feq.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.feq.message.WEB3FeqCancelCompleteRequest;
import webbroker3.feq.message.WEB3FeqCancelCompleteResponse;
import webbroker3.feq.message.WEB3FeqCancelConfirmRequest;
import webbroker3.feq.message.WEB3FeqCancelConfirmResponse;
import webbroker3.feq.service.delegate.WEB3FeqCancelService;
import webbroker3.util.WEB3LogUtility;

/**
 * (外国株式取消ハンドラ)<BR>
 * 外国株式取消ハンドラクラス
 * 
 * @@author 韋念瓊(中訊)
 * @@version 1.0
 */
public class WEB3FeqCancelHandler implements MessageHandler 
{
    /**
     * ログ出力ユーティリティ。
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3FeqCancelHandler.class);
    
    /**
     * @@roseuid 42D0DA190109
     */
    public WEB3FeqCancelHandler() 
    {
     
    }
    
    /**
     * (validate注文)<BR>
     * 取消注文の確認処理を行う。<BR>
     * <BR>
     * 外国株式取消サービスを取得し、execute()メソッドをコールする。<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * 
     * @@return WEB3FeqCancelConfirmResponse
     * @@roseuid 429ADDB40239
     */
    public WEB3FeqCancelConfirmResponse validateOrder(
            WEB3FeqCancelConfirmRequest l_request) 
    {
        final String STR_METHOD_NAME = 
            "validateOrder(WEB3FeqCancelConfirmRequest l_request)";
        log.entering(STR_METHOD_NAME);
        
        //外国株式取消サービスインターフェイス
        WEB3FeqCancelService l_service = null;
         
        //外国株式取消確認レスポンス
        WEB3FeqCancelConfirmResponse l_response = null;
         
        try
        {
            l_service = 
                (WEB3FeqCancelService) Services.getService(
                        WEB3FeqCancelService.class);
        }
        catch (Exception l_ex)
        {
            //サービスで例外が発生した場合は、
            //エラー情報をレスポンスメッセージに設定する
            l_response =
                (WEB3FeqCancelConfirmResponse) l_request.createResponse();
            l_response.errorInfo = 
                WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "外国株式取消サービスの取得に失敗しました。",
                l_response.errorInfo,
                l_ex);
            return l_response;
        }
        try
        {
            l_response =
                (WEB3FeqCancelConfirmResponse) l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response =
                (WEB3FeqCancelConfirmResponse) l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request, 
                "取消注文の確認処理に失敗しました。", 
                l_ex);
            return l_response;
        }
         
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * (submit注文)<BR>
     * 取消注文の更新処理を行う。<BR>
     * <BR>
     * 外国株式取消サービスを取得し、execute()メソッドをコールする。<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * リクエストデータ
     * 
     * @@return WEB3FeqCancelCompleteResponse
     * @@roseuid 429ADDB40258
     */
    public WEB3FeqCancelCompleteResponse submitOrder(
            WEB3FeqCancelCompleteRequest l_request)
    {
        final String STR_METHOD_NAME = 
            "submitOrder(WEB3FeqCancelCompleteRequest l_request)";
        log.entering(STR_METHOD_NAME);
        
        //外国株式取消サービスインターフェイス
        WEB3FeqCancelService l_service = null;
         
        //外国株式取消完了レスポンス
        WEB3FeqCancelCompleteResponse l_response = null;
         
        try
        {
            l_service = 
                (WEB3FeqCancelService) Services.getService(
                        WEB3FeqCancelService.class);
        }
        catch (Exception l_ex)
        {
            //サービスで例外が発生した場合は、
            //エラー情報をレスポンスメッセージに設定する
            l_response =
                (WEB3FeqCancelCompleteResponse) l_request.createResponse();
            l_response.errorInfo = 
                WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "外国株式取消サービスの取得に失敗しました。",
                l_response.errorInfo,
                l_ex);
            return l_response;
        }
        try
        {
            l_response =
                (WEB3FeqCancelCompleteResponse) l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response =
                (WEB3FeqCancelCompleteResponse) l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request, 
                "取消注文の更新処理に失敗しました。", 
                l_ex);
            return l_response;
        }
         
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
}
@
