head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.06.45.08;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7984d7db5d91d86;
filename	WEB3AdminSrvRegiServiceChangeInputHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : サービス利用管理者サービス変更入力ハンドラ(WEB3AdminSrvRegiServiceChangeInputHandler.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/22 郭英 (中訊) 新規作成
*/

package webbroker3.srvregi.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.srvregi.message.WEB3AdminSrvRegiServiceChangeInputRequest;
import webbroker3.srvregi.message.WEB3AdminSrvRegiServiceChangeInputResponse;
import webbroker3.srvregi.service.delegate.WEB3AdminSrvRegiServiceChangeInputService;
import webbroker3.util.WEB3LogUtility;

/**
 * (サービス利用管理者サービス変更入力ハンドラ)<BR>
 * サービス利用管理者サービス変更入力ハンドラクラス<BR>
 * 
 * @@author 郭英
 * @@version 1.0
 */
public class WEB3AdminSrvRegiServiceChangeInputHandler implements MessageHandler 
{
    
    /**
     * ログ出力ユーティリティ。<BR>
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(WEB3AdminSrvRegiServiceChangeInputHandler.class);
    
    /**
     * @@roseuid 416F415B037A
     */
    public WEB3AdminSrvRegiServiceChangeInputHandler() 
    {
     
    }
    
    /**
     * (サービス変更入力リクエスト)<BR>
     * サービス利用管理者サービス変更入力処理を行う。<BR>
     * <BR>
     * サービス利用管理者サービス変更入力サービスを取得し、execute( )<BR>
     * メソッドをコールする。<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * サービス利用管理者サービス変更入力リクエスト　@オブジェクト<BR>
     * @@return webbroker3.srvregi.message.WEB3AdminSrvRegiServiceChangeInputResponse
     * @@roseuid 40F516D4022B
     */
    public WEB3AdminSrvRegiServiceChangeInputResponse srvChangeInputRequest(WEB3AdminSrvRegiServiceChangeInputRequest l_request) 
    {
        final String STR_METHOD_NAME = " srvChangeInputRequest(WEB3AdminSrvRegiServiceChangeInputRequest l_request) ";
        log.entering(STR_METHOD_NAME);
        
        WEB3AdminSrvRegiServiceChangeInputResponse l_response = null;
        WEB3AdminSrvRegiServiceChangeInputService l_service = null;
        
        try
        {
            l_service = (WEB3AdminSrvRegiServiceChangeInputService)Services.getService(WEB3AdminSrvRegiServiceChangeInputService.class);//Exception
        }
        // サービスで例外が発生した場合は、エラー情報をレスポンスメッセージに設定する
        catch (Exception l_ex)
        {
            l_response = (WEB3AdminSrvRegiServiceChangeInputResponse) l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;    
            log.error(
                l_request, 
                " サービス利用管理者サービス変更入力に失敗しました。",
                l_response.errorInfo, 
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        
        //サービス利用管理者サービス変更入力サービス.execute()メソッドをコールする
        try
        {
            l_response = (WEB3AdminSrvRegiServiceChangeInputResponse) l_service.execute(l_request);//WEB3BaseException
        }
        catch (WEB3BaseException l_ex)
        {
            l_response = (WEB3AdminSrvRegiServiceChangeInputResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request, " サービス利用管理者サービス変更入力に失敗しました。", l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        
        log.exiting(STR_METHOD_NAME);
        
        return l_response; 
    }
}
@
