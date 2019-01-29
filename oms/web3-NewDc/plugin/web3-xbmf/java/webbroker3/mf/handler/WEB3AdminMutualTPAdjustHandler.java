head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.03;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3AdminMutualTPAdjustHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 投信管理者余力調整ハンドラクラス(WEB3AdminMutualTPAdjustHandler)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/12/19 韋念瓊 (中訊) 新規作成                   
*/

package webbroker3.mf.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.mf.message.WEB3AdminMutualTPAdjustCompleteRequest;
import webbroker3.mf.message.WEB3AdminMutualTPAdjustCompleteResponse;
import webbroker3.mf.message.WEB3AdminMutualTPAdjustConfirmRequest;
import webbroker3.mf.message.WEB3AdminMutualTPAdjustConfirmResponse;
import webbroker3.mf.service.delegate.WEB3AdminMutualTPAdjustService;
import webbroker3.util.WEB3LogUtility;

/**
 * 投信管理者余力調整ハンドラクラス
 * 
 * @@author 韋念瓊(中訊)
 * @@version 1.0
 */
public class WEB3AdminMutualTPAdjustHandler implements MessageHandler 
{
    /**
     * ログユーティリティ
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminMutualTPAdjustHandler.class);
 
    /**
     * (余力調整確認)
     * 投資信託余力調整確認処理を行う。<BR> 
     * <BR>
     * 投信管理者余力調整サービスを取得し、execute()メソッドをコールする。 <BR>
     * <BR>
     * @@param l_request - リクエストデータ
     * @@return WEB3AdminMutualTPAdjustConfirmResponse
     * @@throws WEB3BaseException
     * @@roseuid 40B2EDB5002C
     */
    public WEB3AdminMutualTPAdjustConfirmResponse tpAdjustConfirm(
        WEB3AdminMutualTPAdjustConfirmRequest l_request)
            throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = 
            "tpAdjustConfirm(WEB3AdminMutualTPAdjustConfirmRequest l_request)";
        log.entering(STR_METHOD_NAME);        
        
        //投信管理者余力調整サービスを取得し
        WEB3AdminMutualTPAdjustService l_service = null;
        WEB3AdminMutualTPAdjustConfirmResponse l_response = null;
        try
        {
            l_service =
                (WEB3AdminMutualTPAdjustService) 
                    Services.getService(WEB3AdminMutualTPAdjustService.class);
        }
        // サービスで例外が発生した場合は、エラー情報をレスポンスメッセージに設定する
        catch (Exception l_ex)
        {
            l_response =
                (WEB3AdminMutualTPAdjustConfirmResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "投信管理者余力調整サービスの取得に失敗しました。",
                l_response.errorInfo,
                l_ex);
            return l_response;   
        }
        try
        {
            //execute()メソッドをコールする
            l_response = (WEB3AdminMutualTPAdjustConfirmResponse) l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response = (WEB3AdminMutualTPAdjustConfirmResponse) l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request, 
                "投信管理者余力調整処理が失敗しました。",
                l_ex.getErrorInfo(), 
                l_ex);
            return l_response;   
        }
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * (余力調整完了)
     * 投資信託余力調整完了処理を行う。 <BR>
     * <BR>
     * 投信管理者余力調整サービスを取得し、execute()メソッド <BR>
     * をコールする。 <BR>
     * <BR>
     * @@param l_request - リクエストデータ
     * @@return WEB3AdminMutualTPAdjustCompleteResponse
     * @@throws WEB3BaseException
     * @@roseuid 40B2EDB5002C
     */
    public WEB3AdminMutualTPAdjustCompleteResponse tpAdjustComplete(
        WEB3AdminMutualTPAdjustCompleteRequest l_request)
            throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = 
            "tpAdjustComplete(WEB3AdminMutualTPAdjustCompleteRequest l_request)";
        log.entering(STR_METHOD_NAME);        
        
        //投信管理者余力調整サービスを取得し
        WEB3AdminMutualTPAdjustService l_service = null;
        WEB3AdminMutualTPAdjustCompleteResponse l_response = null;
        try
        {
            l_service =
                (WEB3AdminMutualTPAdjustService) 
                    Services.getService(WEB3AdminMutualTPAdjustService.class);
        }
        // サービスで例外が発生した場合は、エラー情報をレスポンスメッセージに設定する
        catch (Exception l_ex)
        {
            l_response =
                (WEB3AdminMutualTPAdjustCompleteResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "投信管理者余力調整サービスの取得に失敗しました。",
                l_response.errorInfo,
                l_ex);
            return l_response;   
        }
        try
        {
            //execute()メソッドをコールする
            l_response = (WEB3AdminMutualTPAdjustCompleteResponse) l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response = (WEB3AdminMutualTPAdjustCompleteResponse) l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request, 
                "投信管理者余力調整処理が失敗しました。",
                l_ex.getErrorInfo(), 
                l_ex);
            return l_response;   
        }
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
}
@
