head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.16;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3AdminDirSecAccRegVoucherStatUpdHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright           : (株)大和総研 証券ソリューションシステム第二部
File Name           : 管理者顧客情報登録伝票ステータス更新ハンドラ(WEB3AdminDirSecAccRegVoucherStatUpdHandler.java)
Author Name         : Daiwa Institute of Research
Revision History    : 2007/06/12  何文敏 (中訊) 新規作成 仕様変更 モデルNo.098
Revision History    : 2007/06/18 柴双紅 (中訊) 仕様変更 モデルNo.101
*/

package webbroker3.dirsec.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.dirsec.message.WEB3AdminDirSecAccRegVoucherSearchInpRequest;
import webbroker3.dirsec.message.WEB3AdminDirSecAccRegVoucherSearchInpResponse;
import webbroker3.dirsec.message.WEB3AdminDirSecAccRegVoucherSearchResRequest;
import webbroker3.dirsec.message.WEB3AdminDirSecAccRegVoucherSearchResResponse;
import webbroker3.dirsec.message.WEB3AdminDirSecAccRegVoucherStatUpdCompRequest;
import webbroker3.dirsec.message.WEB3AdminDirSecAccRegVoucherStatUpdCompResponse;
import webbroker3.dirsec.message.WEB3AdminDirSecAccRegVoucherStatUpdConfRequest;
import webbroker3.dirsec.message.WEB3AdminDirSecAccRegVoucherStatUpdConfResponse;
import webbroker3.dirsec.service.delegate.WEB3AdminDirSecAccRegVoucherStatUpdService;
import webbroker3.util.WEB3LogUtility;

/**
 * (管理者顧客情報登録伝票ステータス更新ハンドラ)<BR>
 * 管理者顧客情報登録伝票ステータス更新ハンドラクラス。<BR>
 * <BR>
 * @@author 何文敏
 * @@version 1.0
 */
public class WEB3AdminDirSecAccRegVoucherStatUpdHandler implements MessageHandler
{
    /**
     * ログ出力ユーティリティ。<BR>
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(
        WEB3AdminDirSecAccRegVoucherStatUpdHandler.class);

    /**
     * @@roseuid 466E0B6A03B5
     */
    public WEB3AdminDirSecAccRegVoucherStatUpdHandler()
    {

    }

    /**
     * (get検索画面)<BR>
     * 顧客情報登録伝票レコード検索画面表示処理を行う。<BR>
     * <BR>
     * 管理者顧客情報登録伝票ステータス更新サービスImplを取得し、<BR>
     * execute()メソッドをコールする。<BR>
     * <BR>
     * @@param l_request - (リクエストデータ)<BR>
     * 管理者・顧客情報登録伝票検索入力リクエストクラス。<BR>
     * @@return WEB3AdminDirSecAccRegVoucherSearchInpResponse
     * @@roseuid 46540181033C
     */
    public WEB3AdminDirSecAccRegVoucherSearchInpResponse getSearchScreen(
        WEB3AdminDirSecAccRegVoucherSearchInpRequest l_request)
    {
        final String STR_METHOD_NAME =
            "getSearchScreen(WEB3AdminDirSecAccRegVoucherSearchInpRequest)";
        log.entering(STR_METHOD_NAME);

        WEB3AdminDirSecAccRegVoucherSearchInpResponse l_response = null;
        WEB3AdminDirSecAccRegVoucherStatUpdService l_service = null;

        try
        {
            l_service =
                (WEB3AdminDirSecAccRegVoucherStatUpdService)Services.getService(
                    WEB3AdminDirSecAccRegVoucherStatUpdService.class);
        }
        catch (Exception l_ex)
        {
            l_response =
                (WEB3AdminDirSecAccRegVoucherSearchInpResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(l_request,
                "管理者顧客情報登録伝票ステータス更新サービス取得に失敗しました。",
                l_response.errorInfo,
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

        try
        {
            l_response =
                (WEB3AdminDirSecAccRegVoucherSearchInpResponse)l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response =
                (WEB3AdminDirSecAccRegVoucherSearchInpResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request,
                "顧客情報登録伝票レコード検索画面表示処理に失敗しました。",
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (get検索結果画面)<BR>
     * 顧客情報登録伝票レコード検索結果画面表示処理を行う。<BR>
     * <BR>
     * 管理者顧客情報登録伝票ステータス更新サービスImplを取得し、<BR>
     * execute()メソッドをコールする。<BR>
     * <BR>
     * @@param l_request - (リクエストデータ)<BR>
     * 管理者・顧客情報登録伝票検索結果リクエストクラス。<BR>
     * @@return WEB3AdminDirSecAccRegVoucherSearchResResponse
     * @@roseuid 46540235021C
     */
    public WEB3AdminDirSecAccRegVoucherSearchResResponse getSearchResultScreen(
        WEB3AdminDirSecAccRegVoucherSearchResRequest l_request)
    {
        final String STR_METHOD_NAME =
            "getSearchResultScreen(WEB3AdminDirSecAccRegVoucherSearchResRequest)";
        log.entering(STR_METHOD_NAME);

        WEB3AdminDirSecAccRegVoucherSearchResResponse l_response = null;
        WEB3AdminDirSecAccRegVoucherStatUpdService l_service = null;

        try
        {
            l_service =
                (WEB3AdminDirSecAccRegVoucherStatUpdService)Services.getService(
                    WEB3AdminDirSecAccRegVoucherStatUpdService.class);
        }
        catch (Exception l_ex)
        {
            l_response =
                (WEB3AdminDirSecAccRegVoucherSearchResResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(l_request,
                "管理者顧客情報登録伝票ステータス更新サービス取得に失敗しました。",
                l_response.errorInfo,
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

        try
        {
            l_response =
                (WEB3AdminDirSecAccRegVoucherSearchResResponse)l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response =
                (WEB3AdminDirSecAccRegVoucherSearchResResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request,
                "顧客情報登録伝票レコード検索結果画面表示処理に失敗しました。",
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (get更新確認画面)<BR>
     * 顧客情報登録伝票ステータス更新確認画面表示処理を行う。<BR>
     * <BR>
     * 管理者顧客情報登録伝票ステータス更新サービスImplを取得し、<BR>
     * execute()メソッドをコールする。<BR>
     * <BR>
     * @@param l_request - (リクエストデータ)<BR>
     * 管理者・顧客情報登録伝票ステータス更新確認リクエストクラス。<BR>
     * @@return WEB3AdminDirSecAccRegVoucherStatUpdConfResponse
     * @@roseuid 465402910341
     */
    public WEB3AdminDirSecAccRegVoucherStatUpdConfResponse getUpdateConfirmScreen(
        WEB3AdminDirSecAccRegVoucherStatUpdConfRequest l_request)
    {
        final String STR_METHOD_NAME =
            "getUpdateConfirmScreen(WEB3AdminDirSecAccRegVoucherStatUpdConfRequest)";
        log.entering(STR_METHOD_NAME);

        WEB3AdminDirSecAccRegVoucherStatUpdConfResponse l_response = null;
        WEB3AdminDirSecAccRegVoucherStatUpdService l_service = null;

        try
        {
            l_service =
                (WEB3AdminDirSecAccRegVoucherStatUpdService)Services.getService(
                    WEB3AdminDirSecAccRegVoucherStatUpdService.class);
        }
        catch (Exception l_ex)
        {
            l_response =
                (WEB3AdminDirSecAccRegVoucherStatUpdConfResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(l_request,
                "管理者顧客情報登録伝票ステータス更新サービス取得に失敗しました。",
                l_response.errorInfo,
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

        try
        {
            l_response =
                (WEB3AdminDirSecAccRegVoucherStatUpdConfResponse)l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response =
                (WEB3AdminDirSecAccRegVoucherStatUpdConfResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request,
                "顧客情報登録伝票ステータス更新確認画面表示処理に失敗しました。",
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (get更新完了画面)<BR>
     * 顧客情報登録伝票ステータス更新完了画面表示処理を行う。<BR>
     * <BR>
     * 管理者顧客情報登録伝票ステータス更新サービスImplを取得し、<BR>
     * execute()メソッドをコールする。<BR>
     * <BR>
     * @@param l_request - (リクエストデータ)<BR>
     * 管理者・顧客情報登録伝票ステータス更新完了リクエストクラス。<BR>
     * @@return WEB3AdminDirSecAccRegVoucherStatUpdCompResponse
     * @@roseuid 465402D00235
     */
    public WEB3AdminDirSecAccRegVoucherStatUpdCompResponse getUpdateCompleteScreen(
        WEB3AdminDirSecAccRegVoucherStatUpdCompRequest l_request)
    {
        final String STR_METHOD_NAME =
            "getUpdateCompleteScreen(WEB3AdminDirSecAccRegVoucherStatUpdCompRequest)";
        log.entering(STR_METHOD_NAME);

        WEB3AdminDirSecAccRegVoucherStatUpdCompResponse l_response = null;
        WEB3AdminDirSecAccRegVoucherStatUpdService l_service = null;

        try
        {
            l_service =
                (WEB3AdminDirSecAccRegVoucherStatUpdService)Services.getService(
                    WEB3AdminDirSecAccRegVoucherStatUpdService.class);
        }
        catch (Exception l_ex)
        {
            l_response =
                (WEB3AdminDirSecAccRegVoucherStatUpdCompResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(l_request,
                "管理者顧客情報登録伝票ステータス更新サービス取得に失敗しました。",
                l_response.errorInfo,
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

        try
        {
            l_response =
                (WEB3AdminDirSecAccRegVoucherStatUpdCompResponse)l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response =
                (WEB3AdminDirSecAccRegVoucherStatUpdCompResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request,
                "顧客情報登録伝票ステータス更新完了画面表示処理に失敗しました。",
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
}
@
