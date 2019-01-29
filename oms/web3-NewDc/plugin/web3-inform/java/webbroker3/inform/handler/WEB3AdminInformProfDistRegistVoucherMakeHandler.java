head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.04.56.30;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	88c4d7eeee91bda;
filename	WEB3AdminInformProfDistRegistVoucherMakeHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright           : (株)大和総研 証券ソリューションシステム第二部
File Name           : 利金・分配金登録伝票作成ハンドラ(WEB3AdminInformProfDistRegistVoucherMakeHandler.java)
Author Name         : Daiwa Institute of Research
Revision History    : 2007/06/04 謝旋(中訊) 新規作成 モデルNo.056
Revision History    : 2007/06/04 徐宏偉(中訊) モデル No.065
*/

package webbroker3.inform.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.inform.message.WEB3AdminInformProfDistStatusSearchInputRequest;
import webbroker3.inform.message.WEB3AdminInformProfDistStatusSearchInputResponse;
import webbroker3.inform.message.WEB3AdminInformProfDistVoucherCancCmpRequest;
import webbroker3.inform.message.WEB3AdminInformProfDistVoucherCancCmpResponse;
import webbroker3.inform.message.WEB3AdminInformProfDistVoucherCancCnfRequest;
import webbroker3.inform.message.WEB3AdminInformProfDistVoucherCancCnfResponse;
import webbroker3.inform.message.WEB3AdminInformProfDistVoucherChgCmpRequest;
import webbroker3.inform.message.WEB3AdminInformProfDistVoucherChgCmpResponse;
import webbroker3.inform.message.WEB3AdminInformProfDistVoucherChgCnfRequest;
import webbroker3.inform.message.WEB3AdminInformProfDistVoucherChgCnfResponse;
import webbroker3.inform.message.WEB3AdminInformProfDistVoucherInfoRefRequest;
import webbroker3.inform.message.WEB3AdminInformProfDistVoucherInfoRefResponse;
import webbroker3.inform.message.WEB3AdminInformProfDistVoucherMakeCmpRequest;
import webbroker3.inform.message.WEB3AdminInformProfDistVoucherMakeCmpResponse;
import webbroker3.inform.message.WEB3AdminInformProfDistVoucherMakeCnfRequest;
import webbroker3.inform.message.WEB3AdminInformProfDistVoucherMakeCnfResponse;
import webbroker3.inform.message.WEB3AdminInformProfDistVoucherMakeInpRequest;
import webbroker3.inform.message.WEB3AdminInformProfDistVoucherMakeInpResponse;
import webbroker3.inform.service.delegate.WEB3AdminInformProfDistRegistVoucherMakeService;
import webbroker3.util.WEB3LogUtility;

/**
 * (利金・分配金登録伝票作成ハンドラ)<BR>
 * 利金・分配金登録伝票作成ハンドラクラス<BR>
 * <BR>
 * @@author 謝旋
 * @@version 1.0
 */
public class WEB3AdminInformProfDistRegistVoucherMakeHandler implements MessageHandler
{
    /**
     * (ログ出力ユーティリティ)
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(
            WEB3AdminInformProfDistRegistVoucherMakeHandler.class);

    /**
     * @@roseuid 4663A9D502AF
     */
    public WEB3AdminInformProfDistRegistVoucherMakeHandler()
    {

    }

    /**
     * (登録状況検索)<BR>
     * 分配金振替口座登録状況問合せ画面を表示する。<BR>
     * <BR>
     * 利金・分配金登録伝票作成サービスを取得し、execute()メソッドをコールする。<BR>
     * @@param l_request - リクエストデータ
     * @@return WEB3AdminInformProfDistStatusSearchInputResponse
     */
    public WEB3AdminInformProfDistStatusSearchInputResponse registStatusSearch(
        WEB3AdminInformProfDistStatusSearchInputRequest l_request)
    {
        final String STR_METHOD_NAME =
            "registStatusSearch(WEB3AdminInformProfDistStatusSearchInputRequest)";
        log.exiting(STR_METHOD_NAME);

        WEB3AdminInformProfDistStatusSearchInputResponse l_response = null;
        WEB3AdminInformProfDistRegistVoucherMakeService l_service = null;

        //利金・分配金登録伝票作成サービスオブジェクトを取得
        try
        {
            l_service = (WEB3AdminInformProfDistRegistVoucherMakeService)Services.getService(
                WEB3AdminInformProfDistRegistVoucherMakeService.class);
        }
        catch (Exception l_ex)
        {
            //サービスで例外が発生した場合は、エラー情報をレスポンスメッセージに設定する
            l_response =
                (WEB3AdminInformProfDistStatusSearchInputResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "利金・分配金登録伝票作成サービスを取得に失敗しました。",
                l_response.errorInfo,
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

        //execute()メソッドをコールする
        try
        {
            l_response =
                (WEB3AdminInformProfDistStatusSearchInputResponse)l_service.execute(
                    l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response =
                (WEB3AdminInformProfDistStatusSearchInputResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request,
                "登録状況検索が失敗しました。", l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

        log.exiting(STR_METHOD_NAME);
        //レスポンスオブジェクトを返却する。
        return l_response;
    }

    /**
     * (伝票作成入力)<BR>
     * 分配金振替口座登録入力画面を表示する。<BR>
     * <BR>
     * 利金・分配金登録伝票作成サービスを取得し、execute()メソッドをコールする。<BR>
     * @@param l_request - リクエストデータ
     * @@return WEB3AdminInformProfDistVoucherMakeInpResponse
     */
    public WEB3AdminInformProfDistVoucherMakeInpResponse voucherMakeInp(
        WEB3AdminInformProfDistVoucherMakeInpRequest l_request)
    {
        final String STR_METHOD_NAME =
            "voucherMakeInp(WEB3AdminInformProfDistVoucherMakeInpRequest)";
        log.exiting(STR_METHOD_NAME);

        WEB3AdminInformProfDistVoucherMakeInpResponse l_response = null;
        WEB3AdminInformProfDistRegistVoucherMakeService l_service = null;

        //利金・分配金登録伝票作成サービスオブジェクトを取得
        try
        {
            l_service = (WEB3AdminInformProfDistRegistVoucherMakeService)Services.getService(
                WEB3AdminInformProfDistRegistVoucherMakeService.class);
        }
        catch (Exception l_ex)
        {
            //サービスで例外が発生した場合は、エラー情報をレスポンスメッセージに設定する
            l_response =
                (WEB3AdminInformProfDistVoucherMakeInpResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "利金・分配金登録伝票作成サービスを取得に失敗しました。",
                l_response.errorInfo,
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

        //execute()メソッドをコールする
        try
        {
            l_response =
                (WEB3AdminInformProfDistVoucherMakeInpResponse)l_service.execute(
                    l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response =
                (WEB3AdminInformProfDistVoucherMakeInpResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request,
                "伝票作成入力が失敗しました。", l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

        log.exiting(STR_METHOD_NAME);
        //レスポンスオブジェクトを返却する。
        return l_response;
    }

    /**
     * (伝票作成確認)<BR>
     * 分配金振替口座登録確認画面を表示する。<BR>
     * <BR>
     * 利金・分配金登録伝票作成サービスを取得し、execute()メソッドをコールする。<BR>
     * @@param l_request - リクエストデータ
     * @@return WEB3AdminInformProfDistVoucherMakeCnfResponse
     */
    public WEB3AdminInformProfDistVoucherMakeCnfResponse voucherMakeCnf(
        WEB3AdminInformProfDistVoucherMakeCnfRequest l_request)
    {
        final String STR_METHOD_NAME =
            "voucherMakeCnf(WEB3AdminInformProfDistVoucherMakeCnfRequest)";
        log.exiting(STR_METHOD_NAME);

        WEB3AdminInformProfDistVoucherMakeCnfResponse l_response = null;
        WEB3AdminInformProfDistRegistVoucherMakeService l_service = null;

        //利金・分配金登録伝票作成サービスオブジェクトを取得
        try
        {
            l_service = (WEB3AdminInformProfDistRegistVoucherMakeService)Services.getService(
                WEB3AdminInformProfDistRegistVoucherMakeService.class);
        }
        catch (Exception l_ex)
        {
            //サービスで例外が発生した場合は、エラー情報をレスポンスメッセージに設定する
            l_response =
                (WEB3AdminInformProfDistVoucherMakeCnfResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "利金・分配金登録伝票作成サービスを取得に失敗しました。",
                l_response.errorInfo,
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

        //execute()メソッドをコールする
        try
        {
            l_response =
                (WEB3AdminInformProfDistVoucherMakeCnfResponse)l_service.execute(
                    l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response =
                (WEB3AdminInformProfDistVoucherMakeCnfResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request,
                "伝票作成確認が失敗しました。", l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

        log.exiting(STR_METHOD_NAME);
        //レスポンスオブジェクトを返却する。
        return l_response;
    }

    /**
     * (伝票作成完了)<BR>
     * 分配金振替口座登完了画面を表示する。<BR>
     * <BR>
     * 利金・分配金登録伝票作成サービスを取得し、execute()メソッドをコールする。<BR>
     * @@param l_request - リクエストデータ
     * @@return WEB3AdminInformProfDistVoucherMakeCmpResponse
     */
    public WEB3AdminInformProfDistVoucherMakeCmpResponse voucherMakeCmp(
        WEB3AdminInformProfDistVoucherMakeCmpRequest l_request)
    {
        final String STR_METHOD_NAME =
            "voucherMakeCmp(WEB3AdminInformProfDistVoucherMakeCmpRequest)";
        log.entering(STR_METHOD_NAME);

        WEB3AdminInformProfDistVoucherMakeCmpResponse l_response = null;
        WEB3AdminInformProfDistRegistVoucherMakeService l_service = null;

        //利金・分配金登録伝票作成サービスオブジェクトを取得
        try
        {
            l_service = (WEB3AdminInformProfDistRegistVoucherMakeService)Services.getService(
                WEB3AdminInformProfDistRegistVoucherMakeService.class);
        }
        catch (Exception l_ex)
        {
            //サービスで例外が発生した場合は、エラー情報をレスポンスメッセージに設定する
            l_response =
                (WEB3AdminInformProfDistVoucherMakeCmpResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "利金・分配金登録伝票作成サービスを取得に失敗しました。",
                l_response.errorInfo,
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

        //execute()メソッドをコールする
        try
        {
            l_response =
                (WEB3AdminInformProfDistVoucherMakeCmpResponse)l_service.execute(
                    l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response =
                (WEB3AdminInformProfDistVoucherMakeCmpResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request,
                "伝票作成完了が失敗しました。", l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

        log.exiting(STR_METHOD_NAME);
        //レスポンスオブジェクトを返却する。
        return l_response;
    }

    /**
     * (登録口座参照)<BR>
     * 分配金振替口座登録参照画面を表示する。<BR>
     * <BR>
     * 利金・分配金登録伝票作成サービスを取得し、execute()メソッドをコールする。<BR>
     * @@param l_request - リクエストデータ
     * @@return WEB3AdminInformProfDistVoucherInfoRefResponse
     */
    public WEB3AdminInformProfDistVoucherInfoRefResponse registAccountRef(
        WEB3AdminInformProfDistVoucherInfoRefRequest l_request)
    {
        final String STR_METHOD_NAME =
            "registAccountRef(WEB3AdminInformProfDistVoucherInfoRefRequest)";
        log.entering(STR_METHOD_NAME);

        WEB3AdminInformProfDistVoucherInfoRefResponse l_response = null;
        WEB3AdminInformProfDistRegistVoucherMakeService l_service = null;

        //利金・分配金登録伝票作成サービスオブジェクトを取得
        try
        {
            l_service = (WEB3AdminInformProfDistRegistVoucherMakeService)Services.getService(
                WEB3AdminInformProfDistRegistVoucherMakeService.class);
        }
        catch (Exception l_ex)
        {
            //サービスで例外が発生した場合は、エラー情報をレスポンスメッセージに設定する
            l_response =
                (WEB3AdminInformProfDistVoucherInfoRefResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "利金・分配金登録伝票作成サービスを取得に失敗しました。",
                l_response.errorInfo,
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

        //execute()メソッドをコールする
        try
        {
            l_response =
                (WEB3AdminInformProfDistVoucherInfoRefResponse)l_service.execute(
                    l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response =
                (WEB3AdminInformProfDistVoucherInfoRefResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request,
                "登録口座参照が失敗しました。", l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

        log.exiting(STR_METHOD_NAME);
        //レスポンスオブジェクトを返却する。
        return l_response;
    }

    /**
     * (登録口座変更確認)<BR>
     * 分配金振替口座登録変更確認画面を表示する。<BR>
     * <BR>
     * 利金・分配金登録伝票作成サービスを取得し、execute()メソッドをコールする。<BR>
     * @@param l_request - リクエストデータ
     * @@return WEB3AdminInformProfDistVoucherChgCnfResponse
     */
    public WEB3AdminInformProfDistVoucherChgCnfResponse registAccountChgeCnf(
        WEB3AdminInformProfDistVoucherChgCnfRequest l_request)
    {
        final String STR_METHOD_NAME =
            "registAccountChgeCnf(WEB3AdminInformProfDistVoucherChgCnfRequest)";
        log.entering(STR_METHOD_NAME);

        WEB3AdminInformProfDistVoucherChgCnfResponse l_response = null;
        WEB3AdminInformProfDistRegistVoucherMakeService l_service = null;

        //利金・分配金登録伝票作成サービスオブジェクトを取得
        try
        {
            l_service = (WEB3AdminInformProfDistRegistVoucherMakeService)Services.getService(
                WEB3AdminInformProfDistRegistVoucherMakeService.class);
        }
        catch (Exception l_ex)
        {
            //サービスで例外が発生した場合は、エラー情報をレスポンスメッセージに設定する
            l_response =
                (WEB3AdminInformProfDistVoucherChgCnfResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "利金・分配金登録伝票作成サービスを取得に失敗しました。",
                l_response.errorInfo,
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

        //execute()メソッドをコールする
        try
        {
            l_response =
                (WEB3AdminInformProfDistVoucherChgCnfResponse)l_service.execute(
                    l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response =
                (WEB3AdminInformProfDistVoucherChgCnfResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request,
                "登録口座変更確認が失敗しました。", l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

        log.exiting(STR_METHOD_NAME);
        //レスポンスオブジェクトを返却する。
        return l_response;
    }

    /**
     * (登録口座変更完了)<BR>
     * 分配金振替口座登録変更完了画面を表示する。<BR>
     * <BR>
     * 利金・分配金登録伝票作成サービスを取得し、execute()メソッドをコールする。<BR>
     * @@param l_request - リクエストデータ
     * @@return WEB3AdminInformProfDistVoucherChgCmpResponse
     */
    public WEB3AdminInformProfDistVoucherChgCmpResponse registAccountChgeCmp(
        WEB3AdminInformProfDistVoucherChgCmpRequest l_request)
    {
        final String STR_METHOD_NAME =
            "registAccountChgeCmp(WEB3AdminInformProfDistVoucherChgCmpRequest)";
        log.entering(STR_METHOD_NAME);

        WEB3AdminInformProfDistVoucherChgCmpResponse l_response = null;
        WEB3AdminInformProfDistRegistVoucherMakeService l_service = null;

        //利金・分配金登録伝票作成サービスオブジェクトを取得
        try
        {
            l_service = (WEB3AdminInformProfDistRegistVoucherMakeService)Services.getService(
                WEB3AdminInformProfDistRegistVoucherMakeService.class);
        }
        catch (Exception l_ex)
        {
            //サービスで例外が発生した場合は、エラー情報をレスポンスメッセージに設定する
            l_response =
                (WEB3AdminInformProfDistVoucherChgCmpResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "利金・分配金登録伝票作成サービスを取得に失敗しました。",
                l_response.errorInfo,
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

        //execute()メソッドをコールする
        try
        {
            l_response =
                (WEB3AdminInformProfDistVoucherChgCmpResponse)l_service.execute(
                    l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response =
                (WEB3AdminInformProfDistVoucherChgCmpResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request,
                "登録口座変更完了が失敗しました。", l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

        log.exiting(STR_METHOD_NAME);
        //レスポンスオブジェクトを返却する。
        return l_response;
    }

    /**
     * (登録口座取消確認)<BR>
     * 分配金振替口座登録取消確認画面を表示する。<BR>
     * <BR>
     * 利金・分配金登録伝票作成サービスを取得し、execute()メソッドをコールする。<BR>
     * @@param l_request - リクエストデータ
     * @@return WEB3AdminInformProfDistVoucherCancCnfResponse
     */
    public WEB3AdminInformProfDistVoucherCancCnfResponse registAccountCancCnf(
        WEB3AdminInformProfDistVoucherCancCnfRequest l_request)
    {
        final String STR_METHOD_NAME =
            "registAccountCancCnf(WEB3AdminInformProfDistVoucherCancCnfRequest)";
        log.entering(STR_METHOD_NAME);

        WEB3AdminInformProfDistVoucherCancCnfResponse l_response = null;
        WEB3AdminInformProfDistRegistVoucherMakeService l_service = null;

        //利金・分配金登録伝票作成サービスオブジェクトを取得
        try
        {
            l_service = (WEB3AdminInformProfDistRegistVoucherMakeService)Services.getService(
                WEB3AdminInformProfDistRegistVoucherMakeService.class);
        }
        catch (Exception l_ex)
        {
            //サービスで例外が発生した場合は、エラー情報をレスポンスメッセージに設定する
            l_response =
                (WEB3AdminInformProfDistVoucherCancCnfResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "利金・分配金登録伝票作成サービスを取得に失敗しました。",
                l_response.errorInfo,
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

        //execute()メソッドをコールする
        try
        {
            l_response =
                (WEB3AdminInformProfDistVoucherCancCnfResponse)l_service.execute(
                    l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response =
                (WEB3AdminInformProfDistVoucherCancCnfResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request,
                "登録口座取消確認が失敗しました。", l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

        log.exiting(STR_METHOD_NAME);
        //レスポンスオブジェクトを返却する。
        return l_response;
    }

    /**
     * (登録口座取消完了)<BR>
     * 分配金振替口座登録取消完了画面を表示する。<BR>
     * <BR>
     * 利金・分配金登録伝票作成サービスを取得し、execute()メソッドをコールする。<BR>
     * @@param l_request - リクエストデータ
     * @@return WEB3AdminInformProfDistVoucherCancCmpResponse
     */
    public WEB3AdminInformProfDistVoucherCancCmpResponse registAccountCancCmp(
        WEB3AdminInformProfDistVoucherCancCmpRequest l_request)
    {
        final String STR_METHOD_NAME =
            "registAccountCancCmp(WEB3AdminInformProfDistVoucherCancCmpRequest)";
        log.entering(STR_METHOD_NAME);

        WEB3AdminInformProfDistVoucherCancCmpResponse l_response = null;
        WEB3AdminInformProfDistRegistVoucherMakeService l_service = null;

        //利金・分配金登録伝票作成サービスオブジェクトを取得
        try
        {
            l_service = (WEB3AdminInformProfDistRegistVoucherMakeService)Services.getService(
                WEB3AdminInformProfDistRegistVoucherMakeService.class);
        }
        catch (Exception l_ex)
        {
            //サービスで例外が発生した場合は、エラー情報をレスポンスメッセージに設定する
            l_response =
                (WEB3AdminInformProfDistVoucherCancCmpResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "利金・分配金登録伝票作成サービスを取得に失敗しました。",
                l_response.errorInfo,
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

        //execute()メソッドをコールする
        try
        {
            l_response =
                (WEB3AdminInformProfDistVoucherCancCmpResponse)l_service.execute(
                    l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response =
                (WEB3AdminInformProfDistVoucherCancCmpResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request,
                "登録口座取消完了が失敗しました。", l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

        log.exiting(STR_METHOD_NAME);
        //レスポンスオブジェクトを返却する。
        return l_response;
    }

}
@
