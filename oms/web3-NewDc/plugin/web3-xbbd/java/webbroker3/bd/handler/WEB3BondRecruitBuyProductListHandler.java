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
filename	WEB3BondRecruitBuyProductListHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 債券応募/買付銘柄一覧ハンドラ(WEB3BondRecruitBuyProductListHandler.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/9/5 郭英 (中訊) 新規作成 
*/

package webbroker3.bd.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.util.WEB3LogUtility;
import webbroker3.bd.message.WEB3BondApplyBuyProductListRequest;
import webbroker3.bd.message.WEB3BondApplyBuyProductListResponse;
import webbroker3.bd.service.delegate.WEB3BondRecruitBuyProductListService;

/**
 * (債券応募/買付銘柄一覧ハンドラ)<BR>
 * 債券応募/買付銘柄一覧ハンドラ<BR>
 *   
 * @@author 郭英
 * @@version 1.0
 */
public class WEB3BondRecruitBuyProductListHandler implements MessageHandler
{
    /**
     * ログ出力ユーティリティ。<BR>
     */
    private static WEB3LogUtility log = 
        WEB3LogUtility.getInstance(WEB3BondRecruitBuyProductListHandler.class);  
    
    /**
     * @@roseuid 44FBFD3A0290
     */
    public WEB3BondRecruitBuyProductListHandler() 
    {
     
    }
    
    /**
     * (債券応募/買付銘柄一覧)<BR>
     * 債券応募/買付銘柄一覧処理を行う。 <BR>
     * <BR>
     * 債券応募/買付銘柄一覧サービスを取得し、execute()メソッドをコールする。<BR>
     * @@param l_request - リクエストデータ
     * @@return webbroker3.bd.message.WEB3BondApplyBuyProductListResponse
     * @@roseuid 44B757CE0249
     */
    public WEB3BondApplyBuyProductListResponse bondRecruitBuyProductList(WEB3BondApplyBuyProductListRequest l_request) 
    {
        final String STR_METHOD_NAME = " bondRecruitBuyProductList(WEB3BondApplyBuyProductListRequest)";
        log.entering(STR_METHOD_NAME);
        
        WEB3BondApplyBuyProductListResponse l_response = null;
        WEB3BondRecruitBuyProductListService l_service = null;
        
        try
        {
            //債券応募/買付銘柄一覧サービスの取得
            l_service = (WEB3BondRecruitBuyProductListService)
                Services.getService(WEB3BondRecruitBuyProductListService.class);
        }
        catch (Exception l_ex)
        {
            l_response = (WEB3BondApplyBuyProductListResponse) l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "債券応募/買付銘柄一覧サービスの取得に失敗しました。", 
                l_response.errorInfo,
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        
        try
        {
            //execute
            l_response = (WEB3BondApplyBuyProductListResponse) l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response = (WEB3BondApplyBuyProductListResponse) l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request, "債券応募/買付銘柄一覧処理の実施に失敗しました。", l_response.errorInfo, l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
}
@
