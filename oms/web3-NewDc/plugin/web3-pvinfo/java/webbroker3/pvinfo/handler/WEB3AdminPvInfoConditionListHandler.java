head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.22.08.09.10;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7444d8857e2568b;
filename	WEB3AdminPvInfoConditionListHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者表示設定一覧ハンドラ(WEB3AdminPvInfoConditionListHandler.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/18 王亞洲(中訊) 新規作成
Revesion History : 2004/10/27 李丁銀(中訊) 作成
Revesion History : 2004/11/2  魏馨(中訊) 修正
*/
package webbroker3.pvinfo.handler;

import com.fitechlabs.xtrade.kernel.handler.MessageHandler;
import com.fitechlabs.xtrade.kernel.boot.Services;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.pvinfo.message.WEB3AdminPvInfoConditionListRequest;
import webbroker3.pvinfo.message.WEB3AdminPvInfoConditionListResponse;
import webbroker3.pvinfo.message.WEB3AdminPvInfoConditionUpdateRequest;
import webbroker3.pvinfo.message.WEB3AdminPvInfoConditionUpdateResponse;
import webbroker3.pvinfo.service.delegate.WEB3AdminPvInfoConditionListService;
import webbroker3.util.WEB3LogUtility;

/**
 * (管理者表示設定一覧ハンドラ)<BR>
 * 管理者表示設定一覧ハンドラクラス<BR>
 * @@author 王亞洲
 * @@version 1.00
 */
public class WEB3AdminPvInfoConditionListHandler implements MessageHandler
{
    /** 
     *ログ出力ユーティリティ。<BR>
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(WEB3AdminPvInfoConditionListHandler.class);
    /**
     * (get表示設定一覧画面)<BR>
     * 表示設定一覧画面表示処理を行う。<BR>
     * <BR>
     * 管理者表示設定一覧サービスImplを取得し、<BR>
     * execute()メソッドをコールする。<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * 管理者・表示設定一覧リクエストオブジェクト<BR>
     * @@return webbroker3.pvinfo.message.WEB3AdminPvInfoConditionListResponse
     * @@roseuid 415BD299037F
     */
    public WEB3AdminPvInfoConditionListResponse getConditionListScreen(WEB3AdminPvInfoConditionListRequest l_request)
    {
        final String STR_METHOD_NAME = " getConditionListScreen(WEB3AdminPvInfoConditionListRequest)";
        log.entering(STR_METHOD_NAME);
        WEB3AdminPvInfoConditionListResponse l_response = null;
        WEB3AdminPvInfoConditionListService l_service = null;
        
        //管理者表示設定一覧サービスを取得し
        try
        {
            l_service = (WEB3AdminPvInfoConditionListService)Services.getService(WEB3AdminPvInfoConditionListService.class);             
        }
        catch(Exception l_ex)
        {
            l_response = (WEB3AdminPvInfoConditionListResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(l_request, "管理者表示設定一覧サービスを取得に失敗しました。", l_response.errorInfo, l_ex);            
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        
        //execute()メソッドをコールする。
        try
        {
            l_response = (WEB3AdminPvInfoConditionListResponse)l_service.execute(l_request);
        }
        catch(WEB3BaseException l_ex)
        {
            l_response = (WEB3AdminPvInfoConditionListResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request, " execute()メソッドをコールすることが失敗しました。", l_ex);            
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        log.exiting(STR_METHOD_NAME);        
        return l_response;
    }

    /**
     * (update現在状況)<BR>
     * 現在状況更新処理を行う。<BR>
     * <BR>
     * 管理者表示設定一覧サービスImplを取得し、<BR>
     * execute()メソッドをコールする。<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * 管理者・現在状況更新リクエストオブジェクト<BR>
     * @@return webbroker3.pvinfo.message.WEB3AdminPvInfoConditionUpdateResponse
     * @@roseuid 415BED7B01F5
     */
    public WEB3AdminPvInfoConditionUpdateResponse updateCondition(WEB3AdminPvInfoConditionUpdateRequest l_request)
    {
        final String STR_METHOD_NAME = " updateCondition(WEB3AdminPvInfoConditionUpdateRequest)";
        log.entering(STR_METHOD_NAME);
        
        WEB3AdminPvInfoConditionUpdateResponse l_response = null;
        WEB3AdminPvInfoConditionListService l_service = null;
        
        //管理者表示設定一覧サービスを取得し
        try
        {
            l_service = (WEB3AdminPvInfoConditionListService)Services.getService(WEB3AdminPvInfoConditionListService.class);
        }
        catch(Exception l_ex)
        {
            l_response = (WEB3AdminPvInfoConditionUpdateResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(l_request, "管理者表示設定一覧サービスを取得に失敗しました。", l_response.errorInfo,l_ex);
            
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        
        //execute()メソッドをコールする。
        try
        {
            l_response = (WEB3AdminPvInfoConditionUpdateResponse)l_service.execute(l_request);
        }
        catch(WEB3BaseException l_ex)
        {
            l_response = (WEB3AdminPvInfoConditionUpdateResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request, "execute()メソッドをコールすることが失敗しました。", l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
}
@
