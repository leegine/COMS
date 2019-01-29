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
filename	WEB3BondDomesticApplyInputHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 国内債券応募入力ハンドラ(WEB3BondDomesticApplyInputHandler.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/07/23 武波 (中訊) 新規作成 仕様変更・モデルNo.225
*/
package webbroker3.bd.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.bd.message.WEB3BondDomesticApplyInputResponse;
import webbroker3.bd.message.WEB3BondDomesticApplyInputRequest;
import webbroker3.bd.service.delegate.WEB3BondDomesticApplyInputService;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.util.WEB3LogUtility;

/**
 * (国内債券応募入力ハンドラ)<BR>
 * 国内債券応募入力ハンドラ<BR>
 * <BR>
 * @@author 武波
 * @@version 1.0
 */
public class WEB3BondDomesticApplyInputHandler implements MessageHandler
{

    /**
     * ログユーティリティ<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3BondDomesticApplyInputHandler.class);

    /**
     * @@roseuid 46A473FD0196
     */
    public WEB3BondDomesticApplyInputHandler()
    {

    }

    /**
     * (国内債券応募入力)<BR>
     * 国内債券応募入力処理を行う。<BR>
     * <BR>
     * 国内債券応募入力サービスを取得し、execute()メソッドをコールする。<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * リクエストデータ<BR>
     * @@return WEB3BondDomesticApplyInputResponse
     * @@roseuid 466CCD150226
     */
    public WEB3BondDomesticApplyInputResponse bondDomesticApplyInput(
        WEB3BondDomesticApplyInputRequest l_request)
    {
        final String STR_METHOD_NAME = " bondDomesticApplyInput(WEB3BondDomesticApplyInputRequest)";
        log.entering(STR_METHOD_NAME);

        WEB3BondDomesticApplyInputResponse l_response = null;
        WEB3BondDomesticApplyInputService l_service = null;
        try
        {
            l_service =
                (WEB3BondDomesticApplyInputService)Services.getService(
                    WEB3BondDomesticApplyInputService.class);
        }
        catch (Exception l_ex)
        {
            l_response =
                (WEB3BondDomesticApplyInputResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "国内債券応募入力サービスを取得に失敗しました。",
                l_response.errorInfo,
                l_ex);

            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        //execute()メソッドをコールする。
        try
        {
            l_response =
                (WEB3BondDomesticApplyInputResponse)l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response =
                (WEB3BondDomesticApplyInputResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request,
                "execute()メソッドをコールすることが失敗しました。",
                l_ex);

            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
}
@
