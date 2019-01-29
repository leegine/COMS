head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.02.00.47;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8704d7ec30c0c9d;
filename	WEB3ToStopEquityManualOrderHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 株式逆指値注文手動発注ハンドラ(WEB3ToStopEquityManualOrderHandler.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/03/06　@李俊(中訊) 新規作成
*/

package webbroker3.triggerorder.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.triggerorder.message.WEB3EquityManualCompleteResponse;
import webbroker3.triggerorder.message.WEB3EquityManualConfirmResponse;
import webbroker3.triggerorder.message.WEB3EquityStopManualCompleteRequest;
import webbroker3.triggerorder.message.WEB3EquityStopManualConfirmRequest;
import webbroker3.triggerorder.service.delegate.WEB3ToStopEquityManualOrderMainService;
import webbroker3.util.WEB3LogUtility;



/**
 * (株式逆指値注文手動発注ハンドラ)<BR>
 * 株式逆指値注文手動発注ハンドラクラス<BR>
 * 
 * @@author 李俊
 * @@version 1.0
 */
public class WEB3ToStopEquityManualOrderHandler implements MessageHandler 
{
    
    /**
     * ログ出力ユーティリティ。<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3ToStopEquityManualOrderHandler.class);

    /**
     * コンストラクタ<BR>
     * @@roseuid 43F491C700DA
     */
    public WEB3ToStopEquityManualOrderHandler() 
    {
     
    }
    
    /**
     * (confirm手動発注)<BR>
     * 株式逆指値注文手動発注確認処理を行う。<BR>
     * １）　@株式逆指値注文手動発注メインサービスImplを取得し、<BR>  
     *　@　@　@execute()メソッドをコールする。 <BR>
     * <BR>
     * 　@　@　@　@[execute()に指定する引数]<BR> 
     * 　@　@　@　@request：　@パラメータ.株式逆指値注文手動発注確認リクエスト <BR> 
     * <BR>
     * ２）　@execute()の戻り値を返す。<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * 株式逆指値注文手動発注確認リクエスト 
     * @@return WEB3EquityManualConfirmResponse
     * @@roseuid 43E9A93203AF
     */
    public WEB3EquityManualConfirmResponse confirmManualOrder(
        WEB3EquityStopManualConfirmRequest l_request) 
    {
        final String STR_METHOD_NAME = 
            "confirmManualOrder(WEB3EquityManualConfirmRequest) ";
        log.entering(STR_METHOD_NAME);
        
        WEB3EquityManualConfirmResponse l_response = null;
        WEB3ToStopEquityManualOrderMainService l_service = null;
        
        try
        {
            //get株式逆指値注文手動発注メインサービス
            l_service = (WEB3ToStopEquityManualOrderMainService) 
                Services.getService(
                WEB3ToStopEquityManualOrderMainService.class);
        }
        //サービスで例外が発生した場合は、エラー情報をレスポンスメッセージに設定する
        catch (Exception l_ex)
        {
            l_response = (WEB3EquityManualConfirmResponse) l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "株式逆指値注文手動発注メインサービスの取得に失敗しました。", 
                l_response.errorInfo,
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        
        try
        {
            //execute
            l_response = (WEB3EquityManualConfirmResponse) l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response = (WEB3EquityManualConfirmResponse) l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request, 
                "株式逆指値注文手動発注確認処理の実施に失敗しました。",
                l_response.errorInfo, 
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        catch (WEB3BaseRuntimeException l_ex)
        {
            l_response = (WEB3EquityManualConfirmResponse) l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request, 
                "株式逆指値注文手動発注確認処理の実施に失敗しました。", 
                l_response.errorInfo, 
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * (complete手動発注)<BR>
     * 株式逆指値注文手動発注完了処理を行う。<BR>
     * １）　@株式逆指値注文手動発注メインサービスImplを取得し、<BR>  
     * 　@　@　@execute()メソッドをコールする。<BR> 
     * <BR>
     * 　@　@　@　@[execute()に指定する引数]<BR> 
     * 　@　@　@　@request：　@パラメータ.株式逆指値注文手動発注完了リクエスト<BR> 
     * <BR>
     * ２）　@execute()の戻り値を返す。<BR>
     * @@param l_request - (リクエストデータ)
     * 株式逆指値注文手動発注完了リクエスト<BR>
     * @@return WEB3EquityManualCompleteResponse
     * @@roseuid 43E9AB730019
     */
    public WEB3EquityManualCompleteResponse completeManualOrder(
        WEB3EquityStopManualCompleteRequest l_request) 
    {
        final String STR_METHOD_NAME =
            "completeManualOrder(WEB3EquityManualCompleteRequest l_request) ";
        log.entering(STR_METHOD_NAME);
        
        WEB3EquityManualCompleteResponse l_response = null;
        WEB3ToStopEquityManualOrderMainService l_service = null;
        
        try
        {
            //get株式逆指値注文手動発注メインサービス
            l_service = (WEB3ToStopEquityManualOrderMainService) 
                Services.getService(
                WEB3ToStopEquityManualOrderMainService.class);
        }
        //サービスで例外が発生した場合は、エラー情報をレスポンスメッセージに設定する
        catch (Exception l_ex)
        {
            l_response = (WEB3EquityManualCompleteResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "株式逆指値注文手動発注メインサービスの取得に失敗しました。", 
                l_response.errorInfo,
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        
        try
        {
            //execute
            l_response = (WEB3EquityManualCompleteResponse) l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response = (WEB3EquityManualCompleteResponse) l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request,
                "株式逆指値注文手動発注完了処理の実施に失敗しました。", 
                l_response.errorInfo, 
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        catch (WEB3BaseRuntimeException l_ex)
        {
            l_response = (WEB3EquityManualCompleteResponse) l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request, 
                "株式逆指値注文手動発注完了処理の実施に失敗しました。", 
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
