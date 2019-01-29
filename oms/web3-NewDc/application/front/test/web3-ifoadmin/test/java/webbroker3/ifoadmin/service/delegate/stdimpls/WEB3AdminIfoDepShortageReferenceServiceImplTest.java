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
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : WEB3AdminIfoDepShortageReferenceServiceImplTest.java
Author Name      : Daiwa Institute of Research
Revision History : 2009/03/09 �����i���u�j�V�K�쐬
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
     * ���O���[�e�B���e�B<BR>
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(
        WEB3AdminIfoDepShortageReferenceServiceImplTest.class);

    /**
     * �����N�G�X�g�f�[�^�̐������`�F�b�N���s���B
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
     * validate�������s
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
     * validate���X����(���X�R�[�h : String[])���s
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
     * ���N�G�X�g�f�[�^ �D�������t ���@@�u���c�Ɠ��v�@@�����@@is�؋����s�����[�����M�ρi�j�̖߂�l == false�̏ꍇ
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
     * create�؋����s���󋵈ꗗ�̖߂�l�̗v�f����0�̏ꍇ
     * ��̃��X�|���X�𐶐����A�����l���Z�b�g���ĕԋp
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
     * ��?�r��
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
     * boolean�r��
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
     * �����r��
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
     * validate�������s
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
     * �p�����[�^�l�s���B
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
     * �p�����[�^�^�C�v�s���B
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
     * ���N�G�X�g�f�[�^.�������t���u���c�Ɠ��v�̏ꍇ�A calc���������z( )�̖߂�l���Z�b�g
     * is���Z�l�����M��()== true
     * is�؋����s�����[�����M��()== false
     * a-c >0�̏ꍇ�@@true���Z�b�g�i���j
     * c >0�̏ꍇ�@@true���Z�b�g�i���j
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
            //���N�G�X�g�f�[�^ �D�������t ���@@�u���c�Ɠ��v�@@�̏ꍇ
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
            assertEquals(l_ifoDepShortageInfo.accountName, "�����@@�l�Y");
            //�����z  ��
            //���N�G�X�g�f�[�^.�������t���u���c�Ɠ��v�̏ꍇ�A calc���������z( )�̖߂�l���Z�b�g
            assertEquals(l_ifoDepShortageInfo.claimAmount + "", "8");
            //���ݖ������z  �� 
            // �i is���Z�l�����M��()== true && is�؋����s�����[�����M��()== false�j
            //�@@|| (���N�G�X�g�f�[�^.�������t ���u�O�c�Ɠ��v&& is�؋����s�����[�����M��()==true)
            // �@@�̏ꍇ�� null���Z�b�g
            // ��L�ȊO�̏ꍇ�@@calc�������z( )�̖߂�l���Z�b�g�B
            assertNull(l_ifoDepShortageInfo.curNonPayAmt);
            //���ݏ؋������v�z          ���@@calc�؋������v�z( )
            assertEquals(l_ifoDepShortageInfo.curIfoDepositNecessaryAmt + "", "7");
            //���ʗL���t���O            ��  (*3)
            //a-c >0�̏ꍇ�@@true���Z�b�g�i���j
            //��L�ȊO�̏ꍇ�@@false���Z�b�g
            assertTrue(l_ifoDepShortageInfo.contractExistFlag);
            //�����L���t���O            ��  (*4)
            //  c >0�܂���d >0�̏ꍇ�@@true���Z�b�g�i���j
            //��L�ȊO�̏ꍇ�@@false���Z�b�g
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
     * ���N�G�X�g�f�[�^.�������t���u�O�c�Ɠ��v�̏ꍇ�Aget���������z( )�̖߂�l���Z�b�g
     * ���N�G�X�g�f�[�^.�������t ���u�O�c�Ɠ��v&& is�؋����s�����[�����M��()==true
     * b-d >0�̏ꍇ�@@true���Z�b�g�i���j
     * d >0�̏ꍇ�@@true���Z�b�g�i���j
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
            //���N�G�X�g�f�[�^.�������t���u�O�c�Ɠ��v�̏ꍇ
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
            assertEquals(l_ifoDepShortageInfo.accountName, "�����@@�l�Y");
            //�����z  ��
            //���N�G�X�g�f�[�^.�������t���u�O�c�Ɠ��v�̏ꍇ�Aget���������z( )�̖߂�l���Z�b�g
            assertEquals(l_ifoDepShortageInfo.claimAmount + "", "7");
            //���ݖ������z  �� 
            // �i is���Z�l�����M��()== true && is�؋����s�����[�����M��()== false�j
            //�@@|| (���N�G�X�g�f�[�^.�������t ���u�O�c�Ɠ��v&& is�؋����s�����[�����M��()==true)
            // �@@�̏ꍇ�� null���Z�b�g
            // ��L�ȊO�̏ꍇ�@@calc�������z( )�̖߂�l���Z�b�g�B
            assertNull(l_ifoDepShortageInfo.curNonPayAmt);
            //���ݏ؋������v�z          ���@@calc�؋������v�z( )
            assertEquals(l_ifoDepShortageInfo.curIfoDepositNecessaryAmt + "", "7");
            //���ʗL���t���O            ��  (*3)
            //b-d >0�̏ꍇ�@@true���Z�b�g�i���j
            //��L�ȊO�̏ꍇ�@@false���Z�b�g
            assertTrue(l_ifoDepShortageInfo.contractExistFlag);
            //�����L���t���O            ��  (*4)
            //  c >0�܂���d >0�̏ꍇ�@@true���Z�b�g�i���j
            //��L�ȊO�̏ꍇ�@@false���Z�b�g
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
     * ���N�G�X�g�f�[�^.�������t���u�O�c�Ɠ��v�̏ꍇ�Aget���������z( )�̖߂�l���Z�b�g
     * !{�i is���Z�l�����M��()== true && is�؋����s�����[�����M��()== false�j
     * || (���N�G�X�g�f�[�^.�������t ���u�O�c�Ɠ��v&& is�؋����s�����[�����M��()==true)}
     * a-c <0 && b-d <0�̏ꍇ�@@false���Z�b�g�i���j
     *  c < 0 && d < 0�̏ꍇ�@@false���Z�b�g�i���j
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
            //���N�G�X�g�f�[�^.�������t���u���c�Ɠ��v�̏ꍇ
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
            assertEquals(l_ifoDepShortageInfo.accountName, "�����@@�l�Y");
            //�����z  ��
            //���N�G�X�g�f�[�^.�������t���u���c�Ɠ��v�̏ꍇ�Aget���������z( )�̖߂�l���Z�b�g
            assertEquals(l_ifoDepShortageInfo.claimAmount + "", "8");
            //���ݖ������z  �� 
            // !{�i is���Z�l�����M��()== true && is�؋����s�����[�����M��()== false�j
            //�@@|| (���N�G�X�g�f�[�^.�������t ���u�O�c�Ɠ��v&& is�؋����s�����[�����M��()==true)}
            // �@@�̏ꍇ�� null���Z�b�g
            // ��L�ȊO�̏ꍇ�@@calc�������z( )�̖߂�l���Z�b�g�B
            assertEquals(l_ifoDepShortageInfo.curNonPayAmt + "", "5");
            //���ݏ؋������v�z          ���@@calc�؋������v�z( )
            assertEquals(l_ifoDepShortageInfo.curIfoDepositNecessaryAmt + "", "4");
            //���ʗL���t���O            ��  (*3)
            //a-c <0 && b-d <0�̏ꍇ�@@false���Z�b�g�i���j
            assertFalse(l_ifoDepShortageInfo.contractExistFlag);
            //�����L���t���O            ��  (*4)
            //c < 0 && d < 0�̏ꍇ�@@false���Z�b�g�i���j
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
         * calc���������z
         */
        public double calcNextBizDateDemandAmount()
        {
            return 8D;
        }
        
        /**
         * get���������z
         */
        public double getCurrentBizDateDemandAmount()
        {
            return 7D;
        }
        
        /**
         * calc�������z
         */
        public double calcNonPayAmount()
        {
            return 5D;
        }
        /**
         * calc�؋������v�z
         */
        public double calcIfoDepositRequiredAmount()
        {
            return 4D;
        }
        /**
         * calc���|�W�V��������(�w��� : int)
         */
        public double calcBuyContractQty(int l_intReservedDate)
        {
            return -1D;
        }
        /**
         * calc���|�W�V��������(�w��� : int)
         */
        public double calcSellContractQty(int l_intReservedDate)
        {
            return -1D;
        }
        /**
         * calc���������|�W�V��������(�w��� : int)
         */
        public double calcBuyOrderQty(int l_intReservedDate)
        {
            return -1D;
        }
        /**
         * calc���������|�W�V��������(�w��� : int)
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
         * calc���������z
         */
        public double calcNextBizDateDemandAmount()
        {
            return 8D;
        }
        
        /**
         * get���������z
         */
        public double getCurrentBizDateDemandAmount()
        {
            return 7D;
        }
        
        /**
         * calc�������z
         */
        public double calcNonPayAmount()
        {
            return 7D;
        }
        /**
         * calc�؋������v�z
         */
        public double calcIfoDepositRequiredAmount()
        {
            return 7D;
        }
        /**
         * calc���|�W�V��������(�w��� : int)
         */
        public double calcBuyContractQty(int l_intReservedDate)
        {
            return 0D;
        }
        /**
         * calc���|�W�V��������(�w��� : int)
         */
        public double calcSellContractQty(int l_intReservedDate)
        {
            return 8D;
        }
        /**
         * calc���������|�W�V��������(�w��� : int)
         */
        public double calcBuyOrderQty(int l_intReservedDate)
        {
            return 0D;
        }
        /**
         * calc���������|�W�V��������(�w��� : int)
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
         * calc���������z
         */
        public double calcNextBizDateDemandAmount()
        {
            return 8D;
        }
        
        /**
         * get���������z
         */
        public double getCurrentBizDateDemandAmount()
        {
            return 7D;
        }
        
        /**
         * calc�������z
         */
        public double calcNonPayAmount()
        {
            return 7D;
        }
        /**
         * calc�؋������v�z
         */
        public double calcIfoDepositRequiredAmount()
        {
            return 7D;
        }
        /**
         * calc���|�W�V��������(�w��� : int)
         */
        public double calcBuyContractQty(int l_intReservedDate)
        {
            return 8D;
        }
        /**
         * calc���|�W�V��������(�w��� : int)
         */
        public double calcSellContractQty(int l_intReservedDate)
        {
            return 7D;
        }
        /**
         * calc���������|�W�V��������(�w��� : int)
         */
        public double calcBuyOrderQty(int l_intReservedDate)
        {
            return 7D;
        }
        /**
         * calc���������|�W�V��������(�w��� : int)
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
     * ���N�G�X�g�f�[�^.�������t���u���c�Ɠ��v�̏ꍇ�A calc���������z( )�̖߂�l���Z�b�g
     * is���Z�l�����M��()== true
     * is�؋����s�����[�����M��()== false
     * a-c >0�̏ꍇ�@@true���Z�b�g�i���j
     * c >0�̏ꍇ�@@true���Z�b�g�i���j
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
            //���N�G�X�g�f�[�^ �D�������t ���@@�u���c�Ɠ��v�@@�̏ꍇ
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
//            assertEquals(l_ifoDepShortageInfo.accountName, "�����@@�l�Y");
//            //�����z  ��
//            //���N�G�X�g�f�[�^.�������t���u���c�Ɠ��v�̏ꍇ�A calc���������z( )�̖߂�l���Z�b�g
//            assertEquals(l_ifoDepShortageInfo.claimAmount + "", "8");
//            //���ݖ������z  �� 
//            // �i is���Z�l�����M��()== true && is�؋����s�����[�����M��()== false�j
//            //�@@|| (���N�G�X�g�f�[�^.�������t ���u�O�c�Ɠ��v&& is�؋����s�����[�����M��()==true)
//            // �@@�̏ꍇ�� null���Z�b�g
//            // ��L�ȊO�̏ꍇ�@@calc�������z( )�̖߂�l���Z�b�g�B
//            assertNull(l_ifoDepShortageInfo.curNonPayAmt);
//            //���ݏ؋������v�z          ���@@calc�؋������v�z( )
//            assertEquals(l_ifoDepShortageInfo.curIfoDepositNecessaryAmt + "", "7");
//            //���ʗL���t���O            ��  (*3)
//            //a-c >0�̏ꍇ�@@true���Z�b�g�i���j
//            //��L�ȊO�̏ꍇ�@@false���Z�b�g
//            assertTrue(l_ifoDepShortageInfo.contractExistFlag);
//            //�����L���t���O            ��  (*4)
//            //  c >0�܂���d >0�̏ꍇ�@@true���Z�b�g�i���j
//            //��L�ȊO�̏ꍇ�@@false���Z�b�g
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
