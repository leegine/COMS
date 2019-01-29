head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.11;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3MarginSwapMarginHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 信用取引現引現渡ハンドラ(WEB3MarginSwapMarginHandler.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/09/20 凌建平 (中訊) 新規作成
                   2005/01/06 岡村和明(SRA) JavaDoc修正
*/

package webbroker3.equity.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.equity.service.delegate.WEB3MarginSwapMarginService;
import webbroker3.equity.message.WEB3MarginSwapMarginCompleteRequest;
import webbroker3.equity.message.WEB3MarginSwapMarginCompleteResponse;
import webbroker3.equity.message.WEB3MarginSwapMarginConfirmRequest;
import webbroker3.equity.message.WEB3MarginSwapMarginConfirmResponse;
import webbroker3.util.WEB3LogUtility;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;

/**
 * （信用取引現引現渡ハンドラ）。<BR>
 * <BR>
 * 信用取引現引現渡ハンドラクラス
 * @@author 凌建平
 * @@version 1.0
 */
public class WEB3MarginSwapMarginHandler implements MessageHandler 
{
    /**
     * (ログ出力ユーティリティ。)<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3MarginSwapMarginHandler.class);
        
    /**
     * @@roseuid 414184C40041
     */
    public WEB3MarginSwapMarginHandler() 
    {
     
    }
    
    /**
     * (confirm現引現渡)<BR>
     * 信用取引現引現渡発注審査を行う。<BR>
     * <BR>
     * 信用取引現引現渡サービスを取得し、<BR>
     * execute()メソッドをコールする。<BR>
     * @@param l_request - リクエストデータ
     * @@return WEB3MarginSwapMarginConfirmResponse
     * @@roseuid 405693970057
     */
    public WEB3MarginSwapMarginConfirmResponse confirmSwapMargin(WEB3MarginSwapMarginConfirmRequest l_request) 
    {
        final String METHOD_NAME = "confirmSwapMargin(WEB3MarginSwapMarginConfirmRequest)";
    
        log.entering(METHOD_NAME);

        WEB3MarginSwapMarginConfirmResponse l_response = null;
        WEB3MarginSwapMarginService l_service      = null;
        
        //信用取引現引現渡サービスを取得
        try
        {
            l_service =
                (WEB3MarginSwapMarginService)Services.getService(
                    WEB3MarginSwapMarginService.class);
        }
        //サービスで例外が発生した場合は、エラー情報をレスポンスメッセージに設定する
        catch (Exception l_ex)
        {
            l_response =
                (WEB3MarginSwapMarginConfirmResponse) l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "信用取引現引現渡サービスを取得に失敗しました。",
                l_response.errorInfo,
                l_ex);
            log.exiting(METHOD_NAME);
            return l_response;
        }
        
        //信用取引現引現渡発注審査サービスオブジェクト.execute（）をコールし、
        //処理結果であるレスポンスオブジェクトを取得する。
        try
        {
            l_response =
                (WEB3MarginSwapMarginConfirmResponse) l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response =
                (WEB3MarginSwapMarginConfirmResponse) l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            l_response.errorMessage = l_ex.getErrorMessage();
            log.error(l_request, "信用取引現引現渡発注審査に失敗しました。", l_ex);
            log.exiting(METHOD_NAME);
            return l_response;
        }
        catch (WEB3BaseRuntimeException l_bre)
        {
            l_response =
                (WEB3MarginSwapMarginConfirmResponse) l_request.createResponse();
            l_response.errorInfo = l_bre.getErrorInfo();
            log.error(l_request, "信用取引現引現渡発注審査に失敗しました。", l_bre);
            log.exiting(METHOD_NAME);
            return l_response;
        }

        log.exiting(METHOD_NAME);

        //レスポンスオブジェクトを返却する。
        return l_response;
    }
    
    /**
     * (complete現引現渡)<BR>
     * 信用取引現引現渡注文登録を行う。<BR>
     * <BR>
     * 信用取引現引現渡サービスを取得し、<BR>
     * execute()メソッドをコールする。<BR>
     * @@param l_request - リクエストデータ
     * @@return WEB3MarginSwapMarginCompleteResponse
     * @@roseuid 4056939E0161
     */
    public WEB3MarginSwapMarginCompleteResponse completeSwapMargin(WEB3MarginSwapMarginCompleteRequest l_request) 
    {
        final String METHOD_NAME = "completeSwapMargin(WEB3MarginSwapMarginCompleteRequest)";
        
        log.entering(METHOD_NAME);

        WEB3MarginSwapMarginCompleteResponse l_response = null;
        WEB3MarginSwapMarginService l_service      = null;
        
        //信用取引現引現渡サービスを取得
        try
        {
            l_service =
                (WEB3MarginSwapMarginService)Services.getService(
                    WEB3MarginSwapMarginService.class);
        }
        //サービスで例外が発生した場合は、エラー情報をレスポンスメッセージに設定する
        catch (Exception l_ex)
        {
            l_response =
                (WEB3MarginSwapMarginCompleteResponse) l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "信用取引現引現渡サービスを取得に失敗しました。",
                l_response.errorInfo,
                l_ex);
            log.exiting(METHOD_NAME);
            return l_response;
        }
        
        //信用取引現引現渡サービスオブジェクト.execute（）をコールし、
        //処理結果であるレスポンスオブジェクトを取得する。
        try
        {
            l_response =
                (WEB3MarginSwapMarginCompleteResponse) l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response =
                (WEB3MarginSwapMarginCompleteResponse) l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            l_response.errorMessage = l_ex.getErrorMessage();
            log.error(l_request, "信用取引現引現渡注文に失敗しました。", l_ex);
            log.exiting(METHOD_NAME);
            return l_response;
        }
        catch (WEB3BaseRuntimeException l_bre)
        {
            l_response =
                (WEB3MarginSwapMarginCompleteResponse) l_request.createResponse();
            l_response.errorInfo = l_bre.getErrorInfo();
            log.error(l_request, "信用取引現引現渡注文に失敗しました。", l_bre);
            log.exiting(METHOD_NAME);
            return l_response;
        }

        log.exiting(METHOD_NAME);

        //レスポンスオブジェクトを返却する。
        return l_response;
    }
}
@
