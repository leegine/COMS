head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.01.37.12;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3AioCashinCooperationNotifyUnitServiceImplTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : (WEB3AioCashinCooperationNotifyUnitServiceImplTest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2007/03/13 何文敏(中訊) 新規作成
Revision History : 2007/07/17 張騰宇 (中訊) モデルNo.734
*/
package webbroker3.aio.service.delegate.stdimpls;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.plugin.security.oplogin.impl.LoginInfoImplForMock;
import com.fitechlabs.xtrade.plugin.tc.gentrade.AccountManager;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.MainAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProcessingResult;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccountTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Trader;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.BranchParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.BranchRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.InstitutionParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.InstitutionRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.ProductParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.ProductRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.SubAccountParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.SubAccountRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.NewOrderSpec;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.OrderSubmissionResult;

import test.util.TestDBUtility;

import webbroker3.aio.data.BankCashTransferStatusParams;
import webbroker3.aio.data.BankDepositErrorHistoryParams;
import webbroker3.aio.data.BankDepositErrorHistoryRow;
import webbroker3.aio.data.BankDepositNotifyParams;
import webbroker3.aio.data.BankDepositNotifyRow;
import webbroker3.aio.data.CareerShopIdParams;
import webbroker3.aio.data.CompBankConditionParams;
import webbroker3.aio.data.CompBankConditionRow;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3OrderStatusFlagDef;
import webbroker3.common.define.WEB3StartStatusFlgDef;
import webbroker3.common.define.WEB3TransactionStatusDef;
import webbroker3.gentrade.WEB3GentradeAccountManager;
import webbroker3.gentrade.WEB3GentradeMainAccount;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagementForMock;
import webbroker3.gentrade.data.BranchPreferencesParams;
import webbroker3.gentrade.data.BranchPreferencesRow;
import webbroker3.gentrade.data.EraParams;
import webbroker3.gentrade.data.GenCurrencyParams;
import webbroker3.gentrade.data.GenCurrencyRow;
import webbroker3.gentrade.data.TradingpowerCalcConditionParams;
import webbroker3.gentrade.data.TradingpowerCalcConditionRow;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3LogUtility;

public class WEB3AioCashinCooperationNotifyUnitServiceImplTest extends TestBaseForMock
{
    private static WEB3LogUtility log = 
        WEB3LogUtility.getInstance(WEB3AioCashinCooperationNotifyUnitServiceImplTest.class);
    
    WEB3AioCashinCooperationNotifyUnitServiceImplForTest l_mpl =
        new WEB3AioCashinCooperationNotifyUnitServiceImplForTest();
    
    public WEB3AioCashinCooperationNotifyUnitServiceImplTest(String arg0)
    {
        super(arg0);
    }

    protected void setUp() throws Exception
    {
        super.setUp();
        MOCK_MANAGER.setIsMockUsed(true);
        TestDBUtility.deleteAll(EraParams.TYPE);
        EraParams l_eraParams = this.getEraParams();
        l_eraParams.setJapaneseEraDiv(1);
        TestDBUtility.insertWithDel(l_eraParams);
    }

    protected void tearDown() throws Exception
    {
        super.tearDown();
    }
    public void testIsNormalLength1()
    {
        final String STR_METHOD_NAME = "testIsNormalLength1()";
        log.entering(STR_METHOD_NAME);
        
        String l_strDepositDataDepositAmount = "100000000000";
        double l_dbSellExecRate = 123542341.123456;
        try
        {
            boolean l_blnResult = 
                l_mpl.isNormalLength(l_strDepositDataDepositAmount, l_dbSellExecRate);
            log.debug("*************l_blnResult = " + l_blnResult);
            assertFalse(l_blnResult);
            
        }
        catch (Exception l_ex)
        {
            log.error("Error in Exception", l_ex);
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
        
    }
    
    public void testIsNormalLength2()
    {
        final String STR_METHOD_NAME = "testIsNormalLength2()";
        log.entering(STR_METHOD_NAME);
        
        String l_strDepositDataDepositAmount = "100000";
        double l_dbSellExecRate = 123;
        try
        {
            boolean l_blnResult = 
                l_mpl.isNormalLength(l_strDepositDataDepositAmount, l_dbSellExecRate);
            log.debug("*************l_blnResult = " + l_blnResult);
            assertTrue(l_blnResult);
            
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
        
    }

    public void testIsNormalLength3()
    {
        final String STR_METHOD_NAME = "testIsNormalLength3()";
        log.entering(STR_METHOD_NAME);
        
        String l_strDepositDataDepositAmount = "155555.545454";
        double l_dbSellExecRate = 100000000000D;
        try
        {
            boolean l_blnResult = 
                l_mpl.isNormalLength(l_strDepositDataDepositAmount, l_dbSellExecRate);
            log.debug("*************l_blnResult = " + l_blnResult);
            assertFalse(l_blnResult);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
        
    }

    public void testIsNormalLength4()
    {
        final String STR_METHOD_NAME = "testIsNormalLength4()";
        log.entering(STR_METHOD_NAME);
        
        String l_strDepositDataDepositAmount = "1234567890";
        double l_dbSellExecRate = 100D;
        try
        {
            boolean l_blnResult = 
                l_mpl.isNormalLength(l_strDepositDataDepositAmount, l_dbSellExecRate);
            log.debug("*************l_blnResult = " + l_blnResult);
            assertTrue(l_blnResult);
            
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
        
    }

    public void testIsNormalLength5()
    {
        final String STR_METHOD_NAME = "testIsNormalLength5()";
        log.entering(STR_METHOD_NAME);
        
        String l_strDepositDataDepositAmount = "1234567890";
        double l_dbSellExecRate = 1000D;
        try
        {
            boolean l_blnResult = 
                l_mpl.isNormalLength(l_strDepositDataDepositAmount, l_dbSellExecRate);
            log.debug("*************l_blnResult = " + l_blnResult);
            assertFalse(l_blnResult);
            
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
        
    }

    public void testnotifyCashinCooperation_case001()
    {
        final String STR_METHOD_NAME = "testnotifyCashinCooperation_case001()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            WEB3AioCashinCooperationNotifyUnitServiceImpl l_serviceImpl =
                new WEB3AioCashinCooperationNotifyUnitServiceImpl();

            TestDBUtility.deleteAll(BankDepositNotifyRow.TYPE);
            BankDepositNotifyParams l_bankDepositNotifyParams = TestDBUtility.getBankDepositNotifyRow();
            l_bankDepositNotifyParams.setAccountCode("123456");
            l_bankDepositNotifyParams.setBranchCode("624");
            l_bankDepositNotifyParams.setCurrencyCode("111");
            l_bankDepositNotifyParams.setBankCode("111");
            l_bankDepositNotifyParams.setBankDepositNotifyId(123);
            l_bankDepositNotifyParams.setDataLoadDiv("1");
            l_bankDepositNotifyParams.setInstitutionCode("123");
            l_bankDepositNotifyParams.setDepositDataDepositAmount("12345");
            l_bankDepositNotifyParams.setLastErrorHistorySerialNo(20);
            l_bankDepositNotifyParams.setStatus("1");
            l_bankDepositNotifyParams.setDepositErrorComment("jiddk");
            TestDBUtility.insertWithDel(l_bankDepositNotifyParams);
            
            TestDBUtility.deleteAll(GenCurrencyRow.TYPE);
            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
            WEB3GentradeAccountManager l_accountManager = 
                (WEB3GentradeAccountManager)l_finApp.getAccountManager();
        
            long l_lngAccountId = 123465L;
            MainAccountParams l_mainAccountparams = TestDBUtility.getMainAccountRow();
            l_mainAccountparams.setAccountId(l_lngAccountId);
            TestDBUtility.insertWithDel(l_mainAccountparams);
            
            BranchParams l_branchparams = TestDBUtility.getBranchRow();
            l_branchparams.setBranchCode("624");
            TestDBUtility.insertWithDel(l_branchparams);
            
            GenCurrencyParams l_params = TestDBUtility.getGenCurrencyRow();
            l_params.setInstitutionCode("123");
            l_params.setCurrencyCode("111");
            l_params.setCurrentSellExecRate(123D);
            TestDBUtility.insertWithDel(l_params);
            
            TestDBUtility.deleteAll(EraParams.TYPE);
            TestDBUtility.deleteAll(BankDepositErrorHistoryParams.TYPE);
            WEB3GentradeMainAccount l_mainAccount = (WEB3GentradeMainAccount)l_accountManager.getMainAccount(l_lngAccountId);

            l_serviceImpl.notifyCashinCooperation(l_bankDepositNotifyParams, l_mainAccount);
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            List l_resultList = l_queryProcessor.doFindAllQuery(BankDepositNotifyParams.TYPE);
            assertEquals(1, l_resultList.size());
            BankDepositNotifyRow l_notifyRow = (BankDepositNotifyRow)l_resultList.get(0);
            assertEquals("9", l_notifyRow.getStatus());
            assertEquals("入金起算日エラー", l_notifyRow.getDepositErrorComment());
            assertEquals(20, l_notifyRow.getLastErrorHistorySerialNo());

            l_resultList = l_queryProcessor.doFindAllQuery(BankDepositErrorHistoryParams.TYPE);
            assertEquals(1, l_resultList.size());
            BankDepositErrorHistoryRow l_erorHistoryRow = (BankDepositErrorHistoryRow)l_resultList.get(0);
            assertEquals("入金起算日エラー", l_erorHistoryRow.getDepositErrorComment());
            assertEquals(20, l_erorHistoryRow.getSerialNo());
            
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }

    public void testnotifyCashinCooperation_case002()
    {
        final String STR_METHOD_NAME = "testnotifyCashinCooperation_case002()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            WEB3AioCashinCooperationNotifyUnitServiceImpl l_serviceImpl =
                new WEB3AioCashinCooperationNotifyUnitServiceImpl();

            TestDBUtility.deleteAll(BankDepositNotifyRow.TYPE);
            BankDepositNotifyParams l_bankDepositNotifyParams = TestDBUtility.getBankDepositNotifyRow();
            l_bankDepositNotifyParams.setAccountCode("123456");
            l_bankDepositNotifyParams.setBranchCode("624");
            l_bankDepositNotifyParams.setCurrencyCode("111");
            l_bankDepositNotifyParams.setBankCode("111");
            l_bankDepositNotifyParams.setBankDepositNotifyId(123);
            l_bankDepositNotifyParams.setDataLoadDiv("1");
            l_bankDepositNotifyParams.setInstitutionCode("123");
            l_bankDepositNotifyParams.setDepositDataDepositAmount("12345");
            l_bankDepositNotifyParams.setLastErrorHistorySerialNo(20);
            l_bankDepositNotifyParams.setStatus("1");
            l_bankDepositNotifyParams.setDepositErrorComment("jiddk");
            l_bankDepositNotifyParams.setDepositDataBaseDate("010107");
            l_bankDepositNotifyParams.setDepositDataAccountDate("123");
            TestDBUtility.insertWithDel(l_bankDepositNotifyParams);
            
            TestDBUtility.deleteAll(GenCurrencyRow.TYPE);
            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
            WEB3GentradeAccountManager l_accountManager = 
                (WEB3GentradeAccountManager)l_finApp.getAccountManager();
        
            long l_lngAccountId = 123465L;
            MainAccountParams l_mainAccountparams = TestDBUtility.getMainAccountRow();
            l_mainAccountparams.setAccountId(l_lngAccountId);
            TestDBUtility.insertWithDel(l_mainAccountparams);
            
            BranchParams l_branchparams = TestDBUtility.getBranchRow();
            l_branchparams.setBranchCode("624");
            TestDBUtility.insertWithDel(l_branchparams);
            
            GenCurrencyParams l_params = TestDBUtility.getGenCurrencyRow();
            l_params.setInstitutionCode("123");
            l_params.setCurrencyCode("111");
            l_params.setCurrentSellExecRate(123D);
            TestDBUtility.insertWithDel(l_params);

            TestDBUtility.deleteAll(BankDepositErrorHistoryParams.TYPE);
            WEB3GentradeMainAccount l_mainAccount = (WEB3GentradeMainAccount)l_accountManager.getMainAccount(l_lngAccountId);

            l_serviceImpl.notifyCashinCooperation(l_bankDepositNotifyParams, l_mainAccount);

            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            List l_resultList = l_queryProcessor.doFindAllQuery(BankDepositNotifyParams.TYPE);
            assertEquals(1, l_resultList.size());
            BankDepositNotifyRow l_notifyRow = (BankDepositNotifyRow)l_resultList.get(0);
            assertEquals("9", l_notifyRow.getStatus());
            assertEquals("勘定日エラー", l_notifyRow.getDepositErrorComment());
            assertEquals(20, l_notifyRow.getLastErrorHistorySerialNo());

            l_resultList = l_queryProcessor.doFindAllQuery(BankDepositErrorHistoryParams.TYPE);
            assertEquals(1, l_resultList.size());
            BankDepositErrorHistoryRow l_erorHistoryRow = (BankDepositErrorHistoryRow)l_resultList.get(0);
            assertEquals("勘定日エラー", l_erorHistoryRow.getDepositErrorComment());
            assertEquals(20, l_erorHistoryRow.getSerialNo());
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testnotifyCashinCooperation_case003()
    {
        final String STR_METHOD_NAME = "testnotifyCashinCooperation_case003()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            //mock
            MOCK_MANAGER.setIsMockUsed(true);
            OrderSubmissionResult l_result =
                new OrderSubmissionResult(ProcessingResult.newSuccessResultInstance());
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.aio.WEB3AioOrderManager",
                "submitNewOrder",
                new Class[] {SubAccount.class, 
                ProductTypeEnum.class, 
                NewOrderSpec.class, long.class, 
                String.class, boolean.class},
                l_result);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.tradingpower.WEB3TPTradingPowerServiceImpl",
                "reCalcTradingPower",
                new Class[] {WEB3GentradeSubAccount.class},
                null);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getLoginInfo",
                new Class[] {},
                new LoginInfoImplForMock());
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.gentrade.WEB3HostReqOrderNumberManageServiceImpl",
                "getNewNumber",
                new Class[] {String.class, String.class, ProductTypeEnum.class },
                "111");

            WEB3AioCashinCooperationNotifyUnitServiceImpl l_serviceImpl =
                new WEB3AioCashinCooperationNotifyUnitServiceImpl();

            TestDBUtility.deleteAll(BankDepositNotifyRow.TYPE);
            BankDepositNotifyParams l_bankDepositNotifyParams = TestDBUtility.getBankDepositNotifyRow();
            l_bankDepositNotifyParams.setAccountCode(null);
            l_bankDepositNotifyParams.setBranchCode("624");
            l_bankDepositNotifyParams.setCurrencyCode("111");
            l_bankDepositNotifyParams.setBankCode("111");
            l_bankDepositNotifyParams.setBankDepositNotifyId(123);
            l_bankDepositNotifyParams.setDataLoadDiv("1");
            l_bankDepositNotifyParams.setInstitutionCode("123");
            l_bankDepositNotifyParams.setDepositDataDepositAmount("12345");
            l_bankDepositNotifyParams.setLastErrorHistorySerialNo(20);
            l_bankDepositNotifyParams.setStatus("2");
            l_bankDepositNotifyParams.setDepositErrorComment("jiddk");
            l_bankDepositNotifyParams.setDepositDataBaseDate("010107");
            l_bankDepositNotifyParams.setDepositDataAccountDate("010107");
            l_bankDepositNotifyParams.setDepositDataTransPersonName("jiddk");
            TestDBUtility.insertWithDel(l_bankDepositNotifyParams);
            
            TestDBUtility.deleteAll(GenCurrencyRow.TYPE);
            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
            WEB3GentradeAccountManager l_accountManager = 
                (WEB3GentradeAccountManager)l_finApp.getAccountManager();
        
            long l_lngAccountId = 123465L;
            MainAccountParams l_mainAccountparams = TestDBUtility.getMainAccountRow();
            l_mainAccountparams.setAccountId(l_lngAccountId);
            l_mainAccountparams.setFamilyNameAlt1("jiddk");
            TestDBUtility.insertWithDel(l_mainAccountparams);

            SubAccountParams l_subAccountParams1 = TestDBUtility.getSubAccountRow();
            l_subAccountParams1.setAccountId(l_mainAccountparams.getAccountId());
            l_subAccountParams1.setSubAccountId(33381251220301L);
            l_subAccountParams1.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
            SubAccountParams l_subAccountParams2 = TestDBUtility.getSubAccountRow();
            l_subAccountParams2.setAccountId(333812512246L);
            l_subAccountParams2.setSubAccountId(33381251220302L);
            l_subAccountParams2.setSubAccountType(SubAccountTypeEnum.EQUITY_MARGIN_SUB_ACCOUNT);
            
            TestDBUtility.insertWithDel(l_subAccountParams1);
            TestDBUtility.insertWithDel(l_subAccountParams2);
            
            BranchParams l_branchparams = TestDBUtility.getBranchRow();
            l_branchparams.setBranchCode("624");
            TestDBUtility.insertWithDel(l_branchparams);
            
            GenCurrencyParams l_params = TestDBUtility.getGenCurrencyRow();
            l_params.setInstitutionCode("123");
            l_params.setCurrencyCode("111");
            l_params.setCurrentSellExecRate(123D);
            TestDBUtility.insertWithDel(l_params);

            TestDBUtility.deleteAll(BankDepositErrorHistoryParams.TYPE);
            WEB3GentradeMainAccount l_mainAccount = (WEB3GentradeMainAccount)l_accountManager.getMainAccount(l_lngAccountId);

            l_serviceImpl.notifyCashinCooperation(l_bankDepositNotifyParams, l_mainAccount);

            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            List l_resultList = l_queryProcessor.doFindAllQuery(BankDepositNotifyParams.TYPE);
            assertEquals(1, l_resultList.size());
            BankDepositNotifyRow l_notifyRow = (BankDepositNotifyRow)l_resultList.get(0);
            assertEquals("1", l_notifyRow.getStatus());
            assertEquals(20, l_notifyRow.getLastErrorHistorySerialNo());

            l_resultList = l_queryProcessor.doFindAllQuery(BankDepositErrorHistoryParams.TYPE);
            assertEquals(0, l_resultList.size());
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }

    /**
     * 
     * @@author zhang-tengyu
     *
     */
    public void testSubmitOrderCase1()
    {
        final String STR_METHOD_NAME = " testSubmitOrderCase1()";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {
            //mock
            MOCK_MANAGER.setIsMockUsed(true);
            OrderSubmissionResult l_result =
                new OrderSubmissionResult(ProcessingResult.newSuccessResultInstance());
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.aio.WEB3AioOrderManager",
                    "submitNewOrder",
                    new Class[] {SubAccount.class, 
                    ProductTypeEnum.class, 
                    NewOrderSpec.class, long.class, 
                    String.class, boolean.class},
                    l_result);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.tradingpower.WEB3TPTradingPowerServiceImpl",
                    "reCalcTradingPower",
                    new Class[] {WEB3GentradeSubAccount.class},
                    null);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                    "getLoginInfo",
                    new Class[] {},
                    new LoginInfoImplForMock());
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.gentrade.WEB3HostReqOrderNumberManageServiceImpl",
                    "getNewNumber",
                    new Class[] {String.class, String.class, ProductTypeEnum.class },
                    "111");
                    
            Calendar ca1 =  Calendar.getInstance();
            ca1.set(2008,6-1,19);
            
            Date date1 = ca1.getTime();
            WEB3GentradeTradingTimeManagementForMock.mockGetOrderBizDate(date1);
            
            //data
            
            TestDBUtility.deleteAll(BankCashTransferStatusParams.TYPE);
            BankCashTransferStatusParams l_bankCashTransferStatusParams =
                WEB3AioMultiBankTelegramProcessServiceImplTest.getBankCashTransferStatusRow();
            l_bankCashTransferStatusParams.setTransactionStatus(WEB3TransactionStatusDef.OK);
            l_bankCashTransferStatusParams.setOrderStatusFlag(WEB3OrderStatusFlagDef.REPUEST_RECEIPT);
            l_bankCashTransferStatusParams.setStartStatusFlag(WEB3StartStatusFlgDef.REPUEST_RECEIPT);
            TestDBUtility.insertWithDel(l_bankCashTransferStatusParams);

            TestDBUtility.deleteAll(CareerShopIdParams.TYPE);
            CareerShopIdParams l_careerShopIdParams =
                WEB3AioMultiBankTelegramProcessServiceImplTest.getCareerShopIdRow();
            l_careerShopIdParams.setBranchCode("381");
            l_careerShopIdParams.setInstitutionCode("0D");
            l_careerShopIdParams.setCareerDiv("2");
            l_careerShopIdParams.setShopId("12345678");
            l_careerShopIdParams.setPfUrl("111111");
            l_careerShopIdParams.setReturnUrl("222222");
            TestDBUtility.insertWithDel(l_careerShopIdParams);
            
            TestDBUtility.deleteAll(CompBankConditionRow.TYPE);
            CompBankConditionParams l_CompBankConditionParams = new CompBankConditionParams();
            l_CompBankConditionParams.setInstitutionCode("0D");
            l_CompBankConditionParams.setBranchCode("381");
            l_CompBankConditionParams.setPaySchemeId("6");
            l_CompBankConditionParams.setShopId("111");
            TestDBUtility.insertWithDel(l_CompBankConditionParams);
            
            TestDBUtility.deleteAll(BranchRow.TYPE);
            TestDBUtility.deleteAll(InstitutionRow.TYPE);
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            TestDBUtility.deleteAll(ProductRow.TYPE);
            BranchParams l_BranchParams = TestDBUtility.getBranchRow();
            l_BranchParams.setBranchCode("381");
            InstitutionParams l_InstitutionParams = TestDBUtility.getInstitutionRow();
            l_InstitutionParams.setInstitutionCode("0D");
            MainAccountParams l_MainAccountParams = TestDBUtility.getMainAccountRow();
            l_MainAccountParams.setInstitutionCode("0D");
            l_MainAccountParams.setBranchCode("381");
            l_MainAccountParams.setAccountCode("2512246");
            l_MainAccountParams.setAccountId(333812512246L);
            
            SubAccountParams l_subAccountParams1 = TestDBUtility.getSubAccountRow();
            l_subAccountParams1.setAccountId(333812512246L);
            l_subAccountParams1.setSubAccountId(33381251220301L);
            l_subAccountParams1.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
            SubAccountParams l_subAccountParams2 = TestDBUtility.getSubAccountRow();
            l_subAccountParams2.setAccountId(333812512246L);
            l_subAccountParams2.setSubAccountId(33381251220302L);
            l_subAccountParams2.setSubAccountType(SubAccountTypeEnum.EQUITY_MARGIN_SUB_ACCOUNT);
            
            TestDBUtility.insertWithDel(l_subAccountParams1);
            TestDBUtility.insertWithDel(l_subAccountParams2);
            
            ProductParams l_ProductParams = TestDBUtility.getProductRow();
            l_ProductParams.setInstitutionCode("0D");
            l_ProductParams.setProductType(ProductTypeEnum.CASH);
            TestDBUtility.insertWithDel(l_BranchParams);
            TestDBUtility.insertWithDel(l_InstitutionParams);
            TestDBUtility.insertWithDel(l_MainAccountParams);
            TestDBUtility.insertWithDel(l_ProductParams);

            TestDBUtility.deleteAll(BranchPreferencesRow.TYPE);
            BranchPreferencesParams l_BranchPreferencesParams =
                TestDBUtility.getBranchPreferencesRow();
            l_BranchPreferencesParams.setBranchId(33381);
            TestDBUtility.insertWithDel(l_BranchPreferencesParams);
            
            TestDBUtility.deleteAll(TradingpowerCalcConditionRow.TYPE);
            TradingpowerCalcConditionParams l_TradingpowerCalcConditionParams =
                TestDBUtility.getTradingpowerCalcConditionRow();
            l_TradingpowerCalcConditionParams.setAccountId(333812512246L);
            TestDBUtility.insertWithDel(l_TradingpowerCalcConditionParams);
            

            
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        try
        {
            TestDBUtility.deleteAll(BankDepositNotifyRow.TYPE);
            BankDepositNotifyParams l_BankDepositNotifyParams = TestDBUtility.getBankDepositNotifyRow();
            l_BankDepositNotifyParams.setDataLoadDiv("A");
            l_BankDepositNotifyParams.setDepositDataAccountDate("010108");
            TestDBUtility.insertWithDel(l_BankDepositNotifyParams);
            AccountManager l_accMgr = (AccountManager) GtlUtils.getAccountManager();
            MainAccount l_mainAccount = l_accMgr.getMainAccount(333812512246L);
            WEB3AioCashinCooperationNotifyUnitServiceImpl l_impl =
                new WEB3AioCashinCooperationNotifyUnitServiceImpl();
            l_impl.submitOrder(l_BankDepositNotifyParams, (WEB3GentradeMainAccount)l_mainAccount);

        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    
    public void testSubmitOrderCase2()
    {
        final String STR_METHOD_NAME = " testSubmitOrderCase2()";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {
            //mock
            MOCK_MANAGER.setIsMockUsed(true);
            OrderSubmissionResult l_result =
                new OrderSubmissionResult(ProcessingResult.newSuccessResultInstance());
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.aio.WEB3AioOrderManager",
                    "submitNewOrder",
                    new Class[] {SubAccount.class, 
                    ProductTypeEnum.class, 
                    NewOrderSpec.class, long.class, 
                    String.class, boolean.class},
                    l_result);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.tradingpower.WEB3TPTradingPowerServiceImpl",
                    "reCalcTradingPower",
                    new Class[] {WEB3GentradeSubAccount.class},
                    null);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                    "getLoginInfo",
                    new Class[] {},
                    new LoginInfoImplForMock());
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.gentrade.WEB3HostReqOrderNumberManageServiceImpl",
                    "getNewNumber",
                    new Class[] {String.class, String.class, ProductTypeEnum.class },
                    "111");

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.aio.service.delegate.stdimpls.WEB3MarginTransferServiceImpl",
                    "submitMarginTransfer",
                    new Class[]
                    { WEB3GentradeMainAccount.class, Date.class, double.class, String.class , Trader.class},
                    null);

            Calendar ca1 =  Calendar.getInstance();
            ca1.set(2008,6-1,19);
            
            Date date1 = ca1.getTime();
            WEB3GentradeTradingTimeManagementForMock.mockGetOrderBizDate(date1);
            
            //data
            
            TestDBUtility.deleteAll(BankCashTransferStatusParams.TYPE);
            BankCashTransferStatusParams l_bankCashTransferStatusParams =
                WEB3AioMultiBankTelegramProcessServiceImplTest.getBankCashTransferStatusRow();
            l_bankCashTransferStatusParams.setTransactionStatus(WEB3TransactionStatusDef.OK);
            l_bankCashTransferStatusParams.setOrderStatusFlag(WEB3OrderStatusFlagDef.REPUEST_RECEIPT);
            l_bankCashTransferStatusParams.setStartStatusFlag(WEB3StartStatusFlgDef.REPUEST_RECEIPT);
            TestDBUtility.insertWithDel(l_bankCashTransferStatusParams);

            TestDBUtility.deleteAll(CareerShopIdParams.TYPE);
            CareerShopIdParams l_careerShopIdParams =
                WEB3AioMultiBankTelegramProcessServiceImplTest.getCareerShopIdRow();
            l_careerShopIdParams.setBranchCode("381");
            l_careerShopIdParams.setInstitutionCode("0D");
            l_careerShopIdParams.setCareerDiv("2");
            l_careerShopIdParams.setShopId("12345678");
            l_careerShopIdParams.setPfUrl("111111");
            l_careerShopIdParams.setReturnUrl("222222");
            TestDBUtility.insertWithDel(l_careerShopIdParams);
            
            TestDBUtility.deleteAll(CompBankConditionRow.TYPE);
            CompBankConditionParams l_CompBankConditionParams = new CompBankConditionParams();
            l_CompBankConditionParams.setInstitutionCode("0D");
            l_CompBankConditionParams.setBranchCode("381");
            l_CompBankConditionParams.setPaySchemeId("6");
            l_CompBankConditionParams.setShopId("111");
            TestDBUtility.insertWithDel(l_CompBankConditionParams);
            
            TestDBUtility.deleteAll(BranchRow.TYPE);
            TestDBUtility.deleteAll(InstitutionRow.TYPE);
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            TestDBUtility.deleteAll(ProductRow.TYPE);
            BranchParams l_BranchParams = TestDBUtility.getBranchRow();
            l_BranchParams.setBranchCode("381");
            InstitutionParams l_InstitutionParams = TestDBUtility.getInstitutionRow();
            l_InstitutionParams.setInstitutionCode("0D");
            MainAccountParams l_MainAccountParams = TestDBUtility.getMainAccountRow();
            l_MainAccountParams.setInstitutionCode("0D");
            l_MainAccountParams.setBranchCode("381");
            l_MainAccountParams.setAccountCode("2512246");
            l_MainAccountParams.setAccountId(333812512246L);
            l_MainAccountParams.setMarginGenAccOpenDiv("1");
            
            SubAccountParams l_subAccountParams1 = TestDBUtility.getSubAccountRow();
            l_subAccountParams1.setAccountId(333812512246L);
            l_subAccountParams1.setSubAccountId(33381251220301L);
            l_subAccountParams1.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
            SubAccountParams l_subAccountParams2 = TestDBUtility.getSubAccountRow();
            l_subAccountParams2.setAccountId(333812512246L);
            l_subAccountParams2.setSubAccountId(33381251220302L);
            l_subAccountParams2.setSubAccountType(SubAccountTypeEnum.EQUITY_MARGIN_SUB_ACCOUNT);
            
            TestDBUtility.insertWithDel(l_subAccountParams1);
            TestDBUtility.insertWithDel(l_subAccountParams2);
            
            ProductParams l_ProductParams = TestDBUtility.getProductRow();
            l_ProductParams.setInstitutionCode("0D");
            l_ProductParams.setProductType(ProductTypeEnum.CASH);
            TestDBUtility.insertWithDel(l_BranchParams);
            TestDBUtility.insertWithDel(l_InstitutionParams);
            TestDBUtility.insertWithDel(l_MainAccountParams);
            TestDBUtility.insertWithDel(l_ProductParams);

            TestDBUtility.deleteAll(BranchPreferencesRow.TYPE);
            BranchPreferencesParams l_BranchPreferencesParams =
                TestDBUtility.getBranchPreferencesRow();
            l_BranchPreferencesParams.setBranchId(33381);
            TestDBUtility.insertWithDel(l_BranchPreferencesParams);
            
            TestDBUtility.deleteAll(TradingpowerCalcConditionRow.TYPE);
            TradingpowerCalcConditionParams l_TradingpowerCalcConditionParams =
                TestDBUtility.getTradingpowerCalcConditionRow();
            l_TradingpowerCalcConditionParams.setAccountId(333812512246L);
            TestDBUtility.insertWithDel(l_TradingpowerCalcConditionParams);
            

            
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        try
        {
            TestDBUtility.deleteAll(BankDepositNotifyRow.TYPE);
            BankDepositNotifyParams l_BankDepositNotifyParams = TestDBUtility.getBankDepositNotifyRow();
            l_BankDepositNotifyParams.setDataLoadDiv("A");
            l_BankDepositNotifyParams.setDepositDataAccountDate("010108");
            TestDBUtility.insertWithDel(l_BankDepositNotifyParams);
            AccountManager l_accMgr = (AccountManager) GtlUtils.getAccountManager();
            MainAccount l_mainAccount = l_accMgr.getMainAccount(333812512246L);
            WEB3AioCashinCooperationNotifyUnitServiceImpl l_impl =
                new WEB3AioCashinCooperationNotifyUnitServiceImpl();
            l_impl.submitOrder(l_BankDepositNotifyParams, (WEB3GentradeMainAccount)l_mainAccount);

        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * 沒有取到getSubAccount(arg0 : SubAccountTypeEnum)[引数] arg0： 2（株式信用取引口座（保証金））
     * 進入了if分隻
     */
    public void testSubmitOrderCase3()
    {
        final String STR_METHOD_NAME = " testSubmitOrderCase3()";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {
            //mock
            MOCK_MANAGER.setIsMockUsed(true);
            OrderSubmissionResult l_result =
                new OrderSubmissionResult(ProcessingResult.newSuccessResultInstance());
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.aio.WEB3AioOrderManager",
                    "submitNewOrder",
                    new Class[] {SubAccount.class, 
                    ProductTypeEnum.class, 
                    NewOrderSpec.class, long.class, 
                    String.class, boolean.class},
                    l_result);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.tradingpower.WEB3TPTradingPowerServiceImpl",
                    "reCalcTradingPower",
                    new Class[] {WEB3GentradeSubAccount.class},
                    null);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                    "getLoginInfo",
                    new Class[] {},
                    new LoginInfoImplForMock());
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.gentrade.WEB3HostReqOrderNumberManageServiceImpl",
                    "getNewNumber",
                    new Class[] {String.class, String.class, ProductTypeEnum.class },
                    "111");
                    
            Calendar ca1 =  Calendar.getInstance();
            ca1.set(2008,6-1,19);
            
            Date date1 = ca1.getTime();
            WEB3GentradeTradingTimeManagementForMock.mockGetOrderBizDate(date1);
            
            //data
            
            TestDBUtility.deleteAll(BankCashTransferStatusParams.TYPE);
            BankCashTransferStatusParams l_bankCashTransferStatusParams =
                WEB3AioMultiBankTelegramProcessServiceImplTest.getBankCashTransferStatusRow();
            l_bankCashTransferStatusParams.setTransactionStatus(WEB3TransactionStatusDef.OK);
            l_bankCashTransferStatusParams.setOrderStatusFlag(WEB3OrderStatusFlagDef.REPUEST_RECEIPT);
            l_bankCashTransferStatusParams.setStartStatusFlag(WEB3StartStatusFlgDef.REPUEST_RECEIPT);
            TestDBUtility.insertWithDel(l_bankCashTransferStatusParams);

            TestDBUtility.deleteAll(CareerShopIdParams.TYPE);
            CareerShopIdParams l_careerShopIdParams =
                WEB3AioMultiBankTelegramProcessServiceImplTest.getCareerShopIdRow();
            l_careerShopIdParams.setBranchCode("381");
            l_careerShopIdParams.setInstitutionCode("0D");
            l_careerShopIdParams.setCareerDiv("2");
            l_careerShopIdParams.setShopId("12345678");
            l_careerShopIdParams.setPfUrl("111111");
            l_careerShopIdParams.setReturnUrl("222222");
            TestDBUtility.insertWithDel(l_careerShopIdParams);
            
            TestDBUtility.deleteAll(CompBankConditionRow.TYPE);
            CompBankConditionParams l_CompBankConditionParams = new CompBankConditionParams();
            l_CompBankConditionParams.setInstitutionCode("0D");
            l_CompBankConditionParams.setBranchCode("381");
            l_CompBankConditionParams.setPaySchemeId("6");
            l_CompBankConditionParams.setShopId("111");
            TestDBUtility.insertWithDel(l_CompBankConditionParams);
            
            TestDBUtility.deleteAll(BranchRow.TYPE);
            TestDBUtility.deleteAll(InstitutionRow.TYPE);
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            TestDBUtility.deleteAll(ProductRow.TYPE);
            BranchParams l_BranchParams = TestDBUtility.getBranchRow();
            l_BranchParams.setBranchCode("381");
            InstitutionParams l_InstitutionParams = TestDBUtility.getInstitutionRow();
            l_InstitutionParams.setInstitutionCode("0D");
            MainAccountParams l_MainAccountParams = TestDBUtility.getMainAccountRow();
            l_MainAccountParams.setInstitutionCode("0D");
            l_MainAccountParams.setBranchCode("381");
            l_MainAccountParams.setAccountCode("2512246");
            l_MainAccountParams.setAccountId(333812512246L);
            l_MainAccountParams.setMarginGenAccOpenDiv("1");
            
            SubAccountParams l_subAccountParams1 = TestDBUtility.getSubAccountRow();
            l_subAccountParams1.setAccountId(333812512246L);
            l_subAccountParams1.setSubAccountId(33381251220301L);
            l_subAccountParams1.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
            SubAccountParams l_subAccountParams2 = TestDBUtility.getSubAccountRow();
            l_subAccountParams2.setAccountId(333812512246L);
            l_subAccountParams2.setSubAccountId(33381251220302L);
            l_subAccountParams2.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
            
            TestDBUtility.insertWithDel(l_subAccountParams1);
            TestDBUtility.insertWithDel(l_subAccountParams2);
            
            ProductParams l_ProductParams = TestDBUtility.getProductRow();
            l_ProductParams.setInstitutionCode("0D");
            l_ProductParams.setProductType(ProductTypeEnum.CASH);
            TestDBUtility.insertWithDel(l_BranchParams);
            TestDBUtility.insertWithDel(l_InstitutionParams);
            TestDBUtility.insertWithDel(l_MainAccountParams);
            TestDBUtility.insertWithDel(l_ProductParams);

            TestDBUtility.deleteAll(BranchPreferencesRow.TYPE);
            BranchPreferencesParams l_BranchPreferencesParams =
                TestDBUtility.getBranchPreferencesRow();
            l_BranchPreferencesParams.setBranchId(33381);
            TestDBUtility.insertWithDel(l_BranchPreferencesParams);
            
            TestDBUtility.deleteAll(TradingpowerCalcConditionRow.TYPE);
            TradingpowerCalcConditionParams l_TradingpowerCalcConditionParams =
                TestDBUtility.getTradingpowerCalcConditionRow();
            l_TradingpowerCalcConditionParams.setAccountId(333812512246L);
            TestDBUtility.insertWithDel(l_TradingpowerCalcConditionParams);
            

            
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        try
        {
            TestDBUtility.deleteAll(BankDepositNotifyRow.TYPE);
            BankDepositNotifyParams l_BankDepositNotifyParams = TestDBUtility.getBankDepositNotifyRow();
            l_BankDepositNotifyParams.setDataLoadDiv("A");
            l_BankDepositNotifyParams.setDepositDataAccountDate("010108");
            TestDBUtility.insertWithDel(l_BankDepositNotifyParams);
            AccountManager l_accMgr = (AccountManager) GtlUtils.getAccountManager();
            MainAccount l_mainAccount = l_accMgr.getMainAccount(333812512246L);
            WEB3AioCashinCooperationNotifyUnitServiceImpl l_impl =
                new WEB3AioCashinCooperationNotifyUnitServiceImpl();
            l_impl.submitOrder(l_BankDepositNotifyParams, (WEB3GentradeMainAccount)l_mainAccount);

            
        }
        catch (WEB3BaseException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80005, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }

    private class WEB3AioCashinCooperationNotifyUnitServiceImplForTest
        extends WEB3AioCashinCooperationNotifyUnitServiceImpl
    {
        protected boolean isForeignCurrencyCode(
            String l_strInstitutionCode, 
            String l_strForeignCurrencyCode)
        {
            
            return true;
        }  
        /**
         * (submit注文)<BR>
         * 入金通知の注文登録する。<BR>
         * <BR>
         * シーケンス図「（入金連携通知一件サービスImpl）submit注文」 参照<BR>
         * <BR>
         * @@param l_bankDepositNotifyParams - 入金通知Params<BR>
         * @@param l_mainAccount  顧客 <BR>
         * @@throws WEB3BaseException
         * @@roseuid 40BEFA0600BA
         */
        protected void submitOrder(
            BankDepositNotifyParams l_bankDepositNotifyParams,
            WEB3GentradeMainAccount l_mainAccount) throws WEB3BaseException
        {

        }
        
        protected void updateDepositNotify(
            BankDepositNotifyParams l_bankDepositNotifyParams, 
            Map l_mapSpac) throws WEB3BaseException
        {
            final String STR_METHOD_NAME = "updateDepositNotify()";
            log.entering(STR_METHOD_NAME);
            
            log.debug("Map status ==" + l_mapSpac.get("status"));
            log.debug("Map deposit_error_comment ==" + l_mapSpac.get("deposit_error_comment"));
            log.debug("Map last_error_history_serial_no ==" + l_mapSpac.get("last_error_history_serial_no"));
            if (l_bankDepositNotifyParams.getDepositDataDepositAmount().length() <= 5)
            {
                
                assertEquals("1", l_mapSpac.get("status"));
                assertEquals(null, l_mapSpac.get("deposit_error_comment"));
            }
            else
            {
                assertEquals("9", l_mapSpac.get("status"));
                assertEquals("円換算額桁数エラー", l_mapSpac.get("deposit_error_comment"));
            }
            
            assertEquals("20", l_mapSpac.get("last_error_history_serial_no"));

            log.exiting(STR_METHOD_NAME);
        }
        
        /**
         * (insert入金通知処理エラー履歴)<BR>
         * 入金通知処理エラー履歴テーブルに1件追加する。<BR>
         * <BR>
         * <DB更新仕様参照> <BR>
         * 銀行入金通知_入金通知処理エラー履歴テーブル.xls<BR>
         * <BR>
         * @@param l_bankDepositNotifyParams - 入金通知Params<BR>
         * @@throws WEB3BaseException
         * @@roseuid 40BEFA0600BA
         */
        protected void insertDepositErrorHistory(BankDepositNotifyParams l_bankDepositNotifyParams) 
            throws WEB3BaseException
        {
            final String STR_METHOD_NAME =
                "insertDepositErrorHistory(BankDepositNotifyParams l_bankDepositNotifyParams) ";
            log.entering(STR_METHOD_NAME);
            
            //)銀行入金通知テーブルID
            assertEquals(123 ,l_bankDepositNotifyParams.getBankDepositNotifyId());

            //)履歴番号
            assertEquals(20 ,l_bankDepositNotifyParams.getLastErrorHistorySerialNo());
            
            //)入金エラーコメント
            assertEquals("円換算額桁数エラー" ,l_bankDepositNotifyParams.getDepositErrorComment());
            
            //)備考
            assertEquals(null ,l_bankDepositNotifyParams.getRemark());

            //)証券会社コード = 銀行入金通知テーブル.証券会社コード
            assertEquals("123" ,l_bankDepositNotifyParams.getInstitutionCode());

            //)データ取込区分 = 銀行入金通知テーブル.データ取込区分
            assertEquals("1" ,l_bankDepositNotifyParams.getDataLoadDiv());

          
            log.exiting(STR_METHOD_NAME);
        }
    }
    
    public EraParams getEraParams()
    {
        EraParams l_eraParams = new EraParams();
        //年号区分    japanese_era_div    NUMBER    1    NotNull    1：明治、2：大正、3：昭和、4：平成、9：不明
        l_eraParams.setJapaneseEraDiv(1);
        //年号    japanese_era    VARCHAR2    20    NotNull    1：明治、2：大正、3：昭和、4：平成、9：不明
        l_eraParams.setJapaneseEra("1");
        //開始年(和暦)    start_year_jap    VARCHAR2    2    NotNull    和暦(YY)
        l_eraParams.setStartYearJap("01");
        //開始年(西暦)    start_year    VARCHAR2    4    NotNull    西暦(YYYY)
        l_eraParams.setStartYear("2008");
        //開始月日    start_date    VARCHAR2    4    NotNull    MMDD
        l_eraParams.setStartDate("0002");
        //終了年(和暦)    end_year_jap    VARCHAR2    2    NotNull    和暦(YY)
        l_eraParams.setEndYearJap("02");
        //終了年(西暦)    end_year    VARCHAR2    4    NotNull    西暦(YYYY)
        l_eraParams.setEndYear("2009");
        //終了月日    end_date    VARCHAR2    4    NotNull    MMDD
        l_eraParams.setEndDate("0102");
        //作成日時    created_timestamp    DATE        NotNull    DEFAULT sysdate
        l_eraParams.setCreatedTimestamp(GtlUtils.getSystemTimestamp());
        //更新日時    last_updated_timestamp    DATE        NotNull    DEFAULT sysdate
        l_eraParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
        return l_eraParams;
    }
    
}
@
