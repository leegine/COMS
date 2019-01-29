head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.06.44.47;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7984d7db5d91d86;
filename	WEB3AdminSrvRegiAccountDataUlStateInquiryHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : サービス利用管理者顧客データUL状況照会ハンドラ(WEB3AdminSrvRegiAccountDataUlStateInquiryHandler.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/27 鄭海良(中訊) 新規作成
*/

package webbroker3.srvregi.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.srvregi.message.WEB3AdminSrvRegiUploadStateRequest;
import webbroker3.srvregi.message.WEB3AdminSrvRegiUploadStateResponse;
import webbroker3.srvregi.service.delegate.WEB3AdminSrvRegiAccountDataUlStateInquiryService;
import webbroker3.util.WEB3LogUtility;

/**
 * (サービス利用管理者顧客データUL状況照会ハンドラ)<BR>
 * サービス利用管理者顧客データアップロード状況照会ハンドラクラス<BR>
 *  
 * @@author 鄭海良
 * @@version 1.0  
 */
public class WEB3AdminSrvRegiAccountDataUlStateInquiryHandler implements MessageHandler 
{

    /**
     * (ログ出力ユーティリティ。)<BR>
     */
    private static WEB3LogUtility log = 
        WEB3LogUtility.getInstance(WEB3AdminSrvRegiAccountDataUlStateInquiryHandler.class);
    
    /**
     * @@roseuid 416F415C0148
     */
    public WEB3AdminSrvRegiAccountDataUlStateInquiryHandler() 
    {
     
    }
    
    /**
     * (顧客データUL状況照会リクエスト)<BR>
     * 顧客データアップロード状況照会処理を行う。<BR>
     * <BR>
     * 顧客データUL状況照会サービスを取得し、execute( )メソッドをコールする。<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * サービス利用管理者顧客データUL状況照会リクエスト<BR>
     * @@return webbroker3.srvregi.message.WEB3AdminSrvRegiUploadStateResponse
     * @@roseuid 411AC737028E
     */
    public WEB3AdminSrvRegiUploadStateResponse accountDataUploadStateInqueryRequest(WEB3AdminSrvRegiUploadStateRequest l_request) 
    {
        final String STR_METHOD_NAME = " accountUploadScreenIndication(WEB3AdminSrvRegiUploadInputRequest )";    
        log.entering(STR_METHOD_NAME);
        
        WEB3AdminSrvRegiUploadStateResponse l_response = null;
        WEB3AdminSrvRegiAccountDataUlStateInquiryService l_service = null;        
        try
        {
            //get service
            l_service = 
                (WEB3AdminSrvRegiAccountDataUlStateInquiryService)Services.getService(
                    WEB3AdminSrvRegiAccountDataUlStateInquiryService.class);
        }
        catch (Exception l_e)
        {
            
            l_response = (WEB3AdminSrvRegiUploadStateResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "サービス利用管理者顧客データUL状況照会サービスの取得に失敗しました。",
                l_response.errorInfo,
                l_e);
            return l_response;                  
        }

        try
        {
            //execute()メソッドをコールする
            l_response = (WEB3AdminSrvRegiUploadStateResponse)l_service.execute(l_request);
        }
        catch (WEB3BaseException l_e)
        {
            l_response = (WEB3AdminSrvRegiUploadStateResponse)l_request.createResponse();
            l_response.errorInfo = l_e.getErrorInfo();
            log.error(l_request, "顧客データUL状況照会リクエストに失敗しました。", l_e);
            return l_response;
        }

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
}
@
