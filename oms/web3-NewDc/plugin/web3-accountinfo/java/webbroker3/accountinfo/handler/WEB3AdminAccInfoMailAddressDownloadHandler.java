head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.10.02.14.20;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8644d782f8a12fc;
filename	WEB3AdminAccInfoMailAddressDownloadHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者お客様情報ﾒｰﾙｱﾄﾞﾚｽ全件ﾀﾞｳﾝﾛｰﾄﾞﾊﾝﾄﾞﾗ(WEB3AdminAccInfoMailAddressDownloadHandler.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/11/09 劉江涛 (中訊) 新規作成
*/
package webbroker3.accountinfo.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.accountinfo.message.WEB3AdminAccInfoMailAddressDownloadRequest;
import webbroker3.accountinfo.message.WEB3AdminAccInfoMailAddressDownloadResponse;
import webbroker3.accountinfo.message.WEB3AdminAccInfoMailAddressFileDownloadRequest;
import webbroker3.accountinfo.message.WEB3AdminAccInfoMailAddressFileDownloadResponse;
import webbroker3.accountinfo.message.WEB3AdminAccInfoMailAddressInquiryRequest;
import webbroker3.accountinfo.message.WEB3AdminAccInfoMailAddressInquiryResponse;
import webbroker3.accountinfo.service.delegate.WEB3AdminAccInfoMailAddressDownloadService;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.util.WEB3LogUtility;

/**
 * (管理者お客様情報ﾒｰﾙｱﾄﾞﾚｽ全件ﾀﾞｳﾝﾛｰﾄﾞﾊﾝﾄﾞﾗ)<BR>
 * 管理者お客様情報ﾒｰﾙｱﾄﾞﾚｽ全件ﾀﾞｳﾝﾛｰﾄﾞﾊﾝﾄﾞﾗ<BR>
 * @@author 劉江涛
 * @@version 1.0
 */
public class WEB3AdminAccInfoMailAddressDownloadHandler implements MessageHandler 
{
    /**
     * ログ出力ユーティリティ。<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminAccInfoMailAddressDownloadHandler.class);
    /**
     * @@roseuid 418F3A0E038A
     */
    public WEB3AdminAccInfoMailAddressDownloadHandler() 
    {
     
    }
    /**
     * (入力画面表示)<BR>
     * メールアドレス全件ダウンロード入力画面表示処理を行う。  <BR>
     * <BR>
     * 管理者お客様情報ﾒｰﾙｱﾄﾞﾚｽ全件ﾀﾞｳﾝﾛｰﾄﾞｻｰﾋﾞｽを取得し、<BR>
     * execute()メソッドをコールする。 <BR>
     * @@param l_request - 管理者お客様情報ﾒｰﾙｱﾄﾞﾚｽ全件問合せﾘｸｴｽﾄデータオブジェクト
     * 
     * @@return webbroker3.accountinfo.message.WEB3AdminAccInfoMailAddressInquiryResponse
     * @@throws WEB3BaseException
     * @@roseuid 4147E614023D
     */
    public WEB3AdminAccInfoMailAddressInquiryResponse inputScreenDisplay(WEB3AdminAccInfoMailAddressInquiryRequest l_request) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " inputScreenDisplay(WEB3AdminAccInfoMailAddressInquiryRequest)";
        log.entering(STR_METHOD_NAME);
        
        WEB3AdminAccInfoMailAddressInquiryResponse l_response = null;
        WEB3AdminAccInfoMailAddressDownloadService l_service = null;
        
        //管理者お客様情報ﾒｰﾙｱﾄﾞﾚｽ全件ﾀﾞｳﾝﾛｰﾄﾞサービスを取得
        try
        {
            l_service = (WEB3AdminAccInfoMailAddressDownloadService)Services.getService(WEB3AdminAccInfoMailAddressDownloadService.class);
        }
        //サービスで例外が発生した場合は、エラー情報をレスポンスメッセージに設定する
        catch (Exception l_ex)
        {
            l_response = (WEB3AdminAccInfoMailAddressInquiryResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "管理者お客様情報ﾒｰﾙｱﾄﾞﾚｽ全件ﾀﾞｳﾝﾛｰﾄﾞサービスの取得に失敗しました。",
                l_response.errorInfo,l_ex);
            return l_response;      
        }
        
        //管理者お客様情報ﾒｰﾙｱﾄﾞﾚｽ全件ﾀﾞｳﾝﾛｰﾄﾞサービス.execute()メソッドをコールする
        try
        {
            l_response =
                (WEB3AdminAccInfoMailAddressInquiryResponse)l_service.execute(
                    l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response = (WEB3AdminAccInfoMailAddressInquiryResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request,
                "管理者お客様情報ﾒｰﾙｱﾄﾞﾚｽ全件ﾀﾞｳﾝﾛｰﾄﾞサービスに失敗しました。",
                l_ex);
            return l_response; 
        }
        
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * (ダウンロード画面表示)<BR>
     * メールアドレス全件ダウンロード画面表示処理を行う。 <BR>
     * <BR>
     * 管理者お客様情報ﾒｰﾙｱﾄﾞﾚｽ全件ﾀﾞｳﾝﾛｰﾄﾞｻｰﾋﾞｽを取得し、<BR>
     * execute()メソッドをコールする。 <BR>
     * @@param l_request - 管理者お客様情報ﾒｰﾙｱﾄﾞﾚｽ全件ﾀﾞｳﾝﾛｰﾄﾞﾘｸｴｽﾄデータオブジェクト
     * 
     * @@return webbroker3.accountinfo.message.WEB3AdminAccInfoMailAddressDownloadResponse
     * @@throws WEB3BaseException
     * @@roseuid 4147E614023D
     */
    public WEB3AdminAccInfoMailAddressDownloadResponse downloadScreenDisplay(WEB3AdminAccInfoMailAddressDownloadRequest l_request) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " downloadScreenDisplay(WEB3AdminAccInfoMailAddressDownloadRequest)";
        log.entering(STR_METHOD_NAME);
        
        WEB3AdminAccInfoMailAddressDownloadResponse l_response = null;
        WEB3AdminAccInfoMailAddressDownloadService l_service = null;
        
        //管理者お客様情報ﾒｰﾙｱﾄﾞﾚｽ全件ﾀﾞｳﾝﾛｰﾄﾞサービスを取得
        try
        {
            l_service = (WEB3AdminAccInfoMailAddressDownloadService)Services.getService(WEB3AdminAccInfoMailAddressDownloadService.class);
        }
        //サービスで例外が発生した場合は、エラー情報をレスポンスメッセージに設定する
        catch (Exception l_ex)
        {
            l_response = (WEB3AdminAccInfoMailAddressDownloadResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "管理者お客様情報ﾒｰﾙｱﾄﾞﾚｽ全件ﾀﾞｳﾝﾛｰﾄﾞサービスの取得に失敗しました。",
                l_response.errorInfo,l_ex);
            return l_response;      
        }
        
        //管理者お客様情報ﾒｰﾙｱﾄﾞﾚｽ全件ﾀﾞｳﾝﾛｰﾄﾞサービス.execute()メソッドをコールする
        try
        {
            l_response =
                (WEB3AdminAccInfoMailAddressDownloadResponse)l_service.execute(
                    l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response = (WEB3AdminAccInfoMailAddressDownloadResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request,
                "管理者お客様情報ﾒｰﾙｱﾄﾞﾚｽ全件ﾀﾞｳﾝﾛｰﾄﾞサービスに失敗しました。",
                l_ex);
            return l_response; 
        }
        
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * (メールアドレス全件ダウンロード)<BR>
     * メールアドレス全件ダウンロードファ@イルデータ取得処理を行う。 <BR>
     * <BR>
     * 管理者お客様情報ﾒｰﾙｱﾄﾞﾚｽ全件ﾀﾞｳﾝﾛｰﾄﾞｻｰﾋﾞｽを取得し、<BR>
     * execute()メソッドをコールする。 <BR>
     * @@param l_request - 管理者お客様情報ﾒｰﾙｱﾄﾞﾚｽ全件ﾌｧｲﾙﾀﾞｳﾝﾛｰﾄﾞﾘｸｴｽﾄデータオブジェクト
     * 
     * @@return webbroker3.accountinfo.message.WEB3AdminAccInfoMailAddressFileDownloadResponse
     * @@throws WEB3BaseException
     * @@roseuid 4147E614025D
     */
    public WEB3AdminAccInfoMailAddressFileDownloadResponse mailAddressDownload(WEB3AdminAccInfoMailAddressFileDownloadRequest l_request) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " mailAddressDownload(WEB3AdminAccInfoMailAddressFileDownloadRequest)";
        log.entering(STR_METHOD_NAME);
        
        WEB3AdminAccInfoMailAddressFileDownloadResponse l_response = null;
        WEB3AdminAccInfoMailAddressDownloadService l_service = null;
        
        //管理者お客様情報ﾒｰﾙｱﾄﾞﾚｽ全件ﾀﾞｳﾝﾛｰﾄﾞサービスを取得
        try
        {
            l_service = (WEB3AdminAccInfoMailAddressDownloadService)Services.getService(WEB3AdminAccInfoMailAddressDownloadService.class);
        }
        //サービスで例外が発生した場合は、エラー情報をレスポンスメッセージに設定する
        catch (Exception l_ex)
        {
            l_response = (WEB3AdminAccInfoMailAddressFileDownloadResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "管理者お客様情報ﾒｰﾙｱﾄﾞﾚｽ全件ﾀﾞｳﾝﾛｰﾄﾞサービスの取得に失敗しました。",
                l_response.errorInfo,l_ex);
            return l_response;      
        }
        
        //管理者お客様情報ﾒｰﾙｱﾄﾞﾚｽ全件ﾀﾞｳﾝﾛｰﾄﾞサービス.execute()メソッドをコールする
        try
        {
            l_response =
                (WEB3AdminAccInfoMailAddressFileDownloadResponse)l_service.execute(
                    l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response = (WEB3AdminAccInfoMailAddressFileDownloadResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request,
                "管理者お客様情報ﾒｰﾙｱﾄﾞﾚｽ全件ﾀﾞｳﾝﾛｰﾄﾞサービスに失敗しました。",
                l_ex);
            return l_response; 
        }
        
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
}
@
