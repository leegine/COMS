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
filename	WEB3EquityReceiveCloseOrderHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 株式失効通知ハンドラ(WEB3EquityReceiveCloseOrderHandler.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/05/24 李綱 (中訊) 新規作成
                   2004/12/13 水落 (SRA) クラスの接頭辞修正
                   2005/01/06 岡村 (SRA) JavaDoc修正
*/
package webbroker3.equity.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.equity.message.WEB3EquityCloseOrderRequest;
import webbroker3.equity.message.WEB3EquityCloseOrderResponse;
import webbroker3.equity.service.delegate.WEB3EquityReceiveCloseOrderService;
import webbroker3.util.WEB3LogUtility;

/**
 * （株式失効通知ハンドラ）。<BR>
 * <BR>
 * 株式失効通知ハンドラクラス
 * @@version 1.0
 */
public class WEB3EquityReceiveCloseOrderHandler implements MessageHandler
{
    /**
     * ログ出力ユーティリティ。<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3EquityReceiveCloseOrderHandler.class);

    /**
     * @@roseuid 40AC8A9E03A9
     */
    public WEB3EquityReceiveCloseOrderHandler()
    {

    }

    /**
     * (失効通知)<BR>
     * リクエストを受け、株式失効通知処理を実行する。<BR>
     * （システム実装方針ガイド 4.5.ハンドラ　@参照）<BR>
     * <BR>
     * １）　@株式失効通知サービスオブジェクトを取得する。<BR>
     * <BR>
     * ２）　@株式失効通知サービスオブジェクト.execute（）をコールし、処理結果である<BR>
     * 株式失効通知レスポンスオブジェクトを取得する。<BR>
     * <BR>
     * 　@[引数]<BR>
     * 　@失効リクエスト<BR>
     * <BR>
     * ３）　@サービスで例外が発生した場合は、<BR>
     * エラー情報をレスポンスメッセージに設定する。<BR>
     * <BR>
     * ４）　@株式失効通知レスポンスオブジェクトを返却する。<BR>
     * @@param l_request - (l_request)<BR>
     * 失効通知リクエストオブジェクト<BR>
     * @@return webbroker3.equity.message.WEB3EquityCloseOrderResponse
     * @@throws webbroker3.common.WEB3BaseException
     * @@roseuid 403D8CE40251
     */
    public WEB3EquityCloseOrderResponse receiveCloseOrder(WEB3EquityCloseOrderRequest l_request)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "receiveCloseOrder(WEB3EquityCloseOrderRequest)";
        log.entering(STR_METHOD_NAME);

        WEB3EquityCloseOrderResponse l_response = null;
        WEB3EquityReceiveCloseOrderService l_service = null;

        try
        {
            l_service =
                (WEB3EquityReceiveCloseOrderService) Services.getService(
                    WEB3EquityReceiveCloseOrderService.class);
        }
        catch (Exception l_exp)
        {
            l_response =
                (WEB3EquityCloseOrderResponse) l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "株式注文失効通知サービスの取得に失敗しました。",
                l_response.errorInfo,
                l_exp);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

        try
        {
            l_response =
                (WEB3EquityCloseOrderResponse) l_service.execute(l_request);
        }
        catch (WEB3BaseException l_exp)
        {
            l_response =
                (WEB3EquityCloseOrderResponse) l_request.createResponse();
            l_response.errorInfo = l_exp.getErrorInfo();
            log.error(l_request, "株式注文失効通知に失敗しました。", l_exp);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        catch (WEB3BaseRuntimeException l_bre)
        {
            l_response =
                (WEB3EquityCloseOrderResponse) l_request.createResponse();
            l_response.errorInfo = l_bre.getErrorInfo();
            log.error(l_request, "株式注文失効通知に失敗しました。", l_bre);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

        log.exiting(STR_METHOD_NAME);

        return l_response;
    }
}
@
