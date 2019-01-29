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
filename	WEB3BondRecruitBuyInputHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 債券応募/買付入力ハンドラ(WEB3BondRecruitBuyInputHandler.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/09/08 唐性峰 (中訊) 新規作成
*/

package webbroker3.bd.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.util.WEB3LogUtility;
import webbroker3.bd.message.WEB3BondApplyBuyInputRequest;
import webbroker3.bd.message.WEB3BondApplyBuyInputResponse;
import webbroker3.bd.service.delegate.WEB3BondRecruitBuyInputService;

/**
 * (債券応募/買付入力ハンドラ)<BR>
 * 債券応募/買付入力ハンドラ<BR>
 * <BR>
 * @@author 唐性峰
 * @@version 1.0
 */
public class WEB3BondRecruitBuyInputHandler implements MessageHandler
{
    /**
     * ログユーティリティ
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3BondRecruitBuyInputHandler.class); 
    
    /**
     * @@roseuid 44FBFD3A0242
     */
    public WEB3BondRecruitBuyInputHandler() 
    {
     
    }
    
    /**
     * (債券応募/買付入力)<BR>
     * 債券応募/買付入力処理を行う。<BR> 
     * <BR>
     * 債券応募/買付入力サービスを取得し、execute()メソッドをコールする。<BR>
     * @@param l_request - リクエストデータ
     * @@return webbroker3.bd.message.WEB3BondApplyBuyInputResponse
     * @@roseuid 44C453640111
     */
    public WEB3BondApplyBuyInputResponse inputBondRecruitBuy(WEB3BondApplyBuyInputRequest l_request) 
    {
        final String STR_METHOD_NAME = " confirmBondSell(WEB3BondSellConfirmRequest)";
        log.entering(STR_METHOD_NAME);
        
        WEB3BondRecruitBuyInputService l_service = null;
        WEB3BondApplyBuyInputResponse l_response = null;
        
        try
        {
            //債券応募/買付入力サービスを取得し
            l_service = 
                (WEB3BondRecruitBuyInputService)Services.getService(
                        WEB3BondRecruitBuyInputService.class);
        }
        //サービスで例外が発生した場合は、エラー情報をレスポンスメッセージに設定する
        catch (Exception l_ex)
        {
            l_response =
                (WEB3BondApplyBuyInputResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "債券応募/買付入力サービスを取得に失敗しました",
                l_response.errorInfo,
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        
        try
        {
            //execute()メソッドをコールする
            l_response =
                (WEB3BondApplyBuyInputResponse)l_service.execute(l_request);           
        }
        catch (WEB3BaseException l_ex)
        {
            l_response = 
                (WEB3BondApplyBuyInputResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request,
                "債券応募/買付入力処理が失敗しました。",
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
