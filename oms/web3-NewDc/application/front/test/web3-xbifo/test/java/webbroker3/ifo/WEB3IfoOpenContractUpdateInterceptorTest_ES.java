head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.00.59.39;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3IfoOpenContractUpdateInterceptorTest_ES.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 先物OP新規建更新インタセプタ
Author Name      : Daiwa Institute of Research
Revesion History : 2007/06/11 孟亜南 (中訊) 
*/
package webbroker3.ifo;

import java.util.Calendar;
import java.util.Date;

import com.fitechlabs.xtrade.kernel.data.DataFindException;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderManagerPersistenceContext;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderManagerPersistenceType;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Trader;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.InstitutionParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.InstitutionRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MarketParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MarketRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.ProductParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.ProductRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.TradedProductParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.TradedProductRow;
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
 * 先物OP新規建更新インタセプタ
 * @@author 孟亜南
 *
 */
public class WEB3IfoOpenContractUpdateInterceptorTest_ES extends TestBaseForMock
{

    public WEB3IfoOpenContractUpdateInterceptorTest_ES(String name)
    {
        super(name);
    }
    
    protected void setUp() throws Exception 
    {
        //TradingTimeRow
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
        
        //IfoProductRow
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
        TestDBUtility.deleteAll(IfoProductRow.TYPE);
        TestDBUtility.insertWithDel(l_ifoProductParams);
        
        //TradedProductRow
        TradedProductParams l_tradedProductParams = new TradedProductParams();
        l_tradedProductParams.setTradedProductId(1006160060005L);
        l_tradedProductParams.setInstitutionCode("0D");
        l_tradedProductParams.setProductId(3304148080000L);
        l_tradedProductParams.setMarketId(3303L);
        l_tradedProductParams.setMarginRatio(70.000000D);
        l_tradedProductParams.setSuspensionFlag(BooleanEnum.FALSE);
        l_tradedProductParams.setBaseDate(WEB3DateUtility.getDate("20040917","yyyyMMdd"));
        l_tradedProductParams.setDailyDeliveryDate(WEB3DateUtility.getDate("20040924","yyyyMMdd"));
        l_tradedProductParams.setProductType(ProductTypeEnum.FOREIGN_EQUITY);
        l_tradedProductParams.setCollateralFlag(BooleanEnum.FALSE);
        l_tradedProductParams.setCreatedTimestamp(Calendar.getInstance().getTime());
        l_tradedProductParams.setLastUpdatedTimestamp(Calendar.getInstance().getTime());
        TestDBUtility.deleteAll(TradedProductRow.TYPE);
        TestDBUtility.insertWithDel(l_tradedProductParams);
        
        TradedProductParams l_tradedProductParams1 = new TradedProductParams();
        l_tradedProductParams1.setTradedProductId(1006160060005L);
        l_tradedProductParams1.setInstitutionCode("0D");
        l_tradedProductParams1.setProductId(1006169090018L);
        l_tradedProductParams1.setMarketId(3303L);
        l_tradedProductParams1.setMarginRatio(70.000000D);
        l_tradedProductParams1.setSuspensionFlag(BooleanEnum.FALSE);
        l_tradedProductParams1.setBaseDate(WEB3DateUtility.getDate("20040917","yyyyMMdd"));
        l_tradedProductParams1.setDailyDeliveryDate(WEB3DateUtility.getDate("20040924","yyyyMMdd"));
        l_tradedProductParams1.setProductType(ProductTypeEnum.FOREIGN_EQUITY);
        l_tradedProductParams1.setCollateralFlag(BooleanEnum.FALSE);
        l_tradedProductParams1.setCreatedTimestamp(Calendar.getInstance().getTime());
        l_tradedProductParams1.setLastUpdatedTimestamp(Calendar.getInstance().getTime());
        TestDBUtility.deleteAll(TradedProductRow.TYPE);
        TestDBUtility.insertWithDel(l_tradedProductParams1);
        
        //ProductRow
        ProductParams l_productParams = new ProductParams();
        l_productParams.setProductId(3304148080000L);
        l_productParams.setInstitutionCode("0D");
        l_productParams.setProductType(ProductTypeEnum.FOREIGN_EQUITY);
        l_productParams.setStandardName("シンセンテルス");
        l_productParams.setLotSize(0.000000D);
        l_productParams.setCalcSize(1.000000D);
        l_productParams.setEstimationPrice(0.000000D);
        l_productParams.setMarginRatio(0.000000D);
        l_productParams.setSecuritiesEstimationRatio(0.000000D);
        l_productParams.setCreatedTimestamp(Calendar.getInstance().getTime());
        l_productParams.setLastUpdatedTimestamp(Calendar.getInstance().getTime());
        TestDBUtility.deleteAll(ProductRow.TYPE);
        TestDBUtility.insertWithDel(l_productParams);

        ProductParams l_productParams1 = new ProductParams();
        l_productParams1.setProductId(1006169090018L);
        l_productParams1.setInstitutionCode("0D");
        l_productParams1.setProductType(ProductTypeEnum.FOREIGN_EQUITY);
        l_productParams1.setStandardName("シンセンテルス");
        l_productParams1.setLotSize(0.000000D);
        l_productParams1.setCalcSize(1.000000D);
        l_productParams1.setEstimationPrice(0.000000D);
        l_productParams1.setMarginRatio(0.000000D);
        l_productParams1.setSecuritiesEstimationRatio(0.000000D);
        l_productParams1.setCreatedTimestamp(Calendar.getInstance().getTime());
        l_productParams1.setLastUpdatedTimestamp(Calendar.getInstance().getTime());
        TestDBUtility.deleteAll(ProductRow.TYPE);
        TestDBUtility.insertWithDel(l_productParams1);
        
        TestDBUtility.deleteAll(InstitutionRow.TYPE);
        InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
        TestDBUtility.insertWithDel(l_institutionParams);
        
    }
    
    protected void tearDown() throws Exception
    {
        super.tearDown();
    }

    /**
     * ログユーティリティ<BR>
     */
    private static WEB3LogUtility log =
         WEB3LogUtility.getInstance(
             WEB3IfoMarketRequestSenderServiceImplTest.class);
    
    /**
     * 夕場前繰越対象フラグ = true
     *
     */
    public void test_mutate_0001()
    {
        final String STR_METHOD_NAME = "test_send_0001()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);
        
        String l_strInstitutionCode = "00";
        Trader l_trader = null;
        boolean l_blnIsBuyToOpenOrder = false;
        String l_strMarket = "00";
        
        double l_dblQuantity = 0;
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
        
        WEB3IfoOpenContractOrderSpec l_openContractOrderSpec = null;
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
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl", 
                    "getSessionProperty",
                    new Class[] {String.class},
                    "aa");
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.gentrade.WEB3HostReqOrderNumberManageServiceImpl",
                    "getNewNumber", 
                    new Class[]{ String.class, String.class, ProductTypeEnum.class},
                    "1234");
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.ifo.service.delegate.stdimpls.WEB3IfoFrontOrderServiceImpl",
                    "getSubmitOrderRouteDiv",
                    new Class[] {String.class, String.class},
                    "cc");
            
            l_openContractOrderSpec = WEB3IfoOpenContractOrderSpec.createOpenContractOrderSpec(
                    l_strInstitutionCode,
                    l_trader,
                    l_blnIsBuyToOpenOrder,
                    l_strMarket,
                    l_product,
                    l_dblQuantity,
                    l_dblLimitPrice,
                    l_executionConditionType,
                    l_datExpirationDate,
                    l_strOrderCond,
                    l_dblStopOrderBasePrice,
                    l_dblWLimitPriceChange,
                    l_wLimitExecCondType,
                    l_strExpirationDateType,
                    l_lngFirstOrderUnitId,
                    l_blnEveningSessionCarryoverFlag);
            //夕場前繰越対象フラグ
            l_openContractOrderSpec.setEveningSessionCarryoverFlag(true);
        }
        catch (WEB3BaseException e)
        {
            log.error("", e);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        
        WEB3GentradeCommission l_commision = new WEB3GentradeCommission();
        WEB3IfoEstimateDeliveryAmountCalcResult l_estimateDeliveryAmounCalcResult =
            new WEB3IfoEstimateDeliveryAmountCalcResult();
        l_estimateDeliveryAmounCalcResult.setCalcUnitPrice(11);
        
        WEB3IfoOpenContractUpdateInterceptor l_interceptor =
            new WEB3IfoOpenContractUpdateInterceptor(l_openContractOrderSpec);
        
        l_interceptor.setSessionType("123");
        
        l_interceptor.setCommision(l_commision);
        l_interceptor.setEstimateDeliveryAmountCalcResult(l_estimateDeliveryAmounCalcResult);
        
        OrderManagerPersistenceType l_orderManagerPersistenceType = null;
        OrderManagerPersistenceContext l_orderManagerPersistenceContext = null;
        IfoOrderUnitParams l_ifoOrderUnitParams = new IfoOrderUnitParams();
        l_ifoOrderUnitParams.product_id = 1006169090018L;
        l_ifoOrderUnitParams.account_id = 101001010010L;
        l_ifoOrderUnitParams.setMarketId(1002);
        
        l_interceptor.mutate(
                l_orderManagerPersistenceType,
                l_orderManagerPersistenceContext,
                l_ifoOrderUnitParams);
        
        assertEquals("123",l_ifoOrderUnitParams.getSessionType());
        assertNull(l_ifoOrderUnitParams.getDayTradeType());
        assertEquals("1","" + l_ifoOrderUnitParams.getEveningSessionCarryoverFlag().intValue());
        assertEquals("123",l_interceptor.getSessionType());
    }
    
    /**
     * 夕場前繰越対象フラグ = false
     *
     */
    public void test_mutate_0002()
    {
        final String STR_METHOD_NAME = "test_send_0002()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);
        
        String l_strInstitutionCode = "00";
        Trader l_trader = null;
        boolean l_blnIsBuyToOpenOrder = false;
        String l_strMarket = "00";
        
        double l_dblQuantity = 0;
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
        
        WEB3IfoOpenContractOrderSpec l_openContractOrderSpec = null;
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
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl", 
                    "getSessionProperty",
                    new Class[] {String.class},
                    "aa");
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.gentrade.WEB3HostReqOrderNumberManageServiceImpl",
                    "getNewNumber", 
                    new Class[]{ String.class, String.class, ProductTypeEnum.class},
                    "1234");
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.ifo.service.delegate.stdimpls.WEB3IfoFrontOrderServiceImpl",
                    "getSubmitOrderRouteDiv",
                    new Class[] {String.class, String.class},
                    "cc");
            
            l_openContractOrderSpec = WEB3IfoOpenContractOrderSpec.createOpenContractOrderSpec(
                    l_strInstitutionCode,
                    l_trader,
                    l_blnIsBuyToOpenOrder,
                    l_strMarket,
                    l_product,
                    l_dblQuantity,
                    l_dblLimitPrice,
                    l_executionConditionType,
                    l_datExpirationDate,
                    l_strOrderCond,
                    l_dblStopOrderBasePrice,
                    l_dblWLimitPriceChange,
                    l_wLimitExecCondType,
                    l_strExpirationDateType,
                    l_lngFirstOrderUnitId,
                    l_blnEveningSessionCarryoverFlag);
            //夕場前繰越対象フラグ
            l_openContractOrderSpec.setEveningSessionCarryoverFlag(false);
        }
        catch (WEB3BaseException e)
        {
            log.error("", e);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        
        WEB3GentradeCommission l_commision = new WEB3GentradeCommission();
        WEB3IfoEstimateDeliveryAmountCalcResult l_estimateDeliveryAmounCalcResult =
            new WEB3IfoEstimateDeliveryAmountCalcResult();
        l_estimateDeliveryAmounCalcResult.setCalcUnitPrice(11);
        
        WEB3IfoOpenContractUpdateInterceptor l_interceptor =
            new WEB3IfoOpenContractUpdateInterceptor(l_openContractOrderSpec);
        
        l_interceptor.setSessionType("123");
        
        l_interceptor.setCommision(l_commision);
        l_interceptor.setEstimateDeliveryAmountCalcResult(l_estimateDeliveryAmounCalcResult);
        
        OrderManagerPersistenceType l_orderManagerPersistenceType = null;
        OrderManagerPersistenceContext l_orderManagerPersistenceContext = null;
        IfoOrderUnitParams l_ifoOrderUnitParams = new IfoOrderUnitParams();
        l_ifoOrderUnitParams.product_id = 1006169090018L;
        l_ifoOrderUnitParams.account_id = 101001010010L;
        l_ifoOrderUnitParams.setMarketId(1002);
        
        l_interceptor.mutate(
                l_orderManagerPersistenceType,
                l_orderManagerPersistenceContext,
                l_ifoOrderUnitParams);
        
        assertEquals("123",l_ifoOrderUnitParams.getSessionType());
        assertNull(l_ifoOrderUnitParams.getDayTradeType());
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
