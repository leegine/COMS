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
filename	WEB3EquityMarginExecuteReferenceHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 株式・信用注文約定照会ハンドラ(WEB3EquityMarginExecuteReferenceHandler.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2007/01/10 金　@傑(中訊) 新規作成
*/

package webbroker3.equity.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.equity.message.WEB3EquityMarginExecuteReferenceRequest;
import webbroker3.equity.message.WEB3EquityMarginExecuteReferenceResponse;
import webbroker3.equity.service.delegate.WEB3EquityMarginExecuteReferenceService;
import webbroker3.util.WEB3LogUtility;

/**
 * (株式・信用注文約定照会ハンドラ)<BR>
 * 株式・信用注文約定照会ハンドラクラス<BR>
 * @@ author 金　@傑 <BR>
 * @@ version 1.0<BR>
 */
public class WEB3EquityMarginExecuteReferenceHandler implements MessageHandler
{
    /**
     * ログ出力ユーティリティ。<BR>
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(WEB3EquityMarginExecuteReferenceHandler.class);

    /**
     * @@roseuid 45A3606F005D
     */
    public WEB3EquityMarginExecuteReferenceHandler() 
    {

    }

    /**
     * (search注文約定照会)<BR>
     * 株式・信用注文約定照会サービスを取得し、execute()メソッドをコールする。<BR>
     * <BR>
     * @@param l_request - (リクエストデータ)<BR>
     * リクエストデータ<BR>
     * <BR>
     * @@return WEB3EquityMarginExecuteReferenceResponse
     * @@roseuid 455D1FD303CB
     */
    public WEB3EquityMarginExecuteReferenceResponse searchExecuteReference(
         WEB3EquityMarginExecuteReferenceRequest l_request) 
    {
		final String STR_METHOD_NAME = "searchExecuteReference(WEB3EquityMarginExecuteReferenceRequest)";
		log.entering(STR_METHOD_NAME);
		WEB3EquityMarginExecuteReferenceResponse l_response = null;
		WEB3EquityMarginExecuteReferenceService l_service = null;
		try
		{
			l_service = (WEB3EquityMarginExecuteReferenceService) Services
					.getService(WEB3EquityMarginExecuteReferenceService.class);
		}
		catch (Exception l_exception)
		{
			l_response = (WEB3EquityMarginExecuteReferenceResponse) l_request.createResponse();
			l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
			log.error(l_request, "株式・信用注文約定照会サービスの取得に失敗しました。", l_response.errorInfo, l_exception);
			log.exiting(STR_METHOD_NAME);
			return l_response;
		}
		try
		{
			l_response = (WEB3EquityMarginExecuteReferenceResponse) l_service.execute(l_request);

		}
		catch (WEB3BaseException l_wbex)
		{
			log.error(l_request, "株式・信用注文約定照会に失敗しました。", l_wbex);
			l_response = (WEB3EquityMarginExecuteReferenceResponse) l_request.createResponse();
			l_response.errorInfo = l_wbex.getErrorInfo();
		}
		log.exiting(STR_METHOD_NAME);
		return l_response;
	}
}
@
