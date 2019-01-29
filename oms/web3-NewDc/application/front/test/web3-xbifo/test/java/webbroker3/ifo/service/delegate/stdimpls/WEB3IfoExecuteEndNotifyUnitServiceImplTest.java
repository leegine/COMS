head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.00.47.18;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3IfoExecuteEndNotifyUnitServiceImplTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 先物OP出来終了通知UnitServiceImplTest(WEB3IfoExecuteEndNotifyUnitServiceImplTest.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/06/14 趙林鵬(中訊) 新規作成
*/

package webbroker3.ifo.service.delegate.stdimpls;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataFindException;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Institution;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Market;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Order;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderAction;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderCategEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderExecution;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderExpirationStatusEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderOpenStatusEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderStatusEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderUnit;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Product;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SideEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TaxTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradedProduct;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingCalendarDetails;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.BranchParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.BranchRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.InstitutionRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MarketParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.ProductParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.ProductRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.SubAccountParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.SubAccountRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.TradedProductParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.TradedProductRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.TradedProductUpdqParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.TradedProductUpdqRow;
import com.fitechlabs.xtrade.plugin.tc.xbifo.IfoDerivativeTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.xbifo.IfoOrderExecutionConditionType;
import com.fitechlabs.xtrade.plugin.tc.xbifo.IfoOrderUnit;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoOrderUnitParams;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoOrderUnitRow;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoProductParams;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoProductRow;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoTradedProductParams;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoTradedProductRow;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoTradedProductUpdqParams;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoTradedProductUpdqRow;

import test.util.TestDBUtility;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.gentrade.WEB3GentradeBizDate;
import webbroker3.gentrade.WEB3GentradeTradingClendarDetailsImpl;
import webbroker3.ifo.WEB3IfoTradedProductImpl;
import webbroker3.ifo.WEB3OptionOrderManagerImpl;
import webbroker3.mock.TestBaseForMock;
import webbroker3.triggerorder.base.data.RsvIfoOrderActionRow;
import webbroker3.triggerorder.base.data.RsvIfoOrderUnitParams;
import webbroker3.triggerorder.base.data.RsvIfoOrderUnitRow;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

public class WEB3IfoExecuteEndNotifyUnitServiceImplTest extends TestBaseForMock
{
    /**
     * ログユーティリティ<BR>
     */
    private static WEB3LogUtility log =
         WEB3LogUtility.getInstance(WEB3IfoExecuteEndNotifyUnitServiceImplTest.class);

    private boolean l_blnIsEserveOrderExistFlag = false;
    
    public WEB3IfoExecuteEndNotifyUnitServiceImplTest(String arg0)
    {
        super(arg0);
    }

    protected void setUp() throws Exception
    {
        MOCK_MANAGER.setIsMockUsed(true);
        TradingCalendarDetails tradingCalendarDetails =
            new WEB3GentradeTradingClendarDetailsImpl();
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.gentrade.WEB3GentradeTradingCalendarModelImpl",
                "getTradingCalendarDetails",
                new Class[] {long.class},
                tradingCalendarDetails);
        
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.gentrade.WEB3GentradeTradingCalendarModelImpl",
                "getCurrentBizDate",
                new Class[] {long.class},
                WEB3DateUtility.getDate("20070619","yyyyMMdd"));
        super.setUp();
    }

    protected void tearDown() throws Exception
    {
        super.tearDown();
    }
    
    public void testIsExecuteEndIfoOrderUnitC0001()
    {
        final String STR_METHOD_NAME = " testIsExecuteEndIfoOrderUnitC0001";
        log.entering(TEST_START + STR_METHOD_NAME);
        Method method = null;
        try
        {
            TestDBUtility.deleteAll(IfoOrderUnitRow.TYPE);
            WEB3IfoExecuteEndNotifyUnitServiceImpl l_impl = new WEB3IfoExecuteEndNotifyUnitServiceImpl();
            
            method = WEB3IfoExecuteEndNotifyUnitServiceImpl.class.getDeclaredMethod(
                "isExecuteEndIfoOrderUnit",
                new Class[]{OrderUnit.class, String.class});
            
            method.setAccessible(true);
            
            String l_strOrderExecutionEndType = "1";

            Object[] l_obj = {null, l_strOrderExecutionEndType};
            
            method.invoke(l_impl, l_obj);
            fail();
        }
        catch (InvocationTargetException l_ex)
        {
            log.debug("exception =" + ((WEB3SystemLayerException)l_ex.getTargetException()).getErrorInfo().getErrorMessage());
            log.error(l_ex.getMessage(), l_ex);
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                ((WEB3SystemLayerException)l_ex.getTargetException()).getErrorInfo());
        }
        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
    }

    public void testIsExecuteEndIfoOrderUnitC0002()
    {
        final String STR_METHOD_NAME = " testIsExecuteEndIfoOrderUnitC0002";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        try
        {
            IfoOrderUnitParams l_ifoOrderUnitParams = TestDBUtility.getIfoOrderUnitRow();
            l_ifoOrderUnitParams.setRequestType("0");
            l_ifoOrderUnitParams.setSubmitOrderDelayFlag(BooleanEnum.TRUE);
            
            TestDBUtility.deleteAll(IfoOrderUnitRow.TYPE);
            TestDBUtility.insertWithDel(l_ifoOrderUnitParams);
            
            WEB3IfoExecuteEndNotifyUnitServiceImpl l_impl = new WEB3IfoExecuteEndNotifyUnitServiceImpl();
            
            Method method = WEB3IfoExecuteEndNotifyUnitServiceImpl.class.getDeclaredMethod(
                "isExecuteEndIfoOrderUnit",
                new Class[]{OrderUnit.class, String.class});
            
            method.setAccessible(true);
            
            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
            TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.IFO);
            WEB3OptionOrderManagerImpl l_orderManager =
                (WEB3OptionOrderManagerImpl)l_tradingModule.getOrderManager();
            OrderUnit l_orderUnit = l_orderManager.getOrderUnit(1001);
            String l_strOrderExecutionEndType = "1";

            Object[] l_obj = {l_orderUnit, l_strOrderExecutionEndType};
            
            boolean l_result = ((Boolean)method.invoke(l_impl, l_obj)).booleanValue();
            
            assertTrue(l_result);
            
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
        
    public void testIsExecuteEndIfoOrderUnitC0003()
    {
        final String STR_METHOD_NAME = " testIsExecuteEndIfoOrderUnitC0003";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        try
        {
            IfoOrderUnitParams l_ifoOrderUnitParams = TestDBUtility.getIfoOrderUnitRow();
            l_ifoOrderUnitParams.setRequestType("5");
            l_ifoOrderUnitParams.setSubmitOrderDelayFlag(BooleanEnum.TRUE);
            
            TestDBUtility.deleteAll(IfoOrderUnitRow.TYPE);
            TestDBUtility.insertWithDel(l_ifoOrderUnitParams);
            
            WEB3IfoExecuteEndNotifyUnitServiceImpl l_impl = new WEB3IfoExecuteEndNotifyUnitServiceImpl();
            
            Method method = WEB3IfoExecuteEndNotifyUnitServiceImpl.class.getDeclaredMethod(
                "isExecuteEndIfoOrderUnit",
                new Class[]{OrderUnit.class, String.class});
            
            method.setAccessible(true);
            
            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
            TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.IFO);
            WEB3OptionOrderManagerImpl l_orderManager =
                (WEB3OptionOrderManagerImpl)l_tradingModule.getOrderManager();
            OrderUnit l_orderUnit = l_orderManager.getOrderUnit(1001);
            String l_strOrderExecutionEndType = "1";

            Object[] l_obj = {l_orderUnit, l_strOrderExecutionEndType};
            
            boolean l_result = ((Boolean)method.invoke(l_impl, l_obj)).booleanValue();
            
            assertTrue(l_result);
            
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
        
    public void testIsExecuteEndIfoOrderUnitC0004()
    {
        final String STR_METHOD_NAME = " testIsExecuteEndIfoOrderUnitC0004";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        try
        {
            IfoOrderUnitParams l_ifoOrderUnitParams = TestDBUtility.getIfoOrderUnitRow();
            l_ifoOrderUnitParams.setRequestType("2");
            l_ifoOrderUnitParams.setFirstOrderUnitId(null);
            l_ifoOrderUnitParams.setEveningSessionCarryoverFlag(BooleanEnum.FALSE);
            l_ifoOrderUnitParams.setSessionType(null);
            
            TestDBUtility.deleteAll(IfoOrderUnitRow.TYPE);
            TestDBUtility.insertWithDel(l_ifoOrderUnitParams);
            
            
            ProductParams l_productParams = getProductRow();
            
            TestDBUtility.deleteAll(ProductParams.TYPE);
            TestDBUtility.insertWithDel(l_productParams);
            
            TradedProductParams l_TradedProductParams = getTradedProductRow();
            IfoTradedProductParams l_IfoTradedProductParams = getIfoTradedProductRow();
            IfoProductParams l_ifoProductParams = getIfoProductRow();
            MarketParams l_marketParams = getMarketRow();
            IfoTradedProductUpdqParams l_ifoTradedProductUpdqParams = getIfoTradedProductUpdqRow();
            
            TestDBUtility.deleteAll(TradedProductParams.TYPE);
            TestDBUtility.insertWithDel(l_TradedProductParams);
            TestDBUtility.deleteAll(IfoTradedProductParams.TYPE);
            TestDBUtility.insertWithDel(l_IfoTradedProductParams);
            TestDBUtility.deleteAll(IfoProductParams.TYPE);
            TestDBUtility.insertWithDel(l_ifoProductParams);
            TestDBUtility.deleteAll(MarketParams.TYPE);
            TestDBUtility.insertWithDel(l_marketParams);
            TestDBUtility.deleteAll(IfoTradedProductUpdqParams.TYPE);
            TestDBUtility.insertWithDel(l_ifoTradedProductUpdqParams);
            MOCK_MANAGER.setIsMockUsed(true);
            TradingCalendarDetails tradingCalendarDetails =
                new WEB3GentradeTradingClendarDetailsImpl();
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.gentrade.WEB3GentradeTradingCalendarModelImpl",
                    "getTradingCalendarDetails",
                    new Class[] {long.class},
                    tradingCalendarDetails);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.gentrade.WEB3GentradeTradingCalendarModelImpl",
                    "getCurrentBizDate",
                    new Class[] {long.class},
                    WEB3DateUtility.getDate("20070619","yyyyMMdd"));
            WEB3IfoExecuteEndNotifyUnitServiceImpl l_impl = new WEB3IfoExecuteEndNotifyUnitServiceImpl();
            
            Method method = WEB3IfoExecuteEndNotifyUnitServiceImpl.class.getDeclaredMethod(
                "isExecuteEndIfoOrderUnit",
                new Class[]{OrderUnit.class, String.class});
            
            method.setAccessible(true);
            
            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
            TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.IFO);
            WEB3OptionOrderManagerImpl l_orderManager =
                (WEB3OptionOrderManagerImpl)l_tradingModule.getOrderManager();
            
            OrderUnit l_orderUnit = l_orderManager.getOrderUnit(1001);
            String l_strOrderExecutionEndType = "1";

            Object[] l_obj = {l_orderUnit, l_strOrderExecutionEndType};
            
            boolean l_result = ((Boolean)method.invoke(l_impl, l_obj)).booleanValue();
            
            assertTrue(l_result);
            
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    public void testIsExecuteEndIfoOrderUnitC0005()
    {
        final String STR_METHOD_NAME = " testIsExecuteEndIfoOrderUnitC0005";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        try
        {
            IfoOrderUnitParams l_ifoOrderUnitParams = TestDBUtility.getIfoOrderUnitRow();
            l_ifoOrderUnitParams.setRequestType("2");
            l_ifoOrderUnitParams.setFirstOrderUnitId(null);
            l_ifoOrderUnitParams.setEveningSessionCarryoverFlag(BooleanEnum.TRUE);
            l_ifoOrderUnitParams.setSessionType(null);
            
            TestDBUtility.deleteAll(IfoOrderUnitRow.TYPE);
            TestDBUtility.insertWithDel(l_ifoOrderUnitParams);
            
            ProductParams l_productParams = getProductRow();
            
            TestDBUtility.deleteAll(ProductParams.TYPE);
            TestDBUtility.insertWithDel(l_productParams);
            
            TradedProductParams l_TradedProductParams = getTradedProductRow();
            IfoTradedProductParams l_IfoTradedProductParams = getIfoTradedProductRow();
            IfoProductParams l_ifoProductParams = getIfoProductRow();
            MarketParams l_marketParams = getMarketRow();
            IfoTradedProductUpdqParams l_ifoTradedProductUpdqParams = getIfoTradedProductUpdqRow();
            
            TestDBUtility.deleteAll(TradedProductParams.TYPE);
            TestDBUtility.insertWithDel(l_TradedProductParams);
            TestDBUtility.deleteAll(IfoTradedProductParams.TYPE);
            TestDBUtility.insertWithDel(l_IfoTradedProductParams);
            TestDBUtility.deleteAll(IfoProductParams.TYPE);
            TestDBUtility.insertWithDel(l_ifoProductParams);
            TestDBUtility.deleteAll(MarketParams.TYPE);
            TestDBUtility.insertWithDel(l_marketParams);
            TestDBUtility.deleteAll(IfoTradedProductUpdqParams.TYPE);
            TestDBUtility.insertWithDel(l_ifoTradedProductUpdqParams);
            MOCK_MANAGER.setIsMockUsed(true);
            TradingCalendarDetails tradingCalendarDetails =
                new WEB3GentradeTradingClendarDetailsImpl();
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.gentrade.WEB3GentradeTradingCalendarModelImpl",
                    "getTradingCalendarDetails",
                    new Class[] {long.class},
                    tradingCalendarDetails);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.gentrade.WEB3GentradeTradingCalendarModelImpl",
                    "getCurrentBizDate",
                    new Class[] {long.class},
                    WEB3DateUtility.getDate("20070619","yyyyMMdd"));
            WEB3IfoExecuteEndNotifyUnitServiceImpl l_impl = new WEB3IfoExecuteEndNotifyUnitServiceImpl();
            
            Method method = WEB3IfoExecuteEndNotifyUnitServiceImpl.class.getDeclaredMethod(
                "isExecuteEndIfoOrderUnit",
                new Class[]{OrderUnit.class, String.class});
            
            method.setAccessible(true);
            
            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
            TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.IFO);
            WEB3OptionOrderManagerImpl l_orderManager =
                (WEB3OptionOrderManagerImpl)l_tradingModule.getOrderManager();
            OrderUnit l_orderUnit = l_orderManager.getOrderUnit(1001);
            String l_strOrderExecutionEndType = "1";

            Object[] l_obj = {l_orderUnit, l_strOrderExecutionEndType};
            
            boolean l_result = ((Boolean)method.invoke(l_impl, l_obj)).booleanValue();
            
            assertFalse(l_result);
            
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    public void testIsExecuteEndIfoOrderUnitC0006()
    {
        final String STR_METHOD_NAME = " testIsExecuteEndIfoOrderUnitC0006";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        try
        {
            IfoOrderUnitParams l_ifoOrderUnitParams = TestDBUtility.getIfoOrderUnitRow();
            l_ifoOrderUnitParams.setRequestType("2");
            l_ifoOrderUnitParams.setExpirationDate(GtlUtils.getTradingSystem().getBizDate());
            
            TestDBUtility.deleteAll(IfoOrderUnitRow.TYPE);
            TestDBUtility.insertWithDel(l_ifoOrderUnitParams);
            
            ProductParams l_productParams = getProductRow();
            
            TestDBUtility.deleteAll(ProductParams.TYPE);
            TestDBUtility.insertWithDel(l_productParams);
            
            TradedProductParams l_TradedProductParams = getTradedProductRow();
            IfoTradedProductParams l_IfoTradedProductParams = getIfoTradedProductRow();
            IfoProductParams l_ifoProductParams = getIfoProductRow();
            MarketParams l_marketParams = getMarketRow();
            IfoTradedProductUpdqParams l_ifoTradedProductUpdqParams = getIfoTradedProductUpdqRow();
            
            TestDBUtility.deleteAll(TradedProductParams.TYPE);
            TestDBUtility.insertWithDel(l_TradedProductParams);
            TestDBUtility.deleteAll(IfoTradedProductParams.TYPE);
            TestDBUtility.insertWithDel(l_IfoTradedProductParams);
            TestDBUtility.deleteAll(IfoProductParams.TYPE);
            TestDBUtility.insertWithDel(l_ifoProductParams);
            TestDBUtility.deleteAll(MarketParams.TYPE);
            TestDBUtility.insertWithDel(l_marketParams);
            TestDBUtility.deleteAll(IfoTradedProductUpdqParams.TYPE);
            TestDBUtility.insertWithDel(l_ifoTradedProductUpdqParams);
            MOCK_MANAGER.setIsMockUsed(true);
            TradingCalendarDetails tradingCalendarDetails =
                new WEB3GentradeTradingClendarDetailsImpl();
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.gentrade.WEB3GentradeTradingCalendarModelImpl",
                    "getTradingCalendarDetails",
                    new Class[] {long.class},
                    tradingCalendarDetails);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.gentrade.WEB3GentradeTradingCalendarModelImpl",
                    "getCurrentBizDate",
                    new Class[] {long.class},
                    WEB3DateUtility.getDate("20070619","yyyyMMdd"));
            WEB3IfoExecuteEndNotifyUnitServiceImpl l_impl = new WEB3IfoExecuteEndNotifyUnitServiceImpl();
            
            Method method = WEB3IfoExecuteEndNotifyUnitServiceImpl.class.getDeclaredMethod(
                "isExecuteEndIfoOrderUnit",
                new Class[]{OrderUnit.class, String.class});
            
            method.setAccessible(true);
            
            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
            TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.IFO);
            WEB3OptionOrderManagerImpl l_orderManager =
                (WEB3OptionOrderManagerImpl)l_tradingModule.getOrderManager();
            OrderUnit l_orderUnit = l_orderManager.getOrderUnit(1001);
            String l_strOrderExecutionEndType = "0";

            Object[] l_obj = {l_orderUnit, l_strOrderExecutionEndType};
            
            boolean l_result = ((Boolean)method.invoke(l_impl, l_obj)).booleanValue();
            
            assertTrue(l_result);
            
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    public void testIsExecuteEndIfoOrderUnitC0007()
    {
        final String STR_METHOD_NAME = " testIsExecuteEndIfoOrderUnitC0007";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        try
        {
            IfoOrderUnitParams l_ifoOrderUnitParams = TestDBUtility.getIfoOrderUnitRow();
            l_ifoOrderUnitParams.setRequestType("2");
            l_ifoOrderUnitParams.setExpirationDate(WEB3DateUtility.getDate("20040204", "yyyyMMdd"));
            
            TestDBUtility.deleteAll(IfoOrderUnitRow.TYPE);
            TestDBUtility.insertWithDel(l_ifoOrderUnitParams);
            
            ProductParams l_productParams = getProductRow();
            
            TestDBUtility.deleteAll(ProductParams.TYPE);
            TestDBUtility.insertWithDel(l_productParams);
            
            TradedProductParams l_TradedProductParams = getTradedProductRow();
            IfoTradedProductParams l_IfoTradedProductParams = getIfoTradedProductRow();
            IfoProductParams l_ifoProductParams = getIfoProductRow();
            MarketParams l_marketParams = getMarketRow();
            IfoTradedProductUpdqParams l_ifoTradedProductUpdqParams = getIfoTradedProductUpdqRow();
            
            TestDBUtility.deleteAll(TradedProductParams.TYPE);
            TestDBUtility.insertWithDel(l_TradedProductParams);
            TestDBUtility.deleteAll(IfoTradedProductParams.TYPE);
            TestDBUtility.insertWithDel(l_IfoTradedProductParams);
            TestDBUtility.deleteAll(IfoProductParams.TYPE);
            TestDBUtility.insertWithDel(l_ifoProductParams);
            TestDBUtility.deleteAll(MarketParams.TYPE);
            TestDBUtility.insertWithDel(l_marketParams);
            TestDBUtility.deleteAll(IfoTradedProductUpdqParams.TYPE);
            TestDBUtility.insertWithDel(l_ifoTradedProductUpdqParams);
            MOCK_MANAGER.setIsMockUsed(true);
            TradingCalendarDetails tradingCalendarDetails =
                new WEB3GentradeTradingClendarDetailsImpl();
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.gentrade.WEB3GentradeTradingCalendarModelImpl",
                    "getTradingCalendarDetails",
                    new Class[] {long.class},
                    tradingCalendarDetails);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.gentrade.WEB3GentradeTradingCalendarModelImpl",
                    "getCurrentBizDate",
                    new Class[] {long.class},
                    WEB3DateUtility.getDate("20070619","yyyyMMdd"));
            WEB3IfoExecuteEndNotifyUnitServiceImpl l_impl = new WEB3IfoExecuteEndNotifyUnitServiceImpl();
            
            Method method = WEB3IfoExecuteEndNotifyUnitServiceImpl.class.getDeclaredMethod(
                "isExecuteEndIfoOrderUnit",
                new Class[]{OrderUnit.class, String.class});
            
            method.setAccessible(true);
            
            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
            TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.IFO);
            WEB3OptionOrderManagerImpl l_orderManager =
                (WEB3OptionOrderManagerImpl)l_tradingModule.getOrderManager();
            OrderUnit l_orderUnit = l_orderManager.getOrderUnit(1001);
            String l_strOrderExecutionEndType = "0";

            Object[] l_obj = {l_orderUnit, l_strOrderExecutionEndType};
            
            boolean l_result = ((Boolean)method.invoke(l_impl, l_obj)).booleanValue();
            
            assertFalse(l_result);
            
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
     * 発注日が売買最終日の注文は失効対象 
     *
     */
    public void testIsExecuteEndIfoOrderUnitC0008()
    {
        final String STR_METHOD_NAME = " testIsExecuteEndIfoOrderUnitC0008";
        log.entering(TEST_START + STR_METHOD_NAME);
//        TradingModule l_tradingModuleMock = GtlUtils.getTradingModule(ProductTypeEnum.IFO);
//        TradingModule l_tradingModule = GtlUtils.getTradingModule(ProductTypeEnum.IFO);
//        
//        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
//        l_finApp.uninstallTradingModule("xb-ifo-pdbt");
        

        
        try
        {
            IfoOrderUnitParams l_ifoOrderUnitParams = TestDBUtility.getIfoOrderUnitRow();
            l_ifoOrderUnitParams.setRequestType("5");
            l_ifoOrderUnitParams.setExpirationDate(WEB3DateUtility.getDate("20040204", "yyyyMMdd"));
            
            TestDBUtility.deleteAll(IfoOrderUnitRow.TYPE);
            TestDBUtility.insertWithDel(l_ifoOrderUnitParams);
            
            ProductParams l_productParams = getProductRow();
            
            TestDBUtility.deleteAll(ProductParams.TYPE);
            TestDBUtility.insertWithDel(l_productParams);
            
            TradedProductParams l_TradedProductParams = getTradedProductRow();
            l_TradedProductParams.setProductType(ProductTypeEnum.IFO);
            IfoTradedProductParams l_IfoTradedProductParams = getIfoTradedProductRow();
            l_IfoTradedProductParams.setLastTradingDate(WEB3DateUtility.getDate("20040101","yyyyMMdd")); 
            IfoProductParams l_ifoProductParams = getIfoProductRow();
            MarketParams l_marketParams = getMarketRow();
            IfoTradedProductUpdqParams l_ifoTradedProductUpdqParams = getIfoTradedProductUpdqRow();
            l_ifoTradedProductUpdqParams.setLastTradingDate(WEB3DateUtility.getDate("20040101","yyyyMMdd"));
            
            TestDBUtility.deleteAll(TradedProductParams.TYPE);
            TestDBUtility.insertWithDel(l_TradedProductParams);
            TestDBUtility.deleteAll(IfoTradedProductParams.TYPE);
            TestDBUtility.insertWithDel(l_IfoTradedProductParams);
            TestDBUtility.deleteAll(IfoProductParams.TYPE);
            TestDBUtility.insertWithDel(l_ifoProductParams);
            TestDBUtility.deleteAll(MarketParams.TYPE);
            TestDBUtility.insertWithDel(l_marketParams);
            TestDBUtility.deleteAll(IfoTradedProductUpdqParams.TYPE);
            TestDBUtility.insertWithDel(l_ifoTradedProductUpdqParams);
            
            WEB3IfoExecuteEndNotifyUnitServiceImpl l_impl = new WEB3IfoExecuteEndNotifyUnitServiceImpl();
            
            Method method = WEB3IfoExecuteEndNotifyUnitServiceImpl.class.getDeclaredMethod(
                "isExecuteEndIfoOrderUnit",
                new Class[]{OrderUnit.class, String.class});
            
            method.setAccessible(true);
            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
//            l_finApp.installTradingModule(new WEB3IfoTradingModule());
            TradingModule l_tradingModule = GtlUtils.getTradingModule(ProductTypeEnum.IFO);
            l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.IFO);
            
//            l_tradingModule.overrideOrderManager(new WEB3OptionOrderManagerImpl());
            
            WEB3OptionOrderManagerImpl l_orderManager =
                (WEB3OptionOrderManagerImpl)l_tradingModule.getOrderManager();
            OrderUnit l_orderUnit = l_orderManager.getOrderUnit(1001);
            String l_strOrderExecutionEndType = "0";

            Object[] l_obj = {l_orderUnit, l_strOrderExecutionEndType};
            
            boolean l_result = ((Boolean)method.invoke(l_impl, l_obj)).booleanValue();
            
            assertTrue(l_result);
            
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
//        finally
//        {
//            try
//            {
//            l_finApp.uninstallTradingModule("xb-ifo-pdbt");
//            l_finApp.installTradingModule(new WEB3IfoTradingModuleForMock());
//            l_tradingModuleMock = l_finApp.getTradingModule("xb-ifo-pdbt");
//            l_tradingModuleMock.overrideOrderManager(new WEB3OptionOrderManagerImplForMock());
//            }
//            catch(Exception l_ex)
//            {
//                log.error(l_ex.getMessage(), l_ex);
//                fail();
//            }
//        }
        log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
        log.exiting(TEST_END + STR_METHOD_NAME);
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
     * 先物OP取引銘柄マスタ(ifo_traded_product)
     */
    public static IfoTradedProductParams getIfoTradedProductRow()
    {
        IfoTradedProductParams l_IfoTradedProductParams = new IfoTradedProductParams();
        l_IfoTradedProductParams.setTradedProductId(0L);
        l_IfoTradedProductParams.setInstitutionCode("0D");
        l_IfoTradedProductParams.setMarketId(1002);
        l_IfoTradedProductParams.setProductId(1006169090018L);
        l_IfoTradedProductParams.setUnitSize(10000L);
        l_IfoTradedProductParams.setUnitMargin(0L);
        l_IfoTradedProductParams.setPerOrderMaxUnits(1000L);
        l_IfoTradedProductParams.setOrderCloseTime("");
        l_IfoTradedProductParams.setLastClosingPrice(8D);
        l_IfoTradedProductParams.setStartTradingDate(WEB3DateUtility.getDate("20040708","yyyyMMdd"));
        l_IfoTradedProductParams.setLastTradingDate(WEB3DateUtility.getDate("20040718","yyyyMMdd")); 
        l_IfoTradedProductParams.setTradeStopFlag(BooleanEnum.FALSE);
        l_IfoTradedProductParams.setBuyToOpenStopFlag(BooleanEnum.FALSE);
        l_IfoTradedProductParams.setSellToOpenStopFlag(BooleanEnum.FALSE);
        l_IfoTradedProductParams.setSellToCloseStopFlag(BooleanEnum.FALSE);
        l_IfoTradedProductParams.setBuyToCloseStopFlag(BooleanEnum.FALSE);
        l_IfoTradedProductParams.setListFlag(BooleanEnum.TRUE);
        l_IfoTradedProductParams.setCreatedTimestamp(WEB3DateUtility.getDate("20040130","yyyyMMdd"));
        l_IfoTradedProductParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20040130","yyyyMMdd"));
        return l_IfoTradedProductParams;
    }
    
    /**
     * 取引銘柄マスターRowを作成.<BR>
     */
    public static TradedProductParams getTradedProductRow()
    {
        TradedProductParams l_tradedProductParams = new TradedProductParams();
        
        l_tradedProductParams.setTradedProductId(0L);
        l_tradedProductParams.setInstitutionCode("0D");
        l_tradedProductParams.setProductId(1006169090018L);
        l_tradedProductParams.setMarketId(1002);
        l_tradedProductParams.setMarginRatio(70.000000D);
        l_tradedProductParams.setSuspensionFlag(BooleanEnum.FALSE);
        l_tradedProductParams.setBaseDate(WEB3DateUtility.getDate("20040917","yyyyMMdd"));
        l_tradedProductParams.setDailyDeliveryDate(WEB3DateUtility.getDate("20040924","yyyyMMdd"));
        l_tradedProductParams.setProductType(ProductTypeEnum.FOREIGN_EQUITY);
        l_tradedProductParams.setCollateralFlag(BooleanEnum.FALSE);
        l_tradedProductParams.setCreatedTimestamp(Calendar.getInstance().getTime());
        l_tradedProductParams.setLastUpdatedTimestamp(Calendar.getInstance().getTime());
        
        return l_tradedProductParams;
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
    
    /**
     * 先物OP取引銘柄ﾏｽﾀ（一時ﾃｰﾌﾞﾙ）(ifo_traded_product_updq)
     */
    public static IfoTradedProductUpdqParams getIfoTradedProductUpdqRow()
    {
        Date l_datNextBizDate = null;
        try
        {
            l_datNextBizDate = new WEB3GentradeBizDate(
                    new Timestamp(GtlUtils.getTradingSystem().getBizDate().getTime())).roll(1);
        }
        catch (WEB3SystemLayerException e)
        {
            fail();
        }
        
        IfoTradedProductUpdqParams l_ifoTradedProductUpdqParams = new IfoTradedProductUpdqParams();
        l_ifoTradedProductUpdqParams.setTradedProductId(0L);
        l_ifoTradedProductUpdqParams.setValidForBizDate(WEB3DateUtility.formatDate(l_datNextBizDate,"yyyyMMdd"));
        l_ifoTradedProductUpdqParams.setInstitutionCode("0D");
        l_ifoTradedProductUpdqParams.setMarketId(1002);
        l_ifoTradedProductUpdqParams.setProductId(1006169090018L);
        l_ifoTradedProductUpdqParams.setUnitSize(10000D);
        l_ifoTradedProductUpdqParams.setUnitMargin(0L);
        l_ifoTradedProductUpdqParams.setPerOrderMaxUnits(1000D);
        l_ifoTradedProductUpdqParams.setOrderCloseTime("0");
        l_ifoTradedProductUpdqParams.setLastClosingPrice(28);
        l_ifoTradedProductUpdqParams.setStartTradingDate(WEB3DateUtility.getDate("20040708","yyyyMMdd"));
        l_ifoTradedProductUpdqParams.setLastTradingDate(WEB3DateUtility.getDate("20040716","yyyyMMdd"));
        l_ifoTradedProductUpdqParams.setTradeStopFlag(BooleanEnum.FALSE);
        l_ifoTradedProductUpdqParams.setBuyToOpenStopFlag(BooleanEnum.FALSE);
        l_ifoTradedProductUpdqParams.setSellToOpenStopFlag(BooleanEnum.FALSE);
        l_ifoTradedProductUpdqParams.setSellToCloseStopFlag(BooleanEnum.FALSE);
        l_ifoTradedProductUpdqParams.setBuyToCloseStopFlag(BooleanEnum.FALSE);
        l_ifoTradedProductUpdqParams.setListFlag(BooleanEnum.TRUE);
        l_ifoTradedProductUpdqParams.setListDate(WEB3DateUtility.getDate("20040130","yyyyMMdd"));
        l_ifoTradedProductUpdqParams.setUnlistedDate(WEB3DateUtility.getDate("20040730","yyyyMMdd"));
        l_ifoTradedProductUpdqParams.setExerciseStop(BooleanEnum.FALSE);
        l_ifoTradedProductUpdqParams.setActualDelivaryStop(BooleanEnum.FALSE);
        l_ifoTradedProductUpdqParams.setActualRecieveStop(BooleanEnum.FALSE);
        l_ifoTradedProductUpdqParams.setReserveStop(BooleanEnum.FALSE);
        l_ifoTradedProductUpdqParams.setIndicationPrice(30.8D);
        l_ifoTradedProductUpdqParams.setLastLiquidationPrice(0D);
        l_ifoTradedProductUpdqParams.setTargetSpotPrice(1212D);
        l_ifoTradedProductUpdqParams.setLiquidationPrice(8.13D);
        l_ifoTradedProductUpdqParams.setCreatedTimestamp(WEB3DateUtility.getDate("20040130","yyyyMMdd"));
        l_ifoTradedProductUpdqParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20040130","yyyyMMdd"));
        return l_ifoTradedProductUpdqParams;
    }
    
    /**
     * 引数.先物OP予約注文単位行.発注日の比較結果が同日の場合（*）、trueを返却する。
     *
     */
    public void testIsExecuteEndReserveIfoOrderUnit_C0001()
    {
        final String STR_METHOD_NAME = "testIsExecuteEndReserveIfoOrderUnit_C0001";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {
            this.initData();
            super.MOCK_MANAGER.setIsMockUsed(true);
            
            OrderUnit[] l_orderUnits =  new OrderUnitForTest[1];
            l_orderUnits[0] = new OrderUnitForTest();
            
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.ifo.WEB3FuturesOrderManagerImpl", 
                    "getOrderUnits", new Class[]
                    { long.class },
                    l_orderUnits);
                    
            WEB3IfoExecuteEndNotifyUnitServiceImpl l_service = new WEB3IfoExecuteEndNotifyUnitServiceImpl();
            Method method = WEB3IfoExecuteEndNotifyUnitServiceImpl.class.getDeclaredMethod(
                    "isExecuteEndReserveIfoOrderUnit",
                    new Class[]{RsvIfoOrderUnitRow.class, String.class});
            
            method.setAccessible(true);
            
            RsvIfoOrderUnitParams l_rsvIfoOrderUnitRow = new RsvIfoOrderUnitParams();
            l_rsvIfoOrderUnitRow.setBizDate("20080326");
            l_rsvIfoOrderUnitRow.setMarketId(3303);
            l_rsvIfoOrderUnitRow.setProductId(1006169090018L);
            String l_strOrderExecutionEndType = "1";

            Object[] l_obj = {l_rsvIfoOrderUnitRow, l_strOrderExecutionEndType};
            
            assertTrue(((Boolean)method.invoke(l_service, l_obj)).booleanValue());
        }
        catch (InvocationTargetException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    /**
     * 夕場前出来終了（引数.出来終了区分==”夕場前出来終了”）の場合
     *   日中登録の当日限り注文の場合
     *
     */
    public void testIsExecuteEndReserveIfoOrderUnit_C0002()
    {
        final String STR_METHOD_NAME = "testIsExecuteEndReserveIfoOrderUnit_C0002";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {
            this.initData();
            super.MOCK_MANAGER.setIsMockUsed(true);
            
            OrderUnit[] l_orderUnits =  new OrderUnitForTest[1];
            l_orderUnits[0] = new OrderUnitForTest();
            
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.ifo.WEB3FuturesOrderManagerImpl", 
                    "getOrderUnits", new Class[]
                    { long.class },
                    l_orderUnits);
                    
            WEB3IfoExecuteEndNotifyUnitServiceImpl l_service = new WEB3IfoExecuteEndNotifyUnitServiceImpl();
            Method method = WEB3IfoExecuteEndNotifyUnitServiceImpl.class.getDeclaredMethod(
                    "isExecuteEndReserveIfoOrderUnit",
                    new Class[]{RsvIfoOrderUnitRow.class, String.class});
            
            method.setAccessible(true);
            
            RsvIfoOrderUnitParams l_rsvIfoOrderUnitRow = new RsvIfoOrderUnitParams();
            l_rsvIfoOrderUnitRow.setBizDate("20090326");
            l_rsvIfoOrderUnitRow.setEveningSessionCarryoverFlag(com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum.FALSE);
            l_rsvIfoOrderUnitRow.setMarketId(3303);
            l_rsvIfoOrderUnitRow.setProductId(1006169090018L);
            String l_strOrderExecutionEndType = "1";

            Object[] l_obj = {l_rsvIfoOrderUnitRow, l_strOrderExecutionEndType};
            
            assertTrue(((Boolean)method.invoke(l_service, l_obj)).booleanValue());
        }
        catch (InvocationTargetException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    /**
     * 出来終了（最終）の場合 true
     *
     */
    public void testIsExecuteEndReserveIfoOrderUnit_C0003()
    {
        final String STR_METHOD_NAME = "testIsExecuteEndReserveIfoOrderUnit_C0003";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {
            this.initData();
            super.MOCK_MANAGER.setIsMockUsed(true);
            
            OrderUnit[] l_orderUnits =  new OrderUnitForTest[1];
            l_orderUnits[0] = new OrderUnitForTest();
            
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.ifo.WEB3FuturesOrderManagerImpl", 
                    "getOrderUnits", new Class[]
                    { long.class },
                    l_orderUnits);
            
            
            Calendar l_cal = Calendar.getInstance();
            l_cal.set(2008,03,02);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.tc.gentrade.stdimpls.TradingSystemImpl",
                    "getBizDate",
                    new Class[] {},
                    l_cal.getTime());
                    
            WEB3IfoExecuteEndNotifyUnitServiceImpl l_service = new WEB3IfoExecuteEndNotifyUnitServiceImpl();
            Method method = WEB3IfoExecuteEndNotifyUnitServiceImpl.class.getDeclaredMethod(
                    "isExecuteEndReserveIfoOrderUnit",
                    new Class[]{RsvIfoOrderUnitRow.class, String.class});
            
            method.setAccessible(true);
            
            RsvIfoOrderUnitParams l_rsvIfoOrderUnitRow = new RsvIfoOrderUnitParams();
            l_rsvIfoOrderUnitRow.setBizDate("20090326");
            
            l_rsvIfoOrderUnitRow.setExpirationDate(l_cal.getTime());
            l_rsvIfoOrderUnitRow.setMarketId(3303);
            l_rsvIfoOrderUnitRow.setProductId(1006169090018L);
            String l_strOrderExecutionEndType = "0";

            Object[] l_obj = {l_rsvIfoOrderUnitRow, l_strOrderExecutionEndType};
            
            assertTrue(((Boolean)method.invoke(l_service, l_obj)).booleanValue());
        }
        catch (InvocationTargetException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    /**
     * 返回 false
     *
     */
    public void testIsExecuteEndReserveIfoOrderUnit_C0004()
    {
        final String STR_METHOD_NAME = "testIsExecuteEndReserveIfoOrderUnit_C0004";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {
            this.initData();
            super.MOCK_MANAGER.setIsMockUsed(true);
            
            OrderUnit[] l_orderUnits =  new OrderUnitForTest[1];
            l_orderUnits[0] = new OrderUnitForTest();
            
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.ifo.WEB3FuturesOrderManagerImpl", 
                    "getOrderUnits", new Class[]
                    { long.class },
                    l_orderUnits);
            
            
            Calendar l_cal = Calendar.getInstance();
            l_cal.set(2008,03,02);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.tc.gentrade.stdimpls.TradingSystemImpl",
                    "getBizDate",
                    new Class[] {},
                    l_cal.getTime());
                    
            WEB3IfoExecuteEndNotifyUnitServiceImpl l_service = new WEB3IfoExecuteEndNotifyUnitServiceImpl();
            Method method = WEB3IfoExecuteEndNotifyUnitServiceImpl.class.getDeclaredMethod(
                    "isExecuteEndReserveIfoOrderUnit",
                    new Class[]{RsvIfoOrderUnitRow.class, String.class});
            
            method.setAccessible(true);
            
            RsvIfoOrderUnitParams l_rsvIfoOrderUnitRow = new RsvIfoOrderUnitParams();
            l_rsvIfoOrderUnitRow.setBizDate("20090326");
            l_rsvIfoOrderUnitRow.setMarketId(3303);
            l_rsvIfoOrderUnitRow.setProductId(1006169090018L);
            l_cal.set(2008,0,11);
            l_rsvIfoOrderUnitRow.setExpirationDate(l_cal.getTime());
            
            String l_strOrderExecutionEndType = "0";

            Object[] l_obj = {l_rsvIfoOrderUnitRow, l_strOrderExecutionEndType};
            
            assertFalse(((Boolean)method.invoke(l_service, l_obj)).booleanValue());
        }
        catch (InvocationTargetException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    /**
     * is予約注文確認要 = false
     *
     */
    public void testNotifyExecuteEnd_C0001()
    {
        final String STR_METHOD_NAME = "testNotifyExecuteEnd_C0001";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {
            
            this.initData();
            super.MOCK_MANAGER.setIsMockUsed(true);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.gentrade.WEB3GentradeAccountManager",
                    "lockAccount",
                    new Class[] {String.class,String.class,String.class},
                    null);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.ifo.WEB3FuturesOrderManagerImpl", 
                    "getOrderUnit", 
                    new Class[]{ long.class },
                    new OrderUnitForTest());
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.ifo.service.delegate.stdimpls.WEB3IfoExecutedMailSendServiceImpl", 
                    "sendMailProcess",
                    new Class[]
                    { OrderUnit.class, String.class },
                    null);
                    
            WEB3IfoExecuteEndNotifyUnitServiceImpl l_service = new WEB3IfoExecuteEndNotifyUnitServiceImpl();
            OrderUnit l_orderUnit = new OrderUnitForTest();
            String l_strOrderExecutionEndType = "1";
            l_service.notifyExecuteEnd(l_orderUnit, l_strOrderExecutionEndType);
            assertTrue(true);
        }
        catch(WEB3BaseException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    /**
     * is出来終了注文単位 = true または 出来終了区分 = "出来終了"（最終）&& is連続注文繰越実施 = false
     * 更新【先物OP予約注文単位テーブル】往【先物OP予約注文履歴テーブル】插入數據
     *
     */
    public void testNotifyExecuteEnd_C0002()
    {
        final String STR_METHOD_NAME = "testNotifyExecuteEnd_C0002";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {
            this.l_blnIsEserveOrderExistFlag = true;
            this.initData();
            super.MOCK_MANAGER.setIsMockUsed(true);

            TestDBUtility.deleteAll(RsvIfoOrderActionRow.TYPE);
            
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.gentrade.WEB3GentradeAccountManager",
                    "lockAccount",
                    new Class[] {String.class,String.class,String.class},
                    null);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.ifo.WEB3FuturesOrderManagerImpl", 
                    "getOrderUnit", 
                    new Class[]{ long.class },
                    new OrderUnitForTest());
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.ifo.service.delegate.stdimpls.WEB3IfoExecutedMailSendServiceImpl", 
                    "sendMailProcess",
                    new Class[]
                    { OrderUnit.class, String.class },
                    null);
                    
            WEB3IfoExecuteEndNotifyUnitServiceImpl l_service = new WEB3IfoExecuteEndNotifyUnitServiceImpl();
            OrderUnit l_orderUnit = new OrderUnitForTest();
            String l_strOrderExecutionEndType = "0";
            l_service.notifyExecuteEnd(l_orderUnit, l_strOrderExecutionEndType);
            assertTrue(true);
            
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            
            List l_lisRsvIfoOrderUnitRows =
                l_queryProcessor.doFindAllQuery(
                    RsvIfoOrderUnitRow.TYPE,
                    " parent_order_id = ?",
                    new Object[]{new Long(1001)});
            // setOrderOpenStatus
            assertEquals(OrderOpenStatusEnum.CLOSED,((RsvIfoOrderUnitRow)l_lisRsvIfoOrderUnitRows.get(0)).getOrderOpenStatus());
            
            List l_lisRsvIfoOrderActionRows =
                l_queryProcessor.doFindAllQuery(
                    RsvIfoOrderActionRow.TYPE,
                    "order_id = ?",
                    new Object[]{new Long(1001)});
            
            assertEquals(1001,((RsvIfoOrderActionRow)l_lisRsvIfoOrderActionRows.get(0)).getOrderId(),0);
        }
        catch(WEB3BaseException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    /**
     * is出来終了注文単位 = true または 出来終了区分 = "出来終了"（最終）&& is連続注文繰越実施 = true
     *
     *  is出来終了予約注文単位でない場合
     */
    public void testNotifyExecuteEnd_C0003()
    {
        final String STR_METHOD_NAME = "testNotifyExecuteEnd_C0003";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {
            this.l_blnIsEserveOrderExistFlag = true;
            this.initData();
            TestDBUtility.deleteAll(RsvIfoOrderActionRow.TYPE);
            
            TestDBUtility.deleteAll(RsvIfoOrderUnitRow.TYPE);
            RsvIfoOrderUnitParams l_rsvIfoOrderUnitParams = TestDBUtility.getRsvIfoOrderUnitRow();
            l_rsvIfoOrderUnitParams.setBizDate("20080327");
            l_rsvIfoOrderUnitParams.setFirstOrderId(123);
            l_rsvIfoOrderUnitParams.setEveningSessionCarryoverFlag(BooleanEnum.TRUE);
            l_rsvIfoOrderUnitParams.setMarketId(3303L);
            l_rsvIfoOrderUnitParams.setProductId(1006169090018L);
            TestDBUtility.insertWithDel(l_rsvIfoOrderUnitParams);
            
            
            super.MOCK_MANAGER.setIsMockUsed(true);
            
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.gentrade.WEB3GentradeAccountManager",
                    "lockAccount",
                    new Class[] {String.class,String.class,String.class},
                    null);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.ifo.WEB3FuturesOrderManagerImpl", 
                    "getOrderUnit", 
                    new Class[]{ long.class },
                    new OrderUnitForTest());
            
            OrderUnit[] l_orderUnits =  new OrderUnitForTest[1];
            l_orderUnits[0] = new OrderUnitForTest();
           
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.ifo.WEB3FuturesOrderManagerImpl", 
                    "getOrderUnits", new Class[]
                    { long.class },
                    l_orderUnits);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.ifo.service.delegate.stdimpls.WEB3IfoExecutedMailSendServiceImpl", 
                    "sendMailProcess",
                    new Class[]
                    { OrderUnit.class, String.class },
                    null);
                    
            WEB3IfoExecuteEndNotifyUnitServiceImpl l_service = new WEB3IfoExecuteEndNotifyUnitServiceImpl();
            OrderUnit l_orderUnit = new OrderUnitForTest();
            String l_strOrderExecutionEndType = "1";
            l_service.notifyExecuteEnd(l_orderUnit, l_strOrderExecutionEndType);
            assertTrue(true);
            
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            
            List l_lisRsvIfoOrderUnitRows =
                l_queryProcessor.doFindAllQuery(
                    RsvIfoOrderUnitRow.TYPE,
                    " parent_order_id = ?",
                    new Object[]{new Long(1001)});
            // setOrderOpenStatus
            assertEquals(OrderOpenStatusEnum.OPEN,((RsvIfoOrderUnitRow)l_lisRsvIfoOrderUnitRows.get(0)).getOrderOpenStatus());
            
            List l_lisRsvIfoOrderActionRows =
                l_queryProcessor.doFindAllQuery(
                    RsvIfoOrderActionRow.TYPE,
                    "order_id = ?",
                    new Object[]{new Long(1001)});
            
            assertEquals(0,l_lisRsvIfoOrderActionRows.size());
        }
        catch(WEB3BaseException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    
    
    private void initData()
    {
        final String STR_METHOD_NAME = "initData()";
        log.entering(STR_METHOD_NAME);
        try
        {
            MarketParams l_marketParams = TestDBUtility.getMarketRow();
            l_marketParams.setMarketId(3303L);
            TestDBUtility.insertWithDel(l_marketParams);
            
            
            TestDBUtility.deleteAll(ProductRow.TYPE);
            ProductParams l_productParams = TestDBUtility.getProductRow();

            l_productParams.setProductId(1006169090018L);
            TestDBUtility.insertWithDel(l_productParams);
            
            TestDBUtility.deleteAll(IfoProductRow.TYPE);
            IfoProductParams l_ifoProductParams = TestDBUtility.getIfoProductRow();
            l_ifoProductParams.setProductId(1006169090018L);
            l_ifoProductParams.setTargetMarketId(3303L);
            TestDBUtility.insertWithDel(l_ifoProductParams);
            
            TestDBUtility.deleteAll(TradedProductRow.TYPE);
            TradedProductParams l_tradedProductParams = TestDBUtility.getTradedProductRow();
            l_tradedProductParams.setTradedProductId(1006160060005L);
            l_tradedProductParams.setProductId(1006169090018L);
            l_tradedProductParams.setMarketId(3303L);
            TestDBUtility.insertWithDel(l_tradedProductParams);
            
            TestDBUtility.deleteAll(IfoTradedProductRow.TYPE);
            IfoTradedProductParams l_IfoTradedProductParams = TestDBUtility.getIfoTradedProductRow();
            l_IfoTradedProductParams.setTradedProductId(1006160060005L);
            l_IfoTradedProductParams.setProductId(1006169090018L);
            l_IfoTradedProductParams.setMarketId(3303L);
            l_IfoTradedProductParams.setLastTradingDate(WEB3DateUtility.getDate("20080326","yyyyMMdd"));
            TestDBUtility.insertWithDel(l_IfoTradedProductParams);
            
            TestDBUtility.deleteAll(TradedProductUpdqRow.TYPE);
            TradedProductUpdqParams l_tradedProductUpdqParams = TestDBUtility.getTradedProductUpdqRow();
            l_tradedProductUpdqParams.setTradedProductId(1006160060005L);
            l_tradedProductUpdqParams.setProductId(1006169090018L);
            l_tradedProductUpdqParams.setMarketId(3303L);
            l_tradedProductUpdqParams.setProductType(ProductTypeEnum.IFO);
            TestDBUtility.insertWithDel(l_tradedProductUpdqParams);
            
            TestDBUtility.deleteAll(IfoTradedProductUpdqRow.TYPE);
            IfoTradedProductUpdqParams l_ifoTradedProductUpdqParams = TestDBUtility.getIfoTradedProductUpdqRow();
            l_ifoTradedProductUpdqParams.setTradedProductId(1006160060005L);
            l_ifoTradedProductUpdqParams.setProductId(1006169090018L);
            l_ifoTradedProductUpdqParams.setMarketId(3303L);
            l_ifoTradedProductUpdqParams.setLastTradingDate(WEB3DateUtility.getDate("20080326","yyyyMMdd"));
            TestDBUtility.insertWithDel(l_ifoTradedProductUpdqParams);
            
            SimpleDateFormat l_format = GtlUtils.getThreadSafeSimpleDateFormat("yyyyMMdd");
            Date l_datBizDate = GtlUtils.getTradingSystem().getSystemTimestamp();
            Date l_datpreBizDate = new WEB3GentradeBizDate(
                new Timestamp(l_datBizDate.getTime())).roll(1);
            String l_strCreateDate = l_format.format(l_datpreBizDate);
            
            l_ifoTradedProductUpdqParams.setValidForBizDate(l_strCreateDate);
            TestDBUtility.insertWithDel(l_ifoTradedProductUpdqParams);
            
            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            SubAccountParams l_subAccountParams =TestDBUtility.getSubAccountRow();
            l_subAccountParams.setAccountId(333812512203L);
            l_subAccountParams.setBranchId(33381L);
            TestDBUtility.insertWithDel(l_subAccountParams);
            
            TestDBUtility.deleteAll(IfoOrderUnitRow.TYPE);
            IfoOrderUnitParams l_ifoOrderUnitParams =TestDBUtility.getIfoOrderUnitRow();
            l_ifoOrderUnitParams.setMarketId(3303L);
            l_ifoOrderUnitParams.setProductId(1006169090018L);
            TestDBUtility.insertWithDel(l_ifoOrderUnitParams);
            
            TestDBUtility.deleteAll(InstitutionRow.TYPE);
            TestDBUtility.insertWithDel(TestDBUtility.getInstitutionRow());
            
            TestDBUtility.deleteAll(BranchRow.TYPE);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setBranchId(33381);
            TestDBUtility.insertWithDel(l_branchParams);
            
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(333812512203L);
            TestDBUtility.insertWithDel(l_mainAccountParams);
            
            TestDBUtility.deleteAll(RsvIfoOrderUnitRow.TYPE);
            RsvIfoOrderUnitParams l_rsvIfoOrderUnitParams = new RsvIfoOrderUnitParams();
            l_rsvIfoOrderUnitParams.setOrderUnitId(1001);
            l_rsvIfoOrderUnitParams.setAccountId(333812512203L);
            l_rsvIfoOrderUnitParams.setSubAccountId(33381251220301L);
            l_rsvIfoOrderUnitParams.setBranchId(33381L);
            l_rsvIfoOrderUnitParams.setOrderId(1001);
            l_rsvIfoOrderUnitParams.setMarketId(3303);
            l_rsvIfoOrderUnitParams.setOrderType(OrderTypeEnum.IDX_FUTURES_BUY_TO_OPEN);
            l_rsvIfoOrderUnitParams.setOrderCateg(OrderCategEnum.IDX_FUTURES_OPEN);
            l_rsvIfoOrderUnitParams.setLastOrderActionSerialNo(1005);
            l_rsvIfoOrderUnitParams.setProductType(ProductTypeEnum.IFO);
            l_rsvIfoOrderUnitParams.setQuantity(60000);
            l_rsvIfoOrderUnitParams.setOrderStatus(OrderStatusEnum.ACCEPTED);
            l_rsvIfoOrderUnitParams.setOrderOpenStatus(OrderOpenStatusEnum.OPEN);
            l_rsvIfoOrderUnitParams.setExpirationStatus(OrderExpirationStatusEnum.OPEN);
            l_rsvIfoOrderUnitParams.setTaxType(TaxTypeEnum.NORMAL);
            l_rsvIfoOrderUnitParams.setBizDate("20080326");
            l_rsvIfoOrderUnitParams.setProductId(1006169090018L);
            l_rsvIfoOrderUnitParams.setParentOrderId(1001);
            l_rsvIfoOrderUnitParams.setSerialNoInParent(12345678);
            l_rsvIfoOrderUnitParams.setCreatedTimestamp(Calendar.getInstance().getTime());
            l_rsvIfoOrderUnitParams.setLastUpdatedTimestamp(Calendar.getInstance().getTime());
            TestDBUtility.insertWithDel(l_rsvIfoOrderUnitParams);
        }
        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    private class OrderUnitForTest implements IfoOrderUnit
    {

        public long getOrderUnitId()
        {
            // TODO Auto-generated method stub
            return 1001;
        }

        public long getOrderId()
        {
            // TODO Auto-generated method stub
            return 1001;
        }

        public long getAccountId()
        {
            // TODO Auto-generated method stub
            return 333812512203L;
        }

        public long getSubAccountId()
        {
            // TODO Auto-generated method stub
            return 33381251220301L;
        }

        public long getBranchId()
        {
            // TODO Auto-generated method stub
            return 33381;
        }

        public long getTraderId()
        {
            // TODO Auto-generated method stub
            return 0;
        }

        public OrderAction[] getOrderActions()
        {
            // TODO Auto-generated method stub
            return null;
        }

        public Timestamp getReceivedTimestamp()
        {
            // TODO Auto-generated method stub
            return null;
        }

        public Timestamp getExpirationTimestamp()
        {
            // TODO Auto-generated method stub
            return null;
        }

        public Product getProduct()
        {
            // TODO Auto-generated method stub
            return null;
        }

        public Order getOrder()
        {
            // TODO Auto-generated method stub
            return null;
        }

        public OrderExecution[] getExecutions()
        {
            // TODO Auto-generated method stub
            return null;
        }

        public OrderOpenStatusEnum getOrderOpenStatus()
        {
            // TODO Auto-generated method stub
            return null;
        }

        public OrderTypeEnum getOrderType()
        {
            // TODO Auto-generated method stub
            return null;
        }

        public OrderCategEnum getOrderCateg()
        {
            // TODO Auto-generated method stub
            return null;
        }

        public TaxTypeEnum getTaxType()
        {
            // TODO Auto-generated method stub
            return null;
        }

        public SideEnum getSide()
        {
            // TODO Auto-generated method stub
            return null;
        }

        public OrderStatusEnum getOrderStatus()
        {
            // TODO Auto-generated method stub
            return null;
        }

        public OrderExpirationStatusEnum getExpirationStatus()
        {
            // TODO Auto-generated method stub
            return null;
        }

        public boolean isExpired()
        {
            // TODO Auto-generated method stub
            return false;
        }

        public boolean isFullyExecuted()
        {
            // TODO Auto-generated method stub
            return false;
        }

        public boolean isPartiallyExecuted()
        {
            // TODO Auto-generated method stub
            return false;
        }

        public boolean isUnexecuted()
        {
            // TODO Auto-generated method stub
            return false;
        }

        public double getConfirmedPrice()
        {
            // TODO Auto-generated method stub
            return 0;
        }

        public boolean isConfirmedPriceMarketOrder()
        {
            // TODO Auto-generated method stub
            return false;
        }

        public double getConfirmedQuantity()
        {
            // TODO Auto-generated method stub
            return 0;
        }

        public double getQuantity()
        {
            // TODO Auto-generated method stub
            return 0;
        }

        public Date getDeliveryDate()
        {
            // TODO Auto-generated method stub
            return null;
        }

        public double getExecutedAmount()
        {
            // TODO Auto-generated method stub
            return 0;
        }

        public double getExecutedQuantity()
        {
            // TODO Auto-generated method stub
            return 0;
        }

        public double getLimitPrice()
        {
            // TODO Auto-generated method stub
            return 0;
        }

        public boolean isMarketOrder()
        {
            // TODO Auto-generated method stub
            return false;
        }

        public TradedProduct getTradedProduct()
        {
            // TODO Auto-generated method stub
            WEB3IfoTradedProductImplForTest l_ifoTradedProduct = null;
            try
            {
                l_ifoTradedProduct = new WEB3IfoTradedProductImplForTest(1006160060005L);
            }
            catch (Exception l_ex)
            {
                fail();
            }
            return l_ifoTradedProduct;
        }

        public Object getDataSourceObject()
        {
            // TODO Auto-generated method stub
            IfoOrderUnitParams l_ifoOrderUnitParams = TestDBUtility.getIfoOrderUnitRow();
            
            //口座ＩＤ]
            l_ifoOrderUnitParams.setAccountId(333812512203L);
            //補助口座ＩＤ
            l_ifoOrderUnitParams.setSubAccountId(33381251220301L);
            if(l_blnIsEserveOrderExistFlag)
            {
                l_ifoOrderUnitParams.setReserveOrderExistFlag("1");
            }
            
            l_ifoOrderUnitParams.setMarketId(3303);
            l_ifoOrderUnitParams.setProductId(1006169090018L);
            return l_ifoOrderUnitParams;
        }

        public ProductTypeEnum getProductType()
        {
            // TODO Auto-generated method stub
            return null;
        }

        public boolean isFuturesOrder()
        {
            // TODO Auto-generated method stub
            return false;
        }

        public boolean isOptionsOrder()
        {
            // TODO Auto-generated method stub
            return false;
        }

        public boolean isCallOptionsOrder()
        {
            // TODO Auto-generated method stub
            return false;
        }

        public boolean isPutOptionsOrder()
        {
            // TODO Auto-generated method stub
            return false;
        }

        public boolean isOpenContractOrder()
        {
            // TODO Auto-generated method stub
            return false;
        }

        public boolean isSettleContractOrder()
        {
            // TODO Auto-generated method stub
            return false;
        }

        public IfoOrderExecutionConditionType getExecutionConditionType()
        {
            // TODO Auto-generated method stub
            return null;
        }
        
    }
    
    private class WEB3IfoTradedProductImplForTest extends WEB3IfoTradedProductImpl
    {

        public WEB3IfoTradedProductImplForTest(long l_lngTradedProductID) throws DataQueryException, DataNetworkException, DataFindException
        {
            super(l_lngTradedProductID);
            // TODO Auto-generated constructor stub
        }

        public long getTradedProductId()
        {
            // TODO Auto-generated method stub
            return 0;
        }

        public Product getProduct()
        {
            // TODO Auto-generated method stub
            return null;
        }

        public Market getMarket()
        {
            // TODO Auto-generated method stub
            return null;
        }

        public boolean isTradingSuspended()
        {
            // TODO Auto-generated method stub
            return false;
        }

        public double getMarginRatio()
        {
            // TODO Auto-generated method stub
            return 0;
        }

        public Date getBaseDate()
        {
            // TODO Auto-generated method stub
            return null;
        }

        public Date getDailyDeliveryDate()
        {
            // TODO Auto-generated method stub
            return null;
        }

        public boolean isCollateralizable()
        {
            // TODO Auto-generated method stub
            return false;
        }

        public Institution getInstitution()
        {
            // TODO Auto-generated method stub
            return null;
        }

        public Object getDataSourceObject()
        {
            // TODO Auto-generated method stub
            return null;
        }
        
        public Date getLastTradingDate()
        {
            Calendar l_ca = Calendar.getInstance();
            l_ca.set(2008,02,26);
            return l_ca.getTime();
        }
        
    }
}
@
