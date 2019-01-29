head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.06.44.30;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7984d7db5d91d86;
filename	WEB3AdminSrvRegiOtherOrgIdDownloadHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright           : (株)大和総研 証券ソリューションシステム第二部
File Name           : サービス利用管理者外部連携ID照会ﾀﾞｳﾝﾛｰﾄﾞﾊﾝﾄﾞﾗ(WEB3AdminSrvRegiOtherOrgIdDownloadHandler.java)
Author Name         : Daiwa Institute of Research
Revision History    : 2008/03/10 柴双紅(中訊) 新規作成 モデルNo.336
*/

package webbroker3.srvregi.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.srvregi.message.WEB3AdminSrvRegiOtherOrgIdDownloadRequest;
import webbroker3.srvregi.message.WEB3AdminSrvRegiOtherOrgIdDownloadResponse;
import webbroker3.srvregi.service.delegate.WEB3AdminSrvRegiOtherOrgIdDownloadService;
import webbroker3.util.WEB3LogUtility;

/**
 * (サービス利用管理者外部連携ID照会ﾀﾞｳﾝﾛｰﾄﾞﾊﾝﾄﾞﾗ)<BR>
 * サービス利用管理者外部連携ID照会ﾀﾞｳﾝﾛｰﾄﾞﾊﾝﾄﾞﾗクラス<BR>
 * <BR>
 * @@author 柴双紅
 * @@version 1.0
 */
public class WEB3AdminSrvRegiOtherOrgIdDownloadHandler implements MessageHandler
{

    /**
     * ログ出力ユーティリティ
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminSrvRegiOtherOrgIdDownloadHandler.class);

    /**
     * @@roseuid 47D11137008F
     */
    public WEB3AdminSrvRegiOtherOrgIdDownloadHandler()
    {

    }

    /**
     * (外部連携ID照会ﾀﾞｳﾝﾛｰﾄﾞ)<BR>
     * サービス利用管理者外部連携ID照会ﾀﾞｳﾝﾛｰﾄﾞ処理を行う。<BR>
     * <BR>
     * サービス利用管理者外部連携ID照会ﾀﾞｳﾝﾛｰﾄﾞサービスを取得し、<BR>
     * execute()メソッドをコールする。<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * サービス利用管理者外部連携ID照会ﾀﾞｳﾝﾛｰﾄﾞリクエスト　@オブジェクト<BR>
     * @@return WEB3AdminSrvRegiOtherOrgIdDownloadResponse
     * @@roseuid 47B944690201
     */
    public WEB3AdminSrvRegiOtherOrgIdDownloadResponse otherOrgIdDownload(
        WEB3AdminSrvRegiOtherOrgIdDownloadRequest l_request)
    {
        final String STR_METHOD_NAME =
            "otherOrgIdDownload(WEB3AdminSrvRegiOtherOrgIdDownloadRequest l_request)";
        log.entering(STR_METHOD_NAME);

        WEB3AdminSrvRegiOtherOrgIdDownloadResponse l_response = null;
        WEB3AdminSrvRegiOtherOrgIdDownloadService l_service = null;

        try
        {
            l_service = (WEB3AdminSrvRegiOtherOrgIdDownloadService)Services.getService(
                    WEB3AdminSrvRegiOtherOrgIdDownloadService.class);
        }
        catch (Exception l_ex)
        {
            l_response =
                (WEB3AdminSrvRegiOtherOrgIdDownloadResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(l_request,
                "サービス利用管理者外部連携ID照会ﾀﾞｳﾝﾛｰﾄﾞサービスを取得に失敗しました。",
                l_response.errorInfo,
                l_ex);

            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

        try
        {
            l_response =
                (WEB3AdminSrvRegiOtherOrgIdDownloadResponse)l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response =
                (WEB3AdminSrvRegiOtherOrgIdDownloadResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request, "サービス利用管理者外部連携ID照会の取得にエラーが発生しました。", l_ex);

            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        catch (WEB3BaseRuntimeException l_ex)
        {
            l_response =
                (WEB3AdminSrvRegiOtherOrgIdDownloadResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request,
                "サービス利用管理者外部連携ID照会の取得にエラーが発生しました。",
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
