head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.00.59.35;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3IfoSettleContractOrderSpecTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 返済注文内容
Author Name      : Daiwa Institute of Research
Revesion History : 2007/06/11　@孟亜南(中訊)
*/
package webbroker3.ifo;

import java.util.Calendar;
import java.util.Date;

import com.fitechlabs.xtrade.plugin.tc.gentrade.Trader;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.SettleContractEntry;
import com.fitechlabs.xtrade.plugin.tc.xbifo.IfoOrderExecutionConditionType;

import test.util.TestDBUtility;

import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.gentrade.data.TradingTimeParams;
import webbroker3.gentrade.data.TradingTimeRow;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * 返済注文内容
 * @@author 孟亜南
 *
 */
public class WEB3IfoSettleContractOrderSpecTest extends TestBaseForMock
{
    /**
     * ログ出力ユーティリティ。
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3IfoSettleContractOrderSpecTest.class);

    public WEB3IfoSettleContractOrderSpecTest(String name)
    {
        super(name);
    }
    
    protected void setUp() throws Exception 
    {
        TradingTimeParams l_tradingTimeParams = new TradingTimeParams();

        l_tradingTimeParams.setInstitutionCode("0D");
        l_tradingTimeParams.setBranchCode("381");
        l_tradingTimeParams.setMarketCode("SP");
        l_tradingTimeParams.setTradingTimeType("26");
        l_tradingTimeParams.setProductCode("0");
        l_tradingTimeParams.setBizDateType("0");
        l_tradingTimeParams.setStartTime("000000");
        l_tradingTimeParams.setEndTime("235959");
        l_tradingTimeParams.setSubmitMarketTrigger("0");
        l_tradingTimeParams.setEnableOrder("0");
        l_tradingTimeParams.setBizdateCalcParameter("1");
        l_tradingTimeParams.setCreatedTimestamp(Calendar.getInstance().getTime());
        l_tradingTimeParams.setLastUpdatedTimestamp(Calendar.getInstance().getTime());

        TestDBUtility.deleteAll(TradingTimeRow.TYPE);
        TestDBUtility.insertWithDel(l_tradingTimeParams);
    }

    protected void tearDown() throws Exception
    {
        super.tearDown();
    }

    /**
     * 先物OP返済注文内容オブジェクトを生成
     * 注文失効日 = null;
     *
     */
    public void test_createSettleContractOrderSpec_0001()
    {
        final String STR_METHOD_NAME = "test_createSettleContractOrderSpec_0001()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        String l_strInstitutionCode = "00";
        Trader l_trader = null;
        double l_dblLimitPrice = 0;
        IfoOrderExecutionConditionType l_executeCond = null;
        Date l_datOrderExpirationDate = null;
        SettleContractEntry[] l_settleContractOrderEntry = null;
        String l_strOrderCond = "01";
        double l_dblStopOrderPrice = 1;
        double l_dblWLimitPrice = 0;
        IfoOrderExecutionConditionType l_wLimitExecCondType = null;
        String l_strExpirationDateType = "02";
        Long l_lngFirstOrderUnitId = new Long(1001);
        boolean l_blnEveningSessionCarryoverFlag = true;
        
        WEB3IfoSettleContractOrderSpec l_spec = null;
        try
        {
            l_spec = WEB3IfoSettleContractOrderSpec.createSettleContractOrderSpec(
                    l_strInstitutionCode,
                    l_trader,
                    l_dblLimitPrice,
                    l_executeCond,
                    l_datOrderExpirationDate,
                    l_settleContractOrderEntry,
                    l_strOrderCond,
                    l_dblStopOrderPrice,
                    l_dblWLimitPrice,
                    l_wLimitExecCondType,
                    l_strExpirationDateType,
                    l_lngFirstOrderUnitId,
                    l_blnEveningSessionCarryoverFlag);
            
            assertEquals("00", l_spec.getInstitutionCode());
            assertEquals("01", l_spec.getOrderCond());
            assertEquals("0", WEB3StringTypeUtility.formatNumber(l_spec.getWLimitPriceChange()));
            assertEquals(WEB3GentradeTradingTimeManagement.getOrderBizDate(), l_spec.getOrderBizDate());
            assertEquals("02", l_spec.getExpirationDateType());
            assertEquals(new Long(1001), l_spec.getFirstOrderUnitId());
            assertEquals("1", WEB3StringTypeUtility.formatNumber(l_spec.getStopOrderPrice()));
            assertNull(l_spec.getWLimitExecCondType());
            assertTrue(l_spec.getEveningSessionCarryoverFlag());

        }
        catch (Exception l_ex)
        {
            log.error(ERROR + l_ex.getMessage(), l_ex);
            fail();
        }
        log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
    }
    
    /**
     * 先物OP返済注文内容オブジェクトを生成
     * 注文失効日 != null;
     */
    public void test_createSettleContractOrderSpec_0002()
    {
        final String STR_METHOD_NAME = "test_createSettleContractOrderSpec_0002()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        String l_strInstitutionCode = "00";
        Trader l_trader = null;
        double l_dblLimitPrice = 0;
        IfoOrderExecutionConditionType l_executeCond = null;
        Date l_datOrderExpirationDate = WEB3DateUtility.getDate("20070831", "yyyyMMdd");
        SettleContractEntry[] l_settleContractOrderEntry = null;
        String l_strOrderCond = "01";
        double l_dblStopOrderPrice = 1;
        double l_dblWLimitPrice = 0;
        IfoOrderExecutionConditionType l_wLimitExecCondType = null;
        String l_strExpirationDateType = "02";
        Long l_lngFirstOrderUnitId = new Long(1001);
        boolean l_blnEveningSessionCarryoverFlag = true;
        
        WEB3IfoSettleContractOrderSpec l_spec = null;
        try
        {
            l_spec = WEB3IfoSettleContractOrderSpec.createSettleContractOrderSpec(
                    l_strInstitutionCode,
                    l_trader,
                    l_dblLimitPrice,
                    l_executeCond,
                    l_datOrderExpirationDate,
                    l_settleContractOrderEntry,
                    l_strOrderCond,
                    l_dblStopOrderPrice,
                    l_dblWLimitPrice,
                    l_wLimitExecCondType,
                    l_strExpirationDateType,
                    l_lngFirstOrderUnitId,
                    l_blnEveningSessionCarryoverFlag);
            
            assertEquals("00", l_spec.getInstitutionCode());
            assertEquals("01", l_spec.getOrderCond());
            assertEquals("0", WEB3StringTypeUtility.formatNumber(l_spec.getWLimitPriceChange()));
            assertEquals(WEB3GentradeTradingTimeManagement.getOrderBizDate(), l_spec.getOrderBizDate());
            assertEquals("02", l_spec.getExpirationDateType());
            assertEquals(new Long(1001), l_spec.getFirstOrderUnitId());
            assertEquals("1", WEB3StringTypeUtility.formatNumber(l_spec.getStopOrderPrice()));
            assertNull(l_spec.getWLimitExecCondType());
            assertTrue(l_spec.getEveningSessionCarryoverFlag());

        }
        catch (Exception l_ex)
        {
            log.error(ERROR + l_ex.getMessage(), l_ex);
            fail();
        }
        log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
    }
}
@
