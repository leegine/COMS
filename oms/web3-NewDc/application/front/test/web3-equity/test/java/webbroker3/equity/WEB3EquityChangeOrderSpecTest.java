head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.01.12.42;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3EquityChangeOrderSpecTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 株式注文訂正内容Test(WEB3EquityChangeOrderSpecTest.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/12/30 趙林鵬(中訊) 新規作成
*/

package webbroker3.equity;

import java.util.Date;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeExecutionConditionType;
import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeOrderUnit;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderUnitParams;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderUnitRow;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeProductParams;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeProductRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.BranchParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.BranchRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.InstitutionParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.InstitutionRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MarketParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MarketRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.ProductParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.ProductRow;

import test.util.TestDBUtility;

import webbroker3.gentrade.WEB3GentradeTrader;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagementForMock;
import webbroker3.gentrade.data.TradingTimeParams;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

public class WEB3EquityChangeOrderSpecTest extends TestBaseForMock
{
    private static WEB3LogUtility log = 
        WEB3LogUtility.getInstance(WEB3EquityChangeOrderSpecTest.class);

    public WEB3EquityChangeOrderSpecTest(String arg0)
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
    
    /**
     * －this.株式注文内容がnullでない場合、this.株式注文内容を返却し処理を終了する。 
     */
    public void testCreatePTSOrderSpecCase0001()
    {
        final String STR_METHOD_NAME = "testCreatePTSOrderSpecCase0001()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            //BranchParams
            TestDBUtility.deleteAll(BranchRow.TYPE);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            TestDBUtility.insertWithDel(l_branchParams);
            
            TestDBUtility.deleteAll(MarketRow.TYPE);
            MarketParams l_marketRow = TestDBUtility.getMarketRow();
            l_marketRow.setMarketCode("1");
            TestDBUtility.insertWithDel(l_marketRow);
            
            //ProductParams
            TestDBUtility.deleteAll(ProductRow.TYPE);
            ProductParams l_productParams = TestDBUtility.getProductRow();
            l_productParams.setProductId(1006169090018L);
            l_productParams.setProductType(ProductTypeEnum.EQUITY);
            TestDBUtility.insertWithDel(l_productParams);
            
            TestDBUtility.deleteAll(EqtypeProductRow.TYPE);
            EqtypeProductParams l_EqtypeProductParams = TestDBUtility.getEqtypeProductRow();
            l_EqtypeProductParams.setProductId(1006169090018L);
            l_EqtypeProductParams.setProductCode("0");
            TestDBUtility.insertWithDel(l_EqtypeProductParams);
            
            TestDBUtility.deleteAll(EqtypeOrderUnitRow.TYPE);
            EqtypeOrderUnitParams l_eqtypeOrderUnitRow = TestDBUtility.getEqtypeOrderUnitRow();
            l_eqtypeOrderUnitRow.setBranchId(l_branchParams.getBranchId());
            l_eqtypeOrderUnitRow.setMarketId(l_marketRow.getMarketId());
            l_eqtypeOrderUnitRow.setProductId(1006169090018L);
            l_eqtypeOrderUnitRow.setOrderType(OrderTypeEnum.EQUITY_SELL);
            TestDBUtility.insertWithDel(l_eqtypeOrderUnitRow);
            
            TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams.setInstitutionCode("0D");
            l_tradingTimeParams.setBranchCode("123");
            l_tradingTimeParams.setBizDateType("4");
            l_tradingTimeParams.setMarketCode("N1");
            l_tradingTimeParams.setProductCode("0");
            l_tradingTimeParams.setTradingTimeType("01");
            TestDBUtility.insertWithDel(l_tradingTimeParams);
            
            //InstitutionParams
            TestDBUtility.deleteAll(InstitutionRow.TYPE);
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            TestDBUtility.insertWithDel(l_institutionParams);

            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
            TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
            WEB3EquityOrderManager l_equityOrderManager =
                (WEB3EquityOrderManager)l_tradingModule.getOrderManager();
            EqTypeOrderUnit l_eqtypeOrderUnit =
                (EqTypeOrderUnit)l_equityOrderManager.getOrderUnit(l_eqtypeOrderUnitRow.getOrderUnitId());
            
            WEB3GentradeTrader l_trader = null;
            Date l_dat = WEB3DateUtility.getDate("20071227", "yyyyMMdd");
            
            WEB3GentradeTradingTimeManagementForMock.mockGetOrderBizDate(l_dat);
            EqTypeExecutionConditionType l_eqTypeExecutionconditionType = null;
            long l_lngOrderId = 1001;
            WEB3EquityChangeOrderUnitEntry l_eqTypeChangeOrderUnitEntry =
                new WEB3EquityChangeOrderUnitEntry(
                    l_eqTypeExecutionconditionType,
                    l_eqtypeOrderUnit,
                    false,
                    "1", "1", "1",
                    200, 300,
                    l_dat, l_eqTypeExecutionconditionType, "1");;

            String l_strInstitutionCode = "0D";
            String l_strOrderChannel = "1";
            WEB3EquityChangeOrderSpec l_changeOrderSpec =
                new WEB3EquityChangeOrderSpec(
                        l_lngOrderId, l_eqTypeChangeOrderUnitEntry, 
                        l_strInstitutionCode, l_strOrderChannel,l_trader);

            l_changeOrderSpec.createPTSOrderSpec();
            
            WEB3EquityNewCashBasedOrderSpec l_specAfter =  l_changeOrderSpec.createPTSOrderSpec();
            
            
            assertEquals("10", l_specAfter.getCommissionProductCode());
            assertEquals("33381", l_specAfter.getCommission().getBranchId() + "");
            assertEquals("0D", l_specAfter.getCommission().getInstitutionCode());
            
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    /**
     * －this.株式注文内容がnullの場合
     * 株式注文内容オブジェクト.createPTS株式注文内容( )にて株式注文内容インスタンスを生成する。
     * ”現物売注文”の場合はis売注文 = true
     * 注文単位.初回注文の注文単位ID = null;
     */
    public void testCreatePTSOrderSpecCase0002()
    {
        final String STR_METHOD_NAME = "testCreatePTSOrderSpecCase0002()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            //BranchParams
            TestDBUtility.deleteAll(BranchRow.TYPE);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            TestDBUtility.insertWithDel(l_branchParams);
            
            TestDBUtility.deleteAll(MarketRow.TYPE);
            MarketParams l_marketRow = TestDBUtility.getMarketRow();
            l_marketRow.setMarketCode("1");
            TestDBUtility.insertWithDel(l_marketRow);
            
            //ProductParams
            TestDBUtility.deleteAll(ProductRow.TYPE);
            ProductParams l_productParams = TestDBUtility.getProductRow();
            l_productParams.setProductId(1006169090018L);
            l_productParams.setProductType(ProductTypeEnum.EQUITY);
            TestDBUtility.insertWithDel(l_productParams);
            
            TestDBUtility.deleteAll(EqtypeProductRow.TYPE);
            EqtypeProductParams l_EqtypeProductParams = TestDBUtility.getEqtypeProductRow();
            l_EqtypeProductParams.setProductId(1006169090018L);
            l_EqtypeProductParams.setProductCode("0");
            TestDBUtility.insertWithDel(l_EqtypeProductParams);
            
            TestDBUtility.deleteAll(EqtypeOrderUnitRow.TYPE);
            EqtypeOrderUnitParams l_eqtypeOrderUnitRow = TestDBUtility.getEqtypeOrderUnitRow();
            l_eqtypeOrderUnitRow.setBranchId(l_branchParams.getBranchId());
            l_eqtypeOrderUnitRow.setMarketId(l_marketRow.getMarketId());
            l_eqtypeOrderUnitRow.setProductId(1006169090018L);
            l_eqtypeOrderUnitRow.setOrderType(OrderTypeEnum.EQUITY_SELL);
            TestDBUtility.insertWithDel(l_eqtypeOrderUnitRow);
            
            TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams.setInstitutionCode("0D");
            l_tradingTimeParams.setBranchCode("123");
            l_tradingTimeParams.setBizDateType("4");
            l_tradingTimeParams.setMarketCode("N1");
            l_tradingTimeParams.setProductCode("0");
            l_tradingTimeParams.setTradingTimeType("01");
            TestDBUtility.insertWithDel(l_tradingTimeParams);
            
            //InstitutionParams
            TestDBUtility.deleteAll(InstitutionRow.TYPE);
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            TestDBUtility.insertWithDel(l_institutionParams);

            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
            TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
            WEB3EquityOrderManager l_equityOrderManager =
                (WEB3EquityOrderManager)l_tradingModule.getOrderManager();
            EqTypeOrderUnit l_eqtypeOrderUnit =
                (EqTypeOrderUnit)l_equityOrderManager.getOrderUnit(l_eqtypeOrderUnitRow.getOrderUnitId());
            
            WEB3GentradeTrader l_trader = null;
            Date l_dat = WEB3DateUtility.getDate("20071227", "yyyyMMdd");
            
            WEB3GentradeTradingTimeManagementForMock.mockGetOrderBizDate(l_dat);
            EqTypeExecutionConditionType l_eqTypeExecutionconditionType = null;
            long l_lngOrderId = 1001;
            WEB3EquityChangeOrderUnitEntry l_eqTypeChangeOrderUnitEntry =
                new WEB3EquityChangeOrderUnitEntry(
                    l_eqTypeExecutionconditionType,
                    l_eqtypeOrderUnit,
                    false,
                    "1", "1", "1",
                    200, 300,
                    l_dat, l_eqTypeExecutionconditionType, "1");;

            String l_strInstitutionCode = "0D";
            String l_strOrderChannel = "1";
            WEB3EquityChangeOrderSpec l_changeOrderSpec =
                new WEB3EquityChangeOrderSpec(
                        l_lngOrderId, l_eqTypeChangeOrderUnitEntry, 
                        l_strInstitutionCode, l_strOrderChannel,l_trader);
            
            WEB3EquityNewCashBasedOrderSpec l_specAfter =  l_changeOrderSpec.createPTSOrderSpec();
            
            assertTrue(l_specAfter.isSellOrder());
            assertNull(l_specAfter.getFirstOrderUnitId());
            assertEquals("10", l_specAfter.getCommissionProductCode());
            assertEquals("33381", l_specAfter.getCommission().getBranchId() + "");
            assertEquals("0D", l_specAfter.getCommission().getInstitutionCode());
            
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    /**
     * －this.株式注文内容がnullの場合
     * 株式注文内容オブジェクト.createPTS株式注文内容( )にて株式注文内容インスタンスを生成する。
     * ”現物買注文”の場合はis売注文 = false
     * 注文単位.初回注文の注文単位ID != null;
     */
    public void testCreatePTSOrderSpecCase0003()
    {
        final String STR_METHOD_NAME = "testCreatePTSOrderSpecCase0003()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            //BranchParams
            TestDBUtility.deleteAll(BranchRow.TYPE);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            TestDBUtility.insertWithDel(l_branchParams);
            
            TestDBUtility.deleteAll(MarketRow.TYPE);
            MarketParams l_marketRow = TestDBUtility.getMarketRow();
            l_marketRow.setMarketCode("1");
            TestDBUtility.insertWithDel(l_marketRow);
            
            //ProductParams
            TestDBUtility.deleteAll(ProductRow.TYPE);
            ProductParams l_productParams = TestDBUtility.getProductRow();
            l_productParams.setProductId(1006169090018L);
            l_productParams.setProductType(ProductTypeEnum.EQUITY);
            TestDBUtility.insertWithDel(l_productParams);
            
            TestDBUtility.deleteAll(EqtypeProductRow.TYPE);
            EqtypeProductParams l_EqtypeProductParams = TestDBUtility.getEqtypeProductRow();
            l_EqtypeProductParams.setProductId(1006169090018L);
            l_EqtypeProductParams.setProductCode("0");
            TestDBUtility.insertWithDel(l_EqtypeProductParams);
            
            TestDBUtility.deleteAll(EqtypeOrderUnitRow.TYPE);
            EqtypeOrderUnitParams l_eqtypeOrderUnitRow = TestDBUtility.getEqtypeOrderUnitRow();
            l_eqtypeOrderUnitRow.setBranchId(l_branchParams.getBranchId());
            l_eqtypeOrderUnitRow.setMarketId(l_marketRow.getMarketId());
            l_eqtypeOrderUnitRow.setProductId(1006169090018L);
            l_eqtypeOrderUnitRow.setOrderType(OrderTypeEnum.EQUITY_BUY);
            l_eqtypeOrderUnitRow.setFirstOrderUnitId(1002);
            TestDBUtility.insertWithDel(l_eqtypeOrderUnitRow);
            
            TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams.setInstitutionCode("0D");
            l_tradingTimeParams.setBranchCode("123");
            l_tradingTimeParams.setBizDateType("4");
            l_tradingTimeParams.setMarketCode("N1");
            l_tradingTimeParams.setProductCode("0");
            l_tradingTimeParams.setTradingTimeType("01");
            TestDBUtility.insertWithDel(l_tradingTimeParams);
            
            //InstitutionParams
            TestDBUtility.deleteAll(InstitutionRow.TYPE);
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            TestDBUtility.insertWithDel(l_institutionParams);

            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
            TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
            WEB3EquityOrderManager l_equityOrderManager =
                (WEB3EquityOrderManager)l_tradingModule.getOrderManager();
            EqTypeOrderUnit l_eqtypeOrderUnit =
                (EqTypeOrderUnit)l_equityOrderManager.getOrderUnit(l_eqtypeOrderUnitRow.getOrderUnitId());
            
            WEB3GentradeTrader l_trader = null;
            Date l_dat = WEB3DateUtility.getDate("20071227", "yyyyMMdd");
            
            WEB3GentradeTradingTimeManagementForMock.mockGetOrderBizDate(l_dat);
            EqTypeExecutionConditionType l_eqTypeExecutionconditionType = null;
            long l_lngOrderId = 1001;
            WEB3EquityChangeOrderUnitEntry l_eqTypeChangeOrderUnitEntry =
                new WEB3EquityChangeOrderUnitEntry(
                    l_eqTypeExecutionconditionType,
                    l_eqtypeOrderUnit,
                    false,
                    "1", "1", "1",
                    200, 300,
                    l_dat, l_eqTypeExecutionconditionType, "1");;

            String l_strInstitutionCode = "0D";
            String l_strOrderChannel = "1";
            WEB3EquityChangeOrderSpec l_changeOrderSpec =
                new WEB3EquityChangeOrderSpec(
                        l_lngOrderId, l_eqTypeChangeOrderUnitEntry, 
                        l_strInstitutionCode, l_strOrderChannel,l_trader);
            
            WEB3EquityNewCashBasedOrderSpec l_specAfter =  l_changeOrderSpec.createPTSOrderSpec();
            
            assertTrue(l_specAfter.isBuyOrder());
            assertEquals("1002", l_specAfter.getFirstOrderUnitId() + "");
            assertEquals("10", l_specAfter.getCommissionProductCode());
            assertEquals("33381", l_specAfter.getCommission().getBranchId() + "");
            assertEquals("0D", l_specAfter.getCommission().getInstitutionCode());
            
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
}
@
