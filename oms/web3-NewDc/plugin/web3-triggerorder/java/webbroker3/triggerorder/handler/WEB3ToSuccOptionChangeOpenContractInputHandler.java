head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.02.00.43;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8704d7ec30c0c9d;
filename	WEB3ToSuccOptionChangeOpenContractInputHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : （連続）OP訂正新規建入力ハンドラ（WEB3ToSuccOptionChangeOpenContractInputHandler.java）
Author Name      : Daiwa Institute of Research
Revision History : 2008/04/11 トウ鋒鋼 (中訊) 新規作成 モデル267
*/
package webbroker3.triggerorder.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.triggerorder.message.WEB3SuccOptionsOpenChangeInputRequest;
import webbroker3.triggerorder.message.WEB3SuccOptionsOpenChangeInputResponse;
import webbroker3.triggerorder.service.delegate.WEB3ToSuccOptionChangeOpenContractInputService;
import webbroker3.util.WEB3LogUtility;

/**
 * (（連続）OP訂正新規建入力ハンドラ)<BR>
 * （連続）OP訂正新規建入力ハンドラクラス<BR>
 *
 * @@author トウ鋒鋼
 * @@version 1.0
 */
public class WEB3ToSuccOptionChangeOpenContractInputHandler implements MessageHandler
{
    /**
     * ログ出力ユーティリティ。
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3ToSuccOptionChangeOpenContractInputHandler.class);

    /**
     * @@roseuid 47FDBE3D037A
     */
    public WEB3ToSuccOptionChangeOpenContractInputHandler()
    {

    }

    /**
     * (訂正新規建入力リクエスト)<BR>
     * （連続）OP訂正新規建入力表示処理を行う。  <BR>
     * <BR>
     * （連続）OP訂正新規建入力サービスを取得し、execute()メソッドをコールする。<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * リクエストデータ<BR>
     * @@return WEB3SuccOptionsOpenChangeInputResponse
     * @@roseuid 47A920FE002D
     */
    public WEB3SuccOptionsOpenChangeInputResponse changeOpenContractInputRequest(
        WEB3SuccOptionsOpenChangeInputRequest l_request)
    {
        final String STR_METHOD_NAME = "changeOpenContractInputRequest(WEB3SuccOptionsOpenChangeInputRequest)";
        log.entering(STR_METHOD_NAME);

        WEB3SuccOptionsOpenChangeInputResponse l_response = null;
        WEB3ToSuccOptionChangeOpenContractInputService l_service = null;
        try
        {
            // （連続）OP訂正新規建入力サービスを取得
            l_service = (WEB3ToSuccOptionChangeOpenContractInputService)Services.getService(
                WEB3ToSuccOptionChangeOpenContractInputService.class);
        }
        catch (Exception l_ex)
        {
            l_response = (WEB3SuccOptionsOpenChangeInputResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "（連続）OP訂正新規建入力サービスを取得に失敗しました。",
                l_response.errorInfo,
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

        try
        {
            // execute()メソッドをコールする
            l_response = (WEB3SuccOptionsOpenChangeInputResponse)l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response = (WEB3SuccOptionsOpenChangeInputResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request,
                "（連続）OP訂正新規建入力表示処理が失敗しました。",
                l_response.errorInfo,
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        catch (WEB3BaseRuntimeException l_ex)
        {
            l_response = (WEB3SuccOptionsOpenChangeInputResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request,
                "（連続）OP訂正新規建入力表示処理が失敗しました。",
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
