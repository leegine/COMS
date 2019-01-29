head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.01.54.56;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3FeqOrderAcceptExecutionNotifyUnitTransactionCallbackTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright           : (株)大和総研 証券ソリューションシステム第二部
File Name           : 
Author Name         : Daiwa Institute of Research
Revision History    : 2007/08/16 韓斌 (中訊) 
*/


package webbroker3.feq;

import java.lang.reflect.Method;
import java.util.Date;
import java.util.List;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataCallbackException;
import com.fitechlabs.xtrade.kernel.data.DataException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.kernel.util.ThreadLocalSystemAttributesRegistry;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderStatusEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.BranchParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.BranchRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.InstitutionParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MarketParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MarketRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.ProductParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.ProductRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.SubAccountParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.SubAccountRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.TradedProductParams;
import com.fitechlabs.xtrade.plugin.tc.xbfeq.data.FeqFinTransactionParams;
import com.fitechlabs.xtrade.plugin.tc.xbfeq.data.FeqOrderExecutionParams;
import com.fitechlabs.xtrade.plugin.tc.xbfeq.data.FeqOrderExecutionRow;
import com.fitechlabs.xtrade.plugin.tc.xbfeq.data.FeqOrderParams;
import com.fitechlabs.xtrade.plugin.tc.xbfeq.data.FeqOrderUnitParams;
import com.fitechlabs.xtrade.plugin.tc.xbfeq.data.FeqOrderUnitRow;
import com.fitechlabs.xtrade.plugin.tc.xbfeq.data.FeqProductParams;
import com.fitechlabs.xtrade.plugin.tc.xbfeq.data.FeqTradedProductParams;

import test.util.TestDBUtility;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3OrderAccProductDef;
import webbroker3.common.define.WEB3ProductCodeDef;
import webbroker3.common.define.WEB3TradingTimeTypeDef;
import webbroker3.feq.data.HostFeqOrderExecNotifyParams;
import webbroker3.feq.message.WEB3FeqOrderAcceptCancelUnit;
import webbroker3.feq.service.delegate.WEB3FeqExecutionNotifyUnitService;
import webbroker3.feq.service.delegate.stdimpls.WEB3FeqExecutionNotifyUnitServiceImpl;
import webbroker3.feq.service.delegate.stdimpls.WEB3FeqExecutionNotifyUnitServiceImplForMock;
import webbroker3.gentrade.WEB3GentradeMarket;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.gentrade.WEB3GentradeTradingClendarContext;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagementForMock;
import webbroker3.gentrade.data.EquityCommAccountCondMstParams;
import webbroker3.gentrade.data.GenCurrencyParams;
import webbroker3.gentrade.data.InstBranchProductParams;
import webbroker3.gentrade.data.TradingTimeParams;
import webbroker3.mock.TestBaseForMock;
import webbroker3.slebase.data.SleRcvdQParams;
import webbroker3.slebase.data.SleRcvdQRow;
import webbroker3.slebase.enums.SleRcvdqProcStatusEnum;
import webbroker3.slebase.enums.SleSendqOpTypeEnum;
import webbroker3.util.WEB3LogUtility;

public class WEB3FeqOrderAcceptExecutionNotifyUnitTransactionCallbackTest extends TestBaseForMock
{
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3FeqOrderAcceptExecutionNotifyUnitTransactionCallbackTest.class);

    public WEB3FeqOrderAcceptExecutionNotifyUnitTransactionCallbackTest(String arg0)
    {
        super(arg0);
        
    }

    protected void setUp() throws Exception
    {
        super.setUp();

        
//        Services.overrideService(
//                WEB3FeqExecutionNotifyUnitService.class,
//                new WEB3FeqExecutionNotifyUnitServiceImpl()); 
        
       
    }

    protected void tearDown() throws Exception
    {
        super.tearDown();
    }

    public void testProcess_Case01()
    {
        final String STR_METHOD_NAME = " testProcess_Case01()";
        log.entering(STR_METHOD_NAME);

        try
        {
            TestDBUtility.deleteAll(TradingTimeParams.TYPE);
            TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);
            WEB3GentradeTradingTimeManagementForMock.setClendarContext(null);

            WEB3GentradeTradingClendarContext l_clendarContext = new WEB3GentradeTradingClendarContext();
            l_clendarContext.setInstitutionCode("0D");
            l_clendarContext.setBranchCode("381");
            l_clendarContext.setProductCode("0");
            l_clendarContext.setMarketCode("N1");
            l_clendarContext.setTradingTimeType("10");
            l_clendarContext.setBizDateType("1");
            ThreadLocalSystemAttributesRegistry.setAttribute("web3.tradingcalendarcontext", l_clendarContext);
            
            WEB3GentradeTradingTimeManagementForMock.setClendarContext(l_clendarContext);
            WEB3GentradeTradingTimeManagementForMock.mockSetTimestamp();
            
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                  "webbroker3.tradingpower.WEB3TPTradingPowerServiceImpl",
                  "reCalcTradingPower",
                  new Class[]{ WEB3GentradeSubAccount.class},
                  null);
            
            //FeqOrderUnitParams
            TestDBUtility.deleteAll(FeqOrderUnitRow.TYPE);
            FeqOrderUnitParams l_params = TestDBUtility.getFeqOrderUnitRow();
            l_params.setOrderUnitId(1234L);
            l_params.setExecutionSeqNo(111);
            l_params.setAccountId(333812512246L);
            l_params.setOrderStatus(OrderStatusEnum.ORDERING);
            l_params.setExecEndTimestamp(null);
            l_params.setLimitPrice(200D);
            l_params.setQuantity(100D);
            l_params.setTemporaryExecutionFlag("2");
            TestDBUtility.insertWithDel(l_params);
            
            //BranchRow
            TestDBUtility.deleteAll(BranchRow.TYPE);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setBranchId(l_params.getBranchId());
            TestDBUtility.insertWithDel(l_branchParams);
            
            TestDBUtility.deleteAll(MarketParams.TYPE);
            MarketParams l_MarketParams = TestDBUtility.getMarketRow();
            l_MarketParams.setMarketCode("N1");
            TestDBUtility.insertWithDel(l_MarketParams);
            
            //ProductRow
            TestDBUtility.deleteAll(ProductRow.TYPE);
            ProductParams l_productParams = TestDBUtility.getProductRow();
            l_productParams.setProductId(l_params.getProductId());
            TestDBUtility.insertWithDel(l_productParams);
            
            //MainAccountRow
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(l_params.getAccountId());
            l_mainAccountParams.setBranchId(l_params.getBranchId());
            TestDBUtility.insertWithDel(l_mainAccountParams);
            
            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            SubAccountParams l_SubAccountParams = TestDBUtility.getSubAccountRow();
            l_SubAccountParams.setAccountId(l_params.getAccountId());
            l_SubAccountParams.setBranchId(l_params.getBranchId());
            l_SubAccountParams.setSubAccountId(l_params.getSubAccountId());
            TestDBUtility.insertWithDel(l_SubAccountParams);
            
            WEB3FeqOrderManager l_feqOrderManager = new WEB3FeqOrderManager();
            WEB3FeqOrderUnit l_orderUnit = (WEB3FeqOrderUnit)l_feqOrderManager.getOrderUnit(1234L);

            //SleRcvdQParams
            TestDBUtility.deleteAll(SleRcvdQParams.TYPE);
            SleRcvdQParams l_cvdQPatarams = new SleRcvdQParams();
            l_cvdQPatarams.setRouteDiv("0");
            l_cvdQPatarams.setFxRate(123D);
            l_cvdQPatarams.setExecQty(123D);
            l_cvdQPatarams.setExecPrice(123D);
            l_cvdQPatarams.setRepliesIndex(112L);
            l_cvdQPatarams.setRejectCode("S_001");
            l_cvdQPatarams.setAcceptDiv("01");
            l_cvdQPatarams.setXblocksProductType(ProductTypeEnum.OTHER);  
            l_cvdQPatarams.setOpType(SleSendqOpTypeEnum.CANCEL_ORDER);
            l_cvdQPatarams.setAccountId(333812512246L);
            l_cvdQPatarams.setStatus(SleRcvdqProcStatusEnum.NO_DEFINE_2);
            l_cvdQPatarams.setCreatedTimestamp(GtlUtils.getSystemTimestamp());
            l_cvdQPatarams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
            l_cvdQPatarams.setPrice(200D);
            l_cvdQPatarams.setQuantity(100D);
            TestDBUtility.insertWithDel(l_cvdQPatarams);

            //GenCurrencyParams
            TestDBUtility.deleteAll(GenCurrencyParams.TYPE);
            GenCurrencyParams l_genCurrencyParams =
                TestDBUtility.getGenCurrencyRow();
            l_genCurrencyParams.setInstitutionCode("0D");
            l_genCurrencyParams.setCurrentBuyExecRate(12.23);
            l_genCurrencyParams.setCurrentSellExecRate(10.33);
            TestDBUtility.insertWithDel(l_genCurrencyParams);

            //FeqProductParams
            TestDBUtility.deleteAll(FeqProductParams.TYPE);
            FeqProductParams l_feqProductParams =
                TestDBUtility.getFeqProductRow();
            l_feqProductParams.setProductId(l_params.getProductId());
            l_feqProductParams.setCurrencyCode(l_genCurrencyParams.getCurrencyCode());
            TestDBUtility.insertWithDel(l_feqProductParams);
            
            //InstitutionParams
            TestDBUtility.deleteAll(InstitutionParams.TYPE);
            InstitutionParams l_institutionParams =
                TestDBUtility.getInstitutionRow();
            l_institutionParams.setInstitutionCode("0D");
            TestDBUtility.insertWithDel(l_institutionParams);
            
            
            WEB3FeqOrderAcceptCancelUnit l_cancelUnit = new WEB3FeqOrderAcceptCancelUnit();
            l_cancelUnit.aftChangeAcceptDiv = "01";

            WEB3FeqOrderAcceptExecutionNotifyUnitTransactionCallback l_callBack =
                new WEB3FeqOrderAcceptExecutionNotifyUnitTransactionCallback(l_orderUnit, l_cvdQPatarams);

            l_callBack.process();

            QueryProcessor l_queryProcesser = Processors.getDefaultProcessor();
            List list = l_queryProcesser.doFindAllQuery(SleRcvdQParams.TYPE);
            assertEquals(1, list.size());
            SleRcvdQRow l_SleRcvdQRow = (SleRcvdQRow)list.get(0);
            assertEquals(SleRcvdqProcStatusEnum.EXEC_PROCESSING, l_SleRcvdQRow.getStatus());
            
            List list2 = l_queryProcesser.doFindAllQuery(FeqOrderUnitParams.TYPE);
            assertEquals(1, list2.size());
            FeqOrderUnitRow l_feqOrderUnitRow = (FeqOrderUnitRow)list2.get(0);
            assertEquals("1", l_feqOrderUnitRow.getTemporaryExecutionFlag());
            
        }
        catch(WEB3BaseException l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            log.error(l_ex.getMessage(), l_ex);
            assertEquals(l_ex.getErrorInfo(), WEB3ErrorCatalog.SYSTEM_ERROR_80003);
        }
        catch(Exception l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
    }
    
    public void testProcess_Case02()
    {
        final String STR_METHOD_NAME = " testProcess_Case02()";
        log.entering(STR_METHOD_NAME);

        try
        {
            TestDBUtility.deleteAll(TradingTimeParams.TYPE);
            TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);
            WEB3GentradeTradingTimeManagementForMock.setClendarContext(null);

            WEB3GentradeTradingClendarContext l_clendarContext = new WEB3GentradeTradingClendarContext();
            l_clendarContext.setInstitutionCode("0D");
            l_clendarContext.setBranchCode("381");
            l_clendarContext.setProductCode("0");
            l_clendarContext.setMarketCode("N1");
            l_clendarContext.setTradingTimeType("10");
            l_clendarContext.setBizDateType("1");
            ThreadLocalSystemAttributesRegistry.setAttribute("web3.tradingcalendarcontext", l_clendarContext);
            
            WEB3GentradeTradingTimeManagementForMock.setClendarContext(l_clendarContext);
            WEB3GentradeTradingTimeManagementForMock.mockSetTimestamp();
            
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                  "webbroker3.tradingpower.WEB3TPTradingPowerServiceImpl",
                  "reCalcTradingPower",
                  new Class[]{ WEB3GentradeSubAccount.class},
                  null);
            TestDBUtility.deleteAll(MarketParams.TYPE);
            MarketParams l_MarketParams = TestDBUtility.getMarketRow();
            l_MarketParams.setMarketCode("N1");
            TestDBUtility.insertWithDel(l_MarketParams);
            
            //FeqOrderUnitParams
            TestDBUtility.deleteAll(FeqOrderUnitRow.TYPE);
            FeqOrderUnitParams l_params = TestDBUtility.getFeqOrderUnitRow();
            l_params.setOrderUnitId(1234L);
            l_params.setExecutionSeqNo(111);
            l_params.setAccountId(333812512246L);
            l_params.setOrderStatus(OrderStatusEnum.ORDERING);
            l_params.setExecEndTimestamp(null);
            l_params.setLimitPrice(200D);
            l_params.setQuantity(100D);
            l_params.setTemporaryExecutionFlag("2");
            TestDBUtility.insertWithDel(l_params);
            
            //BranchRow
            TestDBUtility.deleteAll(BranchRow.TYPE);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setBranchId(l_params.getBranchId());
            TestDBUtility.insertWithDel(l_branchParams);
            
            //ProductRow
            TestDBUtility.deleteAll(ProductRow.TYPE);
            ProductParams l_productParams = TestDBUtility.getProductRow();
            l_productParams.setProductId(l_params.getProductId());
            TestDBUtility.insertWithDel(l_productParams);
            
            //MainAccountRow
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(l_params.getAccountId());
            l_mainAccountParams.setBranchId(l_params.getBranchId());
            TestDBUtility.insertWithDel(l_mainAccountParams);
            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            SubAccountParams l_SubAccountParams = TestDBUtility.getSubAccountRow();
            l_SubAccountParams.setAccountId(l_params.getAccountId());
            l_SubAccountParams.setBranchId(l_params.getBranchId());
            l_SubAccountParams.setSubAccountId(l_params.getSubAccountId());
            TestDBUtility.insertWithDel(l_SubAccountParams);
            WEB3FeqOrderManager l_feqOrderManager = new WEB3FeqOrderManager();
            WEB3FeqOrderUnit l_orderUnit = (WEB3FeqOrderUnit)l_feqOrderManager.getOrderUnit(1234L);

            //SleRcvdQParams
            TestDBUtility.deleteAll(SleRcvdQParams.TYPE);
            SleRcvdQParams l_cvdQPatarams = new SleRcvdQParams();
            l_cvdQPatarams.setRouteDiv("1");
            l_cvdQPatarams.setFxRate(123D);
            l_cvdQPatarams.setExecQty(123D);
            l_cvdQPatarams.setExecPrice(123D);
            l_cvdQPatarams.setRepliesIndex(112L);
            l_cvdQPatarams.setRejectCode("S_001");
            l_cvdQPatarams.setAcceptDiv("01");
            l_cvdQPatarams.setXblocksProductType(ProductTypeEnum.OTHER);  
            l_cvdQPatarams.setOpType(SleSendqOpTypeEnum.CANCEL_ORDER);
            l_cvdQPatarams.setAccountId(333812512246L);
            l_cvdQPatarams.setStatus(SleRcvdqProcStatusEnum.NO_DEFINE_2);
            l_cvdQPatarams.setCreatedTimestamp(GtlUtils.getSystemTimestamp());
            l_cvdQPatarams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
            l_cvdQPatarams.setPrice(200D);
            l_cvdQPatarams.setQuantity(100D);
            TestDBUtility.insertWithDel(l_cvdQPatarams);

            //GenCurrencyParams
            TestDBUtility.deleteAll(GenCurrencyParams.TYPE);
            GenCurrencyParams l_genCurrencyParams =
                TestDBUtility.getGenCurrencyRow();
            l_genCurrencyParams.setInstitutionCode("0D");
            l_genCurrencyParams.setCurrentBuyExecRate(12.23);
            l_genCurrencyParams.setCurrentSellExecRate(10.33);
            TestDBUtility.insertWithDel(l_genCurrencyParams);

            //FeqProductParams
            TestDBUtility.deleteAll(FeqProductParams.TYPE);
            FeqProductParams l_feqProductParams =
                TestDBUtility.getFeqProductRow();
            l_feqProductParams.setProductId(l_params.getProductId());
            l_feqProductParams.setCurrencyCode(l_genCurrencyParams.getCurrencyCode());
            TestDBUtility.insertWithDel(l_feqProductParams);
            
            //InstitutionParams
            TestDBUtility.deleteAll(InstitutionParams.TYPE);
            InstitutionParams l_institutionParams =
                TestDBUtility.getInstitutionRow();
            l_institutionParams.setInstitutionCode("0D");
            TestDBUtility.insertWithDel(l_institutionParams);
            
            
            WEB3FeqOrderAcceptCancelUnit l_cancelUnit = new WEB3FeqOrderAcceptCancelUnit();
            l_cancelUnit.aftChangeAcceptDiv = "01";

            WEB3FeqOrderAcceptExecutionNotifyUnitTransactionCallback l_callBack =
                new WEB3FeqOrderAcceptExecutionNotifyUnitTransactionCallback(l_orderUnit, l_cvdQPatarams);

            l_callBack.process();

            QueryProcessor l_queryProcesser = Processors.getDefaultProcessor();
            List list = l_queryProcesser.doFindAllQuery(SleRcvdQParams.TYPE);
            assertEquals(1, list.size());
            SleRcvdQRow l_SleRcvdQRow = (SleRcvdQRow)list.get(0);
            assertEquals(SleRcvdqProcStatusEnum.EXEC_PROCESSING, l_SleRcvdQRow.getStatus());
            
            List list2 = l_queryProcesser.doFindAllQuery(FeqOrderUnitParams.TYPE);
            assertEquals(1, list2.size());
            FeqOrderUnitRow l_feqOrderUnitRow = (FeqOrderUnitRow)list2.get(0);
            assertEquals("1", l_feqOrderUnitRow.getTemporaryExecutionFlag());
            
        }
        catch(WEB3BaseException l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            log.error(l_ex.getMessage(), l_ex);
            assertEquals(l_ex.getErrorInfo(), WEB3ErrorCatalog.SYSTEM_ERROR_80003);
        }
        catch(Exception l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
    }
    
    public void testProcess_Case03()
    {
        final String STR_METHOD_NAME = " testProcess_Case03()";
        log.entering(STR_METHOD_NAME);

        try
        {
            TestDBUtility.deleteAll(TradingTimeParams.TYPE);
            TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);
            WEB3GentradeTradingTimeManagementForMock.setClendarContext(null);

            WEB3GentradeTradingClendarContext l_clendarContext = new WEB3GentradeTradingClendarContext();
            l_clendarContext.setInstitutionCode("0D");
            l_clendarContext.setBranchCode("381");
            l_clendarContext.setProductCode("0");
            l_clendarContext.setMarketCode("N1");
            l_clendarContext.setTradingTimeType("10");
            l_clendarContext.setBizDateType("1");
            ThreadLocalSystemAttributesRegistry.setAttribute("web3.tradingcalendarcontext", l_clendarContext);
            
            WEB3GentradeTradingTimeManagementForMock.setClendarContext(l_clendarContext);
            WEB3GentradeTradingTimeManagementForMock.mockSetTimestamp();
            
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                  "webbroker3.tradingpower.WEB3TPTradingPowerServiceImpl",
                  "reCalcTradingPower",
                  new Class[]{ WEB3GentradeSubAccount.class},
                  null);
            TestDBUtility.deleteAll(MarketParams.TYPE);
            MarketParams l_MarketParams = TestDBUtility.getMarketRow();
            l_MarketParams.setMarketCode("N1");
            TestDBUtility.insertWithDel(l_MarketParams);
            
            //FeqOrderUnitParams
            TestDBUtility.deleteAll(FeqOrderUnitRow.TYPE);
            FeqOrderUnitParams l_params = TestDBUtility.getFeqOrderUnitRow();
            l_params.setOrderUnitId(1234L);
            l_params.setExecutionSeqNo(111);
            l_params.setAccountId(333812512246L);
            l_params.setOrderStatus(OrderStatusEnum.ORDERING);
            l_params.setExecEndTimestamp(null);
            l_params.setLimitPrice(200D);
            l_params.setQuantity(100D);
            l_params.setTemporaryExecutionFlag("2");
            TestDBUtility.insertWithDel(l_params);
            
            //BranchRow
            TestDBUtility.deleteAll(BranchRow.TYPE);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setBranchId(l_params.getBranchId());
            TestDBUtility.insertWithDel(l_branchParams);
            
            //ProductRow
            TestDBUtility.deleteAll(ProductRow.TYPE);
            ProductParams l_productParams = TestDBUtility.getProductRow();
            l_productParams.setProductId(l_params.getProductId());
            TestDBUtility.insertWithDel(l_productParams);
            
            //MainAccountRow
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(l_params.getAccountId());
            l_mainAccountParams.setBranchId(l_params.getBranchId());
            TestDBUtility.insertWithDel(l_mainAccountParams);
            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            SubAccountParams l_SubAccountParams = TestDBUtility.getSubAccountRow();
            l_SubAccountParams.setAccountId(l_params.getAccountId());
            l_SubAccountParams.setBranchId(l_params.getBranchId());
            l_SubAccountParams.setSubAccountId(l_params.getSubAccountId());
            TestDBUtility.insertWithDel(l_SubAccountParams);
            WEB3FeqOrderManager l_feqOrderManager = new WEB3FeqOrderManager();
            WEB3FeqOrderUnit l_orderUnit = (WEB3FeqOrderUnit)l_feqOrderManager.getOrderUnit(1234L);

            //SleRcvdQParams
            TestDBUtility.deleteAll(SleRcvdQParams.TYPE);
            SleRcvdQParams l_cvdQPatarams = new SleRcvdQParams();
            l_cvdQPatarams.setRouteDiv("2");
            l_cvdQPatarams.setFxRate(123D);
            l_cvdQPatarams.setExecQty(123D);
            l_cvdQPatarams.setExecPrice(123D);
            l_cvdQPatarams.setRepliesIndex(112L);
            l_cvdQPatarams.setRejectCode("S_001");
            l_cvdQPatarams.setAcceptDiv("01");
            l_cvdQPatarams.setXblocksProductType(ProductTypeEnum.OTHER);  
            l_cvdQPatarams.setOpType(SleSendqOpTypeEnum.CANCEL_ORDER);
            l_cvdQPatarams.setAccountId(333812512246L);
            l_cvdQPatarams.setStatus(SleRcvdqProcStatusEnum.NO_DEFINE_2);
            l_cvdQPatarams.setCreatedTimestamp(GtlUtils.getSystemTimestamp());
            l_cvdQPatarams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
            l_cvdQPatarams.setPrice(200D);
            l_cvdQPatarams.setQuantity(100D);
            TestDBUtility.insertWithDel(l_cvdQPatarams);

            //GenCurrencyParams
            TestDBUtility.deleteAll(GenCurrencyParams.TYPE);
            GenCurrencyParams l_genCurrencyParams =
                TestDBUtility.getGenCurrencyRow();
            l_genCurrencyParams.setInstitutionCode("0D");
            l_genCurrencyParams.setCurrentBuyExecRate(12.23);
            l_genCurrencyParams.setCurrentSellExecRate(10.33);
            TestDBUtility.insertWithDel(l_genCurrencyParams);

            //FeqProductParams
            TestDBUtility.deleteAll(FeqProductParams.TYPE);
            FeqProductParams l_feqProductParams =
                TestDBUtility.getFeqProductRow();
            l_feqProductParams.setProductId(l_params.getProductId());
            l_feqProductParams.setCurrencyCode(l_genCurrencyParams.getCurrencyCode());
            TestDBUtility.insertWithDel(l_feqProductParams);
            
            //InstitutionParams
            TestDBUtility.deleteAll(InstitutionParams.TYPE);
            InstitutionParams l_institutionParams =
                TestDBUtility.getInstitutionRow();
            l_institutionParams.setInstitutionCode("0D");
            TestDBUtility.insertWithDel(l_institutionParams);
            
            
            WEB3FeqOrderAcceptCancelUnit l_cancelUnit = new WEB3FeqOrderAcceptCancelUnit();
            l_cancelUnit.aftChangeAcceptDiv = "01";

            WEB3FeqOrderAcceptExecutionNotifyUnitTransactionCallback l_callBack =
                new WEB3FeqOrderAcceptExecutionNotifyUnitTransactionCallback(l_orderUnit, l_cvdQPatarams);

            l_callBack.process();

            QueryProcessor l_queryProcesser = Processors.getDefaultProcessor();
            List list = l_queryProcesser.doFindAllQuery(SleRcvdQParams.TYPE);
            assertEquals(1, list.size());
            SleRcvdQRow l_SleRcvdQRow = (SleRcvdQRow)list.get(0);
            assertEquals(SleRcvdqProcStatusEnum.EXEC_PROCESSING, l_SleRcvdQRow.getStatus());
            
            List list2 = l_queryProcesser.doFindAllQuery(FeqOrderUnitParams.TYPE);
            assertEquals(1, list2.size());
            FeqOrderUnitRow l_feqOrderUnitRow = (FeqOrderUnitRow)list2.get(0);
            assertEquals("1", l_feqOrderUnitRow.getTemporaryExecutionFlag());
            
        }
        catch(WEB3BaseException l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            log.error(l_ex.getMessage(), l_ex);
            assertEquals(l_ex.getErrorInfo(), WEB3ErrorCatalog.SYSTEM_ERROR_80003);
        }
        catch(Exception l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
    }
    
    public void testProcess_Case04()
    {
        final String STR_METHOD_NAME = " testProcess_Case04()";
        log.entering(STR_METHOD_NAME);

        try
        {
            TestDBUtility.deleteAll(TradingTimeParams.TYPE);
            TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);
            WEB3GentradeTradingTimeManagementForMock.setClendarContext(null);

            WEB3GentradeTradingClendarContext l_clendarContext = new WEB3GentradeTradingClendarContext();
            l_clendarContext.setInstitutionCode("0D");
            l_clendarContext.setBranchCode("381");
            l_clendarContext.setProductCode("0");
            l_clendarContext.setMarketCode("N1");
            l_clendarContext.setTradingTimeType("10");
            l_clendarContext.setBizDateType("1");
            ThreadLocalSystemAttributesRegistry.setAttribute("web3.tradingcalendarcontext", l_clendarContext);
            
            WEB3GentradeTradingTimeManagementForMock.setClendarContext(l_clendarContext);
            WEB3GentradeTradingTimeManagementForMock.mockSetTimestamp();
            
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                  "webbroker3.tradingpower.WEB3TPTradingPowerServiceImpl",
                  "reCalcTradingPower",
                  new Class[]{ WEB3GentradeSubAccount.class},
                  null);
            TestDBUtility.deleteAll(MarketParams.TYPE);
            MarketParams l_MarketParams = TestDBUtility.getMarketRow();
            l_MarketParams.setMarketCode("N1");
            TestDBUtility.insertWithDel(l_MarketParams);
            
            //FeqOrderUnitParams
            TestDBUtility.deleteAll(FeqOrderUnitRow.TYPE);
            FeqOrderUnitParams l_params = TestDBUtility.getFeqOrderUnitRow();
            l_params.setOrderUnitId(1234L);
            l_params.setExecutionSeqNo(111);
            l_params.setAccountId(333812512246L);
            l_params.setOrderStatus(OrderStatusEnum.ORDERING);
            l_params.setExecEndTimestamp(null);
            l_params.setLimitPrice(200D);
            l_params.setQuantity(100D);
            l_params.setTemporaryExecutionFlag("2");
            TestDBUtility.insertWithDel(l_params);
            
            //BranchRow
            TestDBUtility.deleteAll(BranchRow.TYPE);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setBranchId(l_params.getBranchId());
            TestDBUtility.insertWithDel(l_branchParams);
            
            //ProductRow
            TestDBUtility.deleteAll(ProductRow.TYPE);
            ProductParams l_productParams = TestDBUtility.getProductRow();
            l_productParams.setProductId(l_params.getProductId());
            TestDBUtility.insertWithDel(l_productParams);
            
            //MainAccountRow
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(l_params.getAccountId());
            l_mainAccountParams.setBranchId(l_params.getBranchId());
            TestDBUtility.insertWithDel(l_mainAccountParams);
            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            SubAccountParams l_SubAccountParams = TestDBUtility.getSubAccountRow();
            l_SubAccountParams.setAccountId(l_params.getAccountId());
            l_SubAccountParams.setBranchId(l_params.getBranchId());
            l_SubAccountParams.setSubAccountId(l_params.getSubAccountId());
            TestDBUtility.insertWithDel(l_SubAccountParams);
            WEB3FeqOrderManager l_feqOrderManager = new WEB3FeqOrderManager();
            WEB3FeqOrderUnit l_orderUnit = (WEB3FeqOrderUnit)l_feqOrderManager.getOrderUnit(1234L);

            //SleRcvdQParams
            TestDBUtility.deleteAll(SleRcvdQParams.TYPE);
            SleRcvdQParams l_cvdQPatarams = new SleRcvdQParams();
            l_cvdQPatarams.setRouteDiv("2");
            l_cvdQPatarams.setFxRate(123D);
            l_cvdQPatarams.setExecQty(123D);
            l_cvdQPatarams.setExecPrice(123D);
            l_cvdQPatarams.setRepliesIndex(112L);
            l_cvdQPatarams.setRejectCode("S_001");
            l_cvdQPatarams.setAcceptDiv("01");
            l_cvdQPatarams.setXblocksProductType(ProductTypeEnum.OTHER);  
            l_cvdQPatarams.setOpType(SleSendqOpTypeEnum.CANCEL_ORDER);
            l_cvdQPatarams.setAccountId(333812512246L);
            l_cvdQPatarams.setStatus(SleRcvdqProcStatusEnum.NO_DEFINE_2);
            l_cvdQPatarams.setCreatedTimestamp(GtlUtils.getSystemTimestamp());
            l_cvdQPatarams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
            l_cvdQPatarams.setPrice(200D);
            l_cvdQPatarams.setQuantity(100D);
            TestDBUtility.insertWithDel(l_cvdQPatarams);

            //GenCurrencyParams
            TestDBUtility.deleteAll(GenCurrencyParams.TYPE);
            GenCurrencyParams l_genCurrencyParams =
                TestDBUtility.getGenCurrencyRow();
            l_genCurrencyParams.setInstitutionCode("0D");
            l_genCurrencyParams.setCurrentBuyExecRate(12.23);
            l_genCurrencyParams.setCurrentSellExecRate(10.33);
            l_genCurrencyParams.setExecRateUpdateTimestamp(GtlUtils.getSystemTimestamp());
            TestDBUtility.insertWithDel(l_genCurrencyParams);

            //FeqProductParams
            TestDBUtility.deleteAll(FeqProductParams.TYPE);
            FeqProductParams l_feqProductParams =
                TestDBUtility.getFeqProductRow();
            l_feqProductParams.setProductId(l_params.getProductId());
            l_feqProductParams.setCurrencyCode(l_genCurrencyParams.getCurrencyCode());
            TestDBUtility.insertWithDel(l_feqProductParams);
            
            //InstitutionParams
            TestDBUtility.deleteAll(InstitutionParams.TYPE);
            InstitutionParams l_institutionParams =
                TestDBUtility.getInstitutionRow();
            l_institutionParams.setInstitutionCode("0D");
            TestDBUtility.insertWithDel(l_institutionParams);
            
            
            WEB3FeqOrderAcceptCancelUnit l_cancelUnit = new WEB3FeqOrderAcceptCancelUnit();
            l_cancelUnit.aftChangeAcceptDiv = "01";

            WEB3FeqOrderAcceptExecutionNotifyUnitTransactionCallback l_callBack =
                new WEB3FeqOrderAcceptExecutionNotifyUnitTransactionCallback(l_orderUnit, l_cvdQPatarams);

            l_callBack.process();

            QueryProcessor l_queryProcesser = Processors.getDefaultProcessor();
            List list = l_queryProcesser.doFindAllQuery(SleRcvdQParams.TYPE);
            assertEquals(1, list.size());
            SleRcvdQRow l_SleRcvdQRow = (SleRcvdQRow)list.get(0);
            assertEquals(SleRcvdqProcStatusEnum.PROCESSED, l_SleRcvdQRow.getStatus());
            
            List list2 = l_queryProcesser.doFindAllQuery(FeqOrderUnitParams.TYPE);
            assertEquals(1, list2.size());
            FeqOrderUnitRow l_feqOrderUnitRow = (FeqOrderUnitRow)list2.get(0);
            assertEquals("0", l_feqOrderUnitRow.getTemporaryExecutionFlag());
            
        }
        catch(WEB3BaseException l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            log.error(l_ex.getMessage(), l_ex);
            assertEquals(l_ex.getErrorInfo(), WEB3ErrorCatalog.SYSTEM_ERROR_80003);
        }
        catch(Exception l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
    }
    
    public void testProcess_Case05()
    {
        final String STR_METHOD_NAME = " testProcess_Case05()";
        log.entering(STR_METHOD_NAME);

        try
        {
            TestDBUtility.deleteAll(TradingTimeParams.TYPE);
            TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);
            WEB3GentradeTradingTimeManagementForMock.setClendarContext(null);

            WEB3GentradeTradingClendarContext l_clendarContext = new WEB3GentradeTradingClendarContext();
            l_clendarContext.setInstitutionCode("0D");
            l_clendarContext.setBranchCode("381");
            l_clendarContext.setProductCode("0");
            l_clendarContext.setMarketCode("N1");
            l_clendarContext.setTradingTimeType("10");
            l_clendarContext.setBizDateType("1");
            ThreadLocalSystemAttributesRegistry.setAttribute("web3.tradingcalendarcontext", l_clendarContext);
            
            WEB3GentradeTradingTimeManagementForMock.setClendarContext(l_clendarContext);
            WEB3GentradeTradingTimeManagementForMock.mockSetTimestamp();
            
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                  "webbroker3.tradingpower.WEB3TPTradingPowerServiceImpl",
                  "reCalcTradingPower",
                  new Class[]{ WEB3GentradeSubAccount.class},
                  null);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.feq.WEB3FeqOrderManager",
                    "updateEstimatedPrice",
                    new Class[]{ WEB3FeqOrderUnit.class, Date.class},
                    null);
            
            WEB3FeqAmountCalcResult l_result = new WEB3FeqAmountCalcResult();
            l_result.setNetAmount(100);
            l_result.setNetAmountFc(1000);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.feq.WEB3FeqBizLogicProvider",
                    "calcFeqAmount",
                    new Class[]{ WEB3GentradeSubAccount.class,
                            WEB3FeqProduct.class,
                            WEB3GentradeMarket.class,
                            Date.class,
                            Date.class,
                            double.class,
                            double.class,
                            boolean.class,
                            boolean.class,
                            boolean.class,
                            String.class},
                            l_result);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.feq.WEB3FeqPositionManager",
                    "updateTransaction",
                    new Class[]{long.class, boolean.class},
                    null);
            
            
            TestDBUtility.deleteAll(MarketParams.TYPE);
            MarketParams l_MarketParams = TestDBUtility.getMarketRow();
            l_MarketParams.setMarketCode("N1");
            TestDBUtility.insertWithDel(l_MarketParams);
            QueryProcessor l_queryProcesser = Processors.getDefaultProcessor();
            //FeqOrderUnitParams
            TestDBUtility.deleteAll(FeqOrderUnitRow.TYPE);
            FeqOrderUnitParams l_params = TestDBUtility.getFeqOrderUnitRow();
            l_params.setOrderUnitId(1234L);
            l_params.setExecutionSeqNo(111);
            l_params.setAccountId(333812512246L);
            l_params.setOrderStatus(OrderStatusEnum.ORDERING);
            l_params.setExecEndTimestamp(null);
            l_params.setLimitPrice(200D);
            l_params.setQuantity(100D);
            l_params.setTemporaryExecutionFlag("2");
            l_params.setOrderEmpCode("1001");
            TestDBUtility.insertWithDel(l_params);
            
            //BranchRow
            TestDBUtility.deleteAll(BranchRow.TYPE);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setBranchId(l_params.getBranchId());
            TestDBUtility.insertWithDel(l_branchParams);
            
            //ProductRow
            TestDBUtility.deleteAll(ProductRow.TYPE);
            ProductParams l_productParams = TestDBUtility.getProductRow();
            l_productParams.setProductId(l_params.getProductId());
            TestDBUtility.insertWithDel(l_productParams);
            
            //MainAccountRow
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(l_params.getAccountId());
            l_mainAccountParams.setBranchId(l_params.getBranchId());
            TestDBUtility.insertWithDel(l_mainAccountParams);
            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            SubAccountParams l_SubAccountParams = TestDBUtility.getSubAccountRow();
            l_SubAccountParams.setAccountId(l_params.getAccountId());
            l_SubAccountParams.setBranchId(l_params.getBranchId());
            l_SubAccountParams.setSubAccountId(l_params.getSubAccountId());
            TestDBUtility.insertWithDel(l_SubAccountParams);
            WEB3FeqOrderManager l_feqOrderManager = new WEB3FeqOrderManager();
            WEB3FeqOrderUnit l_orderUnit = (WEB3FeqOrderUnit)l_feqOrderManager.getOrderUnit(1234L);

            //SleRcvdQParams
            TestDBUtility.deleteAll(SleRcvdQParams.TYPE);
            SleRcvdQParams l_cvdQPatarams = new SleRcvdQParams();
            l_cvdQPatarams.setRouteDiv("2");
            l_cvdQPatarams.setFxRate(123D);
            l_cvdQPatarams.setExecQty(123D);
            l_cvdQPatarams.setExecPrice(123D);
            l_cvdQPatarams.setRepliesIndex(112L);
            l_cvdQPatarams.setRejectCode("S_001");
            l_cvdQPatarams.setAcceptDiv("01");
            l_cvdQPatarams.setXblocksProductType(ProductTypeEnum.OTHER);  
            l_cvdQPatarams.setOpType(SleSendqOpTypeEnum.CANCEL_ORDER);
            l_cvdQPatarams.setAccountId(333812512246L);
            l_cvdQPatarams.setStatus(SleRcvdqProcStatusEnum.EXEC_PROCESSING);
            l_cvdQPatarams.setCreatedTimestamp(GtlUtils.getSystemTimestamp());
            l_cvdQPatarams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
            l_cvdQPatarams.setPrice(200D);
            l_cvdQPatarams.setQuantity(100D);
            l_cvdQPatarams.setOrderEmpCode("1001");
            TestDBUtility.insertWithDel(l_cvdQPatarams);
            
            //22222
            l_cvdQPatarams.setQueueId(124587963);
            l_cvdQPatarams.setStatus(SleRcvdqProcStatusEnum.EXEC_PROCESSING);
            l_queryProcesser.doInsertQuery(l_cvdQPatarams);

            //GenCurrencyParams
            TestDBUtility.deleteAll(GenCurrencyParams.TYPE);
            GenCurrencyParams l_genCurrencyParams =
                TestDBUtility.getGenCurrencyRow();
            l_genCurrencyParams.setInstitutionCode("0D");
            l_genCurrencyParams.setCurrentBuyExecRate(12.23);
            l_genCurrencyParams.setCurrentSellExecRate(10.33);
            l_genCurrencyParams.setExecRateUpdateTimestamp(GtlUtils.getSystemTimestamp());
            TestDBUtility.insertWithDel(l_genCurrencyParams);

            //FeqProductParams
            TestDBUtility.deleteAll(FeqProductParams.TYPE);
            FeqProductParams l_feqProductParams =
                TestDBUtility.getFeqProductRow();
            l_feqProductParams.setProductId(l_params.getProductId());
            l_feqProductParams.setCurrencyCode(l_genCurrencyParams.getCurrencyCode());
            l_feqProductParams.setMarketCode(l_MarketParams.getMarketCode());
            TestDBUtility.insertWithDel(l_feqProductParams);
            
            TestDBUtility.deleteAll(TradedProductParams.TYPE);
            TradedProductParams l_TradedProductParams = TestDBUtility.getTradedProductRow();
            l_TradedProductParams.setInstitutionCode("0D");
            l_TradedProductParams.setProductId(l_params.getProductId());
            l_TradedProductParams.setMarketId(l_MarketParams.getMarketId());
            TestDBUtility.insertWithDel(l_TradedProductParams);
            
            TestDBUtility.deleteAll(FeqTradedProductParams.TYPE);
            FeqTradedProductParams l_FeqTradedProductParams = TestDBUtility.getFeqTradedProductRow();
            l_FeqTradedProductParams.setInstitutionCode("0D");
            l_FeqTradedProductParams.setProductId(l_params.getProductId());
            l_FeqTradedProductParams.setMarketId(l_MarketParams.getMarketId());
            TestDBUtility.insertWithDel(l_FeqTradedProductParams);
            
            //InstitutionParams
            TestDBUtility.deleteAll(InstitutionParams.TYPE);
            InstitutionParams l_institutionParams =
                TestDBUtility.getInstitutionRow();
            l_institutionParams.setInstitutionCode("0D");
            TestDBUtility.insertWithDel(l_institutionParams);
            
            TestDBUtility.deleteAll(FeqOrderExecutionRow.TYPE);
            FeqOrderExecutionParams l_FeqOrderExecutionRow = TestDBUtility.getFeqOrderExecutionParams();
            l_FeqOrderExecutionRow.setOrderUnitId(1234L);
            l_FeqOrderExecutionRow.setProductId(l_params.getProductId());
            TestDBUtility.insertWithDel(l_FeqOrderExecutionRow);
            
            TestDBUtility.deleteAll(FeqFinTransactionParams.TYPE);
            FeqFinTransactionParams l_FeqFinTransactionParams = TestDBUtility.getFeqFinTransactionParams();
            l_FeqFinTransactionParams.setProductId(l_params.getProductId());
            l_FeqFinTransactionParams.setOrderId(l_params.getOrderId());
            l_FeqFinTransactionParams.setOrderUnitId(l_params.getOrderUnitId());
            l_FeqFinTransactionParams.setMarketId(3303L);
            l_FeqFinTransactionParams.setAccountId(l_params.getAccountId());
            l_FeqFinTransactionParams.setSubAccountId(l_params.getSubAccountId());
            l_FeqFinTransactionParams.setQuantity(100);
            l_FeqFinTransactionParams.setPrice(100);
            TestDBUtility.insertWithDel(l_FeqFinTransactionParams);
            
            
            TestDBUtility.deleteAll(InstBranchProductParams.TYPE);
            InstBranchProductParams l_InstBranchProductParams = TestDBUtility.getInstBranchProductRow();
            l_InstBranchProductParams.setBranchId(40625L);
            l_InstBranchProductParams.setCommissionProductCode("40");
            TestDBUtility.insertWithDel(l_InstBranchProductParams);
            
            TestDBUtility.deleteAll(EquityCommAccountCondMstParams.TYPE);
            EquityCommAccountCondMstParams l_EquityCommAccountCondMstParams = TestDBUtility.getEquityCommAccountCondMstRow();
            l_EquityCommAccountCondMstParams.setAccountId(l_params.getAccountId());
            l_EquityCommAccountCondMstParams.setBranchId(40625L);
            l_EquityCommAccountCondMstParams.setCommProductCode("40");
            l_EquityCommAccountCondMstParams.setInstitutionCode("0D");
            l_EquityCommAccountCondMstParams.setValidUntilBizDate("20080201");
            TestDBUtility.insertWithDel(l_EquityCommAccountCondMstParams);
            
            WEB3FeqOrderAcceptExecutionNotifyUnitTransactionCallback l_callBack =
                new WEB3FeqOrderAcceptExecutionNotifyUnitTransactionCallback(l_orderUnit, l_cvdQPatarams);

            l_callBack.process();

            
            List list = l_queryProcesser.doFindAllQuery(SleRcvdQParams.TYPE);
            assertEquals(2, list.size());
            SleRcvdQRow l_SleRcvdQRow = (SleRcvdQRow)list.get(1);
            assertEquals(SleRcvdqProcStatusEnum.PROCESSED, l_SleRcvdQRow.getStatus());
            
            List list2 = l_queryProcesser.doFindAllQuery(FeqOrderUnitParams.TYPE);
            assertEquals(1, list2.size());
            FeqOrderUnitRow l_feqOrderUnitRow = (FeqOrderUnitRow)list2.get(0);
            assertEquals("1", l_feqOrderUnitRow.getTemporaryExecutionFlag());
            
        }
        catch(WEB3BaseException l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            log.error(l_ex.getMessage(), l_ex);
            assertEquals(l_ex.getErrorInfo(), WEB3ErrorCatalog.SYSTEM_ERROR_80003);
        }
        catch(Exception l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
    }
    
    public void testProcess_case2()
    {
        final String STR_METHOD_NAME = " testProcess_case2()";
        log.entering(STR_METHOD_NAME);

        try
        {
            
            TestDBUtility.deleteAll(FeqOrderUnitRow.TYPE);
            FeqOrderUnitParams l_params = TestDBUtility.getFeqOrderUnitRow();
            l_params.setOrderUnitId(1234L);
            l_params.setExecutionSeqNo(111);
            l_params.setAccountId(333812512246L);
            l_params.setOrderStatus(OrderStatusEnum.ORDERING);
            l_params.setExecEndTimestamp(null);
            l_params.setLimitPrice(200D);
            l_params.setQuantity(100D);
            TestDBUtility.insertWithDel(l_params);
            
            TestDBUtility.deleteAll(BranchRow.TYPE);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setBranchId(l_params.getBranchId());
            TestDBUtility.insertWithDel(l_branchParams);
            

            
            TestDBUtility.deleteAll(MainAccountParams.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(333812512246L);

            TestDBUtility.insertWithDel(l_mainAccountParams);

            TestDBUtility.deleteAll(SubAccountParams.TYPE);            
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setAccountId(333812512246L);
            l_subAccountParams.setSubAccountId(33381251220301L);
            TestDBUtility.insertWithDel(l_subAccountParams);
            

            
            TestDBUtility.deleteAll(MarketRow.TYPE);
            MarketParams l_marketParams = TestDBUtility.getMarketRow();
            l_marketParams.setMarketId(3303);
            TestDBUtility.insertWithDel(l_marketParams);
            
            WEB3FeqOrderManager l_feqOrderManager = new WEB3FeqOrderManager();
            WEB3FeqOrderUnit l_orderUnit = (WEB3FeqOrderUnit)l_feqOrderManager.getOrderUnit(1234L);

            
            
            TestDBUtility.deleteAll(FeqOrderParams.TYPE);
            FeqOrderParams l_FeqOrderParams = TestDBUtility.getFeqOrderRow();
            TestDBUtility.insertWithDel(l_FeqOrderParams);
            
            
            TestDBUtility.deleteAll(SleRcvdQParams.TYPE);
            
            SleRcvdQParams l_cvdQPatarams = new SleRcvdQParams();
            
            l_cvdQPatarams.setRouteDiv("3");
            l_cvdQPatarams.setFxRate(123D);
            l_cvdQPatarams.setExecQty(123D);
            l_cvdQPatarams.setExecPrice(123D);
            l_cvdQPatarams.setRepliesIndex(112L);
            
            l_cvdQPatarams.setRejectCode("S_001");
            l_cvdQPatarams.setAcceptDiv("01");
            
            l_cvdQPatarams.setXblocksProductType(ProductTypeEnum.OTHER);  
            l_cvdQPatarams.setOpType(SleSendqOpTypeEnum.CANCEL_ORDER);
            l_cvdQPatarams.setAccountId(333812512246L);
            l_cvdQPatarams.setStatus(SleRcvdqProcStatusEnum.NO_DEFINE_2);
            l_cvdQPatarams.setCreatedTimestamp(GtlUtils.getSystemTimestamp());
            l_cvdQPatarams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
            
            l_cvdQPatarams.setPrice(200D);
            l_cvdQPatarams.setQuantity(100D);
            TestDBUtility.insertWithDel(l_cvdQPatarams);
            
            GenCurrencyParams l_genCurrencyParams = TestDBUtility.getGenCurrencyRow();
            
            TestDBUtility.deleteAll(GenCurrencyParams.TYPE);
            l_genCurrencyParams.setInstitutionCode("0D");
            //  通貨コード   currency_code   VARCHAR2   3   Notnull   ｢通貨コード｣sheet参照       
            l_genCurrencyParams.setCurrencyCode("01");
            l_genCurrencyParams.setExecRateUpdateTimestamp(GtlUtils.getSystemTimestamp());
            
            TestDBUtility.insertWithDel(l_genCurrencyParams);
            
            
            WEB3FeqOrderAcceptCancelUnit l_cancelUnit = new WEB3FeqOrderAcceptCancelUnit();
            l_cancelUnit.aftChangeAcceptDiv = "01";
            
            
            
            
            WEB3FeqOrderAcceptExecutionNotifyUnitTransactionCallback l_callBack =
                new WEB3FeqOrderAcceptExecutionNotifyUnitTransactionCallback(l_orderUnit, l_cvdQPatarams);

            Services.unregisterService(WEB3FeqExecutionNotifyUnitService.class);
            Services.registerService(WEB3FeqExecutionNotifyUnitService.class,
                new WEB3FeqExecutionNotifyUnitServiceImplForMock());
            
            Services.addInterceptor(WEB3FeqExecutionNotifyUnitService.class,
                new WEB3FeqExecutionNotifyUnitServiceInterceptorForMock());
            
            WEB3GentradeTradingTimeManagementForMock.mockSetTimestamp();

            l_callBack.process();
            
        }
        catch(DataCallbackException l_ex)
        {
            WEB3BaseException l_baseException = (WEB3BaseException)l_ex.getDetails();
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        catch(Exception l_ex)
        {
            
            log.exiting(STR_METHOD_NAME);
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        finally
        {
            Services.unregisterService(WEB3FeqExecutionNotifyUnitService.class);
            Services.registerService(
                WEB3FeqExecutionNotifyUnitService.class,
                new WEB3FeqExecutionNotifyUnitServiceImpl());
            Services.addInterceptor(
                WEB3FeqExecutionNotifyUnitService.class,
                new WEB3FeqExecutionNotifyUnitServiceInterceptor());
        }
        
        log.exiting(STR_METHOD_NAME);
        
       

    }
    
    public void testProcess_case3()
    {
        final String STR_METHOD_NAME = " testProcess_case3()";
        log.entering(STR_METHOD_NAME);

        try
        {
            
            TestDBUtility.deleteAll(FeqOrderUnitRow.TYPE);
            FeqOrderUnitParams l_params = TestDBUtility.getFeqOrderUnitRow();
            l_params.setOrderUnitId(1234L);
            l_params.setExecutionSeqNo(111);
            l_params.setAccountId(333812512246L);
            l_params.setOrderStatus(OrderStatusEnum.ORDERING);
            l_params.setExecEndTimestamp(null);
            l_params.setLimitPrice(200D);
            l_params.setQuantity(100D);
            TestDBUtility.insertWithDel(l_params);
            
            TestDBUtility.deleteAll(BranchRow.TYPE);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setBranchId(l_params.getBranchId());
            TestDBUtility.insertWithDel(l_branchParams);
            

            
            TestDBUtility.deleteAll(MainAccountParams.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(333812512246L);

            TestDBUtility.insertWithDel(l_mainAccountParams);

            TestDBUtility.deleteAll(SubAccountParams.TYPE);            
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setAccountId(333812512246L);
            l_subAccountParams.setSubAccountId(33381251220301L);
            TestDBUtility.insertWithDel(l_subAccountParams);
            

            
            TestDBUtility.deleteAll(MarketRow.TYPE);
            MarketParams l_marketParams = TestDBUtility.getMarketRow();
            l_marketParams.setMarketId(3303);
            TestDBUtility.insertWithDel(l_marketParams);
            
            WEB3FeqOrderManager l_feqOrderManager = new WEB3FeqOrderManager();
            WEB3FeqOrderUnit l_orderUnit = (WEB3FeqOrderUnit)l_feqOrderManager.getOrderUnit(1234L);

            
            
            TestDBUtility.deleteAll(FeqOrderParams.TYPE);
            FeqOrderParams l_FeqOrderParams = TestDBUtility.getFeqOrderRow();
            TestDBUtility.insertWithDel(l_FeqOrderParams);
            
            
            SleRcvdQParams l_cvdQPatarams = new SleRcvdQParams();
            l_cvdQPatarams.setRouteDiv("3");
            l_cvdQPatarams.setFxRate(123D);
            l_cvdQPatarams.setExecQty(123D);
            l_cvdQPatarams.setExecPrice(123D);
            l_cvdQPatarams.setRepliesIndex(112L);

            l_cvdQPatarams.setRejectCode("M_001");
            l_cvdQPatarams.setAcceptDiv("01");
            
            l_cvdQPatarams.setPrice(200D);
            l_cvdQPatarams.setQuantity(100D);
            
            GenCurrencyParams l_genCurrencyParams = TestDBUtility.getGenCurrencyRow();
            
            TestDBUtility.deleteAll(GenCurrencyParams.TYPE);
            l_genCurrencyParams.setInstitutionCode("0D");
            //  通貨コード   currency_code   VARCHAR2   3   Notnull   ｢通貨コード｣sheet参照       
            l_genCurrencyParams.setCurrencyCode("01");
            l_genCurrencyParams.setExecRateUpdateTimestamp(GtlUtils.getSystemTimestamp());
            
            TestDBUtility.insertWithDel(l_genCurrencyParams);
            
            
            WEB3FeqOrderAcceptCancelUnit l_cancelUnit = new WEB3FeqOrderAcceptCancelUnit();
            l_cancelUnit.aftChangeAcceptDiv = "01";
            
            
            
            
            WEB3FeqOrderAcceptExecutionNotifyUnitTransactionCallback l_callBack =
                new WEB3FeqOrderAcceptExecutionNotifyUnitTransactionCallback(l_orderUnit, l_cvdQPatarams);

            Services.unregisterService(WEB3FeqExecutionNotifyUnitService.class);
            Services.registerService(WEB3FeqExecutionNotifyUnitService.class,
                new WEB3FeqExecutionNotifyUnitServiceImplForMock());
            
            Services.addInterceptor(WEB3FeqExecutionNotifyUnitService.class,
                new WEB3FeqExecutionNotifyUnitServiceInterceptorForMock());
            
            WEB3GentradeTradingTimeManagementForMock.mockSetTimestamp();

            l_callBack.process();
            fail();
            
        }
        catch(DataCallbackException l_ex)
        {
            WEB3BaseException l_baseException = (WEB3BaseException)l_ex.getDetails();
            assertEquals(l_baseException.getErrorInfo(), WEB3ErrorCatalog.BUSINESS_ERROR_01975);
            log.exiting(STR_METHOD_NAME);
            
        }
        catch(Exception l_ex)
        {
            
            log.exiting(STR_METHOD_NAME);
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        finally
        {
            Services.unregisterService(WEB3FeqExecutionNotifyUnitService.class);
            Services.registerService(
                WEB3FeqExecutionNotifyUnitService.class,
                new WEB3FeqExecutionNotifyUnitServiceImpl());
            Services.addInterceptor(
                WEB3FeqExecutionNotifyUnitService.class,
                new WEB3FeqExecutionNotifyUnitServiceInterceptor());
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testProcess_case4()
    {
        final String STR_METHOD_NAME = " testProcess_case4()";
        log.entering(STR_METHOD_NAME);

        try
        {
            
            TestDBUtility.deleteAll(FeqOrderUnitRow.TYPE);
            FeqOrderUnitParams l_params = TestDBUtility.getFeqOrderUnitRow();
            l_params.setOrderUnitId(1234L);
            l_params.setExecutionSeqNo(111);
            l_params.setAccountId(333812512246L);
            l_params.setOrderStatus(OrderStatusEnum.ORDERING);
            l_params.setExecEndTimestamp(null);
            l_params.setLimitPrice(200D);
            l_params.setQuantity(100D);
            TestDBUtility.insertWithDel(l_params);
            
            TestDBUtility.deleteAll(BranchRow.TYPE);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setBranchId(l_params.getBranchId());
            TestDBUtility.insertWithDel(l_branchParams);
            

            
            TestDBUtility.deleteAll(MainAccountParams.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(333812512246L);

            TestDBUtility.insertWithDel(l_mainAccountParams);

            TestDBUtility.deleteAll(SubAccountParams.TYPE);            
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setAccountId(333812512246L);
            l_subAccountParams.setSubAccountId(33381251220301L);
            TestDBUtility.insertWithDel(l_subAccountParams);
            

            
            TestDBUtility.deleteAll(MarketRow.TYPE);
            MarketParams l_marketParams = TestDBUtility.getMarketRow();
            l_marketParams.setMarketId(3303);
            TestDBUtility.insertWithDel(l_marketParams);
            
            WEB3FeqOrderManager l_feqOrderManager = new WEB3FeqOrderManager();
            WEB3FeqOrderUnit l_orderUnit = (WEB3FeqOrderUnit)l_feqOrderManager.getOrderUnit(1234L);

            
            
            TestDBUtility.deleteAll(FeqOrderParams.TYPE);
            FeqOrderParams l_FeqOrderParams = TestDBUtility.getFeqOrderRow();
            TestDBUtility.insertWithDel(l_FeqOrderParams);
            
            
            TestDBUtility.deleteAll(SleRcvdQParams.TYPE);
            
            SleRcvdQParams l_cvdQPatarams = new SleRcvdQParams();
            
            l_cvdQPatarams.setRouteDiv("3");
            l_cvdQPatarams.setFxRate(123D);
            l_cvdQPatarams.setExecQty(123D);
            l_cvdQPatarams.setExecPrice(123D);
            l_cvdQPatarams.setRepliesIndex(112L);
            
            l_cvdQPatarams.setRejectCode("S_001");
            l_cvdQPatarams.setAcceptDiv("01");
            
            l_cvdQPatarams.setXblocksProductType(ProductTypeEnum.OTHER);  
            l_cvdQPatarams.setOpType(SleSendqOpTypeEnum.CANCEL_ORDER);
            l_cvdQPatarams.setAccountId(333812512246L);
            l_cvdQPatarams.setStatus(SleRcvdqProcStatusEnum.NO_DEFINE_2);
            l_cvdQPatarams.setCreatedTimestamp(GtlUtils.getSystemTimestamp());
            l_cvdQPatarams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
            
            l_cvdQPatarams.setPrice(200D);
            l_cvdQPatarams.setQuantity(100D);
            TestDBUtility.insertWithDel(l_cvdQPatarams);
            
            GenCurrencyParams l_genCurrencyParams = TestDBUtility.getGenCurrencyRow();
            
            TestDBUtility.deleteAll(GenCurrencyParams.TYPE);
            l_genCurrencyParams.setInstitutionCode("0D");
            //  通貨コード   currency_code   VARCHAR2   3   Notnull   ｢通貨コード｣sheet参照       
            l_genCurrencyParams.setCurrencyCode("01");
            l_genCurrencyParams.setExecRateUpdateTimestamp(GtlUtils.getSystemTimestamp());
            
            TestDBUtility.insertWithDel(l_genCurrencyParams);
            
            
            WEB3FeqOrderAcceptCancelUnit l_cancelUnit = new WEB3FeqOrderAcceptCancelUnit();
            l_cancelUnit.aftChangeAcceptDiv = "01";

            
            WEB3FeqOrderAcceptExecutionNotifyUnitTransactionCallback l_callBack =
                new WEB3FeqOrderAcceptExecutionNotifyUnitTransactionCallback(l_orderUnit, l_cvdQPatarams);

            Services.unregisterService(WEB3FeqExecutionNotifyUnitService.class);
            Services.registerService(WEB3FeqExecutionNotifyUnitService.class,
                new WEB3FeqExecutionNotifyUnitServiceImplForMock());
            
            Services.addInterceptor(WEB3FeqExecutionNotifyUnitService.class,
                new WEB3FeqExecutionNotifyUnitServiceInterceptorForMock());
            
            WEB3GentradeTradingTimeManagementForMock.mockSetTimestamp();

            l_callBack.process();
            

            List lisResult = null;
            try
            {
                QueryProcessor process = Processors.getDefaultProcessor();
                lisResult = process.doFindAllQuery(SleRcvdQRow.TYPE);
            }
            catch(DataException l_ex)
            {
                l_ex.printStackTrace();
            }
            log.debug("lisResult.size()=============="+lisResult.size());
            SleRcvdQRow l_sle = (SleRcvdQRow)lisResult.get(0);

            assertEquals(l_sle.getStatus(),SleRcvdqProcStatusEnum.PROCESSED);
  
        }
        catch(DataCallbackException l_ex)
        {
            WEB3BaseException l_baseException = (WEB3BaseException)l_ex.getDetails();
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        catch(Exception l_ex)
        {
            
            log.exiting(STR_METHOD_NAME);
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        finally
        {
            Services.unregisterService(WEB3FeqExecutionNotifyUnitService.class);
            Services.registerService(
                WEB3FeqExecutionNotifyUnitService.class,
                new WEB3FeqExecutionNotifyUnitServiceImpl());
            Services.addInterceptor(
                WEB3FeqExecutionNotifyUnitService.class,
                new WEB3FeqExecutionNotifyUnitServiceInterceptor());
        }
        
        log.exiting(STR_METHOD_NAME);


    }

    
    class WEB3FeqExecutionNotifyUnitServiceInterceptorForMock extends WEB3FeqExecutionNotifyUnitServiceInterceptor
    {
        
        public Object onCall(Method l_method, Object[] l_serviceParams) 
        {          
            final String STR_METHOD_NAME = " onCall(Method, Object[])";
            log.entering(STR_METHOD_NAME);

            if (l_serviceParams == null || l_serviceParams.length == 0)
            {
                log.debug("パラメータ値不正。");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                    this.getClass().getName() + STR_METHOD_NAME, 
                    "パラメータ値不正。");
            }
            
            WEB3GentradeTradingClendarContext l_context = null;
            try
            {  
                l_context = new WEB3GentradeTradingClendarContext();
                
                //引数.サービスの引数[0]より、注文単位を取得する。
                WEB3FeqOrderUnit l_orderUnit = (WEB3FeqOrderUnit)l_serviceParams[0];
                
                //引数.サービスの引数[1]より、外株出来通知キューを取得する。
                HostFeqOrderExecNotifyParams l_params = (HostFeqOrderExecNotifyParams)l_serviceParams[1];
                
                if (l_params != null)
                {
                    //取引カレンダコンテキスト.証券会社コード = 外株出来通知キュー.get証券会社コード()の戻り値
                    l_context.setInstitutionCode(l_params.getInstitutionCode());
                    
                    //取引カレンダコンテキスト.部店コード = 外株出来通知キュー.get部店コード()の戻り値
                    l_context.setBranchCode(l_params.getBranchCode());
                }
                
                //取引カレンダコンテキスト.市場コード = 注文単位.get市場().get市場コード()の戻り値
                if (l_orderUnit != null)
                {
                    l_context.setMarketCode(l_orderUnit.getMarket().getMarketCode());
                }
                
                //取引カレンダコンテキスト.受付時間区分 = "10：外国株式"
                l_context.setTradingTimeType(WEB3TradingTimeTypeDef.FOREIGN_STOCK);
                
                //取引カレンダコンテキスト.商品コード = 0：DEFAULT 
                l_context.setProductCode(WEB3ProductCodeDef.DEFAULT);
                
                //取引カレンダコンテキスト.注文受付商品 = "04：外国株"
                l_context.setOrderAcceptProduct(WEB3OrderAccProductDef.FOREIGN_STOCK);
                
                //取引カレンダコンテキスト.注文受付トランザクション = null
                l_context.setOrderAcceptTransaction(null);
                
                // 取引時間コンテキストをセットする
                ThreadLocalSystemAttributesRegistry.setAttribute(
                    WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH,
                    l_context);
            }
            catch (WEB3BaseException l_ex)
            {
                log.error("DBへのアクセスに失敗しました。", l_ex);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
            }
            log.exiting(STR_METHOD_NAME);
            return null;   
        }
    }

}
@
