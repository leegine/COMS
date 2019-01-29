head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.00.56.27;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3EquityMarginExecuteReferenceServiceImplTest_execute.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : (WEB3EquityMarginExecuteReferenceServiceImplTest_execute.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2007/01/18　@崔遠鵬(中訊)
*/
package webbroker3.equity.service.delegate.stdimpls;

import java.util.Date;

import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.kernel.util.ThreadLocalSystemAttributesRegistry;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeProductParams;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeTradedProductParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProcessingResult;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccountTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.BranchParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.InstitutionParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MarketParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.ProductParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.SubAccountParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.TradedProductParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.OrderValidationResult;

import test.util.TestDBUtility;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3AscDescDef;
import webbroker3.common.define.WEB3DealtDef;
import webbroker3.common.define.WEB3MarginAccountOpenDivDef;
import webbroker3.common.define.WEB3MarginTradingDivDef;
import webbroker3.common.define.WEB3MarketCodeDef;
import webbroker3.equity.define.WEB3EquityExecStatusTypeDef;
import webbroker3.equity.define.WEB3EquityKeyItemDef;
import webbroker3.equity.define.WEB3EquityProductDivDef;
import webbroker3.equity.define.WEB3EquityReferenceTypeDef;
import webbroker3.equity.message.WEB3EquityMarginExecuteReferenceRequest;
import webbroker3.equity.message.WEB3EquityMarginExecuteReferenceResponse;
import webbroker3.equity.message.WEB3EquityMarginSortKey;
import webbroker3.gentrade.WEB3GentradeBranch;
import webbroker3.gentrade.WEB3GentradeMainAccount;
import webbroker3.gentrade.WEB3GentradeTradingClendarContext;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagementForMock;
import webbroker3.gentrade.data.BranchMarketDealtCondParams;
import webbroker3.gentrade.data.BranchMarketRepayDealtCondParams;
import webbroker3.gentrade.data.OrderAcceptStatusRow;
import webbroker3.gentrade.define.WEB3GentradeRepaymentDivDef;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

public class WEB3EquityMarginExecuteReferenceServiceImplTest_execute extends TestBaseForMock
{
    /**
     * ログユーティリティ<BR>
     */
    private static WEB3LogUtility log =
         WEB3LogUtility.getInstance(
             WEB3EquityMarginExecuteReferenceServiceImplTest_execute.class);

    private String caseNumber = null;

    WEB3EquityMarginExecuteReferenceServiceImpl l_equityMarginExecuteReferenceServiceImpl = 
        new WEB3EquityMarginExecuteReferenceServiceImpl();

    WEB3EquityMarginExecuteReferenceServiceImpl l_equityMarginExecuteReferenceServiceImplForMock = 
        new WEB3EquityMarginExecuteReferenceServiceImplForMock();

    public WEB3EquityMarginExecuteReferenceServiceImplTest_execute(String arg0)
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
    
    public void test_execute_C0001()
    {
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue("webbroker3.gentrade.WEB3GentradeBranch",
            "isMarginTradeEnforcement", new Class[] {String.class}, Boolean.TRUE);
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue("webbroker3.gentrade.WEB3GentradeMainAccount",
            "isMarginAccountEstablished", new Class[] {String.class}, Boolean.TRUE);
        final String STR_METHOD_NAME = ".test_execute_C0001()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            // 実際メソッドをコール
            WEB3EquityMarginExecuteReferenceRequest l_request = new WEB3EquityMarginExecuteReferenceRequestForMock();
            l_equityMarginExecuteReferenceServiceImpl.execute(l_request);
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            // 予想結果と実際結果の比較
            log.debug(STR_METHOD_NAME, l_ex);
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80005, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error("", l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    public void test_execute_C0002()
    {
        final String STR_METHOD_NAME = ".test_execute_C0002()";
        log.entering(TEST_START + STR_METHOD_NAME);

        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue("webbroker3.gentrade.WEB3GentradeBranch",
            "isMarginTradeEnforcement", new Class[] {String.class}, Boolean.TRUE);
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue("webbroker3.gentrade.WEB3GentradeMainAccount",
            "isMarginAccountEstablished", new Class[] {String.class}, Boolean.TRUE);
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue("webbroker3.gentrade.WEB3GentradeBranch",
            "isMarginTradeEnforcement", new Class[] {String.class}, Boolean.FALSE);
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl", "getAccountId",
            new Class[] {}, new Long(333812512246L));

        try
        {
            // テーブルへデータをインサート
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            TestDBUtility.insertWithDel(l_mainAccountParams);

            BranchParams l_branchParams =TestDBUtility.getBranchRow();
            TestDBUtility.insertWithDel(l_branchParams);

            // 実際メソッドをコール
            WEB3EquityMarginExecuteReferenceRequest l_request = new WEB3EquityMarginExecuteReferenceRequest();
            l_request.referenceType = WEB3EquityReferenceTypeDef.REFERENCE_TYPE_VIEW;
            l_request.productDiv = WEB3EquityProductDivDef.MARGIN;
            l_request.execType = WEB3EquityExecStatusTypeDef.EXEC_TYPE_NOT_PROMISE;
            l_request.sortKeys = new WEB3EquityMarginSortKey[1];
            l_request.sortKeys[0] = new WEB3EquityMarginSortKey();
            l_request.sortKeys[0].keyItem = WEB3EquityKeyItemDef.PRODUCTCODE;
            l_request.sortKeys[0].ascDesc = WEB3AscDescDef.ASC;
            l_request.pageIndex = "1";
            l_request.pageSize = "1";
            l_equityMarginExecuteReferenceServiceImpl.execute(l_request);
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            // 予想結果と実際結果の比較
            log.debug(STR_METHOD_NAME, l_ex);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00746, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error("", l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    public void test_execute_C0003()
    {
        final String STR_METHOD_NAME = ".test_execute_C0003()";
        log.entering(TEST_START + STR_METHOD_NAME);

        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue("webbroker3.gentrade.WEB3GentradeBranch",
            "isMarginTradeEnforcement", new Class[] {String.class}, Boolean.TRUE);
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue("webbroker3.gentrade.WEB3GentradeMainAccount",
            "isMarginAccountEstablished", new Class[] {String.class}, Boolean.FALSE);
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl", "getAccountId",
            new Class[] {}, new Long(333812512246L));

        try
        {
            // テーブルへデータをインサート
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            TestDBUtility.insertWithDel(l_mainAccountParams);

            BranchParams l_branchParams =TestDBUtility.getBranchRow();
            TestDBUtility.insertWithDel(l_branchParams);

            // 実際メソッドをコール
            WEB3EquityMarginExecuteReferenceRequest l_request = new WEB3EquityMarginExecuteReferenceRequest();
            l_request.referenceType = WEB3EquityReferenceTypeDef.REFERENCE_TYPE_VIEW;
            l_request.productDiv = WEB3EquityProductDivDef.MARGIN;
            l_request.execType = WEB3EquityExecStatusTypeDef.EXEC_TYPE_NOT_PROMISE;
            l_request.sortKeys = new WEB3EquityMarginSortKey[1];
            l_request.sortKeys[0] = new WEB3EquityMarginSortKey();
            l_request.sortKeys[0].keyItem = WEB3EquityKeyItemDef.PRODUCTCODE;
            l_request.sortKeys[0].ascDesc = WEB3AscDescDef.ASC;
            l_request.pageIndex = "1";
            l_request.pageSize = "1";
            l_equityMarginExecuteReferenceServiceImpl.execute(l_request);
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            // 予想結果と実際結果の比較
            log.debug(STR_METHOD_NAME, l_ex);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00747, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error("", l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    public void test_execute_C0004()
    {
        final String STR_METHOD_NAME = ".test_execute_C0004()";
        log.entering(TEST_START + STR_METHOD_NAME);

        caseNumber = "test_execute_C0004";
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue("webbroker3.gentrade.WEB3GentradeBranch",
            "isMarginTradeEnforcement", new Class[] {String.class}, Boolean.TRUE);
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue("webbroker3.gentrade.WEB3GentradeMainAccount",
            "isMarginAccountEstablished", new Class[] {String.class}, Boolean.TRUE);
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl", "getAccountId",
            new Class[] {}, new Long(333812512246L));

        try
        {
            // テーブルへデータをインサート
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            TestDBUtility.insertWithDel(l_mainAccountParams);

            BranchParams l_branchParams =TestDBUtility.getBranchRow();
            TestDBUtility.insertWithDel(l_branchParams);

            // 実際メソッドをコール
            WEB3EquityMarginExecuteReferenceRequest l_request = new WEB3EquityMarginExecuteReferenceRequest();
            l_request.referenceType = WEB3EquityReferenceTypeDef.REFERENCE_TYPE_VIEW;
            l_request.productDiv = WEB3EquityProductDivDef.MARGIN;
            l_request.execType = WEB3EquityExecStatusTypeDef.EXEC_TYPE_NOT_PROMISE;
            l_request.sortKeys = new WEB3EquityMarginSortKey[1];
            l_request.sortKeys[0] = new WEB3EquityMarginSortKey();
            l_request.sortKeys[0].keyItem = WEB3EquityKeyItemDef.PRODUCTCODE;
            l_request.sortKeys[0].ascDesc = WEB3AscDescDef.ASC;
            l_request.pageIndex = "1";
            l_request.pageSize = "1";
            l_equityMarginExecuteReferenceServiceImplForMock.execute(l_request);
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            // 予想結果と実際結果の比較
            log.debug(STR_METHOD_NAME, l_ex);
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80005, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error("", l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    //リクエスト.照会区分＝"訂正取消一覧"の場合のみ実行
    //validate取引可能顧客(顧客, String) //TODO
    public void test_execute_C0005()
    {
        final String STR_METHOD_NAME = ".test_execute_C0005()";
        log.entering(TEST_START + STR_METHOD_NAME);

        caseNumber ="test_execute_C0005";
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue("webbroker3.gentrade.WEB3GentradeBranch",
            "isMarginTradeEnforcement", new Class[] {String.class}, Boolean.TRUE);
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue("webbroker3.gentrade.WEB3GentradeMainAccount",
            "isMarginAccountEstablished", new Class[] {String.class}, Boolean.TRUE);
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl", "getAccountId",
            new Class[] {}, new Long(333812512246L));
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "webbroker3.gentrade.WEB3GentradeOrderValidator",
            "validateAccountForTrading",
            new Class[] {WEB3GentradeMainAccount.class},
            new OrderValidationResult(ProcessingResult.
            newFailedResultInstance(WEB3ErrorCatalog.BUSINESS_ERROR_00009)));

        try
        {
            // テーブルへデータをインサート
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            TestDBUtility.insertWithDel(l_mainAccountParams);

            BranchParams l_branchParams =TestDBUtility.getBranchRow();
            TestDBUtility.insertWithDel(l_branchParams);

            SubAccountParams l_subAccountParams =TestDBUtility.getSubAccountRow();
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_MARGIN_SUB_ACCOUNT);
            l_subAccountParams.setAccountId(333812512246L);
            TestDBUtility.insertWithDel(l_subAccountParams);

            // 実際メソッドをコール
            WEB3EquityMarginExecuteReferenceRequest l_request = new WEB3EquityMarginExecuteReferenceRequest();
            l_request.referenceType = WEB3EquityReferenceTypeDef.REFERENCE_TYPE_UPDATE;
            l_request.productDiv = WEB3EquityProductDivDef.EQUITY;
            l_request.execType = WEB3EquityExecStatusTypeDef.EXEC_TYPE_NOT_PROMISE;
            l_request.sortKeys = new WEB3EquityMarginSortKey[1];
            l_request.sortKeys[0] = new WEB3EquityMarginSortKey();
            l_request.sortKeys[0].keyItem = WEB3EquityKeyItemDef.PRODUCTCODE;
            l_request.sortKeys[0].ascDesc = WEB3AscDescDef.ASC;
            l_request.pageIndex = "1";
            l_request.pageSize = "1";
            l_equityMarginExecuteReferenceServiceImplForMock.execute(l_request);
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.debug(STR_METHOD_NAME, l_ex);
            // 予想結果と実際結果の比較
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00009, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error("", l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    public void test_execute_C0006()
    {
        final String STR_METHOD_NAME = ".test_execute_C0006()";
        log.entering(TEST_START + STR_METHOD_NAME);

        caseNumber ="test_execute_C0006";
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue("webbroker3.gentrade.WEB3GentradeBranch",
            "isMarginTradeEnforcement", new Class[] {String.class}, Boolean.TRUE);
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue("webbroker3.gentrade.WEB3GentradeMainAccount",
            "isMarginAccountEstablished", new Class[] {String.class}, Boolean.TRUE);
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl", "getAccountId",
            new Class[] {}, new Long(333812512246L));
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "webbroker3.gentrade.WEB3GentradeOrderValidator",
            "validateAccountForTrading",
            new Class[] {WEB3GentradeMainAccount.class},
            new OrderValidationResult(ProcessingResult.SUCCESS_RESULT));

        try
        {
            // テーブルへデータをインサート
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            TestDBUtility.insertWithDel(l_mainAccountParams);

            BranchParams l_branchParams =TestDBUtility.getBranchRow();
            TestDBUtility.insertWithDel(l_branchParams);

            SubAccountParams l_subAccountParams =TestDBUtility.getSubAccountRow();
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_MARGIN_SUB_ACCOUNT);
            l_subAccountParams.setAccountId(333812512246L);
            TestDBUtility.insertWithDel(l_subAccountParams);

            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            TestDBUtility.insertWithDel(l_institutionParams);

            EqtypeProductParams l_eqtypeProduct = TestDBUtility.getEqtypeProductRow();
            QueryProcessor l_processor = Processors.getDefaultProcessor();
            l_processor.doDeleteAllQuery(l_eqtypeProduct.getRowType());

            // 実際メソッドをコール
            WEB3EquityMarginExecuteReferenceRequest l_request = new WEB3EquityMarginExecuteReferenceRequest();
            l_request.referenceType = WEB3EquityReferenceTypeDef.REFERENCE_TYPE_VIEW;
            l_request.productDiv = WEB3EquityProductDivDef.EQUITY;
            l_request.execType = WEB3EquityExecStatusTypeDef.EXEC_TYPE_NOT_PROMISE;
            l_request.sortKeys = new WEB3EquityMarginSortKey[1];
            l_request.sortKeys[0] = new WEB3EquityMarginSortKey();
            l_request.sortKeys[0].keyItem = WEB3EquityKeyItemDef.PRODUCTCODE;
            l_request.sortKeys[0].ascDesc = WEB3AscDescDef.ASC;
            l_request.pageIndex = "1";
            l_request.pageSize = "1";
            l_request.productCode = "N8080"; 
            l_equityMarginExecuteReferenceServiceImplForMock.execute(l_request);
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.debug(STR_METHOD_NAME, l_ex);
            // 予想結果と実際結果の比較
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00301, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error("", l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    public void test_execute_C0007()
    {
        final String STR_METHOD_NAME = ".test_execute_C0007()";
        log.entering(TEST_START + STR_METHOD_NAME);

        caseNumber ="test_execute_C0007";
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue("webbroker3.gentrade.WEB3GentradeBranch",
            "isMarginTradeEnforcement", new Class[] {String.class}, Boolean.TRUE);
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue("webbroker3.gentrade.WEB3GentradeMainAccount",
            "isMarginAccountEstablished", new Class[] {String.class}, Boolean.TRUE);
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl", "getAccountId",
            new Class[] {}, new Long(333812512246L));
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "webbroker3.gentrade.WEB3GentradeOrderValidator",
            "validateAccountForTrading",
            new Class[] {WEB3GentradeMainAccount.class},
            new OrderValidationResult(ProcessingResult.SUCCESS_RESULT));

        try
        {
            // テーブルへデータをインサート
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            TestDBUtility.insertWithDel(l_mainAccountParams);

            BranchParams l_branchParams =TestDBUtility.getBranchRow();
            TestDBUtility.insertWithDel(l_branchParams);

            SubAccountParams l_subAccountParams =TestDBUtility.getSubAccountRow();
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_MARGIN_SUB_ACCOUNT);
            l_subAccountParams.setAccountId(333812512246L);
            TestDBUtility.insertWithDel(l_subAccountParams);

            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            TestDBUtility.insertWithDel(l_institutionParams);

            EqtypeProductParams l_eqtypeProduct = TestDBUtility.getEqtypeProductRow();
            TestDBUtility.insertWithDel(l_eqtypeProduct);

            ProductParams l_productParams = TestDBUtility.getProductRow();
            TestDBUtility.insertWithDel(l_productParams);  

            MarketParams l_marketParams = TestDBUtility.getMarketRow();
            l_marketParams.setMarketCode(WEB3MarketCodeDef.TOKYO);
            TestDBUtility.insertWithDel(l_marketParams);

            TradedProductParams l_tradedProductParams = new TradedProductParams();
            l_tradedProductParams.setProductId(3304148080000L);
            l_tradedProductParams.setMarketId(3303L);
            QueryProcessor l_processor = Processors.getDefaultProcessor();
            l_processor.doDeleteAllQuery(l_tradedProductParams.getRowType());

            // 実際メソッドをコール
            WEB3GentradeTradingClendarContext l_context = new WEB3GentradeTradingClendarContext();
            l_context.setInstitutionCode("0D");
            l_context.setBranchCode("381");
            l_context.setProductCode("N8080");
            l_context.setBizDateType("1");
            l_context.setMarketCode("1");
            l_context.setTradingTimeType("01");
            WEB3GentradeTradingTimeManagementForMock.setClendarContext(l_context);
            
            WEB3EquityMarginExecuteReferenceRequest l_request = new WEB3EquityMarginExecuteReferenceRequest();
            l_request.referenceType = WEB3EquityReferenceTypeDef.REFERENCE_TYPE_VIEW;
            l_request.productDiv = WEB3EquityProductDivDef.EQUITY;
            l_request.execType = WEB3EquityExecStatusTypeDef.EXEC_TYPE_NOT_PROMISE;
            l_request.sortKeys = new WEB3EquityMarginSortKey[1];
            l_request.sortKeys[0] = new WEB3EquityMarginSortKey();
            l_request.sortKeys[0].keyItem = WEB3EquityKeyItemDef.PRODUCTCODE;
            l_request.sortKeys[0].ascDesc = WEB3AscDescDef.ASC;
            l_request.pageIndex = "1";
            l_request.pageSize = "1";
            l_request.productCode = "N8080";
            l_request.marketCode = WEB3MarketCodeDef.TOKYO;
            l_equityMarginExecuteReferenceServiceImplForMock.execute(l_request);
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.debug(STR_METHOD_NAME, l_ex);
            // 予想結果と実際結果の比較
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00638, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error("", l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    public void test_execute_C0008()
    {
        final String STR_METHOD_NAME = ".test_execute_C0008()";
        log.entering(TEST_START + STR_METHOD_NAME);

        caseNumber ="test_execute_C0008";
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue("webbroker3.gentrade.WEB3GentradeBranch",
            "isMarginTradeEnforcement", new Class[] {String.class}, Boolean.TRUE);
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue("webbroker3.gentrade.WEB3GentradeMainAccount",
            "isMarginAccountEstablished", new Class[] {String.class}, Boolean.TRUE);
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl", "getAccountId",
            new Class[] {}, new Long(333812512246L));
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "webbroker3.gentrade.WEB3GentradeOrderValidator",
            "validateAccountForTrading",
            new Class[] {WEB3GentradeMainAccount.class},
            new OrderValidationResult(ProcessingResult.SUCCESS_RESULT));

        try
        {
            // テーブルへデータをインサート
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setMarginGenAccOpenDiv(WEB3MarginAccountOpenDivDef.MARGIN_ACCOUNT_NOT_OPEN);
            l_mainAccountParams.setMarginSysAccOpenDiv(WEB3MarginAccountOpenDivDef.MARGIN_ACCOUNT_OPEN);
            TestDBUtility.insertWithDel(l_mainAccountParams);
            
            BranchParams l_branchParams =TestDBUtility.getBranchRow();
            TestDBUtility.insertWithDel(l_branchParams);

            SubAccountParams l_subAccountParams =TestDBUtility.getSubAccountRow();
            l_subAccountParams.setAccountId(333812512246L);
            TestDBUtility.insertWithDel(l_subAccountParams);

            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            TestDBUtility.insertWithDel(l_institutionParams);

            EqtypeProductParams l_eqtypeProduct = TestDBUtility.getEqtypeProductRow();
            TestDBUtility.insertWithDel(l_eqtypeProduct);

            ProductParams l_productParams = TestDBUtility.getProductRow();
            TestDBUtility.insertWithDel(l_productParams);            

            MarketParams l_marketParams = TestDBUtility.getMarketRow();
            l_marketParams.setMarketCode(WEB3MarketCodeDef.TOKYO);
            TestDBUtility.insertWithDel(l_marketParams);

            TradedProductParams l_tradedProductParams = TestDBUtility.getTradedProductRow();
            l_tradedProductParams.setProductId(3304148080000L);
            l_tradedProductParams.setMarketId(3303L);
            l_tradedProductParams.setProductType(ProductTypeEnum.EQUITY);
            TestDBUtility.insertWithDel(l_tradedProductParams);

            EqtypeTradedProductParams l_eqtypeTradedProductParams = TestDBUtility.getEqtypeTradedProductRow();
            TestDBUtility.insertWithDel(l_eqtypeTradedProductParams);

            BranchMarketDealtCondParams l_branchMarketDealtCondParams = new BranchMarketDealtCondParams();
            l_branchMarketDealtCondParams.setInstitutionCode("0D");
            l_branchMarketDealtCondParams.setBranchCode("381");
            QueryProcessor l_processor = Processors.getDefaultProcessor();
            l_processor.doDeleteAllQuery(l_branchMarketDealtCondParams.getRowType());
            l_branchMarketDealtCondParams = TestDBUtility.getBranchMarketDealCondRow();
            TestDBUtility.insertWithDel(l_branchMarketDealtCondParams);

            // 実際メソッドをコール
            WEB3GentradeMainAccount l_mainAccount =
                (WEB3GentradeMainAccount) l_equityMarginExecuteReferenceServiceImplForMock.getMainAccount();
            WEB3GentradeBranch l_branch = (WEB3GentradeBranch)l_mainAccount.getBranch();
            WEB3GentradeTradingTimeManagementForMock.mockGetTradeCloseMarket(l_branch, ProductTypeEnum.EQUITY,
                WEB3MarginTradingDivDef.MARKET_MARGIN, new String[] {new String("1")});

            WEB3EquityMarginExecuteReferenceRequest l_request = new WEB3EquityMarginExecuteReferenceRequest();
            l_request.referenceType = WEB3EquityReferenceTypeDef.REFERENCE_TYPE_VIEW;
            l_request.productDiv = WEB3EquityProductDivDef.EQUITY;
            l_request.execType = WEB3EquityExecStatusTypeDef.EXEC_TYPE_NOT_PROMISE;
            l_request.sortKeys = new WEB3EquityMarginSortKey[1];
            l_request.sortKeys[0] = new WEB3EquityMarginSortKey();
            l_request.sortKeys[0].keyItem = WEB3EquityKeyItemDef.PRODUCTCODE;
            l_request.sortKeys[0].ascDesc = WEB3AscDescDef.ASC;
            l_request.pageIndex = "1";
            l_request.pageSize = "1";
            l_request.productCode = "N8080";
            l_request.marketCode = WEB3MarketCodeDef.TOKYO;
            WEB3EquityMarginExecuteReferenceResponse l_response =
                (WEB3EquityMarginExecuteReferenceResponse)l_equityMarginExecuteReferenceServiceImplForMock.
                execute(l_request);

            // 予想結果と実際結果の比較
            assertEquals(WEB3MarketCodeDef.TOKYO, l_response.marketList[0]);
            assertEquals(WEB3DateUtility.getDate("20070108","yyyyMMdd"), l_response.orderBizDateList[0]);
            assertEquals("1", l_response.messageSuspension[0].toString());
        }
        catch (WEB3BaseException l_ex)
        {
            log.debug(STR_METHOD_NAME, l_ex);
            fail();
        }
        catch (Exception l_ex)
        {
            log.error("", l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    public void test_execute_C0009()
    {
        final String STR_METHOD_NAME = ".test_execute_C0009()";
        log.entering(TEST_START + STR_METHOD_NAME);

        caseNumber ="test_execute_C0009";
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue("webbroker3.gentrade.WEB3GentradeBranch",
            "isMarginTradeEnforcement", new Class[] {String.class}, Boolean.TRUE);
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue("webbroker3.gentrade.WEB3GentradeMainAccount",
            "isMarginAccountEstablished", new Class[] {String.class}, Boolean.TRUE);
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl", "getAccountId",
            new Class[] {}, new Long(333812512246L));
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "webbroker3.gentrade.WEB3GentradeOrderValidator",
            "validateAccountForTrading",
            new Class[] {WEB3GentradeMainAccount.class},
            new OrderValidationResult(ProcessingResult.SUCCESS_RESULT));

        try
        {
            // テーブルへデータをインサート
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setMarginGenAccOpenDiv(WEB3MarginAccountOpenDivDef.MARGIN_ACCOUNT_NOT_OPEN);
            l_mainAccountParams.setMarginSysAccOpenDiv(WEB3MarginAccountOpenDivDef.MARGIN_ACCOUNT_OPEN);
            TestDBUtility.insertWithDel(l_mainAccountParams);

            BranchParams l_branchParams =TestDBUtility.getBranchRow();
            TestDBUtility.insertWithDel(l_branchParams);

            SubAccountParams l_subAccountParams =TestDBUtility.getSubAccountRow();
            l_subAccountParams.setAccountId(333812512246L);
            TestDBUtility.insertWithDel(l_subAccountParams);

            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            TestDBUtility.insertWithDel(l_institutionParams);

            EqtypeProductParams l_eqtypeProduct = TestDBUtility.getEqtypeProductRow();
            TestDBUtility.insertWithDel(l_eqtypeProduct);

            ProductParams l_productParams = TestDBUtility.getProductRow();
            TestDBUtility.insertWithDel(l_productParams);

            MarketParams l_marketParams = TestDBUtility.getMarketRow();
            l_marketParams.setMarketCode(WEB3MarketCodeDef.TOKYO);
            TestDBUtility.insertWithDel(l_marketParams);

            TradedProductParams l_tradedProductParams = TestDBUtility.getTradedProductRow();
            l_tradedProductParams.setProductId(3304148080000L);
            l_tradedProductParams.setMarketId(3303L);
            l_tradedProductParams.setProductType(ProductTypeEnum.EQUITY);
            TestDBUtility.insertWithDel(l_tradedProductParams);

            EqtypeTradedProductParams l_eqtypeTradedProductParams = TestDBUtility.getEqtypeTradedProductRow();
            TestDBUtility.insertWithDel(l_eqtypeTradedProductParams);

            BranchMarketRepayDealtCondParams l_branchMarketRepayDealtCondParams =
                new BranchMarketRepayDealtCondParams();
            l_branchMarketRepayDealtCondParams.setInstitutionCode("0D");
            l_branchMarketRepayDealtCondParams.setBranchCode("381");
            QueryProcessor l_processor = Processors.getDefaultProcessor();
            l_processor.doDeleteAllQuery(l_branchMarketRepayDealtCondParams.getRowType());
            l_branchMarketRepayDealtCondParams = TestDBUtility.getBranchMarketRepayDealCondRow();
            TestDBUtility.insertWithDel(l_branchMarketRepayDealtCondParams);

            // 実際メソッドをコール
            WEB3GentradeMainAccount l_mainAccount =
                (WEB3GentradeMainAccount) l_equityMarginExecuteReferenceServiceImplForMock.getMainAccount();
            WEB3GentradeBranch l_branch = (WEB3GentradeBranch)l_mainAccount.getBranch();
            WEB3GentradeTradingTimeManagementForMock.mockGetTradeCloseMarket(l_branch, ProductTypeEnum.EQUITY,
                WEB3MarginTradingDivDef.MARKET_MARGIN, new String[] {new String("2")});

            WEB3EquityMarginExecuteReferenceRequest l_request = new WEB3EquityMarginExecuteReferenceRequest();
            l_request.referenceType = WEB3EquityReferenceTypeDef.REFERENCE_TYPE_VIEW;
            l_request.productDiv = WEB3EquityProductDivDef.MARGIN;
            l_request.execType = WEB3EquityExecStatusTypeDef.EXEC_TYPE_NOT_PROMISE;
            l_request.sortKeys = new WEB3EquityMarginSortKey[1];
            l_request.sortKeys[0] = new WEB3EquityMarginSortKey();
            l_request.sortKeys[0].keyItem = WEB3EquityKeyItemDef.PRODUCTCODE;
            l_request.sortKeys[0].ascDesc = WEB3AscDescDef.ASC;
            l_request.pageIndex = "1";
            l_request.pageSize = "1";
            l_request.productCode = "N8080";
            l_request.marketCode = WEB3MarketCodeDef.TOKYO;
            WEB3EquityMarginExecuteReferenceResponse l_response =
                (WEB3EquityMarginExecuteReferenceResponse)l_equityMarginExecuteReferenceServiceImplForMock.
                execute(l_request);

            // 予想結果と実際結果の比較
            assertEquals("2", l_response.marketList[0]);
            assertEquals(WEB3DateUtility.getDate("20070109","yyyyMMdd"), l_response.orderBizDateList[0]);
            assertEquals("2", l_response.messageSuspension[0].toString());
        }
        catch (WEB3BaseException l_ex)
        {
            log.debug(STR_METHOD_NAME, l_ex);
            fail();
        }
        catch (Exception l_ex)
        {
            log.error("", l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    public void test_execute_C0010()
    {
        final String STR_METHOD_NAME = ".test_execute_C0010()";
        log.entering(TEST_START + STR_METHOD_NAME);

        caseNumber ="test_execute_C0010";
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue("webbroker3.gentrade.WEB3GentradeBranch",
            "isMarginTradeEnforcement", new Class[] {String.class}, Boolean.TRUE);
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue("webbroker3.gentrade.WEB3GentradeMainAccount",
            "isMarginAccountEstablished", new Class[] {String.class}, Boolean.TRUE);
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl", "getAccountId",
            new Class[] {}, new Long(333812512246L));
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "webbroker3.gentrade.WEB3GentradeOrderValidator",
            "validateAccountForTrading",
            new Class[] {WEB3GentradeMainAccount.class},
            new OrderValidationResult(ProcessingResult.SUCCESS_RESULT));

        try
        {
            // テーブルへデータをインサート
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setMarginGenAccOpenDiv(WEB3MarginAccountOpenDivDef.MARGIN_ACCOUNT_OPEN);
            l_mainAccountParams.setMarginSysAccOpenDiv(WEB3MarginAccountOpenDivDef.MARGIN_ACCOUNT_NOT_OPEN);
            TestDBUtility.insertWithDel(l_mainAccountParams);

            BranchParams l_branchParams =TestDBUtility.getBranchRow();
            TestDBUtility.insertWithDel(l_branchParams);

            SubAccountParams l_subAccountParams =TestDBUtility.getSubAccountRow();
            l_subAccountParams.setAccountId(333812512246L);
            TestDBUtility.insertWithDel(l_subAccountParams);

            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            TestDBUtility.insertWithDel(l_institutionParams);

            EqtypeProductParams l_eqtypeProduct = TestDBUtility.getEqtypeProductRow();
            TestDBUtility.insertWithDel(l_eqtypeProduct);

            ProductParams l_productParams = TestDBUtility.getProductRow();
            TestDBUtility.insertWithDel(l_productParams);            

            MarketParams l_marketParams = TestDBUtility.getMarketRow();
            l_marketParams.setMarketCode(WEB3MarketCodeDef.TOKYO);
            TestDBUtility.insertWithDel(l_marketParams);

            TradedProductParams l_tradedProductParams = TestDBUtility.getTradedProductRow();
            l_tradedProductParams.setProductId(0L);
            l_tradedProductParams.setMarketId(3303L);
            TestDBUtility.insertWithDel(l_tradedProductParams);

            BranchMarketRepayDealtCondParams l_branchMarketRepayDealtCondParams =
                new BranchMarketRepayDealtCondParams();
            l_branchMarketRepayDealtCondParams.setInstitutionCode("0D");
            l_branchMarketRepayDealtCondParams.setBranchCode("381");
            QueryProcessor l_processor = Processors.getDefaultProcessor();
            l_processor.doDeleteAllQuery(l_branchMarketRepayDealtCondParams.getRowType());
            l_branchMarketRepayDealtCondParams = TestDBUtility.getBranchMarketRepayDealCondRow();
            l_branchMarketRepayDealtCondParams.setMartCanDealt(WEB3DealtDef.CAN_DEALT);
            l_branchMarketRepayDealtCondParams.setRepaymentDiv(WEB3GentradeRepaymentDivDef.REPAYMENT_DIV_MARGIN_GEN);
            TestDBUtility.insertWithDel(l_branchMarketRepayDealtCondParams);

            // 実際メソッドをコール
            WEB3GentradeMainAccount l_mainAccount =
                (WEB3GentradeMainAccount) l_equityMarginExecuteReferenceServiceImplForMock.getMainAccount();
            WEB3GentradeBranch l_branch = (WEB3GentradeBranch)l_mainAccount.getBranch();
            WEB3GentradeTradingTimeManagementForMock.mockGetTradeCloseMarket(l_branch, ProductTypeEnum.EQUITY,
                WEB3MarginTradingDivDef.INSTITUTION_MARGIN, new String[] {new String("3")});

            WEB3EquityMarginExecuteReferenceRequest l_request = new WEB3EquityMarginExecuteReferenceRequest();
            l_request.referenceType = WEB3EquityReferenceTypeDef.REFERENCE_TYPE_VIEW;
            l_request.productDiv = WEB3EquityProductDivDef.MARGIN;
            l_request.execType = WEB3EquityExecStatusTypeDef.EXEC_TYPE_NOT_PROMISE;
            l_request.sortKeys = new WEB3EquityMarginSortKey[1];
            l_request.sortKeys[0] = new WEB3EquityMarginSortKey();
            l_request.sortKeys[0].keyItem = WEB3EquityKeyItemDef.PRODUCTCODE;
            l_request.sortKeys[0].ascDesc = WEB3AscDescDef.ASC;
            l_request.pageIndex = "1";
            l_request.pageSize = "1";
            l_request.productCode = "N8080";
            l_request.marketCode = null;
            WEB3EquityMarginExecuteReferenceResponse l_response =
                (WEB3EquityMarginExecuteReferenceResponse)l_equityMarginExecuteReferenceServiceImplForMock.
                execute(l_request);

            // 予想結果と実際結果の比較
            assertEquals("3", l_response.marketList[0]);
            assertEquals(WEB3DateUtility.getDate("20070110","yyyyMMdd"), l_response.orderBizDateList[0]);
            assertEquals("3", l_response.messageSuspension[0].toString());
        }
        catch (WEB3BaseException l_ex)
        {
            log.debug(STR_METHOD_NAME, l_ex);
            fail();
        }
        catch (Exception l_ex)
        {
            log.error("", l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    public void test_execute_C0011()
    {
        final String STR_METHOD_NAME = ".test_execute_C0011()";
        log.entering(TEST_START + STR_METHOD_NAME);

        caseNumber ="test_execute_C0011";
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl", "getAccountId",
            new Class[] {}, new Long(333812512246L));
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue("webbroker3.gentrade.WEB3GentradeBranch",
            "isMarginTradeEnforcement", new Class[] {String.class}, Boolean.TRUE);
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue("webbroker3.gentrade.WEB3GentradeMainAccount",
            "isMarginAccountEstablished", new Class[] {String.class}, Boolean.TRUE);
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "webbroker3.gentrade.WEB3GentradeOrderValidator",
            "validateAccountForTrading",
            new Class[] {WEB3GentradeMainAccount.class},
            new OrderValidationResult(ProcessingResult.SUCCESS_RESULT));

        try
        {
            // テーブルへデータをインサート
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setMarginGenAccOpenDiv(WEB3MarginAccountOpenDivDef.MARGIN_ACCOUNT_OPEN);
            l_mainAccountParams.setMarginSysAccOpenDiv(WEB3MarginAccountOpenDivDef.MARGIN_ACCOUNT_OPEN);
            TestDBUtility.insertWithDel(l_mainAccountParams);

            BranchParams l_branchParams =TestDBUtility.getBranchRow();
            TestDBUtility.insertWithDel(l_branchParams);

            SubAccountParams l_subAccountParams =TestDBUtility.getSubAccountRow();
            l_subAccountParams.setAccountId(333812512246L);
            TestDBUtility.insertWithDel(l_subAccountParams);

            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            TestDBUtility.insertWithDel(l_institutionParams);

            EqtypeProductParams l_eqtypeProduct = TestDBUtility.getEqtypeProductRow();
            TestDBUtility.insertWithDel(l_eqtypeProduct);

            ProductParams l_productParams = TestDBUtility.getProductRow();
            TestDBUtility.insertWithDel(l_productParams);            

            MarketParams l_marketParams = TestDBUtility.getMarketRow();
            l_marketParams.setMarketCode(WEB3MarketCodeDef.TOKYO);
            TestDBUtility.insertWithDel(l_marketParams);

            TradedProductParams l_tradedProductParams = TestDBUtility.getTradedProductRow();
            l_tradedProductParams.setProductId(0L);
            l_tradedProductParams.setMarketId(3303L);
            TestDBUtility.insertWithDel(l_tradedProductParams);

            BranchMarketRepayDealtCondParams l_branchMarketRepayDealtCondParams =
                new BranchMarketRepayDealtCondParams();
            l_branchMarketRepayDealtCondParams.setInstitutionCode("0D");
            l_branchMarketRepayDealtCondParams.setBranchCode("381");
            QueryProcessor l_processor = Processors.getDefaultProcessor();
            l_processor.doDeleteAllQuery(l_branchMarketRepayDealtCondParams.getRowType());
            l_branchMarketRepayDealtCondParams = TestDBUtility.getBranchMarketRepayDealCondRow();
            TestDBUtility.insertWithDel(l_branchMarketRepayDealtCondParams);

            // 実際メソッドをコール
            WEB3GentradeMainAccount l_mainAccount =
                (WEB3GentradeMainAccount) l_equityMarginExecuteReferenceServiceImplForMock.getMainAccount();
            WEB3GentradeBranch l_branch = (WEB3GentradeBranch)l_mainAccount.getBranch();
            WEB3GentradeTradingTimeManagementForMock.mockGetTradeCloseMarket(l_branch, ProductTypeEnum.EQUITY,
                WEB3MarginTradingDivDef.MARKET_MARGIN_OR_INSTITUTION_MARGIN, new String[] {new String("4")});

            WEB3EquityMarginExecuteReferenceRequest l_request = new WEB3EquityMarginExecuteReferenceRequest();
            l_request.referenceType = WEB3EquityReferenceTypeDef.REFERENCE_TYPE_VIEW;
            l_request.productDiv = WEB3EquityProductDivDef.MARGIN;
            l_request.execType = WEB3EquityExecStatusTypeDef.EXEC_TYPE_NOT_PROMISE;
            l_request.sortKeys = new WEB3EquityMarginSortKey[1];
            l_request.sortKeys[0] = new WEB3EquityMarginSortKey();
            l_request.sortKeys[0].keyItem = WEB3EquityKeyItemDef.PRODUCTCODE;
            l_request.sortKeys[0].ascDesc = WEB3AscDescDef.ASC;
            l_request.pageIndex = "1";
            l_request.pageSize = "1";
            l_request.productCode = null;
            l_request.marketCode = null;
            WEB3EquityMarginExecuteReferenceResponse l_response =
                (WEB3EquityMarginExecuteReferenceResponse)l_equityMarginExecuteReferenceServiceImplForMock.
                execute(l_request);

            // 予想結果と実際結果の比較
            assertEquals("4", l_response.marketList[0]);
            assertEquals(WEB3DateUtility.getDate("20070111","yyyyMMdd"), l_response.orderBizDateList[0]);
            assertEquals("4", l_response.messageSuspension[0].toString());
        }
        catch (WEB3BaseException l_ex)
        {
            log.debug(STR_METHOD_NAME, l_ex);
            fail();
        }
        catch (Exception l_ex)
        {
            log.error("", l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    

    //リクエスト.照会区分＝"0：注文約定照会"の場合 沒有validate取引可能顧客
    //商品区分為1： 現物株式
    public void test_execute_C0012()
    {
        final String STR_METHOD_NAME = ".test_execute_C0012()";
        log.entering(TEST_START + STR_METHOD_NAME);

        caseNumber ="test_execute_C0011";
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl", "getAccountId",
            new Class[] {}, new Long(333812512246L));
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue("webbroker3.gentrade.WEB3GentradeBranch",
            "isMarginTradeEnforcement", new Class[] {String.class}, Boolean.TRUE);
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue("webbroker3.gentrade.WEB3GentradeMainAccount",
            "isMarginAccountEstablished", new Class[] {String.class}, Boolean.TRUE);
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "webbroker3.gentrade.WEB3GentradeOrderValidator",
            "validateAccountForTrading",
            new Class[] {WEB3GentradeMainAccount.class},
            new OrderValidationResult(ProcessingResult.SUCCESS_RESULT));

        try
        {
            // テーブルへデータをインサート
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setMarginGenAccOpenDiv(WEB3MarginAccountOpenDivDef.MARGIN_ACCOUNT_OPEN);
            l_mainAccountParams.setMarginSysAccOpenDiv(WEB3MarginAccountOpenDivDef.MARGIN_ACCOUNT_OPEN);
            l_mainAccountParams.setPtsAccOpenDiv("0");
            TestDBUtility.insertWithDel(l_mainAccountParams);

            BranchParams l_branchParams =TestDBUtility.getBranchRow();
            TestDBUtility.insertWithDel(l_branchParams);

            SubAccountParams l_subAccountParams =TestDBUtility.getSubAccountRow();
            l_subAccountParams.setAccountId(333812512246L);
            TestDBUtility.insertWithDel(l_subAccountParams);

            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            TestDBUtility.insertWithDel(l_institutionParams);

            EqtypeProductParams l_eqtypeProduct = TestDBUtility.getEqtypeProductRow();
            TestDBUtility.insertWithDel(l_eqtypeProduct);

            ProductParams l_productParams = TestDBUtility.getProductRow();
            TestDBUtility.insertWithDel(l_productParams);            

            MarketParams l_marketParams = TestDBUtility.getMarketRow();
            l_marketParams.setMarketCode(WEB3MarketCodeDef.TOKYO);
            TestDBUtility.insertWithDel(l_marketParams);

            TradedProductParams l_tradedProductParams = TestDBUtility.getTradedProductRow();
            l_tradedProductParams.setProductId(0L);
            l_tradedProductParams.setMarketId(3303L);
            TestDBUtility.insertWithDel(l_tradedProductParams);

            BranchMarketRepayDealtCondParams l_branchMarketRepayDealtCondParams =
                new BranchMarketRepayDealtCondParams();
            l_branchMarketRepayDealtCondParams.setInstitutionCode("0D");
            l_branchMarketRepayDealtCondParams.setBranchCode("381");
            QueryProcessor l_processor = Processors.getDefaultProcessor();
            l_processor.doDeleteAllQuery(l_branchMarketRepayDealtCondParams.getRowType());
            l_branchMarketRepayDealtCondParams = TestDBUtility.getBranchMarketRepayDealCondRow();
            TestDBUtility.insertWithDel(l_branchMarketRepayDealtCondParams);

            // 実際メソッドをコール
            WEB3GentradeMainAccount l_mainAccount =
                (WEB3GentradeMainAccount) l_equityMarginExecuteReferenceServiceImplForMock.getMainAccount();
            WEB3GentradeBranch l_branch = (WEB3GentradeBranch)l_mainAccount.getBranch();
            WEB3GentradeTradingTimeManagementForMock.mockGetTradeCloseMarket(l_branch, ProductTypeEnum.EQUITY,
                WEB3MarginTradingDivDef.MARKET_MARGIN_OR_INSTITUTION_MARGIN, new String[] {new String("4")});

            WEB3EquityMarginExecuteReferenceRequest l_request = new WEB3EquityMarginExecuteReferenceRequest();
            l_request.referenceType = WEB3EquityReferenceTypeDef.REFERENCE_TYPE_VIEW;
            l_request.productDiv = WEB3EquityProductDivDef.EQUITY;
            l_request.execType = WEB3EquityExecStatusTypeDef.EXEC_TYPE_NOT_PROMISE;
            l_request.sortKeys = new WEB3EquityMarginSortKey[1];
            l_request.sortKeys[0] = new WEB3EquityMarginSortKey();
            l_request.sortKeys[0].keyItem = WEB3EquityKeyItemDef.PRODUCTCODE;
            l_request.sortKeys[0].ascDesc = WEB3AscDescDef.ASC;
            l_request.pageIndex = "1";
            l_request.pageSize = "1";
            l_request.productCode = null;
            l_request.marketCode = null;
            WEB3EquityMarginExecuteReferenceResponse l_response =
                (WEB3EquityMarginExecuteReferenceResponse)l_equityMarginExecuteReferenceServiceImplForMock.
                execute(l_request);

            // 予想結果と実際結果の比較
            assertEquals("1", l_response.marketList[0]);
            assertEquals(WEB3DateUtility.getDate("20070111","yyyyMMdd"), l_response.orderBizDateList[0]);
            assertEquals("1", l_response.messageSuspension[0].toString());
        }
        catch (WEB3BaseException l_ex)
        {
            log.debug(STR_METHOD_NAME, l_ex);
            fail();
        }
        catch (Exception l_ex)
        {
            log.error("", l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    //isPTS口座開設( ) true
    public void test_execute_C0013()
    {
        final String STR_METHOD_NAME = ".test_execute_C0013()";
        log.entering(TEST_START + STR_METHOD_NAME);

        caseNumber ="test_execute_C0011";
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl", "getAccountId",
            new Class[] {}, new Long(333812512246L));
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue("webbroker3.gentrade.WEB3GentradeBranch",
            "isMarginTradeEnforcement", new Class[] {String.class}, Boolean.TRUE);
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue("webbroker3.gentrade.WEB3GentradeMainAccount",
            "isMarginAccountEstablished", new Class[] {String.class}, Boolean.TRUE);
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "webbroker3.gentrade.WEB3GentradeOrderValidator",
            "validateAccountForTrading",
            new Class[] {WEB3GentradeMainAccount.class},
            new OrderValidationResult(ProcessingResult.SUCCESS_RESULT));

        try
        {
            // テーブルへデータをインサート
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setMarginGenAccOpenDiv(WEB3MarginAccountOpenDivDef.MARGIN_ACCOUNT_OPEN);
            l_mainAccountParams.setMarginSysAccOpenDiv(WEB3MarginAccountOpenDivDef.MARGIN_ACCOUNT_OPEN);
            l_mainAccountParams.setPtsAccOpenDiv("1");
            TestDBUtility.insertWithDel(l_mainAccountParams);

            BranchParams l_branchParams =TestDBUtility.getBranchRow();
            TestDBUtility.insertWithDel(l_branchParams);

            SubAccountParams l_subAccountParams =TestDBUtility.getSubAccountRow();
            l_subAccountParams.setAccountId(333812512246L);
            TestDBUtility.insertWithDel(l_subAccountParams);

            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            TestDBUtility.insertWithDel(l_institutionParams);

            EqtypeProductParams l_eqtypeProduct = TestDBUtility.getEqtypeProductRow();
            TestDBUtility.insertWithDel(l_eqtypeProduct);

            ProductParams l_productParams = TestDBUtility.getProductRow();
            TestDBUtility.insertWithDel(l_productParams);            

            MarketParams l_marketParams = TestDBUtility.getMarketRow();
            l_marketParams.setMarketCode(WEB3MarketCodeDef.TOKYO);
            TestDBUtility.insertWithDel(l_marketParams);

            TradedProductParams l_tradedProductParams = TestDBUtility.getTradedProductRow();
            l_tradedProductParams.setProductId(0L);
            l_tradedProductParams.setMarketId(3303L);
            TestDBUtility.insertWithDel(l_tradedProductParams);

            BranchMarketRepayDealtCondParams l_branchMarketRepayDealtCondParams =
                new BranchMarketRepayDealtCondParams();
            l_branchMarketRepayDealtCondParams.setInstitutionCode("0D");
            l_branchMarketRepayDealtCondParams.setBranchCode("381");
            QueryProcessor l_processor = Processors.getDefaultProcessor();
            l_processor.doDeleteAllQuery(l_branchMarketRepayDealtCondParams.getRowType());
            l_branchMarketRepayDealtCondParams = TestDBUtility.getBranchMarketRepayDealCondRow();
            TestDBUtility.insertWithDel(l_branchMarketRepayDealtCondParams);

            // 実際メソッドをコール
            WEB3GentradeMainAccount l_mainAccount =
                (WEB3GentradeMainAccount) l_equityMarginExecuteReferenceServiceImplForMock.getMainAccount();
            WEB3GentradeBranch l_branch = (WEB3GentradeBranch)l_mainAccount.getBranch();
            WEB3GentradeTradingTimeManagementForMock.mockGetTradeCloseMarket(l_branch, ProductTypeEnum.EQUITY,
                WEB3MarginTradingDivDef.MARKET_MARGIN_OR_INSTITUTION_MARGIN, new String[] {new String("4")});

            WEB3EquityMarginExecuteReferenceRequest l_request = new WEB3EquityMarginExecuteReferenceRequest();
            l_request.referenceType = WEB3EquityReferenceTypeDef.REFERENCE_TYPE_UPDATE;
            l_request.productDiv = WEB3EquityProductDivDef.EQUITY;
            l_request.execType = WEB3EquityExecStatusTypeDef.EXEC_TYPE_NOT_PROMISE;
            l_request.sortKeys = new WEB3EquityMarginSortKey[1];
            l_request.sortKeys[0] = new WEB3EquityMarginSortKey();
            l_request.sortKeys[0].keyItem = WEB3EquityKeyItemDef.PRODUCTCODE;
            l_request.sortKeys[0].ascDesc = WEB3AscDescDef.ASC;
            l_request.pageIndex = "1";
            l_request.pageSize = "1";
            l_request.productCode = null;
            l_request.marketCode = null;
            WEB3EquityMarginExecuteReferenceResponse l_response =
                (WEB3EquityMarginExecuteReferenceResponse)l_equityMarginExecuteReferenceServiceImplForMock.
                execute(l_request);

            // 予想結果と実際結果の比較
            assertEquals("1", l_response.marketList[0]);
            assertEquals(WEB3DateUtility.getDate("20070111","yyyyMMdd"), l_response.orderBizDateList[0]);
            assertEquals("1", l_response.messageSuspension[0].toString());
            
//            WEB3GentradeTradingClendarContext l_clendarContext =
//                (WEB3GentradeTradingClendarContext)ThreadLocalSystemAttributesRegistry.getAttribute(
//                "web3.tradingcalendarcontext");
//            
//            assertEquals("1", l_clendarContext.getMarketCode());
        }
        catch (WEB3BaseException l_ex)
        {
            log.debug(STR_METHOD_NAME, l_ex);
            fail();
        }
        catch (Exception l_ex)
        {
            log.error("", l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    //isPTS口座開設( ) true
    public void test_execute_C0014()
    {
        final String STR_METHOD_NAME = ".test_execute_C0014()";
        log.entering(TEST_START + STR_METHOD_NAME);

        caseNumber ="test_execute_C0011";
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl", "getAccountId",
            new Class[] {}, new Long(333812512246L));
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue("webbroker3.gentrade.WEB3GentradeBranch",
            "isMarginTradeEnforcement", new Class[] {String.class}, Boolean.TRUE);
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue("webbroker3.gentrade.WEB3GentradeMainAccount",
            "isMarginAccountEstablished", new Class[] {String.class}, Boolean.TRUE);
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "webbroker3.gentrade.WEB3GentradeOrderValidator",
            "validateAccountForTrading",
            new Class[] {WEB3GentradeMainAccount.class},
            new OrderValidationResult(ProcessingResult.SUCCESS_RESULT));

        try
        {
            // テーブルへデータをインサート
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setMarginGenAccOpenDiv(WEB3MarginAccountOpenDivDef.MARGIN_ACCOUNT_OPEN);
            l_mainAccountParams.setMarginSysAccOpenDiv(WEB3MarginAccountOpenDivDef.MARGIN_ACCOUNT_OPEN);
            l_mainAccountParams.setPtsAccOpenDiv("1");
            TestDBUtility.insertWithDel(l_mainAccountParams);

            BranchParams l_branchParams =TestDBUtility.getBranchRow();
            TestDBUtility.insertWithDel(l_branchParams);

            SubAccountParams l_subAccountParams =TestDBUtility.getSubAccountRow();
            l_subAccountParams.setAccountId(333812512246L);
            TestDBUtility.insertWithDel(l_subAccountParams);

            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            TestDBUtility.insertWithDel(l_institutionParams);

            EqtypeProductParams l_eqtypeProduct = TestDBUtility.getEqtypeProductRow();
            TestDBUtility.insertWithDel(l_eqtypeProduct);

            ProductParams l_productParams = TestDBUtility.getProductRow();
            TestDBUtility.insertWithDel(l_productParams);            

            MarketParams l_marketParams = TestDBUtility.getMarketRow();
            l_marketParams.setMarketCode(WEB3MarketCodeDef.TOKYO);
            TestDBUtility.insertWithDel(l_marketParams);

            TestDBUtility.deleteAll(OrderAcceptStatusRow.TYPE);
            
            TradedProductParams l_tradedProductParams = TestDBUtility.getTradedProductRow();
            l_tradedProductParams.setProductId(0L);
            l_tradedProductParams.setMarketId(3303L);
            TestDBUtility.insertWithDel(l_tradedProductParams);

            BranchMarketRepayDealtCondParams l_branchMarketRepayDealtCondParams =
                new BranchMarketRepayDealtCondParams();
            l_branchMarketRepayDealtCondParams.setInstitutionCode("0D");
            l_branchMarketRepayDealtCondParams.setBranchCode("381");
            QueryProcessor l_processor = Processors.getDefaultProcessor();
            l_processor.doDeleteAllQuery(l_branchMarketRepayDealtCondParams.getRowType());
            l_branchMarketRepayDealtCondParams = TestDBUtility.getBranchMarketRepayDealCondRow();
            TestDBUtility.insertWithDel(l_branchMarketRepayDealtCondParams);

            // 実際メソッドをコール
            WEB3GentradeMainAccount l_mainAccount =
                (WEB3GentradeMainAccount) l_equityMarginExecuteReferenceServiceImplForMock.getMainAccount();
            WEB3GentradeBranch l_branch = (WEB3GentradeBranch)l_mainAccount.getBranch();
            WEB3GentradeTradingTimeManagementForMock.mockGetTradeCloseMarket(l_branch, ProductTypeEnum.EQUITY,
                WEB3MarginTradingDivDef.MARKET_MARGIN_OR_INSTITUTION_MARGIN, new String[] {new String("4")});

            WEB3EquityMarginExecuteReferenceRequest l_request = new WEB3EquityMarginExecuteReferenceRequest();
            l_request.referenceType = WEB3EquityReferenceTypeDef.REFERENCE_TYPE_UPDATE;
            l_request.productDiv = WEB3EquityProductDivDef.EQUITY;
            l_request.execType = WEB3EquityExecStatusTypeDef.EXEC_TYPE_NOT_PROMISE;
            l_request.sortKeys = new WEB3EquityMarginSortKey[1];
            l_request.sortKeys[0] = new WEB3EquityMarginSortKey();
            l_request.sortKeys[0].keyItem = WEB3EquityKeyItemDef.PRODUCTCODE;
            l_request.sortKeys[0].ascDesc = WEB3AscDescDef.ASC;
            l_request.pageIndex = "1";
            l_request.pageSize = "1";
            l_request.productCode = null;
            l_request.marketCode = "1";
            WEB3EquityMarginExecuteReferenceResponse l_response =
                (WEB3EquityMarginExecuteReferenceResponse)l_equityMarginExecuteReferenceServiceImplForMock.
                execute(l_request);

//            // 予想結果と実際結果の比較
//            assertEquals("1", l_response.marketList[0]);
//            assertEquals(WEB3DateUtility.getDate("20070111","yyyyMMdd"), l_response.orderBizDateList[0]);
//            assertEquals("1", l_response.messageSuspension[0].toString());
            WEB3GentradeTradingClendarContext l_clendarContext =
                (WEB3GentradeTradingClendarContext)ThreadLocalSystemAttributesRegistry.getAttribute(
                "web3.tradingcalendarcontext");
            
            assertEquals("1", l_clendarContext.getMarketCode());
            

        }
        catch (WEB3BaseException l_ex)
        {
            WEB3GentradeTradingClendarContext l_clendarContext =
                (WEB3GentradeTradingClendarContext)ThreadLocalSystemAttributesRegistry.getAttribute(
                "web3.tradingcalendarcontext");
            
            assertEquals("1", l_clendarContext.getMarketCode());
        }
        catch (Exception l_ex)
        {
            log.error("", l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    private class WEB3EquityMarginExecuteReferenceRequestForMock extends WEB3EquityMarginExecuteReferenceRequest
    {
        public void validate() throws WEB3BaseException
        {
            throw new WEB3BaseException(WEB3ErrorCatalog.SYSTEM_ERROR_80005, this.getClass().getName(),
                "validateというメソッドの異常");
        }
    }

    private class WEB3EquityMarginExecuteReferenceServiceImplForMock extends
        WEB3EquityMarginExecuteReferenceServiceImpl
    {
        protected OrderStatus validateOrderAccept(
            String l_strReferenceType,
            String l_strProductType,
            boolean l_blnIsMargin,
            boolean l_blnIsOffFloorExecute) throws WEB3BaseException
        {
            if ("test_execute_C0004".equals(caseNumber))
            {
                throw new WEB3BaseException(WEB3ErrorCatalog.SYSTEM_ERROR_80005, this.getClass().getName(),
                    "validateOrderAcceptというメソッドの異常");    
            }
            if ("test_execute_C0005".equals(caseNumber) || "test_execute_C0006".equals(caseNumber) ||
                "test_execute_C0007".equals(caseNumber) || "test_execute_C0008".equals(caseNumber) ||
                "test_execute_C0009".equals(caseNumber) || "test_execute_C0010".equals(caseNumber) ||
                "test_execute_C0011".equals(caseNumber))
            {
                return new OrderStatus();
            }
            return null;
        }

        protected Date[] getOrderBizDateList(String[] l_strMarketCodeList) throws WEB3BaseException
        {
            if ("test_execute_C0008".equals(caseNumber))
            {
                Date [] l_datMarketList = new Date[l_strMarketCodeList.length];
                for (int i = 0; i < l_strMarketCodeList.length; i++)
                {
                    l_datMarketList[i] = WEB3DateUtility.getDate("20070108","yyyyMMdd");
                }
                return l_datMarketList;
            }
            if ("test_execute_C0009".equals(caseNumber))
            {
                Date [] l_datMarketList = new Date[l_strMarketCodeList.length];
                for (int i = 0; i < l_strMarketCodeList.length; i++)
                {
                    l_datMarketList[i] = WEB3DateUtility.getDate("20070109","yyyyMMdd");
                }
                return l_datMarketList;
            }
            if ("test_execute_C0010".equals(caseNumber))
            {
                Date [] l_datMarketList = new Date[l_strMarketCodeList.length];
                for (int i = 0; i < l_strMarketCodeList.length; i++)
                {
                    l_datMarketList[i] = WEB3DateUtility.getDate("20070110","yyyyMMdd");
                }
                return l_datMarketList;
            }
            if ("test_execute_C0011".equals(caseNumber))
            {
                Date [] l_datMarketList = new Date[l_strMarketCodeList.length];
                for (int i = 0; i < l_strMarketCodeList.length; i++)
                {
                    l_datMarketList[i] = WEB3DateUtility.getDate("20070111","yyyyMMdd");
                }
                return l_datMarketList;
            }
            return null;
        }
    }

}
@
