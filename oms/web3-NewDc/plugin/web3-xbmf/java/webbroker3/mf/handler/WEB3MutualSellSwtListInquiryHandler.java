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
filename	WEB3MutualSellSwtListInquiryHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 投資信託解約乗換一覧照会ハンドラ(WEB3MutualSellSwtListInquiryHandler)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/08/09 韋念瓊 (中訊) 新規作成
                   2004/08/23 于美麗 (中訊) レビュー 
*/

package webbroker3.mf.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;

import webbroker3.mf.message.WEB3MutualSellSwtListRequest;
import webbroker3.mf.message.WEB3MutualSellSwtListResponse;
import webbroker3.mf.service.delegate.WEB3MutualSellSwtListInquiryService;
import webbroker3.util.WEB3LogUtility;

/**
 * 投資信託解約乗換一覧照会ハンドラ
 * 
 * @@author 韋念瓊(中訊)
 * @@version 1.0
 */
public class WEB3MutualSellSwtListInquiryHandler implements MessageHandler 
{
    /**
     * ログユーティリティ
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3MutualSellSwtListInquiryHandler.class);
 
    /**
     * (search解約乗換一覧)<BR>
     * 投資信託解約乗換一覧照会処理を実施する。<BR>
     * <BR>
     * 投資信託解約乗換一覧照会サービスを取得し、<BR>
     * execute()メソッドをコールする。<BR>
     * @@param l_request - リクエストデータ
     * @@return webbroker3.mf.message.WEB3MutualSellSwtListResponse
     * @@roseuid 40B2EDB5002C
     */
    public WEB3MutualSellSwtListResponse searchSellSwtList(WEB3MutualSellSwtListRequest l_request)
        throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = 
            "searchSellSwtList(WEB3MutualSellSwtListRequest l_request)";
        log.entering(STR_METHOD_NAME);        
        
        //投資信託解約乗換一覧照会サービスを取得し
        WEB3MutualSellSwtListInquiryService l_service = null;
        WEB3MutualSellSwtListResponse l_response = null;
        try
        {
            l_service =
                (WEB3MutualSellSwtListInquiryService) 
                Services.getService(WEB3MutualSellSwtListInquiryService.class);
        }
        // サービスで例外が発生した場合は、エラー情報をレスポンスメッセージに設定する
        catch (Exception l_ex)
        {
            l_response =
                (WEB3MutualSellSwtListResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "投資信託解約乗換一覧照会サービスの取得に失敗しました。",
                l_response.errorInfo,
                l_ex);
            return l_response;   
        }
        try
        {
            //execute()メソッドをコールする
            l_response = (WEB3MutualSellSwtListResponse) l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response = (WEB3MutualSellSwtListResponse) l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request, 
                "投資信託解約乗換一覧照会処理が失敗しました。",
                l_ex.getErrorInfo(), 
                l_ex);
            return l_response;   
        }
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
}
@
