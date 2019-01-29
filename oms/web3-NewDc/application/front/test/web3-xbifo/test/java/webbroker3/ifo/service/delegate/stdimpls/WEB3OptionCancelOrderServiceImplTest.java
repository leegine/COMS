head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.00.45.53;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3OptionCancelOrderServiceImplTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : WEB3OptionCancelOrderServiceImplTest.java
Author Name      : Daiwa Institute of Research
Revision History : 2007/06/19 金傑 (中訊) 新規作成
*/

package webbroker3.ifo.service.delegate.stdimpls;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProcessingResult;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccountTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradedProduct;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Trader;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.BranchParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.BranchRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MarketParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MarketRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.ProductParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.ProductRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.SubAccountParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.TradedProductParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.TradedProductRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.CancelOrderSpec;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.OrderSubmissionResult;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.OrderValidationResult;
import com.fitechlabs.xtrade.plugin.tc.xbifo.IfoOrderUnit;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoOrderParams;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoOrderRow;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoOrderUnitParams;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoOrderUnitRow;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoProductParams;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoTradedProductParams;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoTradedProductRow;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoTradedProductUpdqParams;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoTradedProductUpdqRow;
import com.fitechlabs.xtrade.plugin.tc.xbifo.stdimpls.IfoOrderImpl;

import test.util.TestDBUtility;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.gentrade.WEB3GentradeBizDate;
import webbroker3.gentrade.WEB3GentradeBranch;
import webbroker3.gentrade.WEB3GentradeMainAccount;
import webbroker3.gentrade.WEB3GentradeMarket;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.gentrade.WEB3GentradeSubAccountForMock;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagementForMock;
import webbroker3.ifo.WEB3IfoProductImplForMock;
import webbroker3.ifo.WEB3IfoTradedProductImplForMock;
import webbroker3.ifo.message.WEB3FuturesOptionsContractUnit;
import webbroker3.ifo.message.WEB3OptionsCancelCompleteRequest;
import webbroker3.ifo.message.WEB3OptionsCancelConfirmRequest;
import webbroker3.ifo.message.WEB3OptionsCancelConfirmResponse;
import webbroker3.mock.TestBaseForMock;
import webbroker3.mock.util.WEB3MockObjectParamsValue;
import webbroker3.quoteadaptor.RealType;
import webbroker3.quoteadaptor.stdimpls.WEB3IfoQuoteDataImplForMock;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

public class WEB3OptionCancelOrderServiceImplTest extends TestBaseForMock
{
    private WEB3OptionsCancelConfirmRequest l_request = null;
    private WEB3OptionsCancelConfirmResponse l_response = null;
    private WEB3OptionCancelOrderServiceImpl l_service = null;
    private WEB3OptionsCancelCompleteRequest l_completeRequest = null;
    private boolean l_blnIsOptionSubAccount = false;
    
    /**
     * ログユーティリティ<BR>
     */
    private static WEB3LogUtility log = WEB3LogUtility
            .getInstance(WEB3OptionCancelOrderServiceImplTest.class);

    public WEB3OptionCancelOrderServiceImplTest(String arg0)
    {
        super(arg0);
    }

    protected void setUp() throws Exception
    {
        super.setUp();
        MOCK_MANAGER.setIsMockUsed(true);
        this.l_request = new WEB3OptionsCancelConfirmRequestForTest();
        this.l_service = new WEB3OptionCancelOrderServiceImplForTest();
        this.l_completeRequest = new WEB3OptionsCancelCompleteRequestForTest();
        this.initData();
        this.setMockMethod();
        
    }

    protected void tearDown() throws Exception
    {
        this.l_request = null;
        this.l_response = null;
        this.l_service = null;
//        super.checkMethodValue();
        super.tearDown();
    }
    
    /**
     * レスポンス.注文期限区分 = 1
     * レスポンス.注文有効期限 = null
     * レスポンス.立会区分 = 1
     */
    public void testvalidateOrder_C0001()
    {
        final String STR_METHOD_NAME = "testvalidateOrder_C0001()";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {
                        
            this.l_response = this.l_service.validateOrder(this.l_request);

            this.getParamsValue();
            assertEquals("1",l_response.expirationDateType);
            assertEquals("1",l_response.sessionType);
            assertNull(l_response.expirationDate);
        }
        catch(Exception l_ex)
        {
            log.error("", l_ex);
            fail();
        }
        
        log.exiting(TEST_START + STR_METHOD_NAME);
    }
    
    /**
     * レスポンス.注文期限区分 = 2
     * レスポンス.注文有効期限を取得する
     *
     */
    public void testvalidateOrder_C0002()
    {
        final String STR_METHOD_NAME = "testvalidateOrder_C0002()";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.ifo.WEB3FuturesOrderManagerImpl",
                    "isEveningSessionOrder", 
                    new Class[]{ IfoOrderUnit.class },
                    new Boolean(false));
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.ifo.WEB3FuturesOrderManagerImpl",
                    "isCarriedOrderUnit", 
                    new Class[]{ IfoOrderUnit.class },
                    new Boolean(true));
            
            this.l_response = this.l_service.validateOrder(this.l_request);
            
            this.getParamsValue();
            WEB3MockObjectParamsValue l_paramsValue1 = TestBaseForMock.MOCK_MANAGER.getMethodParamsValue(
                    "webbroker3.ifo.WEB3FuturesOrderManagerImpl", 
                    "isEveningSessionOrder", 
                    new Class[] { IfoOrderUnit.class });
            
            assertEquals(1,((IfoOrderUnit)l_paramsValue1.getFirstCalled()[0]).getOrderId());
            
            WEB3MockObjectParamsValue l_paramsValue2 = TestBaseForMock.MOCK_MANAGER.getMethodParamsValue(
                    "webbroker3.ifo.WEB3FuturesOrderManagerImpl", 
                    "isCarriedOrderUnit", 
                    new Class[] { IfoOrderUnit.class });
            
            assertEquals(1,((IfoOrderUnit)l_paramsValue2.getFirstCalled()[0]).getOrderId());
            
            assertEquals("2",l_response.expirationDateType);
            assertEquals(WEB3DateUtility.toDay(new Date()),l_response.expirationDate);
        }
        catch(Exception l_ex)
        {
            log.error("", l_ex);
            fail();
        }
        
        log.exiting(TEST_START + STR_METHOD_NAME);
    }
    
    public void testSubmitOrder_C0001()
    {
        final String STR_METHOD_NAME = "testSubmitOrder_C0001()";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {
            this.l_blnIsOptionSubAccount = true;
            this.l_completeRequest.id = "1";
            this.l_service.submitOrder(this.l_completeRequest);
        }
        catch(Exception l_ex)
        {
            log.error("", l_ex);
            fail();
        }
        log.exiting(TEST_START + STR_METHOD_NAME);
    }
    
    private void getParamsValue()
    {
        final String STR_METHOD_NAME = "getParamsValue()";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {
            WEB3MockObjectParamsValue l_paramsValue1 = TestBaseForMock.MOCK_MANAGER.getMethodParamsValue(
                    "webbroker3.ifo.WEB3FuturesOrderManagerImpl", 
                    "validateCancelOrder", 
                    new Class[] { WEB3GentradeSubAccount.class, CancelOrderSpec.class });
            assertEquals(333812512266L,((WEB3GentradeSubAccountForMock)l_paramsValue1.getFirstCalled()[0]).getAccountId());
            assertEquals(1,((CancelOrderSpec)l_paramsValue1.getFirstCalled()[1]).getOrderId());
            
            WEB3MockObjectParamsValue l_paramsValue2 = TestBaseForMock.MOCK_MANAGER.getMethodParamsValue(
                    "webbroker3.ifo.WEB3FuturesOrderManagerImpl", 
                    "getOrder", 
                    new Class[] { long.class });
            assertEquals(1,((Long)l_paramsValue2.getFirstCalled()[0]).longValue());
            
            WEB3MockObjectParamsValue l_paramsValue3 = TestBaseForMock.MOCK_MANAGER.getMethodParamsValue(
                    "webbroker3.ifo.WEB3IfoProductManagerImpl", 
                    "getProduct", 
                    new Class[] { long.class });

            assertEquals(1006160060005L,((Long)l_paramsValue3.getFirstCalled()[0]).longValue());
            
            WEB3MockObjectParamsValue l_paramsValue4 = TestBaseForMock.MOCK_MANAGER.getMethodParamsValue(
                    "webbroker3.ifo.WEB3FuturesOrderManagerImpl", 
                    "createContractUnitByOrder", 
                    new Class[] { long.class });
            assertEquals(1,((Long)l_paramsValue4.getFirstCalled()[0]).longValue());

            WEB3MockObjectParamsValue l_paramsValue5 = TestBaseForMock.MOCK_MANAGER.getMethodParamsValue(
                    "webbroker3.quoteadaptor.prototype.WEB3ProtoQuoteDataSupplierService", 
                    "getQuote", 
                    new Class[] { TradedProduct.class, RealType.class });
            
            assertEquals(WEB3IfoTradedProductImplForMock.class,((WEB3IfoTradedProductImplForMock)l_paramsValue5.getFirstCalled()[0]).getClass());
            assertEquals(RealType.DELAY,((RealType)l_paramsValue5.getFirstCalled()[1]).DELAY);
        }
        catch(Exception l_ex)
        {
            log.error("", l_ex);
            fail();
        }
        log.exiting(TEST_START + STR_METHOD_NAME);
    }
    
    private void initData()
    {
        final String STR_METHOD_NAME = "initData()";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {
            
            TestDBUtility.deleteAll(BranchRow.TYPE);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setBranchId(33382);
            l_branchParams.setCloseWorngOption(0);
            TestDBUtility.insertWithDel(l_branchParams);
            
            TestDBUtility.deleteAll(IfoOrderRow.TYPE);
            IfoOrderParams l_ifoOrderParams = TestDBUtility.getIfoOrderRow();
            l_ifoOrderParams.setOrderId(1L);
            TestDBUtility.insertWithDel(l_ifoOrderParams);

            TestDBUtility.deleteAll(IfoOrderUnitRow.TYPE);
            IfoOrderUnitParams l_ifoOrderUnitParams = TestDBUtility.getIfoOrderUnitRow();
            l_ifoOrderUnitParams.setOrderId(1);
            l_ifoOrderUnitParams.setProductId(1006160060005L);
            l_ifoOrderUnitParams.setMarketId(3306L);
            l_ifoOrderUnitParams.setSessionType("1");
            l_ifoOrderUnitParams.setExpirationDate(new Date());
            TestDBUtility.insertWithDel(l_ifoOrderUnitParams);
            
            
            TestDBUtility.deleteAll(MarketRow.TYPE);
            MarketParams l_marketParams =TestDBUtility.getMarketRow();
            l_marketParams.setMarketId(3306L);
            TestDBUtility.insertWithDel(l_marketParams);
            
            TestDBUtility.deleteAll(ProductRow.TYPE);
            ProductParams l_productParams =  TestDBUtility.getProductRow();
            l_productParams.setProductId(1006160060005L);
            TestDBUtility.insertWithDel(l_productParams);
            
            IfoProductParams l_ifoProductParams = TestDBUtility.getIfoProductRow();
            l_ifoProductParams.setProductId(1006160060005L);
            TestDBUtility.insertWithDel(l_ifoProductParams);
            
            TestDBUtility.deleteAll(TradedProductRow.TYPE);
            TradedProductParams l_tradedProductParams = TestDBUtility.getTradedProductRow();
            l_tradedProductParams.setTradedProductId(330304148080002L);
            l_tradedProductParams.setProductId(1006160060005L);
            l_tradedProductParams.setMarketId(3306L);
            TestDBUtility.insertWithDel(l_tradedProductParams);
            
            TestDBUtility.deleteAll(IfoTradedProductRow.TYPE);
            IfoTradedProductParams l_IfoTradedProductParams = TestDBUtility.getIfoTradedProductRow();
            l_IfoTradedProductParams.setProductId(1006160060005L);
            l_IfoTradedProductParams.setTradedProductId(330304148080002L);
            l_IfoTradedProductParams.setMarketId(3306L);
            TestDBUtility.insertWithDel(l_IfoTradedProductParams);
            
            TestDBUtility.deleteAll(IfoTradedProductUpdqRow.TYPE);
            IfoTradedProductUpdqParams l_ifoTradedProductUpdqParams = TestDBUtility.getIfoTradedProductUpdqRow();
            l_ifoTradedProductUpdqParams.setTradedProductId(330304148080002L);
            l_ifoTradedProductUpdqParams.setMarketId(3306L);
            
            
            SimpleDateFormat l_format = GtlUtils.getThreadSafeSimpleDateFormat("yyyyMMdd");
            Date l_datBizDate = GtlUtils.getTradingSystem().getSystemTimestamp();
            Date l_datpreBizDate = new WEB3GentradeBizDate(
                new Timestamp(l_datBizDate.getTime())).roll(1);
            String l_strCreateDate = l_format.format(l_datpreBizDate);
            
            l_ifoTradedProductUpdqParams.setValidForBizDate(l_strCreateDate);
            l_ifoTradedProductUpdqParams.setProductId(1006160060009L);
            TestDBUtility.insertWithDel(l_ifoTradedProductUpdqParams);
            
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(333812512266L);
            l_mainAccountParams.setInstitutionCode("1D");
            l_mainAccountParams.setIfoAccOpenDivTokyo("3");
            TestDBUtility.insertWithDel(l_mainAccountParams);
            
            WEB3GentradeTradingTimeManagementForMock.mockGetOrderBizDate(new Date());

     }
        catch (Exception l_ex)
        {
            log.error("", l_ex);
            fail();
        }
        log.exiting(TEST_START + STR_METHOD_NAME);
    }
    
    private void setMockMethod()
    {
        final String STR_METHOD_NAME = "setMockMethod()";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {
            OrderValidationResult l_result = new OrderValidationResult(ProcessingResult.SUCCESS_RESULT);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.ifo.WEB3FuturesOrderManagerImpl", 
                "validateCancelOrder",
                new Class[]{ WEB3GentradeSubAccount.class, CancelOrderSpec.class },
                l_result);
            
            this.l_request.id = "1";
            long l_lngOrderId = Long.parseLong(l_request.id);

            IfoOrderImpl l_ifoOrder = new IfoOrderImpl(l_lngOrderId);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.ifo.WEB3FuturesOrderManagerImpl", 
                "getOrder", new Class[]{ long.class },
                l_ifoOrder);


            WEB3IfoProductImplForMock l_ifoProduct = new WEB3IfoProductImplForMock(1006160060005L); 
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.ifo.WEB3IfoProductManagerImpl",
                "getProduct",
                new Class[]{ long.class },
                l_ifoProduct);
            
            WEB3FuturesOptionsContractUnit[] l_web3FuturesOptionsContractUnits = new WEB3FuturesOptionsContractUnit[1];
            l_web3FuturesOptionsContractUnits[0] = new WEB3FuturesOptionsContractUnit();
            

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.ifo.WEB3FuturesOrderManagerImpl", 
                    "createContractUnitByOrder", 
                    new Class[]{ long.class },
                    l_web3FuturesOptionsContractUnits);

            WEB3GentradeBranch l_branch = new WEB3GentradeBranch(33382);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.gentrade.WEB3GentradeSubAccount",
                    "getWeb3GenBranch",
                    new Class[] {},
                    l_branch);
            
            WEB3GentradeMainAccount l_mainAccount = new WEB3GentradeMainAccount(333812512266L);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.gentrade.WEB3GentradeSubAccount", 
                    "getMainAccount", 
                    new Class[]{},
                    l_mainAccount);
            
            WEB3IfoQuoteDataImplForMock l_web3IfoQuoteData = new WEB3IfoQuoteDataImplForMock();

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.quoteadaptor.prototype.WEB3ProtoQuoteDataSupplierService", 
                    "getQuote", new Class[]{ TradedProduct.class, RealType.class },
                    l_web3IfoQuoteData);
            
            WEB3GentradeMarket l_market = new WEB3GentradeMarket(3306L);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.ifo.WEB3IfoProductImpl",
                    "getPrimaryMarket", 
                    new Class[]{},
                    l_market);
            
            OrderSubmissionResult l_submitResult = new OrderSubmissionResult(ProcessingResult.SUCCESS_RESULT);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.ifo.WEB3FuturesOrderManagerImpl",
                    "submitCancelOrder", 
                    new Class[]
                    {SubAccount.class, CancelOrderSpec.class, String.class, boolean.class},
                    l_submitResult);
        }
        catch(Exception l_ex)
        {
            log.error("", l_ex);
            fail();
        }
        log.exiting(TEST_START + STR_METHOD_NAME);
    }
    
    
    private class WEB3OptionCancelOrderServiceImplForTest extends WEB3OptionCancelOrderServiceImpl
    {
        public SubAccount getSubAccount() throws WEB3BaseException
        {
            WEB3GentradeSubAccount l_subAccount = null;
            try
            {
                SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
                // 口座ＩＤ]
                l_subAccountParams.setAccountId(333812512266L);
                // 補助口座ＩＤ
                l_subAccountParams.setSubAccountId(33381251220366L);
                if(l_blnIsOptionSubAccount)
                {
                    l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_OPTIONS_SUB_ACCOUNT);
                }
                
                
                l_subAccountParams.setInstitutionCode("1D");
                TestDBUtility.insertWithDel(l_subAccountParams);
                l_subAccount = new WEB3GentradeSubAccountForMock(333812512266L, 33381251220366L);
            }
            catch (Exception l_ex)
            {
                log.error("", l_ex);
                fail();
            }
            return l_subAccount;
        }
        
        public Trader getTrader() throws WEB3SystemLayerException
        {
            return null;
        }
    }
    
    private class WEB3OptionsCancelConfirmRequestForTest extends WEB3OptionsCancelConfirmRequest
    {
        public void validate() throws WEB3BaseException
        {
            log.debug("WEB3OptionsCancelConfirmRequestForMock.validate()");
        }
    }
    
    private class WEB3OptionsCancelCompleteRequestForTest extends WEB3OptionsCancelCompleteRequest
    {
        public void validate() throws WEB3BaseException
        {

        }
    }
}
@
