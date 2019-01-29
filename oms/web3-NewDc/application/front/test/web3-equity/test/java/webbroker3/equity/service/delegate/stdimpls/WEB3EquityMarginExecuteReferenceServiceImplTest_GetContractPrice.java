head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.00.56.53;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3EquityMarginExecuteReferenceServiceImplTest_GetContractPrice.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.equity.service.delegate.stdimpls;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeOrderManager;
import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeOrderUnit;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeClosingContractSpecParams;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeContractParams;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderUnitParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderCategEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;

import test.util.TestDBUtility;

import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3LogUtility;

public class WEB3EquityMarginExecuteReferenceServiceImplTest_GetContractPrice extends TestBaseForMock
{
    /**
     * ログ出力ユーティリティ。<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3EquityMarginExecuteReferenceServiceImplTest_GetContractPrice.class);
    WEB3EquityMarginExecuteReferenceServiceImpl impl =
        new WEB3EquityMarginExecuteReferenceServiceImpl();

    public WEB3EquityMarginExecuteReferenceServiceImplTest_GetContractPrice(String arg0)
    {
        super(arg0);
    }

    protected void setUp() throws Exception
    {
        super.setUp();
    }

    protected void tearDown() throws Exception
    {
        super.tearDown();
        
    }
    
    /*
     * Test method for 'webbroker3.equity.service.delegate.stdimpls.WEB3EquityMarginExecuteReferenceServiceImpl.
     * getContractPrice(EqTypeOrderUnit l_orderUnit)'
     */
    
    // １）引数.注文単位.注文カテゴリ == ”新規建注文” の場合
    // null を返却する。のテスト
    public void testGetContractPrice_C0001()
    {
        final String STR_METHOD_NAME = "testGetContractPrice_C0001()";
        log.entering(STR_METHOD_NAME);
        try
        {

            EqtypeOrderUnitParams l_eqTypeOrderUnitParams1 = TestDBUtility.getEqtypeOrderUnitRow();
            l_eqTypeOrderUnitParams1.setProductType(ProductTypeEnum.EQUITY);
            l_eqTypeOrderUnitParams1.setOrderUnitId(3304148080001L);
            l_eqTypeOrderUnitParams1.setExecutedQuantity(12.23D);
            l_eqTypeOrderUnitParams1.setConfirmedQuantity(22.23D);
            l_eqTypeOrderUnitParams1.setOrderCateg(OrderCategEnum.OPEN_MARGIN);
            
            TestDBUtility.insertWithDel(l_eqTypeOrderUnitParams1);

            FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
            EqTypeOrderManager l_eqTypeOrderManager =
                (EqTypeOrderManager)l_finApp.getTradingModule(ProductTypeEnum.EQUITY).getOrderManager();
            EqTypeOrderUnit l_orderUnit = (EqTypeOrderUnit) l_eqTypeOrderManager.getOrderUnit(3304148080001L);
            
            assertEquals(null,impl.getContractPrice(l_orderUnit));
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    // l_specs = 1のテスト
    public void testGetContractPrice_C0002()
    {
        final String STR_METHOD_NAME = "testGetContractPrice_C0002()";
        log.entering(STR_METHOD_NAME);
        try
        {
            EqtypeOrderUnitParams l_eqTypeOrderUnitParams1 = TestDBUtility.getEqtypeOrderUnitRow();
            l_eqTypeOrderUnitParams1.setProductType(ProductTypeEnum.EQUITY);
            l_eqTypeOrderUnitParams1.setOrderUnitId(3304148080001L);
            l_eqTypeOrderUnitParams1.setExecutedQuantity(12.23D);
            l_eqTypeOrderUnitParams1.setConfirmedQuantity(22.23D);
            l_eqTypeOrderUnitParams1.setOrderCateg(OrderCategEnum.CLOSE_MARGIN);
            l_eqTypeOrderUnitParams1.setClosingOrderType(null);

            TestDBUtility.insertWithDel(l_eqTypeOrderUnitParams1);
            
            EqtypeClosingContractSpecParams l_eqTypeClosingContractSpecParams1 = 
                TestDBUtility.getEqtypeClosingContractSpecRow();
            l_eqTypeClosingContractSpecParams1.setClosingContractSpecId(3304148080001L);
            l_eqTypeClosingContractSpecParams1.setQuantity(2000);

            TestDBUtility.insertWithDel(l_eqTypeClosingContractSpecParams1);

            EqtypeContractParams l_eqTypeContractParams =
                TestDBUtility.getEqtypeContractRow();
            l_eqTypeContractParams.setContractId(2134566345L);
            l_eqTypeContractParams.setContractPrice(1000);

            TestDBUtility.insertWithDel(l_eqTypeContractParams);

            FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
            EqTypeOrderManager l_eqTypeOrderManager =
                (EqTypeOrderManager)l_finApp.getTradingModule(ProductTypeEnum.EQUITY).getOrderManager();
            EqTypeOrderUnit l_orderUnit = (EqTypeOrderUnit) l_eqTypeOrderManager.getOrderUnit(3304148080001L);

            assertEquals("1000",impl.getContractPrice(l_orderUnit));
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    // l_specs = 3のテスト
    public void testGetContractPrice_C0003()
    {
        final String STR_METHOD_NAME = "testGetContractPrice_C0003()";
        log.entering(STR_METHOD_NAME);
        try
        {
            EqtypeOrderUnitParams l_eqTypeOrderUnitParams1 = TestDBUtility.getEqtypeOrderUnitRow();
            l_eqTypeOrderUnitParams1.setProductType(ProductTypeEnum.EQUITY);
            l_eqTypeOrderUnitParams1.setOrderUnitId(3304148080001L);
            l_eqTypeOrderUnitParams1.setExecutedQuantity(12.21D);
            l_eqTypeOrderUnitParams1.setConfirmedQuantity(22.21D);
            l_eqTypeOrderUnitParams1.setOrderCateg(OrderCategEnum.CLOSE_MARGIN);
            l_eqTypeOrderUnitParams1.setClosingOrderType(null);
            TestDBUtility.insertWithDel(l_eqTypeOrderUnitParams1);
                        
            EqtypeClosingContractSpecParams l_eqTypeClosingContractSpecParams1 = 
                TestDBUtility.getEqtypeClosingContractSpecRow();
            
            l_eqTypeClosingContractSpecParams1.setQuantity(1000);
            l_eqTypeClosingContractSpecParams1.setOrderUnitId(3304148080001L);
            l_eqTypeClosingContractSpecParams1.setContractId(2134566345L);
            TestDBUtility.insertWithDel(l_eqTypeClosingContractSpecParams1);
            
            EqtypeClosingContractSpecParams l_eqTypeClosingContractSpecParams2 = 
                TestDBUtility.getEqtypeClosingContractSpecRow();
            l_eqTypeClosingContractSpecParams2.setClosingContractSpecId(33445566778800L);
            l_eqTypeClosingContractSpecParams2.setOrderUnitId(3304148080001L);
            l_eqTypeClosingContractSpecParams2.setQuantity(2000);
            l_eqTypeClosingContractSpecParams2.setContractId(2134566346L);
            l_eqTypeClosingContractSpecParams2.setClosingSerialNo(112);
            TestDBUtility.insertWithDel(l_eqTypeClosingContractSpecParams2);
            
            EqtypeClosingContractSpecParams l_eqTypeClosingContractSpecParams3 = 
                TestDBUtility.getEqtypeClosingContractSpecRow();
            l_eqTypeClosingContractSpecParams3.setClosingContractSpecId(33445566778833L);
            l_eqTypeClosingContractSpecParams3.setOrderUnitId(3304148080001L);
            l_eqTypeClosingContractSpecParams3.setQuantity(3000);
            l_eqTypeClosingContractSpecParams3.setContractId(2134566347L);
            l_eqTypeClosingContractSpecParams3.setClosingSerialNo(113);
            TestDBUtility.insertWithDel(l_eqTypeClosingContractSpecParams3);
           

            EqtypeContractParams l_eqTypeContractParams1 =
                TestDBUtility.getEqtypeContractRow();
            l_eqTypeContractParams1.setContractId(2134566345L);
            l_eqTypeContractParams1.setContractPrice(1000);
            TestDBUtility.insertWithDel(l_eqTypeContractParams1);
            
            EqtypeContractParams l_eqTypeContractParams2 =
                TestDBUtility.getEqtypeContractRow();
            l_eqTypeContractParams2.setContractId(2134566346L);
            l_eqTypeContractParams2.setContractPrice(1800);
            TestDBUtility.insertWithDel(l_eqTypeContractParams2);
            
            EqtypeContractParams l_eqTypeContractParams3 =
                TestDBUtility.getEqtypeContractRow();
            l_eqTypeContractParams3.setContractId(2134566347L);
            l_eqTypeContractParams3.setContractPrice(1900);
            TestDBUtility.insertWithDel(l_eqTypeContractParams3);
            

            FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
            EqTypeOrderManager l_eqTypeOrderManager =
                (EqTypeOrderManager)l_finApp.getTradingModule(ProductTypeEnum.EQUITY).getOrderManager();
            EqTypeOrderUnit l_orderUnit = (EqTypeOrderUnit) l_eqTypeOrderManager.getOrderUnit(3304148080001L);

            assertEquals("1717",impl.getContractPrice(l_orderUnit));
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    public void testGetContractPrice_C0004()
    {
        final String STR_METHOD_NAME = "testGetContractPrice_C0004()";
        log.entering(STR_METHOD_NAME);
        try
        {
            EqtypeClosingContractSpecParams l_eqTypeClosingContractSpecParams = new EqtypeClosingContractSpecParams();
            QueryProcessor l_processor = Processors.getDefaultProcessor();
            l_processor.doDeleteAllQuery(l_eqTypeClosingContractSpecParams.getRowType());
            
            EqtypeOrderUnitParams l_eqTypeOrderUnitParams1 = TestDBUtility.getEqtypeOrderUnitRow();
            l_eqTypeOrderUnitParams1.setProductType(ProductTypeEnum.EQUITY);
            l_eqTypeOrderUnitParams1.setOrderUnitId(3304148080001L);
            l_eqTypeOrderUnitParams1.setExecutedQuantity(12.23D);
            l_eqTypeOrderUnitParams1.setConfirmedQuantity(22.23D);
            l_eqTypeOrderUnitParams1.setOrderCateg(OrderCategEnum.CLOSE_MARGIN);
            l_eqTypeOrderUnitParams1.setClosingOrderType(null);

            TestDBUtility.insertWithDel(l_eqTypeOrderUnitParams1);
            
            EqtypeClosingContractSpecParams l_eqTypeClosingContractSpecParams1 = 
                TestDBUtility.getEqtypeClosingContractSpecRow();
            l_eqTypeClosingContractSpecParams1.setClosingContractSpecId(33445566778829L);
            l_eqTypeClosingContractSpecParams1.setOrderUnitId(3304148080010L);
            l_eqTypeClosingContractSpecParams1.setQuantity(2000);

            l_eqTypeClosingContractSpecParams1.setContractId(2134566348L);
            l_eqTypeClosingContractSpecParams1.setClosingSerialNo(114);
            TestDBUtility.insertWithDel(l_eqTypeClosingContractSpecParams1);

            EqtypeContractParams l_eqTypeContractParams =
                TestDBUtility.getEqtypeContractRow();
            l_eqTypeContractParams.setContractId(2134566345L);
            l_eqTypeContractParams.setContractPrice(1000);

            TestDBUtility.insertWithDel(l_eqTypeContractParams);

            FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
            EqTypeOrderManager l_eqTypeOrderManager =
                (EqTypeOrderManager)l_finApp.getTradingModule(ProductTypeEnum.EQUITY).getOrderManager();
            EqTypeOrderUnit l_orderUnit = (EqTypeOrderUnit) l_eqTypeOrderManager.getOrderUnit(3304148080001L);
            assertEquals("2",impl.getContractPrice(l_orderUnit));
            fail();
        }
        catch (WEB3SystemLayerException l_web3SystemException)
        {
            assertEquals(WEB3SystemLayerException.class,l_web3SystemException.getClass());
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80002,l_web3SystemException.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    // CLOSE_MARGIN SWAP_MARGIN != l_orderCateg のテスト
    public void testgetContractPrice_C0005()
    {
        final String STR_METHOD_NAME = "testgetContractPrice_C0005()";
        log.entering(STR_METHOD_NAME);
        try
        {

            EqtypeOrderUnitParams l_eqTypeOrderUnitParams1 = TestDBUtility.getEqtypeOrderUnitRow();
            l_eqTypeOrderUnitParams1.setProductType(ProductTypeEnum.EQUITY);
            l_eqTypeOrderUnitParams1.setOrderUnitId(3304148080001L);
            l_eqTypeOrderUnitParams1.setExecutedQuantity(12.23D);
            l_eqTypeOrderUnitParams1.setConfirmedQuantity(22.23D);
            l_eqTypeOrderUnitParams1.setOrderCateg(OrderCategEnum.UNDEFINED);
            
            TestDBUtility.insertWithDel(l_eqTypeOrderUnitParams1);

            FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
            EqTypeOrderManager l_eqTypeOrderManager =
                (EqTypeOrderManager)l_finApp.getTradingModule(ProductTypeEnum.EQUITY).getOrderManager();
            EqTypeOrderUnit l_orderUnit = (EqTypeOrderUnit) l_eqTypeOrderManager.getOrderUnit(3304148080001L);
            
            assertEquals(null,impl.getContractPrice(l_orderUnit));
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    // CLOSE_MARGIN SWAP_MARGIN != l_orderCateg のテスト
    public void testgetContractPrice_C0006()
    {
        final String STR_METHOD_NAME = "testgetContractPrice_C0006()";
        log.entering(STR_METHOD_NAME);
        try
        {

            EqtypeOrderUnitParams l_eqTypeOrderUnitParams1 = TestDBUtility.getEqtypeOrderUnitRow();
            l_eqTypeOrderUnitParams1.setProductType(ProductTypeEnum.EQUITY);
            l_eqTypeOrderUnitParams1.setOrderUnitId(3304148080001L);
            l_eqTypeOrderUnitParams1.setExecutedQuantity(12.23D);
            l_eqTypeOrderUnitParams1.setConfirmedQuantity(22.23D);
            l_eqTypeOrderUnitParams1.setOrderCateg(OrderCategEnum.ASSET);
            
            TestDBUtility.insertWithDel(l_eqTypeOrderUnitParams1);

            FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
            EqTypeOrderManager l_eqTypeOrderManager =
                (EqTypeOrderManager)l_finApp.getTradingModule(ProductTypeEnum.EQUITY).getOrderManager();
            EqTypeOrderUnit l_orderUnit = (EqTypeOrderUnit) l_eqTypeOrderManager.getOrderUnit(3304148080001L);
            
            assertEquals(null,impl.getContractPrice(l_orderUnit));
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    // CLOSE_MARGIN SWAP_MARGIN != l_orderCateg のテスト
    public void testgetContractPrice_C0007()
    {
        final String STR_METHOD_NAME = "testgetContractPrice_C0007()";
        log.entering(STR_METHOD_NAME);
        try
        {

            EqtypeOrderUnitParams l_eqTypeOrderUnitParams1 = TestDBUtility.getEqtypeOrderUnitRow();
            l_eqTypeOrderUnitParams1.setProductType(ProductTypeEnum.EQUITY);
            l_eqTypeOrderUnitParams1.setOrderUnitId(3304148080001L);
            l_eqTypeOrderUnitParams1.setExecutedQuantity(12.23D);
            l_eqTypeOrderUnitParams1.setConfirmedQuantity(22.23D);
            l_eqTypeOrderUnitParams1.setOrderCateg(OrderCategEnum.ASSET_IN_OUT);
            
            TestDBUtility.insertWithDel(l_eqTypeOrderUnitParams1);

            FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
            EqTypeOrderManager l_eqTypeOrderManager =
                (EqTypeOrderManager)l_finApp.getTradingModule(ProductTypeEnum.EQUITY).getOrderManager();
            EqTypeOrderUnit l_orderUnit = (EqTypeOrderUnit) l_eqTypeOrderManager.getOrderUnit(3304148080001L);
            
            assertEquals(null,impl.getContractPrice(l_orderUnit));
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    // CLOSE_MARGIN SWAP_MARGIN != l_orderCateg のテスト
    public void testgetContractPrice_C0008()
    {
        final String STR_METHOD_NAME = "testgetContractPrice_C0008()";
        log.entering(STR_METHOD_NAME);
        try
        {

            EqtypeOrderUnitParams l_eqTypeOrderUnitParams1 = TestDBUtility.getEqtypeOrderUnitRow();
            l_eqTypeOrderUnitParams1.setProductType(ProductTypeEnum.EQUITY);
            l_eqTypeOrderUnitParams1.setOrderUnitId(3304148080001L);
            l_eqTypeOrderUnitParams1.setExecutedQuantity(12.23D);
            l_eqTypeOrderUnitParams1.setConfirmedQuantity(22.23D);
            l_eqTypeOrderUnitParams1.setOrderCateg(OrderCategEnum.IDX_FUTURES_OPEN);
            
            TestDBUtility.insertWithDel(l_eqTypeOrderUnitParams1);

            FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
            EqTypeOrderManager l_eqTypeOrderManager =
                (EqTypeOrderManager)l_finApp.getTradingModule(ProductTypeEnum.EQUITY).getOrderManager();
            EqTypeOrderUnit l_orderUnit = (EqTypeOrderUnit) l_eqTypeOrderManager.getOrderUnit(3304148080001L);
            
            assertEquals(null,impl.getContractPrice(l_orderUnit));
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    // CLOSE_MARGIN SWAP_MARGIN != l_orderCateg のテスト
    public void testgetContractPrice_C0009()
    {
        final String STR_METHOD_NAME = "testgetContractPrice_C0009()";
        log.entering(STR_METHOD_NAME);
        try
        {

            EqtypeOrderUnitParams l_eqTypeOrderUnitParams1 = TestDBUtility.getEqtypeOrderUnitRow();
            l_eqTypeOrderUnitParams1.setProductType(ProductTypeEnum.EQUITY);
            l_eqTypeOrderUnitParams1.setOrderUnitId(3304148080001L);
            l_eqTypeOrderUnitParams1.setExecutedQuantity(12.23D);
            l_eqTypeOrderUnitParams1.setConfirmedQuantity(22.23D);
            l_eqTypeOrderUnitParams1.setOrderCateg(OrderCategEnum.IDX_FUTURES_CLOSE);
            
            TestDBUtility.insertWithDel(l_eqTypeOrderUnitParams1);

            FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
            EqTypeOrderManager l_eqTypeOrderManager =
                (EqTypeOrderManager)l_finApp.getTradingModule(ProductTypeEnum.EQUITY).getOrderManager();
            EqTypeOrderUnit l_orderUnit = (EqTypeOrderUnit) l_eqTypeOrderManager.getOrderUnit(3304148080001L);
            
            assertEquals(null,impl.getContractPrice(l_orderUnit));
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    // CLOSE_MARGIN SWAP_MARGIN != l_orderCateg のテスト
    public void testgetContractPrice_C0010()
    {
        final String STR_METHOD_NAME = "testgetContractPrice_C0010()";
        log.entering(STR_METHOD_NAME);
        try
        {

            EqtypeOrderUnitParams l_eqTypeOrderUnitParams1 = TestDBUtility.getEqtypeOrderUnitRow();
            l_eqTypeOrderUnitParams1.setProductType(ProductTypeEnum.EQUITY);
            l_eqTypeOrderUnitParams1.setOrderUnitId(3304148080001L);
            l_eqTypeOrderUnitParams1.setExecutedQuantity(12.23D);
            l_eqTypeOrderUnitParams1.setConfirmedQuantity(22.23D);
            l_eqTypeOrderUnitParams1.setOrderCateg(OrderCategEnum.IDX_OPTIONS_OPEN);
            
            TestDBUtility.insertWithDel(l_eqTypeOrderUnitParams1);

            FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
            EqTypeOrderManager l_eqTypeOrderManager =
                (EqTypeOrderManager)l_finApp.getTradingModule(ProductTypeEnum.EQUITY).getOrderManager();
            EqTypeOrderUnit l_orderUnit = (EqTypeOrderUnit) l_eqTypeOrderManager.getOrderUnit(3304148080001L);
            
            assertEquals(null,impl.getContractPrice(l_orderUnit));
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    // CLOSE_MARGIN SWAP_MARGIN != l_orderCateg のテスト
    public void testgetContractPrice_C0011()
    {
        final String STR_METHOD_NAME = "testgetContractPrice_C0011()";
        log.entering(STR_METHOD_NAME);
        try
        {

            EqtypeOrderUnitParams l_eqTypeOrderUnitParams1 = TestDBUtility.getEqtypeOrderUnitRow();
            l_eqTypeOrderUnitParams1.setProductType(ProductTypeEnum.EQUITY);
            l_eqTypeOrderUnitParams1.setOrderUnitId(3304148080001L);
            l_eqTypeOrderUnitParams1.setExecutedQuantity(12.23D);
            l_eqTypeOrderUnitParams1.setConfirmedQuantity(22.23D);
            l_eqTypeOrderUnitParams1.setOrderCateg(OrderCategEnum.IDX_OPTIONS_CLOSE);
            
            TestDBUtility.insertWithDel(l_eqTypeOrderUnitParams1);

            FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
            EqTypeOrderManager l_eqTypeOrderManager =
                (EqTypeOrderManager)l_finApp.getTradingModule(ProductTypeEnum.EQUITY).getOrderManager();
            EqTypeOrderUnit l_orderUnit = (EqTypeOrderUnit) l_eqTypeOrderManager.getOrderUnit(3304148080001L);
            
            assertEquals(null,impl.getContractPrice(l_orderUnit));
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    // CLOSE_MARGIN SWAP_MARGIN != l_orderCateg のテスト
    public void testgetContractPrice_C0012()
    {
        final String STR_METHOD_NAME = "testgetContractPrice_C0012()";
        log.entering(STR_METHOD_NAME);
        try
        {

            EqtypeOrderUnitParams l_eqTypeOrderUnitParams1 = TestDBUtility.getEqtypeOrderUnitRow();
            l_eqTypeOrderUnitParams1.setProductType(ProductTypeEnum.EQUITY);
            l_eqTypeOrderUnitParams1.setOrderUnitId(3304148080001L);
            l_eqTypeOrderUnitParams1.setExecutedQuantity(12.23D);
            l_eqTypeOrderUnitParams1.setConfirmedQuantity(22.23D);
            l_eqTypeOrderUnitParams1.setOrderCateg(OrderCategEnum.CASH_TRANSFER);
            
            TestDBUtility.insertWithDel(l_eqTypeOrderUnitParams1);

            FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
            EqTypeOrderManager l_eqTypeOrderManager =
                (EqTypeOrderManager)l_finApp.getTradingModule(ProductTypeEnum.EQUITY).getOrderManager();
            EqTypeOrderUnit l_orderUnit = (EqTypeOrderUnit) l_eqTypeOrderManager.getOrderUnit(3304148080001L);
            
            assertEquals(null,impl.getContractPrice(l_orderUnit));
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    // CLOSE_MARGIN SWAP_MARGIN != l_orderCateg のテスト
    public void testgetContractPrice_C0013()
    {
        final String STR_METHOD_NAME = "testgetContractPrice_C0013()";
        log.entering(STR_METHOD_NAME);
        try
        {

            EqtypeOrderUnitParams l_eqTypeOrderUnitParams1 = TestDBUtility.getEqtypeOrderUnitRow();
            l_eqTypeOrderUnitParams1.setProductType(ProductTypeEnum.EQUITY);
            l_eqTypeOrderUnitParams1.setOrderUnitId(3304148080001L);
            l_eqTypeOrderUnitParams1.setExecutedQuantity(12.23D);
            l_eqTypeOrderUnitParams1.setConfirmedQuantity(22.23D);
            l_eqTypeOrderUnitParams1.setOrderCateg(OrderCategEnum.ASSET_TRANSFER);
            
            TestDBUtility.insertWithDel(l_eqTypeOrderUnitParams1);

            FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
            EqTypeOrderManager l_eqTypeOrderManager =
                (EqTypeOrderManager)l_finApp.getTradingModule(ProductTypeEnum.EQUITY).getOrderManager();
            EqTypeOrderUnit l_orderUnit = (EqTypeOrderUnit) l_eqTypeOrderManager.getOrderUnit(3304148080001L);
            
            assertEquals(null,impl.getContractPrice(l_orderUnit));
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    // CLOSE_MARGIN SWAP_MARGIN != l_orderCateg のテスト
    public void testgetContractPrice_C0014()
    {
        final String STR_METHOD_NAME = "testgetContractPrice_C0014()";
        log.entering(STR_METHOD_NAME);
        try
        {

            EqtypeOrderUnitParams l_eqTypeOrderUnitParams1 = TestDBUtility.getEqtypeOrderUnitRow();
            l_eqTypeOrderUnitParams1.setProductType(ProductTypeEnum.EQUITY);
            l_eqTypeOrderUnitParams1.setOrderUnitId(3304148080001L);
            l_eqTypeOrderUnitParams1.setExecutedQuantity(12.23D);
            l_eqTypeOrderUnitParams1.setConfirmedQuantity(22.23D);
            l_eqTypeOrderUnitParams1.setOrderCateg(OrderCategEnum.FX);
            
            TestDBUtility.insertWithDel(l_eqTypeOrderUnitParams1);

            FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
            EqTypeOrderManager l_eqTypeOrderManager =
                (EqTypeOrderManager)l_finApp.getTradingModule(ProductTypeEnum.EQUITY).getOrderManager();
            EqTypeOrderUnit l_orderUnit = (EqTypeOrderUnit) l_eqTypeOrderManager.getOrderUnit(3304148080001L);
            
            assertEquals(null,impl.getContractPrice(l_orderUnit));
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    // CLOSE_MARGIN SWAP_MARGIN != l_orderCateg のテスト
    public void testgetContractPrice_C0015()
    {
        final String STR_METHOD_NAME = "testgetContractPrice_C0015()";
        log.entering(STR_METHOD_NAME);
        try
        {

            EqtypeOrderUnitParams l_eqTypeOrderUnitParams1 = TestDBUtility.getEqtypeOrderUnitRow();
            l_eqTypeOrderUnitParams1.setProductType(ProductTypeEnum.EQUITY);
            l_eqTypeOrderUnitParams1.setOrderUnitId(3304148080001L);
            l_eqTypeOrderUnitParams1.setExecutedQuantity(12.23D);
            l_eqTypeOrderUnitParams1.setConfirmedQuantity(22.23D);
            l_eqTypeOrderUnitParams1.setOrderCateg(OrderCategEnum.FEQ_TRANSFER);
            
            TestDBUtility.insertWithDel(l_eqTypeOrderUnitParams1);

            FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
            EqTypeOrderManager l_eqTypeOrderManager =
                (EqTypeOrderManager)l_finApp.getTradingModule(ProductTypeEnum.EQUITY).getOrderManager();
            EqTypeOrderUnit l_orderUnit = (EqTypeOrderUnit) l_eqTypeOrderManager.getOrderUnit(3304148080001L);
            
            assertEquals(null,impl.getContractPrice(l_orderUnit));
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    // l_specs = 1 かつ OrderCategEnum.SWAP_MARGIN のテスト 
    public void testGetContractPrice_C0016()
    {
        final String STR_METHOD_NAME = "testGetContractPrice_C0016()";
        log.entering(STR_METHOD_NAME);
        try
        {
            EqtypeOrderUnitParams l_eqTypeOrderUnitParams1 = TestDBUtility.getEqtypeOrderUnitRow();
            l_eqTypeOrderUnitParams1.setProductType(ProductTypeEnum.EQUITY);
            l_eqTypeOrderUnitParams1.setOrderUnitId(3304148080001L);
            l_eqTypeOrderUnitParams1.setExecutedQuantity(12.23D);
            l_eqTypeOrderUnitParams1.setConfirmedQuantity(22.23D);
            l_eqTypeOrderUnitParams1.setOrderCateg(OrderCategEnum.SWAP_MARGIN);
            l_eqTypeOrderUnitParams1.setClosingOrderType(null);

            TestDBUtility.insertWithDel(l_eqTypeOrderUnitParams1);
            
            EqtypeClosingContractSpecParams l_eqTypeClosingContractSpecParams1 = 
                TestDBUtility.getEqtypeClosingContractSpecRow();
            l_eqTypeClosingContractSpecParams1.setClosingContractSpecId(3304148080001L);
            l_eqTypeClosingContractSpecParams1.setQuantity(2000);

            TestDBUtility.insertWithDel(l_eqTypeClosingContractSpecParams1);

            EqtypeContractParams l_eqTypeContractParams =
                TestDBUtility.getEqtypeContractRow();
            l_eqTypeContractParams.setContractId(2134566345L);
            l_eqTypeContractParams.setContractPrice(1000);

            TestDBUtility.insertWithDel(l_eqTypeContractParams);

            FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
            EqTypeOrderManager l_eqTypeOrderManager =
                (EqTypeOrderManager)l_finApp.getTradingModule(ProductTypeEnum.EQUITY).getOrderManager();
            EqTypeOrderUnit l_orderUnit = (EqTypeOrderUnit) l_eqTypeOrderManager.getOrderUnit(3304148080001L);

            assertEquals("1000",impl.getContractPrice(l_orderUnit));
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    // l_specs = 3 かつ OrderCategEnum.SWAP_MARGIN のテスト
    public void testGetContractPrice_C0017()
    {
        final String STR_METHOD_NAME = "testGetContractPrice_C0017()";
        log.entering(STR_METHOD_NAME);
        try
        {
            EqtypeOrderUnitParams l_eqTypeOrderUnitParams1 = TestDBUtility.getEqtypeOrderUnitRow();
            l_eqTypeOrderUnitParams1.setProductType(ProductTypeEnum.EQUITY);
            l_eqTypeOrderUnitParams1.setOrderUnitId(3304148080001L);
            l_eqTypeOrderUnitParams1.setExecutedQuantity(12.21D);
            l_eqTypeOrderUnitParams1.setConfirmedQuantity(22.21D);
            l_eqTypeOrderUnitParams1.setOrderCateg(OrderCategEnum.SWAP_MARGIN);
            l_eqTypeOrderUnitParams1.setClosingOrderType(null);
            TestDBUtility.insertWithDel(l_eqTypeOrderUnitParams1);
                        
            EqtypeClosingContractSpecParams l_eqTypeClosingContractSpecParams1 = 
                TestDBUtility.getEqtypeClosingContractSpecRow();
            
            l_eqTypeClosingContractSpecParams1.setQuantity(1000);
            l_eqTypeClosingContractSpecParams1.setOrderUnitId(3304148080001L);
            l_eqTypeClosingContractSpecParams1.setContractId(2134566345L);
            TestDBUtility.insertWithDel(l_eqTypeClosingContractSpecParams1);
            
            EqtypeClosingContractSpecParams l_eqTypeClosingContractSpecParams2 = 
                TestDBUtility.getEqtypeClosingContractSpecRow();
            l_eqTypeClosingContractSpecParams2.setClosingContractSpecId(33445566778800L);
            l_eqTypeClosingContractSpecParams2.setOrderUnitId(3304148080001L);
            l_eqTypeClosingContractSpecParams2.setQuantity(2000);
            l_eqTypeClosingContractSpecParams2.setContractId(2134566346L);
            l_eqTypeClosingContractSpecParams2.setClosingSerialNo(112);
            TestDBUtility.insertWithDel(l_eqTypeClosingContractSpecParams2);
            
            EqtypeClosingContractSpecParams l_eqTypeClosingContractSpecParams3 = 
                TestDBUtility.getEqtypeClosingContractSpecRow();
            l_eqTypeClosingContractSpecParams3.setClosingContractSpecId(33445566778833L);
            l_eqTypeClosingContractSpecParams3.setOrderUnitId(3304148080001L);
            l_eqTypeClosingContractSpecParams3.setQuantity(3000);
            l_eqTypeClosingContractSpecParams3.setContractId(2134566347L);
            l_eqTypeClosingContractSpecParams3.setClosingSerialNo(113);
            TestDBUtility.insertWithDel(l_eqTypeClosingContractSpecParams3);
           

            EqtypeContractParams l_eqTypeContractParams1 =
                TestDBUtility.getEqtypeContractRow();
            l_eqTypeContractParams1.setContractId(2134566345L);
            l_eqTypeContractParams1.setContractPrice(1000);
            TestDBUtility.insertWithDel(l_eqTypeContractParams1);
            
            EqtypeContractParams l_eqTypeContractParams2 =
                TestDBUtility.getEqtypeContractRow();
            l_eqTypeContractParams2.setContractId(2134566346L);
            l_eqTypeContractParams2.setContractPrice(1800);
            TestDBUtility.insertWithDel(l_eqTypeContractParams2);
            
            EqtypeContractParams l_eqTypeContractParams3 =
                TestDBUtility.getEqtypeContractRow();
            l_eqTypeContractParams3.setContractId(2134566347L);
            l_eqTypeContractParams3.setContractPrice(1900);
            TestDBUtility.insertWithDel(l_eqTypeContractParams3);
            

            FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
            EqTypeOrderManager l_eqTypeOrderManager =
                (EqTypeOrderManager)l_finApp.getTradingModule(ProductTypeEnum.EQUITY).getOrderManager();
            EqTypeOrderUnit l_orderUnit = (EqTypeOrderUnit) l_eqTypeOrderManager.getOrderUnit(3304148080001L);

            assertEquals("1717",impl.getContractPrice(l_orderUnit));
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    public void testGetContractPrice_C0018()
    {
        final String STR_METHOD_NAME = "testGetContractPrice_C0018()";
        log.entering(STR_METHOD_NAME);
        try
        {
            EqtypeClosingContractSpecParams l_eqTypeClosingContractSpecParams = new EqtypeClosingContractSpecParams();
            QueryProcessor l_processor = Processors.getDefaultProcessor();
            l_processor.doDeleteAllQuery(l_eqTypeClosingContractSpecParams.getRowType());
            
            EqtypeOrderUnitParams l_eqTypeOrderUnitParams1 = TestDBUtility.getEqtypeOrderUnitRow();
            l_eqTypeOrderUnitParams1.setProductType(ProductTypeEnum.EQUITY);
            l_eqTypeOrderUnitParams1.setOrderUnitId(3304148080001L);
            l_eqTypeOrderUnitParams1.setExecutedQuantity(12.23D);
            l_eqTypeOrderUnitParams1.setConfirmedQuantity(22.23D);
            l_eqTypeOrderUnitParams1.setOrderCateg(OrderCategEnum.SWAP_MARGIN);
            l_eqTypeOrderUnitParams1.setClosingOrderType(null);

            TestDBUtility.insertWithDel(l_eqTypeOrderUnitParams1);
            
            EqtypeClosingContractSpecParams l_eqTypeClosingContractSpecParams1 = 
                TestDBUtility.getEqtypeClosingContractSpecRow();
            l_eqTypeClosingContractSpecParams1.setClosingContractSpecId(33445566778829L);
            l_eqTypeClosingContractSpecParams1.setOrderUnitId(3304148080010L);
            l_eqTypeClosingContractSpecParams1.setQuantity(2000);

            l_eqTypeClosingContractSpecParams1.setContractId(2134566348L);
            l_eqTypeClosingContractSpecParams1.setClosingSerialNo(114);
            TestDBUtility.insertWithDel(l_eqTypeClosingContractSpecParams1);

            EqtypeContractParams l_eqTypeContractParams =
                TestDBUtility.getEqtypeContractRow();
            l_eqTypeContractParams.setContractId(2134566345L);
            l_eqTypeContractParams.setContractPrice(1000);

            TestDBUtility.insertWithDel(l_eqTypeContractParams);

            FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
            EqTypeOrderManager l_eqTypeOrderManager =
                (EqTypeOrderManager)l_finApp.getTradingModule(ProductTypeEnum.EQUITY).getOrderManager();
            EqTypeOrderUnit l_orderUnit = (EqTypeOrderUnit) l_eqTypeOrderManager.getOrderUnit(3304148080001L);
            assertEquals("2",impl.getContractPrice(l_orderUnit));
            fail();
        }
        catch (WEB3SystemLayerException l_web3SystemException)
        {
            assertEquals(WEB3SystemLayerException.class,l_web3SystemException.getClass());
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80002,l_web3SystemException.getErrorInfo());
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
