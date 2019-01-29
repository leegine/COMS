head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.59.19;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8704d7ec30c0c9d;
filename	WEB3ToSuccSettingListHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 連続設定一覧ハンドラ(WEB3ToSuccSettingListHandler.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/10/17 呉艶飛(中訊) 新規作成
*/
package webbroker3.triggerorder.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.triggerorder.message.WEB3SuccSettingListRequest;
import webbroker3.triggerorder.message.WEB3SuccSettingListResponse;
import webbroker3.triggerorder.service.delegate.WEB3ToSuccSettingListService;
import webbroker3.util.WEB3LogUtility;

/**
 * (連続設定一覧ハンドラ)<BR>
 * 連続設定一覧ハンドラクラス<BR>
 * @@author 呉艶飛
 * @@version 1.0
 */
public class WEB3ToSuccSettingListHandler implements MessageHandler 
{
    /**
     * ログ出力ユーティリティ。
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3ToSuccSettingListHandler.class);
    
    /**
     * @@roseuid 4348ECB6037A
     */
    public WEB3ToSuccSettingListHandler() 
    {
     
    }
    
    /**
     * (get一覧画面)<BR>
     * 連続設定一覧画面表示処理を行う。<BR>
     * <BR>
     * 連続設定一覧サービスImplを取得し、<BR>
     * execute()メソッドをコールする。<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * 連続設定一覧リクエストオブジェクト<BR>
     * @@return WEB3SuccSettingListResponse
     * @@roseuid 431CFF6302F1
     */
    public WEB3SuccSettingListResponse getListScreen(WEB3SuccSettingListRequest l_request) 
    {
        final String STR_METHOD_NAME = "getListScreen(WEB3SuccSettingListRequest l_request)";
        log.entering(STR_METHOD_NAME);
       
        WEB3SuccSettingListResponse l_response = null;
        WEB3ToSuccSettingListService l_service = null;       
        // 連続設定一覧画面表示処理を行う。
        // 連続設定一覧サービスImplを取得し、execute()メソッドをコールする
        try
        {
            l_service =
                (WEB3ToSuccSettingListService) Services.getService(WEB3ToSuccSettingListService.class);
        }
        // サービスで例外が発生した場合は、エラー情報をレスポンスメッセージに設定する
        catch (Exception e)
        {
            l_response =(WEB3SuccSettingListResponse) l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;    
            log.error(l_request,
                "連続設定一覧サービスの取得に失敗しました。",
                l_response.errorInfo,e);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

        try
        {
            l_response =
                (WEB3SuccSettingListResponse) l_service.execute(l_request);
        }
        catch (WEB3BaseException e)
        {
            l_response =
                (WEB3SuccSettingListResponse) l_request.createResponse();
            l_response.errorInfo = e.getErrorInfo();
            log.error(l_request, "連続設定一覧に失敗しました。", e);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        catch (WEB3BaseRuntimeException l_bre)
        {
            l_response =
                (WEB3SuccSettingListResponse) l_request.createResponse();
            l_response.errorInfo = l_bre.getErrorInfo();
            log.error(l_request, "連続設定一覧に失敗しました。", l_bre);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

        log.exiting(STR_METHOD_NAME);

        // レスポンスオブジェクトを返却する。
        return l_response;
    }
}
@
