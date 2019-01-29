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
filename	WEB3AdminDirSecBatoTroubleFlagUpdateHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright           : (株)大和総研 証券ソリューションシステム第二部
File Name           : 管理者電子鳩障害フラグ更新ハンドラ(WEB3AdminDirSecBatoTroubleFlagUpdateHandler.java)
Author Name         : Daiwa Institute of Research
Revision History    : 2008/04/28 柴双紅(中訊) 新規作成 モデルNo.117
*/

package webbroker3.dirsec.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.dirsec.message.WEB3AdminDirSecWorkingCompleteRequest;
import webbroker3.dirsec.message.WEB3AdminDirSecWorkingCompleteResponse;
import webbroker3.dirsec.message.WEB3AdminDirSecWorkingConfirmRequest;
import webbroker3.dirsec.message.WEB3AdminDirSecWorkingConfirmResponse;
import webbroker3.dirsec.message.WEB3AdminDirSecWorkingListRequest;
import webbroker3.dirsec.message.WEB3AdminDirSecWorkingListResponse;
import webbroker3.dirsec.service.delegate.WEB3AdminDirSecBatoTroubleFlagUpdateService;
import webbroker3.util.WEB3LogUtility;

/**
 * (管理者電子鳩障害フラグ更新ハンドラ)<BR>
 * 管理者電子鳩障害フラグ更新ハンドラクラス。<BR>
 * <BR>
 * @@author 柴双紅
 * @@version 1.0
 */
public class WEB3AdminDirSecBatoTroubleFlagUpdateHandler implements MessageHandler
{

    /**
     * ログ出力ユーティリティ。<BR>
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(
        WEB3AdminDirSecBatoTroubleFlagUpdateHandler.class);

    /**
     * @@roseuid 481155FD0353
     */
    public WEB3AdminDirSecBatoTroubleFlagUpdateHandler()
    {

    }

    /**
     * (get一覧画面)<BR>
     * 稼動状況一覧画面表示処理を行う。<BR>
     * <BR>
     * 管理者電子鳩障害フラグ更新Implを取得し、<BR>
     * 　@execute()メソッドをコールする。<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * 電子鳩システム会社部店プリファ@レンステーブルレコード検索結果リクエストクラス。<BR>
     * @@return WEB3AdminDirSecWorkingListResponse
     * @@roseuid 47C3700A0002
     */
    public WEB3AdminDirSecWorkingListResponse getListScreen(WEB3AdminDirSecWorkingListRequest l_request)
    {
        final String STR_METHOD_NAME = "getListScreen(WEB3AdminDirSecWorkingListRequest)";
        log.entering(STR_METHOD_NAME);

        WEB3AdminDirSecBatoTroubleFlagUpdateService l_service;
        WEB3AdminDirSecWorkingListResponse l_response;

        try
        {
            l_service = (WEB3AdminDirSecBatoTroubleFlagUpdateService)Services.getService(
                WEB3AdminDirSecBatoTroubleFlagUpdateService.class);
        }
        catch (Exception l_ex)
        {
            l_response = (WEB3AdminDirSecWorkingListResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(l_request,
                "管理者電子鳩障害フラグ更新の取得に失敗しました。",
                l_response.errorInfo,
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

        try
        {
            l_response = (WEB3AdminDirSecWorkingListResponse)l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response = (WEB3AdminDirSecWorkingListResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request,
                "稼動状況一覧画面表示処理に失敗しました。",
                l_response.errorInfo,
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        catch (WEB3BaseRuntimeException l_ex)
        {
            l_response = (WEB3AdminDirSecWorkingListResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request,
                "稼動状況一覧画面表示処理に失敗しました。",
                l_response.errorInfo,
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (validate変更確認画面)<BR>
     * 稼動状況変更確認画面表示処理を行う。<BR>
     * <BR>
     * 管理者電子鳩障害フラグ更新Implを取得し、<BR>
     * execute()メソッドをコールする。<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * 管理者・稼動状況更新確認リクエストクラス。<BR>
     * @@return WEB3AdminDirSecWorkingConfirmResponse
     * @@roseuid 47C37045007B
     */
    public WEB3AdminDirSecWorkingConfirmResponse validateChangeConfirmScreen(
        WEB3AdminDirSecWorkingConfirmRequest l_request)
    {
        final String STR_METHOD_NAME = "validateChangeConfirmScreen(WEB3AdminDirSecWorkingConfirmRequest)";
        log.entering(STR_METHOD_NAME);

        WEB3AdminDirSecBatoTroubleFlagUpdateService l_service;
        WEB3AdminDirSecWorkingConfirmResponse l_response;

        try
        {
            l_service = (WEB3AdminDirSecBatoTroubleFlagUpdateService)Services.getService(
                WEB3AdminDirSecBatoTroubleFlagUpdateService.class);
        }
        catch (Exception l_ex)
        {
            l_response = (WEB3AdminDirSecWorkingConfirmResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(l_request,
                "管理者電子鳩障害フラグ更新の取得に失敗しました。",
                l_response.errorInfo,
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

        try
        {
            l_response = (WEB3AdminDirSecWorkingConfirmResponse)l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response = (WEB3AdminDirSecWorkingConfirmResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request,
                "稼動状況変更確認画面表示処理に失敗しました。",
                l_response.errorInfo,
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        catch (WEB3BaseRuntimeException l_ex)
        {
            l_response = (WEB3AdminDirSecWorkingConfirmResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request,
                "稼動状況変更確認画面表示処理に失敗しました。",
                l_response.errorInfo,
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (submit変更完了画面)<BR>
     * 稼動状況更新完了画面表示処理を行う。<BR>
     * <BR>
     * 管理者電子鳩障害フラグ更新Implを取得し、<BR>
     * execute()メソッドをコールする。<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * 管理者・稼動状況更新完了リクエストクラス。<BR>
     * @@return WEB3AdminDirSecWorkingCompleteResponse
     * @@roseuid 47C3705A005A
     */
    public WEB3AdminDirSecWorkingCompleteResponse submitChangeCompleteScreen(
        WEB3AdminDirSecWorkingCompleteRequest l_request)
    {
        final String STR_METHOD_NAME = "submitChangeCompleteScreen(WEB3AdminDirSecWorkingCompleteRequest)";
        log.entering(STR_METHOD_NAME);

        WEB3AdminDirSecBatoTroubleFlagUpdateService l_service;
        WEB3AdminDirSecWorkingCompleteResponse l_response;

        try
        {
            l_service = (WEB3AdminDirSecBatoTroubleFlagUpdateService)Services.getService(
                WEB3AdminDirSecBatoTroubleFlagUpdateService.class);
        }
        catch (Exception l_ex)
        {
            l_response = (WEB3AdminDirSecWorkingCompleteResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(l_request,
                "管理者電子鳩障害フラグ更新の取得に失敗しました。",
                l_response.errorInfo,
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

        try
        {
            l_response = (WEB3AdminDirSecWorkingCompleteResponse)l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response = (WEB3AdminDirSecWorkingCompleteResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request,
                "稼動状況更新完了画面表示処理に失敗しました。",
                l_response.errorInfo,
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        catch (WEB3BaseRuntimeException l_ex)
        {
            l_response = (WEB3AdminDirSecWorkingCompleteResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request,
                "稼動状況更新完了画面表示処理に失敗しました。",
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
