head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.10.02.12.35;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8644d782f8a12fc;
filename	WEB3AdminAccInfoAccEstablishSearchHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 新規口座開設検索ﾊﾝﾄﾞﾗ(WEB3AdminAccInfoAccEstablishSearchHandler.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/10/09  何文敏(中訊) 新規作成
*/

package webbroker3.accountinfo.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.accountinfo.message.WEB3AdminAccInfoAccEstablishSearchDLRequest;
import webbroker3.accountinfo.message.WEB3AdminAccInfoAccEstablishSearchDLResponse;
import webbroker3.accountinfo.message.WEB3AdminAccInfoAccEstablishSearchInputRequest;
import webbroker3.accountinfo.message.WEB3AdminAccInfoAccEstablishSearchInputResponse;
import webbroker3.accountinfo.message.WEB3AdminAccInfoAccEstablishSearchListRequest;
import webbroker3.accountinfo.message.WEB3AdminAccInfoAccEstablishSearchListResponse;
import webbroker3.accountinfo.service.delegate.WEB3AdminAccInfoAccEstablishSearchService;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.util.WEB3LogUtility;

/**
 * (管理者お客様情報新規口座開設・口座移管・ログインロック顧客検索ﾊﾝﾄﾞﾗ)<BR>
 * 管理者お客様情報新規口座開設・口座移管・ログインロック顧客検索ﾊﾝﾄﾞﾗ<BR>
 * <BR>
 * @@author 何文敏
 * @@version 1.0
 */

public class WEB3AdminAccInfoAccEstablishSearchHandler implements MessageHandler
{
    /**
     * ログ出力ユーティリティ。
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminAccInfoAccEstablishSearchHandler.class);

    /**
     * @@roseuid 418F3A0F0251
     */
    public WEB3AdminAccInfoAccEstablishSearchHandler()
    {

    }

    /**
     * (入力画面表示)<BR>
     * 新規口座開設・口座移管・ログインロック顧客検索入力画面表示処理を行う。<BR>
     * <BR>
     * 管理者お客様情報新規口座開設・口座移管・ログインロック顧客検索サービスImplを取得し、<BR>
     * execute()メソッドをコールする。<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * 管理者お客様情報新規口座開設・口座移管・ログインロック顧客検索入力リクエストオブジェクト
     * @@return WEB3AdminAccInfoAccOpenLockSearchInputResponse
     */
    public WEB3AdminAccInfoAccEstablishSearchInputResponse getInputScreen(
        WEB3AdminAccInfoAccEstablishSearchInputRequest l_request)
    {
        final String STR_METHOD_NAME = " getInputScreen(WEB3AdminAccInfoAccOpenLockSearchInputRequest)";
        log.entering(STR_METHOD_NAME);

        WEB3AdminAccInfoAccEstablishSearchInputResponse l_response = null;
        WEB3AdminAccInfoAccEstablishSearchService l_service = null;

        try
        {
            l_service =
                (WEB3AdminAccInfoAccEstablishSearchService)Services.getService(
                        WEB3AdminAccInfoAccEstablishSearchService.class);
        }

        //サービスで例外が発生した場合は、エラー情報をレスポンスメッセージに設定する
        catch (Exception l_ex)
        {
            l_response = (WEB3AdminAccInfoAccEstablishSearchInputResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "新規口座開設検索ｻｰﾋﾞｽの取得に失敗しました。",
                l_response.errorInfo, l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        
        //管理者お客様情報顧客基本情報問合せサービス.execute()メソッドをコールする
        try
        {
            l_response = (WEB3AdminAccInfoAccEstablishSearchInputResponse)l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response = (WEB3AdminAccInfoAccEstablishSearchInputResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request, "新規口座開設検索ｻｰﾋﾞｽに失敗しました。", l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        log.exiting(STR_METHOD_NAME);
        //レスポンスオブジェクトを返却する。
        return l_response;
    }

    /**
     * (一覧画面表示)<BR>
     * 新規口座開設・口座移管・ログインロック顧客検索一覧画面表示処理を行う。<BR>
     * <BR>
     * 管理者お客様情報新規口座開設・口座移管・ログインロック顧客検索サービスImplを取得し、<BR>
     * execute()メソッドをコールする。<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * 管理者お客様情報新規口座開設・口座移管・ログインロック顧客検索一覧リクエストオブジェクト
     * @@return WEB3AdminAccInfoAccOpenLockSearchListResponse
     */
    public WEB3AdminAccInfoAccEstablishSearchListResponse getListScreen(
        WEB3AdminAccInfoAccEstablishSearchListRequest l_request)
    {
        final String STR_METHOD_NAME = " getInputScreen(WEB3AdminAccInfoAccOpenLockSearchInputRequest)";
        log.entering(STR_METHOD_NAME);

        WEB3AdminAccInfoAccEstablishSearchListResponse l_response = null;
        WEB3AdminAccInfoAccEstablishSearchService l_service = null;

        try
        {
            l_service =
                (WEB3AdminAccInfoAccEstablishSearchService)Services.getService(
                        WEB3AdminAccInfoAccEstablishSearchService.class);
        }

        //サービスで例外が発生した場合は、エラー情報をレスポンスメッセージに設定する
        catch (Exception l_ex)
        {
            l_response = (WEB3AdminAccInfoAccEstablishSearchListResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "新規口座開設検索ｻｰﾋﾞｽの取得に失敗しました。",
                l_response.errorInfo, l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

        //管理者お客様情報顧客基本情報問合せサービス.execute()メソッドをコールする
        try
        {
            l_response = (WEB3AdminAccInfoAccEstablishSearchListResponse)l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response = (WEB3AdminAccInfoAccEstablishSearchListResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request, "新規口座開設検索ｻｰﾋﾞｽに失敗しました。", l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        log.exiting(STR_METHOD_NAME);
        //レスポンスオブジェクトを返却する。
        return l_response;
    }

    /**
     * (getダウンロードファ@イル )<BR>
     * 新規口座開設・口座移管・ログインロック顧客検索getダウンロードファ@イル処理を行う。<BR>
     * <BR>
     * 管理者お客様情報新規口座開設・口座移管・ログインロック顧客検索サービスImplを取得し、<BR>
     * execute()メソッドをコールする。<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * 管理者お客様情報新規口座開設・口座移管・ログインロック顧客検索ダウンロードリクエストオブジェクト
     * @@return WEB3AdminAccInfoAccOpenLockSearchDLResponse
     */
    public WEB3AdminAccInfoAccEstablishSearchDLResponse getDownLoadFile(
        WEB3AdminAccInfoAccEstablishSearchDLRequest l_request)
    {
        final String STR_METHOD_NAME = " getInputScreen(WEB3AdminAccInfoAccOpenLockSearchInputRequest)";
        log.entering(STR_METHOD_NAME);

        WEB3AdminAccInfoAccEstablishSearchDLResponse l_response = null;
        WEB3AdminAccInfoAccEstablishSearchService l_service = null;

        try
        {
            l_service =
                (WEB3AdminAccInfoAccEstablishSearchService)Services.getService(
                        WEB3AdminAccInfoAccEstablishSearchService.class);
        }

        //サービスで例外が発生した場合は、エラー情報をレスポンスメッセージに設定する
        catch (Exception l_ex)
        {
            l_response = (WEB3AdminAccInfoAccEstablishSearchDLResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "新規口座開設検索ｻｰﾋﾞｽの取得に失敗しました。",
                l_response.errorInfo, l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

        //管理者お客様情報顧客基本情報問合せサービス.execute()メソッドをコールする
        try
        {
            l_response = (WEB3AdminAccInfoAccEstablishSearchDLResponse)l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response = (WEB3AdminAccInfoAccEstablishSearchDLResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request, "新規口座開設検索ｻｰﾋﾞｽに失敗しました。", l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        log.exiting(STR_METHOD_NAME);
        //レスポンスオブジェクトを返却する。
        return l_response;
    }

}
@
