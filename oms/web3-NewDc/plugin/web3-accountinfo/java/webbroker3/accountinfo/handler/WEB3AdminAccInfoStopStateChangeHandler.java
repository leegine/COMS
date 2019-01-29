head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.10.02.14.24;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8644d782f8a12fc;
filename	WEB3AdminAccInfoStopStateChangeHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者お客様情報停止状況変更ハンドラ(WEB3AdminAccInfoStopStateChangeHandler.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/11/11 李海波 (中訊) 新規作成
*/

package webbroker3.accountinfo.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.accountinfo.message.WEB3AdminAccInfoStopStateChangeCompleteRequest;
import webbroker3.accountinfo.message.WEB3AdminAccInfoStopStateChangeCompleteResponse;
import webbroker3.accountinfo.message.WEB3AdminAccInfoStopStateChangeConfirmRequest;
import webbroker3.accountinfo.message.WEB3AdminAccInfoStopStateChangeConfirmResponse;
import webbroker3.accountinfo.message.WEB3AdminAccInfoStopStateChangeInputRequest;
import webbroker3.accountinfo.message.WEB3AdminAccInfoStopStateChangeInputResponse;
import webbroker3.accountinfo.service.delegate.WEB3AdminAccInfoStopStateChangeService;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.util.WEB3LogUtility;

/**
 * (管理者お客様情報停止状況変更ハンドラ)<BR>
 * 管理者お客様情報停止状況変更ハンドラ<BR>
 * 
 * @@author 李海波
 * @@version 1.0
 */
public class WEB3AdminAccInfoStopStateChangeHandler implements MessageHandler 
{
    /**
     * ログ出力ユーティリティ。
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminAccInfoStopStateChangeHandler.class);
        
    /**
     * @@roseuid 418F3A1401A5
     */
    public WEB3AdminAccInfoStopStateChangeHandler() 
    {
     
    }
    
    /**
     * (入力画面表示)<BR>
     * 停止状況変更入力画面表示処理を行う。 <BR>
     * <BR>
     * 管理者お客様情報停止状況変更サービスを取得し、<BR>
     * execute()メソッドをコールする。 <BR>
     * @@param l_request - 管理者お客様情報停止状況変更入力リクエストデータオブジェクト
     * 
     * @@return webbroker3.accountinfo.message.WEB3AdminAccInfoStopStateChangeInputResponse
     * @@throws WEB3BaseException
     * @@roseuid 416B32FB00FD
     */
    public WEB3AdminAccInfoStopStateChangeInputResponse inputScreenDisplay(WEB3AdminAccInfoStopStateChangeInputRequest l_request) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " inputScreenDisplay(WEB3AdminAccInfoStopStateChangeInputRequest)";
        log.entering(STR_METHOD_NAME);
        
        WEB3AdminAccInfoStopStateChangeInputResponse l_response = null;
        WEB3AdminAccInfoStopStateChangeService l_service = null;
        
        try
        {
            l_service = 
                (WEB3AdminAccInfoStopStateChangeService)Services.getService(
                    WEB3AdminAccInfoStopStateChangeService.class);
        }
        //サービスで例外が発生した場合は、エラー情報をレスポンスメッセージに設定する
        catch (Exception l_ex)
        {
            l_response = (WEB3AdminAccInfoStopStateChangeInputResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "管理者お客様情報停止状況変更サービスの取得に失敗しました。",
                l_response.errorInfo,l_ex);
            return l_response;      
        }
        
        //管理者お客様情報停止状況変更サービス.execute()メソッドをコールする
        try
        {
            l_response = (WEB3AdminAccInfoStopStateChangeInputResponse)l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response = (WEB3AdminAccInfoStopStateChangeInputResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request, "管理者お客様情報停止状況変更サービスに失敗しました。", l_ex);
            return l_response;
        }
        
        log.exiting(STR_METHOD_NAME);
        //レスポンスオブジェクトを返却する。
        return l_response;
    }
    
    /**
     * (変更確認)<BR>
     * 停止状況変更確認処理を行う。 <BR>
     * <BR>
     * 管理者お客様情報停止状況変更サービスを取得し、<BR>
     * execute()メソッドをコールする。 <BR>
     * @@param l_request - 管理者お客様情報停止状況変更確認リクエストデータオブジェクト
     * @@return webbroker3.accountinfo.message.WEB3AdminAccInfoStopStateChangeConfirmResponse
     * @@roseuid 41634D26016F
     */
    public WEB3AdminAccInfoStopStateChangeConfirmResponse changeConfirm(WEB3AdminAccInfoStopStateChangeConfirmRequest l_request) 
    {
        final String STR_METHOD_NAME = " changeConfirm(WEB3AdminAccInfoStopStateChangeConfirmRequest)";
        log.entering(STR_METHOD_NAME);
        
        WEB3AdminAccInfoStopStateChangeConfirmResponse l_response = null;
        WEB3AdminAccInfoStopStateChangeService l_service = null;
        
        try
        {
            l_service = 
                (WEB3AdminAccInfoStopStateChangeService)Services.getService(
                    WEB3AdminAccInfoStopStateChangeService.class);
        }
        //サービスで例外が発生した場合は、エラー情報をレスポンスメッセージに設定する
        catch (Exception l_ex)
        {
            l_response = (WEB3AdminAccInfoStopStateChangeConfirmResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "管理者お客様情報停止状況変更サービスの取得に失敗しました。",
                l_response.errorInfo,l_ex);
            return l_response;      
        }
        
        //管理者お客様情報停止状況変更サービス.execute()メソッドをコールする
        try
        {
            l_response = (WEB3AdminAccInfoStopStateChangeConfirmResponse)l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response = (WEB3AdminAccInfoStopStateChangeConfirmResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request, "管理者お客様情報停止状況変更サービスに失敗しました。", l_ex);
            return l_response;
        }
        
        log.exiting(STR_METHOD_NAME);
        //レスポンスオブジェクトを返却する。
        return l_response;
    }
    
    /**
     * (変更完了)<BR>
     * 停止状況変更完了処理を行う。 <BR>
     * <BR>
     * 管理者お客様情報停止状況変更サービスを取得し、<BR>
     * execute()メソッドをコールする。 <BR>
     * @@param l_request - 管理者お客様情報停止状況変更完了リクエストデータオブジェクト
     * @@return webbroker3.accountinfo.message.WEB3AdminAccInfoStopStateChangeCompleteResponse
     * @@roseuid 41634D26018E
     */
    public WEB3AdminAccInfoStopStateChangeCompleteResponse changeComplete(WEB3AdminAccInfoStopStateChangeCompleteRequest l_request) 
    {
        final String STR_METHOD_NAME = " changeComplete(WEB3AdminAccInfoStopStateChangeCompleteRequest)";
        log.entering(STR_METHOD_NAME);
        
        WEB3AdminAccInfoStopStateChangeCompleteResponse l_response = null;
        WEB3AdminAccInfoStopStateChangeService l_service = null;
        
        try
        {
            l_service = 
                (WEB3AdminAccInfoStopStateChangeService)Services.getService(
                WEB3AdminAccInfoStopStateChangeService.class);
        }
        //サービスで例外が発生した場合は、エラー情報をレスポンスメッセージに設定する
        catch (Exception l_ex)
        {
            l_response = (WEB3AdminAccInfoStopStateChangeCompleteResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "管理者お客様情報停止状況変更サービスの取得に失敗しました。",
                l_response.errorInfo,l_ex);
            return l_response;      
        }
        
        //管理者お客様情報停止状況変更サービス.execute()メソッドをコールする
        try
        {
            l_response = (WEB3AdminAccInfoStopStateChangeCompleteResponse)l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response = (WEB3AdminAccInfoStopStateChangeCompleteResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request, "管理者お客様情報停止状況変更サービスに失敗しました。", l_ex);
            return l_response;
        }
        
        log.exiting(STR_METHOD_NAME);
        //レスポンスオブジェクトを返却する。
        return l_response;
    }
}
@
