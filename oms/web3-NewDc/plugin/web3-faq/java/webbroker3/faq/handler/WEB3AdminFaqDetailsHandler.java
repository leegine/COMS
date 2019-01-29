head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.24;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3AdminFaqDetailsHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者問い合わせ管理お問合せ詳細ハンドラ(WEB3AdminFaqDetailsHandler)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/12/23 張宝楠 (中訊) 新規作成
*/

package webbroker3.faq.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.faq.message.WEB3AdminFaqDetailsRequest;
import webbroker3.faq.message.WEB3AdminFaqDetailsResponse;
import webbroker3.faq.service.delegate.WEB3AdminFaqDetailsService;
import webbroker3.util.WEB3LogUtility;

/**
 * (管理者問い合わせ管理お問合せ詳細ハンドラ)<BR>
 * 管理者問い合わせ管理お問合せ詳細ハンドラ<BR>
 * 
 * @@author 張宝楠(中訊)
 * @@version 1.0
 */
public class WEB3AdminFaqDetailsHandler implements MessageHandler 
{
    /**
     * ログ出力ユーティリティ。
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminFaqDetailsHandler.class);
    
    /**
     * @@roseuid 41C25BF4005D
     */
    public WEB3AdminFaqDetailsHandler() 
    {
     
    }
    
    /**
     * (問合せ詳細表示)<BR>
     * 問合せ詳細表示処理を行う。<BR>
     * <BR>
     * 管理者問い合わせ管理お問合せ詳細サービスを取得し、<BR>
     * execute()メソッドをコールする。<BR>
     * @@param l_request - 管理者問合せ管理お問合せ詳細リクエストデータオブジェクト
     * @@return webbroker3.faq.message.WEB3AdminFaqDetailsResponse
     * @@roseuid 41AC2E2A03C0
     */
    public WEB3AdminFaqDetailsResponse faqDetailsDisplay(WEB3AdminFaqDetailsRequest l_request) 
    {
        final String STR_METHOD_NAME = " faqDetailsDisplay(WEB3AdminFaqDetailsRequest l_request)";
        log.entering(STR_METHOD_NAME);

        WEB3AdminFaqDetailsResponse l_response = null;
        WEB3AdminFaqDetailsService l_service = null;

        try
        {
            l_service = (WEB3AdminFaqDetailsService)Services.getService(WEB3AdminFaqDetailsService.class);
        }
        // サービスで例外が発生した場合は、エラー情報をレスポンスメッセージに設定する
        catch (Exception l_ex)
        {
            l_response = (WEB3AdminFaqDetailsResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "管理者問い合わせ管理お問合せ詳細サービスの取得に失敗しました。",
                l_response.errorInfo,
                l_ex);
            return l_response;
        }

        try
        {
            l_response = (WEB3AdminFaqDetailsResponse)l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response = (WEB3AdminFaqDetailsResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request, "管理者問い合わせ管理お問合せ詳細の問合せ詳細表示処理に失敗しました。", l_ex);
            return l_response;
        }

        log.exiting(STR_METHOD_NAME);

        return l_response;
    }
}
@
