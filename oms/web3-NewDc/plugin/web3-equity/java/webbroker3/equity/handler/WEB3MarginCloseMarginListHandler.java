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
filename	WEB3MarginCloseMarginListHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 信用取引決済一覧ハンドラ(WEB3MarginCloseMarginListHandler.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/09/30 呉艶飛 (中訊) 新規作成
                   2005/01/06 岡村和明(SRA) JavaDoc修正
*/
package webbroker3.equity.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.equity.message.WEB3MarginCloseMarginListRequest;
import webbroker3.equity.message.WEB3MarginCloseMarginListResponse;
import webbroker3.equity.message.WEB3MarginIndividualCloseMarginListRequest;
import webbroker3.equity.message.WEB3MarginIndividualCloseMarginListResponse;
import webbroker3.equity.service.delegate.WEB3MarginCloseMarginListService;
import webbroker3.util.WEB3LogUtility;

/**
 * （信用取引決済一覧ハンドラ）。<BR>
 * <BR>
 * 信用取引決済一覧ハンドラクラス
 * @@author 呉艶飛(中訊)
 * @@version 1.0
 */
public class WEB3MarginCloseMarginListHandler implements MessageHandler 
{
    /**
     * ログ出力ユーティリティ。
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3MarginCloseMarginListHandler.class);
    
    /**
     * (get決済一覧)<BR>
     * 信用取引決済一覧表示処理を行う。<BR>
     * <BR>
     * 信用取引決済一覧サービスを取得し、execute()メソッドをコールする。<BR>
     * @@param l_request - リクエストデータ
     * @@return WEB3MarginCloseMarginListResponse
     * @@roseuid 40E400EF003C
     */
    public WEB3MarginCloseMarginListResponse getCloseMarginList(WEB3MarginCloseMarginListRequest l_request) 
    {
        final String STR_METHOD_NAME = "getCloseMarginList(WEB3MarginCloseMarginListRequest)";
        log.entering(STR_METHOD_NAME);

        WEB3MarginCloseMarginListResponse l_response = null;
        WEB3MarginCloseMarginListService l_service = null;

        try
        {
            l_service =
                 (WEB3MarginCloseMarginListService)Services.getService(
            WEB3MarginCloseMarginListService.class);
        }
        // サービスで例外が発生した場合は、エラー情報をレスポンスメッセージに設定する
        catch (Exception l_ex)
        {
            l_response =
                (WEB3MarginCloseMarginListResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "信用取引決済一覧サービスの取得に失敗しました。",
                l_response.errorInfo,
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

        try
        {
            l_response =
                (WEB3MarginCloseMarginListResponse)l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response =
                (WEB3MarginCloseMarginListResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request, "信用取引決済一覧表示処理に失敗しました。", l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        catch (WEB3BaseRuntimeException l_bre)
        {
            l_response =
                (WEB3MarginCloseMarginListResponse)l_request.createResponse();
            l_response.errorInfo = l_bre.getErrorInfo();
            log.error(l_request, "信用取引決済一覧表示処理に失敗しました。", l_bre);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

        log.exiting(STR_METHOD_NAME);
        return l_response;

    }
    
    /**
     * (get個別決済一覧)<BR>
     * 信用取引個別決済一覧表示処理を行う。<BR>
     * <BR>
     * 信用取引決済一覧サービスを取得し、execute()メソッドをコールする。<BR>
     * @@param l_request - リクエストデータ
     * @@return webbroker3.margin.message.WEB3MarginIndividualCloseMarginListResponse
     * @@roseuid 40FF204F0134
     */
    public WEB3MarginIndividualCloseMarginListResponse getIndividualCloseMarginList(WEB3MarginIndividualCloseMarginListRequest l_request) 
    {
        final String STR_METHOD_NAME = "getIndividualCloseMarginList(WEB3MarginIndividualCloseMarginListRequest)";
        log.entering(STR_METHOD_NAME);

        WEB3MarginIndividualCloseMarginListResponse l_response = null;
        WEB3MarginCloseMarginListService l_service = null;

        try
        {
            l_service =
                 (WEB3MarginCloseMarginListService)Services.getService(
            WEB3MarginCloseMarginListService.class);
        }
        // サービスで例外が発生した場合は、エラー情報をレスポンスメッセージに設定する
        catch (Exception l_ex)
        {
            l_response =
                (WEB3MarginIndividualCloseMarginListResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "信用取引決済一覧サービスの取得に失敗しました。",
                l_response.errorInfo,
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

        try
        {
            l_response =
                (WEB3MarginIndividualCloseMarginListResponse)l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response =
                (WEB3MarginIndividualCloseMarginListResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request, "信用取引個別決済一覧表示に失敗しました。", l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        catch (WEB3BaseRuntimeException l_bre)
        {
            l_response =
                (WEB3MarginIndividualCloseMarginListResponse)l_request.createResponse();
            l_response.errorInfo = l_bre.getErrorInfo();
            log.error(l_request, "信用取引個別決済一覧表示に失敗しました。", l_bre);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
}
@
