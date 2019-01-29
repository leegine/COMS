head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.11;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3EquityBookValuePriceRegistHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 現物株式簿価単価登録ハンドラ(WEB3EquityBookValuePriceRegistHandler.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/02/14 沢村　@仁士(SRA) 新規作成
*/

package webbroker3.equity.handler;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.equity.message.WEB3EquityBookPriceInputRequest;
import webbroker3.equity.message.WEB3EquityBookPriceInputResponse;
import webbroker3.equity.message.WEB3EquityBookPriceRegistRequest;
import webbroker3.equity.message.WEB3EquityBookPriceRegistResponse;
import webbroker3.equity.service.delegate.WEB3EquityBookValuePriceRegistService;
import webbroker3.util.WEB3LogUtility;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;


/**
 * （現物株式簿価単価登録ハンドラ）。<BR>
 * <BR>
 * 現物株式簿価単価登録ハンドラクラス<BR>
 */
public class WEB3EquityBookValuePriceRegistHandler implements MessageHandler 
{
    /**
     * ログ出力ユーティリティ。
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3EquityBookValuePriceRegistHandler.class);
         
   /**
     * @@roseuid 4206EF8F0079
     */
    public WEB3EquityBookValuePriceRegistHandler() 
    {
    
    }
   
    /**
     * 現物株式簿価単価登録入力画面表示処理を行う。<BR>
     * <BR>
     * 現物株式簿価単価登録サービスImplを取得し、<BR>
     * execute()メソッドをコールする。<BR>
     * @@param l_request - (リクエストデータ) 現物株式簿価単価登録入力リクエストオブジェクト<BR>
     * @@return WEB3EquityBookPriceInputResponse<BR>
     * @@roseuid 41B9245A01F1<BR>
     */
    public WEB3EquityBookPriceInputResponse getInputScreen(WEB3EquityBookPriceInputRequest l_request) 
    {
       final String STR_METHOD_NAME = "getInputScreen(WEB3EquityBookPriceInputRequest)";
       log.entering(STR_METHOD_NAME);

       WEB3EquityBookValuePriceRegistService l_service = null;
       WEB3EquityBookPriceInputResponse l_response = null;

       try
       {
           //現物株式簿価単価登録サービスを取得
           l_service =
               (WEB3EquityBookValuePriceRegistService) Services.getService(
                   WEB3EquityBookValuePriceRegistService.class);
       }
       catch (Exception e)
       {
           l_response =
               (WEB3EquityBookPriceInputResponse) l_request.createResponse();
           l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
           log.error(
               l_request,
               "現物株式簿価単価登録サービスの取得に失敗しました。",
               l_response.errorInfo,
               e);
           log.exiting(STR_METHOD_NAME);
           return l_response;
       }
                
       try
       {        
           //execute()メソッドをコールする。
           l_response =
               (WEB3EquityBookPriceInputResponse) l_service.execute(l_request);
       }
       catch (WEB3BaseException e)
       {
           l_response =
               (WEB3EquityBookPriceInputResponse) l_request.createResponse();
           l_response.errorInfo = e.getErrorInfo();
           log.error(
               l_request, 
               "現物株式簿価単価登録サービス.get入力画面()メソッド実行中にエラーが発生しました。", 
               e);
           log.exiting(STR_METHOD_NAME);
           return l_response;
       }
            
       log.exiting(STR_METHOD_NAME);

       // レスポンスオブジェクトを返却する。
       return l_response;
    }
   
    /**
     * 現物株式簿価単価登録処理を行う。<BR>
     * <BR>
     * 現物株式簿価単価登録サービスImplを取得し、<BR>
     * execute()メソッドをコールする。<BR>
     * @@param リクエストデータ - 現物株式簿価単価登録リクエストオブジェクト<BR>
     * @@return <BR>
     * webbroker3.equity.株式サービスモデル.メッセージ（株式）.現物株式簿価単価登録レス
     * ポンス
     * @@roseuid 41B924A900E7
     */
    public WEB3EquityBookPriceRegistResponse completeBookValuePrice(WEB3EquityBookPriceRegistRequest l_request) 
    {
        final String STR_METHOD_NAME = "completeBookValuePrice(WEB3EquityBookPriceRegistRequest)";
        log.entering(STR_METHOD_NAME);

        WEB3EquityBookValuePriceRegistService l_service = null;
        WEB3EquityBookPriceRegistResponse l_response = null;

        try
        {
            //現物株式簿価単価登録サービスを取得
            l_service =
                (WEB3EquityBookValuePriceRegistService) Services.getService(
                    WEB3EquityBookValuePriceRegistService.class);
        }
        catch (Exception e)
        {
            l_response =
                (WEB3EquityBookPriceRegistResponse) l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "現物株式簿価単価登録サービスの取得に失敗しました。",
                l_response.errorInfo,
                e);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
                
        try
        {        
            //execute()メソッドをコールする。
            l_response =
                (WEB3EquityBookPriceRegistResponse) l_service.execute(l_request);
        }
        catch (WEB3BaseException e)
        {
            l_response =
                (WEB3EquityBookPriceRegistResponse) l_request.createResponse();
            l_response.errorInfo = e.getErrorInfo();
            log.error(
                l_request, 
                "現物株式簿価単価登録サービス.submit簿価単価()メソッド実行中にエラーが発生しました。", 
                e);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
            
        log.exiting(STR_METHOD_NAME);

        // レスポンスオブジェクトを返却する。
        return l_response;
    }
}
@
