head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.10.02.13.34;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8644d782f8a12fc;
filename	WEB3AdminAccInfoMailAddressChangeHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者お客様情報メールアドレス変更ハンドラ(WEB3AdminAccInfoMailAddressChangeHandler.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/11/09 劉江涛 (中訊) 新規作成
                 : 2006/05/19 周捷 (中訊) 仕様変更・モデル104
*/
package webbroker3.accountinfo.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.accountinfo.message.WEB3AdminAccInfoMailAddressChangeCompleteRequest;
import webbroker3.accountinfo.message.WEB3AdminAccInfoMailAddressChangeCompleteResponse;
import webbroker3.accountinfo.message.WEB3AdminAccInfoMailAddressChangeConfirmRequest;
import webbroker3.accountinfo.message.WEB3AdminAccInfoMailAddressChangeConfirmResponse;
import webbroker3.accountinfo.message.WEB3AdminAccInfoMailAddressChangeInputRequest;
import webbroker3.accountinfo.message.WEB3AdminAccInfoMailAddressChangeInputResponse;
import webbroker3.accountinfo.service.delegate.WEB3AdminAccInfoMailAddressChangeService;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.util.WEB3LogUtility;

/**
 * (管理者お客様情報メールアドレス変更ハンドラ)<BR>
 * 管理者お客様情報メールアドレス変更ハンドラ<BR>
 * @@author 劉江涛
 * @@version 1.0
 */
public class WEB3AdminAccInfoMailAddressChangeHandler implements MessageHandler 
{
    /**
     * ログ出力ユーティリティ。<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminAccInfoMailAddressChangeHandler.class);
    /**
     * @@roseuid 418F3A0E0203
     */
    public WEB3AdminAccInfoMailAddressChangeHandler() 
    {
     
    }
    
    /**
     * (入力画面表示)<BR>
     * メールアドレス変更入力画面表示処理を行う。<BR>
     * <BR>
     * 管理者お客様情報メールアドレス変更サービスを取得し、<BR>
     * execute()メソッドをコールする。 <BR>
     * @@param l_request - 管理者お客様情報メールアドレス変更入力リクエストデータオブジェクト
     * 
     * @@return webbroker3.accountinfo.message.WEB3AdminAccInfoMailAddressChangeInputResponse
     * @@roseuid 4166629402AB
     */
    public WEB3AdminAccInfoMailAddressChangeInputResponse inputScreenDisplay(WEB3AdminAccInfoMailAddressChangeInputRequest l_request) 
    {
        final String STR_METHOD_NAME = " inputScreenDisplay(WEB3AdminAccInfoMailAddressChangeInputRequest)";
        log.entering(STR_METHOD_NAME);
        
        WEB3AdminAccInfoMailAddressChangeInputResponse l_response = null;
        WEB3AdminAccInfoMailAddressChangeService l_service = null;
        
        //管理者お客様情報メールアドレス変更サービスを取得
        try
        {
            l_service = (WEB3AdminAccInfoMailAddressChangeService)Services.getService(WEB3AdminAccInfoMailAddressChangeService.class);
        }
        //サービスで例外が発生した場合は、エラー情報をレスポンスメッセージに設定する
        catch (Exception l_ex)
        {
            l_response = (WEB3AdminAccInfoMailAddressChangeInputResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "管理者お客様情報メールアドレス変更サービスの取得に失敗しました。",
                l_response.errorInfo,l_ex);
            return l_response;      
        }
        
        //管理者お客様情報メールアドレス変更サービス.execute()メソッドをコールする
        try
        {
            l_response =
                (WEB3AdminAccInfoMailAddressChangeInputResponse)l_service.execute(
                    l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response = (WEB3AdminAccInfoMailAddressChangeInputResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request,
                "管理者お客様情報メールアドレス変更サービスに失敗しました。",
                l_ex);
            return l_response; 
        }
        
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * (メールアドレス変更確認)<BR>
     * メールアドレスを変更する。<BR>
     * <BR>
     * 管理者お客様情報メールアドレス変更サービスを取得し、<BR>
     * execute()メソッドをコールする。 <BR>
     * @@param l_request - 管理者お客様情報メールアドレス変更確認リクエストデータオブジェクト
     * 
     * @@return webbroker3.accountinfo.message.WEB3AdminAccInfoMailAddressChangeConfirmResponse
     * @@roseuid 4147FAD8020E
     */
    public WEB3AdminAccInfoMailAddressChangeConfirmResponse mailAddressChangeConfirm(WEB3AdminAccInfoMailAddressChangeConfirmRequest l_request) 
    {
        final String STR_METHOD_NAME = " mailAddressChangeConfirm(WEB3AdminAccInfoMailAddressChangeConfirmRequest)";
        log.entering(STR_METHOD_NAME);
        
        WEB3AdminAccInfoMailAddressChangeConfirmResponse l_response = null;
        WEB3AdminAccInfoMailAddressChangeService l_service = null;
        
        //管理者お客様情報メールアドレス変更サービスを取得
        try
        {
            l_service = (WEB3AdminAccInfoMailAddressChangeService)Services.getService(WEB3AdminAccInfoMailAddressChangeService.class);
        }
        //サービスで例外が発生した場合は、エラー情報をレスポンスメッセージに設定する
        catch (Exception l_ex)
        {
            l_response = (WEB3AdminAccInfoMailAddressChangeConfirmResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            l_response.errorMessage = l_ex.getMessage();
            log.error(
                l_request,
                "管理者お客様情報メールアドレス変更サービスの取得に失敗しました。",
                l_response.errorInfo,l_ex);
            return l_response;      
        }
        
        //管理者お客様情報メールアドレス変更サービス.execute()メソッドをコールする
        try
        {
            l_response =
                (WEB3AdminAccInfoMailAddressChangeConfirmResponse)l_service.execute(
                    l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response = (WEB3AdminAccInfoMailAddressChangeConfirmResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
//			<--修正漏れの為変更***2006.06.14 SCS Inomata
//			l_response.errorMessage = l_ex.getMessage() ;
            l_response.errorMessage = l_ex.getErrorMessage() ;
//			<--修正漏れのの為変更***2006.06.14 SCS Inomata
            log.error(
                l_request,
                "管理者お客様情報メールアドレス変更サービスに失敗しました。",
                l_ex);
            return l_response; 
        }
        
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * (メールアドレス変更完了)<BR>
     * メールアドレスを変更する。<BR>
     * <BR>
     * 管理者お客様情報メールアドレス変更サービスを取得し、<BR>
     * execute()メソッドをコールする。 <BR>
     * @@param l_request - 管理者お客様情報メールアドレス変更完了リクエストデータオブジェクト
     * @@return webbroker3.accountinfo.message.WEB3AdminAccInfoMailAddressChangeCompleteResponse
     * @@roseuid 4147FAD8021E
     */
    public WEB3AdminAccInfoMailAddressChangeCompleteResponse mailAddressChangeComplete(WEB3AdminAccInfoMailAddressChangeCompleteRequest l_request) 
    {
        final String STR_METHOD_NAME = " mailAddressChangeComplete(WEB3AdminAccInfoMailAddressChangeCompleteRequest)";
        log.entering(STR_METHOD_NAME);
        
        WEB3AdminAccInfoMailAddressChangeCompleteResponse l_response = null;
        WEB3AdminAccInfoMailAddressChangeService l_service = null;
        
        //管理者お客様情報メールアドレス変更サービスを取得
        try
        {
            l_service = (WEB3AdminAccInfoMailAddressChangeService)Services.getService(WEB3AdminAccInfoMailAddressChangeService.class);
        }
        //サービスで例外が発生した場合は、エラー情報をレスポンスメッセージに設定する
        catch (Exception l_ex)
        {
            l_response = (WEB3AdminAccInfoMailAddressChangeCompleteResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            l_response.errorMessage = l_ex.getMessage();
            log.error(
                l_request,
                "管理者お客様情報メールアドレス変更サービスの取得に失敗しました。",
                l_response.errorInfo,l_ex);
            return l_response;      
        }
        
        //管理者お客様情報メールアドレス変更サービス.execute()メソッドをコールする
        try
        {
            l_response =
                (WEB3AdminAccInfoMailAddressChangeCompleteResponse)l_service.execute(
                    l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response = (WEB3AdminAccInfoMailAddressChangeCompleteResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
//			<--修正漏れの為変更***2006.06.14 SCS Inomata
//			l_response.errorMessage = l_ex.getMessage() ;
			l_response.errorMessage = l_ex.getErrorMessage() ;
//			<--修正漏れのの為変更***2006.06.14 SCS Inomata
            log.error(
                l_request,
                "管理者お客様情報メールアドレス変更サービスに失敗しました。",
                l_ex);
            return l_response; 
        }
        
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
}
@
