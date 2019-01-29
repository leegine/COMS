head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.05.48.45;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6f44d7efa104045;
filename	WEB3AdminIpoLotResultUploadHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者IPO抽選結果ｱｯﾌﾟﾛｰﾄﾞﾊﾝﾄﾞﾗクラス(WEB3AdminIpoLotResultUploadHandler.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/08/24 李海波 (中訊) 新規作成
*/

package webbroker3.ipo.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.ipo.message.WEB3AdminIPOLotResultUploadCancelRequest;
import webbroker3.ipo.message.WEB3AdminIPOLotResultUploadCancelResponse;
import webbroker3.ipo.message.WEB3AdminIPOLotResultUploadCompleteRequest;
import webbroker3.ipo.message.WEB3AdminIPOLotResultUploadCompleteResponse;
import webbroker3.ipo.message.WEB3AdminIPOLotResultUploadConfirmRequest;
import webbroker3.ipo.message.WEB3AdminIPOLotResultUploadConfirmResponse;
import webbroker3.ipo.message.WEB3AdminIPOLotResultUploadInputRequest;
import webbroker3.ipo.message.WEB3AdminIPOLotResultUploadInputResponse;
import webbroker3.ipo.service.delegate.WEB3AdminIpoLotResultUploadService;
import webbroker3.util.WEB3LogUtility;

/**
 * 管理者IPO抽選結果ｱｯﾌﾟﾛｰﾄﾞﾊﾝﾄﾞﾗクラス
 * 
 * @@author 李海波
 * @@version 1.0
 */
public class WEB3AdminIpoLotResultUploadHandler implements MessageHandler 
{
    
    /**
     * ログ出力ユーティリティ。
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminIpoLotResultUploadHandler.class);
    
    /**
     * @@roseuid 4112EEA7017C
     */
    public WEB3AdminIpoLotResultUploadHandler() 
    {
     
    }
    
    /**
     * IPO管理者IPO抽選結果アップロード画面表示処理を行う。<BR>
     * <BR>
     * 管理者IPO抽選結果アップロードサービスを取得し、execute()メソッドをコールする。
     * @@param l_request - 管理者IPO抽選結果ｱｯﾌﾟﾛｰﾄﾞ入力ﾘｸｴｽﾄデータオブジェクト
     * @@return webbroker3.ipo.message.WEB3AdminIpoLotResultUploadInputResponse
     * @@roseuid 40E1478C0131
     */
    public WEB3AdminIPOLotResultUploadInputResponse lotResultUploadScreenIndication(WEB3AdminIPOLotResultUploadInputRequest l_request) 
    {

        final String STR_METHOD_NAME = " lotResultUploadScreenIndication(WEB3AdminIpoLotResultUploadInputRequest)";    
        log.entering(STR_METHOD_NAME);
        
        WEB3AdminIPOLotResultUploadInputResponse l_response = null;
        WEB3AdminIpoLotResultUploadService l_service = null;
        
        try
        {
            l_service =
                (WEB3AdminIpoLotResultUploadService)Services.getService(
                    WEB3AdminIpoLotResultUploadService.class);
        }
        catch (Exception l_ex)
        {
            
            l_response = (WEB3AdminIPOLotResultUploadInputResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "管理者IPO抽選結果ｱｯﾌﾟﾛｰﾄﾞｻｰﾋﾞｽサービスの取得に失敗しました。",
                l_response.errorInfo,
                l_ex);
            return l_response;   
               
        }
        
        //管理者IPO抽選結果ｱｯﾌﾟﾛｰﾄﾞｻｰﾋﾞｽサービス.execute()メソッドをコールする
        try
        {
            
            l_response = (WEB3AdminIPOLotResultUploadInputResponse)l_service.execute(l_request);
            
        }
        catch (WEB3BaseException l_ex)
        {
            l_response = (WEB3AdminIPOLotResultUploadInputResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            l_response.errorMessage = l_ex.getErrorMessage();
            log.error(l_request, "管理者IPO抽選結果ｱｯﾌﾟﾛｰﾄﾞｻｰﾋﾞｽに失敗しました。", l_ex);
            return l_response;
        }
        log.exiting(STR_METHOD_NAME);
 
        //レスポンスオブジェクトを返却する。
        return l_response;

    }
    
    /**
     * (抽選結果アップロードファ@イル確認)<BR>
     * IPO管理者・抽選結果アップロード確認処理を行う。<BR>
     * <BR>
     * 管理者IPO抽選結果アップロードサービスを取得し、execute()メソッドをコールする。
     * @@param l_request - 管理者IPO抽選結果ｱｯﾌﾟﾛｰﾄﾞ確認ﾘｸｴｽﾄデータオブジェクト
     * @@return webbroker3.ipo.message.WEB3AdminIpoLotResultUploadConfirmResponse
     * @@roseuid 40E1478C0141
     */
    public WEB3AdminIPOLotResultUploadConfirmResponse lotResultUploadFileConfirm(WEB3AdminIPOLotResultUploadConfirmRequest l_request) 
    {

        final String STR_METHOD_NAME = " lotResultUploadFileConfirm(WEB3AdminIpoLotResultUploadConfirmRequest)";    
        log.entering(STR_METHOD_NAME);
        
        WEB3AdminIPOLotResultUploadConfirmResponse l_response = null;
        WEB3AdminIpoLotResultUploadService l_service = null;
        
        try
        {
            l_service =
                (WEB3AdminIpoLotResultUploadService)Services.getService(
                    WEB3AdminIpoLotResultUploadService.class);
        }
        catch (Exception l_ex)
        {
            
            l_response = (WEB3AdminIPOLotResultUploadConfirmResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "管理者IPO抽選結果ｱｯﾌﾟﾛｰﾄﾞｻｰﾋﾞｽサービスの取得に失敗しました。",
                l_response.errorInfo,
                l_ex);
            return l_response;   
               
        }
        
        //管理者IPO抽選結果ｱｯﾌﾟﾛｰﾄﾞｻｰﾋﾞｽサービス.execute()メソッドをコールする
        try
        {
            
            l_response = (WEB3AdminIPOLotResultUploadConfirmResponse)l_service.execute(l_request);
            
        }
        catch (WEB3BaseException l_ex)
        {
            l_response = (WEB3AdminIPOLotResultUploadConfirmResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            l_response.errorMessage = l_ex.getErrorMessage();
            log.error(l_request, "管理者IPO抽選結果ｱｯﾌﾟﾛｰﾄﾞｻｰﾋﾞｽに失敗しました。", l_ex);
            return l_response;
        }
        log.exiting(STR_METHOD_NAME);
 
        //レスポンスオブジェクトを返却する。
        return l_response;

    }
    
    /**
     * (抽選結果アップロード)<BR>
     * IPO管理者・抽選結果アップロード完了処理を行う。<BR>
     * <BR>
     * 管理者IPO抽選結果アップロードサービスを取得し、execute()メソッドをコールする。
     * @@param l_request - 管理者IPO抽選結果ｱｯﾌﾟﾛｰﾄﾞ完了ﾘｸｴｽﾄデータオブジェクト
     * @@return webbroker3.ipo.message.WEB3AdminIpoLotResultUploadCompleteResponse
     * @@roseuid 40E1478C0150
     */
    public WEB3AdminIPOLotResultUploadCompleteResponse lotResultUpload(WEB3AdminIPOLotResultUploadCompleteRequest l_request) 
    {

        final String STR_METHOD_NAME = " lotResultUpload(WEB3AdminIpoLotResultUploadCompleteRequest)";    
        log.entering(STR_METHOD_NAME);
        
        WEB3AdminIPOLotResultUploadCompleteResponse l_response = null;
        WEB3AdminIpoLotResultUploadService l_service = null;
        
        try
        {
            l_service =
                (WEB3AdminIpoLotResultUploadService)Services.getService(
                    WEB3AdminIpoLotResultUploadService.class);
        }
        catch (Exception l_ex)
        {
            
            l_response = (WEB3AdminIPOLotResultUploadCompleteResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "管理者IPO抽選結果ｱｯﾌﾟﾛｰﾄﾞｻｰﾋﾞｽサービスの取得に失敗しました。",
                l_response.errorInfo,
                l_ex);
            return l_response;   
               
        }
        
        //管理者IPO抽選結果ｱｯﾌﾟﾛｰﾄﾞｻｰﾋﾞｽサービス.execute()メソッドをコールする
        try
        {
            
            l_response = (WEB3AdminIPOLotResultUploadCompleteResponse)l_service.execute(l_request);
            
        }
        catch (WEB3BaseException l_ex)
        {
            l_response = (WEB3AdminIPOLotResultUploadCompleteResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            l_response.errorMessage = l_ex.getErrorMessage();
            log.error(l_request, "管理者IPO抽選結果ｱｯﾌﾟﾛｰﾄﾞｻｰﾋﾞｽに失敗しました。", l_ex);
            return l_response;
        }
        log.exiting(STR_METHOD_NAME);
 
        //レスポンスオブジェクトを返却する。
        return l_response;

    }
    
    /**
     * (抽選結果アップロード中止)<BR>
     * IPO管理者・抽選結果アップロード中止処理を行う。<BR>
     * <BR>
     * 管理者IPO抽選結果アップロードサービスを取得し、execute()メソッドをコールする。
     * @@param l_request - 管理者IPO抽選結果ｱｯﾌﾟﾛｰﾄﾞ中止ﾘｸｴｽﾄデータオブジェクト
     * @@return webbroker3.ipo.message.WEB3AdminIpoLotResultUploadCancelResponse
     * @@roseuid 40F77BF4023B
     */
    public WEB3AdminIPOLotResultUploadCancelResponse lotResultUploadCancel(WEB3AdminIPOLotResultUploadCancelRequest l_request) 
    {

        final String STR_METHOD_NAME = " lotResultUploadCancel(WEB3AdminIpoLotResultUploadCancelRequest)";    
        log.entering(STR_METHOD_NAME);
        
        WEB3AdminIPOLotResultUploadCancelResponse l_response = null;
        WEB3AdminIpoLotResultUploadService l_service = null;
        
        try
        {
            l_service =
                (WEB3AdminIpoLotResultUploadService)Services.getService(
                    WEB3AdminIpoLotResultUploadService.class);
        }
        catch (Exception l_ex)
        {
            
            l_response = (WEB3AdminIPOLotResultUploadCancelResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "管理者IPO抽選結果ｱｯﾌﾟﾛｰﾄﾞｻｰﾋﾞｽサービスの取得に失敗しました。",
                l_response.errorInfo,
                l_ex);
            return l_response;   
               
        }
        
        //管理者IPO抽選結果ｱｯﾌﾟﾛｰﾄﾞｻｰﾋﾞｽサービス.execute()メソッドをコールする
        try
        {
            
            l_response = (WEB3AdminIPOLotResultUploadCancelResponse)l_service.execute(l_request);
            
        }
        catch (WEB3BaseException l_ex)
        {
            l_response = (WEB3AdminIPOLotResultUploadCancelResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            l_response.errorMessage = l_ex.getErrorMessage();
            log.error(l_request, "管理者IPO抽選結果ｱｯﾌﾟﾛｰﾄﾞｻｰﾋﾞｽに失敗しました。", l_ex);
            return l_response;
        }
        log.exiting(STR_METHOD_NAME);
 
        //レスポンスオブジェクトを返却する。
        return l_response;

    }
}
@
