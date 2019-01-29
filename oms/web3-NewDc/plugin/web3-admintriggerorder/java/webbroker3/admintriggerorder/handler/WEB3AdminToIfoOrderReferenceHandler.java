head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.17;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3AdminToIfoOrderReferenceHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : トリガー注文管理者・先物OP注文照会ハンドラ(WEB3AdminToIfoOrderReferenceHandler.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/02/15　@余新敏(中訊) 新規作成
*/

package webbroker3.admintriggerorder.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.admintriggerorder.message.WEB3AdminToIfoOrderRefInpRequest;
import webbroker3.admintriggerorder.message.WEB3AdminToIfoOrderRefInpResponse;
import webbroker3.admintriggerorder.message.WEB3AdminToIfoOrderRefRefRequest;
import webbroker3.admintriggerorder.message.WEB3AdminToIfoOrderRefRefResponse;
import webbroker3.admintriggerorder.service.delegate.WEB3AdminToIfoOrderReferenceService;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.util.WEB3LogUtility;

/**
 * (トリガー注文管理者・先物OP注文照会ハンドラ)<BR>
 * トリガー注文管理者・先物OP注文照会ハンドラクラス<BR>
 * 
 * @@author 余新敏
 * @@version 1.0
 */
public class WEB3AdminToIfoOrderReferenceHandler implements MessageHandler 
{
    /**
     * ログ出力ユーティリティ。<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminToIfoOrderReferenceHandler.class);

    /**
     * コンストラクタ<BR>
     * @@roseuid 43F1BBF800EA
     */
    public WEB3AdminToIfoOrderReferenceHandler() 
    {
     
    }
    
    /**
     * (get入力画面)<BR>
     * 先物OP注文照会入力画面表示処理を行う。<BR> 
     * <BR>
     * トリガー注文管理者・先物OP注文照会サービスImplを取得し、<BR> 
     * execute()メソッドをコールする。<BR>
     * @@param l_request - (トリガー注文管理者・先物OP注文照会入力リクエスト)<BR>
     * トリガー注文管理者・先物OP注文照会入力リクエストクラス<BR>
     * @@return WEB3AdminToIfoOrderRefInpResponse
     * @@roseuid 43E3540D0299
     */
    public WEB3AdminToIfoOrderRefInpResponse getInputScreen(WEB3AdminToIfoOrderRefInpRequest l_request)
    {
        final String STR_METHOD_NAME = " getInputScreen(WEB3AdminToIfoOrderRefInpRequest)";
        log.entering(STR_METHOD_NAME);
        
        WEB3AdminToIfoOrderRefInpResponse l_response = null;
        WEB3AdminToIfoOrderReferenceService l_service = null;
        
        try
        {
            //getトリガー注文管理者・先物OP注文照会サービス
            l_service = (WEB3AdminToIfoOrderReferenceService)
                Services.getService(WEB3AdminToIfoOrderReferenceService.class);
        }
        //サービスで例外が発生した場合は、エラー情報をレスポンスメッセージに設定する
        catch (Exception l_ex)
        {
            l_response = (WEB3AdminToIfoOrderRefInpResponse) l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "トリガー注文管理者・先物OP注文照会サービスの取得に失敗しました。", 
                l_response.errorInfo,
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        
        try
        {
            //execute
            l_response = (WEB3AdminToIfoOrderRefInpResponse) l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response = (WEB3AdminToIfoOrderRefInpResponse) l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request, "先物OP注文照会入力画面表示処理の実施に失敗しました。", l_response.errorInfo, l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        catch (WEB3BaseRuntimeException l_ex)
        {
            l_response = (WEB3AdminToIfoOrderRefInpResponse) l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request, "先物OP注文照会入力画面表示処理の実施に失敗しました。", l_response.errorInfo, l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * (get照会画面)<BR>
     * 先物OP注文照会処理を行う。<BR> 
     * <BR>
     * トリガー注文管理者・先物OP注文照会サービスImplを取得し、<BR> 
     * execute()メソッドをコールする。<BR>
     * @@param l_request - (トリガー注文管理者・先物OP注文照会リクエスト)<BR>
     * トリガー注文管理者・先物OP注文照会リクエストクラス<BR>
     * @@return WEB3AdminToIfoOrderRefRefResponse
     * @@roseuid 43E354A90008
     */
    public WEB3AdminToIfoOrderRefRefResponse getReferenceScreen(WEB3AdminToIfoOrderRefRefRequest l_request) 
    {
        final String STR_METHOD_NAME = " getReferenceScreen(WEB3AdminToIfoOrderRefRefRequest)";
        log.entering(STR_METHOD_NAME);
        
        WEB3AdminToIfoOrderRefRefResponse l_response = null;
        WEB3AdminToIfoOrderReferenceService l_service = null;
        
        try
        {
            //getトリガー注文管理者・先物OP注文照会サービス
            l_service = (WEB3AdminToIfoOrderReferenceService)
                Services.getService(WEB3AdminToIfoOrderReferenceService.class);
        }
        //サービスで例外が発生した場合は、エラー情報をレスポンスメッセージに設定する
        catch (Exception l_ex)
        {
            l_response = (WEB3AdminToIfoOrderRefRefResponse) l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request, 
                "トリガー注文管理者・先物OP注文照会サービスの取得に失敗しました。", 
                l_response.errorInfo, 
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        
        try
        {
            //execute
            l_response = (WEB3AdminToIfoOrderRefRefResponse) l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response = (WEB3AdminToIfoOrderRefRefResponse) l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request, "先物OP注文照会処理の実施に失敗しました。", l_response.errorInfo, l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        catch (WEB3BaseRuntimeException l_ex)
        {
            l_response = (WEB3AdminToIfoOrderRefRefResponse) l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request, "先物OP注文照会処理の実施に失敗しました。", l_response.errorInfo, l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
}
@
