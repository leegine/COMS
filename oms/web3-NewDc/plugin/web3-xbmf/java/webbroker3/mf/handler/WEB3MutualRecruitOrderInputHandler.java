head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.02;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3MutualRecruitOrderInputHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 投信募集注文入力ハンドラクラス(WEB3MutualRecruitOrderInputHandler)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/09/29 黄建 (中訊) 新規作成
*/

package webbroker3.mf.handler;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.mf.message.WEB3MutualApplyInputRequest;
import webbroker3.mf.message.WEB3MutualApplyInputResponse;
import webbroker3.mf.service.delegate.WEB3MutualRecruitOrderInputService;
import webbroker3.util.WEB3LogUtility;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

/**
 * 投信募集注文入力ハンドラクラス<BR>
 * 
 * @@author 黄建(中訊)
 * @@version 1.0
 */

public class WEB3MutualRecruitOrderInputHandler implements MessageHandler
{
    /**
     * ログユーティリティ
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3MutualRecruitOrderInputHandler.class);
    
    /**
     * (募集注文入力)<BR>
     * 投資信託の募集注文入力画面表示処理を行う。<BR>
     * 投信募集注文入力サービスを取得し、execute()メソッドをコールする。<BR>
     * @@param l_request - 投信募集注文入力リクエスト
     * @@return webbroker3.mf.message.WEB3MutualApplyInputResponse
     * @@roseuid 40555E0E00E9
     */
    public WEB3MutualApplyInputResponse RecruitOrderInput(
        WEB3MutualApplyInputRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = 
            " RecruitOrderInput(WEB3MutualApplyInputRequest l_request)";
        log.entering(STR_METHOD_NAME);
        
        if (l_request == null)
        {
            log.debug("the parametor method in null ");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        WEB3MutualRecruitOrderInputService l_service = null;
        WEB3MutualApplyInputResponse l_response = null;
        try
        {
            l_service = 
                (WEB3MutualRecruitOrderInputService) Services.getService(
                    WEB3MutualRecruitOrderInputService.class);
        }
        catch (Exception l_ex)
        {
            l_response = 
                (WEB3MutualApplyInputResponse) l_request.createResponse();
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
                (WEB3MutualApplyInputResponse) l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response = 
                (WEB3MutualApplyInputResponse) l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request,
                " 投信募集注文入力の取得に失敗しました",
                l_ex.getErrorInfo(),
                l_ex);
            return l_response;
        }
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }   
}
@
