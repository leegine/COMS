head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.02.06.10;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3AdminFPTBatoProductCodeManagementTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.docadmin;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.plugin.security.oplogin.impl.LoginInfoImpl;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.BranchParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.BranchRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.InstitutionParams;

import test.util.TestDBUtility;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.docadmin.message.WEB3FPTBatoProductCodeAdminInfoUnit;
import webbroker3.docadmin.message.WEB3FPTDocumentUpdateInfoUnit;
import webbroker3.gentrade.WEB3Administrator;
import webbroker3.gentrade.WEB3AdministratorForMock;
import webbroker3.gentrade.data.AdministratorParams;
import webbroker3.gentrade.data.AdministratorRow;
import webbroker3.gentrade.data.BatoProductManagementParams;
import webbroker3.gentrade.data.BatoProductManagementRow;
import webbroker3.gentrade.data.DocDeliveryManagementParams;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3LogUtility;

public class WEB3AdminFPTBatoProductCodeManagementTest extends TestBaseForMock
{
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminFPTBatoProductCodeManagementTest.class);
    public WEB3AdminFPTBatoProductCodeManagementTest(String arg0)
    {
        super(arg0);
    }

    protected void setUp() throws Exception
    {
        super.setUp();
    }

    protected void tearDown() throws Exception
    {
        super.tearDown();
    }

    public void testIsBatoProductCode()
    {
        final String STR_METHOD_NAME = "testIsBatoProductCode()";
        log.entering(STR_METHOD_NAME);

        try
        {

            WEB3AdminFPTBatoProductCodeManagement l_batoProductCodeManagement =
                new WEB3AdminFPTBatoProductCodeManagement("0D", "381", "010", "1111111");
            TestDBUtility.deleteAll(BatoProductManagementParams.TYPE);

            assertFalse(l_batoProductCodeManagement.isBatoProductCode());
            BatoProductManagementParams l_batoProductManagementParams = new BatoProductManagementParams();
            l_batoProductManagementParams.setInstitutionCode("0D");
            l_batoProductManagementParams.setBranchCode("381");
            l_batoProductManagementParams.setBatoProductCode("1111111");
            l_batoProductManagementParams.setDocumentDiv("010");
            l_batoProductManagementParams.setCreatedTimestamp(Calendar.getInstance().getTime());
            l_batoProductManagementParams.setLastUpdatedTimestamp(Calendar.getInstance().getTime());
            l_batoProductManagementParams.setValidFlag("0");
            TestDBUtility.insertWithDel(l_batoProductManagementParams);

            assertTrue(l_batoProductCodeManagement.isBatoProductCode());
        }
        catch (Exception l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            fail();
        }
    }

    public void testGetBatoProductManagement()
    {
        final String STR_METHOD_NAME = "testGetBatoProductManagement()";
        log.entering(STR_METHOD_NAME);

        try
        {

            WEB3AdminFPTBatoProductCodeManagement l_batoProductCodeManagement =
                new WEB3AdminFPTBatoProductCodeManagement("0D", "381", "010", "1111111");
            TestDBUtility.deleteAll(BatoProductManagementParams.TYPE);

            assertEquals(l_batoProductCodeManagement.getBatoProductManagement().size(), 0);
            BatoProductManagementParams l_batoProductManagementParams = new BatoProductManagementParams();
            l_batoProductManagementParams.setInstitutionCode("0D");
            l_batoProductManagementParams.setBranchCode("381");
            l_batoProductManagementParams.setBatoProductCode("1111111");
            l_batoProductManagementParams.setDocumentDiv("010");
            l_batoProductManagementParams.setCreatedTimestamp(Calendar.getInstance().getTime());
            l_batoProductManagementParams.setLastUpdatedTimestamp(Calendar.getInstance().getTime());
            l_batoProductManagementParams.setValidFlag("0");
            TestDBUtility.insertWithDel(l_batoProductManagementParams);

            assertEquals(l_batoProductCodeManagement.getBatoProductManagement().size(), 1);
        }
        catch (Exception l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            fail();
        }
    }

    public void testGetBatoProductCodeAdminInfoUnit()
    {
        final String STR_METHOD_NAME = "testGetBatoProductCodeAdminInfoUnit()";
        log.entering(STR_METHOD_NAME);

        try
        {

            WEB3AdminFPTBatoProductCodeManagement l_batoProductCodeManagement =
                new WEB3AdminFPTBatoProductCodeManagement("0D", "381", "010", "111");
            TestDBUtility.deleteAll(BatoProductManagementParams.TYPE);

            BatoProductManagementParams l_batoProductManagementParams = new BatoProductManagementParams();
            l_batoProductManagementParams.setInstitutionCode("0D");
            l_batoProductManagementParams.setBranchCode("381");
            l_batoProductManagementParams.setBatoProductCode("1111111");
            l_batoProductManagementParams.setDocumentDiv("010");
            l_batoProductManagementParams.setCreatedTimestamp(Calendar.getInstance().getTime());
            l_batoProductManagementParams.setLastUpdatedTimestamp(Calendar.getInstance().getTime());
            l_batoProductManagementParams.setValidFlag("0");
            l_batoProductManagementParams.setRemarks("test1");
            TestDBUtility.insertWithDel(l_batoProductManagementParams);

            WEB3FPTBatoProductCodeAdminInfoUnit[] l_batoProductCodeAdminInfoUnits =
                l_batoProductCodeManagement.getBatoProductCodeAdminInfoUnit();
            assertEquals("test1", l_batoProductCodeAdminInfoUnits[0].remarks);
            assertEquals("1111", l_batoProductCodeAdminInfoUnits[0].documentCategoryNumber);
            assertEquals("0", l_batoProductCodeAdminInfoUnits[0].validFlag);

            l_batoProductManagementParams.setInstitutionCode("0D");
            l_batoProductManagementParams.setBranchCode("381");
            l_batoProductManagementParams.setBatoProductCode("1112111");
            l_batoProductManagementParams.setDocumentDiv("010");
            l_batoProductManagementParams.setCreatedTimestamp(Calendar.getInstance().getTime());
            l_batoProductManagementParams.setLastUpdatedTimestamp(Calendar.getInstance().getTime());
            l_batoProductManagementParams.setValidFlag("1");
            l_batoProductManagementParams.setRemarks("test2");
            TestDBUtility.insertWithDel(l_batoProductManagementParams);

            l_batoProductCodeAdminInfoUnits =
                l_batoProductCodeManagement.getBatoProductCodeAdminInfoUnit();
            assertEquals("test1", l_batoProductCodeAdminInfoUnits[0].remarks);
            assertEquals("1111", l_batoProductCodeAdminInfoUnits[0].documentCategoryNumber);
            assertEquals("0", l_batoProductCodeAdminInfoUnits[0].validFlag);

            assertEquals("test2", l_batoProductCodeAdminInfoUnits[1].remarks);
            assertEquals("2111", l_batoProductCodeAdminInfoUnits[1].documentCategoryNumber);
            assertEquals("1", l_batoProductCodeAdminInfoUnits[1].validFlag);

            l_batoProductCodeManagement =
                new WEB3AdminFPTBatoProductCodeManagement();
            TestDBUtility.deleteAll(BatoProductManagementParams.TYPE);
            l_batoProductCodeManagement.getBatoProductCodeAdminInfoUnit();

            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02999, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            fail();
        }
    }

    public void testsetInstBranchInfo_0001()
    {
        final String STR_METHOD_NAME = "testsetInstBranchInfo_0001()";
        log.entering(STR_METHOD_NAME);

        try
        {
            TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getAccountId",
                new Class[] {},
                new Long("123")
                );
            
            LoginInfoImpl l_loginInfoImpl = new LoginInfoImpl();
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getLoginInfo",
                new Class[] {},
                l_loginInfoImpl
                );
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.LoginInfoImpl",
                "getLoginId",
                new Class[] {},
                new Long("111")
                );

            AdministratorParams l_administratorParams = TestDBUtility.getAdministratorRow();
            l_administratorParams.setInstitutionCode("0D");
            l_administratorParams.setBranchCode("381");
            TestDBUtility.deleteAll(AdministratorRow.TYPE);
            TestDBUtility.insertWithDel(l_administratorParams);
            TestDBUtility.deleteAll(BranchRow.TYPE);
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            TestDBUtility.deleteAll(InstitutionParams.TYPE);
            TestDBUtility.insertWithDel(l_institutionParams);
            WEB3Administrator l_expectAdministrator = new WEB3Administrator(l_administratorParams);

            WEB3AdministratorForMock.mockGetInstanceFromLoginInfo(l_expectAdministrator);

            // getInstanceFromログイン情報()
            //管理者インスタンスを取得する。
            WEB3Administrator l_administrator =
                WEB3Administrator.getInstanceFromLoginInfo();
            WEB3FPTDocumentUpdateInfoUnit[] documentUpdateList = new WEB3FPTDocumentUpdateInfoUnit[0];
            WEB3AdminFPTBatoProductCodeManagement management =
                new WEB3AdminFPTBatoProductCodeManagement(
                    l_administrator,
                    documentUpdateList,
                    "1");
            Field field = management.getClass().getDeclaredField("branchCodeList");
            field.setAccessible(true);
            String[] l_branchs = (String[])field.get(management);
            assertEquals(0, l_branchs.length);
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }

    public void testsetInstBranchInfo_0002()
    {
        final String STR_METHOD_NAME = "testsetInstBranchInfo_0002()";
        log.entering(STR_METHOD_NAME);

        try
        {
            TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getAccountId",
                new Class[] {},
                new Long("123")
                );
            
            LoginInfoImpl l_loginInfoImpl = new LoginInfoImpl();
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getLoginInfo",
                new Class[] {},
                l_loginInfoImpl
                );
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.LoginInfoImpl",
                "getLoginId",
                new Class[] {},
                new Long("111")
                );

            AdministratorParams l_administratorParams = TestDBUtility.getAdministratorRow();
            l_administratorParams.setInstitutionCode("0D");
            l_administratorParams.setBranchCode("381");
            TestDBUtility.deleteAll(AdministratorRow.TYPE);
            TestDBUtility.insertWithDel(l_administratorParams);
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            TestDBUtility.deleteAll(InstitutionParams.TYPE);
            TestDBUtility.insertWithDel(l_institutionParams);
            WEB3Administrator l_expectAdministrator = new WEB3Administrator(l_administratorParams);

            TestDBUtility.deleteAll(BranchRow.TYPE);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            TestDBUtility.insertWithDel(l_branchParams);
            
            WEB3AdministratorForMock.mockGetInstanceFromLoginInfo(l_expectAdministrator);

            // getInstanceFromログイン情報()
            //管理者インスタンスを取得する。
            WEB3Administrator l_administrator =
                WEB3Administrator.getInstanceFromLoginInfo();
            WEB3FPTDocumentUpdateInfoUnit[] documentUpdateList = new WEB3FPTDocumentUpdateInfoUnit[0];
            WEB3AdminFPTBatoProductCodeManagement management =
                new WEB3AdminFPTBatoProductCodeManagement(
                    l_administrator,
                    documentUpdateList,
                    "1");
            Field field = management.getClass().getDeclaredField("branchCodeList");
            field.setAccessible(true);
            String[] l_branchs = (String[])field.get(management);
            assertEquals(1, l_branchs.length);
            Field field2 = management.getClass().getDeclaredField("institutionCode");
            field2.setAccessible(true);
            String l_institutionCode = (String)field2.get(management);
            assertEquals("0D", l_institutionCode);
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }

    public void testsetInstBranchInfo_0003()
    {
        final String STR_METHOD_NAME = "testsetInstBranchInfo_0003()";
        log.entering(STR_METHOD_NAME);

        try
        {
            TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);

            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            TestDBUtility.deleteAll(InstitutionParams.TYPE);
            TestDBUtility.insertWithDel(l_institutionParams);
            TestDBUtility.deleteAll(AdministratorRow.TYPE);
            AdministratorParams l_administratorParams = TestDBUtility.getAdministratorRow();
            l_administratorParams.setInstitutionCode("0D");
            l_administratorParams.setBranchCode("382");
            TestDBUtility.insertWithDel(l_administratorParams);            
            WEB3Administrator l_expectAdministrator = new WEB3Administrator(l_administratorParams);

            TestDBUtility.deleteAll(BranchRow.TYPE);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            TestDBUtility.insertWithDel(l_branchParams);
            BranchParams l_branchParams1 = TestDBUtility.getBranchRow();
            l_branchParams1.setBranchCode("382");
            l_branchParams1.setBranchId(33382);
            TestDBUtility.insertWithDel(l_branchParams1);
            BranchParams l_branchParams2 = TestDBUtility.getBranchRow();
            l_branchParams2.setBranchCode("383");
            l_branchParams2.setBranchId(33383);
            TestDBUtility.insertWithDel(l_branchParams2);
            
            WEB3AdministratorForMock.mockGetInstanceFromLoginInfo(l_expectAdministrator);

            // getInstanceFromログイン情報()
            //管理者インスタンスを取得する。
            WEB3Administrator l_administrator =
                WEB3Administrator.getInstanceFromLoginInfo();
            WEB3FPTDocumentUpdateInfoUnit[] documentUpdateList = new WEB3FPTDocumentUpdateInfoUnit[0];
            WEB3AdminFPTBatoProductCodeManagement management =
                new WEB3AdminFPTBatoProductCodeManagement(
                    l_administrator,
                    documentUpdateList,
                    "1");
            Field field = management.getClass().getDeclaredField("branchCodeList");
            field.setAccessible(true);
            String[] l_branchs = (String[])field.get(management);
            assertEquals(3, l_branchs.length);
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }

        log.exiting(STR_METHOD_NAME);
    }

//    public void testdeleteBatoProductCodeAdmin_0001()
//    {
//        final String STR_METHOD_NAME = "testdeleteBatoProductCodeAdmin_0001()";
//        log.entering(STR_METHOD_NAME);
//
//        try
//        {
//            TestDBUtility.deleteAll(BatoProductManagementParams.TYPE);
//            BatoProductManagementParams l_batoProductManagementParams = this.getBatoProductManagementRow();
//            l_batoProductManagementParams.setDocumentDiv("11");
//            TestDBUtility.insertWithDel(l_batoProductManagementParams);
//
//            BatoProductManagementParams l_batoProductManagementParams1 = this.getBatoProductManagementRow();
//            l_batoProductManagementParams1.setDocumentDiv("12");
//            TestDBUtility.insertWithDel(l_batoProductManagementParams1);
//
//            BatoProductManagementParams l_batoProductManagementParams2 = this.getBatoProductManagementRow();
//            l_batoProductManagementParams2.setDocumentDiv("13");
//            TestDBUtility.insertWithDel(l_batoProductManagementParams2);
//
//            AdministratorParams l_administratorParams = TestDBUtility.getAdministratorRow();
//            l_administratorParams.setInstitutionCode("0D");
//            l_administratorParams.setBranchCode("381");
//            TestDBUtility.deleteAll(AdministratorRow.TYPE);
//            TestDBUtility.insertWithDel(l_administratorParams);
//            TestDBUtility.deleteAll(BranchRow.TYPE);
//            WEB3Administrator l_expectAdministrator = new WEB3Administrator(l_administratorParams);
//
//            WEB3AdministratorForMock.mockGetInstanceFromLoginInfo(l_expectAdministrator);
//
//            // getInstanceFromログイン情報()
//            //管理者インスタンスを取得する。
//            WEB3Administrator l_administrator =
//                WEB3Administrator.getInstanceFromLoginInfo();
//            WEB3FPTDocumentUpdateInfoUnit[] documentUpdateList = new WEB3FPTDocumentUpdateInfoUnit[3];
//            WEB3FPTDocumentUpdateInfoUnit unit1 = new WEB3FPTDocumentUpdateInfoUnit();
//            unit1.documentDiv = "21";
//            unit1.batoProductCode = "123";
//            unit1.documentNumber = "1";
//            documentUpdateList[0] = unit1;
//            WEB3FPTDocumentUpdateInfoUnit unit2 = new WEB3FPTDocumentUpdateInfoUnit();
//            unit2.documentDiv = "22";
//            unit2.batoProductCode = "123";
//            unit2.documentNumber = "1";
//            documentUpdateList[1] = unit2;
//            WEB3FPTDocumentUpdateInfoUnit unit3 = new WEB3FPTDocumentUpdateInfoUnit();
//            unit3.documentDiv = "23";
//            unit3.batoProductCode = "123";
//            unit3.documentNumber = "1";
//            documentUpdateList[2] = unit3;
//            WEB3AdminFPTBatoProductCodeManagement management =
//                new WEB3AdminFPTBatoProductCodeManagement(
//                    l_administrator,
//                    documentUpdateList,
//                    "1");
//            management.deleteBatoProductCodeAdmin();
//            
//            fail();
//        }
//        catch (WEB3BaseException l_ex)
//        {
//            log.error(STR_METHOD_NAME, l_ex);
//            log.exiting(STR_METHOD_NAME);
//            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80003, l_ex.getErrorInfo());
//            
//            try
//            {
//                QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
//                List l_lis = l_queryProcessor.doFindAllQuery(BatoProductManagementParams.TYPE);
//                assertEquals(3, l_lis.size());
//            }
//            catch (Exception ex)
//            {
//                log.error(STR_METHOD_NAME, l_ex);
//                log.exiting(STR_METHOD_NAME);
//                
//                fail();
//            }
//        }
//        catch (Exception l_ex)
//        {
//            log.error(STR_METHOD_NAME, l_ex);
//            log.exiting(STR_METHOD_NAME);
//            fail();
//        }
//
//        
//        log.exiting(STR_METHOD_NAME);
//    }
//
//    public void testdeleteBatoProductCodeAdmin_0002()
//    {
//        final String STR_METHOD_NAME = "testdeleteBatoProductCodeAdmin_0002()";
//        log.entering(STR_METHOD_NAME);
//
//        try
//        {
//            TestDBUtility.deleteAll(BatoProductManagementParams.TYPE);
//            BatoProductManagementParams l_batoProductManagementParams = this.getBatoProductManagementRow();
//            l_batoProductManagementParams.setDocumentDiv("11");
//            TestDBUtility.insertWithDel(l_batoProductManagementParams);
//
//            BatoProductManagementParams l_batoProductManagementParams1 = this.getBatoProductManagementRow();
//            l_batoProductManagementParams1.setDocumentDiv("12");
//            TestDBUtility.insertWithDel(l_batoProductManagementParams1);
//
//            BatoProductManagementParams l_batoProductManagementParams2 = this.getBatoProductManagementRow();
//            l_batoProductManagementParams2.setDocumentDiv("13");
//            TestDBUtility.insertWithDel(l_batoProductManagementParams2);
//
//            AdministratorParams l_administratorParams = TestDBUtility.getAdministratorRow();
//            l_administratorParams.setInstitutionCode("0D");
//            l_administratorParams.setBranchCode("381");
//            TestDBUtility.deleteAll(AdministratorRow.TYPE);
//            TestDBUtility.insertWithDel(l_administratorParams);
//            TestDBUtility.deleteAll(BranchRow.TYPE);
//            WEB3Administrator l_expectAdministrator = new WEB3Administrator(l_administratorParams);
//
//            WEB3AdministratorForMock.mockGetInstanceFromLoginInfo(l_expectAdministrator);
//
//            // getInstanceFromログイン情報()
//            //管理者インスタンスを取得する。
//            WEB3Administrator l_administrator =
//                WEB3Administrator.getInstanceFromLoginInfo();
//            WEB3FPTDocumentUpdateInfoUnit[] documentUpdateList = new WEB3FPTDocumentUpdateInfoUnit[3];
//            WEB3FPTDocumentUpdateInfoUnit unit1 = new WEB3FPTDocumentUpdateInfoUnit();
//            unit1.documentDiv = "11";
//            unit1.batoProductCode = "000";
//            unit1.documentNumber = "000";
//            documentUpdateList[0] = unit1;
//            WEB3FPTDocumentUpdateInfoUnit unit2 = new WEB3FPTDocumentUpdateInfoUnit();
//            unit2.documentDiv = "22";
//            unit2.batoProductCode = "000";
//            unit2.documentNumber = "000";
//            documentUpdateList[1] = unit2;
//            WEB3FPTDocumentUpdateInfoUnit unit3 = new WEB3FPTDocumentUpdateInfoUnit();
//            unit3.documentDiv = "23";
//            unit3.batoProductCode = "000";
//            unit3.documentNumber = "000";
//            documentUpdateList[2] = unit3;
//            WEB3AdminFPTBatoProductCodeManagement management =
//                new WEB3AdminFPTBatoProductCodeManagement(
//                    l_administrator,
//                    documentUpdateList,
//                    "1");
//            management.deleteBatoProductCodeAdmin();
//            
//            fail();
//        }
//        catch (WEB3BaseException l_ex)
//        {
//            log.error(STR_METHOD_NAME, l_ex);
//            log.exiting(STR_METHOD_NAME);
//            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80003, l_ex.getErrorInfo());
//            
//            try
//            {
//                QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
//                List l_lis = l_queryProcessor.doFindAllQuery(BatoProductManagementParams.TYPE);
//                assertEquals(2, l_lis.size());
//            }
//            catch (Exception ex)
//            {
//                log.error(STR_METHOD_NAME, l_ex);
//                log.exiting(STR_METHOD_NAME);
//                
//                fail();
//            }
//        }
//        catch (Exception l_ex)
//        {
//            log.error(STR_METHOD_NAME, l_ex);
//            log.exiting(STR_METHOD_NAME);
//            fail();
//        }
//
//        
//        log.exiting(STR_METHOD_NAME);
//    }

    public void testdeleteBatoProductCodeAdmin_0003()
    {
        final String STR_METHOD_NAME = "testdeleteBatoProductCodeAdmin_0003()";
        log.entering(STR_METHOD_NAME);

        try
        {
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            TestDBUtility.deleteAll(InstitutionParams.TYPE);
            TestDBUtility.insertWithDel(l_institutionParams);
            TestDBUtility.deleteAll(BatoProductManagementParams.TYPE);
            BatoProductManagementParams l_batoProductManagementParams = this.getBatoProductManagementRow();
            l_batoProductManagementParams.setDocumentDiv("11");
            TestDBUtility.insertWithDel(l_batoProductManagementParams);

            BatoProductManagementParams l_batoProductManagementParams1 = this.getBatoProductManagementRow();
            l_batoProductManagementParams1.setDocumentDiv("12");
            TestDBUtility.insertWithDel(l_batoProductManagementParams1);

            BatoProductManagementParams l_batoProductManagementParams2 = this.getBatoProductManagementRow();
            l_batoProductManagementParams2.setDocumentDiv("13");
            TestDBUtility.insertWithDel(l_batoProductManagementParams2);

            AdministratorParams l_administratorParams = TestDBUtility.getAdministratorRow();
            l_administratorParams.setInstitutionCode("0D");
            l_administratorParams.setBranchCode("381");
            TestDBUtility.deleteAll(AdministratorRow.TYPE);
            TestDBUtility.insertWithDel(l_administratorParams);
            TestDBUtility.deleteAll(BranchRow.TYPE);
            WEB3Administrator l_expectAdministrator = new WEB3Administrator(l_administratorParams);

            WEB3AdministratorForMock.mockGetInstanceFromLoginInfo(l_expectAdministrator);

            // getInstanceFromログイン情報()
            //管理者インスタンスを取得する。
            WEB3Administrator l_administrator =
                WEB3Administrator.getInstanceFromLoginInfo();
            WEB3FPTDocumentUpdateInfoUnit[] documentUpdateList = new WEB3FPTDocumentUpdateInfoUnit[3];
            WEB3FPTDocumentUpdateInfoUnit unit1 = new WEB3FPTDocumentUpdateInfoUnit();
            unit1.documentDiv = "11";
            unit1.batoProductCode = "000";
            unit1.documentNumber = "000";
            documentUpdateList[0] = unit1;
            WEB3FPTDocumentUpdateInfoUnit unit2 = new WEB3FPTDocumentUpdateInfoUnit();
            unit2.documentDiv = "12";
            unit2.batoProductCode = "000";
            unit2.documentNumber = "000";
            documentUpdateList[1] = unit2;
            WEB3FPTDocumentUpdateInfoUnit unit3 = new WEB3FPTDocumentUpdateInfoUnit();
            unit3.documentDiv = "13";
            unit3.batoProductCode = "000";
            unit3.documentNumber = "000";
            documentUpdateList[2] = unit3;
            WEB3AdminFPTBatoProductCodeManagement management =
                new WEB3AdminFPTBatoProductCodeManagement(
                    l_administrator,
                    documentUpdateList,
                    "1");
            management.deleteBatoProductCodeAdmin();
            
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }

        
        log.exiting(STR_METHOD_NAME);
    }

    public void testgetBatoProductManagementAllBranch_0001()
    {
        final String STR_METHOD_NAME = "testgetBatoProductManagementAllBranch_0001()";
        log.entering(STR_METHOD_NAME);

        try
        {
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            TestDBUtility.deleteAll(InstitutionParams.TYPE);
            TestDBUtility.insertWithDel(l_institutionParams);
            AdministratorParams l_administratorParams = TestDBUtility.getAdministratorRow();
            l_administratorParams.setInstitutionCode("0D");
            l_administratorParams.setBranchCode("381");
            TestDBUtility.deleteAll(AdministratorRow.TYPE);
            TestDBUtility.insertWithDel(l_administratorParams);
            TestDBUtility.deleteAll(BranchRow.TYPE);
            WEB3Administrator l_expectAdministrator = new WEB3Administrator(l_administratorParams);

            WEB3AdministratorForMock.mockGetInstanceFromLoginInfo(l_expectAdministrator);

            // getInstanceFromログイン情報()
            //管理者インスタンスを取得する。
            WEB3Administrator l_administrator =
                WEB3Administrator.getInstanceFromLoginInfo();
            WEB3FPTDocumentUpdateInfoUnit[] documentUpdateList = new WEB3FPTDocumentUpdateInfoUnit[0];
            WEB3AdminFPTBatoProductCodeManagement management =
                new WEB3AdminFPTBatoProductCodeManagement(
                    l_administrator,
                    documentUpdateList,
                    "1");

            Method method = management.getClass().getDeclaredMethod("getBatoProductManagementAllBranch", null);
            method.setAccessible(true);
            List l_lis = (List)method.invoke(management, null);
            
            assertEquals(0, l_lis.size());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }

    public void testgetBatoProductManagementAllBranch_0002()
    {
        final String STR_METHOD_NAME = "testgetBatoProductManagementAllBranch_0002()";
        log.entering(STR_METHOD_NAME);

        try
        {
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            TestDBUtility.deleteAll(InstitutionParams.TYPE);
            TestDBUtility.insertWithDel(l_institutionParams);
            AdministratorParams l_administratorParams = TestDBUtility.getAdministratorRow();
            l_administratorParams.setInstitutionCode("0D");
            l_administratorParams.setBranchCode("381");
            TestDBUtility.deleteAll(AdministratorRow.TYPE);
            TestDBUtility.insertWithDel(l_administratorParams);
            WEB3Administrator l_expectAdministrator = new WEB3Administrator(l_administratorParams);

            TestDBUtility.deleteAll(BranchRow.TYPE);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            TestDBUtility.insertWithDel(l_branchParams);
            
            WEB3AdministratorForMock.mockGetInstanceFromLoginInfo(l_expectAdministrator);

            // getInstanceFromログイン情報()
            //管理者インスタンスを取得する。
            WEB3Administrator l_administrator =
                WEB3Administrator.getInstanceFromLoginInfo();
            WEB3FPTDocumentUpdateInfoUnit[] documentUpdateList = new WEB3FPTDocumentUpdateInfoUnit[0];
            WEB3AdminFPTBatoProductCodeManagement management =
                new WEB3AdminFPTBatoProductCodeManagement(
                    l_administrator,
                    documentUpdateList,
                    "1");

            Method method = management.getClass().getDeclaredMethod("getBatoProductManagementAllBranch", null);
            method.setAccessible(true);
            List l_lis = (List)method.invoke(management, null);
            
            assertEquals(0, l_lis.size());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }

    public void testgetBatoProductManagementAllBranch_0003()
    {
        final String STR_METHOD_NAME = "testgetBatoProductManagementAllBranch_0003()";
        log.entering(STR_METHOD_NAME);

        try
        {
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            TestDBUtility.deleteAll(InstitutionParams.TYPE);
            TestDBUtility.insertWithDel(l_institutionParams);
            BatoProductManagementParams l_batoProductManagementParams = this.getBatoProductManagementRow();
            TestDBUtility.deleteAll(BatoProductManagementParams.TYPE);
            TestDBUtility.insertWithDel(l_batoProductManagementParams);

            AdministratorParams l_administratorParams = TestDBUtility.getAdministratorRow();
            l_administratorParams.setInstitutionCode("0D");
            l_administratorParams.setBranchCode("381");
            TestDBUtility.deleteAll(AdministratorRow.TYPE);
            TestDBUtility.insertWithDel(l_administratorParams);
            WEB3Administrator l_expectAdministrator = new WEB3Administrator(l_administratorParams);

            TestDBUtility.deleteAll(BranchRow.TYPE);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            TestDBUtility.insertWithDel(l_branchParams);

            WEB3AdministratorForMock.mockGetInstanceFromLoginInfo(l_expectAdministrator);

            // getInstanceFromログイン情報()
            //管理者インスタンスを取得する。
            WEB3Administrator l_administrator =
                WEB3Administrator.getInstanceFromLoginInfo();
            WEB3FPTDocumentUpdateInfoUnit[] documentUpdateList = new WEB3FPTDocumentUpdateInfoUnit[1];
            WEB3FPTDocumentUpdateInfoUnit unit = new WEB3FPTDocumentUpdateInfoUnit();
            unit.batoProductCode = "000";
            unit.documentDiv = "10";
            unit.documentNumber = "000";
            documentUpdateList[0] = unit;
            WEB3AdminFPTBatoProductCodeManagement management =
                new WEB3AdminFPTBatoProductCodeManagement(
                    l_administrator,
                    documentUpdateList,
                    "1");

            Method method = management.getClass().getDeclaredMethod("getBatoProductManagementAllBranch", null);
            method.setAccessible(true);
            List l_lis = (List)method.invoke(management, null);

            assertEquals(1, l_lis.size());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }

        log.exiting(STR_METHOD_NAME);
    }

    public void testgetBatoProductManagementAllBranch_0004()
    {
        final String STR_METHOD_NAME = "testgetBatoProductManagementAllBranch_0004()";
        log.entering(STR_METHOD_NAME);

        try
        {
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            TestDBUtility.deleteAll(InstitutionParams.TYPE);
            TestDBUtility.insertWithDel(l_institutionParams);
            BatoProductManagementParams l_batoProductManagementParams = this.getBatoProductManagementRow();
            TestDBUtility.deleteAll(BatoProductManagementParams.TYPE);
            TestDBUtility.insertWithDel(l_batoProductManagementParams);

            AdministratorParams l_administratorParams = TestDBUtility.getAdministratorRow();
            l_administratorParams.setInstitutionCode("0D");
            l_administratorParams.setBranchCode("381");
            TestDBUtility.deleteAll(AdministratorRow.TYPE);
            TestDBUtility.insertWithDel(l_administratorParams);
            WEB3Administrator l_expectAdministrator = new WEB3Administrator(l_administratorParams);

            TestDBUtility.deleteAll(BranchRow.TYPE);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            TestDBUtility.insertWithDel(l_branchParams);

            WEB3AdministratorForMock.mockGetInstanceFromLoginInfo(l_expectAdministrator);

            // getInstanceFromログイン情報()
            //管理者インスタンスを取得する。
            WEB3Administrator l_administrator =
                WEB3Administrator.getInstanceFromLoginInfo();
            WEB3FPTDocumentUpdateInfoUnit[] documentUpdateList = new WEB3FPTDocumentUpdateInfoUnit[1];
            WEB3FPTDocumentUpdateInfoUnit unit = new WEB3FPTDocumentUpdateInfoUnit();
            unit.batoProductCode = "000";
            unit.documentDiv = "11";
            unit.documentNumber = "000";
            documentUpdateList[0] = unit;
            WEB3AdminFPTBatoProductCodeManagement management =
                new WEB3AdminFPTBatoProductCodeManagement(
                    l_administrator,
                    documentUpdateList,
                    "1");

            Method method = management.getClass().getDeclaredMethod("getBatoProductManagementAllBranch", null);
            method.setAccessible(true);
            List l_lis = (List)method.invoke(management, null);

            assertEquals(1, l_lis.size());
            assertEquals(null, l_lis.get(0));
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }

        log.exiting(STR_METHOD_NAME);
    }

    public void testgetBatoProductManagementList_0001()
    {
        final String STR_METHOD_NAME = "testgetBatoProductManagementList_0001()";
        log.entering(STR_METHOD_NAME);

        try
        {
            TestDBUtility.deleteAll(BatoProductManagementParams.TYPE);
            List l_lis = WEB3AdminFPTBatoProductCodeManagement.getBatoProductManagementList(
                "institution_code = ? ",
                new Object[]{"0D"},
                "");
            assertEquals(0, l_lis.size());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }

    public void testgetBatoProductManagementList_0002()
    {
        final String STR_METHOD_NAME = "testgetBatoProductManagementList_0002()";
        log.entering(STR_METHOD_NAME);

        try
        {
            BatoProductManagementParams l_batoProductManagementParams = this.getBatoProductManagementRow();
            TestDBUtility.deleteAll(BatoProductManagementParams.TYPE);
            TestDBUtility.insertWithDel(l_batoProductManagementParams);
            List l_lis = WEB3AdminFPTBatoProductCodeManagement.getBatoProductManagementList(
                "institution_code = ? ",
                new Object[]{"0D"},
                "");
            assertEquals(1, l_lis.size());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }

        log.exiting(STR_METHOD_NAME);
    }
    
    public void testinsertBatoProductManagement_0001()
    {
        final String STR_METHOD_NAME = "testinsertBatoProductManagement_0001()";
        log.entering(STR_METHOD_NAME);

        try
        {
            TestDBUtility.deleteAll(BatoProductManagementParams.TYPE);
            AdministratorParams l_administratorParams = TestDBUtility.getAdministratorRow();
            l_administratorParams.setInstitutionCode("0D");
            l_administratorParams.setBranchCode("381");
            TestDBUtility.deleteAll(AdministratorRow.TYPE);
            TestDBUtility.insertWithDel(l_administratorParams);
            TestDBUtility.deleteAll(BranchRow.TYPE);
            WEB3Administrator l_expectAdministrator = new WEB3Administrator(l_administratorParams);

            WEB3AdministratorForMock.mockGetInstanceFromLoginInfo(l_expectAdministrator);

            // getInstanceFromログイン情報()
            //管理者インスタンスを取得する。
            WEB3Administrator l_administrator =
                WEB3Administrator.getInstanceFromLoginInfo();
            WEB3FPTDocumentUpdateInfoUnit[] documentUpdateList = new WEB3FPTDocumentUpdateInfoUnit[0];
            WEB3AdminFPTBatoProductCodeManagement management =
                new WEB3AdminFPTBatoProductCodeManagement(
                    l_administrator,
                    documentUpdateList,
                    "1");

            management.insertBatoProductManagement();

            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            List l_lis = l_queryProcessor.doFindAllQuery(BatoProductManagementParams.TYPE);
            assertEquals(0, l_lis.size());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }

    public void testinsertBatoProductManagement_0002()
    {
        final String STR_METHOD_NAME = "testinsertBatoProductManagement_0002()";
        log.entering(STR_METHOD_NAME);

        try
        {
            TestDBUtility.deleteAll(BatoProductManagementParams.TYPE);
            AdministratorParams l_administratorParams = TestDBUtility.getAdministratorRow();
            l_administratorParams.setInstitutionCode("0D");
            l_administratorParams.setBranchCode("381");
            TestDBUtility.deleteAll(AdministratorRow.TYPE);
            TestDBUtility.insertWithDel(l_administratorParams);
            WEB3Administrator l_expectAdministrator = new WEB3Administrator(l_administratorParams);

            TestDBUtility.deleteAll(BranchRow.TYPE);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            TestDBUtility.insertWithDel(l_branchParams);
            
            WEB3AdministratorForMock.mockGetInstanceFromLoginInfo(l_expectAdministrator);

            WEB3Administrator l_administrator =
                WEB3Administrator.getInstanceFromLoginInfo();
            WEB3FPTDocumentUpdateInfoUnit[] documentUpdateList = new WEB3FPTDocumentUpdateInfoUnit[1];
            WEB3FPTDocumentUpdateInfoUnit unit = new WEB3FPTDocumentUpdateInfoUnit();
            unit.batoProductCode = "000";
            unit.documentDiv = "11";
            unit.documentNumber = "000";
            unit.validFlag = "0";
            documentUpdateList[0] = unit;
            WEB3AdminFPTBatoProductCodeManagement management =
                new WEB3AdminFPTBatoProductCodeManagement(
                    l_administrator,
                    documentUpdateList,
                    "1");

            management.insertBatoProductManagement();

            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            List l_lis = l_queryProcessor.doFindAllQuery(BatoProductManagementParams.TYPE);
            assertEquals(1, l_lis.size());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }

    public void testinsertBatoProductManagement_0003()
    {
        final String STR_METHOD_NAME = "testinsertBatoProductManagement_0003()";
        log.entering(STR_METHOD_NAME);

        try
        {
            TestDBUtility.deleteAll(BatoProductManagementParams.TYPE);
            AdministratorParams l_administratorParams = TestDBUtility.getAdministratorRow();
            l_administratorParams.setInstitutionCode("0D");
            l_administratorParams.setBranchCode("381");
            TestDBUtility.deleteAll(AdministratorRow.TYPE);
            TestDBUtility.insertWithDel(l_administratorParams);
            WEB3Administrator l_expectAdministrator = new WEB3Administrator(l_administratorParams);

            TestDBUtility.deleteAll(BranchRow.TYPE);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            TestDBUtility.insertWithDel(l_branchParams);
            
            WEB3AdministratorForMock.mockGetInstanceFromLoginInfo(l_expectAdministrator);

            WEB3Administrator l_administrator =
                WEB3Administrator.getInstanceFromLoginInfo();
            WEB3FPTDocumentUpdateInfoUnit[] documentUpdateList = new WEB3FPTDocumentUpdateInfoUnit[3];
            WEB3FPTDocumentUpdateInfoUnit unit = new WEB3FPTDocumentUpdateInfoUnit();
            unit.batoProductCode = "000";
            unit.documentDiv = "11";
            unit.documentNumber = "000";
            unit.validFlag = "0";
            documentUpdateList[0] = unit;
            WEB3FPTDocumentUpdateInfoUnit unit1 = new WEB3FPTDocumentUpdateInfoUnit();
            unit1.batoProductCode = "000";
            unit1.documentDiv = "11";
            unit1.documentNumber = "000";
            unit1.validFlag = "0";
            documentUpdateList[1] = unit1;
            WEB3FPTDocumentUpdateInfoUnit unit2 = new WEB3FPTDocumentUpdateInfoUnit();
            unit2.batoProductCode = "000";
            unit2.documentDiv = "12";
            unit2.documentNumber = "000";
            unit2.validFlag = "0";
            documentUpdateList[2] = unit2;
            WEB3AdminFPTBatoProductCodeManagement management =
                new WEB3AdminFPTBatoProductCodeManagement(
                    l_administrator,
                    documentUpdateList,
                    "1");

            management.insertBatoProductManagement();

            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80003, l_ex.getErrorInfo());
            
            try
            {
                QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
                List l_lis = l_queryProcessor.doFindAllQuery(BatoProductManagementParams.TYPE);
                assertEquals(1, l_lis.size());
            }
            catch (Exception ex)
            {
                log.error(STR_METHOD_NAME, l_ex);
                log.exiting(STR_METHOD_NAME);
                fail();
            }
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }

    public void testinsertBatoProductManagement_0004()
    {
        final String STR_METHOD_NAME = "testinsertBatoProductManagement_0004()";
        log.entering(STR_METHOD_NAME);

        try
        {
            TestDBUtility.deleteAll(BatoProductManagementParams.TYPE);
            AdministratorParams l_administratorParams = TestDBUtility.getAdministratorRow();
            l_administratorParams.setInstitutionCode("0D");
            l_administratorParams.setBranchCode("381");
            TestDBUtility.deleteAll(AdministratorRow.TYPE);
            TestDBUtility.insertWithDel(l_administratorParams);
            WEB3Administrator l_expectAdministrator = new WEB3Administrator(l_administratorParams);

            TestDBUtility.deleteAll(BranchRow.TYPE);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            TestDBUtility.insertWithDel(l_branchParams);
            
            WEB3AdministratorForMock.mockGetInstanceFromLoginInfo(l_expectAdministrator);

            WEB3Administrator l_administrator =
                WEB3Administrator.getInstanceFromLoginInfo();
            WEB3FPTDocumentUpdateInfoUnit[] documentUpdateList = new WEB3FPTDocumentUpdateInfoUnit[3];
            WEB3FPTDocumentUpdateInfoUnit unit = new WEB3FPTDocumentUpdateInfoUnit();
            unit.batoProductCode = "000";
            unit.documentDiv = "10";
            unit.documentNumber = "000";
            unit.validFlag = "0";
            documentUpdateList[0] = unit;
            WEB3FPTDocumentUpdateInfoUnit unit1 = new WEB3FPTDocumentUpdateInfoUnit();
            unit1.batoProductCode = "000";
            unit1.documentDiv = "11";
            unit1.documentNumber = "000";
            unit1.validFlag = "0";
            documentUpdateList[1] = unit1;
            WEB3FPTDocumentUpdateInfoUnit unit2 = new WEB3FPTDocumentUpdateInfoUnit();
            unit2.batoProductCode = "000";
            unit2.documentDiv = "12";
            unit2.documentNumber = "000";
            unit2.validFlag = "0";
            documentUpdateList[2] = unit2;
            WEB3AdminFPTBatoProductCodeManagement management =
                new WEB3AdminFPTBatoProductCodeManagement(
                    l_administrator,
                    documentUpdateList,
                    "1");

            management.insertBatoProductManagement();

            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            List l_lis = l_queryProcessor.doFindAllQuery(BatoProductManagementParams.TYPE);
            assertEquals(3, l_lis.size());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }

    public void testupdateBatoProductManagement_0001()
    {
        final String STR_METHOD_NAME = "testupdateBatoProductManagement_0001()";
        log.entering(STR_METHOD_NAME);

        try
        {
            AdministratorParams l_administratorParams = TestDBUtility.getAdministratorRow();
            l_administratorParams.setInstitutionCode("0D");
            l_administratorParams.setBranchCode("381");
            TestDBUtility.deleteAll(AdministratorRow.TYPE);
            TestDBUtility.insertWithDel(l_administratorParams);
            TestDBUtility.deleteAll(BranchRow.TYPE);
            WEB3Administrator l_expectAdministrator = new WEB3Administrator(l_administratorParams);

            WEB3AdministratorForMock.mockGetInstanceFromLoginInfo(l_expectAdministrator);

            // getInstanceFromログイン情報()
            //管理者インスタンスを取得する。
            WEB3Administrator l_administrator =
                WEB3Administrator.getInstanceFromLoginInfo();
            WEB3FPTDocumentUpdateInfoUnit[] documentUpdateList = new WEB3FPTDocumentUpdateInfoUnit[0];
            WEB3AdminFPTBatoProductCodeManagement management =
                new WEB3AdminFPTBatoProductCodeManagement(
                    l_administrator,
                    documentUpdateList,
                    "1");

            management.updateBatoProductManagement();
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }

    public void testupdateBatoProductManagement_0002()
    {
        final String STR_METHOD_NAME = "testupdateBatoProductManagement_0002()";
        log.entering(STR_METHOD_NAME);

        try
        {
            TestDBUtility.deleteAll(BatoProductManagementParams.TYPE);

            AdministratorParams l_administratorParams = TestDBUtility.getAdministratorRow();
            l_administratorParams.setInstitutionCode("0D");
            l_administratorParams.setBranchCode("381");
            TestDBUtility.deleteAll(AdministratorRow.TYPE);
            TestDBUtility.insertWithDel(l_administratorParams);
            TestDBUtility.deleteAll(BranchRow.TYPE);
            WEB3Administrator l_expectAdministrator = new WEB3Administrator(l_administratorParams);

            WEB3AdministratorForMock.mockGetInstanceFromLoginInfo(l_expectAdministrator);

            // getInstanceFromログイン情報()
            //管理者インスタンスを取得する。
            WEB3Administrator l_administrator =
                WEB3Administrator.getInstanceFromLoginInfo();
            WEB3FPTDocumentUpdateInfoUnit[] documentUpdateList = new WEB3FPTDocumentUpdateInfoUnit[1];
            WEB3FPTDocumentUpdateInfoUnit unit = new WEB3FPTDocumentUpdateInfoUnit();
            unit.batoProductCode = "000";
            unit.documentDiv = "10";
            unit.documentNumber = "000";
            unit.validFlag = "0";
            documentUpdateList[0] = unit;
            WEB3AdminFPTBatoProductCodeManagement management =
                new WEB3AdminFPTBatoProductCodeManagement(
                    l_administrator,
                    documentUpdateList,
                    "1");

            management.updateBatoProductManagement();
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_03038, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }

    public void testupdateBatoProductManagement_0003()
    {
        final String STR_METHOD_NAME = "testupdateBatoProductManagement_0003()";
        log.entering(STR_METHOD_NAME);

        try
        {
            BatoProductManagementParams l_batoProductManagementParams = this.getBatoProductManagementRow();
            TestDBUtility.deleteAll(BatoProductManagementParams.TYPE);
            TestDBUtility.insertWithDel(l_batoProductManagementParams);

            AdministratorParams l_administratorParams = TestDBUtility.getAdministratorRow();
            l_administratorParams.setInstitutionCode("0D");
            l_administratorParams.setBranchCode("381");
            TestDBUtility.deleteAll(AdministratorRow.TYPE);
            TestDBUtility.insertWithDel(l_administratorParams);
            TestDBUtility.deleteAll(BranchRow.TYPE);
            WEB3Administrator l_expectAdministrator = new WEB3Administrator(l_administratorParams);

            WEB3AdministratorForMock.mockGetInstanceFromLoginInfo(l_expectAdministrator);

            // getInstanceFromログイン情報()
            //管理者インスタンスを取得する。
            WEB3Administrator l_administrator =
                WEB3Administrator.getInstanceFromLoginInfo();
            WEB3FPTDocumentUpdateInfoUnit[] documentUpdateList = new WEB3FPTDocumentUpdateInfoUnit[1];
            WEB3FPTDocumentUpdateInfoUnit unit = new WEB3FPTDocumentUpdateInfoUnit();
            unit.batoProductCode = "000";
            unit.documentDiv = "10";
            unit.documentNumber = "000";
            unit.documentCategory = "000";
            unit.validFlag = "5";
            documentUpdateList[0] = unit;
            WEB3AdminFPTBatoProductCodeManagement management =
                new WEB3AdminFPTBatoProductCodeManagement(
                    l_administrator,
                    documentUpdateList,
                    "1");

            management.updateBatoProductManagement();

            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            List l_lis = l_queryProcessor.doFindAllQuery(BatoProductManagementParams.TYPE);
            assertEquals(1, l_lis.size());
            assertEquals("5", ((BatoProductManagementParams)l_lis.get(0)).getValidFlag());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }

    public void testupdateBatoProductManagement_0004()
    {
        final String STR_METHOD_NAME = "testupdateBatoProductManagement_0004()";
        log.entering(STR_METHOD_NAME);

        QueryProcessor l_queryProcessor = null;
        try
        {
            l_queryProcessor = Processors.getDefaultProcessor();

            BatoProductManagementParams l_batoProductManagementParams = this.getBatoProductManagementRow();
            TestDBUtility.deleteAll(BatoProductManagementParams.TYPE);
            TestDBUtility.insertWithDel(l_batoProductManagementParams);
            BatoProductManagementParams l_batoProductManagementParams1 = this.getBatoProductManagementRow();
            l_batoProductManagementParams1.setDocumentDiv("11");
            TestDBUtility.insertWithDel(l_batoProductManagementParams1);
            BatoProductManagementParams l_batoProductManagementParams2 = this.getBatoProductManagementRow();
            l_batoProductManagementParams2.setDocumentDiv("12");
            TestDBUtility.insertWithDel(l_batoProductManagementParams2);

            String l_strQueryString = " institution_code = ? and branch_code = ?"
                + " and document_div = ? and bato_product_code = ? ";

            Object[] l_queryContainers = {
                "0D",
                "381",
                "11",
                "000000"};

            l_queryProcessor.doFindAllQuery(
                BatoProductManagementRow.TYPE,
                l_strQueryString,
                null,
                "for update",
                l_queryContainers);
            
            
            AdministratorParams l_administratorParams = TestDBUtility.getAdministratorRow();
            l_administratorParams.setInstitutionCode("0D");
            l_administratorParams.setBranchCode("381");
            TestDBUtility.deleteAll(AdministratorRow.TYPE);
            TestDBUtility.insertWithDel(l_administratorParams);
            TestDBUtility.deleteAll(BranchRow.TYPE);
            WEB3Administrator l_expectAdministrator = new WEB3Administrator(l_administratorParams);

            WEB3AdministratorForMock.mockGetInstanceFromLoginInfo(l_expectAdministrator);

            // getInstanceFromログイン情報()
            //管理者インスタンスを取得する。
            WEB3Administrator l_administrator =
                WEB3Administrator.getInstanceFromLoginInfo();
            WEB3FPTDocumentUpdateInfoUnit[] documentUpdateList = new WEB3FPTDocumentUpdateInfoUnit[3];
            WEB3FPTDocumentUpdateInfoUnit unit = new WEB3FPTDocumentUpdateInfoUnit();
            unit.batoProductCode = "000";
            unit.documentDiv = "10";
            unit.documentNumber = "000";
            unit.documentCategory = "000";
            unit.validFlag = "1";
            documentUpdateList[0] = unit;
            WEB3FPTDocumentUpdateInfoUnit unit1 = new WEB3FPTDocumentUpdateInfoUnit();
            unit1.batoProductCode = "000";
            unit1.documentDiv = "11";
            unit1.documentNumber = "000";
            unit1.documentCategory = "000";
            unit1.validFlag = "2";
            documentUpdateList[1] = unit1;
            WEB3FPTDocumentUpdateInfoUnit unit2 = new WEB3FPTDocumentUpdateInfoUnit();
            unit2.batoProductCode = "000";
            unit2.documentDiv = "12";
            unit2.documentNumber = "000";
            unit2.documentCategory = "000";
            unit2.validFlag = "3";
            documentUpdateList[2] = unit2;
            WEB3AdminFPTBatoProductCodeManagement management =
                new WEB3AdminFPTBatoProductCodeManagement(
                    l_administrator,
                    documentUpdateList,
                    "1");

            management.updateBatoProductManagement();
            
//            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80003, l_ex.getErrorInfo());
            
            try
            {
                List l_lis = l_queryProcessor.doFindAllQuery(BatoProductManagementParams.TYPE);
                assertEquals(3, l_lis.size());
                assertEquals("1", ((BatoProductManagementParams)l_lis.get(0)).getValidFlag());
                assertEquals("0", ((BatoProductManagementParams)l_lis.get(1)).getValidFlag());
                assertEquals("0", ((BatoProductManagementParams)l_lis.get(3)).getValidFlag());
            }
            catch (Exception ex)
            {
                log.error(STR_METHOD_NAME, l_ex);
                log.exiting(STR_METHOD_NAME);
                fail();
            }
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }

    public void testvalidateBatoProductManagementParams_0001()
    {
        final String STR_METHOD_NAME = "testValidateBatoProductManagementParams_0001()";
        log.entering(STR_METHOD_NAME);

        try
        {
            AdministratorParams l_administratorParams = TestDBUtility.getAdministratorRow();
            l_administratorParams.setInstitutionCode("0D");
            l_administratorParams.setBranchCode("381");
            TestDBUtility.deleteAll(AdministratorRow.TYPE);
            TestDBUtility.insertWithDel(l_administratorParams);
            WEB3Administrator l_expectAdministrator = new WEB3Administrator(l_administratorParams);

            TestDBUtility.deleteAll(BranchRow.TYPE);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            TestDBUtility.insertWithDel(l_branchParams);
            
            WEB3AdministratorForMock.mockGetInstanceFromLoginInfo(l_expectAdministrator);

            // getInstanceFromログイン情報()
            //管理者インスタンスを取得する。
            WEB3Administrator l_administrator =
                WEB3Administrator.getInstanceFromLoginInfo();
            WEB3FPTDocumentUpdateInfoUnit[] documentUpdateList = new WEB3FPTDocumentUpdateInfoUnit[0];
            WEB3AdminFPTBatoProductCodeManagement management =
                new WEB3AdminFPTBatoProductCodeManagement(
                    l_administrator,
                    documentUpdateList,
                    "0");
            management.validateBatoProductManagementParams();
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }

    public void testvalidateBatoProductManagementParams_0002()
    {
        final String STR_METHOD_NAME = "testValidateBatoProductManagementParams_0002()";
        log.entering(STR_METHOD_NAME);

        try
        {
            BatoProductManagementParams l_batoProductManagementParams = this.getBatoProductManagementRow();
            TestDBUtility.deleteAll(BatoProductManagementParams.TYPE);
            TestDBUtility.insertWithDel(l_batoProductManagementParams);

            AdministratorParams l_administratorParams = TestDBUtility.getAdministratorRow();
            l_administratorParams.setInstitutionCode("0D");
            l_administratorParams.setBranchCode("381");
            TestDBUtility.deleteAll(AdministratorRow.TYPE);
            TestDBUtility.insertWithDel(l_administratorParams);
            WEB3Administrator l_expectAdministrator = new WEB3Administrator(l_administratorParams);

            TestDBUtility.deleteAll(BranchRow.TYPE);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            TestDBUtility.insertWithDel(l_branchParams);

            WEB3AdministratorForMock.mockGetInstanceFromLoginInfo(l_expectAdministrator);

            // getInstanceFromログイン情報()
            //管理者インスタンスを取得する。
            WEB3Administrator l_administrator =
                WEB3Administrator.getInstanceFromLoginInfo();
            WEB3FPTDocumentUpdateInfoUnit[] documentUpdateList = new WEB3FPTDocumentUpdateInfoUnit[1];
            WEB3FPTDocumentUpdateInfoUnit unit = new WEB3FPTDocumentUpdateInfoUnit();
            unit.batoProductCode = "000";
            unit.documentDiv = "10";
            unit.documentNumber = "000";
            unit.documentCategory = "000";
            documentUpdateList[0] = unit;
            WEB3AdminFPTBatoProductCodeManagement management =
                new WEB3AdminFPTBatoProductCodeManagement(
                    l_administrator,
                    documentUpdateList,
                    "0");
            management.validateBatoProductManagementParams();
            
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_03037, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }

    public void testvalidateBatoProductManagementParams_0003()
    {
        final String STR_METHOD_NAME = "testValidateBatoProductManagementParams_0003()";
        log.entering(STR_METHOD_NAME);

        try
        {
            BatoProductManagementParams l_batoProductManagementParams = this.getBatoProductManagementRow();
            TestDBUtility.deleteAll(BatoProductManagementParams.TYPE);
            TestDBUtility.insertWithDel(l_batoProductManagementParams);

            AdministratorParams l_administratorParams = TestDBUtility.getAdministratorRow();
            l_administratorParams.setInstitutionCode("0D");
            l_administratorParams.setBranchCode("381");
            TestDBUtility.deleteAll(AdministratorRow.TYPE);
            TestDBUtility.insertWithDel(l_administratorParams);
            WEB3Administrator l_expectAdministrator = new WEB3Administrator(l_administratorParams);

            TestDBUtility.deleteAll(BranchRow.TYPE);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            TestDBUtility.insertWithDel(l_branchParams);

            WEB3AdministratorForMock.mockGetInstanceFromLoginInfo(l_expectAdministrator);

            // getInstanceFromログイン情報()
            //管理者インスタンスを取得する。
            WEB3Administrator l_administrator =
                WEB3Administrator.getInstanceFromLoginInfo();
            WEB3FPTDocumentUpdateInfoUnit[] documentUpdateList = new WEB3FPTDocumentUpdateInfoUnit[1];
            WEB3FPTDocumentUpdateInfoUnit unit = new WEB3FPTDocumentUpdateInfoUnit();
            unit.batoProductCode = "000";
            unit.documentDiv = "10";
            unit.documentNumber = "000";
//            unit.documentCategory = "000";
            documentUpdateList[0] = unit;
            WEB3AdminFPTBatoProductCodeManagement management =
                new WEB3AdminFPTBatoProductCodeManagement(
                    l_administrator,
                    documentUpdateList,
                    "1");
            management.validateBatoProductManagementParams();
            
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_03038, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }

    public void testvalidateBatoProductManagementParams_0004()
    {
        final String STR_METHOD_NAME = "testValidateBatoProductManagementParams_0004()";
        log.entering(STR_METHOD_NAME);

        try
        {
            BatoProductManagementParams l_batoProductManagementParams = this.getBatoProductManagementRow();
            l_batoProductManagementParams.setValidFlag("1");
            TestDBUtility.deleteAll(BatoProductManagementParams.TYPE);
            TestDBUtility.insertWithDel(l_batoProductManagementParams);

            AdministratorParams l_administratorParams = TestDBUtility.getAdministratorRow();
            l_administratorParams.setInstitutionCode("0D");
            l_administratorParams.setBranchCode("381");
            TestDBUtility.deleteAll(AdministratorRow.TYPE);
            TestDBUtility.insertWithDel(l_administratorParams);
            WEB3Administrator l_expectAdministrator = new WEB3Administrator(l_administratorParams);

            TestDBUtility.deleteAll(BranchRow.TYPE);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            TestDBUtility.insertWithDel(l_branchParams);

            WEB3AdministratorForMock.mockGetInstanceFromLoginInfo(l_expectAdministrator);

            // getInstanceFromログイン情報()
            //管理者インスタンスを取得する。
            WEB3Administrator l_administrator =
                WEB3Administrator.getInstanceFromLoginInfo();
            WEB3FPTDocumentUpdateInfoUnit[] documentUpdateList = new WEB3FPTDocumentUpdateInfoUnit[0];
//            WEB3FPTDocumentUpdateInfoUnit unit = new WEB3FPTDocumentUpdateInfoUnit();
//            unit.batoProductCode = "000";
//            unit.documentDiv = "10";
//            unit.documentNumber = "000";
//            unit.documentCategory = "000";
//            documentUpdateList[0] = unit;
            WEB3AdminFPTBatoProductCodeManagement management =
                new WEB3AdminFPTBatoProductCodeManagement(
                    l_administrator,
                    documentUpdateList,
                    "1");
            management.validateBatoProductManagementParams();
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }

    public void testvalidateBatoProductManagementParams_0005()
    {
        final String STR_METHOD_NAME = "testValidateBatoProductManagementParams_0005()";
        log.entering(STR_METHOD_NAME);

        try
        {
            BatoProductManagementParams l_batoProductManagementParams = this.getBatoProductManagementRow();
            l_batoProductManagementParams.setValidFlag("0");
            TestDBUtility.deleteAll(BatoProductManagementParams.TYPE);
            TestDBUtility.insertWithDel(l_batoProductManagementParams);

            AdministratorParams l_administratorParams = TestDBUtility.getAdministratorRow();
            l_administratorParams.setInstitutionCode("0D");
            l_administratorParams.setBranchCode("381");
            TestDBUtility.deleteAll(AdministratorRow.TYPE);
            TestDBUtility.insertWithDel(l_administratorParams);
            WEB3Administrator l_expectAdministrator = new WEB3Administrator(l_administratorParams);

            TestDBUtility.deleteAll(BranchRow.TYPE);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            TestDBUtility.insertWithDel(l_branchParams);

            WEB3AdministratorForMock.mockGetInstanceFromLoginInfo(l_expectAdministrator);

            // getInstanceFromログイン情報()
            //管理者インスタンスを取得する。
            WEB3Administrator l_administrator =
                WEB3Administrator.getInstanceFromLoginInfo();
            WEB3FPTDocumentUpdateInfoUnit[] documentUpdateList = new WEB3FPTDocumentUpdateInfoUnit[1];
            WEB3FPTDocumentUpdateInfoUnit unit = new WEB3FPTDocumentUpdateInfoUnit();
            unit.batoProductCode = "000";
            unit.documentDiv = "10";
            unit.documentNumber = "000";
            unit.documentCategory = "000";
            documentUpdateList[0] = unit;
            WEB3AdminFPTBatoProductCodeManagement management =
                new WEB3AdminFPTBatoProductCodeManagement(
                    l_administrator,
                    documentUpdateList,
                    "2");
            management.validateBatoProductManagementParams();
            
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_03039, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }

    public void testvalidateBatoProductManagementParams_0006()
    {
        final String STR_METHOD_NAME = "testValidateBatoProductManagementParams_0006()";
        log.entering(STR_METHOD_NAME);

        try
        {
            AdministratorParams l_administratorParams = TestDBUtility.getAdministratorRow();
            l_administratorParams.setInstitutionCode("0D");
            l_administratorParams.setBranchCode("381");
            TestDBUtility.deleteAll(AdministratorRow.TYPE);
            TestDBUtility.insertWithDel(l_administratorParams);
            WEB3Administrator l_expectAdministrator = new WEB3Administrator(l_administratorParams);

            TestDBUtility.deleteAll(BranchRow.TYPE);
//            BranchParams l_branchParams = TestDBUtility.getBranchRow();
//            TestDBUtility.insertWithDel(l_branchParams);
            
            WEB3AdministratorForMock.mockGetInstanceFromLoginInfo(l_expectAdministrator);

            DocDeliveryManagementParams l_docDeliveryManagementRow = TestDBUtility.getDocDeliveryManagementRow();
            l_docDeliveryManagementRow.setDocumentDiv("10");
            l_docDeliveryManagementRow.setProductCode("000000");
            l_docDeliveryManagementRow.setDocumentCategory("000");
            TestDBUtility.deleteAll(DocDeliveryManagementParams.TYPE);
            TestDBUtility.insertWithDel(l_docDeliveryManagementRow);
            
            // getInstanceFromログイン情報()
            //管理者インスタンスを取得する。
            WEB3Administrator l_administrator =
                WEB3Administrator.getInstanceFromLoginInfo();
            WEB3FPTDocumentUpdateInfoUnit[] documentUpdateList = new WEB3FPTDocumentUpdateInfoUnit[1];
            WEB3FPTDocumentUpdateInfoUnit unit = new WEB3FPTDocumentUpdateInfoUnit();
            unit.batoProductCode = "000";
            unit.documentDiv = "10";
            unit.documentNumber = "000";
            unit.documentCategory = "000";
            documentUpdateList[0] = unit;
            WEB3AdminFPTBatoProductCodeManagement management =
                new WEB3AdminFPTBatoProductCodeManagement(
                    l_administrator,
                    documentUpdateList,
                    "2");
            management.validateBatoProductManagementParams();
            
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_03040, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }

    public void testvalidateBatoProductManagementParams_0007()
    {
        final String STR_METHOD_NAME = "testValidateBatoProductManagementParams_0007()";
        log.entering(STR_METHOD_NAME);

        try
        {
            AdministratorParams l_administratorParams = TestDBUtility.getAdministratorRow();
            l_administratorParams.setInstitutionCode("0D");
            l_administratorParams.setBranchCode("381");
            TestDBUtility.deleteAll(AdministratorRow.TYPE);
            TestDBUtility.insertWithDel(l_administratorParams);
            WEB3Administrator l_expectAdministrator = new WEB3Administrator(l_administratorParams);

            TestDBUtility.deleteAll(BranchRow.TYPE);
//            BranchParams l_branchParams = TestDBUtility.getBranchRow();
//            TestDBUtility.insertWithDel(l_branchParams);

            WEB3AdministratorForMock.mockGetInstanceFromLoginInfo(l_expectAdministrator);

//            DocDeliveryManagementParams l_docDeliveryManagementRow = TestDBUtility.getDocDeliveryManagementRow();
//            l_docDeliveryManagementRow.setDocumentDiv("10");
//            l_docDeliveryManagementRow.setProductCode("000000");
//            l_docDeliveryManagementRow.setDocumentCategory("000");
            TestDBUtility.deleteAll(DocDeliveryManagementParams.TYPE);
//            TestDBUtility.insertWithDel(l_docDeliveryManagementRow);

            // getInstanceFromログイン情報()
            //管理者インスタンスを取得する。
            WEB3Administrator l_administrator =
                WEB3Administrator.getInstanceFromLoginInfo();
            WEB3FPTDocumentUpdateInfoUnit[] documentUpdateList = new WEB3FPTDocumentUpdateInfoUnit[1];
            WEB3FPTDocumentUpdateInfoUnit unit = new WEB3FPTDocumentUpdateInfoUnit();
            unit.batoProductCode = "000";
            unit.documentDiv = "10";
            unit.documentNumber = "000";
            unit.documentCategory = "000";
            documentUpdateList[0] = unit;
            WEB3AdminFPTBatoProductCodeManagement management =
                new WEB3AdminFPTBatoProductCodeManagement(
                    l_administrator,
                    documentUpdateList,
                    "2");
            management.validateBatoProductManagementParams();
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    private BatoProductManagementParams getBatoProductManagementRow()
    {
        BatoProductManagementParams l_batoProductManagementParams = new BatoProductManagementParams();
        l_batoProductManagementParams.setInstitutionCode("0D");
        l_batoProductManagementParams.setBranchCode("381");
        l_batoProductManagementParams.setDocumentDiv("10");
        l_batoProductManagementParams.setBatoProductCode("000000");
        l_batoProductManagementParams.setCreatedTimestamp(new Date());
        l_batoProductManagementParams.setLastUpdatedTimestamp(new Date());
        l_batoProductManagementParams.setValidFlag("0");

        return l_batoProductManagementParams;
    }
}
@
