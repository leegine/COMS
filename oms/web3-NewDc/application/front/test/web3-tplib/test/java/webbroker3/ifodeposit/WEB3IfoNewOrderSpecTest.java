head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.01.28.19;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3IfoNewOrderSpecTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright           : (äî)ëÂòaëçå§ èÿåîÉ\ÉäÉÖÅ[ÉVÉáÉìÉVÉXÉeÉÄëÊìÒïî
File Name           : WEB3IfoNewOrderSpecTest.java
Author Name         : Daiwa Institute of Research
Revision History    : 2008/08/18 í£è≠åÜ (íÜêu) êVãKçÏê¨
*/

package webbroker3.ifodeposit;

import java.util.Date;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ContractTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccountTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TaxTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Trader;
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
import com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.SettleContractEntry;
import com.fitechlabs.xtrade.plugin.tc.xbifo.DefaultIfoOrderManagerPersistenceEventInterceptor;
import com.fitechlabs.xtrade.plugin.tc.xbifo.IfoDerivativeTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.xbifo.IfoOrderExecutionConditionType;
import com.fitechlabs.xtrade.plugin.tc.xbifo.IfoOrderManagerPersistenceEventInterceptor;
import com.fitechlabs.xtrade.plugin.tc.xbifo.IfoProduct;
import com.fitechlabs.xtrade.plugin.tc.xbifo.IfoProductManager;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoOrderParams;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoOrderRow;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoOrderUnitParams;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoOrderUnitRow;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoProductParams;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoProductRow;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoTradedProductParams;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoTradedProductRow;
import com.fitechlabs.xtrade.plugin.tc.xbifo.ordersubmitter.io.IfoChangeOpenContractOrderSpec;
import com.fitechlabs.xtrade.plugin.tc.xbifo.ordersubmitter.io.IfoOpenContractOrderSpec;
import com.fitechlabs.xtrade.plugin.tc.xbifo.ordersubmitter.io.IfoSettleContractOrderSpec;
import com.fitechlabs.xtrade.plugin.tc.xbifo.stdimpls.IfoTradingModuleImpl;
import test.util.TestDBUtility;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.gentrade.data.TradingTimeParams;
import webbroker3.gentrade.data.TradingTimeRow;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3LogUtility;

public class WEB3IfoNewOrderSpecTest extends TestBaseForMock
{
    WEB3LogUtility
    log = WEB3LogUtility.getInstance(WEB3IfoNewOrderSpecTest.class);
    public WEB3IfoNewOrderSpecTest(String arg0)
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
    //íçï∂ì‡óe==IfoOpenContractOrderSpec(êVãKåö)&&íçï∂éÌï  = = êÊï®êVãKîÉåöíçï∂
    public void testCreateWEB3IfoNewOrderSpec_C001()
    {
        String STR_METHOD_NAME = "testCreateWEB3IfoNewOrderSpec_C001()";
        log.entering(STR_METHOD_NAME);
        try
        {
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
            OrderTypeEnum l_orderTypeEnum = OrderTypeEnum.IDX_FUTURES_BUY_TO_OPEN;
            
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
            
            assertEquals(-1,l_newOrderSpec.orderId );
            assertEquals(-1,l_newOrderSpec.orderUnitId);
            assertEquals(ContractTypeEnum.LONG,l_newOrderSpec.contractType);
            assertEquals(IfoDerivativeTypeEnum.FUTURES,l_newOrderSpec.ifoDerivativeType);
            assertEquals("0005",l_newOrderSpec.underlyingProductCode);
            log.exiting(STR_METHOD_NAME);
            
        }
        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        
    }
    //  íçï∂ì‡óe==IfoOpenContractOrderSpec(í˘ê≥êVãKåö)&&íçï∂éÌï  = = êÊï®êVãKîÑåöíçï∂
    public void testCreateWEB3IfoNewOrderSpec_C002()
    {
        String STR_METHOD_NAME = "testCreateWEB3IfoNewOrderSpec_C002()";
        log.entering(STR_METHOD_NAME);
        try
        {
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
            
            TestDBUtility.deleteAll(IfoOrderUnitRow.TYPE);
            IfoOrderUnitParams l_ifoOrderUnitParams = TestDBUtility.getIfoOrderUnitRow();
            l_ifoOrderUnitParams.setProductId(1006160060005L);
            l_ifoOrderUnitParams.setMarketId(3303);
            l_ifoOrderUnitParams.setOrderType(OrderTypeEnum.IDX_FUTURES_SELL_TO_OPEN);
            l_ifoOrderUnitParams.setLimitPrice(200);
            TestDBUtility.insertWithDel(l_ifoOrderUnitParams);
            
            TestDBUtility.deleteAll(IfoOrderRow.TYPE);
            IfoOrderParams l_ifoOrderParams = TestDBUtility.getIfoOrderRow();
            l_ifoOrderParams.setOrderId(1001);
            l_ifoOrderParams.setAccountId(101001010010L);
            l_ifoOrderParams.setSubAccountId(10100101001007L);
            TestDBUtility.insertWithDel(l_ifoOrderParams);

            IfoOrderManagerPersistenceEventInterceptor l_interceptor = new DefaultIfoOrderManagerPersistenceEventInterceptor();
            OrderTypeEnum l_orderTypeEnum = OrderTypeEnum.IDX_FUTURES_SELL_TO_OPEN;
            
            IfoProductManager l_pm =
                (IfoProductManager) (GtlUtils
                    .getTradingModule(IfoTradingModuleImpl.TRADING_MODULE_NAME)
                    .getProductManager());
            IfoProduct l_product = (IfoProduct)l_pm.getProduct(l_ifoOrderUnitParams.getProductId());
            
            IfoChangeOpenContractOrderSpec l_orderspec =
                new IfoChangeOpenContractOrderSpec(
                        1001,
                        1001,
                        100,
                        200);
            
            WEB3IfoNewOrderSpec l_newOrderSpec = 
                WEB3IfoNewOrderSpec.createWEB3IfoNewOrderSpec(
                l_subAccount,
                l_interceptor,
                l_orderspec,
                l_orderTypeEnum); 
            
            assertEquals(ContractTypeEnum.SHORT,l_newOrderSpec.contractType);
            assertEquals(l_product.getDerivativeType(),l_newOrderSpec.ifoDerivativeType);
            assertEquals(l_product.getUnderlyingProductCode(),l_newOrderSpec.underlyingProductCode);
            log.exiting(STR_METHOD_NAME);
            
        }
        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
    }
    
    //  íçï∂ì‡óe != IfoChangeOpenContractOrderSpec(í˘ê≥êVãKåö) or íçï∂ì‡óe!=IfoOpenContractOrderSpec(êVãKåö)
    public void testCreateWEB3IfoNewOrderSpec_C003()
    {
        String STR_METHOD_NAME = "testCreateWEB3IfoNewOrderSpec_C003()";
        log.entering(STR_METHOD_NAME);
        try
        {
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
            
            TestDBUtility.deleteAll(IfoOrderUnitRow.TYPE);
            IfoOrderUnitParams l_ifoOrderUnitParams = TestDBUtility.getIfoOrderUnitRow();
            l_ifoOrderUnitParams.setProductId(1006160060005L);
            l_ifoOrderUnitParams.setMarketId(3303);
            l_ifoOrderUnitParams.setOrderType(OrderTypeEnum.IDX_FUTURES_SELL_TO_OPEN);
            l_ifoOrderUnitParams.setLimitPrice(200);
            TestDBUtility.insertWithDel(l_ifoOrderUnitParams);
            
            TestDBUtility.deleteAll(IfoOrderRow.TYPE);
            IfoOrderParams l_ifoOrderParams = TestDBUtility.getIfoOrderRow();
            l_ifoOrderParams.setOrderId(1001);
            l_ifoOrderParams.setAccountId(101001010010L);
            l_ifoOrderParams.setSubAccountId(10100101001007L);
            TestDBUtility.insertWithDel(l_ifoOrderParams);

            IfoOrderManagerPersistenceEventInterceptor l_interceptor = new DefaultIfoOrderManagerPersistenceEventInterceptor();
            OrderTypeEnum l_orderTypeEnum = OrderTypeEnum.IDX_FUTURES_SELL_TO_OPEN;
            
            Trader trader = null;
            IfoOrderExecutionConditionType  execType = null; 
            Date orderExpDate = new Date();
            SettleContractEntry entries[] = new SettleContractEntry[1];

            IfoSettleContractOrderSpec l_orderspec =
                IfoSettleContractOrderSpec.createMarketOrderSpec(
                trader,
                entries,
                execType,
                orderExpDate,
                TaxTypeEnum.UNDEFINED);
            
            WEB3IfoNewOrderSpec l_newOrderSpec = 
                WEB3IfoNewOrderSpec.createWEB3IfoNewOrderSpec(
                l_subAccount,
                l_interceptor,
                l_orderspec,
                l_orderTypeEnum); 
            fail();
        }
        catch(WEB3BaseRuntimeException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80017,l_ex.getErrorInfo());
        }
        catch(Exception l_ex)
        {
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    //íçï∂ì‡óe==IfoOpenContractOrderSpec(êVãKåö)&&íçï∂éÌï  = = êÊï®êVãKîÉåöíçï∂
    public void testCreateWEB3IfoNewOrderSpec_C004()
    {
        String STR_METHOD_NAME = "testCreateWEB3IfoNewOrderSpec_C004()";
        log.entering(STR_METHOD_NAME);
        try
        {
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
            l_orderUnitParams.setOrderType(OrderTypeEnum.IDX_OPTIONS_BUY_TO_OPEN);
            TestDBUtility.insertWithDel(l_orderUnitParams);       
            
            WEB3IfoNewOrderSpec l_newOrderSpec = 
                WEB3IfoNewOrderSpec.createWEB3IfoNewOrderSpec(
                l_subAccount,
                l_interceptor,
                l_orderspec,
                l_orderTypeEnum); 
            
            assertEquals(-1,l_newOrderSpec.orderId );
            assertEquals(-1,l_newOrderSpec.orderUnitId);
            assertEquals(ContractTypeEnum.LONG,l_newOrderSpec.contractType);
            assertEquals(IfoDerivativeTypeEnum.FUTURES,l_newOrderSpec.ifoDerivativeType);
            assertEquals("0005",l_newOrderSpec.underlyingProductCode);
            log.exiting(STR_METHOD_NAME);
            
        }
        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        
    }
}
@
