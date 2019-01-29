head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.57.51;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8704d7ec30c0c9d;
filename	WEB3ToSuccEquityManualOrderHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 株式連続注文手動発注ハンドラ(WEB3ToSuccEquityManualOrderHandler.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/03/06　@陳琦(中訊) 新規作成
*/

package webbroker3.triggerorder.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.triggerorder.message.WEB3EquityManualCompleteResponse;
import webbroker3.triggerorder.message.WEB3EquityManualConfirmResponse;
import webbroker3.triggerorder.message.WEB3EquitySuccManualCompleteRequest;
import webbroker3.triggerorder.message.WEB3EquitySuccManualConfirmRequest;
import webbroker3.triggerorder.service.delegate.WEB3ToSuccEquityManualOrderMainService;
import webbroker3.util.WEB3LogUtility;

/**
 * (株式連続注文手動発注ハンドラ)<BR>
 * 株式連続注文手動発注ハンドラクラス<BR>
 * 
 * @@author 陳琦
 * @@version 1.0
 */
public class WEB3ToSuccEquityManualOrderHandler implements MessageHandler 
{
    
    /**
     * ログ出力ユーティリティ。<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3ToSuccEquityManualOrderHandler.class);

    /**
     * コンストラクタ<BR>
     * @@roseuid 43F491C700DA
     */
    public WEB3ToSuccEquityManualOrderHandler() 
    {
     
    }
  
    /**
     * (confirm手動発注)<BR>
     * 株式連続注文手動発注確認処理を行う。<BR>
     * １）　@株式連続注文手動発注メインサービスImplを取得し、 <BR> 
     * 　@　@　@execute()メソッドをコールする。 <BR>
     * <BR>
     * 　@　@　@　@[execute()に指定する引数] <BR>
     * 　@　@　@　@request：　@パラメータ．株式連続注文手動発注確認リクエスト <BR>
     * <BR>
     * ２）　@execute()の戻り値を返す。<BR>
     * <BR>
     * @@param l_request - (リクエストデータ)
     * 株式連続注文手動発注確認リクエスト<BR>
     * @@return WEB3EquityManualConfirmResponse
     * @@roseuid 43E9A93203AF
     */
    public WEB3EquityManualConfirmResponse confirmManualOrder(
        WEB3EquitySuccManualConfirmRequest l_request) 
    {        
        final String STR_METHOD_NAME = "confirmManualOrder(" +
                "WEB3EquityManualConfirmRequest) ";
        log.entering(STR_METHOD_NAME);
        
        WEB3EquityManualConfirmResponse l_response = null;
        WEB3ToSuccEquityManualOrderMainService l_service = null;
        
        try
        {
            l_service = 
                (WEB3ToSuccEquityManualOrderMainService) Services.getService(
                    WEB3ToSuccEquityManualOrderMainService.class);
        }
        //サービスで例外が発生した場合は、エラー情報をレスポンスメッセージに設定する
        catch (Exception l_ex)
        {
            l_response = (WEB3EquityManualConfirmResponse) l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "株式連続注文手動発注サービスの取得に失敗しました。", 
                l_response.errorInfo,
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        
        try
        {
            //１）　@株式連続注文手動発注メインサービスImplを取得し、  
            //   execute()メソッドをコールする。 
            //
            //     [execute()に指定する引数] 
            //     request：　@パラメータ．株式手動発注確認リクエスト 
            l_response = (WEB3EquityManualConfirmResponse) l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response = (WEB3EquityManualConfirmResponse) l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request, "株式連続注文手動発注確認処理の実施に失敗しました。", 
                l_response.errorInfo, 
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        catch (WEB3BaseRuntimeException l_ex)
        {
            l_response = (WEB3EquityManualConfirmResponse) l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request, "株式連続注文手動発注確認処理の実施に失敗しました。", 
                l_response.errorInfo, 
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        
        //２）　@execute()の戻り値を返す
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * (complete手動発注)<BR>
     * 株式連続注文手動発注確認処理を行う。<BR>
     * １）　@株式連続注文手動発注メインサービスImplを取得し、<BR>  
     * 　@　@　@execute()メソッドをコールする。 <BR>
     * <BR>
     * 　@　@　@　@[execute()に指定する引数] <BR>
     * 　@　@　@　@request：　@パラメータ．株式連続注文手動発注完了リクエスト <BR>
     * <BR>
     * ２）　@execute()の戻り値を返す。<BR>
     * @@param l_request - (リクエストデータ)
     * 株式連続注文手動発注完了リクエスト<BR>
     * @@return WEB3EquityManualCompleteResponse
     * @@roseuid 43E9AB730019
     */
    public WEB3EquityManualCompleteResponse completeManualOrder(
        WEB3EquitySuccManualCompleteRequest l_request) 
    {   
        final String STR_METHOD_NAME = "completeManualOrder(" +
                "WEB3EquityManualCompleteRequest) ";
        log.entering(STR_METHOD_NAME);
        
        WEB3EquityManualCompleteResponse l_response = null;
        WEB3ToSuccEquityManualOrderMainService l_service = null;
        
        try
        {
            l_service = 
                (WEB3ToSuccEquityManualOrderMainService) Services.getService(
                    WEB3ToSuccEquityManualOrderMainService.class);
        }
        //サービスで例外が発生した場合は、エラー情報をレスポンスメッセージに設定する
        catch (Exception l_ex)
        {
            l_response = (WEB3EquityManualCompleteResponse) l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "株式連続注文手動発注サービスの取得に失敗しました。", 
                l_response.errorInfo,
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        
        try
        {
            //１）　@株式連続注文手動発注メインサービスImplを取得し、  
            //     execute()メソッドをコールする。 
            //
            //       [execute()に指定する引数] 
            //       request：　@パラメータ．株式手動発注完了リクエスト
            l_response = (WEB3EquityManualCompleteResponse) l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response = (WEB3EquityManualCompleteResponse) l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request, "株式連続注文手動発完了処理の実施に失敗しました。", 
                l_response.errorInfo, 
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        catch (WEB3BaseRuntimeException l_ex)
        {
            l_response = (WEB3EquityManualCompleteResponse) l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request, "株式連続注文手動発注完了処理の実施に失敗しました。", 
                l_response.errorInfo, 
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        
        //２）　@execute()の戻り値を返す。
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
}
@
