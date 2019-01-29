head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.00.57.06;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3EquityMarginExecuteReferenceServiceImplTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : WEB3EquityMarginExecuteReferenceServiceImplTest.java
Author Name      : Daiwa Institute of Research
Revision History : 2007/10/18 金傑（中訊）新規作成
*/
package webbroker3.equity.service.delegate.stdimpls;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.util.ThreadLocalSystemAttributesRegistry;
import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeOrder;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderParams;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderRow;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderUnitParams;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderUnitRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Order;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MarketParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MarketPreferencesParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MarketPreferencesRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MarketRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.OrderValidationResult;

import test.util.TestDBUtility;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.equity.service.delegate.stdimpls.WEB3EquityMarginExecuteReferenceServiceImpl.OrderStatus;
import webbroker3.gentrade.WEB3GentradeMainAccount;
import webbroker3.gentrade.WEB3GentradeMarket;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.gentrade.WEB3GentradeTradingClendarContext;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagementForMock;
import webbroker3.gentrade.data.CalendarRow;
import webbroker3.gentrade.data.OrderAcceptStatusParams;
import webbroker3.gentrade.data.OrderAcceptStatusRow;
import webbroker3.gentrade.data.TradingTimeRow;
import webbroker3.mock.TestBaseForMock;
import webbroker3.mock.util.WEB3MockObjectParamsValue;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

public class WEB3EquityMarginExecuteReferenceServiceImplTest  extends TestBaseForMock
{
    private WEB3EquityMarginExecuteReferenceServiceImpl l_serviceImpl = null;
    
    private String l_strProductCode = null;
    
    private String l_strMarketCode = null;
    
    private Date l_datOrderBizDate = null;
    
    private String l_strOrgOrderConditionDiv = null;
    
    private String l_strOrderConditionDiv = null;
    
    private String l_strQueryCond = null;
    
    private Object[] l_queryDataContainer = null;
    
    private List l_lisResults = null;
    
    private OrderStatus l_orderStatus = null;
    
    private WEB3GentradeSubAccount l_subAccount = null;

    /**
     * ログ出力ユーティリティ。<BR>
     */
    private static WEB3LogUtility log = 
        WEB3LogUtility.getInstance(WEB3EquityMarginExecuteReferenceServiceImplTest.class);

    public WEB3EquityMarginExecuteReferenceServiceImplTest(String name)
    {
        super(name);
    }
    
    protected void setUp() throws Exception
    {
        super.setUp();
        this.l_serviceImpl = new WEB3EquityMarginExecuteReferenceServiceImplForTest();
        this.l_orderStatus = this.l_serviceImpl.new OrderStatus();
        // interestEquityFlag
        this.l_orderStatus.interestEquityFlag = true;
        Calendar l_calendar = Calendar.getInstance();
        l_calendar.set(Calendar.YEAR,2007);
        l_calendar.set(Calendar.MONTH,9);
        l_calendar.set(Calendar.DAY_OF_MONTH,18);
        this.l_datOrderBizDate = l_calendar.getTime();
        this.l_strOrderConditionDiv = "1";
        this.l_subAccount = new WEB3GentradeSubAccount(TestDBUtility.getSubAccountRow());

    }
    
    protected void tearDown() throws Exception
    {
        this.l_serviceImpl = null;
        this.l_datOrderBizDate = null;
        this.l_strOrderConditionDiv = null;
        this.l_lisResults = null;
        super.tearDown();
    }
    
    /**
     * 株式注文単位テーブル.元発注条件==null 且つ 株式注文単位テーブル.発注条件!=null
     * 
     * 按「株式注文単位テーブル.発注条件」檢索
     *
     */
    public void testCreateQueryCond_C0001()
    {
        final String STR_METHOD_NAME = "testCreateQueryCond_C0001()";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {
            
            this.initData();
            this.l_strQueryCond = this.l_serviceImpl.createQueryString(
                this.l_strProductCode,
                this.l_strMarketCode,
                this.l_datOrderBizDate,
                this.l_orderStatus,
                this.l_strOrderConditionDiv);
            
            this.l_queryDataContainer = this.l_serviceImpl.createQueryDataContainer(
                this.l_subAccount,
                this.l_strProductCode,
                this.l_strMarketCode,
                this.l_datOrderBizDate,
                this.l_orderStatus,
                this.l_strOrderConditionDiv);
            
            this.getSearchData();
            assertEquals(3,this.l_lisResults.size());
            
            // 株式注文単位テーブル.発注条件
            assertEquals("1",((EqtypeOrderUnitRow)this.l_lisResults.get(0)).getOrderConditionType());
            assertEquals("1",((EqtypeOrderUnitRow)this.l_lisResults.get(1)).getOrderConditionType());
            assertEquals("1",((EqtypeOrderUnitRow)this.l_lisResults.get(2)).getOrderConditionType());
            
            // 株式注文単位テーブル.元発注条件
            assertNull(((EqtypeOrderUnitRow)this.l_lisResults.get(0)).getOrgOrderConditionType());
            assertNull(((EqtypeOrderUnitRow)this.l_lisResults.get(1)).getOrgOrderConditionType());
            assertNull(((EqtypeOrderUnitRow)this.l_lisResults.get(2)).getOrgOrderConditionType());
            
        }
        catch (Exception l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            log.entering(TEST_END + STR_METHOD_NAME);
            fail();
        }
        log.entering(TEST_END + STR_METHOD_NAME);
    }
    
    /**
     * 株式注文単位テーブル.元発注条件!=null 且つ 株式注文単位テーブル.発注条件==null
     * 
     * 按「株式注文単位テーブル.元発注条件」檢索
     *
     */
    public void testCreateQueryCond_C0002()
    {
        final String STR_METHOD_NAME = "testCreateQueryCond_C0002()";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {
            this.l_strOrgOrderConditionDiv = "2";
            this.l_strOrderConditionDiv = null;
            this.initData();
            this.l_strQueryCond = this.l_serviceImpl.createQueryString(
                    this.l_strProductCode,
                    this.l_strMarketCode,
                    this.l_datOrderBizDate,
                    this.l_orderStatus,
                    this.l_strOrderConditionDiv);
                
                this.l_queryDataContainer = this.l_serviceImpl.createQueryDataContainer(
                    this.l_subAccount,
                    this.l_strProductCode,
                    this.l_strMarketCode,
                    this.l_datOrderBizDate,
                    this.l_orderStatus,
                    this.l_strOrderConditionDiv);
            
            this.getSearchData();
            assertEquals(3,this.l_lisResults.size());
            
            // 株式注文単位テーブル.元発注条件
            assertEquals("2",((EqtypeOrderUnitRow)this.l_lisResults.get(0)).getOrgOrderConditionType());
            assertEquals("2",((EqtypeOrderUnitRow)this.l_lisResults.get(1)).getOrgOrderConditionType());
            assertEquals("2",((EqtypeOrderUnitRow)this.l_lisResults.get(2)).getOrgOrderConditionType());
            
            // 株式注文単位テーブル.発注条件
            assertNull(((EqtypeOrderUnitRow)this.l_lisResults.get(0)).getOrderConditionType());
            assertNull(((EqtypeOrderUnitRow)this.l_lisResults.get(1)).getOrderConditionType());
            assertNull(((EqtypeOrderUnitRow)this.l_lisResults.get(2)).getOrderConditionType());
            
        }
        catch (Exception l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            log.entering(TEST_END + STR_METHOD_NAME);
            fail();
        }
        log.entering(TEST_END + STR_METHOD_NAME);
    }
    
    /**
     * 株式注文単位テーブル.元発注条件=1 且つ 株式注文単位テーブル.発注条件==2
     * 
     * 按「株式注文単位テーブル.元発注条件」檢索，檢索不到數據
     *
     */
    public void testCreateQueryCond_C0003()
    {
        final String STR_METHOD_NAME = "testCreateQueryCond_C0003()";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {
            this.l_strOrgOrderConditionDiv = "1";
            this.l_strOrderConditionDiv = "2";
            this.initData();
            this.l_strQueryCond = this.l_serviceImpl.createQueryString(
                    this.l_strProductCode,
                    this.l_strMarketCode,
                    this.l_datOrderBizDate,
                    this.l_orderStatus,
                    this.l_strOrderConditionDiv);
                
                this.l_queryDataContainer = this.l_serviceImpl.createQueryDataContainer(
                    this.l_subAccount,
                    this.l_strProductCode,
                    this.l_strMarketCode,
                    this.l_datOrderBizDate,
                    this.l_orderStatus,
                    this.l_strOrderConditionDiv);
            
            this.getSearchData();
            assertEquals(0,this.l_lisResults.size());            
        }
        catch (Exception l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            log.entering(TEST_END + STR_METHOD_NAME);
            fail();
        }
        log.entering(TEST_END + STR_METHOD_NAME);
    }
    
    /**
     * 株式注文単位テーブル.元発注条件=1 且つ 株式注文単位テーブル.発注条件==1
     * 
     * 按「株式注文単位テーブル.元発注条件」檢索，檢索到數據
     *
     */
    public void testCreateQueryCond_C0004()
    {
        final String STR_METHOD_NAME = "testCreateQueryCond_C0003()";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {
            this.l_strOrgOrderConditionDiv = "1";
            this.l_strOrderConditionDiv = "1";
            this.initData();
            this.l_strQueryCond = this.l_serviceImpl.createQueryString(
                    this.l_strProductCode,
                    this.l_strMarketCode,
                    this.l_datOrderBizDate,
                    this.l_orderStatus,
                    this.l_strOrderConditionDiv);
                
                this.l_queryDataContainer = this.l_serviceImpl.createQueryDataContainer(
                    this.l_subAccount,
                    this.l_strProductCode,
                    this.l_strMarketCode,
                    this.l_datOrderBizDate,
                    this.l_orderStatus,
                    this.l_strOrderConditionDiv);
            
            this.getSearchData();
            assertEquals(3,this.l_lisResults.size());
            
            // 株式注文単位テーブル.元発注条件
            assertEquals("1",((EqtypeOrderUnitRow)this.l_lisResults.get(0)).getOrgOrderConditionType());
            assertEquals("1",((EqtypeOrderUnitRow)this.l_lisResults.get(1)).getOrgOrderConditionType());
            assertEquals("1",((EqtypeOrderUnitRow)this.l_lisResults.get(2)).getOrgOrderConditionType());
            
            // 株式注文単位テーブル.発注条件
            assertEquals("1",((EqtypeOrderUnitRow)this.l_lisResults.get(0)).getOrderConditionType());
            assertEquals("1",((EqtypeOrderUnitRow)this.l_lisResults.get(1)).getOrderConditionType());
            assertEquals("1",((EqtypeOrderUnitRow)this.l_lisResults.get(2)).getOrderConditionType());
            
        }
        catch (Exception l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            log.entering(TEST_END + STR_METHOD_NAME);
            fail();
        }
        log.entering(TEST_END + STR_METHOD_NAME);
    }
    
    private void initData()
    {
        final String STR_METHOD_NAME = "initData()";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {
            // EqtypeOrderUnitRow.TYPE
            TestDBUtility.deleteAll(EqtypeOrderUnitRow.TYPE);
            EqtypeOrderUnitParams l_eqtypeOrderUnitParams1 = TestDBUtility.getEqtypeOrderUnitRow();
            l_eqtypeOrderUnitParams1.setOrderUnitId(3304148080001L);
            l_eqtypeOrderUnitParams1.setOrderType(OrderTypeEnum.EQUITY_BUY);
            l_eqtypeOrderUnitParams1.setBizDate(WEB3DateUtility.formatDate(this.l_datOrderBizDate,"yyyyMMdd"));
            l_eqtypeOrderUnitParams1.setOrgOrderConditionType(this.l_strOrgOrderConditionDiv);
            l_eqtypeOrderUnitParams1.setOrderConditionType(this.l_strOrderConditionDiv);
            l_eqtypeOrderUnitParams1.setSonarTradedCode("11");
            TestDBUtility.insertWithDel(l_eqtypeOrderUnitParams1);
            
            EqtypeOrderUnitParams l_eqtypeOrderUnitParams2 = TestDBUtility.getEqtypeOrderUnitRow();
            l_eqtypeOrderUnitParams2.setOrderUnitId(3304148080002L);
            l_eqtypeOrderUnitParams2.setOrderType(OrderTypeEnum.EQUITY_BUY);
            l_eqtypeOrderUnitParams2.setBizDate(WEB3DateUtility.formatDate(this.l_datOrderBizDate,"yyyyMMdd"));
            l_eqtypeOrderUnitParams2.setOrgOrderConditionType(this.l_strOrgOrderConditionDiv);
            l_eqtypeOrderUnitParams2.setOrderConditionType(this.l_strOrderConditionDiv);
            l_eqtypeOrderUnitParams2.setSonarTradedCode("11");
            TestDBUtility.insertWithDel(l_eqtypeOrderUnitParams2);
            
            EqtypeOrderUnitParams l_eqtypeOrderUnitParams3 = TestDBUtility.getEqtypeOrderUnitRow();
            l_eqtypeOrderUnitParams3.setOrderUnitId(3304148080003L);
            l_eqtypeOrderUnitParams3.setOrderType(OrderTypeEnum.EQUITY_BUY);
            l_eqtypeOrderUnitParams3.setBizDate(WEB3DateUtility.formatDate(this.l_datOrderBizDate,"yyyyMMdd"));
            l_eqtypeOrderUnitParams3.setOrgOrderConditionType(this.l_strOrgOrderConditionDiv);
            l_eqtypeOrderUnitParams3.setOrderConditionType(this.l_strOrderConditionDiv);
            l_eqtypeOrderUnitParams3.setSonarTradedCode("11");
            TestDBUtility.insertWithDel(l_eqtypeOrderUnitParams3);
            
            EqtypeOrderUnitParams l_eqtypeOrderUnitParams4 = TestDBUtility.getEqtypeOrderUnitRow();
            l_eqtypeOrderUnitParams4.setOrderUnitId(3304148080004L);
            l_eqtypeOrderUnitParams4.setBizDate(WEB3DateUtility.formatDate(this.l_datOrderBizDate,"yyyyMMdd"));
            l_eqtypeOrderUnitParams4.setOrgOrderConditionType(this.l_strOrgOrderConditionDiv);
            l_eqtypeOrderUnitParams4.setOrderConditionType(this.l_strOrderConditionDiv);
            l_eqtypeOrderUnitParams4.setSonarTradedCode("11");
            TestDBUtility.insertWithDel(l_eqtypeOrderUnitParams4);
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    private void getSearchData()
    {
        final String STR_METHOD_NAME = "getSearchData()";
        log.entering(TEST_START + STR_METHOD_NAME); 
        try
        {
            this.l_lisResults = Processors.getDefaultProcessor().doFindAllQuery(
                    EqtypeOrderUnitRow.TYPE, this.l_strQueryCond,this.l_queryDataContainer);
        }
        catch (Exception l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            log.entering(TEST_END + STR_METHOD_NAME);
            fail();
        }
        log.entering(TEST_END + STR_METHOD_NAME);
    }

    /**
     * (validate注文訂正可能状態)
     * PTS市場の場合(引数.市場.isPTS市場() == false )
     * 拡張株式注文マネージャ.validate注文訂正可能状態()をコールする。正常。
     */
    public void testValidateOrderForChangeabilityCase0001()
    {
        final String STR_METHOD_NAME = "testValidateOrderForChangeabilityCase0001()";
        log.entering(STR_METHOD_NAME);
        
        MOCK_MANAGER.setIsMockUsed(true);
        try
        {
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.equity.WEB3EquityOrderManager",
                "validateOrderForChangeability",
                new Class[] {Order.class},
                null);
            
            TestDBUtility.deleteAll(MarketRow.TYPE);
            MarketParams l_marketRow = TestDBUtility.getMarketRow();
            l_marketRow.setMarketCode("1");
            TestDBUtility.insertWithDel(l_marketRow);
            
            TestDBUtility.deleteAll(EqtypeOrderRow.TYPE);
            EqtypeOrderParams l_EqtypeOrderParams = TestDBUtility.getEqtypeOrderRow();
            TestDBUtility.insertWithDel(l_EqtypeOrderParams);
            
            TestDBUtility.deleteAll(MarketPreferencesRow.TYPE);
            
            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
            WEB3GentradeMarket l_market =
                (WEB3GentradeMarket)l_finApp.getFinObjectManager().getMarket(3303);

            TradingModule l_tradingMod = l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
            Order l_order = l_tradingMod.getOrderManager().getOrder(2099);
            WEB3EquityMarginExecuteReferenceServiceImpl l_impl =
                new  WEB3EquityMarginExecuteReferenceServiceImpl();
            
            l_impl.validateOrderForChangeability(l_order, l_market);
            
            WEB3MockObjectParamsValue l_paramsValue1 = TestBaseForMock.MOCK_MANAGER.getMethodParamsValue(
                    "webbroker3.equity.WEB3EquityOrderManager",
                    "validateOrderForChangeability",
                    new Class[] {Order.class});
            assertEquals(new Long(2099) + "", 
                    ((EqTypeOrder)l_paramsValue1.getFirstCalled()[0]).getOrderId() + "");

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
     * (validate注文訂正可能状態)
     * PTS市場の場合(引数.市場.isPTS市場() == false )
     * 拡張株式注文マネージャ.validate注文訂正可能状態()をコールする抛異常
     */
    public void testValidateOrderForChangeabilityCase0002()
    {
        final String STR_METHOD_NAME = "testValidateOrderForChangeabilityCase0002()";
        log.entering(STR_METHOD_NAME);
        
        MOCK_MANAGER.setIsMockUsed(true);
        try
        {
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.equity.WEB3EquityOrderManager",
                "validateOrderForChangeability",
                new Class[] {Order.class},
                new WEB3BaseException(WEB3ErrorCatalog.BUSINESS_ERROR_00406, ""));
            
            TestDBUtility.deleteAll(MarketRow.TYPE);
            MarketParams l_marketRow = TestDBUtility.getMarketRow();
            l_marketRow.setMarketCode("1");
            TestDBUtility.insertWithDel(l_marketRow);
            
            TestDBUtility.deleteAll(EqtypeOrderRow.TYPE);
            EqtypeOrderParams l_EqtypeOrderParams = TestDBUtility.getEqtypeOrderRow();
            TestDBUtility.insertWithDel(l_EqtypeOrderParams);
            
            TestDBUtility.deleteAll(MarketPreferencesRow.TYPE);
            
            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
            WEB3GentradeMarket l_market =
                (WEB3GentradeMarket)l_finApp.getFinObjectManager().getMarket(3303);

            TradingModule l_tradingMod = l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
            Order l_order = l_tradingMod.getOrderManager().getOrder(2099);
            WEB3EquityMarginExecuteReferenceServiceImpl l_impl =
                new  WEB3EquityMarginExecuteReferenceServiceImpl();
            
            l_impl.validateOrderForChangeability(l_order, l_market);
            
            fail();
        }
        catch(WEB3BaseException l_ex)
        {
            log.debug(STR_METHOD_NAME, l_ex);
            assertEquals(l_ex.getErrorInfo(),WEB3ErrorCatalog.BUSINESS_ERROR_00406);

            WEB3MockObjectParamsValue l_paramsValue1 = TestBaseForMock.MOCK_MANAGER.getMethodParamsValue(
                    "webbroker3.equity.WEB3EquityOrderManager",
                    "validateOrderForChangeability",
                    new Class[] {Order.class});
            assertEquals(new Long(2099) + "", 
                    ((EqTypeOrder)l_paramsValue1.getFirstCalled()[0]).getOrderId() + "");

            log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");

        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
    }
    
    /**
     * (validate注文訂正可能状態)
     * PTS市場の場合(引数.市場.isPTS市場() == true )
     * PTS注文マネージャ.validatePTS注文訂正可能状態()をコールする正常。
     */
    public void testValidateOrderForChangeabilityCase0003()
    {
        final String STR_METHOD_NAME = "testValidateOrderForChangeabilityCase0003()";
        log.entering(STR_METHOD_NAME);
        
        MOCK_MANAGER.setIsMockUsed(true);
        try
        {
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.equity.WEB3EquityPTSOrderManager",
                "validatePTSOrderForChangeability",
                new Class[]{ Order.class }, 
                null);
            
            TestDBUtility.deleteAll(MarketRow.TYPE);
            MarketParams l_marketRow = TestDBUtility.getMarketRow();
            l_marketRow.setMarketCode("1");
            TestDBUtility.insertWithDel(l_marketRow);
            
            TestDBUtility.deleteAll(EqtypeOrderRow.TYPE);
            EqtypeOrderParams l_EqtypeOrderParams = TestDBUtility.getEqtypeOrderRow();
            TestDBUtility.insertWithDel(l_EqtypeOrderParams);
            
            TestDBUtility.deleteAll(MarketPreferencesRow.TYPE);
            MarketPreferencesParams l_MarketPreferencesRow = TestDBUtility.getMarketPreferencesRow();
            l_MarketPreferencesRow.setName("equity.pts.market.div");
            l_MarketPreferencesRow.setValue("1");
            TestDBUtility.insertWithDel(l_MarketPreferencesRow);
            
            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
            WEB3GentradeMarket l_market =
                (WEB3GentradeMarket)l_finApp.getFinObjectManager().getMarket(3303);

            TradingModule l_tradingMod = l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
            Order l_order = l_tradingMod.getOrderManager().getOrder(2099);
            
            WEB3EquityMarginExecuteReferenceServiceImpl l_impl =
                new  WEB3EquityMarginExecuteReferenceServiceImpl();
            
            l_impl.validateOrderForChangeability(l_order, l_market);
            
            WEB3MockObjectParamsValue l_paramsValue1 = TestBaseForMock.MOCK_MANAGER.getMethodParamsValue(
                    "webbroker3.equity.WEB3EquityPTSOrderManager",
                    "validatePTSOrderForChangeability",
                    new Class[]{ Order.class });
            assertEquals(new Long(2099) + "", 
                    ((EqTypeOrder)l_paramsValue1.getFirstCalled()[0]).getOrderId() + "");
            
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
     * (validate注文訂正可能状態)
     * PTS市場の場合(引数.市場.isPTS市場() == true )
     * PTS注文マネージャ.validatePTS注文訂正可能状態()をコールする抛異常。
     */
    public void testValidateOrderForChangeabilityCase0004()
    {
        final String STR_METHOD_NAME = "testValidateOrderForChangeabilityCase0004()";
        log.entering(STR_METHOD_NAME);
        
        MOCK_MANAGER.setIsMockUsed(true);
        try
        {
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.equity.WEB3EquityPTSOrderManager",
                "validatePTSOrderForChangeability",
                new Class[]{ Order.class }, 
                new WEB3BaseException(WEB3ErrorCatalog.BUSINESS_ERROR_00406, ""));
            
            TestDBUtility.deleteAll(MarketRow.TYPE);
            MarketParams l_marketRow = TestDBUtility.getMarketRow();
            l_marketRow.setMarketCode("1");
            TestDBUtility.insertWithDel(l_marketRow);
            
            TestDBUtility.deleteAll(EqtypeOrderRow.TYPE);
            EqtypeOrderParams l_EqtypeOrderParams = TestDBUtility.getEqtypeOrderRow();
            TestDBUtility.insertWithDel(l_EqtypeOrderParams);
            
            TestDBUtility.deleteAll(MarketPreferencesRow.TYPE);
            MarketPreferencesParams l_MarketPreferencesRow = TestDBUtility.getMarketPreferencesRow();
            l_MarketPreferencesRow.setName("equity.pts.market.div");
            l_MarketPreferencesRow.setValue("1");
            TestDBUtility.insertWithDel(l_MarketPreferencesRow);
            
            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
            WEB3GentradeMarket l_market =
                (WEB3GentradeMarket)l_finApp.getFinObjectManager().getMarket(3303);

            TradingModule l_tradingMod = l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
            Order l_order = l_tradingMod.getOrderManager().getOrder(2099);
            
            WEB3EquityMarginExecuteReferenceServiceImpl l_impl =
                new  WEB3EquityMarginExecuteReferenceServiceImpl();
            
            l_impl.validateOrderForChangeability(l_order, l_market);
            
            fail();
        }
        catch(WEB3BaseException l_ex)
        {
            log.debug(STR_METHOD_NAME, l_ex);
            assertEquals(l_ex.getErrorInfo(),WEB3ErrorCatalog.BUSINESS_ERROR_00406);

            WEB3MockObjectParamsValue l_paramsValue1 = TestBaseForMock.MOCK_MANAGER.getMethodParamsValue(
                    "webbroker3.equity.WEB3EquityPTSOrderManager",
                    "validatePTSOrderForChangeability",
                    new Class[]{ Order.class });
            assertEquals(new Long(2099) + "", 
                    ((EqTypeOrder)l_paramsValue1.getFirstCalled()[0]).getOrderId() + "");

            log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");

        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
    }
    

    /**
     * validate取引可能顧客
     參數顧客 == null，
     */
    public void testValidateAccountForTradingCase0001()
    {
        final String STR_METHOD_NAME = "testValidateAccountForTradingCase0001()";
        log.entering(STR_METHOD_NAME);

        try
        {
            WEB3GentradeMainAccount l_mainAccount = null;
            
            WEB3EquityMarginExecuteReferenceServiceImpl l_impl =
                new  WEB3EquityMarginExecuteReferenceServiceImpl();
            
            OrderValidationResult l_orderValidationResult =
                l_impl.validateAccountForTrading(l_mainAccount, null);

            assertEquals(l_orderValidationResult.getProcessingResult().getErrorInfo(),
                WEB3ErrorCatalog.SYSTEM_ERROR_80017);
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
     * validate取引可能顧客
     * 注文チェック.validate取引可能顧客(顧客、発注日)をコールする。 
     */
    public void testValidateAccountForTradingCase0002()
    {
        final String STR_METHOD_NAME = "testValidateAccountForTradingCase0002()";
        log.entering(STR_METHOD_NAME);
        
        MOCK_MANAGER.setIsMockUsed(true);
        try
        {
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.gentrade.WEB3GentradeOrderValidator",
                    "validateAccountForTrading",
                    new Class[] {WEB3GentradeMainAccount.class, Timestamp.class},
                    OrderValidationResult.VALIDATION_OK_RESULT);
            
            //MainAccountParams
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            TestDBUtility.insertWithDel(l_mainAccountParams);
            
            TestDBUtility.deleteAll(MarketRow.TYPE);
            MarketParams l_marketRow = TestDBUtility.getMarketRow();
            l_marketRow.setMarketCode("1");
            TestDBUtility.insertWithDel(l_marketRow);
            
            TestDBUtility.deleteAll(MarketPreferencesRow.TYPE);
            MarketPreferencesParams l_MarketPreferencesRow = TestDBUtility.getMarketPreferencesRow();
            l_MarketPreferencesRow.setName("equity.pts.market.div");
            l_MarketPreferencesRow.setValue("1");
            TestDBUtility.insertWithDel(l_MarketPreferencesRow);
            
            Date l_datsystime = WEB3DateUtility.getDate("20071225", "yyyyMMdd");
                //Calendar.getInstance().getTime();
            WEB3GentradeTradingTimeManagementForMock.mockGetOrderBizDate(l_datsystime);
            
            FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
            WEB3GentradeMainAccount l_mainAccount =
                (WEB3GentradeMainAccount)l_finApp.getAccountManager().getMainAccount(l_mainAccountParams.getAccountId());
            
            TestDBUtility.deleteAll(CalendarRow.TYPE);
            
            WEB3EquityMarginExecuteReferenceServiceImpl l_impl =
                new  WEB3EquityMarginExecuteReferenceServiceImpl();
            
            OrderValidationResult l_orderValidationResult =
                l_impl.validateAccountForTrading(l_mainAccount, "1");

            WEB3MockObjectParamsValue l_paramsValue2 = TestBaseForMock.MOCK_MANAGER.getMethodParamsValue(
                    "webbroker3.gentrade.WEB3GentradeOrderValidator",
                    "validateAccountForTrading",
                    new Class[] {WEB3GentradeMainAccount.class, Timestamp.class});
            
            assertEquals(new Long(333812512246L) + "", 
                    ((WEB3GentradeMainAccount)l_paramsValue2.getFirstCalled()[0]).getAccountId() + "");
            
            assertEquals(WEB3DateUtility.formatDate(l_datsystime, "yyyyMMdd"), 
                    WEB3DateUtility.formatDate((Date)l_paramsValue2.getFirstCalled()[1], "yyyyMMdd"));
            
            assertEquals(l_orderValidationResult, OrderValidationResult.VALIDATION_OK_RESULT);

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
     * validate取引可能顧客
     * 注文チェック.validate取引可能顧客(顧客)をコールする。 
     */
    public void testValidateAccountForTradingCase0003()
    {
        final String STR_METHOD_NAME = "testValidateAccountForTradingCase0003()";
        log.entering(STR_METHOD_NAME);

        MOCK_MANAGER.setIsMockUsed(true);
        try
        {
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.gentrade.WEB3GentradeOrderValidator",
                    "validateAccountForTrading",
                    new Class[] {WEB3GentradeMainAccount.class},
                    OrderValidationResult.VALIDATION_OK_RESULT);
            
            //MainAccountParams
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            TestDBUtility.insertWithDel(l_mainAccountParams);

            Date l_datsystime = WEB3DateUtility.getDate("20071225", "yyyyMMdd");
                //Calendar.getInstance().getTime();
            WEB3GentradeTradingTimeManagementForMock.mockGetOrderBizDate(l_datsystime);
            
            FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
            WEB3GentradeMainAccount l_mainAccount =
                (WEB3GentradeMainAccount)l_finApp.getAccountManager().getMainAccount(l_mainAccountParams.getAccountId());
            
            TestDBUtility.deleteAll(CalendarRow.TYPE);
            
            WEB3EquityMarginExecuteReferenceServiceImpl l_impl =
                new  WEB3EquityMarginExecuteReferenceServiceImpl();
            
            OrderValidationResult l_orderValidationResult =
                l_impl.validateAccountForTrading(l_mainAccount, null);

            WEB3MockObjectParamsValue l_paramsValue1 = TestBaseForMock.MOCK_MANAGER.getMethodParamsValue(
                    "webbroker3.gentrade.WEB3GentradeOrderValidator",
                    "validateAccountForTrading",
                    new Class[] {WEB3GentradeMainAccount.class});

            assertEquals(new Long(333812512246L) + "", 
                    ((WEB3GentradeMainAccount)l_paramsValue1.getFirstCalled()[0]).getAccountId() + "");
            
            assertEquals(l_orderValidationResult, OrderValidationResult.VALIDATION_OK_RESULT);

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
     * validate取引可能顧客
       注文チェック.validate取引可能顧客(顧客、発注日)をコールする。
       PTS取引時間管理.get発注日()  抛異常
     */
    public void testValidateAccountForTradingCase0004()
    {
        final String STR_METHOD_NAME = "testValidateAccountForTradingCase0004()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            //MainAccountParams
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            TestDBUtility.insertWithDel(l_mainAccountParams);
            
            TestDBUtility.deleteAll(MarketRow.TYPE);
            MarketParams l_marketRow = TestDBUtility.getMarketRow();
            l_marketRow.setMarketCode("1");
            TestDBUtility.insertWithDel(l_marketRow);
            
            TestDBUtility.deleteAll(MarketPreferencesRow.TYPE);
            MarketPreferencesParams l_MarketPreferencesRow = TestDBUtility.getMarketPreferencesRow();
            l_MarketPreferencesRow.setName("equity.pts.market.div");
            l_MarketPreferencesRow.setValue("1");
            TestDBUtility.insertWithDel(l_MarketPreferencesRow);
            
            TestDBUtility.deleteAll(TradingTimeRow.TYPE);
            
            FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
            WEB3GentradeMainAccount l_mainAccount =
                (WEB3GentradeMainAccount)l_finApp.getAccountManager().getMainAccount(l_mainAccountParams.getAccountId());
            
            TestDBUtility.deleteAll(CalendarRow.TYPE);
            
            WEB3EquityMarginExecuteReferenceServiceImpl l_impl =
                new  WEB3EquityMarginExecuteReferenceServiceImpl();
            
            OrderValidationResult l_orderValidationResult =
                l_impl.validateAccountForTrading(l_mainAccount, "1");
            
            assertEquals(l_orderValidationResult.getProcessingResult().getErrorInfo(),
                    WEB3ErrorCatalog.SYSTEM_ERROR_80006);

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
     * 引数.照会区分 == ”訂正取消一覧”
     * 引数.商品区分 != ”現物株式” and 引数.is信用客 == true の場合
     * 
     * 注文受付状態.信用取引フラグ = true
     * 注文受付状態.現引・現渡フラグ = true
     *
     */
    public void testValidateOrderAccept_C0001()
    {
        final String STR_METHOD_NAME = "testValidateOrderAccept_C0001()";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {
           
            // OrderAcceptStatusRow
            TestDBUtility.deleteAll(OrderAcceptStatusRow.TYPE);
            OrderAcceptStatusParams l_orderAcceptStatusParams = TestDBUtility.getOrderAcceptStatusRow();
            l_orderAcceptStatusParams.setInstitutionCode("0D");
            l_orderAcceptStatusParams.setBranchCode("381");
            l_orderAcceptStatusParams.setOrderAccProduct("03");
            l_orderAcceptStatusParams.setOrderAccTransaction("05");
            l_orderAcceptStatusParams.setOrderAcceptStatus("0");
            TestDBUtility.insertWithDel(l_orderAcceptStatusParams);
            
            
            WEB3GentradeTradingClendarContext l_clendarContext = new WEB3GentradeTradingClendarContext(); 
            l_clendarContext.setInstitutionCode("0D");
            l_clendarContext.setBranchCode("381");
            l_clendarContext.setOrderAcceptTransaction("01");
                
                
                ThreadLocalSystemAttributesRegistry.setAttribute(
                    "web3.tradingcalendarcontext",l_clendarContext);
            String l_strReferenceType = "1";
            String l_strProductType = "2";
            boolean l_blnIsMargin = true;
            boolean l_blnIsOffFloorExecute = false;
            this.l_orderStatus = this.l_serviceImpl.validateOrderAccept(
                l_strReferenceType, l_strProductType, l_blnIsMargin, l_blnIsOffFloorExecute);
            
            assertTrue(this.l_orderStatus.interestMarginFlag);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    /**
     * 引数.照会区分 == ”訂正取消一覧”
     * 引数.商品区分 != ”現物株式” and 引数.is信用客 == true の場合
     * 
     * 注文受付状態.信用取引フラグ = false
     * 注文受付状態.現引・現渡フラグ = false
     * 
     * 抛出異常信息：注文受付ステイタス＝緊急停止中
     *
     */
    public void testValidateOrderAccept_C0002()
    {
        final String STR_METHOD_NAME = "testValidateOrderAccept_C0002()";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {
           
            // OrderAcceptStatusRow
            TestDBUtility.deleteAll(OrderAcceptStatusRow.TYPE);
            OrderAcceptStatusParams l_orderAcceptStatusParams1 = TestDBUtility.getOrderAcceptStatusRow();
            l_orderAcceptStatusParams1.setInstitutionCode("0D");
            l_orderAcceptStatusParams1.setBranchCode("381");
            l_orderAcceptStatusParams1.setOrderAccProduct("03");
            l_orderAcceptStatusParams1.setOrderAccTransaction("05");
            l_orderAcceptStatusParams1.setOrderAcceptStatus("2");
            TestDBUtility.insertWithDel(l_orderAcceptStatusParams1);
            
            OrderAcceptStatusParams l_orderAcceptStatusParams2 = TestDBUtility.getOrderAcceptStatusRow();
            l_orderAcceptStatusParams2.setInstitutionCode("0D");
            l_orderAcceptStatusParams2.setBranchCode("381");
            l_orderAcceptStatusParams2.setOrderAccProduct("03");
            l_orderAcceptStatusParams2.setOrderAccTransaction("06");
            l_orderAcceptStatusParams2.setOrderAcceptStatus("2");
            TestDBUtility.insertWithDel(l_orderAcceptStatusParams2);
            
            
            WEB3GentradeTradingClendarContext l_clendarContext = new WEB3GentradeTradingClendarContext(); 
            l_clendarContext.setInstitutionCode("0D");
            l_clendarContext.setBranchCode("381");
            l_clendarContext.setOrderAcceptTransaction("01");
                
                
                ThreadLocalSystemAttributesRegistry.setAttribute(
                    "web3.tradingcalendarcontext",l_clendarContext);
            String l_strReferenceType = "1";
            String l_strProductType = "2";
            boolean l_blnIsMargin = true;
            boolean l_blnIsOffFloorExecute = false;
            this.l_orderStatus = this.l_serviceImpl.validateOrderAccept(
                l_strReferenceType, l_strProductType, l_blnIsMargin, l_blnIsOffFloorExecute);
            
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00012,l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    private class WEB3EquityMarginExecuteReferenceServiceImplForTest extends WEB3EquityMarginExecuteReferenceServiceImpl
    {
        public WEB3GentradeSubAccount getSubAccount() throws WEB3BaseException
        {
            return null;
        }
    }
}
@
