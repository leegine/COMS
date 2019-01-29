head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.44;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3FeqProductListHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 外国株式銘柄一覧ハンドラ(WEB3FeqProductListHandler)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/07/15 黄建 (中訊) 新規作成   
                 : 2005/08/03 鄭海良(中訊) レビュー       
*/

package webbroker3.feq.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.feq.message.WEB3FeqProductListRequest;
import webbroker3.feq.message.WEB3FeqProductListResponse;
import webbroker3.feq.service.delegate.WEB3FeqProductListService;
import webbroker3.util.WEB3LogUtility;

/**
 * (外国株式銘柄一覧ハンドラ)<BR>
 * 外国株式銘柄一覧ハンドラ
 * 
 * @@author 黄建(中訊)
 * @@version 1.0
 */

public class WEB3FeqProductListHandler implements MessageHandler 
{
    
    /**
    * ログ出力ユーティリティ。
    */    
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3FeqProductListHandler.class);
    
    /**
     * @@roseuid 42D0DA1A01D4
     */
    public WEB3FeqProductListHandler() 
    {
     
    }
        
    /**
     * (get銘柄情報一覧)<BR>
     * 外国株式銘柄一覧処理を実施する。<BR>
     * <BR>
     * 外国株式銘柄一覧サービスを取得し、execute()メソッドをコールする。<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * 外国株式銘柄一覧リクエスト
     * @@return webbroker3.feq.message.WEB3FeqProductListResponse
     * @@roseuid 4297247B0157
     */
    public WEB3FeqProductListResponse getProductInformationList(WEB3FeqProductListRequest l_request) 
    {
        final String STR_METHOD_NAME = 
            "getProductInformationList(WEB3FeqProductListRequest l_request)";
        log.entering(STR_METHOD_NAME);
        
        //外国株式銘柄一覧サービスインターフェイス
        WEB3FeqProductListService l_service = null;
         
        //外国株式訂正確認レスポンス
        WEB3FeqProductListResponse l_response = null;
         
        try
        {
            l_service = 
                (WEB3FeqProductListService) Services.getService(
                    WEB3FeqProductListService.class);
        }
        catch (Exception l_ex)
        {
            //サービスで例外が発生した場合は、
            //エラー情報をレスポンスメッセージに設定する
            l_response =
                (WEB3FeqProductListResponse) l_request.createResponse();
            l_response.errorInfo = 
                WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "外国株式銘柄一覧サービスの取得に失敗しました。",
                l_response.errorInfo,
                l_ex);
            return l_response;
        }
        try
        {
            l_response =
                (WEB3FeqProductListResponse) l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response =
                (WEB3FeqProductListResponse) l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request, 
                "外国株式銘柄一覧処理に失敗しました。", 
                l_ex);
            return l_response;
        }
         
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
}
@
