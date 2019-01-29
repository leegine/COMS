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
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �iWEB3EquityPTSChangeOrderInputServiceImplTest.java�j
Author Name      : Daiwa Institute of Research
Revision History : 2007/12/26 �g�E�N�| (���u) �V�K�쐬
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
            l_eqtypeProductParams.setStandardName("�V���Z���e���X");
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
            //�����敪
            l_equityProductQuote.setQuoteTypeDiv("1");
            //����
            l_equityProductQuote.setQuote(23.0);
            //�O����
            l_equityProductQuote.setComparedPreviousDay(1.2);
            //�������\����
            l_equityProductQuote.setQuoteTime(new Timestamp(WEB3DateUtility.getDate("20071227", "yyyyMMdd").getTime()));
            //���ݒl
            l_equityProductQuote.setBoardCurrentPrice("23");
            //���ݒl����
            l_equityProductQuote.setBoardCurrentPriceTime(new Timestamp(WEB3DateUtility.getDate("20071228", "yyyyMMdd").getTime()));
            //���ݒl�敪
            l_equityProductQuote.setBoardCurrentPriceDiv("1");
            //���ݒl�O����
            l_equityProductQuote.setBoardChange("2");
            //�o����
            l_equityProductQuote.setVolume("5");
            //�o��������
            l_equityProductQuote.setVolumeTime(new Timestamp(WEB3DateUtility.getDate("20071228", "yyyyMMdd").getTime()));
            //���C�z�l�^�C�g���敪
            l_equityProductQuote.setAskPriceTitle("2");
            //���C�z�l
            l_equityProductQuote.setAskPrice("15");
            //���C�z�l����
            l_equityProductQuote.setAskPriceTime(new Timestamp(WEB3DateUtility.getDate("20071228", "yyyyMMdd").getTime()));
            //���C�z�l�^�C�g���敪
            l_equityProductQuote.setBidPriceTitle("2");
            //���C�z�l
            l_equityProductQuote.setBidPrice("23");
            //���C�z�l����
            l_equityProductQuote.setBidPriceTime(new Timestamp(WEB3DateUtility.getDate("20071228", "yyyyMMdd").getTime()));
            //��l�i
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
            
            //�����P���敪�ꗗ
            assertEquals("1", l_response.orderPriceDivList[0]);
            //�l�i�����ꗗ
            assertEquals("0", l_response.priceCondList[0]);
            //���s�����ꗗ
            assertEquals("1", l_response.execCondList[0]);
            assertEquals("3", l_response.execCondList[1]);
            //W�w�l�p���s�����ꗗ
            assertNull(l_response.wlimitExecCondList);
            //�L�������J�n��
            assertNull(l_response.expirationStartDate);
            //�L�������ŏI��
            assertNull(l_response.expirationEndDate);
            //�L���������j���ꗗ
            assertNull(l_response.holidayList);
            //����I���x���s��R�[�h�ꗗ
            assertEquals("0", l_response.messageSuspension.length + "");
            //�C���T�C�_�[�x���\���t���O
            assertFalse(l_response.insiderWarningFlag);
            //�����敪
            assertEquals("1", l_response.currentPriceDiv);
            //����
            assertEquals("23", l_response.currentPrice);
            //�O����
            assertEquals("1.2", l_response.comparedPreviousDay);
            //�������\����
            assertEquals(WEB3DateUtility.getDate("20071227", "yyyyMMdd"), l_response.currentPriceTime);
            //���ݒl
            assertEquals("23", l_response.boardCurrentPrice);
            //���ݒl����
            assertEquals(WEB3DateUtility.getDate("20071228", "yyyyMMdd"), l_response.boardCurrentPriceTime);
            //���ݒl�敪
            assertEquals("1", l_response.boardCurrentPriceDiv);
            //���ݒl�O����
            assertEquals("2", l_response.boardChange);
            //�o����
            assertEquals("5", l_response.volume);
            //�o��������
            assertEquals(WEB3DateUtility.getDate("20071228", "yyyyMMdd"), l_response.volumeTime);
            //���C�z�l�^�C�g���敪
            assertEquals("2", l_response.askPriceTitle);
            //���C�z�l
            assertEquals("15", l_response.askPrice);
            //���C�z�l����
            assertEquals(WEB3DateUtility.getDate("20071228", "yyyyMMdd"), l_response.askPriceTime);
            //���C�z�l�^�C�g���敪
            assertEquals("2", l_response.bidPriceTitle);
            //���C�z�l
            assertEquals("23", l_response.bidPrice);
            //���C�z�l����
            assertEquals(WEB3DateUtility.getDate("20071228", "yyyyMMdd"), l_response.bidPriceTime);
            //��l�i
            assertEquals("15", l_response.basePrice);
            //���t�\���z
            assertEquals("25", l_response.tradingPower);
            //�����敪
            assertEquals("1", l_response.dealingType);
            //�T�Z�뉿�P��
            assertNull(l_response.estimatedBookPrice);
            //�����R�[�h
            assertEquals("N8080", l_response.productCode);
            //������
            assertEquals("�V���Z���e���X", l_response.productName);
            //�s��R�[�h
            assertEquals("SP", l_response.marketCode);
            //�����敪
            assertEquals("1", l_response.taxType);
            //��������
            assertEquals("1200", l_response.orderQuantity);
            //���o������
            assertEquals("20", l_response.partContQuantity);
            //�����P���敪
            assertEquals("0", l_response.orderPriceDiv);
            //�����P��
            assertNull(l_response.limitPrice);
            //�l�i����
            assertEquals("1", l_response.priceCondType);
            //���s����
            assertEquals("1", l_response.execCondType);
            //�����L������
            assertNull(l_response.expirationDate);
            //���������敪
            assertEquals("1", l_response.expirationDateType);
            //���������敪
            assertEquals("1", l_response.orderCondType);
            //�t�w�l�p���������P��
            assertEquals("20", l_response.stopOrderCondPrice);
            //�t�w�l�p�����������Z�q: �����P��.�����������Z�q
            assertEquals("2", l_response.stopOrderCondOperator);
            
            
//            //W�w�l�p�L����ԋ敪
//            assertEquals("", l_response.wlimitEnableStatusDiv);
            
            //�����������敪
            assertEquals("1", l_response.orgOrderCondType);
            //�����������P��
            assertEquals("20", l_response.orgOrderCondPrice);
            //�������������Z�q
            assertEquals("2", l_response.orgOrderCondOperator);
            //��W�w�l�p�����P���敪
            
            //�T�Z��n���
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
            l_eqtypeProductParams.setStandardName("�V���Z���e���X");
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
            //�����敪
            l_equityProductQuote.setQuoteTypeDiv("1");
            //����
            l_equityProductQuote.setQuote(23.0);
            //�O����
            l_equityProductQuote.setComparedPreviousDay(1.2);
            //�������\����
            l_equityProductQuote.setQuoteTime(new Timestamp(WEB3DateUtility.getDate("20071227", "yyyyMMdd").getTime()));
            //���ݒl
            l_equityProductQuote.setBoardCurrentPrice("23");
            //���ݒl����
            l_equityProductQuote.setBoardCurrentPriceTime(new Timestamp(WEB3DateUtility.getDate("20071228", "yyyyMMdd").getTime()));
            //���ݒl�敪
            l_equityProductQuote.setBoardCurrentPriceDiv("1");
            //���ݒl�O����
            l_equityProductQuote.setBoardChange("2");
            //�o����
            l_equityProductQuote.setVolume("5");
            //�o��������
            l_equityProductQuote.setVolumeTime(new Timestamp(WEB3DateUtility.getDate("20071228", "yyyyMMdd").getTime()));
            //���C�z�l�^�C�g���敪
            l_equityProductQuote.setAskPriceTitle("2");
            //���C�z�l
            l_equityProductQuote.setAskPrice("15");
            //���C�z�l����
            l_equityProductQuote.setAskPriceTime(new Timestamp(WEB3DateUtility.getDate("20071228", "yyyyMMdd").getTime()));
            //���C�z�l�^�C�g���敪
            l_equityProductQuote.setBidPriceTitle("2");
            //���C�z�l
            l_equityProductQuote.setBidPrice("23");
            //���C�z�l����
            l_equityProductQuote.setBidPriceTime(new Timestamp(WEB3DateUtility.getDate("20071228", "yyyyMMdd").getTime()));
            //��l�i
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
            
            //�����P���敪�ꗗ
            assertEquals("1", l_response.orderPriceDivList[0]);
            //�l�i�����ꗗ
            assertEquals("0", l_response.priceCondList[0]);
            //���s�����ꗗ
            assertEquals("1", l_response.execCondList[0]);
            assertEquals("3", l_response.execCondList[1]);
            //W�w�l�p���s�����ꗗ
            assertNull(l_response.wlimitExecCondList);
            //�L�������J�n��
            assertNull(l_response.expirationStartDate);
            //�L�������ŏI��
            assertNull(l_response.expirationEndDate);
            //�L���������j���ꗗ
            assertNull(l_response.holidayList);
            //����I���x���s��R�[�h�ꗗ
            assertEquals("0", l_response.messageSuspension.length + "");
            //�C���T�C�_�[�x���\���t���O
            assertFalse(l_response.insiderWarningFlag);
            //�����敪
            assertEquals("1", l_response.currentPriceDiv);
            //����
            assertEquals("23", l_response.currentPrice);
            //�O����
            assertEquals("1.2", l_response.comparedPreviousDay);
            //�������\����
            assertEquals(WEB3DateUtility.getDate("20071227", "yyyyMMdd"), l_response.currentPriceTime);
            //���ݒl
            assertEquals("23", l_response.boardCurrentPrice);
            //���ݒl����
            assertEquals(WEB3DateUtility.getDate("20071228", "yyyyMMdd"), l_response.boardCurrentPriceTime);
            //���ݒl�敪
            assertEquals("1", l_response.boardCurrentPriceDiv);
            //���ݒl�O����
            assertEquals("2", l_response.boardChange);
            //�o����
            assertEquals("5", l_response.volume);
            //�o��������
            assertEquals(WEB3DateUtility.getDate("20071228", "yyyyMMdd"), l_response.volumeTime);
            //���C�z�l�^�C�g���敪
            assertEquals("2", l_response.askPriceTitle);
            //���C�z�l
            assertEquals("15", l_response.askPrice);
            //���C�z�l����
            assertEquals(WEB3DateUtility.getDate("20071228", "yyyyMMdd"), l_response.askPriceTime);
            //���C�z�l�^�C�g���敪
            assertEquals("2", l_response.bidPriceTitle);
            //���C�z�l
            assertEquals("23", l_response.bidPrice);
            //���C�z�l����
            assertEquals(WEB3DateUtility.getDate("20071228", "yyyyMMdd"), l_response.bidPriceTime);
            //��l�i
            assertEquals("15", l_response.basePrice);
            //���t�\���z
            assertNull(l_response.tradingPower);
            //�����敪
            assertEquals("2", l_response.dealingType);
            //�T�Z�뉿�P��
            assertEquals("30", l_response.estimatedBookPrice);
            //�����R�[�h
            assertEquals("N8080", l_response.productCode);
            //������
            assertEquals("�V���Z���e���X", l_response.productName);
            //�s��R�[�h
            assertEquals("SP", l_response.marketCode);
            //�����敪
            assertEquals("1", l_response.taxType);
            //��������
            assertEquals("1200", l_response.orderQuantity);
            //���o������
            assertEquals("20", l_response.partContQuantity);
            //�����P���敪
            assertEquals("1", l_response.orderPriceDiv);
            //�����P��
            assertEquals("5", l_response.limitPrice);
            //�l�i����
            assertEquals("1", l_response.priceCondType);
            //���s����
            assertEquals("1", l_response.execCondType);
            //�����L������
            assertEquals(WEB3DateUtility.getDate("20071227", "yyyyMMdd"), l_response.expirationDate);
            //���������敪
            assertEquals("2", l_response.expirationDateType);
            //���������敪
            assertEquals("2", l_response.orderCondType);
            //�t�w�l�p���������P��
            assertNull(l_response.stopOrderCondPrice);
            //�t�w�l�p�����������Z�q: �����P��.�����������Z�q
            assertNull(l_response.stopOrderCondOperator);
            
            //W�w�l�p���������P��
            assertEquals("20", l_response.wlimitOrderCondPrice);
            //W�w�l�p�����������Z�q
            assertEquals("2", l_response.wlimitOrderCondOperator);
            //W�w�l�p�����P���敪
            assertEquals("0", l_response.wLimitOrderPriceDiv);
            //W�w�l�p���s����
            assertEquals("3", l_response.wlimitExecCondType);
            
            //W�w�l�p�L����ԋ敪
            assertEquals("0", l_response.wlimitEnableStatusDiv);
            
            //W�w�l�p�֑ؑO�����P��
            assertEquals("5", l_response.wlimitBefChgLimitPrice);
            //W�w�l�p�֑ؑO���s����
            assertEquals("1", l_response.wlimitBefChgExecCondType);
            
            //�����������敪
            assertEquals("2", l_response.orgOrderCondType);
            //�����������P��
            assertEquals("20", l_response.orgOrderCondPrice);
            //�������������Z�q
            assertEquals("2", l_response.orgOrderCondOperator);
            //��W�w�l�p�����P���敪
            
            //�T�Z��n���
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
            l_eqtypeProductParams.setStandardName("�V���Z���e���X");
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
            //�����敪
            l_equityProductQuote.setQuoteTypeDiv("1");
            //����
            l_equityProductQuote.setQuote(23.0);
            //�O����
            l_equityProductQuote.setComparedPreviousDay(1.2);
            //�������\����
            l_equityProductQuote.setQuoteTime(new Timestamp(WEB3DateUtility.getDate("20071227", "yyyyMMdd").getTime()));
            //���ݒl
            l_equityProductQuote.setBoardCurrentPrice("23");
            //���ݒl����
            l_equityProductQuote.setBoardCurrentPriceTime(new Timestamp(WEB3DateUtility.getDate("20071228", "yyyyMMdd").getTime()));
            //���ݒl�敪
            l_equityProductQuote.setBoardCurrentPriceDiv("1");
            //���ݒl�O����
            l_equityProductQuote.setBoardChange("2");
            //�o����
            l_equityProductQuote.setVolume("5");
            //�o��������
            l_equityProductQuote.setVolumeTime(new Timestamp(WEB3DateUtility.getDate("20071228", "yyyyMMdd").getTime()));
            //���C�z�l�^�C�g���敪
            l_equityProductQuote.setAskPriceTitle("2");
            //���C�z�l
            l_equityProductQuote.setAskPrice("15");
            //���C�z�l����
            l_equityProductQuote.setAskPriceTime(new Timestamp(WEB3DateUtility.getDate("20071228", "yyyyMMdd").getTime()));
            //���C�z�l�^�C�g���敪
            l_equityProductQuote.setBidPriceTitle("2");
            //���C�z�l
            l_equityProductQuote.setBidPrice("23");
            //���C�z�l����
            l_equityProductQuote.setBidPriceTime(new Timestamp(WEB3DateUtility.getDate("20071228", "yyyyMMdd").getTime()));
            //��l�i
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
            
            //�����P���敪�ꗗ
            assertEquals("1", l_response.orderPriceDivList[0]);
            //�l�i�����ꗗ
            assertEquals("0", l_response.priceCondList[0]);
            //���s�����ꗗ
            assertEquals("1", l_response.execCondList[0]);
            assertEquals("3", l_response.execCondList[1]);
            //W�w�l�p���s�����ꗗ
            assertNull(l_response.wlimitExecCondList);
            //�L�������J�n��
            assertNull(l_response.expirationStartDate);
            //�L�������ŏI��
            assertNull(l_response.expirationEndDate);
            //�L���������j���ꗗ
            assertNull(l_response.holidayList);
            //����I���x���s��R�[�h�ꗗ
            assertEquals("0", l_response.messageSuspension.length + "");
            //�C���T�C�_�[�x���\���t���O
            assertFalse(l_response.insiderWarningFlag);
            //�����敪
            assertEquals("1", l_response.currentPriceDiv);
            //����
            assertEquals("23", l_response.currentPrice);
            //�O����
            assertEquals("1.2", l_response.comparedPreviousDay);
            //�������\����
            assertEquals(WEB3DateUtility.getDate("20071227", "yyyyMMdd"), l_response.currentPriceTime);
            //���ݒl
            assertEquals("23", l_response.boardCurrentPrice);
            //���ݒl����
            assertEquals(WEB3DateUtility.getDate("20071228", "yyyyMMdd"), l_response.boardCurrentPriceTime);
            //���ݒl�敪
            assertEquals("1", l_response.boardCurrentPriceDiv);
            //���ݒl�O����
            assertEquals("2", l_response.boardChange);
            //�o����
            assertEquals("5", l_response.volume);
            //�o��������
            assertEquals(WEB3DateUtility.getDate("20071228", "yyyyMMdd"), l_response.volumeTime);
            //���C�z�l�^�C�g���敪
            assertEquals("2", l_response.askPriceTitle);
            //���C�z�l
            assertEquals("15", l_response.askPrice);
            //���C�z�l����
            assertEquals(WEB3DateUtility.getDate("20071228", "yyyyMMdd"), l_response.askPriceTime);
            //���C�z�l�^�C�g���敪
            assertEquals("2", l_response.bidPriceTitle);
            //���C�z�l
            assertEquals("23", l_response.bidPrice);
            //���C�z�l����
            assertEquals(WEB3DateUtility.getDate("20071228", "yyyyMMdd"), l_response.bidPriceTime);
            //��l�i
            assertEquals("15", l_response.basePrice);
            //���t�\���z
            assertNull(l_response.tradingPower);
            //�����敪
            assertEquals("2", l_response.dealingType);
            //�T�Z�뉿�P��
            assertEquals("30", l_response.estimatedBookPrice);
            //�����R�[�h
            assertEquals("N8080", l_response.productCode);
            //������
            assertEquals("�V���Z���e���X", l_response.productName);
            //�s��R�[�h
            assertEquals("SP", l_response.marketCode);
            //�����敪
            assertEquals("1", l_response.taxType);
            //��������
            assertEquals("1200", l_response.orderQuantity);
            //���o������
            assertEquals("20", l_response.partContQuantity);
            //�����P���敪
            assertEquals("1", l_response.orderPriceDiv);
            //�����P��
            assertEquals("5", l_response.limitPrice);
            //�l�i����
            assertEquals("1", l_response.priceCondType);
            //���s����
            assertEquals("1", l_response.execCondType);
            //�����L������
            assertEquals(WEB3DateUtility.getDate("20071227", "yyyyMMdd"), l_response.expirationDate);
            //���������敪
            assertEquals("2", l_response.expirationDateType);
            //���������敪
            assertEquals("2", l_response.orderCondType);
            //�t�w�l�p���������P��
            assertNull(l_response.stopOrderCondPrice);
            //�t�w�l�p�����������Z�q: �����P��.�����������Z�q
            assertNull(l_response.stopOrderCondOperator);
            
            //W�w�l�p���������P��
            assertEquals("20", l_response.wlimitOrderCondPrice);
            //W�w�l�p�����������Z�q
            assertEquals("2", l_response.wlimitOrderCondOperator);
            //W�w�l�p�����P���敪
            assertEquals("1", l_response.wLimitOrderPriceDiv);
            //W�w�l�p�����P��
            assertEquals("5.3", l_response.wLimitPrice);
            //W�w�l�p���s����
            assertEquals("3", l_response.wlimitExecCondType);
            
            //W�w�l�p�L����ԋ敪
            assertEquals("1", l_response.wlimitEnableStatusDiv);
            
            //W�w�l�p�֑ؑO�����P��
            assertNull(l_response.wlimitBefChgLimitPrice);
            //W�w�l�p�֑ؑO���s����
            assertEquals("3", l_response.wlimitBefChgExecCondType);
            
            //�����������敪
            assertEquals("2", l_response.orgOrderCondType);
            //�����������P��
            assertEquals("20", l_response.orgOrderCondPrice);
            //�������������Z�q
            assertEquals("2", l_response.orgOrderCondOperator);
            //��W�w�l�p�����P���敪
            assertEquals("1", l_response.orgWlimitOrderPriceDiv);
            //��W�w�l�p�����P��
            assertEquals("5.9", l_response.orgWlimitPrice);
            //�T�Z��n���
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
