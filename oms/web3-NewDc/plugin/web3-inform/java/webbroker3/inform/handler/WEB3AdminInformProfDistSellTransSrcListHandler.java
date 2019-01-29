head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.04.56.56;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	88c4d7eeee91bda;
filename	WEB3AdminInformProfDistSellTransSrcListHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright           : (株)大和総研 証券ソリューションシステム第二部
File Name           : 利金・分配金・売却代金振込先一覧サービスハンドラクラス(WEB3AdminInformProfDistSellTransSrcListHandler.java)
Author Name         : Daiwa Institute of Research
Revision History    : 2007/05/24 謝旋(中訊) 新規作成 モデルNo.046
*/

package webbroker3.inform.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.inform.message.WEB3AdminInformProfDistSellTransSrcInpRequest;
import webbroker3.inform.message.WEB3AdminInformProfDistSellTransSrcInpResponse;
import webbroker3.inform.message.WEB3AdminInformProfDistSellTransSrcListRequest;
import webbroker3.inform.message.WEB3AdminInformProfDistSellTransSrcListResponse;
import webbroker3.inform.service.delegate.WEB3AdminInformProfDistSellTransSrcListService;
import webbroker3.util.WEB3LogUtility;

/**
 * (利金・分配金・売却代金振込先一覧サービスハンドラクラス)<BR>
 * 利金・分配金・売却代金振込先一覧サービスハンドラクラス<BR>
 * @@author 謝旋
 * @@version 1.0
 */
public class WEB3AdminInformProfDistSellTransSrcListHandler implements MessageHandler
{
    /**
     * (ログ出力ユーティリティ)
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(
            WEB3AdminInformProfDistSellTransSrcListHandler.class);

    /**
     * @@roseuid 465593750165
     */
    public WEB3AdminInformProfDistSellTransSrcListHandler()
    {

    }

    /**
     * (入力画面表示)<BR>
     * 利金・分配金・売却代金振込先検索入力画面を表示する。<BR>
     * <BR>
     * 利金・分配金・売却代金振込先一覧サービスを取得し、<BR>
     * execute()メソッドをコールする。<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * リクエストデータ
     * @@return WEB3AdminInformProfDistSellTransSrcInpResponse
     * @@roseuid 461F4FF8036D
     */
    public WEB3AdminInformProfDistSellTransSrcInpResponse displayInputScreen(
        WEB3AdminInformProfDistSellTransSrcInpRequest l_request)
    {
        String STR_METHOD_NAME = " displayInputScreen(WEB3AdminInformProfDistSellTransSrcInpRequest)";
        log.entering(STR_METHOD_NAME);

        WEB3AdminInformProfDistSellTransSrcInpResponse l_response = null;
        WEB3AdminInformProfDistSellTransSrcListService l_service = null;

        //利金・分配金・売却代金振込先一覧サービスを取得
        try
        {
            l_service = (WEB3AdminInformProfDistSellTransSrcListService)Services.getService(
                WEB3AdminInformProfDistSellTransSrcListService.class);
        }
        catch (Exception l_ex)
        {
            //サービスで例外が発生した場合は、エラー情報をレスポンスメッセージに設定する
            l_response =
                (WEB3AdminInformProfDistSellTransSrcInpResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "利金・分配金・売却代金振込先一覧サービスを取得に失敗しました。",
                l_response.errorInfo,
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

        //連絡情報検索サービスオブジェクト.execute（）をコールし、
        //処理結果であるレスポンスオブジェクトを取得する。
        try
        {
            l_response =
                (WEB3AdminInformProfDistSellTransSrcInpResponse)l_service.execute(
                    l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response =
                (WEB3AdminInformProfDistSellTransSrcInpResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request, "利金・分配金・売却代金振込先検索入力に失敗しました。", l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

        log.exiting(STR_METHOD_NAME);

        //レスポンスオブジェクトを返却する。
        return l_response;
    }

    /**
     * (一覧画面表示)<BR>
     * 利金・分配金・売却代金振込先一覧画面を表示する。<BR>
     * <BR>
     * 利金・分配金・売却代金振込先一覧サービスを取得し、<BR>
     * execute()メソッドをコールする。<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * リクエストデータ
     * @@return WEB3AdminInformProfDistSellTransSrcListResponse
     * @@roseuid 461F500B0330
     */
    public WEB3AdminInformProfDistSellTransSrcListResponse displayListScreen(
        WEB3AdminInformProfDistSellTransSrcListRequest l_request)
    {
        String STR_METHOD_NAME = " displayListScreen(WEB3AdminInformProfDistSellTransSrcListRequest)";
        log.entering(STR_METHOD_NAME);

        WEB3AdminInformProfDistSellTransSrcListResponse l_response = null;
        WEB3AdminInformProfDistSellTransSrcListService l_service = null;

        //利金・分配金・売却代金振込先一覧サービスを取得
        try
        {
            l_service = (WEB3AdminInformProfDistSellTransSrcListService)Services.getService(
                WEB3AdminInformProfDistSellTransSrcListService.class);
        }
        catch (Exception l_ex)
        {
            //サービスで例外が発生した場合は、エラー情報をレスポンスメッセージに設定する
            l_response =
                (WEB3AdminInformProfDistSellTransSrcListResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "利金・分配金・売却代金振込先一覧サービスを取得に失敗しました。",
                l_response.errorInfo,
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

        //連絡情報検索サービスオブジェクト.execute（）をコールし、
        //処理結果であるレスポンスオブジェクトを取得する。
        try
        {
            l_response =
                (WEB3AdminInformProfDistSellTransSrcListResponse)l_service.execute(
                    l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response =
                (WEB3AdminInformProfDistSellTransSrcListResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request, "利金・分配金・売却代金振込先一覧に失敗しました。", l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

        log.exiting(STR_METHOD_NAME);

        //レスポンスオブジェクトを返却する。
        return l_response;
    }
}
@
