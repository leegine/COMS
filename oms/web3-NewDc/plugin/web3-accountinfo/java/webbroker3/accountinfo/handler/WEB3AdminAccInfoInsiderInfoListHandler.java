head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.10.02.13.13;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8644d782f8a12fc;
filename	WEB3AdminAccInfoInsiderInfoListHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者お客様情報内部者情報一覧ハンドラ(WEB3AdminAccInfoInsiderInfoListHandler.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/12/14 李海波 (中訊) 新規作成
*/
package webbroker3.accountinfo.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.accountinfo.message.WEB3AdminAccInfoInsiderInfoInputRequest;
import webbroker3.accountinfo.message.WEB3AdminAccInfoInsiderInfoInputResponse;
import webbroker3.accountinfo.message.WEB3AdminAccInfoInsiderInfoListRequest;
import webbroker3.accountinfo.message.WEB3AdminAccInfoInsiderInfoListResponse;
import webbroker3.accountinfo.service.delegate.WEB3AdminAccInfoInsiderInfoListService;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.util.WEB3LogUtility;

/**
 * (管理者お客様情報内部者情報一覧ハンドラ)<BR>
 * 管理者お客様情報内部者情報一覧ハンドラ<BR>
 * 
 * @@author 李海波
 * @@version 1.0
 */
public class WEB3AdminAccInfoInsiderInfoListHandler implements MessageHandler
{
    
    /**
     * ログ出力ユーティリティ。
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminAccInfoInsiderInfoListHandler.class);
        
    /**
     * @@roseuid 418F3A0D02CE
     */
    public WEB3AdminAccInfoInsiderInfoListHandler() 
    {
     
    }
    
    /**
     * (入力画面表示)<BR>
     * 内部者情報一覧入力画面表示処理を行う。<BR>
     * <BR>
     * 管理者お客様情報内部者情報一覧サービスImplを取得し、<BR>
     * execute()メソッドをコールする。<BR>
     * @@param l_request - 管理者お客様情報内部情報一覧入力リクエストデータオブジェクト<BR>
     * 
     * @@return WEB3AdminAccInfoInsiderInfoInputResponse
     * @@throws WEB3BaseException
     * @@roseuid 416B745B027C
     */
    public WEB3AdminAccInfoInsiderInfoInputResponse inputScreenDisplay(WEB3AdminAccInfoInsiderInfoInputRequest l_request) 
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " inputScreenDisplay(WEB3AdminAccInfoInsiderInfoInputRequest)";
        log.entering(STR_METHOD_NAME);

        WEB3AdminAccInfoInsiderInfoInputResponse l_response = null;
        WEB3AdminAccInfoInsiderInfoListService l_service = null;

        try
        {
            l_service = (WEB3AdminAccInfoInsiderInfoListService)Services.getService(WEB3AdminAccInfoInsiderInfoListService.class);
        }
        // サービスで例外が発生した場合は、エラー情報をレスポンスメッセージに設定する
        catch (Exception l_ex)
        {
            l_response = (WEB3AdminAccInfoInsiderInfoInputResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                " 管理者お客様情報内部者情報一覧サービスの取得に失敗しました。",
                l_response.errorInfo,
                l_ex);
            return l_response;
        }

        try
        {
            l_response = (WEB3AdminAccInfoInsiderInfoInputResponse)l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response = (WEB3AdminAccInfoInsiderInfoInputResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request, "管理者お客様情報内部者情報一覧の入力画面表示に失敗しました。", l_ex);
            return l_response;
        }

        log.exiting(STR_METHOD_NAME);

        return l_response;                
    }
        
    /**
     * (一覧画面表示)<BR>
     * 内部者情報一覧入力画面表示処理を行う。<BR>
     * <BR>
     * 管理者お客様情報内部者情報一覧サービスImplを取得し、<BR>
     * execute()メソッドをコールする。<BR>
     * @@param l_request - 管理者お客様情報内部情報一覧リクエストデータオブジェクト<BR>
     * 
     * @@return WEB3AdminAccInfoInsiderInfoListResponse
     * @@throws WEB3BaseException
     * @@roseuid 416B745B027C
     */
    public WEB3AdminAccInfoInsiderInfoListResponse listScreenDisplay(WEB3AdminAccInfoInsiderInfoListRequest l_request) 
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " listScreenDisplay(WEB3AdminAccInfoInsiderInfoListRequest)";
        log.entering(STR_METHOD_NAME);

        WEB3AdminAccInfoInsiderInfoListResponse l_response = null;
        WEB3AdminAccInfoInsiderInfoListService l_service = null;

        try
        {
            l_service = (WEB3AdminAccInfoInsiderInfoListService)Services.getService(WEB3AdminAccInfoInsiderInfoListService.class);
        }
        // サービスで例外が発生した場合は、エラー情報をレスポンスメッセージに設定する
        catch (Exception l_ex)
        {
            l_response = (WEB3AdminAccInfoInsiderInfoListResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                " 管理者お客様情報内部者情報一覧サービスの取得に失敗しました。",
                l_response.errorInfo,
                l_ex);
            return l_response;
        }

        try
        {
            l_response = (WEB3AdminAccInfoInsiderInfoListResponse)l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response = (WEB3AdminAccInfoInsiderInfoListResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request, "管理者お客様情報内部者情報一覧の一覧画面表示に失敗しました。", l_ex);
            return l_response;
        }

        log.exiting(STR_METHOD_NAME);

        return l_response;            
    }
}
@
