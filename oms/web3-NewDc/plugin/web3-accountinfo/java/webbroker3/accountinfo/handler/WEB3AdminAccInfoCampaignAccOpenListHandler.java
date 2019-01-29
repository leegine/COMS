head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.10.02.14.00;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8644d782f8a12fc;
filename	WEB3AdminAccInfoCampaignAccOpenListHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 口座開設条件指定一覧ハンドラ (WEB3AdminAccInfoCampaignAccOpenListHandler.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/02/01 李木子 (中訊) 新規作成
*/
package webbroker3.accountinfo.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.accountinfo.message.WEB3AdminAccInfoCampaignAccOpenListRequest;
import webbroker3.accountinfo.message.WEB3AdminAccInfoCampaignAccOpenListResponse;
import webbroker3.accountinfo.service.delegate.WEB3AdminAccInfoCampaignAccOpenListService;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.util.WEB3LogUtility;

/**
 * (口座開設条件指定一覧ハンドラ)<BR>
 * 口座開設条件指定一覧ハンドラ<BR>
 * <BR>
 * @@author 李木子<BR>
 * @@version 1.0<BR>
 */
public class WEB3AdminAccInfoCampaignAccOpenListHandler implements MessageHandler
{
    /**
     * Log Variable
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminAccInfoCampaignAccOpenListHandler.class);

    /**
     * @@roseuid 45C08B5101D4
     */
    public WEB3AdminAccInfoCampaignAccOpenListHandler()
    {

    }

    /**
     * (一覧画面表示処理)<BR>
     * 口座開設条件指定一覧表示処理を実施する。<BR>
     * <BR>
     * 口座開設条件指定一覧サービスを取得し、<BR>
     * execute()メソッドをコールする。<BR>
     * @@param l_request - 管理者お客様情報手数料割引キャンペーン<BR>
     * 口座開設条件一覧リクエストオブジェクト<BR>
     *
     * @@return WEB3AdminAccInfoCampaignAccOpenListResponse
     * @@throws WEB3BaseException
     * @@roseuid 45AB14AC027B
     */
    public WEB3AdminAccInfoCampaignAccOpenListResponse getListScreen(
        WEB3AdminAccInfoCampaignAccOpenListRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = ".getListScreen(l_request)";
        log.entering(STR_METHOD_NAME);

        WEB3AdminAccInfoCampaignAccOpenListResponse l_response = null;
        WEB3AdminAccInfoCampaignAccOpenListService l_service = null;

        try
        {
            // 個別顧客指定一覧サービス
            l_service = (WEB3AdminAccInfoCampaignAccOpenListService)
                Services.getService(WEB3AdminAccInfoCampaignAccOpenListService.class);
        }
        catch (Exception l_ex)
        {
            l_response = (WEB3AdminAccInfoCampaignAccOpenListResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "口座開設条件指定一覧サービスの取得に失敗しました。",
                l_response.errorInfo,
                l_ex);
            return l_response;
        }

        try
        {
            l_response = (WEB3AdminAccInfoCampaignAccOpenListResponse)l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response = (WEB3AdminAccInfoCampaignAccOpenListResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request, "口座開設条件指定一覧サービスの取得に失敗しました。", l_ex);
            return l_response;
        }

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
}
@
