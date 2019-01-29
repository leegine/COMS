head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.05.49.40;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6f44d7efa104045;
filename	WEB3AdminIpoOfferStopResumeHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者IPO募集停止再開ハンドラクラス(WEB3AdminIpoOfferStopResumeHandler.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/08/24 李海波 (中訊) 新規作成
*/

package webbroker3.ipo.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.ipo.message.WEB3AdminIPOOfferStopResumeCompleteRequest;
import webbroker3.ipo.message.WEB3AdminIPOOfferStopResumeCompleteResponse;
import webbroker3.ipo.message.WEB3AdminIPOOfferStopResumeConfirmRequest;
import webbroker3.ipo.message.WEB3AdminIPOOfferStopResumeConfirmResponse;
import webbroker3.ipo.service.delegate.WEB3AdminIpoOfferStopResumeService;
import webbroker3.util.WEB3LogUtility;

/**
 * 管理者IPO募集停止再開ハンドラクラス
 * 
 * @@author 李海波
 * @@version 1.0
 */
public class WEB3AdminIpoOfferStopResumeHandler implements MessageHandler 
{
    
    /**
     * ログ出力ユーティリティ。
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminIpoOfferStopResumeHandler.class);
    
    /**
     * @@roseuid 4112EE57004A
     */
    public WEB3AdminIpoOfferStopResumeHandler() 
    {
     
    }
    
    /**
     * (募集停止再開確認)<BR>
     * 管理者IPO募集停止再開確認処理<BR>
     * <BR>
     * 管理者IPO募集停止再開サービスを取得し、execute()メソッドをコールする。<BR>
     * @@param l_request - 管理者IPO募集停止再開確認リクエストデータオブジェクト
     * 
     * @@return webbroker3.ipo.message.WEB3AdminIpoOfferStopResumeConfirmResponse
     * @@roseuid 40D015D0003C
     */
    public WEB3AdminIPOOfferStopResumeConfirmResponse offerStopResumeConfirm(WEB3AdminIPOOfferStopResumeConfirmRequest l_request) 
    {

        final String STR_METHOD_NAME = " offerStopResumeConfirm(WEB3AdminIpoOfferStopResumeConfirmRequest)";   
        log.entering(STR_METHOD_NAME);
                
        WEB3AdminIPOOfferStopResumeConfirmResponse l_response = null;
        WEB3AdminIpoOfferStopResumeService l_service = null;
        
        try
        {
            l_service = (WEB3AdminIpoOfferStopResumeService)Services.getService(WEB3AdminIpoOfferStopResumeService.class);
        }
        //サービスで例外が発生した場合は、エラー情報をレスポンスメッセージに設定する
        catch (Exception l_ex)
        {
            l_response = (WEB3AdminIPOOfferStopResumeConfirmResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "管理者IPO募集停止再開サービスの取得に失敗しました。",
                l_response.errorInfo,l_ex);
            return l_response;      
        }
            
        //管理者IPO募集停止再開サービス.execute()メソッドをコールする
        try
        {
            l_response = (WEB3AdminIPOOfferStopResumeConfirmResponse)l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
                
            l_response = (WEB3AdminIPOOfferStopResumeConfirmResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request, "管理者IPO募集停止再開に失敗しました。", l_ex);
            return l_response;
                
        }
        log.exiting(STR_METHOD_NAME);
            
        //レスポンスオブジェクトを返却する。
        return l_response;

    }
    
    /**
     * (募集停止再開完了)<BR>
     * 管理者IPO募集停止再開完了処理<BR>
     * <BR>
     * 管理者IPO募集停止再開サービスを取得し、execute()メソッドをコールする。<BR>
     * @@param l_request - 管理者IPO募集停止再開完了リクエストデータオブジェクト
     * @@return webbroker3.ipo.message.WEB3AdminIpoOfferStopResumeCompleteResponse
     * @@roseuid 40D01614023F
     */
    public WEB3AdminIPOOfferStopResumeCompleteResponse offerStopResumeComplete(WEB3AdminIPOOfferStopResumeCompleteRequest l_request) 
    {

        final String STR_METHOD_NAME = " offerStopResumeComplete(WEB3AdminIpoOfferStopResumeCompleteRequest)";   
        log.entering(STR_METHOD_NAME);
                
        WEB3AdminIPOOfferStopResumeCompleteResponse l_response = null;
        WEB3AdminIpoOfferStopResumeService l_service = null;
        
        try
        {
            l_service = (WEB3AdminIpoOfferStopResumeService)Services.getService(WEB3AdminIpoOfferStopResumeService.class);
        }
        //サービスで例外が発生した場合は、エラー情報をレスポンスメッセージに設定する
        catch (Exception l_ex)
        {
            l_response = (WEB3AdminIPOOfferStopResumeCompleteResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "管理者IPO募集停止再開サービスの取得に失敗しました。",
                l_response.errorInfo,l_ex);
            return l_response;      
        }
            
        //管理者IPO募集停止再開サービス.execute()メソッドをコールする
        try
        {
            l_response = (WEB3AdminIPOOfferStopResumeCompleteResponse)l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
                
            l_response = (WEB3AdminIPOOfferStopResumeCompleteResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request, "管理者IPO募集停止再開に失敗しました。", l_ex);
            return l_response;
                
        }
        log.exiting(STR_METHOD_NAME);
            
        //レスポンスオブジェクトを返却する。
        return l_response;

    }
}
@
