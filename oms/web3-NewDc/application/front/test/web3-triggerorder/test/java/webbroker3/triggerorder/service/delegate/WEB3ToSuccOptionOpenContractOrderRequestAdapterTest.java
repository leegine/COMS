head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.02.34.06;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3ToSuccOptionOpenContractOrderRequestAdapterTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 
Author Name      : Daiwa Institute of Research
Revesion History : 
*/
package webbroker3.triggerorder.service.delegate;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderUnit;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MarketParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.ProductParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.TradedProductParams;
import com.fitechlabs.xtrade.plugin.tc.xbifo.IfoOrderUnit;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoOrderUnitParams;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoProductParams;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoTradedProductParams;

import test.util.TestDBUtility;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.ifo.WEB3OptionOrderManagerImpl;
import webbroker3.mock.TestBaseForMock;
import webbroker3.triggerorder.message.WEB3SuccCommonInfo;
import webbroker3.triggerorder.message.WEB3SuccEquityBuyInputRequest;
import webbroker3.triggerorder.message.WEB3SuccOptionsCloseConfirmRequest;
import webbroker3.triggerorder.message.WEB3SuccOptionsOpenCompleteRequest;
import webbroker3.triggerorder.message.WEB3SuccOptionsOpenConfirmRequest;
import webbroker3.triggerorder.message.WEB3SuccPriceAdjustmentValueInfo;
import webbroker3.util.WEB3LogUtility;

public class WEB3ToSuccOptionOpenContractOrderRequestAdapterTest extends TestBaseForMock
{
    /**
     * (ログ出力ユーティリティ。)<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3ToSuccOptionOpenContractOrderRequestAdapterTest.class);
    
    
    WEB3ToSuccOptionOpenContractOrderRequestAdapter l_adapter = null;
    
    public WEB3ToSuccOptionOpenContractOrderRequestAdapterTest(String name)
    {
        super(name);
    }

    protected void setUp() throws Exception
    {
        l_adapter = new WEB3ToSuccOptionOpenContractOrderRequestAdapter();
        TestDBUtility.deleteAll(IfoOrderUnitParams.TYPE);
        TestDBUtility.deleteAll(ProductParams.TYPE);
        TestDBUtility.deleteAll(IfoProductParams.TYPE);
        TestDBUtility.deleteAll(TradedProductParams.TYPE);
        TestDBUtility.deleteAll(IfoTradedProductParams.TYPE);
        TestDBUtility.deleteAll(MarketParams.TYPE);
        
        super.setUp();
    }

    protected void tearDown() throws Exception
    {
        super.tearDown();
    }

    /**
     * （連続）株価指数先物新規建注文確認リクエスト
     *
     */
    public void test_create_0001()
    {
        final String STR_METHOD_NAME = "test_create_0001()";
        log.entering(STR_METHOD_NAME);
        
        WEB3SuccCommonInfo l_succCommonInfo = new WEB3SuccCommonInfo();
        l_succCommonInfo.parentOrderId = "1001";
        
        WEB3SuccOptionsOpenConfirmRequest l_request = new WEB3SuccOptionsOpenConfirmRequest();
        
        
        l_request.succCommonInfo = l_succCommonInfo;
        try
        {
            IfoOrderUnitParams l_ifoOrderUnitParams = TestDBUtility.getIfoOrderUnitRow(); 
            TestDBUtility.insertWithDel(l_ifoOrderUnitParams);
            WEB3ToSuccOptionOpenContractOrderRequestAdapter l_adapter1 =
                (WEB3ToSuccOptionOpenContractOrderRequestAdapter)l_adapter.create(l_request);
            
            assertEquals("101001010010", "" + ((IfoOrderUnit)l_adapter1.parentOrderUnit).getAccountId());
            
            assertEquals("0.0", "" + l_adapter1.price);
            assertEquals("1001", ((WEB3SuccOptionsOpenConfirmRequest)l_adapter1.request).succCommonInfo.parentOrderId);
        }
        catch (WEB3BaseException e)
        {
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * （連続）株価指数先物新規建注文完了リクエスト
     *
     */
    public void test_create_0002()
    {
        final String STR_METHOD_NAME = "test_create_0002()";
        log.entering(STR_METHOD_NAME);
        
        WEB3SuccCommonInfo l_succCommonInfo = new WEB3SuccCommonInfo();
        l_succCommonInfo.parentOrderId = "1001";
        l_succCommonInfo.succTradingType = "2";
        WEB3SuccOptionsOpenCompleteRequest l_request = new WEB3SuccOptionsOpenCompleteRequest();
        
        
        l_request.succCommonInfo = l_succCommonInfo;
        try
        {
            IfoOrderUnitParams l_ifoOrderUnitParams = TestDBUtility.getIfoOrderUnitRow();
            TestDBUtility.insertWithDel(l_ifoOrderUnitParams);
            WEB3ToSuccOptionOpenContractOrderRequestAdapter l_adapter1 =
                (WEB3ToSuccOptionOpenContractOrderRequestAdapter)l_adapter.create(l_request);
            
            assertEquals("101001010010", "" + ((IfoOrderUnit)l_adapter1.parentOrderUnit).getAccountId());
            
            assertEquals("0.0", "" + l_adapter1.price);
            assertEquals("2", ((WEB3SuccOptionsOpenCompleteRequest)l_adapter1.request).succCommonInfo.succTradingType);
        }
        catch (WEB3BaseException e)
        {
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * WEB3ErrorCatalog.SYSTEM_ERROR_80018
     *
     */
    public void test_create_0003()
    {
        final String STR_METHOD_NAME = "test_create_0003()";
        log.entering(STR_METHOD_NAME);
        
        WEB3SuccCommonInfo l_succCommonInfo = new WEB3SuccCommonInfo();
        l_succCommonInfo.parentOrderId = "1001";
        l_succCommonInfo.succTradingType = "2";
        WEB3SuccOptionsCloseConfirmRequest l_request = new WEB3SuccOptionsCloseConfirmRequest();
        
        
        l_request.succCommonInfo = l_succCommonInfo;
        try
        {
            IfoOrderUnitParams l_ifoOrderUnitParams = TestDBUtility.getIfoOrderUnitRow();
            TestDBUtility.insertWithDel(l_ifoOrderUnitParams);
            l_adapter.create(l_request);
            
            fail();
        }
        catch (WEB3BaseException e)
        {
            log.exiting(STR_METHOD_NAME);
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80018, e.getErrorInfo());
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * get単価
     * （連続）株価指数先物新規建注文確認リクエスト
     * this.単価 == 0の場合
     *  リクエストデータ.単価調整値情報 == nullの場合
     */
    public void test_getPrice_0001()
    {
        final String STR_METHOD_NAME = "test_getPrice_0001()";
        log.entering(STR_METHOD_NAME);
        
        WEB3SuccOptionsOpenConfirmRequest l_request = new WEB3SuccOptionsOpenConfirmRequest();
        l_request.orderPriceDiv = "1";
        l_request.limitPrice = "2.3";
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);

        
        try
        {
            
            IfoOrderUnitParams l_ifoOrderUnitParams = TestDBUtility.getIfoOrderUnitRow();
            l_ifoOrderUnitParams.setProductId(3304148080000L);
            l_ifoOrderUnitParams.setMarketId(3303L);
            TestDBUtility.insertWithDel(l_ifoOrderUnitParams);
            
            ProductParams l_productParams = TestDBUtility.getProductRow();
            l_productParams.setProductId(3304148080000L);
            l_productParams.setProductType(ProductTypeEnum.IFO);
            TestDBUtility.insertWithDel(l_productParams);

            IfoProductParams l_ifoProductParams = TestDBUtility.getIfoProductRow();
            l_ifoProductParams.setProductId(3304148080000L);
            TestDBUtility.insertWithDel(l_ifoProductParams);

            TradedProductParams l_tradedProductParams = TestDBUtility.getTradedProductRow();
            l_tradedProductParams.setTradedProductId(1006160060005L);
            l_tradedProductParams.setProductId(3304148080000L);
            l_tradedProductParams.setMarketId(3303L);
            l_tradedProductParams.setProductType(ProductTypeEnum.IFO);
            TestDBUtility.insertWithDel(l_tradedProductParams);
            
            IfoTradedProductParams l_ifoTradedProductParams = TestDBUtility.getIfoTradedProductRow();
            l_ifoTradedProductParams.setTradedProductId(1006160060005L);
            l_ifoTradedProductParams.setMarketId(3303L);
            l_ifoTradedProductParams.setProductId(3304148080000L);
            l_ifoTradedProductParams.setValidForBizDate(null);
            TestDBUtility.insertWithDel(l_ifoTradedProductParams);
            
            MarketParams l_marketParams = TestDBUtility.getMarketRow();
            l_marketParams.setMarketId(3303L);
            
            TestDBUtility.insertWithDel(l_marketParams);
            
            TradingModule l_tradingModule =
                l_finApp.getTradingModule(ProductTypeEnum.IFO);
            
            //OP注文マネージャ.getOrderUnits(引数の（親注文）注文ID)をコールする。
            WEB3OptionOrderManagerImpl l_orderMgr =
                (WEB3OptionOrderManagerImpl) l_tradingModule.getOrderManager();
            OrderUnit[] l_orderUnits = l_orderMgr.getOrderUnits(1001);

            l_adapter.price = 0.0;
            l_adapter.request = l_request;
            l_adapter.parentOrderUnit = (IfoOrderUnit)l_orderUnits[0];
            double l_dblPrice = l_adapter.getPrice();
            
            assertEquals("2.3","" + l_dblPrice);
            
        }
        catch (WEB3BaseException e)
        {
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        catch(Exception e)
        {
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * get単価
     * （連続）株価指数先物新規建注文確認リクエスト
     * this.単価 == 0の場合
     *  リクエストデータ.単価調整値情報 != nullの場合
     */
    public void test_getPrice_0002()
    {
        final String STR_METHOD_NAME = "test_getPrice_0002()";
        log.entering(STR_METHOD_NAME);
        
        WEB3SuccPriceAdjustmentValueInfo l_info = new WEB3SuccPriceAdjustmentValueInfo();
        l_info.setPriceAdjustmentValue(25);
        
        WEB3SuccOptionsOpenConfirmRequest l_request = new WEB3SuccOptionsOpenConfirmRequest();
        l_request.orderPriceDiv = "1";
        l_request.limitPrice = "2.3";
        l_request.priceAdjustmentValueInfo = l_info;
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);

        
        try
        {
            
            IfoOrderUnitParams l_ifoOrderUnitParams = TestDBUtility.getIfoOrderUnitRow();
            l_ifoOrderUnitParams.setProductId(3304148080000L);
            l_ifoOrderUnitParams.setMarketId(3303L);
            TestDBUtility.insertWithDel(l_ifoOrderUnitParams);
            
            ProductParams l_productParams = TestDBUtility.getProductRow();
            l_productParams.setProductId(3304148080000L);
            l_productParams.setProductType(ProductTypeEnum.IFO);
            TestDBUtility.insertWithDel(l_productParams);

            IfoProductParams l_ifoProductParams = TestDBUtility.getIfoProductRow();
            l_ifoProductParams.setProductId(3304148080000L);
            TestDBUtility.insertWithDel(l_ifoProductParams);

            TradedProductParams l_tradedProductParams = TestDBUtility.getTradedProductRow();
            l_tradedProductParams.setTradedProductId(1006160060005L);
            l_tradedProductParams.setProductId(3304148080000L);
            l_tradedProductParams.setMarketId(3303L);
            l_tradedProductParams.setProductType(ProductTypeEnum.IFO);
            TestDBUtility.insertWithDel(l_tradedProductParams);
            
            IfoTradedProductParams l_ifoTradedProductParams = TestDBUtility.getIfoTradedProductRow();
            l_ifoTradedProductParams.setTradedProductId(1006160060005L);
            l_ifoTradedProductParams.setMarketId(3303L);
            l_ifoTradedProductParams.setProductId(3304148080000L);
            l_ifoTradedProductParams.setValidForBizDate(null);
            TestDBUtility.insertWithDel(l_ifoTradedProductParams);
            
            MarketParams l_marketParams = TestDBUtility.getMarketRow();
            l_marketParams.setMarketId(3303L);
            
            TestDBUtility.insertWithDel(l_marketParams);
            
            TradingModule l_tradingModule =
                l_finApp.getTradingModule(ProductTypeEnum.IFO);
            
            //OP注文マネージャ.getOrderUnits(引数の（親注文）注文ID)をコールする。
            WEB3OptionOrderManagerImpl l_orderMgr =
                (WEB3OptionOrderManagerImpl) l_tradingModule.getOrderManager();
            OrderUnit[] l_orderUnits = l_orderMgr.getOrderUnits(1001);

            l_adapter.price = 0.0;
            l_adapter.request = l_request;
            l_adapter.parentOrderUnit = (IfoOrderUnit)l_orderUnits[0];
            double l_dblPrice = l_adapter.getPrice();
            
            assertEquals("33.0","" + l_dblPrice);
            
        }
        catch (WEB3BaseException e)
        {
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        catch(Exception e)
        {
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * get単価
     * （連続）株価指数先物新規建注文確認リクエスト
     * this.単価 != 0の場合
     */
    public void test_getPrice_0003()
    {
        final String STR_METHOD_NAME = "test_getPrice_0003()";
        log.entering(STR_METHOD_NAME);
        
        WEB3SuccPriceAdjustmentValueInfo l_info = new WEB3SuccPriceAdjustmentValueInfo();
        l_info.setPriceAdjustmentValue(25);
        
        WEB3SuccOptionsOpenConfirmRequest l_request = new WEB3SuccOptionsOpenConfirmRequest();
        l_request.orderPriceDiv = "1";
        l_request.limitPrice = "2.3";
        l_request.priceAdjustmentValueInfo = l_info;
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);

        try
        {
            IfoOrderUnitParams l_ifoOrderUnitParams = TestDBUtility.getIfoOrderUnitRow();
            l_ifoOrderUnitParams.setProductId(3304148080000L);
            l_ifoOrderUnitParams.setMarketId(3303L);
            TestDBUtility.insertWithDel(l_ifoOrderUnitParams);
            
            ProductParams l_productParams = TestDBUtility.getProductRow();
            l_productParams.setProductId(3304148080000L);
            l_productParams.setProductType(ProductTypeEnum.IFO);
            TestDBUtility.insertWithDel(l_productParams);

            IfoProductParams l_ifoProductParams = TestDBUtility.getIfoProductRow();
            l_ifoProductParams.setProductId(3304148080000L);
            TestDBUtility.insertWithDel(l_ifoProductParams);

            TradedProductParams l_tradedProductParams = TestDBUtility.getTradedProductRow();
            l_tradedProductParams.setTradedProductId(1006160060005L);
            l_tradedProductParams.setProductId(3304148080000L);
            l_tradedProductParams.setMarketId(3303L);
            l_tradedProductParams.setProductType(ProductTypeEnum.IFO);
            TestDBUtility.insertWithDel(l_tradedProductParams);
            
            IfoTradedProductParams l_ifoTradedProductParams = TestDBUtility.getIfoTradedProductRow();
            l_ifoTradedProductParams.setTradedProductId(1006160060005L);
            l_ifoTradedProductParams.setMarketId(3303L);
            l_ifoTradedProductParams.setProductId(3304148080000L);
            l_ifoTradedProductParams.setValidForBizDate(null);
            TestDBUtility.insertWithDel(l_ifoTradedProductParams);
            
            MarketParams l_marketParams = TestDBUtility.getMarketRow();
            l_marketParams.setMarketId(3303L);
            
            TestDBUtility.insertWithDel(l_marketParams);
            
            TradingModule l_tradingModule =
                l_finApp.getTradingModule(ProductTypeEnum.IFO);
            
            //OP注文マネージャ.getOrderUnits(引数の（親注文）注文ID)をコールする。
            WEB3OptionOrderManagerImpl l_orderMgr =
                (WEB3OptionOrderManagerImpl) l_tradingModule.getOrderManager();
            OrderUnit[] l_orderUnits = l_orderMgr.getOrderUnits(1001);

            l_adapter.price = 335.0;
            l_adapter.request = l_request;
            l_adapter.parentOrderUnit = (IfoOrderUnit)l_orderUnits[0];
            double l_dblPrice = l_adapter.getPrice();
            
            assertEquals("335.0","" + l_dblPrice);
            
        }
        catch (WEB3BaseException e)
        {
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        catch(Exception e)
        {
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * get単価
     * （連続）株価指数先物新規建注文完了リクエスト
     * リクエストデータ.単価調整値情報==null（指値／成行）の場合
     * 
     */
    public void test_getPrice_0004()
    {
        final String STR_METHOD_NAME = "test_getPrice_0004()";
        log.entering(STR_METHOD_NAME);
        
        WEB3SuccOptionsOpenCompleteRequest l_request = new WEB3SuccOptionsOpenCompleteRequest();
        l_request.orderPriceDiv = "1";
        l_request.limitPrice = "5.3";
        l_request.priceAdjustmentValueInfo = null;
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);

        try
        {
            IfoOrderUnitParams l_ifoOrderUnitParams = TestDBUtility.getIfoOrderUnitRow();
            l_ifoOrderUnitParams.setProductId(3304148080000L);
            l_ifoOrderUnitParams.setMarketId(3303L);
            TestDBUtility.insertWithDel(l_ifoOrderUnitParams);
            
            ProductParams l_productParams = TestDBUtility.getProductRow();
            l_productParams.setProductId(3304148080000L);
            l_productParams.setProductType(ProductTypeEnum.IFO);
            TestDBUtility.insertWithDel(l_productParams);

            IfoProductParams l_ifoProductParams = TestDBUtility.getIfoProductRow();
            l_ifoProductParams.setProductId(3304148080000L);
            TestDBUtility.insertWithDel(l_ifoProductParams);

            TradedProductParams l_tradedProductParams = TestDBUtility.getTradedProductRow();
            l_tradedProductParams.setTradedProductId(1006160060005L);
            l_tradedProductParams.setProductId(3304148080000L);
            l_tradedProductParams.setMarketId(3303L);
            l_tradedProductParams.setProductType(ProductTypeEnum.IFO);
            TestDBUtility.insertWithDel(l_tradedProductParams);
            
            IfoTradedProductParams l_ifoTradedProductParams = TestDBUtility.getIfoTradedProductRow();
            l_ifoTradedProductParams.setTradedProductId(1006160060005L);
            l_ifoTradedProductParams.setMarketId(3303L);
            l_ifoTradedProductParams.setProductId(3304148080000L);
            l_ifoTradedProductParams.setValidForBizDate(null);
            TestDBUtility.insertWithDel(l_ifoTradedProductParams);
            
            MarketParams l_marketParams = TestDBUtility.getMarketRow();
            l_marketParams.setMarketId(3303L);
            
            TestDBUtility.insertWithDel(l_marketParams);
            
            TradingModule l_tradingModule =
                l_finApp.getTradingModule(ProductTypeEnum.IFO);
            
            //OP注文マネージャ.getOrderUnits(引数の（親注文）注文ID)をコールする。
            WEB3OptionOrderManagerImpl l_orderMgr =
                (WEB3OptionOrderManagerImpl) l_tradingModule.getOrderManager();
            OrderUnit[] l_orderUnits = l_orderMgr.getOrderUnits(1001);

            l_adapter.price = 335.0;
            l_adapter.request = l_request;
            l_adapter.parentOrderUnit = (IfoOrderUnit)l_orderUnits[0];
            double l_dblPrice = l_adapter.getPrice();
            
            assertEquals("5.3","" + l_dblPrice);
            
        }
        catch (WEB3BaseException e)
        {
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        catch(Exception e)
        {
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * get単価
     * （連続）株価指数先物新規建注文完了リクエスト
     * リクエストデータ.単価調整値情報≠null（±指値指定）の場合
     * 調整後単価がnullの場合、「執行単価がnull」の例外をthrowする
     */
    public void test_getPrice_0005()
    {
        final String STR_METHOD_NAME = "test_getPrice_0005()";
        log.entering(STR_METHOD_NAME);
        
        WEB3SuccPriceAdjustmentValueInfo l_info = new WEB3SuccPriceAdjustmentValueInfo();
        l_info.setPriceAdjustmentValue(25);
        
        WEB3SuccOptionsOpenCompleteRequest l_request = new WEB3SuccOptionsOpenCompleteRequest();
        l_request.orderPriceDiv = "1";
        l_request.limitPrice = "5.3";
        l_request.priceAdjustmentValueInfo = l_info;
        l_request.afterAdjustmentPrice = null;
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);

        try
        {
            IfoOrderUnitParams l_ifoOrderUnitParams = TestDBUtility.getIfoOrderUnitRow();
            l_ifoOrderUnitParams.setProductId(3304148080000L);
            l_ifoOrderUnitParams.setMarketId(3303L);
            TestDBUtility.insertWithDel(l_ifoOrderUnitParams);
            
            ProductParams l_productParams = TestDBUtility.getProductRow();
            l_productParams.setProductId(3304148080000L);
            l_productParams.setProductType(ProductTypeEnum.IFO);
            TestDBUtility.insertWithDel(l_productParams);

            IfoProductParams l_ifoProductParams = TestDBUtility.getIfoProductRow();
            l_ifoProductParams.setProductId(3304148080000L);
            TestDBUtility.insertWithDel(l_ifoProductParams);

            TradedProductParams l_tradedProductParams = TestDBUtility.getTradedProductRow();
            l_tradedProductParams.setTradedProductId(1006160060005L);
            l_tradedProductParams.setProductId(3304148080000L);
            l_tradedProductParams.setMarketId(3303L);
            l_tradedProductParams.setProductType(ProductTypeEnum.IFO);
            TestDBUtility.insertWithDel(l_tradedProductParams);
            
            IfoTradedProductParams l_ifoTradedProductParams = TestDBUtility.getIfoTradedProductRow();
            l_ifoTradedProductParams.setTradedProductId(1006160060005L);
            l_ifoTradedProductParams.setMarketId(3303L);
            l_ifoTradedProductParams.setProductId(3304148080000L);
            l_ifoTradedProductParams.setValidForBizDate(null);
            TestDBUtility.insertWithDel(l_ifoTradedProductParams);
            
            MarketParams l_marketParams = TestDBUtility.getMarketRow();
            l_marketParams.setMarketId(3303L);
            
            TestDBUtility.insertWithDel(l_marketParams);
            
            TradingModule l_tradingModule =
                l_finApp.getTradingModule(ProductTypeEnum.IFO);
            
            //OP注文マネージャ.getOrderUnits(引数の（親注文）注文ID)をコールする。
            WEB3OptionOrderManagerImpl l_orderMgr =
                (WEB3OptionOrderManagerImpl) l_tradingModule.getOrderManager();
            OrderUnit[] l_orderUnits = l_orderMgr.getOrderUnits(1001);

            l_adapter.price = 335.0;
            l_adapter.request = l_request;
            l_adapter.parentOrderUnit = (IfoOrderUnit)l_orderUnits[0];
            double l_dblPrice = l_adapter.getPrice();
            
            fail();
        }
        catch (WEB3BaseException e)
        {
            log.exiting(STR_METHOD_NAME);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02707, e.getErrorInfo());
        }
        catch(Exception e)
        {
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * get単価
     * （連続）株価指数先物新規建注文完了リクエスト
     * リクエストデータ.単価調整値情報≠null（±指値指定）の場合
     * 調整後単価が0以下の場合、「執行単価が0以下」の例外をthrowする。
     */
    public void test_getPrice_0006()
    {
        final String STR_METHOD_NAME = "test_getPrice_0006()";
        log.entering(STR_METHOD_NAME);
        
        WEB3SuccPriceAdjustmentValueInfo l_info = new WEB3SuccPriceAdjustmentValueInfo();
        l_info.setPriceAdjustmentValue(25);
        
        WEB3SuccOptionsOpenCompleteRequest l_request = new WEB3SuccOptionsOpenCompleteRequest();
        l_request.orderPriceDiv = "1";
        l_request.limitPrice = "5.3";
        l_request.priceAdjustmentValueInfo = l_info;
        l_request.afterAdjustmentPrice = "-8";
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);

        try
        {
            IfoOrderUnitParams l_ifoOrderUnitParams = TestDBUtility.getIfoOrderUnitRow();
            l_ifoOrderUnitParams.setProductId(3304148080000L);
            l_ifoOrderUnitParams.setMarketId(3303L);
            TestDBUtility.insertWithDel(l_ifoOrderUnitParams);
            
            ProductParams l_productParams = TestDBUtility.getProductRow();
            l_productParams.setProductId(3304148080000L);
            l_productParams.setProductType(ProductTypeEnum.IFO);
            TestDBUtility.insertWithDel(l_productParams);

            IfoProductParams l_ifoProductParams = TestDBUtility.getIfoProductRow();
            l_ifoProductParams.setProductId(3304148080000L);
            TestDBUtility.insertWithDel(l_ifoProductParams);

            TradedProductParams l_tradedProductParams = TestDBUtility.getTradedProductRow();
            l_tradedProductParams.setTradedProductId(1006160060005L);
            l_tradedProductParams.setProductId(3304148080000L);
            l_tradedProductParams.setMarketId(3303L);
            l_tradedProductParams.setProductType(ProductTypeEnum.IFO);
            TestDBUtility.insertWithDel(l_tradedProductParams);
            
            IfoTradedProductParams l_ifoTradedProductParams = TestDBUtility.getIfoTradedProductRow();
            l_ifoTradedProductParams.setTradedProductId(1006160060005L);
            l_ifoTradedProductParams.setMarketId(3303L);
            l_ifoTradedProductParams.setProductId(3304148080000L);
            l_ifoTradedProductParams.setValidForBizDate(null);
            TestDBUtility.insertWithDel(l_ifoTradedProductParams);
            
            MarketParams l_marketParams = TestDBUtility.getMarketRow();
            l_marketParams.setMarketId(3303L);
            
            TestDBUtility.insertWithDel(l_marketParams);
            
            TradingModule l_tradingModule =
                l_finApp.getTradingModule(ProductTypeEnum.IFO);
            
            //OP注文マネージャ.getOrderUnits(引数の（親注文）注文ID)をコールする。
            WEB3OptionOrderManagerImpl l_orderMgr =
                (WEB3OptionOrderManagerImpl) l_tradingModule.getOrderManager();
            OrderUnit[] l_orderUnits = l_orderMgr.getOrderUnits(1001);

            l_adapter.price = 335.0;
            l_adapter.request = l_request;
            l_adapter.parentOrderUnit = (IfoOrderUnit)l_orderUnits[0];
            double l_dblPrice = l_adapter.getPrice();
            
            fail();
        }
        catch (WEB3BaseException e)
        {
            log.exiting(STR_METHOD_NAME);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02298, e.getErrorInfo());
        }
        catch(Exception e)
        {
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * get単価
     * WEB3ErrorCatalog.SYSTEM_ERROR_80018
     */
    public void test_getPrice_0007()
    {
        final String STR_METHOD_NAME = "test_getPrice_0007()";
        log.entering(STR_METHOD_NAME);
        
        WEB3SuccEquityBuyInputRequest l_request = new WEB3SuccEquityBuyInputRequest();

        try
        {
            l_adapter.price = 335.0;
            l_adapter.request = l_request;
            l_adapter.parentOrderUnit = null;
            double l_dblPrice = l_adapter.getPrice();
            
            fail();
        }
        catch (WEB3BaseException e)
        {
            log.exiting(STR_METHOD_NAME);
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80018, e.getErrorInfo());
        }
        catch(Exception e)
        {
            log.exiting(STR_METHOD_NAME);
            System.out.print(e);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
}
@
