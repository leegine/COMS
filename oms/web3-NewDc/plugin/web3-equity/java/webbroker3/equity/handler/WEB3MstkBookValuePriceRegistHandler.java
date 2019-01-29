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
filename	WEB3MstkBookValuePriceRegistHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        :株式ミニ投資簿価単価登録ハンドラ(WEB3MstkBookValuePriceRegistHandler.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/02/14 沢村　@仁士(SRA) 新規作成
*/

package webbroker3.equity.handler;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.equity.message.WEB3MstkBookPriceInputRequest;
import webbroker3.equity.message.WEB3MstkBookPriceInputResponse;
import webbroker3.equity.message.WEB3MstkBookPriceRegistRequest;
import webbroker3.equity.message.WEB3MstkBookPriceRegistResponse;
import webbroker3.equity.service.delegate.WEB3MstkBookValuePriceRegistService;
import webbroker3.util.WEB3LogUtility;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;


/**
 * （株式ミニ投資簿価単価登録ハンドラ）。<BR>
 * <BR>
 * 株式ミニ投資簿価単価登録ハンドラクラス<BR>
 */
public class WEB3MstkBookValuePriceRegistHandler implements MessageHandler 
{
    /**
     * ログ出力ユーティリティ。
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3MstkBookValuePriceRegistHandler.class);
         
    /**
     * @@roseuid 4206F07D027C<BR>
     */
    public WEB3MstkBookValuePriceRegistHandler() 
    {
     
    }
    
    /**
     * 株式ミニ投資簿価単価登録入力画面表示処理を行う。<BR>
     * <BR>
     * 株式ミニ投資簿価単価登録サービスImplを取得し、<BR>
     * execute()メソッドをコールする。<BR>
     * @@param l_request - (リクエストデータ) 株式ミニ投資簿価単価登録入力リクエストオブジェクト<BR>
     * @@return WEB3MstkBookPriceInputResponse<BR>
     * @@roseuid 41C66F930245<BR>
     */
    public WEB3MstkBookPriceInputResponse getInputScreen(WEB3MstkBookPriceInputRequest l_request) 
    {
        final String STR_METHOD_NAME = "getInputScreen(WEB3MstkBookPriceInputRequest)";
        log.entering(STR_METHOD_NAME);

        WEB3MstkBookValuePriceRegistService l_service = null;
        WEB3MstkBookPriceInputResponse l_response = null;

        try
        {
            //株式ミニ投資簿価単価登録サービスを取得
            l_service =
                (WEB3MstkBookValuePriceRegistService) Services.getService(
                    WEB3MstkBookValuePriceRegistService.class);
        }
        catch (Exception e)
        {
            l_response =
                (WEB3MstkBookPriceInputResponse) l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "株式ミニ投資簿価単価登録サービスの取得に失敗しました。",
                l_response.errorInfo,
                e);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
                
        try
        {        
            //execute()メソッドをコールする。
            l_response =
                (WEB3MstkBookPriceInputResponse) l_service.execute(l_request);
        }
        catch (WEB3BaseException e)
        {
            l_response =
                (WEB3MstkBookPriceInputResponse) l_request.createResponse();
            l_response.errorInfo = e.getErrorInfo();
            log.error(
                l_request, 
                "株式ミニ投資簿価単価登録サービス.get入力画面()メソッド実行中にエラーが発生しました。", 
                e);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
            
        log.exiting(STR_METHOD_NAME);

        // レスポンスオブジェクトを返却する。
        return l_response;
    }
    
    /**
     * 株式ミニ投資簿価単価登録処理を行う。<BR>
     * <BR>
     * 株式ミニ投資簿価単価登録サービスImplを取得し、<BR>
     * execute()メソッドをコールする。<BR>
     * @@param リクエストデータ - 株式ミニ投資簿価単価登録リクエストオブジェクト<BR>
     * @@return <BR>
     * webbroker3.equity.株式ミニ投資サービスモデル.メッセージ（ミニ株）.株式ミニ投資簿<BR>
     * 価単価登録レスポンス<BR>
     * @@roseuid 41C66F930264<BR>
     */
    public WEB3MstkBookPriceRegistResponse completeBookValuePrice(WEB3MstkBookPriceRegistRequest l_request) 
    {
        final String STR_METHOD_NAME = "completeBookValuePrice(WEB3MstkBookPriceRegistRequest)";
        log.entering(STR_METHOD_NAME);

        WEB3MstkBookValuePriceRegistService l_service = null;
        WEB3MstkBookPriceRegistResponse l_response = null;

        try
        {
            //株式ミニ投資簿価単価登録サービスを取得
            l_service =
                (WEB3MstkBookValuePriceRegistService) Services.getService(
                    WEB3MstkBookValuePriceRegistService.class);
        }
        catch (Exception e)
        {
            l_response =
                (WEB3MstkBookPriceRegistResponse) l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "株式ミニ投資簿価単価登録サービスの取得に失敗しました。",
                l_response.errorInfo,
                e);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
                
        try
        {        
            //execute()メソッドをコールする。
            l_response =
                (WEB3MstkBookPriceRegistResponse) l_service.execute(l_request);
        }
        catch (WEB3BaseException e)
        {
            l_response =
                (WEB3MstkBookPriceRegistResponse) l_request.createResponse();
            l_response.errorInfo = e.getErrorInfo();
            log.error(
                l_request, 
                "株式ミニ投資簿価単価登録サービス.submit簿価単価()メソッド実行中にエラーが発生しました。", 
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
