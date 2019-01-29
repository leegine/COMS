head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.02.40.55;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	WEB3AdminMCCCOperatorChangeHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者メニュー制御CCオペレータ変更ハンドラ(WEB3AdminMCCCOperatorChangeHandler.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/11/23  温 顕 法@ (中訊) 新規作成
*/

package webbroker3.adminmc.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.util.WEB3LogUtility;

import webbroker3.adminmc.message.WEB3AdminMCCCOperatorChangeInputResponse;
import webbroker3.adminmc.message.WEB3AdminMCCCOperatorChangeInputRequest;
import webbroker3.adminmc.message.WEB3AdminMCCCOperatorChangeConfirmResponse;
import webbroker3.adminmc.message.WEB3AdminMCCCOperatorChangeConfirmRequest;
import webbroker3.adminmc.message.WEB3AdminMCCCOperatorChangeCompleteResponse;
import webbroker3.adminmc.message.WEB3AdminMCCCOperatorChangeCompleteRequest;
import webbroker3.adminmc.service.delegate.WEB3AdminMCCCOperatorChangeService;

/**
 * (管理者メニュー制御CCオペレータ変更ハンドラ)<BR>
 * 管理者メニュー制御CCオペレータ変更ハンドラ<BR>
 * @@author 温顕法@
 * @@version 1.0
 */
 
public class WEB3AdminMCCCOperatorChangeHandler implements MessageHandler 
{

    /**
     *ログ出力ユーティリティ。
     */
    private WEB3LogUtility log = WEB3LogUtility.getInstance(WEB3AdminMCCCOperatorChangeHandler.class);
    
    /**
     * @@roseuid 4198642E005D
     */
    public WEB3AdminMCCCOperatorChangeHandler() 
    {
     
    }
    
    /**
     * (入力画面表示)<BR>
     * CCオペレータ変更入力画面表示処理を実施する。<BR>
     * <BR>
     * 管理者メニュー制御CCオペレータ変更サービスを取得し、execute()メソッドをコールす・
     * 。<BR>
     * <BR>
     * <BR>
     * @@param l_request - (リクエストデータ)<BR>
     * 管理者メニュー制御CCｵﾍﾟﾚｰﾀ変更入力ﾘｸｴｽﾄデータオブジェクト<BR>
     * <BR>
     * @@return webbroker3.adminmc.message.WEB3AdminMCCCOperatorChangeInputResponse
     * @@throws WEB3BaseException
     * @@roseuid 417F82E6031C
     */
    public WEB3AdminMCCCOperatorChangeInputResponse inputScreenDisplay(WEB3AdminMCCCOperatorChangeInputRequest l_request) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME =
                getClass().getName() + ".inputScreenDisplay(WEB3AdminMCCCOperatorChangeInputRequest l_request)";
        log.entering(STR_METHOD_NAME);
        WEB3AdminMCCCOperatorChangeInputResponse l_response = null;
        WEB3AdminMCCCOperatorChangeService l_service = null;     
        try
        {
            l_service = (WEB3AdminMCCCOperatorChangeService)Services.getService(WEB3AdminMCCCOperatorChangeService.class); 
        }
        catch (Exception l_ex)
        {
            l_response = (WEB3AdminMCCCOperatorChangeInputResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(l_request, "管理者メニュー制御CCオペレータ変更サービスImplの取得に失敗しました.", l_response.errorInfo, l_ex);
            return l_response;
        }
                 
        try
        {
            //execute()メソッドをコールする
            l_response = (WEB3AdminMCCCOperatorChangeInputResponse)l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response =
                (WEB3AdminMCCCOperatorChangeInputResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request,
                "CCオペレータ変更入力画面表示処理に失敗しました.",
                l_ex);
            return l_response;
        }
        
        log.exiting(STR_METHOD_NAME);      
        
        return l_response; 
    }
    
    /**
     * (扱者変更確認)<BR>
     * CCオペレータ変更確認処理を実施する。<BR>
     * <BR>
     * 管理者メニュー制御CCオペレータ変更サービスを取得し、execute()メソッドをコールす・
     * 。<BR>
     * <BR>
     * <BR>
     * @@param l_request - (リクエストデータ)<BR>
     * 管理者メニュー制御CCｵﾍﾟﾚｰﾀ変更確認ﾘｸｴｽﾄデータオブジェクト<BR>
     * @@return webbroker3.adminmc.message.WEB3AdminMCCCOperatorChangeConfirmResponse
     * @@roseuid 417F82E6032B
     */
    public WEB3AdminMCCCOperatorChangeConfirmResponse traderChangeConfirm(WEB3AdminMCCCOperatorChangeConfirmRequest l_request) 
    {
        final String STR_METHOD_NAME =
                getClass().getName() + ".traderChangeConfirm(WEB3AdminMCCCOperatorChangeConfirmRequest l_request)";
        log.entering(STR_METHOD_NAME);
        WEB3AdminMCCCOperatorChangeConfirmResponse l_response = null;
        WEB3AdminMCCCOperatorChangeService l_service = null;     
        try
        {
            l_service = (WEB3AdminMCCCOperatorChangeService)Services.getService(WEB3AdminMCCCOperatorChangeService.class); 
        }
        catch (Exception l_ex)
        {
            l_response = (WEB3AdminMCCCOperatorChangeConfirmResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(l_request, "管理者メニュー制御CCオペレータ変更サービスImplの取得に失敗しました.", l_response.errorInfo, l_ex);
            return l_response;
        }
                 
        try
        {
            //execute()メソッドをコールする
            l_response = (WEB3AdminMCCCOperatorChangeConfirmResponse)l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response =
                (WEB3AdminMCCCOperatorChangeConfirmResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request,
                "CCオペレータ変更確認処理に失敗しました.",
                l_ex);
            return l_response;
        }
        
        log.exiting(STR_METHOD_NAME);      
        
        return l_response; 
    }
    
    /**
     * (扱者変更完了)<BR>
     * CCオペレータ変更完了処理を実施する。<BR>
     * <BR>
     * 管理者メニュー制御CCオペレータ変更サービスを取得し、execute()メソッドをコールす・
     * 。<BR>
     * <BR>
     * <BR>
     * @@param l_request - (リクエストデータ)<BR>
     * 管理者メニュー制御CCｵﾍﾟﾚｰﾀ変更完了ﾘｸｴｽﾄデータオブジェクト<BR>
     * @@return webbroker3.adminmc.message.WEB3AdminMCCCOperatorChangeCompleteResponse
     * @@roseuid 417F82E6032D
     */
    public WEB3AdminMCCCOperatorChangeCompleteResponse traderChangeComplete(WEB3AdminMCCCOperatorChangeCompleteRequest l_request) 
    {
        final String STR_METHOD_NAME =
                getClass().getName() + ".traderChangeComplete(WEB3AdminMCCCOperatorChangeCompleteRequest l_request)";
        log.entering(STR_METHOD_NAME);
        WEB3AdminMCCCOperatorChangeCompleteResponse l_response = null;
        WEB3AdminMCCCOperatorChangeService l_service = null;     
        try
        {
            l_service= (WEB3AdminMCCCOperatorChangeService)Services.getService(WEB3AdminMCCCOperatorChangeService.class); 
        }
        catch (Exception l_ex)
        {
            l_response = (WEB3AdminMCCCOperatorChangeCompleteResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(l_request, "管理者メニュー制御CCオペレータ変更サービスImplの取得に失敗しました.", l_response.errorInfo, l_ex);
            return l_response;
        }
                 
        try
        {
            //execute()メソッドをコールする
            l_response = (WEB3AdminMCCCOperatorChangeCompleteResponse)l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response =
                (WEB3AdminMCCCOperatorChangeCompleteResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request,
                "CCオペレータ変更完了に失敗しました.",
                l_ex);
            return l_response;
        }
        
        log.exiting(STR_METHOD_NAME);      
        
        return l_response; 
    }
}
@
