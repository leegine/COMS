head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.10.02.13.21;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8644d782f8a12fc;
filename	WEB3AdminAccInfoCampaignAccOpenChangeHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 口座開設条件指定変更ハンドラ(WEB3AdminAccInfoCampaignAccOpenChangeHandler.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/02/01 魏 (中訊) 新規作成
*/
package webbroker3.accountinfo.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.accountinfo.message.WEB3AdminAccInfoCampaignAccOpenCompleteRequest;
import webbroker3.accountinfo.message.WEB3AdminAccInfoCampaignAccOpenCompleteResponse;
import webbroker3.accountinfo.message.WEB3AdminAccInfoCampaignAccOpenConfirmRequest;
import webbroker3.accountinfo.message.WEB3AdminAccInfoCampaignAccOpenConfirmResponse;
import webbroker3.accountinfo.message.WEB3AdminAccInfoCampaignAccOpenInputRequest;
import webbroker3.accountinfo.message.WEB3AdminAccInfoCampaignAccOpenInputResponse;
import webbroker3.accountinfo.service.delegate.WEB3AdminAccInfoCampaignAccOpenChangeService;
import webbroker3.accountinfo.service.delegate.stdimpls.WEB3AdminAccInfoCampaignAccOpenChangeServiceImpl;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.util.WEB3LogUtility;

/**
 * (口座開設条件指定変更ハンドラ)<BR>
 * @@author  魏
 * @@version 1.0
 */
public class WEB3AdminAccInfoCampaignAccOpenChangeHandler implements MessageHandler
{
    /**
     * ログ出力ユーティリティ。
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminAccInfoCampaignAccOpenChangeHandler.class);

    /**
     * @@roseuid 45C08B510290
     */
    public WEB3AdminAccInfoCampaignAccOpenChangeHandler()
    {

    }

    /**
     * (入力画面表示処理)<BR>
     * 口座開設条件指定入力画面表示処理を実施する。<BR>
     * <BR>
     * 口座開設条件指定変更サービスを取得し、execute()メソッドをコールする。<BR>
     *
     * @@param l_request -
     *            管理者お客様情報手数料割引キャンペーン口座開設条件入力リクエストオブジェクト<BR>
     * @@return WEB3AdminAccInfoCampaignAccOpenInputResponse
     * @@roseuid 45B04CD801AD
     */
    public WEB3AdminAccInfoCampaignAccOpenInputResponse inputScreenProcess(
            WEB3AdminAccInfoCampaignAccOpenInputRequest l_request)
    {
        final String STR_METHOD_NAME = " inputScreenProcess(WEB3AdminAccInfoCampaignAccOpenInputRequest) ";
        log.entering(STR_METHOD_NAME);

        if (l_request == null)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME,
                "リクエストがnullです。");
        }

        WEB3AdminAccInfoCampaignAccOpenInputResponse l_response = null;
        WEB3AdminAccInfoCampaignAccOpenChangeService l_service = null;

        try
        {
            //口座開設条件指定変更サービス
            l_service = (WEB3AdminAccInfoCampaignAccOpenChangeService)
                Services.getService(WEB3AdminAccInfoCampaignAccOpenChangeService.class);
            if (l_service == null)
            {
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                    this.getClass().getName() + STR_METHOD_NAME,
                    "口座開設条件指定変更サービスを取得に失敗しました。");
            }
            //execute
            l_response = (WEB3AdminAccInfoCampaignAccOpenInputResponse)l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response = (WEB3AdminAccInfoCampaignAccOpenInputResponse)l_request.createResponse();
            if (l_response == null)
            {
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                    this.getClass().getName() + STR_METHOD_NAME);
            }
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request,
                "get入力画面を実施に失敗しました。",
                l_response.errorInfo,
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        catch (Exception l_ex)
        {
            l_response = (WEB3AdminAccInfoCampaignAccOpenInputResponse) l_request.createResponse();
            if (l_response == null)
            {
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                    this.getClass().getName() + STR_METHOD_NAME);
            }

            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "get入力画面を実施に失敗しました。",
                l_response.errorInfo,
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

        log.exiting(STR_METHOD_NAME);
        return l_response;

    }

    /**
     * (変更確認画面表示処理)<BR>
     * 口座開設条件指定変更確認画面表示処理を実施する。<BR>
     * <BR>
     * 口座開設条件指定変更サービスを取得し、execute()メソッドをコールする。<BR>
     *
     * @@param l_request -
     *            管理者お客様情報手数料割引キャンペーン口座開設条件確認リクエストオブジェクト<BR>
     * @@return WEB3AdminAccInfoCampaignAccOpenConfirmResponse
     * @@roseuid 45B050570153
     */
    public WEB3AdminAccInfoCampaignAccOpenConfirmResponse changeConfirmScreenProcess(
            WEB3AdminAccInfoCampaignAccOpenConfirmRequest l_request)
    {
        final String STR_METHOD_NAME = " changeConfirmScreenProcess(WEB3AdminAccInfoCampaignAccOpenConfirmRequest) ";
        log.entering(STR_METHOD_NAME);

        if (l_request == null)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME,
                "リクエストがnullです。");
        }

        WEB3AdminAccInfoCampaignAccOpenConfirmResponse l_response = null;
        WEB3AdminAccInfoCampaignAccOpenChangeService l_service = null;

        try
        {
            //口座開設条件指定変更サービス
            l_service = (WEB3AdminAccInfoCampaignAccOpenChangeService)
                Services.getService(WEB3AdminAccInfoCampaignAccOpenChangeService.class);
            if (l_service == null)
            {
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                    this.getClass().getName() + STR_METHOD_NAME,
                    "口座開設条件指定変更サービスを取得に失敗しました。");
            }
            //execute
            l_response = (WEB3AdminAccInfoCampaignAccOpenConfirmResponse)l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response = (WEB3AdminAccInfoCampaignAccOpenConfirmResponse)l_request.createResponse();
            if (l_response == null)
            {
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                    this.getClass().getName() + STR_METHOD_NAME);
            }
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request,
                "get変更確認を実施に失敗しました。",
                l_response.errorInfo,
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        catch (Exception l_ex)
        {
            l_response = (WEB3AdminAccInfoCampaignAccOpenConfirmResponse) l_request.createResponse();
            if (l_response == null)
            {
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                    this.getClass().getName() + STR_METHOD_NAME);
            }

            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "get変更確認を実施に失敗しました。",
                l_response.errorInfo,
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

        log.exiting(STR_METHOD_NAME);
        return l_response;

    }

    /**
     * (変更完了画面表示処理)<BR>
     * 口座開設条件指定変更完了画面表示処理を実施する。<BR>
     * <BR>
     * 口座開設条件指定変更サービスを取得し、execute()メソッドをコールする。<BR>
     *
     * @@param l_request -
     *            管理者お客様情報手数料割引キャンペーン口座開設条件確認リクエストオブジェクト<BR>
     * @@return WEB3AdminAccInfoCampaignAccOpenCompleteResponse
     * @@roseuid 45B0507E0376
     */
    public WEB3AdminAccInfoCampaignAccOpenCompleteResponse changeCompleteScreenProcess(
            WEB3AdminAccInfoCampaignAccOpenCompleteRequest l_request)
    {
        final String STR_METHOD_NAME = " changeCompleteScreenProcess(WEB3AdminAccInfoCampaignAccOpenCompleteRequest) ";
        log.entering(STR_METHOD_NAME);

        if (l_request == null)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME,
                "リクエストがnullです。");
        }

        WEB3AdminAccInfoCampaignAccOpenCompleteResponse l_response = null;
        WEB3AdminAccInfoCampaignAccOpenChangeService l_service = null;

        try
        {
            //口座開設条件指定変更サービス
            l_service = (WEB3AdminAccInfoCampaignAccOpenChangeService)
                Services.getService(WEB3AdminAccInfoCampaignAccOpenChangeService.class);
            if (l_service == null)
            {
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                    this.getClass().getName() + STR_METHOD_NAME,
                    "口座開設条件指定変更サービスを取得に失敗しました。");
            }
            //execute
            l_response = (WEB3AdminAccInfoCampaignAccOpenCompleteResponse)l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response = (WEB3AdminAccInfoCampaignAccOpenCompleteResponse)l_request.createResponse();
            if (l_response == null)
            {
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                    this.getClass().getName() + STR_METHOD_NAME);
            }
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request,
                "get完了画面を実施に失敗しました。",
                l_response.errorInfo,
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        catch (Exception l_ex)
        {
            l_response = (WEB3AdminAccInfoCampaignAccOpenCompleteResponse) l_request.createResponse();
            if (l_response == null)
            {
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                    this.getClass().getName() + STR_METHOD_NAME);
            }

            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "get完了画面を実施に失敗しました。",
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
