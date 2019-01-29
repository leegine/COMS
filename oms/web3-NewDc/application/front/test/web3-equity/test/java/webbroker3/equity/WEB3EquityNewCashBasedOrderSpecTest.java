head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.01.11.19;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3EquityNewCashBasedOrderSpecTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 株式注文内容Test(WEB3EquityNewCashBasedOrderSpecTest.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/12/28 趙林鵬(中訊) 新規作成
*/

package webbroker3.equity;

import java.sql.Timestamp;
import java.util.Date;

import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeExecutionConditionType;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TaxTypeEnum;

import test.util.TestDBUtility;

import webbroker3.gentrade.WEB3GentradeTrader;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagementForMock;
import webbroker3.gentrade.data.TradingTimeParams;
import webbroker3.gentrade.data.TradingTimeRow;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

public class WEB3EquityNewCashBasedOrderSpecTest extends TestBaseForMock
{
    /**
     * ログ出力ユーティリティ。<BR>
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(WEB3EquityNewCashBasedOrderSpecTest.class);

    public WEB3EquityNewCashBasedOrderSpecTest(String arg0)
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
     * createPTS株式注文内容
     * 株式注文内容.create注文内容( )の戻り値オブジェクト.set発注日( )をcallする
     * 引数設定は発注日 ： PTS取引時間管理.get発注日()の戻り値
     */
    public void testCreatePTSEquityOrderSpecCase0001()
    {
        String STR_METHOD_NAME = "testCreatePTSEquityOrderSpecCase0001()";
        log.entering(TEST_START + STR_METHOD_NAME);

        Date l_dat = WEB3DateUtility.getDate("20071227", "yyyyMMdd");
        EqTypeExecutionConditionType l_eqTypeExecutionconditionType = null;
        WEB3GentradeTrader l_trader = null;

        MOCK_MANAGER.setIsMockUsed(true);
        try
        {
            TestDBUtility.deleteAll(TradingTimeRow.TYPE);
            TradingTimeParams l_tradingTimeParams1 = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams1.setInstitutionCode("0D");
            l_tradingTimeParams1.setBranchCode("123");
            l_tradingTimeParams1.setBizDateType("4");
            l_tradingTimeParams1.setMarketCode("N1");
            l_tradingTimeParams1.setProductCode("0");
            l_tradingTimeParams1.setTradingTimeType("01");
            TestDBUtility.insertWithDel(l_tradingTimeParams1);
            
            WEB3GentradeTradingTimeManagementForMock.mockGetOrderBizDate(l_dat);
            WEB3EquityNewCashBasedOrderSpec l_spec = 
                WEB3EquityNewCashBasedOrderSpec.createPTSEquityOrderSpec(
                    "0D", l_trader,"1", "1", 100D,100D, l_eqTypeExecutionconditionType,
                    TaxTypeEnum.NORMAL, new Timestamp(l_dat.getTime()),
                    false, "1", "1", "2", "4", 100D, 200D, new Long(1001));

            Date l_datOrderBizDate = WEB3EquityPTSTradingTimeManagement.getOrderBizDate();
            Date l_datOrderBizDate1 = l_spec.getOrderBizDate();
            
            assertEquals(WEB3DateUtility.formatDate(l_datOrderBizDate, "yyyyMMdd"),
                WEB3DateUtility.formatDate(l_datOrderBizDate1, "yyyyMMdd"));
            assertEquals("0D", l_spec.getInstitutionCode());
            assertEquals("1", l_spec.getOrderChannel());
            assertEquals("1", l_spec.getPriceConditionType());
            assertEquals("2", l_spec.getOrderCond());
            assertEquals("4", l_spec.getOrderCondOperator());
            assertEquals("100", WEB3StringTypeUtility.formatNumber(l_spec.getStopLimitPriceBasePrice()));
            assertEquals("200", WEB3StringTypeUtility.formatNumber(l_spec.getWLimitPriceChange()));
            assertEquals("1001", l_spec.getFirstOrderUnitId() + "");
            
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
