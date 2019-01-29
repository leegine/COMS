head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.23.39;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3AdminAioVirtualAccCashinULHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : バーチャル口座入金ULハンドラ(WEB3AdminAioVirtualAccCashinULHandler)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/05/09 肖志偉 (中訊) 新規作成     
*/

package webbroker3.aio.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.aio.message.WEB3AdminAioVirtualAccCashinULCancelRequest;
import webbroker3.aio.message.WEB3AdminAioVirtualAccCashinULCancelResponse;
import webbroker3.aio.message.WEB3AdminAioVirtualAccCashinULCompleteRequest;
import webbroker3.aio.message.WEB3AdminAioVirtualAccCashinULCompleteResponse;
import webbroker3.aio.message.WEB3AdminAioVirtualAccCashinULConfirmRequest;
import webbroker3.aio.message.WEB3AdminAioVirtualAccCashinULConfirmResponse;
import webbroker3.aio.message.WEB3AdminAioVirtualAccCashinULInputRequest;
import webbroker3.aio.message.WEB3AdminAioVirtualAccCashinULInputResponse;
import webbroker3.aio.service.delegate.WEB3AdminAioVirtualAccCashinULService;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.util.WEB3LogUtility;

/**
 * (バーチャル口座入金ULハンドラ)<BR>
 * バーチャル口座入金ULハンドラ
 * 
 * @@author 肖志偉(中訊)
 * @@version 1.0
 */
public class WEB3AdminAioVirtualAccCashinULHandler implements MessageHandler
{
    /**
     * ログユーティリティ
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminAioSettleReportInputHandler.class);
    
    /**
     * @@roseuid 44603B3301F4
     */
    public WEB3AdminAioVirtualAccCashinULHandler() 
    {
     
    }
    
    /**
     * (アップロード画面表示)<BR>
     * バーチャル口座入金アップロード画面表示処理を行う。 <BR>
     * <BR>
     * バーチャル口座入金ｱｯﾌﾟﾛｰﾄﾞｻｰﾋﾞｽを取得し、<BR> 
     * execute()メソッドをコールする<BR>
     * @@param l_request - (バーチャル口座入金ULリクエスト)<BR>
     * バーチャル口座入金UL入力リクエスト
     * @@return WEB3AdminAioVirtualAccCashinULInputResponse
     * @@roseuid 4455BC8E0008
     */
    public WEB3AdminAioVirtualAccCashinULInputResponse uploadScreenDisplay(
        WEB3AdminAioVirtualAccCashinULInputRequest l_request) 
    {
        final String STR_METHOD_NAME = 
            "uploadScreenDisplay(WEB3AdminAioVirtualAccCashinULInputRequest l_request)";
        log.entering(STR_METHOD_NAME); 
        
        //バーチャル口座入金ｱｯﾌﾟﾛｰﾄﾞｻｰﾋﾞｽを取得し
        WEB3AdminAioVirtualAccCashinULService l_service = null;
        WEB3AdminAioVirtualAccCashinULInputResponse l_response = null;
        try
        {
            l_service = 
                (WEB3AdminAioVirtualAccCashinULService)
                    Services.getService(WEB3AdminAioVirtualAccCashinULService.class);
        }
        catch(Exception l_ex)
        {
            l_response =
                (WEB3AdminAioVirtualAccCashinULInputResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;           
            log.error(
                l_request,
                "バーチャル口座入金ｱｯﾌﾟﾛｰﾄﾞｻｰﾋﾞｽを取得に失敗しました。",
                l_response.errorInfo,
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        
        try 
        {
            //execute()メソッドをコールする
            l_response = (WEB3AdminAioVirtualAccCashinULInputResponse)l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response =
                (WEB3AdminAioVirtualAccCashinULInputResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request, 
                "バーチャル口座入金アップロード画面表示処理が失敗しました。", 
                l_ex.getErrorInfo(), 
                l_ex); 
        }       
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * (アップロードファ@イル確認)<BR>
     * バーチャル口座入金アップロード確認処理を行う。<BR>
     * <BR>
     * バーチャル口座入金アップロードｱｯﾌﾟﾛｰﾄﾞｻｰﾋﾞｽを取得し、<BR> 
     * execute()メソッドをコールする。<BR>
     * @@param l_request - (バーチャル口座入金UL確認リクエスト)<BR>
     * バーチャル口座入金UL確認リクエスト
     * @@return WEB3AdminAioVirtualAccCashinULConfirmResponse
     * @@roseuid 4455BD4E03E0
     */
    public WEB3AdminAioVirtualAccCashinULConfirmResponse uploadFileConfirm(
        WEB3AdminAioVirtualAccCashinULConfirmRequest l_request) 
    {
        final String STR_METHOD_NAME = 
            "uploadFileConfirm(WEB3AdminAioVirtualAccCashinULConfirmRequest l_request)";
        log.entering(STR_METHOD_NAME); 
        
        //バーチャル口座入金ｱｯﾌﾟﾛｰﾄﾞｻｰﾋﾞｽを取得し
        WEB3AdminAioVirtualAccCashinULService l_service = null;
        WEB3AdminAioVirtualAccCashinULConfirmResponse l_response = null;
        try
        {
            l_service = 
                (WEB3AdminAioVirtualAccCashinULService)
                    Services.getService(WEB3AdminAioVirtualAccCashinULService.class);
        }
        catch(Exception l_ex)
        {
            l_response =
                (WEB3AdminAioVirtualAccCashinULConfirmResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;           
            log.error(
                l_request,
                "バーチャル口座入金ｱｯﾌﾟﾛｰﾄﾞｻｰﾋﾞｽを取得に失敗しました。",
                l_response.errorInfo,
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        
        try 
        {
            //execute()メソッドをコールする
            l_response = (WEB3AdminAioVirtualAccCashinULConfirmResponse)l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response =
                (WEB3AdminAioVirtualAccCashinULConfirmResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            l_response.errorMessage = l_ex.getErrorMessage();
            log.error(l_request, 
                "バーチャル口座入金アップロード確認処理が失敗しました。", 
                l_ex.getErrorInfo(), 
                l_ex); 
        }       
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * (バーチャル口座入金アップロード)<BR>
     * バーチャル口座入金アップロード完了処理を行う。<BR> 
     * <BR>
     * バーチャル口座入金ｱｯﾌﾟﾛｰﾄﾞｻｰﾋﾞｽを取得し、<BR> 
     * execute()メソッドをコールする<BR>
     * @@param l_request - (バーチャル口座入金完了リクエスト)<BR>
     * バーチャル口座入金UL完了リクエスト
     * @@return WEB3AdminAioVirtualAccCashinULCompleteResponse
     * @@roseuid 4455BEE80218
     */
    public WEB3AdminAioVirtualAccCashinULCompleteResponse virtualAccCashinUpload(
        WEB3AdminAioVirtualAccCashinULCompleteRequest l_request) 
    {
        final String STR_METHOD_NAME = 
            "virtualAccCashinUpload(WEB3AdminAioVirtualAccCashinULCompleteRequest l_request)";
        log.entering(STR_METHOD_NAME); 
        
        //バーチャル口座入金ｱｯﾌﾟﾛｰﾄﾞｻｰﾋﾞｽを取得し
        WEB3AdminAioVirtualAccCashinULService l_service = null;
        WEB3AdminAioVirtualAccCashinULCompleteResponse l_response = null;
        try
        {
            l_service = 
                (WEB3AdminAioVirtualAccCashinULService)
                    Services.getService(WEB3AdminAioVirtualAccCashinULService.class);
        }
        catch(Exception l_ex)
        {
            l_response =
                (WEB3AdminAioVirtualAccCashinULCompleteResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;           
            log.error(
                l_request,
                "バーチャル口座入金ｱｯﾌﾟﾛｰﾄﾞｻｰﾋﾞｽを取得に失敗しました。",
                l_response.errorInfo,
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        
        try 
        {
            //execute()メソッドをコールする
            l_response = (WEB3AdminAioVirtualAccCashinULCompleteResponse)l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response =
                (WEB3AdminAioVirtualAccCashinULCompleteResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo(); 
            log.error(l_request, 
                "バーチャル口座入金アップロード完了処理が失敗しました。", 
                l_ex.getErrorInfo(), 
                l_ex); 
        }       
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * (アップロー中止)<BR>
     * バーチャル口座入金アップロード中止処理を行う。 <BR>
     * <BR>
     * バーチャル口座入金ｱｯﾌﾟﾛｰﾄﾞｻｰﾋﾞｽを取得し、 <BR>
     * execute()メソッドをコールする。<BR>
     * @@param l_request - (バーチャル口座入金UL中止リクエスト)<BR>
     * バーチャル口座入金UL中止リクエスト
     * @@return WEB3AdminAioVirtualAccCashinULCancelResponse
     * @@roseuid 4455BF9A0313
     */
    public WEB3AdminAioVirtualAccCashinULCancelResponse uploadCancel(
        WEB3AdminAioVirtualAccCashinULCancelRequest l_request) 
    {
        final String STR_METHOD_NAME = 
            "uploadCancel(WEB3AdminAioVirtualAccCashinULCancelRequest l_request)";
        log.entering(STR_METHOD_NAME); 
        
        //バーチャル口座入金ｱｯﾌﾟﾛｰﾄﾞｻｰﾋﾞｽを取得し
        WEB3AdminAioVirtualAccCashinULService l_service = null;
        WEB3AdminAioVirtualAccCashinULCancelResponse l_response = null;
        try
        {
            l_service = 
                (WEB3AdminAioVirtualAccCashinULService)
                    Services.getService(WEB3AdminAioVirtualAccCashinULService.class);
        }
        catch(Exception l_ex)
        {
            l_response =
                (WEB3AdminAioVirtualAccCashinULCancelResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;           
            log.error(
                l_request,
                "バーチャル口座入金ｱｯﾌﾟﾛｰﾄﾞｻｰﾋﾞｽを取得に失敗しました。",
                l_response.errorInfo,
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        
        try 
        {
            //execute()メソッドをコールする
            l_response = (WEB3AdminAioVirtualAccCashinULCancelResponse)l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response =
                (WEB3AdminAioVirtualAccCashinULCancelResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request, 
                "バーチャル口座入金アップロード中止処理が失敗しました。", 
                l_ex.getErrorInfo(), 
                l_ex); 
        }       
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
}
@
