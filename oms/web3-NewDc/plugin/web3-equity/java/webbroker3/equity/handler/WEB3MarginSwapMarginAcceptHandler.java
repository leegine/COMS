head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.11;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3MarginSwapMarginAcceptHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        :信用取引現引現渡受付ハンドラクラス(WEB3MarginSwapMarginAcceptHandler.java)
Author Name      : 2004/10/8 盧法@旭(中訊) 新規作成
                   2005/01/06 岡村和明(SRA) JavaDoc修正
*/

package webbroker3.equity.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.equity.message.WEB3MarginSwapMarginAcceptRequest;
import webbroker3.equity.message.WEB3MarginSwapMarginAcceptResponse;
import webbroker3.equity.service.delegate.WEB3MarginSwapMarginAcceptService;
import webbroker3.util.WEB3LogUtility;
/**
 * （信用取引現引現渡受付ハンドラ）。<BR>
 * <BR>
 * 信用取引現引現渡受付ハンドラクラス
 *  @@author 法@旭
 * @@version 1.0
 */
public class WEB3MarginSwapMarginAcceptHandler implements MessageHandler 
{
    

    /**
     * (ログ出力ユーティリティ。)<BR>
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(WEB3MarginContractReferenceHandler.class);

    /**
     * @@roseuid 414184C400F5
     */
    public WEB3MarginSwapMarginAcceptHandler() 
    {
     
    }
    
    /**
     * (現引現渡受付リクエスト)<BR>
     * 信用取引現引現渡受付処理を実施する。<BR>
     * <BR>
     * 信用取引現引現渡受付サービスを取得し、execute()メソッドをコールする。<BR>
     * @@param l_request - 信用取引現引現渡受付リクエストオブジェクト
     * @@return webbroker3.margin.message.WEB3MarginSwapMarginAcceptResponse
     * @@roseuid 4057AECB00DD
     */
    public WEB3MarginSwapMarginAcceptResponse swapMarginAcceptRequest(WEB3MarginSwapMarginAcceptRequest l_request) 
    {
        final String STR_METHOD_NAME = "swapMarginAcceptRequest(WEB3MarginSwapMarginAcceptRequest)";
        log.entering(STR_METHOD_NAME);
        if(l_request == null)
        {
            throw new WEB3BaseRuntimeException(
               WEB3ErrorCatalog.SYSTEM_ERROR_80017,
               this.getClass().getName() + "." + STR_METHOD_NAME);            
        }               
        WEB3MarginSwapMarginAcceptService l_service = null;
        WEB3MarginSwapMarginAcceptResponse  l_response = null;
        try
        {
            l_service = (WEB3MarginSwapMarginAcceptService) Services.getService(WEB3MarginSwapMarginAcceptService.class);
        }
        // サービスで例外が発生した場合は、エラー情報をレスポンスメッセージに設定する
        catch (Exception l_ex)
        {
            l_response = (WEB3MarginSwapMarginAcceptResponse) l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(l_request, "信用取引現引現渡受付サービスの取得に失敗しました。", l_response.errorInfo, l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

        try
        {
            l_response = (WEB3MarginSwapMarginAcceptResponse) l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response = (WEB3MarginSwapMarginAcceptResponse) l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request, "信用取引現引現渡受付に失敗しました。", l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        catch (WEB3BaseRuntimeException l_bre)
        {
            l_response = (WEB3MarginSwapMarginAcceptResponse) l_request.createResponse();
            l_response.errorInfo = l_bre.getErrorInfo();
            log.error(l_request, "信用取引現引現渡受付に失敗しました。", l_bre);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
}
@
