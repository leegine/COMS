head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.05.49.10;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6f44d7efa104045;
filename	WEB3AdminIpoProductDeleteHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者IPO銘柄削除ハンドラ(WEB3AdminIpoProductDeleteHandler.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/08/20 李頴淵 新規作成
*/

package webbroker3.ipo.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.ipo.message.WEB3AdminIPOProductDeleteCompleteRequest;
import webbroker3.ipo.message.WEB3AdminIPOProductDeleteCompleteResponse;
import webbroker3.ipo.message.WEB3AdminIPOProductDeleteConfirmRequest;
import webbroker3.ipo.message.WEB3AdminIPOProductDeleteConfirmResponse;
import webbroker3.ipo.service.delegate.WEB3AdminIpoProductDeleteService;
import webbroker3.util.WEB3LogUtility;

/**
 * (管理者IPO銘柄削除ハンドラ)<BR>
 * 管理者IPO銘柄削除ハンドラクラス
 * 
 * @@author 李頴淵
 * @@version 1.0
 */
public class WEB3AdminIpoProductDeleteHandler implements MessageHandler 
{
    /**
     * ログ出力ユーティリティ。<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminIpoProductDeleteHandler.class);
    
    /**
     * @@roseuid 4112EE570181
     */
    public WEB3AdminIpoProductDeleteHandler() 
    {
     
    }
    
    /**
     * (銘柄削除確認)<BR>
     * 管理者IPO銘柄削除確認処理<BR>
     * <BR>
     * 管理者IPO銘柄削除サービスを取得し、execute()メソッドをコールする。<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * 管理者IPO銘柄削除確認リクエスト
     * @@return webbroker3.ipo.message.WEB3AdminIPOProductDeleteConfirmResponse
     * @@roseuid 40C7DEB10166
     */
    public WEB3AdminIPOProductDeleteConfirmResponse productDeleteConfirm(WEB3AdminIPOProductDeleteConfirmRequest l_request) 
    {
        final String STR_METHOD_NAME = " productDeleteConfirm(WEB3AdminIPOProductDeleteConfirmRequest)";
        log.entering(STR_METHOD_NAME);
        
        WEB3AdminIPOProductDeleteConfirmResponse l_response = null;
        WEB3AdminIpoProductDeleteService l_service = null;
        
        //管理者IPO銘柄削除サービスを取得
        try
        {
            l_service =
                (WEB3AdminIpoProductDeleteService)Services.getService(
                WEB3AdminIpoProductDeleteService.class);
        }
        //サービスで例外が発生した場合は、エラー情報をレスポンスメッセージに設定する
        catch (Exception l_ex)
        {
            l_response =
                (WEB3AdminIPOProductDeleteConfirmResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "管理者IPO銘柄削除サービスの取得に失敗しました。",
                l_response.errorInfo,l_ex);
            return l_response;      
        }
        
        //管理者IPO銘柄削除サービス.execute()メソッドをコールする
        try
        {
            l_response =
                (WEB3AdminIPOProductDeleteConfirmResponse)l_service.execute(
                    l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response =
                (WEB3AdminIPOProductDeleteConfirmResponse)l_request.createResponse();
			// 2004/11/12 U00407 ビジネスエラーをレスポンスにセットするように修正  坂上@@SRA START
			// l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
			l_response.errorInfo = l_ex.getErrorInfo();
			// 2004/11/12 U00407 ビジネスエラーをレスポンスにセットするように修正  坂上@@SRA END
            log.error(
                l_request,
                "管理者IPO銘柄削除サービスに失敗しました。",
                l_ex);
            return l_response;
        }
        
        log.exiting(STR_METHOD_NAME);
        return l_response;

    }
    
    /**
     * (銘柄削除完了)<BR>
     * 管理者IPO銘柄削除完了処理<BR>
     * <BR>
     * 管理者IPO銘柄削除サービスを取得し、execute()メソッドをコールする。<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * 管理者IPO銘柄削除完了リクエスト
     * @@return webbroker3.ipo.message.WEB3AdminIPOProductDeleteCompleteResponse
     * @@roseuid 40C7DEB1031C
     */
    public WEB3AdminIPOProductDeleteCompleteResponse productDeleteComplete(WEB3AdminIPOProductDeleteCompleteRequest l_request) 
    {
        final String STR_METHOD_NAME = " productDeleteComplete(WEB3AdminIPOProductDeleteCompleteRequest)";
        log.entering(STR_METHOD_NAME);
        
        WEB3AdminIPOProductDeleteCompleteResponse l_response = null;
        WEB3AdminIpoProductDeleteService l_service = null;
        
        //管理者IPO銘柄削除サービスを取得
        try
        {
            l_service =
                (WEB3AdminIpoProductDeleteService)Services.getService(
                    WEB3AdminIpoProductDeleteService.class);
        }
        //サービスで例外が発生した場合は、エラー情報をレスポンスメッセージに設定する
        catch (Exception l_ex)
        {
            l_response =
                (WEB3AdminIPOProductDeleteCompleteResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "管理者IPO銘柄削除サービスの取得に失敗しました。",
                l_response.errorInfo,l_ex);
            return l_response;      
        }
        
        //管理者IPO銘柄削除サービス.execute()メソッドをコールする
        try
        {
            l_response =
                (WEB3AdminIPOProductDeleteCompleteResponse)l_service.execute(
                    l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response =
                (WEB3AdminIPOProductDeleteCompleteResponse)l_request.createResponse();                
			// 2004/11/12 U00407 ビジネスエラーをレスポンスにセットするように修正  坂上@@SRA START
    		// l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
			l_response.errorInfo = l_ex.getErrorInfo();
            // 2004/11/12 U00407 ビジネスエラーをレスポンスにセットするように修正  坂上@@SRA END
            log.error(
                l_request,
                "管理者IPO銘柄削除に失敗しました。",
                l_ex);
            return l_response; 

        }
        
        log.exiting(STR_METHOD_NAME);
        return l_response;
     
    }
}
@
