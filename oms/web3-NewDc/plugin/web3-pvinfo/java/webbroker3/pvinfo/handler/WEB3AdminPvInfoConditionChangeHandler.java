head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.22.08.09.02;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7444d8857e2568b;
filename	WEB3AdminPvInfoConditionChangeHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者表示設定変更ハンドラ(WEB3AdminPvInfoConditionChangeHandler.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/18 王亞洲(中訊) 新規作成
Revesion History : 2004/10/27 李丁銀(中訊) 作成
*/
package webbroker3.pvinfo.handler;

import com.fitechlabs.xtrade.kernel.handler.MessageHandler;
import com.fitechlabs.xtrade.kernel.boot.Services;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.pvinfo.message.WEB3AdminPvInfoConditionChangeCompleteRequest;
import webbroker3.pvinfo.message.WEB3AdminPvInfoConditionChangeCompleteResponse;
import webbroker3.pvinfo.message.WEB3AdminPvInfoConditionChangeConfirmRequest;
import webbroker3.pvinfo.message.WEB3AdminPvInfoConditionChangeConfirmResponse;
import webbroker3.pvinfo.message.WEB3AdminPvInfoConditionChangeInputRequest;
import webbroker3.pvinfo.message.WEB3AdminPvInfoConditionChangeInputResponse;
import webbroker3.pvinfo.service.delegate.WEB3AdminPvInfoConditionChangeService;
import webbroker3.util.WEB3LogUtility;

/**
 * (管理者表示設定変更ハンドラ)<BR>
 * 管理者表示設定変更ハンドラクラス<BR>
 * @@author 王亞洲
 * @@version 1.00
 */
public class WEB3AdminPvInfoConditionChangeHandler implements MessageHandler
{
    /**
     * ログ出力ユーティリティ。<BR>
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(WEB3AdminPvInfoConditionChangeHandler.class);
    
    /**
     * (get表示設定変更入力画面)<BR>
     * 表示設定変更入力画面表示処理を行う。<BR>
     * <BR>
     * 管理者表示設定変更サービスImplを取得し、<BR>
     * execute()メソッドをコールする。<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * 管理者・表示設定変更入力リクエストオブジェクト<BR>
     * @@return webbroker3.pvinfo.message.WEB3AdminPvInfoConditionChangeInputResponse
     * @@roseuid 415D11E602DF
     */
    public WEB3AdminPvInfoConditionChangeInputResponse getConditionChangeInputScreen(WEB3AdminPvInfoConditionChangeInputRequest l_request)
    {
        final String STR_METHOD_NAME = " getConditionChangeInputScreen(WEB3AdminPvInfoConditionChangeInputRequest)";
        log.entering(STR_METHOD_NAME);
        
        WEB3AdminPvInfoConditionChangeInputResponse l_response = null;
        WEB3AdminPvInfoConditionChangeService l_service = null;
        
        //管理者表示設定変更サービスImplを取得し
        try
        {
            l_service = (WEB3AdminPvInfoConditionChangeService)Services.getService(WEB3AdminPvInfoConditionChangeService.class);
        }
        catch(Exception l_ex)
        {
            l_response = (WEB3AdminPvInfoConditionChangeInputResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(l_request, "管理者表示設定変更サービスImplを取得に失敗しました。", l_response.errorInfo, l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        
        //execute()メソッドをコールする。
        try
        {
            l_response = (WEB3AdminPvInfoConditionChangeInputResponse)l_service.execute(l_request);
        }
        catch(WEB3BaseException l_ex)
        {
            l_response = (WEB3AdminPvInfoConditionChangeInputResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request, "execute()メソッドをコールすることが失敗しました。", l_ex);
            
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (confirm表示設定変更)<BR>
     * 表示設定変更確認処理を行う。<BR>
     * <BR>
     * 管理者表示設定変更サービスImplを取得し、<BR>
     * execute()メソッドをコールする。<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * 管理者・表示設定変更確認リクエストオブジェクト<BR>
     * @@return webbroker3.pvinfo.message.WEB3AdminPvInfoConditionChangeConfirmResponse
     * @@roseuid 415D129203AA
     */
    public WEB3AdminPvInfoConditionChangeConfirmResponse confirmConditionChange(WEB3AdminPvInfoConditionChangeConfirmRequest l_request)
    {
        final String STR_METHOD_NAME = " confirmConditionChange(WEB3AdminPvInfoConditionChangeConfirmRequest)";
        log.entering(STR_METHOD_NAME);
        
        WEB3AdminPvInfoConditionChangeConfirmResponse l_response = null;
        WEB3AdminPvInfoConditionChangeService l_service = null;
        
        //管理者表示設定変更サービスを取得し
        try
        {
            l_service = (WEB3AdminPvInfoConditionChangeService)Services.getService(WEB3AdminPvInfoConditionChangeService.class);
        }
        catch(Exception l_ex)
        {
            l_response = (WEB3AdminPvInfoConditionChangeConfirmResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(l_request, "管理者表示設定変更サービスを取得に失敗しました。", l_response.errorInfo, l_ex);
            
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        
        //execute()メソッドをコールする。
        try
        {
            l_response = (WEB3AdminPvInfoConditionChangeConfirmResponse)l_service.execute(l_request);
        }
        catch(WEB3BaseException l_ex)
        {
            l_response = (WEB3AdminPvInfoConditionChangeConfirmResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request, "execute()メソッドをコールすることが失敗しました。", l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (complete表示設定変更)<BR>
     * 表示設定変更完了処理を行う。<BR>
     * <BR>
     * 管理者表示設定変更サービスImplを取得し、<BR>
     * execute()メソッドをコールする。<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * 管理者・表示設定変更完了リクエストオブジェクト<BR>
     * @@return webbroker3.pvinfo.message.WEB3AdminPvInfoConditionChangeCompleteResponse
     * @@roseuid 415D12E302DF
     */
    public WEB3AdminPvInfoConditionChangeCompleteResponse completeConditionChange(WEB3AdminPvInfoConditionChangeCompleteRequest l_request)
    {
        final String STR_METHOD_NAME = " completeConditionChange(WEB3AdminPvInfoConditionChangeCompleteRequest)";
        log.entering(STR_METHOD_NAME);
        
        WEB3AdminPvInfoConditionChangeCompleteResponse l_response = null;
        WEB3AdminPvInfoConditionChangeService l_service = null;
        
        //管理者表示設定変更サービスを取得し
        try
        {
            l_service = (WEB3AdminPvInfoConditionChangeService)Services.getService(WEB3AdminPvInfoConditionChangeService.class);
        }
        catch(Exception l_ex)
        {
            l_response = (WEB3AdminPvInfoConditionChangeCompleteResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(l_request, "管理者表示設定変更サービスを取得に失敗しました。", l_response.errorInfo, l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        
        //execute()メソッドをコールする。
        try
        {
            l_response = (WEB3AdminPvInfoConditionChangeCompleteResponse)l_service.execute(l_request);
        }
        catch(WEB3BaseException l_ex)
        {
            l_response = (WEB3AdminPvInfoConditionChangeCompleteResponse)l_request.createResponse();
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
