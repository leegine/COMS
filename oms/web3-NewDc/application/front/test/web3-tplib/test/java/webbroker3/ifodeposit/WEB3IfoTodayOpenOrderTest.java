head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.01.28.10;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3IfoTodayOpenOrderTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (äî)ëÂòaëçå§ èÿåîÉ\ÉäÉÖÅ[ÉVÉáÉìÉVÉXÉeÉÄëÊìÒïî
File Name        : WEB3IfoTodayOpenOrderTest.java
Author Name      : Daiwa Institute of Research
Revision History : 2008/08/20  í£è≠åÜ (íÜêu) êVãKçÏê¨
*/
package webbroker3.ifodeposit;

import java.util.Calendar;
import java.util.Date;

import com.fitechlabs.xtrade.plugin.tc.gentrade.ContractTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderCategEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccountTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TaxTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Trader;
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
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.TradedProductParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.TradedProductRow;
import com.fitechlabs.xtrade.plugin.tc.xbifo.DefaultIfoOrderManagerPersistenceEventInterceptor;
import com.fitechlabs.xtrade.plugin.tc.xbifo.IfoDerivativeTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.xbifo.IfoOrderExecutionConditionType;
import com.fitechlabs.xtrade.plugin.tc.xbifo.IfoOrderManagerPersistenceEventInterceptor;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoOrderUnitParams;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoOrderUnitRow;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoProductParams;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoProductRow;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoTradedProductParams;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoTradedProductRow;
import com.fitechlabs.xtrade.plugin.tc.xbifo.ordersubmitter.io.IfoOpenContractOrderSpec;

import test.util.TestDBUtility;

import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.gentrade.data.CalendarRow;
import webbroker3.gentrade.data.TradingTimeParams;
import webbroker3.gentrade.data.TradingTimeRow;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

public class WEB3IfoTodayOpenOrderTest extends TestBaseForMock
{
    WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3IfoTodayOpenOrderTest.class);
    public WEB3IfoTodayOpenOrderTest(String arg0)
    {
        super(arg0);
    }

    protected void setUp() throws Exception
    {
        super.setUp();
        TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);
    }

    protected void tearDown() throws Exception
    {
        super.tearDown();
    }
    
    //íçï∂íPà Params.íçï∂éÌï  == ÅhêÊï®êVãKîÉåöíçï∂Åh&&ñ¢ñÒíË
    public void testgetIfoTodayOpenOrderC001()
    {
        String STR_METHOD_NAME = " testgetIfoTodayOpenOrderC001";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            TestDBUtility.deleteAll(IfoOrderUnitRow.TYPE);
            IfoOrderUnitParams l_ifoOrderUnitParams = TestDBUtility.getIfoOrderUnitRow();
            l_ifoOrderUnitParams.setOrderType(OrderTypeEnum.IDX_FUTURES_BUY_TO_OPEN);
            l_ifoOrderUnitParams.setExecutedQuantity(null);
            TestDBUtility.insertWithDel(l_ifoOrderUnitParams);
            
            WEB3IfoTodayOpenOrder l_order =
                WEB3IfoTodayOpenOrder.getIfoTodayOpenOrder(l_ifoOrderUnitParams);
            
            assertEquals(1001,l_order.getOrderUnitId());
            assertEquals(100,l_order.getQuantity(),0);
            assertEquals(ContractTypeEnum.LONG,l_order.getContractType());
        }
        catch(Exception l_ex)
        { 
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    //íçï∂íPà Params.íçï∂éÌï  == ÅhOPêVãKîÉåöíçï∂Åh&&àÍïîñÒíË
    public void testgetIfoTodayOpenOrderC002()
    {
        String STR_METHOD_NAME = " testgetIfoTodayOpenOrderC002";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            TestDBUtility.deleteAll(IfoOrderUnitRow.TYPE);
            IfoOrderUnitParams l_ifoOrderUnitParams = TestDBUtility.getIfoOrderUnitRow();
            l_ifoOrderUnitParams.setOrderType(OrderTypeEnum.IDX_OPTIONS_BUY_TO_OPEN);
            l_ifoOrderUnitParams.setExecutedQuantity(50);
            TestDBUtility.insertWithDel(l_ifoOrderUnitParams);
            
            WEB3IfoTodayOpenOrder l_order =
                WEB3IfoTodayOpenOrder.getIfoTodayOpenOrder(l_ifoOrderUnitParams);
            
            assertEquals(1001,l_order.getOrderUnitId());
            assertEquals(50,l_order.getQuantity(),0);
            assertEquals(ContractTypeEnum.LONG,l_order.getContractType());
        }
        catch(Exception l_ex)
        { 
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }  
    
    //íçï∂íPà Params.íçï∂éÌï  != ÅhêÊï®êVãKîÉåöíçï∂Åh && ÅhOPêVãKîÉåöíçï∂Åh
    public void testgetIfoTodayOpenOrderC003()
    {
        String STR_METHOD_NAME = " testgetIfoTodayOpenOrderC003";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            TestDBUtility.deleteAll(IfoOrderUnitRow.TYPE);
            IfoOrderUnitParams l_ifoOrderUnitParams = TestDBUtility.getIfoOrderUnitRow();
            l_ifoOrderUnitParams.setOrderType(OrderTypeEnum.IDX_OPTIONS_SELL_TO_OPEN);
            TestDBUtility.insertWithDel(l_ifoOrderUnitParams);
            
            WEB3IfoTodayOpenOrder l_order =
                WEB3IfoTodayOpenOrder.getIfoTodayOpenOrder(l_ifoOrderUnitParams);
            
            assertEquals(1001,l_order.getOrderUnitId());
            assertEquals(100,l_order.getQuantity(),0);
            assertEquals(ContractTypeEnum.SHORT,l_order.getContractType());
        }
        catch(Exception l_ex)
        { 
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    //ÉIÉvÉVÉáÉìÇÃèÍçá,îÉåöÇÃèÍçá,äTéZéÛìnë„ã‡ÇÃÉ}ÉCÉiÉXíl
    public void testgetIfoTodayOpenOrderC004()
    {
        String STR_METHOD_NAME = " testgetIfoTodayOpenOrderC004";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            TestDBUtility.deleteAll(IfoOrderUnitRow.TYPE);
            IfoOrderUnitParams l_ifoOrderUnitParams = TestDBUtility.getIfoOrderUnitRow();
            l_ifoOrderUnitParams.setOrderCateg(OrderCategEnum.IDX_OPTIONS_OPEN);
            l_ifoOrderUnitParams.setOrderType(OrderTypeEnum.IDX_OPTIONS_BUY_TO_OPEN);
            l_ifoOrderUnitParams.setEstimatedPrice(1000);
            TestDBUtility.insertWithDel(l_ifoOrderUnitParams);
            
            WEB3IfoTodayOpenOrder l_order =
                WEB3IfoTodayOpenOrder.getIfoTodayOpenOrder(l_ifoOrderUnitParams);
            
            assertEquals(-1000, l_order.getOptionEstimatedNetAmount(), 0);
        }
        catch(Exception l_ex)
        { 
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    //ÉIÉvÉVÉáÉìÇÃèÍçá,îÑåöÇÃèÍçáÇÃèÍçá,íçï∂íPà Params.äTéZéÛìnë„ã‡
    public void testgetIfoTodayOpenOrderC005()
    {
        String STR_METHOD_NAME = " testgetIfoTodayOpenOrderC005";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            TestDBUtility.deleteAll(IfoOrderUnitRow.TYPE);
            IfoOrderUnitParams l_ifoOrderUnitParams = TestDBUtility.getIfoOrderUnitRow();
            l_ifoOrderUnitParams.setOrderCateg(OrderCategEnum.IDX_OPTIONS_OPEN);
            l_ifoOrderUnitParams.setOrderType(OrderTypeEnum.IDX_OPTIONS_SELL_TO_OPEN);
            l_ifoOrderUnitParams.setEstimatedPrice(1000);
            TestDBUtility.insertWithDel(l_ifoOrderUnitParams);
            
            WEB3IfoTodayOpenOrder l_order =
                WEB3IfoTodayOpenOrder.getIfoTodayOpenOrder(l_ifoOrderUnitParams);
            
            assertEquals(1000, l_order.getOptionEstimatedNetAmount(), 0);
        }
        catch(Exception l_ex)
        { 
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    //êÊï®OPåªíçï∂ì‡óe.êÊï®ÉIÉvÉVÉáÉìè§ïi == ÅhêÊï®ÅhÇÃèÍçá
    public void testgetIfoTodayOpenOrder_case001()
    {
        String STR_METHOD_NAME = " testgetIfoTodayOpenOrder_case001";
        log.entering(STR_METHOD_NAME);
        try
        {
            TestDBUtility.deleteAll(CalendarRow.TYPE);
            TestDBUtility.deleteAll(TradingTimeRow.TYPE);
            TradingTimeParams l_TradingTimeParams = TestDBUtility.getTradingTimeRow();
            l_TradingTimeParams.setInstitutionCode("0D");
            l_TradingTimeParams.setBranchCode("123");
            l_TradingTimeParams.setMarketCode("N1");
            l_TradingTimeParams.setTradingTimeType("01");
            l_TradingTimeParams.setProductCode("0");
            l_TradingTimeParams.setBizDateType("1");
            TestDBUtility.insertWithDel(l_TradingTimeParams);
            
            TestDBUtility.deleteAll(MarketRow.TYPE);
            MarketParams l_marketParams = TestDBUtility.getMarketRow();
            TestDBUtility.insertWithDel(l_marketParams);
            
            TestDBUtility.deleteAll(BranchRow.TYPE);
            BranchParams l_BranchParams = TestDBUtility.getBranchRow();
            TestDBUtility.insertWithDel(l_BranchParams);

            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setAccountId(0001);
            l_subAccountParams.setSubAccountId(0002);
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
            l_subAccountParams.setInstitutionCode("0D");
            //èÿåîâÔé–ID
            l_subAccountParams.setInstitutionId(33);
            TestDBUtility.insertWithDel(l_subAccountParams);
            
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            MainAccountParams l_mainAccount = TestDBUtility.getMainAccountRow();
            l_mainAccount.setAccountId(1L);
            TestDBUtility.insertWithDel(l_mainAccount);
            WEB3GentradeSubAccount l_subAccount =  new WEB3GentradeSubAccount(l_subAccountParams);
            
            TestDBUtility.deleteAll(InstitutionRow.TYPE);
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            TestDBUtility.insertWithDel(l_institutionParams);
            
            TestDBUtility.deleteAll(ProductRow.TYPE);
            ProductParams l_productParams = TestDBUtility.getProductRow();
            l_productParams.setInstitutionCode("0D");
            l_productParams.setProductType(ProductTypeEnum.IFO);
            l_productParams.setProductId(1006160060005L);
            TestDBUtility.insertWithDel(l_productParams);
            
            TestDBUtility.deleteAll(TradedProductRow.TYPE);
            TradedProductParams l_tradedProductParams = TestDBUtility.getTradedProductRow();
            l_tradedProductParams.setInstitutionCode("0D");
            l_tradedProductParams.setProductId(1006160060005L);
            l_tradedProductParams.setProductType(ProductTypeEnum.IFO);
            TestDBUtility.insertWithDel(l_tradedProductParams);
            
            TestDBUtility.deleteAll(IfoProductRow.TYPE);
            IfoProductParams l_ifoProductParams = TestDBUtility.getIfoProductRow();
            l_ifoProductParams.setInstitutionCode("0D");
            l_ifoProductParams.setSplitType("000");
            TestDBUtility.insertWithDel(l_ifoProductParams);
            
            TestDBUtility.deleteAll(IfoTradedProductRow.TYPE);
            IfoTradedProductParams l_ifoTradedProductParams = TestDBUtility.getIfoTradedProductRow();
            l_ifoTradedProductParams.setProductId(1006160060005L);
            l_ifoTradedProductParams.setInstitutionCode("0D");
            l_ifoTradedProductParams.setProductId(1006160060005L);
            l_ifoTradedProductParams.setMarketId(3303);
            TestDBUtility.insertWithDel(l_ifoTradedProductParams);
            
            Trader trader = null;
            boolean isBuyToOpen = true;
            String underlyingProductCode = "0005";
            IfoDerivativeTypeEnum derivativeType = IfoDerivativeTypeEnum.FUTURES;
            String monthOfDelivery = "200503";
            double strikePrice = 0;
            String marketCode = "SP";
            double quantity = 1000;
            IfoOrderExecutionConditionType execType = null; 
            Date orderExpDate = new Date();
            TaxTypeEnum taxType = null;
            IfoOpenContractOrderSpec l_orderspec = IfoOpenContractOrderSpec.createMarketOrderSpec(trader,
                    isBuyToOpen,underlyingProductCode,
                    derivativeType,monthOfDelivery,
                    strikePrice,marketCode,quantity,
                    execType,orderExpDate,taxType);
            IfoOrderManagerPersistenceEventInterceptor l_interceptor = new DefaultIfoOrderManagerPersistenceEventInterceptor();
            OrderTypeEnum l_orderTypeEnum = OrderTypeEnum.IDX_OPTIONS_BUY_TO_OPEN;
            
            TestDBUtility.deleteAll(IfoOrderUnitParams.TYPE);
            IfoOrderUnitParams l_orderUnitParams = TestDBUtility.getIfoOrderUnitRow();
            l_orderUnitParams.setOrderType(OrderTypeEnum.IDX_FUTURES_BUY_TO_OPEN);
            TestDBUtility.insertWithDel(l_orderUnitParams);       
            
            WEB3IfoNewOrderSpec l_newOrderSpec = 
                WEB3IfoNewOrderSpec.createWEB3IfoNewOrderSpec(
                l_subAccount,
                l_interceptor,
                l_orderspec,
                l_orderTypeEnum);
            l_newOrderSpec.ifoDerivativeType = IfoDerivativeTypeEnum.FUTURES;
            l_newOrderSpec.estimatedNetAmount = 323201;
            l_newOrderSpec.quantity = 1000;
            WEB3IfoTodayOpenOrder l_order =
                WEB3IfoTodayOpenOrder.getIfoTodayOpenOrder(l_newOrderSpec);
            assertEquals(0,l_order.optionEstimatedNetAmount,0);
            assertEquals(1000,l_order.quantity,0);
        }
        catch(Exception l_ex)
        {
            fail();
        }
    }
    //êÊï®OPåªíçï∂ì‡óe.êÊï®ÉIÉvÉVÉáÉìè§ïi == ÅhÉIÉvÉVÉáÉìÅh&&åöãÊï™ == îÉåö
    public void testgetIfoTodayOpenOrder_case002()
    {
        String STR_METHOD_NAME = " testgetIfoTodayOpenOrder_case002";
        log.entering(STR_METHOD_NAME);
        try
        {
            TestDBUtility.deleteAll(CalendarRow.TYPE);
            TestDBUtility.deleteAll(TradingTimeRow.TYPE);
            TradingTimeParams l_TradingTimeParams = TestDBUtility.getTradingTimeRow();
            l_TradingTimeParams.setInstitutionCode("0D");
            l_TradingTimeParams.setBranchCode("123");
            l_TradingTimeParams.setMarketCode("N1");
            l_TradingTimeParams.setTradingTimeType("01");
            l_TradingTimeParams.setProductCode("0");
            l_TradingTimeParams.setBizDateType("1");
            TestDBUtility.insertWithDel(l_TradingTimeParams);
            
            TestDBUtility.deleteAll(MarketRow.TYPE);
            MarketParams l_marketParams = TestDBUtility.getMarketRow();
            TestDBUtility.insertWithDel(l_marketParams);
            
            TestDBUtility.deleteAll(BranchRow.TYPE);
            BranchParams l_BranchParams = TestDBUtility.getBranchRow();
            TestDBUtility.insertWithDel(l_BranchParams);

            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setAccountId(0001);
            l_subAccountParams.setSubAccountId(0002);
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
            l_subAccountParams.setInstitutionCode("0D");
            //èÿåîâÔé–ID
            l_subAccountParams.setInstitutionId(33);
            TestDBUtility.insertWithDel(l_subAccountParams);
            
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            MainAccountParams l_mainAccount = TestDBUtility.getMainAccountRow();
            l_mainAccount.setAccountId(1L);
            TestDBUtility.insertWithDel(l_mainAccount);
            WEB3GentradeSubAccount l_subAccount =  new WEB3GentradeSubAccount(l_subAccountParams);
            
            TestDBUtility.deleteAll(InstitutionRow.TYPE);
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            TestDBUtility.insertWithDel(l_institutionParams);
            
            TestDBUtility.deleteAll(ProductRow.TYPE);
            ProductParams l_productParams = TestDBUtility.getProductRow();
            l_productParams.setInstitutionCode("0D");
            l_productParams.setProductType(ProductTypeEnum.IFO);
            l_productParams.setProductId(1006160060005L);
            TestDBUtility.insertWithDel(l_productParams);
            
            TestDBUtility.deleteAll(TradedProductRow.TYPE);
            TradedProductParams l_tradedProductParams = TestDBUtility.getTradedProductRow();
            l_tradedProductParams.setInstitutionCode("0D");
            l_tradedProductParams.setProductId(1006160060005L);
            l_tradedProductParams.setProductType(ProductTypeEnum.IFO);
            TestDBUtility.insertWithDel(l_tradedProductParams);
            
            TestDBUtility.deleteAll(IfoProductRow.TYPE);
            IfoProductParams l_ifoProductParams = TestDBUtility.getIfoProductRow();
            l_ifoProductParams.setInstitutionCode("0D");
            l_ifoProductParams.setSplitType("000");
            l_ifoProductParams.setDerivativeType(IfoDerivativeTypeEnum.CALL_OPTIONS);
            TestDBUtility.insertWithDel(l_ifoProductParams);
            
            TestDBUtility.deleteAll(IfoTradedProductRow.TYPE);
            IfoTradedProductParams l_ifoTradedProductParams = TestDBUtility.getIfoTradedProductRow();
            l_ifoTradedProductParams.setProductId(1006160060005L);
            l_ifoTradedProductParams.setInstitutionCode("0D");
            l_ifoTradedProductParams.setProductId(1006160060005L);
            l_ifoTradedProductParams.setMarketId(3303);
            TestDBUtility.insertWithDel(l_ifoTradedProductParams);
            
            Trader trader = null;
            boolean isBuyToOpen = true;
            String underlyingProductCode = "0005";
            IfoDerivativeTypeEnum derivativeType = IfoDerivativeTypeEnum.CALL_OPTIONS;
            String monthOfDelivery = "200503";
            double strikePrice = 0;
            String marketCode = "SP";
            double quantity = 1000;
            IfoOrderExecutionConditionType execType = null; 
            Date orderExpDate = new Date();
            TaxTypeEnum taxType = null;
            IfoOpenContractOrderSpec l_orderspec = IfoOpenContractOrderSpec.createMarketOrderSpec(trader,
                    isBuyToOpen,underlyingProductCode,
                    derivativeType,monthOfDelivery,
                    strikePrice,marketCode,quantity,
                    execType,orderExpDate,taxType);
            IfoOrderManagerPersistenceEventInterceptor l_interceptor = new DefaultIfoOrderManagerPersistenceEventInterceptor();
            OrderTypeEnum l_orderTypeEnum = OrderTypeEnum.IDX_OPTIONS_BUY_TO_OPEN;
            
            TestDBUtility.deleteAll(IfoOrderUnitParams.TYPE);
            IfoOrderUnitParams l_orderUnitParams = TestDBUtility.getIfoOrderUnitRow();
            l_orderUnitParams.setOrderType(OrderTypeEnum.IDX_OPTIONS_BUY_TO_OPEN);
            TestDBUtility.insertWithDel(l_orderUnitParams);       
            
            WEB3IfoNewOrderSpec l_newOrderSpec = 
                WEB3IfoNewOrderSpec.createWEB3IfoNewOrderSpec(
                l_subAccount,
                l_interceptor,
                l_orderspec,
                l_orderTypeEnum);
            l_newOrderSpec.ifoDerivativeType = IfoDerivativeTypeEnum.CALL_OPTIONS;
            l_newOrderSpec.estimatedNetAmount = 323201;
            l_newOrderSpec.quantity = 1000;
            WEB3IfoTodayOpenOrder l_order =
                WEB3IfoTodayOpenOrder.getIfoTodayOpenOrder(l_newOrderSpec);
            assertEquals(-323201,l_order.optionEstimatedNetAmount,0);
            assertEquals(1000,l_order.quantity,0);
        }
        catch(Exception l_ex)
        {
            fail();
        }
    }
    // //êÊï®OPåªíçï∂ì‡óe.êÊï®ÉIÉvÉVÉáÉìè§ïi == ÅhÉIÉvÉVÉáÉìÅh&& åöãÊï™ == îÑåö
    public void testgetIfoTodayOpenOrder_case003()
    {
        String STR_METHOD_NAME = " testgetIfoTodayOpenOrder_case003";
        log.entering(STR_METHOD_NAME);
        try
        {
            TestDBUtility.deleteAll(CalendarRow.TYPE);
            TestDBUtility.deleteAll(TradingTimeRow.TYPE);
            TradingTimeParams l_TradingTimeParams = TestDBUtility.getTradingTimeRow();
            l_TradingTimeParams.setInstitutionCode("0D");
            l_TradingTimeParams.setBranchCode("123");
            l_TradingTimeParams.setMarketCode("N1");
            l_TradingTimeParams.setTradingTimeType("01");
            l_TradingTimeParams.setProductCode("0");
            l_TradingTimeParams.setBizDateType("1");
            TestDBUtility.insertWithDel(l_TradingTimeParams);
            
            TestDBUtility.deleteAll(MarketRow.TYPE);
            MarketParams l_marketParams = TestDBUtility.getMarketRow();
            TestDBUtility.insertWithDel(l_marketParams);
            
            TestDBUtility.deleteAll(BranchRow.TYPE);
            BranchParams l_BranchParams = TestDBUtility.getBranchRow();
            TestDBUtility.insertWithDel(l_BranchParams);

            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setAccountId(0001);
            l_subAccountParams.setSubAccountId(0002);
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
            l_subAccountParams.setInstitutionCode("0D");
            //èÿåîâÔé–ID
            l_subAccountParams.setInstitutionId(33);
            TestDBUtility.insertWithDel(l_subAccountParams);
            
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            MainAccountParams l_mainAccount = TestDBUtility.getMainAccountRow();
            l_mainAccount.setAccountId(1L);
            TestDBUtility.insertWithDel(l_mainAccount);
            WEB3GentradeSubAccount l_subAccount =  new WEB3GentradeSubAccount(l_subAccountParams);
            
            TestDBUtility.deleteAll(InstitutionRow.TYPE);
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            TestDBUtility.insertWithDel(l_institutionParams);
            
            TestDBUtility.deleteAll(ProductRow.TYPE);
            ProductParams l_productParams = TestDBUtility.getProductRow();
            l_productParams.setInstitutionCode("0D");
            l_productParams.setProductType(ProductTypeEnum.IFO);
            l_productParams.setProductId(1006160060005L);
            TestDBUtility.insertWithDel(l_productParams);
            
            TestDBUtility.deleteAll(TradedProductRow.TYPE);
            TradedProductParams l_tradedProductParams = TestDBUtility.getTradedProductRow();
            l_tradedProductParams.setInstitutionCode("0D");
            l_tradedProductParams.setProductId(1006160060005L);
            l_tradedProductParams.setProductType(ProductTypeEnum.IFO);
            TestDBUtility.insertWithDel(l_tradedProductParams);
            
            TestDBUtility.deleteAll(IfoProductRow.TYPE);
            IfoProductParams l_ifoProductParams = TestDBUtility.getIfoProductRow();
            l_ifoProductParams.setInstitutionCode("0D");
            l_ifoProductParams.setSplitType("000");
            l_ifoProductParams.setDerivativeType(IfoDerivativeTypeEnum.PUT_OPTIONS);
            TestDBUtility.insertWithDel(l_ifoProductParams);
            
            TestDBUtility.deleteAll(IfoTradedProductRow.TYPE);
            IfoTradedProductParams l_ifoTradedProductParams = TestDBUtility.getIfoTradedProductRow();
            l_ifoTradedProductParams.setProductId(1006160060005L);
            l_ifoTradedProductParams.setInstitutionCode("0D");
            l_ifoTradedProductParams.setProductId(1006160060005L);
            l_ifoTradedProductParams.setMarketId(3303);
            TestDBUtility.insertWithDel(l_ifoTradedProductParams);
            
            Trader trader = null;
            boolean isBuyToOpen = true;
            String underlyingProductCode = "0005";
            IfoDerivativeTypeEnum derivativeType = IfoDerivativeTypeEnum.PUT_OPTIONS;
            String monthOfDelivery = "200503";
            double strikePrice = 0;
            String marketCode = "SP";
            double quantity = 1000;
            IfoOrderExecutionConditionType execType = null; 
            Date orderExpDate = new Date();
            TaxTypeEnum taxType = null;
            IfoOpenContractOrderSpec l_orderspec = IfoOpenContractOrderSpec.createMarketOrderSpec(trader,
                    isBuyToOpen,underlyingProductCode,
                    derivativeType,monthOfDelivery,
                    strikePrice,marketCode,quantity,
                    execType,orderExpDate,taxType);
            IfoOrderManagerPersistenceEventInterceptor l_interceptor = new DefaultIfoOrderManagerPersistenceEventInterceptor();
            OrderTypeEnum l_orderTypeEnum = OrderTypeEnum.IDX_OPTIONS_SELL_TO_OPEN;
            
            TestDBUtility.deleteAll(IfoOrderUnitParams.TYPE);
            IfoOrderUnitParams l_orderUnitParams = TestDBUtility.getIfoOrderUnitRow();
            l_orderUnitParams.setOrderType(OrderTypeEnum.IDX_OPTIONS_SELL_TO_OPEN);
            TestDBUtility.insertWithDel(l_orderUnitParams);       
            
            WEB3IfoNewOrderSpec l_newOrderSpec = 
                WEB3IfoNewOrderSpec.createWEB3IfoNewOrderSpec(
                l_subAccount,
                l_interceptor,
                l_orderspec,
                l_orderTypeEnum);
            l_newOrderSpec.ifoDerivativeType = IfoDerivativeTypeEnum.PUT_OPTIONS;
            l_newOrderSpec.estimatedNetAmount = 323201;
            l_newOrderSpec.quantity = 2000;
            WEB3IfoTodayOpenOrder l_order =
                WEB3IfoTodayOpenOrder.getIfoTodayOpenOrder(l_newOrderSpec);
            assertEquals(323201,l_order.optionEstimatedNetAmount,0);
            assertEquals(2000,l_order.quantity,0);
        }
        catch(Exception l_ex)
        {
            fail();
        }
    }
    
    //isîÉåö == true && this.éÛìnì˙  ==Å@@à¯êî.éÛìnì˙ÇÃèÍçá
    //ÉIÉvÉVÉáÉìäTéZéÛìnë„ã‡Çï‘ãpÇ∑ÇÈ
    public void testGetOptionBuyEstimatedNetAmountC001()
    {
        String STR_METHOD_NAME = "testGetOptionBuyEstimatedNetAmountC001";
        log.entering(STR_METHOD_NAME);
        
        Date l_datDeliveryDate = WEB3DateUtility.getDate("20070112","yyyyMMdd");
        WEB3IfoTodayOpenOrder l_WEB3IfoTodayOpenOrder = new WEB3IfoTodayOpenOrder(); 
        l_WEB3IfoTodayOpenOrder.deliveryDate = WEB3DateUtility.getDate("20070112","yyyyMMdd");
        l_WEB3IfoTodayOpenOrder.optionEstimatedNetAmount = 11000;
        l_WEB3IfoTodayOpenOrder.setContractType(ContractTypeEnum.LONG);
        double l_dbReturn =
            l_WEB3IfoTodayOpenOrder.getOptionBuyEstimatedNetAmount(l_datDeliveryDate);
        assertEquals(11000, l_dbReturn, 0);
        log.exiting(STR_METHOD_NAME);
    }
    
    //isîÉåö == true && this.éÛìnì˙  ==Å@@à¯êî.éÛìnì˙ÇÃèÍçá
    //ÉIÉvÉVÉáÉìäTéZéÛìnë„ã‡Çï‘ãpÇ∑ÇÈ
    public void testGetOptionBuyEstimatedNetAmountC002()
    {
        String STR_METHOD_NAME = " testGetOptionBuyEstimatedNetAmountC002";
        log.entering(STR_METHOD_NAME);
        
        Date l_datDeliveryDate = WEB3DateUtility.getDate("20070112","yyyyMMdd");
        WEB3IfoTodayOpenOrder l_WEB3IfoTodayOpenOrder = new WEB3IfoTodayOpenOrder(); 
        l_WEB3IfoTodayOpenOrder.deliveryDate = WEB3DateUtility.getDate("20070113","yyyyMMdd");
        l_WEB3IfoTodayOpenOrder.optionEstimatedNetAmount = 11000;
        l_WEB3IfoTodayOpenOrder.setContractType(ContractTypeEnum.LONG);
        double l_dbReturn =
            l_WEB3IfoTodayOpenOrder.getOptionBuyEstimatedNetAmount(l_datDeliveryDate);
        assertEquals(0.0, l_dbReturn, 0);
        log.exiting(STR_METHOD_NAME);
    }
    //ÉIÉvÉVÉáÉìäTéZéÛìnë„ã‡ = this.ÉIÉvÉVÉáÉìäTéZéÛìnë„ã‡ - à¯êî.éÛìnë„ã‡
    public void testSubtractOptionEstimatedNetAmountC001()
    {
        String STR_METHOD_NAME = " testSubtractOptionEstimatedNetAmountC001";
        log.entering(STR_METHOD_NAME);
        
        WEB3IfoTodayOpenOrder l_WEB3IfoTodayOpenOrder = new WEB3IfoTodayOpenOrder(); 
        l_WEB3IfoTodayOpenOrder.optionEstimatedNetAmount = 5200;
        double l_dblNetAmount = 1000;
        l_WEB3IfoTodayOpenOrder.subtractOptionEstimatedNetAmount(l_dblNetAmount);
        assertEquals(4200, l_WEB3IfoTodayOpenOrder.getOptionEstimatedNetAmount(), 0);
        log.exiting(STR_METHOD_NAME);
    }

    //this.åöãÊï™==ÅhîÉåöÅhÇÃèÍçáÅAtrueÇï‘ãpÇ∑ÇÈ
    public void testIsBuyC001()
    {
        String STR_METHOD_NAME = " testIsBuyC001";
        log.entering(STR_METHOD_NAME);
        
        WEB3IfoTodayOpenOrder l_WEB3IfoTodayOpenOrder = new WEB3IfoTodayOpenOrder();
        l_WEB3IfoTodayOpenOrder.contractType = ContractTypeEnum.LONG;
        boolean l_blnReturn = l_WEB3IfoTodayOpenOrder.isBuy();
        assertEquals(true, l_blnReturn);
        
        log.exiting(STR_METHOD_NAME);
    }
    
    //this.åöãÊï™!=ÅhîÉåöÅhÇÃèÍçáÅAfalseÇï‘ãpÇ∑ÇÈÅB
    public void testIsBuyC002()
    {
        String STR_METHOD_NAME = " testIsBuyC002";
        log.entering(STR_METHOD_NAME);
        
        WEB3IfoTodayOpenOrder l_WEB3IfoTodayOpenOrder = new WEB3IfoTodayOpenOrder();
        l_WEB3IfoTodayOpenOrder.contractType = ContractTypeEnum.SHORT;
        boolean l_blnReturn = l_WEB3IfoTodayOpenOrder.isBuy();
        assertEquals(false, l_blnReturn);
        
        log.exiting(STR_METHOD_NAME);
    }
    
    //this.êÊï®Å^ÉIÉvÉVÉáÉìãÊï™ == ÅhêÊï®ÅhÇÃèÍçáÅAtrueÇï‘ãpÇ∑ÇÈ
    public void testIsFuturesC001()
    {
        String STR_METHOD_NAME = " testIsFuturesC001";
        log.entering(STR_METHOD_NAME);
        
        WEB3IfoTodayOpenOrder l_WEB3IfoTodayOpenOrder = new WEB3IfoTodayOpenOrder();
        l_WEB3IfoTodayOpenOrder.ifoProductType = "1";
        boolean l_blnReturn = l_WEB3IfoTodayOpenOrder.isFutures();
        assertEquals(true, l_blnReturn);
        
        log.exiting(STR_METHOD_NAME);
    }
    
    //this.êÊï®Å^ÉIÉvÉVÉáÉìãÊï™ == ÅhÉIÉvÉVÉáÉìÅhÇÃèÍçáÅAfalseÇï‘ãpÇ∑ÇÈ
    public void testIsFuturesC002()
    {
        String STR_METHOD_NAME = " testIsFuturesC002";
        log.entering(STR_METHOD_NAME);
        
        WEB3IfoTodayOpenOrder l_WEB3IfoTodayOpenOrder = new WEB3IfoTodayOpenOrder();
        l_WEB3IfoTodayOpenOrder.ifoProductType = "2";
        boolean l_blnReturn = l_WEB3IfoTodayOpenOrder.isFutures();
        assertEquals(false, l_blnReturn);
        
        log.exiting(STR_METHOD_NAME);
    }
}
@
