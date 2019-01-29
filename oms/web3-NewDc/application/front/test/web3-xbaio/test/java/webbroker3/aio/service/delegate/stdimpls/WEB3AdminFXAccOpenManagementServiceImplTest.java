head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.01.36.19;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3AdminFXAccOpenManagementServiceImplTest.java;


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

import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.BranchParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.InstitutionParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountRow;

import test.util.TestDBUtility;

import webbroker3.aio.data.CompFxConditionParams;
import webbroker3.aio.data.FxAccountCodeParams;
import webbroker3.aio.data.FxAccountParams;
import webbroker3.aio.data.GftAccountOpenStatusParams;
import webbroker3.aio.data.GftAccountOpenStatusRow;
import webbroker3.aio.message.WEB3AdminFXAccOpenApplyDownloadRequest;
import webbroker3.aio.message.WEB3AdminFXAccOpenApplyDownloadResponse;
import webbroker3.aio.message.WEB3AdminFXAccOpenApplyListRequest;
import webbroker3.aio.message.WEB3AdminFXAccOpenApplyListResponse;
import webbroker3.aio.message.WEB3AdminFXAccOpenStatusUpdCompleteRequest;
import webbroker3.aio.message.WEB3FXAccInformationUnit;
import webbroker3.aio.message.WEB3FXSearchConditionUnit;
import webbroker3.common.define.WEB3QuestionDivDef;
import webbroker3.common.define.WEB3TransactionCategoryDef;
import webbroker3.gentrade.WEB3Administrator;
import webbroker3.gentrade.WEB3AdministratorForMock;
import webbroker3.gentrade.data.AccOpenDivRow;
import webbroker3.gentrade.data.AdminPermissionParams;
import webbroker3.gentrade.data.AdminPermissionRow;
import webbroker3.gentrade.data.AdministratorParams;
import webbroker3.gentrade.data.AdministratorTypeParams;
import webbroker3.gentrade.data.BranchPreferencesParams;
import webbroker3.gentrade.data.InstitutionPreferencesParams;
import webbroker3.gentrade.data.QuestionAnswerParams;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

public class WEB3AdminFXAccOpenManagementServiceImplTest extends TestBaseForMock
{
    /**
     * ログユーティリティ<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminFXAccOpenManagementServiceImpl.class);
    
    WEB3AdminFXAccOpenManagementServiceImpl l_impl;
    public WEB3AdminFXAccOpenManagementServiceImplTest(String arg0)
    {
        super(arg0);
    }

    protected void setUp() throws Exception
    {
        super.setUp();
        l_impl = new WEB3AdminFXAccOpenManagementServiceImpl();
        TestDBUtility.deleteAll(AccOpenDivRow.TYPE);
    }

    protected void tearDown() throws Exception
    {
        super.tearDown();
    }


    public void testSubmitStatusUpd_Case001()
    {
        final String STR_METHOD_NAME = "testSubmitStatusUpd_Case001()";
        log.entering(STR_METHOD_NAME);
        try
        {
            WEB3FXSearchConditionUnit l_fxSearchConditionUnit =
                new WEB3FXSearchConditionUnit();
            l_fxSearchConditionUnit.branchCode = "624";
            l_fxSearchConditionUnit.requestNumber = "123";

            WEB3FXAccInformationUnit[] l_fxAccInformationList =
                new WEB3FXAccInformationUnit[1];
            WEB3FXAccInformationUnit l_fxAccInformationUnit =
                new WEB3FXAccInformationUnit();
            l_fxAccInformationUnit.fxAccountCode = "111111";
            l_fxAccInformationUnit.fxCourseDiv = "1";
            l_fxAccInformationList[0] = l_fxAccInformationUnit;
            WEB3AdminFXAccOpenStatusUpdCompleteRequest l_request =
                new WEB3AdminFXAccOpenStatusUpdCompleteRequest();
            l_request.fxSearchConditionUnit = l_fxSearchConditionUnit;
            l_request.updatedStatusDiv = "1";
//            l_request.updatedAgreementDiv = "1";
            l_request.fxAccInformationList = l_fxAccInformationList;

            TestDBUtility.deleteAll(FxAccountCodeParams.TYPE);
            TestDBUtility.deleteAll(InstitutionParams.TYPE);
            InstitutionParams l_institutionParams =
                TestDBUtility.getInstitutionRow();
            l_institutionParams.setInstitutionId(33);
            l_institutionParams.setInstitutionCode("0D");
            TestDBUtility.insertWithDel(l_institutionParams);

            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();

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
            AdminPermissionParams l_adminPermissionParams =
                TestDBUtility.getAdminPermissionRow();
            l_adminPermissionParams.setInstitutionCode(l_administrator.getInstitutionCode());
            l_adminPermissionParams.setPermissionLevel(l_administrator.getPermissionLevel());
            l_adminPermissionParams.setTransactionCategory("B0401");
            l_queryProcessor.doInsertQuery(l_adminPermissionParams);

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
//            FxAccountParams l_fxAccountParams = TestDBUtility.getFxAccountRow();
//            l_fxAccountParams.setInstitutionCode("0D");
//            l_fxAccountParams.setBranchCode("624");
//            l_fxAccountParams.setFxLoginId(123);
//            l_fxAccountParams.setFxSystemCode("07");
//            l_fxAccountParams.setAccountCode("123456");
//            l_queryProcessor.doInsertQuery(l_fxAccountParams);

            //FxAccountCodeParams
            l_queryProcessor.doDeleteAllQuery(FxAccountCodeParams.TYPE);
            FxAccountCodeParams l_fxAccountCodeParams =
                TestDBUtility.getFxAccountCodeRow();
            l_fxAccountCodeParams.setInstitutionCode("0D");
            l_fxAccountCodeParams.setBranchCode("624");
            l_fxAccountCodeParams.setAccountCode("123456");
            l_fxAccountCodeParams.setFxSystemCode("01");
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
            l_compFxConditionParams.setAccType("01");
            l_compFxConditionParams.setAccOpenRealUpdate("0");
            l_compFxConditionParams.setGetTransferableAmtDiv("1");
            l_queryProcessor.doInsertQuery(l_compFxConditionParams);

            //InstitutionPreferencesParams
            l_queryProcessor.doDeleteAllQuery(InstitutionPreferencesParams.TYPE);
            InstitutionPreferencesParams l_institutionPreferencesParams =
                new InstitutionPreferencesParams();
//            l_institutionPreferencesParams.setInstitutionId(33);
//            l_institutionPreferencesParams.setName("fx.account.div.non.update");
//            l_institutionPreferencesParams.setNameSerialNo(1);
//            l_institutionPreferencesParams.setValue("1");
//            l_queryProcessor.doInsertQuery(l_institutionPreferencesParams);

            l_institutionPreferencesParams.setInstitutionId(33);
            l_institutionPreferencesParams.setName("gft.accountopen.fxsystemcode");
            l_institutionPreferencesParams.setNameSerialNo(1);
            l_institutionPreferencesParams.setValue("1");
            l_queryProcessor.doInsertQuery(l_institutionPreferencesParams);

            l_queryProcessor.doDeleteAllQuery(GftAccountOpenStatusParams.TYPE);
            GftAccountOpenStatusParams l_gftAccountOpenStatusParams =
                this.getGftAccountOpenStatusParams();
            l_gftAccountOpenStatusParams.setBranchCode("624");
            l_gftAccountOpenStatusParams.setOrderRequestNumber("123");
            l_gftAccountOpenStatusParams.setAccountOpenStatusDiv("0");
            l_gftAccountOpenStatusParams.setAgreementDiv("2");
            l_gftAccountOpenStatusParams.setFxSystemCode("07");
            l_gftAccountOpenStatusParams.setLoginId("1");
            l_queryProcessor.doInsertQuery(l_gftAccountOpenStatusParams);

            TestDBUtility.deleteAll(AccOpenDivRow.TYPE);
            l_queryProcessor.doDeleteAllQuery(FxAccountParams.TYPE);
            l_impl.submitStatusUpd(l_request);
            
            List l_lisResult1 = l_queryProcessor.doFindAllQuery(MainAccountRow.TYPE);
            assertEquals(l_lisResult1.size(), 1);
            
            MainAccountRow l_mainAccountRow = 
                (MainAccountRow)l_lisResult1.get(0);
            assertEquals(l_mainAccountRow.getFxAccOpenDiv(), "0");
            assertEquals(l_mainAccountRow.getFxAccOpenDivLastUpdater(),
                l_administrator.getAdministratorCode());
            
            List l_lisResult2 = l_queryProcessor.doFindAllQuery(AccOpenDivRow.TYPE);
            assertEquals(l_lisResult2.size(), 2);
            AccOpenDivRow l_accOpenDivRow1 =
                (AccOpenDivRow)l_lisResult2.get(0);
            assertEquals(l_accOpenDivRow1.getAccountId(), l_mainAccountParams.getAccountId());
            assertEquals(l_accOpenDivRow1.getAccType(), l_compFxConditionParams.getAccType());
            assertEquals(l_accOpenDivRow1.getAccOpenDiv(), "0");
            assertEquals(l_accOpenDivRow1.getLastUpdater(), l_administrator.getAdministratorCode());
            
            AccOpenDivRow l_accOpenDivRow2 =
                (AccOpenDivRow)l_lisResult2.get(1);
            assertEquals(l_accOpenDivRow2.getAccountId(), l_mainAccountParams.getAccountId());
            assertEquals(l_accOpenDivRow2.getAccType(), "02");
            assertEquals(l_accOpenDivRow2.getAccOpenDiv(), "0");
            assertEquals(l_accOpenDivRow2.getLastUpdater(), l_administrator.getAdministratorCode());

        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
    }

    public void testSubmitStatusUpd_Case002()
    {
        final String STR_METHOD_NAME = "testSubmitStatusUpd_Case002()";
        log.entering(STR_METHOD_NAME);
        try
        {
            WEB3FXSearchConditionUnit l_fxSearchConditionUnit =
                new WEB3FXSearchConditionUnit();
            l_fxSearchConditionUnit.branchCode = "624";
            l_fxSearchConditionUnit.requestNumber = "123";

            WEB3FXAccInformationUnit[] l_fxAccInformationList =
                new WEB3FXAccInformationUnit[1];
            WEB3FXAccInformationUnit l_fxAccInformationUnit =
                new WEB3FXAccInformationUnit();
            l_fxAccInformationUnit.fxAccountCode = "111111";
            l_fxAccInformationUnit.fxCourseDiv = "1";
            l_fxAccInformationList[0] = l_fxAccInformationUnit;
            WEB3AdminFXAccOpenStatusUpdCompleteRequest l_request =
                new WEB3AdminFXAccOpenStatusUpdCompleteRequest();
            l_request.fxSearchConditionUnit = l_fxSearchConditionUnit;
            l_request.updatedStatusDiv = "1";
//            l_request.updatedAgreementDiv = "1";
            l_request.fxAccInformationList = l_fxAccInformationList;

            TestDBUtility.deleteAll(FxAccountCodeParams.TYPE);
            TestDBUtility.deleteAll(InstitutionParams.TYPE);
            InstitutionParams l_institutionParams =
                TestDBUtility.getInstitutionRow();
            l_institutionParams.setInstitutionId(33);
            l_institutionParams.setInstitutionCode("0D");
            TestDBUtility.insertWithDel(l_institutionParams);

            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();

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
            AdminPermissionParams l_adminPermissionParams =
                TestDBUtility.getAdminPermissionRow();
            l_adminPermissionParams.setInstitutionCode(l_administrator.getInstitutionCode());
            l_adminPermissionParams.setPermissionLevel(l_administrator.getPermissionLevel());
            l_adminPermissionParams.setTransactionCategory("B0401");
            l_queryProcessor.doInsertQuery(l_adminPermissionParams);

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
//            FxAccountParams l_fxAccountParams = TestDBUtility.getFxAccountRow();
//            l_fxAccountParams.setInstitutionCode("0D");
//            l_fxAccountParams.setBranchCode("624");
//            l_fxAccountParams.setFxLoginId(123);
//            l_fxAccountParams.setFxSystemCode("07");
//            l_fxAccountParams.setAccountCode("123456");
//            l_queryProcessor.doInsertQuery(l_fxAccountParams);

            //FxAccountCodeParams
            l_queryProcessor.doDeleteAllQuery(FxAccountCodeParams.TYPE);
            FxAccountCodeParams l_fxAccountCodeParams =
                TestDBUtility.getFxAccountCodeRow();
            l_fxAccountCodeParams.setInstitutionCode("0D");
            l_fxAccountCodeParams.setBranchCode("624");
            l_fxAccountCodeParams.setAccountCode("123456");
            l_fxAccountCodeParams.setFxSystemCode("01");
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
            l_compFxConditionParams.setAccType("02");
            l_compFxConditionParams.setAccOpenRealUpdate("1");
            l_compFxConditionParams.setGetTransferableAmtDiv("1");
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

//            l_institutionPreferencesParams.setInstitutionId(33);
//            l_institutionPreferencesParams.setName("gft.accountopen.fxsystemcode");
//            l_institutionPreferencesParams.setNameSerialNo(1);
//            l_institutionPreferencesParams.setValue("1");
//            l_queryProcessor.doInsertQuery(l_institutionPreferencesParams);
//
            l_queryProcessor.doDeleteAllQuery(GftAccountOpenStatusParams.TYPE);
            GftAccountOpenStatusParams l_gftAccountOpenStatusParams =
                this.getGftAccountOpenStatusParams();
            l_gftAccountOpenStatusParams.setBranchCode("624");
            l_gftAccountOpenStatusParams.setOrderRequestNumber("123");
            l_gftAccountOpenStatusParams.setAccountOpenStatusDiv("0");
            l_gftAccountOpenStatusParams.setAgreementDiv("2");
            l_gftAccountOpenStatusParams.setFxSystemCode("07");
            l_gftAccountOpenStatusParams.setLoginId("1");
            l_queryProcessor.doInsertQuery(l_gftAccountOpenStatusParams);

            l_queryProcessor.doDeleteAllQuery(FxAccountParams.TYPE);
            l_impl.submitStatusUpd(l_request);
            
            List l_lisResult2 = l_queryProcessor.doFindAllQuery(AccOpenDivRow.TYPE);
            assertEquals(l_lisResult2.size(), 1);
            AccOpenDivRow l_accOpenDivRow1 =
                (AccOpenDivRow)l_lisResult2.get(0);
            assertEquals(l_accOpenDivRow1.getAccountId(), l_mainAccountParams.getAccountId());
            assertEquals(l_accOpenDivRow1.getAccType(), "02");
            assertEquals(l_accOpenDivRow1.getAccOpenDiv(), "1");
            assertEquals(l_accOpenDivRow1.getLastUpdater(), l_administrator.getAdministratorCode());

        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
    }

    public void testCreateQueryConditionList_Case001()
    {
        final String STR_METHOD_NAME = "testCreateQueryConditionList_Case001()";
        log.entering(STR_METHOD_NAME);
        try
        {
            String l_strSql = l_impl.createQueryConditionList(
                "0D",
                new String[]{"624"},
                null,
                null,
                null,
                null,
                null);
            assertEquals(" institution_code = ?  and branch_code in (?  ) ", l_strSql);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
    }

    public void testCreateQueryConditionList_Case002()
    {
        final String STR_METHOD_NAME = "testCreateQueryConditionList_Case002()";
        log.entering(STR_METHOD_NAME);
        try
        {
            String l_strSql = l_impl.createQueryConditionList(
                "0D",
                new String[]{"624", "381"},
                "1",
                null,
                null,
                null,
                null);
            assertEquals(" institution_code = ?  and branch_code in (? , ?  )  and account_open_status_div = ? ", l_strSql);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
    }

    public void testCreateQueryConditionList_Case003()
    {
        final String STR_METHOD_NAME = "testCreateQueryConditionList_Case003()";
        log.entering(STR_METHOD_NAME);
        try
        {
            String l_strSql = l_impl.createQueryConditionList(
                "0D",
                new String[]{"624"},
                "1",
                "2008021512",
                null,
                null,
                null);
            String l_strSqlRe = " institution_code = ?  and branch_code in (?  )  and account_open_status_div = ? " +
                " and to_char(created_timestamp, 'YYYYMMDDHH24') >= ? ";
            assertEquals(l_strSqlRe, l_strSql);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
    }
    
    public void testCreateQueryConditionList_Case004()
    {
        final String STR_METHOD_NAME = "testCreateQueryConditionList_Case004()";
        log.entering(STR_METHOD_NAME);
        try
        {
            String l_strSql = l_impl.createQueryConditionList(
                "0D",
                new String[]{"624"},
                "1",
                "2008021512",
                "2008051512",
                "2",
                null);
            String l_strSqlRe = " institution_code = ?  and branch_code in (?  )  and account_open_status_div = ? " +
                " and to_char(created_timestamp, 'YYYYMMDDHH24') >= ?  and to_char(created_timestamp, 'YYYYMMDDHH24') < ? " +
                " and agreement_div = ? ";
            assertEquals(l_strSqlRe, l_strSql);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
    }
    
    public void testCreateQueryConditionList_Case005()
    {
        final String STR_METHOD_NAME = "testCreateQueryConditionList_Case005()";
        log.entering(STR_METHOD_NAME);
        try
        {
            String l_strSql = l_impl.createQueryConditionList(
                "0D",
                new String[]{"624"},
                "1",
                "2008021512",
                "2008051512",
                "2",
                "07");
            String l_strSqlRe = " institution_code = ?  and branch_code in (?  )  and account_open_status_div = ? " +
                " and to_char(created_timestamp, 'YYYYMMDDHH24') >= ?  and to_char(created_timestamp, 'YYYYMMDDHH24') < ? " +
                " and agreement_div = ?  and fx_system_code = ? ";
            assertEquals(l_strSqlRe, l_strSql);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
    }
    
    public void testCreateQueryDataContainer_Case001()
    {
        final String STR_METHOD_NAME = "testCreateQueryDataContainer_Case001()";
        log.entering(STR_METHOD_NAME);
        try
        {
            String[] l_strValues = l_impl.createQueryDataContainer(
                "0D",
                new String[]{"624"},
                null,
                null,
                null,
                null,
                null);
            assertEquals(2, l_strValues.length);
            assertEquals("0D", l_strValues[0]);
            assertEquals("624", l_strValues[1]);            
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
    }
    
    public void testCreateQueryDataContainer_Case002()
    {
        final String STR_METHOD_NAME = "testCreateQueryDataContainer_Case002()";
        log.entering(STR_METHOD_NAME);
        try
        {
            String[] l_strValues = l_impl.createQueryDataContainer(
                "0D",
                new String[]{"624", "381"},
                "1",
                null,
                null,
                null,
                null);
            assertEquals(4, l_strValues.length);
            assertEquals("0D", l_strValues[0]);
            assertEquals("624", l_strValues[1]);
            assertEquals("381", l_strValues[2]);  
            assertEquals("1", l_strValues[3]);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
    }
    
    public void testCreateQueryDataContainer_Case003()
    {
        final String STR_METHOD_NAME = "testCreateQueryDataContainer_Case003()";
        log.entering(STR_METHOD_NAME);
        try
        {
            String[] l_strValues = l_impl.createQueryDataContainer(
                "0D",
                new String[]{"624", "381"},
                "1",
                "2008021512",
                null,
                null,
                null);
            assertEquals(5, l_strValues.length);
            assertEquals("0D", l_strValues[0]);
            assertEquals("624", l_strValues[1]);
            assertEquals("381", l_strValues[2]);  
            assertEquals("1", l_strValues[3]);
            assertEquals("2008021512", l_strValues[4]); 
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
    }
    
    public void testCreateQueryDataContainer_Case004()
    {
        final String STR_METHOD_NAME = "testCreateQueryDataContainer_Case004()";
        log.entering(STR_METHOD_NAME);
        try
        {
            String[] l_strValues = l_impl.createQueryDataContainer(
                "0D",
                new String[]{"624", "381"},
                "1",
                "2008021512",
                "2008051512",
                null,
                null);
            assertEquals(6, l_strValues.length);
            assertEquals("0D", l_strValues[0]);
            assertEquals("624", l_strValues[1]);
            assertEquals("381", l_strValues[2]);  
            assertEquals("1", l_strValues[3]);
            assertEquals("2008021512", l_strValues[4]);
            assertEquals("2008051512", l_strValues[5]);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
    }
    
    public void testCreateQueryDataContainer_Case005()
    {
        final String STR_METHOD_NAME = "testCreateQueryDataContainer_Case005()";
        log.entering(STR_METHOD_NAME);
        try
        {
            String[] l_strValues = l_impl.createQueryDataContainer(
                "0D",
                new String[]{"624", "381"},
                "1",
                "2008021512",
                "2008051512",
                "2",
                null);
            assertEquals(7, l_strValues.length);
            assertEquals("0D", l_strValues[0]);
            assertEquals("624", l_strValues[1]);
            assertEquals("381", l_strValues[2]);  
            assertEquals("1", l_strValues[3]);
            assertEquals("2008021512", l_strValues[4]);
            assertEquals("2008051512", l_strValues[5]);
            assertEquals("2", l_strValues[6]);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
    }
    
    public void testCreateQueryDataContainer_Case006()
    {
        final String STR_METHOD_NAME = "testCreateQueryDataContainer_Case006()";
        log.entering(STR_METHOD_NAME);
        try
        {
            String[] l_strValues = l_impl.createQueryDataContainer(
                "0D",
                new String[]{"624", "381"},
                "1",
                "2008021512",
                "2008051512",
                "2",
                "07");
            assertEquals(8, l_strValues.length);
            assertEquals("0D", l_strValues[0]);
            assertEquals("624", l_strValues[1]);
            assertEquals("381", l_strValues[2]);  
            assertEquals("1", l_strValues[3]);
            assertEquals("2008021512", l_strValues[4]);
            assertEquals("2008051512", l_strValues[5]);
            assertEquals("2", l_strValues[6]);
            assertEquals("07", l_strValues[7]);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
    }

    public void testGetListScreen_Case001()
    {
        final String STR_METHOD_NAME = "testGetListScreen_Case001()";
        log.entering(STR_METHOD_NAME);
        try
        {
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            WEB3AdminFXAccOpenApplyListRequest l_request =
                new WEB3AdminFXAccOpenApplyListRequest();
            l_request.branchCodeList = new String[]{"624"};
            l_request.statusDiv = "0";
            l_request.mrfAccountStatusDiv = "2";
            l_request.applyHourFrom = "20050212";
            l_request.applyHourTo = "20120202";
            l_request.pageIndex = "1";
            l_request.pageSize = "5";
            l_request.agreementDiv = "0";
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
            
            //GftAccountOpenStatusRow
            l_queryProcessor.doDeleteAllQuery(GftAccountOpenStatusParams.TYPE);
            GftAccountOpenStatusParams l_gftAccountOpenStatusParams =
                this.getGftAccountOpenStatusParams();
            l_gftAccountOpenStatusParams.setBranchCode("624");
            l_gftAccountOpenStatusParams.setAccountOpenStatusDiv("0");
            l_gftAccountOpenStatusParams.setAgreementDiv("0");
            l_gftAccountOpenStatusParams.setFxSystemCode("07");
            l_queryProcessor.doInsertQuery(l_gftAccountOpenStatusParams);

            //MainAccountParams
            l_queryProcessor.doDeleteAllQuery(MainAccountParams.TYPE);
            MainAccountParams l_mainAccountParams =
                TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setInstitutionCode("0D");
            l_mainAccountParams.setBranchCode("624");
            l_mainAccountParams.setAccountCode("123456");
            l_mainAccountParams.setMrfAccOpenDiv("0");
            l_queryProcessor.doInsertQuery(l_mainAccountParams);
            
            //QuestionAnswerParams
            l_queryProcessor.doDeleteAllQuery(QuestionAnswerParams.TYPE);
            QuestionAnswerParams l_questionAnswerParams =
                this.getQuestionAnswerParams();
            l_questionAnswerParams.setOrderRequestNumber("101");
            l_questionAnswerParams.setInstitutionCode("0D");
            l_questionAnswerParams.setBranchCode("624");
            l_questionAnswerParams.setQuestionDiv("07");
            l_questionAnswerParams.setOrderRequestNumber(
                l_gftAccountOpenStatusParams.getOrderRequestNumber());
            l_queryProcessor.doInsertQuery(l_questionAnswerParams);

            WEB3AdminFXAccOpenApplyListResponse l_response = l_impl.getListScreen(l_request);
            assertEquals("07",l_response.fxAccOpenApplyList[0].fxSystemCode);
            
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
         
        
    }
    
    public void testGetDownloadFile_Case001()
    {
        final String STR_METHOD_NAME = "testGetDownloadFile_Case001()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);

            WEB3AdminFXAccOpenApplyDownloadRequest l_request =
                new WEB3AdminFXAccOpenApplyDownloadRequest();
            l_request.branchCodeList = new String[]{"624"};
            l_request.statusDiv = "0";
            l_request.mrfAccountStatusDiv = "2";
            l_request.applyHourFrom = "20050212";
            l_request.applyHourTo = "20120202";
            l_request.pageIndex = "1";
            l_request.pageSize = "5";
            l_request.agreementDiv = "0";
            l_request.fxSystemCode = "07";

            TestDBUtility.deleteAll(AdministratorParams.TYPE);
            AdministratorParams l_administratorParams = TestDBUtility.getAdministratorRow();
            TestDBUtility.insertWithDel(l_administratorParams);
            WEB3Administrator l_administrator = new WEB3Administrator(l_administratorParams);

            WEB3AdministratorForMock.mockGetInstanceFromLoginInfo(l_administrator);

            WEB3AdministratorForMock.mockValidateAuthority(l_administrator,
                WEB3TransactionCategoryDef.FX_ACCOUNT_MANAGE,
                true,
                true);
            WEB3AdministratorForMock.mockValidateBranchPermission(l_administrator,l_request.branchCodeList,true);
            
            //GftAccountOpenStatusRow
            TestDBUtility.deleteAll(GftAccountOpenStatusRow.TYPE);
            GftAccountOpenStatusParams l_gftAccountOpenStatusParams1 =
                this.getGftAccountOpenStatusParams();
            l_gftAccountOpenStatusParams1.setInstitutionCode("0D");
            l_gftAccountOpenStatusParams1.setAccountOpenStatusDiv("0");
            l_gftAccountOpenStatusParams1.setCreatedTimestamp(WEB3DateUtility.getDate("20081012", "yyyyMMdd"));
            l_gftAccountOpenStatusParams1.setBranchCode("624");
            l_gftAccountOpenStatusParams1.setAgreementDiv("0");
            l_gftAccountOpenStatusParams1.setLastName("ddd");
            l_gftAccountOpenStatusParams1.setLoginId("123456");
            l_gftAccountOpenStatusParams1.setMailAddress("shandong");
            l_gftAccountOpenStatusParams1.setOrderRequestNumber("4");
            TestDBUtility.insertWithDel(l_gftAccountOpenStatusParams1);
            
            GftAccountOpenStatusParams l_gftAccountOpenStatusParams2 =
                this.getGftAccountOpenStatusParams();
            l_gftAccountOpenStatusParams2.setInstitutionCode("0D");
            l_gftAccountOpenStatusParams2.setAccountOpenStatusDiv("0");
            l_gftAccountOpenStatusParams2.setCreatedTimestamp(WEB3DateUtility.getDate("20080312", "yyyyMMdd"));
            l_gftAccountOpenStatusParams2.setBranchCode("624");
            l_gftAccountOpenStatusParams2.setAgreementDiv("0");
            l_gftAccountOpenStatusParams2.setLastName("yyy");
            l_gftAccountOpenStatusParams2.setLoginId("78787878");
            l_gftAccountOpenStatusParams2.setMailAddress("beijing");
            l_gftAccountOpenStatusParams2.setOrderRequestNumber("6");
            TestDBUtility.insertWithDel(l_gftAccountOpenStatusParams2);
            
            GftAccountOpenStatusParams l_gftAccountOpenStatusParams3 =
                this.getGftAccountOpenStatusParams();
            l_gftAccountOpenStatusParams3.setInstitutionCode("0D");
            l_gftAccountOpenStatusParams3.setAccountOpenStatusDiv("0");
            l_gftAccountOpenStatusParams3.setCreatedTimestamp(WEB3DateUtility.getDate("20080312", "yyyyMMdd"));
            l_gftAccountOpenStatusParams3.setBranchCode("381");
            l_gftAccountOpenStatusParams3.setAgreementDiv("0");
            l_gftAccountOpenStatusParams3.setLastName("yyy");
            l_gftAccountOpenStatusParams3.setLoginId("78787878");
            l_gftAccountOpenStatusParams3.setMailAddress("JINZHOU");
            l_gftAccountOpenStatusParams3.setOrderRequestNumber("7");
            TestDBUtility.insertWithDel(l_gftAccountOpenStatusParams3);
            
            WEB3AdminFXAccOpenApplyDownloadResponse l_response = l_impl.getDownloadFile(l_request);
            assertEquals(2,l_response.downloadFile.length);
            String l_strDownloadFile0 = "I,23456,ddd,23456,,,shandong,,A,A,2,01,01,1," +
                    "20081012,20081012,," +
                    "4,USD/JPY,300,EUR/JPY," +
                    "300,GBP/JPY,300,AUD/JPY," +
                    "300,CHF/JPY,300,CAD/JPY," +
                    "300,NZD/JPY,300,ZAR/JPY," +
                    "300,TRY/JPY,300,NOK/JPY," +
                    "300,HKD/JPY,300,SEK/JPY," +
                    "300,MXN/JPY,300,PLN/JPY,300," +
                    "EUR/USD,300,GBP/USD,300,GBP/CHF," +
                    "300,USD/CHF,300,USD/CAD," +
                    "300,AUD/USD,300,EUR/CHF,300," +
                    "EUR/GBP,300,NZD/USD,300," +
                    "EUR/AUD,300,GBP/AUD,300";
            log.debug("l_strDownloadFile0=====" + l_strDownloadFile0);
            log.debug("l_strDownloadFile0=====" + l_response.downloadFile[0]);
            assertEquals(l_strDownloadFile0,
                l_response.downloadFile[0]);
            String l_strDownloadFile1 =
                "I,8787878,yyy,8787878,,,beijing," +
                ",A,A,2,01,01,1,20080312,20080312," +
                ",6,USD/JPY,300,EUR/JPY,300,GBP/JPY," +
                "300,AUD/JPY,300,CHF/JPY,300,CAD/JPY," +
                "300,NZD/JPY,300,ZAR/JPY,300,TRY/JPY," +
                "300,NOK/JPY,300,HKD/JPY,300,SEK/JPY," +
                "300,MXN/JPY,300,PLN/JPY,300,EUR/USD," +
                "300,GBP/USD,300,GBP/CHF,300,USD/CHF," +
                "300,USD/CAD,300,AUD/USD,300,EUR/CHF,300" +
                ",EUR/GBP,300,NZD/USD,300,EUR/AUD,300,GBP/AUD,300";
            log.debug("l_strDownloadFile1=====" + l_strDownloadFile1);
            log.debug("l_strDownloadFile1=====" + l_response.downloadFile[1]);
            assertEquals(l_strDownloadFile1,
                l_response.downloadFile[1]);

        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
    }
       
    public void testCreateFXAccInfoListCase001()
    {
        final String STR_METHOD_NAME = "testCreateFXAccInfoListCase001()";
        log.entering(STR_METHOD_NAME);
        
        GftAccountOpenStatusParams l_GftAccountOpenStatusParams =
            this.getGftAccountOpenStatusParams();
        l_GftAccountOpenStatusParams.setFxAccountCode01("100001");
        l_GftAccountOpenStatusParams.setFxAccountCode10("100002");
        l_GftAccountOpenStatusParams.setExtAccountCode("100003");
        l_GftAccountOpenStatusParams.setExtAccountCode2("100004");
        l_GftAccountOpenStatusParams.setExtAccountCode3("100005");
        l_GftAccountOpenStatusParams.setExtAccountCode4("100006");
        l_GftAccountOpenStatusParams.setExtAccountCode5("100007");
        l_GftAccountOpenStatusParams.setExtAccountCode6("100008");
        l_GftAccountOpenStatusParams.setExtAccountCode7("100009");
        l_GftAccountOpenStatusParams.setExtAccountCode8("100010");
        
        WEB3AdminFXAccOpenManagementServiceImpl l_impl =
            new WEB3AdminFXAccOpenManagementServiceImpl();
        
        WEB3FXAccInformationUnit[] l_unit =
            l_impl.createFXAccInfoList(l_GftAccountOpenStatusParams);
        
        assertEquals(10, l_unit.length);
        
        log.exiting(STR_METHOD_NAME);
    }
    
    public GftAccountOpenStatusParams getGftAccountOpenStatusParams()
    {
        GftAccountOpenStatusParams l_params = new GftAccountOpenStatusParams();
        //証券会社コード    institution_code    VARCHAR2    3    NotNull
        l_params.setInstitutionCode("0D");
        //部店コード    branch_code    VARCHAR2    3    NotNull
        l_params.setBranchCode("624");
        //顧客コード    account_code    VARCHAR2    7    NotNull
        l_params.setAccountCode("123456");
        //識別コード    order_request_number    VARCHAR2    9    NotNull
        l_params.setOrderRequestNumber("101");
        //名前（姓）    last_name    VARCHAR2    50    Null
        //名前（名）    first_name    VARCHAR2    50    Null
        //メールアドレス    mail_address    VARCHAR2    50    Null
        //ログインID    login_id    VARCHAR2    18    Null
        //初期パスワード    password    VARCHAR2    8    Null
        //口座番号（1万通貨コース）    fx_account_code_01    VARCHAR2    6    Null
        //口座番号（10万通貨コース）    fx_account_code_10    VARCHAR2    6    Null
        //口座開設状況区分    account_open_status_div    VARCHAR2    2    NotNull
        l_params.setAccountOpenStatusDiv("1");
        //送受信区分    send_rcv_div    VARCHAR2    2    NotNull
        l_params.setSendRcvDiv("0");
        //受付結果コード    result_code    VARCHAR2    8    Null
        //エラー理由コード    error_reason_code    VARCHAR2    4    Null
        //更新者コード    last_updater    VARCHAR2    20    Null
        //作成日付    created_timestamp    DATE        NotNull
        l_params.setCreatedTimestamp(GtlUtils.getSystemTimestamp());
        //更新日付    last_updated_timestamp    DATE        NotNull
        l_params.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
        //約諾書区分    agreement_div    VARCHAR2    1    NotNull
        l_params.setAgreementDiv("1");
        //受付結果コード（SOAP）    result_code_soap    VARCHAR2    4    Null
        //ＦＸシステムコード    fx_system_code    VARCHAR2    2    Null
        return l_params;
    }
    
    protected QuestionAnswerParams getQuestionAnswerParams()
    {
        QuestionAnswerParams l_params = new QuestionAnswerParams();
        //証券会社コード    institution_code    VARCHAR2    3    NotNull
        l_params.setInstitutionCode("0D");
        //部店コード    branch_code    VARCHAR2    3    NotNull
        l_params.setBranchCode("624");
        //質問区分    question_div    VARCHAR2    4    NotNull
        l_params.setQuestionDiv("1001");
        //識別コード    order_request_number    VARCHAR2    9    NotNull
        l_params.setOrderRequestNumber("1234");
        //質問番号    question_no    VARCHAR2    3    NotNull
        l_params.setQuestionNo("101");
        //質問回答    question_answer    VARCHAR2    1    NotNull
        l_params.setQuestionAnswer("1");
        //更新者コード    last_updater    VARCHAR2    20    Null
        //作成日付    created_timestamp    DATE        NotNull
        l_params.setCreatedTimestamp(GtlUtils.getSystemTimestamp());
        //更新日付    last_updated_timestamp     DATE        NotNull
        l_params.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
        return l_params;
        
    }
}
@
