head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.00.42.02;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3ToSuccEqTypeOrderUnitImplTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 株式予約注文単位Implテスト(WEB3ToSuccEqTypeOrderUnitImplTest.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/03/06 趙林鵬 (中訊) 新規作成
*/

package webbroker3.triggerorder;

import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderCategEnum;

import test.util.TestDBUtility;

import webbroker3.mock.TestBaseForMock;
import webbroker3.triggerorder.base.data.RsvEqOrderUnitParams;
import webbroker3.triggerorder.base.data.RsvEqOrderUnitRow;
import webbroker3.triggerorder.util.WEB3TriggerOrderTradingModel;
import webbroker3.util.WEB3LogUtility;

/**
 * （株式予約注文単位Implテスト）<BR>
 *
 * @@author 趙林鵬
 * @@version 1.0
 */

public class WEB3ToSuccEqTypeOrderUnitImplTest extends TestBaseForMock
{
    /**
     * ログユーティリティ<BR>
     */
    private static WEB3LogUtility log =
         WEB3LogUtility.getInstance(WEB3ToSuccEqTypeOrderUnitImplTest.class);

    public WEB3ToSuccEqTypeOrderUnitImplTest(String arg0)
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
    
    public void test_getMsgOrderPriceDiv_case0001()
    {
        final String STR_METHOD_NAME = " test_getMsgOrderPriceDiv_case0001";
        log.entering(TEST_START + STR_METHOD_NAME);
    
        WEB3ToSuccOrderManagerImpl l_toOrderManager = 
            (WEB3ToSuccOrderManagerImpl)WEB3TriggerOrderTradingModel.getOrderManager();
        WEB3ToSuccEqTypeOrderUnitImpl l_succEqTypeOrderUnitImpl = null;
        long l_lngOrderId = 1001L;

        try
        {
            TestDBUtility.deleteAll(RsvEqOrderUnitRow.TYPE);
            RsvEqOrderUnitParams l_rsvEqOrderUnitParams = TestDBUtility.getRsvEqOrderUnitRow();
            l_rsvEqOrderUnitParams.setOrderId(1001L);
            l_rsvEqOrderUnitParams.setPriceAdjustValue(100);
            
            TestDBUtility.insertWithDel(l_rsvEqOrderUnitParams);
            
            
            l_succEqTypeOrderUnitImpl = l_toOrderManager.getReserveEqtypeOrderUnit(l_lngOrderId);
            
            String l_strmsgOrderPriceDiv = l_succEqTypeOrderUnitImpl.getMsgOrderPriceDiv();
            
            assertEquals("0", l_strmsgOrderPriceDiv);

        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
        log.exiting(TEST_END + STR_METHOD_NAME);  
    }
    
    public void test_getMsgOrderPriceDiv_case0002()
    {
        final String STR_METHOD_NAME = " test_getMsgOrderPriceDiv_case0002";
        log.entering(TEST_START + STR_METHOD_NAME);
    
        WEB3ToSuccOrderManagerImpl l_toOrderManager = 
            (WEB3ToSuccOrderManagerImpl)WEB3TriggerOrderTradingModel.getOrderManager();
        WEB3ToSuccEqTypeOrderUnitImpl l_succEqTypeOrderUnitImpl = null;
        long l_lngOrderId = 1001L;

        try
        {
            TestDBUtility.deleteAll(RsvEqOrderUnitRow.TYPE);
            RsvEqOrderUnitParams l_rsvEqOrderUnitParams = TestDBUtility.getRsvEqOrderUnitRow();
            l_rsvEqOrderUnitParams.setOrderId(1001L);
            l_rsvEqOrderUnitParams.setPriceAdjustValue(null);
            l_rsvEqOrderUnitParams.setOrderCateg(OrderCategEnum.SWAP_MARGIN);
            
            TestDBUtility.insertWithDel(l_rsvEqOrderUnitParams);
            
            
            l_succEqTypeOrderUnitImpl = l_toOrderManager.getReserveEqtypeOrderUnit(l_lngOrderId);
            
            String l_strmsgOrderPriceDiv = l_succEqTypeOrderUnitImpl.getMsgOrderPriceDiv();
            
            assertEquals("1", l_strmsgOrderPriceDiv);

        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
        log.exiting(TEST_END + STR_METHOD_NAME);  
    }
    
    public void test_getMsgOrderPriceDiv_case0003()
    {
        final String STR_METHOD_NAME = " test_getMsgOrderPriceDiv_case0003";
        log.entering(TEST_START + STR_METHOD_NAME);
    
        WEB3ToSuccOrderManagerImpl l_toOrderManager = 
            (WEB3ToSuccOrderManagerImpl)WEB3TriggerOrderTradingModel.getOrderManager();
        WEB3ToSuccEqTypeOrderUnitImpl l_succEqTypeOrderUnitImpl = null;
        long l_lngOrderId = 1001L;

        try
        {
            TestDBUtility.deleteAll(RsvEqOrderUnitRow.TYPE);
            RsvEqOrderUnitParams l_rsvEqOrderUnitParams = TestDBUtility.getRsvEqOrderUnitRow();
            l_rsvEqOrderUnitParams.setOrderId(1001L);
            l_rsvEqOrderUnitParams.setPriceAdjustValue(null);
            l_rsvEqOrderUnitParams.setOrderCateg(OrderCategEnum.OPEN_MARGIN);
            l_rsvEqOrderUnitParams.setLimitPrice(100);
            
            TestDBUtility.insertWithDel(l_rsvEqOrderUnitParams);
            
            
            l_succEqTypeOrderUnitImpl = l_toOrderManager.getReserveEqtypeOrderUnit(l_lngOrderId);
            
            String l_strmsgOrderPriceDiv = l_succEqTypeOrderUnitImpl.getMsgOrderPriceDiv();
            
            assertEquals("1", l_strmsgOrderPriceDiv);

        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
        log.exiting(TEST_END + STR_METHOD_NAME);  
    }
    
    public void test_getMsgOrderPriceDiv_case0004()
    {
        final String STR_METHOD_NAME = " test_getMsgOrderPriceDiv_case0004";
        log.entering(TEST_START + STR_METHOD_NAME);
    
        WEB3ToSuccOrderManagerImpl l_toOrderManager = 
            (WEB3ToSuccOrderManagerImpl)WEB3TriggerOrderTradingModel.getOrderManager();
        WEB3ToSuccEqTypeOrderUnitImpl l_succEqTypeOrderUnitImpl = null;
        long l_lngOrderId = 1001L;

        try
        {
            TestDBUtility.deleteAll(RsvEqOrderUnitRow.TYPE);
            RsvEqOrderUnitParams l_rsvEqOrderUnitParams = TestDBUtility.getRsvEqOrderUnitRow();
            l_rsvEqOrderUnitParams.setOrderId(1001L);
            l_rsvEqOrderUnitParams.setPriceAdjustValue(null);
            l_rsvEqOrderUnitParams.setOrderCateg(OrderCategEnum.OPEN_MARGIN);
            l_rsvEqOrderUnitParams.setLimitPrice(0);
            TestDBUtility.insertWithDel(l_rsvEqOrderUnitParams);
            
            
            l_succEqTypeOrderUnitImpl = l_toOrderManager.getReserveEqtypeOrderUnit(l_lngOrderId);
            
            String l_strmsgOrderPriceDiv = l_succEqTypeOrderUnitImpl.getMsgOrderPriceDiv();
            
            assertEquals("0", l_strmsgOrderPriceDiv);

        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
        log.exiting(TEST_END + STR_METHOD_NAME);  
    }
    
    public void test_getMsgLimitPrice_case0001()
    {
        final String STR_METHOD_NAME = " test_getMsgLimitPrice_case0001";
        log.entering(TEST_START + STR_METHOD_NAME);
    
        WEB3ToSuccOrderManagerImpl l_toOrderManager = 
            (WEB3ToSuccOrderManagerImpl)WEB3TriggerOrderTradingModel.getOrderManager();
        WEB3ToSuccEqTypeOrderUnitImpl l_succEqTypeOrderUnitImpl = null;
        long l_lngOrderId = 1001L;

        try
        {
            TestDBUtility.deleteAll(RsvEqOrderUnitRow.TYPE);
            RsvEqOrderUnitParams l_rsvEqOrderUnitParams = TestDBUtility.getRsvEqOrderUnitRow();
            l_rsvEqOrderUnitParams.setOrderId(1001L);
            l_rsvEqOrderUnitParams.setPriceAdjustValue(null);
            l_rsvEqOrderUnitParams.setOrderCateg(OrderCategEnum.SWAP_MARGIN);
            l_rsvEqOrderUnitParams.setLimitPrice(200);

            TestDBUtility.insertWithDel(l_rsvEqOrderUnitParams);
            
            
            l_succEqTypeOrderUnitImpl = l_toOrderManager.getReserveEqtypeOrderUnit(l_lngOrderId);
            
            String l_strmsgLimitPrice = l_succEqTypeOrderUnitImpl.getMsgLimitPrice();
            
            assertEquals("200", l_strmsgLimitPrice);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
        log.exiting(TEST_END + STR_METHOD_NAME);  
    }
    
}
@
