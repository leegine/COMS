head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.02;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3MutualBuyHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 投資信託買付注文ハンドラクラス(WEB3MutualBuyHandler)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/08/10 周勇 (中訊) 新規作成
                   2004/08/23 黄建 (中訊) レビュー 
*/
package webbroker3.mf.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.mf.message.WEB3MutualBuyCompleteRequest;
import webbroker3.mf.message.WEB3MutualBuyCompleteResponse;
import webbroker3.mf.message.WEB3MutualBuyConfirmRequest;
import webbroker3.mf.message.WEB3MutualBuyConfirmResponse;
import webbroker3.mf.service.delegate.WEB3MutualBuyService;
import webbroker3.util.WEB3LogUtility;


/**
 * 投資信託買付注文ハンドラクラス<BR>
 * 
 * @@author 周勇(中訊)
 * @@version 1.0
 */
public class WEB3MutualBuyHandler implements MessageHandler
{
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3MutualBuyHandler.class);
    /**
     * (confirm買付注文)<BR>
     * 投資信託買付注文サービスを取得し、execute()<BR>
     * メソッドをコールする。<BR>
     * @@param l_request - リクエストデータ
     * @@return webbroker3.mf.message.WEB3MutualBuyConfirmResponse
     * @@roseuid 40555E0E00E9
     */
    public WEB3MutualBuyConfirmResponse confirmBuyOrder(WEB3MutualBuyConfirmRequest l_request)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "confirmBuyOrder(WEB3MutualBuyConfirmRequest l_request)";
        log.entering(STR_METHOD_NAME);
        if (l_request == null)
        {
            log.debug("the parametor method in null ");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        WEB3MutualBuyService l_service = null;
        WEB3MutualBuyConfirmResponse l_response = null;
        try
        {
            l_service =
                (WEB3MutualBuyService) Services.getService(
                     WEB3MutualBuyService.class);
        }
        catch (Exception l_ex)
        {
            l_response = (WEB3MutualBuyConfirmResponse) l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "投資信託買付注文サービスの取得に失敗しました。",
                l_response.errorInfo,
                l_ex);
            return l_response;
        }
        try
        {
            l_response =
                (WEB3MutualBuyConfirmResponse) l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response = (WEB3MutualBuyConfirmResponse) l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request, "投資信託買付注文の取得に失敗しました", l_ex.getErrorInfo(), l_ex);
            return l_response;
        }
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    /**
     * (complete買付注文)<BR>
     * 投資信託買付注文サービスを取得し、execute()<BR>
     * メソッドをコールする。<BR>
     * @@param l_request - リクエストデータ
     * @@return webbroker3.mf.message.WEB3MutualBuyCompleteResponse
     * @@roseuid 40555E4F03C7
     */
    public WEB3MutualBuyCompleteResponse completeBuyOrder(WEB3MutualBuyCompleteRequest l_request)
    {
        final String STR_METHOD_NAME =
            "completeBuyOrder(WEB3MutualBuyCompleteRequest l_request)";
        log.entering(STR_METHOD_NAME);
        if (l_request == null)
        {
            log.debug("the parametor method in null ");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }        
        WEB3MutualBuyService l_service = null;
        WEB3MutualBuyCompleteResponse l_response = null;
        try
        {
            l_service =
                (WEB3MutualBuyService) Services.getService(
                    WEB3MutualBuyService.class);
        }
        catch (Exception l_ex)
        {
            l_response =
                (WEB3MutualBuyCompleteResponse) l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "投資信託買付注文サービスの取得に失敗しました",
                l_response.errorInfo,
                l_ex);
            return l_response;
        }
        try
        {
            l_response =
                (WEB3MutualBuyCompleteResponse) l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response =
                (WEB3MutualBuyCompleteResponse) l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request, "投資信託買付注文に失敗しました。", l_ex);
            return l_response;
        }
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
}
@
