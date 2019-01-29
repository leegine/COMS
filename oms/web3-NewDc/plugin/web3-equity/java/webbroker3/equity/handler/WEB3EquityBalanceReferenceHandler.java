head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.10;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3EquityBalanceReferenceHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 現物株式残高照会ハンドラ(WEB3EquityBalanceReferenceHandler.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/02/07 沢村　@仁士(SRA) 新規作成
*/

package webbroker3.equity.handler;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.equity.message.WEB3EquityBalanceReferenceRequest;
import webbroker3.equity.message.WEB3EquityBalanceReferenceResponse;
import webbroker3.equity.message.WEB3EquityBalanceReferenceTotalRequest;
import webbroker3.equity.message.WEB3EquityBalanceReferenceTotalResponse;
import webbroker3.equity.service.delegate.WEB3EquityBalanceReferenceService;
import webbroker3.util.WEB3LogUtility;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;


/**
 * （現物株式残高照会ハンドラ）。<BR>
 * <BR>
 * 現物株式残高照会ハンドラクラス<BR>
 */
public class WEB3EquityBalanceReferenceHandler implements MessageHandler 
{
    /**
     * ログ出力ユーティリティ。
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3EquityBalanceReferenceHandler.class);
        
    /**
     * @@roseuid 4206CC4A001B<BR>
     */
    public WEB3EquityBalanceReferenceHandler() 
    {
     
    }
    
    /**
     * (get残高照会)<BR>
     * <BR>
     * 現物株式残高照会処理を行う。<BR>
     * <BR>
     * 現物株式残高照会サービスImplを取得し、<BR>
     * execute()メソッドをコールする。<BR>
     * @@param l_request - (リクエストデータ) 現物株式残高照会リクエストオブジェクト<BR>
     * @@return WEB3EquityBalanceReferenceResponse<BR>
     * ス<BR>
     * @@roseuid 41B58FEF002C<BR>
     */
    public WEB3EquityBalanceReferenceResponse getBalanceReference(WEB3EquityBalanceReferenceRequest l_request) 
    {
        final String STR_METHOD_NAME = "getBalanceReference(WEB3EquityBalanceReferenceRequest)";
        log.entering(STR_METHOD_NAME);

        WEB3EquityBalanceReferenceService l_service = null;
        WEB3EquityBalanceReferenceResponse l_response = null;

        try
        {
            //現物株式残高照会サービスを取得
            l_service =
                (WEB3EquityBalanceReferenceService) Services.getService(
                    WEB3EquityBalanceReferenceService.class);
        }
        catch (Exception e)
        {
            l_response =
                (WEB3EquityBalanceReferenceResponse) l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "現物株式残高照会サービスの取得に失敗しました。", l_response.errorInfo, e);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
                
        try
        {        
            //execute()メソッドをコールする。
            l_response =
                (WEB3EquityBalanceReferenceResponse) l_service.execute(l_request);
        }
        catch (WEB3BaseException e)
        {
            l_response =
                (WEB3EquityBalanceReferenceResponse) l_request.createResponse();
            l_response.errorInfo = e.getErrorInfo();
            log.error(l_request, "現物株式残高照会に失敗しました。", e);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        catch (WEB3BaseRuntimeException l_bre)
        {
            l_response =
                (WEB3EquityBalanceReferenceResponse) l_request.createResponse();
            l_response.errorInfo = l_bre.getErrorInfo();
            log.error(l_request, "現物株式残高照会に失敗しました。", l_bre);
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
     * 現物株式残高合計処理を行う。<BR>
     * <BR>
     * 現物株式残高照会サービスImplを取得し、<BR>
     * execute()メソッドをコールする。<BR>
     * @@param l_request - (リクエストデータ) 現物株式残高照会残高合計リクエストオブジェクト<BR>
     * @@return WEB3EquityBalanceReferenceTotalResponse<BR>
     * @@roseuid 41B59034027D<BR>
     */
    public WEB3EquityBalanceReferenceTotalResponse getBalanceTotal(WEB3EquityBalanceReferenceTotalRequest l_request) 
    {
        final String STR_METHOD_NAME = "getBalanceTotal(WEB3EquityBalanceReferenceTotalRequest)";
        log.entering(STR_METHOD_NAME);

        WEB3EquityBalanceReferenceService l_service = null;
        WEB3EquityBalanceReferenceTotalResponse l_response = null;

        try
        {
            //現物株式残高照会サービスを取得
            l_service =
                (WEB3EquityBalanceReferenceService) Services.getService(
                    WEB3EquityBalanceReferenceService.class);
        }
        catch (Exception e)
        {
            l_response =
                (WEB3EquityBalanceReferenceTotalResponse) l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request, "現物株式残高照会サービスの取得に失敗しました。", l_response.errorInfo, e);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
                
        try
        {        
            //execute()メソッドをコールする。
            l_response =
                (WEB3EquityBalanceReferenceTotalResponse) l_service.execute(l_request);
        }
        catch (WEB3BaseException e)
        {
            l_response =
                (WEB3EquityBalanceReferenceTotalResponse) l_request.createResponse();
            l_response.errorInfo = e.getErrorInfo();
            log.error(l_request, "現物株式残高合計に失敗しました。", e);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        catch (WEB3BaseRuntimeException l_bre)
        {
            l_response =
                (WEB3EquityBalanceReferenceTotalResponse) l_request.createResponse();
            l_response.errorInfo = l_bre.getErrorInfo();
            log.error(l_request, "現物株式残高合計に失敗しました。", l_bre);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
            
        log.exiting(STR_METHOD_NAME);

        // レスポンスオブジェクトを返却する。
        return l_response;
    }
}
@
