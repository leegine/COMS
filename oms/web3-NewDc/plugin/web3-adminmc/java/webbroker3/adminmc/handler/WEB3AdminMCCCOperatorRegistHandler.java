head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.02.41.09;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	WEB3AdminMCCCOperatorRegistHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者メニュー制御CCオペレータ登録ハンドラ(WEB3AdminMCCCOperatorRegistHandler.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/11/29 範慧琴 (中訊) 新規作成
*/

package webbroker3.adminmc.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.util.WEB3LogUtility;
import webbroker3.adminmc.message.WEB3AdminMCCCOperatorRegistInputResponse;
import webbroker3.adminmc.message.WEB3AdminMCCCOperatorRegistInputRequest;
import webbroker3.adminmc.message.WEB3AdminMCCCOperatorRegistConfirmResponse;
import webbroker3.adminmc.message.WEB3AdminMCCCOperatorRegistConfirmRequest;
import webbroker3.adminmc.message.WEB3AdminMCCCOperatorRegistCompleteResponse;
import webbroker3.adminmc.message.WEB3AdminMCCCOperatorRegistCompleteRequest;
import webbroker3.adminmc.service.delegate.WEB3AdminMCCCOperatorRegistService;

/**
 * (管理者メニュー制御CCオペレータ登録ハンドラ)<BR>
 * 管理者メニュー制御CCオペレータ登録ハンドラ<BR>
 * 
 * @@author 範慧琴
 * @@version 1.0
 */
public class WEB3AdminMCCCOperatorRegistHandler implements MessageHandler 
{

    /**         
     * ログ出力ユーティリティ。         
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminMCCCOperatorRegistHandler.class);
    
    /**
     * @@roseuid 4198642B0280
     */
    public WEB3AdminMCCCOperatorRegistHandler() 
    {
     
    }
    
    /**
     * (入力画面表示)<BR>
     * CCオペレータ登録入力画面表示処理を実施する。<BR>
     * <BR>
     * 管理者メニュー制御CCオペレータ登録サービスを取得しexecute()メソッドをコールする・
     * <BR>
     * <BR>
     * @@param l_request - (リクエストデータ)<BR>
     * 管理者メニュー制御CCｵﾍﾟﾚｰﾀ登録入力ﾘｸｴｽﾄデータオブジェクト<BR>
     * <BR>
     * @@return webbroker3.adminmc.message.WEB3AdminMCCCOperatorRegistInputResponse
     * @@throws WEB3BaseException
     * @@roseuid 417F7F370260
     */
    public WEB3AdminMCCCOperatorRegistInputResponse inputScreenDisplay(WEB3AdminMCCCOperatorRegistInputRequest l_request) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME =" inputScreenDisplay(WEB3AdminMCCCOperatorRegistInputRequest l_request)";
        log.entering(STR_METHOD_NAME);
        WEB3AdminMCCCOperatorRegistInputResponse l_response = null;
        WEB3AdminMCCCOperatorRegistService l_service = null;     
        try
        {
            l_service= (WEB3AdminMCCCOperatorRegistService)Services.getService(WEB3AdminMCCCOperatorRegistService.class); 
        }
        catch (Exception l_ex)
        {
            l_response = (WEB3AdminMCCCOperatorRegistInputResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(l_request, "管理者メニュー制御CCオペレータ登録サービスImplの取得に失敗しました", l_response.errorInfo, l_ex);
            return l_response;
        }
                 
        try
        {
            //execute()メソッドをコールする
            l_response = (WEB3AdminMCCCOperatorRegistInputResponse)l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response =
                (WEB3AdminMCCCOperatorRegistInputResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request,
                "CCオペレータ登録入力画面表示処理に失敗しました",
                l_ex);
            return l_response;
        }
        
        log.exiting(STR_METHOD_NAME);             
        return l_response; 

    }
    
    /**
     * (扱者登録確認)<BR>
     * CCオペレータ登録確認処理を実施する。<BR>
     * <BR>
     * 管理者メニュー制御CCオペレータ登録サービスを取得しexecute()メソッドをコールする・
     * <BR>
     * <BR>
     * <BR>
     * @@param l_request - (リクエストデータ)<BR>
     * 管理者メニュー制御CCｵﾍﾟﾚｰﾀ登録確認ﾘｸｴｽﾄデータオブジェクト<BR>
     * @@return webbroker3.adminmc.message.WEB3AdminMCCCOperatorRegistConfirmResponse
     * @@roseuid 417F7F370262
     */
    public WEB3AdminMCCCOperatorRegistConfirmResponse traderRegistConfirm(WEB3AdminMCCCOperatorRegistConfirmRequest l_request) 
    {
        final String STR_METHOD_NAME =" traderRegistConfirm(WEB3AdminMCCCOperatorRegistConfirmRequest l_request)";
        log.entering(STR_METHOD_NAME);
        WEB3AdminMCCCOperatorRegistConfirmResponse l_response = null;
        WEB3AdminMCCCOperatorRegistService l_service = null;     
        try
        {
            l_service= (WEB3AdminMCCCOperatorRegistService)Services.getService(WEB3AdminMCCCOperatorRegistService.class); 
        }
        catch (Exception l_ex)
        {
            l_response = (WEB3AdminMCCCOperatorRegistConfirmResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(l_request, "管理者メニュー制御CCオペレータ登録サービスImplの取得に失敗しました", l_response.errorInfo, l_ex);
            return l_response;
        }
                 
        try
        {
            //execute()メソッドをコールする
            l_response = (WEB3AdminMCCCOperatorRegistConfirmResponse)l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response =
                (WEB3AdminMCCCOperatorRegistConfirmResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request,
                "CCオペレータ登録確認処理に失敗しました",
                l_ex);
            return l_response;
        }
        
        log.exiting(STR_METHOD_NAME);             
        return l_response; 

    }
    
    /**
     * (扱者登録完了)<BR>
     * CCオペレータ登録完了処理を実施する。<BR>
     * <BR>
     * 管理者メニュー制御CCオペレータ登録サービスを取得しexecute()メソッドをコールする・
     * <BR>
     * <BR>
     * <BR>
     * @@param l_request - (リクエストデータ)<BR>
     * 管理者メニュー制御CCｵﾍﾟﾚｰﾀ登録完了ﾘｸｴｽﾄデータオブジェクト<BR>
     * @@return webbroker3.adminmc.message.WEB3AdminMCCCOperatorRegistCompleteResponse
     * @@roseuid 417F7F370264
     */
    public WEB3AdminMCCCOperatorRegistCompleteResponse traderRegistComplete(WEB3AdminMCCCOperatorRegistCompleteRequest l_request) 
    {
        final String STR_METHOD_NAME =" traderRegistComplete(WEB3AdminMCCCOperatorRegistCompleteRequest l_request)";
        log.entering(STR_METHOD_NAME);
        WEB3AdminMCCCOperatorRegistCompleteResponse l_response = null;
        WEB3AdminMCCCOperatorRegistService l_service = null;     
        try
        {
            l_service= (WEB3AdminMCCCOperatorRegistService)Services.getService(WEB3AdminMCCCOperatorRegistService.class); 
        }
        catch (Exception l_ex)
        {
            l_response = (WEB3AdminMCCCOperatorRegistCompleteResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(l_request, "管理者メニュー制御CCオペレータ登録サービスImplの取得に失敗しました", l_response.errorInfo, l_ex);
            return l_response;
        }
                 
        try
        {
            //execute()メソッドをコールする
            l_response = (WEB3AdminMCCCOperatorRegistCompleteResponse)l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response =
                (WEB3AdminMCCCOperatorRegistCompleteResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request,
                "CCオペレータ登録完了処理に失敗しました",
                l_ex);
            return l_response;
        }
        
        log.exiting(STR_METHOD_NAME);             
        return l_response; 
    }
}
@
