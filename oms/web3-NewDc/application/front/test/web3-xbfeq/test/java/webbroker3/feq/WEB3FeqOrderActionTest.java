head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.01.54.47;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3FeqOrderActionTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.feq;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderEventTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderExpirationStatusEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderStatusEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule;
import com.fitechlabs.xtrade.plugin.tc.xbfeq.data.FeqOrderActionParams;
import com.fitechlabs.xtrade.plugin.tc.xbfeq.data.FeqOrderActionRow;
import com.fitechlabs.xtrade.plugin.tc.xbfeq.data.FeqOrderUnitParams;
import com.fitechlabs.xtrade.plugin.tc.xbfeq.data.FeqOrderUnitRow;

import test.util.TestDBUtility;

import webbroker3.common.WEB3BaseException;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

public class WEB3FeqOrderActionTest extends TestBaseForMock
{
    /**
     * ログ出力ユーティリティ<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3FeqOrderActionTest.class);

    WEB3FeqOrderAction l_action;

    public WEB3FeqOrderActionTest(String arg0)
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

    public void testGetActionStateDiv_Case0001()
    {
        final String STR_METHOD_NAME = "testGetActionStateDiv_Case0001()";
        log.entering(STR_METHOD_NAME);
        
        
        try
        {
            //FeqOrderActionRow
            TestDBUtility.deleteAll(FeqOrderActionRow.TYPE);
            FeqOrderActionParams l_feqOrderActionParams = this.getFeqOrderActionRow();
            l_feqOrderActionParams.setOrderEventType(OrderEventTypeEnum.EXECUTE);
            l_feqOrderActionParams.setConfirmedQuantity(120);
            l_feqOrderActionParams.setExecutedQuantity(100);
            TestDBUtility.insertWithDel(l_feqOrderActionParams);
            
            //FeqOrderUnitRow
            TestDBUtility.deleteAll(FeqOrderUnitRow.TYPE);
            FeqOrderUnitParams l_feqOrderUnitParams = TestDBUtility.getFeqOrderUnitRow();
            l_feqOrderUnitParams.setTemporaryExecutionFlag("0");
            TestDBUtility.insertWithDel(l_feqOrderUnitParams);
            
            //取得一個WEB3FeqOrderUnit
            FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
            TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.FOREIGN_EQUITY);
            WEB3FeqOrderManager l_orderMgr = 
                (WEB3FeqOrderManager)l_tradingModule.getOrderManager();
            WEB3FeqOrderUnit l_orderUnit = 
                (WEB3FeqOrderUnit)l_orderMgr.getOrderUnit(l_feqOrderUnitParams.getOrderUnitId());
            
            l_action = new WEB3FeqOrderAction(l_feqOrderActionParams); 
            
            String l_actionStateDiv = l_action.getActionStateDiv(l_orderUnit);
            assertEquals("11",l_actionStateDiv);
            
        }
        catch (WEB3BaseException e)
        {
            log.error(e.getMessage(),e);
            fail();
        }
        catch (Exception e)
        {
            log.error(e.getMessage(),e);
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testGetActionStateDiv_Case0002()
    {
        final String STR_METHOD_NAME = "testGetActionStateDiv_Case0002()";
        log.entering(STR_METHOD_NAME);
        
        
        try
        {
            //FeqOrderActionRow
            TestDBUtility.deleteAll(FeqOrderActionRow.TYPE);
            FeqOrderActionParams l_feqOrderActionParams = this.getFeqOrderActionRow();
            l_feqOrderActionParams.setOrderEventType(OrderEventTypeEnum.EXECUTE);
            l_feqOrderActionParams.setConfirmedQuantity(100);
            l_feqOrderActionParams.setExecutedQuantity(100);
            TestDBUtility.insertWithDel(l_feqOrderActionParams);
            
            //FeqOrderUnitRow
            TestDBUtility.deleteAll(FeqOrderUnitRow.TYPE);
            FeqOrderUnitParams l_feqOrderUnitParams = TestDBUtility.getFeqOrderUnitRow();
            l_feqOrderUnitParams.setTemporaryExecutionFlag("0");
            TestDBUtility.insertWithDel(l_feqOrderUnitParams);
            
            //取得一個WEB3FeqOrderUnit
            FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
            TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.FOREIGN_EQUITY);
            WEB3FeqOrderManager l_orderMgr = 
                (WEB3FeqOrderManager)l_tradingModule.getOrderManager();
            WEB3FeqOrderUnit l_orderUnit = 
                (WEB3FeqOrderUnit)l_orderMgr.getOrderUnit(l_feqOrderUnitParams.getOrderUnitId());
            
            l_action = new WEB3FeqOrderAction(l_feqOrderActionParams); 
            
            String l_actionStateDiv = l_action.getActionStateDiv(l_orderUnit);
            assertEquals("12",l_actionStateDiv);
            
        }
        catch (WEB3BaseException e)
        {
            log.error(e.getMessage(),e);
            fail();
        }
        catch (Exception e)
        {
            log.error(e.getMessage(),e);
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testGetActionStateDiv_Case0003()
    {
        final String STR_METHOD_NAME = "testGetActionStateDiv_Case0003()";
        log.entering(STR_METHOD_NAME);
        
        
        try
        {
            //FeqOrderActionRow
            TestDBUtility.deleteAll(FeqOrderActionRow.TYPE);
            FeqOrderActionParams l_feqOrderActionParams = this.getFeqOrderActionRow();
            l_feqOrderActionParams.setOrderEventType(OrderEventTypeEnum.EXECUTE);
            l_feqOrderActionParams.setConfirmedQuantity(120);
            l_feqOrderActionParams.setExecutedQuantity(100);
            TestDBUtility.insertWithDel(l_feqOrderActionParams);
            
            //FeqOrderUnitRow
            TestDBUtility.deleteAll(FeqOrderUnitRow.TYPE);
            FeqOrderUnitParams l_feqOrderUnitParams = TestDBUtility.getFeqOrderUnitRow();
            l_feqOrderUnitParams.setTemporaryExecutionFlag("1");
            TestDBUtility.insertWithDel(l_feqOrderUnitParams);
            
            //取得一個WEB3FeqOrderUnit
            FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
            TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.FOREIGN_EQUITY);
            WEB3FeqOrderManager l_orderMgr = 
                (WEB3FeqOrderManager)l_tradingModule.getOrderManager();
            WEB3FeqOrderUnit l_orderUnit = 
                (WEB3FeqOrderUnit)l_orderMgr.getOrderUnit(l_feqOrderUnitParams.getOrderUnitId());
            
            l_action = new WEB3FeqOrderAction(l_feqOrderActionParams); 
            
            String l_actionStateDiv = l_action.getActionStateDiv(l_orderUnit);
            assertEquals("31",l_actionStateDiv);
            
        }
        catch (WEB3BaseException e)
        {
            log.error(e.getMessage(),e);
            fail();
        }
        catch (Exception e)
        {
            log.error(e.getMessage(),e);
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testGetActionStateDiv_Case0004()
    {
        final String STR_METHOD_NAME = "testGetActionStateDiv_Case0004()";
        log.entering(STR_METHOD_NAME);
        
        
        try
        {
            //FeqOrderActionRow
            TestDBUtility.deleteAll(FeqOrderActionRow.TYPE);
            FeqOrderActionParams l_feqOrderActionParams = this.getFeqOrderActionRow();
            l_feqOrderActionParams.setOrderEventType(OrderEventTypeEnum.EXECUTE);
            l_feqOrderActionParams.setConfirmedQuantity(100);
            l_feqOrderActionParams.setExecutedQuantity(100);
            TestDBUtility.insertWithDel(l_feqOrderActionParams);
            
            //FeqOrderUnitRow
            TestDBUtility.deleteAll(FeqOrderUnitRow.TYPE);
            FeqOrderUnitParams l_feqOrderUnitParams = TestDBUtility.getFeqOrderUnitRow();
            l_feqOrderUnitParams.setTemporaryExecutionFlag("1");
            TestDBUtility.insertWithDel(l_feqOrderUnitParams);
            
            //取得一個WEB3FeqOrderUnit
            FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
            TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.FOREIGN_EQUITY);
            WEB3FeqOrderManager l_orderMgr = 
                (WEB3FeqOrderManager)l_tradingModule.getOrderManager();
            WEB3FeqOrderUnit l_orderUnit = 
                (WEB3FeqOrderUnit)l_orderMgr.getOrderUnit(l_feqOrderUnitParams.getOrderUnitId());
            
            l_action = new WEB3FeqOrderAction(l_feqOrderActionParams); 
            
            String l_actionStateDiv = l_action.getActionStateDiv(l_orderUnit);
            assertEquals("32",l_actionStateDiv);
            
        }
        catch (WEB3BaseException e)
        {
            log.error(e.getMessage(),e);
            fail();
        }
        catch (Exception e)
        {
            log.error(e.getMessage(),e);
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    public FeqOrderActionParams getFeqOrderActionRow()
    {
        FeqOrderActionParams l_feqOrderActionParams = new FeqOrderActionParams();
        l_feqOrderActionParams.setOrderActionId(123456);
        l_feqOrderActionParams.setAccountId(333812512246L);
        l_feqOrderActionParams.setSubAccountId(33381251220301L);
        l_feqOrderActionParams.setOrderEventType(OrderEventTypeEnum.EXECUTE);
        l_feqOrderActionParams.setOrderId(120000);
        l_feqOrderActionParams.setOrderUnitId(1234L);
        l_feqOrderActionParams.setOrderType(OrderTypeEnum.EQUITY_BUY);
        l_feqOrderActionParams.setQuantity(12);
        l_feqOrderActionParams.setOrderStatus(OrderStatusEnum.MODIFIED);
        l_feqOrderActionParams.setExpirationStatus(OrderExpirationStatusEnum.OPEN);
        l_feqOrderActionParams.setOrderActionSerialNo(123);
        l_feqOrderActionParams.setProductId(400300090000000000L);
        l_feqOrderActionParams.setCreatedTimestamp(WEB3DateUtility.getDate("20070228", "yyyyMMdd"));
        l_feqOrderActionParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070228", "yyyyMMdd"));
        
        return l_feqOrderActionParams;
    }
    
    
}
@
