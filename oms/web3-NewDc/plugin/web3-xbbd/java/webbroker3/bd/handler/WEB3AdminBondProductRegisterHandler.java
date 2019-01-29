head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.18;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3AdminBondProductRegisterHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 債券管理者銘柄登録ハンドラ(WEB3AdminBondProductRegisterHandler.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/8/16 周捷(中訊) 新規作成         
*/

package webbroker3.bd.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.util.WEB3LogUtility;
import webbroker3.bd.message.WEB3AdminBondProductRegistCompleteRequest;
import webbroker3.bd.message.WEB3AdminBondProductRegistCompleteResponse;
import webbroker3.bd.message.WEB3AdminBondProductRegistConfirmRequest;
import webbroker3.bd.message.WEB3AdminBondProductRegistConfirmResponse;
import webbroker3.bd.message.WEB3AdminBondProductRegistInputRequest;
import webbroker3.bd.message.WEB3AdminBondProductRegistInputResponse;
import webbroker3.bd.service.delegate.WEB3AdminBondProductRegisterService;

/**
 * (債券管理者銘柄登録ハンドラ)<BR>
 * 債券管理者銘柄登録ハンドラ クラス
 * 
 * @@author 周捷(中訊)
 * @@version 1.0
 */
public class WEB3AdminBondProductRegisterHandler implements MessageHandler 
{
    /**
     * ログユーティリティ
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminBondProductRegisterHandler.class); 
    /**
     * @@roseuid 44E336310000
     */
    public WEB3AdminBondProductRegisterHandler() 
    {
     
    }
    
    /**
     * (銘柄登録入力リクエスト)<BR>
     * 管理者債券銘柄登録入力処理を行う。<BR>
     * <BR>
     * <BR>
     * 債券管理者銘柄登録サービスを取得し、execute()メソッドをコールする。
     * @@param l_request - (リクエストデータ)<BR>
     * 債券銘柄登録入力リクエスト
     * @@return webbroker3.bd.message.WEB3AdminBondProductRegistInputResponse
     * @@roseuid 44B6061E038D
     */
    public WEB3AdminBondProductRegistInputResponse inputProductRegister(
        WEB3AdminBondProductRegistInputRequest l_request) 
    {
        final String STR_METHOD_NAME = 
            "inputProductRegister(WEB3AdminBondProductRegistInputRequest)";
        log.entering(STR_METHOD_NAME);

        //債券管理者銘柄登録サービスを取得し
        WEB3AdminBondProductRegisterService l_service = null;
        WEB3AdminBondProductRegistInputResponse l_response = null;
        try
        {
            l_service =
                (WEB3AdminBondProductRegisterService) 
                Services.getService(WEB3AdminBondProductRegisterService.class);
        }
        // サービスで例外が発生した場合は、エラー情報をレスポンスメッセージに設定する
        catch (Exception l_ex)
        {
            l_response =
                (WEB3AdminBondProductRegistInputResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "債券管理者銘柄登録サービスを取得に失敗しました。",
                l_response.errorInfo,
                l_ex);
            return l_response;
        }
        try
        {
            //execute()メソッドをコールする
            l_response = (WEB3AdminBondProductRegistInputResponse) l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response =
                (WEB3AdminBondProductRegistInputResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request, 
                "債券管理者銘柄登録入力処理が失敗しました。", 
                l_ex.getErrorInfo(), 
                l_ex);
            return l_response;
        }
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * (銘柄登録確認リクエスト)<BR>
     * 管理者債券銘柄登録確認処理を行う。 <BR>
     * <BR>
     * <BR>
     * 債券管理者銘柄登録サービスを取得し、execute()メソッドをコールする。
     * @@param l_request - (リクエストデータ)<BR>
     * 債券銘柄登録確認リクエスト
     * @@return webbroker3.bd.message.WEB3AdminBondProductRegistConfirmResponse
     * @@roseuid 44B6061E038F
     */
    public WEB3AdminBondProductRegistConfirmResponse confirmProductRegister(
        WEB3AdminBondProductRegistConfirmRequest l_request) 
    {
        final String STR_METHOD_NAME = 
            "confirmProductRegister(WEB3AdminBondProductRegistConfirmRequest)";
        log.entering(STR_METHOD_NAME);

        //管理者債券銘柄登録サービスを取得し
        WEB3AdminBondProductRegisterService l_service = null;
        WEB3AdminBondProductRegistConfirmResponse l_response = null;
        try
        {
            l_service =
                (WEB3AdminBondProductRegisterService) 
                Services.getService(WEB3AdminBondProductRegisterService.class);
        }
        // サービスで例外が発生した場合は、エラー情報をレスポンスメッセージに設定する
        catch (Exception l_ex)
        {
            l_response =
                (WEB3AdminBondProductRegistConfirmResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "管理者債券銘柄登録サービスを取得に失敗しました。",
                l_response.errorInfo,
                l_ex);
            return l_response;
        }
        try
        {
            //execute()メソッドをコールする
            l_response = (WEB3AdminBondProductRegistConfirmResponse) l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response =
                (WEB3AdminBondProductRegistConfirmResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request, 
                "管理者債券銘柄登録確認処理が失敗しました。", 
                l_ex.getErrorInfo(), 
                l_ex);
            return l_response;
        }
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * (銘柄登録完了リクエスト)<BR>
     * 管理者債券銘柄登録完了処理を行う。 <BR>
     * <BR>
     * 債券管理者銘柄登録サービスを取得し、execute()メソッドをコールする。
     * @@param l_request - 銘柄登録完了リクエスト
     * @@return webbroker3.bd.message.WEB3AdminBondProductRegistCompleteResponse
     * @@roseuid 44BADCAB003E
     */
    public WEB3AdminBondProductRegistCompleteResponse completeProductRegister(
        WEB3AdminBondProductRegistCompleteRequest l_request) 
    {
        final String STR_METHOD_NAME = 
            "completeProductRegister(WEB3AdminBondProductRegistCompleteRequest)";
        log.entering(STR_METHOD_NAME);

        //管理者債券銘柄登録サービスを取得し
        WEB3AdminBondProductRegisterService l_service = null;
        WEB3AdminBondProductRegistCompleteResponse l_response = null;
        try
        {
            l_service =
                (WEB3AdminBondProductRegisterService) 
                Services.getService(WEB3AdminBondProductRegisterService.class);
        }
        // サービスで例外が発生した場合は、エラー情報をレスポンスメッセージに設定する
        catch (Exception l_ex)
        {
            l_response =
                (WEB3AdminBondProductRegistCompleteResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "管理者債券銘柄登録サービスを取得に失敗しました。",
                l_response.errorInfo,
                l_ex);
            return l_response;
        }
        try
        {
            //execute()メソッドをコールする
            l_response = (WEB3AdminBondProductRegistCompleteResponse) l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response =
                (WEB3AdminBondProductRegistCompleteResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request, 
                "管理者債券銘柄登録完了処理が失敗しました。", 
                l_ex.getErrorInfo(), 
                l_ex);
            return l_response;
        }
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
}
@
