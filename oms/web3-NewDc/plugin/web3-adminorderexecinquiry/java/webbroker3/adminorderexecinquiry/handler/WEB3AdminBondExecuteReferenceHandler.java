head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.03.42.11;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	WEB3AdminBondExecuteReferenceHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 債券管理者注文約定照会ハンドラ(WEB3AdminBondExecuteReferenceHandler)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/08/10 何文敏(中訊) 新規作成  
*/

package webbroker3.adminorderexecinquiry.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.adminorderexecinquiry.message.WEB3AdminORBondExecRefInputRequest;
import webbroker3.adminorderexecinquiry.message.WEB3AdminORBondExecRefInputResponse;
import webbroker3.adminorderexecinquiry.message.WEB3AdminORBondExecRefReferenceRequest;
import webbroker3.adminorderexecinquiry.message.WEB3AdminORBondExecRefReferenceResponse;
import webbroker3.adminorderexecinquiry.service.delegate.WEB3AdminBondExecuteReferenceService;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.util.WEB3LogUtility;

/**
 * (債券管理者注文約定照会ハンドラ)<BR>
 * 債券管理者注文約定照会ハンドラクラス<BR>
 * 
 * @@author 何文敏(中訊)
 * @@version 1.0
 */
public class WEB3AdminBondExecuteReferenceHandler implements MessageHandler
{
    
    private static WEB3LogUtility log = 
        WEB3LogUtility.getInstance(WEB3AdminBondExecuteReferenceHandler.class);
    
    /**
     * (債券管理者注文約定照会条件表示リクエスト)<BR>
     * 債券管理者注文約定照会条件表示を行う。<BR>
     * <BR>
     * 債券管理者注文約定照会サービスを取得し、execute()メソッド
     * をコールする。
     * @@param l_request - (リクエスト)<BR>
     * 債券管理者注文約定照会条件表示リクエスト
     * @@return 
     * webbroker3.adminorderexecinquiry.message.WEB3AdminORBondExecRefReferenceResponse 
     * @@roseuid 44B74C6001D0
     */
    public WEB3AdminORBondExecRefReferenceResponse getReferenceScreen
        (WEB3AdminORBondExecRefReferenceRequest l_request)
    {
        String STR_METHOD_NAME = 
            "getReferenceScreen(WEB3AdminORBondExecReferrenceRequest l_request)";
        log.entering(STR_METHOD_NAME);
        
        WEB3AdminBondExecuteReferenceService l_service = null;
        WEB3AdminORBondExecRefReferenceResponse l_response = null;
        
        try
        {
            //債券管理者注文約定照会サービスを取得し
            l_service = 
                (WEB3AdminBondExecuteReferenceService)Services.getService(
                        WEB3AdminBondExecuteReferenceService.class);
        }
        //サービスで例外が発生した場合は、エラー情報をレスポンスメッセージに設定する
        catch (Exception l_ex)
        {
            l_response = 
                (WEB3AdminORBondExecRefReferenceResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request, 
                "__債券管理者注文約定照会サービスを取得に失敗しました__",
                l_response.errorInfo,
                l_ex);
            return l_response;
        }
        
        try
        {
            //execute()メソッドをコールする
            l_response = (WEB3AdminORBondExecRefReferenceResponse)l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response =
                (WEB3AdminORBondExecRefReferenceResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request,
                "__error in call (債券管理者注文約定照会サービス.execute()__",
                l_ex);
            return l_response;
        }
        
        log.exiting(STR_METHOD_NAME);
        
        return l_response;     
        
    }
    
    /**
     * (債券管理者注文約定照会検索表示リクエスト)<BR>
     * 債券管理者注文約定照会検索表示を行う。<BR>
     * <BR>
     * 債券管理者注文約定照会サービスを取得し、execute()メソッド
     * をコールする。
     * @@param l_request - (リクエスト)<BR>
     * 債券管理者注文約定照会検索表示リクエスト
     * @@return webbroker3.adminorderexecinquiry.message.WEB3AdminORBondExecRefInputResponse
     * @@roseuid 44B74C74000B
     */
    public WEB3AdminORBondExecRefInputResponse getInputScreen
        (WEB3AdminORBondExecRefInputRequest l_request) 
    {
        String STR_METHOD_NAME = 
            "getInputScreen(WEB3AdminORBondExecRefInputRequest l_request)";
        log.entering(STR_METHOD_NAME);
        
        WEB3AdminBondExecuteReferenceService l_service = null;
        WEB3AdminORBondExecRefInputResponse l_response = null;
        
        try
        {
            //債券管理者注文約定照会サービスを取得し
            l_service = 
                (WEB3AdminBondExecuteReferenceService)Services.getService(
                        WEB3AdminBondExecuteReferenceService.class);
        }
        //サービスで例外が発生した場合は、エラー情報をレスポンスメッセージに設定する
        catch (Exception l_ex)
        {
            l_response = 
                (WEB3AdminORBondExecRefInputResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request, 
                "__債券管理者注文約定照会サービスを取得に失敗しました__",
                l_response.errorInfo,
                l_ex);
            return l_response;
        }
        
        try
        {
            //execute()メソッドをコールする
            l_response = (WEB3AdminORBondExecRefInputResponse)l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response =
                (WEB3AdminORBondExecRefInputResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request,
                "__error in call (債券管理者注文約定照会サービス.execute()__",
                l_ex);
            return l_response;
        }
        
        log.exiting(STR_METHOD_NAME);
        
        return l_response;     
    }
}
@
