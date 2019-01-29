head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.01.54.34;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3FeqTypeOrderManagerReusableValidationsTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.feq;

import java.sql.Timestamp;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.InstitutionParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.InstitutionRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.ProductParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.ProductRow;
import com.fitechlabs.xtrade.plugin.tc.xbfeq.data.FeqOrderUnitParams;
import com.fitechlabs.xtrade.plugin.tc.xbfeq.data.FeqOrderUnitRow;
import com.fitechlabs.xtrade.plugin.tc.xbfeq.data.FeqProductParams;
import com.fitechlabs.xtrade.plugin.tc.xbfeq.data.FeqProductRow;

import test.util.TestDBUtility;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.gentrade.data.GenCurrencyParams;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3LogUtility;


public class WEB3FeqTypeOrderManagerReusableValidationsTest extends TestBaseForMock
{

    public WEB3FeqTypeOrderManagerReusableValidationsTest(String name)
    {
        super(name);
        // TODO Auto-generated constructor stub
    }

    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3FeqTypeOrderManagerReusableValidationsTest.class);
    
    WEB3FeqTypeOrderManagerReusableValidations l_validations;

    protected void setUp() throws Exception
    {
        super.setUp();
        l_validations = new WEB3FeqTypeOrderManagerReusableValidations();
    }

    protected void tearDown() throws Exception
    {
        super.tearDown();
    }

    public void  testValidateOrderCancelPossibleStatus_C0001()
    {
//        final String STR_METHOD_NAME = "testValidateOrderCancelPossibleStatus_C0001()";
//        log.entering(STR_METHOD_NAME);
//
//        long l_lngOrderId = 123;
//        try
//        {
//            
//            TestDBUtility.deleteAll(FeqOrderUnitParams.TYPE);
//            FeqOrderUnitParams l_feqOrderUnitParams = TestDBUtility.getFeqOrderUnitRow();
//
//            l_feqOrderUnitParams.setOrderId(l_lngOrderId);
//            l_feqOrderUnitParams.setOrderOpenStatus(OrderOpenStatusEnum.OPEN);
//            l_feqOrderUnitParams.setExecutedQuantity(12);
//            l_feqOrderUnitParams.setQuantity(12);
//
//            TestDBUtility.deleteAll(ProductParams.TYPE);
//            ProductParams l_productParams = TestDBUtility.getProductRow();
//            l_productParams.setProductId(l_feqOrderUnitParams.getProductId());
//
//            TestDBUtility.insertWithDel(l_feqOrderUnitParams);
//            
//            WEB3FeqTypeOrderManagerReusableValidations l_orderManagerReusableValidations =
//                new WEB3FeqTypeOrderManagerReusableValidations();
//            l_orderManagerReusableValidations.validateOrderCancelPossibleStatus(
//                l_lngOrderId);
//
//            log.exiting(STR_METHOD_NAME);
//            fail();
//        }
//        catch (WEB3BaseException l_ex)
//        {
//            assertEquals("ñÒíËêîó Ç∆íçï∂êîó ÇÕìØÇ∂Ç≈Ç∑ÅB", l_ex.getErrorMessage());
//            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_03098, l_ex.getErrorInfo());
//            log.exiting(STR_METHOD_NAME);
//        }
    }

//    public void testValidateCurrentFxRate_C0001()
//    {
//        final String STR_METHOD_NAME = "testValidateCurrentFxRate_C0001()";
//        log.entering(STR_METHOD_NAME);
//
//        try
//        {         
//            TestDBUtility.deleteAll(FeqOrderUnitParams.TYPE);
//            FeqOrderUnitParams l_feqOrderUnitParams = TestDBUtility.getFeqOrderUnitRow();
//            l_feqOrderUnitParams.setCurrencyCode("10");
//            l_feqOrderUnitParams.setInstitutionCode("0D");
//            l_feqOrderUnitParams.setProductId(123);
//
//            TestDBUtility.deleteAll(GenCurrencyParams.TYPE);
//            GenCurrencyParams l_genCurrencyParams = TestDBUtility.getGenCurrencyRow();
//            l_genCurrencyParams.setCurrencyCode("10");
//            l_genCurrencyParams.setInstitutionCode("0D");
//            l_genCurrencyParams.setExecRateUpdateTimestamp(GtlUtils.getSystemTimestamp());
//
//            TestDBUtility.deleteAll(FeqProductRow.TYPE);
//            FeqProductParams l_feqProductParams = TestDBUtility.getFeqProductRow();
//            l_feqProductParams.setProductId(123);
//            l_feqProductParams.setCurrencyCode("10");
//            TestDBUtility.insertWithDel(l_feqProductParams);
//
//            TestDBUtility.deleteAll(ProductParams.TYPE);
//            ProductParams l_productParams = TestDBUtility.getProductRow();
//            l_productParams.setProductId(l_feqOrderUnitParams.getProductId());
//
//            TestDBUtility.insertWithDel(l_feqOrderUnitParams);
//            TestDBUtility.insertWithDel(l_productParams);
//
//            TestDBUtility.insertWithDel(l_genCurrencyParams);
//    
//            WEB3FeqOrderUnit l_feqOrderUnit = new WEB3FeqOrderUnit(l_feqOrderUnitParams);
//    
//            WEB3FeqTypeOrderManagerReusableValidations l_orderManagerReusableValidations =
//                new WEB3FeqTypeOrderManagerReusableValidations();
//
//           assertTrue(l_orderManagerReusableValidations.validateCurrentFxRate(l_feqOrderUnit));
//        }
//        catch(Exception l_ex)
//        {
//            log.error(l_ex.getMessage(), l_ex);
//            fail();
//        }
//
//        log.exiting(STR_METHOD_NAME);
//    }

//    public void testValidateCurrentFxRate_C0002()
//    {
//        final String STR_METHOD_NAME = "testValidateCurrentFxRate_C0002()";
//        log.entering(STR_METHOD_NAME);
//
//        try
//        {         
//            TestDBUtility.deleteAll(FeqOrderUnitParams.TYPE);
//            FeqOrderUnitParams l_feqOrderUnitParams = TestDBUtility.getFeqOrderUnitRow();
//            l_feqOrderUnitParams.setCurrencyCode("10");
//            l_feqOrderUnitParams.setInstitutionCode("0D");
//            l_feqOrderUnitParams.setProductId(123);
//
//            TestDBUtility.deleteAll(GenCurrencyParams.TYPE);
//            GenCurrencyParams l_genCurrencyParams = TestDBUtility.getGenCurrencyRow();
//            l_genCurrencyParams.setCurrencyCode("10");
//            l_genCurrencyParams.setInstitutionCode("0D");
//            l_genCurrencyParams.setExecRateUpdateTimestamp(new Date(20081010));
//
//            TestDBUtility.deleteAll(FeqProductRow.TYPE);
//            FeqProductParams l_feqProductParams = TestDBUtility.getFeqProductRow();
//            l_feqProductParams.setProductId(123);
//            l_feqProductParams.setCurrencyCode("10");
//            TestDBUtility.insertWithDel(l_feqProductParams);
//
//            TestDBUtility.deleteAll(ProductParams.TYPE);
//            ProductParams l_productParams = TestDBUtility.getProductRow();
//            l_productParams.setProductId(l_feqOrderUnitParams.getProductId());
//
//            TestDBUtility.insertWithDel(l_feqOrderUnitParams);
//            TestDBUtility.insertWithDel(l_productParams);
//
//            TestDBUtility.insertWithDel(l_genCurrencyParams);
//    
//            WEB3FeqOrderUnit l_feqOrderUnit = new WEB3FeqOrderUnit(l_feqOrderUnitParams);
//    
//            WEB3FeqTypeOrderManagerReusableValidations l_orderManagerReusableValidations =
//                new WEB3FeqTypeOrderManagerReusableValidations();
//
//            assertFalse(l_orderManagerReusableValidations.validateCurrentFxRate(l_feqOrderUnit));
//        }
//        catch(Exception l_ex)
//        {
//            log.error(l_ex.getMessage(), l_ex);
//            fail();
//        }
//
//        log.exiting(STR_METHOD_NAME);
//    }
    
    public void test_ValidateDayExchange_C0001()
    {
        final String STR_METHOD_NAME = "test_ValidateDayExchange_C0001()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            //FeqOrderUnitRow
            TestDBUtility.deleteAll(FeqOrderUnitRow.TYPE);
            FeqOrderUnitParams l_feqOrderUnitParams = TestDBUtility.getFeqOrderUnitRow();
            l_feqOrderUnitParams.setInstitutionCode("0D");
            l_feqOrderUnitParams.setCurrencyCode("001");
            l_feqOrderUnitParams.setProductId(1006169090018L);
            TestDBUtility.insertWithDel(l_feqOrderUnitParams);

            FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
            TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.FOREIGN_EQUITY);
            WEB3FeqOrderManager l_orderMgr = 
                (WEB3FeqOrderManager)l_tradingModule.getOrderManager();
            WEB3FeqOrderUnit l_orderUnit = 
                (WEB3FeqOrderUnit)l_orderMgr.getOrderUnit(l_feqOrderUnitParams.getOrderUnitId());

            //GenCurrencyDao
            GenCurrencyParams l_genCurrencyParams = TestDBUtility.getGenCurrencyRow();
            l_genCurrencyParams.setInstitutionCode("0D");
            l_genCurrencyParams.setCurrencyCode("001");
            l_genCurrencyParams.setExecRateUpdateTimestamp(GtlUtils.getSystemTimestamp());
            TestDBUtility.insertWithDel(l_genCurrencyParams);

            //Product
            TestDBUtility.deleteAll(ProductRow.TYPE);
            ProductParams l_productParams = TestDBUtility.getProductRow();
            l_productParams.setProductId(1006169090018L);
            TestDBUtility.insertWithDel(l_productParams);
            
            //FeqProductRow
            TestDBUtility.deleteAll(FeqProductRow.TYPE);
            FeqProductParams l_feqProductParams = TestDBUtility.getFeqProductRow();
            l_feqProductParams.setProductId(l_feqOrderUnitParams.product_id);
            l_feqProductParams.setCurrencyCode("001");
            TestDBUtility.insertWithDel(l_feqProductParams);
            
            //Institution
            TestDBUtility.deleteAll(InstitutionRow.TYPE);
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            TestDBUtility.insertWithDel(l_institutionParams);

            boolean l_return = l_validations.validateDayExchange(l_orderUnit);
            assertEquals(true, l_return);
            
        }
        catch (Exception e)
        {
            log.error(e.getMessage(), e);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        
    }
    
    public void test_ValidateDayExchange_C0002()
    {
        final String STR_METHOD_NAME = "test_ValidateDayExchange_C0002()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            //FeqOrderUnitRow
            TestDBUtility.deleteAll(FeqOrderUnitRow.TYPE);
            FeqOrderUnitParams l_feqOrderUnitParams = TestDBUtility.getFeqOrderUnitRow();
            l_feqOrderUnitParams.setInstitutionCode("0D");
            l_feqOrderUnitParams.setCurrencyCode("001");
            l_feqOrderUnitParams.setProductId(1006169090018L);
            TestDBUtility.insertWithDel(l_feqOrderUnitParams);

            FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
            TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.FOREIGN_EQUITY);
            WEB3FeqOrderManager l_orderMgr = 
                (WEB3FeqOrderManager)l_tradingModule.getOrderManager();
            WEB3FeqOrderUnit l_orderUnit = 
                (WEB3FeqOrderUnit)l_orderMgr.getOrderUnit(l_feqOrderUnitParams.getOrderUnitId());

            //GenCurrencyDao
            GenCurrencyParams l_genCurrencyParams = TestDBUtility.getGenCurrencyRow();
            l_genCurrencyParams.setInstitutionCode("0D");
            l_genCurrencyParams.setCurrencyCode("001");
            l_genCurrencyParams.setExecRateUpdateTimestamp(new Timestamp(123245646L));
            TestDBUtility.insertWithDel(l_genCurrencyParams);

            //Product
            TestDBUtility.deleteAll(ProductRow.TYPE);
            ProductParams l_productParams = TestDBUtility.getProductRow();
            l_productParams.setProductId(1006169090018L);
            TestDBUtility.insertWithDel(l_productParams);
            
            //FeqProductRow
            TestDBUtility.deleteAll(FeqProductRow.TYPE);
            FeqProductParams l_feqProductParams = TestDBUtility.getFeqProductRow();
            l_feqProductParams.setProductId(l_feqOrderUnitParams.product_id);
            l_feqProductParams.setCurrencyCode("001");
            TestDBUtility.insertWithDel(l_feqProductParams);
            
            //Institution
            TestDBUtility.deleteAll(InstitutionRow.TYPE);
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            TestDBUtility.insertWithDel(l_institutionParams);

            boolean l_return = l_validations.validateDayExchange(l_orderUnit);
            assertEquals(false, l_return);
            
        }
        catch (Exception e)
        {
            log.error(e.getMessage(), e);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        
    }
    
    public void test_ValidateDayExchange_C0003()
    {
        final String STR_METHOD_NAME = "test_ValidateDayExchange_C0002()";
        log.entering(STR_METHOD_NAME);
        
        WEB3FeqOrderUnit l_orderUnit = null;
        try
        {
            boolean l_return = l_validations.validateDayExchange(l_orderUnit);
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80017,l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(),l_ex);
            log.exiting(STR_METHOD_NAME);
        }
        
        log.exiting(STR_METHOD_NAME);
    }
}
@
