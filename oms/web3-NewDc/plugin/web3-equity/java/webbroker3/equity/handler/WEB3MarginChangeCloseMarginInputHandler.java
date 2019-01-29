head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.10;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3MarginChangeCloseMarginInputHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : (信用取引訂正返済入力ハンドラクラス)
                 : (WEB3MarginChangeCloseMarginInputHandler.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/09/28 li-songfeng (中訊) 新規作成
                   2005/01/06 岡村和明(SRA) JavaDoc修正
*/
package webbroker3.equity.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.equity.message.WEB3MarginCloseMarginChangeInputRequest;
import webbroker3.equity.message.WEB3MarginCloseMarginChangeInputResponse;
import webbroker3.equity.service.delegate.WEB3MarginChangeCloseMarginInputService;
import webbroker3.util.WEB3LogUtility;

/**
 * （信用取引訂正返済入力ハンドラ）。<BR>
 * <BR>
 * 信用取引訂正返済入力ハンドラクラス
 * @@version　@1.0
 */
public class WEB3MarginChangeCloseMarginInputHandler implements MessageHandler 
{
   /**
    * (ログユーティリティ)<BR>
    */
   private static WEB3LogUtility log =
      WEB3LogUtility.getInstance(WEB3MarginChangeCloseMarginInputHandler.class);
 
    /**
     * @@roseuid 414184C6017A
     */
    public WEB3MarginChangeCloseMarginInputHandler() 
    {
     
    }
    
    /**
     * (get訂正返済入力画面)<BR>
     * 信用取引訂正返済の入力画面表示処理を行う。<BR>
     * <BR>
     * 信用取引訂正返済入力サービスを取得し、<BR>
     * execute()メソッドをコールする。
     * @@param l_request - リクエストデータ
     * @@return WEB3MarginCloseMarginChangeInputResponse
     * @@roseuid 407CB2050163
     */
    public WEB3MarginCloseMarginChangeInputResponse getCloseMarginChangeInputScreen(WEB3MarginCloseMarginChangeInputRequest l_request) 
    {
        final String STR_METHOD_NAME = "getCloseMarginChangeInputScreen(WEB3MarginCloseMarginChangeInputRequest)";
        
        log.entering(STR_METHOD_NAME);
        WEB3MarginChangeCloseMarginInputService l_service=null;
        WEB3MarginCloseMarginChangeInputResponse l_response=null;
        try
        {
            l_service = (WEB3MarginChangeCloseMarginInputService)Services.getService(WEB3MarginChangeCloseMarginInputService.class);
        }
        catch (Exception l_ex)
        {
 
            l_response = (WEB3MarginCloseMarginChangeInputResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(l_request, "1信用取引訂正返済入力サービスの取得に失敗しました。", l_response.errorInfo, l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        try
        {
            l_response = (WEB3MarginCloseMarginChangeInputResponse) l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response = (WEB3MarginCloseMarginChangeInputResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request, "2信用取引訂正返済入力に失敗しました。", l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        catch (WEB3BaseRuntimeException l_bre)
        {
            l_response = (WEB3MarginCloseMarginChangeInputResponse)l_request.createResponse();
            l_response.errorInfo = l_bre.getErrorInfo();
            log.error(l_request, "2信用取引訂正返済入力に失敗しました。", l_bre);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
     log.exiting(STR_METHOD_NAME);
     return l_response;
    }
}
@
