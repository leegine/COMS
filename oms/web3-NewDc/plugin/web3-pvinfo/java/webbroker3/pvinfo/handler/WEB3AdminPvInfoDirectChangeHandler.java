head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.22.08.09.14;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7444d8857e2568b;
filename	WEB3AdminPvInfoDirectChangeHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者ダイレクト指定変更ハンドラ(WEB3AdminPvInfoDirectChangeHandler.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/18 王亞洲(中訊) 新規作成
Revesion History : 2004/10/26 李弘毅(中訊) 作成
Revesion History : 2007/06/28 徐宏偉(中訊) 実装の問題003
*/
package webbroker3.pvinfo.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.pvinfo.message.WEB3AdminPvInfoDirectChangeCancelRequest;
import webbroker3.pvinfo.message.WEB3AdminPvInfoDirectChangeCancelResponse;
import webbroker3.pvinfo.message.WEB3AdminPvInfoDirectChangeCompleteRequest;
import webbroker3.pvinfo.message.WEB3AdminPvInfoDirectChangeCompleteResponse;
import webbroker3.pvinfo.message.WEB3AdminPvInfoDirectChangeConfirmRequest;
import webbroker3.pvinfo.message.WEB3AdminPvInfoDirectChangeConfirmResponse;
import webbroker3.pvinfo.message.WEB3AdminPvInfoDirectChangeInputRequest;
import webbroker3.pvinfo.message.WEB3AdminPvInfoDirectChangeInputResponse;
import webbroker3.pvinfo.service.delegate.WEB3AdminPvInfoDirectChangeService;
import webbroker3.util.WEB3LogUtility;

/**
 * (管理者ダイレクト指定変更ハンドラ)<BR>
 * 管理者ダイレクト指定変更ハンドラクラス<BR>
 * @@author 王亞洲
 * @@version 1.00
 */
public class WEB3AdminPvInfoDirectChangeHandler implements MessageHandler
{
    
    /**
     * ログ出力ユーティリティ。<BR>
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(WEB3AdminPvInfoDirectChangeHandler.class);

    /**
     * (getダイレクト指定変更入力画面)<BR>
     * ダイレクト指定変更入力画面表示処理を行う。<BR>
     * <BR>
     * 管理者ダイレクト指定変更サービスImplを取得し、<BR>
     * execute()メソッドをコールする。<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * 管理者・ダイレクト指定変更入力リクエストオブジェクト<BR>
     * @@return webbroker3.pvinfo.message.WEB3AdminPvInfoDirectChangeInputResponse
     * @@roseuid 416101C501E0
     */
    public WEB3AdminPvInfoDirectChangeInputResponse getDirectChangeInputScreen(WEB3AdminPvInfoDirectChangeInputRequest l_request)
    {
        
        final String STR_METHOD_NAME = " getDirectChangeInputScreen(WEB3AdminPvInfoDirectChangeInputRequest)";
        log.entering(STR_METHOD_NAME);
        
        WEB3AdminPvInfoDirectChangeInputResponse l_response = null;
        WEB3AdminPvInfoDirectChangeService l_service = null;
        
        //管理者ダイレクト指定変更サービスインタフェイス      
        try
        {
            l_service =
                (WEB3AdminPvInfoDirectChangeService)Services.getService(
                WEB3AdminPvInfoDirectChangeService.class);
        }
        catch (Exception l_ex)
        {
            l_response = (WEB3AdminPvInfoDirectChangeInputResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "管理者ダイレクト指定変更サービスの取得に失敗しました。",
                l_response.errorInfo, l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;    
        }
        
        //管理者ダイレクト指定変更サービス.execute()メソッドをコールする
        try
        {
            l_response = (WEB3AdminPvInfoDirectChangeInputResponse)l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response = (WEB3AdminPvInfoDirectChangeInputResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request, "管理者ダイレクト指定変更に失敗しました。", l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        
        log.exiting(STR_METHOD_NAME);

        //レスポンスオブジェクトを返却する。
        return l_response; 
    }

    /**
     * (confirmダイレクト指定変更)<BR>
     * ダイレクト指定変更確認処理を行う。<BR>
     * <BR>
     * 管理者ダイレクト指定変更サービスImplを取得し、<BR>
     * execute()メソッドをコールする。<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * 管理者・ダイレクト指定変更確認リクエストオブジェクト<BR>
     * @@return webbroker3.pvinfo.message.WEB3AdminPvInfoDirectChangeConfirmResponse
     * @@roseuid 416101C50200
     */
    public WEB3AdminPvInfoDirectChangeConfirmResponse confirmDirectChange(WEB3AdminPvInfoDirectChangeConfirmRequest l_request)
    {
        
        final String STR_METHOD_NAME = " confirmDirectChange(WEB3AdminPvInfoDirectChangeConfirmRequest)";
        log.entering(STR_METHOD_NAME);
        
        WEB3AdminPvInfoDirectChangeConfirmResponse l_response = null;
        WEB3AdminPvInfoDirectChangeService l_service = null;
        
        //管理者ダイレクト指定変更サービスを取得
        try
        {
            l_service = (WEB3AdminPvInfoDirectChangeService)Services.getService(WEB3AdminPvInfoDirectChangeService.class);
        }
        catch (Exception l_ex)
        {
            l_response = (WEB3AdminPvInfoDirectChangeConfirmResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "管理者ダイレクト指定変更サービスの取得に失敗しました。",
                l_response.errorInfo, l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;    
        }
        
        //管理者ダイレクト指定変更サービスImpl.execute()メソッドをコールする。
        try
        {
            l_response = (WEB3AdminPvInfoDirectChangeConfirmResponse)l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response = (WEB3AdminPvInfoDirectChangeConfirmResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            l_response.errorMessage = l_ex.getErrorMessage();
            log.error(l_request, "管理者ダイレクト指定変更に失敗しました。", l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        
        log.exiting(STR_METHOD_NAME);

        //レスポンスオブジェクトを返却する。
        return l_response;         
    }

    /**
     * (completeダイレクト指定変更)<BR>
     * ダイレクト指定変更完了処理を行う。<BR>
     * <BR>
     * 管理者ダイレクト指定変更サービスImplを取得し、<BR>
     * execute()メソッドをコールする。<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * 管理者・ダイレクト指定変更完了リクエストオブジェクト<BR>
     * @@return webbroker3.pvinfo.message.WEB3AdminPvInfoDirectChangeCompleteResponse
     * @@roseuid 416101C5020F
     */
    public WEB3AdminPvInfoDirectChangeCompleteResponse completeDirectChange(WEB3AdminPvInfoDirectChangeCompleteRequest l_request)
    {
        final String STR_METHOD_NAME = " completeDirectChange(WEB3AdminPvInfoDirectChangeCompleteRequest)";
        log.entering(STR_METHOD_NAME);
        
        WEB3AdminPvInfoDirectChangeCompleteResponse l_response = null;
        WEB3AdminPvInfoDirectChangeService l_service = null;
        
        //管理者ダイレクト指定変更サービスを取得
        try
        {
            l_service = (WEB3AdminPvInfoDirectChangeService)Services.getService(WEB3AdminPvInfoDirectChangeService.class);
        }
        catch (Exception l_ex)
        {
            l_response = (WEB3AdminPvInfoDirectChangeCompleteResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "管理者ダイレクト指定変更サービスの取得に失敗しました。",
                l_response.errorInfo, l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;    
        }
        
        //管理者ダイレクト指定変更サービスImpl.execute()メソッドをコールする。
        try
        {
            l_response = (WEB3AdminPvInfoDirectChangeCompleteResponse)l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response = (WEB3AdminPvInfoDirectChangeCompleteResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            l_response.errorMessage = l_ex.getErrorMessage();
            log.error(l_request, "管理者ダイレクト指定変更に失敗しました。", l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        
        log.exiting(STR_METHOD_NAME);

        //レスポンスオブジェクトを返却する。
        return l_response;
        
    }

    /**
     * (undoダイレクト指定変更アップロード)<BR>
     * ダイレクト指定変更アップロード中止処理を行う。<BR>
     * <BR>
     * 管理者ダイレクト指定変更サービスImplを取得し、<BR>
     * execute()メソッドをコールする。<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * 管理者・ダイレクト指定変更アップロード中止リクエストオブジェクト<BR>
     * @@return webbroker3.pvinfo.message.WEB3AdminPvInfoDirectChangeCancelResponse
     * @@roseuid 416101C5022E
     */
    public WEB3AdminPvInfoDirectChangeCancelResponse undoDirectChangeUpload(WEB3AdminPvInfoDirectChangeCancelRequest l_request)
    {
        final String STR_METHOD_NAME = " undoDirectChangeUpload(WEB3AdminPvInfoDirectChangeCancelRequest)";
        log.entering(STR_METHOD_NAME);
        
        WEB3AdminPvInfoDirectChangeCancelResponse l_response = null;
        WEB3AdminPvInfoDirectChangeService l_service = null;
        
        //管理者ダイレクト指定変更サービスを取得
        try
        {
            l_service = (WEB3AdminPvInfoDirectChangeService)Services.getService(WEB3AdminPvInfoDirectChangeService.class);
        }
        catch (Exception l_ex)
        {
            l_response = (WEB3AdminPvInfoDirectChangeCancelResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "管理者ダイレクト指定変更サービスの取得に失敗しました。",
                l_response.errorInfo, l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;    
        }
        
        //管理者ダイレクト指定変更サービス.execute()メソッドをコールする。
        try
        {
            l_response = (WEB3AdminPvInfoDirectChangeCancelResponse)l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response = (WEB3AdminPvInfoDirectChangeCancelResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request, "管理者ダイレクト指定変更に失敗しました。", l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        
        log.exiting(STR_METHOD_NAME);

        //レスポンスオブジェクトを返却する。
        return l_response;
        
    }
}
@
