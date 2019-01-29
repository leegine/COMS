head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.02.32.25;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3EquityMarketRequestSenderServiceImplTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.equity.marketadaptor;

import java.util.List;

import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeExecutionConditionType;
import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeOrderUnit;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderParams;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderUnitParams;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderUnitRow;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeProductParams;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeProductRow;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeTradedProductParams;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeTradedProductRow;
import com.fitechlabs.xtrade.plugin.tc.eqtype.market.messages.DefaultEqTypeChangeOrderMarketRequestMessage;
import com.fitechlabs.xtrade.plugin.tc.eqtype.market.messages.DefaultEqTypeMarketRequestMarginSettleOrderUnitSpec;
import com.fitechlabs.xtrade.plugin.tc.eqtype.market.messages.DefaultEqTypeNewCashBasedOrderMarketRequestMessage;
import com.fitechlabs.xtrade.plugin.tc.eqtype.market.messages.DefaultEqTypeSettleContractOrderMarketRequestMessage;
import com.fitechlabs.xtrade.plugin.tc.eqtype.market.messages.EqTypeChangeOrderMarketRequestMessage;
import com.fitechlabs.xtrade.plugin.tc.eqtype.market.messages.EqTypeMarketRequestCashBasedOrderUnitSpec;
import com.fitechlabs.xtrade.plugin.tc.eqtype.market.messages.EqTypeNewCashBasedOrderMarketRequestMessage;
import com.fitechlabs.xtrade.plugin.tc.eqtype.market.messages.EqTypeSettleContractOrderMarketRequestMessage;
import com.fitechlabs.xtrade.plugin.tc.eqtype.ordersubmitter.io.EqTypeChangeOrderUnitEntry;
import com.fitechlabs.xtrade.plugin.tc.eqtype.stdimpls.EqTypeOrderUnitImpl;
import com.fitechlabs.xtrade.plugin.tc.eqtype.stdimpls.PersistenceManagerImpl;
import com.fitechlabs.xtrade.plugin.tc.gentrade.MarketRequestSendResult;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderManagerPersistenceContext;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderOpenStatusEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderUnit;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
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
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.SubAccountRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.TradedProductParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.TradedProductRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.market.messages.CancelOrderMarketRequestMessage;
import com.fitechlabs.xtrade.plugin.tc.gentrade.market.messages.DefaultCancelOrderMarketRequestMessage;

import test.util.TestDBUtility;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.equity.data.HostEqtypeOrderAllParams;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3LogUtility;

public class WEB3EquityMarketRequestSenderServiceImplTest extends TestBaseForMock
{
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3EquityMarketRequestSenderServiceImplTest.class);

    public WEB3EquityMarketRequestSenderServiceImplTest(String arg0)
    {
        super(arg0);
    }

    protected void setUp() throws Exception
    {
        super.setUp();
        MOCK_MANAGER.setIsMockUsed(true);
    }

    protected void tearDown() throws Exception
    {
        super.tearDown();
    }

    public void testSend_0001()
    {
        final String STR_METHOD_NAME = "testSend_0001()";
        log.entering(STR_METHOD_NAME);
        
        MOCK_MANAGER.setIsMockUsed(true);
        
        try
        {
            PersistenceManagerImpl l_PersistenceManagerImpl = new PersistenceManagerImpl();

            TestDBUtility.deleteAll(EqtypeOrderUnitRow.TYPE);
            EqtypeOrderUnitParams l_eqTypeOrderUnitParams = TestDBUtility.getEqtypeOrderUnitRow();;
            l_eqTypeOrderUnitParams.setApproveStatusType("2");
            l_eqTypeOrderUnitParams.setExecutionConditionType(EqTypeExecutionConditionType.AT_MARKET_OPEN);
            l_eqTypeOrderUnitParams.setPriceConditionType("1");
            l_eqTypeOrderUnitParams.setOrderConditionType("1");
            TestDBUtility.insertWithDel(l_eqTypeOrderUnitParams);
            
            EqTypeOrderUnit l_eqTypeOrderUnit = new EqTypeOrderUnitImpl(l_PersistenceManagerImpl , l_eqTypeOrderUnitParams);
            OrderUnit[] l_orderUnit = {l_eqTypeOrderUnit};
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.equity.WEB3EquityOrderManager",
                "getOrderUnits",
                new Class[] { long.class },
                l_orderUnit
            );
            
            
            DefaultEqTypeMarketRequestMarginSettleOrderUnitSpec l_DefaultEqTypeMarketRequestMarginSettleOrderUnitSpec = 
                new DefaultEqTypeMarketRequestMarginSettleOrderUnitSpec(11L , 11D , 11D , null , false , null , null , null);
            EqTypeSettleContractOrderMarketRequestMessage l_requestMessage = 
                new DefaultEqTypeSettleContractOrderMarketRequestMessage(null , 123L , l_DefaultEqTypeMarketRequestMarginSettleOrderUnitSpec);
            
            WEB3EquityMarketRequestSenderServiceImpl l_equityMarketRequestSenderServiceImpl = 
                new WEB3EquityMarketRequestSenderServiceImpl();

            assertTrue((l_equityMarketRequestSenderServiceImpl.send(l_requestMessage)).getProcessingResult().isSuccessfulResult());
        }
        catch (Exception l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * 新規注文送信
     * getMarket()の戻り値.getMarketCode() == "PTS市場"の場合
     * 
     * 正常返回
     *
     */
    public void testSend_0002()
    {
        final String STR_METHOD_NAME = "testSend_0002()";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {
            TestDBUtility.deleteAll(InstitutionRow.TYPE);
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            l_institutionParams.setInstitutionCode("1D");
            l_institutionParams.setInstitutionId(33L);
            TestDBUtility.insertWithDel(l_institutionParams);
            
            TestDBUtility.deleteAll(BranchRow.TYPE);
            BranchParams l_branchParams =TestDBUtility.getBranchRow();
            l_branchParams.setBranchId(33381);
            l_branchParams.setInstitutionCode("1D");
            l_branchParams.setInstitutionId(33);
            TestDBUtility.insertWithDel(l_branchParams);
            
            TestDBUtility.deleteAll(MarketRow.TYPE);
            MarketParams l_marketParams = TestDBUtility.getMarketRow();
            l_marketParams.setMarketId(3303L);
            l_marketParams.setInstitutionCode("1D");
            l_marketParams.setMarketCode("11");
            l_marketParams.setSuspension("0");
            TestDBUtility.insertWithDel(l_marketParams);
            
            TestDBUtility.deleteAll(EqtypeOrderUnitRow.TYPE);
            EqtypeOrderUnitParams l_eqtypeOrderUnitParams =TestDBUtility.getEqtypeOrderUnitRow();
            l_eqtypeOrderUnitParams.setOrderUnitId(3304148080001L);
            l_eqtypeOrderUnitParams.setOrderId(123456789L);
            l_eqtypeOrderUnitParams.setAccountId(333812512246L);
            l_eqtypeOrderUnitParams.setOrderOpenStatus(OrderOpenStatusEnum.OPEN);
            l_eqtypeOrderUnitParams.setMarketId(3303L);
            l_eqtypeOrderUnitParams.setProductId(3304148080000L);
            TestDBUtility.insertWithDel(l_eqtypeOrderUnitParams);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.equity.WEB3EquityOrderManager", 
                    "notifyRLS",
                    new Class[]{EqTypeOrderUnit.class,OrderManagerPersistenceContext.class},
                    null);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.equity.WEB3EquityOrderManager",
                    "insertEquityHostOrder", new Class[]
                    { long.class },
                    null);
            
            WEB3GentradeSubAccount l_subAccount =
                new WEB3GentradeSubAccountForTest(TestDBUtility.getSubAccountRow());

            EqTypeMarketRequestCashBasedOrderUnitSpec [] l_orderUnitSpecs = new  EqTypeMarketRequestCashBasedOrderUnitSpec [1];
            
            EqTypeNewCashBasedOrderMarketRequestMessage l_requestMessage =
                new DefaultEqTypeNewCashBasedOrderMarketRequestMessage(l_subAccount,123456789L,l_orderUnitSpecs);
            
            WEB3EquityMarketRequestSenderServiceImpl l_equityMarketRequestSenderServiceImpl = 
                new WEB3EquityMarketRequestSenderServiceImpl(); 
            
            MarketRequestSendResult l_result = l_equityMarketRequestSenderServiceImpl.send(l_requestMessage);
            
            assertTrue(l_result.getProcessingResult().isSuccessfulResult());
        }
        catch(Exception l_ex)
        {
            log.error(ERROR + l_ex.getMessage(), l_ex);
            fail();
        }
        log.entering(TEST_END + STR_METHOD_NAME);
    }
    
    /**
     * 新規注文送信
     * getMarket()の戻り値.getMarketCode() != "PTS市場"の場合
     * 
     * 抛出異常
     *
     */
    public void testSend_0003()
    {
        final String STR_METHOD_NAME = "testSend_0003()";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {
            TestDBUtility.deleteAll(MarketRow.TYPE);
            MarketParams l_marketParams = TestDBUtility.getMarketRow();
            l_marketParams.setMarketId(3303L);
            l_marketParams.setInstitutionCode("1D");
            l_marketParams.setMarketCode("10");
            l_marketParams.setSuspension("0");
            TestDBUtility.insertWithDel(l_marketParams);
            
            TestDBUtility.deleteAll(EqtypeOrderUnitRow.TYPE);
            EqtypeOrderUnitParams l_eqtypeOrderUnitParams =TestDBUtility.getEqtypeOrderUnitRow();
            l_eqtypeOrderUnitParams.setOrderUnitId(3304148080001L);
            l_eqtypeOrderUnitParams.setOrderId(123456789L);
            l_eqtypeOrderUnitParams.setAccountId(333812512246L);
            l_eqtypeOrderUnitParams.setOrderOpenStatus(OrderOpenStatusEnum.OPEN);
            l_eqtypeOrderUnitParams.setMarketId(3303L);
            l_eqtypeOrderUnitParams.setProductId(3304148080000L);
            TestDBUtility.insertWithDel(l_eqtypeOrderUnitParams);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.equity.WEB3EquityOrderManager", 
                    "notifyRLS",
                    new Class[]{EqTypeOrderUnit.class,OrderManagerPersistenceContext.class},
                    null);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.equity.WEB3EquityOrderManager",
                    "insertEquityHostOrder", new Class[]
                    { long.class },
                    null);
            
            TestDBUtility.deleteAll(TradedProductRow.TYPE);
            TradedProductParams l_tradedProductParams =TestDBUtility.getTradedProductRow();
            l_tradedProductParams.setTradedProductId(1006160060005L);
            l_tradedProductParams.setInstitutionCode("1D");
            l_tradedProductParams.setProductId(3304148080000L);
            TestDBUtility.insertWithDel(l_tradedProductParams);
            
            TestDBUtility.deleteAll(EqtypeTradedProductRow.TYPE);
            EqtypeTradedProductParams l_eqtypeTradedProductParams = TestDBUtility.getEqtypeTradedProductRow();
            l_eqtypeTradedProductParams.setTradedProductId(1006160060005L);
            l_eqtypeTradedProductParams.setProductId(3304148080000L);
            l_eqtypeTradedProductParams.setInstitutionCode("1D");
            TestDBUtility.insertWithDel(l_eqtypeTradedProductParams);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.equity.service.delegate.stdimpls.WEB3EquityFrontOrderServiceImpl",
                    "getFrontOrderSystemCode", new Class[]
                    { String.class, String.class },
                    new WEB3BaseException(WEB3ErrorCatalog.BUSINESS_ERROR_00005,STR_METHOD_NAME));
            
            WEB3GentradeSubAccount l_subAccount =
                new WEB3GentradeSubAccountForTest(TestDBUtility.getSubAccountRow());

            EqTypeMarketRequestCashBasedOrderUnitSpec [] l_orderUnitSpecs = new  EqTypeMarketRequestCashBasedOrderUnitSpec [1];
            
            EqTypeNewCashBasedOrderMarketRequestMessage l_requestMessage =
                new DefaultEqTypeNewCashBasedOrderMarketRequestMessage(l_subAccount,123456789L,l_orderUnitSpecs);
            
            WEB3EquityMarketRequestSenderServiceImpl l_equityMarketRequestSenderServiceImpl = 
                new WEB3EquityMarketRequestSenderServiceImpl(); 
            
            MarketRequestSendResult l_result = l_equityMarketRequestSenderServiceImpl.send(l_requestMessage);
            
            fail();
        }
        catch(WEB3BaseException l_ex)
        {
            log.error(ERROR + l_ex.getMessage(), l_ex);
            assertTrue(true);
        }
        catch(Exception l_ex)
        {
            log.error(ERROR + l_ex.getMessage(), l_ex);
            assertTrue(true);
        }
        log.entering(TEST_END + STR_METHOD_NAME);
    }
    
    /**
     * 取消注文送信
     * getMarket()の戻り値.getMarketCode() == "PTS市場"の場合
     * 
     * 正常返回:「株式注文取引キューテーブル」を更新する
     *
     */
    public void testSend_0004()
    {
        final String STR_METHOD_NAME = "testSend_0004()";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {
            TestDBUtility.deleteAll(HostEqtypeOrderAllParams.TYPE);
            
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            MainAccountParams l_mainAccountParams =TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(333812512246L);
            TestDBUtility.insertWithDel(l_mainAccountParams);
            
            TestDBUtility.deleteAll(InstitutionRow.TYPE);
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            l_institutionParams.setInstitutionCode("1D");
            l_institutionParams.setInstitutionId(33);
            TestDBUtility.insertWithDel(l_institutionParams);
            
            
            TestDBUtility.deleteAll(BranchRow.TYPE);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setBranchId(33381);
            l_branchParams.setInstitutionCode("1D");
            l_branchParams.setInstitutionId(33);
            TestDBUtility.insertWithDel(l_branchParams);
            
            TestDBUtility.deleteAll(ProductRow.TYPE);
            ProductParams l_productParams = TestDBUtility.getProductRow();
            l_productParams.setProductType(ProductTypeEnum.EQUITY);
            l_productParams.setProductId(3304148080000L);
            TestDBUtility.insertWithDel(l_productParams);
            
            TestDBUtility.deleteAll(EqtypeProductRow.TYPE);
            EqtypeProductParams l_eqtypeProductParams =TestDBUtility.getEqtypeProductRow();
            l_eqtypeProductParams.setProductId(3304148080000L);
            l_eqtypeProductParams.setProductType(ProductTypeEnum.EQUITY);
            TestDBUtility.insertWithDel(l_eqtypeProductParams);
            
            
            
            TestDBUtility.deleteAll(MarketRow.TYPE);
            MarketParams l_marketParams = TestDBUtility.getMarketRow();
            l_marketParams.setMarketId(3303L);
            l_marketParams.setInstitutionCode("1D");
            l_marketParams.setMarketCode("11");
            l_marketParams.setSuspension("0");
            TestDBUtility.insertWithDel(l_marketParams);
            
            TestDBUtility.deleteAll(EqtypeOrderUnitRow.TYPE);
            EqtypeOrderParams l_eqtypeOrderParams = TestDBUtility.getEqtypeOrderRow();
            l_eqtypeOrderParams.setOrderId(123456789L);
            TestDBUtility.insertWithDel(l_eqtypeOrderParams);
            
            TestDBUtility.deleteAll(EqtypeOrderUnitRow.TYPE);
            EqtypeOrderUnitParams l_eqtypeOrderUnitParams =TestDBUtility.getEqtypeOrderUnitRow();
            l_eqtypeOrderUnitParams.setOrderUnitId(3304148080001L);
            l_eqtypeOrderUnitParams.setOrderId(123456789L);
            l_eqtypeOrderUnitParams.setAccountId(333812512246L);
            l_eqtypeOrderUnitParams.setOrderOpenStatus(OrderOpenStatusEnum.OPEN);
            l_eqtypeOrderUnitParams.setMarketId(3303L);
            l_eqtypeOrderUnitParams.setProductId(3304148080000L);
            l_eqtypeOrderUnitParams.setFirstOrderUnitId(1123);
            l_eqtypeOrderUnitParams.setConfirmedPriceConditionType("0");
            TestDBUtility.insertWithDel(l_eqtypeOrderUnitParams);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.equity.WEB3EquityOrderManager", 
                    "notifyRLS",
                    new Class[]{EqTypeOrderUnit.class,OrderManagerPersistenceContext.class},
                    null);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.equity.WEB3EquityOrderManager",
                    "insertEquityHostOrder", new Class[]
                    { long.class },
                    null);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.equity.service.delegate.stdimpls.WEB3EquityFrontOrderServiceImpl",
                    "getFrontOrderSystemCode", new Class[]
                    { String.class, String.class },
                    null);
            
            WEB3GentradeSubAccount l_subAccount =
                new WEB3GentradeSubAccountForTest(TestDBUtility.getSubAccountRow());

            
            CancelOrderMarketRequestMessage l_requestMessage = new DefaultCancelOrderMarketRequestMessage(l_subAccount,123456789L);
            
            WEB3EquityMarketRequestSenderServiceImpl l_equityMarketRequestSenderServiceImpl = 
                new WEB3EquityMarketRequestSenderServiceImpl(); 
            
            MarketRequestSendResult l_result = l_equityMarketRequestSenderServiceImpl.send(l_requestMessage,false);
            
            assertTrue(l_result.getProcessingResult().isSuccessfulResult());
            
            QueryProcessor l_processor = Processors.getDefaultProcessor();
            String l_strWhere = "account_id = ?";
            Object[] l_objBindValue = {new Long(333812512246L)};
            
            List l_lisResult = l_processor.doFindAllQuery(
                HostEqtypeOrderAllParams.TYPE,
                l_strWhere,
                l_objBindValue);
            
            assertEquals(1,l_lisResult.size());
            
            assertEquals(333812512246L,((HostEqtypeOrderAllParams)l_lisResult.get(0)).getAccountId());
        }
        catch(Exception l_ex)
        {
            log.error(ERROR + l_ex.getMessage(), l_ex);
            fail();
        }
        log.entering(TEST_END + STR_METHOD_NAME);
    }
    
    /**
     * 取消注文送信
     * getMarket()の戻り値.getMarketCode() != "PTS市場"の場合
     * 
     * 返回失敗信息
     *
     */
    public void testSend_0005()
    {
        final String STR_METHOD_NAME = "testSend_0005()";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {
            TestDBUtility.deleteAll(HostEqtypeOrderAllParams.TYPE);
            
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            MainAccountParams l_mainAccountParams =TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(333812512246L);
            TestDBUtility.insertWithDel(l_mainAccountParams);
            
            TestDBUtility.deleteAll(InstitutionRow.TYPE);
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            l_institutionParams.setInstitutionCode("1D");
            l_institutionParams.setInstitutionId(33);
            TestDBUtility.insertWithDel(l_institutionParams);
            
            
            TestDBUtility.deleteAll(BranchRow.TYPE);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setBranchId(33381);
            l_branchParams.setInstitutionCode("1D");
            l_branchParams.setInstitutionId(33);
            TestDBUtility.insertWithDel(l_branchParams);
            
            TestDBUtility.deleteAll(ProductRow.TYPE);
            ProductParams l_productParams = TestDBUtility.getProductRow();
            l_productParams.setProductType(ProductTypeEnum.EQUITY);
            l_productParams.setProductId(3304148080000L);
            TestDBUtility.insertWithDel(l_productParams);
            
            TestDBUtility.deleteAll(EqtypeProductRow.TYPE);
            EqtypeProductParams l_eqtypeProductParams =TestDBUtility.getEqtypeProductRow();
            l_eqtypeProductParams.setProductId(3304148080000L);
            l_eqtypeProductParams.setProductType(ProductTypeEnum.EQUITY);
            TestDBUtility.insertWithDel(l_eqtypeProductParams);
            
            TestDBUtility.deleteAll(MarketRow.TYPE);
            MarketParams l_marketParams = TestDBUtility.getMarketRow();
            l_marketParams.setMarketId(3303L);
            l_marketParams.setInstitutionCode("1D");
            l_marketParams.setMarketCode("9");
            l_marketParams.setSuspension("0");
            TestDBUtility.insertWithDel(l_marketParams);
            
            TestDBUtility.deleteAll(EqtypeOrderUnitRow.TYPE);
            EqtypeOrderParams l_eqtypeOrderParams = TestDBUtility.getEqtypeOrderRow();
            l_eqtypeOrderParams.setOrderId(123456789L);
            TestDBUtility.insertWithDel(l_eqtypeOrderParams);
            
            TestDBUtility.deleteAll(EqtypeOrderUnitRow.TYPE);
            EqtypeOrderUnitParams l_eqtypeOrderUnitParams =TestDBUtility.getEqtypeOrderUnitRow();
            l_eqtypeOrderUnitParams.setOrderUnitId(3304148080001L);
            l_eqtypeOrderUnitParams.setOrderId(123456789L);
            l_eqtypeOrderUnitParams.setAccountId(333812512246L);
            l_eqtypeOrderUnitParams.setOrderOpenStatus(OrderOpenStatusEnum.OPEN);
            l_eqtypeOrderUnitParams.setMarketId(3303L);
            l_eqtypeOrderUnitParams.setProductId(3304148080000L);
            l_eqtypeOrderUnitParams.setFirstOrderUnitId(1123);
            l_eqtypeOrderUnitParams.setConfirmedPriceConditionType("0");
            TestDBUtility.insertWithDel(l_eqtypeOrderUnitParams);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.equity.WEB3EquityOrderManager", 
                    "notifyRLS",
                    new Class[]{EqTypeOrderUnit.class,OrderManagerPersistenceContext.class},
                    new WEB3BaseException(WEB3ErrorCatalog.BUSINESS_ERROR_00005,STR_METHOD_NAME));
            
            
            WEB3GentradeSubAccount l_subAccount =
                new WEB3GentradeSubAccountForTest(TestDBUtility.getSubAccountRow());

            
            CancelOrderMarketRequestMessage l_requestMessage = new DefaultCancelOrderMarketRequestMessage(l_subAccount,123456789L);
            
            WEB3EquityMarketRequestSenderServiceImpl l_equityMarketRequestSenderServiceImpl = 
                new WEB3EquityMarketRequestSenderServiceImpl(); 
            
            MarketRequestSendResult l_result = l_equityMarketRequestSenderServiceImpl.send(l_requestMessage,false);
            
            assertTrue(l_result.getProcessingResult().isFailedResult());
            
        }
        catch(WEB3BaseException l_ex)
        {
            log.error(ERROR + l_ex.getMessage(), l_ex);
            fail();
        }
        catch(Exception l_ex)
        {
            log.error(ERROR + l_ex.getMessage(), l_ex);
            fail();
        }
        log.entering(TEST_END + STR_METHOD_NAME);
    }
    
    /**
     * 訂正注文送信
     * getMarket()の戻り値.getMarketCode() == "PTS市場"の場合
     * 
     * 正常返回:「株式注文取引キューテーブル」を更新する
     *
     */
    public void testSend_0006()
    {
        final String STR_METHOD_NAME = "testSend_0006()";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {
            TestDBUtility.deleteAll(HostEqtypeOrderAllParams.TYPE);
            
            
            TestDBUtility.deleteAll(MarketRow.TYPE);
            MarketParams l_marketParams = TestDBUtility.getMarketRow();
            l_marketParams.setMarketId(3303L);
            l_marketParams.setInstitutionCode("1D");
            l_marketParams.setMarketCode("11");
            l_marketParams.setSuspension("0");
            TestDBUtility.insertWithDel(l_marketParams);
            
            TestDBUtility.deleteAll(EqtypeOrderUnitRow.TYPE);
            EqtypeOrderParams l_eqtypeOrderParams = TestDBUtility.getEqtypeOrderRow();
            l_eqtypeOrderParams.setOrderId(123456789L);
            TestDBUtility.insertWithDel(l_eqtypeOrderParams);
            
            TestDBUtility.deleteAll(EqtypeOrderUnitRow.TYPE);
            EqtypeOrderUnitParams l_eqtypeOrderUnitParams =TestDBUtility.getEqtypeOrderUnitRow();
            l_eqtypeOrderUnitParams.setOrderUnitId(3304148080001L);
            l_eqtypeOrderUnitParams.setOrderId(123456789L);
            l_eqtypeOrderUnitParams.setAccountId(333812512246L);
            l_eqtypeOrderUnitParams.setOrderOpenStatus(OrderOpenStatusEnum.OPEN);
            l_eqtypeOrderUnitParams.setMarketId(3303L);
            l_eqtypeOrderUnitParams.setBranchId(33381L);
            l_eqtypeOrderUnitParams.setProductId(3304148080000L);
            l_eqtypeOrderUnitParams.setFirstOrderUnitId(1123);
            l_eqtypeOrderUnitParams.setConfirmedPriceConditionType("0");
            l_eqtypeOrderUnitParams.setPriceConditionType("0");
            TestDBUtility.insertWithDel(l_eqtypeOrderUnitParams);
            
            TestDBUtility.deleteAll(BranchRow.TYPE);
            BranchParams l_branchParams =TestDBUtility.getBranchRow();
            l_branchParams.setBranchId(33381);
            l_branchParams.setInstitutionId(33);
            TestDBUtility.insertWithDel(l_branchParams);
            
            TestDBUtility.deleteAll(InstitutionRow.TYPE);
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            l_institutionParams.setInstitutionId(33);
            TestDBUtility.insertWithDel(l_institutionParams);
            
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(333812512246L);
            l_mainAccountParams.setAccountCode("2512246");
            TestDBUtility.insertWithDel(l_mainAccountParams);
            
            
            TestDBUtility.deleteAll(ProductRow.TYPE);
            ProductParams l_productParams = TestDBUtility.getProductRow();
            l_productParams.setProductId(3304148080000L);
            l_productParams.setProductType(ProductTypeEnum.EQUITY);
            TestDBUtility.insertWithDel(l_productParams);
            
            TestDBUtility.deleteAll(EqtypeProductRow.TYPE);
            EqtypeProductParams l_eqtypeProductParams =TestDBUtility.getEqtypeProductRow();
            l_eqtypeProductParams.setProductId(3304148080000L);
            l_eqtypeProductParams.setCapitalGainTaxDealingsReg(1);
            TestDBUtility.insertWithDel(l_eqtypeProductParams);
            
            WEB3GentradeSubAccount l_subAccount =
                new WEB3GentradeSubAccountForTest(TestDBUtility.getSubAccountRow());

            
            EqTypeChangeOrderUnitEntry l_changeOrderUnitEntries[] = new EqTypeChangeOrderUnitEntry[1];
            
            EqTypeChangeOrderMarketRequestMessage l_requestMessage =
                new DefaultEqTypeChangeOrderMarketRequestMessage(l_subAccount,123456789L,l_changeOrderUnitEntries);
            
            WEB3EquityMarketRequestSenderServiceImpl l_equityMarketRequestSenderServiceImpl = 
                new WEB3EquityMarketRequestSenderServiceImpl(); 
            
            MarketRequestSendResult l_result = l_equityMarketRequestSenderServiceImpl.send(l_requestMessage,false);
            
            assertTrue(l_result.getProcessingResult().isSuccessfulResult());
            
            QueryProcessor l_processor = Processors.getDefaultProcessor();
            String l_strWhere = "account_id = ?";
            Object[] l_objBindValue = {new Long(333812512246L)};
            
            List l_lisResult = l_processor.doFindAllQuery(
                HostEqtypeOrderAllParams.TYPE,
                l_strWhere,
                l_objBindValue);
            
            assertEquals(1,l_lisResult.size());
            
            assertEquals(333812512246L,((HostEqtypeOrderAllParams)l_lisResult.get(0)).getAccountId());
        }
        catch(Exception l_ex)
        {
            log.error(ERROR + l_ex.getMessage(), l_ex);
            fail();
        }
        log.entering(TEST_END + STR_METHOD_NAME);
    }
    
    /**
     * 訂正注文送信
     * getMarket()の戻り値.getMarketCode() != "PTS市場"の場合
     * 
     * 返回失敗信息
     *
     */
    public void testSend_0007()
    {
        final String STR_METHOD_NAME = "testSend_0007()";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {
            TestDBUtility.deleteAll(HostEqtypeOrderAllParams.TYPE);
            
            TestDBUtility.deleteAll(MarketRow.TYPE);
            MarketParams l_marketParams = TestDBUtility.getMarketRow();
            l_marketParams.setMarketId(3303L);
            l_marketParams.setInstitutionCode("1D");
            l_marketParams.setMarketCode("9");
            l_marketParams.setSuspension("0");
            TestDBUtility.insertWithDel(l_marketParams);
            
            TestDBUtility.deleteAll(EqtypeOrderUnitRow.TYPE);
            EqtypeOrderParams l_eqtypeOrderParams = TestDBUtility.getEqtypeOrderRow();
            l_eqtypeOrderParams.setOrderId(123456789L);
            TestDBUtility.insertWithDel(l_eqtypeOrderParams);
            
            TestDBUtility.deleteAll(EqtypeOrderUnitRow.TYPE);
            EqtypeOrderUnitParams l_eqtypeOrderUnitParams =TestDBUtility.getEqtypeOrderUnitRow();
            l_eqtypeOrderUnitParams.setOrderUnitId(3304148080001L);
            l_eqtypeOrderUnitParams.setOrderId(123456789L);
            l_eqtypeOrderUnitParams.setAccountId(333812512246L);
            l_eqtypeOrderUnitParams.setOrderOpenStatus(OrderOpenStatusEnum.OPEN);
            l_eqtypeOrderUnitParams.setMarketId(3303L);
            l_eqtypeOrderUnitParams.setBranchId(33381L);
            l_eqtypeOrderUnitParams.setProductId(3304148080000L);
            l_eqtypeOrderUnitParams.setFirstOrderUnitId(1123);
            l_eqtypeOrderUnitParams.setConfirmedPriceConditionType("0");
            l_eqtypeOrderUnitParams.setPriceConditionType("0");
            TestDBUtility.insertWithDel(l_eqtypeOrderUnitParams);
            
            TestDBUtility.deleteAll(BranchRow.TYPE);
            BranchParams l_branchParams =TestDBUtility.getBranchRow();
            l_branchParams.setBranchId(33381);
            l_branchParams.setInstitutionId(33);
            TestDBUtility.insertWithDel(l_branchParams);
            
            TestDBUtility.deleteAll(InstitutionRow.TYPE);
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            l_institutionParams.setInstitutionId(33);
            TestDBUtility.insertWithDel(l_institutionParams);
            
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(333812512246L);
            l_mainAccountParams.setAccountCode("2512246");
            TestDBUtility.insertWithDel(l_mainAccountParams);
            
            
            TestDBUtility.deleteAll(ProductRow.TYPE);
            ProductParams l_productParams = TestDBUtility.getProductRow();
            l_productParams.setProductId(3304148080000L);
            l_productParams.setProductType(ProductTypeEnum.EQUITY);
            TestDBUtility.insertWithDel(l_productParams);
            
            TestDBUtility.deleteAll(EqtypeProductRow.TYPE);
            EqtypeProductParams l_eqtypeProductParams =TestDBUtility.getEqtypeProductRow();
            l_eqtypeProductParams.setProductId(3304148080000L);
            l_eqtypeProductParams.setCapitalGainTaxDealingsReg(1);
            TestDBUtility.insertWithDel(l_eqtypeProductParams);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.equity.WEB3EquityOrderManager", 
                    "notifyRLS",
                    new Class[]{EqTypeOrderUnit.class,OrderManagerPersistenceContext.class},
                    new WEB3BaseException(WEB3ErrorCatalog.BUSINESS_ERROR_00005,STR_METHOD_NAME));
            
            WEB3GentradeSubAccount l_subAccount =
                new WEB3GentradeSubAccountForTest(TestDBUtility.getSubAccountRow());

            
            EqTypeChangeOrderUnitEntry l_changeOrderUnitEntries[] = new EqTypeChangeOrderUnitEntry[1];
            
            EqTypeChangeOrderMarketRequestMessage l_requestMessage =
                new DefaultEqTypeChangeOrderMarketRequestMessage(l_subAccount,123456789L,l_changeOrderUnitEntries);
            
            WEB3EquityMarketRequestSenderServiceImpl l_equityMarketRequestSenderServiceImpl = 
                new WEB3EquityMarketRequestSenderServiceImpl(); 
            
            MarketRequestSendResult l_result = l_equityMarketRequestSenderServiceImpl.send(l_requestMessage,false);
            
            assertTrue(l_result.getProcessingResult().isFailedResult());
            
        }
        catch(Exception l_ex)
        {
            log.error(ERROR + l_ex.getMessage(), l_ex);
            fail();
        }
        log.entering(TEST_END + STR_METHOD_NAME);
    }
    
    private class WEB3GentradeSubAccountForTest extends WEB3GentradeSubAccount
    {

        public WEB3GentradeSubAccountForTest(SubAccountRow l_subAcctRow)
        {
            super(l_subAcctRow);
        }        
    }
    
}
@
