head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.22.46;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3AdminAioOtherCountReferenceHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : その他件数照会ハンドラ(WEB3AdminAioOtherCountReferenceHandler)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/7/7 韋念瓊 (中訊) 新規作成   
*/

package webbroker3.aio.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.aio.message.WEB3AdminAioOtherCountReferenceInputRequest;
import webbroker3.aio.message.WEB3AdminAioOtherCountReferenceInputResponse;
import webbroker3.aio.message.WEB3AdminAioOtherCountReferenceRequest;
import webbroker3.aio.message.WEB3AdminAioOtherCountReferenceResponse;
import webbroker3.aio.service.delegate.WEB3AdminAioOtherCountReferenceService;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.util.WEB3LogUtility;

/**
 * (その他件数照会ハンドラ)<BR>
 * その他件数照会ハンドラクラス
 * 
 * @@author 韋念瓊(中訊)
 * @@version 1.0
 */
public class WEB3AdminAioOtherCountReferenceHandler implements MessageHandler 
{
    /**
     * ログ出力ユーティリティ。
     */    
     private static WEB3LogUtility log =
         WEB3LogUtility.getInstance(WEB3AdminAioOtherCountReferenceHandler.class);
    
    /**
     * @@roseuid 423562E40242
     */
    public WEB3AdminAioOtherCountReferenceHandler() 
    {
     
    }
    
    /**
     * (get入力画面)<BR>
     * 入力画面表示を行う。<BR>
     * その他件数照会サービスを取得し、execute()メソッドをコールする。 
     * @@param l_request - リクエストデータ
     * 
     * @@return WEB3AdminAioOtherCountReferenceInputResponse
     * @@roseuid 41E386A40162
     */
    public WEB3AdminAioOtherCountReferenceInputResponse getInputScreen(
                WEB3AdminAioOtherCountReferenceInputRequest l_request) 
    {
        String l_strMethodName = 
            "getInputScreen(WEB3AdminAioOtherCountReferenceInputRequest l_request)";
        log.entering(l_strMethodName);
        
        WEB3AdminAioOtherCountReferenceService l_service;
        WEB3AdminAioOtherCountReferenceInputResponse l_response;
        
        try
        {
            //その他件数照会サービスを取得し
            l_service = 
                (WEB3AdminAioOtherCountReferenceService)Services.getService(
                    WEB3AdminAioOtherCountReferenceService.class);
        }
        //サービスで例外が発生した場合は、エラー情報をレスポンスメッセージに設定する
        catch (Exception l_ex)
        {
            l_response = 
                (WEB3AdminAioOtherCountReferenceInputResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request, 
                "__その他件数照会サービスの取得に失敗しました__",
                l_response.errorInfo,
                l_ex);
            return l_response;
        }
        
        try
        {
            //execute()メソッドをコールする
            l_response = 
                (WEB3AdminAioOtherCountReferenceInputResponse)l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response =
                (WEB3AdminAioOtherCountReferenceInputResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request,
                "__error in call その他件数照会サービス.execute__",
                l_ex);
            return l_response;
        }
        
        log.exiting(l_strMethodName);
        
        return l_response;
    }
    
    /**
     * (get件数照会画面)<BR>
     * 件数照会画面表示を行う。<BR>
     * <BR>
     * その他件数照会サービスを取得し、execute()メソッドをコールする。
     * @@param l_request - リクエストデータ
     * 
     * @@return WEB3AdminAioOtherCountReferenceResponse
     * @@roseuid 421ADF2B028A
     */
    public WEB3AdminAioOtherCountReferenceResponse getCountReferenceScreen(
                WEB3AdminAioOtherCountReferenceRequest l_request) 
    {
        String l_strMethodName = 
            "getCountReferenceScreen(WEB3AdminAioOtherCountReferenceRequest l_request)";
        log.entering(l_strMethodName);
        
        WEB3AdminAioOtherCountReferenceService l_service;
        WEB3AdminAioOtherCountReferenceResponse l_response;
        
        try
        {
            //その他件数照会サービスを取得し
            l_service = 
                (WEB3AdminAioOtherCountReferenceService)Services.getService(
                    WEB3AdminAioOtherCountReferenceService.class);
        }
        //サービスで例外が発生した場合は、エラー情報をレスポンスメッセージに設定する
        catch (Exception l_ex)
        {
            l_response = 
                (WEB3AdminAioOtherCountReferenceResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request, 
                "__その他件数照会サービスの取得に失敗しました__",
                l_response.errorInfo,
                l_ex);
            return l_response;
        }
        
        try
        {
            //execute()メソッドをコールする
            l_response = 
                (WEB3AdminAioOtherCountReferenceResponse)l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response =
                (WEB3AdminAioOtherCountReferenceResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request,
                "__error in call その他件数照会サービス.execute__",
                l_ex);
            return l_response;
        }
        
        log.exiting(l_strMethodName);
        
        return l_response;
    }
}@
