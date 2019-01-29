head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.58.25;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8704d7ec30c0c9d;
filename	WEB3ToSuccMarginCancelHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/** 
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : （連続）信用取引取消ハンドラ(WEB3ToSuccMarginCancelHandler.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/11/08 黄　@浩澎 (中訊) 新規作成 
*/

package webbroker3.triggerorder.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.triggerorder.message.WEB3SuccMarginCancelCompleteRequest;
import webbroker3.triggerorder.message.WEB3SuccMarginCancelCompleteResponse;
import webbroker3.triggerorder.message.WEB3SuccMarginCancelConfirmRequest;
import webbroker3.triggerorder.message.WEB3SuccMarginCancelConfirmResponse;
import webbroker3.triggerorder.service.delegate.WEB3ToSuccMarginCancelService;
import webbroker3.util.WEB3LogUtility;

/**
 * (（連続）信用取引取消ハンドラ)<BR>
 * （連続）信用取引取消ハンドラクラス<BR>
 * 
 * @@author 　@黄　@浩澎(中訊)
 * @@version 1.0 
 */
public class WEB3ToSuccMarginCancelHandler implements MessageHandler 
{
    
    /**
     * ログ出力ユーティリティ。
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3ToSuccMarginCancelHandler.class);
    
    /**
     * @@roseuid 4369ED30034B
     */
    public WEB3ToSuccMarginCancelHandler() 
    {
     
    }
    
    /**
     * (confirm取消)<BR>
     * （連続）信用取引注文取消発注審査を行う<BR>
     * <BR>
     * （連続）信用取引取消サービスImplを取得し、<BR>
     * execute()メソッドをコールする。<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * （連続）信用取引注文取消確認リクエストオブジェクト<BR>
     * @@return WEB3SuccMarginCancelConfirmResponse
     * @@roseuid 433A2CC70000
     */
    public WEB3SuccMarginCancelConfirmResponse confirmCancel(WEB3SuccMarginCancelConfirmRequest l_request) 
    {
        final String STR_METHOD_NAME =
            " confirmCancel(WEB3SuccMarginCancelConfirmRequest)";
        log.entering(STR_METHOD_NAME);
        
        //（連続）信用取引取消サービス
        WEB3ToSuccMarginCancelService l_service = null;
        //（連続）信用取引注文取消確認レスポンスオブジェクト
        WEB3SuccMarginCancelConfirmResponse l_response = null;
        
        try
        {
            l_service =
                (WEB3ToSuccMarginCancelService) Services.getService(
                    WEB3ToSuccMarginCancelService.class);
        }
        catch (Exception l_ex)
        {
            //サービスで例外が発生した場合は、
            //エラー情報をレスポンスメッセージに設定する
            l_response =
                (WEB3SuccMarginCancelConfirmResponse) l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "（連続）信用取引取消サービスの取得に失敗しました。",
                l_response.errorInfo,
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        try
        {
            l_response =
                (WEB3SuccMarginCancelConfirmResponse) l_service.execute(
                    l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response =
                (WEB3SuccMarginCancelConfirmResponse) l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request, 
                "（連続）信用取引注文取消確認処理の実施に失敗しました。", 
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        catch (WEB3BaseRuntimeException l_ex)
        {
            l_response = (WEB3SuccMarginCancelConfirmResponse) l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request, 
                "（連続）信用取引注文取消確認処理の実施に失敗しました。",
                l_response.errorInfo, 
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        log.exiting(STR_METHOD_NAME);
        return l_response;    
    }
    
    /**
     * (complete取消)<BR>
     * （連続）信用取引注文取消処理を行う<BR>
     * <BR>
     * （連続）信用取引取消サービスImplを取得し、<BR>
     * execute()メソッドをコールする。<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * （連続）信用取引注文取消完了リクエストオブジェクト
     * @@return WEB3SuccMarginCancelCompleteResponse
     * @@roseuid 433A2D3202EE
     */
    public WEB3SuccMarginCancelCompleteResponse completeCancel(WEB3SuccMarginCancelCompleteRequest l_request) 
    {
        final String STR_METHOD_NAME =
            " confirmCancel(WEB3SuccMarginCancelConfirmRequest)";
        log.entering(STR_METHOD_NAME);
        
        //（連続）信用取引取消サービス
        WEB3ToSuccMarginCancelService l_service = null;
        //（連続）信用取引注文取消完了レスポンスオブジェクト
        WEB3SuccMarginCancelCompleteResponse l_response = null;
        
        try
        {
            l_service =
                (WEB3ToSuccMarginCancelService) Services.getService(
                    WEB3ToSuccMarginCancelService.class);
        }
        catch (Exception l_ex)
        {
            //サービスで例外が発生した場合は、
            //エラー情報をレスポンスメッセージに設定する
            l_response =
                (WEB3SuccMarginCancelCompleteResponse) l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "（連続）信用取引取消サービスの取得に失敗しました。",
                l_response.errorInfo,
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        try
        {
            l_response =
                (WEB3SuccMarginCancelCompleteResponse) l_service.execute(
                    l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response =
                (WEB3SuccMarginCancelCompleteResponse) l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request, 
                "（連続）信用取引注文取消完了処理の実施に失敗しました。", 
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        catch (WEB3BaseRuntimeException l_ex)
        {
            l_response = (WEB3SuccMarginCancelCompleteResponse) l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request, 
                "（連続）信用取引注文取消完了処理の実施に失敗しました。",
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
