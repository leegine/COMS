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
filename	WEB3EquityOrderAcceptHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 株式注文受付ハンドラクラス(WEB3EquityOrderAcceptHandler.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/02/24 中尾　@寿彦(SRA) 新規作成
                   2004/10/22法@旭修正   
                   2005/01/06 岡村和明(SRA) JavaDoc修正
*/
package webbroker3.equity.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.equity.message.WEB3EquityOrderAcceptRequest;
import webbroker3.equity.message.WEB3EquityOrderAcceptResponse;
import webbroker3.equity.service.delegate.WEB3EquityOrderAcceptService;
import webbroker3.util.WEB3LogUtility;

/**
 * （株式注文受付ハンドラ）。<BR>
 * <BR>
 * 株式注文受付ハンドラクラス
 * @@version 1.0
 */
public class WEB3EquityOrderAcceptHandler implements MessageHandler
{
    /**
     * ログ出力ユーティリティ。<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3EquityOrderAcceptHandler.class);
    /**
     * @@roseuid 40B1E6D400FA
     */
    public WEB3EquityOrderAcceptHandler()
    {
    }

    /**
     * (株式注文受付リクエスト )
     * リクエストを受け、株式注文受付処理を実行する。 <BR>
     * （システム実装方針ガイド 4.5.ハンドラ　@参照） <BR>
     * <BR>
     * １）　@株式注文受付サービスオブジェクトを取得する。 <BR>
     * <BR>
     * ２）　@株式注文受付サービスオブジェクト.execute（）をコールし、 <BR>
     * 処理結果である株式注文受付レスポンスオブジェクトを取得する。 <BR>
     * <BR>
     * ３）　@サービスで例外が発生した場合は、 <BR>
     * エラー情報をレスポンスメッセージに設定する。 <BR>
     * <BR>
     * ４）　@株式注文受付レスポンスオブジェクトを返却する。 <BR>
     * <BR>
     * @@param WEB3EquityOrderAcceptRequest - (入力データ) <BR>
     * クライアントからのリクエストメッセージを指定する。 <BR>
     * @@return WEB3EquityOrderAcceptResponse
     * @@roseuid 4035DA8303B9
     */
    public WEB3EquityOrderAcceptResponse equityOrderAcceptRequest(WEB3EquityOrderAcceptRequest request)
    {
        final String STR_METHOD_NAME =
            "equityOrderAcceptRequest(WEB3EquityOrderAcceptRequest)";
        log.entering(STR_METHOD_NAME);
        if (request == null)
        {
            throw new WEB3BaseRuntimeException(
               WEB3ErrorCatalog.SYSTEM_ERROR_80017,
               this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        WEB3EquityOrderAcceptResponse l_response = null;
        WEB3EquityOrderAcceptService l_service = null;
        try
        {
            l_service =
                (WEB3EquityOrderAcceptService) Services.getService(
                    WEB3EquityOrderAcceptService.class);
        }
        catch (Exception l_exp)
        {
            l_response =
                (WEB3EquityOrderAcceptResponse) request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                request,
                "株式注文受付サービスの取得に失敗しました。",
                l_response.errorInfo,
                l_exp);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        try
        {
            l_response =
                (WEB3EquityOrderAcceptResponse) l_service.execute(request);
        }
        catch (WEB3BaseException l_exp)
        {
            l_response =
                (WEB3EquityOrderAcceptResponse) request.createResponse();
            l_response.errorInfo = l_exp.getErrorInfo();
            log.error(request, "株式注文受付に失敗しました。", l_exp);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        catch (WEB3BaseRuntimeException l_bre)
        {
            l_response =
                (WEB3EquityOrderAcceptResponse) request.createResponse();
            l_response.errorInfo = l_bre.getErrorInfo();
            log.error(request, "株式注文受付に失敗しました。", l_bre);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
}
@
