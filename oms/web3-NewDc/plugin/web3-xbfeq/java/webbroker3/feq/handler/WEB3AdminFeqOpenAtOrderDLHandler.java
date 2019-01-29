head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.44;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3AdminFeqOpenAtOrderDLHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 外国株式寄付注文DLハンドラ(WEB3AdminFeqOpenAtOrderDLHandler.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/07/20 戴義波 (中訊) 新規作成
                   2005/08/03 黄建(中訊) レビュー     
*/

package webbroker3.feq.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.feq.message.WEB3AdminFeqOpenAtOrderDownloadInputRequest;
import webbroker3.feq.message.WEB3AdminFeqOpenAtOrderDownloadInputResponse;
import webbroker3.feq.message.WEB3AdminFeqOpenAtOrderDownloadRequest;
import webbroker3.feq.message.WEB3AdminFeqOpenAtOrderDownloadResponse;
import webbroker3.feq.service.delegate.WEB3AdminFeqOpenAtOrderDLService;
import webbroker3.util.WEB3LogUtility;

/**
 * (外国株式寄付注文DLハンドラ)<BR>
 * 外国株式寄付注文DLハンドラクラス<BR>
 *   
 * @@author 戴義波
 * @@version 1.0
 */
public class WEB3AdminFeqOpenAtOrderDLHandler implements MessageHandler 
{
    
    /**
     * ログ出力ユーティリティ。
     */
    private static WEB3LogUtility log =
            WEB3LogUtility.getInstance(WEB3AdminFeqOpenAtOrderDLHandler.class);
    
    /**
     * @@roseuid 42D0DA1600BB
     */
    public WEB3AdminFeqOpenAtOrderDLHandler() 
    {
     
    }
    
    /**
     * (get入力画面)<BR>
     * 外国株式寄付注文DLサービスを取得し、execute()メソッドをコールする。<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * リクエストデータ<BR>
     * 
     * @@return webbroker3.feq.message.WEB3AdminFeqOpenAtOrderDownloadInputResponse
     * @@roseuid 421494D4010B
     */
    public WEB3AdminFeqOpenAtOrderDownloadInputResponse getInputScreen(WEB3AdminFeqOpenAtOrderDownloadInputRequest l_request) 
    {
        final String STR_METHOD_NAME = " getInputScreen(WEB3AdminFeqOpenAtOrderDownloadInputRequest)";
        log.entering(STR_METHOD_NAME);

        if (l_request == null)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME,
                "リクエストがnullです。");
        }

        WEB3AdminFeqOpenAtOrderDownloadInputResponse l_response = null;
        WEB3AdminFeqOpenAtOrderDLService l_service = null;

        try
        {            
            //get外国株式寄付注文DLサービス
            l_service = (WEB3AdminFeqOpenAtOrderDLService)
                Services.getService(WEB3AdminFeqOpenAtOrderDLService.class);
            if (l_service == null)
            {
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                    this.getClass().getName() + STR_METHOD_NAME,
                    "外国株式寄付注文DLサービスを取得に失敗しました。");
            }
            //execute
            l_response = (WEB3AdminFeqOpenAtOrderDownloadInputResponse)l_service.execute(l_request);//WEB3BaseException
        }        
        catch (WEB3BaseException l_ex)
        {
            l_response = (WEB3AdminFeqOpenAtOrderDownloadInputResponse)l_request.createResponse();
            if (l_response == null)
            {
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                    this.getClass().getName() + STR_METHOD_NAME);
            }
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request, 
                "入力画面表示処理を実施に失敗しました。",
                l_response.errorInfo,
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        catch (Exception l_ex)
        {
            l_response = (WEB3AdminFeqOpenAtOrderDownloadInputResponse) l_request.createResponse();
            if (l_response == null)
            {
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                    this.getClass().getName() + STR_METHOD_NAME);
            }

            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;    
            log.error(
                l_request, 
                "入力画面表示処理を実施に失敗しました。",
                l_response.errorInfo, 
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * (getダウンロードファ@イル)<BR>
     * 外国株式寄付注文DLサービスを取得し、execute()メソッドをコールする。<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * リクエストデータ<BR>
     * 
     * @@return webbroker3.feq.message.WEB3AdminFeqOpenAtOrderDownloadResponse
     * @@roseuid 4214951C0291
     */
    public WEB3AdminFeqOpenAtOrderDownloadResponse getDownloadFile(WEB3AdminFeqOpenAtOrderDownloadRequest l_request) 
    {
        final String STR_METHOD_NAME = " getDownloadFile(WEB3AdminFeqOpenAtOrderDownloadRequest)";
        log.entering(STR_METHOD_NAME);

        if (l_request == null)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME,
                "リクエストがnullです。");
        }

        WEB3AdminFeqOpenAtOrderDownloadResponse l_response = null;
        WEB3AdminFeqOpenAtOrderDLService l_service = null;

        try
        {
            //get外国株式寄付注文DLサービス
            l_service = (WEB3AdminFeqOpenAtOrderDLService)
                Services.getService(WEB3AdminFeqOpenAtOrderDLService.class);
            if (l_service == null)
            {
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                    this.getClass().getName() + STR_METHOD_NAME,
                    "外国株式寄付注文DLサービスを取得に失敗しました。");
            }
            //execute
            l_response = (WEB3AdminFeqOpenAtOrderDownloadResponse)l_service.execute(l_request);//WEB3BaseException
        }        
        catch (WEB3BaseException l_ex)
        {
            l_response = (WEB3AdminFeqOpenAtOrderDownloadResponse)l_request.createResponse();
            if (l_response == null)
            {
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                    this.getClass().getName() + STR_METHOD_NAME);
            }
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request, 
                "getダウンロードファ@イル処理を実施に失敗しました。",
                l_response.errorInfo,
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        catch (Exception l_ex)
        {
            l_response = (WEB3AdminFeqOpenAtOrderDownloadResponse) l_request.createResponse();
            if (l_response == null)
            {
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                    this.getClass().getName() + STR_METHOD_NAME);
            }

            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;    
            log.error(
                l_request, 
                "getダウンロードファ@イル処理を実施に失敗しました。",
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
