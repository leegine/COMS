head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.06.46.10;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7984d7db5d91d86;
filename	WEB3AdminSrvRegiAccountDataDownloadHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : サービス利用管理者顧客データダウンロードハンドラ(WEB3AdminSrvRegiAccountDataDownloadHandler.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/18 鄭海良(中訊) 新規作成
*/

package webbroker3.srvregi.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.srvregi.message.WEB3AdminSrvRegiDownloadRequest;
import webbroker3.srvregi.message.WEB3AdminSrvRegiDownloadResponse;
import webbroker3.srvregi.service.delegate.WEB3AdminSrvRegiAccountDataDownloadService;
import webbroker3.util.WEB3LogUtility;

/**
 * (サービス利用管理者顧客データダウンロードハンドラ)<BR>
 * 管理者顧客データダウンロードハンドラクラス<BR>
 *  
 * @@author 鄭海良
 * @@version 1.0  
 */
public class WEB3AdminSrvRegiAccountDataDownloadHandler implements MessageHandler 
{
    
    /**
     * ログ出力ユーティリティ。
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminSrvRegiAccountDataDownloadHandler.class);
    
    /**
     * @@roseuid 416F415C01A5
     */
    public WEB3AdminSrvRegiAccountDataDownloadHandler() 
    {
     
    }
    
    /**
     * (顧客ダウンロード)<BR>
     * サービス利用管理者顧客データダウンロード処理を行う。<BR>
     * <BR>
     * サービス利用管理者顧客データダウンロードサービスを取得し、execute( )メソッドをコールする。<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * サービス利用管理者ダウンロードリクエスト　@オブジェクト<BR>
     * @@return webbroker3.srvregi.message.WEB3AdminSrvRegiDownloadResponse
     * @@roseuid 4107082302BE
     */
    public WEB3AdminSrvRegiDownloadResponse acountDownload(WEB3AdminSrvRegiDownloadRequest l_request) 
    {
        final String STR_METHOD_NAME = " acountDownload(WEB3AdminSrvRegiDownloadRequest )";    
        log.entering(STR_METHOD_NAME);
        
        WEB3AdminSrvRegiDownloadResponse l_response = null;
        WEB3AdminSrvRegiAccountDataDownloadService l_service = null;        
        try
        {
            //get service
            l_service = 
                (WEB3AdminSrvRegiAccountDataDownloadService)Services.getService(
                    WEB3AdminSrvRegiAccountDataDownloadService.class);
        }
        catch (Exception l_ex)
        {
            
            l_response = (WEB3AdminSrvRegiDownloadResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "サービス利用管理者顧客データダウンロードサービスの取得に失敗しました。",
                l_response.errorInfo,
                l_ex);
            return l_response;                  
        }

        try
        {
            //execute()メソッドをコールする
            l_response = (WEB3AdminSrvRegiDownloadResponse)l_service.execute(l_request);//WEB3BaseException
        }
        catch (WEB3BaseException l_ex)
        {
            l_response = (WEB3AdminSrvRegiDownloadResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request, "顧客ダウンロードに失敗しました。", l_ex);
            return l_response;
        }

        log.exiting(STR_METHOD_NAME);
        return l_response;    
    }
}
@
