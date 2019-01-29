head	1.2;
access;
symbols;
locks; strict;
comment	@// @;


1.2
date	2011.03.24.08.49.12;	author che-jin;	state Exp;
branches;
next	1.1;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5cc4d8b0586158f;
filename	WEB3GentradeBatoClientHandler.java;

1.1
date	2011.03.14.05.46.20;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3GentradeBatoClientHandler.java;


desc
@@


1.2
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研ビジネス・イノベーション
File Name        : 電子鳩システム接続ハンドラクラス(WEB3GentradeBatoClientHandler)
Author Name      : Daiwa Institute of Research Business Innovation
Revesion History : 2005/03/15 仲川(ＳＲＡ) 新規作成
Revesion History : 2010/11/10 張騰宇(中訊) モデル353
*/
package webbroker3.gentrade.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.gentrade.message.WEB3DocumentDeliverRequest;
import webbroker3.gentrade.message.WEB3DocumentDeliverResponse;
import webbroker3.gentrade.message.WEB3GentradeProspectusDisplayResponse;
import webbroker3.gentrade.message.WEB3GentradeProspectusDisplayRequest;
import webbroker3.gentrade.message.WEB3GentradeMenuDisplayResponse;
import webbroker3.gentrade.message.WEB3GentradeMenuDisplayRequest;
import webbroker3.gentrade.message.WEB3GentradeReadDisplayRequest;
import webbroker3.gentrade.message.WEB3GentradeReadDisplayResponse;
import webbroker3.gentrade.service.delegate.WEB3GentradeBatoClientService;
import webbroker3.util.WEB3LogUtility;

/**
 * 電子鳩システム接続ハンドラクラス
 */
public class WEB3GentradeBatoClientHandler implements MessageHandler 
{
   
    /**
     * ログ出力ユーティリティ
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3GentradeBatoClientHandler.class);
    
    /**
     * デフォルトコンストラクタ
     * @@roseuid 423698CB025F
     */
    public WEB3GentradeBatoClientHandler() 
    {
    }
   
    /**
     * （目論見書表示）<br />
     * <br />
     * 電子鳩システム接続サービスを取得し、execute()メソッドをコールする。<br />
     * <br />
     * @@param l_request - リクエストデータ<br />
     * <br />
     * @@return webbroker3.gentrade.message.WEB3GentradeProspectusDisplayResponse<br />
     * @@roseuid 421032820217<br />
     */
    public WEB3GentradeProspectusDisplayResponse displayProspectus(
        WEB3GentradeProspectusDisplayRequest l_request
        ) 
    {
        final String STR_METHOD_NAME = 
            " displayProspectus(WEB3GentradeProspectusDisplayRequest)";    
        log.entering(STR_METHOD_NAME);
        
        WEB3GentradeBatoClientService l_service = null;
        WEB3GentradeProspectusDisplayResponse l_response = null;
        
        try
        {
            l_service =
                (WEB3GentradeBatoClientService)Services.getService(
                WEB3GentradeBatoClientService.class);
        }
        catch (Exception l_ex)
        {
            l_response = 
                (WEB3GentradeProspectusDisplayResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "電子鳩システム接続サービスの取得に失敗しました。",
                l_response.errorInfo,
                l_ex);
            return l_response;   
        }
        
        try
        {
            l_response = 
                (WEB3GentradeProspectusDisplayResponse)l_service.execute(
                    l_request
                    );
        }
        catch (WEB3BaseException l_ex)
        {
            l_response = 
                (WEB3GentradeProspectusDisplayResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            l_response.errorMessage = l_ex.getErrorMessage();
            log.error(l_request, 
                "電子鳩システム接続サービスに失敗しました。",
                l_ex);
            return l_response;
        }
        catch (Exception l_ex) 
        {
            l_response = 
                (WEB3GentradeProspectusDisplayResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            l_response.errorMessage = 
                "電子鳩システム接続サービスにて、想定外の例外が発生しました。" +
                "例外の内容は、StackTrace を参照してください。";
            log.error(
                l_request,
                l_response.errorMessage,
                l_response.errorInfo,
                l_ex);
        }

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
   
    /**
     * 電子鳩システム接続サービスを取得し、execute()メソッドをコールする。<br />
     * @@param l_request - リクエストデータ<br />
     * <br />
     * @@return webbroker3.gentrade.message.WEB3GentradeMenuDisplayResponse<br />
     * @@roseuid 4215BA7002F6<br />
     */
    public WEB3GentradeMenuDisplayResponse displayMenu(
        WEB3GentradeMenuDisplayRequest l_request
        ) 
    {
        final String STR_METHOD_NAME = 
            " displayMenu(WEB3GentradeMenuDisplayRequest)";    
        log.entering(STR_METHOD_NAME);
        
        WEB3GentradeBatoClientService l_service = null;
        WEB3GentradeMenuDisplayResponse l_response = null;
        
        try
        {
            l_service =
                (WEB3GentradeBatoClientService)Services.getService(
                WEB3GentradeBatoClientService.class);
        }
        catch (Exception l_ex)
        {
            l_response = 
                (WEB3GentradeMenuDisplayResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "電子鳩システム接続サービスの取得に失敗しました。",
                l_response.errorInfo,
                l_ex);
            return l_response;   
        }
        
        try
        {
            l_response = 
                (WEB3GentradeMenuDisplayResponse)l_service.execute(
                    l_request
                    );
        }
        catch (WEB3BaseException l_ex)
        {
            l_response = 
                (WEB3GentradeMenuDisplayResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            l_response.errorMessage = l_ex.getErrorMessage();
            log.error(l_request, 
                "電子鳩システム接続サービスに失敗しました。",
                l_ex);
            return l_response;
        }
        catch (Exception l_ex) 
        {
            l_response = 
                (WEB3GentradeMenuDisplayResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            l_response.errorMessage = 
                "電子鳩システム接続サービスにて、想定外の例外が発生しました。" +
                "例外の内容は、StackTrace を参照してください。";
            log.error(
                l_request,
                l_response.errorMessage,
                l_response.errorInfo,
                l_ex);
        }

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * (書面交付)<BR>
     * <BR>
     * 電子鳩システム接続サービスを取得し、execute()メソッドをコールする。<BR>
     * <BR>
     * @@param l_request リクエストデータ
     * @@return WEB3DocumentDeliverResponse
     */
    public WEB3DocumentDeliverResponse documentDeliver(WEB3DocumentDeliverRequest l_request)
    {
        final String STR_METHOD_NAME = "documentDeliver(WEB3DocumentDeliverRequest)";
        
        log.entering(STR_METHOD_NAME);
        
        WEB3GentradeBatoClientService l_service = null;
        WEB3DocumentDeliverResponse l_response = null;
        
        // 電子鳩システム接続サービスを取得
        try
        {
            l_service = 
                (WEB3GentradeBatoClientService)
                    Services.getService(WEB3GentradeBatoClientService.class);
        }
        catch (Exception e)
        {
            l_response = 
                (WEB3DocumentDeliverResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request, 
                "電子鳩システム接続サービスの取得に失敗しました。", 
                l_response.errorInfo, 
                e);
            
            return l_response;

        }
        
        // 電子鳩システム接続サービスを取得し、execute()メソッドをコールする。
        try
        {
            l_response = 
                (WEB3DocumentDeliverResponse)l_service.execute(l_request);
        }
        catch (WEB3BaseException e)
        {
            l_response = 
                (WEB3DocumentDeliverResponse)l_request.createResponse();
            l_response.errorInfo = e.getErrorInfo();
            log.error(
                l_request, 
                "書面交付に失敗しました。", 
                e);
            
            return l_response;

        }
        
        log.exiting(STR_METHOD_NAME);
        
        return l_response;
    }

    /**
     * (電子鳩閲覧表示)<BR>
     * <BR>
     * 電子鳩システム接続サービスを取得し、execute()メソッドをコールする。<BR>
     * <BR>
     * @@param l_request リクエストデータ
     * @@return WEB3GentradeReadDisplayResponse
     */
    public WEB3GentradeReadDisplayResponse readDisplay(WEB3GentradeReadDisplayRequest l_request)
    {
        final String STR_METHOD_NAME = "readDisplay(WEB3GentradeReadDisplayRequest)";

        log.entering(STR_METHOD_NAME);

        WEB3GentradeBatoClientService l_service = null;
        WEB3GentradeReadDisplayResponse l_response = null;

        // 電子鳩システム接続サービスを取得
        try
        {
            l_service =
                (WEB3GentradeBatoClientService)Services.getService(WEB3GentradeBatoClientService.class);
        }
        catch (Exception l_ex)
        {
            l_response =
                (WEB3GentradeReadDisplayResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "電子鳩システム接続サービスの取得に失敗しました。",
                l_response.errorInfo,
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

        // 電子鳩システム接続サービスを取得し、execute()メソッドをコールする。
        try
        {
            l_response =
                (WEB3GentradeReadDisplayResponse)l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response =
                (WEB3GentradeReadDisplayResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request,
                "電子鳩閲覧表示処理に失敗しました。",
                l_response.errorInfo,
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        catch (WEB3BaseRuntimeException l_ex)
        {
            l_response = (WEB3GentradeReadDisplayResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request,
                "電子鳩閲覧表示処理に失敗しました。",
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


1.1
log
@*** empty log message ***
@
text
@d2 1
a2 1
Copyright        : (株)大和総研 証券ソリューションシステム第二部
d4 1
a4 1
Author Name      : Daiwa Institute of Research
d6 1
d14 1
d22 2
d259 73
@

