head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.18;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3AdminBondOrderAndExecuteHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者新規約定入力ハンドラ(WEB3AdminBondOrderAndExecuteHandler.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/08/17 趙林鵬 (中訊) 新規作成
*/

package webbroker3.bd.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.util.WEB3LogUtility;
import webbroker3.bd.message.WEB3AdminBondExecCalculateRequest;
import webbroker3.bd.message.WEB3AdminBondExecCalculateResponse;
import webbroker3.bd.message.WEB3AdminBondExecCompleteRequest;
import webbroker3.bd.message.WEB3AdminBondExecCompleteResponse;
import webbroker3.bd.message.WEB3AdminBondExecConfirmRequest;
import webbroker3.bd.message.WEB3AdminBondExecConfirmResponse;
import webbroker3.bd.message.WEB3AdminBondExecInputRequest;
import webbroker3.bd.message.WEB3AdminBondExecInputResponse;
import webbroker3.bd.service.delegate.WEB3AdminBondOrderAndExecuteService;

/**
 * (管理者新規約定入力ハンドラ)<BR>
 * 管理者新規約定入力ハンドラクラス
 * 
 * @@author 趙林鵬
 * @@version 1.0
 */
public class WEB3AdminBondOrderAndExecuteHandler implements MessageHandler
{
    /**
     * ログ出力ユーティリティ。<BR>
     */
    private static WEB3LogUtility log = 
        WEB3LogUtility.getInstance(WEB3AdminBondOrderAndExecuteHandler.class);
    
    /**
     * @@roseuid 44E3362F0399
     */
    public WEB3AdminBondOrderAndExecuteHandler() 
    {
     
    }
    
    /**
     * (新規約定入力リクエスト)<BR>
     * 管理者新規注文約定入力処理を行う。 <BR>
     * <BR>
     * 管理者新規約定入力サービスを取得し、execute()メソッドをコールする。
     * @@param l_request - (リクエストデータ)<BR>
     * リクエストデータ
     * @@return WEB3AdminBondExecInputResponse
     * @@roseuid 44B61AB403B8
     */
    public WEB3AdminBondExecInputResponse inputOrderAndExecute(WEB3AdminBondExecInputRequest l_request) 
    {
        final String STR_METHOD_NAME = "inputOrderAndExecute(WEB3AdminBondExecInputRequest)";
        log.entering(STR_METHOD_NAME);
        
        WEB3AdminBondExecInputResponse l_response = null;
        WEB3AdminBondOrderAndExecuteService l_service = null;
        try
        {
            l_service =
                (WEB3AdminBondOrderAndExecuteService)Services.getService(
                        WEB3AdminBondOrderAndExecuteService.class);
        }
        catch (Exception l_ex)
        {
            l_response =
                (WEB3AdminBondExecInputResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "管理者新規約定入力サービスを取得に失敗しました。",
                l_response.errorInfo,
                l_ex);

            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        //execute()メソッドをコールする。
        try
        {
            l_response =
                (WEB3AdminBondExecInputResponse)l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response =
                (WEB3AdminBondExecInputResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request, 
                "execute()メソッドをコールすることが失敗しました。", 
                l_ex);
            
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

        log.exiting(STR_METHOD_NAME);
        return l_response;        
    }
    
    /**
     * (新規約定確認リクエスト)<BR>
     * 管理者新規注文約定入力確認処理を行う。 <BR>
     * <BR>
     * 管理者新規約定入力サービスを取得し、execute()メソッドをコールする。
     * @@param l_request - (リクエストデータ)<BR>
     * リクエストデータ
     * @@return WEB3AdminBondExecConfirmResponse
     * @@roseuid 44CD8CA901A7
     */
    public WEB3AdminBondExecConfirmResponse confirmOrderAndExecute(WEB3AdminBondExecConfirmRequest l_request) 
    {
        final String STR_METHOD_NAME = "confirmOrderAndExecute(WEB3AdminBondExecConfirmRequest)";
        log.entering(STR_METHOD_NAME);
        
        WEB3AdminBondExecConfirmResponse l_response = null;
        WEB3AdminBondOrderAndExecuteService l_service = null;
        try
        {
            l_service =
                (WEB3AdminBondOrderAndExecuteService)Services.getService(
                        WEB3AdminBondOrderAndExecuteService.class);
        }
        catch (Exception l_ex)
        {
            l_response =
                (WEB3AdminBondExecConfirmResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "管理者新規約定入力サービスを取得に失敗しました。",
                l_response.errorInfo,
                l_ex);

            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        //execute()メソッドをコールする。
        try
        {
            l_response =
                (WEB3AdminBondExecConfirmResponse)l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response =
                (WEB3AdminBondExecConfirmResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request, 
                "execute()メソッドをコールすることが失敗しました。", 
                l_ex);
            
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

        log.exiting(STR_METHOD_NAME);
        return l_response;        
    }
    
    /**
     * (新規約定完了リクエスト)<BR>
     * 管理者新規注文約定入力完了処理を行う。 <BR>
     * <BR>
     * 管理者新規約定入力サービスを取得し、execute()メソッドをコールする。
     * @@param l_request - (リクエストデータ)<BR>
     * リクエストデータ
     * @@return WEB3AdminBondExecCompleteResponse
     * @@roseuid 44CD8F5100E7
     */
    public WEB3AdminBondExecCompleteResponse completeOrderAndExecute(WEB3AdminBondExecCompleteRequest l_request) 
    {
        final String STR_METHOD_NAME = "completeOrderAndExecute(WEB3AdminBondExecCompleteRequest)";
        log.entering(STR_METHOD_NAME);
        
        WEB3AdminBondExecCompleteResponse l_response = null;
        WEB3AdminBondOrderAndExecuteService l_service = null;
        try
        {
            l_service =
                (WEB3AdminBondOrderAndExecuteService)Services.getService(
                        WEB3AdminBondOrderAndExecuteService.class);
        }
        catch (Exception l_ex)
        {
            l_response =
                (WEB3AdminBondExecCompleteResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "管理者新規約定入力サービスを取得に失敗しました。",
                l_response.errorInfo,
                l_ex);

            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        //execute()メソッドをコールする。
        try
        {
            l_response =
                (WEB3AdminBondExecCompleteResponse)l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response =
                (WEB3AdminBondExecCompleteResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request, 
                "execute()メソッドをコールすることが失敗しました。", 
                l_ex);
            
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

        log.exiting(STR_METHOD_NAME);
        return l_response;        
    }
    
    /**
     * (受渡代金計算リクエスト)<BR>
     * 売買代金、経過利子、受渡代金を計算する。  <BR>
     * <BR>
     * 管理者新規約定入力サービスを取得し、execute()メソッドをコールする。
     * @@param l_request - (リクエストデータ)<BR>
     * リクエストデータ
     * @@return WEB3AdminBondExecCalculateResponse
     * @@roseuid 44CD90D801B1
     */
    public WEB3AdminBondExecCalculateResponse calcEstimatedPrice(WEB3AdminBondExecCalculateRequest l_request) 
    {
        final String STR_METHOD_NAME = "calcEstimatedPrice(WEB3AdminBondExecCalculateRequest)";
        log.entering(STR_METHOD_NAME);
        
        WEB3AdminBondExecCalculateResponse l_response = null;
        WEB3AdminBondOrderAndExecuteService l_service = null;
        try
        {
            l_service =
                (WEB3AdminBondOrderAndExecuteService)Services.getService(
                        WEB3AdminBondOrderAndExecuteService.class);
        }
        catch (Exception l_ex)
        {
            l_response =
                (WEB3AdminBondExecCalculateResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "管理者新規約定入力サービスを取得に失敗しました。",
                l_response.errorInfo,
                l_ex);

            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        //execute()メソッドをコールする。
        try
        {
            l_response =
                (WEB3AdminBondExecCalculateResponse)l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response =
                (WEB3AdminBondExecCalculateResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request, 
                "execute()メソッドをコールすることが失敗しました。", 
                l_ex);
            
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

        log.exiting(STR_METHOD_NAME);
        return l_response;        
    }
}
@
