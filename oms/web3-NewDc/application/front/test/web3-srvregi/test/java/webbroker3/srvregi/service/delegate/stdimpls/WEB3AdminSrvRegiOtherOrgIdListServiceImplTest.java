head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.00.51.40;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3AdminSrvRegiOtherOrgIdListServiceImplTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : サービス利用管理者外部連携ID一覧照会ｻｰﾋﾞｽImplのテストクラス(WEB3AdminSrvRegiOtherOrgIdListServiceImplTest.java)
Author Name      : Daiwa Institute of Research
Revision History : 2008/03/20 周墨洋 (中訊) 新規作成
*/
package webbroker3.srvregi.service.delegate.stdimpls;

import java.util.Calendar;

import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccountTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.BranchParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.InstitutionParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.SubAccountParams;

import test.util.TestDBUtility;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3AscDescDef;
import webbroker3.common.define.WEB3TransactionCategoryDef;
import webbroker3.gentrade.WEB3Administrator;
import webbroker3.gentrade.WEB3AdministratorForMock;
import webbroker3.gentrade.data.AdministratorParams;
import webbroker3.mock.TestBaseForMock;
import webbroker3.srvregi.data.OtherOrgInfoAdminParams;
import webbroker3.srvregi.data.SrvRegiMasterParams;
import webbroker3.srvregi.define.WEB3SrvRegiKeyItemDef;
import webbroker3.srvregi.message.WEB3AdminSrvRegiOtherOrgIdListReferenceRequest;
import webbroker3.srvregi.message.WEB3AdminSrvRegiOtherOrgIdListReferenceResponse;
import webbroker3.srvregi.message.WEB3AdminSrvRegiOtherOrgIdListSearchRequest;
import webbroker3.srvregi.message.WEB3AdminSrvRegiOtherOrgIdListSearchResponse;
import webbroker3.srvregi.message.WEB3SrvRegiSortKey;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

/**
 * (サービス利用管理者外部連携ID一覧照会ｻｰﾋﾞｽImplのテストクラス)<BR>
 * サービス利用管理者外部連携ID一覧照会ｻｰﾋﾞｽImplのテストクラス<BR>
 * <BR>
 * @@author 周墨洋
 * @@version 1.0
 */
public class WEB3AdminSrvRegiOtherOrgIdListServiceImplTest
    extends TestBaseForMock
{

    /**
     * ログユーティリティ<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(
            WEB3AdminSrvRegiOtherOrgIdListServiceImplTest.class);

    /**
     * サービス利用管理者外部連携ID一覧照会ｻｰﾋﾞｽImpl
     */
    private WEB3AdminSrvRegiOtherOrgIdListServiceImpl impl;

    /**
     * @@param l_arg0
     * l_arg0
     */
    public WEB3AdminSrvRegiOtherOrgIdListServiceImplTest(String l_arg0)
    {
        super(l_arg0);
    }

    /**
     *
     */
    protected void setUp() throws Exception
    {
        super.setUp();
    }

    /**
     *
     */
    protected void tearDown() throws Exception
    {
        super.tearDown();
    }

    /**
     * testGetListSearchScreenCase0001
     */
    public void testGetListSearchScreenCase0001()
    {
        final String STR_METHOD_NAME = "testGetListSearchScreenCase0001()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            this.impl = new WEB3AdminSrvRegiOtherOrgIdListServiceImpl();

            WEB3AdminSrvRegiOtherOrgIdListSearchRequest l_request =
                new WEB3AdminSrvRegiOtherOrgIdListSearchRequest();
            l_request.specialProcessDiv = "1";

            MainAccountParams l_mainAccountParams =
                TestDBUtility.getMainAccountRow();
            TestDBUtility.deleteAll(MainAccountParams.TYPE);
            TestDBUtility.insertWithDel(l_mainAccountParams);

            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setBranchCode(
                l_mainAccountParams.getBranchCode());
            l_branchParams.setInstitutionCode(
                l_mainAccountParams.getInstitutionCode());
            TestDBUtility.deleteAll(BranchParams.TYPE);
            TestDBUtility.insertWithDel(l_branchParams);

            InstitutionParams l_institutionParams =
                TestDBUtility.getInstitutionRow();
            l_institutionParams.setInstitutionCode(
                l_mainAccountParams.getInstitutionCode());
            TestDBUtility.deleteAll(InstitutionParams.TYPE);
            TestDBUtility.insertWithDel(l_institutionParams);

            SubAccountParams l_subAccountParams =
                TestDBUtility.getSubAccountRow();
            l_subAccountParams.setAccountId(l_mainAccountParams.getAccountId());
            l_subAccountParams.setBranchId(l_branchParams.getBranchId());
            l_subAccountParams.setSubAccountType(
                SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
            TestDBUtility.deleteAll(SubAccountParams.TYPE);
            TestDBUtility.insertWithDel(l_subAccountParams);

            AdministratorParams l_administratorParams =
                TestDBUtility.getAdministratorRow();
            l_administratorParams.setLoginId(123L);
            TestDBUtility.deleteAll(AdministratorParams.TYPE);
            TestDBUtility.insertWithDel(l_administratorParams);

            SrvRegiMasterParams l_srvRegiMasterParams =
                TestDBUtility.getSrvRegiMasterRow();
            l_srvRegiMasterParams.setInstitutionCode(
                l_institutionParams.getInstitutionCode());
            l_srvRegiMasterParams.setSpecialProcessDiv("1");
            l_srvRegiMasterParams.setSrvDiv("3456");
            l_srvRegiMasterParams.setSrvName("name");
            TestDBUtility.deleteAll(SrvRegiMasterParams.TYPE);
            TestDBUtility.insertWithDel(l_srvRegiMasterParams);

            WEB3Administrator l_administrator =
                new WEB3Administrator(l_administratorParams);
            WEB3AdministratorForMock.mockGetInstanceFromLoginInfo(
                l_administrator);

            WEB3AdministratorForMock.mockValidateAuthority(
                l_administrator,
                WEB3TransactionCategoryDef.SRVREGI_OTHERORG,
                false,
                true);

            WEB3AdminSrvRegiOtherOrgIdListSearchResponse l_response =
                this.impl.getListSearchScreen(l_request);

            assertEquals(1, l_response.serviceDiv.length);
            assertEquals(1, l_response.serviceName.length);

            assertEquals("3456", l_response.serviceDiv[0]);
            assertEquals("name", l_response.serviceName[0]);

            log.exiting(TEST_END + STR_METHOD_NAME);
        }
        catch (WEB3BaseException l_exBE)
        {
            log.debug(STR_METHOD_NAME, l_exBE);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        catch (Exception l_exE)
        {
            log.debug(STR_METHOD_NAME, l_exE);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
    }

    /**
     * testGetListSearchScreenCase0002
     */
    public void testGetListSearchScreenCase0002()
    {
        final String STR_METHOD_NAME = "testGetListSearchScreenCase0002()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            this.impl = new WEB3AdminSrvRegiOtherOrgIdListServiceImpl();

            WEB3AdminSrvRegiOtherOrgIdListSearchRequest l_request =
                new WEB3AdminSrvRegiOtherOrgIdListSearchRequest();
            l_request.specialProcessDiv = "1";

            MainAccountParams l_mainAccountParams =
                TestDBUtility.getMainAccountRow();
            TestDBUtility.deleteAll(MainAccountParams.TYPE);
            TestDBUtility.insertWithDel(l_mainAccountParams);

            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setBranchCode(
                l_mainAccountParams.getBranchCode());
            l_branchParams.setInstitutionCode(
                l_mainAccountParams.getInstitutionCode());
            TestDBUtility.deleteAll(BranchParams.TYPE);
            TestDBUtility.insertWithDel(l_branchParams);

            InstitutionParams l_institutionParams =
                TestDBUtility.getInstitutionRow();
            l_institutionParams.setInstitutionCode(
                l_mainAccountParams.getInstitutionCode());
            TestDBUtility.deleteAll(InstitutionParams.TYPE);
            TestDBUtility.insertWithDel(l_institutionParams);

            SubAccountParams l_subAccountParams =
                TestDBUtility.getSubAccountRow();
            l_subAccountParams.setAccountId(l_mainAccountParams.getAccountId());
            l_subAccountParams.setBranchId(l_branchParams.getBranchId());
            l_subAccountParams.setSubAccountType(
                SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
            TestDBUtility.deleteAll(SubAccountParams.TYPE);
            TestDBUtility.insertWithDel(l_subAccountParams);

            AdministratorParams l_administratorParams =
                TestDBUtility.getAdministratorRow();
            l_administratorParams.setLoginId(123L);
            TestDBUtility.deleteAll(AdministratorParams.TYPE);
            TestDBUtility.insertWithDel(l_administratorParams);

            TestDBUtility.deleteAll(SrvRegiMasterParams.TYPE);
            SrvRegiMasterParams l_srvRegiMasterParams =
                TestDBUtility.getSrvRegiMasterRow();
            l_srvRegiMasterParams.setInstitutionCode(
                l_institutionParams.getInstitutionCode());
            l_srvRegiMasterParams.setSpecialProcessDiv("1");
            l_srvRegiMasterParams.setSrvDiv("3456");
            l_srvRegiMasterParams.setSrvName("name");
            TestDBUtility.insertWithDel(l_srvRegiMasterParams);

            l_srvRegiMasterParams =
                TestDBUtility.getSrvRegiMasterRow();
            l_srvRegiMasterParams.setInstitutionCode(
                l_institutionParams.getInstitutionCode());
            l_srvRegiMasterParams.setSpecialProcessDiv("1");
            l_srvRegiMasterParams.setSrvDiv("5678");
            l_srvRegiMasterParams.setSrvName("eman");
            TestDBUtility.insertWithDel(l_srvRegiMasterParams);

            WEB3Administrator l_administrator =
                new WEB3Administrator(l_administratorParams);
            WEB3AdministratorForMock.mockGetInstanceFromLoginInfo(
                l_administrator);

            WEB3AdministratorForMock.mockValidateAuthority(
                l_administrator,
                WEB3TransactionCategoryDef.SRVREGI_OTHERORG,
                false,
                true);

            WEB3AdminSrvRegiOtherOrgIdListSearchResponse l_response =
                this.impl.getListSearchScreen(l_request);

            assertEquals(2, l_response.serviceDiv.length);
            assertEquals(2, l_response.serviceName.length);

            assertEquals("3456", l_response.serviceDiv[0]);
            assertEquals("name", l_response.serviceName[0]);

            assertEquals("5678", l_response.serviceDiv[1]);
            assertEquals("eman", l_response.serviceName[1]);

            log.exiting(TEST_END + STR_METHOD_NAME);
        }
        catch (WEB3BaseException l_exBE)
        {
            log.debug(STR_METHOD_NAME, l_exBE);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        catch (Exception l_exE)
        {
            log.debug(STR_METHOD_NAME, l_exE);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
    }

    /**
     * testGetListSearchScreenCase0003
     */
    public void testGetListSearchScreenCase0003()
    {
        final String STR_METHOD_NAME = "testGetListSearchScreenCase0003()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            this.impl = new WEB3AdminSrvRegiOtherOrgIdListServiceImpl();

            WEB3AdminSrvRegiOtherOrgIdListSearchRequest l_request =
                new WEB3AdminSrvRegiOtherOrgIdListSearchRequest();
            l_request.specialProcessDiv = "1";

            MainAccountParams l_mainAccountParams =
                TestDBUtility.getMainAccountRow();
            TestDBUtility.deleteAll(MainAccountParams.TYPE);
            TestDBUtility.insertWithDel(l_mainAccountParams);

            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setBranchCode(
                l_mainAccountParams.getBranchCode());
            l_branchParams.setInstitutionCode(
                l_mainAccountParams.getInstitutionCode());
            TestDBUtility.deleteAll(BranchParams.TYPE);
            TestDBUtility.insertWithDel(l_branchParams);

            InstitutionParams l_institutionParams =
                TestDBUtility.getInstitutionRow();
            l_institutionParams.setInstitutionCode(
                l_mainAccountParams.getInstitutionCode());
            TestDBUtility.deleteAll(InstitutionParams.TYPE);
            TestDBUtility.insertWithDel(l_institutionParams);

            SubAccountParams l_subAccountParams =
                TestDBUtility.getSubAccountRow();
            l_subAccountParams.setAccountId(l_mainAccountParams.getAccountId());
            l_subAccountParams.setBranchId(l_branchParams.getBranchId());
            l_subAccountParams.setSubAccountType(
                SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
            TestDBUtility.deleteAll(SubAccountParams.TYPE);
            TestDBUtility.insertWithDel(l_subAccountParams);

            AdministratorParams l_administratorParams =
                TestDBUtility.getAdministratorRow();
            l_administratorParams.setLoginId(123L);
            TestDBUtility.deleteAll(AdministratorParams.TYPE);
            TestDBUtility.insertWithDel(l_administratorParams);

            TestDBUtility.deleteAll(SrvRegiMasterParams.TYPE);

            WEB3Administrator l_administrator =
                new WEB3Administrator(l_administratorParams);
            WEB3AdministratorForMock.mockGetInstanceFromLoginInfo(
                l_administrator);

            WEB3AdministratorForMock.mockValidateAuthority(
                l_administrator,
                WEB3TransactionCategoryDef.SRVREGI_OTHERORG,
                false,
                true);

            this.impl.getListSearchScreen(l_request);

            fail();

            log.exiting(TEST_END + STR_METHOD_NAME);
        }
        catch (WEB3BaseException l_exBE)
        {
            log.debug(STR_METHOD_NAME, l_exBE);
            assertEquals(
                WEB3ErrorCatalog.BUSINESS_ERROR_00982,
                l_exBE.getErrorInfo());
            log.exiting(TEST_END + STR_METHOD_NAME);
        }
        catch (Exception l_exE)
        {
            log.debug(STR_METHOD_NAME, l_exE);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
    }

    /**
     * testGetListReferenceScreenCase0001
     */
    public void testGetListReferenceScreenCase0001()
    {
        final String STR_METHOD_NAME = "testGetListReferenceScreenCase0001()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            this.impl = new WEB3AdminSrvRegiOtherOrgIdListServiceImpl();

            MainAccountParams l_mainAccountParams =
                TestDBUtility.getMainAccountRow();
            TestDBUtility.deleteAll(MainAccountParams.TYPE);
            TestDBUtility.insertWithDel(l_mainAccountParams);

            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setBranchCode(
                l_mainAccountParams.getBranchCode());
            l_branchParams.setInstitutionCode(
                l_mainAccountParams.getInstitutionCode());
            TestDBUtility.deleteAll(BranchParams.TYPE);
            TestDBUtility.insertWithDel(l_branchParams);

            InstitutionParams l_institutionParams =
                TestDBUtility.getInstitutionRow();
            l_institutionParams.setInstitutionCode(
                l_mainAccountParams.getInstitutionCode());
            TestDBUtility.deleteAll(InstitutionParams.TYPE);
            TestDBUtility.insertWithDel(l_institutionParams);

            SubAccountParams l_subAccountParams =
                TestDBUtility.getSubAccountRow();
            l_subAccountParams.setAccountId(l_mainAccountParams.getAccountId());
            l_subAccountParams.setBranchId(l_branchParams.getBranchId());
            l_subAccountParams.setSubAccountType(
                SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
            TestDBUtility.deleteAll(SubAccountParams.TYPE);
            TestDBUtility.insertWithDel(l_subAccountParams);

            AdministratorParams l_administratorParams =
                TestDBUtility.getAdministratorRow();
            l_administratorParams.setLoginId(123L);
            TestDBUtility.deleteAll(AdministratorParams.TYPE);
            TestDBUtility.insertWithDel(l_administratorParams);

            OtherOrgInfoAdminParams l_otherOrgInfoAdminParams =
                new OtherOrgInfoAdminParams();
            l_otherOrgInfoAdminParams.setSequenceNumber(123L);
            l_otherOrgInfoAdminParams.setSrvDiv("12");
            l_otherOrgInfoAdminParams.setInstitutionCode(
                l_mainAccountParams.getInstitutionCode());
            l_otherOrgInfoAdminParams.setBranchCode(
                l_mainAccountParams.getBranchCode());
            l_otherOrgInfoAdminParams.setAccountCode(
                l_mainAccountParams.getAccountCode());
            l_otherOrgInfoAdminParams.setStatus("9");
            l_otherOrgInfoAdminParams.setId("87654321");
            l_otherOrgInfoAdminParams.setPassword("123");
            l_otherOrgInfoAdminParams.setLastUpdater("a");
            l_otherOrgInfoAdminParams.setAppliStartDate(
                WEB3DateUtility.getDate("20080327", "yyyyMMdd"));
            l_otherOrgInfoAdminParams.setAppliEndDate(
                WEB3DateUtility.getDate("20080327", "yyyyMMdd"));
            l_otherOrgInfoAdminParams.setCreatedTimestamp(
                Calendar.getInstance().getTime());
            l_otherOrgInfoAdminParams.setLastUpdatedTimestamp(
                Calendar.getInstance().getTime());
            TestDBUtility.deleteAll(OtherOrgInfoAdminParams.TYPE);
            TestDBUtility.insertWithDel(l_otherOrgInfoAdminParams);

            WEB3Administrator l_administrator =
                new WEB3Administrator(l_administratorParams);
            WEB3AdministratorForMock.mockGetInstanceFromLoginInfo(
                l_administrator);

            WEB3AdministratorForMock.mockValidateAuthority(
                l_administrator,
                WEB3TransactionCategoryDef.SRVREGI_OTHERORG,
                false,
                true);

            WEB3AdministratorForMock.mockValidateBranchPermission(
                l_administrator,
                l_branchParams.getBranchCode(),
                true);

            WEB3AdminSrvRegiOtherOrgIdListReferenceRequest l_request =
                new WEB3AdminSrvRegiOtherOrgIdListReferenceRequest();
            l_request.pageSize = "10";
            l_request.pageIndex = "1";
            WEB3SrvRegiSortKey l_srvRegiSortKey = new WEB3SrvRegiSortKey();
            l_srvRegiSortKey.keyItem = WEB3SrvRegiKeyItemDef.SEQUENCE_NUMBER;
            l_srvRegiSortKey.ascDesc = WEB3AscDescDef.ASC;
            l_request.sortKeys =
                new WEB3SrvRegiSortKey[]{l_srvRegiSortKey};

            l_request.seqNumber = "123";
            l_request.serviceDiv = "12";
            l_request.branchCode =
                new String[]{l_mainAccountParams.getBranchCode()};
            l_request.accountCode =
                l_mainAccountParams.getAccountCode().substring(0, 6);
            l_request.status = "9";
            l_request.id = "87654321";
            l_request.appliStartFrom =
                WEB3DateUtility.getDate("20080327", "yyyyMMdd");
            l_request.appliStartTo =
                WEB3DateUtility.getDate("20080327", "yyyyMMdd");

            WEB3AdminSrvRegiOtherOrgIdListReferenceResponse l_response =
                this.impl.getListReferenceScreen(l_request);

            assertEquals("1", l_response.pageIndex);
            assertEquals("1", l_response.totalPages);
            assertEquals("1", l_response.totalRecords);
            assertEquals(1, l_response.otherOrgIdList.length);

            assertEquals("123", l_response.otherOrgIdList[0].seqNumber);
            assertEquals("381", l_response.otherOrgIdList[0].branchCode);
            assertEquals("251224", l_response.otherOrgIdList[0].accountCode);
            assertEquals("87654321", l_response.otherOrgIdList[0].id);
            assertEquals(
                WEB3DateUtility.getDate("20080327", "yyyyMMdd"),
                l_response.otherOrgIdList[0].appliStartDate);
            assertEquals(
                WEB3DateUtility.getDate("20080327", "yyyyMMdd"),
                l_response.otherOrgIdList[0].appliEndDate);
            assertEquals("9", l_response.otherOrgIdList[0].status);
            assertEquals(null, l_response.otherOrgIdList[0].lastUpdater);

            log.exiting(TEST_END + STR_METHOD_NAME);
        }
        catch (WEB3BaseException l_exBE)
        {
            log.debug(STR_METHOD_NAME, l_exBE);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        catch (Exception l_exE)
        {
            log.debug(STR_METHOD_NAME, l_exE);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
    }

    /**
     * testGetListReferenceScreenCase0002
     */
    public void testGetListReferenceScreenCase0002()
    {
        final String STR_METHOD_NAME = "testGetListReferenceScreenCase0002()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            this.impl = new WEB3AdminSrvRegiOtherOrgIdListServiceImpl();

            MainAccountParams l_mainAccountParams =
                TestDBUtility.getMainAccountRow();
            TestDBUtility.deleteAll(MainAccountParams.TYPE);
            TestDBUtility.insertWithDel(l_mainAccountParams);

            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setBranchCode(
                l_mainAccountParams.getBranchCode());
            l_branchParams.setInstitutionCode(
                l_mainAccountParams.getInstitutionCode());
            TestDBUtility.deleteAll(BranchParams.TYPE);
            TestDBUtility.insertWithDel(l_branchParams);

            InstitutionParams l_institutionParams =
                TestDBUtility.getInstitutionRow();
            l_institutionParams.setInstitutionCode(
                l_mainAccountParams.getInstitutionCode());
            TestDBUtility.deleteAll(InstitutionParams.TYPE);
            TestDBUtility.insertWithDel(l_institutionParams);

            SubAccountParams l_subAccountParams =
                TestDBUtility.getSubAccountRow();
            l_subAccountParams.setAccountId(l_mainAccountParams.getAccountId());
            l_subAccountParams.setBranchId(l_branchParams.getBranchId());
            l_subAccountParams.setSubAccountType(
                SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
            TestDBUtility.deleteAll(SubAccountParams.TYPE);
            TestDBUtility.insertWithDel(l_subAccountParams);

            AdministratorParams l_administratorParams =
                TestDBUtility.getAdministratorRow();
            l_administratorParams.setLoginId(123L);
            TestDBUtility.deleteAll(AdministratorParams.TYPE);
            TestDBUtility.insertWithDel(l_administratorParams);

            OtherOrgInfoAdminParams l_otherOrgInfoAdminParams =
                new OtherOrgInfoAdminParams();
            l_otherOrgInfoAdminParams.setSequenceNumber(123L);
            l_otherOrgInfoAdminParams.setSrvDiv("12");
            l_otherOrgInfoAdminParams.setInstitutionCode(
                l_mainAccountParams.getInstitutionCode());
            l_otherOrgInfoAdminParams.setBranchCode(
                l_mainAccountParams.getBranchCode());
            l_otherOrgInfoAdminParams.setAccountCode(
                l_mainAccountParams.getAccountCode());
            l_otherOrgInfoAdminParams.setStatus("1");
            l_otherOrgInfoAdminParams.setId("87654321");
            l_otherOrgInfoAdminParams.setPassword("123");
            l_otherOrgInfoAdminParams.setLastUpdater("abc");
            l_otherOrgInfoAdminParams.setAppliStartDate(
                WEB3DateUtility.getDate("20080327", "yyyyMMdd"));
            l_otherOrgInfoAdminParams.setAppliEndDate(
                WEB3DateUtility.getDate("20080327", "yyyyMMdd"));
            l_otherOrgInfoAdminParams.setCreatedTimestamp(
                Calendar.getInstance().getTime());
            l_otherOrgInfoAdminParams.setLastUpdatedTimestamp(
                Calendar.getInstance().getTime());
            TestDBUtility.deleteAll(OtherOrgInfoAdminParams.TYPE);
            TestDBUtility.insertWithDel(l_otherOrgInfoAdminParams);

            WEB3Administrator l_administrator =
                new WEB3Administrator(l_administratorParams);
            WEB3AdministratorForMock.mockGetInstanceFromLoginInfo(
                l_administrator);

            WEB3AdministratorForMock.mockValidateAuthority(
                l_administrator,
                WEB3TransactionCategoryDef.SRVREGI_OTHERORG,
                false,
                true);

            WEB3AdministratorForMock.mockValidateBranchPermission(
                l_administrator,
                l_branchParams.getBranchCode(),
                true);

            WEB3AdminSrvRegiOtherOrgIdListReferenceRequest l_request =
                new WEB3AdminSrvRegiOtherOrgIdListReferenceRequest();
            l_request.pageSize = "10";
            l_request.pageIndex = "1";
            WEB3SrvRegiSortKey l_srvRegiSortKey = new WEB3SrvRegiSortKey();
            l_srvRegiSortKey.keyItem = WEB3SrvRegiKeyItemDef.SEQUENCE_NUMBER;
            l_srvRegiSortKey.ascDesc = WEB3AscDescDef.ASC;
            l_request.sortKeys =
                new WEB3SrvRegiSortKey[]{l_srvRegiSortKey};

            l_request.seqNumber = "123";
            l_request.serviceDiv = "12";
            l_request.branchCode =
                new String[]{l_mainAccountParams.getBranchCode()};
            l_request.accountCode =
                l_mainAccountParams.getAccountCode().substring(0, 6);
            l_request.status = "1";
            l_request.id = "87654321";
            l_request.appliStartFrom =
                WEB3DateUtility.getDate("20080327", "yyyyMMdd");
            l_request.appliStartTo =
                WEB3DateUtility.getDate("20080327", "yyyyMMdd");

            WEB3AdminSrvRegiOtherOrgIdListReferenceResponse l_response =
                this.impl.getListReferenceScreen(l_request);

            assertEquals("1", l_response.pageIndex);
            assertEquals("1", l_response.totalPages);
            assertEquals("1", l_response.totalRecords);
            assertEquals(1, l_response.otherOrgIdList.length);

            assertEquals("123", l_response.otherOrgIdList[0].seqNumber);
            assertEquals("381", l_response.otherOrgIdList[0].branchCode);
            assertEquals("251224", l_response.otherOrgIdList[0].accountCode);
            assertEquals("87654321", l_response.otherOrgIdList[0].id);
            assertEquals(
                WEB3DateUtility.getDate("20080327", "yyyyMMdd"),
                l_response.otherOrgIdList[0].appliStartDate);
            assertEquals(
                WEB3DateUtility.getDate("20080327", "yyyyMMdd"),
                l_response.otherOrgIdList[0].appliEndDate);
            assertEquals("1", l_response.otherOrgIdList[0].status);
            assertEquals("abc", l_response.otherOrgIdList[0].lastUpdater);

            log.exiting(TEST_END + STR_METHOD_NAME);
        }
        catch (WEB3BaseException l_exBE)
        {
            log.debug(STR_METHOD_NAME, l_exBE);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        catch (Exception l_exE)
        {
            log.debug(STR_METHOD_NAME, l_exE);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
    }

    /**
     * testGetListReferenceScreenCase0003
     */
    public void testGetListReferenceScreenCase0003()
    {
        final String STR_METHOD_NAME = "testGetListReferenceScreenCase0003()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            this.impl = new WEB3AdminSrvRegiOtherOrgIdListServiceImpl();

            MainAccountParams l_mainAccountParams =
                TestDBUtility.getMainAccountRow();
            TestDBUtility.deleteAll(MainAccountParams.TYPE);
            TestDBUtility.insertWithDel(l_mainAccountParams);

            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setBranchCode(
                l_mainAccountParams.getBranchCode());
            l_branchParams.setInstitutionCode(
                l_mainAccountParams.getInstitutionCode());
            TestDBUtility.deleteAll(BranchParams.TYPE);
            TestDBUtility.insertWithDel(l_branchParams);

            InstitutionParams l_institutionParams =
                TestDBUtility.getInstitutionRow();
            l_institutionParams.setInstitutionCode(
                l_mainAccountParams.getInstitutionCode());
            TestDBUtility.deleteAll(InstitutionParams.TYPE);
            TestDBUtility.insertWithDel(l_institutionParams);

            SubAccountParams l_subAccountParams =
                TestDBUtility.getSubAccountRow();
            l_subAccountParams.setAccountId(l_mainAccountParams.getAccountId());
            l_subAccountParams.setBranchId(l_branchParams.getBranchId());
            l_subAccountParams.setSubAccountType(
                SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
            TestDBUtility.deleteAll(SubAccountParams.TYPE);
            TestDBUtility.insertWithDel(l_subAccountParams);

            AdministratorParams l_administratorParams =
                TestDBUtility.getAdministratorRow();
            l_administratorParams.setLoginId(123L);
            TestDBUtility.deleteAll(AdministratorParams.TYPE);
            TestDBUtility.insertWithDel(l_administratorParams);

            TestDBUtility.deleteAll(OtherOrgInfoAdminParams.TYPE);

            OtherOrgInfoAdminParams l_otherOrgInfoAdminParams1 =
                new OtherOrgInfoAdminParams();
            l_otherOrgInfoAdminParams1.setSequenceNumber(123L);
            l_otherOrgInfoAdminParams1.setSrvDiv("12");
            l_otherOrgInfoAdminParams1.setInstitutionCode(
                l_mainAccountParams.getInstitutionCode());
            l_otherOrgInfoAdminParams1.setBranchCode("381");
            l_otherOrgInfoAdminParams1.setAccountCode(
                l_mainAccountParams.getAccountCode());
            l_otherOrgInfoAdminParams1.setStatus("9");
            l_otherOrgInfoAdminParams1.setId("87654321");
            l_otherOrgInfoAdminParams1.setPassword("123");
            l_otherOrgInfoAdminParams1.setLastUpdater("a");
            l_otherOrgInfoAdminParams1.setAppliStartDate(
                WEB3DateUtility.getDate("20080327", "yyyyMMdd"));
            l_otherOrgInfoAdminParams1.setAppliEndDate(
                WEB3DateUtility.getDate("20080327", "yyyyMMdd"));
            l_otherOrgInfoAdminParams1.setCreatedTimestamp(
                Calendar.getInstance().getTime());
            l_otherOrgInfoAdminParams1.setLastUpdatedTimestamp(
                Calendar.getInstance().getTime());
            TestDBUtility.insertWithDel(l_otherOrgInfoAdminParams1);

            OtherOrgInfoAdminParams l_otherOrgInfoAdminParams2 =
                new OtherOrgInfoAdminParams();
            l_otherOrgInfoAdminParams2.setSequenceNumber(124L);
            l_otherOrgInfoAdminParams2.setSrvDiv("12");
            l_otherOrgInfoAdminParams2.setInstitutionCode(
                l_mainAccountParams.getInstitutionCode());
            l_otherOrgInfoAdminParams2.setBranchCode("183");
            l_otherOrgInfoAdminParams2.setAccountCode(
                l_mainAccountParams.getAccountCode());
            l_otherOrgInfoAdminParams2.setStatus("9");
            l_otherOrgInfoAdminParams2.setId("87654321");
            l_otherOrgInfoAdminParams2.setPassword("123");
            l_otherOrgInfoAdminParams2.setLastUpdater("a");
            l_otherOrgInfoAdminParams2.setAppliStartDate(
                WEB3DateUtility.getDate("20080327", "yyyyMMdd"));
            l_otherOrgInfoAdminParams2.setAppliEndDate(
                WEB3DateUtility.getDate("20080327", "yyyyMMdd"));
            l_otherOrgInfoAdminParams2.setCreatedTimestamp(
                Calendar.getInstance().getTime());
            l_otherOrgInfoAdminParams2.setLastUpdatedTimestamp(
                Calendar.getInstance().getTime());
            TestDBUtility.insertWithDel(l_otherOrgInfoAdminParams2);

            OtherOrgInfoAdminParams l_otherOrgInfoAdminParams =
                new OtherOrgInfoAdminParams();
            l_otherOrgInfoAdminParams.setSequenceNumber(125L);
            l_otherOrgInfoAdminParams.setSrvDiv("12");
            l_otherOrgInfoAdminParams.setInstitutionCode(
                l_mainAccountParams.getInstitutionCode());
            l_otherOrgInfoAdminParams.setBranchCode("831");
            l_otherOrgInfoAdminParams.setAccountCode(
                l_mainAccountParams.getAccountCode());
            l_otherOrgInfoAdminParams.setStatus("9");
            l_otherOrgInfoAdminParams.setId("87654321");
            l_otherOrgInfoAdminParams.setPassword("123");
            l_otherOrgInfoAdminParams.setLastUpdater("a");
            l_otherOrgInfoAdminParams.setAppliStartDate(
                WEB3DateUtility.getDate("20080327", "yyyyMMdd"));
            l_otherOrgInfoAdminParams.setAppliEndDate(
                WEB3DateUtility.getDate("20080327", "yyyyMMdd"));
            l_otherOrgInfoAdminParams.setCreatedTimestamp(
                Calendar.getInstance().getTime());
            l_otherOrgInfoAdminParams.setLastUpdatedTimestamp(
                Calendar.getInstance().getTime());
            TestDBUtility.insertWithDel(l_otherOrgInfoAdminParams);

            WEB3Administrator l_administrator =
                new WEB3Administrator(l_administratorParams);
            WEB3AdministratorForMock.mockGetInstanceFromLoginInfo(
                l_administrator);

            WEB3AdministratorForMock.mockValidateAuthority(
                l_administrator,
                WEB3TransactionCategoryDef.SRVREGI_OTHERORG,
                false,
                true);

            WEB3AdministratorForMock.mockValidateBranchPermission(
                l_administrator,
                l_branchParams.getBranchCode(),
                true);

            WEB3AdminSrvRegiOtherOrgIdListReferenceRequest l_request =
                new WEB3AdminSrvRegiOtherOrgIdListReferenceRequest();
            l_request.pageSize = "2";
            l_request.pageIndex = "2";
            WEB3SrvRegiSortKey l_srvRegiSortKey = new WEB3SrvRegiSortKey();
            l_srvRegiSortKey.keyItem = WEB3SrvRegiKeyItemDef.BRANCH_CODE;
            l_srvRegiSortKey.ascDesc = WEB3AscDescDef.ASC;
            l_request.sortKeys =
                new WEB3SrvRegiSortKey[]{l_srvRegiSortKey};

            l_request.seqNumber = null;
            l_request.serviceDiv = "12";
            l_request.branchCode = new String[]{"381", "183", "831"};
            l_request.accountCode =
                l_mainAccountParams.getAccountCode().substring(0, 6);
            l_request.status = "9";
            l_request.id = "87654321";
            l_request.appliStartFrom =
                WEB3DateUtility.getDate("20080327", "yyyyMMdd");
            l_request.appliStartTo =
                WEB3DateUtility.getDate("20080327", "yyyyMMdd");

            WEB3AdminSrvRegiOtherOrgIdListReferenceResponse l_response =
                this.impl.getListReferenceScreen(l_request);

            assertEquals("2", l_response.pageIndex);
            assertEquals("2", l_response.totalPages);
            assertEquals("3", l_response.totalRecords);
            assertEquals(1, l_response.otherOrgIdList.length);

//            assertEquals("124", l_response.otherOrgIdList[0].seqNumber);
//            assertEquals("183", l_response.otherOrgIdList[0].branchCode);
//            assertEquals("251224", l_response.otherOrgIdList[0].accountCode);
//            assertEquals("87654321", l_response.otherOrgIdList[0].id);
//            assertEquals(
//                WEB3DateUtility.getDate("20080327", "yyyyMMdd"),
//                l_response.otherOrgIdList[0].appliStartDate);
//            assertEquals(
//                WEB3DateUtility.getDate("20080327", "yyyyMMdd"),
//                l_response.otherOrgIdList[0].appliEndDate);
//            assertEquals("9", l_response.otherOrgIdList[0].status);
//            assertEquals(null, l_response.otherOrgIdList[0].lastUpdater);
//
//            assertEquals("123", l_response.otherOrgIdList[1].seqNumber);
//            assertEquals("381", l_response.otherOrgIdList[1].branchCode);
//            assertEquals("251224", l_response.otherOrgIdList[1].accountCode);
//            assertEquals("87654321", l_response.otherOrgIdList[1].id);
//            assertEquals(
//                WEB3DateUtility.getDate("20080327", "yyyyMMdd"),
//                l_response.otherOrgIdList[1].appliStartDate);
//            assertEquals(
//                WEB3DateUtility.getDate("20080327", "yyyyMMdd"),
//                l_response.otherOrgIdList[1].appliEndDate);
//            assertEquals("9", l_response.otherOrgIdList[1].status);
//            assertEquals(null, l_response.otherOrgIdList[1].lastUpdater);

            assertEquals("125", l_response.otherOrgIdList[0].seqNumber);
            assertEquals("831", l_response.otherOrgIdList[0].branchCode);
            assertEquals("251224", l_response.otherOrgIdList[0].accountCode);
            assertEquals("87654321", l_response.otherOrgIdList[0].id);
            assertEquals(
                WEB3DateUtility.getDate("20080327", "yyyyMMdd"),
                l_response.otherOrgIdList[0].appliStartDate);
            assertEquals(
                WEB3DateUtility.getDate("20080327", "yyyyMMdd"),
                l_response.otherOrgIdList[0].appliEndDate);
            assertEquals("9", l_response.otherOrgIdList[0].status);
            assertEquals(null, l_response.otherOrgIdList[0].lastUpdater);

            log.exiting(TEST_END + STR_METHOD_NAME);
        }
        catch (WEB3BaseException l_exBE)
        {
            log.debug(STR_METHOD_NAME, l_exBE);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        catch (Exception l_exE)
        {
            log.debug(STR_METHOD_NAME, l_exE);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
    }

    /**
     * testGetListReferenceScreenCase0004
     */
    public void testGetListReferenceScreenCase0004()
    {
        final String STR_METHOD_NAME = "testGetListReferenceScreenCase0004()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            this.impl = new WEB3AdminSrvRegiOtherOrgIdListServiceImpl();

            MainAccountParams l_mainAccountParams =
                TestDBUtility.getMainAccountRow();
            TestDBUtility.deleteAll(MainAccountParams.TYPE);
            TestDBUtility.insertWithDel(l_mainAccountParams);

            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setBranchCode(
                l_mainAccountParams.getBranchCode());
            l_branchParams.setInstitutionCode(
                l_mainAccountParams.getInstitutionCode());
            TestDBUtility.deleteAll(BranchParams.TYPE);
            TestDBUtility.insertWithDel(l_branchParams);

            InstitutionParams l_institutionParams =
                TestDBUtility.getInstitutionRow();
            l_institutionParams.setInstitutionCode(
                l_mainAccountParams.getInstitutionCode());
            TestDBUtility.deleteAll(InstitutionParams.TYPE);
            TestDBUtility.insertWithDel(l_institutionParams);

            SubAccountParams l_subAccountParams =
                TestDBUtility.getSubAccountRow();
            l_subAccountParams.setAccountId(l_mainAccountParams.getAccountId());
            l_subAccountParams.setBranchId(l_branchParams.getBranchId());
            l_subAccountParams.setSubAccountType(
                SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
            TestDBUtility.deleteAll(SubAccountParams.TYPE);
            TestDBUtility.insertWithDel(l_subAccountParams);

            AdministratorParams l_administratorParams =
                TestDBUtility.getAdministratorRow();
            l_administratorParams.setLoginId(123L);
            TestDBUtility.deleteAll(AdministratorParams.TYPE);
            TestDBUtility.insertWithDel(l_administratorParams);

            TestDBUtility.deleteAll(OtherOrgInfoAdminParams.TYPE);

            OtherOrgInfoAdminParams l_otherOrgInfoAdminParams =
                new OtherOrgInfoAdminParams();
            l_otherOrgInfoAdminParams.setSequenceNumber(123L);
            l_otherOrgInfoAdminParams.setSrvDiv("12");
            l_otherOrgInfoAdminParams.setInstitutionCode(
                l_mainAccountParams.getInstitutionCode());
            l_otherOrgInfoAdminParams.setBranchCode("381");
            l_otherOrgInfoAdminParams.setAccountCode(
                l_mainAccountParams.getAccountCode());
            l_otherOrgInfoAdminParams.setStatus("9");
            l_otherOrgInfoAdminParams.setId("87654321");
            l_otherOrgInfoAdminParams.setPassword("123");
            l_otherOrgInfoAdminParams.setLastUpdater("a");
            l_otherOrgInfoAdminParams.setAppliStartDate(
                WEB3DateUtility.getDate("20080327", "yyyyMMdd"));
            l_otherOrgInfoAdminParams.setAppliEndDate(
                WEB3DateUtility.getDate("20080327", "yyyyMMdd"));
            l_otherOrgInfoAdminParams.setCreatedTimestamp(
                Calendar.getInstance().getTime());
            l_otherOrgInfoAdminParams.setLastUpdatedTimestamp(
                Calendar.getInstance().getTime());
            TestDBUtility.insertWithDel(l_otherOrgInfoAdminParams);

            l_otherOrgInfoAdminParams =
                new OtherOrgInfoAdminParams();
            l_otherOrgInfoAdminParams.setSequenceNumber(124L);
            l_otherOrgInfoAdminParams.setSrvDiv("12");
            l_otherOrgInfoAdminParams.setInstitutionCode(
                l_mainAccountParams.getInstitutionCode());
            l_otherOrgInfoAdminParams.setBranchCode("381");
            l_otherOrgInfoAdminParams.setAccountCode(
                l_mainAccountParams.getAccountCode());
            l_otherOrgInfoAdminParams.setStatus("9");
            l_otherOrgInfoAdminParams.setId("87654321");
            l_otherOrgInfoAdminParams.setPassword("123");
            l_otherOrgInfoAdminParams.setLastUpdater("a");
            l_otherOrgInfoAdminParams.setAppliStartDate(
                WEB3DateUtility.getDate("20080327", "yyyyMMdd"));
            l_otherOrgInfoAdminParams.setAppliEndDate(
                WEB3DateUtility.getDate("20080327", "yyyyMMdd"));
            l_otherOrgInfoAdminParams.setCreatedTimestamp(
                Calendar.getInstance().getTime());
            l_otherOrgInfoAdminParams.setLastUpdatedTimestamp(
                Calendar.getInstance().getTime());
            TestDBUtility.insertWithDel(l_otherOrgInfoAdminParams);

            l_otherOrgInfoAdminParams =
                new OtherOrgInfoAdminParams();
            l_otherOrgInfoAdminParams.setSequenceNumber(125L);
            l_otherOrgInfoAdminParams.setSrvDiv("12");
            l_otherOrgInfoAdminParams.setInstitutionCode(
                l_mainAccountParams.getInstitutionCode());
            l_otherOrgInfoAdminParams.setBranchCode("381");
            l_otherOrgInfoAdminParams.setAccountCode(
                l_mainAccountParams.getAccountCode());
            l_otherOrgInfoAdminParams.setStatus("9");
            l_otherOrgInfoAdminParams.setId("87654321");
            l_otherOrgInfoAdminParams.setPassword("123");
            l_otherOrgInfoAdminParams.setLastUpdater("a");
            l_otherOrgInfoAdminParams.setAppliStartDate(
                WEB3DateUtility.getDate("20080327", "yyyyMMdd"));
            l_otherOrgInfoAdminParams.setAppliEndDate(
                WEB3DateUtility.getDate("20080327", "yyyyMMdd"));
            l_otherOrgInfoAdminParams.setCreatedTimestamp(
                Calendar.getInstance().getTime());
            l_otherOrgInfoAdminParams.setLastUpdatedTimestamp(
                Calendar.getInstance().getTime());
            TestDBUtility.insertWithDel(l_otherOrgInfoAdminParams);

            WEB3Administrator l_administrator =
                new WEB3Administrator(l_administratorParams);
            WEB3AdministratorForMock.mockGetInstanceFromLoginInfo(
                l_administrator);

            WEB3AdministratorForMock.mockValidateAuthority(
                l_administrator,
                WEB3TransactionCategoryDef.SRVREGI_OTHERORG,
                false,
                true);

            WEB3AdministratorForMock.mockValidateBranchPermission(
                l_administrator,
                l_branchParams.getBranchCode(),
                true);

            WEB3AdminSrvRegiOtherOrgIdListReferenceRequest l_request =
                new WEB3AdminSrvRegiOtherOrgIdListReferenceRequest();
            l_request.pageSize = "2";
            l_request.pageIndex = "2";
            WEB3SrvRegiSortKey l_srvRegiSortKey = new WEB3SrvRegiSortKey();
            l_srvRegiSortKey.keyItem = WEB3SrvRegiKeyItemDef.SEQUENCE_NUMBER;
            l_srvRegiSortKey.ascDesc = WEB3AscDescDef.ASC;
            l_request.sortKeys =
                new WEB3SrvRegiSortKey[]{l_srvRegiSortKey};

            l_request.seqNumber = null;
            l_request.serviceDiv = "12";
            l_request.branchCode = new String[]{"381"};
            l_request.accountCode =
                l_mainAccountParams.getAccountCode().substring(0, 6);
            l_request.status = "9";
            l_request.id = "87654321";
            l_request.appliStartFrom =
                WEB3DateUtility.getDate("20080327", "yyyyMMdd");
            l_request.appliStartTo =
                WEB3DateUtility.getDate("20080327", "yyyyMMdd");

            WEB3AdminSrvRegiOtherOrgIdListReferenceResponse l_response =
                this.impl.getListReferenceScreen(l_request);

            assertEquals("2", l_response.pageIndex);
            assertEquals("2", l_response.totalPages);
            assertEquals("3", l_response.totalRecords);
            assertEquals(1, l_response.otherOrgIdList.length);

//            assertEquals("123", l_response.otherOrgIdList[0].seqNumber);
//            assertEquals("381", l_response.otherOrgIdList[0].branchCode);
//            assertEquals("251224", l_response.otherOrgIdList[0].accountCode);
//            assertEquals("87654321", l_response.otherOrgIdList[0].id);
//            assertEquals(
//                WEB3DateUtility.getDate("20080327", "yyyyMMdd"),
//                l_response.otherOrgIdList[0].appliStartDate);
//            assertEquals(
//                WEB3DateUtility.getDate("20080327", "yyyyMMdd"),
//                l_response.otherOrgIdList[0].appliEndDate);
//            assertEquals("9", l_response.otherOrgIdList[0].status);
//            assertEquals(null, l_response.otherOrgIdList[0].lastUpdater);
//
//            assertEquals("124", l_response.otherOrgIdList[1].seqNumber);
//            assertEquals("381", l_response.otherOrgIdList[1].branchCode);
//            assertEquals("251224", l_response.otherOrgIdList[1].accountCode);
//            assertEquals("87654321", l_response.otherOrgIdList[1].id);
//            assertEquals(
//                WEB3DateUtility.getDate("20080327", "yyyyMMdd"),
//                l_response.otherOrgIdList[1].appliStartDate);
//            assertEquals(
//                WEB3DateUtility.getDate("20080327", "yyyyMMdd"),
//                l_response.otherOrgIdList[1].appliEndDate);
//            assertEquals("9", l_response.otherOrgIdList[1].status);
//            assertEquals(null, l_response.otherOrgIdList[1].lastUpdater);

            assertEquals("125", l_response.otherOrgIdList[0].seqNumber);
            assertEquals("381", l_response.otherOrgIdList[0].branchCode);
            assertEquals("251224", l_response.otherOrgIdList[0].accountCode);
            assertEquals("87654321", l_response.otherOrgIdList[0].id);
            assertEquals(
                WEB3DateUtility.getDate("20080327", "yyyyMMdd"),
                l_response.otherOrgIdList[0].appliStartDate);
            assertEquals(
                WEB3DateUtility.getDate("20080327", "yyyyMMdd"),
                l_response.otherOrgIdList[0].appliEndDate);
            assertEquals("9", l_response.otherOrgIdList[0].status);
            assertEquals(null, l_response.otherOrgIdList[0].lastUpdater);

            log.exiting(TEST_END + STR_METHOD_NAME);
        }
        catch (WEB3BaseException l_exBE)
        {
            log.debug(STR_METHOD_NAME, l_exBE);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        catch (Exception l_exE)
        {
            log.debug(STR_METHOD_NAME, l_exE);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
    }

    /**
     * testGetListReferenceScreenCase0005
     */
    public void testGetListReferenceScreenCase0005()
    {
        final String STR_METHOD_NAME = "testGetListReferenceScreenCase0005()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            this.impl = new WEB3AdminSrvRegiOtherOrgIdListServiceImpl();

            MainAccountParams l_mainAccountParams =
                TestDBUtility.getMainAccountRow();
            TestDBUtility.deleteAll(MainAccountParams.TYPE);
            TestDBUtility.insertWithDel(l_mainAccountParams);

            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setBranchCode(
                l_mainAccountParams.getBranchCode());
            l_branchParams.setInstitutionCode(
                l_mainAccountParams.getInstitutionCode());
            TestDBUtility.deleteAll(BranchParams.TYPE);
            TestDBUtility.insertWithDel(l_branchParams);

            InstitutionParams l_institutionParams =
                TestDBUtility.getInstitutionRow();
            l_institutionParams.setInstitutionCode(
                l_mainAccountParams.getInstitutionCode());
            TestDBUtility.deleteAll(InstitutionParams.TYPE);
            TestDBUtility.insertWithDel(l_institutionParams);

            SubAccountParams l_subAccountParams =
                TestDBUtility.getSubAccountRow();
            l_subAccountParams.setAccountId(l_mainAccountParams.getAccountId());
            l_subAccountParams.setBranchId(l_branchParams.getBranchId());
            l_subAccountParams.setSubAccountType(
                SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
            TestDBUtility.deleteAll(SubAccountParams.TYPE);
            TestDBUtility.insertWithDel(l_subAccountParams);

            AdministratorParams l_administratorParams =
                TestDBUtility.getAdministratorRow();
            l_administratorParams.setLoginId(123L);
            TestDBUtility.deleteAll(AdministratorParams.TYPE);
            TestDBUtility.insertWithDel(l_administratorParams);

            TestDBUtility.deleteAll(OtherOrgInfoAdminParams.TYPE);

            OtherOrgInfoAdminParams l_otherOrgInfoAdminParams =
                new OtherOrgInfoAdminParams();
            l_otherOrgInfoAdminParams.setSequenceNumber(123L);
            l_otherOrgInfoAdminParams.setSrvDiv("12");
            l_otherOrgInfoAdminParams.setInstitutionCode(
                l_mainAccountParams.getInstitutionCode());
            l_otherOrgInfoAdminParams.setBranchCode(null);
            l_otherOrgInfoAdminParams.setAccountCode(
                l_mainAccountParams.getAccountCode());
            l_otherOrgInfoAdminParams.setStatus("9");
            l_otherOrgInfoAdminParams.setId("87654321");
            l_otherOrgInfoAdminParams.setPassword("123");
            l_otherOrgInfoAdminParams.setLastUpdater("a");
            l_otherOrgInfoAdminParams.setAppliStartDate(
                WEB3DateUtility.getDate("20080327", "yyyyMMdd"));
            l_otherOrgInfoAdminParams.setAppliEndDate(
                WEB3DateUtility.getDate("20080327", "yyyyMMdd"));
            l_otherOrgInfoAdminParams.setCreatedTimestamp(
                Calendar.getInstance().getTime());
            l_otherOrgInfoAdminParams.setLastUpdatedTimestamp(
                Calendar.getInstance().getTime());
            TestDBUtility.insertWithDel(l_otherOrgInfoAdminParams);

            l_otherOrgInfoAdminParams =
                new OtherOrgInfoAdminParams();
            l_otherOrgInfoAdminParams.setSequenceNumber(124L);
            l_otherOrgInfoAdminParams.setSrvDiv("12");
            l_otherOrgInfoAdminParams.setInstitutionCode(
                l_mainAccountParams.getInstitutionCode());
            l_otherOrgInfoAdminParams.setBranchCode(null);
            l_otherOrgInfoAdminParams.setAccountCode(
                l_mainAccountParams.getAccountCode());
            l_otherOrgInfoAdminParams.setStatus("9");
            l_otherOrgInfoAdminParams.setId("87654321");
            l_otherOrgInfoAdminParams.setPassword("123");
            l_otherOrgInfoAdminParams.setLastUpdater("a");
            l_otherOrgInfoAdminParams.setAppliStartDate(
                WEB3DateUtility.getDate("20080327", "yyyyMMdd"));
            l_otherOrgInfoAdminParams.setAppliEndDate(
                WEB3DateUtility.getDate("20080327", "yyyyMMdd"));
            l_otherOrgInfoAdminParams.setCreatedTimestamp(
                Calendar.getInstance().getTime());
            l_otherOrgInfoAdminParams.setLastUpdatedTimestamp(
                Calendar.getInstance().getTime());
            TestDBUtility.insertWithDel(l_otherOrgInfoAdminParams);

            l_otherOrgInfoAdminParams =
                new OtherOrgInfoAdminParams();
            l_otherOrgInfoAdminParams.setSequenceNumber(125L);
            l_otherOrgInfoAdminParams.setSrvDiv("12");
            l_otherOrgInfoAdminParams.setInstitutionCode(
                l_mainAccountParams.getInstitutionCode());
            l_otherOrgInfoAdminParams.setBranchCode(null);
            l_otherOrgInfoAdminParams.setAccountCode(
                l_mainAccountParams.getAccountCode());
            l_otherOrgInfoAdminParams.setStatus("9");
            l_otherOrgInfoAdminParams.setId("87654321");
            l_otherOrgInfoAdminParams.setPassword("123");
            l_otherOrgInfoAdminParams.setLastUpdater("a");
            l_otherOrgInfoAdminParams.setAppliStartDate(
                WEB3DateUtility.getDate("20080327", "yyyyMMdd"));
            l_otherOrgInfoAdminParams.setAppliEndDate(
                WEB3DateUtility.getDate("20080327", "yyyyMMdd"));
            l_otherOrgInfoAdminParams.setCreatedTimestamp(
                Calendar.getInstance().getTime());
            l_otherOrgInfoAdminParams.setLastUpdatedTimestamp(
                Calendar.getInstance().getTime());
            TestDBUtility.insertWithDel(l_otherOrgInfoAdminParams);

            l_otherOrgInfoAdminParams =
                new OtherOrgInfoAdminParams();
            l_otherOrgInfoAdminParams.setSequenceNumber(126L);
            l_otherOrgInfoAdminParams.setSrvDiv("12");
            l_otherOrgInfoAdminParams.setInstitutionCode(
                l_mainAccountParams.getInstitutionCode());
            l_otherOrgInfoAdminParams.setBranchCode("138");
            l_otherOrgInfoAdminParams.setAccountCode(
                l_mainAccountParams.getAccountCode());
            l_otherOrgInfoAdminParams.setStatus("9");
            l_otherOrgInfoAdminParams.setId("87654321");
            l_otherOrgInfoAdminParams.setPassword("123");
            l_otherOrgInfoAdminParams.setLastUpdater("a");
            l_otherOrgInfoAdminParams.setAppliStartDate(
                WEB3DateUtility.getDate("20080327", "yyyyMMdd"));
            l_otherOrgInfoAdminParams.setAppliEndDate(
                WEB3DateUtility.getDate("20080327", "yyyyMMdd"));
            l_otherOrgInfoAdminParams.setCreatedTimestamp(
                Calendar.getInstance().getTime());
            l_otherOrgInfoAdminParams.setLastUpdatedTimestamp(
                Calendar.getInstance().getTime());
            TestDBUtility.insertWithDel(l_otherOrgInfoAdminParams);

            WEB3Administrator l_administrator =
                new WEB3Administrator(l_administratorParams);
            WEB3AdministratorForMock.mockGetInstanceFromLoginInfo(
                l_administrator);

            WEB3AdministratorForMock.mockValidateAuthority(
                l_administrator,
                WEB3TransactionCategoryDef.SRVREGI_OTHERORG,
                false,
                true);

            WEB3AdministratorForMock.mockValidateBranchPermission(
                l_administrator,
                l_branchParams.getBranchCode(),
                true);

            WEB3AdminSrvRegiOtherOrgIdListReferenceRequest l_request =
                new WEB3AdminSrvRegiOtherOrgIdListReferenceRequest();
            l_request.pageSize = "2";
            l_request.pageIndex = "1";
            WEB3SrvRegiSortKey l_srvRegiSortKey = new WEB3SrvRegiSortKey();
            l_srvRegiSortKey.keyItem = WEB3SrvRegiKeyItemDef.SEQUENCE_NUMBER;
            l_srvRegiSortKey.ascDesc = WEB3AscDescDef.ASC;
            l_request.sortKeys =
                new WEB3SrvRegiSortKey[]{l_srvRegiSortKey};

            l_request.seqNumber = null;
            l_request.serviceDiv = "12";
            l_request.branchCode = new String[]{"138"};
            l_request.accountCode =
                l_mainAccountParams.getAccountCode().substring(0, 6);
            l_request.status = "9";
            l_request.id = "87654321";
            l_request.appliStartFrom =
                WEB3DateUtility.getDate("20080327", "yyyyMMdd");
            l_request.appliStartTo =
                WEB3DateUtility.getDate("20080327", "yyyyMMdd");

            WEB3AdminSrvRegiOtherOrgIdListReferenceResponse l_response =
                this.impl.getListReferenceScreen(l_request);

            assertEquals("1", l_response.pageIndex);
            assertEquals("2", l_response.totalPages);
            assertEquals("4", l_response.totalRecords);
            assertEquals(2, l_response.otherOrgIdList.length);

            assertEquals("123", l_response.otherOrgIdList[0].seqNumber);
            assertEquals(null, l_response.otherOrgIdList[0].branchCode);
            assertEquals("251224", l_response.otherOrgIdList[0].accountCode);
            assertEquals("87654321", l_response.otherOrgIdList[0].id);
            assertEquals(
                WEB3DateUtility.getDate("20080327", "yyyyMMdd"),
                l_response.otherOrgIdList[0].appliStartDate);
            assertEquals(
                WEB3DateUtility.getDate("20080327", "yyyyMMdd"),
                l_response.otherOrgIdList[0].appliEndDate);
            assertEquals("9", l_response.otherOrgIdList[0].status);
            assertEquals(null, l_response.otherOrgIdList[0].lastUpdater);

            assertEquals("124", l_response.otherOrgIdList[1].seqNumber);
            assertEquals(null, l_response.otherOrgIdList[1].branchCode);
            assertEquals("251224", l_response.otherOrgIdList[1].accountCode);
            assertEquals("87654321", l_response.otherOrgIdList[1].id);
            assertEquals(
                WEB3DateUtility.getDate("20080327", "yyyyMMdd"),
                l_response.otherOrgIdList[1].appliStartDate);
            assertEquals(
                WEB3DateUtility.getDate("20080327", "yyyyMMdd"),
                l_response.otherOrgIdList[1].appliEndDate);
            assertEquals("9", l_response.otherOrgIdList[1].status);
            assertEquals(null, l_response.otherOrgIdList[1].lastUpdater);

//            assertEquals("125", l_response.otherOrgIdList[2].seqNumber);
//            assertEquals(null, l_response.otherOrgIdList[2].branchCode);
//            assertEquals("251224", l_response.otherOrgIdList[2].accountCode);
//            assertEquals("87654321", l_response.otherOrgIdList[2].id);
//            assertEquals(
//                WEB3DateUtility.getDate("20080327", "yyyyMMdd"),
//                l_response.otherOrgIdList[2].appliStartDate);
//            assertEquals(
//                WEB3DateUtility.getDate("20080327", "yyyyMMdd"),
//                l_response.otherOrgIdList[2].appliEndDate);
//            assertEquals("9", l_response.otherOrgIdList[2].status);
//            assertEquals(null, l_response.otherOrgIdList[2].lastUpdater);
//
//            assertEquals("126", l_response.otherOrgIdList[3].seqNumber);
//            assertEquals("138", l_response.otherOrgIdList[3].branchCode);
//            assertEquals("251224", l_response.otherOrgIdList[3].accountCode);
//            assertEquals("87654321", l_response.otherOrgIdList[3].id);
//            assertEquals(
//                WEB3DateUtility.getDate("20080327", "yyyyMMdd"),
//                l_response.otherOrgIdList[3].appliStartDate);
//            assertEquals(
//                WEB3DateUtility.getDate("20080327", "yyyyMMdd"),
//                l_response.otherOrgIdList[3].appliEndDate);
//            assertEquals("9", l_response.otherOrgIdList[3].status);
//            assertEquals(null, l_response.otherOrgIdList[3].lastUpdater);

            log.exiting(TEST_END + STR_METHOD_NAME);
        }
        catch (WEB3BaseException l_exBE)
        {
            log.debug(STR_METHOD_NAME, l_exBE);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        catch (Exception l_exE)
        {
            log.debug(STR_METHOD_NAME, l_exE);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
    }

    /**
     * testGetListReferenceScreenCase0006
     */
    public void testGetListReferenceScreenCase0006()
    {
        final String STR_METHOD_NAME = "testGetListReferenceScreenCase0006()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            this.impl = new WEB3AdminSrvRegiOtherOrgIdListServiceImpl();

            MainAccountParams l_mainAccountParams =
                TestDBUtility.getMainAccountRow();
            TestDBUtility.deleteAll(MainAccountParams.TYPE);
            TestDBUtility.insertWithDel(l_mainAccountParams);

            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setBranchCode(
                l_mainAccountParams.getBranchCode());
            l_branchParams.setInstitutionCode(
                l_mainAccountParams.getInstitutionCode());
            TestDBUtility.deleteAll(BranchParams.TYPE);
            TestDBUtility.insertWithDel(l_branchParams);

            InstitutionParams l_institutionParams =
                TestDBUtility.getInstitutionRow();
            l_institutionParams.setInstitutionCode(
                l_mainAccountParams.getInstitutionCode());
            TestDBUtility.deleteAll(InstitutionParams.TYPE);
            TestDBUtility.insertWithDel(l_institutionParams);

            SubAccountParams l_subAccountParams =
                TestDBUtility.getSubAccountRow();
            l_subAccountParams.setAccountId(l_mainAccountParams.getAccountId());
            l_subAccountParams.setBranchId(l_branchParams.getBranchId());
            l_subAccountParams.setSubAccountType(
                SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
            TestDBUtility.deleteAll(SubAccountParams.TYPE);
            TestDBUtility.insertWithDel(l_subAccountParams);

            AdministratorParams l_administratorParams =
                TestDBUtility.getAdministratorRow();
            l_administratorParams.setLoginId(123L);
            TestDBUtility.deleteAll(AdministratorParams.TYPE);
            TestDBUtility.insertWithDel(l_administratorParams);

            TestDBUtility.deleteAll(OtherOrgInfoAdminParams.TYPE);

            WEB3Administrator l_administrator =
                new WEB3Administrator(l_administratorParams);
            WEB3AdministratorForMock.mockGetInstanceFromLoginInfo(
                l_administrator);

            WEB3AdministratorForMock.mockValidateAuthority(
                l_administrator,
                WEB3TransactionCategoryDef.SRVREGI_OTHERORG,
                false,
                true);

            WEB3AdministratorForMock.mockValidateBranchPermission(
                l_administrator,
                l_branchParams.getBranchCode(),
                true);

            WEB3AdminSrvRegiOtherOrgIdListReferenceRequest l_request =
                new WEB3AdminSrvRegiOtherOrgIdListReferenceRequest();
            l_request.pageSize = "2";
            l_request.pageIndex = "2";
            WEB3SrvRegiSortKey l_srvRegiSortKey = new WEB3SrvRegiSortKey();
            l_srvRegiSortKey.keyItem = WEB3SrvRegiKeyItemDef.BRANCH_CODE;
            l_srvRegiSortKey.ascDesc = WEB3AscDescDef.ASC;
            l_request.sortKeys =
                new WEB3SrvRegiSortKey[]{l_srvRegiSortKey};

            l_request.seqNumber = "123";
            l_request.serviceDiv = "12";
            l_request.branchCode = new String[]{"381", "183", "831"};
            l_request.accountCode =
                l_mainAccountParams.getAccountCode().substring(0, 6);
            l_request.status = "9";
            l_request.id = "87654321";
            l_request.appliStartFrom =
                WEB3DateUtility.getDate("20080327", "yyyyMMdd");
            l_request.appliStartTo =
                WEB3DateUtility.getDate("20080327", "yyyyMMdd");

            this.impl.getListReferenceScreen(l_request);

            log.exiting(TEST_END + STR_METHOD_NAME);

            fail();
        }
        catch (WEB3BaseException l_exBE)
        {
            assertEquals(
                WEB3ErrorCatalog.BUSINESS_ERROR_03053,
                l_exBE.getErrorInfo());
            log.debug(STR_METHOD_NAME, l_exBE);
            log.exiting(TEST_END + STR_METHOD_NAME);
        }
        catch (Exception l_exE)
        {
            log.debug(STR_METHOD_NAME, l_exE);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
    }

    /**
     * testExecuteCase0001
     */
    public void testExecuteCase0001()
    {
        final String STR_METHOD_NAME = "testExecuteCase0001()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            this.impl = new WEB3AdminSrvRegiOtherOrgIdListServiceImpl();

            WEB3AdminSrvRegiOtherOrgIdListSearchRequest l_request =
                new WEB3AdminSrvRegiOtherOrgIdListSearchRequest();
            l_request.specialProcessDiv = "1";

            MainAccountParams l_mainAccountParams =
                TestDBUtility.getMainAccountRow();
            TestDBUtility.deleteAll(MainAccountParams.TYPE);
            TestDBUtility.insertWithDel(l_mainAccountParams);

            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setBranchCode(
                l_mainAccountParams.getBranchCode());
            l_branchParams.setInstitutionCode(
                l_mainAccountParams.getInstitutionCode());
            TestDBUtility.deleteAll(BranchParams.TYPE);
            TestDBUtility.insertWithDel(l_branchParams);

            InstitutionParams l_institutionParams =
                TestDBUtility.getInstitutionRow();
            l_institutionParams.setInstitutionCode(
                l_mainAccountParams.getInstitutionCode());
            TestDBUtility.deleteAll(InstitutionParams.TYPE);
            TestDBUtility.insertWithDel(l_institutionParams);

            SubAccountParams l_subAccountParams =
                TestDBUtility.getSubAccountRow();
            l_subAccountParams.setAccountId(l_mainAccountParams.getAccountId());
            l_subAccountParams.setBranchId(l_branchParams.getBranchId());
            l_subAccountParams.setSubAccountType(
                SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
            TestDBUtility.deleteAll(SubAccountParams.TYPE);
            TestDBUtility.insertWithDel(l_subAccountParams);

            AdministratorParams l_administratorParams =
                TestDBUtility.getAdministratorRow();
            l_administratorParams.setLoginId(123L);
            TestDBUtility.deleteAll(AdministratorParams.TYPE);
            TestDBUtility.insertWithDel(l_administratorParams);

            TestDBUtility.deleteAll(SrvRegiMasterParams.TYPE);

            WEB3Administrator l_administrator =
                new WEB3Administrator(l_administratorParams);
            WEB3AdministratorForMock.mockGetInstanceFromLoginInfo(
                l_administrator);

            WEB3AdministratorForMock.mockValidateAuthority(
                l_administrator,
                WEB3TransactionCategoryDef.SRVREGI_OTHERORG,
                false,
                true);

            this.impl.execute(l_request);

            fail();

            log.exiting(TEST_END + STR_METHOD_NAME);
        }
        catch (WEB3BaseException l_exBE)
        {
            log.debug(STR_METHOD_NAME, l_exBE);
            assertEquals(
                WEB3ErrorCatalog.BUSINESS_ERROR_00982,
                l_exBE.getErrorInfo());
            log.exiting(TEST_END + STR_METHOD_NAME);
        }
        catch (Exception l_exE)
        {
            log.debug(STR_METHOD_NAME, l_exE);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
    }

    /**
     * testExecuteCase0002
     */
    public void testExecuteCase0002()
    {
        final String STR_METHOD_NAME = "testExecuteCase0002()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            this.impl = new WEB3AdminSrvRegiOtherOrgIdListServiceImpl();

            MainAccountParams l_mainAccountParams =
                TestDBUtility.getMainAccountRow();
            TestDBUtility.deleteAll(MainAccountParams.TYPE);
            TestDBUtility.insertWithDel(l_mainAccountParams);

            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setBranchCode(
                l_mainAccountParams.getBranchCode());
            l_branchParams.setInstitutionCode(
                l_mainAccountParams.getInstitutionCode());
            TestDBUtility.deleteAll(BranchParams.TYPE);
            TestDBUtility.insertWithDel(l_branchParams);

            InstitutionParams l_institutionParams =
                TestDBUtility.getInstitutionRow();
            l_institutionParams.setInstitutionCode(
                l_mainAccountParams.getInstitutionCode());
            TestDBUtility.deleteAll(InstitutionParams.TYPE);
            TestDBUtility.insertWithDel(l_institutionParams);

            SubAccountParams l_subAccountParams =
                TestDBUtility.getSubAccountRow();
            l_subAccountParams.setAccountId(l_mainAccountParams.getAccountId());
            l_subAccountParams.setBranchId(l_branchParams.getBranchId());
            l_subAccountParams.setSubAccountType(
                SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
            TestDBUtility.deleteAll(SubAccountParams.TYPE);
            TestDBUtility.insertWithDel(l_subAccountParams);

            AdministratorParams l_administratorParams =
                TestDBUtility.getAdministratorRow();
            l_administratorParams.setLoginId(123L);
            TestDBUtility.deleteAll(AdministratorParams.TYPE);
            TestDBUtility.insertWithDel(l_administratorParams);

            TestDBUtility.deleteAll(OtherOrgInfoAdminParams.TYPE);

            WEB3Administrator l_administrator =
                new WEB3Administrator(l_administratorParams);
            WEB3AdministratorForMock.mockGetInstanceFromLoginInfo(
                l_administrator);

            WEB3AdministratorForMock.mockValidateAuthority(
                l_administrator,
                WEB3TransactionCategoryDef.SRVREGI_OTHERORG,
                false,
                true);

            WEB3AdministratorForMock.mockValidateBranchPermission(
                l_administrator,
                l_branchParams.getBranchCode(),
                true);

            WEB3AdminSrvRegiOtherOrgIdListReferenceRequest l_request =
                new WEB3AdminSrvRegiOtherOrgIdListReferenceRequest();
            l_request.pageSize = "2";
            l_request.pageIndex = "2";
            WEB3SrvRegiSortKey l_srvRegiSortKey = new WEB3SrvRegiSortKey();
            l_srvRegiSortKey.keyItem = WEB3SrvRegiKeyItemDef.BRANCH_CODE;
            l_srvRegiSortKey.ascDesc = WEB3AscDescDef.ASC;
            l_request.sortKeys =
                new WEB3SrvRegiSortKey[]{l_srvRegiSortKey};

            l_request.seqNumber = "123";
            l_request.serviceDiv = "12";
            l_request.branchCode = new String[]{"381", "183", "831"};
            l_request.accountCode =
                l_mainAccountParams.getAccountCode().substring(0, 6);
            l_request.status = "9";
            l_request.id = "87654321";
            l_request.appliStartFrom =
                WEB3DateUtility.getDate("20080327", "yyyyMMdd");
            l_request.appliStartTo =
                WEB3DateUtility.getDate("20080327", "yyyyMMdd");

            this.impl.getListReferenceScreen(l_request);

            log.exiting(TEST_END + STR_METHOD_NAME);

            fail();
        }
        catch (WEB3BaseException l_exBE)
        {
            log.debug(STR_METHOD_NAME, l_exBE);
            assertEquals(
                WEB3ErrorCatalog.BUSINESS_ERROR_03053,
                l_exBE.getErrorInfo());
            log.exiting(TEST_END + STR_METHOD_NAME);
        }
        catch (Exception l_exE)
        {
            log.debug(STR_METHOD_NAME, l_exE);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
    }

}
@
