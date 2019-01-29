head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.01.09.40;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3AdminEquityForcedSettleOrderApproveUnitServiceImplTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.eqtypeadmin.service.delegate.stdimpls;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.fitechlabs.xtrade.kernel.message.ErrorInfo;
import com.fitechlabs.xtrade.kernel.util.ThreadLocalSystemAttributesRegistry;
import com.fitechlabs.xtrade.plugin.security.oplogin.LoginInfo;
import com.fitechlabs.xtrade.plugin.security.oplogin.impl.LoginInfoImpl;
import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeExecutionConditionType;
import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeOrderUnit;
import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeTradedProduct;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeClosingContractSpecParams;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeClosingContractSpecRow;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeContractParams;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeContractRow;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderActionDao;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderActionRow;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderParams;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderRow;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderUnitDao;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderUnitParams;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderUnitRow;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeProductParams;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeProductRow;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeTradedProductParams;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeTradedProductRow;
import com.fitechlabs.xtrade.plugin.tc.eqtype.ordersubmitter.io.EqTypeNewOrderValidationResult;
import com.fitechlabs.xtrade.plugin.tc.eqtype.ordersubmitter.io.EqTypeSettleContractOrderEntry;
import com.fitechlabs.xtrade.plugin.tc.eqtype.ordersubmitter.io.EqTypeSettleContractOrderSpec;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ContractTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderCategEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderEventTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderExpirationStatusEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderOpenStatusEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderStatusEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProcessingResult;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TaxTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.BranchParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.BranchRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.InstitutionParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.InstitutionRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MarketParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MarketRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.ProductParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.ProductRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.SubAccountParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.SubAccountRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.TradedProductParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.TradedProductRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.TraderParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.TraderRow;

import test.util.TestDBUtility;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3ApproveStatusType;
import webbroker3.eqtypeadmin.data.AdminEqForcedSettleOrderParams;
import webbroker3.eqtypeadmin.data.AdminEqForcedSettleOrderRow;
import webbroker3.equity.WEB3EquityContract;
import webbroker3.equity.WEB3EquityRealizedProfitAndLossPrice;
import webbroker3.equity.WEB3EquityTradedProduct;
import webbroker3.equity.data.HostEqtypeCloseOrderNotifyParams;
import webbroker3.gentrade.WEB3Administrator;
import webbroker3.gentrade.WEB3AdministratorForMock;
import webbroker3.gentrade.WEB3GentradeCommission;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagementForMock;
import webbroker3.gentrade.data.AdministratorParams;
import webbroker3.gentrade.data.AdministratorRow;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

public class WEB3AdminEquityForcedSettleOrderApproveUnitServiceImplTest extends TestBaseForMock
{
    /**
     * ログ出力ユーティリティ。<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminEquityForcedSettleOrderApproveUnitServiceImplTest.class);
    
    WEB3AdminEquityForcedSettleOrderApproveUnitServiceImplForTest l_mpl =
        new WEB3AdminEquityForcedSettleOrderApproveUnitServiceImplForTest();

    public WEB3AdminEquityForcedSettleOrderApproveUnitServiceImplTest(String arg0)
    {
        super(arg0);
    }

    protected void setUp() throws Exception
    {
        super.setUp();
        MOCK_MANAGER.setIsMockUsed(true);
    }

    protected void tearDown() throws Exception
    {
        super.tearDown();
    }

    
    public void testExecApproveCase0001()
    {
        final String STR_METHOD_NAME = " testExecApproveCase0001";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            AdminEqForcedSettleOrderRow l_forcedSettleOrderRow = null;
            
            AdministratorParams l_administratorParams = TestDBUtility.getAdministratorRow();
            TestDBUtility.deleteAll(AdministratorRow.TYPE);
            TestDBUtility.insertWithDel(l_administratorParams);
            
            WEB3AdminEquityForcedSettleOrderApproveUnitServiceImpl l_impl =
                new WEB3AdminEquityForcedSettleOrderApproveUnitServiceImpl();
            WEB3Administrator l_expectAdministrator = new WEB3Administrator(l_administratorParams);
            
            l_impl.execApprove(l_forcedSettleOrderRow, l_expectAdministrator);

        }
        catch(WEB3BaseException l_ex)
        {
            log.debug(STR_METHOD_NAME, l_ex);
            assertEquals(l_ex.getErrorInfo(),WEB3ErrorCatalog.SYSTEM_ERROR_80017);
            log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
  }
    
    public void testExecApproveCase0002()
    {
        final String STR_METHOD_NAME = " testExecApproveCase0002";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            this.deleteAll();

            AdministratorParams l_administratorParams = TestDBUtility.getAdministratorRow();
            TestDBUtility.deleteAll(AdministratorRow.TYPE);
            TestDBUtility.insertWithDel(l_administratorParams);
            
            AdminEqForcedSettleOrderParams l_forcedSettleOrderParams =
                new AdminEqForcedSettleOrderParams();
            l_forcedSettleOrderParams.setAccountId(12356);

            WEB3Administrator l_expectAdministrator = new WEB3Administrator(l_administratorParams);

            WEB3AdministratorForMock.mockGetInstanceFromLoginInfo(l_expectAdministrator);
            
            WEB3AdminEquityForcedSettleOrderApproveUnitServiceImpl l_impl =
                new WEB3AdminEquityForcedSettleOrderApproveUnitServiceImpl();
            
            boolean l_blnExecApprove = l_impl.execApprove(l_forcedSettleOrderParams, l_expectAdministrator);

            assertTrue(l_blnExecApprove);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
        log.exiting(TEST_END + STR_METHOD_NAME);
  }
    
    public void testExecApproveCase0003()
    {
        final String STR_METHOD_NAME = " testExecApproveCase0003";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setBranchCode("381");
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            TestDBUtility.insertWithDel(l_mainAccountParams);

            EqtypeOrderUnitParams l_eqtypeOrderUnitParams = TestDBUtility.getEqtypeOrderUnitRow();
            TestDBUtility.deleteAll(EqtypeOrderUnitRow.TYPE);
            l_eqtypeOrderUnitParams.setOrderUnitId(1001);
            l_eqtypeOrderUnitParams.setForcedSettleReasonType("0");
            l_eqtypeOrderUnitParams.setApproveStatusType("1");
            l_eqtypeOrderUnitParams.setOrderOpenStatus(OrderOpenStatusEnum.OPEN);
            l_eqtypeOrderUnitParams.setBizDate("20070117");
            l_eqtypeOrderUnitParams.setOrderCateg(OrderCategEnum.CLOSE_MARGIN);
            l_eqtypeOrderUnitParams.setMarketId(1002);
            l_eqtypeOrderUnitParams.setProductId(1006160060005L);
            l_eqtypeOrderUnitParams.setExecutionConditionType(EqTypeExecutionConditionType.AT_MARKET_OPEN);
            l_eqtypeOrderUnitParams.setPriceConditionType("1");
            TestDBUtility.insertWithDel(l_eqtypeOrderUnitParams);
            
            AdministratorParams l_administratorParams = TestDBUtility.getAdministratorRow();
            TestDBUtility.deleteAll(AdministratorRow.TYPE);
            TestDBUtility.insertWithDel(l_administratorParams);
            
            TestDBUtility.deleteAll(BranchRow.TYPE);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setBranchId(33381);
            TestDBUtility.insertWithDel(l_branchParams);
            
            AdminEqForcedSettleOrderParams l_forcedSettleOrderParams =
                new AdminEqForcedSettleOrderParams();
            l_forcedSettleOrderParams.setAccountId(l_mainAccountParams.getAccountId());
            l_forcedSettleOrderParams.setOrderUnitId(l_eqtypeOrderUnitParams.getOrderUnitId());

            WEB3Administrator l_expectAdministrator = new WEB3Administrator(l_administratorParams);

            WEB3AdministratorForMock.mockGetInstanceFromLoginInfo(l_expectAdministrator);
            
            WEB3AdminEquityForcedSettleOrderApproveUnitServiceImpl l_impl =
                new WEB3AdminEquityForcedSettleOrderApproveUnitServiceImpl();
            
            boolean l_blnExecApprove = l_impl.execApprove(l_forcedSettleOrderParams, l_expectAdministrator);

            assertFalse(l_blnExecApprove);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
   
    public void testExecApproveCase0004()
    {
        final String STR_METHOD_NAME = " testExecApproveCase0004";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            this.deleteAll();
            Date l_datExpect = WEB3DateUtility.getDate("20070117","yyyyMMdd");
            WEB3GentradeTradingTimeManagementForMock.mockGetOrderBizDate(l_datExpect);
            
            WEB3AdminEquityForcedSettleOrderApproveUnitServiceImpl l_impl =
                new WEB3AdminEquityForcedSettleOrderApproveUnitServiceImpl();

            EqtypeOrderUnitParams l_eqtypeOrderUnitParams = TestDBUtility.getEqtypeOrderUnitRow();
//            TestDBUtility.deleteAll(EqtypeOrderUnitRow.TYPE);
            l_eqtypeOrderUnitParams.setOrderUnitId(1001);
            l_eqtypeOrderUnitParams.setForcedSettleReasonType("0");
            l_eqtypeOrderUnitParams.setApproveStatusType("0");
            l_eqtypeOrderUnitParams.setOrderOpenStatus(OrderOpenStatusEnum.OPEN);
            l_eqtypeOrderUnitParams.setBizDate("20070117");
            l_eqtypeOrderUnitParams.setOrderCateg(OrderCategEnum.CLOSE_MARGIN);
            l_eqtypeOrderUnitParams.setMarketId(1002);
            l_eqtypeOrderUnitParams.setProductId(1006160060005L);
            l_eqtypeOrderUnitParams.setExecutionConditionType(EqTypeExecutionConditionType.AT_MARKET_OPEN);
            l_eqtypeOrderUnitParams.setPriceConditionType("1");
            TestDBUtility.insertWithDel(l_eqtypeOrderUnitParams);
//            TestDBUtility.deleteAll(EqtypeOrderRow.TYPE);
            
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(l_eqtypeOrderUnitParams.getAccountId());
            l_mainAccountParams.setBranchCode("381");
//            TestDBUtility.deleteAll(MainAccountRow.TYPE); 
            TestDBUtility.insertWithDel(l_mainAccountParams);
            
            AdministratorParams l_administratorParams = TestDBUtility.getAdministratorRow();
            TestDBUtility.deleteAll(AdministratorRow.TYPE);
            TestDBUtility.insertWithDel(l_administratorParams);
            
            
            TraderParams l_traderParams = TestDBUtility.getTraderRow();
            TestDBUtility.deleteAll(TraderRow.TYPE);
            TestDBUtility.insertWithDel(l_traderParams);
            
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setAccountId(l_eqtypeOrderUnitParams.getAccountId());
            l_subAccountParams.setSubAccountId(l_eqtypeOrderUnitParams.getSubAccountId());
            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            TestDBUtility.insertWithDel(l_subAccountParams);
            
            TradedProductParams l_tradedProductParams = TestDBUtility.getTradedProductRow();
            TestDBUtility.deleteAll(TradedProductRow.TYPE);
            l_tradedProductParams.setMarketId(1002);
            l_tradedProductParams.setProductId(1006160060005L);
            TestDBUtility.insertWithDel(l_tradedProductParams);
            
            EqtypeContractParams l_eqTypeContractParams =
                TestDBUtility.getEqtypeContractRow();
            TestDBUtility.deleteAll(EqtypeContractRow.TYPE);
            l_eqTypeContractParams.setTaxType(TaxTypeEnum.SPECIAL);
            l_eqTypeContractParams.setContractId(1001);
            l_eqTypeContractParams.setCloseDate(WEB3DateUtility.getDate("20070111","yyyyMMdd"));
            l_eqTypeContractParams.setMarketId(1002);
            l_eqTypeContractParams.setProductId(1006160060005L);
            l_eqTypeContractParams.setAccountId(l_eqtypeOrderUnitParams.getAccountId());
            l_eqTypeContractParams.setSubAccountId(l_eqtypeOrderUnitParams.getSubAccountId());
            l_eqTypeContractParams.setRepaymentType("1");
            TestDBUtility.insertWithDel(l_eqTypeContractParams);
            
            EqtypeClosingContractSpecParams l_eqTypeClosingContractSpecParams =
                TestDBUtility.getEqtypeClosingContractSpecRow();
            TestDBUtility.deleteAll(EqtypeClosingContractSpecRow.TYPE);
            l_eqTypeClosingContractSpecParams.setOrderUnitId(1001);
            l_eqTypeClosingContractSpecParams.setContractId(1001);
            TestDBUtility.insertWithDel(l_eqTypeClosingContractSpecParams);
            
            EqtypeTradedProductParams l_eqtypeTradedProductParams =
                TestDBUtility.getEqtypeTradedProductRow();
            l_eqtypeTradedProductParams.setProductId(1006160060005L);
            l_eqtypeTradedProductParams.setTradedProductId(l_tradedProductParams.getTradedProductId());
            TestDBUtility.deleteAll(EqtypeTradedProductRow.TYPE);
            TestDBUtility.insertWithDel(l_eqtypeTradedProductParams);
            
            EqtypeProductParams l_eqtypeProductParams =
                TestDBUtility.getEqtypeProductRow();
            l_eqtypeProductParams.setProductType(ProductTypeEnum.EQUITY);
            l_eqtypeProductParams.setProductId(1006160060005L);
            TestDBUtility.deleteAll(EqtypeProductRow.TYPE);
            TestDBUtility.insertWithDel(l_eqtypeProductParams);
            
            ProductParams l_ProductParams = TestDBUtility.getProductRow();
            l_ProductParams.setProductId(1006160060005L);
            l_ProductParams.setProductType(ProductTypeEnum.EQUITY);
            TestDBUtility.deleteAll(ProductRow.TYPE);
            TestDBUtility.insertWithDel(l_ProductParams);
            
            MarketParams l_marketParams = TestDBUtility.getMarketRow();
            l_marketParams.setMarketId(1002);
            TestDBUtility.deleteAll(MarketRow.TYPE);
            TestDBUtility.insertWithDel(l_marketParams);

            AdminEqForcedSettleOrderParams l_forcedSettleOrderParams =
                new AdminEqForcedSettleOrderParams();
            l_forcedSettleOrderParams.setAccountId(l_mainAccountParams.getAccountId());
            l_forcedSettleOrderParams.setOrderUnitId(l_eqtypeOrderUnitParams.getOrderUnitId());

            WEB3Administrator l_expectAdministrator = new WEB3Administrator(l_administratorParams);

            WEB3AdministratorForMock.mockGetInstanceFromLoginInfo(l_expectAdministrator);
            
            WEB3EquityRealizedProfitAndLossPrice l_equityRealizedProfitAndLossPrice =
                new WEB3EquityRealizedProfitAndLossPrice();
            l_equityRealizedProfitAndLossPrice.setCalcUnitPrice(100);
            l_equityRealizedProfitAndLossPrice.setTurnover(100);
            l_equityRealizedProfitAndLossPrice.setEstimatedRealizedProfitAndLossAmount(200);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.equity.WEB3EquityOrderManager",
                "calcEstimatedRealizedProfitAndLossAmount",
                new Class[] {
                    WEB3GentradeCommission.class, double.class,
                    WEB3GentradeSubAccount.class, WEB3EquityTradedProduct.class,
                    EqTypeSettleContractOrderEntry[].class, double.class,
                    EqTypeOrderUnit.class,
                    double.class,
                    double.class,
                    boolean.class,
                    WEB3EquityContract.class},
                l_equityRealizedProfitAndLossPrice);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.equity.service.delegate.stdimpls.WEB3EquityReceiveCloseOrderUnitServiceImpl",
                "execCloseOrder",
                new Class[] {HostEqtypeCloseOrderNotifyParams.class, EqTypeOrderUnit.class},
                null);
            
            EqTypeNewOrderValidationResult l_eqTypeNewOrderValidationResult =    
                 new EqTypeNewOrderValidationResult(ProcessingResult.SUCCESS_RESULT);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.equity.WEB3EquityOrderManager",
                "validateSettleContractOrder",
                new Class[] {SubAccount.class, EqTypeSettleContractOrderSpec.class, WEB3EquityContract.class},
                l_eqTypeNewOrderValidationResult);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.tradingpower.WEB3TPTradingPowerServiceImpl",
                "reCalcTradingPower",
                new Class[] {WEB3GentradeSubAccount.class},
                null);
            
            boolean l_blnExecApprove = l_impl.execApprove(l_forcedSettleOrderParams, l_expectAdministrator);

            assertFalse(l_blnExecApprove);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
        log.exiting(TEST_END + STR_METHOD_NAME);
  }

    public void testExecApproveCase0005()
    {
        final String STR_METHOD_NAME = " testExecApproveCase0005";
        log.entering(TEST_START + STR_METHOD_NAME);
        MOCK_MANAGER.setIsMockUsed(true);
        try
        {  
            Date l_datExpect = WEB3DateUtility.getDate("20070117","yyyyMMdd");
            WEB3GentradeTradingTimeManagementForMock.mockGetOrderBizDate(l_datExpect);
            
            WEB3AdminEquityForcedSettleOrderApproveUnitServiceImpl l_impl =
                new WEB3AdminEquityForcedSettleOrderApproveUnitServiceImpl();

            EqtypeOrderUnitParams l_eqtypeOrderUnitParams = TestDBUtility.getEqtypeOrderUnitRow();
            TestDBUtility.deleteAll(EqtypeOrderUnitRow.TYPE);
            l_eqtypeOrderUnitParams.setOrderUnitId(1001);
            l_eqtypeOrderUnitParams.setForcedSettleReasonType("0");
            l_eqtypeOrderUnitParams.setApproveStatusType("0");
            l_eqtypeOrderUnitParams.setOrderOpenStatus(OrderOpenStatusEnum.OPEN);
            l_eqtypeOrderUnitParams.setBizDate("20070117");
            l_eqtypeOrderUnitParams.setOrderCateg(OrderCategEnum.CLOSE_MARGIN);
            l_eqtypeOrderUnitParams.setMarketId(1002);
            l_eqtypeOrderUnitParams.setProductId(1006160060005L);
            l_eqtypeOrderUnitParams.setExecutionConditionType(EqTypeExecutionConditionType.AT_MARKET_OPEN);
            l_eqtypeOrderUnitParams.setPriceConditionType("1");
            l_eqtypeOrderUnitParams.setOrderType(OrderTypeEnum.CLOSE_MARGIN_LONG);
            TestDBUtility.insertWithDel(l_eqtypeOrderUnitParams);
            
            EqtypeOrderParams l_eqtypeOrderParams = TestDBUtility.getEqtypeOrderRow();
            TestDBUtility.deleteAll(EqtypeOrderRow.TYPE);
            l_eqtypeOrderParams.setOrderId(l_eqtypeOrderUnitParams.getOrderId());
            l_eqtypeOrderParams.setAccountId(l_eqtypeOrderUnitParams.getAccountId());
            l_eqtypeOrderParams.setSubAccountId(l_eqtypeOrderUnitParams.getSubAccountId());
            l_eqtypeOrderParams.setProductType(ProductTypeEnum.EQUITY);            
            TestDBUtility.insertWithDel(l_eqtypeOrderParams);
            
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(l_eqtypeOrderUnitParams.getAccountId());
            l_mainAccountParams.setBranchCode("381");
            TestDBUtility.deleteAll(MainAccountRow.TYPE); 
            TestDBUtility.insertWithDel(l_mainAccountParams);
            
            AdministratorParams l_administratorParams = TestDBUtility.getAdministratorRow();
            TestDBUtility.deleteAll(AdministratorRow.TYPE);
            TestDBUtility.insertWithDel(l_administratorParams);
            
            
            TraderParams l_traderParams = TestDBUtility.getTraderRow();
            TestDBUtility.deleteAll(TraderRow.TYPE);
            TestDBUtility.insertWithDel(l_traderParams);
            
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setAccountId(l_eqtypeOrderUnitParams.getAccountId());
            l_subAccountParams.setSubAccountId(l_eqtypeOrderUnitParams.getSubAccountId());
            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            TestDBUtility.insertWithDel(l_subAccountParams);
            
            TradedProductParams l_tradedProductParams = TestDBUtility.getTradedProductRow();
            TestDBUtility.deleteAll(TradedProductRow.TYPE);
            l_tradedProductParams.setMarketId(1002);
            l_tradedProductParams.setProductId(1006160060005L);
            TestDBUtility.insertWithDel(l_tradedProductParams);
            
            EqtypeContractParams l_eqTypeContractParams =
                TestDBUtility.getEqtypeContractRow();
            TestDBUtility.deleteAll(EqtypeContractRow.TYPE);
            l_eqTypeContractParams.setTaxType(TaxTypeEnum.SPECIAL);
            l_eqTypeContractParams.setContractId(1001);
            l_eqTypeContractParams.setCloseDate(WEB3DateUtility.getDate("20070111","yyyyMMdd"));
            l_eqTypeContractParams.setMarketId(1002);
            l_eqTypeContractParams.setProductId(1006160060005L);
            l_eqTypeContractParams.setAccountId(l_eqtypeOrderUnitParams.getAccountId());
            l_eqTypeContractParams.setSubAccountId(l_eqtypeOrderUnitParams.getSubAccountId());
            l_eqTypeContractParams.setRepaymentType("1");
            TestDBUtility.insertWithDel(l_eqTypeContractParams);
            
            EqtypeClosingContractSpecParams l_eqTypeClosingContractSpecParams =
                TestDBUtility.getEqtypeClosingContractSpecRow();
            TestDBUtility.deleteAll(EqtypeClosingContractSpecRow.TYPE);
            l_eqTypeClosingContractSpecParams.setOrderUnitId(1001);
            l_eqTypeClosingContractSpecParams.setContractId(1001);
            TestDBUtility.insertWithDel(l_eqTypeClosingContractSpecParams);
            
            EqtypeTradedProductParams l_eqtypeTradedProductParams =
                TestDBUtility.getEqtypeTradedProductRow();
            l_eqtypeTradedProductParams.setProductId(1006160060005L);
            l_eqtypeTradedProductParams.setTradedProductId(l_tradedProductParams.getTradedProductId());
            TestDBUtility.deleteAll(EqtypeTradedProductRow.TYPE);
            TestDBUtility.insertWithDel(l_eqtypeTradedProductParams);
            
            EqtypeProductParams l_eqtypeProductParams =
                TestDBUtility.getEqtypeProductRow();
            l_eqtypeProductParams.setProductType(ProductTypeEnum.EQUITY);
            l_eqtypeProductParams.setProductId(1006160060005L);
            TestDBUtility.deleteAll(EqtypeProductRow.TYPE);
            TestDBUtility.insertWithDel(l_eqtypeProductParams);
            
            ProductParams l_ProductParams = TestDBUtility.getProductRow();
            l_ProductParams.setProductId(1006160060005L);
            l_ProductParams.setProductType(ProductTypeEnum.EQUITY);
            TestDBUtility.deleteAll(ProductRow.TYPE);
            TestDBUtility.insertWithDel(l_ProductParams);
            
            MarketParams l_marketParams = TestDBUtility.getMarketRow();
            l_marketParams.setMarketId(1002);
            TestDBUtility.deleteAll(MarketRow.TYPE);
            TestDBUtility.insertWithDel(l_marketParams);

            AdminEqForcedSettleOrderParams l_forcedSettleOrderParams =
                new AdminEqForcedSettleOrderParams();
            l_forcedSettleOrderParams.setAccountId(l_mainAccountParams.getAccountId());
            l_forcedSettleOrderParams.setOrderUnitId(l_eqtypeOrderUnitParams.getOrderUnitId());

            WEB3Administrator l_expectAdministrator = new WEB3Administrator(l_administratorParams);

            WEB3AdministratorForMock.mockGetInstanceFromLoginInfo(l_expectAdministrator);
            
            WEB3EquityRealizedProfitAndLossPrice l_equityRealizedProfitAndLossPrice =
                new WEB3EquityRealizedProfitAndLossPrice();
            l_equityRealizedProfitAndLossPrice.setCalcUnitPrice(100);
            l_equityRealizedProfitAndLossPrice.setTurnover(100);
            l_equityRealizedProfitAndLossPrice.setEstimatedRealizedProfitAndLossAmount(200);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.equity.WEB3EquityOrderManager",
                "calcEstimatedRealizedProfitAndLossAmount",
                new Class[] {
                    WEB3GentradeCommission.class, double.class,
                    WEB3GentradeSubAccount.class, WEB3EquityTradedProduct.class,
                    EqTypeSettleContractOrderEntry[].class, double.class,
                    EqTypeOrderUnit.class,
                    double.class,
                    double.class,
                    boolean.class,
                    WEB3EquityContract.class},
                l_equityRealizedProfitAndLossPrice);

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.equity.WEB3EquityTypeOrderManagerReusableValidations",
                "validateSettleContractTotalQuantity",
                new Class[] 
                  {
                    WEB3GentradeSubAccount.class,
                    long.class,
                    WEB3EquityTradedProduct.class,
                    TaxTypeEnum.class,
                    String.class,
                    double.class,
                    double.class,
                    ContractTypeEnum.class},
                null);
            
            EqTypeNewOrderValidationResult l_eqTypeNewOrderValidationResult =    
                 new EqTypeNewOrderValidationResult(ProcessingResult.SUCCESS_RESULT);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.equity.WEB3EquityOrderManager",
                "validateSettleContractOrder",
                new Class[] {SubAccount.class, EqTypeSettleContractOrderSpec.class, WEB3EquityContract.class},
                l_eqTypeNewOrderValidationResult);
            
            String l_strSubmitOrderRouteDiv = "1";
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.equity.service.delegate.stdimpls.WEB3EquityFrontOrderServiceImpl",
                "getSubmitOrderRouteDiv",
                new Class[] {EqTypeTradedProduct.class, String.class,  String.class},
                l_strSubmitOrderRouteDiv);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.tradingpower.WEB3TPTradingPowerServiceImpl",
                "reCalcTradingPower",
                new Class[] {WEB3GentradeSubAccount.class},
                null);

            boolean l_blnExecApprove = l_impl.execApprove(l_forcedSettleOrderParams, l_expectAdministrator);
            
            assertFalse(l_blnExecApprove);

        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    public void deleteAll()
    {
        try
        {
            TestDBUtility.deleteAll(EqtypeOrderUnitRow.TYPE);
            TestDBUtility.deleteAll(EqtypeOrderRow.TYPE);
            TestDBUtility.deleteAll(MainAccountRow.TYPE); 
            TestDBUtility.deleteAll(AdministratorRow.TYPE);
            TestDBUtility.deleteAll(TraderRow.TYPE);
            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            TestDBUtility.deleteAll(TradedProductRow.TYPE);
            TestDBUtility.deleteAll(EqtypeContractRow.TYPE);
            TestDBUtility.deleteAll(EqtypeClosingContractSpecRow.TYPE);
            TestDBUtility.deleteAll(EqtypeTradedProductRow.TYPE);
            TestDBUtility.deleteAll(EqtypeProductRow.TYPE);
            TestDBUtility.deleteAll(ProductRow.TYPE);
            TestDBUtility.deleteAll(MarketRow.TYPE);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.info("*******************deleteAll***************** !!");
    }
 
    public void testExecDisapprove1()
    {
        final String STR_METHOD_NAME = "testExecDisapprove1()";
        log.entering(STR_METHOD_NAME);
        
        try
        {  
            AdminEqForcedSettleOrderParams l_params = new AdminEqForcedSettleOrderParams();
            l_params.setAccountId(0001L);
            l_params.setAdditionalMarginAccruedDays(20);
            l_params.setAdditionalMarginDate(WEB3DateUtility.getDate("20070405", "yyyyMMdd"));
            l_params.setApproverCode("20");
            l_params.setApproveStatusType("1");
            l_params.setApproveTimestamp(Calendar.getInstance().getTime());
            l_params.setBizDate("20070405");
            l_params.setBranchId(0001);
            l_params.setCloseDate(WEB3DateUtility.getDate("20070405", "yyyyMMdd"));
            l_params.setContractAmount(20);
            l_params.setContractId(001);
            l_params.setContractPrice(10);
            l_params.setContractQuantity(10);
            l_params.setContractType(ContractTypeEnum.LONG);
            l_params.setCreatedTimestamp(Calendar.getInstance().getTime());
            l_params.setDeliveryDate(WEB3DateUtility.getDate("20070405", "yyyyMMdd"));
            l_params.setErrorReasonCode("1");
            l_params.setExpirationStatus(OrderExpirationStatusEnum.EXPIRED);
            l_params.setForcedExpireType("2");
            l_params.setForcedSettleReasonType("3");
            l_params.setLastUpdatedTimestamp(Calendar.getInstance().getTime());
            l_params.setLimitPrice(20);
            l_params.setMarginMaintenanceRate(20);
            l_params.setMarketId(001);
            l_params.setOpenDate(Calendar.getInstance().getTime());
            l_params.setOrderCateg(OrderCategEnum.ASSET);
            l_params.setOrderId(0001);
            l_params.setOrderOpenStatus(OrderOpenStatusEnum.CLOSED);
            l_params.setOrderStatus(OrderStatusEnum.ACCEPTED);
            l_params.setOrderType(OrderTypeEnum.ASSET_IN);
            l_params.setOrderUnitId(001);
            l_params.setOrgContractQuantity(20);
            l_params.setOriginalContractPrice(10);
            l_params.setProductId(0001);
            l_params.setQuantity(30);
            l_params.setReceivedDateTime(Calendar.getInstance().getTime());
            l_params.setRepaymentNum(23);
            l_params.setRepaymentType("0");
            l_params.setSubAccountId(0001);
            l_params.setTaxType(TaxTypeEnum.NORMAL);
            l_params.setTraderId(0001);
            
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(0001L);
            TestDBUtility.insertWithDel(l_mainAccountParams);
            
            TestDBUtility.deleteAll(EqtypeOrderUnitRow.TYPE);
            EqtypeOrderUnitParams l_eqtypeOrderUnitParams = TestDBUtility.getEqtypeOrderUnitRow();
            l_eqtypeOrderUnitParams.setOrderUnitId(001);
            TestDBUtility.insertWithDel(l_eqtypeOrderUnitParams);
            
            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setAccountId(0001L);
            l_subAccountParams.setSubAccountId(0001L);
            TestDBUtility.insertWithDel(l_subAccountParams);
            
            TestDBUtility.deleteAll(AdministratorRow.TYPE);
            AdministratorParams l_administratorParams = TestDBUtility.getAdministratorRow();
            TestDBUtility.insertWithDel(l_administratorParams);
            
            TestDBUtility.deleteAll(InstitutionRow.TYPE);
            InstitutionParams l_InstitutionParams = TestDBUtility.getInstitutionRow();
            l_InstitutionParams.setInstitutionId(33);
            TestDBUtility.insertWithDel(l_InstitutionParams);
            
            TestDBUtility.deleteAll(BranchRow.TYPE);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setBranchId(33381L);
            TestDBUtility.insertWithDel(l_branchParams);
            
            WEB3Administrator l_administratorSet = new WEB3Administrator(l_administratorParams);
            WEB3AdministratorForMock.mockGetInstanceFromLoginInfo(l_administratorSet);

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.gentrade.WEB3GentradeAccountManager",
                    "lockAccount",
                    new Class[] {String.class,String.class,String.class},
                    null);
            
            boolean l_bln = l_mpl.execDisapprove(l_params, l_administratorSet);
            assertEquals(l_bln, false);
        }
        catch (Exception l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testExecDisapprove2()
    {
        final String STR_METHOD_NAME = "testExecDisapprove2()";
        log.entering(STR_METHOD_NAME);
        
        try
        {  
            AdminEqForcedSettleOrderParams l_params = new AdminEqForcedSettleOrderParams();
            l_params.setAccountId(0001L);
            l_params.setAdditionalMarginAccruedDays(20);
            l_params.setAdditionalMarginDate(WEB3DateUtility.getDate("20070405", "yyyyMMdd"));
            l_params.setApproverCode("20");
            l_params.setApproveStatusType("1");
            l_params.setApproveTimestamp(Calendar.getInstance().getTime());
            l_params.setBizDate("20070405");
            l_params.setBranchId(0001);
            l_params.setCloseDate(WEB3DateUtility.getDate("20070405", "yyyyMMdd"));
            l_params.setContractAmount(20);
            l_params.setContractId(001);
            l_params.setContractPrice(10);
            l_params.setContractQuantity(10);
            l_params.setContractType(ContractTypeEnum.LONG);
            l_params.setCreatedTimestamp(Calendar.getInstance().getTime());
            l_params.setDeliveryDate(WEB3DateUtility.getDate("20040202", "yyyyMMdd"));
            l_params.setErrorReasonCode("1");
            l_params.setExpirationStatus(OrderExpirationStatusEnum.EXPIRED);
            l_params.setForcedExpireType("2");
            l_params.setForcedSettleReasonType("3");
            l_params.setLastUpdatedTimestamp(Calendar.getInstance().getTime());
            l_params.setLimitPrice(20);
            l_params.setMarginMaintenanceRate(20);
            l_params.setMarketId(001);
            l_params.setOpenDate(Calendar.getInstance().getTime());
            l_params.setOrderCateg(OrderCategEnum.ASSET);
            l_params.setOrderId(0001);
            l_params.setOrderOpenStatus(OrderOpenStatusEnum.CLOSED);
            l_params.setOrderStatus(OrderStatusEnum.ACCEPTED);
            l_params.setOrderType(OrderTypeEnum.ASSET_IN);
            l_params.setOrderUnitId(001);
            l_params.setOrgContractQuantity(20);
            l_params.setOriginalContractPrice(10);
            l_params.setProductId(0001);
            l_params.setQuantity(30);
            l_params.setReceivedDateTime(Calendar.getInstance().getTime());
            l_params.setRepaymentNum(23);
            l_params.setRepaymentType("0");
            l_params.setSubAccountId(0001);
            l_params.setTaxType(TaxTypeEnum.NORMAL);
            l_params.setTraderId(0001);
            
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(0001L);
            TestDBUtility.insertWithDel(l_mainAccountParams);
            
            TestDBUtility.deleteAll(EqtypeOrderUnitRow.TYPE);
            EqtypeOrderUnitParams l_eqtypeOrderUnitParams = TestDBUtility.getEqtypeOrderUnitRow();
            l_eqtypeOrderUnitParams.setOrderUnitId(001);
            l_eqtypeOrderUnitParams.setAccountId(0001);
            l_eqtypeOrderUnitParams.setSubAccountId(0001);
            l_eqtypeOrderUnitParams.setApproveStatusType(WEB3ApproveStatusType.UNAPPROVED);
            TestDBUtility.insertWithDel(l_eqtypeOrderUnitParams);
            
            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setAccountId(0001L);
            l_subAccountParams.setSubAccountId(0001);
            TestDBUtility.insertWithDel(l_subAccountParams);
            
            TestDBUtility.deleteAll(AdministratorRow.TYPE);
            AdministratorParams l_administratorParams = TestDBUtility.getAdministratorRow();
            TestDBUtility.insertWithDel(l_administratorParams);
            
            TestDBUtility.deleteAll(InstitutionRow.TYPE);
            InstitutionParams l_InstitutionParams = TestDBUtility.getInstitutionRow();
            l_InstitutionParams.setInstitutionId(33);
            TestDBUtility.insertWithDel(l_InstitutionParams);
            
            TestDBUtility.deleteAll(BranchRow.TYPE);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setBranchId(33381L);
            TestDBUtility.insertWithDel(l_branchParams);
            
            WEB3Administrator l_administratorSet = new WEB3Administrator(l_administratorParams);
            WEB3AdministratorForMock.mockGetInstanceFromLoginInfo(l_administratorSet);

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.gentrade.WEB3GentradeAccountManager",
                    "lockAccount",
                    new Class[] {String.class,String.class,String.class},
                    null);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.tradingpower.WEB3TPTradingPowerServiceImpl",
                    "reCalcTradingPower",
                    new Class[] {WEB3GentradeSubAccount.class},
                    null);
            
            Date l_expectDate = WEB3DateUtility.getDate("20070117","yyyyMMdd");
            WEB3GentradeTradingTimeManagementForMock.mockGetOrderBizDate(l_expectDate);
            
            boolean l_bln = l_mpl.execDisapprove(l_params, l_administratorSet);
            assertEquals(l_bln, false);
        }
        catch (Exception l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testExecDisapprove3()
    {
        final String STR_METHOD_NAME = "testExecDisapprove3()";
        log.entering(STR_METHOD_NAME);
        
        try
        {  
            TestDBUtility.deleteAll(AdministratorRow.TYPE);
            AdministratorParams l_administratorParams = TestDBUtility.getAdministratorRow();
            TestDBUtility.insertWithDel(l_administratorParams);
            
            WEB3Administrator l_administratorSet = new WEB3Administrator(l_administratorParams);
            WEB3AdministratorForMock.mockGetInstanceFromLoginInfo(l_administratorSet);
            AdminEqForcedSettleOrderParams l_params =
                new AdminEqForcedSettleOrderParams();

            boolean l_bln = l_mpl.execDisapprove(l_params, l_administratorSet);
            assertEquals(l_bln, true);
        }
        catch (WEB3BaseException l_ex)
        {
            log.debug(STR_METHOD_NAME, l_ex);
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80005, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testExecDisapprove4()
    {
        final String STR_METHOD_NAME = "testExecDisapprove4()";
        log.entering(STR_METHOD_NAME);
        
        try
        {  
            TestDBUtility.deleteAll(AdministratorRow.TYPE);
            AdministratorParams l_administratorParams = TestDBUtility.getAdministratorRow();
            TestDBUtility.insertWithDel(l_administratorParams);
            
            WEB3Administrator l_administratorSet = new WEB3Administrator(l_administratorParams);
            WEB3AdministratorForMock.mockGetInstanceFromLoginInfo(l_administratorSet);
            AdminEqForcedSettleOrderRow l_row = null;

            l_mpl.execDisapprove(l_row, l_administratorSet);

        }
        catch (WEB3BaseException l_ex)
        {
            log.debug(STR_METHOD_NAME, l_ex);
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80017, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testExecDisapprove5()
    {
        final String STR_METHOD_NAME = "testExecDisapprove5()";
        log.entering(STR_METHOD_NAME);
        
        try
        {  
            TestDBUtility.deleteAll(AdministratorRow.TYPE);
            AdministratorParams l_administratorParams = TestDBUtility.getAdministratorRow();
            TestDBUtility.insertWithDel(l_administratorParams);
            
            WEB3Administrator l_administratorSet = new WEB3Administrator(l_administratorParams);
            WEB3AdministratorForMock.mockGetInstanceFromLoginInfo(l_administratorSet);
            AdminEqForcedSettleOrderRow l_row = null;

            l_mpl.execDisapprove(l_row, l_administratorSet);

        }
        catch (WEB3BaseException l_ex)
        {
            log.debug(STR_METHOD_NAME, l_ex);
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80017, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testUpdateOrderData()
    {
        final String STR_METHOD_NAME = "testUpdateOrderData()";
        log.entering(STR_METHOD_NAME);
        try
        {
            TestDBUtility.deleteAll(EqtypeOrderActionRow.TYPE);
            TestDBUtility.deleteAll(EqtypeOrderUnitRow.TYPE);
            EqtypeOrderUnitParams l_eqtypeOrderUnitParams = TestDBUtility.getEqtypeOrderUnitRow();
            TestDBUtility.insertWithDel(l_eqtypeOrderUnitParams);
    
            TestDBUtility.deleteAll(AdministratorRow.TYPE);
            AdministratorParams l_administratorParams = TestDBUtility.getAdministratorRow();
            l_administratorParams.setLoginId(0);
            TestDBUtility.insertWithDel(l_administratorParams);

            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(l_eqtypeOrderUnitParams.getAccountId());
            l_mainAccountParams.setBranchCode("381");
            TestDBUtility.deleteAll(MainAccountRow.TYPE); 
            TestDBUtility.insertWithDel(l_mainAccountParams);
            
            LoginInfo loginInfo = new LoginInfoImpl();
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl", 
                    "getLoginInfo",
                    new Class[] {},
                    loginInfo);
            WEB3Administrator l_admin = WEB3Administrator.getInstanceFromLoginInfo();
            
            EqtypeOrderUnitRow l_eqtypeOrderUnitRow1 = EqtypeOrderUnitDao.findRowByOrderUnitId(3304148080001L);
            
            WEB3EquityRealizedProfitAndLossPrice l_equityRealizedProfitAndLossPrice =
                new WEB3EquityRealizedProfitAndLossPrice();
            l_equityRealizedProfitAndLossPrice.setCalcUnitPrice(20);
            l_equityRealizedProfitAndLossPrice.setEstimatedRealizedProfitAndLossAmount(20);
            l_equityRealizedProfitAndLossPrice.setTurnover(20);
            String l_strSubmitOrderRouteDiv = "1";
            
            WEB3AdminEquityForcedSettleOrderApproveUnitServiceImpl l_mpl1 =
                new WEB3AdminEquityForcedSettleOrderApproveUnitServiceImpl();
            l_mpl1.updateOrderData(l_admin,
                l_eqtypeOrderUnitRow1,
                l_equityRealizedProfitAndLossPrice,
                l_strSubmitOrderRouteDiv,
                true);
            EqtypeOrderUnitRow l_eqtypeOrderUnitRow = EqtypeOrderUnitDao.findRowByOrderUnitId(3304148080001L);
            assertEquals(l_eqtypeOrderUnitRow.getPrice(), new Double(20).doubleValue(), new Double(20.0).doubleValue());
            assertEquals(l_eqtypeOrderUnitRow.getEstimatedPrice(),new Double(20).doubleValue(), new Double(20.0).doubleValue() );
            assertEquals(l_eqtypeOrderUnitRow.getSubmitOrderRouteDiv(), "1");
            assertEquals(l_eqtypeOrderUnitRow.getErrorReasonCode(), "0000");
            assertEquals(l_eqtypeOrderUnitRow.getApproveStatusType(),"1");
            assertEquals(l_eqtypeOrderUnitRow.getApproverCode(),"330001");
            List l_lis = EqtypeOrderActionDao.findRowsByAccountId(333812512203L);
            EqtypeOrderActionRow l_orderActionRow = (EqtypeOrderActionRow)l_lis.get(0);
            
            assertEquals(l_orderActionRow.getAccountId(), 333812512203L);
            assertEquals(l_orderActionRow.getSubAccountId(), 33381251220301L);
            assertEquals(l_orderActionRow.getTraderId(), 3338111123L);
            assertEquals(l_orderActionRow.getOrderId(), 123456789);
            assertEquals(l_orderActionRow.getOrderUnitId(), 3304148080001L);
            assertEquals(l_orderActionRow.getMarketId(), 3303);
            assertEquals(l_orderActionRow.getOrderType().stringValue(), "SWAP_MARGIN_LONG");
            assertEquals(l_orderActionRow.getOrderEventType().toString(), OrderEventTypeEnum.APPROVED.toString());
            assertEquals(l_orderActionRow.getPrice(), new Double(20).doubleValue(), new Double(20.0).doubleValue());
            assertEquals(l_orderActionRow.getExecutionConditionType().stringValue(), "LIMIT_PRICE");
            assertEquals(l_orderActionRow.getExpirationStatus().stringValue(), "EXPIRED");
            assertEquals(l_orderActionRow.getOrderActionSerialNo(), 87);
            assertEquals(l_orderActionRow.getProductType().stringValue(), "EQUITY");
            assertEquals(l_orderActionRow.getProductId(), 3304148080001L);
            assertEquals(l_orderActionRow.getQuantityType().stringValue(), "QUANTITY");
            assertEquals(l_orderActionRow.getEstimatedPrice(), new Double(20).doubleValue(), new Double(20.0).doubleValue());
            assertEquals(l_orderActionRow.getErrorReasonCode(), "0000");
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }

    public void testUpdateOrderData1()
    {
        final String STR_METHOD_NAME = "testUpdateOrderData1()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(333812512203L);
            TestDBUtility.insertWithDel(l_mainAccountParams);
            
            TestDBUtility.deleteAll(EqtypeOrderUnitRow.TYPE);
            EqtypeOrderUnitParams l_eqtypeOrderUnitParams = TestDBUtility.getEqtypeOrderUnitRow();
            TestDBUtility.insertWithDel(l_eqtypeOrderUnitParams);
            
            TestDBUtility.deleteAll(AdministratorRow.TYPE);
            AdministratorParams l_administratorParams = TestDBUtility.getAdministratorRow();
            l_administratorParams.setLoginId(0);
            TestDBUtility.insertWithDel(l_administratorParams);
    
            LoginInfo loginInfo = new LoginInfoImpl();
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl", 
                    "getLoginInfo",
                    new Class[] {},
                    loginInfo);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.equity.service.delegate.stdimpls.WEB3EquityReceiveCloseOrderUnitServiceImpl",
                    "execCloseOrder",
                    new Class[] {HostEqtypeCloseOrderNotifyParams.class, EqTypeOrderUnit.class},
                    null);
            
            WEB3Administrator l_admin = WEB3Administrator.getInstanceFromLoginInfo();
            
            EqtypeOrderUnitRow l_eqtypeOrderUnitRow = EqtypeOrderUnitDao.findRowByOrderUnitId(l_eqtypeOrderUnitParams.getOrderUnitId());
            WEB3EquityRealizedProfitAndLossPrice l_equityRealizedProfitAndLossPrice =
                new WEB3EquityRealizedProfitAndLossPrice();
            l_equityRealizedProfitAndLossPrice.setCalcUnitPrice(20);
            l_equityRealizedProfitAndLossPrice.setEstimatedRealizedProfitAndLossAmount(20);
            l_equityRealizedProfitAndLossPrice.setTurnover(20);
            String l_strSubmitOrderRouteDiv = "1";
            
            WEB3AdminEquityForcedSettleOrderApproveUnitServiceImpl l_mpl1 =
                new WEB3AdminEquityForcedSettleOrderApproveUnitServiceImpl();
            l_mpl1.updateOrderData(l_admin,
                l_eqtypeOrderUnitRow,
                l_equityRealizedProfitAndLossPrice,
                l_strSubmitOrderRouteDiv,
                false);
        }
        catch (Exception l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }

    public void testUpdateOrderExpire()
    {
        final String STR_METHOD_NAME = "testUpdateOrderExpire()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            TestDBUtility.deleteAll(EqtypeOrderUnitRow.TYPE);
            EqtypeOrderUnitParams l_eqtypeOrderUnitParams = TestDBUtility.getEqtypeOrderUnitRow();
            l_eqtypeOrderUnitParams.setOrderRequestNumber("1234");
            TestDBUtility.insertWithDel(l_eqtypeOrderUnitParams);
            
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(333812512203L);
            TestDBUtility.insertWithDel(l_mainAccountParams);
            
            TestDBUtility.deleteAll(MarketRow.TYPE);
            MarketParams l_marketParams = TestDBUtility.getMarketRow();
            l_marketParams.setMarketId(3303);
            TestDBUtility.insertWithDel(l_marketParams);
            
            TestDBUtility.deleteAll(AdministratorRow.TYPE);
            AdministratorParams l_administratorParams = TestDBUtility.getAdministratorRow();
            l_administratorParams.setLoginId(0);
            TestDBUtility.insertWithDel(l_administratorParams);
            
            String l_strErrorCode = "0000";
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.equity.WEB3EquityOrderManager",
                    "getErrorReasonCode",
                    new Class[] {String.class},
                    l_strErrorCode);
            
            LoginInfo loginInfo = new LoginInfoImpl();
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl", 
                    "getLoginInfo",
                    new Class[] {},
                    loginInfo);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.equity.service.delegate.stdimpls.WEB3EquityReceiveCloseOrderUnitServiceImpl",
                    "execCloseOrder",
                    new Class[] {HostEqtypeCloseOrderNotifyParams.class, EqTypeOrderUnit.class},
                    null);
            
            WEB3Administrator l_admin = WEB3Administrator.getInstanceFromLoginInfo();
            
            EqtypeOrderUnitRow l_eqtypeOrderUnitRow = EqtypeOrderUnitDao.findRowByOrderUnitId(3304148080001L); 
            WEB3AdminEquityForcedSettleOrderApproveUnitServiceImpl l_mpl1 =
                new WEB3AdminEquityForcedSettleOrderApproveUnitServiceImpl();
            
            l_mpl1.updateOrderExpire(
                l_admin,
                l_eqtypeOrderUnitRow,
                l_strErrorCode,
                false);
            
            EqtypeOrderUnitRow l_row = EqtypeOrderUnitDao.findRowByOrderUnitId(3304148080001L);
            Long l_lngAccountIdCtx = (Long)ThreadLocalSystemAttributesRegistry.getAttribute("MPDS_KEY_VALUE");
            assertEquals(new Long(333812512203L), l_lngAccountIdCtx);
            assertEquals(l_row.getApproveStatusType(), "2");
            assertEquals(l_row.getApproverCode(), "330001");
            
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testUpdateOrderExpire1()
    {
        final String STR_METHOD_NAME = "testUpdateOrderExpire1()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            TestDBUtility.deleteAll(EqtypeOrderUnitRow.TYPE);
            EqtypeOrderUnitParams l_eqtypeOrderUnitParams = TestDBUtility.getEqtypeOrderUnitRow();
            l_eqtypeOrderUnitParams.setOrderRequestNumber("1234");
            TestDBUtility.insertWithDel(l_eqtypeOrderUnitParams);
            
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(333812512203L);
            TestDBUtility.insertWithDel(l_mainAccountParams);
            
            TestDBUtility.deleteAll(MarketRow.TYPE);
            MarketParams l_marketParams = TestDBUtility.getMarketRow();
            l_marketParams.setMarketId(3303);
            TestDBUtility.insertWithDel(l_marketParams);
            
            TestDBUtility.deleteAll(AdministratorRow.TYPE);
            AdministratorParams l_administratorParams = TestDBUtility.getAdministratorRow();
            l_administratorParams.setLoginId(0);
            TestDBUtility.insertWithDel(l_administratorParams);
            
            
            LoginInfo loginInfo = new LoginInfoImpl();
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl", 
                    "getLoginInfo",
                    new Class[] {},
                    loginInfo);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.equity.service.delegate.stdimpls.WEB3EquityReceiveCloseOrderUnitServiceImpl",
                    "execCloseOrder",
                    new Class[] {HostEqtypeCloseOrderNotifyParams.class, EqTypeOrderUnit.class},
                    null);
            
            WEB3Administrator l_admin = WEB3Administrator.getInstanceFromLoginInfo();
            
            EqtypeOrderUnitRow l_eqtypeOrderUnitRow = EqtypeOrderUnitDao.findRowByOrderUnitId(3304148080001L); 
            WEB3AdminEquityForcedSettleOrderApproveUnitServiceImpl l_mpl1 =
                new WEB3AdminEquityForcedSettleOrderApproveUnitServiceImpl();
            
            l_mpl1.updateOrderExpire(
                l_admin,
                l_eqtypeOrderUnitRow,
                null,
                true);
            
            EqtypeOrderUnitRow l_row = EqtypeOrderUnitDao.findRowByOrderUnitId(3304148080001L);
            assertEquals(l_row.getApproveStatusType(), "9");
            assertEquals(l_row.getApproverCode(), "330001");
            
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testUpdateOrderExpire2()
    {
        final String STR_METHOD_NAME = "testUpdateOrderExpire2()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            TestDBUtility.deleteAll(EqtypeOrderUnitRow.TYPE);
            EqtypeOrderUnitParams l_eqtypeOrderUnitParams = TestDBUtility.getEqtypeOrderUnitRow();
            l_eqtypeOrderUnitParams.setOrderRequestNumber("1234");
            TestDBUtility.insertWithDel(l_eqtypeOrderUnitParams);
            
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(333822512203L);
            TestDBUtility.insertWithDel(l_mainAccountParams);

            LoginInfo loginInfo = new LoginInfoImpl();
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl", 
                    "getLoginInfo",
                    new Class[] {},
                    loginInfo);
            WEB3Administrator l_admin = WEB3Administrator.getInstanceFromLoginInfo();
            
            EqtypeOrderUnitRow l_eqtypeOrderUnitRow = EqtypeOrderUnitDao.findRowByOrderUnitId(3304148080001L); 
            WEB3AdminEquityForcedSettleOrderApproveUnitServiceImpl l_mpl1 =
                new WEB3AdminEquityForcedSettleOrderApproveUnitServiceImpl();
            
            l_mpl1.updateOrderExpire(
                l_admin,
                l_eqtypeOrderUnitRow,
                null,
                true);
        }
        catch (WEB3BaseException l_ex)
        {
            log.debug(STR_METHOD_NAME, l_ex);
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80005, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testUpdateOrderExpire3()
    {
        final String STR_METHOD_NAME = "testUpdateOrderExpire3()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            TestDBUtility.deleteAll(EqtypeOrderUnitRow.TYPE);
            EqtypeOrderUnitParams l_eqtypeOrderUnitParams = TestDBUtility.getEqtypeOrderUnitRow();
            l_eqtypeOrderUnitParams.setOrderRequestNumber("1234");
            TestDBUtility.insertWithDel(l_eqtypeOrderUnitParams);
            
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(333812512203L);
            TestDBUtility.insertWithDel(l_mainAccountParams);
            
            TestDBUtility.deleteAll(MarketRow.TYPE);
            MarketParams l_marketParams = TestDBUtility.getMarketRow();
            l_marketParams.setMarketId(3303);
            TestDBUtility.insertWithDel(l_marketParams);
            
            TestDBUtility.deleteAll(AdministratorRow.TYPE);
            AdministratorParams l_administratorParams = TestDBUtility.getAdministratorRow();
            l_administratorParams.setLoginId(0);
            TestDBUtility.insertWithDel(l_administratorParams);
            
            
            LoginInfo loginInfo = new LoginInfoImpl();
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl", 
                    "getLoginInfo",
                    new Class[] {},
                    loginInfo);
            ErrorInfo errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80003;
            String errorMethod = "DBへのアクセスに失敗しました。";
            WEB3BaseException l_ex = new WEB3BaseException(errorInfo, errorMethod);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.equity.service.delegate.stdimpls.WEB3EquityReceiveCloseOrderUnitServiceImpl",
                    "execCloseOrder",
                    new Class[] {HostEqtypeCloseOrderNotifyParams.class, EqTypeOrderUnit.class},
                    l_ex);
            
            WEB3Administrator l_admin = WEB3Administrator.getInstanceFromLoginInfo();
            
            EqtypeOrderUnitRow l_eqtypeOrderUnitRow = EqtypeOrderUnitDao.findRowByOrderUnitId(3304148080001L); 
            WEB3AdminEquityForcedSettleOrderApproveUnitServiceImpl l_mpl1 =
                new WEB3AdminEquityForcedSettleOrderApproveUnitServiceImpl();
            
            l_mpl1.updateOrderExpire(
                l_admin,
                l_eqtypeOrderUnitRow,
                null,
                true);
        }
        catch (WEB3BaseException l_ex)
        {
            log.debug(STR_METHOD_NAME, l_ex);
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80003, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    
    public class WEB3AdminEquityForcedSettleOrderApproveUnitServiceImplForTest extends WEB3AdminEquityForcedSettleOrderApproveUnitServiceImpl
    {
        protected void updateOrderData(
                WEB3Administrator l_admin,
                EqtypeOrderUnitRow l_eqtypeOrderUnitRow,
                WEB3EquityRealizedProfitAndLossPrice l_equityRealizedProfitAndLossPrice,
                String l_strSubmitOrderRouteDiv,
                boolean l_blnIsApprove) throws WEB3BaseException
            {
            
            }
        protected void updateOrderExpire(
                WEB3Administrator l_admin,
                EqtypeOrderUnitRow l_eqtypeOrderUnitRow,
                String l_strErrorCode,
                boolean l_blnIsApprove) throws WEB3BaseException
        {
            
        }
               
    }
    
    //參照QA：WEB3-EQTYPEADMIN-A-FT-0033
    //以及式樣變更：【株管理者】仕様変更管理台帳（モデル）No.155
    public void testUpdateOrderExpireC1()
    {
        final String STR_METHOD_NAME = "testUpdateOrderExpireC1()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            TestDBUtility.deleteAll(EqtypeOrderUnitRow.TYPE);
            EqtypeOrderUnitParams l_eqtypeOrderUnitParams = TestDBUtility.getEqtypeOrderUnitRow();
            l_eqtypeOrderUnitParams.setOrderRequestNumber("1234");
            TestDBUtility.insertWithDel(l_eqtypeOrderUnitParams);
            
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(333812512203L);
            TestDBUtility.insertWithDel(l_mainAccountParams);
            
            TestDBUtility.deleteAll(MarketRow.TYPE);
            MarketParams l_marketParams = TestDBUtility.getMarketRow();
            l_marketParams.setMarketId(3303);
            TestDBUtility.insertWithDel(l_marketParams);
            
            TestDBUtility.deleteAll(AdministratorRow.TYPE);
            AdministratorParams l_administratorParams = TestDBUtility.getAdministratorRow();
            l_administratorParams.setLoginId(0);
            TestDBUtility.insertWithDel(l_administratorParams);
            
            MOCK_MANAGER.setIsMockUsed(true);
            LoginInfo loginInfo = new LoginInfoImpl();
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl", 
                    "getLoginInfo",
                    new Class[] {},
                    loginInfo);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.equity.service.delegate.stdimpls.WEB3EquityReceiveCloseOrderUnitServiceImpl",
                    "execCloseOrder",
                    new Class[] {HostEqtypeCloseOrderNotifyParams.class, EqTypeOrderUnit.class},
                    null);
            
            WEB3Administrator l_admin = WEB3Administrator.getInstanceFromLoginInfo();
            
            EqtypeOrderUnitRow l_eqtypeOrderUnitRow = EqtypeOrderUnitDao.findRowByOrderUnitId(3304148080001L); 
            WEB3AdminEquityForcedSettleOrderApproveUnitServiceImpl l_mpl1 =
                new WEB3AdminEquityForcedSettleOrderApproveUnitServiceImpl();
            
            l_mpl1.updateOrderExpire(
                l_admin,
                l_eqtypeOrderUnitRow,
                null,
                true);
            
            EqtypeOrderUnitRow l_row = EqtypeOrderUnitDao.findRowByOrderUnitId(3304148080001L);
            assertEquals(l_row.getApproveStatusType(), "9");
            assertEquals(l_row.getApproverCode(), "330001");
            
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testUpdateOrderExpireC2()
    {
        final String STR_METHOD_NAME = "testUpdateOrderExpireC2()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            TestDBUtility.deleteAll(EqtypeOrderUnitRow.TYPE);
            EqtypeOrderUnitParams l_eqtypeOrderUnitParams = TestDBUtility.getEqtypeOrderUnitRow();
            l_eqtypeOrderUnitParams.setOrderRequestNumber("1234");
            TestDBUtility.insertWithDel(l_eqtypeOrderUnitParams);
            
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(333812512203L);
            TestDBUtility.insertWithDel(l_mainAccountParams);
            
            TestDBUtility.deleteAll(MarketRow.TYPE);
            MarketParams l_marketParams = TestDBUtility.getMarketRow();
            l_marketParams.setMarketId(3303);
            TestDBUtility.insertWithDel(l_marketParams);
            
            TestDBUtility.deleteAll(AdministratorRow.TYPE);
            AdministratorParams l_administratorParams = TestDBUtility.getAdministratorRow();
            l_administratorParams.setLoginId(0);
            TestDBUtility.insertWithDel(l_administratorParams);
            
            MOCK_MANAGER.setIsMockUsed(true);
            LoginInfo loginInfo = new LoginInfoImpl();
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl", 
                    "getLoginInfo",
                    new Class[] {},
                    loginInfo);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.equity.service.delegate.stdimpls.WEB3EquityReceiveCloseOrderUnitServiceImpl",
                    "execCloseOrder",
                    new Class[] {HostEqtypeCloseOrderNotifyParams.class, EqTypeOrderUnit.class},
                    null);
            
            WEB3Administrator l_admin = WEB3Administrator.getInstanceFromLoginInfo();
            
            EqtypeOrderUnitRow l_eqtypeOrderUnitRow = EqtypeOrderUnitDao.findRowByOrderUnitId(3304148080001L); 
            WEB3AdminEquityForcedSettleOrderApproveUnitServiceImpl l_mpl1 =
                new WEB3AdminEquityForcedSettleOrderApproveUnitServiceImpl();
            
            l_mpl1.updateOrderExpire(
                l_admin,
                l_eqtypeOrderUnitRow,
                null,
                false);
            
            EqtypeOrderUnitRow l_row = EqtypeOrderUnitDao.findRowByOrderUnitId(3304148080001L);
            assertEquals(l_row.getApproveStatusType(), "2");
            assertEquals(l_row.getApproverCode(), "330001");
            
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testUpdateOrderExpireC3()
    {
        final String STR_METHOD_NAME = "testUpdateOrderExpireC3()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            TestDBUtility.deleteAll(EqtypeOrderUnitRow.TYPE);
            EqtypeOrderUnitParams l_eqtypeOrderUnitParams = TestDBUtility.getEqtypeOrderUnitRow();
            l_eqtypeOrderUnitParams.setOrderRequestNumber("1234");
            l_eqtypeOrderUnitParams.setOrderType(OrderTypeEnum.EQUITY_BUY);
            TestDBUtility.insertWithDel(l_eqtypeOrderUnitParams);
            
            TestDBUtility.deleteAll(EqtypeOrderRow.TYPE);
            EqtypeOrderParams l_eqtypeOrderParams = TestDBUtility.getEqtypeOrderRow();
            l_eqtypeOrderParams.setOrderId(123456789L);
            TestDBUtility.insertWithDel(l_eqtypeOrderParams);
            
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(333812512203L);
            TestDBUtility.insertWithDel(l_mainAccountParams);
            
            TestDBUtility.deleteAll(MarketRow.TYPE);
//            MarketParams l_marketParams = TestDBUtility.getMarketRow();
//            l_marketParams.setMarketId(3303);
//            TestDBUtility.insertWithDel(l_marketParams);
            
            TestDBUtility.deleteAll(AdministratorRow.TYPE);
            AdministratorParams l_administratorParams = TestDBUtility.getAdministratorRow();
            l_administratorParams.setLoginId(0);
            TestDBUtility.insertWithDel(l_administratorParams);
            
            MOCK_MANAGER.setIsMockUsed(true);
            LoginInfo loginInfo = new LoginInfoImpl();
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl", 
                    "getLoginInfo",
                    new Class[] {},
                    loginInfo);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.equity.service.delegate.stdimpls.WEB3EquityReceiveCloseOrderUnitServiceImpl",
                    "execCloseOrder",
                    new Class[] {HostEqtypeCloseOrderNotifyParams.class, EqTypeOrderUnit.class},
                     new WEB3SystemLayerException(
                            WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                            WEB3Administrator.class +"." + STR_METHOD_NAME));

            WEB3Administrator l_admin = WEB3Administrator.getInstanceFromLoginInfo();
            EqtypeOrderUnitRow l_eqtypeOrderUnitRow = EqtypeOrderUnitDao.findRowByOrderUnitId(3304148080001L); 
            WEB3AdminEquityForcedSettleOrderApproveUnitServiceImpl l_mpl1 =
                new WEB3AdminEquityForcedSettleOrderApproveUnitServiceImpl();
            
            l_mpl1.updateOrderExpire(
                l_admin,
                l_eqtypeOrderUnitRow,
                null,
                false);
            
            EqtypeOrderUnitRow l_row = EqtypeOrderUnitDao.findRowByOrderUnitId(3304148080001L);
            assertEquals(l_row.getApproveStatusType(), "9");
            assertEquals(l_row.getApproverCode(), "330001");
            
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
