head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.10.02.12.56;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8644d782f8a12fc;
filename	WEB3AccInfoBaseInfoReferenceHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : お客様情報基本情報照会ハンドラ(WEB3AccInfoBaseInfoReferenceHandler.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/11/11 李海波 (中訊) 新規作成
*/

package webbroker3.accountinfo.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.accountinfo.message.WEB3AccInfoAccountBaseInfoReferenceRequest;
import webbroker3.accountinfo.message.WEB3AccInfoAccountBaseInfoReferenceResponse;
import webbroker3.accountinfo.service.delegate.WEB3AccInfoBaseInfoReferenceService;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.util.WEB3LogUtility;

/**
 * (お客様情報基本情報照会ハンドラ)<BR>
 * お客様情報基本情報照会ハンドラ<BR>
 * 
 * @@author 李海波
 * @@version 1.0
 */
public class WEB3AccInfoBaseInfoReferenceHandler implements MessageHandler 
{
    /**
     * ログ出力ユーティリティ。
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AccInfoBaseInfoReferenceHandler.class);
        
    /**
     * @@roseuid 418F3A1101A5
     */
    public WEB3AccInfoBaseInfoReferenceHandler() 
    {
     
    }
    
    /**
     * (基本情報照会)<BR>
     * 基本情報照会処理を行う。<BR>
     * <BR>
     * お客様情報基本情報照会サービスを取得し、execute()メソッドをコールする。<BR>
     * @@param l_request - お客様情報基本情報照会リクエストデータオブジェクト
     * @@return webbroker3.accountinfo.message.WEB3AccInfoAccountBaseInfoReferenceResponse
     * @@roseuid 4163B666033E
     */
    public WEB3AccInfoAccountBaseInfoReferenceResponse baseInfoReference(WEB3AccInfoAccountBaseInfoReferenceRequest l_request) 
    {
        final String STR_METHOD_NAME = " baseInfoReference(WEB3AccInfoAccountBaseInfoReferenceRequest)";
        log.entering(STR_METHOD_NAME);
        
        WEB3AccInfoAccountBaseInfoReferenceResponse l_response = null;
        WEB3AccInfoBaseInfoReferenceService l_service = null;
        
        try
        {
            l_service = 
                (WEB3AccInfoBaseInfoReferenceService)Services.getService(
                    WEB3AccInfoBaseInfoReferenceService.class);
        }
        //サービスで例外が発生した場合は、エラー情報をレスポンスメッセージに設定する
        catch (Exception l_ex)
        {
            l_response = (WEB3AccInfoAccountBaseInfoReferenceResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "お客様情報基本情報照会サービスの取得に失敗しました。",
                l_response.errorInfo,l_ex);
            return l_response;      
        }
        
        //お客様情報基本情報照会サービス.execute()メソッドをコールする
        try
        {
            l_response = (WEB3AccInfoAccountBaseInfoReferenceResponse)l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response = (WEB3AccInfoAccountBaseInfoReferenceResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request, "お客様情報基本情報照会サービスに失敗しました。", l_ex);
            return l_response;
        }
        
        log.exiting(STR_METHOD_NAME);
        //レスポンスオブジェクトを返却する。
        return l_response;
    }
}
@
