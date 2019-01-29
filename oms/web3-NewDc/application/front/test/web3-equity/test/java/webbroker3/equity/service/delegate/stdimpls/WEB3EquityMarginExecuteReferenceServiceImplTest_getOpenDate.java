head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.00.57.15;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3EquityMarginExecuteReferenceServiceImplTest_getOpenDate.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.equity.service.delegate.stdimpls;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeClosingContractSpec;
import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeContractSettleOrderUnit;
import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeContractSwapOrderUnit;
import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeOrderManager;
import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeOrderUnit;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeClosingContractSpecParams;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeContractParams;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderUnitParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderCategEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule;

import test.util.TestDBUtility;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.equity.WEB3EquityContract;
import webbroker3.equity.WEB3EquityPositionManager;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3LogUtility;

public class WEB3EquityMarginExecuteReferenceServiceImplTest_getOpenDate extends TestBaseForMock
{
    /**
     * ログ出力ユーティリティ。<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3EquityMarginExecuteReferenceServiceImplTest_getOpenDate.class);
    WEB3EquityMarginExecuteReferenceServiceImpl impl =
        new WEB3EquityMarginExecuteReferenceServiceImpl();

    public WEB3EquityMarginExecuteReferenceServiceImplTest_getOpenDate(String arg0)
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
     * getOpenDate(EqTypeOrderUnit l_orderUnit)'
     */
    
    // １）引数.注文単位.注文カテゴリ == ”新規建注文” の場合
    // null を返却する。のテスト
    public void testGetOpenDate_C0001()
    {
        final String STR_METHOD_NAME = "testGetOpenDate_C0001()";
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
            
            assertEquals(null,impl.getOpenDate(l_orderUnit));
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    // ２－２）引数.注文単位.決済順序区分 != null の場合
    // null を返却する。のテスト
    public void testGetOpenDate_C0002()
    {
        final String STR_METHOD_NAME = "testGetOpenDate_C0002()";
        log.entering(STR_METHOD_NAME);
        try
        {

            EqtypeOrderUnitParams l_eqTypeOrderUnitParams1 = TestDBUtility.getEqtypeOrderUnitRow();
            l_eqTypeOrderUnitParams1.setProductType(ProductTypeEnum.EQUITY);
            l_eqTypeOrderUnitParams1.setOrderUnitId(3304148080001L);
            l_eqTypeOrderUnitParams1.setExecutedQuantity(12.23D);
            l_eqTypeOrderUnitParams1.setConfirmedQuantity(22.23D);
            l_eqTypeOrderUnitParams1.setOrderCateg(OrderCategEnum.CLOSE_MARGIN);
            l_eqTypeOrderUnitParams1.setClosingOrderType("1");
            
            TestDBUtility.insertWithDel(l_eqTypeOrderUnitParams1);

            FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
            EqTypeOrderManager l_eqTypeOrderManager =
                (EqTypeOrderManager)l_finApp.getTradingModule(ProductTypeEnum.EQUITY).getOrderManager();
            EqTypeOrderUnit l_orderUnit = (EqTypeOrderUnit) l_eqTypeOrderManager.getOrderUnit(3304148080001L);
            
            assertEquals(null,impl.getOpenDate(l_orderUnit));
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    // 取得した建株.建日を返却する。のテスト
    public void testGetOpenDate_C0003()
    {
        final String STR_METHOD_NAME = "testGetOpenDate_C0003()";
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
            
            EqtypeClosingContractSpecParams l_eqTypeClosingContractSpecParams = 
                TestDBUtility.getEqtypeClosingContractSpecRow();
            l_eqTypeClosingContractSpecParams.setClosingContractSpecId(3304148080001L);
            
            TestDBUtility.insertWithDel(l_eqTypeClosingContractSpecParams);
            
            EqtypeContractParams l_eqTypeContractParams =
                TestDBUtility.getEqtypeContractRow();
            
            TestDBUtility.insertWithDel(l_eqTypeContractParams);

            FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
            TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
            WEB3EquityPositionManager l_positionManager = 
                (WEB3EquityPositionManager) l_tradingModule.getPositionManager();
            EqTypeOrderManager l_eqTypeOrderManager =
                (EqTypeOrderManager)l_finApp.getTradingModule(ProductTypeEnum.EQUITY).getOrderManager();
            EqTypeOrderUnit l_orderUnit = (EqTypeOrderUnit) l_eqTypeOrderManager.getOrderUnit(3304148080001L);
            
            EqTypeClosingContractSpec[] l_arrSpecs = 
                ((EqTypeContractSettleOrderUnit) l_orderUnit).getContractsToClose();
            WEB3EquityContract l_contract = 
                (WEB3EquityContract) l_positionManager.getContract(l_arrSpecs[0].getContractId());
            assertEquals(l_contract.getOpenDate(),impl.getOpenDate(l_orderUnit));
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    // CLOSE_MARGIN SWAP_MARGIN != l_orderCateg のテスト
    public void testGetOpenDate_C0004()
    {
        final String STR_METHOD_NAME = "testGetOpenDate_C0004()";
        log.entering(STR_METHOD_NAME);
        try
        {

            EqtypeOrderUnitParams l_eqTypeOrderUnitParams1 = TestDBUtility.getEqtypeOrderUnitRow();
            l_eqTypeOrderUnitParams1.setProductType(ProductTypeEnum.EQUITY);
            l_eqTypeOrderUnitParams1.setOrderUnitId(3304148080001L);
            l_eqTypeOrderUnitParams1.setExecutedQuantity(12.23D);
            l_eqTypeOrderUnitParams1.setConfirmedQuantity(22.23D);
            l_eqTypeOrderUnitParams1.setOrderCateg(OrderCategEnum.CASH_IN_OUT);
            
            TestDBUtility.insertWithDel(l_eqTypeOrderUnitParams1);

            FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
            EqTypeOrderManager l_eqTypeOrderManager =
                (EqTypeOrderManager)l_finApp.getTradingModule(ProductTypeEnum.EQUITY).getOrderManager();
            EqTypeOrderUnit l_orderUnit = (EqTypeOrderUnit) l_eqTypeOrderManager.getOrderUnit(3304148080001L);
            
            assertEquals(null,impl.getOpenDate(l_orderUnit));
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    // CLOSE_MARGIN SWAP_MARGIN != l_orderCateg のテスト
    public void testGetOpenDate_C0005()
    {
        final String STR_METHOD_NAME = "testGetOpenDate_C0005()";
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
            
            assertEquals(null,impl.getOpenDate(l_orderUnit));
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    // CLOSE_MARGIN SWAP_MARGIN != l_orderCateg のテスト
    public void testGetOpenDate_C0006()
    {
        final String STR_METHOD_NAME = "testGetOpenDate_C0006()";
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
            
            assertEquals(null,impl.getOpenDate(l_orderUnit));
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    // CLOSE_MARGIN SWAP_MARGIN != l_orderCateg のテスト
    public void testGetOpenDate_C0007()
    {
        final String STR_METHOD_NAME = "testGetOpenDate_C0007()";
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
            
            assertEquals(null,impl.getOpenDate(l_orderUnit));
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    // CLOSE_MARGIN SWAP_MARGIN != l_orderCateg のテスト
    public void testGetOpenDate_C0008()
    {
        final String STR_METHOD_NAME = "testGetOpenDate_C0008()";
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
            
            assertEquals(null,impl.getOpenDate(l_orderUnit));
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    // CLOSE_MARGIN SWAP_MARGIN != l_orderCateg のテスト
    public void testGetOpenDate_C0009()
    {
        final String STR_METHOD_NAME = "testGetOpenDate_C0009()";
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
            
            assertEquals(null,impl.getOpenDate(l_orderUnit));
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    // CLOSE_MARGIN SWAP_MARGIN != l_orderCateg のテスト
    public void testGetOpenDate_C0010()
    {
        final String STR_METHOD_NAME = "testGetOpenDate_C0010()";
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
            
            assertEquals(null,impl.getOpenDate(l_orderUnit));
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    // CLOSE_MARGIN SWAP_MARGIN != l_orderCateg のテスト
    public void testGetOpenDate_C0011()
    {
        final String STR_METHOD_NAME = "testGetOpenDate_C0011()";
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
            
            assertEquals(null,impl.getOpenDate(l_orderUnit));
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    // CLOSE_MARGIN SWAP_MARGIN != l_orderCateg のテスト
    public void testGetOpenDate_C0012()
    {
        final String STR_METHOD_NAME = "testGetOpenDate_C0012()";
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
            
            assertEquals(null,impl.getOpenDate(l_orderUnit));
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    // CLOSE_MARGIN SWAP_MARGIN != l_orderCateg のテスト
    public void testGetOpenDate_C0013()
    {
        final String STR_METHOD_NAME = "testGetOpenDate_C0013()";
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
            
            assertEquals(null,impl.getOpenDate(l_orderUnit));
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    // CLOSE_MARGIN SWAP_MARGIN != l_orderCateg のテスト
    public void testGetOpenDate_C0014()
    {
        final String STR_METHOD_NAME = "testGetOpenDate_C0014()";
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
            
            assertEquals(null,impl.getOpenDate(l_orderUnit));
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    // CLOSE_MARGIN SWAP_MARGIN != l_orderCateg のテスト
    public void testGetOpenDate_C0015()
    {
        final String STR_METHOD_NAME = "testGetOpenDate_C0015()";
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
            
            assertEquals(null,impl.getOpenDate(l_orderUnit));
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    public void testGetOpenDate_C00016()
    {
        final String STR_METHOD_NAME = "testGetOpenDate_C00016()";
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
            
            EqtypeClosingContractSpecParams l_eqTypeClosingContractSpecParams = 
                TestDBUtility.getEqtypeClosingContractSpecRow();
            l_eqTypeClosingContractSpecParams.setClosingContractSpecId(3304148080001L);
            
            TestDBUtility.insertWithDel(l_eqTypeClosingContractSpecParams);
            
            EqtypeContractParams l_eqTypeContractParams =
                TestDBUtility.getEqtypeContractRow();
            
            TestDBUtility.insertWithDel(l_eqTypeContractParams);

            FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
            TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
            WEB3EquityPositionManager l_positionManager = 
                (WEB3EquityPositionManager) l_tradingModule.getPositionManager();
            EqTypeOrderManager l_eqTypeOrderManager =
                (EqTypeOrderManager)l_finApp.getTradingModule(ProductTypeEnum.EQUITY).getOrderManager();
            EqTypeOrderUnit l_orderUnit = (EqTypeOrderUnit) l_eqTypeOrderManager.getOrderUnit(3304148080001L);
            
            EqTypeClosingContractSpec[] l_arrSpecs = 
                ((EqTypeContractSettleOrderUnit) l_orderUnit).getContractsToClose();
            WEB3EquityContract l_contract = 
                (WEB3EquityContract) l_positionManager.getContract(l_arrSpecs[0].getContractId());
            assertEquals(l_contract.getOpenDate(),impl.getOpenDate(l_orderUnit));
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    public void testGetOpenDate_C00017()
    {
        final String STR_METHOD_NAME = "testGetOpenDate_C00017()";
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
            
            EqtypeClosingContractSpecParams l_eqTypeClosingContractSpecParams = 
                TestDBUtility.getEqtypeClosingContractSpecRow();
            l_eqTypeClosingContractSpecParams.setClosingContractSpecId(3304148080001L);
            
            TestDBUtility.insertWithDel(l_eqTypeClosingContractSpecParams);
            
            EqtypeContractParams l_eqTypeContractParams =
                TestDBUtility.getEqtypeContractRow();
            
            TestDBUtility.insertWithDel(l_eqTypeContractParams);

            FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
            TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
            WEB3EquityPositionManager l_positionManager = 
                (WEB3EquityPositionManager) l_tradingModule.getPositionManager();
            EqTypeOrderManager l_eqTypeOrderManager =
                (EqTypeOrderManager)l_finApp.getTradingModule(ProductTypeEnum.EQUITY).getOrderManager();
            EqTypeOrderUnit l_orderUnit = (EqTypeOrderUnit) l_eqTypeOrderManager.getOrderUnit(3304148080001L);
            
            EqTypeClosingContractSpec[] l_arrSpecs = 
                ((EqTypeContractSwapOrderUnit) l_orderUnit).getContractsToClose();
            WEB3EquityContract l_contract = 
                (WEB3EquityContract) l_positionManager.getContract(l_arrSpecs[0].getContractId());
            assertEquals(l_contract.getOpenDate(),impl.getOpenDate(l_orderUnit));
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }
}@
