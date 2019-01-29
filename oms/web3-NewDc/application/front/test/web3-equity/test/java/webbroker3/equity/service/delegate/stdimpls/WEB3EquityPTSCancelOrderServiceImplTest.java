head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.00.57.49;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3EquityPTSCancelOrderServiceImplTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 
Author Name      : Daiwa Institute of Research
Revesion History : 
*/
package webbroker3.equity.service.delegate.stdimpls;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.message.ErrorInfo;
import com.fitechlabs.xtrade.kernel.util.ThreadLocalSystemAttributesRegistry;
import com.fitechlabs.xtrade.plugin.security.oplogin.impl.LoginInfoImplForMock;
import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeExecutionConditionType;
import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeTradedProduct;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderParams;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderUnitParams;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeProductParams;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeTradedProductParams;
import com.fitechlabs.xtrade.plugin.tc.eqtype.ordersubmitter.io.EqTypeCancelOrderSpec;
import com.fitechlabs.xtrade.plugin.tc.eqtype.ordersubmitter.io.EqTypeOrderSubmissionResult;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderCategEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderOpenStatusEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderStatusEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderValidationException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProcessingResult;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccountTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TaxTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.AssetParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.BranchParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.InstitutionParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MarketParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.ProductParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.SubAccountParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.TradedProductParams;

import test.util.TestDBUtility;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3OrderConditionOperatorDef;
import webbroker3.common.define.WEB3OrderingConditionDef;
import webbroker3.common.define.WEB3PriceConditionDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.equity.WEB3EquityCancelOrderSpec;
import webbroker3.equity.WEB3EquityProductQuote;
import webbroker3.equity.WEB3EquityTradedProductForMock;
import webbroker3.equity.message.WEB3EquityBookPriceRegistRequest;
import webbroker3.equity.message.WEB3EquityCancelCompleteRequest;
import webbroker3.equity.message.WEB3EquityCancelCompleteResponse;
import webbroker3.equity.message.WEB3EquityCancelConfirmRequest;
import webbroker3.equity.message.WEB3EquityCancelConfirmResponse;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.gentrade.WEB3GentradeTradingClendarContext;
import webbroker3.gentrade.data.BranchMarketPtsDealtCondParams;
import webbroker3.gentrade.data.TradingTimeParams;
import webbroker3.mock.TestBaseForMock;
import webbroker3.mock.util.WEB3MockObjectParamsValue;
import webbroker3.quoteadaptor.prototype.data.Web3QuoteProtoParams;
import webbroker3.tradingpower.WEB3TPTradingPowerService;
import webbroker3.tradingpower.WEB3TPTradingPowerServiceImpl;
import webbroker3.tradingpower.WEB3TPTradingPowerServiceImplForMock;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

public class WEB3EquityPTSCancelOrderServiceImplTest extends TestBaseForMock
{

    /**
     * ログユーティリティ<BR>
     */
    private static WEB3LogUtility log =
         WEB3LogUtility.getInstance(WEB3EquityPTSCancelOrderServiceImplTest.class);
    
    public WEB3EquityPTSCancelOrderServiceImplTest(String name)
    {
        super(name);
    }
    
    public void setUp() throws WEB3BaseException
    {
        MOCK_MANAGER.setIsMockUsed(true);
        TestDBUtility.deleteAll(TradingTimeParams.TYPE);
        TestDBUtility.deleteAll(MainAccountParams.TYPE);
        TestDBUtility.deleteAll(SubAccountParams.TYPE);
        TestDBUtility.deleteAll(EqtypeOrderParams.TYPE);
        TestDBUtility.deleteAll(EqtypeOrderUnitParams.TYPE);
        TestDBUtility.deleteAll(TradedProductParams.TYPE);
        TestDBUtility.deleteAll(ProductParams.TYPE);
        TestDBUtility.deleteAll(EqtypeTradedProductParams.TYPE);
        TestDBUtility.deleteAll(EqtypeProductParams.TYPE);
        TestDBUtility.deleteAll(BranchParams.TYPE);
        TestDBUtility.deleteAll(BranchMarketPtsDealtCondParams.TYPE);
        TestDBUtility.deleteAll(AssetParams.TYPE);
        TestDBUtility.deleteAll(MarketParams.TYPE);
        TestDBUtility.deleteAll(InstitutionParams.TYPE);
        InstitutionParams l_InstitutionParams = TestDBUtility.getInstitutionRow();
        TestDBUtility.insertWithDel(l_InstitutionParams);
    }

    public void tearDown() throws WEB3BaseException
    {
        TestDBUtility.deleteAll(TradingTimeParams.TYPE);
        TestDBUtility.deleteAll(MainAccountParams.TYPE);
        TestDBUtility.deleteAll(SubAccountParams.TYPE);
        TestDBUtility.deleteAll(EqtypeOrderParams.TYPE);
        TestDBUtility.deleteAll(EqtypeOrderUnitParams.TYPE);
        TestDBUtility.deleteAll(TradedProductParams.TYPE);
        TestDBUtility.deleteAll(ProductParams.TYPE);
        TestDBUtility.deleteAll(EqtypeTradedProductParams.TYPE);
        TestDBUtility.deleteAll(EqtypeProductParams.TYPE);
        TestDBUtility.deleteAll(BranchParams.TYPE);
        TestDBUtility.deleteAll(BranchMarketPtsDealtCondParams.TYPE);
        TestDBUtility.deleteAll(AssetParams.TYPE);
        TestDBUtility.deleteAll(MarketParams.TYPE);
    }
    /**
     * execute
     * request=null
     * WEB3ErrorCatalog.SYSTEM_ERROR_80017
     */
    public void test_execute_0001()
    {
        final String STR_METHOD_NAME = "test_execute_0001()";
        log.entering(TEST_START + STR_METHOD_NAME);
        WEB3EquityPTSCancelOrderServiceImpl l_cancelOrderServiceImpl = new WEB3EquityPTSCancelOrderServiceImpl();
        try
        {
            l_cancelOrderServiceImpl.execute(null);
            fail();
        }
        catch (WEB3BaseException e)
        {
            log.exiting(TEST_END + STR_METHOD_NAME);
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80017, e.getErrorInfo());
        }
        catch (Exception e)
        {
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    /**
     * execute
     * request パラメータタイプ不正
     * WEB3ErrorCatalog.SYSTEM_ERROR_80018
     */
    public void test_execute_0002()
    {
        final String STR_METHOD_NAME = "test_execute_0002()";
        log.entering(TEST_START + STR_METHOD_NAME);
        WEB3EquityPTSCancelOrderServiceImpl l_cancelOrderServiceImpl = new WEB3EquityPTSCancelOrderServiceImpl();
        WEB3GenRequest l_request = new WEB3EquityBookPriceRegistRequest();
        try
        {
            l_cancelOrderServiceImpl.execute(l_request);
            fail();
        }
        catch (WEB3BaseException e)
        {
            log.exiting(TEST_END + STR_METHOD_NAME);
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80018, e.getErrorInfo());
        }
        catch (Exception e)
        {
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    /**
     * execute
     * request 現物株式注文取消確認リクエスト
     * this.validate注文取消()
     * WEB3ErrorCatalog.BUSINESS_ERROR_00600
     */
    public void test_execute_0003()
    {
        final String STR_METHOD_NAME = "test_execute_0003()";
        log.entering(TEST_START + STR_METHOD_NAME);
        WEB3EquityPTSCancelOrderServiceImpl l_cancelOrderServiceImpl = new WEB3EquityPTSCancelOrderServiceImpl();
        WEB3GenRequest l_request = new WEB3EquityCancelConfirmRequest();
        try
        {
            l_cancelOrderServiceImpl.execute(l_request);
            fail();
        }
        catch (WEB3BaseException e)
        {
            log.exiting(TEST_END + STR_METHOD_NAME);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00600, e.getErrorInfo());
        }
        catch (Exception e)
        {
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    /**
     * execute
     * request 現物株式注文取消完了リクエスト
     * this.submit注文取消()
     * WEB3ErrorCatalog.BUSINESS_ERROR_00600
     */
    public void test_execute_0004()
    {
        final String STR_METHOD_NAME = "test_execute_0004()";
        log.entering(TEST_START + STR_METHOD_NAME);
        WEB3EquityPTSCancelOrderServiceImpl l_cancelOrderServiceImpl = new WEB3EquityPTSCancelOrderServiceImpl();
        WEB3GenRequest l_request = new WEB3EquityCancelCompleteRequest();
        try
        {
            l_cancelOrderServiceImpl.execute(l_request);
            fail();
        }
        catch (WEB3BaseException e)
        {
            log.exiting(TEST_END + STR_METHOD_NAME);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00600, e.getErrorInfo());
        }
        catch (Exception e)
        {
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    /**
     * submit注文取消
     * validate()
     * WEB3ErrorCatalog.BUSINESS_ERROR_00600
     */
    public void test_submitCancelOrder_0001()
    {
        final String STR_METHOD_NAME = "test_submitCancelOrder_0001()";
        log.entering(TEST_START + STR_METHOD_NAME);
        WEB3EquityPTSCancelOrderServiceImpl l_cancelOrderServiceImpl = new WEB3EquityPTSCancelOrderServiceImpl();
        WEB3EquityCancelCompleteRequest l_cancelCompleteRequest = new WEB3EquityCancelCompleteRequest();
        try
        {
            l_cancelOrderServiceImpl.submitCancelOrder(l_cancelCompleteRequest);
            fail();
        }
        catch (WEB3BaseException e)
        {
            log.exiting(TEST_END + STR_METHOD_NAME);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00600, e.getErrorInfo());
        }
        catch (Exception e)
        {
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    /**
     * submit注文取消
     * validate注文受付可能()
     * WEB3ErrorCatalog.SYSTEM_ERROR_80005
     */
    public void test_submitCancelOrder_0002()
    {
        final String STR_METHOD_NAME = "test_submitCancelOrder_0002()";
        log.entering(TEST_START + STR_METHOD_NAME);
        WEB3EquityPTSCancelOrderServiceImpl l_cancelOrderServiceImpl = new WEB3EquityPTSCancelOrderServiceImpl();
        WEB3EquityCancelCompleteRequest l_cancelCompleteRequest = new WEB3EquityCancelCompleteRequest();
        l_cancelCompleteRequest.id = "10";
        try
        {
            WEB3GentradeTradingClendarContext l_clendarContext = new WEB3GentradeTradingClendarContext();
            l_clendarContext.setInstitutionCode("0D");
            l_clendarContext.setBranchCode("381");
            l_clendarContext.setProductCode("0");
            l_clendarContext.setMarketCode("SP");
            l_clendarContext.setTradingTimeType("26");
            l_clendarContext.setBizDateType("0");
            ThreadLocalSystemAttributesRegistry.setAttribute(
                "web3.tradingcalendarcontext", l_clendarContext);
            
            ThreadLocalSystemAttributesRegistry.setAttribute(
                    "xblocks.gtl.attributes.systemtimestamp",
                    new Timestamp(Calendar.getInstance().getTime().getTime()));

            l_cancelOrderServiceImpl.submitCancelOrder(l_cancelCompleteRequest);
            fail();
        }
        catch (WEB3BaseException e)
        {
            log.exiting(TEST_END + STR_METHOD_NAME);
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80005, e.getErrorInfo());
        }
        catch (Exception e)
        {
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    /**
     * submit注文取消
     * validatePTS取消注文()
     * WEB3ErrorCatalog.BUSINESS_ERROR_00820
     */
    public void test_submitCancelOrder_0003()
    {
        final String STR_METHOD_NAME = "test_submitCancelOrder_0003()";
        log.entering(TEST_START + STR_METHOD_NAME);
        WEB3EquityPTSCancelOrderServiceImpl l_cancelOrderServiceImpl = new WEB3EquityPTSCancelOrderServiceImpl();
        WEB3EquityCancelCompleteRequest l_cancelCompleteRequest = new WEB3EquityCancelCompleteRequest();
        l_cancelCompleteRequest.id = "10";
        WEB3GentradeTradingClendarContext l_clendarContext = new WEB3GentradeTradingClendarContext();
        l_clendarContext.setInstitutionCode("0D");
        l_clendarContext.setBranchCode("381");
        l_clendarContext.setProductCode("0");
        l_clendarContext.setMarketCode("SP");
        l_clendarContext.setTradingTimeType("26");
        l_clendarContext.setBizDateType("0");
        ThreadLocalSystemAttributesRegistry.setAttribute(
            "web3.tradingcalendarcontext", l_clendarContext);
        
        Calendar ca =  Calendar.getInstance();
        ca.set(2007,6-1,12);// 注意月?要減1
        Date date = ca.getTime();
        Timestamp st = new Timestamp(date.getTime());
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "com.fitechlabs.xtrade.plugin.tc." + 
                "gentrade.stdimpls.TradingSystemImpl", 
            "getSystemTimestamp",
            new Class[]{}, 
            st);
        
        ThreadLocalSystemAttributesRegistry.setAttribute(
                "web3.attributes.basetimestampfororderbizdate",
                null);
                
        ThreadLocalSystemAttributesRegistry.setAttribute(
                "xblocks.gtl.attributes.systemtimestamp",
                st);
        try
        {
            MOCK_MANAGER.setIsMockUsed(true);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl", "getLoginInfo",
                    new Class[] {},
                    new LoginInfoImplForMock());
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.LoginInfoImpl",
                    "getLoginId",
                    new Class[] {},
                    new Long(123));
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl", "getAccountId",
                 new Class[] {}, new Long(333812512246L));
            
            TestDBUtility.deleteAll(TradingTimeParams.TYPE);
            TestDBUtility.deleteAll(MainAccountParams.TYPE);
            TestDBUtility.deleteAll(SubAccountParams.TYPE);
            TestDBUtility.deleteAll(EqtypeOrderUnitParams.TYPE);
            TestDBUtility.deleteAll(MarketParams.TYPE);
            
            MarketParams l_marketParams = TestDBUtility.getMarketRow();
            TestDBUtility.insertWithDel(l_marketParams);
            
            EqtypeOrderParams l_eqtypeOrderParams = TestDBUtility.getEqtypeOrderRow();
            l_eqtypeOrderParams.setOrderId(10L);
            TestDBUtility.insertWithDel(l_eqtypeOrderParams);
            
            EqtypeOrderUnitParams l_eqtypeOrderUnitParams = TestDBUtility.getEqtypeOrderUnitRow();
            l_eqtypeOrderUnitParams.setOrderStatus(OrderStatusEnum.NOT_CANCELLED);
            l_eqtypeOrderUnitParams.setOrderOpenStatus(OrderOpenStatusEnum.CLOSED);
            l_eqtypeOrderUnitParams.setOrderId(10L);
            l_eqtypeOrderUnitParams.setProductId(3304148080001L);
            l_eqtypeOrderUnitParams.setAccountId(333812512246L);
            l_eqtypeOrderUnitParams.setOrderType(OrderTypeEnum.EQUITY_BUY);
            l_eqtypeOrderUnitParams.setOrderCateg(OrderCategEnum.ASSET);
            l_eqtypeOrderUnitParams.setProductType(ProductTypeEnum.EQUITY);
            
            TestDBUtility.insertWithDel(l_eqtypeOrderUnitParams);
            
            TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams.setBizDateType("1");
            TestDBUtility.insertWithDel(l_tradingTimeParams);
            
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            TestDBUtility.insertWithDel(l_mainAccountParams);
            
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setAccountId(333812512246L);
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
            TestDBUtility.insertWithDel(l_subAccountParams);
            
            l_cancelOrderServiceImpl.submitCancelOrder(l_cancelCompleteRequest);
            fail();
        }
        catch (WEB3BaseException e)
        {
            log.exiting(TEST_END + STR_METHOD_NAME);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00820, e.getErrorInfo());
            
        }
        catch (Exception e)
        {
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    /**
     * submit注文取消
     * submit現物株式注文取消()
     * error
     */
    public void test_submitCancelOrder_0004()
    {
        final String STR_METHOD_NAME = "test_submitCancelOrder_0004()";
        log.entering(TEST_START + STR_METHOD_NAME);
        WEB3EquityPTSCancelOrderServiceImpl l_cancelOrderServiceImpl = new WEB3EquityPTSCancelOrderServiceImpl();
        WEB3EquityCancelCompleteRequest l_cancelCompleteRequest = new WEB3EquityCancelCompleteRequest();
        l_cancelCompleteRequest.id = "10";
        l_cancelCompleteRequest.password = "1";
        WEB3GentradeTradingClendarContext l_clendarContext = new WEB3GentradeTradingClendarContext();
        l_clendarContext.setInstitutionCode("0D");
        l_clendarContext.setBranchCode("381");
        l_clendarContext.setProductCode("0");
        l_clendarContext.setMarketCode("SP");
        l_clendarContext.setTradingTimeType("26");
        l_clendarContext.setBizDateType("0");
        ThreadLocalSystemAttributesRegistry.setAttribute(
            "web3.tradingcalendarcontext", l_clendarContext);
        
        Calendar ca =  Calendar.getInstance();
        ca.set(2007,6-1,12);// 注意月?要減1
        Date date = ca.getTime();
        Timestamp st = new Timestamp(date.getTime());
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "com.fitechlabs.xtrade.plugin.tc." + 
                "gentrade.stdimpls.TradingSystemImpl", 
            "getSystemTimestamp",
            new Class[]{}, 
            st);
        
        ThreadLocalSystemAttributesRegistry.setAttribute(
                "web3.attributes.basetimestampfororderbizdate",
                null);
                
        ThreadLocalSystemAttributesRegistry.setAttribute(
                "xblocks.gtl.attributes.systemtimestamp",
                st);
        try
        {
            MOCK_MANAGER.setIsMockUsed(true);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getLoginInfo",
                new Class[] {},
                new LoginInfoImplForMock());
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.LoginInfoImpl",
                "getLoginId",
                new Class[] {},
                new Long(1));
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getAccountId",
                 new Class[] {}, new Long(333812512246L));
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getSessionProperty",
                new Class[] {String.class},
                "2");
            ErrorInfo l_errorInfo = new ErrorInfo();
            l_errorInfo.setErrorCode("111");
            l_errorInfo.setErrorMessage("12312");
            OrderValidationException l_ex = new OrderValidationException(l_errorInfo);
            ProcessingResult l_processingResult = l_ex.getValidationResult().getProcessingResult();
            EqTypeOrderSubmissionResult l_orderSubmissionResult = new EqTypeOrderSubmissionResult(l_processingResult);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.equity.WEB3EquityPTSOrderManager",
                "submitCancelOrder",
                new Class[]
                { SubAccount.class, EqTypeCancelOrderSpec.class, String.class, boolean.class },
                l_orderSubmissionResult);
            
            TestDBUtility.deleteAll(TradingTimeParams.TYPE);
            TestDBUtility.deleteAll(MainAccountParams.TYPE);
            TestDBUtility.deleteAll(SubAccountParams.TYPE);
            TestDBUtility.deleteAll(EqtypeOrderParams.TYPE);
            TestDBUtility.deleteAll(EqtypeOrderUnitParams.TYPE);
            TestDBUtility.deleteAll(TradedProductParams.TYPE);
            TestDBUtility.deleteAll(ProductParams.TYPE);
            TestDBUtility.deleteAll(EqtypeTradedProductParams.TYPE);
            TestDBUtility.deleteAll(EqtypeProductParams.TYPE);
            TestDBUtility.deleteAll(BranchParams.TYPE);
            TestDBUtility.deleteAll(BranchMarketPtsDealtCondParams.TYPE);
            TestDBUtility.deleteAll(MarketParams.TYPE);
            
            
            MarketParams l_marketParams = TestDBUtility.getMarketRow();
            TestDBUtility.insertWithDel(l_marketParams);
            
            
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setBranchId(33381);
            TestDBUtility.insertWithDel(l_branchParams);
            
            TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams.setBizDateType("1");
            TestDBUtility.insertWithDel(l_tradingTimeParams);
            
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(333812512246L);
            l_mainAccountParams.setBranchId(33381L);
            TestDBUtility.insertWithDel(l_mainAccountParams);
            
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setCashBalance(12.0);
            l_subAccountParams.setAccountId(333812512246L);
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
            TestDBUtility.insertWithDel(l_subAccountParams);
            
            EqtypeOrderParams l_eqtypeOrderParams = TestDBUtility.getEqtypeOrderRow();
            l_eqtypeOrderParams.setOrderId(10L);
            TestDBUtility.insertWithDel(l_eqtypeOrderParams);
            
            EqtypeOrderUnitParams l_eqtypeOrderUnitParams = TestDBUtility.getEqtypeOrderUnitRow();
            l_eqtypeOrderUnitParams.setOrderStatus(OrderStatusEnum.NOT_CANCELLED);
            l_eqtypeOrderUnitParams.setOrderOpenStatus(OrderOpenStatusEnum.OPEN);
            l_eqtypeOrderUnitParams.setOrderId(10L);
            l_eqtypeOrderUnitParams.setProductId(3304148080001L);
            l_eqtypeOrderUnitParams.setAccountId(333812512246L);
            l_eqtypeOrderUnitParams.setOrderType(OrderTypeEnum.EQUITY_BUY);
            l_eqtypeOrderUnitParams.setOrderCateg(OrderCategEnum.ASSET);
            l_eqtypeOrderUnitParams.setProductType(ProductTypeEnum.EQUITY);
            l_eqtypeOrderUnitParams.setConfirmedQuantity(231D);
            TestDBUtility.insertWithDel(l_eqtypeOrderUnitParams);
            
            ProductParams l_productParams = TestDBUtility.getProductRow();
            l_productParams.setProductType(ProductTypeEnum.EQUITY);
            l_productParams.setProductId(3304148080001L);
            TestDBUtility.insertWithDel(l_productParams);
            
            EqtypeProductParams l_eqtypeProductParams = TestDBUtility.getEqtypeProductRow();
            l_eqtypeProductParams.setProductId(3304148080001L);
            TestDBUtility.insertWithDel(l_eqtypeProductParams);
            
            TradedProductParams l_tradedProductParams = TestDBUtility.getTradedProductRow();
            l_tradedProductParams.setProductId(3304148080001L);
            l_tradedProductParams.setProductType(ProductTypeEnum.EQUITY);
            TestDBUtility.insertWithDel(l_tradedProductParams);
            
            EqtypeTradedProductParams l_eqtypeTradedProductParams = TestDBUtility.getEqtypeTradedProductRow();
            l_eqtypeTradedProductParams.setProductId(3304148080001L);
            TestDBUtility.insertWithDel(l_eqtypeTradedProductParams);
            
            BranchMarketPtsDealtCondParams l_branchMarketPTSDealtCondParams = new BranchMarketPtsDealtCondParams();
            l_branchMarketPTSDealtCondParams.setInstitutionCode("0D");
            l_branchMarketPTSDealtCondParams.setBranchCode("381");
            l_branchMarketPTSDealtCondParams.setMarketCode("SP");
            l_branchMarketPTSDealtCondParams.setMartCanDealtEquity("1");
            TestDBUtility.insertWithDel(l_branchMarketPTSDealtCondParams);
            
            l_cancelOrderServiceImpl.submitCancelOrder(l_cancelCompleteRequest);
            fail();
        }
        catch (WEB3BaseException e)
        {
            log.exiting(TEST_END + STR_METHOD_NAME);
            assertEquals("12312", e.getErrorInfo().getErrorMessage());
        }
        catch (Exception e)
        {
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    /**
     * submit注文取消
     * submit現物株式注文取消()
     * 正しい
     */
    public void test_submitCancelOrder_0005()
    {
        final String STR_METHOD_NAME = "test_submitCancelOrder_0005()";
        log.entering(TEST_START + STR_METHOD_NAME);
        WEB3EquityPTSCancelOrderServiceImpl l_cancelOrderServiceImpl = new WEB3EquityPTSCancelOrderServiceImpl();
        WEB3EquityCancelCompleteRequest l_cancelCompleteRequest = new WEB3EquityCancelCompleteRequest();
        l_cancelCompleteRequest.id = "10";
        l_cancelCompleteRequest.password = "1";
        WEB3GentradeTradingClendarContext l_clendarContext = new WEB3GentradeTradingClendarContext();
        l_clendarContext.setInstitutionCode("0D");
        l_clendarContext.setBranchCode("381");
        l_clendarContext.setProductCode("0");
        l_clendarContext.setMarketCode("SP");
        l_clendarContext.setTradingTimeType("26");
        l_clendarContext.setBizDateType("0");
        ThreadLocalSystemAttributesRegistry.setAttribute(
            "web3.tradingcalendarcontext", l_clendarContext);
        
        Calendar ca =  Calendar.getInstance();
        ca.set(2007,6-1,12);// 注意月?要減1
        Date date = ca.getTime();
        Timestamp st = new Timestamp(date.getTime());
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "com.fitechlabs.xtrade.plugin.tc." + 
                "gentrade.stdimpls.TradingSystemImpl", 
            "getSystemTimestamp",
            new Class[]{}, 
            st);
        
        ThreadLocalSystemAttributesRegistry.setAttribute(
                "web3.attributes.basetimestampfororderbizdate",
                null);
                
        ThreadLocalSystemAttributesRegistry.setAttribute(
                "xblocks.gtl.attributes.systemtimestamp",
                st);
        try
        {
            MOCK_MANAGER.setIsMockUsed(true);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getLoginInfo",
                new Class[] {},
                new LoginInfoImplForMock());
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.LoginInfoImpl",
                "getLoginId",
                new Class[] {},
                new Long(1));
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getAccountId",
                 new Class[] {}, new Long(333812512246L));
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getSessionProperty",
                new Class[] {String.class},
                "2");
//            ErrorInfo l_errorInfo = new ErrorInfo();
//            l_errorInfo.setErrorCode("111");
//            l_errorInfo.setErrorMessage("12312");
//            OrderValidationException l_ex = new OrderValidationException(l_errorInfo);
            ProcessingResult l_processingResult = ProcessingResult.newSuccessResultInstance();
            EqTypeOrderSubmissionResult l_orderSubmissionResult = new EqTypeOrderSubmissionResult(l_processingResult);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.equity.WEB3EquityPTSOrderManager",
                "submitCancelOrder",
                new Class[]
                { SubAccount.class, EqTypeCancelOrderSpec.class, String.class, boolean.class },
                l_orderSubmissionResult);
            
            TestDBUtility.deleteAll(TradingTimeParams.TYPE);
            TestDBUtility.deleteAll(MainAccountParams.TYPE);
            TestDBUtility.deleteAll(SubAccountParams.TYPE);
            TestDBUtility.deleteAll(EqtypeOrderParams.TYPE);
            TestDBUtility.deleteAll(EqtypeOrderUnitParams.TYPE);
            TestDBUtility.deleteAll(TradedProductParams.TYPE);
            TestDBUtility.deleteAll(ProductParams.TYPE);
            TestDBUtility.deleteAll(EqtypeTradedProductParams.TYPE);
            TestDBUtility.deleteAll(EqtypeProductParams.TYPE);
            TestDBUtility.deleteAll(BranchParams.TYPE);
            TestDBUtility.deleteAll(BranchMarketPtsDealtCondParams.TYPE);
            TestDBUtility.deleteAll(MarketParams.TYPE);
            
            
            MarketParams l_marketParams = TestDBUtility.getMarketRow();
            TestDBUtility.insertWithDel(l_marketParams);
            
            
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setBranchId(33381);
            TestDBUtility.insertWithDel(l_branchParams);
            
            TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams.setBizDateType("1");
            TestDBUtility.insertWithDel(l_tradingTimeParams);
            
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(333812512246L);
            l_mainAccountParams.setBranchId(33381L);
            TestDBUtility.insertWithDel(l_mainAccountParams);
            
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setCashBalance(12.0);
            l_subAccountParams.setAccountId(333812512246L);
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
            TestDBUtility.insertWithDel(l_subAccountParams);
            
            EqtypeOrderParams l_eqtypeOrderParams = TestDBUtility.getEqtypeOrderRow();
            l_eqtypeOrderParams.setOrderId(10L);
            TestDBUtility.insertWithDel(l_eqtypeOrderParams);
            
            EqtypeOrderUnitParams l_eqtypeOrderUnitParams = TestDBUtility.getEqtypeOrderUnitRow();
            l_eqtypeOrderUnitParams.setOrderStatus(OrderStatusEnum.NOT_CANCELLED);
            l_eqtypeOrderUnitParams.setOrderOpenStatus(OrderOpenStatusEnum.OPEN);
            l_eqtypeOrderUnitParams.setOrderId(10L);
            l_eqtypeOrderUnitParams.setProductId(3304148080001L);
            l_eqtypeOrderUnitParams.setAccountId(333812512246L);
            l_eqtypeOrderUnitParams.setOrderType(OrderTypeEnum.EQUITY_BUY);
            l_eqtypeOrderUnitParams.setOrderCateg(OrderCategEnum.ASSET);
            l_eqtypeOrderUnitParams.setProductType(ProductTypeEnum.EQUITY);
            l_eqtypeOrderUnitParams.setConfirmedQuantity(231D);
            TestDBUtility.insertWithDel(l_eqtypeOrderUnitParams);
            
            ProductParams l_productParams = TestDBUtility.getProductRow();
            l_productParams.setProductType(ProductTypeEnum.EQUITY);
            l_productParams.setProductId(3304148080001L);
            TestDBUtility.insertWithDel(l_productParams);
            
            EqtypeProductParams l_eqtypeProductParams = TestDBUtility.getEqtypeProductRow();
            l_eqtypeProductParams.setProductId(3304148080001L);
            TestDBUtility.insertWithDel(l_eqtypeProductParams);
            
            TradedProductParams l_tradedProductParams = TestDBUtility.getTradedProductRow();
            l_tradedProductParams.setProductId(3304148080001L);
            l_tradedProductParams.setProductType(ProductTypeEnum.EQUITY);
            TestDBUtility.insertWithDel(l_tradedProductParams);
            
            EqtypeTradedProductParams l_eqtypeTradedProductParams = TestDBUtility.getEqtypeTradedProductRow();
            l_eqtypeTradedProductParams.setProductId(3304148080001L);
            TestDBUtility.insertWithDel(l_eqtypeTradedProductParams);
            
            BranchMarketPtsDealtCondParams l_branchMarketPTSDealtCondParams = new BranchMarketPtsDealtCondParams();
            l_branchMarketPTSDealtCondParams.setInstitutionCode("0D");
            l_branchMarketPTSDealtCondParams.setBranchCode("381");
            l_branchMarketPTSDealtCondParams.setMarketCode("SP");
            l_branchMarketPTSDealtCondParams.setMartCanDealtEquity("1");
            TestDBUtility.insertWithDel(l_branchMarketPTSDealtCondParams);
            
            Services.overrideService(WEB3TPTradingPowerService.class, new WEB3TPTradingPowerServiceImplForTest());
            
            WEB3EquityCancelCompleteResponse l_completeResponse = l_cancelOrderServiceImpl.submitCancelOrder(l_cancelCompleteRequest);
            
            WEB3MockObjectParamsValue l_paramsValue = TestBaseForMock.MOCK_MANAGER.getMethodParamsValue(
                    "webbroker3.equity.WEB3EquityPTSOrderManager",
                    "submitCancelOrder",
                    new Class[]
                    { SubAccount.class, EqTypeCancelOrderSpec.class, String.class, boolean.class });
                    
            assertEquals(WEB3GentradeSubAccount.class,((SubAccount)l_paramsValue.getFirstCalled()[0]).getClass());
            assertEquals(WEB3EquityCancelOrderSpec.class,((EqTypeCancelOrderSpec)l_paramsValue.getFirstCalled()[1]).getClass());
            assertEquals("1",((String)l_paramsValue.getFirstCalled()[2]));
            assertEquals(true,((Boolean)l_paramsValue.getFirstCalled()[3]).booleanValue());
                
            //更新時間
            assertEquals(WEB3DateUtility.formatDate(Calendar.getInstance().getTime(), "yyyyMMdd"),
                    WEB3DateUtility.formatDate(l_completeResponse.lastUpdatedTimestamp, "yyyyMMdd"));
            //識別番号
            assertEquals("10",l_completeResponse.orderActionId);
            //連続注文設定フラグ
            assertFalse(l_completeResponse.succSettingFlag);
            
            Services.overrideService(WEB3TPTradingPowerService.class, new WEB3TPTradingPowerServiceImplForMock());
        }
        catch (WEB3BaseException e)
        {
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        catch (Exception e)
        {
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    /**
     * submit注文取消
     * submit現物株式注文取消()
     * 正しい
     */
    public void test_submitCancelOrder_0006()
    {
        final String STR_METHOD_NAME = "test_submitCancelOrder_0006()";
        log.entering(TEST_START + STR_METHOD_NAME);
        WEB3EquityPTSCancelOrderServiceImpl l_cancelOrderServiceImpl = new WEB3EquityPTSCancelOrderServiceImpl();
        WEB3EquityCancelCompleteRequest l_cancelCompleteRequest = new WEB3EquityCancelCompleteRequest();
        l_cancelCompleteRequest.id = "10";
        l_cancelCompleteRequest.password = "1";
        WEB3GentradeTradingClendarContext l_clendarContext = new WEB3GentradeTradingClendarContext();
        l_clendarContext.setInstitutionCode("0D");
        l_clendarContext.setBranchCode("381");
        l_clendarContext.setProductCode("0");
        l_clendarContext.setMarketCode("SP");
        l_clendarContext.setTradingTimeType("26");
        l_clendarContext.setBizDateType("0");
        ThreadLocalSystemAttributesRegistry.setAttribute(
            "web3.tradingcalendarcontext", l_clendarContext);
        
        Calendar ca =  Calendar.getInstance();
        ca.set(2007,6-1,12);// 注意月?要減1
        Date date = ca.getTime();
        Timestamp st = new Timestamp(date.getTime());
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "com.fitechlabs.xtrade.plugin.tc." + 
                "gentrade.stdimpls.TradingSystemImpl", 
            "getSystemTimestamp",
            new Class[]{}, 
            st);
        
        ThreadLocalSystemAttributesRegistry.setAttribute(
                "web3.attributes.basetimestampfororderbizdate",
                null);
                
        ThreadLocalSystemAttributesRegistry.setAttribute(
                "xblocks.gtl.attributes.systemtimestamp",
                st);
        try
        {
            MOCK_MANAGER.setIsMockUsed(true);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getLoginInfo",
                new Class[] {},
                new LoginInfoImplForMock());
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.LoginInfoImpl",
                "getLoginId",
                new Class[] {},
                new Long(1));
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getAccountId",
                 new Class[] {}, new Long(333812512246L));
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getSessionProperty",
                new Class[] {String.class},
                "2");
//            ErrorInfo l_errorInfo = new ErrorInfo();
//            l_errorInfo.setErrorCode("111");
//            l_errorInfo.setErrorMessage("12312");
//            OrderValidationException l_ex = new OrderValidationException(l_errorInfo);
            ProcessingResult l_processingResult = ProcessingResult.newSuccessResultInstance();
            EqTypeOrderSubmissionResult l_orderSubmissionResult = new EqTypeOrderSubmissionResult(l_processingResult);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.equity.WEB3EquityPTSOrderManager",
                "submitCancelOrder",
                new Class[]
                { SubAccount.class, EqTypeCancelOrderSpec.class, String.class, boolean.class },
                l_orderSubmissionResult);
            
            TestDBUtility.deleteAll(TradingTimeParams.TYPE);
            TestDBUtility.deleteAll(MainAccountParams.TYPE);
            TestDBUtility.deleteAll(SubAccountParams.TYPE);
            TestDBUtility.deleteAll(EqtypeOrderParams.TYPE);
            TestDBUtility.deleteAll(EqtypeOrderUnitParams.TYPE);
            TestDBUtility.deleteAll(TradedProductParams.TYPE);
            TestDBUtility.deleteAll(ProductParams.TYPE);
            TestDBUtility.deleteAll(EqtypeTradedProductParams.TYPE);
            TestDBUtility.deleteAll(EqtypeProductParams.TYPE);
            TestDBUtility.deleteAll(BranchParams.TYPE);
            TestDBUtility.deleteAll(BranchMarketPtsDealtCondParams.TYPE);
            TestDBUtility.deleteAll(MarketParams.TYPE);
            
            
            MarketParams l_marketParams = TestDBUtility.getMarketRow();
            TestDBUtility.insertWithDel(l_marketParams);
            
            
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setBranchId(33381);
            TestDBUtility.insertWithDel(l_branchParams);
            
            TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams.setBizDateType("1");
            TestDBUtility.insertWithDel(l_tradingTimeParams);
            
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(333812512246L);
            l_mainAccountParams.setBranchId(33381L);
            TestDBUtility.insertWithDel(l_mainAccountParams);
            
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setCashBalance(12.0);
            l_subAccountParams.setAccountId(333812512246L);
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
            TestDBUtility.insertWithDel(l_subAccountParams);
            
            EqtypeOrderParams l_eqtypeOrderParams = TestDBUtility.getEqtypeOrderRow();
            l_eqtypeOrderParams.setOrderId(10L);
            TestDBUtility.insertWithDel(l_eqtypeOrderParams);
            
            EqtypeOrderUnitParams l_eqtypeOrderUnitParams = TestDBUtility.getEqtypeOrderUnitRow();
            l_eqtypeOrderUnitParams.setOrderStatus(OrderStatusEnum.NOT_CANCELLED);
            l_eqtypeOrderUnitParams.setOrderOpenStatus(OrderOpenStatusEnum.OPEN);
            l_eqtypeOrderUnitParams.setOrderId(10L);
            l_eqtypeOrderUnitParams.setProductId(3304148080001L);
            l_eqtypeOrderUnitParams.setAccountId(333812512246L);
            l_eqtypeOrderUnitParams.setOrderType(OrderTypeEnum.EQUITY_BUY);
            l_eqtypeOrderUnitParams.setOrderCateg(OrderCategEnum.ASSET);
            l_eqtypeOrderUnitParams.setProductType(ProductTypeEnum.EQUITY);
            l_eqtypeOrderUnitParams.setConfirmedQuantity(231D);
            l_eqtypeOrderUnitParams.setExecutedQuantity(5.0D);
            TestDBUtility.insertWithDel(l_eqtypeOrderUnitParams);
            
            ProductParams l_productParams = TestDBUtility.getProductRow();
            l_productParams.setProductType(ProductTypeEnum.EQUITY);
            l_productParams.setProductId(3304148080001L);
            TestDBUtility.insertWithDel(l_productParams);
            
            EqtypeProductParams l_eqtypeProductParams = TestDBUtility.getEqtypeProductRow();
            l_eqtypeProductParams.setProductId(3304148080001L);
            TestDBUtility.insertWithDel(l_eqtypeProductParams);
            
            TradedProductParams l_tradedProductParams = TestDBUtility.getTradedProductRow();
            l_tradedProductParams.setProductId(3304148080001L);
            l_tradedProductParams.setProductType(ProductTypeEnum.EQUITY);
            TestDBUtility.insertWithDel(l_tradedProductParams);
            
            EqtypeTradedProductParams l_eqtypeTradedProductParams = TestDBUtility.getEqtypeTradedProductRow();
            l_eqtypeTradedProductParams.setProductId(3304148080001L);
            TestDBUtility.insertWithDel(l_eqtypeTradedProductParams);
            
            BranchMarketPtsDealtCondParams l_branchMarketPTSDealtCondParams = new BranchMarketPtsDealtCondParams();
            l_branchMarketPTSDealtCondParams.setInstitutionCode("0D");
            l_branchMarketPTSDealtCondParams.setBranchCode("381");
            l_branchMarketPTSDealtCondParams.setMarketCode("SP");
            l_branchMarketPTSDealtCondParams.setMartCanDealtEquity("1");
            TestDBUtility.insertWithDel(l_branchMarketPTSDealtCondParams);
            
            Services.overrideService(WEB3TPTradingPowerService.class, new WEB3TPTradingPowerServiceImplForTest());
            
            WEB3EquityCancelCompleteResponse l_completeResponse = l_cancelOrderServiceImpl.submitCancelOrder(l_cancelCompleteRequest);
            
            WEB3MockObjectParamsValue l_paramsValue = TestBaseForMock.MOCK_MANAGER.getMethodParamsValue(
                    "webbroker3.equity.WEB3EquityPTSOrderManager",
                    "submitCancelOrder",
                    new Class[]
                    { SubAccount.class, EqTypeCancelOrderSpec.class, String.class, boolean.class });
                    
            assertEquals(WEB3GentradeSubAccount.class,((SubAccount)l_paramsValue.getFirstCalled()[0]).getClass());
            assertEquals(WEB3EquityCancelOrderSpec.class,((EqTypeCancelOrderSpec)l_paramsValue.getFirstCalled()[1]).getClass());
            assertEquals("1",((String)l_paramsValue.getFirstCalled()[2]));
            assertEquals(true,((Boolean)l_paramsValue.getFirstCalled()[3]).booleanValue());
                
            //更新時間
            assertEquals(WEB3DateUtility.formatDate(Calendar.getInstance().getTime(), "yyyyMMdd"),
                    WEB3DateUtility.formatDate(l_completeResponse.lastUpdatedTimestamp, "yyyyMMdd"));
            //識別番号
            assertEquals("10",l_completeResponse.orderActionId);
            //連続注文設定フラグ
            assertFalse(l_completeResponse.succSettingFlag);
            
            Services.overrideService(WEB3TPTradingPowerService.class, new WEB3TPTradingPowerServiceImplForMock());
        }
        catch (WEB3BaseException e)
        {
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        catch (Exception e)
        {
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    /**
     * validate注文取消
     * validate( )
     * WEB3ErrorCatalog.BUSINESS_ERROR_00600
     */
    public void test_validateCancelOrder_0001()
    {
        final String STR_METHOD_NAME = "test_validateCancelOrder_0001()";
        log.entering(TEST_START + STR_METHOD_NAME);
        WEB3EquityPTSCancelOrderServiceImpl l_cancelOrderServiceImpl = new WEB3EquityPTSCancelOrderServiceImpl();
        WEB3EquityCancelConfirmRequest l_cancelConfirmRequest = new WEB3EquityCancelConfirmRequest();
        
        try
        {
            l_cancelOrderServiceImpl.validateCancelOrder(l_cancelConfirmRequest);
            fail();
        }
        catch (WEB3BaseException e)
        {
            log.exiting(TEST_END + STR_METHOD_NAME);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00600, e.getErrorInfo());
        }
        catch (Exception e)
        {
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        
        log.exiting(TEST_END + STR_METHOD_NAME);
        
    }
    
    /**
     * validate注文取消
     * validate注文受付可能( )
     * WEB3ErrorCatalog.SYSTEM_ERROR_80005
     */
    public void test_validateCancelOrder_0002()
    {
        final String STR_METHOD_NAME = "test_validateCancelOrder_0002()";
        log.entering(TEST_START + STR_METHOD_NAME);
        WEB3EquityPTSCancelOrderServiceImpl l_cancelOrderServiceImpl = new WEB3EquityPTSCancelOrderServiceImpl();
        WEB3EquityCancelConfirmRequest l_cancelConfirmRequest = new WEB3EquityCancelConfirmRequest();
        l_cancelConfirmRequest.id = "10";
        WEB3GentradeTradingClendarContext l_clendarContext = new WEB3GentradeTradingClendarContext();
        l_clendarContext.setInstitutionCode("0D");
        l_clendarContext.setBranchCode("381");
        l_clendarContext.setProductCode("1");
        l_clendarContext.setMarketCode("SP");
        l_clendarContext.setTradingTimeType("26");
        l_clendarContext.setBizDateType("0");
        ThreadLocalSystemAttributesRegistry.setAttribute(
            "web3.tradingcalendarcontext", l_clendarContext);
        
        Calendar ca =  Calendar.getInstance();
        ca.set(2007,6-1,12);// 注意月?要減1
        Date date = ca.getTime();
        Timestamp st = new Timestamp(date.getTime());
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "com.fitechlabs.xtrade.plugin.tc." + 
                "gentrade.stdimpls.TradingSystemImpl", 
            "getSystemTimestamp",
            new Class[]{}, 
            st);
        
        ThreadLocalSystemAttributesRegistry.setAttribute(
                "web3.attributes.basetimestampfororderbizdate",
                null);
                
        ThreadLocalSystemAttributesRegistry.setAttribute(
                "xblocks.gtl.attributes.systemtimestamp",
                st);
        try
        {

            MOCK_MANAGER.setIsMockUsed(true);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl", "getLoginInfo",
                    new Class[] {},
                    new LoginInfoImplForMock());
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.LoginInfoImpl",
                    "getLoginId",
                    new Class[] {},
                    new Long(123));
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl", "getAccountId",
                 new Class[] {}, new Long(333812512246L));
            
            TestDBUtility.deleteAll(TradingTimeParams.TYPE);
            TestDBUtility.deleteAll(MainAccountParams.TYPE);
            TestDBUtility.deleteAll(SubAccountParams.TYPE);
            TestDBUtility.deleteAll(BranchParams.TYPE);
            TestDBUtility.deleteAll(MarketParams.TYPE);
            
            
            MarketParams l_marketParams = TestDBUtility.getMarketRow();
            TestDBUtility.insertWithDel(l_marketParams);
            
            
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setBranchId(33381);
            TestDBUtility.insertWithDel(l_branchParams);
            
            TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams.setBizDateType("1");
            TestDBUtility.insertWithDel(l_tradingTimeParams);
            
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(333812512246L);
            l_mainAccountParams.setBranchId(33381L);
            TestDBUtility.insertWithDel(l_mainAccountParams);
            
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setCashBalance(12.0);
            l_subAccountParams.setAccountId(333812512246L);
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
            TestDBUtility.insertWithDel(l_subAccountParams);
            
            l_cancelOrderServiceImpl.validateCancelOrder(l_cancelConfirmRequest);
            fail();
        }
        catch (WEB3BaseException e)
        {
            log.exiting(TEST_END + STR_METHOD_NAME);
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80005, e.getErrorInfo());
        }
        catch (Exception e)
        {
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    /**
     * validate注文取消
     * validatePTS取消注文()
     * WEB3ErrorCatalog.BUSINESS_ERROR_00820
     */
    public void test_validateCancelOrder_0003()
    {
        final String STR_METHOD_NAME = "test_validateCancelOrder_0003()";
        log.entering(TEST_START + STR_METHOD_NAME);
        WEB3EquityPTSCancelOrderServiceImpl l_cancelOrderServiceImpl = new WEB3EquityPTSCancelOrderServiceImpl();
        WEB3EquityCancelConfirmRequest l_cancelConfirmRequest = new WEB3EquityCancelConfirmRequest();
        l_cancelConfirmRequest.id = "10";
        WEB3GentradeTradingClendarContext l_clendarContext = new WEB3GentradeTradingClendarContext();
        l_clendarContext.setInstitutionCode("0D");
        l_clendarContext.setBranchCode("381");
        l_clendarContext.setProductCode("0");
        l_clendarContext.setMarketCode("SP");
        l_clendarContext.setTradingTimeType("26");
        l_clendarContext.setBizDateType("0");
        ThreadLocalSystemAttributesRegistry.setAttribute(
            "web3.tradingcalendarcontext", l_clendarContext);
        try
        {
            Calendar ca =  Calendar.getInstance();
            ca.set(2007,6-1,12);// 注意月?要減1
            Date date = ca.getTime();
            Timestamp st = new Timestamp(date.getTime());
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.tc." + 
                    "gentrade.stdimpls.TradingSystemImpl", 
                "getSystemTimestamp",
                new Class[]{}, 
                st);
            
            ThreadLocalSystemAttributesRegistry.setAttribute(
                    "web3.attributes.basetimestampfororderbizdate",
                    null);
                    
            ThreadLocalSystemAttributesRegistry.setAttribute(
                    "xblocks.gtl.attributes.systemtimestamp",
                    st);
            
            MOCK_MANAGER.setIsMockUsed(true);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl", "getLoginInfo",
                    new Class[] {},
                    new LoginInfoImplForMock());
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.LoginInfoImpl",
                    "getLoginId",
                    new Class[] {},
                    new Long(123));
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl", "getAccountId",
                 new Class[] {}, new Long(333812512246L));
            
            TestDBUtility.deleteAll(TradingTimeParams.TYPE);
            TestDBUtility.deleteAll(MainAccountParams.TYPE);
            TestDBUtility.deleteAll(SubAccountParams.TYPE);
            TestDBUtility.deleteAll(BranchParams.TYPE);
            TestDBUtility.deleteAll(EqtypeOrderParams.TYPE);
            TestDBUtility.deleteAll(EqtypeOrderUnitParams.TYPE);
            TestDBUtility.deleteAll(MarketParams.TYPE);
            
            
            MarketParams l_marketParams = TestDBUtility.getMarketRow();
            TestDBUtility.insertWithDel(l_marketParams);
            
            EqtypeOrderParams l_eqtypeOrderParams = TestDBUtility.getEqtypeOrderRow();
            l_eqtypeOrderParams.setOrderId(10L);
            TestDBUtility.insertWithDel(l_eqtypeOrderParams);
            
            EqtypeOrderUnitParams l_eqtypeOrderUnitParams = TestDBUtility.getEqtypeOrderUnitRow();
            l_eqtypeOrderUnitParams.setOrderStatus(OrderStatusEnum.NOT_CANCELLED);
            l_eqtypeOrderUnitParams.setOrderOpenStatus(OrderOpenStatusEnum.CLOSED);
            l_eqtypeOrderUnitParams.setOrderId(10L);
            l_eqtypeOrderUnitParams.setProductId(3304148080001L);
            l_eqtypeOrderUnitParams.setAccountId(333812512246L);
            l_eqtypeOrderUnitParams.setOrderType(OrderTypeEnum.EQUITY_BUY);
            l_eqtypeOrderUnitParams.setOrderCateg(OrderCategEnum.ASSET);
            l_eqtypeOrderUnitParams.setProductType(ProductTypeEnum.EQUITY);
            TestDBUtility.insertWithDel(l_eqtypeOrderUnitParams);
            
            
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setBranchId(33381);
            TestDBUtility.insertWithDel(l_branchParams);
            
            TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams.setBizDateType("1");
            TestDBUtility.insertWithDel(l_tradingTimeParams);
            
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(333812512246L);
            l_mainAccountParams.setBranchId(33381L);
            TestDBUtility.insertWithDel(l_mainAccountParams);
            
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setCashBalance(12.0);
            l_subAccountParams.setAccountId(333812512246L);
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
            TestDBUtility.insertWithDel(l_subAccountParams);
            
            l_cancelOrderServiceImpl.validateCancelOrder(l_cancelConfirmRequest);
            fail();
        }
        catch (WEB3BaseException e)
        {
            log.exiting(TEST_END + STR_METHOD_NAME);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00820, e.getErrorInfo());
        }
        catch (Exception e)
        {
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    /**
     * validate注文取消
     * 分岐フロー：　@売り注文の場合のみ実行
     * calc概算簿価単価
     * 逆指値
     */
    public void test_validateCancelOrder_0004()
    {
        final String STR_METHOD_NAME = "test_validateCancelOrder_0004()";
        log.entering(TEST_START + STR_METHOD_NAME);
        WEB3EquityPTSCancelOrderServiceImpl l_cancelOrderServiceImpl = new WEB3EquityPTSCancelOrderServiceImpl();
        WEB3EquityCancelConfirmRequest l_cancelConfirmRequest = new WEB3EquityCancelConfirmRequest();
        l_cancelConfirmRequest.id = "10";
        WEB3GentradeTradingClendarContext l_clendarContext = new WEB3GentradeTradingClendarContext();
        l_clendarContext.setInstitutionCode("0D");
        l_clendarContext.setBranchCode("381");
        l_clendarContext.setProductCode("0");
        l_clendarContext.setMarketCode("SP");
        l_clendarContext.setTradingTimeType("26");
        l_clendarContext.setBizDateType("0");
        ThreadLocalSystemAttributesRegistry.setAttribute(
            "web3.tradingcalendarcontext", l_clendarContext);
        try
        {
            Calendar ca =  Calendar.getInstance();
            ca.set(2007,6-1,12);// 注意月?要減1
            Date date = ca.getTime();
            Timestamp st = new Timestamp(date.getTime());
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.tc." + 
                    "gentrade.stdimpls.TradingSystemImpl", 
                "getSystemTimestamp",
                new Class[]{}, 
                st);
            
            ThreadLocalSystemAttributesRegistry.setAttribute(
                    "web3.attributes.basetimestampfororderbizdate",
                    null);
                    
            ThreadLocalSystemAttributesRegistry.setAttribute(
                    "xblocks.gtl.attributes.systemtimestamp",
                    st);
            
            WEB3EquityProductQuote l_productQuote = new WEB3EquityProductQuote();
            //時価
            l_productQuote.setQuote(1.0D);
            //前日比
            l_productQuote.setComparedPreviousDay(87.0D);
            //時価発表時間
            l_productQuote.setQuoteTime(new Timestamp(Calendar.getInstance().getTime().getTime()));
            //時価取得区分
            l_productQuote.setQuoteFromDiv("2");
            //時価区分
            l_productQuote.setQuoteTypeDiv("3");
            //市場コード
            l_productQuote.setMarketCode("4");
            //現在値
            l_productQuote.setBoardCurrentPrice("5");
            //現在値時刻
            l_productQuote.setBoardCurrentPriceTime(new Timestamp(Calendar.getInstance().getTime().getTime()));
            //現在値区分
            l_productQuote.setBoardCurrentPriceDiv("6");
            //現在値前日比
            l_productQuote.setBoardChange("7");
            //出来高
            l_productQuote.setVolume("8");
            //出来高時刻
            l_productQuote.setVolumeTime(new Timestamp(Calendar.getInstance().getTime().getTime()));
            //買気配値タイトル区分
            l_productQuote.setAskPriceTitle("9");
            //買気配値
            l_productQuote.setAskPrice("10");
            //買気配値時刻
            l_productQuote.setAskPriceTime(new Timestamp(Calendar.getInstance().getTime().getTime()));
            //売気配値タイトル区分
            l_productQuote.setBidPriceTitle("11");
            //売気配値
            l_productQuote.setBidPrice("12");
            //売気配値時刻
            l_productQuote.setBidPriceTime(new Timestamp(Calendar.getInstance().getTime().getTime()));
            //基準値段
            l_productQuote.setBasePrice("13");
            
            MOCK_MANAGER.setIsMockUsed(true);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getLoginInfo",
                new Class[] {},
                new LoginInfoImplForMock());
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.LoginInfoImpl",
                "getLoginId",
                new Class[] {},
                new Long(1));
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getAccountId",
                 new Class[] {}, new Long(333812512246L));
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getSessionProperty",
                new Class[] {String.class},
                "2");
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.equity.WEB3EquityProductManager",
                "getDisplayEquityProductQuote",
                new Class[]{EqTypeTradedProduct.class, WEB3GentradeSubAccount.class},
                l_productQuote);
            
            TestDBUtility.deleteAll(TradingTimeParams.TYPE);
            TestDBUtility.deleteAll(MainAccountParams.TYPE);
            TestDBUtility.deleteAll(SubAccountParams.TYPE);
            TestDBUtility.deleteAll(EqtypeOrderParams.TYPE);
            TestDBUtility.deleteAll(EqtypeOrderUnitParams.TYPE);
            TestDBUtility.deleteAll(TradedProductParams.TYPE);
            TestDBUtility.deleteAll(ProductParams.TYPE);
            TestDBUtility.deleteAll(EqtypeTradedProductParams.TYPE);
            TestDBUtility.deleteAll(EqtypeProductParams.TYPE);
            TestDBUtility.deleteAll(BranchParams.TYPE);
            TestDBUtility.deleteAll(BranchMarketPtsDealtCondParams.TYPE);
            TestDBUtility.deleteAll(AssetParams.TYPE);
            TestDBUtility.deleteAll(MarketParams.TYPE);
            
            
            MarketParams l_marketParams = TestDBUtility.getMarketRow();
            TestDBUtility.insertWithDel(l_marketParams);
            
            
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setBranchId(33381);
            TestDBUtility.insertWithDel(l_branchParams);
            
            TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams.setBizDateType("1");
            l_tradingTimeParams.setBizdateCalcParameter("0");
            TestDBUtility.insertWithDel(l_tradingTimeParams);
            
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(333812512246L);
            l_mainAccountParams.setBranchId(33381L);
            TestDBUtility.insertWithDel(l_mainAccountParams);
            
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setCashBalance(12.0);
            l_subAccountParams.setAccountId(333812512246L);
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
            TestDBUtility.insertWithDel(l_subAccountParams);
            
            EqtypeOrderParams l_eqtypeOrderParams = TestDBUtility.getEqtypeOrderRow();
            l_eqtypeOrderParams.setOrderId(10L);
            TestDBUtility.insertWithDel(l_eqtypeOrderParams);
            
            EqtypeOrderUnitParams l_eqtypeOrderUnitParams = TestDBUtility.getEqtypeOrderUnitRow();
            l_eqtypeOrderUnitParams.setOrderStatus(OrderStatusEnum.NOT_CANCELLED);
            l_eqtypeOrderUnitParams.setOrderOpenStatus(OrderOpenStatusEnum.OPEN);
            l_eqtypeOrderUnitParams.setOrderId(10L);
            l_eqtypeOrderUnitParams.setProductId(3304148080001L);
            l_eqtypeOrderUnitParams.setAccountId(333812512246L);
            l_eqtypeOrderUnitParams.setOrderCateg(OrderCategEnum.ASSET);
            l_eqtypeOrderUnitParams.setProductType(ProductTypeEnum.EQUITY);
            l_eqtypeOrderUnitParams.setExecutionConditionType(EqTypeExecutionConditionType.NONE);
            //売り注文の場合
            l_eqtypeOrderUnitParams.setOrderType(OrderTypeEnum.EQUITY_SELL);
            //概算受渡代金
            l_eqtypeOrderUnitParams.setEstimatedPrice(0.0D);
            //注文数量
            l_eqtypeOrderUnitParams.setQuantity(23.0D);
            //約定数量
            l_eqtypeOrderUnitParams.setExecutedQuantity(12.0D);
            //値段条件
            l_eqtypeOrderUnitParams.setPriceConditionType(WEB3PriceConditionDef.PRESENT_VALUE_LIMIT_PRICE_ORDER);
            //発注条件
            l_eqtypeOrderUnitParams.setOrderConditionType(WEB3OrderingConditionDef.STOP_LIMIT_PRICE);
            //逆指値
            l_eqtypeOrderUnitParams.setStopOrderPrice(80.0D);
            //発注条件演算子
            l_eqtypeOrderUnitParams.setOrderCondOperator(WEB3OrderConditionOperatorDef.ABOVE_BASE_PRICE);
            //元発注条件
            l_eqtypeOrderUnitParams.setOrgOrderConditionType("1");
            //元逆指値基準値
            l_eqtypeOrderUnitParams.setOrgStopOrderPrice(123.0D);
            //元発注条件演算子
            l_eqtypeOrderUnitParams.setOrgOrderCondOperator("1");
            l_eqtypeOrderUnitParams.setConfirmedQuantity(231D);
            TestDBUtility.insertWithDel(l_eqtypeOrderUnitParams);
            
            ProductParams l_productParams = TestDBUtility.getProductRow();
            l_productParams.setProductType(ProductTypeEnum.EQUITY);
            l_productParams.setProductId(3304148080001L);
            l_productParams.setStandardName("シンセンテルス");
            TestDBUtility.insertWithDel(l_productParams);
            
            EqtypeProductParams l_eqtypeProductParams = TestDBUtility.getEqtypeProductRow();
            l_eqtypeProductParams.setProductId(3304148080001L);
            l_eqtypeProductParams.setStandardName("シンセンテルス");
            TestDBUtility.insertWithDel(l_eqtypeProductParams);
            
            TradedProductParams l_tradedProductParams = TestDBUtility.getTradedProductRow();
            l_tradedProductParams.setProductId(3304148080001L);
            l_tradedProductParams.setProductType(ProductTypeEnum.EQUITY);
            TestDBUtility.insertWithDel(l_tradedProductParams);
            
            EqtypeTradedProductParams l_eqtypeTradedProductParams = TestDBUtility.getEqtypeTradedProductRow();
            l_eqtypeTradedProductParams.setProductId(3304148080001L);
            TestDBUtility.insertWithDel(l_eqtypeTradedProductParams);
            
            BranchMarketPtsDealtCondParams l_branchMarketPTSDealtCondParams = new BranchMarketPtsDealtCondParams();
            l_branchMarketPTSDealtCondParams.setInstitutionCode("0D");
            l_branchMarketPTSDealtCondParams.setBranchCode("381");
            l_branchMarketPTSDealtCondParams.setMarketCode("SP");
            l_branchMarketPTSDealtCondParams.setMartCanDealtEquity("1");
            TestDBUtility.insertWithDel(l_branchMarketPTSDealtCondParams);
            
            AssetParams l_assetParams = TestDBUtility.getAssetRow();
            l_assetParams.setAccountId(333812512246L);
            l_assetParams.setSubAccountId(33381251220301L);
            l_assetParams.setProductId(3304148080001L);
            l_assetParams.setTaxType(TaxTypeEnum.SPECIAL);
            l_assetParams.setMiniStockDiv("0");
            TestDBUtility.insertWithDel(l_assetParams);
            
            WEB3EquityCancelConfirmResponse l_confirmResponse = l_cancelOrderServiceImpl.validateCancelOrder(l_cancelConfirmRequest);
            
            WEB3MockObjectParamsValue l_paramsValue = TestBaseForMock.MOCK_MANAGER.getMethodParamsValue(
                    "webbroker3.equity.WEB3EquityProductManager",
                    "getDisplayEquityProductQuote",
                    new Class[]{EqTypeTradedProduct.class, WEB3GentradeSubAccount.class});
                    
            assertEquals(WEB3EquityTradedProductForMock.class,((EqTypeTradedProduct)l_paramsValue.getFirstCalled()[0]).getClass());
            assertEquals(WEB3GentradeSubAccount.class,((WEB3GentradeSubAccount)l_paramsValue.getFirstCalled()[1]).getClass());
                
            //確認時発注日
            assertEquals(WEB3DateUtility.formatDate(l_confirmResponse.checkDate, "yyyyMMdd"),
                    "20070612");
            //概算受渡代金
            assertEquals("0", "" + l_confirmResponse.estimatedPrice);
            //取引終了警告市場コード一覧
            assertEquals("0", "" + l_confirmResponse.messageSuspension.length);
            //銘柄コード
            assertEquals("N8080", l_confirmResponse.productCode);
            //銘柄名
            assertEquals("シンセンテルス", l_confirmResponse.productName);
            //市場コード
            assertEquals("SP", l_confirmResponse.marketCode);
            //口座区分
            assertEquals("1", l_confirmResponse.taxType);
            //取引区分
            //注文単位.注文種別＝”2：現物売注文”の場合は、"2：現物売付注文"をセット。
            assertEquals("2", l_confirmResponse.tradingType);
            //注文株数
            assertEquals("23", l_confirmResponse.orderQuantity);
            //内出来株数
            assertEquals("12", l_confirmResponse.partContQuantity);
            //注文単価区分
            assertEquals("0", l_confirmResponse.orderPriceDiv);
            //注文単価
            //※注文単位.isMarketOrder( )＝trueの場合は、nullをセット。
            assertNull(l_confirmResponse.limitPrice);
            //概算簿価単価
            //注文単位.注文種別＝"2：現物売注文"の場合、株式計算サービス.calc概算簿価単価( )
            assertEquals("1", l_confirmResponse.estimatedBookPrice);
            //値段条件
            assertEquals("1", l_confirmResponse.priceCondType);
            //執行条件
            assertEquals("1", l_confirmResponse.execCondType);
            //注文期限区分
            //PTS注文マネージャ.is出来るまで注文単位(注文単位)＝falseの場合、"1：当日限り"をセット。
            assertEquals("1", l_confirmResponse.expirationDateType);
            //注文有効期限
            assertNull(l_confirmResponse.expirationDate);
            //発注条件区分
            assertEquals("1", l_confirmResponse.orderCondType);
            //逆指値用発注条件単価
            //注文単位.発注条件＝1（逆指値）の場合、注文単位.逆指値
            assertEquals("80", l_confirmResponse.stopOrderCondPrice);
            //逆指値用発注条件演算子
            //注文単位.発注条件＝1（逆指値）の場合、注文単位.発注条件演算子
            assertEquals("1", l_confirmResponse.stopOrderCondOperator);
            //W指値用発注条件単価
            assertNull(l_confirmResponse.wlimitOrderCondPrice);
            //W指値用発注条件演算子
            assertNull(l_confirmResponse.wlimitOrderCondOperator);
            //W指値用注文単価区分
            assertNull(l_confirmResponse.wLimitOrderPriceDiv);
            //W指値用注文単価
            assertNull(l_confirmResponse.wLimitPrice);
            //W指値用執行条件
            assertNull(l_confirmResponse.wlimitExecCondType);
            //W指値用有効状態区分
            assertNull(l_confirmResponse.wlimitEnableStatusDiv);
            //W指値用切替前注文単価
            assertNull(l_confirmResponse.wlimitBefChgLimitPrice);
            //W指値用切替前執行条件
            assertNull(l_confirmResponse.wlimitBefChgExecCondType);
            //元発注条件区分
            assertEquals("1", l_confirmResponse.orgOrderCondType);
            //元発注条件単価
            assertEquals("123", l_confirmResponse.orgOrderCondPrice);
            //元発注条件演算子
            assertEquals("1", l_confirmResponse.orgOrderCondOperator);
            //元W指値用注文単価区分
            assertNull(l_confirmResponse.orgWlimitOrderPriceDiv);
            //元W指値用注文単価
            assertNull(l_confirmResponse.orgWlimitPrice);
            //元W指値用執行条件
            assertNull(l_confirmResponse.orgWlimitExecCondType);
            //時価区分
            assertEquals("3", l_confirmResponse.currentPriceDiv);
            //時価（現在値）
            assertEquals("1", "" + l_confirmResponse.currentPrice);
            //前日比
            assertEquals("87", "" + l_confirmResponse.comparedPreviousDay);
            //取引時間(時価発表時間)
            assertEquals(WEB3DateUtility.formatDate(Calendar.getInstance().getTime(), "yyyyMMdd"),
                    WEB3DateUtility.formatDate(l_confirmResponse.currentPriceTime, "yyyyMMdd"));
            //現在値：　@取得した株式銘柄時価情報.get現在値( )
            assertEquals("5", "" + l_confirmResponse.boardCurrentPrice);
            //現在値時刻：　@取得した株式銘柄時価情報.get現在値時刻( )
            assertEquals(WEB3DateUtility.formatDate(Calendar.getInstance().getTime(), "yyyyMMdd"),
                    WEB3DateUtility.formatDate(l_confirmResponse.boardCurrentPriceTime, "yyyyMMdd"));
            //現在値区分：　@取得した株式銘柄時価情報.get現在値区分( )
            assertEquals("6", "" + l_confirmResponse.boardCurrentPriceDiv);
            //現在値前日比：　@取得した株式銘柄時価情報.get現在値前日比( )
            assertEquals("7", "" + l_confirmResponse.boardChange);
            //出来高：　@取得した株式銘柄時価情報.get出来高( )
            assertEquals("8", "" + l_confirmResponse.volume);
            //出来高時刻：　@取得した株式銘柄時価情報.get出来高時刻( )
            assertEquals(WEB3DateUtility.formatDate(Calendar.getInstance().getTime(), "yyyyMMdd"),
                    WEB3DateUtility.formatDate(l_confirmResponse.volumeTime, "yyyyMMdd"));
            //買気配値タイトル区分：　@取得した株式銘柄時価情報.get買気配値タイトル区分( )
            assertEquals("9", "" + l_confirmResponse.askPriceTitle);
            //買気配値：　@取得した株式銘柄時価情報.get買気配値( )
            assertEquals("10", "" + l_confirmResponse.askPrice);
            //買気配値時刻：　@取得した株式銘柄時価情報.get買気配値時刻( )
            assertEquals(WEB3DateUtility.formatDate(Calendar.getInstance().getTime(), "yyyyMMdd"),
                    WEB3DateUtility.formatDate(l_confirmResponse.askPriceTime, "yyyyMMdd"));
            //売気配値タイトル区分：　@取得した株式銘柄時価情報.get売気配値タイトル区分( )
            assertEquals("11", "" + l_confirmResponse.bidPriceTitle);
            //売気配値：　@取得した株式銘柄時価情報.get売気配値( )
            assertEquals("12", "" + l_confirmResponse.bidPrice);
            //売気配値時刻：　@取得した株式銘柄時価情報.get売気配値時刻( )
            assertEquals(WEB3DateUtility.formatDate(Calendar.getInstance().getTime(), "yyyyMMdd"),
                    WEB3DateUtility.formatDate(l_confirmResponse.bidPriceTime, "yyyyMMdd"));
            //基準値段：　@取得した株式銘柄時価情報.get基準値段( )
            assertEquals("13", "" + l_confirmResponse.basePrice);
            
        }
        catch (WEB3BaseException e)
        {
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        catch (Exception e)
        {
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    /**
     * validate注文取消
     * 買注文の場合のみ実行
     * W指値
     */
    public void test_validateCancelOrder_0005()
    {
        final String STR_METHOD_NAME = "test_validateCancelOrder_0005()";
        log.entering(TEST_START + STR_METHOD_NAME);
        WEB3EquityPTSCancelOrderServiceImpl l_cancelOrderServiceImpl = new WEB3EquityPTSCancelOrderServiceImpl();
        WEB3EquityCancelConfirmRequest l_cancelConfirmRequest = new WEB3EquityCancelConfirmRequest();
        l_cancelConfirmRequest.id = "10";
        WEB3GentradeTradingClendarContext l_clendarContext = new WEB3GentradeTradingClendarContext();
        l_clendarContext.setInstitutionCode("0D");
        l_clendarContext.setBranchCode("381");
        l_clendarContext.setProductCode("0");
        l_clendarContext.setMarketCode("SP");
        l_clendarContext.setTradingTimeType("26");
        l_clendarContext.setBizDateType("0");
        ThreadLocalSystemAttributesRegistry.setAttribute(
            "web3.tradingcalendarcontext", l_clendarContext);
        try
        {
            Calendar ca =  Calendar.getInstance();
            ca.set(2007,6-1,12);// 注意月?要減1
            Date date = ca.getTime();
            Timestamp st = new Timestamp(date.getTime());
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.tc." + 
                    "gentrade.stdimpls.TradingSystemImpl", 
                "getSystemTimestamp",
                new Class[]{}, 
                st);
            
            ThreadLocalSystemAttributesRegistry.setAttribute(
                    "web3.attributes.basetimestampfororderbizdate",
                    null);
                    
            ThreadLocalSystemAttributesRegistry.setAttribute(
                    "xblocks.gtl.attributes.systemtimestamp",
                    st);
            
            WEB3EquityProductQuote l_productQuote = new WEB3EquityProductQuote();
            //時価
            l_productQuote.setQuote(1.0D);
            //前日比
            l_productQuote.setComparedPreviousDay(87.0D);
            //時価発表時間
            l_productQuote.setQuoteTime(new Timestamp(Calendar.getInstance().getTime().getTime()));
            //時価取得区分
            l_productQuote.setQuoteFromDiv("2");
            //時価区分
            l_productQuote.setQuoteTypeDiv("3");
            //市場コード
            l_productQuote.setMarketCode("4");
            //現在値
            l_productQuote.setBoardCurrentPrice("5");
            //現在値時刻
            l_productQuote.setBoardCurrentPriceTime(new Timestamp(Calendar.getInstance().getTime().getTime()));
            //現在値区分
            l_productQuote.setBoardCurrentPriceDiv("6");
            //現在値前日比
            l_productQuote.setBoardChange("7");
            //出来高
            l_productQuote.setVolume("8");
            //出来高時刻
            l_productQuote.setVolumeTime(new Timestamp(Calendar.getInstance().getTime().getTime()));
            //買気配値タイトル区分
            l_productQuote.setAskPriceTitle("9");
            //買気配値
            l_productQuote.setAskPrice("10");
            //買気配値時刻
            l_productQuote.setAskPriceTime(new Timestamp(Calendar.getInstance().getTime().getTime()));
            //売気配値タイトル区分
            l_productQuote.setBidPriceTitle("11");
            //売気配値
            l_productQuote.setBidPrice("12");
            //売気配値時刻
            l_productQuote.setBidPriceTime(new Timestamp(Calendar.getInstance().getTime().getTime()));
            //基準値段
            l_productQuote.setBasePrice("13");
            
            MOCK_MANAGER.setIsMockUsed(true);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getLoginInfo",
                new Class[] {},
                new LoginInfoImplForMock());
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.LoginInfoImpl",
                "getLoginId",
                new Class[] {},
                new Long(1));
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getAccountId",
                 new Class[] {}, new Long(333812512246L));
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getSessionProperty",
                new Class[] {String.class},
                "2");
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.equity.WEB3EquityProductManager",
                "getDisplayEquityProductQuote",
                new Class[]{EqTypeTradedProduct.class, WEB3GentradeSubAccount.class},
                l_productQuote);
            
            TestDBUtility.deleteAll(TradingTimeParams.TYPE);
            TestDBUtility.deleteAll(MainAccountParams.TYPE);
            TestDBUtility.deleteAll(SubAccountParams.TYPE);
            TestDBUtility.deleteAll(EqtypeOrderParams.TYPE);
            TestDBUtility.deleteAll(EqtypeOrderUnitParams.TYPE);
            TestDBUtility.deleteAll(TradedProductParams.TYPE);
            TestDBUtility.deleteAll(ProductParams.TYPE);
            TestDBUtility.deleteAll(EqtypeTradedProductParams.TYPE);
            TestDBUtility.deleteAll(EqtypeProductParams.TYPE);
            TestDBUtility.deleteAll(BranchParams.TYPE);
            TestDBUtility.deleteAll(BranchMarketPtsDealtCondParams.TYPE);
            TestDBUtility.deleteAll(AssetParams.TYPE);
            TestDBUtility.deleteAll(Web3QuoteProtoParams.TYPE);
            TestDBUtility.deleteAll(MarketParams.TYPE);
            
            
            MarketParams l_marketParams = TestDBUtility.getMarketRow();
            TestDBUtility.insertWithDel(l_marketParams);
            
            
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setBranchId(33381);
            TestDBUtility.insertWithDel(l_branchParams);
            
            TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams.setBizDateType("1");
            l_tradingTimeParams.setBizdateCalcParameter("0");
            TestDBUtility.insertWithDel(l_tradingTimeParams);
            
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(333812512246L);
            l_mainAccountParams.setBranchId(33381L);
            TestDBUtility.insertWithDel(l_mainAccountParams);
            
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setCashBalance(12.0);
            l_subAccountParams.setAccountId(333812512246L);
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
            TestDBUtility.insertWithDel(l_subAccountParams);
            
            EqtypeOrderParams l_eqtypeOrderParams = TestDBUtility.getEqtypeOrderRow();
            l_eqtypeOrderParams.setOrderId(10L);
            TestDBUtility.insertWithDel(l_eqtypeOrderParams);
            
            EqtypeOrderUnitParams l_eqtypeOrderUnitParams = TestDBUtility.getEqtypeOrderUnitRow();
            l_eqtypeOrderUnitParams.setOrderStatus(OrderStatusEnum.NOT_CANCELLED);
            l_eqtypeOrderUnitParams.setOrderOpenStatus(OrderOpenStatusEnum.OPEN);
            l_eqtypeOrderUnitParams.setOrderId(10L);
            l_eqtypeOrderUnitParams.setProductId(3304148080001L);
            l_eqtypeOrderUnitParams.setAccountId(333812512246L);
            l_eqtypeOrderUnitParams.setOrderCateg(OrderCategEnum.ASSET);
            l_eqtypeOrderUnitParams.setProductType(ProductTypeEnum.EQUITY);
            l_eqtypeOrderUnitParams.setExecutionConditionType(EqTypeExecutionConditionType.NONE);
            //買注文の場合
            l_eqtypeOrderUnitParams.setOrderType(OrderTypeEnum.EQUITY_BUY);
            //概算受渡代金
            l_eqtypeOrderUnitParams.setEstimatedPrice(0.0D);
            //注文数量
            l_eqtypeOrderUnitParams.setQuantity(23.0D);
            //約定数量
            l_eqtypeOrderUnitParams.setExecutedQuantity(12.0D);
            //値段条件
            l_eqtypeOrderUnitParams.setPriceConditionType(WEB3PriceConditionDef.PRESENT_VALUE_LIMIT_PRICE_ORDER);
            //発注条件
            l_eqtypeOrderUnitParams.setOrderConditionType(WEB3OrderingConditionDef.W_LIMIT_PRICE);
            //逆指値
            l_eqtypeOrderUnitParams.setStopOrderPrice(80.0D);
            //発注条件演算子
            l_eqtypeOrderUnitParams.setOrderCondOperator(WEB3OrderConditionOperatorDef.ABOVE_BASE_PRICE);
            //元発注条件
            l_eqtypeOrderUnitParams.setOrgOrderConditionType("2");
//            //元逆指値基準値
//            l_eqtypeOrderUnitParams.setOrgStopOrderPrice(123.0D);
            //元発注条件演算子
            l_eqtypeOrderUnitParams.setOrgOrderCondOperator("1");
            //（W指値）訂正指値
            l_eqtypeOrderUnitParams.setWLimitPrice(21.3D);
            //指値
            l_eqtypeOrderUnitParams.setLimitPrice(7.90D);
            //初回注文の注文単位ＩＤ
            l_eqtypeOrderUnitParams.setFirstOrderUnitId(5);
            //注文失効日付
            l_eqtypeOrderUnitParams.setExpirationDate(Calendar.getInstance().getTime());
            //元（W指値）執行条件
            l_eqtypeOrderUnitParams.setWLimitExecCondType(EqTypeExecutionConditionType.AT_MARKET_CLOSE);
            //リクエストタイプ
            l_eqtypeOrderUnitParams.setRequestType("2");
            //
            l_eqtypeOrderUnitParams.setWLimitBeforeExecCondType(EqTypeExecutionConditionType.NONE);
            //
            l_eqtypeOrderUnitParams.setOrgWLimitPrice(1.0D);
            //
            l_eqtypeOrderUnitParams.setOrgWLimitExecCondType(EqTypeExecutionConditionType.NONE);
            l_eqtypeOrderUnitParams.setWLimitBeforeLimitPrice(82.3D);
            l_eqtypeOrderUnitParams.setConfirmedQuantity(231D);
            TestDBUtility.insertWithDel(l_eqtypeOrderUnitParams);
            
            ProductParams l_productParams = TestDBUtility.getProductRow();
            l_productParams.setProductType(ProductTypeEnum.EQUITY);
            l_productParams.setProductId(3304148080001L);
            l_productParams.setStandardName("シンセンテルス");
            TestDBUtility.insertWithDel(l_productParams);
            
            EqtypeProductParams l_eqtypeProductParams = TestDBUtility.getEqtypeProductRow();
            l_eqtypeProductParams.setProductId(3304148080001L);
            l_eqtypeProductParams.setStandardName("シンセンテルス");
            TestDBUtility.insertWithDel(l_eqtypeProductParams);
            
            TradedProductParams l_tradedProductParams = TestDBUtility.getTradedProductRow();
            l_tradedProductParams.setProductId(3304148080001L);
            l_tradedProductParams.setProductType(ProductTypeEnum.EQUITY);
            TestDBUtility.insertWithDel(l_tradedProductParams);
            
            EqtypeTradedProductParams l_eqtypeTradedProductParams = TestDBUtility.getEqtypeTradedProductRow();
            l_eqtypeTradedProductParams.setProductId(3304148080001L);
            TestDBUtility.insertWithDel(l_eqtypeTradedProductParams);
            
            BranchMarketPtsDealtCondParams l_branchMarketPTSDealtCondParams = new BranchMarketPtsDealtCondParams();
            l_branchMarketPTSDealtCondParams.setInstitutionCode("0D");
            l_branchMarketPTSDealtCondParams.setBranchCode("381");
            l_branchMarketPTSDealtCondParams.setMarketCode("SP");
            l_branchMarketPTSDealtCondParams.setMartCanDealtEquity("1");
            TestDBUtility.insertWithDel(l_branchMarketPTSDealtCondParams);
            
            AssetParams l_assetParams = TestDBUtility.getAssetRow();
            l_assetParams.setAccountId(333812512246L);
            l_assetParams.setSubAccountId(33381251220301L);
            l_assetParams.setProductId(3304148080001L);
            l_assetParams.setTaxType(TaxTypeEnum.SPECIAL);
            l_assetParams.setMiniStockDiv("0");
            TestDBUtility.insertWithDel(l_assetParams);
            
            WEB3EquityCancelConfirmResponse l_confirmResponse = l_cancelOrderServiceImpl.validateCancelOrder(l_cancelConfirmRequest);
            
            //確認時発注日
            assertEquals(WEB3DateUtility.formatDate(l_confirmResponse.checkDate, "yyyyMMdd"),
                    "20070612");
            //概算受渡代金
            assertEquals("0", "" + l_confirmResponse.estimatedPrice);
            //取引終了警告市場コード一覧
            assertEquals("0", "" + l_confirmResponse.messageSuspension.length);
            //銘柄コード
            assertEquals("N8080", l_confirmResponse.productCode);
            //銘柄名
            assertEquals("シンセンテルス", l_confirmResponse.productName);
            //市場コード
            assertEquals("SP", l_confirmResponse.marketCode);
            //口座区分
            assertEquals("1", l_confirmResponse.taxType);
            //取引区分
            //注文単位.注文種別＝”1：現物買注文”の場合は、"1：現物買付注文"をセット。
            assertEquals("1", l_confirmResponse.tradingType);
            //注文株数
            assertEquals("23", l_confirmResponse.orderQuantity);
            //内出来株数
            assertEquals("12", l_confirmResponse.partContQuantity);
            //注文単価区分
            assertEquals("1", l_confirmResponse.orderPriceDiv);
            //注文単価
            //※注文単位.isMarketOrder( )＝trueの場合は、nullをセット。
            assertEquals("7.9", "" + l_confirmResponse.limitPrice);
            //概算簿価単価
            //注文単位.注文種別＝"2：現物売注文"の場合、株式計算サービス.calc概算簿価単価( )
            assertNull(l_confirmResponse.estimatedBookPrice);
            //値段条件
            assertEquals("1", l_confirmResponse.priceCondType);
            //執行条件
            assertEquals("1", l_confirmResponse.execCondType);
            //注文期限区分
            //PTS注文マネージャ.is出来るまで注文単位(注文単位)＝trueの場合、"2：出来るまで注文"をセット。
            assertEquals("2", l_confirmResponse.expirationDateType);
            //注文有効期限
            assertEquals(WEB3DateUtility.formatDate(l_confirmResponse.expirationDate, "yyyyMMdd"),
               WEB3DateUtility.formatDate(Calendar.getInstance().getTime(), "yyyyMMdd"));
            //発注条件区分
            assertEquals("2", l_confirmResponse.orderCondType);
            //逆指値用発注条件単価
            //注文単位.発注条件＝1（逆指値）の場合、注文単位.逆指値
            assertNull(l_confirmResponse.stopOrderCondPrice);
            //逆指値用発注条件演算子
            //注文単位.発注条件＝1（逆指値）の場合、注文単位.発注条件演算子
            assertNull(l_confirmResponse.stopOrderCondOperator);
            //W指値用発注条件単価
            //注文単位.発注条件＝2（W指値）の場合、注文単位.逆指値
            assertEquals("80", l_confirmResponse.wlimitOrderCondPrice);
            //W指値用発注条件演算子
            //注文単位.発注条件＝2（W指値）の場合、注文単位.発注条件演算子
            assertEquals("1", l_confirmResponse.wlimitOrderCondOperator);
            //W指値用注文単価区分
            assertEquals("1", l_confirmResponse.wLimitOrderPriceDiv);
            //W指値用注文単価
            assertEquals("21.3", l_confirmResponse.wLimitPrice);
            //W指値用執行条件
            assertEquals("4", l_confirmResponse.wlimitExecCondType);
            //W指値用有効状態区分
            assertEquals("1", l_confirmResponse.wlimitEnableStatusDiv);
            //W指値用切替前注文単価
            assertEquals("82.3", l_confirmResponse.wlimitBefChgLimitPrice);
            //W指値用切替前執行条件
            assertEquals("1", l_confirmResponse.wlimitBefChgExecCondType);
            //元発注条件区分
            assertEquals("2", l_confirmResponse.orgOrderCondType);
            //元発注条件単価
            assertNull(l_confirmResponse.orgOrderCondPrice);
            //元発注条件演算子
            assertEquals("1", l_confirmResponse.orgOrderCondOperator);
            //元W指値用注文単価区分
            assertEquals("1", l_confirmResponse.orgWlimitOrderPriceDiv);
            //元W指値用注文単価
            assertEquals("1", l_confirmResponse.orgWlimitPrice);
            //元W指値用執行条件
            assertEquals("1", l_confirmResponse.orgWlimitExecCondType);
            //時価区分
            assertEquals("3", l_confirmResponse.currentPriceDiv);
            //時価（現在値）
            assertEquals("1", "" + l_confirmResponse.currentPrice);
            //前日比
            assertEquals("87", "" + l_confirmResponse.comparedPreviousDay);
            //取引時間(時価発表時間)
            assertEquals(WEB3DateUtility.formatDate(Calendar.getInstance().getTime(), "yyyyMMdd"),
                    WEB3DateUtility.formatDate(l_confirmResponse.currentPriceTime, "yyyyMMdd"));
            //現在値：　@取得した株式銘柄時価情報.get現在値( )
            assertEquals("5", "" + l_confirmResponse.boardCurrentPrice);
            //現在値時刻：　@取得した株式銘柄時価情報.get現在値時刻( )
            assertEquals(WEB3DateUtility.formatDate(Calendar.getInstance().getTime(), "yyyyMMdd"),
                    WEB3DateUtility.formatDate(l_confirmResponse.boardCurrentPriceTime, "yyyyMMdd"));
            //現在値区分：　@取得した株式銘柄時価情報.get現在値区分( )
            assertEquals("6", "" + l_confirmResponse.boardCurrentPriceDiv);
            //現在値前日比：　@取得した株式銘柄時価情報.get現在値前日比( )
            assertEquals("7", "" + l_confirmResponse.boardChange);
            //出来高：　@取得した株式銘柄時価情報.get出来高( )
            assertEquals("8", "" + l_confirmResponse.volume);
            //出来高時刻：　@取得した株式銘柄時価情報.get出来高時刻( )
            assertEquals(WEB3DateUtility.formatDate(Calendar.getInstance().getTime(), "yyyyMMdd"),
                    WEB3DateUtility.formatDate(l_confirmResponse.volumeTime, "yyyyMMdd"));
            //買気配値タイトル区分：　@取得した株式銘柄時価情報.get買気配値タイトル区分( )
            assertEquals("9", "" + l_confirmResponse.askPriceTitle);
            //買気配値：　@取得した株式銘柄時価情報.get買気配値( )
            assertEquals("10", "" + l_confirmResponse.askPrice);
            //買気配値時刻：　@取得した株式銘柄時価情報.get買気配値時刻( )
            assertEquals(WEB3DateUtility.formatDate(Calendar.getInstance().getTime(), "yyyyMMdd"),
                    WEB3DateUtility.formatDate(l_confirmResponse.askPriceTime, "yyyyMMdd"));
            //売気配値タイトル区分：　@取得した株式銘柄時価情報.get売気配値タイトル区分( )
            assertEquals("11", "" + l_confirmResponse.bidPriceTitle);
            //売気配値：　@取得した株式銘柄時価情報.get売気配値( )
            assertEquals("12", "" + l_confirmResponse.bidPrice);
            //売気配値時刻：　@取得した株式銘柄時価情報.get売気配値時刻( )
            assertEquals(WEB3DateUtility.formatDate(Calendar.getInstance().getTime(), "yyyyMMdd"),
                    WEB3DateUtility.formatDate(l_confirmResponse.bidPriceTime, "yyyyMMdd"));
            //基準値段：　@取得した株式銘柄時価情報.get基準値段( )
            assertEquals("13", "" + l_confirmResponse.basePrice);
            
        }
        catch (WEB3BaseException e)
        {
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        catch (Exception e)
        {
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    
    /**
     * validate注文取消
     * 買注文の場合のみ実行
     * W指値
     */
    public void test_validateCancelOrder_0006()
    {
        final String STR_METHOD_NAME = "test_validateCancelOrder_0006()";
        log.entering(TEST_START + STR_METHOD_NAME);
        WEB3EquityPTSCancelOrderServiceImpl l_cancelOrderServiceImpl = new WEB3EquityPTSCancelOrderServiceImpl();
        WEB3EquityCancelConfirmRequest l_cancelConfirmRequest = new WEB3EquityCancelConfirmRequest();
        l_cancelConfirmRequest.id = "10";
        WEB3GentradeTradingClendarContext l_clendarContext = new WEB3GentradeTradingClendarContext();
        l_clendarContext.setInstitutionCode("0D");
        l_clendarContext.setBranchCode("381");
        l_clendarContext.setProductCode("0");
        l_clendarContext.setMarketCode("SP");
        l_clendarContext.setTradingTimeType("26");
        l_clendarContext.setBizDateType("0");
        ThreadLocalSystemAttributesRegistry.setAttribute(
            "web3.tradingcalendarcontext", l_clendarContext);
        try
        {
            Calendar ca =  Calendar.getInstance();
            ca.set(2007,6-1,12);// 注意月?要減1
            Date date = ca.getTime();
            Timestamp st = new Timestamp(date.getTime());
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.tc." + 
                    "gentrade.stdimpls.TradingSystemImpl", 
                "getSystemTimestamp",
                new Class[]{}, 
                st);
            
            ThreadLocalSystemAttributesRegistry.setAttribute(
                    "web3.attributes.basetimestampfororderbizdate",
                    null);
                    
            ThreadLocalSystemAttributesRegistry.setAttribute(
                    "xblocks.gtl.attributes.systemtimestamp",
                    st);
            
            WEB3EquityProductQuote l_productQuote = new WEB3EquityProductQuote();
            //時価
            l_productQuote.setQuote(1.0D);
            //前日比
            l_productQuote.setComparedPreviousDay(87.0D);
            //時価発表時間
            l_productQuote.setQuoteTime(new Timestamp(Calendar.getInstance().getTime().getTime()));
            //時価取得区分
            l_productQuote.setQuoteFromDiv("2");
            //時価区分
            l_productQuote.setQuoteTypeDiv("3");
            //市場コード
            l_productQuote.setMarketCode("4");
            //現在値
            l_productQuote.setBoardCurrentPrice("5");
            //現在値時刻
            l_productQuote.setBoardCurrentPriceTime(new Timestamp(Calendar.getInstance().getTime().getTime()));
            //現在値区分
            l_productQuote.setBoardCurrentPriceDiv("6");
            //現在値前日比
            l_productQuote.setBoardChange("7");
            //出来高
            l_productQuote.setVolume("8");
            //出来高時刻
            l_productQuote.setVolumeTime(new Timestamp(Calendar.getInstance().getTime().getTime()));
            //買気配値タイトル区分
            l_productQuote.setAskPriceTitle("9");
            //買気配値
            l_productQuote.setAskPrice("10");
            //買気配値時刻
            l_productQuote.setAskPriceTime(new Timestamp(Calendar.getInstance().getTime().getTime()));
            //売気配値タイトル区分
            l_productQuote.setBidPriceTitle("11");
            //売気配値
            l_productQuote.setBidPrice("12");
            //売気配値時刻
            l_productQuote.setBidPriceTime(new Timestamp(Calendar.getInstance().getTime().getTime()));
            //基準値段
            l_productQuote.setBasePrice("13");
            
            MOCK_MANAGER.setIsMockUsed(true);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getLoginInfo",
                new Class[] {},
                new LoginInfoImplForMock());
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.LoginInfoImpl",
                "getLoginId",
                new Class[] {},
                new Long(1));
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getAccountId",
                 new Class[] {}, new Long(333812512246L));
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getSessionProperty",
                new Class[] {String.class},
                "2");
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.equity.WEB3EquityProductManager",
                "getDisplayEquityProductQuote",
                new Class[]{EqTypeTradedProduct.class, WEB3GentradeSubAccount.class},
                l_productQuote);
            
            TestDBUtility.deleteAll(TradingTimeParams.TYPE);
            TestDBUtility.deleteAll(MainAccountParams.TYPE);
            TestDBUtility.deleteAll(SubAccountParams.TYPE);
            TestDBUtility.deleteAll(EqtypeOrderParams.TYPE);
            TestDBUtility.deleteAll(EqtypeOrderUnitParams.TYPE);
            TestDBUtility.deleteAll(TradedProductParams.TYPE);
            TestDBUtility.deleteAll(ProductParams.TYPE);
            TestDBUtility.deleteAll(EqtypeTradedProductParams.TYPE);
            TestDBUtility.deleteAll(EqtypeProductParams.TYPE);
            TestDBUtility.deleteAll(BranchParams.TYPE);
            TestDBUtility.deleteAll(BranchMarketPtsDealtCondParams.TYPE);
            TestDBUtility.deleteAll(AssetParams.TYPE);
            TestDBUtility.deleteAll(Web3QuoteProtoParams.TYPE);
            TestDBUtility.deleteAll(MarketParams.TYPE);
            
            
            MarketParams l_marketParams = TestDBUtility.getMarketRow();
            TestDBUtility.insertWithDel(l_marketParams);
            
            
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setBranchId(33381);
            TestDBUtility.insertWithDel(l_branchParams);
            
            TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams.setBizDateType("1");
            l_tradingTimeParams.setBizdateCalcParameter("0");
            TestDBUtility.insertWithDel(l_tradingTimeParams);
            
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(333812512246L);
            l_mainAccountParams.setBranchId(33381L);
            TestDBUtility.insertWithDel(l_mainAccountParams);
            
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setCashBalance(12.0);
            l_subAccountParams.setAccountId(333812512246L);
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
            TestDBUtility.insertWithDel(l_subAccountParams);
            
            EqtypeOrderParams l_eqtypeOrderParams = TestDBUtility.getEqtypeOrderRow();
            l_eqtypeOrderParams.setOrderId(10L);
            TestDBUtility.insertWithDel(l_eqtypeOrderParams);
            
            EqtypeOrderUnitParams l_eqtypeOrderUnitParams = TestDBUtility.getEqtypeOrderUnitRow();
            l_eqtypeOrderUnitParams.setOrderStatus(OrderStatusEnum.NOT_CANCELLED);
            l_eqtypeOrderUnitParams.setOrderOpenStatus(OrderOpenStatusEnum.OPEN);
            l_eqtypeOrderUnitParams.setOrderId(10L);
            l_eqtypeOrderUnitParams.setProductId(3304148080001L);
            l_eqtypeOrderUnitParams.setAccountId(333812512246L);
            l_eqtypeOrderUnitParams.setOrderCateg(OrderCategEnum.ASSET);
            l_eqtypeOrderUnitParams.setProductType(ProductTypeEnum.EQUITY);
            l_eqtypeOrderUnitParams.setExecutionConditionType(EqTypeExecutionConditionType.NONE);
            //買注文の場合
            l_eqtypeOrderUnitParams.setOrderType(OrderTypeEnum.EQUITY_BUY);
            //概算受渡代金
            l_eqtypeOrderUnitParams.setEstimatedPrice(0.0D);
            //注文数量
            l_eqtypeOrderUnitParams.setQuantity(23.0D);
            //約定数量
            l_eqtypeOrderUnitParams.setExecutedQuantity(12.0D);
            //値段条件
            l_eqtypeOrderUnitParams.setPriceConditionType(WEB3PriceConditionDef.PRESENT_VALUE_LIMIT_PRICE_ORDER);
            //発注条件
            l_eqtypeOrderUnitParams.setOrderConditionType(WEB3OrderingConditionDef.W_LIMIT_PRICE);
            //逆指値
            l_eqtypeOrderUnitParams.setStopOrderPrice(80.0D);
            //発注条件演算子
            l_eqtypeOrderUnitParams.setOrderCondOperator(WEB3OrderConditionOperatorDef.ABOVE_BASE_PRICE);
            //元発注条件
            l_eqtypeOrderUnitParams.setOrgOrderConditionType("2");
//            //元逆指値基準値
//            l_eqtypeOrderUnitParams.setOrgStopOrderPrice(123.0D);
            //元発注条件演算子
            l_eqtypeOrderUnitParams.setOrgOrderCondOperator("1");
            //（W指値）訂正指値
            l_eqtypeOrderUnitParams.setWLimitPrice(0.0D);
            //指値
            l_eqtypeOrderUnitParams.setLimitPrice(7.90D);
            //初回注文の注文単位ＩＤ
            l_eqtypeOrderUnitParams.setFirstOrderUnitId(5);
            //注文失効日付
            l_eqtypeOrderUnitParams.setExpirationDate(Calendar.getInstance().getTime());
            //元（W指値）執行条件
            l_eqtypeOrderUnitParams.setWLimitExecCondType(EqTypeExecutionConditionType.AT_MARKET_CLOSE);
            //リクエストタイプ
            l_eqtypeOrderUnitParams.setRequestType("2");
            //
            l_eqtypeOrderUnitParams.setWLimitBeforeExecCondType(EqTypeExecutionConditionType.NONE);
            //
            l_eqtypeOrderUnitParams.setOrgWLimitPrice(1.0D);
            //
            l_eqtypeOrderUnitParams.setOrgWLimitExecCondType(EqTypeExecutionConditionType.NONE);
            l_eqtypeOrderUnitParams.setWLimitBeforeLimitPrice(82.3D);
            l_eqtypeOrderUnitParams.setConfirmedQuantity(231D);
            TestDBUtility.insertWithDel(l_eqtypeOrderUnitParams);
            
            ProductParams l_productParams = TestDBUtility.getProductRow();
            l_productParams.setProductType(ProductTypeEnum.EQUITY);
            l_productParams.setProductId(3304148080001L);
            l_productParams.setStandardName("シンセンテルス");
            TestDBUtility.insertWithDel(l_productParams);
            
            EqtypeProductParams l_eqtypeProductParams = TestDBUtility.getEqtypeProductRow();
            l_eqtypeProductParams.setProductId(3304148080001L);
            l_eqtypeProductParams.setStandardName("シンセンテルス");
            TestDBUtility.insertWithDel(l_eqtypeProductParams);
            
            TradedProductParams l_tradedProductParams = TestDBUtility.getTradedProductRow();
            l_tradedProductParams.setProductId(3304148080001L);
            l_tradedProductParams.setProductType(ProductTypeEnum.EQUITY);
            TestDBUtility.insertWithDel(l_tradedProductParams);
            
            EqtypeTradedProductParams l_eqtypeTradedProductParams = TestDBUtility.getEqtypeTradedProductRow();
            l_eqtypeTradedProductParams.setProductId(3304148080001L);
            TestDBUtility.insertWithDel(l_eqtypeTradedProductParams);
            
            BranchMarketPtsDealtCondParams l_branchMarketPTSDealtCondParams = new BranchMarketPtsDealtCondParams();
            l_branchMarketPTSDealtCondParams.setInstitutionCode("0D");
            l_branchMarketPTSDealtCondParams.setBranchCode("381");
            l_branchMarketPTSDealtCondParams.setMarketCode("SP");
            l_branchMarketPTSDealtCondParams.setMartCanDealtEquity("1");
            TestDBUtility.insertWithDel(l_branchMarketPTSDealtCondParams);
            
            AssetParams l_assetParams = TestDBUtility.getAssetRow();
            l_assetParams.setAccountId(333812512246L);
            l_assetParams.setSubAccountId(33381251220301L);
            l_assetParams.setProductId(3304148080001L);
            l_assetParams.setTaxType(TaxTypeEnum.SPECIAL);
            l_assetParams.setMiniStockDiv("0");
            TestDBUtility.insertWithDel(l_assetParams);
            
            WEB3EquityCancelConfirmResponse l_confirmResponse = l_cancelOrderServiceImpl.validateCancelOrder(l_cancelConfirmRequest);
            
            //確認時発注日
            assertEquals(WEB3DateUtility.formatDate(l_confirmResponse.checkDate, "yyyyMMdd"),
                    "20070612");
            //概算受渡代金
            assertEquals("0", "" + l_confirmResponse.estimatedPrice);
            //取引終了警告市場コード一覧
            assertEquals("0", "" + l_confirmResponse.messageSuspension.length);
            //銘柄コード
            assertEquals("N8080", l_confirmResponse.productCode);
            //銘柄名
            assertEquals("シンセンテルス", l_confirmResponse.productName);
            //市場コード
            assertEquals("SP", l_confirmResponse.marketCode);
            //口座区分
            assertEquals("1", l_confirmResponse.taxType);
            //取引区分
            //注文単位.注文種別＝”1：現物買注文”の場合は、"1：現物買付注文"をセット。
            assertEquals("1", l_confirmResponse.tradingType);
            //注文株数
            assertEquals("23", l_confirmResponse.orderQuantity);
            //内出来株数
            assertEquals("12", l_confirmResponse.partContQuantity);
            //注文単価区分
            assertEquals("1", l_confirmResponse.orderPriceDiv);
            //注文単価
            //※注文単位.isMarketOrder( )＝trueの場合は、nullをセット。
            assertEquals("7.9", "" + l_confirmResponse.limitPrice);
            //概算簿価単価
            //注文単位.注文種別＝"2：現物売注文"の場合、株式計算サービス.calc概算簿価単価( )
            assertNull(l_confirmResponse.estimatedBookPrice);
            //値段条件
            assertEquals("1", l_confirmResponse.priceCondType);
            //執行条件
            assertEquals("1", l_confirmResponse.execCondType);
            //注文期限区分
            //PTS注文マネージャ.is出来るまで注文単位(注文単位)＝trueの場合、"2：出来るまで注文"をセット。
            assertEquals("2", l_confirmResponse.expirationDateType);
            //注文有効期限
            assertEquals(WEB3DateUtility.formatDate(l_confirmResponse.expirationDate, "yyyyMMdd"),
               WEB3DateUtility.formatDate(Calendar.getInstance().getTime(), "yyyyMMdd"));
            //発注条件区分
            assertEquals("2", l_confirmResponse.orderCondType);
            //逆指値用発注条件単価
            //注文単位.発注条件＝1（逆指値）の場合、注文単位.逆指値
            assertNull(l_confirmResponse.stopOrderCondPrice);
            //逆指値用発注条件演算子
            //注文単位.発注条件＝1（逆指値）の場合、注文単位.発注条件演算子
            assertNull(l_confirmResponse.stopOrderCondOperator);
            //W指値用発注条件単価
            //注文単位.発注条件＝2（W指値）の場合、注文単位.逆指値
            assertEquals("80", l_confirmResponse.wlimitOrderCondPrice);
            //W指値用発注条件演算子
            //注文単位.発注条件＝2（W指値）の場合、注文単位.発注条件演算子
            assertEquals("1", l_confirmResponse.wlimitOrderCondOperator);
            //W指値用注文単価区分
            assertEquals("0", l_confirmResponse.wLimitOrderPriceDiv);
            //W指値用注文単価
            assertNull(l_confirmResponse.wLimitPrice);
            //W指値用執行条件
            assertEquals("4", l_confirmResponse.wlimitExecCondType);
            //W指値用有効状態区分
            assertEquals("1", l_confirmResponse.wlimitEnableStatusDiv);
            //W指値用切替前注文単価
            assertEquals("82.3", l_confirmResponse.wlimitBefChgLimitPrice);
            //W指値用切替前執行条件
            assertEquals("1", l_confirmResponse.wlimitBefChgExecCondType);
            //元発注条件区分
            assertEquals("2", l_confirmResponse.orgOrderCondType);
            //元発注条件単価
            assertNull(l_confirmResponse.orgOrderCondPrice);
            //元発注条件演算子
            assertEquals("1", l_confirmResponse.orgOrderCondOperator);
            //元W指値用注文単価区分
            assertEquals("1", l_confirmResponse.orgWlimitOrderPriceDiv);
            //元W指値用注文単価
            assertEquals("1", l_confirmResponse.orgWlimitPrice);
            //元W指値用執行条件
            assertEquals("1", l_confirmResponse.orgWlimitExecCondType);
            //時価区分
            assertEquals("3", l_confirmResponse.currentPriceDiv);
            //時価（現在値）
            assertEquals("1", "" + l_confirmResponse.currentPrice);
            //前日比
            assertEquals("87", "" + l_confirmResponse.comparedPreviousDay);
            //取引時間(時価発表時間)
            assertEquals(WEB3DateUtility.formatDate(Calendar.getInstance().getTime(), "yyyyMMdd"),
                    WEB3DateUtility.formatDate(l_confirmResponse.currentPriceTime, "yyyyMMdd"));
            //現在値：　@取得した株式銘柄時価情報.get現在値( )
            assertEquals("5", "" + l_confirmResponse.boardCurrentPrice);
            //現在値時刻：　@取得した株式銘柄時価情報.get現在値時刻( )
            assertEquals(WEB3DateUtility.formatDate(Calendar.getInstance().getTime(), "yyyyMMdd"),
                    WEB3DateUtility.formatDate(l_confirmResponse.boardCurrentPriceTime, "yyyyMMdd"));
            //現在値区分：　@取得した株式銘柄時価情報.get現在値区分( )
            assertEquals("6", "" + l_confirmResponse.boardCurrentPriceDiv);
            //現在値前日比：　@取得した株式銘柄時価情報.get現在値前日比( )
            assertEquals("7", "" + l_confirmResponse.boardChange);
            //出来高：　@取得した株式銘柄時価情報.get出来高( )
            assertEquals("8", "" + l_confirmResponse.volume);
            //出来高時刻：　@取得した株式銘柄時価情報.get出来高時刻( )
            assertEquals(WEB3DateUtility.formatDate(Calendar.getInstance().getTime(), "yyyyMMdd"),
                    WEB3DateUtility.formatDate(l_confirmResponse.volumeTime, "yyyyMMdd"));
            //買気配値タイトル区分：　@取得した株式銘柄時価情報.get買気配値タイトル区分( )
            assertEquals("9", "" + l_confirmResponse.askPriceTitle);
            //買気配値：　@取得した株式銘柄時価情報.get買気配値( )
            assertEquals("10", "" + l_confirmResponse.askPrice);
            //買気配値時刻：　@取得した株式銘柄時価情報.get買気配値時刻( )
            assertEquals(WEB3DateUtility.formatDate(Calendar.getInstance().getTime(), "yyyyMMdd"),
                    WEB3DateUtility.formatDate(l_confirmResponse.askPriceTime, "yyyyMMdd"));
            //売気配値タイトル区分：　@取得した株式銘柄時価情報.get売気配値タイトル区分( )
            assertEquals("11", "" + l_confirmResponse.bidPriceTitle);
            //売気配値：　@取得した株式銘柄時価情報.get売気配値( )
            assertEquals("12", "" + l_confirmResponse.bidPrice);
            //売気配値時刻：　@取得した株式銘柄時価情報.get売気配値時刻( )
            assertEquals(WEB3DateUtility.formatDate(Calendar.getInstance().getTime(), "yyyyMMdd"),
                    WEB3DateUtility.formatDate(l_confirmResponse.bidPriceTime, "yyyyMMdd"));
            //基準値段：　@取得した株式銘柄時価情報.get基準値段( )
            assertEquals("13", "" + l_confirmResponse.basePrice);
            
        }
        catch (WEB3BaseException e)
        {
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        catch (Exception e)
        {
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    
    public class WEB3TPTradingPowerServiceImplForTest extends WEB3TPTradingPowerServiceImpl
    {
        public void reCalcTradingPower(WEB3GentradeSubAccount l_subAccount)
        throws WEB3SystemLayerException
        {
            
        }
    }
}
@
