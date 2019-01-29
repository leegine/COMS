head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.02.41.30;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	WEB3AdminMCCCOperatorListHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者メニュー制御CCオペレータ一覧ハンドラ(WEB3AdminMCCCOperatorListHandler.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/11/22 範慧琴 (中訊) 新規作成
*/

package webbroker3.adminmc.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.adminmc.service.delegate.WEB3AdminMCCCOperatorListService;
import webbroker3.adminmc.message.WEB3AdminMCCCOperatorListInputResponse;
import webbroker3.adminmc.message.WEB3AdminMCCCOperatorListInputRequest;
import webbroker3.adminmc.message.WEB3AdminMCCCOperatorListResponse;
import webbroker3.adminmc.message.WEB3AdminMCCCOperatorListRequest;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.util.WEB3LogUtility;

/**
 * (管理者メニュー制御CCオペレータ一覧ハンドラ)<BR>
 * 管理者メニュー制御CCオペレータ一覧ハンドラ<BR>
 * 
 * @@author 範慧琴
 * @@version 1.0
 */
public class WEB3AdminMCCCOperatorListHandler implements MessageHandler 
{
    /**
     *ログ出力ユーティリティ。
     */
    private WEB3LogUtility log = WEB3LogUtility.getInstance(WEB3AdminMCCCOperatorListHandler.class);        

    /**
     * @@roseuid 4198642B0000
     */
    public WEB3AdminMCCCOperatorListHandler() 
    {
     
    }
    
    /**
     * (入力画面表示)<BR>
     * CCオペレータ一覧条件入力画面表示処理を実施する。<BR>
     * <BR>
     * 管理者メニュー制御CCオペレータ一覧サービスを取得し、execute()メソッドをコールす・
     * 。<BR>
     * <BR>
     * <BR>
     * <BR>
     * @@param l_request - (リクエストデータ)<BR>
     * 管理者メニュー制御CCｵﾍﾟﾚｰﾀ一覧入力ﾘｸｴｽﾄデータオブジェクト<BR>
     * <BR>
     * @@return webbroker3.adminmc.message.WEB3AdminMCCCOperatorListInputResponse
     * @@roseuid 417F776100BA
     */
    public WEB3AdminMCCCOperatorListInputResponse inputScreenDisplay(WEB3AdminMCCCOperatorListInputRequest l_request) 
    {
        final String STR_METHOD_NAME = "inputScreenDisplay(WEB3AdminMCCCOperatorListInputRequest l_request)";
        log.entering(STR_METHOD_NAME);
        
        WEB3AdminMCCCOperatorListInputResponse l_response = null;
        WEB3AdminMCCCOperatorListService l_service = null;     
        try
        {
            l_service= (WEB3AdminMCCCOperatorListService)Services.getService(WEB3AdminMCCCOperatorListService.class); 
        }
        catch (Exception l_ex)
        {
            l_response = (WEB3AdminMCCCOperatorListInputResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(l_request, "管理者メニュー制御CCオペレータ一覧サービスの取得に失敗しました", l_response.errorInfo, l_ex);
            return l_response;
        }
                 
        try
        {
            //execute()メソッドをコールする
            l_response = (WEB3AdminMCCCOperatorListInputResponse)l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response =
                (WEB3AdminMCCCOperatorListInputResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request,
                "入力画面表示に失敗しました",
                l_ex);
            return l_response;
        }
        
        log.exiting(STR_METHOD_NAME);             
        return l_response; 

    }
    
    /**
     * (CCオペレータ一覧表示)<BR>
     * CCオペレータ一覧表示処理を実施する。<BR>
     * <BR>
     * 管理者メニュー制御CCオペレータ一覧サービスを取得し、execute()メソッドをコールす・
     * 。<BR>
     * <BR>
     * @@param l_request - (リクエストデータ)<BR>
     * 管理者メニュー制御CCｵﾍﾟﾚｰﾀ一覧ﾘｸｴｽﾄデータオブジェクト<BR>
     * @@return webbroker3.adminmc.message.WEB3AdminMCCCOperatorListResponse
     * @@roseuid 417F776100CA
     */
    public WEB3AdminMCCCOperatorListResponse CCOperatorListDisplay(WEB3AdminMCCCOperatorListRequest l_request) 
    {
        final String STR_METHOD_NAME = "CCOperatorListDisplay(WEB3AdminMCCCOperatorListRequest l_request)";
        log.entering(STR_METHOD_NAME);
        
        WEB3AdminMCCCOperatorListResponse l_response = null;
        WEB3AdminMCCCOperatorListService l_service = null;     
        try
        {
            l_service= (WEB3AdminMCCCOperatorListService)Services.getService(WEB3AdminMCCCOperatorListService.class); 
        }
        catch (Exception l_ex)
        {
            l_response = (WEB3AdminMCCCOperatorListResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(l_request, "管理者メニュー制御CCオペレータ一覧サービスの取得に失敗しました", l_response.errorInfo, l_ex);
            return l_response;
        }
                 
        try
        {
            //execute()メソッドをコールする
            l_response = (WEB3AdminMCCCOperatorListResponse)l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response =
                (WEB3AdminMCCCOperatorListResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request,
                "CCオペレータ一覧表示に失敗しました",
                l_ex);
            return l_response;
        }
        
        log.exiting(STR_METHOD_NAME);             
        return l_response; 
    }
    
}
@
