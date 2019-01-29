head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.01.39.39;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3AioMultiBankTelegramProcessServiceImplTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 保証金への振替サービスImplTest(WEB3MarginTransferServiceImplTest.java)
Author Name      : Daiwa Institute of Research
Revision History : 新規作成
Revision History : 2007/07/13 張騰宇 (中訊) モデルNo.734
*/
package webbroker3.aio.service.delegate.stdimpls;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

import com.fitechlabs.xtrade.plugin.security.oplogin.impl.LoginInfoImplForMock;
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
import webbroker3.aio.data.BankCashTransferStatusRow;
import webbroker3.aio.data.BankSettleResultResponseParams;
import webbroker3.aio.data.CareerShopIdParams;
import webbroker3.aio.data.CompBankConditionParams;
import webbroker3.aio.data.CompBankConditionRow;
import webbroker3.aio.define.WEB3AioTelegramFormatDef;
import webbroker3.aio.define.WEB3AioTelegramReturnCodeDef;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3OrderStatusFlagDef;
import webbroker3.common.define.WEB3StartStatusFlgDef;
import webbroker3.common.define.WEB3TransactionStatusDef;
import webbroker3.gentrade.WEB3GentradeMainAccount;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagementForMock;
import webbroker3.gentrade.data.BranchPreferencesParams;
import webbroker3.gentrade.data.BranchPreferencesRow;
import webbroker3.gentrade.data.TradingpowerCalcConditionParams;
import webbroker3.gentrade.data.TradingpowerCalcConditionRow;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3LogUtility;

public class WEB3AioMultiBankTelegramProcessServiceImplTest extends TestBaseForMock
{

    /**
     * ログユーティリティ<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AioMultiBankTelegramProcessServiceImplTest.class);

    public WEB3AioMultiBankTelegramProcessServiceImplTest(String arg0) {
        super(arg0);
    }

    protected void setUp() throws Exception {
        super.setUp();
        TestDBUtility.deleteAll(MainAccountRow.TYPE);
        TestDBUtility.deleteAll(SubAccountRow.TYPE);
    }

    protected void tearDown() throws Exception {
        super.tearDown();
    }

    /**
     * 金融機@関連携入出金状況.処理区分 != ０：未処理
     * 処理FLAG（注文）!= ０：未処理
     */
    public void test_orderRequireAccept_C0001()
    {
        final String STR_METHOD_NAME = " test_orderRequireAccept_C0001()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            TestDBUtility.deleteAll(BankCashTransferStatusParams.TYPE);
            BankCashTransferStatusParams l_bankCashTransferStatusParams =
                WEB3AioMultiBankTelegramProcessServiceImplTest.getBankCashTransferStatusRow();
            l_bankCashTransferStatusParams.setTransactionStatus(WEB3TransactionStatusDef.OK);
            l_bankCashTransferStatusParams.setOrderStatusFlag(WEB3OrderStatusFlagDef.REPUEST_RECEIPT);
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

            String l_strInstitutionCode = "0D";
            String l_strBranchCode = "381";
            String l_strOrderRequestNumber = "123456";

            String l_strReturnCode = WEB3AioTelegramReturnCodeDef.OK;
            HashMap l_receiptTelegramData = new HashMap();
            l_receiptTelegramData.put(WEB3AioTelegramFormatDef.cpy, l_strInstitutionCode);
            l_receiptTelegramData.put(WEB3AioTelegramFormatDef.btn, l_strBranchCode);
            l_receiptTelegramData.put(WEB3AioTelegramFormatDef.req, l_strOrderRequestNumber);
            l_receiptTelegramData.put(WEB3AioTelegramFormatDef.protocolVersion, "1");
            l_receiptTelegramData.put(WEB3AioTelegramFormatDef.linked_1, "2");
            l_receiptTelegramData.put(WEB3AioTelegramFormatDef.shopId, "3");
            l_receiptTelegramData.put(WEB3AioTelegramFormatDef.date, "4");
            l_receiptTelegramData.put(WEB3AioTelegramFormatDef.paySchemeId, "6");
            l_receiptTelegramData.put(WEB3AioTelegramFormatDef.centerPayId, "5");
            l_receiptTelegramData.put(WEB3AioTelegramFormatDef.accessKey, "7");
            l_receiptTelegramData.put(WEB3AioTelegramFormatDef.rdiv, "8");

            WEB3AioMultiBankTelegramProcessServiceImpl l_aioMultiBankTelegramProcessServiceImpl =
                new WEB3AioMultiBankTelegramProcessServiceImpl();
            l_aioMultiBankTelegramProcessServiceImpl.orderRequireAccept(l_strReturnCode, l_receiptTelegramData);
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
    
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    /**
     * 金融機@関連携入出金状況.処理区分 != ０：未処理
     * 処理FLAG（注文） != ２：応答送信
     * 処理FLAG（決済開始） != ０：未処理
     */
    public void test_settlementStartAccept_C0001()
    {
        final String STR_METHOD_NAME = " test_settlementStartAccept_C0001()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
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

            String l_strInstitutionCode = "0D";
            String l_strBranchCode = "381";
            String l_strOrderRequestNumber = "123456";

            String l_strReturnCode = WEB3AioTelegramReturnCodeDef.OK;
            HashMap l_receiptTelegramData = new HashMap();
            l_receiptTelegramData.put(WEB3AioTelegramFormatDef.cpy, l_strInstitutionCode);
            l_receiptTelegramData.put(WEB3AioTelegramFormatDef.btn, l_strBranchCode);
            l_receiptTelegramData.put(WEB3AioTelegramFormatDef.req, l_strOrderRequestNumber);
            l_receiptTelegramData.put(WEB3AioTelegramFormatDef.protocolVersion, "1");
            l_receiptTelegramData.put(WEB3AioTelegramFormatDef.linked_1, "2");
            l_receiptTelegramData.put(WEB3AioTelegramFormatDef.shopId, "3");
            l_receiptTelegramData.put(WEB3AioTelegramFormatDef.date, "4");
            l_receiptTelegramData.put(WEB3AioTelegramFormatDef.paySchemeId, "6");
            l_receiptTelegramData.put(WEB3AioTelegramFormatDef.centerPayId, "5");
            l_receiptTelegramData.put(WEB3AioTelegramFormatDef.accessKey, "7");
            l_receiptTelegramData.put(WEB3AioTelegramFormatDef.rdiv, "8");
            l_receiptTelegramData.put(WEB3AioTelegramFormatDef.payStatus, "9");

            WEB3AioMultiBankTelegramProcessServiceImpl l_aioMultiBankTelegramProcessServiceImpl =
                new WEB3AioMultiBankTelegramProcessServiceImpl();
            l_aioMultiBankTelegramProcessServiceImpl.settlementStartAccept(l_strReturnCode, l_receiptTelegramData);
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
    
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    /**
     * 金融機@関連携入出金状況.処理区分 != ０：未処理
     * 処理FLAG（注文）!= ２：応答送信
     * 処理FLAG（決済開始） != ２：応答送信
     * 処理FLAG（決済結果）!= ０：未処理
     */
    public void test_settlementResultNotify_C0001()
    {
        final String STR_METHOD_NAME = " test_settlementResultNotify_C0001()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
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

            String l_strInstitutionCode = "0D";
            String l_strBranchCode = "381";
            String l_strOrderRequestNumber = "123456";

            String l_strReturnCode = WEB3AioTelegramReturnCodeDef.OK;
            HashMap l_receiptTelegramData = new HashMap();
            l_receiptTelegramData.put(WEB3AioTelegramFormatDef.cpy, l_strInstitutionCode);
            l_receiptTelegramData.put(WEB3AioTelegramFormatDef.btn, l_strBranchCode);
            l_receiptTelegramData.put(WEB3AioTelegramFormatDef.req, l_strOrderRequestNumber);
            l_receiptTelegramData.put(WEB3AioTelegramFormatDef.acc, "123456");
            l_receiptTelegramData.put(WEB3AioTelegramFormatDef.protocolVersion, "1");
            l_receiptTelegramData.put(WEB3AioTelegramFormatDef.linked_1, "2");
            l_receiptTelegramData.put(WEB3AioTelegramFormatDef.shopId, "3");
            l_receiptTelegramData.put(WEB3AioTelegramFormatDef.date, "4");
            l_receiptTelegramData.put(WEB3AioTelegramFormatDef.paySchemeId, "6");
            l_receiptTelegramData.put(WEB3AioTelegramFormatDef.centerPayId, "5");
            l_receiptTelegramData.put(WEB3AioTelegramFormatDef.accessKey, "7");
            l_receiptTelegramData.put(WEB3AioTelegramFormatDef.rdiv, "8");
            l_receiptTelegramData.put(WEB3AioTelegramFormatDef.payStatus, "9");

            //MainAccountParams
            TestDBUtility.deleteAll(MainAccountParams.TYPE);
            MainAccountParams l_params = TestDBUtility.getMainAccountRow();
            l_params.setInstitutionCode("0D");
            l_params.setBranchCode("381");
            l_params.setAccountCode("123456");
            TestDBUtility.insertWithDel(l_params);

            WEB3AioMultiBankTelegramProcessServiceImpl l_aioMultiBankTelegramProcessServiceImpl =
                new WEB3AioMultiBankTelegramProcessServiceImpl();
            l_aioMultiBankTelegramProcessServiceImpl.settlementResultNotify(l_strReturnCode, l_receiptTelegramData);
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
    
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    public void test_settlementResultNotify_C0002()
    {
        final String STR_METHOD_NAME = " test_settlementResultNotify_C0002()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
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

            String l_strInstitutionCode = "0D";
            String l_strBranchCode = "381";
            String l_strOrderRequestNumber = "123456";

            String l_strReturnCode = WEB3AioTelegramReturnCodeDef.OK;
            HashMap l_receiptTelegramData = new HashMap();
            l_receiptTelegramData.put(WEB3AioTelegramFormatDef.cpy, l_strInstitutionCode);
            l_receiptTelegramData.put(WEB3AioTelegramFormatDef.btn, l_strBranchCode);
            l_receiptTelegramData.put(WEB3AioTelegramFormatDef.req, l_strOrderRequestNumber);
            l_receiptTelegramData.put(WEB3AioTelegramFormatDef.acc, "123456");
            l_receiptTelegramData.put(WEB3AioTelegramFormatDef.protocolVersion, "1");
            l_receiptTelegramData.put(WEB3AioTelegramFormatDef.linked_1, "2");
            l_receiptTelegramData.put(WEB3AioTelegramFormatDef.shopId, "3");
            l_receiptTelegramData.put(WEB3AioTelegramFormatDef.date, "4");
            l_receiptTelegramData.put(WEB3AioTelegramFormatDef.paySchemeId, "6");
            l_receiptTelegramData.put(WEB3AioTelegramFormatDef.centerPayId, "5");
            l_receiptTelegramData.put(WEB3AioTelegramFormatDef.accessKey, "7");
            l_receiptTelegramData.put(WEB3AioTelegramFormatDef.rdiv, "8");
            l_receiptTelegramData.put(WEB3AioTelegramFormatDef.payStatus, "9");

            //MainAccountParams
            TestDBUtility.deleteAll(MainAccountParams.TYPE);

            WEB3AioMultiBankTelegramProcessServiceImpl l_aioMultiBankTelegramProcessServiceImpl =
                new WEB3AioMultiBankTelegramProcessServiceImpl();
            String l_str[] =
                l_aioMultiBankTelegramProcessServiceImpl.settlementResultNotify(l_strReturnCode, l_receiptTelegramData);
            assertNull(l_str);
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
    
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    /**
     * is信用口座開設(弁済区分 : String) = false
     * 沒有調用submit保証金振替(顧客, Date, double, String)
     */
    public void testSubmitOrderCase1()
    {
        final String STR_METHOD_NAME = " testSubmitOrderCase1()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
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
                    
            Calendar ca1 =  Calendar.getInstance();
            ca1.set(2007,6-1,19);
            
            Date date1 = ca1.getTime();
            WEB3GentradeTradingTimeManagementForMock.mockGetOrderBizDate(date1);
            
            TestDBUtility.deleteAll(BankCashTransferStatusRow.TYPE);
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
            SubAccountParams l_SubAccountParams = TestDBUtility.getSubAccountRow();
            l_SubAccountParams.setAccountId(333812512246L);
            l_SubAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
            ProductParams l_ProductParams = TestDBUtility.getProductRow();
            l_ProductParams.setInstitutionCode("0D");
            l_ProductParams.setProductType(ProductTypeEnum.CASH);
            TestDBUtility.insertWithDel(l_BranchParams);
            TestDBUtility.insertWithDel(l_InstitutionParams);
            TestDBUtility.insertWithDel(l_MainAccountParams);
            TestDBUtility.insertWithDel(l_SubAccountParams);
            TestDBUtility.insertWithDel(l_ProductParams);

            String l_strInstitutionCode = "0D";
            String l_strBranchCode = "381";
            String l_strOrderRequestNumber = "123456";
            String l_strAccountCode = "2512246";

//            String l_strReturnCode = WEB3AioTelegramReturnCodeDef.OK;
            HashMap l_receiptTelegramData = new HashMap();
            l_receiptTelegramData.put(WEB3AioTelegramFormatDef.cpy, l_strInstitutionCode);
            l_receiptTelegramData.put(WEB3AioTelegramFormatDef.btn, l_strBranchCode);
            l_receiptTelegramData.put(WEB3AioTelegramFormatDef.req, l_strOrderRequestNumber);
            l_receiptTelegramData.put(WEB3AioTelegramFormatDef.acc, l_strAccountCode);
            l_receiptTelegramData.put(WEB3AioTelegramFormatDef.protocolVersion, "1");
            l_receiptTelegramData.put(WEB3AioTelegramFormatDef.linked_1, "2");
            l_receiptTelegramData.put(WEB3AioTelegramFormatDef.shopId, "3");
            l_receiptTelegramData.put(WEB3AioTelegramFormatDef.date, "4");
            l_receiptTelegramData.put(WEB3AioTelegramFormatDef.paySchemeId, "6");
            l_receiptTelegramData.put(WEB3AioTelegramFormatDef.centerPayId, "5");
            l_receiptTelegramData.put(WEB3AioTelegramFormatDef.accessKey, "7");
            l_receiptTelegramData.put(WEB3AioTelegramFormatDef.rdiv, "8");
            l_receiptTelegramData.put(WEB3AioTelegramFormatDef.payStatus, "9");
            l_receiptTelegramData.put(WEB3AioTelegramFormatDef.amount, "999");

            WEB3AioMultiBankTelegramProcessServiceImpl l_aioMultiBankTelegramProcessServiceImpl =
                new WEB3AioMultiBankTelegramProcessServiceImpl();
            l_aioMultiBankTelegramProcessServiceImpl.submitOrder(l_receiptTelegramData);
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
    
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    /**
     * is信用口座開設(弁済区分 : String) = true
     * 調用submit保証金振替(顧客, Date, double, String)
     * 弁済区分＝”指定なし”の場合<BR>
     * 制度信用取引口座開設区分＝”口座開設”<BR>
     */
    public void testSubmitOrderCase2()
    {
        final String STR_METHOD_NAME = " testSubmitOrderCase2()";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {
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
            ca1.set(2007,6-1,19);
            
            Date date1 = ca1.getTime();
            WEB3GentradeTradingTimeManagementForMock.mockGetOrderBizDate(date1);
            
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

            TestDBUtility.deleteAll(SubAccountRow.TYPE);
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
            
            String l_strInstitutionCode = "0D";
            String l_strBranchCode = "381";
            String l_strOrderRequestNumber = "123456";
            String l_strAccountCode = "2512246";

//            String l_strReturnCode = WEB3AioTelegramReturnCodeDef.OK;
            HashMap l_receiptTelegramData = new HashMap();
            l_receiptTelegramData.put(WEB3AioTelegramFormatDef.cpy, l_strInstitutionCode);
            l_receiptTelegramData.put(WEB3AioTelegramFormatDef.btn, l_strBranchCode);
            l_receiptTelegramData.put(WEB3AioTelegramFormatDef.req, l_strOrderRequestNumber);
            l_receiptTelegramData.put(WEB3AioTelegramFormatDef.acc, l_strAccountCode);
            l_receiptTelegramData.put(WEB3AioTelegramFormatDef.protocolVersion, "1");
            l_receiptTelegramData.put(WEB3AioTelegramFormatDef.linked_1, "2");
            l_receiptTelegramData.put(WEB3AioTelegramFormatDef.shopId, "3");
            l_receiptTelegramData.put(WEB3AioTelegramFormatDef.date, "4");
            l_receiptTelegramData.put(WEB3AioTelegramFormatDef.paySchemeId, "6");
            l_receiptTelegramData.put(WEB3AioTelegramFormatDef.centerPayId, "5");
            l_receiptTelegramData.put(WEB3AioTelegramFormatDef.accessKey, "7");
            l_receiptTelegramData.put(WEB3AioTelegramFormatDef.rdiv, "8");
            l_receiptTelegramData.put(WEB3AioTelegramFormatDef.payStatus, "9");
            l_receiptTelegramData.put(WEB3AioTelegramFormatDef.amount, "999");

            WEB3AioMultiBankTelegramProcessServiceImpl l_aioMultiBankTelegramProcessServiceImpl =
                new WEB3AioMultiBankTelegramProcessServiceImpl();
            l_aioMultiBankTelegramProcessServiceImpl.submitOrder(l_receiptTelegramData);
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
    
        log.exiting(TEST_END + STR_METHOD_NAME);
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
            ca1.set(2007,6-1,19);
            
            Date date1 = ca1.getTime();
            WEB3GentradeTradingTimeManagementForMock.mockGetOrderBizDate(date1);
            
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
            
            String l_strInstitutionCode = "0D";
            String l_strBranchCode = "381";
            String l_strOrderRequestNumber = "123456";
            String l_strAccountCode = "2512246";

//            String l_strReturnCode = WEB3AioTelegramReturnCodeDef.OK;
            HashMap l_receiptTelegramData = new HashMap();
            l_receiptTelegramData.put(WEB3AioTelegramFormatDef.cpy, l_strInstitutionCode);
            l_receiptTelegramData.put(WEB3AioTelegramFormatDef.btn, l_strBranchCode);
            l_receiptTelegramData.put(WEB3AioTelegramFormatDef.req, l_strOrderRequestNumber);
            l_receiptTelegramData.put(WEB3AioTelegramFormatDef.acc, l_strAccountCode);
            l_receiptTelegramData.put(WEB3AioTelegramFormatDef.protocolVersion, "1");
            l_receiptTelegramData.put(WEB3AioTelegramFormatDef.linked_1, "2");
            l_receiptTelegramData.put(WEB3AioTelegramFormatDef.shopId, "3");
            l_receiptTelegramData.put(WEB3AioTelegramFormatDef.date, "4");
            l_receiptTelegramData.put(WEB3AioTelegramFormatDef.paySchemeId, "6");
            l_receiptTelegramData.put(WEB3AioTelegramFormatDef.centerPayId, "5");
            l_receiptTelegramData.put(WEB3AioTelegramFormatDef.accessKey, "7");
            l_receiptTelegramData.put(WEB3AioTelegramFormatDef.rdiv, "8");
            l_receiptTelegramData.put(WEB3AioTelegramFormatDef.payStatus, "9");
            l_receiptTelegramData.put(WEB3AioTelegramFormatDef.amount, "999");

            WEB3AioMultiBankTelegramProcessServiceImpl l_aioMultiBankTelegramProcessServiceImpl =
                new WEB3AioMultiBankTelegramProcessServiceImpl();
            l_aioMultiBankTelegramProcessServiceImpl.submitOrder(l_receiptTelegramData);
        }
        catch (WEB3BaseException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80005, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
    
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    
    /**
     * 金融機@関連携入出金状況Row
     * @@return
     */
    public static BankCashTransferStatusParams getBankCashTransferStatusRow()
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
        return l_bankCashTransferStatusParams;
    }

    public static BankSettleResultResponseParams getBankSettleResultResponseRow()
    {
        BankSettleResultResponseParams l_bankSettleResultResponseParams = new BankSettleResultResponseParams();
        
//        l_bankSettleResultResponseParams.setBranchCode("381");
//        l_bankSettleResultResponseParams.setAccountCode("2512246");
//        l_bankSettleResultResponseParams.setPaySchemeId("123456789");
//        l_bankSettleResultResponseParams.setOrderStatusFlag("0");
//        l_bankSettleResultResponseParams.setOrderRequestNumber("123456");
//        l_bankSettleResultResponseParams.setStartStatusFlag("1");
//        l_bankSettleResultResponseParams.setResultStatusFlag("1");
//        l_bankSettleResultResponseParams.setTransactionStatus("1");
        return l_bankSettleResultResponseParams;
    }

    public static CareerShopIdParams getCareerShopIdRow()
    {
        CareerShopIdParams l_careerShopIdParams = new CareerShopIdParams();
        l_careerShopIdParams.setBranchCode("381");
        l_careerShopIdParams.setInstitutionCode("0D");
        l_careerShopIdParams.setCareerDiv("2");
        l_careerShopIdParams.setShopId("12345678");
        l_careerShopIdParams.setPfUrl("111111");
        l_careerShopIdParams.setReturnUrl("222222");
        return l_careerShopIdParams;
    }
    

}
@
