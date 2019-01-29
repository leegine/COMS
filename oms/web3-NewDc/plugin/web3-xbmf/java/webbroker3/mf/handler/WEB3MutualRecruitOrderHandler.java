head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.03;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3MutualRecruitOrderHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 投信募集注文ハンドラクラス(WEB3RecruitOrderHandle)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/09/29 黄建 (中訊) 新規作成
*/

package webbroker3.mf.handler;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.mf.message.WEB3MutualApplyCompleteRequest;
import webbroker3.mf.message.WEB3MutualApplyCompleteResponse;
import webbroker3.mf.message.WEB3MutualApplyConfirmRequest;
import webbroker3.mf.message.WEB3MutualApplyConfirmResponse;
import webbroker3.mf.service.delegate.WEB3MutualRecruitOrderService;
import webbroker3.util.WEB3LogUtility;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

/**
 * 投信募集注文ハンドラクラス<BR>
 * 
 * @@author 黄建(中訊)
 * @@version 1.0
 */

public class WEB3MutualRecruitOrderHandler implements MessageHandler
{
    /**
     * ログユーティリティ
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3MutualRecruitOrderHandler.class);
    
    /**
     * (募集注文審査)<BR>
     * 投資信託の募集注文審査処理を行う<BR>
     * 投信募集注文サービスを取得し、execute()メソッドをコールする。<BR>
     * @@param l_request - 投信募集注文確認リクエスト
     * @@return webbroker3.mf.message.WEB3MutualApplyConfirmResponse
     * @@roseuid 40555E0E00E9
     */
    public WEB3MutualApplyConfirmResponse RecruitOrderValidation(
        WEB3MutualApplyConfirmRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = 
            "RecruitOrderValidation(WEB3MutualApplyConfirmRequest l_request)";
        log.entering(STR_METHOD_NAME);
        
        if (l_request == null)
        {
            log.debug("the parametor method in null ");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        WEB3MutualRecruitOrderService l_service = null;
        WEB3MutualApplyConfirmResponse l_response = null;
        try
        {
            l_service = 
                (WEB3MutualRecruitOrderService) Services.getService(
                    WEB3MutualRecruitOrderService.class);
        }
        catch (Exception l_ex)
        {
            l_response = 
                (WEB3MutualApplyConfirmResponse) l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "投信募集注文サービスの取得に失敗しました。",
                l_response.errorInfo,
                l_ex);
            return l_response;
        }
        try
        {
            l_response = 
                (WEB3MutualApplyConfirmResponse) l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response = 
                (WEB3MutualApplyConfirmResponse) l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request,
                " 投信募集注文確認の取得に失敗しました",
                l_ex.getErrorInfo(),
                l_ex);
            return l_response;
        }
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * (募集注文登録)<BR>
     * 投資信託の募集注文登録処理を行う<BR>
     * 投信募集注文サービスを取得し、execute()メソッドをコールする。<BR>
     * @@param l_request - 投信募集注文完了リクエスト
     * @@return webbroker3.mf.message.WEB3MutualApplyCompleteResponse
     * @@roseuid 40555E0E00E9
     */
    public WEB3MutualApplyCompleteResponse RecruitOrderRegister(
        WEB3MutualApplyCompleteRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = 
            "RecruitOrderRegister(WEB3MutualApplyCompleteRequest l_request)";
        log.entering(STR_METHOD_NAME);
        
        if (l_request == null)
        {
            log.debug("the parametor method in null ");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        WEB3MutualRecruitOrderService l_service = null;
        WEB3MutualApplyCompleteResponse l_response = null;
        try
        {
            l_service = 
                (WEB3MutualRecruitOrderService) Services.getService(
                    WEB3MutualRecruitOrderService.class);
        }
        catch (Exception l_ex)
        {
            l_response = 
                (WEB3MutualApplyCompleteResponse) l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "投信募集注文入力サービスの取得に失敗しました。",
                l_response.errorInfo,
                l_ex);
            return l_response;
        }
        try
        {
            l_response = 
                (WEB3MutualApplyCompleteResponse) l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response = 
                (WEB3MutualApplyCompleteResponse) l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request,
                " 投信募集注文完了の取得に失敗しました",
                l_ex.getErrorInfo(),
                l_ex);
            return l_response;
        }
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
}
@
