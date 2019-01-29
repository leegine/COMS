head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.02;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3MutualCancelHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 投資信託取消ハンドラクラス(WEB3MutualCancelHandler)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/08/09 于美麗 (中訊) 新規作成
                   2004/08/20 韋念瓊 (中訊) レビュー    
*/

package webbroker3.mf.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.mf.message.WEB3MutualCancelCompleteRequest;
import webbroker3.mf.message.WEB3MutualCancelCompleteResponse;
import webbroker3.mf.message.WEB3MutualCancelConfirmRequest;
import webbroker3.mf.message.WEB3MutualCancelConfirmResponse;
import webbroker3.mf.service.delegate.WEB3MutualCancelService;
import webbroker3.util.WEB3LogUtility;

/**
 * 投資信託取消ハンドラクラス
 * 
 * @@author 于美麗(中訊)
 * @@version 1.0
 */
public class WEB3MutualCancelHandler implements MessageHandler
{
    /**
     * ログユーティリティ
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3MutualCancelHandler.class);

    /**
     * (confirm取消)<BR>
     * 投資信託の取消審査を行う。<BR>
     * <BR>
     * 投資信託取消サービスを取得し、execute()メソッドをコールする。<BR>
     * @@param l_request - リクエストデータ
     * @@return webbroker3.mf.message.WEB3MutualCancelConfirmResponse
     * @@roseuid 4055775702CD
     */
    public WEB3MutualCancelConfirmResponse confirmCancel(WEB3MutualCancelConfirmRequest l_request)
        throws WEB3SystemLayerException
    {
        final String STR_METHOD_NAME =
            "confirmCancel(WEB3MutualCancelConfirmRequest l_request)";
        log.entering(STR_METHOD_NAME);

        WEB3MutualCancelService l_service = null;
        WEB3MutualCancelConfirmResponse l_response = null;

        try
        {
            l_service =
                (WEB3MutualCancelService) Services.getService(
                    WEB3MutualCancelService.class);
        }
        // サービスで例外が発生した場合は、エラー情報をレスポンスメッセージに設定する
        catch (Exception l_ex)
        {
            l_response =
                (WEB3MutualCancelConfirmResponse) l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "投資信託取消サービスの取得に失敗しました。",
                l_response.errorInfo,
                l_ex);
            return l_response;
        }

        try
        {
            l_response =
                (WEB3MutualCancelConfirmResponse) l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response =
                (WEB3MutualCancelConfirmResponse) l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request,
                "投資信託取消審査処理が失敗しました。",
                l_ex.getErrorInfo(),
                l_ex);
        }

        log.exiting(STR_METHOD_NAME);

        return l_response;
    }

    /**
     * (complete取消)<BR>
     * 投資信託取消登録を行う。<BR>
     * <BR>
     * 投資信託取消サービスを取得し、execute()メソッドをコールする。<BR>
     * @@param l_request - リクエストデータ
     * @@return webbroker3.mf.message.WEB3MutualCancelCompleteResponse
     * @@roseuid 4055775F0137
     */
    public WEB3MutualCancelCompleteResponse completeCancel(WEB3MutualCancelCompleteRequest l_request)
        throws WEB3SystemLayerException
    {
        final String STR_METHOD_NAME =
            "completeCancel(WEB3MutualCancelCompleteRequest l_request)";
        log.entering(STR_METHOD_NAME);

        WEB3MutualCancelService l_service = null;
        WEB3MutualCancelCompleteResponse l_response = null;

        try
        {
            l_service =
                (WEB3MutualCancelService) Services.getService(
                    WEB3MutualCancelService.class);
        }
        // サービスで例外が発生した場合は、エラー情報をレスポンスメッセージに設定する
        catch (Exception l_ex)
        {
            l_response =
                (WEB3MutualCancelCompleteResponse) l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "投資信託取消サービスの取得に失敗しました。",
                l_response.errorInfo,
                l_ex);
            return l_response;
        }

        try
        {
            l_response =
                (WEB3MutualCancelCompleteResponse) l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response =
                (WEB3MutualCancelCompleteResponse) l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request,
                "投資信託取消登録処理が失敗しました。",
                l_ex.getErrorInfo(),
                l_ex);
        }

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
}
@
