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
filename	WEB3FeqBalanceReferenceHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 外国株式残高照会ハンドラ(WEB3FeqBalanceReferenceHandler)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/07/14 韋念瓊 (中訊) 新規作成
                 : 2005/08/01 郭英(中訊) レビュー          
*/

package webbroker3.feq.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.feq.message.WEB3FeqBalanceReferenceRequest;
import webbroker3.feq.message.WEB3FeqBalanceReferenceResponse;
import webbroker3.feq.message.WEB3FeqBalanceReferenceTotalRequest;
import webbroker3.feq.message.WEB3FeqBalanceReferenceTotalResponse;
import webbroker3.feq.service.delegate.WEB3FeqBalanceReferenceService;
import webbroker3.util.WEB3LogUtility;

/**
 * (外国株式残高照会ハンドラ)<BR>
 * 外国株式残高照会ハンドラクラス
 * 
 * @@author 韋念瓊(中訊)
 * @@version 1.0
 */
public class WEB3FeqBalanceReferenceHandler implements MessageHandler 
{
    /**
     * ログ出力ユーティリティ。
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3FeqBalanceReferenceHandler.class);
    
    /**
     * @@roseuid 42D0DA19006D
     */
    public WEB3FeqBalanceReferenceHandler() 
    {
     
    }
    
    /**
     * (get残高照会)<BR>
     * 外国株式残高照会処理を行う。<BR>
     * <BR>
     * 外国株式残高照会サービスImplを取得し、execute()メソッドをコールする。<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * 外国株式残高照会リクエストオブジェクト
     * @@return webbroker3.feq.message.WEB3FeqBalanceReferenceResponse
     * @@roseuid 42A7F63602FA
     */
    public WEB3FeqBalanceReferenceResponse getBalanceReference(
        WEB3FeqBalanceReferenceRequest l_request) 
    {
        final String STR_METHOD_NAME = 
            "getBalanceReference(WEB3FeqBalanceReferenceRequest l_request)";
        log.entering(STR_METHOD_NAME);
        
        //外国株式残高照会サービスインターフェイス
        WEB3FeqBalanceReferenceService l_service = null;          
         
        //外国株式残高照会レスポンス
        WEB3FeqBalanceReferenceResponse l_response = null;     
         
        try
        {
            l_service = 
                (WEB3FeqBalanceReferenceService) Services.getService(
                    WEB3FeqBalanceReferenceService.class);
        }
        catch (Exception l_ex)
        {
            //サービスで例外が発生した場合は、
            //エラー情報をレスポンスメッセージに設定する
            l_response =
                (WEB3FeqBalanceReferenceResponse) l_request.createResponse();
            l_response.errorInfo = 
                WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "外国株式残高照会サービスの取得に失敗しました。",
                l_response.errorInfo,
                l_ex);
            return l_response;
        }
        try
        {
            l_response =
                (WEB3FeqBalanceReferenceResponse) l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response =
                (WEB3FeqBalanceReferenceResponse) l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request, 
                "外国株式残高照会処理に失敗しました。", 
                l_ex);
            return l_response;
        }
         
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * (get残高合計)<BR>
     * 外国株式残高合計処理を行う。<BR>
     * <BR>
     * 外国株式残高照会サービスImplを取得し、execute()メソッドをコールする。<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * 外国株式残高合計リクエストオブジェクト
     * @@return webbroker3.feq.message.WEB3FeqBalanceReferenceTotalResponse
     * @@roseuid 42A7F65B0154
     */
    public WEB3FeqBalanceReferenceTotalResponse getBalanceTotal(
        WEB3FeqBalanceReferenceTotalRequest l_request) 
    {
        final String STR_METHOD_NAME = 
            "getBalanceTotal(WEB3FeqBalanceReferenceTotalRequest l_request)";
        log.entering(STR_METHOD_NAME);
        
        //外国株式残高照会サービスインターフェイス
        WEB3FeqBalanceReferenceService l_service = null;          
         
        //外国株式残高合計レスポンス
        WEB3FeqBalanceReferenceTotalResponse l_response = null;     
         
        try
        {
            l_service = 
                (WEB3FeqBalanceReferenceService) Services.getService(
                    WEB3FeqBalanceReferenceService.class);
        }
        catch (Exception l_ex)
        {
            //サービスで例外が発生した場合は、
            //エラー情報をレスポンスメッセージに設定する
            l_response =
                (WEB3FeqBalanceReferenceTotalResponse) l_request.createResponse();
            l_response.errorInfo = 
                WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "外国株式残高照会サービスの取得に失敗しました。",
                l_response.errorInfo,
                l_ex);
            return l_response;
        }
        try
        {
            l_response =
                (WEB3FeqBalanceReferenceTotalResponse) l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response =
                (WEB3FeqBalanceReferenceTotalResponse) l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request, 
                "外国株式残高合計処理に失敗しました。", 
                l_ex);
            return l_response;
        }
         
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
}
@
