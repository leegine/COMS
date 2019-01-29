head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.02.11.32;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3AdminMutualTPAdjustServiceImplTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.mf.service.delegate.stdimpls;

import java.sql.Timestamp;

import com.fitechlabs.xtrade.kernel.util.ThreadLocalSystemAttributesRegistry;
import com.fitechlabs.xtrade.plugin.security.oplogin.LoginInfo;
import com.fitechlabs.xtrade.plugin.security.oplogin.impl.LoginInfoImpl;
import com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProcessingResult;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccountTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.InstitutionParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.InstitutionRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.ProductParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.ProductRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.SubAccountParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.SubAccountRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.NewOrderSpec;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.OrderSubmissionResult;
import com.fitechlabs.xtrade.plugin.tc.xbmf.data.MutualFundProductParams;
import com.fitechlabs.xtrade.plugin.tc.xbmf.data.MutualFundProductRow;

import test.util.TestDBUtility;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.gentrade.WEB3Administrator;
import webbroker3.gentrade.WEB3AdministratorForMock;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagementForMock;
import webbroker3.gentrade.data.AdminPermissionParams;
import webbroker3.gentrade.data.AdminPermissionRow;
import webbroker3.gentrade.data.AdministratorParams;
import webbroker3.mf.message.WEB3AdminMutualTPACancelCompleteRequest;
import webbroker3.mf.message.WEB3AdminMutualTPAdjustCompleteRequest;
import webbroker3.mf.message.WEB3AdminMutualTPAdjustCompleteResponse;
import webbroker3.mf.message.WEB3AdminMutualTPAdjustConfirmRequest;
import webbroker3.mf.message.WEB3AdminMutualTPAdjustConfirmResponse;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

public class WEB3AdminMutualTPAdjustServiceImplTest extends TestBaseForMock
{
    /**
     *  ログユーティリティ<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(
            WEB3AdminMutualTPAdjustServiceImplTest.class);

    private WEB3AdminMutualTPAdjustServiceImpl l_serviceImpl =
        new WEB3AdminMutualTPAdjustServiceImpl();

    public WEB3AdminMutualTPAdjustServiceImplTest(String name)
    {
        super(name);
    }

    protected void setUp() throws Exception
    {
        super.setUp();
        TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);

        ThreadLocalSystemAttributesRegistry.setAttribute(
            "xblocks.gtl.attributes.systemtimestamp",
            new Timestamp(WEB3DateUtility.getDate("20080708", "yyyyMMdd").getTime()));
    }

    protected void tearDown() throws Exception
    {
        super.tearDown();
        ThreadLocalSystemAttributesRegistry.setAttribute("xblocks.gtl.attributes.systemtimestamp", null);
    }

    public void testExecute_C0001()
    {
        final String STR_METHOD_NAME = "testExecute_C0001()";
        log.entering(STR_METHOD_NAME);

        WEB3GenRequest l_request = null;
        try
        {
            l_serviceImpl.execute(l_request);
            fail();
        }
        catch (WEB3BaseRuntimeException l_ex)
        {
            assertEquals(l_ex.getErrorInfo(), WEB3ErrorCatalog.SYSTEM_ERROR_80017);
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

        WEB3AdminMutualTPAdjustConfirmRequest l_request =
            new WEB3AdminMutualTPAdjustConfirmRequest();
        l_request.accountCode = null;
        try
        {
            l_serviceImpl.execute(l_request);
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            assertEquals(l_ex.getErrorInfo(), WEB3ErrorCatalog.BUSINESS_ERROR_00835);
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

        WEB3AdminMutualTPAdjustCompleteRequest l_request =
            new WEB3AdminMutualTPAdjustCompleteRequest();
        l_request.accountCode = "123";
        l_request.mutualProductCode = null;
        try
        {
            l_serviceImpl.execute(l_request);
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            assertEquals(l_ex.getErrorInfo(), WEB3ErrorCatalog.BUSINESS_ERROR_01252);
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

        WEB3AdminMutualTPACancelCompleteRequest l_request =
            new WEB3AdminMutualTPACancelCompleteRequest();
        try
        {
            l_serviceImpl.execute(l_request);
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

    public void testValidateTPAdjust_C0001()
    {
        final String STR_METHOD_NAME = "testValidateTPAdjust_C0001()";
        log.entering(STR_METHOD_NAME);

        WEB3AdminMutualTPAdjustConfirmRequest l_request =
            new WEB3AdminMutualTPAdjustConfirmRequest();
        l_request.accountCode = null;

        try
        {
            l_serviceImpl.validateTPAdjust(l_request);
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            assertEquals(l_ex.getErrorInfo(), WEB3ErrorCatalog.BUSINESS_ERROR_00835);
        }
        catch (Exception l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }

        log.exiting(STR_METHOD_NAME);
    }

    public void testValidateTPAdjust_C0002()
    {
        final String STR_METHOD_NAME = "testValidateTPAdjust_C0002()";
        log.entering(STR_METHOD_NAME);

        WEB3AdminMutualTPAdjustConfirmRequest l_request =
            new WEB3AdminMutualTPAdjustConfirmRequest();
        l_request.accountCode = "123";
        l_request.mutualProductCode = "12345";
        l_request.settlePrice = "120";
        l_request.orderBizDate = WEB3DateUtility.getDate("20081010", "yyyyMMdd");
        l_request.executionTimestamp = WEB3DateUtility.getDate("20081009", "yyyyMMdd");
        l_request.deliveryDate = WEB3DateUtility.getDate("20081008", "yyyyMMdd");

        try
        {
            AdministratorParams l_administratorParams =
                TestDBUtility.getAdministratorRow();
            l_administratorParams.setBranchCode("123");
            l_administratorParams.setInstitutionId(1230);
            WEB3Administrator l_administrator = new WEB3Administrator(l_administratorParams);
            WEB3AdministratorForMock.mockGetInstanceFromLoginInfo(l_administrator);

            TestDBUtility.deleteAll(InstitutionParams.TYPE);
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            l_institutionParams.setInstitutionId(1230);
            TestDBUtility.insertWithDel(l_institutionParams);

            TestDBUtility.deleteAll(AdminPermissionRow.TYPE);
            AdminPermissionParams l_adminPermissionParams =
                TestDBUtility.getAdminPermissionRow();
            l_adminPermissionParams.setInstitutionCode("0D");
            l_adminPermissionParams.setPermissionLevel("331");
            l_adminPermissionParams.setTransactionCategory("C0205");
            l_adminPermissionParams.setUpdatePermissionFlag(BooleanEnum.TRUE);
            TestDBUtility.insertWithDel(l_adminPermissionParams);

            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountCode("123");
            l_mainAccountParams.setBranchCode("1234");
            l_mainAccountParams.setInstitutionCode("0D");
            TestDBUtility.insertWithDel(l_mainAccountParams);

            l_serviceImpl.validateTPAdjust(l_request);
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            assertEquals(l_ex.getErrorInfo(), WEB3ErrorCatalog.BUSINESS_ERROR_01035);
        }
        catch (Exception l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }

        log.exiting(STR_METHOD_NAME);
    }

    public void testValidateTPAdjust_C0003()
    {
        final String STR_METHOD_NAME = "testValidateTPAdjust_C0003()";
        log.entering(STR_METHOD_NAME);

        WEB3AdminMutualTPAdjustConfirmRequest l_request =
            new WEB3AdminMutualTPAdjustConfirmRequest();
        l_request.accountCode = "123";
        l_request.mutualProductCode = "12345";
        l_request.settlePrice = "120";
        l_request.orderBizDate = WEB3DateUtility.getDate("20081010", "yyyyMMdd");
        l_request.executionTimestamp = WEB3DateUtility.getDate("20081009", "yyyyMMdd");
        l_request.deliveryDate = WEB3DateUtility.getDate("20081008", "yyyyMMdd");

        try
        {
            AdministratorParams l_administratorParams =
                TestDBUtility.getAdministratorRow();
            l_administratorParams.setBranchCode("123");
            l_administratorParams.setInstitutionId(1234);
            WEB3Administrator l_administrator = new WEB3Administrator(l_administratorParams);
            WEB3AdministratorForMock.mockGetInstanceFromLoginInfo(l_administrator);

            TestDBUtility.deleteAll(AdminPermissionRow.TYPE);
            AdminPermissionParams l_adminPermissionParams =
                TestDBUtility.getAdminPermissionRow();
            l_adminPermissionParams.setInstitutionCode("0D");
            l_adminPermissionParams.setPermissionLevel("331");
            l_adminPermissionParams.setTransactionCategory("C0205");
            l_adminPermissionParams.setUpdatePermissionFlag(BooleanEnum.TRUE);
            TestDBUtility.insertWithDel(l_adminPermissionParams);

            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountCode("123");
            l_mainAccountParams.setBranchCode("123");
            l_mainAccountParams.setInstitutionCode("0D");
            TestDBUtility.insertWithDel(l_mainAccountParams);

            TestDBUtility.deleteAll(MutualFundProductRow.TYPE);
            MutualFundProductParams l_mutualFundProductParams =
                TestDBUtility.getMutualFundProductRow();
            l_mutualFundProductParams.setProductIssueCode("1");
            l_mutualFundProductParams.setInstitutionCode("120");
            l_mutualFundProductParams.setProductCode("12345");
            TestDBUtility.insertWithDel(l_mutualFundProductParams);

            TestDBUtility.deleteAll(InstitutionParams.TYPE);
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            l_institutionParams.setInstitutionId(1234);
            l_institutionParams.setInstitutionCode("120");
            TestDBUtility.insertWithDel(l_institutionParams);

            l_serviceImpl.validateTPAdjust(l_request);
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            assertEquals(l_ex.getErrorInfo(), WEB3ErrorCatalog.BUSINESS_ERROR_00391);
        }
        catch (Exception l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }

        log.exiting(STR_METHOD_NAME);
    }

    public void testValidateTPAdjust_C0004()
    {
        final String STR_METHOD_NAME = "testValidateTPAdjust_C0004()";
        log.entering(STR_METHOD_NAME);

        WEB3AdminMutualTPAdjustConfirmRequest l_request =
            new WEB3AdminMutualTPAdjustConfirmRequest();
        l_request.accountCode = "123";
        l_request.mutualProductCode = "12345";
        l_request.settlePrice = "120";
        l_request.orderBizDate = WEB3DateUtility.getDate("20081010", "yyyyMMdd");
        l_request.executionTimestamp = WEB3DateUtility.getDate("20081009", "yyyyMMdd");
        l_request.deliveryDate = WEB3DateUtility.getDate("20081008", "yyyyMMdd");

        try
        {
            AdministratorParams l_administratorParams =
                TestDBUtility.getAdministratorRow();
            l_administratorParams.setBranchCode("123");
            l_administratorParams.setInstitutionId(1234);
            WEB3Administrator l_administrator = new WEB3Administrator(l_administratorParams);
            WEB3AdministratorForMock.mockGetInstanceFromLoginInfo(l_administrator);

            TestDBUtility.deleteAll(AdminPermissionRow.TYPE);
            AdminPermissionParams l_adminPermissionParams =
                TestDBUtility.getAdminPermissionRow();
            l_adminPermissionParams.setInstitutionCode("0D");
            l_adminPermissionParams.setPermissionLevel("331");
            l_adminPermissionParams.setTransactionCategory("C0205");
            l_adminPermissionParams.setUpdatePermissionFlag(BooleanEnum.TRUE);
            TestDBUtility.insertWithDel(l_adminPermissionParams);

            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountCode("123");
            l_mainAccountParams.setBranchCode("123");
            l_mainAccountParams.setInstitutionCode("0D");
            TestDBUtility.insertWithDel(l_mainAccountParams);

            TestDBUtility.deleteAll(MutualFundProductRow.TYPE);
            MutualFundProductParams l_mutualFundProductParams =
                TestDBUtility.getMutualFundProductRow();
            l_mutualFundProductParams.setProductIssueCode("0");
            l_mutualFundProductParams.setInstitutionCode("120");
            l_mutualFundProductParams.setProductCode("12345");
            TestDBUtility.insertWithDel(l_mutualFundProductParams);

            TestDBUtility.deleteAll(InstitutionParams.TYPE);
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            l_institutionParams.setInstitutionId(1234);
            l_institutionParams.setInstitutionCode("120");
            TestDBUtility.insertWithDel(l_institutionParams);

            TestDBUtility.deleteAll(ProductRow.TYPE);
            ProductParams l_productParams = TestDBUtility.getProductRow();
            l_productParams.setProductId(32768);
            TestDBUtility.insertWithDel(l_productParams);

            WEB3GentradeTradingTimeManagementForMock.mockGetBizDateType(
                new Timestamp(WEB3DateUtility.getDate("20081010", "yyyyMMdd").getTime()),
                "0");

            l_serviceImpl.validateTPAdjust(l_request);
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            assertEquals(l_ex.getErrorInfo(), WEB3ErrorCatalog.BUSINESS_ERROR_02019);
        }
        catch (Exception l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }

        log.exiting(STR_METHOD_NAME);
    }

    public void testValidateTPAdjust_C0005()
    {
        final String STR_METHOD_NAME = "testValidateTPAdjust_C0005()";
        log.entering(STR_METHOD_NAME);

        WEB3AdminMutualTPAdjustConfirmRequest l_request =
            new WEB3AdminMutualTPAdjustConfirmRequest();
        l_request.accountCode = "123";
        l_request.mutualProductCode = "12345";
        l_request.settlePrice = "120";
        l_request.orderBizDate = WEB3DateUtility.getDate("20081010", "yyyyMMdd");
        l_request.executionTimestamp = WEB3DateUtility.getDate("20081009", "yyyyMMdd");
        l_request.deliveryDate = WEB3DateUtility.getDate("20081008", "yyyyMMdd");

        try
        {
            AdministratorParams l_administratorParams =
                TestDBUtility.getAdministratorRow();
            l_administratorParams.setBranchCode("123");
            l_administratorParams.setInstitutionId(1234);
            WEB3Administrator l_administrator = new WEB3Administrator(l_administratorParams);
            WEB3AdministratorForMock.mockGetInstanceFromLoginInfo(l_administrator);

            TestDBUtility.deleteAll(AdminPermissionRow.TYPE);
            AdminPermissionParams l_adminPermissionParams =
                TestDBUtility.getAdminPermissionRow();
            l_adminPermissionParams.setInstitutionCode("0D");
            l_adminPermissionParams.setPermissionLevel("331");
            l_adminPermissionParams.setTransactionCategory("C0205");
            l_adminPermissionParams.setUpdatePermissionFlag(BooleanEnum.TRUE);
            TestDBUtility.insertWithDel(l_adminPermissionParams);

            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountCode("123");
            l_mainAccountParams.setBranchCode("123");
            l_mainAccountParams.setInstitutionCode("0D");
            TestDBUtility.insertWithDel(l_mainAccountParams);

            TestDBUtility.deleteAll(MutualFundProductRow.TYPE);
            MutualFundProductParams l_mutualFundProductParams =
                TestDBUtility.getMutualFundProductRow();
            l_mutualFundProductParams.setProductIssueCode("0");
            l_mutualFundProductParams.setInstitutionCode("120");
            l_mutualFundProductParams.setProductCode("12345");
            TestDBUtility.insertWithDel(l_mutualFundProductParams);

            TestDBUtility.deleteAll(InstitutionParams.TYPE);
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            l_institutionParams.setInstitutionId(1234);
            l_institutionParams.setInstitutionCode("120");
            TestDBUtility.insertWithDel(l_institutionParams);

            TestDBUtility.deleteAll(ProductRow.TYPE);
            ProductParams l_productParams = TestDBUtility.getProductRow();
            l_productParams.setProductId(32768);
            TestDBUtility.insertWithDel(l_productParams);

            WEB3GentradeTradingTimeManagementForMock.mockGetBizDateType(
                new Timestamp(WEB3DateUtility.getDate("20081010", "yyyyMMdd").getTime()),
                "1");

            WEB3GentradeTradingTimeManagementForMock.mockGetBizDateType(
                    new Timestamp(WEB3DateUtility.getDate("20081009", "yyyyMMdd").getTime()),
                    "0");

            l_serviceImpl.validateTPAdjust(l_request);
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            assertEquals(l_ex.getErrorInfo(), WEB3ErrorCatalog.BUSINESS_ERROR_02149);
        }
        catch (Exception l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }

        log.exiting(STR_METHOD_NAME);
    }

    public void testValidateTPAdjust_C0006()
    {
        final String STR_METHOD_NAME = "testValidateTPAdjust_C0006()";
        log.entering(STR_METHOD_NAME);

        WEB3AdminMutualTPAdjustConfirmRequest l_request =
            new WEB3AdminMutualTPAdjustConfirmRequest();
        l_request.accountCode = "123";
        l_request.mutualProductCode = "12345";
        l_request.settlePrice = "120";
        l_request.orderBizDate = WEB3DateUtility.getDate("20081010", "yyyyMMdd");
        l_request.executionTimestamp = WEB3DateUtility.getDate("20081009", "yyyyMMdd");
        l_request.deliveryDate = WEB3DateUtility.getDate("20081008", "yyyyMMdd");

        try
        {
            AdministratorParams l_administratorParams =
                TestDBUtility.getAdministratorRow();
            l_administratorParams.setBranchCode("123");
            l_administratorParams.setInstitutionId(1234);
            WEB3Administrator l_administrator = new WEB3Administrator(l_administratorParams);
            WEB3AdministratorForMock.mockGetInstanceFromLoginInfo(l_administrator);

            TestDBUtility.deleteAll(AdminPermissionRow.TYPE);
            AdminPermissionParams l_adminPermissionParams =
                TestDBUtility.getAdminPermissionRow();
            l_adminPermissionParams.setInstitutionCode("0D");
            l_adminPermissionParams.setPermissionLevel("331");
            l_adminPermissionParams.setTransactionCategory("C0205");
            l_adminPermissionParams.setUpdatePermissionFlag(BooleanEnum.TRUE);
            TestDBUtility.insertWithDel(l_adminPermissionParams);

            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountCode("123");
            l_mainAccountParams.setBranchCode("123");
            l_mainAccountParams.setInstitutionCode("0D");
            TestDBUtility.insertWithDel(l_mainAccountParams);

            TestDBUtility.deleteAll(MutualFundProductRow.TYPE);
            MutualFundProductParams l_mutualFundProductParams =
                TestDBUtility.getMutualFundProductRow();
            l_mutualFundProductParams.setProductIssueCode("0");
            l_mutualFundProductParams.setInstitutionCode("120");
            l_mutualFundProductParams.setProductCode("12345");
            TestDBUtility.insertWithDel(l_mutualFundProductParams);

            TestDBUtility.deleteAll(InstitutionParams.TYPE);
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            l_institutionParams.setInstitutionId(1234);
            l_institutionParams.setInstitutionCode("120");
            TestDBUtility.insertWithDel(l_institutionParams);

            TestDBUtility.deleteAll(ProductRow.TYPE);
            ProductParams l_productParams = TestDBUtility.getProductRow();
            l_productParams.setProductId(32768);
            TestDBUtility.insertWithDel(l_productParams);

            WEB3GentradeTradingTimeManagementForMock.mockGetBizDateType(
                new Timestamp(WEB3DateUtility.getDate("20081010", "yyyyMMdd").getTime()),
                "1");

            WEB3GentradeTradingTimeManagementForMock.mockGetBizDateType(
                new Timestamp(WEB3DateUtility.getDate("20081009", "yyyyMMdd").getTime()),
                "1");

            WEB3GentradeTradingTimeManagementForMock.mockGetBizDateType(
                new Timestamp(WEB3DateUtility.getDate("20081008", "yyyyMMdd").getTime()),
                "0");

            l_serviceImpl.validateTPAdjust(l_request);
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            assertEquals(l_ex.getErrorInfo(), WEB3ErrorCatalog.BUSINESS_ERROR_02336);
        }
        catch (Exception l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }

        log.exiting(STR_METHOD_NAME);
    }

    public void testValidateTPAdjust_C0007()
    {
        final String STR_METHOD_NAME = "testValidateTPAdjust_C0007()";
        log.entering(STR_METHOD_NAME);

        WEB3AdminMutualTPAdjustConfirmRequest l_request =
            new WEB3AdminMutualTPAdjustConfirmRequest();
        l_request.accountCode = "123";
        l_request.mutualProductCode = "12345";
        l_request.settlePrice = "120";
        l_request.orderBizDate = WEB3DateUtility.getDate("20081010", "yyyyMMdd");
        l_request.executionTimestamp = WEB3DateUtility.getDate("20081009", "yyyyMMdd");
        l_request.deliveryDate = WEB3DateUtility.getDate("20080608", "yyyyMMdd");

        try
        {
            AdministratorParams l_administratorParams =
                TestDBUtility.getAdministratorRow();
            l_administratorParams.setBranchCode("123");
            l_administratorParams.setInstitutionId(1234);
            WEB3Administrator l_administrator = new WEB3Administrator(l_administratorParams);
            WEB3AdministratorForMock.mockGetInstanceFromLoginInfo(l_administrator);

            TestDBUtility.deleteAll(AdminPermissionRow.TYPE);
            AdminPermissionParams l_adminPermissionParams =
                TestDBUtility.getAdminPermissionRow();
            l_adminPermissionParams.setInstitutionCode("0D");
            l_adminPermissionParams.setPermissionLevel("331");
            l_adminPermissionParams.setTransactionCategory("C0205");
            l_adminPermissionParams.setUpdatePermissionFlag(BooleanEnum.TRUE);
            TestDBUtility.insertWithDel(l_adminPermissionParams);

            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountCode("123");
            l_mainAccountParams.setBranchCode("123");
            l_mainAccountParams.setInstitutionCode("0D");
            TestDBUtility.insertWithDel(l_mainAccountParams);

            TestDBUtility.deleteAll(MutualFundProductRow.TYPE);
            MutualFundProductParams l_mutualFundProductParams =
                TestDBUtility.getMutualFundProductRow();
            l_mutualFundProductParams.setProductIssueCode("0");
            l_mutualFundProductParams.setInstitutionCode("120");
            l_mutualFundProductParams.setProductCode("12345");
            TestDBUtility.insertWithDel(l_mutualFundProductParams);

            TestDBUtility.deleteAll(InstitutionParams.TYPE);
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            l_institutionParams.setInstitutionId(1234);
            l_institutionParams.setInstitutionCode("120");
            TestDBUtility.insertWithDel(l_institutionParams);

            TestDBUtility.deleteAll(ProductRow.TYPE);
            ProductParams l_productParams = TestDBUtility.getProductRow();
            l_productParams.setProductId(32768);
            TestDBUtility.insertWithDel(l_productParams);

            WEB3GentradeTradingTimeManagementForMock.mockGetBizDateType(
                new Timestamp(WEB3DateUtility.getDate("20081010", "yyyyMMdd").getTime()),
                "1");

            WEB3GentradeTradingTimeManagementForMock.mockGetBizDateType(
                new Timestamp(WEB3DateUtility.getDate("20081009", "yyyyMMdd").getTime()),
                "1");

            WEB3GentradeTradingTimeManagementForMock.mockGetBizDateType(
                new Timestamp(WEB3DateUtility.getDate("20080608", "yyyyMMdd").getTime()),
                "1");

            l_serviceImpl.validateTPAdjust(l_request);
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            assertEquals(l_ex.getErrorInfo(), WEB3ErrorCatalog.BUSINESS_ERROR_02347);
        }
        catch (Exception l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }

        log.exiting(STR_METHOD_NAME);
    }

    public void testValidateTPAdjust_C0008()
    {
        final String STR_METHOD_NAME = "testValidateTPAdjust_C0008()";
        log.entering(STR_METHOD_NAME);

        WEB3AdminMutualTPAdjustConfirmRequest l_request =
            new WEB3AdminMutualTPAdjustConfirmRequest();
        l_request.accountCode = "123";
        l_request.mutualProductCode = "12345";
        l_request.settlePrice = "120";
        l_request.orderBizDate = WEB3DateUtility.getDate("20081010", "yyyyMMdd");
        l_request.executionTimestamp = WEB3DateUtility.getDate("20081009", "yyyyMMdd");
        l_request.deliveryDate = WEB3DateUtility.getDate("20081008", "yyyyMMdd");

        WEB3AdminMutualTPAdjustConfirmResponse l_response = null;
        try
        {
            AdministratorParams l_administratorParams =
                TestDBUtility.getAdministratorRow();
            l_administratorParams.setBranchCode("123");
            l_administratorParams.setInstitutionId(1234);
            WEB3Administrator l_administrator = new WEB3Administrator(l_administratorParams);
            WEB3AdministratorForMock.mockGetInstanceFromLoginInfo(l_administrator);

            TestDBUtility.deleteAll(AdminPermissionRow.TYPE);
            AdminPermissionParams l_adminPermissionParams =
                TestDBUtility.getAdminPermissionRow();
            l_adminPermissionParams.setInstitutionCode("0D");
            l_adminPermissionParams.setPermissionLevel("331");
            l_adminPermissionParams.setTransactionCategory("C0205");
            l_adminPermissionParams.setUpdatePermissionFlag(BooleanEnum.TRUE);
            TestDBUtility.insertWithDel(l_adminPermissionParams);

            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountCode("123");
            l_mainAccountParams.setBranchCode("123");
            l_mainAccountParams.setInstitutionCode("0D");
            TestDBUtility.insertWithDel(l_mainAccountParams);

            TestDBUtility.deleteAll(MutualFundProductRow.TYPE);
            MutualFundProductParams l_mutualFundProductParams =
                TestDBUtility.getMutualFundProductRow();
            l_mutualFundProductParams.setProductIssueCode("0");
            l_mutualFundProductParams.setInstitutionCode("120");
            l_mutualFundProductParams.setProductCode("12345");
            TestDBUtility.insertWithDel(l_mutualFundProductParams);

            TestDBUtility.deleteAll(InstitutionParams.TYPE);
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            l_institutionParams.setInstitutionId(1234);
            l_institutionParams.setInstitutionCode("120");
            TestDBUtility.insertWithDel(l_institutionParams);

            TestDBUtility.deleteAll(ProductRow.TYPE);
            ProductParams l_productParams = TestDBUtility.getProductRow();
            l_productParams.setProductId(32768);
            TestDBUtility.insertWithDel(l_productParams);

            WEB3GentradeTradingTimeManagementForMock.mockGetBizDateType(
                new Timestamp(WEB3DateUtility.getDate("20081010", "yyyyMMdd").getTime()),
                "1");

            WEB3GentradeTradingTimeManagementForMock.mockGetBizDateType(
                new Timestamp(WEB3DateUtility.getDate("20081009", "yyyyMMdd").getTime()),
                "1");

            WEB3GentradeTradingTimeManagementForMock.mockGetBizDateType(
                new Timestamp(WEB3DateUtility.getDate("20081008", "yyyyMMdd").getTime()),
                "1");

            l_response = (WEB3AdminMutualTPAdjustConfirmResponse)l_serviceImpl.validateTPAdjust(l_request);

            assertNotNull(l_response);
        }
        catch (Exception l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }

        log.exiting(STR_METHOD_NAME);
    }

    public void testSubmitTPAdjust_C0001()
    {
        final String STR_METHOD_NAME = "testSubmitTPAdjust_C0001()";
        log.entering(STR_METHOD_NAME);

        WEB3AdminMutualTPAdjustCompleteRequest l_request =
            new WEB3AdminMutualTPAdjustCompleteRequest();
        l_request.accountCode = "123";
        l_request.mutualProductCode = null;

        try
        {
            l_serviceImpl.submitTPAdjust(l_request);
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            assertEquals(l_ex.getErrorInfo(), WEB3ErrorCatalog.BUSINESS_ERROR_01252);
        }
        catch (Exception l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }

        log.exiting(STR_METHOD_NAME);
    }

    public void testSubmitTPAdjust_C0002()
    {
        final String STR_METHOD_NAME = "testSubmitTPAdjust_C0002()";
        log.entering(STR_METHOD_NAME);

        WEB3AdminMutualTPAdjustCompleteRequest l_request =
            new WEB3AdminMutualTPAdjustCompleteRequest();
        l_request.accountCode = "123";
        l_request.mutualProductCode = "12345";
        l_request.settlePrice = "120";
        l_request.orderBizDate = WEB3DateUtility.getDate("20081010", "yyyyMMdd");
        l_request.executionTimestamp = WEB3DateUtility.getDate("20081009", "yyyyMMdd");
        l_request.deliveryDate = WEB3DateUtility.getDate("20081008", "yyyyMMdd");
        l_request.password = "123";

        try
        {
            AdministratorParams l_administratorParams =
                TestDBUtility.getAdministratorRow();
            l_administratorParams.setBranchCode("123");
            WEB3Administrator l_administrator = new WEB3Administrator(l_administratorParams);
            WEB3AdministratorForMock.mockGetInstanceFromLoginInfo(l_administrator);

            TestDBUtility.deleteAll(AdminPermissionRow.TYPE);
            AdminPermissionParams l_adminPermissionParams =
                TestDBUtility.getAdminPermissionRow();
            l_adminPermissionParams.setInstitutionCode("0D");
            l_adminPermissionParams.setPermissionLevel("331");
            l_adminPermissionParams.setTransactionCategory("C0205");
            l_adminPermissionParams.setUpdatePermissionFlag(BooleanEnum.TRUE);
            TestDBUtility.insertWithDel(l_adminPermissionParams);

            WEB3AdministratorForMock.mockValidateTradingPassword("123", true);

            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountCode("123");
            l_mainAccountParams.setBranchCode("1234");
            l_mainAccountParams.setInstitutionCode("0D");
            TestDBUtility.insertWithDel(l_mainAccountParams);

            l_serviceImpl.submitTPAdjust(l_request);
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            assertEquals(l_ex.getErrorInfo(), WEB3ErrorCatalog.BUSINESS_ERROR_01035);
        }
        catch (Exception l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }

        log.exiting(STR_METHOD_NAME);
    }

    public void testSubmitTPAdjust_C0003()
    {
        final String STR_METHOD_NAME = "testSubmitTPAdjust_C0003()";
        log.entering(STR_METHOD_NAME);

        WEB3AdminMutualTPAdjustCompleteRequest l_request =
            new WEB3AdminMutualTPAdjustCompleteRequest();
        l_request.accountCode = "123";
        l_request.mutualProductCode = "12345";
        l_request.settlePrice = "120";
        l_request.orderBizDate = WEB3DateUtility.getDate("20081010", "yyyyMMdd");
        l_request.executionTimestamp = WEB3DateUtility.getDate("20081009", "yyyyMMdd");
        l_request.deliveryDate = WEB3DateUtility.getDate("20081008", "yyyyMMdd");

        try
        {
            AdministratorParams l_administratorParams =
                TestDBUtility.getAdministratorRow();
            l_administratorParams.setBranchCode("123");
            WEB3Administrator l_administrator = new WEB3Administrator(l_administratorParams);
            WEB3AdministratorForMock.mockGetInstanceFromLoginInfo(l_administrator);

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.gentrade.WEB3GentradeMainAccount",
                "isMarginAccountEstablished",
                new Class[] {String.class},
                new Boolean(true));

            TestDBUtility.deleteAll(AdminPermissionRow.TYPE);
            AdminPermissionParams l_adminPermissionParams =
                TestDBUtility.getAdminPermissionRow();
            l_adminPermissionParams.setInstitutionCode("0D");
            l_adminPermissionParams.setPermissionLevel("331");
            l_adminPermissionParams.setTransactionCategory("C0205");
            l_adminPermissionParams.setUpdatePermissionFlag(BooleanEnum.TRUE);
            TestDBUtility.insertWithDel(l_adminPermissionParams);

            WEB3AdministratorForMock.mockValidateTradingPassword("123", true);

            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountCode("123");
            l_mainAccountParams.setBranchCode("123");
            l_mainAccountParams.setInstitutionCode("0D");
            l_mainAccountParams.setAccountId(12345);
            TestDBUtility.insertWithDel(l_mainAccountParams);

            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setAccountId(12345);
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
            TestDBUtility.insertWithDel(l_subAccountParams);

            l_serviceImpl.submitTPAdjust(l_request);
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

    public void testSubmitTPAdjust_C0004()
    {
        final String STR_METHOD_NAME = "testSubmitTPAdjust_C0004()";
        log.entering(STR_METHOD_NAME);

        WEB3AdminMutualTPAdjustCompleteRequest l_request =
            new WEB3AdminMutualTPAdjustCompleteRequest();
        l_request.accountCode = "123";
        l_request.mutualProductCode = "12345";
        l_request.settlePrice = "120";
        l_request.orderBizDate = WEB3DateUtility.getDate("20081010", "yyyyMMdd");
        l_request.executionTimestamp = WEB3DateUtility.getDate("20081009", "yyyyMMdd");
        l_request.deliveryDate = WEB3DateUtility.getDate("20081008", "yyyyMMdd");

        try
        {
            AdministratorParams l_administratorParams =
                TestDBUtility.getAdministratorRow();
            l_administratorParams.setBranchCode("123");
            WEB3Administrator l_administrator = new WEB3Administrator(l_administratorParams);
            WEB3AdministratorForMock.mockGetInstanceFromLoginInfo(l_administrator);

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.gentrade.WEB3GentradeMainAccount",
                "isMarginAccountEstablished",
                new Class[] {String.class},
                new Boolean(false));

            TestDBUtility.deleteAll(AdminPermissionRow.TYPE);
            AdminPermissionParams l_adminPermissionParams =
                TestDBUtility.getAdminPermissionRow();
            l_adminPermissionParams.setInstitutionCode("0D");
            l_adminPermissionParams.setPermissionLevel("331");
            l_adminPermissionParams.setTransactionCategory("C0205");
            l_adminPermissionParams.setUpdatePermissionFlag(BooleanEnum.TRUE);
            TestDBUtility.insertWithDel(l_adminPermissionParams);

            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountCode("123");
            l_mainAccountParams.setBranchCode("123");
            l_mainAccountParams.setInstitutionCode("0D");
            l_mainAccountParams.setAccountId(12345);
            TestDBUtility.insertWithDel(l_mainAccountParams);

            WEB3AdministratorForMock.mockValidateTradingPassword("123", true);

            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setAccountId(12345);
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_MARGIN_SUB_ACCOUNT);
            TestDBUtility.insertWithDel(l_subAccountParams);

            l_serviceImpl.submitTPAdjust(l_request);
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

    public void testSubmitTPAdjust_C0005()
    {
        final String STR_METHOD_NAME = "testSubmitTPAdjust_C0005()";
        log.entering(STR_METHOD_NAME);

        WEB3AdminMutualTPAdjustCompleteRequest l_request =
            new WEB3AdminMutualTPAdjustCompleteRequest();
        l_request.accountCode = "123";
        l_request.mutualProductCode = "12345";
        l_request.settlePrice = "120";
        l_request.orderBizDate = WEB3DateUtility.getDate("20081010", "yyyyMMdd");
        l_request.executionTimestamp = WEB3DateUtility.getDate("20081009", "yyyyMMdd");
        l_request.deliveryDate = WEB3DateUtility.getDate("20081008", "yyyyMMdd");

        try
        {
            AdministratorParams l_administratorParams =
                TestDBUtility.getAdministratorRow();
            l_administratorParams.setBranchCode("123");
            l_administratorParams.setInstitutionId(1230);
            WEB3Administrator l_administrator = new WEB3Administrator(l_administratorParams);
            WEB3AdministratorForMock.mockGetInstanceFromLoginInfo(l_administrator);

            TestDBUtility.deleteAll(InstitutionParams.TYPE);
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            l_institutionParams.setInstitutionId(1230);
            TestDBUtility.insertWithDel(l_institutionParams);

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.gentrade.WEB3GentradeMainAccount",
                "isMarginAccountEstablished",
                new Class[] {String.class},
                new Boolean(true));

            TestDBUtility.deleteAll(AdminPermissionRow.TYPE);
            AdminPermissionParams l_adminPermissionParams =
                TestDBUtility.getAdminPermissionRow();
            l_adminPermissionParams.setInstitutionCode("0D");
            l_adminPermissionParams.setPermissionLevel("331");
            l_adminPermissionParams.setTransactionCategory("C0205");
            l_adminPermissionParams.setUpdatePermissionFlag(BooleanEnum.TRUE);
            TestDBUtility.insertWithDel(l_adminPermissionParams);

            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountCode("123");
            l_mainAccountParams.setBranchCode("123");
            l_mainAccountParams.setInstitutionCode("0D");
            l_mainAccountParams.setAccountId(12345);
            TestDBUtility.insertWithDel(l_mainAccountParams);

            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setAccountId(12345);
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_MARGIN_SUB_ACCOUNT);
            TestDBUtility.insertWithDel(l_subAccountParams);

            WEB3AdministratorForMock.mockValidateTradingPassword("123", true);

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.gentrade.WEB3GentradeAccountManager",
                "lockAccount",
                new Class[] {String.class,String.class,String.class},
                null);

            TestDBUtility.deleteAll(MutualFundProductRow.TYPE);
            MutualFundProductParams l_mutualFundProductParams =
                TestDBUtility.getMutualFundProductRow();
            l_mutualFundProductParams.setInstitutionCode("0D");
            l_mutualFundProductParams.setProductCode("12345");
            l_mutualFundProductParams.setProductIssueCode("1");
            TestDBUtility.insertWithDel(l_mutualFundProductParams);

            l_serviceImpl.submitTPAdjust(l_request);
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            assertEquals(l_ex.getErrorInfo(), WEB3ErrorCatalog.BUSINESS_ERROR_00391);
        }
        catch (Exception l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }

        log.exiting(STR_METHOD_NAME);
    }

    public void testSubmitTPAdjust_C0006()
    {
        final String STR_METHOD_NAME = "testSubmitTPAdjust_C0006()";
        log.entering(STR_METHOD_NAME);

        WEB3AdminMutualTPAdjustCompleteRequest l_request =
            new WEB3AdminMutualTPAdjustCompleteRequest();
        l_request.accountCode = "123";
        l_request.mutualProductCode = "12345";
        l_request.settlePrice = "120";
        l_request.orderBizDate = WEB3DateUtility.getDate("20081010", "yyyyMMdd");
        l_request.executionTimestamp = WEB3DateUtility.getDate("20081009", "yyyyMMdd");
        l_request.deliveryDate = WEB3DateUtility.getDate("20081008", "yyyyMMdd");

        try
        {
            AdministratorParams l_administratorParams =
                TestDBUtility.getAdministratorRow();
            l_administratorParams.setBranchCode("123");
            l_administratorParams.setInstitutionId(1230);
            WEB3Administrator l_administrator = new WEB3Administrator(l_administratorParams);
            WEB3AdministratorForMock.mockGetInstanceFromLoginInfo(l_administrator);

            TestDBUtility.deleteAll(InstitutionParams.TYPE);
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            l_institutionParams.setInstitutionId(1230);
            TestDBUtility.insertWithDel(l_institutionParams);

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.gentrade.WEB3GentradeMainAccount",
                "isMarginAccountEstablished",
                new Class[] {String.class},
                new Boolean(true));

            TestDBUtility.deleteAll(AdminPermissionRow.TYPE);
            AdminPermissionParams l_adminPermissionParams =
                TestDBUtility.getAdminPermissionRow();
            l_adminPermissionParams.setInstitutionCode("0D");
            l_adminPermissionParams.setPermissionLevel("331");
            l_adminPermissionParams.setTransactionCategory("C0205");
            l_adminPermissionParams.setUpdatePermissionFlag(BooleanEnum.TRUE);
            TestDBUtility.insertWithDel(l_adminPermissionParams);

            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountCode("123");
            l_mainAccountParams.setBranchCode("123");
            l_mainAccountParams.setInstitutionCode("0D");
            l_mainAccountParams.setAccountId(12345);
            TestDBUtility.insertWithDel(l_mainAccountParams);

            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setAccountId(12345);
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_MARGIN_SUB_ACCOUNT);
            TestDBUtility.insertWithDel(l_subAccountParams);

            WEB3AdministratorForMock.mockValidateTradingPassword("123", true);

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.gentrade.WEB3GentradeAccountManager",
                "lockAccount",
                new Class[] {String.class,String.class,String.class},
                null);

            TestDBUtility.deleteAll(MutualFundProductRow.TYPE);
            MutualFundProductParams l_mutualFundProductParams =
                TestDBUtility.getMutualFundProductRow();
            l_mutualFundProductParams.setInstitutionCode("0D");
            l_mutualFundProductParams.setProductCode("12345");
            l_mutualFundProductParams.setProductIssueCode("0");
            TestDBUtility.insertWithDel(l_mutualFundProductParams);

            WEB3GentradeTradingTimeManagementForMock.mockGetBizDateType(
                new Timestamp(WEB3DateUtility.getDate("20081010", "yyyyMMdd").getTime()),
                "0");

            l_serviceImpl.submitTPAdjust(l_request);
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            assertEquals(l_ex.getErrorInfo(), WEB3ErrorCatalog.BUSINESS_ERROR_02019);
        }
        catch (Exception l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }

        log.exiting(STR_METHOD_NAME);
    }

    public void testSubmitTPAdjust_C0007()
    {
        final String STR_METHOD_NAME = "testSubmitTPAdjust_C0007()";
        log.entering(STR_METHOD_NAME);

        WEB3AdminMutualTPAdjustCompleteRequest l_request =
            new WEB3AdminMutualTPAdjustCompleteRequest();
        l_request.accountCode = "123";
        l_request.mutualProductCode = "12345";
        l_request.settlePrice = "120";
        l_request.orderBizDate = WEB3DateUtility.getDate("20081010", "yyyyMMdd");
        l_request.executionTimestamp = WEB3DateUtility.getDate("20081009", "yyyyMMdd");
        l_request.deliveryDate = WEB3DateUtility.getDate("20081008", "yyyyMMdd");

        try
        {
            AdministratorParams l_administratorParams =
                TestDBUtility.getAdministratorRow();
            l_administratorParams.setBranchCode("123");
            l_administratorParams.setInstitutionId(1230);
            WEB3Administrator l_administrator = new WEB3Administrator(l_administratorParams);
            WEB3AdministratorForMock.mockGetInstanceFromLoginInfo(l_administrator);

            TestDBUtility.deleteAll(InstitutionParams.TYPE);
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            l_institutionParams.setInstitutionId(1230);
            TestDBUtility.insertWithDel(l_institutionParams);

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.gentrade.WEB3GentradeMainAccount",
                "isMarginAccountEstablished",
                new Class[] {String.class},
                new Boolean(true));

            TestDBUtility.deleteAll(AdminPermissionRow.TYPE);
            AdminPermissionParams l_adminPermissionParams =
                TestDBUtility.getAdminPermissionRow();
            l_adminPermissionParams.setInstitutionCode("0D");
            l_adminPermissionParams.setPermissionLevel("331");
            l_adminPermissionParams.setTransactionCategory("C0205");
            l_adminPermissionParams.setUpdatePermissionFlag(BooleanEnum.TRUE);
            TestDBUtility.insertWithDel(l_adminPermissionParams);

            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountCode("123");
            l_mainAccountParams.setBranchCode("123");
            l_mainAccountParams.setInstitutionCode("0D");
            l_mainAccountParams.setAccountId(12345);
            TestDBUtility.insertWithDel(l_mainAccountParams);

            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setAccountId(12345);
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_MARGIN_SUB_ACCOUNT);
            TestDBUtility.insertWithDel(l_subAccountParams);

            WEB3AdministratorForMock.mockValidateTradingPassword("123", true);

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.gentrade.WEB3GentradeAccountManager",
                "lockAccount",
                new Class[] {String.class,String.class,String.class},
                null);

            TestDBUtility.deleteAll(MutualFundProductRow.TYPE);
            MutualFundProductParams l_mutualFundProductParams =
                TestDBUtility.getMutualFundProductRow();
            l_mutualFundProductParams.setInstitutionCode("0D");
            l_mutualFundProductParams.setProductCode("12345");
            l_mutualFundProductParams.setProductIssueCode("0");
            TestDBUtility.insertWithDel(l_mutualFundProductParams);

            WEB3GentradeTradingTimeManagementForMock.mockGetBizDateType(
                new Timestamp(WEB3DateUtility.getDate("20081010", "yyyyMMdd").getTime()),
                "1");

            WEB3GentradeTradingTimeManagementForMock.mockGetBizDateType(
                new Timestamp(WEB3DateUtility.getDate("20081009", "yyyyMMdd").getTime()),
                "0");

            l_serviceImpl.submitTPAdjust(l_request);
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            assertEquals(l_ex.getErrorInfo(), WEB3ErrorCatalog.BUSINESS_ERROR_02149);
        }
        catch (Exception l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }

        log.exiting(STR_METHOD_NAME);
    }

    public void testSubmitTPAdjust_C0008()
    {
        final String STR_METHOD_NAME = "testSubmitTPAdjust_C0008()";
        log.entering(STR_METHOD_NAME);

        WEB3AdminMutualTPAdjustCompleteRequest l_request =
            new WEB3AdminMutualTPAdjustCompleteRequest();
        l_request.accountCode = "123";
        l_request.mutualProductCode = "12345";
        l_request.settlePrice = "120";
        l_request.orderBizDate = WEB3DateUtility.getDate("20081010", "yyyyMMdd");
        l_request.executionTimestamp = WEB3DateUtility.getDate("20081009", "yyyyMMdd");
        l_request.deliveryDate = WEB3DateUtility.getDate("20081008", "yyyyMMdd");

        try
        {
            AdministratorParams l_administratorParams =
                TestDBUtility.getAdministratorRow();
            l_administratorParams.setBranchCode("123");
            l_administratorParams.setInstitutionId(1230);
            WEB3Administrator l_administrator = new WEB3Administrator(l_administratorParams);
            WEB3AdministratorForMock.mockGetInstanceFromLoginInfo(l_administrator);

            TestDBUtility.deleteAll(InstitutionParams.TYPE);
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            l_institutionParams.setInstitutionId(1230);
            TestDBUtility.insertWithDel(l_institutionParams);

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.gentrade.WEB3GentradeMainAccount",
                "isMarginAccountEstablished",
                new Class[] {String.class},
                new Boolean(true));

            TestDBUtility.deleteAll(AdminPermissionRow.TYPE);
            AdminPermissionParams l_adminPermissionParams =
                TestDBUtility.getAdminPermissionRow();
            l_adminPermissionParams.setInstitutionCode("0D");
            l_adminPermissionParams.setPermissionLevel("331");
            l_adminPermissionParams.setTransactionCategory("C0205");
            l_adminPermissionParams.setUpdatePermissionFlag(BooleanEnum.TRUE);
            TestDBUtility.insertWithDel(l_adminPermissionParams);

            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountCode("123");
            l_mainAccountParams.setBranchCode("123");
            l_mainAccountParams.setInstitutionCode("0D");
            l_mainAccountParams.setAccountId(12345);
            TestDBUtility.insertWithDel(l_mainAccountParams);

            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setAccountId(12345);
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_MARGIN_SUB_ACCOUNT);
            TestDBUtility.insertWithDel(l_subAccountParams);

            WEB3AdministratorForMock.mockValidateTradingPassword("123", true);

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.gentrade.WEB3GentradeAccountManager",
                "lockAccount",
                new Class[] {String.class,String.class,String.class},
                null);

            TestDBUtility.deleteAll(MutualFundProductRow.TYPE);
            MutualFundProductParams l_mutualFundProductParams =
                TestDBUtility.getMutualFundProductRow();
            l_mutualFundProductParams.setInstitutionCode("0D");
            l_mutualFundProductParams.setProductCode("12345");
            l_mutualFundProductParams.setProductIssueCode("0");
            TestDBUtility.insertWithDel(l_mutualFundProductParams);

            WEB3GentradeTradingTimeManagementForMock.mockGetBizDateType(
                new Timestamp(WEB3DateUtility.getDate("20081010", "yyyyMMdd").getTime()),
                "1");

            WEB3GentradeTradingTimeManagementForMock.mockGetBizDateType(
                new Timestamp(WEB3DateUtility.getDate("20081009", "yyyyMMdd").getTime()),
                "1");

            WEB3GentradeTradingTimeManagementForMock.mockGetBizDateType(
                new Timestamp(WEB3DateUtility.getDate("20081008", "yyyyMMdd").getTime()),
                "0");

            l_serviceImpl.submitTPAdjust(l_request);
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            assertEquals(l_ex.getErrorInfo(), WEB3ErrorCatalog.BUSINESS_ERROR_02336);
        }
        catch (Exception l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }

        log.exiting(STR_METHOD_NAME);
    }

    public void testSubmitTPAdjust_C0009()
    {
        final String STR_METHOD_NAME = "testSubmitTPAdjust_C0009()";
        log.entering(STR_METHOD_NAME);

        WEB3AdminMutualTPAdjustCompleteRequest l_request =
            new WEB3AdminMutualTPAdjustCompleteRequest();
        l_request.accountCode = "123";
        l_request.mutualProductCode = "12345";
        l_request.settlePrice = "120";
        l_request.orderBizDate = WEB3DateUtility.getDate("20081010", "yyyyMMdd");
        l_request.executionTimestamp = WEB3DateUtility.getDate("20081009", "yyyyMMdd");
        l_request.deliveryDate = WEB3DateUtility.getDate("20080608", "yyyyMMdd");

        try
        {
            AdministratorParams l_administratorParams =
                TestDBUtility.getAdministratorRow();
            l_administratorParams.setBranchCode("123");
            l_administratorParams.setInstitutionId(1230);
            WEB3Administrator l_administrator = new WEB3Administrator(l_administratorParams);
            WEB3AdministratorForMock.mockGetInstanceFromLoginInfo(l_administrator);

            TestDBUtility.deleteAll(InstitutionParams.TYPE);
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            l_institutionParams.setInstitutionId(1230);
            TestDBUtility.insertWithDel(l_institutionParams);

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.gentrade.WEB3GentradeMainAccount",
                "isMarginAccountEstablished",
                new Class[] {String.class},
                new Boolean(true));

            TestDBUtility.deleteAll(AdminPermissionRow.TYPE);
            AdminPermissionParams l_adminPermissionParams =
                TestDBUtility.getAdminPermissionRow();
            l_adminPermissionParams.setInstitutionCode("0D");
            l_adminPermissionParams.setPermissionLevel("331");
            l_adminPermissionParams.setTransactionCategory("C0205");
            l_adminPermissionParams.setUpdatePermissionFlag(BooleanEnum.TRUE);
            TestDBUtility.insertWithDel(l_adminPermissionParams);

            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountCode("123");
            l_mainAccountParams.setBranchCode("123");
            l_mainAccountParams.setInstitutionCode("0D");
            l_mainAccountParams.setAccountId(12345);
            TestDBUtility.insertWithDel(l_mainAccountParams);

            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setAccountId(12345);
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_MARGIN_SUB_ACCOUNT);
            TestDBUtility.insertWithDel(l_subAccountParams);

            WEB3AdministratorForMock.mockValidateTradingPassword("123", true);

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.gentrade.WEB3GentradeAccountManager",
                "lockAccount",
                new Class[] {String.class,String.class,String.class},
                null);

            TestDBUtility.deleteAll(MutualFundProductRow.TYPE);
            MutualFundProductParams l_mutualFundProductParams =
                TestDBUtility.getMutualFundProductRow();
            l_mutualFundProductParams.setInstitutionCode("0D");
            l_mutualFundProductParams.setProductCode("12345");
            l_mutualFundProductParams.setProductIssueCode("0");
            TestDBUtility.insertWithDel(l_mutualFundProductParams);

            WEB3GentradeTradingTimeManagementForMock.mockGetBizDateType(
                new Timestamp(WEB3DateUtility.getDate("20081010", "yyyyMMdd").getTime()),
                "1");

            WEB3GentradeTradingTimeManagementForMock.mockGetBizDateType(
                new Timestamp(WEB3DateUtility.getDate("20081009", "yyyyMMdd").getTime()),
                "1");

            WEB3GentradeTradingTimeManagementForMock.mockGetBizDateType(
                new Timestamp(WEB3DateUtility.getDate("20080608", "yyyyMMdd").getTime()),
                "1");

            l_serviceImpl.submitTPAdjust(l_request);
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            assertEquals(l_ex.getErrorInfo(), WEB3ErrorCatalog.BUSINESS_ERROR_02347);
        }
        catch (Exception l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }

        log.exiting(STR_METHOD_NAME);
    }

    public void testSubmitTPAdjust_C0010()
    {
        final String STR_METHOD_NAME = "testSubmitTPAdjust_C0010()";
        log.entering(STR_METHOD_NAME);

        WEB3AdminMutualTPAdjustCompleteRequest l_request =
            new WEB3AdminMutualTPAdjustCompleteRequest();
        l_request.accountCode = "123";
        l_request.mutualProductCode = "12345";
        l_request.settlePrice = "120";
        l_request.orderBizDate = WEB3DateUtility.getDate("20081010", "yyyyMMdd");
        l_request.executionTimestamp = WEB3DateUtility.getDate("20081009", "yyyyMMdd");
        l_request.deliveryDate = WEB3DateUtility.getDate("20081008", "yyyyMMdd");

        WEB3AdminMutualTPAdjustCompleteResponse l_response = null;
        try
        {
            AdministratorParams l_administratorParams =
                TestDBUtility.getAdministratorRow();
            l_administratorParams.setBranchCode("123");
            l_administratorParams.setInstitutionId(1230);
            WEB3Administrator l_administrator = new WEB3Administrator(l_administratorParams);
            WEB3AdministratorForMock.mockGetInstanceFromLoginInfo(l_administrator);

            LoginInfo l_loginInfo = new LoginInfoImpl();
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getLoginInfo",
                new Class[] {},
                l_loginInfo);

            TestDBUtility.deleteAll(InstitutionRow.TYPE);
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            l_institutionParams.setInstitutionId(1230);
            TestDBUtility.insertWithDel(l_institutionParams);

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.gentrade.WEB3GentradeMainAccount",
                "isMarginAccountEstablished",
                new Class[] {String.class},
                new Boolean(true));

            TestDBUtility.deleteAll(AdminPermissionRow.TYPE);
            AdminPermissionParams l_adminPermissionParams =
                TestDBUtility.getAdminPermissionRow();
            l_adminPermissionParams.setInstitutionCode("0D");
            l_adminPermissionParams.setPermissionLevel("331");
            l_adminPermissionParams.setTransactionCategory("C0205");
            l_adminPermissionParams.setUpdatePermissionFlag(BooleanEnum.TRUE);
            TestDBUtility.insertWithDel(l_adminPermissionParams);

            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountCode("123");
            l_mainAccountParams.setBranchCode("123");
            l_mainAccountParams.setInstitutionCode("0D");
            l_mainAccountParams.setAccountId(12345);
            TestDBUtility.insertWithDel(l_mainAccountParams);

            TestDBUtility.deleteAll(ProductRow.TYPE);
            ProductParams l_productParams = TestDBUtility.getProductRow();
            l_productParams.setProductId(32768);
            TestDBUtility.insertWithDel(l_productParams);

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.tradingpower.WEB3TPTradingPowerServiceImpl",
                "reCalcTradingPower",
                new Class[] {WEB3GentradeSubAccount.class},
                null);

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.gentrade.WEB3GentradeAccountManager",
                "lockAccount",
                new Class[] {String.class,String.class,String.class},
                null);

            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setAccountId(12345);
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_MARGIN_SUB_ACCOUNT);
            TestDBUtility.insertWithDel(l_subAccountParams);

            WEB3AdministratorForMock.mockValidateTradingPassword("123", true);

            TestDBUtility.deleteAll(MutualFundProductRow.TYPE);
            MutualFundProductParams l_mutualFundProductParams =
                TestDBUtility.getMutualFundProductRow();
            l_mutualFundProductParams.setInstitutionCode("0D");
            l_mutualFundProductParams.setProductCode("12345");
            l_mutualFundProductParams.setProductIssueCode("0");
            l_mutualFundProductParams.setConstantValueAppDate(WEB3DateUtility.getDate("20080810", "yyyyMMdd"));
            TestDBUtility.insertWithDel(l_mutualFundProductParams);

            WEB3GentradeTradingTimeManagementForMock.mockGetBizDateType(
                new Timestamp(WEB3DateUtility.getDate("20081010", "yyyyMMdd").getTime()),
                "1");

            WEB3GentradeTradingTimeManagementForMock.mockGetBizDateType(
                new Timestamp(WEB3DateUtility.getDate("20081009", "yyyyMMdd").getTime()),
                "1");

            WEB3GentradeTradingTimeManagementForMock.mockGetBizDateType(
                new Timestamp(WEB3DateUtility.getDate("20081008", "yyyyMMdd").getTime()),
                "1");

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.gentrade.WEB3GentradeMainAccount",
                "isSpecialAccountEstablished",
                new Class[] {SubAccount.class},
                new Boolean(true));

            ProcessingResult l_processingResult =
                ProcessingResult.newSuccessResultInstance();
            OrderSubmissionResult l_orderSubmissionResult =
                new OrderSubmissionResult(l_processingResult);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.mf.WEB3MutualFundOrderManager",
                "submitNewOrder",
                new Class[] {
                    SubAccount.class,
                    ProductTypeEnum.class,
                    NewOrderSpec.class,
                    long.class,
                    String.class,
                    boolean.class},
                    l_orderSubmissionResult);

            l_response = l_serviceImpl.submitTPAdjust(l_request);

            assertNotNull(l_response);
        }
        catch (Exception l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }

        log.exiting(STR_METHOD_NAME);
    }

    public void testSubmitTPAdjust_C0011()
    {
        final String STR_METHOD_NAME = "testSubmitTPAdjust_C0011()";
        log.entering(STR_METHOD_NAME);

        WEB3AdminMutualTPAdjustCompleteRequest l_request =
            new WEB3AdminMutualTPAdjustCompleteRequest();
        l_request.accountCode = "123";
        l_request.mutualProductCode = "12345";
        l_request.settlePrice = "120";
        l_request.orderBizDate = WEB3DateUtility.getDate("20081010", "yyyyMMdd");
        l_request.executionTimestamp = WEB3DateUtility.getDate("20081009", "yyyyMMdd");
        l_request.deliveryDate = WEB3DateUtility.getDate("20081008", "yyyyMMdd");

        WEB3AdminMutualTPAdjustCompleteResponse l_response = null;
        try
        {
            AdministratorParams l_administratorParams =
                TestDBUtility.getAdministratorRow();
            l_administratorParams.setBranchCode("123");
            l_administratorParams.setInstitutionId(1230);
            WEB3Administrator l_administrator = new WEB3Administrator(l_administratorParams);
            WEB3AdministratorForMock.mockGetInstanceFromLoginInfo(l_administrator);

            TestDBUtility.deleteAll(InstitutionParams.TYPE);
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            l_institutionParams.setInstitutionId(1230);
            TestDBUtility.insertWithDel(l_institutionParams);

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.gentrade.WEB3GentradeMainAccount",
                "isMarginAccountEstablished",
                new Class[] {String.class},
                new Boolean(true));

            TestDBUtility.deleteAll(AdminPermissionRow.TYPE);
            AdminPermissionParams l_adminPermissionParams =
                TestDBUtility.getAdminPermissionRow();
            l_adminPermissionParams.setInstitutionCode("0D");
            l_adminPermissionParams.setPermissionLevel("331");
            l_adminPermissionParams.setTransactionCategory("C0205");
            l_adminPermissionParams.setUpdatePermissionFlag(BooleanEnum.TRUE);
            TestDBUtility.insertWithDel(l_adminPermissionParams);

            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountCode("123");
            l_mainAccountParams.setBranchCode("123");
            l_mainAccountParams.setInstitutionCode("0D");
            l_mainAccountParams.setAccountId(12345);
            TestDBUtility.insertWithDel(l_mainAccountParams);

            TestDBUtility.deleteAll(ProductRow.TYPE);
            ProductParams l_productParams = TestDBUtility.getProductRow();
            l_productParams.setProductId(32768);
            TestDBUtility.insertWithDel(l_productParams);

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.tradingpower.WEB3TPTradingPowerServiceImpl",
                "reCalcTradingPower",
                new Class[] {WEB3GentradeSubAccount.class},
                null);

            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setAccountId(12345);
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_MARGIN_SUB_ACCOUNT);
            TestDBUtility.insertWithDel(l_subAccountParams);

            WEB3AdministratorForMock.mockValidateTradingPassword("123", true);

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.gentrade.WEB3GentradeAccountManager",
                "lockAccount",
                new Class[] {String.class,String.class,String.class},
                null);

            TestDBUtility.deleteAll(MutualFundProductRow.TYPE);
            MutualFundProductParams l_mutualFundProductParams =
                TestDBUtility.getMutualFundProductRow();
            l_mutualFundProductParams.setInstitutionCode("0D");
            l_mutualFundProductParams.setProductCode("12345");
            l_mutualFundProductParams.setProductIssueCode("0");
            l_mutualFundProductParams.setConstantValueAppDate(WEB3DateUtility.getDate("20080810", "yyyyMMdd"));
            TestDBUtility.insertWithDel(l_mutualFundProductParams);

            WEB3GentradeTradingTimeManagementForMock.mockGetBizDateType(
                new Timestamp(WEB3DateUtility.getDate("20081010", "yyyyMMdd").getTime()),
                "1");

            WEB3GentradeTradingTimeManagementForMock.mockGetBizDateType(
                new Timestamp(WEB3DateUtility.getDate("20081009", "yyyyMMdd").getTime()),
                "1");

            WEB3GentradeTradingTimeManagementForMock.mockGetBizDateType(
                new Timestamp(WEB3DateUtility.getDate("20081008", "yyyyMMdd").getTime()),
                "1");

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.gentrade.WEB3GentradeMainAccount",
                "isSpecialAccountEstablished",
                new Class[] {SubAccount.class},
                new Boolean(true));

            ProcessingResult l_processingResult =
                ProcessingResult.newSuccessResultInstance();
            OrderSubmissionResult l_orderSubmissionResult =
                new OrderSubmissionResult(l_processingResult);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.mf.WEB3MutualFundOrderManager",
                "submitNewOrder",
                new Class[] {
                    SubAccount.class,
                    ProductTypeEnum.class,
                    NewOrderSpec.class,
                    long.class,
                    String.class,
                    boolean.class},
                    l_orderSubmissionResult);

            l_response = l_serviceImpl.submitTPAdjust(l_request);

            assertNotNull(l_response);
        }
        catch (Exception l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }

        log.exiting(STR_METHOD_NAME);
    }

    public void testSubmitTPAdjust_C0012()
    {
        final String STR_METHOD_NAME = "testSubmitTPAdjust_C0012()";
        log.entering(STR_METHOD_NAME);

        WEB3AdminMutualTPAdjustCompleteRequest l_request =
            new WEB3AdminMutualTPAdjustCompleteRequest();
        l_request.accountCode = "123";
        l_request.mutualProductCode = "12345";
        l_request.settlePrice = "120";
        l_request.orderBizDate = WEB3DateUtility.getDate("20081010", "yyyyMMdd");
        l_request.executionTimestamp = WEB3DateUtility.getDate("20081009", "yyyyMMdd");
        l_request.deliveryDate = WEB3DateUtility.getDate("20081008", "yyyyMMdd");

        try
        {
            AdministratorParams l_administratorParams =
                TestDBUtility.getAdministratorRow();
            l_administratorParams.setBranchCode("123");
            l_administratorParams.setInstitutionId(1230);
            WEB3Administrator l_administrator = new WEB3Administrator(l_administratorParams);
            WEB3AdministratorForMock.mockGetInstanceFromLoginInfo(l_administrator);

            TestDBUtility.deleteAll(InstitutionParams.TYPE);
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            l_institutionParams.setInstitutionId(1230);
            TestDBUtility.insertWithDel(l_institutionParams);

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.gentrade.WEB3GentradeMainAccount",
                "isMarginAccountEstablished",
                new Class[] {String.class},
                new Boolean(true));

            TestDBUtility.deleteAll(AdminPermissionRow.TYPE);
            AdminPermissionParams l_adminPermissionParams =
                TestDBUtility.getAdminPermissionRow();
            l_adminPermissionParams.setInstitutionCode("0D");
            l_adminPermissionParams.setPermissionLevel("331");
            l_adminPermissionParams.setTransactionCategory("C0205");
            l_adminPermissionParams.setUpdatePermissionFlag(BooleanEnum.TRUE);
            TestDBUtility.insertWithDel(l_adminPermissionParams);

            TestDBUtility.deleteAll(ProductRow.TYPE);
            ProductParams l_productParams = TestDBUtility.getProductRow();
            l_productParams.setProductId(32768);
            TestDBUtility.insertWithDel(l_productParams);

//            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
//                "webbroker3.tradingpower.WEB3TPTradingPowerServiceImpl",
//                "reCalcTradingPower",
//                new Class[] {WEB3GentradeSubAccount.class},
//                null);

            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountCode("123");
            l_mainAccountParams.setBranchCode("123");
            l_mainAccountParams.setInstitutionCode("0D");
            l_mainAccountParams.setAccountId(12345);
            TestDBUtility.insertWithDel(l_mainAccountParams);

            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setAccountId(12345);
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_MARGIN_SUB_ACCOUNT);
            TestDBUtility.insertWithDel(l_subAccountParams);

            WEB3AdministratorForMock.mockValidateTradingPassword("123", true);

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.gentrade.WEB3GentradeAccountManager",
                "lockAccount",
                new Class[] {String.class,String.class,String.class},
                null);

            TestDBUtility.deleteAll(MutualFundProductRow.TYPE);
            MutualFundProductParams l_mutualFundProductParams =
                TestDBUtility.getMutualFundProductRow();
            l_mutualFundProductParams.setInstitutionCode("0D");
            l_mutualFundProductParams.setProductCode("12345");
            l_mutualFundProductParams.setProductIssueCode("0");
            l_mutualFundProductParams.setConstantValueAppDate(WEB3DateUtility.getDate("20080810", "yyyyMMdd"));
            TestDBUtility.insertWithDel(l_mutualFundProductParams);

            WEB3GentradeTradingTimeManagementForMock.mockGetBizDateType(
                new Timestamp(WEB3DateUtility.getDate("20081010", "yyyyMMdd").getTime()),
                "1");

            WEB3GentradeTradingTimeManagementForMock.mockGetBizDateType(
                new Timestamp(WEB3DateUtility.getDate("20081009", "yyyyMMdd").getTime()),
                "1");

            WEB3GentradeTradingTimeManagementForMock.mockGetBizDateType(
                new Timestamp(WEB3DateUtility.getDate("20081008", "yyyyMMdd").getTime()),
                "1");

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.gentrade.WEB3GentradeMainAccount",
                "isSpecialAccountEstablished",
                new Class[] {SubAccount.class},
                new Boolean(true));

            ProcessingResult l_processingResult =
                ProcessingResult.newFailedResultInstance(WEB3ErrorCatalog.BUSINESS_ERROR_02347);
            OrderSubmissionResult l_orderSubmissionResult =
                new OrderSubmissionResult(l_processingResult);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.mf.WEB3MutualFundOrderManager",
                "submitNewOrder",
                new Class[] {
                    SubAccount.class,
                    ProductTypeEnum.class,
                    NewOrderSpec.class,
                    long.class,
                    String.class,
                    boolean.class},
                    l_orderSubmissionResult);

            l_serviceImpl.submitTPAdjust(l_request);
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            assertEquals(l_ex.getErrorInfo(), WEB3ErrorCatalog.BUSINESS_ERROR_00191);
        }
        catch (Exception l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }

        log.exiting(STR_METHOD_NAME);
    }
}
@
