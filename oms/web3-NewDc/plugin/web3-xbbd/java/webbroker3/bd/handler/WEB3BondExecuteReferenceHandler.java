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
filename	WEB3BondExecuteReferenceHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 債券注文約定照会ハンドラ (WEB3BondExecuteReferenceHandler.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/09/21 周捷 (中訊) 新規作成
*/
package webbroker3.bd.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.bd.message.WEB3BondExecuteReferenceRequest;
import webbroker3.bd.message.WEB3BondExecuteReferenceResponse;
import webbroker3.bd.service.delegate.WEB3BondExecuteReferenceService;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.util.WEB3LogUtility;

/**
 * (債券注文約定照会ハンドラ )<BR>
 * 債券注文約定照会ハンドラクラス
 * 
 * @@author 周捷
 * @@version 1.0
 */
public class WEB3BondExecuteReferenceHandler implements MessageHandler
{
    /**
     * ログユーティリティ
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3BondExecuteReferenceHandler.class); 
    /**
     * @@roseuid 44E336310000
     */
    public WEB3BondExecuteReferenceHandler() 
    {
     
    }
    
    /**
     * (get注文約定照会)<BR>
     * 債券注文約定照会処理を実施する。 <BR>
     * <BR>
     * 債券注文約定照会サービスを取得し、 <BR>
     * execute()メソッドをコールする。<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * 債券注文約定照会リクエストオブジェクト
     * @@return WEB3BondExecuteReferenceResponse
     * @@roseuid 44B6061E038D
     */
    public WEB3BondExecuteReferenceResponse getExecuteReference(
        WEB3BondExecuteReferenceRequest l_request) 
    {
        final String STR_METHOD_NAME = 
            "getExecuteReference(WEB3BondExecuteReferenceRequest)";
        log.entering(STR_METHOD_NAME);

        //債券注文約定照会サービスを取得し
        WEB3BondExecuteReferenceService l_service = null;
        WEB3BondExecuteReferenceResponse l_response = null;
        try
        {
            l_service =
                (WEB3BondExecuteReferenceService) 
                Services.getService(WEB3BondExecuteReferenceService.class);
        }
        // サービスで例外が発生した場合は、エラー情報をレスポンスメッセージに設定する
        catch (Exception l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            l_response =
                (WEB3BondExecuteReferenceResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "債券注文約定照会サービスを取得に失敗しました。",
                l_response.errorInfo,
                l_ex);
            return l_response;
        }
        try
        {
            //execute()メソッドをコールする
            l_response = (WEB3BondExecuteReferenceResponse) l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            l_response =
                (WEB3BondExecuteReferenceResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request, 
                "債券注文約定照会処理が失敗しました。", 
                l_ex.getErrorInfo(), 
                l_ex);
            return l_response;
        }
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
}
@
