head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.02.10.08;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3AdminMutualCategoryRegistServiceImplTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.mf.service.delegate.stdimpls;

import com.fitechlabs.xtrade.plugin.security.oplogin.impl.LoginInfoImpl;
import com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.xbmf.data.MutualFundProductParams;

import test.util.TestDBUtility;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3TransactionCategoryDef;
import webbroker3.gentrade.WEB3AdministratorForMock;
import webbroker3.gentrade.data.AdminPermissionParams;
import webbroker3.gentrade.data.AdministratorParams;
import webbroker3.mf.data.MutualFundProductCategoryParams;
import webbroker3.mf.message.WEB3AdminMutualCategoryRegistCompleteRequest;
import webbroker3.mf.message.WEB3AdminMutualCategoryRegistConfirmRequest;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3LogUtility;

public class WEB3AdminMutualCategoryRegistServiceImplTest extends TestBaseForMock
{
    /**
     * ログユーティリティ<BR>
     */
    private static WEB3LogUtility log = 
        WEB3LogUtility.getInstance(WEB3AdminMutualCategoryRegistServiceImpl.class);

    public WEB3AdminMutualCategoryRegistServiceImplTest(String arg0)
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

    /*
     * Test method for 'webbroker3.mf.service.delegate.stdimpls.WEB3AdminMutualCategoryRegistServiceImpl.validateMutualProductCategoryRegistr(WEB3AdminMutualCategoryRegistConfirmRequest)'
     */
    public void testValidateMutualProductCategoryRegistrC0001()
    {
        final String STR_METHOD_NAME = "testValidateMutualProductCategoryRegistrC0001()";
        log.entering(STR_METHOD_NAME);

        WEB3AdminMutualCategoryRegistServiceImpl l_adminMutualCategoryRegistServiceImpl =
            new WEB3AdminMutualCategoryRegistServiceImpl();

        WEB3AdminMutualCategoryRegistConfirmRequest l_request =
            new WEB3AdminMutualCategoryRegistConfirmRequest();

        l_request.categoryCode = "2";
        l_request.categoryName = "002";
        l_request.parentCategoryCode = null;
        l_request.procedureDiv = "2";
        MOCK_MANAGER.setIsMockUsed(true);
        LoginInfoImpl l_loginInfoImpl = new LoginInfoImpl();
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
            "getLoginInfo",
            new Class[] {},
            l_loginInfoImpl);
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.gentrade.WEB3Administrator",
                "getInstitutionCode",
                new Class[] {},
                "0D");
        try
        {
            AdministratorParams l_administratorParams = TestDBUtility.getAdministratorRow();
            TestDBUtility.deleteAll(AdministratorParams.TYPE);
            l_administratorParams.setLoginId(0);
            l_administratorParams.setCreatedTimestamp(GtlUtils.getSystemTimestamp());
            TestDBUtility.insertWithDel(l_administratorParams);

            AdminPermissionParams l_adminPermissionParams = TestDBUtility.getAdminPermissionRow();
            l_adminPermissionParams.setInstitutionCode("0D");
            l_adminPermissionParams.setTransactionCategory(WEB3TransactionCategoryDef.ADMIN_MUTUAL_CONDITIONS);
            l_adminPermissionParams.setPermissionLevel(l_administratorParams.getPermissionLevel());
            l_adminPermissionParams.setUpdatePermissionFlag(BooleanEnum.TRUE);
            TestDBUtility.deleteAll(AdminPermissionParams.TYPE);
            TestDBUtility.insertWithDel(l_adminPermissionParams);

            MutualFundProductCategoryParams l_mutualFundProductCategoryParams =
                TestDBUtility.getMutualFundProductCategoryRow();
            TestDBUtility.deleteAll(MutualFundProductCategoryParams.TYPE);
            TestDBUtility.insertWithDel(l_mutualFundProductCategoryParams);

            l_adminMutualCategoryRegistServiceImpl.validateMutualProductCategoryRegistr(l_request);
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            assertEquals(l_ex.getErrorInfo(), WEB3ErrorCatalog.SYSTEM_ERROR_80006);
        }
        log.exiting(STR_METHOD_NAME);
    }

    public void testValidateMutualProductCategoryRegistrC0002()
    {
        final String STR_METHOD_NAME = "testValidateMutualProductCategoryRegistrC0002()";
        log.entering(STR_METHOD_NAME);

        WEB3AdminMutualCategoryRegistServiceImpl l_adminMutualCategoryRegistServiceImpl =
            new WEB3AdminMutualCategoryRegistServiceImpl();

        WEB3AdminMutualCategoryRegistConfirmRequest l_request =
            new WEB3AdminMutualCategoryRegistConfirmRequest();

        l_request.categoryCode = "0";
        l_request.categoryName = "002";
        l_request.parentCategoryCode = null;
        l_request.procedureDiv = "2";
        MOCK_MANAGER.setIsMockUsed(true);
        LoginInfoImpl l_loginInfoImpl = new LoginInfoImpl();
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
            "getLoginInfo",
            new Class[] {},
            l_loginInfoImpl);
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.gentrade.WEB3Administrator",
                "getInstitutionCode",
                new Class[] {},
                "0D");
        try
        {
            AdministratorParams l_administratorParams = TestDBUtility.getAdministratorRow();
            TestDBUtility.deleteAll(AdministratorParams.TYPE);
            l_administratorParams.setLoginId(0);
            l_administratorParams.setCreatedTimestamp(GtlUtils.getSystemTimestamp());
            TestDBUtility.insertWithDel(l_administratorParams);

            AdminPermissionParams l_adminPermissionParams = TestDBUtility.getAdminPermissionRow();
            l_adminPermissionParams.setInstitutionCode("0D");
            l_adminPermissionParams.setTransactionCategory(WEB3TransactionCategoryDef.ADMIN_MUTUAL_CONDITIONS);
            l_adminPermissionParams.setPermissionLevel(l_administratorParams.getPermissionLevel());
            l_adminPermissionParams.setUpdatePermissionFlag(BooleanEnum.TRUE);
            TestDBUtility.deleteAll(AdminPermissionParams.TYPE);
            TestDBUtility.insertWithDel(l_adminPermissionParams);

            MutualFundProductCategoryParams l_mutualFundProductCategoryParams =
                TestDBUtility.getMutualFundProductCategoryRow();
            TestDBUtility.deleteAll(MutualFundProductCategoryParams.TYPE);
            TestDBUtility.insertWithDel(l_mutualFundProductCategoryParams);

            l_mutualFundProductCategoryParams.setCategoryCode("01");
            l_mutualFundProductCategoryParams.setCategoryName("012");
            l_mutualFundProductCategoryParams.setInstitutionCode("0D");
            l_mutualFundProductCategoryParams.setParentCategoryCode("01");
            l_mutualFundProductCategoryParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
            l_mutualFundProductCategoryParams.setLastUpdater("132");
            l_mutualFundProductCategoryParams.setCreatedTimestamp(GtlUtils.getSystemTimestamp());
            TestDBUtility.insertWithDel(l_mutualFundProductCategoryParams);

            TestDBUtility.deleteAll(MutualFundProductParams.TYPE);
            MutualFundProductParams l_mutualFundProductParams = TestDBUtility.getMutualFundProductRow();
            l_mutualFundProductParams.setInstitutionCode("0D");
            l_mutualFundProductParams.setCategoryCode("0");
            TestDBUtility.insertWithDel(l_mutualFundProductParams);
            l_adminMutualCategoryRegistServiceImpl.validateMutualProductCategoryRegistr(l_request);
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            assertEquals(l_ex.getErrorInfo(),WEB3ErrorCatalog.BUSINESS_ERROR_03081);
        }
        log.exiting(STR_METHOD_NAME);
    }

    public void testValidateMutualProductCategoryRegistrC0003()
    {
        final String STR_METHOD_NAME = "testValidateMutualProductCategoryRegistrC0003()";
        log.entering(STR_METHOD_NAME);


        WEB3AdminMutualCategoryRegistServiceImpl l_adminMutualCategoryRegistServiceImpl =
            new WEB3AdminMutualCategoryRegistServiceImpl();

        WEB3AdminMutualCategoryRegistConfirmRequest l_request =
            new WEB3AdminMutualCategoryRegistConfirmRequest();

        l_request.categoryCode = "0";
        l_request.categoryName = "002";
        l_request.parentCategoryCode = null;
        l_request.procedureDiv = "2";
        MOCK_MANAGER.setIsMockUsed(true);
        LoginInfoImpl l_loginInfoImpl = new LoginInfoImpl();
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
            "getLoginInfo",
            new Class[] {},
            l_loginInfoImpl);
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.gentrade.WEB3Administrator",
                "getInstitutionCode",
                new Class[] {},
                "0D");
        try
        {
            AdministratorParams l_administratorParams = TestDBUtility.getAdministratorRow();
            TestDBUtility.deleteAll(AdministratorParams.TYPE);
            l_administratorParams.setLoginId(0);
            l_administratorParams.setCreatedTimestamp(GtlUtils.getSystemTimestamp());
            TestDBUtility.insertWithDel(l_administratorParams);

            AdminPermissionParams l_adminPermissionParams = TestDBUtility.getAdminPermissionRow();
            l_adminPermissionParams.setInstitutionCode("0D");
            l_adminPermissionParams.setTransactionCategory(WEB3TransactionCategoryDef.ADMIN_MUTUAL_CONDITIONS);
            l_adminPermissionParams.setPermissionLevel(l_administratorParams.getPermissionLevel());
            l_adminPermissionParams.setUpdatePermissionFlag(BooleanEnum.TRUE);
            TestDBUtility.deleteAll(AdminPermissionParams.TYPE);
            TestDBUtility.insertWithDel(l_adminPermissionParams);

            MutualFundProductCategoryParams l_mutualFundProductCategoryParams =
                TestDBUtility.getMutualFundProductCategoryRow();
            TestDBUtility.deleteAll(MutualFundProductCategoryParams.TYPE);
            l_mutualFundProductCategoryParams.setCategoryCode("0");
            l_mutualFundProductCategoryParams.setCategoryName("012");
            l_mutualFundProductCategoryParams.setInstitutionCode("0D");
            l_mutualFundProductCategoryParams.setParentCategoryCode("01");
            l_mutualFundProductCategoryParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
            l_mutualFundProductCategoryParams.setLastUpdater("132");
            l_mutualFundProductCategoryParams.setCreatedTimestamp(GtlUtils.getSystemTimestamp());
            TestDBUtility.insertWithDel(l_mutualFundProductCategoryParams);

            l_mutualFundProductCategoryParams.setCategoryCode("01");
            l_mutualFundProductCategoryParams.setCategoryName("012");
            l_mutualFundProductCategoryParams.setInstitutionCode("0D");
            l_mutualFundProductCategoryParams.setParentCategoryCode("01");
            l_mutualFundProductCategoryParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
            l_mutualFundProductCategoryParams.setLastUpdater("132");
            l_mutualFundProductCategoryParams.setCreatedTimestamp(GtlUtils.getSystemTimestamp());
            TestDBUtility.insertWithDel(l_mutualFundProductCategoryParams);

            l_mutualFundProductCategoryParams.setCategoryCode("01");
            l_mutualFundProductCategoryParams.setCategoryName("012");
            l_mutualFundProductCategoryParams.setInstitutionCode("0D");
            l_mutualFundProductCategoryParams.setParentCategoryCode("0");
            l_mutualFundProductCategoryParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
            l_mutualFundProductCategoryParams.setLastUpdater("132");
            l_mutualFundProductCategoryParams.setCreatedTimestamp(GtlUtils.getSystemTimestamp());
            TestDBUtility.insertWithDel(l_mutualFundProductCategoryParams);

            l_mutualFundProductCategoryParams.setCategoryCode("0");
            l_mutualFundProductCategoryParams.setCategoryName("012");
            l_mutualFundProductCategoryParams.setInstitutionCode("0D");
            l_mutualFundProductCategoryParams.setParentCategoryCode("0");
            l_mutualFundProductCategoryParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
            l_mutualFundProductCategoryParams.setLastUpdater("132");
            l_mutualFundProductCategoryParams.setCreatedTimestamp(GtlUtils.getSystemTimestamp());
            TestDBUtility.insertWithDel(l_mutualFundProductCategoryParams);

            TestDBUtility.deleteAll(MutualFundProductParams.TYPE);

            l_adminMutualCategoryRegistServiceImpl.validateMutualProductCategoryRegistr(l_request);
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
//            assertEquals(l_ex.getErrorInfo(),WEB3ErrorCatalog.BUSINESS_ERROR_03081);
        }
        log.exiting(STR_METHOD_NAME);
    }

    public void testValidateMutualProductCategoryRegistrC0004()
    {
        final String STR_METHOD_NAME = "testValidateMutualProductCategoryRegistrC0002()";
        log.entering(STR_METHOD_NAME);

        WEB3AdminMutualCategoryRegistServiceImpl l_adminMutualCategoryRegistServiceImpl =
            new WEB3AdminMutualCategoryRegistServiceImpl();

        WEB3AdminMutualCategoryRegistConfirmRequest l_request =
            new WEB3AdminMutualCategoryRegistConfirmRequest();

        l_request.categoryCode = "0";
        l_request.categoryName = "002";
        l_request.parentCategoryCode = null;
        l_request.procedureDiv = "2";
        MOCK_MANAGER.setIsMockUsed(true);
        LoginInfoImpl l_loginInfoImpl = new LoginInfoImpl();
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
            "getLoginInfo",
            new Class[] {},
            l_loginInfoImpl);
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.gentrade.WEB3Administrator",
                "getInstitutionCode",
                new Class[] {},
                "0D");
        try
        {
            AdministratorParams l_administratorParams = TestDBUtility.getAdministratorRow();
            TestDBUtility.deleteAll(AdministratorParams.TYPE);
            l_administratorParams.setLoginId(0);
            l_administratorParams.setCreatedTimestamp(GtlUtils.getSystemTimestamp());
            TestDBUtility.insertWithDel(l_administratorParams);

            AdminPermissionParams l_adminPermissionParams = TestDBUtility.getAdminPermissionRow();
            l_adminPermissionParams.setInstitutionCode("0D");
            l_adminPermissionParams.setTransactionCategory(WEB3TransactionCategoryDef.ADMIN_MUTUAL_CONDITIONS);
            l_adminPermissionParams.setPermissionLevel(l_administratorParams.getPermissionLevel());
            l_adminPermissionParams.setUpdatePermissionFlag(BooleanEnum.TRUE);
            TestDBUtility.deleteAll(AdminPermissionParams.TYPE);
            TestDBUtility.insertWithDel(l_adminPermissionParams);

            MutualFundProductCategoryParams l_mutualFundProductCategoryParams =
                TestDBUtility.getMutualFundProductCategoryRow();
            TestDBUtility.deleteAll(MutualFundProductCategoryParams.TYPE);
            TestDBUtility.insertWithDel(l_mutualFundProductCategoryParams);

            l_mutualFundProductCategoryParams.setCategoryCode("01");
            l_mutualFundProductCategoryParams.setCategoryName("012");
            l_mutualFundProductCategoryParams.setInstitutionCode("0D");
            l_mutualFundProductCategoryParams.setParentCategoryCode("01");
            l_mutualFundProductCategoryParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
            l_mutualFundProductCategoryParams.setLastUpdater("132");
            l_mutualFundProductCategoryParams.setCreatedTimestamp(GtlUtils.getSystemTimestamp());
            TestDBUtility.insertWithDel(l_mutualFundProductCategoryParams);

            l_adminMutualCategoryRegistServiceImpl.validateMutualProductCategoryRegistr(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }

    /*
     * Test method for 'webbroker3.mf.service.delegate.stdimpls.WEB3AdminMutualCategoryRegistServiceImpl.validateMutualProductCategoryRegistr(WEB3AdminMutualCategoryRegistConfirmRequest)'
     */
    public void testsubmitMutualProductCategoryRegistrC0001()
    {
        final String STR_METHOD_NAME = "testsubmitMutualProductCategoryRegistrC0001()";
        log.entering(STR_METHOD_NAME);

        WEB3AdminMutualCategoryRegistServiceImpl l_adminMutualCategoryRegistServiceImpl =
            new WEB3AdminMutualCategoryRegistServiceImpl();

        WEB3AdminMutualCategoryRegistCompleteRequest l_request =
            new WEB3AdminMutualCategoryRegistCompleteRequest();

        l_request.categoryCode = "2";
        l_request.categoryName = "002";
        l_request.parentCategoryCode = null;
        l_request.procedureDiv = "2";
        MOCK_MANAGER.setIsMockUsed(true);
        LoginInfoImpl l_loginInfoImpl = new LoginInfoImpl();
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
            "getLoginInfo",
            new Class[] {},
            l_loginInfoImpl);
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.gentrade.WEB3Administrator",
                "getInstitutionCode",
                new Class[] {},
                "0D");
        WEB3AdministratorForMock.mockValidateTradingPassword("123", true);
        try
        {
            AdministratorParams l_administratorParams = TestDBUtility.getAdministratorRow();
            TestDBUtility.deleteAll(AdministratorParams.TYPE);
            l_administratorParams.setLoginId(0);
            l_administratorParams.setCreatedTimestamp(GtlUtils.getSystemTimestamp());
            TestDBUtility.insertWithDel(l_administratorParams);

            AdminPermissionParams l_adminPermissionParams = TestDBUtility.getAdminPermissionRow();
            l_adminPermissionParams.setInstitutionCode("0D");
            l_adminPermissionParams.setTransactionCategory(WEB3TransactionCategoryDef.ADMIN_MUTUAL_CONDITIONS);
            l_adminPermissionParams.setPermissionLevel(l_administratorParams.getPermissionLevel());
            l_adminPermissionParams.setUpdatePermissionFlag(BooleanEnum.TRUE);
            TestDBUtility.deleteAll(AdminPermissionParams.TYPE);
            TestDBUtility.insertWithDel(l_adminPermissionParams);

            MutualFundProductCategoryParams l_mutualFundProductCategoryParams =
                TestDBUtility.getMutualFundProductCategoryRow();
            TestDBUtility.deleteAll(MutualFundProductCategoryParams.TYPE);
            TestDBUtility.insertWithDel(l_mutualFundProductCategoryParams);

            l_adminMutualCategoryRegistServiceImpl.submitMutualProductCategoryRegistr(l_request);
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            assertEquals(l_ex.getErrorInfo(), WEB3ErrorCatalog.SYSTEM_ERROR_80006);
        }
        log.exiting(STR_METHOD_NAME);
    }

    public void testsubmitMutualProductCategoryRegistrC0002()
    {
        final String STR_METHOD_NAME = "testsubmitMutualProductCategoryRegistrC0002()";
        log.entering(STR_METHOD_NAME);

        WEB3AdminMutualCategoryRegistServiceImpl l_adminMutualCategoryRegistServiceImpl =
            new WEB3AdminMutualCategoryRegistServiceImpl();

        WEB3AdminMutualCategoryRegistCompleteRequest l_request =
            new WEB3AdminMutualCategoryRegistCompleteRequest();

        l_request.categoryCode = "0";
        l_request.categoryName = "002";
        l_request.parentCategoryCode = null;
        l_request.procedureDiv = "2";
        MOCK_MANAGER.setIsMockUsed(true);
        LoginInfoImpl l_loginInfoImpl = new LoginInfoImpl();
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
            "getLoginInfo",
            new Class[] {},
            l_loginInfoImpl);
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.gentrade.WEB3Administrator",
                "getInstitutionCode",
                new Class[] {},
                "0D");
        WEB3AdministratorForMock.mockValidateTradingPassword("123", true);
        try
        {
            AdministratorParams l_administratorParams = TestDBUtility.getAdministratorRow();
            TestDBUtility.deleteAll(AdministratorParams.TYPE);
            l_administratorParams.setLoginId(0);
            l_administratorParams.setCreatedTimestamp(GtlUtils.getSystemTimestamp());
            TestDBUtility.insertWithDel(l_administratorParams);

            AdminPermissionParams l_adminPermissionParams = TestDBUtility.getAdminPermissionRow();
            l_adminPermissionParams.setInstitutionCode("0D");
            l_adminPermissionParams.setTransactionCategory(WEB3TransactionCategoryDef.ADMIN_MUTUAL_CONDITIONS);
            l_adminPermissionParams.setPermissionLevel(l_administratorParams.getPermissionLevel());
            l_adminPermissionParams.setUpdatePermissionFlag(BooleanEnum.TRUE);
            TestDBUtility.deleteAll(AdminPermissionParams.TYPE);
            TestDBUtility.insertWithDel(l_adminPermissionParams);

            MutualFundProductCategoryParams l_mutualFundProductCategoryParams =
                TestDBUtility.getMutualFundProductCategoryRow();
            TestDBUtility.deleteAll(MutualFundProductCategoryParams.TYPE);
            TestDBUtility.insertWithDel(l_mutualFundProductCategoryParams);

            l_mutualFundProductCategoryParams.setCategoryCode("01");
            l_mutualFundProductCategoryParams.setCategoryName("012");
            l_mutualFundProductCategoryParams.setInstitutionCode("0D");
            l_mutualFundProductCategoryParams.setParentCategoryCode("01");
            l_mutualFundProductCategoryParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
            l_mutualFundProductCategoryParams.setLastUpdater("132");
            l_mutualFundProductCategoryParams.setCreatedTimestamp(GtlUtils.getSystemTimestamp());
            TestDBUtility.insertWithDel(l_mutualFundProductCategoryParams);

            TestDBUtility.deleteAll(MutualFundProductParams.TYPE);
            MutualFundProductParams l_mutualFundProductParams = TestDBUtility.getMutualFundProductRow();
            l_mutualFundProductParams.setInstitutionCode("0D");
            l_mutualFundProductParams.setCategoryCode("0");
            TestDBUtility.insertWithDel(l_mutualFundProductParams);
            l_adminMutualCategoryRegistServiceImpl.submitMutualProductCategoryRegistr(l_request);
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            assertEquals(l_ex.getErrorInfo(),WEB3ErrorCatalog.BUSINESS_ERROR_03081);
        }
        log.exiting(STR_METHOD_NAME);
    }

    public void testsubmitMutualProductCategoryRegistrC0003()
    {
        final String STR_METHOD_NAME = "testsubmitMutualProductCategoryRegistrC0003()";
        log.entering(STR_METHOD_NAME);


        WEB3AdminMutualCategoryRegistServiceImpl l_adminMutualCategoryRegistServiceImpl =
            new WEB3AdminMutualCategoryRegistServiceImpl();

        WEB3AdminMutualCategoryRegistCompleteRequest l_request =
            new WEB3AdminMutualCategoryRegistCompleteRequest();

        l_request.categoryCode = "0";
        l_request.categoryName = "002";
        l_request.parentCategoryCode = null;
        l_request.procedureDiv = "2";
        MOCK_MANAGER.setIsMockUsed(true);
        LoginInfoImpl l_loginInfoImpl = new LoginInfoImpl();
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
            "getLoginInfo",
            new Class[] {},
            l_loginInfoImpl);
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.gentrade.WEB3Administrator",
                "getInstitutionCode",
                new Class[] {},
                "0D");
        WEB3AdministratorForMock.mockValidateTradingPassword("123", true);
        try
        {
            AdministratorParams l_administratorParams = TestDBUtility.getAdministratorRow();
            TestDBUtility.deleteAll(AdministratorParams.TYPE);
            l_administratorParams.setLoginId(0);
            l_administratorParams.setCreatedTimestamp(GtlUtils.getSystemTimestamp());
            TestDBUtility.insertWithDel(l_administratorParams);

            AdminPermissionParams l_adminPermissionParams = TestDBUtility.getAdminPermissionRow();
            l_adminPermissionParams.setInstitutionCode("0D");
            l_adminPermissionParams.setTransactionCategory(WEB3TransactionCategoryDef.ADMIN_MUTUAL_CONDITIONS);
            l_adminPermissionParams.setPermissionLevel(l_administratorParams.getPermissionLevel());
            l_adminPermissionParams.setUpdatePermissionFlag(BooleanEnum.TRUE);
            TestDBUtility.deleteAll(AdminPermissionParams.TYPE);
            TestDBUtility.insertWithDel(l_adminPermissionParams);

            MutualFundProductCategoryParams l_mutualFundProductCategoryParams =
                TestDBUtility.getMutualFundProductCategoryRow();
            TestDBUtility.deleteAll(MutualFundProductCategoryParams.TYPE);
            l_mutualFundProductCategoryParams.setCategoryCode("0");
            l_mutualFundProductCategoryParams.setCategoryName("012");
            l_mutualFundProductCategoryParams.setInstitutionCode("0D");
            l_mutualFundProductCategoryParams.setParentCategoryCode("01");
            l_mutualFundProductCategoryParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
            l_mutualFundProductCategoryParams.setLastUpdater("132");
            l_mutualFundProductCategoryParams.setCreatedTimestamp(GtlUtils.getSystemTimestamp());
            TestDBUtility.insertWithDel(l_mutualFundProductCategoryParams);

            l_mutualFundProductCategoryParams.setCategoryCode("01");
            l_mutualFundProductCategoryParams.setCategoryName("012");
            l_mutualFundProductCategoryParams.setInstitutionCode("0D");
            l_mutualFundProductCategoryParams.setParentCategoryCode("01");
            l_mutualFundProductCategoryParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
            l_mutualFundProductCategoryParams.setLastUpdater("132");
            l_mutualFundProductCategoryParams.setCreatedTimestamp(GtlUtils.getSystemTimestamp());
            TestDBUtility.insertWithDel(l_mutualFundProductCategoryParams);

            l_mutualFundProductCategoryParams.setCategoryCode("01");
            l_mutualFundProductCategoryParams.setCategoryName("012");
            l_mutualFundProductCategoryParams.setInstitutionCode("0D");
            l_mutualFundProductCategoryParams.setParentCategoryCode("0");
            l_mutualFundProductCategoryParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
            l_mutualFundProductCategoryParams.setLastUpdater("132");
            l_mutualFundProductCategoryParams.setCreatedTimestamp(GtlUtils.getSystemTimestamp());
            TestDBUtility.insertWithDel(l_mutualFundProductCategoryParams);

            l_mutualFundProductCategoryParams.setCategoryCode("0");
            l_mutualFundProductCategoryParams.setCategoryName("012");
            l_mutualFundProductCategoryParams.setInstitutionCode("0D");
            l_mutualFundProductCategoryParams.setParentCategoryCode("0");
            l_mutualFundProductCategoryParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
            l_mutualFundProductCategoryParams.setLastUpdater("132");
            l_mutualFundProductCategoryParams.setCreatedTimestamp(GtlUtils.getSystemTimestamp());
            TestDBUtility.insertWithDel(l_mutualFundProductCategoryParams);

            TestDBUtility.deleteAll(MutualFundProductParams.TYPE);

            l_adminMutualCategoryRegistServiceImpl.submitMutualProductCategoryRegistr(l_request);
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
//            assertEquals(l_ex.getErrorInfo(),WEB3ErrorCatalog.BUSINESS_ERROR_03081);
        }
        log.exiting(STR_METHOD_NAME);
    }

    public void testsubmitMutualProductCategoryRegistrC0004()
    {
        final String STR_METHOD_NAME = "testsubmitMutualProductCategoryRegistrC0004()";
        log.entering(STR_METHOD_NAME);

        WEB3AdminMutualCategoryRegistServiceImpl l_adminMutualCategoryRegistServiceImpl =
            new WEB3AdminMutualCategoryRegistServiceImpl();

        WEB3AdminMutualCategoryRegistCompleteRequest l_request =
            new WEB3AdminMutualCategoryRegistCompleteRequest();

        l_request.categoryCode = "0";
        l_request.categoryName = "002";
        l_request.parentCategoryCode = null;
        l_request.procedureDiv = "2";
        MOCK_MANAGER.setIsMockUsed(true);
        LoginInfoImpl l_loginInfoImpl = new LoginInfoImpl();
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
            "getLoginInfo",
            new Class[] {},
            l_loginInfoImpl);
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.gentrade.WEB3Administrator",
                "getInstitutionCode",
                new Class[] {},
                "0D");

        WEB3AdministratorForMock.mockValidateTradingPassword("123", true);
        try
        {
            AdministratorParams l_administratorParams = TestDBUtility.getAdministratorRow();
            TestDBUtility.deleteAll(AdministratorParams.TYPE);
            l_administratorParams.setLoginId(0);
            l_administratorParams.setCreatedTimestamp(GtlUtils.getSystemTimestamp());
            TestDBUtility.insertWithDel(l_administratorParams);

            AdminPermissionParams l_adminPermissionParams = TestDBUtility.getAdminPermissionRow();
            l_adminPermissionParams.setInstitutionCode("0D");
            l_adminPermissionParams.setTransactionCategory(WEB3TransactionCategoryDef.ADMIN_MUTUAL_CONDITIONS);
            l_adminPermissionParams.setPermissionLevel(l_administratorParams.getPermissionLevel());
            l_adminPermissionParams.setUpdatePermissionFlag(BooleanEnum.TRUE);
            TestDBUtility.deleteAll(AdminPermissionParams.TYPE);
            TestDBUtility.insertWithDel(l_adminPermissionParams);

            MutualFundProductCategoryParams l_mutualFundProductCategoryParams =
                TestDBUtility.getMutualFundProductCategoryRow();
            TestDBUtility.deleteAll(MutualFundProductCategoryParams.TYPE);
            TestDBUtility.insertWithDel(l_mutualFundProductCategoryParams);

            l_mutualFundProductCategoryParams.setCategoryCode("01");
            l_mutualFundProductCategoryParams.setCategoryName("012");
            l_mutualFundProductCategoryParams.setInstitutionCode("0D");
            l_mutualFundProductCategoryParams.setParentCategoryCode("01");
            l_mutualFundProductCategoryParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
            l_mutualFundProductCategoryParams.setLastUpdater("132");
            l_mutualFundProductCategoryParams.setCreatedTimestamp(GtlUtils.getSystemTimestamp());
            TestDBUtility.insertWithDel(l_mutualFundProductCategoryParams);

            l_adminMutualCategoryRegistServiceImpl.submitMutualProductCategoryRegistr(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }

}
@
