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
filename	WEB3BondDomesticApplyHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 国内債券応募ハンドラ(WEB3BondDomesticApplyHandler.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/07/23 武波 (中訊) 新規作成 仕様変更・モデルNo.226
*/
package webbroker3.bd.handler;

import webbroker3.bd.message.WEB3BondDomesticApplyCompleteRequest;
import webbroker3.bd.message.WEB3BondDomesticApplyCompleteResponse;
import webbroker3.bd.message.WEB3BondDomesticApplyConfirmRequest;
import webbroker3.bd.message.WEB3BondDomesticApplyConfirmResponse;
import webbroker3.bd.service.delegate.WEB3BondDomesticApplyService;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.util.WEB3LogUtility;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

/**
 * (国内債券応募ハンドラ)<BR>
 * 国内債券応募ハンドラ<BR>
 * <BR>
 * @@author 武波
 * @@version 1.0
 */
public class WEB3BondDomesticApplyHandler implements MessageHandler
{


    /**
     * ログユーティリティ<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3BondDomesticApplyHandler.class);

    /**
     * @@roseuid 46A473FD0138
     */
    public WEB3BondDomesticApplyHandler()
    {

    }

    /**
     * (国内債券応募確認)<BR>
     * 国内債券応募サービスを取得し、execute()メソッドをコールする。<BR>
     * @@param l_request - リクエストデータ<BR>
     * リクエストデータ<BR>
     * @@return WEB3BondDomesticApplyConfirmResponse
     * @@roseuid 466CD6A3033F
     */
    public WEB3BondDomesticApplyConfirmResponse bondDomesticApplyConfirm(
        WEB3BondDomesticApplyConfirmRequest l_request)
    {
        final String STR_METHOD_NAME = " bondDomesticApplyConfirm(WEB3BondDomesticApplyConfirmRequest)";
        log.entering(STR_METHOD_NAME);

        WEB3BondDomesticApplyConfirmResponse l_response = null;
        WEB3BondDomesticApplyService l_service = null;

        try
        {
            l_service =
                (WEB3BondDomesticApplyService)Services.getService(
                    WEB3BondDomesticApplyService.class);
        }
        catch (Exception l_ex)
        {
            l_response =
                (WEB3BondDomesticApplyConfirmResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "国内債券応募サービスを取得に失敗しました。",
                l_response.errorInfo,
                l_ex);

            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

        //execute()メソッドをコールする。
        try
        {
            l_response =
                (WEB3BondDomesticApplyConfirmResponse)l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response =
                (WEB3BondDomesticApplyConfirmResponse)l_request.createResponse();
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
     * (国内債券応募完了)<BR>
     * 国内債券応募サービスを取得し、execute()メソッドをコールする。<BR>
     * @@param l_request - リクエストデータ
     * @@return WEB3BondDomesticApplyCompleteResponse
     * @@roseuid 466CD6BA016A
     */
    public WEB3BondDomesticApplyCompleteResponse bondDomesticApplyComplete(
        WEB3BondDomesticApplyCompleteRequest l_request)
    {
        final String STR_METHOD_NAME = " bondDomesticApplyComplete(WEB3BondDomesticApplyCompleteRequest)";
        log.entering(STR_METHOD_NAME);

        WEB3BondDomesticApplyCompleteResponse l_response = null;
        WEB3BondDomesticApplyService l_service = null;

        try
        {
            l_service =
                (WEB3BondDomesticApplyService)Services.getService(
                    WEB3BondDomesticApplyService.class);
        }
        catch (Exception l_ex)
        {
            l_response =
                (WEB3BondDomesticApplyCompleteResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "国内債券応募サービスを取得に失敗しました。",
                l_response.errorInfo,
                l_ex);

            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

        //execute()メソッドをコールする。
        try
        {
            l_response =
                (WEB3BondDomesticApplyCompleteResponse)l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response =
                (WEB3BondDomesticApplyCompleteResponse)l_request.createResponse();
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
