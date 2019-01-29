head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.59.07;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8704d7ec30c0c9d;
filename	WEB3ToSuccAdditionalContentsSelectHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 追加内容選択ハンドラ(WEB3ToSuccAdditionalContentsSelectHandler.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/10/13 呉艶飛(中訊) 新規作成
*/
package webbroker3.triggerorder.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;


import webbroker3.triggerorder.message.WEB3SuccAdditionalContentSelectRequest;
import webbroker3.triggerorder.message.WEB3SuccAdditionalContentSelectResponse;
import webbroker3.triggerorder.service.delegate.WEB3ToSuccAdditionalContentsSelectService;

import webbroker3.util.WEB3LogUtility;

/**
 * (追加内容選択ハンドラ)<BR>
 * 追加内容選択ハンドラクラス
 * @@author 呉艶飛
 * @@version 1.0
 */
public class WEB3ToSuccAdditionalContentsSelectHandler implements MessageHandler 
{
    /**
     * ログ出力ユーティリティ。
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3ToSuccAdditionalContentsSelectHandler.class);
    
    /**
     * @@roseuid 4348ECB7000F
     */
    public WEB3ToSuccAdditionalContentsSelectHandler() 
    {
     
    }
    
    /**
     * (get選択画面)<BR>
     * 追加内容選択画面表示処理を行う。<BR>
     * <BR>
     * 追加内容選択サービスImplを取得し、<BR>
     * execute()メソッドをコールする。<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * 追加内容選択リクエストオブジェクト<BR>
     * @@return WEB3SuccAdditionalContentSelectResponse
     * @@roseuid 431D17860078
     */
    public WEB3SuccAdditionalContentSelectResponse getSelectScreen(WEB3SuccAdditionalContentSelectRequest l_request) 
    {
        final String STR_METHOD_NAME = "getSelectScreen(WEB3SuccAdditionalContentSelectRequest l_request)";
        log.entering(STR_METHOD_NAME);
       
        WEB3SuccAdditionalContentSelectResponse l_response = null;
        WEB3ToSuccAdditionalContentsSelectService l_service = null;       
        // 追加内容選択サービスImplを取得し、
        // 処理結果であるレスポンスオブジェクトを取得する。
        try
        {
            l_service =
                (WEB3ToSuccAdditionalContentsSelectService) Services.getService(WEB3ToSuccAdditionalContentsSelectService.class);
        }
        // サービスで例外が発生した場合は、エラー情報をレスポンスメッセージに設定する
        catch (Exception e)
        {
            l_response =(WEB3SuccAdditionalContentSelectResponse) l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;    
            log.error(l_request,
                "追加内容選択サービスの取得に失敗しました。",
                l_response.errorInfo,e);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

        try
        {
            l_response =
                (WEB3SuccAdditionalContentSelectResponse) l_service.execute(l_request);
        }
        catch (WEB3BaseException e)
        {
            l_response =
                (WEB3SuccAdditionalContentSelectResponse) l_request.createResponse();
            l_response.errorInfo = e.getErrorInfo();
            log.error(l_request, "追加内容選択に失敗しました。", e);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        catch (WEB3BaseRuntimeException l_bre)
        {
            l_response =
                (WEB3SuccAdditionalContentSelectResponse) l_request.createResponse();
            l_response.errorInfo = l_bre.getErrorInfo();
            log.error(l_request, "追加内容選択に失敗しました。", l_bre);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

        log.exiting(STR_METHOD_NAME);

        // レスポンスオブジェクトを返却する。
        return l_response;
    }
}
@
