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
filename	WEB3MstkBalanceReferenceHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 株式ミニ投資残高照会ハンドラ(WEB3EquityBalanceReferenceHandler.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/02/07 沢村　@仁士(SRA) 新規作成
*/

package webbroker3.equity.handler;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.equity.message.WEB3MstkBalanceReferenceRequest;
import webbroker3.equity.message.WEB3MstkBalanceReferenceResponse;
import webbroker3.equity.message.WEB3MstkBalanceReferenceTotalRequest;
import webbroker3.equity.message.WEB3MstkBalanceReferenceTotalResponse;
import webbroker3.equity.service.delegate.WEB3MstkBalanceReferenceService;
import webbroker3.util.WEB3LogUtility;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;


/**
 * （株式ミニ投資残高照会ハンドラ）。<BR>
 * <BR>
 * 株式ミニ投資残高照会ハンドラクラス<BR>
 */
public class WEB3MstkBalanceReferenceHandler implements MessageHandler 
{
    
    /**
     * ログ出力ユーティリティ。
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3MstkBalanceReferenceHandler.class);
        
    /**
     * @@roseuid 4206CCB302EA<BR>
     */
    public WEB3MstkBalanceReferenceHandler() 
    {
     
    }
    
    /**
     * (get残高照会)<BR>
     * <BR>
     * 株式ミニ投資残高照会処理を行う。<BR>
     * <BR>
     * 株式ミニ投資残高照会サービスImplを取得し、<BR>
     * execute()メソッドをコールする。<BR>
     * @@param l_request - (リクエストデータ) 株式ミニ投資残高照会リクエストオブジェクト<BR>
     * @@return WEB3MstkBalanceReferenceResponse<BR>
     * @@roseuid 41C2D1730083<BR>
     */
    public WEB3MstkBalanceReferenceResponse getBalanceReference(WEB3MstkBalanceReferenceRequest l_request) 
    {
        final String STR_METHOD_NAME = "getBalanceReference(WEB3MstkBalanceReferenceRequest)";
        log.entering(STR_METHOD_NAME);

        WEB3MstkBalanceReferenceService l_service = null;
        WEB3MstkBalanceReferenceResponse l_response = null;

        try
        {
            //株式ミニ投資残高照会サービスを取得
            l_service =
                (WEB3MstkBalanceReferenceService) Services.getService(
                    WEB3MstkBalanceReferenceService.class);
        }
        catch (Exception e)
        {
            l_response =
                (WEB3MstkBalanceReferenceResponse) l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "株式ミニ投資残高照会サービスの取得に失敗しました。",
                l_response.errorInfo,
                e);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
                
        try
        {        
            //execute()メソッドをコールする。
            l_response =
                (WEB3MstkBalanceReferenceResponse) l_service.execute(l_request);
        }
        catch (WEB3BaseException e)
        {
            l_response =
                (WEB3MstkBalanceReferenceResponse) l_request.createResponse();
            l_response.errorInfo = e.getErrorInfo();
            log.error(
                l_request, 
                "株式ミニ投資残高照会サービス.get残高照会()メソッド実行中にエラーが発生しました。", 
                e);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
            
        log.exiting(STR_METHOD_NAME);

        // レスポンスオブジェクトを返却する。
        return l_response;
    }
    
    /**
     * (get残高合計)<BR>
     * <BR>
     * 株式ミニ投資残高合計処理を行う。<BR>
     * <BR>
     * 株式ミニ投資残高照会サービスImplを取得し、<BR>
     * execute()メソッドをコールする。<BR>
     * @@param l_request - (リクエストデータ) 株式ミニ投資残高照会残高合計リクエストオブジェクト<BR>
     * @@return WEB3MstkBalanceReferenceTotalResponse<BR>
     * @@roseuid 41C2D1CE0381<BR>
     */
    public WEB3MstkBalanceReferenceTotalResponse getBalanceTotal(WEB3MstkBalanceReferenceTotalRequest l_request) 
    {
        final String STR_METHOD_NAME = "getBalanceReference(WEB3MstkBalanceReferenceRequest)";
        log.entering(STR_METHOD_NAME);

        WEB3MstkBalanceReferenceService l_service = null;
        WEB3MstkBalanceReferenceTotalResponse l_response = null;

        try
        {
            //株式ミニ投資残高照会サービスを取得
            l_service =
                (WEB3MstkBalanceReferenceService) Services.getService(
                    WEB3MstkBalanceReferenceService.class);
        }
        catch (Exception e)
        {
            l_response =
                (WEB3MstkBalanceReferenceTotalResponse) l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "株式ミニ投資残高照会サービスの取得に失敗しました。",
                l_response.errorInfo,
                e);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
                
        try
        {        
            //execute()メソッドをコールする。
            l_response =
                (WEB3MstkBalanceReferenceTotalResponse) l_service.execute(l_request);
        }
        catch (WEB3BaseException e)
        {
            l_response =
                (WEB3MstkBalanceReferenceTotalResponse) l_request.createResponse();
            l_response.errorInfo = e.getErrorInfo();
            log.error(
                l_request, 
                "株式ミニ投資残高照会サービス.get残高合計()メソッド実行中にエラーが発生しました。", 
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
