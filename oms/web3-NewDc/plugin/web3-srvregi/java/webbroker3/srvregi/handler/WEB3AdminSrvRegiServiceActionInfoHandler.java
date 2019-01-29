head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.06.45.49;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7984d7db5d91d86;
filename	WEB3AdminSrvRegiServiceActionInfoHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : サービス利用管理者サービス履歴情報ハンドラ(WEB3AdminSrvRegiServiceActionInfoHandler.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/22 郭英 (中訊) 新規作成
*/

package webbroker3.srvregi.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.srvregi.message.WEB3AdminSrvRegiServiceHistoryRequest;
import webbroker3.srvregi.message.WEB3AdminSrvRegiServiceHistoryResponse;
import webbroker3.srvregi.service.delegate.WEB3AdminSrvRegiServiceActionInfoService;
import webbroker3.util.WEB3LogUtility;

/**
 * (サービス利用管理者サービス履歴情報ハンドラ)<BR>
 * サービス利用管理者サービス履歴情報ハンドラクラス<BR>
 * 
 * @@author 郭英
 * @@version 1.0
 */
public class WEB3AdminSrvRegiServiceActionInfoHandler implements MessageHandler 
{
    
    /**
     * ログ出力ユーティリティ。<BR>
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(WEB3AdminSrvRegiServiceActionInfoHandler.class);
    
    /**
     * @@roseuid 416F415C0000
     */
    public WEB3AdminSrvRegiServiceActionInfoHandler() 
    {
     
    }
    
    /**
     * (searchサービス履歴)<BR>
     * サービス利用管理者サービス履歴情報照会処理を行う。<BR>
     * <BR>
     * サービス利用管理者サービス履歴情報サービスを取得し、execute( )<BR>
     * メソッドをコールする。<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * サービス利用管理者サービス履歴情報リクエスト　@オブジェクト<BR>
     * @@return webbroker3.srvregi.message.WEB3AdminSrvRegiServiceHistoryResponse
     * @@roseuid 40F519AF01EC
     */
    public WEB3AdminSrvRegiServiceHistoryResponse searchSrvAction(WEB3AdminSrvRegiServiceHistoryRequest l_request) 
    {
        final String STR_METHOD_NAME = " searchSrvAction(WEB3AdminSrvRegiServiceHistoryRequest) ";
        log.entering(STR_METHOD_NAME);
        
        WEB3AdminSrvRegiServiceHistoryResponse l_response = null;
        WEB3AdminSrvRegiServiceActionInfoService l_service = null;
        
        try
        {
            l_service = (WEB3AdminSrvRegiServiceActionInfoService)Services.getService(WEB3AdminSrvRegiServiceActionInfoService.class);//Exception
        }
        // サービスで例外が発生した場合は、エラー情報をレスポンスメッセージに設定する
        catch (Exception l_ex)
        {
            l_response = (WEB3AdminSrvRegiServiceHistoryResponse) l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;    
            log.error(
                l_request, 
                " サービス利用管理者サービス履歴情報照会に失敗しました。",
                l_response.errorInfo, 
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        
        //サービス利用管理者サービス履歴情報サービス.execute()メソッドをコールする
        try
        {
            l_response = (WEB3AdminSrvRegiServiceHistoryResponse) l_service.execute(l_request);//WEB3BaseException
        }
        catch (WEB3BaseException l_ex)
        {
            l_response = (WEB3AdminSrvRegiServiceHistoryResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request, " サービス利用管理者サービス履歴情報照会に失敗しました。", l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        
        log.exiting(STR_METHOD_NAME);
        
        return l_response;
    }
}
@
