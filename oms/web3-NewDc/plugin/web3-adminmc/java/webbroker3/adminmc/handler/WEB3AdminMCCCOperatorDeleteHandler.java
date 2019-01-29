head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.02.41.55;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	WEB3AdminMCCCOperatorDeleteHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者メニュー制御CCオペレータ削除ハンドラ(WEB3AdminMCCCOperatorDeleteHandler.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/11/24 範慧琴 (中訊) 新規作成
*/

package webbroker3.adminmc.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;
import webbroker3.adminmc.message.WEB3AdminMCCCOperatorDeleteConfirmResponse;
import webbroker3.adminmc.message.WEB3AdminMCCCOperatorDeleteConfirmRequest;
import webbroker3.adminmc.message.WEB3AdminMCCCOperatorDeleteCompleteResponse;
import webbroker3.adminmc.message.WEB3AdminMCCCOperatorDeleteCompleteRequest;
import webbroker3.adminmc.service.delegate.WEB3AdminMCCCOperatorDeleteService;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.util.WEB3LogUtility;

/**
 * (管理者メニュー制御CCオペレータ削除ハンドラ)<BR>
 * 管理者メニュー制御CCオペレータ削除ハンドラ<BR>
 * 
 * @@author 範慧琴
 * @@version 1.0
 */
public class WEB3AdminMCCCOperatorDeleteHandler implements MessageHandler 
{
    
    /**         
     * ログ出力ユーティリティ。         
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminMCCCOperatorDeleteHandler.class);  
        
    /**
     * @@roseuid 4198642B0119
     */
    public WEB3AdminMCCCOperatorDeleteHandler() 
    {
     
    }
    
    /**
     * (扱者削除確認)<BR>
     * CCオペレータ削除確認処理を実施する。<BR>
     * <BR>
     * 管理者メニュー制御CCオペレータ削除サービスを取得し、execute()メソッドをコールす・
     * 。<BR>
     * <BR>
     * @@param l_request - (リクエストデータ)<BR>
     * 管理者メニュー制御CCｵﾍﾟﾚｰﾀ削除確認ﾘｸｴｽﾄデータオブジェクト<BR>
     * @@return webbroker3.adminmc.message.WEB3AdminMCCCOperatorDeleteConfirmResponse
     * @@roseuid 417F793703B8
     */
    public WEB3AdminMCCCOperatorDeleteConfirmResponse traderDeleteConfirm(WEB3AdminMCCCOperatorDeleteConfirmRequest l_request) 
    {
        final String STR_METHOD_NAME = " traderDeleteConfirm(WEB3AdminMCCCOperatorDeleteConfirmRequest l_request)";
        log.entering(STR_METHOD_NAME);
        
        WEB3AdminMCCCOperatorDeleteConfirmResponse l_response = null;
        WEB3AdminMCCCOperatorDeleteService l_service = null;     
        try
        {
            l_service = (WEB3AdminMCCCOperatorDeleteService)Services.getService(WEB3AdminMCCCOperatorDeleteService.class); 
        }
        catch (Exception l_ex)
        {
            l_response = (WEB3AdminMCCCOperatorDeleteConfirmResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(l_request, "管理者メニュー制御CCオペレータ削除サービスImplの取得に失敗しました", l_response.errorInfo, l_ex);
            return l_response;
        }
                 
        try
        {
            //execute()メソッドをコールする
            l_response = (WEB3AdminMCCCOperatorDeleteConfirmResponse)l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response =
                (WEB3AdminMCCCOperatorDeleteConfirmResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request,
                "CCオペレータ削除確認処理に失敗しました",
                l_ex);
            return l_response;
        }
        
        log.exiting(STR_METHOD_NAME);      
        
        return l_response; 

    }
    
    /**
     * (扱者削除完了)<BR>
     * CCオペレータ削除完了処理を実施する。<BR>
     * <BR>
     * 管理者メニュー制御CCオペレータ削除サービスを取得し、execute()メソッドをコールす・
     * 。<BR>
     * <BR>
     * @@param l_request - (リクエストデータ)<BR>
     * 管理者メニュー制御CCｵﾍﾟﾚｰﾀ削除完了ﾘｸｴｽﾄデータオブジェクト<BR>
     * @@return webbroker3.adminmc.message.WEB3AdminMCCCOperatorDeleteCompleteResponse
     * @@roseuid 417F793703BA
     */
    public WEB3AdminMCCCOperatorDeleteCompleteResponse traderDeleteComplete(WEB3AdminMCCCOperatorDeleteCompleteRequest l_request) 
    {
        final String STR_METHOD_NAME = " traderDeleteComplete(WEB3AdminMCCCOperatorDeleteCompleteRequest l_request)";
        log.entering(STR_METHOD_NAME);
        
        WEB3AdminMCCCOperatorDeleteCompleteResponse l_response = null;
        WEB3AdminMCCCOperatorDeleteService l_service = null;     
        try
        {
            l_service = (WEB3AdminMCCCOperatorDeleteService)Services.getService(WEB3AdminMCCCOperatorDeleteService.class); 
        }
        catch (Exception l_ex)
        {
            l_response = (WEB3AdminMCCCOperatorDeleteCompleteResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(l_request, "管理者メニュー制御CCオペレータ削除サービスImplの取得に失敗しました", l_response.errorInfo, l_ex);
            return l_response;
        }
                 
        try
        {
            //execute()メソッドをコールする
            l_response = (WEB3AdminMCCCOperatorDeleteCompleteResponse)l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response =
                (WEB3AdminMCCCOperatorDeleteCompleteResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request,
                "CCオペレータ削除完了処理に失敗しました",
                l_ex);
            return l_response;
        }
        
        log.exiting(STR_METHOD_NAME);      
        
        return l_response; 

    }
}
@
