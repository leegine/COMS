head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.18;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3AdminBondExecuteChangeHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者約定変更ハンドラ(WEB3AdminBondExecuteChangeHandler.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/08/17 周捷(中訊) 新規作成
*/

package webbroker3.bd.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.bd.message.WEB3AdminBondExecChangeCompleteRequest;
import webbroker3.bd.message.WEB3AdminBondExecChangeCompleteResponse;
import webbroker3.bd.message.WEB3AdminBondExecChangeConfirmRequest;
import webbroker3.bd.message.WEB3AdminBondExecChangeConfirmResponse;
import webbroker3.bd.message.WEB3AdminBondExecChangeInputRequest;
import webbroker3.bd.message.WEB3AdminBondExecChangeInputResponse;
import webbroker3.bd.service.delegate.WEB3AdminBondExecuteChangeService;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.util.WEB3LogUtility;

/**
 * (管理者約定変更ハンドラ)<BR>
 * 管理者約定変更ハンドラクラス
 * 
 * @@author 周捷(中訊)
 * @@version 1.0 
 */
public class WEB3AdminBondExecuteChangeHandler implements MessageHandler
{
    /**
     * ログユーティリティ
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminBondExecuteChangeHandler.class); 
    
    /**
     * @@roseuid 44E3363102EE
     */
    public WEB3AdminBondExecuteChangeHandler() 
    {
     
    }
    
    /**
     * (管理者約定変更入力リクエスト)<BR>
     * 管理者約定変更入力処理を行う。 <BR>
     * <BR>
     * 管理者約定変更サービスを取得し、execute()メソッドをコールする。<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * リクエストデータ<BR>
     * @@return WEB3AdminBondExecChangeInputResponse
     * @@roseuid 44C897F8037A
     */
    public WEB3AdminBondExecChangeInputResponse inputExecuteChange(
        WEB3AdminBondExecChangeInputRequest l_request) 
    {
        final String STR_METHOD_NAME = 
            "inputExecuteChange(WEB3AdminBondExecChangeInputRequest)";
        log.entering(STR_METHOD_NAME);

        //管理者約定変更サービスを取得し
        WEB3AdminBondExecuteChangeService l_service = null;
        WEB3AdminBondExecChangeInputResponse l_response = null;
        try
        {
            l_service =
                (WEB3AdminBondExecuteChangeService) 
                Services.getService(WEB3AdminBondExecuteChangeService.class);
        }
        // サービスで例外が発生した場合は、エラー情報をレスポンスメッセージに設定する
        catch (Exception l_ex)
        {
            l_response =
                (WEB3AdminBondExecChangeInputResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "管理者約定変更サービスを取得に失敗しました。",
                l_response.errorInfo,
                l_ex);
            return l_response;
        }
        try
        {
            //execute()メソッドをコールする
            l_response = (WEB3AdminBondExecChangeInputResponse) l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response =
                (WEB3AdminBondExecChangeInputResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request, 
                "管理者約定変更入力処理が失敗しました。", 
                l_ex.getErrorInfo(), 
                l_ex);
            return l_response;
        }
        catch (WEB3BaseRuntimeException l_ex)
        {
            l_response = 
                (WEB3AdminBondExecChangeInputResponse) l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request, 
                "管理者約定変更入力処理が失敗しました。", 
                l_response.errorInfo, 
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * (管理者約定変更確認リクエスト)<BR>
     * 管理者約定変更確認処理を行う。 <BR>
     * <BR>
     * 管理者約定変更サービスを取得し、execute()メソッドをコールする。<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * リクエストデータ<BR>
     * @@return WEB3AdminBondExecChangeConfirmResponse
     * @@roseuid 44CD965D0294
     */
    public WEB3AdminBondExecChangeConfirmResponse confirmExecuteChange(
        WEB3AdminBondExecChangeConfirmRequest l_request) 
    {
        final String STR_METHOD_NAME = 
            "confirmExecuteChange(WEB3AdminBondExecChangeConfirmRequest)";
        log.entering(STR_METHOD_NAME);

        //管理者約定変更サービスを取得し
        WEB3AdminBondExecuteChangeService l_service = null;
        WEB3AdminBondExecChangeConfirmResponse l_response = null;
        try
        {
            l_service =
                (WEB3AdminBondExecuteChangeService) 
                Services.getService(WEB3AdminBondExecuteChangeService.class);
        }
        // サービスで例外が発生した場合は、エラー情報をレスポンスメッセージに設定する
        catch (Exception l_ex)
        {
            l_response =
                (WEB3AdminBondExecChangeConfirmResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "管理者約定変更サービスを取得に失敗しました。",
                l_response.errorInfo,
                l_ex);
            return l_response;
        }
        try
        {
            //execute()メソッドをコールする
            l_response = (WEB3AdminBondExecChangeConfirmResponse) l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response =
                (WEB3AdminBondExecChangeConfirmResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request, 
                "管理者約定変更確認処理が失敗しました。", 
                l_ex.getErrorInfo(), 
                l_ex);
            return l_response;
        }
        catch (WEB3BaseRuntimeException l_ex)
        {
            l_response = 
                (WEB3AdminBondExecChangeConfirmResponse) l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request, 
                "管理者約定変更確認処理が失敗しました。", 
                l_response.errorInfo, 
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * (管理者約定変更完了リクエスト)<BR>
     * 管理者約定変更完了処理を行う。 <BR>
     * <BR>
     * 管理者約定変更サービスを取得し、execute()メソッドをコールする。<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * リクエストデータ<BR>
     * @@return WEB3AdminBondExecChangeCompleteResponse
     * @@roseuid 44CD97240105
     */
    public WEB3AdminBondExecChangeCompleteResponse completeExecuteChange(
        WEB3AdminBondExecChangeCompleteRequest l_request) 
    {
        final String STR_METHOD_NAME = 
            "completeExecuteChange(WEB3AdminBondExecChangeCompleteRequest)";
        log.entering(STR_METHOD_NAME);

        //管理者約定変更サービスを取得し
        WEB3AdminBondExecuteChangeService l_service = null;
        WEB3AdminBondExecChangeCompleteResponse l_response = null;
        try
        {
            l_service =
                (WEB3AdminBondExecuteChangeService) 
                Services.getService(WEB3AdminBondExecuteChangeService.class);
        }
        // サービスで例外が発生した場合は、エラー情報をレスポンスメッセージに設定する
        catch (Exception l_ex)
        {
            l_response =
                (WEB3AdminBondExecChangeCompleteResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "管理者約定変更サービスを取得に失敗しました。",
                l_response.errorInfo,
                l_ex);
            return l_response;
        }
        try
        {
            //execute()メソッドをコールする
            l_response = (WEB3AdminBondExecChangeCompleteResponse) l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response =
                (WEB3AdminBondExecChangeCompleteResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request, 
                "管理者約定変更完了処理が失敗しました。", 
                l_ex.getErrorInfo(), 
                l_ex);
            return l_response;
        }
        catch (WEB3BaseRuntimeException l_ex)
        {
            l_response = 
                (WEB3AdminBondExecChangeCompleteResponse) l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request, 
                "管理者約定変更完了処理が失敗しました。", 
                l_response.errorInfo, 
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
}
@
