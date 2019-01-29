head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.22.08.09.06;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7444d8857e2568b;
filename	WEB3AdminPvInfoConditionRegistHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者表示設定登録ハンドラ(WEB3AdminPvInfoConditionRegistHandler.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/18 王亞洲(中訊) 新規作成
Revesion History : 2004/10/26 李弘毅(中訊) 作成
*/
package webbroker3.pvinfo.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.pvinfo.message.WEB3AdminPvInfoConditionRegistCompleteRequest;
import webbroker3.pvinfo.message.WEB3AdminPvInfoConditionRegistCompleteResponse;
import webbroker3.pvinfo.message.WEB3AdminPvInfoConditionRegistConfirmRequest;
import webbroker3.pvinfo.message.WEB3AdminPvInfoConditionRegistConfirmResponse;
import webbroker3.pvinfo.message.WEB3AdminPvInfoConditionRegistInputRequest;
import webbroker3.pvinfo.message.WEB3AdminPvInfoConditionRegistInputResponse;
import webbroker3.pvinfo.service.delegate.WEB3AdminPvInfoConditionRegistService;
import webbroker3.util.WEB3LogUtility;

/**
 * (管理者表示設定登録ハンドラ)<BR>
 * 管理者表示設定登録ハンドラクラス<BR>
 * @@author 王亞洲
 * @@version 1.00
 */
public class WEB3AdminPvInfoConditionRegistHandler implements MessageHandler
{
    
    /**
     * ログ出力ユーティリティ。<BR>
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(WEB3AdminPvInfoConditionRegistHandler.class);

    /**
     * (get表示設定登録入力画面)<BR>
     * 表示設定登録入力画面表示処理を行う。<BR>
     * <BR>
     * 管理者表示設定登録サービスImplを取得し、<BR>
     * execute()メソッドをコールする。<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * 管理者・表示設定登録入力リクエストオブジェクト<BR>
     * @@return webbroker3.pvinfo.message.WEB3AdminPvInfoConditionRegistInputResponse
     * @@roseuid 415BFC8A01D3
     */
    public WEB3AdminPvInfoConditionRegistInputResponse getConditionRegistInputScreen(WEB3AdminPvInfoConditionRegistInputRequest l_request)
    {
        final String STR_METHOD_NAME = " getConditionRegistInputScreen(WEB3AdminPvInfoConditionRegistInputRequest)";
        log.entering(STR_METHOD_NAME);
        
        WEB3AdminPvInfoConditionRegistInputResponse l_response = null;
        WEB3AdminPvInfoConditionRegistService l_service = null;
        
        //管理者表示設定登録サービスを取得し
        try
        {
            l_service = (WEB3AdminPvInfoConditionRegistService)Services.getService(
                WEB3AdminPvInfoConditionRegistService.class);
        }
        catch (Exception l_ex)
        {
            l_response = (WEB3AdminPvInfoConditionRegistInputResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "管理者表示設定登録サービスの取得に失敗しました。",
                l_response.errorInfo, l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;    
        }
        
        //管理者表示設定登録サービス.execute()メソッドをコールする。
        try
        {
            l_response = (WEB3AdminPvInfoConditionRegistInputResponse)l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response =
                (WEB3AdminPvInfoConditionRegistInputResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request, "管理者表示設定登録に失敗しました。", l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        
        log.exiting(STR_METHOD_NAME);

        //レスポンスオブジェクトを返却する。
        return l_response;        
    }

    /**
     * (confirm表示設定登録)<BR>
     * 表示設定登録確認処理を行う。<BR>
     * <BR>
     * 管理者表示設定登録サービスImplを取得し、<BR>
     * execute()メソッドをコールする。<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * 管理者・表示設定登録確認リクエストオブジェクト<BR>
     * @@return webbroker3.pvinfo.message.WEB3AdminPvInfoConditionRegistConfirmResponse
     * @@roseuid 415BF93A0058
     */
    public WEB3AdminPvInfoConditionRegistConfirmResponse confirmConditionRegist(WEB3AdminPvInfoConditionRegistConfirmRequest l_request)
    {
        final String STR_METHOD_NAME = " confirmConditionRegist(WEB3AdminPvInfoConditionRegistConfirmRequest)";
        log.entering(STR_METHOD_NAME);
        
        WEB3AdminPvInfoConditionRegistConfirmResponse l_response = null;
        WEB3AdminPvInfoConditionRegistService l_service = null;
        
        //管理者表示設定登録サービスを取得し
        try
        {
            l_service = (WEB3AdminPvInfoConditionRegistService)Services.getService(
                WEB3AdminPvInfoConditionRegistService.class);
        }
        catch (Exception l_ex)
        {
            l_response = (WEB3AdminPvInfoConditionRegistConfirmResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "管理者表示設定登録サービスの取得に失敗しました。",
                l_response.errorInfo, l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;    
        }
        
        //管理者表示設定登録サービス.execute()メソッドをコールする。
        try
        {
            l_response = (WEB3AdminPvInfoConditionRegistConfirmResponse)l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response =
                (WEB3AdminPvInfoConditionRegistConfirmResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request, "管理者表示設定登録に失敗しました。", l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        
        log.exiting(STR_METHOD_NAME);

        //レスポンスオブジェクトを返却する。
        return l_response;
    }

    /**
     * (complete表示設定登録)<BR>
     * 表示設定登録完了処理を行う。<BR>
     * <BR>
     * 管理者表示設定登録サービスImplを取得し、<BR>
     * execute()メソッドをコールする。<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * 管理者・表示設定登録完了リクエストオブジェクト<BR>
     * @@return webbroker3.pvinfo.message.WEB3AdminPvInfoConditionRegistCompleteResponse
     * @@roseuid 415BF9DF03B1
     */
    public WEB3AdminPvInfoConditionRegistCompleteResponse completeConditionRegist(WEB3AdminPvInfoConditionRegistCompleteRequest l_request)
    {
        final String STR_METHOD_NAME = " completeConditionRegist(WEB3AdminPvInfoConditionRegistCompleteRequest)";
        log.entering(STR_METHOD_NAME);
        
        WEB3AdminPvInfoConditionRegistCompleteResponse l_response = null;
        WEB3AdminPvInfoConditionRegistService l_service = null;
        
        //管理者表示設定登録サービスを取得し
        try
        {
            l_service = (WEB3AdminPvInfoConditionRegistService)Services.getService(
                WEB3AdminPvInfoConditionRegistService.class);
        }
        catch (Exception l_ex)
        {
            l_response = (WEB3AdminPvInfoConditionRegistCompleteResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "管理者表示設定登録サービスの取得に失敗しました。",
                l_response.errorInfo, l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;    
        }
        
        //管理者表示設定登録サービス.execute()メソッドをコールする。
        try
        {
            l_response = (WEB3AdminPvInfoConditionRegistCompleteResponse)l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response =
                (WEB3AdminPvInfoConditionRegistCompleteResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request, "管理者表示設定登録に失敗しました。", l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        
        log.exiting(STR_METHOD_NAME);

        //レスポンスオブジェクトを返却する。
        return l_response;
    }
}
@
