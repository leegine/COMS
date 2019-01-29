head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.00.55.04;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3EquityMarginExecuteReferenceServiceImplTest_isDesignateExecutedStatus.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : WEB3EquityMarginExecuteReferenceServiceImplTest.java
Author Name      : Daiwa Institute of Research
Revesion History : Revesion History : 2007/01/17　@関博(中訊) 新規作成
*/
package webbroker3.equity.service.delegate.stdimpls;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeOrderManager;
import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeOrderUnit;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderUnitParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;

import test.util.TestDBUtility;

import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3LogUtility;

/**
 * 株式・信用注文約定照会サービスImplクラスのテスト<BR>
 * @@author 関博(中訊)
 * @@version 1.0
 */
public class WEB3EquityMarginExecuteReferenceServiceImplTest_isDesignateExecutedStatus extends TestBaseForMock
{
    /**
     * ログ出力ユーティリティ。<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3EquityMarginExecuteReferenceServiceImplTest_isDesignateExecutedStatus.class);
    WEB3EquityMarginExecuteReferenceServiceImpl impl =
        new WEB3EquityMarginExecuteReferenceServiceImpl();
    
    public WEB3EquityMarginExecuteReferenceServiceImplTest_isDesignateExecutedStatus(String arg0)
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
    
    /*
     * Test method for 'webbroker3.equity.service.delegate.stdimpls.WEB3EquityMarginExecuteReferenceServiceImpl.
     * isDesignateExecutedStatus(String l_strExecutedStatus, EqTypeOrderUnit l_orderUnit)'
     */
    public void testIsDesignateExecutedStatus_C0001()
    {
        final String STR_METHOD_NAME = "testIsDesignateExecutedStatus_C0001()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        try
        {
            EqtypeOrderUnitParams l_eqTypeOrderUnitParams1 = TestDBUtility.getEqtypeOrderUnitRow();
            l_eqTypeOrderUnitParams1.setProductType(ProductTypeEnum.EQUITY);
            l_eqTypeOrderUnitParams1.setOrderUnitId(3304148080001L);
            l_eqTypeOrderUnitParams1.setExecutedQuantity(12.23D);
            l_eqTypeOrderUnitParams1.setConfirmedQuantity(22.23D);
            
            TestDBUtility.insertWithDel(l_eqTypeOrderUnitParams1);

            FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
            EqTypeOrderManager l_eqTypeOrderManager =
                (EqTypeOrderManager)l_finApp.getTradingModule(ProductTypeEnum.EQUITY).getOrderManager();
            EqTypeOrderUnit l_orderUnit = (EqTypeOrderUnit)l_eqTypeOrderManager.getOrderUnit(3304148080001L);

            assertTrue(impl.isDesignateExecutedStatus("1",l_orderUnit));
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    public void testIsDesignateExecutedStatus_C0002()
    {
        final String STR_METHOD_NAME = "testIsDesignateExecutedStatus_C0002()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        try
        {
            EqtypeOrderUnitParams l_eqTypeOrderUnitParams1 = TestDBUtility.getEqtypeOrderUnitRow();
            l_eqTypeOrderUnitParams1.setProductType(ProductTypeEnum.EQUITY);
            l_eqTypeOrderUnitParams1.setOrderUnitId(3304148080001L);
            l_eqTypeOrderUnitParams1.setExecutedQuantity(12.23D);
            l_eqTypeOrderUnitParams1.setConfirmedQuantity(22.23D);
            
            TestDBUtility.insertWithDel(l_eqTypeOrderUnitParams1);

            FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
            EqTypeOrderManager l_eqTypeOrderManager =
                (EqTypeOrderManager)l_finApp.getTradingModule(ProductTypeEnum.EQUITY).getOrderManager();
            EqTypeOrderUnit l_orderUnit = (EqTypeOrderUnit)l_eqTypeOrderManager.getOrderUnit(3304148080001L);

            assertFalse(impl.isDesignateExecutedStatus("2",l_orderUnit));
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    public void testIsDesignateExecutedStatus_C0003()
    {
        final String STR_METHOD_NAME = "testIsDesignateExecutedStatus_C0003()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        try
        {
            EqtypeOrderUnitParams l_eqTypeOrderUnitParams1 = TestDBUtility.getEqtypeOrderUnitRow();
            l_eqTypeOrderUnitParams1.setProductType(ProductTypeEnum.EQUITY);
            l_eqTypeOrderUnitParams1.setOrderUnitId(3304148080001L);
            l_eqTypeOrderUnitParams1.setExecutedQuantity(22.23D);
            l_eqTypeOrderUnitParams1.setConfirmedQuantity(22.23D);
            
            TestDBUtility.insertWithDel(l_eqTypeOrderUnitParams1);

            FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
            EqTypeOrderManager l_eqTypeOrderManager =
                (EqTypeOrderManager)l_finApp.getTradingModule(ProductTypeEnum.EQUITY).getOrderManager();
            EqTypeOrderUnit l_orderUnit = (EqTypeOrderUnit)l_eqTypeOrderManager.getOrderUnit(3304148080001L);

            assertTrue(impl.isDesignateExecutedStatus("2",l_orderUnit));
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    public void testIsDesignateExecutedStatus_C0004()
    {
        final String STR_METHOD_NAME = "testIsDesignateExecutedStatus_C0004()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        try
        {
            EqtypeOrderUnitParams l_eqTypeOrderUnitParams1 = TestDBUtility.getEqtypeOrderUnitRow();
            l_eqTypeOrderUnitParams1.setProductType(ProductTypeEnum.EQUITY);
            l_eqTypeOrderUnitParams1.setOrderUnitId(3304148080001L);
            l_eqTypeOrderUnitParams1.setExecutedQuantity(22.23D);
            l_eqTypeOrderUnitParams1.setConfirmedQuantity(23.23D);
            
            TestDBUtility.insertWithDel(l_eqTypeOrderUnitParams1);

            FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
            EqTypeOrderManager l_eqTypeOrderManager =
                (EqTypeOrderManager)l_finApp.getTradingModule(ProductTypeEnum.EQUITY).getOrderManager();
            EqTypeOrderUnit l_orderUnit = (EqTypeOrderUnit)l_eqTypeOrderManager.getOrderUnit(3304148080001L);

            assertFalse(impl.isDesignateExecutedStatus("2",l_orderUnit));
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    public void testIsDesignateExecutedStatus_C0005()
    {
        final String STR_METHOD_NAME = "testIsDesignateExecutedStatus_C0005()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        try
        {
            EqtypeOrderUnitParams l_eqTypeOrderUnitParams1 = TestDBUtility.getEqtypeOrderUnitRow();
            l_eqTypeOrderUnitParams1.setProductType(ProductTypeEnum.EQUITY);
            l_eqTypeOrderUnitParams1.setOrderUnitId(3304148080001L);
            l_eqTypeOrderUnitParams1.setExecutedQuantity(0);
            l_eqTypeOrderUnitParams1.setConfirmedQuantity(23.23D);
            
            TestDBUtility.insertWithDel(l_eqTypeOrderUnitParams1);

            FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
            EqTypeOrderManager l_eqTypeOrderManager =
                (EqTypeOrderManager)l_finApp.getTradingModule(ProductTypeEnum.EQUITY).getOrderManager();
            EqTypeOrderUnit l_orderUnit = (EqTypeOrderUnit)l_eqTypeOrderManager.getOrderUnit(3304148080001L);

            assertTrue(impl.isDesignateExecutedStatus("0",l_orderUnit));
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    public void testIsDesignateExecutedStatus_C0006()
    {
        final String STR_METHOD_NAME = "testIsDesignateExecutedStatus_C0006()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        try
        {
            EqtypeOrderUnitParams l_eqTypeOrderUnitParams1 = TestDBUtility.getEqtypeOrderUnitRow();
            l_eqTypeOrderUnitParams1.setProductType(ProductTypeEnum.EQUITY);
            l_eqTypeOrderUnitParams1.setOrderUnitId(3304148080001L);
            l_eqTypeOrderUnitParams1.setExecutedQuantity(22.23D);
            l_eqTypeOrderUnitParams1.setConfirmedQuantity(23.23D);
            
            TestDBUtility.insertWithDel(l_eqTypeOrderUnitParams1);

            FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
            EqTypeOrderManager l_eqTypeOrderManager =
                (EqTypeOrderManager)l_finApp.getTradingModule(ProductTypeEnum.EQUITY).getOrderManager();
            EqTypeOrderUnit l_orderUnit = (EqTypeOrderUnit)l_eqTypeOrderManager.getOrderUnit(3304148080001L);

            assertFalse(impl.isDesignateExecutedStatus("0",l_orderUnit));
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    public void testIsDesignateExecutedStatus_C0007()
    {
        final String STR_METHOD_NAME = "testIsDesignateExecutedStatus_C0007()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        try
        {
            EqtypeOrderUnitParams l_eqTypeOrderUnitParams1 = TestDBUtility.getEqtypeOrderUnitRow();
            l_eqTypeOrderUnitParams1.setProductType(ProductTypeEnum.EQUITY);
            l_eqTypeOrderUnitParams1.setOrderUnitId(3304148080001L);
            l_eqTypeOrderUnitParams1.setExecutedQuantity(12.23D);
            l_eqTypeOrderUnitParams1.setConfirmedQuantity(23.23D);
            
            TestDBUtility.insertWithDel(l_eqTypeOrderUnitParams1);

            FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
            EqTypeOrderManager l_eqTypeOrderManager =
                (EqTypeOrderManager)l_finApp.getTradingModule(ProductTypeEnum.EQUITY).getOrderManager();
            EqTypeOrderUnit l_orderUnit = (EqTypeOrderUnit)l_eqTypeOrderManager.getOrderUnit(3304148080001L);

            assertFalse(impl.isDesignateExecutedStatus(null,l_orderUnit));
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }

}
@
