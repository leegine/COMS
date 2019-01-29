head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.02.17.57;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3FeqExecutionNotifyUnitServiceImplTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.feq.service.delegate.stdimpls;

import java.lang.reflect.Constructor;
import java.util.Date;
import java.util.List;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccountTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.BranchParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.InstitutionParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MarketParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MarketPreferencesParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.SubAccountParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.SubAccountRow;
import com.fitechlabs.xtrade.plugin.tc.xbfeq.data.FeqFinTransactionParams;
import com.fitechlabs.xtrade.plugin.tc.xbfeq.data.FeqFinTransactionRow;
import com.fitechlabs.xtrade.plugin.tc.xbfeq.data.FeqOrderExecutionParams;
import com.fitechlabs.xtrade.plugin.tc.xbfeq.data.FeqOrderExecutionRow;
import com.fitechlabs.xtrade.plugin.tc.xbfeq.data.FeqOrderUnitParams;
import com.fitechlabs.xtrade.plugin.tc.xbfeq.data.FeqOrderUnitRow;

import test.util.TestDBUtility;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.feq.WEB3FeqOrderManager;
import webbroker3.feq.WEB3FeqOrderUnit;
import webbroker3.feq.data.HostFeqOrderExecNotifyParams;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagementForMock;
import webbroker3.gentrade.data.GenCurrencyParams;
import webbroker3.gentrade.data.InstitutionPreferencesParams;
import webbroker3.mock.TestBaseForMock;
import webbroker3.mock.util.WEB3MockObjectRuntimeException;
import webbroker3.slebase.data.SleRcvdQParams;
import webbroker3.slebase.enums.SleRcvdqProcStatusEnum;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;




public class WEB3FeqExecutionNotifyUnitServiceImplTest extends TestBaseForMock
{
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(
        WEB3FeqExecutionNotifyUnitServiceImplTest.class);

    WEB3FeqExecutionNotifyUnitServiceImpl l_impl = null;
    
    
    public WEB3FeqExecutionNotifyUnitServiceImplTest(String arg0)
    {
        super(arg0);
    }

    protected void setUp() throws Exception
    {
        super.setUp();
        l_impl = new WEB3FeqExecutionNotifyUnitServiceImpl();
        MOCK_MANAGER.setIsMockUsed(true);
    }

    protected void tearDown() throws Exception
    {
        super.tearDown();
    }

    public void testValidateOrderStatus()
    {
        final String STR_METHOD_NAME = " testValidateOrderStatus()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            //feqOrderUnit テーブル
            TestDBUtility.deleteAll(FeqOrderUnitParams.TYPE);
            FeqOrderUnitParams l_params = TestDBUtility.getFeqOrderUnitRow();
            l_params.setConfirmedQuantity(null);
            TestDBUtility.insertWithDel(l_params);
            
            //currency テーブル
            TestDBUtility.deleteAll(GenCurrencyParams.TYPE);
            GenCurrencyParams l_currencyParams = TestDBUtility.getGenCurrencyRow();
            l_currencyParams.setInstitutionCode("0D");
            l_currencyParams.setCurrencyCode("01");
            l_currencyParams.setExecRateUpdateTimestamp(WEB3DateUtility.getDate("20070228", "yyyyMMdd"));
            TestDBUtility.insertWithDel(l_currencyParams);
            
            //feqOrder マネージャ
            WEB3FeqOrderManager l_feqOrderManager = new WEB3FeqOrderManager();
            WEB3FeqOrderUnit l_orderUnit = (WEB3FeqOrderUnit)l_feqOrderManager.getOrderUnit(1234L);
            
            HostFeqOrderExecNotifyParams l_hostFeqOrderExecNotifyParams = this.getHostFeqOrderExecNotifyParams();
            l_hostFeqOrderExecNotifyParams.setBranchCode("sfd");
            l_impl.validateOrderStatus(l_orderUnit, l_hostFeqOrderExecNotifyParams);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.debug(STR_METHOD_NAME + l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            assertEquals(
                WEB3ErrorCatalog.BUSINESS_ERROR_01975.error_code, 
                l_ex.getErrorInfo().getErrorCode());
        }
        catch (Exception l_ex)
        {
            log.debug(STR_METHOD_NAME + l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    public void testValidateOrderStatus_Case001()
    {
        final String STR_METHOD_NAME = "testValidateOrderStatus_Case001()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            //feqOrderUnit テーブル
            TestDBUtility.deleteAll(FeqOrderUnitParams.TYPE);
            FeqOrderUnitParams l_params = TestDBUtility.getFeqOrderUnitRow();
            l_params.setConfirmedQuantity(100);
            l_params.setInstitutionCode("0D");
            TestDBUtility.insertWithDel(l_params);

            //currency テーブル
            TestDBUtility.deleteAll(GenCurrencyParams.TYPE);
            GenCurrencyParams l_currencyParams = TestDBUtility.getGenCurrencyRow();
            l_currencyParams.setInstitutionCode("0D");
            l_currencyParams.setCurrencyCode("01");
            l_currencyParams.setExecRateUpdateTimestamp(WEB3DateUtility.getDate("20070228", "yyyyMMdd"));
            TestDBUtility.insertWithDel(l_currencyParams);
            
            //feqOrder マネージャ
            WEB3FeqOrderManager l_feqOrderManager = new WEB3FeqOrderManager();
            WEB3FeqOrderUnit l_orderUnit = (WEB3FeqOrderUnit)l_feqOrderManager.getOrderUnit(1234L);
            
            HostFeqOrderExecNotifyParams l_hostFeqOrderExecNotifyParams = this.getHostFeqOrderExecNotifyParams();
            l_hostFeqOrderExecNotifyParams.setBranchCode("sfd");
            l_impl.validateOrderStatus(l_orderUnit, l_hostFeqOrderExecNotifyParams);
            log.exiting(TEST_END + STR_METHOD_NAME);

        }
        catch (Exception l_ex)
        {
            log.debug(STR_METHOD_NAME + l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    public void testValidateOrderStatus_case1()
    {
        final String STR_METHOD_NAME = " testValidateOrderStatus_case1()";
        log.entering(TEST_START + STR_METHOD_NAME);

        FeqOrderUnitParams l_feqOrderUnitParams = TestDBUtility.getFeqOrderUnitRow();
        l_feqOrderUnitParams.setExecutionSeqNo(null);
        l_feqOrderUnitParams.setConfirmedQuantity(1D);
        

        GenCurrencyParams l_currencyParams = TestDBUtility.getGenCurrencyRow();
        l_currencyParams.setInstitutionCode(l_feqOrderUnitParams.getInstitutionCode());
        l_currencyParams.setCurrencyCode(l_feqOrderUnitParams.getCurrencyCode());
        

        try
        {
            TestDBUtility.deleteAll(FeqOrderUnitParams.TYPE);

            TestDBUtility.insertWithDel(l_feqOrderUnitParams);
            
            TestDBUtility.deleteAll(GenCurrencyParams.TYPE);
            TestDBUtility.insertWithDel(l_currencyParams);
            
        }
        catch (Exception l_ex)
        {
            log.debug(STR_METHOD_NAME + l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }

        try
        {
            WEB3FeqOrderUnit l_feqOrderUnit = null;

            Constructor l_connstructor =
                WEB3FeqOrderUnit.class.getDeclaredConstructor(new Class[] {FeqOrderUnitRow.class});
            l_connstructor.setAccessible(true);
            l_feqOrderUnit = (WEB3FeqOrderUnit)l_connstructor.newInstance(new Object[] {l_feqOrderUnitParams});
//            l_feqOrderUnit.gete

            HostFeqOrderExecNotifyParams l_hostFeqOrderExecNotifyParams = new HostFeqOrderExecNotifyParams();
            l_hostFeqOrderExecNotifyParams.setExecutionSeqNo(23);
            l_impl = new WEB3FeqExecutionNotifyUnitServiceImpl();

            WEB3GentradeTradingTimeManagementForMock.mockSetTimestamp();
            l_impl.validateOrderStatus(l_feqOrderUnit, l_hostFeqOrderExecNotifyParams);

//            assertNotNull(l_feqOrderUnit);
//            assertEquals(1234L, l_feqOrderUnit.getOrderUnitId());
//            FeqOrderUnit l_feqOrderUnit = new WEB3Feq/OrderUnit(new Long(123L));
//            l_feqOrderUnit.setExecutionSeqNo("1");
            

            try
            {
                TestDBUtility.deleteAll(FeqOrderUnitParams.TYPE);
            }
            catch (Exception l_ex)
            {
                log.debug(STR_METHOD_NAME + l_ex.getMessage(), l_ex);
                log.exiting(TEST_END + STR_METHOD_NAME);
                fail();
            }
        }
        catch (WEB3BaseException l_ex)
        {
            log.debug(STR_METHOD_NAME + l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            assertEquals(
                WEB3ErrorCatalog.BUSINESS_ERROR_01975.error_code, 
                l_ex.getErrorInfo().getErrorCode());
        }
        catch (Exception l_ex)
        {
            log.debug(STR_METHOD_NAME + l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);

    }
        
    
    public void testValidateOrderStatus_case2()
    {
        final String STR_METHOD_NAME = " testValidateOrderStatus_case2()";
        log.entering(TEST_START + STR_METHOD_NAME);

        FeqOrderUnitParams l_feqOrderUnitParams = TestDBUtility.getFeqOrderUnitRow();
        l_feqOrderUnitParams.setExecutionSeqNo(123);
        l_feqOrderUnitParams.setConfirmedQuantity(1D);
        
        GenCurrencyParams l_currencyParams = TestDBUtility.getGenCurrencyRow();
        l_currencyParams.setInstitutionCode(l_feqOrderUnitParams.getInstitutionCode());
        l_currencyParams.setCurrencyCode(l_feqOrderUnitParams.getCurrencyCode());
        l_currencyParams.setExecRateUpdateTimestamp(GtlUtils.getSystemTimestamp());
        
        

        try
        {
            TestDBUtility.deleteAll(FeqOrderUnitParams.TYPE);

            TestDBUtility.insertWithDel(l_feqOrderUnitParams);
            
            TestDBUtility.deleteAll(GenCurrencyParams.TYPE);
            TestDBUtility.insertWithDel(l_currencyParams);
            
        }
        catch (Exception l_ex)
        {
            log.debug(STR_METHOD_NAME + l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }

        try
        {
            WEB3FeqOrderUnit l_feqOrderUnit = null;

            Constructor l_connstructor =
                WEB3FeqOrderUnit.class.getDeclaredConstructor(new Class[] {FeqOrderUnitRow.class});
            l_connstructor.setAccessible(true);
            l_feqOrderUnit = (WEB3FeqOrderUnit)l_connstructor.newInstance(new Object[] {l_feqOrderUnitParams});

            HostFeqOrderExecNotifyParams l_hostFeqOrderExecNotifyParams = new HostFeqOrderExecNotifyParams();
            l_hostFeqOrderExecNotifyParams.setExecutionSeqNo(100);
            l_impl = new WEB3FeqExecutionNotifyUnitServiceImpl();

            l_impl.validateOrderStatus(l_feqOrderUnit, l_hostFeqOrderExecNotifyParams);


            TestDBUtility.deleteAll(FeqOrderUnitParams.TYPE);
            fail();

        }
        catch(WEB3BusinessLayerException l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02891, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.debug(STR_METHOD_NAME + l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);

    }
    public void testValidateOrderStatus_case3()
    {
        final String STR_METHOD_NAME = " testValidateOrderStatus_case3()";
        log.entering(TEST_START + STR_METHOD_NAME);

        FeqOrderUnitParams l_feqOrderUnitParams = TestDBUtility.getFeqOrderUnitRow();
        l_feqOrderUnitParams.setExecutionSeqNo(123);
        l_feqOrderUnitParams.setConfirmedQuantity(1D);
        
        GenCurrencyParams l_currencyParams = TestDBUtility.getGenCurrencyRow();
        l_currencyParams.setInstitutionCode(l_feqOrderUnitParams.getInstitutionCode());
        l_currencyParams.setCurrencyCode(l_feqOrderUnitParams.getCurrencyCode());
        l_currencyParams.setExecRateUpdateTimestamp(GtlUtils.getSystemTimestamp());
        
        

        try
        {
            TestDBUtility.deleteAll(FeqOrderUnitParams.TYPE);

            TestDBUtility.insertWithDel(l_feqOrderUnitParams);
            
            TestDBUtility.deleteAll(GenCurrencyParams.TYPE);
            TestDBUtility.insertWithDel(l_currencyParams);
            
        }
        catch (Exception l_ex)
        {
            log.debug(STR_METHOD_NAME + l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }

        try
        {
            WEB3FeqOrderUnit l_feqOrderUnit = null;

            Constructor l_connstructor =
                WEB3FeqOrderUnit.class.getDeclaredConstructor(new Class[] {FeqOrderUnitRow.class});
            l_connstructor.setAccessible(true);
            l_feqOrderUnit = (WEB3FeqOrderUnit)l_connstructor.newInstance(new Object[] {l_feqOrderUnitParams});

            HostFeqOrderExecNotifyParams l_hostFeqOrderExecNotifyParams = new HostFeqOrderExecNotifyParams();
            l_hostFeqOrderExecNotifyParams.setExecutionSeqNo(123);
            l_impl = new WEB3FeqExecutionNotifyUnitServiceImpl();

            l_impl.validateOrderStatus(l_feqOrderUnit, l_hostFeqOrderExecNotifyParams);


            TestDBUtility.deleteAll(FeqOrderUnitParams.TYPE);
            fail();

        }
        catch(WEB3BusinessLayerException l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02891, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.debug(STR_METHOD_NAME + l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);

    }
    public void testValidateOrderStatus_case4()
    {
        final String STR_METHOD_NAME = " testValidateOrderStatus_case4()";
        log.entering(TEST_START + STR_METHOD_NAME);

        FeqOrderUnitParams l_feqOrderUnitParams = TestDBUtility.getFeqOrderUnitRow();
        l_feqOrderUnitParams.setExecutionSeqNo(123);
        l_feqOrderUnitParams.setConfirmedQuantity(1D);
        
        GenCurrencyParams l_currencyParams = TestDBUtility.getGenCurrencyRow();
        l_currencyParams.setInstitutionCode(l_feqOrderUnitParams.getInstitutionCode());
        l_currencyParams.setCurrencyCode(l_feqOrderUnitParams.getCurrencyCode());
        l_currencyParams.setExecRateUpdateTimestamp(GtlUtils.getSystemTimestamp());
        
        

        try
        {
            TestDBUtility.deleteAll(FeqOrderUnitParams.TYPE);

            TestDBUtility.insertWithDel(l_feqOrderUnitParams);
            
            TestDBUtility.deleteAll(GenCurrencyParams.TYPE);
            TestDBUtility.insertWithDel(l_currencyParams);
            
        }
        catch (Exception l_ex)
        {
            log.debug(STR_METHOD_NAME + l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }

        try
        {
            WEB3FeqOrderUnit l_feqOrderUnit = null;

            Constructor l_connstructor =
                WEB3FeqOrderUnit.class.getDeclaredConstructor(new Class[] {FeqOrderUnitRow.class});
            l_connstructor.setAccessible(true);
            l_feqOrderUnit = (WEB3FeqOrderUnit)l_connstructor.newInstance(new Object[] {l_feqOrderUnitParams});

            HostFeqOrderExecNotifyParams l_hostFeqOrderExecNotifyParams = new HostFeqOrderExecNotifyParams();
            
            l_hostFeqOrderExecNotifyParams.setExecutionSeqNo(999);
            
            l_impl = new WEB3FeqExecutionNotifyUnitServiceImpl();
            l_impl.validateOrderStatus(l_feqOrderUnit, l_hostFeqOrderExecNotifyParams);


//            TestDBUtility.deleteAll(FeqOrderUnitParams.TYPE);

        }
        catch(WEB3BusinessLayerException l_ex)
        {
            log.debug(STR_METHOD_NAME + l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        catch (Exception l_ex)
        {
            log.debug(STR_METHOD_NAME + l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);

    }

    public void testValidateOrderStatus_case5()
    {
        final String STR_METHOD_NAME = " testValidateOrderStatus_case5()";
        log.entering(TEST_START + STR_METHOD_NAME);

        FeqOrderUnitParams l_feqOrderUnitParams = TestDBUtility.getFeqOrderUnitRow();
        l_feqOrderUnitParams.setExecutionSeqNo(900);
        l_feqOrderUnitParams.setConfirmedQuantity(1D);
        
        GenCurrencyParams l_currencyParams = TestDBUtility.getGenCurrencyRow();
        l_currencyParams.setInstitutionCode(l_feqOrderUnitParams.getInstitutionCode());
        l_currencyParams.setCurrencyCode(l_feqOrderUnitParams.getCurrencyCode());
        l_currencyParams.setExecRateUpdateTimestamp(GtlUtils.getSystemTimestamp());
        
        

        try
        {
            TestDBUtility.deleteAll(FeqOrderUnitParams.TYPE);

            TestDBUtility.insertWithDel(l_feqOrderUnitParams);
            
            TestDBUtility.deleteAll(GenCurrencyParams.TYPE);
            TestDBUtility.insertWithDel(l_currencyParams);
            
        }
        catch (Exception l_ex)
        {
            log.debug(STR_METHOD_NAME + l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }

        try
        {
            WEB3FeqOrderUnit l_feqOrderUnit = null;

            Constructor l_connstructor =
                WEB3FeqOrderUnit.class.getDeclaredConstructor(new Class[] {FeqOrderUnitRow.class});
            l_connstructor.setAccessible(true);
            l_feqOrderUnit = (WEB3FeqOrderUnit)l_connstructor.newInstance(new Object[] {l_feqOrderUnitParams});

            HostFeqOrderExecNotifyParams l_hostFeqOrderExecNotifyParams = new HostFeqOrderExecNotifyParams();
            
            l_hostFeqOrderExecNotifyParams.setExecutionSeqNo(1000);
            
            l_impl = new WEB3FeqExecutionNotifyUnitServiceImpl();
            l_impl.validateOrderStatus(l_feqOrderUnit, l_hostFeqOrderExecNotifyParams);


//            TestDBUtility.deleteAll(FeqOrderUnitParams.TYPE);

        }
        catch(WEB3BusinessLayerException l_ex)
        {
            log.debug(STR_METHOD_NAME + l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        catch (Exception l_ex)
        {
            log.debug(STR_METHOD_NAME + l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);

    }
        
    
    public HostFeqOrderExecNotifyParams getHostFeqOrderExecNotifyParams()
    {
        HostFeqOrderExecNotifyParams l_params = new HostFeqOrderExecNotifyParams();
        
        return l_params;
    }
    public void testNotifyOrder_case1()
    {
        final String STR_METHOD_NAME = " testNotifyOrder_case1()";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {
            TestDBUtility.deleteAll(FeqOrderUnitParams.TYPE);
            FeqOrderUnitParams l_params = TestDBUtility.getFeqOrderUnitRow();
            l_params.setExecutionSeqNo(111);
            TestDBUtility.insertWithDel(l_params);
            
            WEB3FeqOrderManager l_feqOrderManager = new WEB3FeqOrderManager();
            WEB3FeqOrderUnit l_orderUnit = (WEB3FeqOrderUnit)l_feqOrderManager.getOrderUnit(1234L);
            
            HostFeqOrderExecNotifyParams l_hostParams = this.getHostFeqOrderExecNotifyParams();
            l_hostParams.setExecSerialNo("111");
            
            String l_strFeqInputRouteDiv = "0";
            
            
            
//            l_impl.notifyOrder(l_orderUnit, l_hostParams, l_strFeqInputRouteDiv);
            
        }
        catch(WEB3BusinessLayerException l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            log.error(l_ex.getMessage(),l_ex);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02891, l_ex.getErrorInfo());
        }
        catch(Exception l_ex)
        {
            log.exiting(TEST_END +STR_METHOD_NAME);
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testNotifyOrder_Case001()
    {
        final String STR_METHOD_NAME = "testNotifyOrder_Case001()";
        log.entering(STR_METHOD_NAME);
        try
        {
            //FeqOrderUnitParams
            TestDBUtility.deleteAll(FeqOrderUnitParams.TYPE);
            FeqOrderUnitParams l_feqOrderUnitParams =
                TestDBUtility.getFeqOrderUnitRow();
            l_feqOrderUnitParams.setConfirmedQuantity(null);
            TestDBUtility.insertWithDel(l_feqOrderUnitParams);

            //SleRcvdQParams
            TestDBUtility.deleteAll(SleRcvdQParams.TYPE);
            SleRcvdQParams l_sleRvcdQParams =
                TestDBUtility.getSleRcvdQRow();
            l_sleRvcdQParams.setRouteDiv("0");
            l_sleRvcdQParams.setStatus(SleRcvdqProcStatusEnum.TODO);
            TestDBUtility.insertWithDel(l_sleRvcdQParams);
            
            FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
            WEB3FeqOrderManager l_orderManager =
                (WEB3FeqOrderManager)l_finApp.getTradingModule(
                ProductTypeEnum.FOREIGN_EQUITY).getOrderManager();
            WEB3FeqOrderUnit l_feqOrderUnit =
                (WEB3FeqOrderUnit)l_orderManager.getOrderUnit(
                    l_feqOrderUnitParams.getOrderUnitId());

            //HostFeqOrderExecNotifyParams
            TestDBUtility.deleteAll(HostFeqOrderExecNotifyParams.TYPE);
            HostFeqOrderExecNotifyParams l_hostParams = this.getHostFeqOrderExecNotifyParams();
            l_hostParams.setExecSerialNo("111");
            l_hostParams.setExecutionSeqNo(111);
            l_hostParams.setOrderBizDate(GtlUtils.getSystemTimestamp());
            l_hostParams.setOrderEmpCode("120");
            TestDBUtility.insertWithDel(l_hostParams);

            l_impl.notifyOrder(l_feqOrderUnit, l_hostParams, l_sleRvcdQParams,Boolean.TRUE);
            fail();
        }
        catch (WEB3BusinessLayerException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_01975, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testNotifyOrder_Case002()
    {
        final String STR_METHOD_NAME = "testNotifyOrder_Case002()";
        log.entering(STR_METHOD_NAME);
        try
        {
            //FeqOrderUnitParams
            TestDBUtility.deleteAll(FeqOrderUnitParams.TYPE);
            FeqOrderUnitParams l_feqOrderUnitParams =
                TestDBUtility.getFeqOrderUnitRow();
            l_feqOrderUnitParams.setConfirmedQuantity(100);
            l_feqOrderUnitParams.setExecutionSeqNo(100);
            l_feqOrderUnitParams.setOrderType(OrderTypeEnum.FEQ_BUY);
            l_feqOrderUnitParams.setConfirmedPrice(100);
            l_feqOrderUnitParams.setExecEndTimestamp(null);
            TestDBUtility.insertWithDel(l_feqOrderUnitParams);

            //SleRcvdQParams
            TestDBUtility.deleteAll(SleRcvdQParams.TYPE);
            SleRcvdQParams l_sleRvcdQParams =
                TestDBUtility.getSleRcvdQRow();
            l_sleRvcdQParams.setRouteDiv("1");
            l_sleRvcdQParams.setStatus(SleRcvdqProcStatusEnum.TODO);
            TestDBUtility.insertWithDel(l_sleRvcdQParams);
            
            FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
            WEB3FeqOrderManager l_orderManager =
                (WEB3FeqOrderManager)l_finApp.getTradingModule(
                ProductTypeEnum.FOREIGN_EQUITY).getOrderManager();
            WEB3FeqOrderUnit l_feqOrderUnit =
                (WEB3FeqOrderUnit)l_orderManager.getOrderUnit(
                    l_feqOrderUnitParams.getOrderUnitId());

            //HostFeqOrderExecNotifyParams
            TestDBUtility.deleteAll(HostFeqOrderExecNotifyParams.TYPE);
            HostFeqOrderExecNotifyParams l_hostParams = this.getHostFeqOrderExecNotifyParams();
            l_hostParams.setExecSerialNo("111");
            l_hostParams.setExecutionSeqNo(120);
            l_hostParams.setOrderBizDate(GtlUtils.getSystemTimestamp());
            l_hostParams.setOrderEmpCode("120");
            l_hostParams.setExecPrice(200);
            TestDBUtility.insertWithDel(l_hostParams);

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.feq.WEB3FeqOrderManager",
                "validateExecutionDate",
                new Class[] { WEB3FeqOrderUnit.class, Date.class }, null);

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.feq.WEB3FeqOrderManager",
                "validateExecutedQuantity",
                new Class[] {WEB3FeqOrderUnit.class, double.class}, null);

            l_impl.notifyOrder(l_feqOrderUnit, l_hostParams, l_sleRvcdQParams,Boolean.TRUE);
            fail();
        }
        catch (WEB3BusinessLayerException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02100, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }

    public void testNotifyOrder_Case003()
    {
        final String STR_METHOD_NAME = "testNotifyOrder_Case003()";
        log.entering(STR_METHOD_NAME);
        try
        {
            //FeqOrderUnitParams
            TestDBUtility.deleteAll(FeqOrderUnitParams.TYPE);
            FeqOrderUnitParams l_feqOrderUnitParams =
                TestDBUtility.getFeqOrderUnitRow();
            l_feqOrderUnitParams.setConfirmedQuantity(100);
            l_feqOrderUnitParams.setExecutionSeqNo(100);
            l_feqOrderUnitParams.setOrderType(OrderTypeEnum.FEQ_BUY);
            l_feqOrderUnitParams.setConfirmedPrice(100);
            l_feqOrderUnitParams.setExecEndTimestamp(null);
            TestDBUtility.insertWithDel(l_feqOrderUnitParams);

            //SleRcvdQParams
            TestDBUtility.deleteAll(SleRcvdQParams.TYPE);
            SleRcvdQParams l_sleRvcdQParams =
                TestDBUtility.getSleRcvdQRow();
            l_sleRvcdQParams.setRouteDiv("1");
            l_sleRvcdQParams.setStatus(SleRcvdqProcStatusEnum.TODO);
            TestDBUtility.insertWithDel(l_sleRvcdQParams);
            
            FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
            WEB3FeqOrderManager l_orderManager =
                (WEB3FeqOrderManager)l_finApp.getTradingModule(
                ProductTypeEnum.FOREIGN_EQUITY).getOrderManager();
            WEB3FeqOrderUnit l_feqOrderUnit =
                (WEB3FeqOrderUnit)l_orderManager.getOrderUnit(
                    l_feqOrderUnitParams.getOrderUnitId());

            //HostFeqOrderExecNotifyParams
            TestDBUtility.deleteAll(HostFeqOrderExecNotifyParams.TYPE);
            HostFeqOrderExecNotifyParams l_hostParams = this.getHostFeqOrderExecNotifyParams();
            l_hostParams.setExecSerialNo("111");
            l_hostParams.setExecutionSeqNo(120);
            l_hostParams.setOrderBizDate(GtlUtils.getSystemTimestamp());
            l_hostParams.setOrderEmpCode("120");
            l_hostParams.setExecPrice(200);
            TestDBUtility.insertWithDel(l_hostParams);

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.feq.WEB3FeqOrderManager",
                "validateExecutionDate",
                new Class[] { WEB3FeqOrderUnit.class, Date.class }, null);

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.feq.WEB3FeqOrderManager",
                "validateExecutedQuantity",
                new Class[] {WEB3FeqOrderUnit.class, double.class}, null);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.feq.WEB3FeqOrderManager",
                "validateExecutedPrice",
                new Class[] {WEB3FeqOrderUnit.class, double.class}, null);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.feq.WEB3FeqOrderManager",
                "validateFDeliveryDate",
                new Class[] {WEB3FeqOrderUnit.class, Date.class}, null);

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.feq.WEB3FeqOrderManager",
                "updateEstimatedPrice",
                new Class[] {WEB3FeqOrderUnit.class, Date.class}, null);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.feq.WEB3FeqPositionManager",
                "updateTransaction",
                new Class[] {long.class, boolean.class}, null);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.feq.WEB3FeqOrderManager",
                "executeChangeCancelOrderRejected",
                new Class[] {long.class},
                new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                    "テーブルに該当するデータがありません。"));

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.feq.service.delegate.stdimpls.WEB3FeqOrderAndExecutionUpdateServiceImpl",
                "updateExecuteUnit",
                new Class[]{HostFeqOrderExecNotifyParams.class}, null);
            
            l_impl.notifyOrder(l_feqOrderUnit, l_hostParams, l_sleRvcdQParams,Boolean.TRUE);
            fail();
        }
        catch (WEB3MockObjectRuntimeException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            WEB3SystemLayerException l_sex =
                (WEB3SystemLayerException)l_ex.getCause();
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80005, l_sex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testNotifyOrder_Case004()
    {
        final String STR_METHOD_NAME = "testNotifyOrder_Case004()";
        log.entering(STR_METHOD_NAME);
        try
        {
            //FeqOrderUnitParams
            TestDBUtility.deleteAll(FeqOrderUnitParams.TYPE);
            FeqOrderUnitParams l_feqOrderUnitParams =
                TestDBUtility.getFeqOrderUnitRow();
            l_feqOrderUnitParams.setConfirmedQuantity(100);
            l_feqOrderUnitParams.setExecutionSeqNo(100);
            l_feqOrderUnitParams.setOrderType(OrderTypeEnum.FEQ_BUY);
            l_feqOrderUnitParams.setConfirmedPrice(100);
            l_feqOrderUnitParams.setExecEndTimestamp(null);
            TestDBUtility.insertWithDel(l_feqOrderUnitParams);

            //SleRcvdQParams
            TestDBUtility.deleteAll(SleRcvdQParams.TYPE);
            SleRcvdQParams l_sleRvcdQParams =
                TestDBUtility.getSleRcvdQRow();
            l_sleRvcdQParams.setRouteDiv("1");
            l_sleRvcdQParams.setStatus(SleRcvdqProcStatusEnum.TODO);
            TestDBUtility.insertWithDel(l_sleRvcdQParams);
            
            FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
            WEB3FeqOrderManager l_orderManager =
                (WEB3FeqOrderManager)l_finApp.getTradingModule(
                ProductTypeEnum.FOREIGN_EQUITY).getOrderManager();
            WEB3FeqOrderUnit l_feqOrderUnit =
                (WEB3FeqOrderUnit)l_orderManager.getOrderUnit(
                    l_feqOrderUnitParams.getOrderUnitId());

            //SubAccount
            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            SubAccountParams l_subAccount = TestDBUtility.getSubAccountRow();
            l_subAccount.setSubAccountId(l_feqOrderUnitParams.getSubAccountId());
            l_subAccount.setAccountId(l_feqOrderUnitParams.getAccountId());
            TestDBUtility.insertWithDel(l_subAccount);
            
            //HostFeqOrderExecNotifyParams
            TestDBUtility.deleteAll(HostFeqOrderExecNotifyParams.TYPE);
            HostFeqOrderExecNotifyParams l_hostParams = this.getHostFeqOrderExecNotifyParams();
            l_hostParams.setExecSerialNo("111");
            l_hostParams.setExecutionSeqNo(120);
            l_hostParams.setOrderBizDate(GtlUtils.getSystemTimestamp());
            l_hostParams.setOrderEmpCode("120");
            l_hostParams.setExecPrice(200);
            TestDBUtility.insertWithDel(l_hostParams);

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.feq.WEB3FeqOrderManager",
                "validateExecutionDate",
                new Class[] { WEB3FeqOrderUnit.class, Date.class }, null);

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.feq.WEB3FeqOrderManager",
                "validateExecutedQuantity",
                new Class[] {WEB3FeqOrderUnit.class, double.class}, null);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.feq.WEB3FeqOrderManager",
                "validateExecutedPrice",
                new Class[] {WEB3FeqOrderUnit.class, double.class}, null);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.feq.WEB3FeqOrderManager",
                "validateFDeliveryDate",
                new Class[] {WEB3FeqOrderUnit.class, Date.class}, null);

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.feq.WEB3FeqOrderManager",
                "updateEstimatedPrice",
                new Class[] {WEB3FeqOrderUnit.class, Date.class}, null);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.feq.WEB3FeqOrderManager",
                "executeChangeCancelOrderRejected",
                new Class[] {long.class}, null);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.feq.WEB3FeqPositionManager",
                "updateTransaction",
                new Class[] {long.class, boolean.class}, null);

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.feq.service.delegate.stdimpls.WEB3FeqOrderAndExecutionUpdateServiceImpl",
                "updateExecuteUnit",
                new Class[]{HostFeqOrderExecNotifyParams.class}, null);

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.tradingpower.WEB3TPTradingPowerServiceImpl",
                "reCalcTradingPower",
                new Class[] {WEB3GentradeSubAccount.class}, null);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.tradingpower.WEB3TPTradingPowerServiceImpl",
                "reCalcTradingPower",
                new Class[] {WEB3GentradeSubAccount.class}, null);
            
            l_impl.notifyOrder(l_feqOrderUnit, l_hostParams, l_sleRvcdQParams,Boolean.TRUE);
            
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }

    public void testNotifyOrder_Case005()
    {
        final String STR_METHOD_NAME = "testNotifyOrder_Case005()";
        log.entering(STR_METHOD_NAME);
        try
        {
            TestDBUtility.deleteAll(MarketParams.TYPE);
            MarketParams l_MarketParams = TestDBUtility.getMarketRow();
            TestDBUtility.insertWithDel(l_MarketParams);
            //FeqOrderUnitParams
            TestDBUtility.deleteAll(FeqOrderUnitParams.TYPE);
            FeqOrderUnitParams l_feqOrderUnitParams =
                TestDBUtility.getFeqOrderUnitRow();
            l_feqOrderUnitParams.setConfirmedQuantity(100);
            l_feqOrderUnitParams.setExecutionSeqNo(100);
            l_feqOrderUnitParams.setOrderType(OrderTypeEnum.FEQ_BUY);
            l_feqOrderUnitParams.setConfirmedPrice(100);
            l_feqOrderUnitParams.setExecEndTimestamp(null);
            l_feqOrderUnitParams.setExecutedAmount(123.45);
            TestDBUtility.insertWithDel(l_feqOrderUnitParams);

            //SleRcvdQParams
            TestDBUtility.deleteAll(SleRcvdQParams.TYPE);
            SleRcvdQParams l_sleRvcdQParams =
                TestDBUtility.getSleRcvdQRow();
            l_sleRvcdQParams.setRouteDiv("1");
            l_sleRvcdQParams.setStatus(SleRcvdqProcStatusEnum.EXEC_PROCESSING);
            TestDBUtility.insertWithDel(l_sleRvcdQParams);
            
            TestDBUtility.deleteAll(MainAccountParams.TYPE);
            MainAccountParams l_MainAccountParams = TestDBUtility.getMainAccountRow();
            l_MainAccountParams.setAccountId(l_feqOrderUnitParams.getAccountId());
            TestDBUtility.insertWithDel(l_MainAccountParams);
            
            //SubAccount
            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            SubAccountParams l_subAccount = TestDBUtility.getSubAccountRow();
            l_subAccount.setSubAccountId(l_feqOrderUnitParams.getSubAccountId());
            l_subAccount.setAccountId(l_feqOrderUnitParams.getAccountId());
            TestDBUtility.insertWithDel(l_subAccount);
            
            FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
            WEB3FeqOrderManager l_orderManager =
                (WEB3FeqOrderManager)l_finApp.getTradingModule(
                ProductTypeEnum.FOREIGN_EQUITY).getOrderManager();
            WEB3FeqOrderUnit l_feqOrderUnit =
                (WEB3FeqOrderUnit)l_orderManager.getOrderUnit(
                    l_feqOrderUnitParams.getOrderUnitId());

            //HostFeqOrderExecNotifyParams
            TestDBUtility.deleteAll(HostFeqOrderExecNotifyParams.TYPE);
            HostFeqOrderExecNotifyParams l_hostParams = this.getHostFeqOrderExecNotifyParams();
            l_hostParams.setExecSerialNo("111");
            l_hostParams.setExecutionSeqNo(120);
            l_hostParams.setOrderBizDate(GtlUtils.getSystemTimestamp());
            l_hostParams.setOrderEmpCode("120");
            l_hostParams.setExecPrice(200);
            l_hostParams.setFxRate(10);
            TestDBUtility.insertWithDel(l_hostParams);
            
            //FeqOrderExecutionParams
            TestDBUtility.deleteAll(FeqOrderExecutionParams.TYPE);
            FeqOrderExecutionParams l_feqOrderExecutionParams =
                TestDBUtility.getFeqOrderExecutionParams();
            l_feqOrderExecutionParams.setOrderUnitId(l_feqOrderUnitParams.getOrderUnitId());
            l_feqOrderExecutionParams.setFxRate(11);
            TestDBUtility.insertWithDel(l_feqOrderExecutionParams);

            //FeqFinTransactionParams
            TestDBUtility.deleteAll(FeqFinTransactionParams.TYPE);
            FeqFinTransactionParams l_feqFinTransactionParams =
                TestDBUtility.getFeqFinTransactionParams();
            l_feqFinTransactionParams.setOrderId(l_feqOrderUnitParams.getOrderId());
            l_feqFinTransactionParams.setOrderUnitId(l_feqOrderUnitParams.getOrderUnitId());
            l_feqFinTransactionParams.setDeleteFlag(BooleanEnum.FALSE);
            l_feqFinTransactionParams.setFxRate(11);
            TestDBUtility.insertWithDel(l_feqFinTransactionParams);

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.feq.WEB3FeqPositionManager",
                "updateTransaction",
                new Class[] {long.class, boolean.class}, null);

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.feq.WEB3FeqOrderManager",
                "updateEstimatedPrice",
                new Class[] {WEB3FeqOrderUnit.class, Date.class}, null);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.feq.WEB3FeqOrderManager",
                "updateExecutedAmountYen",
                new Class[] {long.class}, null);

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.tradingpower.WEB3TPTradingPowerServiceImpl",
                "reCalcTradingPower",
                new Class[] {WEB3GentradeSubAccount.class}, null);

            l_impl.notifyOrder(l_feqOrderUnit, l_hostParams, l_sleRvcdQParams,Boolean.TRUE);

            QueryProcessor l_queryProcesser = Processors.getDefaultProcessor();
            List l_listResult1 =
                l_queryProcesser.doFindAllQuery(FeqOrderExecutionParams.TYPE);
            FeqOrderExecutionRow l_executionRow =
                (FeqOrderExecutionRow)l_listResult1.get(0);
            assertEquals(10, l_executionRow.getFxRate(), 0);
            
            List l_listResult2 =
                l_queryProcesser.doFindAllQuery(FeqFinTransactionRow.TYPE);
            FeqFinTransactionRow l_transactionRow =
                (FeqFinTransactionRow)l_listResult2.get(0);
            assertEquals(10, l_transactionRow.getFxRate(), 0);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
    }
    
    //「is日計り取引採用() == true 且つ　@is日計り市場() == true　@且つ　@買付注文（is買付() == true）の場合」
    //update保有資産()
    public void testNotifyOrder_Case006()
    {
        final String STR_METHOD_NAME = "testNotifyOrder_Case006()";
        log.entering(STR_METHOD_NAME);
        try
        {
            TestDBUtility.deleteAll(MainAccountParams.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(333812512246L);
            TestDBUtility.insertWithDel(l_mainAccountParams);
            TestDBUtility.deleteAll(SubAccountParams.TYPE);
            SubAccountParams l_SubAccountParams = TestDBUtility.getSubAccountRow();
            l_SubAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
            l_SubAccountParams.setAccountId(333812512246L);
            l_SubAccountParams.setSubAccountId(33381251220301L);
            TestDBUtility.insertWithDel(l_SubAccountParams);
            TestDBUtility.deleteAll(MarketParams.TYPE);
            MarketParams l_marketParams = TestDBUtility.getMarketRow();
            l_marketParams.setMarketCode("SP");
            TestDBUtility.insertWithDel(l_marketParams);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setInstitutionCode("0D");
            TestDBUtility.insertWithDel(l_branchParams);
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            l_institutionParams.setInstitutionCode("0D");
            l_institutionParams.setInstitutionId(33);
            TestDBUtility.insertWithDel(l_institutionParams);
            TestDBUtility.deleteAll(InstitutionPreferencesParams.TYPE);
            InstitutionPreferencesParams l_institutionPreferencesParams = TestDBUtility.getInstitutionPreferencesRow();
            l_institutionPreferencesParams.setInstitutionId(33);
            l_institutionPreferencesParams.setName("feq.day.trade.div");
            l_institutionPreferencesParams.setNameSerialNo(1);
            l_institutionPreferencesParams.setValue("1");
            TestDBUtility.insertWithDel(l_institutionPreferencesParams);
            TestDBUtility.deleteAll(MarketPreferencesParams.TYPE);
            MarketPreferencesParams l_marketPreferencesParams = TestDBUtility.getMarketPreferencesRow();
            l_marketPreferencesParams.setMarketId(3303L);
            l_marketPreferencesParams.setName("feq.day.trade.market.div");
            l_marketPreferencesParams.setNameSerialNo(1);
            l_marketPreferencesParams.setValue("1");
            TestDBUtility.insertWithDel(l_marketPreferencesParams);
            //FeqOrderUnitParams
            TestDBUtility.deleteAll(FeqOrderUnitParams.TYPE);
            FeqOrderUnitParams l_feqOrderUnitParams =
                TestDBUtility.getFeqOrderUnitRow();
            l_feqOrderUnitParams.setConfirmedQuantity(100);
            l_feqOrderUnitParams.setExecutionSeqNo(100);
            l_feqOrderUnitParams.setOrderType(OrderTypeEnum.FEQ_BUY);
            l_feqOrderUnitParams.setConfirmedPrice(100);
            l_feqOrderUnitParams.setExecEndTimestamp(null);
            l_feqOrderUnitParams.setExecutedAmount(123.45);
            TestDBUtility.insertWithDel(l_feqOrderUnitParams);

            //SleRcvdQParams
            TestDBUtility.deleteAll(SleRcvdQParams.TYPE);
            SleRcvdQParams l_sleRvcdQParams =
                TestDBUtility.getSleRcvdQRow();
            l_sleRvcdQParams.setRouteDiv("1");
            l_sleRvcdQParams.setStatus(SleRcvdqProcStatusEnum.EXEC_PROCESSING);
            TestDBUtility.insertWithDel(l_sleRvcdQParams);
            
            //SubAccount
            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            SubAccountParams l_subAccount = TestDBUtility.getSubAccountRow();
            l_subAccount.setSubAccountId(l_feqOrderUnitParams.getSubAccountId());
            l_subAccount.setAccountId(l_feqOrderUnitParams.getAccountId());
            TestDBUtility.insertWithDel(l_subAccount);
            
            FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
            WEB3FeqOrderManager l_orderManager =
                (WEB3FeqOrderManager)l_finApp.getTradingModule(
                ProductTypeEnum.FOREIGN_EQUITY).getOrderManager();
            WEB3FeqOrderUnit l_feqOrderUnit =
                (WEB3FeqOrderUnit)l_orderManager.getOrderUnit(
                    l_feqOrderUnitParams.getOrderUnitId());

            //HostFeqOrderExecNotifyParams
            TestDBUtility.deleteAll(HostFeqOrderExecNotifyParams.TYPE);
            HostFeqOrderExecNotifyParams l_hostParams = this.getHostFeqOrderExecNotifyParams();
            l_hostParams.setExecSerialNo("111");
            l_hostParams.setExecutionSeqNo(120);
            l_hostParams.setOrderBizDate(GtlUtils.getSystemTimestamp());
            l_hostParams.setOrderEmpCode("120");
            l_hostParams.setExecPrice(200);
            l_hostParams.setFxRate(10);
            TestDBUtility.insertWithDel(l_hostParams);
            
            //FeqOrderExecutionParams
            TestDBUtility.deleteAll(FeqOrderExecutionParams.TYPE);
            FeqOrderExecutionParams l_feqOrderExecutionParams =
                TestDBUtility.getFeqOrderExecutionParams();
            l_feqOrderExecutionParams.setOrderUnitId(l_feqOrderUnitParams.getOrderUnitId());
            l_feqOrderExecutionParams.setFxRate(11);
            TestDBUtility.insertWithDel(l_feqOrderExecutionParams);

            //FeqFinTransactionParams
            TestDBUtility.deleteAll(FeqFinTransactionParams.TYPE);
            FeqFinTransactionParams l_feqFinTransactionParams =
                TestDBUtility.getFeqFinTransactionParams();
            l_feqFinTransactionParams.setOrderId(l_feqOrderUnitParams.getOrderId());
            l_feqFinTransactionParams.setOrderUnitId(l_feqOrderUnitParams.getOrderUnitId());
            l_feqFinTransactionParams.setDeleteFlag(BooleanEnum.FALSE);
            l_feqFinTransactionParams.setFxRate(11);
            TestDBUtility.insertWithDel(l_feqFinTransactionParams);

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.feq.WEB3FeqPositionManager",
                "updateTransaction",
                new Class[] {long.class, boolean.class}, null);

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.feq.WEB3FeqOrderManager",
                "updateEstimatedPrice",
                new Class[] {WEB3FeqOrderUnit.class, Date.class}, null);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.feq.WEB3FeqOrderManager",
                "updateExecutedAmountYen",
                new Class[] {long.class}, null);

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.tradingpower.WEB3TPTradingPowerServiceImpl",
                "reCalcTradingPower",
                new Class[] {WEB3GentradeSubAccount.class}, null);

            l_impl.notifyOrder(l_feqOrderUnit, l_hostParams, l_sleRvcdQParams,Boolean.TRUE);

            QueryProcessor l_queryProcesser = Processors.getDefaultProcessor();
            List l_listResult1 =
                l_queryProcesser.doFindAllQuery(FeqOrderExecutionParams.TYPE);
            FeqOrderExecutionRow l_executionRow =
                (FeqOrderExecutionRow)l_listResult1.get(0);
            assertEquals(10, l_executionRow.getFxRate(), 0);
            
            List l_listResult2 =
                l_queryProcesser.doFindAllQuery(FeqFinTransactionRow.TYPE);
            FeqFinTransactionRow l_transactionRow =
                (FeqFinTransactionRow)l_listResult2.get(0);
            assertEquals(10, l_transactionRow.getFxRate(), 0);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
    }
    
    //「is日計り取引採用() == true 且つ　@is日計り市場() == true　@且つ　@買付注文（is買付() == true）の場合」
    //update保有資産()
    //「is日計り取引採用() == false 且つ　@is日計り市場() == true　@且つ　@買付注文（is買付() == true）の場合」
    public void testNotifyOrder_Case007()
    {
        final String STR_METHOD_NAME = "testNotifyOrder_Case007()";
        log.entering(STR_METHOD_NAME);
        try
        {
            TestDBUtility.deleteAll(MainAccountParams.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(333812512246L);
            TestDBUtility.insertWithDel(l_mainAccountParams);
            TestDBUtility.deleteAll(SubAccountParams.TYPE);
            SubAccountParams l_SubAccountParams = TestDBUtility.getSubAccountRow();
            l_SubAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
            l_SubAccountParams.setAccountId(333812512246L);
            l_SubAccountParams.setSubAccountId(33381251220301L);
            TestDBUtility.insertWithDel(l_SubAccountParams);
            TestDBUtility.deleteAll(MarketParams.TYPE);
            MarketParams l_marketParams = TestDBUtility.getMarketRow();
            l_marketParams.setMarketCode("SP");
            TestDBUtility.insertWithDel(l_marketParams);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setInstitutionCode("0D");
            TestDBUtility.insertWithDel(l_branchParams);
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            l_institutionParams.setInstitutionCode("0D");
            l_institutionParams.setInstitutionId(33);
            TestDBUtility.insertWithDel(l_institutionParams);
            TestDBUtility.deleteAll(InstitutionPreferencesParams.TYPE);
            InstitutionPreferencesParams l_institutionPreferencesParams = TestDBUtility.getInstitutionPreferencesRow();
            l_institutionPreferencesParams.setInstitutionId(33);
            l_institutionPreferencesParams.setName("feq.day.trade.di");
            l_institutionPreferencesParams.setNameSerialNo(1);
            l_institutionPreferencesParams.setValue("1");
            TestDBUtility.insertWithDel(l_institutionPreferencesParams);
            TestDBUtility.deleteAll(MarketPreferencesParams.TYPE);
            MarketPreferencesParams l_marketPreferencesParams = TestDBUtility.getMarketPreferencesRow();
            l_marketPreferencesParams.setMarketId(3303L);
            l_marketPreferencesParams.setName("feq.day.trade.market.div");
            l_marketPreferencesParams.setNameSerialNo(1);
            l_marketPreferencesParams.setValue("1");
            TestDBUtility.insertWithDel(l_marketPreferencesParams);
            //FeqOrderUnitParams
            TestDBUtility.deleteAll(FeqOrderUnitParams.TYPE);
            FeqOrderUnitParams l_feqOrderUnitParams =
                TestDBUtility.getFeqOrderUnitRow();
            l_feqOrderUnitParams.setConfirmedQuantity(100);
            l_feqOrderUnitParams.setExecutionSeqNo(100);
            l_feqOrderUnitParams.setOrderType(OrderTypeEnum.FEQ_BUY);
            l_feqOrderUnitParams.setConfirmedPrice(100);
            l_feqOrderUnitParams.setExecEndTimestamp(null);
            l_feqOrderUnitParams.setExecutedAmount(123.45);
            TestDBUtility.insertWithDel(l_feqOrderUnitParams);

            //SleRcvdQParams
            TestDBUtility.deleteAll(SleRcvdQParams.TYPE);
            SleRcvdQParams l_sleRvcdQParams =
                TestDBUtility.getSleRcvdQRow();
            l_sleRvcdQParams.setRouteDiv("1");
            l_sleRvcdQParams.setStatus(SleRcvdqProcStatusEnum.EXEC_PROCESSING);
            TestDBUtility.insertWithDel(l_sleRvcdQParams);
            
            //SubAccount
            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            SubAccountParams l_subAccount = TestDBUtility.getSubAccountRow();
            l_subAccount.setSubAccountId(l_feqOrderUnitParams.getSubAccountId());
            l_subAccount.setAccountId(l_feqOrderUnitParams.getAccountId());
            TestDBUtility.insertWithDel(l_subAccount);
            
            FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
            WEB3FeqOrderManager l_orderManager =
                (WEB3FeqOrderManager)l_finApp.getTradingModule(
                ProductTypeEnum.FOREIGN_EQUITY).getOrderManager();
            WEB3FeqOrderUnit l_feqOrderUnit =
                (WEB3FeqOrderUnit)l_orderManager.getOrderUnit(
                    l_feqOrderUnitParams.getOrderUnitId());

            //HostFeqOrderExecNotifyParams
            TestDBUtility.deleteAll(HostFeqOrderExecNotifyParams.TYPE);
            HostFeqOrderExecNotifyParams l_hostParams = this.getHostFeqOrderExecNotifyParams();
            l_hostParams.setExecSerialNo("111");
            l_hostParams.setExecutionSeqNo(120);
            l_hostParams.setOrderBizDate(GtlUtils.getSystemTimestamp());
            l_hostParams.setOrderEmpCode("120");
            l_hostParams.setExecPrice(200);
            l_hostParams.setFxRate(10);
            TestDBUtility.insertWithDel(l_hostParams);
            
            //FeqOrderExecutionParams
            TestDBUtility.deleteAll(FeqOrderExecutionParams.TYPE);
            FeqOrderExecutionParams l_feqOrderExecutionParams =
                TestDBUtility.getFeqOrderExecutionParams();
            l_feqOrderExecutionParams.setOrderUnitId(l_feqOrderUnitParams.getOrderUnitId());
            l_feqOrderExecutionParams.setFxRate(11);
            TestDBUtility.insertWithDel(l_feqOrderExecutionParams);

            //FeqFinTransactionParams
            TestDBUtility.deleteAll(FeqFinTransactionParams.TYPE);
            FeqFinTransactionParams l_feqFinTransactionParams =
                TestDBUtility.getFeqFinTransactionParams();
            l_feqFinTransactionParams.setOrderId(l_feqOrderUnitParams.getOrderId());
            l_feqFinTransactionParams.setOrderUnitId(l_feqOrderUnitParams.getOrderUnitId());
            l_feqFinTransactionParams.setDeleteFlag(BooleanEnum.FALSE);
            l_feqFinTransactionParams.setFxRate(11);
            TestDBUtility.insertWithDel(l_feqFinTransactionParams);

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.feq.WEB3FeqPositionManager",
                "updateTransaction",
                new Class[] {long.class, boolean.class}, null);

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.feq.WEB3FeqOrderManager",
                "updateEstimatedPrice",
                new Class[] {WEB3FeqOrderUnit.class, Date.class}, null);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.feq.WEB3FeqOrderManager",
                "updateExecutedAmountYen",
                new Class[] {long.class}, null);

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.tradingpower.WEB3TPTradingPowerServiceImpl",
                "reCalcTradingPower",
                new Class[] {WEB3GentradeSubAccount.class}, null);

            l_impl.notifyOrder(l_feqOrderUnit, l_hostParams, l_sleRvcdQParams,Boolean.TRUE);

            QueryProcessor l_queryProcesser = Processors.getDefaultProcessor();
            List l_listResult1 =
                l_queryProcesser.doFindAllQuery(FeqOrderExecutionParams.TYPE);
            FeqOrderExecutionRow l_executionRow =
                (FeqOrderExecutionRow)l_listResult1.get(0);
            assertEquals(10, l_executionRow.getFxRate(), 0);
            
            List l_listResult2 =
                l_queryProcesser.doFindAllQuery(FeqFinTransactionRow.TYPE);
            FeqFinTransactionRow l_transactionRow =
                (FeqFinTransactionRow)l_listResult2.get(0);
            assertEquals(10, l_transactionRow.getFxRate(), 0);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
    }
    
    //「is日計り取引採用() == true 且つ　@is日計り市場() == true　@且つ　@買付注文（is買付() == true）の場合」
    //update保有資産()
    //「is日計り取引採用() == true 且つ　@is日計り市場() == false　@且つ　@買付注文（is買付() == true）の場合」
    public void testNotifyOrder_Case008()
    {
        final String STR_METHOD_NAME = "testNotifyOrder_Case008()";
        log.entering(STR_METHOD_NAME);
        try
        {
            TestDBUtility.deleteAll(MainAccountParams.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(333812512246L);
            TestDBUtility.insertWithDel(l_mainAccountParams);
            TestDBUtility.deleteAll(SubAccountParams.TYPE);
            SubAccountParams l_SubAccountParams = TestDBUtility.getSubAccountRow();
            l_SubAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
            l_SubAccountParams.setAccountId(333812512246L);
            l_SubAccountParams.setSubAccountId(33381251220301L);
            TestDBUtility.insertWithDel(l_SubAccountParams);
            TestDBUtility.deleteAll(MarketParams.TYPE);
            MarketParams l_marketParams = TestDBUtility.getMarketRow();
            l_marketParams.setMarketCode("SP");
            TestDBUtility.insertWithDel(l_marketParams);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setInstitutionCode("0D");
            TestDBUtility.insertWithDel(l_branchParams);
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            l_institutionParams.setInstitutionCode("0D");
            l_institutionParams.setInstitutionId(33);
            TestDBUtility.insertWithDel(l_institutionParams);
            TestDBUtility.deleteAll(InstitutionPreferencesParams.TYPE);
            InstitutionPreferencesParams l_institutionPreferencesParams = TestDBUtility.getInstitutionPreferencesRow();
            l_institutionPreferencesParams.setInstitutionId(33);
            l_institutionPreferencesParams.setName("feq.day.trade.div");
            l_institutionPreferencesParams.setNameSerialNo(1);
            l_institutionPreferencesParams.setValue("1");
            TestDBUtility.insertWithDel(l_institutionPreferencesParams);
            TestDBUtility.deleteAll(MarketPreferencesParams.TYPE);
            MarketPreferencesParams l_marketPreferencesParams = TestDBUtility.getMarketPreferencesRow();
            l_marketPreferencesParams.setMarketId(3303L);
            l_marketPreferencesParams.setName("feq.day.trade.market");
            l_marketPreferencesParams.setNameSerialNo(1);
            l_marketPreferencesParams.setValue("1");
            TestDBUtility.insertWithDel(l_marketPreferencesParams);
            //FeqOrderUnitParams
            TestDBUtility.deleteAll(FeqOrderUnitParams.TYPE);
            FeqOrderUnitParams l_feqOrderUnitParams =
                TestDBUtility.getFeqOrderUnitRow();
            l_feqOrderUnitParams.setConfirmedQuantity(100);
            l_feqOrderUnitParams.setExecutionSeqNo(100);
            l_feqOrderUnitParams.setOrderType(OrderTypeEnum.FEQ_BUY);
            l_feqOrderUnitParams.setConfirmedPrice(100);
            l_feqOrderUnitParams.setExecEndTimestamp(null);
            l_feqOrderUnitParams.setExecutedAmount(123.45);
            TestDBUtility.insertWithDel(l_feqOrderUnitParams);

            //SleRcvdQParams
            TestDBUtility.deleteAll(SleRcvdQParams.TYPE);
            SleRcvdQParams l_sleRvcdQParams =
                TestDBUtility.getSleRcvdQRow();
            l_sleRvcdQParams.setRouteDiv("1");
            l_sleRvcdQParams.setStatus(SleRcvdqProcStatusEnum.EXEC_PROCESSING);
            TestDBUtility.insertWithDel(l_sleRvcdQParams);
            
            //SubAccount
            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            SubAccountParams l_subAccount = TestDBUtility.getSubAccountRow();
            l_subAccount.setSubAccountId(l_feqOrderUnitParams.getSubAccountId());
            l_subAccount.setAccountId(l_feqOrderUnitParams.getAccountId());
            TestDBUtility.insertWithDel(l_subAccount);
            
            FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
            WEB3FeqOrderManager l_orderManager =
                (WEB3FeqOrderManager)l_finApp.getTradingModule(
                ProductTypeEnum.FOREIGN_EQUITY).getOrderManager();
            WEB3FeqOrderUnit l_feqOrderUnit =
                (WEB3FeqOrderUnit)l_orderManager.getOrderUnit(
                    l_feqOrderUnitParams.getOrderUnitId());

            //HostFeqOrderExecNotifyParams
            TestDBUtility.deleteAll(HostFeqOrderExecNotifyParams.TYPE);
            HostFeqOrderExecNotifyParams l_hostParams = this.getHostFeqOrderExecNotifyParams();
            l_hostParams.setExecSerialNo("111");
            l_hostParams.setExecutionSeqNo(120);
            l_hostParams.setOrderBizDate(GtlUtils.getSystemTimestamp());
            l_hostParams.setOrderEmpCode("120");
            l_hostParams.setExecPrice(200);
            l_hostParams.setFxRate(10);
            TestDBUtility.insertWithDel(l_hostParams);
            
            //FeqOrderExecutionParams
            TestDBUtility.deleteAll(FeqOrderExecutionParams.TYPE);
            FeqOrderExecutionParams l_feqOrderExecutionParams =
                TestDBUtility.getFeqOrderExecutionParams();
            l_feqOrderExecutionParams.setOrderUnitId(l_feqOrderUnitParams.getOrderUnitId());
            l_feqOrderExecutionParams.setFxRate(11);
            TestDBUtility.insertWithDel(l_feqOrderExecutionParams);

            //FeqFinTransactionParams
            TestDBUtility.deleteAll(FeqFinTransactionParams.TYPE);
            FeqFinTransactionParams l_feqFinTransactionParams =
                TestDBUtility.getFeqFinTransactionParams();
            l_feqFinTransactionParams.setOrderId(l_feqOrderUnitParams.getOrderId());
            l_feqFinTransactionParams.setOrderUnitId(l_feqOrderUnitParams.getOrderUnitId());
            l_feqFinTransactionParams.setDeleteFlag(BooleanEnum.FALSE);
            l_feqFinTransactionParams.setFxRate(11);
            TestDBUtility.insertWithDel(l_feqFinTransactionParams);

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.feq.WEB3FeqPositionManager",
                "updateTransaction",
                new Class[] {long.class, boolean.class}, null);

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.feq.WEB3FeqOrderManager",
                "updateEstimatedPrice",
                new Class[] {WEB3FeqOrderUnit.class, Date.class}, null);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.feq.WEB3FeqOrderManager",
                "updateExecutedAmountYen",
                new Class[] {long.class}, null);

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.tradingpower.WEB3TPTradingPowerServiceImpl",
                "reCalcTradingPower",
                new Class[] {WEB3GentradeSubAccount.class}, null);

            l_impl.notifyOrder(l_feqOrderUnit, l_hostParams, l_sleRvcdQParams,Boolean.TRUE);

            QueryProcessor l_queryProcesser = Processors.getDefaultProcessor();
            List l_listResult1 =
                l_queryProcesser.doFindAllQuery(FeqOrderExecutionParams.TYPE);
            FeqOrderExecutionRow l_executionRow =
                (FeqOrderExecutionRow)l_listResult1.get(0);
            assertEquals(10, l_executionRow.getFxRate(), 0);
            
            List l_listResult2 =
                l_queryProcesser.doFindAllQuery(FeqFinTransactionRow.TYPE);
            FeqFinTransactionRow l_transactionRow =
                (FeqFinTransactionRow)l_listResult2.get(0);
            assertEquals(10, l_transactionRow.getFxRate(), 0);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
    }
    
    //「is日計り取引採用() == true 且つ　@is日計り市場() == true　@且つ　@買付注文（is買付() == true）の場合」
    //update保有資産()
    //「is日計り取引採用() == true 且つ　@is日計り市場() == true　@且つ　@買付注文（is買付() == false）の場合」
    public void testNotifyOrder_Case009()
    {
        final String STR_METHOD_NAME = "testNotifyOrder_Case009()";
        log.entering(STR_METHOD_NAME);
        try
        {
            TestDBUtility.deleteAll(MainAccountParams.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(333812512246L);
            TestDBUtility.insertWithDel(l_mainAccountParams);
            TestDBUtility.deleteAll(SubAccountParams.TYPE);
            SubAccountParams l_SubAccountParams = TestDBUtility.getSubAccountRow();
            l_SubAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
            l_SubAccountParams.setAccountId(333812512246L);
            l_SubAccountParams.setSubAccountId(33381251220301L);
            TestDBUtility.insertWithDel(l_SubAccountParams);
            TestDBUtility.deleteAll(MarketParams.TYPE);
            MarketParams l_marketParams = TestDBUtility.getMarketRow();
            l_marketParams.setMarketCode("SP");
            TestDBUtility.insertWithDel(l_marketParams);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setInstitutionCode("0D");
            TestDBUtility.insertWithDel(l_branchParams);
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            l_institutionParams.setInstitutionCode("0D");
            l_institutionParams.setInstitutionId(33);
            TestDBUtility.insertWithDel(l_institutionParams);
            TestDBUtility.deleteAll(InstitutionPreferencesParams.TYPE);
            InstitutionPreferencesParams l_institutionPreferencesParams = TestDBUtility.getInstitutionPreferencesRow();
            l_institutionPreferencesParams.setInstitutionId(33);
            l_institutionPreferencesParams.setName("feq.day.trade.div");
            l_institutionPreferencesParams.setNameSerialNo(1);
            l_institutionPreferencesParams.setValue("1");
            TestDBUtility.insertWithDel(l_institutionPreferencesParams);
            TestDBUtility.deleteAll(MarketPreferencesParams.TYPE);
            MarketPreferencesParams l_marketPreferencesParams = TestDBUtility.getMarketPreferencesRow();
            l_marketPreferencesParams.setMarketId(3303L);
            l_marketPreferencesParams.setName("feq.day.trade.market.div");
            l_marketPreferencesParams.setNameSerialNo(1);
            l_marketPreferencesParams.setValue("1");
            TestDBUtility.insertWithDel(l_marketPreferencesParams);
            //FeqOrderUnitParams
            TestDBUtility.deleteAll(FeqOrderUnitParams.TYPE);
            FeqOrderUnitParams l_feqOrderUnitParams =
                TestDBUtility.getFeqOrderUnitRow();
            l_feqOrderUnitParams.setConfirmedQuantity(100);
            l_feqOrderUnitParams.setExecutionSeqNo(100);
            l_feqOrderUnitParams.setOrderType(OrderTypeEnum.FEQ_SELL);
            l_feqOrderUnitParams.setConfirmedPrice(100);
            l_feqOrderUnitParams.setExecEndTimestamp(null);
            l_feqOrderUnitParams.setExecutedAmount(123.45);
            TestDBUtility.insertWithDel(l_feqOrderUnitParams);

            //SleRcvdQParams
            TestDBUtility.deleteAll(SleRcvdQParams.TYPE);
            SleRcvdQParams l_sleRvcdQParams =
                TestDBUtility.getSleRcvdQRow();
            l_sleRvcdQParams.setRouteDiv("1");
            l_sleRvcdQParams.setStatus(SleRcvdqProcStatusEnum.EXEC_PROCESSING);
            TestDBUtility.insertWithDel(l_sleRvcdQParams);
            
            //SubAccount
            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            SubAccountParams l_subAccount = TestDBUtility.getSubAccountRow();
            l_subAccount.setSubAccountId(l_feqOrderUnitParams.getSubAccountId());
            l_subAccount.setAccountId(l_feqOrderUnitParams.getAccountId());
            TestDBUtility.insertWithDel(l_subAccount);
            
            FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
            WEB3FeqOrderManager l_orderManager =
                (WEB3FeqOrderManager)l_finApp.getTradingModule(
                ProductTypeEnum.FOREIGN_EQUITY).getOrderManager();
            WEB3FeqOrderUnit l_feqOrderUnit =
                (WEB3FeqOrderUnit)l_orderManager.getOrderUnit(
                    l_feqOrderUnitParams.getOrderUnitId());

            //HostFeqOrderExecNotifyParams
            TestDBUtility.deleteAll(HostFeqOrderExecNotifyParams.TYPE);
            HostFeqOrderExecNotifyParams l_hostParams = this.getHostFeqOrderExecNotifyParams();
            l_hostParams.setExecSerialNo("111");
            l_hostParams.setExecutionSeqNo(120);
            l_hostParams.setOrderBizDate(GtlUtils.getSystemTimestamp());
            l_hostParams.setOrderEmpCode("120");
            l_hostParams.setExecPrice(200);
            l_hostParams.setFxRate(10);
            TestDBUtility.insertWithDel(l_hostParams);
            
            //FeqOrderExecutionParams
            TestDBUtility.deleteAll(FeqOrderExecutionParams.TYPE);
            FeqOrderExecutionParams l_feqOrderExecutionParams =
                TestDBUtility.getFeqOrderExecutionParams();
            l_feqOrderExecutionParams.setOrderUnitId(l_feqOrderUnitParams.getOrderUnitId());
            l_feqOrderExecutionParams.setFxRate(11);
            TestDBUtility.insertWithDel(l_feqOrderExecutionParams);

            //FeqFinTransactionParams
            TestDBUtility.deleteAll(FeqFinTransactionParams.TYPE);
            FeqFinTransactionParams l_feqFinTransactionParams =
                TestDBUtility.getFeqFinTransactionParams();
            l_feqFinTransactionParams.setOrderId(l_feqOrderUnitParams.getOrderId());
            l_feqFinTransactionParams.setOrderUnitId(l_feqOrderUnitParams.getOrderUnitId());
            l_feqFinTransactionParams.setDeleteFlag(BooleanEnum.FALSE);
            l_feqFinTransactionParams.setFxRate(11);
            TestDBUtility.insertWithDel(l_feqFinTransactionParams);

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.feq.WEB3FeqPositionManager",
                "updateTransaction",
                new Class[] {long.class, boolean.class}, null);

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.feq.WEB3FeqOrderManager",
                "updateEstimatedPrice",
                new Class[] {WEB3FeqOrderUnit.class, Date.class}, null);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.feq.WEB3FeqOrderManager",
                "updateExecutedAmountYen",
                new Class[] {long.class}, null);

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.tradingpower.WEB3TPTradingPowerServiceImpl",
                "reCalcTradingPower",
                new Class[] {WEB3GentradeSubAccount.class}, null);

            l_impl.notifyOrder(l_feqOrderUnit, l_hostParams, l_sleRvcdQParams,Boolean.TRUE);

            QueryProcessor l_queryProcesser = Processors.getDefaultProcessor();
            List l_listResult1 =
                l_queryProcesser.doFindAllQuery(FeqOrderExecutionParams.TYPE);
            FeqOrderExecutionRow l_executionRow =
                (FeqOrderExecutionRow)l_listResult1.get(0);
            assertEquals(10, l_executionRow.getFxRate(), 0);
            
            List l_listResult2 =
                l_queryProcesser.doFindAllQuery(FeqFinTransactionRow.TYPE);
            FeqFinTransactionRow l_transactionRow =
                (FeqFinTransactionRow)l_listResult2.get(0);
            assertEquals(10, l_transactionRow.getFxRate(), 0);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
    }
}

@
