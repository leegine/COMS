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
filename	WEB3AdminEquityAttentionInfoReferenceHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者注意情報照会ハンドラ(WEB3AdminEquityAttentionInfoReferenceHandler.java)
Author Name      : Daiwa Institute of Research
Revision History : 2008/12/30　@李キョウ(中訊) 新規作成 仕様変更モデルNo.216
*/
package webbroker3.eqtypeadmin.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.eqtypeadmin.message.WEB3AdminEqAttentionInfoRefInpRequest;
import webbroker3.eqtypeadmin.message.WEB3AdminEqAttentionInfoRefInpResponse;
import webbroker3.eqtypeadmin.message.WEB3AdminEqAttentionInfoRefRefRequest;
import webbroker3.eqtypeadmin.message.WEB3AdminEqAttentionInfoRefRefResponse;
import webbroker3.eqtypeadmin.service.delegate.WEB3AdminEquityAttentionInfoReferenceService;
import webbroker3.util.WEB3LogUtility;

/**
 * (管理者注意情報照会ハンドラ)<BR>
 * 管理者注意情報照会ハンドラクラス<BR>
 * <BR>
 * @@author 李キョウ
 * @@version 1.0
 */
public class WEB3AdminEquityAttentionInfoReferenceHandler implements MessageHandler
{
    /**
     * ログ出力ユーティリティ。<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminEquityAttentionInfoReferenceHandler.class);
    /**
     * @@roseuid 49588AEB003E
     */
    public WEB3AdminEquityAttentionInfoReferenceHandler()
    {

    }

    /**
     * (get入力画面)<BR>
     * 注意情報照会入力画面表示処理を行う。<BR>
     * <BR>
     * 管理者注意情報照会サービスImplを取得し、<BR>
     * execute()メソッドをコールする。<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * 管理者・注意情報入力リクエストオブジェクト<BR>
     * @@return WEB3AdminEqAttentionInfoRefInpResponse
     * @@roseuid 494227B502C3
     */
    public WEB3AdminEqAttentionInfoRefInpResponse getInputScreen(
        WEB3AdminEqAttentionInfoRefInpRequest l_request)
    {
        final String STR_METHOD_NAME = "getInputScreen(WEB3AdminEqAttentionInfoRefInpRequest)";
        log.entering(STR_METHOD_NAME);

        WEB3AdminEqAttentionInfoRefInpResponse l_response = null;
        WEB3AdminEquityAttentionInfoReferenceService l_service = null;

        try
        {
            //管理者注意情報照会サービスを取得し
            l_service = (WEB3AdminEquityAttentionInfoReferenceService)Services.getService(
                WEB3AdminEquityAttentionInfoReferenceService.class);
        }
        // サービスで例外が発生した場合は、エラー情報をレスポンスメッセージに設定する
        catch (Exception l_ex)
        {
            l_response = (WEB3AdminEqAttentionInfoRefInpResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "管理者注意情報照会サービスの取得に失敗しました。",
                l_response.errorInfo,
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

        try
        {
            //execute
            l_response = (WEB3AdminEqAttentionInfoRefInpResponse)l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response = (WEB3AdminEqAttentionInfoRefInpResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request,
                "注意情報照会入力画面表示処理が失敗しました。",
                l_response.errorInfo,
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        catch (WEB3BaseRuntimeException l_ex)
        {
            l_response = (WEB3AdminEqAttentionInfoRefInpResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request,
                " 注意情報照会入力画面表示処理が失敗しました。",
                l_response.errorInfo,
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (get照会画面)<BR>
     * 注意情報照会画面表示処理を行う。<BR>
     * <BR>
     * 管理者注意情報照会サービスImplを取得し、<BR>
     * execute()メソッドをコールする。<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * 管理者・注意情報照会リクエストオブジェクト<BR>
     * @@return WEB3AdminEqAttentionInfoRefRefResponse
     * @@roseuid 494228FF018C
     */
    public WEB3AdminEqAttentionInfoRefRefResponse getReferenceScreen(
        WEB3AdminEqAttentionInfoRefRefRequest l_request)
    {
        final String STR_METHOD_NAME = "getReferenceScreen(WEB3AdminEqAttentionInfoRefRefRequest)";
        log.entering(STR_METHOD_NAME);

        WEB3AdminEqAttentionInfoRefRefResponse l_response = null;
        WEB3AdminEquityAttentionInfoReferenceService l_service = null;

        try
        {
            //管理者注意情報照会サービスを取得し
            l_service = (WEB3AdminEquityAttentionInfoReferenceService)Services.getService(
                WEB3AdminEquityAttentionInfoReferenceService.class);
        }
        // サービスで例外が発生した場合は、エラー情報をレスポンスメッセージに設定する
        catch (Exception l_ex)
        {
            l_response = (WEB3AdminEqAttentionInfoRefRefResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "管理者注意情報照会サービスの取得に失敗しました。",
                l_response.errorInfo,
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

        try
        {
            //execute
            l_response = (WEB3AdminEqAttentionInfoRefRefResponse)l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response = (WEB3AdminEqAttentionInfoRefRefResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request,
                "注意情報照会画面表示処理が失敗しました。",
                l_response.errorInfo,
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        catch (WEB3BaseRuntimeException l_ex)
        {
            l_response = (WEB3AdminEqAttentionInfoRefRefResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request,
                " 注意情報照会画面表示処理が失敗しました。",
                l_response.errorInfo,
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
}
@
