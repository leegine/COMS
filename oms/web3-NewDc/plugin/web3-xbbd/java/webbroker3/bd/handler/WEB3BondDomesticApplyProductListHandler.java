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
filename	WEB3BondDomesticApplyProductListHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 国内債券応募銘柄一覧ハンドラ(WEB3BondDomesticApplyProductListHandler.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/07/23 武波 (中訊) 新規作成 仕様変更・モデルNo.224
*/
package webbroker3.bd.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.bd.message.WEB3BondDomesticApplyProductListRequest;
import webbroker3.bd.message.WEB3BondDomesticApplyProductListResponse;
import webbroker3.bd.service.delegate.WEB3BondDomesticApplyProductListService;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.util.WEB3LogUtility;

/**
 * (国内債券応募銘柄一覧ハンドラ)<BR>
 * 国内債券応募銘柄一覧ハンドラ<BR>
 * <BR>
 * @@author 武波
 * @@version 1.0
 */
public class WEB3BondDomesticApplyProductListHandler implements MessageHandler
{

    /**
     * ログユーティリティ<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3BondDomesticApplyProductListHandler.class);

    /**
     * @@roseuid 46A473FD0196
     */
    public WEB3BondDomesticApplyProductListHandler()
    {

    }

    /**
     * (国内債券応募銘柄一覧)<BR>
     * 国内債券応募銘柄一覧処理を行う。<BR>
     * <BR>
     * 国内債券応募銘柄一覧サービスを取得し、execute()メソッドをコールする。<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * リクエストデータ<BR>
     * @@return WEB3BondDomesticApplyProductListResponse
     * @@roseuid 466CCD150226
     */
    public WEB3BondDomesticApplyProductListResponse bondDomesticApplyProductList(
        WEB3BondDomesticApplyProductListRequest l_request)
    {
        final String STR_METHOD_NAME = " bondDomesticApplyProductList(WEB3BondDomesticApplyProductListRequest)";
        log.entering(STR_METHOD_NAME);

        WEB3BondDomesticApplyProductListResponse l_response = null;
        WEB3BondDomesticApplyProductListService l_service = null;
        try
        {
            //国内債券応募銘柄一覧サービスを取得
            l_service =
                (WEB3BondDomesticApplyProductListService)Services.getService(
                    WEB3BondDomesticApplyProductListService.class);
        }
        catch (Exception l_ex)
        {
            l_response =
                (WEB3BondDomesticApplyProductListResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "国内債券応募銘柄一覧サービスを取得に失敗しました。",
                l_response.errorInfo,
                l_ex);

            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        //execute()メソッドをコールする。
        try
        {
            l_response =
                (WEB3BondDomesticApplyProductListResponse)l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response =
                (WEB3BondDomesticApplyProductListResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request,
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
