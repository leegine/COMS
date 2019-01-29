head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.10.02.21.40;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8644d782f8a12fc;
filename	WEB3AdminAccInfoCampaignIndiviListServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 個別顧客指定一覧サービスImpl (WEB3AdminAccInfoCampaignIndiviListServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/02/01 李木子 (中訊) 新規作成
*/
package webbroker3.accountinfo.service.delegate.stdimpls;

import webbroker3.accountinfo.WEB3AdminAccInfoCampaignCommon;
import webbroker3.accountinfo.define.WEB3AccInfoUpdateFlagDef;
import webbroker3.accountinfo.message.WEB3AccInfoCampaignInfo;
import webbroker3.accountinfo.message.WEB3AdminAccInfoCampaignIndiviListRequest;
import webbroker3.accountinfo.message.WEB3AdminAccInfoCampaignIndiviListResponse;
import webbroker3.accountinfo.service.delegate.WEB3AdminAccInfoCampaignIndiviListService;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3TransactionCategoryDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.gentrade.WEB3Administrator;
import webbroker3.util.WEB3LogUtility;

/**
 * (個別顧客指定一覧サービスImpl)<BR>
 * 個別顧客指定一覧サービスImpl<BR>
 * <BR>
 * @@author 李木子<BR>
 * @@version 1.0<BR>
 */
public class WEB3AdminAccInfoCampaignIndiviListServiceImpl implements WEB3AdminAccInfoCampaignIndiviListService
{
    /**
     * Log Variable
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminAccInfoCampaignIndiviListServiceImpl.class);

    /**
     * @@roseuid 45C08B520148
     */
    public WEB3AdminAccInfoCampaignIndiviListServiceImpl()
    {

    }

    /**
     * 個別顧客指定一覧表示処理を実施する。<BR>
     * <BR>
     * 　@－get一覧画面()をコールする。<BR>
     * @@param l_request - リクエスト<BR>
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 45B0702302FD
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = ".getListScreen(l_request)";
        log.entering(STR_METHOD_NAME);

        WEB3GenResponse l_response = null;

        //引数のリクエストデータが、 管理者お客様情報手数料割引ｷｬﾝﾍﾟｰﾝ個別顧客指定一覧ﾘｸｴｽﾄデータオブジェクトの場合
        if(l_request instanceof WEB3AdminAccInfoCampaignIndiviListRequest)
        {
            l_response = this.getListScreen((WEB3AdminAccInfoCampaignIndiviListRequest)l_request);
        }
        else
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80018,
                this.getClass().getName() + STR_METHOD_NAME);
        }

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (get一覧画面)<BR>
     * 個別顧客指定一覧画面表示を行う。 <BR>
     * <BR>
     * シーケンス図  <BR>
     * 「管理者お客様情報（個別顧客指定一覧）get一覧画面」参照。  <BR>
     * @@param l_request - 管理者お客様情報手数料割引ｷｬﾝﾍﾟｰﾝ<BR>
     * 個別顧客指定一覧ﾘｸｴｽﾄデータオブジェクト<BR>
     *
     * @@return WEB3AdminAccInfoCampaignIndiviListResponse
     * @@throws WEB3BaseException
     * @@roseuid 45AEFB8F0196
     */
    protected WEB3AdminAccInfoCampaignIndiviListResponse getListScreen(
        WEB3AdminAccInfoCampaignIndiviListRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = ".getListScreen(l_request)";
        log.entering(STR_METHOD_NAME);

        // validate( )
        l_request.validate();

        // getInstanceFromログイン情報( )
        WEB3Administrator l_administrator = WEB3Administrator.getInstanceFromLoginInfo();

        // validate権限(String, boolean)
        l_administrator.validateAuthority(WEB3TransactionCategoryDef.ACCINFO_COMMISSION_DISCOUNT_CAMPAIGN, false);

        // validate部店権限(String)
        l_administrator.validateBranchPermission(l_request.campaignSearchItem.branchCode);

        // get証券会社コード( )
        String l_strInstitutionCode = l_administrator.getInstitutionCode();

        // 手数料割引キャンペーン共通サービス
        WEB3AdminAccInfoCampaignCommon l_accInfoCampaignCommon = WEB3AdminAccInfoCampaignCommon.getInstance();

        // 登録タイプ
        String[] l_strUpdateFlag = {WEB3AccInfoUpdateFlagDef.UPDATE, WEB3AccInfoUpdateFlagDef.DELETE};

        // getキャンペーン一覧(WEB3GenRequest, String, String[])
        WEB3AccInfoCampaignInfo[] l_accInfoCampaignInfo =
            l_accInfoCampaignCommon.getCampaignList(
            l_request, l_strInstitutionCode, l_strUpdateFlag);

        // get総レコード件数(手数料割引キャンペーン検索条件, String, String[])
        int l_intAllRecordCount =
            l_accInfoCampaignCommon.getAllRecordCount(
            l_request.campaignSearchItem, l_strInstitutionCode, l_strUpdateFlag);

        // createResponse()
        WEB3AdminAccInfoCampaignIndiviListResponse l_response =
            (WEB3AdminAccInfoCampaignIndiviListResponse)l_request.createResponse();

        // プロパティセット
        int l_pageSize = Integer.parseInt(l_request.pageSize);

        if (l_pageSize < 1)
        {
        	l_pageSize = l_intAllRecordCount;
        }

        if (l_intAllRecordCount == 0)
        {
            l_response.totalPages = "" + 0;
        }
        else if (l_intAllRecordCount % l_pageSize == 0)
        {
            l_response.totalPages = "" + l_intAllRecordCount / l_pageSize;
        }
        else
        {
            l_response.totalPages = "" + (l_intAllRecordCount / l_pageSize + 1);
        }

        l_response.commissionCampaignInfo = l_accInfoCampaignInfo;
        l_response.totalRecords = "" + l_intAllRecordCount;
        l_response.pageIndex = l_request.pageIndex;

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
}
@
