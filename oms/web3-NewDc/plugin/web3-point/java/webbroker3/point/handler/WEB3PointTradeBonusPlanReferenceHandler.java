head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.22.08.02.07;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4e44d8854634b20;
filename	WEB3PointTradeBonusPlanReferenceHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : トレードボーナスプラン照会ハンドラ(WEB3PointTradeBonusPlanReferenceHandler.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/02/25 鄭海良(中訊) 新規作成
*/
package webbroker3.point.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.point.message.WEB3PointTradeBonusPlanReferenceResponse;
import webbroker3.point.message.WEB3PointTradeBonusPlanReferenceRequest;
import webbroker3.point.service.delegate.WEB3PointTradeBonusPlanReferenceService;
import webbroker3.util.WEB3LogUtility;


/**
 * (トレードボーナスプラン照会ハンドラ)<BR>
 * トレードボーナスプラン照会ハンドラクラス<BR>
 *
 * @@author 鄭海良
 * @@version 1.0
 */
public class WEB3PointTradeBonusPlanReferenceHandler implements MessageHandler
{

    /**
     * ログ出力ユーティリティ。<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3PointTradeBonusPlanReferenceHandler.class);        
    
    /**
     * @@roseuid 421D7B380271
     */
    public WEB3PointTradeBonusPlanReferenceHandler() 
    {
     
    }
    
    /**
     * (トレードボーナスプラン照会)<BR>
     * トレードボーナスプラン照会サービスを取得し、execute()メソッドをコールする。<BR>
     * トレードボーナスプラン照会<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * リクエストデータ<BR>
     * 
     * @@return webbroker3.point.message.WEB3PointTradeBonusPlanReferenceResponse
     * @@roseuid 42071DE300F7
     */
    public WEB3PointTradeBonusPlanReferenceResponse tradeBonusPlanReference(WEB3PointTradeBonusPlanReferenceRequest l_request) 
    {
        final String STR_METHOD_NAME = " tradeBonusPlanReference(WEB3PointTradeBonusPlanReferenceRequest )";    
        log.entering(STR_METHOD_NAME);
        
        WEB3PointTradeBonusPlanReferenceResponse l_response = null;
        WEB3PointTradeBonusPlanReferenceService l_service = null;      

        try
        {
            //get service
            l_service = 
                (WEB3PointTradeBonusPlanReferenceService)Services.getService(
                    WEB3PointTradeBonusPlanReferenceService.class);
        }
        catch (Exception l_ex)
        {
            if (l_request == null)
            {
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                    this.getClass().getName() + STR_METHOD_NAME);
            }
            l_response = (WEB3PointTradeBonusPlanReferenceResponse)l_request.createResponse();
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
                "トレードボーナスプラン照会サービスの取得に失敗しました。",
                l_response.errorInfo,
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;                  
        }

        try
        {
            //execute()メソッドをコールする
            if (l_service == null)
            {
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                    this.getClass().getName() + STR_METHOD_NAME);
            }

            l_response = (WEB3PointTradeBonusPlanReferenceResponse)l_service.execute(l_request);//WEB3BaseException
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
            l_response = (WEB3PointTradeBonusPlanReferenceResponse)l_request.createResponse();
            if (l_response == null)
            {
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                    this.getClass().getName() + STR_METHOD_NAME);
            }
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request, "トレードボーナスプラン照会に失敗しました。", l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

        log.exiting(STR_METHOD_NAME);
        return l_response;    
    }
}
@
