head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.03.46.39;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	WEB3AdminAccOpenCompleteMailSendHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者口座開設完了メール送信ハンドラ(WEB3AdminAccOpenCompleteMailSendHandler.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/12/15 張学剛 新規作成
*/
package webbroker3.accountopen.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.accountopen.message.WEB3AdminAccOpenCompleteMailSendListRequest;
import webbroker3.accountopen.message.WEB3AdminAccOpenCompleteMailSendListResponse;
import webbroker3.accountopen.message.WEB3AdminAccOpenCompleteMailSendRequest;
import webbroker3.accountopen.message.WEB3AdminAccOpenCompleteMailSendResponse;
import webbroker3.accountopen.service.delegate.WEB3AdminAccOpenCompleteMailSendService;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.util.WEB3LogUtility;

/**
 * (管理者口座開設完了メール送信ハンドラ)<BR>
 * 管理者口座開設完了メール送信ハンドラ
 * @@author 張学剛
 * @@version 1.0
 */
public class WEB3AdminAccOpenCompleteMailSendHandler implements MessageHandler 
{
    /**
     * Logger
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(WEB3AdminAccOpenCompleteMailSendHandler.class);
    
    /**
     * @@roseuid 41B45E7400CB
     */
    public WEB3AdminAccOpenCompleteMailSendHandler() 
    {
     
    }
    
    /**
     * (メール送信一覧表示)<BR>
     * 口座開設完了メール送信一覧表示処理を行う。 <BR>
     * <BR>
     * 口座開設完了メール送信サービスを取得し、execute()メソッドをコールする。<BR>
     * @@param l_request - 管理者口座開設完了メール送信一覧リクエストデータオブジェクト
     * 
     * @@return webbroker3.accountopen.message.WEB3AdminAccOpenCompleteMailSendListResponse
     * @@throws WEB3BaseException
     * @@roseuid 41A1958402D1
     */
    public WEB3AdminAccOpenCompleteMailSendListResponse mailSendListDisplay(WEB3AdminAccOpenCompleteMailSendListRequest l_request) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " mailSendListDisplay(WEB3AdminAccOpenCompleteMailSendListRequest l_request)";
        log.entering(STR_METHOD_NAME);
        
        WEB3AdminAccOpenCompleteMailSendListResponse l_response = null;
        WEB3AdminAccOpenCompleteMailSendService l_service = null;
        
        try
        {
            l_service = (WEB3AdminAccOpenCompleteMailSendService)
                Services.getService(WEB3AdminAccOpenCompleteMailSendService.class);//Exception
        }        
        // サービスで例外が発生した場合は、エラー情報をレスポンスメッセージに設定する
        catch (Exception l_ex)
        {
            l_response = (WEB3AdminAccOpenCompleteMailSendListResponse) l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;    
            log.error(
                l_request, 
                " 口座開設完了メール送信サービスを取得に失敗しました。",
                l_response.errorInfo, 
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        
        try
        {
            l_response = (WEB3AdminAccOpenCompleteMailSendListResponse)l_service.execute(l_request);//WEB3BaseException
        }
        catch (WEB3BaseException l_ex)
        {
            l_response = (WEB3AdminAccOpenCompleteMailSendListResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            l_response.errorMessage = l_ex.getErrorMessage();
            log.error(l_request, " 管理者口座開設完了メール送信一覧リクエストに失敗しました。", l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        
        log.exiting(STR_METHOD_NAME);
        
        return l_response;
    }
    
    /**
     * (メール送信)<BR>
     * 口座開設完了メール送信処理を行う。 <BR>
     * <BR>
     * 口座開設完了メール送信サービスを取得し、execute()メソッドをコールする。<BR>
     * @@param l_request - 管理者口座開設完了メール送信リクエストデータオブジェクト
     * 
     * @@return webbroker3.accountopen.message.WEB3AdminAccOpenCompleteMailSendResponse
     * @@throws WEB3BaseException
     * @@roseuid 41A1958402E1
     */
    public WEB3AdminAccOpenCompleteMailSendResponse mailSend(WEB3AdminAccOpenCompleteMailSendRequest l_request) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " mailSend(WEB3AdminAccOpenCompleteMailSendRequest l_request)";
        log.entering(STR_METHOD_NAME);
        
        WEB3AdminAccOpenCompleteMailSendResponse l_response = null;
        WEB3AdminAccOpenCompleteMailSendService l_service = null;
        
        try
        {
            l_service = (WEB3AdminAccOpenCompleteMailSendService)
                Services.getService(WEB3AdminAccOpenCompleteMailSendService.class);//Exception
        }       
        // サービスで例外が発生した場合は、エラー情報をレスポンスメッセージに設定する
        catch (Exception l_ex)
        {
            l_response = (WEB3AdminAccOpenCompleteMailSendResponse) l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;    
            log.error(
                l_request, 
                " 口座開設完了メール送信サービスを取得に失敗しました。",
                l_response.errorInfo, 
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        
        try
        {
            l_response = (WEB3AdminAccOpenCompleteMailSendResponse)l_service.execute(l_request);//WEB3BaseException
        }
        catch (WEB3BaseException l_ex)
        {
            l_response = (WEB3AdminAccOpenCompleteMailSendResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            l_response.errorMessage = l_ex.getErrorMessage();
            log.error(l_request, " 管理者口座開設完了メール送信リクエストに失敗しました。", l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        
        log.exiting(STR_METHOD_NAME);
        
        return l_response;
    }
}
@
