head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.01.12.32;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3EquityDataAdapterTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
 Copyright        : (äî)ëÂòaëçå§ èÿåîÉ\ÉäÉÖÅ[ÉVÉáÉìÉVÉXÉeÉÄëÊìÒïî
 File Name        : (WEB3MarginExecuteReferenceServiceImplTest.java)
 Author Name      : Daiwa Institute of Research
 Revesion History : 2007/06/10  âΩï∂ïq(íÜêu)Å@@êVãKçÏê¨
 */
package webbroker3.equity;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeOrderAction;
import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeOrderUnit;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderActionParams;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderActionRow;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderUnitParams;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderUnitRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderAction;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderEventTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderExpirationStatusEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderUnit;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule;

import test.util.TestDBUtility;

import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3LogUtility;

/**
 * @@author âΩï∂ïq
 * @@version 1.0
 */
public class WEB3EquityDataAdapterTest extends TestBaseForMock
{
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3EquityDataAdapterTest.class);
//    WEB3EquityDataAdapter l_adapter = new WEB3EquityDataAdapter();

    public WEB3EquityDataAdapterTest(String arg0)
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

    public void testGetOrderType_Case0001()
    {
        final String STR_METHOD_NAME = "testGetOrderType_Case0001()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            TestDBUtility.deleteAll(EqtypeOrderUnitRow.TYPE);
            EqtypeOrderUnitParams l_eqtypeOrderUnitParams = TestDBUtility.getEqtypeOrderUnitRow();
            l_eqtypeOrderUnitParams.setForcedExpireType("1");
            TestDBUtility.insertWithDel(l_eqtypeOrderUnitParams);
            
            TestDBUtility.deleteAll(EqtypeOrderActionRow.TYPE);
            EqtypeOrderActionParams l_eqtypeOrderActionParams = TestDBUtility.getEqtypeOrderActionRow();
            l_eqtypeOrderActionParams.setOrderEventType(OrderEventTypeEnum.EXPIRE);
            l_eqtypeOrderActionParams.setExpirationStatus(OrderExpirationStatusEnum.INVALIDATED_BY_MKT);
            l_eqtypeOrderActionParams.setOrderUnitId(3304148080001L);
            l_eqtypeOrderActionParams.setProductType(ProductTypeEnum.EQUITY);
            TestDBUtility.insertWithDel(l_eqtypeOrderActionParams);
            
            
            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
            TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
            WEB3EquityOrderManager l_orderManager =
                (WEB3EquityOrderManager)l_tradingModule.getOrderManager();
            
            OrderUnit l_orderUnit = l_orderManager.getOrderUnit(3304148080001L);
            EqTypeOrderUnit l_eqtypeOrderUnit = (EqTypeOrderUnit)l_orderUnit;

            OrderAction[] l_orderAction =
                l_eqtypeOrderUnit.getOrderActions();
            EqTypeOrderAction l_eqtypeOrderAction = null;
            for (int i = 0; i < l_orderAction.length; i++)
            {
                l_eqtypeOrderAction = (EqTypeOrderAction)l_orderAction[i];
            }
            
            
            String l_strOrderType = WEB3EquityDataAdapter.getOrderType(l_eqtypeOrderAction, l_eqtypeOrderUnit);
            assertEquals("29", l_strOrderType);
        }
        catch (Exception l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testGetOrderType_Case0002()
    {
        final String STR_METHOD_NAME = "testGetOrderType_Case0002()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            TestDBUtility.deleteAll(EqtypeOrderUnitRow.TYPE);
            EqtypeOrderUnitParams l_eqtypeOrderUnitParams = TestDBUtility.getEqtypeOrderUnitRow();
            l_eqtypeOrderUnitParams.setForcedExpireType("1");
            TestDBUtility.insertWithDel(l_eqtypeOrderUnitParams);
            
            TestDBUtility.deleteAll(EqtypeOrderActionRow.TYPE);
            EqtypeOrderActionParams l_eqtypeOrderActionParams = TestDBUtility.getEqtypeOrderActionRow();
            l_eqtypeOrderActionParams.setOrderUnitId(3304148080001L);
            l_eqtypeOrderActionParams.setProductType(ProductTypeEnum.EQUITY);
            TestDBUtility.insertWithDel(l_eqtypeOrderActionParams);
            
            
            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
            TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
            WEB3EquityOrderManager l_orderManager =
                (WEB3EquityOrderManager)l_tradingModule.getOrderManager();
            
            OrderUnit l_orderUnit = l_orderManager.getOrderUnit(3304148080001L);
            EqTypeOrderUnit l_eqtypeOrderUnit = (EqTypeOrderUnit)l_orderUnit;

            OrderAction[] l_orderAction =
                l_eqtypeOrderUnit.getOrderActions();
            EqTypeOrderAction l_eqtypeOrderAction = null;
            for (int i = 0; i < l_orderAction.length; i++)
            {
                l_eqtypeOrderAction = (EqTypeOrderAction)l_orderAction[i];
            }
            
            
            String l_strOrderType = WEB3EquityDataAdapter.getOrderType(l_eqtypeOrderAction, l_eqtypeOrderUnit);
            assertEquals("00", l_strOrderType);
        }
        catch (Exception l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testGetOrderType_Case0003()
    {
        final String STR_METHOD_NAME = "testGetOrderType_Case0003()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            TestDBUtility.deleteAll(EqtypeOrderUnitRow.TYPE);
            EqtypeOrderUnitParams l_eqtypeOrderUnitParams = TestDBUtility.getEqtypeOrderUnitRow();
            TestDBUtility.insertWithDel(l_eqtypeOrderUnitParams);
            
            TestDBUtility.deleteAll(EqtypeOrderActionRow.TYPE);
            EqtypeOrderActionParams l_eqtypeOrderActionParams = TestDBUtility.getEqtypeOrderActionRow();
            l_eqtypeOrderActionParams.setOrderEventType(OrderEventTypeEnum.EXPIRE);
            l_eqtypeOrderActionParams.setOrderUnitId(3304148080001L);
            l_eqtypeOrderActionParams.setProductType(ProductTypeEnum.EQUITY);
            TestDBUtility.insertWithDel(l_eqtypeOrderActionParams);
            
            
            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
            TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
            WEB3EquityOrderManager l_orderManager =
                (WEB3EquityOrderManager)l_tradingModule.getOrderManager();
            
            OrderUnit l_orderUnit = l_orderManager.getOrderUnit(3304148080001L);
            EqTypeOrderUnit l_eqtypeOrderUnit = (EqTypeOrderUnit)l_orderUnit;

            OrderAction[] l_orderAction =
                l_eqtypeOrderUnit.getOrderActions();
            EqTypeOrderAction l_eqtypeOrderAction = null;
            for (int i = 0; i < l_orderAction.length; i++)
            {
                l_eqtypeOrderAction = (EqTypeOrderAction)l_orderAction[i];
            }
            
            
            String l_strOrderType = WEB3EquityDataAdapter.getOrderType(l_eqtypeOrderAction, l_eqtypeOrderUnit);
            assertEquals("00", l_strOrderType);
        }
        catch (Exception l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testGetOrderType_Case0004()
    {
        final String STR_METHOD_NAME = "testGetOrderType_Case0004()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            TestDBUtility.deleteAll(EqtypeOrderUnitRow.TYPE);
            EqtypeOrderUnitParams l_eqtypeOrderUnitParams = TestDBUtility.getEqtypeOrderUnitRow();
            TestDBUtility.insertWithDel(l_eqtypeOrderUnitParams);
            
            TestDBUtility.deleteAll(EqtypeOrderActionRow.TYPE);
            EqtypeOrderActionParams l_eqtypeOrderActionParams = TestDBUtility.getEqtypeOrderActionRow();
            l_eqtypeOrderActionParams.setExpirationStatus(OrderExpirationStatusEnum.INVALIDATED_BY_MKT);
            l_eqtypeOrderActionParams.setOrderUnitId(3304148080001L);
            l_eqtypeOrderActionParams.setProductType(ProductTypeEnum.EQUITY);
            TestDBUtility.insertWithDel(l_eqtypeOrderActionParams);
            
            
            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
            TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
            WEB3EquityOrderManager l_orderManager =
                (WEB3EquityOrderManager)l_tradingModule.getOrderManager();
            
            OrderUnit l_orderUnit = l_orderManager.getOrderUnit(3304148080001L);
            EqTypeOrderUnit l_eqtypeOrderUnit = (EqTypeOrderUnit)l_orderUnit;

            OrderAction[] l_orderAction =
                l_eqtypeOrderUnit.getOrderActions();
            EqTypeOrderAction l_eqtypeOrderAction = null;
            for (int i = 0; i < l_orderAction.length; i++)
            {
                l_eqtypeOrderAction = (EqTypeOrderAction)l_orderAction[i];
            }
            
            
            String l_strOrderType = WEB3EquityDataAdapter.getOrderType(l_eqtypeOrderAction, l_eqtypeOrderUnit);
            assertEquals("00", l_strOrderType);
        }
        catch (Exception l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testGetOrderType_Case0005()
    {
        final String STR_METHOD_NAME = "testGetOrderType_Case0005()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            TestDBUtility.deleteAll(EqtypeOrderUnitRow.TYPE);
            EqtypeOrderUnitParams l_eqtypeOrderUnitParams = TestDBUtility.getEqtypeOrderUnitRow();
            l_eqtypeOrderUnitParams.setForcedExpireType("0");
            TestDBUtility.insertWithDel(l_eqtypeOrderUnitParams);
            
            TestDBUtility.deleteAll(EqtypeOrderActionRow.TYPE);
            EqtypeOrderActionParams l_eqtypeOrderActionParams = TestDBUtility.getEqtypeOrderActionRow();
            l_eqtypeOrderActionParams.setOrderEventType(OrderEventTypeEnum.EXPIRE);
            l_eqtypeOrderActionParams.setExpirationStatus(OrderExpirationStatusEnum.INVALIDATED_BY_MKT);
            l_eqtypeOrderActionParams.setOrderUnitId(3304148080001L);
            l_eqtypeOrderActionParams.setProductType(ProductTypeEnum.EQUITY);
            TestDBUtility.insertWithDel(l_eqtypeOrderActionParams);
            
            
            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
            TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
            WEB3EquityOrderManager l_orderManager =
                (WEB3EquityOrderManager)l_tradingModule.getOrderManager();
            
            OrderUnit l_orderUnit = l_orderManager.getOrderUnit(3304148080001L);
            EqTypeOrderUnit l_eqtypeOrderUnit = (EqTypeOrderUnit)l_orderUnit;

            OrderAction[] l_orderAction =
                l_eqtypeOrderUnit.getOrderActions();
            EqTypeOrderAction l_eqtypeOrderAction = null;
            for (int i = 0; i < l_orderAction.length; i++)
            {
                l_eqtypeOrderAction = (EqTypeOrderAction)l_orderAction[i];
            }
            
            
            String l_strOrderType = WEB3EquityDataAdapter.getOrderType(l_eqtypeOrderAction, l_eqtypeOrderUnit);
            assertEquals("14", l_strOrderType);
        }
        catch (Exception l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    
    public void testGetOrderType_Case0006()
    {
        final String STR_METHOD_NAME = "testGetOrderType_Case0006()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            TestDBUtility.deleteAll(EqtypeOrderUnitRow.TYPE);
            EqtypeOrderUnitParams l_eqtypeOrderUnitParams = TestDBUtility.getEqtypeOrderUnitRow();
            l_eqtypeOrderUnitParams.setForcedExpireType("0");
            TestDBUtility.insertWithDel(l_eqtypeOrderUnitParams);
            
            TestDBUtility.deleteAll(EqtypeOrderActionRow.TYPE);
            EqtypeOrderActionParams l_eqtypeOrderActionParams = TestDBUtility.getEqtypeOrderActionRow();
            l_eqtypeOrderActionParams.setOrderEventType(OrderEventTypeEnum.APPROVED);
            l_eqtypeOrderActionParams.setExpirationStatus(OrderExpirationStatusEnum.INVALIDATED_BY_MKT);
            l_eqtypeOrderActionParams.setOrderUnitId(3304148080001L);
            l_eqtypeOrderActionParams.setProductType(ProductTypeEnum.EQUITY);
            TestDBUtility.insertWithDel(l_eqtypeOrderActionParams);
            
            
            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
            TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
            WEB3EquityOrderManager l_orderManager =
                (WEB3EquityOrderManager)l_tradingModule.getOrderManager();
            
            OrderUnit l_orderUnit = l_orderManager.getOrderUnit(3304148080001L);
            EqTypeOrderUnit l_eqtypeOrderUnit = (EqTypeOrderUnit)l_orderUnit;

            OrderAction[] l_orderAction =
                l_eqtypeOrderUnit.getOrderActions();
            EqTypeOrderAction l_eqtypeOrderAction = null;
            for (int i = 0; i < l_orderAction.length; i++)
            {
                l_eqtypeOrderAction = (EqTypeOrderAction)l_orderAction[i];
            }
            
            
            String l_strOrderType = WEB3EquityDataAdapter.getOrderType(l_eqtypeOrderAction, l_eqtypeOrderUnit);
            assertEquals("30", l_strOrderType);
        }
        catch (Exception l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }
}
@
