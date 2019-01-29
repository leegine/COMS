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
filename	WEB3MutualSellHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 投資信託解約ハンドラクラス(WEB3MutualSellHandler)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/08/09 韋念瓊 (中訊) 新規作成
                   2004/08/23 于美麗 (中訊) レビュー 
*/

package webbroker3.mf.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;

import webbroker3.mf.message.WEB3MutualSellCompleteRequest;
import webbroker3.mf.message.WEB3MutualSellCompleteResponse;
import webbroker3.mf.message.WEB3MutualSellConfirmRequest;
import webbroker3.mf.message.WEB3MutualSellConfirmResponse;
import webbroker3.mf.service.delegate.WEB3MutualSellService;
import webbroker3.util.WEB3LogUtility;

/**
 * 投資信託解約ハンドラクラス
 * 
 * @@author 韋念瓊(中訊)
 * @@version 1.0
 */
public class WEB3MutualSellHandler implements MessageHandler 
{
    /**
     * ログユーティリティ
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3MutualSellHandler.class);
    
    /**
     * (confirm解約)<BR>
     * 投資信託解約審査を行う。<BR>
     * <BR>
     * 投資信託解約サービスを取得し、execute()メソッド<BR>
     * をコールする。<BR>
     * @@param l_request - リクエストデータ
     * @@return webbroker3.mf.message.WEB3MutualSellConfirmResponse
     * @@roseuid 4055698B0270
     */
    public WEB3MutualSellConfirmResponse confirmSell(
            WEB3MutualSellConfirmRequest l_request) throws WEB3BaseException
    {
        String STR_METHOD_NAME = "confirmSell(" +
            "WEB3MutualSellConfirmRequest l_request)";
        log.entering(STR_METHOD_NAME);       

        //投資信託解約サービスを取得し
        WEB3MutualSellService l_service = null;
        WEB3MutualSellConfirmResponse l_response = null;
        try
        {
            l_service = (WEB3MutualSellService) 
                Services.getService(WEB3MutualSellService.class);
        }
        // サービスで例外が発生した場合は、エラー情報をレスポンスメッセージに設定する
        catch (Exception l_ex)
        {
            l_response =
                (WEB3MutualSellConfirmResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "投資信託解約サービスの取得に失敗しました。",
                l_response.errorInfo,
                l_ex);
            return l_response;
        }
        try
        {
            //execute()メソッドをコールする
            l_response = (WEB3MutualSellConfirmResponse) l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response =
                (WEB3MutualSellConfirmResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request, "投資信託解約処理が失敗しました。", l_ex);
            return l_response;
        }
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * (complete解約)<BR>
     * 投資信託解約登録を行う。<BR>
     * <BR>
     * 投資信託解約サービスを取得し、execute()メソッド<BR>
     * をコールする。<BR>
     * @@param l_request - リクエストデータ
     * @@return webbroker3.mf.message.WEB3MutualSellCompleteResponse
     * @@roseuid 405569D8001E
     */
    public WEB3MutualSellCompleteResponse completeSell(
            WEB3MutualSellCompleteRequest l_request) 
            throws WEB3BaseException
    {
        String STR_METHOD_NAME = "completeSell(" +
            "WEB3MutualSellCompleteRequest l_request)";
        log.entering(STR_METHOD_NAME);

        //投資信託解約サービスを取得
        WEB3MutualSellService l_service = null;
        WEB3MutualSellCompleteResponse l_response = null;

        try
        {
            l_service = (WEB3MutualSellService) 
                Services.getService(WEB3MutualSellService.class);
        }
        // サービスで例外が発生した場合は、エラー情報をレスポンスメッセージに設定する
        catch (Exception l_ex)
        {
            l_response =
                (WEB3MutualSellCompleteResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "投資信託解約サービスの取得に失敗しました。",
                l_response.errorInfo,
                l_ex);
            return l_response;
        }

        try
        {
            //execute()メソッドをコールする
            l_response = (WEB3MutualSellCompleteResponse) l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response =
                (WEB3MutualSellCompleteResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request, 
                "投資信託解約登録が失敗しました。", 
                l_ex.getErrorInfo(), 
                l_ex);
            return l_response;
        }
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
}
@
