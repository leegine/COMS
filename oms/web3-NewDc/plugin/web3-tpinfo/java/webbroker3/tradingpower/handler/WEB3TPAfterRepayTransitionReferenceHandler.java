head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.10.16;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	WEB3TPAfterRepayTransitionReferenceHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
 Copyright        : (株)大和総研 証券ソリューションシステム第二部
 File Name        : 返済後余力情報画面表示ハンドラ(WEB3TPAfterRepayTransitionReferenceHandler.java)
 Author Name      : Daiwa Institute of Research
 Revesion History : 2005/04/08 nakazato(ACT) 新規作成
 */
package webbroker3.tradingpower.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.equity.define.WEB3MarginBeforeRequestDivDef;
import webbroker3.tradingpower.message.WEB3TPAfterRepayTransitionReferenceRequest;
import webbroker3.tradingpower.message.WEB3TPAfterRepayTransitionReferenceResponse;
import webbroker3.tradingpower.service.delegate.WEB3TPAfterRepayTransitionReferenceService;
import webbroker3.util.WEB3LogUtility;

/**
 * (返済後余力情報画面表示ハンドラ)
 */
public class WEB3TPAfterRepayTransitionReferenceHandler
        implements MessageHandler
{

    /**
     * ログユーティリティ
     */
    private static final WEB3LogUtility log = WEB3LogUtility
        .getInstance(WEB3TPAfterRepayTransitionReferenceHandler.class);

    /**
     * @@roseuid 4255DAAC03D9
     */
    public WEB3TPAfterRepayTransitionReferenceHandler()
    {

    }

    /**
     * (get返済後余力情報画面) 信用取引返済注文後の余力推移画面を表示する。
     * 
     * 返済後余力情報画面表示サービスを取得し、execute()メソッドをコールする。
     * 
     * @@param (返済後余力)信用取引返済注文確認リクエスト -
     *            ((返済後余力)信用取引返済注文確認リクエスト)
     * 
     * @@return webbroker3.tradingpower.message.WEB3TPAfterRepayTransitionReferenceResponse
     * @@roseuid 4248A63D030F
     */
    public WEB3TPAfterRepayTransitionReferenceResponse getAfterRepayTransitionReference(
            WEB3TPAfterRepayTransitionReferenceRequest l_request)
    {
        final String STR_METHOD_NAME = "getAfterRepayTransitionReference(WEB3TPAfterRepayTransitionReferenceRequest)";
        log.entering(STR_METHOD_NAME);

        //返済後余力情報画面表示サービス
        WEB3TPAfterRepayTransitionReferenceService l_service = null;
        //返済後余力情報画面表示レスポンス
        WEB3TPAfterRepayTransitionReferenceResponse l_response = null;

        //リクエスト.要求元区分に1:返済後余力表示をセット
        l_request.requestFromType = WEB3MarginBeforeRequestDivDef.AFTER_REPAY;

        try
        {
            //返済後余力情報画面表示サービスを取得
            l_service = (WEB3TPAfterRepayTransitionReferenceService) Services
                .getService(WEB3TPAfterRepayTransitionReferenceService.class);

            //execute()メソッドをコール
            l_response = (WEB3TPAfterRepayTransitionReferenceResponse) l_service
                .execute(l_request);

            //返済後余力情報画面表示レスポンスを返却する
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        catch(WEB3BaseException be)
        {
            l_response = (WEB3TPAfterRepayTransitionReferenceResponse) l_request
                .createResponse();
            l_response.errorInfo = be.getErrorInfo();
            log.error(l_request, "返済後余力情報画面表示処理に失敗しました。", be);
            return l_response;
        }
        catch(WEB3BaseRuntimeException bre)
        {
            l_response = (WEB3TPAfterRepayTransitionReferenceResponse) l_request
                .createResponse();
            l_response.errorInfo = bre.getErrorInfo();
            log.error(l_request, "返済後余力情報画面表示処理に失敗しました。", bre);
            return l_response;
        }
        catch(Exception e)
        {
            l_response = (WEB3TPAfterRepayTransitionReferenceResponse) l_request
                .createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                      l_request, "返済後余力情報画面表示処理に失敗しました。", l_response.errorInfo,
                      e);
            return l_response;
        }
    }
}@
