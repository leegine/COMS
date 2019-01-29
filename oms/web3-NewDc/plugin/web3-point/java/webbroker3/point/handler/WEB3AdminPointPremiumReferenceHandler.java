head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.22.08.02.03;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4e44d8854634b20;
filename	WEB3AdminPointPremiumReferenceHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 景品一覧ハンドラ(WEB3AdminPointPremiumReferenceHandler.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/01/05 張学剛(中訊) 新規作成
*/
package webbroker3.point.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.point.message.WEB3AdminPointPremiumReferenceRequest;
import webbroker3.point.message.WEB3AdminPointPremiumReferenceResponse;
import webbroker3.point.service.delegate.WEB3AdminPointPremiumReferenceService;
import webbroker3.util.WEB3LogUtility;

/**
 * (景品一覧ハンドラ)<BR>
 * 景品一覧ハンドラクラス<BR>
 *
 * @@author 張学剛
 * @@version 1.0
 */
public class WEB3AdminPointPremiumReferenceHandler implements MessageHandler 
{
    /**
     * Logger
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(WEB3AdminPointPremiumReferenceHandler.class);
    
    /**
     * (景品一覧リクエスト)<BR>
     * 景品一覧サービスを取得し、execute()メソッドをコールする。 <BR>
     * @@param l_request - (リクエストデータ)<BR>
     * リクエストデータ<BR>
     * 
     * @@return webbroker3.point.message.WEB3AdminPointPremiumReferenceResponse
     * @@roseuid 4192D3FA0164
     */
    public WEB3AdminPointPremiumReferenceResponse premiumReferenceRequest(WEB3AdminPointPremiumReferenceRequest l_request) 
    {
        final String STR_METHOD_NAME = " premiumReferenceRequest(WEB3AdminPointPremiumReferenceRequest)";
        log.entering(STR_METHOD_NAME);
        
        WEB3AdminPointPremiumReferenceResponse l_response = null;
        WEB3AdminPointPremiumReferenceService l_service = null;
        
        try
        {
            l_service = (WEB3AdminPointPremiumReferenceService)
                Services.getService(WEB3AdminPointPremiumReferenceService.class);//Exception
        }        
        // サービスで例外が発生した場合は、エラー情報をレスポンスメッセージに設定する
        catch (Exception l_ex)
        {
            if (l_request == null)
            {
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                    this.getClass().getName() + STR_METHOD_NAME);
            }
            l_response = (WEB3AdminPointPremiumReferenceResponse) l_request.createResponse();
            if (l_response == null)
            {
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                    this.getClass().getName() + STR_METHOD_NAME);
            }

            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;    
            log.error(
                l_request, 
                " 景品一覧サービスを取得に失敗しました。",
                l_response.errorInfo, 
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        
        try
        {
            if (l_service == null)
            {
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                    this.getClass().getName() + STR_METHOD_NAME);
            }

            l_response = (WEB3AdminPointPremiumReferenceResponse)l_service.execute(l_request);//WEB3BaseException
        }
        catch (WEB3BaseException l_ex)
        {
            if (l_request == null)
            {
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                    this.getClass().getName() + STR_METHOD_NAME);
            }
            l_response = (WEB3AdminPointPremiumReferenceResponse)l_request.createResponse();
            if (l_response == null)
            {
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                    this.getClass().getName() + STR_METHOD_NAME);
            }

            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request, " 景品一覧サービスを取得リクエストに失敗しました。", l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        
        log.exiting(STR_METHOD_NAME);
        
        return l_response;
    }
}
@
