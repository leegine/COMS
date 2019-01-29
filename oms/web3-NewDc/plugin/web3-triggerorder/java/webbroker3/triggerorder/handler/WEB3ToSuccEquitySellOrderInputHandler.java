head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.02.00.18;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8704d7ec30c0c9d;
filename	WEB3ToSuccEquitySellOrderInputHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : （連続）現物株式売付入力ハンドラ(WEB3ToSuccEquitySellOrderInputHandler.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/10/18 鄭海良(中訊) 新規作成
*/

package webbroker3.triggerorder.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.triggerorder.message.WEB3SuccEquitySellInputRequest;
import webbroker3.triggerorder.message.WEB3SuccEquitySellInputResponse;
import webbroker3.triggerorder.service.delegate.WEB3ToSuccEquitySellOrderInputService;
import webbroker3.util.WEB3LogUtility;

/**
 * (（連続）現物株式売付入力ハンドラ)<BR>
 * （連続）現物株式売付入力ハンドラクラス。
 *   
 * @@author 鄭海良
 * @@version 1.0
 */
public class WEB3ToSuccEquitySellOrderInputHandler implements MessageHandler 
{
    
    /**
     * (ログ出力ユーティリティ。)<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3ToSuccEquitySellOrderInputHandler.class);
    
    /**
     * @@roseuid 4348ECB60196
     */
    public WEB3ToSuccEquitySellOrderInputHandler() 
    {
     
    }
    
    /**
     * (get入力画面)<BR>
     * （連続）現物株式売付注文入力表示処理を行う。<BR>
     * <BR>
     * 「（連続）現物株式売付注文入力サービス」を取得し、<BR>
     * execute()メソッドをコールする。<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * リクエストデータ。
     * @@return WEB3SuccEquitySellInputResponse
     * @@roseuid 43265ADF005C
     */
    public WEB3SuccEquitySellInputResponse getInputScreen(WEB3SuccEquitySellInputRequest l_request)
    {
        final String STR_METHOD_NAME =" getInputScreen(WEB3SuccEquitySellInputRequest )";
        log.entering(STR_METHOD_NAME);

        WEB3SuccEquitySellInputResponse l_response = null;
        WEB3ToSuccEquitySellOrderInputService l_service = null;

        try
        {            
            //get（連続）現物株式売付入力サービス
            l_service = (WEB3ToSuccEquitySellOrderInputService)
                Services.getService(WEB3ToSuccEquitySellOrderInputService.class);
        }
        // サービスで例外が発生した場合は、エラー情報をレスポンスメッセージに設定する
        catch (Exception e)
        {
            l_response =(WEB3SuccEquitySellInputResponse) l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;    
            log.error(l_request,
                "（連続）現物株式売付入力サービスの取得に失敗しました。",
                l_response.errorInfo,e);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

        try
        {
            //execute
            l_response = (WEB3SuccEquitySellInputResponse)
                l_service.execute(l_request);//WEB3BaseException
        }        
        catch (WEB3BaseException l_ex)
        {
            l_response = (WEB3SuccEquitySellInputResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request, 
                "（連続）現物株式売付注文入力表示処理を実施に失敗しました。",
                l_response.errorInfo,
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        catch (WEB3BaseRuntimeException l_ex)
        {
            l_response = (WEB3SuccEquitySellInputResponse) l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request, 
                "（連続）現物株式売付注文入力表示処理を実施に失敗しました。",
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
