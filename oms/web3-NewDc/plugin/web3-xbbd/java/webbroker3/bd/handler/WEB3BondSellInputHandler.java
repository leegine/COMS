head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.18;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3BondSellInputHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 債券売却入力ハンドラ(WEB3BondSellInputHandler.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/09/06 唐性峰 (中訊) 新規作成
*/

package webbroker3.bd.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.util.WEB3LogUtility;
import webbroker3.bd.message.WEB3BondSellInputRequest;
import webbroker3.bd.message.WEB3BondSellInputResponse;
import webbroker3.bd.service.delegate.WEB3BondSellInputService;

/**
 * (債券売却入力ハンドラ)<BR>
 * 債券売却入力ハンドラ<BR>
 * <BR>
 * @@author 唐性峰
 * @@version 1.0
 */
public class WEB3BondSellInputHandler implements MessageHandler
{
    /**
     * ログユーティリティ
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3BondSellInputHandler.class); 
    
    /**
     * @@roseuid 44FBFD3A032C
     */
    public WEB3BondSellInputHandler() 
    {
     
    }
    
    /**
     * (債券売却入力)<BR>
     * 債券売却入力表示処理を行う。<BR>
     * <BR>
     * 債券売却入力サービスを取得し、<BR>
     * execute()メソッドをコールする。<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * 債券売却入力リクエスト<BR>
     * @@return webbroker3.bd.message.WEB3BondSellInputResponse
     * @@roseuid 44C024F10066
     */
    public WEB3BondSellInputResponse inputBondSell(WEB3BondSellInputRequest l_request) 
    {
        final String STR_METHOD_NAME = " inputBondSell(WEB3BondSellInputRequest)";
        log.entering(STR_METHOD_NAME);
        
        WEB3BondSellInputService l_service = null;
        WEB3BondSellInputResponse l_response = null;
        
        try
        {
            //債券売却入力サービスを取得し
            l_service =
                (WEB3BondSellInputService)Services.getService(
                    WEB3BondSellInputService.class);
        }
        //サービスで例外が発生した場合は、エラー情報をレスポンスメッセージに設定する
        catch (Exception l_ex)
        {
            l_response =
                (WEB3BondSellInputResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "債券売却入力サービスを取得に失敗しました",
                l_response.errorInfo,
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        
        try
        {
            //execute()メソッドをコール
            l_response = 
                (WEB3BondSellInputResponse)l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response =
                (WEB3BondSellInputResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request,
                "債券売却入力表示処理が失敗しました。",
                l_response.errorInfo,
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
}
@
