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
filename	WEB3MutualSellInputHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 投信解約入力ハンドラクラス(WEB3MutualSellInputHandler)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/08/09 韋念瓊 (中訊) 新規作成
                   2004/08/23 于美麗 (中訊) レビュー 
*/


package webbroker3.mf.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;

import webbroker3.mf.message.WEB3MutualSellInputRequest;
import webbroker3.mf.message.WEB3MutualSellInputResponse;
import webbroker3.mf.service.delegate.WEB3MutualSellInputService;
import webbroker3.util.WEB3LogUtility;

/**
 * 投信解約入力ハンドラクラス
 * 
 * @@author 韋念瓊(中訊)
 * @@version 1.0
 */
public class WEB3MutualSellInputHandler implements MessageHandler 
{
    /**
     * ログユーティリティ
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3MutualSellInputHandler.class);
    
    /**
     * (解約入力リクエスト)<BR>
     * 投資信託の解約入力画面表示処理を行う。<BR>
     * <BR>
     * 投信解約入力サービスを取得し、execute()メソッドをコールする。<BR>
     * @@param l_request - 投信解約入力リクエストオブジェクト
     * @@return webbroker3.mf.message.WEB3MutualSellInputResponse
     * @@roseuid 40B164240290
     */
    public WEB3MutualSellInputResponse sellInputRequest(
        WEB3MutualSellInputRequest l_request)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = 
            "sellInputRequest(WEB3MutualSellInputResponse l_request)";
        log.entering(STR_METHOD_NAME);       
        
        //投信解約入力サービスを取得し
        WEB3MutualSellInputService l_service = null;
        WEB3MutualSellInputResponse l_response = null;
        try
        {
            l_service =
                (WEB3MutualSellInputService) 
                Services.getService(WEB3MutualSellInputService.class);
        }
        // サービスで例外が発生した場合は、エラー情報をレスポンスメッセージに設定する
        catch (Exception l_ex)
        {
            l_response =
                (WEB3MutualSellInputResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "投信解約入力サービスの取得に失敗しました。",
                l_response.errorInfo,
                l_ex);
            return l_response;
        }
        try
        {
            //execute()メソッドをコールする
            l_response = (WEB3MutualSellInputResponse) l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response =
                (WEB3MutualSellInputResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request, 
                "投信解約入力処理が失敗しました。", 
                l_ex.getErrorInfo(), 
                l_ex);
            return l_response;
        }
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
}
@
