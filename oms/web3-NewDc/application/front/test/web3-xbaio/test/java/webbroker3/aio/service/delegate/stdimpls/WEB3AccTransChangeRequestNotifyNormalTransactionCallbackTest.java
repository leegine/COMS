head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.01.37.56;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3AccTransChangeRequestNotifyNormalTransactionCallbackTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.aio.service.delegate.stdimpls;

import java.util.List;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderUnit;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccountTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.SubAccountParams;
import com.fitechlabs.xtrade.plugin.tc.xbaio.AioOrderUnit;
import com.fitechlabs.xtrade.plugin.tc.xbaio.AssetTransferTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.xbaio.data.AioOrderUnitDao;
import com.fitechlabs.xtrade.plugin.tc.xbaio.data.AioOrderUnitParams;
import com.fitechlabs.xtrade.plugin.tc.xbaio.data.AioOrderUnitRow;
import com.fitechlabs.xtrade.plugin.tc.xbaio.stdimpls.AioOrderUnitImpl;

import test.util.TestDBUtility;

import webbroker3.aio.data.HostTransferReceiptParams;
import webbroker3.aio.data.HostTransferReceiptRow;
import webbroker3.aio.service.delegate.WEB3AccTransChangeRequestNotifyUnitService;
import webbroker3.common.WEB3BaseException;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3LogUtility;

public class WEB3AccTransChangeRequestNotifyNormalTransactionCallbackTest
        extends TestBaseForMock {
    
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AccTransChangeRequestNotifyNormalTransactionCallbackTest.class);


    public WEB3AccTransChangeRequestNotifyNormalTransactionCallbackTest(
            String arg0) {
        super(arg0);
    }

    protected void setUp() throws Exception {
        super.setUp();
        MOCK_MANAGER.setIsMockUsed(true);
    }

    protected void tearDown() throws Exception {
        super.tearDown();
    }

    
    /*
     * Test method for 'webbroker3.aio.service.delegate.stdimpls.WEB3AccTransChangeRequestNotifyNormalTransactionCallback.process()'
     */
    /**
     * ’•¶Ží•Ê =ˆ×‘Ö•ÛØ‹àU‘Ö’•¶(—a‚©‚è‹à‚©‚çˆ×‘Ö•ÛØ‹à)?
     */
    public void testProcess_T1() 
    {
        final String STR_METHOD_NAME = "testProcess_T1()";
        log.entering(STR_METHOD_NAME);
        
        HostTransferReceiptParams l_hostTransferReceiptParams = this.getHostTransferReceiptRow();
        OrderTypeEnum l_orderType= OrderTypeEnum.FX_GUARANTEE_FROM_DEPOSIT_AMOUNT;

        try
        {
            TestDBUtility.deleteAll(HostTransferReceiptParams.TYPE);
            TestDBUtility.insertWithDel(l_hostTransferReceiptParams);
            
            QueryProcessor p = Processors.getDefaultProcessor();
            List l_lis = p.doFindAllQuery(HostTransferReceiptParams.TYPE);
            HostTransferReceiptRow l_row = (HostTransferReceiptRow)l_lis.get(0);
            log.info("================"+ l_hostTransferReceiptParams.getRowid());
            log.info("================"+ l_row.getRowid());
            
            l_hostTransferReceiptParams.setRowid(l_row.getRowid());
            WEB3AccTransChangeRequestNotifyNormalTransactionCallback l_callbck = 
                new WEB3AccTransChangeRequestNotifyNormalTransactionCallbackForTest(l_hostTransferReceiptParams,l_orderType);

            l_callbck.process();

           List l_lisresult =  p.doFindAllQuery(HostTransferReceiptParams.TYPE);
 
           if (l_lisresult.size() > 0 )
           {
               HostTransferReceiptRow l_rowresult = (HostTransferReceiptRow)l_lisresult.get(0);
               assertEquals(l_rowresult.getStatus(),"1");
           }

        }
        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage(),l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    
    /**
     * ’•¶Ží•Ê =‚»‚Ì‘¼U‘Ö’•¶(—a‚©‚è‹à‚©‚çX)?
     *
     */
    public void testProcess_T2() 
    {
        final String STR_METHOD_NAME = "testProcess_T2()";
        log.entering(STR_METHOD_NAME);
        
        HostTransferReceiptParams l_hostTransferReceiptParams = this.getHostTransferReceiptRow();
        OrderTypeEnum l_orderType= OrderTypeEnum.TO_OTHER_TRANSFER;

        try
        {
            TestDBUtility.deleteAll(HostTransferReceiptParams.TYPE);
            TestDBUtility.insertWithDel(l_hostTransferReceiptParams);
            
            QueryProcessor p = Processors.getDefaultProcessor();
            List l_lis = p.doFindAllQuery(HostTransferReceiptParams.TYPE);
            HostTransferReceiptRow l_row = (HostTransferReceiptRow)l_lis.get(0);
            log.info("================"+ l_hostTransferReceiptParams.getRowid());
            log.info("================"+ l_row.getRowid());
            
            l_hostTransferReceiptParams.setRowid(l_row.getRowid());
            WEB3AccTransChangeRequestNotifyNormalTransactionCallback l_callbck = 
                new WEB3AccTransChangeRequestNotifyNormalTransactionCallbackForTest(l_hostTransferReceiptParams,l_orderType);

            l_callbck.process();
            List l_lisresult =  p.doFindAllQuery(HostTransferReceiptParams.TYPE);
            
            if (l_lisresult.size() > 0 )
            {
                HostTransferReceiptRow l_rowresult = (HostTransferReceiptRow)l_lisresult.get(0);
                assertEquals(l_rowresult.getStatus(),"1");
            }

        }
        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage(),l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
        
    }
    
    
    /**
     * ’•¶Ží•Ê =1012(ˆ×‘Ö•ÛØ‹àU‘Ö’•¶(ˆ×‘Ö•ÛØ‹à‚©‚ç—a‚©‚è‹à))
     */
    public void testProcess_T3() 
    {
        final String STR_METHOD_NAME = "testProcess_T3()";
        log.entering(STR_METHOD_NAME);
        
        HostTransferReceiptParams l_hostTransferReceiptParams = this.getHostTransferReceiptRow();
        OrderTypeEnum l_orderType= OrderTypeEnum.DEPOSIT_AMOUNT_FROM_FX_GUARANTEE;

        try
        {
            TestDBUtility.deleteAll(HostTransferReceiptParams.TYPE);
            TestDBUtility.insertWithDel(l_hostTransferReceiptParams);
            
            QueryProcessor p = Processors.getDefaultProcessor();
            List l_lis = p.doFindAllQuery(HostTransferReceiptParams.TYPE);
            HostTransferReceiptRow l_row = (HostTransferReceiptRow)l_lis.get(0);
            log.info("================"+ l_hostTransferReceiptParams.getRowid());
            log.info("================"+ l_row.getRowid());
            
            l_hostTransferReceiptParams.setRowid(l_row.getRowid());
            WEB3AccTransChangeRequestNotifyNormalTransactionCallback l_callbck = 
                new WEB3AccTransChangeRequestNotifyNormalTransactionCallbackForTest(l_hostTransferReceiptParams,l_orderType);

            l_callbck.process();
            List l_lisresult =  p.doFindAllQuery(HostTransferReceiptParams.TYPE);
            
            if (l_lisresult.size() > 0 )
            {
                HostTransferReceiptRow l_rowresult = (HostTransferReceiptRow)l_lisresult.get(0);
                assertEquals(l_rowresult.getStatus(),"1");
            }

        }
        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage(),l_ex);
            fail();
        }

        log.exiting(STR_METHOD_NAME);
        
    }
    
    
    /**
     * ’•¶Ží•Ê = 1018(‚»‚Ì‘¼U‘Ö’•¶(X‚©‚ç—a‚©‚è‹à))
     *
     */
    public void testProcess_T4() 
    {
        final String STR_METHOD_NAME = "testProcess_T4()";
        log.entering(STR_METHOD_NAME);
        
        HostTransferReceiptParams l_hostTransferReceiptParams = this.getHostTransferReceiptRow();
        OrderTypeEnum l_orderType= OrderTypeEnum.FROM_OTHER_TRANSFER;

        try
        {
            TestDBUtility.deleteAll(HostTransferReceiptParams.TYPE);
            TestDBUtility.insertWithDel(l_hostTransferReceiptParams);
            
            QueryProcessor p = Processors.getDefaultProcessor();
            List l_lis = p.doFindAllQuery(HostTransferReceiptParams.TYPE);
            HostTransferReceiptRow l_row = (HostTransferReceiptRow)l_lis.get(0);
            log.info("================"+ l_hostTransferReceiptParams.getRowid());
            log.info("================"+ l_row.getRowid());
            
            l_hostTransferReceiptParams.setRowid(l_row.getRowid());
            WEB3AccTransChangeRequestNotifyNormalTransactionCallback l_callbck = 
                new WEB3AccTransChangeRequestNotifyNormalTransactionCallbackForTest(l_hostTransferReceiptParams,l_orderType);

            l_callbck.process();
            List l_lisresult =  p.doFindAllQuery(HostTransferReceiptParams.TYPE);
            
            if (l_lisresult.size() > 0 )
            {
                HostTransferReceiptRow l_rowresult = (HostTransferReceiptRow)l_lisresult.get(0);
                assertEquals(l_rowresult.getStatus(),"1");
            }

        }
        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage(),l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
        
    }
    
    
    
    /**
     * ’•¶Ží•Ê !=1012(ˆ×‘Ö•ÛØ‹àU‘Ö’•¶(ˆ×‘Ö•ÛØ‹à‚©‚ç—a‚©‚è‹à))or 
     *      1018(‚»‚Ì‘¼U‘Ö’•¶(X‚©‚ç—a‚©‚è‹à))or 
     *      (ˆ×‘Ö•ÛØ‹àU‘Ö’•¶(—a‚©‚è‹à‚©‚çˆ×‘Ö•ÛØ‹à))or (
     *      ‚»‚Ì‘¼U‘Ö’•¶(—a‚©‚è‹à‚©‚çX))?
     *
     */
    public void testProcess_T5() 
    {
        final String STR_METHOD_NAME = "testProcess_T5";
        log.entering(STR_METHOD_NAME);
        
        HostTransferReceiptParams l_hostTransferReceiptParams = this.getHostTransferReceiptRow();
        OrderTypeEnum l_orderType= OrderTypeEnum.UNDEFINED;

        try
        {
            TestDBUtility.deleteAll(HostTransferReceiptParams.TYPE);
            TestDBUtility.insertWithDel(l_hostTransferReceiptParams);
            
            QueryProcessor p = Processors.getDefaultProcessor();
            List l_lis = p.doFindAllQuery(HostTransferReceiptParams.TYPE);
            HostTransferReceiptRow l_row = (HostTransferReceiptRow)l_lis.get(0);
            log.info("================"+ l_hostTransferReceiptParams.getRowid());
            log.info("================"+ l_row.getRowid());
            
            l_hostTransferReceiptParams.setRowid(l_row.getRowid());
            WEB3AccTransChangeRequestNotifyNormalTransactionCallback l_callbck = 
                new WEB3AccTransChangeRequestNotifyNormalTransactionCallbackForTest(l_hostTransferReceiptParams,l_orderType);

            l_callbck.process();
            List l_lisresult =  p.doFindAllQuery(HostTransferReceiptParams.TYPE);
            
            if (l_lisresult.size() > 0 )
            {
                HostTransferReceiptRow l_rowresult = (HostTransferReceiptRow)l_lisresult.get(0);
                assertEquals(l_rowresult.getStatus(),"1");
            }

        }
        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage(),l_ex);
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
        
    }

    
    
    
    
    /*
     * Test method for 'webbroker3.aio.service.delegate.stdimpls.WEB3AccTransChangeRequestNotifyNormalTransactionCallback.createOrder(HostTransferReceiptParams, OrderTypeEnum, AssetTransferTypeEnum)'
     */
    public void testCreateOrder_T1() 
    {
        final String STR_METHOD_NAME = "testCreateOrder_T1";
        log.entering(STR_METHOD_NAME);
        
        
        HostTransferReceiptParams l_hostTransferReceiptParams = this.getHostTransferReceiptRow();
        OrderTypeEnum l_orderType= OrderTypeEnum.ASSET_IN;
        AssetTransferTypeEnum l_changeType = AssetTransferTypeEnum.CASH_IN;
        
        
        WEB3AccTransChangeRequestNotifyNormalTransactionCallback l_callbck = 
            new WEB3AccTransChangeRequestNotifyNormalTransactionCallback(l_hostTransferReceiptParams,l_orderType);
        
        try
        {
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.aio.service.delegate.stdimpls.WEB3AccTransChangeAcceptUnitServiceImpl",
                    "execute",
                    new Class[] {AioOrderUnit.class, String.class, String.class},
                    null);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.aio.service.delegate.stdimpls.WEB3AccTransChangeCompleteUnitServiceImpl",
                    "completeChange",
                    new Class[] {AioOrderUnit.class},
                    null);

            Services.overrideService(WEB3AccTransChangeRequestNotifyUnitService.class ,new WEB3AccTransChangeRequestNotifyUnitServiceImplForTest());

            TestDBUtility.deleteAll(MainAccountParams.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            TestDBUtility.insertWithDel(l_mainAccountParams);

            
            TestDBUtility.deleteAll(SubAccountParams.TYPE);
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setAccountId(l_mainAccountParams.getAccountId());
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_MARGIN_SUB_ACCOUNT);
            
            TestDBUtility.insertWithDel(l_subAccountParams);
            
            
            TestDBUtility.deleteAll(AioOrderUnitParams.TYPE);
            AioOrderUnitParams l_AioOrderUnitParams = TestDBUtility.getAioOrderUnitRow();
            l_AioOrderUnitParams.setOrderUnitId(1001L);
            l_AioOrderUnitParams.setAccountId(l_mainAccountParams.getAccountId());
            l_AioOrderUnitParams.setSubAccountId(l_subAccountParams.getSubAccountId());
            TestDBUtility.insertWithDel(l_AioOrderUnitParams);
            
            OrderUnit[] l_orderunit = new OrderUnit[1];
            l_orderunit[0] = new AioOrderUnitForTest(l_AioOrderUnitParams);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.aio.WEB3AioOrderManager", "getOrderUnits", new Class[]
                    { long.class },
                    l_orderunit);
            
            
            l_callbck.createOrder(l_hostTransferReceiptParams,l_orderType,l_changeType);
        
        }
        catch(WEB3BaseException l_ex)
        {
            log.error(l_ex.getErrorMessage(),l_ex);
            fail();
            
            
        }
        
        
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testCreateOrder_T2() 
    {
        final String STR_METHOD_NAME = "testCreateOrder_T2";
        log.entering(STR_METHOD_NAME);
        
        HostTransferReceiptParams l_hostTransferReceiptParams = this.getHostTransferReceiptRow();
        OrderTypeEnum l_orderType= OrderTypeEnum.ASSET_IN;
        AssetTransferTypeEnum l_changeType = AssetTransferTypeEnum.CASH_IN;
        
        
        WEB3AccTransChangeRequestNotifyNormalTransactionCallback l_callbck = 
            new WEB3AccTransChangeRequestNotifyNormalTransactionCallback(l_hostTransferReceiptParams,l_orderType);
        
        try
        {
            MOCK_MANAGER.setIsMockUsed(true);
            
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.aio.service.delegate.stdimpls.WEB3AccTransChangeAcceptUnitServiceImpl",
                    "execute",
                    new Class[] {AioOrderUnit.class, String.class, String.class},
                    null);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.aio.service.delegate.stdimpls.WEB3AccTransChangeCompleteUnitServiceImpl",
                    "completeChange",
                    new Class[] {AioOrderUnit.class},
                    null);
            
            Services.overrideService(WEB3AccTransChangeRequestNotifyUnitService.class ,new WEB3AccTransChangeRequestNotifyUnitServiceImplForTest());
            
            
            TestDBUtility.deleteAll(MainAccountParams.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            TestDBUtility.insertWithDel(l_mainAccountParams);

            
            TestDBUtility.deleteAll(SubAccountParams.TYPE);
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setAccountId(l_mainAccountParams.getAccountId());
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
            
            TestDBUtility.insertWithDel(l_subAccountParams);
            
            
            TestDBUtility.deleteAll(AioOrderUnitParams.TYPE);
            AioOrderUnitParams l_AioOrderUnitParams = TestDBUtility.getAioOrderUnitRow();
            l_AioOrderUnitParams.setOrderUnitId(1001L);
            l_AioOrderUnitParams.setAccountId(l_mainAccountParams.getAccountId());
            l_AioOrderUnitParams.setSubAccountId(l_subAccountParams.getSubAccountId());
            TestDBUtility.insertWithDel(l_AioOrderUnitParams);
            
            OrderUnit[] l_orderunit = new OrderUnit[1];
            l_orderunit[0] = new AioOrderUnitForTest(l_AioOrderUnitParams);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.aio.WEB3AioOrderManager", "getOrderUnits", new Class[]
                    { long.class },
                    l_orderunit);
            
            l_callbck.createOrder(l_hostTransferReceiptParams,l_orderType,l_changeType);
        
        }
        catch(WEB3BaseException l_ex)
        {
            log.error(l_ex.getErrorMessage(),l_ex);
            fail();
            
            
        }
        
        
        log.exiting(STR_METHOD_NAME);
    }

    public HostTransferReceiptParams getHostTransferReceiptRow()
    {
        
        HostTransferReceiptParams l_params = new HostTransferReceiptParams();
 
        //rowid                                           
        l_params.setRowid("1");
        //request_code                                           
        l_params.setRequestCode("10");
        //institution_code                                           
        l_params.setInstitutionCode("0D");
        //branch_code                                           
        l_params.setBranchCode("381");
        //account_code                                           
        l_params.setAccountCode("1");
        //trader_code                                           
        l_params.setTraderCode("1");
        //deposit_type_div                                           
        l_params.setDepositTypeDiv("1");
        //transfer_amount                                         
        l_params.setTransferAmount(100);
        //cancel_div                                           
        l_params.setCancelDiv("1");
        //transfer_time                                           
        l_params.setTransferTime(GtlUtils.getSystemTimestamp());
        //order_request_number                                           
        l_params.setOrderRequestNumber("1");
        //status                                           
        l_params.setStatus("0");
        //remark_code                                           
        l_params.setRemarkCode("1");
        //transfer_date                                           
        l_params.setTransferDate("");
        //created_timestamp                                           
        l_params.setCreatedTimestamp(GtlUtils.getSystemTimestamp());

        return l_params;
        
    }
    
    private class WEB3AccTransChangeRequestNotifyUnitServiceImplForTest extends WEB3AccTransChangeRequestNotifyUnitServiceImpl
    {
        
        
        public long submitOrder(
                HostTransferReceiptParams l_hostTransferReceiptParams,
                OrderTypeEnum l_orderType,
                AssetTransferTypeEnum l_changeType)
                throws WEB3BaseException
            {
                String STR_METHOD_NAME = "submitOrder(" +
                        "HostTransferReceiptParams l_hostTransferReceiptParams," +
                        "OrderTypeEnum l_orderType," +
                        "AssetTransferTypeEnum l_changeType)";

                return 1001L;
                
                
            }

    }
    
    private class  AioOrderUnitForTest extends AioOrderUnitImpl
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
    
    public class WEB3AccTransChangeRequestNotifyNormalTransactionCallbackForTest extends WEB3AccTransChangeRequestNotifyNormalTransactionCallback
    {

        /**
         * 
         */
        private static final long serialVersionUID = 1L;


        public WEB3AccTransChangeRequestNotifyNormalTransactionCallbackForTest(HostTransferReceiptParams l_hostTransferReceiptParams, OrderTypeEnum l_orderType) {
            super(l_hostTransferReceiptParams, l_orderType);
            // TODO Auto-generated constructor stub
        }
        
        protected void createOrder(
                HostTransferReceiptParams l_hostTransferReceiptParams,
                OrderTypeEnum l_orderType,
                AssetTransferTypeEnum l_changeType)
                    throws WEB3BaseException
            {
                log.info("inner class mock");
            }
    }
    
}
@
