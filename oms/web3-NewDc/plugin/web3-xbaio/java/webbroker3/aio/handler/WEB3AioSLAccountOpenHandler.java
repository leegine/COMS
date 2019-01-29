head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.26.22;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3AioSLAccountOpenHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : SL口座開設ハンドラ(WEB3AioSLAccountOpenHandler.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/09/19 謝旋 (中訊) 新規作成 モデルNo.763
*/

package webbroker3.aio.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.aio.message.WEB3SLAccountOpenApplyRequest;
import webbroker3.aio.message.WEB3SLAccountOpenApplyResponse;
import webbroker3.aio.message.WEB3SLAccountOpenRequest;
import webbroker3.aio.message.WEB3SLAccountOpenResponse;
import webbroker3.aio.service.delegate.WEB3AioSLAccountOpenUnitService;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.util.WEB3LogUtility;

/**
 * (SL口座開設ハンドラ)<BR>
 * SL口座開設ハンドラクラス<BR>
 * <BR>
 * @@author 謝旋
 * @@version 1.0
 */
public class WEB3AioSLAccountOpenHandler implements MessageHandler
{
    /**
     * ログ出力ユーティリティ。
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AioSLAccountOpenHandler.class);

    /**
     * @@roseuid 46F0D58001E4
     */
    public WEB3AioSLAccountOpenHandler()
    {

    }

    /**
     * (SL口座開設確認)<BR>
     * SL口座開設Unitサービスを取得し、execute()メソッドをコールする。<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * リクエストデータ<BR>
     * <BR>
     * @@return WEB3SLAccountOpenApplyResponse
     * @@roseuid 46BBF45E0187
     */
    public WEB3SLAccountOpenApplyResponse slAccountOpenConfirm(WEB3SLAccountOpenApplyRequest l_request)
    {
        final String STR_METHOD_NAME = " slAccountOpenConfirm(WEB3SLAccountOpenApplyRequest)";
        log.entering(STR_METHOD_NAME);

        WEB3SLAccountOpenApplyResponse l_response = null;
        WEB3AioSLAccountOpenUnitService l_aioSLAccountOpenUnitService = null;

        try
        {
            l_aioSLAccountOpenUnitService =
                (WEB3AioSLAccountOpenUnitService)Services.getService(
                    WEB3AioSLAccountOpenUnitService.class);
        }
        // サービスで例外が発生した場合は、エラー情報をレスポンスメッセージに設定する
        catch (Exception l_ex)
        {
            l_response =
                (WEB3SLAccountOpenApplyResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "SL口座開設UnitServiceの取得に失敗しました。",
                l_response.errorInfo,
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

        try
        {
            //SL口座開設UnitServiceを取得し、execute()メソッドをコールする
            l_response =
                (WEB3SLAccountOpenApplyResponse)l_aioSLAccountOpenUnitService.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response =
                (WEB3SLAccountOpenApplyResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request, "SL口座開設申込リクエストが失敗しました。", l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        catch (WEB3BaseRuntimeException l_ex)
        {
            l_response = (WEB3SLAccountOpenApplyResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request,
                "SL口座開設UnitService処理が失敗しました。",
                l_response.errorInfo,
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (SL口座開設)<BR>
     * SL口座開設Unitサービスを取得し、execute()メソッドをコールする。<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * リクエストデータ<BR>
     * <BR>
     * @@return WEB3SLAccountOpenResponse
     * @@roseuid 46CE402C018B
     */
    public WEB3SLAccountOpenResponse slAccountOpen(WEB3SLAccountOpenRequest l_request)
    {
        final String STR_METHOD_NAME = " slAccountOpen(WEB3SLAccountOpenRequest)";
        log.entering(STR_METHOD_NAME);

        WEB3SLAccountOpenResponse l_response = null;
        WEB3AioSLAccountOpenUnitService l_aioSLAccountOpenUnitService = null;

        try
        {
            l_aioSLAccountOpenUnitService =
                (WEB3AioSLAccountOpenUnitService)Services.getService(
                    WEB3AioSLAccountOpenUnitService.class);
        }
        // サービスで例外が発生した場合は、エラー情報をレスポンスメッセージに設定する
        catch (Exception l_ex)
        {
            l_response =
                (WEB3SLAccountOpenResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "SL口座開設UnitServiceの取得に失敗しました。",
                l_response.errorInfo,
                l_ex);
            return l_response;
        }

        try
        {
            //証券振替通知サービスを取得し、execute()メソッドをコールする
            l_response =
                (WEB3SLAccountOpenResponse)l_aioSLAccountOpenUnitService.execute(
                    l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response =
                (WEB3SLAccountOpenResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request, "SL口座開設リクエストが失敗しました。", l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        catch (WEB3BaseRuntimeException l_ex)
        {
            l_response = (WEB3SLAccountOpenResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request,
                "SL口座開設UnitService処理が失敗しました。",
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
