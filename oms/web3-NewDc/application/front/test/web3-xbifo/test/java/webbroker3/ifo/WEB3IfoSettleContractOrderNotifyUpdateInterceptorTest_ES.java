head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.01.00.06;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3IfoSettleContractOrderNotifyUpdateInterceptorTest_ES.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 先物OP返済注文通知更新インタセプタ
Author Name      : Daiwa Institute of Research
Revesion History : 2007/06/14 孟亜南 (中訊)
*/
package webbroker3.ifo;

import java.util.Calendar;
import java.util.Date;

import com.fitechlabs.xtrade.kernel.data.DataFindException;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderManagerPersistenceContext;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderManagerPersistenceType;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Trader;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MarketParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MarketRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.ProductParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.ProductRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.SettleContractEntry;
import com.fitechlabs.xtrade.plugin.tc.xbifo.IfoDerivativeTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.xbifo.IfoOrderExecutionConditionType;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoOrderUnitParams;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoProductParams;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoProductRow;

import test.util.TestDBUtility;

import webbroker3.common.WEB3BaseException;
import webbroker3.gentrade.WEB3GentradeCommission;
import webbroker3.gentrade.data.TradingTimeParams;
import webbroker3.gentrade.data.TradingTimeRow;
import webbroker3.ifo.marketadaptor.WEB3IfoMarketRequestSenderServiceImplTest;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

/**
 * 先物OP返済注文通知更新インタセプタ
 * @@author 孟亜南
 *
 */
public class WEB3IfoSettleContractOrderNotifyUpdateInterceptorTest_ES extends TestBaseForMock
{

    public WEB3IfoSettleContractOrderNotifyUpdateInterceptorTest_ES(String name)
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

    /**
     * ログユーティリティ<BR>
     */
    private static WEB3LogUtility log =
         WEB3LogUtility.getInstance(
                 WEB3IfoSettleContractOrderNotifyUpdateInterceptorTest_ES.class);
    
    /**
     * 夕場前繰越対象フラグ = true
     *
     */
    public void test_mutate_0001()
    {

        final String STR_METHOD_NAME = "test_mutate_0001()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        String l_strInstitutionCode = "00";
        Trader l_trader = null;
        
        double l_dblLimitPrice = 0;
        IfoOrderExecutionConditionType l_executionConditionType = null;
        Date l_datExpirationDate = new Date();
        String l_strOrderCond = "";
        double l_dblStopOrderBasePrice = 0;
        double l_dblWLimitPriceChange = 0;
        IfoOrderExecutionConditionType l_wLimitExecCondType = null;
        String l_strExpirationDateType = "00";
        Long l_lngFirstOrderUnitId = new Long(0);
        boolean l_blnEveningSessionCarryoverFlag = false;
        
        
        SettleContractEntry[] l_settleContractOrderEntry = null;
        
        WEB3IfoSettleContractOrderSpec l_ifoSettleContractOrderSpec = null;
        WEB3IfoProductImpl l_product = null;
        
        try
        {            
            try
            {
                l_product = new WEB3IfoProductImpl(1006169090018L);
            }
            catch (DataFindException e)
            {
                log.error("", e);
                log.exiting(TEST_END + STR_METHOD_NAME);
                fail();
            }
            catch (DataQueryException e)
            {
                log.error("", e);
                log.exiting(TEST_END + STR_METHOD_NAME);
                fail();
            }
            catch (DataNetworkException e)
            {
                log.error("", e);
                log.exiting(TEST_END + STR_METHOD_NAME);
                fail();
            }
            
            ProductParams l_productParams = this.getProductRow();
            IfoProductParams l_ifoProductParams = this.getIfoProductRow();
            MarketParams l_marketParams = this.getMarketRow();
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(101001010010L);
            
            TestDBUtility.deleteAll(ProductRow.TYPE);
            TestDBUtility.deleteAll(IfoProductRow.TYPE);
            TestDBUtility.deleteAll(MarketRow.TYPE);
            
            TestDBUtility.insertWithDel(l_marketParams);
            TestDBUtility.insertWithDel(l_productParams);
            TestDBUtility.insertWithDel(l_ifoProductParams);
            TestDBUtility.insertWithDel(l_mainAccountParams);
        }
        catch (WEB3BaseException l_ex)
        {
            log.error("", l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        
        try
        {
            l_ifoSettleContractOrderSpec = WEB3IfoSettleContractOrderSpec.createSettleContractOrderSpec(
                    l_strInstitutionCode,
                    l_trader,
                    l_dblLimitPrice,
                    l_executionConditionType,
                    l_datExpirationDate,
                    l_settleContractOrderEntry,
                    l_strOrderCond,
                    l_dblStopOrderBasePrice,
                    l_dblWLimitPriceChange,
                    l_wLimitExecCondType,
                    l_strExpirationDateType,
                    l_lngFirstOrderUnitId,
                    l_blnEveningSessionCarryoverFlag);
            //夕場前繰越対象フラグ
            l_ifoSettleContractOrderSpec.setEveningSessionCarryoverFlag(true);
        }
        catch (WEB3BaseException e)
        {
            log.error("", e);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        
        OrderManagerPersistenceType l_orderManagerPersistenceType = null;
        OrderManagerPersistenceContext l_orderManagerPersistenceContext = null;
        IfoOrderUnitParams l_ifoOrderUnitParams = new IfoOrderUnitParams();
        l_ifoOrderUnitParams.product_id = 1006169090018L;
        l_ifoOrderUnitParams.account_id = 101001010010L;
        l_ifoOrderUnitParams.setMarketId(1002);
        
        WEB3GentradeCommission l_commision = new WEB3GentradeCommission();
        l_commision.setDayTradeType("012");
        WEB3IfoEstimateDeliveryAmountCalcResult l_estimateDeliveryAmounCalcResult =
            new WEB3IfoEstimateDeliveryAmountCalcResult();
        l_estimateDeliveryAmounCalcResult.setCalcUnitPrice(11);
        
        WEB3IfoSettleContractOrderNotifyUpdateInterceptor l_interceptor =
            new WEB3IfoSettleContractOrderNotifyUpdateInterceptor(l_ifoSettleContractOrderSpec);
        l_interceptor.setSessionType("123");
        
        l_interceptor.setCommision(l_commision);
        l_interceptor.setOrderRequestNumber("1243");
        l_interceptor.setEstimateDeliveryAmountCalcResult(l_estimateDeliveryAmounCalcResult);
        
        l_interceptor.setDeliveryDate(WEB3DateUtility.getDate("20070101","yyyyMMdd"));
        
        l_ifoOrderUnitParams = l_interceptor.mutate(
                l_orderManagerPersistenceType,
                l_orderManagerPersistenceContext,
                l_ifoOrderUnitParams);
        
        assertEquals("123",l_ifoOrderUnitParams.getSessionType());
        assertEquals("012",l_ifoOrderUnitParams.getDayTradeType());
        assertEquals("1","" + l_ifoOrderUnitParams.getEveningSessionCarryoverFlag().intValue());
        assertEquals("123",l_interceptor.getSessionType());
        assertEquals("0","" + WEB3DateUtility.compareToDay(WEB3DateUtility.getDate("20070101","yyyyMMdd"),l_ifoOrderUnitParams.getDeliveryDate()));
        assertEquals("0","" + WEB3DateUtility.compareToDay(WEB3DateUtility.getDate("20070101","yyyyMMdd"),l_interceptor.getDeliveryDate()));
    }
    
    /**
     * 夕場前繰越対象フラグ = false
     *
     */
    public void test_mutate_0002()
    {

        final String STR_METHOD_NAME = "test_mutate_0002()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        String l_strInstitutionCode = "00";
        Trader l_trader = null;
        
        double l_dblLimitPrice = 0;
        IfoOrderExecutionConditionType l_executionConditionType = null;
        Date l_datExpirationDate = new Date();
        String l_strOrderCond = "";
        double l_dblStopOrderBasePrice = 0;
        double l_dblWLimitPriceChange = 0;
        IfoOrderExecutionConditionType l_wLimitExecCondType = null;
        String l_strExpirationDateType = "00";
        Long l_lngFirstOrderUnitId = new Long(0);
        boolean l_blnEveningSessionCarryoverFlag = false;
        
        
        SettleContractEntry[] l_settleContractOrderEntry = null;
        
        WEB3IfoSettleContractOrderSpec l_ifoSettleContractOrderSpec = null;
        WEB3IfoProductImpl l_product = null;
        
        try
        {            
            try
            {
                l_product = new WEB3IfoProductImpl(1006169090018L);
            }
            catch (DataFindException e)
            {
                log.error("", e);
                log.exiting(TEST_END + STR_METHOD_NAME);
                fail();
            }
            catch (DataQueryException e)
            {
                log.error("", e);
                log.exiting(TEST_END + STR_METHOD_NAME);
                fail();
            }
            catch (DataNetworkException e)
            {
                log.error("", e);
                log.exiting(TEST_END + STR_METHOD_NAME);
                fail();
            }
            
            ProductParams l_productParams = this.getProductRow();
            IfoProductParams l_ifoProductParams = this.getIfoProductRow();
            MarketParams l_marketParams = this.getMarketRow();
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(101001010010L);
            
            TestDBUtility.deleteAll(ProductRow.TYPE);
            TestDBUtility.deleteAll(IfoProductRow.TYPE);
            TestDBUtility.deleteAll(MarketRow.TYPE);
            
            TestDBUtility.insertWithDel(l_marketParams);
            TestDBUtility.insertWithDel(l_productParams);
            TestDBUtility.insertWithDel(l_ifoProductParams);
            TestDBUtility.insertWithDel(l_mainAccountParams);
        }
        catch (WEB3BaseException l_ex)
        {
            log.error("", l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        
        try
        {
            l_ifoSettleContractOrderSpec = WEB3IfoSettleContractOrderSpec.createSettleContractOrderSpec(
                    l_strInstitutionCode,
                    l_trader,
                    l_dblLimitPrice,
                    l_executionConditionType,
                    l_datExpirationDate,
                    l_settleContractOrderEntry,
                    l_strOrderCond,
                    l_dblStopOrderBasePrice,
                    l_dblWLimitPriceChange,
                    l_wLimitExecCondType,
                    l_strExpirationDateType,
                    l_lngFirstOrderUnitId,
                    l_blnEveningSessionCarryoverFlag);
            //夕場前繰越対象フラグ
            l_ifoSettleContractOrderSpec.setEveningSessionCarryoverFlag(false);
        }
        catch (WEB3BaseException e)
        {
            log.error("", e);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        
        OrderManagerPersistenceType l_orderManagerPersistenceType = null;
        OrderManagerPersistenceContext l_orderManagerPersistenceContext = null;
        IfoOrderUnitParams l_ifoOrderUnitParams = new IfoOrderUnitParams();
        l_ifoOrderUnitParams.product_id = 1006169090018L;
        l_ifoOrderUnitParams.account_id = 101001010010L;
        l_ifoOrderUnitParams.setMarketId(1002);
        
        WEB3GentradeCommission l_commision = new WEB3GentradeCommission();
        l_commision.setDayTradeType("012");
        WEB3IfoEstimateDeliveryAmountCalcResult l_estimateDeliveryAmounCalcResult =
            new WEB3IfoEstimateDeliveryAmountCalcResult();
        l_estimateDeliveryAmounCalcResult.setCalcUnitPrice(11);
        
        WEB3IfoSettleContractOrderNotifyUpdateInterceptor l_interceptor =
            new WEB3IfoSettleContractOrderNotifyUpdateInterceptor(l_ifoSettleContractOrderSpec);
        l_interceptor.setSessionType("123");
        
        l_interceptor.setCommision(l_commision);
        l_interceptor.setOrderRequestNumber("1243");
        l_interceptor.setEstimateDeliveryAmountCalcResult(l_estimateDeliveryAmounCalcResult);
        
        l_ifoOrderUnitParams = l_interceptor.mutate(
                l_orderManagerPersistenceType,
                l_orderManagerPersistenceContext,
                l_ifoOrderUnitParams);
        
        assertEquals("123",l_ifoOrderUnitParams.getSessionType());
        assertEquals("012",l_ifoOrderUnitParams.getDayTradeType());
        assertEquals("0","" + l_ifoOrderUnitParams.getEveningSessionCarryoverFlag().intValue());
        assertEquals("123",l_interceptor.getSessionType());
    }
    
    /**
     * 銘柄Rowを作成.<BR>
     */
    public static ProductParams getProductRow()
    {
        ProductParams l_productParams = new ProductParams();

        l_productParams.setProductId(1006169090018L);
        l_productParams.setInstitutionCode("0D");
        l_productParams.setProductType(ProductTypeEnum.IFO);
        l_productParams.setStandardName("シンセンテルス");
        l_productParams.setLotSize(0.000000D);
        l_productParams.setCalcSize(1.000000D);
        l_productParams.setEstimationPrice(0.000000D);
        l_productParams.setMarginRatio(0.000000D);
        l_productParams.setSecuritiesEstimationRatio(0.000000D);
        l_productParams.setCreatedTimestamp(Calendar.getInstance().getTime());
        l_productParams.setLastUpdatedTimestamp(Calendar.getInstance().getTime());

        return l_productParams;
    }
    
    /**
     * 先物OP銘柄テーブル (ifo_product)
     */
    public static IfoProductParams getIfoProductRow()
    {
        IfoProductParams l_ifoProductParams = new IfoProductParams();
        l_ifoProductParams.setProductId(1006169090018L);
        l_ifoProductParams.setInstitutionCode("10");
        l_ifoProductParams.setProductCode("160030005");
        l_ifoProductParams.setUnderlyingProductCode("0005");
        l_ifoProductParams.setDerivativeType(IfoDerivativeTypeEnum.FUTURES);
        l_ifoProductParams.setStrikePrice(0);
        l_ifoProductParams.setMonthOfDelivery("200503");
        l_ifoProductParams.setExerciseDate(WEB3DateUtility.getDate("20070112","yyyyMMdd"));
        l_ifoProductParams.setCreatedTimestamp(Calendar.getInstance().getTime());
        l_ifoProductParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070101","yyyyMMdd"));
        return l_ifoProductParams;
    }
    
    /**
     * 市場Rowを作成.<BR>
     */
    public static MarketParams getMarketRow()
    {
        MarketParams l_marketParams = new MarketParams();

        l_marketParams.setMarketId(1002);
        l_marketParams.setInstitutionCode("0D");
        l_marketParams.setMarketCode("SP");
        l_marketParams.setSonarMarketCode("G");
        l_marketParams.setMarketName("シンガポール");
        l_marketParams.setOpenTime("09:00");
        l_marketParams.setCloseTime("15:00");
        l_marketParams.setSuspension("0");
        l_marketParams.setChangeableType("1");
        l_marketParams.setFeqOrderEmpDiv("7");
        l_marketParams.setFeqOrderRequestDiv("1");
        l_marketParams.setCreatedTimestamp(Calendar.getInstance().getTime());
        l_marketParams.setLastUpdatedTimestamp(Calendar.getInstance().getTime());

        return l_marketParams;
    }
}
@
