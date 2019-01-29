head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.01.28.38;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3IfoDepositCalcServiceImplTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
 Copyright        : (株)大和総研 証券ソリューションシステム第二部
 File Name        : WEB3IfoDepositCalcServiceImplTest.java
 Author Name      : Daiwa Institute of Research
 Revision History : 2008/08/25 楊夫志(中訊) 新規作成 モデル 132
*/
package webbroker3.ifodeposit;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Calendar;
import java.util.Date;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.plugin.tc.gentrade.AccountManager;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ContractTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinTransactionCateg;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderCategEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderOpenStatusEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderStatusEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccountTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.BranchParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.BranchRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.InstitutionParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.InstitutionRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.SubAccountParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.SubAccountRow;
import com.fitechlabs.xtrade.plugin.tc.xbaio.data.AioOrderUnitParams;
import com.fitechlabs.xtrade.plugin.tc.xbaio.data.AioOrderUnitRow;
import com.fitechlabs.xtrade.plugin.tc.xbifo.IfoDerivativeTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoContractParams;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoContractRow;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoFinTransactionParams;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoFinTransactionRow;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoOrderUnitParams;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoOrderUnitRow;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoProductParams;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoProductRow;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoTradedProductParams;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoTradedProductRow;

import test.util.TestDBUtility;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3BranchPreferencesNameDef;
import webbroker3.common.define.WEB3IfodepositNonCalcSqProductDef;
import webbroker3.common.define.WEB3SessionTypeDef;
import webbroker3.gentrade.WEB3GentradeSubAccount;
//import webbroker3.gentrade.data.BranchIndexDealtCondParams;
//import webbroker3.gentrade.data.BranchIndexDealtCondRow;
import webbroker3.gentrade.data.BranchPreferencesParams;
import webbroker3.gentrade.data.BranchPreferencesRow;
import webbroker3.gentrade.data.IfoDepositParams;
import webbroker3.gentrade.data.IfoDepositRow;
import webbroker3.gentrade.data.ProcessManagementParams;
import webbroker3.gentrade.data.ProcessManagementRow;
import webbroker3.gentrade.data.TradingTimeParams;
import webbroker3.gentrade.data.TradingTimeRow;
import webbroker3.gentrade.data.TradingpowerCalcConditionParams;
import webbroker3.gentrade.data.TradingpowerCalcConditionRow;
import webbroker3.ifodeposit.define.WEB3IfoDepositBranchPrefNameDef;
import webbroker3.mock.TestBaseForMock;
import webbroker3.tradingpower.data.TpCashBalanceParams;
import webbroker3.tradingpower.data.TpCashBalanceRow;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

public class WEB3IfoDepositCalcServiceImplTest extends TestBaseForMock
{
    WEB3LogUtility log = WEB3LogUtility.getInstance(WEB3IfoDepositCalcServiceImplTest.class);
    
    WEB3IfoDepositCalcServiceImpl l_serviceImpl = null;
    public WEB3IfoDepositCalcServiceImplTest(String arg0)
    {
        super(arg0);
    }

    protected void setUp() throws Exception
    {
        super.setUp();
        MOCK_MANAGER.setIsMockUsed(true);
        l_serviceImpl = new WEB3IfoDepositCalcServiceImpl();
    }

    protected void tearDown() throws Exception
    {
        super.tearDown();
    }

    //WEB3SystemLayerException
    public void testGetIfoDepositCalcWEB3GentradeSubAccount_C0001()
    {
        final String STR_METHOD_NAME = "testGetIfoDepositCalcWEB3GentradeSubAccount_C0001()";
        log.entering(STR_METHOD_NAME);

        try
        {
            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            SubAccountParams subAccountParams = TestDBUtility.getSubAccountRow();
            subAccountParams.setAccountId(1001);
            subAccountParams.setSubAccountId(1002);
            TestDBUtility.insertWithDel(subAccountParams);
            
            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
            AccountManager l_accountMgr = l_finApp.getAccountManager();
            WEB3GentradeSubAccount l_subAccount =
                (WEB3GentradeSubAccount) l_accountMgr.getSubAccount(1001, 1002);

            WEB3IfoDepositCalcServiceImplForTest implForTest = new WEB3IfoDepositCalcServiceImplForTest();
            implForTest.setFlag(false);
            implForTest.getIfoDepositCalc(l_subAccount);
        }
        catch(WEB3SystemLayerException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80002, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }

        log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>test past");
        log.exiting(STR_METHOD_NAME);
    }

    //証拠金計算を生成し、返却する。
    public void testGetIfoDepositCalcWEB3GentradeSubAccount_C0002()
    {
        final String STR_METHOD_NAME = "testGetIfoDepositCalcWEB3GentradeSubAccount_C0002()";
        log.entering(STR_METHOD_NAME);

        try
        {
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            MainAccountParams mainAccountParams = TestDBUtility.getMainAccountRow();
            TestDBUtility.insertWithDel(mainAccountParams);
            
            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            SubAccountParams subAccountParams = TestDBUtility.getSubAccountRow();
            subAccountParams.setAccountId(333812512246L);
            TestDBUtility.insertWithDel(subAccountParams);

            TestDBUtility.deleteAll(AioOrderUnitRow.TYPE);
            AioOrderUnitParams aioOrderUnitParams = TestDBUtility.getAioOrderUnitRow();
            aioOrderUnitParams.setAccountId(333812512246L);
            aioOrderUnitParams.setSubAccountId(33381251220301L);
            aioOrderUnitParams.setBizDate("20080825");
            aioOrderUnitParams.setOrderStatus(OrderStatusEnum.ACCEPTED);
            TestDBUtility.insertWithDel(aioOrderUnitParams);

            TestDBUtility.deleteAll(IfoOrderUnitRow.TYPE);
            IfoOrderUnitParams ifoOrderUnitParams = TestDBUtility.getIfoOrderUnitRow();
            ifoOrderUnitParams.setAccountId(333812512246L);
            ifoOrderUnitParams.setSubAccountId(33381251220301L);
            ifoOrderUnitParams.setBizDate("20080825");
            ifoOrderUnitParams.setOrderCateg(OrderCategEnum.ASSET);
            ifoOrderUnitParams.setOrderOpenStatus(OrderOpenStatusEnum.OPEN);
            ifoOrderUnitParams.setSessionType(WEB3SessionTypeDef.EVENING_SESSION);
            TestDBUtility.insertWithDel(ifoOrderUnitParams);

            TestDBUtility.deleteAll(TpCashBalanceRow.TYPE);
            TpCashBalanceParams cashBalanceParams = TestDBUtility.getTpCashBalanceRow();
            cashBalanceParams.setAccountId(333812512246L);
            cashBalanceParams.setSubAccountId(33381251220301L);
            TestDBUtility.insertWithDel(cashBalanceParams);

            TestDBUtility.deleteAll(IfoTradedProductRow.TYPE);
            IfoTradedProductParams ifoTradedProductParams = TestDBUtility.getIfoTradedProductRow();
            ifoTradedProductParams.setProductId(1006149081018L);
            ifoTradedProductParams.setMarketId(1002);
            TestDBUtility.insertWithDel(ifoTradedProductParams);

            TestDBUtility.deleteAll(IfoProductRow.TYPE);
            IfoProductParams ifoProductParams = TestDBUtility.getIfoProductRow();
            ifoProductParams.setProductId(1006149081018L);
            TestDBUtility.insertWithDel(ifoProductParams);

            TestDBUtility.deleteAll(IfoContractRow.TYPE);
            IfoContractParams ifoContractParams = TestDBUtility.getIfoContractRow();
            ifoContractParams.setAccountId(333812512246L);
            ifoContractParams.setSubAccountId(33381251220301L);
            TestDBUtility.insertWithDel(ifoContractParams);

            TestDBUtility.deleteAll(IfoFinTransactionRow.TYPE);
            IfoFinTransactionParams finTransactionParams = TestDBUtility.getIfoFinTransactionRow();
            finTransactionParams.setAccountId(333812512246L);
            finTransactionParams.setSubAccountId(33381251220301L);
            finTransactionParams.setOrderUnitId(1001);
            finTransactionParams.setContractId(1001);
            finTransactionParams.setFinTransactionCateg(FinTransactionCateg.EQTYPE_IDX_FUTURES_CLOSE);
            finTransactionParams.setFinTransactionTimestamp(WEB3DateUtility.getDate("20080816", "yyyyMMdd"));
            TestDBUtility.insertWithDel(finTransactionParams);

            TestDBUtility.deleteAll(TradingTimeRow.TYPE);
            TradingTimeParams tradingTimeParams = TestDBUtility.getTradingTimeRow();
            tradingTimeParams.setInstitutionCode("0D");
            tradingTimeParams.setBranchCode("123");
            tradingTimeParams.setMarketCode("N1");
            tradingTimeParams.setTradingTimeType("11");
            tradingTimeParams.setBizDateType("1");
            tradingTimeParams.setProductCode("0005");
            TradingTimeParams tradingTimeParams2 = TestDBUtility.getTradingTimeRow();
            tradingTimeParams2.setInstitutionCode("0D");
            tradingTimeParams2.setBranchCode("123");
            tradingTimeParams2.setMarketCode("N1");
            tradingTimeParams2.setTradingTimeType("01");
            tradingTimeParams2.setBizDateType("1");
            tradingTimeParams2.setProductCode("0005");
            TradingTimeParams tradingTimeParams3 = TestDBUtility.getTradingTimeRow();
            tradingTimeParams3.setInstitutionCode("0D");
            tradingTimeParams3.setBranchCode("123");
            tradingTimeParams3.setMarketCode("N1");
            tradingTimeParams3.setTradingTimeType("13");
            tradingTimeParams3.setBizDateType("1");
            tradingTimeParams3.setProductCode("0");
            TestDBUtility.insertWithDel(tradingTimeParams);
            TestDBUtility.insertWithDel(tradingTimeParams2);
            TestDBUtility.insertWithDel(tradingTimeParams3);

            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
            AccountManager l_accountMgr = l_finApp.getAccountManager();
            WEB3GentradeSubAccount l_subAccount =
                (WEB3GentradeSubAccount) l_accountMgr.getSubAccount(333812512246L, 33381251220301L);

            WEB3IfoDepositCalcServiceImplForTest implForTest = new WEB3IfoDepositCalcServiceImplForTest();
            implForTest.setFlag(true);
            implForTest.getIfoDepositCalc(l_subAccount);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }

        log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>test past");
        log.exiting(STR_METHOD_NAME);
    }

    //WEB3SystemLayerException
    public void testGetIfoDepositCalcWEB3GentradeSubAccountWEB3IfoNewOrderSpec_C0001()
    {
        final String STR_METHOD_NAME = "testGetIfoDepositCalcWEB3GentradeSubAccountWEB3IfoNewOrderSpec_C0001()";
        log.entering(STR_METHOD_NAME);

        try
        {
            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
            AccountManager l_accountMgr = l_finApp.getAccountManager();
            WEB3GentradeSubAccount l_subAccount =
                (WEB3GentradeSubAccount) l_accountMgr.getSubAccount(333812512246L, 33381251220301L);

            WEB3IfoNewOrderSpec l_ifoNewOrderSpec = new WEB3IfoNewOrderSpec();
            WEB3IfoDepositCalcServiceImplForTest implForTest = new WEB3IfoDepositCalcServiceImplForTest();
            implForTest.setFlag(false);
            implForTest.getIfoDepositCalc(l_subAccount, l_ifoNewOrderSpec);
        }
        catch(WEB3SystemLayerException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80002, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }

        log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>test past");
        log.exiting(STR_METHOD_NAME);
    }

    //証拠金計算を生成し、返却する。
    public void testGetIfoDepositCalcWEB3GentradeSubAccountWEB3IfoNewOrderSpec_C0002()
    {
        final String STR_METHOD_NAME = "testGetIfoDepositCalcWEB3GentradeSubAccountWEB3IfoNewOrderSpec_C0002()";
        log.entering(STR_METHOD_NAME);

        try
        {
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            MainAccountParams mainAccountParams = TestDBUtility.getMainAccountRow();
            TestDBUtility.insertWithDel(mainAccountParams);
            
            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            SubAccountParams subAccountParams = TestDBUtility.getSubAccountRow();
            subAccountParams.setAccountId(333812512246L);
            TestDBUtility.insertWithDel(subAccountParams);

            TestDBUtility.deleteAll(AioOrderUnitRow.TYPE);
            AioOrderUnitParams aioOrderUnitParams = TestDBUtility.getAioOrderUnitRow();
            aioOrderUnitParams.setAccountId(333812512246L);
            aioOrderUnitParams.setSubAccountId(33381251220301L);
            aioOrderUnitParams.setBizDate("20080825");
            aioOrderUnitParams.setOrderStatus(OrderStatusEnum.ACCEPTED);
            TestDBUtility.insertWithDel(aioOrderUnitParams);

            TestDBUtility.deleteAll(IfoOrderUnitRow.TYPE);
            IfoOrderUnitParams ifoOrderUnitParams = TestDBUtility.getIfoOrderUnitRow();
            ifoOrderUnitParams.setAccountId(333812512246L);
            ifoOrderUnitParams.setSubAccountId(33381251220301L);
            ifoOrderUnitParams.setBizDate("20080825");
            ifoOrderUnitParams.setOrderCateg(OrderCategEnum.IDX_FUTURES_OPEN);
            ifoOrderUnitParams.setOrderOpenStatus(OrderOpenStatusEnum.OPEN);
            ifoOrderUnitParams.setSessionType(WEB3SessionTypeDef.EVENING_SESSION);
            TestDBUtility.insertWithDel(ifoOrderUnitParams);

            TestDBUtility.deleteAll(TpCashBalanceRow.TYPE);
            TpCashBalanceParams cashBalanceParams = TestDBUtility.getTpCashBalanceRow();
            cashBalanceParams.setAccountId(333812512246L);
            cashBalanceParams.setSubAccountId(33381251220301L);
            TestDBUtility.insertWithDel(cashBalanceParams);

            TestDBUtility.deleteAll(IfoTradedProductRow.TYPE);
            IfoTradedProductParams ifoTradedProductParams = TestDBUtility.getIfoTradedProductRow();
            ifoTradedProductParams.setTradedProductId(1);
            ifoTradedProductParams.setProductId(1006149081018L);
            ifoTradedProductParams.setMarketId(1002);
            IfoTradedProductParams ifoTradedProductParams1 = TestDBUtility.getIfoTradedProductRow();
            ifoTradedProductParams1.setTradedProductId(2);
            ifoTradedProductParams1.setProductId(22);
            ifoTradedProductParams1.setMarketId(33);
            IfoTradedProductParams ifoTradedProductParams2 = TestDBUtility.getIfoTradedProductRow();
            ifoTradedProductParams2.setTradedProductId(3);
            ifoTradedProductParams2.setProductId(1006169090018L);
            ifoTradedProductParams2.setMarketId(1002);
            TestDBUtility.insertWithDel(ifoTradedProductParams);
            TestDBUtility.insertWithDel(ifoTradedProductParams1);
            TestDBUtility.insertWithDel(ifoTradedProductParams2);

            TestDBUtility.deleteAll(IfoProductRow.TYPE);
            IfoProductParams ifoProductParams = TestDBUtility.getIfoProductRow();
            ifoProductParams.setProductId(1006149081018L);
            IfoProductParams ifoProductParams2 = TestDBUtility.getIfoProductRow();
            ifoProductParams2.setProductId(22);
            ifoProductParams2.setInstitutionCode("123");
            ifoProductParams2.setUnderlyingProductCode("345");
            ifoProductParams2.setDerivativeType(IfoDerivativeTypeEnum.CALL_OPTIONS);
            ifoProductParams2.setStrikePrice(111);
            ifoProductParams2.setMonthOfDelivery("100");
            ifoProductParams2.setExerciseDate(WEB3DateUtility.getDate("20080816", "yyyyMMdd"));
            ifoProductParams2.setCreatedTimestamp(WEB3DateUtility.getDate("20080816", "yyyyMMdd"));
            ifoProductParams2.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20080816", "yyyyMMdd"));
            IfoProductParams ifoProductParams3 = TestDBUtility.getIfoProductRow();
            ifoProductParams3.setProductId(1006169090018L);
            ifoProductParams3.setInstitutionCode("11");
            ifoProductParams3.setProductCode("11");
            ifoProductParams3.setUnderlyingProductCode("11");
            ifoProductParams3.setDerivativeType(IfoDerivativeTypeEnum.OTHER);
            ifoProductParams3.setStrikePrice(12);
            ifoProductParams3.setMonthOfDelivery("200805");
            ifoProductParams3.setExerciseDate(WEB3DateUtility.getDate("20080530","yyyyMMdd"));
            ifoProductParams3.setCreatedTimestamp(Calendar.getInstance().getTime());
            ifoProductParams3.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20080530","yyyyMMdd"));
            TestDBUtility.insertWithDel(ifoProductParams);
            TestDBUtility.insertWithDel(ifoProductParams2);
            TestDBUtility.insertWithDel(ifoProductParams3);

            TestDBUtility.deleteAll(IfoContractRow.TYPE);
            IfoContractParams ifoContractParams = TestDBUtility.getIfoContractRow();
            ifoContractParams.setAccountId(333812512246L);
            ifoContractParams.setSubAccountId(33381251220301L);
            TestDBUtility.insertWithDel(ifoContractParams);

            TestDBUtility.deleteAll(IfoFinTransactionRow.TYPE);
            IfoFinTransactionParams finTransactionParams = TestDBUtility.getIfoFinTransactionRow();
            finTransactionParams.setAccountId(333812512246L);
            finTransactionParams.setSubAccountId(33381251220301L);
            finTransactionParams.setOrderUnitId(1001);
            finTransactionParams.setContractId(1001);
            finTransactionParams.setFinTransactionCateg(FinTransactionCateg.EQTYPE_IDX_FUTURES_CLOSE);
            finTransactionParams.setFinTransactionTimestamp(WEB3DateUtility.getDate("20080816", "yyyyMMdd"));
            TestDBUtility.insertWithDel(finTransactionParams);

            TestDBUtility.deleteAll(TradingTimeRow.TYPE);
            TradingTimeParams tradingTimeParams = TestDBUtility.getTradingTimeRow();
            tradingTimeParams.setInstitutionCode("0D");
            tradingTimeParams.setBranchCode("123");
            tradingTimeParams.setMarketCode("N1");
            tradingTimeParams.setTradingTimeType("11");
            tradingTimeParams.setBizDateType("1");
            tradingTimeParams.setProductCode("0005");
            TradingTimeParams tradingTimeParams2 = TestDBUtility.getTradingTimeRow();
            tradingTimeParams2.setInstitutionCode("0D");
            tradingTimeParams2.setBranchCode("123");
            tradingTimeParams2.setMarketCode("N1");
            tradingTimeParams2.setTradingTimeType("01");
            tradingTimeParams2.setBizDateType("1");
            tradingTimeParams2.setProductCode("0005");
            TradingTimeParams tradingTimeParams3 = TestDBUtility.getTradingTimeRow();
            tradingTimeParams3.setInstitutionCode("0D");
            tradingTimeParams3.setBranchCode("123");
            tradingTimeParams3.setMarketCode("N1");
            tradingTimeParams3.setTradingTimeType("13");
            tradingTimeParams3.setBizDateType("1");
            tradingTimeParams3.setProductCode("0");
            TradingTimeParams tradingTimeParams4 = TestDBUtility.getTradingTimeRow();
            tradingTimeParams4.setInstitutionCode("0D");
            tradingTimeParams4.setBranchCode("123");
            tradingTimeParams4.setMarketCode("N1");
            tradingTimeParams4.setTradingTimeType("11");
            tradingTimeParams4.setBizDateType("1");
            tradingTimeParams4.setProductCode("11");
            TradingTimeParams tradingTimeParams5 = TestDBUtility.getTradingTimeRow();
            tradingTimeParams5.setInstitutionCode("0D");
            tradingTimeParams5.setBranchCode("123");
            tradingTimeParams5.setMarketCode("N1");
            tradingTimeParams5.setTradingTimeType("11");
            tradingTimeParams5.setBizDateType("1");
            tradingTimeParams5.setProductCode("345");
            TestDBUtility.insertWithDel(tradingTimeParams);
            TestDBUtility.insertWithDel(tradingTimeParams2);
            TestDBUtility.insertWithDel(tradingTimeParams3);
            TestDBUtility.insertWithDel(tradingTimeParams4);
            TestDBUtility.insertWithDel(tradingTimeParams5);

            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
            AccountManager l_accountMgr = l_finApp.getAccountManager();
            WEB3GentradeSubAccount l_subAccount =
                (WEB3GentradeSubAccount) l_accountMgr.getSubAccount(333812512246L, 33381251220301L);

            WEB3IfoNewOrderSpec l_ifoNewOrderSpec = new WEB3IfoNewOrderSpec();
            l_ifoNewOrderSpec.orderUnitId = 11;
            l_ifoNewOrderSpec.productId = 22;
            l_ifoNewOrderSpec.marketId = 33;
            l_ifoNewOrderSpec.orderBizDate = WEB3DateUtility.getDate("20080526", "yyyyMMdd");
            l_ifoNewOrderSpec.contractType = ContractTypeEnum.LONG;
            l_ifoNewOrderSpec.ifoDerivativeType = IfoDerivativeTypeEnum.FUTURES;
            l_ifoNewOrderSpec.orderBizDate = WEB3DateUtility.getDate("20080526", "yyyyMMdd");
            l_ifoNewOrderSpec.deliveryDate = WEB3DateUtility.getDate("20080526", "yyyyMMdd");
            l_ifoNewOrderSpec.quantity = 100;
            l_ifoNewOrderSpec.estimatedNetAmount = 100;

            WEB3IfoDepositCalcServiceImplForTest implForTest = new WEB3IfoDepositCalcServiceImplForTest();
            implForTest.setFlag(true);
            implForTest.getIfoDepositCalc(l_subAccount, l_ifoNewOrderSpec);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }

        log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>test past");
        log.exiting(STR_METHOD_NAME);
    }

//    //部店別証拠金計算条件をセット
//    //add部店別証拠金計算条件=null
//    public void testCreateIfoDepositCalcCondition_C0001()
//    {
//        final String STR_METHOD_NAME = "testCreateIfoDepositCalcCondition_C0001()";
//        log.entering(STR_METHOD_NAME);
//
//        try
//        {
//            TestDBUtility.deleteAll(MainAccountRow.TYPE);
//            MainAccountParams mainAccountParams = TestDBUtility.getMainAccountRow();
//            TestDBUtility.insertWithDel(mainAccountParams);
//            
//            TestDBUtility.deleteAll(SubAccountRow.TYPE);
//            SubAccountParams subAccountParams = TestDBUtility.getSubAccountRow();
//            subAccountParams.setAccountId(333812512246L);
//            TestDBUtility.insertWithDel(subAccountParams);
//       
//            TestDBUtility.deleteAll(InstitutionRow.TYPE);
//            InstitutionParams institutionParams = TestDBUtility.getInstitutionRow();
//            institutionParams.setIfoRealPriceCalcDiv("0");
//            TestDBUtility.insertWithDel(institutionParams);
//
//            TestDBUtility.deleteAll(BranchRow.TYPE);
//            BranchParams branchParams = TestDBUtility.getBranchRow();
//            TestDBUtility.insertWithDel(branchParams);
//
//            TestDBUtility.deleteAll(ProcessManagementRow.TYPE);
//            ProcessManagementParams managementParams = new ProcessManagementParams();
//            managementParams.setProcessId("0001");
//            managementParams.setInstitutionCode("0D");
//            managementParams.setBranchCode("381");
//            ProcessManagementParams managementParams1 = new ProcessManagementParams();
//            managementParams1.setProcessId("0008");
//            managementParams1.setInstitutionCode("0D");
//            managementParams1.setBranchCode("381");
//            TestDBUtility.insertWithDel(managementParams);
//            TestDBUtility.insertWithDel(managementParams1);
//
//            TestDBUtility.deleteAll(BranchIndexDealtCondRow.TYPE);
//            BranchIndexDealtCondParams condParams = TestDBUtility.getBranchIndexDealtCondRow();
//            condParams.setInstitutionCode("0D");
//            condParams.setBranchCode("381");
//            TestDBUtility.insertWithDel(condParams);
//
//            TestDBUtility.deleteAll(TradingTimeRow.TYPE);
//            TradingTimeParams tradingTimeParams = TestDBUtility.getTradingTimeRow();
//            tradingTimeParams.setInstitutionCode("0D");
//            tradingTimeParams.setBranchCode("123");
//            tradingTimeParams.setMarketCode("N1");
//            tradingTimeParams.setTradingTimeType("01");
//            tradingTimeParams.setBizDateType("1");
//            tradingTimeParams.setProductCode("0");
//            TestDBUtility.insertWithDel(tradingTimeParams);
//
//            TestDBUtility.deleteAll(TradingpowerCalcConditionRow.TYPE);
//            TradingpowerCalcConditionParams calcConditionParams = TestDBUtility.getTradingpowerCalcConditionRow();
//            calcConditionParams.setAccountId(333812512246L);
//            TestDBUtility.insertWithDel(calcConditionParams);
//            
//            TestDBUtility.deleteAll(BranchPreferencesRow.TYPE);
//            BranchPreferencesParams l_branchPreferencesParams = TestDBUtility.getBranchPreferencesRow();
//            l_branchPreferencesParams.setBranchId(87871);
//            l_branchPreferencesParams.setName("evening.session.div");
//            l_branchPreferencesParams.setNameSerialNo(2);
//            l_branchPreferencesParams.setValue("2");
//            TestDBUtility.insertWithDel(l_branchPreferencesParams);
//            
//            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
//            AccountManager l_accountMgr = l_finApp.getAccountManager();
//            WEB3GentradeSubAccount l_subAccount =
//                (WEB3GentradeSubAccount) l_accountMgr.getSubAccount(333812512246L, 33381251220301L);
//
//            WEB3IfoDepositCalcCondition condition = l_serviceImpl.createIfoDepositCalcCondition(l_subAccount);
//            assertTrue(condition.isIfoDepositMailFlag());
//            assertTrue(condition.isQuickReportReceived());
//            assertFalse(condition.isEveningSessionEnforcemented());
//            assertEquals(condition.getPreBizDateIfoDepositLackCharge() + "", "0.0");
//            assertEquals(condition.getCurrentBizDateIfoDepositLackCharge() + "", "0.0");
//            assertFalse(condition.isNewOpenTradingPowerAvailable());
//            assertFalse(condition.isLackChargeNonManagement());
//            assertFalse(condition.isIfodepositNonCalcSqProductFlag());
//            assertFalse(condition.isRealPriceIfoDepositCalc());
//            assertFalse(condition.isSimpleSPANCalc());
//            assertFalse(condition.isSPANTrouble());
//            assertEquals(condition.getSPANFactor() + "", "0.0");
//            assertEquals(condition.getSPANFactorRed() + "", "0.0");
//            assertEquals(condition.getTransferPowerFactor() + "", "0.0");
//            assertEquals(condition.getMinIfoDeposit() + "", "0.0");
//        }
//        catch (Exception l_ex)
//        {
//            log.error(l_ex.getMessage(), l_ex);
//            fail();
//        }
//
//        log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>test past");
//        log.exiting(STR_METHOD_NAME);
//    }
//
//    //部店別証拠金計算条件をセット
//    //add部店別証拠金計算条件 not = null
//    public void testCreateIfoDepositCalcCondition_C0002()
//    {
//        final String STR_METHOD_NAME = "testCreateIfoDepositCalcCondition_C0002()";
//        log.entering(STR_METHOD_NAME);
//
//        try
//        {
//            TestDBUtility.deleteAll(MainAccountRow.TYPE);
//            MainAccountParams mainAccountParams = TestDBUtility.getMainAccountRow();
//            TestDBUtility.insertWithDel(mainAccountParams);
//            
//            TestDBUtility.deleteAll(SubAccountRow.TYPE);
//            SubAccountParams subAccountParams = TestDBUtility.getSubAccountRow();
//            subAccountParams.setAccountId(333812512246L);
//            TestDBUtility.insertWithDel(subAccountParams);
//       
//            TestDBUtility.deleteAll(InstitutionRow.TYPE);
//            InstitutionParams institutionParams = TestDBUtility.getInstitutionRow();
//            institutionParams.setIfoRealPriceCalcDiv("0");
//            TestDBUtility.insertWithDel(institutionParams);
//
//            TestDBUtility.deleteAll(BranchRow.TYPE);
//            BranchParams branchParams = TestDBUtility.getBranchRow();
//            TestDBUtility.insertWithDel(branchParams);
//
//            TestDBUtility.deleteAll(ProcessManagementRow.TYPE);
//            ProcessManagementParams managementParams = new ProcessManagementParams();
//            managementParams.setProcessId("0001");
//            managementParams.setInstitutionCode("0D");
//            managementParams.setBranchCode("381");
//            ProcessManagementParams managementParams1 = new ProcessManagementParams();
//            managementParams1.setProcessId("0008");
//            managementParams1.setInstitutionCode("0D");
//            managementParams1.setBranchCode("381");
//            TestDBUtility.insertWithDel(managementParams);
//            TestDBUtility.insertWithDel(managementParams1);
//
//            TestDBUtility.deleteAll(BranchIndexDealtCondRow.TYPE);
//            BranchIndexDealtCondParams condParams = TestDBUtility.getBranchIndexDealtCondRow();
//            condParams.setInstitutionCode("0D");
//            condParams.setBranchCode("381");
//            TestDBUtility.insertWithDel(condParams);
//
//            TestDBUtility.deleteAll(TradingTimeRow.TYPE);
//            TradingTimeParams tradingTimeParams = TestDBUtility.getTradingTimeRow();
//            tradingTimeParams.setInstitutionCode("0D");
//            tradingTimeParams.setBranchCode("123");
//            tradingTimeParams.setMarketCode("N1");
//            tradingTimeParams.setTradingTimeType("01");
//            tradingTimeParams.setBizDateType("1");
//            tradingTimeParams.setProductCode("0");
//            TestDBUtility.insertWithDel(tradingTimeParams);
//
//            TestDBUtility.deleteAll(TradingpowerCalcConditionRow.TYPE);
//            TradingpowerCalcConditionParams calcConditionParams = TestDBUtility.getTradingpowerCalcConditionRow();
//            calcConditionParams.setAccountId(333812512246L);
//            TestDBUtility.insertWithDel(calcConditionParams);
//
//            TestDBUtility.deleteAll(BranchPreferencesRow.TYPE);
//            BranchPreferencesParams branchPreferencesParams = TestDBUtility.getBranchPreferencesRow();
//            branchPreferencesParams.setBranchId(33381);
//            TestDBUtility.insertWithDel(branchPreferencesParams);
//
//            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
//            AccountManager l_accountMgr = l_finApp.getAccountManager();
//            WEB3GentradeSubAccount l_subAccount =
//                (WEB3GentradeSubAccount) l_accountMgr.getSubAccount(333812512246L, 33381251220301L);
//
//            WEB3IfoDepositCalcCondition condition = l_serviceImpl.createIfoDepositCalcCondition(l_subAccount);
//            assertTrue(condition.isIfoDepositMailFlag());
//            assertTrue(condition.isQuickReportReceived());
//            assertFalse(condition.isEveningSessionEnforcemented());
//            assertEquals(condition.getPreBizDateIfoDepositLackCharge() + "", "0.0");
//            assertEquals(condition.getCurrentBizDateIfoDepositLackCharge() + "", "0.0");
//            assertFalse(condition.isNewOpenTradingPowerAvailable());
//            assertFalse(condition.isLackChargeNonManagement());
//            assertFalse(condition.isIfodepositNonCalcSqProductFlag());
//            assertFalse(condition.isRealPriceIfoDepositCalc());
//            assertFalse(condition.isSimpleSPANCalc());
//            assertFalse(condition.isSPANTrouble());
//            assertEquals(condition.getSPANFactor() + "", "0.0");
//            assertEquals(condition.getSPANFactorRed() + "", "0.0");
//            assertEquals(condition.getTransferPowerFactor() + "", "0.0");
//            assertEquals(condition.getMinIfoDeposit() + "", "0.0");
//            assertEquals(condition.getCalcConditionPerBranch("triggerorder.wlimitorder.check.order.cond.price") + "", "1");
//        }
//        catch (Exception l_ex)
//        {
//            log.error(l_ex.getMessage(), l_ex);
//            fail();
//        }
//
//        log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>test past");
//        log.exiting(STR_METHOD_NAME);
//    }

    //証拠金口座開設済みの場合
    //引数.補助口座.補助口座タイプ == ”株式オプション取引口座(先物証拠金)”
    //余力条件テーブルParams.取引停止区分 == ”取引可能”、かつ、<BR>
    //余力条件テーブルParams..先物OP新規建余力区分 == 
    //”余力可”ならば、trueを返却する。
    public void testisNewOpenTradingPowerAvailable_C0001()
    {
        final String STR_METHOD_NAME = "testisNewOpenTradingPowerAvailable_C0001()";
        log.entering(STR_METHOD_NAME);

        try
        {
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            MainAccountParams mainAccountParams = TestDBUtility.getMainAccountRow();
            TestDBUtility.insertWithDel(mainAccountParams);
            
            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            SubAccountParams subAccountParams = TestDBUtility.getSubAccountRow();
            subAccountParams.setAccountId(333812512246L);
            subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_OPTIONS_SUB_ACCOUNT);
            TestDBUtility.insertWithDel(subAccountParams);

            TestDBUtility.deleteAll(TradingpowerCalcConditionRow.TYPE);
            TradingpowerCalcConditionParams calcConditionParams = TestDBUtility.getTradingpowerCalcConditionRow();
            calcConditionParams.setAccountId(333812512246L);
            calcConditionParams.setTradingStop("0");
            calcConditionParams.setIfoOpenPositionStop("0");
            TestDBUtility.insertWithDel(calcConditionParams);

            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
            AccountManager l_accountMgr = l_finApp.getAccountManager();
            WEB3GentradeSubAccount l_subAccount =
                (WEB3GentradeSubAccount) l_accountMgr.getSubAccount(333812512246L, 33381251220301L);

            Method l_method = WEB3IfoDepositCalcServiceImpl.class.getDeclaredMethod("isNewOpenTradingPowerAvailable",
                new Class[]{ WEB3GentradeSubAccount.class });
            l_method.setAccessible(true);
            Boolean l_strSortcon = (Boolean)l_method.invoke(l_serviceImpl, new Object[]{l_subAccount});
            assertTrue(l_strSortcon.booleanValue());
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }

        log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>test past");
        log.exiting(STR_METHOD_NAME);
    }

    //証拠金口座開設済みの場合
    //引数.補助口座.補助口座タイプ == ”株式オプション取引口座(先物証拠金)”
    //余力条件テーブルParams.取引停止区分 == ”取引可能”、かつ、<BR>
    //余力条件テーブルParams..先物OP新規建余力区分 == 
    //”余力可”以外の場合falseを返却する。
    public void testisNewOpenTradingPowerAvailable_C0002()
    {
        final String STR_METHOD_NAME = "testisNewOpenTradingPowerAvailable_C0002()";
        log.entering(STR_METHOD_NAME);

        try
        {
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            MainAccountParams mainAccountParams = TestDBUtility.getMainAccountRow();
            TestDBUtility.insertWithDel(mainAccountParams);
            
            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            SubAccountParams subAccountParams = TestDBUtility.getSubAccountRow();
            subAccountParams.setAccountId(333812512246L);
            subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_OPTIONS_SUB_ACCOUNT);
            TestDBUtility.insertWithDel(subAccountParams);

            TestDBUtility.deleteAll(TradingpowerCalcConditionRow.TYPE);
            TradingpowerCalcConditionParams calcConditionParams = TestDBUtility.getTradingpowerCalcConditionRow();
            calcConditionParams.setAccountId(333812512246L);
            calcConditionParams.setTradingStop("1");
            calcConditionParams.setIfoOpenPositionStop("0");
            TestDBUtility.insertWithDel(calcConditionParams);

            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
            AccountManager l_accountMgr = l_finApp.getAccountManager();
            WEB3GentradeSubAccount l_subAccount =
                (WEB3GentradeSubAccount) l_accountMgr.getSubAccount(333812512246L, 33381251220301L);

            Method l_method = WEB3IfoDepositCalcServiceImpl.class.getDeclaredMethod("isNewOpenTradingPowerAvailable",
                new Class[]{ WEB3GentradeSubAccount.class });
            l_method.setAccessible(true);
            Boolean l_strSortcon = (Boolean)l_method.invoke(l_serviceImpl, new Object[]{l_subAccount});
            assertFalse(l_strSortcon.booleanValue());
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }

        log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>test past");
        log.exiting(STR_METHOD_NAME);
    }

    //証拠金口座未開設の場合
    //引数.補助口座.補助口座タイプ != ”株式オプション取引口座(先物証拠金)”)
    //余力条件テーブルParams.取引停止区分 == ”取引可能”、かつ、<BR>
    //余力条件テーブルParams..先物OP新規建余力区分 == 
    //”余力可”ならば、trueを返却する。
    public void testisNewOpenTradingPowerAvailable_C0003()
    {
        final String STR_METHOD_NAME = "testisNewOpenTradingPowerAvailable_C0003()";
        log.entering(STR_METHOD_NAME);

        try
        {
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            MainAccountParams mainAccountParams = TestDBUtility.getMainAccountRow();
            TestDBUtility.insertWithDel(mainAccountParams);
            
            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            SubAccountParams subAccountParams = TestDBUtility.getSubAccountRow();
            subAccountParams.setAccountId(333812512246L);
            subAccountParams.setSubAccountType(SubAccountTypeEnum.ALL_IN_ONE_ACCOUNT);
            TestDBUtility.insertWithDel(subAccountParams);

            TestDBUtility.deleteAll(TradingpowerCalcConditionRow.TYPE);
            TradingpowerCalcConditionParams calcConditionParams = TestDBUtility.getTradingpowerCalcConditionRow();
            calcConditionParams.setAccountId(333812512246L);
            calcConditionParams.setTradingStop("0");
            calcConditionParams.setOtherTradingStop("0");
            TestDBUtility.insertWithDel(calcConditionParams);

            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
            AccountManager l_accountMgr = l_finApp.getAccountManager();
            WEB3GentradeSubAccount l_subAccount =
                (WEB3GentradeSubAccount) l_accountMgr.getSubAccount(333812512246L, 33381251220301L);

            Method l_method = WEB3IfoDepositCalcServiceImpl.class.getDeclaredMethod("isNewOpenTradingPowerAvailable",
                new Class[]{ WEB3GentradeSubAccount.class });
            l_method.setAccessible(true);
            Boolean l_strSortcon = (Boolean)l_method.invoke(l_serviceImpl, new Object[]{l_subAccount});
            assertTrue(l_strSortcon.booleanValue());
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }

        log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>test past");
        log.exiting(STR_METHOD_NAME);
    }

    //証拠金口座未開設の場合
    //引数.補助口座.補助口座タイプ != ”株式オプション取引口座(先物証拠金)”)
    //余力条件テーブルParams.取引停止区分 == ”取引可能”、かつ、<BR>
    //余力条件テーブルParams..先物OP新規建余力区分 == 
    //”余力可”以外の場合falseを返却する。
    public void testisNewOpenTradingPowerAvailable_C0004()
    {
        final String STR_METHOD_NAME = "testisNewOpenTradingPowerAvailable_C0004()";
        log.entering(STR_METHOD_NAME);

        try
        {
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            MainAccountParams mainAccountParams = TestDBUtility.getMainAccountRow();
            TestDBUtility.insertWithDel(mainAccountParams);
            
            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            SubAccountParams subAccountParams = TestDBUtility.getSubAccountRow();
            subAccountParams.setAccountId(333812512246L);
            subAccountParams.setSubAccountType(SubAccountTypeEnum.ALL_IN_ONE_ACCOUNT);
            TestDBUtility.insertWithDel(subAccountParams);

            TestDBUtility.deleteAll(TradingpowerCalcConditionRow.TYPE);
            TradingpowerCalcConditionParams calcConditionParams = TestDBUtility.getTradingpowerCalcConditionRow();
            calcConditionParams.setAccountId(333812512246L);
            calcConditionParams.setTradingStop("1");
            calcConditionParams.setIfoOpenPositionStop("0");
            TestDBUtility.insertWithDel(calcConditionParams);

            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
            AccountManager l_accountMgr = l_finApp.getAccountManager();
            WEB3GentradeSubAccount l_subAccount =
                (WEB3GentradeSubAccount) l_accountMgr.getSubAccount(333812512246L, 33381251220301L);

            Method l_method = WEB3IfoDepositCalcServiceImpl.class.getDeclaredMethod("isNewOpenTradingPowerAvailable",
                new Class[]{ WEB3GentradeSubAccount.class });
            l_method.setAccessible(true);
            Boolean l_strSortcon = (Boolean)l_method.invoke(l_serviceImpl, new Object[]{l_subAccount});
            assertFalse(l_strSortcon.booleanValue());
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }

        log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>test past");
        log.exiting(STR_METHOD_NAME);
    }

    //証拠金不足メール未送信の場合(getプロセス管理Params( )で該当データなし(null))、falseを返却する。
    public void testIsIfoDepositMailFlag_C0001()
    {
        final String STR_METHOD_NAME = "testIsIfoDepositMailFlag_C0001()";
        log.entering(STR_METHOD_NAME);

        try
        {
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            MainAccountParams mainAccountParams = TestDBUtility.getMainAccountRow();
            TestDBUtility.insertWithDel(mainAccountParams);
            
            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            SubAccountParams subAccountParams = TestDBUtility.getSubAccountRow();
            subAccountParams.setAccountId(333812512246L);
            TestDBUtility.insertWithDel(subAccountParams);

            TestDBUtility.deleteAll(ProcessManagementRow.TYPE);
            ProcessManagementParams managementParams = new ProcessManagementParams();
            managementParams.setProcessId("0001");
            managementParams.setInstitutionCode("00");
            managementParams.setBranchCode("381");
            TestDBUtility.insertWithDel(managementParams);

            TestDBUtility.deleteAll(InstitutionRow.TYPE);
            InstitutionParams institutionParams = TestDBUtility.getInstitutionRow();
            TestDBUtility.insertWithDel(institutionParams);

            TestDBUtility.deleteAll(BranchRow.TYPE);
            BranchParams branchParams = TestDBUtility.getBranchRow();
            TestDBUtility.insertWithDel(branchParams);

            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
            AccountManager l_accountMgr = l_finApp.getAccountManager();
            WEB3GentradeSubAccount l_subAccount =
                (WEB3GentradeSubAccount) l_accountMgr.getSubAccount(333812512246L, 33381251220301L);

            Method l_method = WEB3IfoDepositCalcServiceImpl.class.getDeclaredMethod("isIfoDepositMailFlag",
                new Class[]{ WEB3GentradeSubAccount.class });
            l_method.setAccessible(true);
            Boolean l_strSortcon = (Boolean)l_method.invoke(l_serviceImpl, new Object[]{l_subAccount});
            assertFalse(l_strSortcon.booleanValue());
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }

        log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>test past");
        log.exiting(STR_METHOD_NAME);
    }

    //証拠金不足メール送信済の場合(getプロセス管理Params( )で該当データあり)、trueを返却する。
    public void testIsIfoDepositMailFlag_C0002()
    {
        final String STR_METHOD_NAME = "testIsIfoDepositMailFlag_C0002()";
        log.entering(STR_METHOD_NAME);

        try
        {
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            MainAccountParams mainAccountParams = TestDBUtility.getMainAccountRow();
            TestDBUtility.insertWithDel(mainAccountParams);
            
            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            SubAccountParams subAccountParams = TestDBUtility.getSubAccountRow();
            subAccountParams.setAccountId(333812512246L);
            TestDBUtility.insertWithDel(subAccountParams);

            TestDBUtility.deleteAll(ProcessManagementRow.TYPE);
            ProcessManagementParams managementParams = new ProcessManagementParams();
            managementParams.setProcessId("0001");
            managementParams.setInstitutionCode("0D");
            managementParams.setBranchCode("381");
            TestDBUtility.insertWithDel(managementParams);

            TestDBUtility.deleteAll(InstitutionRow.TYPE);
            InstitutionParams institutionParams = TestDBUtility.getInstitutionRow();
            TestDBUtility.insertWithDel(institutionParams);

            TestDBUtility.deleteAll(BranchRow.TYPE);
            BranchParams branchParams = TestDBUtility.getBranchRow();
            TestDBUtility.insertWithDel(branchParams);

            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
            AccountManager l_accountMgr = l_finApp.getAccountManager();
            WEB3GentradeSubAccount l_subAccount =
                (WEB3GentradeSubAccount) l_accountMgr.getSubAccount(333812512246L, 33381251220301L);

            Method l_method = WEB3IfoDepositCalcServiceImpl.class.getDeclaredMethod("isIfoDepositMailFlag",
                new Class[]{ WEB3GentradeSubAccount.class });
            l_method.setAccessible(true);
            Boolean l_strSortcon = (Boolean)l_method.invoke(l_serviceImpl, new Object[]{l_subAccount});
            assertTrue(l_strSortcon.booleanValue());
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }

        log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>test past");
        log.exiting(STR_METHOD_NAME);
    }

    //営業日[T-1]に証拠金不足が発生していない場合(get証拠金Params( )の戻り値 == null)、0を返却する。
    public void testGetPreBizDateIfoDepositLackCharge_C0001()
    {
        final String STR_METHOD_NAME = "testGetPreBizDateIfoDepositLackCharge_C0001()";
        log.entering(STR_METHOD_NAME);

        try
        {
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            MainAccountParams mainAccountParams = TestDBUtility.getMainAccountRow();
            TestDBUtility.insertWithDel(mainAccountParams);
            
            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            SubAccountParams subAccountParams = TestDBUtility.getSubAccountRow();
            subAccountParams.setAccountId(333812512246L);
            TestDBUtility.insertWithDel(subAccountParams);

            TestDBUtility.deleteAll(InstitutionRow.TYPE);
            InstitutionParams institutionParams = TestDBUtility.getInstitutionRow();
            TestDBUtility.insertWithDel(institutionParams);

            TestDBUtility.deleteAll(BranchRow.TYPE);
            BranchParams branchParams = TestDBUtility.getBranchRow();
            TestDBUtility.insertWithDel(branchParams);

            TestDBUtility.deleteAll(IfoDepositRow.TYPE);
            IfoDepositParams depositParams = new IfoDepositParams();
            depositParams.setInstitutionCode("00");
            depositParams.setBranchCode("381");
            depositParams.setAccountCode("2512246");
            depositParams.setCalcDate(WEB3DateUtility.getDate("20080528", "yyyyMMdd"));
            depositParams.setNetAmoutCash(100);
            TestDBUtility.insertWithDel(depositParams);

            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
            AccountManager l_accountMgr = l_finApp.getAccountManager();
            WEB3GentradeSubAccount l_subAccount =
                (WEB3GentradeSubAccount) l_accountMgr.getSubAccount(333812512246L, 33381251220301L);

            Date l_datPreBizDate = WEB3DateUtility.getDate("20080528", "yyyyMMdd");
            Method l_method = WEB3IfoDepositCalcServiceImpl.class.getDeclaredMethod("getPreBizDateIfoDepositLackCharge",
                new Class[]{ WEB3GentradeSubAccount.class, Date.class});
            l_method.setAccessible(true);
            String l_dblCharge = (String)l_method.invoke(l_serviceImpl, new Object[]{l_subAccount, l_datPreBizDate}).toString();
            assertEquals(l_dblCharge, "0.0");
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }

        log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>test past");
        log.exiting(STR_METHOD_NAME);
    }

    //営業日[T+0]の証拠金不足メール未送信の場合(上記以外）、
    //証拠金Params.翌日受渡額現金の絶対値(Abs(翌日受渡額現金))を返却する。
    public void testGetPreBizDateIfoDepositLackCharge_C0002()
    {
        final String STR_METHOD_NAME = "testGetPreBizDateIfoDepositLackCharge_C0002()";
        log.entering(STR_METHOD_NAME);

        try
        {
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            MainAccountParams mainAccountParams = TestDBUtility.getMainAccountRow();
            TestDBUtility.insertWithDel(mainAccountParams);
            
            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            SubAccountParams subAccountParams = TestDBUtility.getSubAccountRow();
            subAccountParams.setAccountId(333812512246L);
            TestDBUtility.insertWithDel(subAccountParams);

            TestDBUtility.deleteAll(InstitutionRow.TYPE);
            InstitutionParams institutionParams = TestDBUtility.getInstitutionRow();
            TestDBUtility.insertWithDel(institutionParams);

            TestDBUtility.deleteAll(BranchRow.TYPE);
            BranchParams branchParams = TestDBUtility.getBranchRow();
            TestDBUtility.insertWithDel(branchParams);

            TestDBUtility.deleteAll(IfoDepositRow.TYPE);
            IfoDepositParams depositParams = new IfoDepositParams();
            depositParams.setInstitutionCode("0D");
            depositParams.setBranchCode("381");
            depositParams.setAccountCode("2512246");
            depositParams.setCalcDate(WEB3DateUtility.getDate("20080528", "yyyyMMdd"));
            depositParams.setNetAmoutCash(100);
            TestDBUtility.insertWithDel(depositParams);

            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
            AccountManager l_accountMgr = l_finApp.getAccountManager();
            WEB3GentradeSubAccount l_subAccount =
                (WEB3GentradeSubAccount) l_accountMgr.getSubAccount(333812512246L, 33381251220301L);

            Date l_datPreBizDate = WEB3DateUtility.getDate("20080528", "yyyyMMdd");
            Method l_method = WEB3IfoDepositCalcServiceImpl.class.getDeclaredMethod("getPreBizDateIfoDepositLackCharge",
                new Class[]{ WEB3GentradeSubAccount.class, Date.class});
            l_method.setAccessible(true);
            String l_dblCharge = (String)l_method.invoke(l_serviceImpl, new Object[]{l_subAccount, l_datPreBizDate}).toString();
            assertEquals(l_dblCharge, "100.0");
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }

        log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>test past");
        log.exiting(STR_METHOD_NAME);
    }

    //営業日[T+0]に証拠金不足が発生していない(または、営業日[T+0]の証拠金不足メ?
    //ルが未送信)の場合(get証拠金Params( )の戻り値 == null)、0を返却する。
    public void testGetCurrentBizDateIfoDepositLackCharge_C0001()
    {
        final String STR_METHOD_NAME = "testGetCurrentBizDateIfoDepositLackCharge_C0001()";
        log.entering(STR_METHOD_NAME);

        try
        {
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            MainAccountParams mainAccountParams = TestDBUtility.getMainAccountRow();
            TestDBUtility.insertWithDel(mainAccountParams);
            
            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            SubAccountParams subAccountParams = TestDBUtility.getSubAccountRow();
            subAccountParams.setAccountId(333812512246L);
            TestDBUtility.insertWithDel(subAccountParams);

            TestDBUtility.deleteAll(InstitutionRow.TYPE);
            InstitutionParams institutionParams = TestDBUtility.getInstitutionRow();
            TestDBUtility.insertWithDel(institutionParams);

            TestDBUtility.deleteAll(BranchRow.TYPE);
            BranchParams branchParams = TestDBUtility.getBranchRow();
            TestDBUtility.insertWithDel(branchParams);

            TestDBUtility.deleteAll(IfoDepositRow.TYPE);
            IfoDepositParams depositParams = new IfoDepositParams();
            depositParams.setInstitutionCode("00");
            depositParams.setBranchCode("381");
            depositParams.setAccountCode("2512246");
            depositParams.setCalcDate(WEB3DateUtility.getDate("20080528", "yyyyMMdd"));
            depositParams.setNetAmoutCash(100);
            TestDBUtility.insertWithDel(depositParams);

            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
            AccountManager l_accountMgr = l_finApp.getAccountManager();
            WEB3GentradeSubAccount l_subAccount =
                (WEB3GentradeSubAccount) l_accountMgr.getSubAccount(333812512246L, 33381251220301L);

            Date l_datPreBizDate = WEB3DateUtility.getDate("20080528", "yyyyMMdd");
            Method l_method = WEB3IfoDepositCalcServiceImpl.class.getDeclaredMethod("getCurrentBizDateIfoDepositLackCharge",
                new Class[]{ WEB3GentradeSubAccount.class, Date.class});
            l_method.setAccessible(true);
            String l_dblCharge = (String)l_method.invoke(l_serviceImpl, new Object[]{l_subAccount, l_datPreBizDate}).toString();
            assertEquals(l_dblCharge, "0.0");
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }

        log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>test past");
        log.exiting(STR_METHOD_NAME);
    }

    //営業日[T+0]の証拠金不足メール未送信の場合(上記以外）、
    //証拠金Params.翌日受渡額現金の絶対値(Abs(翌日受渡額現金))を返却する。
    public void testGetCurrentBizDateIfoDepositLackCharge_C0002()
    {
        final String STR_METHOD_NAME = "testGetCurrentBizDateIfoDepositLackCharge_C0002()";
        log.entering(STR_METHOD_NAME);

        try
        {
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            MainAccountParams mainAccountParams = TestDBUtility.getMainAccountRow();
            TestDBUtility.insertWithDel(mainAccountParams);
            
            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            SubAccountParams subAccountParams = TestDBUtility.getSubAccountRow();
            subAccountParams.setAccountId(333812512246L);
            TestDBUtility.insertWithDel(subAccountParams);

            TestDBUtility.deleteAll(InstitutionRow.TYPE);
            InstitutionParams institutionParams = TestDBUtility.getInstitutionRow();
            TestDBUtility.insertWithDel(institutionParams);

            TestDBUtility.deleteAll(BranchRow.TYPE);
            BranchParams branchParams = TestDBUtility.getBranchRow();
            TestDBUtility.insertWithDel(branchParams);

            TestDBUtility.deleteAll(IfoDepositRow.TYPE);
            IfoDepositParams depositParams = new IfoDepositParams();
            depositParams.setInstitutionCode("0D");
            depositParams.setBranchCode("381");
            depositParams.setAccountCode("2512246");
            depositParams.setCalcDate(WEB3DateUtility.getDate("20080528", "yyyyMMdd"));
            depositParams.setNetAmoutCash(100);
            TestDBUtility.insertWithDel(depositParams);

            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
            AccountManager l_accountMgr = l_finApp.getAccountManager();
            WEB3GentradeSubAccount l_subAccount =
                (WEB3GentradeSubAccount) l_accountMgr.getSubAccount(333812512246L, 33381251220301L);

            Date l_datPreBizDate = WEB3DateUtility.getDate("20080528", "yyyyMMdd");
            Method l_method = WEB3IfoDepositCalcServiceImpl.class.getDeclaredMethod("getCurrentBizDateIfoDepositLackCharge",
                new Class[]{ WEB3GentradeSubAccount.class, Date.class});
            l_method.setAccessible(true);
            String l_dblCharge = (String)l_method.invoke(l_serviceImpl, new Object[]{l_subAccount, l_datPreBizDate}).toString();
            assertEquals(l_dblCharge, "100.0");
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }

        log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>test past");
        log.exiting(STR_METHOD_NAME);
    }

    //清算値速報未受信の場合(getプロセス管理Params( )で該当データなし(null))、falseを返却する。
    public void testIsQuickReportReceived_C0001()
    {
        final String STR_METHOD_NAME = "testIsQuickReportReceived_C0001()";
        log.entering(STR_METHOD_NAME);

        try
        {
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            MainAccountParams mainAccountParams = TestDBUtility.getMainAccountRow();
            TestDBUtility.insertWithDel(mainAccountParams);
            
            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            SubAccountParams subAccountParams = TestDBUtility.getSubAccountRow();
            subAccountParams.setAccountId(333812512246L);
            TestDBUtility.insertWithDel(subAccountParams);

            TestDBUtility.deleteAll(ProcessManagementRow.TYPE);
            ProcessManagementParams managementParams = new ProcessManagementParams();
            managementParams.setProcessId("0008");
            managementParams.setInstitutionCode("00");
            managementParams.setBranchCode("381");
            TestDBUtility.insertWithDel(managementParams);

            TestDBUtility.deleteAll(InstitutionRow.TYPE);
            InstitutionParams institutionParams = TestDBUtility.getInstitutionRow();
            TestDBUtility.insertWithDel(institutionParams);

            TestDBUtility.deleteAll(BranchRow.TYPE);
            BranchParams branchParams = TestDBUtility.getBranchRow();
            TestDBUtility.insertWithDel(branchParams);

            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
            AccountManager l_accountMgr = l_finApp.getAccountManager();
            WEB3GentradeSubAccount l_subAccount =
                (WEB3GentradeSubAccount) l_accountMgr.getSubAccount(333812512246L, 33381251220301L);

            Method l_method = WEB3IfoDepositCalcServiceImpl.class.getDeclaredMethod("isQuickReportReceived",
                new Class[]{ WEB3GentradeSubAccount.class });
            l_method.setAccessible(true);
            Boolean l_strSortcon = (Boolean)l_method.invoke(l_serviceImpl, new Object[]{l_subAccount});
            assertFalse(l_strSortcon.booleanValue());
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }

        log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>test past");
        log.exiting(STR_METHOD_NAME);
    }

    //受信済の場合(getプロセス管理Params( )で該当データあり)、trueを返却する。
    public void testIsQuickReportReceived_C0002()
    {
        final String STR_METHOD_NAME = "testIsQuickReportReceived_C0002()";
        log.entering(STR_METHOD_NAME);

        try
        {
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            MainAccountParams mainAccountParams = TestDBUtility.getMainAccountRow();
            TestDBUtility.insertWithDel(mainAccountParams);
            
            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            SubAccountParams subAccountParams = TestDBUtility.getSubAccountRow();
            subAccountParams.setAccountId(333812512246L);
            TestDBUtility.insertWithDel(subAccountParams);

            TestDBUtility.deleteAll(ProcessManagementRow.TYPE);
            ProcessManagementParams managementParams = new ProcessManagementParams();
            managementParams.setProcessId("0008");
            managementParams.setInstitutionCode("0D");
            managementParams.setBranchCode("381");
            TestDBUtility.insertWithDel(managementParams);

            TestDBUtility.deleteAll(InstitutionRow.TYPE);
            InstitutionParams institutionParams = TestDBUtility.getInstitutionRow();
            TestDBUtility.insertWithDel(institutionParams);

            TestDBUtility.deleteAll(BranchRow.TYPE);
            BranchParams branchParams = TestDBUtility.getBranchRow();
            TestDBUtility.insertWithDel(branchParams);

            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
            AccountManager l_accountMgr = l_finApp.getAccountManager();
            WEB3GentradeSubAccount l_subAccount =
                (WEB3GentradeSubAccount) l_accountMgr.getSubAccount(333812512246L, 33381251220301L);

            Method l_method = WEB3IfoDepositCalcServiceImpl.class.getDeclaredMethod("isQuickReportReceived",
                new Class[]{ WEB3GentradeSubAccount.class });
            l_method.setAccessible(true);
            Boolean l_strSortcon = (Boolean)l_method.invoke(l_serviceImpl, new Object[]{l_subAccount});
            assertTrue(l_strSortcon.booleanValue());
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }

        log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>test past");
        log.exiting(STR_METHOD_NAME);
    }

    //夕場実施判定 
    //で取得した部店.is夕場実施()の戻り値を返却する。 
    //夕場未実施の場合はtrueを返却する。
    public void testIsEveningSession_C0001()
    {
        final String STR_METHOD_NAME = "testIsEveningSession_C0001()";
        log.entering(STR_METHOD_NAME);

        try
        {
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            MainAccountParams mainAccountParams = TestDBUtility.getMainAccountRow();
            TestDBUtility.insertWithDel(mainAccountParams);
            
            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            SubAccountParams subAccountParams = TestDBUtility.getSubAccountRow();
            subAccountParams.setAccountId(333812512246L);
            TestDBUtility.insertWithDel(subAccountParams);

            TestDBUtility.deleteAll(InstitutionRow.TYPE);
            InstitutionParams institutionParams = TestDBUtility.getInstitutionRow();
            TestDBUtility.insertWithDel(institutionParams);

            TestDBUtility.deleteAll(BranchRow.TYPE);
            BranchParams branchParams = TestDBUtility.getBranchRow();
            TestDBUtility.insertWithDel(branchParams);

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue("webbroker3.gentrade.WEB3GentradeBranch",
                    "isEveningSessionEnforcemented", new Class[]
                    { ProductTypeEnum.class },
                    new Boolean(true));

            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
            AccountManager l_accountMgr = l_finApp.getAccountManager();
            WEB3GentradeSubAccount l_subAccount =
                (WEB3GentradeSubAccount) l_accountMgr.getSubAccount(333812512246L, 33381251220301L);

            Method l_method = WEB3IfoDepositCalcServiceImpl.class.getDeclaredMethod("isEveningSession",
                new Class[]{ WEB3GentradeSubAccount.class });
            l_method.setAccessible(true);
            Boolean l_strSortcon = (Boolean)l_method.invoke(l_serviceImpl, new Object[]{l_subAccount});
            assertTrue(l_strSortcon.booleanValue());
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }

        log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>test past");
        log.exiting(STR_METHOD_NAME);
    }

    //夕場実施判定 
    //で取得した部店.is夕場実施()の戻り値を返却する。 
    //夕場未実施の場合はfalseを返却する。
    public void testIsEveningSession_C0002()
    {
        final String STR_METHOD_NAME = "testIsEveningSession_C0002()";
        log.entering(STR_METHOD_NAME);

        try
        {
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            MainAccountParams mainAccountParams = TestDBUtility.getMainAccountRow();
            TestDBUtility.insertWithDel(mainAccountParams);
            
            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            SubAccountParams subAccountParams = TestDBUtility.getSubAccountRow();
            subAccountParams.setAccountId(333812512246L);
            TestDBUtility.insertWithDel(subAccountParams);

            TestDBUtility.deleteAll(InstitutionRow.TYPE);
            InstitutionParams institutionParams = TestDBUtility.getInstitutionRow();
            TestDBUtility.insertWithDel(institutionParams);

            TestDBUtility.deleteAll(BranchRow.TYPE);
            BranchParams branchParams = TestDBUtility.getBranchRow();
            TestDBUtility.insertWithDel(branchParams);

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue("webbroker3.gentrade.WEB3GentradeBranch",
                    "isEveningSessionEnforcemented", new Class[]
                    { ProductTypeEnum.class },
                    new Boolean(false));

            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
            AccountManager l_accountMgr = l_finApp.getAccountManager();
            WEB3GentradeSubAccount l_subAccount =
                (WEB3GentradeSubAccount) l_accountMgr.getSubAccount(333812512246L, 33381251220301L);

            Method l_method = WEB3IfoDepositCalcServiceImpl.class.getDeclaredMethod("isEveningSession",
                new Class[]{ WEB3GentradeSubAccount.class });
            l_method.setAccessible(true);
            Boolean l_strSortcon = (Boolean)l_method.invoke(l_serviceImpl, new Object[]{l_subAccount});
            assertFalse(l_strSortcon.booleanValue());
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }

        log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>test past");
        log.exiting(STR_METHOD_NAME);
    }

    /*
     * is夕場実施()失敗
     */
    public void testIsEveningSession_C0003()
    {
        final String STR_METHOD_NAME = "testIsEveningSession_C0003()";
        log.entering(STR_METHOD_NAME);

        try
        {
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            MainAccountParams mainAccountParams = TestDBUtility.getMainAccountRow();
            TestDBUtility.insertWithDel(mainAccountParams);

            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            SubAccountParams subAccountParams = TestDBUtility.getSubAccountRow();
            subAccountParams.setAccountId(333812512246L);
            TestDBUtility.insertWithDel(subAccountParams);

            TestDBUtility.deleteAll(InstitutionRow.TYPE);
            InstitutionParams institutionParams = TestDBUtility.getInstitutionRow();
            TestDBUtility.insertWithDel(institutionParams);

            TestDBUtility.deleteAll(BranchRow.TYPE);
            BranchParams branchParams = TestDBUtility.getBranchRow();
            TestDBUtility.insertWithDel(branchParams);

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.gentrade.WEB3GentradeBranch",
                "isEveningSessionEnforcemented",
                new Class[]{ ProductTypeEnum.class },
                new WEB3BaseException(WEB3ErrorCatalog.SYSTEM_ERROR_80002, "isEveningSessionEnforcemented"));

            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
            AccountManager l_accountMgr = l_finApp.getAccountManager();
            WEB3GentradeSubAccount l_subAccount =
                (WEB3GentradeSubAccount) l_accountMgr.getSubAccount(333812512246L, 33381251220301L);

            Method l_method = WEB3IfoDepositCalcServiceImpl.class.getDeclaredMethod("isEveningSession",
                new Class[]{ WEB3GentradeSubAccount.class });
            l_method.setAccessible(true);
            l_method.invoke(l_serviceImpl, new Object[]{l_subAccount});
            fail();
        }
        catch (InvocationTargetException l_exITE)
        {
            log.error(TEST_END + STR_METHOD_NAME, l_exITE);
            log.exiting(TEST_END + STR_METHOD_NAME);

            WEB3BaseRuntimeException l_targetException =
                (WEB3BaseRuntimeException)l_exITE.getTargetException();
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                l_targetException.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }

    //証拠金不足額を管理するかを判定する。
    //で取得した部店.is証拠金不足額非管理()の戻り値を返却する。 
    //証拠金不足額を管理しない場合はtrueを返却する。
    public void testIsLackChargeNonManagement_C0001()
    {
        final String STR_METHOD_NAME = "testIsLackChargeNonManagement_C0001()";
        log.entering(STR_METHOD_NAME);

        try
        {
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            MainAccountParams mainAccountParams = TestDBUtility.getMainAccountRow();
            TestDBUtility.insertWithDel(mainAccountParams);
            
            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            SubAccountParams subAccountParams = TestDBUtility.getSubAccountRow();
            subAccountParams.setAccountId(333812512246L);
            TestDBUtility.insertWithDel(subAccountParams);

            TestDBUtility.deleteAll(InstitutionRow.TYPE);
            InstitutionParams institutionParams = TestDBUtility.getInstitutionRow();
            TestDBUtility.insertWithDel(institutionParams);

            TestDBUtility.deleteAll(BranchRow.TYPE);
            BranchParams branchParams = TestDBUtility.getBranchRow();
            TestDBUtility.insertWithDel(branchParams);

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue("webbroker3.gentrade.WEB3GentradeBranch",
                    "isIfodepositLackchargeNonManagement", new Class[]{},
                    new Boolean(true));

            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
            AccountManager l_accountMgr = l_finApp.getAccountManager();
            WEB3GentradeSubAccount l_subAccount =
                (WEB3GentradeSubAccount) l_accountMgr.getSubAccount(333812512246L, 33381251220301L);

            Method l_method = WEB3IfoDepositCalcServiceImpl.class.getDeclaredMethod("isLackChargeNonManagement",
                new Class[]{ WEB3GentradeSubAccount.class });
            l_method.setAccessible(true);
            Boolean l_strSortcon = (Boolean)l_method.invoke(l_serviceImpl, new Object[]{l_subAccount});
            assertTrue(l_strSortcon.booleanValue());
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }

        log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>test past");
        log.exiting(STR_METHOD_NAME);
    }

    //証拠金不足額を管理するかを判定する。
    //で取得した部店.is証拠金不足額非管理()の戻り値を返却する。 
    //証拠金不足額を管理しない場合はfalseを返却する。
    public void testIsLackChargeNonManagement_C0002()
    {
        final String STR_METHOD_NAME = "testIsLackChargeNonManagement_C0002()";
        log.entering(STR_METHOD_NAME);

        try
        {
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            MainAccountParams mainAccountParams = TestDBUtility.getMainAccountRow();
            TestDBUtility.insertWithDel(mainAccountParams);
            
            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            SubAccountParams subAccountParams = TestDBUtility.getSubAccountRow();
            subAccountParams.setAccountId(333812512246L);
            TestDBUtility.insertWithDel(subAccountParams);

            TestDBUtility.deleteAll(InstitutionRow.TYPE);
            InstitutionParams institutionParams = TestDBUtility.getInstitutionRow();
            TestDBUtility.insertWithDel(institutionParams);

            TestDBUtility.deleteAll(BranchRow.TYPE);
            BranchParams branchParams = TestDBUtility.getBranchRow();
            TestDBUtility.insertWithDel(branchParams);

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue("webbroker3.gentrade.WEB3GentradeBranch",
                    "isIfodepositLackchargeNonManagement", new Class[]{},
                    new Boolean(false));

            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
            AccountManager l_accountMgr = l_finApp.getAccountManager();
            WEB3GentradeSubAccount l_subAccount =
                (WEB3GentradeSubAccount) l_accountMgr.getSubAccount(333812512246L, 33381251220301L);

            Method l_method = WEB3IfoDepositCalcServiceImpl.class.getDeclaredMethod("isLackChargeNonManagement",
                new Class[]{ WEB3GentradeSubAccount.class });
            l_method.setAccessible(true);
            Boolean l_strSortcon = (Boolean)l_method.invoke(l_serviceImpl, new Object[]{l_subAccount});
            assertFalse(l_strSortcon.booleanValue());
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }

        log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>test past");
        log.exiting(STR_METHOD_NAME);
    }
    /*
     * is証拠金不足額非管理()失敗
     */
    public void testIsLackChargeNonManagement_C0003()
    {
        final String STR_METHOD_NAME = "testIsLackChargeNonManagement_C0003()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            MainAccountParams mainAccountParams = TestDBUtility.getMainAccountRow();
            TestDBUtility.insertWithDel(mainAccountParams);
            
            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            SubAccountParams subAccountParams = TestDBUtility.getSubAccountRow();
            subAccountParams.setAccountId(333812512246L);
            TestDBUtility.insertWithDel(subAccountParams);
            
            TestDBUtility.deleteAll(InstitutionRow.TYPE);
            InstitutionParams institutionParams = TestDBUtility.getInstitutionRow();
            TestDBUtility.insertWithDel(institutionParams);
            
            TestDBUtility.deleteAll(BranchRow.TYPE);
            BranchParams branchParams = TestDBUtility.getBranchRow();
            TestDBUtility.insertWithDel(branchParams);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue("webbroker3.gentrade.WEB3GentradeBranch",
                "isIfodepositLackchargeNonManagement",
                new Class[]{},
                new WEB3BaseException(WEB3ErrorCatalog.SYSTEM_ERROR_80002, "isIfodepositLackchargeNonManagement"));
            
            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
            AccountManager l_accountMgr = l_finApp.getAccountManager();
            WEB3GentradeSubAccount l_subAccount =
                (WEB3GentradeSubAccount) l_accountMgr.getSubAccount(333812512246L, 33381251220301L);
            
            Method l_method = WEB3IfoDepositCalcServiceImpl.class.getDeclaredMethod("isLackChargeNonManagement",
                new Class[]{ WEB3GentradeSubAccount.class });
            l_method.setAccessible(true);
            l_method.invoke(l_serviceImpl, new Object[]{l_subAccount});
            
            fail();
        }
        catch (InvocationTargetException l_exITE)
        {
            log.error(TEST_END + STR_METHOD_NAME, l_exITE);
            log.exiting(TEST_END + STR_METHOD_NAME);
            
            WEB3BaseRuntimeException l_targetException =
                (WEB3BaseRuntimeException)l_exITE.getTargetException();
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                l_targetException.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }

    //で取得した部店から「証拠金SQ日銘柄ポジション非計上」を検索する。
    //「計上しない」場合、trueを返却する。
    public void testIsIfodepositNonCalcSqProductFlag_C0001()
    {
        final String STR_METHOD_NAME = "testIsIfodepositNonCalcSqProductFlag_C0001()";
        log.entering(STR_METHOD_NAME);

        try
        {
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            MainAccountParams mainAccountParams = TestDBUtility.getMainAccountRow();
            TestDBUtility.insertWithDel(mainAccountParams);
            
            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            SubAccountParams subAccountParams = TestDBUtility.getSubAccountRow();
            subAccountParams.setAccountId(333812512246L);
            TestDBUtility.insertWithDel(subAccountParams);

            TestDBUtility.deleteAll(InstitutionRow.TYPE);
            InstitutionParams institutionParams = TestDBUtility.getInstitutionRow();
            TestDBUtility.insertWithDel(institutionParams);

            TestDBUtility.deleteAll(BranchRow.TYPE);
            BranchParams branchParams = TestDBUtility.getBranchRow();
            TestDBUtility.insertWithDel(branchParams);

            TestDBUtility.deleteAll(BranchPreferencesRow.TYPE);
            BranchPreferencesParams branchPreferencesParams = TestDBUtility.getBranchPreferencesRow();
            branchPreferencesParams.setBranchId(33381);
            branchPreferencesParams.setName(WEB3BranchPreferencesNameDef.IFODEPOSIT_NON_CALC_SQ_PRODUCT);
            branchPreferencesParams.setNameSerialNo(1);
            branchPreferencesParams.setValue(WEB3IfodepositNonCalcSqProductDef.NON_CALC);
            TestDBUtility.insertWithDel(branchPreferencesParams);

            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
            AccountManager l_accountMgr = l_finApp.getAccountManager();
            WEB3GentradeSubAccount l_subAccount =
                (WEB3GentradeSubAccount) l_accountMgr.getSubAccount(333812512246L, 33381251220301L);

            Method l_method = WEB3IfoDepositCalcServiceImpl.class.getDeclaredMethod("isIfodepositNonCalcSqProductFlag",
                new Class[]{ WEB3GentradeSubAccount.class });
            l_method.setAccessible(true);
            Boolean l_strSortcon = (Boolean)l_method.invoke(l_serviceImpl, new Object[]{l_subAccount});
            assertTrue(l_strSortcon.booleanValue());
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }

        log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>test past");
        log.exiting(STR_METHOD_NAME);
    }

    //で取得した部店から「証拠金SQ日銘柄ポジション非計上」を検索する。
    //上記以外の場合、falseを返却する。
    public void testIsIfodepositNonCalcSqProductFlag_C0002()
    {
        final String STR_METHOD_NAME = "testIsIfodepositNonCalcSqProductFlag_C0002()";
        log.entering(STR_METHOD_NAME);

        try
        {
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            MainAccountParams mainAccountParams = TestDBUtility.getMainAccountRow();
            TestDBUtility.insertWithDel(mainAccountParams);
            
            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            SubAccountParams subAccountParams = TestDBUtility.getSubAccountRow();
            subAccountParams.setAccountId(333812512246L);
            TestDBUtility.insertWithDel(subAccountParams);

            TestDBUtility.deleteAll(InstitutionRow.TYPE);
            InstitutionParams institutionParams = TestDBUtility.getInstitutionRow();
            TestDBUtility.insertWithDel(institutionParams);

            TestDBUtility.deleteAll(BranchRow.TYPE);
            BranchParams branchParams = TestDBUtility.getBranchRow();
            TestDBUtility.insertWithDel(branchParams);

            TestDBUtility.deleteAll(BranchPreferencesRow.TYPE);
            BranchPreferencesParams branchPreferencesParams = TestDBUtility.getBranchPreferencesRow();
            branchPreferencesParams.setBranchId(33381);
            branchPreferencesParams.setName(WEB3BranchPreferencesNameDef.IFODEPOSIT_NON_CALC_SQ_PRODUCT);
            branchPreferencesParams.setNameSerialNo(1);
            branchPreferencesParams.setValue(WEB3IfodepositNonCalcSqProductDef.DEFAULT);
            TestDBUtility.insertWithDel(branchPreferencesParams);

            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
            AccountManager l_accountMgr = l_finApp.getAccountManager();
            WEB3GentradeSubAccount l_subAccount =
                (WEB3GentradeSubAccount) l_accountMgr.getSubAccount(333812512246L, 33381251220301L);

            Method l_method = WEB3IfoDepositCalcServiceImpl.class.getDeclaredMethod("isIfodepositNonCalcSqProductFlag",
                new Class[]{ WEB3GentradeSubAccount.class });
            l_method.setAccessible(true);
            Boolean l_strSortcon = (Boolean)l_method.invoke(l_serviceImpl, new Object[]{l_subAccount});
            assertFalse(l_strSortcon.booleanValue());
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }

        log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>test past");
        log.exiting(STR_METHOD_NAME);
    }

    private class WEB3IfoDepositCalcServiceImplForTest extends WEB3IfoDepositCalcServiceImpl
    {
        private boolean flag;
        public WEB3IfoDepositCalcCondition createIfoDepositCalcCondition(WEB3GentradeSubAccount l_subAccount) 
        {
            if (this.flag)
            {
                WEB3IfoDepositCalcCondition condition = new WEB3IfoDepositCalcCondition();
                Date[] dates = new Date[]{
                    WEB3DateUtility.getDate("20080815", "yyyyMMdd"),
                    WEB3DateUtility.getDate("20080816", "yyyyMMdd"),
                    WEB3DateUtility.getDate("20080817", "yyyyMMdd"),
                    WEB3DateUtility.getDate("20080818", "yyyyMMdd")};
                condition.setBizDates(dates);
                condition.addCalcConditionPerBranch(WEB3IfoDepositBranchPrefNameDef.IFO_DEPOSIT_PROFITLOSS_CALC, "1");
                return condition;
            }
            try
            {
                throw new WEB3SystemLayerException(WEB3ErrorCatalog.SYSTEM_ERROR_80002, "");
            }
            catch (WEB3SystemLayerException e)
            {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            return null;
        }
        public boolean isFlag()
        {
            return flag;
        }
        public void setFlag(boolean flag)
        {
            this.flag = flag;
        }
    }
}
@
