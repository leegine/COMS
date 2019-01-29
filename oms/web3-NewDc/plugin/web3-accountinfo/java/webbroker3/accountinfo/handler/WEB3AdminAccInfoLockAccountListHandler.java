head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.10.02.12.27;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8644d782f8a12fc;
filename	WEB3AdminAccInfoLockAccountListHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者お客様情報ロック顧客登録問合せ一覧ハンドラ(WEB3AdminAccInfoLockAccountListHandler)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/09/19 呉艶飛 (中訊) 新規作成
*/
package webbroker3.accountinfo.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.accountinfo.message.WEB3AdminAccInfoLockAccountSearchInputRequest;
import webbroker3.accountinfo.message.WEB3AdminAccInfoLockAccountSearchInputResponse;
import webbroker3.accountinfo.message.WEB3AdminAccInfoLockAccountSearchListRequest;
import webbroker3.accountinfo.message.WEB3AdminAccInfoLockAccountSearchListResponse;
import webbroker3.accountinfo.service.delegate.WEB3AdminAccInfoLockAccountListService;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.util.WEB3LogUtility;

/**
 * (管理者お客様情報ロック顧客登録問合せ一覧ハンドラ)<BR>
 * 管理者お客様情報ロック顧客登録問合せ一覧ハンドラ<BR>
 * <BR>
 * @@author 呉艶飛<BR>
 * @@version 1.0<BR>
 */
public class WEB3AdminAccInfoLockAccountListHandler implements MessageHandler
{
    /**
     * ログ出力ユーティリティ。
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminAccInfoLockAccountListHandler.class);

    public WEB3AdminAccInfoLockAccountListHandler() 
    {
     
    }
    
    /**
     * (入力画面表示)<BR>
     * ロック顧客登録問合せ一覧入力画面表示処理を行う。<BR> 
     * <BR>
     * ロック顧客登録問合せ一覧サービスを取得し、execute()メソッドをコールする。<BR>  
     * @@return WEB3AdminAccInfoInsiderInfoInputResponse<BR>
     * @@throws WEB3BaseException
     * @@roseuid 416B745B027C
     */
    public WEB3AdminAccInfoLockAccountSearchInputResponse inputScreenDisplay(WEB3AdminAccInfoLockAccountSearchInputRequest l_request) 
    {
        final String STR_METHOD_NAME = " inputScreenDisplay(WEB3AdminAccInfoLockAccountSearchInputRequest)";
        log.entering(STR_METHOD_NAME);

        WEB3AdminAccInfoLockAccountSearchInputResponse l_response = null;
        WEB3AdminAccInfoLockAccountListService l_service = null;

        try
        {
            l_service = (WEB3AdminAccInfoLockAccountListService)Services.getService(WEB3AdminAccInfoLockAccountListService.class);
        }
        // サービスで例外が発生した場合は、エラー情報をレスポンスメッセージに設定する
        catch (Exception l_ex)
        {
            l_response = (WEB3AdminAccInfoLockAccountSearchInputResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "管理者お客様情報ロック顧客登録問合せ一覧サービス の取得に失敗しました。",
                l_response.errorInfo,l_ex);
            return l_response;    
        }
        //管理者お客様情報ロック顧客登録問合せ一覧サービス.execute()メソッドをコールする
        try
        {
            l_response =
                (WEB3AdminAccInfoLockAccountSearchInputResponse)l_service.execute(
                    l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response = (WEB3AdminAccInfoLockAccountSearchInputResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request,
                "管理者お客様情報ロック顧客登録問合せ一覧サービスに失敗しました。",
                l_ex);
            return l_response; 

        }
        log.exiting(STR_METHOD_NAME);

        return l_response;                
    }
        
    /**
     * (ロック顧客登録問合せ一覧表示)<BR>
     * ロック顧客登録問合せ一覧表示処理を行う。 
     * ロック顧客登録問合せ一覧サービスを取得し、execute()メソッドをコールする。  
     * execute()メソッドをコールする。<BR>
     * @@param l_request - 管理者お客様情報内部情報一覧リクエストデータオブジェクト<BR>
     * <BR>
     * @@return WEB3AdminAccInfoLockAccountSearchListResponse<BR>
     * @@throws WEB3BaseException<BR>
     * @@roseuid 416B745B027C
     */
    public WEB3AdminAccInfoLockAccountSearchListResponse getLockAccountRegistList(WEB3AdminAccInfoLockAccountSearchListRequest l_request) 
    {
        final String STR_METHOD_NAME = " getLockAccountRegistList(WEB3AdminAccInfoLockAccountSearchListRequest)";
        log.entering(STR_METHOD_NAME);

        WEB3AdminAccInfoLockAccountSearchListResponse l_response = null;
        WEB3AdminAccInfoLockAccountListService l_service = null;

        try
        {
            l_service = (WEB3AdminAccInfoLockAccountListService)Services.getService(WEB3AdminAccInfoLockAccountListService.class);
        }
        // サービスで例外が発生した場合は、エラー情報をレスポンスメッセージに設定する
        catch (Exception l_ex)
        {
            l_response = (WEB3AdminAccInfoLockAccountSearchListResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "管理者お客様情報ロック顧客登録問合せ一覧サービス の取得に失敗しました。",
                l_response.errorInfo,l_ex);
            return l_response;   
        }
        //管理者お客様情報ロック顧客登録問合せ一覧サービス.execute()メソッドをコールする
        try
        {
            l_response =
                (WEB3AdminAccInfoLockAccountSearchListResponse)l_service.execute(
                    l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response = (WEB3AdminAccInfoLockAccountSearchListResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request,
                "管理者お客様情報ロック顧客登録問合せ一覧サービスに失敗しました。",
                l_ex);
            return l_response; 

        }
        log.exiting(STR_METHOD_NAME);

        return l_response;              
    }
}
@
