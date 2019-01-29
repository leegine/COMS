head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.10.02.13.08;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8644d782f8a12fc;
filename	WEB3AccInfoMailAddressChangeHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : お客様情報メールアドレス変更ハンドラ(WEB3AccInfoMailAddressChangeHandler.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/11/09 劉江涛 (中訊) 新規作成
                 : 2006/05/19 周捷 (中訊) 仕様変更・モデル104
*/
package webbroker3.accountinfo.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.accountinfo.message.WEB3AccInfoMailAddressChangeCompleteRequest;
import webbroker3.accountinfo.message.WEB3AccInfoMailAddressChangeCompleteResponse;
import webbroker3.accountinfo.message.WEB3AccInfoMailAddressChangeConfirmRequest;
import webbroker3.accountinfo.message.WEB3AccInfoMailAddressChangeConfirmResponse;
import webbroker3.accountinfo.service.delegate.WEB3AccInfoMailAddressChangeService;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.util.WEB3LogUtility;

/**
 * (お客様情報メールアドレス変更ハンドラ)<BR>
 * お客様情報メールアドレス変更ハンドラ<BR>
 * @@author 劉江涛
 * @@version 1.0
 */
public class WEB3AccInfoMailAddressChangeHandler implements MessageHandler 
{                   
    /**
     * ログ出力ユーティリティ。<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AccInfoMailAddressChangeHandler.class);
    /**
     * @@roseuid 418F3A0B003E
     */
    public WEB3AccInfoMailAddressChangeHandler() 
    {
     
    }
    
    /**
     * (メールアドレス変更確認)<BR>
     * メールアドレスを変更する。<BR>
     * <BR>
     * お客様情報メールアドレス変更サービスを取得し、execute()メソッドをコールする。 <BR>
     * @@param l_request - お客様情報メールアドレス変更確認リクエストデータオブジェクト
     * 
     * @@return webbroker3.accountinfo.message.WEB3AccInfoMailAddressChangeConfirmResponse
     * @@roseuid 413D3D510326
     */
    public WEB3AccInfoMailAddressChangeConfirmResponse mailAddressChangeConfirm(WEB3AccInfoMailAddressChangeConfirmRequest l_request) 
    {
        final String STR_METHOD_NAME = " mailAddressChangeConfirm(WEB3AccInfoMailAddressChangeConfirmRequest)";
        log.entering(STR_METHOD_NAME);
        
        WEB3AccInfoMailAddressChangeConfirmResponse l_response = null;
        WEB3AccInfoMailAddressChangeService l_service = null;
        
        //お客様情報メールアドレス変更サービスを取得
        try
        {
            l_service = (WEB3AccInfoMailAddressChangeService)Services.getService(WEB3AccInfoMailAddressChangeService.class);
        }
        //サービスで例外が発生した場合は、エラー情報をレスポンスメッセージに設定する
        catch (Exception l_ex)
        {
            l_response = (WEB3AccInfoMailAddressChangeConfirmResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            l_response.errorMessage = l_ex.getMessage();
            log.error(
                l_request,
                "お客様情報メールアドレス変更サービスの取得に失敗しました。",
                l_response.errorInfo,l_ex);
            
            return l_response;      
        }
        
        //お客様情報メールアドレス変更サービス.execute()メソッドをコールする
        try
        {
            l_response =
                (WEB3AccInfoMailAddressChangeConfirmResponse)l_service.execute(
                    l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response = (WEB3AccInfoMailAddressChangeConfirmResponse)l_request.createResponse();
//			<--修正漏れの為変更***2006.06.14 SCS Inomata
//			l_response.errorMessage = l_ex.getMessage() ;
			l_response.errorMessage = l_ex.getErrorMessage() ;
//			<--修正漏れのの為変更***2006.06.14 SCS Inomata
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request,
                "お客様情報メールアドレス変更サービスに失敗しました。",
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
     * お客様情報メールアドレス変更サービスを取得し、execute()メソッドをコールする。 <BR>
     * @@param l_request - お客様情報メールアドレス変更完了リクエストデータオブジェクト
     * 
     * @@return webbroker3.accountinfo.message.WEB3AccInfoMailAddressChangeCompleteResponse
     * @@roseuid 413D3D510335
     */
    public WEB3AccInfoMailAddressChangeCompleteResponse mailAddressChangeComplete(WEB3AccInfoMailAddressChangeCompleteRequest l_request) 
    {
        final String STR_METHOD_NAME = " mailAddressChangeComplete(WEB3AccInfoMailAddressChangeCompleteRequest)";
        log.entering(STR_METHOD_NAME);
        
        WEB3AccInfoMailAddressChangeCompleteResponse l_response = null;
        WEB3AccInfoMailAddressChangeService l_service = null;
        
        //お客様情報メールアドレス変更サービスを取得
        try
        {
            l_service = (WEB3AccInfoMailAddressChangeService)Services.getService(WEB3AccInfoMailAddressChangeService.class);
        }
        //サービスで例外が発生した場合は、エラー情報をレスポンスメッセージに設定する
        catch (Exception l_ex)
        {
            l_response = (WEB3AccInfoMailAddressChangeCompleteResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            l_response.errorMessage = l_ex.getMessage();
            log.error(
                l_request,
                "お客様情報メールアドレス変更サービスの取得に失敗しました。",
                l_response.errorInfo,l_ex);
            return l_response;      
        }
        
        //お客様情報メールアドレス変更サービス.execute()メソッドをコールする
        try
        {
            l_response =
                (WEB3AccInfoMailAddressChangeCompleteResponse)l_service.execute(
                    l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response = (WEB3AccInfoMailAddressChangeCompleteResponse)l_request.createResponse();
//			<--修正漏れの為変更***2006.06.14 SCS Inomata
//			l_response.errorMessage = l_ex.getMessage() ;
			l_response.errorMessage = l_ex.getErrorMessage() ;
//			<--修正漏れのの為変更***2006.06.14 SCS Inomata
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request,
                "お客様情報メールアドレス変更サービスに失敗しました。",
                l_ex);
            return l_response; 

        }
        
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
}
@
