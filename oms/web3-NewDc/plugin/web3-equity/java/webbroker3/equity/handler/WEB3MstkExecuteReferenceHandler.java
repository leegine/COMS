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
filename	WEB3MstkExecuteReferenceHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 株式ミニ投資注文約定照会ハンドラ(WEB3MstkExecuteReferenceHandler.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/12  彭巍(中訊) 新規作成
                   2004/12/29 岡村和明(SRA) JavaDoc修正
*/
package webbroker3.equity.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.equity.message.WEB3MstkExecuteReferenceRequest;
import webbroker3.equity.message.WEB3MstkExecuteReferenceResponse;
import webbroker3.equity.service.delegate.WEB3MstkExecuteReferenceService;
import webbroker3.util.WEB3LogUtility;

/**
 * （株式ミニ投資注文約定照会ハンドラ）。<BR>
 * <BR>
 * 株式ミニ投資注文約定照会ハンドラクラス
 * @@author 彭巍
 * @@version 1.0
 */
public class WEB3MstkExecuteReferenceHandler implements MessageHandler 
{
    /**
     * （ログ出力ユーティリティ）。
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3MstkExecuteReferenceHandler.class);
    
    /**
     * 
     */
    public WEB3MstkExecuteReferenceHandler() 
    {
     
    }
    
    /**
     * （search注文約定照会）。<BR>
     * <BR>
     * 株式ミニ投資注文約定照会処理を実施する。<BR>
     * <BR>
     * 株式ミニ投資注文約定照会サービスを取得し、execute()<BR>
     * メソッドをコールする。
     * @@param l_request (リクエストデータ)<BR>
     * 　@　@　@株式ミニ投資注文約定照会リクエストデータオブジェクト
     * @@return WEB3MstkExecuteReferenceResponse
     */
    public WEB3MstkExecuteReferenceResponse handleSearchOrderExecuteReference(WEB3MstkExecuteReferenceRequest l_request) 
    {
        //株式ミ注文約定照会サービスオブジェクトを取得する
        final String STR_METHOD_NAME =
            "searchOrderExecuteReference(WEB3MarginExecuteReferenceRequest l_request)";

        log.entering(STR_METHOD_NAME);

        WEB3MstkExecuteReferenceResponse l_response = null;
        WEB3MstkExecuteReferenceService l_service = null;

        //株式ミニ投資注文約定照会サービスを取得し、execute()
        //処理結果であるレスポンスオブジェクトを取得する。
        try
        {
            l_service =
                (WEB3MstkExecuteReferenceService)Services.getService(
                    WEB3MstkExecuteReferenceService.class);
        }
        //サービスで例外が発生した場合は、エラー情報をレスポンスメッセージに設定する
        catch (Exception l_ex)
        {
            l_response =
                (WEB3MstkExecuteReferenceResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "株式ミ注文約定照会サービスの取得に失敗しました。",
                l_response.errorInfo,
                l_ex);
            return l_response;
        }

        try
        {
            l_response =
                (WEB3MstkExecuteReferenceResponse)l_service.execute(
                    l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response =
                (WEB3MstkExecuteReferenceResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request, "株式ミ注文約定照会に失敗しました。", l_ex);
            return l_response;
        }
		catch (WEB3BaseRuntimeException l_ex)
		{
			l_response =
				(WEB3MstkExecuteReferenceResponse)l_request.createResponse();
			l_response.errorInfo = l_ex.getErrorInfo();
			log.error(l_request, "株式ミ注文約定照会に失敗しました。", l_ex);
			return l_response;
		}

        log.exiting(STR_METHOD_NAME);

        //レスポンスオブジェクトを返却する。
        return l_response;
     
    }
}
@
