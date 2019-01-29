head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.22.08;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3AdminAioSLRegistProductReferenceHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 担保登録銘柄照会ハンドラクラス(WEB3AdminAioSLRegistProductReferenceHandler.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/09/13 張騰宇 (中訊) 新規作成 モデル760
*/

package webbroker3.aio.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.aio.message.WEB3AdminSLProductRegiListRequest;
import webbroker3.aio.message.WEB3AdminSLProductRegiListResponse;
import webbroker3.aio.service.delegate.WEB3AdminAioSLRegistProductReferenceService;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.util.WEB3LogUtility;

/**
 * (担保登録銘柄照会ハンドラ)<BR>
 * 担保登録銘柄照会ハンドラクラス<BR>
 *
 * @@author 張騰宇
 * @@version 1.0
 */
public class WEB3AdminAioSLRegistProductReferenceHandler implements MessageHandler
{
    /**
     * ログ出力ユーティリティ。
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminAioSLRegistProductReferenceHandler.class);

    /**
     * (get担保登録銘柄一覧)<BR>
     * 登録されている担保銘柄を表示する処理をコールする。 <BR>
     * <BR>
     * １）　@担保登録銘柄照会サービスを取得し、executeメソッドをコールする。<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * リクエストデータ<BR>
     * @@return WEB3AdminSLProductRegiListResponse
     */
    public WEB3AdminSLProductRegiListResponse getSLRegiProductList(
        WEB3AdminSLProductRegiListRequest l_request)
    {
        final String STR_METHOD_NAME =
            "getSLRegiProductList(WEB3AdminSLProductRegiListRequest)";
        log.entering(STR_METHOD_NAME);

        //担保登録銘柄照会サービスを取得
        WEB3AdminAioSLRegistProductReferenceService l_service = null;
        WEB3AdminSLProductRegiListResponse l_response = null;
        try
        {
            l_service =
                (WEB3AdminAioSLRegistProductReferenceService)
                Services.getService(WEB3AdminAioSLRegistProductReferenceService.class);
        }
        // サービスで例外が発生した場合は、エラー情報をレスポンスメッセージに設定する
        catch (Exception l_ex)
        {
            l_response =
                (WEB3AdminSLProductRegiListResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "担保登録銘柄照会サービスを取得に失敗しました。",
                l_response.errorInfo,
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        try
        {
            //execute()メソッドをコールする
            l_response = (WEB3AdminSLProductRegiListResponse)l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response =
                (WEB3AdminSLProductRegiListResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request,
                "登録されている担保銘柄を表示する処理が失敗しました。",
                l_response.errorInfo,
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        catch (WEB3BaseRuntimeException l_ex)
        {
            l_response = (WEB3AdminSLProductRegiListResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request,
                "登録されている担保銘柄を表示する処理が失敗しました。",
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
