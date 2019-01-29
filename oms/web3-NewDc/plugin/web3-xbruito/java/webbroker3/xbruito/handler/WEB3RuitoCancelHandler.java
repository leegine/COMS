head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.35;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3RuitoCancelHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 累投取消ハンドラ (WEB3RuitoCancelHandler)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/06/11 李志強 (中訊) 新規作成
*/

package webbroker3.xbruito.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.util.WEB3LogUtility;
import webbroker3.xbruito.message.WEB3RuitoCancelCompleteRequest;
import webbroker3.xbruito.message.WEB3RuitoCancelCompleteResponse;
import webbroker3.xbruito.message.WEB3RuitoCancelConfirmRequest;
import webbroker3.xbruito.message.WEB3RuitoCancelConfirmResponse;
import webbroker3.xbruito.service.delegate.WEB3RuitoCancelService;

/**
 * 累積投資取消ハンドラクラス<BR>
 */
public class WEB3RuitoCancelHandler implements MessageHandler 
{
    private static  WEB3LogUtility log = WEB3LogUtility.getInstance(
	    WEB3RuitoCancelHandler.class);
   
   /**
    * 累積投資取消審査を行う。<BR>
    * <BR>
    * 累積投資取消サービスを取得し、execute()メソッドをコールする。<BR>
    * @@param l_request - 累積投資取消確認リクエストオブジェクト
    * @@return webbroker3.xbruito.message.WEB3RuitoCancelConfirmResponse
    * @@roseuid 40581D7500EC
    */
    public WEB3RuitoCancelConfirmResponse confirmCancel(
            WEB3RuitoCancelConfirmRequest l_request)
    {
		final String STR_METHOD_NAME = 
            "confirmCancel(WEB3RuitoCancelConfirmRequest l_request)";
		log.entering(STR_METHOD_NAME);
		
		if (l_request == null)
		{
            log.debug(" パラメータ値がNULL ");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME, 
                "パラメータ値がNULL");
		}

        WEB3RuitoCancelService l_service = null;
        WEB3RuitoCancelConfirmResponse l_response = null;
    
        l_service = (WEB3RuitoCancelService)Services.getService(
            WEB3RuitoCancelService.class);

        try 
        {
            l_response =
                (WEB3RuitoCancelConfirmResponse)l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response =
            (WEB3RuitoCancelConfirmResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request,
                "累積投資取消審査処理が失敗しました。", l_ex.getErrorInfo(), l_ex);
        }
        
		log.exiting(STR_METHOD_NAME);               
        return l_response;
    }
   
   /**
    * 累積投資取消登録を行う。<BR>
    * <BR>
    * 累積投資取消サービスを取得し、execute()メソッドをコールする。<BR>
    * @@param l_request - 累積投資取消完了リクエストオブジェクト<BR>
    * @@return webbroker3.xbruito.message.WEB3RuitoCancelCompleteResponse
    * @@throws WEB3BaseException
    * @@roseuid 40581D7E0021
    */
    public WEB3RuitoCancelCompleteResponse completeCancel(
        WEB3RuitoCancelCompleteRequest l_request)
    {
		final String STR_METHOD_NAME = 
			"confirmCancel(WEB3RuitoCancelConfirmRequest l_request)";
		log.entering(STR_METHOD_NAME);
		
		if (l_request == null)
		{
            log.debug(" パラメータ値がNULL ");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME, 
                "パラメータ値がNULL");
		}

		WEB3RuitoCancelService l_service = null;
        WEB3RuitoCancelCompleteResponse l_response = null;
 
        l_service = (WEB3RuitoCancelService)Services.getService(
            WEB3RuitoCancelService.class);

        try 
        {
            l_response =
            (WEB3RuitoCancelCompleteResponse)l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response =
            (WEB3RuitoCancelCompleteResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request,
                "累積投資取消登録処理が失敗しました。", l_ex.getErrorInfo(), l_ex);
        }
        
		log.exiting(STR_METHOD_NAME);
        return l_response;
    }
}
@
