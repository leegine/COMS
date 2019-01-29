head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.00.52.24;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3AdminSrvRegiAccountRegistServiceImplTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : サービス利用管理者顧客登録サービスImplのテストクラス(WEB3AdminSrvRegiAccountRegistServiceImplTest.java)
Author Name      : Daiwa Institute of Research
Revision History : 2008/02/28 周墨洋 新規作成
*/
package webbroker3.srvregi.service.delegate.stdimpls;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.List;

import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.plugin.security.oplogin.impl.LoginInfoImpl;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccountTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.BranchParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.InstitutionParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.SubAccountParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.TraderParams;

import test.util.TestDBUtility;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3TransactionCategoryDef;
import webbroker3.gentrade.WEB3Administrator;
import webbroker3.gentrade.WEB3AdministratorForMock;
import webbroker3.gentrade.data.AdministratorParams;
import webbroker3.gentrade.data.SrvRegiApplicationParams;
import webbroker3.mock.TestBaseForMock;
import webbroker3.srvregi.WEB3SrvRegiNewAppliSpec;
import webbroker3.srvregi.data.OtherOrgInfoAdminParams;
import webbroker3.srvregi.data.SrvRegiHistoryParams;
import webbroker3.srvregi.data.SrvRegiMasterParams;
import webbroker3.srvregi.data.SrvRegiSetupParams;
import webbroker3.srvregi.message.WEB3AdminSrvRegiCustomerRegistCompleteRequest;
import webbroker3.util.WEB3LogUtility;

/**
 * (サービス利用管理者顧客登録サービスImplのテストクラス)<BR>
 * サービス利用管理者顧客登録サービス実装のテストクラス<BR>
 * @@author 周墨洋
 * @@version 1.0
 */
public class WEB3AdminSrvRegiAccountRegistServiceImplTest extends TestBaseForMock
{

    /**
     * ログユーティリティ<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(
            WEB3AdminSrvRegiAccountRegistServiceImplTest.class);

    /**
     * サービス利用管理者顧客登録サービスImpl
     */
    private WEB3AdminSrvRegiAccountRegistServiceImpl l_impl;

    /**
     * @@param arg0
     */
    public WEB3AdminSrvRegiAccountRegistServiceImplTest(String arg0)
    {
        super(arg0);
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
     *
     */
    public void testValidateAppliRegist_case0001()
    {
        final String STR_METHOD_NAME = "testValidateAppliRegist_case0001()";
        log.entering(TEST_START + STR_METHOD_NAME);

        this.l_impl = new WEB3AdminSrvRegiAccountRegistServiceImpl();

        try
        {
            MainAccountParams l_mainAccountParams =
                TestDBUtility.getMainAccountRow();
            TestDBUtility.deleteAll(MainAccountParams.TYPE);
            TestDBUtility.insertWithDel(l_mainAccountParams);

            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            TestDBUtility.deleteAll(BranchParams.TYPE);
            l_branchParams.setBranchCode(
                l_mainAccountParams.getBranchCode());
            l_branchParams.setInstitutionCode(
                l_mainAccountParams.getInstitutionCode());
            TestDBUtility.insertWithDel(l_branchParams);

            InstitutionParams l_institutionParams =
                TestDBUtility.getInstitutionRow();
            TestDBUtility.deleteAll(InstitutionParams.TYPE);
            l_institutionParams.setInstitutionCode(
                l_mainAccountParams.getInstitutionCode());
            TestDBUtility.insertWithDel(l_institutionParams);

            SubAccountParams l_subAccountParams =
                TestDBUtility.getSubAccountRow();
            TestDBUtility.deleteAll(SubAccountParams.TYPE);
            l_subAccountParams.setAccountId(
                l_mainAccountParams.getAccountId());
            l_subAccountParams.setBranchId(
                l_branchParams.getBranchId());
            l_subAccountParams.setInstitutionId(
                l_mainAccountParams.getInstitutionId());
            l_subAccountParams.setInstitutionCode(
                l_mainAccountParams.getInstitutionCode());
            l_subAccountParams.setSubAccountType(
                SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
            TestDBUtility.insertWithDel(l_subAccountParams);

            SrvRegiMasterParams l_srvRegiMasterParams =
                TestDBUtility.getSrvRegiMasterRow();
            TestDBUtility.deleteAll(SrvRegiMasterParams.TYPE);
            l_srvRegiMasterParams.setSrvDiv("1");
            l_srvRegiMasterParams.setInstitutionCode(
                l_mainAccountParams.getInstitutionCode());
            l_srvRegiMasterParams.setSrvStatus("2");
            l_srvRegiMasterParams.setSpecialProcessDiv("1");
            TestDBUtility.insertWithDel(l_srvRegiMasterParams);

            SrvRegiSetupParams l_srvRegiSetupParams =
                TestDBUtility.getSrvRegiSetupRow();
            TestDBUtility.deleteAll(SrvRegiSetupParams.TYPE);
            l_srvRegiSetupParams.setSrvDiv("1");
            l_srvRegiSetupParams.setInstitutionCode(
                l_mainAccountParams.getInstitutionCode());
            l_srvRegiSetupParams.setLotDiv("2");
            TestDBUtility.insertWithDel(l_srvRegiSetupParams);

            TestDBUtility.deleteAll(OtherOrgInfoAdminParams.TYPE);

            WEB3SrvRegiNewAppliSpec l_srvRegiNewAppliSpec =
                new WEB3SrvRegiNewAppliSpec();
            l_srvRegiNewAppliSpec.setInstitutionCode(
                l_mainAccountParams.getInstitutionCode());
            l_srvRegiNewAppliSpec.setSrvDiv("1");

            Method l_method =
                WEB3AdminSrvRegiAccountRegistServiceImpl.class.getDeclaredMethod(
                    "validateAppliRegist",
                    new Class[] {WEB3SrvRegiNewAppliSpec.class});
            l_method.setAccessible(true);
            l_method.invoke(
                this.l_impl,
                new Object[] {l_srvRegiNewAppliSpec});

            fail();
        }
        catch (NoSuchMethodException l_exNSME)
        {
            log.debug(STR_METHOD_NAME, l_exNSME);
            fail();
        }
        catch (IllegalAccessException l_exIAE)
        {
            log.debug(STR_METHOD_NAME, l_exIAE);
            fail();
        }
        catch (InvocationTargetException l_exITE)
        {
            log.debug(STR_METHOD_NAME, l_exITE);
            assertEquals(
                WEB3BusinessLayerException.class,
                l_exITE.getTargetException().getClass());
            assertEquals(
                WEB3ErrorCatalog.BUSINESS_ERROR_03019,
                ((WEB3BusinessLayerException)l_exITE.getTargetException()).getErrorInfo());
        }
        catch (WEB3BaseException l_exBE)
        {
            log.debug(STR_METHOD_NAME, l_exBE);
            fail();
        }
        catch (Exception l_exE)
        {
            log.debug(STR_METHOD_NAME, l_exE);
            fail();
        }
        finally
        {
            log.entering(TEST_END + STR_METHOD_NAME);
        }
    }

    /**
     *
     */
    public void testValidateAppliRegist_case0002()
    {
        final String STR_METHOD_NAME = "testValidateAppliRegist_case0002()";
        log.entering(TEST_START + STR_METHOD_NAME);

        this.l_impl = new WEB3AdminSrvRegiAccountRegistServiceImpl();

        try
        {
            MainAccountParams l_mainAccountParams =
                TestDBUtility.getMainAccountRow();
            TestDBUtility.deleteAll(MainAccountParams.TYPE);
            TestDBUtility.insertWithDel(l_mainAccountParams);

            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            TestDBUtility.deleteAll(BranchParams.TYPE);
            l_branchParams.setBranchCode(
                l_mainAccountParams.getBranchCode());
            l_branchParams.setInstitutionCode(
                l_mainAccountParams.getInstitutionCode());
            TestDBUtility.insertWithDel(l_branchParams);

            InstitutionParams l_institutionParams =
                TestDBUtility.getInstitutionRow();
            TestDBUtility.deleteAll(InstitutionParams.TYPE);
            l_institutionParams.setInstitutionCode(
                l_mainAccountParams.getInstitutionCode());
            TestDBUtility.insertWithDel(l_institutionParams);

            SubAccountParams l_subAccountParams =
                TestDBUtility.getSubAccountRow();
            TestDBUtility.deleteAll(SubAccountParams.TYPE);
            l_subAccountParams.setAccountId(
                l_mainAccountParams.getAccountId());
            l_subAccountParams.setBranchId(
                l_branchParams.getBranchId());
            l_subAccountParams.setInstitutionId(
                l_mainAccountParams.getInstitutionId());
            l_subAccountParams.setInstitutionCode(
                l_mainAccountParams.getInstitutionCode());
            l_subAccountParams.setSubAccountType(
                SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
            TestDBUtility.insertWithDel(l_subAccountParams);

            SrvRegiMasterParams l_srvRegiMasterParams =
                TestDBUtility.getSrvRegiMasterRow();
            TestDBUtility.deleteAll(SrvRegiMasterParams.TYPE);
            l_srvRegiMasterParams.setSrvDiv("1");
            l_srvRegiMasterParams.setInstitutionCode(
                l_mainAccountParams.getInstitutionCode());
            l_srvRegiMasterParams.setSrvStatus("2");
            l_srvRegiMasterParams.setSpecialProcessDiv("1");
            TestDBUtility.insertWithDel(l_srvRegiMasterParams);

            SrvRegiSetupParams l_srvRegiSetupParams =
                TestDBUtility.getSrvRegiSetupRow();
            TestDBUtility.deleteAll(SrvRegiSetupParams.TYPE);
            l_srvRegiSetupParams.setSrvDiv("1");
            l_srvRegiSetupParams.setInstitutionCode(
                l_mainAccountParams.getInstitutionCode());
            l_srvRegiSetupParams.setLotDiv("0");
            TestDBUtility.insertWithDel(l_srvRegiSetupParams);

            OtherOrgInfoAdminParams l_otherOrgInfoAdminParams =
                new OtherOrgInfoAdminParams();
            TestDBUtility.deleteAll(OtherOrgInfoAdminParams.TYPE);
            l_otherOrgInfoAdminParams.setSequenceNumber(123);
            l_otherOrgInfoAdminParams.setSrvDiv("1");
            l_otherOrgInfoAdminParams.setId("1");
            l_otherOrgInfoAdminParams.setPassword("1");
            l_otherOrgInfoAdminParams.setStatus("9");
            l_otherOrgInfoAdminParams.setInstitutionCode(
                l_mainAccountParams.getInstitutionCode());
            l_otherOrgInfoAdminParams.setBranchCode(
                l_mainAccountParams.getBranchCode());
            l_otherOrgInfoAdminParams.setAccountCode(
                l_mainAccountParams.getAccountCode());
            l_otherOrgInfoAdminParams.setAppliStartDate(new Date());
            l_otherOrgInfoAdminParams.setAppliEndDate(new Date());
            l_otherOrgInfoAdminParams.setLastUpdater("1");
            l_otherOrgInfoAdminParams.setCreatedTimestamp(new Date());
            l_otherOrgInfoAdminParams.setLastUpdatedTimestamp(new Date());
            TestDBUtility.insertWithDel(l_otherOrgInfoAdminParams);

            WEB3SrvRegiNewAppliSpec l_srvRegiNewAppliSpec =
                new WEB3SrvRegiNewAppliSpec();
            l_srvRegiNewAppliSpec.setInstitutionCode(
                l_mainAccountParams.getInstitutionCode());
            l_srvRegiNewAppliSpec.setSrvDiv("1");

            Method l_method =
                WEB3AdminSrvRegiAccountRegistServiceImpl.class.getDeclaredMethod(
                    "validateAppliRegist",
                    new Class[] {WEB3SrvRegiNewAppliSpec.class});
            l_method.setAccessible(true);
            l_method.invoke(
                this.l_impl,
                new Object[] {l_srvRegiNewAppliSpec});

            assertTrue(true);
        }
        catch (NoSuchMethodException l_exNSME)
        {
            log.debug(STR_METHOD_NAME, l_exNSME);
            fail();
        }
        catch (IllegalAccessException l_exIAE)
        {
            log.debug(STR_METHOD_NAME, l_exIAE);
            fail();
        }
        catch (InvocationTargetException l_exITE)
        {
            log.debug(STR_METHOD_NAME, l_exITE);
            fail();
        }
        catch (WEB3BaseException l_exBE)
        {
            log.debug(STR_METHOD_NAME, l_exBE);
            fail();
        }
        catch (Exception l_exE)
        {
            log.debug(STR_METHOD_NAME, l_exE);
            fail();
        }
        finally
        {
            log.entering(TEST_END + STR_METHOD_NAME);
        }
    }

    /**
     *
     */
    public void testSubmitRegist_case0001()
    {
        final String STR_METHOD_NAME = "testSubmitRegist_case0001()";
        log.entering(TEST_START + STR_METHOD_NAME);

        this.l_impl = new WEB3AdminSrvRegiAccountRegistServiceImpl();

        try
        {
            MainAccountParams l_mainAccountParams =
                TestDBUtility.getMainAccountRow();
            TestDBUtility.deleteAll(MainAccountParams.TYPE);
            TestDBUtility.insertWithDel(l_mainAccountParams);

            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            TestDBUtility.deleteAll(BranchParams.TYPE);
            l_branchParams.setBranchCode(
                l_mainAccountParams.getBranchCode());
            l_branchParams.setInstitutionCode(
                l_mainAccountParams.getInstitutionCode());
            TestDBUtility.insertWithDel(l_branchParams);

            InstitutionParams l_institutionParams =
                TestDBUtility.getInstitutionRow();
            TestDBUtility.deleteAll(InstitutionParams.TYPE);
            l_institutionParams.setInstitutionCode(
                l_mainAccountParams.getInstitutionCode());
            TestDBUtility.insertWithDel(l_institutionParams);

            SubAccountParams l_subAccountParams =
                TestDBUtility.getSubAccountRow();
            TestDBUtility.deleteAll(SubAccountParams.TYPE);
            l_subAccountParams.setAccountId(
                l_mainAccountParams.getAccountId());
            l_subAccountParams.setBranchId(
                l_branchParams.getBranchId());
            l_subAccountParams.setInstitutionId(
                l_mainAccountParams.getInstitutionId());
            l_subAccountParams.setInstitutionCode(
                l_mainAccountParams.getInstitutionCode());
            l_subAccountParams.setSubAccountType(
                SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
            TestDBUtility.insertWithDel(l_subAccountParams);

            AdministratorParams l_administratorParams =
                TestDBUtility.getAdministratorRow();
            TestDBUtility.deleteAll(AdministratorParams.TYPE);
            l_administratorParams.setBranchId(
                l_branchParams.getBranchId());
            l_administratorParams.setInstitutionId(
                l_mainAccountParams.getInstitutionId());
            l_administratorParams.setInstitutionCode(
                l_mainAccountParams.getInstitutionCode());
            TestDBUtility.insertWithDel(l_administratorParams);

            SrvRegiMasterParams l_srvRegiMasterParams =
                TestDBUtility.getSrvRegiMasterRow();
            TestDBUtility.deleteAll(SrvRegiMasterParams.TYPE);
            l_srvRegiMasterParams.setSrvDiv("12");
            l_srvRegiMasterParams.setInstitutionCode(
                l_mainAccountParams.getInstitutionCode());
            l_srvRegiMasterParams.setSrvStatus("2");
            l_srvRegiMasterParams.setSpecialProcessDiv("1");
            TestDBUtility.insertWithDel(l_srvRegiMasterParams);

            SrvRegiSetupParams l_srvRegiSetupParams =
                TestDBUtility.getSrvRegiSetupRow();
            TestDBUtility.deleteAll(SrvRegiSetupParams.TYPE);
            l_srvRegiSetupParams.setSrvDiv("12");
            l_srvRegiSetupParams.setInstitutionCode(
                l_mainAccountParams.getInstitutionCode());
            l_srvRegiSetupParams.setLotDiv("0");
            TestDBUtility.insertWithDel(l_srvRegiSetupParams);

            SrvRegiApplicationParams l_srvRegiApplicationParams =
                TestDBUtility.getSrvRegiApplicationParams();
            TestDBUtility.deleteAll(SrvRegiApplicationParams.TYPE);
            l_srvRegiApplicationParams.setSrvDiv("12");
            l_srvRegiApplicationParams.setInstitutionCode(
                l_mainAccountParams.getInstitutionCode());
            l_srvRegiApplicationParams.setBranchCode(
                l_mainAccountParams.getBranchCode());
            l_srvRegiApplicationParams.setAccountCode(
                l_mainAccountParams.getAccountCode());
            l_srvRegiApplicationParams.setCancelDiv("0");
            l_srvRegiApplicationParams.setEffectiveDiv("0");
            TestDBUtility.insertWithDel(l_srvRegiApplicationParams);

            OtherOrgInfoAdminParams l_otherOrgInfoAdminParams =
                new OtherOrgInfoAdminParams();
            TestDBUtility.deleteAll(OtherOrgInfoAdminParams.TYPE);
            l_otherOrgInfoAdminParams.setSequenceNumber(123);
            l_otherOrgInfoAdminParams.setSrvDiv("12");
            l_otherOrgInfoAdminParams.setId("1");
            l_otherOrgInfoAdminParams.setPassword("1");
            l_otherOrgInfoAdminParams.setStatus("9");
            l_otherOrgInfoAdminParams.setInstitutionCode(
                l_mainAccountParams.getInstitutionCode());
            l_otherOrgInfoAdminParams.setBranchCode(
                l_mainAccountParams.getBranchCode());
            l_otherOrgInfoAdminParams.setAccountCode(
                l_mainAccountParams.getAccountCode());
            l_otherOrgInfoAdminParams.setAppliStartDate(new Date());
            l_otherOrgInfoAdminParams.setAppliEndDate(new Date());
            l_otherOrgInfoAdminParams.setLastUpdater("1");
            l_otherOrgInfoAdminParams.setCreatedTimestamp(new Date());
            l_otherOrgInfoAdminParams.setLastUpdatedTimestamp(new Date());
            TestDBUtility.insertWithDel(l_otherOrgInfoAdminParams);

            SrvRegiHistoryParams l_srvRegiHistoryParams =
                new SrvRegiHistoryParams();
            TestDBUtility.deleteAll(SrvRegiHistoryParams.TYPE);
            l_srvRegiHistoryParams.setSrvDiv("12");
            l_srvRegiHistoryParams.setInstitutionCode(
                l_mainAccountParams.getInstitutionCode());
            l_srvRegiHistoryParams.setBranchCode(
                l_mainAccountParams.getBranchCode());
            l_srvRegiHistoryParams.setAccountCode(
                l_mainAccountParams.getAccountCode());
            l_srvRegiHistoryParams.setOrderRootDiv("1");
            l_srvRegiHistoryParams.setRegistDate(new Date());
            l_srvRegiHistoryParams.setLastUpdater("1");
            l_srvRegiHistoryParams.setCreatedTimestamp(new Date());
            l_srvRegiHistoryParams.setLastUpdatedTimestamp(new Date());
            TestDBUtility.insertWithDel(l_srvRegiHistoryParams);

            TraderParams l_traderParams = TestDBUtility.getTraderRow();
            TestDBUtility.deleteAll(TraderParams.TYPE);
            l_traderParams.setBranchCode(l_mainAccountParams.getBranchCode());
            l_traderParams.setBranchId(l_mainAccountParams.getBranchId());
            l_traderParams.setLoginId(l_administratorParams.getLoginId());
            TestDBUtility.insertWithDel(l_traderParams);

            WEB3AdminSrvRegiCustomerRegistCompleteRequest l_request =
                new WEB3AdminSrvRegiCustomerRegistCompleteRequest();
            l_request.serviceDiv = "12";
            l_request.branchCode = l_mainAccountParams.getBranchCode();
            l_request.accountCode =
                l_mainAccountParams.getAccountCode().substring(0, 6);
            l_request.trialStartDate = new Date();
            l_request.trialEndDate = new Date();
            l_request.chargeAmt = "1";
            l_request.applyDate = new Date();
            l_request.registDiv = "1";
            l_request.password = "123";

            MOCK_MANAGER.setIsMockUsed(true);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getLoginInfo",
                new Class[] {},
                new LoginInfoTest());

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getSessionProperty",
                new Class[] {String.class},
                new Long(l_mainAccountParams.getInstitutionId()));

            WEB3Administrator l_administrator =
                new WEB3Administrator(l_administratorParams);

            WEB3AdministratorForMock.mockGetInstanceFromLoginInfo(
                l_administrator);
            WEB3AdministratorForMock.mockValidateAuthority(
                l_administrator,
                WEB3TransactionCategoryDef.SRVREGI_ACCOUNT,
                true,
                true);
            WEB3AdministratorForMock.mockValidateBranchPermission(
                l_administrator,
                l_mainAccountParams.getBranchCode(),
                true);
            WEB3AdministratorForMock.mockValidateTradingPassword(
                l_request.password,
                true);

            this.l_impl.submitRegist(l_request);


            QueryProcessor l_processor = Processors.getDefaultProcessor();
            List l_list = l_processor.doFindAllQuery(OtherOrgInfoAdminParams.TYPE);
            assertEquals(1, l_list.size());
            OtherOrgInfoAdminParams l_resultOtherOrgInfoAdminParams =
                (OtherOrgInfoAdminParams)l_list.get(0);
            assertEquals("0D",
                l_resultOtherOrgInfoAdminParams.getInstitutionCode());
            assertEquals("381",
                l_resultOtherOrgInfoAdminParams.getBranchCode());
            assertEquals("2512246",
                l_resultOtherOrgInfoAdminParams.getAccountCode());
            assertEquals("12",
                l_resultOtherOrgInfoAdminParams.getSrvDiv());
            assertEquals("0",
                l_resultOtherOrgInfoAdminParams.getStatus());
        }
        catch (WEB3BaseException l_exBE)
        {
            log.debug(STR_METHOD_NAME, l_exBE);
            fail();
        }
        catch (Exception l_exE)
        {
            log.debug(STR_METHOD_NAME, l_exE);
            fail();
        }
        finally
        {
            log.entering(TEST_END + STR_METHOD_NAME);
        }
    }

    /**
     *
     */
    public void testSubmitRegist_case0002()
    {
        final String STR_METHOD_NAME = "testSubmitRegist_case0002()";
        log.entering(TEST_START + STR_METHOD_NAME);

        this.l_impl = new WEB3AdminSrvRegiAccountRegistServiceImpl();

        try
        {
            MainAccountParams l_mainAccountParams =
                TestDBUtility.getMainAccountRow();
            TestDBUtility.deleteAll(MainAccountParams.TYPE);
            TestDBUtility.insertWithDel(l_mainAccountParams);

            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            TestDBUtility.deleteAll(BranchParams.TYPE);
            l_branchParams.setBranchCode(
                l_mainAccountParams.getBranchCode());
            l_branchParams.setInstitutionCode(
                l_mainAccountParams.getInstitutionCode());
            TestDBUtility.insertWithDel(l_branchParams);

            InstitutionParams l_institutionParams =
                TestDBUtility.getInstitutionRow();
            TestDBUtility.deleteAll(InstitutionParams.TYPE);
            l_institutionParams.setInstitutionCode(
                l_mainAccountParams.getInstitutionCode());
            TestDBUtility.insertWithDel(l_institutionParams);

            SubAccountParams l_subAccountParams =
                TestDBUtility.getSubAccountRow();
            TestDBUtility.deleteAll(SubAccountParams.TYPE);
            l_subAccountParams.setAccountId(
                l_mainAccountParams.getAccountId());
            l_subAccountParams.setBranchId(
                l_branchParams.getBranchId());
            l_subAccountParams.setInstitutionId(
                l_mainAccountParams.getInstitutionId());
            l_subAccountParams.setInstitutionCode(
                l_mainAccountParams.getInstitutionCode());
            l_subAccountParams.setSubAccountType(
                SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
            TestDBUtility.insertWithDel(l_subAccountParams);

            AdministratorParams l_administratorParams =
                TestDBUtility.getAdministratorRow();
            TestDBUtility.deleteAll(AdministratorParams.TYPE);
            l_administratorParams.setBranchId(
                l_branchParams.getBranchId());
            l_administratorParams.setInstitutionId(
                l_mainAccountParams.getInstitutionId());
            l_administratorParams.setInstitutionCode(
                l_mainAccountParams.getInstitutionCode());
            TestDBUtility.insertWithDel(l_administratorParams);

            SrvRegiMasterParams l_srvRegiMasterParams =
                TestDBUtility.getSrvRegiMasterRow();
            TestDBUtility.deleteAll(SrvRegiMasterParams.TYPE);
            l_srvRegiMasterParams.setSrvDiv("12");
            l_srvRegiMasterParams.setInstitutionCode(
                l_mainAccountParams.getInstitutionCode());
            l_srvRegiMasterParams.setSrvStatus("2");
            l_srvRegiMasterParams.setSpecialProcessDiv("0");
            TestDBUtility.insertWithDel(l_srvRegiMasterParams);

            SrvRegiSetupParams l_srvRegiSetupParams =
                TestDBUtility.getSrvRegiSetupRow();
            TestDBUtility.deleteAll(SrvRegiSetupParams.TYPE);
            l_srvRegiSetupParams.setSrvDiv("12");
            l_srvRegiSetupParams.setInstitutionCode(
                l_mainAccountParams.getInstitutionCode());
            l_srvRegiSetupParams.setLotDiv("0");
            TestDBUtility.insertWithDel(l_srvRegiSetupParams);

            SrvRegiApplicationParams l_srvRegiApplicationParams =
                TestDBUtility.getSrvRegiApplicationParams();
            TestDBUtility.deleteAll(SrvRegiApplicationParams.TYPE);
            l_srvRegiApplicationParams.setSrvDiv("12");
            l_srvRegiApplicationParams.setInstitutionCode(
                l_mainAccountParams.getInstitutionCode());
            l_srvRegiApplicationParams.setBranchCode(
                l_mainAccountParams.getBranchCode());
            l_srvRegiApplicationParams.setAccountCode(
                l_mainAccountParams.getAccountCode());
            l_srvRegiApplicationParams.setCancelDiv("0");
            l_srvRegiApplicationParams.setEffectiveDiv("0");
            TestDBUtility.insertWithDel(l_srvRegiApplicationParams);

            OtherOrgInfoAdminParams l_otherOrgInfoAdminParams =
                new OtherOrgInfoAdminParams();
            TestDBUtility.deleteAll(OtherOrgInfoAdminParams.TYPE);
            l_otherOrgInfoAdminParams.setSequenceNumber(123);
            l_otherOrgInfoAdminParams.setSrvDiv("12");
            l_otherOrgInfoAdminParams.setId("1");
            l_otherOrgInfoAdminParams.setPassword("1");
            l_otherOrgInfoAdminParams.setStatus("9");
            l_otherOrgInfoAdminParams.setInstitutionCode(
                l_mainAccountParams.getInstitutionCode());
            l_otherOrgInfoAdminParams.setBranchCode(
                l_mainAccountParams.getBranchCode());
            l_otherOrgInfoAdminParams.setAccountCode(
                l_mainAccountParams.getAccountCode());
            l_otherOrgInfoAdminParams.setAppliStartDate(new Date());
            l_otherOrgInfoAdminParams.setAppliEndDate(new Date());
            l_otherOrgInfoAdminParams.setLastUpdater("1");
            l_otherOrgInfoAdminParams.setCreatedTimestamp(new Date());
            l_otherOrgInfoAdminParams.setLastUpdatedTimestamp(new Date());
            TestDBUtility.insertWithDel(l_otherOrgInfoAdminParams);

            SrvRegiHistoryParams l_srvRegiHistoryParams =
                new SrvRegiHistoryParams();
            TestDBUtility.deleteAll(SrvRegiHistoryParams.TYPE);
            l_srvRegiHistoryParams.setSrvDiv("12");
            l_srvRegiHistoryParams.setInstitutionCode(
                l_mainAccountParams.getInstitutionCode());
            l_srvRegiHistoryParams.setBranchCode(
                l_mainAccountParams.getBranchCode());
            l_srvRegiHistoryParams.setAccountCode(
                l_mainAccountParams.getAccountCode());
            l_srvRegiHistoryParams.setOrderRootDiv("1");
            l_srvRegiHistoryParams.setRegistDate(new Date());
            l_srvRegiHistoryParams.setLastUpdater("1");
            l_srvRegiHistoryParams.setCreatedTimestamp(new Date());
            l_srvRegiHistoryParams.setLastUpdatedTimestamp(new Date());
            TestDBUtility.insertWithDel(l_srvRegiHistoryParams);

            TraderParams l_traderParams = TestDBUtility.getTraderRow();
            TestDBUtility.deleteAll(TraderParams.TYPE);
            l_traderParams.setBranchCode(l_mainAccountParams.getBranchCode());
            l_traderParams.setBranchId(l_mainAccountParams.getBranchId());
            l_traderParams.setLoginId(l_administratorParams.getLoginId());
            TestDBUtility.insertWithDel(l_traderParams);

            WEB3AdminSrvRegiCustomerRegistCompleteRequest l_request =
                new WEB3AdminSrvRegiCustomerRegistCompleteRequest();
            l_request.serviceDiv = "12";
            l_request.branchCode = l_mainAccountParams.getBranchCode();
            l_request.accountCode =
                l_mainAccountParams.getAccountCode().substring(0, 6);
            l_request.trialStartDate = new Date();
            l_request.trialEndDate = new Date();
            l_request.chargeAmt = "1";
            l_request.applyDate = new Date();
            l_request.registDiv = "1";
            l_request.password = "123";

            MOCK_MANAGER.setIsMockUsed(true);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getLoginInfo",
                new Class[] {},
                new LoginInfoTest());

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getSessionProperty",
                new Class[] {String.class},
                new Long(l_mainAccountParams.getInstitutionId()));

            WEB3Administrator l_administrator =
                new WEB3Administrator(l_administratorParams);

            WEB3AdministratorForMock.mockGetInstanceFromLoginInfo(
                l_administrator);
            WEB3AdministratorForMock.mockValidateAuthority(
                l_administrator,
                WEB3TransactionCategoryDef.SRVREGI_ACCOUNT,
                true,
                true);
            WEB3AdministratorForMock.mockValidateBranchPermission(
                l_administrator,
                l_mainAccountParams.getBranchCode(),
                true);
            WEB3AdministratorForMock.mockValidateTradingPassword(
                l_request.password,
                true);

            this.l_impl.submitRegist(l_request);


            QueryProcessor l_processor = Processors.getDefaultProcessor();
            List l_list = l_processor.doFindAllQuery(OtherOrgInfoAdminParams.TYPE);
            assertEquals(1, l_list.size());
            OtherOrgInfoAdminParams l_resultOtherOrgInfoAdminParams =
                (OtherOrgInfoAdminParams)l_list.get(0);
            assertEquals("0D",
                l_resultOtherOrgInfoAdminParams.getInstitutionCode());
            assertEquals("381",
                l_resultOtherOrgInfoAdminParams.getBranchCode());
            assertEquals("2512246",
                l_resultOtherOrgInfoAdminParams.getAccountCode());
            assertEquals("12",
                l_resultOtherOrgInfoAdminParams.getSrvDiv());
            assertEquals("9",
                l_resultOtherOrgInfoAdminParams.getStatus());
        }
        catch (WEB3BaseException l_exBE)
        {
            log.debug(STR_METHOD_NAME, l_exBE);
            fail();
        }
        catch (Exception l_exE)
        {
            log.debug(STR_METHOD_NAME, l_exE);
            fail();
        }
        finally
        {
            log.entering(TEST_END + STR_METHOD_NAME);
        }
    }

    /**
     *
     */
    public class LoginInfoTest extends LoginInfoImpl
    {
        /**
         *
         * @@return l_lngLoginId
         */
        public long getLoginId()
        {
            long l_lngLoginId = 33381330003L;
            return l_lngLoginId;
        }
    }

}
@
