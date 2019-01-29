head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.02.16.42;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3FeqOrderAcceptUnitServiceImplTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.feq.service.delegate.stdimpls;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderOpenStatusEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderStatusEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.BranchParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.BranchRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MarketParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MarketRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.SubAccountParams;
import com.fitechlabs.xtrade.plugin.tc.xbfeq.data.FeqOrderParams;
import com.fitechlabs.xtrade.plugin.tc.xbfeq.data.FeqOrderUnitParams;
import com.fitechlabs.xtrade.plugin.tc.xbfeq.data.FeqOrderUnitRow;

import test.util.TestDBUtility;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.feq.WEB3FeqOrderManager;
import webbroker3.feq.WEB3FeqOrderUnit;
import webbroker3.feq.message.WEB3FeqOrderAcceptCancelUnit;
import webbroker3.gentrade.data.GenCurrencyParams;
import webbroker3.mock.TestBaseForMock;
import webbroker3.slebase.data.SleRcvdQParams;
import webbroker3.util.WEB3LogUtility;

public class WEB3FeqOrderAcceptUnitServiceImplTest extends TestBaseForMock {

    public WEB3FeqOrderAcceptUnitServiceImplTest(String arg0) {
        super(arg0);
    }

    protected void setUp() throws Exception {
        super.setUp();
    }

    protected void tearDown() throws Exception {
        super.tearDown();
    }

    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3FeqOrderAcceptUnitServiceImplTest.class);

    WEB3FeqOrderAcceptUnitServiceImpl l_impl = new WEB3FeqOrderAcceptUnitServiceImpl();
    
    /*
     * Test method for 'webbroker3.feq.service.delegate.stdimpls.WEB3FeqOrderAcceptUnitServiceImpl.notifyOrderAccept(WEB3FeqOrderUnit, WEB3FeqOrderAcceptCancelUnit, SleRcvdQParams)'
     */
    public void testNotifyOrderAccept_T000() 
    {
        final String STR_METHOD_NAME = " testNotifyOrderAccept_T000()";
        log.entering(STR_METHOD_NAME);

        try
        {
            TestDBUtility.deleteAll(FeqOrderUnitRow.TYPE);
            FeqOrderUnitParams l_params = TestDBUtility.getFeqOrderUnitRow();
            l_params.setOrderUnitId(1234L);
            l_params.setExecutionSeqNo(111);
            l_params.setAccountId(124l);
            l_params.setOrderStatus(OrderStatusEnum.ORDERING);
            l_params.setExecEndTimestamp(null);
            l_params.setLimitPrice(200D);
            l_params.setQuantity(100D);
            TestDBUtility.insertWithDel(l_params);
            
            TestDBUtility.deleteAll(BranchRow.TYPE);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setBranchId(l_params.getBranchId());
            TestDBUtility.insertWithDel(l_branchParams);
            

            TestDBUtility.deleteAll(MainAccountParams.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(333812512246L);
            TestDBUtility.insertWithDel(l_mainAccountParams);

            TestDBUtility.deleteAll(SubAccountParams.TYPE);            
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setAccountId(333812512246L);
            l_subAccountParams.setSubAccountId(33381251220301L);
            TestDBUtility.insertWithDel(l_subAccountParams);
            

            TestDBUtility.deleteAll(MarketRow.TYPE);
            MarketParams l_marketParams = TestDBUtility.getMarketRow();
            l_marketParams.setMarketId(3303);
            TestDBUtility.insertWithDel(l_marketParams);
            
            WEB3FeqOrderManager l_feqOrderManager = new WEB3FeqOrderManager();
            WEB3FeqOrderUnit l_orderUnit = (WEB3FeqOrderUnit)l_feqOrderManager.getOrderUnit(1234L);


            TestDBUtility.deleteAll(FeqOrderParams.TYPE);
            FeqOrderParams l_FeqOrderParams = TestDBUtility.getFeqOrderRow();
            TestDBUtility.insertWithDel(l_FeqOrderParams);
            
            SleRcvdQParams l_cvdQPatarams = new SleRcvdQParams();
            l_cvdQPatarams.setRouteDiv("3");
            l_cvdQPatarams.setFxRate(123D);
            l_cvdQPatarams.setExecQty(123D);
            l_cvdQPatarams.setExecPrice(123D);
            l_cvdQPatarams.setRepliesIndex(112L);
            l_cvdQPatarams.setRejectCode("S_001");
            
            //1.9
            l_cvdQPatarams.setAckdCommand(2);
            l_cvdQPatarams.setAcceptDiv("11");
            l_cvdQPatarams.setPrice(200D);
            l_cvdQPatarams.setQuantity(100D);
            
            GenCurrencyParams l_genCurrencyParams = TestDBUtility.getGenCurrencyRow();
            TestDBUtility.deleteAll(GenCurrencyParams.TYPE);
            l_genCurrencyParams.setInstitutionCode("0D");      
            l_genCurrencyParams.setCurrencyCode("01");
            l_genCurrencyParams.setExecRateUpdateTimestamp(GtlUtils.getSystemTimestamp());
            TestDBUtility.insertWithDel(l_genCurrencyParams);

            WEB3FeqOrderAcceptCancelUnit l_cancelUnit = new WEB3FeqOrderAcceptCancelUnit();
            l_cancelUnit.aftChangeAcceptDiv = "01";
            

            l_impl.notifyOrderAccept(l_orderUnit,l_cancelUnit,l_cvdQPatarams);
            

            //assertEquals();

        }
        catch (WEB3BaseException l_ex)
        {
            log.error(l_ex.getErrorMessage(),l_ex);
            fail();
        }
        catch (NotFoundException l_ex)
        {
            log.error(l_ex.getMessage());
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }

    
    public void testNotifyOrderAccept_T001() 
    {
        final String STR_METHOD_NAME = " testNotifyOrderAccept_T001()";
        log.entering(STR_METHOD_NAME);

        try
        {
            TestDBUtility.deleteAll(FeqOrderUnitRow.TYPE);
            FeqOrderUnitParams l_params = TestDBUtility.getFeqOrderUnitRow();
            l_params.setOrderUnitId(1234L);
            l_params.setExecutionSeqNo(111);
            l_params.setAccountId(124l);
            l_params.setOrderStatus(OrderStatusEnum.ORDERING);
            l_params.setExecEndTimestamp(null);
            l_params.setLimitPrice(200D);
            l_params.setQuantity(100D);
            TestDBUtility.insertWithDel(l_params);
            
            TestDBUtility.deleteAll(BranchRow.TYPE);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setBranchId(l_params.getBranchId());
            TestDBUtility.insertWithDel(l_branchParams);
            

            TestDBUtility.deleteAll(MainAccountParams.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(333812512246L);
            TestDBUtility.insertWithDel(l_mainAccountParams);

            TestDBUtility.deleteAll(SubAccountParams.TYPE);            
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setAccountId(333812512246L);
            l_subAccountParams.setSubAccountId(33381251220301L);
            TestDBUtility.insertWithDel(l_subAccountParams);
            

            TestDBUtility.deleteAll(MarketRow.TYPE);
            MarketParams l_marketParams = TestDBUtility.getMarketRow();
            l_marketParams.setMarketId(3303);
            TestDBUtility.insertWithDel(l_marketParams);
            
            WEB3FeqOrderManager l_feqOrderManager = new WEB3FeqOrderManager();
            WEB3FeqOrderUnit l_orderUnit = (WEB3FeqOrderUnit)l_feqOrderManager.getOrderUnit(1234L);


            TestDBUtility.deleteAll(FeqOrderParams.TYPE);
            FeqOrderParams l_FeqOrderParams = TestDBUtility.getFeqOrderRow();
            TestDBUtility.insertWithDel(l_FeqOrderParams);
            
            SleRcvdQParams l_cvdQPatarams = new SleRcvdQParams();
            l_cvdQPatarams.setRouteDiv("3");
            l_cvdQPatarams.setFxRate(123D);
            l_cvdQPatarams.setExecQty(123D);
            l_cvdQPatarams.setExecPrice(123D);
            l_cvdQPatarams.setRepliesIndex(112L);
            l_cvdQPatarams.setRejectCode("S_001");
            //1.9
            l_cvdQPatarams.setAckdCommand(0);
            l_cvdQPatarams.setAcceptDiv("01");
            
            l_cvdQPatarams.setPrice(200D);
            l_cvdQPatarams.setQuantity(100D);

            GenCurrencyParams l_genCurrencyParams = TestDBUtility.getGenCurrencyRow();
            TestDBUtility.deleteAll(GenCurrencyParams.TYPE);
            l_genCurrencyParams.setInstitutionCode("0D");
            //  通貨コード   currency_code   VARCHAR2   3   Notnull   ｢通貨コード｣sheet参照       
            l_genCurrencyParams.setCurrencyCode("01");
            l_genCurrencyParams.setExecRateUpdateTimestamp(GtlUtils.getSystemTimestamp());
            TestDBUtility.insertWithDel(l_genCurrencyParams);
            
            WEB3FeqOrderAcceptCancelUnit l_cancelUnit = new WEB3FeqOrderAcceptCancelUnit();
            l_cancelUnit.aftChangeAcceptDiv = "01";
            

            l_impl.notifyOrderAccept(l_orderUnit,l_cancelUnit,l_cvdQPatarams);
            

            //assertEquals();

        }
        catch (WEB3BaseException l_ex)
        {
            log.error(l_ex.getErrorMessage(),l_ex);
            fail();
        }
        catch (NotFoundException l_ex)
        {
            log.error(l_ex.getMessage());
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }

    public void testNotifyOrderAccept_T002() 
    {
        final String STR_METHOD_NAME = " testNotifyOrderAccept_T002()";
        log.entering(STR_METHOD_NAME);

        try
        {
            TestDBUtility.deleteAll(FeqOrderUnitRow.TYPE);
            FeqOrderUnitParams l_params = TestDBUtility.getFeqOrderUnitRow();
            l_params.setOrderUnitId(1234L);
            l_params.setExecutionSeqNo(111);
            l_params.setAccountId(124l);
            l_params.setOrderStatus(OrderStatusEnum.ORDERING);
            l_params.setExecEndTimestamp(null);
            l_params.setLimitPrice(200D);
            l_params.setQuantity(100D);
            TestDBUtility.insertWithDel(l_params);
            
            TestDBUtility.deleteAll(BranchRow.TYPE);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setBranchId(l_params.getBranchId());
            TestDBUtility.insertWithDel(l_branchParams);
            
            TestDBUtility.deleteAll(MainAccountParams.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(333812512246L);
            TestDBUtility.insertWithDel(l_mainAccountParams);

            TestDBUtility.deleteAll(SubAccountParams.TYPE);            
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setAccountId(333812512246L);
            l_subAccountParams.setSubAccountId(33381251220301L);
            TestDBUtility.insertWithDel(l_subAccountParams);
            
            TestDBUtility.deleteAll(MarketRow.TYPE);
            MarketParams l_marketParams = TestDBUtility.getMarketRow();
            l_marketParams.setMarketId(3303);
            TestDBUtility.insertWithDel(l_marketParams);
            
            WEB3FeqOrderManager l_feqOrderManager = new WEB3FeqOrderManager();
            WEB3FeqOrderUnit l_orderUnit = (WEB3FeqOrderUnit)l_feqOrderManager.getOrderUnit(1234L);


            TestDBUtility.deleteAll(FeqOrderParams.TYPE);
            FeqOrderParams l_FeqOrderParams = TestDBUtility.getFeqOrderRow();
            TestDBUtility.insertWithDel(l_FeqOrderParams);
            
            
            SleRcvdQParams l_cvdQPatarams = new SleRcvdQParams();
            l_cvdQPatarams.setRouteDiv("3");
            l_cvdQPatarams.setFxRate(123D);
            l_cvdQPatarams.setExecQty(123D);
            l_cvdQPatarams.setExecPrice(123D);
            l_cvdQPatarams.setRepliesIndex(112L);

            l_cvdQPatarams.setRejectCode("M_001");
            l_cvdQPatarams.setAcceptDiv("01");
            
            l_cvdQPatarams.setPrice(200D);
            l_cvdQPatarams.setQuantity(100D);
            
            GenCurrencyParams l_genCurrencyParams = TestDBUtility.getGenCurrencyRow();
            
            TestDBUtility.deleteAll(GenCurrencyParams.TYPE);
            l_genCurrencyParams.setInstitutionCode("0D");
            //  通貨コード   currency_code   VARCHAR2   3   Notnull   ｢通貨コード｣sheet参照       
            l_genCurrencyParams.setCurrencyCode("01");
            l_genCurrencyParams.setExecRateUpdateTimestamp(GtlUtils.getSystemTimestamp());
            
            TestDBUtility.insertWithDel(l_genCurrencyParams);
            
            
            WEB3FeqOrderAcceptCancelUnit l_cancelUnit = new WEB3FeqOrderAcceptCancelUnit();
            l_cancelUnit.aftChangeAcceptDiv = "01";
            

            l_impl.notifyOrderAccept(l_orderUnit,l_cancelUnit,l_cvdQPatarams);
            

            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(l_ex.getErrorMessage(),l_ex);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_01975,l_ex.getErrorInfo());
        }
        catch (NotFoundException l_ex)
        {
            log.error(l_ex.getMessage());
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testNotifyOrderAccept_T003() 
    {
        final String STR_METHOD_NAME = " testNotifyOrderAccept_T003()";
        log.entering(STR_METHOD_NAME);

        try
        {
            TestDBUtility.deleteAll(FeqOrderUnitRow.TYPE);
            FeqOrderUnitParams l_params = TestDBUtility.getFeqOrderUnitRow();
            l_params.setOrderUnitId(1234L);
            l_params.setExecutionSeqNo(111);
            l_params.setAccountId(124l);
            l_params.setOrderStatus(OrderStatusEnum.ACCEPTED);
            l_params.setExecEndTimestamp(null);
            l_params.setLimitPrice(200D);
            l_params.setQuantity(100D);
            
            l_params.setOrderOpenStatus(OrderOpenStatusEnum.CLOSED);
            
            
            TestDBUtility.insertWithDel(l_params);
            
            TestDBUtility.deleteAll(BranchRow.TYPE);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setBranchId(l_params.getBranchId());
            TestDBUtility.insertWithDel(l_branchParams);
            

            
            TestDBUtility.deleteAll(MainAccountParams.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(333812512246L);
            TestDBUtility.insertWithDel(l_mainAccountParams);

            TestDBUtility.deleteAll(SubAccountParams.TYPE);            
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setAccountId(333812512246L);
            l_subAccountParams.setSubAccountId(33381251220301L);
            TestDBUtility.insertWithDel(l_subAccountParams);
            

            
            TestDBUtility.deleteAll(MarketRow.TYPE);
            MarketParams l_marketParams = TestDBUtility.getMarketRow();
            l_marketParams.setMarketId(3303);
            TestDBUtility.insertWithDel(l_marketParams);
            
            WEB3FeqOrderManager l_feqOrderManager = new WEB3FeqOrderManager();
            WEB3FeqOrderUnit l_orderUnit = (WEB3FeqOrderUnit)l_feqOrderManager.getOrderUnit(1234L);

            
            
            TestDBUtility.deleteAll(FeqOrderParams.TYPE);
            FeqOrderParams l_FeqOrderParams = TestDBUtility.getFeqOrderRow();
            TestDBUtility.insertWithDel(l_FeqOrderParams);
            
            
            SleRcvdQParams l_cvdQPatarams = new SleRcvdQParams();
            l_cvdQPatarams.setRouteDiv("1");
            l_cvdQPatarams.setFxRate(123D);
            l_cvdQPatarams.setExecQty(123D);
            l_cvdQPatarams.setExecPrice(123D);
            l_cvdQPatarams.setRepliesIndex(112L);

            l_cvdQPatarams.setRejectCode("M_001");
            l_cvdQPatarams.setAcceptDiv("01");
            
            l_cvdQPatarams.setPrice(200D);
            l_cvdQPatarams.setQuantity(100D);
            
            GenCurrencyParams l_genCurrencyParams = TestDBUtility.getGenCurrencyRow();
            
            TestDBUtility.deleteAll(GenCurrencyParams.TYPE);
            l_genCurrencyParams.setInstitutionCode("0D");
            //  通貨コード   currency_code   VARCHAR2   3   Notnull   ｢通貨コード｣sheet参照       
            l_genCurrencyParams.setCurrencyCode("01");
            l_genCurrencyParams.setExecRateUpdateTimestamp(GtlUtils.getSystemTimestamp());
            
            TestDBUtility.insertWithDel(l_genCurrencyParams);
            
            
            WEB3FeqOrderAcceptCancelUnit l_cancelUnit = new WEB3FeqOrderAcceptCancelUnit();
            l_cancelUnit.aftChangeAcceptDiv = "01";
            

            l_impl.notifyOrderAccept(l_orderUnit,l_cancelUnit,l_cvdQPatarams);
            

            //assertEquals();

        }
        catch (WEB3BaseException l_ex)
        {
            log.error(l_ex.getErrorMessage(),l_ex);
            fail();
        }
        catch (NotFoundException l_ex)
        {
            log.error(l_ex.getMessage());
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    
    /*
     * Test method for 'webbroker3.feq.service.delegate.stdimpls.WEB3FeqOrderAcceptUnitServiceImpl.validateAcceptTelegram(SleRcvdQParams, WEB3FeqOrderUnit)'
     */
    public void testValidateAcceptTelegram_T001()
    {
        final String STR_METHOD_NAME = " testValidateAcceptTelegram_T001()";
        log.entering(STR_METHOD_NAME);
        
        
        
        try
        {
            TestDBUtility.deleteAll(FeqOrderUnitRow.TYPE);
            FeqOrderUnitParams l_params = TestDBUtility.getFeqOrderUnitRow();
            l_params.setOrderUnitId(1234L);
            l_params.setLimitPrice(200D);
            l_params.setQuantity(100D);
            TestDBUtility.insertWithDel(l_params);
            
            
            
            WEB3FeqOrderManager l_feqOrderManager = new WEB3FeqOrderManager();
            WEB3FeqOrderUnit l_orderUnit = (WEB3FeqOrderUnit)l_feqOrderManager.getOrderUnit(1234L);


            TestDBUtility.deleteAll(FeqOrderParams.TYPE);
            FeqOrderParams l_FeqOrderParams = TestDBUtility.getFeqOrderRow();
            TestDBUtility.insertWithDel(l_FeqOrderParams);
            
            
            SleRcvdQParams l_cvdQPatarams = new SleRcvdQParams();
            l_cvdQPatarams.setPrice(200D);
            l_cvdQPatarams.setQuantity(100D);
            

            WEB3FeqOrderAcceptCancelUnit l_cancelUnit = new WEB3FeqOrderAcceptCancelUnit();
            l_cancelUnit.aftChangeAcceptDiv = "01";
            
            
           // l_impl.validateAcceptTelegram(l_cvdQPatarams,l_orderUnit);
            Object[] obj = {l_cvdQPatarams, l_orderUnit};
                             
            Method method = WEB3FeqOrderAcceptUnitServiceImpl.class.getDeclaredMethod(
                    "validateAcceptTelegram",
                   new Class[]{SleRcvdQParams.class, WEB3FeqOrderUnit.class});
            method.setAccessible(true);
            method.invoke(l_impl, obj);
            
            
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(l_ex.getErrorMessage(),l_ex);
            
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80006,l_ex.getErrorInfo());
            
            fail();
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage());
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testValidateAcceptTelegram_T002() 
    {
        final String STR_METHOD_NAME = " testValidateAcceptTelegram_T002()";
        log.entering(STR_METHOD_NAME);

        try
        {
            TestDBUtility.deleteAll(FeqOrderUnitRow.TYPE);
            FeqOrderUnitParams l_params = TestDBUtility.getFeqOrderUnitRow();
            l_params.setOrderUnitId(1234L);
            l_params.setLimitPrice(200D);
            l_params.setQuantity(100D);
            TestDBUtility.insertWithDel(l_params);
            
            
            
            WEB3FeqOrderManager l_feqOrderManager = new WEB3FeqOrderManager();
            WEB3FeqOrderUnit l_orderUnit = (WEB3FeqOrderUnit)l_feqOrderManager.getOrderUnit(1234L);


            TestDBUtility.deleteAll(FeqOrderParams.TYPE);
            FeqOrderParams l_FeqOrderParams = TestDBUtility.getFeqOrderRow();
            TestDBUtility.insertWithDel(l_FeqOrderParams);
            
            
            SleRcvdQParams l_cvdQPatarams = new SleRcvdQParams();
            l_cvdQPatarams.setPrice(201D);
            l_cvdQPatarams.setQuantity(100D);
            

            WEB3FeqOrderAcceptCancelUnit l_cancelUnit = new WEB3FeqOrderAcceptCancelUnit();
            l_cancelUnit.aftChangeAcceptDiv = "01";
            
            
            //l_impl.validateAcceptTelegram(l_cvdQPatarams,l_orderUnit);
            Object[] obj = {l_cvdQPatarams, l_orderUnit};
            
            Method method = WEB3FeqOrderAcceptUnitServiceImpl.class.getDeclaredMethod(
                    "validateAcceptTelegram",
                   new Class[]{SleRcvdQParams.class, WEB3FeqOrderUnit.class});
            method.setAccessible(true);
            method.invoke(l_impl, obj);

            //assertEquals();
            fail();
            
            
        }
        catch (InvocationTargetException l_ex)
        {
            
            WEB3SystemLayerException l_sysex = (WEB3SystemLayerException)l_ex.getTargetException();
            log.error(l_sysex.getErrorMessage(),l_ex);
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80006,l_sysex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage());
            fail();

        }
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testValidateAcceptTelegram_T003() 
    {
        final String STR_METHOD_NAME = " testValidateAcceptTelegram_T003()";
        log.entering(STR_METHOD_NAME);

        try
        {
            TestDBUtility.deleteAll(FeqOrderUnitRow.TYPE);
            FeqOrderUnitParams l_params = TestDBUtility.getFeqOrderUnitRow();
            l_params.setOrderUnitId(1234L);
            l_params.setLimitPrice(200D);
            l_params.setQuantity(100D);
            TestDBUtility.insertWithDel(l_params);
            
            
            
            WEB3FeqOrderManager l_feqOrderManager = new WEB3FeqOrderManager();
            WEB3FeqOrderUnit l_orderUnit = (WEB3FeqOrderUnit)l_feqOrderManager.getOrderUnit(1234L);


            TestDBUtility.deleteAll(FeqOrderParams.TYPE);
            FeqOrderParams l_FeqOrderParams = TestDBUtility.getFeqOrderRow();
            TestDBUtility.insertWithDel(l_FeqOrderParams);
            
            
            SleRcvdQParams l_cvdQPatarams = new SleRcvdQParams();
            l_cvdQPatarams.setPrice(200D);
            l_cvdQPatarams.setQuantity(101D);
            

            WEB3FeqOrderAcceptCancelUnit l_cancelUnit = new WEB3FeqOrderAcceptCancelUnit();
            l_cancelUnit.aftChangeAcceptDiv = "01";
            
            
            //l_impl.validateAcceptTelegram(l_cvdQPatarams,l_orderUnit);
            Object[] obj = {l_cvdQPatarams, l_orderUnit};
            
            Method method = WEB3FeqOrderAcceptUnitServiceImpl.class.getDeclaredMethod(
                    "validateAcceptTelegram",
                   new Class[]{SleRcvdQParams.class, WEB3FeqOrderUnit.class});
            method.setAccessible(true);
            method.invoke(l_impl, obj);

            //assertEquals();
            fail();
            
            
        }
        catch (InvocationTargetException l_ex)
        {
            
            WEB3SystemLayerException l_sysex = (WEB3SystemLayerException)l_ex.getTargetException();
            log.error(l_sysex.getErrorMessage(),l_ex);
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80006,l_sysex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage());
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testvalidateOrderStatus_T001() 
    {
        final String STR_METHOD_NAME = "testvalidateOrderStatus_T001";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            
            TestDBUtility.deleteAll(FeqOrderUnitRow.TYPE);
            FeqOrderUnitParams l_params = TestDBUtility.getFeqOrderUnitRow();
            l_params.setOrderStatus(OrderStatusEnum.ORDERING);
            l_params.setOrderUnitId(1234L);
            TestDBUtility.insertWithDel(l_params);

            WEB3FeqOrderManager l_feqOrderManager = new WEB3FeqOrderManager();
            WEB3FeqOrderUnit l_orderUnit = (WEB3FeqOrderUnit)l_feqOrderManager.getOrderUnit(1234L);

            
            l_impl.validateOrderStatus(l_orderUnit,"M_123");
            fail();
            
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(l_ex.getErrorMessage(),l_ex);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_01975,l_ex.getErrorInfo());
        }
        catch (NotFoundException l_ex)
        {
            log.error(l_ex.getMessage());
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
        
    }
    
    public void testvalidateOrderStatus_T002() 
    {
        final String STR_METHOD_NAME = "testvalidateOrderStatus_T002";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            
            TestDBUtility.deleteAll(FeqOrderUnitRow.TYPE);
            FeqOrderUnitParams l_params = TestDBUtility.getFeqOrderUnitRow();
            l_params.setOrderUnitId(1234L);
            TestDBUtility.insertWithDel(l_params);

            WEB3FeqOrderManager l_feqOrderManager = new WEB3FeqOrderManager();
            WEB3FeqOrderUnit l_orderUnit = (WEB3FeqOrderUnit)l_feqOrderManager.getOrderUnit(1234L);

            
            l_impl.validateOrderStatus(l_orderUnit,"S_123");
            
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(l_ex.getErrorMessage(),l_ex);
            fail();
            
        }
        catch (NotFoundException l_ex)
        {
            log.error(l_ex.getMessage());
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
        
    }
    

}
@
