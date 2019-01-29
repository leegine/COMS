head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.06.45.20;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7984d7db5d91d86;
filename	WEB3SrvRegiServiceListInquiryHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : サービス利用サービス一覧照会ハンドラ(WEB3SrvRegiServiceListInquiryHandler.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/26 李頴淵 新規作成
*/

package webbroker3.srvregi.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.srvregi.message.WEB3SrvRegiReferenceRequest;
import webbroker3.srvregi.message.WEB3SrvRegiReferenceResponse;
import webbroker3.srvregi.service.delegate.WEB3SrvRegiServiceListInquiryService;
import webbroker3.util.WEB3LogUtility;

/**
 * (サービス利用サービス一覧照会ハンドラ)<BR>
 * サービス利用サービス一覧照会ハンドラクラス<BR>
 * 
 * @@author 李頴淵
 * @@version 1.0
 */
public class WEB3SrvRegiServiceListInquiryHandler implements MessageHandler 
{
    
    /**
     * ログ出力ユーティリティ。<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3SrvRegiServiceListInquiryHandler.class);
    
    /**
     * @@roseuid 416F415D031C
     */
    public WEB3SrvRegiServiceListInquiryHandler() 
    {
     
    }
    
    /**
     * (searchサービス)<BR>
     * サービス利用サービス一覧照会処理を行う。<BR>
     * <BR>
     * サービス利用サービス一覧照会サービスを取得し、execute( )メソッドをコールする。<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * サービス利用サービス一覧照会リクエスト　@オブジェクト<BR>
     * @@return webbroker3.srvregi.message.WEB3SrvRegiReferenceResponse
     * @@roseuid 40F781E40178
     */
    public WEB3SrvRegiReferenceResponse searchService(WEB3SrvRegiReferenceRequest l_request) 
    {
        final String STR_METHOD_NAME = " searchService(WEB3SrvRegiReferenceRequest)";
        log.entering(STR_METHOD_NAME);
        
        WEB3SrvRegiReferenceResponse l_response = null;
        WEB3SrvRegiServiceListInquiryService l_service = null;
        
        //サービス利用サービス一覧照会サービスを取得
        try
        {
            l_service =
                (WEB3SrvRegiServiceListInquiryService)Services.getService(
                WEB3SrvRegiServiceListInquiryService.class);    //Exception
        }
        //サービスで例外が発生した場合は、エラー情報をレスポンスメッセージに設定する
        catch (Exception l_ex)
        {
            l_response =
                (WEB3SrvRegiReferenceResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "サービス利用サービス一覧照会サービスの取得に失敗しました。",
                l_response.errorInfo,l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;      
        }
        
        //サービス利用サービス一覧照会サービス.execute()メソッドをコールする
        try
        {
            l_response =
                (WEB3SrvRegiReferenceResponse)l_service.execute(
                l_request);                      //WEB3BaseException
        }
        catch (WEB3BaseException l_ex)
        {
            l_response =
                (WEB3SrvRegiReferenceResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request,
                "サービス利用サービス一覧照会サービスに失敗しました。",
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        
        log.exiting(STR_METHOD_NAME);
        return l_response;
     
    }
}
@
