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
filename	WEB3AdminBondProductListHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 債券管理者銘柄一覧ハンドラ(WEB3AdminBondProductListHandler.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/08/17 何文敏 (中訊) 新規作成
*/

package webbroker3.bd.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.util.WEB3LogUtility;
import webbroker3.bd.message.WEB3AdminBondProductSearchInputRequest;
import webbroker3.bd.message.WEB3AdminBondProductSearchInputResponse;
import webbroker3.bd.message.WEB3AdminBondProductSearchListRequest;
import webbroker3.bd.message.WEB3AdminBondProductSearchListResponse;
import webbroker3.bd.service.delegate.WEB3AdminBondProductListService;

/**
 * (債券管理者銘柄一覧ハンドラ)<BR>
 * 債券管理者銘柄一覧ハンドラクラス。<BR>
 * 
 * @@author 何文敏(中訊)
 * @@version 1.0 
 */
public class WEB3AdminBondProductListHandler implements MessageHandler 
{
    private static WEB3LogUtility log = 
        WEB3LogUtility.getInstance(WEB3AdminBondProductListHandler.class);
    
    /**
     * (銘柄一覧画面表示リクエスト)<BR>
     * 債券管理者銘柄一覧画面表示処理を行う。<BR>
     * <BR>
     * 債券管理者銘柄一覧サービスを取得し、execute()メソッド<BR>
     * をコールする。<BR>
     * @@param l_request - (リクエスト)<BR>
     * 債券管理者銘柄一覧画面表示リクエスト<BR>
     * @@return WEB3AdminBondProductSearchInputResponse
     * @@roseuid 44B603CE029E
     */
    public WEB3AdminBondProductSearchInputResponse inputProductList(
        WEB3AdminBondProductSearchInputRequest l_request) 
    {
        String STR_METHOD_NAME = 
            "inputProductList(WEB3AdminBondProductSearchInputRequest l_request)";
        log.entering(STR_METHOD_NAME);
        
        WEB3AdminBondProductListService l_service = null;
        WEB3AdminBondProductSearchInputResponse l_response = null;
        
        try
        {
            //債券管理者銘柄一覧サービスを取得し
            l_service = 
                (WEB3AdminBondProductListService)Services.getService(
                    WEB3AdminBondProductListService.class);
        }
        //サービスで例外が発生した場合は、エラー情報をレスポンスメッセージに設定する
        catch (Exception l_ex)
        {
            l_response = 
                (WEB3AdminBondProductSearchInputResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request, 
                "管理者債券銘柄一覧サービスを取得に失敗しました",
                l_response.errorInfo,
                l_ex);
            return l_response;
        }
        
        try
        {
            //execute()メソッドをコールする
            l_response =
                (WEB3AdminBondProductSearchInputResponse)l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response =
                (WEB3AdminBondProductSearchInputResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request,
                "債券管理者銘柄一覧画面表示処理が失敗しました。", 
                l_ex);
           
        }
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * (銘柄一覧検索リクエスト)<BR>
     * 債券管理者銘柄一覧検索処理を行う。<BR>
     * <BR>
     * 債券管理者銘柄一覧サービスを取得し、execute()メソッド<BR>
     * をコールする。<BR>
     * @@param l_request - (リクエスト)<BR>
     * 債券管理者銘柄一覧検索リクエスト<BR>
     * @@return WEB3AdminBondProductSearchListResponse
     * @@roseuid 44B603E502BE
     */
    public WEB3AdminBondProductSearchListResponse searchProductList(
        WEB3AdminBondProductSearchListRequest l_request) 
    {
        String STR_METHOD_NAME = 
            "searchProductList(WEB3AdminBondProductSearchListRequest l_request)";
        log.entering(STR_METHOD_NAME);
        
        WEB3AdminBondProductListService l_service = null;
        WEB3AdminBondProductSearchListResponse l_response = null;
        
        try
        {
            //債券管理者注文約定照会サービスを取得し
            l_service = 
                (WEB3AdminBondProductListService)Services.getService(
                    WEB3AdminBondProductListService.class);
        }
        //サービスで例外が発生した場合は、エラー情報をレスポンスメッセージに設定する
        catch (Exception l_ex)
        {
            l_response = 
                (WEB3AdminBondProductSearchListResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request, 
                "管理者債券銘柄一覧サービスを取得に失敗しました",
                l_response.errorInfo,
                l_ex);
            return l_response;
        }
        try
        {
            //execute()メソッドをコールする
            l_response = 
                (WEB3AdminBondProductSearchListResponse)l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response =
                (WEB3AdminBondProductSearchListResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request,
                "債券管理者銘柄一覧検索処理が失敗しました。", 
                l_ex);
            return l_response;
        }
        
        log.exiting(STR_METHOD_NAME);
        
        return l_response;     
    }
}
@
