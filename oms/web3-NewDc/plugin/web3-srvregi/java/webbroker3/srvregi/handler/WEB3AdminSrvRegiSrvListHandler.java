head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.06.44.34;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7984d7db5d91d86;
filename	WEB3AdminSrvRegiSrvListHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : サービス利用管理者サービス一覧ハンドラ(WEB3AdminSrvRegiSrvListHandler.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/25 李頴淵 新規作成
*/

package webbroker3.srvregi.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.srvregi.message.WEB3AdminSrvRegiServiceReferenceRequest;
import webbroker3.srvregi.message.WEB3AdminSrvRegiServiceReferenceResponse;
import webbroker3.srvregi.service.delegate.WEB3AdminSrvRegiSrvListService;
import webbroker3.util.WEB3LogUtility;

/**
 * (サービス利用管理者サービス一覧ハンドラ)<BR>
 * サービス利用管理者サービス一覧ハンドラクラス<BR>
 * 
 * @@author 李頴淵
 * @@version 1.0
 */
public class WEB3AdminSrvRegiSrvListHandler implements MessageHandler 
{
    
    /**
     * ログ出力ユーティリティ。<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminSrvRegiSrvListHandler.class);
    
    /**
     * @@roseuid 416F415B0138
     */
    public WEB3AdminSrvRegiSrvListHandler() 
    {
     
    }
    
    /**
     * (searchサービス)<BR>
     * サービス利用管理者サービス一覧照会処理を行う。<BR>
     * <BR>
     * サービス利用管理者サービス一覧サービスを取得し、execute( )メソッドをコールする。<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * サービス利用管理者サービス一覧リクエスト　@オブジェクト<BR>
     * @@return webbroker3.srvregi.message.WEB3AdminSrvRegiServiceReferenceResponse
     * @@roseuid 40F5029003B1
     */
    public WEB3AdminSrvRegiServiceReferenceResponse searchSrv(WEB3AdminSrvRegiServiceReferenceRequest l_request) 
    {
        final String STR_METHOD_NAME = " searchSrv(WEB3AdminSrvRegiServiceReferenceRequest)";
        log.entering(STR_METHOD_NAME);
        
        WEB3AdminSrvRegiServiceReferenceResponse l_response = null;
        WEB3AdminSrvRegiSrvListService l_service = null;
        
        //サービス利用管理者サービス一覧サービスを取得
        try
        {
            log.debug("l_service");
            l_service =
                (WEB3AdminSrvRegiSrvListService)Services.getService(
                WEB3AdminSrvRegiSrvListService.class);                  //Exception
        }
        //サービスで例外が発生した場合は、エラー情報をレスポンスメッセージに設定する
        catch (Exception l_ex)
        {
            l_response =
                (WEB3AdminSrvRegiServiceReferenceResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "サービス利用管理者サービス一覧サービスの取得に失敗しました。",
                l_response.errorInfo,l_ex);
            log.exiting(STR_METHOD_NAME);    
            return l_response;      
        }
        
        //サービス利用管理者サービス一覧サービス.execute()メソッドをコールする
        try
        {
            log.debug("l_response");
            l_response =
                (WEB3AdminSrvRegiServiceReferenceResponse)l_service.execute(
                    l_request);                        //WEB3BaseException
        }
        catch (WEB3BaseException l_ex)
        {
            l_response =
                (WEB3AdminSrvRegiServiceReferenceResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request,
                "サービス利用管理者サービス一覧サービスに失敗しました。",
                l_ex);
            log.exiting(STR_METHOD_NAME);    
            return l_response;
        }
        
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
}
@
