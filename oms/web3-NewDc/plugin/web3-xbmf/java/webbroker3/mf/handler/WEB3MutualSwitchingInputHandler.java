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
filename	WEB3MutualSwitchingInputHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 投信乗換入力ハンドラクラス(WEB3MutualSwitchingInputHandler)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/08/09 于美麗 (中訊) 新規作成
                   2004/08/24 韋念瓊 (中訊) レビュー    
*/

package webbroker3.mf.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.mf.message.WEB3MutualSwitchingInputRequest;
import webbroker3.mf.message.WEB3MutualSwitchingInputResponse;
import webbroker3.mf.service.delegate.WEB3MutualSwitchingInputService;
import webbroker3.util.WEB3LogUtility;

/**
 * 投信乗換入力ハンドラクラス
 * 
 * @@author 于美麗(中訊)
 * @@version 1.0
 */
public class WEB3MutualSwitchingInputHandler implements MessageHandler
{
    /**
     * ログユーティリティ
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3MutualSwitchingInputHandler.class);

    /**
     * (乗換入力リクエスト )<BR>
     * 投資信託の乗換入力画面表示処理を行う。<BR>
     * <BR>
     * 投信乗換入力サービスを取得し、execute()メソッドをコールする。
     * @@param l_request - (リクエストデータ)<BR>
     * 投信乗換入力リクエストオブジェクト
     * @@return webbroker3.mf.message.WEB3MutualSwitchingInputResponse
     * @@roseuid 40B1713402BF
     */
    public WEB3MutualSwitchingInputResponse switchingInputRequest(WEB3MutualSwitchingInputRequest l_request)
        throws WEB3SystemLayerException
    {
        final String STR_METHOD_NAME =
            "switchingInputRequest(WEB3MutualSwitchingInputRequest l_request)";
        log.entering(STR_METHOD_NAME);

        WEB3MutualSwitchingInputResponse l_response = null;

        WEB3MutualSwitchingInputService l_service = null;

        try
        {
            l_service =
                (WEB3MutualSwitchingInputService) Services.getService(
            WEB3MutualSwitchingInputService.class);
        }
        // サービスで例外が発生した場合は、エラー情報をレスポンスメッセージに設定する
        catch (Exception l_ex)
        {
            l_response =
                (WEB3MutualSwitchingInputResponse) l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "投資信託の乗換入力画面表示処理が失敗しました。",
                l_response.errorInfo,
                l_ex);
            return l_response;
        }
        
        try
        {
            //投信乗換入力サービスを取得し、execute()メソッドをコールする
            l_response =
                (WEB3MutualSwitchingInputResponse) l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response =
                (WEB3MutualSwitchingInputResponse) l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request,
                "投資信託の乗換入力画面表示処理が失敗しました。",
                l_ex.getErrorInfo(),
                l_ex);
        }
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
}
@
