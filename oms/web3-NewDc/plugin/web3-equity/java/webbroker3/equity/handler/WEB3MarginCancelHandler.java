head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.10;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3MarginCancelHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 信用取引取消ハンドラ(WEB3MarginCancelServiceHandler.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/08 凌建平 (中訊) 新規作成
                   2005/01/06 岡村和明(SRA) JavaDoc修正
*/

package webbroker3.equity.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.equity.message.WEB3MarginCancelCompleteRequest;
import webbroker3.equity.message.WEB3MarginCancelCompleteResponse;
import webbroker3.equity.message.WEB3MarginCancelConfirmRequest;
import webbroker3.equity.message.WEB3MarginCancelConfirmResponse;
import webbroker3.equity.service.delegate.WEB3MarginCancelService;
import webbroker3.util.WEB3LogUtility;

/**
 * （信用取引取消ハンドラ）。<BR>
 * <BR>
 * 信用取引取消ハンドラクラス
 * @@author 凌建平
 * @@version 1.0
 */
public class WEB3MarginCancelHandler implements MessageHandler 
{
    /**
     * (ログ出力ユーティリティ。)<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3MarginCancelHandler.class);
        
    /**
     * @@roseuid 414184C40249
     */
    public WEB3MarginCancelHandler() 
    {
     
    }
    
    /**
     * (confirm取消)<BR>
     * 信用取引取消審査を行う。<BR>
     * <BR>
     * 信用取引取消サービスを取得し、execute()メソッドをコールする。<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * 信用取引取消リクエストオブジェクト<BR>
     * @@return WEB3MarginCancelConfirmResponse<BR>
     * @@roseuid 405808D001E6
     */
    public WEB3MarginCancelConfirmResponse confirmCancel(WEB3MarginCancelConfirmRequest l_request) 
    {
        final String STR_METHOD_NAME =
                "confirmSwapMargin(WEB3MarginCancelConfirmRequest)";
    
        log.entering(STR_METHOD_NAME);

        WEB3MarginCancelConfirmResponse l_response = null;
        WEB3MarginCancelService l_service      = null;
        
        //信用取引取消サービスを取得
        try
        {
            l_service =
                (WEB3MarginCancelService)Services.getService(
                    WEB3MarginCancelService.class);
        }
        //サービスで例外が発生した場合は、エラー情報をレスポンスメッセージに設定する
        catch (Exception l_ex)
        {
            l_response =
                (WEB3MarginCancelConfirmResponse) l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "信用取引取消サービスの取得に失敗しました。",
                l_response.errorInfo,
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        
        //信用取引取消サービスオブジェクト.execute（）をコールし、
        //処理結果であるレスポンスオブジェクトを取得する。
        try
        {
            l_response =
                (WEB3MarginCancelConfirmResponse) l_service.execute(
                    l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response =
                (WEB3MarginCancelConfirmResponse) l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request, "信用取引取消審査に失敗しました。", l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        catch (WEB3BaseRuntimeException l_bre)
        {
            l_response =
                (WEB3MarginCancelConfirmResponse) l_request.createResponse();
            l_response.errorInfo = l_bre.getErrorInfo();
            log.error(l_request, "信用取引取消審査に失敗しました。", l_bre);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

        log.exiting(STR_METHOD_NAME);

        //レスポンスオブジェクトを返却する。
        return l_response;
    }
    
    /**
     * (complete取消)<BR>
     * 信用取引注文取消を行う。<BR>
     * <BR>
     * 信用取引取消サービスを取得し、execute()メソッドをコールする。<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * 信用取引取消リクエストオブジェクト<BR>
     * @@return WEB3MarginCancelCompleteResponse<BR>
     * @@roseuid 405808D60198
     */
    public WEB3MarginCancelCompleteResponse completeCancel(WEB3MarginCancelCompleteRequest l_request) 
    {
        final String STR_METHOD_NAME =
                "confirmSwapMargin(WEB3MarginCancelCompleteRequest)";
    
        log.entering(STR_METHOD_NAME);

        WEB3MarginCancelCompleteResponse l_response = null;
        WEB3MarginCancelService l_service      = null;
        
        //信用取引取消サービスを取得
        try
        {
            l_service =
                (WEB3MarginCancelService)Services.getService(
                    WEB3MarginCancelService.class);
        }
        //サービスで例外が発生した場合は、エラー情報をレスポンスメッセージに設定する
        catch (Exception l_ex)
        {
            l_response =
                (WEB3MarginCancelCompleteResponse) l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "信用取引取消サービスサービスの取得に失敗しました。",
                l_response.errorInfo,
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        
        //信用取引取消サービスオブジェクト.execute（）をコールし、
        //処理結果であるレスポンスオブジェクトを取得する。
        try
        {
            l_response =
                (WEB3MarginCancelCompleteResponse) l_service.execute(
                    l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response =
                (WEB3MarginCancelCompleteResponse) l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request, "信用取引注文取消に失敗しました。", l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        catch (WEB3BaseRuntimeException l_bre)
        {
            l_response =
                (WEB3MarginCancelCompleteResponse) l_request.createResponse();
            l_response.errorInfo = l_bre.getErrorInfo();
            log.error(l_request, "信用取引注文取消に失敗しました。", l_bre);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

        log.exiting(STR_METHOD_NAME);

        //レスポンスオブジェクトを返却する。
        return l_response;
    }
}
@
