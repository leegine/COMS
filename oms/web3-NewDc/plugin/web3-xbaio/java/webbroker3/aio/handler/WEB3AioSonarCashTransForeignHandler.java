head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.25.10;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3AioSonarCashTransForeignHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : SONAR入出金（外貨）ハンドラ(WEB3AioSonarCashTransForeignHandler.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/08/28 徐宏偉 (中訊) 新規作成
*/
package webbroker3.aio.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.aio.message.WEB3AioSonarCashTransForeignRequest;
import webbroker3.aio.message.WEB3AioSonarCashTransForeignResponse;
import webbroker3.aio.service.delegate.WEB3AioSonarCashTransForeignService;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.util.WEB3LogUtility;

/**
 * (SONAR入出金（外貨）ハンドラ)<BR>
 * SONAR入出金（外貨）ハンドラクラス<BR>
 * <BR>
 * @@author 徐宏偉(中訊)
 * @@version 1.0
 */
public class WEB3AioSonarCashTransForeignHandler implements MessageHandler
{
    /**
     * ログ出力ユーティリティ。<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(
            WEB3AioSonarCashTransForeignHandler.class);
    
    /**
     * (SONAR入出金（外貨）リクエスト)<BR>
     * SONAR入出金（外貨）処理を行う。<BR> 
     * <BR>
     * SONAR入出金（外貨）サービスを取得し、<BR>
     * execute()メソッドをコールする。<BR>
     * <BR>
     * @@param l_request - (リクエストデータ)<BR>
     * リクエストデータ<BR>
     * @@return WEB3AioSonarCashTransForeignResponse
     */
    public WEB3AioSonarCashTransForeignResponse sonarCashTransForeignRequest
        (WEB3AioSonarCashTransForeignRequest l_request) 
    {
        final String STR_METHOD_NAME = 
            "sonarCashTransForeignRequest(WEB3AioSonarCashTransForeignRequest l_request)"; 
        log.entering(STR_METHOD_NAME);
        
        //SONAR入出金（外貨）サービスインターフェイス
        WEB3AioSonarCashTransForeignService l_service = null;          
         
        //SONAR入出金（外貨）レスポンス
        WEB3AioSonarCashTransForeignResponse l_response = null;     
         
        try
        {
            l_service = 
                (WEB3AioSonarCashTransForeignService)Services.getService(
                    WEB3AioSonarCashTransForeignService.class);
        }
        catch (Exception l_ex)
        {
            //サービスで例外が発生した場合は、
            //エラー情報をレスポンスメッセージに設定する
            l_response =
                (WEB3AioSonarCashTransForeignResponse)l_request.createResponse();
            l_response.errorInfo = 
                WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "SONAR入出金（外貨）サービスの取得に失敗しました。",
                l_response.errorInfo,
                l_ex);
            
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        try
        {
            l_response =
                (WEB3AioSonarCashTransForeignResponse)l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response =
                (WEB3AioSonarCashTransForeignResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request, 
                "SONAR入出金（外貨）処理に失敗しました。", 
                l_ex);
            
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
}
@
