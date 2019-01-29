head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.06.44.51;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7984d7db5d91d86;
filename	WEB3AdminSrvRegiOtherOrgIdListHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright           : (株)大和総研 証券ソリューションシステム第二部
File Name           : サービス利用管理者外部連携ID一覧照会ﾊﾝﾄﾞﾗ(WEB3AdminSrvRegiOtherOrgIdListHandler.java)
Author Name         : Daiwa Institute of Research
Revision History    : 2008/03/10 柴双紅(中訊) 新規作成 モデルNo.335
*/

package webbroker3.srvregi.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.srvregi.message.WEB3AdminSrvRegiOtherOrgIdListReferenceRequest;
import webbroker3.srvregi.message.WEB3AdminSrvRegiOtherOrgIdListReferenceResponse;
import webbroker3.srvregi.message.WEB3AdminSrvRegiOtherOrgIdListSearchRequest;
import webbroker3.srvregi.message.WEB3AdminSrvRegiOtherOrgIdListSearchResponse;
import webbroker3.srvregi.service.delegate.WEB3AdminSrvRegiOtherOrgIdListService;
import webbroker3.util.WEB3LogUtility;

/**
 * (サービス利用管理者外部連携ID一覧照会ﾊﾝﾄﾞﾗ)<BR>
 * サービス利用管理者外部連携ID一覧照会ﾊﾝﾄﾞﾗクラス<BR>
 * <BR>
 * @@author 柴双紅
 * @@version 1.0
 */
public class WEB3AdminSrvRegiOtherOrgIdListHandler implements MessageHandler
{

    /**
     * ログ出力ユーティリティ
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(
            WEB3AdminSrvRegiOtherOrgIdListHandler.class);

    /**
     * @@roseuid 47D11137039C
     */
    public WEB3AdminSrvRegiOtherOrgIdListHandler()
    {

    }

    /**
     * (外部連携ID一覧照会)<BR>
     * サービス利用管理者外部連携ID一覧照会処理を行う。<BR>
     * <BR>
     * サービス利用管理者外部連携ID一覧照会サービスを取得し、<BR>
     * execute()メソッドをコールする。<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * サービス利用管理者外部連携ID一覧照会リクエスト　@オブジェクト<BR>
     * @@return WEB3AdminSrvRegiOtherOrgIdListReferenceResponse
     * @@roseuid 47B944EC0303
     */
    public WEB3AdminSrvRegiOtherOrgIdListReferenceResponse otherOrgIdListReference(
        WEB3AdminSrvRegiOtherOrgIdListReferenceRequest l_request)
    {
        final String STR_METHOD_NAME =
            "otherOrgIdListReference(WEB3AdminSrvRegiOtherOrgIdListReferenceRequest)";
        log.entering(STR_METHOD_NAME);

        WEB3AdminSrvRegiOtherOrgIdListReferenceResponse l_response = null;
        WEB3AdminSrvRegiOtherOrgIdListService l_service = null;

        try
        {
            l_service =
                (WEB3AdminSrvRegiOtherOrgIdListService)Services.getService(
                    WEB3AdminSrvRegiOtherOrgIdListService.class);
        }
        catch (Exception l_ex)
        {
            l_response =
                (WEB3AdminSrvRegiOtherOrgIdListReferenceResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "サービス利用管理者外部連携ID一覧照会ｻｰﾋﾞｽを取得に失敗しました。",
                l_response.errorInfo,
                l_ex);

            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

        try
        {
            l_response =
                (WEB3AdminSrvRegiOtherOrgIdListReferenceResponse)l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response =
                (WEB3AdminSrvRegiOtherOrgIdListReferenceResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();

            log.error(
                l_request,
                "サービス利用管理者外部連携ID一覧照会処理にエラーが発生しました。",
                l_response.errorInfo,
                l_ex);

            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        catch (WEB3BaseRuntimeException l_ex)
        {
            l_response =
                (WEB3AdminSrvRegiOtherOrgIdListReferenceResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request,
                "サービス利用管理者外部連携ID一覧照会処理にエラーが発生しました。",
                l_response.errorInfo,
                l_ex);

            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (外部連携ID一覧条件)<BR>
     * サービス利用管理者外部連携ID一覧条件入力画面処理を行う。<BR>
     * <BR>
     * サービス利用管理者外部連携ID一覧照会サービスを取得し、<BR>
     * execute()メソッドをコールする。<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * サービス利用管理者外部連携ID一覧条件入力リクエスト　@オブジェクト<BR>
     * @@return WEB3AdminSrvRegiOtherOrgIdListSearchResponse
     * @@roseuid 47B9458601C0
     */
    public WEB3AdminSrvRegiOtherOrgIdListSearchResponse otherOrgIdListSearch(
        WEB3AdminSrvRegiOtherOrgIdListSearchRequest l_request)
    {
        final String STR_METHOD_NAME =
            "otherOrgIdListSearch(WEB3AdminSrvRegiOtherOrgIdListSearchRequest)";
        log.entering(STR_METHOD_NAME);

        WEB3AdminSrvRegiOtherOrgIdListSearchResponse l_response = null;
        WEB3AdminSrvRegiOtherOrgIdListService l_service = null;

        try
        {
            l_service =
                (WEB3AdminSrvRegiOtherOrgIdListService)Services.getService(
                    WEB3AdminSrvRegiOtherOrgIdListService.class);
        }
        catch (Exception l_ex)
        {
            l_response =
                (WEB3AdminSrvRegiOtherOrgIdListSearchResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "サービス利用管理者外部連携ID一覧照会ｻｰﾋﾞｽを取得に失敗しました。",
                l_response.errorInfo,
                l_ex);

            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

        try
        {
            l_response =
                (WEB3AdminSrvRegiOtherOrgIdListSearchResponse)l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response =
                (WEB3AdminSrvRegiOtherOrgIdListSearchResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();

            log.error(
                l_request,
                "サービス利用管理者外部連携ID一覧条件入力画面表示処理にエラーが発生しました。",
                l_response.errorInfo,
                l_ex);

            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        catch (WEB3BaseRuntimeException l_ex)
        {
            l_response =
                (WEB3AdminSrvRegiOtherOrgIdListSearchResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request,
                "サービス利用管理者外部連携ID一覧条件入力画面表示処理にエラーが発生しました。",
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
