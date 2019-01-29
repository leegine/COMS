head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.01.18.32;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3ToSuccIfoOrderUnitServiceImplTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : (WEB3ToSuccIfoOrderUnitServiceImplTest.java)
Author Name      : Daiwa Institute of Research
Revision History : 2008/05/09 安陽(中訊) 新規作成
*/
package webbroker3.triggerorder.service.delegate.stdimpls;

import java.sql.Timestamp;

import com.fitechlabs.xtrade.kernel.message.ErrorInfo;
import com.fitechlabs.xtrade.kernel.util.ThreadLocalSystemAttributesRegistry;
import com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ContractTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProcessingResult;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SideEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccountTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.BranchParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.BranchRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.InstitutionParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.InstitutionRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MarketParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MarketRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.ProductParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.ProductRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.SubAccountParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.SubAccountRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.TraderParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.TraderRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.NewOrderValidationResult;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.OrderSubmissionResult;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.SettleContractEntry;
import com.fitechlabs.xtrade.plugin.tc.xbifo.IfoOrderUnit;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoContractParams;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoContractRow;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoLockedContractDetailsParams;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoProductParams;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoProductRow;
import com.fitechlabs.xtrade.plugin.tc.xbifo.ordersubmitter.io.IfoOpenContractOrderSpec;
import com.fitechlabs.xtrade.plugin.tc.xbifo.ordersubmitter.io.IfoSettleContractOrderSpec;

import test.util.TestDBUtility;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.gentrade.WEB3GentradeCommission;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.gentrade.data.TradingTimeParams;
import webbroker3.gentrade.data.TradingTimeRow;
import webbroker3.ifo.WEB3IfoEstimateDeliveryAmountCalcResult;
import webbroker3.ifo.WEB3IfoOpenContractOrderSpec;
import webbroker3.ifo.WEB3IfoSettleContractOrderSpec;
import webbroker3.ifo.WEB3IfoTradedProductImpl;
import webbroker3.mock.TestBaseForMock;
import webbroker3.tradingpower.WEB3TPTradingPowerResult;
import webbroker3.triggerorder.WEB3ToSuccIfoOrderUnitImpl;
import webbroker3.triggerorder.base.data.RsvIfoClosingContractSpecParams;
import webbroker3.triggerorder.base.data.RsvIfoClosingContractSpecRow;
import webbroker3.triggerorder.base.data.RsvIfoOrderActionRow;
import webbroker3.triggerorder.base.data.RsvIfoOrderUnitParams;
import webbroker3.triggerorder.base.data.RsvIfoOrderUnitRow;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

public class WEB3ToSuccIfoOrderUnitServiceImplTest extends TestBaseForMock
{
    
    private WEB3ToSuccIfoOrderUnitServiceImpl l_serviceImpl = null;
    
    /**
     * (ログ出力ユーティリティ。)<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3ToSuccIfoOrderUnitServiceImplTest.class);
    
    public WEB3ToSuccIfoOrderUnitServiceImplTest(String arg0)
    {
        super(arg0);
    }

    protected void setUp() throws Exception
    {
        super.setUp();
        MOCK_MANAGER.setIsMockUsed(true);
        TestDBUtility.deleteAll(RsvIfoOrderActionRow.TYPE);
        this.l_serviceImpl = new WEB3ToSuccIfoOrderUnitServiceImpl();
        
        TestDBUtility.deleteAll(BranchRow.TYPE);
        BranchParams l_BranchParams = TestDBUtility.getBranchRow();
        TestDBUtility.insertWithDel(l_BranchParams);
        
        TestDBUtility.deleteAll(InstitutionRow.TYPE);
        InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
        TestDBUtility.insertWithDel(l_institutionParams);
    }

    protected void tearDown() throws Exception
    {
        this.l_serviceImpl = null;
        super.tearDown();
    }

    /*
     * Test method for 'webbroker3.triggerorder.service.delegate.stdimpls.WEB3ToSuccIfoOrderUnitServiceImpl.createSettleContractEntries(RsvIfoClosingContractSpecRow[])'
     */
    public void testCreateSettleContractEntries_C0001()
    {
        final String STR_METHOD_NAME = "testCreateSettleContractEntries_C0001()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            l_serviceImpl.createSettleContractEntries(null);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        catch (WEB3SystemLayerException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80017, l_ex.getErrorInfo());
        }
        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
    }
    
    public void testCreateSettleContractEntries_C0002()
    {
        final String STR_METHOD_NAME = "testCreateSettleContractEntries_C0002()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            RsvIfoClosingContractSpecRow[] l_rsvIfoClosingContractSpecs =
                new RsvIfoClosingContractSpecRow[0];
            SettleContractEntry[] l_settleContractEntries =
                l_serviceImpl.createSettleContractEntries(l_rsvIfoClosingContractSpecs);
            assertNull(l_settleContractEntries);
            log.exiting(TEST_END + STR_METHOD_NAME);
        }
        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
    }
    
    public void testCreateSettleContractEntries_C0003()
    {
        final String STR_METHOD_NAME = "testCreateSettleContractEntries_C0003()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            RsvIfoClosingContractSpecRow[] l_rsvIfoClosingContractSpecs =
                new RsvIfoClosingContractSpecRow[1];
            RsvIfoClosingContractSpecParams l_rsvIfoClosingContractSpecParams =
                TestDBUtility.getRsvIfoClosingContractSpecRow();
            l_rsvIfoClosingContractSpecParams.setContractId(1001L);
            l_rsvIfoClosingContractSpecParams.setQuantity(100.0D);
            l_rsvIfoClosingContractSpecs[0] = l_rsvIfoClosingContractSpecParams;
            
            TestDBUtility.deleteAll(IfoContractParams.TYPE);
            IfoContractParams l_ifoContractParams = TestDBUtility.getIfoContractRow();
            l_ifoContractParams.setContractId(1001L);
            l_ifoContractParams.setQuantity(50.0D);
            TestDBUtility.insertWithDel(l_ifoContractParams);
            
            TestDBUtility.deleteAll(IfoLockedContractDetailsParams.TYPE);
            IfoLockedContractDetailsParams l_ifoLockedContractDetailsParams =
                TestDBUtility.getIfoLockedContractDetailsRow();
            l_ifoLockedContractDetailsParams.setContractId(1001L);
            l_ifoLockedContractDetailsParams.setLockedQuantity(30.0D);
            TestDBUtility.insertWithDel(l_ifoLockedContractDetailsParams);
            
            l_serviceImpl.createSettleContractEntries(l_rsvIfoClosingContractSpecs);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        catch (WEB3BusinessLayerException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_03082, l_ex.getErrorInfo());
        }
        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
    }
    
    public void testCreateSettleContractEntries_C0004()
    {
        final String STR_METHOD_NAME = "testCreateSettleContractEntries_C0004()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            RsvIfoClosingContractSpecRow[] l_rsvIfoClosingContractSpecs =
                new RsvIfoClosingContractSpecRow[3];
            RsvIfoClosingContractSpecParams l_rsvIfoClosingContractSpecParams;
            IfoContractParams l_ifoContractParams;
            TestDBUtility.deleteAll(IfoContractParams.TYPE);
            IfoLockedContractDetailsParams l_ifoLockedContractDetailsParams;
            TestDBUtility.deleteAll(IfoLockedContractDetailsParams.TYPE);
            
            //l_rsvIfoClosingContractSpecs[0]
            l_rsvIfoClosingContractSpecParams = TestDBUtility.getRsvIfoClosingContractSpecRow();
            l_rsvIfoClosingContractSpecParams.setContractId(1001L);
            l_rsvIfoClosingContractSpecParams.setQuantity(50.0D);
            l_rsvIfoClosingContractSpecs[0] = l_rsvIfoClosingContractSpecParams;
            
            l_ifoContractParams = TestDBUtility.getIfoContractRow();
            l_ifoContractParams.setContractId(1001L);
            l_ifoContractParams.setQuantity(80.0D);
            l_ifoContractParams.setAccountId(5001L);
            TestDBUtility.insertWithDel(l_ifoContractParams);
            
            l_ifoLockedContractDetailsParams = TestDBUtility.getIfoLockedContractDetailsRow();
            l_ifoLockedContractDetailsParams.setContractId(1001L);
            l_ifoLockedContractDetailsParams.setLockedQuantity(30.0D);
            TestDBUtility.insertWithDel(l_ifoLockedContractDetailsParams);
            
            //l_rsvIfoClosingContractSpecs[1]
            l_rsvIfoClosingContractSpecParams = TestDBUtility.getRsvIfoClosingContractSpecRow();
            l_rsvIfoClosingContractSpecParams.setContractId(1002L);
            l_rsvIfoClosingContractSpecParams.setQuantity(0.0D);
            l_rsvIfoClosingContractSpecs[1] = l_rsvIfoClosingContractSpecParams;
            
          
            l_ifoContractParams = TestDBUtility.getIfoContractRow();
            l_ifoContractParams.setContractId(1002L);
            l_ifoContractParams.setQuantity(50.0D);
            l_ifoContractParams.setAccountId(5002L);
            TestDBUtility.insertWithDel(l_ifoContractParams);
            
            l_ifoLockedContractDetailsParams = TestDBUtility.getIfoLockedContractDetailsRow();
            l_ifoLockedContractDetailsParams.setContractId(1002L);
            l_ifoLockedContractDetailsParams.setLockedQuantity(30.0D);
            TestDBUtility.insertWithDel(l_ifoLockedContractDetailsParams);
            
            //l_rsvIfoClosingContractSpecs[2]
            l_rsvIfoClosingContractSpecParams = TestDBUtility.getRsvIfoClosingContractSpecRow();
            l_rsvIfoClosingContractSpecParams.setContractId(1003L);
            l_rsvIfoClosingContractSpecParams.setQuantity(10.0D);
            l_rsvIfoClosingContractSpecs[2] = l_rsvIfoClosingContractSpecParams;
            
            l_ifoContractParams = TestDBUtility.getIfoContractRow();
            l_ifoContractParams.setContractId(1003L);
            l_ifoContractParams.setQuantity(80.0D);
            l_ifoContractParams.setAccountId(5003L);
            TestDBUtility.insertWithDel(l_ifoContractParams);
            
            l_ifoLockedContractDetailsParams = TestDBUtility.getIfoLockedContractDetailsRow();
            l_ifoLockedContractDetailsParams.setContractId(1003L);
            l_ifoLockedContractDetailsParams.setLockedQuantity(30.0D);
            TestDBUtility.insertWithDel(l_ifoLockedContractDetailsParams);
            
            SettleContractEntry[] l_settleContractEntries =
                l_serviceImpl.createSettleContractEntries(l_rsvIfoClosingContractSpecs);
            
            assertEquals(2, l_settleContractEntries.length);
            assertEquals(1001L, l_settleContractEntries[0].getContractId());
            assertEquals(1003L, l_settleContractEntries[1].getContractId());
            assertEquals(50.0D, l_settleContractEntries[0].getQuantity(), 0);
            assertEquals(10.0D, l_settleContractEntries[1].getQuantity(), 0);
            
            log.exiting(TEST_END + STR_METHOD_NAME);
        }
        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
    }
    
    /**
     * 正常終了
     * 
     * l_rsvIfoOrderUnitRow.getTraderIdIsNull() == true
     * l_rsvIfoOrderUnitRow.getFirstOrderUnitIdIsNull() == true
     * BooleanEnum.TRUE.equals(l_rsvIfoOrderUnitRow.getEveningSessionCarryoverFlag()) == false
     * l_orderValidationResult.getProcessingResult().isFailedResult() == false
     * l_tradingPowerResult.isResultFlg() == true
     * l_orderSubmissionResult.getProcessingResult().isSuccessfulResult() == true
     * l_orderSpec[0].isBuyToOpenOrder() == true
     */
    public void testSubmitFuturesOpenContractOrder_C0001()
    {
        final String STR_METHOD_NAME = "testSubmitFuturesOpenContractOrder_C0001()";
        log.entering(STR_METHOD_NAME);
        
        WEB3ToSuccIfoOrderUnitServiceImpl l_impl =
            new WEB3ToSuccIfoOrderUnitServiceImpl();

        RsvIfoOrderUnitParams l_rsvIfoOrderUnitParams =
            TestDBUtility.getRsvIfoOrderUnitRow();
        l_rsvIfoOrderUnitParams.setBizDate("20080512");
        l_rsvIfoOrderUnitParams.setMarketId(3303L);
        l_rsvIfoOrderUnitParams.setProductId(3304148080000L);
        WEB3ToSuccIfoOrderUnitImpl l_toSuccIfoOrderUnitImpl =
            new WEB3ToSuccIfoOrderUnitImpl(l_rsvIfoOrderUnitParams);
        try
        {
            TestDBUtility.deleteAll(TradingTimeRow.TYPE);
            TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams.setInstitutionCode("0D");
            l_tradingTimeParams.setBranchCode("123");
            l_tradingTimeParams.setMarketCode("N1");
            l_tradingTimeParams.setTradingTimeType("01");
            l_tradingTimeParams.setProductCode("0");
            l_tradingTimeParams.setBizDateType("1");
            l_tradingTimeParams.setBizdateCalcParameter("0");
            TestDBUtility.insertWithDel(l_tradingTimeParams);
            
            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setAccountId(333812512203L);
            l_subAccountParams.setSubAccountId(33381251220301L);
            TestDBUtility.insertWithDel(l_subAccountParams);
            
            TestDBUtility.deleteAll(InstitutionRow.TYPE);
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            TestDBUtility.insertWithDel(l_institutionParams);
            
            TestDBUtility.deleteAll(MarketRow.TYPE);
            MarketParams l_marketParams = TestDBUtility.getMarketRow();
            TestDBUtility.insertWithDel(l_marketParams);
            
            TestDBUtility.deleteAll(ProductRow.TYPE);
            ProductParams l_productParams = TestDBUtility.getProductRow();
            l_productParams.setProductType(ProductTypeEnum.IFO);
            TestDBUtility.insertWithDel(l_productParams);
            
            TestDBUtility.deleteAll(IfoProductRow.TYPE);
            IfoProductParams l_ifoProductParams = TestDBUtility.getIfoProductRow();
            l_ifoProductParams.setProductId(3304148080000L);
            TestDBUtility.insertWithDel(l_ifoProductParams);
            
            ProcessingResult l_processingResult = ProcessingResult.newSuccessResultInstance();
            NewOrderValidationResult l_newOrderValidationResult =
                new NewOrderValidationResult(l_processingResult);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.ifo.WEB3FuturesOrderManagerImpl", "validateFuturesOpenContractOrder", new Class[]
                { WEB3GentradeSubAccount.class, WEB3IfoOpenContractOrderSpec.class, IfoOrderUnit.class },
                l_newOrderValidationResult);
            
            WEB3IfoEstimateDeliveryAmountCalcResult l_ifoEstimateDeliveryAmountCalcResult =
                new WEB3IfoEstimateDeliveryAmountCalcResult();
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.ifo.WEB3FuturesOrderManagerImpl",
                "calcEstimatePrice",
                new Class[]
                { WEB3GentradeCommission.class, double.class, WEB3GentradeSubAccount.class,
                    WEB3IfoTradedProductImpl.class, double.class, boolean.class },
                    l_ifoEstimateDeliveryAmountCalcResult);
            
            WEB3TPTradingPowerResult l_tpTradingPowerResult = new WEB3TPTradingPowerResult();
            l_tpTradingPowerResult.setResultFlg(true);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.tradingpower.WEB3TPTradingPowerServiceImpl", "validateTradingPower",
                new Class[] {WEB3GentradeSubAccount.class, Object[].class, Object[].class, OrderTypeEnum.class,boolean.class},
                l_tpTradingPowerResult);
                    
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(333812512203L);
            TestDBUtility.insertWithDel(l_mainAccountParams);
            
            OrderSubmissionResult l_orderSubmissionResult = new OrderSubmissionResult(l_processingResult);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.ifo.WEB3FuturesOrderManagerImpl", "submitOpenContractOrder", new Class[]
                { SubAccount.class, IfoOpenContractOrderSpec.class, long.class, String.class, boolean.class },
                l_orderSubmissionResult);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue("webbroker3.triggerorder.WEB3ToSuccOrderManagerImpl",
                "setOrderedToOrderUnit", new Class[]
                {ProductTypeEnum.class, long.class},
                null);
                    
            l_impl.submitFuturesOpenContractOrder(l_toSuccIfoOrderUnitImpl);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    /**
     * 正常終了
     * 
     * l_rsvIfoOrderUnitRow.getTraderIdIsNull() == false
     * l_rsvIfoOrderUnitRow.getFirstOrderUnitIdIsNull() == false
     * BooleanEnum.TRUE.equals(l_rsvIfoOrderUnitRow.getEveningSessionCarryoverFlag()) == true
     * l_orderValidationResult.getProcessingResult().isFailedResult() == true
     */
    public void testSubmitFuturesOpenContractOrder_C0002()
    {
        final String STR_METHOD_NAME = "testSubmitFuturesOpenContractOrder_C0002()";
        log.entering(STR_METHOD_NAME);
        
        WEB3ToSuccIfoOrderUnitServiceImpl l_impl =
            new WEB3ToSuccIfoOrderUnitServiceImpl();

        RsvIfoOrderUnitParams l_rsvIfoOrderUnitParams =
            TestDBUtility.getRsvIfoOrderUnitRow();
        l_rsvIfoOrderUnitParams.setBizDate("20080512");
        l_rsvIfoOrderUnitParams.setMarketId(3303L);
        l_rsvIfoOrderUnitParams.setProductId(3304148080000L);
        l_rsvIfoOrderUnitParams.setTraderId(3338111123L);
        l_rsvIfoOrderUnitParams.setFirstOrderUnitId(1);
        l_rsvIfoOrderUnitParams.setEveningSessionCarryoverFlag(BooleanEnum.TRUE);
        WEB3ToSuccIfoOrderUnitImpl l_toSuccIfoOrderUnitImpl =
            new WEB3ToSuccIfoOrderUnitImpl(l_rsvIfoOrderUnitParams);
        try
        {
            TestDBUtility.deleteAll(TradingTimeRow.TYPE);
            TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams.setInstitutionCode("0D");
            l_tradingTimeParams.setBranchCode("123");
            l_tradingTimeParams.setMarketCode("N1");
            l_tradingTimeParams.setTradingTimeType("01");
            l_tradingTimeParams.setProductCode("0");
            l_tradingTimeParams.setBizDateType("1");
            l_tradingTimeParams.setBizdateCalcParameter("0");
            TestDBUtility.insertWithDel(l_tradingTimeParams);
            
            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setAccountId(333812512203L);
            l_subAccountParams.setSubAccountId(33381251220301L);
            TestDBUtility.insertWithDel(l_subAccountParams);
            
            TestDBUtility.deleteAll(InstitutionRow.TYPE);
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            TestDBUtility.insertWithDel(l_institutionParams);
            
            TestDBUtility.deleteAll(MarketRow.TYPE);
            MarketParams l_marketParams = TestDBUtility.getMarketRow();
            TestDBUtility.insertWithDel(l_marketParams);
            
            TestDBUtility.deleteAll(ProductRow.TYPE);
            ProductParams l_productParams = TestDBUtility.getProductRow();
            l_productParams.setProductType(ProductTypeEnum.IFO);
            TestDBUtility.insertWithDel(l_productParams);
            
            TestDBUtility.deleteAll(IfoProductRow.TYPE);
            IfoProductParams l_ifoProductParams = TestDBUtility.getIfoProductRow();
            l_ifoProductParams.setProductId(3304148080000L);
            TestDBUtility.insertWithDel(l_ifoProductParams);
            
            ProcessingResult l_processingResult = ProcessingResult.newFailedResultInstance(WEB3ErrorCatalog.SYSTEM_ERROR_80005);
            NewOrderValidationResult l_newOrderValidationResult =
                new NewOrderValidationResult(l_processingResult);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.ifo.WEB3FuturesOrderManagerImpl", "validateFuturesOpenContractOrder", new Class[]
                { WEB3GentradeSubAccount.class, WEB3IfoOpenContractOrderSpec.class, IfoOrderUnit.class },
                l_newOrderValidationResult);
            
            WEB3IfoEstimateDeliveryAmountCalcResult l_ifoEstimateDeliveryAmountCalcResult =
                new WEB3IfoEstimateDeliveryAmountCalcResult();
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.ifo.WEB3FuturesOrderManagerImpl",
                "calcEstimatePrice",
                new Class[]
                { WEB3GentradeCommission.class, double.class, WEB3GentradeSubAccount.class,
                    WEB3IfoTradedProductImpl.class, double.class, boolean.class },
                    l_ifoEstimateDeliveryAmountCalcResult);
            
            WEB3TPTradingPowerResult l_tpTradingPowerResult = new WEB3TPTradingPowerResult();
            l_tpTradingPowerResult.setResultFlg(true);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.tradingpower.WEB3TPTradingPowerServiceImpl", "validateTradingPower",
                new Class[] {WEB3GentradeSubAccount.class, Object[].class, Object[].class, OrderTypeEnum.class,boolean.class},
                l_tpTradingPowerResult);
                    
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(333812512203L);
            TestDBUtility.insertWithDel(l_mainAccountParams);
            
            OrderSubmissionResult l_orderSubmissionResult = new OrderSubmissionResult(l_processingResult);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.ifo.WEB3FuturesOrderManagerImpl", "submitOpenContractOrder", new Class[]
                { SubAccount.class, IfoOpenContractOrderSpec.class, long.class, String.class, boolean.class },
                l_orderSubmissionResult);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue("webbroker3.triggerorder.WEB3ToSuccOrderManagerImpl",
                "setOrderedToOrderUnit", new Class[]
                {ProductTypeEnum.class, long.class},
                null);
            
            TestDBUtility.deleteAll(TraderRow.TYPE);
            TraderParams l_traderParams = TestDBUtility.getTraderRow();
            TestDBUtility.insertWithDel(l_traderParams);
                    
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.triggerorder.base.service.delegate.stdimpls.WEB3ToSuccReservationIfoOrderUpdateServiceImpl",
                "invalidateOrderUnit", new Class[]
                {RsvIfoOrderUnitRow.class, String.class},
                null);
                    
            l_impl.submitFuturesOpenContractOrder(l_toSuccIfoOrderUnitImpl);
            log.exiting(TEST_END + STR_METHOD_NAME);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
    }
    
    /**
     * 正常終了
     * 
     * l_rsvIfoOrderUnitRow.getTraderIdIsNull() == true
     * l_rsvIfoOrderUnitRow.getFirstOrderUnitIdIsNull() == true
     * BooleanEnum.TRUE.equals(l_rsvIfoOrderUnitRow.getEveningSessionCarryoverFlag()) == false
     * l_orderValidationResult.getProcessingResult().isFailedResult() == false
     * l_tradingPowerResult.isResultFlg() == false
     */
    public void testSubmitFuturesOpenContractOrder_C0003()
    {
        final String STR_METHOD_NAME = "testSubmitFuturesOpenContractOrder_C0003()";
        log.entering(STR_METHOD_NAME);
        
        WEB3ToSuccIfoOrderUnitServiceImpl l_impl =
            new WEB3ToSuccIfoOrderUnitServiceImpl();

        RsvIfoOrderUnitParams l_rsvIfoOrderUnitParams =
            TestDBUtility.getRsvIfoOrderUnitRow();
        l_rsvIfoOrderUnitParams.setBizDate("20080512");
        l_rsvIfoOrderUnitParams.setMarketId(3303L);
        l_rsvIfoOrderUnitParams.setProductId(3304148080000L);
        WEB3ToSuccIfoOrderUnitImpl l_toSuccIfoOrderUnitImpl =
            new WEB3ToSuccIfoOrderUnitImpl(l_rsvIfoOrderUnitParams);
        try
        {
            TestDBUtility.deleteAll(TradingTimeRow.TYPE);
            TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams.setInstitutionCode("0D");
            l_tradingTimeParams.setBranchCode("123");
            l_tradingTimeParams.setMarketCode("N1");
            l_tradingTimeParams.setTradingTimeType("01");
            l_tradingTimeParams.setProductCode("0");
            l_tradingTimeParams.setBizDateType("1");
            l_tradingTimeParams.setBizdateCalcParameter("0");
            TestDBUtility.insertWithDel(l_tradingTimeParams);
            
            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setAccountId(333812512203L);
            l_subAccountParams.setSubAccountId(33381251220301L);
            TestDBUtility.insertWithDel(l_subAccountParams);
            
            TestDBUtility.deleteAll(InstitutionRow.TYPE);
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            TestDBUtility.insertWithDel(l_institutionParams);
            
            TestDBUtility.deleteAll(MarketRow.TYPE);
            MarketParams l_marketParams = TestDBUtility.getMarketRow();
            TestDBUtility.insertWithDel(l_marketParams);
            
            TestDBUtility.deleteAll(ProductRow.TYPE);
            ProductParams l_productParams = TestDBUtility.getProductRow();
            l_productParams.setProductType(ProductTypeEnum.IFO);
            TestDBUtility.insertWithDel(l_productParams);
            
            TestDBUtility.deleteAll(IfoProductRow.TYPE);
            IfoProductParams l_ifoProductParams = TestDBUtility.getIfoProductRow();
            l_ifoProductParams.setProductId(3304148080000L);
            TestDBUtility.insertWithDel(l_ifoProductParams);
            
            ProcessingResult l_processingResult = ProcessingResult.newSuccessResultInstance();
            NewOrderValidationResult l_newOrderValidationResult =
                new NewOrderValidationResult(l_processingResult);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.ifo.WEB3FuturesOrderManagerImpl", "validateFuturesOpenContractOrder", new Class[]
                { WEB3GentradeSubAccount.class, WEB3IfoOpenContractOrderSpec.class, IfoOrderUnit.class },
                l_newOrderValidationResult);
            
            WEB3IfoEstimateDeliveryAmountCalcResult l_ifoEstimateDeliveryAmountCalcResult =
                new WEB3IfoEstimateDeliveryAmountCalcResult();
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.ifo.WEB3FuturesOrderManagerImpl",
                "calcEstimatePrice",
                new Class[]
                { WEB3GentradeCommission.class, double.class, WEB3GentradeSubAccount.class,
                    WEB3IfoTradedProductImpl.class, double.class, boolean.class },
                    l_ifoEstimateDeliveryAmountCalcResult);
            
            WEB3TPTradingPowerResult l_tpTradingPowerResult = new WEB3TPTradingPowerResult();
            l_tpTradingPowerResult.setResultFlg(false);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.tradingpower.WEB3TPTradingPowerServiceImpl", "validateTradingPower",
                new Class[] {WEB3GentradeSubAccount.class, Object[].class, Object[].class, OrderTypeEnum.class,boolean.class},
                l_tpTradingPowerResult);
                    
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(333812512203L);
            TestDBUtility.insertWithDel(l_mainAccountParams);
            
            OrderSubmissionResult l_orderSubmissionResult = new OrderSubmissionResult(l_processingResult);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.ifo.WEB3FuturesOrderManagerImpl", "submitOpenContractOrder", new Class[]
                { SubAccount.class, IfoOpenContractOrderSpec.class, long.class, String.class, boolean.class },
                l_orderSubmissionResult);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue("webbroker3.triggerorder.WEB3ToSuccOrderManagerImpl",
                "setOrderedToOrderUnit", new Class[]
                {ProductTypeEnum.class, long.class},
                null);
                    
            l_impl.submitFuturesOpenContractOrder(l_toSuccIfoOrderUnitImpl);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
   
    /**
     * 正常終了
     * 
     * l_rsvIfoOrderUnitRow.getTraderIdIsNull() == true
     * l_rsvIfoOrderUnitRow.getFirstOrderUnitIdIsNull() == true
     * BooleanEnum.TRUE.equals(l_rsvIfoOrderUnitRow.getEveningSessionCarryoverFlag()) == false
     * l_orderValidationResult.getProcessingResult().isFailedResult() == false
     * l_tradingPowerResult.isResultFlg() == true
     * l_orderSubmissionResult.getProcessingResult().isSuccessfulResult() == false
     * l_orderSpec[0].isBuyToOpenOrder() == false
     */
    public void testSubmitFuturesOpenContractOrder_C0004()
    {
        final String STR_METHOD_NAME = "testSubmitFuturesOpenContractOrder_C0004()";
        log.entering(STR_METHOD_NAME);
        
        WEB3ToSuccIfoOrderUnitServiceImpl l_impl =
            new WEB3ToSuccIfoOrderUnitServiceImpl();

        RsvIfoOrderUnitParams l_rsvIfoOrderUnitParams =
            TestDBUtility.getRsvIfoOrderUnitRow();
        l_rsvIfoOrderUnitParams.setBizDate("20080512");
        l_rsvIfoOrderUnitParams.setMarketId(3303L);
        l_rsvIfoOrderUnitParams.setProductId(3304148080000L);
        l_rsvIfoOrderUnitParams.setOrderType(OrderTypeEnum.IDX_OPTIONS_SELL_TO_OPEN);
        WEB3ToSuccIfoOrderUnitImpl l_toSuccIfoOrderUnitImpl =
            new WEB3ToSuccIfoOrderUnitImpl(l_rsvIfoOrderUnitParams);
        try
        {
            TestDBUtility.deleteAll(TradingTimeRow.TYPE);
            TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams.setInstitutionCode("0D");
            l_tradingTimeParams.setBranchCode("123");
            l_tradingTimeParams.setMarketCode("N1");
            l_tradingTimeParams.setTradingTimeType("01");
            l_tradingTimeParams.setProductCode("0");
            l_tradingTimeParams.setBizDateType("1");
            l_tradingTimeParams.setBizdateCalcParameter("0");
            TestDBUtility.insertWithDel(l_tradingTimeParams);
            
            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setAccountId(333812512203L);
            l_subAccountParams.setSubAccountId(33381251220301L);
            TestDBUtility.insertWithDel(l_subAccountParams);
            
            TestDBUtility.deleteAll(InstitutionRow.TYPE);
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            TestDBUtility.insertWithDel(l_institutionParams);
            
            TestDBUtility.deleteAll(MarketRow.TYPE);
            MarketParams l_marketParams = TestDBUtility.getMarketRow();
            TestDBUtility.insertWithDel(l_marketParams);
            
            TestDBUtility.deleteAll(ProductRow.TYPE);
            ProductParams l_productParams = TestDBUtility.getProductRow();
            l_productParams.setProductType(ProductTypeEnum.IFO);
            TestDBUtility.insertWithDel(l_productParams);
            
            TestDBUtility.deleteAll(IfoProductRow.TYPE);
            IfoProductParams l_ifoProductParams = TestDBUtility.getIfoProductRow();
            l_ifoProductParams.setProductId(3304148080000L);
            TestDBUtility.insertWithDel(l_ifoProductParams);
            
            ProcessingResult l_processingResult = ProcessingResult.newSuccessResultInstance();
            NewOrderValidationResult l_newOrderValidationResult =
                new NewOrderValidationResult(l_processingResult);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.ifo.WEB3FuturesOrderManagerImpl", "validateFuturesOpenContractOrder", new Class[]
                { WEB3GentradeSubAccount.class, WEB3IfoOpenContractOrderSpec.class, IfoOrderUnit.class },
                l_newOrderValidationResult);
            
            WEB3IfoEstimateDeliveryAmountCalcResult l_ifoEstimateDeliveryAmountCalcResult =
                new WEB3IfoEstimateDeliveryAmountCalcResult();
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.ifo.WEB3FuturesOrderManagerImpl",
                "calcEstimatePrice",
                new Class[]
                { WEB3GentradeCommission.class, double.class, WEB3GentradeSubAccount.class,
                    WEB3IfoTradedProductImpl.class, double.class, boolean.class },
                    l_ifoEstimateDeliveryAmountCalcResult);
            
            WEB3TPTradingPowerResult l_tpTradingPowerResult = new WEB3TPTradingPowerResult();
            l_tpTradingPowerResult.setResultFlg(true);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.tradingpower.WEB3TPTradingPowerServiceImpl", "validateTradingPower",
                new Class[] {WEB3GentradeSubAccount.class, Object[].class, Object[].class, OrderTypeEnum.class,boolean.class},
                l_tpTradingPowerResult);
                    
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(333812512203L);
            TestDBUtility.insertWithDel(l_mainAccountParams);
            
            ProcessingResult l_processingResult1 = ProcessingResult.newFailedResultInstance(WEB3ErrorCatalog.SYSTEM_ERROR_80005);
            OrderSubmissionResult l_orderSubmissionResult = new OrderSubmissionResult(l_processingResult1);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.ifo.WEB3FuturesOrderManagerImpl", "submitOpenContractOrder", new Class[]
                { SubAccount.class, IfoOpenContractOrderSpec.class, long.class, String.class, boolean.class },
                l_orderSubmissionResult);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue("webbroker3.triggerorder.WEB3ToSuccOrderManagerImpl",
                "setOrderedToOrderUnit", new Class[]
                {ProductTypeEnum.class, long.class},
                null);
                    
            l_impl.submitFuturesOpenContractOrder(l_toSuccIfoOrderUnitImpl);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    /**
     * 正常終了
     * 
     * l_rsvIfoOrderUnitRow.getTraderIdIsNull() == true
     * l_rsvIfoOrderUnitRow.getFirstOrderUnitIdIsNull() == true
     * BooleanEnum.TRUE.equals(l_rsvIfoOrderUnitRow.getEveningSessionCarryoverFlag()) == false
     * l_orderValidationResult.getProcessingResult().isFailedResult() == false
     * l_tradingPowerResult.isResultFlg() == true
     * l_orderSubmissionResult.getProcessingResult().isSuccessfulResult() == true
     * l_orderSpec[0].isBuyToOpenOrder() == true
     */
    public void testSubmitOptionsOpenContractOrder_C0001()
    {
        final String STR_METHOD_NAME = "testSubmitOptionsOpenContractOrder_C0001()";
        log.entering(STR_METHOD_NAME);
        
        WEB3ToSuccIfoOrderUnitServiceImpl l_impl =
            new WEB3ToSuccIfoOrderUnitServiceImpl();

        RsvIfoOrderUnitParams l_rsvIfoOrderUnitParams =
            TestDBUtility.getRsvIfoOrderUnitRow();
        l_rsvIfoOrderUnitParams.setBizDate("20080512");
        l_rsvIfoOrderUnitParams.setMarketId(3303L);
        l_rsvIfoOrderUnitParams.setProductId(3304148080000L);
//        l_rsvIfoOrderUnitParams.setOrderType(OrderTypeEnum.IDX_OPTIONS_SELL_TO_OPEN);
        WEB3ToSuccIfoOrderUnitImpl l_toSuccIfoOrderUnitImpl =
            new WEB3ToSuccIfoOrderUnitImpl(l_rsvIfoOrderUnitParams);
        try
        {
            TestDBUtility.deleteAll(TradingTimeRow.TYPE);
            TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams.setInstitutionCode("0D");
            l_tradingTimeParams.setBranchCode("123");
            l_tradingTimeParams.setMarketCode("N1");
            l_tradingTimeParams.setTradingTimeType("01");
            l_tradingTimeParams.setProductCode("0");
            l_tradingTimeParams.setBizDateType("1");
            l_tradingTimeParams.setBizdateCalcParameter("0");
            TestDBUtility.insertWithDel(l_tradingTimeParams);
            
            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setAccountId(333812512203L);
            l_subAccountParams.setSubAccountId(33381251220301L);
            TestDBUtility.insertWithDel(l_subAccountParams);
            
            TestDBUtility.deleteAll(InstitutionRow.TYPE);
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            TestDBUtility.insertWithDel(l_institutionParams);
            
            TestDBUtility.deleteAll(MarketRow.TYPE);
            MarketParams l_marketParams = TestDBUtility.getMarketRow();
            TestDBUtility.insertWithDel(l_marketParams);
            
            TestDBUtility.deleteAll(ProductRow.TYPE);
            ProductParams l_productParams = TestDBUtility.getProductRow();
            l_productParams.setProductType(ProductTypeEnum.IFO);
            TestDBUtility.insertWithDel(l_productParams);
            
            TestDBUtility.deleteAll(IfoProductRow.TYPE);
            IfoProductParams l_ifoProductParams = TestDBUtility.getIfoProductRow();
            l_ifoProductParams.setProductId(3304148080000L);
            TestDBUtility.insertWithDel(l_ifoProductParams);
            
            ProcessingResult l_processingResult = ProcessingResult.newSuccessResultInstance();
            NewOrderValidationResult l_newOrderValidationResult =
                new NewOrderValidationResult(l_processingResult);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.ifo.WEB3FuturesOrderManagerImpl", "validateFuturesOpenContractOrder", new Class[]
                { WEB3GentradeSubAccount.class, WEB3IfoOpenContractOrderSpec.class, IfoOrderUnit.class },
                l_newOrderValidationResult);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.ifo.WEB3FuturesOrderManagerImpl", "validateOpenContractOrder", new Class[]
                { WEB3GentradeSubAccount.class, WEB3IfoOpenContractOrderSpec.class, IfoOrderUnit.class },
                l_newOrderValidationResult);
            
            WEB3IfoEstimateDeliveryAmountCalcResult l_ifoEstimateDeliveryAmountCalcResult =
                new WEB3IfoEstimateDeliveryAmountCalcResult();
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.ifo.WEB3FuturesOrderManagerImpl",
                "calcEstimateDeliveryAmount",
                new Class[]
                {WEB3GentradeCommission.class, double.class, SubAccount.class, WEB3IfoTradedProductImpl.class,
                 double.class, SideEnum.class, boolean.class, boolean.class  },
                 l_ifoEstimateDeliveryAmountCalcResult);
            
            WEB3TPTradingPowerResult l_tpTradingPowerResult = new WEB3TPTradingPowerResult();
            l_tpTradingPowerResult.setResultFlg(true);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.tradingpower.WEB3TPTradingPowerServiceImpl", "validateTradingPower",
                new Class[] {WEB3GentradeSubAccount.class, Object[].class, Object[].class, OrderTypeEnum.class,boolean.class},
                l_tpTradingPowerResult);
                    
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(333812512203L);
            TestDBUtility.insertWithDel(l_mainAccountParams);
            
            ProcessingResult l_processingResult1 = ProcessingResult.newSuccessResultInstance();
            OrderSubmissionResult l_orderSubmissionResult = new OrderSubmissionResult(l_processingResult1);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.ifo.WEB3FuturesOrderManagerImpl", "submitOpenContractOrder", new Class[]
                { SubAccount.class, IfoOpenContractOrderSpec.class, long.class, String.class, boolean.class },
                l_orderSubmissionResult);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue("webbroker3.triggerorder.WEB3ToSuccOrderManagerImpl",
                "setOrderedToOrderUnit", new Class[]
                {ProductTypeEnum.class, long.class},
                null);
                    
            l_impl.submitOptionsOpenContractOrder(l_toSuccIfoOrderUnitImpl);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    /**
     * 正常終了
     * 
     * l_rsvIfoOrderUnitRow.getTraderIdIsNull() == false
     * l_rsvIfoOrderUnitRow.getFirstOrderUnitIdIsNull() == false
     * BooleanEnum.TRUE.equals(l_rsvIfoOrderUnitRow.getEveningSessionCarryoverFlag()) == true
     * l_orderValidationResult.getProcessingResult().isFailedResult() == true
     */
    public void testSubmitOptionsOpenContractOrder_C0002()
    {
        final String STR_METHOD_NAME = "testSubmitOptionsOpenContractOrder_C0002()";
        log.entering(STR_METHOD_NAME);
        
        WEB3ToSuccIfoOrderUnitServiceImpl l_impl =
            new WEB3ToSuccIfoOrderUnitServiceImpl();

        RsvIfoOrderUnitParams l_rsvIfoOrderUnitParams =
            TestDBUtility.getRsvIfoOrderUnitRow();
        l_rsvIfoOrderUnitParams.setBizDate("20080512");
        l_rsvIfoOrderUnitParams.setMarketId(3303L);
        l_rsvIfoOrderUnitParams.setProductId(3304148080000L);
        l_rsvIfoOrderUnitParams.setOrderType(OrderTypeEnum.IDX_OPTIONS_SELL_TO_OPEN);
        WEB3ToSuccIfoOrderUnitImpl l_toSuccIfoOrderUnitImpl =
            new WEB3ToSuccIfoOrderUnitImpl(l_rsvIfoOrderUnitParams);
        try
        {
            TestDBUtility.deleteAll(TradingTimeRow.TYPE);
            TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams.setInstitutionCode("0D");
            l_tradingTimeParams.setBranchCode("123");
            l_tradingTimeParams.setMarketCode("N1");
            l_tradingTimeParams.setTradingTimeType("01");
            l_tradingTimeParams.setProductCode("0");
            l_tradingTimeParams.setBizDateType("1");
            l_tradingTimeParams.setBizdateCalcParameter("0");
            TestDBUtility.insertWithDel(l_tradingTimeParams);
            
            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setAccountId(333812512203L);
            l_subAccountParams.setSubAccountId(33381251220301L);
            TestDBUtility.insertWithDel(l_subAccountParams);
            
            TestDBUtility.deleteAll(InstitutionRow.TYPE);
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            TestDBUtility.insertWithDel(l_institutionParams);
            
            TestDBUtility.deleteAll(MarketRow.TYPE);
            MarketParams l_marketParams = TestDBUtility.getMarketRow();
            TestDBUtility.insertWithDel(l_marketParams);
            
            TestDBUtility.deleteAll(ProductRow.TYPE);
            ProductParams l_productParams = TestDBUtility.getProductRow();
            l_productParams.setProductType(ProductTypeEnum.IFO);
            TestDBUtility.insertWithDel(l_productParams);
            
            TestDBUtility.deleteAll(IfoProductRow.TYPE);
            IfoProductParams l_ifoProductParams = TestDBUtility.getIfoProductRow();
            l_ifoProductParams.setProductId(3304148080000L);
            TestDBUtility.insertWithDel(l_ifoProductParams);
            
            ProcessingResult l_processingResult = ProcessingResult.newFailedResultInstance(WEB3ErrorCatalog.SYSTEM_ERROR_80005);
            NewOrderValidationResult l_newOrderValidationResult =
                new NewOrderValidationResult(l_processingResult);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.ifo.WEB3FuturesOrderManagerImpl", "validateOpenContractOrder", new Class[]
                { WEB3GentradeSubAccount.class, WEB3IfoOpenContractOrderSpec.class, IfoOrderUnit.class },
                l_newOrderValidationResult);
            
            WEB3IfoEstimateDeliveryAmountCalcResult l_ifoEstimateDeliveryAmountCalcResult =
                new WEB3IfoEstimateDeliveryAmountCalcResult();
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.ifo.WEB3FuturesOrderManagerImpl",
                "calcEstimateDeliveryAmount",
                new Class[]
                {WEB3GentradeCommission.class, double.class, SubAccount.class, WEB3IfoTradedProductImpl.class,
                 double.class, SideEnum.class, boolean.class, boolean.class  },
                 l_ifoEstimateDeliveryAmountCalcResult);
            
            WEB3TPTradingPowerResult l_tpTradingPowerResult = new WEB3TPTradingPowerResult();
            l_tpTradingPowerResult.setResultFlg(true);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.tradingpower.WEB3TPTradingPowerServiceImpl", "validateTradingPower",
                new Class[] {WEB3GentradeSubAccount.class, Object[].class, Object[].class, OrderTypeEnum.class,boolean.class},
                l_tpTradingPowerResult);
                    
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(333812512203L);
            TestDBUtility.insertWithDel(l_mainAccountParams);
            
            ProcessingResult l_processingResult1 = ProcessingResult.newSuccessResultInstance();
            OrderSubmissionResult l_orderSubmissionResult = new OrderSubmissionResult(l_processingResult1);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.ifo.WEB3FuturesOrderManagerImpl", "submitOpenContractOrder", new Class[]
                { SubAccount.class, IfoOpenContractOrderSpec.class, long.class, String.class, boolean.class },
                l_orderSubmissionResult);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue("webbroker3.triggerorder.WEB3ToSuccOrderManagerImpl",
                "setOrderedToOrderUnit", new Class[]
                {ProductTypeEnum.class, long.class},
                null);
                    
            l_impl.submitOptionsOpenContractOrder(l_toSuccIfoOrderUnitImpl);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    /**
     * 正常終了
     * 
     * l_rsvIfoOrderUnitRow.getTraderIdIsNull() == true
     * l_rsvIfoOrderUnitRow.getFirstOrderUnitIdIsNull() == true
     * BooleanEnum.TRUE.equals(l_rsvIfoOrderUnitRow.getEveningSessionCarryoverFlag()) == false
     * l_orderValidationResult.getProcessingResult().isFailedResult() == false
     * l_tradingPowerResult.isResultFlg() == false
     */
    public void testSubmitOptionsOpenContractOrder_C0003()
    {
        final String STR_METHOD_NAME = "testSubmitOptionsOpenContractOrder_C0003()";
        log.entering(STR_METHOD_NAME);
        
        WEB3ToSuccIfoOrderUnitServiceImpl l_impl =
            new WEB3ToSuccIfoOrderUnitServiceImpl();

        RsvIfoOrderUnitParams l_rsvIfoOrderUnitParams =
            TestDBUtility.getRsvIfoOrderUnitRow();
        l_rsvIfoOrderUnitParams.setBizDate("20080512");
        l_rsvIfoOrderUnitParams.setMarketId(3303L);
        l_rsvIfoOrderUnitParams.setProductId(3304148080000L);
        l_rsvIfoOrderUnitParams.setOrderType(OrderTypeEnum.IDX_OPTIONS_SELL_TO_OPEN);
        WEB3ToSuccIfoOrderUnitImpl l_toSuccIfoOrderUnitImpl =
            new WEB3ToSuccIfoOrderUnitImpl(l_rsvIfoOrderUnitParams);
        try
        {
            TestDBUtility.deleteAll(TradingTimeRow.TYPE);
            TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams.setInstitutionCode("0D");
            l_tradingTimeParams.setBranchCode("123");
            l_tradingTimeParams.setMarketCode("N1");
            l_tradingTimeParams.setTradingTimeType("01");
            l_tradingTimeParams.setProductCode("0");
            l_tradingTimeParams.setBizDateType("1");
            l_tradingTimeParams.setBizdateCalcParameter("0");
            TestDBUtility.insertWithDel(l_tradingTimeParams);
            
            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setAccountId(333812512203L);
            l_subAccountParams.setSubAccountId(33381251220301L);
            TestDBUtility.insertWithDel(l_subAccountParams);
            
            TestDBUtility.deleteAll(InstitutionRow.TYPE);
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            TestDBUtility.insertWithDel(l_institutionParams);
            
            TestDBUtility.deleteAll(MarketRow.TYPE);
            MarketParams l_marketParams = TestDBUtility.getMarketRow();
            TestDBUtility.insertWithDel(l_marketParams);
            
            TestDBUtility.deleteAll(ProductRow.TYPE);
            ProductParams l_productParams = TestDBUtility.getProductRow();
            l_productParams.setProductType(ProductTypeEnum.IFO);
            TestDBUtility.insertWithDel(l_productParams);
            
            TestDBUtility.deleteAll(IfoProductRow.TYPE);
            IfoProductParams l_ifoProductParams = TestDBUtility.getIfoProductRow();
            l_ifoProductParams.setProductId(3304148080000L);
            TestDBUtility.insertWithDel(l_ifoProductParams);
            
            ProcessingResult l_processingResult = ProcessingResult.newSuccessResultInstance();
            NewOrderValidationResult l_newOrderValidationResult =
                new NewOrderValidationResult(l_processingResult);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.ifo.WEB3FuturesOrderManagerImpl", "validateOpenContractOrder", new Class[]
                { WEB3GentradeSubAccount.class, WEB3IfoOpenContractOrderSpec.class, IfoOrderUnit.class },
                l_newOrderValidationResult);
            
            WEB3IfoEstimateDeliveryAmountCalcResult l_ifoEstimateDeliveryAmountCalcResult =
                new WEB3IfoEstimateDeliveryAmountCalcResult();
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.ifo.WEB3FuturesOrderManagerImpl",
                "calcEstimateDeliveryAmount",
                new Class[]
                {WEB3GentradeCommission.class, double.class, SubAccount.class, WEB3IfoTradedProductImpl.class,
                 double.class, SideEnum.class, boolean.class, boolean.class  },
                 l_ifoEstimateDeliveryAmountCalcResult);
            
            WEB3TPTradingPowerResult l_tpTradingPowerResult = new WEB3TPTradingPowerResult();
            l_tpTradingPowerResult.setResultFlg(false);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.tradingpower.WEB3TPTradingPowerServiceImpl", "validateTradingPower",
                new Class[] {WEB3GentradeSubAccount.class, Object[].class, Object[].class, OrderTypeEnum.class,boolean.class},
                l_tpTradingPowerResult);
                    
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(333812512203L);
            TestDBUtility.insertWithDel(l_mainAccountParams);
            
            ProcessingResult l_processingResult1 = ProcessingResult.newSuccessResultInstance();
            OrderSubmissionResult l_orderSubmissionResult = new OrderSubmissionResult(l_processingResult1);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.ifo.WEB3FuturesOrderManagerImpl", "submitOpenContractOrder", new Class[]
                { SubAccount.class, IfoOpenContractOrderSpec.class, long.class, String.class, boolean.class },
                l_orderSubmissionResult);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue("webbroker3.triggerorder.WEB3ToSuccOrderManagerImpl",
                "setOrderedToOrderUnit", new Class[]
                {ProductTypeEnum.class, long.class},
                null);
                    
            l_impl.submitOptionsOpenContractOrder(l_toSuccIfoOrderUnitImpl);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    /**
     * 正常終了
     * 
     * l_rsvIfoOrderUnitRow.getTraderIdIsNull() == true
     * l_rsvIfoOrderUnitRow.getFirstOrderUnitIdIsNull() == true
     * BooleanEnum.TRUE.equals(l_rsvIfoOrderUnitRow.getEveningSessionCarryoverFlag()) == false
     * l_orderValidationResult.getProcessingResult().isFailedResult() == false
     * l_tradingPowerResult.isResultFlg() == true
     * l_orderSubmissionResult.getProcessingResult().isSuccessfulResult() == false
     * l_orderSpec[0].isBuyToOpenOrder() == false
     */
    public void testSubmitOptionsOpenContractOrder_C0004()
    {
        final String STR_METHOD_NAME = "testSubmitOptionsOpenContractOrder_C0004()";
        log.entering(STR_METHOD_NAME);
        
        WEB3ToSuccIfoOrderUnitServiceImpl l_impl =
            new WEB3ToSuccIfoOrderUnitServiceImpl();

        RsvIfoOrderUnitParams l_rsvIfoOrderUnitParams =
            TestDBUtility.getRsvIfoOrderUnitRow();
        l_rsvIfoOrderUnitParams.setBizDate("20080512");
        l_rsvIfoOrderUnitParams.setMarketId(3303L);
        l_rsvIfoOrderUnitParams.setProductId(3304148080000L);
        l_rsvIfoOrderUnitParams.setOrderType(OrderTypeEnum.IDX_OPTIONS_SELL_TO_OPEN);
        WEB3ToSuccIfoOrderUnitImpl l_toSuccIfoOrderUnitImpl =
            new WEB3ToSuccIfoOrderUnitImpl(l_rsvIfoOrderUnitParams);
        try
        {
            TestDBUtility.deleteAll(TradingTimeRow.TYPE);
            TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams.setInstitutionCode("0D");
            l_tradingTimeParams.setBranchCode("123");
            l_tradingTimeParams.setMarketCode("N1");
            l_tradingTimeParams.setTradingTimeType("01");
            l_tradingTimeParams.setProductCode("0");
            l_tradingTimeParams.setBizDateType("1");
            l_tradingTimeParams.setBizdateCalcParameter("0");
            TestDBUtility.insertWithDel(l_tradingTimeParams);
            
            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setAccountId(333812512203L);
            l_subAccountParams.setSubAccountId(33381251220301L);
            TestDBUtility.insertWithDel(l_subAccountParams);
            
            TestDBUtility.deleteAll(InstitutionRow.TYPE);
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            TestDBUtility.insertWithDel(l_institutionParams);
            
            TestDBUtility.deleteAll(MarketRow.TYPE);
            MarketParams l_marketParams = TestDBUtility.getMarketRow();
            TestDBUtility.insertWithDel(l_marketParams);
            
            TestDBUtility.deleteAll(ProductRow.TYPE);
            ProductParams l_productParams = TestDBUtility.getProductRow();
            l_productParams.setProductType(ProductTypeEnum.IFO);
            TestDBUtility.insertWithDel(l_productParams);
            
            TestDBUtility.deleteAll(IfoProductRow.TYPE);
            IfoProductParams l_ifoProductParams = TestDBUtility.getIfoProductRow();
            l_ifoProductParams.setProductId(3304148080000L);
            TestDBUtility.insertWithDel(l_ifoProductParams);
            
            ProcessingResult l_processingResult = ProcessingResult.newSuccessResultInstance();
            NewOrderValidationResult l_newOrderValidationResult =
                new NewOrderValidationResult(l_processingResult);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.ifo.WEB3FuturesOrderManagerImpl", "validateFuturesOpenContractOrder", new Class[]
                { WEB3GentradeSubAccount.class, WEB3IfoOpenContractOrderSpec.class, IfoOrderUnit.class },
                l_newOrderValidationResult);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.ifo.WEB3FuturesOrderManagerImpl", "validateOpenContractOrder", new Class[]
                { WEB3GentradeSubAccount.class, WEB3IfoOpenContractOrderSpec.class, IfoOrderUnit.class },
                l_newOrderValidationResult);
            
            WEB3IfoEstimateDeliveryAmountCalcResult l_ifoEstimateDeliveryAmountCalcResult =
                new WEB3IfoEstimateDeliveryAmountCalcResult();
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.ifo.WEB3FuturesOrderManagerImpl",
                "calcEstimatePrice",
                new Class[]
                { WEB3GentradeCommission.class, double.class, WEB3GentradeSubAccount.class,
                    WEB3IfoTradedProductImpl.class, double.class, boolean.class },
                    l_ifoEstimateDeliveryAmountCalcResult);
            
            WEB3TPTradingPowerResult l_tpTradingPowerResult = new WEB3TPTradingPowerResult();
            l_tpTradingPowerResult.setResultFlg(true);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.tradingpower.WEB3TPTradingPowerServiceImpl", "validateTradingPower",
                new Class[] {WEB3GentradeSubAccount.class, Object[].class, Object[].class, OrderTypeEnum.class,boolean.class},
                l_tpTradingPowerResult);
                    
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(333812512203L);
            TestDBUtility.insertWithDel(l_mainAccountParams);
            
            ProcessingResult l_processingResult1 = ProcessingResult.newFailedResultInstance(WEB3ErrorCatalog.SYSTEM_ERROR_80005);
            OrderSubmissionResult l_orderSubmissionResult = new OrderSubmissionResult(l_processingResult1);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.ifo.WEB3FuturesOrderManagerImpl", "submitOpenContractOrder", new Class[]
                { SubAccount.class, IfoOpenContractOrderSpec.class, long.class, String.class, boolean.class },
                l_orderSubmissionResult);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue("webbroker3.triggerorder.WEB3ToSuccOrderManagerImpl",
                "setOrderedToOrderUnit", new Class[]
                {ProductTypeEnum.class, long.class},
                null);
                    
            l_impl.submitOptionsOpenContractOrder(l_toSuccIfoOrderUnitImpl);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    //先物OP予約建玉返済指定情報一覧( )==null
    public void testSubmitOptionsSettleContractOrder_C0001()
	{
		final String STR_METHOD_NAME = "testSubmitOptionsSettleContractOrder_C0001()";
        log.entering(TEST_START + STR_METHOD_NAME);
        WEB3ToSuccIfoOrderUnitServiceImpl l_service= new WEB3ToSuccIfoOrderUnitServiceImpl();
        try
        {
        	TestDBUtility.deleteAll(RsvIfoOrderUnitRow.TYPE);
            RsvIfoOrderUnitParams  l_rsvIfoOrderUnitParams =TestDBUtility.getRsvIfoOrderUnitRow();
            l_rsvIfoOrderUnitParams.setBizDate("20080512");
            l_rsvIfoOrderUnitParams.setMarketId(3303L);
            l_rsvIfoOrderUnitParams.setProductId(3304148080000L);
            TestDBUtility.insertWithDel(l_rsvIfoOrderUnitParams);
            
            TestDBUtility.deleteAll(TradingTimeRow.TYPE);
            TradingTimeParams  l_tradingTimeParams =TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams.setInstitutionCode("0D");
            l_tradingTimeParams.setBranchCode("123");
            l_tradingTimeParams.setMarketCode("N1");
            l_tradingTimeParams.setTradingTimeType("01");
            l_tradingTimeParams.setProductCode("0");
            l_tradingTimeParams.setBizDateType("1");
            TestDBUtility.insertWithDel(l_tradingTimeParams);
            
            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setAccountId(333812512203L);
            l_subAccountParams.setSubAccountId(33381251220301L);
            TestDBUtility.insertWithDel(l_subAccountParams);
            
            ThreadLocalSystemAttributesRegistry.setAttribute(
                "xblocks.gtl.attributes.systemtimestamp",
                new Timestamp(WEB3DateUtility.getDate("20080509", "yyyyMMdd").getTime()));
            
            ThreadLocalSystemAttributesRegistry.setAttribute(
            	"web3.attributes.basetimestampfororderbizdate", null);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.triggerorder.base.service.delegate.stdimpls.WEB3ToSuccReservationIfoOrderUpdateServiceImpl",
                    "invalidateOrderUnit", new Class[]
                    {RsvIfoOrderUnitRow.class,String.class},new WEB3BaseException(WEB3ErrorCatalog.BUSINESS_ERROR_02339,""));
            
        	WEB3ToSuccIfoOrderUnitImplForTest l_rsvIfoOrderUnit= new WEB3ToSuccIfoOrderUnitImplForTest(l_rsvIfoOrderUnitParams);

        	l_service.submitOptionsSettleContractOrder(l_rsvIfoOrderUnit);
            fail();
        }
        catch(WEB3BaseException l_ex)
        {
            log.error("", l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02339, l_ex.getErrorInfo());

        }
        catch(Exception l_ex)
        {
            log.error("", l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
	}

	//分岐フロー：　@予約注文単位.取引者ID≠nullの場合のみ
	public void testSubmitOptionsSettleContractOrder_C0002()
	{
		final String STR_METHOD_NAME = "testSubmitOptionsSettleContractOrder_C0002()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        WEB3ToSuccIfoOrderUnitServiceImplForTest l_service =new WEB3ToSuccIfoOrderUnitServiceImplForTest();
        try
        {
            
            TestDBUtility.deleteAll(TraderRow.TYPE);
            TraderParams l_traderParams  =TestDBUtility.getTraderRow();
            l_traderParams.setTraderId(1000);
            TestDBUtility.insertWithDel(l_traderParams);
            
            TestDBUtility.deleteAll(RsvIfoOrderUnitRow.TYPE);
            RsvIfoOrderUnitParams  l_rsvIfoOrderUnitParams =TestDBUtility.getRsvIfoOrderUnitRow();
            l_rsvIfoOrderUnitParams.setBizDate("20080512");
            l_rsvIfoOrderUnitParams.setMarketId(3303L);
            l_rsvIfoOrderUnitParams.setProductId(3304148080000L);
            l_rsvIfoOrderUnitParams.setTraderId(1000);
            //l_rsvIfoOrderUnitParams.setFirstOrderUnitId(1000);
            TestDBUtility.insertWithDel(l_rsvIfoOrderUnitParams);
            
            TestDBUtility.deleteAll(TradingTimeRow.TYPE);
            TradingTimeParams  l_tradingTimeParams =TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams.setInstitutionCode("0D");
            l_tradingTimeParams.setBranchCode("123");
            l_tradingTimeParams.setMarketCode("N1");
            l_tradingTimeParams.setTradingTimeType("01");
            l_tradingTimeParams.setProductCode("0");
            l_tradingTimeParams.setBizDateType("1");
            TestDBUtility.insertWithDel(l_tradingTimeParams);
            
            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setAccountId(333812512203L);
            l_subAccountParams.setSubAccountId(33381251220301L);
            TestDBUtility.insertWithDel(l_subAccountParams);
            
            TestDBUtility.deleteAll(IfoContractRow.TYPE);
            IfoContractParams l_ifoContractParams = TestDBUtility.getIfoContractRow();
            l_ifoContractParams.setContractId(1);
            TestDBUtility.insertWithDel(l_ifoContractParams);
            
            ThreadLocalSystemAttributesRegistry.setAttribute(
                "xblocks.gtl.attributes.systemtimestamp",
                new Timestamp(WEB3DateUtility.getDate("20080509", "yyyyMMdd").getTime()));
            
            ThreadLocalSystemAttributesRegistry.setAttribute(
            	"web3.attributes.basetimestampfororderbizdate", null);
            
            ProcessingResult processingResult = ProcessingResult.newSuccessResultInstance();
            NewOrderValidationResult l_result = new NewOrderValidationResult(processingResult);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
					"webbroker3.ifo.WEB3OptionOrderManagerImpl",
					"validateSettleContractOrder",
					new Class[] { WEB3GentradeSubAccount.class, WEB3IfoSettleContractOrderSpec.class },
					l_result);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
					"webbroker3.ifo.WEB3OptionOrderManagerImpl",
					"validateSettleContractOrder",
					new Class[] { WEB3GentradeSubAccount.class, WEB3IfoSettleContractOrderSpec.class },
					l_result);
            
            String l_str="1000";
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
					"webbroker3.ifo.WEB3OptionOrderManagerImpl",
					"getDayTradeType",
					new Class[] { SettleContractEntry[].class },l_str);
            
            WEB3IfoEstimateDeliveryAmountCalcResult l_1mountCalcResult =new WEB3IfoEstimateDeliveryAmountCalcResult();
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
					"webbroker3.ifo.WEB3OptionOrderManagerImpl",
					"calcEstimateDeliveryAmount",
					new Class[] { WEB3GentradeCommission.class,
							double.class,
				            SubAccount.class,
				            WEB3IfoTradedProductImpl.class,
				            double.class,
				            SideEnum.class,
				            boolean.class,
				            boolean.class},l_1mountCalcResult);

            OrderSubmissionResult l_orderSubmissionResult =  new OrderSubmissionResult(processingResult);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
					"webbroker3.ifo.WEB3OptionOrderManagerImpl",
					"submitSettleContractOrder",
					new Class[] {SubAccount.class,
							IfoSettleContractOrderSpec.class,
							long.class,
							String.class,
							boolean.class},l_orderSubmissionResult);
                    
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.triggerorder.base.service.delegate.stdimpls.WEB3ToSuccReservationIfoOrderUpdateServiceImpl",
                    "insertReserveOrderAction", new Class[]
                    {long.class},null);
            
            WEB3ToSuccIfoOrderUnitImplForTest1 l_rsvIfoOrderUnit= new WEB3ToSuccIfoOrderUnitImplForTest1(l_rsvIfoOrderUnitParams);

        	l_service.submitOptionsSettleContractOrder(l_rsvIfoOrderUnit);
        }
        catch(WEB3BaseException l_ex)
        {
            log.error("", l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();

        }
        catch(Exception l_ex)
        {
            log.error("", l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
	}

	//分岐フロー：　@予約注文単位.取引者ID=nullの場合のみ
	//[create返済注文内容( )：引数設定仕様]
	//!l_rsvIfoOrderUnitRow.getFirstOrderUnitIdIsNull()
	public void testSubmitOptionsSettleContractOrder_C0003()
	{
		final String STR_METHOD_NAME = "testSubmitOptionsSettleContractOrder_C0003()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        WEB3ToSuccIfoOrderUnitServiceImplForTest l_service =new WEB3ToSuccIfoOrderUnitServiceImplForTest();
        try
        {

        	TestDBUtility.deleteAll(RsvIfoOrderUnitRow.TYPE);
            RsvIfoOrderUnitParams  l_rsvIfoOrderUnitParams =TestDBUtility.getRsvIfoOrderUnitRow();
            l_rsvIfoOrderUnitParams.setBizDate("20080512");
            l_rsvIfoOrderUnitParams.setMarketId(3303L);
            l_rsvIfoOrderUnitParams.setProductId(3304148080000L);
            //l_rsvIfoOrderUnitParams.setTraderId(1000);
            l_rsvIfoOrderUnitParams.setFirstOrderUnitId(1000);
            TestDBUtility.insertWithDel(l_rsvIfoOrderUnitParams);
            
            TestDBUtility.deleteAll(TradingTimeRow.TYPE);
            TradingTimeParams  l_tradingTimeParams =TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams.setInstitutionCode("0D");
            l_tradingTimeParams.setBranchCode("123");
            l_tradingTimeParams.setMarketCode("N1");
            l_tradingTimeParams.setTradingTimeType("01");
            l_tradingTimeParams.setProductCode("0");
            l_tradingTimeParams.setBizDateType("1");
            TestDBUtility.insertWithDel(l_tradingTimeParams);
            
            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setAccountId(333812512203L);
            l_subAccountParams.setSubAccountId(33381251220301L);
            TestDBUtility.insertWithDel(l_subAccountParams);
            
            TestDBUtility.deleteAll(IfoContractRow.TYPE);
            IfoContractParams l_ifoContractParams = TestDBUtility.getIfoContractRow();
            l_ifoContractParams.setContractId(1);
            TestDBUtility.insertWithDel(l_ifoContractParams);
            
            ThreadLocalSystemAttributesRegistry.setAttribute(
                "xblocks.gtl.attributes.systemtimestamp",
                new Timestamp(WEB3DateUtility.getDate("20080509", "yyyyMMdd").getTime()));
            
            ThreadLocalSystemAttributesRegistry.setAttribute(
            	"web3.attributes.basetimestampfororderbizdate", null);
            
            ProcessingResult processingResult = ProcessingResult.newSuccessResultInstance();
            NewOrderValidationResult l_result = new NewOrderValidationResult(processingResult);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
					"webbroker3.ifo.WEB3OptionOrderManagerImpl",
					"validateSettleContractOrder",
					new Class[] { WEB3GentradeSubAccount.class, WEB3IfoSettleContractOrderSpec.class },
					l_result);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
					"webbroker3.ifo.WEB3OptionOrderManagerImpl",
					"validateSettleContractOrder",
					new Class[] { WEB3GentradeSubAccount.class, WEB3IfoSettleContractOrderSpec.class },
					l_result);
            
            String l_str="1000";
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
					"webbroker3.ifo.WEB3OptionOrderManagerImpl",
					"getDayTradeType",
					new Class[] { SettleContractEntry[].class },l_str);
            
            WEB3IfoEstimateDeliveryAmountCalcResult l_1mountCalcResult =new WEB3IfoEstimateDeliveryAmountCalcResult();
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
					"webbroker3.ifo.WEB3OptionOrderManagerImpl",
					"calcEstimateDeliveryAmount",
					new Class[] { WEB3GentradeCommission.class,
							double.class,
				            SubAccount.class,
				            WEB3IfoTradedProductImpl.class,
				            double.class,
				            SideEnum.class,
				            boolean.class,
				            boolean.class},l_1mountCalcResult);

            OrderSubmissionResult l_orderSubmissionResult =  new OrderSubmissionResult(processingResult);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
					"webbroker3.ifo.WEB3OptionOrderManagerImpl",
					"submitSettleContractOrder",
					new Class[] {SubAccount.class,
							IfoSettleContractOrderSpec.class,
							long.class,
							String.class,
							boolean.class},l_orderSubmissionResult);
                    
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.triggerorder.base.service.delegate.stdimpls.WEB3ToSuccReservationIfoOrderUpdateServiceImpl",
                    "insertReserveOrderAction", new Class[]
                    {long.class},null);
            
            WEB3ToSuccIfoOrderUnitImplForTest1 l_rsvIfoOrderUnit= new WEB3ToSuccIfoOrderUnitImplForTest1(l_rsvIfoOrderUnitParams);

        	l_service.submitOptionsSettleContractOrder(l_rsvIfoOrderUnit);
        }
        catch(WEB3BaseException l_ex)
        {
            log.error("", l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();

        }
        catch(Exception l_ex)
        {
            log.error("", l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
	}

	//[create返済注文内容( )：引数設定仕様]
	//l_rsvIfoOrderUnitRow.getFirstOrderUnitIdIsNull()
	//l_rsvIfoOrderUnitRow.getEveningSessionCarryoverFlag() = false
	public void testSubmitOptionsSettleContractOrder_C0004()
	{
		final String STR_METHOD_NAME = "testSubmitOptionsSettleContractOrder_C0004()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        WEB3ToSuccIfoOrderUnitServiceImplForTest l_service =new WEB3ToSuccIfoOrderUnitServiceImplForTest();
        try
        {

        	TestDBUtility.deleteAll(RsvIfoOrderUnitRow.TYPE);
            RsvIfoOrderUnitParams  l_rsvIfoOrderUnitParams =TestDBUtility.getRsvIfoOrderUnitRow();
            l_rsvIfoOrderUnitParams.setBizDate("20080512");
            l_rsvIfoOrderUnitParams.setMarketId(3303L);
            l_rsvIfoOrderUnitParams.setProductId(3304148080000L);
            //l_rsvIfoOrderUnitParams.setTraderId(1000);
            //l_rsvIfoOrderUnitParams.setFirstOrderUnitId(1000);
            TestDBUtility.insertWithDel(l_rsvIfoOrderUnitParams);
            
            TestDBUtility.deleteAll(TradingTimeRow.TYPE);
            TradingTimeParams  l_tradingTimeParams =TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams.setInstitutionCode("0D");
            l_tradingTimeParams.setBranchCode("123");
            l_tradingTimeParams.setMarketCode("N1");
            l_tradingTimeParams.setTradingTimeType("01");
            l_tradingTimeParams.setProductCode("0");
            l_tradingTimeParams.setBizDateType("1");
            TestDBUtility.insertWithDel(l_tradingTimeParams);
            
            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setAccountId(333812512203L);
            l_subAccountParams.setSubAccountId(33381251220301L);
            TestDBUtility.insertWithDel(l_subAccountParams);
            
            TestDBUtility.deleteAll(IfoContractRow.TYPE);
            IfoContractParams l_ifoContractParams = TestDBUtility.getIfoContractRow();
            l_ifoContractParams.setContractId(1);
            TestDBUtility.insertWithDel(l_ifoContractParams);
            
            ThreadLocalSystemAttributesRegistry.setAttribute(
                "xblocks.gtl.attributes.systemtimestamp",
                new Timestamp(WEB3DateUtility.getDate("20080509", "yyyyMMdd").getTime()));
            
            ThreadLocalSystemAttributesRegistry.setAttribute(
            	"web3.attributes.basetimestampfororderbizdate", null);
            
            ProcessingResult processingResult = ProcessingResult.newSuccessResultInstance();
            NewOrderValidationResult l_result = new NewOrderValidationResult(processingResult);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
					"webbroker3.ifo.WEB3OptionOrderManagerImpl",
					"validateSettleContractOrder",
					new Class[] { WEB3GentradeSubAccount.class, WEB3IfoSettleContractOrderSpec.class },
					l_result);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
					"webbroker3.ifo.WEB3OptionOrderManagerImpl",
					"validateSettleContractOrder",
					new Class[] { WEB3GentradeSubAccount.class, WEB3IfoSettleContractOrderSpec.class },
					l_result);
            
            String l_str="1000";
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
					"webbroker3.ifo.WEB3OptionOrderManagerImpl",
					"getDayTradeType",
					new Class[] { SettleContractEntry[].class },l_str);
            
            WEB3IfoEstimateDeliveryAmountCalcResult l_1mountCalcResult =new WEB3IfoEstimateDeliveryAmountCalcResult();
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
					"webbroker3.ifo.WEB3OptionOrderManagerImpl",
					"calcEstimateDeliveryAmount",
					new Class[] { WEB3GentradeCommission.class,
							double.class,
				            SubAccount.class,
				            WEB3IfoTradedProductImpl.class,
				            double.class,
				            SideEnum.class,
				            boolean.class,
				            boolean.class},l_1mountCalcResult);

            OrderSubmissionResult l_orderSubmissionResult =  new OrderSubmissionResult(processingResult);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
					"webbroker3.ifo.WEB3OptionOrderManagerImpl",
					"submitSettleContractOrder",
					new Class[] {SubAccount.class,
							IfoSettleContractOrderSpec.class,
							long.class,
							String.class,
							boolean.class},l_orderSubmissionResult);
                    
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.triggerorder.base.service.delegate.stdimpls.WEB3ToSuccReservationIfoOrderUpdateServiceImpl",
                    "insertReserveOrderAction", new Class[]
                    {long.class},null);
            
            WEB3ToSuccIfoOrderUnitImplForTest1 l_rsvIfoOrderUnit= new WEB3ToSuccIfoOrderUnitImplForTest1(l_rsvIfoOrderUnitParams);

        	l_service.submitOptionsSettleContractOrder(l_rsvIfoOrderUnit);
        }
        catch(WEB3BaseException l_ex)
        {
            log.error("", l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();

        }
        catch(Exception l_ex)
        {
            log.error("", l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
	}

	//l_rsvIfoOrderUnitRow.getFirstOrderUnitIdIsNull()
	//l_rsvIfoOrderUnitRow.getEveningSessionCarryoverFlag() = true
	//validate返済注文(補助口座 : SubAccount, 信用返済注文内容 : EqTypeSettleContractOrderSpec)
	public void testSubmitOptionsSettleContractOrder_C0005()
	{
		final String STR_METHOD_NAME = "testSubmitOptionsSettleContractOrder_C0005()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        WEB3ToSuccIfoOrderUnitServiceImplForTest l_service =new WEB3ToSuccIfoOrderUnitServiceImplForTest();
        try
        {

        	TestDBUtility.deleteAll(RsvIfoOrderUnitRow.TYPE);
            RsvIfoOrderUnitParams  l_rsvIfoOrderUnitParams =TestDBUtility.getRsvIfoOrderUnitRow();
            l_rsvIfoOrderUnitParams.setBizDate("20080512");
            l_rsvIfoOrderUnitParams.setMarketId(3303L);
            l_rsvIfoOrderUnitParams.setProductId(3304148080000L);
            //l_rsvIfoOrderUnitParams.setTraderId(1000);
            //l_rsvIfoOrderUnitParams.setFirstOrderUnitId(1000);
            TestDBUtility.insertWithDel(l_rsvIfoOrderUnitParams);
            
            TestDBUtility.deleteAll(TradingTimeRow.TYPE);
            TradingTimeParams  l_tradingTimeParams =TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams.setInstitutionCode("0D");
            l_tradingTimeParams.setBranchCode("123");
            l_tradingTimeParams.setMarketCode("N1");
            l_tradingTimeParams.setTradingTimeType("01");
            l_tradingTimeParams.setProductCode("0");
            l_tradingTimeParams.setBizDateType("1");
            TestDBUtility.insertWithDel(l_tradingTimeParams);
            
            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setAccountId(333812512203L);
            l_subAccountParams.setSubAccountId(33381251220301L);
            TestDBUtility.insertWithDel(l_subAccountParams);
            
            ThreadLocalSystemAttributesRegistry.setAttribute(
                "xblocks.gtl.attributes.systemtimestamp",
                new Timestamp(WEB3DateUtility.getDate("20080509", "yyyyMMdd").getTime()));
            
            ThreadLocalSystemAttributesRegistry.setAttribute(
            	"web3.attributes.basetimestampfororderbizdate", null);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.triggerorder.base.service.delegate.stdimpls.WEB3ToSuccReservationIfoOrderUpdateServiceImpl",
                    "invalidateOrderUnit", new Class[]
                    {RsvIfoOrderUnitRow.class,String.class},new WEB3BaseException(WEB3ErrorCatalog.BUSINESS_ERROR_00283,""));
            						
            WEB3ToSuccIfoOrderUnitImplForTest1 l_rsvIfoOrderUnit= new WEB3ToSuccIfoOrderUnitImplForTest1(l_rsvIfoOrderUnitParams);

        	l_service.submitOptionsSettleContractOrder(l_rsvIfoOrderUnit);
            fail();
        }
        catch(WEB3BaseException l_ex)
        {
            log.error("", l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00283, l_ex.getErrorInfo());

        }
        catch(Exception l_ex)
        {
            log.error("", l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
	}

	//l_rsvIfoOrderUnitRow.getFirstOrderUnitIdIsNull()
	//l_rsvIfoOrderUnitRow.getEveningSessionCarryoverFlag() = true
	//{validate返済注文()}が失敗の場合。
	public void testSubmitOptionsSettleContractOrder_C0006()
	{
		final String STR_METHOD_NAME = "testSubmitOptionsSettleContractOrder_C0006()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        WEB3ToSuccIfoOrderUnitServiceImplForTest l_service =new WEB3ToSuccIfoOrderUnitServiceImplForTest();
        try
        {

        	TestDBUtility.deleteAll(RsvIfoOrderUnitRow.TYPE);
            RsvIfoOrderUnitParams  l_rsvIfoOrderUnitParams =TestDBUtility.getRsvIfoOrderUnitRow();
            l_rsvIfoOrderUnitParams.setBizDate("20080512");
            l_rsvIfoOrderUnitParams.setMarketId(3303L);
            l_rsvIfoOrderUnitParams.setProductId(3304148080000L);
            //l_rsvIfoOrderUnitParams.setTraderId(1000);
            //l_rsvIfoOrderUnitParams.setFirstOrderUnitId(1000);
            TestDBUtility.insertWithDel(l_rsvIfoOrderUnitParams);
            
            TestDBUtility.deleteAll(TradingTimeRow.TYPE);
            TradingTimeParams  l_tradingTimeParams =TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams.setInstitutionCode("0D");
            l_tradingTimeParams.setBranchCode("123");
            l_tradingTimeParams.setMarketCode("N1");
            l_tradingTimeParams.setTradingTimeType("01");
            l_tradingTimeParams.setProductCode("0");
            l_tradingTimeParams.setBizDateType("1");
            TestDBUtility.insertWithDel(l_tradingTimeParams);
            
            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setAccountId(333812512203L);
            l_subAccountParams.setSubAccountId(33381251220301L);
            TestDBUtility.insertWithDel(l_subAccountParams);
            
            ThreadLocalSystemAttributesRegistry.setAttribute(
                "xblocks.gtl.attributes.systemtimestamp",
                new Timestamp(WEB3DateUtility.getDate("20080509", "yyyyMMdd").getTime()));
            
            ThreadLocalSystemAttributesRegistry.setAttribute(
            	"web3.attributes.basetimestampfororderbizdate", null);
            
            //ProcessingResult processingResult = ProcessingResult.newSuccessResultInstance();
            ProcessingResult processingResult = null;
            ErrorInfo errorInfo= new ErrorInfo();
            errorInfo.setErrorCode("BUSINESS_ERROR_00282");
            NewOrderValidationResult l_result = new NewOrderValidationResult(processingResult);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
					"webbroker3.ifo.WEB3OptionOrderManagerImpl",
					"validateSettleContractOrder",
					new Class[] { WEB3GentradeSubAccount.class, WEB3IfoSettleContractOrderSpec.class },
					l_result);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.triggerorder.base.service.delegate.stdimpls.WEB3ToSuccReservationIfoOrderUpdateServiceImpl",
                    "invalidateOrderUnit", new Class[]
                    {RsvIfoOrderUnitRow.class,String.class},new WEB3BaseException(WEB3ErrorCatalog.BUSINESS_ERROR_00282,""));
   
            WEB3ToSuccIfoOrderUnitImplForTest1 l_rsvIfoOrderUnit= new WEB3ToSuccIfoOrderUnitImplForTest1(l_rsvIfoOrderUnitParams);

        	l_service.submitOptionsSettleContractOrder(l_rsvIfoOrderUnit);
            fail();
        }
        catch(WEB3BaseException l_ex)
        {
            log.error("", l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00282, l_ex.getErrorInfo());

        }
        catch(Exception l_ex)
        {
            log.error("", l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
	}

	//l_rsvIfoOrderUnitRow.getFirstOrderUnitIdIsNull()
	//l_rsvIfoOrderUnitRow.getEveningSessionCarryoverFlag() = true
	//建玉.isLong() == trueの場合
	//correct case
	public void testSubmitOptionsSettleContractOrder_C0007()
	{
		final String STR_METHOD_NAME = "testSubmitOptionsSettleContractOrder_C0007()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        WEB3ToSuccIfoOrderUnitServiceImplForTest l_service =new WEB3ToSuccIfoOrderUnitServiceImplForTest();
        try
        {
        	TestDBUtility.deleteAll(RsvIfoOrderUnitRow.TYPE);
            RsvIfoOrderUnitParams  l_rsvIfoOrderUnitParams =TestDBUtility.getRsvIfoOrderUnitRow();
            l_rsvIfoOrderUnitParams.setBizDate("20080512");
            l_rsvIfoOrderUnitParams.setMarketId(3303L);
            l_rsvIfoOrderUnitParams.setProductId(3304148080000L);
            //l_rsvIfoOrderUnitParams.setTraderId(1000);
            //l_rsvIfoOrderUnitParams.setFirstOrderUnitId(1000);
            TestDBUtility.insertWithDel(l_rsvIfoOrderUnitParams);
            
            TestDBUtility.deleteAll(TradingTimeRow.TYPE);
            TradingTimeParams  l_tradingTimeParams =TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams.setInstitutionCode("0D");
            l_tradingTimeParams.setBranchCode("123");
            l_tradingTimeParams.setMarketCode("N1");
            l_tradingTimeParams.setTradingTimeType("01");
            l_tradingTimeParams.setProductCode("0");
            l_tradingTimeParams.setBizDateType("1");
            TestDBUtility.insertWithDel(l_tradingTimeParams);
            
            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setAccountId(333812512203L);
            l_subAccountParams.setSubAccountId(33381251220301L);
            TestDBUtility.insertWithDel(l_subAccountParams);
            
            TestDBUtility.deleteAll(IfoContractRow.TYPE);
            IfoContractParams l_ifoContractParams = TestDBUtility.getIfoContractRow();
            l_ifoContractParams.setContractId(1);
            TestDBUtility.insertWithDel(l_ifoContractParams);
            
            ThreadLocalSystemAttributesRegistry.setAttribute(
                "xblocks.gtl.attributes.systemtimestamp",
                new Timestamp(WEB3DateUtility.getDate("20080509", "yyyyMMdd").getTime()));
            
            ThreadLocalSystemAttributesRegistry.setAttribute(
            	"web3.attributes.basetimestampfororderbizdate", null);
            
            ProcessingResult processingResult = ProcessingResult.newSuccessResultInstance();
            NewOrderValidationResult l_result = new NewOrderValidationResult(processingResult);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
					"webbroker3.ifo.WEB3OptionOrderManagerImpl",
					"validateSettleContractOrder",
					new Class[] { WEB3GentradeSubAccount.class, WEB3IfoSettleContractOrderSpec.class },
					l_result);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
					"webbroker3.ifo.WEB3OptionOrderManagerImpl",
					"validateSettleContractOrder",
					new Class[] { WEB3GentradeSubAccount.class, WEB3IfoSettleContractOrderSpec.class },
					l_result);
            
            String l_str="1000";
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
					"webbroker3.ifo.WEB3OptionOrderManagerImpl",
					"getDayTradeType",
					new Class[] { SettleContractEntry[].class },l_str);
            
            WEB3IfoEstimateDeliveryAmountCalcResult l_1mountCalcResult =new WEB3IfoEstimateDeliveryAmountCalcResult();
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
					"webbroker3.ifo.WEB3OptionOrderManagerImpl",
					"calcEstimateDeliveryAmount",
					new Class[] { WEB3GentradeCommission.class,
							double.class,
				            SubAccount.class,
				            WEB3IfoTradedProductImpl.class,
				            double.class,
				            SideEnum.class,
				            boolean.class,
				            boolean.class},l_1mountCalcResult);

            OrderSubmissionResult l_orderSubmissionResult =  new OrderSubmissionResult(processingResult);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
					"webbroker3.ifo.WEB3OptionOrderManagerImpl",
					"submitSettleContractOrder",
					new Class[] {SubAccount.class,
							IfoSettleContractOrderSpec.class,
							long.class,
							String.class,
							boolean.class},l_orderSubmissionResult);
                    
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.triggerorder.base.service.delegate.stdimpls.WEB3ToSuccReservationIfoOrderUpdateServiceImpl",
                    "insertReserveOrderAction", new Class[]
                    {long.class},null);
            
            WEB3ToSuccIfoOrderUnitImplForTest1 l_rsvIfoOrderUnit= new WEB3ToSuccIfoOrderUnitImplForTest1(l_rsvIfoOrderUnitParams);

        	l_service.submitOptionsSettleContractOrder(l_rsvIfoOrderUnit);
        }
        catch(WEB3BaseException l_ex)
        {
            log.error("", l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();

        }
        catch(Exception l_ex)
        {
            log.error("", l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
	}

    //l_rsvIfoOrderUnitRow.getFirstOrderUnitIdIsNull()
	//l_rsvIfoOrderUnitRow.getEveningSessionCarryoverFlag() = true
	//建玉.isLong() == falseの場合
	//correct case
	public void testSubmitOptionsSettleContractOrder_C0008()
	{
		final String STR_METHOD_NAME = "testSubmitOptionsSettleContractOrder_C0008()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        WEB3ToSuccIfoOrderUnitServiceImplForTest l_service =new WEB3ToSuccIfoOrderUnitServiceImplForTest();
        try
        {
        	TestDBUtility.deleteAll(RsvIfoOrderUnitRow.TYPE);
            RsvIfoOrderUnitParams  l_rsvIfoOrderUnitParams =TestDBUtility.getRsvIfoOrderUnitRow();
            l_rsvIfoOrderUnitParams.setBizDate("20080512");
            l_rsvIfoOrderUnitParams.setMarketId(3303L);
            l_rsvIfoOrderUnitParams.setProductId(3304148080000L);
            //l_rsvIfoOrderUnitParams.setTraderId(1000);
            //l_rsvIfoOrderUnitParams.setFirstOrderUnitId(1000);
            TestDBUtility.insertWithDel(l_rsvIfoOrderUnitParams);
            
            TestDBUtility.deleteAll(TradingTimeRow.TYPE);
            TradingTimeParams  l_tradingTimeParams =TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams.setInstitutionCode("0D");
            l_tradingTimeParams.setBranchCode("123");
            l_tradingTimeParams.setMarketCode("N1");
            l_tradingTimeParams.setTradingTimeType("01");
            l_tradingTimeParams.setProductCode("0");
            l_tradingTimeParams.setBizDateType("1");
            TestDBUtility.insertWithDel(l_tradingTimeParams);
            
            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setAccountId(333812512203L);
            l_subAccountParams.setSubAccountId(33381251220301L);
            TestDBUtility.insertWithDel(l_subAccountParams);
            
            TestDBUtility.deleteAll(IfoContractRow.TYPE);
            IfoContractParams l_ifoContractParams = TestDBUtility.getIfoContractRow();
            l_ifoContractParams.setContractId(1);
            TestDBUtility.insertWithDel(l_ifoContractParams);
            
            ThreadLocalSystemAttributesRegistry.setAttribute(
                "xblocks.gtl.attributes.systemtimestamp",
                new Timestamp(WEB3DateUtility.getDate("20080509", "yyyyMMdd").getTime()));
            
            ThreadLocalSystemAttributesRegistry.setAttribute(
            	"web3.attributes.basetimestampfororderbizdate", null);
            
            ProcessingResult processingResult = ProcessingResult.newSuccessResultInstance();
            NewOrderValidationResult l_result = new NewOrderValidationResult(processingResult);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
					"webbroker3.ifo.WEB3OptionOrderManagerImpl",
					"validateSettleContractOrder",
					new Class[] { WEB3GentradeSubAccount.class, WEB3IfoSettleContractOrderSpec.class },
					l_result);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
					"webbroker3.ifo.WEB3OptionOrderManagerImpl",
					"validateSettleContractOrder",
					new Class[] { WEB3GentradeSubAccount.class, WEB3IfoSettleContractOrderSpec.class },
					l_result);
            
            String l_str="1000";
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
					"webbroker3.ifo.WEB3OptionOrderManagerImpl",
					"getDayTradeType",
					new Class[] { SettleContractEntry[].class },l_str);
            
            WEB3IfoEstimateDeliveryAmountCalcResult l_1mountCalcResult =new WEB3IfoEstimateDeliveryAmountCalcResult();
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
					"webbroker3.ifo.WEB3OptionOrderManagerImpl",
					"calcEstimateDeliveryAmount",
					new Class[] { WEB3GentradeCommission.class,
							double.class,
				            SubAccount.class,
				            WEB3IfoTradedProductImpl.class,
				            double.class,
				            SideEnum.class,
				            boolean.class,
				            boolean.class},l_1mountCalcResult);

            OrderSubmissionResult l_orderSubmissionResult =  new OrderSubmissionResult(processingResult);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
					"webbroker3.ifo.WEB3OptionOrderManagerImpl",
					"submitSettleContractOrder",
					new Class[] {SubAccount.class,
							IfoSettleContractOrderSpec.class,
							long.class,
							String.class,
							boolean.class},l_orderSubmissionResult);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.triggerorder.base.service.delegate.stdimpls.WEB3ToSuccReservationIfoOrderUpdateServiceImpl",
                    "insertReserveOrderAction", new Class[]
                    {long.class},null);
            
            WEB3ToSuccIfoOrderUnitImplForTest1 l_rsvIfoOrderUnit= new WEB3ToSuccIfoOrderUnitImplForTest1(l_rsvIfoOrderUnitParams);

        	l_service.submitOptionsSettleContractOrder(l_rsvIfoOrderUnit);
        }
        catch(WEB3BaseException l_ex)
        {
            log.error("", l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();

        }
        catch(Exception l_ex)
        {
            log.error("", l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
	}

	//実行結果に応じ、予約注文単位をupdateするOK
	public void testSubmitOptionsSettleContractOrder_C0009()
	{
		final String STR_METHOD_NAME = "testSubmitOptionsSettleContractOrder_C0008()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        WEB3ToSuccIfoOrderUnitServiceImplForTest l_service =new WEB3ToSuccIfoOrderUnitServiceImplForTest();
        try
        {
        	TestDBUtility.deleteAll(RsvIfoOrderUnitRow.TYPE);
            RsvIfoOrderUnitParams  l_rsvIfoOrderUnitParams =TestDBUtility.getRsvIfoOrderUnitRow();
            l_rsvIfoOrderUnitParams.setBizDate("20080512");
            l_rsvIfoOrderUnitParams.setMarketId(3303L);
            l_rsvIfoOrderUnitParams.setProductId(3304148080000L);
            //l_rsvIfoOrderUnitParams.setTraderId(1000);
            //l_rsvIfoOrderUnitParams.setFirstOrderUnitId(1000);
            TestDBUtility.insertWithDel(l_rsvIfoOrderUnitParams);
            
            TestDBUtility.deleteAll(TradingTimeRow.TYPE);
            TradingTimeParams  l_tradingTimeParams =TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams.setInstitutionCode("0D");
            l_tradingTimeParams.setBranchCode("123");
            l_tradingTimeParams.setMarketCode("N1");
            l_tradingTimeParams.setTradingTimeType("01");
            l_tradingTimeParams.setProductCode("0");
            l_tradingTimeParams.setBizDateType("1");
            TestDBUtility.insertWithDel(l_tradingTimeParams);
            
            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setAccountId(333812512203L);
            l_subAccountParams.setSubAccountId(33381251220301L);
            TestDBUtility.insertWithDel(l_subAccountParams);
            
            TestDBUtility.deleteAll(IfoContractRow.TYPE);
            IfoContractParams l_ifoContractParams = TestDBUtility.getIfoContractRow();
            l_ifoContractParams.setContractId(1);
            l_ifoContractParams.setContractType(ContractTypeEnum.SHORT);
            TestDBUtility.insertWithDel(l_ifoContractParams);
            
            ThreadLocalSystemAttributesRegistry.setAttribute(
                "xblocks.gtl.attributes.systemtimestamp",
                new Timestamp(WEB3DateUtility.getDate("20080509", "yyyyMMdd").getTime()));
            
            ThreadLocalSystemAttributesRegistry.setAttribute(
            	"web3.attributes.basetimestampfororderbizdate", null);
            
            ProcessingResult processingResult = ProcessingResult.newSuccessResultInstance();
            NewOrderValidationResult l_result = new NewOrderValidationResult(processingResult);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
					"webbroker3.ifo.WEB3OptionOrderManagerImpl",
					"validateSettleContractOrder",
					new Class[] { WEB3GentradeSubAccount.class, WEB3IfoSettleContractOrderSpec.class },
					l_result);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
					"webbroker3.ifo.WEB3OptionOrderManagerImpl",
					"validateSettleContractOrder",
					new Class[] { WEB3GentradeSubAccount.class, WEB3IfoSettleContractOrderSpec.class },
					l_result);
            
            String l_str="1000";
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
					"webbroker3.ifo.WEB3OptionOrderManagerImpl",
					"getDayTradeType",
					new Class[] { SettleContractEntry[].class },l_str);
            
            WEB3IfoEstimateDeliveryAmountCalcResult l_1mountCalcResult =new WEB3IfoEstimateDeliveryAmountCalcResult();
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
					"webbroker3.ifo.WEB3OptionOrderManagerImpl",
					"calcEstimateDeliveryAmount",
					new Class[] { WEB3GentradeCommission.class,
							double.class,
				            SubAccount.class,
				            WEB3IfoTradedProductImpl.class,
				            double.class,
				            SideEnum.class,
				            boolean.class,
				            boolean.class},l_1mountCalcResult);

            OrderSubmissionResult l_orderSubmissionResult =  new OrderSubmissionResult(processingResult);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
					"webbroker3.ifo.WEB3OptionOrderManagerImpl",
					"submitSettleContractOrder",
					new Class[] {SubAccount.class,
							IfoSettleContractOrderSpec.class,
							long.class,
							String.class,
							boolean.class},l_orderSubmissionResult);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.triggerorder.base.service.delegate.stdimpls.WEB3ToSuccReservationIfoOrderUpdateServiceImpl",
                    "insertReserveOrderAction", new Class[]
                    {long.class},null);
            
            WEB3ToSuccIfoOrderUnitImplForTest1 l_rsvIfoOrderUnit= new WEB3ToSuccIfoOrderUnitImplForTest1(l_rsvIfoOrderUnitParams);

        	l_service.submitOptionsSettleContractOrder(l_rsvIfoOrderUnit);
        }
        catch(WEB3BaseException l_ex)
        {
            log.error("", l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();

        }
        catch(Exception l_ex)
        {
            log.error("", l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
	}

	//実行結果に応じ、予約注文単位をupdateする false
	public void testSubmitOptionsSettleContractOrder_C0010()
	{
		final String STR_METHOD_NAME = "testSubmitOptionsSettleContractOrder_C0010()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        WEB3ToSuccIfoOrderUnitServiceImplForTest l_service =new WEB3ToSuccIfoOrderUnitServiceImplForTest();
        try
        {
        	TestDBUtility.deleteAll(RsvIfoOrderUnitRow.TYPE);
            RsvIfoOrderUnitParams  l_rsvIfoOrderUnitParams =TestDBUtility.getRsvIfoOrderUnitRow();
            l_rsvIfoOrderUnitParams.setBizDate("20080512");
            l_rsvIfoOrderUnitParams.setMarketId(3303L);
            l_rsvIfoOrderUnitParams.setProductId(3304148080000L);
            //l_rsvIfoOrderUnitParams.setTraderId(1000);
            //l_rsvIfoOrderUnitParams.setFirstOrderUnitId(1000);
            TestDBUtility.insertWithDel(l_rsvIfoOrderUnitParams);
            
            TestDBUtility.deleteAll(TradingTimeRow.TYPE);
            TradingTimeParams  l_tradingTimeParams =TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams.setInstitutionCode("0D");
            l_tradingTimeParams.setBranchCode("123");
            l_tradingTimeParams.setMarketCode("N1");
            l_tradingTimeParams.setTradingTimeType("01");
            l_tradingTimeParams.setProductCode("0");
            l_tradingTimeParams.setBizDateType("1");
            TestDBUtility.insertWithDel(l_tradingTimeParams);
            
            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setAccountId(333812512203L);
            l_subAccountParams.setSubAccountId(33381251220301L);
            TestDBUtility.insertWithDel(l_subAccountParams);
            
            TestDBUtility.deleteAll(IfoContractRow.TYPE);
            IfoContractParams l_ifoContractParams = TestDBUtility.getIfoContractRow();
            l_ifoContractParams.setContractId(1);
            TestDBUtility.insertWithDel(l_ifoContractParams);
            
            ThreadLocalSystemAttributesRegistry.setAttribute(
                "xblocks.gtl.attributes.systemtimestamp",
                new Timestamp(WEB3DateUtility.getDate("20080509", "yyyyMMdd").getTime()));
            
            ThreadLocalSystemAttributesRegistry.setAttribute(
            	"web3.attributes.basetimestampfororderbizdate", null);
            
            ProcessingResult processingResult = ProcessingResult.newSuccessResultInstance();
            NewOrderValidationResult l_result = new NewOrderValidationResult(processingResult);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
					"webbroker3.ifo.WEB3OptionOrderManagerImpl",
					"validateSettleContractOrder",
					new Class[] { WEB3GentradeSubAccount.class, WEB3IfoSettleContractOrderSpec.class },
					l_result);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
					"webbroker3.ifo.WEB3OptionOrderManagerImpl",
					"validateSettleContractOrder",
					new Class[] { WEB3GentradeSubAccount.class, WEB3IfoSettleContractOrderSpec.class },
					l_result);
            
            String l_str="1000";
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
					"webbroker3.ifo.WEB3OptionOrderManagerImpl",
					"getDayTradeType",
					new Class[] { SettleContractEntry[].class },l_str);
            
            WEB3IfoEstimateDeliveryAmountCalcResult l_1mountCalcResult =new WEB3IfoEstimateDeliveryAmountCalcResult();
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
					"webbroker3.ifo.WEB3OptionOrderManagerImpl",
					"calcEstimateDeliveryAmount",
					new Class[] { WEB3GentradeCommission.class,
							double.class,
				            SubAccount.class,
				            WEB3IfoTradedProductImpl.class,
				            double.class,
				            SideEnum.class,
				            boolean.class,
				            boolean.class},l_1mountCalcResult);

            ErrorInfo errorInfo = new ErrorInfo();
            errorInfo.setErrorCode("BUSINESS_ERROR_02332");
            ProcessingResult processingResult2 = ProcessingResult.newFailedResultInstance(errorInfo);
            OrderSubmissionResult l_orderSubmissionResult =  new OrderSubmissionResult(processingResult2);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
					"webbroker3.ifo.WEB3OptionOrderManagerImpl",
					"submitSettleContractOrder",
					new Class[] {SubAccount.class,
							IfoSettleContractOrderSpec.class,
							long.class,
							String.class,
							boolean.class},l_orderSubmissionResult);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.triggerorder.base.service.delegate.stdimpls.WEB3ToSuccReservationIfoOrderUpdateServiceImpl",
                    "insertReserveOrderAction", new Class[]
                    {long.class},null);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.triggerorder.base.service.delegate.stdimpls.WEB3ToSuccReservationIfoOrderUpdateServiceImpl",
                    "invalidateOrderUnit", new Class[]
                    {RsvIfoOrderUnitRow.class,String.class},new WEB3BaseException(WEB3ErrorCatalog.BUSINESS_ERROR_02332,""));
            
            WEB3ToSuccIfoOrderUnitImplForTest1 l_rsvIfoOrderUnit= new WEB3ToSuccIfoOrderUnitImplForTest1(l_rsvIfoOrderUnitParams);

        	l_service.submitOptionsSettleContractOrder(l_rsvIfoOrderUnit);
            fail();
        }
        catch(WEB3BaseException l_ex)
        {
            log.error("", l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02332, l_ex.getErrorInfo());

        }
        catch(Exception l_ex)
        {
            log.error("", l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
	}

	//オプション買建口座（補助口座.補助口座タイプ≠"株式オプション取引口座（先物証拠金））の場合
	public void testSubmitOptionsSettleContractOrder_C0011()
	{
		final String STR_METHOD_NAME = "testSubmitOptionsSettleContractOrder_C0011()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        WEB3ToSuccIfoOrderUnitServiceImplForTest l_service =new WEB3ToSuccIfoOrderUnitServiceImplForTest();
        try
        {
        	TestDBUtility.deleteAll(RsvIfoOrderUnitRow.TYPE);
            RsvIfoOrderUnitParams  l_rsvIfoOrderUnitParams =TestDBUtility.getRsvIfoOrderUnitRow();
            l_rsvIfoOrderUnitParams.setBizDate("20080512");
            l_rsvIfoOrderUnitParams.setMarketId(3303L);
            l_rsvIfoOrderUnitParams.setProductId(3304148080000L);
            TestDBUtility.insertWithDel(l_rsvIfoOrderUnitParams);
            
            TestDBUtility.deleteAll(TradingTimeRow.TYPE);
            TradingTimeParams  l_tradingTimeParams =TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams.setInstitutionCode("0D");
            l_tradingTimeParams.setBranchCode("123");
            l_tradingTimeParams.setMarketCode("N1");
            l_tradingTimeParams.setTradingTimeType("01");
            l_tradingTimeParams.setProductCode("0");
            l_tradingTimeParams.setBizDateType("1");
            TestDBUtility.insertWithDel(l_tradingTimeParams);
            
            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setAccountId(333812512203L);
            l_subAccountParams.setSubAccountId(33381251220301L);
            TestDBUtility.insertWithDel(l_subAccountParams);
            
            TestDBUtility.deleteAll(IfoContractRow.TYPE);
            IfoContractParams l_ifoContractParams = TestDBUtility.getIfoContractRow();
            l_ifoContractParams.setContractId(1);
            TestDBUtility.insertWithDel(l_ifoContractParams);
            
            ThreadLocalSystemAttributesRegistry.setAttribute(
                "xblocks.gtl.attributes.systemtimestamp",
                new Timestamp(WEB3DateUtility.getDate("20080509", "yyyyMMdd").getTime()));
            
            ThreadLocalSystemAttributesRegistry.setAttribute(
            	"web3.attributes.basetimestampfororderbizdate", null);
            
            ProcessingResult processingResult = ProcessingResult.newSuccessResultInstance();
            NewOrderValidationResult l_result = new NewOrderValidationResult(processingResult);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
					"webbroker3.ifo.WEB3OptionOrderManagerImpl",
					"validateSettleContractOrder",
					new Class[] { WEB3GentradeSubAccount.class, WEB3IfoSettleContractOrderSpec.class },
					l_result);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
					"webbroker3.ifo.WEB3OptionOrderManagerImpl",
					"validateSettleContractOrder",
					new Class[] { WEB3GentradeSubAccount.class, WEB3IfoSettleContractOrderSpec.class },
					l_result);
            
            String l_str="1000";
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
					"webbroker3.ifo.WEB3OptionOrderManagerImpl",
					"getDayTradeType",
					new Class[] { SettleContractEntry[].class },l_str);
            
            WEB3IfoEstimateDeliveryAmountCalcResult l_1mountCalcResult =new WEB3IfoEstimateDeliveryAmountCalcResult();
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
					"webbroker3.ifo.WEB3OptionOrderManagerImpl",
					"calcEstimateDeliveryAmount",
					new Class[] { WEB3GentradeCommission.class,
							double.class,
				            SubAccount.class,
				            WEB3IfoTradedProductImpl.class,
				            double.class,
				            SideEnum.class,
				            boolean.class,
				            boolean.class},l_1mountCalcResult);

            OrderSubmissionResult l_orderSubmissionResult =  new OrderSubmissionResult(processingResult);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
					"webbroker3.ifo.WEB3OptionOrderManagerImpl",
					"submitSettleContractOrder",
					new Class[] {SubAccount.class,
							IfoSettleContractOrderSpec.class,
							long.class,
							String.class,
							boolean.class},l_orderSubmissionResult);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.triggerorder.base.service.delegate.stdimpls.WEB3ToSuccReservationIfoOrderUpdateServiceImpl",
                    "insertReserveOrderAction", new Class[]
                    {long.class},null);
            
            WEB3ToSuccIfoOrderUnitImplForTest1 l_rsvIfoOrderUnit= new WEB3ToSuccIfoOrderUnitImplForTest1(l_rsvIfoOrderUnitParams);

        	l_service.submitOptionsSettleContractOrder(l_rsvIfoOrderUnit);
        }
        catch(WEB3BaseException l_ex)
        {
            log.error("", l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();

        }
        catch(Exception l_ex)
        {
            log.error("", l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
	}
	
	//オプション買建口座（補助口座.補助口座タイプ="株式オプション取引口座（先物証拠金））の場合
	public void testSubmitOptionsSettleContractOrder_C0012()
	{
		final String STR_METHOD_NAME = "testSubmitOptionsSettleContractOrder_C0012()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        WEB3ToSuccIfoOrderUnitServiceImplForTest l_service =new WEB3ToSuccIfoOrderUnitServiceImplForTest();
        try
        {
        	TestDBUtility.deleteAll(RsvIfoOrderUnitRow.TYPE);
            RsvIfoOrderUnitParams  l_rsvIfoOrderUnitParams =TestDBUtility.getRsvIfoOrderUnitRow();
            l_rsvIfoOrderUnitParams.setBizDate("20080512");
            l_rsvIfoOrderUnitParams.setMarketId(3303L);
            l_rsvIfoOrderUnitParams.setProductId(3304148080000L);
            TestDBUtility.insertWithDel(l_rsvIfoOrderUnitParams);
            
            TestDBUtility.deleteAll(TradingTimeRow.TYPE);
            TradingTimeParams  l_tradingTimeParams =TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams.setInstitutionCode("0D");
            l_tradingTimeParams.setBranchCode("123");
            l_tradingTimeParams.setMarketCode("N1");
            l_tradingTimeParams.setTradingTimeType("01");
            l_tradingTimeParams.setProductCode("0");
            l_tradingTimeParams.setBizDateType("1");
            TestDBUtility.insertWithDel(l_tradingTimeParams);
            
            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setAccountId(333812512203L);
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_OPTIONS_SUB_ACCOUNT);
            l_subAccountParams.setSubAccountId(33381251220301L);
            TestDBUtility.insertWithDel(l_subAccountParams);
            
            TestDBUtility.deleteAll(IfoContractRow.TYPE);
            IfoContractParams l_ifoContractParams = TestDBUtility.getIfoContractRow();
            l_ifoContractParams.setContractId(1);
            TestDBUtility.insertWithDel(l_ifoContractParams);
            
            ThreadLocalSystemAttributesRegistry.setAttribute(
                "xblocks.gtl.attributes.systemtimestamp",
                new Timestamp(WEB3DateUtility.getDate("20080509", "yyyyMMdd").getTime()));
            
            ThreadLocalSystemAttributesRegistry.setAttribute(
            	"web3.attributes.basetimestampfororderbizdate", null);
            
            ProcessingResult processingResult = ProcessingResult.newSuccessResultInstance();
            NewOrderValidationResult l_result = new NewOrderValidationResult(processingResult);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
					"webbroker3.ifo.WEB3OptionOrderManagerImpl",
					"validateSettleContractOrder",
					new Class[] { WEB3GentradeSubAccount.class, WEB3IfoSettleContractOrderSpec.class },
					l_result);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
					"webbroker3.ifo.WEB3OptionOrderManagerImpl",
					"validateSettleContractOrder",
					new Class[] { WEB3GentradeSubAccount.class, WEB3IfoSettleContractOrderSpec.class },
					l_result);
            
            String l_str="1000";
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
					"webbroker3.ifo.WEB3OptionOrderManagerImpl",
					"getDayTradeType",
					new Class[] { SettleContractEntry[].class },l_str);
            
            WEB3IfoEstimateDeliveryAmountCalcResult l_1mountCalcResult =new WEB3IfoEstimateDeliveryAmountCalcResult();
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
					"webbroker3.ifo.WEB3OptionOrderManagerImpl",
					"calcEstimateDeliveryAmount",
					new Class[] { WEB3GentradeCommission.class,
							double.class,
				            SubAccount.class,
				            WEB3IfoTradedProductImpl.class,
				            double.class,
				            SideEnum.class,
				            boolean.class,
				            boolean.class},l_1mountCalcResult);

            OrderSubmissionResult l_orderSubmissionResult =  new OrderSubmissionResult(processingResult);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
					"webbroker3.ifo.WEB3OptionOrderManagerImpl",
					"submitSettleContractOrder",
					new Class[] {SubAccount.class,
							IfoSettleContractOrderSpec.class,
							long.class,
							String.class,
							boolean.class},l_orderSubmissionResult);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.triggerorder.base.service.delegate.stdimpls.WEB3ToSuccReservationIfoOrderUpdateServiceImpl",
                    "insertReserveOrderAction", new Class[]
                    {long.class},null);
            
            WEB3ToSuccIfoOrderUnitImplForTest1 l_rsvIfoOrderUnit= new WEB3ToSuccIfoOrderUnitImplForTest1(l_rsvIfoOrderUnitParams);

        	l_service.submitOptionsSettleContractOrder(l_rsvIfoOrderUnit);
        }
        catch(WEB3BaseException l_ex)
        {
            log.error("", l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();

        }
        catch(Exception l_ex)
        {
            log.error("", l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
	}

	//先物OP予約建玉返済指定情報一覧( )==null
	public void testSubmitFuturesSettleContractOrder_C0001()
	{
		final String STR_METHOD_NAME = "testSubmitFuturesSettleContractOrder_C0001()";
        log.entering(TEST_START + STR_METHOD_NAME);
        WEB3ToSuccIfoOrderUnitServiceImpl l_service= new WEB3ToSuccIfoOrderUnitServiceImpl();
        try
        {
        	TestDBUtility.deleteAll(RsvIfoOrderUnitRow.TYPE);
            RsvIfoOrderUnitParams  l_rsvIfoOrderUnitParams =TestDBUtility.getRsvIfoOrderUnitRow();
            l_rsvIfoOrderUnitParams.setBizDate("20080512");
            l_rsvIfoOrderUnitParams.setMarketId(3303L);
            l_rsvIfoOrderUnitParams.setProductId(3304148080000L);
            TestDBUtility.insertWithDel(l_rsvIfoOrderUnitParams);
            
            TestDBUtility.deleteAll(TradingTimeRow.TYPE);
            TradingTimeParams  l_tradingTimeParams =TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams.setInstitutionCode("0D");
            l_tradingTimeParams.setBranchCode("123");
            l_tradingTimeParams.setMarketCode("N1");
            l_tradingTimeParams.setTradingTimeType("01");
            l_tradingTimeParams.setProductCode("0");
            l_tradingTimeParams.setBizDateType("1");
            TestDBUtility.insertWithDel(l_tradingTimeParams);
            
            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setAccountId(333812512203L);
            l_subAccountParams.setSubAccountId(33381251220301L);
            TestDBUtility.insertWithDel(l_subAccountParams);
            
            ThreadLocalSystemAttributesRegistry.setAttribute(
                "xblocks.gtl.attributes.systemtimestamp",
                new Timestamp(WEB3DateUtility.getDate("20080509", "yyyyMMdd").getTime()));
            
            ThreadLocalSystemAttributesRegistry.setAttribute(
            	"web3.attributes.basetimestampfororderbizdate", null);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.triggerorder.base.service.delegate.stdimpls.WEB3ToSuccReservationIfoOrderUpdateServiceImpl",
                    "invalidateOrderUnit", new Class[]
                    {RsvIfoOrderUnitRow.class,String.class},new WEB3BaseException(WEB3ErrorCatalog.BUSINESS_ERROR_02339,""));
            
        	WEB3ToSuccIfoOrderUnitImplForTest l_rsvIfoOrderUnit= new WEB3ToSuccIfoOrderUnitImplForTest(l_rsvIfoOrderUnitParams);

        	l_service.submitFuturesSettleContractOrder(l_rsvIfoOrderUnit);
            fail();
        }
        catch(WEB3BaseException l_ex)
        {
            log.error("", l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02339, l_ex.getErrorInfo());

        }
        catch(Exception l_ex)
        {
            log.error("", l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
	}

	//分岐フロー：　@予約注文単位.取引者ID≠nullの場合のみ
	public void testSubmitFuturesSettleContractOrder_C0002()
	{
		final String STR_METHOD_NAME = "testSubmitFuturesSettleContractOrder_C0002()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        WEB3ToSuccIfoOrderUnitServiceImplForTest l_service =new WEB3ToSuccIfoOrderUnitServiceImplForTest();
        try
        {
            
            TestDBUtility.deleteAll(TraderRow.TYPE);
            TraderParams l_traderParams  =TestDBUtility.getTraderRow();
            l_traderParams.setTraderId(1000);
            TestDBUtility.insertWithDel(l_traderParams);
            
            TestDBUtility.deleteAll(RsvIfoOrderUnitRow.TYPE);
            RsvIfoOrderUnitParams  l_rsvIfoOrderUnitParams =TestDBUtility.getRsvIfoOrderUnitRow();
            l_rsvIfoOrderUnitParams.setBizDate("20080512");
            l_rsvIfoOrderUnitParams.setMarketId(3303L);
            l_rsvIfoOrderUnitParams.setProductId(3304148080000L);
            l_rsvIfoOrderUnitParams.setTraderId(1000);
            //l_rsvIfoOrderUnitParams.setFirstOrderUnitId(1000);
            TestDBUtility.insertWithDel(l_rsvIfoOrderUnitParams);
            
            TestDBUtility.deleteAll(TradingTimeRow.TYPE);
            TradingTimeParams  l_tradingTimeParams =TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams.setInstitutionCode("0D");
            l_tradingTimeParams.setBranchCode("123");
            l_tradingTimeParams.setMarketCode("N1");
            l_tradingTimeParams.setTradingTimeType("01");
            l_tradingTimeParams.setProductCode("0");
            l_tradingTimeParams.setBizDateType("1");
            TestDBUtility.insertWithDel(l_tradingTimeParams);
            
            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setAccountId(333812512203L);
            l_subAccountParams.setSubAccountId(33381251220301L);
            TestDBUtility.insertWithDel(l_subAccountParams);
            
            TestDBUtility.deleteAll(IfoContractRow.TYPE);
            IfoContractParams l_ifoContractParams = TestDBUtility.getIfoContractRow();
            l_ifoContractParams.setContractId(1);
            TestDBUtility.insertWithDel(l_ifoContractParams);
            
            ThreadLocalSystemAttributesRegistry.setAttribute(
                "xblocks.gtl.attributes.systemtimestamp",
                new Timestamp(WEB3DateUtility.getDate("20080509", "yyyyMMdd").getTime()));
            
            ThreadLocalSystemAttributesRegistry.setAttribute(
            	"web3.attributes.basetimestampfororderbizdate", null);
            
            ProcessingResult processingResult = ProcessingResult.newSuccessResultInstance();
            NewOrderValidationResult l_result = new NewOrderValidationResult(processingResult);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
					"webbroker3.ifo.WEB3OptionOrderManagerImpl",
					"validateSettleContractOrder",
					new Class[] { WEB3GentradeSubAccount.class, WEB3IfoSettleContractOrderSpec.class },
					l_result);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
					"webbroker3.ifo.WEB3OptionOrderManagerImpl",
					"validateSettleContractOrder",
					new Class[] { WEB3GentradeSubAccount.class, WEB3IfoSettleContractOrderSpec.class },
					l_result);
            
            String l_str="1000";
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
					"webbroker3.ifo.WEB3OptionOrderManagerImpl",
					"getDayTradeType",
					new Class[] { SettleContractEntry[].class },l_str);
            
            WEB3IfoEstimateDeliveryAmountCalcResult l_1mountCalcResult =new WEB3IfoEstimateDeliveryAmountCalcResult();
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
					"webbroker3.ifo.WEB3OptionOrderManagerImpl",
					"calcEstimateDeliveryAmount",
					new Class[] { WEB3GentradeCommission.class,
							double.class,
				            SubAccount.class,
				            WEB3IfoTradedProductImpl.class,
				            double.class,
				            SideEnum.class,
				            boolean.class,
				            boolean.class},l_1mountCalcResult);

            OrderSubmissionResult l_orderSubmissionResult =  new OrderSubmissionResult(processingResult);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
					"webbroker3.ifo.WEB3OptionOrderManagerImpl",
					"submitSettleContractOrder",
					new Class[] {SubAccount.class,
							IfoSettleContractOrderSpec.class,
							long.class,
							String.class,
							boolean.class},l_orderSubmissionResult);
                    
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.triggerorder.base.service.delegate.stdimpls.WEB3ToSuccReservationIfoOrderUpdateServiceImpl",
                    "insertReserveOrderAction", new Class[]
                    {long.class},null);
            
            WEB3ToSuccIfoOrderUnitImplForTest1 l_rsvIfoOrderUnit= new WEB3ToSuccIfoOrderUnitImplForTest1(l_rsvIfoOrderUnitParams);

        	l_service.submitFuturesSettleContractOrder(l_rsvIfoOrderUnit);
        }
        catch(WEB3BaseException l_ex)
        {
            log.error("", l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();

        }
        catch(Exception l_ex)
        {
            log.error("", l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
	}

	//分岐フロー：　@予約注文単位.取引者ID=nullの場合のみ
	//[create返済注文内容( )：引数設定仕様]
	//!l_rsvIfoOrderUnitRow.getFirstOrderUnitIdIsNull()
	public void testSubmitFuturesSettleContractOrder_C0003()
	{
		final String STR_METHOD_NAME = "testSubmitFuturesSettleContractOrder_C0003()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        WEB3ToSuccIfoOrderUnitServiceImplForTest l_service =new WEB3ToSuccIfoOrderUnitServiceImplForTest();
        try
        {

        	TestDBUtility.deleteAll(RsvIfoOrderUnitRow.TYPE);
            RsvIfoOrderUnitParams  l_rsvIfoOrderUnitParams =TestDBUtility.getRsvIfoOrderUnitRow();
            l_rsvIfoOrderUnitParams.setBizDate("20080512");
            l_rsvIfoOrderUnitParams.setMarketId(3303L);
            l_rsvIfoOrderUnitParams.setProductId(3304148080000L);
            //l_rsvIfoOrderUnitParams.setTraderId(1000);
            l_rsvIfoOrderUnitParams.setFirstOrderUnitId(1000);
            TestDBUtility.insertWithDel(l_rsvIfoOrderUnitParams);
            
            TestDBUtility.deleteAll(TradingTimeRow.TYPE);
            TradingTimeParams  l_tradingTimeParams =TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams.setInstitutionCode("0D");
            l_tradingTimeParams.setBranchCode("123");
            l_tradingTimeParams.setMarketCode("N1");
            l_tradingTimeParams.setTradingTimeType("01");
            l_tradingTimeParams.setProductCode("0");
            l_tradingTimeParams.setBizDateType("1");
            TestDBUtility.insertWithDel(l_tradingTimeParams);
            
            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setAccountId(333812512203L);
            l_subAccountParams.setSubAccountId(33381251220301L);
            TestDBUtility.insertWithDel(l_subAccountParams);
            
            TestDBUtility.deleteAll(IfoContractRow.TYPE);
            IfoContractParams l_ifoContractParams = TestDBUtility.getIfoContractRow();
            l_ifoContractParams.setContractId(1);
            TestDBUtility.insertWithDel(l_ifoContractParams);
            
            ThreadLocalSystemAttributesRegistry.setAttribute(
                "xblocks.gtl.attributes.systemtimestamp",
                new Timestamp(WEB3DateUtility.getDate("20080509", "yyyyMMdd").getTime()));
            
            ThreadLocalSystemAttributesRegistry.setAttribute(
            	"web3.attributes.basetimestampfororderbizdate", null);
            
            ProcessingResult processingResult = ProcessingResult.newSuccessResultInstance();
            NewOrderValidationResult l_result = new NewOrderValidationResult(processingResult);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
					"webbroker3.ifo.WEB3OptionOrderManagerImpl",
					"validateSettleContractOrder",
					new Class[] { WEB3GentradeSubAccount.class, WEB3IfoSettleContractOrderSpec.class },
					l_result);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
					"webbroker3.ifo.WEB3OptionOrderManagerImpl",
					"validateSettleContractOrder",
					new Class[] { WEB3GentradeSubAccount.class, WEB3IfoSettleContractOrderSpec.class },
					l_result);
            
            String l_str="1000";
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
					"webbroker3.ifo.WEB3OptionOrderManagerImpl",
					"getDayTradeType",
					new Class[] { SettleContractEntry[].class },l_str);
            
            WEB3IfoEstimateDeliveryAmountCalcResult l_1mountCalcResult =new WEB3IfoEstimateDeliveryAmountCalcResult();
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
					"webbroker3.ifo.WEB3OptionOrderManagerImpl",
					"calcEstimateDeliveryAmount",
					new Class[] { WEB3GentradeCommission.class,
							double.class,
				            SubAccount.class,
				            WEB3IfoTradedProductImpl.class,
				            double.class,
				            SideEnum.class,
				            boolean.class,
				            boolean.class},l_1mountCalcResult);

            OrderSubmissionResult l_orderSubmissionResult =  new OrderSubmissionResult(processingResult);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
					"webbroker3.ifo.WEB3OptionOrderManagerImpl",
					"submitSettleContractOrder",
					new Class[] {SubAccount.class,
							IfoSettleContractOrderSpec.class,
							long.class,
							String.class,
							boolean.class},l_orderSubmissionResult);
                    
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.triggerorder.base.service.delegate.stdimpls.WEB3ToSuccReservationIfoOrderUpdateServiceImpl",
                    "insertReserveOrderAction", new Class[]
                    {long.class},null);
            
            WEB3ToSuccIfoOrderUnitImplForTest1 l_rsvIfoOrderUnit= new WEB3ToSuccIfoOrderUnitImplForTest1(l_rsvIfoOrderUnitParams);

        	l_service.submitFuturesSettleContractOrder(l_rsvIfoOrderUnit);
        }
        catch(WEB3BaseException l_ex)
        {
            log.error("", l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();

        }
        catch(Exception l_ex)
        {
            log.error("", l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
	}

	//[create返済注文内容( )：引数設定仕様]
	//l_rsvIfoOrderUnitRow.getFirstOrderUnitIdIsNull()
	//l_rsvIfoOrderUnitRow.getEveningSessionCarryoverFlag() = false
	public void testSubmitFuturesSettleContractOrder_C0004()
	{
		final String STR_METHOD_NAME = "testSubmitFuturesSettleContractOrder_C0004()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        WEB3ToSuccIfoOrderUnitServiceImplForTest l_service =new WEB3ToSuccIfoOrderUnitServiceImplForTest();
        try
        {

        	TestDBUtility.deleteAll(RsvIfoOrderUnitRow.TYPE);
            RsvIfoOrderUnitParams  l_rsvIfoOrderUnitParams =TestDBUtility.getRsvIfoOrderUnitRow();
            l_rsvIfoOrderUnitParams.setBizDate("20080512");
            l_rsvIfoOrderUnitParams.setMarketId(3303L);
            l_rsvIfoOrderUnitParams.setProductId(3304148080000L);
            //l_rsvIfoOrderUnitParams.setTraderId(1000);
            //l_rsvIfoOrderUnitParams.setFirstOrderUnitId(1000);
            TestDBUtility.insertWithDel(l_rsvIfoOrderUnitParams);
            
            TestDBUtility.deleteAll(TradingTimeRow.TYPE);
            TradingTimeParams  l_tradingTimeParams =TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams.setInstitutionCode("0D");
            l_tradingTimeParams.setBranchCode("123");
            l_tradingTimeParams.setMarketCode("N1");
            l_tradingTimeParams.setTradingTimeType("01");
            l_tradingTimeParams.setProductCode("0");
            l_tradingTimeParams.setBizDateType("1");
            TestDBUtility.insertWithDel(l_tradingTimeParams);
            
            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setAccountId(333812512203L);
            l_subAccountParams.setSubAccountId(33381251220301L);
            TestDBUtility.insertWithDel(l_subAccountParams);
            
            TestDBUtility.deleteAll(IfoContractRow.TYPE);
            IfoContractParams l_ifoContractParams = TestDBUtility.getIfoContractRow();
            l_ifoContractParams.setContractId(1);
            TestDBUtility.insertWithDel(l_ifoContractParams);
            
            ThreadLocalSystemAttributesRegistry.setAttribute(
                "xblocks.gtl.attributes.systemtimestamp",
                new Timestamp(WEB3DateUtility.getDate("20080509", "yyyyMMdd").getTime()));
            
            ThreadLocalSystemAttributesRegistry.setAttribute(
            	"web3.attributes.basetimestampfororderbizdate", null);
            
            ProcessingResult processingResult = ProcessingResult.newSuccessResultInstance();
            NewOrderValidationResult l_result = new NewOrderValidationResult(processingResult);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
					"webbroker3.ifo.WEB3OptionOrderManagerImpl",
					"validateSettleContractOrder",
					new Class[] { WEB3GentradeSubAccount.class, WEB3IfoSettleContractOrderSpec.class },
					l_result);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
					"webbroker3.ifo.WEB3OptionOrderManagerImpl",
					"validateSettleContractOrder",
					new Class[] { WEB3GentradeSubAccount.class, WEB3IfoSettleContractOrderSpec.class },
					l_result);
            
            String l_str="1000";
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
					"webbroker3.ifo.WEB3OptionOrderManagerImpl",
					"getDayTradeType",
					new Class[] { SettleContractEntry[].class },l_str);
            
            WEB3IfoEstimateDeliveryAmountCalcResult l_1mountCalcResult =new WEB3IfoEstimateDeliveryAmountCalcResult();
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
					"webbroker3.ifo.WEB3OptionOrderManagerImpl",
					"calcEstimateDeliveryAmount",
					new Class[] { WEB3GentradeCommission.class,
							double.class,
				            SubAccount.class,
				            WEB3IfoTradedProductImpl.class,
				            double.class,
				            SideEnum.class,
				            boolean.class,
				            boolean.class},l_1mountCalcResult);

            OrderSubmissionResult l_orderSubmissionResult =  new OrderSubmissionResult(processingResult);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
					"webbroker3.ifo.WEB3OptionOrderManagerImpl",
					"submitSettleContractOrder",
					new Class[] {SubAccount.class,
							IfoSettleContractOrderSpec.class,
							long.class,
							String.class,
							boolean.class},l_orderSubmissionResult);
                    
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.triggerorder.base.service.delegate.stdimpls.WEB3ToSuccReservationIfoOrderUpdateServiceImpl",
                    "insertReserveOrderAction", new Class[]
                    {long.class},null);
            
            WEB3ToSuccIfoOrderUnitImplForTest1 l_rsvIfoOrderUnit= new WEB3ToSuccIfoOrderUnitImplForTest1(l_rsvIfoOrderUnitParams);

        	l_service.submitFuturesSettleContractOrder(l_rsvIfoOrderUnit);
        }
        catch(WEB3BaseException l_ex)
        {
            log.error("", l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();

        }
        catch(Exception l_ex)
        {
            log.error("", l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
	}

	//l_rsvIfoOrderUnitRow.getFirstOrderUnitIdIsNull()
	//l_rsvIfoOrderUnitRow.getEveningSessionCarryoverFlag() = true
	//validate返済注文(補助口座 : SubAccount, 信用返済注文内容 : EqTypeSettleContractOrderSpec)
	public void testSubmitFuturesSettleContractOrder_C0005()
	{
		final String STR_METHOD_NAME = "testSubmitFuturesSettleContractOrder_C0005()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        WEB3ToSuccIfoOrderUnitServiceImplForTest l_service =new WEB3ToSuccIfoOrderUnitServiceImplForTest();
        try
        {

        	TestDBUtility.deleteAll(RsvIfoOrderUnitRow.TYPE);
            RsvIfoOrderUnitParams  l_rsvIfoOrderUnitParams =TestDBUtility.getRsvIfoOrderUnitRow();
            l_rsvIfoOrderUnitParams.setBizDate("20080512");
            l_rsvIfoOrderUnitParams.setMarketId(3303L);
            l_rsvIfoOrderUnitParams.setProductId(3304148080000L);
            //l_rsvIfoOrderUnitParams.setTraderId(1000);
            //l_rsvIfoOrderUnitParams.setFirstOrderUnitId(1000);
            TestDBUtility.insertWithDel(l_rsvIfoOrderUnitParams);
            
            TestDBUtility.deleteAll(TradingTimeRow.TYPE);
            TradingTimeParams  l_tradingTimeParams =TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams.setInstitutionCode("0D");
            l_tradingTimeParams.setBranchCode("123");
            l_tradingTimeParams.setMarketCode("N1");
            l_tradingTimeParams.setTradingTimeType("01");
            l_tradingTimeParams.setProductCode("0");
            l_tradingTimeParams.setBizDateType("1");
            TestDBUtility.insertWithDel(l_tradingTimeParams);
            
            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setAccountId(333812512203L);
            l_subAccountParams.setSubAccountId(33381251220301L);
            TestDBUtility.insertWithDel(l_subAccountParams);
            
            ThreadLocalSystemAttributesRegistry.setAttribute(
                "xblocks.gtl.attributes.systemtimestamp",
                new Timestamp(WEB3DateUtility.getDate("20080509", "yyyyMMdd").getTime()));
            
            ThreadLocalSystemAttributesRegistry.setAttribute(
            	"web3.attributes.basetimestampfororderbizdate", null);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.triggerorder.base.service.delegate.stdimpls.WEB3ToSuccReservationIfoOrderUpdateServiceImpl",
                    "invalidateOrderUnit", new Class[]
                    {RsvIfoOrderUnitRow.class,String.class},new WEB3BaseException(WEB3ErrorCatalog.BUSINESS_ERROR_00283,""));
            						
            WEB3ToSuccIfoOrderUnitImplForTest1 l_rsvIfoOrderUnit= new WEB3ToSuccIfoOrderUnitImplForTest1(l_rsvIfoOrderUnitParams);

        	l_service.submitFuturesSettleContractOrder(l_rsvIfoOrderUnit);
            fail();
        }
        catch(WEB3BaseException l_ex)
        {
            log.error("", l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00283, l_ex.getErrorInfo());

        }
        catch(Exception l_ex)
        {
            log.error("", l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
	}

	//l_rsvIfoOrderUnitRow.getFirstOrderUnitIdIsNull()
	//l_rsvIfoOrderUnitRow.getEveningSessionCarryoverFlag() = true
	//{validate返済注文()}が失敗の場合。
	public void testSubmitFuturesSettleContractOrder_C0006()
	{
		final String STR_METHOD_NAME = "testSubmitFuturesSettleContractOrder_C0006()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        WEB3ToSuccIfoOrderUnitServiceImplForTest l_service =new WEB3ToSuccIfoOrderUnitServiceImplForTest();
        try
        {

        	TestDBUtility.deleteAll(RsvIfoOrderUnitRow.TYPE);
            RsvIfoOrderUnitParams  l_rsvIfoOrderUnitParams =TestDBUtility.getRsvIfoOrderUnitRow();
            l_rsvIfoOrderUnitParams.setBizDate("20080512");
            l_rsvIfoOrderUnitParams.setMarketId(3303L);
            l_rsvIfoOrderUnitParams.setProductId(3304148080000L);
            //l_rsvIfoOrderUnitParams.setTraderId(1000);
            //l_rsvIfoOrderUnitParams.setFirstOrderUnitId(1000);
            TestDBUtility.insertWithDel(l_rsvIfoOrderUnitParams);
            
            TestDBUtility.deleteAll(TradingTimeRow.TYPE);
            TradingTimeParams  l_tradingTimeParams =TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams.setInstitutionCode("0D");
            l_tradingTimeParams.setBranchCode("123");
            l_tradingTimeParams.setMarketCode("N1");
            l_tradingTimeParams.setTradingTimeType("01");
            l_tradingTimeParams.setProductCode("0");
            l_tradingTimeParams.setBizDateType("1");
            TestDBUtility.insertWithDel(l_tradingTimeParams);
            
            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setAccountId(333812512203L);
            l_subAccountParams.setSubAccountId(33381251220301L);
            TestDBUtility.insertWithDel(l_subAccountParams);
            
            ThreadLocalSystemAttributesRegistry.setAttribute(
                "xblocks.gtl.attributes.systemtimestamp",
                new Timestamp(WEB3DateUtility.getDate("20080509", "yyyyMMdd").getTime()));
            
            ThreadLocalSystemAttributesRegistry.setAttribute(
            	"web3.attributes.basetimestampfororderbizdate", null);
            
            //ProcessingResult processingResult = ProcessingResult.newSuccessResultInstance();
            ProcessingResult processingResult = null;
            ErrorInfo errorInfo= new ErrorInfo();
            errorInfo.setErrorCode("BUSINESS_ERROR_00282");
            NewOrderValidationResult l_result = new NewOrderValidationResult(processingResult);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
					"webbroker3.ifo.WEB3OptionOrderManagerImpl",
					"validateSettleContractOrder",
					new Class[] { WEB3GentradeSubAccount.class, WEB3IfoSettleContractOrderSpec.class },
					l_result);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.triggerorder.base.service.delegate.stdimpls.WEB3ToSuccReservationIfoOrderUpdateServiceImpl",
                    "invalidateOrderUnit", new Class[]
                    {RsvIfoOrderUnitRow.class,String.class},new WEB3BaseException(WEB3ErrorCatalog.BUSINESS_ERROR_00282,""));
   
            WEB3ToSuccIfoOrderUnitImplForTest1 l_rsvIfoOrderUnit= new WEB3ToSuccIfoOrderUnitImplForTest1(l_rsvIfoOrderUnitParams);

        	l_service.submitFuturesSettleContractOrder(l_rsvIfoOrderUnit);
            fail();
        }
        catch(WEB3BaseException l_ex)
        {
            log.error("", l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00282, l_ex.getErrorInfo());

        }
        catch(Exception l_ex)
        {
            log.error("", l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
	}

	//l_rsvIfoOrderUnitRow.getFirstOrderUnitIdIsNull()
	//l_rsvIfoOrderUnitRow.getEveningSessionCarryoverFlag() = true
	//建玉.isLong() == trueの場合
	//correct case
	public void testSubmitFuturesSettleContractOrder_C0007()
	{
		final String STR_METHOD_NAME = "testSubmitFuturesSettleContractOrder_C0007()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        WEB3ToSuccIfoOrderUnitServiceImplForTest l_service =new WEB3ToSuccIfoOrderUnitServiceImplForTest();
        try
        {
        	TestDBUtility.deleteAll(RsvIfoOrderUnitRow.TYPE);
            RsvIfoOrderUnitParams  l_rsvIfoOrderUnitParams =TestDBUtility.getRsvIfoOrderUnitRow();
            l_rsvIfoOrderUnitParams.setBizDate("20080512");
            l_rsvIfoOrderUnitParams.setMarketId(3303L);
            l_rsvIfoOrderUnitParams.setProductId(3304148080000L);
            //l_rsvIfoOrderUnitParams.setTraderId(1000);
            //l_rsvIfoOrderUnitParams.setFirstOrderUnitId(1000);
            TestDBUtility.insertWithDel(l_rsvIfoOrderUnitParams);
            
            TestDBUtility.deleteAll(TradingTimeRow.TYPE);
            TradingTimeParams  l_tradingTimeParams =TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams.setInstitutionCode("0D");
            l_tradingTimeParams.setBranchCode("123");
            l_tradingTimeParams.setMarketCode("N1");
            l_tradingTimeParams.setTradingTimeType("01");
            l_tradingTimeParams.setProductCode("0");
            l_tradingTimeParams.setBizDateType("1");
            TestDBUtility.insertWithDel(l_tradingTimeParams);
            
            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setAccountId(333812512203L);
            l_subAccountParams.setSubAccountId(33381251220301L);
            TestDBUtility.insertWithDel(l_subAccountParams);
            
            TestDBUtility.deleteAll(IfoContractRow.TYPE);
            IfoContractParams l_ifoContractParams = TestDBUtility.getIfoContractRow();
            l_ifoContractParams.setContractId(1);
            TestDBUtility.insertWithDel(l_ifoContractParams);
            
            ThreadLocalSystemAttributesRegistry.setAttribute(
                "xblocks.gtl.attributes.systemtimestamp",
                new Timestamp(WEB3DateUtility.getDate("20080509", "yyyyMMdd").getTime()));
            
            ThreadLocalSystemAttributesRegistry.setAttribute(
            	"web3.attributes.basetimestampfororderbizdate", null);
            
            ProcessingResult processingResult = ProcessingResult.newSuccessResultInstance();
            NewOrderValidationResult l_result = new NewOrderValidationResult(processingResult);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
					"webbroker3.ifo.WEB3OptionOrderManagerImpl",
					"validateSettleContractOrder",
					new Class[] { WEB3GentradeSubAccount.class, WEB3IfoSettleContractOrderSpec.class },
					l_result);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
					"webbroker3.ifo.WEB3OptionOrderManagerImpl",
					"validateSettleContractOrder",
					new Class[] { WEB3GentradeSubAccount.class, WEB3IfoSettleContractOrderSpec.class },
					l_result);
            
            String l_str="1000";
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
					"webbroker3.ifo.WEB3OptionOrderManagerImpl",
					"getDayTradeType",
					new Class[] { SettleContractEntry[].class },l_str);
            
            WEB3IfoEstimateDeliveryAmountCalcResult l_1mountCalcResult =new WEB3IfoEstimateDeliveryAmountCalcResult();
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
					"webbroker3.ifo.WEB3OptionOrderManagerImpl",
					"calcEstimateDeliveryAmount",
					new Class[] { WEB3GentradeCommission.class,
							double.class,
				            SubAccount.class,
				            WEB3IfoTradedProductImpl.class,
				            double.class,
				            SideEnum.class,
				            boolean.class,
				            boolean.class},l_1mountCalcResult);

            OrderSubmissionResult l_orderSubmissionResult =  new OrderSubmissionResult(processingResult);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
					"webbroker3.ifo.WEB3OptionOrderManagerImpl",
					"submitSettleContractOrder",
					new Class[] {SubAccount.class,
							IfoSettleContractOrderSpec.class,
							long.class,
							String.class,
							boolean.class},l_orderSubmissionResult);
                    
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.triggerorder.base.service.delegate.stdimpls.WEB3ToSuccReservationIfoOrderUpdateServiceImpl",
                    "insertReserveOrderAction", new Class[]
                    {long.class},null);
            
            WEB3ToSuccIfoOrderUnitImplForTest1 l_rsvIfoOrderUnit= new WEB3ToSuccIfoOrderUnitImplForTest1(l_rsvIfoOrderUnitParams);

        	l_service.submitFuturesSettleContractOrder(l_rsvIfoOrderUnit);
        }
        catch(WEB3BaseException l_ex)
        {
            log.error("", l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();

        }
        catch(Exception l_ex)
        {
            log.error("", l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
	}

    //l_rsvIfoOrderUnitRow.getFirstOrderUnitIdIsNull()
	//l_rsvIfoOrderUnitRow.getEveningSessionCarryoverFlag() = true
	//建玉.isLong() == falseの場合
	//correct case
	public void testSubmitFuturesSettleContractOrder_C0008()
	{
		final String STR_METHOD_NAME = "testSubmitFuturesSettleContractOrder_C0008()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        WEB3ToSuccIfoOrderUnitServiceImplForTest l_service =new WEB3ToSuccIfoOrderUnitServiceImplForTest();
        try
        {
        	TestDBUtility.deleteAll(RsvIfoOrderUnitRow.TYPE);
            RsvIfoOrderUnitParams  l_rsvIfoOrderUnitParams =TestDBUtility.getRsvIfoOrderUnitRow();
            l_rsvIfoOrderUnitParams.setBizDate("20080512");
            l_rsvIfoOrderUnitParams.setMarketId(3303L);
            l_rsvIfoOrderUnitParams.setProductId(3304148080000L);
            //l_rsvIfoOrderUnitParams.setTraderId(1000);
            //l_rsvIfoOrderUnitParams.setFirstOrderUnitId(1000);
            TestDBUtility.insertWithDel(l_rsvIfoOrderUnitParams);
            
            TestDBUtility.deleteAll(TradingTimeRow.TYPE);
            TradingTimeParams  l_tradingTimeParams =TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams.setInstitutionCode("0D");
            l_tradingTimeParams.setBranchCode("123");
            l_tradingTimeParams.setMarketCode("N1");
            l_tradingTimeParams.setTradingTimeType("01");
            l_tradingTimeParams.setProductCode("0");
            l_tradingTimeParams.setBizDateType("1");
            TestDBUtility.insertWithDel(l_tradingTimeParams);
            
            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setAccountId(333812512203L);
            l_subAccountParams.setSubAccountId(33381251220301L);
            TestDBUtility.insertWithDel(l_subAccountParams);
            
            TestDBUtility.deleteAll(IfoContractRow.TYPE);
            IfoContractParams l_ifoContractParams = TestDBUtility.getIfoContractRow();
            l_ifoContractParams.setContractId(1);
            TestDBUtility.insertWithDel(l_ifoContractParams);
            
            ThreadLocalSystemAttributesRegistry.setAttribute(
                "xblocks.gtl.attributes.systemtimestamp",
                new Timestamp(WEB3DateUtility.getDate("20080509", "yyyyMMdd").getTime()));
            
            ThreadLocalSystemAttributesRegistry.setAttribute(
            	"web3.attributes.basetimestampfororderbizdate", null);
            
            ProcessingResult processingResult = ProcessingResult.newSuccessResultInstance();
            NewOrderValidationResult l_result = new NewOrderValidationResult(processingResult);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
					"webbroker3.ifo.WEB3OptionOrderManagerImpl",
					"validateSettleContractOrder",
					new Class[] { WEB3GentradeSubAccount.class, WEB3IfoSettleContractOrderSpec.class },
					l_result);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
					"webbroker3.ifo.WEB3OptionOrderManagerImpl",
					"validateSettleContractOrder",
					new Class[] { WEB3GentradeSubAccount.class, WEB3IfoSettleContractOrderSpec.class },
					l_result);
            
            String l_str="1000";
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
					"webbroker3.ifo.WEB3OptionOrderManagerImpl",
					"getDayTradeType",
					new Class[] { SettleContractEntry[].class },l_str);
            
            WEB3IfoEstimateDeliveryAmountCalcResult l_1mountCalcResult =new WEB3IfoEstimateDeliveryAmountCalcResult();
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
					"webbroker3.ifo.WEB3OptionOrderManagerImpl",
					"calcEstimateDeliveryAmount",
					new Class[] { WEB3GentradeCommission.class,
							double.class,
				            SubAccount.class,
				            WEB3IfoTradedProductImpl.class,
				            double.class,
				            SideEnum.class,
				            boolean.class,
				            boolean.class},l_1mountCalcResult);

            OrderSubmissionResult l_orderSubmissionResult =  new OrderSubmissionResult(processingResult);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
					"webbroker3.ifo.WEB3OptionOrderManagerImpl",
					"submitSettleContractOrder",
					new Class[] {SubAccount.class,
							IfoSettleContractOrderSpec.class,
							long.class,
							String.class,
							boolean.class},l_orderSubmissionResult);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.triggerorder.base.service.delegate.stdimpls.WEB3ToSuccReservationIfoOrderUpdateServiceImpl",
                    "insertReserveOrderAction", new Class[]
                    {long.class},null);
            
            WEB3ToSuccIfoOrderUnitImplForTest1 l_rsvIfoOrderUnit= new WEB3ToSuccIfoOrderUnitImplForTest1(l_rsvIfoOrderUnitParams);

        	l_service.submitFuturesSettleContractOrder(l_rsvIfoOrderUnit);
        }
        catch(WEB3BaseException l_ex)
        {
            log.error("", l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();

        }
        catch(Exception l_ex)
        {
            log.error("", l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
	}

	//実行結果に応じ、予約注文単位をupdateするOK
	public void testSubmitFuturesSettleContractOrder_C0009()
	{
		final String STR_METHOD_NAME = "testSubmitFuturesSettleContractOrder_C0009()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        WEB3ToSuccIfoOrderUnitServiceImplForTest l_service =new WEB3ToSuccIfoOrderUnitServiceImplForTest();
        try
        {
            	TestDBUtility.deleteAll(RsvIfoOrderUnitRow.TYPE);
                RsvIfoOrderUnitParams  l_rsvIfoOrderUnitParams =TestDBUtility.getRsvIfoOrderUnitRow();
                l_rsvIfoOrderUnitParams.setBizDate("20080512");
                l_rsvIfoOrderUnitParams.setMarketId(3303L);
                l_rsvIfoOrderUnitParams.setProductId(3304148080000L);
                //l_rsvIfoOrderUnitParams.setTraderId(1000);
                //l_rsvIfoOrderUnitParams.setFirstOrderUnitId(1000);
                TestDBUtility.insertWithDel(l_rsvIfoOrderUnitParams);
                
                TestDBUtility.deleteAll(TradingTimeRow.TYPE);
                TradingTimeParams  l_tradingTimeParams =TestDBUtility.getTradingTimeRow();
                l_tradingTimeParams.setInstitutionCode("0D");
                l_tradingTimeParams.setBranchCode("123");
                l_tradingTimeParams.setMarketCode("N1");
                l_tradingTimeParams.setTradingTimeType("01");
                l_tradingTimeParams.setProductCode("0");
                l_tradingTimeParams.setBizDateType("1");
                TestDBUtility.insertWithDel(l_tradingTimeParams);
                
                TestDBUtility.deleteAll(SubAccountRow.TYPE);
                SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
                l_subAccountParams.setAccountId(333812512203L);
                l_subAccountParams.setSubAccountId(33381251220301L);
                TestDBUtility.insertWithDel(l_subAccountParams);
                
                TestDBUtility.deleteAll(IfoContractRow.TYPE);
                IfoContractParams l_ifoContractParams = TestDBUtility.getIfoContractRow();
                l_ifoContractParams.setContractId(1);
                l_ifoContractParams.setContractType(ContractTypeEnum.SHORT);
                TestDBUtility.insertWithDel(l_ifoContractParams);
                
                ThreadLocalSystemAttributesRegistry.setAttribute(
                    "xblocks.gtl.attributes.systemtimestamp",
                    new Timestamp(WEB3DateUtility.getDate("20080509", "yyyyMMdd").getTime()));
                
                ThreadLocalSystemAttributesRegistry.setAttribute(
                	"web3.attributes.basetimestampfororderbizdate", null);
                
                ProcessingResult processingResult = ProcessingResult.newSuccessResultInstance();
                NewOrderValidationResult l_result = new NewOrderValidationResult(processingResult);
                TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
    					"webbroker3.ifo.WEB3OptionOrderManagerImpl",
    					"validateSettleContractOrder",
    					new Class[] { WEB3GentradeSubAccount.class, WEB3IfoSettleContractOrderSpec.class },
    					l_result);
                
                TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
    					"webbroker3.ifo.WEB3OptionOrderManagerImpl",
    					"validateSettleContractOrder",
    					new Class[] { WEB3GentradeSubAccount.class, WEB3IfoSettleContractOrderSpec.class },
    					l_result);
                
                String l_str="1000";
                TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
    					"webbroker3.ifo.WEB3OptionOrderManagerImpl",
    					"getDayTradeType",
    					new Class[] { SettleContractEntry[].class },l_str);
                
                WEB3IfoEstimateDeliveryAmountCalcResult l_1mountCalcResult =new WEB3IfoEstimateDeliveryAmountCalcResult();
                TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
    					"webbroker3.ifo.WEB3OptionOrderManagerImpl",
    					"calcEstimateDeliveryAmount",
    					new Class[] { WEB3GentradeCommission.class,
    							double.class,
    				            SubAccount.class,
    				            WEB3IfoTradedProductImpl.class,
    				            double.class,
    				            SideEnum.class,
    				            boolean.class,
    				            boolean.class},l_1mountCalcResult);

                OrderSubmissionResult l_orderSubmissionResult =  new OrderSubmissionResult(processingResult);
                TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
    					"webbroker3.ifo.WEB3OptionOrderManagerImpl",
    					"submitSettleContractOrder",
    					new Class[] {SubAccount.class,
    							IfoSettleContractOrderSpec.class,
    							long.class,
    							String.class,
    							boolean.class},l_orderSubmissionResult);
                
                TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                        "webbroker3.triggerorder.base.service.delegate.stdimpls.WEB3ToSuccReservationIfoOrderUpdateServiceImpl",
                        "insertReserveOrderAction", new Class[]
                        {long.class},null);
                
                WEB3ToSuccIfoOrderUnitImplForTest1 l_rsvIfoOrderUnit= new WEB3ToSuccIfoOrderUnitImplForTest1(l_rsvIfoOrderUnitParams);

            	l_service.submitOptionsSettleContractOrder(l_rsvIfoOrderUnit);
            }
            catch(WEB3BaseException l_ex)
            {
                log.error("", l_ex);
                log.exiting(TEST_END + STR_METHOD_NAME);
                fail();

            }
            catch(Exception l_ex)
            {
                log.error("", l_ex);
                log.exiting(TEST_END + STR_METHOD_NAME);
                fail();
            }
            
            log.exiting(TEST_END + STR_METHOD_NAME);
	}

	//実行結果に応じ、予約注文単位をupdateする false
	public void testSubmitFuturesSettleContractOrder_C0010()
	{
		final String STR_METHOD_NAME = "testSubmitFuturesSettleContractOrder_C0010()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        WEB3ToSuccIfoOrderUnitServiceImplForTest l_service =new WEB3ToSuccIfoOrderUnitServiceImplForTest();
        try
        {
        	TestDBUtility.deleteAll(RsvIfoOrderUnitRow.TYPE);
            RsvIfoOrderUnitParams  l_rsvIfoOrderUnitParams =TestDBUtility.getRsvIfoOrderUnitRow();
            l_rsvIfoOrderUnitParams.setBizDate("20080512");
            l_rsvIfoOrderUnitParams.setMarketId(3303L);
            l_rsvIfoOrderUnitParams.setProductId(3304148080000L);
            //l_rsvIfoOrderUnitParams.setTraderId(1000);
            //l_rsvIfoOrderUnitParams.setFirstOrderUnitId(1000);
            TestDBUtility.insertWithDel(l_rsvIfoOrderUnitParams);
            
            TestDBUtility.deleteAll(TradingTimeRow.TYPE);
            TradingTimeParams  l_tradingTimeParams =TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams.setInstitutionCode("0D");
            l_tradingTimeParams.setBranchCode("123");
            l_tradingTimeParams.setMarketCode("N1");
            l_tradingTimeParams.setTradingTimeType("01");
            l_tradingTimeParams.setProductCode("0");
            l_tradingTimeParams.setBizDateType("1");
            TestDBUtility.insertWithDel(l_tradingTimeParams);
            
            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setAccountId(333812512203L);
            l_subAccountParams.setSubAccountId(33381251220301L);
            TestDBUtility.insertWithDel(l_subAccountParams);
            
            TestDBUtility.deleteAll(IfoContractRow.TYPE);
            IfoContractParams l_ifoContractParams = TestDBUtility.getIfoContractRow();
            l_ifoContractParams.setContractId(1);
            TestDBUtility.insertWithDel(l_ifoContractParams);
            
            ThreadLocalSystemAttributesRegistry.setAttribute(
                "xblocks.gtl.attributes.systemtimestamp",
                new Timestamp(WEB3DateUtility.getDate("20080509", "yyyyMMdd").getTime()));
            
            ThreadLocalSystemAttributesRegistry.setAttribute(
            	"web3.attributes.basetimestampfororderbizdate", null);
            
            ProcessingResult processingResult = ProcessingResult.newSuccessResultInstance();
            NewOrderValidationResult l_result = new NewOrderValidationResult(processingResult);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
					"webbroker3.ifo.WEB3OptionOrderManagerImpl",
					"validateSettleContractOrder",
					new Class[] { WEB3GentradeSubAccount.class, WEB3IfoSettleContractOrderSpec.class },
					l_result);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
					"webbroker3.ifo.WEB3OptionOrderManagerImpl",
					"validateSettleContractOrder",
					new Class[] { WEB3GentradeSubAccount.class, WEB3IfoSettleContractOrderSpec.class },
					l_result);
            
            String l_str="1000";
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
					"webbroker3.ifo.WEB3OptionOrderManagerImpl",
					"getDayTradeType",
					new Class[] { SettleContractEntry[].class },l_str);
            
            WEB3IfoEstimateDeliveryAmountCalcResult l_1mountCalcResult =new WEB3IfoEstimateDeliveryAmountCalcResult();
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
					"webbroker3.ifo.WEB3OptionOrderManagerImpl",
					"calcEstimateDeliveryAmount",
					new Class[] { WEB3GentradeCommission.class,
							double.class,
				            SubAccount.class,
				            WEB3IfoTradedProductImpl.class,
				            double.class,
				            SideEnum.class,
				            boolean.class,
				            boolean.class},l_1mountCalcResult);

            ErrorInfo errorInfo = new ErrorInfo();
            errorInfo.setErrorCode("BUSINESS_ERROR_02332");
            ProcessingResult processingResult2 = ProcessingResult.newFailedResultInstance(errorInfo);
            OrderSubmissionResult l_orderSubmissionResult =  new OrderSubmissionResult(processingResult2);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
					"webbroker3.ifo.WEB3OptionOrderManagerImpl",
					"submitSettleContractOrder",
					new Class[] {SubAccount.class,
							IfoSettleContractOrderSpec.class,
							long.class,
							String.class,
							boolean.class},l_orderSubmissionResult);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.triggerorder.base.service.delegate.stdimpls.WEB3ToSuccReservationIfoOrderUpdateServiceImpl",
                    "insertReserveOrderAction", new Class[]
                    {long.class},null);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.triggerorder.base.service.delegate.stdimpls.WEB3ToSuccReservationIfoOrderUpdateServiceImpl",
                    "invalidateOrderUnit", new Class[]
                    {RsvIfoOrderUnitRow.class,String.class},new WEB3BaseException(WEB3ErrorCatalog.BUSINESS_ERROR_02332,""));
            
            WEB3ToSuccIfoOrderUnitImplForTest1 l_rsvIfoOrderUnit= new WEB3ToSuccIfoOrderUnitImplForTest1(l_rsvIfoOrderUnitParams);

        	l_service.submitFuturesSettleContractOrder(l_rsvIfoOrderUnit);
            fail();
        }
        catch(WEB3BaseException l_ex)
        {
            log.error("", l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02332, l_ex.getErrorInfo());

        }
        catch(Exception l_ex)
        {
            log.error("", l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
	}

	class WEB3ToSuccIfoOrderUnitImplForTest extends WEB3ToSuccIfoOrderUnitImpl
	{
		public WEB3ToSuccIfoOrderUnitImplForTest(RsvIfoOrderUnitRow l_rsvIfoOrderUnitRow) {
			super(l_rsvIfoOrderUnitRow);
			// TODO Auto-generated constructor stub
		}

		public RsvIfoClosingContractSpecRow[] getContractsToClose() throws WEB3BaseException
	    {
			return null;
	    }
	}
	class WEB3ToSuccIfoOrderUnitImplForTest1 extends WEB3ToSuccIfoOrderUnitImpl
	{
		public WEB3ToSuccIfoOrderUnitImplForTest1(RsvIfoOrderUnitRow l_rsvIfoOrderUnitRow) {
			super(l_rsvIfoOrderUnitRow);
			// TODO Auto-generated constructor stub
		}

		public RsvIfoClosingContractSpecRow[] getContractsToClose() throws WEB3BaseException
	    {
			RsvIfoClosingContractSpecRow[] l_rsvIfoClosingContractSpecRows = new RsvIfoClosingContractSpecRow[0];
			return l_rsvIfoClosingContractSpecRows;
	    }
	}
	class WEB3ToSuccIfoOrderUnitServiceImplForTest extends WEB3ToSuccIfoOrderUnitServiceImpl
	{
		 protected SettleContractEntry[] createSettleContractEntries(
				RsvIfoClosingContractSpecRow[] l_rsvIfoClosingContractSpecs)
				throws WEB3BaseException {
			SettleContractEntry[] l_settleContractEntries = {new SettleContractEntry(0001,1000),new SettleContractEntry(0002,2000)};
			return l_settleContractEntries;
		}
	}
}
@
