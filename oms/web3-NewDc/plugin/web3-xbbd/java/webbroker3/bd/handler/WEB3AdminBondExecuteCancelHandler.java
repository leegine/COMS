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
filename	WEB3AdminBondExecuteCancelHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者債券約定取消ハンドラ(WEB3AdminBondExecuteCancelHandler.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/8/16 徐大方(中訊) 新規作成         
*/

package webbroker3.bd.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.bd.message.WEB3AdminBondExecCancelCompleteRequest;
import webbroker3.bd.message.WEB3AdminBondExecCancelCompleteResponse;
import webbroker3.bd.message.WEB3AdminBondExecCancelConfirmRequest;
import webbroker3.bd.message.WEB3AdminBondExecCancelConfirmResponse;
import webbroker3.bd.service.delegate.WEB3AdminBondExecuteCancelService;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.util.WEB3LogUtility;

/**
 * (管理者債券約定取消ハンドラ)<BR>
 * 管理者債券約定取消ハンドラ クラス
 * 
 * @@author 徐大方
 * @@version 1.0
 */
public class WEB3AdminBondExecuteCancelHandler implements MessageHandler 
{
    /**
     * ログユーティリティ
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminBondExecuteCancelHandler.class); 
    
    /**
     * @@roseuid 44E336310177
     */
    public WEB3AdminBondExecuteCancelHandler() 
    {
     
    }
    
    /**
     * (管理者債券約定取消確認リクエスト)<BR>
     * 債券約定取消確認処理を行う。 <BR>
     * <BR>
     * 管理者債券約定取消サービスを取得し、execute()メソッドをコールする。
     * @@param l_request - (リクエスト)<BR>
     * 管理者債券約定取消確認リクエスト
     * @@return WEB3AdminBondExecCancelConfirmResponse
     * @@roseuid 44B6FCE10321
     */
    public WEB3AdminBondExecCancelConfirmResponse confirmExecuteCancel(
        WEB3AdminBondExecCancelConfirmRequest l_request) 
    {
        final String STR_METHOD_NAME = 
            "confirmExecuteCancel(WEB3AdminBondExecCancelConfirmRequest)";
        log.entering(STR_METHOD_NAME);

        //債券管理者銘柄登録サービスを取得し
        WEB3AdminBondExecuteCancelService l_service = null;
        WEB3AdminBondExecCancelConfirmResponse l_response = null;
        try
        {
            l_service =
                (WEB3AdminBondExecuteCancelService) 
                Services.getService(WEB3AdminBondExecuteCancelService.class);
        }
        // サービスで例外が発生した場合は、エラー情報をレスポンスメッセージに設定する
        catch (Exception l_ex)
        {
            l_response =
                (WEB3AdminBondExecCancelConfirmResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "管理者債券約定取消サービスを取得に失敗しました。",
                l_response.errorInfo,
                l_ex);
            return l_response;
        }
        try
        {
            //execute()メソッドをコールする
            l_response = (WEB3AdminBondExecCancelConfirmResponse) l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response =
                (WEB3AdminBondExecCancelConfirmResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request, 
                "債券約定取消確認処理が失敗しました。", 
                l_ex.getErrorInfo(), 
                l_ex);
            return l_response;
        }
        catch (WEB3BaseRuntimeException l_ex)
        {
            l_response = 
                (WEB3AdminBondExecCancelConfirmResponse) l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request, 
                "債券約定取消確認処理が失敗しました。", 
                l_response.errorInfo, 
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * (管理者債券約定取消完了リクエスト)<BR>
     * 債券約定取消完了処理を行う。 <BR>
     * <BR>
     * 管理者債券約定取消サービスを取得し、execute()メソッドをコールする。
     * @@param l_request - (リクエスト)<BR>
     * 管理者債券約定取消完了リクエスト
     * @@return WEB3AdminBondExecCancelCompleteResponse
     * @@roseuid 44B6FD2403DD
     */
    public WEB3AdminBondExecCancelCompleteResponse completeExecuteCancel(
        WEB3AdminBondExecCancelCompleteRequest l_request) 
    {
        final String STR_METHOD_NAME = 
            "completeExecuteCancel(WEB3AdminBondExecCancelCompleteRequest)";
        log.entering(STR_METHOD_NAME);

        //債券管理者銘柄登録サービスを取得し
        WEB3AdminBondExecuteCancelService l_service = null;
        WEB3AdminBondExecCancelCompleteResponse l_response = null;
        try
        {
            l_service =
                (WEB3AdminBondExecuteCancelService) 
                Services.getService(WEB3AdminBondExecuteCancelService.class);
        }
        // サービスで例外が発生した場合は、エラー情報をレスポンスメッセージに設定する
        catch (Exception l_ex)
        {
            l_response =
                (WEB3AdminBondExecCancelCompleteResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "管理者債券約定取消サービスを取得に失敗しました。",
                l_response.errorInfo,
                l_ex);
            return l_response;
        }
        try
        {
            //execute()メソッドをコールする
            l_response = (WEB3AdminBondExecCancelCompleteResponse) l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response =
                (WEB3AdminBondExecCancelCompleteResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request, 
                "債券約定取消完了処理が失敗しました。", 
                l_ex.getErrorInfo(), 
                l_ex);
            return l_response;
        }
        catch (WEB3BaseRuntimeException l_ex)
        {
            l_response = 
                (WEB3AdminBondExecCancelCompleteResponse) l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request, 
                "債券約定取消完了処理が失敗しました。", 
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
