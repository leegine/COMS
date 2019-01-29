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
filename	WEB3MutualSwtProductListHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 投信乗換先銘柄一覧照会ハンドラ(WEB3MutualSwtProductListHandler)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/10/18 韋念瓊 (中訊) 新規作成                   
*/

package webbroker3.mf.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.mf.message.WEB3MutualSwTargetListRequest;
import webbroker3.mf.message.WEB3MutualSwTargetListResponse;
import webbroker3.mf.service.delegate.WEB3MutualSwtProductListService;
import webbroker3.util.WEB3LogUtility;

/**
 * 投信乗換先銘柄一覧照会ハンドラ
 * 
 * @@author 韋念瓊(中訊)
 * @@version 1.0
 */
public class WEB3MutualSwtProductListHandler implements MessageHandler 
{
    /**
     * ログユーティリティ
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3MutualSwtProductListHandler.class);
 
    /**
     * (投信乗換先銘柄一覧リクエスト)<BR>
     * 投信乗換先銘柄一覧照会処理を実施する。 <BR>
     * <BR>
     * 投信乗換先銘柄一覧照会サービスを取得し、execute()メソッドをコールする。<BR>
     * @@param l_request - リクエストデータ
     * @@return WEB3MutualSwTargetListResponse
     * @@throws WEB3BaseException
     * @@roseuid 40B2EDB5002C
     */
    public WEB3MutualSwTargetListResponse swtListRequest(WEB3MutualSwTargetListRequest l_request)
        throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = 
            "swtListRequest(WEB3MutualSwTargetListRequest l_request)";
        log.entering(STR_METHOD_NAME);        
        
        //投信乗換先銘柄一覧照会サービスを取得し
        WEB3MutualSwtProductListService l_service = null;
        WEB3MutualSwTargetListResponse l_response = null;
        try
        {
            l_service =
                (WEB3MutualSwtProductListService) 
                Services.getService(WEB3MutualSwtProductListService.class);
        }
        // サービスで例外が発生した場合は、エラー情報をレスポンスメッセージに設定する
        catch (Exception l_ex)
        {
            l_response =
                (WEB3MutualSwTargetListResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "投信乗換先銘柄一覧照会サービスの取得に失敗しました。",
                l_response.errorInfo,
                l_ex);
            return l_response;   
        }
        try
        {
            //execute()メソッドをコールする
            l_response = (WEB3MutualSwTargetListResponse) l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response = (WEB3MutualSwTargetListResponse) l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request, 
                "投信乗換先銘柄一覧照会処理が失敗しました。",
                l_ex.getErrorInfo(), 
                l_ex);
            return l_response;   
        }
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
}
@
