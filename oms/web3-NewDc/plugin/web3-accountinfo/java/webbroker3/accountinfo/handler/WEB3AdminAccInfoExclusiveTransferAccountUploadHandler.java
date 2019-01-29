head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.10.02.14.28;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8644d782f8a12fc;
filename	WEB3AdminAccInfoExclusiveTransferAccountUploadHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者お客様情報専用振込先口座ｱｯﾌﾟﾛｰﾄﾞﾊﾝﾄﾞﾗ(WEB3AdminAccInfoExclusiveTransferAccountUploadHandler.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/11/10 カク寛新 (中訊) 新規作成
*/

package webbroker3.accountinfo.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.accountinfo.message.WEB3AdminAccInfoExclusiveTransferAccountUploadCancelRequest;
import webbroker3.accountinfo.message.WEB3AdminAccInfoExclusiveTransferAccountUploadCancelResponse;
import webbroker3.accountinfo.message.WEB3AdminAccInfoExclusiveTransferAccountUploadCompleteRequest;
import webbroker3.accountinfo.message.WEB3AdminAccInfoExclusiveTransferAccountUploadCompleteResponse;
import webbroker3.accountinfo.message.WEB3AdminAccInfoExclusiveTransferAccountUploadConfirmRequest;
import webbroker3.accountinfo.message.WEB3AdminAccInfoExclusiveTransferAccountUploadConfirmResponse;
import webbroker3.accountinfo.message.WEB3AdminAccInfoExclusiveTransferAccountUploadInputRequest;
import webbroker3.accountinfo.message.WEB3AdminAccInfoExclusiveTransferAccountUploadInputResponse;
import webbroker3.accountinfo.service.delegate.WEB3AdminAccInfoExclusiveTransferAccountUploadService;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.util.WEB3LogUtility;

/**
 * (管理者お客様情報専用振込先口座ｱｯﾌﾟﾛｰﾄﾞﾊﾝﾄﾞﾗ)<BR>
 * 管理者お客様情報専用振込先口座ｱｯﾌﾟﾛｰﾄﾞﾊﾝﾄﾞﾗ<BR>
 * @@author カク寛新
 * @@version 1.0 
 */
public class WEB3AdminAccInfoExclusiveTransferAccountUploadHandler implements MessageHandler 
{
    /**
     * ログ出力ユーティリティ。<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminAccInfoExclusiveTransferAccountUploadHandler.class);

    
    /**
     * @@roseuid 418F3A130222
     */
    public WEB3AdminAccInfoExclusiveTransferAccountUploadHandler() 
    {
     
    }
    
    /**
     * (アップロード画面表示)<BR>
     * 専用振込先口座アップロード画面表示処理を行う。 <BR>
     * <BR>
     * 管理者お客様情報専用振込先口座ｱｯﾌﾟﾛｰﾄﾞｻｰﾋﾞｽを取得し、<BR>
     * execute()メソッドをコールする。 <BR>
     * @@param l_request - 管理者お客様情報専用振込先口座ｱｯﾌﾟﾛｰﾄﾞ入力ﾘｸｴｽﾄデータオブジェクト
     * @@return webbroker3.accountinfo.message.WEB3AdminAccInfoExclusiveTransferAccountUploadInputResponse
     * @@roseuid 415BC4640005
     */
    public WEB3AdminAccInfoExclusiveTransferAccountUploadInputResponse uploadScreenDisplay(WEB3AdminAccInfoExclusiveTransferAccountUploadInputRequest l_request) 
    {
        final String STR_METHOD_NAME = "uploadScreenDisplay(WEB3AdminAccInfoExclusiveTransferAccountUploadInputRequest)";
        log.entering(STR_METHOD_NAME);
        
        WEB3AdminAccInfoExclusiveTransferAccountUploadInputResponse l_response = null;
        WEB3AdminAccInfoExclusiveTransferAccountUploadService l_service = null;
        
        // 管理者お客様情報専用振込先口座ｱｯﾌﾟﾛｰﾄﾞｻｰﾋﾞｽを取得
        try
        {  
                      
            l_service = (WEB3AdminAccInfoExclusiveTransferAccountUploadService)Services.getService(WEB3AdminAccInfoExclusiveTransferAccountUploadService.class);            
            
        }
        // サービスで例外が発生した場合は、エラー情報をレスポンスメッセージに設定する
        catch(Exception l_ex)
        {
            
            l_response = (WEB3AdminAccInfoExclusiveTransferAccountUploadInputResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "管理者お客様情報専用振込先口座ｱｯﾌﾟﾛｰﾄﾞｻｰﾋﾞｽの取得に失敗しました。",
                l_response.errorInfo,l_ex);
            return l_response; 
            
        }
        
        //管理者お客様情報専用振込先口座ｱｯﾌﾟﾛｰﾄﾞｻｰﾋﾞｽを取得し、execute()メソッドをコールする 
        try
        {
            
            l_response = (WEB3AdminAccInfoExclusiveTransferAccountUploadInputResponse)l_service.execute(l_request);
                    
        }
        catch (WEB3BaseException l_ex)
        {
            
            l_response = (WEB3AdminAccInfoExclusiveTransferAccountUploadInputResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request,
                "管理者お客様情報専用振込先口座ｱｯﾌﾟﾛｰﾄﾞｻｰﾋﾞｽに失敗しました。",
                l_ex);
            return l_response;                    
            
        }
        log.exiting(STR_METHOD_NAME);
        return l_response; 
    }
    
    /**
     * (アップロードファ@イル確認)<BR>
     * 専用振込先口座アップロード確認処理を行う。 <BR>
     * <BR>
     * 管理者お客様情報専用振込先口座ｱｯﾌﾟﾛｰﾄﾞｻｰﾋﾞｽを取得し、<BR>
     * execute()メソッドをコールする。 <BR>
     * @@param l_request - 管理者お客様情報専用振込先口座ｱｯﾌﾟﾛｰﾄﾞ確認ﾘｸｴｽﾄデータオブジェクト
     * @@return webbroker3.accountinfo.message.WEB3AdminAccInfoExclusiveTransferAccountUploadConfirmResponse
     * @@roseuid 415BC4640024
     */
    public WEB3AdminAccInfoExclusiveTransferAccountUploadConfirmResponse uploadFileConfirm(WEB3AdminAccInfoExclusiveTransferAccountUploadConfirmRequest l_request) 
    {
        final String STR_METHOD_NAME = "uploadFileConfirm(WEB3AdminAccInfoExclusiveTransferAccountUploadConfirmRequest)";
        log.entering(STR_METHOD_NAME);
        
        WEB3AdminAccInfoExclusiveTransferAccountUploadConfirmResponse l_response = null;
        WEB3AdminAccInfoExclusiveTransferAccountUploadService l_service = null;
        
        // 管理者お客様情報専用振込先口座ｱｯﾌﾟﾛｰﾄﾞｻｰﾋﾞｽを取得
        try
        {  
                      
            l_service = (WEB3AdminAccInfoExclusiveTransferAccountUploadService)Services.getService(WEB3AdminAccInfoExclusiveTransferAccountUploadService.class);            
            
        }
        // サービスで例外が発生した場合は、エラー情報をレスポンスメッセージに設定する
        catch(Exception l_ex)
        {
            
            l_response = (WEB3AdminAccInfoExclusiveTransferAccountUploadConfirmResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "管理者お客様情報専用振込先口座ｱｯﾌﾟﾛｰﾄﾞｻｰﾋﾞｽの取得に失敗しました。",
                l_response.errorInfo,l_ex);
            return l_response; 
            
        }
        
        //管理者お客様情報専用振込先口座ｱｯﾌﾟﾛｰﾄﾞｻｰﾋﾞｽを取得し、execute()メソッドをコールする 
        try
        {
            
            l_response = (WEB3AdminAccInfoExclusiveTransferAccountUploadConfirmResponse)l_service.execute(l_request);
                    
        }
        catch (WEB3BaseException l_ex)
        {
            
            l_response = (WEB3AdminAccInfoExclusiveTransferAccountUploadConfirmResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request,
                "管理者お客様情報専用振込先口座ｱｯﾌﾟﾛｰﾄﾞｻｰﾋﾞｽに失敗しました。",
                l_ex);
            return l_response;                    
            
        }
        log.exiting(STR_METHOD_NAME);
        return l_response; 
    }
    
    /**
     * (専用振込先口座アップロード)<BR>
     * 専用振込先口座アップロード完了処理を行う。 <BR>
     * <BR>
     * 管理者お客様情報専用振込先口座ｱｯﾌﾟﾛｰﾄﾞｻｰﾋﾞｽを取得し、<BR>
     * execute()メソッドをコールする。 <BR>
     * @@param l_request - 管理者お客様情報専用振込先口座ｱｯﾌﾟﾛｰﾄﾞ完了ﾘｸｴｽﾄデータオブジェクト
     * @@return webbroker3.accountinfo.message.WEB3AdminAccInfoExclusiveTransferAccountUploadCompleteResponse
     * @@roseuid 415BC4640034
     */
    public WEB3AdminAccInfoExclusiveTransferAccountUploadCompleteResponse exclusiveTransferAccountUpload(WEB3AdminAccInfoExclusiveTransferAccountUploadCompleteRequest l_request) 
    {
        final String STR_METHOD_NAME = "exclusiveTransferAccountUpload(WEB3AdminAccInfoExclusiveTransferAccountUploadCompleteRequest)";
        log.entering(STR_METHOD_NAME);
        
        WEB3AdminAccInfoExclusiveTransferAccountUploadCompleteResponse l_response = null;
        WEB3AdminAccInfoExclusiveTransferAccountUploadService l_service = null;
        
        // 管理者お客様情報専用振込先口座ｱｯﾌﾟﾛｰﾄﾞｻｰﾋﾞｽを取得
        try
        {  
                      
            l_service = (WEB3AdminAccInfoExclusiveTransferAccountUploadService)Services.getService(WEB3AdminAccInfoExclusiveTransferAccountUploadService.class);            
            
        }
        // サービスで例外が発生した場合は、エラー情報をレスポンスメッセージに設定する
        catch(Exception l_ex)
        {
            
            l_response = (WEB3AdminAccInfoExclusiveTransferAccountUploadCompleteResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "管理者お客様情報専用振込先口座ｱｯﾌﾟﾛｰﾄﾞｻｰﾋﾞｽの取得に失敗しました。",
                l_response.errorInfo,l_ex);
            return l_response; 
            
        }
        
        //管理者お客様情報専用振込先口座ｱｯﾌﾟﾛｰﾄﾞｻｰﾋﾞｽを取得し、execute()メソッドをコールする 
        try
        {
            
            l_response = (WEB3AdminAccInfoExclusiveTransferAccountUploadCompleteResponse)l_service.execute(l_request);
                    
        }
        catch (WEB3BaseException l_ex)
        {
            
            l_response = (WEB3AdminAccInfoExclusiveTransferAccountUploadCompleteResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request,
                "管理者お客様情報専用振込先口座ｱｯﾌﾟﾛｰﾄﾞｻｰﾋﾞｽに失敗しました。",
                l_ex);
            return l_response;                    
            
        }
        log.exiting(STR_METHOD_NAME);
        return l_response; 
    }
    
    /**
     * (アップロード中止)<BR>
     * 専用振込先口座アップロード中止処理を行う。 <BR>
     * <BR>
     * 管理者お客様情報専用振込先口座ｱｯﾌﾟﾛｰﾄﾞｻｰﾋﾞｽを取得し、<BR>
     * execute()メソッドをコールする。 <BR>
     * @@param l_request - 管理者お客様情報専用振込先口座ｱｯﾌﾟﾛｰﾄﾞ中止ﾘｸｴｽﾄデータオブジェクト
     * @@return webbroker3.accountinfo.message.WEB3AdminAccInfoExclusiveTransferAccountUploadCancelResponse
     * @@roseuid 415BC4640072
     */
    public WEB3AdminAccInfoExclusiveTransferAccountUploadCancelResponse uploadCancel(WEB3AdminAccInfoExclusiveTransferAccountUploadCancelRequest l_request) 
    {
        final String STR_METHOD_NAME = "uploadCancel(WEB3AdminAccInfoExclusiveTransferAccountUploadCancelRequest)";
        log.entering(STR_METHOD_NAME);
        
        WEB3AdminAccInfoExclusiveTransferAccountUploadCancelResponse l_response = null;
        WEB3AdminAccInfoExclusiveTransferAccountUploadService l_service = null;
        
        // 管理者お客様情報専用振込先口座ｱｯﾌﾟﾛｰﾄﾞｻｰﾋﾞｽを取得
        try
        {  
                      
            l_service = (WEB3AdminAccInfoExclusiveTransferAccountUploadService)Services.getService(WEB3AdminAccInfoExclusiveTransferAccountUploadService.class);            
            
        }
        // サービスで例外が発生した場合は、エラー情報をレスポンスメッセージに設定する
        catch(Exception l_ex)
        {
            
            l_response = (WEB3AdminAccInfoExclusiveTransferAccountUploadCancelResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "管理者お客様情報専用振込先口座ｱｯﾌﾟﾛｰﾄﾞｻｰﾋﾞｽの取得に失敗しました。",
                l_response.errorInfo,l_ex);
            return l_response; 
            
        }
        
        //管理者お客様情報専用振込先口座ｱｯﾌﾟﾛｰﾄﾞｻｰﾋﾞｽを取得し、execute()メソッドをコールする 
        try
        {
            
            l_response = (WEB3AdminAccInfoExclusiveTransferAccountUploadCancelResponse)l_service.execute(l_request);
                    
        }
        catch (WEB3BaseException l_ex)
        {
            
            l_response = (WEB3AdminAccInfoExclusiveTransferAccountUploadCancelResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request,
                "管理者お客様情報専用振込先口座ｱｯﾌﾟﾛｰﾄﾞｻｰﾋﾞｽに失敗しました。",
                l_ex);
            return l_response;                    
            
        }
        log.exiting(STR_METHOD_NAME);
        return l_response; 
    }
}
@
