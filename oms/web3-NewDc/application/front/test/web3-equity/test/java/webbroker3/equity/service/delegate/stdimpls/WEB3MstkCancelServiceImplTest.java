head	1.2;
access;
symbols;
locks; strict;
comment	@// @;


1.2
date	2011.04.08.00.31.45;	author zhang-tengyu;	state Exp;
branches;
next	1.1;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7c04d9e57702cd5;
filename	WEB3MstkCancelServiceImplTest.java;

1.1
date	2011.04.07.00.54.42;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3MstkCancelServiceImplTest.java;


desc
@@


1.2
log
@*** empty log message ***
@
text
@/**
 Copyright        : (株)大和総研 証券ソリューションシステム第二部
 File Name        : WEB3MstkCancelServiceImplTest.java
 Author Name      : Daiwa Institute of Research
 Revision History : 2008/07/25 楊夫志(中訊) 新規作成 モデル 132
*/
package webbroker3.equity.service.delegate.stdimpls;

import java.util.Date;
import java.util.Map;
import java.util.Set;

import com.fitechlabs.xtrade.kernel.util.ThreadLocalSystemAttributesRegistry;
import com.fitechlabs.xtrade.plugin.security.oplogin.LoginInfo;
import com.fitechlabs.xtrade.plugin.security.oplogin.LoginTypeInfo;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderParams;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderRow;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderUnitParams;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderUnitRow;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeProductParams;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeProductRow;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeTradedProductParams;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeTradedProductRow;
import com.fitechlabs.xtrade.plugin.tc.eqtype.ordersubmitter.io.EqTypeCancelOrderSpec;
import com.fitechlabs.xtrade.plugin.tc.eqtype.ordersubmitter.io.EqTypeOrderSubmissionResult;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProcessingResult;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SideEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccountTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.BranchParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.BranchRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.InstitutionParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.InstitutionRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MarketParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MarketRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.ProductParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.ProductRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.SubAccountParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.SubAccountRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.TradedProductParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.TradedProductRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.TraderParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.TraderRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.CancelOrderSpec;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.OrderValidationResult;

import test.util.TestDBUtility;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3BizDateCalcParameterDef;
import webbroker3.common.define.WEB3SessionAttributeDef;
import webbroker3.equity.message.WEB3MstkBalanceReferenceRequest;
import webbroker3.equity.message.WEB3MstkCancelCompleteRequest;
import webbroker3.equity.message.WEB3MstkCancelCompleteResponse;
import webbroker3.equity.message.WEB3MstkCancelConfirmRequest;
import webbroker3.equity.message.WEB3MstkCancelConfirmResponse;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.gentrade.data.TradingTimeParams;
import webbroker3.gentrade.data.TradingTimeRow;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

public class WEB3MstkCancelServiceImplTest extends TestBaseForMock
{
    private static WEB3LogUtility log = WEB3LogUtility
    .getInstance(WEB3MstkCancelServiceImplTest.class);
    
    private WEB3MstkCancelServiceImpl  l_serviceImpl= null;

    public WEB3MstkCancelServiceImplTest(String arg0)
    {
        super(arg0);
    }

    protected void setUp() throws Exception
    {
        super.setUp();
        MOCK_MANAGER.setIsMockUsed(true);
        l_serviceImpl = new WEB3MstkCancelServiceImpl();
    }

    protected void tearDown() throws Exception
    {
        super.tearDown();
    }

    //引数のリクエストデータが、株式ミニ投資注文取消確認リクエストの場合 
    public void testExecute_C0001()
    {
        final String STR_METHOD_NAME = "testExecute_C0001()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            WEB3MstkCancelConfirmRequest confirmRequest =
                new WEB3MstkCancelConfirmRequest();
            WEB3MstkCancelServiceImplForTest implForTest = new WEB3MstkCancelServiceImplForTest();
            
            assertEquals("1111", implForTest.execute(confirmRequest).errorMessage);
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        log.info(STR_METHOD_NAME
            + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    //引数のリクエストデータが、株式ミニ投資注文取消完了リクエストの場合 
    public void testExecute_C0002()
    {
        final String STR_METHOD_NAME = "testExecute_C0002()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            WEB3MstkCancelCompleteRequest confirmRequest =
                new WEB3MstkCancelCompleteRequest();
            WEB3MstkCancelServiceImplForTest implForTest = new WEB3MstkCancelServiceImplForTest();
            
            assertEquals("2222", implForTest.execute(confirmRequest).errorMessage);
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        log.info(STR_METHOD_NAME
            + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    //引数のリクエストデータが、株式ミニ投資注文取消完了リクエスト,
    //株式ミニ投資注文取消確認リクエストの以外的場合 
    //WEB3BaseRuntimeException
    public void testExecute_C0003()
    {
        final String STR_METHOD_NAME = "testExecute_C0003()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            WEB3MstkBalanceReferenceRequest referenceRequest =
                new WEB3MstkBalanceReferenceRequest();
            
            l_serviceImpl.execute(referenceRequest);
            fail();
        } 
        catch (WEB3BaseRuntimeException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80018, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        log.info(STR_METHOD_NAME
            + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    //2) validate()
    public void testValidateOrder_C0001()
    {
        final String STR_METHOD_NAME = "testValidateOrder_C0001()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            WEB3MstkCancelConfirmRequest confirmRequest =
                new WEB3MstkCancelConfirmRequest();
            confirmRequest.id = null;
            l_serviceImpl.validateOrder(confirmRequest);
            fail();
        } 
        catch (WEB3BaseException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00080, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        log.info(STR_METHOD_NAME
            + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    //5)validateミニ株注文取消(補助口座, CancelOrderSpec)
    public void testValidateOrder_C0002()
    {
        final String STR_METHOD_NAME = "testValidateOrder_C0002()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            WEB3MstkCancelConfirmRequest confirmRequest =
                new WEB3MstkCancelConfirmRequest();
            confirmRequest.id = "1111";
            
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            MainAccountParams mainAccountParams = TestDBUtility.getMainAccountRow();
            TestDBUtility.insertWithDel(mainAccountParams);
            
            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            SubAccountParams subAccountParams = TestDBUtility.getSubAccountRow();
            subAccountParams.setAccountId(333812512246L);
            subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
            TestDBUtility.insertWithDel(subAccountParams);
            
            TestDBUtility.deleteAll(TraderRow.TYPE);
            TraderParams traderParams = TestDBUtility.getTraderRow();
            TestDBUtility.insertWithDel(traderParams);
            
            LoginInfoForTest infoForTest = new LoginInfoForTest();
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl", "getLoginInfo",
                    new Class[]{},
                    infoForTest);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                    "getAccountId",
                    new Class[] {},
                    new Long(333812512246L));  
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.equity.WEB3EquityOrderManager", "validateMiniStockCancelOrder", new Class[]
                    { WEB3GentradeSubAccount.class, CancelOrderSpec.class },
                    new WEB3BaseException(WEB3ErrorCatalog.BUSINESS_ERROR_00010,""));
            
            l_serviceImpl.validateOrder(confirmRequest);
            fail();
        } 
        catch (WEB3BaseException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00010, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        log.info(STR_METHOD_NAME
            + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    //validateミニ株注文取消(補助口座, CancelOrderSpec)結果處理失敗
    public void testValidateOrder_C0003()
    {
        final String STR_METHOD_NAME = "testValidateOrder_C0003()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            WEB3MstkCancelConfirmRequest confirmRequest =
                new WEB3MstkCancelConfirmRequest();
            confirmRequest.id = "1111";
            
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            MainAccountParams mainAccountParams = TestDBUtility.getMainAccountRow();
            TestDBUtility.insertWithDel(mainAccountParams);
            
            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            SubAccountParams subAccountParams = TestDBUtility.getSubAccountRow();
            subAccountParams.setAccountId(333812512246L);
            subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
            TestDBUtility.insertWithDel(subAccountParams);
            
            TestDBUtility.deleteAll(TraderRow.TYPE);
            TraderParams traderParams = TestDBUtility.getTraderRow();
            TestDBUtility.insertWithDel(traderParams);
            
            LoginInfoForTest infoForTest = new LoginInfoForTest();
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl", "getLoginInfo",
                    new Class[]{},
                    infoForTest);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                    "getAccountId",
                    new Class[] {},
                    new Long(333812512246L));  
            
            ProcessingResult processingResult = ProcessingResult.newFailedResultInstance(WEB3ErrorCatalog.BUSINESS_ERROR_00011);
            OrderValidationResult l_orderValidationResult = new OrderValidationResult(processingResult);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.equity.WEB3EquityOrderManager", "validateMiniStockCancelOrder", new Class[]
                    { WEB3GentradeSubAccount.class, CancelOrderSpec.class },
                    l_orderValidationResult);
            
            l_serviceImpl.validateOrder(confirmRequest);
            fail();
        } 
        catch (WEB3BaseException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00011, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        log.info(STR_METHOD_NAME
            + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    //l_orderValidationResult.getProcessingResult().isSuccessResult()
    //8)getTradedProduct()失敗異常?出
    //BUSINESS_ERROR_01966
    public void testValidateOrder_C0004()
    {
        final String STR_METHOD_NAME = "testValidateOrder_C0004()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            WEB3MstkCancelConfirmRequest confirmRequest =
                new WEB3MstkCancelConfirmRequest();
            confirmRequest.id = "1111";
            
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            MainAccountParams mainAccountParams = TestDBUtility.getMainAccountRow();
            TestDBUtility.insertWithDel(mainAccountParams);
            
            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            SubAccountParams subAccountParams = TestDBUtility.getSubAccountRow();
            subAccountParams.setAccountId(333812512246L);
            subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
            TestDBUtility.insertWithDel(subAccountParams);
            
            TestDBUtility.deleteAll(BranchRow.TYPE);
            BranchParams branchParams = TestDBUtility.getBranchRow();
            branchParams.setBranchId(33381L);
            TestDBUtility.insertWithDel(branchParams);
            
            TestDBUtility.deleteAll(TraderRow.TYPE);
            TraderParams traderParams = TestDBUtility.getTraderRow();
            TestDBUtility.insertWithDel(traderParams);

            TestDBUtility.deleteAll(EqtypeOrderUnitRow.TYPE);
            EqtypeOrderUnitParams eqtypeOrderUnitParams = TestDBUtility.getEqtypeOrderUnitRow();
            eqtypeOrderUnitParams.setOrderId(1111);
            eqtypeOrderUnitParams.setProductId(0);
            TestDBUtility.insertWithDel(eqtypeOrderUnitParams);
            
            TestDBUtility.deleteAll(TradingTimeRow.TYPE);
            TradingTimeParams tradingTimeParams = TestDBUtility.getTradingTimeRow();
            tradingTimeParams.setInstitutionCode("0D");
            tradingTimeParams.setBranchCode("123");
            tradingTimeParams.setMarketCode("N1");
            tradingTimeParams.setTradingTimeType("01");
            tradingTimeParams.setProductCode("0");
            tradingTimeParams.setBizDateType("1");
            TestDBUtility.insertWithDel(tradingTimeParams);
            
            LoginInfoForTest infoForTest = new LoginInfoForTest();
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl", "getLoginInfo",
                    new Class[]{},
                    infoForTest);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                    "getAccountId",
                    new Class[] {},
                    new Long(333812512246L));
            
            ProcessingResult processingResult = ProcessingResult.newSuccessResultInstance();
            OrderValidationResult l_orderValidationResult = new OrderValidationResult(processingResult);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.equity.WEB3EquityOrderManager", "validateMiniStockCancelOrder", new Class[]
                    { WEB3GentradeSubAccount.class, CancelOrderSpec.class},
                    l_orderValidationResult);
            
            l_serviceImpl.validateOrder(confirmRequest);
            fail();
        } 
        catch (WEB3BaseException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_01966, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        log.info(STR_METHOD_NAME
            + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    //14.3)売買区分:
    //（注文単位.getSide() == SideEnum.BUY）の場合、買い
    public void testValidateOrder_C0005()
    {
        final String STR_METHOD_NAME = "testValidateOrder_C0005()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            WEB3MstkCancelConfirmRequest confirmRequest =
                new WEB3MstkCancelConfirmRequest();
            confirmRequest.id = "1111";
            
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            MainAccountParams mainAccountParams = TestDBUtility.getMainAccountRow();
            TestDBUtility.insertWithDel(mainAccountParams);
            
            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            SubAccountParams subAccountParams = TestDBUtility.getSubAccountRow();
            subAccountParams.setAccountId(333812512246L);
            subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
            TestDBUtility.insertWithDel(subAccountParams);
            
            TestDBUtility.deleteAll(BranchRow.TYPE);
            BranchParams branchParams = TestDBUtility.getBranchRow();
            branchParams.setBranchId(33381L);
            TestDBUtility.insertWithDel(branchParams);
            
            TestDBUtility.deleteAll(TraderRow.TYPE);
            TraderParams traderParams = TestDBUtility.getTraderRow();
            TestDBUtility.insertWithDel(traderParams);

            TestDBUtility.deleteAll(EqtypeOrderUnitRow.TYPE);
            EqtypeOrderUnitParams eqtypeOrderUnitParams = TestDBUtility.getEqtypeOrderUnitRow();
            eqtypeOrderUnitParams.setOrderId(1111);
            eqtypeOrderUnitParams.setProductId(1111);
            eqtypeOrderUnitParams.setOrderType(OrderTypeEnum.EQUITY_BUY);
            TestDBUtility.insertWithDel(eqtypeOrderUnitParams);
            
            TestDBUtility.deleteAll(TradingTimeRow.TYPE);
            TradingTimeParams tradingTimeParams = TestDBUtility.getTradingTimeRow();
            tradingTimeParams.setInstitutionCode("0D");
            tradingTimeParams.setBranchCode("123");
            tradingTimeParams.setMarketCode("N1");
            tradingTimeParams.setTradingTimeType("01");
            tradingTimeParams.setProductCode("0");
            tradingTimeParams.setBizDateType("1");
            TestDBUtility.insertWithDel(tradingTimeParams);
            
            TestDBUtility.deleteAll(ProductRow.TYPE);
            ProductParams productParams = TestDBUtility.getProductRow();
            productParams.setProductId(1111);
            productParams.setProductType(ProductTypeEnum.EQUITY);
            TestDBUtility.insertWithDel(productParams);
            
            TestDBUtility.deleteAll(MarketRow.TYPE);
            MarketParams marketParams = TestDBUtility.getMarketRow();
            TestDBUtility.insertWithDel(marketParams);
            
            TestDBUtility.deleteAll(TradedProductRow.TYPE);
            TradedProductParams tradedProductParams = TestDBUtility.getTradedProductRow();
            tradedProductParams.setProductId(1111);
            tradedProductParams.setProductType(ProductTypeEnum.EQUITY);
            TestDBUtility.insertWithDel(tradedProductParams);
            
            TestDBUtility.deleteAll(EqtypeProductRow.TYPE);
            EqtypeProductParams eqtypeProductParams = TestDBUtility.getEqtypeProductRow();
            eqtypeProductParams.setProductId(1111);
            TestDBUtility.insertWithDel(eqtypeProductParams);
            
            TestDBUtility.deleteAll(EqtypeTradedProductRow.TYPE);
            EqtypeTradedProductParams eqtypeTradedProductParams = TestDBUtility.getEqtypeTradedProductRow();
            eqtypeTradedProductParams.setProductId(1111);
            TestDBUtility.insertWithDel(eqtypeTradedProductParams);
            
            LoginInfoForTest infoForTest = new LoginInfoForTest();
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl", "getLoginInfo",
                    new Class[]{},
                    infoForTest);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                    "getAccountId",
                    new Class[] {},
                    new Long(333812512246L));
            
            ProcessingResult processingResult = ProcessingResult.newSuccessResultInstance();
            OrderValidationResult l_orderValidationResult = new OrderValidationResult(processingResult);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.equity.WEB3EquityOrderManager", "validateMiniStockCancelOrder", new Class[]
                    { WEB3GentradeSubAccount.class, CancelOrderSpec.class},
                    l_orderValidationResult);
            
            WEB3MstkCancelConfirmResponse l_response = new WEB3MstkCancelConfirmResponse();
            l_response = l_serviceImpl.validateOrder(confirmRequest);
            assertEquals(l_response.dealingType,  "" + SideEnum.BUY.intValue());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        log.info(STR_METHOD_NAME
            + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    //14.3)売買区分:
    //注文単位.getSide() == SideEnum.SELL）の場合、売り
    public void testValidateOrder_C0006()
    {
        final String STR_METHOD_NAME = "testValidateOrder_C0006()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            WEB3MstkCancelConfirmRequest confirmRequest =
                new WEB3MstkCancelConfirmRequest();
            confirmRequest.id = "1111";
            
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            MainAccountParams mainAccountParams = TestDBUtility.getMainAccountRow();
            TestDBUtility.insertWithDel(mainAccountParams);
            
            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            SubAccountParams subAccountParams = TestDBUtility.getSubAccountRow();
            subAccountParams.setAccountId(333812512246L);
            subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
            TestDBUtility.insertWithDel(subAccountParams);
            
            TestDBUtility.deleteAll(BranchRow.TYPE);
            BranchParams branchParams = TestDBUtility.getBranchRow();
            branchParams.setBranchId(33381L);
            TestDBUtility.insertWithDel(branchParams);
            
            TestDBUtility.deleteAll(TraderRow.TYPE);
            TraderParams traderParams = TestDBUtility.getTraderRow();
            TestDBUtility.insertWithDel(traderParams);

            TestDBUtility.deleteAll(EqtypeOrderUnitRow.TYPE);
            EqtypeOrderUnitParams eqtypeOrderUnitParams = TestDBUtility.getEqtypeOrderUnitRow();
            eqtypeOrderUnitParams.setOrderId(1111);
            eqtypeOrderUnitParams.setProductId(1111);
            eqtypeOrderUnitParams.setOrderType(OrderTypeEnum.EQUITY_SELL);
            TestDBUtility.insertWithDel(eqtypeOrderUnitParams);
            
            TestDBUtility.deleteAll(TradingTimeRow.TYPE);
            TradingTimeParams tradingTimeParams = TestDBUtility.getTradingTimeRow();
            tradingTimeParams.setInstitutionCode("0D");
            tradingTimeParams.setBranchCode("123");
            tradingTimeParams.setMarketCode("N1");
            tradingTimeParams.setTradingTimeType("01");
            tradingTimeParams.setProductCode("0");
            tradingTimeParams.setBizDateType("1");
            TestDBUtility.insertWithDel(tradingTimeParams);
            
            TestDBUtility.deleteAll(ProductRow.TYPE);
            ProductParams productParams = TestDBUtility.getProductRow();
            productParams.setProductId(1111);
            productParams.setProductType(ProductTypeEnum.EQUITY);
            TestDBUtility.insertWithDel(productParams);
            
            TestDBUtility.deleteAll(MarketRow.TYPE);
            MarketParams marketParams = TestDBUtility.getMarketRow();
            TestDBUtility.insertWithDel(marketParams);
            
            TestDBUtility.deleteAll(TradedProductRow.TYPE);
            TradedProductParams tradedProductParams = TestDBUtility.getTradedProductRow();
            tradedProductParams.setProductId(1111);
            tradedProductParams.setProductType(ProductTypeEnum.EQUITY);
            TestDBUtility.insertWithDel(tradedProductParams);
            
            TestDBUtility.deleteAll(EqtypeProductRow.TYPE);
            EqtypeProductParams eqtypeProductParams = TestDBUtility.getEqtypeProductRow();
            eqtypeProductParams.setProductId(1111);
            TestDBUtility.insertWithDel(eqtypeProductParams);
            
            TestDBUtility.deleteAll(EqtypeTradedProductRow.TYPE);
            EqtypeTradedProductParams eqtypeTradedProductParams = TestDBUtility.getEqtypeTradedProductRow();
            eqtypeTradedProductParams.setProductId(1111);
            TestDBUtility.insertWithDel(eqtypeTradedProductParams);
            
            LoginInfoForTest infoForTest = new LoginInfoForTest();
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl", "getLoginInfo",
                    new Class[]{},
                    infoForTest);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                    "getAccountId",
                    new Class[] {},
                    new Long(333812512246L));
            
            ProcessingResult processingResult = ProcessingResult.newSuccessResultInstance();
            OrderValidationResult l_orderValidationResult = new OrderValidationResult(processingResult);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.equity.WEB3EquityOrderManager", "validateMiniStockCancelOrder", new Class[]
                    { WEB3GentradeSubAccount.class, CancelOrderSpec.class},
                    l_orderValidationResult);
            
            WEB3MstkCancelConfirmResponse l_response = new WEB3MstkCancelConfirmResponse();
            l_response = l_serviceImpl.validateOrder(confirmRequest);
            assertEquals(l_response.dealingType,  "" + SideEnum.SELL.intValue());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        log.info(STR_METHOD_NAME
            + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    //2) validate()
    public void testSubmitOrder_C0001()
    {
        final String STR_METHOD_NAME = "testSubmitOrder_C0001()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            WEB3MstkCancelCompleteRequest completeRequest =
                new WEB3MstkCancelCompleteRequest();
            completeRequest.checkDate = WEB3DateUtility.getDate("20080725", "yyyyMMdd");
            completeRequest.id = null;
            l_serviceImpl.submitOrder(completeRequest);
            fail();
        } 
        catch (WEB3BaseException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00080, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        log.info(STR_METHOD_NAME
            + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    //5)validateミニ株注文取消(補助口座, CancelOrderSpec)
    public void testSubmitOrder_C0002()
    {
        final String STR_METHOD_NAME = "testSubmitOrder_C0002()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            WEB3MstkCancelCompleteRequest completeRequest =
                new WEB3MstkCancelCompleteRequest();
            completeRequest.id = "1111";
            completeRequest.checkDate = WEB3DateUtility.getDate("20080725", "yyyyMMdd");
            
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            MainAccountParams mainAccountParams = TestDBUtility.getMainAccountRow();
            TestDBUtility.insertWithDel(mainAccountParams);
            
            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            SubAccountParams subAccountParams = TestDBUtility.getSubAccountRow();
            subAccountParams.setAccountId(333812512246L);
            subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
            TestDBUtility.insertWithDel(subAccountParams);
            
            TestDBUtility.deleteAll(TraderRow.TYPE);
            TraderParams traderParams = TestDBUtility.getTraderRow();
            TestDBUtility.insertWithDel(traderParams);
            
            LoginInfoForTest infoForTest = new LoginInfoForTest();
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl", "getLoginInfo",
                    new Class[]{},
                    infoForTest);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                    "getAccountId",
                    new Class[] {},
                    new Long(333812512246L));  
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.equity.WEB3EquityOrderManager", "validateMiniStockCancelOrder", new Class[]
                    { WEB3GentradeSubAccount.class, CancelOrderSpec.class },
                    new WEB3BaseException(WEB3ErrorCatalog.BUSINESS_ERROR_00010,""));
            
            l_serviceImpl.submitOrder(completeRequest);
            fail();
        } 
        catch (WEB3BaseException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00010, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        log.info(STR_METHOD_NAME
            + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    //l_orderValidationResult.getProcessingResult().isFailedResult()
    public void testSubmitOrder_C0003()
    {
        final String STR_METHOD_NAME = "testSubmitOrder_C0003()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            WEB3MstkCancelCompleteRequest completeRequest =
                new WEB3MstkCancelCompleteRequest();
            completeRequest.id = "1111";
            completeRequest.checkDate = WEB3DateUtility.getDate("20080725", "yyyyMMdd");
            
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            MainAccountParams mainAccountParams = TestDBUtility.getMainAccountRow();
            TestDBUtility.insertWithDel(mainAccountParams);
            
            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            SubAccountParams subAccountParams = TestDBUtility.getSubAccountRow();
            subAccountParams.setAccountId(333812512246L);
            subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
            TestDBUtility.insertWithDel(subAccountParams);
            
            TestDBUtility.deleteAll(TraderRow.TYPE);
            TraderParams traderParams = TestDBUtility.getTraderRow();
            TestDBUtility.insertWithDel(traderParams);
            
            LoginInfoForTest infoForTest = new LoginInfoForTest();
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl", "getLoginInfo",
                    new Class[]{},
                    infoForTest);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                    "getAccountId",
                    new Class[] {},
                    new Long(333812512246L));  
            
            ProcessingResult processingResult = ProcessingResult.newFailedResultInstance(WEB3ErrorCatalog.BUSINESS_ERROR_00011);
            OrderValidationResult l_orderValidationResult = new OrderValidationResult(processingResult);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.equity.WEB3EquityOrderManager", "validateMiniStockCancelOrder", new Class[]
                    { WEB3GentradeSubAccount.class, CancelOrderSpec.class },
                    l_orderValidationResult);
            
            l_serviceImpl.submitOrder(completeRequest);
            fail();
        } 
        catch (WEB3BaseException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00011, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        log.info(STR_METHOD_NAME
            + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    //8)submitCancelOrder(SubAccount, EqTypeCancelOrderSpec, 論理ビュー::java::lang::String, boolean) 
    public void testSubmitOrder_C0004()
    {
        final String STR_METHOD_NAME = "testSubmitOrder_C0004()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            WEB3MstkCancelCompleteRequest completeRequest =
                new WEB3MstkCancelCompleteRequest();
            completeRequest.id = "1111";
            completeRequest.password = "1111";
            completeRequest.checkDate = (Date)ThreadLocalSystemAttributesRegistry.getAttribute(
                    WEB3GentradeTradingTimeManagement.BASE_TIMESTAMP_FOR_ORDER_BIZ_DATE);
            
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            MainAccountParams mainAccountParams = TestDBUtility.getMainAccountRow();
            TestDBUtility.insertWithDel(mainAccountParams);
            
            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            SubAccountParams subAccountParams = TestDBUtility.getSubAccountRow();
            subAccountParams.setAccountId(333812512246L);
            subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
            TestDBUtility.insertWithDel(subAccountParams);
            
            TestDBUtility.deleteAll(TraderRow.TYPE);
            TraderParams traderParams = TestDBUtility.getTraderRow();
            TestDBUtility.insertWithDel(traderParams);
            
            TestDBUtility.deleteAll(TradingTimeRow.TYPE);
            TradingTimeParams tradingTimeParams = TestDBUtility.getTradingTimeRow();
            tradingTimeParams.setBizdateCalcParameter(WEB3BizDateCalcParameterDef.DAY_BIZ_DATE);
            tradingTimeParams.setInstitutionCode("0D");
            tradingTimeParams.setBranchCode("123");
            tradingTimeParams.setMarketCode("N1");
            tradingTimeParams.setTradingTimeType("01");
            tradingTimeParams.setProductCode("0");
            tradingTimeParams.setBizDateType("1");
            TestDBUtility.insertWithDel(tradingTimeParams);
            
            TestDBUtility.deleteAll(EqtypeOrderUnitRow.TYPE);
            EqtypeOrderUnitParams eqtypeOrderUnitParams = TestDBUtility.getEqtypeOrderUnitRow();
            eqtypeOrderUnitParams.setOrderId(1111);
            eqtypeOrderUnitParams.setProductId(1111);
            eqtypeOrderUnitParams.setOrderType(OrderTypeEnum.EQUITY_SELL);
            TestDBUtility.insertWithDel(eqtypeOrderUnitParams);
            
            TestDBUtility.deleteAll(EqtypeOrderRow.TYPE);
            EqtypeOrderParams eqtypeOrderParams = TestDBUtility.getEqtypeOrderRow();
            eqtypeOrderParams.setSubAccountId(33381251220301L);
            eqtypeOrderParams.setAccountId(1111);
            eqtypeOrderParams.setOrderId(1111);
            TestDBUtility.insertWithDel(eqtypeOrderParams);
            
            LoginInfoForTest infoForTest = new LoginInfoForTest();
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl", "getLoginInfo",
                    new Class[]{},
                    infoForTest);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                    "getAccountId",
                    new Class[] {},
                    new Long(333812512246L));
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl", "getSessionProperty",
                    new Class[] {String.class},
                    WEB3SessionAttributeDef.ORDER_ROOT_DIV);
            
            ProcessingResult processingResult = ProcessingResult.newSuccessResultInstance();
            OrderValidationResult l_orderValidationResult = new OrderValidationResult(processingResult);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.equity.WEB3EquityOrderManager", "validateMiniStockCancelOrder", new Class[]
                    { WEB3GentradeSubAccount.class, CancelOrderSpec.class },
                    l_orderValidationResult);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue("webbroker3.equity.WEB3EquityPTSOrderManager",
                    "submitCancelOrder", new Class[]
                    { SubAccount.class, EqTypeCancelOrderSpec.class, String.class, boolean.class},
                    new WEB3BaseRuntimeException(WEB3ErrorCatalog.SYSTEM_ERROR_80017, STR_METHOD_NAME));
            
            l_serviceImpl.submitOrder(completeRequest);
            fail();
        } 
        catch (WEB3BaseRuntimeException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80017, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        log.info(STR_METHOD_NAME
            + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    //l_result.getProcessingResult().isFailedResult()
    public void testSubmitOrder_C0005()
    {
        final String STR_METHOD_NAME = "testSubmitOrder_C0005()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            WEB3MstkCancelCompleteRequest completeRequest =
                new WEB3MstkCancelCompleteRequest();
            completeRequest.id = "1111";
            completeRequest.password = "1111";
            completeRequest.checkDate = (Date)ThreadLocalSystemAttributesRegistry.getAttribute(
                    WEB3GentradeTradingTimeManagement.BASE_TIMESTAMP_FOR_ORDER_BIZ_DATE);
            
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            MainAccountParams mainAccountParams = TestDBUtility.getMainAccountRow();
            TestDBUtility.insertWithDel(mainAccountParams);
            
            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            SubAccountParams subAccountParams = TestDBUtility.getSubAccountRow();
            subAccountParams.setAccountId(333812512246L);
            subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
            TestDBUtility.insertWithDel(subAccountParams);
            
            TestDBUtility.deleteAll(TraderRow.TYPE);
            TraderParams traderParams = TestDBUtility.getTraderRow();
            TestDBUtility.insertWithDel(traderParams);
            
            TestDBUtility.deleteAll(TradingTimeRow.TYPE);
            TradingTimeParams tradingTimeParams = TestDBUtility.getTradingTimeRow();
            tradingTimeParams.setBizdateCalcParameter(WEB3BizDateCalcParameterDef.DAY_BIZ_DATE);
            tradingTimeParams.setInstitutionCode("0D");
            tradingTimeParams.setBranchCode("123");
            tradingTimeParams.setMarketCode("N1");
            tradingTimeParams.setTradingTimeType("01");
            tradingTimeParams.setProductCode("0");
            tradingTimeParams.setBizDateType("1");
            TestDBUtility.insertWithDel(tradingTimeParams);
            
            TestDBUtility.deleteAll(EqtypeOrderUnitRow.TYPE);
            EqtypeOrderUnitParams eqtypeOrderUnitParams = TestDBUtility.getEqtypeOrderUnitRow();
            eqtypeOrderUnitParams.setOrderId(1111);
            eqtypeOrderUnitParams.setProductId(1111);
            eqtypeOrderUnitParams.setOrderType(OrderTypeEnum.EQUITY_SELL);
            TestDBUtility.insertWithDel(eqtypeOrderUnitParams);
            
            TestDBUtility.deleteAll(EqtypeOrderRow.TYPE);
            EqtypeOrderParams eqtypeOrderParams = TestDBUtility.getEqtypeOrderRow();
            eqtypeOrderParams.setSubAccountId(33381251220301L);
            eqtypeOrderParams.setAccountId(1111);
            eqtypeOrderParams.setOrderId(1111);
            TestDBUtility.insertWithDel(eqtypeOrderParams);
            
            LoginInfoForTest infoForTest = new LoginInfoForTest();
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl", "getLoginInfo",
                    new Class[]{},
                    infoForTest);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                    "getAccountId",
                    new Class[] {},
                    new Long(333812512246L));
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl", "getSessionProperty",
                    new Class[] {String.class},
                    WEB3SessionAttributeDef.ORDER_ROOT_DIV);
            
            ProcessingResult processingResult = ProcessingResult.newSuccessResultInstance();
            OrderValidationResult l_orderValidationResult = new OrderValidationResult(processingResult);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.equity.WEB3EquityOrderManager", "validateMiniStockCancelOrder", new Class[]
                    { WEB3GentradeSubAccount.class, CancelOrderSpec.class },
                    l_orderValidationResult);
            
            ProcessingResult processingResult1 = ProcessingResult.newFailedResultInstance(WEB3ErrorCatalog.BUSINESS_ERROR_00005);
            EqTypeOrderSubmissionResult submissionResult = new EqTypeOrderSubmissionResult(processingResult1);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue("webbroker3.equity.WEB3EquityPTSOrderManager",
                    "submitCancelOrder", new Class[]
                    { SubAccount.class, EqTypeCancelOrderSpec.class, String.class, boolean.class},
                    submissionResult);
            
            l_serviceImpl.submitOrder(completeRequest);
            fail();
        } 
        catch (WEB3BaseException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00005, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        log.info(STR_METHOD_NAME
            + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    //l_result.getProcessingResult().isSuccessResult()
    //normalcase
    public void testSubmitOrder_C0006()
    {
        final String STR_METHOD_NAME = "testSubmitOrder_C0006()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            WEB3MstkCancelCompleteRequest completeRequest =
                new WEB3MstkCancelCompleteRequest();
            completeRequest.id = "1111";
            completeRequest.password = "1111";
            completeRequest.checkDate = (Date)ThreadLocalSystemAttributesRegistry.getAttribute(
                    WEB3GentradeTradingTimeManagement.BASE_TIMESTAMP_FOR_ORDER_BIZ_DATE);
            
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            MainAccountParams mainAccountParams = TestDBUtility.getMainAccountRow();
            TestDBUtility.insertWithDel(mainAccountParams);
            
            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            SubAccountParams subAccountParams = TestDBUtility.getSubAccountRow();
            subAccountParams.setAccountId(333812512246L);
            subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
            TestDBUtility.insertWithDel(subAccountParams);
            
            TestDBUtility.deleteAll(TraderRow.TYPE);
            TraderParams traderParams = TestDBUtility.getTraderRow();
            TestDBUtility.insertWithDel(traderParams);
            
            TestDBUtility.deleteAll(TradingTimeRow.TYPE);
            TradingTimeParams tradingTimeParams = TestDBUtility.getTradingTimeRow();
            tradingTimeParams.setBizdateCalcParameter(WEB3BizDateCalcParameterDef.DAY_BIZ_DATE);
            tradingTimeParams.setInstitutionCode("0D");
            tradingTimeParams.setBranchCode("123");
            tradingTimeParams.setMarketCode("N1");
            tradingTimeParams.setTradingTimeType("01");
            tradingTimeParams.setProductCode("0");
            tradingTimeParams.setBizDateType("1");
            TestDBUtility.insertWithDel(tradingTimeParams);
            
            TestDBUtility.deleteAll(EqtypeOrderUnitRow.TYPE);
            EqtypeOrderUnitParams eqtypeOrderUnitParams = TestDBUtility.getEqtypeOrderUnitRow();
            eqtypeOrderUnitParams.setOrderId(1111);
            eqtypeOrderUnitParams.setProductId(1111);
            eqtypeOrderUnitParams.setOrderType(OrderTypeEnum.EQUITY_SELL);
            TestDBUtility.insertWithDel(eqtypeOrderUnitParams);
            
            TestDBUtility.deleteAll(EqtypeOrderRow.TYPE);
            EqtypeOrderParams eqtypeOrderParams = TestDBUtility.getEqtypeOrderRow();
            eqtypeOrderParams.setSubAccountId(33381251220301L);
            eqtypeOrderParams.setAccountId(1111);
            eqtypeOrderParams.setOrderId(1111);
            TestDBUtility.insertWithDel(eqtypeOrderParams);
            
            TestDBUtility.deleteAll(InstitutionRow.TYPE);
            InstitutionParams institutionParams = TestDBUtility.getInstitutionRow();
            institutionParams.setInstitutionId(33);
            TestDBUtility.insertWithDel(institutionParams);

            LoginInfoForTest infoForTest = new LoginInfoForTest();
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl", "getLoginInfo",
                    new Class[]{},
                    infoForTest);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                    "getAccountId",
                    new Class[] {},
                    new Long(333812512246L));
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl", "getSessionProperty",
                    new Class[] {String.class},
                    WEB3SessionAttributeDef.ORDER_ROOT_DIV);
            
            ProcessingResult processingResult = ProcessingResult.newSuccessResultInstance();
            OrderValidationResult l_orderValidationResult = new OrderValidationResult(processingResult);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.equity.WEB3EquityOrderManager", "validateMiniStockCancelOrder", new Class[]
                    { WEB3GentradeSubAccount.class, CancelOrderSpec.class },
                    l_orderValidationResult);
            
            ProcessingResult processingResult1 = ProcessingResult.newSuccessResultInstance();
            EqTypeOrderSubmissionResult submissionResult = new EqTypeOrderSubmissionResult(processingResult1);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue("webbroker3.equity.WEB3EquityPTSOrderManager",
                    "submitCancelOrder", new Class[]
                    { SubAccount.class, EqTypeCancelOrderSpec.class, String.class, boolean.class},
                    submissionResult);

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.tradingpower.WEB3TPTradingPowerServiceImpl",
                    "reCalcTradingPower",
                    new Class[] {WEB3GentradeSubAccount.class},
                    null);

            assertEquals("1111", l_serviceImpl.submitOrder(completeRequest).orderActionId);
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        log.info(STR_METHOD_NAME
            + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    private class WEB3MstkCancelServiceImplForTest extends WEB3MstkCancelServiceImpl
    {
        protected WEB3MstkCancelConfirmResponse validateOrder(WEB3MstkCancelConfirmRequest l_request) throws WEB3BaseException 
        {
            WEB3MstkCancelConfirmResponse l_response =new  WEB3MstkCancelConfirmResponse();
            l_response.errorMessage = "1111";
            return l_response;
        }

        protected WEB3MstkCancelCompleteResponse submitOrder(WEB3MstkCancelCompleteRequest l_request) throws WEB3BaseException
        {
            WEB3MstkCancelCompleteResponse l_response =new  WEB3MstkCancelCompleteResponse();
            l_response.errorMessage = "2222";
            return l_response;
        }
    }
    
    private class LoginInfoForTest implements LoginInfo
    {

        public LoginTypeInfo getLoginTypeInfo()
        {
            // TODO Auto-generated method stub
            return null;
        }

        public long getLoginId()
        {
            // TODO Auto-generated method stub
            return 3338111123L;
        }

        public long getLoginTypeId()
        {
            // TODO Auto-generated method stub
            return 0;
        }

        public String getUsername()
        {
            // TODO Auto-generated method stub
            return null;
        }

        public String getInitialPassword()
        {
            // TODO Auto-generated method stub
            return null;
        }

        public Set getSubordinateLoginGroups()
        {
            // TODO Auto-generated method stub
            return null;
        }

        public boolean isDisabled()
        {
            // TODO Auto-generated method stub
            return false;
        }

        public Set getReachableAccountIds()
        {
            // TODO Auto-generated method stub
            return null;
        }

        public Set getReachableLoginIds()
        {
            // TODO Auto-generated method stub
            return null;
        }

        public Set getReachableLogins()
        {
            // TODO Auto-generated method stub
            return null;
        }

        public Map getAttributes()
        {
            // TODO Auto-generated method stub
            return null;
        }

        public boolean isAccountReachable(long arg0)
        {
            // TODO Auto-generated method stub
            return false;
        }

        public boolean hasFailedLogin()
        {
            // TODO Auto-generated method stub
            return false;
        }

        public int getFailureCount()
        {
            // TODO Auto-generated method stub
            return 0;
        }

        public Date getLastFailureTimestamp()
        {
            // TODO Auto-generated method stub
            return null;
        }

		public Map getLoginAttributes() {
			// TODO Auto-generated method stub
			return null;
		}

		public Map getLoginTypeAttributes() {
			// TODO Auto-generated method stub
			return null;
		}

		public Long getDefaultAccountId() {
			// TODO Auto-generated method stub
			return null;
		}
        
    }
}
@


1.1
log
@*** empty log message ***
@
text
@d1184 15
@

