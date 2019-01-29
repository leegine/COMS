head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.14;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3FuturesIndividualSettleContractListHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 株価指数先物個別返済一覧表示ハンドラクラス(WEB3FuturesIndividualSettleContractListHandler.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/07/22 鄒鋭 (中訊) 新規作成
*/

package webbroker3.ifo.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.util.WEB3LogUtility;

import webbroker3.ifo.service.delegate.WEB3FuturesIndividualSettleContractListService;
import webbroker3.ifo.message.WEB3FuturesIndividualCloseMarginListRequest;
import webbroker3.ifo.message.WEB3FuturesIndividualCloseMarginListResponse;

/**
 * (株価指数先物個別返済一覧表示ハンドラ)<BR>
 * 株価指数先物個別返済一覧表示ハンドラクラス<BR>
 * @@author 鄒鋭
 * @@version 1.0
 */
public class WEB3FuturesIndividualSettleContractListHandler implements MessageHandler
{

    /**
     * ログ出力ユーティリティ。
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(WEB3FuturesIndividualSettleContractListHandler.class);

    /**
     * @@roseuid 40F7B07200DA
     */
    public WEB3FuturesIndividualSettleContractListHandler()
    {

    }

    /**
     * (get個別返済一覧)<BR>
     * 株価指数先物個別返済一覧表示処理を行う。<BR>
     * <BR>
     * 先物個別返済一覧画面表示サービスを取得し、execute()<BR>
     * メソッドをコールする。<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * 
     * @@return webbroker3.ifo.message.WEB3FuturesIndividualCloseMarginListResponse
     * @@roseuid 40A9923C023B
     */
    public WEB3FuturesIndividualCloseMarginListResponse getIndividualCloseMarginList(WEB3FuturesIndividualCloseMarginListRequest l_request)
    {
        final String STR_METHOD_NAME = " getIndividualSettleContractList(WEB3FuturesIndividualCloseMarginListRequest l_request)";
        log.entering(STR_METHOD_NAME);

        WEB3FuturesIndividualCloseMarginListResponse l_response = null;
        WEB3FuturesIndividualSettleContractListService l_service = null;

        try
        {
            l_service = (WEB3FuturesIndividualSettleContractListService) Services.getService(WEB3FuturesIndividualSettleContractListService.class);
        }
        // サービスで例外が発生した場合は、エラー情報をレスポンスメッセージに設定する
        catch (Exception l_ex)
        {
            l_response = (WEB3FuturesIndividualCloseMarginListResponse) l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(l_request, "先物個別返済一覧表示サービスの取得に失敗しました。", l_response.errorInfo, l_ex);
            return l_response;
        }

        try
        {
            l_response = (WEB3FuturesIndividualCloseMarginListResponse) l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response = (WEB3FuturesIndividualCloseMarginListResponse) l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request, "株価指数先物個別返済一覧表示処理に失敗しました。", l_ex);
            return l_response;
        }

        log.exiting(STR_METHOD_NAME);

        return l_response;
    }
}
@
