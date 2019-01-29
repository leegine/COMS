head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.10.02.12.51;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8644d782f8a12fc;
filename	WEB3AdminAccInfoCampaignRegistAccListHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 手数料割引キャンペーン登録顧客照会ハンドラ
                       (WEB3AdminAccInfoCampaignRegistAccListHandler.java)
Author Name      : Daiwa Institute of Research
Revision  History : 2007/02/01 孟亜南 (中訊) 新規作成
*/

package webbroker3.accountinfo.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.accountinfo.message.WEB3AdminAccInfoCampaignRegistAccListRequest;
import webbroker3.accountinfo.message.WEB3AdminAccInfoCampaignRegistAccListResponse;
import webbroker3.accountinfo.service.delegate.WEB3AdminAccInfoCampaignRegistAccListService;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.util.WEB3LogUtility;


/**
 * (登録顧客照会ハンドラ)<BR>
 * 手数料割引キャンペーン登録顧客照会ハンドラ<BR>
 * @@author 孟亜南 
 * @@version 1.0
 */
public class WEB3AdminAccInfoCampaignRegistAccListHandler implements MessageHandler 
{
    /**
     * ログ出力ユーティリティ。
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminAccInfoCampaignRegistAccListHandler.class);
    
    /**
     * @@roseuid 45C08B51035B
     */
    public WEB3AdminAccInfoCampaignRegistAccListHandler() 
    {
     
    }
    
    /**
     * (照会画面表示)<BR>
     * 手数料割引キャンペーン登録顧客照会画面表示処理を行う。 <BR>
     * 登録顧客照会サービスImplを取得し、 execute()メソッドをコールする。 <BR>
     * @@param l_request - (リクエストデータ)<BR>
     * 管理者お客様情報<BR>
     * 手数料割引キャンペーン登録顧客照会リクエストデータオブジェクト<BR>
     * 
     * 
     * @@return WEB3AdminAccInfoCampaignRegistAccListResponse
     * @@roseuid 45ADEF2F0114
     */
    public WEB3AdminAccInfoCampaignRegistAccListResponse listScreenShow(
            WEB3AdminAccInfoCampaignRegistAccListRequest l_request) 
    {
        final String STR_METHOD_NAME = ".getListScreen()";
        log.entering(STR_METHOD_NAME);
        
        WEB3AdminAccInfoCampaignRegistAccListService l_service = null;
        WEB3AdminAccInfoCampaignRegistAccListResponse l_response = null;
        
        try
        {
            l_service =
                (WEB3AdminAccInfoCampaignRegistAccListService)Services.getService(
                    WEB3AdminAccInfoCampaignRegistAccListService.class);
        }
        catch (Exception l_ex)
        {
            l_response = (WEB3AdminAccInfoCampaignRegistAccListResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "登録顧客照会サービスの取得に失敗しました。",
                l_response.errorInfo,
                l_ex);
            return l_response;
        }
        
        try
        {
            l_response = (WEB3AdminAccInfoCampaignRegistAccListResponse)l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response = (WEB3AdminAccInfoCampaignRegistAccListResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request, "登録顧客照会サービスの取得に失敗しました。", l_ex);
            return l_response;
        }
        
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
}
@
