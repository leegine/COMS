head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.01.10.30;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3EquityProductManagerTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 拡張プロダクトマネージャテスト(WEB3EquityProductManagerTest.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/02/13 趙林鵬 (中訊) 新規作成
*/

package webbroker3.equity;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.util.ThreadLocalSystemAttributesRegistry;
import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeTradedProduct;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeProductParams;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeProductRow;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeTradedProductParams;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeTradedProductRow;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeTradedProductUpdqParams;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeTradedProductUpdqRow;
import com.fitechlabs.xtrade.plugin.tc.eqtype.stdimpls.EqTypeTradedProductImpl;
import com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Market;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.BranchRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.InstitutionRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MarketParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MarketRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.ProductParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.ProductRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.SubAccountRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.TradedProductCalendarRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.TradedProductDao;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.TradedProductParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.TradedProductRow;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoContractRow;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoOrderRow;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoOrderUnitRow;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoProductRow;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoTradedProductRow;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoTradedProductUpdqRow;

import test.util.TestDBUtility;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.gentrade.WEB3GentradeFinObjectManager;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagementForMock;
import webbroker3.gentrade.data.LastClosingPriceParams;
import webbroker3.gentrade.data.LastClosingPriceRow;
import webbroker3.gentrade.data.ProcessManagementParams;
import webbroker3.gentrade.data.TradingTimeParams;
import webbroker3.mock.TestBaseForMock;
import webbroker3.quoteadaptor.RealType;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

/**
 * （拡張プロダクトマネージャテスト）<BR>
 *
 * @@author 趙林鵬
 * @@version 1.0
 */

public class WEB3EquityProductManagerTest extends TestBaseForMock
{
    
    /**
     * ログユーティリティ<BR>
     */
    private static WEB3LogUtility log =
         WEB3LogUtility.getInstance(WEB3EquityProductManagerTest.class);

    public WEB3EquityProductManagerTest(String arg0)
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
    
    //is翌日基準値受信
    public void testIsNextDayBasePriceMailCase1()
    {
        final String STR_METHOD_NAME = " testIsNextDayBasePriceMailCase1";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {
            FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
            TradingModule l_tradingModule =
                l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
            
            TestDBUtility.deleteAll(ProcessManagementParams.TYPE);
            ProcessManagementParams l_params = TestDBUtility.getProcessManagementParams();
            l_params.setProcessId("0011");
            l_params.setInstitutionCode("0D");
            TestDBUtility.insertWithDel(l_params);

            //拡張プロダクトマネージャを取得 
            WEB3EquityProductManager l_equityProductManager = (WEB3EquityProductManager)l_tradingModule.getProductManager();
            boolean l_bln = l_equityProductManager.isNextDayBasePriceMail("0D");
            assertTrue(l_bln);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
        log.exiting(TEST_END + STR_METHOD_NAME); 
    }
    public void testIsNextDayBasePriceMailCase2()
    {
        final String STR_METHOD_NAME = " testIsNextDayBasePriceMailCase2";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {
            FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
            TradingModule l_tradingModule =
                l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
            
            TestDBUtility.deleteAll(ProcessManagementParams.TYPE);
            ProcessManagementParams l_params = TestDBUtility.getProcessManagementParams();
            l_params.setProcessId("0011");
            l_params.setInstitutionCode("0D");
            l_params.setBranchCode("101");
            TestDBUtility.insertWithDel(l_params);
            ProcessManagementParams l_params1 = TestDBUtility.getProcessManagementParams();
            l_params1.setProcessId("0011");
            l_params1.setInstitutionCode("0D");
            l_params1.setBranchCode("102");
            TestDBUtility.insertWithDel(l_params1);

            //拡張プロダクトマネージャを取得 
            WEB3EquityProductManager l_equityProductManager = (WEB3EquityProductManager)l_tradingModule.getProductManager();
            boolean l_bln = l_equityProductManager.isNextDayBasePriceMail("0D");
            assertTrue(l_bln);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
        log.exiting(TEST_END + STR_METHOD_NAME); 
    }
    public void testIsNextDayBasePriceMailCase3()
    {
        final String STR_METHOD_NAME = " testIsNextDayBasePriceMailCase2";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {
            FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
            TradingModule l_tradingModule =
                l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
            
            TestDBUtility.deleteAll(ProcessManagementParams.TYPE);


            //拡張プロダクトマネージャを取得 
            WEB3EquityProductManager l_equityProductManager = (WEB3EquityProductManager)l_tradingModule.getProductManager();
            boolean l_bln = l_equityProductManager.isNextDayBasePriceMail("0D");
            assertFalse(l_bln);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
        log.exiting(TEST_END + STR_METHOD_NAME); 
    }
    
    //is大引け基準値受信完了
    public void testIsBasePriceRecCompletedCase1()
    {
        final String STR_METHOD_NAME = " testIsBasePriceRecCompletedCase1";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {
            FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
            TradingModule l_tradingModule =
                l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
            
            TestDBUtility.deleteAll(ProcessManagementParams.TYPE);
            ProcessManagementParams l_params = TestDBUtility.getProcessManagementParams();
            l_params.setProcessId("0012");
            l_params.setInstitutionCode("0D");
            TestDBUtility.insertWithDel(l_params);

            //拡張プロダクトマネージャを取得 
            WEB3EquityProductManager l_equityProductManager = (WEB3EquityProductManager)l_tradingModule.getProductManager();
            boolean l_bln = l_equityProductManager.isBasePriceRecCompleted("0D");
            assertTrue(l_bln);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
        log.exiting(TEST_END + STR_METHOD_NAME); 
    }
    public void testIsBasePriceRecCompletedCase2()
    {
        final String STR_METHOD_NAME = " testIsBasePriceRecCompletedCase2";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {
            FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
            TradingModule l_tradingModule =
                l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
            
            TestDBUtility.deleteAll(ProcessManagementParams.TYPE);
            ProcessManagementParams l_params = TestDBUtility.getProcessManagementParams();
            l_params.setProcessId("0012");
            l_params.setInstitutionCode("0D");
            l_params.setBranchCode("101");
            TestDBUtility.insertWithDel(l_params);
            ProcessManagementParams l_params1 = TestDBUtility.getProcessManagementParams();
            l_params1.setProcessId("0012");
            l_params1.setInstitutionCode("0D");
            l_params1.setBranchCode("102");
            TestDBUtility.insertWithDel(l_params1);

            //拡張プロダクトマネージャを取得 
            WEB3EquityProductManager l_equityProductManager = (WEB3EquityProductManager)l_tradingModule.getProductManager();
            boolean l_bln = l_equityProductManager.isBasePriceRecCompleted("0D");
            assertTrue(l_bln);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
        log.exiting(TEST_END + STR_METHOD_NAME); 
    }
    public void testIsBasePriceRecCompletedCase3()
    {
        final String STR_METHOD_NAME = " testIsBasePriceRecCompletedCase3";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {
            FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
            TradingModule l_tradingModule =
                l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
            
            TestDBUtility.deleteAll(ProcessManagementParams.TYPE);


            //拡張プロダクトマネージャを取得 
            WEB3EquityProductManager l_equityProductManager = (WEB3EquityProductManager)l_tradingModule.getProductManager();
            boolean l_bln = l_equityProductManager.isBasePriceRecCompleted("0D");
            assertFalse(l_bln);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
        log.exiting(TEST_END + STR_METHOD_NAME); 
    }
    
  public void test_getProductQuote_c0001()
  {
      final String STR_METHOD_NAME = " test_getProductQuote_c0001";
      log.entering(TEST_START + STR_METHOD_NAME);
      
      try
      {
          MOCK_MANAGER.setIsMockUsed(true);
          
          boolean l_blnExpectValue = false;
          WEB3GentradeTradingTimeManagementForMock.mockIsTradeOpenTimeZone(l_blnExpectValue);
          
          Date l_datExpect = WEB3DateUtility.getDate("20040710","yyyyMMdd");
          WEB3GentradeTradingTimeManagementForMock.mockGetOrderBizDate(l_datExpect);
          
          TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();

          l_tradingTimeParams.setInstitutionCode("0D");
          l_tradingTimeParams.setBranchCode("123");
          l_tradingTimeParams.setMarketCode("N1");
          l_tradingTimeParams.setTradingTimeType("01");
          l_tradingTimeParams.setProductCode("0");
          l_tradingTimeParams.setBizDateType("4");
          l_tradingTimeParams.setStartTime("000000");
          l_tradingTimeParams.setEndTime("235959");        
          l_tradingTimeParams.setSubmitMarketTrigger("0");
          l_tradingTimeParams.setEnableOrder("0");
          l_tradingTimeParams.setBizdateCalcParameter("0");
          l_tradingTimeParams.setCreatedTimestamp(Calendar.getInstance().getTime());
          l_tradingTimeParams.setLastUpdatedTimestamp(Calendar.getInstance().getTime());
          TestDBUtility.insertWithDel(l_tradingTimeParams);
          
          Calendar l_calendar = Calendar.getInstance();
          l_calendar.set(Calendar.YEAR,2004);
          l_calendar.set(Calendar.MONTH,06);
          l_calendar.set(Calendar.DAY_OF_MONTH,10);
          l_calendar.set(Calendar.HOUR_OF_DAY,15);
          l_calendar.set(Calendar.MINUTE,00);
          l_calendar.set(Calendar.SECOND,01);

          Timestamp l_tsBizDate = new Timestamp(l_calendar.getTimeInMillis());

          ThreadLocalSystemAttributesRegistry.setAttribute(
              WEB3GentradeTradingTimeManagement.TIMESTAMP_TAG, l_tsBizDate);
          
          ProductParams l_productParams = TestDBUtility.getProductRow();
          EqtypeTradedProductParams l_eqtypeTradedProductParams = TestDBUtility.getEqtypeTradedProductRow();
          EqtypeProductParams l_eqtypeProductParams = TestDBUtility.getEqtypeProductRow();
          TradedProductParams l_tradedProductParams = TestDBUtility.getTradedProductRow();
          MarketParams l_marketParams = TestDBUtility.getMarketRow();
          LastClosingPriceParams l_lastClosingPriceParams = TestDBUtility.getLastClosingPriceRow();
    
          l_productParams.setProductId(1006169090018L);
          l_productParams.setProductType(ProductTypeEnum.EQUITY);
          l_productParams.setPrimaryMarketId(3303L);
          
          l_eqtypeTradedProductParams.setProductId(1006169090018L);
          l_eqtypeTradedProductParams.setMarketId(3303L);
          
          l_eqtypeTradedProductParams.setValidUntilBizDate("20040709");                                                         
          l_eqtypeTradedProductParams.setTradedProductId(100106139070605L);
          l_eqtypeTradedProductParams.setLastClosingPrice(0);
          
          l_eqtypeProductParams.setProductId(1006169090018L);
          l_eqtypeProductParams.setProductType(ProductTypeEnum.EQUITY);
          
          l_tradedProductParams.setProductId(1006169090018L);
          l_tradedProductParams.setTradedProductId(100106139070605L);
          l_tradedProductParams.setProductType(ProductTypeEnum.EQUITY);
          l_tradedProductParams.setValidUntilBizDate("20040709");
          
          l_lastClosingPriceParams.setBaseDate(WEB3DateUtility.getDate("20040709","yyyyMMdd"));
          
          EqtypeTradedProductUpdqParams l_eqTradedProductUpdqParam = TestDBUtility.getEqtypeTradedProductUpdqRow();
          
          l_eqTradedProductUpdqParam.setProductId(1006169090018L);
          l_eqTradedProductUpdqParam.setTradedProductId(100106139070605L);
          l_eqTradedProductUpdqParam.setValidUntilBizDate("20040710");
          
          
          //l_eqTradedProductUpdqParam.setBasePrice();
          this.deleteAll();
          TestDBUtility.insertWithDel(l_eqTradedProductUpdqParam);
          TestDBUtility.insertWithDel(l_productParams);
          TestDBUtility.insertWithDel(l_eqtypeTradedProductParams);
          TestDBUtility.insertWithDel(l_eqtypeProductParams);
          TestDBUtility.insertWithDel(l_tradedProductParams);
          TestDBUtility.insertWithDel(l_marketParams);
          TestDBUtility.insertWithDel(l_lastClosingPriceParams);
          
          FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
          TradingModule l_tradingModule =
              l_finApp.getTradingModule(ProductTypeEnum.EQUITY);

          //拡張プロダクトマネージャを取得 
          WEB3EquityProductManager l_equityProductManager = (WEB3EquityProductManager)l_tradingModule.getProductManager();
          
          TradedProductRow l_tradedProductRow = TradedProductDao.findRowByPk(100106139070605L);
          
          EqTypeTradedProduct l_equityTradedProduct = new EqTypeTradedProductImpl(l_tradedProductRow);
          
          RealType l_realType = RealType.REAL;
          boolean l_blnIsLastClosingPriceUnconditionalyRead = true;
          
          WEB3EquityProductQuote l_equityProductQuote = l_equityProductManager.getProductQuote(
              l_equityTradedProduct, l_realType, l_blnIsLastClosingPriceUnconditionalyRead);
          
          assertTrue(Double.isNaN(l_equityProductQuote.getComparedPreviousDay()));
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
   * 20071120
   * モデル1214
   *
   */
  public void test_getProductQuoteCase1()
  {
      final String STR_METHOD_NAME = " test_getProductQuoteCase1";
      log.entering(TEST_START + STR_METHOD_NAME);
      
      try
      {
          MOCK_MANAGER.setIsMockUsed(true);
          
          boolean l_blnExpectValue = false;
          WEB3GentradeTradingTimeManagementForMock.mockIsTradeOpenTimeZone(l_blnExpectValue);
          
          Date l_datExpect = WEB3DateUtility.getDate("20040710","yyyyMMdd");
          WEB3GentradeTradingTimeManagementForMock.mockGetOrderBizDate(l_datExpect);
          TestBaseForMock.MOCK_MANAGER.setMethodReturnValue("webbroker3.gentrade.WEB3GentradeTradingCalendarModelImpl",
                  "getCurrentBizDate",
                  new Class[] {long.class},
                  WEB3DateUtility.getDate("20040710", "yyyyMMdd"));
          
          Calendar l_calendar = Calendar.getInstance();
          l_calendar.set(Calendar.YEAR,2004);
          l_calendar.set(Calendar.MONTH,06);
          l_calendar.set(Calendar.DAY_OF_MONTH,10);
          l_calendar.set(Calendar.HOUR_OF_DAY,15);
          l_calendar.set(Calendar.MINUTE,00);
          l_calendar.set(Calendar.SECOND,01);

          Timestamp l_tsBizDate = new Timestamp(l_calendar.getTimeInMillis());

          ThreadLocalSystemAttributesRegistry.setAttribute(
              WEB3GentradeTradingTimeManagement.TIMESTAMP_TAG, l_tsBizDate);
          
          ProductParams l_productParams = TestDBUtility.getProductRow();
          EqtypeTradedProductParams l_eqtypeTradedProductParams = TestDBUtility.getEqtypeTradedProductRow();
          EqtypeProductParams l_eqtypeProductParams = TestDBUtility.getEqtypeProductRow();
          TradedProductParams l_tradedProductParams = TestDBUtility.getTradedProductRow();
          MarketParams l_marketParams = TestDBUtility.getMarketRow();
          LastClosingPriceParams l_lastClosingPriceParams = TestDBUtility.getLastClosingPriceRow();
    
          l_productParams.setProductId(1006169090018L);
          l_productParams.setProductType(ProductTypeEnum.EQUITY);
          l_productParams.setPrimaryMarketId(3303L);
          
          l_eqtypeTradedProductParams.setProductId(1006169090018L);
          l_eqtypeTradedProductParams.setMarketId(3303L);
          l_eqtypeTradedProductParams.setValidUntilBizDate("20040709");
          l_eqtypeTradedProductParams.setTradedProductId(100106139070605L);
          l_eqtypeTradedProductParams.setLastClosingPrice(500);
          
          l_eqtypeProductParams.setProductId(1006169090018L);
          l_eqtypeProductParams.setProductType(ProductTypeEnum.EQUITY);
          
          l_tradedProductParams.setProductId(1006169090018L);
          l_tradedProductParams.setTradedProductId(100106139070605L);
          l_tradedProductParams.setProductType(ProductTypeEnum.EQUITY);
          l_tradedProductParams.setValidUntilBizDate("20040709");
          
          l_lastClosingPriceParams.setBaseDate(WEB3DateUtility.getDate("20040709","yyyyMMdd"));
          
          EqtypeTradedProductUpdqParams l_eqTradedProductUpdqParam = TestDBUtility.getEqtypeTradedProductUpdqRow();
          l_eqTradedProductUpdqParam.setProductId(1006169090018L);
          l_eqTradedProductUpdqParam.setTradedProductId(100106139070605L);
          l_eqTradedProductUpdqParam.setValidUntilBizDate("20040710");
          
          this.deleteAll();
          TestDBUtility.insertWithDel(l_productParams);          
          TestDBUtility.insertWithDel(l_eqtypeTradedProductParams);
          TestDBUtility.insertWithDel(l_eqTradedProductUpdqParam);
          TestDBUtility.insertWithDel(l_eqtypeProductParams);
          TestDBUtility.insertWithDel(l_tradedProductParams);
          TestDBUtility.insertWithDel(l_marketParams);
          TestDBUtility.insertWithDel(l_lastClosingPriceParams);
          
          FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
          TradingModule l_tradingModule =
              l_finApp.getTradingModule(ProductTypeEnum.EQUITY);

          //拡張プロダクトマネージャを取得 
          WEB3EquityProductManager l_equityProductManager = (WEB3EquityProductManager)l_tradingModule.getProductManager();
          
          TradedProductRow l_tradedProductRow = TradedProductDao.findRowByPk(100106139070605L);
          
          EqTypeTradedProduct l_equityTradedProduct = new EqTypeTradedProductImpl(l_tradedProductRow);
          
          RealType l_realType = RealType.REAL;
          boolean l_blnIsLastClosingPriceUnconditionalyRead = true;
          
          WEB3EquityProductQuote l_equityProductQuote = l_equityProductManager.getProductQuote(
              l_equityTradedProduct, l_realType, l_blnIsLastClosingPriceUnconditionalyRead);
          
          assertEquals(l_equityProductQuote.getComparedPreviousDay()+"","400.0");
      }
      catch (Exception l_ex)
      {
          log.error(l_ex.getMessage(), l_ex);
          fail();
      }
      log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
      log.exiting(TEST_END + STR_METHOD_NAME); 
  }

  public void test_getProductQuote_c0002()
  {
      final String STR_METHOD_NAME = " test_getProductQuote_c0002";
      log.entering(TEST_START + STR_METHOD_NAME);
      
      try
      {
          MOCK_MANAGER.setIsMockUsed(true);
          
          boolean l_blnExpectValue = true;
          WEB3GentradeTradingTimeManagementForMock.mockIsTradeOpenTimeZone(l_blnExpectValue);
          
          Date l_datExpect = WEB3DateUtility.getDate("20040710","yyyyMMdd");
          WEB3GentradeTradingTimeManagementForMock.mockGetOrderBizDate(l_datExpect);
          
          ProductParams l_productParams = TestDBUtility.getProductRow();
          EqtypeTradedProductParams l_eqtypeTradedProductParams = TestDBUtility.getEqtypeTradedProductRow();
          EqtypeProductParams l_eqtypeProductParams = TestDBUtility.getEqtypeProductRow();
          TradedProductParams l_tradedProductParams = TestDBUtility.getTradedProductRow();
          MarketParams l_marketParams = TestDBUtility.getMarketRow();
          LastClosingPriceParams l_lastClosingPriceParams = TestDBUtility.getLastClosingPriceRow();
    
          l_productParams.setProductId(1006169090018L);
          l_productParams.setProductType(ProductTypeEnum.EQUITY);
          l_productParams.setPrimaryMarketId(3303L);
          
          l_eqtypeTradedProductParams.setProductId(1006169090018L);
          l_eqtypeTradedProductParams.setTradedProductId(100106139070605L);
          
          l_eqtypeProductParams.setProductId(1006169090018L);
          l_eqtypeProductParams.setProductType(ProductTypeEnum.EQUITY);
          
          l_tradedProductParams.setProductId(1006169090018L);
          l_tradedProductParams.setTradedProductId(100106139070605L);
          l_tradedProductParams.setProductType(ProductTypeEnum.EQUITY);
          
          l_lastClosingPriceParams.setBaseDate(WEB3DateUtility.getDate("20040709","yyyyMMdd"));
          
          this.deleteAll();
          TestDBUtility.insertWithDel(l_productParams);
          TestDBUtility.insertWithDel(l_eqtypeTradedProductParams);
          TestDBUtility.insertWithDel(l_eqtypeProductParams);
          TestDBUtility.insertWithDel(l_tradedProductParams);
          TestDBUtility.insertWithDel(l_marketParams);
          TestDBUtility.insertWithDel(l_lastClosingPriceParams);
          TestDBUtility.commit();
          FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
          TradingModule l_tradingModule =
              l_finApp.getTradingModule(ProductTypeEnum.EQUITY);

          //拡張プロダクトマネージャを取得 
          WEB3EquityProductManager l_equityProductManager = (WEB3EquityProductManager)l_tradingModule.getProductManager();
          
          TradedProductRow l_tradedProductRow = TradedProductDao.findRowByPk(100106139070605L);
          
          EqTypeTradedProduct l_equityTradedProduct = new EqTypeTradedProductImpl(l_tradedProductRow);
          
          RealType l_realType = RealType.REAL;
          boolean l_blnIsLastClosingPriceUnconditionalyRead = false;
          
          WEB3EquityProductQuote l_equityProductQuote = l_equityProductManager.getProductQuote(
              l_equityTradedProduct, l_realType, l_blnIsLastClosingPriceUnconditionalyRead);
          
          assertTrue(Double.isNaN(l_equityProductQuote.getComparedPreviousDay()));
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
     * 20071113
     * モデル1203
     */
    //株式取引銘柄テーブルを以下の条件で検索する
    //取得したレコードを取引銘柄インスタンスとして返却する。
    public void testGetTradedProductCase1()
    {
        final String STR_METHOD_NAME = " testGetTradedProductCase1";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        try
        {
            Calendar l_calendar = Calendar.getInstance();
            l_calendar.set(Calendar.YEAR,2004);
            l_calendar.set(Calendar.MONTH,06);
            l_calendar.set(Calendar.DAY_OF_MONTH,8);
            l_calendar.set(Calendar.HOUR_OF_DAY,17);
            l_calendar.set(Calendar.MINUTE,00);
            l_calendar.set(Calendar.SECOND,01);

            Timestamp l_tsBizDate = new Timestamp(l_calendar.getTimeInMillis());

            ThreadLocalSystemAttributesRegistry.setAttribute(
                WEB3GentradeTradingTimeManagement.TIMESTAMP_TAG, l_tsBizDate);
            
            MarketParams l_marketParams = TestDBUtility.getMarketRow();
      
            ProductParams l_productParams = TestDBUtility.getProductRow();
            l_productParams.setProductId(1006169090018L);
            l_productParams.setProductType(ProductTypeEnum.EQUITY);
            l_productParams.setPrimaryMarketId(3303L);
            
            EqtypeTradedProductParams l_eqtypeTradedProductParams = TestDBUtility.getEqtypeTradedProductRow();
            l_eqtypeTradedProductParams.setProductId(1006169090018L);
            l_eqtypeTradedProductParams.setTradedProductId(100106139070605L);
            l_eqtypeTradedProductParams.setValidUntilBizDate("20040709");
            
            EqtypeTradedProductUpdqParams l_eqtypeTradedProductUpdqParams =
                insertEqtypeTradedProductUpdqRow();
            
            EqtypeProductParams l_eqtypeProductParams = TestDBUtility.getEqtypeProductRow();
            l_eqtypeProductParams.setProductId(1006169090018L);
            l_eqtypeProductParams.setProductType(ProductTypeEnum.EQUITY);
            
            TradedProductParams l_tradedProductParams = TestDBUtility.getTradedProductRow();
            l_tradedProductParams.setProductId(1006169090018L);
            l_tradedProductParams.setTradedProductId(100106139070605L);
            l_tradedProductParams.setProductType(ProductTypeEnum.EQUITY);
            l_tradedProductParams.setBaseDate(WEB3DateUtility.getDate("20040709","yyyyMMdd"));
            
            this.deleteAll();
            TestDBUtility.insertWithDel(l_productParams);
            TestDBUtility.insertWithDel(l_eqtypeTradedProductParams);
            TestDBUtility.insertWithDel(l_eqtypeTradedProductUpdqParams);
            TestDBUtility.insertWithDel(l_eqtypeProductParams);
            TestDBUtility.insertWithDel(l_tradedProductParams);
            TestDBUtility.insertWithDel(l_marketParams);
            
            WEB3EquityProduct l_equityProduct = new WEB3EquityProduct(l_productParams);

            FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
            TradingModule l_tradingModule =
                l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
            
            WEB3GentradeFinObjectManager l_finObjectManager =
                (WEB3GentradeFinObjectManager)l_finApp.getFinObjectManager();
            Market l_market = l_finObjectManager.getMarket(3303L);

            //拡張プロダクトマネージャを取得 
            WEB3EquityProductManager l_equityProductManager =
                (WEB3EquityProductManager)l_tradingModule.getProductManager();
            EqtypeTradedProductParams l_tradedProduct = l_equityProductManager.getTradedProduct(
                l_equityProduct, l_market, WEB3DateUtility.getDate("20040709","yyyyMMdd"));
            
            assertEquals(l_tradedProduct.getInstitutionCode(),"0D");
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    //株式取引銘柄テーブルを以下の条件で検索する
    //株式取引銘柄テーブルの検索結果が取得できなかった場合
    //同条件で株式取引銘柄updqテーブルを検索する。
    //取得したレコードを取引銘柄インスタンスとして返却する。
    public void testGetTradedProductCase2()
    {
        final String STR_METHOD_NAME = " testGetTradedProductCase2";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        try
        {
            Calendar l_calendar = Calendar.getInstance();
            l_calendar.set(Calendar.YEAR,2004);
            l_calendar.set(Calendar.MONTH,06);
            l_calendar.set(Calendar.DAY_OF_MONTH,8);
            l_calendar.set(Calendar.HOUR_OF_DAY,17);
            l_calendar.set(Calendar.MINUTE,00);
            l_calendar.set(Calendar.SECOND,01);

            Timestamp l_tsBizDate = new Timestamp(l_calendar.getTimeInMillis());

            ThreadLocalSystemAttributesRegistry.setAttribute(
                WEB3GentradeTradingTimeManagement.TIMESTAMP_TAG, l_tsBizDate);
            
            MarketParams l_marketParams = TestDBUtility.getMarketRow();
      
            ProductParams l_productParams = TestDBUtility.getProductRow();
            l_productParams.setProductId(1006169090018L);
            l_productParams.setProductType(ProductTypeEnum.EQUITY);
            l_productParams.setPrimaryMarketId(3303L);
            
            EqtypeTradedProductParams l_eqtypeTradedProductParams = TestDBUtility.getEqtypeTradedProductRow();
            l_eqtypeTradedProductParams.setProductId(1006169090018L);
            l_eqtypeTradedProductParams.setTradedProductId(100106139070605L);
            l_eqtypeTradedProductParams.setValidUntilBizDate("20040708");
            
            EqtypeTradedProductUpdqParams l_eqtypeTradedProductUpdqParams =
                insertEqtypeTradedProductUpdqRow();
            l_eqtypeTradedProductUpdqParams.setProductId(1006169090018L);
            l_eqtypeTradedProductUpdqParams.setTradedProductId(100106139070605L);
            l_eqtypeTradedProductUpdqParams.setValidUntilBizDate("20040709");
            
            EqtypeProductParams l_eqtypeProductParams = TestDBUtility.getEqtypeProductRow();
            l_eqtypeProductParams.setProductId(1006169090018L);
            l_eqtypeProductParams.setProductType(ProductTypeEnum.EQUITY);
            
            TradedProductParams l_tradedProductParams = TestDBUtility.getTradedProductRow();
            l_tradedProductParams.setProductId(1006169090018L);
            l_tradedProductParams.setTradedProductId(100106139070605L);
            l_tradedProductParams.setProductType(ProductTypeEnum.EQUITY);
            l_tradedProductParams.setBaseDate(WEB3DateUtility.getDate("20040709","yyyyMMdd"));
            
            this.deleteAll();
            TestDBUtility.insertWithDel(l_productParams);
            TestDBUtility.insertWithDel(l_eqtypeTradedProductParams);
            TestDBUtility.insertWithDel(l_eqtypeTradedProductUpdqParams);
            TestDBUtility.insertWithDel(l_eqtypeProductParams);
            TestDBUtility.insertWithDel(l_tradedProductParams);
            TestDBUtility.insertWithDel(l_marketParams);
            
            WEB3EquityProduct l_equityProduct = new WEB3EquityProduct(l_productParams);

            FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
            TradingModule l_tradingModule =
                l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
            
            WEB3GentradeFinObjectManager l_finObjectManager =
                (WEB3GentradeFinObjectManager)l_finApp.getFinObjectManager();
            Market l_market = l_finObjectManager.getMarket(3303L);

            //拡張プロダクトマネージャを取得 
            WEB3EquityProductManager l_equityProductManager =
                (WEB3EquityProductManager)l_tradingModule.getProductManager();
            EqtypeTradedProductParams l_tradedProduct = l_equityProductManager.getTradedProduct(
                l_equityProduct, l_market, WEB3DateUtility.getDate("20040709","yyyyMMdd"));
            
            assertEquals(l_tradedProduct.getInstitutionCode(),"0D");
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    //株式取引銘柄テーブルを以下の条件で検索する
    //株式取引銘柄テーブルの検索結果が取得できなかった場合
    //同条件で株式取引銘柄updqテーブルを検索する。
    //銘柄updqテーブルの検索結果が取得できなかった場合
    public void testGetTradedProductCase3()
    {
        final String STR_METHOD_NAME = " testGetTradedProductCase3";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        try
        {
            Calendar l_calendar = Calendar.getInstance();
            l_calendar.set(Calendar.YEAR,2004);
            l_calendar.set(Calendar.MONTH,06);
            l_calendar.set(Calendar.DAY_OF_MONTH,8);
            l_calendar.set(Calendar.HOUR_OF_DAY,17);
            l_calendar.set(Calendar.MINUTE,00);
            l_calendar.set(Calendar.SECOND,01);

            Timestamp l_tsBizDate = new Timestamp(l_calendar.getTimeInMillis());

            ThreadLocalSystemAttributesRegistry.setAttribute(
                WEB3GentradeTradingTimeManagement.TIMESTAMP_TAG, l_tsBizDate);
            
            MarketParams l_marketParams = TestDBUtility.getMarketRow();
      
            ProductParams l_productParams = TestDBUtility.getProductRow();
            l_productParams.setProductId(1006169090018L);
            l_productParams.setProductType(ProductTypeEnum.EQUITY);
            l_productParams.setPrimaryMarketId(3303L);
            
            EqtypeTradedProductParams l_eqtypeTradedProductParams = TestDBUtility.getEqtypeTradedProductRow();
            l_eqtypeTradedProductParams.setProductId(1006169090018L);
            l_eqtypeTradedProductParams.setTradedProductId(100106139070605L);
            l_eqtypeTradedProductParams.setValidUntilBizDate("20040708");
            
            EqtypeTradedProductUpdqParams l_eqtypeTradedProductUpdqParams =
                insertEqtypeTradedProductUpdqRow();
            l_eqtypeTradedProductUpdqParams.setProductId(1006169090019L);
            l_eqtypeTradedProductUpdqParams.setTradedProductId(100106139070605L);
            l_eqtypeTradedProductUpdqParams.setValidUntilBizDate("20040709");
            
            EqtypeProductParams l_eqtypeProductParams = TestDBUtility.getEqtypeProductRow();
            l_eqtypeProductParams.setProductId(1006169090018L);
            l_eqtypeProductParams.setProductType(ProductTypeEnum.EQUITY);
            
            TradedProductParams l_tradedProductParams = TestDBUtility.getTradedProductRow();
            l_tradedProductParams.setProductId(1006169090018L);
            l_tradedProductParams.setTradedProductId(100106139070605L);
            l_tradedProductParams.setProductType(ProductTypeEnum.EQUITY);
            l_tradedProductParams.setBaseDate(WEB3DateUtility.getDate("20040709","yyyyMMdd"));
            
            this.deleteAll();
            TestDBUtility.insertWithDel(l_productParams);
            TestDBUtility.insertWithDel(l_eqtypeTradedProductParams);
            TestDBUtility.insertWithDel(l_eqtypeTradedProductUpdqParams);
            TestDBUtility.insertWithDel(l_eqtypeProductParams);
            TestDBUtility.insertWithDel(l_tradedProductParams);
            TestDBUtility.insertWithDel(l_marketParams);
            
            WEB3EquityProduct l_equityProduct = new WEB3EquityProduct(l_productParams);

            FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
            TradingModule l_tradingModule =
                l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
            
            WEB3GentradeFinObjectManager l_finObjectManager =
                (WEB3GentradeFinObjectManager)l_finApp.getFinObjectManager();
            Market l_market = l_finObjectManager.getMarket(3303L);

            //拡張プロダクトマネージャを取得 
            WEB3EquityProductManager l_equityProductManager =
                (WEB3EquityProductManager)l_tradingModule.getProductManager();
            EqtypeTradedProductParams l_tradedProduct = l_equityProductManager.getTradedProduct(
                l_equityProduct, l_market, WEB3DateUtility.getDate("20040709","yyyyMMdd"));
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            assertEquals(l_ex.getErrorInfo(), WEB3ErrorCatalog.SYSTEM_ERROR_80005);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testGetTradedProductCase4()
    {
        final String STR_METHOD_NAME = " testGetTradedProductCase4";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        try
        {
            FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
            TradingModule l_tradingModule =
                l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
            WEB3GentradeFinObjectManager l_finObjectManager =
                (WEB3GentradeFinObjectManager)l_finApp.getFinObjectManager();
            Market l_market = l_finObjectManager.getMarket(3303L);
            //拡張プロダクトマネージャを取得 
            WEB3EquityProductManager l_equityProductManager =
                (WEB3EquityProductManager)l_tradingModule.getProductManager();
            EqtypeTradedProductParams l_tradedProduct = l_equityProductManager.getTradedProduct(
                null, l_market, WEB3DateUtility.getDate("20040709","yyyyMMdd"));
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            assertEquals(l_ex.getErrorInfo(), WEB3ErrorCatalog.SYSTEM_ERROR_80017);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testGetTradedProductCase5()
    {
        final String STR_METHOD_NAME = " testGetTradedProductCase5";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        try
        {
            ProductParams l_productParams = TestDBUtility.getProductRow();
            l_productParams.setProductId(1006169090018L);
            l_productParams.setProductType(ProductTypeEnum.EQUITY);
            l_productParams.setPrimaryMarketId(3303L);
            
            EqtypeProductParams l_eqtypeProductParams = TestDBUtility.getEqtypeProductRow();
            l_eqtypeProductParams.setProductId(1006169090018L);
            l_eqtypeProductParams.setProductType(ProductTypeEnum.EQUITY);
            this.deleteAll();
            TestDBUtility.insertWithDel(l_productParams);
            TestDBUtility.insertWithDel(l_eqtypeProductParams);
            WEB3EquityProduct l_equityProduct = new WEB3EquityProduct(l_productParams);
            FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
            TradingModule l_tradingModule =
                l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
            WEB3GentradeFinObjectManager l_finObjectManager =
                (WEB3GentradeFinObjectManager)l_finApp.getFinObjectManager();
            //拡張プロダクトマネージャを取得 
            WEB3EquityProductManager l_equityProductManager =
                (WEB3EquityProductManager)l_tradingModule.getProductManager();
            EqtypeTradedProductParams l_tradedProduct = l_equityProductManager.getTradedProduct(
                l_equityProduct, null, WEB3DateUtility.getDate("20040709","yyyyMMdd"));
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            assertEquals(l_ex.getErrorInfo(), WEB3ErrorCatalog.SYSTEM_ERROR_80017);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    public EqtypeTradedProductUpdqParams insertEqtypeTradedProductUpdqRow()
    {
        EqtypeTradedProductUpdqParams l_params = new EqtypeTradedProductUpdqParams();
        l_params.setTradedProductId(1006160060005L);
        l_params.setProductId(1006169090018L);
        l_params.setInstitutionCode("0D");
        l_params.setMarketId(3303L);
        l_params.setValidUntilBizDate("20040709");
        l_params.setListFlag(BooleanEnum.TRUE);
        l_params.setListType("1");
        l_params.setNewListType("1");
        l_params.setListedDate(Calendar.getInstance().getTime());
        l_params.setMarginableFlag(BooleanEnum.TRUE);
        l_params.setShortableFlag(BooleanEnum.TRUE);
        l_params.setMiniStockCanDealt(BooleanEnum.TRUE);
        l_params.setLotSize(100.0D);
        l_params.setLastClosingPrice(1000.0D);
        l_params.setMiniStockFlag(BooleanEnum.TRUE);
        l_params.setCreatedTimestamp(Calendar.getInstance().getTime());
        l_params.setLastUpdatedTimestamp(Calendar.getInstance().getTime());
        l_params.setBasePrice(10.0D);
        return l_params;
    }
  
  public void deleteAll()
  {
      try
      {
          TestDBUtility.deleteAll(IfoOrderUnitRow.TYPE);
          TestDBUtility.deleteAll(SubAccountRow.TYPE);
          TestDBUtility.deleteAll(MainAccountRow.TYPE);
          TestDBUtility.deleteAll(IfoOrderRow.TYPE);
          TestDBUtility.deleteAll(ProductRow.TYPE);
          TestDBUtility.deleteAll(IfoProductRow.TYPE);
          TestDBUtility.deleteAll(MarketRow.TYPE);
          TestDBUtility.deleteAll(IfoContractRow.TYPE);
          TestDBUtility.deleteAll(IfoTradedProductRow.TYPE);
          TestDBUtility.deleteAll(BranchRow.TYPE);
          TestDBUtility.deleteAll(InstitutionRow.TYPE);
          TestDBUtility.deleteAll(TradedProductRow.TYPE);
          TestDBUtility.deleteAll(IfoTradedProductUpdqRow.TYPE);
          TestDBUtility.deleteAll(TradedProductCalendarRow.TYPE);
          TestDBUtility.deleteAll(EqtypeTradedProductRow.TYPE);
          TestDBUtility.deleteAll(EqtypeTradedProductUpdqRow.TYPE);
          TestDBUtility.deleteAll(EqtypeProductRow.TYPE);
          TestDBUtility.deleteAll(TradedProductRow.TYPE);
          TestDBUtility.deleteAll(LastClosingPriceRow.TYPE);
          
      }
      catch (Exception l_ex)
      {
          log.error(l_ex.getMessage(), l_ex);
          fail();
      }
      log.info("*******************deleteAll***************** !!");
  }
  
  
}
@
