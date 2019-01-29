head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.14;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3FuturesContractInquiryHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 株価指数先物建玉照会ハンドラクラス(WEB3FuturesContractInquiryHandler.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/07/21 李強 (中訊) 新規作成
*/
package webbroker3.ifo.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.ifo.message.WEB3FuturesContractReferenceRequest;
import webbroker3.ifo.message.WEB3FuturesContractReferenceResponse;
import webbroker3.ifo.service.delegate.WEB3FuturesContractInquiryService;
import webbroker3.util.WEB3LogUtility;

/**
 * (株価指数先物建玉照会ハンドラ)<BR>
 * 株価指数先物建玉照会ハンドラクラス<BR>
 * @@author 李強
 * @@version 1.0
 */
public class WEB3FuturesContractInquiryHandler implements MessageHandler 
{
    /**
     * ログ出力ユーティリティ。
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3FuturesContractInquiryHandler.class);
        
    /**
     * @@roseuid 40F7B0700261
     */
    public WEB3FuturesContractInquiryHandler() 
    {
     
    }
    
    /**
     * (get建玉)<BR>
     * 株価指数先物建玉照会処理を行う。<BR>
     * <BR>
     * 株価指数先物建玉照会サービスを取得し、<BR>
     * execute()メソッドをコールする。<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * 株式指数先物建玉照会リクエストオブジェクト
     * @@return WEB3FuturesContractReferenceResponse
     * @@roseuid 40A4C0E5032B
     */
    public WEB3FuturesContractReferenceResponse getContract(WEB3FuturesContractReferenceRequest l_request) 
    {
        final String STR_METHOD_NAME =
            "getContract(WEB3FuturesContractReferenceRequest)";

        log.entering(STR_METHOD_NAME);

        WEB3FuturesContractReferenceResponse l_response = null;
        WEB3FuturesContractInquiryService l_service = null;

        try
        {
            //株価指数先物建玉照会サービスオブジェクトを取得する

            l_service =
                (WEB3FuturesContractInquiryService)Services.getService(
            WEB3FuturesContractInquiryService.class);
        }
        //サービスで例外が発生した場合は、エラー情報をレスポンスメッセージに設定する
        catch (Exception l_ex)
        {
            l_response = (WEB3FuturesContractReferenceResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(l_request, "株価指数先物建玉照会サービスの取得に失敗しました。", l_response.errorInfo, l_ex); 
            log.exiting(STR_METHOD_NAME);           
            return l_response;
        }

        try
        {
            //株価指数先物建玉照会サービスオブジェクト.execute（）をコールし、
            //処理結果であるレスポンスオブジェクトを取得する。           
            l_response = (WEB3FuturesContractReferenceResponse)l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response = (WEB3FuturesContractReferenceResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request, "株価指数先物建玉照会に失敗しました。", l_ex);      
            log.exiting(STR_METHOD_NAME);               
            return l_response;
        }

        log.exiting(STR_METHOD_NAME);
        
        //レスポンスオブジェクトを返却する。
        return l_response;
    }
}
@
