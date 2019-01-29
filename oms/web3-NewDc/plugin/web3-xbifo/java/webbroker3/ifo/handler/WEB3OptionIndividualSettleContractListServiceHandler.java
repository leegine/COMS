head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.13;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3OptionIndividualSettleContractListServiceHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : OP個別返済一覧表示サービスハンドラ(WEB3OptionIndividualSettleContractListServiceHandler.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/06/24 張宝楠 (中訊) 新規作成
*/

package webbroker3.ifo.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.ifo.service.delegate.WEB3OptionIndividualSettleContractListService;
import webbroker3.ifo.message.WEB3OptionsIndividualCloseMarginListResponse;
import webbroker3.ifo.message.WEB3OptionsIndividualCloseMarginListRequest;
import webbroker3.util.WEB3LogUtility;

/**
 * (OP個別返済一覧表示サービスハンドラ)<BR>
 * 株価指数オプション個別返済一覧表示サービスハンドラクラス<BR>
 * @@author 張宝楠(中訊)
 * @@version 1.0
 */
public class WEB3OptionIndividualSettleContractListServiceHandler implements MessageHandler
{
    /**
     * ログ出力ユーティリティ。
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3OptionIndividualSettleContractListServiceHandler.class);

    /**
     * (get個別返済一覧)<BR>
     * <BR>
     * 株価指数オプション個別返済一覧表示処理を行う。<BR>
     * <BR>
     * OP個別返済一覧画面表示サービスを取得し、execute()メソッドをコールする。<BR>
     * @@param l_request - リクエストデータ
     * @@return webbroker3.ifo.message.WEB3OptionsＩndividualCloseMarginListResponse
     * @@roseuid 408F67900057
     */
    public WEB3OptionsIndividualCloseMarginListResponse getIndividualSettleContractList(WEB3OptionsIndividualCloseMarginListRequest l_request)
    {
        final String STR_METHOD_NAME = " getIndividualSettleContractList(WEB3OptionsIndividualCloseMarginListRequest l_request)";
        log.entering(STR_METHOD_NAME);

        WEB3OptionsIndividualCloseMarginListResponse l_response = null;
        WEB3OptionIndividualSettleContractListService l_service = null;

        try
        {
            l_service =
                 (WEB3OptionIndividualSettleContractListService)Services.getService(
                    WEB3OptionIndividualSettleContractListService.class);
        }
        // サービスで例外が発生した場合は、エラー情報をレスポンスメッセージに設定する
        catch (Exception l_ex)
        {
            l_response =
                (WEB3OptionsIndividualCloseMarginListResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "OP個別返済一覧表示サービスの取得に失敗しました。",
                l_response.errorInfo,
                l_ex);
            return l_response;
        }

        try
        {
            l_response =
                (WEB3OptionsIndividualCloseMarginListResponse)l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response =
                (WEB3OptionsIndividualCloseMarginListResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request, "株価指数オプション個別返済一覧表示処理に失敗しました。", l_ex);
            return l_response;
        }

        log.exiting(STR_METHOD_NAME);

        return l_response;
    }
}
@
