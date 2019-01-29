head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.00.57.45;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3AsynEquityReceiveCloseOrderServiceImplTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.equity.service.delegate.stdimpls;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeOrder;
import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeOrderUnit;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderUnitParams;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderUnitRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Order;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderAction;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderCategEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderExecution;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderExpirationStatusEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderOpenStatusEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderStatusEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Product;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SideEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TaxTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradedProduct;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.BranchParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.BranchRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.InstitutionParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.InstitutionRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.SubAccountRow;

import test.util.TestDBUtility;

import webbroker3.common.define.WEB3StatusDef;
import webbroker3.equity.data.HostEqtypeCloseOrderNotifyDao;
import webbroker3.equity.data.HostEqtypeCloseOrderNotifyParams;
import webbroker3.equity.data.HostEqtypeCloseOrderNotifyRow;
import webbroker3.equity.message.WEB3EquityCloseOrderRequest;
import webbroker3.equity.service.delegate.stdimpls.WEB3AsynEquityReceiveCloseOrderServiceImpl.WEB3EquityReceiveCloseOrderUnitTransactionCallback;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.gentrade.data.DaemonTriggerParams;
import webbroker3.gentrade.data.DaemonTriggerRow;
import webbroker3.gentrade.data.TradingTimeParams;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

public class WEB3AsynEquityReceiveCloseOrderServiceImplTest extends TestBaseForMock
{
    /**
     * ログユーティリティ<BR>
     */
    private static WEB3LogUtility log =
         WEB3LogUtility.getInstance(
                 WEB3AsynEquityReceiveCloseOrderServiceImplTest.class);

    public WEB3AsynEquityReceiveCloseOrderServiceImplTest(String arg0)
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
     * Test method for 'webbroker3.equity.service.delegate.stdimpls.WEB3AsynEquityReceiveCloseOrderServiceImpl.WEB3AsynEquityReceiveCloseOrderServiceImpl(WEB3EquityCloseOrderRequest)'
     */
    public void testProcessC1()
    {
        final String STR_METHOD_NAME = "testProcessC1()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        try
        {
            TestDBUtility.deleteAllAndCommit(InstitutionRow.TYPE);
            TestDBUtility.deleteAllAndCommit(BranchRow.TYPE);
            TestDBUtility.deleteAllAndCommit(MainAccountRow.TYPE);
            TestDBUtility.deleteAllAndCommit(HostEqtypeCloseOrderNotifyRow.TYPE);
            TestDBUtility.deleteAllAndCommit(DaemonTriggerRow.TYPE);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }

        MainAccountParams l_mainAccountParams =
            TestDBUtility.getMainAccountRow();
        l_mainAccountParams.setAccountId(333812512203L);
        l_mainAccountParams.setInstitutionId(63);
        l_mainAccountParams.setAccountCode("2512246");
        
        InstitutionParams l_institutionParams =
            TestDBUtility.getInstitutionRow();
        l_institutionParams.setInstitutionCode("2D");
        
        BranchParams l_branchParams =
            TestDBUtility.getBranchRow();
        l_branchParams.setInstitutionCode("2D");
        l_branchParams.setBranchCode("381");
        
        HostEqtypeCloseOrderNotifyParams l_hostEqtypeCloseOrderNotifyParams =
            new HostEqtypeCloseOrderNotifyParams();
        l_hostEqtypeCloseOrderNotifyParams.setInstitutionCode("2D");
        l_hostEqtypeCloseOrderNotifyParams.setBranchCode("381");
        l_hostEqtypeCloseOrderNotifyParams.setAccountCode("2512246");
        l_hostEqtypeCloseOrderNotifyParams.setOrderRequestNumber("111");
        l_hostEqtypeCloseOrderNotifyParams.setRequestCode("AI813");
        l_hostEqtypeCloseOrderNotifyParams.setStatus("0");
        
        DaemonTriggerParams l_daemonTriggerParams = new DaemonTriggerParams();
        l_daemonTriggerParams.setThreadNo(111L);
        l_daemonTriggerParams.setTriggerType("1");
        l_daemonTriggerParams.setOrderRequestNumber("1");
        l_daemonTriggerParams.setRangeFrom(0);
        l_daemonTriggerParams.setRangeTo(6);
        l_daemonTriggerParams.setTriggerStatus("1");
        try
        {
            TestDBUtility.insertWithDelAndCommit(l_institutionParams);
            TestDBUtility.insertWithDelAndCommit(l_branchParams);
            TestDBUtility.insertWithDelAndCommit(l_mainAccountParams);
            TestDBUtility.insertWithDelAndCommit(l_hostEqtypeCloseOrderNotifyParams);
            TestDBUtility.insertWithDelAndCommit(l_daemonTriggerParams);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        
        try
        {
            WEB3EquityCloseOrderRequest l_equityCloseOrderRequest = new WEB3EquityCloseOrderRequest();
            l_equityCloseOrderRequest.threadNo = new Long(111);
            WEB3AsynEquityReceiveCloseOrderServiceImpl l_impl =
                new WEB3AsynEquityReceiveCloseOrderServiceImpl(l_equityCloseOrderRequest);

            l_impl.run();
            List l_list = HostEqtypeCloseOrderNotifyDao.findRowsByOrderRequestNumber("111");
            HostEqtypeCloseOrderNotifyRow l_row =(HostEqtypeCloseOrderNotifyRow)l_list.get(0);
            
            assertEquals(WEB3StatusDef.DEALT,l_row.getStatus());
            assertEquals(WEB3DateUtility.formatDate(GtlUtils.getSystemTimestamp(),"yyyyMMdd"), 
                    WEB3DateUtility.formatDate(l_row.getLastUpdatedTimestamp(),"yyyyMMdd"));
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }

    log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    public void testProcessC2()
    {
        final String STR_METHOD_NAME = "testProcessC2()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        try
        {
            TestDBUtility.deleteAllAndCommit(MainAccountRow.TYPE);
            TestDBUtility.deleteAllAndCommit(HostEqtypeCloseOrderNotifyRow.TYPE);
            TestDBUtility.deleteAllAndCommit(DaemonTriggerRow.TYPE);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }

        MainAccountParams l_mainAccountParams =
            TestDBUtility.getMainAccountRow();
        l_mainAccountParams.setAccountId(333812512203L);
        l_mainAccountParams.setInstitutionId(63);
        
        HostEqtypeCloseOrderNotifyParams l_hostEqtypeCloseOrderNotifyParams =
            new HostEqtypeCloseOrderNotifyParams();
        l_hostEqtypeCloseOrderNotifyParams.setInstitutionCode("0D");
        l_hostEqtypeCloseOrderNotifyParams.setBranchCode("381");
        l_hostEqtypeCloseOrderNotifyParams.setAccountCode("2512246");
        l_hostEqtypeCloseOrderNotifyParams.setOrderRequestNumber("111");
        l_hostEqtypeCloseOrderNotifyParams.setRequestCode("AI813");
        l_hostEqtypeCloseOrderNotifyParams.setStatus("0");
        
        DaemonTriggerParams l_daemonTriggerParams = new DaemonTriggerParams();
        l_daemonTriggerParams.setThreadNo(111L);
        l_daemonTriggerParams.setTriggerType("1");
        l_daemonTriggerParams.setOrderRequestNumber("1");
        l_daemonTriggerParams.setRangeFrom(0);
        l_daemonTriggerParams.setRangeTo(6);
        l_daemonTriggerParams.setTriggerStatus("1");
        try
        {
            TestDBUtility.insertWithDelAndCommit(l_mainAccountParams);
            TestDBUtility.insertWithDelAndCommit(l_hostEqtypeCloseOrderNotifyParams);
            TestDBUtility.insertWithDelAndCommit(l_daemonTriggerParams);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        
        try
        {
            WEB3EquityCloseOrderRequest l_equityCloseOrderRequest = new WEB3EquityCloseOrderRequest();
            l_equityCloseOrderRequest.threadNo = new Long(111);
            WEB3AsynEquityReceiveCloseOrderServiceImpl l_impl =
                new WEB3AsynEquityReceiveCloseOrderServiceImpl(l_equityCloseOrderRequest);

            l_impl.run();
            List l_list = HostEqtypeCloseOrderNotifyDao.findRowsByOrderRequestNumber("111");
            HostEqtypeCloseOrderNotifyRow l_row =(HostEqtypeCloseOrderNotifyRow)l_list.get(0);
            
            assertEquals(WEB3StatusDef.DATA_ERROR, l_row.getStatus());
            assertEquals(WEB3DateUtility.formatDate(GtlUtils.getSystemTimestamp(),"yyyyMMdd"), 
                    WEB3DateUtility.formatDate(l_row.getLastUpdatedTimestamp(),"yyyyMMdd"));
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }

    log.exiting(TEST_END + STR_METHOD_NAME);
    }
    

  public void testProcessC3()
  {
      final String STR_METHOD_NAME = "testProcessC3()";
      log.entering(TEST_START + STR_METHOD_NAME);
      
      try
      {
          TestDBUtility.deleteAllAndCommit(MainAccountRow.TYPE);
          TestDBUtility.deleteAllAndCommit(HostEqtypeCloseOrderNotifyRow.TYPE);
          TestDBUtility.deleteAllAndCommit(DaemonTriggerRow.TYPE);
          TestDBUtility.deleteAllAndCommit(EqtypeOrderUnitRow.TYPE);
      }
      catch (Exception l_ex)
      {
          log.error(l_ex.getMessage(), l_ex);
          fail();
      }

      MainAccountParams l_mainAccountParams =
          TestDBUtility.getMainAccountRow();
      l_mainAccountParams.setAccountId(333812512203L);
      l_mainAccountParams.setInstitutionId(33);
      l_mainAccountParams.setInstitutionCode("2D");
      l_mainAccountParams.setBranchCode("381");
      l_mainAccountParams.setAccountCode("2512246");
      l_mainAccountParams.setBranchId(33381L);
      
      InstitutionParams l_institutionParams =
          TestDBUtility.getInstitutionRow();
      l_institutionParams.setInstitutionCode("2D");
      
      BranchParams l_branchParams =
          TestDBUtility.getBranchRow();
      l_branchParams.setInstitutionCode("2D");
      l_branchParams.setBranchCode("381");
      
      HostEqtypeCloseOrderNotifyParams l_hostEqtypeCloseOrderNotifyParams =
          new HostEqtypeCloseOrderNotifyParams();
      l_hostEqtypeCloseOrderNotifyParams.setInstitutionCode("2D");
      l_hostEqtypeCloseOrderNotifyParams.setBranchCode("381");
      l_hostEqtypeCloseOrderNotifyParams.setAccountCode("2512246");
      l_hostEqtypeCloseOrderNotifyParams.setOrderRequestNumber("111");
      l_hostEqtypeCloseOrderNotifyParams.setRequestCode("AI813");
      l_hostEqtypeCloseOrderNotifyParams.setStatus("0");
      
      DaemonTriggerParams l_daemonTriggerParams = new DaemonTriggerParams();
      l_daemonTriggerParams.setThreadNo(111L);
      l_daemonTriggerParams.setTriggerType("1");
      l_daemonTriggerParams.setOrderRequestNumber("1");
      l_daemonTriggerParams.setRangeFrom(0);
      l_daemonTriggerParams.setRangeTo(6);
      l_daemonTriggerParams.setTriggerStatus("1");
      
      TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
      l_tradingTimeParams.setInstitutionCode("2D");
      l_tradingTimeParams.setBranchCode("381");
      l_tradingTimeParams.setTradingTimeType("26");
      l_tradingTimeParams.setBizDateType("0");
      
      try
      {

          TestDBUtility.insertWithDelAndCommit(l_institutionParams);
          TestDBUtility.insertWithDelAndCommit(l_branchParams);
          TestDBUtility.insertWithDelAndCommit(l_mainAccountParams);
          TestDBUtility.insertWithDelAndCommit(l_hostEqtypeCloseOrderNotifyParams);
          TestDBUtility.insertWithDelAndCommit(l_daemonTriggerParams);
          TestDBUtility.insertWithDelAndCommit(l_tradingTimeParams);
      }
      catch (Exception l_ex)
      {
          log.error(l_ex.getMessage(), l_ex);
          fail();
      }
      
      try
      {
          WEB3EquityCloseOrderRequest l_equityCloseOrderRequest = new WEB3EquityCloseOrderRequest();
          l_equityCloseOrderRequest.threadNo = new Long(111);
          WEB3AsynEquityReceiveCloseOrderServiceImpl l_impl =
              new WEB3AsynEquityReceiveCloseOrderServiceImpl(l_equityCloseOrderRequest);

          l_impl.run();
          List l_list = HostEqtypeCloseOrderNotifyDao.findRowsByOrderRequestNumber("111");
          HostEqtypeCloseOrderNotifyRow l_row =(HostEqtypeCloseOrderNotifyRow)l_list.get(0);
          
          assertEquals("9",l_row.getStatus());
          assertEquals(WEB3DateUtility.formatDate(GtlUtils.getSystemTimestamp(),"yyyyMMdd"), 
                  WEB3DateUtility.formatDate(l_row.getLastUpdatedTimestamp(),"yyyyMMdd"));
          
      }
      catch (Exception l_ex)
      {
          log.error(l_ex.getMessage(), l_ex);
          fail();
      }

  log.exiting(TEST_END + STR_METHOD_NAME);
  }
    
    public void testProcessC4()
    {
        final String STR_METHOD_NAME = "testProcessC4()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        try
        {
            TestDBUtility.deleteAllAndCommit(MainAccountRow.TYPE);
            TestDBUtility.deleteAllAndCommit(HostEqtypeCloseOrderNotifyRow.TYPE);
            TestDBUtility.deleteAllAndCommit(DaemonTriggerRow.TYPE);
            TestDBUtility.deleteAllAndCommit(EqtypeOrderUnitRow.TYPE);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }

        MainAccountParams l_mainAccountParams =
            TestDBUtility.getMainAccountRow();
        l_mainAccountParams.setAccountId(333812512203L);
        l_mainAccountParams.setInstitutionId(33);
        l_mainAccountParams.setBranchId(33381L);
        
        InstitutionParams l_institutionParams =
            TestDBUtility.getInstitutionRow();
        l_institutionParams.setInstitutionCode("2D");
        
        BranchParams l_branchParams =
            TestDBUtility.getBranchRow();
        l_branchParams.setInstitutionCode("2D");
        l_branchParams.setBranchCode("381");
        
        HostEqtypeCloseOrderNotifyParams l_hostEqtypeCloseOrderNotifyParams =
            new HostEqtypeCloseOrderNotifyParams();
        l_hostEqtypeCloseOrderNotifyParams.setInstitutionCode("2D");
        l_hostEqtypeCloseOrderNotifyParams.setBranchCode("381");
        l_hostEqtypeCloseOrderNotifyParams.setAccountCode("2512246");
        l_hostEqtypeCloseOrderNotifyParams.setOrderRequestNumber("111");
        l_hostEqtypeCloseOrderNotifyParams.setRequestCode("AI813");
        l_hostEqtypeCloseOrderNotifyParams.setStatus("0");
        
        DaemonTriggerParams l_daemonTriggerParams = new DaemonTriggerParams();
        l_daemonTriggerParams.setThreadNo(111L);
        l_daemonTriggerParams.setTriggerType("1");
        l_daemonTriggerParams.setOrderRequestNumber("1");
        l_daemonTriggerParams.setRangeFrom(0);
        l_daemonTriggerParams.setRangeTo(6);
        l_daemonTriggerParams.setTriggerStatus("1");
        
        TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
        
        
        try
        {

            TestDBUtility.insertWithDelAndCommit(l_institutionParams);
            TestDBUtility.insertWithDelAndCommit(l_branchParams);
            
            TestDBUtility.insertWithDelAndCommit(l_mainAccountParams);

            TestDBUtility.insertWithDelAndCommit(l_hostEqtypeCloseOrderNotifyParams);
            TestDBUtility.insertWithDelAndCommit(l_daemonTriggerParams);
            
            TestDBUtility.insertWithDelAndCommit(l_tradingTimeParams);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        
        try
        {
            WEB3EquityCloseOrderRequest l_equityCloseOrderRequest = new WEB3EquityCloseOrderRequest();
            l_equityCloseOrderRequest.threadNo = new Long(111);
            WEB3AsynEquityReceiveCloseOrderServiceImpl l_impl =
                new WEB3AsynEquityReceiveCloseOrderServiceImpl(l_equityCloseOrderRequest);

            l_impl.run();
            List l_list = HostEqtypeCloseOrderNotifyDao.findRowsByOrderRequestNumber("111");
            HostEqtypeCloseOrderNotifyRow l_row =(HostEqtypeCloseOrderNotifyRow)l_list.get(0);
            
            assertEquals("1",l_row.getStatus());
            assertEquals(WEB3DateUtility.formatDate(GtlUtils.getSystemTimestamp(),"yyyyMMdd"), 
                    WEB3DateUtility.formatDate(l_row.getLastUpdatedTimestamp(),"yyyyMMdd"));
            
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }

    log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    public void testProcessC5()
    {
        final String STR_METHOD_NAME = "testProcessC5()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        try
        {
            TestDBUtility.deleteAllAndCommit(MainAccountRow.TYPE);
            TestDBUtility.deleteAllAndCommit(HostEqtypeCloseOrderNotifyRow.TYPE);
            TestDBUtility.deleteAllAndCommit(DaemonTriggerRow.TYPE);
            TestDBUtility.deleteAllAndCommit(EqtypeOrderUnitRow.TYPE);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }

        MainAccountParams l_mainAccountParams =
            TestDBUtility.getMainAccountRow();
        l_mainAccountParams.setAccountId(333812512203L);
        l_mainAccountParams.setInstitutionId(33);
        l_mainAccountParams.setBranchId(33381L);
        
        EqtypeOrderUnitParams l_eqtypeOrderUnitParams =
            TestDBUtility.getEqtypeOrderUnitRow();
        l_eqtypeOrderUnitParams.setBranchId(33381);
        l_eqtypeOrderUnitParams.setOrderRequestNumber("111");
        l_eqtypeOrderUnitParams.setConfirmedQuantity(11d);
        l_eqtypeOrderUnitParams.setOrderStatus(OrderStatusEnum.NOT_CANCELLED);
       
        InstitutionParams l_institutionParams =
            TestDBUtility.getInstitutionRow();
        l_institutionParams.setInstitutionCode("2D");
        
        BranchParams l_branchParams =
            TestDBUtility.getBranchRow();
        l_branchParams.setInstitutionCode("2D");
        l_branchParams.setBranchCode("381");
        
        HostEqtypeCloseOrderNotifyParams l_hostEqtypeCloseOrderNotifyParams =
            new HostEqtypeCloseOrderNotifyParams();
        l_hostEqtypeCloseOrderNotifyParams.setInstitutionCode("2D");
        l_hostEqtypeCloseOrderNotifyParams.setBranchCode("381");
        l_hostEqtypeCloseOrderNotifyParams.setAccountCode("2512246");
        l_hostEqtypeCloseOrderNotifyParams.setOrderRequestNumber("111");
        l_hostEqtypeCloseOrderNotifyParams.setRequestCode("AI813");
        l_hostEqtypeCloseOrderNotifyParams.setStatus("0");
        
        DaemonTriggerParams l_daemonTriggerParams = new DaemonTriggerParams();
        l_daemonTriggerParams.setThreadNo(111L);
        l_daemonTriggerParams.setTriggerType("1");
        l_daemonTriggerParams.setOrderRequestNumber("1");
        l_daemonTriggerParams.setRangeFrom(0);
        l_daemonTriggerParams.setRangeTo(6);
        l_daemonTriggerParams.setTriggerStatus("1");
        
        try
        {

            TestDBUtility.insertWithDelAndCommit(l_institutionParams);
            TestDBUtility.insertWithDelAndCommit(l_branchParams);
            TestDBUtility.insertWithDelAndCommit(l_mainAccountParams);
            TestDBUtility.insertWithDelAndCommit(l_eqtypeOrderUnitParams);
            TestDBUtility.insertWithDelAndCommit(l_hostEqtypeCloseOrderNotifyParams);
            TestDBUtility.insertWithDelAndCommit(l_daemonTriggerParams);

        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        
        try
        {
            WEB3EquityCloseOrderRequest l_equityCloseOrderRequest = new WEB3EquityCloseOrderRequest();
            l_equityCloseOrderRequest.threadNo = new Long(111);
            WEB3AsynEquityReceiveCloseOrderServiceImpl l_impl =
                new WEB3AsynEquityReceiveCloseOrderServiceImpl(l_equityCloseOrderRequest);

            l_impl.run();
            List l_list = HostEqtypeCloseOrderNotifyDao.findRowsByOrderRequestNumber("111");
            HostEqtypeCloseOrderNotifyRow l_row =(HostEqtypeCloseOrderNotifyRow)l_list.get(0);
            
            assertEquals("9",l_row.getStatus());
            assertEquals(WEB3DateUtility.formatDate(GtlUtils.getSystemTimestamp(),"yyyyMMdd"), 
                    WEB3DateUtility.formatDate(l_row.getLastUpdatedTimestamp(),"yyyyMMdd"));
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }

    log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    /**
     * <失効通知キュー.失効数量に値がセットされている場合>
     * 失効通知キュー.失効数量 < (注文単位.市場から確認済みの数量-注文単位.約定数量)
     * 
     * 株式失効通知キューテーブル.処理区分 = 処理中
     *
     */
    public void testPorcess_C0001()
    {
        final String STR_METHOD_NAME = "testPorcess_C0001()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        WEB3EquityReceiveCloseOrderUnitTransactionCallback l_callBack = null;
        try
        {
            TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);
            WEB3EquityCloseOrderRequest l_equityCloseOrderRequest = new WEB3EquityCloseOrderRequest();
            WEB3AsynEquityReceiveCloseOrderServiceImpl l_impl =
                new WEB3AsynEquityReceiveCloseOrderServiceImpl(l_equityCloseOrderRequest);
            
            TestDBUtility.deleteAll(HostEqtypeCloseOrderNotifyRow.TYPE);
            HostEqtypeCloseOrderNotifyParams l_hostEqtypeCloseOrderNotifyParams =
                TestDBUtility.getHostEqtypeCloseOrderNotifyRow();
            
            l_hostEqtypeCloseOrderNotifyParams.setCloseQuantity(6000);
            
            TestDBUtility.insertWithDel(l_hostEqtypeCloseOrderNotifyParams);
            
            l_callBack = l_impl.new WEB3EquityReceiveCloseOrderUnitTransactionCallback(
                l_hostEqtypeCloseOrderNotifyParams,new EqTypeOrderUnitForTest());
            
            
            
            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            TestDBUtility.insertWithDel(TestDBUtility.getSubAccountRow());
            
            TestDBUtility.deleteAll(EqtypeOrderUnitRow.TYPE);
            EqtypeOrderUnitParams l_eqtypeOrderUnitParams = TestDBUtility.getEqtypeOrderUnitRow();
            l_eqtypeOrderUnitParams.setConfirmedPrice(20000);
            l_eqtypeOrderUnitParams.setExecutedQuantity(5000);
            TestDBUtility.insertWithDel(l_eqtypeOrderUnitParams);
            
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.equity.service.delegate.stdimpls.WEB3EquityReceiveCloseOrderUnitServiceImpl",
                    "notifyCloseOrder", new Class[]{ HostEqtypeCloseOrderNotifyParams.class, EqTypeOrderUnit.class },
                    null);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.gentrade.WEB3GentradeAccountManager", 
                "getSubAccount",
                new Class[] { long.class, long.class },
                new WEB3GentradeSubAccountForTest(333812512203L,33381251220301L));
            
            l_callBack.process();
            assertTrue(true);
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    /**
     * <失効通知キュー.失効数量に値がセットされていない場合>
     * 注文単位.約定数量 < 失効通知キュー.約定数量
     * 
     * 株式失効通知キューテーブル.処理区分 = 処理中
     *
     */
    public void testPorcess_C0002()
    {
        final String STR_METHOD_NAME = "testPorcess_C0002()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        WEB3EquityReceiveCloseOrderUnitTransactionCallback l_callBack = null;
        try
        {
            TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);
            WEB3EquityCloseOrderRequest l_equityCloseOrderRequest = new WEB3EquityCloseOrderRequest();
            WEB3AsynEquityReceiveCloseOrderServiceImpl l_impl =
                new WEB3AsynEquityReceiveCloseOrderServiceImpl(l_equityCloseOrderRequest);
            
            TestDBUtility.deleteAll(HostEqtypeCloseOrderNotifyRow.TYPE);
            HostEqtypeCloseOrderNotifyParams l_hostEqtypeCloseOrderNotifyParams =
                TestDBUtility.getHostEqtypeCloseOrderNotifyRow();
            
            l_hostEqtypeCloseOrderNotifyParams.setExecutedQuantity(8000);
            
            TestDBUtility.insertWithDel(l_hostEqtypeCloseOrderNotifyParams);
            
            l_callBack = l_impl.new WEB3EquityReceiveCloseOrderUnitTransactionCallback(
                l_hostEqtypeCloseOrderNotifyParams,new EqTypeOrderUnitForTest());
            
            
            
            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            TestDBUtility.insertWithDel(TestDBUtility.getSubAccountRow());
            
            TestDBUtility.deleteAll(EqtypeOrderUnitRow.TYPE);
            EqtypeOrderUnitParams l_eqtypeOrderUnitParams = TestDBUtility.getEqtypeOrderUnitRow();
            l_eqtypeOrderUnitParams.setConfirmedPrice(20000);
            l_eqtypeOrderUnitParams.setExecutedQuantity(5000);
            TestDBUtility.insertWithDel(l_eqtypeOrderUnitParams);
            
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.equity.service.delegate.stdimpls.WEB3EquityReceiveCloseOrderUnitServiceImpl",
                    "notifyCloseOrder", new Class[]{ HostEqtypeCloseOrderNotifyParams.class, EqTypeOrderUnit.class },
                    null);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.gentrade.WEB3GentradeAccountManager", 
                "getSubAccount",
                new Class[] { long.class, long.class },
                new WEB3GentradeSubAccountForTest(333812512203L,33381251220301L));
            
            l_callBack.process();
            assertTrue(true);
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    /**
     * <失効通知キュー.失効数量に値がセットされていない場合>
     * 注文単位.約定数量 > 失効通知キュー.約定数量
     * 
     * 株式失効通知キューテーブル.処理区分 = 処理済
     */
    public void testPorcess_C0003()
    {
        final String STR_METHOD_NAME = "testPorcess_C0003()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        WEB3EquityReceiveCloseOrderUnitTransactionCallback l_callBack = null;
        try
        {
            TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);
            WEB3EquityCloseOrderRequest l_equityCloseOrderRequest = new WEB3EquityCloseOrderRequest();
            WEB3AsynEquityReceiveCloseOrderServiceImpl l_impl =
                new WEB3AsynEquityReceiveCloseOrderServiceImpl(l_equityCloseOrderRequest);
            
            TestDBUtility.deleteAll(HostEqtypeCloseOrderNotifyRow.TYPE);
            HostEqtypeCloseOrderNotifyParams l_hostEqtypeCloseOrderNotifyParams =
                TestDBUtility.getHostEqtypeCloseOrderNotifyRow();
            
            l_hostEqtypeCloseOrderNotifyParams.setExecutedQuantity(8000);
            
            TestDBUtility.insertWithDel(l_hostEqtypeCloseOrderNotifyParams);
            
            l_callBack = l_impl.new WEB3EquityReceiveCloseOrderUnitTransactionCallback(
                l_hostEqtypeCloseOrderNotifyParams,new EqTypeOrderUnitForTest());
            
            
            
            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            TestDBUtility.insertWithDel(TestDBUtility.getSubAccountRow());
            
            TestDBUtility.deleteAll(EqtypeOrderUnitRow.TYPE);
            EqtypeOrderUnitParams l_eqtypeOrderUnitParams = TestDBUtility.getEqtypeOrderUnitRow();
            l_eqtypeOrderUnitParams.setConfirmedPrice(20000);
            l_eqtypeOrderUnitParams.setExecutedQuantity(15000);
            TestDBUtility.insertWithDel(l_eqtypeOrderUnitParams);
            
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.equity.service.delegate.stdimpls.WEB3EquityReceiveCloseOrderUnitServiceImpl",
                    "notifyCloseOrder", new Class[]{ HostEqtypeCloseOrderNotifyParams.class, EqTypeOrderUnit.class },
                    null);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.gentrade.WEB3GentradeAccountManager", 
                "getSubAccount",
                new Class[] { long.class, long.class },
                new WEB3GentradeSubAccountForTest(333812512203L,33381251220301L));
            
            l_callBack.process();
            assertTrue(true);
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    private class EqTypeOrderUnitForTest implements EqTypeOrderUnit
    {

        public EqTypeOrder getEqTypeOrder()
        {
            // TODO Auto-generated method stub
            return null;
        }

        public long getOrderUnitId()
        {
            // TODO Auto-generated method stub
            return 3304148080001L;
        }

        public long getOrderId()
        {
            // TODO Auto-generated method stub
            return 0;
        }

        public long getAccountId()
        {
            // TODO Auto-generated method stub
            return 333812512203L;
        }

        public long getSubAccountId()
        {
            // TODO Auto-generated method stub
            return 33381251220301L;
        }

        public long getBranchId()
        {
            // TODO Auto-generated method stub
            return 0;
        }

        public long getTraderId()
        {
            // TODO Auto-generated method stub
            return 0;
        }

        public OrderAction[] getOrderActions()
        {
            // TODO Auto-generated method stub
            return null;
        }

        public Timestamp getReceivedTimestamp()
        {
            // TODO Auto-generated method stub
            return null;
        }

        public Timestamp getExpirationTimestamp()
        {
            // TODO Auto-generated method stub
            return null;
        }

        public Product getProduct()
        {
            // TODO Auto-generated method stub
            return null;
        }

        public Order getOrder()
        {
            // TODO Auto-generated method stub
            return null;
        }

        public OrderExecution[] getExecutions()
        {
            // TODO Auto-generated method stub
            return null;
        }

        public OrderOpenStatusEnum getOrderOpenStatus()
        {
            // TODO Auto-generated method stub
            return null;
        }

        public OrderTypeEnum getOrderType()
        {
            // TODO Auto-generated method stub
            return null;
        }

        public OrderCategEnum getOrderCateg()
        {
            // TODO Auto-generated method stub
            return null;
        }

        public TaxTypeEnum getTaxType()
        {
            // TODO Auto-generated method stub
            return null;
        }

        public SideEnum getSide()
        {
            // TODO Auto-generated method stub
            return null;
        }

        public OrderStatusEnum getOrderStatus()
        {
            // TODO Auto-generated method stub
            return null;
        }

        public OrderExpirationStatusEnum getExpirationStatus()
        {
            // TODO Auto-generated method stub
            return null;
        }

        public boolean isExpired()
        {
            // TODO Auto-generated method stub
            return false;
        }

        public boolean isFullyExecuted()
        {
            // TODO Auto-generated method stub
            return false;
        }

        public boolean isPartiallyExecuted()
        {
            // TODO Auto-generated method stub
            return false;
        }

        public boolean isUnexecuted()
        {
            // TODO Auto-generated method stub
            return false;
        }

        public double getConfirmedPrice()
        {
            // TODO Auto-generated method stub
            return 90000;
        }

        public boolean isConfirmedPriceMarketOrder()
        {
            // TODO Auto-generated method stub
            return false;
        }

        public double getConfirmedQuantity()
        {
            // TODO Auto-generated method stub
            return 0;
        }

        public double getQuantity()
        {
            // TODO Auto-generated method stub
            return 0;
        }

        public Date getDeliveryDate()
        {
            // TODO Auto-generated method stub
            return null;
        }

        public double getExecutedAmount()
        {
            // TODO Auto-generated method stub
            return 0;
        }

        public double getExecutedQuantity()
        {
            // TODO Auto-generated method stub
            return 5000;
        }

        public double getLimitPrice()
        {
            // TODO Auto-generated method stub
            return 0;
        }

        public boolean isMarketOrder()
        {
            // TODO Auto-generated method stub
            return false;
        }

        public TradedProduct getTradedProduct()
        {
            // TODO Auto-generated method stub
            return null;
        }

        public Object getDataSourceObject()
        {
            // TODO Auto-generated method stub
            return null;
        }
        
    }
    
    private class WEB3GentradeSubAccountForTest extends WEB3GentradeSubAccount
    {

        public WEB3GentradeSubAccountForTest(long l_lngAccountId, long l_lngSubAccountId) throws DataQueryException, DataNetworkException
        {
            super(l_lngAccountId, l_lngSubAccountId);
            // TODO Auto-generated constructor stub
        }
        
    }
}

@
