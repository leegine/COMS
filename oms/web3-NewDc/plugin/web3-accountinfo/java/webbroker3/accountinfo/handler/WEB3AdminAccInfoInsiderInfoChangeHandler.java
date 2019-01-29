head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.10.02.12.39;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8644d782f8a12fc;
filename	WEB3AdminAccInfoInsiderInfoChangeHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者お客様情報内部者情報変更ハンドラ(WEB3AdminAccInfoInsiderInfoChangeHandler.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/11/11 李海波 (中訊) 新規作成
*/

package webbroker3.accountinfo.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.accountinfo.message.WEB3AdminAccInfoInsiderInfoChangeCompleteRequest;
import webbroker3.accountinfo.message.WEB3AdminAccInfoInsiderInfoChangeCompleteResponse;
import webbroker3.accountinfo.message.WEB3AdminAccInfoInsiderInfoChangeConfirmRequest;
import webbroker3.accountinfo.message.WEB3AdminAccInfoInsiderInfoChangeConfirmResponse;
import webbroker3.accountinfo.message.WEB3AdminAccInfoInsiderInfoChangeInputRequest;
import webbroker3.accountinfo.message.WEB3AdminAccInfoInsiderInfoChangeInputResponse;
import webbroker3.accountinfo.service.delegate.WEB3AdminAccInfoInsiderInfoChangeService;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.util.WEB3LogUtility;

/**
 * (管理者お客様情報内部者情報変更ハンドラ)<BR>
 * 管理者お客様情報内部者情報変更ハンドラ<BR>
 * 
 * @@author 李海波
 * @@version 1.0
 */
public class WEB3AdminAccInfoInsiderInfoChangeHandler implements MessageHandler 
{
    /**
     * ログ出力ユーティリティ。
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminAccInfoInsiderInfoChangeHandler.class);
        
    /**
     * @@roseuid 418F3A140000
     */
    public WEB3AdminAccInfoInsiderInfoChangeHandler() 
    {
     
    }
    
    /**
     * (入力画面表示)<BR>
     * 内部者情報変更入力画面表示処理を行う。 <BR>
     * <BR>
     * 管理者お客様情報内部者情報変更サービスを取得し、<BR>
     * execute()メソッドをコールする。 <BR>
     * @@param l_request - 管理者お客様情報内部者情報変更入力リクエストデータオブジェクト
     * 
     * @@return webbroker3.accountinfo.message.WEB3AdminAccInfoInsiderInfoChangeInputResponse
     * @@throws WEB3BaseException
     * @@roseuid 416B64A200B7
     */
    public WEB3AdminAccInfoInsiderInfoChangeInputResponse 
        inputScreenDisplay(WEB3AdminAccInfoInsiderInfoChangeInputRequest l_request) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " accountBaseInfoInquiry(WEB3AdminAccInfoAccountBaseInfoResultRequest)";
        log.entering(STR_METHOD_NAME);
        
        WEB3AdminAccInfoInsiderInfoChangeInputResponse l_response = null;
        WEB3AdminAccInfoInsiderInfoChangeService l_service = null;
        
        try
        {
            l_service = 
                (WEB3AdminAccInfoInsiderInfoChangeService)Services.getService(
            WEB3AdminAccInfoInsiderInfoChangeService.class);
        }
        //サービスで例外が発生した場合は、エラー情報をレスポンスメッセージに設定する
        catch (Exception l_ex)
        {
            l_response = (WEB3AdminAccInfoInsiderInfoChangeInputResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "管理者お客様情報内部者情報変更サービスの取得に失敗しました。",
                l_response.errorInfo,l_ex);
            return l_response;      
        }
        
        //管理者お客様情報内部者情報変更サービス.execute()メソッドをコールする
        try
        {
            l_response = (WEB3AdminAccInfoInsiderInfoChangeInputResponse)l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response = (WEB3AdminAccInfoInsiderInfoChangeInputResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request, "管理者お客様情報内部者情報変更サービスに失敗しました。", l_ex);
            return l_response;
        }
        
        log.exiting(STR_METHOD_NAME);
        //レスポンスオブジェクトを返却する。
        return l_response;
    }
    
    /**
     * (変更確認)<BR>
     * 内部者情報変更確認処理を行う。 <BR>
     * <BR>
     * 管理者お客様情報内部者情報変更サービスを取得し、<BR>
     * execute()メソッドをコールする。 <BR>
     * @@param l_request - 管理者お客様情報内部者情報変更確認リクエストデータオブジェクト
     * @@return webbroker3.accountinfo.message.WEB3AdminAccInfoInsiderInfoChangeConfirmResponse
     * @@roseuid 415D017B03D5
     */
    public WEB3AdminAccInfoInsiderInfoChangeConfirmResponse 
        changeConfirm(WEB3AdminAccInfoInsiderInfoChangeConfirmRequest l_request) 
    {
        final String STR_METHOD_NAME = " accountBaseInfoInquiry(WEB3AdminAccInfoAccountBaseInfoResultRequest)";
        log.entering(STR_METHOD_NAME);
        
        WEB3AdminAccInfoInsiderInfoChangeConfirmResponse l_response = null;
        WEB3AdminAccInfoInsiderInfoChangeService l_service = null;
        
        try
        {
            l_service = 
                (WEB3AdminAccInfoInsiderInfoChangeService)Services.getService(
            WEB3AdminAccInfoInsiderInfoChangeService.class);
        }
        //サービスで例外が発生した場合は、エラー情報をレスポンスメッセージに設定する
        catch (Exception l_ex)
        {
            l_response = (WEB3AdminAccInfoInsiderInfoChangeConfirmResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "管理者お客様情報内部者情報変更サービスの取得に失敗しました。",
                l_response.errorInfo,l_ex);
            return l_response;      
        }
        
        //管理者お客様情報内部者情報変更サービス.execute()メソッドをコールする
        try
        {
            l_response = (WEB3AdminAccInfoInsiderInfoChangeConfirmResponse)l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response = (WEB3AdminAccInfoInsiderInfoChangeConfirmResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request, "管理者お客様情報内部者情報変更サービスに失敗しました。", l_ex);
            return l_response;
        }
        
        log.exiting(STR_METHOD_NAME);
        //レスポンスオブジェクトを返却する。
        return l_response;
    }
    
    /**
     * (変更完了)<BR>
     * 内部者情報変更完了処理を行う。 <BR>
     * <BR>
     * 管理者お客様情報内部者情報変更サービスを取得し、<BR>
     * execute()メソッドをコールする。 <BR>
     * @@param l_request - 管理者お客様情報内部者情報変更完了リクエストデータオブジェクト
     * @@return webbroker3.accountinfo.message.WEB3AdminAccInfoInsiderInfoChangeCompleteResponse
     * @@roseuid 415D017B03D7
     */
    public WEB3AdminAccInfoInsiderInfoChangeCompleteResponse 
        changeComplete(WEB3AdminAccInfoInsiderInfoChangeCompleteRequest l_request) 
    {
        final String STR_METHOD_NAME = " accountBaseInfoInquiry(WEB3AdminAccInfoAccountBaseInfoResultRequest)";
        log.entering(STR_METHOD_NAME);
        
        WEB3AdminAccInfoInsiderInfoChangeCompleteResponse l_response = null;
        WEB3AdminAccInfoInsiderInfoChangeService l_service = null;
        
        try
        {
            l_service = 
                (WEB3AdminAccInfoInsiderInfoChangeService)Services.getService(
            WEB3AdminAccInfoInsiderInfoChangeService.class);
        }
        //サービスで例外が発生した場合は、エラー情報をレスポンスメッセージに設定する
        catch (Exception l_ex)
        {
            l_response = (WEB3AdminAccInfoInsiderInfoChangeCompleteResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "管理者お客様情報内部者情報変更サービスの取得に失敗しました。",
                l_response.errorInfo,l_ex);
            return l_response;      
        }
        
        //管理者お客様情報内部者情報変更サービス.execute()メソッドをコールする
        try
        {
            l_response = (WEB3AdminAccInfoInsiderInfoChangeCompleteResponse)l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response = (WEB3AdminAccInfoInsiderInfoChangeCompleteResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request, "管理者お客様情報内部者情報変更サービスに失敗しました。", l_ex);
            return l_response;
        }
        
        log.exiting(STR_METHOD_NAME);
        //レスポンスオブジェクトを返却する。
        return l_response;
    }
}
@
