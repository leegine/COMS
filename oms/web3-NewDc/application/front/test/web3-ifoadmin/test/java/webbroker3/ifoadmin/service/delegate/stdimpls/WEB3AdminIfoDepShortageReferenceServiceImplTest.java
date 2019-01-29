head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.02.02.56;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3AdminIfoDepShortageReferenceServiceImplTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : WEB3AdminIfoDepShortageReferenceServiceImplTest.java
Author Name      : Daiwa Institute of Research
Revision History : 2009/03/09 劉剣（中訊）新規作成
*/
package webbroker3.ifoadmin.service.delegate.stdimpls;

import java.sql.Timestamp;
import java.util.Date;
import java.util.Map;
import java.util.Set;

import com.fitechlabs.xtrade.plugin.security.oplogin.LoginInfo;
import com.fitechlabs.xtrade.plugin.security.oplogin.LoginTypeInfo;
import com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccountTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.InstitutionParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.InstitutionRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.SubAccountParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.SubAccountRow;

import test.util.TestDBUtility;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.gentrade.WEB3GentradeBizDate;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.gentrade.data.AdminPermissionParams;
import webbroker3.gentrade.data.AdminPermissionRow;
import webbroker3.gentrade.data.AdministratorParams;
import webbroker3.gentrade.data.AdministratorRow;
import webbroker3.gentrade.data.AdministratorTypeParams;
import webbroker3.gentrade.data.AdministratorTypeRow;
import webbroker3.gentrade.data.IfoDepositParams;
import webbroker3.gentrade.data.IfoDepositRow;
import webbroker3.gentrade.data.ProcessManagementParams;
import webbroker3.gentrade.data.ProcessManagementRow;
import webbroker3.ifoadmin.message.WEB3AdminIfoDepShortageRefInputRequest;
import webbroker3.ifoadmin.message.WEB3AdminIfoDepShortageRefInputResponse;
import webbroker3.ifoadmin.message.WEB3AdminIfoDepShortageReferenceRequest;
import webbroker3.ifoadmin.message.WEB3AdminIfoDepShortageReferenceResponse;
import webbroker3.ifoadmin.message.WEB3AdminIfoManualLapseConfirmRequest;
import webbroker3.ifoadmin.message.WEB3IfoDepShortageInfo;
import webbroker3.ifoadmin.message.WEB3IfoDepShortageSortKey;
import webbroker3.ifodeposit.WEB3IfoDepositCalc;
import webbroker3.ifodeposit.WEB3IfoDepositCalcCondition;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

public class WEB3AdminIfoDepShortageReferenceServiceImplTest extends TestBaseForMock
{
    public WEB3AdminIfoDepShortageReferenceServiceImplTest(String name)
    {
        super(name);
        // TODO Auto-generated constructor stub
    }

    protected void setUp() throws Exception
    {
        super.setUp();
    }

    protected void tearDown() throws Exception
    {
        super.tearDown();
    }

    /**
     * ログユーティリティ<BR>
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(
        WEB3AdminIfoDepShortageReferenceServiceImplTest.class);

    /**
     * 当リクエストデータの整合性チェックを行う。
     * WEB3ErrorCatalog.BUSINESS_ERROR_03154
     */
    public void testGetReferenceScreen_C0001()
    {
        final String STR_METHOD_NAME = " testGetReferenceScreen_C0001()";
        log.entering(STR_METHOD_NAME);
        try
        {
            WEB3AdminIfoDepShortageReferenceRequest l_request =
                new WEB3AdminIfoDepShortageReferenceRequest();

            WEB3AdminIfoDepShortageReferenceServiceImpl l_impl =
                new WEB3AdminIfoDepShortageReferenceServiceImpl();

            l_impl.getReferenceScreen(l_request);

            fail();
        }
        catch (WEB3BusinessLayerException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_03154, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME,l_ex);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    /**
     * validate権限失敗
     * WEB3ErrorCatalog.BUSINESS_ERROR_01056
     */
    public void testGetReferenceScreen_C0002()
    {
        final String STR_METHOD_NAME = " testGetReferenceScreen_C0002()";
        log.entering(STR_METHOD_NAME);
        try
        {
            LoginInfo l_loginInfo = new LoginInfoForTest();
            MOCK_MANAGER.setIsMockUsed(true);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                    "getLoginInfo",
                    new Class[] {},
                    l_loginInfo);

            TestDBUtility.deleteAll(AdministratorRow.TYPE);
            AdministratorParams l_administratorParams = TestDBUtility.getAdministratorRow();
            l_administratorParams.setLoginId(33381330003L);
            TestDBUtility.insertWithDel(l_administratorParams);

            TestDBUtility.deleteAll(AdminPermissionRow.TYPE);

            WEB3AdminIfoDepShortageReferenceRequest l_request =
                new WEB3AdminIfoDepShortageReferenceRequest();
            l_request.searchDate = WEB3DateUtility.getDate("20090309", "yyyyMMdd");
            l_request.branchCode = new String[1];
            l_request.branchCode[0] = "005";
            l_request.uncancelDiv = "0";
            l_request.sortKeys = new WEB3IfoDepShortageSortKey[1];
            l_request.sortKeys[0] = new WEB3IfoDepShortageSortKey();
            l_request.sortKeys[0].keyItem = "branchCode";
            l_request.sortKeys[0].ascDesc = "A";
            l_request.pageIndex = "1";
            l_request.pageSize = "1";

            WEB3AdminIfoDepShortageReferenceServiceImpl l_impl =
                new WEB3AdminIfoDepShortageReferenceServiceImpl();

            l_impl.getReferenceScreen(l_request);

            fail();
        }
        catch (WEB3BusinessLayerException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_01056, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME,l_ex);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    /**
     * validate部店権限(部店コード : String[])失敗
     * WEB3ErrorCatalog.BUSINESS_ERROR_01074
     */
    public void testGetReferenceScreen_C0003()
    {
        final String STR_METHOD_NAME = " testGetReferenceScreen_C0003()";
        log.entering(STR_METHOD_NAME);
        try
        {
            LoginInfo l_loginInfo = new LoginInfoForTest();
            MOCK_MANAGER.setIsMockUsed(true);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                    "getLoginInfo",
                    new Class[] {},
                    l_loginInfo);

            TestDBUtility.deleteAll(AdministratorTypeRow.TYPE);
            AdministratorTypeParams l_administratorTypeParams = TestDBUtility.getAdministratorTypeRow();
            l_administratorTypeParams.setInstitutionCode("0D");
            l_administratorTypeParams.setPermissionLevel("331");
            l_administratorTypeParams.setAllBranchPermissionFlag(BooleanEnum.FALSE);
            TestDBUtility.insertWithDel(l_administratorTypeParams);

            TestDBUtility.deleteAll(InstitutionRow.TYPE);
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            l_institutionParams.setInstitutionCode("0D");
            l_institutionParams.setInstitutionId(33L);
            TestDBUtility.insertWithDel(l_institutionParams);

            TestDBUtility.deleteAll(AdministratorRow.TYPE);
            AdministratorParams l_administratorParams = TestDBUtility.getAdministratorRow();
            l_administratorParams.setInstitutionCode("0D");
            l_administratorParams.setPermissionLevel("331");
            l_administratorParams.setBranchCode("001");
            l_administratorParams.setLoginId(33381330003L);
            TestDBUtility.insertWithDel(l_administratorParams);

            TestDBUtility.deleteAll(AdminPermissionRow.TYPE);
            AdminPermissionParams l_adminPermissionParams = TestDBUtility.getAdminPermissionRow();
            l_adminPermissionParams.setInstitutionCode("0D");
            l_adminPermissionParams.setPermissionLevel("331");
            l_adminPermissionParams.setTransactionCategory("C0305");
            l_adminPermissionParams.setUpdatePermissionFlag(BooleanEnum.TRUE);
            TestDBUtility.insertWithDel(l_adminPermissionParams);

            WEB3AdminIfoDepShortageReferenceRequest l_request =
                new WEB3AdminIfoDepShortageReferenceRequest();
            l_request.searchDate = WEB3DateUtility.getDate("20090309", "yyyyMMdd");
            l_request.branchCode = new String[1];
            l_request.branchCode[0] = "005";
            l_request.uncancelDiv = "0";
            l_request.sortKeys = new WEB3IfoDepShortageSortKey[1];
            l_request.sortKeys[0] = new WEB3IfoDepShortageSortKey();
            l_request.sortKeys[0].keyItem = "branchCode";
            l_request.sortKeys[0].ascDesc = "A";
            l_request.pageIndex = "1";
            l_request.pageSize = "1";

            WEB3AdminIfoDepShortageReferenceServiceImpl l_impl =
                new WEB3AdminIfoDepShortageReferenceServiceImpl();

            l_impl.getReferenceScreen(l_request);

            fail();
        }
        catch (WEB3BusinessLayerException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_01074, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME,l_ex);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    /**
     * リクエストデータ ．検索日付 が　@「当営業日」　@＆＆　@is証拠金不足メール送信済（）の戻り値 == falseの場合
     * WEB3ErrorCatalog.BUSINESS_ERROR_03157
     */
    public void testGetReferenceScreen_C0004()
    {
        final String STR_METHOD_NAME = " testGetReferenceScreen_C0004()";
        log.entering(STR_METHOD_NAME);
        try
        {
            LoginInfo l_loginInfo = new LoginInfoForTest();
            MOCK_MANAGER.setIsMockUsed(true);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                    "getLoginInfo",
                    new Class[] {},
                    l_loginInfo);

            Date l_dat = WEB3DateUtility.getDate("20090309", "yyyyMMdd");
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.tc.gentrade.stdimpls.TradingSystemImpl",
                    "getBizDate",
                    new Class[] {},
                    l_dat);

            TestDBUtility.deleteAll(InstitutionRow.TYPE);
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            l_institutionParams.setInstitutionCode("0D");
            l_institutionParams.setInstitutionId(33L);
            TestDBUtility.insertWithDel(l_institutionParams);

            TestDBUtility.deleteAll(AdministratorRow.TYPE);
            AdministratorParams l_administratorParams = TestDBUtility.getAdministratorRow();
            l_administratorParams.setInstitutionCode("0D");
            l_administratorParams.setPermissionLevel("331");
            l_administratorParams.setBranchCode("001");
            l_administratorParams.setLoginId(33381330003L);
            TestDBUtility.insertWithDel(l_administratorParams);

            TestDBUtility.deleteAll(AdminPermissionRow.TYPE);
            AdminPermissionParams l_adminPermissionParams = TestDBUtility.getAdminPermissionRow();
            l_adminPermissionParams.setInstitutionCode("0D");
            l_adminPermissionParams.setPermissionLevel("331");
            l_adminPermissionParams.setTransactionCategory("C0305");
            l_adminPermissionParams.setUpdatePermissionFlag(BooleanEnum.TRUE);
            TestDBUtility.insertWithDel(l_adminPermissionParams);

            TestDBUtility.deleteAll(ProcessManagementRow.TYPE);

            WEB3AdminIfoDepShortageReferenceRequest l_request =
                new WEB3AdminIfoDepShortageReferenceRequest();
            l_request.searchDate = WEB3DateUtility.getDate("20090309", "yyyyMMdd");
            l_request.branchCode = new String[1];
            l_request.branchCode[0] = "001";
            l_request.uncancelDiv = "0";
            l_request.sortKeys = new WEB3IfoDepShortageSortKey[1];
            l_request.sortKeys[0] = new WEB3IfoDepShortageSortKey();
            l_request.sortKeys[0].keyItem = "branchCode";
            l_request.sortKeys[0].ascDesc = "A";
            l_request.pageIndex = "1";
            l_request.pageSize = "1";

            WEB3AdminIfoDepShortageReferenceServiceImpl l_impl =
                new WEB3AdminIfoDepShortageReferenceServiceImpl();

            l_impl.getReferenceScreen(l_request);

            fail();
        }
        catch (WEB3BusinessLayerException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_03157, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME,l_ex);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    /**
     * create証拠金不足状況一覧の戻り値の要素数が0の場合
     * 空のレスポンスを生成し、初期値をセットして返却
     */
    public void testGetReferenceScreen_C0005()
    {
        final String STR_METHOD_NAME = " testGetReferenceScreen_C0005()";
        log.entering(STR_METHOD_NAME);
        try
        {
            LoginInfo l_loginInfo = new LoginInfoForTest();
            MOCK_MANAGER.setIsMockUsed(true);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                    "getLoginInfo",
                    new Class[] {},
                    l_loginInfo);

            Date l_dat = WEB3DateUtility.getDate("20090309", "yyyyMMdd");
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.tc.gentrade.stdimpls.TradingSystemImpl",
                    "getBizDate",
                    new Class[] {},
                    l_dat);

            TestDBUtility.deleteAll(InstitutionRow.TYPE);
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            l_institutionParams.setInstitutionCode("0D");
            l_institutionParams.setInstitutionId(33L);
            TestDBUtility.insertWithDel(l_institutionParams);

            TestDBUtility.deleteAll(AdministratorRow.TYPE);
            AdministratorParams l_administratorParams = TestDBUtility.getAdministratorRow();
            l_administratorParams.setInstitutionCode("0D");
            l_administratorParams.setPermissionLevel("331");
            l_administratorParams.setBranchCode("001");
            l_administratorParams.setLoginId(33381330003L);
            TestDBUtility.insertWithDel(l_administratorParams);

            TestDBUtility.deleteAll(AdminPermissionRow.TYPE);
            AdminPermissionParams l_adminPermissionParams = TestDBUtility.getAdminPermissionRow();
            l_adminPermissionParams.setInstitutionCode("0D");
            l_adminPermissionParams.setPermissionLevel("331");
            l_adminPermissionParams.setTransactionCategory("C0305");
            l_adminPermissionParams.setUpdatePermissionFlag(BooleanEnum.TRUE);
            TestDBUtility.insertWithDel(l_adminPermissionParams);

            TestDBUtility.deleteAll(ProcessManagementRow.TYPE);
            ProcessManagementParams l_processManagementParams = new ProcessManagementParams();
            l_processManagementParams.setProcessId("0001");
            l_processManagementParams.setInstitutionCode("0D");
            l_processManagementParams.setBranchCode("001");
            TestDBUtility.insertWithDel(l_processManagementParams);

            TestDBUtility.deleteAll(IfoDepositRow.TYPE);

            WEB3AdminIfoDepShortageReferenceRequest l_request =
                new WEB3AdminIfoDepShortageReferenceRequest();
            l_request.searchDate = WEB3DateUtility.getDate("20090309", "yyyyMMdd");
            l_request.branchCode = new String[1];
            l_request.branchCode[0] = "001";
            l_request.uncancelDiv = "0";
            l_request.sortKeys = new WEB3IfoDepShortageSortKey[1];
            l_request.sortKeys[0] = new WEB3IfoDepShortageSortKey();
            l_request.sortKeys[0].keyItem = "branchCode";
            l_request.sortKeys[0].ascDesc = "A";
            l_request.pageIndex = "1";
            l_request.pageSize = "1";

            WEB3AdminIfoDepShortageReferenceServiceImpl l_impl =
                new WEB3AdminIfoDepShortageReferenceServiceImpl();

            WEB3AdminIfoDepShortageReferenceResponse l_response = l_impl.getReferenceScreen(l_request);

            assertEquals(GtlUtils.getSystemTimestamp(), l_response.dispDate);
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME,l_ex);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    /**
     * normal case
     */
    public void testGetReferenceScreen_C0006()
    {
        final String STR_METHOD_NAME = " testGetReferenceScreen_C0006()";
        log.entering(STR_METHOD_NAME);
        try
        {
            LoginInfo l_loginInfo = new LoginInfoForTest();
            MOCK_MANAGER.setIsMockUsed(true);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                    "getLoginInfo",
                    new Class[] {},
                    l_loginInfo);

            Date l_dat = WEB3DateUtility.getDate("20090309", "yyyyMMdd");
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.tc.gentrade.stdimpls.TradingSystemImpl",
                    "getBizDate",
                    new Class[] {},
                    l_dat);

            WEB3IfoDepositCalc l_ifoDepCalc = new WEB3IfoDepositCalcForTest();
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.ifodeposit.WEB3IfoDepositCalcServiceImpl",
                    "getIfoDepositCalc",
                    new Class[]{ WEB3GentradeSubAccount.class },
                    l_ifoDepCalc);

            TestDBUtility.deleteAll(InstitutionRow.TYPE);
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            l_institutionParams.setInstitutionCode("0D");
            l_institutionParams.setInstitutionId(33L);
            TestDBUtility.insertWithDel(l_institutionParams);

            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(333812512246L);
            l_mainAccountParams.setInstitutionCode("0D");
            l_mainAccountParams.setBranchCode("001");
            l_mainAccountParams.setAccountCode("1234567");
            TestDBUtility.insertWithDel(l_mainAccountParams);

            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setAccountId(333812512246L);
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_OPTIONS_SUB_ACCOUNT);
            TestDBUtility.insertWithDel(l_subAccountParams);

            TestDBUtility.deleteAll(AdministratorRow.TYPE);
            AdministratorParams l_administratorParams = TestDBUtility.getAdministratorRow();
            l_administratorParams.setInstitutionCode("0D");
            l_administratorParams.setPermissionLevel("331");
            l_administratorParams.setBranchCode("001");
            l_administratorParams.setLoginId(33381330003L);
            TestDBUtility.insertWithDel(l_administratorParams);

            TestDBUtility.deleteAll(AdminPermissionRow.TYPE);
            AdminPermissionParams l_adminPermissionParams = TestDBUtility.getAdminPermissionRow();
            l_adminPermissionParams.setInstitutionCode("0D");
            l_adminPermissionParams.setPermissionLevel("331");
            l_adminPermissionParams.setTransactionCategory("C0305");
            l_adminPermissionParams.setUpdatePermissionFlag(BooleanEnum.TRUE);
            TestDBUtility.insertWithDel(l_adminPermissionParams);

            TestDBUtility.deleteAll(ProcessManagementRow.TYPE);
            ProcessManagementParams l_processManagementParams = new ProcessManagementParams();
            l_processManagementParams.setProcessId("0001");
            l_processManagementParams.setInstitutionCode("0D");
            l_processManagementParams.setBranchCode("001");
            TestDBUtility.insertWithDel(l_processManagementParams);

            TestDBUtility.deleteAll(IfoDepositRow.TYPE);
            IfoDepositParams l_ifoDepositParams = new IfoDepositParams();
            l_ifoDepositParams.setInstitutionCode("0D");
            l_ifoDepositParams.setBranchCode("001");
            l_ifoDepositParams.setAccountCode("1234567");
            l_ifoDepositParams.setCalcDate(WEB3DateUtility.getDate("20090309", "yyyyMMdd"));
            TestDBUtility.insertWithDel(l_ifoDepositParams);

            WEB3AdminIfoDepShortageReferenceRequest l_request =
                new WEB3AdminIfoDepShortageReferenceRequest();
            l_request.searchDate = WEB3DateUtility.getDate("20090309", "yyyyMMdd");
            l_request.branchCode = new String[1];
            l_request.branchCode[0] = "001";
            l_request.uncancelDiv = "0";
            l_request.sortKeys = new WEB3IfoDepShortageSortKey[1];
            l_request.sortKeys[0] = new WEB3IfoDepShortageSortKey();
            l_request.sortKeys[0].keyItem = "branchCode";
            l_request.sortKeys[0].ascDesc = "A";
            l_request.pageIndex = "1";
            l_request.pageSize = "1";

            WEB3AdminIfoDepShortageReferenceServiceImpl l_impl =
                new WEB3AdminIfoDepShortageReferenceServiceImpl();

            WEB3AdminIfoDepShortageReferenceResponse l_response = l_impl.getReferenceScreen(l_request);

            assertEquals("1", l_response.pageIndex);
            assertEquals("1", l_response.totalPages);
            assertEquals("1", l_response.totalRecords);
            assertEquals(1, l_response.ifoDepShortageInfos.length);
            assertEquals(GtlUtils.getSystemTimestamp(), l_response.dispDate);
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME,l_ex);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    /**
     * 數?排序
     */
    public void testSortDepShortageList_C0001()
    {
        final String STR_METHOD_NAME = " testSortDepShortageList_C0001()";
        log.entering(STR_METHOD_NAME);
        try
        {
            WEB3IfoDepShortageInfo[] l_sortDepShortageList = new WEB3IfoDepShortageInfo[3];
            l_sortDepShortageList[0] = new WEB3IfoDepShortageInfo();
            l_sortDepShortageList[0].branchCode = "001";
            l_sortDepShortageList[0].accountCode = "012345";
            l_sortDepShortageList[0].accountName = "name0";
            l_sortDepShortageList[0].curNonPayAmt = "11";
            l_sortDepShortageList[0].curIfoDepositNecessaryAmt = "12";
            l_sortDepShortageList[0].contractExistFlag = true;
            l_sortDepShortageList[0].orderExistFlag = false;

            l_sortDepShortageList[1] = new WEB3IfoDepShortageInfo();
            l_sortDepShortageList[1].branchCode = "001";
            l_sortDepShortageList[1].accountCode = "123456";
            l_sortDepShortageList[1].accountName = "name1";
            l_sortDepShortageList[1].curNonPayAmt = "21";
            l_sortDepShortageList[1].curIfoDepositNecessaryAmt = "22";
            l_sortDepShortageList[1].contractExistFlag = false;
            l_sortDepShortageList[1].orderExistFlag = true;

            l_sortDepShortageList[2] = new WEB3IfoDepShortageInfo();
            l_sortDepShortageList[2].branchCode = "002";
            l_sortDepShortageList[2].accountCode = "234567";
            l_sortDepShortageList[2].accountName = "name2";
            l_sortDepShortageList[2].curNonPayAmt = "31";
            l_sortDepShortageList[2].curIfoDepositNecessaryAmt = "32";
            l_sortDepShortageList[2].contractExistFlag = false;
            l_sortDepShortageList[2].orderExistFlag = false;

            WEB3IfoDepShortageSortKey[] l_sortKeys = new WEB3IfoDepShortageSortKey[2];
            l_sortKeys[0] = new WEB3IfoDepShortageSortKey();
            l_sortKeys[0].keyItem = "branchCode";
            l_sortKeys[0].ascDesc = "D";

            l_sortKeys[1] = new WEB3IfoDepShortageSortKey();
            l_sortKeys[1].keyItem = "accountCode";
            l_sortKeys[1].ascDesc = "A";

            WEB3AdminIfoDepShortageReferenceServiceImpl l_impl =
                new WEB3AdminIfoDepShortageReferenceServiceImpl();

            WEB3IfoDepShortageInfo[] l_infos = l_impl.sortDepShortageList(l_sortDepShortageList, l_sortKeys);

            assertEquals("002", l_infos[0].branchCode);
            assertEquals("012345", l_infos[1].accountCode);
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME,l_ex);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    /**
     * boolean排序
     */
    public void testSortDepShortageList_C0002()
    {
        final String STR_METHOD_NAME = " testSortDepShortageList_C0002()";
        log.entering(STR_METHOD_NAME);
        try
        {
            WEB3IfoDepShortageInfo[] l_sortDepShortageList = new WEB3IfoDepShortageInfo[3];
            l_sortDepShortageList[0] = new WEB3IfoDepShortageInfo();
            l_sortDepShortageList[0].branchCode = "001";
            l_sortDepShortageList[0].accountCode = "012345";
            l_sortDepShortageList[0].accountName = "name0";
            l_sortDepShortageList[0].curNonPayAmt = "11";
            l_sortDepShortageList[0].curIfoDepositNecessaryAmt = "12";
            l_sortDepShortageList[0].contractExistFlag = true;
            l_sortDepShortageList[0].orderExistFlag = false;

            l_sortDepShortageList[1] = new WEB3IfoDepShortageInfo();
            l_sortDepShortageList[1].branchCode = "001";
            l_sortDepShortageList[1].accountCode = "123456";
            l_sortDepShortageList[1].accountName = "name1";
            l_sortDepShortageList[1].curNonPayAmt = "21";
            l_sortDepShortageList[1].curIfoDepositNecessaryAmt = "22";
            l_sortDepShortageList[1].contractExistFlag = false;
            l_sortDepShortageList[1].orderExistFlag = true;

            l_sortDepShortageList[2] = new WEB3IfoDepShortageInfo();
            l_sortDepShortageList[2].branchCode = "002";
            l_sortDepShortageList[2].accountCode = "234567";
            l_sortDepShortageList[2].accountName = "name2";
            l_sortDepShortageList[2].curNonPayAmt = "31";
            l_sortDepShortageList[2].curIfoDepositNecessaryAmt = "32";
            l_sortDepShortageList[2].contractExistFlag = false;
            l_sortDepShortageList[2].orderExistFlag = false;

            WEB3IfoDepShortageSortKey[] l_sortKeys = new WEB3IfoDepShortageSortKey[2];
            l_sortKeys[0] = new WEB3IfoDepShortageSortKey();
            l_sortKeys[0].keyItem = "contractExistFlag";
            l_sortKeys[0].ascDesc = "D";

            l_sortKeys[1] = new WEB3IfoDepShortageSortKey();
            l_sortKeys[1].keyItem = "orderExistFlag";
            l_sortKeys[1].ascDesc = "A";

            WEB3AdminIfoDepShortageReferenceServiceImpl l_impl =
                new WEB3AdminIfoDepShortageReferenceServiceImpl();

            WEB3IfoDepShortageInfo[] l_infos = l_impl.sortDepShortageList(l_sortDepShortageList, l_sortKeys);

            assertEquals(true, l_infos[0].contractExistFlag);
            assertEquals(false, l_infos[1].orderExistFlag);
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME,l_ex);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    /**
     * 混合排序
     */
    public void testSortDepShortageList_C0003()
    {
        final String STR_METHOD_NAME = " testSortDepShortageList_C0003()";
        log.entering(STR_METHOD_NAME);
        try
        {
            WEB3IfoDepShortageInfo[] l_sortDepShortageList = new WEB3IfoDepShortageInfo[3];
            l_sortDepShortageList[0] = new WEB3IfoDepShortageInfo();
            l_sortDepShortageList[0].branchCode = "001";
            l_sortDepShortageList[0].accountCode = "012345";
            l_sortDepShortageList[0].accountName = "name0";
            l_sortDepShortageList[0].curNonPayAmt = "11";
            l_sortDepShortageList[0].curIfoDepositNecessaryAmt = "12";
            l_sortDepShortageList[0].contractExistFlag = true;
            l_sortDepShortageList[0].orderExistFlag = false;

            l_sortDepShortageList[1] = new WEB3IfoDepShortageInfo();
            l_sortDepShortageList[1].branchCode = "001";
            l_sortDepShortageList[1].accountCode = "123456";
            l_sortDepShortageList[1].accountName = "name1";
            l_sortDepShortageList[1].curNonPayAmt = "31";
            l_sortDepShortageList[1].curIfoDepositNecessaryAmt = "22";
            l_sortDepShortageList[1].contractExistFlag = false;
            l_sortDepShortageList[1].orderExistFlag = true;

            l_sortDepShortageList[2] = new WEB3IfoDepShortageInfo();
            l_sortDepShortageList[2].branchCode = "002";
            l_sortDepShortageList[2].accountCode = "234567";
            l_sortDepShortageList[2].accountName = "name2";
            l_sortDepShortageList[2].curNonPayAmt = "31";
            l_sortDepShortageList[2].curIfoDepositNecessaryAmt = "32";
            l_sortDepShortageList[2].contractExistFlag = false;
            l_sortDepShortageList[2].orderExistFlag = false;

            WEB3IfoDepShortageSortKey[] l_sortKeys = new WEB3IfoDepShortageSortKey[2];
            l_sortKeys[0] = new WEB3IfoDepShortageSortKey();
            l_sortKeys[0].keyItem = "curNonPayAmt";
            l_sortKeys[0].ascDesc = "D";

            l_sortKeys[1] = new WEB3IfoDepShortageSortKey();
            l_sortKeys[1].keyItem = "orderExistFlag";
            l_sortKeys[1].ascDesc = "A";

            WEB3AdminIfoDepShortageReferenceServiceImpl l_impl =
                new WEB3AdminIfoDepShortageReferenceServiceImpl();

            WEB3IfoDepShortageInfo[] l_infos = l_impl.sortDepShortageList(l_sortDepShortageList, l_sortKeys);

            assertEquals("11", l_infos[2].curNonPayAmt);
            assertEquals(false, l_infos[0].orderExistFlag);
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME,l_ex);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    /**
     * validate権限失敗
     * WEB3ErrorCatalog.BUSINESS_ERROR_01056
     */
    public void testGetInputScreen_C0001()
    {
        final String STR_METHOD_NAME = " testGetInputScreen_C0001()";
        log.entering(STR_METHOD_NAME);
        try
        {
            LoginInfo l_loginInfo = new LoginInfoForTest();
            MOCK_MANAGER.setIsMockUsed(true);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                    "getLoginInfo",
                    new Class[] {},
                    l_loginInfo);

            TestDBUtility.deleteAll(AdministratorRow.TYPE);
            AdministratorParams l_administratorParams = TestDBUtility.getAdministratorRow();
            l_administratorParams.setLoginId(33381330003L);
            TestDBUtility.insertWithDel(l_administratorParams);

            TestDBUtility.deleteAll(AdminPermissionRow.TYPE);

            WEB3AdminIfoDepShortageReferenceServiceImpl l_impl =
                new WEB3AdminIfoDepShortageReferenceServiceImpl();

            WEB3AdminIfoDepShortageRefInputRequest l_request =
                new WEB3AdminIfoDepShortageRefInputRequest();

            l_impl.getInputScreen(l_request);

            fail();
        }
        catch (WEB3BusinessLayerException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_01056, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME,l_ex);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    /**
     * normal case
     */
    public void testGetInputScreen_C0002()
    {
        final String STR_METHOD_NAME = " testGetInputScreen_C0002()";
        log.entering(STR_METHOD_NAME);
        try
        {
            LoginInfo l_loginInfo = new LoginInfoForTest();
            MOCK_MANAGER.setIsMockUsed(true);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                    "getLoginInfo",
                    new Class[] {},
                    l_loginInfo);

            Date l_dat = WEB3DateUtility.getDate("20090309", "yyyyMMdd");
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.tc.gentrade.stdimpls.TradingSystemImpl",
                    "getBizDate",
                    new Class[] {},
                    l_dat);

            TestDBUtility.deleteAll(AdministratorRow.TYPE);
            AdministratorParams l_administratorParams = TestDBUtility.getAdministratorRow();
            l_administratorParams.setInstitutionCode("0D");
            l_administratorParams.setPermissionLevel("331");
            l_administratorParams.setBranchCode("001");
            l_administratorParams.setLoginId(33381330003L);
            TestDBUtility.insertWithDel(l_administratorParams);

            TestDBUtility.deleteAll(AdminPermissionRow.TYPE);
            AdminPermissionParams l_adminPermissionParams = TestDBUtility.getAdminPermissionRow();
            l_adminPermissionParams.setInstitutionCode("0D");
            l_adminPermissionParams.setPermissionLevel("331");
            l_adminPermissionParams.setTransactionCategory("C0305");
            l_adminPermissionParams.setUpdatePermissionFlag(BooleanEnum.TRUE);
            TestDBUtility.insertWithDel(l_adminPermissionParams);

            WEB3AdminIfoDepShortageReferenceServiceImpl l_impl =
                new WEB3AdminIfoDepShortageReferenceServiceImpl();

            WEB3AdminIfoDepShortageRefInputRequest l_request =
                new WEB3AdminIfoDepShortageRefInputRequest();

            WEB3AdminIfoDepShortageRefInputResponse l_response = l_impl.getInputScreen(l_request);

            assertEquals("20090306", WEB3DateUtility.formatDate(l_response.searchDateList[0], "yyyyMMdd"));
            assertEquals("20090309", WEB3DateUtility.formatDate(l_response.searchDateList[1], "yyyyMMdd"));
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME,l_ex);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    /**
     * パラメータ値不正。
     * WEB3ErrorCatalog.SYSTEM_ERROR_80017
     */
    public void testExecute_C0001()
    {
        final String STR_METHOD_NAME = " testExecute_C0001()";
        log.entering(STR_METHOD_NAME);
        try
        {
            WEB3AdminIfoDepShortageReferenceServiceImpl l_impl =
                new WEB3AdminIfoDepShortageReferenceServiceImpl();

            l_impl.execute(null);

            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(STR_METHOD_NAME,l_ex);
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80017, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME,l_ex);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    /**
     * パラメータタイプ不正。
     * WEB3ErrorCatalog.SYSTEM_ERROR_80018
     */
    public void testExecute_C0002()
    {
        final String STR_METHOD_NAME = " testExecute_C0002()";
        log.entering(STR_METHOD_NAME);
        try
        {
            WEB3AdminIfoDepShortageReferenceServiceImpl l_impl =
                new WEB3AdminIfoDepShortageReferenceServiceImpl();

            WEB3AdminIfoManualLapseConfirmRequest l_request = new WEB3AdminIfoManualLapseConfirmRequest();
            l_impl.execute(l_request);

            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(STR_METHOD_NAME,l_ex);
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80018, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME,l_ex);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    /**
     * WEB3AdminIfoDepShortageRefInputRequest
     */
    public void testExecute_C0003()
    {
        final String STR_METHOD_NAME = " testExecute_C0003()";
        log.entering(STR_METHOD_NAME);
        try
        {
            LoginInfo l_loginInfo = new LoginInfoForTest();
            MOCK_MANAGER.setIsMockUsed(true);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                    "getLoginInfo",
                    new Class[] {},
                    l_loginInfo);

            Date l_dat = WEB3DateUtility.getDate("20090309", "yyyyMMdd");
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.tc.gentrade.stdimpls.TradingSystemImpl",
                    "getBizDate",
                    new Class[] {},
                    l_dat);

            TestDBUtility.deleteAll(AdministratorRow.TYPE);
            AdministratorParams l_administratorParams = TestDBUtility.getAdministratorRow();
            l_administratorParams.setInstitutionCode("0D");
            l_administratorParams.setPermissionLevel("331");
            l_administratorParams.setBranchCode("001");
            l_administratorParams.setLoginId(33381330003L);
            TestDBUtility.insertWithDel(l_administratorParams);

            TestDBUtility.deleteAll(AdminPermissionRow.TYPE);
            AdminPermissionParams l_adminPermissionParams = TestDBUtility.getAdminPermissionRow();
            l_adminPermissionParams.setInstitutionCode("0D");
            l_adminPermissionParams.setPermissionLevel("331");
            l_adminPermissionParams.setTransactionCategory("C0305");
            l_adminPermissionParams.setUpdatePermissionFlag(BooleanEnum.TRUE);
            TestDBUtility.insertWithDel(l_adminPermissionParams);

            WEB3AdminIfoDepShortageReferenceServiceImpl l_impl =
                new WEB3AdminIfoDepShortageReferenceServiceImpl();

            WEB3AdminIfoDepShortageRefInputRequest l_request =
                new WEB3AdminIfoDepShortageRefInputRequest();

            WEB3AdminIfoDepShortageRefInputResponse l_response =
                (WEB3AdminIfoDepShortageRefInputResponse)l_impl.execute(l_request);

            assertEquals("20090306", WEB3DateUtility.formatDate(l_response.searchDateList[0], "yyyyMMdd"));
            assertEquals("20090309", WEB3DateUtility.formatDate(l_response.searchDateList[1], "yyyyMMdd"));
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME,l_ex);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    /**
     * WEB3AdminIfoDepShortageReferenceRequest
     */
    public void testExecute_C0004()
    {
        final String STR_METHOD_NAME = " testExecute_C0004()";
        log.entering(STR_METHOD_NAME);
        try
        {
            LoginInfo l_loginInfo = new LoginInfoForTest();
            MOCK_MANAGER.setIsMockUsed(true);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                    "getLoginInfo",
                    new Class[] {},
                    l_loginInfo);

            Date l_dat = WEB3DateUtility.getDate("20090309", "yyyyMMdd");
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.tc.gentrade.stdimpls.TradingSystemImpl",
                    "getBizDate",
                    new Class[] {},
                    l_dat);

            WEB3IfoDepositCalc l_ifoDepCalc = new WEB3IfoDepositCalcForTest();
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.ifodeposit.WEB3IfoDepositCalcServiceImpl",
                    "getIfoDepositCalc",
                    new Class[]{ WEB3GentradeSubAccount.class },
                    l_ifoDepCalc);

            TestDBUtility.deleteAll(InstitutionRow.TYPE);
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            l_institutionParams.setInstitutionCode("0D");
            l_institutionParams.setInstitutionId(33L);
            TestDBUtility.insertWithDel(l_institutionParams);

            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(333812512246L);
            l_mainAccountParams.setInstitutionCode("0D");
            l_mainAccountParams.setBranchCode("001");
            l_mainAccountParams.setAccountCode("1234567");
            TestDBUtility.insertWithDel(l_mainAccountParams);

            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setAccountId(333812512246L);
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_OPTIONS_SUB_ACCOUNT);
            TestDBUtility.insertWithDel(l_subAccountParams);

            TestDBUtility.deleteAll(AdministratorRow.TYPE);
            AdministratorParams l_administratorParams = TestDBUtility.getAdministratorRow();
            l_administratorParams.setInstitutionCode("0D");
            l_administratorParams.setPermissionLevel("331");
            l_administratorParams.setBranchCode("001");
            l_administratorParams.setLoginId(33381330003L);
            TestDBUtility.insertWithDel(l_administratorParams);

            TestDBUtility.deleteAll(AdminPermissionRow.TYPE);
            AdminPermissionParams l_adminPermissionParams = TestDBUtility.getAdminPermissionRow();
            l_adminPermissionParams.setInstitutionCode("0D");
            l_adminPermissionParams.setPermissionLevel("331");
            l_adminPermissionParams.setTransactionCategory("C0305");
            l_adminPermissionParams.setUpdatePermissionFlag(BooleanEnum.TRUE);
            TestDBUtility.insertWithDel(l_adminPermissionParams);

            TestDBUtility.deleteAll(ProcessManagementRow.TYPE);
            ProcessManagementParams l_processManagementParams = new ProcessManagementParams();
            l_processManagementParams.setProcessId("0001");
            l_processManagementParams.setInstitutionCode("0D");
            l_processManagementParams.setBranchCode("001");
            TestDBUtility.insertWithDel(l_processManagementParams);

            TestDBUtility.deleteAll(IfoDepositRow.TYPE);
            IfoDepositParams l_ifoDepositParams = new IfoDepositParams();
            l_ifoDepositParams.setInstitutionCode("0D");
            l_ifoDepositParams.setBranchCode("001");
            l_ifoDepositParams.setAccountCode("1234567");
            l_ifoDepositParams.setCalcDate(WEB3DateUtility.getDate("20090309", "yyyyMMdd"));
            TestDBUtility.insertWithDel(l_ifoDepositParams);

            WEB3AdminIfoDepShortageReferenceRequest l_request =
                new WEB3AdminIfoDepShortageReferenceRequest();
            l_request.searchDate = WEB3DateUtility.getDate("20090309", "yyyyMMdd");
            l_request.branchCode = new String[1];
            l_request.branchCode[0] = "001";
            l_request.uncancelDiv = "0";
            l_request.sortKeys = new WEB3IfoDepShortageSortKey[1];
            l_request.sortKeys[0] = new WEB3IfoDepShortageSortKey();
            l_request.sortKeys[0].keyItem = "branchCode";
            l_request.sortKeys[0].ascDesc = "A";
            l_request.pageIndex = "1";
            l_request.pageSize = "1";

            WEB3AdminIfoDepShortageReferenceServiceImpl l_impl =
                new WEB3AdminIfoDepShortageReferenceServiceImpl();

            WEB3AdminIfoDepShortageReferenceResponse l_response =
                (WEB3AdminIfoDepShortageReferenceResponse)l_impl.execute(l_request);

            assertEquals("1", l_response.pageIndex);
            assertEquals("1", l_response.totalPages);
            assertEquals("1", l_response.totalRecords);
            assertEquals(1, l_response.ifoDepShortageInfos.length);
            assertEquals(GtlUtils.getSystemTimestamp(), l_response.dispDate);
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME,l_ex);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    /**
     * WEB3IfoDepositCalcForTest
     */
    private class WEB3IfoDepositCalcForTest extends WEB3IfoDepositCalc
    {
        public double calcNextBizDateDemandAmount()
        {
            return 11;
        }
        public double getCurrentBizDateDemandAmount()
        {
            return 22;
        }
        public double calcNonPayAmount()
        {
            return 33;
        }
        public double calcIfoDepositRequiredAmount()
        {
            return 44;
        }
        public double calcBuyContractQty(int l_intReservedDate)
        {
            return 55;
        }
        public double calcSellContractQty(int l_intReservedDate)
        {
            return 66;
        }
        public double calcBuyOrderQty(int l_intReservedDate)
        {
            return 77;
        }
        public double calcSellOrderQty(int l_intReservedDate)
        {
            return 88;
        }
        public WEB3IfoDepositCalcCondition getIfoDepositCalcCondition()
        {
            WEB3IfoDepositCalcCondition l_condition = new WEB3IfoDepositCalcCondition();
            return l_condition;
        }
    }

    /**
     * LoginInfoForTest
     */
    private class LoginInfoForTest implements LoginInfo
    {

        public LoginTypeInfo getLoginTypeInfo()
        {
            // TODO Auto-generated method stub
            return null;
        }

        public long getLoginId()
        {
            // TODO Auto-generated method stub
            return 33381330003L;
        }

        public long getLoginTypeId()
        {
            // TODO Auto-generated method stub
            return 0;
        }

        public String getUsername()
        {
            // TODO Auto-generated method stub
            return null;
        }

        public String getInitialPassword()
        {
            // TODO Auto-generated method stub
            return null;
        }

        public Set getSubordinateLoginGroups()
        {
            // TODO Auto-generated method stub
            return null;
        }

        public boolean isDisabled()
        {
            // TODO Auto-generated method stub
            return false;
        }

        public Set getReachableAccountIds()
        {
            // TODO Auto-generated method stub
            return null;
        }

        public Set getReachableLoginIds()
        {
            // TODO Auto-generated method stub
            return null;
        }

        public Set getReachableLogins()
        {
            // TODO Auto-generated method stub
            return null;
        }

        public Map getAttributes()
        {
            // TODO Auto-generated method stub
            return null;
        }

        public boolean isAccountReachable(long arg0)
        {
            // TODO Auto-generated method stub
            return false;
        }

        public boolean hasFailedLogin()
        {
            // TODO Auto-generated method stub
            return false;
        }

        public int getFailureCount()
        {
            // TODO Auto-generated method stub
            return 0;
        }

        public Date getLastFailureTimestamp()
        {
            // TODO Auto-generated method stub
            return null;
        }
    }
    
    
    /**
     * リクエストデータ.検索日付が「当営業日」の場合、 calc翌日請求額( )の戻り値をセット
     * is清算値速報受信済()== true
     * is証拠金不足メール送信済()== false
     * a-c >0の場合　@trueをセット（※）
     * c >0の場合　@trueをセット（※）
     */
    public void test_createDepShortageList_0001()
    {
        TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);
        ProcessManagementParams l_processManagementParams = new ProcessManagementParams();
        l_processManagementParams.setProcessId("0008");
        l_processManagementParams.setInstitutionCode("0D");    
        l_processManagementParams.setBranchCode("ch");     
        l_processManagementParams.setStatus("1");         
        l_processManagementParams.setLastUpdater("dd");
        l_processManagementParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
        
        WEB3AdminIfoDepShortageReferenceServiceImpl l_service = new WEB3AdminIfoDepShortageReferenceServiceImpl();
        try
        {
            WEB3IfoDepositCalc1 l_calc = new WEB3IfoDepositCalc1();

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.ifodeposit.WEB3IfoDepositCalcServiceImpl",
                    "getIfoDepositCalc",
                    new Class[]
                    { WEB3GentradeSubAccount.class },
                   l_calc);
            
            WEB3AdminIfoDepShortageReferenceRequest l_request = new WEB3AdminIfoDepShortageReferenceRequest();
            //リクエストデータ ．検索日付 が　@「当営業日」　@の場合
            l_request.searchDate = GtlUtils.getTradingSystem().getBizDate();
            l_request.branchCode = new String[]{"ch"};
            
            l_request.uncancelDiv = "1";
            
            TestDBUtility.deleteAll(ProcessManagementParams.TYPE);
            TestDBUtility.insertWithDel(l_processManagementParams);
            
            TestDBUtility.deleteAll(MainAccountParams.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountCode("1234567");
            l_mainAccountParams.setBranchCode("ch");
            l_mainAccountParams.setInstitutionCode("0D");
            TestDBUtility.insertWithDel(l_mainAccountParams);
            
            TestDBUtility.deleteAll(SubAccountParams.TYPE);
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_OPTIONS_SUB_ACCOUNT);
            l_subAccountParams.setAccountId(333812512246L);
            TestDBUtility.insertWithDel(l_subAccountParams);
            
            IfoDepositParams l_ifoDepositParams = new IfoDepositParams();
            l_ifoDepositParams.setInstitutionCode("0D");
            l_ifoDepositParams.setBranchCode("ch");
            l_ifoDepositParams.setAccountCode("1234567");
            l_ifoDepositParams.setCalcDate(GtlUtils.getSystemTimestamp());
            IfoDepositRow[] l_depInfoList = new IfoDepositRow[]{l_ifoDepositParams};
            
            WEB3IfoDepShortageInfo[] l_ifoDepShortageInfos = l_service.createDepShortageList("0D", l_depInfoList, l_request);
            WEB3IfoDepShortageInfo l_ifoDepShortageInfo = l_ifoDepShortageInfos[0];
            assertEquals(l_ifoDepShortageInfo.accountCode, "123456");
            assertEquals(l_ifoDepShortageInfo.branchCode, "ch");
            assertEquals(l_ifoDepShortageInfo.accountName, "内藤　@四郎");
            //請求額  ＝
            //リクエストデータ.検索日付が「当営業日」の場合、 calc翌日請求額( )の戻り値をセット
            assertEquals(l_ifoDepShortageInfo.claimAmount + "", "8");
            //現在未入金額  ＝ 
            // （ is清算値速報受信済()== true && is証拠金不足メール送信済()== false）
            //　@|| (リクエストデータ.検索日付 が「前営業日」&& is証拠金不足メール送信済()==true)
            // 　@の場合は nullをセット
            // 上記以外の場合　@calc未入金額( )の戻り値をセット。
            assertNull(l_ifoDepShortageInfo.curNonPayAmt);
            //現在証拠金所要額          ＝　@calc証拠金所要額( )
            assertEquals(l_ifoDepShortageInfo.curIfoDepositNecessaryAmt + "", "7");
            //建玉有無フラグ            ＝  (*3)
            //a-c >0の場合　@trueをセット（※）
            //上記以外の場合　@falseをセット
            assertTrue(l_ifoDepShortageInfo.contractExistFlag);
            //注文有無フラグ            ＝  (*4)
            //  c >0またはd >0の場合　@trueをセット（※）
            //上記以外の場合　@falseをセット
            assertTrue(l_ifoDepShortageInfo.orderExistFlag);
        }
        catch (WEB3BaseException e)
        {
            e.printStackTrace();
            fail();
        }
        catch (Exception e)
        {
            e.printStackTrace();
            fail();
        }
        
    }
    
    /**
     * リクエストデータ.検索日付が「前営業日」の場合、get当日請求額( )の戻り値をセット
     * リクエストデータ.検索日付 が「前営業日」&& is証拠金不足メール送信済()==true
     * b-d >0の場合　@trueをセット（※）
     * d >0の場合　@trueをセット（※）
     */
    public void test_createDepShortageList_0002()
    {
        TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);
        ProcessManagementParams l_processManagementParams = new ProcessManagementParams();
        l_processManagementParams.setProcessId("0001");
        l_processManagementParams.setInstitutionCode("0D");    
        l_processManagementParams.setBranchCode("ch");     
        l_processManagementParams.setStatus("1");         
        l_processManagementParams.setLastUpdater("dd");
        l_processManagementParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
        
        WEB3AdminIfoDepShortageReferenceServiceImpl l_service = new WEB3AdminIfoDepShortageReferenceServiceImpl();
        try
        {
            WEB3IfoDepositCalc0 l_calc = new WEB3IfoDepositCalc0();

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.ifodeposit.WEB3IfoDepositCalcServiceImpl",
                    "getIfoDepositCalc",
                    new Class[]
                    { WEB3GentradeSubAccount.class },
                   l_calc);
            
            WEB3AdminIfoDepShortageReferenceRequest l_request = new WEB3AdminIfoDepShortageReferenceRequest();
            //リクエストデータ.検索日付が「前営業日」の場合
            Date l_datBizDate = GtlUtils.getTradingSystem().getBizDate();

            l_request.searchDate =
                new WEB3GentradeBizDate(
                    new Timestamp(l_datBizDate.getTime())).roll(-1);
            l_request.branchCode = new String[]{"ch"};
            
            l_request.uncancelDiv = "1";
            
            TestDBUtility.deleteAll(ProcessManagementParams.TYPE);
            TestDBUtility.insertWithDel(l_processManagementParams);
            
            TestDBUtility.deleteAll(MainAccountParams.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountCode("1234567");
            l_mainAccountParams.setBranchCode("ch");
            l_mainAccountParams.setInstitutionCode("0D");
            TestDBUtility.insertWithDel(l_mainAccountParams);
            
            TestDBUtility.deleteAll(SubAccountParams.TYPE);
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_OPTIONS_SUB_ACCOUNT);
            l_subAccountParams.setAccountId(333812512246L);
            TestDBUtility.insertWithDel(l_subAccountParams);
            
            IfoDepositParams l_ifoDepositParams = new IfoDepositParams();
            l_ifoDepositParams.setInstitutionCode("0D");
            l_ifoDepositParams.setBranchCode("ch");
            l_ifoDepositParams.setAccountCode("1234567");
            l_ifoDepositParams.setCalcDate(GtlUtils.getSystemTimestamp());
            IfoDepositRow[] l_depInfoList = new IfoDepositRow[]{l_ifoDepositParams};
            
            WEB3IfoDepShortageInfo[] l_ifoDepShortageInfos = l_service.createDepShortageList("0D", l_depInfoList, l_request);
            WEB3IfoDepShortageInfo l_ifoDepShortageInfo = l_ifoDepShortageInfos[0];
            assertEquals(l_ifoDepShortageInfo.accountCode, "123456");
            assertEquals(l_ifoDepShortageInfo.branchCode, "ch");
            assertEquals(l_ifoDepShortageInfo.accountName, "内藤　@四郎");
            //請求額  ＝
            //リクエストデータ.検索日付が「前営業日」の場合、get当日請求額( )の戻り値をセット
            assertEquals(l_ifoDepShortageInfo.claimAmount + "", "7");
            //現在未入金額  ＝ 
            // （ is清算値速報受信済()== true && is証拠金不足メール送信済()== false）
            //　@|| (リクエストデータ.検索日付 が「前営業日」&& is証拠金不足メール送信済()==true)
            // 　@の場合は nullをセット
            // 上記以外の場合　@calc未入金額( )の戻り値をセット。
            assertNull(l_ifoDepShortageInfo.curNonPayAmt);
            //現在証拠金所要額          ＝　@calc証拠金所要額( )
            assertEquals(l_ifoDepShortageInfo.curIfoDepositNecessaryAmt + "", "7");
            //建玉有無フラグ            ＝  (*3)
            //b-d >0の場合　@trueをセット（※）
            //上記以外の場合　@falseをセット
            assertTrue(l_ifoDepShortageInfo.contractExistFlag);
            //注文有無フラグ            ＝  (*4)
            //  c >0またはd >0の場合　@trueをセット（※）
            //上記以外の場合　@falseをセット
            assertTrue(l_ifoDepShortageInfo.orderExistFlag);
        }
        catch (WEB3BaseException e)
        {
            e.printStackTrace();
            fail();
        }
        catch (Exception e)
        {
            e.printStackTrace();
            fail();
        }
        
    }
    
    /**
     * リクエストデータ.検索日付が「前営業日」の場合、get当日請求額( )の戻り値をセット
     * !{（ is清算値速報受信済()== true && is証拠金不足メール送信済()== false）
     * || (リクエストデータ.検索日付 が「前営業日」&& is証拠金不足メール送信済()==true)}
     * a-c <0 && b-d <0の場合　@falseをセット（※）
     *  c < 0 && d < 0の場合　@falseをセット（※）
     */
    public void test_createDepShortageList_0003()
    {
        TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);
        ProcessManagementParams l_processManagementParams = new ProcessManagementParams();
        l_processManagementParams.setProcessId("0001");
        l_processManagementParams.setInstitutionCode("0D");    
        l_processManagementParams.setBranchCode("ch");     
        l_processManagementParams.setStatus("1");         
        l_processManagementParams.setLastUpdater("dd");
        l_processManagementParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
        
        WEB3AdminIfoDepShortageReferenceServiceImpl l_service = new WEB3AdminIfoDepShortageReferenceServiceImpl();
        try
        {
            WEB3IfoDepositCalc2 l_calc = new WEB3IfoDepositCalc2();

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.ifodeposit.WEB3IfoDepositCalcServiceImpl",
                    "getIfoDepositCalc",
                    new Class[]
                    { WEB3GentradeSubAccount.class },
                   l_calc);
            
            WEB3AdminIfoDepShortageReferenceRequest l_request = new WEB3AdminIfoDepShortageReferenceRequest();
            //リクエストデータ.検索日付が「当営業日」の場合
            Date l_datBizDate = GtlUtils.getTradingSystem().getBizDate();

            l_request.searchDate = l_datBizDate;
            l_request.branchCode = new String[]{"ch"};
            
            l_request.uncancelDiv = "1";
            
            TestDBUtility.deleteAll(ProcessManagementParams.TYPE);
            TestDBUtility.insertWithDel(l_processManagementParams);
            
            TestDBUtility.deleteAll(MainAccountParams.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountCode("1234567");
            l_mainAccountParams.setBranchCode("ch");
            l_mainAccountParams.setInstitutionCode("0D");
            TestDBUtility.insertWithDel(l_mainAccountParams);
            
            TestDBUtility.deleteAll(SubAccountParams.TYPE);
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_OPTIONS_SUB_ACCOUNT);
            l_subAccountParams.setAccountId(333812512246L);
            TestDBUtility.insertWithDel(l_subAccountParams);
            
            IfoDepositParams l_ifoDepositParams = new IfoDepositParams();
            l_ifoDepositParams.setInstitutionCode("0D");
            l_ifoDepositParams.setBranchCode("ch");
            l_ifoDepositParams.setAccountCode("1234567");
            l_ifoDepositParams.setCalcDate(GtlUtils.getSystemTimestamp());
            IfoDepositRow[] l_depInfoList = new IfoDepositRow[]{l_ifoDepositParams};
            
            WEB3IfoDepShortageInfo[] l_ifoDepShortageInfos = l_service.createDepShortageList("0D", l_depInfoList, l_request);
            WEB3IfoDepShortageInfo l_ifoDepShortageInfo = l_ifoDepShortageInfos[0];
            assertEquals(l_ifoDepShortageInfo.accountCode, "123456");
            assertEquals(l_ifoDepShortageInfo.branchCode, "ch");
            assertEquals(l_ifoDepShortageInfo.accountName, "内藤　@四郎");
            //請求額  ＝
            //リクエストデータ.検索日付が「当営業日」の場合、get当日請求額( )の戻り値をセット
            assertEquals(l_ifoDepShortageInfo.claimAmount + "", "8");
            //現在未入金額  ＝ 
            // !{（ is清算値速報受信済()== true && is証拠金不足メール送信済()== false）
            //　@|| (リクエストデータ.検索日付 が「前営業日」&& is証拠金不足メール送信済()==true)}
            // 　@の場合は nullをセット
            // 上記以外の場合　@calc未入金額( )の戻り値をセット。
            assertEquals(l_ifoDepShortageInfo.curNonPayAmt + "", "5");
            //現在証拠金所要額          ＝　@calc証拠金所要額( )
            assertEquals(l_ifoDepShortageInfo.curIfoDepositNecessaryAmt + "", "4");
            //建玉有無フラグ            ＝  (*3)
            //a-c <0 && b-d <0の場合　@falseをセット（※）
            assertFalse(l_ifoDepShortageInfo.contractExistFlag);
            //注文有無フラグ            ＝  (*4)
            //c < 0 && d < 0の場合　@falseをセット（※）
            assertFalse(l_ifoDepShortageInfo.orderExistFlag);
        }
        catch (WEB3BaseException e)
        {
            e.printStackTrace();
            fail();
        }
        catch (Exception e)
        {
            e.printStackTrace();
            fail();
        }
        
    }
    
    private class WEB3IfoDepositCalc2 extends WEB3IfoDepositCalc
    {
        /**
         * calc翌日請求額
         */
        public double calcNextBizDateDemandAmount()
        {
            return 8D;
        }
        
        /**
         * get当日請求額
         */
        public double getCurrentBizDateDemandAmount()
        {
            return 7D;
        }
        
        /**
         * calc未入金額
         */
        public double calcNonPayAmount()
        {
            return 5D;
        }
        /**
         * calc証拠金所要額
         */
        public double calcIfoDepositRequiredAmount()
        {
            return 4D;
        }
        /**
         * calc買ポジション建玉(指定日 : int)
         */
        public double calcBuyContractQty(int l_intReservedDate)
        {
            return -1D;
        }
        /**
         * calc売ポジション建玉(指定日 : int)
         */
        public double calcSellContractQty(int l_intReservedDate)
        {
            return -1D;
        }
        /**
         * calc注文中買ポジション建玉(指定日 : int)
         */
        public double calcBuyOrderQty(int l_intReservedDate)
        {
            return -1D;
        }
        /**
         * calc注文中売ポジション建玉(指定日 : int)
         */
        public double calcSellOrderQty(int l_intReservedDate)
        {
            return -1D;
        }
        public WEB3IfoDepositCalcCondition getIfoDepositCalcCondition()
        {
            WEB3IfoDepositCalcCondition l_condition = new WEB3IfoDepositCalcCondition();
            return l_condition;
        }
    }
    
    private class WEB3IfoDepositCalc0 extends WEB3IfoDepositCalc
    {
        /**
         * calc翌日請求額
         */
        public double calcNextBizDateDemandAmount()
        {
            return 8D;
        }
        
        /**
         * get当日請求額
         */
        public double getCurrentBizDateDemandAmount()
        {
            return 7D;
        }
        
        /**
         * calc未入金額
         */
        public double calcNonPayAmount()
        {
            return 7D;
        }
        /**
         * calc証拠金所要額
         */
        public double calcIfoDepositRequiredAmount()
        {
            return 7D;
        }
        /**
         * calc買ポジション建玉(指定日 : int)
         */
        public double calcBuyContractQty(int l_intReservedDate)
        {
            return 0D;
        }
        /**
         * calc売ポジション建玉(指定日 : int)
         */
        public double calcSellContractQty(int l_intReservedDate)
        {
            return 8D;
        }
        /**
         * calc注文中買ポジション建玉(指定日 : int)
         */
        public double calcBuyOrderQty(int l_intReservedDate)
        {
            return 0D;
        }
        /**
         * calc注文中売ポジション建玉(指定日 : int)
         */
        public double calcSellOrderQty(int l_intReservedDate)
        {
            return 7D;
        }
        public WEB3IfoDepositCalcCondition getIfoDepositCalcCondition()
        {
            WEB3IfoDepositCalcCondition l_condition = new WEB3IfoDepositCalcCondition();
            return l_condition;
        }
    }
    
    private class WEB3IfoDepositCalc1 extends WEB3IfoDepositCalc
    {
        /**
         * calc翌日請求額
         */
        public double calcNextBizDateDemandAmount()
        {
            return 8D;
        }
        
        /**
         * get当日請求額
         */
        public double getCurrentBizDateDemandAmount()
        {
            return 7D;
        }
        
        /**
         * calc未入金額
         */
        public double calcNonPayAmount()
        {
            return 7D;
        }
        /**
         * calc証拠金所要額
         */
        public double calcIfoDepositRequiredAmount()
        {
            return 7D;
        }
        /**
         * calc買ポジション建玉(指定日 : int)
         */
        public double calcBuyContractQty(int l_intReservedDate)
        {
            return 8D;
        }
        /**
         * calc売ポジション建玉(指定日 : int)
         */
        public double calcSellContractQty(int l_intReservedDate)
        {
            return 7D;
        }
        /**
         * calc注文中買ポジション建玉(指定日 : int)
         */
        public double calcBuyOrderQty(int l_intReservedDate)
        {
            return 7D;
        }
        /**
         * calc注文中売ポジション建玉(指定日 : int)
         */
        public double calcSellOrderQty(int l_intReservedDate)
        {
            return 7D;
        }
        public WEB3IfoDepositCalcCondition getIfoDepositCalcCondition()
        {
            WEB3IfoDepositCalcCondition l_condition = new WEB3IfoDepositCalcCondition();
            return l_condition;
        }
    }
    
    
    /**
     * リクエストデータ.検索日付が「当営業日」の場合、 calc翌日請求額( )の戻り値をセット
     * is清算値速報受信済()== true
     * is証拠金不足メール送信済()== false
     * a-c >0の場合　@trueをセット（※）
     * c >0の場合　@trueをセット（※）
     */
    public void test_createDepShortageList_0004()
    {
        TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);
        ProcessManagementParams l_processManagementParams = new ProcessManagementParams();
        l_processManagementParams.setProcessId("0008");
        l_processManagementParams.setInstitutionCode("0D");    
        l_processManagementParams.setBranchCode("ch");     
        l_processManagementParams.setStatus("1");         
        l_processManagementParams.setLastUpdater("dd");
        l_processManagementParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
        
        WEB3AdminIfoDepShortageReferenceServiceImpl l_service = new WEB3AdminIfoDepShortageReferenceServiceImpl();
        try
        {
            WEB3IfoDepositCalc1 l_calc = new WEB3IfoDepositCalc1();

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.ifodeposit.WEB3IfoDepositCalcServiceImpl",
                    "getIfoDepositCalc",
                    new Class[]
                    { WEB3GentradeSubAccount.class },
                   l_calc);
            
            WEB3AdminIfoDepShortageReferenceRequest l_request = new WEB3AdminIfoDepShortageReferenceRequest();
            //リクエストデータ ．検索日付 が　@「当営業日」　@の場合
            l_request.searchDate = GtlUtils.getTradingSystem().getBizDate();
            l_request.branchCode = new String[]{"ch"};
            
            l_request.uncancelDiv = "0";
            
            TestDBUtility.deleteAll(ProcessManagementParams.TYPE);
            TestDBUtility.insertWithDel(l_processManagementParams);
            
            TestDBUtility.deleteAll(MainAccountParams.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountCode("1234567");
            l_mainAccountParams.setBranchCode("ch");
            l_mainAccountParams.setInstitutionCode("0D");
            TestDBUtility.insertWithDel(l_mainAccountParams);
            
            TestDBUtility.deleteAll(SubAccountParams.TYPE);
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_OPTIONS_SUB_ACCOUNT);
            l_subAccountParams.setAccountId(333812512246L);
            TestDBUtility.insertWithDel(l_subAccountParams);
            
            IfoDepositParams l_ifoDepositParams = new IfoDepositParams();
            l_ifoDepositParams.setInstitutionCode("0D");
            l_ifoDepositParams.setBranchCode("ch");
            l_ifoDepositParams.setAccountCode("1234567");
            l_ifoDepositParams.setCalcDate(GtlUtils.getSystemTimestamp());
            IfoDepositRow[] l_depInfoList = new IfoDepositRow[]{l_ifoDepositParams};
            
            WEB3IfoDepShortageInfo[] l_ifoDepShortageInfos = l_service.createDepShortageList("0D", l_depInfoList, l_request);
            assertEquals("0",l_ifoDepShortageInfos.length + "");
//            WEB3IfoDepShortageInfo l_ifoDepShortageInfo = l_ifoDepShortageInfos[0];
//            assertEquals(l_ifoDepShortageInfo.accountCode, "123456");
//            assertEquals(l_ifoDepShortageInfo.branchCode, "ch");
//            assertEquals(l_ifoDepShortageInfo.accountName, "内藤　@四郎");
//            //請求額  ＝
//            //リクエストデータ.検索日付が「当営業日」の場合、 calc翌日請求額( )の戻り値をセット
//            assertEquals(l_ifoDepShortageInfo.claimAmount + "", "8");
//            //現在未入金額  ＝ 
//            // （ is清算値速報受信済()== true && is証拠金不足メール送信済()== false）
//            //　@|| (リクエストデータ.検索日付 が「前営業日」&& is証拠金不足メール送信済()==true)
//            // 　@の場合は nullをセット
//            // 上記以外の場合　@calc未入金額( )の戻り値をセット。
//            assertNull(l_ifoDepShortageInfo.curNonPayAmt);
//            //現在証拠金所要額          ＝　@calc証拠金所要額( )
//            assertEquals(l_ifoDepShortageInfo.curIfoDepositNecessaryAmt + "", "7");
//            //建玉有無フラグ            ＝  (*3)
//            //a-c >0の場合　@trueをセット（※）
//            //上記以外の場合　@falseをセット
//            assertTrue(l_ifoDepShortageInfo.contractExistFlag);
//            //注文有無フラグ            ＝  (*4)
//            //  c >0またはd >0の場合　@trueをセット（※）
//            //上記以外の場合　@falseをセット
//            assertTrue(l_ifoDepShortageInfo.orderExistFlag);
        }
        catch (WEB3BaseException e)
        {
            e.printStackTrace();
            fail();
        }
        catch (Exception e)
        {
            e.printStackTrace();
            fail();
        }
        
    }
}
@
