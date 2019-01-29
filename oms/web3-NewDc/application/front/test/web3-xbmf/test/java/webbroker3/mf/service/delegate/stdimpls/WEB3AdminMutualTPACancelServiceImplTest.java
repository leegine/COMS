head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.02.09.50;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3AdminMutualTPACancelServiceImplTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.mf.service.delegate.stdimpls;

import java.util.List;

import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderOpenStatusEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccountTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.BranchParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.ProductParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.ProductRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.SubAccountParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.SubAccountRow;
import com.fitechlabs.xtrade.plugin.tc.xbmf.data.MutualFundOrderActionParams;
import com.fitechlabs.xtrade.plugin.tc.xbmf.data.MutualFundOrderActionRow;
import com.fitechlabs.xtrade.plugin.tc.xbmf.data.MutualFundOrderUnitParams;
import com.fitechlabs.xtrade.plugin.tc.xbmf.data.MutualFundOrderUnitRow;
import com.fitechlabs.xtrade.plugin.tc.xbmf.data.MutualFundProductParams;

import test.util.TestDBUtility;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3OrderRootDivDef;
import webbroker3.gentrade.WEB3Administrator;
import webbroker3.gentrade.WEB3AdministratorForMock;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.gentrade.data.AdminPermissionParams;
import webbroker3.gentrade.data.AdminPermissionRow;
import webbroker3.gentrade.data.AdministratorParams;
import webbroker3.mf.message.WEB3AdminMutualFrgncalInputRequest;
import webbroker3.mf.message.WEB3AdminMutualTPACancelCompleteRequest;
import webbroker3.mf.message.WEB3AdminMutualTPACancelCompleteResponse;
import webbroker3.mf.message.WEB3AdminMutualTPACancelListRequest;
import webbroker3.mf.message.WEB3AdminMutualTPACancelListResponse;
import webbroker3.mf.message.WEB3AdminMutualTPAdjustConfirmRequest;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

public class WEB3AdminMutualTPACancelServiceImplTest extends TestBaseForMock
{
    /**
     *  ログユーティリティ<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(
                WEB3AdminMutualTPACancelServiceImplTest.class);


    private WEB3AdminMutualTPACancelServiceImpl l_serviceImpl =
        new WEB3AdminMutualTPACancelServiceImpl();

    public WEB3AdminMutualTPACancelServiceImplTest(String name)
    {
        super(name);
    }

    protected void setUp() throws Exception
    {
        super.setUp();
        TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);
    }

    protected void tearDown() throws Exception
    {
        super.tearDown();
    }

    public void testExecute_C0001()
    {
        final String STR_METHOD_NAME = "testExecute_C0001()";
        log.entering(STR_METHOD_NAME);

        WEB3AdminMutualFrgncalInputRequest l_request = null;
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

        WEB3AdminMutualTPACancelListRequest l_request =
            new WEB3AdminMutualTPACancelListRequest();
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

        WEB3AdminMutualTPACancelCompleteRequest l_request =
            new WEB3AdminMutualTPACancelCompleteRequest();
        l_request.accountCode = "123";
        l_request.orderId = null;
        try
        {
            l_serviceImpl.execute(l_request);
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            assertEquals(l_ex.getErrorInfo(), WEB3ErrorCatalog.BUSINESS_ERROR_00600);
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

        WEB3AdminMutualTPAdjustConfirmRequest l_request =
            new WEB3AdminMutualTPAdjustConfirmRequest();
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

    public void testSearchTPACancel_C0001()
    {
        final String STR_METHOD_NAME = "testSearchTPACancel_C0001()";
        log.entering(STR_METHOD_NAME);

        WEB3AdminMutualTPACancelListRequest l_request =
            new WEB3AdminMutualTPACancelListRequest();
        l_request.accountCode = null;
        try
        {
            l_serviceImpl.searchTPACancel(l_request);
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

    public void testSearchTPACancel_C0002()
    {
        final String STR_METHOD_NAME = "testSearchTPACancel_C0002()";
        log.entering(STR_METHOD_NAME);

        WEB3AdminMutualTPACancelListRequest l_request =
            new WEB3AdminMutualTPACancelListRequest();
        l_request.accountCode = "123";
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
            l_adminPermissionParams.setUpdatePermissionFlag(BooleanEnum.FALSE);
            TestDBUtility.insertWithDel(l_adminPermissionParams);


            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountCode("123");
            l_mainAccountParams.setBranchCode("1234");
            l_mainAccountParams.setInstitutionCode("0D");
            TestDBUtility.insertWithDel(l_mainAccountParams);

            l_serviceImpl.searchTPACancel(l_request);
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

    public void testSearchTPACancel_C0003()
    {
        final String STR_METHOD_NAME = "testSearchTPACancel_C0003()";
        log.entering(STR_METHOD_NAME);

        WEB3AdminMutualTPACancelListRequest l_request =
            new WEB3AdminMutualTPACancelListRequest();
        l_request.accountCode = "123";
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
            l_adminPermissionParams.setUpdatePermissionFlag(BooleanEnum.FALSE);
            TestDBUtility.insertWithDel(l_adminPermissionParams);

            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountCode("123");
            l_mainAccountParams.setBranchCode("123");
            l_mainAccountParams.setInstitutionCode("0D");
            l_mainAccountParams.setAccountId(123456);
            TestDBUtility.insertWithDel(l_mainAccountParams);

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.gentrade.WEB3GentradeMainAccount",
                "isMarginAccountEstablished",
                new Class[] {String.class},
                new Boolean(true));

            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setSubAccountId(123456);
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
            TestDBUtility.insertWithDel(l_subAccountParams);
            l_serviceImpl.searchTPACancel(l_request);
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

    public void testSearchTPACancel_C0004()
    {
        final String STR_METHOD_NAME = "testSearchTPACancel_C0004()";
        log.entering(STR_METHOD_NAME);

        WEB3AdminMutualTPACancelListRequest l_request =
            new WEB3AdminMutualTPACancelListRequest();
        l_request.accountCode = "123";
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
            l_adminPermissionParams.setUpdatePermissionFlag(BooleanEnum.FALSE);
            TestDBUtility.insertWithDel(l_adminPermissionParams);

            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountCode("123");
            l_mainAccountParams.setBranchCode("123");
            l_mainAccountParams.setInstitutionCode("0D");
            l_mainAccountParams.setAccountId(123456);
            TestDBUtility.insertWithDel(l_mainAccountParams);

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.gentrade.WEB3GentradeMainAccount",
                "isMarginAccountEstablished",
                new Class[] {String.class},
                new Boolean(false));

            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setSubAccountId(123456);
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_MARGIN_SUB_ACCOUNT);
            TestDBUtility.insertWithDel(l_subAccountParams);
            l_serviceImpl.searchTPACancel(l_request);
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

    public void testSearchTPACancel_C0005()
    {
        final String STR_METHOD_NAME = "testSearchTPACancel_C0005()";
        log.entering(STR_METHOD_NAME);

        WEB3AdminMutualTPACancelListRequest l_request =
            new WEB3AdminMutualTPACancelListRequest();
        l_request.accountCode = "123";
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
            l_adminPermissionParams.setUpdatePermissionFlag(BooleanEnum.FALSE);
            TestDBUtility.insertWithDel(l_adminPermissionParams);

            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountCode("123");
            l_mainAccountParams.setBranchCode("123");
            l_mainAccountParams.setInstitutionCode("0D");
            l_mainAccountParams.setAccountId(12345);
            TestDBUtility.insertWithDel(l_mainAccountParams);

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.gentrade.WEB3GentradeMainAccount",
                "isMarginAccountEstablished",
                new Class[] {String.class},
                new Boolean(false));

            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setSubAccountId(123456);
            l_subAccountParams.setAccountId(12345);
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
            TestDBUtility.insertWithDel(l_subAccountParams);

            TestDBUtility.deleteAll(MutualFundOrderUnitRow.TYPE);
            MutualFundOrderUnitParams l_mutualFundOrderUnitParams =
                TestDBUtility.getMutualFundOrderUnitRow();
            l_mutualFundOrderUnitParams.setAccountId(12345);
            l_mutualFundOrderUnitParams.setSubAccountId(123456);
            l_mutualFundOrderUnitParams.setProductType(ProductTypeEnum.MUTUAL_FUND);
            l_mutualFundOrderUnitParams.setOrderOpenStatus(OrderOpenStatusEnum.CLOSED);
            l_mutualFundOrderUnitParams.setOrderRootDiv(WEB3OrderRootDivDef.HOST);
            l_mutualFundOrderUnitParams.setOrderRequestNumber(null);
            TestDBUtility.insertWithDel(l_mutualFundOrderUnitParams);

            WEB3AdminMutualTPACancelListResponse l_response =
                (WEB3AdminMutualTPACancelListResponse)l_serviceImpl.searchTPACancel(l_request);

            assertEquals(l_response.cancelList.length, 0);
        }
        catch (Exception l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }

        log.exiting(STR_METHOD_NAME);
    }

    public void testSearchTPACancel_C0006()
    {
        final String STR_METHOD_NAME = "testSearchTPACancel_C0006()";
        log.entering(STR_METHOD_NAME);

        WEB3AdminMutualTPACancelListRequest l_request =
            new WEB3AdminMutualTPACancelListRequest();
        l_request.accountCode = "123";
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
            l_adminPermissionParams.setUpdatePermissionFlag(BooleanEnum.FALSE);
            TestDBUtility.insertWithDel(l_adminPermissionParams);

            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountCode("123");
            l_mainAccountParams.setBranchCode("123");
            l_mainAccountParams.setInstitutionCode("0D");
            l_mainAccountParams.setAccountId(12345);
            TestDBUtility.insertWithDel(l_mainAccountParams);

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.gentrade.WEB3GentradeMainAccount",
                "isMarginAccountEstablished",
                new Class[] {String.class},
                new Boolean(false));

            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setSubAccountId(123456);
            l_subAccountParams.setAccountId(12345);
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
            TestDBUtility.insertWithDel(l_subAccountParams);

            TestDBUtility.deleteAll(MutualFundOrderUnitRow.TYPE);
            MutualFundOrderUnitParams l_mutualFundOrderUnitParams =
                TestDBUtility.getMutualFundOrderUnitRow();
            l_mutualFundOrderUnitParams.setAccountId(12345);
            l_mutualFundOrderUnitParams.setSubAccountId(123456);
            l_mutualFundOrderUnitParams.setProductType(ProductTypeEnum.MUTUAL_FUND);
            l_mutualFundOrderUnitParams.setOrderOpenStatus(OrderOpenStatusEnum.OPEN);
            l_mutualFundOrderUnitParams.setOrderRootDiv(WEB3OrderRootDivDef.HOST);
            l_mutualFundOrderUnitParams.setOrderRequestNumber(null);
            l_mutualFundOrderUnitParams.setProductId(1234);
            TestDBUtility.insertWithDel(l_mutualFundOrderUnitParams);

            TestDBUtility.deleteAll(ProductRow.TYPE);
            ProductParams l_productParams = TestDBUtility.getProductRow();
            l_productParams.setProductId(123);
            TestDBUtility.insertWithDel(l_productParams);

            l_serviceImpl.searchTPACancel(l_request);

            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            assertEquals(l_ex.getErrorInfo(), WEB3ErrorCatalog.BUSINESS_ERROR_00377);
        }
        catch (Exception l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }

        log.exiting(STR_METHOD_NAME);
    }

    public void testSearchTPACancel_C0007()
    {
        final String STR_METHOD_NAME = "testSearchTPACancel_C0007()";
        log.entering(STR_METHOD_NAME);

        WEB3AdminMutualTPACancelListRequest l_request =
            new WEB3AdminMutualTPACancelListRequest();
        l_request.accountCode = "123";
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
            l_adminPermissionParams.setUpdatePermissionFlag(BooleanEnum.FALSE);
            TestDBUtility.insertWithDel(l_adminPermissionParams);

            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountCode("123");
            l_mainAccountParams.setBranchCode("123");
            l_mainAccountParams.setInstitutionCode("0D");
            l_mainAccountParams.setAccountId(12345);
            TestDBUtility.insertWithDel(l_mainAccountParams);

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.gentrade.WEB3GentradeMainAccount",
                "isMarginAccountEstablished",
                new Class[] {String.class},
                new Boolean(false));

            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setSubAccountId(123456);
            l_subAccountParams.setAccountId(12345);
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
            TestDBUtility.insertWithDel(l_subAccountParams);

            TestDBUtility.deleteAll(MutualFundOrderUnitRow.TYPE);
            MutualFundOrderUnitParams l_mutualFundOrderUnitParams =
                TestDBUtility.getMutualFundOrderUnitRow();
            l_mutualFundOrderUnitParams.setAccountId(12345);
            l_mutualFundOrderUnitParams.setSubAccountId(123456);
            l_mutualFundOrderUnitParams.setProductType(ProductTypeEnum.MUTUAL_FUND);
            l_mutualFundOrderUnitParams.setOrderOpenStatus(OrderOpenStatusEnum.OPEN);
            l_mutualFundOrderUnitParams.setOrderRootDiv(WEB3OrderRootDivDef.HOST);
            l_mutualFundOrderUnitParams.setOrderRequestNumber(null);
            l_mutualFundOrderUnitParams.setProductId(1234);
            l_mutualFundOrderUnitParams.setOrderId(1231);
            l_mutualFundOrderUnitParams.setQuantity(100);
            l_mutualFundOrderUnitParams.setBizDate("20081010");
            l_mutualFundOrderUnitParams.setExecDate(WEB3DateUtility.getDate("20081009", "yyyyMMdd"));
            l_mutualFundOrderUnitParams.setDeliveryDate(WEB3DateUtility.getDate("20081008", "yyyyMMdd"));
            TestDBUtility.insertWithDel(l_mutualFundOrderUnitParams);

            TestDBUtility.deleteAll(MutualFundProductParams.TYPE);
            MutualFundProductParams l_mutualFundProductParams = TestDBUtility.getMutualFundProductRow();
            l_mutualFundProductParams.setProductId(1234);
            l_mutualFundProductParams.setProductCode("1232");
            l_mutualFundProductParams.setStandardName("test");
            TestDBUtility.insertWithDel(l_mutualFundProductParams);

            TestDBUtility.deleteAll(ProductRow.TYPE);
            ProductParams l_productParams = TestDBUtility.getProductRow();
            l_productParams.setProductId(1234);
            TestDBUtility.insertWithDel(l_productParams);

            WEB3AdminMutualTPACancelListResponse l_response =
                (WEB3AdminMutualTPACancelListResponse)l_serviceImpl.searchTPACancel(l_request);

            assertEquals(l_response.cancelList.length, 1);
            assertEquals(l_response.cancelList[0].orderId, "1231");
            assertEquals(l_response.cancelList[0].mutualProductCode, "1232");
            assertEquals(l_response.cancelList[0].mutualProductName, "test");
            assertEquals(l_response.cancelList[0].settlePrice, "100");
            int l_intCompareDate = WEB3DateUtility.compareToDay(
                l_response.cancelList[0].orderBizDate,
                WEB3DateUtility.getDate("20081010", "yyyyMMdd"));
            assertEquals(l_intCompareDate, 0);
            l_intCompareDate = WEB3DateUtility.compareToDay(
                l_response.cancelList[0].executionTimestamp,
                WEB3DateUtility.getDate("20081009", "yyyyMMdd"));
            assertEquals(l_intCompareDate, 0);
            l_intCompareDate = WEB3DateUtility.compareToDay(
                l_response.cancelList[0].deliveryDate,
                WEB3DateUtility.getDate("20081008", "yyyyMMdd"));
            assertEquals(l_intCompareDate, 0);
        }
        catch (Exception l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }

        log.exiting(STR_METHOD_NAME);
    }

    public void testSubmitTPACancel_C0001()
    {
        final String STR_METHOD_NAME = "testSubmitTPACancel_C0001()";
        log.entering(STR_METHOD_NAME);

        WEB3AdminMutualTPACancelCompleteRequest l_request =
            new WEB3AdminMutualTPACancelCompleteRequest();
        l_request.accountCode = "123";
        l_request.orderId = null;

        try
        {
            l_serviceImpl.submitTPACancel(l_request);
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            assertEquals(l_ex.getErrorInfo(), WEB3ErrorCatalog.BUSINESS_ERROR_00600);
        }
        catch (Exception l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }

        log.exiting(STR_METHOD_NAME);
    }

    public void testSubmitTPACancel_C0002()
    {
        final String STR_METHOD_NAME = "testSubmitTPACancel_C0002()";
        log.entering(STR_METHOD_NAME);

        WEB3AdminMutualTPACancelCompleteRequest l_request =
            new WEB3AdminMutualTPACancelCompleteRequest();
        l_request.accountCode = "123";
        l_request.orderId = "1234";
        l_request.password = "1001";

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

            WEB3AdministratorForMock.mockValidateTradingPassword(
                "10001",
                true);

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

            l_serviceImpl.submitTPACancel(l_request);
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

    public void testSubmitTPACancel_C0003()
    {
        final String STR_METHOD_NAME = "testSubmitTPACancel_C0003()";
        log.entering(STR_METHOD_NAME);

        WEB3AdminMutualTPACancelCompleteRequest l_request =
            new WEB3AdminMutualTPACancelCompleteRequest();
        l_request.accountCode = "123";
        l_request.orderId = "1234";

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

            WEB3AdministratorForMock.mockValidateTradingPassword(
                "10001",
                true);

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
            l_mainAccountParams.setAccountId(123456);
            TestDBUtility.insertWithDel(l_mainAccountParams);

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.gentrade.WEB3GentradeMainAccount",
                "isMarginAccountEstablished",
                new Class[] {String.class},
                new Boolean(true));

            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setSubAccountId(123456);
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
            TestDBUtility.insertWithDel(l_subAccountParams);

            l_serviceImpl.submitTPACancel(l_request);
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

    public void testSubmitTPACancel_C0004()
    {
        final String STR_METHOD_NAME = "testSubmitTPACancel_C0004()";
        log.entering(STR_METHOD_NAME);

        WEB3AdminMutualTPACancelCompleteRequest l_request =
            new WEB3AdminMutualTPACancelCompleteRequest();
        l_request.accountCode = "123";
        l_request.orderId = "1234";

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

            WEB3AdministratorForMock.mockValidateTradingPassword(
                "10001",
                true);

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
            l_mainAccountParams.setAccountId(123456);
            TestDBUtility.insertWithDel(l_mainAccountParams);

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.gentrade.WEB3GentradeMainAccount",
                "isMarginAccountEstablished",
                new Class[] {String.class},
                new Boolean(false));

            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setSubAccountId(123456);
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_MARGIN_SUB_ACCOUNT);
            TestDBUtility.insertWithDel(l_subAccountParams);

            l_serviceImpl.submitTPACancel(l_request);
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

    public void testSubmitTPACancel_C0005()
    {
        final String STR_METHOD_NAME = "testSubmitTPACancel_C0005()";
        log.entering(STR_METHOD_NAME);

        WEB3AdminMutualTPACancelCompleteRequest l_request =
            new WEB3AdminMutualTPACancelCompleteRequest();
        l_request.accountCode = "123";
        l_request.orderId = "1234";

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

            WEB3AdministratorForMock.mockValidateTradingPassword(
                "10001",
                true);

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
            l_mainAccountParams.setAccountId(123456);
            TestDBUtility.insertWithDel(l_mainAccountParams);

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.gentrade.WEB3GentradeMainAccount",
                "isMarginAccountEstablished",
                new Class[] {String.class},
                new Boolean(true));

            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setSubAccountId(12345);
            l_subAccountParams.setAccountId(123456);
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_MARGIN_SUB_ACCOUNT);
            TestDBUtility.insertWithDel(l_subAccountParams);

            TestDBUtility.deleteAll(MutualFundOrderActionRow.TYPE);
            MutualFundOrderActionParams l_mutualFundOrderActionParams =
                TestDBUtility.getMutualFundOrderActionRow();
            l_mutualFundOrderActionParams.setOrderId(1234);
            TestDBUtility.insertWithDel(l_mutualFundOrderActionParams);

            TestDBUtility.deleteAll(BranchParams.TYPE);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setBranchId(33381);
            TestDBUtility.insertWithDel(l_branchParams);

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.tradingpower.WEB3TPTradingPowerServiceImpl",
                "reCalcTradingPower",
                new Class[] {WEB3GentradeSubAccount.class},
                null);

            WEB3AdminMutualTPACancelCompleteResponse l_response =
                (WEB3AdminMutualTPACancelCompleteResponse)l_serviceImpl.submitTPACancel(l_request);

            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            List l_lisRows = l_queryProcessor.doFindAllQuery(
                MutualFundOrderActionRow.TYPE,
                "order_id = ?",
                null,
                null, 
                new Object[]{"1234"});

            assertNotNull(l_response);
            assertEquals(l_lisRows.size(), 0);
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
