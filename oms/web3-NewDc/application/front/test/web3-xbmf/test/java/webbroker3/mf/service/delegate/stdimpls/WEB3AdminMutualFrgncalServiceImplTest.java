head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.02.10.43;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3AdminMutualFrgncalServiceImplTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.mf.service.delegate.stdimpls;

import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.kernel.util.ThreadLocalSystemAttributesRegistry;
import com.fitechlabs.xtrade.plugin.security.oplogin.LoginInfo;
import com.fitechlabs.xtrade.plugin.security.oplogin.impl.LoginInfoImpl;
import com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.InstitutionParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.ProductParams;
import com.fitechlabs.xtrade.plugin.tc.xbmf.data.MutualFundProductParams;
import com.fitechlabs.xtrade.plugin.tc.xbmf.data.MutualFundProductRow;

import test.util.TestDBUtility;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3SystemHandlingDivDef;
import webbroker3.gentrade.WEB3Administrator;
import webbroker3.gentrade.WEB3AdministratorForMock;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.gentrade.data.AdminPermissionParams;
import webbroker3.gentrade.data.AdminPermissionRow;
import webbroker3.gentrade.data.AdministratorParams;
import webbroker3.gentrade.data.AdministratorRow;
import webbroker3.gentrade.define.WEB3GentradeTimeDef;
import webbroker3.mf.data.MutualFundFrgncalParams;
import webbroker3.mf.data.MutualFundFrgncalRow;
import webbroker3.mf.message.WEB3AdminMutualConditionsCompleteRequest;
import webbroker3.mf.message.WEB3AdminMutualFrgncalCompleteRequest;
import webbroker3.mf.message.WEB3AdminMutualFrgncalInputRequest;
import webbroker3.mf.message.WEB3AdminMutualFrgncalInputResponse;
import webbroker3.mf.message.WEB3AdminMutualFrgncalReferenceRequest;
import webbroker3.mf.message.WEB3AdminMutualFrgncalReferenceResponse;
import webbroker3.mf.message.WEB3MutualBizDateUnit;
import webbroker3.mf.message.WEB3MutualProductCodeNameUnit;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

public class WEB3AdminMutualFrgncalServiceImplTest extends TestBaseForMock
{
    /**
     *  ログユーティリティ<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(
                WEB3AdminMutualFrgncalServiceImplTest.class);

    private WEB3AdminMutualFrgncalServiceImpl l_serviceImplForMock =
        new WEB3AdminMutualFrgncalServiceImplForMock();

    private WEB3AdminMutualFrgncalServiceImpl l_serviceImpl =
        new WEB3AdminMutualFrgncalServiceImpl();

    public WEB3AdminMutualFrgncalServiceImplTest(String name)
    {
        super(name);
    }

    protected void setUp() throws Exception
    {
        super.setUp();
        TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);
        Date l_datLocalSystemTime = WEB3DateUtility.getDate("20090201", WEB3GentradeTimeDef.DATE_FORMAT_YMD);
        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3GentradeTradingTimeManagement.TIMESTAMP_TAG, new Timestamp(l_datLocalSystemTime.getTime()));
    }

    protected void tearDown() throws Exception
    {
        super.tearDown();
        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3GentradeTradingTimeManagement.TIMESTAMP_TAG, null);
    }

    public void testExecute_C0001()
    {
        final String STR_METHOD_NAME = "testExecute_C0001()";
        log.entering(STR_METHOD_NAME);

        WEB3AdminMutualFrgncalInputRequest l_request = null;
        try
        {
            l_serviceImplForMock.execute(l_request);
            fail();
        }
        catch (WEB3BaseRuntimeException l_ex)
        {
            assertEquals(l_ex.getErrorInfo(), WEB3ErrorCatalog.SYSTEM_ERROR_80017);
        }
        catch (WEB3BaseException l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        catch (Exception l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }

        log.exiting(STR_METHOD_NAME);
    }

    public void testExecute_C0002()
    {
        final String STR_METHOD_NAME = "testExecute_C0002()";
        log.entering(STR_METHOD_NAME);

        WEB3AdminMutualFrgncalInputRequest l_request =
            new WEB3AdminMutualFrgncalInputRequest();
        try
        {
            LoginInfo l_loginInfo = new LoginInfoImpl();
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getLoginInfo",
                new Class[] {},
                l_loginInfo);

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getLoginId",
                new Class[] {},
                new Long(123));

            TestDBUtility.deleteAll(AdministratorRow.TYPE);
            AdministratorParams l_administratorParams =
                TestDBUtility.getAdministratorRow();
            l_administratorParams.setLoginId(1234);
            TestDBUtility.insertWithDel(l_administratorParams);

            l_serviceImpl.execute(l_request);
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            assertEquals(l_ex.getErrorInfo(), WEB3ErrorCatalog.SYSTEM_ERROR_80005);
        }
        catch (Exception l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }

        log.exiting(STR_METHOD_NAME);
    }

    public void testExecute_C0003()
    {
        final String STR_METHOD_NAME = "testExecute_C0003()";
        log.entering(STR_METHOD_NAME);

        WEB3AdminMutualFrgncalReferenceRequest l_request =
            new WEB3AdminMutualFrgncalReferenceRequest();
        try
        {
            l_serviceImplForMock.execute(l_request);
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            assertEquals(l_ex.getErrorInfo(), WEB3ErrorCatalog.BUSINESS_ERROR_00079);
        }
        catch (Exception l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }

        log.exiting(STR_METHOD_NAME);
    }

    public void testExecute_C0004()
    {
        final String STR_METHOD_NAME = "testExecute_C0004()";
        log.entering(STR_METHOD_NAME);

        WEB3AdminMutualFrgncalCompleteRequest l_request =
            new WEB3AdminMutualFrgncalCompleteRequest();
        try
        {
            l_request.mutualProductCode = "123";
            l_serviceImplForMock.execute(l_request);
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            assertEquals(l_ex.getErrorInfo(), WEB3ErrorCatalog.BUSINESS_ERROR_00411);
        }
        catch (Exception l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }

        log.exiting(STR_METHOD_NAME);
    }

    public void testExecute_C0005()
    {
        final String STR_METHOD_NAME = "testExecute_C0005()";
        log.entering(STR_METHOD_NAME);

        WEB3AdminMutualConditionsCompleteRequest l_request =
            new WEB3AdminMutualConditionsCompleteRequest();
        try
        {
            l_serviceImplForMock.execute(l_request);
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            assertEquals(l_ex.getErrorInfo(), WEB3ErrorCatalog.SYSTEM_ERROR_80018);
        }
        catch (Exception l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }

        log.exiting(STR_METHOD_NAME);
    }

    public void testInputFrgncal_C0001()
    {
        final String STR_METHOD_NAME = "testInputFrgncal_C0001()";
        log.entering(STR_METHOD_NAME);

        WEB3AdminMutualFrgncalInputRequest l_request =
            new WEB3AdminMutualFrgncalInputRequest();
        try
        {
            AdministratorParams l_administratorParams =
                TestDBUtility.getAdministratorRow();
            WEB3Administrator l_administrator = new WEB3Administrator(l_administratorParams);
            WEB3AdministratorForMock.mockGetInstanceFromLoginInfo(l_administrator);

            TestDBUtility.deleteAll(AdminPermissionRow.TYPE);
            AdminPermissionParams l_adminPermissionParams =
                TestDBUtility.getAdminPermissionRow();
            l_adminPermissionParams.setInstitutionCode("0D");
            l_adminPermissionParams.setPermissionLevel("331");
            l_adminPermissionParams.setTransactionCategory("C0202");
            l_adminPermissionParams.setUpdatePermissionFlag(BooleanEnum.FALSE);
            TestDBUtility.insertWithDel(l_adminPermissionParams);

            TestDBUtility.deleteAll(InstitutionParams.TYPE);
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            l_institutionParams.setInstitutionId(33L);
            TestDBUtility.insertWithDel(l_institutionParams);

            TestDBUtility.deleteAll(MutualFundProductRow.TYPE);
            MutualFundProductParams l_mutualFundProductParams =
                TestDBUtility.getMutualFundProductRow();
            l_mutualFundProductParams.setInstitutionCode("77");
            l_mutualFundProductParams.setSystemHandlingDiv(
                WEB3SystemHandlingDivDef.WEBBROKER3_TREAT_IT_IN);
            TestDBUtility.insertWithDel(l_mutualFundProductParams);

            WEB3AdminMutualFrgncalInputResponse l_response =
                l_serviceImpl.inputFrgncal(l_request);

            assertEquals(0, l_response.mutualProductCodeNames.length);
            assertEquals(2, l_response.searchYMList.length);
            assertEquals(
                "200902", l_response.searchYMList[0]);
            assertEquals(
                "200903", l_response.searchYMList[1]);
        }
        catch (Exception l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            fail();
        }

        log.exiting(STR_METHOD_NAME);
    }

    public void testInputFrgncal_C0002()
    {
        final String STR_METHOD_NAME = "testInputFrgncal_C0002()";
        log.entering(STR_METHOD_NAME);

        WEB3AdminMutualFrgncalInputRequest l_request =
            new WEB3AdminMutualFrgncalInputRequest();
        try
        {
            AdministratorParams l_administratorParams =
                TestDBUtility.getAdministratorRow();
            WEB3Administrator l_administrator = new WEB3Administrator(l_administratorParams);
            WEB3AdministratorForMock.mockGetInstanceFromLoginInfo(l_administrator);

            TestDBUtility.deleteAll(AdminPermissionRow.TYPE);
            AdminPermissionParams l_adminPermissionParams =
                TestDBUtility.getAdminPermissionRow();
            l_adminPermissionParams.setInstitutionCode("0D");
            l_adminPermissionParams.setPermissionLevel("331");
            l_adminPermissionParams.setTransactionCategory("C0202");
            l_adminPermissionParams.setUpdatePermissionFlag(BooleanEnum.FALSE);
            TestDBUtility.insertWithDel(l_adminPermissionParams);

            TestDBUtility.deleteAll(InstitutionParams.TYPE);
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            l_institutionParams.setInstitutionId(33L);
            TestDBUtility.insertWithDel(l_institutionParams);

            TestDBUtility.deleteAll(MutualFundProductRow.TYPE);
            MutualFundProductParams l_mutualFundProductParams =
                TestDBUtility.getMutualFundProductRow();
            l_mutualFundProductParams.setProductId(001);
            l_mutualFundProductParams.setProductCode("0001");
            l_mutualFundProductParams.setSwtPossibleGroupId(101);
            l_mutualFundProductParams.setInstitutionCode("0D");
            l_mutualFundProductParams.setSystemHandlingDiv(
                WEB3SystemHandlingDivDef.WEBBROKER3_TREAT_IT_IN);
            TestDBUtility.insertWithDel(l_mutualFundProductParams);
            l_mutualFundProductParams.setProductId(002);
            l_mutualFundProductParams.setProductCode("0002");
            l_mutualFundProductParams.setSwtPossibleGroupId(100);
            l_mutualFundProductParams.setSystemHandlingDiv(
                WEB3SystemHandlingDivDef.MAIL_REQUEST_ONLY);
            TestDBUtility.insertWithDel(l_mutualFundProductParams);

            WEB3AdminMutualFrgncalInputResponse l_response =
                l_serviceImpl.inputFrgncal(l_request);

            assertEquals(1, l_response.mutualProductCodeNames.length);
            assertEquals(
                "0001",
                ((WEB3MutualProductCodeNameUnit)l_response.mutualProductCodeNames[0]).mutualProductCode);
            assertNull(((WEB3MutualProductCodeNameUnit)l_response.mutualProductCodeNames[0]).taxTypeList);
            assertEquals(2, l_response.searchYMList.length);
            assertEquals(
                "200902", l_response.searchYMList[0]);
            assertEquals(
                "200903", l_response.searchYMList[1]);
        }
        catch (Exception l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            fail();
        }

        log.exiting(STR_METHOD_NAME);
    }

    public void testInputFrgncal_C0003()
    {
        final String STR_METHOD_NAME = "testInputFrgncal_C0003()";
        log.entering(STR_METHOD_NAME);

        WEB3AdminMutualFrgncalInputRequest l_request =
            new WEB3AdminMutualFrgncalInputRequest();
        try
        {
            AdministratorParams l_administratorParams =
                TestDBUtility.getAdministratorRow();
            WEB3Administrator l_administrator = new WEB3Administrator(l_administratorParams);
            WEB3AdministratorForMock.mockGetInstanceFromLoginInfo(l_administrator);

            TestDBUtility.deleteAll(AdminPermissionRow.TYPE);
            AdminPermissionParams l_adminPermissionParams =
                TestDBUtility.getAdminPermissionRow();
            l_adminPermissionParams.setInstitutionCode("0D");
            l_adminPermissionParams.setPermissionLevel("331");
            l_adminPermissionParams.setTransactionCategory("C0202");
            l_adminPermissionParams.setUpdatePermissionFlag(BooleanEnum.FALSE);
            TestDBUtility.insertWithDel(l_adminPermissionParams);

            TestDBUtility.deleteAll(InstitutionParams.TYPE);
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            l_institutionParams.setInstitutionId(33L);
            TestDBUtility.insertWithDel(l_institutionParams);

            TestDBUtility.deleteAll(MutualFundProductRow.TYPE);
            MutualFundProductParams l_mutualFundProductParams =
                TestDBUtility.getMutualFundProductRow();
            l_mutualFundProductParams.setProductId(001);
            l_mutualFundProductParams.setInstitutionCode("0D");
            l_mutualFundProductParams.setSwtPossibleGroupId(101);
            l_mutualFundProductParams.setProductCode("025");
            l_mutualFundProductParams.setSystemHandlingDiv(
                WEB3SystemHandlingDivDef.WEBBROKER3_TREAT_IT_IN);
            TestDBUtility.insertWithDel(l_mutualFundProductParams);
            l_mutualFundProductParams.setProductId(002);
            l_mutualFundProductParams.setSwtPossibleGroupId(102);
            l_mutualFundProductParams.setProductCode("024");
            l_mutualFundProductParams.setSystemHandlingDiv(
                WEB3SystemHandlingDivDef.WEBBROKER3_TREAT_IT_IN);
            TestDBUtility.insertWithDel(l_mutualFundProductParams);
            l_mutualFundProductParams.setProductId(003);
            l_mutualFundProductParams.setSwtPossibleGroupId(101);
            l_mutualFundProductParams.setProductCode("023");
            l_mutualFundProductParams.setSystemHandlingDiv(
                WEB3SystemHandlingDivDef.MAIL_REQUEST_ONLY);
            TestDBUtility.insertWithDel(l_mutualFundProductParams);

            WEB3AdminMutualFrgncalInputResponse l_response =
                l_serviceImpl.inputFrgncal(l_request);

            assertEquals(2, l_response.mutualProductCodeNames.length);
            assertEquals(
                "024",
                ((WEB3MutualProductCodeNameUnit)l_response.mutualProductCodeNames[0]).mutualProductCode);
            assertNull(((WEB3MutualProductCodeNameUnit)l_response.mutualProductCodeNames[0]).taxTypeList);
            assertEquals(
                "025",
                ((WEB3MutualProductCodeNameUnit)l_response.mutualProductCodeNames[1]).mutualProductCode);
            assertNull(((WEB3MutualProductCodeNameUnit)l_response.mutualProductCodeNames[1]).taxTypeList);
            assertEquals(2, l_response.searchYMList.length);
            assertEquals(
                    "200902", l_response.searchYMList[0]);
                assertEquals(
                    "200903", l_response.searchYMList[1]);
        }
        catch (Exception l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            fail();
        }

        log.exiting(STR_METHOD_NAME);
    }

    public void testSearchFrgncal_C0001()
    {
        final String STR_METHOD_NAME = "testSearchFrgncal_C0001()";
        log.entering(STR_METHOD_NAME);

        WEB3AdminMutualFrgncalReferenceRequest l_request =
            new WEB3AdminMutualFrgncalReferenceRequest();
        l_request.mutualProductCode = null;

        try
        {
            l_serviceImpl.searchFrgncal(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            assertEquals(l_ex.getErrorInfo(), WEB3ErrorCatalog.BUSINESS_ERROR_00079);
        }
        catch (Exception l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            fail();
        }

        log.exiting(STR_METHOD_NAME);
    }

    public void testSearchFrgncal_C0002()
    {
        final String STR_METHOD_NAME = "testSearchFrgncal_C0002()";
        log.entering(STR_METHOD_NAME);

        WEB3AdminMutualFrgncalReferenceRequest l_request =
            new WEB3AdminMutualFrgncalReferenceRequest();
        l_request.mutualProductCode = "123";
        l_request.searchYM = "200807";

        try
        {
            AdministratorParams l_administratorParams =
                TestDBUtility.getAdministratorRow();
            WEB3Administrator l_administrator = new WEB3Administrator(l_administratorParams);
            WEB3AdministratorForMock.mockGetInstanceFromLoginInfo(l_administrator);

            TestDBUtility.deleteAll(AdminPermissionRow.TYPE);
            AdminPermissionParams l_adminPermissionParams =
                TestDBUtility.getAdminPermissionRow();
            l_adminPermissionParams.setInstitutionCode("0D");
            l_adminPermissionParams.setPermissionLevel("331");
            l_adminPermissionParams.setTransactionCategory("C0203");
            l_adminPermissionParams.setUpdatePermissionFlag(BooleanEnum.FALSE);
            TestDBUtility.insertWithDel(l_adminPermissionParams);

            l_serviceImpl.searchFrgncal(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            assertEquals(l_ex.getErrorInfo(), WEB3ErrorCatalog.BUSINESS_ERROR_01056);
        }
        catch (Exception l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            fail();
        }

        log.exiting(STR_METHOD_NAME);
    }

    public void testSearchFrgncal_C0003()
    {
        final String STR_METHOD_NAME = "testSearchFrgncal_C0003()";
        log.entering(STR_METHOD_NAME);

        WEB3AdminMutualFrgncalReferenceRequest l_request =
            new WEB3AdminMutualFrgncalReferenceRequest();
        l_request.mutualProductCode = "1234";
        l_request.searchYM = "200807";

        try
        {
            TestBaseForMock.MOCK_MANAGER.setIsMockUsed(false);

            AdministratorParams l_administratorParams =
                TestDBUtility.getAdministratorRow();
            WEB3Administrator l_administrator = new WEB3Administrator(l_administratorParams);
            WEB3AdministratorForMock.mockGetInstanceFromLoginInfo(l_administrator);

            TestDBUtility.deleteAll(AdminPermissionRow.TYPE);
            AdminPermissionParams l_adminPermissionParams =
                TestDBUtility.getAdminPermissionRow();
            l_adminPermissionParams.setInstitutionCode("0D");
            l_adminPermissionParams.setPermissionLevel("331");
            l_adminPermissionParams.setTransactionCategory("C0202");
            l_adminPermissionParams.setUpdatePermissionFlag(BooleanEnum.FALSE);
            TestDBUtility.insertWithDel(l_adminPermissionParams);


            TestDBUtility.deleteAll(InstitutionParams.TYPE);
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            l_institutionParams.setInstitutionId(33L);
            TestDBUtility.insertWithDel(l_institutionParams);

            TestDBUtility.deleteAll(MutualFundProductParams.TYPE);
            MutualFundProductParams l_mutualFundProductParams =
                TestDBUtility.getMutualFundProductRow();
            l_mutualFundProductParams.setProductId(123L);
            l_mutualFundProductParams.setInstitutionCode("0D");
            l_mutualFundProductParams.setProductCode("123");
            l_mutualFundProductParams.setProductIssueCode("0");
            TestDBUtility.insertWithDel(l_mutualFundProductParams);

            l_serviceImpl.searchFrgncal(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            assertEquals(l_ex.getErrorInfo(), WEB3ErrorCatalog.BUSINESS_ERROR_00334);
        }
        catch (Exception l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            fail();
        }
        finally
        {
            TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);
        }

        log.exiting(STR_METHOD_NAME);
    }

    public void testSearchFrgncal_C0004()
    {
        final String STR_METHOD_NAME = "testSearchFrgncal_C0004()";
        log.entering(STR_METHOD_NAME);

        WEB3AdminMutualFrgncalReferenceRequest l_request =
            new WEB3AdminMutualFrgncalReferenceRequest();
        l_request.mutualProductCode = "123";
        l_request.searchYM = "200807";

        try
        {
            TestBaseForMock.MOCK_MANAGER.setIsMockUsed(false);

            AdministratorParams l_administratorParams =
                TestDBUtility.getAdministratorRow();
            WEB3Administrator l_administrator = new WEB3Administrator(l_administratorParams);
            WEB3AdministratorForMock.mockGetInstanceFromLoginInfo(l_administrator);

            TestDBUtility.deleteAll(AdminPermissionRow.TYPE);
            AdminPermissionParams l_adminPermissionParams =
                TestDBUtility.getAdminPermissionRow();
            l_adminPermissionParams.setInstitutionCode("0D");
            l_adminPermissionParams.setPermissionLevel("331");
            l_adminPermissionParams.setTransactionCategory("C0202");
            l_adminPermissionParams.setUpdatePermissionFlag(BooleanEnum.FALSE);
            TestDBUtility.insertWithDel(l_adminPermissionParams);

            TestDBUtility.deleteAll(InstitutionParams.TYPE);
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            l_institutionParams.setInstitutionId(33L);
            TestDBUtility.insertWithDel(l_institutionParams);

            TestDBUtility.deleteAll(MutualFundProductParams.TYPE);
            MutualFundProductParams l_mutualFundProductParams =
                TestDBUtility.getMutualFundProductRow();
            l_mutualFundProductParams.setProductId(123L);
            l_mutualFundProductParams.setInstitutionCode("0D");
            l_mutualFundProductParams.setProductCode("123");
            l_mutualFundProductParams.setProductIssueCode("0");
            TestDBUtility.insertWithDel(l_mutualFundProductParams);

            TestDBUtility.deleteAll(ProductParams.TYPE);
            ProductParams l_productParams = TestDBUtility.getProductRow();
            l_productParams.setProductId(123L);
            TestDBUtility.insertWithDel(l_productParams);

            TestDBUtility.deleteAll(MutualFundFrgncalRow.TYPE);
            MutualFundFrgncalParams l_mutualFundFrgncalParams =
                TestDBUtility.getMutualFundFrgncalRow();
            l_mutualFundFrgncalParams.setHoliday(WEB3DateUtility.getDate("20080716","yyyyMMdd"));
            l_mutualFundFrgncalParams.setInstitutionCode("0D");
            l_mutualFundFrgncalParams.setProductCode("123");
            TestDBUtility.insertWithDel(l_mutualFundFrgncalParams);

            WEB3AdminMutualFrgncalReferenceResponse l_response =
                l_serviceImpl.searchFrgncal(l_request);

            assertEquals(l_response.notBizDateList.length, 1);
            int l_intReturn = WEB3DateUtility.compareToDay(l_response.notBizDateList[0],
                    WEB3DateUtility.getDate("20080716", "yyyyMMdd"));
            assertEquals(l_intReturn, 0);
        }
        catch (Exception l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            fail();
        }
        finally
        {
            TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);
        }

        log.exiting(STR_METHOD_NAME);
    }

    public void testSubmitFrgncal_C0001()
    {
        final String STR_METHOD_NAME = "testSubmitFrgncal_C0001()";
        log.entering(STR_METHOD_NAME);

        WEB3AdminMutualFrgncalCompleteRequest l_request =
            new WEB3AdminMutualFrgncalCompleteRequest();
        l_request.mutualProductCode = "123";
        l_request.bizDateList = null;

        try
        {
            l_serviceImpl.submitFrgncal(l_request);
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            assertEquals(l_ex.getErrorInfo(), WEB3ErrorCatalog.BUSINESS_ERROR_00411);
        }
        catch (Exception l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            fail();
        }

        log.exiting(STR_METHOD_NAME);
    }

    public void testSubmitFrgncal_C0002()
    {
        final String STR_METHOD_NAME = "testSubmitFrgncal_C0002()";
        log.entering(STR_METHOD_NAME);

        WEB3AdminMutualFrgncalCompleteRequest l_request =
            new WEB3AdminMutualFrgncalCompleteRequest();
        l_request.mutualProductCode = "123";
        l_request.bizDateList = new WEB3MutualBizDateUnit[1];

        try
        {
            AdministratorParams l_administratorParams =
                TestDBUtility.getAdministratorRow();
            WEB3Administrator l_administrator = new WEB3Administrator(l_administratorParams);
            WEB3AdministratorForMock.mockGetInstanceFromLoginInfo(l_administrator);

            TestDBUtility.deleteAll(AdminPermissionRow.TYPE);
            AdminPermissionParams l_adminPermissionParams =
                TestDBUtility.getAdminPermissionRow();
            l_adminPermissionParams.setInstitutionCode("0D");
            l_adminPermissionParams.setPermissionLevel("331");
            l_adminPermissionParams.setTransactionCategory("C0203");
            l_adminPermissionParams.setUpdatePermissionFlag(BooleanEnum.TRUE);
            TestDBUtility.insertWithDel(l_adminPermissionParams);

            l_serviceImpl.submitFrgncal(l_request);
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            assertEquals(l_ex.getErrorInfo(), WEB3ErrorCatalog.BUSINESS_ERROR_01056);
        }
        catch (Exception l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            fail();
        }

        log.exiting(STR_METHOD_NAME);
    }

    public void testSubmitFrgncal_C0003()
    {
        final String STR_METHOD_NAME = "testSubmitFrgncal_C0003()";
        log.entering(STR_METHOD_NAME);

        WEB3AdminMutualFrgncalCompleteRequest l_request =
            new WEB3AdminMutualFrgncalCompleteRequest();
        l_request.mutualProductCode = "123";
        l_request.bizDateList = new WEB3MutualBizDateUnit[1];

        try
        {
            LoginInfo l_loginInfo = new LoginInfoImpl();
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getLoginInfo",
                new Class[] {},
                l_loginInfo);

            Map l_mapAttributes = new HashMap();
            l_mapAttributes.put("TRADING_PWD_ENV", "0");
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginAdminServiceImpl",
                "getLoginTypeAttributes",
                new Class[] {long.class},
                l_mapAttributes);

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "checkPassword",
                new Class[] {String.class},
                new Boolean(false));

            AdministratorParams l_administratorParams =
                TestDBUtility.getAdministratorRow();
            WEB3Administrator l_administrator = new WEB3Administrator(l_administratorParams);
            WEB3AdministratorForMock.mockGetInstanceFromLoginInfo(l_administrator);

            TestDBUtility.deleteAll(AdminPermissionRow.TYPE);
            AdminPermissionParams l_adminPermissionParams =
                TestDBUtility.getAdminPermissionRow();
            l_adminPermissionParams.setInstitutionCode("0D");
            l_adminPermissionParams.setPermissionLevel("331");
            l_adminPermissionParams.setTransactionCategory("C0202");
            l_adminPermissionParams.setUpdatePermissionFlag(BooleanEnum.TRUE);
            TestDBUtility.insertWithDel(l_adminPermissionParams);

            l_serviceImpl.submitFrgncal(l_request);
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            assertEquals(l_ex.getErrorInfo(), WEB3ErrorCatalog.BUSINESS_ERROR_00009);
        }
        catch (Exception l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            fail();
        }

        log.exiting(STR_METHOD_NAME);
    }

    public void testSubmitFrgncal_C0004()
    {
        final String STR_METHOD_NAME = "testSubmitFrgncal_C0004()";
        log.entering(STR_METHOD_NAME);

        WEB3AdminMutualFrgncalCompleteRequest l_request =
            new WEB3AdminMutualFrgncalCompleteRequest();
        l_request.mutualProductCode = "1234";
        l_request.bizDateList = new WEB3MutualBizDateUnit[1];

        try
        {
            LoginInfo l_loginInfo = new LoginInfoImpl();
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getLoginInfo",
                new Class[] {},
                l_loginInfo);

            Map l_mapAttributes = new HashMap();
            l_mapAttributes.put("TRADING_PWD_ENV", "0");
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginAdminServiceImpl",
                "getLoginTypeAttributes",
                new Class[] {long.class},
                l_mapAttributes);

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "checkPassword",
                new Class[] {String.class},
                new Boolean(true));

            AdministratorParams l_administratorParams =
                TestDBUtility.getAdministratorRow();
            WEB3Administrator l_administrator = new WEB3Administrator(l_administratorParams);
            WEB3AdministratorForMock.mockGetInstanceFromLoginInfo(l_administrator);

            TestDBUtility.deleteAll(AdminPermissionRow.TYPE);
            AdminPermissionParams l_adminPermissionParams =
                TestDBUtility.getAdminPermissionRow();
            l_adminPermissionParams.setInstitutionCode("0D");
            l_adminPermissionParams.setPermissionLevel("331");
            l_adminPermissionParams.setTransactionCategory("C0202");
            l_adminPermissionParams.setUpdatePermissionFlag(BooleanEnum.TRUE);
            TestDBUtility.insertWithDel(l_adminPermissionParams);

            TestDBUtility.deleteAll(MutualFundProductParams.TYPE);
            MutualFundProductParams l_mutualFundProductParams =
                TestDBUtility.getMutualFundProductRow();
            l_mutualFundProductParams.setProductId(123L);
            l_mutualFundProductParams.setInstitutionCode("0D");
            l_mutualFundProductParams.setProductCode("123");
            l_mutualFundProductParams.setProductIssueCode("0");
            TestDBUtility.insertWithDel(l_mutualFundProductParams);

            TestDBUtility.deleteAll(ProductParams.TYPE);
            ProductParams l_productParams = TestDBUtility.getProductRow();
            l_productParams.setProductId(123L);
            TestDBUtility.insertWithDel(l_productParams);

            l_serviceImpl.submitFrgncal(l_request);
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            assertEquals(l_ex.getErrorInfo(), WEB3ErrorCatalog.BUSINESS_ERROR_00334);
        }
        catch (Exception l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            fail();
        }

        log.exiting(STR_METHOD_NAME);
    }

    public void testSubmitFrgncal_C0005()
    {
        final String STR_METHOD_NAME = "testSubmitFrgncal_C0005()";
        log.entering(STR_METHOD_NAME);

        WEB3AdminMutualFrgncalCompleteRequest l_request =
            new WEB3AdminMutualFrgncalCompleteRequest();
        l_request.mutualProductCode = "123";
        l_request.bizDateList = new WEB3MutualBizDateUnit[1];
        WEB3MutualBizDateUnit l_mutualBizDateUnit = new WEB3MutualBizDateUnit();
        l_mutualBizDateUnit.bizDate = WEB3DateUtility.getDate("20080706", "yyyyMMdd");
        l_mutualBizDateUnit.bizDateType = "0";
        l_request.bizDateList[0] = l_mutualBizDateUnit;

        try
        {
            LoginInfo l_loginInfo = new LoginInfoImpl();
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getLoginInfo",
                new Class[] {},
                l_loginInfo);

            Map l_mapAttributes = new HashMap();
            l_mapAttributes.put("TRADING_PWD_ENV", "0");
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginAdminServiceImpl",
                "getLoginTypeAttributes",
                new Class[] {long.class},
                l_mapAttributes);

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "checkPassword",
                new Class[] {String.class},
                new Boolean(true));

            AdministratorParams l_administratorParams =
                TestDBUtility.getAdministratorRow();
            WEB3Administrator l_administrator = new WEB3Administrator(l_administratorParams);
            WEB3AdministratorForMock.mockGetInstanceFromLoginInfo(l_administrator);

            TestDBUtility.deleteAll(AdminPermissionRow.TYPE);
            AdminPermissionParams l_adminPermissionParams =
                TestDBUtility.getAdminPermissionRow();
            l_adminPermissionParams.setInstitutionCode("0D");
            l_adminPermissionParams.setPermissionLevel("331");
            l_adminPermissionParams.setTransactionCategory("C0202");
            l_adminPermissionParams.setUpdatePermissionFlag(BooleanEnum.TRUE);
            TestDBUtility.insertWithDel(l_adminPermissionParams);

            TestDBUtility.deleteAll(MutualFundProductParams.TYPE);
            MutualFundProductParams l_mutualFundProductParams =
                TestDBUtility.getMutualFundProductRow();
            l_mutualFundProductParams.setProductId(123L);
            l_mutualFundProductParams.setInstitutionCode("0D");
            l_mutualFundProductParams.setProductCode("123");
            l_mutualFundProductParams.setProductIssueCode("0");
            TestDBUtility.insertWithDel(l_mutualFundProductParams);

            TestDBUtility.deleteAll(ProductParams.TYPE);
            ProductParams l_productParams = TestDBUtility.getProductRow();
            l_productParams.setProductId(123L);
            TestDBUtility.insertWithDel(l_productParams);

            TestDBUtility.deleteAll(MutualFundFrgncalRow.TYPE);
            MutualFundFrgncalParams l_mutualFundFrgncalParams =
                TestDBUtility.getMutualFundFrgncalRow();
            l_mutualFundFrgncalParams.setHoliday(WEB3DateUtility.getDate("20080706", "yyyyMMdd"));
            l_mutualFundFrgncalParams.setInstitutionCode("0D");
            l_mutualFundFrgncalParams.setProductCode("123");
            TestDBUtility.insertWithDel(l_mutualFundFrgncalParams);

            l_serviceImpl.submitFrgncal(l_request);

            QueryProcessor l_processorObject = Processors.getDefaultProcessor();
            StringBuffer l_sbWhere = new StringBuffer();
            l_sbWhere.append(" institution_code = ? "); //証券会社コード   
            l_sbWhere.append(" and product_code = ? "); //銘柄コード
            l_sbWhere.append(" and holiday = ? ");      //日付
            Object[] l_objMutualFrgncalWhere =
                { "0D", "123", WEB3DateUtility.getDate("20080706", "yyyyMMdd")};
            List l_lisMFFrgncalRows = 
                l_processorObject.doFindAllQuery(
                    MutualFundFrgncalRow.TYPE,
                    l_sbWhere.toString(),
                    l_objMutualFrgncalWhere);

            assertEquals(l_lisMFFrgncalRows.size(), 0);
        }
        catch (WEB3BaseException l_ex)
        {
            assertEquals(l_ex.getErrorInfo(), WEB3ErrorCatalog.BUSINESS_ERROR_00334);
        }
        catch (Exception l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            fail();
        }

        log.exiting(STR_METHOD_NAME);
    }

    public void testSubmitFrgncal_C0006()
    {
        final String STR_METHOD_NAME = "testSubmitFrgncal_C0006()";
        log.entering(STR_METHOD_NAME);

        WEB3AdminMutualFrgncalCompleteRequest l_request =
            new WEB3AdminMutualFrgncalCompleteRequest();
        l_request.mutualProductCode = "123";
        l_request.bizDateList = new WEB3MutualBizDateUnit[1];
        WEB3MutualBizDateUnit l_mutualBizDateUnit = new WEB3MutualBizDateUnit();
        l_mutualBizDateUnit.bizDate = WEB3DateUtility.getDate("20080709", "yyyyMMdd");
        l_mutualBizDateUnit.bizDateType = "1";
        l_request.bizDateList[0] = l_mutualBizDateUnit;

        try
        {
            LoginInfo l_loginInfo = new LoginInfoImpl();
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getLoginInfo",
                new Class[] {},
                l_loginInfo);

            Map l_mapAttributes = new HashMap();
            l_mapAttributes.put("TRADING_PWD_ENV", "0");
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginAdminServiceImpl",
                "getLoginTypeAttributes",
                new Class[] {long.class},
                l_mapAttributes);

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "checkPassword",
                new Class[] {String.class},
                new Boolean(true));

            AdministratorParams l_administratorParams =
                TestDBUtility.getAdministratorRow();
            WEB3Administrator l_administrator = new WEB3Administrator(l_administratorParams);
            WEB3AdministratorForMock.mockGetInstanceFromLoginInfo(l_administrator);

            TestDBUtility.deleteAll(AdminPermissionRow.TYPE);
            AdminPermissionParams l_adminPermissionParams =
                TestDBUtility.getAdminPermissionRow();
            l_adminPermissionParams.setInstitutionCode("0D");
            l_adminPermissionParams.setPermissionLevel("331");
            l_adminPermissionParams.setTransactionCategory("C0202");
            l_adminPermissionParams.setUpdatePermissionFlag(BooleanEnum.TRUE);
            TestDBUtility.insertWithDel(l_adminPermissionParams);

            TestDBUtility.deleteAll(MutualFundProductParams.TYPE);
            MutualFundProductParams l_mutualFundProductParams =
                TestDBUtility.getMutualFundProductRow();
            l_mutualFundProductParams.setProductId(123L);
            l_mutualFundProductParams.setInstitutionCode("0D");
            l_mutualFundProductParams.setProductCode("123");
            l_mutualFundProductParams.setProductIssueCode("0");
            TestDBUtility.insertWithDel(l_mutualFundProductParams);

            TestDBUtility.deleteAll(ProductParams.TYPE);
            ProductParams l_productParams = TestDBUtility.getProductRow();
            l_productParams.setProductId(123L);
            TestDBUtility.insertWithDel(l_productParams);

            TestDBUtility.deleteAll(MutualFundFrgncalRow.TYPE);
            MutualFundFrgncalParams l_mutualFundFrgncalParams =
                TestDBUtility.getMutualFundFrgncalRow();
            l_mutualFundFrgncalParams.setHoliday(WEB3DateUtility.getDate("20080810", "yyyyMMdd"));
            l_mutualFundFrgncalParams.setInstitutionCode("0D");
            l_mutualFundFrgncalParams.setProductCode("123");
            TestDBUtility.insertWithDel(l_mutualFundFrgncalParams);

            l_serviceImpl.submitFrgncal(l_request);

            QueryProcessor l_processorObject = Processors.getDefaultProcessor();
            StringBuffer l_sbWhere = new StringBuffer();
            l_sbWhere.append(" institution_code = ? "); //証券会社コード   
            l_sbWhere.append(" and product_code = ? "); //銘柄コード
            l_sbWhere.append(" and holiday = ? ");      //日付
            Object[] l_objMutualFrgncalWhere =
                { "0D", "123", WEB3DateUtility.getDate("20080709", "yyyyMMdd")};
            List l_lisMFFrgncalRows = 
                l_processorObject.doFindAllQuery(
                    MutualFundFrgncalRow.TYPE,
                    l_sbWhere.toString(),
                    l_objMutualFrgncalWhere);

            assertEquals(l_lisMFFrgncalRows.size(), 1);
            int l_intReturn = WEB3DateUtility.compareToDay(
                ((MutualFundFrgncalRow)l_lisMFFrgncalRows.get(0)).getHoliday(),
                WEB3DateUtility.getDate("20080709", "yyyyMMdd"));
            assertEquals(l_intReturn, 0);
        }
        catch (Exception l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            fail();
        }

        log.exiting(STR_METHOD_NAME);
    }
}
@
