head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.23.35;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3AdminAioCashinNoticeConfirmHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 入金連絡確認ハンドラクラス(WEB3AdminAioCashinNoticeConfirmHandler)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/13 韋念瓊 (中訊) 新規作成
*/

package webbroker3.aio.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.aio.message.WEB3AdminAioCashinConfirmListRequest;
import webbroker3.aio.message.WEB3AdminAioCashinConfirmListResponse;
import webbroker3.aio.service.delegate.WEB3AdminAioCashinNoticeConfirmService;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.util.WEB3LogUtility;

/**
 * (入金連絡確認ハンドラ)<BR>
 * 入金連絡確認ハンドラクラス<BR>
 * 
 * @@author 韋念瓊(中訊)
 * @@version 1.0
 */

public class WEB3AdminAioCashinNoticeConfirmHandler implements MessageHandler 
{
    /**
     * ログユーティリティ
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminAioCashinNoticeConfirmHandler.class);    
            
    
    /**
     * (入金連絡確認一覧リクエスト)<BR>
     * 入金連絡確認サービスを取得し、execute()メソッドをコールする。<BR>
     * <BR>
     * @@param l_request - (リクエストデータ)<BR>
     * @@return WEB3AdminAioCashinConfirmListResponse
     * @@roseuid 4108777A037B
     */
    public WEB3AdminAioCashinConfirmListResponse handleCashinConfirmListRequest(
        WEB3AdminAioCashinConfirmListRequest l_request) 
    {
        final String STR_METHOD_NAME = 
            "handleCashinConfirmListRequest(WEB3AdminAioCashinConfirmListRequest l_request) ";
        log.entering(STR_METHOD_NAME);

        //入金連絡確認サービスを取得し
        WEB3AdminAioCashinNoticeConfirmService l_service = null;
        WEB3AdminAioCashinConfirmListResponse l_response = null;
        try
        {
            l_service =
                (WEB3AdminAioCashinNoticeConfirmService) 
                Services.getService(WEB3AdminAioCashinNoticeConfirmService.class);
        }
        // サービスで例外が発生した場合は、エラー情報をレスポンスメッセージに設定する
        catch (Exception l_ex)
        {
            l_response =
                (WEB3AdminAioCashinConfirmListResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "入金連絡確認サービスを取得に失敗しました。",
                l_response.errorInfo,
                l_ex);
            return l_response;
        }
        try
        {
            //execute()メソッドをコールする
            l_response = (WEB3AdminAioCashinConfirmListResponse) l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response =
                (WEB3AdminAioCashinConfirmListResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request, 
                "入金連絡確認処理が失敗しました。", 
                l_ex.getErrorInfo(), 
                l_ex);
            return l_response;
        }
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
}
@
