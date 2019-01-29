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
filename	WEB3AdminFaqListHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者問合せ管理お問合せ一覧ハンドラ(WEB3AdminFaqListHandler)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/12/23 張宝楠 (中訊) 新規作成
*/

package webbroker3.faq.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.faq.message.WEB3AdminFaqListInputRequest;
import webbroker3.faq.message.WEB3AdminFaqListInputResponse;
import webbroker3.faq.message.WEB3AdminFaqListRequest;
import webbroker3.faq.message.WEB3AdminFaqListResponse;
import webbroker3.faq.service.delegate.WEB3AdminFaqListService;
import webbroker3.util.WEB3LogUtility;

/**
 * (管理者問合せ管理お問合せ一覧ハンドラ)<BR>
 * 管理者問合せ管理お問合せ一覧ハンドラ<BR>
 * 
 * @@author 張宝楠(中訊)
 * @@version 1.0
 */
public class WEB3AdminFaqListHandler implements MessageHandler 
{
    /**
     * ログ出力ユーティリティ。
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminFaqListHandler.class);
            
    /**
     * @@roseuid 41C25BF3034B
     */
    public WEB3AdminFaqListHandler() 
    {
     
    }
    
    /**
     * (入力画面表示)<BR>
     * 問合せ一覧入力画面表示処理を行う。 <BR>
     * <BR>
     * 管理者問合せ管理お問合せ一覧サービスを取得し、<BR>
     * execute()メソッドをコールする。<BR>
     * @@param l_request - 管理者問合せ管理お問合せ一覧入力リクエストデータオブジェクト
     * 
     * 
     * @@return webbroker3.faq.message.WEB3AdminFaqListInputResponse
     * @@throws WEB3BaseException
     * @@roseuid 41AC1B020118
     */
    public WEB3AdminFaqListInputResponse inputScreenDisplay(WEB3AdminFaqListInputRequest l_request) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " inputScreenDisplay(WEB3AdminFaqListInputRequest l_request)";
        log.entering(STR_METHOD_NAME);

        WEB3AdminFaqListInputResponse l_response = null;
        WEB3AdminFaqListService l_service = null;

        try
        {
            l_service = (WEB3AdminFaqListService)Services.getService(WEB3AdminFaqListService.class);
        }
        // サービスで例外が発生した場合は、エラー情報をレスポンスメッセージに設定する
        catch (Exception l_ex)
        {
            l_response = (WEB3AdminFaqListInputResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "管理者問合せ管理お問合せ一覧サービスの取得に失敗しました。",
                l_response.errorInfo,
                l_ex);
            return l_response;
        }

        try
        {
            l_response = (WEB3AdminFaqListInputResponse)l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response = (WEB3AdminFaqListInputResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request, "管理者問合せ管理お問合せ一覧の問合せ一覧入力画面表示処理に失敗しました。", l_ex);
            return l_response;
        }

        log.exiting(STR_METHOD_NAME);

        return l_response;
    }
    
    /**
     * (問合せ一覧表示)<BR>
     * 問合せ一覧表示処理を行う。<BR>
     * <BR>
     * 管理者問合せ管理お問合せ一覧サービスを取得し、<BR>
     * execute()メソッドをコールする。<BR>
     * @@param l_request - 管理者問合せ管理お問合せ一覧リクエストデータオブジェクト
     * @@return webbroker3.faq.message.WEB3AdminFaqListResponse
     * @@roseuid 41AC1B020137
     */
    public WEB3AdminFaqListResponse faqListDisplay(WEB3AdminFaqListRequest l_request) 
    {
        final String STR_METHOD_NAME = " faqListDisplay(WEB3AdminFaqListRequest l_request)";
        log.entering(STR_METHOD_NAME);

        WEB3AdminFaqListResponse l_response = null;
        WEB3AdminFaqListService l_service = null;

        try
        {
            l_service = (WEB3AdminFaqListService)Services.getService(WEB3AdminFaqListService.class);
        }
        // サービスで例外が発生した場合は、エラー情報をレスポンスメッセージに設定する
        catch (Exception l_ex)
        {
            l_response = (WEB3AdminFaqListResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "管理者問合せ管理お問合せ一覧サービスの取得に失敗しました。",
                l_response.errorInfo,
                l_ex);
            return l_response;
        }

        try
        {
            l_response = (WEB3AdminFaqListResponse)l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response = (WEB3AdminFaqListResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request, "管理者問合せ管理お問合せ一覧の問合せ一覧表示処理に失敗しました。", l_ex);
            return l_response;
        }

        log.exiting(STR_METHOD_NAME);

        return l_response;
    }
}
@
