head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.23.26;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3AioSecurityTransferForceHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 証券振替強制ハンドラ(WEB3AioSecurityTransferForceHandler)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/12/08 屈陽 (中訊) 新規作成   
*/

package webbroker3.aio.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.aio.message.WEB3AioSecurityTransferForceRequest;
import webbroker3.aio.message.WEB3AioSecurityTransferForceResponse;
import webbroker3.aio.service.delegate.WEB3AioSecurityTransferForceService;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.util.WEB3LogUtility;

/**
 * (証券振替強制ハンドラ)<BR>
 * 証券振替強制ハンドラクラス
 * 
 * @@author 屈陽(中訊)
 * @@version 1.0
 */
public class WEB3AioSecurityTransferForceHandler implements MessageHandler 
{
    /**
    * ログ出力ユーティリティ。
    */    
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AioSecurityTransferForceHandler.class);
    
    /**
     * (証券振替強制リクエスト)<BR>
     * 証券振替強制サービスを取得し、execute()メソッドをコールする。
     * @@param l_request - リクエストデータ
     * @@return WEB3AioSecurityTransferForceResponse
     * @@roseuid 4157981D0040
     */
    public WEB3AioSecurityTransferForceResponse handleSecurityTransferForceRequest(WEB3AioSecurityTransferForceRequest l_request) 
    {
        String l_strMethodName = 
            "handleSecurityTransferForceRequest(WEB3AioSecurityTransferForceRequest l_request)";
        log.entering(l_strMethodName);
        
        WEB3AioSecurityTransferForceService l_service;
        WEB3AioSecurityTransferForceResponse l_response;
        
        try
        {
            //証券振替入力サービスを取得し
            l_service = 
                (WEB3AioSecurityTransferForceService)Services.getService(
                    WEB3AioSecurityTransferForceService.class);
        }
        //サービスで例外が発生した場合は、エラー情報をレスポンスメッセージに設定する
        catch (Exception l_ex)
        {
            l_response = 
                (WEB3AioSecurityTransferForceResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request, 
                "__(証券振替強制サービスの取得に失敗しました__",
                l_response.errorInfo,
                l_ex);
            return l_response;
        }
        
        try
        {
            //execute()メソッドをコールする
            l_response = (WEB3AioSecurityTransferForceResponse)l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response =
                (WEB3AioSecurityTransferForceResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request,
                "__error in call (証券振替強制サービス.execute()__",
                l_ex);
            return l_response;
        }
        
        log.exiting(l_strMethodName);
        
        return l_response;
    }
}
@
