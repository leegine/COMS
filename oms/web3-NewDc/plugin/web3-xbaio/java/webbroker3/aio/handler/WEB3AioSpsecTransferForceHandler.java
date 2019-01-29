head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.22.12;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3AioSpsecTransferForceHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 特定口座振替強制ハンドラ(WEB3AioSpsecTransferForceHandler)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/2/6 韋念瓊 (中訊) 新規作成   
*/

package webbroker3.aio.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.aio.message.WEB3AioSpsecTransferForceRequest;
import webbroker3.aio.message.WEB3AioSpsecTransferForceResponse;
import webbroker3.aio.service.delegate.WEB3AioSpsecTransferForceService;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.util.WEB3LogUtility;

/**
 * (特定口座振替強制ハンドラ)<BR>
 * 特定口座振替強制ハンドラクラス 
 * 
 * @@author 韋念瓊(中訊)
 * @@version 1.0
 */
public class WEB3AioSpsecTransferForceHandler implements MessageHandler 
{
    /**
    * ログ出力ユーティリティ。
    */    
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AioSpsecTransferForceHandler.class);
    
    /**
     * (特定口座振替強制リクエスト )<BR>
     * 特定口座振替強制サービスを取得し、execute()メソッドをコールする。 
     * @@param l_request - リクエストデータ
     * @@return WEB3AioSpsecTransferForceResponse
     * @@roseuid 4157981D0040
     */
    public WEB3AioSpsecTransferForceResponse handleSpsecTransferForceRequest(WEB3AioSpsecTransferForceRequest l_request) 
    {
    		
        String STR_METHOD_NAME = 
            "handleSpsecTransferForceRequest(WEB3AioSpsecTransferForceRequest l_request)";
        log.entering(STR_METHOD_NAME);
        
        WEB3AioSpsecTransferForceService l_service;
        WEB3AioSpsecTransferForceResponse l_response;
        
        try
        {
            //特定口座振替強制サービスを取得し
            l_service = 
                (WEB3AioSpsecTransferForceService)Services.getService(
                        WEB3AioSpsecTransferForceService.class);
        }
        //サービスで例外が発生した場合は、エラー情報をレスポンスメッセージに設定する
        catch (Exception l_ex)
        {
            l_response = 
                (WEB3AioSpsecTransferForceResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request, 
                "__(特定口座振替強制サービスの取得に失敗しました__",
                l_response.errorInfo,
                l_ex);
            return l_response;
        }
        
        try
        {
            //execute()メソッドをコールする
            l_response = (WEB3AioSpsecTransferForceResponse)l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response =
                (WEB3AioSpsecTransferForceResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request,
                "__error in call (特定口座振替強制サービス.execute()__",
                l_ex);
            return l_response;
        }
        
        log.exiting(STR_METHOD_NAME);
        
        return l_response;        
        
    }
}
@
