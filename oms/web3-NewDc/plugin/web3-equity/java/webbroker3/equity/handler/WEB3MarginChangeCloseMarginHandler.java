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
filename	WEB3MarginChangeCloseMarginHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : (信用取引訂正返済ハンドラ)信用取引訂正返済ハンドラクラス
                 : (WEB3MarginChangeCloseMarginHandler.java)
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
import webbroker3.equity.message.WEB3MarginCloseMarginChangeCompleteRequest;
import webbroker3.equity.message.WEB3MarginCloseMarginChangeCompleteResponse;
import webbroker3.equity.message.WEB3MarginCloseMarginChangeConfirmRequest;
import webbroker3.equity.message.WEB3MarginCloseMarginChangeConfirmResponse;
import webbroker3.equity.service.delegate.WEB3MarginChangeCloseMarginService;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;


/**
 * （信用取引訂正返済ハンドラ）。<BR>
 * <BR>
 * 信用取引訂正返済ハンドラクラス
 * @@version　@1.0
 */
public class WEB3MarginChangeCloseMarginHandler implements MessageHandler 
{
  /**
    * (ログユーティリティ)<BR>
    */
   private static WEB3LogUtility log =
      WEB3LogUtility.getInstance(WEB3MarginChangeCloseMarginHandler.class);

    
    /**
     * @@roseuid 414184C600D0
     */
    public WEB3MarginChangeCloseMarginHandler() 
    {
     
    }
    
    /**
     * (confirm訂正返済)<BR>
     * 信用取引の訂正返済発注審査を行う。<BR>
     * <BR>
     * 信用取引訂正返済サービスを取得し、execute()メソッドをコールする。
     * @@param l_request - 信用取引訂正返済確認リクエスト
     * @@return WEB3MarginCloseMarginChangeConfirmResponse
     * @@roseuid 4058273103D6
     */
    public WEB3MarginCloseMarginChangeConfirmResponse confirmCloseMarginChange(WEB3MarginCloseMarginChangeConfirmRequest l_request) 
    {
        final String STR_METHOD_NAME = "confirmCloseMarginChange(WEB3MarginCloseMarginChangeConfirmRequest)";
        
        log.entering(STR_METHOD_NAME);
        WEB3MarginChangeCloseMarginService l_service = null;
        WEB3MarginCloseMarginChangeConfirmResponse l_response = null;
        try
        {
            l_service= (WEB3MarginChangeCloseMarginService)Services.getService(WEB3MarginChangeCloseMarginService.class); 
        }
        catch (Exception l_ex)
        {
            l_response = (WEB3MarginCloseMarginChangeConfirmResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(l_request, "信用取引訂正返済サービスの取得に失敗しました。", l_response.errorInfo, l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        try
        {
            l_response = (WEB3MarginCloseMarginChangeConfirmResponse)l_service.execute(l_request);
        } 
        catch (WEB3BaseException l_ex)
        {
            l_response = (WEB3MarginCloseMarginChangeConfirmResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            if (!WEB3StringTypeUtility.isEmpty(l_ex.getErrorMessage()))
            {
                l_response.errorMessage = l_ex.getErrorMessage();
            }
            log.error(l_request, "信用取引訂正返済発注審査に失敗しました。", l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        catch (WEB3BaseRuntimeException l_bre)
        {
            l_response = (WEB3MarginCloseMarginChangeConfirmResponse)l_request.createResponse();
            l_response.errorInfo = l_bre.getErrorInfo();
            log.error(l_request, "信用取引訂正返済発注審査に失敗しました。", l_bre);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        } 
        log.exiting(STR_METHOD_NAME);    
        return l_response;
    }
    
    /**
     * (complete訂正返済)<BR>
     * 信用取引の訂正返済注文を登録する。<BR>
     * <BR>
     * 信用取引訂正返済サービスを取得し、execute()メソッドをコールする。
     * @@param l_request - 信用取引訂正返済完了リクエスト
     * @@return WEB3MarginCloseMarginChangeCompleteResponse
     * @@roseuid 40582732000D
     */
    public WEB3MarginCloseMarginChangeCompleteResponse completeCloseMarginChange(WEB3MarginCloseMarginChangeCompleteRequest l_request) 
    {
        final String STR_METHOD_NAME = "completeCloseMarginChange(WEB3MarginCloseMarginChangeCompleteRequest)";
        
        log.entering(STR_METHOD_NAME); 
        WEB3MarginChangeCloseMarginService l_service = null;
        WEB3MarginCloseMarginChangeCompleteResponse l_response = null;
        try
        {
            l_service= (WEB3MarginChangeCloseMarginService)Services.getService(WEB3MarginChangeCloseMarginService.class); 
        }
        catch (Exception l_ex)
        {
            l_response = (WEB3MarginCloseMarginChangeCompleteResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(l_request, "信用取引訂正返済サービスの取得に失敗しました。", l_response.errorInfo, l_ex);
            return l_response;
        }
        try
        {
            l_response = (WEB3MarginCloseMarginChangeCompleteResponse)l_service.execute(l_request);
        } 
        catch (WEB3BaseException l_ex)
        {
            l_response = (WEB3MarginCloseMarginChangeCompleteResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            if (!WEB3StringTypeUtility.isEmpty(l_ex.getErrorMessage()))
            {
                l_response.errorMessage = l_ex.getErrorMessage();
            }
            log.error(l_request, "信用取引訂正返済注文に失敗しました。", l_ex);
            return l_response;
        }
        catch (WEB3BaseRuntimeException l_bre)
        {
            l_response = (WEB3MarginCloseMarginChangeCompleteResponse)l_request.createResponse();
            l_response.errorInfo = l_bre.getErrorInfo();
            log.error(l_request, "信用取引訂正返済注文に失敗しました。", l_bre);
            return l_response;
        }
        log.exiting(STR_METHOD_NAME);     
        return l_response;
    }
}
@
