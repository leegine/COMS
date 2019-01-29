head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.01.32.58;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3AioMultiBankSettleControlServiceImplTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : //TODO(WEB3AioMultiBankSettleControlServiceImplTest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2007/05/08 何文敏 (中訊) 新規作成
Revesion History : 2007/07/31 金傑 (中訊) 仕様変更・モデル No.740
*/
package webbroker3.aio;

/**
 *
 * @@author 何文敏(中訊)
 * @@version 1.0
 */
import java.util.Calendar;
import java.util.HashMap;

import com.fitechlabs.xtrade.plugin.security.oplogin.data.LoginParams;
import com.fitechlabs.xtrade.plugin.security.oplogin.data.LoginRow;
import com.fitechlabs.xtrade.plugin.security.oplogin.data.LoginSessionParams;
import com.fitechlabs.xtrade.plugin.security.oplogin.data.LoginSessionRow;
import com.fitechlabs.xtrade.plugin.security.oplogin.data.LoginTypeParams;
import com.fitechlabs.xtrade.plugin.security.oplogin.data.LoginTypeRow;

import test.util.TestDBUtility;

import webbroker3.aio.data.BankCashTransferStatusParams;
import webbroker3.aio.data.BankOrderRequestParams;
import webbroker3.aio.data.BankOrderRequestRow;
import webbroker3.aio.data.BankSettleResultResponseParams;
import webbroker3.aio.data.BankSettleStartRequestParams;
import webbroker3.aio.data.HostCashTransOrderAcceptParams;
import webbroker3.common.WEB3BaseException;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;


public class WEB3AioMultiBankSettleControlServiceImplTest extends TestBaseForMock
{
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AioMultiBankSettleControlServiceImplTest.class);
    
    WEB3AioMultiBankSettleControlServiceImpl l_mpl =
        new WEB3AioMultiBankSettleControlServiceImpl();
    public WEB3AioMultiBankSettleControlServiceImplTest(String arg0)
    {
        super(arg0);
    }

    protected void setUp() throws Exception
    {
        super.setUp();
        MOCK_MANAGER.setIsMockUsed(true);
    }

    protected void tearDown() throws Exception
    {
        super.tearDown();
    }
/*
    public void testGetCashTransSituation()
    {
        final String STR_METHOD_NAME = "testGetCashTransSituation()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            TestDBUtility.insertWithDel(l_subAccountParams);
            
            TestDBUtility.deleteAll(BankCashTransferStatusRow.TYPE);
            BankCashTransferStatusParams l_bankCashTransferStatusParams = this.getBankCashTransferStatusRow();
            l_bankCashTransferStatusParams.setTransactionStatus("1");
            l_bankCashTransferStatusParams.setOrderStatusFlag("2");
            l_bankCashTransferStatusParams.setStartStatusFlag("2");
            l_bankCashTransferStatusParams.setResultStatusFlag("2");
            TestDBUtility.insertWithDel(l_bankCashTransferStatusParams);
            
            TestDBUtility.deleteAll(HostCashTransOrderAcceptRow.TYPE);
            HostCashTransOrderAcceptParams l_hostCashTransOrderAcceptParams = this.getHostCashTransOrderAcceptRow();
            TestDBUtility.insertWithDel(l_hostCashTransOrderAcceptParams);
            
            WEB3GentradeSubAccount l_subAccount = new WEB3GentradeSubAccount(333812512203L, 33381251220301L);
            
            String l_strOrderRequestNumber = "123456";
            OrderStatusEnum l_orderStatus = OrderStatusEnum.CANCELLED;
            String l_strOrderCancleStatus = "3";
            
            String l_str = 
                l_mpl.getCashTransSituation(
                    l_subAccount,
                    l_strOrderRequestNumber,
                    l_orderStatus,
                    l_strOrderCancleStatus);

            assertEquals("I", l_str);
        }
        catch (Exception l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testGetCashTransSituation1()
    {
        final String STR_METHOD_NAME = "testGetCashTransSituation1()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            TestDBUtility.insertWithDel(l_subAccountParams);
            
            TestDBUtility.deleteAll(BankCashTransferStatusRow.TYPE);
            BankCashTransferStatusParams l_bankCashTransferStatusParams = this.getBankCashTransferStatusRow();
            l_bankCashTransferStatusParams.setTransactionStatus("1");
            l_bankCashTransferStatusParams.setOrderStatusFlag("2");
            l_bankCashTransferStatusParams.setStartStatusFlag("2");
            l_bankCashTransferStatusParams.setResultStatusFlag("2");
            TestDBUtility.insertWithDel(l_bankCashTransferStatusParams);
            
            TestDBUtility.deleteAll(HostCashTransOrderAcceptRow.TYPE);
            HostCashTransOrderAcceptParams l_hostCashTransOrderAcceptParams = this.getHostCashTransOrderAcceptRow();
            TestDBUtility.insertWithDel(l_hostCashTransOrderAcceptParams);
            
            WEB3GentradeSubAccount l_subAccount = new WEB3GentradeSubAccount(333812512203L, 33381251220301L);
            
            String l_strOrderRequestNumber = "123456";
            OrderStatusEnum l_orderStatus = OrderStatusEnum.ORDERED;
            String l_strOrderCancleStatus = "0";
            
            String l_str = 
                l_mpl.getCashTransSituation(
                    l_subAccount,
                    l_strOrderRequestNumber,
                    l_orderStatus,
                    l_strOrderCancleStatus);
            assertEquals("E", l_str);
        }
        catch (Exception l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    */
    
//    public void testValidateReceiptTelegram()
//    {
//        final String STR_METHOD_NAME = "testValidateReceiptTelegram()";
//        log.entering(STR_METHOD_NAME);
//        
//        try
//        {
//            TestDBUtility.deleteAll(BankCashTransferStatusParams.TYPE);
//            BankCashTransferStatusParams l_bankCashTransferStatusParams = this.getBankCashTransferStatusRow();
//            TestDBUtility.insertWithDel(l_bankCashTransferStatusParams);
//            
//            TestDBUtility.deleteAll(LoginSessionRow.TYPE);
//            LoginSessionParams l_loginSessionParams = this.getLoginSessionRow();
//            TestDBUtility.insertWithDel(l_loginSessionParams);
//
//            TestDBUtility.deleteAll(BankOrderRequestRow.TYPE);
//            BankOrderRequestParams l_bankOrderRequestParams = this.getBankOrderRequestRow();
//            TestDBUtility.insertWithDel(l_bankOrderRequestParams);
//            
//            WEB3AioMultiBankSettleControlServiceImplForTest l_impl =
//                new WEB3AioMultiBankSettleControlServiceImplForTest();
//            
//            HashMap l_receiptTelegramData = new HashMap();
//            l_receiptTelegramData.put("apsid", "123");
//            l_receiptTelegramData.put("linked_1", "123");
//            l_receiptTelegramData.put("centerPayId", "1254");
//            WEB3AioFinInstitutionCashTransStatus l_cashTransStatus =
//                new WEB3AioFinInstitutionCashTransStatus("0D", "381", "123456");
//            
//            String l_strTelegram = l_impl.validateReceiptTelegram(l_receiptTelegramData, l_cashTransStatus);
//            assertEquals("ERROR", l_strTelegram);
//        }
//        catch (Exception l_ex)
//        {
//            log.debug(l_ex.getMessage(), l_ex);
//            fail();
//        }
//        
//        log.exiting(STR_METHOD_NAME);
//    }
    
   /**
    * 注文情報要求テーブルの同じ内容の電文があった場合は、"ERROR"を返す。
    */
    public void testValidateReceiptTelegram_C0001()
  {
      final String STR_METHOD_NAME = "testValidateReceiptTelegram_C0001()";
      log.entering(STR_METHOD_NAME);
      
      try
      {
          TestDBUtility.deleteAll(BankCashTransferStatusParams.TYPE);
          BankCashTransferStatusParams l_bankCashTransferStatusParams = this.getBankCashTransferStatusRow();
          TestDBUtility.insertWithDel(l_bankCashTransferStatusParams);

          TestDBUtility.deleteAll(BankOrderRequestRow.TYPE);
          BankOrderRequestParams l_bankOrderRequestParams = this.getBankOrderRequestRow();
          TestDBUtility.insertWithDel(l_bankOrderRequestParams);
          
          TestDBUtility.deleteAll(LoginTypeRow.TYPE);
          LoginTypeParams l_loginTypeParams = this.getLoginTypeRow();
          TestDBUtility.insertWithDel(l_loginTypeParams);
          
          TestDBUtility.deleteAll(LoginRow.TYPE);
          LoginParams l_loginParams = this.getLoginRow();
          TestDBUtility.insertWithDel(l_loginParams);
          
          TestDBUtility.deleteAll(LoginSessionRow.TYPE);
          LoginSessionParams l_loginSession = this.getLoginSessionRow();
          TestDBUtility.insertWithDel(l_loginSession);
          
          WEB3AioMultiBankSettleControlServiceImplForTest l_impl =
              new WEB3AioMultiBankSettleControlServiceImplForTest();
          
          HashMap l_receiptTelegramData = new HashMap();
          l_receiptTelegramData.put("apsid", "123");
          l_receiptTelegramData.put("linked_1", "123");
          l_receiptTelegramData.put("centerPayId", "1254");
          l_receiptTelegramData.put("web3Request","OrderDemand");
          WEB3AioFinInstitutionCashTransStatus l_cashTransStatus =
              new WEB3AioFinInstitutionCashTransStatus("0D", "381", "123456");
          
          String l_strTelegram = l_impl.validateReceiptTelegram(l_receiptTelegramData, l_cashTransStatus);
          assertEquals("ERROR", l_strTelegram);
      }
      catch (Exception l_ex)
      {
          log.debug(l_ex.getMessage(), l_ex);
          fail();
      }
      log.exiting(STR_METHOD_NAME);
  }
    
    /**
     * 決済開始要求テーブルの同じ内容の電文があった場合は、"ERROR"を返す。
     */
     public void testValidateReceiptTelegram_C0002()
   {
       final String STR_METHOD_NAME = "testValidateReceiptTelegram_C0002()";
       log.entering(STR_METHOD_NAME);
       
       try
       {
           TestDBUtility.deleteAll(BankCashTransferStatusParams.TYPE);
           BankCashTransferStatusParams l_bankCashTransferStatusParams = this.getBankCashTransferStatusRow();
           TestDBUtility.insertWithDel(l_bankCashTransferStatusParams);

           TestDBUtility.deleteAll(BankSettleStartRequestParams.TYPE);
           BankSettleStartRequestParams l_bankSettleStartRequestParams = this.getBankSettleStartRequestRow();
           TestDBUtility.insertWithDel(l_bankSettleStartRequestParams);
           
           TestDBUtility.deleteAll(LoginTypeRow.TYPE);
           LoginTypeParams l_loginTypeParams = this.getLoginTypeRow();
           TestDBUtility.insertWithDel(l_loginTypeParams);
           
           TestDBUtility.deleteAll(LoginRow.TYPE);
           LoginParams l_loginParams = this.getLoginRow();
           TestDBUtility.insertWithDel(l_loginParams);
           
           TestDBUtility.deleteAll(LoginSessionRow.TYPE);
           LoginSessionParams l_loginSession = this.getLoginSessionRow();
           TestDBUtility.insertWithDel(l_loginSession);
           
           WEB3AioMultiBankSettleControlServiceImplForTest l_impl =
               new WEB3AioMultiBankSettleControlServiceImplForTest();
           
           HashMap l_receiptTelegramData = new HashMap();
           l_receiptTelegramData.put("apsid", "123");
           l_receiptTelegramData.put("linked_1", "1234");
           l_receiptTelegramData.put("centerPayId", "5678");
           l_receiptTelegramData.put("web3Request","SettleStart");
           WEB3AioFinInstitutionCashTransStatus l_cashTransStatus =
               new WEB3AioFinInstitutionCashTransStatus("0D", "381", "123456");
           
           String l_strTelegram = l_impl.validateReceiptTelegram(l_receiptTelegramData, l_cashTransStatus);
           assertEquals("ERROR", l_strTelegram);
       }
       catch (Exception l_ex)
       {
           log.debug(l_ex.getMessage(), l_ex);
           fail();
       }
       log.exiting(STR_METHOD_NAME);
   }
     
     /**
      * 決済結果通知テーブルの同じ内容の電文があった場合は、"ERROR"を返す。
      */
      public void testValidateReceiptTelegram_C0003()
    {
        final String STR_METHOD_NAME = "testValidateReceiptTelegram_C0003()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            TestDBUtility.deleteAll(BankCashTransferStatusParams.TYPE);
            BankCashTransferStatusParams l_bankCashTransferStatusParams = this.getBankCashTransferStatusRow();
            TestDBUtility.insertWithDel(l_bankCashTransferStatusParams);

            TestDBUtility.deleteAll(BankSettleResultResponseParams.TYPE);
            BankSettleResultResponseParams l_bankSettleResultResponseParams = this.getBankSettleResultResponseRow();
            TestDBUtility.insertWithDel(l_bankSettleResultResponseParams);
            
            TestDBUtility.deleteAll(LoginTypeRow.TYPE);
            LoginTypeParams l_loginTypeParams = this.getLoginTypeRow();
            TestDBUtility.insertWithDel(l_loginTypeParams);
            
            TestDBUtility.deleteAll(LoginRow.TYPE);
            LoginParams l_loginParams = this.getLoginRow();
            TestDBUtility.insertWithDel(l_loginParams);
            
            TestDBUtility.deleteAll(LoginSessionRow.TYPE);
            LoginSessionParams l_loginSession = this.getLoginSessionRow();
            TestDBUtility.insertWithDel(l_loginSession);
            
            WEB3AioMultiBankSettleControlServiceImplForTest l_impl =
                new WEB3AioMultiBankSettleControlServiceImplForTest();
            
            HashMap l_receiptTelegramData = new HashMap();
            l_receiptTelegramData.put("apsid", "123");
            l_receiptTelegramData.put("linked_1", "1234");
            l_receiptTelegramData.put("centerPayId", "5678");
            l_receiptTelegramData.put("web3Request","SettleResult");
            WEB3AioFinInstitutionCashTransStatus l_cashTransStatus =
                new WEB3AioFinInstitutionCashTransStatus("0D", "381", "123456");
            
            String l_strTelegram = l_impl.validateReceiptTelegram(l_receiptTelegramData, l_cashTransStatus);
            assertEquals("ERROR", l_strTelegram);
        }
        catch (Exception l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * 金融機@関連携入出金状況Row
     * @@return
     */
    public BankCashTransferStatusParams getBankCashTransferStatusRow()
    {
        BankCashTransferStatusParams l_bankCashTransferStatusParams = new BankCashTransferStatusParams();
        
        l_bankCashTransferStatusParams.setInstitutionCode("0D");
        l_bankCashTransferStatusParams.setBranchCode("381");
        l_bankCashTransferStatusParams.setAccountCode("2512246");
        l_bankCashTransferStatusParams.setPaySchemeId("123456789");
        l_bankCashTransferStatusParams.setOrderStatusFlag("0");
        l_bankCashTransferStatusParams.setOrderRequestNumber("123456");
        l_bankCashTransferStatusParams.setStartStatusFlag("1");
        l_bankCashTransferStatusParams.setResultStatusFlag("1");
        l_bankCashTransferStatusParams.setTransactionStatus("1");
        l_bankCashTransferStatusParams.setLastUpdateTimestamp(WEB3DateUtility.getDate("20070604", "yyyyMMdd"));
        return l_bankCashTransferStatusParams;
    }
    
    /**
     * 入出金伝票受付キューテーブル
     */
    public HostCashTransOrderAcceptParams getHostCashTransOrderAcceptRow()
    {
        HostCashTransOrderAcceptParams l_hostCashTransOrderAcceptParams = new HostCashTransOrderAcceptParams();
        
        l_hostCashTransOrderAcceptParams.setRequestCode("GI80C");
        l_hostCashTransOrderAcceptParams.setInstitutionCode("0D");
        l_hostCashTransOrderAcceptParams.setBranchCode("381");
        l_hostCashTransOrderAcceptParams.setAccountCode("2512246");
        l_hostCashTransOrderAcceptParams.setOrderRequestNumber("123456");
        l_hostCashTransOrderAcceptParams.setAcceptDiv("1");
        l_hostCashTransOrderAcceptParams.setStatus("0");
        
        return l_hostCashTransOrderAcceptParams;
    }
    
    /**
     * LoginSessionテーブル
     */
    public LoginSessionParams getLoginSessionRow()
    {
        LoginSessionParams l_loginSessionParams = new LoginSessionParams();
        
        l_loginSessionParams.setCreationDate(Calendar.getInstance().getTime());
        l_loginSessionParams.setCurrentAccountId(123L);
        l_loginSessionParams.setExpirationDate(WEB3DateUtility.getDate("99901010", "yyyyMMdd"));
        l_loginSessionParams.setLastUpdate(Calendar.getInstance().getTime());
        l_loginSessionParams.setLoginId(123L);
        l_loginSessionParams.setValidity(2);
        l_loginSessionParams.setTypeId(123L);
        l_loginSessionParams.setSessionId(123);
        l_loginSessionParams.setSessionToken("0");
        
        
        return l_loginSessionParams;
    }
    
    public LoginTypeParams getLoginTypeRow()
    {
        LoginTypeParams l_loginTypeParams = new LoginTypeParams();
        l_loginTypeParams.setTypeId(123L);
        l_loginTypeParams.setTypeName("typeName");
        return l_loginTypeParams;
    }
    
    public LoginParams getLoginRow()
    {
        LoginParams l_loginParams = new LoginParams();
        l_loginParams.setLoginId(123L);
        l_loginParams.setTypeId(123L);
        l_loginParams.setHashedPassword("@@#$%");
        l_loginParams.setInitialPassword("^&*");
        l_loginParams.setDisabled(1);
        l_loginParams.setAffinityKey(987);
        l_loginParams.setFailureCount(5);
        l_loginParams.setLastFailureTimestamp(Calendar.getInstance().getTime());
        return l_loginParams;
    }
    
    /**
     * 注文情報要求テーブル
     */
    public BankOrderRequestParams getBankOrderRequestRow()
    {
        BankOrderRequestParams l_bankOrderRequestParams = new BankOrderRequestParams();
        
        l_bankOrderRequestParams.setShopId("12345");
        l_bankOrderRequestParams.setAccessKey("2");
        l_bankOrderRequestParams.setCenterPayId("1254");
        l_bankOrderRequestParams.setCreatedTimestamp("20070612");
        l_bankOrderRequestParams.setLinked1("123");
        l_bankOrderRequestParams.setOrderDateTime("20070612");
        l_bankOrderRequestParams.setPaySchemeId("1254");
        l_bankOrderRequestParams.setProtocolVersion("1.2");
        
        return l_bankOrderRequestParams;
    }
    
    public BankSettleStartRequestParams getBankSettleStartRequestRow()
    {
        BankSettleStartRequestParams l_bankSettleStartRequestParams = new BankSettleStartRequestParams();
        l_bankSettleStartRequestParams.setCreatedTimestamp("20070731154722");
        l_bankSettleStartRequestParams.setProtocolVersion("v1.2");
        l_bankSettleStartRequestParams.setLinked1("1234");
        l_bankSettleStartRequestParams.setShopId("8512");
        l_bankSettleStartRequestParams.setOrderDateTime("20070731");
        l_bankSettleStartRequestParams.setCenterPayId("5678");
        l_bankSettleStartRequestParams.setPaySchemeId("777");
        l_bankSettleStartRequestParams.setAccessKey("8888");
        l_bankSettleStartRequestParams.setPayStatus("Start");
        
        return l_bankSettleStartRequestParams;
    }
    
    public BankSettleResultResponseParams getBankSettleResultResponseRow()
    {
        BankSettleResultResponseParams l_bankSettleResultResponseParams = new BankSettleResultResponseParams();
        l_bankSettleResultResponseParams.setCreatedTimestamp("20070731154727");
        l_bankSettleResultResponseParams.setProtocolVersion("v1.2");
        l_bankSettleResultResponseParams.setLinked1("1234");
        l_bankSettleResultResponseParams.setShopId("8512");
        l_bankSettleResultResponseParams.setOrderDateTime("20070731");
        l_bankSettleResultResponseParams.setCenterPayId("5678");
        l_bankSettleResultResponseParams.setPayStatus("OK");
        l_bankSettleResultResponseParams.setPaySchemeId("777");
        l_bankSettleResultResponseParams.setAccessKey("8888");
        return l_bankSettleResultResponseParams;
    }
    
    public class WEB3AioMultiBankSettleControlServiceImplForTest extends WEB3AioMultiBankSettleControlServiceImpl
    {
        public String getPreference(
                String l_strInstitutionCode,
                String l_strName)
                throws WEB3BaseException
        {
            return "1000000000000";
        }
    }
}
@
