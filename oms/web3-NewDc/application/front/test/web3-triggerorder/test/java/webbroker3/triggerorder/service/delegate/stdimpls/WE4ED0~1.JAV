head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.01.21.04;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3ToWLimitIfoSwitchUnitServiceImplTest_submitFuturesSettleContractWLimitOrder.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
 Copyright        : (株)大和総研 証券ソリューションシステム第二部
 File Name        : (WEB3ToWLimitIfoSwitchUnitServiceImplTest_submitFuturesSettleContractWLimitOrder.java)
 Author Name      : Daiwa Institute of Research
 Revision History : 2007/07/06 崔遠鵬(中訊) 新規作成
 */
package webbroker3.triggerorder.service.delegate.stdimpls;

import java.sql.Timestamp;
import java.util.Calendar;

import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.kernel.util.ThreadLocalSystemAttributesRegistry;
import com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Institution;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderCategEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProcessingResult;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SideEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccountTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MarketParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MarketPreferencesParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MarketPreferencesRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MarketRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.ProductParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.ProductRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.SubAccountParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.SubAccountRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.OrderSubmissionResult;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.OrderValidationResult;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.SettleContractEntry;
import com.fitechlabs.xtrade.plugin.tc.xbifo.IfoOrderManagerPersistenceEventInterceptor;
import com.fitechlabs.xtrade.plugin.tc.xbifo.IfoOrderUnit;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoClosingContractSpecParams;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoClosingContractSpecRow;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoContractParams;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoContractRow;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoOrderUnitParams;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoOrderUnitRow;
import com.fitechlabs.xtrade.plugin.tc.xbifo.ordersubmitter.io.IfoChangeSettleContractOrderSpec;
import com.fitechlabs.xtrade.plugin.tc.xbifo.stdimpls.IfoContractSettleOrderUnitImpl;

import test.util.TestDBUtility;

import webbroker3.gentrade.WEB3GentradeCommission;
import webbroker3.gentrade.WEB3GentradeMarket;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.gentrade.WEB3GentradeTradingClendarContext;
import webbroker3.gentrade.data.TradingTimeParams;
import webbroker3.gentrade.data.TradingTimeRow;
import webbroker3.ifo.WEB3IfoChangeSettleContractOrderSpec;
import webbroker3.ifo.WEB3IfoTradedProductImpl;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3LogUtility;

public class WEB3ToWLimitIfoSwitchUnitServiceImplTest_submitFuturesSettleContractWLimitOrder extends TestBaseForMock
{
    /**
     * ログユーティリティ<BR>
     */
    private static WEB3LogUtility log =
         WEB3LogUtility.getInstance(
                 WEB3ToWLimitIfoSwitchUnitServiceImplTest_submitFuturesSettleContractWLimitOrder.class);

    WEB3ToWLimitIfoSwitchUnitServiceImpl toWLimitIfoSwitchUnitServiceImpl = new WEB3ToWLimitIfoSwitchUnitServiceImplForTest();

    public WEB3ToWLimitIfoSwitchUnitServiceImplTest_submitFuturesSettleContractWLimitOrder(String arg0)
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

    public void testSubmitFuturesSettleContractWLimitOrder_C0001()
    {
        final String STR_METHOD_NAME = ".testSubmitFuturesSettleContractWLimitOrder_C0001()";
        log.entering(TEST_START + STR_METHOD_NAME);

        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "webbroker3.ifo.WEB3FuturesOrderManagerImpl",
            "isEveningSessionOrder", 
            new Class[] {IfoOrderUnit.class},
            Boolean.TRUE);
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "webbroker3.ifo.WEB3FuturesOrderManagerImpl",
            "validateFuturesChangeSettleContractOrder",
            new Class[]{ WEB3GentradeSubAccount.class, WEB3IfoChangeSettleContractOrderSpec.class, boolean.class },
            new OrderValidationResult(ProcessingResult.SUCCESS_RESULT));
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "webbroker3.ifo.WEB3IfoBizLogicProvider",
            "createCommission", 
            new Class[] {long.class},
            new WEB3GentradeCommission());
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "webbroker3.ifo.WEB3FuturesOrderManagerImpl",
            "submitChangeSettleContractOrder", new Class[]
            { SubAccount.class, IfoChangeSettleContractOrderSpec.class, String.class, boolean.class },
            new OrderSubmissionResult(ProcessingResult.SUCCESS_RESULT));

        try
        {
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.ifo.WEB3IfoProductManagerImpl",
                "getIfoTradedProduct",
                new Class[]{Institution.class, String.class, String.class},
                null);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.ifo.WEB3FuturesOrderManagerImpl",
                "calcChangeEstimateSettlementIncome",
                new Class[]{ WEB3GentradeCommission.class, double.class, WEB3GentradeSubAccount.class,
                    WEB3IfoTradedProductImpl.class, SettleContractEntry[].class, double.class, SideEnum.class,
                    double.class, long.class, boolean.class },
                null);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.ifo.WEB3FuturesOrderManagerImpl",
                "setThreadLocalPersistenceEventInterceptor", new Class[]
                { IfoOrderManagerPersistenceEventInterceptor.class },
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
            l_ifoOrderUnitParams.setMarketId(null);
            l_ifoOrderUnitParams.setEveningSessionCarryoverFlag(BooleanEnum.TRUE);
            l_ifoOrderUnitParams.setFirstOrderUnitId(0L);
            l_ifoOrderUnitParams.setOrderCateg(OrderCategEnum.IDX_OPTIONS_CLOSE);
            TestDBUtility.insertWithDel(l_ifoOrderUnitParams);

            SubAccountParams l_subAccountParams = new SubAccountParams();
            l_processor.doDeleteAllQuery(SubAccountRow.TYPE);
            l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setAccountId(101001010010L);
            l_subAccountParams.setSubAccountId(10100101001007L);
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_OPTIONS_SUB_ACCOUNT);
            TestDBUtility.insertWithDel(l_subAccountParams);

            TradingTimeParams l_tradingTimeParams =  new TradingTimeParams();
            l_processor.doDeleteAllQuery(TradingTimeRow.TYPE);
            l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams.setBizDateType("1");
            l_tradingTimeParams.setSessionType("1");
            TestDBUtility.insertWithDel(l_tradingTimeParams);

            IfoClosingContractSpecParams l_ifoClosingContractSpecParams = new IfoClosingContractSpecParams();
            l_processor.doDeleteAllQuery(IfoClosingContractSpecRow.TYPE);
            l_ifoClosingContractSpecParams = TestDBUtility.getIfoClosingContractSpecRow();
            l_ifoClosingContractSpecParams.setQuantity(101.0D);
            TestDBUtility.insertWithDel(l_ifoClosingContractSpecParams);

            IfoContractParams l_ifoContractParams = new IfoContractParams();
            l_processor.doDeleteAllQuery(IfoContractRow.TYPE);
            l_ifoContractParams = TestDBUtility.getIfoContractRow();
            l_ifoContractParams.setQuantity(201.0);
            TestDBUtility.insertWithDel(l_ifoContractParams);

            MarketParams l_maketParams = new MarketParams();
            l_processor.doDeleteAllQuery(MarketRow.TYPE);
            l_maketParams = TestDBUtility.getMarketRow();
            TestDBUtility.insertWithDel(l_maketParams);

            MarketPreferencesParams l_maketPreferencesParams = new MarketPreferencesParams();
            l_processor.doDeleteAllQuery(MarketPreferencesRow.TYPE);
            l_maketPreferencesParams = TestDBUtility.getMarketPreferencesRow();
            TestDBUtility.insertWithDel(l_maketPreferencesParams);

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.gentrade.WEB3GentradeFinObjectManager",
                "getMarket",
                new Class[]{long.class}, 
                new WEB3GentradeMarket(3303L));

            ProductParams l_productParams = new ProductParams();
            l_processor.doDeleteAllQuery(ProductRow.TYPE);
            l_productParams = TestDBUtility.getProductRow();
            l_productParams.setProductId(1006169090018L);
            TestDBUtility.insertWithDel(l_productParams);

            MainAccountParams l_mainAccountParams = new MainAccountParams();
            l_processor.doDeleteAllQuery(MainAccountRow.TYPE);
            l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(101001010010L);
            l_mainAccountParams.setTradingPassword("1");
            TestDBUtility.insertWithDel(l_mainAccountParams);

            // 実際メソッドをコール
            IfoOrderUnit l_orderUnit = new IfoContractSettleOrderUnitImpl(1001L);
            toWLimitIfoSwitchUnitServiceImpl.submitFuturesSettleContractWLimitOrder(l_orderUnit);
    
            // 予想結果と実際結果の比較
            assertTrue(true);
        }
        catch (Exception l_ex)
        {
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    private class WEB3ToWLimitIfoSwitchUnitServiceImplForTest extends WEB3ToWLimitIfoSwitchUnitServiceImpl
    {
        protected boolean isProcessObject(IfoOrderUnit l_orderUnit)
        {
            return true;
        }

        protected void validateSwitchPossible(IfoOrderUnit l_orderUnit)
        {
            return;
        }

        protected boolean isLimitPriceAfterSwitch(IfoOrderUnit l_orderUnit)
        {
            return true;
        }
    }
}
@
