head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.01.45.34;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3AdminBondExecuteCancelServiceImplTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.bd.service.delegate.stdimpls;

import java.util.HashMap;
import java.util.Map;

import test.util.TestDBUtility;
import webbroker3.bd.data.BondBranchConditionParams;
import webbroker3.bd.message.WEB3AdminBondExecCancelCompleteRequest;
import webbroker3.bd.message.WEB3AdminBondExecCancelCompleteResponse;
import webbroker3.common.define.WEB3LoginTypeAttributeKeyDef;
import webbroker3.common.define.WEB3TradingPwdEnvDef;
import webbroker3.common.define.WEB3TransactionCategoryDef;
import webbroker3.gentrade.WEB3Administrator;
import webbroker3.gentrade.WEB3AdministratorForMock;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagementForMock;
import webbroker3.gentrade.data.AdminPermissionParams;
import webbroker3.gentrade.data.AdministratorParams;
import webbroker3.gentrade.data.AdministratorTypeParams;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3LogUtility;

import com.fitechlabs.xtrade.plugin.security.oplogin.impl.LoginInfoImpl;
import com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderStatusEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.BranchParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.ProductParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.SubAccountParams;
import com.fitechlabs.xtrade.plugin.tc.xbbd.BondTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.xbbd.data.BondOrderParams;
import com.fitechlabs.xtrade.plugin.tc.xbbd.data.BondOrderUnitParams;
import com.fitechlabs.xtrade.plugin.tc.xbbd.data.BondProductParams;

public class WEB3AdminBondExecuteCancelServiceImplTest extends TestBaseForMock
{

    /**
     * ログ出力ユーティリティ。<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminBondExecuteCancelServiceImplTest.class);

    public WEB3AdminBondExecuteCancelServiceImplTest(String arg0)
    {
        super(arg0);
    }

    protected void setUp() throws Exception
    {
        MOCK_MANAGER.setIsMockUsed(true);
        super.setUp();
    }

    protected void tearDown() throws Exception
    {
        super.tearDown();
    }

    /**
     * <BR>
     */
    public void test_submitExecuteCancel_0001()
    {
        final String STR_METHOD_NAME =
            " test_submitExecuteCancel_0001()";
        log.entering(STR_METHOD_NAME);

        try
        {
            TestDBUtility.deleteAll(AdministratorParams.TYPE);
            TestDBUtility.deleteAll(AdminPermissionParams.TYPE);
            TestDBUtility.deleteAll(BondOrderParams.TYPE);
            TestDBUtility.deleteAll(SubAccountParams.TYPE);
            TestDBUtility.deleteAll(MainAccountParams.TYPE);
            TestDBUtility.deleteAll(BranchParams.TYPE);
            TestDBUtility.deleteAll(BondOrderUnitParams.TYPE);
            TestDBUtility.deleteAll(AdministratorTypeParams.TYPE);
            TestDBUtility.deleteAll(BondBranchConditionParams.TYPE);

            LoginInfoImpl l_loginInfo = new LoginInfoImpl();
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getLoginInfo",
                new Class[] {},
                l_loginInfo);

            Map l_map = new HashMap();
            l_map.put(WEB3LoginTypeAttributeKeyDef.TRADING_PWD_ENV, WEB3TradingPwdEnvDef.DEFAULT);
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

            WEB3GentradeTradingTimeManagementForMock.mockGetOrderBizDate(GtlUtils.getSystemTimestamp());

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.tradingpower.WEB3TPTradingPowerServiceImpl",
                "reCalcTradingPower",
                new Class[] {WEB3GentradeSubAccount.class},
                null);

            BondBranchConditionParams l_bondBranchConditionParams = TestDBUtility.getBondBranchConditionRow();
            l_bondBranchConditionParams.setBranchId(33381L);
            l_bondBranchConditionParams.setOrderLockUseDiv("0");
            TestDBUtility.insertWithDel(l_bondBranchConditionParams);

            BondProductParams l_bondProductParams = TestDBUtility.getBondProductRow();
            l_bondProductParams.setProductId(123456L);
            l_bondProductParams.setTradeHandleDiv("1");
            TestDBUtility.insertWithDel(l_bondProductParams);

            ProductParams l_productParams = TestDBUtility.getProductRow();
            l_productParams.setProductId(123456L);
            TestDBUtility.insertWithDel(l_productParams);

            BondOrderUnitParams l_bondOrderUnitParams = TestDBUtility.getBondOrderUnitRow();
            l_bondOrderUnitParams.setOrderId(123L);
            l_bondOrderUnitParams.setAccountId(1001L);
            l_bondOrderUnitParams.setSubAccountId(1001L);
            l_bondOrderUnitParams.setOrderExecStatus("1");
            l_bondOrderUnitParams.setDeliveryDate(GtlUtils.getSystemTimestamp());
            l_bondOrderUnitParams.setBondType(BondTypeEnum.FOREIGN_BOND);
            TestDBUtility.insertWithDel(l_bondOrderUnitParams);

            AdministratorParams l_administratorParams = TestDBUtility.getAdministratorRow();
            l_administratorParams.setInstitutionCode("0D");
            l_administratorParams.setPermissionLevel("1");
            TestDBUtility.insertWithDel(l_administratorParams);

            BondOrderParams l_bondOrderParams = new BondOrderParams();
            l_bondOrderParams.setSubAccountId(1001L);
            l_bondOrderParams.setProductType(ProductTypeEnum.BOND);
            l_bondOrderParams.setOrderId(123L);
            l_bondOrderParams.setAccountId(1001L);
            l_bondOrderParams.setCreatedTimestamp(GtlUtils.getSystemTimestamp());
            l_bondOrderParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
            TestDBUtility.insertWithDel(l_bondOrderParams);

            AdministratorTypeParams l_administratorTypeParams = TestDBUtility.getAdministratorTypeRow();
            l_administratorTypeParams.setInstitutionCode("0D");
            l_administratorTypeParams.setPermissionLevel("1");
            l_administratorTypeParams.setAllBranchPermissionFlag(BooleanEnum.TRUE);
            TestDBUtility.insertWithDel(l_administratorTypeParams);

            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setAccountId(1001L);
            l_subAccountParams.setSubAccountId(1001L);
            TestDBUtility.insertWithDel(l_subAccountParams);

            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(1001L);
            l_mainAccountParams.setBranchId(1001L);
            TestDBUtility.insertWithDel(l_mainAccountParams);

            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setBranchId(1001L);
            TestDBUtility.insertWithDel(l_branchParams);

            AdminPermissionParams l_adminPermissionParams = TestDBUtility.getAdminPermissionRow();
            l_adminPermissionParams.setTransactionCategory(WEB3TransactionCategoryDef.BOND_EXECUTE_MODIFY_CANCEL);
            l_adminPermissionParams.setInstitutionCode("0D");
            l_adminPermissionParams.setPermissionLevel("1");
            l_adminPermissionParams.setUpdatePermissionFlag(BooleanEnum.TRUE);
            TestDBUtility.insertWithDel(l_adminPermissionParams);

            WEB3Administrator l_expectAdministrator = new WEB3Administrator(l_administratorParams);
            WEB3AdministratorForMock.mockGetInstanceFromLoginInfo(l_expectAdministrator);
            WEB3AdminBondExecCancelCompleteRequest l_request = new WEB3AdminBondExecCancelCompleteRequest();
            l_request.id = "123";
            l_request.password = "123";

            WEB3AdminBondExecuteCancelServiceImpl l_adminBondExecuteCancelServiceImpl =
                new WEB3AdminBondExecuteCancelServiceImpl();
            WEB3AdminBondExecCancelCompleteResponse l_response =
                l_adminBondExecuteCancelServiceImpl.submitExecuteCancel(l_request);
            assertEquals(l_response.lastUpdatedTimestamp, GtlUtils.getSystemTimestamp());
            assertEquals(l_response.orderActionId, "123");
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
    }

    /**
     * <BR>
     */
    public void test_submitExecuteCancel_0002()
    {
        final String STR_METHOD_NAME =
            " test_submitExecuteCancel_0002()";
        log.entering(STR_METHOD_NAME);

        try
        {
            TestDBUtility.deleteAll(AdministratorParams.TYPE);
            TestDBUtility.deleteAll(AdminPermissionParams.TYPE);
            TestDBUtility.deleteAll(BondOrderParams.TYPE);
            TestDBUtility.deleteAll(SubAccountParams.TYPE);
            TestDBUtility.deleteAll(MainAccountParams.TYPE);
            TestDBUtility.deleteAll(BranchParams.TYPE);
            TestDBUtility.deleteAll(BondOrderUnitParams.TYPE);
            TestDBUtility.deleteAll(AdministratorTypeParams.TYPE);
            TestDBUtility.deleteAll(BondBranchConditionParams.TYPE);

            LoginInfoImpl l_loginInfo = new LoginInfoImpl();
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getLoginInfo",
                new Class[] {},
                l_loginInfo);

            Map l_map = new HashMap();
            l_map.put(WEB3LoginTypeAttributeKeyDef.TRADING_PWD_ENV, WEB3TradingPwdEnvDef.DEFAULT);
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

            WEB3GentradeTradingTimeManagementForMock.mockGetOrderBizDate(GtlUtils.getSystemTimestamp());

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.tradingpower.WEB3TPTradingPowerServiceImpl",
                "reCalcTradingPower",
                new Class[] {WEB3GentradeSubAccount.class},
                null);

            BondBranchConditionParams l_bondBranchConditionParams = TestDBUtility.getBondBranchConditionRow();
            l_bondBranchConditionParams.setBranchId(33381L);
            l_bondBranchConditionParams.setOrderLockUseDiv("0");
            TestDBUtility.insertWithDel(l_bondBranchConditionParams);

            BondProductParams l_bondProductParams = TestDBUtility.getBondProductRow();
            l_bondProductParams.setProductId(123456L);
            l_bondProductParams.setTradeHandleDiv("1");
            TestDBUtility.insertWithDel(l_bondProductParams);

            ProductParams l_productParams = TestDBUtility.getProductRow();
            l_productParams.setProductId(123456L);
            TestDBUtility.insertWithDel(l_productParams);

            BondOrderUnitParams l_bondOrderUnitParams = TestDBUtility.getBondOrderUnitRow();
            l_bondOrderUnitParams.setOrderId(123L);
            l_bondOrderUnitParams.setAccountId(1001L);
            l_bondOrderUnitParams.setSubAccountId(1001L);
            l_bondOrderUnitParams.setOrderExecStatus("0");
            l_bondOrderUnitParams.setOrderStatus(OrderStatusEnum.MODIFIED);
            l_bondOrderUnitParams.setDeliveryDate(GtlUtils.getSystemTimestamp());
            l_bondOrderUnitParams.setBondType(BondTypeEnum.CB);
            TestDBUtility.insertWithDel(l_bondOrderUnitParams);

            AdministratorParams l_administratorParams = TestDBUtility.getAdministratorRow();
            l_administratorParams.setInstitutionCode("0D");
            l_administratorParams.setPermissionLevel("1");
            TestDBUtility.insertWithDel(l_administratorParams);

            BondOrderParams l_bondOrderParams = new BondOrderParams();
            l_bondOrderParams.setSubAccountId(1001L);
            l_bondOrderParams.setProductType(ProductTypeEnum.BOND);
            l_bondOrderParams.setOrderId(123L);
            l_bondOrderParams.setAccountId(1001L);
            l_bondOrderParams.setCreatedTimestamp(GtlUtils.getSystemTimestamp());
            l_bondOrderParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
            TestDBUtility.insertWithDel(l_bondOrderParams);

            AdministratorTypeParams l_administratorTypeParams = TestDBUtility.getAdministratorTypeRow();
            l_administratorTypeParams.setInstitutionCode("0D");
            l_administratorTypeParams.setPermissionLevel("1");
            l_administratorTypeParams.setAllBranchPermissionFlag(BooleanEnum.TRUE);
            TestDBUtility.insertWithDel(l_administratorTypeParams);

            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setAccountId(1001L);
            l_subAccountParams.setSubAccountId(1001L);
            TestDBUtility.insertWithDel(l_subAccountParams);

            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(1001L);
            l_mainAccountParams.setBranchId(1001L);
            TestDBUtility.insertWithDel(l_mainAccountParams);

            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setBranchId(1001L);
            TestDBUtility.insertWithDel(l_branchParams);

            AdminPermissionParams l_adminPermissionParams = TestDBUtility.getAdminPermissionRow();
            l_adminPermissionParams.setTransactionCategory(WEB3TransactionCategoryDef.BOND_EXECUTE_MODIFY_CANCEL);
            l_adminPermissionParams.setInstitutionCode("0D");
            l_adminPermissionParams.setPermissionLevel("1");
            l_adminPermissionParams.setUpdatePermissionFlag(BooleanEnum.TRUE);
            TestDBUtility.insertWithDel(l_adminPermissionParams);

            WEB3Administrator l_expectAdministrator = new WEB3Administrator(l_administratorParams);
            WEB3AdministratorForMock.mockGetInstanceFromLoginInfo(l_expectAdministrator);
            WEB3AdminBondExecCancelCompleteRequest l_request = new WEB3AdminBondExecCancelCompleteRequest();
            l_request.id = "123";
            l_request.password = "123";

            WEB3AdminBondExecuteCancelServiceImpl l_adminBondExecuteCancelServiceImpl =
                new WEB3AdminBondExecuteCancelServiceImpl();
            WEB3AdminBondExecCancelCompleteResponse l_response =
                l_adminBondExecuteCancelServiceImpl.submitExecuteCancel(l_request);
            assertEquals(l_response.lastUpdatedTimestamp, GtlUtils.getSystemTimestamp());
            assertEquals(l_response.orderActionId, "123");
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
    }

    /**
     * 補助口座 == null<BR>
     * 債券銘柄 == null<BR>
     */
    public void test_submitExecuteCancel_0003()
    {
        final String STR_METHOD_NAME =
            " test_submitExecuteCancel_0003()";
        log.entering(STR_METHOD_NAME);

        try
        {
            TestDBUtility.deleteAll(AdministratorParams.TYPE);
            TestDBUtility.deleteAll(AdminPermissionParams.TYPE);
            TestDBUtility.deleteAll(BondOrderParams.TYPE);
            TestDBUtility.deleteAll(SubAccountParams.TYPE);
            TestDBUtility.deleteAll(MainAccountParams.TYPE);
            TestDBUtility.deleteAll(BranchParams.TYPE);
            TestDBUtility.deleteAll(BondOrderUnitParams.TYPE);
            TestDBUtility.deleteAll(AdministratorTypeParams.TYPE);
            TestDBUtility.deleteAll(BondBranchConditionParams.TYPE);

            LoginInfoImpl l_loginInfo = new LoginInfoImpl();
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getLoginInfo",
                new Class[] {},
                l_loginInfo);

            Map l_map = new HashMap();
            l_map.put(WEB3LoginTypeAttributeKeyDef.TRADING_PWD_ENV, WEB3TradingPwdEnvDef.DEFAULT);
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

            WEB3GentradeTradingTimeManagementForMock.mockGetOrderBizDate(GtlUtils.getSystemTimestamp());

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.tradingpower.WEB3TPTradingPowerServiceImpl",
                "reCalcTradingPower",
                new Class[] {WEB3GentradeSubAccount.class},
                null);

            BondBranchConditionParams l_bondBranchConditionParams = TestDBUtility.getBondBranchConditionRow();
            l_bondBranchConditionParams.setBranchId(33381L);
            l_bondBranchConditionParams.setOrderLockUseDiv("0");
            TestDBUtility.insertWithDel(l_bondBranchConditionParams);

            BondProductParams l_bondProductParams = TestDBUtility.getBondProductRow();
            l_bondProductParams.setProductId(123456L);
            l_bondProductParams.setTradeHandleDiv("1");
            TestDBUtility.insertWithDel(l_bondProductParams);

            ProductParams l_productParams = TestDBUtility.getProductRow();
            l_productParams.setProductId(123456L);
            TestDBUtility.insertWithDel(l_productParams);

            BondOrderUnitParams l_bondOrderUnitParams = TestDBUtility.getBondOrderUnitRow();
            l_bondOrderUnitParams.setOrderId(123L);
            l_bondOrderUnitParams.setAccountId(1001L);
            l_bondOrderUnitParams.setSubAccountId(1001L);
            l_bondOrderUnitParams.setOrderExecStatus("1");
            l_bondOrderUnitParams.setDeliveryDate(GtlUtils.getSystemTimestamp());
            l_bondOrderUnitParams.setBondType(BondTypeEnum.FOREIGN_BOND);
            l_bondOrderUnitParams.setConfirmedQuantity(1.1D);
            TestDBUtility.insertWithDel(l_bondOrderUnitParams);

            AdministratorParams l_administratorParams = TestDBUtility.getAdministratorRow();
            l_administratorParams.setInstitutionCode("0D");
            l_administratorParams.setPermissionLevel("1");
            TestDBUtility.insertWithDel(l_administratorParams);

            BondOrderParams l_bondOrderParams = new BondOrderParams();
            l_bondOrderParams.setSubAccountId(1001L);
            l_bondOrderParams.setProductType(ProductTypeEnum.BOND);
            l_bondOrderParams.setOrderId(123L);
            l_bondOrderParams.setAccountId(1001L);
            l_bondOrderParams.setCreatedTimestamp(GtlUtils.getSystemTimestamp());
            l_bondOrderParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
            TestDBUtility.insertWithDel(l_bondOrderParams);

            AdministratorTypeParams l_administratorTypeParams = TestDBUtility.getAdministratorTypeRow();
            l_administratorTypeParams.setInstitutionCode("0D");
            l_administratorTypeParams.setPermissionLevel("1");
            l_administratorTypeParams.setAllBranchPermissionFlag(BooleanEnum.TRUE);
            TestDBUtility.insertWithDel(l_administratorTypeParams);

            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setAccountId(1001L);
            l_subAccountParams.setSubAccountId(1001L);
            TestDBUtility.insertWithDel(l_subAccountParams);

            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(1001L);
            l_mainAccountParams.setBranchId(1001L);
            TestDBUtility.insertWithDel(l_mainAccountParams);

            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setBranchId(1001L);
            TestDBUtility.insertWithDel(l_branchParams);

            AdminPermissionParams l_adminPermissionParams = TestDBUtility.getAdminPermissionRow();
            l_adminPermissionParams.setTransactionCategory(WEB3TransactionCategoryDef.BOND_EXECUTE_MODIFY_CANCEL);
            l_adminPermissionParams.setInstitutionCode("0D");
            l_adminPermissionParams.setPermissionLevel("1");
            l_adminPermissionParams.setUpdatePermissionFlag(BooleanEnum.TRUE);
            TestDBUtility.insertWithDel(l_adminPermissionParams);

            WEB3Administrator l_expectAdministrator = new WEB3Administrator(l_administratorParams);
            WEB3AdministratorForMock.mockGetInstanceFromLoginInfo(l_expectAdministrator);
            WEB3AdminBondExecCancelCompleteRequest l_request = new WEB3AdminBondExecCancelCompleteRequest();
            l_request.id = "123";
            l_request.password = "123";

            WEB3AdminBondExecuteCancelServiceImpl l_adminBondExecuteCancelServiceImpl =
                new WEB3AdminBondExecuteCancelServiceImpl();
            WEB3AdminBondExecCancelCompleteResponse l_response =
                l_adminBondExecuteCancelServiceImpl.submitExecuteCancel(l_request);
            assertEquals(l_response.lastUpdatedTimestamp, GtlUtils.getSystemTimestamp());
            assertEquals(l_response.orderActionId, "123");

            Long l_loginId = (Long)TestBaseForMock.MOCK_MANAGER.getMethodParamsValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginAdminServiceImpl",
                "getLoginTypeAttributes",
                new Class[] {long.class}).getFirstCalled()[0];
            assertEquals(0, l_loginId.longValue());

            WEB3GentradeSubAccount l_subAccount =
                (WEB3GentradeSubAccount)TestBaseForMock.MOCK_MANAGER.getMethodParamsValue(
                    "webbroker3.tradingpower.WEB3TPTradingPowerServiceImpl",
                    "reCalcTradingPower",
                    new Class[] {WEB3GentradeSubAccount.class}).getFirstCalled()[0];
            assertEquals(1001L, l_subAccount.getAccountId());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
    }
}
@
