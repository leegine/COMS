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
filename	WEB3AdminFeqOrderVoucherListHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者外国株式当日注文伝票一覧ハンドラ(WEB3AdminFeqOrderVoucherListHandler.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/07/21 郭英 (中訊) 新規作成
                   2005/08/03 黄建(中訊) レビュー     
*/

package webbroker3.feq.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.feq.message.WEB3AdminFeqOrderVoucherListInputRequest;
import webbroker3.feq.message.WEB3AdminFeqOrderVoucherListInputResponse;
import webbroker3.feq.message.WEB3AdminFeqOrderVoucherListRequest;
import webbroker3.feq.message.WEB3AdminFeqOrderVoucherListResponse;
import webbroker3.feq.service.delegate.WEB3AdminFeqOrderVoucherListService;
import webbroker3.util.WEB3LogUtility;

/**
 * (管理者外国株式当日注文伝票一覧ハンドラ)<BR>
 * 管理者外国株式当日注文伝票一覧ハンドラクラス<BR>
 *   
 * @@author 郭英
 * @@version 1.0
 */
public class WEB3AdminFeqOrderVoucherListHandler implements MessageHandler 
{
    
    /**
     * ログ出力ユーティリティ。
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminFeqOrderVoucherListHandler.class);
        
    /**
     * @@roseuid 42D0DA170251
     */
    public WEB3AdminFeqOrderVoucherListHandler() 
    {
     
    }
    
    /**
     * (get入力画面)<BR>
     * 入力画面の表示に必要なデータを取得する。<BR>
     * <BR>
     * 管理者外国株式当日注文伝票一覧サービスを取得し、<BR>
     * execute()メソッドをコールする。<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * リクエストデータ<BR>
     * 
     * @@return webbroker3.feq.message.WEB3AdminFeqOrderVoucherListInputResponse
     * @@roseuid 429FC4ED036E
     */
    public WEB3AdminFeqOrderVoucherListInputResponse getInputScreen(WEB3AdminFeqOrderVoucherListInputRequest l_request) 
    {
        final String STR_METHOD_NAME = " getInputScreen(WEB3AdminFeqOrderVoucherListInputRequest)";
        log.entering(STR_METHOD_NAME);

        if (l_request == null)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME,
                "リクエストがnullです。");
        }

        WEB3AdminFeqOrderVoucherListInputResponse l_response = null;
        WEB3AdminFeqOrderVoucherListService l_service = null;

        try
        {            
            //get管理者外国株式当日注文伝票一覧サービス
            l_service = (WEB3AdminFeqOrderVoucherListService)
                Services.getService(WEB3AdminFeqOrderVoucherListService.class);
            if (l_service == null)
            {
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                    this.getClass().getName() + STR_METHOD_NAME,
                    "管理者外国株式当日注文伝票一覧サービスを取得に失敗しました。");
            }
            //execute
            l_response = (WEB3AdminFeqOrderVoucherListInputResponse)l_service.execute(l_request);//WEB3BaseException
        }        
        catch (WEB3BaseException l_ex)
        {
            l_response = (WEB3AdminFeqOrderVoucherListInputResponse)l_request.createResponse();
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
            l_response = (WEB3AdminFeqOrderVoucherListInputResponse) l_request.createResponse();
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
     * (get一覧画面)<BR>
     * 一覧画面の表示に必要なデータを取得する。<BR>
     * <BR>
     * 管理者外国株式当日注文伝票一覧サービスを取得し、<BR>
     * execute()メソッドをコールする。<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * リクエストデータ<BR>
     * 
     * @@return webbroker3.feq.message.WEB3AdminFeqOrderVoucherListResponse
     * @@roseuid 429FC69E038E
     */
    public WEB3AdminFeqOrderVoucherListResponse getListScreen(WEB3AdminFeqOrderVoucherListRequest l_request) 
    {
        final String STR_METHOD_NAME = " getListScreen(WEB3AdminFeqOrderVoucherListRequest)";
        log.entering(STR_METHOD_NAME);

        if (l_request == null)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME,
                "リクエストがnullです。");
        }

        WEB3AdminFeqOrderVoucherListResponse l_response = null;
        WEB3AdminFeqOrderVoucherListService l_service = null;

        try
        {            
            //get管理者外国株式当日注文伝票一覧サービス
            l_service = (WEB3AdminFeqOrderVoucherListService)
                Services.getService(WEB3AdminFeqOrderVoucherListService.class);
            if (l_service == null)
            {
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                    this.getClass().getName() + STR_METHOD_NAME,
                    "管理者外国株式当日注文伝票一覧サービスを取得に失敗しました。");
            }
            //execute
            l_response = (WEB3AdminFeqOrderVoucherListResponse)l_service.execute(l_request);//WEB3BaseException
        }        
        catch (WEB3BaseException l_ex)
        {
            l_response = (WEB3AdminFeqOrderVoucherListResponse)l_request.createResponse();
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
                "get一覧画面を実施に失敗しました。",
                l_response.errorInfo,
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        catch (Exception l_ex)
        {
            l_response = (WEB3AdminFeqOrderVoucherListResponse) l_request.createResponse();
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
                "get一覧画面を実施に失敗しました。",
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
