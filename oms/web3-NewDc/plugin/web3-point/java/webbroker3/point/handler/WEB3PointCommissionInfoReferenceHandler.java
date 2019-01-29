head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.22.08.02.53;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4e44d8854634b20;
filename	WEB3PointCommissionInfoReferenceHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 株式手数料無料情報照会ハンドラ(WEB3PointCommissionInfoReferenceHandler.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/02/25 張学剛(中訊) 新規作成
*/
package webbroker3.point.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.point.message.WEB3PointCommissionInfoReferenceRequest;
import webbroker3.point.message.WEB3PointCommissionInfoReferenceResponse;
import webbroker3.point.service.delegate.WEB3PointCommissionInfoReferenceService;
import webbroker3.util.WEB3LogUtility;

/**
 * (株式手数料無料情報照会ハンドラ)<BR>
 * 株式手数料無料情報照会ハンドラクラス<BR>
 * 
 * @@author 張学剛
 * @@version 1.0
 */
public class WEB3PointCommissionInfoReferenceHandler implements MessageHandler
{
    /**
     * ログ出力ユーティリティ。<BR>
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(WEB3PointCommissionInfoReferenceHandler.class);

    /**
     * @@roseuid 421D7B3802DE
     */
    public WEB3PointCommissionInfoReferenceHandler() 
    {
     
    }
    
    /**
     * (株式手数料無料情報照会)<BR>
     * 株式手数料無料情報照会サービスを取得し、execute()メソッドをコールする。<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * リクエストデータ<BR>
     * 
     * @@return webbroker3.point.message.WEB3PointCommissionInfoReferenceResponse
     * @@roseuid 4207160B0174
     */
    public WEB3PointCommissionInfoReferenceResponse commissionInfoReference(WEB3PointCommissionInfoReferenceRequest l_request) 
    {
        final String STR_METHOD_NAME = " commissionInfoReference(WEB3PointCommissionInfoReferenceRequest)";
        log.entering(STR_METHOD_NAME);
        
        WEB3PointCommissionInfoReferenceResponse l_response = null;
        WEB3PointCommissionInfoReferenceService l_service = null;
        
        try
        {
            l_service = (WEB3PointCommissionInfoReferenceService)
                Services.getService(WEB3PointCommissionInfoReferenceService.class);//Exception
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
            
            l_response = (WEB3PointCommissionInfoReferenceResponse) l_request.createResponse();
            
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
                " 株式手数料無料情報照会サービスを取得に失敗しました。",
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

            l_response = (WEB3PointCommissionInfoReferenceResponse)l_service.execute(l_request);//WEB3BaseException
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
            
            l_response = (WEB3PointCommissionInfoReferenceResponse)l_request.createResponse();
            
            if (l_response == null)
            {
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                    this.getClass().getName() + STR_METHOD_NAME);
            }

            l_response.errorInfo = l_ex.getErrorInfo();
            
            log.error(l_request, " 株式手数料無料情報照会サービスを取得リクエストに失敗しました。", l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        
        log.exiting(STR_METHOD_NAME);
        
        return l_response;
    }
}
@
