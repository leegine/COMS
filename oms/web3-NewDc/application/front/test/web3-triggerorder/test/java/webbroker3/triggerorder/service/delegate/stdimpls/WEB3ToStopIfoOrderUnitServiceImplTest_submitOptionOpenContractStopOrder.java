head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.01.18.37;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3ToStopIfoOrderUnitServiceImplTest_submitOptionOpenContractStopOrder.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
 Copyright        : (株)大和総研 証券ソリューションシステム第二部
 File Name        : (WEB3ToStopIfoOrderUnitServiceImplTest_submitOptionOpenContractStopOrder.java)
 Author Name      : Daiwa Institute of Research
 Revision History : 2007/07/04 崔遠鵬(中訊) 新規作成
 */
package webbroker3.triggerorder.service.delegate.stdimpls;

import java.sql.Timestamp;
import java.util.Calendar;

import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.kernel.util.ThreadLocalSystemAttributesRegistry;
import com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Institution;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProcessingResult;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SideEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.BranchParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.BranchRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.InstitutionParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.InstitutionRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MarketParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MarketPreferencesParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MarketPreferencesRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MarketRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.ProductParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.ProductRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.SubAccountParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.SubAccountRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.TradedProductParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.TradedProductRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.NewOrderValidationResult;
import com.fitechlabs.xtrade.plugin.tc.xbifo.IfoOrderUnit;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoOrderUnitParams;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoOrderUnitRow;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoProductParams;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoProductRow;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoTradedProductParams;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoTradedProductRow;
import com.fitechlabs.xtrade.plugin.tc.xbifo.stdimpls.IfoContractOpenOrderUnitImpl;

import test.util.TestDBUtility;

import webbroker3.gentrade.WEB3GentradeCommission;
import webbroker3.gentrade.WEB3GentradeMarket;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.gentrade.WEB3GentradeTradingClendarContext;
import webbroker3.gentrade.data.CalendarRow;
import webbroker3.gentrade.data.TradingTimeParams;
import webbroker3.gentrade.data.TradingTimeRow;
import webbroker3.ifo.WEB3IfoEstimateDeliveryAmountCalcResult;
import webbroker3.ifo.WEB3IfoOpenContractChangeSpec;
import webbroker3.ifo.WEB3IfoOpenContractOrderSpec;
import webbroker3.ifo.WEB3IfoTradedProductImpl;
import webbroker3.mock.TestBaseForMock;
import webbroker3.tradingpower.WEB3TPTradingPowerResult;
import webbroker3.util.WEB3LogUtility;

public class WEB3ToStopIfoOrderUnitServiceImplTest_submitOptionOpenContractStopOrder extends TestBaseForMock
{
    /**
     * ログユーティリティ<BR>
     */
    private static WEB3LogUtility log =
         WEB3LogUtility.getInstance(
             WEB3ToStopIfoOrderUnitServiceImplTest_submitOptionOpenContractStopOrder.class);

    WEB3ToStopIfoOrderUnitServiceImpl toStopIfoOrderUnitServiceImpl = new WEB3ToStopIfoOrderUnitServiceImplForTest();

    public WEB3ToStopIfoOrderUnitServiceImplTest_submitOptionOpenContractStopOrder(String arg0)
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

    public void testSubmitOptionOpenContractStopOrder_C0001()
    {
        final String STR_METHOD_NAME = ".testSubmitOptionOpenContractStopOrder_C0001()";
        log.entering(TEST_START + STR_METHOD_NAME);

        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "webbroker3.ifo.WEB3FuturesOrderManagerImpl",
            "isEveningSessionOrder", 
            new Class[] {IfoOrderUnit.class},
            Boolean.TRUE);
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "webbroker3.ifo.WEB3FuturesOrderManagerImpl",
            "validateOpenContractOrder",
            new Class[] {WEB3GentradeSubAccount.class, WEB3IfoOpenContractOrderSpec.class, IfoOrderUnit.class},
            new NewOrderValidationResult(ProcessingResult.SUCCESS_RESULT));
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "webbroker3.ifo.WEB3IfoBizLogicProvider",
            "createCommission", 
            new Class[] {long.class},
            new WEB3GentradeCommission());

        try
        {
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.ifo.WEB3IfoProductManagerImpl",
                "getIfoTradedProduct",
                new Class[]{Institution.class, String.class, String.class},
                null);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.ifo.WEB3FuturesOrderManagerImpl",
                "calcEstimateDeliveryAmount",
                new Class[]{ WEB3GentradeCommission.class, double.class, SubAccount.class, WEB3IfoTradedProductImpl.class,
                    double.class, SideEnum.class, boolean.class, boolean.class },
                new WEB3IfoEstimateDeliveryAmountCalcResult());
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.tradingpower.WEB3TPTradingPowerServiceImpl",
                "validateTradingPower",
                new Class[] {WEB3GentradeSubAccount.class, Object[].class, Object[].class, OrderTypeEnum.class, boolean.class},
                null);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.ifo.WEB3FuturesOrderManagerImpl",
                "throwTpErrorInfo",
                new Class[]{WEB3TPTradingPowerResult.class, WEB3GentradeSubAccount.class},
                null);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.ifo.WEB3OptionOrderManagerImpl",
                "insertOpenContractHostOrder",
                new Class[]{long.class},
                null);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.ifo.service.delegate.stdimpls.WEB3IfoFrontOrderServiceImpl",
                "getSubmitOrderRouteDiv",
                new Class[] {String.class, String.class},
                null);

            //スタティックメソッドのセット
            WEB3GentradeTradingClendarContext l_context = new WEB3GentradeTradingClendarContext();
            l_context.setInstitutionCode("0D");
            l_context.setBranchCode("381");
            l_context.setMarketCode("SP");
            l_context.setTradingTimeType("26");
            l_context.setBizDateType("0");
            l_context.setProductCode("0");
            ThreadLocalSystemAttributesRegistry.setAttribute("web3.tradingcalendarcontext", l_context);
            Calendar l_calendar =  Calendar.getInstance();
            l_calendar.set(2007, 7-1, 4);
            Timestamp l_tsOrderAcceptDate = new Timestamp(l_calendar.getTimeInMillis());
            ThreadLocalSystemAttributesRegistry.setAttribute("xblocks.gtl.attributes.systemtimestamp", l_tsOrderAcceptDate);
            ThreadLocalSystemAttributesRegistry.setAttribute("web3.attributes.basetimestampfororderbizdate", null);

            // テーブルへデータをインサート
            IfoOrderUnitParams l_ifoOrderUnitParams = new IfoOrderUnitParams();
            QueryProcessor l_processor = Processors.getDefaultProcessor();
            l_processor.doDeleteAllQuery(IfoOrderUnitRow.TYPE);
            l_ifoOrderUnitParams = TestDBUtility.getIfoOrderUnitRow();
            l_ifoOrderUnitParams.setBizDate("20070705");
            l_ifoOrderUnitParams.setSessionType("1");
            l_ifoOrderUnitParams.setMarketId(3303l);
            l_ifoOrderUnitParams.setEveningSessionCarryoverFlag(BooleanEnum.TRUE);
            l_ifoOrderUnitParams.setFirstOrderUnitId(0L);
            TestDBUtility.insertWithDel(l_ifoOrderUnitParams);

            SubAccountParams l_subAccountParams = new SubAccountParams();
            l_processor.doDeleteAllQuery(SubAccountRow.TYPE);
            l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setAccountId(101001010010L);
            l_subAccountParams.setSubAccountId(10100101001007L);
            TestDBUtility.insertWithDel(l_subAccountParams);

            TradingTimeParams l_tradingTimeParams =  new TradingTimeParams();
            l_processor.doDeleteAllQuery(TradingTimeRow.TYPE);
            l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams.setBizDateType("1");
            l_tradingTimeParams.setSessionType("1");
            TestDBUtility.insertWithDel(l_tradingTimeParams);

            ProductParams l_productParams = new ProductParams();
            l_processor.doDeleteAllQuery(ProductRow.TYPE);
            l_productParams = TestDBUtility.getProductRow();
            l_productParams.setProductId(1006169090018L);
            TestDBUtility.insertWithDel(l_productParams);

            l_processor.doDeleteAllQuery(CalendarRow.TYPE);

            IfoProductParams l_ifoProductParams = new IfoProductParams();
            l_processor.doDeleteAllQuery(IfoProductRow.TYPE);
            l_ifoProductParams = TestDBUtility.getIfoProductRow();
            l_ifoProductParams.setProductId(1006169090018L);
            l_ifoProductParams.setProductCode("160030005");
            l_ifoProductParams.setInstitutionCode("0D");
            TestDBUtility.insertWithDel(l_ifoProductParams);

            InstitutionParams l_institutionParams = new InstitutionParams();
            l_processor.doDeleteAllQuery(InstitutionRow.TYPE);
            l_institutionParams = TestDBUtility.getInstitutionRow();
            TestDBUtility.insertWithDel(l_institutionParams);

            TradedProductParams l_tradedProductParams =  new TradedProductParams();
            l_processor.doDeleteAllQuery(TradedProductRow.TYPE);
            l_tradedProductParams = TestDBUtility.getTradedProductRow();
            l_tradedProductParams.setProductId(1006169090018L);
            TestDBUtility.insertWithDel(l_tradedProductParams);

            IfoTradedProductParams l_ifoTradedProductParams = new IfoTradedProductParams();
            l_processor.doDeleteAllQuery(IfoTradedProductRow.TYPE);
            l_ifoTradedProductParams = TestDBUtility.getIfoTradedProductRow();
            l_ifoTradedProductParams.setProductId(1006169090018L);
            l_ifoTradedProductParams.setMarketId(3303L);
            TestDBUtility.insertWithDel(l_ifoTradedProductParams);

            BranchParams l_branchParams = new BranchParams();
            l_processor.doDeleteAllQuery(BranchRow.TYPE);
            l_branchParams = TestDBUtility.getBranchRow();
            TestDBUtility.insertWithDel(l_branchParams);

            MarketParams l_marketParams = new MarketParams();
            l_processor.doDeleteAllQuery(MarketRow.TYPE);
            l_marketParams = TestDBUtility.getMarketRow();
            TestDBUtility.insertWithDel(l_marketParams);

            MarketPreferencesParams l_marketPreferencesParams = new MarketPreferencesParams();
            l_processor.doDeleteAllQuery(MarketPreferencesRow.TYPE);
            l_marketPreferencesParams = TestDBUtility.getMarketPreferencesRow();
            TestDBUtility.insertWithDel(l_marketPreferencesParams);

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.gentrade.WEB3GentradeFinObjectManager",
                "getMarket",
                new Class[]{long.class}, 
                new WEB3GentradeMarket(3303L));

            // 実際メソッドをコール
            IfoOrderUnit l_orderUnit = new IfoContractOpenOrderUnitImpl(1001L);
            toStopIfoOrderUnitServiceImpl.submitOptionOpenContractStopOrder(l_orderUnit);

            // 予想結果と実際結果の比較
            assertTrue(true);
        }
        catch (Exception l_ex)
        {
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    private class WEB3ToStopIfoOrderUnitServiceImplForTest extends WEB3ToStopIfoOrderUnitServiceImpl
    {
        protected boolean isProcessObject (IfoOrderUnit l_orderUnit)
        {
            return true;
        }

        protected WEB3IfoOpenContractChangeSpec createOpenContractChangeSpec (IfoOrderUnit l_orderUnit)
        {
            return null;
        }

        protected void updateOrderData(
            IfoOrderUnit l_orderUnit, 
            double l_dblOrderPrice, 
            double l_dblEstimatedPrice,
            String l_strOrderRootDiv)
        {
            return;
        }

        protected void sendMQTrigger(IfoOrderUnit l_orderUnit)
        {
            return;
        }
    }
}
@
