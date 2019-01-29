head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.00.51.31;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3AdminSrvRegiAccountChangeServiceImplTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : サービス利用管理者顧客変更サービスImplのテストクラス(WEB3AdminSrvRegiAccountChangeServiceImplTest.java)
Author Name      : Daiwa Institute of Research
Revision History : 2008/02/28 周墨洋 新規作成
*/
package webbroker3.srvregi.service.delegate.stdimpls;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Date;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.plugin.security.oplogin.OpLoginSecurityService;
import com.fitechlabs.xtrade.plugin.security.oplogin.impl.LoginInfoImpl;
import com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImplForMock;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccountTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.BranchParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.InstitutionParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.SubAccountParams;

import test.util.TestDBUtility;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3TransactionCategoryDef;
import webbroker3.gentrade.WEB3Administrator;
import webbroker3.gentrade.WEB3AdministratorForMock;
import webbroker3.gentrade.data.AdministratorParams;
import webbroker3.gentrade.data.AdministratorTypeParams;
import webbroker3.gentrade.data.SrvRegiApplicationParams;
import webbroker3.mock.TestBaseForMock;
import webbroker3.srvregi.WEB3SrvRegiChangeAppliSpec;
import webbroker3.srvregi.data.OtherOrgInfoAdminParams;
import webbroker3.srvregi.data.SrvRegiHistoryParams;
import webbroker3.srvregi.data.SrvRegiMasterParams;
import webbroker3.srvregi.data.SrvRegiSetupParams;
import webbroker3.srvregi.message.WEB3AdminSrvRegiCustomerChangeCompleteRequest;
import webbroker3.srvregi.message.WEB3AdminSrvRegiCustomerChangeGroup;
import webbroker3.util.WEB3LogUtility;

/**
 * (サービス利用管理者顧客変更サービスImplのテストクラス)<BR>
 * サービス利用管理者顧客変更サービス実装のテストクラス<BR>
 * @@author 周墨洋
 * @@version 1.0
 */
public class WEB3AdminSrvRegiAccountChangeServiceImplTest extends TestBaseForMock
{

    /**
     * ログユーティリティ<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(
                WEB3AdminSrvRegiAccountChangeServiceImplTest.class);

    /**
     * サービス利用管理者顧客変更サービスImpl
     */
    private WEB3AdminSrvRegiAccountChangeServiceImpl l_impl;

    /**
     * @@param arg0
     */
    public WEB3AdminSrvRegiAccountChangeServiceImplTest(String arg0)
    {
        super(arg0);
    }

    /**
     *
     */
    protected void setUp() throws Exception
    {
        super.setUp();
        Services.overrideService(OpLoginSecurityService.class,
            new OpLoginSecurityServiceImplForMock());
    }

    /**
     *
     */
    protected void tearDown() throws Exception
    {
        super.tearDown();
    }

//    /**
//     *
//     */
//    public void testValidateAppliRegist_case0001()
//    {
//        final String STR_METHOD_NAME = "testValidateAppliRegist_case0001()";
//        log.entering(TEST_START + STR_METHOD_NAME);
//
//        this.l_impl = new WEB3AdminSrvRegiAccountChangeServiceImpl();
//
//        try
//        {
//            MainAccountParams l_mainAccountParams =
//                TestDBUtility.getMainAccountRow();
//            TestDBUtility.deleteAll(MainAccountParams.TYPE);
//            TestDBUtility.insertWithDel(l_mainAccountParams);
//
//            BranchParams l_branchParams = TestDBUtility.getBranchRow();
//            TestDBUtility.deleteAll(BranchParams.TYPE);
//            l_branchParams.setBranchCode(
//                l_mainAccountParams.getBranchCode());
//            l_branchParams.setInstitutionCode(
//                l_mainAccountParams.getInstitutionCode());
//            TestDBUtility.insertWithDel(l_branchParams);
//
//            InstitutionParams l_institutionParams =
//                TestDBUtility.getInstitutionRow();
//            TestDBUtility.deleteAll(InstitutionParams.TYPE);
//            l_institutionParams.setInstitutionCode(
//                l_mainAccountParams.getInstitutionCode());
//            TestDBUtility.insertWithDel(l_institutionParams);
//
//            SubAccountParams l_subAccountParams =
//                TestDBUtility.getSubAccountRow();
//            TestDBUtility.deleteAll(SubAccountParams.TYPE);
//            l_subAccountParams.setAccountId(
//                l_mainAccountParams.getAccountId());
//            l_subAccountParams.setBranchId(
//                l_branchParams.getBranchId());
//            l_subAccountParams.setInstitutionId(
//                l_mainAccountParams.getInstitutionId());
//            l_subAccountParams.setInstitutionCode(
//                l_mainAccountParams.getInstitutionCode());
//            l_subAccountParams.setSubAccountType(
//                SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
//            TestDBUtility.insertWithDel(l_subAccountParams);
//
//            AdministratorParams l_administratorParams =
//                TestDBUtility.getAdministratorRow();
//            TestDBUtility.deleteAll(AdministratorParams.TYPE);
//            l_administratorParams.setBranchId(
//                l_branchParams.getBranchId());
//            l_administratorParams.setInstitutionId(
//                l_mainAccountParams.getInstitutionId());
//            l_administratorParams.setInstitutionCode(
//                l_mainAccountParams.getInstitutionCode());
//            TestDBUtility.insertWithDel(l_administratorParams);
//
//            SrvRegiMasterParams l_srvRegiMasterParams =
//                TestDBUtility.getSrvRegiMasterRow();
//            TestDBUtility.deleteAll(SrvRegiMasterParams.TYPE);
//            l_srvRegiMasterParams.setSrvDiv("12");
//            l_srvRegiMasterParams.setInstitutionCode(
//                l_mainAccountParams.getInstitutionCode());
//            l_srvRegiMasterParams.setSrvStatus("2");
//            l_srvRegiMasterParams.setSpecialProcessDiv("1");
//            TestDBUtility.insertWithDel(l_srvRegiMasterParams);
//
//            SrvRegiSetupParams l_srvRegiSetupParams =
//                TestDBUtility.getSrvRegiSetupRow();
//            TestDBUtility.deleteAll(SrvRegiSetupParams.TYPE);
//            l_srvRegiSetupParams.setSrvDiv("12");
//            l_srvRegiSetupParams.setInstitutionCode(
//                l_mainAccountParams.getInstitutionCode());
//            l_srvRegiSetupParams.setLotDiv("2");
//            TestDBUtility.insertWithDel(l_srvRegiSetupParams);
//
//            SrvRegiApplicationParams l_srvRegiApplicationParams =
//                TestDBUtility.getSrvRegiApplicationParams();
//            TestDBUtility.deleteAll(SrvRegiApplicationParams.TYPE);
//            l_srvRegiApplicationParams.setSrvDiv("12");
//            l_srvRegiApplicationParams.setInstitutionCode(
//                l_mainAccountParams.getInstitutionCode());
//            l_srvRegiApplicationParams.setBranchCode(
//                l_mainAccountParams.getBranchCode());
//            l_srvRegiApplicationParams.setAccountCode(
//                l_mainAccountParams.getAccountCode());
//            l_srvRegiApplicationParams.setCancelDiv("0");
//            l_srvRegiApplicationParams.setEffectiveDiv("0");
//            l_srvRegiApplicationParams.setRegistId(1);
//            TestDBUtility.insertWithDel(l_srvRegiApplicationParams);
//
//            OtherOrgInfoAdminParams l_otherOrgInfoAdminParams =
//                new OtherOrgInfoAdminParams();
//            TestDBUtility.deleteAll(OtherOrgInfoAdminParams.TYPE);
//            l_otherOrgInfoAdminParams.setSequenceNumber(123);
//            l_otherOrgInfoAdminParams.setSrvDiv("12");
//            l_otherOrgInfoAdminParams.setId("1");
//            l_otherOrgInfoAdminParams.setPassword("1");
//            l_otherOrgInfoAdminParams.setStatus("9");
//            l_otherOrgInfoAdminParams.setInstitutionCode(
//                l_mainAccountParams.getInstitutionCode());
//            l_otherOrgInfoAdminParams.setBranchCode(
//                l_mainAccountParams.getBranchCode());
//            l_otherOrgInfoAdminParams.setAccountCode(
//                l_mainAccountParams.getAccountCode());
//            l_otherOrgInfoAdminParams.setAppliStartDate(new Date());
//            l_otherOrgInfoAdminParams.setAppliEndDate(new Date());
//            l_otherOrgInfoAdminParams.setLastUpdater("1");
//            l_otherOrgInfoAdminParams.setCreatedTimestamp(new Date());
//            l_otherOrgInfoAdminParams.setLastUpdatedTimestamp(new Date());
//            
//            TestDBUtility.insertWithDel(l_otherOrgInfoAdminParams);
//            l_otherOrgInfoAdminParams.setSequenceNumber(124);
//            TestDBUtility.insertWithDel(l_otherOrgInfoAdminParams);
//            
//            TestDBUtility.deleteAll(SrvRegiHistoryParams.TYPE);
//
//            WEB3SrvRegiChangeAppliSpec l_srvRegiChangeAppliSpec =
//                new WEB3SrvRegiChangeAppliSpec();
//            l_srvRegiChangeAppliSpec.setInstitutionCode(
//                l_mainAccountParams.getInstitutionCode());
//            l_srvRegiChangeAppliSpec.setBranchCode(
//                l_mainAccountParams.getBranchCode());
//            l_srvRegiChangeAppliSpec.setAccountCode(
//                l_mainAccountParams.getAccountCode());
//            l_srvRegiChangeAppliSpec.setSrvDiv("12");
//            l_srvRegiChangeAppliSpec.setRegistId("1");
//
//            WEB3Administrator l_administrator =
//                new WEB3Administrator(l_administratorParams);
//
//            Method l_method =
//                WEB3AdminSrvRegiAccountChangeServiceImpl.class.getDeclaredMethod(
//                    "validateAppliChange",
//                    new Class[] {
//                        WEB3SrvRegiChangeAppliSpec.class,
//                        WEB3Administrator.class});
//            l_method.setAccessible(true);
//            l_method.invoke(
//                this.l_impl,
//                new Object[] {
//                        l_srvRegiChangeAppliSpec,
//                    l_administrator});
//
//            fail();
//        }
//        catch (NoSuchMethodException l_exNSME)
//        {
//            log.debug(STR_METHOD_NAME, l_exNSME);
//            fail();
//        }
//        catch (IllegalAccessException l_exIAE)
//        {
//            log.debug(STR_METHOD_NAME, l_exIAE);
//            fail();
//        }
//        catch (InvocationTargetException l_exITE)
//        {
//            log.debug(STR_METHOD_NAME, l_exITE);
//            assertEquals(
//                WEB3BusinessLayerException.class,
//                l_exITE.getTargetException().getClass());
//            assertEquals(
//                WEB3ErrorCatalog.BUSINESS_ERROR_03019,
//                ((WEB3BusinessLayerException)l_exITE.getTargetException()).getErrorInfo());
//        }
//        catch (WEB3BaseException l_exBE)
//        {
//            log.debug(STR_METHOD_NAME, l_exBE);
//            fail();
//        }
//        catch (Exception l_exE)
//        {
//            log.debug(STR_METHOD_NAME, l_exE);
//            fail();
//        }
//        finally
//        {
//            log.entering(TEST_END + STR_METHOD_NAME);
//        }
//    }

    /**
     *
     */
    public void testValidateAppliRegist_case0002()
    {
        final String STR_METHOD_NAME = "testValidateAppliRegist_case0002()";
        log.entering(TEST_START + STR_METHOD_NAME);

        this.l_impl = new WEB3AdminSrvRegiAccountChangeServiceImpl();

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
            
            TestDBUtility.deleteAll(AdministratorTypeParams.TYPE);
            AdministratorTypeParams l_AdministratorTypeParams =
                TestDBUtility.getAdministratorTypeRow();
            TestDBUtility.insertWithDel(l_AdministratorTypeParams);

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
            l_srvRegiSetupParams.setLotDiv("2");
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
            l_srvRegiApplicationParams.setRegistId(1);
            TestDBUtility.insertWithDel(l_srvRegiApplicationParams);

            OtherOrgInfoAdminParams l_otherOrgInfoAdminParams =
                new OtherOrgInfoAdminParams();
            TestDBUtility.deleteAll(OtherOrgInfoAdminParams.TYPE);
            l_otherOrgInfoAdminParams.setSequenceNumber(123);
            l_otherOrgInfoAdminParams.setSrvDiv("12");
            l_otherOrgInfoAdminParams.setId("1");
            l_otherOrgInfoAdminParams.setPassword("1");
            l_otherOrgInfoAdminParams.setStatus("0");
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

            TestDBUtility.deleteAll(SrvRegiHistoryParams.TYPE);

            WEB3SrvRegiChangeAppliSpec l_srvRegiChangeAppliSpec =
                new WEB3SrvRegiChangeAppliSpec();
            l_srvRegiChangeAppliSpec.setInstitutionCode(
                l_mainAccountParams.getInstitutionCode());
            l_srvRegiChangeAppliSpec.setBranchCode(
                l_mainAccountParams.getBranchCode());
            l_srvRegiChangeAppliSpec.setAccountCode(
                l_mainAccountParams.getAccountCode());
            l_srvRegiChangeAppliSpec.setSrvDiv("12");
            l_srvRegiChangeAppliSpec.setRegistId("1");

            WEB3Administrator l_administrator =
                new WEB3Administrator(l_administratorParams);

            
            WEB3AdministratorForMock.mockValidateBranchPermission(
                    l_administrator,l_branchParams.getBranchCode(),true);
            
            Method l_method =
                WEB3AdminSrvRegiAccountChangeServiceImpl.class.getDeclaredMethod(
                    "validateAppliChange",
                    new Class[] {
                        WEB3SrvRegiChangeAppliSpec.class,
                        WEB3Administrator.class});
            l_method.setAccessible(true);
            l_method.invoke(
                this.l_impl,
                new Object[] {
                        l_srvRegiChangeAppliSpec,
                    l_administrator});
            
            
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
    public void testSubmitChange_case0001()
    {
        final String STR_METHOD_NAME = "testSubmitChange_case0001()";
        log.entering(TEST_START + STR_METHOD_NAME);

        this.l_impl = new WEB3AdminSrvRegiAccountChangeServiceImpl();

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
            l_administratorParams.setTradingPassword("123");
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
            l_srvRegiSetupParams.setLotDiv("2");
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
            l_srvRegiApplicationParams.setRegistId(1);
            TestDBUtility.insertWithDel(l_srvRegiApplicationParams);

            OtherOrgInfoAdminParams l_otherOrgInfoAdminParams =
                new OtherOrgInfoAdminParams();
            TestDBUtility.deleteAll(OtherOrgInfoAdminParams.TYPE);
            
            l_otherOrgInfoAdminParams.setSequenceNumber(123);
            //l_otherOrgInfoAdminParams.setSrvDiv("00");
            l_otherOrgInfoAdminParams.setSrvDiv("12");
            l_otherOrgInfoAdminParams.setId("1");
            l_otherOrgInfoAdminParams.setPassword("1");
            l_otherOrgInfoAdminParams.setStatus("0");
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
            //add a data into database
            l_otherOrgInfoAdminParams.setSequenceNumber(124);
            TestDBUtility.insertWithDel(l_otherOrgInfoAdminParams);
            
            
            TestDBUtility.deleteAll(SrvRegiHistoryParams.TYPE);

            WEB3SrvRegiChangeAppliSpec l_srvRegiChangeAppliSpec =
                new WEB3SrvRegiChangeAppliSpec();
            l_srvRegiChangeAppliSpec.setInstitutionCode(
                l_mainAccountParams.getInstitutionCode());
            l_srvRegiChangeAppliSpec.setBranchCode(
                l_mainAccountParams.getBranchCode());
            l_srvRegiChangeAppliSpec.setAccountCode(
                l_mainAccountParams.getAccountCode());
            l_srvRegiChangeAppliSpec.setSrvDiv("12");
            l_srvRegiChangeAppliSpec.setRegistId("1");

            WEB3AdminSrvRegiCustomerChangeCompleteRequest l_request =
                new WEB3AdminSrvRegiCustomerChangeCompleteRequest();

            l_request.serviceDiv = "12";
            l_request.password = "123";
            WEB3AdminSrvRegiCustomerChangeGroup l_adminSrvRegiCustomerChangeGroup =
                new WEB3AdminSrvRegiCustomerChangeGroup();
            l_adminSrvRegiCustomerChangeGroup.registDiv = "1";
            l_adminSrvRegiCustomerChangeGroup.branchCode =
                l_mainAccountParams.getBranchCode();
            l_adminSrvRegiCustomerChangeGroup.accountCode =
                l_mainAccountParams.getAccountCode().substring(0, 6);
            l_adminSrvRegiCustomerChangeGroup.trialStartDate = new Date();
            l_adminSrvRegiCustomerChangeGroup.trialEndDate = new Date();
            l_adminSrvRegiCustomerChangeGroup.applyDate = new Date();
            l_adminSrvRegiCustomerChangeGroup.chargeAmt = "1";
            l_adminSrvRegiCustomerChangeGroup.lotteryDiv = "1";
            l_adminSrvRegiCustomerChangeGroup.applyLotteryDiv = "2";
            l_adminSrvRegiCustomerChangeGroup.applyRegId = "1";
            l_request.chgCustomerList =
                new WEB3AdminSrvRegiCustomerChangeGroup[]{
                    l_adminSrvRegiCustomerChangeGroup};

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

            this.l_impl.submitChange(l_request);

            fail();
        }
        catch (WEB3BaseException l_exBE)
        {
            log.debug(STR_METHOD_NAME, l_exBE);
            assertEquals(
                WEB3ErrorCatalog.SYSTEM_ERROR_80006,
                l_exBE.getErrorInfo());
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
    public void testSubmitChange_case0002()
    {
        final String STR_METHOD_NAME = "testSubmitChange_case0002()";
        log.entering(TEST_START + STR_METHOD_NAME);

        this.l_impl = new WEB3AdminSrvRegiAccountChangeServiceImpl();

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
            l_administratorParams.setTradingPassword("123");
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
            l_srvRegiSetupParams.setLotDiv("2");
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
            l_srvRegiApplicationParams.setRegistId(1);
            TestDBUtility.insertWithDel(l_srvRegiApplicationParams);

            OtherOrgInfoAdminParams l_otherOrgInfoAdminParams =
                new OtherOrgInfoAdminParams();
            TestDBUtility.deleteAll(OtherOrgInfoAdminParams.TYPE);
            l_otherOrgInfoAdminParams.setSequenceNumber(123);
            l_otherOrgInfoAdminParams.setSrvDiv("12");
            l_otherOrgInfoAdminParams.setId("1");
            l_otherOrgInfoAdminParams.setPassword("1");
            l_otherOrgInfoAdminParams.setStatus("0");
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

            TestDBUtility.deleteAll(SrvRegiHistoryParams.TYPE);

            WEB3SrvRegiChangeAppliSpec l_srvRegiChangeAppliSpec =
                new WEB3SrvRegiChangeAppliSpec();
            l_srvRegiChangeAppliSpec.setInstitutionCode(
                l_mainAccountParams.getInstitutionCode());
            l_srvRegiChangeAppliSpec.setBranchCode(
                l_mainAccountParams.getBranchCode());
            l_srvRegiChangeAppliSpec.setAccountCode(
                l_mainAccountParams.getAccountCode());
            l_srvRegiChangeAppliSpec.setSrvDiv("12");
            l_srvRegiChangeAppliSpec.setRegistId("1");

            WEB3AdminSrvRegiCustomerChangeCompleteRequest l_request =
                new WEB3AdminSrvRegiCustomerChangeCompleteRequest();

            l_request.serviceDiv = "12";
            l_request.password = "123";
            WEB3AdminSrvRegiCustomerChangeGroup l_adminSrvRegiCustomerChangeGroup =
                new WEB3AdminSrvRegiCustomerChangeGroup();
            l_adminSrvRegiCustomerChangeGroup.registDiv = "1";
            l_adminSrvRegiCustomerChangeGroup.branchCode =
                l_mainAccountParams.getBranchCode();
            l_adminSrvRegiCustomerChangeGroup.accountCode =
                l_mainAccountParams.getAccountCode().substring(0, 6);
            l_adminSrvRegiCustomerChangeGroup.trialStartDate = new Date();
            l_adminSrvRegiCustomerChangeGroup.trialEndDate = new Date();
            l_adminSrvRegiCustomerChangeGroup.applyDate = new Date();
            l_adminSrvRegiCustomerChangeGroup.chargeAmt = "1";
            l_adminSrvRegiCustomerChangeGroup.lotteryDiv = "1";
            l_adminSrvRegiCustomerChangeGroup.applyLotteryDiv = "2";
            l_adminSrvRegiCustomerChangeGroup.applyRegId = "1";
            l_request.chgCustomerList =
                new WEB3AdminSrvRegiCustomerChangeGroup[]{
                    l_adminSrvRegiCustomerChangeGroup};

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

            this.l_impl.submitChange(l_request);

            assertTrue(true);
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
