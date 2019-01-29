head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.06.08.40.45;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5404d9c264d0465;
filename	WEB3AdminAccInfoCampaignIndiviListHandlerTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 個別顧客指定一覧ハンドラテスト (WEB3AdminAccInfoCampaignIndiviListHandlerTest.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/02/01 李木子 (中訊) 新規作成
*/
package webbroker3.accountinfo.handler;

import java.util.Calendar;

import com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum;

import test.util.TestDBUtility;

import webbroker3.accountinfo.data.CommCampaignCondMstParams;
import webbroker3.accountinfo.message.WEB3AccInfoCampaignInfo;
import webbroker3.accountinfo.message.WEB3AccInfoCampaignSearchCondition;
import webbroker3.accountinfo.message.WEB3AccInfoSortKey;
import webbroker3.accountinfo.message.WEB3AdminAccInfoCampaignIndiviListRequest;
import webbroker3.accountinfo.message.WEB3AdminAccInfoCampaignIndiviListResponse;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.gentrade.WEB3Administrator;
import webbroker3.gentrade.WEB3AdministratorForMock;
import webbroker3.gentrade.data.AdminPermissionParams;
import webbroker3.gentrade.data.AdministratorParams;
import webbroker3.gentrade.data.AdministratorTypeParams;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3LogUtility;

/**
 * (個別顧客指定一覧ハンドラテスト)<BR>
 * 個別顧客指定一覧ハンドラテスト<BR>
 * <BR>
 * @@author 李木子<BR>
 * @@version 1.0<BR>
 */
public class WEB3AdminAccInfoCampaignIndiviListHandlerTest extends
        TestBaseForMock {

    /**
     * Log Variable
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminAccInfoCampaignIndiviListHandlerTest.class);

    public WEB3AdminAccInfoCampaignIndiviListHandlerTest(String arg0) {
        super(arg0);
    }

    protected void setUp() throws Exception {
        super.setUp();
        TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);
    }

    protected void tearDown() throws Exception {
        super.tearDown();
    }

    /**
     * (一覧画面表示処理テスト)<BR>
     * 手数料キャンペーン顧客別一覧画面表示処理テストを実施する。<BR>
     */
    public void test_getListScreen_0001()
    {
        final String STR_METHOD_NAME = ".test_getListScreen_0001()";
        log.entering(STR_METHOD_NAME);

        WEB3AdminAccInfoCampaignIndiviListHandler l_adminAccInfoCampaignIndiviListHandler =
            new WEB3AdminAccInfoCampaignIndiviListHandler();

        WEB3AdminAccInfoCampaignIndiviListRequest l_request = new WEB3AdminAccInfoCampaignIndiviListRequest();
        WEB3AccInfoCampaignSearchCondition l_campainSearchItem = new WEB3AccInfoCampaignSearchCondition();
        WEB3AccInfoSortKey l_accInfoSortKey1 = new WEB3AccInfoSortKey();

        l_campainSearchItem.branchCode = "381";
        l_campainSearchItem.accountCode = "251224";
        l_campainSearchItem.campaignName = "601";
        l_campainSearchItem.collectRate = "99";

        l_accInfoSortKey1.keyItem = "branchCode";
        l_accInfoSortKey1.ascDesc = "A";

        WEB3AccInfoSortKey[] l_accInfoSortKeyList = {l_accInfoSortKey1};

        l_request.campaignSearchItem = l_campainSearchItem;
        l_request.pageSize = "2";
        l_request.sortKeys = l_accInfoSortKeyList;
        l_request.pageIndex = "2";

        WEB3AccInfoCampaignInfo l_accInfoCampaignInfo = new WEB3AccInfoCampaignInfo();

        l_accInfoCampaignInfo.accountCode = "251224";

        WEB3AccInfoCampaignInfo[] l_accInfoCampaignInfos = {l_accInfoCampaignInfo};

        AdministratorParams l_administratorRow = this.getAdministratorRow();
        AdminPermissionParams l_adminPermissionRow = this.getAdminPermissionRow();
        AdministratorTypeParams l_administratorTypeRow = this.getAdministratorTypeRow();
        CommCampaignCondMstParams l_commCampaignCondMstParams = this.getCommCampaignCondMstRow();

        try
        {
            WEB3Administrator l_administratorSet = new WEB3Administrator(l_administratorRow);

            TestDBUtility.insertWithDel(l_administratorRow);
            TestDBUtility.insertWithDel(l_adminPermissionRow);
            TestDBUtility.insertWithDel(l_administratorTypeRow);
            TestDBUtility.insertWithDel(l_commCampaignCondMstParams);
            WEB3AdministratorForMock.mockGetInstanceFromLoginInfo(l_administratorSet);
            WEB3AdministratorForMock.mockValidateAuthority(l_administratorSet, "C0903", false, true);
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }

        Integer l_intCampaign = new Integer(5);

        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "webbroker3.accountinfo.WEB3AdminAccInfoCampaignCommon",
            "getCampaignList",
            new Class[] {WEB3GenRequest.class, String.class, String[].class},
            l_accInfoCampaignInfos);

        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "webbroker3.accountinfo.WEB3AdminAccInfoCampaignCommon",
            "getAllRecordCount",
            new Class[] {WEB3AccInfoCampaignSearchCondition.class, String.class, String[].class},
            l_intCampaign);

        try
        {
            WEB3AdminAccInfoCampaignIndiviListResponse l_response =
                l_adminAccInfoCampaignIndiviListHandler.getListScreen(l_request);

            WEB3AccInfoCampaignInfo commissionCampaignInfo[] = l_response.commissionCampaignInfo;
            WEB3AccInfoCampaignInfo l_commissionCampaignInfo = commissionCampaignInfo[0];

            assertEquals("251224", l_commissionCampaignInfo.accountCode);
            assertEquals("5", l_response.totalRecords);
            assertEquals("2", l_response.pageIndex);
            assertEquals("3", l_response.totalPages);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (一覧画面表示処理テスト)<BR>
     * 手数料キャンペーン顧客別一覧画面表示処理テストを実施する。<BR>
     */
    public void test_getListScreen_0002()
    {
        final String STR_METHOD_NAME = ".test_getListScreen_0002()";
        log.entering(STR_METHOD_NAME);

        WEB3AdminAccInfoCampaignIndiviListHandler l_adminAccInfoCampaignIndiviListHandler =
            new WEB3AdminAccInfoCampaignIndiviListHandler();

        WEB3AdminAccInfoCampaignIndiviListRequest l_request = new WEB3AdminAccInfoCampaignIndiviListRequest();
        WEB3AccInfoCampaignSearchCondition l_campainSearchItem = new WEB3AccInfoCampaignSearchCondition();

        l_request.campaignSearchItem = l_campainSearchItem;
        l_campainSearchItem.branchCode = "1";

        try
        {
            WEB3AdminAccInfoCampaignIndiviListResponse l_response =
                l_adminAccInfoCampaignIndiviListHandler.getListScreen(l_request);

            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00834, l_response.errorInfo);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }

    /**
     * 手数料割引キャンペーン条件マスタテーブルRowを作成.<BR>
     */
    public CommCampaignCondMstParams getCommCampaignCondMstRow()
    {
        CommCampaignCondMstParams l_commCampaignCondMstParams = new CommCampaignCondMstParams();

        l_commCampaignCondMstParams.setCampaignId(1906);
        l_commCampaignCondMstParams.setCommCampaignName("601");
        l_commCampaignCondMstParams.setInstitutionCode("0D");
        l_commCampaignCondMstParams.setBranchCode("381");
        l_commCampaignCondMstParams.setAccountCode("251224");
        l_commCampaignCondMstParams.setAccountChargeRatio(100.1001);
        l_commCampaignCondMstParams.setLastUpdater("123456789");
        l_commCampaignCondMstParams.setLastUpdatedTimestamp(Calendar.getInstance().getTime());
        l_commCampaignCondMstParams.setCreatedTimestamp(Calendar.getInstance().getTime());
        l_commCampaignCondMstParams.setLastUpdatedTimestamp(Calendar.getInstance().getTime());

        return l_commCampaignCondMstParams;
    }

    /**
     * 管理者テーブルRowを作成.<BR>
     */
    public AdministratorParams getAdministratorRow()
    {
        AdministratorParams l_administratorParams = new AdministratorParams();

        l_administratorParams.setAdministratorId(110120119L);
        l_administratorParams.setAdministratorCode("123456789");
        l_administratorParams.setBranchCode("381");
        l_administratorParams.setInstitutionId(33);
        l_administratorParams.setInstitutionCode("0D");
        l_administratorParams.setLoginId(3338111123L);
        l_administratorParams.setPermissionLevel("123");

        return l_administratorParams;
    }

    /**
     * 管理者権限テーブルRowを作成.<BR>
     */
    public AdminPermissionParams getAdminPermissionRow()
    {
        AdminPermissionParams l_adminPermissionParams = new AdminPermissionParams();

        l_adminPermissionParams.setInstitutionCode("0D");
        l_adminPermissionParams.setPermissionLevel("123");
        l_adminPermissionParams.setTransactionCategory("C0903");
        l_adminPermissionParams.setUpdatePermissionFlag(BooleanEnum.FALSE);
        l_adminPermissionParams.setCreatedTimestamp(Calendar.getInstance().getTime());
        l_adminPermissionParams.setUpdateTimestamp(Calendar.getInstance().getTime());

        return l_adminPermissionParams;
    }

    public AdministratorTypeParams getAdministratorTypeRow()
    {
        AdministratorTypeParams l_administratorTypeRow = new AdministratorTypeParams();

        l_administratorTypeRow.setInstitutionCode("0D");
        l_administratorTypeRow.setPermissionLevel("123");
        l_administratorTypeRow.setDirAdminFlag(1);
        l_administratorTypeRow.setAllBranchPermissionFlag(BooleanEnum.FALSE);
        l_administratorTypeRow.setCreatedTimestamp(Calendar.getInstance().getTime());
        l_administratorTypeRow.setUpdateTimestamp(Calendar.getInstance().getTime());

        return l_administratorTypeRow;
    }
}
@
