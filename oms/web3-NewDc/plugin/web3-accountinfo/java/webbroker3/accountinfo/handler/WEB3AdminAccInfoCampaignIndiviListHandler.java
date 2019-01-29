head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.10.02.14.04;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8644d782f8a12fc;
filename	WEB3AdminAccInfoCampaignIndiviListHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 個別顧客指定一覧ハンドラ (WEB3AdminAccInfoCampaignIndiviListHandler.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/02/01 李木子 (中訊) 新規作成
*/
package webbroker3.accountinfo.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.accountinfo.message.WEB3AdminAccInfoCampaignIndiviListRequest;
import webbroker3.accountinfo.message.WEB3AdminAccInfoCampaignIndiviListResponse;
import webbroker3.accountinfo.service.delegate.WEB3AdminAccInfoCampaignIndiviListService;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.util.WEB3LogUtility;

/**
 * (個別顧客指定一覧ハンドラ)<BR>
 * 個別顧客指定一覧ハンドラ<BR>
 * <BR>
 * @@author 李木子<BR>
 * @@version 1.0<BR>
 */
public class WEB3AdminAccInfoCampaignIndiviListHandler implements MessageHandler
{

    /**
     * ログ出力ユーティリティ。
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminAccInfoCampaignIndiviListHandler.class);

    /**
     * @@roseuid 45C08B51000F
     */
    public WEB3AdminAccInfoCampaignIndiviListHandler()
    {

    }

    /**
     * (一覧画面表示処理)<BR>
     * 手数料キャンペーン顧客別一覧画面表示処理を実施する。<BR>
     * <BR>
     * 個別顧客指定登録サービスを取得し、execute()メソッドをコールする。<BR>
     * @@return WEB3AdminAccInfoCampaignIndiviListResponse
     * @@throws WEB3BaseException
     * @@roseuid 45A6E38703E3
     */
    public WEB3AdminAccInfoCampaignIndiviListResponse getListScreen(
        WEB3AdminAccInfoCampaignIndiviListRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = ".getListScreen()";
        log.entering(STR_METHOD_NAME);

        WEB3AdminAccInfoCampaignIndiviListResponse l_response = null;
        WEB3AdminAccInfoCampaignIndiviListService l_service = null;

        try
        {
            // 個別顧客指定一覧サービス
            l_service = (WEB3AdminAccInfoCampaignIndiviListService)
                Services.getService(WEB3AdminAccInfoCampaignIndiviListService.class);
        }
        catch (Exception l_ex)
        {
            l_response = (WEB3AdminAccInfoCampaignIndiviListResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "個別顧客指定一覧サービスの取得に失敗しました。",
                l_response.errorInfo,
                l_ex);
            return l_response;
        }

        try
        {
            l_response = (WEB3AdminAccInfoCampaignIndiviListResponse)l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response = (WEB3AdminAccInfoCampaignIndiviListResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request, "個別顧客指定一覧サービスの取得に失敗しました。", l_ex);
            return l_response;
        }

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
}
@
