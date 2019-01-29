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
filename	WEB3OptionChangeOpenContractInputHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : OP訂正新規建入力ハンドラ(WEB3OptionChangeOpenContractInputHandler.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/06/11 李頴淵 (中訊) 新規作成
*/

package webbroker3.ifo.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.util.WEB3LogUtility;
import webbroker3.ifo.service.delegate.WEB3OptionChangeOpenContractInputService;
import webbroker3.ifo.message.WEB3OptionsOpenMarginChangeInputResponse;
import webbroker3.ifo.message.WEB3OptionsOpenMarginChangeInputRequest;

/**
 * (OP訂正新規建入力ハンドラ)<BR>
 * 株価指数オプション訂正新規建入力ハンドラクラス<BR>
 * @@author 李頴淵
 * @@version 1.0
 */
public class WEB3OptionChangeOpenContractInputHandler implements MessageHandler 
{
    
    /**
     * ログ出力ユーティリティ。
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3OptionChangeOpenContractInputHandler.class);
    
    /**
     * @@roseuid 40C0755F00EA
     */
    public WEB3OptionChangeOpenContractInputHandler() 
    {
     
    }
    
    /**
     * (訂正新規建入力リクエスト)<BR>
     * <BR>
     * 株価指数オプション訂正新規建入力表示処理を行う。<BR>
     * <BR>
     * 株価指数オプション訂正新規建入力サービスを取得し、<BR>
     * execute()メソッドをコールする。<BR>
     * @@param l_request - リクエストデータ
     * @@return webbroker3.ifo.message.WEB3OptionsOpenMarginChangeInputResponse
     * @@roseuid 407A4D170109
     */
    public WEB3OptionsOpenMarginChangeInputResponse changeOpenContractInputRequest(WEB3OptionsOpenMarginChangeInputRequest l_request) 
    {
        final String STR_METHOD_NAME =
            getClass().getName()
                + ".changeOpenContractInputRequest(WEB3OptionsOpenMarginChangeInputRequest)";
                
        log.entering(STR_METHOD_NAME);
        
        WEB3OptionsOpenMarginChangeInputResponse l_response = null;
        WEB3OptionChangeOpenContractInputService l_service = null;
        
        //株価指数オプション訂正新規建入力サービスを取得する
        try
        {
            l_service =
                (WEB3OptionChangeOpenContractInputService)Services.getService(
            WEB3OptionChangeOpenContractInputService.class);
        }
        //サービスで例外が発生した場合は、エラー情報をレスポンスメッセージに設定する
        catch (Exception l_ex)
        {
            l_response = (WEB3OptionsOpenMarginChangeInputResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "株価指数オプション訂正新規建入力サービスの取得に失敗しました。",
                l_response.errorInfo,l_ex);
            return l_response;
        }
        
        //株価指数オプション訂正新規建入力サービス.execute()メソッドをコールする
        try
        {
            l_response =
                (WEB3OptionsOpenMarginChangeInputResponse)l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response = (WEB3OptionsOpenMarginChangeInputResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request, "株価指数オプション訂正新規建入力に失敗しました。", l_ex);
            return l_response;
        }

        log.exiting(STR_METHOD_NAME);

        //レスポンスオブジェクトを返却する。
        return l_response;

    }
}
@
