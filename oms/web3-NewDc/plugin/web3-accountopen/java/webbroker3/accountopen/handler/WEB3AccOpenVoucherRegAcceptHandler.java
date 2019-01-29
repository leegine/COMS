head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.03.46.35;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	WEB3AccOpenVoucherRegAcceptHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 口座開設伝票登録受付ハンドラ(WEB3AccOpenVoucherRegAcceptHandler.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/12/15 鄭海良(中訊) 新規作成
*/

package webbroker3.accountopen.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.accountopen.message.WEB3AccOpenVoucherRegAcceptRequest;
import webbroker3.accountopen.message.WEB3AccOpenVoucherRegAcceptResponse;
import webbroker3.accountopen.service.delegate.WEB3AccOpenVoucherRegAcceptService;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.util.WEB3LogUtility;

/**
 * (口座開設伝票登録受付ハンドラ)<BR>
 * 口座開設伝票登録受付ハンドラ<BR>
 *   
 * @@author 鄭海良
 * @@version 1.0
 */
public class WEB3AccOpenVoucherRegAcceptHandler implements MessageHandler 
{

    /**
     * (ログ出力ユーティリティ。)<BR>
     */
    private static WEB3LogUtility log = 
        WEB3LogUtility.getInstance(WEB3AccOpenVoucherRegAcceptHandler.class);
    
    /**
     * @@roseuid 41B45E7500BB
     */
    public WEB3AccOpenVoucherRegAcceptHandler() 
    {
     
    }
    
    /**
     * (口座開設伝票登録受付)<BR>
     * 口座開設伝票登録受付処理を実施する。<BR>
     * <BR>
     * 口座開設伝票登録受付サービスを取得し、execute()メソッドをコールする。<BR>
     * @@param l_request - 口座開設伝票登録受付リクエストデータオブジェクト
     * 
     * @@return webbroker3.accountopen.message.WEB3AccOpenVoucherRegAcceptResponse
     * @@roseuid 41A19B78039C
     */
    public WEB3AccOpenVoucherRegAcceptResponse accOpenVoucherRegAccept(WEB3AccOpenVoucherRegAcceptRequest l_request) 
    {
        final String STR_METHOD_NAME = " accOpenVoucherRegAccept(WEB3AccOpenVoucherRegAcceptRequest )";    
        log.entering(STR_METHOD_NAME);
        
        WEB3AccOpenVoucherRegAcceptResponse l_response = null;
        WEB3AccOpenVoucherRegAcceptService l_service = null;      
        try
        {
            //get service
            l_service = 
                (WEB3AccOpenVoucherRegAcceptService)Services.getService(
                    WEB3AccOpenVoucherRegAcceptService.class);
        }
        catch (Exception l_ex)
        {
            
            l_response = (WEB3AccOpenVoucherRegAcceptResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "口座開設伝票登録受付サービスの取得に失敗しました。",
                l_response.errorInfo,
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;                  
        }

        try
        {
            //execute()メソッドをコールする
            l_response = (WEB3AccOpenVoucherRegAcceptResponse)l_service.execute(l_request);//WEB3BaseException
        }
        catch (WEB3BaseException l_ex)
        {
            l_response = (WEB3AccOpenVoucherRegAcceptResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request, "口座開設伝票登録受付処理に失敗しました。", l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

        log.exiting(STR_METHOD_NAME);
        return l_response;    
    }
}
@
