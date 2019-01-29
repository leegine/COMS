head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.01.37.50;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3AdminFXAccManagementServiceImplTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.aio.service.delegate.stdimpls;

import java.util.HashMap;
import java.util.List;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.plugin.security.oplogin.OpLoginAdminService;
import com.fitechlabs.xtrade.plugin.security.oplogin.OpLoginSecurityService;
import com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginAdminServiceImplForMock;
import com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImplForMock;
import com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.BranchParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountRow;

import test.util.TestDBUtility;

import webbroker3.aio.data.CompFxConditionParams;
import webbroker3.aio.data.FxAccountCodeParams;
import webbroker3.aio.data.FxAccountParams;
import webbroker3.aio.data.FxAccountRow;
import webbroker3.aio.message.WEB3AdminFXAccInfoChangeCompleteRequest;
import webbroker3.aio.message.WEB3AdminFXAccInfoChangeInputRequest;
import webbroker3.aio.message.WEB3AdminFXAccInfoChangeInputResponse;
import webbroker3.aio.message.WEB3AdminFXAccInfoSearchRequest;
import webbroker3.aio.message.WEB3AdminFXAccInfoSearchResponse;
import webbroker3.aio.message.WEB3FXAccInformationUnit;
import webbroker3.gentrade.WEB3Administrator;
import webbroker3.gentrade.WEB3AdministratorForMock;
import webbroker3.gentrade.data.AccOpenDivParams;
import webbroker3.gentrade.data.AccOpenDivRow;
import webbroker3.gentrade.data.AdminPermissionParams;
import webbroker3.gentrade.data.AdminPermissionRow;
import webbroker3.gentrade.data.AdministratorParams;
import webbroker3.gentrade.data.AdministratorTypeParams;
import webbroker3.gentrade.data.BranchPreferencesParams;
import webbroker3.gentrade.data.InstitutionPreferencesParams;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3LogUtility;

public class WEB3AdminFXAccManagementServiceImplTest extends TestBaseForMock
{

    /**
     * ログユーティリティ。
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminFXAccManagementServiceImplTest.class);
    
    WEB3AdminFXAccManagementServiceImpl l_impl;

    public WEB3AdminFXAccManagementServiceImplTest(String arg0)
    {
        super(arg0);
    }

    protected void setUp() throws Exception
    {
        super.setUp();
        l_impl = new WEB3AdminFXAccManagementServiceImpl();
        Services.overrideService(OpLoginSecurityService.class, new OpLoginSecurityServiceImplForMock());
        Services.overrideService(OpLoginAdminService.class, new OpLoginAdminServiceImplForMock());
    }

    protected void tearDown() throws Exception
    {
        super.tearDown();
    }

    public void testCreateQueryString_Case001()
    {
        final String STR_METHOD_NAME = "testCreateQueryString_Case001()";
        log.entering(STR_METHOD_NAME);
        try
        {
            String l_strSqlReturn = l_impl.createQueryString(
                "0D",
                "624",
                null,
                null,
                "07");
            String l_strSql = " institution_code = ? and branch_code = ?  and fx_system_code = ? ";
            assertEquals(l_strSqlReturn, l_strSql);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
    }

    public void testCreateQueryContainer_Case001()
    {
        final String STR_METHOD_NAME = "testCreateQueryString_Case001()";
        log.entering(STR_METHOD_NAME);
        try
        {
            String[] l_strSqlValue = l_impl.createQueryContainer(
                "0D",
                "624",
                null,
                null,
                "07");
            assertEquals("0D", l_strSqlValue[0]);
            assertEquals("624", l_strSqlValue[1]);
            assertNull(l_strSqlValue[2]);
            assertEquals("07", l_strSqlValue[3]);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
    }
    
    public void testCreateQueryContainer_Case002()
    {
        final String STR_METHOD_NAME = "testCreateQueryString_Case002()";
        log.entering(STR_METHOD_NAME);
        try
        {
            //FxAccountCodeParams
            TestDBUtility.deleteAll(FxAccountCodeParams.TYPE);
            FxAccountCodeParams l_fxAccountCodeParams =
                TestDBUtility.getFxAccountCodeRow();
            l_fxAccountCodeParams.setInstitutionCode("0D");
            l_fxAccountCodeParams.setBranchCode("624");
            l_fxAccountCodeParams.setAccountCode("789456");
            l_fxAccountCodeParams.setFxAccountCode("45");
            l_fxAccountCodeParams.setFxSystemCode("07");
            TestDBUtility.insertWithDel(l_fxAccountCodeParams);
            
            String[] l_strSqlValue = l_impl.createQueryContainer(
                "0D",
                "624",
                "2",
                "45",
                "07");
            
            assertEquals("0D", l_strSqlValue[0]);
            assertEquals("624", l_strSqlValue[1]);
            assertEquals("789456", l_strSqlValue[2]);
            assertEquals("07", l_strSqlValue[3]);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
    }
    
    public void testGetQueryResult_Case001()
    {
        final String STR_METHOD_NAME = "testGetQueryResult_Case001()";
        log.entering(STR_METHOD_NAME);
        try
        {
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            WEB3AdminFXAccInfoSearchRequest l_request =
                new WEB3AdminFXAccInfoSearchRequest();
            l_request.branchCode = "624";
            l_request.inputNumberDiv = "1";
            l_request.inputNumber = "123";
            
            //AdministratorParams
            l_queryProcessor.doDeleteAllQuery(AdministratorParams.TYPE);
            AdministratorParams l_administratorParams =
                TestDBUtility.getAdministratorRow();
            l_administratorParams.setInstitutionCode("0D");
            l_administratorParams.setBranchCode("624");
            l_queryProcessor.doInsertQuery(l_administratorParams);
            WEB3Administrator l_administrator = new WEB3Administrator(l_administratorParams);
            WEB3AdministratorForMock.mockGetInstanceFromLoginInfo(l_administrator);

            //AdminPermissionRow
            l_queryProcessor.doDeleteAllQuery(AdminPermissionRow.TYPE);
            AdminPermissionParams l_AdminPermissionParams =
                TestDBUtility.getAdminPermissionRow();
            l_AdminPermissionParams.setInstitutionCode(l_administrator.getInstitutionCode());
            l_AdminPermissionParams.setPermissionLevel(l_administrator.getPermissionLevel());
            l_AdminPermissionParams.setTransactionCategory("B0401");
            l_queryProcessor.doInsertQuery(l_AdminPermissionParams);
            
            //AdministratorTypeParams
            l_queryProcessor.doDeleteAllQuery(AdministratorTypeParams.TYPE);
            AdministratorTypeParams l_administratorTypeParams =
                TestDBUtility.getAdministratorTypeRow();
            l_administratorTypeParams.setInstitutionCode("0D");
            l_administratorTypeParams.setPermissionLevel(l_administratorParams.getPermissionLevel());
            l_administratorTypeParams.setAllBranchPermissionFlag(BooleanEnum.TRUE);
            l_queryProcessor.doInsertQuery(l_administratorTypeParams);

            //FxAccountParams
            l_queryProcessor.doDeleteAllQuery(FxAccountParams.TYPE);
            FxAccountParams l_fxAccountParams = TestDBUtility.getFxAccountRow();
            l_fxAccountParams.setInstitutionCode("0D");
            l_fxAccountParams.setBranchCode("624");
            l_fxAccountParams.setFxLoginId(123);
            l_fxAccountParams.setFxSystemCode("07");
            l_fxAccountParams.setAccountCode("123456");
            l_queryProcessor.doInsertQuery(l_fxAccountParams);
            
            //MainAccountParams
            l_queryProcessor.doDeleteAllQuery(MainAccountParams.TYPE);
            MainAccountParams l_MainAccountParams =
                TestDBUtility.getMainAccountRow();
            l_MainAccountParams.setInstitutionCode("0D");
            l_MainAccountParams.setBranchCode("624");
            l_MainAccountParams.setAccountCode("123456");
            l_queryProcessor.doInsertQuery(l_MainAccountParams);
            
            //FxAccountCodeParams
            l_queryProcessor.doDeleteAllQuery(FxAccountCodeParams.TYPE);
            FxAccountCodeParams l_fxAccountCodeParams =
                TestDBUtility.getFxAccountCodeRow();
            l_fxAccountCodeParams.setInstitutionCode("0D");
            l_fxAccountCodeParams.setBranchCode("624");
            l_fxAccountCodeParams.setAccountCode("123456");
            l_queryProcessor.doInsertQuery(l_fxAccountCodeParams);
            
            //CompFxConditionParams
            l_queryProcessor.doDeleteAllQuery(CompFxConditionParams.TYPE);
            CompFxConditionParams l_compFxConditionParams =
                TestDBUtility.getCompFxConditionRow();
            l_compFxConditionParams.setInstitutionCode("0D");
            l_compFxConditionParams.setBranchCode("624");           
            l_queryProcessor.doInsertQuery(l_compFxConditionParams);
            
            WEB3AdminFXAccInfoSearchResponse l_response= l_impl.getQueryResult(l_request);
            assertEquals("07", l_response.fxSystemCode);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
    }

    public void testgetUpdInputScreen_Case001()
    {
        final String STR_METHOD_NAME = "testgetUpdInputScreen_Case001()";
        log.entering(STR_METHOD_NAME);
        try
        {
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            WEB3AdminFXAccInfoChangeInputRequest l_request =
                new WEB3AdminFXAccInfoChangeInputRequest();
            l_request.branchCode = "624";
            l_request.accountCode = "123456";
            l_request.fxSystemCode = "07";
            
            //AdministratorParams
            l_queryProcessor.doDeleteAllQuery(AdministratorParams.TYPE);
            AdministratorParams l_administratorParams =
                TestDBUtility.getAdministratorRow();
            l_administratorParams.setInstitutionCode("0D");
            l_administratorParams.setBranchCode("624");
            l_queryProcessor.doInsertQuery(l_administratorParams);
            WEB3Administrator l_administrator = new WEB3Administrator(l_administratorParams);
            WEB3AdministratorForMock.mockGetInstanceFromLoginInfo(l_administrator);

            //AdminPermissionRow
            l_queryProcessor.doDeleteAllQuery(AdminPermissionRow.TYPE);
            AdminPermissionParams l_AdminPermissionParams =
                TestDBUtility.getAdminPermissionRow();
            l_AdminPermissionParams.setInstitutionCode(l_administrator.getInstitutionCode());
            l_AdminPermissionParams.setPermissionLevel(l_administrator.getPermissionLevel());
            l_AdminPermissionParams.setTransactionCategory("B0401");
            l_queryProcessor.doInsertQuery(l_AdminPermissionParams);
            
            //AdministratorTypeParams
            l_queryProcessor.doDeleteAllQuery(AdministratorTypeParams.TYPE);
            AdministratorTypeParams l_administratorTypeParams =
                TestDBUtility.getAdministratorTypeRow();
            l_administratorTypeParams.setInstitutionCode("0D");
            l_administratorTypeParams.setPermissionLevel(l_administratorParams.getPermissionLevel());
            l_administratorTypeParams.setAllBranchPermissionFlag(BooleanEnum.TRUE);
            l_queryProcessor.doInsertQuery(l_administratorTypeParams);

            //FxAccountParams
            l_queryProcessor.doDeleteAllQuery(FxAccountParams.TYPE);
            FxAccountParams l_fxAccountParams = TestDBUtility.getFxAccountRow();
            l_fxAccountParams.setInstitutionCode("0D");
            l_fxAccountParams.setBranchCode("624");
            l_fxAccountParams.setFxLoginId(123);
            l_fxAccountParams.setFxSystemCode("07");
            l_fxAccountParams.setAccountCode("123456");
            l_queryProcessor.doInsertQuery(l_fxAccountParams);
            
            //FxAccountCodeParams
            l_queryProcessor.doDeleteAllQuery(FxAccountCodeParams.TYPE);
            FxAccountCodeParams l_fxAccountCodeParams =
                TestDBUtility.getFxAccountCodeRow();
            l_fxAccountCodeParams.setInstitutionCode("0D");
            l_fxAccountCodeParams.setBranchCode("624");
            l_fxAccountCodeParams.setAccountCode("123456");
            l_fxAccountCodeParams.setFxSystemCode("07");
            l_queryProcessor.doInsertQuery(l_fxAccountCodeParams);
            
            //CompFxConditionParams
            l_queryProcessor.doDeleteAllQuery(CompFxConditionParams.TYPE);
            CompFxConditionParams l_compFxConditionParams =
                TestDBUtility.getCompFxConditionRow();
            l_compFxConditionParams.setInstitutionCode("0D");
            l_compFxConditionParams.setBranchCode("624");
            l_compFxConditionParams.setFxSystemCode("07");
            l_queryProcessor.doInsertQuery(l_compFxConditionParams);
            
            WEB3AdminFXAccInfoChangeInputResponse l_response = l_impl.getUpdInputScreen(l_request);
            assertNotNull(l_response.fxAccInformationList);
            
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }

    public void testSubmitChange_Case001()
    {
        final String STR_METHOD_NAME = "testSubmitChange_Case001()";
        log.entering(STR_METHOD_NAME);
        try
        {
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            WEB3AdminFXAccInfoChangeCompleteRequest l_request =
                new WEB3AdminFXAccInfoChangeCompleteRequest();
            l_request.branchCode = "624";
            l_request.accountCode = "123456";
            l_request.operationDiv = "1";
            l_request.fxSystemCode = "07";
            l_request.updatedAccountOpenStatusDiv = "9";
            l_request.updatedMailAddress = "jiddk@@126.com";

            WEB3FXAccInformationUnit[] l_updatedFxAccInformationList =
                new WEB3FXAccInformationUnit[1];
            l_updatedFxAccInformationList[0] = new WEB3FXAccInformationUnit();
            l_updatedFxAccInformationList[0].fxCourseDiv = "1";
            l_updatedFxAccInformationList[0].fxAccountCode = "987654";
            
            l_request.updatedFxAccInformationList = l_updatedFxAccInformationList;
            
            //AdministratorParams
            l_queryProcessor.doDeleteAllQuery(AdministratorParams.TYPE);
            AdministratorParams l_administratorParams =
                TestDBUtility.getAdministratorRow();
            l_administratorParams.setInstitutionCode("0D");
            l_administratorParams.setBranchCode("624");
            l_administratorParams.setAdministratorCode("321654");
            l_queryProcessor.doInsertQuery(l_administratorParams);
            WEB3Administrator l_administrator = new WEB3Administrator(l_administratorParams);
            WEB3AdministratorForMock.mockGetInstanceFromLoginInfo(l_administrator);

            //AdminPermissionRow
            l_queryProcessor.doDeleteAllQuery(AdminPermissionRow.TYPE);
            AdminPermissionParams l_AdminPermissionParams =
                TestDBUtility.getAdminPermissionRow();
            l_AdminPermissionParams.setInstitutionCode(l_administrator.getInstitutionCode());
            l_AdminPermissionParams.setPermissionLevel(l_administrator.getPermissionLevel());
            l_AdminPermissionParams.setTransactionCategory("B0401");
            l_queryProcessor.doInsertQuery(l_AdminPermissionParams);

            //AdministratorTypeParams
            l_queryProcessor.doDeleteAllQuery(AdministratorTypeParams.TYPE);
            AdministratorTypeParams l_administratorTypeParams =
                TestDBUtility.getAdministratorTypeRow();
            l_administratorTypeParams.setInstitutionCode("0D");
            l_administratorTypeParams.setPermissionLevel(l_administratorParams.getPermissionLevel());
            l_administratorTypeParams.setAllBranchPermissionFlag(BooleanEnum.TRUE);
            l_queryProcessor.doInsertQuery(l_administratorTypeParams);

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.LoginInfoImpl",
                "getLoginTypeId",
                new Class[] {},
                new Long(123456));
            
            HashMap l_map = new HashMap();
            l_map.put("TRADING_PWD_ENV", "0");
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginAdminServiceImpl", 
                "getLoginTypeAttributes",
                new Class[] {long.class},
                l_map);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl", 
                "checkPassword",
                new Class[] {String.class},
                Boolean.TRUE);

            //FxAccountParams
            l_queryProcessor.doDeleteAllQuery(FxAccountParams.TYPE);
            FxAccountParams l_fxAccountParams = TestDBUtility.getFxAccountRow();
            l_fxAccountParams.setInstitutionCode("0D");
            l_fxAccountParams.setBranchCode("624");
            l_fxAccountParams.setFxLoginId(123);
            l_fxAccountParams.setFxSystemCode("07");
            l_fxAccountParams.setAccountCode("123456");
            l_fxAccountParams.setFxMailAddress("123456789");
            l_queryProcessor.doInsertQuery(l_fxAccountParams);
            
            //FxAccountCodeParams
            l_queryProcessor.doDeleteAllQuery(FxAccountCodeParams.TYPE);
            FxAccountCodeParams l_fxAccountCodeParams =
                TestDBUtility.getFxAccountCodeRow();
            l_fxAccountCodeParams.setInstitutionCode("0D");
            l_fxAccountCodeParams.setBranchCode("624");
            l_fxAccountCodeParams.setAccountCode("123456");
            l_fxAccountCodeParams.setFxSystemCode("07");
            l_fxAccountCodeParams.setFxCourseDiv("1");
            l_queryProcessor.doInsertQuery(l_fxAccountCodeParams);

            //MainAccountParams
            l_queryProcessor.doDeleteAllQuery(MainAccountParams.TYPE);
            MainAccountParams l_mainAccountParams =
                TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setInstitutionCode("0D");
            l_mainAccountParams.setBranchCode("624");
            l_mainAccountParams.setAccountCode("123456");
            l_mainAccountParams.setFxAccOpenDivLastUpdater("11111");
            l_queryProcessor.doInsertQuery(l_mainAccountParams);
            
            //BranchParams
            l_queryProcessor.doDeleteAllQuery(BranchParams.TYPE);
            BranchParams l_branchParams = TestDBUtility.getBranchRow(); 
            l_branchParams.setBranchCode("624");
            l_branchParams.setBranchId(l_mainAccountParams.getBranchId());
            l_queryProcessor.doInsertQuery(l_branchParams);

            //BranchPreferencesParams
            l_queryProcessor.doDeleteAllQuery(BranchPreferencesParams.TYPE);
            BranchPreferencesParams l_branchPreferencesParams =
                TestDBUtility.getBranchPreferencesRow();
            l_branchPreferencesParams.setBranchId(l_mainAccountParams.getBranchId());
            l_branchPreferencesParams.setName("fx.account.div.non.update");
            l_branchPreferencesParams.setNameSerialNo(1);
            l_queryProcessor.doInsertQuery(l_branchPreferencesParams);
           
            //CompFxConditionParams
            l_queryProcessor.doDeleteAllQuery(CompFxConditionParams.TYPE);
            CompFxConditionParams l_compFxConditionParams =
                TestDBUtility.getCompFxConditionRow();
            l_compFxConditionParams.setInstitutionCode("0D");
            l_compFxConditionParams.setBranchCode("624");     
            l_compFxConditionParams.setFxSystemCode("07");
            l_compFxConditionParams.setExtConnectSystemCode("01");
            l_compFxConditionParams.setAccType("01");
            l_queryProcessor.doInsertQuery(l_compFxConditionParams);
            
            //InstitutionPreferencesParams
            l_queryProcessor.doDeleteAllQuery(InstitutionPreferencesParams.TYPE);
            InstitutionPreferencesParams l_institutionPreferencesParams =
                new InstitutionPreferencesParams();
            l_institutionPreferencesParams.setInstitutionId(33);
            l_institutionPreferencesParams.setName("fx.account.div.non.update");     
            l_institutionPreferencesParams.setNameSerialNo(1);
            l_institutionPreferencesParams.setValue("1");
            l_queryProcessor.doInsertQuery(l_institutionPreferencesParams);
            
            //AccOpenDivParams
            TestDBUtility.deleteAll(AccOpenDivRow.TYPE);
            AccOpenDivParams l_accOpenDivParams =
                TestDBUtility.getAccOpenDivRow();
            l_accOpenDivParams.setAccountId(l_mainAccountParams.getAccountId());
            l_accOpenDivParams.setAccType(l_compFxConditionParams.getAccType());
            l_queryProcessor.doInsertQuery(l_accOpenDivParams);
            
            l_impl.submitChange(l_request);
            List l_lisResult = l_queryProcessor.doFindAllQuery(FxAccountParams.TYPE);
            assertEquals(1, l_lisResult.size());
            FxAccountRow l_fxAccountRow =
                (FxAccountRow)l_lisResult.get(0);
            assertEquals(l_fxAccountRow.getFxMailAddress(), "jiddk@@126.com");
            assertEquals(l_fxAccountRow.getLastUpdater(), l_administrator.getAdministratorCode());
            
            List l_lisResult1 = l_queryProcessor.doFindAllQuery(MainAccountRow.TYPE);
            assertEquals(1, l_lisResult1.size());
            MainAccountRow l_mainAccountRow =
                (MainAccountRow)l_lisResult1.get(0);
            assertEquals("2", l_mainAccountRow.getFxAccOpenDiv());
            assertEquals(l_mainAccountRow.getFxAccOpenDiv(), "2");

            List l_lisResult2 = l_queryProcessor.doFindAllQuery(AccOpenDivParams.TYPE);
            assertEquals(1, l_lisResult2.size());
            AccOpenDivRow l_accOpenDivRow =
                (AccOpenDivRow)l_lisResult2.get(0);
            assertEquals(l_accOpenDivRow.getAccOpenDiv(), "2");
            assertEquals(l_accOpenDivRow.getLastUpdater(), "321654");
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }

    public void testSubmitChange_Case002()
    {
        final String STR_METHOD_NAME = "testSubmitChange_Case002()";
        log.entering(STR_METHOD_NAME);
        try
        {
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            WEB3AdminFXAccInfoChangeCompleteRequest l_request =
                new WEB3AdminFXAccInfoChangeCompleteRequest();
            l_request.branchCode = "624";
            l_request.accountCode = "123456";
            l_request.operationDiv = "1";
            l_request.fxSystemCode = "07";
            l_request.updatedAccountOpenStatusDiv = "9";
            l_request.updatedMailAddress = "jiddk@@126.com";

            WEB3FXAccInformationUnit[] l_updatedFxAccInformationList =
                new WEB3FXAccInformationUnit[1];
            l_updatedFxAccInformationList[0] = new WEB3FXAccInformationUnit();
            l_updatedFxAccInformationList[0].fxCourseDiv = "1";
            l_updatedFxAccInformationList[0].fxAccountCode = "987654";
            
            l_request.updatedFxAccInformationList = l_updatedFxAccInformationList;
            
            //AdministratorParams
            l_queryProcessor.doDeleteAllQuery(AdministratorParams.TYPE);
            AdministratorParams l_administratorParams =
                TestDBUtility.getAdministratorRow();
            l_administratorParams.setInstitutionCode("0D");
            l_administratorParams.setBranchCode("624");
            l_administratorParams.setAdministratorCode("321654");
            l_queryProcessor.doInsertQuery(l_administratorParams);
            WEB3Administrator l_administrator = new WEB3Administrator(l_administratorParams);
            WEB3AdministratorForMock.mockGetInstanceFromLoginInfo(l_administrator);

            //AdminPermissionRow
            l_queryProcessor.doDeleteAllQuery(AdminPermissionRow.TYPE);
            AdminPermissionParams l_AdminPermissionParams =
                TestDBUtility.getAdminPermissionRow();
            l_AdminPermissionParams.setInstitutionCode(l_administrator.getInstitutionCode());
            l_AdminPermissionParams.setPermissionLevel(l_administrator.getPermissionLevel());
            l_AdminPermissionParams.setTransactionCategory("B0401");
            l_queryProcessor.doInsertQuery(l_AdminPermissionParams);

            //AdministratorTypeParams
            l_queryProcessor.doDeleteAllQuery(AdministratorTypeParams.TYPE);
            AdministratorTypeParams l_administratorTypeParams =
                TestDBUtility.getAdministratorTypeRow();
            l_administratorTypeParams.setInstitutionCode("0D");
            l_administratorTypeParams.setPermissionLevel(l_administratorParams.getPermissionLevel());
            l_administratorTypeParams.setAllBranchPermissionFlag(BooleanEnum.TRUE);
            l_queryProcessor.doInsertQuery(l_administratorTypeParams);

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.LoginInfoImpl",
                "getLoginTypeId",
                new Class[] {},
                new Long(123456));
            
            HashMap l_map = new HashMap();
            l_map.put("TRADING_PWD_ENV", "0");
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginAdminServiceImpl", 
                "getLoginTypeAttributes",
                new Class[] {long.class},
                l_map);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl", 
                "checkPassword",
                new Class[] {String.class},
                Boolean.TRUE);

            //FxAccountParams
            l_queryProcessor.doDeleteAllQuery(FxAccountParams.TYPE);
            FxAccountParams l_fxAccountParams = TestDBUtility.getFxAccountRow();
            l_fxAccountParams.setInstitutionCode("0D");
            l_fxAccountParams.setBranchCode("624");
            l_fxAccountParams.setFxLoginId(123);
            l_fxAccountParams.setFxSystemCode("07");
            l_fxAccountParams.setAccountCode("123456");
            l_queryProcessor.doInsertQuery(l_fxAccountParams);
            
            //FxAccountCodeParams
            l_queryProcessor.doDeleteAllQuery(FxAccountCodeParams.TYPE);
            FxAccountCodeParams l_fxAccountCodeParams =
                TestDBUtility.getFxAccountCodeRow();
            l_fxAccountCodeParams.setInstitutionCode("0D");
            l_fxAccountCodeParams.setBranchCode("624");
            l_fxAccountCodeParams.setAccountCode("123456");
            l_fxAccountCodeParams.setFxSystemCode("07");
            l_fxAccountCodeParams.setFxCourseDiv("1");
            l_queryProcessor.doInsertQuery(l_fxAccountCodeParams);

            //MainAccountParams
            l_queryProcessor.doDeleteAllQuery(MainAccountParams.TYPE);
            MainAccountParams l_mainAccountParams =
                TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setInstitutionCode("0D");
            l_mainAccountParams.setBranchCode("624");
            l_mainAccountParams.setAccountCode("123456");
            l_mainAccountParams.setFxAccOpenDivLastUpdater("11111");
            l_queryProcessor.doInsertQuery(l_mainAccountParams);
            
            //BranchParams
            l_queryProcessor.doDeleteAllQuery(BranchParams.TYPE);
            BranchParams l_branchParams = TestDBUtility.getBranchRow(); 
            l_branchParams.setBranchCode("624");
            l_branchParams.setBranchId(l_mainAccountParams.getBranchId());
            l_queryProcessor.doInsertQuery(l_branchParams);

            //BranchPreferencesParams
            l_queryProcessor.doDeleteAllQuery(BranchPreferencesParams.TYPE);
           
            //CompFxConditionParams
            l_queryProcessor.doDeleteAllQuery(CompFxConditionParams.TYPE);
            CompFxConditionParams l_compFxConditionParams =
                TestDBUtility.getCompFxConditionRow();
            l_compFxConditionParams.setInstitutionCode("0D");
            l_compFxConditionParams.setBranchCode("624");     
            l_compFxConditionParams.setFxSystemCode("07");
            l_compFxConditionParams.setAccType("02");
            l_compFxConditionParams.setExtConnectSystemCode("02");
            l_queryProcessor.doInsertQuery(l_compFxConditionParams);
            
            //AccOpenDivParams
            TestDBUtility.deleteAll(AccOpenDivRow.TYPE);
            AccOpenDivParams l_accOpenDivParams =
                TestDBUtility.getAccOpenDivRow();
            l_accOpenDivParams.setAccountId(l_mainAccountParams.getAccountId());
            l_accOpenDivParams.setAccType(l_compFxConditionParams.getAccType());
            l_queryProcessor.doInsertQuery(l_accOpenDivParams);
            
            l_impl.submitChange(l_request);
            List l_lisResult = l_queryProcessor.doFindAllQuery(FxAccountParams.TYPE);
            assertEquals(1, l_lisResult.size());
            FxAccountRow l_fxAccountRow =
                (FxAccountRow)l_lisResult.get(0);
            assertEquals(l_fxAccountRow.getFxMailAddress(), "jiddk@@126.com");
            assertEquals(l_fxAccountRow.getLastUpdater(), "321654");
            
            List l_lisResult1 = l_queryProcessor.doFindAllQuery(MainAccountRow.TYPE);
            assertEquals(1, l_lisResult1.size());
            MainAccountRow l_mainAccountRow =
                (MainAccountRow)l_lisResult1.get(0);
            assertEquals(l_mainAccountParams.getFxAccOpenDiv(), l_mainAccountRow.getFxAccOpenDiv());

            List l_lisResult2 = l_queryProcessor.doFindAllQuery(AccOpenDivParams.TYPE);
            assertEquals(1, l_lisResult2.size());
            AccOpenDivRow l_accOpenDivRow =
                (AccOpenDivRow)l_lisResult2.get(0);
            assertEquals(l_accOpenDivRow.getAccOpenDiv(), "2");
            assertEquals(l_accOpenDivRow.getLastUpdater(), "321654");
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }

    public void testSubmitChange_Case003()
    {
        final String STR_METHOD_NAME = "testSubmitChange_Case003()";
        log.entering(STR_METHOD_NAME);
        try
        {
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            WEB3AdminFXAccInfoChangeCompleteRequest l_request =
                new WEB3AdminFXAccInfoChangeCompleteRequest();
            l_request.branchCode = "624";
            l_request.accountCode = "123456";
            l_request.operationDiv = "1";
            l_request.fxSystemCode = "07";
            l_request.updatedAccountOpenStatusDiv = "1";

            WEB3FXAccInformationUnit[] l_updatedFxAccInformationList =
                new WEB3FXAccInformationUnit[1];
            l_updatedFxAccInformationList[0] = new WEB3FXAccInformationUnit();
            l_updatedFxAccInformationList[0].fxCourseDiv = "1";
            l_updatedFxAccInformationList[0].fxAccountCode = "987654";
            
            l_request.updatedFxAccInformationList = l_updatedFxAccInformationList;
            
            //AdministratorParams
            l_queryProcessor.doDeleteAllQuery(AdministratorParams.TYPE);
            AdministratorParams l_administratorParams =
                TestDBUtility.getAdministratorRow();
            l_administratorParams.setInstitutionCode("0D");
            l_administratorParams.setBranchCode("624");
            l_administratorParams.setAdministratorCode("321654");
            l_queryProcessor.doInsertQuery(l_administratorParams);
            WEB3Administrator l_administrator = new WEB3Administrator(l_administratorParams);
            WEB3AdministratorForMock.mockGetInstanceFromLoginInfo(l_administrator);

            //AdminPermissionRow
            l_queryProcessor.doDeleteAllQuery(AdminPermissionRow.TYPE);
            AdminPermissionParams l_AdminPermissionParams =
                TestDBUtility.getAdminPermissionRow();
            l_AdminPermissionParams.setInstitutionCode(l_administrator.getInstitutionCode());
            l_AdminPermissionParams.setPermissionLevel(l_administrator.getPermissionLevel());
            l_AdminPermissionParams.setTransactionCategory("B0401");
            l_queryProcessor.doInsertQuery(l_AdminPermissionParams);

            //AdministratorTypeParams
            l_queryProcessor.doDeleteAllQuery(AdministratorTypeParams.TYPE);
            AdministratorTypeParams l_administratorTypeParams =
                TestDBUtility.getAdministratorTypeRow();
            l_administratorTypeParams.setInstitutionCode("0D");
            l_administratorTypeParams.setPermissionLevel(l_administratorParams.getPermissionLevel());
            l_administratorTypeParams.setAllBranchPermissionFlag(BooleanEnum.TRUE);
            l_queryProcessor.doInsertQuery(l_administratorTypeParams);

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.LoginInfoImpl",
                "getLoginTypeId",
                new Class[] {},
                new Long(123456));
            
            HashMap l_map = new HashMap();
            l_map.put("TRADING_PWD_ENV", "0");
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginAdminServiceImpl", 
                "getLoginTypeAttributes",
                new Class[] {long.class},
                l_map);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl", 
                "checkPassword",
                new Class[] {String.class},
                Boolean.TRUE);

            //FxAccountParams
            l_queryProcessor.doDeleteAllQuery(FxAccountParams.TYPE);
            FxAccountParams l_fxAccountParams = TestDBUtility.getFxAccountRow();
            l_fxAccountParams.setInstitutionCode("0D");
            l_fxAccountParams.setBranchCode("624");
            l_fxAccountParams.setFxLoginId(123);
            l_fxAccountParams.setFxSystemCode("07");
            l_fxAccountParams.setAccountCode("123456");
            l_queryProcessor.doInsertQuery(l_fxAccountParams);
            
            //FxAccountCodeParams
            l_queryProcessor.doDeleteAllQuery(FxAccountCodeParams.TYPE);
            FxAccountCodeParams l_fxAccountCodeParams =
                TestDBUtility.getFxAccountCodeRow();
            l_fxAccountCodeParams.setInstitutionCode("0D");
            l_fxAccountCodeParams.setBranchCode("624");
            l_fxAccountCodeParams.setAccountCode("123456");
            l_fxAccountCodeParams.setFxSystemCode("07");
            l_fxAccountCodeParams.setFxCourseDiv("1");
            l_queryProcessor.doInsertQuery(l_fxAccountCodeParams);

            //MainAccountParams
            l_queryProcessor.doDeleteAllQuery(MainAccountParams.TYPE);
            MainAccountParams l_mainAccountParams =
                TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setInstitutionCode("0D");
            l_mainAccountParams.setBranchCode("624");
            l_mainAccountParams.setAccountCode("123456");
            l_mainAccountParams.setFxAccOpenDivLastUpdater("11111");
            l_queryProcessor.doInsertQuery(l_mainAccountParams);
            
            //BranchParams
            l_queryProcessor.doDeleteAllQuery(BranchParams.TYPE);
            BranchParams l_branchParams = TestDBUtility.getBranchRow(); 
            l_branchParams.setBranchCode("624");
            l_branchParams.setBranchId(l_mainAccountParams.getBranchId());
            l_queryProcessor.doInsertQuery(l_branchParams);

            //BranchPreferencesParams
            l_queryProcessor.doDeleteAllQuery(BranchPreferencesParams.TYPE);
            BranchPreferencesParams l_branchPreferencesParams =
                TestDBUtility.getBranchPreferencesRow();
            l_branchPreferencesParams.setBranchId(l_mainAccountParams.getBranchId());
            l_branchPreferencesParams.setName("fx.account.div.non.update");
            l_branchPreferencesParams.setNameSerialNo(1);
            l_queryProcessor.doInsertQuery(l_branchPreferencesParams);
           
            //CompFxConditionParams
            l_queryProcessor.doDeleteAllQuery(CompFxConditionParams.TYPE);
            CompFxConditionParams l_compFxConditionParams =
                TestDBUtility.getCompFxConditionRow();
            l_compFxConditionParams.setInstitutionCode("0D");
            l_compFxConditionParams.setBranchCode("624");     
            l_compFxConditionParams.setFxSystemCode("07");
            l_compFxConditionParams.setFxSystemDiv(null);
            l_queryProcessor.doInsertQuery(l_compFxConditionParams);
            
            //InstitutionPreferencesParams
            l_queryProcessor.doDeleteAllQuery(InstitutionPreferencesParams.TYPE);
            InstitutionPreferencesParams l_institutionPreferencesParams =
                new InstitutionPreferencesParams();
            l_institutionPreferencesParams.setInstitutionId(33);
            l_institutionPreferencesParams.setName("fx.account.div.non.update");     
            l_institutionPreferencesParams.setNameSerialNo(1);
            l_institutionPreferencesParams.setValue("1");
            l_queryProcessor.doInsertQuery(l_institutionPreferencesParams);
            
            //AccOpenDivParams
            TestDBUtility.deleteAll(AccOpenDivRow.TYPE);
            AccOpenDivParams l_accOpenDivParams =
                TestDBUtility.getAccOpenDivRow();
            l_accOpenDivParams.setAccountId(l_mainAccountParams.getAccountId());
            l_accOpenDivParams.setAccType(l_compFxConditionParams.getAccType());
            l_queryProcessor.doInsertQuery(l_accOpenDivParams);
            
            l_impl.submitChange(l_request);
            List l_lisResult = l_queryProcessor.doFindAllQuery(MainAccountParams.TYPE);
                
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }

    public void testSubmitChange_Case004()
    {
        final String STR_METHOD_NAME = "testSubmitChange_Case004()";
        log.entering(STR_METHOD_NAME);
        try
        {
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            WEB3AdminFXAccInfoChangeCompleteRequest l_request =
                new WEB3AdminFXAccInfoChangeCompleteRequest();
            l_request.branchCode = "624";
            l_request.accountCode = "123456";
            l_request.operationDiv = "1";
            l_request.fxSystemCode = "07";
            l_request.updatedAccountOpenStatusDiv = "1";

            WEB3FXAccInformationUnit[] l_updatedFxAccInformationList =
                new WEB3FXAccInformationUnit[1];
            l_updatedFxAccInformationList[0] = new WEB3FXAccInformationUnit();
            l_updatedFxAccInformationList[0].fxCourseDiv = "1";
            l_updatedFxAccInformationList[0].fxAccountCode = "987654";
            
            l_request.updatedFxAccInformationList = l_updatedFxAccInformationList;

            //AdministratorParams
            l_queryProcessor.doDeleteAllQuery(AdministratorParams.TYPE);
            AdministratorParams l_administratorParams =
                TestDBUtility.getAdministratorRow();
            l_administratorParams.setInstitutionCode("0D");
            l_administratorParams.setBranchCode("624");
            l_administratorParams.setAdministratorCode("321654");
            l_queryProcessor.doInsertQuery(l_administratorParams);
            WEB3Administrator l_administrator = new WEB3Administrator(l_administratorParams);
            WEB3AdministratorForMock.mockGetInstanceFromLoginInfo(l_administrator);

            //AdminPermissionRow
            l_queryProcessor.doDeleteAllQuery(AdminPermissionRow.TYPE);
            AdminPermissionParams l_AdminPermissionParams =
                TestDBUtility.getAdminPermissionRow();
            l_AdminPermissionParams.setInstitutionCode(l_administrator.getInstitutionCode());
            l_AdminPermissionParams.setPermissionLevel(l_administrator.getPermissionLevel());
            l_AdminPermissionParams.setTransactionCategory("B0401");
            l_queryProcessor.doInsertQuery(l_AdminPermissionParams);

            //AdministratorTypeParams
            l_queryProcessor.doDeleteAllQuery(AdministratorTypeParams.TYPE);
            AdministratorTypeParams l_administratorTypeParams =
                TestDBUtility.getAdministratorTypeRow();
            l_administratorTypeParams.setInstitutionCode("0D");
            l_administratorTypeParams.setPermissionLevel(l_administratorParams.getPermissionLevel());
            l_administratorTypeParams.setAllBranchPermissionFlag(BooleanEnum.TRUE);
            l_queryProcessor.doInsertQuery(l_administratorTypeParams);

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.LoginInfoImpl",
                "getLoginTypeId",
                new Class[] {},
                new Long(123456));
            
            HashMap l_map = new HashMap();
            l_map.put("TRADING_PWD_ENV", "0");
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginAdminServiceImpl", 
                "getLoginTypeAttributes",
                new Class[] {long.class},
                l_map);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl", 
                "checkPassword",
                new Class[] {String.class},
                Boolean.TRUE);

            //FxAccountParams
            l_queryProcessor.doDeleteAllQuery(FxAccountParams.TYPE);
            FxAccountParams l_fxAccountParams = TestDBUtility.getFxAccountRow();
            l_fxAccountParams.setInstitutionCode("0D");
            l_fxAccountParams.setBranchCode("624");
            l_fxAccountParams.setFxLoginId(123);
            l_fxAccountParams.setFxSystemCode("07");
            l_fxAccountParams.setAccountCode("123456");
            l_queryProcessor.doInsertQuery(l_fxAccountParams);
            
            //FxAccountCodeParams
            l_queryProcessor.doDeleteAllQuery(FxAccountCodeParams.TYPE);
            FxAccountCodeParams l_fxAccountCodeParams =
                TestDBUtility.getFxAccountCodeRow();
            l_fxAccountCodeParams.setInstitutionCode("0D");
            l_fxAccountCodeParams.setBranchCode("624");
            l_fxAccountCodeParams.setAccountCode("123456");
            l_fxAccountCodeParams.setFxSystemCode("07");
            l_fxAccountCodeParams.setFxCourseDiv("1");
            l_queryProcessor.doInsertQuery(l_fxAccountCodeParams);

            //MainAccountParams
            l_queryProcessor.doDeleteAllQuery(MainAccountParams.TYPE);
            MainAccountParams l_mainAccountParams =
                TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setInstitutionCode("0D");
            l_mainAccountParams.setBranchCode("624");
            l_mainAccountParams.setAccountCode("123456");
            l_mainAccountParams.setFxAccOpenDivLastUpdater("11111");
            l_queryProcessor.doInsertQuery(l_mainAccountParams);
            
            //BranchParams
            l_queryProcessor.doDeleteAllQuery(BranchParams.TYPE);
            BranchParams l_branchParams = TestDBUtility.getBranchRow(); 
            l_branchParams.setBranchCode("624");
            l_branchParams.setBranchId(l_mainAccountParams.getBranchId());
            l_queryProcessor.doInsertQuery(l_branchParams);

            //BranchPreferencesParams
            l_queryProcessor.doDeleteAllQuery(BranchPreferencesParams.TYPE);
            BranchPreferencesParams l_branchPreferencesParams =
                TestDBUtility.getBranchPreferencesRow();
            l_branchPreferencesParams.setBranchId(l_mainAccountParams.getBranchId());
            l_branchPreferencesParams.setName("fx.account.div.non.update");
            l_branchPreferencesParams.setNameSerialNo(1);
            l_queryProcessor.doInsertQuery(l_branchPreferencesParams);
           
            //CompFxConditionParams
            l_queryProcessor.doDeleteAllQuery(CompFxConditionParams.TYPE);
            CompFxConditionParams l_compFxConditionParams =
                TestDBUtility.getCompFxConditionRow();
            l_compFxConditionParams.setInstitutionCode("0D");
            l_compFxConditionParams.setBranchCode("624");     
            l_compFxConditionParams.setFxSystemCode("07");
            l_compFxConditionParams.setFxSystemDiv("2");
            l_queryProcessor.doInsertQuery(l_compFxConditionParams);
            
            //InstitutionPreferencesParams
            l_queryProcessor.doDeleteAllQuery(InstitutionPreferencesParams.TYPE);
            InstitutionPreferencesParams l_institutionPreferencesParams =
                new InstitutionPreferencesParams();
            l_institutionPreferencesParams.setInstitutionId(33);
            l_institutionPreferencesParams.setName("fx.account.div.non.update");     
            l_institutionPreferencesParams.setNameSerialNo(1);
            l_institutionPreferencesParams.setValue("1");
            l_queryProcessor.doInsertQuery(l_institutionPreferencesParams);
            
            //AccOpenDivParams
            TestDBUtility.deleteAll(AccOpenDivRow.TYPE);
            AccOpenDivParams l_accOpenDivParams =
                TestDBUtility.getAccOpenDivRow();
            l_accOpenDivParams.setAccountId(l_mainAccountParams.getAccountId());
            l_accOpenDivParams.setAccType(l_compFxConditionParams.getAccType());
            l_queryProcessor.doInsertQuery(l_accOpenDivParams);
            
            l_impl.submitChange(l_request);
            List l_lisResult = l_queryProcessor.doFindAllQuery(MainAccountParams.TYPE);
                
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }

    public void testSubmitChange_Case005()
    {
        final String STR_METHOD_NAME = "testSubmitChange_Case005()";
        log.entering(STR_METHOD_NAME);
        try
        {
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            WEB3AdminFXAccInfoChangeCompleteRequest l_request =
                new WEB3AdminFXAccInfoChangeCompleteRequest();
            l_request.branchCode = "624";
            l_request.accountCode = "123456";
            l_request.operationDiv = "1";
            l_request.fxSystemCode = "07";
            l_request.updatedAccountOpenStatusDiv = "9";

            WEB3FXAccInformationUnit[] l_updatedFxAccInformationList =
                new WEB3FXAccInformationUnit[1];
            l_updatedFxAccInformationList[0] = new WEB3FXAccInformationUnit();
            l_updatedFxAccInformationList[0].fxCourseDiv = "1";
            l_updatedFxAccInformationList[0].fxAccountCode = "987654";
            
            l_request.updatedFxAccInformationList = l_updatedFxAccInformationList;
            
            //AdministratorParams
            l_queryProcessor.doDeleteAllQuery(AdministratorParams.TYPE);
            AdministratorParams l_administratorParams =
                TestDBUtility.getAdministratorRow();
            l_administratorParams.setInstitutionCode("0D");
            l_administratorParams.setBranchCode("624");
            l_administratorParams.setAdministratorCode("321654");
            l_queryProcessor.doInsertQuery(l_administratorParams);
            WEB3Administrator l_administrator = new WEB3Administrator(l_administratorParams);
            WEB3AdministratorForMock.mockGetInstanceFromLoginInfo(l_administrator);

            //AdminPermissionRow
            l_queryProcessor.doDeleteAllQuery(AdminPermissionRow.TYPE);
            AdminPermissionParams l_AdminPermissionParams =
                TestDBUtility.getAdminPermissionRow();
            l_AdminPermissionParams.setInstitutionCode(l_administrator.getInstitutionCode());
            l_AdminPermissionParams.setPermissionLevel(l_administrator.getPermissionLevel());
            l_AdminPermissionParams.setTransactionCategory("B0401");
            l_queryProcessor.doInsertQuery(l_AdminPermissionParams);

            //AdministratorTypeParams
            l_queryProcessor.doDeleteAllQuery(AdministratorTypeParams.TYPE);
            AdministratorTypeParams l_administratorTypeParams =
                TestDBUtility.getAdministratorTypeRow();
            l_administratorTypeParams.setInstitutionCode("0D");
            l_administratorTypeParams.setPermissionLevel(l_administratorParams.getPermissionLevel());
            l_administratorTypeParams.setAllBranchPermissionFlag(BooleanEnum.TRUE);
            l_queryProcessor.doInsertQuery(l_administratorTypeParams);

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.LoginInfoImpl",
                "getLoginTypeId",
                new Class[] {},
                new Long(123456));
            
            HashMap l_map = new HashMap();
            l_map.put("TRADING_PWD_ENV", "0");
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginAdminServiceImpl", 
                "getLoginTypeAttributes",
                new Class[] {long.class},
                l_map);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl", 
                "checkPassword",
                new Class[] {String.class},
                Boolean.TRUE);

            //FxAccountParams
            l_queryProcessor.doDeleteAllQuery(FxAccountParams.TYPE);
            FxAccountParams l_fxAccountParams = TestDBUtility.getFxAccountRow();
            l_fxAccountParams.setInstitutionCode("0D");
            l_fxAccountParams.setBranchCode("624");
            l_fxAccountParams.setFxLoginId(123);
            l_fxAccountParams.setFxSystemCode("07");
            l_fxAccountParams.setAccountCode("123456");
            l_queryProcessor.doInsertQuery(l_fxAccountParams);
            
            //FxAccountCodeParams
            l_queryProcessor.doDeleteAllQuery(FxAccountCodeParams.TYPE);
            FxAccountCodeParams l_fxAccountCodeParams =
                TestDBUtility.getFxAccountCodeRow();
            l_fxAccountCodeParams.setInstitutionCode("0D");
            l_fxAccountCodeParams.setBranchCode("624");
            l_fxAccountCodeParams.setAccountCode("123456");
            l_fxAccountCodeParams.setFxSystemCode("07");
            l_fxAccountCodeParams.setFxCourseDiv("1");
            l_queryProcessor.doInsertQuery(l_fxAccountCodeParams);

            //MainAccountParams
            l_queryProcessor.doDeleteAllQuery(MainAccountParams.TYPE);
            MainAccountParams l_mainAccountParams =
                TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setInstitutionCode("0D");
            l_mainAccountParams.setBranchCode("624");
            l_mainAccountParams.setAccountCode("123456");
            l_mainAccountParams.setFxAccOpenDivLastUpdater("11111");
            l_queryProcessor.doInsertQuery(l_mainAccountParams);
            
            //BranchParams
            l_queryProcessor.doDeleteAllQuery(BranchParams.TYPE);
            BranchParams l_branchParams = TestDBUtility.getBranchRow(); 
            l_branchParams.setBranchCode("624");
            l_branchParams.setBranchId(l_mainAccountParams.getBranchId());
            l_queryProcessor.doInsertQuery(l_branchParams);

            //BranchPreferencesParams
            l_queryProcessor.doDeleteAllQuery(BranchPreferencesParams.TYPE);
            BranchPreferencesParams l_branchPreferencesParams =
                TestDBUtility.getBranchPreferencesRow();
            l_branchPreferencesParams.setBranchId(l_mainAccountParams.getBranchId());
            l_branchPreferencesParams.setName("fx.account.div.non.update");
            l_branchPreferencesParams.setNameSerialNo(1);
            l_queryProcessor.doInsertQuery(l_branchPreferencesParams);
           
            //CompFxConditionParams
            l_queryProcessor.doDeleteAllQuery(CompFxConditionParams.TYPE);
            CompFxConditionParams l_compFxConditionParams =
                TestDBUtility.getCompFxConditionRow();
            l_compFxConditionParams.setInstitutionCode("0D");
            l_compFxConditionParams.setBranchCode("624");     
            l_compFxConditionParams.setFxSystemCode("07");
            l_compFxConditionParams.setFxSystemDiv(null);
            l_queryProcessor.doInsertQuery(l_compFxConditionParams);
            
            //InstitutionPreferencesParams
            l_queryProcessor.doDeleteAllQuery(InstitutionPreferencesParams.TYPE);
//            InstitutionPreferencesParams l_institutionPreferencesParams =
//                new InstitutionPreferencesParams();
//            l_institutionPreferencesParams.setInstitutionId(33);
//            l_institutionPreferencesParams.setName("fx.account.div.non.update");     
//            l_institutionPreferencesParams.setNameSerialNo(1);
//            l_institutionPreferencesParams.setValue("1");
//            l_queryProcessor.doInsertQuery(l_institutionPreferencesParams);
            
            //AccOpenDivParams
            TestDBUtility.deleteAll(AccOpenDivRow.TYPE);
            AccOpenDivParams l_accOpenDivParams =
                TestDBUtility.getAccOpenDivRow();
            l_accOpenDivParams.setAccountId(l_mainAccountParams.getAccountId());
            l_accOpenDivParams.setAccType(l_compFxConditionParams.getAccType());
            l_queryProcessor.doInsertQuery(l_accOpenDivParams);
            
            l_impl.submitChange(l_request);
            List l_lisResult = l_queryProcessor.doFindAllQuery(MainAccountParams.TYPE);
                
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }

    public void testSubmitChange_Case006()
    {
        final String STR_METHOD_NAME = "testSubmitChange_Case006()";
        log.entering(STR_METHOD_NAME);
        try
        {
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            WEB3AdminFXAccInfoChangeCompleteRequest l_request =
                new WEB3AdminFXAccInfoChangeCompleteRequest();
            l_request.branchCode = "624";
            l_request.accountCode = "123456";
            l_request.operationDiv = "1";
            l_request.fxSystemCode = "07";
            l_request.updatedAccountOpenStatusDiv = "9";

            WEB3FXAccInformationUnit[] l_updatedFxAccInformationList =
                new WEB3FXAccInformationUnit[1];
            l_updatedFxAccInformationList[0] = new WEB3FXAccInformationUnit();
            l_updatedFxAccInformationList[0].fxCourseDiv = "1";
            l_updatedFxAccInformationList[0].fxAccountCode = "987654";
            
            l_request.updatedFxAccInformationList = l_updatedFxAccInformationList;
            
            //AdministratorParams
            l_queryProcessor.doDeleteAllQuery(AdministratorParams.TYPE);
            AdministratorParams l_administratorParams =
                TestDBUtility.getAdministratorRow();
            l_administratorParams.setInstitutionCode("0D");
            l_administratorParams.setBranchCode("624");
            l_administratorParams.setAdministratorCode("321654");
            l_queryProcessor.doInsertQuery(l_administratorParams);
            WEB3Administrator l_administrator = new WEB3Administrator(l_administratorParams);
            WEB3AdministratorForMock.mockGetInstanceFromLoginInfo(l_administrator);

            //AdminPermissionRow
            l_queryProcessor.doDeleteAllQuery(AdminPermissionRow.TYPE);
            AdminPermissionParams l_AdminPermissionParams =
                TestDBUtility.getAdminPermissionRow();
            l_AdminPermissionParams.setInstitutionCode(l_administrator.getInstitutionCode());
            l_AdminPermissionParams.setPermissionLevel(l_administrator.getPermissionLevel());
            l_AdminPermissionParams.setTransactionCategory("B0401");
            l_queryProcessor.doInsertQuery(l_AdminPermissionParams);

            //AdministratorTypeParams
            l_queryProcessor.doDeleteAllQuery(AdministratorTypeParams.TYPE);
            AdministratorTypeParams l_administratorTypeParams =
                TestDBUtility.getAdministratorTypeRow();
            l_administratorTypeParams.setInstitutionCode("0D");
            l_administratorTypeParams.setPermissionLevel(l_administratorParams.getPermissionLevel());
            l_administratorTypeParams.setAllBranchPermissionFlag(BooleanEnum.TRUE);
            l_queryProcessor.doInsertQuery(l_administratorTypeParams);

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.LoginInfoImpl",
                "getLoginTypeId",
                new Class[] {},
                new Long(123456));
            
            HashMap l_map = new HashMap();
            l_map.put("TRADING_PWD_ENV", "0");
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginAdminServiceImpl", 
                "getLoginTypeAttributes",
                new Class[] {long.class},
                l_map);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl", 
                "checkPassword",
                new Class[] {String.class},
                Boolean.TRUE);

            //FxAccountParams
            l_queryProcessor.doDeleteAllQuery(FxAccountParams.TYPE);
            FxAccountParams l_fxAccountParams = TestDBUtility.getFxAccountRow();
            l_fxAccountParams.setInstitutionCode("0D");
            l_fxAccountParams.setBranchCode("624");
            l_fxAccountParams.setFxLoginId(123);
            l_fxAccountParams.setFxSystemCode("07");
            l_fxAccountParams.setAccountCode("123456");
            l_queryProcessor.doInsertQuery(l_fxAccountParams);
            
            //FxAccountCodeParams
            l_queryProcessor.doDeleteAllQuery(FxAccountCodeParams.TYPE);
            FxAccountCodeParams l_fxAccountCodeParams =
                TestDBUtility.getFxAccountCodeRow();
            l_fxAccountCodeParams.setInstitutionCode("0D");
            l_fxAccountCodeParams.setBranchCode("624");
            l_fxAccountCodeParams.setAccountCode("123456");
            l_fxAccountCodeParams.setFxSystemCode("07");
            l_fxAccountCodeParams.setFxCourseDiv("1");
            l_queryProcessor.doInsertQuery(l_fxAccountCodeParams);

            //MainAccountParams
            l_queryProcessor.doDeleteAllQuery(MainAccountParams.TYPE);
            MainAccountParams l_mainAccountParams =
                TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setInstitutionCode("0D");
            l_mainAccountParams.setBranchCode("624");
            l_mainAccountParams.setAccountCode("123456");
            l_mainAccountParams.setFxAccOpenDivLastUpdater("11111");
            l_queryProcessor.doInsertQuery(l_mainAccountParams);
            
            //BranchParams
            l_queryProcessor.doDeleteAllQuery(BranchParams.TYPE);
            BranchParams l_branchParams = TestDBUtility.getBranchRow(); 
            l_branchParams.setBranchCode("624");
            l_branchParams.setBranchId(l_mainAccountParams.getBranchId());
            l_queryProcessor.doInsertQuery(l_branchParams);

            //BranchPreferencesParams
            l_queryProcessor.doDeleteAllQuery(BranchPreferencesParams.TYPE);
            BranchPreferencesParams l_branchPreferencesParams =
                TestDBUtility.getBranchPreferencesRow();
            l_branchPreferencesParams.setBranchId(l_mainAccountParams.getBranchId());
            l_branchPreferencesParams.setName("fx.account.div.non.update");
            l_branchPreferencesParams.setNameSerialNo(1);
            l_queryProcessor.doInsertQuery(l_branchPreferencesParams);
           
            //CompFxConditionParams
            l_queryProcessor.doDeleteAllQuery(CompFxConditionParams.TYPE);
            CompFxConditionParams l_compFxConditionParams =
                TestDBUtility.getCompFxConditionRow();
            l_compFxConditionParams.setInstitutionCode("0D");
            l_compFxConditionParams.setBranchCode("624");     
            l_compFxConditionParams.setFxSystemCode("07");
            l_compFxConditionParams.setFxSystemDiv("2");
            l_queryProcessor.doInsertQuery(l_compFxConditionParams);
            
            //InstitutionPreferencesParams
            l_queryProcessor.doDeleteAllQuery(InstitutionPreferencesParams.TYPE);
//            InstitutionPreferencesParams l_institutionPreferencesParams =
//                new InstitutionPreferencesParams();
//            l_institutionPreferencesParams.setInstitutionId(33);
//            l_institutionPreferencesParams.setName("fx.account.div.non.update");     
//            l_institutionPreferencesParams.setNameSerialNo(1);
//            l_institutionPreferencesParams.setValue("1");
//            l_queryProcessor.doInsertQuery(l_institutionPreferencesParams);
            
            //AccOpenDivParams
            TestDBUtility.deleteAll(AccOpenDivRow.TYPE);
            AccOpenDivParams l_accOpenDivParams =
                TestDBUtility.getAccOpenDivRow();
            l_accOpenDivParams.setAccountId(l_mainAccountParams.getAccountId());
            l_accOpenDivParams.setAccType(l_compFxConditionParams.getAccType());
            l_queryProcessor.doInsertQuery(l_accOpenDivParams);
            
            l_impl.submitChange(l_request);
            List l_lisResult = l_queryProcessor.doFindAllQuery(MainAccountParams.TYPE);
                
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }

}
@
