head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.43;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3AdminFeqUploadErrCancelHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者外国株式同時ｱｯﾌﾟﾛｰﾄﾞｴﾗｰ解除ハンドラ(WEB3AdminFeqUploadErrCancelHandler.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/07/22 郭英 (中訊) 新規作成
                 : 2005/08/01 韋念瓊(中訊) レビュー   
*/

package webbroker3.feq.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.feq.message.WEB3AdminFeqUploadErrCancelCompleteRequest;
import webbroker3.feq.message.WEB3AdminFeqUploadErrCancelCompleteResponse;
import webbroker3.feq.message.WEB3AdminFeqUploadErrCancelConfirmRequest;
import webbroker3.feq.message.WEB3AdminFeqUploadErrCancelConfirmResponse;
import webbroker3.feq.message.WEB3AdminFeqUploadErrCancelInputRequest;
import webbroker3.feq.message.WEB3AdminFeqUploadErrCancelInputResponse;
import webbroker3.feq.service.delegate.WEB3AdminFeqUploadErrCancelService;
import webbroker3.util.WEB3LogUtility;

/**
 * (管理者外国株式同時ｱｯﾌﾟﾛｰﾄﾞｴﾗｰ解除ハンドラ)<BR>
 * 管理者外国株式同時ｱｯﾌﾟﾛｰﾄﾞｴﾗｰ解除ハンドラ<BR>
 *   
 * @@author 郭英
 * @@version 1.0
 */
public class WEB3AdminFeqUploadErrCancelHandler implements MessageHandler 
{
    
    /**
     * ログ出力ユーティリティ。
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminFeqUploadErrCancelHandler.class);
        
    /**
     * @@roseuid 42D0DA17033C
     */
    public WEB3AdminFeqUploadErrCancelHandler() 
    {
     
    }
    
    /**
     * (get入力画面)<BR>
     * 入力画面表示処理を実施する。<BR>
     * <BR>
     * 管理者外国株式同時ｱｯﾌﾟﾛｰﾄﾞｴﾗｰ解除サービス.execute()をコールする。<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * 管理者外国株式同時ｱｯﾌﾟﾛｰﾄﾞｴﾗｰ解除入力リクエスト<BR>
     * @@return webbroker3.feq.message.WEB3AdminFeqUploadErrCancelInputResponse
     * @@roseuid 42BBB5B7027F
     */
    public WEB3AdminFeqUploadErrCancelInputResponse getInputScreen(WEB3AdminFeqUploadErrCancelInputRequest l_request) 
    {
        final String STR_METHOD_NAME = " getInputScreen(WEB3AdminFeqUploadErrCancelInputRequest)";
        log.entering(STR_METHOD_NAME);

        if (l_request == null)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME,
                "リクエストがnullです。");
        }

        WEB3AdminFeqUploadErrCancelInputResponse l_response = null;
        WEB3AdminFeqUploadErrCancelService l_service = null;

        try
        {            
            //get管理者外国株式同時ｱｯﾌﾟﾛｰﾄﾞｴﾗｰ解除サービス
            l_service = (WEB3AdminFeqUploadErrCancelService)
                Services.getService(WEB3AdminFeqUploadErrCancelService.class);
            if (l_service == null)
            {
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                    this.getClass().getName() + STR_METHOD_NAME,
                    "管理者外国株式同時ｱｯﾌﾟﾛｰﾄﾞｴﾗｰ解除サービスを取得に失敗しました。");
            }
            //execute
            l_response = (WEB3AdminFeqUploadErrCancelInputResponse)l_service.execute(l_request);//WEB3BaseException
        }        
        catch (WEB3BaseException l_ex)
        {
            l_response = (WEB3AdminFeqUploadErrCancelInputResponse)l_request.createResponse();
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
                "get入力画面を実施に失敗しました。",
                l_response.errorInfo,
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        catch (Exception l_ex)
        {
            l_response = (WEB3AdminFeqUploadErrCancelInputResponse) l_request.createResponse();
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
                "get入力画面を実施に失敗しました。",
                l_response.errorInfo, 
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * (validate解除)<BR>
     * 確認画面表示処理を実施する。<BR>
     * <BR>
     * 管理者外国株式同時ｱｯﾌﾟﾛｰﾄﾞｴﾗｰ解除サービス.execute()をコールする。<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * 管理者外国株式同時ｱｯﾌﾟﾛｰﾄﾞｴﾗｰ解除確認リクエスト<BR>
     * @@return webbroker3.feq.message.WEB3AdminFeqUploadErrCancelConfirmResponse
     * @@roseuid 42BBB5BD0147
     */
    public WEB3AdminFeqUploadErrCancelConfirmResponse validateRelease(WEB3AdminFeqUploadErrCancelConfirmRequest l_request) 
    {
        final String STR_METHOD_NAME = " validateRelease(WEB3AdminFeqUploadErrCancelConfirmRequest)";
        log.entering(STR_METHOD_NAME);

        if (l_request == null)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME,
                "リクエストがnullです。");
        }

        WEB3AdminFeqUploadErrCancelConfirmResponse l_response = null;
        WEB3AdminFeqUploadErrCancelService l_service = null;

        try
        {            
            //get管理者外国株式同時ｱｯﾌﾟﾛｰﾄﾞｴﾗｰ解除サービス
            l_service = (WEB3AdminFeqUploadErrCancelService)
                Services.getService(WEB3AdminFeqUploadErrCancelService.class);
            if (l_service == null)
            {
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                    this.getClass().getName() + STR_METHOD_NAME,
                    "管理者外国株式同時ｱｯﾌﾟﾛｰﾄﾞｴﾗｰ解除サービスを取得に失敗しました。");
            }
            //execute
            l_response = (WEB3AdminFeqUploadErrCancelConfirmResponse)l_service.execute(l_request);//WEB3BaseException
        }        
        catch (WEB3BaseException l_ex)
        {
            l_response = (WEB3AdminFeqUploadErrCancelConfirmResponse)l_request.createResponse();
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
                "validate解除を実施に失敗しました。",
                l_response.errorInfo,
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        catch (Exception l_ex)
        {
            l_response = (WEB3AdminFeqUploadErrCancelConfirmResponse) l_request.createResponse();
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
                "validate解除を実施に失敗しました。",
                l_response.errorInfo, 
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * (submit解除)<BR>
     * 同時ｱｯﾌﾟﾛｰﾄﾞ解除処理を実施する。<BR>
     * <BR>
     * 管理者外国株式同時ｱｯﾌﾟﾛｰﾄﾞｴﾗｰ解除サービス.execute()をコールする。<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * 管理者外国株式同時ｱｯﾌﾟﾛｰﾄﾞｴﾗｰ解除完了リクエスト<BR>
     * @@return webbroker3.feq.message.WEB3AdminFeqUploadErrCancelCompleteResponse
     * @@roseuid 42BBB5C2000E
     */
    public WEB3AdminFeqUploadErrCancelCompleteResponse submitRelease(WEB3AdminFeqUploadErrCancelCompleteRequest l_request) 
    {
        final String STR_METHOD_NAME = " submitRelease(WEB3AdminFeqUploadErrCancelCompleteRequest)";
        log.entering(STR_METHOD_NAME);

        if (l_request == null)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME,
                "リクエストがnullです。");
        }

        WEB3AdminFeqUploadErrCancelCompleteResponse l_response = null;
        WEB3AdminFeqUploadErrCancelService l_service = null;

        try
        {            
            //get管理者外国株式同時ｱｯﾌﾟﾛｰﾄﾞｴﾗｰ解除サービス
            l_service = (WEB3AdminFeqUploadErrCancelService)
                Services.getService(WEB3AdminFeqUploadErrCancelService.class);
            if (l_service == null)
            {
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                    this.getClass().getName() + STR_METHOD_NAME,
                    "管理者外国株式同時ｱｯﾌﾟﾛｰﾄﾞｴﾗｰ解除サービスを取得に失敗しました。");
            }
            //execute
            l_response = (WEB3AdminFeqUploadErrCancelCompleteResponse)l_service.execute(l_request);//WEB3BaseException
        }        
        catch (WEB3BaseException l_ex)
        {
            l_response = (WEB3AdminFeqUploadErrCancelCompleteResponse)l_request.createResponse();
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
                "submit解除を実施に失敗しました。",
                l_response.errorInfo,
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        catch (Exception l_ex)
        {
            l_response = (WEB3AdminFeqUploadErrCancelCompleteResponse) l_request.createResponse();
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
                "submit解除を実施に失敗しました。",
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
