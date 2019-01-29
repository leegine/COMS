head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.59.40;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8704d7ec30c0c9d;
filename	WEB3ToSuccSettingContentsConfirmHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 設定内容確認ハンドラ(WEB3ToSuccSettingContentsConfirmHandler)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/10/1７ 李俊(中訊) 新規作成
*/
package webbroker3.triggerorder.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.triggerorder.message.WEB3SuccSettingContentConfirmRequest;
import webbroker3.triggerorder.message.WEB3SuccSettingContentConfirmResponse;
import webbroker3.triggerorder.service.delegate.WEB3ToSuccSettingContentsConfirmService;
import webbroker3.util.WEB3LogUtility;

/**
 * (設定内容確認ハンドラ)<BR>
 * 設定内容確認ハンドラクラス<BR>
 * @@author 李俊 <BR>
 * @@version 1.0<BR>
 */
public class WEB3ToSuccSettingContentsConfirmHandler implements MessageHandler 
{
    /**
     * ログ出力ユーティリティ。
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3ToSuccSettingContentsConfirmHandler.class);
    
    
    /**
     * @@roseuid 434B592E0399
     */
    public WEB3ToSuccSettingContentsConfirmHandler() 
    {
     
    }
    
    /**
     * (get確認画面)<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * 設定内容確認リクエストオブジェクトBR>
     * @@return WEB3SuccSettingContentConfirmResponse
     * @@roseuid 431D18E2001A
     */
    public WEB3SuccSettingContentConfirmResponse getConfirmScreen(WEB3SuccSettingContentConfirmRequest l_request) 
    {
        final String STR_METHOD_NAME = "getConfirmScreen(WEB3SuccSettingContentConfirmRequest l_request)";
        log.entering(STR_METHOD_NAME);
       
        WEB3SuccSettingContentConfirmResponse l_response = null;
        WEB3ToSuccSettingContentsConfirmService l_service = null;       
        // 設定内容確認サービスImplを取得し、
        // 処理結果であるレスポンスオブジェクトを取得する。
        try
        {
            l_service =(WEB3ToSuccSettingContentsConfirmService) 
				Services.getService(WEB3ToSuccSettingContentsConfirmService.class);        
        }
        // サービスで例外が発生した場合は、エラー情報をレスポンスメッセージに設定する
        catch (Exception l_ex)
        {
            l_response =(WEB3SuccSettingContentConfirmResponse) l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;    
            log.error(l_request,
                "設定内容確認サービスの取得に失敗しました。",
                l_response.errorInfo,l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

        try
        {
            l_response =
                (WEB3SuccSettingContentConfirmResponse) l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response =
                (WEB3SuccSettingContentConfirmResponse) l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request, "設定内容確認に失敗しました。", l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        catch (WEB3BaseRuntimeException l_bre)
        {
            l_response =
                (WEB3SuccSettingContentConfirmResponse) l_request.createResponse();
            l_response.errorInfo = l_bre.getErrorInfo();
            log.error(l_request, "設定内容確認に失敗しました。", l_bre);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

        log.exiting(STR_METHOD_NAME);

        // レスポンスオブジェクトを返却する。
        return l_response;
    }
}
@
