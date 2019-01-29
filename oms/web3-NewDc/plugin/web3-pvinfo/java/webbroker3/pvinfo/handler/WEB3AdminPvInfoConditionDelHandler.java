head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.22.08.09.27;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7444d8857e2568b;
filename	WEB3AdminPvInfoConditionDelHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者表示設定削除ハンドラ(WEB3AdminPvInfoConditionDelHandler.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/18 王亞洲(中訊) 新規作成
Revesion History : 2004/10/27 李丁銀(中訊) 作成
*/
package webbroker3.pvinfo.handler;

import com.fitechlabs.xtrade.kernel.handler.MessageHandler;
import com.fitechlabs.xtrade.kernel.boot.Services;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.pvinfo.message.WEB3AdminPvInfoConditionDelCompleteRequest;
import webbroker3.pvinfo.message.WEB3AdminPvInfoConditionDelCompleteResponse;
import webbroker3.pvinfo.message.WEB3AdminPvInfoConditionDelConfirmRequest;
import webbroker3.pvinfo.message.WEB3AdminPvInfoConditionDelConfirmResponse;
import webbroker3.pvinfo.service.delegate.WEB3AdminPvInfoConditionDelService;
import webbroker3.util.WEB3LogUtility;

/**
 * (管理者表示設定削除ハンドラ)<BR>
 * 管理者表示設定削除ハンドラクラス<BR>
 * @@author 王亞洲
 * @@version 1.00
 */
public class WEB3AdminPvInfoConditionDelHandler implements MessageHandler
{
    /**
     * ログ出力ユーティリティ。<BR>
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(WEB3AdminPvInfoConditionDelHandler.class);
    
    /**
     * (confirm表示設定削除)<BR>
     * 表示設定削除確認処理を行う。<BR>
     * <BR>
     * 管理者表示設定削除サービスImplを取得し、<BR>
     * execute()メソッドをコールする。<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * 管理者・表示設定削除確認リクエストオブジェクト<BR>
     * @@return webbroker3.pvinfo.message.WEB3AdminPvInfoConditionDelConfirmResponse
     * @@roseuid 415D299F006E
     */
    public WEB3AdminPvInfoConditionDelConfirmResponse confirmConditionDel(WEB3AdminPvInfoConditionDelConfirmRequest l_request)
    {
        final String STR_METHOD_NAME = " confirmConditionDel(WEB3AdminPvInfoConditionDelConfirmRequest)";
        log.entering(STR_METHOD_NAME);
        
        WEB3AdminPvInfoConditionDelConfirmResponse l_response = null;
        WEB3AdminPvInfoConditionDelService l_service = null;
        
        //管理者表示設定削除サービスを取得し
        try
        {
            l_service = (WEB3AdminPvInfoConditionDelService)Services.getService(WEB3AdminPvInfoConditionDelService.class);           
        }
        catch(Exception l_ex)
        {
            l_response = (WEB3AdminPvInfoConditionDelConfirmResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(l_request, "管理者表示設定削除サービスを取得に失敗しました。", l_response.errorInfo, l_ex);
            
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        //execute()メソッドをコールする。
        try
        {
            l_response = (WEB3AdminPvInfoConditionDelConfirmResponse)l_service.execute(l_request);
        }
        catch(WEB3BaseException l_ex)
        {
            l_response = (WEB3AdminPvInfoConditionDelConfirmResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request, "execute()メソッドをコールすることが失敗しました。", l_ex);
            
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (complete表示設定削除)<BR>
     * 表示設定削除完了処理を行う。<BR>
     * <BR>
     * 管理者表示設定削除サービスImplを取得し、<BR>
     * execute()メソッドをコールする。<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * 管理者・表示設定削除完了リクエストオブジェクト<BR>
     * @@return webbroker3.pvinfo.message.WEB3AdminPvInfoConditionDelCompleteResponse
     * @@roseuid 415D29EF0252
     */
    public WEB3AdminPvInfoConditionDelCompleteResponse completeConditionDel(WEB3AdminPvInfoConditionDelCompleteRequest l_request)
    {
        final String STR_METHOD_NAME = " completeConditionDel(WEB3AdminPvInfoConditionDelCompleteRequest)";
        log.entering(STR_METHOD_NAME);
        
        WEB3AdminPvInfoConditionDelCompleteResponse l_response = null;
        WEB3AdminPvInfoConditionDelService l_service = null;
        
        //管理者表示設定削除サービスを取得し、
        try
        {
            l_service = (WEB3AdminPvInfoConditionDelService)Services.getService(WEB3AdminPvInfoConditionDelService.class);
        }
        catch(Exception l_ex)
        {
            l_response = (WEB3AdminPvInfoConditionDelCompleteResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(l_request, "管理者表示設定削除サービスを取得に失敗しました。", l_response.errorInfo, l_ex);
            
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        
        //execute()メソッドをコールする。
        try
        {
            l_response = (WEB3AdminPvInfoConditionDelCompleteResponse)l_service.execute(l_request);
        }
        catch(WEB3BaseException l_ex)
        {
            l_response = (WEB3AdminPvInfoConditionDelCompleteResponse)l_request.createResponse();
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
