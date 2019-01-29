head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.02.03.22;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3AdminIfoDepShortageDownloadServiceImplTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : WEB3AdminIfoDepShortageDownloadServiceImplTest.java
Author Name      : Daiwa Institute of Research
Revision History : 2009/03/06 ���ʗ�i���u�j�V�K�쐬
*/
package webbroker3.ifoadmin.service.delegate.stdimpls;

import java.sql.Timestamp;
import java.util.Date;

import com.fitechlabs.xtrade.kernel.util.ThreadLocalSystemAttributesRegistry;
import com.fitechlabs.xtrade.plugin.security.oplogin.impl.LoginInfoImplForMock;
import com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.BranchTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccountTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.BranchParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.InstitutionParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.SubAccountParams;

import test.util.TestDBUtility;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.gentrade.data.AdminPermissionParams;
import webbroker3.gentrade.data.AdministratorParams;
import webbroker3.gentrade.data.AdministratorTypeParams;
import webbroker3.gentrade.data.BranchPreferencesParams;
import webbroker3.gentrade.data.IfoDepositParams;
import webbroker3.gentrade.data.ProcessManagementParams;
import webbroker3.gentrade.data.TradingTimeParams;
import webbroker3.gentrade.data.TradingTimeRow;
import webbroker3.ifoadmin.message.WEB3AdminIfoDepShortageDownloadRequest;
import webbroker3.ifoadmin.message.WEB3AdminIfoDepShortageDownloadResponse;
import webbroker3.ifoadmin.message.WEB3AdminIfoDepShortageReferenceRequest;
import webbroker3.ifoadmin.message.WEB3IfoDepShortageSortKey;
import webbroker3.ifodeposit.WEB3IfoDepositCalc;
import webbroker3.ifodeposit.WEB3IfoDepositCalcCondition;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

public class WEB3AdminIfoDepShortageDownloadServiceImplTest extends TestBaseForMock
{

    /**
     * ���O���[�e�B���e�B<BR>
     */
    private static WEB3LogUtility log =
         WEB3LogUtility.getInstance(WEB3AdminIfoDepShortageDownloadServiceImplTest.class);

    private WEB3AdminIfoDepShortageDownloadServiceImpl l_impl = null;
    private WEB3AdminIfoDepShortageDownloadResponse l_response = null;

    public WEB3AdminIfoDepShortageDownloadServiceImplTest(String name)
    {
        super(name);
    }

    protected void setUp() throws Exception
    {
        super.setUp();
        l_impl = new WEB3AdminIfoDepShortageDownloadServiceImpl();
    }

    protected void tearDown() throws Exception
    {
        super.tearDown();
    }

     // �p�����[�^�l�s���B
     // ���N�G�X�g = null
    public void testExecute_0001()
    {
        String STR_METHOD_NAME = "testExecute_0001()";
        log.entering(STR_METHOD_NAME);
        try
        {
            l_impl.execute(null);
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80017, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }

     // �p�����[�^�^�C�v�s��
     // ���N�G�X�g�^�C�v != �Ǘ��ҁE�؋����s���󋵃_�E�����[�h���N�G�X�g
    public void testExecute_0002()
    {
        String STR_METHOD_NAME = "testExecute_0002()";
        log.entering(STR_METHOD_NAME);
        try
        {
            WEB3AdminIfoDepShortageReferenceRequest l_request =
                new WEB3AdminIfoDepShortageReferenceRequest();
            l_impl.execute(l_request);
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80018,l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }

     // ���N�G�X�g�^�C�v = �Ǘ��ҁE�؋����s���󋵃_�E�����[�h���N�G�X�g
     // ����I��
    public void testExecute_0003()
    {
        String STR_METHOD_NAME = "testExecute_0003()";
        log.entering(STR_METHOD_NAME);

        try
        {
            WEB3AdminIfoDepShortageDownloadRequest l_request =
                new WEB3AdminIfoDepShortageDownloadRequest();

            l_impl = new WEB3AdminIfoDepShortageDownloadServiceImplForTest();
            WEB3GenResponse l_response = l_impl.execute(l_request);

            assertEquals("OK", l_response.errorMessage);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }

     // validate
     // this.���X�R�[�h�ꗗ == null�̏ꍇ
    public void testGetDownLoadFile_0001()
    {
        String STR_METHOD_NAME = "testGetDownLoadFile_0001()";
        log.entering(STR_METHOD_NAME);

        try
        {
            WEB3AdminIfoDepShortageDownloadRequest l_request =
                new WEB3AdminIfoDepShortageDownloadRequest();

            l_request.searchDate = WEB3DateUtility.getDate("20090306", "yyyyMMdd");
            l_impl.execute(l_request);
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_01429, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }

        log.exiting(STR_METHOD_NAME);
    }

     // �Ǘ��Ҍ����`�F�b�N���s��
    public void testGetDownLoadFile_0002()
    {
        String STR_METHOD_NAME = "testGetDownLoadFile_0002()";
        log.entering(STR_METHOD_NAME);

        try
        {
            MOCK_MANAGER.setIsMockUsed(true);

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getLoginInfo",
                new Class[] {},
                new LoginInfoImplForMock());

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.LoginInfoImpl",
                "getLoginId",
                new Class[] {},
                new Long(33381330001L));

            TestDBUtility.deleteAll(AdministratorParams.TYPE);
            AdministratorParams l_administratorParams = TestDBUtility.getAdministratorRow();
            l_administratorParams.setLoginId(33381330001L);
            l_administratorParams.setInstitutionCode("0D");
            TestDBUtility.insertWithDel(l_administratorParams);

            TestDBUtility.deleteAll(AdminPermissionParams.TYPE);
            AdminPermissionParams l_adminPermissionParams1 = TestDBUtility.getAdminPermissionRow();
            l_adminPermissionParams1.setInstitutionCode("0D");
            l_adminPermissionParams1.setPermissionLevel("331");
            l_adminPermissionParams1.setTransactionCategory("C0101");
            l_adminPermissionParams1.setUpdatePermissionFlag(BooleanEnum.TRUE);
            l_adminPermissionParams1.setCreatedTimestamp(WEB3DateUtility.getDate("20090306", "yyyyMMdd"));
            l_adminPermissionParams1.setUpdateTimestamp(WEB3DateUtility.getDate("20090306", "yyyyMMdd"));
            TestDBUtility.insertWithDel(l_adminPermissionParams1);

            WEB3AdminIfoDepShortageDownloadRequest l_request =
                new WEB3AdminIfoDepShortageDownloadRequest();

            l_request.searchDate = WEB3DateUtility.getDate("20090306", "yyyyMMdd");
            String[] l_branchCodeList = new String[1];
            l_branchCodeList[0] = "123";
            l_request.branchCode = l_branchCodeList;
            l_request.pageIndex = "1";
            l_request.pageSize = "10";
            WEB3IfoDepShortageSortKey[] l_sortKeys = new WEB3IfoDepShortageSortKey[1];
            l_sortKeys[0] = new WEB3IfoDepShortageSortKey();
            l_sortKeys[0].keyItem = "branchCode";
            l_sortKeys[0].ascDesc = "A";
            l_request.sortKeys = l_sortKeys;

            l_impl.execute(l_request);
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_01056, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }

     // ���X�����̃`�F�b�N���s��
    public void testGetDownLoadFile_0003()
    {
        String STR_METHOD_NAME = "testGetDownLoadFile_0003()";
        log.entering(STR_METHOD_NAME);

        try
        {
            MOCK_MANAGER.setIsMockUsed(true);

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getLoginInfo",
                new Class[] {},
                new LoginInfoImplForMock());

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.LoginInfoImpl",
                "getLoginId",
                new Class[] {},
                new Long(33381330001L));

            TestDBUtility.deleteAll(InstitutionParams.TYPE);
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            l_institutionParams.setInstitutionCode("0D");
            l_institutionParams.setInstitutionId(33);
            l_institutionParams.setCreatedTimestamp(WEB3DateUtility.getDate("20090306", "yyyyMMdd"));
            l_institutionParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20090306", "yyyyMMdd"));
            TestDBUtility.insertWithDel(l_institutionParams);

            TestDBUtility.deleteAll(AdministratorParams.TYPE);
            AdministratorParams l_administratorParams = TestDBUtility.getAdministratorRow();
            l_administratorParams.setLoginId(33381330001L);
            l_administratorParams.setInstitutionCode("0D");
            l_administratorParams.setInstitutionId(33);
            l_administratorParams.setBranchCode("101");
            l_administratorParams.setBranchId(33381L);
            TestDBUtility.insertWithDel(l_administratorParams);

            TestDBUtility.deleteAll(AdministratorTypeParams.TYPE);
            AdministratorTypeParams l_administratorTypeParams = TestDBUtility.getAdministratorTypeRow();
            l_administratorParams.setLoginId(33381330001L);
            l_administratorTypeParams.setInstitutionCode("0D");
            l_administratorTypeParams.setPermissionLevel("331");
            l_administratorTypeParams.setDirAdminFlag(0);
            l_administratorTypeParams.setAllBranchPermissionFlag(BooleanEnum.FALSE);
            l_administratorTypeParams.setCreatedTimestamp(WEB3DateUtility.getDate("20090306", "yyyyMMdd"));
            l_administratorTypeParams.setUpdateTimestamp(WEB3DateUtility.getDate("20090306", "yyyyMMdd"));
            TestDBUtility.insertWithDel(l_administratorTypeParams);

            TestDBUtility.deleteAll(AdminPermissionParams.TYPE);
            AdminPermissionParams l_adminPermissionParams1 = TestDBUtility.getAdminPermissionRow();
            l_adminPermissionParams1.setInstitutionCode("0D");
            l_adminPermissionParams1.setPermissionLevel("331");
            l_adminPermissionParams1.setTransactionCategory("C0305");
            l_adminPermissionParams1.setUpdatePermissionFlag(BooleanEnum.FALSE);
            l_adminPermissionParams1.setCreatedTimestamp(WEB3DateUtility.getDate("20090306", "yyyyMMdd"));
            l_adminPermissionParams1.setUpdateTimestamp(WEB3DateUtility.getDate("20090306", "yyyyMMdd"));
            TestDBUtility.insertWithDel(l_adminPermissionParams1);

            WEB3AdminIfoDepShortageDownloadRequest l_request =
                new WEB3AdminIfoDepShortageDownloadRequest();

            l_request.searchDate = WEB3DateUtility.getDate("20090306", "yyyyMMdd");
            String[] l_branchCodeList = new String[1];
            l_branchCodeList[0] = "123";
            l_request.branchCode = l_branchCodeList;
            l_request.pageIndex = "1";
            l_request.pageSize = "10";
            WEB3IfoDepShortageSortKey[] l_sortKeys = new WEB3IfoDepShortageSortKey[1];
            l_sortKeys[0] = new WEB3IfoDepShortageSortKey();
            l_sortKeys[0].keyItem = "branchCode";
            l_sortKeys[0].ascDesc = "A";
            l_request.sortKeys = l_sortKeys;

            l_impl.execute(l_request);
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_01074, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }

        log.exiting(STR_METHOD_NAME);
    }

    // �؋����s���m��ς𔻒肷��
    //���N�G�X�g�f�[�^ �D�������t ���@@�u���c�Ɠ��v
    //is�؋����s�����[�����M�ρi�j�̖߂�l == false
    //�u�{���̏؋����s���͂܂��m�F���Ă��܂���B�v�̗�O���X���[����B
    public void testGetDownLoadFile_0004()
    {
        String STR_METHOD_NAME = "testGetDownLoadFile_0004()";
        log.entering(STR_METHOD_NAME);

        try
        {
            MOCK_MANAGER.setIsMockUsed(true);

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getLoginInfo",
                new Class[] {},
                new LoginInfoImplForMock());

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.LoginInfoImpl",
                "getLoginId",
                new Class[] {},
                new Long(33381330001L));

            TestDBUtility.deleteAll(InstitutionParams.TYPE);
            InstitutionParams l_institutionParams = new InstitutionParams();
            l_institutionParams.setInstitutionCode("0D");
            l_institutionParams.setInstitutionId(33);
            l_institutionParams.setCreatedTimestamp(WEB3DateUtility.getDate("20090306", "yyyyMMdd"));
            l_institutionParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20090306", "yyyyMMdd"));
            TestDBUtility.insertWithDel(l_institutionParams);

            TestDBUtility.deleteAll(AdministratorParams.TYPE);
            AdministratorParams l_administratorParams = TestDBUtility.getAdministratorRow();
            l_administratorParams.setLoginId(33381330001L);
            l_administratorParams.setInstitutionCode("0D");
            l_administratorParams.setInstitutionId(33);
            l_administratorParams.setBranchCode("101");
            l_administratorParams.setBranchId(33381L);
            TestDBUtility.insertWithDel(l_administratorParams);

            TestDBUtility.deleteAll(AdministratorTypeParams.TYPE);
            AdministratorTypeParams l_administratorTypeParams = TestDBUtility.getAdministratorTypeRow();
            l_administratorParams.setLoginId(33381330001L);
            l_administratorTypeParams.setInstitutionCode("0D");
            l_administratorTypeParams.setPermissionLevel("331");
            l_administratorTypeParams.setDirAdminFlag(0);
            l_administratorTypeParams.setAllBranchPermissionFlag(BooleanEnum.FALSE);
            l_administratorTypeParams.setCreatedTimestamp(WEB3DateUtility.getDate("20090306", "yyyyMMdd"));
            l_administratorTypeParams.setUpdateTimestamp(WEB3DateUtility.getDate("20090306", "yyyyMMdd"));
            TestDBUtility.insertWithDel(l_administratorTypeParams);

            TestDBUtility.deleteAll(AdminPermissionParams.TYPE);
            AdminPermissionParams l_adminPermissionParams1 = new AdminPermissionParams();
            l_adminPermissionParams1.setInstitutionCode("0D");
            l_adminPermissionParams1.setPermissionLevel("331");
            l_adminPermissionParams1.setTransactionCategory("C0305");
            l_adminPermissionParams1.setUpdatePermissionFlag(BooleanEnum.FALSE);
            l_adminPermissionParams1.setCreatedTimestamp(WEB3DateUtility.getDate("20090306", "yyyyMMdd"));
            l_adminPermissionParams1.setUpdateTimestamp(WEB3DateUtility.getDate("20090306", "yyyyMMdd"));
            TestDBUtility.insertWithDel(l_adminPermissionParams1);

            TestDBUtility.deleteAll(ProcessManagementParams.TYPE);
            ProcessManagementParams l_processManagentParams = new ProcessManagementParams();
            l_processManagentParams.setProcessId("0001");
            l_processManagentParams.setInstitutionCode("6D");
            l_processManagentParams.setBranchCode("101");
            TestDBUtility.insertWithDel(l_processManagentParams);

            WEB3AdminIfoDepShortageDownloadRequest l_request =
                new WEB3AdminIfoDepShortageDownloadRequest();

            Date l_dat = WEB3DateUtility.getDate("20090306", "yyyyMMdd");
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.tc.gentrade.stdimpls.TradingSystemImpl",
                "getBizDate",
                new Class[] {},
                l_dat);

            l_request.searchDate = l_dat;
            String[] l_branchCodeList = new String[1];
            l_branchCodeList[0] = "101";
            l_request.branchCode = l_branchCodeList;
            l_request.pageIndex = "1";
            l_request.pageSize = "10";
            WEB3IfoDepShortageSortKey[] l_sortKeys = new WEB3IfoDepShortageSortKey[1];
            l_sortKeys[0] = new WEB3IfoDepShortageSortKey();
            l_sortKeys[0].keyItem = "branchCode";
            l_sortKeys[0].ascDesc = "A";
            l_request.sortKeys = l_sortKeys;

            l_impl.execute(l_request);
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_03157, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }

        log.exiting(STR_METHOD_NAME);
    }

    //create�؋����s���󋵈ꗗ�̖߂�l�̗v�f����0�̏ꍇ
    //get�؋������ꗗ()�̖߂�l�̗v�f����0�̏ꍇ
    public void testGetDownLoadFile_0005()
    {
        String STR_METHOD_NAME = "testGetDownLoadFile_0005()";
        log.entering(STR_METHOD_NAME);

        try
        {
            MOCK_MANAGER.setIsMockUsed(true);

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getLoginInfo",
                new Class[] {},
                new LoginInfoImplForMock());

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.LoginInfoImpl",
                "getLoginId",
                new Class[] {},
                new Long(33381330001L));

            TestDBUtility.deleteAll(InstitutionParams.TYPE);
            InstitutionParams l_institutionParams = new InstitutionParams();
            l_institutionParams.setInstitutionCode("0D");
            l_institutionParams.setInstitutionId(33);
            l_institutionParams.setCreatedTimestamp(WEB3DateUtility.getDate("20090306", "yyyyMMdd"));
            l_institutionParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20090306", "yyyyMMdd"));
            TestDBUtility.insertWithDel(l_institutionParams);

            TestDBUtility.deleteAll(AdministratorParams.TYPE);
            AdministratorParams l_administratorParams = TestDBUtility.getAdministratorRow();
            l_administratorParams.setLoginId(33381330001L);
            l_administratorParams.setInstitutionCode("0D");
            l_administratorParams.setInstitutionId(33);
            l_administratorParams.setBranchCode("101");
            l_administratorParams.setBranchId(33381L);
            TestDBUtility.insertWithDel(l_administratorParams);

            TestDBUtility.deleteAll(AdministratorTypeParams.TYPE);
            AdministratorTypeParams l_administratorTypeParams = TestDBUtility.getAdministratorTypeRow();
            l_administratorParams.setLoginId(33381330001L);
            l_administratorTypeParams.setInstitutionCode("0D");
            l_administratorTypeParams.setPermissionLevel("331");
            l_administratorTypeParams.setDirAdminFlag(0);
            l_administratorTypeParams.setAllBranchPermissionFlag(BooleanEnum.FALSE);
            l_administratorTypeParams.setCreatedTimestamp(WEB3DateUtility.getDate("20090306", "yyyyMMdd"));
            l_administratorTypeParams.setUpdateTimestamp(WEB3DateUtility.getDate("20090306", "yyyyMMdd"));
            TestDBUtility.insertWithDel(l_administratorTypeParams);

            TestDBUtility.deleteAll(AdminPermissionParams.TYPE);
            AdminPermissionParams l_adminPermissionParams1 = new AdminPermissionParams();
            l_adminPermissionParams1.setInstitutionCode("0D");
            l_adminPermissionParams1.setPermissionLevel("331");
            l_adminPermissionParams1.setTransactionCategory("C0305");
            l_adminPermissionParams1.setUpdatePermissionFlag(BooleanEnum.FALSE);
            l_adminPermissionParams1.setCreatedTimestamp(WEB3DateUtility.getDate("20090306", "yyyyMMdd"));
            l_adminPermissionParams1.setUpdateTimestamp(WEB3DateUtility.getDate("20090306", "yyyyMMdd"));
            TestDBUtility.insertWithDel(l_adminPermissionParams1);

            TestDBUtility.deleteAll(ProcessManagementParams.TYPE);
            ProcessManagementParams l_processManagentParams = new ProcessManagementParams();
            l_processManagentParams.setProcessId("0001");
            l_processManagentParams.setInstitutionCode("0D");
            l_processManagentParams.setBranchCode("101");
            TestDBUtility.insertWithDel(l_processManagentParams);


            TestDBUtility.deleteAll(MainAccountParams.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(333812511906L);
            l_mainAccountParams.setInstitutionCode("0D");
            l_mainAccountParams.setAccountCode("123456");
            l_mainAccountParams.setBranchCode("101");
            TestDBUtility.insertWithDel(l_mainAccountParams);

            TestDBUtility.deleteAll(SubAccountParams.TYPE);
            SubAccountParams l_subAccuontParams = TestDBUtility.getSubAccountRow();
            l_subAccuontParams.setAccountId(333812511906L);
            l_subAccuontParams.setSubAccountId(33381251220301L);
            l_subAccuontParams.setInstitutionCode("0D");
            l_subAccuontParams.setInstitutionId(33);
            l_subAccuontParams.setBranchId(33381L);
            l_subAccuontParams.setSubAccountType(SubAccountTypeEnum.EQUITY_OPTIONS_SUB_ACCOUNT);
            TestDBUtility.insertWithDel(l_subAccuontParams);

            TestDBUtility.deleteAll(IfoDepositParams.TYPE);
            IfoDepositParams l_ifoDepositParams = new IfoDepositParams();
            l_ifoDepositParams.setBranchCode("101");
            l_ifoDepositParams.setInstitutionCode("0D");
            l_ifoDepositParams.setCalcDate(WEB3DateUtility.getDate("20090306", "yyyyMMdd"));
            l_ifoDepositParams.setAccountCode("1234567");
            //TestDBUtility.insertWithDel(l_ifoDepositParams);

            TestDBUtility.deleteAll(TradingTimeRow.TYPE);
            TradingTimeParams l_tradingTimeParams1 = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams1.setInstitutionCode("0D");
            l_tradingTimeParams1.setBranchCode("123");
            l_tradingTimeParams1.setMarketCode("N1");
            l_tradingTimeParams1.setTradingTimeType("01");
            l_tradingTimeParams1.setBizDateType("1");
            l_tradingTimeParams1.setProductCode("0");
            TestDBUtility.insertWithDel(l_tradingTimeParams1);

            WEB3IfoDepositCalc l_ifoDepCalc = new WEB3IfoDepositCalcForTest();

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.ifodeposit.WEB3IfoDepositCalcServiceImpl", "getIfoDepositCalc", new Class[]
                { WEB3GentradeSubAccount.class },
                l_ifoDepCalc);

            WEB3AdminIfoDepShortageDownloadRequest l_request =
                new WEB3AdminIfoDepShortageDownloadRequest();

            l_request.searchDate = WEB3DateUtility.getDate("20090306", "yyyyMMdd");
            String[] l_branchCodeList = new String[1];
            l_branchCodeList[0] = "101";
            l_request.branchCode = l_branchCodeList;
            l_request.pageIndex = "1";
            l_request.pageSize = "10";
            WEB3IfoDepShortageSortKey[] l_sortKeys = new WEB3IfoDepShortageSortKey[1];
            l_sortKeys[0] = new WEB3IfoDepShortageSortKey();
            l_sortKeys[0].keyItem = "branchCode";
            l_sortKeys[0].ascDesc = "A";
            l_request.sortKeys = l_sortKeys;

            l_impl.execute(l_request);
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_03158, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }

        log.exiting(STR_METHOD_NAME);
    }

    //����I��
    //�ԉ�1�𐔐�
    public void testGetDownLoadFile_0006()
    {
        String STR_METHOD_NAME = "testGetDownLoadFile_0006()";
        log.entering(STR_METHOD_NAME);

        try
        {
            MOCK_MANAGER.setIsMockUsed(true);

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getLoginInfo",
                new Class[] {},
                new LoginInfoImplForMock());

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.LoginInfoImpl",
                "getLoginId",
                new Class[] {},
                new Long(33381330001L));

            ThreadLocalSystemAttributesRegistry.setAttribute(
                "xblocks.gtl.attributes.systemtimestamp",
                new Timestamp(WEB3DateUtility.getDate("20090109", "yyyyMMdd").getTime()));

            TestDBUtility.deleteAll(InstitutionParams.TYPE);
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            l_institutionParams.setInstitutionCode("0D");
            l_institutionParams.setInstitutionId(33);
            l_institutionParams.setCreatedTimestamp(WEB3DateUtility.getDate("20090306", "yyyyMMdd"));
            l_institutionParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20090306", "yyyyMMdd"));
            TestDBUtility.insertWithDel(l_institutionParams);

            TestDBUtility.deleteAll(BranchParams.TYPE);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setBranchId(33381);
            l_branchParams.setInstitutionCode("0D");
            l_branchParams.setInstitutionId(33);
            l_branchParams.setBranchCode("101");
            l_branchParams.setBranchName("�����x�X");
            l_branchParams.setBranchNameAlt1("TOKYO BRANCH");
            l_branchParams.setBranchType(BranchTypeEnum.WEB_BRANCH);
            TestDBUtility.insertWithDel(l_branchParams);

            TestDBUtility.deleteAll(BranchPreferencesParams.TYPE);
            BranchPreferencesParams l_branchPreferencesParams = TestDBUtility.getBranchPreferencesRow();
            l_branchPreferencesParams.setBranchId(33381L);
            l_branchPreferencesParams.setValue("1");
            l_branchPreferencesParams.setNameSerialNo(1002);
            TestDBUtility.insertWithDel(l_branchPreferencesParams);

            TestDBUtility.deleteAll(AdministratorParams.TYPE);
            AdministratorParams l_administratorParams = TestDBUtility.getAdministratorRow();
            l_administratorParams.setLoginId(33381330001L);
            l_administratorParams.setInstitutionCode("0D");
            l_administratorParams.setInstitutionId(33);
            l_administratorParams.setBranchCode("101");
            l_administratorParams.setBranchId(33381L);
            TestDBUtility.insertWithDel(l_administratorParams);

            TestDBUtility.deleteAll(AdministratorTypeParams.TYPE);
            AdministratorTypeParams l_administratorTypeParams = TestDBUtility.getAdministratorTypeRow();
            l_administratorParams.setLoginId(33381330001L);
            l_administratorTypeParams.setInstitutionCode("0D");
            l_administratorTypeParams.setPermissionLevel("331");
            l_administratorTypeParams.setDirAdminFlag(0);
            l_administratorTypeParams.setAllBranchPermissionFlag(BooleanEnum.TRUE);
            l_administratorTypeParams.setCreatedTimestamp(WEB3DateUtility.getDate("20090306", "yyyyMMdd"));
            l_administratorTypeParams.setUpdateTimestamp(WEB3DateUtility.getDate("20090306", "yyyyMMdd"));
            TestDBUtility.insertWithDel(l_administratorTypeParams);

            TestDBUtility.deleteAll(AdminPermissionParams.TYPE);
            AdminPermissionParams l_adminPermissionParams1 = TestDBUtility.getAdminPermissionRow();
            l_adminPermissionParams1.setInstitutionCode("0D");
            l_adminPermissionParams1.setPermissionLevel("331");
            l_adminPermissionParams1.setTransactionCategory("C0305");
            l_adminPermissionParams1.setUpdatePermissionFlag(BooleanEnum.FALSE);
            l_adminPermissionParams1.setCreatedTimestamp(WEB3DateUtility.getDate("20090306", "yyyyMMdd"));
            l_adminPermissionParams1.setUpdateTimestamp(WEB3DateUtility.getDate("20090306", "yyyyMMdd"));
            TestDBUtility.insertWithDel(l_adminPermissionParams1);

            TestDBUtility.deleteAll(ProcessManagementParams.TYPE);
            ProcessManagementParams l_processManagentParams = new ProcessManagementParams();
            l_processManagentParams.setProcessId("0001");
            l_processManagentParams.setInstitutionCode("0D");
            l_processManagentParams.setBranchCode("101");
            TestDBUtility.insertWithDel(l_processManagentParams);

            TestDBUtility.deleteAll(MainAccountParams.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(333812511906L);
            l_mainAccountParams.setInstitutionCode("0D");
            l_mainAccountParams.setAccountCode("123456");
            l_mainAccountParams.setBranchCode("101");
            TestDBUtility.insertWithDel(l_mainAccountParams);

            TestDBUtility.deleteAll(SubAccountParams.TYPE);
            SubAccountParams l_subAccuontParams = TestDBUtility.getSubAccountRow();
            l_subAccuontParams.setAccountId(333812511906L);
            l_subAccuontParams.setSubAccountId(33381251220301L);
            l_subAccuontParams.setInstitutionCode("0D");
            l_subAccuontParams.setInstitutionId(33);
            l_subAccuontParams.setBranchId(33381L);
            l_subAccuontParams.setSubAccountType(SubAccountTypeEnum.EQUITY_OPTIONS_SUB_ACCOUNT);
            TestDBUtility.insertWithDel(l_subAccuontParams);

            TestDBUtility.deleteAll(IfoDepositParams.TYPE);
            IfoDepositParams l_ifoDepositParams = new IfoDepositParams();
            l_ifoDepositParams.setBranchCode("101");
            l_ifoDepositParams.setInstitutionCode("0D");
            l_ifoDepositParams.setCalcDate(WEB3DateUtility.getDate("20090306", "yyyyMMdd"));
            l_ifoDepositParams.setAccountCode("123456");
            TestDBUtility.insertWithDel(l_ifoDepositParams);

            TestDBUtility.deleteAll(TradingTimeRow.TYPE);
            TradingTimeParams l_tradingTimeParams1 = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams1.setInstitutionCode("0D");
            l_tradingTimeParams1.setBranchCode("123");
            l_tradingTimeParams1.setMarketCode("N1");
            l_tradingTimeParams1.setTradingTimeType("01");
            l_tradingTimeParams1.setBizDateType("1");
            l_tradingTimeParams1.setProductCode("0");
            TestDBUtility.insertWithDel(l_tradingTimeParams1);

            WEB3IfoDepositCalc l_ifoDepCalc = new WEB3IfoDepositCalcForTest();

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.ifodeposit.WEB3IfoDepositCalcServiceImpl", "getIfoDepositCalc", new Class[]
                { WEB3GentradeSubAccount.class },
                l_ifoDepCalc);

            WEB3AdminIfoDepShortageDownloadRequest l_request =
                new WEB3AdminIfoDepShortageDownloadRequest();

            l_request.searchDate = WEB3DateUtility.getDate("20090306", "yyyyMMdd");
            String[] l_branchCodeList = new String[1];
            l_branchCodeList[0] = "101";
            l_request.branchCode = l_branchCodeList;
            l_request.accountCode = "123456";
            l_request.uncancelDiv = "1";
            l_request.pageIndex = "1";
            l_request.pageSize = "10";
            WEB3IfoDepShortageSortKey[] l_sortKeys = new WEB3IfoDepShortageSortKey[1];
            l_sortKeys[0] = new WEB3IfoDepShortageSortKey();
            l_sortKeys[0].keyItem = "branchCode";
            l_sortKeys[0].ascDesc = "A";
            l_request.sortKeys = l_sortKeys;

            l_response = new WEB3AdminIfoDepShortageDownloadResponse();
            l_response = (WEB3AdminIfoDepShortageDownloadResponse)l_impl.execute(l_request);

            assertEquals("CSV�o�͓�,2009/01/09 00:00:00,�������t,2009/03/06", l_response.downloadFile[0]);
            assertEquals("���X,�ڋq,�ڋq��,�����z,���݂̖��������z,���݂̏؋������v�z,���ʗL���iOP���������j,�����L���iOP���������j", l_response.downloadFile[1]);
            assertEquals("101,123456,�����@@�l�Y,,3,4,��,�L", l_response.downloadFile[2]);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }

        log.exiting(STR_METHOD_NAME);
    }

    //����I��
    //�ԉ�2�𐔐�
    public void testGetDownLoadFile_0007()
    {
        String STR_METHOD_NAME = "testGetDownLoadFile_0007()";
        log.entering(STR_METHOD_NAME);

        try
        {
            MOCK_MANAGER.setIsMockUsed(true);

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getLoginInfo",
                new Class[] {},
                new LoginInfoImplForMock());

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.LoginInfoImpl",
                "getLoginId",
                new Class[] {},
                new Long(33381330001L));

            ThreadLocalSystemAttributesRegistry.setAttribute(
                "xblocks.gtl.attributes.systemtimestamp",
                new Timestamp(WEB3DateUtility.getDate("20090109", "yyyyMMdd").getTime()));

            TestDBUtility.deleteAll(InstitutionParams.TYPE);
            InstitutionParams l_institutionParams = new InstitutionParams();
            l_institutionParams.setInstitutionCode("0D");
            l_institutionParams.setInstitutionId(33);
            l_institutionParams.setCreatedTimestamp(WEB3DateUtility.getDate("20090306", "yyyyMMdd"));
            l_institutionParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20090306", "yyyyMMdd"));
            TestDBUtility.insertWithDel(l_institutionParams);

            TestDBUtility.deleteAll(BranchParams.TYPE);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setBranchId(33381);
            l_branchParams.setInstitutionCode("0D");
            l_branchParams.setInstitutionId(33);
            l_branchParams.setBranchCode("101");
            l_branchParams.setBranchName("�����x�X11");
            l_branchParams.setBranchNameAlt1("TOKYO BRANCH");
            l_branchParams.setBranchType(BranchTypeEnum.WEB_BRANCH);
            BranchParams l_branchParams2 = TestDBUtility.getBranchRow();
            l_branchParams2.setBranchId(33382);
            l_branchParams2.setInstitutionCode("0D");
            l_branchParams2.setInstitutionId(33);
            l_branchParams2.setBranchCode("102");
            l_branchParams2.setBranchName("�����x�X2");
            l_branchParams2.setBranchNameAlt1("TOKYO BRANCH2");
            l_branchParams2.setBranchType(BranchTypeEnum.WEB_BRANCH);
            TestDBUtility.insertWithDel(l_branchParams);
            TestDBUtility.insertWithDel(l_branchParams2);

            TestDBUtility.deleteAll(BranchPreferencesParams.TYPE);
            BranchPreferencesParams l_branchPreferencesParams = TestDBUtility.getBranchPreferencesRow();
            l_branchPreferencesParams.setBranchId(33381L);
            l_branchPreferencesParams.setValue("1");
            l_branchPreferencesParams.setNameSerialNo(1002);
            BranchPreferencesParams l_branchPreferencesParams2 = TestDBUtility.getBranchPreferencesRow();
            l_branchPreferencesParams2.setBranchId(33382L);
            l_branchPreferencesParams2.setValue("1");
            l_branchPreferencesParams2.setNameSerialNo(1003);
            TestDBUtility.insertWithDel(l_branchPreferencesParams);
            TestDBUtility.insertWithDel(l_branchPreferencesParams2);

            TestDBUtility.deleteAll(AdministratorParams.TYPE);
            AdministratorParams l_administratorParams = TestDBUtility.getAdministratorRow();
            l_administratorParams.setLoginId(33381330001L);
            l_administratorParams.setInstitutionCode("0D");
            l_administratorParams.setInstitutionId(33);
            l_administratorParams.setBranchCode("101");
            l_administratorParams.setBranchId(33381L);
            AdministratorParams l_administratorParams2 = TestDBUtility.getAdministratorRow();
            l_administratorParams2.setLoginId(33381330001L);
            l_administratorParams2.setInstitutionCode("0D");
            l_administratorParams2.setInstitutionId(33);
            l_administratorParams2.setBranchCode("102");
            l_administratorParams2.setBranchId(33382L);
            TestDBUtility.insertWithDel(l_administratorParams);
            TestDBUtility.insertWithDel(l_administratorParams2);

            TestDBUtility.deleteAll(AdministratorTypeParams.TYPE);
            AdministratorTypeParams l_administratorTypeParams = TestDBUtility.getAdministratorTypeRow();
            l_administratorParams.setLoginId(33381330001L);
            l_administratorTypeParams.setInstitutionCode("0D");
            l_administratorTypeParams.setPermissionLevel("331");
            l_administratorTypeParams.setDirAdminFlag(0);
            l_administratorTypeParams.setAllBranchPermissionFlag(BooleanEnum.TRUE);
            l_administratorTypeParams.setCreatedTimestamp(WEB3DateUtility.getDate("20090306", "yyyyMMdd"));
            l_administratorTypeParams.setUpdateTimestamp(WEB3DateUtility.getDate("20090306", "yyyyMMdd"));
            TestDBUtility.insertWithDel(l_administratorTypeParams);

            TestDBUtility.deleteAll(AdminPermissionParams.TYPE);
            AdminPermissionParams l_adminPermissionParams1 = TestDBUtility.getAdminPermissionRow();
            l_adminPermissionParams1.setInstitutionCode("0D");
            l_adminPermissionParams1.setPermissionLevel("331");
            l_adminPermissionParams1.setTransactionCategory("C0305");
            l_adminPermissionParams1.setUpdatePermissionFlag(BooleanEnum.TRUE);
            l_adminPermissionParams1.setCreatedTimestamp(WEB3DateUtility.getDate("20090306", "yyyyMMdd"));
            l_adminPermissionParams1.setUpdateTimestamp(WEB3DateUtility.getDate("20090306", "yyyyMMdd"));
            TestDBUtility.insertWithDel(l_adminPermissionParams1);

            TestDBUtility.deleteAll(ProcessManagementParams.TYPE);
            ProcessManagementParams l_processManagentParams = new ProcessManagementParams();
            l_processManagentParams.setProcessId("0001");
            l_processManagentParams.setInstitutionCode("0D");
            l_processManagentParams.setBranchCode("101");
            ProcessManagementParams l_processManagentParams2 = new ProcessManagementParams();
            l_processManagentParams2.setProcessId("0001");
            l_processManagentParams2.setInstitutionCode("0D");
            l_processManagentParams2.setBranchCode("102");
            TestDBUtility.insertWithDel(l_processManagentParams);
            TestDBUtility.insertWithDel(l_processManagentParams2);

            TestDBUtility.deleteAll(MainAccountParams.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(333812511906L);
            l_mainAccountParams.setInstitutionCode("0D");
            l_mainAccountParams.setAccountCode("123456");
            l_mainAccountParams.setBranchCode("101");
            MainAccountParams l_mainAccountParams2 = TestDBUtility.getMainAccountRow();
            l_mainAccountParams2.setAccountId(133812511906L);
            l_mainAccountParams2.setInstitutionCode("0D");
            l_mainAccountParams2.setAccountCode("123456");
            l_mainAccountParams2.setBranchCode("102");
            l_mainAccountParams2.setBranchId(33382L);
            l_mainAccountParams2.setFamilyName("�����@@�O�Y");
            TestDBUtility.insertWithDel(l_mainAccountParams);
            TestDBUtility.insertWithDel(l_mainAccountParams2);

            TestDBUtility.deleteAll(SubAccountParams.TYPE);
            SubAccountParams l_subAccuontParams = TestDBUtility.getSubAccountRow();
            l_subAccuontParams.setAccountId(333812511906L);
            l_subAccuontParams.setSubAccountId(33381251220301L);
            l_subAccuontParams.setInstitutionCode("0D");
            l_subAccuontParams.setInstitutionId(33);
            l_subAccuontParams.setBranchId(33381L);
            l_subAccuontParams.setSubAccountType(SubAccountTypeEnum.EQUITY_OPTIONS_SUB_ACCOUNT);
            SubAccountParams l_subAccuontParams2 = TestDBUtility.getSubAccountRow();
            l_subAccuontParams2.setAccountId(133812511906L);
            l_subAccuontParams2.setSubAccountId(13381251220301L);
            l_subAccuontParams2.setInstitutionCode("0D");
            l_subAccuontParams2.setInstitutionId(33);
            l_subAccuontParams2.setBranchId(33382L);
            l_subAccuontParams2.setSubAccountType(SubAccountTypeEnum.EQUITY_OPTIONS_SUB_ACCOUNT);
            TestDBUtility.insertWithDel(l_subAccuontParams);
            TestDBUtility.insertWithDel(l_subAccuontParams2);

            TestDBUtility.deleteAll(IfoDepositParams.TYPE);
            IfoDepositParams l_ifoDepositParams = new IfoDepositParams();
            l_ifoDepositParams.setBranchCode("101");
            l_ifoDepositParams.setInstitutionCode("0D");
            l_ifoDepositParams.setCalcDate(WEB3DateUtility.getDate("20090306", "yyyyMMdd"));
            l_ifoDepositParams.setAccountCode("123456");
            IfoDepositParams l_ifoDepositParams2 = new IfoDepositParams();
            l_ifoDepositParams2.setBranchCode("102");
            l_ifoDepositParams2.setInstitutionCode("0D");
            l_ifoDepositParams2.setCalcDate(WEB3DateUtility.getDate("20090306", "yyyyMMdd"));
            l_ifoDepositParams2.setAccountCode("123456");
            TestDBUtility.insertWithDel(l_ifoDepositParams);
            TestDBUtility.insertWithDel(l_ifoDepositParams2);

            TestDBUtility.deleteAll(TradingTimeRow.TYPE);
            TradingTimeParams l_tradingTimeParams1 = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams1.setInstitutionCode("0D");
            l_tradingTimeParams1.setBranchCode("123");
            l_tradingTimeParams1.setMarketCode("N1");
            l_tradingTimeParams1.setTradingTimeType("01");
            l_tradingTimeParams1.setBizDateType("1");
            l_tradingTimeParams1.setProductCode("0");
            TestDBUtility.insertWithDel(l_tradingTimeParams1);
            
            WEB3IfoDepositCalc l_ifoDepCalc = new WEB3IfoDepositCalcForTest();
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.ifodeposit.WEB3IfoDepositCalcServiceImpl", "getIfoDepositCalc", new Class[]
                { WEB3GentradeSubAccount.class },
                l_ifoDepCalc);

            WEB3AdminIfoDepShortageDownloadRequest l_request =
                new WEB3AdminIfoDepShortageDownloadRequest();

            l_request.searchDate = WEB3DateUtility.getDate("20090306", "yyyyMMdd");
            String[] l_branchCodeList = new String[2];
            l_branchCodeList[0] = "101";
            l_branchCodeList[1] = "102";
            l_request.branchCode = l_branchCodeList;
            l_request.accountCode = "123456";
            l_request.uncancelDiv = "0";
            l_request.pageIndex = "1";
            l_request.pageSize = "10";
            WEB3IfoDepShortageSortKey[] l_sortKeys = new WEB3IfoDepShortageSortKey[1];
            l_sortKeys[0] = new WEB3IfoDepShortageSortKey();
            l_sortKeys[0].keyItem = "branchCode";
            l_sortKeys[0].ascDesc = "A";
            l_request.sortKeys = l_sortKeys;

            l_response = new WEB3AdminIfoDepShortageDownloadResponse();
            l_response = (WEB3AdminIfoDepShortageDownloadResponse)l_impl.execute(l_request);

            assertEquals("CSV�o�͓�,2009/01/09 00:00:00,�������t,2009/03/06", l_response.downloadFile[0]);
            assertEquals("���X,�ڋq,�ڋq��,�����z,���݂̖��������z,���݂̏؋������v�z,���ʗL���iOP���������j,�����L���iOP���������j", l_response.downloadFile[1]);
            assertEquals("101,123456,�����@@�l�Y,,3,4,��,�L", l_response.downloadFile[2]);
            assertEquals("102,123456,�����@@�O�Y,,3,4,��,�L", l_response.downloadFile[3]);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }

        log.exiting(STR_METHOD_NAME);
    }


    private class WEB3IfoDepositCalcForTest extends WEB3IfoDepositCalc
    {

        public double calcNextBizDateDemandAmount()
        {
            return 1.0;
            
        }
        
        public double getCurrentBizDateDemandAmount()
        {
            return 2.0;
        }
        
        public double calcNonPayAmount()
        {
            return 3.0;
        }
        
        public double calcIfoDepositRequiredAmount()
        {
            return 4.0;
        }
        
        public double calcBuyContractQty(int l_intReservedDate)
        {
            return 5.0;
        }
        
        public double calcSellContractQty(int l_intReservedDate)
        {
            return 6.0;
        }
        
        public double calcBuyOrderQty(int l_intReservedDate)
        {
            return 7.0;
        }
        
        public double calcSellOrderQty(int l_intReservedDate)
        {
            return 8.0;
        }
        public WEB3IfoDepositCalcCondition getIfoDepositCalcCondition()
        {
            WEB3IfoDepositCalcCondition l_condition = new WEB3IfoDepositCalcCondition();
            l_condition.setIfoDepositCalcBaseDate(1);
            return l_condition;
        }
    }

    public class WEB3AdminIfoDepShortageDownloadServiceImplForTest extends WEB3AdminIfoDepShortageDownloadServiceImpl
    {
        public WEB3AdminIfoDepShortageDownloadResponse getDownloadFile(WEB3AdminIfoDepShortageDownloadRequest l_request) throws WEB3BaseException
        {
            final String STR_METHOD_NAME = "execute(WEB3GenRequest)";
            log.entering(STR_METHOD_NAME);

            WEB3AdminIfoDepShortageDownloadResponse l_response = (WEB3AdminIfoDepShortageDownloadResponse)l_request.createResponse();
            l_response.errorMessage = "OK";

            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
    }
}
@
