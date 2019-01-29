head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.03.42.28;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	WEB3AdminFeqExecutionReferenceHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者外国株式注文約定照会ハンドラ(WEB3AdminFeqExecutionReferenceHandler.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/07/22 鄭海良(中訊) 新規作成
                 : 2005/08/03 韋念瓊(中訊) レビュー
*/

package webbroker3.adminorderexecinquiry.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.adminorderexecinquiry.service.delegate.WEB3AdminFeqExecutionReferenceService;
import webbroker3.adminorderexecinquiry.message.WEB3AdminORFeqOrderExecutionRefInputResponse;
import webbroker3.adminorderexecinquiry.message.WEB3AdminORFeqOrderExecutionRefInputRequest;
import webbroker3.adminorderexecinquiry.message.WEB3AdminORFeqOrderExecutionRefReferenceRequest;
import webbroker3.adminorderexecinquiry.message.WEB3AdminORFeqOrderExecutionRefReferenceResponse;
import webbroker3.adminorderexecinquiry.message.WEB3AdminORFeqExecuteListResponse;
import webbroker3.adminorderexecinquiry.message.WEB3AdminORFeqExecuteListRequest;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.util.WEB3LogUtility;

/**
 * (管理者外国株式注文約定照会ハンドラ)<BR>
 * 管理者外国株式注文約定照会ハンドラクラス<BR>
 *
 * @@author 鄭海良
 * @@version 1.0
 */
public class WEB3AdminFeqExecutionReferenceHandler implements MessageHandler
{
    
    /**
     * ログ出力ユーティリティ。
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminFeqExecutionReferenceHandler.class);
    
    /**
     * @@roseuid 42CDFD9B003E
     */
    public WEB3AdminFeqExecutionReferenceHandler() 
    {
     
    }
    
    /**
     * (get入力画面)<BR>
     * 管理者外国株式注文約定照会入力画面表示処理を行う。<BR>
     * <BR>
     * 管理者外国株式注文約定照会サービスImplを取得し、<BR>
     * execute()メソッドをコールする。<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * 管理者・外国株式注文約定照会入力リクエストオブジェクト<BR>
     * @@return webbroker3.adminorderexecinquiry.message.WEB3AdminORFeqOrderExecutionRefInputResponse
     * @@roseuid 42A67D4702C0
     */
    public WEB3AdminORFeqOrderExecutionRefInputResponse getInputScreen(
        WEB3AdminORFeqOrderExecutionRefInputRequest l_request) 
    {
        final String STR_METHOD_NAME = " getInputScreen(WEB3AdminORFeqOrderExecutionRefInputRequest )";
        log.entering(STR_METHOD_NAME);

        if (l_request == null)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME,
                "リクエストがnullです。");
        }

        WEB3AdminORFeqOrderExecutionRefInputResponse l_response = null;
        WEB3AdminFeqExecutionReferenceService l_service = null;

        try
        {
            //get管理者外国株式注文約定照会サービス
            l_service = (WEB3AdminFeqExecutionReferenceService)
                Services.getService(WEB3AdminFeqExecutionReferenceService.class);
            if (l_service == null)
            {
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                    this.getClass().getName() + STR_METHOD_NAME,
                    "管理者外国株式注文約定照会サービスを取得に失敗しました。");
            }
            //execute
            l_response = (WEB3AdminORFeqOrderExecutionRefInputResponse)l_service.execute(l_request);//WEB3BaseException
        }        
        catch (WEB3BaseException l_ex)
        {
            l_response = (WEB3AdminORFeqOrderExecutionRefInputResponse)l_request.createResponse();
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
                "get入力画面に失敗しました。",
                l_response.errorInfo,
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        catch (Exception l_ex)
        {
            l_response = (WEB3AdminORFeqOrderExecutionRefInputResponse) l_request.createResponse();
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
                "get入力画面に失敗しました。",
                l_response.errorInfo, 
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * (get照会画面)<BR>
     * 管理者外国株式注文約定照会処理を行う。<BR>
     * <BR>
     * 管理者外国株式注文約定照会サービスImplを取得し、<BR>
     * execute()メソッドをコールする。<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * 管理者・外国株式注文約定照会リクエストオブジェクト<BR>
     * @@return webbroker3.adminorderexecinquiry.message.WEB3AdminORFeqOrderExecutionRefReferenceResponse
     * @@roseuid 42A67DAB004F
     */
    public WEB3AdminORFeqOrderExecutionRefReferenceResponse getReferenceScreen(
        WEB3AdminORFeqOrderExecutionRefReferenceRequest l_request) 
    {
        final String STR_METHOD_NAME = " getReferenceScreen(WEB3AdminORFeqOrderExecutionRefReferenceRequest )";
        log.entering(STR_METHOD_NAME);

        if (l_request == null)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME,
                "リクエストがnullです。");
        }

        WEB3AdminORFeqOrderExecutionRefReferenceResponse l_response = null;
        WEB3AdminFeqExecutionReferenceService l_service = null;

        try
        {
            //get管理者外国株式注文約定照会サービス
            l_service = (WEB3AdminFeqExecutionReferenceService)
                Services.getService(WEB3AdminFeqExecutionReferenceService.class);
            if (l_service == null)
            {
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                    this.getClass().getName() + STR_METHOD_NAME,
                    "管理者外国株式注文約定照会サービスを取得に失敗しました。");
            }
            //execute
            l_response = (WEB3AdminORFeqOrderExecutionRefReferenceResponse)l_service.execute(l_request);//WEB3BaseException
        }        
        catch (WEB3BaseException l_ex)
        {
            l_response = (WEB3AdminORFeqOrderExecutionRefReferenceResponse)l_request.createResponse();
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
                "get照会画面に失敗しました。",
                l_response.errorInfo,
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        catch (Exception l_ex)
        {
            l_response = (WEB3AdminORFeqOrderExecutionRefReferenceResponse) l_request.createResponse();
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
                "get照会画面に失敗しました。",
                l_response.errorInfo, 
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * (get出来入力一覧)<BR>
     * 管理者外国株式出来入力一覧処理を行う。<BR>
     * <BR>
     * 管理者外国株式注文約定照会サービスImplを取得し、<BR>
     * execute()メソッドをコールする。<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * 管理者・外国株式出来入力一覧リクエストオブジェクト<BR>
     * @@return webbroker3.adminorderexecinquiry.message.WEB3AdminORFeqExecuteListResponse
     * @@roseuid 42AD06F00201
     */
    public WEB3AdminORFeqExecuteListResponse getExecInputList(
        WEB3AdminORFeqExecuteListRequest l_request) 
    {
        final String STR_METHOD_NAME = " getExecInputList(WEB3AdminORFeqExecuteListRequest )";
        log.entering(STR_METHOD_NAME);

        if (l_request == null)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME,
                "リクエストがnullです。");
        }

        WEB3AdminORFeqExecuteListResponse l_response = null;
        WEB3AdminFeqExecutionReferenceService l_service = null;

        try
        {
            //get管理者外国株式注文約定照会サービス
            l_service = (WEB3AdminFeqExecutionReferenceService)
                Services.getService(WEB3AdminFeqExecutionReferenceService.class);
            if (l_service == null)
            {
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                    this.getClass().getName() + STR_METHOD_NAME,
                    "管理者外国株式注文約定照会サービスを取得に失敗しました。");
            }
            //execute
            l_response = (WEB3AdminORFeqExecuteListResponse)l_service.execute(l_request);//WEB3BaseException
        }        
        catch (WEB3BaseException l_ex)
        {
            l_response = (WEB3AdminORFeqExecuteListResponse)l_request.createResponse();
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
                "get出来入力一覧に失敗しました。",
                l_response.errorInfo,
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        catch (Exception l_ex)
        {
            l_response = (WEB3AdminORFeqExecuteListResponse) l_request.createResponse();
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
                "get出来入力一覧に失敗しました。",
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
