head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.05.49.15;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6f44d7efa104045;
filename	WEB3IpoBatoClientHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : IPO電子鳩接続ハンドラ(WEB3IpoBatoClientHandler.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/01/26 陳琦(中訊) 新規作成
*/
package webbroker3.ipo.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.ipo.message.WEB3IPOBatoUrlResponse;
import webbroker3.ipo.message.WEB3IPOBatoUrlRequest;
import webbroker3.ipo.service.delegate.WEB3IpoBatoClientService;
import webbroker3.util.WEB3LogUtility;

/**
 * (IPO電子鳩接続ハンドラ)<BR>
 * IPO電子鳩接続ハンドラクラス
 * 
 * @@author 陳琦
 * @@version 1.0
 */
public class WEB3IpoBatoClientHandler implements MessageHandler 
{

    /**
     * ログ出力ユーティリティ。
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(WEB3IpoBatoClientHandler.class);
    
    /**
     * @@roseuid 43D8344F031C
     */
    public WEB3IpoBatoClientHandler() 
    {
     
    }
    
    /**
     * (電子鳩URL取得)<BR>
     * 電子鳩URL取得処理<BR>
     * <BR>
     * IPO電子鳩接続サービスを取得し、execute()メソッドをコールする。
     * @@param l_request - IPO電子鳩URL取得リクエストデータオブジェクト
     * @@return webbroker3.ipo.message.WEB3IPOBatoUrlResponse
     * @@roseuid 43D08DD300AE
     */
    public WEB3IPOBatoUrlResponse getBatoUrl(WEB3IPOBatoUrlRequest l_request) 
    {

        final String STR_METHOD_NAME = " getBatoUrl(WEB3IPOBatoUrlRequest)";
                
        log.entering(STR_METHOD_NAME);

        WEB3IPOBatoUrlResponse l_response = null;
        WEB3IpoBatoClientService l_service = null;
             
        try
        {        
            // IPO電子鳩接続サービスを取得し
            l_service = (WEB3IpoBatoClientService)
                Services.getService(WEB3IpoBatoClientService.class);
        }
        //サービスで例外が発生した場合は、エラー情報をレスポンスメッセージに設定する
        catch(Exception l_e)
        {
            l_response = (WEB3IPOBatoUrlResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "IPO電子鳩接続サービスの取得に失敗しました。",
                l_response.errorInfo,l_e);
            return l_response;
        }
        
        try
        {        
            //IPO電子鳩接続サービスを取得し、execute()メソッドをコールする
            l_response = (WEB3IPOBatoUrlResponse)l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response = (WEB3IPOBatoUrlResponse) l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request, 
                "電子鳩URL取得処理の実施に失敗しました。",
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        catch (WEB3BaseRuntimeException l_ex)
        {
            l_response = (WEB3IPOBatoUrlResponse) l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request, 
                "電子鳩URL取得処理の実施に失敗しました。",
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
    
        log.exiting(STR_METHOD_NAME);

        //レスポンスオブジェクトを返却する。
        return l_response;
    }
        
}
@
