head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.01.13.16;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3EquityOrderManagerChangeOrderEventInterceptorTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 株式注文訂正インタセプタTest(WEB3EquityOrderManagerChangeOrderEventInterceptorTest.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/12/27 趙林鵬(中訊) 新規作成
*/

package webbroker3.equity;

import java.sql.Timestamp;
import java.util.Date;

import com.fitechlabs.xtrade.kernel.util.ThreadLocalSystemAttributesRegistry;
import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeExecutionConditionType;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderUnitParams;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderUnitRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderManagerPersistenceContext;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderManagerPersistenceType;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TaxTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MarketParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MarketPreferencesParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MarketPreferencesRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MarketRow;

import test.util.TestDBUtility;

import webbroker3.gentrade.WEB3GentradeTrader;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagementForMock;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

public class WEB3EquityOrderManagerChangeOrderEventInterceptorTest extends TestBaseForMock
{
    private static WEB3LogUtility log = 
        WEB3LogUtility.getInstance(WEB3EquityOrderManagerChangeOrderEventInterceptorTest.class);

    public WEB3EquityOrderManagerChangeOrderEventInterceptorTest(String arg0)
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
     * 更新値設定
     * PTS市場注文の場合：
     * 市場から確認済みの注文Rev. = 株式発注サービス.getPTS訂正時注文Rev()の戻り値
     */
    public void testMutateCase0001()
    {
        final String STR_METHOD_NAME = "testMutateCase0001()";
        log.entering(STR_METHOD_NAME);
        
        MOCK_MANAGER.setIsMockUsed(true);
        try
        {
            ThreadLocalSystemAttributesRegistry.setAttribute(WEB3GentradeTradingTimeManagement.TIMESTAMP_TAG,
                    new Timestamp(WEB3DateUtility.getDate("20071227150000", "yyyyMMddHHmmss").getTime()));
            
            String l_strOrderRootdiv = null;
            WEB3GentradeTrader l_trader = null;
            
            WEB3GentradeTradingTimeManagementForMock.mockGetOrderBizDate(
                WEB3DateUtility.getDate("20071227", "yyyyMMdd"));
            
            WEB3EquityOrderManagerChangeOrderEventInterceptor l_interceptor =
                new WEB3EquityOrderManagerChangeOrderEventInterceptor(l_strOrderRootdiv, l_trader);

            TestDBUtility.deleteAll(EqtypeOrderUnitRow.TYPE);
            EqtypeOrderUnitParams l_eqtypeOrderUnitParams = TestDBUtility.getEqtypeOrderUnitRow();
            l_eqtypeOrderUnitParams.setSonarTradedCode("53");
            l_eqtypeOrderUnitParams.setOrderRev("9");
            TestDBUtility.insertWithDel(l_eqtypeOrderUnitParams);
      
            TestDBUtility.deleteAll(MarketRow.TYPE);
            MarketParams l_marketRow = TestDBUtility.getMarketRow();
            l_marketRow.setMarketId(l_eqtypeOrderUnitParams.getMarketId());
            l_marketRow.setMarketCode("1");
            TestDBUtility.insertWithDel(l_marketRow);
            
            TestDBUtility.deleteAll(MarketPreferencesRow.TYPE);
            MarketPreferencesParams l_MarketPreferencesRow = TestDBUtility.getMarketPreferencesRow();
            l_MarketPreferencesRow.setName("equity.pts.market.div");
            l_MarketPreferencesRow.setValue("1");
            TestDBUtility.insertWithDel(l_MarketPreferencesRow);
            
            OrderManagerPersistenceType l_orderManagerPersistenceType = null;
            OrderManagerPersistenceContext l_orderManagerPersistenceContext= null;
            
            Date l_dat = WEB3DateUtility.getDate("20071227", "yyyyMMdd");
            EqTypeExecutionConditionType l_eqTypeExecutionconditionType = null;
            WEB3EquityNewCashBasedOrderSpec l_spec =
                WEB3EquityNewCashBasedOrderSpec.createOrderSpec(
                    "0D", l_trader,"1", "1", 100D,100D, l_eqTypeExecutionconditionType,
                    TaxTypeEnum.NORMAL, new Timestamp(l_dat.getTime()),
                    false, "1", "1", "2", "4", 100D, 200D, new Long(1001));

            l_interceptor.setEquityOrderSpec(l_spec);

            EqtypeOrderUnitParams l_Params = l_interceptor.mutate(
                    l_orderManagerPersistenceType,l_orderManagerPersistenceContext,l_eqtypeOrderUnitParams);
            
            assertEquals("9", l_Params.getOrderRev());

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
