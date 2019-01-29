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
filename	WEB3BondBalanceReferenceHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 債券残高照会ハンドラ (WEB3BondBalanceReferenceHandler.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/09/21 趙林鵬 (中訊) 新規作成
Revesion History : 2007/07/17 謝旋 (中訊) 仕様変更・モデル208
*/

package webbroker3.bd.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.bd.message.WEB3BondBalanceReferenceRequest;
import webbroker3.bd.message.WEB3BondBalanceReferenceResponse;
import webbroker3.bd.message.WEB3BondBalanceReferenceTotalRequest;
import webbroker3.bd.message.WEB3BondBalanceReferenceTotalResponse;
import webbroker3.bd.service.delegate.WEB3BondBalanceReferenceService;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.util.WEB3LogUtility;

/**
 * (債券残高照会ハンドラ)<BR>
 * 債券残高照会ハンドラクラス
 * 
 * @@author 趙林鵬
 * @@version 1.0
 */

public class WEB3BondBalanceReferenceHandler implements MessageHandler
{
    /**
     * ログ出力ユーティリティ。<BR>
     */
    private static WEB3LogUtility log = 
        WEB3LogUtility.getInstance(WEB3BondBalanceReferenceHandler.class);
    
    /**
     * @@roseuid 44E3362F0399
     */
    public WEB3BondBalanceReferenceHandler() 
    {
     
    }
    
    /**
     * (get残高照会)<BR>
     * 債券残高照会処理を実施する。 <BR>
     * <BR>
     * 債券残高照会サービスを取得し、 <BR>
     * execute()メソッドをコールする。<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * 債券残高照会リクエストオブジェクト<BR>
     * @@return WEB3BondBalanceReferenceResponse
     * @@roseuid 44B61AB403B8
     */
    public WEB3BondBalanceReferenceResponse getBalanceReference(WEB3BondBalanceReferenceRequest l_request) 
    {
        final String STR_METHOD_NAME = "getBalanceReference(WEB3BondBalanceReferenceRequest)";
        log.entering(STR_METHOD_NAME);
        
        WEB3BondBalanceReferenceResponse l_response = null;
        WEB3BondBalanceReferenceService l_service = null;
        try
        {
            l_service =
                (WEB3BondBalanceReferenceService)Services.getService(
                    WEB3BondBalanceReferenceService.class);
        }
        catch (Exception l_ex)
        {
            l_response =
                (WEB3BondBalanceReferenceResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "債券残高照会サービスを取得に失敗しました。",
                l_response.errorInfo,
                l_ex);

            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        //execute()メソッドをコールする。
        try
        {
            l_response =
                (WEB3BondBalanceReferenceResponse)l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response =
                (WEB3BondBalanceReferenceResponse)l_request.createResponse();
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
     * (get残高合計)<BR>
     * 債券残高照会残高合計処理を実施する。<BR>
     * <BR>
     * 債券残高照会サービスを取得し、<BR>
     * execute()メソッドをコールする。<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * 債券残高照会残高合計リクエスト<BR>
     * @@return WEB3BondBalanceReferenceTotalResponse
     * @@roseuid 44B61AB403B8
     */
    public WEB3BondBalanceReferenceTotalResponse getBalanceTotal(WEB3BondBalanceReferenceTotalRequest l_request)
    {
        final String STR_METHOD_NAME = " getBalanceTotal(WEB3BondBalanceReferenceTotalRequest)";
        log.entering(STR_METHOD_NAME);

        WEB3BondBalanceReferenceTotalResponse l_response = null;
        WEB3BondBalanceReferenceService l_service = null;
        try
        {
            l_service =
                (WEB3BondBalanceReferenceService)Services.getService(
                    WEB3BondBalanceReferenceService.class);
        }
        catch (Exception l_ex)
        {
            l_response =
                (WEB3BondBalanceReferenceTotalResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "債券残高照会サービスを取得に失敗しました。",
                l_response.errorInfo,
                l_ex);

            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        //execute()メソッドをコールする。
        try
        {
            l_response =
                (WEB3BondBalanceReferenceTotalResponse)l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response =
                (WEB3BondBalanceReferenceTotalResponse)l_request.createResponse();
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
