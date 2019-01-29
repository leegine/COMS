head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.06.45.45;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7984d7db5d91d86;
filename	WEB3AdminSrvRegiServiceChangeHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : サービス利用管理者サービス変更ハンドラ(WEB3AdminSrvRegiServiceChangeHandler.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/22 郭英 (中訊) 新規作成
*/

package webbroker3.srvregi.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.srvregi.message.WEB3AdminSrvRegiServiceChangeCompleteRequest;
import webbroker3.srvregi.message.WEB3AdminSrvRegiServiceChangeCompleteResponse;
import webbroker3.srvregi.message.WEB3AdminSrvRegiServiceChangeConfirmRequest;
import webbroker3.srvregi.message.WEB3AdminSrvRegiServiceChangeConfirmResponse;
import webbroker3.srvregi.service.delegate.WEB3AdminSrvRegiServiceChangeService;
import webbroker3.util.WEB3LogUtility;

/**
 * (サービス利用管理者サービス変更ハンドラ)<BR>
 * サービス利用管理者サービス変更ハンドラクラス<BR>
 * 
 * @@author 郭英
 * @@version 1.0
 */
public class WEB3AdminSrvRegiServiceChangeHandler implements MessageHandler 
{
    
    /**
     * ログ出力ユーティリティ。<BR>
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(WEB3AdminSrvRegiServiceChangeHandler.class);
    
    /**
     * @@roseuid 416F415B02FD
     */
    public WEB3AdminSrvRegiServiceChangeHandler() 
    {
     
    }
    
    /**
     * (confirmサービス変更)<BR>
     * サービス利用管理者サービス変更審査処理を行う。<BR>
     * <BR>
     * サービス利用管理者サービス変更サービスを取得し、execute( )メソッドをコールする。<BR>
     * @@roseuid 40F5140600E3
     */
    public WEB3AdminSrvRegiServiceChangeConfirmResponse confirmSrvChange(WEB3AdminSrvRegiServiceChangeConfirmRequest l_request) 
    {
        final String STR_METHOD_NAME = " confirmSrvChange(WEB3AdminSrvRegiServiceChangeConfirmRequest) ";
        log.entering(STR_METHOD_NAME);
        
        WEB3AdminSrvRegiServiceChangeConfirmResponse l_response = null;
        WEB3AdminSrvRegiServiceChangeService l_service = null;
        
        try
        {
            l_service = (WEB3AdminSrvRegiServiceChangeService)Services.getService(WEB3AdminSrvRegiServiceChangeService.class);//Exception
        }
        // サービスで例外が発生した場合は、エラー情報をレスポンスメッセージに設定する
        catch (Exception l_ex)
        {
            l_response = (WEB3AdminSrvRegiServiceChangeConfirmResponse) l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;    
            log.error(
                l_request, 
                " サービス利用管理者サービス変更審査に失敗しました。",
                l_response.errorInfo, 
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        
        //サービス利用管理者サービス変更サービス.execute()メソッドをコールする
        try
        {
            l_response = (WEB3AdminSrvRegiServiceChangeConfirmResponse) l_service.execute(l_request);//WEB3BaseException
        }
        catch (WEB3BaseException l_ex)
        {
            l_response = (WEB3AdminSrvRegiServiceChangeConfirmResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request, " サービス利用管理者サービス変更審査に失敗しました。", l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        
        log.exiting(STR_METHOD_NAME);
        
        return l_response;
    }
    
    /**
     * (completeサービス変更)<BR>
     * サービス利用管理者サービス変更処理を行う。<BR>
     * <BR>
     * サービス利用管理者サービス変更サービスを取得し、execute( )<BR>
     * メソッドをコールする。<BR>
     * @@roseuid 40F5140F00E3
     */
    public WEB3AdminSrvRegiServiceChangeCompleteResponse completeSrvChange(WEB3AdminSrvRegiServiceChangeCompleteRequest l_request) 
    {
        final String STR_METHOD_NAME = " completeSrvChange(WEB3AdminSrvRegiServiceChangeCompleteRequest) ";
        log.entering(STR_METHOD_NAME);
        
        WEB3AdminSrvRegiServiceChangeCompleteResponse l_response = null;
        WEB3AdminSrvRegiServiceChangeService l_service = null;
        
        try
        {
            l_service = (WEB3AdminSrvRegiServiceChangeService)Services.getService(WEB3AdminSrvRegiServiceChangeService.class);//Exception
        }
        // サービスで例外が発生した場合は、エラー情報をレスポンスメッセージに設定する
        catch (Exception l_ex)
        {
            l_response = (WEB3AdminSrvRegiServiceChangeCompleteResponse) l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;    
            log.error(
                l_request, 
                " サービス利用管理者サービス変更に失敗しました。",
                l_response.errorInfo, 
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        
        //サービス利用管理者サービス変更サービス.execute()メソッドをコールする
        try
        {
            l_response = (WEB3AdminSrvRegiServiceChangeCompleteResponse) l_service.execute(l_request);//WEB3BaseException
        }
        catch (WEB3BaseException l_ex)
        {
            l_response = (WEB3AdminSrvRegiServiceChangeCompleteResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request, " サービス利用管理者サービス変更に失敗しました。", l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        
        log.exiting(STR_METHOD_NAME);
                
        return l_response; 
    }
}
@
