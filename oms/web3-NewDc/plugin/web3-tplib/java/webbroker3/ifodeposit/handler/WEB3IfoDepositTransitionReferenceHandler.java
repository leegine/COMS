head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.05.58;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	WEB3IfoDepositTransitionReferenceHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
 Copyright        : (株)大和総研 証券ソリューションシステム第二部
 File Name        : 証拠金推移参照画面表示ハンドラクラス(WEB3IfoDepositTransitionReferenceHandler.java)
 Author Name      : Daiwa Institute of Research
 Revision History : 2004/11/02 山田　@卓司(FLJ) 新規作成
 */
package webbroker3.ifodeposit.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.ifodeposit.message.WEB3IfoDepositTransitionReferenceRequest;
import webbroker3.ifodeposit.message.WEB3IfoDepositTransitionReferenceResponse;
import webbroker3.ifodeposit.service.delegate.WEB3IfoDepositTransitionReferenceService;
import webbroker3.util.WEB3LogUtility;

/**
 * (証拠金推移参照画面表示ハンドラ)<BR>
 * 証拠金推移参照画面表示ハンドラ。<BR>
 * 
 * @@author Takuji Yamada (FLJ)
 */
public class WEB3IfoDepositTransitionReferenceHandler implements MessageHandler
{

    /**
     * ログユーティリティ
     */
    private static final WEB3LogUtility log =
        WEB3LogUtility.getInstance(
            WEB3IfoDepositTransitionReferenceHandler.class);

    /**
     * @@roseuid 418617C201B4
     */
    public WEB3IfoDepositTransitionReferenceHandler()
    {

    }

    /**
     * (create証拠金推移参照画面)<BR>
     * 証拠金推移参照画面表示処理を行う。<BR>
     * <BR>
     * 証拠金推移参照画面表示サービスを取得し、execute()メソッドをコールする。<BR>
     * @@param l_request
     * @@return webbroker3.ifodeposit.message.WEB3IfoDepositTransitionReferenceResponse
     * @@roseuid 414528E10092
     */
    public WEB3IfoDepositTransitionReferenceResponse ifoDepositTransitionReferenceRequest(WEB3IfoDepositTransitionReferenceRequest l_request)
    {
        final String STR_METHOD_NAME = "ifoDepositTransitionReferenceRequest";
        log.entering(STR_METHOD_NAME);

        WEB3IfoDepositTransitionReferenceService l_service = null;
        WEB3IfoDepositTransitionReferenceResponse l_response = null;

        try
        {
            l_service =
                (WEB3IfoDepositTransitionReferenceService) Services.getService(
                    WEB3IfoDepositTransitionReferenceService.class);
        } 
        catch (Exception ex)
        {
            l_response =
                (WEB3IfoDepositTransitionReferenceResponse) l_request
                    .createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "証拠金推移画面表示サービスを取得できませんでした。",
                l_response.errorInfo,
                ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

        try
        {
            l_response =
                (WEB3IfoDepositTransitionReferenceResponse) l_service.execute(
                    l_request);
        } 
        catch (WEB3BaseException ex)
        {
            l_response =
                (WEB3IfoDepositTransitionReferenceResponse) l_request
                    .createResponse();
            l_response.errorInfo = ex.getErrorInfo();
            log.error(l_request, "証拠金推移画面表示に失敗しました。", l_response.errorInfo, ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        catch(WEB3BaseRuntimeException rx)
        {
            l_response =
                (WEB3IfoDepositTransitionReferenceResponse) l_request
                    .createResponse();
            l_response.errorInfo = rx.getErrorInfo();
            log.error(l_request, "証拠金推移画面表示に失敗しました。", l_response.errorInfo, rx);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        catch(Exception e)
        {
            l_response =
                (WEB3IfoDepositTransitionReferenceResponse) l_request
                    .createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(l_request, "証拠金推移画面表示に失敗しました。", l_response.errorInfo, e);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
}
@
