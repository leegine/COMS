head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.21.30;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3FXSingleSignOnHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : シングルサインオンハンドラ(WEB3FXSingleSignOnHandler)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/8/28 韋念瓊 (中訊) 新規作成   
*/

package webbroker3.aio.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.aio.message.WEB3FXSingleSignOnRequest;
import webbroker3.aio.message.WEB3FXSingleSignOnResponse;
import webbroker3.aio.message.WEB3FXTradeAgreementRequest;
import webbroker3.aio.message.WEB3FXTradeAgreementResponse;
import webbroker3.aio.service.delegate.WEB3FXSingleSignOnService;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.util.WEB3LogUtility;

/**
 * (シングルサインオンハンドラ) <BR>
 * シングルサインオンハンドラクラス <BR>
 * 
 * @@author 韋念瓊(中訊)
 * @@version 1.0
 */
public class WEB3FXSingleSignOnHandler implements MessageHandler
{
    /**
    * ログ出力ユーティリティ。
    */    
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3FXSingleSignOnHandler.class);
    
    /**
     * @@roseuid 41E780B100BB
     */
    public WEB3FXSingleSignOnHandler()
    {
    }

    /**
     * (外部為替システム表示) <BR>
     * シングルサインオンサービスを取得し、execute()メソッドをコールする。 <BR>
     * <BR>
     * @@param l_request - リクエストデータ
     * @@return WEB3FXTransToFXInputResponse
     * @@roseuid 41BCF37E0279
     */
    public WEB3FXSingleSignOnResponse displayExterFxSystem(
        WEB3FXSingleSignOnRequest l_request)
    {
        String l_strMethodName = "displayExterFxSystem(WEB3FXSingleSignOnRequest l_request)";
        log.entering(l_strMethodName);
        
        WEB3FXSingleSignOnService l_service;
        WEB3FXSingleSignOnResponse l_response;
        
        try
        {
            //FXへの振替入力サービスを取得し
            l_service = 
                (WEB3FXSingleSignOnService)Services.getService(
                    WEB3FXSingleSignOnService.class);
        }
        //サービスで例外が発生した場合は、エラー情報をレスポンスメッセージに設定する
        catch (Exception l_ex)
        {
            l_response = 
                (WEB3FXSingleSignOnResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request, 
                "__シングルサインオンサービスの取得に失敗しました__",
                l_response.errorInfo,
                l_ex);
            return l_response;
        }
        
        try
        {
            //execute()メソッドをコールする
            l_response = (WEB3FXSingleSignOnResponse)l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response =
                (WEB3FXSingleSignOnResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request,
                "__error in call シングルサインオンサービス.execute__",
                l_ex);
            return l_response;
        }
        
        log.exiting(l_strMethodName);
        
        return l_response;
    }
    
    /**
     * (取引同意画面表示) <BR>
     * シングルサインオンサービスを取得し、execute()メソッドをコールする。 <BR>
     * <BR>
     * @@param l_request - リクエストデータ
     * @@return WEB3FXTradeAgreementResponse
     * @@roseuid 41BCF37E0279
     */
    public WEB3FXTradeAgreementResponse displayTradeAgreementScreen(
        WEB3FXTradeAgreementRequest l_request)
    {
        String l_strMethodName = "displayTradeAgreementScreen(" +
                "WEB3FXTradeAgreementRequest l_request)";
        log.entering(l_strMethodName);
        
        WEB3FXSingleSignOnService l_service;
        WEB3FXTradeAgreementResponse l_response;
        
        try
        {
            //シングルサインオンサービスを取得し
            l_service = 
                (WEB3FXSingleSignOnService)Services.getService(
                    WEB3FXSingleSignOnService.class);
        }
        //サービスで例外が発生した場合は、エラー情報をレスポンスメッセージに設定する
        catch (Exception l_ex)
        {
            l_response = 
                (WEB3FXTradeAgreementResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request, 
                "__シングルサインオンサービスの取得に失敗しました__",
                l_response.errorInfo,
                l_ex);
            return l_response;
        }
        
        try
        {
            //execute()メソッドをコールする
            l_response = (WEB3FXTradeAgreementResponse)l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response =
                (WEB3FXTradeAgreementResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request,
                "__error in call シングルサインオンサービス.execute__",
                l_ex);
            return l_response;
        }
        
        log.exiting(l_strMethodName);
        
        return l_response;
    }
}@
