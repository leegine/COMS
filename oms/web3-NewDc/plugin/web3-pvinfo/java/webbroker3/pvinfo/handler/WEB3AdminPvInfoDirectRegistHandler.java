head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.22.08.09.23;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7444d8857e2568b;
filename	WEB3AdminPvInfoDirectRegistHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者ダイレクト指定登録ハンドラ(WEB3AdminPvInfoDirectRegistHandler.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/18 王亞洲(中訊) 新規作成
Revesion History : 2004/10/27 李丁銀(中訊) 作成
Revesion History : 2007/06/28 徐宏偉(中訊) 実装の問題003
*/
package webbroker3.pvinfo.handler;

import com.fitechlabs.xtrade.kernel.handler.MessageHandler;
import com.fitechlabs.xtrade.kernel.boot.Services;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.pvinfo.message.WEB3AdminPvInfoDirectRegistCancelRequest;
import webbroker3.pvinfo.message.WEB3AdminPvInfoDirectRegistCancelResponse;
import webbroker3.pvinfo.message.WEB3AdminPvInfoDirectRegistCompleteRequest;
import webbroker3.pvinfo.message.WEB3AdminPvInfoDirectRegistCompleteResponse;
import webbroker3.pvinfo.message.WEB3AdminPvInfoDirectRegistConfirmRequest;
import webbroker3.pvinfo.message.WEB3AdminPvInfoDirectRegistConfirmResponse;
import webbroker3.pvinfo.message.WEB3AdminPvInfoDirectRegistInputRequest;
import webbroker3.pvinfo.message.WEB3AdminPvInfoDirectRegistInputResponse;
import webbroker3.pvinfo.service.delegate.WEB3AdminPvInfoDirectRegistService;
import webbroker3.util.WEB3LogUtility;

/**
 * (管理者ダイレクト指定登録ハンドラ)<BR>
 * 管理者ダイレクト指定登録ハンドラクラス<BR>
 * @@author 王亞洲
 * @@version 1.00
 */
public class WEB3AdminPvInfoDirectRegistHandler implements MessageHandler
{
    /**
     * ログ出力ユーティリティ。<BR>
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(WEB3AdminPvInfoDirectRegistHandler.class);
  
    /**
     * (getダイレクト指定登録入力画面)<BR>
     * ダイレクト指定登録入力画面表示処理を行う。<BR>
     * <BR>
     * 管理者ダイレクト指定登録サービスImplを取得し、<BR>
     * execute()メソッドをコールする。<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * 管理者・ダイレクト指定登録入力リクエストオブジェクト<BR>
     * @@return webbroker3.pvinfo.message.WEB3AdminPvInfoDirectRegistInputResponse
     * @@roseuid 415D3F810002
     */
    public WEB3AdminPvInfoDirectRegistInputResponse getDirectRegistInputScreen(WEB3AdminPvInfoDirectRegistInputRequest l_request)
    {
        final String STR_METHOD_NAME = " getDirectRegistInputScreen(WEB3AdminPvInfoDirectRegistInputRequest)";
        log.entering(STR_METHOD_NAME);
        
        WEB3AdminPvInfoDirectRegistInputResponse l_response = null;
        WEB3AdminPvInfoDirectRegistService l_service = null;
        
        //管理者ダイレクト指定登録サービスを取得し
        try
        {        
            l_service = (WEB3AdminPvInfoDirectRegistService)Services.getService(WEB3AdminPvInfoDirectRegistService.class);
        }
        catch(Exception l_ex)
        {
            l_response = (WEB3AdminPvInfoDirectRegistInputResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(l_request, "管理者ダイレクト指定登録サービスを取得に失敗しました。", l_response.errorInfo, l_ex);
            
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        
        //execute()メソッドをコールする。
        try
        {
            l_response = (WEB3AdminPvInfoDirectRegistInputResponse)l_service.execute(l_request);           
        }
        catch(WEB3BaseException l_ex)
        {
            l_response = (WEB3AdminPvInfoDirectRegistInputResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request, " execute()メソッドをコールすることが失敗しました。", l_ex);
            
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (confirmダイレクト指定登録)<BR>
     * ダイレクト指定登録確認処理を行う。<BR>
     * <BR>
     * 管理者ダイレクト指定登録サービスImplを取得し、<BR>
     * execute()メソッドをコールする。<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * 管理者・ダイレクト指定登録確認リクエストオブジェクト<BR>
     * @@return webbroker3.pvinfo.message.WEB3AdminPvInfoDirectRegistConfirmResponse
     * @@roseuid 415D3FE9014A
     */
    public WEB3AdminPvInfoDirectRegistConfirmResponse confirmDirectRegist(WEB3AdminPvInfoDirectRegistConfirmRequest l_request)
    {
        final String STR_METHOD_NAME = " confirmDirectRegist(WEB3AdminPvInfoDirectRegistConfirmRequest)";
        log.entering(STR_METHOD_NAME);
        
        WEB3AdminPvInfoDirectRegistConfirmResponse l_response = null;
        WEB3AdminPvInfoDirectRegistService l_service = null;
        
        //管理者ダイレクト指定登録サービスを取得し
        try
        {
            l_service = (WEB3AdminPvInfoDirectRegistService)Services.getService(WEB3AdminPvInfoDirectRegistService.class);
        }
        catch(Exception l_ex)
        {
            l_response = (WEB3AdminPvInfoDirectRegistConfirmResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(l_request, "管理者ダイレクト指定登録サービスを取得に失敗しました。", l_response.errorInfo, l_ex);
            
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        
        //execute()メソッドをコールする
        try
        {
            l_response = (WEB3AdminPvInfoDirectRegistConfirmResponse)l_service.execute(l_request);
        }
        catch(WEB3BaseException l_ex)
        {
            l_response = (WEB3AdminPvInfoDirectRegistConfirmResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            l_response.errorMessage = l_ex.getErrorMessage();
            log.error(l_request, " execute()メソッドをコールすることが失敗しました。", l_ex);
            
            log.exiting(STR_METHOD_NAME);
            return  l_response;
        }
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (completeダイレクト指定登録)<BR>
     * ダイレクト指定登録完了処理を行う。<BR>
     * <BR>
     * 管理者ダイレクト指定登録サービスImplを取得し、<BR>
     * execute()メソッドをコールする。<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * 管理者・ダイレクト指定登録完了リクエストオブジェクト<BR>
     * @@return webbroker3.pvinfo.message.WEB3AdminPvInfoDirectRegistCompleteResponse
     * @@roseuid 415D4046037C
     */
    public WEB3AdminPvInfoDirectRegistCompleteResponse completeDirectRegist(WEB3AdminPvInfoDirectRegistCompleteRequest l_request)
    {
        final String STR_METHOD_NAME = " completeDirectRegist(WEB3AdminPvInfoDirectRegistCompleteRequest)";
        log.entering(STR_METHOD_NAME);
        
        WEB3AdminPvInfoDirectRegistCompleteResponse l_response = null;
        WEB3AdminPvInfoDirectRegistService l_service = null;
        
        //管理者ダイレクト指定登録サービスを取得し
        try
        {
            l_service = (WEB3AdminPvInfoDirectRegistService)Services.getService(WEB3AdminPvInfoDirectRegistService.class);            
        }
        catch(Exception l_ex)
        {
            l_response = (WEB3AdminPvInfoDirectRegistCompleteResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(l_request, "管理者ダイレクト指定登録サービスを取得に失敗しました。", l_response.errorInfo, l_ex);
            
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        //execute()メソッドをコールする。
        try
        {
            l_response = (WEB3AdminPvInfoDirectRegistCompleteResponse)l_service.execute(l_request);
        }
        catch(WEB3BaseException l_ex)
        {
            l_response = (WEB3AdminPvInfoDirectRegistCompleteResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            l_response.errorMessage = l_ex.getErrorMessage();
            log.error(l_request, " execute()メソッドをコールすることが失敗しました。", l_ex);
            
            log.exiting(STR_METHOD_NAME);
            return  l_response;
        }
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (undoダイレクト指定登録アップロード)<BR>
     * ダイレクト指定登録アップロード中止処理を行う。<BR>
     * <BR>
     * 管理者ダイレクト指定登録サービスImplを取得し、<BR>
     * execute()メソッドをコールする。<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * 管理者・ダイレクト指定登録アップロード中止リクエストオブジェクト<BR>
     * @@return webbroker3.pvinfo.message.WEB3AdminPvInfoDirectRegistCancelResponse
     * @@roseuid 415D416600E9
     */
    public WEB3AdminPvInfoDirectRegistCancelResponse undoDirectRegistUpload(WEB3AdminPvInfoDirectRegistCancelRequest l_request)
    {
        final String STR_METHOD_NAME = " undoDirectRegistUpload(WEB3AdminPvInfoDirectRegistCancelRequest)";
        log.entering(STR_METHOD_NAME);
        
        WEB3AdminPvInfoDirectRegistCancelResponse l_response = null;
        WEB3AdminPvInfoDirectRegistService l_service = null;
        //管理者ダイレクト指定登録サービスを取得し
        try
        {
            l_service = (WEB3AdminPvInfoDirectRegistService)Services.getService(WEB3AdminPvInfoDirectRegistService.class);            
        }
        catch(Exception l_ex)
        {
            l_response = (WEB3AdminPvInfoDirectRegistCancelResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(l_request, "管理者ダイレクト指定登録サービスを取得に失敗しました。", l_response.errorInfo, l_ex);
            
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        
        //execute()メソッドをコールする。
        try
        {
            l_response = (WEB3AdminPvInfoDirectRegistCancelResponse)l_service.execute(l_request);                      
        }
        catch(WEB3BaseException l_ex)
        {
            l_response = (WEB3AdminPvInfoDirectRegistCancelResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request, " execute()メソッドをコールすることが失敗しました。", l_ex);
            
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
}
@
