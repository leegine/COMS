head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.16;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3AdminDirSecAPMngForcedStartHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者下り処理強制起動ハンドラ(WEB3AdminDirSecAPMngForcedStartHandler.java)
Author Name      : Daiwa Institute of Research
Revision History : 2008/07/21 楊夫志(中訊) 新規作成モデル 132
*/

package webbroker3.dirsec.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.dirsec.message.WEB3AdminDirSecAPMngForcedStartCmpRequest;
import webbroker3.dirsec.message.WEB3AdminDirSecAPMngForcedStartCmpResponse;
import webbroker3.dirsec.message.WEB3AdminDirSecAPMngForcedStartCnfRequest;
import webbroker3.dirsec.message.WEB3AdminDirSecAPMngForcedStartCnfResponse;
import webbroker3.dirsec.message.WEB3AdminDirSecAPMngForcedStartInpRequest;
import webbroker3.dirsec.message.WEB3AdminDirSecAPMngForcedStartInpResponse;
import webbroker3.dirsec.message.WEB3AdminDirSecAPMngListRequest;
import webbroker3.dirsec.message.WEB3AdminDirSecAPMngListResponse;
import webbroker3.dirsec.service.delegate.WEB3AdminDirSecAPMngForcedStartService;
import webbroker3.util.WEB3LogUtility;

/**
 * (管理者下り処理強制起動ハンドラ)<BR>
 * 管理者下り処理強制起動ハンドラクラス。<BR>
 * <BR>
 * @@author 楊夫志
 * @@version 1.0
 */
public class WEB3AdminDirSecAPMngForcedStartHandler implements MessageHandler
{
    /**
     * ログ出力ユーティリティ。<BR>
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(
        WEB3AdminDirSecAPMngForcedStartHandler.class);

    /**
     * @@roseuid 488439E900E2
     */
    public WEB3AdminDirSecAPMngForcedStartHandler() 
    {

    }

    /**
     * (get下り処理一覧)<BR>
     * 管理者下り処理一覧画面表示処理を行う。 <BR>
     * <BR>
     * 管理者下り処理強制起動サービスImplを取得し、 <BR>
     * execute()メソッドをコールする。<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * 管理者・下り処理一覧リクエストクラス。<BR>
     * @@return WEB3AdminDirSecAPMngListResponse
     * @@roseuid 487599280216
     */
    public WEB3AdminDirSecAPMngListResponse getAPMngList(WEB3AdminDirSecAPMngListRequest l_request) 
    {
        final String STR_METHOD_NAME = "getAPMngList(WEB3AdminDirSecAPMngListRequest)";
        log.entering(STR_METHOD_NAME);

        WEB3AdminDirSecAPMngListResponse l_response = null;
        WEB3AdminDirSecAPMngForcedStartService l_service = null;

        //管理者下り処理強制起動サービスImplを取得
        try
        {
            l_service = (WEB3AdminDirSecAPMngForcedStartService)Services.getService(
                WEB3AdminDirSecAPMngForcedStartService.class);
        }
        catch(Exception l_ex)
        {
            l_response = (WEB3AdminDirSecAPMngListResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "管理者下り処理強制起動サービスImplを取得に失敗しました。",
                l_response.errorInfo,
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

        try
        {
            l_response =
                (WEB3AdminDirSecAPMngListResponse)l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response = (WEB3AdminDirSecAPMngListResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request,
                "管理者下り処理一覧画面表示処理に失敗しました。",
                l_response.errorInfo,
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        catch (WEB3BaseRuntimeException l_ex)
        {
            l_response = (WEB3AdminDirSecAPMngListResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request,
                "管理者下り処理一覧画面表示処理に失敗しました。",
                l_response.errorInfo,
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * (get下り処理強制起動入力)<BR>
     * 下り処理強制起動入力画面表示を行う。 <BR>
     * <BR>
     * 管理者下り処理強制起動サービスImplを取得し、 <BR>
     * execute()メソッドをコールする。<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * 管理者・下り処理強制起動入力リクエストクラス。<BR>
     * @@return WEB3AdminDirSecAPMngForcedStartInpResponse
     * @@roseuid 487C46D90136
     */
    public WEB3AdminDirSecAPMngForcedStartInpResponse getAPMngForcedStartInp(
        WEB3AdminDirSecAPMngForcedStartInpRequest l_request) 
    {
        final String STR_METHOD_NAME = "getAPMngForcedStartInp(WEB3AdminDirSecAPMngForcedStartInpRequest)";
        log.entering(STR_METHOD_NAME);

        WEB3AdminDirSecAPMngForcedStartInpResponse l_response = null;
        WEB3AdminDirSecAPMngForcedStartService l_service = null;

        //管理者下り処理強制起動サービスImplを取得
        try
        {
            l_service = (WEB3AdminDirSecAPMngForcedStartService)Services.getService(
                WEB3AdminDirSecAPMngForcedStartService.class);
        }
        catch(Exception l_ex)
        {
            l_response = (WEB3AdminDirSecAPMngForcedStartInpResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "管理者下り処理強制起動サービスImplを取得に失敗しました。",
                l_response.errorInfo,
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

        try
        {
            l_response =
                (WEB3AdminDirSecAPMngForcedStartInpResponse)l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response = (WEB3AdminDirSecAPMngForcedStartInpResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request,
                "下り処理強制起動入力画面表示に失敗しました。",
                l_response.errorInfo,
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        catch (WEB3BaseRuntimeException l_ex)
        {
            l_response = (WEB3AdminDirSecAPMngForcedStartInpResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request,
                "下り処理強制起動入力画面表示に失敗しました。",
                l_response.errorInfo,
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * (validate下り処理強制起動確認)<BR>
     * 下り処理強制起動確認画面表示を行う。 <BR>
     * <BR>
     * 管理者下り処理強制起動サービスImplを取得し、 <BR>
     * execute()メソッドをコールする。<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * 管理者・下り処理強制起動確認リクエストクラス。<BR>
     * @@return WEB3AdminDirSecAPMngForcedStartCnfResponse
     * @@roseuid 487599280218
     */
    public WEB3AdminDirSecAPMngForcedStartCnfResponse validateAPMngForcedStartCnf(
        WEB3AdminDirSecAPMngForcedStartCnfRequest l_request) 
    {
        final String STR_METHOD_NAME = "validateAPMngForcedStartCnf(WEB3AdminDirSecAPMngForcedStartCnfRequest)";
        log.entering(STR_METHOD_NAME);

        WEB3AdminDirSecAPMngForcedStartCnfResponse l_response = null;
        WEB3AdminDirSecAPMngForcedStartService l_service = null;

        //管理者下り処理強制起動サービスImplを取得
        try
        {
            l_service = (WEB3AdminDirSecAPMngForcedStartService)Services.getService(
                WEB3AdminDirSecAPMngForcedStartService.class);
        }
        catch(Exception l_ex)
        {
            l_response = (WEB3AdminDirSecAPMngForcedStartCnfResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "管理者下り処理強制起動サービスImplを取得に失敗しました。",
                l_response.errorInfo,
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

        try
        {
            l_response =
                (WEB3AdminDirSecAPMngForcedStartCnfResponse)l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response = (WEB3AdminDirSecAPMngForcedStartCnfResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request,
                "下り処理強制起動確認画面表示に失敗しました。",
                l_response.errorInfo,
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        catch (WEB3BaseRuntimeException l_ex)
        {
            l_response = (WEB3AdminDirSecAPMngForcedStartCnfResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request,
                "下り処理強制起動確認画面表示に失敗しました。",
                l_response.errorInfo,
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (submit下り処理強制起動完了)<BR>
     * 管理者下り処理強制起動完了画面表示処理を行う。 <BR>
     * <BR>
     * 管理者下り処理強制起動サービスImplを取得し、 <BR>
     * execute()メソッドをコールする。<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * 管理者・下り処理強制起動完了リクエストクラス。<BR>
     * @@return WEB3AdminDirSecAPMngForcedStartCmpResponse
     * @@roseuid 48759928021A
     */
    public WEB3AdminDirSecAPMngForcedStartCmpResponse submitAPMngForcedStartCmp(
        WEB3AdminDirSecAPMngForcedStartCmpRequest l_request) 
    {
        final String STR_METHOD_NAME = "submitAPMngForcedStartCmp(WEB3AdminDirSecAPMngForcedStartCmpRequest)";
        log.entering(STR_METHOD_NAME);

        WEB3AdminDirSecAPMngForcedStartCmpResponse l_response = null;
        WEB3AdminDirSecAPMngForcedStartService l_service = null;

        //管理者下り処理強制起動サービスImplを取得
        try
        {
            l_service = (WEB3AdminDirSecAPMngForcedStartService)Services.getService(
                WEB3AdminDirSecAPMngForcedStartService.class);
        }
        catch(Exception l_ex)
        {
            l_response = (WEB3AdminDirSecAPMngForcedStartCmpResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "管理者下り処理強制起動サービスImplを取得に失敗しました。",
                l_response.errorInfo,
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

        try
        {
            l_response =
                (WEB3AdminDirSecAPMngForcedStartCmpResponse)l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response = (WEB3AdminDirSecAPMngForcedStartCmpResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request,
                "管理者下り処理強制起動完了画面表示処理に失敗しました。",
                l_response.errorInfo,
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        catch (WEB3BaseRuntimeException l_ex)
        {
            l_response = (WEB3AdminDirSecAPMngForcedStartCmpResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request,
                "管理者下り処理強制起動完了画面表示処理に失敗しました。",
                l_response.errorInfo,
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
}
@
