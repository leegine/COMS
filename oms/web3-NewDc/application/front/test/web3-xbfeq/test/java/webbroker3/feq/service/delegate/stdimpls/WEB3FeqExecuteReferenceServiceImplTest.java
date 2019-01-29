head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.02.17.22;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3FeqExecuteReferenceServiceImplTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.feq.service.delegate.stdimpls;

import java.util.Date;

import test.util.TestDBUtility;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.feq.WEB3FeqOrderManager;
import webbroker3.feq.WEB3FeqOrderUnit;
import webbroker3.feq.message.WEB3FeqExecuteDetailInfoUnit;
import webbroker3.feq.message.WEB3FeqExecuteGroup;
import webbroker3.feq.message.WEB3FeqExecuteReferenceRequest;
import webbroker3.feq.message.WEB3FeqExecuteReferenceResponse;
import webbroker3.feq.message.WEB3ForeignSortKey;
import webbroker3.gentrade.WEB3GentradeInstitution;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.gentrade.data.GenCurrencyParams;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.plugin.security.oplogin.impl.LoginInfoImplForMock;
import com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinTransactionCateg;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinTransactionType;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TaxTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Trader;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.BranchParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.InstitutionParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MarketParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.ProductParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.SubAccountParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.TraderParams;
import com.fitechlabs.xtrade.plugin.tc.xbfeq.FeqExecutionConditionType;
import com.fitechlabs.xtrade.plugin.tc.xbfeq.data.FeqFinTransactionParams;
import com.fitechlabs.xtrade.plugin.tc.xbfeq.data.FeqOrderExecutionParams;
import com.fitechlabs.xtrade.plugin.tc.xbfeq.data.FeqOrderUnitParams;
import com.fitechlabs.xtrade.plugin.tc.xbfeq.data.FeqProductParams;

public class WEB3FeqExecuteReferenceServiceImplTest extends TestBaseForMock
{
    /**
     * ���O�o�̓��[�e�B���e�B
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3FeqExecuteReferenceServiceImplTest.class);

        
    public WEB3FeqExecuteReferenceServiceImplTest(String arg0)
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

    public void testCreateOrderDetailList()
    {
        final String STR_METHOD_NAME = "testCreateOrderDetailList()";
        log.entering(STR_METHOD_NAME);
        try
        {
            FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
            WEB3FeqExecuteReferenceServiceImplTestForMock l_mockImpl =
                new WEB3FeqExecuteReferenceServiceImplTestForMock();

            //SubAccountParams
            TestDBUtility.deleteAll(SubAccountParams.TYPE);
            SubAccountParams l_accountParams = TestDBUtility.getSubAccountRow();
            TestDBUtility.insertWithDel(l_accountParams);

            //MainAccountParams
            TestDBUtility.deleteAll(MainAccountParams.TYPE);
            MainAccountParams l_mainAccount = TestDBUtility.getMainAccountRow();
            l_mainAccount.setAccountId(l_accountParams.getAccountId());
            TestDBUtility.insertWithDel(l_mainAccount);
            
            //InstitutionParams
            TestDBUtility.deleteAll(InstitutionParams.TYPE);
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            l_institutionParams.setInstitutionId(l_accountParams.getInstitutionId());
            l_institutionParams.setInstitutionCode(l_accountParams.getInstitutionCode());
            TestDBUtility.insertWithDel(l_institutionParams);

            //FeqOrderUnitParams
            TestDBUtility.deleteAll(FeqOrderUnitParams.TYPE);
            FeqOrderUnitParams l_feqOrderUnitParams = TestDBUtility.getFeqOrderUnitRow();
            l_feqOrderUnitParams.setAccountId(l_accountParams.getAccountId());
            l_feqOrderUnitParams.setSubAccountId(l_accountParams.getSubAccountId());
            l_feqOrderUnitParams.setProductType(ProductTypeEnum.FOREIGN_EQUITY);
            l_feqOrderUnitParams.setExecutionConditionType(FeqExecutionConditionType.NONE);
            l_feqOrderUnitParams.setConfirmedQuantity(2);
            l_feqOrderUnitParams.setExecutedQuantity(2);
            l_feqOrderUnitParams.setExecutedAmount(2);
            TestDBUtility.insertWithDel(l_feqOrderUnitParams);

            //branch
            TestDBUtility.deleteAll(BranchParams.TYPE);
            
            //MarketParams
            TestDBUtility.deleteAll(MarketParams.TYPE);
            MarketParams l_marketParams = TestDBUtility.getMarketRow();
            l_marketParams.setMarketId(l_feqOrderUnitParams.getMarketId());
            TestDBUtility.insertWithDel(l_marketParams);
            
            //ProductParams
            TestDBUtility.deleteAll(ProductParams.TYPE);
            ProductParams l_productParams = TestDBUtility.getProductRow();
            l_productParams.setProductId(l_feqOrderUnitParams.getProductId());
            TestDBUtility.insertWithDel(l_productParams);
            
            //FeqProductParams
            TestDBUtility.deleteAll(FeqProductParams.TYPE);
            FeqProductParams l_feqProductParams = TestDBUtility.getFeqProductRow();
            l_feqProductParams.setProductId(l_feqOrderUnitParams.getProductId());
            TestDBUtility.insertWithDel(l_feqProductParams);
            
            WEB3GentradeSubAccount l_subAccount =
                new WEB3GentradeSubAccount(
                    l_accountParams.getAccountId(),
                    l_accountParams.getSubAccountId());

            WEB3FeqExecuteReferenceRequest l_request = new WEB3FeqExecuteReferenceRequest();
            l_request.productCode = "123";
            l_request.marketCode = "101";
            l_request.execType = "0";
            l_request.orderBizDate = new Date(2007-1900, 10, 10);
            l_request.pageIndex = "1";
            l_request.pageSize = "2";

            WEB3FeqExecuteReferenceResponse l_response = new WEB3FeqExecuteReferenceResponse();
            l_mockImpl.createOrderDetailList(l_subAccount, l_request ,l_response);
            WEB3FeqExecuteGroup[] l_groups = l_response.executeGroups;
            assertEquals(1, l_groups.length);
            assertEquals("1", l_groups[0].execPrice);
            log.info(STR_METHOD_NAME + "-------------------->ok");
        }
        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }

    public void testCreateExecuteDetailsInformation()
    {
        final String STR_METHOD_NAME = "testCreateExecuteDetailsInformation()";
        log.entering(STR_METHOD_NAME);
        try
        {
            WEB3FeqExecuteReferenceServiceImplTestForMock l_mockImpl =
                new WEB3FeqExecuteReferenceServiceImplTestForMock();
            
            //FeqOrderUnitParams
            TestDBUtility.deleteAll(FeqOrderUnitParams.TYPE);
            FeqOrderUnitParams l_feqOrderUnitParams = TestDBUtility.getFeqOrderUnitRow();
            l_feqOrderUnitParams.setProductType(ProductTypeEnum.FOREIGN_EQUITY);
            l_feqOrderUnitParams.setExecutionConditionType(FeqExecutionConditionType.NONE);
            l_feqOrderUnitParams.setExecutedQuantity(2);
            l_feqOrderUnitParams.setExecutedAmount(2);
            l_feqOrderUnitParams.setExecutedQuantity(3);
            l_feqOrderUnitParams.setConfirmedQuantity(10);
            TestDBUtility.insertWithDel(l_feqOrderUnitParams);

            //FeqOrderExecutionParams
            TestDBUtility.deleteAll(FeqOrderExecutionParams.TYPE);
            FeqOrderExecutionParams l_executionParams = this.getFeqOrderExecutionParams();
            l_executionParams.setOrderUnitId(l_feqOrderUnitParams.getOrderUnitId());
            l_executionParams.setProductId(l_feqOrderUnitParams.getProductId());
            TestDBUtility.insertWithDel(l_executionParams);

            //FeqFinTransactionParams
            TestDBUtility.deleteAll(FeqFinTransactionParams.TYPE);
            FeqFinTransactionParams l_feqFinTransactionParams = this.getFeqFinTransactionParams();
            l_feqFinTransactionParams.setOrderId(l_executionParams.getOrderId());
            l_feqFinTransactionParams.setOrderUnitId(l_executionParams.getOrderUnitId());
            l_feqFinTransactionParams.setOrderExecutionId(l_executionParams.getOrderExecutionId());
            l_feqFinTransactionParams.setProductId(l_feqOrderUnitParams.getOrderUnitId());
            TestDBUtility.insertWithDel(l_feqFinTransactionParams);
            
            //ProductParams
            TestDBUtility.deleteAll(ProductParams.TYPE);
            ProductParams l_productParams = TestDBUtility.getProductRow();
            l_productParams.setProductId(l_feqOrderUnitParams.getProductId());
            TestDBUtility.insertWithDel(l_productParams);
            
            //FeqProductParams
            TestDBUtility.deleteAll(FeqProductParams.TYPE);
            FeqProductParams l_feqProductParams = TestDBUtility.getFeqProductRow();
            l_feqProductParams.setProductId(l_feqOrderUnitParams.getProductId());
            l_feqProductParams.setInstitutionCode(l_productParams.getInstitutionCode());
            TestDBUtility.insertWithDel(l_feqProductParams);
            
            //CurrencyParams
            TestDBUtility.deleteAll(GenCurrencyParams.TYPE);
            GenCurrencyParams l_currencyParams = TestDBUtility.getGenCurrencyRow();
            l_currencyParams.setInstitutionCode(l_feqProductParams.getInstitutionCode());
            l_currencyParams.setCurrencyCode(l_feqProductParams.getCurrencyCode());
            TestDBUtility.insertWithDel(l_currencyParams);

            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
            TradingModule l_tradingModule =
                l_finApp.getTradingModule(ProductTypeEnum.FOREIGN_EQUITY);

            WEB3FeqOrderManager l_feqOrderManager =
                (WEB3FeqOrderManager)l_tradingModule.getOrderManager();
            WEB3FeqOrderUnit l_carryOrderUnit =
                (WEB3FeqOrderUnit)l_feqOrderManager.toOrderUnit(l_feqOrderUnitParams);
            WEB3FeqExecuteDetailInfoUnit l_infoUnit =
                l_mockImpl.createExecuteDetailsInformation(l_carryOrderUnit);
            
            
            log.info(STR_METHOD_NAME + "-------------------->ok");
        }
        catch(Exception l_exc)
        {
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testcreateQueryString_Case001()
    {
        final String STR_METHOD_NAME = "testcreateQueryString_Case001()";
        log.entering(STR_METHOD_NAME);
        
        WEB3FeqExecuteReferenceServiceImpl l_impl  = new WEB3FeqExecuteReferenceServiceImpl();
        
        String l_strProductCode=null; 
        String l_strMarketCode=null;
        String l_strExecType= "1";
        Date l_datOrderBizDate=null;
        
        String l_strResult = l_impl.createQueryString(l_strProductCode,l_strMarketCode,l_strExecType,l_datOrderBizDate);
        
        assertEquals(l_strResult," and executed_quantity is not null"  + 
                    " and executed_quantity != ?" + 
                    " and executed_quantity < confirmed_quantity" +
                    " and temporary_execution_flag = ?" +
                    " and biz_date >= ?");

        log.exiting(STR_METHOD_NAME);
    }
    
    public void testcreateQueryString_Case002()
    {
        final String STR_METHOD_NAME = "testcreateQueryString_Case002()";
        log.entering(STR_METHOD_NAME);
        
        WEB3FeqExecuteReferenceServiceImpl l_impl  = new WEB3FeqExecuteReferenceServiceImpl();
        
        String l_strProductCode=null; 
        String l_strMarketCode=null;
        String l_strExecType= "3";
        Date l_datOrderBizDate=null;
        
        String l_strResult = l_impl.createQueryString(l_strProductCode,l_strMarketCode,l_strExecType,l_datOrderBizDate);
        
        assertEquals(l_strResult," and executed_quantity is not null"  + 
                    " and executed_quantity != ?" + 
                    " and executed_quantity < confirmed_quantity" +
                    " and temporary_execution_flag = ?" +
                    " and biz_date >= ?");

        log.exiting(STR_METHOD_NAME);
    }
    
    public void testcreateQueryString_Case003()
    {
        final String STR_METHOD_NAME = "testcreateQueryString_Case003()";
        log.entering(STR_METHOD_NAME);
        
        WEB3FeqExecuteReferenceServiceImpl l_impl  = new WEB3FeqExecuteReferenceServiceImpl();
        
        String l_strProductCode=null; 
        String l_strMarketCode=null;
        String l_strExecType= "2";
        Date l_datOrderBizDate=null;
        
        String l_strResult = l_impl.createQueryString(l_strProductCode,l_strMarketCode,l_strExecType,l_datOrderBizDate);
        
        assertEquals(l_strResult," and executed_quantity is not null"  + 
                    " and executed_quantity != ?" + 
                    " and executed_quantity = confirmed_quantity" +
                    " and temporary_execution_flag = ?" +
                    " and biz_date >= ?");

        log.exiting(STR_METHOD_NAME);
    }
    
    public void testcreateQueryString_Case004()
    {
        final String STR_METHOD_NAME = "testcreateQueryString_Case004()";
        log.entering(STR_METHOD_NAME);
        
        WEB3FeqExecuteReferenceServiceImpl l_impl  = new WEB3FeqExecuteReferenceServiceImpl();
        
        String l_strProductCode=null;
        String l_strMarketCode=null;
        String l_strExecType= "4";
        Date l_datOrderBizDate=null;
        
        String l_strResult = l_impl.createQueryString(l_strProductCode,l_strMarketCode,l_strExecType,l_datOrderBizDate);
        
        assertEquals(l_strResult," and executed_quantity is not null"  + 
                    " and executed_quantity != ?" + 
                    " and executed_quantity = confirmed_quantity" +
                    " and temporary_execution_flag = ?" +
                    " and biz_date >= ?");

        log.exiting(STR_METHOD_NAME);
    }

    public void testCreateQueryDataContainer_Case001()
    {
        final String STR_METHOD_NAME = "testCreateQueryDataContainer_Case001()";
        log.entering(STR_METHOD_NAME);
        try
        {
            WEB3FeqExecuteReferenceServiceImpl l_impl  = new WEB3FeqExecuteReferenceServiceImpl();
            String[] l_strReturn = l_impl.createQueryDataContainer(
                null,
                null,
                null,
                "3",
                WEB3DateUtility.getDate("20080201", "yyyyMMdd"));
            assertEquals(3, l_strReturn.length);
            assertEquals("0", l_strReturn[0]);
            assertEquals("1", l_strReturn[1]);
            assertEquals("20080201", l_strReturn[2]);
            
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testCreateQueryDataContainer_Case002()
    {
        final String STR_METHOD_NAME = "testCreateQueryDataContainer_Case002()";
        log.entering(STR_METHOD_NAME);
        try
        {
            WEB3FeqExecuteReferenceServiceImpl l_impl  = new WEB3FeqExecuteReferenceServiceImpl();
            String[] l_strReturn = l_impl.createQueryDataContainer(
                null,
                null,
                null,
                "4",
                WEB3DateUtility.getDate("20080201", "yyyyMMdd"));
            assertEquals(3, l_strReturn.length);
            assertEquals("0", l_strReturn[0]);
            assertEquals("1", l_strReturn[1]);
            assertEquals("20080201", l_strReturn[2]);
            
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testCreateQueryDataContainer_Case003()
    {
        final String STR_METHOD_NAME = "testCreateQueryDataContainer_Case003()";
        log.entering(STR_METHOD_NAME);
        try
        {
            WEB3FeqExecuteReferenceServiceImpl l_impl  = new WEB3FeqExecuteReferenceServiceImpl();
            String[] l_strReturn = l_impl.createQueryDataContainer(
                null,
                null,
                null,
                "1",
                WEB3DateUtility.getDate("20080201", "yyyyMMdd"));
            assertEquals(3, l_strReturn.length);
            assertEquals("0", l_strReturn[0]);
            assertEquals("0", l_strReturn[1]);
            assertEquals("20080201", l_strReturn[2]);
            
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }

    public void testCreateOrderDetailsInformation_Case001()
    {
        final String STR_MEHTOD_NAME = "testCreateOrderDetailsInformation_Case001()";
        log.entering(STR_MEHTOD_NAME);
        try
        {
            //FeqOrderUnitParams
            TestDBUtility.deleteAll(FeqOrderUnitParams.TYPE);
            FeqOrderUnitParams l_feqOrderUnitParams =
                TestDBUtility.getFeqOrderUnitRow();
            l_feqOrderUnitParams.setExecutionConditionType(FeqExecutionConditionType.NONE);
            l_feqOrderUnitParams.setTemporaryExecutionFlag("0");
            l_feqOrderUnitParams.setConfirmedQuantity(200);
            l_feqOrderUnitParams.setExecutedQuantity(100);
            TestDBUtility.insertWithDel(l_feqOrderUnitParams);

            //TraderParams
            TestDBUtility.deleteAll(TraderParams.TYPE);
            TraderParams l_traderParams =
                TestDBUtility.getTraderRow();
            l_traderParams.setLoginId(123456789);
            TestDBUtility.insertWithDel(l_traderParams);

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl", 
                "getLoginInfo",
                new Class[] {},
                new LoginInfoImplForMock());

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.LoginInfoImpl",
                "getLoginId",
                new Class[] {},
                new Long(123456789));

            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
            TradingModule l_tradingModule = 
                l_finApp.getTradingModule(ProductTypeEnum.FOREIGN_EQUITY);
            WEB3FeqOrderManager l_feqOrderManager = 
                (WEB3FeqOrderManager)l_tradingModule.getOrderManager();

            WEB3FeqOrderUnit l_feqOrderUnit =
                (WEB3FeqOrderUnit)l_feqOrderManager.getOrderUnit(
                    l_feqOrderUnitParams.getOrderUnitId());

            WEB3FeqExecuteReferenceServiceImpl l_impl  =
                new WEB3FeqExecuteReferenceServiceImpl();
            WEB3FeqExecuteDetailInfoUnit l_feqExecuteDetailInfoUnit =
                l_impl.createExecuteDetailsInformation(l_feqOrderUnit);
            assertNotNull(l_feqExecuteDetailInfoUnit.execQuantity);
            assertNotNull(l_feqExecuteDetailInfoUnit.execPrice);
            assertNotNull(l_feqExecuteDetailInfoUnit.execType);
            assertNotNull(l_feqExecuteDetailInfoUnit.execDetailList);
            assertNotNull(l_feqExecuteDetailInfoUnit.execExchangeRate);
            assertNotNull(l_feqExecuteDetailInfoUnit.traderCode);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_MEHTOD_NAME);
    }

    public void testCreateOrderDetailsInformation_Case002()
    {
        final String STR_MEHTOD_NAME = "testCreateOrderDetailsInformation_Case002()";
        log.entering(STR_MEHTOD_NAME);
        try
        {
            //FeqOrderUnitParams
            TestDBUtility.deleteAll(FeqOrderUnitParams.TYPE);
            FeqOrderUnitParams l_feqOrderUnitParams =
                TestDBUtility.getFeqOrderUnitRow();
            l_feqOrderUnitParams.setExecutionConditionType(FeqExecutionConditionType.NONE);
            l_feqOrderUnitParams.setTemporaryExecutionFlag("1");
            l_feqOrderUnitParams.setConfirmedQuantity(200);
            l_feqOrderUnitParams.setExecutedQuantity(100);
            TestDBUtility.insertWithDel(l_feqOrderUnitParams);

            //TraderParams
            TestDBUtility.deleteAll(TraderParams.TYPE);
            TraderParams l_traderParams =
                TestDBUtility.getTraderRow();
            l_traderParams.setLoginId(123456789);
            TestDBUtility.insertWithDel(l_traderParams);

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl", 
                "getLoginInfo",
                new Class[] {},
                new LoginInfoImplForMock());

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.LoginInfoImpl",
                "getLoginId",
                new Class[] {},
                new Long(123456789));

            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
            TradingModule l_tradingModule = 
                l_finApp.getTradingModule(ProductTypeEnum.FOREIGN_EQUITY);
            WEB3FeqOrderManager l_feqOrderManager = 
                (WEB3FeqOrderManager)l_tradingModule.getOrderManager();

            WEB3FeqOrderUnit l_feqOrderUnit =
                (WEB3FeqOrderUnit)l_feqOrderManager.getOrderUnit(
                    l_feqOrderUnitParams.getOrderUnitId());

            WEB3FeqExecuteReferenceServiceImpl l_impl  =
                new WEB3FeqExecuteReferenceServiceImpl();
            WEB3FeqExecuteDetailInfoUnit l_feqExecuteDetailInfoUnit =
                l_impl.createExecuteDetailsInformation(l_feqOrderUnit);
            assertNotNull(l_feqExecuteDetailInfoUnit.execQuantity);
            assertNotNull(l_feqExecuteDetailInfoUnit.execPrice);
            assertNotNull(l_feqExecuteDetailInfoUnit.execType);
            assertNotNull(l_feqExecuteDetailInfoUnit.execDetailList);
            assertNull(l_feqExecuteDetailInfoUnit.execExchangeRate);
            assertNull(l_feqExecuteDetailInfoUnit.traderCode);

        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_MEHTOD_NAME);
    }
    
    public void testCreateOrderDetailsInformation_Case003()
    {
        final String STR_MEHTOD_NAME = "testCreateOrderDetailsInformation_Case003()";
        log.entering(STR_MEHTOD_NAME);
        try
        {
            //FeqOrderUnitParams
            TestDBUtility.deleteAll(FeqOrderUnitParams.TYPE);
            FeqOrderUnitParams l_feqOrderUnitParams =
                TestDBUtility.getFeqOrderUnitRow();
            l_feqOrderUnitParams.setExecutionConditionType(FeqExecutionConditionType.NONE);
            l_feqOrderUnitParams.setTemporaryExecutionFlag("1");
            l_feqOrderUnitParams.setConfirmedQuantity(200);
            l_feqOrderUnitParams.setExecutedQuantity(200);
            TestDBUtility.insertWithDel(l_feqOrderUnitParams);

            //TraderParams
            TestDBUtility.deleteAll(TraderParams.TYPE);
            TraderParams l_traderParams =
                TestDBUtility.getTraderRow();
            l_traderParams.setLoginId(123456789);
            TestDBUtility.insertWithDel(l_traderParams);

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl", 
                "getLoginInfo",
                new Class[] {},
                new LoginInfoImplForMock());

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.LoginInfoImpl",
                "getLoginId",
                new Class[] {},
                new Long(123456789));

            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
            TradingModule l_tradingModule = 
                l_finApp.getTradingModule(ProductTypeEnum.FOREIGN_EQUITY);
            WEB3FeqOrderManager l_feqOrderManager = 
                (WEB3FeqOrderManager)l_tradingModule.getOrderManager();

            WEB3FeqOrderUnit l_feqOrderUnit =
                (WEB3FeqOrderUnit)l_feqOrderManager.getOrderUnit(
                    l_feqOrderUnitParams.getOrderUnitId());

            WEB3FeqExecuteReferenceServiceImpl l_impl  =
                new WEB3FeqExecuteReferenceServiceImpl();
            WEB3FeqExecuteDetailInfoUnit l_feqExecuteDetailInfoUnit =
                l_impl.createExecuteDetailsInformation(l_feqOrderUnit);
            assertNotNull(l_feqExecuteDetailInfoUnit.execQuantity);
            assertNotNull(l_feqExecuteDetailInfoUnit.execPrice);
            assertNotNull(l_feqExecuteDetailInfoUnit.execType);
            assertNotNull(l_feqExecuteDetailInfoUnit.execDetailList);
            assertNull(l_feqExecuteDetailInfoUnit.execExchangeRate);
            assertNull(l_feqExecuteDetailInfoUnit.traderCode);

        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_MEHTOD_NAME);
    }

    public FeqOrderExecutionParams getFeqOrderExecutionParams()
    {
        FeqOrderExecutionParams l_params = new FeqOrderExecutionParams();
        //���h�corder_execution_id  NUMBER18    NotNull
        l_params.setOrderExecutionId(123456798l);
        //�����h�caccount_id  NUMBER18    NotNull
        l_params.setAccountId(123456l);
        //�⏕�����h�csub_account_id    NUMBER18    NotNull
        l_params.setSubAccountId(456132l);
        //���X�h�cbranch_id   NUMBER18    NotNull
        l_params.setBranchId(456l);
        //����҂h�ctrader_id  NUMBER18    NULL
        l_params.setTraderId(789l);
        //�����h�corder_id    NUMBER18    NotNull
        l_params.setOrderId(123957l);
        //�����P�ʂh�corder_unit_id NUMBER18    NotNull
        l_params.setOrderUnitId(98763l);
        //�������order_type  NUMBER6 NotNull
        l_params.setOrderType(OrderTypeEnum.ASSET_IN);
        //�����^�C�vproduct_type   NUMBER6 NotNull
        l_params.setProductType(ProductTypeEnum.FOREIGN_EQUITY);
        //�s��h�cmarket_id   NUMBER18    NULL
        //��n��delivery_date    DATE    NULL
        //���n��n��f_delivery_date    DATE    NULL
        //���ʔ�exec_serial_no  NUMBER8 NotNull
        l_params.setExecSerialNo(1);
        //���P��exec_price  DECIMAL18   12  6   NULL
        //�בփ��[�gfx_rate    DECIMAL18   12  6   NotNull
        l_params.setFxRate(123);
        //��萔��exec_quantity   DECIMAL18   12  6   NotNull
        l_params.setExecQuantity(456);
        //������exec_timestamp  DATE    NotNull
        l_params.setExecTimestamp(GtlUtils.getSystemTimestamp());
        //�폜�t���Odelete_flag    NUMBER1 NotNull
        l_params.setDeleteFlag(BooleanEnum.FALSE);
        //������biz_date VARCHAR28   NotNull
        l_params.setBizDate("20070202");
        //�����h�cproduct_id  NUMBER18    NotNull
        l_params.setProductId(658l);
        //���ϋ敪settle_div  VARCHAR21   NULL
        //���ʃR�[�horder_request_number   VARCHAR29   NULL
        //�^�p�R�[�horder_emp_code VARCHAR27   NULL
        //���o�H�敪order_exec_route_div  VARCHAR21   NULL
        //�X�V�҃R�[�hlast_updater  VARCHAR220  NULL
        //�쐬���tcreated_timestamp   DATE    NotNull
        l_params.setCreatedTimestamp(GtlUtils.getSystemTimestamp());
        //�X�V���tlast_updated_timestamp  DATE    NotNull
        l_params.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
        //�������foreign_trade_price DECIMAL18   12  6   NULL
        return l_params;
    }
    
    public FeqFinTransactionParams getFeqFinTransactionParams()
    {
        FeqFinTransactionParams l_params = new FeqFinTransactionParams();
        //�g�����U�N�V�����h�c  fin_transaction_id  NUMBER  18NotNull       
        l_params.setFinTransactionId(123456789l);
        //�����h�c    account_id  NUMBER  18NotNull
        l_params.setAccountId(789465l);
        //�⏕�����h�c  sub_account_id  NUMBER  18NotNull
        l_params.setSubAccountId(7894561L);
        //�����h�c    product_id  NUMBER  18NotNull
        l_params.setProductId(23434l);
        //�g�����U�N�V�����^�C�v fin_transaction_type    NUMBER  6NotNull
        l_params.setFinTransactionType(FinTransactionType.ASSET_IN);
        //�g�����U�N�V�����J�e�S��    fin_transaction_categ   NUMBER  6NotNull
        l_params.setFinTransactionCateg(FinTransactionCateg.ASSET_IN_OUT);
        //�g�����U�N�V������������    fin_transaction_timestamp   DATENotNull 
        l_params.setFinTransactionTimestamp(GtlUtils.getSystemTimestamp());
        //�ŋ敪 tax_type    NUMBER  6NotNull        
        l_params.setTaxType(TaxTypeEnum.NORMAL);
        //���ϋ敪    settle_div  VARCHAR2    1NULL       
        //������ biz_date    VARCHAR2    8NotNull
        l_params.setBizDate("20040202");
        //��n�� delivery_date   DATENotNull
        l_params.setDeliveryDate(GtlUtils.getSystemTimestamp());
        //�ʉ݃R�[�h   currency_code   VARCHAR2    8NotNull    
        l_params.setCurrencyCode("456");
        //��n���    net_amount  DECIMAL 18  12  6   NotNull
        l_params.setNetAmount(798);
        //��n����i�O�݁j    net_amount_fc   DECIMAL 18  12  6   NotNull
        l_params.setNetAmountFc(123);
        //�K�p�בփ��[�g fx_rate DECIMAL 18  12  6   NotNull
        l_params.setFxRate(213);
        //�����^�C�v   product_type    NUMBER  6NotNull
        l_params.setProductType(ProductTypeEnum.FOREIGN_EQUITY);
        //�s��h�c    market_id   NUMBER  18NULL      
        //���P��    price   DECIMAL 18  12  6   NULL        
        //��萔��    quantity    DECIMAL 18  12  6   NotNull
        l_params.setQuantity(12);
        //�����h�c    order_id    NUMBER  18NULL      
        //�����P�ʂh�c  order_unit_id   NUMBER  18NULL      
        //���h�c    order_execution_id  NUMBER  18NULL      
        //�ϑ��萔��   commission_fee  DECIMAL 18  12  6   NotNull
        l_params.setCommissionFee(23);
        //�ϑ��萔�������    commission_fee_tax  DECIMAL 18  12  6   NotNull
        l_params.setCommissionFeeTax(234);
        //�o�^No    reg_no  VARCHAR2    3Null       
        //������ charge_ratio    DECIMAL 18  12  6   Null        
        //���n���Z����i�~�݁j  balance_amount  DECIMAL 18  12  6   NotNull
        l_params.setBalanceAmount(234);
        //�ϑ��萔���i�O�݁j   commission_fee_fc   DECIMAL 18  12  6   NotNull
        l_params.setCommissionFeeFc(324);
        //�ϑ��萔������Łi�O�݁j    commission_fee_tax_fc   DECIMAL 18  12  6   NotNull
        l_params.setCommissionFeeTaxFc(234);
        //���n���Z���  balance_amount_fc   DECIMAL 18  12  6   NotNull
        l_params.setBalanceAmountFc(34);
        //���n�萔��   foreign_commission_fee  DECIMAL 18  12  6   NotNull
        l_params.setForeignCommissionFee(34);
        //���n�����   foreign_tax DECIMAL 18  12  6   NotNull
        l_params.setForeignTax(324);
        //���̑��R�X�g�P foreign_fee_ext1    DECIMAL 18  12  6   NotNull
        l_params.setForeignFeeExt1(234);
        //���̑��R�X�g�Q foreign_fee_ext2    DECIMAL 18  12  6   NotNull
        l_params.setForeignFeeExt2(234);
        //���Y�h�c    asset_id    NUMBER  18NULL      
        //���n�v���z   capital_gain    DECIMAL 18  12  6   NotNull
        l_params.setCapitalGain(324);
        //���n�v�Ŋz   capital_gain_tax    DECIMAL 18  12  6   NotNull
        l_params.setCapitalGainTax(324);
        //���n�v���z�i�O�݁j   capital_gain_fc DECIMAL 18  12  6   NotNull
        l_params.setCapitalGainFc(546);
        //���n�v�Ŋz�i�O�݁j   capital_gain_tax_fc DECIMAL 18  12  6   NotNull
        l_params.setCapitalGainTaxFc(234);
        //���p�ۗL���Y�̊Ǘ���  transfered_asset_mng_fee    DECIMAL 18  12  6   NULL        
        //���p�ۗL���Y�̊Ǘ�������   transfered_asset_mng_fee_tax    DECIMAL 18  12  6   NULL        
        //���p�ۗL���Y�̎萔��  transfered_asset_setup_fee  DECIMAL 18  12  6   NULL        
        //���p�ۗL���Y�̎萔�������   transfered_asset_setup_fee_tax  DECIMAL 18  12  6   NULL        
        //���Y�̕뉿   transfered_asset_book_value DECIMAL 18  12  6   NULL        
        //�폜�t���O   delete_flag NUMBER  1NotNull     
        l_params.setDeleteFlag(BooleanEnum.FALSE);
        //���o�H�敪  order_exec_route_div    VARCHAR2    1NULL       
        //�X�V�҃R�[�h  last_updater    VARCHAR2    20NULL      
        //�쐬���t    created_timestamp   DATENotNull
        l_params.setCreatedTimestamp(GtlUtils.getSystemTimestamp());
        //�X�V���t    last_updated_timestamp  DATENotNull
        l_params.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
        
        return l_params;
    }
    public class WEB3FeqExecuteReferenceServiceImplTestForMock extends WEB3FeqExecuteReferenceServiceImpl
    {
        protected String createQueryString(
            String l_strProductCode, 
            String l_strMarketCode, 
            String l_strExecType, 
            Date l_datOrderBizDate)
        {
            //l_strQueryString
            return null;
        }
        
        protected String[] createQueryDataContainer(
            WEB3GentradeInstitution l_institution, 
            String l_strProductCode, 
            String l_strMarketCode, 
            String l_strExecType, 
            Date l_datOrderBizDate) throws WEB3BaseException
            {
                //l_strQueryDataContainer
                try
                {
                    WEB3GentradeSubAccount l_subAccount =
                        new WEB3GentradeSubAccount(123l,123l);
                }
                catch(Exception l_exc)
                {
                    return null;
                }
                return null;
            }
        protected String createSortCond(WEB3ForeignSortKey[] l_sortKeys) 
        {
            //l_strSortCond
            return null;
        }
        
        public Trader getTrader() throws WEB3SystemLayerException
        {
            try
            {
                WEB3GentradeSubAccount l_subAccount =
                    new WEB3GentradeSubAccount(123l,123l);
            }
            catch(Exception l_exc)
            {
                return null;
            }
            return null;
        }
        protected boolean isChangePossible(WEB3FeqOrderUnit l_orderUnit) 
        {
            return false;
        }
        protected boolean isCancelPossible(WEB3FeqOrderUnit l_orderUnit) 
        {
            return false;
        }
    }

}
@
