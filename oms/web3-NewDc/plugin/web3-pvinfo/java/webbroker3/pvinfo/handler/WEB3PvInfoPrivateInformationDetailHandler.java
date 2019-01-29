head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.22.08.09.19;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7444d8857e2568b;
filename	WEB3PvInfoPrivateInformationDetailHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : ﾌﾟﾗｲﾍﾞｰﾄｲﾝﾌｫﾒｰｼｮﾝ詳細ハンドラ(WEB3PvInfoPrivateInformationDetailHandler.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/18 王亞洲(中訊) 新規作成
Revesion History : 2004/10/23 李丁銀(中訊) 作成
*/
package webbroker3.pvinfo.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.pvinfo.message.WEB3PvInfoDirectMessageDeleteRequest;
import webbroker3.pvinfo.message.WEB3PvInfoDirectMessageDeleteResponse;
import webbroker3.pvinfo.message.WEB3PvInfoPrivateInformationDetailRequest;
import webbroker3.pvinfo.message.WEB3PvInfoPrivateInformationDetailResponse;
import webbroker3.pvinfo.service.delegate.WEB3PvInfoPrivateInformationDetailService;
import webbroker3.util.WEB3LogUtility;

/**
 * (ﾌﾟﾗｲﾍﾞｰﾄｲﾝﾌｫﾒｰｼｮﾝ詳細ハンドラ)<BR>
 * ﾌﾟﾗｲﾍﾞｰﾄｲﾝﾌｫﾒｰｼｮﾝ詳細ハンドラクラス<BR>
 * @@author 王亞洲
 * @@version 1.00
 */
public class WEB3PvInfoPrivateInformationDetailHandler implements MessageHandler
{
    /**
     *ログ出力ユーティリティ。<BR>
     */
    private WEB3LogUtility log = WEB3LogUtility.getInstance(WEB3PvInfoPrivateInformationDetailHandler.class);
    
    /**
     * (get詳細画面)<BR>
     * 詳細画面表示処理を行う。<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * ﾌﾟﾗｲﾍﾞｰﾄｲﾝﾌｫﾒｰｼｮﾝ詳細リクエストオブジェクト<BR>
     * @@return webbroker3.pvinfo.message.WEB3PvInfoPrivateInformationDetailResponse
     * @@roseuid 41479B2F0173
     */
    public WEB3PvInfoPrivateInformationDetailResponse getDetailedScreen(WEB3PvInfoPrivateInformationDetailRequest l_request)
    {
        final String STR_METHOD_NAME = " getDetailedScreen(WEB3PvInfoPrivateInformationDetailRequest)";
        log.entering(STR_METHOD_NAME);
      
        WEB3PvInfoPrivateInformationDetailResponse l_response = null;
        WEB3PvInfoPrivateInformationDetailService l_service = null;
        
        //詳細画面表示処理サービスを取得
        try
        {
            l_service = (WEB3PvInfoPrivateInformationDetailService)Services.getService(WEB3PvInfoPrivateInformationDetailService.class);
        }        
        //サービスで例外が発生した場合は
        catch(Exception l_ex)
        {
             l_response = (WEB3PvInfoPrivateInformationDetailResponse)l_request.createResponse();
             l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
             log.error(l_request, 
                "詳細画面表示処理サービスの取得に失敗しました。", 
                l_response.errorInfo, l_ex);    
             
             log.exiting(STR_METHOD_NAME);
             return l_response;                      
        }
        
        //詳細画面表示処理サービス.execute()
        try
        {
            l_response = (WEB3PvInfoPrivateInformationDetailResponse)l_service.execute(l_request);
        }
        catch(WEB3BaseException l_ex)
        {
            l_response = (WEB3PvInfoPrivateInformationDetailResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request, 
                "詳細画面表示処理サービス.execute()メソッドをコールすることが失敗しました。", 
                l_response.errorInfo, l_ex);
            
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (deleteダイレクト指定メッセージ)<BR>
     * ダイレクト指定メッセージ削除処理を行う。<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * ダイレクト指定メッセージ削除リクエストオブジェクト<BR>
     * @@return webbroker3.pvinfo.message.WEB3PvInfoDirectMessageDeleteResponse
     * @@roseuid 41479BA1026D
     */
    public WEB3PvInfoDirectMessageDeleteResponse deleteDirectMessage(WEB3PvInfoDirectMessageDeleteRequest l_request)
    {
        final  String STR_METHOD_NAME = "deleteDirectMessage(WEB3PvInfoDirectMessageDeleteRequest)";
        log.entering(STR_METHOD_NAME);
        
        WEB3PvInfoDirectMessageDeleteResponse l_response = null;
        WEB3PvInfoPrivateInformationDetailService l_service = null;
        
        //ダイレクト指定メッセージ削除処理を取得
        try
        {
            l_service = (WEB3PvInfoPrivateInformationDetailService)Services.getService(WEB3PvInfoPrivateInformationDetailService.class);            
        }     
        //サービスで例外が発生した場合は
        catch(Exception l_ex)
        {
            l_response = (WEB3PvInfoDirectMessageDeleteResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(l_request, 
                "ダイレクト指定メッセージ削除処理の取得に失敗しました。", 
                l_response.errorInfo, 
                l_ex);
            
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        
        //ダイレクト指定メッセージ削除処理.execute()
        try
        {
            l_response = (WEB3PvInfoDirectMessageDeleteResponse)l_service.execute(l_request);
        }
        catch(WEB3BaseException l_ex)
        {
            l_response = (WEB3PvInfoDirectMessageDeleteResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request, 
                "ダイレクト指定メッセージ削除処理.execute()メソッドをコールすることが失敗しました。", 
                l_ex);
            
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
}
@
