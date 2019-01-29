head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.59.28;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8704d7ec30c0c9d;
filename	WEB3ToSuccMarginCloseMarginInputHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : （連続）信用取引返済入力ハンドラクラス(WEB3ToSuccMarginCloseMarginInputHandler.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/11/16　@鄭徳懇(中訊) 新規作成
*/

package webbroker3.triggerorder.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.triggerorder.message.WEB3SuccMarginCloseInputRequest;
import webbroker3.triggerorder.message.WEB3SuccMarginCloseInputResponse;
import webbroker3.triggerorder.service.delegate.WEB3ToSuccMarginCloseMarginInputService;
import webbroker3.util.WEB3LogUtility;

/**
 * （（連続）信用取引返済入力ハンドラ)<BR>
 * （連続）信用取引返済入力ハンドラクラス<BR>
 * 
 * @@author 鄭徳懇
 * @@version 1.0 
 */
public class WEB3ToSuccMarginCloseMarginInputHandler implements MessageHandler 
{
    
    /**
     * (ログ出力ユーティリティ。)<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3ToSuccMarginCloseMarginInputHandler.class);

    /**
     * @@roseuid 4369ED350196
     */
    public WEB3ToSuccMarginCloseMarginInputHandler() 
    {
     
    }
    
    /**
     * (get返済入力画面)<BR>
     * （連続）信用取引返済入力画面表示処理を行う。<BR>
     * <BR>
     * （連続）信用取引返済入力サービスImplを取得し、<BR>
     * execute()メソッドをコールする。<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * （連続）信用取引返済注文入力リクエストオブジェクト<BR>
     * @@return WEB3SuccMarginCloseInputResponse
     * @@roseuid 43294821002D
     */
    public WEB3SuccMarginCloseInputResponse getCloseMarginChangeInputScreen(WEB3SuccMarginCloseInputRequest l_request) 
    {
        final String STR_METHOD_NAME = " getCloseMarginChangeInputScreen(WEB3SuccMarginCloseInputRequest)";
        log.entering(STR_METHOD_NAME);

        WEB3SuccMarginCloseInputResponse l_response = null;
        WEB3ToSuccMarginCloseMarginInputService l_service = null;
        
        try
        {            
            //get（連続）信用取引返済入力サービス
            l_service = (WEB3ToSuccMarginCloseMarginInputService)
                Services.getService(WEB3ToSuccMarginCloseMarginInputService.class);
        }
        // サービスで例外が発生した場合は、エラー情報をレスポンスメッセージに設定する
        catch (Exception l_ex)
        {
            l_response = (WEB3SuccMarginCloseInputResponse) l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;    
            log.error(l_request, "（連続）信用取引返済入力サービスの取得に失敗しました。", l_response.errorInfo, l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        
        try
        {
            //execute
            l_response = (WEB3SuccMarginCloseInputResponse) l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response = (WEB3SuccMarginCloseInputResponse) l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request, "（連続）信用取引返済入力画面表示処理の実施に失敗しました。", l_response.errorInfo, l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        catch (WEB3BaseRuntimeException l_ex)
        {
            l_response = (WEB3SuccMarginCloseInputResponse) l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request, "（連続）信用取引返済入力画面表示処理の実施に失敗しました。", l_response.errorInfo, l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
}
@
