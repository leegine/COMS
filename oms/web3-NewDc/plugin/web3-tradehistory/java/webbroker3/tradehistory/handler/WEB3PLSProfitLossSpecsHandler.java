head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.04.24;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	WEB3PLSProfitLossSpecsHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 損益明細照会ハンドラ(WEB3PLSProfitLossSpecsHandler.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/11/10 範慧琴 (中訊) 新規作成
                 : 2006/10/19  趙林鵬 (中訊) モデル056
*/

package webbroker3.tradehistory.handler;

import com.fitechlabs.xtrade.kernel.handler.MessageHandler;
import com.fitechlabs.xtrade.kernel.boot.Services;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.tradehistory.message.WEB3PLSProfitLossDownloadRequest;
import webbroker3.tradehistory.message.WEB3PLSProfitLossDownloadResponse;
import webbroker3.tradehistory.message.WEB3PLSProfitLossSpecsRequest;
import webbroker3.tradehistory.message.WEB3PLSProfitLossSpecsResponse;
import webbroker3.tradehistory.service.delegate.WEB3PLSProfitLossSpecsService;
import webbroker3.util.WEB3LogUtility;

/**
 * (損益明細照会ハンドラ)<BR>
 * 損益明細照会ハンドラクラス<BR>
 *
 * @@author 範慧琴
 * @@version 1.0
 */
public class WEB3PLSProfitLossSpecsHandler implements MessageHandler
{
    /**
     *ログ出力ユーティリティ。
     */
    private WEB3LogUtility log = WEB3LogUtility.getInstance(WEB3PLSProfitLossSpecsHandler.class);

    /**
     * @@roseuid 418877BD0213
     */
    public WEB3PLSProfitLossSpecsHandler()
    {

    }

    /**
     * (get損益明細照会)<BR>
     * 損益明細照会処理を行う。<BR>
     * <BR>
     * 損益明細照会サービスImplを取得し、<BR>
     * execute()メソッドをコールする。<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * 損益明細照会リクエストオブジェクト<BR>
     * @@return webbroker3.plsbvs.message.WEB3PLSProfitLossSpecsResponse
     * @@roseuid 416CFCCE0333
     */
    public WEB3PLSProfitLossSpecsResponse getProfitLossSpecs(WEB3PLSProfitLossSpecsRequest l_request)
    {
        final String STR_METHOD_NAME = " getProfitLossSpecs(WEB3PLSProfitLossSpecsRequest l_request)";
        log.entering(STR_METHOD_NAME);

        WEB3PLSProfitLossSpecsResponse l_response = null;
        WEB3PLSProfitLossSpecsService l_service = null;

        //損益明細照会サービスImplを取得し
        try
        {
            l_service = (WEB3PLSProfitLossSpecsService)Services.getService(WEB3PLSProfitLossSpecsService.class);
        }
        catch (Exception l_ex)
        {
            l_response = (WEB3PLSProfitLossSpecsResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(l_request, "損益明細照会サービスImplの取得に失敗しました", l_response.errorInfo, l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

        try
        {
            //execute()メソッドをコールする
            l_response = (WEB3PLSProfitLossSpecsResponse)l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response =
                (WEB3PLSProfitLossSpecsResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request,
                "損益明細照会処理に失敗しました",
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

        log.exiting(STR_METHOD_NAME);
        return l_response;

    }

    /**
     * (get損益明細ファ@イルダウンロード)<BR>
     * 損益明細ファ@イルダウンロード処理を行う。<BR>
     * <BR>
     * 損益明細照会サービスImplを取得し、<BR>
     * execute()メソッドをコールする。<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * 損益明細ファ@イルダウンロードリクエスト<BR>
     * @@return WEB3PLSProfitLossDownloadResponse
     */
    public WEB3PLSProfitLossDownloadResponse getProfitLossDownload(
        WEB3PLSProfitLossDownloadRequest l_request)
    {
        final String STR_METHOD_NAME = "getProfitLossDownload(WEB3PLSProfitLossDownloadRequest l_request)";
        log.entering(STR_METHOD_NAME);

        WEB3PLSProfitLossDownloadResponse l_response = null;
        WEB3PLSProfitLossSpecsService l_service = null;

        //損益明細照会サービスImplを取得し
        try
        {
            l_service = (WEB3PLSProfitLossSpecsService)Services.getService(WEB3PLSProfitLossSpecsService.class);
        }
        catch (Exception l_ex)
        {
            l_response = (WEB3PLSProfitLossDownloadResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "損益明細照会サービスImplの取得に失敗しました",
                l_response.errorInfo,
                l_ex);

            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

        try
        {
            //execute()メソッドをコールする
            l_response = (WEB3PLSProfitLossDownloadResponse)l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response =
                (WEB3PLSProfitLossDownloadResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request,
                "execute()メソッドをコールすることが失敗しました",
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

}
@
