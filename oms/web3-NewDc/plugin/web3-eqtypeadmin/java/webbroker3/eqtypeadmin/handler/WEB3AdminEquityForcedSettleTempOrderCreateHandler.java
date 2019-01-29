head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.15;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3AdminEquityForcedSettleTempOrderCreateHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 強制決済仮注文作成ハンドラ(WEB3AdminEquityForcedSettleTempOrderCreateHandler.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/04/24 何文敏(中訊) 新規作成 仕様変更モデルNo.131 No.145
*/
package webbroker3.eqtypeadmin.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.eqtypeadmin.message.WEB3AdminEquityForcedSettleTempOrderCreateRequest;
import webbroker3.eqtypeadmin.message.WEB3AdminEquityForcedSettleTempOrderCreateResponse;
import webbroker3.eqtypeadmin.service.delegate.WEB3AdminEquityForcedSettleTempOrderCreateService;
import webbroker3.util.WEB3LogUtility;

/**
 * (強制決済仮注文作成ハンドラ)<BR>
 * 強制決済仮注文作成ハンドラ<BR>
 * <BR>
 * @@author 何文敏
 * @@version 1.0
 */
public class WEB3AdminEquityForcedSettleTempOrderCreateHandler implements MessageHandler
{
    /**
     * ログユーティリティ<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminEquityForcedSettleTempOrderCreateHandler.class);

    /**
     * @@roseuid 447AC0CF01F4
     */
    public WEB3AdminEquityForcedSettleTempOrderCreateHandler()
    {

    }

    /**
     * (complete強制決済仮注文作成)<BR>
     * 強制決済仮注文作成処理を行う。<BR>
     * <BR>
     * 強制決済仮注文作成サービスを取得し、execute()メソッドをコールする。<BR>
     * <BR>
     * @@param l_request - (リクエストデータ)
     * @@return WEB3AdminEquityForcedSettleTempOrderCreateResponse
     * リクエストデータ
     */
    public WEB3AdminEquityForcedSettleTempOrderCreateResponse completeForcedSettleOrderCreate(
        WEB3AdminEquityForcedSettleTempOrderCreateRequest l_request)
    {
        final String STR_METHOD_NAME =
            "completeForcedSettleOrderCreate(WEB3AdminEquityForcedSettleTempOrderCreateRequest)";
        log.entering(STR_METHOD_NAME);

        WEB3AdminEquityForcedSettleTempOrderCreateResponse l_response = null;
        WEB3AdminEquityForcedSettleTempOrderCreateService l_service = null;

        try
        {
            l_service =
                (WEB3AdminEquityForcedSettleTempOrderCreateService)Services.getService(
                    WEB3AdminEquityForcedSettleTempOrderCreateService.class);

        }
        catch (Exception l_exp)
        {
            l_response =
                (WEB3AdminEquityForcedSettleTempOrderCreateResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;

            log.error(
                l_request,
                "強制決済仮注文作成サービスの取得に失敗しました。",
                l_response.errorInfo,
                l_exp);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        try
        {
            l_response =
                (WEB3AdminEquityForcedSettleTempOrderCreateResponse)l_service.execute(l_request);
        }
        catch (WEB3BaseException l_exp)
        {
            log.error(l_request, "強制決済仮注文作成処理を行うに失敗しました。", l_exp);
            l_response =
                (WEB3AdminEquityForcedSettleTempOrderCreateResponse)l_request.createResponse();
            l_response.errorInfo = l_exp.getErrorInfo();
            return l_response;
        }
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
}
@
