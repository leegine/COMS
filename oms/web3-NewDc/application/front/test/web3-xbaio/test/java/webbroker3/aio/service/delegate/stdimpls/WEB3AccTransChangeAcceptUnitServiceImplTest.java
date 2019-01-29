head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.01.37.26;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3AccTransChangeAcceptUnitServiceImplTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.aio.service.delegate.stdimpls;

import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProcessingResult;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccountTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.SubAccountParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.SubAccountRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.market.messages.NewOrderAcceptedMarketResponseMessage;
import com.fitechlabs.xtrade.plugin.tc.xbaio.AioOrderUnit;
import com.fitechlabs.xtrade.plugin.tc.xbaio.data.AioOrderParams;
import com.fitechlabs.xtrade.plugin.tc.xbaio.data.AioOrderUnitDao;
import com.fitechlabs.xtrade.plugin.tc.xbaio.data.AioOrderUnitParams;
import com.fitechlabs.xtrade.plugin.tc.xbaio.data.AioOrderUnitRow;
import com.fitechlabs.xtrade.plugin.tc.xbaio.stdimpls.AioOrderUnitImpl;

import test.util.TestDBUtility;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3AcceptDivDef;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3LogUtility;

public class WEB3AccTransChangeAcceptUnitServiceImplTest extends TestBaseForMock {

    private static WEB3LogUtility log =WEB3LogUtility.getInstance(WEB3AccTransChangeAcceptUnitServiceImplTest.class);
    
    public WEB3AccTransChangeAcceptUnitServiceImplTest(String arg0) {
        super(arg0);
    }

    protected void setUp() throws Exception {
        super.setUp();
        MOCK_MANAGER.setIsMockUsed(true);
    }

    protected void tearDown() throws Exception {
        super.tearDown();
    }

    WEB3AccTransChangeAcceptUnitServiceImpl l_impl = new WEB3AccTransChangeAcceptUnitServiceImpl();
    
    /**
     * 参数l_orderUnit?空
     * Test method for 'webbroker3.aio.service.delegate.stdimpls.WEB3AccTransChangeAcceptUnitServiceImpl.execute(AioOrderUnit, String, String)'
     */
    public void testExecute_T1() 
    {
        final  String STR_METHOD_NAME = "testExecute_T1";
        log.entering(STR_METHOD_NAME);
        
        try 
        {
            AioOrderUnit l_orderUnit = null;
            String l_strErrorCode = "";
            String l_strAcceptDiv = "";

            l_impl.execute(l_orderUnit, l_strErrorCode ,l_strAcceptDiv);

            fail();
            
        }
        catch(WEB3BaseException l_ex)
        {
            log.error(l_ex.getErrorMessage());
            fail();
        }
        catch(WEB3BaseRuntimeException l_ex)
        {
            assertEquals(l_ex.getErrorInfo(),WEB3ErrorCatalog.SYSTEM_ERROR_80017);
        }
        
        log.exiting(STR_METHOD_NAME);
       
    }
    
    /**
     * 受付完了の場合 
     *
     */
    public void testExecute_T2() 
    {
        final  String STR_METHOD_NAME = "testExecute_T2";
        log.entering(STR_METHOD_NAME);
 
        try 
        {
            ProcessingResult l_ProcessingResult = ProcessingResult.newSuccessResultInstance();
           
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "com.fitechlabs.xtrade.plugin.tc.xbaio.stdimpls.AioMarketResponseReceiverCallbackServiceImpl",
            "process",
            new Class[] {NewOrderAcceptedMarketResponseMessage.class},
            l_ProcessingResult);    
            
            //11111111
            TestDBUtility.deleteAll(MainAccountParams.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            TestDBUtility.insertWithDel(l_mainAccountParams);
            //22222222
            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
            l_subAccountParams.setAccountId(l_mainAccountParams.getAccountId());
            TestDBUtility.insertWithDel(l_subAccountParams);
            //3333333333
            TestDBUtility.deleteAll(AioOrderUnitParams.TYPE);
            AioOrderUnitParams l_AioOrderUnitParams = TestDBUtility.getAioOrderUnitRow();
            l_AioOrderUnitParams.setCancelType("2");
            TestDBUtility.insertWithDel(l_AioOrderUnitParams);
            //4444444444
            TestDBUtility.deleteAll(AioOrderParams.TYPE);
            AioOrderParams l_AioOrderParams = TestDBUtility.getAioOrderRow();
            l_AioOrderParams.setOrderId(2000011L);
            l_AioOrderParams.setAccountId(l_mainAccountParams.getAccountId());
            l_AioOrderParams.setSubAccountId(l_subAccountParams.getSubAccountId());
            TestDBUtility.insertWithDel(l_AioOrderParams);
            

            AioOrderUnit l_orderUnit = new AioOrderUnitForTest(l_AioOrderUnitParams);
            String l_strErrorCode = "1234";
            String l_strAcceptDiv = WEB3AcceptDivDef.CASH_TRANS_SERVICE_COMPLETE;

            l_impl.execute(l_orderUnit, l_strErrorCode ,l_strAcceptDiv);

        }
        catch(WEB3BaseException l_ex)
        {
            log.error(l_ex.getErrorMessage());
            fail();
        }
 
        
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * 
     * 受付エラーの場合
     */
    public void testExecute_T3() 
    {
        final  String STR_METHOD_NAME = "testExecute_T3";
        log.entering(STR_METHOD_NAME);
        
        try 
        {
            ProcessingResult l_ProcessingResult = ProcessingResult.newSuccessResultInstance();
           
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "com.fitechlabs.xtrade.plugin.tc.xbaio.stdimpls.AioMarketResponseReceiverCallbackServiceImpl",
            "process",
            new Class[] {NewOrderAcceptedMarketResponseMessage.class},
            l_ProcessingResult);    
            
            //11111111
            TestDBUtility.deleteAll(MainAccountParams.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            TestDBUtility.insertWithDel(l_mainAccountParams);

            //22222222
            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();            
            l_subAccountParams.setAccountId(l_mainAccountParams.getAccountId());
            TestDBUtility.insertWithDel(l_subAccountParams);
 
            
            //3333333333
            TestDBUtility.deleteAll(AioOrderUnitParams.TYPE);
            AioOrderUnitParams l_AioOrderUnitParams = TestDBUtility.getAioOrderUnitRow();
            l_AioOrderUnitParams.setCancelType("2");
            l_AioOrderUnitParams.setAccountId(l_subAccountParams.getAccountId());
            l_AioOrderUnitParams.setSubAccountId(l_subAccountParams.getSubAccountId());
            TestDBUtility.insertWithDel(l_AioOrderUnitParams);
            
            
            
            //4444444444
            TestDBUtility.deleteAll(AioOrderParams.TYPE);
            AioOrderParams l_AioOrderParams = TestDBUtility.getAioOrderRow();
            l_AioOrderParams.setOrderId(2000011L);
            l_AioOrderParams.setAccountId(l_mainAccountParams.getAccountId());
            l_AioOrderParams.setSubAccountId(l_subAccountParams.getSubAccountId());
            TestDBUtility.insertWithDel(l_AioOrderParams);
            

            AioOrderUnit l_orderUnit = new AioOrderUnitForTest(l_AioOrderUnitParams);
            String l_strErrorCode = "1234";
            String l_strAcceptDiv = WEB3AcceptDivDef.ERROR;

            l_impl.execute(l_orderUnit, l_strErrorCode ,l_strAcceptDiv);

        }
        catch(WEB3BaseException l_ex)
        {
            log.error(l_ex.getErrorMessage());
            fail();
        }
 
        
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * 余力再計算、余力の更新を行う。
     *
     */
    public void testExecute_T4() 
    {
        final  String STR_METHOD_NAME = "testExecute_T4";
        log.entering(STR_METHOD_NAME);
        
        try 
        {
            ProcessingResult l_ProcessingResult = ProcessingResult.newSuccessResultInstance();
           
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "com.fitechlabs.xtrade.plugin.tc.xbaio.stdimpls.AioMarketResponseReceiverCallbackServiceImpl",
            "process",
            new Class[] {NewOrderAcceptedMarketResponseMessage.class},
            l_ProcessingResult);    
            
            //11111111
            TestDBUtility.deleteAll(MainAccountParams.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            TestDBUtility.insertWithDel(l_mainAccountParams);

            //22222222
            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();            
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
            l_subAccountParams.setAccountId(l_mainAccountParams.getAccountId());
            TestDBUtility.insertWithDel(l_subAccountParams);
 
            
            //3333333333
            TestDBUtility.deleteAll(AioOrderUnitParams.TYPE);
            AioOrderUnitParams l_AioOrderUnitParams = TestDBUtility.getAioOrderUnitRow();
            l_AioOrderUnitParams.setCancelType("2");
            l_AioOrderUnitParams.setAccountId(l_subAccountParams.getAccountId());
            l_AioOrderUnitParams.setSubAccountId(l_subAccountParams.getSubAccountId());
            TestDBUtility.insertWithDel(l_AioOrderUnitParams);
            
            
            
            //4444444444
            TestDBUtility.deleteAll(AioOrderParams.TYPE);
            AioOrderParams l_AioOrderParams = TestDBUtility.getAioOrderRow();
            l_AioOrderParams.setOrderId(2000011L);
            l_AioOrderParams.setAccountId(l_mainAccountParams.getAccountId());
            l_AioOrderParams.setSubAccountId(l_subAccountParams.getSubAccountId());
            TestDBUtility.insertWithDel(l_AioOrderParams);
            

            AioOrderUnit l_orderUnit = new AioOrderUnitForTest(l_AioOrderUnitParams);
            String l_strErrorCode = "1234";
            String l_strAcceptDiv = WEB3AcceptDivDef.ERROR;

            l_impl.execute(l_orderUnit, l_strErrorCode ,l_strAcceptDiv);

        }
        catch(WEB3BaseException l_ex)
        {
            log.error(l_ex.getErrorMessage());
            fail();
        }
 
        
        log.exiting(STR_METHOD_NAME);
    }
    

    /*
     * Test method for 'webbroker3.aio.service.delegate.stdimpls.WEB3AccTransChangeAcceptUnitServiceImpl.getSubAccount(AioOrderUnit)'
     */
    /**
     * 参数l_orderUnit?空
     *
     */
    public void testGetSubAccount_T1() 
    {
        final  String STR_METHOD_NAME = "testGetSubAccount_T1";
        log.entering(STR_METHOD_NAME);
        
        try
        {

            AioOrderUnit l_orderUnit = null;
            l_impl.getSubAccount(l_orderUnit);
            fail();
 
        }
        catch(WEB3BaseException l_ex)
        {
            log.error(l_ex.getErrorMessage());
            fail();
        }
        catch(WEB3BaseRuntimeException l_ex)
        {
            assertEquals(l_ex.getErrorInfo(),WEB3ErrorCatalog.SYSTEM_ERROR_80017);
        }
        
        
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * 補助口座オブジェクトを取得する。 
     *
     */
    public void testGetSubAccount_T2() 
    {
        final  String STR_METHOD_NAME = "testGetSubAccount_T2";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            //          11111111
            TestDBUtility.deleteAll(MainAccountParams.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            TestDBUtility.insertWithDel(l_mainAccountParams);

            //22222222
            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();            
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
            l_subAccountParams.setAccountId(l_mainAccountParams.getAccountId());
            TestDBUtility.insertWithDel(l_subAccountParams);
            
            TestDBUtility.deleteAll(AioOrderUnitParams.TYPE);
            AioOrderUnitParams l_AioOrderUnitParams = TestDBUtility.getAioOrderUnitRow();
            l_AioOrderUnitParams.setCancelType("2");
            l_AioOrderUnitParams.setAccountId(l_subAccountParams.getAccountId());
            l_AioOrderUnitParams.setSubAccountId(l_subAccountParams.getSubAccountId());
            TestDBUtility.insertWithDel(l_AioOrderUnitParams);

            AioOrderUnit l_orderUnit = new AioOrderUnitForTest(l_AioOrderUnitParams);
            l_impl.getSubAccount(l_orderUnit);
          
            
        }
        catch(WEB3BaseException l_ex)
        {
            log.error(l_ex.getErrorMessage());
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * 
     *補助口座オブジェクトを取得できない時。 
     */
    public void testGetSubAccount_T3() 
    {
        final  String STR_METHOD_NAME = "testGetSubAccount_T3";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            //          11111111
            TestDBUtility.deleteAll(MainAccountParams.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            TestDBUtility.insertWithDel(l_mainAccountParams);

            //22222222
            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();            
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
            l_subAccountParams.setAccountId(l_mainAccountParams.getAccountId());
            TestDBUtility.insertWithDel(l_subAccountParams);
            
            TestDBUtility.deleteAll(AioOrderUnitParams.TYPE);
            AioOrderUnitParams l_AioOrderUnitParams = TestDBUtility.getAioOrderUnitRow();
            l_AioOrderUnitParams.setCancelType("2");
            TestDBUtility.insertWithDel(l_AioOrderUnitParams);

            AioOrderUnit l_orderUnit = new AioOrderUnitForTest(l_AioOrderUnitParams);
            l_impl.getSubAccount(l_orderUnit);
          
            fail();
        }
        catch(WEB3BaseException l_ex)
        {
            log.error(l_ex.getErrorMessage());
            fail();
        }
        catch(WEB3BaseRuntimeException l_ex)
        {
            assertEquals(l_ex.getErrorInfo(),WEB3ErrorCatalog.SYSTEM_ERROR_80005);
            log.error(l_ex.getErrorMessage());

        }

        log.exiting(STR_METHOD_NAME);
    }
    
    
    //innerclass
    public class  AioOrderUnitForTest extends AioOrderUnitImpl
    {
        protected AioOrderUnitForTest(AioOrderUnitRow arg0) {
            super(arg0);
        }
        protected AioOrderUnitForTest(long order_unit_id)
            throws DataQueryException, DataNetworkException
        {
            this(AioOrderUnitDao.findRowByPk(order_unit_id));
        }
    }

}
@
