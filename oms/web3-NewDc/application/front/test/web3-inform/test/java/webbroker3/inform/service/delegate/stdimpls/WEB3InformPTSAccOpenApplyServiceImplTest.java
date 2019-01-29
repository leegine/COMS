head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.02.13.26;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3InformPTSAccOpenApplyServiceImplTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.inform.service.delegate.stdimpls;


import java.lang.reflect.Method;
import java.util.Date;
import java.util.List;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.kernel.util.ThreadLocalSystemAttributesRegistry;
import com.fitechlabs.xtrade.plugin.security.oplogin.impl.LoginInfoImpl;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProcessingResult;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccountTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Trader;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountPK;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.TraderParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.OrderValidationResult;
import com.fitechlabs.xtrade.plugin.tc.gentrade.stdimpls.MainAccountImpl;


import test.util.TestDBUtility;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3MarketCodeDef;
import webbroker3.common.define.WEB3OrderAccProductDef;
import webbroker3.common.define.WEB3OrderAccTransactionDef;
import webbroker3.common.define.WEB3ProductCodeDef;
import webbroker3.common.define.WEB3TradingTimeTypeDef;
import webbroker3.gentrade.WEB3GentradeMainAccount;
import webbroker3.gentrade.WEB3GentradeOrderValidatorForMock;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.gentrade.WEB3GentradeTradingClendarContext;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagementForMock;
import webbroker3.gentrade.data.OrderAcceptStatusParams;
import webbroker3.gentrade.data.QuestionAnswerParams;
import webbroker3.gentrade.data.QuestionAnswerRow;
import webbroker3.gentrade.data.QuestionParams;
import webbroker3.inform.data.VariousInformParams;
import webbroker3.inform.data.VariousInformRow;
import webbroker3.inform.message.WEB3InformPTSAccOpenApplyCmpRequest;
import webbroker3.inform.message.WEB3InformPTSAccOpenApplyInpRequest;
import webbroker3.inform.message.WEB3InformPTSAccOpenApplyInpResponse;
import webbroker3.inform.message.WEB3InformPTSTradeAgreementUnit;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3LogUtility;

public class WEB3InformPTSAccOpenApplyServiceImplTest extends TestBaseForMock
{
    /**
     * ログユーティリティ<BR>
     */
    private static WEB3LogUtility log = WEB3LogUtility
            .getInstance(WEB3InformPTSAccOpenApplyServiceImplTest.class);

    public WEB3InformPTSAccOpenApplyServiceImplTest(String arg0)
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

    public void testInsertQuestionAnswer_0001()
    {
        final String STR_METHOD_NAME = "testInsertQuestionAnswer_0001()";
        log.entering(TEST_START + STR_METHOD_NAME);

        WEB3InformPTSTradeAgreementUnit[] l_ptsTradeAgreementList = new WEB3InformPTSTradeAgreementUnit[0];
        WEB3InformPTSAccOpenApplyServiceImpl impl = new WEB3InformPTSAccOpenApplyServiceImpl();
        try
        {
            TestDBUtility.deleteAll(QuestionAnswerRow.TYPE);
            
            Method l_method =
                impl.getClass().getDeclaredMethod("insertQuestionAnswer",
                    new Class[]{String.class, String.class, String.class, WEB3InformPTSTradeAgreementUnit[].class});
            l_method.setAccessible(true);
            Object[] objs = new Object[4];
            objs[0] = null;
            objs[1] = null;
            objs[2] = null;
            objs[3] = l_ptsTradeAgreementList;
            l_method.invoke(impl, objs);
            
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            List l_list = l_queryProcessor.doFindAllQuery(QuestionAnswerRow.TYPE);
            
            assertEquals(0, l_list.size());
            
            log.exiting(STR_METHOD_NAME);
        }
        catch (Exception e)
        {
            log.error(STR_METHOD_NAME, e);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
    }
    
    public void testInsertQuestionAnswer_0002()
    {
        final String STR_METHOD_NAME = "testInsertQuestionAnswer_0002()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        String l_strInstitutionCode = "0D";
        String l_strBranchCode = "381";
        String l_strRequestNumber = "123456789";
        WEB3InformPTSTradeAgreementUnit[] l_ptsTradeAgreementList = new WEB3InformPTSTradeAgreementUnit[1];
        WEB3InformPTSTradeAgreementUnit unit = new WEB3InformPTSTradeAgreementUnit();
        unit.questionNumber = "123";
        unit.questionAnswer = "1";
        l_ptsTradeAgreementList[0] = unit;
        
        WEB3InformPTSAccOpenApplyServiceImpl impl = new WEB3InformPTSAccOpenApplyServiceImpl();
        try
        {
            TestDBUtility.deleteAll(QuestionAnswerRow.TYPE);
            
            Method l_method =
                impl.getClass().getDeclaredMethod("insertQuestionAnswer",
                    new Class[]{String.class, String.class, String.class, WEB3InformPTSTradeAgreementUnit[].class});
            l_method.setAccessible(true);
            Object[] objs = new Object[4];
            objs[0] = l_strInstitutionCode;
            objs[1] = l_strBranchCode;
            objs[2] = l_strRequestNumber;
            objs[3] = l_ptsTradeAgreementList;
            l_method.invoke(impl, objs);
            
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            List l_list = l_queryProcessor.doFindAllQuery(QuestionAnswerRow.TYPE);
            
            assertEquals(1, l_list.size());
            
            QuestionAnswerRow row = (QuestionAnswerRow)l_list.get(0);
            assertEquals("0D", row.getInstitutionCode());
            assertEquals("381", row.getBranchCode());
            assertEquals("123456789", row.getOrderRequestNumber());
            assertEquals("123", row.getQuestionNo());
            assertEquals("1", row.getQuestionAnswer());
            assertEquals(null, row.getLastUpdater());
            
            log.exiting(STR_METHOD_NAME);
        }
        catch (Exception e)
        {
            log.error(STR_METHOD_NAME, e);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
    }
    
    public void testInsertQuestionAnswer_0003()
    {
        final String STR_METHOD_NAME = "testInsertQuestionAnswer_0003()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        String l_strInstitutionCode = "0D";
        String l_strBranchCode = "381";
        String l_strRequestNumber = "123456789";
        WEB3InformPTSTradeAgreementUnit[] l_ptsTradeAgreementList = new WEB3InformPTSTradeAgreementUnit[3];
        WEB3InformPTSTradeAgreementUnit unit = new WEB3InformPTSTradeAgreementUnit();
        unit.questionNumber = "001";
        unit.questionAnswer = "1";
        l_ptsTradeAgreementList[0] = unit;
        WEB3InformPTSTradeAgreementUnit unit1 = new WEB3InformPTSTradeAgreementUnit();
        unit1.questionNumber = "002";
        unit1.questionAnswer = "1";
        l_ptsTradeAgreementList[1] = unit1;
        WEB3InformPTSTradeAgreementUnit unit2 = new WEB3InformPTSTradeAgreementUnit();
        unit2.questionNumber = "003";
        unit2.questionAnswer = "1";
        l_ptsTradeAgreementList[2] = unit2;
        
        WEB3InformPTSAccOpenApplyServiceImpl impl = new WEB3InformPTSAccOpenApplyServiceImpl();
        try
        {
            TestDBUtility.deleteAll(QuestionAnswerRow.TYPE);
            
            Method l_method =
                impl.getClass().getDeclaredMethod("insertQuestionAnswer",
                    new Class[]{String.class, String.class, String.class, WEB3InformPTSTradeAgreementUnit[].class});
            l_method.setAccessible(true);
            Object[] objs = new Object[4];
            objs[0] = l_strInstitutionCode;
            objs[1] = l_strBranchCode;
            objs[2] = l_strRequestNumber;
            objs[3] = l_ptsTradeAgreementList;
            l_method.invoke(impl, objs);
            
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            List l_list = l_queryProcessor.doFindAllQuery(QuestionAnswerRow.TYPE);
            
            assertEquals(3, l_list.size());
            
            QuestionAnswerRow row = (QuestionAnswerRow)l_list.get(0);
            assertEquals("0D", row.getInstitutionCode());
            assertEquals("381", row.getBranchCode());
            assertEquals("123456789", row.getOrderRequestNumber());
            assertEquals("001", row.getQuestionNo());
            assertEquals("1", row.getQuestionAnswer());
            assertEquals(null, row.getLastUpdater());
            
            QuestionAnswerRow row1 = (QuestionAnswerRow)l_list.get(1);
            assertEquals("0D", row1.getInstitutionCode());
            assertEquals("381", row1.getBranchCode());
            assertEquals("123456789", row1.getOrderRequestNumber());
            assertEquals("002", row1.getQuestionNo());
            assertEquals("1", row1.getQuestionAnswer());
            assertEquals(null, row1.getLastUpdater());
            
            QuestionAnswerRow row2 = (QuestionAnswerRow)l_list.get(2);
            assertEquals("0D", row2.getInstitutionCode());
            assertEquals("381", row2.getBranchCode());
            assertEquals("123456789", row2.getOrderRequestNumber());
            assertEquals("003", row2.getQuestionNo());
            assertEquals("1", row2.getQuestionAnswer());
            assertEquals(null, row2.getLastUpdater());
            log.exiting(STR_METHOD_NAME);
        }
        catch (Exception e)
        {
            log.error(STR_METHOD_NAME, e);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
    }

    public void testGetInputScreen_0001()
    {
        final String STR_METHOD_NAME = "testGetInputScreen_0001()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        WEB3InformPTSAccOpenApplyInpRequest request = new WEB3InformPTSAccOpenApplyInpRequest();
        request.informType = null;
        
        try
        {
            WEB3InformPTSAccOpenApplyServiceImpl impl = new WEB3InformPTSAccOpenApplyServiceImpl();
            impl.getInputScreen(request);

            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);

            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_01817, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testGetInputScreen_0002()
    {
        final String STR_METHOD_NAME = "testGetInputScreen_0002()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        WEB3InformPTSAccOpenApplyInpRequest request = new WEB3InformPTSAccOpenApplyInpRequest();
        request.informType = "123";
        
        try
        {
            TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getAccountId",
                new Class[] {},
                new Long("123")
                );
            
            MainAccountImpl l_mainAccountImpl = new MainAccountImpl(TestDBUtility.getMainAccountRow());
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.gentrade.WEB3GentradeAccountManager",
                "getMainAccount",
                new Class[] {long.class},
                l_mainAccountImpl
                );
            
            WEB3GentradeSubAccount l_gentradeSubAccount = new WEB3GentradeSubAccount(l_mainAccountImpl, TestDBUtility.getSubAccountRow());
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.gentrade.WEB3GentradeMainAccount", "getSubAccount", new Class[]
                { SubAccountTypeEnum.class },
                l_gentradeSubAccount
                );
            
            WEB3GentradeTradingClendarContext l_context =
                new WEB3GentradeTradingClendarContext();

            //取引カレンダコンテキスト.証券会社コード = OpLoginSecurityServiceより編集
            l_context.setInstitutionCode("0D");
            //取引カレンダコンテキスト.部店コード = OpLoginSecurityServiceより編集
            l_context.setBranchCode("381");
            //取引カレンダコンテキスト.市場コード = ”0：DEFAULT”
            l_context.setMarketCode(WEB3MarketCodeDef.DEFAULT);
            //取引カレンダコンテキスト.受付時間区分 = ”00：その他”
            l_context.setTradingTimeType(WEB3TradingTimeTypeDef.DEFAULT);
            //取引カレンダコンテキスト.商品コード = ”0：DEFAULT”
            l_context.setProductCode(WEB3ProductCodeDef.DEFAULT);
            //取引カレンダコンテキスト.注文受付商品 = ”22：顧客サービス”
            l_context.setOrderAcceptProduct(WEB3OrderAccProductDef.ACCOUNT_SERVICE);
            //取引カレンダコンテキスト.注文受付トランザクション = ”00：DEFAULT”
            l_context.setOrderAcceptTransaction(WEB3OrderAccTransactionDef.DEFAULT);

            //－ThreadLocalSystemAttributesRegistry.setAttribute( )にて取引時間コンテキストをセットする。
            //設定キー： 取引時間管理.TRADING_CAL_CONTEXT_PATH
            ThreadLocalSystemAttributesRegistry.setAttribute(
                WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH, l_context);
            
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            TestDBUtility.deleteAll(MainAccountParams.TYPE);
            TestDBUtility.insertWithDel(l_mainAccountParams);
            
            OrderAcceptStatusParams l_orderAcceptStatusParams = TestDBUtility.getOrderAcceptStatusRow();
            l_orderAcceptStatusParams.setInstitutionCode("0D");
            l_orderAcceptStatusParams.setBranchCode("381");
            l_orderAcceptStatusParams.setOrderAccProduct("22");
            l_orderAcceptStatusParams.setOrderAccTransaction("00");
            l_orderAcceptStatusParams.setOrderAcceptStatus("1");
            TestDBUtility.deleteAll(OrderAcceptStatusParams.TYPE);
            TestDBUtility.insertWithDel(l_orderAcceptStatusParams);
            
            WEB3InformPTSAccOpenApplyServiceImpl impl = new WEB3InformPTSAccOpenApplyServiceImpl();
            impl.getInputScreen(request);

            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);

            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00011, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }

    public void testGetInputScreen_0003()
    {
        final String STR_METHOD_NAME = "testGetInputScreen_0003()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        WEB3InformPTSAccOpenApplyInpRequest request = new WEB3InformPTSAccOpenApplyInpRequest();
        request.informType = "123";
        
        try
        {
            TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getAccountId",
                new Class[] {},
                new Long("123")
                );
            
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setPtsAccOpenDiv("1");
            WEB3GentradeMainAccount l_mainAccountImpl = new WEB3GentradeMainAccount(l_mainAccountParams);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.gentrade.WEB3GentradeAccountManager",
                "getMainAccount",
                new Class[] {long.class},
                l_mainAccountImpl
                );
            
            WEB3GentradeSubAccount l_gentradeSubAccount = new WEB3GentradeSubAccount(l_mainAccountImpl, TestDBUtility.getSubAccountRow());
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.gentrade.WEB3GentradeMainAccount", "getSubAccount", new Class[]
                { SubAccountTypeEnum.class },
                l_gentradeSubAccount
                );
            
            WEB3GentradeTradingTimeManagementForMock.mockValidateOrderAccept(true);
            ProcessingResult l_ProcessingResult =
                ProcessingResult.newSuccessResultInstance();
            
            TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);
            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
            
            WEB3GentradeOrderValidatorForMock l_orderalidateor =
                new WEB3GentradeOrderValidatorForMock();
            l_finApp.overrideCommonOrderValidator(l_orderalidateor);
            
            OrderValidationResult l_result = new OrderValidationResult(l_ProcessingResult);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.gentrade.WEB3GentradeOrderValidator",
                "validateSubAccountForTrading",
                new Class[] {SubAccount.class},
                l_result);
            
            WEB3InformPTSAccOpenApplyServiceImpl impl = new WEB3InformPTSAccOpenApplyServiceImpl();
            impl.getInputScreen(request);

            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);

            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_03024, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }

    public void testGetInputScreen_0004()
    {
        final String STR_METHOD_NAME = "testGetInputScreen_0004()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        WEB3InformPTSAccOpenApplyInpRequest request = new WEB3InformPTSAccOpenApplyInpRequest();
        request.informType = "12";
        
        try
        {
            TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getAccountId",
                new Class[] {},
                new Long("123")
                );
            
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setPtsAccOpenDiv("2");
            WEB3GentradeMainAccount l_mainAccountImpl = new WEB3GentradeMainAccount(l_mainAccountParams);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.gentrade.WEB3GentradeAccountManager",
                "getMainAccount",
                new Class[] {long.class},
                l_mainAccountImpl
                );
            
            WEB3GentradeSubAccount l_gentradeSubAccount = new WEB3GentradeSubAccount(l_mainAccountImpl, TestDBUtility.getSubAccountRow());
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.gentrade.WEB3GentradeMainAccount", "getSubAccount", new Class[]
                { SubAccountTypeEnum.class },
                l_gentradeSubAccount
                );
            
            WEB3GentradeTradingTimeManagementForMock.mockValidateOrderAccept(true);
            ProcessingResult l_ProcessingResult =
                ProcessingResult.newSuccessResultInstance();
            
            TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);
            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
            
            WEB3GentradeOrderValidatorForMock l_orderalidateor =
                new WEB3GentradeOrderValidatorForMock();
            l_finApp.overrideCommonOrderValidator(l_orderalidateor);
            
            OrderValidationResult l_result = new OrderValidationResult(l_ProcessingResult);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.gentrade.WEB3GentradeOrderValidator",
                "validateSubAccountForTrading",
                new Class[] {SubAccount.class},
                l_result);
            
            VariousInformParams l_variousInformParams = TestDBUtility.getVariousInformRow();
            l_variousInformParams.setInstitutionCode("0D");
            l_variousInformParams.setBranchCode("381");
            l_variousInformParams.setInformDiv("12");
            l_variousInformParams.setAccountCode("2512246");
            l_variousInformParams.setExtDiv1("1");
            l_variousInformParams.setExtDiv2("0");
            TestDBUtility.deleteAll(VariousInformParams.TYPE);
            TestDBUtility.insertWithDel(l_variousInformParams);
            
            WEB3InformPTSAccOpenApplyServiceImpl impl = new WEB3InformPTSAccOpenApplyServiceImpl();
            impl.getInputScreen(request);

            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);

            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_03025, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }

    public void testGetInputScreen_0005()
    {
        final String STR_METHOD_NAME = "testGetInputScreen_0005()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        WEB3InformPTSAccOpenApplyInpRequest request = new WEB3InformPTSAccOpenApplyInpRequest();
        request.informType = "12";
        
        try
        {
            TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getAccountId",
                new Class[] {},
                new Long("123")
                );
            
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setPtsAccOpenDiv("2");
            WEB3GentradeMainAccount l_mainAccountImpl = new WEB3GentradeMainAccount(l_mainAccountParams);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.gentrade.WEB3GentradeAccountManager",
                "getMainAccount",
                new Class[] {long.class},
                l_mainAccountImpl
                );
            
            WEB3GentradeSubAccount l_gentradeSubAccount = new WEB3GentradeSubAccount(l_mainAccountImpl, TestDBUtility.getSubAccountRow());
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.gentrade.WEB3GentradeMainAccount", "getSubAccount", new Class[]
                { SubAccountTypeEnum.class },
                l_gentradeSubAccount
                );
            
            WEB3GentradeTradingTimeManagementForMock.mockValidateOrderAccept(true);
            ProcessingResult l_ProcessingResult =
                ProcessingResult.newSuccessResultInstance();
            
            TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);
            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
            
            WEB3GentradeOrderValidatorForMock l_orderalidateor =
                new WEB3GentradeOrderValidatorForMock();
            l_finApp.overrideCommonOrderValidator(l_orderalidateor);
            
            OrderValidationResult l_result = new OrderValidationResult(l_ProcessingResult);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.gentrade.WEB3GentradeOrderValidator",
                "validateSubAccountForTrading",
                new Class[] {SubAccount.class},
                l_result);
            
            VariousInformParams l_variousInformParams = TestDBUtility.getVariousInformRow();
            l_variousInformParams.setInstitutionCode("0D");
            l_variousInformParams.setBranchCode("381");
            l_variousInformParams.setInformDiv("12");
            l_variousInformParams.setAccountCode("2512246");
            l_variousInformParams.setExtDiv1("1");
            l_variousInformParams.setExtDiv2("1");
            TestDBUtility.deleteAll(VariousInformParams.TYPE);
            TestDBUtility.insertWithDel(l_variousInformParams);

            TestDBUtility.deleteAll(QuestionParams.TYPE);
            
            WEB3InformPTSAccOpenApplyServiceImpl impl = new WEB3InformPTSAccOpenApplyServiceImpl();
            WEB3InformPTSAccOpenApplyInpResponse response = impl.getInputScreen(request);

            assertEquals(null, response.ptsTradeAgreementList);
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }

    public void testGetInputScreen_0006()
    {
        final String STR_METHOD_NAME = "testGetInputScreen_0006()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        WEB3InformPTSAccOpenApplyInpRequest request = new WEB3InformPTSAccOpenApplyInpRequest();
        request.informType = "12";
        
        try
        {
            TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getAccountId",
                new Class[] {},
                new Long("123")
                );
            
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setPtsAccOpenDiv("2");
            WEB3GentradeMainAccount l_mainAccountImpl = new WEB3GentradeMainAccount(l_mainAccountParams);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.gentrade.WEB3GentradeAccountManager",
                "getMainAccount",
                new Class[] {long.class},
                l_mainAccountImpl
                );
            
            WEB3GentradeSubAccount l_gentradeSubAccount = new WEB3GentradeSubAccount(l_mainAccountImpl, TestDBUtility.getSubAccountRow());
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.gentrade.WEB3GentradeMainAccount", "getSubAccount", new Class[]
                { SubAccountTypeEnum.class },
                l_gentradeSubAccount
                );
            
            WEB3GentradeTradingTimeManagementForMock.mockValidateOrderAccept(true);
            ProcessingResult l_ProcessingResult =
                ProcessingResult.newSuccessResultInstance();
            
            TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);
            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
            
            WEB3GentradeOrderValidatorForMock l_orderalidateor =
                new WEB3GentradeOrderValidatorForMock();
            l_finApp.overrideCommonOrderValidator(l_orderalidateor);
            
            OrderValidationResult l_result = new OrderValidationResult(l_ProcessingResult);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.gentrade.WEB3GentradeOrderValidator",
                "validateSubAccountForTrading",
                new Class[] {SubAccount.class},
                l_result);
            
            VariousInformParams l_variousInformParams = TestDBUtility.getVariousInformRow();
            l_variousInformParams.setInstitutionCode("0D");
            l_variousInformParams.setBranchCode("381");
            l_variousInformParams.setInformDiv("12");
            l_variousInformParams.setAccountCode("2512246");
            l_variousInformParams.setExtDiv1("1");
            l_variousInformParams.setExtDiv2("1");
            TestDBUtility.deleteAll(VariousInformParams.TYPE);
            TestDBUtility.insertWithDel(l_variousInformParams);
            
            QuestionParams l_questionParams = new QuestionParams();
            l_questionParams.setInstitutionCode("0D");
            l_questionParams.setBranchCode("381");
            l_questionParams.setQuestionDiv("0003");
            l_questionParams.setQuestionNo("123");
            l_questionParams.setQuestion("abcdef");
            l_questionParams.setLastUpdater("001");
            l_questionParams.setCreatedTimestamp(new Date());
            l_questionParams.setLastUpdatedTimestamp(new Date());
            TestDBUtility.deleteAll(QuestionParams.TYPE);
            TestDBUtility.insertWithDel(l_questionParams);
            
            WEB3InformPTSAccOpenApplyServiceImpl impl = new WEB3InformPTSAccOpenApplyServiceImpl();
            WEB3InformPTSAccOpenApplyInpResponse response = impl.getInputScreen(request);

            assertEquals(1, response.ptsTradeAgreementList.length);
            assertEquals("123", response.ptsTradeAgreementList[0].questionNumber);
            assertEquals("abcdef", response.ptsTradeAgreementList[0].questionContents);
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }

    public void testGetInputScreen_0007()
    {
        final String STR_METHOD_NAME = "testGetInputScreen_0007()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        WEB3InformPTSAccOpenApplyInpRequest request = new WEB3InformPTSAccOpenApplyInpRequest();
        request.informType = "12";
        
        try
        {
            TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getAccountId",
                new Class[] {},
                new Long("123")
                );
            
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setPtsAccOpenDiv("2");
            WEB3GentradeMainAccount l_mainAccountImpl = new WEB3GentradeMainAccount(l_mainAccountParams);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.gentrade.WEB3GentradeAccountManager",
                "getMainAccount",
                new Class[] {long.class},
                l_mainAccountImpl
                );
            
            WEB3GentradeSubAccount l_gentradeSubAccount = new WEB3GentradeSubAccount(l_mainAccountImpl, TestDBUtility.getSubAccountRow());
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.gentrade.WEB3GentradeMainAccount", "getSubAccount", new Class[]
                { SubAccountTypeEnum.class },
                l_gentradeSubAccount
                );
            
            WEB3GentradeTradingTimeManagementForMock.mockValidateOrderAccept(true);
            ProcessingResult l_ProcessingResult =
                ProcessingResult.newSuccessResultInstance();
            
            TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);
            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
            
            WEB3GentradeOrderValidatorForMock l_orderalidateor =
                new WEB3GentradeOrderValidatorForMock();
            l_finApp.overrideCommonOrderValidator(l_orderalidateor);
            
            OrderValidationResult l_result = new OrderValidationResult(l_ProcessingResult);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.gentrade.WEB3GentradeOrderValidator",
                "validateSubAccountForTrading",
                new Class[] {SubAccount.class},
                l_result);
            
            VariousInformParams l_variousInformParams = TestDBUtility.getVariousInformRow();
            l_variousInformParams.setInstitutionCode("0D");
            l_variousInformParams.setBranchCode("381");
            l_variousInformParams.setInformDiv("12");
            l_variousInformParams.setAccountCode("2512246");
            l_variousInformParams.setExtDiv1("1");
            l_variousInformParams.setExtDiv2("1");
            TestDBUtility.deleteAll(VariousInformParams.TYPE);
            TestDBUtility.insertWithDel(l_variousInformParams);
            
            QuestionParams l_questionParams = this.getQuestionParams();

            QuestionParams l_questionParams1 = this.getQuestionParams();
            l_questionParams1.setQuestionNo("124");
            QuestionParams l_questionParams2 = this.getQuestionParams();
            l_questionParams2.setQuestionNo("125");
            TestDBUtility.deleteAll(QuestionParams.TYPE);
            TestDBUtility.insertWithDel(l_questionParams);
            TestDBUtility.insertWithDel(l_questionParams1);
            TestDBUtility.insertWithDel(l_questionParams2);
            
            WEB3InformPTSAccOpenApplyServiceImpl impl = new WEB3InformPTSAccOpenApplyServiceImpl();
            WEB3InformPTSAccOpenApplyInpResponse response = impl.getInputScreen(request);

            assertEquals(3, response.ptsTradeAgreementList.length);

            assertEquals("123", response.ptsTradeAgreementList[0].questionNumber);
            assertEquals("abcdef", response.ptsTradeAgreementList[0].questionContents);

            assertEquals("124", response.ptsTradeAgreementList[1].questionNumber);
            assertEquals("abcdef", response.ptsTradeAgreementList[1].questionContents);

            assertEquals("125", response.ptsTradeAgreementList[2].questionNumber);
            assertEquals("abcdef", response.ptsTradeAgreementList[2].questionContents);
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }

    public void testSubmitApply_0001()
    {
        final String STR_METHOD_NAME = "testSubmitApply_0001()";
        log.entering(TEST_START + STR_METHOD_NAME);

        WEB3InformPTSAccOpenApplyCmpRequest request = new WEB3InformPTSAccOpenApplyCmpRequest();
        WEB3InformPTSAccOpenApplyServiceImpl impl = new WEB3InformPTSAccOpenApplyServiceImpl();
        
        try
        {
            impl.submitApply(request);
            
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_01817, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME);
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testSubmitApply_0002()
    {
        final String STR_METHOD_NAME = "testSubmitApply_0002()";
        log.entering(TEST_START + STR_METHOD_NAME);

        WEB3InformPTSAccOpenApplyCmpRequest request = new WEB3InformPTSAccOpenApplyCmpRequest();
        request.informType = "12";
        request.ptsAccOpenDiv = "11";
        WEB3InformPTSTradeAgreementUnit[] ptsTradeAgreementList = new WEB3InformPTSTradeAgreementUnit[0];
        request.ptsTradeAgreementList = ptsTradeAgreementList;
        WEB3InformPTSAccOpenApplyServiceImpl impl = new WEB3InformPTSAccOpenApplyServiceImpl();
        
        try
        {
            TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getAccountId",
                new Class[] {},
                new Long("123")
                );
            
            MainAccountImpl l_mainAccountImpl = new MainAccountImpl(TestDBUtility.getMainAccountRow());
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.gentrade.WEB3GentradeAccountManager",
                "getMainAccount",
                new Class[] {long.class},
                l_mainAccountImpl
                );
            
            WEB3GentradeSubAccount l_gentradeSubAccount = new WEB3GentradeSubAccount(l_mainAccountImpl, TestDBUtility.getSubAccountRow());
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.gentrade.WEB3GentradeMainAccount", "getSubAccount", new Class[]
                { SubAccountTypeEnum.class },
                l_gentradeSubAccount
                );
            
            WEB3GentradeTradingClendarContext l_context =
                new WEB3GentradeTradingClendarContext();

            //取引カレンダコンテキスト.証券会社コード = OpLoginSecurityServiceより編集
            l_context.setInstitutionCode("0D");
            //取引カレンダコンテキスト.部店コード = OpLoginSecurityServiceより編集
            l_context.setBranchCode("381");
            //取引カレンダコンテキスト.市場コード = ”0：DEFAULT”
            l_context.setMarketCode(WEB3MarketCodeDef.DEFAULT);
            //取引カレンダコンテキスト.受付時間区分 = ”00：その他”
            l_context.setTradingTimeType(WEB3TradingTimeTypeDef.DEFAULT);
            //取引カレンダコンテキスト.商品コード = ”0：DEFAULT”
            l_context.setProductCode(WEB3ProductCodeDef.DEFAULT);
            //取引カレンダコンテキスト.注文受付商品 = ”22：顧客サービス”
            l_context.setOrderAcceptProduct(WEB3OrderAccProductDef.ACCOUNT_SERVICE);
            //取引カレンダコンテキスト.注文受付トランザクション = ”00：DEFAULT”
            l_context.setOrderAcceptTransaction(WEB3OrderAccTransactionDef.DEFAULT);

            //－ThreadLocalSystemAttributesRegistry.setAttribute( )にて取引時間コンテキストをセットする。
            //設定キー： 取引時間管理.TRADING_CAL_CONTEXT_PATH
            ThreadLocalSystemAttributesRegistry.setAttribute(
                WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH, l_context);
            
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            TestDBUtility.deleteAll(MainAccountParams.TYPE);
            TestDBUtility.insertWithDel(l_mainAccountParams);
            
            OrderAcceptStatusParams l_orderAcceptStatusParams = TestDBUtility.getOrderAcceptStatusRow();
            l_orderAcceptStatusParams.setInstitutionCode("0D");
            l_orderAcceptStatusParams.setBranchCode("381");
            l_orderAcceptStatusParams.setOrderAccProduct("22");
            l_orderAcceptStatusParams.setOrderAccTransaction("00");
            l_orderAcceptStatusParams.setOrderAcceptStatus("1");
            TestDBUtility.deleteAll(OrderAcceptStatusParams.TYPE);
            TestDBUtility.insertWithDel(l_orderAcceptStatusParams);

            impl.submitApply(request);
            
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00011, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME);
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }

    public void testSubmitApply_0003()
    {
        final String STR_METHOD_NAME = "testSubmitApply_0003()";
        log.entering(TEST_START + STR_METHOD_NAME);

        WEB3InformPTSAccOpenApplyCmpRequest request = new WEB3InformPTSAccOpenApplyCmpRequest();
        request.informType = "12";
        request.ptsAccOpenDiv = "11";
        WEB3InformPTSTradeAgreementUnit[] ptsTradeAgreementList = new WEB3InformPTSTradeAgreementUnit[0];
        request.ptsTradeAgreementList = ptsTradeAgreementList;
        WEB3InformPTSAccOpenApplyServiceImpl impl = new WEB3InformPTSAccOpenApplyServiceImpl();
        
        try
        {
            TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getAccountId",
                new Class[] {},
                new Long("123")
                );
            
            LoginInfoImpl l_loginInfoImpl = new LoginInfoImpl();
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getLoginInfo",
                new Class[] {},
                l_loginInfoImpl
                );
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.LoginInfoImpl",
                "getLoginId",
                new Class[] {},
                new Long("111")
                );
            
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setPtsAccOpenDiv("1");
            WEB3GentradeMainAccount l_mainAccountImpl = new WEB3GentradeMainAccount(l_mainAccountParams);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.gentrade.WEB3GentradeAccountManager",
                "getMainAccount",
                new Class[] {long.class},
                l_mainAccountImpl
                );
            
            WEB3GentradeSubAccount l_gentradeSubAccount = new WEB3GentradeSubAccount(l_mainAccountImpl, TestDBUtility.getSubAccountRow());
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.gentrade.WEB3GentradeMainAccount", "getSubAccount", new Class[]
                { SubAccountTypeEnum.class },
                l_gentradeSubAccount
                );
            
            WEB3GentradeTradingTimeManagementForMock.mockValidateOrderAccept(true);
            ProcessingResult l_ProcessingResult0 =
                ProcessingResult.newSuccessResultInstance();
            ProcessingResult l_ProcessingResult =
                ProcessingResult.newFailedResultInstance(WEB3ErrorCatalog.BUSINESS_ERROR_00004);
            
            TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);
            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
            
            WEB3GentradeOrderValidatorForMock l_orderalidateor =
                new WEB3GentradeOrderValidatorForMock();
            l_finApp.overrideCommonOrderValidator(l_orderalidateor);
            
            OrderValidationResult l_result0 = new OrderValidationResult(l_ProcessingResult0);
            OrderValidationResult l_result = new OrderValidationResult(l_ProcessingResult);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.gentrade.WEB3GentradeOrderValidator",
                "validateSubAccountForTrading",
                new Class[] {SubAccount.class},
                l_result0);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.gentrade.WEB3GentradeOrderValidator",
                "validateTradingPassword",
                new Class[] { Trader.class, SubAccount.class, String.class },
                l_result);

            impl.submitApply(request);
            
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00004, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testSubmitApply_0004()
    {
        final String STR_METHOD_NAME = "testSubmitApply_0004()";
        log.entering(TEST_START + STR_METHOD_NAME);

        WEB3InformPTSAccOpenApplyCmpRequest request = new WEB3InformPTSAccOpenApplyCmpRequest();
        request.informType = "12";
        request.ptsAccOpenDiv = "11";
        WEB3InformPTSTradeAgreementUnit[] ptsTradeAgreementList = new WEB3InformPTSTradeAgreementUnit[0];
        request.ptsTradeAgreementList = ptsTradeAgreementList;
        WEB3InformPTSAccOpenApplyServiceImpl impl = new WEB3InformPTSAccOpenApplyServiceImpl();
        
        try
        {
            TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getAccountId",
                new Class[] {},
                new Long("123")
                );
            
            LoginInfoImpl l_loginInfoImpl = new LoginInfoImpl();
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getLoginInfo",
                new Class[] {},
                l_loginInfoImpl
                );
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.LoginInfoImpl",
                "getLoginId",
                new Class[] {},
                new Long("111")
                );
            
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setPtsAccOpenDiv("1");
            WEB3GentradeMainAccount l_mainAccountImpl = new WEB3GentradeMainAccount(l_mainAccountParams);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.gentrade.WEB3GentradeAccountManager",
                "getMainAccount",
                new Class[] {long.class},
                l_mainAccountImpl
                );
            
            WEB3GentradeSubAccount l_gentradeSubAccount = new WEB3GentradeSubAccount(l_mainAccountImpl, TestDBUtility.getSubAccountRow());
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.gentrade.WEB3GentradeMainAccount", "getSubAccount", new Class[]
                { SubAccountTypeEnum.class },
                l_gentradeSubAccount
                );
            
            WEB3GentradeTradingTimeManagementForMock.mockValidateOrderAccept(true);
            ProcessingResult l_ProcessingResult0 =
                ProcessingResult.newSuccessResultInstance();
            
            TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);
            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
            
            WEB3GentradeOrderValidatorForMock l_orderalidateor =
                new WEB3GentradeOrderValidatorForMock();
            l_finApp.overrideCommonOrderValidator(l_orderalidateor);
            
            OrderValidationResult l_result0 = new OrderValidationResult(l_ProcessingResult0);
            OrderValidationResult l_result = new OrderValidationResult(l_ProcessingResult0);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.gentrade.WEB3GentradeOrderValidator",
                "validateSubAccountForTrading",
                new Class[] {SubAccount.class},
                l_result0);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.gentrade.WEB3GentradeOrderValidator",
                "validateTradingPassword",
                new Class[] { Trader.class, SubAccount.class, String.class },
                l_result);

            impl.submitApply(request);
            
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_03024, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testSubmitApply_0005()
    {
        final String STR_METHOD_NAME = "testSubmitApply_0005()";
        log.entering(TEST_START + STR_METHOD_NAME);

        WEB3InformPTSAccOpenApplyCmpRequest request = new WEB3InformPTSAccOpenApplyCmpRequest();
        request.informType = "12";
        request.ptsAccOpenDiv = "11";
        WEB3InformPTSTradeAgreementUnit[] ptsTradeAgreementList = new WEB3InformPTSTradeAgreementUnit[0];
        request.ptsTradeAgreementList = ptsTradeAgreementList;
        WEB3InformPTSAccOpenApplyServiceImpl impl = new WEB3InformPTSAccOpenApplyServiceImpl();
        
        try
        {
            TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getAccountId",
                new Class[] {},
                new Long("123")
                );
            
            LoginInfoImpl l_loginInfoImpl = new LoginInfoImpl();
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getLoginInfo",
                new Class[] {},
                l_loginInfoImpl
                );
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.LoginInfoImpl",
                "getLoginId",
                new Class[] {},
                new Long("111")
                );
            
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setPtsAccOpenDiv("2");
            WEB3GentradeMainAccount l_mainAccountImpl = new WEB3GentradeMainAccount(l_mainAccountParams);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.gentrade.WEB3GentradeAccountManager",
                "getMainAccount",
                new Class[] {long.class},
                l_mainAccountImpl
                );
            
            WEB3GentradeSubAccount l_gentradeSubAccount = new WEB3GentradeSubAccount(l_mainAccountImpl, TestDBUtility.getSubAccountRow());
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.gentrade.WEB3GentradeMainAccount", "getSubAccount", new Class[]
                { SubAccountTypeEnum.class },
                l_gentradeSubAccount
                );
            
            WEB3GentradeTradingTimeManagementForMock.mockValidateOrderAccept(true);
            ProcessingResult l_ProcessingResult0 =
                ProcessingResult.newSuccessResultInstance();
            
            TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);
            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
            
            WEB3GentradeOrderValidatorForMock l_orderalidateor =
                new WEB3GentradeOrderValidatorForMock();
            l_finApp.overrideCommonOrderValidator(l_orderalidateor);
            
            OrderValidationResult l_result0 = new OrderValidationResult(l_ProcessingResult0);
            OrderValidationResult l_result = new OrderValidationResult(l_ProcessingResult0);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.gentrade.WEB3GentradeOrderValidator",
                "validateSubAccountForTrading",
                new Class[] {SubAccount.class},
                l_result0);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.gentrade.WEB3GentradeOrderValidator",
                "validateTradingPassword",
                new Class[] { Trader.class, SubAccount.class, String.class },
                l_result);

            VariousInformParams l_variousInformParams = TestDBUtility.getVariousInformRow();
            l_variousInformParams.setInstitutionCode("0D");
            l_variousInformParams.setBranchCode("381");
            l_variousInformParams.setInformDiv("12");
            l_variousInformParams.setAccountCode("2512246");
            l_variousInformParams.setExtDiv1("1");
            l_variousInformParams.setExtDiv2("0");
            TestDBUtility.deleteAll(VariousInformParams.TYPE);
            TestDBUtility.insertWithDel(l_variousInformParams);

            TestDBUtility.deleteAll(QuestionParams.TYPE);

            
            impl.submitApply(request);
            
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_03025, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testSubmitApply_0006()
    {
        final String STR_METHOD_NAME = "testSubmitApply_0006()";
        log.entering(TEST_START + STR_METHOD_NAME);

        WEB3InformPTSAccOpenApplyCmpRequest request = new WEB3InformPTSAccOpenApplyCmpRequest();
        request.informType = "12";
        request.ptsAccOpenDiv = "11";
        WEB3InformPTSTradeAgreementUnit[] ptsTradeAgreementList = new WEB3InformPTSTradeAgreementUnit[1];
        WEB3InformPTSTradeAgreementUnit unit = new WEB3InformPTSTradeAgreementUnit();
        unit.questionAnswer = "2";
        unit.questionNumber = "1";
        ptsTradeAgreementList[0] = unit;
        request.ptsTradeAgreementList = ptsTradeAgreementList;
        WEB3InformPTSAccOpenApplyServiceImpl impl = new WEB3InformPTSAccOpenApplyServiceImpl();
        
        try
        {
            TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getAccountId",
                new Class[] {},
                new Long("123")
                );
            
            LoginInfoImpl l_loginInfoImpl = new LoginInfoImpl();
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getLoginInfo",
                new Class[] {},
                l_loginInfoImpl
                );
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.LoginInfoImpl",
                "getLoginId",
                new Class[] {},
                new Long("111")
                );
            
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setPtsAccOpenDiv("2");
            WEB3GentradeMainAccount l_mainAccountImpl = new WEB3GentradeMainAccount(l_mainAccountParams);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.gentrade.WEB3GentradeAccountManager",
                "getMainAccount",
                new Class[] {long.class},
                l_mainAccountImpl
                );
            
            WEB3GentradeSubAccount l_gentradeSubAccount = new WEB3GentradeSubAccount(l_mainAccountImpl, TestDBUtility.getSubAccountRow());
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.gentrade.WEB3GentradeMainAccount", "getSubAccount", new Class[]
                { SubAccountTypeEnum.class },
                l_gentradeSubAccount
                );
            
            WEB3GentradeTradingTimeManagementForMock.mockValidateOrderAccept(true);
            ProcessingResult l_ProcessingResult0 =
                ProcessingResult.newSuccessResultInstance();
            
            TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);
            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
            
            WEB3GentradeOrderValidatorForMock l_orderalidateor =
                new WEB3GentradeOrderValidatorForMock();
            l_finApp.overrideCommonOrderValidator(l_orderalidateor);
            
            OrderValidationResult l_result0 = new OrderValidationResult(l_ProcessingResult0);
            OrderValidationResult l_result = new OrderValidationResult(l_ProcessingResult0);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.gentrade.WEB3GentradeOrderValidator",
                "validateSubAccountForTrading",
                new Class[] {SubAccount.class},
                l_result0);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.gentrade.WEB3GentradeOrderValidator",
                "validateTradingPassword",
                new Class[] { Trader.class, SubAccount.class, String.class },
                l_result);

            VariousInformParams l_variousInformParams = TestDBUtility.getVariousInformRow();
            l_variousInformParams.setInstitutionCode("0D");
            l_variousInformParams.setBranchCode("381");
            l_variousInformParams.setInformDiv("12");
            l_variousInformParams.setAccountCode("2512246");
            l_variousInformParams.setExtDiv1("1");
            l_variousInformParams.setExtDiv2("1");
            TestDBUtility.deleteAll(VariousInformParams.TYPE);
            TestDBUtility.insertWithDel(l_variousInformParams);

            TestDBUtility.deleteAll(QuestionParams.TYPE);

            
            impl.submitApply(request);
            
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_03026, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testSubmitApply_0007()
    {
        final String STR_METHOD_NAME = "testSubmitApply_0007()";
        log.entering(TEST_START + STR_METHOD_NAME);

        WEB3InformPTSAccOpenApplyCmpRequest request = new WEB3InformPTSAccOpenApplyCmpRequest();
        request.informType = "12";
        request.ptsAccOpenDiv = "1";
        WEB3InformPTSTradeAgreementUnit[] ptsTradeAgreementList = new WEB3InformPTSTradeAgreementUnit[1];
        WEB3InformPTSTradeAgreementUnit unit = new WEB3InformPTSTradeAgreementUnit();
        unit.questionAnswer = "1";
        unit.questionNumber = "1";
        ptsTradeAgreementList[0] = unit;
        request.ptsTradeAgreementList = ptsTradeAgreementList;
        WEB3InformPTSAccOpenApplyServiceImpl impl = new WEB3InformPTSAccOpenApplyServiceImpl();
        
        try
        {
            TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getAccountId",
                new Class[] {},
                new Long("123")
                );
            
            LoginInfoImpl l_loginInfoImpl = new LoginInfoImpl();
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getLoginInfo",
                new Class[] {},
                l_loginInfoImpl
                );
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.LoginInfoImpl",
                "getLoginId",
                new Class[] {},
                new Long("111")
                );
            
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setPtsAccOpenDiv("2");
            WEB3GentradeMainAccount l_mainAccountImpl = new WEB3GentradeMainAccount(l_mainAccountParams);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.gentrade.WEB3GentradeAccountManager",
                "getMainAccount",
                new Class[] {long.class},
                l_mainAccountImpl
                );
            
            WEB3GentradeSubAccount l_gentradeSubAccount = new WEB3GentradeSubAccount(l_mainAccountImpl, TestDBUtility.getSubAccountRow());
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.gentrade.WEB3GentradeMainAccount", "getSubAccount", new Class[]
                { SubAccountTypeEnum.class },
                l_gentradeSubAccount
                );
            
            WEB3GentradeTradingTimeManagementForMock.mockValidateOrderAccept(true);
            ProcessingResult l_ProcessingResult0 =
                ProcessingResult.newSuccessResultInstance();
            
            TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);
            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
            
            WEB3GentradeOrderValidatorForMock l_orderalidateor =
                new WEB3GentradeOrderValidatorForMock();
            l_finApp.overrideCommonOrderValidator(l_orderalidateor);
            
            OrderValidationResult l_result0 = new OrderValidationResult(l_ProcessingResult0);
            OrderValidationResult l_result = new OrderValidationResult(l_ProcessingResult0);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.gentrade.WEB3GentradeOrderValidator",
                "validateSubAccountForTrading",
                new Class[] {SubAccount.class},
                l_result0);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.gentrade.WEB3GentradeOrderValidator",
                "validateTradingPassword",
                new Class[] { Trader.class, SubAccount.class, String.class },
                l_result);

            VariousInformParams l_variousInformParams = TestDBUtility.getVariousInformRow();
            l_variousInformParams.setInstitutionCode("0D");
            l_variousInformParams.setBranchCode("381");
            l_variousInformParams.setInformDiv("12");
            l_variousInformParams.setAccountCode("2512246");
            l_variousInformParams.setExtDiv1("1");
            l_variousInformParams.setExtDiv2("1");
            TestDBUtility.deleteAll(VariousInformParams.TYPE);
            TestDBUtility.insertWithDel(l_variousInformParams);

            TestDBUtility.deleteAll(QuestionParams.TYPE);
            TestDBUtility.deleteAll(QuestionAnswerParams.TYPE);
            TestDBUtility.deleteAll(TraderParams.TYPE);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.gentrade.WEB3HostReqOrderNumberManageServiceImpl",
                "getNewNumber", 
                new Class[]{ String.class, String.class, ProductTypeEnum.class },
                "12"
                );
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.inform.service.delegate.stdimpls.WEB3InformHostReqOrderNumberManageServiceImpl",
                "getNewOrderRequestCode", new Class[]
                { String.class, String.class },
                "22"
                );
            
            impl.submitApply(request);

            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            List l_lisQuestionAnswerParams = l_queryProcessor.doFindAllQuery(QuestionAnswerParams.TYPE);
            QuestionAnswerRow l_questionAnswerRow = (QuestionAnswerRow)(l_lisQuestionAnswerParams.get(0));
            assertEquals(1, l_lisQuestionAnswerParams.size());
            assertEquals("0D", l_questionAnswerRow.getInstitutionCode());
            assertEquals("381", l_questionAnswerRow.getBranchCode());
            assertEquals("12", l_questionAnswerRow.getOrderRequestNumber());
            assertEquals("1", l_questionAnswerRow.getQuestionAnswer());
            assertEquals("1", l_questionAnswerRow.getQuestionNo());

            StringBuffer l_sbUpdateSql = new StringBuffer();
            l_sbUpdateSql.append(" institution_code = ? ");
            l_sbUpdateSql.append(" and inform_div = ? ");
//            l_sbUpdateSql.append(" and request_number = ? ");
            l_sbUpdateSql.append(" and branch_code = ? ");

            Object[] l_updateValues = new Object[]{
                l_variousInformParams.getInstitutionCode(),
                l_variousInformParams.getInformDiv(),
//                l_variousInformParams.getRequestNumber(),
                l_variousInformParams.getBranchCode()};
            List l_lisVariousInformRow = l_queryProcessor.doFindAllQuery(
                VariousInformRow.TYPE,
                l_sbUpdateSql.toString(),
                l_updateValues);
            VariousInformParams l_variousInformParamsQuery = (VariousInformParams)(l_lisVariousInformRow.get(0));
            assertEquals(2, l_lisVariousInformRow.size());
            assertEquals("0", l_variousInformParamsQuery.getExtDiv1());
            assertEquals("2512246", l_variousInformParamsQuery.getLastUpdater());
            VariousInformParams l_variousInformParamsQuery1 = (VariousInformParams)(l_lisVariousInformRow.get(1));
            assertEquals("1", l_variousInformParamsQuery1.getExtDiv1());

            MainAccountPK l_mainAccountPK = new MainAccountPK(333812512246L);
            MainAccountRow l_mainAccountRow =
                (MainAccountRow)l_queryProcessor.doFindByPrimaryKeyQuery(l_mainAccountPK);
            assertEquals("1", l_mainAccountRow.getPtsAccOpenDiv());
            assertEquals("2512246", l_mainAccountRow.getAccountCode());
            assertEquals("251224", l_mainAccountRow.getPtsAccOpenDivLastUpdater());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }

    public void testSubmitApply_0008()
    {
        final String STR_METHOD_NAME = "testSubmitApply_0008()";
        log.entering(TEST_START + STR_METHOD_NAME);

        WEB3InformPTSAccOpenApplyCmpRequest request = new WEB3InformPTSAccOpenApplyCmpRequest();
        request.informType = "12";
        request.ptsAccOpenDiv = "1";
        WEB3InformPTSTradeAgreementUnit[] ptsTradeAgreementList = new WEB3InformPTSTradeAgreementUnit[1];
        WEB3InformPTSTradeAgreementUnit unit = new WEB3InformPTSTradeAgreementUnit();
        unit.questionAnswer = "1";
        unit.questionNumber = "1";
        ptsTradeAgreementList[0] = unit;
        request.ptsTradeAgreementList = ptsTradeAgreementList;
        WEB3InformPTSAccOpenApplyServiceImpl impl = new WEB3InformPTSAccOpenApplyServiceImpl();
        
        try
        {
            TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getAccountId",
                new Class[] {},
                new Long("123")
                );
            
            LoginInfoImpl l_loginInfoImpl = new LoginInfoImpl();
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getLoginInfo",
                new Class[] {},
                l_loginInfoImpl
                );
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.LoginInfoImpl",
                "getLoginId",
                new Class[] {},
                new Long("111")
                );
            
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setPtsAccOpenDiv("2");
            WEB3GentradeMainAccount l_mainAccountImpl = new WEB3GentradeMainAccount(l_mainAccountParams);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.gentrade.WEB3GentradeAccountManager",
                "getMainAccount",
                new Class[] {long.class},
                l_mainAccountImpl
                );
            
            WEB3GentradeSubAccount l_gentradeSubAccount = new WEB3GentradeSubAccount(l_mainAccountImpl, TestDBUtility.getSubAccountRow());
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.gentrade.WEB3GentradeMainAccount", "getSubAccount", new Class[]
                { SubAccountTypeEnum.class },
                l_gentradeSubAccount
                );
            
            WEB3GentradeTradingTimeManagementForMock.mockValidateOrderAccept(true);
            ProcessingResult l_ProcessingResult0 =
                ProcessingResult.newSuccessResultInstance();
            
            TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);
            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
            
            WEB3GentradeOrderValidatorForMock l_orderalidateor =
                new WEB3GentradeOrderValidatorForMock();
            l_finApp.overrideCommonOrderValidator(l_orderalidateor);
            
            OrderValidationResult l_result0 = new OrderValidationResult(l_ProcessingResult0);
            OrderValidationResult l_result = new OrderValidationResult(l_ProcessingResult0);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.gentrade.WEB3GentradeOrderValidator",
                "validateSubAccountForTrading",
                new Class[] {SubAccount.class},
                l_result0);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.gentrade.WEB3GentradeOrderValidator",
                "validateTradingPassword",
                new Class[] { Trader.class, SubAccount.class, String.class },
                l_result);

            VariousInformParams l_variousInformParams = TestDBUtility.getVariousInformRow();
            l_variousInformParams.setInstitutionCode("0D");
            l_variousInformParams.setBranchCode("381");
            l_variousInformParams.setInformDiv("12");
            l_variousInformParams.setAccountCode("2512246");
            l_variousInformParams.setExtDiv1("1");
            l_variousInformParams.setExtDiv2("1");
            TestDBUtility.deleteAll(VariousInformParams.TYPE);
            TestDBUtility.insertWithDel(l_variousInformParams);

            TestDBUtility.deleteAll(QuestionParams.TYPE);
            TestDBUtility.deleteAll(QuestionAnswerParams.TYPE);
            
            TraderParams l_traderParams = TestDBUtility.getTraderRow();
            l_traderParams.setLoginId(0L);
            TestDBUtility.deleteAll(TraderParams.TYPE);
            TestDBUtility.insertWithDel(l_traderParams);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.gentrade.WEB3HostReqOrderNumberManageServiceImpl",
                "getNewNumber", 
                new Class[]{ String.class, String.class, ProductTypeEnum.class },
                "12"
                );
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.inform.service.delegate.stdimpls.WEB3InformHostReqOrderNumberManageServiceImpl",
                "getNewOrderRequestCode", new Class[]
                { String.class, String.class },
                "22"
                );
            
            impl.submitApply(request);

            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            List l_lisQuestionAnswerParams = l_queryProcessor.doFindAllQuery(QuestionAnswerParams.TYPE);
            QuestionAnswerRow l_questionAnswerRow = (QuestionAnswerRow)(l_lisQuestionAnswerParams.get(0));
            assertEquals(1, l_lisQuestionAnswerParams.size());
            assertEquals("0D", l_questionAnswerRow.getInstitutionCode());
            assertEquals("381", l_questionAnswerRow.getBranchCode());
            assertEquals("12", l_questionAnswerRow.getOrderRequestNumber());
            assertEquals("1", l_questionAnswerRow.getQuestionAnswer());
            assertEquals("1", l_questionAnswerRow.getQuestionNo());

            StringBuffer l_sbUpdateSql = new StringBuffer();
            l_sbUpdateSql.append(" institution_code = ? ");
            l_sbUpdateSql.append(" and inform_div = ? ");
//            l_sbUpdateSql.append(" and request_number = ? ");
            l_sbUpdateSql.append(" and branch_code = ? ");

            Object[] l_updateValues = new Object[]{
                l_variousInformParams.getInstitutionCode(),
                l_variousInformParams.getInformDiv(),
//                l_variousInformParams.getRequestNumber(),
                l_variousInformParams.getBranchCode()};
            List l_lisVariousInformRow = l_queryProcessor.doFindAllQuery(
                VariousInformRow.TYPE,
                l_sbUpdateSql.toString(),
                l_updateValues);
            VariousInformParams l_variousInformParamsQuery = (VariousInformParams)(l_lisVariousInformRow.get(0));
            assertEquals(2, l_lisVariousInformRow.size());
            assertEquals("0", l_variousInformParamsQuery.getExtDiv1());
            assertEquals("11123", l_variousInformParamsQuery.getLastUpdater());
            VariousInformParams l_variousInformParamsQuery1 = (VariousInformParams)(l_lisVariousInformRow.get(1));
            assertEquals("1", l_variousInformParamsQuery1.getExtDiv1());

            MainAccountPK l_mainAccountPK = new MainAccountPK(333812512246L);
            MainAccountRow l_mainAccountRow =
                (MainAccountRow)l_queryProcessor.doFindByPrimaryKeyQuery(l_mainAccountPK);
            assertEquals("1", l_mainAccountRow.getPtsAccOpenDiv());
            assertEquals("2512246", l_mainAccountRow.getAccountCode());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testSubmitApply_0009()
    {
        final String STR_METHOD_NAME = "testSubmitApply_0009()";
        log.entering(TEST_START + STR_METHOD_NAME);

        WEB3InformPTSAccOpenApplyCmpRequest request = new WEB3InformPTSAccOpenApplyCmpRequest();
        request.informType = "12";
        request.ptsAccOpenDiv = "1";
        request.ptsTradeAgreementList = null;
        WEB3InformPTSAccOpenApplyServiceImpl impl = new WEB3InformPTSAccOpenApplyServiceImpl();
        
        try
        {
            TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getAccountId",
                new Class[] {},
                new Long("123")
                );
            
            LoginInfoImpl l_loginInfoImpl = new LoginInfoImpl();
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getLoginInfo",
                new Class[] {},
                l_loginInfoImpl
                );
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.LoginInfoImpl",
                "getLoginId",
                new Class[] {},
                new Long("111")
                );
            
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setPtsAccOpenDiv("2");
            WEB3GentradeMainAccount l_mainAccountImpl = new WEB3GentradeMainAccount(l_mainAccountParams);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.gentrade.WEB3GentradeAccountManager",
                "getMainAccount",
                new Class[] {long.class},
                l_mainAccountImpl
                );
            
            WEB3GentradeSubAccount l_gentradeSubAccount = new WEB3GentradeSubAccount(l_mainAccountImpl, TestDBUtility.getSubAccountRow());
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.gentrade.WEB3GentradeMainAccount", "getSubAccount", new Class[]
                { SubAccountTypeEnum.class },
                l_gentradeSubAccount
                );
            
            WEB3GentradeTradingTimeManagementForMock.mockValidateOrderAccept(true);
            ProcessingResult l_ProcessingResult0 =
                ProcessingResult.newSuccessResultInstance();
            
            TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);
            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
            
            WEB3GentradeOrderValidatorForMock l_orderalidateor =
                new WEB3GentradeOrderValidatorForMock();
            l_finApp.overrideCommonOrderValidator(l_orderalidateor);
            
            OrderValidationResult l_result0 = new OrderValidationResult(l_ProcessingResult0);
            OrderValidationResult l_result = new OrderValidationResult(l_ProcessingResult0);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.gentrade.WEB3GentradeOrderValidator",
                "validateSubAccountForTrading",
                new Class[] {SubAccount.class},
                l_result0);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.gentrade.WEB3GentradeOrderValidator",
                "validateTradingPassword",
                new Class[] { Trader.class, SubAccount.class, String.class },
                l_result);

//            VariousInformParams l_variousInformParams = TestDBUtility.getVariousInformRow();
//            l_variousInformParams.setInstitutionCode("0D");
//            l_variousInformParams.setBranchCode("381");
//            l_variousInformParams.setInformDiv("12");
//            l_variousInformParams.setAccountCode("2512246");
//            l_variousInformParams.setExtDiv1("1");
//            l_variousInformParams.setExtDiv2("1");
            TestDBUtility.deleteAll(VariousInformParams.TYPE);
//            TestDBUtility.insertWithDel(l_variousInformParams);

            TestDBUtility.deleteAll(QuestionParams.TYPE);
            TestDBUtility.deleteAll(QuestionAnswerParams.TYPE);
            
            TraderParams l_traderParams = TestDBUtility.getTraderRow();
            l_traderParams.setLoginId(0L);
            TestDBUtility.deleteAll(TraderParams.TYPE);
            TestDBUtility.insertWithDel(l_traderParams);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.gentrade.WEB3HostReqOrderNumberManageServiceImpl",
                "getNewNumber", 
                new Class[]{ String.class, String.class, ProductTypeEnum.class },
                "12"
                );
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.inform.service.delegate.stdimpls.WEB3InformHostReqOrderNumberManageServiceImpl",
                "getNewOrderRequestCode", new Class[]
                { String.class, String.class },
                "22"
                );
            
            impl.submitApply(request);

            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            List l_lisQuestionAnswerParams = l_queryProcessor.doFindAllQuery(QuestionAnswerParams.TYPE);
            assertEquals(0, l_lisQuestionAnswerParams.size());

            List l_lisVariousInformRow = l_queryProcessor.doFindAllQuery(VariousInformRow.TYPE);
            assertEquals(1, l_lisVariousInformRow.size());
            VariousInformParams l_variousInformParamsQuery1 = (VariousInformParams)(l_lisVariousInformRow.get(0));
            assertEquals("1", l_variousInformParamsQuery1.getExtDiv1());

            MainAccountPK l_mainAccountPK = new MainAccountPK(333812512246L);
            MainAccountRow l_mainAccountRow =
                (MainAccountRow)l_queryProcessor.doFindByPrimaryKeyQuery(l_mainAccountPK);
            assertEquals("1", l_mainAccountRow.getPtsAccOpenDiv());
            assertEquals("2512246", l_mainAccountRow.getAccountCode());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }

    private QuestionParams getQuestionParams()
    {
        QuestionParams l_questionParams = new QuestionParams();
        l_questionParams.setInstitutionCode("0D");
        l_questionParams.setBranchCode("381");
        l_questionParams.setQuestionDiv("0003");
        l_questionParams.setQuestionNo("123");
        l_questionParams.setQuestion("abcdef");
        l_questionParams.setLastUpdater("001");
        l_questionParams.setCreatedTimestamp(new Date());
        l_questionParams.setLastUpdatedTimestamp(new Date());
        
        return l_questionParams;
    }
}
@
