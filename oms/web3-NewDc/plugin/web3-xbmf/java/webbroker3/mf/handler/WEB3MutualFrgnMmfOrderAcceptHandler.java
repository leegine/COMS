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
filename	WEB3MutualFrgnMmfOrderAcceptHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/** 
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 外貨MMF注文受付ハンドラ(WEB3MutualFrgnMmfOrderAcceptHandler.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2007/02/06 張騰宇 (中訊) 新規作成 (モデル534)
*/
package webbroker3.mf.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.mf.message.WEB3MutualFrgnMmfOrderAcceptResponse;
import webbroker3.mf.message.WEB3MutualFrgnMmfOrderAcceptRequest;
import webbroker3.mf.service.delegate.WEB3MutualFrgnMmfOrderAcceptService;
import webbroker3.util.WEB3LogUtility;

/**
 * (外貨MMF注文受付ハンドラ)<BR>
 * 外貨MMF注文受付ハンドラクラス<BR>
 * 
 * @@author 張騰宇(中訊)
 * @@version 1.0
 */
public class WEB3MutualFrgnMmfOrderAcceptHandler implements MessageHandler
{
    /**
     * ログユーティリティ
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3MutualFrgnMmfOrderAcceptHandler.class);
    
    /**
     * @@roseuid 45C440D701DB
     */
    public WEB3MutualFrgnMmfOrderAcceptHandler() 
    {
     
    }
    
    /**
     * (注文受付リクエスト)<BR>
     * 外貨MMF注文受付処理を行う。<BR>
     * <BR>
     * 外貨MMF注文受付サービスを取得し、execute()メソッドをコールする。<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * 定義なし<BR>
     * @@return webbroker3.mf.message.WEB3MutualFrgnMmfOrderAcceptResponse
     * @@roseuid 45B9C8F101D7
     */
    public WEB3MutualFrgnMmfOrderAcceptResponse orderAcceptRequest(
        WEB3MutualFrgnMmfOrderAcceptRequest l_request) 
    {
        final String STR_METHOD_NAME =
            "orderAcceptRequest(WEB3MutualFrgnMmfOrderAcceptRequest l_request)";
        log.entering(STR_METHOD_NAME);
        
        WEB3MutualFrgnMmfOrderAcceptService l_service = null;
        WEB3MutualFrgnMmfOrderAcceptResponse l_response = null;

        try
        {
            l_service =
                (WEB3MutualFrgnMmfOrderAcceptService) Services.getService(
                    WEB3MutualFrgnMmfOrderAcceptService.class);
        }
        // サービスで例外が発生した場合は、エラー情報をレスポンスメッセージに設定する
        catch (Exception l_ex)
        {
            l_response =
                (WEB3MutualFrgnMmfOrderAcceptResponse) l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "外貨MMF注文受付サービスの取得に失敗しました。",
                l_response.errorInfo,
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

        try
        {
            l_response =
                (WEB3MutualFrgnMmfOrderAcceptResponse)l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response =
                (WEB3MutualFrgnMmfOrderAcceptResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request,
                "外貨MMF注文受付処理が失敗しました。",
                l_ex.getErrorInfo(),
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
}
@
