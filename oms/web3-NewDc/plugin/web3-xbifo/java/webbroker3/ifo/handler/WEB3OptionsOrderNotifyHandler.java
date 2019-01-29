head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.13;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3OptionsOrderNotifyHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : OP注文通知ハンドラ(WEB3OptionsOrderNotifyHandler.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/12/02 凌建平 (中訊) 新規作成
*/

package webbroker3.ifo.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.ifo.service.delegate.WEB3OptionsOrderNotifyService;
import webbroker3.ifo.message.WEB3OptionsOrderNotifyResponse;
import webbroker3.ifo.message.WEB3OptionsOrderNotifyRequest;
import webbroker3.util.WEB3LogUtility;

/**
 * (OP注文通知ハンドラ)<BR>
 * 株価指数オプション注文通知ハンドラクラス<BR>
 * @@author  : 凌建平
 * @@version : 1.0
 */
public class WEB3OptionsOrderNotifyHandler implements MessageHandler 
{
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3OptionsOrderNotifyHandler.class);

   
    /**
     * @@roseuid 41AAE845036B
     */
    public WEB3OptionsOrderNotifyHandler() 
    {
      
    }
   
    /**
     * (オプション注文通知リクエスト)<BR>
     * 株価指数オプション注文通知処理を行う。<BR>
     * <BR>
     * 株価指数オプション注文通知サービスを取得し、<BR>
     * execute()メソッドをコールする。<BR>
     * @@param l_request - 株価指数オプション注文通知リクエストオブジェクト
     * @@return WEB3OptionsOrderNotifyResponse
     * @@roseuid 4163A6CC0358
     */
    public WEB3OptionsOrderNotifyResponse optionsOrderNotifyRequest(WEB3OptionsOrderNotifyRequest l_request) 
    {
        final String METHOD_NAME = "optionsOrderNotifyRequest()";
        log.entering(METHOD_NAME);

        WEB3OptionsOrderNotifyResponse l_response = null;
        WEB3OptionsOrderNotifyService l_service = null;
        
        //OP注文通知サービスを取得
        try
        {
            l_service =
                (WEB3OptionsOrderNotifyService)Services.getService(
                    WEB3OptionsOrderNotifyService.class);
        }
        //サービスで例外が発生した場合は、エラー情報をレスポンスメッセージに設定する
        catch (Exception l_ex)
        {
            l_response =
                (WEB3OptionsOrderNotifyResponse) l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "OP注文通知サービスを取得に失敗しました。",
                l_response.errorInfo,
                l_ex);
            return l_response;
        }
        
        //OP注文通知サービスオブジェクト.execute（）をコールし、
        //処理結果であるレスポンスオブジェクトを取得する。
        try
        {
            l_response =
                (WEB3OptionsOrderNotifyResponse) l_service.execute(
                    l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response =
                (WEB3OptionsOrderNotifyResponse) l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request, "OP注文通知に失敗しました。", l_ex);
            return l_response;
        }

        log.exiting(METHOD_NAME);

        //レスポンスオブジェクトを返却する。
        return l_response;
    }
}
@
