head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.10;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3MarginSwapMarginInputHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 信用取引現引現渡入力ハンドラ(WEB3MarginSwapMarginInputHandler.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/09/20 凌建平 (中訊) 新規作成
                   2005/01/06 岡村和明(SRA) JavaDoc修正
*/

package webbroker3.equity.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.equity.message.WEB3MarginSwapMarginInputRequest;
import webbroker3.equity.message.WEB3MarginSwapMarginInputResponse;
import webbroker3.equity.service.delegate.WEB3MarginSwapMarginInputService;
import webbroker3.util.WEB3LogUtility;

/**
 * （信用取引現引現渡入力ハンドラ）。<BR>
 * <BR>
 * 信用取引現引現渡入力ハンドラクラス
 * @@author 凌建平
 * @@version 1.0
 */
public class WEB3MarginSwapMarginInputHandler implements MessageHandler 
{
    /**
     * (ログ出力ユーティリティ。)<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3MarginSwapMarginHandler.class);
    
    /**
     * @@roseuid 414184C40195
     */
    public WEB3MarginSwapMarginInputHandler() 
    {
     
    }
    
    /**
     * (信用取引現引現渡入力)<BR>
     * 信用取引現引現渡入力画面表示処理を行う。<BR>
     * <BR>
     * 信用取引現引現渡入力サービスを取得し、execute()メソッドをコールする。<BR>
     * @@param l_request - リクエストデータ
     * @@return WEB3MarginSwapMarginInputResponse
     * @@roseuid 4107112B03B8
     */
    public WEB3MarginSwapMarginInputResponse getSwapMarginInputScreen(WEB3MarginSwapMarginInputRequest l_request) 
    {
        final String STR_METHOD_NAME = "getSwapMarginInputScreen(WEB3MarginSwapMarginInputRequest)";
    
        log.entering(STR_METHOD_NAME);

        WEB3MarginSwapMarginInputResponse l_response = null;
        WEB3MarginSwapMarginInputService l_service      = null;
        
        //信用取引現引現渡入力サービスを取得
        try
        {
            l_service =
                (WEB3MarginSwapMarginInputService)Services.getService(
                    WEB3MarginSwapMarginInputService.class);
        }
        //サービスで例外が発生した場合は、エラー情報をレスポンスメッセージに設定する
        catch (Exception l_ex)
        {
            l_response =
                (WEB3MarginSwapMarginInputResponse) l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "信用取引現引現渡入力サービスを取得に失敗しました。",
                l_response.errorInfo,
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        
        //信用取引現引現渡入力サービスオブジェクト.execute（）をコールし、
        //処理結果であるレスポンスオブジェクトを取得する。
        try
        {
            l_response =
                (WEB3MarginSwapMarginInputResponse) l_service.execute(
                    l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response =
                (WEB3MarginSwapMarginInputResponse) l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request, "信用取引現引現渡入力に失敗しました。", l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        catch (WEB3BaseRuntimeException l_bre)
        {
            l_response =
                (WEB3MarginSwapMarginInputResponse) l_request.createResponse();
            l_response.errorInfo = l_bre.getErrorInfo();
            log.error(l_request, "信用取引現引現渡入力に失敗しました。", l_bre);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

        log.exiting(STR_METHOD_NAME);

        //レスポンスオブジェクトを返却する。
        return l_response;
    }
}
@
