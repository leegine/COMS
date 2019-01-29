head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.05.49.02;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6f44d7efa104045;
filename	WEB3IpoBookbuildingChangeHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        :  IPOブックビルディング訂正ハンドラクラス(WEB3IpoBookbuildingChangeHandler.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/08/17 劉江涛(中訊) 新規作成
*/
package webbroker3.ipo.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.ipo.message.WEB3IPOBookBuildingChangeCompleteRequest;
import webbroker3.ipo.message.WEB3IPOBookBuildingChangeCompleteResponse;
import webbroker3.ipo.message.WEB3IPOBookBuildingChangeConfirmRequest;
import webbroker3.ipo.message.WEB3IPOBookBuildingChangeConfirmResponse;
import webbroker3.ipo.message.WEB3IPOBookBuildingChangeInputRequest;
import webbroker3.ipo.message.WEB3IPOBookBuildingChangeInputResponse;
import webbroker3.ipo.service.delegate.WEB3IpoBookbuildingChangeService;
import webbroker3.util.WEB3LogUtility;

/**
 * IPOブックビルディング訂正ハンドラクラス
 * @@author 劉江涛(中訊)
 * @@version 1.0
 */
public class WEB3IpoBookbuildingChangeHandler implements MessageHandler 
{
    /**
     * ログ出力ユーティリティ。
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3IpoBookbuildingChangeHandler.class);
    
    /**
     * @@roseuid 4112EE5803DB
     */
    public WEB3IpoBookbuildingChangeHandler() 
    {
     
    }
    
    /**
     * (入力画面表示)<BR>
     * IPOブックビルディング訂正入力画面表示処理<BR>
     * <BR>
     * IPOブックビルディング訂正サービスを取得し、execute()メソッドをコールする。
     * @@param l_request - IPOブックビルディング訂正入力リクエストデータオブジェクト
     * @@return webbroker3.ipo.message.WEB3IpoBookbuildingChangeInputResponse
     * @@roseuid 40D9695002ED
     */
    public WEB3IPOBookBuildingChangeInputResponse inputScreenIndication(WEB3IPOBookBuildingChangeInputRequest l_request) 
    {
        final String STR_METHOD_NAME =
             " inputScreenIndication(WEB3IpoBookbuildingChangeInputRequest)";
                
        log.entering(STR_METHOD_NAME);
        
        WEB3IPOBookBuildingChangeInputResponse l_response = null;
        WEB3IpoBookbuildingChangeService l_service = null;
        
        //IPOブックビルディング訂正サービスインタフェイス
        try
        {
            l_service =
                (WEB3IpoBookbuildingChangeService)Services.getService(
            WEB3IpoBookbuildingChangeService.class);
        }
        //サービスで例外が発生した場合は、エラー情報をレスポンスメッセージに設定する
        catch (Exception l_ex)
        {
            l_response =
                (WEB3IPOBookBuildingChangeInputResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "IPOブックビルディング訂正サービスの取得に失敗しました。",
                l_response.errorInfo,l_ex);
            return l_response;      
        }
        
        //IPOブックビルディング訂正サービス.execute()メソッドをコールする
        try
        {
            l_response =
                (WEB3IPOBookBuildingChangeInputResponse)l_service.execute(
                    l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response =
                (WEB3IPOBookBuildingChangeInputResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request, "IPOブックビルディング訂正に失敗しました。", l_ex);
            return l_response;
        }
        log.exiting(STR_METHOD_NAME);

        //レスポンスオブジェクトを返却する。
        return l_response;        

    }
    
    /**
     * IPOブックビルディング訂正確認処理<BR>
     * <BR>
     * IPOブックビルディング訂正サービスを取得し、execute()メソッドをコールする。
     * @@param l_request - IPOブックビルディング訂正確認リクエストデータオブジェクト
     * @@return webbroker3.ipo.message.WEB3IpoBookbuildingChangeConfirmResponse
     * @@roseuid 40D9692F0195
     */
    public WEB3IPOBookBuildingChangeConfirmResponse bookbuildingChangeConfirm(WEB3IPOBookBuildingChangeConfirmRequest l_request) 
    {
        final String STR_METHOD_NAME =
                 " bookbuildingChangeConfirm(WEB3IpoBookbuildingChangeConfirmRequest )";
                
        log.entering(STR_METHOD_NAME);
        
        WEB3IPOBookBuildingChangeConfirmResponse l_response = null;
        WEB3IpoBookbuildingChangeService l_service = null;
        
        //IPOブックビルディング訂正サービスを取得する
        try
        {
            l_service =
                (WEB3IpoBookbuildingChangeService)Services.getService(
            WEB3IpoBookbuildingChangeService.class);
        }
        //サービスで例外が発生した場合は、エラー情報をレスポンスメッセージに設定する
        catch (Exception l_ex)
        {
            l_response =
                (WEB3IPOBookBuildingChangeConfirmResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "IPOブックビルディング訂正サービスの取得に失敗しました。",
                l_response.errorInfo,l_ex);
            return l_response;      
        }
        
        //IPOブックビルディング訂正サービス.execute()メソッドをコールする
        try
        {
            l_response =
                (WEB3IPOBookBuildingChangeConfirmResponse)l_service.execute(
                    l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response =
                (WEB3IPOBookBuildingChangeConfirmResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request, "IPOブックビルディング訂正に失敗しました。", l_ex);
            return l_response;
        }
        log.exiting(STR_METHOD_NAME);

        //レスポンスオブジェクトを返却する。
        return l_response;
    }
    
    /**
     * IPOブックビルディング訂正完了処理<BR>
     * <BR>
     * IPOブックビルディング訂正サービスを取得し、execute()メソッドをコールする。
     * @@param l_request - IPOブックビルディング訂正完了リクエストデータオブジェクト
     * @@return webbroker3.ipo.message.WEB3IpoBookbuildingChangeCompleteResponse
     * @@roseuid 40D9692F01A5
     */
    public WEB3IPOBookBuildingChangeCompleteResponse bookbuildingChangeComplete(WEB3IPOBookBuildingChangeCompleteRequest l_request) 
    {
        final String STR_METHOD_NAME =
                 " bookbuildingChangeComplete(WEB3IpoBookbuildingChangeCompleteRequest)";
                
        log.entering(STR_METHOD_NAME);
        
        WEB3IPOBookBuildingChangeCompleteResponse l_response = null;
        WEB3IpoBookbuildingChangeService l_service = null;
        
        //IPOブックビルディング訂正サービスを取得する
        try
        {
            l_service =
                (WEB3IpoBookbuildingChangeService)Services.getService(
            WEB3IpoBookbuildingChangeService.class);
        }
        //サービスで例外が発生した場合は、エラー情報をレスポンスメッセージに設定する
        catch (Exception l_ex)
        {
            l_response =
                (WEB3IPOBookBuildingChangeCompleteResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "IPOブックビルディング訂正サービスの取得に失敗しました。",
                l_response.errorInfo,l_ex);
            return l_response;      
        }
        
        //IPOブックビルディング訂正サービス.execute()メソッドをコールする
        try
        {
            l_response =
                (WEB3IPOBookBuildingChangeCompleteResponse)l_service.execute(
                    l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response =
                (WEB3IPOBookBuildingChangeCompleteResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request, "IPOブックビルディング訂正サービスに失敗しました。", l_ex);
            return l_response;
        }
        log.exiting(STR_METHOD_NAME);

        //レスポンスオブジェクトを返却する。
        return l_response;
    }
}
@
