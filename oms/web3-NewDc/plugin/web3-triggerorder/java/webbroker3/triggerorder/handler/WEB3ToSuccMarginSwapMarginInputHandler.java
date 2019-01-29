head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.57.56;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8704d7ec30c0c9d;
filename	WEB3ToSuccMarginSwapMarginInputHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : （連続）信用取引現引現渡入力ハンドラ(WEB3ToSuccMarginSwapMarginInputHandler.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/11/8 譚漢江(中訊) 新規作成
*/

package webbroker3.triggerorder.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.triggerorder.message.WEB3SuccMarginSwapInputRequest;
import webbroker3.triggerorder.message.WEB3SuccMarginSwapInputResponse;
import webbroker3.triggerorder.service.delegate.WEB3ToSuccMarginSwapMarginInputService;
import webbroker3.util.WEB3LogUtility;

/**
 * (（連続）信用取引現引現渡入力ハンドラ)<BR>
 * （連続）信用取引現引現渡入力ハンドラクラス。<BR>
 *   
 * @@author 譚漢江
 * @@version 1.0
 */
public class WEB3ToSuccMarginSwapMarginInputHandler implements MessageHandler 
{

    /**
     * (ログ出力ユーティリティ。)<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3ToSuccMarginSwapMarginInputHandler.class);

    /**
     * @@roseuid 4369ED300109
     */
    public WEB3ToSuccMarginSwapMarginInputHandler() 
    {
     
    }
    
    /**
     * (get現引現渡入力画面)<BR>
     * （連続）信用取引現引現渡入力画面表示処理を行う。<BR>
     * <BR>
     * （連続）信用取引現引現渡入力サービスImplを取得し、<BR>
     * execute()メソッドをコールする。<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * （連続）信用取引現引現渡注文入力リクエストオブジェクト。<BR>
     * @@return WEB3SuccMarginSwapInputResponse
     * @@roseuid 4340E2FB0360
     */
    public WEB3SuccMarginSwapInputResponse getSwapMarginInputScreen(WEB3SuccMarginSwapInputRequest l_request) 
    {
        final String STR_METHOD_NAME = " getSwapMarginInputScreen(WEB3SuccMarginSwapInputRequest)";
        log.entering(STR_METHOD_NAME);
        
        WEB3SuccMarginSwapInputResponse l_response = null;
        WEB3ToSuccMarginSwapMarginInputService l_service = null;
        
        try
        {            
            //get（連続）信用取引現引現渡入力サービス
            l_service = (WEB3ToSuccMarginSwapMarginInputService)
                Services.getService(WEB3ToSuccMarginSwapMarginInputService.class);
        }
        // サービスで例外が発生した場合は、エラー情報をレスポンスメッセージに設定する
        catch (Exception l_ex)
        {
            l_response = (WEB3SuccMarginSwapInputResponse) l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;    
            log.error(l_request,
                "（連続）信用取引現引現渡入力サービスの取得に失敗しました。",
                l_response.errorInfo,
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        
        try
        {
            //execute
            l_response = (WEB3SuccMarginSwapInputResponse) l_service.execute(l_request);
        }        
        catch (WEB3BaseException l_ex)
        {
            l_response = (WEB3SuccMarginSwapInputResponse) l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request, 
                "（連続）信用取引現引現渡入力表示処理の実施に失敗しました。",
                l_response.errorInfo,
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        catch (WEB3BaseRuntimeException l_ex)
        {
            l_response = (WEB3SuccMarginSwapInputResponse) l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request, 
                "（連続）信用取引現引現渡入力表示処理の実施に失敗しました。",
                l_response.errorInfo, 
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
}
@
