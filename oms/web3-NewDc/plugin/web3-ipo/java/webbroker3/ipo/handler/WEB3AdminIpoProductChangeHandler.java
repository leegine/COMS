head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.05.48.41;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6f44d7efa104045;
filename	WEB3AdminIpoProductChangeHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者IPO銘柄訂正ハンドラ(WEB3AdminIpoProductChangeHandler.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/08/16 彭巍 (中訊) 新規作成
*/

package webbroker3.ipo.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.ipo.message.WEB3AdminIPOProductChangeCompleteRequest;
import webbroker3.ipo.message.WEB3AdminIPOProductChangeCompleteResponse;
import webbroker3.ipo.message.WEB3AdminIPOProductChangeConfirmRequest;
import webbroker3.ipo.message.WEB3AdminIPOProductChangeConfirmResponse;
import webbroker3.ipo.message.WEB3AdminIPOProductChangeInputRequest;
import webbroker3.ipo.message.WEB3AdminIPOProductChangeInputResponse;
import webbroker3.ipo.service.delegate.WEB3AdminIpoProductChangeService;
import webbroker3.util.WEB3LogUtility;

/**
 * (管理者IPO銘柄訂正ハンドラ)<BR>
 * 管理者IPO銘柄訂正ハンドラクラス
 * @@author 彭巍
 * @@version 1.0
 */
public class WEB3AdminIpoProductChangeHandler implements MessageHandler 
{
    /**
      * ログユーティリティ<BR>
      */
      private static WEB3LogUtility log = WEB3LogUtility.getInstance( WEB3AdminIpoProductChangeHandler.class);
    /**
     * @@roseuid 4112EE5703C5
     */
    public WEB3AdminIpoProductChangeHandler() 
    {
     
    }
    
    /**
     * (銘柄訂正確認)<BR>
     * 管理者IPO銘柄訂正確認処理<BR>
     * <BR>
     * 管理者IPO銘柄訂正サービスを取得し、execute()メソッドをコールする。<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * 管理者IPO銘柄訂正確認リクエスト<BR>
     * @@return webbroker3.ipo.message.WEB3AdminIPOProductChangeConfirmResponse
     * @@roseuid 40C94834025D
     */
    public WEB3AdminIPOProductChangeConfirmResponse productChangeConfirm(WEB3AdminIPOProductChangeConfirmRequest l_request) 
    {
       final String STR_METHOD_NAME = "productChangeConfirm(WEB3AdminIPOProductChangeConfirmRequest)"; 
       log.entering(STR_METHOD_NAME);
       
       WEB3AdminIPOProductChangeConfirmResponse l_response = null;
       WEB3AdminIpoProductChangeService l_service = null;
        
       try
       {
           l_service = (WEB3AdminIpoProductChangeService) Services.getService(WEB3AdminIpoProductChangeService.class);
       }
       catch (Exception l_exception)
       {              
           l_response = (WEB3AdminIPOProductChangeConfirmResponse) l_request.createResponse();
           l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
           log.error(l_request, "管理者IPO銘柄訂正確認レスポンスの取得に失敗しました。", l_response.errorInfo, l_exception);
           return l_response;
       }
         
       try
       {
           l_response = (WEB3AdminIPOProductChangeConfirmResponse) l_service.execute(l_request);
       }
       catch (WEB3BaseException l_baseException)
       {
           l_response = (WEB3AdminIPOProductChangeConfirmResponse) l_request.createResponse();
           l_response.errorInfo = l_baseException.getErrorInfo();
           log.error(l_request, "管理者IPO銘柄訂正確認に失敗しました。", l_response.errorInfo,l_baseException);
           return l_response;
       }
       log.exiting(STR_METHOD_NAME);
       return l_response;
   }
    
    /**
     * (銘柄訂正完了)<BR>
     * 管理者IPO銘柄訂正完了処理<BR>
     * <BR>
     * 管理者IPO銘柄訂正サービスを取得し、execute()メソッドをコールする。<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * IPO銘柄新規登録完了リクエスト
     * @@return webbroker3.ipo.message.WEB3AdminIPOProductChangeCompleteResponse
     * @@roseuid 40C94834026C
     */
    public WEB3AdminIPOProductChangeCompleteResponse productChangeComplete(WEB3AdminIPOProductChangeCompleteRequest l_request) 
    {
       final String STR_METHOD_NAME = " productChangeComplete(WEB3AdminIPOProductChangeCompleteRequest)"; 
       log.entering(STR_METHOD_NAME);
       WEB3AdminIPOProductChangeCompleteResponse l_response = null;
       WEB3AdminIpoProductChangeService l_service = null;
        
       try
       {
           l_service = (WEB3AdminIpoProductChangeService) Services.getService(WEB3AdminIpoProductChangeService.class);
       }
       catch (Exception l_exception)
       {              
           l_response = (WEB3AdminIPOProductChangeCompleteResponse) l_request.createResponse();
           l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
           log.error(l_request, "管理者IPO銘柄訂正完了レスポンスの取得に失敗しました。", l_response.errorInfo, l_exception);
           return l_response;
       }
         
       try
       {
           l_response = (WEB3AdminIPOProductChangeCompleteResponse) l_service.execute(l_request);
       }
       catch (WEB3BaseException l_baseException)
       {
           l_response = (WEB3AdminIPOProductChangeCompleteResponse) l_request.createResponse();
           l_response.errorInfo = l_baseException.getErrorInfo();
           log.error(l_request, "管理者IPO銘柄訂正完了に失敗しました。", l_response.errorInfo,l_baseException);
           return l_response;
       }
       log.exiting(STR_METHOD_NAME);
       return l_response;
   }
    
    /**
     * (入力画面表示)<BR>
     * 管理者IPO銘柄訂正入力画面表示処理<BR>
     * <BR>
     * 管理者IPO銘柄訂正サービスを取得し、execute()メソッドをコールする。<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * IPO銘柄訂正入力リクエスト
     * @@return webbroker3.ipo.message.WEB3AdminIPOProductChangeInputResponse
     * @@roseuid 40C94834027C
     */
    public WEB3AdminIPOProductChangeInputResponse inputScreenIndication(WEB3AdminIPOProductChangeInputRequest l_request) 
     {
    final String STR_METHOD_NAME = " inputScreenIndication(WEB3AdminIPOProductChangeInputRequest)"; 
    log.entering(STR_METHOD_NAME);
    WEB3AdminIPOProductChangeInputResponse l_response = null;
    WEB3AdminIpoProductChangeService l_service = null;
        
    try
    {
        l_service = (WEB3AdminIpoProductChangeService) Services.getService(WEB3AdminIpoProductChangeService.class);
    }
    catch (Exception l_exception)
    {              
        l_response = (WEB3AdminIPOProductChangeInputResponse) l_request.createResponse();
        l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
        log.error(l_request, "管理者IPO銘柄訂正入力レスポンスの取得に失敗しました。", l_response.errorInfo, l_exception);
        return l_response;
    }
         
    try
    {
        l_response = (WEB3AdminIPOProductChangeInputResponse) l_service.execute(l_request);
    }
    catch (WEB3BaseException l_baseException)
    {
        l_response = (WEB3AdminIPOProductChangeInputResponse) l_request.createResponse();
        l_response.errorInfo = l_baseException.getErrorInfo();
        log.error(l_request, "管理者IPO銘柄訂正入力に失敗しました。", l_response.errorInfo,l_baseException);
        return l_response;
    }
    log.exiting(STR_METHOD_NAME);
    return l_response;
}
}
@
