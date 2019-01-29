head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.02.34.02;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3ToSuccFuturesSettleContractOrderRequestAdapterTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : （WEB3ToSuccFuturesSettleContractOrderRequestAdapterTest.java）
Author Name      : Daiwa Institute of Research
Revision History : 2008/03/27 トウ鋒鋼 (中訊) 新規作成
*/
package webbroker3.triggerorder.service.delegate;

import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.ProductParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.ProductRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.TradedProductParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.TradedProductRow;
import com.fitechlabs.xtrade.plugin.tc.xbifo.IfoOrderUnit;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoContractParams;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoContractRow;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoOrderUnitParams;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoOrderUnitRow;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoProductParams;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoTradedProductParams;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoTradedProductRow;

import test.util.TestDBUtility;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.ifo.WEB3IfoContractImpl;
import webbroker3.ifo.WEB3IfoTradedProductImpl;
import webbroker3.ifo.message.WEB3FuturesOptionsCloseMarginContractUnit;
import webbroker3.mock.TestBaseForMock;
import webbroker3.mock.util.WEB3MockObjectParamsValue;
import webbroker3.triggerorder.message.WEB3SuccCommonInfo;
import webbroker3.triggerorder.message.WEB3SuccFuturesCloseCompleteRequest;
import webbroker3.triggerorder.message.WEB3SuccFuturesCloseConfirmRequest;
import webbroker3.triggerorder.message.WEB3SuccPriceAdjustmentValueInfo;
import webbroker3.triggerorder.util.WEB3ToSuccOrderManagerUtility;
import webbroker3.util.WEB3LogUtility;

public class WEB3ToSuccFuturesSettleContractOrderRequestAdapterTest extends TestBaseForMock
{
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3ToSuccFuturesSettleContractOrderRequestAdapterTest.class);
    
    private WEB3ToSuccFuturesSettleContractOrderRequestAdapter l_requestAdapter = null;

    public WEB3ToSuccFuturesSettleContractOrderRequestAdapterTest(String arg0)
    {
        super(arg0);
    }

    protected void setUp() throws Exception
    {
        super.setUp();
        TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);
        WEB3ToSuccOrderManagerUtility.changeOrderManager();
    }

    protected void tearDown() throws Exception
    {
        super.tearDown();
    }

    public void testCreate_0001()
    {
        final String STR_METHOD_NAME = "testCreate_0001()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        try
        {
            TestDBUtility.deleteAll(IfoOrderUnitRow.TYPE);
            IfoOrderUnitParams l_ifoOrderUnitParams = TestDBUtility.getIfoOrderUnitRow();
            l_ifoOrderUnitParams.setAccountId(333812512246L);
            l_ifoOrderUnitParams.setConfirmedPrice(22.3);
            TestDBUtility.insertWithDel(l_ifoOrderUnitParams);
            
            WEB3SuccFuturesCloseConfirmRequest l_request = new WEB3SuccFuturesCloseConfirmRequest();
            l_request.succCommonInfo = new WEB3SuccCommonInfo();
            l_request.succCommonInfo.parentOrderId = "1001";
            l_request.closingOrder = "0";
            
            WEB3ToSuccFuturesSettleContractOrderRequestAdapter l_requestAdapter =
                (WEB3ToSuccFuturesSettleContractOrderRequestAdapter)
                    WEB3ToSuccFuturesSettleContractOrderRequestAdapter.create(l_request);
            
            assertEquals("0", ((WEB3SuccFuturesCloseConfirmRequest)l_requestAdapter.request).closingOrder);
            assertEquals("22.3", "" + l_requestAdapter.parentOrderUnit.getConfirmedPrice());
            assertEquals("0.0", "" + l_requestAdapter.price);
        }
        catch (Exception l_ex)
        {
            log.error("", l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    public void testCreate_0002()
    {
        final String STR_METHOD_NAME = "testCreate_0002()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        try
        {
            TestDBUtility.deleteAll(IfoOrderUnitRow.TYPE);
            IfoOrderUnitParams l_ifoOrderUnitParams = TestDBUtility.getIfoOrderUnitRow();
            l_ifoOrderUnitParams.setAccountId(333812512246L);
            l_ifoOrderUnitParams.setConfirmedPrice(22.3);
            TestDBUtility.insertWithDel(l_ifoOrderUnitParams);
            
            WEB3SuccFuturesCloseCompleteRequest l_request = new WEB3SuccFuturesCloseCompleteRequest();
            l_request.succCommonInfo = new WEB3SuccCommonInfo();
            l_request.succCommonInfo.parentOrderId = "1001";
            l_request.closingOrder = "0";
            
            WEB3ToSuccFuturesSettleContractOrderRequestAdapter l_requestAdapter =
                (WEB3ToSuccFuturesSettleContractOrderRequestAdapter)
                    WEB3ToSuccFuturesSettleContractOrderRequestAdapter.create(l_request);
            
            assertEquals("0", ((WEB3SuccFuturesCloseCompleteRequest)l_requestAdapter.request).closingOrder);
            assertEquals("22.3", "" + l_requestAdapter.parentOrderUnit.getConfirmedPrice());
            assertEquals("0.0", "" + l_requestAdapter.price);
        }
        catch (Exception l_ex)
        {
            log.error("", l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    public void testGetContract_0001()
    {
        final String STR_METHOD_NAME = "testGetContract_0001()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        try
        {
            this.initData("confirm");

            TestDBUtility.deleteAll(IfoContractRow.TYPE);
            IfoContractParams l_ifoContractParams = TestDBUtility.getIfoContractRow();
            l_ifoContractParams.setContractPrice(20.2);
            TestDBUtility.insertWithDel(l_ifoContractParams);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.triggerorder.WEB3ToSuccOrderManagerImpl",
                    "createIfoContract", new Class[]
                    {IfoOrderUnit.class},
                    new WEB3IfoContractImpl(l_ifoContractParams));
            
            WEB3SuccFuturesCloseConfirmRequest l_request =
                (WEB3SuccFuturesCloseConfirmRequest)l_requestAdapter.request;
            l_request.succCommonInfo.succTradingType = "13";
            
            WEB3IfoContractImpl l_ifoContract = l_requestAdapter.getContract();
            
            assertEquals("20.2", "" + l_ifoContract.getContractPrice());
            
            //參數驗證
            WEB3MockObjectParamsValue l_paramsValue = TestBaseForMock.MOCK_MANAGER.getMethodParamsValue(
                "webbroker3.triggerorder.WEB3ToSuccOrderManagerImpl",
                "createIfoContract",
                new Class[]{IfoOrderUnit.class});
            
            Object[] l_objParamsValues = l_paramsValue.getFirstCalled();
            
            assertEquals("22.3", "" + ((IfoOrderUnit)l_objParamsValues[0]).getConfirmedPrice());
        }
        catch (Exception l_ex)
        {
            log.error("", l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    public void testGetContract_0002()
    {
        final String STR_METHOD_NAME = "testGetContract_0002()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        try
        {
            this.initData("confirm");

            TestDBUtility.deleteAll(IfoContractRow.TYPE);
            IfoContractParams l_ifoContractParams = TestDBUtility.getIfoContractRow();
            l_ifoContractParams.setContractPrice(20.2);
            TestDBUtility.insertWithDel(l_ifoContractParams);
            
            WEB3SuccFuturesCloseConfirmRequest l_request =
                (WEB3SuccFuturesCloseConfirmRequest)l_requestAdapter.request;
            
            l_request.succCommonInfo.succTradingType = "12";
            WEB3FuturesOptionsCloseMarginContractUnit[] l_closeMarginContractUnits =
                new WEB3FuturesOptionsCloseMarginContractUnit[]{new WEB3FuturesOptionsCloseMarginContractUnit()};
            
            l_closeMarginContractUnits[0].id = "1001";
            l_request.closeMarginContractUnits = l_closeMarginContractUnits;
            WEB3IfoContractImpl l_ifoContract = l_requestAdapter.getContract();
            
            assertEquals("20.2", "" + l_ifoContract.getContractPrice());
        }
        catch (Exception l_ex)
        {
            log.error("", l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    public void testIsReversingOrder_0001()
    {
        final String STR_METHOD_NAME = "testIsReversingOrder_0001()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        try
        {
            this.initData("confirm");
            WEB3SuccFuturesCloseConfirmRequest l_request =
                (WEB3SuccFuturesCloseConfirmRequest)l_requestAdapter.request;
            
            l_request.succCommonInfo.succTradingType = "13";
            assertTrue(l_requestAdapter.isReversingOrder());
        }
        catch (Exception l_ex)
        {
            log.error("", l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    public void testIsReversingOrder_0002()
    {
        final String STR_METHOD_NAME = "testIsReversingOrder_0002()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        try
        {
            this.initData("confirm");
            WEB3SuccFuturesCloseConfirmRequest l_request =
                (WEB3SuccFuturesCloseConfirmRequest)l_requestAdapter.request;
            
            l_request.succCommonInfo.succTradingType = "12";
            assertFalse(l_requestAdapter.isReversingOrder());
        }
        catch (Exception l_ex)
        {
            log.error("", l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    public void testIsReversingOrder_0003()
    {
        final String STR_METHOD_NAME = "testIsReversingOrder_0003()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        try
        {
            this.initData("complete");
            WEB3SuccFuturesCloseCompleteRequest l_request =
                (WEB3SuccFuturesCloseCompleteRequest)l_requestAdapter.request;
            
            l_request.succCommonInfo.succTradingType = "13";
            assertTrue(l_requestAdapter.isReversingOrder());
        }
        catch (Exception l_ex)
        {
            log.error("", l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    public void testIsReversingOrder_0004()
    {
        final String STR_METHOD_NAME = "testIsReversingOrder_0004()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        try
        {
            this.initData("complete");
            WEB3SuccFuturesCloseCompleteRequest l_request =
                (WEB3SuccFuturesCloseCompleteRequest)l_requestAdapter.request;
            
            l_request.succCommonInfo.succTradingType = "12";
            assertFalse(l_requestAdapter.isReversingOrder());
        }
        catch (Exception l_ex)
        {
            log.error("", l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    public void testGetPrice_0001()
    {
        final String STR_METHOD_NAME = "testGetPrice_0001()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        try
        {
            this.initData("confirm");
            
            TestDBUtility.deleteAll(ProductRow.TYPE);
            ProductParams l_productParams = TestDBUtility.getProductRow();
            l_productParams.setProductId(1006169090018L);
            l_productParams.setProductType(ProductTypeEnum.IFO);
            TestDBUtility.insertWithDel(l_productParams);
          
            TestDBUtility.deleteAll(TradedProductRow.TYPE);
            TradedProductParams l_tradedProductParams = TestDBUtility.getTradedProductRow();
            l_tradedProductParams.setProductId(1006169090018L);
            l_tradedProductParams.setMarketId(1002);
            l_tradedProductParams.setProductType(ProductTypeEnum.IFO);
            TestDBUtility.insertWithDel(l_tradedProductParams);
          
            TestDBUtility.deleteAll(IfoTradedProductRow.TYPE);
            IfoTradedProductParams l_ifoTradedProductParams = TestDBUtility.getIfoTradedProductRow();
            l_ifoTradedProductParams.setValidForBizDate(null);
            l_ifoTradedProductParams.setTradedProductId(1006160060005L);
            l_ifoTradedProductParams.setProductId(1006169090018L);
            l_ifoTradedProductParams.setMarketId(1002);
            TestDBUtility.insertWithDel(l_ifoTradedProductParams);
            
            TestDBUtility.deleteAll(IfoProductParams.TYPE);
            IfoProductParams l_ifoProductParams = TestDBUtility.getIfoProductRow();
            l_ifoProductParams.setProductId(1006169090018L);
            TestDBUtility.insertWithDel(l_ifoProductParams);

            l_requestAdapter.price = 0;
            assertEquals("12.0", "" + l_requestAdapter.getPrice());
        }
        catch (Exception l_ex)
        {
            log.error("", l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    public void testGetPrice_0002()
    {
        final String STR_METHOD_NAME = "testGetPrice_0002()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        try
        {
            this.initData("confirm");
            
            TestDBUtility.deleteAll(ProductRow.TYPE);
            ProductParams l_productParams = TestDBUtility.getProductRow();
            l_productParams.setProductId(1006169090018L);
            l_productParams.setProductType(ProductTypeEnum.IFO);
            TestDBUtility.insertWithDel(l_productParams);
          
            TestDBUtility.deleteAll(TradedProductRow.TYPE);
            TradedProductParams l_tradedProductParams = TestDBUtility.getTradedProductRow();
            l_tradedProductParams.setProductId(1006169090018L);
            l_tradedProductParams.setMarketId(1002);
            l_tradedProductParams.setProductType(ProductTypeEnum.IFO);
            TestDBUtility.insertWithDel(l_tradedProductParams);
          
            TestDBUtility.deleteAll(IfoTradedProductRow.TYPE);
            IfoTradedProductParams l_ifoTradedProductParams = TestDBUtility.getIfoTradedProductRow();
            l_ifoTradedProductParams.setValidForBizDate(null);
            l_ifoTradedProductParams.setTradedProductId(1006160060005L);
            l_ifoTradedProductParams.setProductId(1006169090018L);
            l_ifoTradedProductParams.setMarketId(1002);
            TestDBUtility.insertWithDel(l_ifoTradedProductParams);
            
            TestDBUtility.deleteAll(IfoProductParams.TYPE);
            IfoProductParams l_ifoProductParams = TestDBUtility.getIfoProductRow();
            l_ifoProductParams.setProductId(1006169090018L);
            TestDBUtility.insertWithDel(l_ifoProductParams);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.triggerorder.WEB3ToSuccOrderManagerImpl",
                    "getReserveIfoOrderExecPrice", new Class[]
                    {IfoOrderUnit.class, double.class, Double.class, WEB3IfoTradedProductImpl.class},
                    new Double(11));

            l_requestAdapter.price = 0;
            
            WEB3SuccFuturesCloseConfirmRequest l_request =
                (WEB3SuccFuturesCloseConfirmRequest)l_requestAdapter.request;
            l_request.priceAdjustmentValueInfo = new WEB3SuccPriceAdjustmentValueInfo();
            l_request.priceAdjustmentValueInfo.sign = "+";
            l_request.priceAdjustmentValueInfo.priceAdjustmentValue = "3";
            
            assertEquals("11.0", "" + l_requestAdapter.getPrice());
        }
        catch (Exception l_ex)
        {
            log.error("", l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    public void testGetPrice_0003()
    {
        final String STR_METHOD_NAME = "testGetPrice_0003()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        try
        {
            this.initData("confirm");

            l_requestAdapter.price = 15;
            
            assertEquals("15.0", "" + l_requestAdapter.getPrice());
        }
        catch (Exception l_ex)
        {
            log.error("", l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    public void testGetPrice_0004()
    {
        final String STR_METHOD_NAME = "testGetPrice_0004()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        try
        {
            this.initData("complete");
            assertEquals("12.0", "" + l_requestAdapter.getPrice());
        }
        catch (Exception l_ex)
        {
            log.error("", l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    public void testGetPrice_0005()
    {
        final String STR_METHOD_NAME = "testGetPrice_0005()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        try
        {
            this.initData("complete");
            
            WEB3SuccFuturesCloseCompleteRequest l_request =
                (WEB3SuccFuturesCloseCompleteRequest)l_requestAdapter.request;
            l_request.priceAdjustmentValueInfo = new WEB3SuccPriceAdjustmentValueInfo();
            l_request.afterAdjustmentPrice = "15";
            
            assertEquals("15.0", "" + l_requestAdapter.getPrice());
        }
        catch (Exception l_ex)
        {
            log.error("", l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    public void testGetPrice_0006()
    {
        final String STR_METHOD_NAME = "testGetPrice_0006()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        try
        {
            this.initData("complete");
            
            WEB3SuccFuturesCloseCompleteRequest l_request =
                (WEB3SuccFuturesCloseCompleteRequest)l_requestAdapter.request;
            l_request.priceAdjustmentValueInfo = new WEB3SuccPriceAdjustmentValueInfo();
            l_requestAdapter.getPrice();
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02707, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error("", l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    public void testGetPrice_0007()
    {
        final String STR_METHOD_NAME = "testGetPrice_0007()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        try
        {
            this.initData("complete");
            
            WEB3SuccFuturesCloseCompleteRequest l_request =
                (WEB3SuccFuturesCloseCompleteRequest)l_requestAdapter.request;
            l_request.priceAdjustmentValueInfo = new WEB3SuccPriceAdjustmentValueInfo();
            l_request.afterAdjustmentPrice = "-2";
            l_requestAdapter.getPrice();
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02298, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error("", l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    protected void initData(String l_strFlag)
    {
        try
        {
            TestDBUtility.deleteAll(IfoOrderUnitRow.TYPE);
            IfoOrderUnitParams l_ifoOrderUnitParams = TestDBUtility.getIfoOrderUnitRow();
            l_ifoOrderUnitParams.setAccountId(333812512246L);
            l_ifoOrderUnitParams.setConfirmedPrice(22.3);
            TestDBUtility.insertWithDel(l_ifoOrderUnitParams);
            
            if ("confirm".equals(l_strFlag))
            {
                WEB3SuccFuturesCloseConfirmRequest l_request = new WEB3SuccFuturesCloseConfirmRequest();
                l_request.succCommonInfo = new WEB3SuccCommonInfo();
                l_request.succCommonInfo.parentOrderId = "1001";
                l_request.closingOrder = "0";
                l_request.orderPriceDiv = "1";
                l_request.limitPrice = "12.0";
                
                l_requestAdapter =
                    (WEB3ToSuccFuturesSettleContractOrderRequestAdapter)
                        WEB3ToSuccFuturesSettleContractOrderRequestAdapter.create(l_request);
            }
            else if ("complete".equals(l_strFlag))
            {
                WEB3SuccFuturesCloseCompleteRequest l_request = new WEB3SuccFuturesCloseCompleteRequest();
                l_request.succCommonInfo = new WEB3SuccCommonInfo();
                l_request.succCommonInfo.parentOrderId = "1001";
                l_request.closingOrder = "0";
                l_request.orderPriceDiv = "1";
                l_request.limitPrice = "12.0";
                
                l_requestAdapter =
                    (WEB3ToSuccFuturesSettleContractOrderRequestAdapter)
                        WEB3ToSuccFuturesSettleContractOrderRequestAdapter.create(l_request);
            }
        }
        catch (WEB3BaseException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
@
