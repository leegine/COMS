head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.00.55.26;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3EquityPTSChangeOrderInputServiceImplTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : （WEB3EquityPTSChangeOrderInputServiceImplTest.java）
Author Name      : Daiwa Institute of Research
Revision History : 2007/12/26 トウ鋒鋼 (中訊) 新規作成
*/
package webbroker3.equity.service.delegate.stdimpls;

import java.sql.Timestamp;

import com.fitechlabs.xtrade.kernel.util.ThreadLocalSystemAttributesRegistry;
import com.fitechlabs.xtrade.plugin.security.oplogin.impl.LoginInfoImplForMock;
import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeExecutionConditionType;
import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeTradedProduct;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderParams;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderUnitParams;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeProductParams;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeTradedProductParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Branch;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Order;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderCategEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderOpenStatusEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderStatusEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProcessingResult;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccountTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TaxTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.BranchParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.InstitutionParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.InstitutionRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MarketParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MarketRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.ProductParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.SubAccountParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.TradedProductParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.OrderValidationResult;

import test.util.TestDBUtility;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.equity.WEB3EquityProductQuote;
import webbroker3.equity.message.WEB3EquityChangeConfirmRequest;
import webbroker3.equity.message.WEB3EquityChangeInputRequest;
import webbroker3.equity.message.WEB3EquityChangeInputResponse;
import webbroker3.gentrade.WEB3GentradeMainAccount;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.gentrade.WEB3GentradeTradingClendarContext;
import webbroker3.gentrade.data.BranchMarketPtsDealtCondParams;
import webbroker3.gentrade.data.EnableOrderConditionParams;
import webbroker3.gentrade.data.EnableOrderConditionRow;
import webbroker3.gentrade.data.OrderAcceptStatusRow;
import webbroker3.gentrade.data.TradingTimeParams;
import webbroker3.gentrade.data.TradingTimeRow;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

public class WEB3EquityPTSChangeOrderInputServiceImplTest extends TestBaseForMock
{
    
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3EquityPTSChangeOrderInputServiceImplTest.class);
    
    WEB3EquityPTSChangeOrderInputServiceImpl l_serviceImpl = null;

    public WEB3EquityPTSChangeOrderInputServiceImplTest(String arg0)
    {
        super(arg0);
    }

    protected void setUp() throws Exception
    {
        super.setUp();
        l_serviceImpl = new WEB3EquityPTSChangeOrderInputServiceImpl();
        ThreadLocalSystemAttributesRegistry.setAttribute(
            "xblocks.gtl.attributes.systemtimestamp",
            new Timestamp(WEB3DateUtility.getDate("20071226", "yyyyMMdd").getTime()));
        ThreadLocalSystemAttributesRegistry.setAttribute(
                "web3.attributes.basetimestampfororderbizdate",
                null);
    }

    protected void tearDown() throws Exception
    {
        l_serviceImpl = null;
        super.tearDown();
    }

    /*
     * Test method for 'webbroker3.equity.service.delegate.stdimpls.WEB3EquityPTSChangeOrderInputServiceImpl.execute(WEB3GenRequest)'
     */
    public void testExecute_0001()
    {
        final String STR_METHOD_NAME = "testExecute_0001()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        try
        {
            l_serviceImpl.execute(null);
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80017, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        
        log.exiting(TEST_END + STR_METHOD_NAME);

    }
    
    public void testExecute_0002()
    {
        final String STR_METHOD_NAME = "testExecute_0002()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        WEB3EquityChangeInputRequest l_request = new WEB3EquityChangeInputRequest();
        
        try
        {
            l_serviceImpl = new WEB3EquityPTSChangeOrderInputServiceImplForTest();
            WEB3EquityChangeInputResponse l_response =
                (WEB3EquityChangeInputResponse)l_serviceImpl.execute(l_request);
            assertEquals("20", l_response.askPrice);
            assertEquals("30", l_response.basePrice);
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        
        log.exiting(TEST_END + STR_METHOD_NAME);

    }
    
    public void testExecute_0003()
    {
        final String STR_METHOD_NAME = "testExecute_0003()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        WEB3EquityChangeConfirmRequest l_request = new WEB3EquityChangeConfirmRequest();
        
        try
        {
            l_serviceImpl.execute(l_request);
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80018, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        
        log.exiting(TEST_END + STR_METHOD_NAME);

    }

    /*
     * Test method for 'webbroker3.equity.service.delegate.stdimpls.WEB3EquityPTSChangeOrderInputServiceImpl.getChangeInputScreen(WEB3EquityChangeInputRequest)'
     */
    public void testGetChangeInputScreen_0001()
    {
        final String STR_METHOD_NAME = "testGetChangeInputScreen_0001()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        try
        {
            WEB3EquityChangeInputRequest l_request = new WEB3EquityChangeInputRequest();
            
            l_serviceImpl.getChangeInputScreen(l_request);
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00600, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    public void testGetChangeInputScreen_0002()
    {
        final String STR_METHOD_NAME = "testGetChangeInputScreen_0002()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        try
        {
            TestDBUtility.deleteAll(EqtypeOrderParams.TYPE);
            TestDBUtility.deleteAll(EqtypeOrderUnitParams.TYPE);
            TestDBUtility.deleteAll(MarketRow.TYPE);

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
            TestDBUtility.insertWithDel(l_eqtypeOrderUnitParams);
            
            TestDBUtility.deleteAll(TradingTimeRow.TYPE);

            MarketParams l_marketParams = TestDBUtility.getMarketRow();
            l_marketParams.setMarketId(3303L);
            TestDBUtility.insertWithDel(l_marketParams);

            WEB3EquityChangeInputRequest l_request = new WEB3EquityChangeInputRequest();
            l_request.id = "10";
            
            l_serviceImpl.getChangeInputScreen(l_request);
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80005, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    public void testGetChangeInputScreen_0003()
    {
        final String STR_METHOD_NAME = "testGetChangeInputScreen_0003()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
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
            
            
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setBranchId(33381);
            TestDBUtility.insertWithDel(l_branchParams);
            
            TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams.setBizDateType("1");
            l_tradingTimeParams.setBranchCode("123");
            l_tradingTimeParams.setTradingTimeType("01");
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

           
            TestDBUtility.deleteAll(MarketRow.TYPE);
            MarketParams l_marketParams = TestDBUtility.getMarketRow();
            l_marketParams.setMarketId(3303L);
            TestDBUtility.insertWithDel(l_marketParams);
            
            TestDBUtility.deleteAll(OrderAcceptStatusRow.TYPE);
            
            WEB3GentradeTradingClendarContext l_clendarContext =
                (WEB3GentradeTradingClendarContext)ThreadLocalSystemAttributesRegistry.getAttribute(
                    "web3.tradingcalendarcontext");
            
            l_clendarContext.setOrderAcceptTransaction("07");
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.gentrade.WEB3GentradeOrderValidator",
                    "validateAccountForTrading",
                    new Class[] {WEB3GentradeMainAccount.class, Timestamp.class},
                    new OrderValidationResult(
                        ProcessingResult.newFailedResultInstance(
                            WEB3ErrorCatalog.BUSINESS_ERROR_00275)));

            WEB3EquityChangeInputRequest l_request = new WEB3EquityChangeInputRequest();
            l_request.id = "10";
            
            l_serviceImpl.getChangeInputScreen(l_request);
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00275, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    public void testGetChangeInputScreen_0004()
    {
        final String STR_METHOD_NAME = "testGetChangeInputScreen_0004()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
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
            TestDBUtility.deleteAll(InstitutionParams.TYPE);
            
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            TestDBUtility.insertWithDel(l_institutionParams);
            
            BranchMarketPtsDealtCondParams l_branchMarketPtsDealtCondParams =
                TestDBUtility.getBranchMarketPtsDealtCondRow();
            l_branchMarketPtsDealtCondParams.setInstitutionCode("0D");
            l_branchMarketPtsDealtCondParams.setMarketCode("SP");
            l_branchMarketPtsDealtCondParams.setMartCanDealtEquity("1");
            TestDBUtility.insertWithDel(l_branchMarketPtsDealtCondParams);
            
            
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setBranchId(33381);
            TestDBUtility.insertWithDel(l_branchParams);
            
            TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams.setBizDateType("1");
            l_tradingTimeParams.setBranchCode("123");
            l_tradingTimeParams.setTradingTimeType("01");
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

           
            TestDBUtility.deleteAll(MarketRow.TYPE);
            MarketParams l_marketParams = TestDBUtility.getMarketRow();
            l_marketParams.setMarketId(3303L);
            TestDBUtility.insertWithDel(l_marketParams);
            
            TestDBUtility.deleteAll(OrderAcceptStatusRow.TYPE);
            
            WEB3GentradeTradingClendarContext l_clendarContext =
                (WEB3GentradeTradingClendarContext)ThreadLocalSystemAttributesRegistry.getAttribute(
                    "web3.tradingcalendarcontext");
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue("webbroker3.equity.WEB3EquityPTSOrderManager",
                    "validateMarket",
                    new Class[]{ String.class, String.class },
                    new WEB3BaseException(WEB3ErrorCatalog.SYSTEM_ERROR_80005, ""));
            
            l_clendarContext.setOrderAcceptTransaction("07");

            WEB3EquityChangeInputRequest l_request = new WEB3EquityChangeInputRequest();
            l_request.id = "10";
            
            l_serviceImpl.getChangeInputScreen(l_request);
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80005, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    public void testGetChangeInputScreen_0005()
    {
        final String STR_METHOD_NAME = "testGetChangeInputScreen_0005()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
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
            
            
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setBranchId(33381);
            TestDBUtility.insertWithDel(l_branchParams);
            
            TestDBUtility.deleteAll(InstitutionParams.TYPE);
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            TestDBUtility.insertWithDel(l_institutionParams);
            
            TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams.setBizDateType("1");
            l_tradingTimeParams.setBranchCode("123");
            l_tradingTimeParams.setTradingTimeType("01");
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

            BranchMarketPtsDealtCondParams l_branchMarketPtsDealtCondParams =
                TestDBUtility.getBranchMarketPtsDealtCondRow();
            l_branchMarketPtsDealtCondParams.setMarketCode("SP");
            l_branchMarketPtsDealtCondParams.setMartCanDealtEquity("1");
            TestDBUtility.insertWithDel(l_branchMarketPtsDealtCondParams);
           
            TestDBUtility.deleteAll(MarketRow.TYPE);
            MarketParams l_marketParams = TestDBUtility.getMarketRow();
            l_marketParams.setMarketId(3303L);
            TestDBUtility.insertWithDel(l_marketParams);
            
            TestDBUtility.deleteAll(OrderAcceptStatusRow.TYPE);
            
            WEB3GentradeTradingClendarContext l_clendarContext =
                (WEB3GentradeTradingClendarContext)ThreadLocalSystemAttributesRegistry.getAttribute(
                    "web3.tradingcalendarcontext");
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue("webbroker3.equity.WEB3EquityPTSOrderManager",
                    "validatePTSOrderForChangeability", new Class[]
                    { Order.class },
                    new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_01357, ""));
            
            l_clendarContext.setOrderAcceptTransaction("07");

            WEB3EquityChangeInputRequest l_request = new WEB3EquityChangeInputRequest();
            l_request.id = "10";
            
            l_serviceImpl.getChangeInputScreen(l_request);
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_01357, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    public void testGetChangeInputScreen_0006()
    {
        final String STR_METHOD_NAME = "testGetChangeInputScreen_0006()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
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
            
            TestDBUtility.deleteAll(InstitutionParams.TYPE);
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            TestDBUtility.insertWithDel(l_institutionParams);
            
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setBranchId(33381);
            TestDBUtility.insertWithDel(l_branchParams);
            
            TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams.setBizDateType("1");
            l_tradingTimeParams.setBranchCode("123");
            l_tradingTimeParams.setTradingTimeType("01");
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
            l_eqtypeOrderUnitParams.setConfirmedQuantity(22.3);
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

            BranchMarketPtsDealtCondParams l_branchMarketPtsDealtCondParams =
                TestDBUtility.getBranchMarketPtsDealtCondRow();
            l_branchMarketPtsDealtCondParams.setMarketCode("SP");
            l_branchMarketPtsDealtCondParams.setMartCanDealtEquity("1");
            TestDBUtility.insertWithDel(l_branchMarketPtsDealtCondParams);
           
            TestDBUtility.deleteAll(MarketRow.TYPE);
            MarketParams l_marketParams = TestDBUtility.getMarketRow();
            l_marketParams.setMarketId(3303L);
            TestDBUtility.insertWithDel(l_marketParams);
            
            TestDBUtility.deleteAll(OrderAcceptStatusRow.TYPE);
            
            TestDBUtility.deleteAll(EnableOrderConditionRow.TYPE);
            EnableOrderConditionParams l_enableOrderConditionParams =
                TestDBUtility.getEnableOrderConditionRow();
            l_enableOrderConditionParams.setProductType(ProductTypeEnum.EQUITY);
            l_enableOrderConditionParams.setFutureOptionDiv("0");
            l_enableOrderConditionParams.setMarketCode("SP");
            TestDBUtility.insertWithDel(l_enableOrderConditionParams);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue("webbroker3.equity.WEB3EquityPTSOrderManager",
                "validatePTSAccountProductOrderStop",
                new Class[]{ SubAccount.class, long.class, OrderTypeEnum.class },
                new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_01357, ""));
            
            WEB3GentradeTradingClendarContext l_clendarContext =
                (WEB3GentradeTradingClendarContext)ThreadLocalSystemAttributesRegistry.getAttribute(
                    "web3.tradingcalendarcontext");
            
            l_clendarContext.setOrderAcceptTransaction("07");

            WEB3EquityChangeInputRequest l_request = new WEB3EquityChangeInputRequest();
            l_request.id = "10";
            
            l_serviceImpl.getChangeInputScreen(l_request);
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_01357, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    public void testGetChangeInputScreen_0007()
    {
        final String STR_METHOD_NAME = "testGetChangeInputScreen_0007()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
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
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue("webbroker3.equity.WEB3EquityPTSOrderManager",
                    "validatePTSOrderForChangeability",
                    new Class[]{ Order.class }, null);
            
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
            
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setBranchId(33381);
            TestDBUtility.insertWithDel(l_branchParams);
            
            TestDBUtility.deleteAll(InstitutionRow.TYPE);
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            TestDBUtility.insertWithDel(l_institutionParams);
            
            TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams.setBizDateType("1");
            l_tradingTimeParams.setBranchCode("123");
            l_tradingTimeParams.setTradingTimeType("01");
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
            l_eqtypeOrderUnitParams.setExecutionConditionType(EqTypeExecutionConditionType.NONE);
            l_eqtypeOrderUnitParams.setExecutedQuantity(new Double(20));
            l_eqtypeOrderUnitParams.setPriceConditionType("1");
            l_eqtypeOrderUnitParams.setOrderConditionType("1");
            l_eqtypeOrderUnitParams.setOrderCondOperator("2");
            l_eqtypeOrderUnitParams.setOrgOrderConditionType("1");
            l_eqtypeOrderUnitParams.setOrgOrderCondOperator("2");
            l_eqtypeOrderUnitParams.setStopOrderPrice(20);
            l_eqtypeOrderUnitParams.setOrgStopOrderPrice(20);
            TestDBUtility.insertWithDel(l_eqtypeOrderUnitParams);
            
            ProductParams l_productParams = TestDBUtility.getProductRow();
            l_productParams.setProductType(ProductTypeEnum.EQUITY);
            l_productParams.setProductId(3304148080001L);
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

            BranchMarketPtsDealtCondParams l_branchMarketPtsDealtCondParams =
                TestDBUtility.getBranchMarketPtsDealtCondRow();
            l_branchMarketPtsDealtCondParams.setMarketCode("SP");
            l_branchMarketPtsDealtCondParams.setMartCanDealtEquity("1");
            TestDBUtility.insertWithDel(l_branchMarketPtsDealtCondParams);
           
            TestDBUtility.deleteAll(MarketRow.TYPE);
            MarketParams l_marketParams = TestDBUtility.getMarketRow();
            l_marketParams.setMarketId(3303L);
            TestDBUtility.insertWithDel(l_marketParams);
            
            TestDBUtility.deleteAll(OrderAcceptStatusRow.TYPE);
            
            TestDBUtility.deleteAll(EnableOrderConditionRow.TYPE);
            EnableOrderConditionParams l_enableOrderConditionParams =
                TestDBUtility.getEnableOrderConditionRow();
            l_enableOrderConditionParams.setProductType(ProductTypeEnum.EQUITY);
            l_enableOrderConditionParams.setFutureOptionDiv("0");
            l_enableOrderConditionParams.setMarketCode("SP");
            l_enableOrderConditionParams.setAtMarketOpen("1");
            TestDBUtility.insertWithDel(l_enableOrderConditionParams);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.tradingpower.WEB3TPTradingPowerServiceImpl", "getEquityTradingPower",
                new Class[]{ WEB3GentradeSubAccount.class },
                new Double(25));
            
            WEB3EquityProductQuote l_equityProductQuote = new WEB3EquityProductQuote();
            //時価区分
            l_equityProductQuote.setQuoteTypeDiv("1");
            //時価
            l_equityProductQuote.setQuote(23.0);
            //前日比
            l_equityProductQuote.setComparedPreviousDay(1.2);
            //時価発表時間
            l_equityProductQuote.setQuoteTime(new Timestamp(WEB3DateUtility.getDate("20071227", "yyyyMMdd").getTime()));
            //現在値
            l_equityProductQuote.setBoardCurrentPrice("23");
            //現在値時刻
            l_equityProductQuote.setBoardCurrentPriceTime(new Timestamp(WEB3DateUtility.getDate("20071228", "yyyyMMdd").getTime()));
            //現在値区分
            l_equityProductQuote.setBoardCurrentPriceDiv("1");
            //現在値前日比
            l_equityProductQuote.setBoardChange("2");
            //出来高
            l_equityProductQuote.setVolume("5");
            //出来高時刻
            l_equityProductQuote.setVolumeTime(new Timestamp(WEB3DateUtility.getDate("20071228", "yyyyMMdd").getTime()));
            //買気配値タイトル区分
            l_equityProductQuote.setAskPriceTitle("2");
            //買気配値
            l_equityProductQuote.setAskPrice("15");
            //買気配値時刻
            l_equityProductQuote.setAskPriceTime(new Timestamp(WEB3DateUtility.getDate("20071228", "yyyyMMdd").getTime()));
            //売気配値タイトル区分
            l_equityProductQuote.setBidPriceTitle("2");
            //売気配値
            l_equityProductQuote.setBidPrice("23");
            //売気配値時刻
            l_equityProductQuote.setBidPriceTime(new Timestamp(WEB3DateUtility.getDate("20071228", "yyyyMMdd").getTime()));
            //基準値段
            l_equityProductQuote.setBasePrice("15");
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.equity.WEB3EquityProductManager", "getDisplayEquityProductQuote",
                    new Class[]{ EqTypeTradedProduct.class, WEB3GentradeSubAccount.class },
                    l_equityProductQuote);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.equity.WEB3EquityOrderManager", "getOrderPriceDivs",
                    new Class[]{ Branch.class, EqTypeTradedProduct.class },
                    new String[]{"1"});
            
            WEB3GentradeTradingClendarContext l_clendarContext =
                (WEB3GentradeTradingClendarContext)ThreadLocalSystemAttributesRegistry.getAttribute(
                    "web3.tradingcalendarcontext");
            
            l_clendarContext.setOrderAcceptTransaction("07");

            WEB3EquityChangeInputRequest l_request = new WEB3EquityChangeInputRequest();
            l_request.id = "10";
            
            WEB3EquityChangeInputResponse l_response = l_serviceImpl.getChangeInputScreen(l_request);
            
            //注文単価区分一覧
            assertEquals("1", l_response.orderPriceDivList[0]);
            //値段条件一覧
            assertEquals("0", l_response.priceCondList[0]);
            //執行条件一覧
            assertEquals("1", l_response.execCondList[0]);
            assertEquals("3", l_response.execCondList[1]);
            //W指値用執行条件一覧
            assertNull(l_response.wlimitExecCondList);
            //有効期限開始日
            assertNull(l_response.expirationStartDate);
            //有効期限最終日
            assertNull(l_response.expirationEndDate);
            //有効期限内祝日一覧
            assertNull(l_response.holidayList);
            //取引終了警告市場コード一覧
            assertEquals("0", l_response.messageSuspension.length + "");
            //インサイダー警告表示フラグ
            assertFalse(l_response.insiderWarningFlag);
            //時価区分
            assertEquals("1", l_response.currentPriceDiv);
            //時価
            assertEquals("23", l_response.currentPrice);
            //前日比
            assertEquals("1.2", l_response.comparedPreviousDay);
            //時価発表時間
            assertEquals(WEB3DateUtility.getDate("20071227", "yyyyMMdd"), l_response.currentPriceTime);
            //現在値
            assertEquals("23", l_response.boardCurrentPrice);
            //現在値時刻
            assertEquals(WEB3DateUtility.getDate("20071228", "yyyyMMdd"), l_response.boardCurrentPriceTime);
            //現在値区分
            assertEquals("1", l_response.boardCurrentPriceDiv);
            //現在値前日比
            assertEquals("2", l_response.boardChange);
            //出来高
            assertEquals("5", l_response.volume);
            //出来高時刻
            assertEquals(WEB3DateUtility.getDate("20071228", "yyyyMMdd"), l_response.volumeTime);
            //買気配値タイトル区分
            assertEquals("2", l_response.askPriceTitle);
            //買気配値
            assertEquals("15", l_response.askPrice);
            //買気配値時刻
            assertEquals(WEB3DateUtility.getDate("20071228", "yyyyMMdd"), l_response.askPriceTime);
            //売気配値タイトル区分
            assertEquals("2", l_response.bidPriceTitle);
            //売気配値
            assertEquals("23", l_response.bidPrice);
            //売気配値時刻
            assertEquals(WEB3DateUtility.getDate("20071228", "yyyyMMdd"), l_response.bidPriceTime);
            //基準値段
            assertEquals("15", l_response.basePrice);
            //買付可能金額
            assertEquals("25", l_response.tradingPower);
            //売買区分
            assertEquals("1", l_response.dealingType);
            //概算簿価単価
            assertNull(l_response.estimatedBookPrice);
            //銘柄コード
            assertEquals("N8080", l_response.productCode);
            //銘柄名
            assertEquals("シンセンテルス", l_response.productName);
            //市場コード
            assertEquals("SP", l_response.marketCode);
            //口座区分
            assertEquals("1", l_response.taxType);
            //注文株数
            assertEquals("1200", l_response.orderQuantity);
            //内出来株数
            assertEquals("20", l_response.partContQuantity);
            //注文単価区分
            assertEquals("0", l_response.orderPriceDiv);
            //注文単価
            assertNull(l_response.limitPrice);
            //値段条件
            assertEquals("1", l_response.priceCondType);
            //執行条件
            assertEquals("1", l_response.execCondType);
            //注文有効期限
            assertNull(l_response.expirationDate);
            //注文期限区分
            assertEquals("1", l_response.expirationDateType);
            //発注条件区分
            assertEquals("1", l_response.orderCondType);
            //逆指値用発注条件単価
            assertEquals("20", l_response.stopOrderCondPrice);
            //逆指値用発注条件演算子: 注文単位.発注条件演算子
            assertEquals("2", l_response.stopOrderCondOperator);
            
            
//            //W指値用有効状態区分
//            assertEquals("", l_response.wlimitEnableStatusDiv);
            
            //元発注条件区分
            assertEquals("1", l_response.orgOrderCondType);
            //元発注条件単価
            assertEquals("20", l_response.orgOrderCondPrice);
            //元発注条件演算子
            assertEquals("2", l_response.orgOrderCondOperator);
            //元W指値用注文単価区分
            
            //概算受渡代金
            assertEquals("0", l_response.estimatedPrice);
            

        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    public void testGetChangeInputScreen_0008()
    {
        final String STR_METHOD_NAME = "testGetChangeInputScreen_0008()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
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
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue("webbroker3.equity.WEB3EquityPTSOrderManager",
                    "validatePTSOrderForChangeability",
                    new Class[]{ Order.class }, null);
            
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
            
            
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setBranchId(33381);
            TestDBUtility.insertWithDel(l_branchParams);
            
            TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams.setBizDateType("1");
            l_tradingTimeParams.setBranchCode("123");
            l_tradingTimeParams.setTradingTimeType("01");
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
            l_eqtypeOrderUnitParams.setOrderType(OrderTypeEnum.EQUITY_SELL);
            l_eqtypeOrderUnitParams.setOrderCateg(OrderCategEnum.ASSET);
            l_eqtypeOrderUnitParams.setProductType(ProductTypeEnum.EQUITY);
            l_eqtypeOrderUnitParams.setExecutionConditionType(EqTypeExecutionConditionType.NONE);
            l_eqtypeOrderUnitParams.setExecutedQuantity(new Double(20));
            l_eqtypeOrderUnitParams.setPriceConditionType("1");
            l_eqtypeOrderUnitParams.setOrderConditionType("2");
            l_eqtypeOrderUnitParams.setOrderCondOperator("2");
            l_eqtypeOrderUnitParams.setOrgOrderConditionType("2");
            l_eqtypeOrderUnitParams.setOrgOrderCondOperator("2");
            l_eqtypeOrderUnitParams.setStopOrderPrice(20);
            l_eqtypeOrderUnitParams.setOrgStopOrderPrice(20);
            l_eqtypeOrderUnitParams.setLimitPrice(new Double(5));
            l_eqtypeOrderUnitParams.setFirstOrderUnitId(3232L);
            l_eqtypeOrderUnitParams.setWLimitExecCondType(EqTypeExecutionConditionType.AT_MARKET_OPEN);
            l_eqtypeOrderUnitParams.setExpirationDate(WEB3DateUtility.getDate("20071227", "yyyyMMdd"));
            TestDBUtility.insertWithDel(l_eqtypeOrderUnitParams);
            
            ProductParams l_productParams = TestDBUtility.getProductRow();
            l_productParams.setProductType(ProductTypeEnum.EQUITY);
            l_productParams.setProductId(3304148080001L);
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

            BranchMarketPtsDealtCondParams l_branchMarketPtsDealtCondParams =
                TestDBUtility.getBranchMarketPtsDealtCondRow();
            l_branchMarketPtsDealtCondParams.setMarketCode("SP");
            l_branchMarketPtsDealtCondParams.setMartCanDealtEquity("1");
            TestDBUtility.insertWithDel(l_branchMarketPtsDealtCondParams);
           
            TestDBUtility.deleteAll(MarketRow.TYPE);
            MarketParams l_marketParams = TestDBUtility.getMarketRow();
            l_marketParams.setMarketId(3303L);
            TestDBUtility.insertWithDel(l_marketParams);
            
            TestDBUtility.deleteAll(OrderAcceptStatusRow.TYPE);
            
            TestDBUtility.deleteAll(EnableOrderConditionRow.TYPE);
            EnableOrderConditionParams l_enableOrderConditionParams =
                TestDBUtility.getEnableOrderConditionRow();
            l_enableOrderConditionParams.setProductType(ProductTypeEnum.EQUITY);
            l_enableOrderConditionParams.setFutureOptionDiv("0");
            l_enableOrderConditionParams.setMarketCode("SP");
            l_enableOrderConditionParams.setAtMarketOpen("1");
            TestDBUtility.insertWithDel(l_enableOrderConditionParams);
            
            WEB3EquityProductQuote l_equityProductQuote = new WEB3EquityProductQuote();
            //時価区分
            l_equityProductQuote.setQuoteTypeDiv("1");
            //時価
            l_equityProductQuote.setQuote(23.0);
            //前日比
            l_equityProductQuote.setComparedPreviousDay(1.2);
            //時価発表時間
            l_equityProductQuote.setQuoteTime(new Timestamp(WEB3DateUtility.getDate("20071227", "yyyyMMdd").getTime()));
            //現在値
            l_equityProductQuote.setBoardCurrentPrice("23");
            //現在値時刻
            l_equityProductQuote.setBoardCurrentPriceTime(new Timestamp(WEB3DateUtility.getDate("20071228", "yyyyMMdd").getTime()));
            //現在値区分
            l_equityProductQuote.setBoardCurrentPriceDiv("1");
            //現在値前日比
            l_equityProductQuote.setBoardChange("2");
            //出来高
            l_equityProductQuote.setVolume("5");
            //出来高時刻
            l_equityProductQuote.setVolumeTime(new Timestamp(WEB3DateUtility.getDate("20071228", "yyyyMMdd").getTime()));
            //買気配値タイトル区分
            l_equityProductQuote.setAskPriceTitle("2");
            //買気配値
            l_equityProductQuote.setAskPrice("15");
            //買気配値時刻
            l_equityProductQuote.setAskPriceTime(new Timestamp(WEB3DateUtility.getDate("20071228", "yyyyMMdd").getTime()));
            //売気配値タイトル区分
            l_equityProductQuote.setBidPriceTitle("2");
            //売気配値
            l_equityProductQuote.setBidPrice("23");
            //売気配値時刻
            l_equityProductQuote.setBidPriceTime(new Timestamp(WEB3DateUtility.getDate("20071228", "yyyyMMdd").getTime()));
            //基準値段
            l_equityProductQuote.setBasePrice("15");
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.equity.WEB3EquityProductManager", "getDisplayEquityProductQuote",
                    new Class[]{ EqTypeTradedProduct.class, WEB3GentradeSubAccount.class },
                    l_equityProductQuote);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue("webbroker3.equity.WEB3EquityBizLogicProvider",
                    "calcEstimatedBookPrice",
                    new Class[]{ long.class, SubAccount.class, TaxTypeEnum.class },
                    new Double(30));
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.equity.WEB3EquityOrderManager", "getOrderPriceDivs",
                    new Class[]{ Branch.class, EqTypeTradedProduct.class },
                    new String[]{"1"});
            
            WEB3GentradeTradingClendarContext l_clendarContext =
                (WEB3GentradeTradingClendarContext)ThreadLocalSystemAttributesRegistry.getAttribute(
                    "web3.tradingcalendarcontext");
            
            l_clendarContext.setOrderAcceptTransaction("07");

            WEB3EquityChangeInputRequest l_request = new WEB3EquityChangeInputRequest();
            l_request.id = "10";
            
            WEB3EquityChangeInputResponse l_response = l_serviceImpl.getChangeInputScreen(l_request);
            
            //注文単価区分一覧
            assertEquals("1", l_response.orderPriceDivList[0]);
            //値段条件一覧
            assertEquals("0", l_response.priceCondList[0]);
            //執行条件一覧
            assertEquals("1", l_response.execCondList[0]);
            assertEquals("3", l_response.execCondList[1]);
            //W指値用執行条件一覧
            assertNull(l_response.wlimitExecCondList);
            //有効期限開始日
            assertNull(l_response.expirationStartDate);
            //有効期限最終日
            assertNull(l_response.expirationEndDate);
            //有効期限内祝日一覧
            assertNull(l_response.holidayList);
            //取引終了警告市場コード一覧
            assertEquals("0", l_response.messageSuspension.length + "");
            //インサイダー警告表示フラグ
            assertFalse(l_response.insiderWarningFlag);
            //時価区分
            assertEquals("1", l_response.currentPriceDiv);
            //時価
            assertEquals("23", l_response.currentPrice);
            //前日比
            assertEquals("1.2", l_response.comparedPreviousDay);
            //時価発表時間
            assertEquals(WEB3DateUtility.getDate("20071227", "yyyyMMdd"), l_response.currentPriceTime);
            //現在値
            assertEquals("23", l_response.boardCurrentPrice);
            //現在値時刻
            assertEquals(WEB3DateUtility.getDate("20071228", "yyyyMMdd"), l_response.boardCurrentPriceTime);
            //現在値区分
            assertEquals("1", l_response.boardCurrentPriceDiv);
            //現在値前日比
            assertEquals("2", l_response.boardChange);
            //出来高
            assertEquals("5", l_response.volume);
            //出来高時刻
            assertEquals(WEB3DateUtility.getDate("20071228", "yyyyMMdd"), l_response.volumeTime);
            //買気配値タイトル区分
            assertEquals("2", l_response.askPriceTitle);
            //買気配値
            assertEquals("15", l_response.askPrice);
            //買気配値時刻
            assertEquals(WEB3DateUtility.getDate("20071228", "yyyyMMdd"), l_response.askPriceTime);
            //売気配値タイトル区分
            assertEquals("2", l_response.bidPriceTitle);
            //売気配値
            assertEquals("23", l_response.bidPrice);
            //売気配値時刻
            assertEquals(WEB3DateUtility.getDate("20071228", "yyyyMMdd"), l_response.bidPriceTime);
            //基準値段
            assertEquals("15", l_response.basePrice);
            //買付可能金額
            assertNull(l_response.tradingPower);
            //売買区分
            assertEquals("2", l_response.dealingType);
            //概算簿価単価
            assertEquals("30", l_response.estimatedBookPrice);
            //銘柄コード
            assertEquals("N8080", l_response.productCode);
            //銘柄名
            assertEquals("シンセンテルス", l_response.productName);
            //市場コード
            assertEquals("SP", l_response.marketCode);
            //口座区分
            assertEquals("1", l_response.taxType);
            //注文株数
            assertEquals("1200", l_response.orderQuantity);
            //内出来株数
            assertEquals("20", l_response.partContQuantity);
            //注文単価区分
            assertEquals("1", l_response.orderPriceDiv);
            //注文単価
            assertEquals("5", l_response.limitPrice);
            //値段条件
            assertEquals("1", l_response.priceCondType);
            //執行条件
            assertEquals("1", l_response.execCondType);
            //注文有効期限
            assertEquals(WEB3DateUtility.getDate("20071227", "yyyyMMdd"), l_response.expirationDate);
            //注文期限区分
            assertEquals("2", l_response.expirationDateType);
            //発注条件区分
            assertEquals("2", l_response.orderCondType);
            //逆指値用発注条件単価
            assertNull(l_response.stopOrderCondPrice);
            //逆指値用発注条件演算子: 注文単位.発注条件演算子
            assertNull(l_response.stopOrderCondOperator);
            
            //W指値用発注条件単価
            assertEquals("20", l_response.wlimitOrderCondPrice);
            //W指値用発注条件演算子
            assertEquals("2", l_response.wlimitOrderCondOperator);
            //W指値用注文単価区分
            assertEquals("0", l_response.wLimitOrderPriceDiv);
            //W指値用執行条件
            assertEquals("3", l_response.wlimitExecCondType);
            
            //W指値用有効状態区分
            assertEquals("0", l_response.wlimitEnableStatusDiv);
            
            //W指値用切替前注文単価
            assertEquals("5", l_response.wlimitBefChgLimitPrice);
            //W指値用切替前執行条件
            assertEquals("1", l_response.wlimitBefChgExecCondType);
            
            //元発注条件区分
            assertEquals("2", l_response.orgOrderCondType);
            //元発注条件単価
            assertEquals("20", l_response.orgOrderCondPrice);
            //元発注条件演算子
            assertEquals("2", l_response.orgOrderCondOperator);
            //元W指値用注文単価区分
            
            //概算受渡代金
            assertEquals("0", l_response.estimatedPrice);
            

        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    public void testGetChangeInputScreen_0009()
    {
        final String STR_METHOD_NAME = "testGetChangeInputScreen_0009()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
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
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue("webbroker3.equity.WEB3EquityPTSOrderManager",
                    "validatePTSOrderForChangeability",
                    new Class[]{ Order.class }, null);
            
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
            
            
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setBranchId(33381);
            TestDBUtility.insertWithDel(l_branchParams);
            
            TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams.setBizDateType("1");
            l_tradingTimeParams.setBranchCode("123");
            l_tradingTimeParams.setTradingTimeType("01");
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
            l_eqtypeOrderUnitParams.setOrderType(OrderTypeEnum.EQUITY_SELL);
            l_eqtypeOrderUnitParams.setOrderCateg(OrderCategEnum.ASSET);
            l_eqtypeOrderUnitParams.setProductType(ProductTypeEnum.EQUITY);
            l_eqtypeOrderUnitParams.setExecutionConditionType(EqTypeExecutionConditionType.NONE);
            l_eqtypeOrderUnitParams.setExecutedQuantity(new Double(20));
            l_eqtypeOrderUnitParams.setPriceConditionType("1");
            l_eqtypeOrderUnitParams.setOrderConditionType("2");
            l_eqtypeOrderUnitParams.setOrderCondOperator("2");
            l_eqtypeOrderUnitParams.setOrgOrderConditionType("2");
            l_eqtypeOrderUnitParams.setOrgOrderCondOperator("2");
            l_eqtypeOrderUnitParams.setLimitPrice(new Double(5));
            l_eqtypeOrderUnitParams.setFirstOrderUnitId(3232L);
            l_eqtypeOrderUnitParams.setWLimitExecCondType(EqTypeExecutionConditionType.AT_MARKET_OPEN);
            l_eqtypeOrderUnitParams.setExpirationDate(WEB3DateUtility.getDate("20071227", "yyyyMMdd"));
            l_eqtypeOrderUnitParams.setWLimitPrice(5.3);
            l_eqtypeOrderUnitParams.setOrgWLimitPrice(5.9);
            l_eqtypeOrderUnitParams.setRequestType("2");
            l_eqtypeOrderUnitParams.setStopOrderPrice(20);
            l_eqtypeOrderUnitParams.setOrgStopOrderPrice(20);
            l_eqtypeOrderUnitParams.setWLimitBeforeExecCondType(EqTypeExecutionConditionType.AT_MARKET_OPEN);
            l_eqtypeOrderUnitParams.setOrgWLimitExecCondType(EqTypeExecutionConditionType.AT_MARKET_OPEN);
            TestDBUtility.insertWithDel(l_eqtypeOrderUnitParams);
            
            ProductParams l_productParams = TestDBUtility.getProductRow();
            l_productParams.setProductType(ProductTypeEnum.EQUITY);
            l_productParams.setProductId(3304148080001L);
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

            BranchMarketPtsDealtCondParams l_branchMarketPtsDealtCondParams =
                TestDBUtility.getBranchMarketPtsDealtCondRow();
            l_branchMarketPtsDealtCondParams.setMarketCode("SP");
            l_branchMarketPtsDealtCondParams.setMartCanDealtEquity("1");
            TestDBUtility.insertWithDel(l_branchMarketPtsDealtCondParams);
           
            TestDBUtility.deleteAll(MarketRow.TYPE);
            MarketParams l_marketParams = TestDBUtility.getMarketRow();
            l_marketParams.setMarketId(3303L);
            TestDBUtility.insertWithDel(l_marketParams);
            
            TestDBUtility.deleteAll(OrderAcceptStatusRow.TYPE);
            
            TestDBUtility.deleteAll(EnableOrderConditionRow.TYPE);
            EnableOrderConditionParams l_enableOrderConditionParams =
                TestDBUtility.getEnableOrderConditionRow();
            l_enableOrderConditionParams.setProductType(ProductTypeEnum.EQUITY);
            l_enableOrderConditionParams.setFutureOptionDiv("0");
            l_enableOrderConditionParams.setMarketCode("SP");
            l_enableOrderConditionParams.setAtMarketOpen("1");
            TestDBUtility.insertWithDel(l_enableOrderConditionParams);
            
            WEB3EquityProductQuote l_equityProductQuote = new WEB3EquityProductQuote();
            //時価区分
            l_equityProductQuote.setQuoteTypeDiv("1");
            //時価
            l_equityProductQuote.setQuote(23.0);
            //前日比
            l_equityProductQuote.setComparedPreviousDay(1.2);
            //時価発表時間
            l_equityProductQuote.setQuoteTime(new Timestamp(WEB3DateUtility.getDate("20071227", "yyyyMMdd").getTime()));
            //現在値
            l_equityProductQuote.setBoardCurrentPrice("23");
            //現在値時刻
            l_equityProductQuote.setBoardCurrentPriceTime(new Timestamp(WEB3DateUtility.getDate("20071228", "yyyyMMdd").getTime()));
            //現在値区分
            l_equityProductQuote.setBoardCurrentPriceDiv("1");
            //現在値前日比
            l_equityProductQuote.setBoardChange("2");
            //出来高
            l_equityProductQuote.setVolume("5");
            //出来高時刻
            l_equityProductQuote.setVolumeTime(new Timestamp(WEB3DateUtility.getDate("20071228", "yyyyMMdd").getTime()));
            //買気配値タイトル区分
            l_equityProductQuote.setAskPriceTitle("2");
            //買気配値
            l_equityProductQuote.setAskPrice("15");
            //買気配値時刻
            l_equityProductQuote.setAskPriceTime(new Timestamp(WEB3DateUtility.getDate("20071228", "yyyyMMdd").getTime()));
            //売気配値タイトル区分
            l_equityProductQuote.setBidPriceTitle("2");
            //売気配値
            l_equityProductQuote.setBidPrice("23");
            //売気配値時刻
            l_equityProductQuote.setBidPriceTime(new Timestamp(WEB3DateUtility.getDate("20071228", "yyyyMMdd").getTime()));
            //基準値段
            l_equityProductQuote.setBasePrice("15");
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.equity.WEB3EquityProductManager", "getDisplayEquityProductQuote",
                    new Class[]{ EqTypeTradedProduct.class, WEB3GentradeSubAccount.class },
                    l_equityProductQuote);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue("webbroker3.equity.WEB3EquityBizLogicProvider",
                    "calcEstimatedBookPrice",
                    new Class[]{ long.class, SubAccount.class, TaxTypeEnum.class },
                    new Double(30));
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.equity.WEB3EquityOrderManager", "getOrderPriceDivs",
                    new Class[]{ Branch.class, EqTypeTradedProduct.class },
                    new String[]{"1"});
            
            WEB3GentradeTradingClendarContext l_clendarContext =
                (WEB3GentradeTradingClendarContext)ThreadLocalSystemAttributesRegistry.getAttribute(
                    "web3.tradingcalendarcontext");
            
            l_clendarContext.setOrderAcceptTransaction("07");

            WEB3EquityChangeInputRequest l_request = new WEB3EquityChangeInputRequest();
            l_request.id = "10";
            
            WEB3EquityChangeInputResponse l_response = l_serviceImpl.getChangeInputScreen(l_request);
            
            //注文単価区分一覧
            assertEquals("1", l_response.orderPriceDivList[0]);
            //値段条件一覧
            assertEquals("0", l_response.priceCondList[0]);
            //執行条件一覧
            assertEquals("1", l_response.execCondList[0]);
            assertEquals("3", l_response.execCondList[1]);
            //W指値用執行条件一覧
            assertNull(l_response.wlimitExecCondList);
            //有効期限開始日
            assertNull(l_response.expirationStartDate);
            //有効期限最終日
            assertNull(l_response.expirationEndDate);
            //有効期限内祝日一覧
            assertNull(l_response.holidayList);
            //取引終了警告市場コード一覧
            assertEquals("0", l_response.messageSuspension.length + "");
            //インサイダー警告表示フラグ
            assertFalse(l_response.insiderWarningFlag);
            //時価区分
            assertEquals("1", l_response.currentPriceDiv);
            //時価
            assertEquals("23", l_response.currentPrice);
            //前日比
            assertEquals("1.2", l_response.comparedPreviousDay);
            //時価発表時間
            assertEquals(WEB3DateUtility.getDate("20071227", "yyyyMMdd"), l_response.currentPriceTime);
            //現在値
            assertEquals("23", l_response.boardCurrentPrice);
            //現在値時刻
            assertEquals(WEB3DateUtility.getDate("20071228", "yyyyMMdd"), l_response.boardCurrentPriceTime);
            //現在値区分
            assertEquals("1", l_response.boardCurrentPriceDiv);
            //現在値前日比
            assertEquals("2", l_response.boardChange);
            //出来高
            assertEquals("5", l_response.volume);
            //出来高時刻
            assertEquals(WEB3DateUtility.getDate("20071228", "yyyyMMdd"), l_response.volumeTime);
            //買気配値タイトル区分
            assertEquals("2", l_response.askPriceTitle);
            //買気配値
            assertEquals("15", l_response.askPrice);
            //買気配値時刻
            assertEquals(WEB3DateUtility.getDate("20071228", "yyyyMMdd"), l_response.askPriceTime);
            //売気配値タイトル区分
            assertEquals("2", l_response.bidPriceTitle);
            //売気配値
            assertEquals("23", l_response.bidPrice);
            //売気配値時刻
            assertEquals(WEB3DateUtility.getDate("20071228", "yyyyMMdd"), l_response.bidPriceTime);
            //基準値段
            assertEquals("15", l_response.basePrice);
            //買付可能金額
            assertNull(l_response.tradingPower);
            //売買区分
            assertEquals("2", l_response.dealingType);
            //概算簿価単価
            assertEquals("30", l_response.estimatedBookPrice);
            //銘柄コード
            assertEquals("N8080", l_response.productCode);
            //銘柄名
            assertEquals("シンセンテルス", l_response.productName);
            //市場コード
            assertEquals("SP", l_response.marketCode);
            //口座区分
            assertEquals("1", l_response.taxType);
            //注文株数
            assertEquals("1200", l_response.orderQuantity);
            //内出来株数
            assertEquals("20", l_response.partContQuantity);
            //注文単価区分
            assertEquals("1", l_response.orderPriceDiv);
            //注文単価
            assertEquals("5", l_response.limitPrice);
            //値段条件
            assertEquals("1", l_response.priceCondType);
            //執行条件
            assertEquals("1", l_response.execCondType);
            //注文有効期限
            assertEquals(WEB3DateUtility.getDate("20071227", "yyyyMMdd"), l_response.expirationDate);
            //注文期限区分
            assertEquals("2", l_response.expirationDateType);
            //発注条件区分
            assertEquals("2", l_response.orderCondType);
            //逆指値用発注条件単価
            assertNull(l_response.stopOrderCondPrice);
            //逆指値用発注条件演算子: 注文単位.発注条件演算子
            assertNull(l_response.stopOrderCondOperator);
            
            //W指値用発注条件単価
            assertEquals("20", l_response.wlimitOrderCondPrice);
            //W指値用発注条件演算子
            assertEquals("2", l_response.wlimitOrderCondOperator);
            //W指値用注文単価区分
            assertEquals("1", l_response.wLimitOrderPriceDiv);
            //W指値用注文単価
            assertEquals("5.3", l_response.wLimitPrice);
            //W指値用執行条件
            assertEquals("3", l_response.wlimitExecCondType);
            
            //W指値用有効状態区分
            assertEquals("1", l_response.wlimitEnableStatusDiv);
            
            //W指値用切替前注文単価
            assertNull(l_response.wlimitBefChgLimitPrice);
            //W指値用切替前執行条件
            assertEquals("3", l_response.wlimitBefChgExecCondType);
            
            //元発注条件区分
            assertEquals("2", l_response.orgOrderCondType);
            //元発注条件単価
            assertEquals("20", l_response.orgOrderCondPrice);
            //元発注条件演算子
            assertEquals("2", l_response.orgOrderCondOperator);
            //元W指値用注文単価区分
            assertEquals("1", l_response.orgWlimitOrderPriceDiv);
            //元W指値用注文単価
            assertEquals("5.9", l_response.orgWlimitPrice);
            //概算受渡代金
            assertEquals("0", l_response.estimatedPrice);
            

        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    private class WEB3EquityPTSChangeOrderInputServiceImplForTest extends WEB3EquityPTSChangeOrderInputServiceImpl
    {
        protected WEB3EquityChangeInputResponse getChangeInputScreen(
            WEB3EquityChangeInputRequest l_request) throws WEB3BaseException
        {
            WEB3EquityChangeInputResponse l_response = new WEB3EquityChangeInputResponse();
            l_response.askPrice = "20";
            l_response.basePrice = "30";
            return l_response;
        }
    }

}
@
