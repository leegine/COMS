head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.06.45.03;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7984d7db5d91d86;
filename	WEB3AdminSrvRegiAccountAppliStateListHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : サービス利用管理者顧客申込状況一覧ハンドラ(WEB3AdminSrvRegiAccountAppliStateListHandler.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/11/01 鄭海良(中訊) 新規作成
*/

package webbroker3.srvregi.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.srvregi.message.WEB3AdminSrvRegiStateRequest;
import webbroker3.srvregi.message.WEB3AdminSrvRegiStateResponse;
import webbroker3.srvregi.service.delegate.WEB3AdminSrvRegiAccountAppliStateListService;
import webbroker3.util.WEB3LogUtility;

/**
 * (サービス利用管理者顧客申込状況一覧ハンドラ)<BR>
 * サービス利用管理者顧客申込状況一覧ハンドラクラス<BR>
 *  
 * @@author 鄭海良
 * @@version 1.0  
 */
public class WEB3AdminSrvRegiAccountAppliStateListHandler implements MessageHandler 
{

    /**
     * (ログ出力ユーティリティ。)<BR>
     */
    private static WEB3LogUtility log = 
        WEB3LogUtility.getInstance(WEB3AdminSrvRegiAccountAppliStateListHandler.class);
    
    /**
     * @@roseuid 416F415C0290
     */
    public WEB3AdminSrvRegiAccountAppliStateListHandler() 
    {
     
    }
    
    /**
     * (search顧客申込状況)<BR>
     * サービス利用管理者顧客申込状況一覧処理を行う。<BR>
     * <BR>
     * サービス利用管理者顧客申込状況一覧サービスを取得し、execute( )メソッドをコールする。<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * サービス利用管理者顧客申込状況一覧リクエスト　@オブジェクト<BR>
     * @@return webbroker3.srvregi.message.WEB3AdminSrvRegiStateResponse
     * @@roseuid 40F51FD50112
     */
    public WEB3AdminSrvRegiStateResponse searchAccountAppliState(WEB3AdminSrvRegiStateRequest l_request) 
    {
        final String STR_METHOD_NAME = " searchAccountAppliState(WEB3AdminSrvRegiStateRequest )";    
        log.entering(STR_METHOD_NAME);
        
        WEB3AdminSrvRegiStateResponse l_response = null;
        WEB3AdminSrvRegiAccountAppliStateListService l_service = null;        
        try
        {
            //get service
            l_service = 
                (WEB3AdminSrvRegiAccountAppliStateListService)Services.getService(
                    WEB3AdminSrvRegiAccountAppliStateListService.class);
        }
        catch (Exception l_ex)
        {
        
            l_response = (WEB3AdminSrvRegiStateResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "サービス利用管理者顧客申込状況一覧サービスの取得に失敗しました。",
                l_response.errorInfo,
                l_ex);
            return l_response;                  
        }

        try
        {
            //execute()メソッドをコールする
            l_response = (WEB3AdminSrvRegiStateResponse)l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response = (WEB3AdminSrvRegiStateResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request, "search顧客申込状況に失敗しました。", l_ex);
            return l_response;
        }

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
}
@
