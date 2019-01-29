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
filename	WEB3MutualCancelAcceptHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 投資信託取消受付ハンドラクラス(WEB3MutualCancelAcceptHandler)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/08/09 于美麗 (中訊) 新規作成
                   2004/08/23 韋念瓊 (中訊) レビュー    
*/

package webbroker3.mf.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.mf.message.WEB3MutualCancelAcceptRequest;
import webbroker3.mf.message.WEB3MutualCancelAcceptResponse;
import webbroker3.mf.service.delegate.WEB3MutualCancelAcceptService;
import webbroker3.util.WEB3LogUtility;

/**
 * 投資信託取消受付ハンドラクラス
 * 
 * @@author 于美麗(中訊)
 * @@version 1.0
 */

public class WEB3MutualCancelAcceptHandler implements MessageHandler
{
    /**
     * ログユーティリティ
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3MutualCancelAcceptHandler.class);

    /**
     * (取消受付リクエスト)<BR>
     * 投資信託取消受付処理を行う。<BR>
     * <BR>
     * 投資信託取消受付サービスを取得し、execute()メソッドをコールする。<BR>
     * @@param l_request - リクエストデータ
     * @@return webbroker3.mf.message.WEB3MutualCancelAcceptResponse
     * @@roseuid 4056604300DC
     */
    public WEB3MutualCancelAcceptResponse cancelAcceptRequest(WEB3MutualCancelAcceptRequest l_request)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "cancelAcceptRequest(WEB3MutualCancelAcceptRequest l_request)";
        log.entering(STR_METHOD_NAME);

        WEB3MutualCancelAcceptResponse l_response = null;
        WEB3MutualCancelAcceptService l_mutualCancelAcceptService = null;
        
        try
        {
            l_mutualCancelAcceptService =
                (WEB3MutualCancelAcceptService) Services.getService(
                    WEB3MutualCancelAcceptService.class);
        }
        // サービスで例外が発生した場合は、エラー情報をレスポンスメッセージに設定する
        catch (Exception l_ex)
        {
            l_response =
                (WEB3MutualCancelAcceptResponse) l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "投資信託取消受付サービスの取得に失敗しました。",
                l_response.errorInfo,
                l_ex);
            return l_response;
        }

        try
        {
            //投資信託取消受付サービスを取得し、execute()メソッドをコールする
            l_response =
                (WEB3MutualCancelAcceptResponse) l_mutualCancelAcceptService.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response =
                (WEB3MutualCancelAcceptResponse) l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request, "投資信託取消受付処理が失敗しました。", l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
}
@
