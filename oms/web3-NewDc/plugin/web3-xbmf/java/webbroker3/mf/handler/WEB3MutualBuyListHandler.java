head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.03;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3MutualBuyListHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 投資信託買付一覧照会ハンドラ(WEB3MutualBuyListHandler)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/08/17 韋念瓊 (中訊) 新規作成
                         2004/08/23 黄建 (中訊) レビュー 
*/

package webbroker3.mf.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.mf.message.WEB3MutualBuyListRequest;
import webbroker3.mf.message.WEB3MutualBuyListResponse;
import webbroker3.mf.service.delegate.WEB3MutualBuyListService;
import webbroker3.util.WEB3LogUtility;

/**
 * 投資信託買付一覧照会ハンドラ
 * @@author 韋念瓊(中訊)
 * @@version 1.0
 */
public class WEB3MutualBuyListHandler implements MessageHandler 
{
    /**
     * ログユーティリティ
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3MutualBuyListHandler.class);
 
    /**
     * (search注文)<BR>
     * 投資信託買付一覧照会処理を実施する。<BR>
     * <BR>
     * 投資信託買付一覧照会サービスを取得し、execute()メソッドをコ<BR>ールする。<BR>
     * @@param l_request - リクエストデータ
     * @@return webbroker3.mf.message.WEB3MutualBuyListResponse
     * @@roseuid 40AC767801F3
     */
    public WEB3MutualBuyListResponse searchOrder(WEB3MutualBuyListRequest l_request) 
    {
        final String l_strMethodName = "searchOrder("
            + "WEB3MutualBuyListRequest l_request ";
        log.entering(l_strMethodName);
        
        WEB3MutualBuyListService l_service;
        WEB3MutualBuyListResponse l_response;
        try
        {
            l_service = 
                (WEB3MutualBuyListService)Services.getService(
                    WEB3MutualBuyListService.class);
        }
        // サービスで例外が発生した場合は、エラー情報をレスポンスメッセージに設定する
        catch (Exception l_ex)
        {
            l_response =
                (WEB3MutualBuyListResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "投資信託買付一覧照会サービスの取得に失敗しました。",
                l_response.errorInfo,
                l_ex);
            return l_response;
        }
        try
        {
            l_response =
                (WEB3MutualBuyListResponse)l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response =
                (WEB3MutualBuyListResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request, "投資信託買付一覧照会処理が失敗しました。", 
            l_response.errorInfo,
            l_ex);
            return l_response;
        }

        log.exiting(l_strMethodName);

        return l_response;   

    }
}
@
