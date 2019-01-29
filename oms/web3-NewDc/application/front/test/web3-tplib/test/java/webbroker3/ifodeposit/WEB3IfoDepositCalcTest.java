head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.01.29.21;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3IfoDepositCalcTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : WEB3IfoDepositCalcTest.java
Author Name      : Daiwa Institute of Research
Revision History : 2008/08/22  張少傑 (中訊) 新規作成
*/

package webbroker3.ifodeposit;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;
import com.fitechlabs.xtrade.kernel.util.ThreadLocalSystemAttributesRegistry;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ContractTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinTransactionCateg;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderCategEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderStatusEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccountTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TaxTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Trader;
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
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.TradedProductUpdqParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.TradedProductUpdqRow;
import com.fitechlabs.xtrade.plugin.tc.xbaio.data.AioOrderUnitParams;
import com.fitechlabs.xtrade.plugin.tc.xbaio.data.AioOrderUnitRow;
import com.fitechlabs.xtrade.plugin.tc.xbifo.DefaultIfoOrderManagerPersistenceEventInterceptor;
import com.fitechlabs.xtrade.plugin.tc.xbifo.IfoDerivativeTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.xbifo.IfoOrderExecutionConditionType;
import com.fitechlabs.xtrade.plugin.tc.xbifo.IfoOrderManagerPersistenceEventInterceptor;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoContractParams;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoContractRow;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoFinTransactionParams;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoFinTransactionRow;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoOrderParams;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoOrderRow;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoOrderUnitParams;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoOrderUnitRow;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoProductParams;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoProductRow;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoTradedProductParams;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoTradedProductRow;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoTradedProductUpdqParams;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoTradedProductUpdqRow;
import com.fitechlabs.xtrade.plugin.tc.xbifo.ordersubmitter.io.IfoOpenContractOrderSpec;
import test.util.TestDBUtility;
import webbroker3.common.define.WEB3FuturesOptionDivDef;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.gentrade.data.CalendarRow;
import webbroker3.gentrade.data.TradingTimeParams;
import webbroker3.gentrade.data.TradingTimeRow;
import webbroker3.ifodeposit.define.WEB3IfoDepositBranchPrefNameDef;
import webbroker3.ifodeposit.define.WEB3IfoPositionBalanceTypeDef;
import webbroker3.mock.TestBaseForMock;
import webbroker3.tradingpower.data.TpCashBalanceParams;
import webbroker3.tradingpower.data.TpCashBalanceRow;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

public class WEB3IfoDepositCalcTest extends TestBaseForMock
{
    WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3IfoDepositCalcTest.class);
    WEB3IfoDepositCalc depositCalc = null;
    private boolean l_blTest = false;
    private boolean l_blPrint = false;
    public WEB3IfoDepositCalcTest(String arg0)
    {
        super(arg0);
    }

    protected void setUp() throws Exception
    {
        super.setUp();
        TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);
        depositCalc = new WEB3IfoDepositCalc();
    }

    protected void tearDown() throws Exception
    {
        super.tearDown();
    }
    
    //顧客勘定残高マスタ情報Paramsの戻り値がNULL
    //当日振替注文単位Params一覧一条
    //先物OP保有建玉情報一条
    //先物OP当日新規建注文情一条
    //正?的先物OP顧客移動明細做成
    public void testCreateIfoCustomerTransferC001()
    {
        String STR_METHOD_NAME = " testCreateIfoCustomerTransferC001";
        log.entering(STR_METHOD_NAME);
        try
        {
            TestDBUtility.deleteAll(CalendarRow.TYPE);
            //取引時間テーブル;
            TestDBUtility.deleteAll(TradingTimeRow.TYPE);
            TradingTimeParams l_TradingTimeParams1 = TestDBUtility.getTradingTimeRow();
            l_TradingTimeParams1.setInstitutionCode("0D");
            l_TradingTimeParams1.setBranchCode("123");
            l_TradingTimeParams1.setMarketCode("N1");
            l_TradingTimeParams1.setTradingTimeType("13");
            l_TradingTimeParams1.setProductCode("0");
            l_TradingTimeParams1.setBizDateType("1");
            TestDBUtility.insertWithDel(l_TradingTimeParams1);
            
            TradingTimeParams l_TradingTimeParams2 = TestDBUtility.getTradingTimeRow();
            l_TradingTimeParams2.setInstitutionCode("0D");
            l_TradingTimeParams2.setBranchCode("123");
            l_TradingTimeParams2.setMarketCode("N1");
            l_TradingTimeParams2.setTradingTimeType("11");
            l_TradingTimeParams2.setProductCode("0005");
            l_TradingTimeParams2.setBizDateType("1");
            TestDBUtility.insertWithDel(l_TradingTimeParams2);
            
            TradingTimeParams l_TradingTimeParams3 = TestDBUtility.getTradingTimeRow();
            l_TradingTimeParams3.setInstitutionCode("0D");
            l_TradingTimeParams3.setBranchCode("123");
            l_TradingTimeParams3.setMarketCode("N1");
            l_TradingTimeParams3.setTradingTimeType("01");
            l_TradingTimeParams3.setProductCode("0005");
            l_TradingTimeParams3.setBizDateType("1");
            TestDBUtility.insertWithDel(l_TradingTimeParams3);
            
            //顧客勘定残高マスタ
            TestDBUtility.deleteAll(TpCashBalanceRow.TYPE);
            TpCashBalanceParams l_tpCashBalanceParams = TestDBUtility.getTpCashBalanceRow();
            TestDBUtility.insertWithDel(l_tpCashBalanceParams);
            
            //当日振替注文単位表
            TestDBUtility.deleteAll( AioOrderUnitRow.TYPE);
            AioOrderUnitParams l_aioOrderUnitParams = TestDBUtility.getAioOrderUnitRow();
            l_aioOrderUnitParams.setOrderType(OrderTypeEnum.FROM_DEPOSIT_AMOUNT_MARGIN);
            l_aioOrderUnitParams.setOrderStatus(OrderStatusEnum.ORDERED);
            TestDBUtility.insertWithDel(l_aioOrderUnitParams);
            
            //輔助口座表
            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
            l_subAccountParams.setAccountId(111101111010L);
            l_subAccountParams.setSubAccountId(11110111101001L);
            TestDBUtility.insertWithDel(l_subAccountParams);
            
            //顧客表
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            MainAccountParams l_mainAccount = TestDBUtility.getMainAccountRow();
            l_mainAccount.setAccountId(111101111010L);
            TestDBUtility.insertWithDel(l_mainAccount);
            
            
            //銘柄表
            TestDBUtility.deleteAll(ProductRow.TYPE);
            ProductParams l_ProductParams = TestDBUtility.getProductRow();
            l_ProductParams.setProductId(2605000000001L);
            TestDBUtility.insertWithDel(l_ProductParams);

            //取引銘柄銘柄表
            TestDBUtility.deleteAll(TradedProductRow.TYPE);
            TradedProductParams l_TradedProductPrams = TestDBUtility.getTradedProductRow();
            l_TradedProductPrams.setProductId(2605000000001L);
            l_TradedProductPrams.setMarketId(1002);
            TestDBUtility.insertWithDel(l_TradedProductPrams);
            
            //IFO銘柄表
            TestDBUtility.deleteAll(IfoProductRow.TYPE);
            IfoProductParams l_ifoProductParams = TestDBUtility.getIfoProductRow();
            l_ifoProductParams.setProductId(2605000000001L);
            TestDBUtility.insertWithDel(l_ifoProductParams);

            //IFO取引銘柄銘柄表
            TestDBUtility.deleteAll(IfoTradedProductRow.TYPE);
            IfoTradedProductParams l_ifoTradedProductParams = TestDBUtility.getIfoTradedProductRow();
            l_ifoTradedProductParams.setProductId(2605000000001L);
            l_ifoTradedProductParams.setMarketId(1002);
            TestDBUtility.insertWithDel(l_ifoTradedProductParams);

            //建玉表
            TestDBUtility.deleteAll(IfoContractRow.TYPE);
            IfoContractParams l_ifoContractParams = TestDBUtility.getIfoContractRow();
            l_ifoContractParams.setProductId(2605000000001L);
            l_ifoContractParams.setAccountId(111101111010L);
            l_ifoContractParams.setSubAccountId(11110111101001L);
            l_ifoContractParams.setOpenDate(WEB3DateUtility.getDate("20040716","yyyyMMdd"));
            TestDBUtility.insertWithDel(l_ifoContractParams);
            
            //トランザクション表
            TestDBUtility.deleteAll(IfoFinTransactionRow.TYPE);
            IfoFinTransactionParams l_ifoFinTransactionParams = TestDBUtility.getIfoFinTransactionRow();
            l_ifoFinTransactionParams.setProductId(2605000000001L);
            l_ifoFinTransactionParams.setAccountId(111101111010L);
            l_ifoFinTransactionParams.setSubAccountId(11110111101001L);
            l_ifoFinTransactionParams.setOrderId(1001);
            l_ifoFinTransactionParams.setOrderUnitId(1001);
            l_ifoFinTransactionParams.setContractId(1001);
            l_ifoFinTransactionParams.setDeliveryDate(WEB3DateUtility.getDate("20040720","yyyyMMdd"));
            l_ifoFinTransactionParams.setFinTransactionTimestamp(WEB3DateUtility.getDate("20040716","yyyyMMdd"));
            l_ifoFinTransactionParams.fin_transaction_categ = FinTransactionCateg.EQTYPE_IDX_OPTIONS_OPEN;
            TestDBUtility.insertWithDel(l_ifoFinTransactionParams);
            
            //注文単位テーブル
            TestDBUtility.deleteAll(IfoOrderUnitRow.TYPE);
            IfoOrderUnitParams l_IfoOrderUnitParams = TestDBUtility.getIfoOrderUnitRow();
            l_IfoOrderUnitParams.setProductId(2605000000001L);
            l_IfoOrderUnitParams.setAccountId(111101111010L);
            l_IfoOrderUnitParams.setSubAccountId(11110111101001L);
            l_IfoOrderUnitParams.setBranchId(11110);
            l_IfoOrderUnitParams.setMarketId(1002);
            l_IfoOrderUnitParams.setBizDate("20040716");
            l_IfoOrderUnitParams.setDeliveryDate(WEB3DateUtility.getDate("20040720","yyyyMMdd"));
            l_IfoOrderUnitParams.setEstimatedPrice(1000);
            l_IfoOrderUnitParams.setOrderType(OrderTypeEnum.IDX_OPTIONS_BUY_TO_OPEN);
            l_IfoOrderUnitParams.setOrderCateg(OrderCategEnum.IDX_OPTIONS_OPEN);
            l_IfoOrderUnitParams.setFutureOptionDiv(WEB3FuturesOptionDivDef.OPTION);
            TestDBUtility.insertWithDel(l_IfoOrderUnitParams);
            
            //注文テーブル
            TestDBUtility.deleteAll(IfoOrderRow.TYPE);
            IfoOrderParams l_IfoOrderParams = TestDBUtility.getIfoOrderRow();
            l_IfoOrderParams.setAccountId(111101111010L);
            l_IfoOrderParams.setSubAccountId(11110111101001L);
            TestDBUtility.insertWithDel(l_IfoOrderParams);
            
            
            //構造WEB3IfoDepositCalc類對象
            WEB3GentradeSubAccount l_subAccount =  new WEB3GentradeSubAccount(l_subAccountParams);
            WEB3IfoDepositCalcCondition l_ifoDepositCalcCondition = new WEB3IfoDepositCalcCondition();
            Date l_bizDate1 = WEB3DateUtility.getDate("20040715","yyyyMMdd");
            Date l_bizDate2 = WEB3DateUtility.getDate("20040716","yyyyMMdd");
            Date l_bizDate3 = WEB3DateUtility.getDate("20040720","yyyyMMdd");
            Date l_bizDate4 = WEB3DateUtility.getDate("20040721","yyyyMMdd");
            Date[] l_bizDates = {l_bizDate1, l_bizDate2, l_bizDate3, l_bizDate4};
            l_ifoDepositCalcCondition.setBizDates(l_bizDates);
            l_ifoDepositCalcCondition.addCalcConditionPerBranch("ifodeposit.profitloss.calc", "1");
            
            WEB3IfoDepositCalc l_ifo = new WEB3IfoDepositCalc(l_subAccount,l_ifoDepositCalcCondition);
            
            WEB3IfoCustomerTransfer i_ifoCustomer = l_ifo.getIfoCustomerTransfer();
            
            WEB3IfoSummaryContractPerProductContractPrice[] l_ifoSummaryContractPerProductContractPrice =
                l_ifo.getIfoSummaryContractPerProductContractPriceList();
            
            WEB3IfoSummaryContractPerProduct[] l_ifoSummaryContractPerProduct =
                l_ifo.getIfoSummaryContractPerProductList();
            
            WEB3IfoSummaryContractPerIndex[] l_ifoSummaryContractPerIndex =
                l_ifo.getIfoSummaryContractPerIndexList();
            
            assertEquals(0,i_ifoCustomer.getBalance(0),0);
            assertEquals(0,i_ifoCustomer.getBalance(1),0);
            assertEquals(0,i_ifoCustomer.getBalance(2),0);
            assertEquals(350000,i_ifoCustomer.getCurrentBizDateCashinAmount(),0);
            assertEquals(5,i_ifoCustomer.getNextBizDateOptionNetAmount(),0);
            assertEquals(-1005,i_ifoCustomer.getNextBizDateOptionBuyEstimatedNetAmount(),0);
            assertEquals(3720, l_ifoSummaryContractPerProductContractPrice[0].getContractPrice(),0);
            assertEquals(1, l_ifoSummaryContractPerProduct[0].getBuyQuantity(),0);
            assertEquals(1, l_ifoSummaryContractPerIndex[0].getFuturesBuyContractQuantity(), 0);
        }
        catch(Exception l_ex)
        {
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    //    顧客勘定残高マスタ情報Paramsの戻り値!=NULL
    //    当日振替注文単位Params一覧多条
    //    先物OP保有建玉情報一覧多条
    //    先物OP当日新規建注文情報一覧多条
    //    オプションの時
    public void testCreateIfoCustomerTransferC002()
    {
        String STR_METHOD_NAME = " testCreateIfoCustomerTransferC002";
        log.entering(STR_METHOD_NAME);
        try
        {
            TestDBUtility.deleteAll(CalendarRow.TYPE);
            //取引時間テーブル;
            TestDBUtility.deleteAll(TradingTimeRow.TYPE);
            TradingTimeParams l_TradingTimeParams1 = TestDBUtility.getTradingTimeRow();
            l_TradingTimeParams1.setInstitutionCode("0D");
            l_TradingTimeParams1.setBranchCode("123");
            l_TradingTimeParams1.setMarketCode("N1");
            l_TradingTimeParams1.setTradingTimeType("13");
            l_TradingTimeParams1.setProductCode("0");
            l_TradingTimeParams1.setBizDateType("1");
            TestDBUtility.insertWithDel(l_TradingTimeParams1);
            
            TradingTimeParams l_TradingTimeParams2 = TestDBUtility.getTradingTimeRow();
            l_TradingTimeParams2.setInstitutionCode("0D");
            l_TradingTimeParams2.setBranchCode("123");
            l_TradingTimeParams2.setMarketCode("N1");
            l_TradingTimeParams2.setTradingTimeType("11");
            l_TradingTimeParams2.setProductCode("0005");
            l_TradingTimeParams2.setBizDateType("1");
            TestDBUtility.insertWithDel(l_TradingTimeParams2);
            
            TradingTimeParams l_TradingTimeParams3 = TestDBUtility.getTradingTimeRow();
            l_TradingTimeParams3.setInstitutionCode("0D");
            l_TradingTimeParams3.setBranchCode("123");
            l_TradingTimeParams3.setMarketCode("N1");
            l_TradingTimeParams3.setTradingTimeType("01");
            l_TradingTimeParams3.setProductCode("0005");
            l_TradingTimeParams3.setBizDateType("1");
            TestDBUtility.insertWithDel(l_TradingTimeParams3);
            
            //顧客勘定残高マスタ
            TestDBUtility.deleteAll(TpCashBalanceRow.TYPE);
            TpCashBalanceParams l_tpCashBalanceParams = TestDBUtility.getTpCashBalanceRow();
            l_tpCashBalanceParams.setAccountId(111101111010L);
            l_tpCashBalanceParams.setSubAccountId(11110111101001L);
            l_tpCashBalanceParams.setCashBalance0(1000);
            l_tpCashBalanceParams.setCashBalance1(2000);
            l_tpCashBalanceParams.setCashBalance2(3000);
            TestDBUtility.insertWithDel(l_tpCashBalanceParams);
            
            //当日振替注文単位表
            TestDBUtility.deleteAll( AioOrderUnitRow.TYPE);
            AioOrderUnitParams l_aioOrderUnitParams = TestDBUtility.getAioOrderUnitRow();
            l_aioOrderUnitParams.setOrderType(OrderTypeEnum.FROM_DEPOSIT_AMOUNT_MARGIN);
            l_aioOrderUnitParams.setOrderStatus(OrderStatusEnum.ORDERED);
            l_aioOrderUnitParams.setOrderId(1001L);
            l_aioOrderUnitParams.setOrderUnitId(1001);
            TestDBUtility.insertWithDel(l_aioOrderUnitParams);
            

            AioOrderUnitParams l_aioOrderUnitParams1 = TestDBUtility.getAioOrderUnitRow();
            l_aioOrderUnitParams1.setOrderType(OrderTypeEnum.FROM_DEPOSIT_AMOUNT_MARGIN);
            l_aioOrderUnitParams1.setOrderStatus(OrderStatusEnum.ORDERED);
            l_aioOrderUnitParams1.setOrderUnitId(1002L);
            l_aioOrderUnitParams1.setOrderId(1002L);
            TestDBUtility.insertWithDel(l_aioOrderUnitParams1);
            
            //輔助口座表
            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
            l_subAccountParams.setAccountId(111101111010L);
            l_subAccountParams.setSubAccountId(11110111101001L);
            TestDBUtility.insertWithDel(l_subAccountParams);
            
            //顧客表
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            MainAccountParams l_mainAccount = TestDBUtility.getMainAccountRow();
            l_mainAccount.setAccountId(111101111010L);
            TestDBUtility.insertWithDel(l_mainAccount);
            
            //銘柄表
            TestDBUtility.deleteAll(ProductRow.TYPE);
            ProductParams l_ProductParams = TestDBUtility.getProductRow();
            l_ProductParams.setProductId(2605000000001L);
            TestDBUtility.insertWithDel(l_ProductParams);

            //取引銘柄銘柄表
            TestDBUtility.deleteAll(TradedProductRow.TYPE);
            TradedProductParams l_TradedProductPrams = TestDBUtility.getTradedProductRow();
            l_TradedProductPrams.setProductId(2605000000001L);
            l_TradedProductPrams.setMarketId(1002);
            TestDBUtility.insertWithDel(l_TradedProductPrams);
            
            //IFO銘柄表
            TestDBUtility.deleteAll(IfoProductRow.TYPE);
            IfoProductParams l_ifoProductParams = TestDBUtility.getIfoProductRow();
            l_ifoProductParams.setProductId(2605000000001L);
            TestDBUtility.insertWithDel(l_ifoProductParams);

            //IFO取引銘柄銘柄表
            TestDBUtility.deleteAll(IfoTradedProductRow.TYPE);
            IfoTradedProductParams l_ifoTradedProductParams = TestDBUtility.getIfoTradedProductRow();
            l_ifoTradedProductParams.setProductId(2605000000001L);
            l_ifoTradedProductParams.setMarketId(1002);
            TestDBUtility.insertWithDel(l_ifoTradedProductParams);

            //建玉表
            TestDBUtility.deleteAll(IfoContractRow.TYPE);
            IfoContractParams l_ifoContractParams = TestDBUtility.getIfoContractRow();
            l_ifoContractParams.setProductId(2605000000001L);
            l_ifoContractParams.setAccountId(111101111010L);
            l_ifoContractParams.setSubAccountId(11110111101001L);
            l_ifoContractParams.setOpenDate(WEB3DateUtility.getDate("20040716","yyyyMMdd"));
            TestDBUtility.insertWithDel(l_ifoContractParams);
            

            IfoContractParams l_ifoContractParams1 = TestDBUtility.getIfoContractRow();
            l_ifoContractParams1.setContractId(1002L);
            l_ifoContractParams1.setContractPrice(3250);
            l_ifoContractParams1.setProductId(2605000000001L);
            l_ifoContractParams1.setAccountId(111101111010L);
            l_ifoContractParams1.setSubAccountId(11110111101001L);
            l_ifoContractParams1.setOpenDate(WEB3DateUtility.getDate("20040716","yyyyMMdd"));
            TestDBUtility.insertWithDel(l_ifoContractParams1);
            
            //トランザクション表
            TestDBUtility.deleteAll(IfoFinTransactionRow.TYPE);
            IfoFinTransactionParams l_ifoFinTransactionParams = TestDBUtility.getIfoFinTransactionRow();
            l_ifoFinTransactionParams.setProductId(2605000000001L);
            l_ifoFinTransactionParams.setAccountId(111101111010L);
            l_ifoFinTransactionParams.setSubAccountId(11110111101001L);
            l_ifoFinTransactionParams.setOrderId(1001);
            l_ifoFinTransactionParams.setOrderUnitId(1001);
            l_ifoFinTransactionParams.setContractId(1001);
            l_ifoFinTransactionParams.setDeliveryDate(WEB3DateUtility.getDate("20040720","yyyyMMdd"));
            l_ifoFinTransactionParams.setFinTransactionTimestamp(WEB3DateUtility.getDate("20040716","yyyyMMdd"));
            l_ifoFinTransactionParams.fin_transaction_categ = FinTransactionCateg.EQTYPE_IDX_OPTIONS_OPEN;
            TestDBUtility.insertWithDel(l_ifoFinTransactionParams);
            

            IfoFinTransactionParams l_ifoFinTransactionParams1 = TestDBUtility.getIfoFinTransactionRow();
            l_ifoFinTransactionParams1.setFinTransactionId(2L);
            l_ifoFinTransactionParams1.setProductId(2605000000001L);
            l_ifoFinTransactionParams1.setAccountId(111101111010L);
            l_ifoFinTransactionParams1.setSubAccountId(11110111101001L);
            l_ifoFinTransactionParams1.setOrderId(1002L);
            l_ifoFinTransactionParams1.setOrderUnitId(1002);
            l_ifoFinTransactionParams1.setContractId(1002);
            l_ifoFinTransactionParams1.setDeliveryDate(WEB3DateUtility.getDate("20040720","yyyyMMdd"));
            l_ifoFinTransactionParams1.setFinTransactionTimestamp(WEB3DateUtility.getDate("20040716","yyyyMMdd"));
            l_ifoFinTransactionParams1.fin_transaction_categ = FinTransactionCateg.EQTYPE_IDX_OPTIONS_OPEN;
            TestDBUtility.insertWithDel(l_ifoFinTransactionParams1);
            
            //注文単位テーブル
            TestDBUtility.deleteAll(IfoOrderUnitRow.TYPE);
            IfoOrderUnitParams l_IfoOrderUnitParams = TestDBUtility.getIfoOrderUnitRow();
            l_IfoOrderUnitParams.setProductId(2605000000001L);
            l_IfoOrderUnitParams.setAccountId(111101111010L);
            l_IfoOrderUnitParams.setSubAccountId(11110111101001L);
            l_IfoOrderUnitParams.setBranchId(11110);
            l_IfoOrderUnitParams.setMarketId(1002);
            l_IfoOrderUnitParams.setBizDate("20040716");
            l_IfoOrderUnitParams.setDeliveryDate(WEB3DateUtility.getDate("20040720","yyyyMMdd"));
            l_IfoOrderUnitParams.setEstimatedPrice(1000);
            l_IfoOrderUnitParams.setOrderType(OrderTypeEnum.IDX_OPTIONS_BUY_TO_OPEN);
            l_IfoOrderUnitParams.setOrderCateg(OrderCategEnum.IDX_OPTIONS_OPEN);
            l_IfoOrderUnitParams.setFutureOptionDiv(WEB3FuturesOptionDivDef.OPTION);
            TestDBUtility.insertWithDel(l_IfoOrderUnitParams);
            
            IfoOrderUnitParams l_IfoOrderUnitParams1 = TestDBUtility.getIfoOrderUnitRow();
            l_IfoOrderUnitParams1.setOrderId(1002L);
            l_IfoOrderUnitParams1.setOrderUnitId(1002L);
            l_IfoOrderUnitParams1.setProductId(2605000000001L);
            l_IfoOrderUnitParams1.setAccountId(111101111010L);
            l_IfoOrderUnitParams1.setSubAccountId(11110111101001L);
            l_IfoOrderUnitParams1.setBranchId(11110);
            l_IfoOrderUnitParams1.setMarketId(1002);
            l_IfoOrderUnitParams1.setBizDate("20040716");
            l_IfoOrderUnitParams1.setDeliveryDate(WEB3DateUtility.getDate("20040720","yyyyMMdd"));
            l_IfoOrderUnitParams1.setEstimatedPrice(1000);
            l_IfoOrderUnitParams1.setOrderType(OrderTypeEnum.IDX_OPTIONS_BUY_TO_OPEN);
            l_IfoOrderUnitParams1.setOrderCateg(OrderCategEnum.IDX_OPTIONS_OPEN);
            l_IfoOrderUnitParams1.setOrderRequestNumber("9008");
            l_IfoOrderUnitParams1.setFirstOrderUnitId(2001L);
            l_IfoOrderUnitParams1.setFutureOptionDiv(WEB3FuturesOptionDivDef.OPTION);
            TestDBUtility.insertWithDel(l_IfoOrderUnitParams1);
            
            //注文テーブル
            TestDBUtility.deleteAll(IfoOrderRow.TYPE);
            IfoOrderParams l_IfoOrderParams = TestDBUtility.getIfoOrderRow();
            l_IfoOrderParams.setOrderId(1001);
            l_IfoOrderParams.setAccountId(111101111010L);
            l_IfoOrderParams.setSubAccountId(11110111101001L);
            TestDBUtility.insertWithDel(l_IfoOrderParams);
            
            IfoOrderParams l_IfoOrderParams1 = TestDBUtility.getIfoOrderRow();
            l_IfoOrderParams1.setOrderId(1002L);
            l_IfoOrderParams1.setAccountId(111101111010L);
            l_IfoOrderParams1.setSubAccountId(11110111101001L);
            TestDBUtility.insertWithDel(l_IfoOrderParams1);
            
            
            //構造WEB3IfoDepositCalc類對象
            WEB3GentradeSubAccount l_subAccount =  new WEB3GentradeSubAccount(l_subAccountParams);
            WEB3IfoDepositCalcCondition l_ifoDepositCalcCondition = new WEB3IfoDepositCalcCondition();
            Date l_bizDate1 = WEB3DateUtility.getDate("20040715","yyyyMMdd");
            Date l_bizDate2 = WEB3DateUtility.getDate("20040716","yyyyMMdd");
            Date l_bizDate3 = WEB3DateUtility.getDate("20040720","yyyyMMdd");
            Date l_bizDate4 = WEB3DateUtility.getDate("20040721","yyyyMMdd");
            Date[] l_bizDates = {l_bizDate1, l_bizDate2, l_bizDate3, l_bizDate4};
            l_ifoDepositCalcCondition.setBizDates(l_bizDates);
            l_ifoDepositCalcCondition.addCalcConditionPerBranch("ifodeposit.profitloss.calc", "1");
            
            WEB3IfoDepositCalc l_ifo = new WEB3IfoDepositCalc(l_subAccount,l_ifoDepositCalcCondition);
            
            WEB3IfoCustomerTransfer i_ifoCustomer = l_ifo.getIfoCustomerTransfer();
            assertEquals(1000,i_ifoCustomer.getBalance(0),0);
            assertEquals(2000,i_ifoCustomer.getBalance(1),0);
            assertEquals(3000,i_ifoCustomer.getBalance(2),0);
            assertEquals(700000,i_ifoCustomer.getCurrentBizDateCashinAmount(),0);
            assertEquals(10,i_ifoCustomer.getNextBizDateOptionNetAmount(),0);
            assertEquals(-2010,i_ifoCustomer.getNextBizDateOptionBuyEstimatedNetAmount(),0);
        }
        catch(Exception l_ex)
        {
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }

//    当日振替注文単位Params一覧存在しない
//    先物OP保有建玉情報一覧存在しない
//    先物OP当日新規建注文存在しない
    public void testCreateIfoCustomerTransferC003()
    {
        String STR_METHOD_NAME = " testCreateIfoCustomerTransferC003";
        log.entering(STR_METHOD_NAME);
        try
        {
            TestDBUtility.deleteAll(CalendarRow.TYPE);
            //取引時間テーブル;
            TestDBUtility.deleteAll(TradingTimeRow.TYPE);
            TradingTimeParams l_TradingTimeParams1 = TestDBUtility.getTradingTimeRow();
            l_TradingTimeParams1.setInstitutionCode("0D");
            l_TradingTimeParams1.setBranchCode("123");
            l_TradingTimeParams1.setMarketCode("N1");
            l_TradingTimeParams1.setTradingTimeType("13");
            l_TradingTimeParams1.setProductCode("0");
            l_TradingTimeParams1.setBizDateType("1");
            TestDBUtility.insertWithDel(l_TradingTimeParams1);
            
            TradingTimeParams l_TradingTimeParams2 = TestDBUtility.getTradingTimeRow();
            l_TradingTimeParams2.setInstitutionCode("0D");
            l_TradingTimeParams2.setBranchCode("123");
            l_TradingTimeParams2.setMarketCode("N1");
            l_TradingTimeParams2.setTradingTimeType("11");
            l_TradingTimeParams2.setProductCode("0005");
            l_TradingTimeParams2.setBizDateType("1");
            TestDBUtility.insertWithDel(l_TradingTimeParams2);
            
            TradingTimeParams l_TradingTimeParams3 = TestDBUtility.getTradingTimeRow();
            l_TradingTimeParams3.setInstitutionCode("0D");
            l_TradingTimeParams3.setBranchCode("123");
            l_TradingTimeParams3.setMarketCode("N1");
            l_TradingTimeParams3.setTradingTimeType("01");
            l_TradingTimeParams3.setProductCode("0005");
            l_TradingTimeParams3.setBizDateType("1");
            TestDBUtility.insertWithDel(l_TradingTimeParams3);
            
            //顧客勘定残高マスタ
            TestDBUtility.deleteAll(TpCashBalanceRow.TYPE);
            TpCashBalanceParams l_tpCashBalanceParams = TestDBUtility.getTpCashBalanceRow();
            l_tpCashBalanceParams.setAccountId(111101111010L);
            l_tpCashBalanceParams.setSubAccountId(11110111101001L);
            l_tpCashBalanceParams.setCashBalance0(1000);
            l_tpCashBalanceParams.setCashBalance1(2000);
            l_tpCashBalanceParams.setCashBalance2(3000);
            TestDBUtility.insertWithDel(l_tpCashBalanceParams);
            
            //当日振替注文単位表
            TestDBUtility.deleteAll( AioOrderUnitRow.TYPE);
            AioOrderUnitParams l_aioOrderUnitParams = TestDBUtility.getAioOrderUnitRow();
            l_aioOrderUnitParams.setOrderType(OrderTypeEnum.FROM_DEPOSIT_AMOUNT_MARGIN);
            l_aioOrderUnitParams.setOrderStatus(OrderStatusEnum.ORDERED);
            l_aioOrderUnitParams.setAccountId(11110111101002L);
            l_aioOrderUnitParams.setSubAccountId(11110111101002L);
            TestDBUtility.insertWithDel(l_aioOrderUnitParams);
            
            //輔助口座表
            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
            l_subAccountParams.setAccountId(111101111010L);
            l_subAccountParams.setSubAccountId(11110111101001L);
            TestDBUtility.insertWithDel(l_subAccountParams);
            
            //顧客表
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            MainAccountParams l_mainAccount = TestDBUtility.getMainAccountRow();
            l_mainAccount.setAccountId(111101111010L);
            TestDBUtility.insertWithDel(l_mainAccount);
            
            //銘柄表
            TestDBUtility.deleteAll(ProductRow.TYPE);
            ProductParams l_ProductParams = TestDBUtility.getProductRow();
            l_ProductParams.setProductId(2605000000001L);
            TestDBUtility.insertWithDel(l_ProductParams);

            //取引銘柄銘柄表
            TestDBUtility.deleteAll(TradedProductRow.TYPE);
            TradedProductParams l_TradedProductPrams = TestDBUtility.getTradedProductRow();
            l_TradedProductPrams.setProductId(2605000000001L);
            l_TradedProductPrams.setMarketId(1002);
            TestDBUtility.insertWithDel(l_TradedProductPrams);
            
            //IFO銘柄表
            TestDBUtility.deleteAll(IfoProductRow.TYPE);
            IfoProductParams l_ifoProductParams = TestDBUtility.getIfoProductRow();
            l_ifoProductParams.setProductId(2605000000001L);
            TestDBUtility.insertWithDel(l_ifoProductParams);

            //IFO取引銘柄銘柄表
            TestDBUtility.deleteAll(IfoTradedProductRow.TYPE);
            IfoTradedProductParams l_ifoTradedProductParams = TestDBUtility.getIfoTradedProductRow();
            l_ifoTradedProductParams.setProductId(2605000000001L);
            l_ifoTradedProductParams.setMarketId(1002);
            TestDBUtility.insertWithDel(l_ifoTradedProductParams);

            //建玉表
            TestDBUtility.deleteAll(IfoContractRow.TYPE);
            IfoContractParams l_ifoContractParams = TestDBUtility.getIfoContractRow();
            l_ifoContractParams.setProductId(2605000000001L);
            l_ifoContractParams.setAccountId(11110111101002L);
            l_ifoContractParams.setSubAccountId(11110111101002L);
            l_ifoContractParams.setOpenDate(WEB3DateUtility.getDate("20040716","yyyyMMdd"));
            TestDBUtility.insertWithDel(l_ifoContractParams);
            
            //トランザクション表
            TestDBUtility.deleteAll(IfoFinTransactionRow.TYPE);
            IfoFinTransactionParams l_ifoFinTransactionParams = TestDBUtility.getIfoFinTransactionRow();
            l_ifoFinTransactionParams.setProductId(2605000000001L);
            l_ifoFinTransactionParams.setAccountId(111101111012L);
            l_ifoFinTransactionParams.setSubAccountId(11110111101002L);
            l_ifoFinTransactionParams.setOrderId(1001);
            l_ifoFinTransactionParams.setOrderUnitId(1001);
            l_ifoFinTransactionParams.setContractId(1001);
            l_ifoFinTransactionParams.setDeliveryDate(WEB3DateUtility.getDate("20040720","yyyyMMdd"));
            l_ifoFinTransactionParams.setFinTransactionTimestamp(WEB3DateUtility.getDate("20040716","yyyyMMdd"));
            l_ifoFinTransactionParams.fin_transaction_categ = FinTransactionCateg.EQTYPE_IDX_OPTIONS_OPEN;
            TestDBUtility.insertWithDel(l_ifoFinTransactionParams);
            
            //注文単位テーブル
            TestDBUtility.deleteAll(IfoOrderUnitRow.TYPE);
            IfoOrderUnitParams l_IfoOrderUnitParams = TestDBUtility.getIfoOrderUnitRow();
            l_IfoOrderUnitParams.setProductId(2605000000001L);
            l_IfoOrderUnitParams.setAccountId(111101111012L);
            l_IfoOrderUnitParams.setSubAccountId(11110111101002L);
            l_IfoOrderUnitParams.setBranchId(11110);
            l_IfoOrderUnitParams.setMarketId(1002);
            l_IfoOrderUnitParams.setBizDate("20040716");
            l_IfoOrderUnitParams.setDeliveryDate(WEB3DateUtility.getDate("20040720","yyyyMMdd"));
            l_IfoOrderUnitParams.setEstimatedPrice(1000);
            l_IfoOrderUnitParams.setOrderType(OrderTypeEnum.IDX_OPTIONS_BUY_TO_OPEN);
            l_IfoOrderUnitParams.setOrderCateg(OrderCategEnum.IDX_OPTIONS_OPEN);
            l_IfoOrderUnitParams.setFutureOptionDiv(WEB3FuturesOptionDivDef.OPTION);
            TestDBUtility.insertWithDel(l_IfoOrderUnitParams);
            
            //注文テーブル
            TestDBUtility.deleteAll(IfoOrderRow.TYPE);
            IfoOrderParams l_IfoOrderParams = TestDBUtility.getIfoOrderRow();
            l_IfoOrderParams.setAccountId(111101111012L);
            l_IfoOrderParams.setSubAccountId(11110111101002L);
            TestDBUtility.insertWithDel(l_IfoOrderParams);
            
            
            //構造WEB3IfoDepositCalc類對象
            WEB3GentradeSubAccount l_subAccount =  new WEB3GentradeSubAccount(l_subAccountParams);
            WEB3IfoDepositCalcCondition l_ifoDepositCalcCondition = new WEB3IfoDepositCalcCondition();
            Date l_bizDate1 = WEB3DateUtility.getDate("20040715","yyyyMMdd");
            Date l_bizDate2 = WEB3DateUtility.getDate("20040716","yyyyMMdd");
            Date l_bizDate3 = WEB3DateUtility.getDate("20040720","yyyyMMdd");
            Date l_bizDate4 = WEB3DateUtility.getDate("20040721","yyyyMMdd");
            Date[] l_bizDates = {l_bizDate1, l_bizDate2, l_bizDate3, l_bizDate4};
            l_ifoDepositCalcCondition.setBizDates(l_bizDates);
            l_ifoDepositCalcCondition.addCalcConditionPerBranch("ifodeposit.profitloss.calc", "1");
            
            WEB3IfoDepositCalc l_ifo = new WEB3IfoDepositCalc(l_subAccount,l_ifoDepositCalcCondition);
            
            WEB3IfoCustomerTransfer i_ifoCustomer = l_ifo.getIfoCustomerTransfer();
            assertEquals(1000, i_ifoCustomer.getBalance(0),0);
            assertEquals(2000, i_ifoCustomer.getBalance(1),0);
            assertEquals(3000, i_ifoCustomer.getBalance(2),0);
            assertEquals(0, i_ifoCustomer.getCurrentBizDateCashinAmount(),0);
            assertEquals(0, i_ifoCustomer.getNextBizDateOptionNetAmount(),0);
            assertEquals(0, i_ifoCustomer.getNextBizDateOptionBuyEstimatedNetAmount(),0);
        }
        catch(Exception l_ex)
        {
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    //返回正確的証拠金計算条件オブジェクト
    public void testWEB3IfoDepositCalc()
    {
        String STR_METHOD_NAME = " testWEB3IfoDepositCalc";
        log.entering(STR_METHOD_NAME);
        try
        {
            TestDBUtility.deleteAll(CalendarRow.TYPE);
            //取引時間テーブル;
            TestDBUtility.deleteAll(TradingTimeRow.TYPE);
            TradingTimeParams l_TradingTimeParams1 = TestDBUtility.getTradingTimeRow();
            l_TradingTimeParams1.setInstitutionCode("0D");
            l_TradingTimeParams1.setBranchCode("123");
            l_TradingTimeParams1.setMarketCode("N1");
            l_TradingTimeParams1.setTradingTimeType("13");
            l_TradingTimeParams1.setProductCode("0");
            l_TradingTimeParams1.setBizDateType("1");
            TestDBUtility.insertWithDel(l_TradingTimeParams1);
            
            TradingTimeParams l_TradingTimeParams2 = TestDBUtility.getTradingTimeRow();
            l_TradingTimeParams2.setInstitutionCode("0D");
            l_TradingTimeParams2.setBranchCode("123");
            l_TradingTimeParams2.setMarketCode("N1");
            l_TradingTimeParams2.setTradingTimeType("11");
            l_TradingTimeParams2.setProductCode("0005");
            l_TradingTimeParams2.setBizDateType("1");
            TestDBUtility.insertWithDel(l_TradingTimeParams2);
            
            TradingTimeParams l_TradingTimeParams3 = TestDBUtility.getTradingTimeRow();
            l_TradingTimeParams3.setInstitutionCode("0D");
            l_TradingTimeParams3.setBranchCode("123");
            l_TradingTimeParams3.setMarketCode("N1");
            l_TradingTimeParams3.setTradingTimeType("01");
            l_TradingTimeParams3.setProductCode("0005");
            l_TradingTimeParams3.setBizDateType("1");
            TestDBUtility.insertWithDel(l_TradingTimeParams3);
            
            TradingTimeParams l_TradingTimeParams4 = TestDBUtility.getTradingTimeRow();
            l_TradingTimeParams4.setInstitutionCode("0D");
            l_TradingTimeParams4.setBranchCode("123");
            l_TradingTimeParams4.setMarketCode("N1");
            l_TradingTimeParams4.setTradingTimeType("01");
            l_TradingTimeParams4.setProductCode("0");
            l_TradingTimeParams4.setBizDateType("1");
            TestDBUtility.insertWithDel(l_TradingTimeParams4);
            
            //市場表
            TestDBUtility.deleteAll(MarketRow.TYPE);
            MarketParams l_marketParams = TestDBUtility.getMarketRow();
            l_marketParams.setMarketId(1002);
            TestDBUtility.insertWithDel(l_marketParams);
            
            //顧客勘定残高マスタ
            TestDBUtility.deleteAll(TpCashBalanceRow.TYPE);
            TpCashBalanceParams l_tpCashBalanceParams = TestDBUtility.getTpCashBalanceRow();
            TestDBUtility.insertWithDel(l_tpCashBalanceParams);
            
            //当日振替注文単位表
            TestDBUtility.deleteAll( AioOrderUnitRow.TYPE);
            AioOrderUnitParams l_aioOrderUnitParams = TestDBUtility.getAioOrderUnitRow();
            l_aioOrderUnitParams.setOrderType(OrderTypeEnum.FROM_DEPOSIT_AMOUNT_MARGIN);
            l_aioOrderUnitParams.setOrderStatus(OrderStatusEnum.ORDERED);
            TestDBUtility.insertWithDel(l_aioOrderUnitParams);
            
            //輔助口座表
            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
            l_subAccountParams.setAccountId(111101111010L);
            l_subAccountParams.setSubAccountId(11110111101001L);
            TestDBUtility.insertWithDel(l_subAccountParams);
            
            //顧客表
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            MainAccountParams l_mainAccount = TestDBUtility.getMainAccountRow();
            l_mainAccount.setAccountId(111101111010L);
            TestDBUtility.insertWithDel(l_mainAccount);
            
            //銘柄表
            TestDBUtility.deleteAll(ProductRow.TYPE);
            ProductParams l_ProductParams = TestDBUtility.getProductRow();
            l_ProductParams.setInstitutionCode("0D");
            l_ProductParams.setProductId(2605000000001L);
            TestDBUtility.insertWithDel(l_ProductParams);

            //取引銘柄銘柄表
            TestDBUtility.deleteAll(TradedProductRow.TYPE);
            TradedProductParams l_TradedProductPrams = TestDBUtility.getTradedProductRow();
            l_TradedProductPrams.setProductId(2605000000001L);
            l_TradedProductPrams.setInstitutionCode("0D");
            l_TradedProductPrams.setMarketId(1002);
            TestDBUtility.insertWithDel(l_TradedProductPrams);
            
            //IFO銘柄表
            TestDBUtility.deleteAll(IfoProductRow.TYPE);
            IfoProductParams l_ifoProductParams = TestDBUtility.getIfoProductRow();
            l_ifoProductParams.setInstitutionCode("0D");
            l_ifoProductParams.setSplitType("000");
            l_ifoProductParams.setProductId(2605000000001L);
            TestDBUtility.insertWithDel(l_ifoProductParams);

            //IFO取引銘柄銘柄表
            TestDBUtility.deleteAll(IfoTradedProductRow.TYPE);
            IfoTradedProductParams l_ifoTradedProductParams = TestDBUtility.getIfoTradedProductRow();
            l_ifoTradedProductParams.setProductId(2605000000001L);
            l_ifoTradedProductParams.setInstitutionCode("0D");
            l_ifoTradedProductParams.setMarketId(1002);
            TestDBUtility.insertWithDel(l_ifoTradedProductParams);

            //建玉表
            TestDBUtility.deleteAll(IfoContractRow.TYPE);
            IfoContractParams l_ifoContractParams = TestDBUtility.getIfoContractRow();
            l_ifoContractParams.setProductId(2605000000001L);
            l_ifoContractParams.setAccountId(111101111010L);
            l_ifoContractParams.setSubAccountId(11110111101001L);
            l_ifoContractParams.setOpenDate(WEB3DateUtility.getDate("20040716","yyyyMMdd"));
            TestDBUtility.insertWithDel(l_ifoContractParams);
            
            //トランザクション表
            TestDBUtility.deleteAll(IfoFinTransactionRow.TYPE);
            IfoFinTransactionParams l_ifoFinTransactionParams = TestDBUtility.getIfoFinTransactionRow();
            l_ifoFinTransactionParams.setProductId(2605000000001L);
            l_ifoFinTransactionParams.setAccountId(111101111010L);
            l_ifoFinTransactionParams.setSubAccountId(11110111101001L);
            l_ifoFinTransactionParams.setOrderId(1001);
            l_ifoFinTransactionParams.setOrderUnitId(1001);
            l_ifoFinTransactionParams.setContractId(1001);
            l_ifoFinTransactionParams.setDeliveryDate(WEB3DateUtility.getDate("20040720","yyyyMMdd"));
            l_ifoFinTransactionParams.setFinTransactionTimestamp(WEB3DateUtility.getDate("20040716","yyyyMMdd"));
            l_ifoFinTransactionParams.fin_transaction_categ = FinTransactionCateg.EQTYPE_IDX_OPTIONS_OPEN;
            TestDBUtility.insertWithDel(l_ifoFinTransactionParams);
            
            //注文単位テーブル
            TestDBUtility.deleteAll(IfoOrderUnitRow.TYPE);
            IfoOrderUnitParams l_IfoOrderUnitParams = TestDBUtility.getIfoOrderUnitRow();
            l_IfoOrderUnitParams.setProductId(2605000000001L);
            l_IfoOrderUnitParams.setAccountId(111101111010L);
            l_IfoOrderUnitParams.setSubAccountId(11110111101001L);
            l_IfoOrderUnitParams.setBranchId(11110);
            l_IfoOrderUnitParams.setMarketId(1002);
            l_IfoOrderUnitParams.setBizDate("20040716");
            l_IfoOrderUnitParams.setDeliveryDate(WEB3DateUtility.getDate("20040720","yyyyMMdd"));
            l_IfoOrderUnitParams.setEstimatedPrice(1000);
            l_IfoOrderUnitParams.setOrderType(OrderTypeEnum.IDX_OPTIONS_BUY_TO_OPEN);
            l_IfoOrderUnitParams.setOrderCateg(OrderCategEnum.IDX_OPTIONS_OPEN);
            l_IfoOrderUnitParams.setFutureOptionDiv(WEB3FuturesOptionDivDef.OPTION);
            TestDBUtility.insertWithDel(l_IfoOrderUnitParams);
            
            //注文テーブル
            TestDBUtility.deleteAll(IfoOrderRow.TYPE);
            IfoOrderParams l_IfoOrderParams = TestDBUtility.getIfoOrderRow();
            l_IfoOrderParams.setAccountId(111101111010L);
            l_IfoOrderParams.setSubAccountId(11110111101001L);
            TestDBUtility.insertWithDel(l_IfoOrderParams);
            
            
            //構造WEB3IfoDepositCalc類對象
            WEB3GentradeSubAccount l_subAccount =  new WEB3GentradeSubAccount(l_subAccountParams);
            WEB3IfoDepositCalcCondition l_ifoDepositCalcCondition = new WEB3IfoDepositCalcCondition();
            Date l_bizDate1 = WEB3DateUtility.getDate("20040715","yyyyMMdd");
            Date l_bizDate2 = WEB3DateUtility.getDate("20040716","yyyyMMdd");
            Date l_bizDate3 = WEB3DateUtility.getDate("20040720","yyyyMMdd");
            Date l_bizDate4 = WEB3DateUtility.getDate("20040721","yyyyMMdd");
            Date[] l_bizDates = {l_bizDate1, l_bizDate2, l_bizDate3, l_bizDate4};
            l_ifoDepositCalcCondition.setBizDates(l_bizDates);
            l_ifoDepositCalcCondition.addCalcConditionPerBranch("ifodeposit.profitloss.calc", "1");
            
            Trader trader = null;
            boolean isBuyToOpen = true;
            String underlyingProductCode = "0005";
            IfoDerivativeTypeEnum derivativeType = IfoDerivativeTypeEnum.FUTURES;
            String monthOfDelivery = "200503";
            double strikePrice = 0;
            String marketCode = "SP";
            double quantity = 1000;
            IfoOrderExecutionConditionType execType = null; 
            Date orderExpDate = new Date();
            TaxTypeEnum taxType = null;
            IfoOpenContractOrderSpec l_orderspec = IfoOpenContractOrderSpec.createMarketOrderSpec(trader,
                    isBuyToOpen,underlyingProductCode,
                    derivativeType,monthOfDelivery,
                    strikePrice,marketCode,quantity,
                    execType,orderExpDate,taxType);
            IfoOrderManagerPersistenceEventInterceptor l_interceptor = new DefaultIfoOrderManagerPersistenceEventInterceptor();
            OrderTypeEnum l_orderTypeEnum = OrderTypeEnum.IDX_FUTURES_BUY_TO_OPEN;
            
            TestDBUtility.deleteAll(IfoOrderUnitParams.TYPE);
            IfoOrderUnitParams l_orderUnitParams = TestDBUtility.getIfoOrderUnitRow();
            l_orderUnitParams.setOrderType(OrderTypeEnum.IDX_FUTURES_BUY_TO_OPEN);
            TestDBUtility.insertWithDel(l_orderUnitParams);       

            WEB3IfoNewOrderSpec l_ifoNewOrderSpec = 
                WEB3IfoNewOrderSpec.createWEB3IfoNewOrderSpec(
                l_subAccount,
                l_interceptor,
                l_orderspec,
                l_orderTypeEnum); 

            WEB3IfoDepositCalc l_ifo =
                new WEB3IfoDepositCalc(l_subAccount, l_ifoDepositCalcCondition, l_ifoNewOrderSpec);
            
            WEB3IfoCustomerTransfer i_ifoCustomer = l_ifo.getIfoCustomerTransfer();

            WEB3IfoSummaryContractPerProductContractPrice[] l_ifoSummaryContractPerProductContractPrice =
                l_ifo.getIfoSummaryContractPerProductContractPriceList();
            
            WEB3IfoSummaryContractPerProduct[] l_ifoSummaryContractPerProduct =
                l_ifo.getIfoSummaryContractPerProductList();
            
            WEB3IfoSummaryContractPerIndex[] l_ifoSummaryContractPerIndex =
                l_ifo.getIfoSummaryContractPerIndexList();
            
            assertEquals(350000,i_ifoCustomer.getCurrentBizDateCashinAmount(),0);
            assertEquals(5,i_ifoCustomer.getNextBizDateOptionNetAmount(),0);
            assertEquals(3720, l_ifoSummaryContractPerProductContractPrice[0].getContractPrice(),0);
            assertEquals(1, l_ifoSummaryContractPerProduct[0].getBuyQuantity(),0);
            assertEquals(1, l_ifoSummaryContractPerIndex[0].getFuturesBuyContractQuantity(), 0);
        }
        catch(Exception l_ex)
        {
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    //先物OP保有建玉情報多条
    //トランザクションParams一覧多條
    public void testCreateIfoCustomerTransfer_case001()
    {
       String STR_METHOD_NAME = "testCreateIfoCustomerTransfer_case001";
       log.entering(STR_METHOD_NAME);
        try
        {
            //建玉表
            TestDBUtility.deleteAll(IfoContractRow.TYPE);
            IfoContractParams l_ifoContractParams = TestDBUtility.getIfoContractRow();
            l_ifoContractParams.setProductId(2605000000001L);
            l_ifoContractParams.setAccountId(111101111010L);
            l_ifoContractParams.setSubAccountId(11110111101001L);
            l_ifoContractParams.setOpenDate(WEB3DateUtility.getDate("20040716","yyyyMMdd"));
            l_ifoContractParams.setOriginalQuantity(5.0);
            TestDBUtility.insertWithDel(l_ifoContractParams);
            
            //トランザクション表
            TestDBUtility.deleteAll(IfoFinTransactionRow.TYPE);
            IfoFinTransactionParams l_ifoFinTransactionParams = TestDBUtility.getIfoFinTransactionRow();
            l_ifoFinTransactionParams.setProductId(2605000000001L);
            l_ifoFinTransactionParams.setAccountId(111101111010L);
            l_ifoFinTransactionParams.setSubAccountId(11110111101001L);
            l_ifoFinTransactionParams.setOrderId(1001);
            l_ifoFinTransactionParams.setOrderUnitId(1001);
            l_ifoFinTransactionParams.setContractId(1001);
            l_ifoFinTransactionParams.setDeliveryDate(WEB3DateUtility.getDate("20040720","yyyyMMdd"));
            l_ifoFinTransactionParams.setFinTransactionTimestamp(WEB3DateUtility.getDate("20040716","yyyyMMdd"));
            l_ifoFinTransactionParams.fin_transaction_categ = FinTransactionCateg.EQTYPE_IDX_FUTURES_CLOSE;
            l_ifoFinTransactionParams.setQuantity(2.0);
            TestDBUtility.insertWithDel(l_ifoFinTransactionParams);
            
            //輔助口座表
            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
            l_subAccountParams.setAccountId(111101111010L);
            l_subAccountParams.setSubAccountId(11110111101001L);
            TestDBUtility.insertWithDel(l_subAccountParams);
            
            //構造WEB3IfoDepositCalc類對象
            WEB3GentradeSubAccount l_subAccount =  new WEB3GentradeSubAccount(l_subAccountParams);
            WEB3IfoDepositCalcCondition l_ifoDepositCalcCondition = new WEB3IfoDepositCalcCondition();
            Date l_bizDate1 = WEB3DateUtility.getDate("20040715","yyyyMMdd");
            Date l_bizDate2 = WEB3DateUtility.getDate("20040716","yyyyMMdd");
            Date l_bizDate3 = WEB3DateUtility.getDate("20040720","yyyyMMdd");
            Date l_bizDate4 = WEB3DateUtility.getDate("20040721","yyyyMMdd");
            Date[] l_bizDates = {l_bizDate1, l_bizDate2, l_bizDate3, l_bizDate4};
            l_ifoDepositCalcCondition.setBizDates(l_bizDates);
            l_ifoDepositCalcCondition.addCalcConditionPerBranch("ifodeposit.profitloss.calc", "1");
            
            WEB3IfoDepositCalc l_ifo =
                new WEB3IfoDepositCalc();
            
            Object[] obj = {};
            
            Field field1 = WEB3IfoDepositCalc.class.getDeclaredField("subAccount");
            Field field2 = WEB3IfoDepositCalc.class.getDeclaredField("ifoDepositCalcCondition");
            field1.setAccessible(true);
            field2.setAccessible(true);
            field1.set(l_ifo, l_subAccount);
            field2.set(l_ifo, l_ifoDepositCalcCondition);
            
            Method method = WEB3IfoDepositCalc.class.getDeclaredMethod( ////被測方法@所在的類名
                "createIfoContractList",  //<------------- 被測方法@名
                new Class[]{});//< ------------ 方法@參數的類型
            
            method.setAccessible(true); //<-------------開關
            
            WEB3IfoContract[] l_ifoContract = (WEB3IfoContract[])method.invoke(l_ifo, obj);  //<---------return
            assertEquals(3720, l_ifoContract[0].getContractPrice(), 0);
            assertEquals(3, l_ifoContract[0].getQuantity(), 0);
        }
        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage());
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    //先物OP保有建玉情報一条
    //トランザクションParams一覧一條
    public void testCreateIfoCustomerTransfer_case002()
    {
        String STR_METHOD_NAME = "testCreateIfoCustomerTransfer_case002";
        log.entering(STR_METHOD_NAME);
        try
        {
            //建玉表
            TestDBUtility.deleteAll(IfoContractRow.TYPE);
            IfoContractParams l_ifoContractParams = TestDBUtility.getIfoContractRow();
            l_ifoContractParams.setProductId(2605000000001L);
            l_ifoContractParams.setAccountId(111101111010L);
            l_ifoContractParams.setSubAccountId(11110111101001L);
            l_ifoContractParams.setOpenDate(WEB3DateUtility.getDate("20040716","yyyyMMdd"));
            l_ifoContractParams.setOriginalQuantity(5.0);
            TestDBUtility.insertWithDel(l_ifoContractParams);
            
            IfoContractParams l_ifoContractParams1 = TestDBUtility.getIfoContractRow();
            l_ifoContractParams1.setContractId(1002L);
            l_ifoContractParams1.setContractPrice(3250);
            l_ifoContractParams1.setProductId(2605000000001L);
            l_ifoContractParams1.setAccountId(111101111010L);
            l_ifoContractParams1.setSubAccountId(11110111101001L);
            l_ifoContractParams1.setOriginalQuantity(7.0);
            l_ifoContractParams1.setOpenDate(WEB3DateUtility.getDate("20040716","yyyyMMdd"));
            TestDBUtility.insertWithDel(l_ifoContractParams1);
            
            //トランザクション表
            TestDBUtility.deleteAll(IfoFinTransactionRow.TYPE);
            IfoFinTransactionParams l_ifoFinTransactionParams = TestDBUtility.getIfoFinTransactionRow();
            l_ifoFinTransactionParams.setProductId(2605000000001L);
            l_ifoFinTransactionParams.setAccountId(111101111010L);
            l_ifoFinTransactionParams.setSubAccountId(11110111101001L);
            l_ifoFinTransactionParams.setOrderId(1001);
            l_ifoFinTransactionParams.setOrderUnitId(1001);
            l_ifoFinTransactionParams.setContractId(1001);
            l_ifoFinTransactionParams.setDeliveryDate(WEB3DateUtility.getDate("20040720","yyyyMMdd"));
            l_ifoFinTransactionParams.setFinTransactionTimestamp(WEB3DateUtility.getDate("20040716","yyyyMMdd"));
            l_ifoFinTransactionParams.fin_transaction_categ = FinTransactionCateg.EQTYPE_IDX_FUTURES_CLOSE;
            l_ifoFinTransactionParams.setQuantity(2.0);
            TestDBUtility.insertWithDel(l_ifoFinTransactionParams);
            
            IfoFinTransactionParams l_ifoFinTransactionParams1 = TestDBUtility.getIfoFinTransactionRow();
            l_ifoFinTransactionParams1.setFinTransactionId(2L);
            l_ifoFinTransactionParams1.setProductId(2605000000001L);
            l_ifoFinTransactionParams1.setAccountId(111101111010L);
            l_ifoFinTransactionParams1.setSubAccountId(11110111101001L);
            l_ifoFinTransactionParams1.setOrderId(1002L);
            l_ifoFinTransactionParams1.setOrderUnitId(1002);
            l_ifoFinTransactionParams1.setContractId(1002);
            l_ifoFinTransactionParams1.setDeliveryDate(WEB3DateUtility.getDate("20040720","yyyyMMdd"));
            l_ifoFinTransactionParams1.setFinTransactionTimestamp(WEB3DateUtility.getDate("20040716","yyyyMMdd"));
            l_ifoFinTransactionParams1.fin_transaction_categ = FinTransactionCateg.EQTYPE_IDX_OPTIONS_OPEN;
            TestDBUtility.insertWithDel(l_ifoFinTransactionParams1);
            
            //注文単位テーブル
            TestDBUtility.deleteAll(IfoOrderUnitRow.TYPE);
            IfoOrderUnitParams l_IfoOrderUnitParams = TestDBUtility.getIfoOrderUnitRow();
            l_IfoOrderUnitParams.setProductId(2605000000001L);
            l_IfoOrderUnitParams.setAccountId(111101111010L);
            l_IfoOrderUnitParams.setSubAccountId(11110111101001L);
            l_IfoOrderUnitParams.setBranchId(11110);
            l_IfoOrderUnitParams.setMarketId(1002);
            l_IfoOrderUnitParams.setBizDate("20040716");
            l_IfoOrderUnitParams.setDeliveryDate(WEB3DateUtility.getDate("20040720","yyyyMMdd"));
            l_IfoOrderUnitParams.setEstimatedPrice(1000);
            l_IfoOrderUnitParams.setOrderType(OrderTypeEnum.IDX_OPTIONS_BUY_TO_OPEN);
            l_IfoOrderUnitParams.setOrderCateg(OrderCategEnum.IDX_OPTIONS_OPEN);
            l_IfoOrderUnitParams.setFutureOptionDiv(WEB3FuturesOptionDivDef.OPTION);
            TestDBUtility.insertWithDel(l_IfoOrderUnitParams);
            
            IfoOrderUnitParams l_IfoOrderUnitParams1 = TestDBUtility.getIfoOrderUnitRow();
            l_IfoOrderUnitParams1.setOrderId(1002L);
            l_IfoOrderUnitParams1.setOrderUnitId(1002L);
            l_IfoOrderUnitParams1.setProductId(2605000000001L);
            l_IfoOrderUnitParams1.setAccountId(111101111010L);
            l_IfoOrderUnitParams1.setSubAccountId(11110111101001L);
            l_IfoOrderUnitParams1.setBranchId(11110);
            l_IfoOrderUnitParams1.setMarketId(1002);
            l_IfoOrderUnitParams1.setBizDate("20040716");
            l_IfoOrderUnitParams1.setDeliveryDate(WEB3DateUtility.getDate("20040720","yyyyMMdd"));
            l_IfoOrderUnitParams1.setEstimatedPrice(1000);
            l_IfoOrderUnitParams1.setOrderType(OrderTypeEnum.IDX_OPTIONS_BUY_TO_OPEN);
            l_IfoOrderUnitParams1.setOrderCateg(OrderCategEnum.IDX_OPTIONS_OPEN);
            l_IfoOrderUnitParams1.setOrderRequestNumber("9008");
            l_IfoOrderUnitParams1.setFirstOrderUnitId(2001L);
            l_IfoOrderUnitParams1.setFutureOptionDiv(WEB3FuturesOptionDivDef.OPTION);
            TestDBUtility.insertWithDel(l_IfoOrderUnitParams1);
            
            //輔助口座表
            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
            l_subAccountParams.setAccountId(111101111010L);
            l_subAccountParams.setSubAccountId(11110111101001L);
            TestDBUtility.insertWithDel(l_subAccountParams);
            
            //構造WEB3IfoDepositCalc類對象
            WEB3GentradeSubAccount l_subAccount =  new WEB3GentradeSubAccount(l_subAccountParams);
            WEB3IfoDepositCalcCondition l_ifoDepositCalcCondition = new WEB3IfoDepositCalcCondition();
            Date l_bizDate1 = WEB3DateUtility.getDate("20040715","yyyyMMdd");
            Date l_bizDate2 = WEB3DateUtility.getDate("20040716","yyyyMMdd");
            Date l_bizDate3 = WEB3DateUtility.getDate("20040720","yyyyMMdd");
            Date l_bizDate4 = WEB3DateUtility.getDate("20040721","yyyyMMdd");
            Date[] l_bizDates = {l_bizDate1, l_bizDate2, l_bizDate3, l_bizDate4};
            l_ifoDepositCalcCondition.setBizDates(l_bizDates);
            l_ifoDepositCalcCondition.addCalcConditionPerBranch("ifodeposit.profitloss.calc", "1");
            
            WEB3IfoDepositCalc l_ifo =
                new WEB3IfoDepositCalc();
            
            Object[] obj = {};
            
            Field field1 = WEB3IfoDepositCalc.class.getDeclaredField("subAccount");
            Field field2 = WEB3IfoDepositCalc.class.getDeclaredField("ifoDepositCalcCondition");
            field1.setAccessible(true);
            field2.setAccessible(true);
            field1.set(l_ifo, l_subAccount);
            field2.set(l_ifo, l_ifoDepositCalcCondition);
            
            Method method = WEB3IfoDepositCalc.class.getDeclaredMethod( ////被測方法@所在的類名
                "createIfoContractList",  //<------------- 被測方法@名
                new Class[]{});//< ------------ 方法@參數的類型
            
            method.setAccessible(true); //<-------------開關
            
            WEB3IfoContract[] l_ifoContract = (WEB3IfoContract[])method.invoke(l_ifo, obj);  //<---------return
            assertEquals(2, l_ifoContract.length);
            assertEquals(3250, l_ifoContract[0].getContractPrice(), 0);
            assertEquals(7, l_ifoContract[0].getQuantity(), 0);
            assertEquals(3720, l_ifoContract[1].getContractPrice(), 0);
            assertEquals(3, l_ifoContract[1].getQuantity(), 0);
        }
        catch(Exception l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            log.error(l_ex.getMessage());
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
//    先物OP保有建玉情報一覧一條
//    トランザクションParams一覧ない
    public void testCreateIfoCustomerTransfer_case003()
    {
        String STR_METHOD_NAME = "testCreateIfoCustomerTransfer_case003";
        log.entering(STR_METHOD_NAME);
        try
        {
            //建玉表
            TestDBUtility.deleteAll(IfoContractRow.TYPE);
            IfoContractParams l_ifoContractParams = TestDBUtility.getIfoContractRow();
            l_ifoContractParams.setProductId(2605000000001L);
            l_ifoContractParams.setAccountId(111101111012L);
            l_ifoContractParams.setSubAccountId(11110111101002L);
            l_ifoContractParams.setOpenDate(WEB3DateUtility.getDate("20040716","yyyyMMdd"));
            l_ifoContractParams.setOriginalQuantity(5.0);
            TestDBUtility.insertWithDel(l_ifoContractParams);
            
            IfoContractParams l_ifoContractParams1 = TestDBUtility.getIfoContractRow();
            l_ifoContractParams1.setContractId(1002L);
            l_ifoContractParams1.setContractPrice(3250);
            l_ifoContractParams1.setProductId(2605000000001L);
            l_ifoContractParams1.setAccountId(111101111012L);
            l_ifoContractParams1.setSubAccountId(11110111101002L);
            l_ifoContractParams1.setOriginalQuantity(7.0);
            l_ifoContractParams1.setOpenDate(WEB3DateUtility.getDate("20040716","yyyyMMdd"));
            TestDBUtility.insertWithDel(l_ifoContractParams1);
            
            //トランザクション表
            TestDBUtility.deleteAll(IfoFinTransactionRow.TYPE);
            IfoFinTransactionParams l_ifoFinTransactionParams = TestDBUtility.getIfoFinTransactionRow();
            l_ifoFinTransactionParams.setProductId(2605000000001L);
            l_ifoFinTransactionParams.setAccountId(111101111010L);
            l_ifoFinTransactionParams.setSubAccountId(11110111101001L);
            l_ifoFinTransactionParams.setOrderId(1001);
            l_ifoFinTransactionParams.setOrderUnitId(1001);
            l_ifoFinTransactionParams.setContractId(1001);
            l_ifoFinTransactionParams.setDeliveryDate(WEB3DateUtility.getDate("20040720","yyyyMMdd"));
            l_ifoFinTransactionParams.setFinTransactionTimestamp(WEB3DateUtility.getDate("20040716","yyyyMMdd"));
            l_ifoFinTransactionParams.fin_transaction_categ = FinTransactionCateg.EQTYPE_IDX_FUTURES_CLOSE;
            l_ifoFinTransactionParams.setQuantity(2.0);
            TestDBUtility.insertWithDel(l_ifoFinTransactionParams);
            
            IfoFinTransactionParams l_ifoFinTransactionParams1 = TestDBUtility.getIfoFinTransactionRow();
            l_ifoFinTransactionParams1.setFinTransactionId(2L);
            l_ifoFinTransactionParams1.setProductId(2605000000001L);
            l_ifoFinTransactionParams1.setAccountId(111101111010L);
            l_ifoFinTransactionParams1.setSubAccountId(11110111101001L);
            l_ifoFinTransactionParams1.setOrderId(1002L);
            l_ifoFinTransactionParams1.setOrderUnitId(1002);
            l_ifoFinTransactionParams1.setContractId(1002);
            l_ifoFinTransactionParams1.setDeliveryDate(WEB3DateUtility.getDate("20040720","yyyyMMdd"));
            l_ifoFinTransactionParams1.setFinTransactionTimestamp(WEB3DateUtility.getDate("20040716","yyyyMMdd"));
            l_ifoFinTransactionParams1.fin_transaction_categ = FinTransactionCateg.EQTYPE_IDX_OPTIONS_OPEN;
            TestDBUtility.insertWithDel(l_ifoFinTransactionParams1);
            
            //注文単位テーブル
            TestDBUtility.deleteAll(IfoOrderUnitRow.TYPE);
            IfoOrderUnitParams l_IfoOrderUnitParams = TestDBUtility.getIfoOrderUnitRow();
            l_IfoOrderUnitParams.setProductId(2605000000001L);
            l_IfoOrderUnitParams.setAccountId(111101111010L);
            l_IfoOrderUnitParams.setSubAccountId(11110111101001L);
            l_IfoOrderUnitParams.setBranchId(11110);
            l_IfoOrderUnitParams.setMarketId(1002);
            l_IfoOrderUnitParams.setBizDate("20040716");
            l_IfoOrderUnitParams.setDeliveryDate(WEB3DateUtility.getDate("20040720","yyyyMMdd"));
            l_IfoOrderUnitParams.setEstimatedPrice(1000);
            l_IfoOrderUnitParams.setOrderType(OrderTypeEnum.IDX_OPTIONS_BUY_TO_OPEN);
            l_IfoOrderUnitParams.setOrderCateg(OrderCategEnum.IDX_OPTIONS_OPEN);
            l_IfoOrderUnitParams.setFutureOptionDiv(WEB3FuturesOptionDivDef.OPTION);
            TestDBUtility.insertWithDel(l_IfoOrderUnitParams);
            
            IfoOrderUnitParams l_IfoOrderUnitParams1 = TestDBUtility.getIfoOrderUnitRow();
            l_IfoOrderUnitParams1.setOrderId(1002L);
            l_IfoOrderUnitParams1.setOrderUnitId(1002L);
            l_IfoOrderUnitParams1.setProductId(2605000000001L);
            l_IfoOrderUnitParams1.setAccountId(111101111010L);
            l_IfoOrderUnitParams1.setSubAccountId(11110111101001L);
            l_IfoOrderUnitParams1.setBranchId(11110);
            l_IfoOrderUnitParams1.setMarketId(1002);
            l_IfoOrderUnitParams1.setBizDate("20040716");
            l_IfoOrderUnitParams1.setDeliveryDate(WEB3DateUtility.getDate("20040720","yyyyMMdd"));
            l_IfoOrderUnitParams1.setEstimatedPrice(1000);
            l_IfoOrderUnitParams1.setOrderType(OrderTypeEnum.IDX_OPTIONS_BUY_TO_OPEN);
            l_IfoOrderUnitParams1.setOrderCateg(OrderCategEnum.IDX_OPTIONS_OPEN);
            l_IfoOrderUnitParams1.setOrderRequestNumber("9008");
            l_IfoOrderUnitParams1.setFirstOrderUnitId(2001L);
            l_IfoOrderUnitParams1.setFutureOptionDiv(WEB3FuturesOptionDivDef.OPTION);
            TestDBUtility.insertWithDel(l_IfoOrderUnitParams1);
            
            //輔助口座表
            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
            l_subAccountParams.setAccountId(111101111010L);
            l_subAccountParams.setSubAccountId(11110111101001L);
            TestDBUtility.insertWithDel(l_subAccountParams);
            
            //構造WEB3IfoDepositCalc類對象
            WEB3GentradeSubAccount l_subAccount =  new WEB3GentradeSubAccount(l_subAccountParams);
            WEB3IfoDepositCalcCondition l_ifoDepositCalcCondition = new WEB3IfoDepositCalcCondition();
            Date l_bizDate1 = WEB3DateUtility.getDate("20040715","yyyyMMdd");
            Date l_bizDate2 = WEB3DateUtility.getDate("20040716","yyyyMMdd");
            Date l_bizDate3 = WEB3DateUtility.getDate("20040720","yyyyMMdd");
            Date l_bizDate4 = WEB3DateUtility.getDate("20040721","yyyyMMdd");
            Date[] l_bizDates = {l_bizDate1, l_bizDate2, l_bizDate3, l_bizDate4};
            l_ifoDepositCalcCondition.setBizDates(l_bizDates);
            l_ifoDepositCalcCondition.addCalcConditionPerBranch("ifodeposit.profitloss.calc", "1");
            
            WEB3IfoDepositCalc l_ifo =
                new WEB3IfoDepositCalc();
            
            Object[] obj = {};
            
            Field field1 = WEB3IfoDepositCalc.class.getDeclaredField("subAccount");
            Field field2 = WEB3IfoDepositCalc.class.getDeclaredField("ifoDepositCalcCondition");
            field1.setAccessible(true);
            field2.setAccessible(true);
            field1.set(l_ifo, l_subAccount);
            field2.set(l_ifo, l_ifoDepositCalcCondition);
            
            Method method = WEB3IfoDepositCalc.class.getDeclaredMethod( ////被測方法@所在的類名
                "createIfoContractList",  //<------------- 被測方法@名
                new Class[]{});//< ------------ 方法@參數的類型
            
            method.setAccessible(true); //<-------------開關
            
            WEB3IfoContract[] l_ifoContract = (WEB3IfoContract[])method.invoke(l_ifo, obj);  //<---------return
            assertNull(l_ifoContract);

        }
        catch(Exception l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            log.error(l_ex.getMessage());
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    
    //先物OP保有建玉情報0条
    //トランザクションParams一覧一條
    public void testCreateIfoCustomerTransfer_case004()
    {
       String STR_METHOD_NAME = "testCreateIfoCustomerTransfer_case004";
       log.entering(STR_METHOD_NAME);
        try
        {
            //建玉表
            TestDBUtility.deleteAll(IfoContractRow.TYPE);
            IfoContractParams l_ifoContractParams = TestDBUtility.getIfoContractRow();
            l_ifoContractParams.setProductId(2605000000001L);
            l_ifoContractParams.setAccountId(111101111010L);
            l_ifoContractParams.setSubAccountId(11110111101001L);
            l_ifoContractParams.setOpenDate(WEB3DateUtility.getDate("20040716","yyyyMMdd"));
            l_ifoContractParams.setOriginalQuantity(5.0);
            TestDBUtility.insertWithDel(l_ifoContractParams);
            
            //トランザクション表
            TestDBUtility.deleteAll(IfoFinTransactionRow.TYPE);
            IfoFinTransactionParams l_ifoFinTransactionParams = TestDBUtility.getIfoFinTransactionRow();
            l_ifoFinTransactionParams.setProductId(2605000000001L);
            l_ifoFinTransactionParams.setOrderId(1001);
            l_ifoFinTransactionParams.setOrderUnitId(1001);
            l_ifoFinTransactionParams.setContractId(1001);
            l_ifoFinTransactionParams.setDeliveryDate(WEB3DateUtility.getDate("20040720","yyyyMMdd"));
            l_ifoFinTransactionParams.setFinTransactionTimestamp(WEB3DateUtility.getDate("20040716","yyyyMMdd"));
            l_ifoFinTransactionParams.fin_transaction_categ = FinTransactionCateg.EQTYPE_IDX_FUTURES_CLOSE;
            l_ifoFinTransactionParams.setQuantity(2.0);
            TestDBUtility.insertWithDel(l_ifoFinTransactionParams);
            
            //輔助口座表
            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
            l_subAccountParams.setAccountId(111101111010L);
            l_subAccountParams.setSubAccountId(11110111101001L);
            TestDBUtility.insertWithDel(l_subAccountParams);
            
            //構造WEB3IfoDepositCalc類對象
            WEB3GentradeSubAccount l_subAccount =  new WEB3GentradeSubAccount(l_subAccountParams);
            WEB3IfoDepositCalcCondition l_ifoDepositCalcCondition = new WEB3IfoDepositCalcCondition();
            Date l_bizDate1 = WEB3DateUtility.getDate("20040715","yyyyMMdd");
            Date l_bizDate2 = WEB3DateUtility.getDate("20040716","yyyyMMdd");
            Date l_bizDate3 = WEB3DateUtility.getDate("20040720","yyyyMMdd");
            Date l_bizDate4 = WEB3DateUtility.getDate("20040721","yyyyMMdd");
            Date[] l_bizDates = {l_bizDate1, l_bizDate2, l_bizDate3, l_bizDate4};
            l_ifoDepositCalcCondition.setBizDates(l_bizDates);
            l_ifoDepositCalcCondition.addCalcConditionPerBranch("ifodeposit.profitloss.calc", "1");
            
            WEB3IfoDepositCalc l_ifo =
                new WEB3IfoDepositCalc();
            
            Object[] obj = {};
            
            Field field1 = WEB3IfoDepositCalc.class.getDeclaredField("subAccount");
            Field field2 = WEB3IfoDepositCalc.class.getDeclaredField("ifoDepositCalcCondition");
            field1.setAccessible(true);
            field2.setAccessible(true);
            field1.set(l_ifo, l_subAccount);
            field2.set(l_ifo, l_ifoDepositCalcCondition);
            
            Method method = WEB3IfoDepositCalc.class.getDeclaredMethod( ////被測方法@所在的類名
                "createIfoContractList",  //<------------- 被測方法@名
                new Class[]{});//< ------------ 方法@參數的類型
            
            method.setAccessible(true); //<-------------開關
            
            WEB3IfoContract[] l_ifoContract = (WEB3IfoContract[])method.invoke(l_ifo, obj);  //<---------return
            
            assertEquals(1,l_ifoContract.length);
            assertEquals(3720, l_ifoContract[0].getContractPrice(), 0);
            assertEquals(5, l_ifoContract[0].getQuantity(), 0);
            
        }
        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage());
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    //当日新規建注文単位Params一覧一條を取得する
    public void testcreateIfoTodayOpenOrderListC001()
    {
        String STR_METHOD_NAME = "testcreateIfoTodayOpenOrderListC001";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            TestDBUtility.deleteAll(CalendarRow.TYPE);
            //注文単位テーブル
            TestDBUtility.deleteAll(IfoOrderUnitRow.TYPE);
            IfoOrderUnitParams l_IfoOrderUnitParams = TestDBUtility.getIfoOrderUnitRow();
            l_IfoOrderUnitParams.setProductId(2605000000001L);
            l_IfoOrderUnitParams.setAccountId(111101111010L);
            l_IfoOrderUnitParams.setSubAccountId(11110111101001L);
            l_IfoOrderUnitParams.setBranchId(11110);
            l_IfoOrderUnitParams.setMarketId(1002);
            l_IfoOrderUnitParams.setBizDate("20040716");
            l_IfoOrderUnitParams.setDeliveryDate(WEB3DateUtility.getDate("20040720","yyyyMMdd"));
            l_IfoOrderUnitParams.setEstimatedPrice(1000);
            l_IfoOrderUnitParams.setOrderType(OrderTypeEnum.IDX_OPTIONS_BUY_TO_OPEN);
            l_IfoOrderUnitParams.setOrderCateg(OrderCategEnum.IDX_OPTIONS_OPEN);
            l_IfoOrderUnitParams.setFutureOptionDiv(WEB3FuturesOptionDivDef.OPTION);
            TestDBUtility.insertWithDel(l_IfoOrderUnitParams);
            
            //注文テーブル
            TestDBUtility.deleteAll(IfoOrderRow.TYPE);
            IfoOrderParams l_IfoOrderParams = TestDBUtility.getIfoOrderRow();
            l_IfoOrderParams.setAccountId(111101111010L);
            l_IfoOrderParams.setSubAccountId(11110111101001L);
            TestDBUtility.insertWithDel(l_IfoOrderParams);
            
            //輔助口座表
            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
            l_subAccountParams.setAccountId(111101111010L);
            l_subAccountParams.setSubAccountId(11110111101001L);
            TestDBUtility.insertWithDel(l_subAccountParams);
            WEB3GentradeSubAccount l_subAccount =  new WEB3GentradeSubAccount(l_subAccountParams);
            IfoOrderManagerPersistenceEventInterceptor l_interceptor =
                new DefaultIfoOrderManagerPersistenceEventInterceptor();
            Trader trader = null;
            boolean isBuyToOpen = true;
            String underlyingProductCode = "0005";
            IfoDerivativeTypeEnum derivativeType = IfoDerivativeTypeEnum.FUTURES;
            String monthOfDelivery = "200503";
            double strikePrice = 0;
            String marketCode = "SP";
            double quantity = 1000;
            IfoOrderExecutionConditionType execType = null; 
            Date orderExpDate = new Date();
            TaxTypeEnum taxType = null;
            IfoOpenContractOrderSpec l_orderspec = IfoOpenContractOrderSpec.createMarketOrderSpec(trader,
                    isBuyToOpen,underlyingProductCode,
                    derivativeType,monthOfDelivery,
                    strikePrice,marketCode,quantity,
                    execType,orderExpDate,taxType);
            OrderTypeEnum l_orderTypeEnum = OrderTypeEnum.IDX_FUTURES_BUY_TO_OPEN;
            
            WEB3IfoNewOrderSpec l_ifoNewOrderSpec = 
                WEB3IfoNewOrderSpec.createWEB3IfoNewOrderSpec(
                l_subAccount,
                l_interceptor,
                l_orderspec,
                l_orderTypeEnum);
            
            WEB3IfoDepositCalcCondition l_ifoDepositCalcCondition = new WEB3IfoDepositCalcCondition();
            Date l_bizDate1 = WEB3DateUtility.getDate("20040715","yyyyMMdd");
            Date l_bizDate2 = WEB3DateUtility.getDate("20040716","yyyyMMdd");
            Date l_bizDate3 = WEB3DateUtility.getDate("20040720","yyyyMMdd");
            Date l_bizDate4 = WEB3DateUtility.getDate("20040721","yyyyMMdd");
            Date[] l_bizDates = {l_bizDate1, l_bizDate2, l_bizDate3, l_bizDate4};
            l_ifoDepositCalcCondition.setBizDates(l_bizDates);
            l_ifoDepositCalcCondition.addCalcConditionPerBranch("ifodeposit.profitloss.calc", "1");
            
            WEB3IfoDepositCalc l_ifo =
                new WEB3IfoDepositCalc();
            
            Object[] obj = {l_ifoNewOrderSpec};
            
            Field field1 = WEB3IfoDepositCalc.class.getDeclaredField("subAccount");
            Field field2 = WEB3IfoDepositCalc.class.getDeclaredField("ifoDepositCalcCondition");
            field1.setAccessible(true);
            field2.setAccessible(true);
            field1.set(l_ifo, l_subAccount);
            field2.set(l_ifo, l_ifoDepositCalcCondition);
            
            Method method = WEB3IfoDepositCalc.class.getDeclaredMethod( ////被測方法@所在的類名
                "createIfoTodayOpenOrderList",  //<------------- 被測方法@名
                new Class[]{WEB3IfoNewOrderSpec.class});//< ------------ 方法@參數的類型
            
            method.setAccessible(true); //<-------------開關
            WEB3IfoTodayOpenOrder[] l_ifoTodayOpenOrde =
                (WEB3IfoTodayOpenOrder[])method.invoke(l_ifo, obj);  //<---------return
            
            assertEquals(2, l_ifoTodayOpenOrde.length);
            assertEquals(1000, l_ifoTodayOpenOrde[0].getQuantity(), 0);
            assertEquals(1001,l_ifoTodayOpenOrde[1].getOrderUnitId());
            assertEquals(100, l_ifoTodayOpenOrde[1].getQuantity(), 0);
        }
        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage());
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    //当日新規建注文単位Params一覧多條を取得する
    public void testcreateIfoTodayOpenOrderListC002()
    {
        String STR_METHOD_NAME = "testcreateIfoTodayOpenOrderListC002";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            TestDBUtility.deleteAll(CalendarRow.TYPE);
            //注文単位テーブル
            TestDBUtility.deleteAll(IfoOrderUnitRow.TYPE);
            IfoOrderUnitParams l_IfoOrderUnitParams = TestDBUtility.getIfoOrderUnitRow();
            l_IfoOrderUnitParams.setProductId(2605000000001L);
            l_IfoOrderUnitParams.setAccountId(111101111010L);
            l_IfoOrderUnitParams.setSubAccountId(11110111101001L);
            l_IfoOrderUnitParams.setBranchId(11110);
            l_IfoOrderUnitParams.setMarketId(1002);
            l_IfoOrderUnitParams.setBizDate("20040716");
            l_IfoOrderUnitParams.setDeliveryDate(WEB3DateUtility.getDate("20040720","yyyyMMdd"));
            l_IfoOrderUnitParams.setEstimatedPrice(1000);
            l_IfoOrderUnitParams.setOrderType(OrderTypeEnum.IDX_OPTIONS_BUY_TO_OPEN);
            l_IfoOrderUnitParams.setOrderCateg(OrderCategEnum.IDX_OPTIONS_OPEN);
            l_IfoOrderUnitParams.setFutureOptionDiv(WEB3FuturesOptionDivDef.OPTION);
            TestDBUtility.insertWithDel(l_IfoOrderUnitParams);
            
            IfoOrderUnitParams l_IfoOrderUnitParams1 = TestDBUtility.getIfoOrderUnitRow();
            l_IfoOrderUnitParams1.setOrderId(1002L);
            l_IfoOrderUnitParams1.setOrderUnitId(1002L);
            l_IfoOrderUnitParams1.setProductId(2605000000001L);
            l_IfoOrderUnitParams1.setAccountId(111101111010L);
            l_IfoOrderUnitParams1.setSubAccountId(11110111101001L);
            l_IfoOrderUnitParams1.setBranchId(11110);
            l_IfoOrderUnitParams1.setMarketId(1002);
            l_IfoOrderUnitParams1.setBizDate("20040716");
            l_IfoOrderUnitParams1.setDeliveryDate(WEB3DateUtility.getDate("20040720","yyyyMMdd"));
            l_IfoOrderUnitParams1.setEstimatedPrice(1000);
            l_IfoOrderUnitParams1.setOrderType(OrderTypeEnum.IDX_OPTIONS_BUY_TO_OPEN);
            l_IfoOrderUnitParams1.setOrderCateg(OrderCategEnum.IDX_OPTIONS_OPEN);
            l_IfoOrderUnitParams1.setOrderRequestNumber("9008");
            l_IfoOrderUnitParams1.setFirstOrderUnitId(2001L);
            l_IfoOrderUnitParams1.setFutureOptionDiv(WEB3FuturesOptionDivDef.OPTION);
            l_IfoOrderUnitParams1.setQuantity(2000);
            TestDBUtility.insertWithDel(l_IfoOrderUnitParams1);
            
            //注文テーブル
            TestDBUtility.deleteAll(IfoOrderRow.TYPE);
            IfoOrderParams l_IfoOrderParams = TestDBUtility.getIfoOrderRow();
            l_IfoOrderParams.setAccountId(111101111010L);
            l_IfoOrderParams.setSubAccountId(11110111101001L);
            TestDBUtility.insertWithDel(l_IfoOrderParams);
            
            IfoOrderParams l_IfoOrderParams1 = TestDBUtility.getIfoOrderRow();
            l_IfoOrderParams1.setOrderId(1002L);
            l_IfoOrderParams1.setAccountId(111101111010L);
            l_IfoOrderParams1.setSubAccountId(11110111101001L);
            TestDBUtility.insertWithDel(l_IfoOrderParams1);
            
            //輔助口座表
            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
            l_subAccountParams.setAccountId(111101111010L);
            l_subAccountParams.setSubAccountId(11110111101001L);
            TestDBUtility.insertWithDel(l_subAccountParams);
            WEB3GentradeSubAccount l_subAccount =  new WEB3GentradeSubAccount(l_subAccountParams);
            IfoOrderManagerPersistenceEventInterceptor l_interceptor =
                new DefaultIfoOrderManagerPersistenceEventInterceptor();
            Trader trader = null;
            boolean isBuyToOpen = true;
            String underlyingProductCode = "0005";
            IfoDerivativeTypeEnum derivativeType = IfoDerivativeTypeEnum.FUTURES;
            String monthOfDelivery = "200503";
            double strikePrice = 0;
            String marketCode = "SP";
            double quantity = 1000;
            IfoOrderExecutionConditionType execType = null; 
            Date orderExpDate = new Date();
            TaxTypeEnum taxType = null;
            IfoOpenContractOrderSpec l_orderspec = IfoOpenContractOrderSpec.createMarketOrderSpec(trader,
                    isBuyToOpen,underlyingProductCode,
                    derivativeType,monthOfDelivery,
                    strikePrice,marketCode,quantity,
                    execType,orderExpDate,taxType);
            OrderTypeEnum l_orderTypeEnum = OrderTypeEnum.IDX_FUTURES_BUY_TO_OPEN;
            
            WEB3IfoNewOrderSpec l_ifoNewOrderSpec = 
                WEB3IfoNewOrderSpec.createWEB3IfoNewOrderSpec(
                l_subAccount,
                l_interceptor,
                l_orderspec,
                l_orderTypeEnum);
            
            WEB3IfoDepositCalcCondition l_ifoDepositCalcCondition = new WEB3IfoDepositCalcCondition();
            Date l_bizDate1 = WEB3DateUtility.getDate("20040715","yyyyMMdd");
            Date l_bizDate2 = WEB3DateUtility.getDate("20040716","yyyyMMdd");
            Date l_bizDate3 = WEB3DateUtility.getDate("20040720","yyyyMMdd");
            Date l_bizDate4 = WEB3DateUtility.getDate("20040721","yyyyMMdd");
            Date[] l_bizDates = {l_bizDate1, l_bizDate2, l_bizDate3, l_bizDate4};
            l_ifoDepositCalcCondition.setBizDates(l_bizDates);
            l_ifoDepositCalcCondition.addCalcConditionPerBranch("ifodeposit.profitloss.calc", "1");
            
            WEB3IfoDepositCalc l_ifo =
                new WEB3IfoDepositCalc();
            
            Object[] obj = {l_ifoNewOrderSpec};
            
            Field field1 = WEB3IfoDepositCalc.class.getDeclaredField("subAccount");
            Field field2 = WEB3IfoDepositCalc.class.getDeclaredField("ifoDepositCalcCondition");
            field1.setAccessible(true);
            field2.setAccessible(true);
            field1.set(l_ifo, l_subAccount);
            field2.set(l_ifo, l_ifoDepositCalcCondition);
            
            Method method = WEB3IfoDepositCalc.class.getDeclaredMethod( ////被測方法@所在的類名
                "createIfoTodayOpenOrderList",  //<------------- 被測方法@名
                new Class[]{WEB3IfoNewOrderSpec.class});//< ------------ 方法@參數的類型
            
            method.setAccessible(true); //<-------------開關
            WEB3IfoTodayOpenOrder[] l_ifoTodayOpenOrde =
                (WEB3IfoTodayOpenOrder[])method.invoke(l_ifo, obj);  //<---------return
            
            assertEquals(3, l_ifoTodayOpenOrde.length);
            assertEquals(1002, l_ifoTodayOpenOrde[0].getOrderUnitId());
            assertEquals(2000, l_ifoTodayOpenOrde[0].getQuantity(), 0);
            assertEquals(-1, l_ifoTodayOpenOrde[1].getOrderUnitId());
            assertEquals(1000, l_ifoTodayOpenOrde[1].getQuantity(), 0);
            assertEquals(100, l_ifoTodayOpenOrde[2].getQuantity(), 0);
            assertEquals(1001, l_ifoTodayOpenOrde[2].getOrderUnitId());
        }
        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage());
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    //当日新規建注文単位Params一覧多條を取得しない
    public void testcreateIfoTodayOpenOrderListC003()
    {
        String STR_METHOD_NAME = "testcreateIfoTodayOpenOrderListC003";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            TestDBUtility.deleteAll(CalendarRow.TYPE);
            //注文単位テーブル
            TestDBUtility.deleteAll(IfoOrderUnitRow.TYPE);
            //注文テーブル
           TestDBUtility.deleteAll(IfoOrderRow.TYPE);
            
            //輔助口座表
            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
            l_subAccountParams.setAccountId(111101111010L);
            l_subAccountParams.setSubAccountId(11110111101001L);
            TestDBUtility.insertWithDel(l_subAccountParams);
            WEB3GentradeSubAccount l_subAccount =  new WEB3GentradeSubAccount(l_subAccountParams);
            IfoOrderManagerPersistenceEventInterceptor l_interceptor =
                new DefaultIfoOrderManagerPersistenceEventInterceptor();
            Trader trader = null;
            boolean isBuyToOpen = true;
            String underlyingProductCode = "0005";
            IfoDerivativeTypeEnum derivativeType = IfoDerivativeTypeEnum.FUTURES;
            String monthOfDelivery = "200503";
            double strikePrice = 0;
            String marketCode = "SP";
            double quantity = 1000;
            IfoOrderExecutionConditionType execType = null; 
            Date orderExpDate = new Date();
            TaxTypeEnum taxType = null;
            IfoOpenContractOrderSpec l_orderspec = IfoOpenContractOrderSpec.createMarketOrderSpec(trader,
                    isBuyToOpen,underlyingProductCode,
                    derivativeType,monthOfDelivery,
                    strikePrice,marketCode,quantity,
                    execType,orderExpDate,taxType);
            OrderTypeEnum l_orderTypeEnum = OrderTypeEnum.IDX_FUTURES_BUY_TO_OPEN;
            
            WEB3IfoNewOrderSpec l_ifoNewOrderSpec = 
                WEB3IfoNewOrderSpec.createWEB3IfoNewOrderSpec(
                l_subAccount,
                l_interceptor,
                l_orderspec,
                l_orderTypeEnum);
            
            WEB3IfoDepositCalcCondition l_ifoDepositCalcCondition = new WEB3IfoDepositCalcCondition();
            Date l_bizDate1 = WEB3DateUtility.getDate("20040715","yyyyMMdd");
            Date l_bizDate2 = WEB3DateUtility.getDate("20040716","yyyyMMdd");
            Date l_bizDate3 = WEB3DateUtility.getDate("20040720","yyyyMMdd");
            Date l_bizDate4 = WEB3DateUtility.getDate("20040721","yyyyMMdd");
            Date[] l_bizDates = {l_bizDate1, l_bizDate2, l_bizDate3, l_bizDate4};
            l_ifoDepositCalcCondition.setBizDates(l_bizDates);
            l_ifoDepositCalcCondition.addCalcConditionPerBranch("ifodeposit.profitloss.calc", "1");
            
            WEB3IfoDepositCalc l_ifo =
                new WEB3IfoDepositCalc();
            
            Object[] obj = {l_ifoNewOrderSpec};
            
            Field field1 = WEB3IfoDepositCalc.class.getDeclaredField("subAccount");
            Field field2 = WEB3IfoDepositCalc.class.getDeclaredField("ifoDepositCalcCondition");
            field1.setAccessible(true);
            field2.setAccessible(true);
            field1.set(l_ifo, l_subAccount);
            field2.set(l_ifo, l_ifoDepositCalcCondition);
            
            Method method = WEB3IfoDepositCalc.class.getDeclaredMethod( ////被測方法@所在的類名
                "createIfoTodayOpenOrderList",  //<------------- 被測方法@名
                new Class[]{WEB3IfoNewOrderSpec.class});//< ------------ 方法@參數的類型
            
            method.setAccessible(true); //<-------------開關
            WEB3IfoTodayOpenOrder[] l_ifoTodayOpenOrde =
                (WEB3IfoTodayOpenOrder[])method.invoke(l_ifo, obj);  //<---------return
            
            assertEquals(1, l_ifoTodayOpenOrde.length);
            assertEquals(1000, l_ifoTodayOpenOrde[0].getQuantity(), 0);
            assertEquals(-1,l_ifoTodayOpenOrde[0].getOrderUnitId());
        }
        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage());
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    //引数.先物OP保有建玉情報一覧 == null、かつ、 
    //引数.先物OP当日新規建注文情報一覧 == nullの場合、nullを返却して終了する
    public void testCreateTradedProductParamsListC001()
    {
        String STE_METHOD_NAME = "testCreateTradedProductParamsListC001";
        log.entering(STE_METHOD_NAME);
        try
        {
            WEB3IfoContract[] l_ifoContractList = null;
            WEB3IfoTodayOpenOrder[] l_ifoTodayOpenOrderList = null;
            
            WEB3IfoDepositCalc l_ifo =
                new WEB3IfoDepositCalc();
            
            Object[] obj = {l_ifoContractList, l_ifoTodayOpenOrderList};
            
            Method method = WEB3IfoDepositCalc.class.getDeclaredMethod( ////被測方法@所在的類名
                "createTradedProductParamsList",  //<------------- 被測方法@名
                new Class[]{WEB3IfoContract[].class, WEB3IfoTodayOpenOrder[].class});//< ------------ 方法@參數的類型
            
            method.setAccessible(true); //<-------------開關
            IfoTradedProductParams[] l_ifoTradedProductParams =
                (IfoTradedProductParams[])method.invoke(l_ifo, obj);  //<---------return
            assertNull(l_ifoTradedProductParams);
        }
        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage());
            fail();
        }
        log.exiting(STE_METHOD_NAME);
    }
    
//    先物OP保有建玉情報一覧一條、かつ、 
//    先物OP当日新規建注文情報一覧 == null、
//    KeyがHashMapに追加未済みの時
//    正確的先物OP取引銘柄Paramsを返却
    public void testCreateTradedProductParamsListC002()
    {
        String STR_METHOD_NAME = "testCreateTradedProductParamsListC002";
        log.entering(STR_METHOD_NAME);
        try
        {
            //銘柄表
            TestDBUtility.deleteAll(ProductRow.TYPE);
            ProductParams l_ProductParams = TestDBUtility.getProductRow();
            l_ProductParams.setProductId(2605000000001L);
            TestDBUtility.insertWithDel(l_ProductParams);

            //取引銘柄銘柄表
            TestDBUtility.deleteAll(TradedProductRow.TYPE);
            TradedProductParams l_TradedProductPrams = TestDBUtility.getTradedProductRow();
            l_TradedProductPrams.setProductId(2605000000001L);
            l_TradedProductPrams.setMarketId(1002);
            TestDBUtility.insertWithDel(l_TradedProductPrams);
            
            //IFO銘柄表
            TestDBUtility.deleteAll(IfoProductRow.TYPE);
            IfoProductParams l_ifoProductParams = TestDBUtility.getIfoProductRow();
            l_ifoProductParams.setProductId(2605000000001L);
            TestDBUtility.insertWithDel(l_ifoProductParams);

            //IFO取引銘柄銘柄表
            TestDBUtility.deleteAll(IfoTradedProductRow.TYPE);
            IfoTradedProductParams l_ifoTradedProductParams = TestDBUtility.getIfoTradedProductRow();
            l_ifoTradedProductParams.setProductId(2605000000001L);
            l_ifoTradedProductParams.setMarketId(1002);
            TestDBUtility.insertWithDel(l_ifoTradedProductParams);
            
            //建玉
            TestDBUtility.deleteAll(IfoContractRow.TYPE);
            IfoContractParams l_ifoContractParams = TestDBUtility.getIfoContractRow();
            l_ifoContractParams.setProductId(2605000000001L);
            l_ifoContractParams.setAccountId(111101111012L);
            l_ifoContractParams.setSubAccountId(11110111101002L);
            l_ifoContractParams.setOpenDate(WEB3DateUtility.getDate("20040716","yyyyMMdd"));
            l_ifoContractParams.setOriginalQuantity(5.0);
            TestDBUtility.insertWithDel(l_ifoContractParams);
            
            WEB3IfoTodayOpenOrder[] l_ifoTodayOpenOrderList = null;
            WEB3IfoContract[] l_ifoContractList = new WEB3IfoContract[1];
            Date l_datBizDate = WEB3DateUtility.getDate("20040716","yyyyMMdd");
            l_ifoContractList[0] =WEB3IfoContract.getIfoContract(l_ifoContractParams, l_datBizDate);
            
            WEB3IfoDepositCalc l_ifo = new WEB3IfoDepositCalc();
            
            Object[] obj = {l_ifoContractList, l_ifoTodayOpenOrderList};
            
            Method method = WEB3IfoDepositCalc.class.getDeclaredMethod( ////被測方法@所在的類名
                "createTradedProductParamsList",  //<------------- 被測方法@名
                new Class[]{WEB3IfoContract[].class, WEB3IfoTodayOpenOrder[].class});//< ------------ 方法@參數的類型
            
            method.setAccessible(true); //<-------------開關
            IfoTradedProductParams[] l_ifoTradedProductParamsRetrun =
                (IfoTradedProductParams[])method.invoke(l_ifo, obj);  //<---------return

            assertEquals(1,l_ifoTradedProductParamsRetrun.length);
            assertEquals(2605000000001L,l_ifoTradedProductParamsRetrun[0].getProductId());
            assertEquals(1002L,l_ifoTradedProductParamsRetrun[0].getMarketId());
            assertEquals(1006160060005L,l_ifoTradedProductParamsRetrun[0].getTradedProductId());
        }
        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage());
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }

//    引数.先物OP保有建玉情報一覧 == null、かつ、 
//    引数.先物OP当日新規建注文情報一覧一條
//    KeyがHashMapに追加未済みの時
//    正確的先物OP取引銘柄Paramsを返却
    public void testCreateTradedProductParamsListC003()
    {
        String STR_METHOD_NAME = "testCreateTradedProductParamsListC003";
        log.entering(STR_METHOD_NAME);
        try
        {
            //銘柄表
            TestDBUtility.deleteAll(ProductRow.TYPE);
            ProductParams l_ProductParams = TestDBUtility.getProductRow();
            l_ProductParams.setProductId(2605000000001L);
            TestDBUtility.insertWithDel(l_ProductParams);

            //取引銘柄銘柄表
            TestDBUtility.deleteAll(TradedProductRow.TYPE);
            TradedProductParams l_TradedProductPrams = TestDBUtility.getTradedProductRow();
            l_TradedProductPrams.setProductId(2605000000001L);
            l_TradedProductPrams.setMarketId(1002);
            TestDBUtility.insertWithDel(l_TradedProductPrams);
            
            //IFO銘柄表
            TestDBUtility.deleteAll(IfoProductRow.TYPE);
            IfoProductParams l_ifoProductParams = TestDBUtility.getIfoProductRow();
            l_ifoProductParams.setProductId(2605000000001L);
            TestDBUtility.insertWithDel(l_ifoProductParams);

            //IFO取引銘柄銘柄表
            TestDBUtility.deleteAll(IfoTradedProductRow.TYPE);
            IfoTradedProductParams l_ifoTradedProductParams = TestDBUtility.getIfoTradedProductRow();
            l_ifoTradedProductParams.setProductId(2605000000001L);
            l_ifoTradedProductParams.setMarketId(1002);
            TestDBUtility.insertWithDel(l_ifoTradedProductParams);
            
            //建玉
            TestDBUtility.deleteAll(IfoContractRow.TYPE);
            IfoContractParams l_ifoContractParams = TestDBUtility.getIfoContractRow();
            l_ifoContractParams.setProductId(2605000000001L);
            l_ifoContractParams.setAccountId(111101111012L);
            l_ifoContractParams.setSubAccountId(11110111101002L);
            l_ifoContractParams.setOpenDate(WEB3DateUtility.getDate("20040716","yyyyMMdd"));
            l_ifoContractParams.setOriginalQuantity(5.0);
            TestDBUtility.insertWithDel(l_ifoContractParams);
            
            //注文単位テーブル
            TestDBUtility.deleteAll(IfoOrderUnitRow.TYPE);
            IfoOrderUnitParams l_IfoOrderUnitParams = TestDBUtility.getIfoOrderUnitRow();
            l_IfoOrderUnitParams.setProductId(2605000000001L);
            l_IfoOrderUnitParams.setAccountId(111101111010L);
            l_IfoOrderUnitParams.setSubAccountId(11110111101001L);
            l_IfoOrderUnitParams.setBranchId(11110);
            l_IfoOrderUnitParams.setMarketId(1002);
            l_IfoOrderUnitParams.setBizDate("20040716");
            l_IfoOrderUnitParams.setDeliveryDate(WEB3DateUtility.getDate("20040720","yyyyMMdd"));
            l_IfoOrderUnitParams.setEstimatedPrice(1000);
            l_IfoOrderUnitParams.setOrderType(OrderTypeEnum.IDX_OPTIONS_BUY_TO_OPEN);
            l_IfoOrderUnitParams.setOrderCateg(OrderCategEnum.IDX_OPTIONS_OPEN);
            l_IfoOrderUnitParams.setFutureOptionDiv(WEB3FuturesOptionDivDef.OPTION);
            TestDBUtility.insertWithDel(l_IfoOrderUnitParams);
            
            WEB3IfoTodayOpenOrder[] l_ifoTodayOpenOrderList = new WEB3IfoTodayOpenOrder[1];
            l_ifoTodayOpenOrderList[0] = WEB3IfoTodayOpenOrder.getIfoTodayOpenOrder(l_IfoOrderUnitParams);
            WEB3IfoContract[] l_ifoContractList = null;
            
            WEB3IfoDepositCalc l_ifo = new WEB3IfoDepositCalc();
            
            Object[] obj = {l_ifoContractList, l_ifoTodayOpenOrderList};
            
            Method method = WEB3IfoDepositCalc.class.getDeclaredMethod( ////被測方法@所在的類名
                "createTradedProductParamsList",  //<------------- 被測方法@名
                new Class[]{WEB3IfoContract[].class, WEB3IfoTodayOpenOrder[].class});//< ------------ 方法@參數的類型
            
            method.setAccessible(true); //<-------------開關
            IfoTradedProductParams[] l_ifoTradedProductParamsRetrun =
                (IfoTradedProductParams[])method.invoke(l_ifo, obj);  //<---------return

            assertEquals(1,l_ifoTradedProductParamsRetrun.length);
            assertEquals(2605000000001L,l_ifoTradedProductParamsRetrun[0].getProductId());
            assertEquals(1002L,l_ifoTradedProductParamsRetrun[0].getMarketId());
            assertEquals(1006160060005L,l_ifoTradedProductParamsRetrun[0].getTradedProductId());
        }
        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage());
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
//    引数.先物OP保有建玉情報一覧多條、 
//    但是對應的取引銘柄是同一條
//    引数.先物OP当日新規建注文情報一覧多條、
//    但是對應的取引銘柄是同一條
//    正確的先物OP取引銘柄Paramsを返却
    public void testCreateTradedProductParamsListC004()
    {
        String STR_METHOD_NAME = "testCreateTradedProductParamsListC004";
        log.entering(STR_METHOD_NAME);
        try
        {
            //銘柄表
            TestDBUtility.deleteAll(ProductRow.TYPE);
            ProductParams l_ProductParams = TestDBUtility.getProductRow();
            l_ProductParams.setProductId(2605000000001L);
            TestDBUtility.insertWithDel(l_ProductParams);

            //取引銘柄銘柄表
            TestDBUtility.deleteAll(TradedProductRow.TYPE);
            TradedProductParams l_TradedProductPrams = TestDBUtility.getTradedProductRow();
            l_TradedProductPrams.setProductId(2605000000001L);
            l_TradedProductPrams.setMarketId(1002);
            TestDBUtility.insertWithDel(l_TradedProductPrams);
            
            //IFO銘柄表
            TestDBUtility.deleteAll(IfoProductRow.TYPE);
            IfoProductParams l_ifoProductParams = TestDBUtility.getIfoProductRow();
            l_ifoProductParams.setProductId(2605000000001L);
            TestDBUtility.insertWithDel(l_ifoProductParams);

            //IFO取引銘柄銘柄表
            TestDBUtility.deleteAll(IfoTradedProductRow.TYPE);
            IfoTradedProductParams l_ifoTradedProductParams = TestDBUtility.getIfoTradedProductRow();
            l_ifoTradedProductParams.setProductId(2605000000001L);
            l_ifoTradedProductParams.setMarketId(1002);
            TestDBUtility.insertWithDel(l_ifoTradedProductParams);
            
            //建玉
            TestDBUtility.deleteAll(IfoContractRow.TYPE);
            IfoContractParams l_ifoContractParams1 = TestDBUtility.getIfoContractRow();
            l_ifoContractParams1.setProductId(2605000000001L);
            l_ifoContractParams1.setAccountId(111101111012L);
            l_ifoContractParams1.setSubAccountId(11110111101002L);
            l_ifoContractParams1.setOpenDate(WEB3DateUtility.getDate("20040716","yyyyMMdd"));
            l_ifoContractParams1.setOriginalQuantity(5.0);
            TestDBUtility.insertWithDel(l_ifoContractParams1);
            
            IfoContractParams l_ifoContractParams2 = TestDBUtility.getIfoContractRow();
            l_ifoContractParams2.setContractId(1002L);
            l_ifoContractParams2.setContractPrice(3250);
            l_ifoContractParams2.setProductId(2605000000001L);
            l_ifoContractParams2.setAccountId(111101111012L);
            l_ifoContractParams2.setSubAccountId(11110111101002L);
            l_ifoContractParams2.setOriginalQuantity(7.0);
            l_ifoContractParams2.setOpenDate(WEB3DateUtility.getDate("20040716","yyyyMMdd"));
            TestDBUtility.insertWithDel(l_ifoContractParams2);
            
            //注文単位テーブル
            TestDBUtility.deleteAll(IfoOrderUnitRow.TYPE);
            IfoOrderUnitParams l_IfoOrderUnitParams1 = TestDBUtility.getIfoOrderUnitRow();
            l_IfoOrderUnitParams1.setProductId(2605000000001L);
            l_IfoOrderUnitParams1.setAccountId(111101111010L);
            l_IfoOrderUnitParams1.setSubAccountId(11110111101001L);
            l_IfoOrderUnitParams1.setBranchId(11110);
            l_IfoOrderUnitParams1.setMarketId(1002);
            l_IfoOrderUnitParams1.setBizDate("20040716");
            l_IfoOrderUnitParams1.setDeliveryDate(WEB3DateUtility.getDate("20040720","yyyyMMdd"));
            l_IfoOrderUnitParams1.setEstimatedPrice(1000);
            l_IfoOrderUnitParams1.setOrderType(OrderTypeEnum.IDX_OPTIONS_BUY_TO_OPEN);
            l_IfoOrderUnitParams1.setOrderCateg(OrderCategEnum.IDX_OPTIONS_OPEN);
            l_IfoOrderUnitParams1.setFutureOptionDiv(WEB3FuturesOptionDivDef.OPTION);
            TestDBUtility.insertWithDel(l_IfoOrderUnitParams1);
            
            IfoOrderUnitParams l_IfoOrderUnitParams2 = TestDBUtility.getIfoOrderUnitRow();
            l_IfoOrderUnitParams2.setOrderId(1002L);
            l_IfoOrderUnitParams2.setOrderUnitId(1002L);
            l_IfoOrderUnitParams2.setProductId(2605000000001L);
            l_IfoOrderUnitParams2.setAccountId(111101111010L);
            l_IfoOrderUnitParams2.setSubAccountId(11110111101001L);
            l_IfoOrderUnitParams2.setBranchId(11110);
            l_IfoOrderUnitParams2.setMarketId(1002);
            l_IfoOrderUnitParams2.setBizDate("20040716");
            l_IfoOrderUnitParams2.setDeliveryDate(WEB3DateUtility.getDate("20040720","yyyyMMdd"));
            l_IfoOrderUnitParams2.setEstimatedPrice(1000);
            l_IfoOrderUnitParams2.setOrderType(OrderTypeEnum.IDX_OPTIONS_BUY_TO_OPEN);
            l_IfoOrderUnitParams2.setOrderCateg(OrderCategEnum.IDX_OPTIONS_OPEN);
            l_IfoOrderUnitParams2.setOrderRequestNumber("9008");
            l_IfoOrderUnitParams2.setFirstOrderUnitId(2001L);
            l_IfoOrderUnitParams2.setFutureOptionDiv(WEB3FuturesOptionDivDef.OPTION);
            TestDBUtility.insertWithDel(l_IfoOrderUnitParams2);
            
            //先物OP当日新規建注文情報一覧 
            WEB3IfoTodayOpenOrder[] l_ifoTodayOpenOrderList = new WEB3IfoTodayOpenOrder[2];
            l_ifoTodayOpenOrderList[0] = WEB3IfoTodayOpenOrder.getIfoTodayOpenOrder(l_IfoOrderUnitParams1);
            l_ifoTodayOpenOrderList[1] = WEB3IfoTodayOpenOrder.getIfoTodayOpenOrder(l_IfoOrderUnitParams2);
            
            //先物OP保有建玉情報一覧
            WEB3IfoContract[] l_ifoContractList = new WEB3IfoContract[2];
            Date l_datBizDate = WEB3DateUtility.getDate("20040716","yyyyMMdd");
            
            l_ifoContractList[0] =WEB3IfoContract.getIfoContract(l_ifoContractParams1, l_datBizDate);
            l_ifoContractList[1] =WEB3IfoContract.getIfoContract(l_ifoContractParams2, l_datBizDate);
            
            WEB3IfoDepositCalc l_ifo = new WEB3IfoDepositCalc();
            
            Object[] obj = {l_ifoContractList, l_ifoTodayOpenOrderList};
            
            Method method = WEB3IfoDepositCalc.class.getDeclaredMethod( ////被測方法@所在的類名
                "createTradedProductParamsList",  //<------------- 被測方法@名
                new Class[]{WEB3IfoContract[].class, WEB3IfoTodayOpenOrder[].class});//< ------------ 方法@參數的類型
            
            method.setAccessible(true); //<-------------開關
            IfoTradedProductParams[] l_ifoTradedProductParamsRetrun =
                (IfoTradedProductParams[])method.invoke(l_ifo, obj);  //<---------return

            assertEquals(1,l_ifoTradedProductParamsRetrun.length);
            assertEquals(2605000000001L,l_ifoTradedProductParamsRetrun[0].getProductId());
            assertEquals(1002L,l_ifoTradedProductParamsRetrun[0].getMarketId());
            assertEquals(1006160060005L,l_ifoTradedProductParamsRetrun[0].getTradedProductId());
        }
        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage());
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    //
    public void testCreateTradedProductParamsListC005()
    {
        String STR_METHOD_NAME = "testCreateTradedProductParamsListC005";
        log.entering(STR_METHOD_NAME);
        try
        {
            //銘柄表
            TestDBUtility.deleteAll(ProductRow.TYPE);
            ProductParams l_ProductParams1 = TestDBUtility.getProductRow();
            l_ProductParams1.setProductId(2605000000001L);
            TestDBUtility.insertWithDel(l_ProductParams1);
            

            ProductParams l_ProductParams2 = TestDBUtility.getProductRow();
            l_ProductParams2.setProductId(2605000000002L);
            TestDBUtility.insertWithDel(l_ProductParams2);
            
            ProductParams l_ProductParams3 = TestDBUtility.getProductRow();
            l_ProductParams3.setProductId(2605000000003L);
            TestDBUtility.insertWithDel(l_ProductParams3);
            
            ProductParams l_ProductParams4 = TestDBUtility.getProductRow();
            l_ProductParams4.setProductId(2605000000004L);
            TestDBUtility.insertWithDel(l_ProductParams4);

            //取引銘柄銘柄表
            TestDBUtility.deleteAll(TradedProductRow.TYPE);
            TradedProductParams l_TradedProductPrams1 = TestDBUtility.getTradedProductRow();
            l_TradedProductPrams1.setProductId(2605000000001L);
            l_TradedProductPrams1.setMarketId(1002);
            TestDBUtility.insertWithDel(l_TradedProductPrams1);
            
            TradedProductParams l_TradedProductPrams2 = TestDBUtility.getTradedProductRow();
            l_TradedProductPrams2.setProductId(2605000000002L);
            l_TradedProductPrams2.setTradedProductId(1006160060006L);
            l_TradedProductPrams2.setMarketId(1002);
            TestDBUtility.insertWithDel(l_TradedProductPrams2);
            
            TradedProductParams l_TradedProductPrams3 = TestDBUtility.getTradedProductRow();
            l_TradedProductPrams3.setProductId(2605000000003L);
            l_TradedProductPrams3.setMarketId(1002);
            l_TradedProductPrams3.setTradedProductId(1006160060007L);
            TestDBUtility.insertWithDel(l_TradedProductPrams3);
            
            TestDBUtility.deleteAll(TradedProductRow.TYPE);
            TradedProductParams l_TradedProductPrams4 = TestDBUtility.getTradedProductRow();
            l_TradedProductPrams4.setProductId(2605000000004L);
            l_TradedProductPrams4.setMarketId(1002);
            l_TradedProductPrams4.setTradedProductId(1006160060008L);
            TestDBUtility.insertWithDel(l_TradedProductPrams4);
            
            //IFO銘柄表
            TestDBUtility.deleteAll(IfoProductRow.TYPE);
            IfoProductParams l_ifoProductParams1 = TestDBUtility.getIfoProductRow();
            l_ifoProductParams1.setProductId(2605000000001L);
            TestDBUtility.insertWithDel(l_ifoProductParams1);
            
            IfoProductParams l_ifoProductParams2 = TestDBUtility.getIfoProductRow();
            l_ifoProductParams2.setProductId(2605000000002L);
            TestDBUtility.insertWithDel(l_ifoProductParams2);
            
            IfoProductParams l_ifoProductParams3 = TestDBUtility.getIfoProductRow();
            l_ifoProductParams3.setProductId(2605000000003L);
            TestDBUtility.insertWithDel(l_ifoProductParams3);
            
            IfoProductParams l_ifoProductParams4 = TestDBUtility.getIfoProductRow();
            l_ifoProductParams4.setProductId(2605000000004L);
            TestDBUtility.insertWithDel(l_ifoProductParams4);

            //IFO取引銘柄銘柄表
            TestDBUtility.deleteAll(IfoTradedProductRow.TYPE);
            IfoTradedProductParams l_ifoTradedProductParams1 = TestDBUtility.getIfoTradedProductRow();
            l_ifoTradedProductParams1.setProductId(2605000000001L);
            l_ifoTradedProductParams1.setMarketId(1002);
            TestDBUtility.insertWithDel(l_ifoTradedProductParams1);
            
            IfoTradedProductParams l_ifoTradedProductParams2 = TestDBUtility.getIfoTradedProductRow();
            l_ifoTradedProductParams2.setProductId(2605000000002L);
            l_ifoTradedProductParams2.setMarketId(1002);
            l_ifoTradedProductParams2.setTradedProductId(1006160060006L);
            TestDBUtility.insertWithDel(l_ifoTradedProductParams2);
            
            IfoTradedProductParams l_ifoTradedProductParams3 = TestDBUtility.getIfoTradedProductRow();
            l_ifoTradedProductParams3.setProductId(2605000000003L);
            l_ifoTradedProductParams3.setMarketId(1002);
            l_ifoTradedProductParams3.setTradedProductId(1006160060007L);
            TestDBUtility.insertWithDel(l_ifoTradedProductParams3);
            
            IfoTradedProductParams l_ifoTradedProductParams4 = TestDBUtility.getIfoTradedProductRow();
            l_ifoTradedProductParams4.setProductId(2605000000004L);
            l_ifoTradedProductParams4.setMarketId(1002);
            l_ifoTradedProductParams4.setTradedProductId(1006160060008L);
            TestDBUtility.insertWithDel(l_ifoTradedProductParams4);
            
            //建玉
            TestDBUtility.deleteAll(IfoContractRow.TYPE);
            IfoContractParams l_ifoContractParams1 = TestDBUtility.getIfoContractRow();
            l_ifoContractParams1.setProductId(2605000000001L);
            l_ifoContractParams1.setAccountId(111101111012L);
            l_ifoContractParams1.setSubAccountId(11110111101002L);
            l_ifoContractParams1.setOpenDate(WEB3DateUtility.getDate("20040716","yyyyMMdd"));
            l_ifoContractParams1.setOriginalQuantity(5.0);
            TestDBUtility.insertWithDel(l_ifoContractParams1);
            
            IfoContractParams l_ifoContractParams2 = TestDBUtility.getIfoContractRow();
            l_ifoContractParams2.setContractId(1002L);
            l_ifoContractParams2.setContractPrice(3250);
            l_ifoContractParams2.setProductId(2605000000002L);
            l_ifoContractParams2.setAccountId(111101111012L);
            l_ifoContractParams2.setSubAccountId(11110111101002L);
            l_ifoContractParams2.setOriginalQuantity(7.0);
            l_ifoContractParams2.setOpenDate(WEB3DateUtility.getDate("20040716","yyyyMMdd"));
            TestDBUtility.insertWithDel(l_ifoContractParams2);
            
            //注文単位テーブル
            TestDBUtility.deleteAll(IfoOrderUnitRow.TYPE);
            IfoOrderUnitParams l_IfoOrderUnitParams1 = TestDBUtility.getIfoOrderUnitRow();
            l_IfoOrderUnitParams1.setProductId(2605000000003L);
            l_IfoOrderUnitParams1.setAccountId(111101111010L);
            l_IfoOrderUnitParams1.setSubAccountId(11110111101001L);
            l_IfoOrderUnitParams1.setBranchId(11110);
            l_IfoOrderUnitParams1.setMarketId(1002);
            l_IfoOrderUnitParams1.setBizDate("20040716");
            l_IfoOrderUnitParams1.setDeliveryDate(WEB3DateUtility.getDate("20040720","yyyyMMdd"));
            l_IfoOrderUnitParams1.setEstimatedPrice(1000);
            l_IfoOrderUnitParams1.setOrderType(OrderTypeEnum.IDX_OPTIONS_BUY_TO_OPEN);
            l_IfoOrderUnitParams1.setOrderCateg(OrderCategEnum.IDX_OPTIONS_OPEN);
            l_IfoOrderUnitParams1.setFutureOptionDiv(WEB3FuturesOptionDivDef.OPTION);
            TestDBUtility.insertWithDel(l_IfoOrderUnitParams1);
            
            IfoOrderUnitParams l_IfoOrderUnitParams2 = TestDBUtility.getIfoOrderUnitRow();
            l_IfoOrderUnitParams2.setOrderId(1002L);
            l_IfoOrderUnitParams2.setOrderUnitId(1002L);
            l_IfoOrderUnitParams2.setProductId(2605000000004L);
            l_IfoOrderUnitParams2.setAccountId(111101111010L);
            l_IfoOrderUnitParams2.setSubAccountId(11110111101001L);
            l_IfoOrderUnitParams2.setBranchId(11110);
            l_IfoOrderUnitParams2.setMarketId(1002);
            l_IfoOrderUnitParams2.setBizDate("20040716");
            l_IfoOrderUnitParams2.setDeliveryDate(WEB3DateUtility.getDate("20040720","yyyyMMdd"));
            l_IfoOrderUnitParams2.setEstimatedPrice(1000);
            l_IfoOrderUnitParams2.setOrderType(OrderTypeEnum.IDX_OPTIONS_BUY_TO_OPEN);
            l_IfoOrderUnitParams2.setOrderCateg(OrderCategEnum.IDX_OPTIONS_OPEN);
            l_IfoOrderUnitParams2.setOrderRequestNumber("9008");
            l_IfoOrderUnitParams2.setFirstOrderUnitId(2001L);
            l_IfoOrderUnitParams2.setFutureOptionDiv(WEB3FuturesOptionDivDef.OPTION);
            TestDBUtility.insertWithDel(l_IfoOrderUnitParams2);
            
            //先物OP当日新規建注文情報一覧 
            WEB3IfoTodayOpenOrder[] l_ifoTodayOpenOrderList = new WEB3IfoTodayOpenOrder[2];
            l_ifoTodayOpenOrderList[0] = WEB3IfoTodayOpenOrder.getIfoTodayOpenOrder(l_IfoOrderUnitParams1);
            l_ifoTodayOpenOrderList[1] = WEB3IfoTodayOpenOrder.getIfoTodayOpenOrder(l_IfoOrderUnitParams2);
            
            //先物OP保有建玉情報一覧
            WEB3IfoContract[] l_ifoContractList = new WEB3IfoContract[2];
            Date l_datBizDate = WEB3DateUtility.getDate("20040716","yyyyMMdd");
            
            l_ifoContractList[0] =WEB3IfoContract.getIfoContract(l_ifoContractParams1, l_datBizDate);
            l_ifoContractList[1] =WEB3IfoContract.getIfoContract(l_ifoContractParams2, l_datBizDate);
            
            WEB3IfoDepositCalc l_ifo = new WEB3IfoDepositCalc();
            
            Object[] obj = {l_ifoContractList, l_ifoTodayOpenOrderList};
            
            Method method = WEB3IfoDepositCalc.class.getDeclaredMethod( ////被測方法@所在的類名
                "createTradedProductParamsList",  //<------------- 被測方法@名
                new Class[]{WEB3IfoContract[].class, WEB3IfoTodayOpenOrder[].class});//< ------------ 方法@參數的類型
            
            method.setAccessible(true); //<-------------開關
            IfoTradedProductParams[] l_ifoTradedProductParamsRetrun =
                (IfoTradedProductParams[])method.invoke(l_ifo, obj);  //<---------return

            assertEquals(4,l_ifoTradedProductParamsRetrun.length);
            assertEquals(2605000000004L,l_ifoTradedProductParamsRetrun[0].getProductId());
            assertEquals(2605000000002L,l_ifoTradedProductParamsRetrun[1].getProductId());
            assertEquals(2605000000003L,l_ifoTradedProductParamsRetrun[2].getProductId());
            assertEquals(2605000000001L,l_ifoTradedProductParamsRetrun[3].getProductId());
        }
        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage());
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    //保有/注文している先物OP銘柄が存在しない場合は、nullを返却する。
    public void testCreateIfoProductListC001()
    {
        String STR_METHOD_NAME = "testCreateIfoProductListC001";
        log.entering(STR_METHOD_NAME);
        try
        {
            IfoTradedProductParams[] l_ifoTradedProductParams = null;
            
            WEB3IfoDepositCalc l_ifo = new WEB3IfoDepositCalc();
            
            Object[] obj = {l_ifoTradedProductParams};
            
            Method method = WEB3IfoDepositCalc.class.getDeclaredMethod( ////被測方法@所在的類名
                "createIfoProductList",  //<------------- 被測方法@名
                new Class[]{IfoTradedProductParams[].class});//< ------------ 方法@參數的類型
            
            method.setAccessible(true); //<-------------開關
            WEB3IfoProduct[] l_ifoProductRetrun =
                (WEB3IfoProduct[])method.invoke(l_ifo, obj);  //<---------return
            assertNull(l_ifoProductRetrun);
            
        }
        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage());
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    //市場開局時間帯(日中)の時
    public void testCreateIfoProductListC002()
    {
        String STR_METHOD_NAME = "testCreateIfoProductListC002";
        log.entering(STR_METHOD_NAME);
        try
        {
            //取引時間テーブル;
            TestDBUtility.deleteAll(TradingTimeRow.TYPE);
            TradingTimeParams l_TradingTimeParams1 = TestDBUtility.getTradingTimeRow();
            l_TradingTimeParams1.setInstitutionCode("0D");
            l_TradingTimeParams1.setBranchCode("123");
            l_TradingTimeParams1.setMarketCode("N1");
            l_TradingTimeParams1.setTradingTimeType("13");
            l_TradingTimeParams1.setProductCode("0");
            l_TradingTimeParams1.setBizDateType("1");
            TestDBUtility.insertWithDel(l_TradingTimeParams1);
            
            TradingTimeParams l_TradingTimeParams2 = TestDBUtility.getTradingTimeRow();
            l_TradingTimeParams2.setInstitutionCode("0D");
            l_TradingTimeParams2.setBranchCode("123");
            l_TradingTimeParams2.setMarketCode("N1");
            l_TradingTimeParams2.setTradingTimeType("11");
            l_TradingTimeParams2.setProductCode("0005");
            l_TradingTimeParams2.setBizDateType("1");
            TestDBUtility.insertWithDel(l_TradingTimeParams2);
            
            TradingTimeParams l_TradingTimeParams3 = TestDBUtility.getTradingTimeRow();
            l_TradingTimeParams3.setInstitutionCode("0D");
            l_TradingTimeParams3.setBranchCode("123");
            l_TradingTimeParams3.setMarketCode("N1");
            l_TradingTimeParams3.setTradingTimeType("01");
            l_TradingTimeParams3.setProductCode("0005");
            l_TradingTimeParams3.setBizDateType("1");
            TestDBUtility.insertWithDel(l_TradingTimeParams3);

//            WEB3GentradeTradingClendarContext l_clendarContext = new WEB3GentradeTradingClendarContext();
//            l_clendarContext.setInstitutionCode("0D");
//            l_clendarContext.setBranchCode("381");
//            l_clendarContext.setMarketCode("SP");
//            l_clendarContext.setProductCode("12");
//            l_clendarContext.setTradingTimeType("01");
//            l_clendarContext.setOrderAcceptProduct("01");
//            l_clendarContext.setBizDateType("1");
//
//            ThreadLocalSystemAttributesRegistry.setAttribute(
//                WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH,
//                l_clendarContext);

            Calendar l_calendar = Calendar.getInstance();
            l_calendar.set(Calendar.YEAR,2004);
            l_calendar.set(Calendar.MONTH,6);
            l_calendar.set(Calendar.DAY_OF_MONTH,16);
            l_calendar.set(Calendar.HOUR_OF_DAY,10);
            l_calendar.set(Calendar.MINUTE,00);
            l_calendar.set(Calendar.SECOND,00);

            Timestamp l_tsBizDate = new Timestamp(l_calendar.getTimeInMillis());

            ThreadLocalSystemAttributesRegistry.setAttribute(
                WEB3GentradeTradingTimeManagement.TIMESTAMP_TAG, l_tsBizDate);

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.tc.gentrade.stdimpls.TradingSystemImpl",
                "getSystemTimestamp",
                new Class[] {},
                l_tsBizDate);

            //銘柄表
            TestDBUtility.deleteAll(ProductRow.TYPE);
            ProductParams l_ProductParams1 = TestDBUtility.getProductRow();
            l_ProductParams1.setProductId(2605000000001L);
            TestDBUtility.insertWithDel(l_ProductParams1);
            
            ProductParams l_ProductParams2 = TestDBUtility.getProductRow();
            l_ProductParams2.setProductId(2605000000002L);
            TestDBUtility.insertWithDel(l_ProductParams2);

            //取引銘柄銘柄表
            TestDBUtility.deleteAll(TradedProductRow.TYPE);
            TradedProductParams l_TradedProductPrams1 = TestDBUtility.getTradedProductRow();
            l_TradedProductPrams1.setProductId(2605000000001L);
            l_TradedProductPrams1.setMarketId(1002);
            l_TradedProductPrams1.setProductType(ProductTypeEnum.IFO);
            TestDBUtility.insertWithDel(l_TradedProductPrams1);
            
            TradedProductParams l_TradedProductPrams2 = TestDBUtility.getTradedProductRow();
            l_TradedProductPrams2.setProductId(2605000000002L);
            l_TradedProductPrams2.setTradedProductId(1006160060006L);
            l_TradedProductPrams2.setMarketId(1002);
            TestDBUtility.insertWithDel(l_TradedProductPrams2);
            
            //IFO銘柄表
            TestDBUtility.deleteAll(IfoProductRow.TYPE);
            IfoProductParams l_ifoProductParams1 = TestDBUtility.getIfoProductRow();
            l_ifoProductParams1.setProductId(2605000000001L);
            l_ifoProductParams1.setProductType(ProductTypeEnum.IFO);
            l_ifoProductParams1.setInstitutionCode("0D");
            TestDBUtility.insertWithDel(l_ifoProductParams1);
            
            IfoProductParams l_ifoProductParams2 = TestDBUtility.getIfoProductRow();
            l_ifoProductParams2.setProductId(2605000000002L);
            l_ifoProductParams2.setProductType(ProductTypeEnum.IFO);
            TestDBUtility.insertWithDel(l_ifoProductParams2);

            //IFO取引銘柄銘柄表
            TestDBUtility.deleteAll(IfoTradedProductRow.TYPE);
            IfoTradedProductParams l_ifoTradedProductParams1 = TestDBUtility.getIfoTradedProductRow();
            l_ifoTradedProductParams1.setProductId(2605000000001L);
            l_ifoTradedProductParams1.setMarketId(1002);
            TestDBUtility.insertWithDel(l_ifoTradedProductParams1);
            
            IfoTradedProductParams l_ifoTradedProductParams2 = TestDBUtility.getIfoTradedProductRow();
            l_ifoTradedProductParams2.setProductId(2605000000002L);
            l_ifoTradedProductParams2.setMarketId(1002);
            l_ifoTradedProductParams2.setTradedProductId(1006160060006L);
            l_ifoTradedProductParams2.setUnitSize(100);
            TestDBUtility.insertWithDel(l_ifoTradedProductParams2);
            
            WEB3IfoDepositCalcCondition l_ifoDepositCalcCondition = new WEB3IfoDepositCalcCondition();
            Date l_bizDate1 = WEB3DateUtility.getDate("20040715","yyyyMMdd");
            Date l_bizDate2 = WEB3DateUtility.getDate("20040716","yyyyMMdd");
            Date l_bizDate3 = WEB3DateUtility.getDate("20040720","yyyyMMdd");
            Date l_bizDate4 = WEB3DateUtility.getDate("20040721","yyyyMMdd");
            Date[] l_bizDates = {l_bizDate1, l_bizDate2, l_bizDate3, l_bizDate4};
            l_ifoDepositCalcCondition.setBizDates(l_bizDates);
            l_ifoDepositCalcCondition.addCalcConditionPerBranch("ifodeposit.profitloss.calc", "1");
            
            IfoTradedProductParams[] l_ifoTradedProductParams = new IfoTradedProductParams[2];
            l_ifoTradedProductParams[0] = l_ifoTradedProductParams1;
            l_ifoTradedProductParams[1] = l_ifoTradedProductParams2;
            
            WEB3IfoDepositCalc l_ifo = new WEB3IfoDepositCalc();
            
            Object[] obj = {l_ifoTradedProductParams};
            
            Field field = WEB3IfoDepositCalc.class.getDeclaredField("ifoDepositCalcCondition");
            field.setAccessible(true);
            field.set(l_ifo, l_ifoDepositCalcCondition);
            
            Method method = WEB3IfoDepositCalc.class.getDeclaredMethod( ////被測方法@所在的類名
                "createIfoProductList",  //<------------- 被測方法@名
                new Class[]{IfoTradedProductParams[].class});//< ------------ 方法@參數的類型
            
            method.setAccessible(true); //<-------------開關
            WEB3IfoProduct[] l_ifoProductRetrun =
                (WEB3IfoProduct[])method.invoke(l_ifo, obj);  //<---------return
            
            assertEquals(2605000000001L, l_ifoProductRetrun[0].getProductId());
            assertEquals(10000, l_ifoProductRetrun[0].getUnitSize(), 0);
            assertEquals(2605000000002L, l_ifoProductRetrun[1].getProductId());
            assertEquals(100, l_ifoProductRetrun[1].getUnitSize(), 0);
        }
        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage());
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    //市場開局時間帯(夕場)の時
    public void testCreateIfoProductListC003()
    {
        String STR_METHOD_NAME = "testCreateIfoProductListC003";
        log.entering(STR_METHOD_NAME);
        try
        {
            //取引時間テーブル;
            TestDBUtility.deleteAll(TradingTimeRow.TYPE);
            TradingTimeParams l_TradingTimeParams1 = TestDBUtility.getTradingTimeRow();
            l_TradingTimeParams1.setInstitutionCode("0D");
            l_TradingTimeParams1.setBranchCode("123");
            l_TradingTimeParams1.setMarketCode("N1");
            l_TradingTimeParams1.setTradingTimeType("13");
            l_TradingTimeParams1.setProductCode("0");
            l_TradingTimeParams1.setBizDateType("1");
            TestDBUtility.insertWithDel(l_TradingTimeParams1);
            
            TradingTimeParams l_TradingTimeParams2 = TestDBUtility.getTradingTimeRow();
            l_TradingTimeParams2.setInstitutionCode("0D");
            l_TradingTimeParams2.setBranchCode("123");
            l_TradingTimeParams2.setMarketCode("N1");
            l_TradingTimeParams2.setTradingTimeType("11");
            l_TradingTimeParams2.setProductCode("0005");
            l_TradingTimeParams2.setBizDateType("1");
            l_TradingTimeParams2.setSessionType("1");
            l_TradingTimeParams2.setSubmitMarketTrigger("1");
            TestDBUtility.insertWithDel(l_TradingTimeParams2);
            
            TradingTimeParams l_TradingTimeParams3 = TestDBUtility.getTradingTimeRow();
            l_TradingTimeParams3.setInstitutionCode("0D");
            l_TradingTimeParams3.setBranchCode("123");
            l_TradingTimeParams3.setMarketCode("N1");
            l_TradingTimeParams3.setTradingTimeType("01");
            l_TradingTimeParams3.setProductCode("0005");
            l_TradingTimeParams3.setBizDateType("1");
            TestDBUtility.insertWithDel(l_TradingTimeParams3);


            Calendar l_calendar = Calendar.getInstance();
            l_calendar.set(Calendar.YEAR,2004);
            l_calendar.set(Calendar.MONTH,6);
            l_calendar.set(Calendar.DAY_OF_MONTH,16);
            l_calendar.set(Calendar.HOUR_OF_DAY,17);
            l_calendar.set(Calendar.MINUTE,00);
            l_calendar.set(Calendar.SECOND,00);

            Timestamp l_tsBizDate = new Timestamp(l_calendar.getTimeInMillis());

            ThreadLocalSystemAttributesRegistry.setAttribute(
                WEB3GentradeTradingTimeManagement.TIMESTAMP_TAG, l_tsBizDate);
            ThreadLocalSystemAttributesRegistry.setAttribute(
                WEB3GentradeTradingTimeManagement.BASE_TIMESTAMP_FOR_ORDER_BIZ_DATE, l_tsBizDate);

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.tc.gentrade.stdimpls.TradingSystemImpl",
                "getSystemTimestamp",
                new Class[] {},
                l_tsBizDate);

            //銘柄表
            TestDBUtility.deleteAll(ProductRow.TYPE);
            ProductParams l_ProductParams1 = TestDBUtility.getProductRow();
            l_ProductParams1.setProductId(2605000000001L);
            TestDBUtility.insertWithDel(l_ProductParams1);

            //取引銘柄銘柄表
            TestDBUtility.deleteAll(TradedProductRow.TYPE);
            TradedProductParams l_TradedProductPrams1 = TestDBUtility.getTradedProductRow();
            l_TradedProductPrams1.setProductId(2605000000001L);
            l_TradedProductPrams1.setMarketId(1002);
            l_TradedProductPrams1.setProductType(ProductTypeEnum.IFO);
            TestDBUtility.insertWithDel(l_TradedProductPrams1);
            
            //取引銘柄銘柄一時表
            TestDBUtility.deleteAll(TradedProductUpdqRow.TYPE);
            TradedProductUpdqParams l_TradedProductUpdqParams = TestDBUtility.getTradedProductUpdqRow();
            l_TradedProductUpdqParams.setProductId(2605000000001L);
            l_TradedProductUpdqParams.setMarketId(1002);
            l_TradedProductUpdqParams.setProductType(ProductTypeEnum.IFO);
            l_TradedProductUpdqParams.setValidUntilBizDate("20040720");
            l_TradedProductUpdqParams.setInstitutionCode("0D");
            l_TradedProductUpdqParams.setBaseDate(WEB3DateUtility.getDate("20040720","yyyyMMdd"));
            TestDBUtility.insertWithDel(l_TradedProductUpdqParams);
            
            //IFO銘柄表
            TestDBUtility.deleteAll(IfoProductRow.TYPE);
            IfoProductParams l_ifoProductParams1 = TestDBUtility.getIfoProductRow();
            l_ifoProductParams1.setProductId(2605000000001L);
            l_ifoProductParams1.setProductType(ProductTypeEnum.IFO);
            l_ifoProductParams1.setInstitutionCode("0D");
            TestDBUtility.insertWithDel(l_ifoProductParams1);

            //IFO取引銘柄銘柄表
            TestDBUtility.deleteAll(IfoTradedProductRow.TYPE);
            IfoTradedProductParams l_ifoTradedProductParams1 = TestDBUtility.getIfoTradedProductRow();
            l_ifoTradedProductParams1.setProductId(2605000000001L);
            l_ifoTradedProductParams1.setMarketId(1002);
            TestDBUtility.insertWithDel(l_ifoTradedProductParams1);
            
            //IFO取引銘柄銘柄表一時
            TestDBUtility.deleteAll(IfoTradedProductUpdqRow.TYPE);
            IfoTradedProductUpdqParams l_ifoTradedProductUpdqParams = TestDBUtility.getIfoTradedProductUpdqRow();
            l_ifoTradedProductUpdqParams.setProductId(2605000000001L);
            l_ifoTradedProductUpdqParams.setMarketId(1002);
            l_ifoTradedProductUpdqParams.setInstitutionCode("0D");
            l_ifoTradedProductUpdqParams.setValidForBizDate("20040720");
            l_ifoTradedProductUpdqParams.setUnitSize(20000);
            l_ifoTradedProductUpdqParams.setLiquidationPrice(12);
            TestDBUtility.insertWithDel(l_ifoTradedProductUpdqParams);
            
            WEB3IfoDepositCalcCondition l_ifoDepositCalcCondition = new WEB3IfoDepositCalcCondition();
            Date l_bizDate1 = WEB3DateUtility.getDate("20040715","yyyyMMdd");
            Date l_bizDate2 = WEB3DateUtility.getDate("20040716","yyyyMMdd");
            Date l_bizDate3 = WEB3DateUtility.getDate("20040720","yyyyMMdd");
            Date l_bizDate4 = WEB3DateUtility.getDate("20040721","yyyyMMdd");
            Date[] l_bizDates = {l_bizDate1, l_bizDate2, l_bizDate3, l_bizDate4};
            l_ifoDepositCalcCondition.setBizDates(l_bizDates);
            
            //リアル時価証拠金計算実施フラグ
            l_ifoDepositCalcCondition.setRealPriceIfoDepositCalcFlag(false);
            //証拠金不足メール送信済フラグ
            l_ifoDepositCalcCondition.setIfoDepositMailFlag(false);
            l_ifoDepositCalcCondition.addCalcConditionPerBranch("ifodeposit.profitloss.calc", "1");
            
            IfoTradedProductParams[] l_ifoTradedProductParams = new IfoTradedProductParams[1];
            l_ifoTradedProductParams[0] = l_ifoTradedProductParams1;
            
            WEB3IfoDepositCalc l_ifo = new WEB3IfoDepositCalc();
            
            Object[] obj = {l_ifoTradedProductParams};
            
            Field field = WEB3IfoDepositCalc.class.getDeclaredField("ifoDepositCalcCondition");
            field.setAccessible(true);
            field.set(l_ifo, l_ifoDepositCalcCondition);
            
            Method method = WEB3IfoDepositCalc.class.getDeclaredMethod( ////被測方法@所在的類名
                "createIfoProductList",  //<------------- 被測方法@名
                new Class[]{IfoTradedProductParams[].class});//< ------------ 方法@參數的類型
            
            method.setAccessible(true); //<-------------開關
            WEB3IfoProduct[] l_ifoProductRetrun =
                (WEB3IfoProduct[])method.invoke(l_ifo, obj);  //<---------return
            //compare
            assertEquals(20000, l_ifoProductRetrun[0].getUnitSize(), 0);
            assertEquals(12, l_ifoProductRetrun[0].getCurrentPrice(), 0);

        }
        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage());
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testCreateIfoProductListC004()
    {
        String STR_METHOD_NAME = "testCreateIfoProductListC004";
        log.entering(STR_METHOD_NAME);
        try
        {
            //取引時間テーブル;
            TestDBUtility.deleteAll(TradingTimeRow.TYPE);
            TradingTimeParams l_TradingTimeParams1 = TestDBUtility.getTradingTimeRow();
            l_TradingTimeParams1.setInstitutionCode("0D");
            l_TradingTimeParams1.setBranchCode("123");
            l_TradingTimeParams1.setMarketCode("N1");
            l_TradingTimeParams1.setTradingTimeType("13");
            l_TradingTimeParams1.setProductCode("0");
            l_TradingTimeParams1.setBizDateType("1");
            TestDBUtility.insertWithDel(l_TradingTimeParams1);
            
            TradingTimeParams l_TradingTimeParams2 = TestDBUtility.getTradingTimeRow();
            l_TradingTimeParams2.setInstitutionCode("0D");
            l_TradingTimeParams2.setBranchCode("123");
            l_TradingTimeParams2.setMarketCode("N1");
            l_TradingTimeParams2.setTradingTimeType("11");
            l_TradingTimeParams2.setProductCode("0005");
            l_TradingTimeParams2.setBizDateType("1");
            l_TradingTimeParams2.setSessionType("0");
            l_TradingTimeParams2.setSubmitMarketTrigger("0");
            l_TradingTimeParams2.setBizdateCalcParameter("0");
            l_TradingTimeParams2.setEndTime("235959");
            l_TradingTimeParams2.setStartTime("150000");
            TestDBUtility.insertWithDel(l_TradingTimeParams2);
            
            TradingTimeParams l_TradingTimeParams3 = TestDBUtility.getTradingTimeRow();
            l_TradingTimeParams3.setInstitutionCode("0D");
            l_TradingTimeParams3.setBranchCode("123");
            l_TradingTimeParams3.setMarketCode("N1");
            l_TradingTimeParams3.setTradingTimeType("11");
            l_TradingTimeParams3.setProductCode("0005");
            l_TradingTimeParams3.setBizDateType("1");
            l_TradingTimeParams3.setSessionType("0");
            l_TradingTimeParams3.setSubmitMarketTrigger("1");
            l_TradingTimeParams3.setBizdateCalcParameter("0");
            l_TradingTimeParams3.setEndTime("080000");
            l_TradingTimeParams3.setStartTime("145959");
            TestDBUtility.insertWithDel(l_TradingTimeParams3);


            Calendar l_calendar = Calendar.getInstance();
            l_calendar.set(Calendar.YEAR,2004);
            l_calendar.set(Calendar.MONTH,6);
            l_calendar.set(Calendar.DAY_OF_MONTH,16);
            l_calendar.set(Calendar.HOUR_OF_DAY,15);
            l_calendar.set(Calendar.MINUTE,10);
            l_calendar.set(Calendar.SECOND,00);

            Timestamp l_tsBizDate = new Timestamp(l_calendar.getTimeInMillis());

            ThreadLocalSystemAttributesRegistry.setAttribute(
                WEB3GentradeTradingTimeManagement.TIMESTAMP_TAG, l_tsBizDate);
            ThreadLocalSystemAttributesRegistry.setAttribute(
                WEB3GentradeTradingTimeManagement.BASE_TIMESTAMP_FOR_ORDER_BIZ_DATE, l_tsBizDate);

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.tc.gentrade.stdimpls.TradingSystemImpl",
                "getSystemTimestamp",
                new Class[] {},
                l_tsBizDate);

            //銘柄表
            TestDBUtility.deleteAll(ProductRow.TYPE);
            ProductParams l_ProductParams1 = TestDBUtility.getProductRow();
            l_ProductParams1.setProductId(2605000000001L);
            TestDBUtility.insertWithDel(l_ProductParams1);

            //取引銘柄銘柄表
            TestDBUtility.deleteAll(TradedProductRow.TYPE);
            TradedProductParams l_TradedProductPrams1 = TestDBUtility.getTradedProductRow();
            l_TradedProductPrams1.setProductId(2605000000001L);
            l_TradedProductPrams1.setMarketId(1002);
            l_TradedProductPrams1.setProductType(ProductTypeEnum.IFO);
            l_TradedProductPrams1.setValidUntilBizDate("20040716");
            TestDBUtility.insertWithDel(l_TradedProductPrams1);
            
            //IFO銘柄表
            TestDBUtility.deleteAll(IfoProductRow.TYPE);
            IfoProductParams l_ifoProductParams1 = TestDBUtility.getIfoProductRow();
            l_ifoProductParams1.setProductId(2605000000001L);
            l_ifoProductParams1.setProductType(ProductTypeEnum.IFO);
            l_ifoProductParams1.setInstitutionCode("0D");
            l_ifoProductParams1.setTargetMarketId(1002);
            TestDBUtility.insertWithDel(l_ifoProductParams1);

            //IFO取引銘柄銘柄表
            TestDBUtility.deleteAll(IfoTradedProductRow.TYPE);
            IfoTradedProductParams l_ifoTradedProductParams1 = TestDBUtility.getIfoTradedProductRow();
            l_ifoTradedProductParams1.setProductId(2605000000001L);
            l_ifoTradedProductParams1.setMarketId(1002);
            l_ifoTradedProductParams1.setUnitSize(30000);
            l_ifoTradedProductParams1.setLiquidationPrice(50);
            l_ifoTradedProductParams1.setValidForBizDate("20040716");
            TestDBUtility.insertWithDel(l_ifoTradedProductParams1);
            
            WEB3IfoDepositCalcCondition l_ifoDepositCalcCondition = new WEB3IfoDepositCalcCondition();
            Date l_bizDate1 = WEB3DateUtility.getDate("20040715","yyyyMMdd");
            Date l_bizDate2 = WEB3DateUtility.getDate("20040716","yyyyMMdd");
            Date l_bizDate3 = WEB3DateUtility.getDate("20040720","yyyyMMdd");
            Date l_bizDate4 = WEB3DateUtility.getDate("20040721","yyyyMMdd");
            Date[] l_bizDates = {l_bizDate1, l_bizDate2, l_bizDate3, l_bizDate4};
            l_ifoDepositCalcCondition.setBizDates(l_bizDates);
            
            //IFO取引銘柄銘柄表一時
            TestDBUtility.deleteAll(IfoTradedProductUpdqRow.TYPE);
            IfoTradedProductUpdqParams l_ifoTradedProductUpdqParams = TestDBUtility.getIfoTradedProductUpdqRow();
            l_ifoTradedProductUpdqParams.setProductId(2605000000001L);
            l_ifoTradedProductUpdqParams.setMarketId(1002);
            l_ifoTradedProductUpdqParams.setInstitutionCode("0D");
            l_ifoTradedProductUpdqParams.setValidForBizDate("20040720");
            l_ifoTradedProductUpdqParams.setUnitSize(40000);
            l_ifoTradedProductUpdqParams.setLiquidationPrice(15);
            TestDBUtility.insertWithDel(l_ifoTradedProductUpdqParams);
            
            //リアル時価証拠金計算実施フラグ
            l_ifoDepositCalcCondition.setRealPriceIfoDepositCalcFlag(false);
            //証拠金不足メール送信済フラグ
            l_ifoDepositCalcCondition.setIfoDepositMailFlag(false);
            l_ifoDepositCalcCondition.addCalcConditionPerBranch("ifodeposit.profitloss.calc", "1");
            
            IfoTradedProductParams[] l_ifoTradedProductParams = new IfoTradedProductParams[1];
            l_ifoTradedProductParams[0] = l_ifoTradedProductParams1;
            
            WEB3IfoDepositCalc l_ifo = new WEB3IfoDepositCalc();
            
            Object[] obj = {l_ifoTradedProductParams};
            
            Field field = WEB3IfoDepositCalc.class.getDeclaredField("ifoDepositCalcCondition");
            field.setAccessible(true);
            field.set(l_ifo, l_ifoDepositCalcCondition);
            
            Method method = WEB3IfoDepositCalc.class.getDeclaredMethod( ////被測方法@所在的類名
                "createIfoProductList",  //<------------- 被測方法@名
                new Class[]{IfoTradedProductParams[].class});//< ------------ 方法@參數的類型
            
            method.setAccessible(true); //<-------------開關
            WEB3IfoProduct[] l_ifoProductRetrun =
                (WEB3IfoProduct[])method.invoke(l_ifo, obj);  //<---------return
            //compare
            assertEquals(1, l_ifoProductRetrun.length);
            assertEquals(30000, l_ifoProductRetrun[0].getUnitSize(), 0);
            assertEquals(50, l_ifoProductRetrun[0].getCurrentPrice(), 0);
        }
        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage());
            fail();
        }
        log.exiting(STR_METHOD_NAME); 
    }
    
    public void testCreateIfoProductListC005()
    {
        String STR_METHOD_NAME = "testCreateIfoProductListC005";
        log.entering(STR_METHOD_NAME);
        try
        {
            //取引時間テーブル;
            TestDBUtility.deleteAll(TradingTimeRow.TYPE);
            TradingTimeParams l_TradingTimeParams1 = TestDBUtility.getTradingTimeRow();
            l_TradingTimeParams1.setInstitutionCode("0D");
            l_TradingTimeParams1.setBranchCode("123");
            l_TradingTimeParams1.setMarketCode("N1");
            l_TradingTimeParams1.setTradingTimeType("13");
            l_TradingTimeParams1.setProductCode("0");
            l_TradingTimeParams1.setBizDateType("1");
            TestDBUtility.insertWithDel(l_TradingTimeParams1);
            
            TradingTimeParams l_TradingTimeParams2 = TestDBUtility.getTradingTimeRow();
            l_TradingTimeParams2.setInstitutionCode("0D");
            l_TradingTimeParams2.setBranchCode("123");
            l_TradingTimeParams2.setMarketCode("N1");
            l_TradingTimeParams2.setTradingTimeType("11");
            l_TradingTimeParams2.setProductCode("0005");
            l_TradingTimeParams2.setBizDateType("1");
            l_TradingTimeParams2.setSessionType("1");
            l_TradingTimeParams2.setSubmitMarketTrigger("0");
            l_TradingTimeParams2.setBizdateCalcParameter("0");
            l_TradingTimeParams2.setEndTime("235959");
            l_TradingTimeParams2.setStartTime("150000");
            TestDBUtility.insertWithDel(l_TradingTimeParams2);
            
            TradingTimeParams l_TradingTimeParams3 = TestDBUtility.getTradingTimeRow();
            l_TradingTimeParams3.setInstitutionCode("0D");
            l_TradingTimeParams3.setBranchCode("123");
            l_TradingTimeParams3.setMarketCode("N1");
            l_TradingTimeParams3.setTradingTimeType("11");
            l_TradingTimeParams3.setProductCode("0005");
            l_TradingTimeParams3.setBizDateType("1");
            l_TradingTimeParams3.setSessionType("0");
            l_TradingTimeParams3.setSubmitMarketTrigger("1");
            l_TradingTimeParams3.setBizdateCalcParameter("0");
            l_TradingTimeParams3.setEndTime("080000");
            l_TradingTimeParams3.setStartTime("145959");
            TestDBUtility.insertWithDel(l_TradingTimeParams3);;


            Calendar l_calendar = Calendar.getInstance();
            l_calendar.set(Calendar.YEAR,2004);
            l_calendar.set(Calendar.MONTH,6);
            l_calendar.set(Calendar.DAY_OF_MONTH,16);
            l_calendar.set(Calendar.HOUR_OF_DAY,20);
            l_calendar.set(Calendar.MINUTE,00);
            l_calendar.set(Calendar.SECOND,00);

            Timestamp l_tsBizDate = new Timestamp(l_calendar.getTimeInMillis());

            ThreadLocalSystemAttributesRegistry.setAttribute(
                WEB3GentradeTradingTimeManagement.TIMESTAMP_TAG, l_tsBizDate);
            ThreadLocalSystemAttributesRegistry.setAttribute(
                WEB3GentradeTradingTimeManagement.BASE_TIMESTAMP_FOR_ORDER_BIZ_DATE, l_tsBizDate);

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.tc.gentrade.stdimpls.TradingSystemImpl",
                "getSystemTimestamp",
                new Class[] {},
                l_tsBizDate);

            //銘柄表
            TestDBUtility.deleteAll(ProductRow.TYPE);
            ProductParams l_ProductParams1 = TestDBUtility.getProductRow();
            l_ProductParams1.setProductId(2605000000001L);
            TestDBUtility.insertWithDel(l_ProductParams1);

            //取引銘柄銘柄表
            TestDBUtility.deleteAll(TradedProductRow.TYPE);
            TradedProductParams l_TradedProductPrams1 = TestDBUtility.getTradedProductRow();
            l_TradedProductPrams1.setProductId(2605000000001L);
            l_TradedProductPrams1.setMarketId(1002);
            l_TradedProductPrams1.setProductType(ProductTypeEnum.IFO);
            l_TradedProductPrams1.setValidUntilBizDate("20040719");
            TestDBUtility.insertWithDel(l_TradedProductPrams1);
            
            //IFO銘柄表
            TestDBUtility.deleteAll(IfoProductRow.TYPE);
            IfoProductParams l_ifoProductParams1 = TestDBUtility.getIfoProductRow();
            l_ifoProductParams1.setProductId(2605000000001L);
            l_ifoProductParams1.setProductType(ProductTypeEnum.IFO);
            l_ifoProductParams1.setInstitutionCode("0D");
            l_ifoProductParams1.setTargetMarketId(1002);
            TestDBUtility.insertWithDel(l_ifoProductParams1);

            //IFO取引銘柄銘柄表
            TestDBUtility.deleteAll(IfoTradedProductRow.TYPE);
            IfoTradedProductParams l_ifoTradedProductParams1 = TestDBUtility.getIfoTradedProductRow();
            l_ifoTradedProductParams1.setProductId(2605000000001L);
            l_ifoTradedProductParams1.setMarketId(1002);
            l_ifoTradedProductParams1.setUnitSize(250);
            l_ifoTradedProductParams1.setLiquidationPrice(290);
            l_ifoTradedProductParams1.setValidForBizDate("20040719");
            TestDBUtility.insertWithDel(l_ifoTradedProductParams1);
            
            WEB3IfoDepositCalcCondition l_ifoDepositCalcCondition = new WEB3IfoDepositCalcCondition();
            Date l_bizDate1 = WEB3DateUtility.getDate("20040715","yyyyMMdd");
            Date l_bizDate2 = WEB3DateUtility.getDate("20040716","yyyyMMdd");
            Date l_bizDate3 = WEB3DateUtility.getDate("20040719","yyyyMMdd");
            Date l_bizDate4 = WEB3DateUtility.getDate("20040720","yyyyMMdd");
            Date[] l_bizDates = {l_bizDate1, l_bizDate2, l_bizDate3, l_bizDate4};
            l_ifoDepositCalcCondition.setBizDates(l_bizDates);
            
            //IFO取引銘柄銘柄表一時
            TestDBUtility.deleteAll(IfoTradedProductUpdqRow.TYPE);
            IfoTradedProductUpdqParams l_ifoTradedProductUpdqParams = TestDBUtility.getIfoTradedProductUpdqRow();
            l_ifoTradedProductUpdqParams.setProductId(2605000000001L);
            l_ifoTradedProductUpdqParams.setMarketId(1002);
            l_ifoTradedProductUpdqParams.setInstitutionCode("0D");
            l_ifoTradedProductUpdqParams.setValidForBizDate("20040720");
            l_ifoTradedProductUpdqParams.setUnitSize(40000);
            l_ifoTradedProductUpdqParams.setLiquidationPrice(15);
            TestDBUtility.insertWithDel(l_ifoTradedProductUpdqParams);
            
            //リアル時価証拠金計算実施フラグ
            l_ifoDepositCalcCondition.setRealPriceIfoDepositCalcFlag(false);
            //証拠金不足メール送信済フラグ
            l_ifoDepositCalcCondition.setIfoDepositMailFlag(false);
            l_ifoDepositCalcCondition.addCalcConditionPerBranch("ifodeposit.profitloss.calc", "1");
            
            IfoTradedProductParams[] l_ifoTradedProductParams = new IfoTradedProductParams[1];
            l_ifoTradedProductParams[0] = l_ifoTradedProductParams1;
            
            WEB3IfoDepositCalc l_ifo = new WEB3IfoDepositCalc();
            
            Object[] obj = {l_ifoTradedProductParams};
            
            Field field = WEB3IfoDepositCalc.class.getDeclaredField("ifoDepositCalcCondition");
            field.setAccessible(true);
            field.set(l_ifo, l_ifoDepositCalcCondition);
            
            Method method = WEB3IfoDepositCalc.class.getDeclaredMethod( ////被測方法@所在的類名
                "createIfoProductList",  //<------------- 被測方法@名
                new Class[]{IfoTradedProductParams[].class});//< ------------ 方法@參數的類型
            
            method.setAccessible(true); //<-------------開關
            WEB3IfoProduct[] l_ifoProductRetrun =
                (WEB3IfoProduct[])method.invoke(l_ifo, obj);  //<---------return
            //compare
            assertEquals(1, l_ifoProductRetrun.length);
            assertEquals(250, l_ifoProductRetrun[0].getUnitSize(), 0);
            assertEquals(290, l_ifoProductRetrun[0].getCurrentPrice(), 0);
        }
        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage());
            fail();
        }
        log.exiting(STR_METHOD_NAME); 
    }
    //先物OP保有建玉情報が存在しない場合
    public void testCreateIfoSummaryContractPerProductContractPriceList_C0001()
    {
        final String STR_METHOD_NAME = "testCreateIfoSummaryContractPerProductContractPriceList_C0001()";
        log.entering(STR_METHOD_NAME);

        try
        {
            Method l_method = WEB3IfoDepositCalc.class.getDeclaredMethod(
                "createIfoSummaryContractPerProductContractPriceList",
                new Class[]{WEB3IfoContract[].class, WEB3IfoProduct[].class});
            l_method.setAccessible(true);
            l_method.invoke(depositCalc, new Object[]{null, null});
            assertNull(depositCalc.getIfoSummaryContractPerProductContractPriceList());
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }

        log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>test past");
        log.exiting(STR_METHOD_NAME);
    }

    //先物OP保有建玉情報一覧の要素数回LOOP処理
    //HashMapにKeyが存在しない時
    //当日建の場合、当日建数量を加算する
    //決済済建玉のみだった場合以外
    public void testCreateIfoSummaryContractPerProductContractPriceList_C0002()
    {
        final String STR_METHOD_NAME = "testCreateIfoSummaryContractPerProductContractPriceList_C0002()";
        log.entering(STR_METHOD_NAME);

        try
        {
            TestDBUtility.deleteAll(IfoProductRow.TYPE);
            IfoProductParams ifoProductParams = TestDBUtility.getIfoProductRow();
            TestDBUtility.insertWithDel(ifoProductParams);
            
            WEB3IfoContract[] l_ifoContractList = new WEB3IfoContract[]{new WEB3IfoContract()};
            l_ifoContractList[0].setProductId(1006160060005L);
            l_ifoContractList[0].setContractPrice(100);
            l_ifoContractList[0].setContractType(ContractTypeEnum.LONG);
            l_ifoContractList[0].setQuantity(100);
            l_ifoContractList[0].quantityTemp = 100;
            l_ifoContractList[0].setOpenDate(WEB3DateUtility.getDate("20080827", "yyyyMMdd"));
            Field field = WEB3IfoDepositCalc.class.getDeclaredField("ifoDepositCalcCondition");
            WEB3IfoDepositCalcCondition ifoDepositCalcCondition = new WEB3IfoDepositCalcCondition();
            Date[] dates =new Date[]{
                WEB3DateUtility.getDate("20080827", "yyyyMMdd"),
                WEB3DateUtility.getDate("20080827", "yyyyMMdd"),
                WEB3DateUtility.getDate("20080827", "yyyyMMdd"),
                WEB3DateUtility.getDate("20080827", "yyyyMMdd")};
            ifoDepositCalcCondition.setBizDates(dates);
            ifoDepositCalcCondition.setIfoDepositCalcBaseDate(1);
            ifoDepositCalcCondition.addCalcConditionPerBranch(
                WEB3IfoDepositBranchPrefNameDef.IFO_DEPOSIT_PROFITLOSS_CALC, "1");
            field.setAccessible(true);
            field.set(depositCalc, ifoDepositCalcCondition);

            WEB3IfoProduct[] products = new WEB3IfoProduct[]{new WEB3IfoProduct()};
            products[0].setProductId(1006160060005L);
            
            Method l_method = WEB3IfoDepositCalc.class.getDeclaredMethod(
                "createIfoSummaryContractPerProductContractPriceList",
                new Class[]{WEB3IfoContract[].class, WEB3IfoProduct[].class});
            l_method.setAccessible(true);
            l_method.invoke(depositCalc, new Object[]{l_ifoContractList, products});
            WEB3IfoSummaryContractPerProductContractPrice[] contractPrices =
                depositCalc.getIfoSummaryContractPerProductContractPriceList();
            assertEquals("" + contractPrices[0].getProductId(), "" + 1006160060005L);
            assertEquals("" + contractPrices[0].getBuyQuantity(), "" + 100.0);
            assertEquals("" + contractPrices[0].getTodayBuyQuantity(), "" + 100.0);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }

        log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>test past");
        log.exiting(STR_METHOD_NAME);
    }

    //先物OP保有建玉情報一覧の要素数回LOOP処理
    //HashMapにKeyが存在しない時
    //当日建の場合以外、当日建数量を加算するなし
    //決済済建玉のみだった場合以外
    public void testCreateIfoSummaryContractPerProductContractPriceList_C0003()
    {
        final String STR_METHOD_NAME = "testCreateIfoSummaryContractPerProductContractPriceList_C0003()";
        log.entering(STR_METHOD_NAME);

        try
        {
            TestDBUtility.deleteAll(IfoProductRow.TYPE);
            IfoProductParams ifoProductParams = TestDBUtility.getIfoProductRow();
            TestDBUtility.insertWithDel(ifoProductParams);
            
            WEB3IfoContract[] l_ifoContractList = new WEB3IfoContract[]{new WEB3IfoContract()};
            l_ifoContractList[0].setProductId(1006160060005L);
            l_ifoContractList[0].setContractPrice(100);
            l_ifoContractList[0].setContractType(ContractTypeEnum.LONG);
            l_ifoContractList[0].setQuantity(100);
            l_ifoContractList[0].quantityTemp = 100;
            l_ifoContractList[0].setOpenDate(WEB3DateUtility.getDate("20080828", "yyyyMMdd"));
            Field field = WEB3IfoDepositCalc.class.getDeclaredField("ifoDepositCalcCondition");
            WEB3IfoDepositCalcCondition ifoDepositCalcCondition = new WEB3IfoDepositCalcCondition();
            Date[] dates =new Date[]{
                WEB3DateUtility.getDate("20080827", "yyyyMMdd"),
                WEB3DateUtility.getDate("20080827", "yyyyMMdd"),
                WEB3DateUtility.getDate("20080827", "yyyyMMdd"),
                WEB3DateUtility.getDate("20080827", "yyyyMMdd")};
            ifoDepositCalcCondition.setBizDates(dates);
            ifoDepositCalcCondition.setIfoDepositCalcBaseDate(1);
            ifoDepositCalcCondition.addCalcConditionPerBranch(
                WEB3IfoDepositBranchPrefNameDef.IFO_DEPOSIT_PROFITLOSS_CALC, "1");
            field.setAccessible(true);
            field.set(depositCalc, ifoDepositCalcCondition);

            WEB3IfoProduct[] products = new WEB3IfoProduct[]{new WEB3IfoProduct()};
            products[0].setProductId(1006160060005L);
            
            Method l_method = WEB3IfoDepositCalc.class.getDeclaredMethod(
                "createIfoSummaryContractPerProductContractPriceList",
                new Class[]{WEB3IfoContract[].class, WEB3IfoProduct[].class});
            l_method.setAccessible(true);
            l_method.invoke(depositCalc, new Object[]{l_ifoContractList, products});
            WEB3IfoSummaryContractPerProductContractPrice[] contractPrices =
                depositCalc.getIfoSummaryContractPerProductContractPriceList();
            assertEquals("" + contractPrices[0].getProductId(), "" + 1006160060005L);
            assertEquals("" + contractPrices[0].getBuyQuantity(), "" + 100.0);
            assertEquals("" + contractPrices[0].getTodayBuyQuantity(), "" + 0.0);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }

        log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>test past");
        log.exiting(STR_METHOD_NAME);
    }

    //先物OP保有建玉情報一覧の要素数回LOOP処理
    //HashMapに既にKeyが存在する時
    //当日建の場合、当日建数量を加算する
    //決済済建玉のみだった場合以外
    public void testCreateIfoSummaryContractPerProductContractPriceList_C0004()
    {
        final String STR_METHOD_NAME = "testCreateIfoSummaryContractPerProductContractPriceList_C0004()";
        log.entering(STR_METHOD_NAME);

        try
        {
            TestDBUtility.deleteAll(IfoProductRow.TYPE);
            IfoProductParams ifoProductParams = TestDBUtility.getIfoProductRow();
            TestDBUtility.insertWithDel(ifoProductParams);
            
            WEB3IfoContract[] l_ifoContractList = new WEB3IfoContract[]{new WEB3IfoContract(),new WEB3IfoContract()};
            l_ifoContractList[0].setProductId(1006160060005L);
            l_ifoContractList[0].setContractPrice(100);
            l_ifoContractList[0].setContractType(ContractTypeEnum.LONG);
            l_ifoContractList[0].setQuantity(100);
            l_ifoContractList[0].quantityTemp = 100;
            l_ifoContractList[0].setOpenDate(WEB3DateUtility.getDate("20080827", "yyyyMMdd"));
            l_ifoContractList[1].setProductId(1006160060005L);
            l_ifoContractList[1].setContractPrice(100);
            l_ifoContractList[1].setContractType(ContractTypeEnum.LONG);
            l_ifoContractList[1].setQuantity(100);
            l_ifoContractList[1].quantityTemp = 100;
            l_ifoContractList[1].setOpenDate(WEB3DateUtility.getDate("20080827", "yyyyMMdd"));
            Field field = WEB3IfoDepositCalc.class.getDeclaredField("ifoDepositCalcCondition");
            WEB3IfoDepositCalcCondition ifoDepositCalcCondition = new WEB3IfoDepositCalcCondition();
            Date[] dates =new Date[]{
                WEB3DateUtility.getDate("20080827", "yyyyMMdd"),
                WEB3DateUtility.getDate("20080827", "yyyyMMdd"),
                WEB3DateUtility.getDate("20080827", "yyyyMMdd"),
                WEB3DateUtility.getDate("20080827", "yyyyMMdd")};
            ifoDepositCalcCondition.setBizDates(dates);
            ifoDepositCalcCondition.setIfoDepositCalcBaseDate(1);
            ifoDepositCalcCondition.addCalcConditionPerBranch(
                WEB3IfoDepositBranchPrefNameDef.IFO_DEPOSIT_PROFITLOSS_CALC, "1");
            field.setAccessible(true);
            field.set(depositCalc, ifoDepositCalcCondition);

            WEB3IfoProduct[] products = new WEB3IfoProduct[]{new WEB3IfoProduct()};
            products[0].setProductId(1006160060005L);
            
            Method l_method = WEB3IfoDepositCalc.class.getDeclaredMethod(
                "createIfoSummaryContractPerProductContractPriceList",
                new Class[]{WEB3IfoContract[].class, WEB3IfoProduct[].class});
            l_method.setAccessible(true);
            l_method.invoke(depositCalc, new Object[]{l_ifoContractList, products});
            WEB3IfoSummaryContractPerProductContractPrice[] contractPrices =
                depositCalc.getIfoSummaryContractPerProductContractPriceList();
            assertEquals("" + contractPrices[0].getProductId(), "" + 1006160060005L);
            assertEquals("" + contractPrices[0].getBuyQuantity(), "" + 200.0);
            assertEquals("" + contractPrices[0].getTodayBuyQuantity(), "" + 200.0);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }

        log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>test past");
        log.exiting(STR_METHOD_NAME);
    }

    //先物OP保有建玉情報一覧の要素数回LOOP処理
    //HashMapに既にKeyが存在する時
    //当日建の場合以外、当日建数量を加算するなし
    //決済済建玉のみだった場合以外
    public void testCreateIfoSummaryContractPerProductContractPriceList_C0005()
    {
        final String STR_METHOD_NAME = "testCreateIfoSummaryContractPerProductContractPriceList_C0005()";
        log.entering(STR_METHOD_NAME);

        try
        {
            TestDBUtility.deleteAll(IfoProductRow.TYPE);
            IfoProductParams ifoProductParams = TestDBUtility.getIfoProductRow();
            TestDBUtility.insertWithDel(ifoProductParams);
            
            WEB3IfoContract[] l_ifoContractList = new WEB3IfoContract[]{new WEB3IfoContract(),new WEB3IfoContract()};
            l_ifoContractList[0].setProductId(1006160060005L);
            l_ifoContractList[0].setContractPrice(100);
            l_ifoContractList[0].setContractType(ContractTypeEnum.LONG);
            l_ifoContractList[0].setQuantity(100);
            l_ifoContractList[0].quantityTemp = 100;
            l_ifoContractList[0].setOpenDate(WEB3DateUtility.getDate("20080828", "yyyyMMdd"));
            l_ifoContractList[1].setProductId(1006160060005L);
            l_ifoContractList[1].setContractPrice(100);
            l_ifoContractList[1].setContractType(ContractTypeEnum.LONG);
            l_ifoContractList[1].setQuantity(100);
            l_ifoContractList[1].quantityTemp = 100;
            l_ifoContractList[1].setOpenDate(WEB3DateUtility.getDate("20080828", "yyyyMMdd"));
            Field field = WEB3IfoDepositCalc.class.getDeclaredField("ifoDepositCalcCondition");
            WEB3IfoDepositCalcCondition ifoDepositCalcCondition = new WEB3IfoDepositCalcCondition();
            Date[] dates =new Date[]{
                WEB3DateUtility.getDate("20080827", "yyyyMMdd"),
                WEB3DateUtility.getDate("20080827", "yyyyMMdd"),
                WEB3DateUtility.getDate("20080827", "yyyyMMdd"),
                WEB3DateUtility.getDate("20080827", "yyyyMMdd")};
            ifoDepositCalcCondition.setBizDates(dates);
            ifoDepositCalcCondition.setIfoDepositCalcBaseDate(1);
            ifoDepositCalcCondition.addCalcConditionPerBranch(
                WEB3IfoDepositBranchPrefNameDef.IFO_DEPOSIT_PROFITLOSS_CALC, "1");
            field.setAccessible(true);
            field.set(depositCalc, ifoDepositCalcCondition);

            WEB3IfoProduct[] products = new WEB3IfoProduct[]{new WEB3IfoProduct()};
            products[0].setProductId(1006160060005L);
            
            Method l_method = WEB3IfoDepositCalc.class.getDeclaredMethod(
                "createIfoSummaryContractPerProductContractPriceList",
                new Class[]{WEB3IfoContract[].class, WEB3IfoProduct[].class});
            l_method.setAccessible(true);
            l_method.invoke(depositCalc, new Object[]{l_ifoContractList, products});
            WEB3IfoSummaryContractPerProductContractPrice[] contractPrices =
                depositCalc.getIfoSummaryContractPerProductContractPriceList();
            assertEquals("" + contractPrices[0].getProductId(), "" + 1006160060005L);
            assertEquals("" + contractPrices[0].getBuyQuantity(), "" + 200.0);
            assertEquals("" + contractPrices[0].getTodayBuyQuantity(), "" + 0.0);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }

        log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>test past");
        log.exiting(STR_METHOD_NAME);
    }
    
    //先物OP保有建玉情報一覧の要素数回LOOP処理
    //HashMapにKeyが存在しない時
    //当日建の場合以外、当日建数量を加算するなし
    //決済済建玉のみだった場合
    public void testCreateIfoSummaryContractPerProductContractPriceList_C0006()
    {
        final String STR_METHOD_NAME = "testCreateIfoSummaryContractPerProductContractPriceList_C0006()";
        log.entering(STR_METHOD_NAME);

        try
        {
            TestDBUtility.deleteAll(IfoProductRow.TYPE);
            IfoProductParams ifoProductParams = TestDBUtility.getIfoProductRow();
            TestDBUtility.insertWithDel(ifoProductParams);
            
            WEB3IfoContract[] l_ifoContractList = new WEB3IfoContract[0];
            Field field = WEB3IfoDepositCalc.class.getDeclaredField("ifoDepositCalcCondition");
            WEB3IfoDepositCalcCondition ifoDepositCalcCondition = new WEB3IfoDepositCalcCondition();
            Date[] dates =new Date[]{
                WEB3DateUtility.getDate("20080827", "yyyyMMdd"),
                WEB3DateUtility.getDate("20080827", "yyyyMMdd"),
                WEB3DateUtility.getDate("20080827", "yyyyMMdd"),
                WEB3DateUtility.getDate("20080827", "yyyyMMdd")};
            ifoDepositCalcCondition.setBizDates(dates);
            ifoDepositCalcCondition.setIfoDepositCalcBaseDate(1);
            ifoDepositCalcCondition.addCalcConditionPerBranch(
                WEB3IfoDepositBranchPrefNameDef.IFO_DEPOSIT_PROFITLOSS_CALC, "1");
            field.setAccessible(true);
            field.set(depositCalc, ifoDepositCalcCondition);

            WEB3IfoProduct[] products = new WEB3IfoProduct[]{new WEB3IfoProduct()};
            products[0].setProductId(1006160060005L);
            
            Method l_method = WEB3IfoDepositCalc.class.getDeclaredMethod(
                "createIfoSummaryContractPerProductContractPriceList",
                new Class[]{WEB3IfoContract[].class, WEB3IfoProduct[].class});
            l_method.setAccessible(true);
            l_method.invoke(depositCalc, new Object[]{l_ifoContractList, products});
            WEB3IfoSummaryContractPerProductContractPrice[] contractPrices =
                depositCalc.getIfoSummaryContractPerProductContractPriceList();
            
            assertNull(depositCalc.getIfoSummaryContractPerProductContractPriceList());
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }

        log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>test past");
        log.exiting(STR_METHOD_NAME);
    }


    //保有/注文先物OP銘柄が存在しない場合
    public void testCreateIfoSummaryContractPerProductList_C0001()
    {
        final String STR_METHOD_NAME = "testCreateIfoSummaryContractPerProductList_C0001()";
        log.entering(STR_METHOD_NAME);

        try
        {
            Method l_method = WEB3IfoDepositCalc.class.getDeclaredMethod(
                "createIfoSummaryContractPerProductList",
                new Class[]{WEB3IfoContract[].class, WEB3IfoTodayOpenOrder[].class, WEB3IfoProduct[].class});
            l_method.setAccessible(true);
            l_method.invoke(depositCalc, new Object[]{null, null, null});
            assertNull(depositCalc.getIfoSummaryContractPerProductList());
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }

        log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>test past");
        log.exiting(STR_METHOD_NAME);
    }

    //先物OP保有建玉情報が存在なしする時
    //先物OP当日新規建注文情報が存在なしする時
    //HashMapが空の時
    public void testCreateIfoSummaryContractPerProductList_C0002()
    {
        final String STR_METHOD_NAME = "testCreateIfoSummaryContractPerProductList_C0002()";
        log.entering(STR_METHOD_NAME);

        try
        {
            Field field = WEB3IfoDepositCalc.class.getDeclaredField("ifoDepositCalcCondition");
            WEB3IfoDepositCalcCondition ifoDepositCalcCondition = new WEB3IfoDepositCalcCondition();
            Date[] dates =new Date[]{
                WEB3DateUtility.getDate("20080827", "yyyyMMdd"),
                WEB3DateUtility.getDate("20080827", "yyyyMMdd"),
                WEB3DateUtility.getDate("20080827", "yyyyMMdd"),
                WEB3DateUtility.getDate("20080827", "yyyyMMdd")};
            ifoDepositCalcCondition.setBizDates(dates);
            ifoDepositCalcCondition.setIfoDepositCalcBaseDate(1);
            ifoDepositCalcCondition.addCalcConditionPerBranch(
                WEB3IfoDepositBranchPrefNameDef.IFO_DEPOSIT_PROFITLOSS_CALC, "1");
            field.setAccessible(true);
            field.set(depositCalc, ifoDepositCalcCondition);
            
            Method l_method = WEB3IfoDepositCalc.class.getDeclaredMethod(
                "createIfoSummaryContractPerProductList",
                new Class[]{WEB3IfoContract[].class, WEB3IfoTodayOpenOrder[].class, WEB3IfoProduct[].class});
            l_method.setAccessible(true);
            WEB3IfoProduct[] l_ifoProductList = new WEB3IfoProduct[]{};
            l_method.invoke(depositCalc, new Object[]{null, null, l_ifoProductList});
            assertNull(depositCalc.getIfoSummaryContractPerProductList());
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }

        log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>test past");
        log.exiting(STR_METHOD_NAME);
    }

    //先物OP保有建玉情報が存在する時
    //HashMapにKeyが存在しない時
    //先物OP当日新規建注文情報が存在する時
    //HashMapにKeyが存在しない時
    //HashMapが空の時以外の時
    public void testCreateIfoSummaryContractPerProductList_C0003()
    {
        final String STR_METHOD_NAME = "testCreateIfoSummaryContractPerProductList_C0003()";
        log.entering(STR_METHOD_NAME);

        try
        {
            TestDBUtility.deleteAll(CalendarRow.TYPE);
            TestDBUtility.deleteAll(TradingTimeRow.TYPE);
            TradingTimeParams tradingTimeParams = TestDBUtility.getTradingTimeRow();
            tradingTimeParams.setInstitutionCode("0D");
            tradingTimeParams.setBranchCode("123");
            tradingTimeParams.setMarketCode("N1");
            tradingTimeParams.setTradingTimeType("01");
            tradingTimeParams.setProductCode("00");
            tradingTimeParams.setBizDateType("1");
            TestDBUtility.insertWithDel(tradingTimeParams);
            
            WEB3IfoContract[] l_ifoContractList = new WEB3IfoContract[]{new WEB3IfoContract()};
            l_ifoContractList[0].setProductId(1006160060005L);
            l_ifoContractList[0].setContractPrice(100);
            l_ifoContractList[0].setContractType(ContractTypeEnum.LONG);
            l_ifoContractList[0].setQuantity(100);
            l_ifoContractList[0].quantityTemp = 100;
            l_ifoContractList[0].setOpenDate(WEB3DateUtility.getDate("20080827", "yyyyMMdd"));
            Field field = WEB3IfoDepositCalc.class.getDeclaredField("ifoDepositCalcCondition");
            WEB3IfoDepositCalcCondition ifoDepositCalcCondition = new WEB3IfoDepositCalcCondition();
            Date[] dates =new Date[]{
                WEB3DateUtility.getDate("20080827", "yyyyMMdd"),
                WEB3DateUtility.getDate("20080827", "yyyyMMdd"),
                WEB3DateUtility.getDate("20080827", "yyyyMMdd"),
                WEB3DateUtility.getDate("20080827", "yyyyMMdd")};
            ifoDepositCalcCondition.setBizDates(dates);
            ifoDepositCalcCondition.setIfoDepositCalcBaseDate(1);
            ifoDepositCalcCondition.addCalcConditionPerBranch(
                WEB3IfoDepositBranchPrefNameDef.IFO_DEPOSIT_PROFITLOSS_CALC, "1");
            field.setAccessible(true);
            field.set(depositCalc, ifoDepositCalcCondition);

            WEB3IfoTodayOpenOrder[] l_ifoTodayOpenOrderList = new WEB3IfoTodayOpenOrder[]{new WEB3IfoTodayOpenOrder()};
            l_ifoTodayOpenOrderList[0].setProductId(1006160060006L);
            l_ifoTodayOpenOrderList[0].setOrderBizDate(WEB3DateUtility.getDate("20080827", "yyyyMMdd"));
            l_ifoTodayOpenOrderList[0].setContractType(ContractTypeEnum.LONG);
            l_ifoTodayOpenOrderList[0].setQuantity(100);
            
            WEB3IfoProduct[] products = new WEB3IfoProduct[]{new WEB3IfoProduct(), new WEB3IfoProduct()};
            products[0].setProductId(1006160060005L);
            products[0].setUnderlyingProductCode("00");
            products[1].setProductId(1006160060006L);
            products[1].setUnderlyingProductCode("11");
            
            Method l_method = WEB3IfoDepositCalc.class.getDeclaredMethod(
                "createIfoSummaryContractPerProductList",
                new Class[]{WEB3IfoContract[].class, WEB3IfoTodayOpenOrder[].class, WEB3IfoProduct[].class});
            l_method.setAccessible(true);
            l_method.invoke(depositCalc, new Object[]{l_ifoContractList, l_ifoTodayOpenOrderList, products});
            WEB3IfoSummaryContractPerProduct[] perProducts = depositCalc.getIfoSummaryContractPerProductList();
            assertEquals(perProducts[0].getProductId() + "", 1006160060005L +"");
            assertEquals(perProducts[0].getBuyQuantity() + "", 100.0 +"");
            assertEquals(perProducts[0].getBuyQuantityTemp() + "", 100.0 +"");
            assertEquals(perProducts[1].getProductId() + "", 1006160060006L +"");
            assertEquals(perProducts[1].getCurrentBizDateBuyOrderQuantity() + "", 100.0 +"");
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }

        log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>test past");
        log.exiting(STR_METHOD_NAME);
    }

    //先物OP保有建玉情報が存在する時
    //HashMapにKeyが存在する時
    //先物OP当日新規建注文情報が存在する時
    //HashMapにKeyが存在する時
    //HashMapが空の時以外の時
    public void testCreateIfoSummaryContractPerProductList_C0004()
    {
        final String STR_METHOD_NAME = "testCreateIfoSummaryContractPerProductList_C0004()";
        log.entering(STR_METHOD_NAME);

        try
        {
            
            TestDBUtility.deleteAll(CalendarRow.TYPE);
            TestDBUtility.deleteAll(TradingTimeRow.TYPE);
            TradingTimeParams tradingTimeParams = TestDBUtility.getTradingTimeRow();
            tradingTimeParams.setInstitutionCode("0D");
            tradingTimeParams.setBranchCode("123");
            tradingTimeParams.setMarketCode("N1");
            tradingTimeParams.setTradingTimeType("01");
            tradingTimeParams.setProductCode("00");
            tradingTimeParams.setBizDateType("1");
            TestDBUtility.insertWithDel(tradingTimeParams);
            
            WEB3IfoContract[] l_ifoContractList = new WEB3IfoContract[]{new WEB3IfoContract(), new WEB3IfoContract()};
            l_ifoContractList[0].setProductId(1006160060005L);
            l_ifoContractList[0].setContractPrice(100);
            l_ifoContractList[0].setContractType(ContractTypeEnum.LONG);
            l_ifoContractList[0].setQuantity(100);
            l_ifoContractList[0].quantityTemp = 100;
            l_ifoContractList[0].setOpenDate(WEB3DateUtility.getDate("20080827", "yyyyMMdd"));
            l_ifoContractList[1].setProductId(1006160060005L);
            l_ifoContractList[1].setContractPrice(100);
            l_ifoContractList[1].setContractType(ContractTypeEnum.LONG);
            l_ifoContractList[1].setQuantity(100);
            l_ifoContractList[1].quantityTemp = 100;
            l_ifoContractList[1].setOpenDate(WEB3DateUtility.getDate("20080827", "yyyyMMdd"));
            Field field = WEB3IfoDepositCalc.class.getDeclaredField("ifoDepositCalcCondition");
            WEB3IfoDepositCalcCondition ifoDepositCalcCondition = new WEB3IfoDepositCalcCondition();
            Date[] dates =new Date[]{
                WEB3DateUtility.getDate("20080827", "yyyyMMdd"),
                WEB3DateUtility.getDate("20080827", "yyyyMMdd"),
                WEB3DateUtility.getDate("20080827", "yyyyMMdd"),
                WEB3DateUtility.getDate("20080827", "yyyyMMdd")};
            ifoDepositCalcCondition.setBizDates(dates);
            ifoDepositCalcCondition.setIfoDepositCalcBaseDate(1);
            ifoDepositCalcCondition.addCalcConditionPerBranch(
                WEB3IfoDepositBranchPrefNameDef.IFO_DEPOSIT_PROFITLOSS_CALC, "1");
            field.setAccessible(true);
            field.set(depositCalc, ifoDepositCalcCondition);

            WEB3IfoTodayOpenOrder[] l_ifoTodayOpenOrderList =
                new WEB3IfoTodayOpenOrder[]{new WEB3IfoTodayOpenOrder(), new WEB3IfoTodayOpenOrder()};
            l_ifoTodayOpenOrderList[0].setProductId(1006160060006L);
            l_ifoTodayOpenOrderList[0].setOrderBizDate(WEB3DateUtility.getDate("20080827", "yyyyMMdd"));
            l_ifoTodayOpenOrderList[0].setContractType(ContractTypeEnum.LONG);
            l_ifoTodayOpenOrderList[0].setQuantity(100);
            l_ifoTodayOpenOrderList[1].setProductId(1006160060006L);
            l_ifoTodayOpenOrderList[1].setOrderBizDate(WEB3DateUtility.getDate("20080827", "yyyyMMdd"));
            l_ifoTodayOpenOrderList[1].setContractType(ContractTypeEnum.LONG);
            l_ifoTodayOpenOrderList[1].setQuantity(100);
            
            WEB3IfoProduct[] products = new WEB3IfoProduct[]{new WEB3IfoProduct(), new WEB3IfoProduct()};
            products[0].setProductId(1006160060005L);
            products[0].setUnderlyingProductCode("00");
            products[1].setProductId(1006160060006L);
            products[1].setUnderlyingProductCode("11");
            
            Method l_method = WEB3IfoDepositCalc.class.getDeclaredMethod(
                "createIfoSummaryContractPerProductList",
                new Class[]{WEB3IfoContract[].class, WEB3IfoTodayOpenOrder[].class, WEB3IfoProduct[].class});
            l_method.setAccessible(true);
            l_method.invoke(depositCalc, new Object[]{l_ifoContractList, l_ifoTodayOpenOrderList, products});
            WEB3IfoSummaryContractPerProduct[] perProducts = depositCalc.getIfoSummaryContractPerProductList();
            assertEquals(perProducts[0].getProductId() + "", 1006160060005L +"");
            assertEquals(perProducts[0].getBuyQuantity() + "", 200.0 +"");
            assertEquals(perProducts[0].getBuyQuantityTemp() + "", 200.0 +"");
            assertEquals(perProducts[1].getProductId() + "", 1006160060006L +"");
            assertEquals(perProducts[1].getCurrentBizDateBuyOrderQuantity() + "", 200.0 +"");
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }

        log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>test past");
        log.exiting(STR_METHOD_NAME);
    }

    //保有/注文先物OP銘柄が存在しない場合
    public void testCreateIfoSummaryContractPerIndexList_C0001()
    {
        final String STR_METHOD_NAME = "testCreateIfoSummaryContractPerIndexList_C0001()";
        log.entering(STR_METHOD_NAME);

        try
        {
            Method l_method = WEB3IfoDepositCalc.class.getDeclaredMethod(
                "createIfoSummaryContractPerIndexList",
                new Class[]{WEB3IfoContract[].class, WEB3IfoTodayOpenOrder[].class, WEB3IfoProduct[].class});
            l_method.setAccessible(true);
            l_method.invoke(depositCalc, new Object[]{null, null, null});
            assertNull(depositCalc.getIfoSummaryContractPerIndexList());
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }

        log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>test past");
        log.exiting(STR_METHOD_NAME);
    }

    //先物OP保有建玉情報が存在なしする時
    //先物OP当日新規建注文情報が存在なしする時
    //HashMapが空の時
    public void testCreateIfoSummaryContractPerIndexList_C0002()
    {
        final String STR_METHOD_NAME = "testCreateIfoSummaryContractPerIndexList_C0002()";
        log.entering(STR_METHOD_NAME);

        try
        {
            Field field = WEB3IfoDepositCalc.class.getDeclaredField("ifoDepositCalcCondition");
            WEB3IfoDepositCalcCondition ifoDepositCalcCondition = new WEB3IfoDepositCalcCondition();
            Date[] dates =new Date[]{
                WEB3DateUtility.getDate("20080827", "yyyyMMdd"),
                WEB3DateUtility.getDate("20080827", "yyyyMMdd"),
                WEB3DateUtility.getDate("20080827", "yyyyMMdd"),
                WEB3DateUtility.getDate("20080827", "yyyyMMdd")};
            ifoDepositCalcCondition.setBizDates(dates);
            ifoDepositCalcCondition.setIfoDepositCalcBaseDate(1);
            ifoDepositCalcCondition.addCalcConditionPerBranch(
                WEB3IfoDepositBranchPrefNameDef.IFO_DEPOSIT_PROFITLOSS_CALC, "1");
            field.setAccessible(true);
            field.set(depositCalc, ifoDepositCalcCondition);
            
            Method l_method = WEB3IfoDepositCalc.class.getDeclaredMethod(
                "createIfoSummaryContractPerIndexList",
                new Class[]{WEB3IfoContract[].class, WEB3IfoTodayOpenOrder[].class, WEB3IfoProduct[].class});
            l_method.setAccessible(true);
            WEB3IfoProduct[] l_ifoProductList = new WEB3IfoProduct[]{};
            l_method.invoke(depositCalc, new Object[]{null, null, l_ifoProductList});
            assertNull(depositCalc.getIfoSummaryContractPerIndexList());
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }

        log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>test past");
        log.exiting(STR_METHOD_NAME);
    }

    //先物OP保有建玉情報が存在するする時
    //該当銘柄が、ポジションとして有効であるか判定する。
    //有効でない場合は、contiuneする。
    //先物OP当日新規建注文情報が存在なしする時
    //HashMapが空の時
    public void testCreateIfoSummaryContractPerIndexList_C0003()
    {
        final String STR_METHOD_NAME = "testCreateIfoSummaryContractPerIndexList_C0003()";
        log.entering(STR_METHOD_NAME);

        try
        {
            WEB3IfoContract[] l_ifoContractList = new WEB3IfoContract[]{new WEB3IfoContract()};
            l_ifoContractList[0].setProductId(1006160060005L);
            l_ifoContractList[0].setContractPrice(100);
            l_ifoContractList[0].setContractType(ContractTypeEnum.LONG);
            l_ifoContractList[0].setQuantity(100);
            l_ifoContractList[0].quantityTemp = 100;
            l_ifoContractList[0].setOpenDate(WEB3DateUtility.getDate("20080827", "yyyyMMdd"));

            WEB3IfoProduct[] l_ifoProductList = new WEB3IfoProduct[]{new WEB3IfoProduct(), new WEB3IfoProduct()};
            l_ifoProductList[0].setProductId(1006160060005L);
            l_ifoProductList[0].setLastTradingDate(WEB3DateUtility.getDate("20080727", "yyyyMMdd"));

            Field field = WEB3IfoDepositCalc.class.getDeclaredField("ifoDepositCalcCondition");
            WEB3IfoDepositCalcCondition ifoDepositCalcCondition = new WEB3IfoDepositCalcCondition();
            Date[] dates =new Date[]{
                WEB3DateUtility.getDate("20080827", "yyyyMMdd"),
                WEB3DateUtility.getDate("20080827", "yyyyMMdd"),
                WEB3DateUtility.getDate("20080827", "yyyyMMdd"),
                WEB3DateUtility.getDate("20080827", "yyyyMMdd")};
            ifoDepositCalcCondition.setBizDates(dates);
            ifoDepositCalcCondition.setIfoDepositCalcBaseDate(1);
            ifoDepositCalcCondition.addCalcConditionPerBranch(
                WEB3IfoDepositBranchPrefNameDef.IFO_DEPOSIT_PROFITLOSS_CALC, "1");
            ifoDepositCalcCondition.setIfodepositNonCalcSqProductFlag(true);
            field.setAccessible(true);
            field.set(depositCalc, ifoDepositCalcCondition);
            
            Method l_method = WEB3IfoDepositCalc.class.getDeclaredMethod(
                "createIfoSummaryContractPerIndexList",
                new Class[]{WEB3IfoContract[].class, WEB3IfoTodayOpenOrder[].class, WEB3IfoProduct[].class});
            l_method.setAccessible(true);
            l_method.invoke(depositCalc, new Object[]{l_ifoContractList, null, l_ifoProductList});
            assertNull(depositCalc.getIfoSummaryContractPerIndexList());
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }

        log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>test past");
        log.exiting(STR_METHOD_NAME);
    }

    //先物OP保有建玉情報が存在する時
    //HashMapにKeyが存在しない時
    //該当銘柄が、ポジションとして有効であるか判定する。
    //有効でない以外の場合
    //先物OP当日新規建注文情報が存在する時
    //HashMapにKeyが存在しない時
    //HashMapが空の時以外の時
    public void testCreateIfoSummaryContractPerIndexList_C0004()
    {
        final String STR_METHOD_NAME = "testCreateIfoSummaryContractPerIndexList_C0004()";
        log.entering(STR_METHOD_NAME);

        try
        {
            TestDBUtility.deleteAll(CalendarRow.TYPE);
            TestDBUtility.deleteAll(TradingTimeRow.TYPE);
            TradingTimeParams tradingTimeParams = TestDBUtility.getTradingTimeRow();
            tradingTimeParams.setInstitutionCode("0D");
            tradingTimeParams.setBranchCode("123");
            tradingTimeParams.setMarketCode("N1");
            tradingTimeParams.setTradingTimeType("01");
            tradingTimeParams.setProductCode("00");
            tradingTimeParams.setBizDateType("1");
            TestDBUtility.insertWithDel(tradingTimeParams);
            
            WEB3IfoContract[] l_ifoContractList = new WEB3IfoContract[]{new WEB3IfoContract()};
            l_ifoContractList[0].setProductId(1006160060005L);
            l_ifoContractList[0].setContractPrice(100);
            l_ifoContractList[0].setContractType(ContractTypeEnum.LONG);
            l_ifoContractList[0].setQuantity(100);
            l_ifoContractList[0].quantityTemp = 100;
            l_ifoContractList[0].setOpenDate(WEB3DateUtility.getDate("20080827", "yyyyMMdd"));
            Field field = WEB3IfoDepositCalc.class.getDeclaredField("ifoDepositCalcCondition");
            WEB3IfoDepositCalcCondition ifoDepositCalcCondition = new WEB3IfoDepositCalcCondition();
            Date[] dates =new Date[]{
                WEB3DateUtility.getDate("20080827", "yyyyMMdd"),
                WEB3DateUtility.getDate("20080827", "yyyyMMdd"),
                WEB3DateUtility.getDate("20080827", "yyyyMMdd"),
                WEB3DateUtility.getDate("20080827", "yyyyMMdd")};
            ifoDepositCalcCondition.setBizDates(dates);
            ifoDepositCalcCondition.setIfoDepositCalcBaseDate(1);
            ifoDepositCalcCondition.addCalcConditionPerBranch(
                WEB3IfoDepositBranchPrefNameDef.IFO_DEPOSIT_PROFITLOSS_CALC, "1");
            ifoDepositCalcCondition.setIfodepositNonCalcSqProductFlag(false);
            WEB3IfoDepositCalcConditionPerIndex[] l_ifoDepositCalcConditionPerIndexList = 
                new WEB3IfoDepositCalcConditionPerIndex[]{
                    new WEB3IfoDepositCalcConditionPerIndex(),
                    new WEB3IfoDepositCalcConditionPerIndex()};
            l_ifoDepositCalcConditionPerIndexList[0].setUnderlyingProductCode("00");
            l_ifoDepositCalcConditionPerIndexList[0].setIfoDepositPerUnit(1.0);
            l_ifoDepositCalcConditionPerIndexList[0].setIfoDepositPerUnitRed(1.1);
            l_ifoDepositCalcConditionPerIndexList[1].setUnderlyingProductCode("11");
            l_ifoDepositCalcConditionPerIndexList[1].setIfoDepositPerUnit(2.0);
            l_ifoDepositCalcConditionPerIndexList[1].setIfoDepositPerUnitRed(2.1);
            ifoDepositCalcCondition.setIfoDepositCalcPerIndexList(l_ifoDepositCalcConditionPerIndexList);
            field.setAccessible(true);
            field.set(depositCalc, ifoDepositCalcCondition);

            WEB3IfoTodayOpenOrder[] l_ifoTodayOpenOrderList = new WEB3IfoTodayOpenOrder[]{new WEB3IfoTodayOpenOrder()};
            l_ifoTodayOpenOrderList[0].setProductId(1006160060006L);
            l_ifoTodayOpenOrderList[0].setOrderBizDate(WEB3DateUtility.getDate("20080827", "yyyyMMdd"));
            l_ifoTodayOpenOrderList[0].setContractType(ContractTypeEnum.LONG);
            l_ifoTodayOpenOrderList[0].setQuantity(100);
            
            WEB3IfoProduct[] products = new WEB3IfoProduct[]{new WEB3IfoProduct(), new WEB3IfoProduct()};
            products[0].setProductId(1006160060005L);
            products[0].setUnderlyingProductCode("00");
            products[0].setIfoDerivativeType(IfoDerivativeTypeEnum.FUTURES);
            products[1].setProductId(1006160060006L);
            products[1].setUnderlyingProductCode("11");
            products[1].setIfoDerivativeType(IfoDerivativeTypeEnum.FUTURES);
            
            Method l_method = WEB3IfoDepositCalc.class.getDeclaredMethod(
                "createIfoSummaryContractPerIndexList",
                new Class[]{WEB3IfoContract[].class, WEB3IfoTodayOpenOrder[].class, WEB3IfoProduct[].class});
            l_method.setAccessible(true);
            l_method.invoke(depositCalc, new Object[]{l_ifoContractList, l_ifoTodayOpenOrderList, products});
            WEB3IfoSummaryContractPerIndex[] perIndexs = depositCalc.getIfoSummaryContractPerIndexList();
            assertEquals(perIndexs[0].getTargetProductCode(), "00");
            assertEquals(perIndexs[0].getFuturesBuyContractQuantity() + "", 100.0 +"");
            assertEquals(perIndexs[0].getFuturesBuyContractQuantityTemp() + "", 100.0 +"");
            assertEquals(perIndexs[0].getIfoDepositPerUnit() + "", 1.0 +"");
            assertEquals(perIndexs[0].getIfoDepositPerUnitRed() + "", 1.1 +"");
            assertEquals(perIndexs[1].getCurrentBizDateFuturesBuyOrderQuantity() + "", 100.0 +"");
            assertEquals(perIndexs[1].getTargetProductCode(), "11");
            assertEquals(perIndexs[1].getIfoDepositPerUnit() + "", 2.0 +"");
            assertEquals(perIndexs[1].getIfoDepositPerUnitRed() + "", 2.1 +"");
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }

        log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>test past");
        log.exiting(STR_METHOD_NAME);
    }

    //先物OP保有建玉情報が存在する時
    //HashMapにKeyが存在する時
    //先物OP当日新規建注文情報が存在する時
    //HashMapにKeyが存在する時
    //HashMapが空の時以外の時
    public void testCreateIfoSummaryContractPerIndexList_C0005()
    {
        final String STR_METHOD_NAME = "testCreateIfoSummaryContractPerIndexList_C0005()";
        log.entering(STR_METHOD_NAME);

        try
        {
            TestDBUtility.deleteAll(CalendarRow.TYPE);
            TestDBUtility.deleteAll(TradingTimeRow.TYPE);
            TradingTimeParams tradingTimeParams = TestDBUtility.getTradingTimeRow();
            tradingTimeParams.setInstitutionCode("0D");
            tradingTimeParams.setBranchCode("123");
            tradingTimeParams.setMarketCode("N1");
            tradingTimeParams.setTradingTimeType("01");
            tradingTimeParams.setProductCode("00");
            tradingTimeParams.setBizDateType("1");
            TestDBUtility.insertWithDel(tradingTimeParams);
            
            WEB3IfoContract[] l_ifoContractList = new WEB3IfoContract[]{new WEB3IfoContract(), new WEB3IfoContract()};
            l_ifoContractList[0].setProductId(1006160060005L);
            l_ifoContractList[0].setContractPrice(100);
            l_ifoContractList[0].setContractType(ContractTypeEnum.LONG);
            l_ifoContractList[0].setQuantity(100);
            l_ifoContractList[0].quantityTemp = 100;
            l_ifoContractList[0].setOpenDate(WEB3DateUtility.getDate("20080827", "yyyyMMdd"));
            l_ifoContractList[1].setProductId(1006160060005L);
            l_ifoContractList[1].setContractPrice(100);
            l_ifoContractList[1].setContractType(ContractTypeEnum.LONG);
            l_ifoContractList[1].setQuantity(100);
            l_ifoContractList[1].quantityTemp = 100;
            l_ifoContractList[1].setOpenDate(WEB3DateUtility.getDate("20080827", "yyyyMMdd"));
            Field field = WEB3IfoDepositCalc.class.getDeclaredField("ifoDepositCalcCondition");
            WEB3IfoDepositCalcCondition ifoDepositCalcCondition = new WEB3IfoDepositCalcCondition();
            Date[] dates =new Date[]{
                WEB3DateUtility.getDate("20080827", "yyyyMMdd"),
                WEB3DateUtility.getDate("20080827", "yyyyMMdd"),
                WEB3DateUtility.getDate("20080827", "yyyyMMdd"),
                WEB3DateUtility.getDate("20080827", "yyyyMMdd")};
            ifoDepositCalcCondition.setBizDates(dates);
            ifoDepositCalcCondition.setIfoDepositCalcBaseDate(1);
            ifoDepositCalcCondition.addCalcConditionPerBranch(
                WEB3IfoDepositBranchPrefNameDef.IFO_DEPOSIT_PROFITLOSS_CALC, "1");
            ifoDepositCalcCondition.setIfodepositNonCalcSqProductFlag(false);
            WEB3IfoDepositCalcConditionPerIndex[] l_ifoDepositCalcConditionPerIndexList = 
                new WEB3IfoDepositCalcConditionPerIndex[]{
                new WEB3IfoDepositCalcConditionPerIndex(),
                new WEB3IfoDepositCalcConditionPerIndex()};
            l_ifoDepositCalcConditionPerIndexList[0].setUnderlyingProductCode("00");
            l_ifoDepositCalcConditionPerIndexList[0].setIfoDepositPerUnit(1.0);
            l_ifoDepositCalcConditionPerIndexList[0].setIfoDepositPerUnitRed(1.1);
            l_ifoDepositCalcConditionPerIndexList[1].setUnderlyingProductCode("11");
            l_ifoDepositCalcConditionPerIndexList[1].setIfoDepositPerUnit(2.0);
            l_ifoDepositCalcConditionPerIndexList[1].setIfoDepositPerUnitRed(2.1);
            ifoDepositCalcCondition.setIfoDepositCalcPerIndexList(l_ifoDepositCalcConditionPerIndexList);
            field.setAccessible(true);
            field.set(depositCalc, ifoDepositCalcCondition);

            WEB3IfoTodayOpenOrder[] l_ifoTodayOpenOrderList =
                new WEB3IfoTodayOpenOrder[]{new WEB3IfoTodayOpenOrder(), new WEB3IfoTodayOpenOrder()};
            l_ifoTodayOpenOrderList[0].setProductId(1006160060006L);
            l_ifoTodayOpenOrderList[0].setOrderBizDate(WEB3DateUtility.getDate("20080827", "yyyyMMdd"));
            l_ifoTodayOpenOrderList[0].setContractType(ContractTypeEnum.LONG);
            l_ifoTodayOpenOrderList[0].setQuantity(100);
            l_ifoTodayOpenOrderList[1].setProductId(1006160060006L);
            l_ifoTodayOpenOrderList[1].setOrderBizDate(WEB3DateUtility.getDate("20080827", "yyyyMMdd"));
            l_ifoTodayOpenOrderList[1].setContractType(ContractTypeEnum.LONG);
            l_ifoTodayOpenOrderList[1].setQuantity(100);
            
            WEB3IfoProduct[] products = new WEB3IfoProduct[]{new WEB3IfoProduct(), new WEB3IfoProduct()};
            products[0].setProductId(1006160060005L);
            products[0].setUnderlyingProductCode("00");
            products[0].setIfoDerivativeType(IfoDerivativeTypeEnum.FUTURES);
            products[1].setProductId(1006160060006L);
            products[1].setUnderlyingProductCode("11");
            products[1].setIfoDerivativeType(IfoDerivativeTypeEnum.FUTURES);
            
            Method l_method = WEB3IfoDepositCalc.class.getDeclaredMethod(
                "createIfoSummaryContractPerIndexList",
                new Class[]{WEB3IfoContract[].class, WEB3IfoTodayOpenOrder[].class, WEB3IfoProduct[].class});
            l_method.setAccessible(true);
            l_method.invoke(depositCalc, new Object[]{l_ifoContractList, l_ifoTodayOpenOrderList, products});
            WEB3IfoSummaryContractPerIndex[] perIndexs = depositCalc.getIfoSummaryContractPerIndexList();
            assertEquals(perIndexs[0].getTargetProductCode(), "00");
            assertEquals(perIndexs[0].getFuturesBuyContractQuantity() + "", 200.0 +"");
            assertEquals(perIndexs[0].getFuturesBuyContractQuantityTemp() + "", 200.0 +"");
            assertEquals(perIndexs[0].getIfoDepositPerUnit() + "", 1.0 +"");
            assertEquals(perIndexs[0].getIfoDepositPerUnitRed() + "", 1.1 +"");
            assertEquals(perIndexs[1].getCurrentBizDateFuturesBuyOrderQuantity() + "", 200.0 +"");
            assertEquals(perIndexs[1].getTargetProductCode(), "11");
            assertEquals(perIndexs[1].getIfoDepositPerUnit() + "", 2.0 +"");
            assertEquals(perIndexs[1].getIfoDepositPerUnitRed() + "", 2.1 +"");
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }

        log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>test past");
        log.exiting(STR_METHOD_NAME);
    }
    
//    先物OP銘柄情報が存在しない時
//    返回null
    public void testGetIfoProductList_C001()
    {
        String STR_METHOD_NAME = "testGetIfoProductList_C001";
        log.entering(STR_METHOD_NAME);
        long l_lngProductId = 1L;
        WEB3IfoProduct[] l_ifoProductList = null;
        
        Object[] obj = {new Long(l_lngProductId), l_ifoProductList};
        
        try
        {
            Method method = WEB3IfoDepositCalc.class.getDeclaredMethod( ////被測方法@所在的類名
                    "getIfoProductList",  //<------------- 被測方法@名
                    new Class[]{long.class, WEB3IfoProduct[].class});//< ------------ 方法@參數的類型
                
                method.setAccessible(true); //<-------------開關
                WEB3IfoProduct[] l_ifoProductRetrun =
                    (WEB3IfoProduct[])method.invoke(depositCalc, obj);  //<---------return
                assertNull(l_ifoProductRetrun);
        }
        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage());
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
//    先物OP銘柄情報が存在する時
//    ArrayListの要素が空の時
//    先物OP銘柄情報.銘柄ID ！= 引数.銘柄IDの時
    public void testGetIfoProductList_C002()
    {
        String STR_METHOD_NAME = "testGetIfoProductList_C002";
        log.entering(STR_METHOD_NAME);
        log.exiting(STR_METHOD_NAME);
        long l_lngProductId = 1L;
        WEB3IfoProduct[] l_ifoProductList = new WEB3IfoProduct[1];
        
        l_ifoProductList[0] = new WEB3IfoProduct();
        l_ifoProductList[0].setProductId(2L);
        Object[] obj = {new Long(l_lngProductId), l_ifoProductList};
        try
        {
            Method method = WEB3IfoDepositCalc.class.getDeclaredMethod( ////被測方法@所在的類名
                    "getIfoProductList",  //<------------- 被測方法@名
                    new Class[]{long.class, WEB3IfoProduct[].class});//< ------------ 方法@參數的類型
                
                method.setAccessible(true); //<-------------開關
                WEB3IfoProduct[] l_ifoProductRetrun =
                    (WEB3IfoProduct[])method.invoke(depositCalc, obj);  //<---------return
                assertNull(l_ifoProductRetrun);
        }
        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage());
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
//    先物OP銘柄情報が存在する時
//    ArrayListの要素が空でないの時
//    先物OP銘柄情報.銘柄ID == 引数.銘柄IDの時
    public void testGetIfoProductList_C003()
    {
        String STR_METHOD_NAME = "testGetIfoProductList_C003";
        log.entering(STR_METHOD_NAME);
        log.exiting(STR_METHOD_NAME);
        long l_lngProductId = 1L;
        WEB3IfoProduct[] l_ifoProductList = new WEB3IfoProduct[1];
        
        l_ifoProductList[0] = new WEB3IfoProduct();
        l_ifoProductList[0].setProductId(1L);
        Object[] obj = {new Long(l_lngProductId), l_ifoProductList};
        try
        {
            Method method = WEB3IfoDepositCalc.class.getDeclaredMethod( ////被測方法@所在的類名
                    "getIfoProductList",  //<------------- 被測方法@名
                    new Class[]{long.class, WEB3IfoProduct[].class});//< ------------ 方法@參數的類型
                
                method.setAccessible(true); //<-------------開關
                WEB3IfoProduct[] l_ifoProductRetrun =
                    (WEB3IfoProduct[])method.invoke(depositCalc, obj);  //<---------return
                
                assertEquals(1,l_ifoProductRetrun.length);
                assertEquals(1L,l_ifoProductRetrun[0].getProductId());
                
        }
        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage());
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
//    先物OP銘柄情報が存在する時
//    ArrayListの要素が空でないの時
//    先物OP銘柄情報.銘柄ID == 引数.銘柄IDの時
    public void testGetIfoProductList_C004()
    {
        String STR_METHOD_NAME = "testGetIfoProductList_C004";
        log.entering(STR_METHOD_NAME);
        log.exiting(STR_METHOD_NAME);
        long l_lngProductId = 2L;
        WEB3IfoProduct[] l_ifoProductList = new WEB3IfoProduct[2];
        
        l_ifoProductList[0] = new WEB3IfoProduct();
        l_ifoProductList[0].setProductId(1L);
        l_ifoProductList[1] = new WEB3IfoProduct();
        l_ifoProductList[1].setProductId(2L);
        Object[] obj = {new Long(l_lngProductId), l_ifoProductList};
        try
        {
            Method method = WEB3IfoDepositCalc.class.getDeclaredMethod( ////被測方法@所在的類名
                    "getIfoProductList",  //<------------- 被測方法@名
                    new Class[]{long.class, WEB3IfoProduct[].class});//< ------------ 方法@參數的類型
                
                method.setAccessible(true); //<-------------開關
                WEB3IfoProduct[] l_ifoProductRetrun =
                    (WEB3IfoProduct[])method.invoke(depositCalc, obj);  //<---------return
                
                assertEquals(1,l_ifoProductRetrun.length);
                assertEquals(2L,l_ifoProductRetrun[0].getProductId());
                
        }
        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage());
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    //先物OP保有建玉情報が存在しない時
    public void testGetCurrentBizDateNetAmount_C001()
    {
        String STR_METHOD_NAME = "testGetCurrentBizDateNetAmount_C001";
        log.entering(STR_METHOD_NAME);
        
        long l_lngOrderUnitId = 1L;
        WEB3IfoContract[] l_ifoContractList = null;
        Date l_datOpenDate = WEB3DateUtility.getDate("20040716","yyyyMMdd");
        Object[] obj = {new Long(l_lngOrderUnitId), l_ifoContractList, l_datOpenDate};
        try
        {
            Method method = WEB3IfoDepositCalc.class.getDeclaredMethod( ////被測方法@所在的類名
                    "getCurrentBizDateNetAmount",  //<------------- 被測方法@名
                    new Class[]{long.class, WEB3IfoContract[].class, Date.class});//< ------------ 方法@參數的類型
                
                method.setAccessible(true); //<-------------開關
                Double l_dblReturn =
                    (Double)method.invoke(depositCalc, obj);  //<---------return
                assertEquals(0, l_dblReturn.doubleValue(), 0);    
        }
        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage());
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    //先物OP保有建玉情報が存在一條時
    public void testGetCurrentBizDateNetAmount_C002()
    {
        String STR_METHOD_NAME = "testGetCurrentBizDateNetAmount_C002";
        log.entering(STR_METHOD_NAME);
        try
        {
            //トランザクション表
            TestDBUtility.deleteAll(IfoFinTransactionRow.TYPE);
            IfoFinTransactionParams l_ifoFinTransactionParams = TestDBUtility.getIfoFinTransactionRow();
            l_ifoFinTransactionParams.setOrderId(1L);
            l_ifoFinTransactionParams.setOrderUnitId(1L);
            l_ifoFinTransactionParams.setContractId(1001);
            l_ifoFinTransactionParams.setDeliveryDate(WEB3DateUtility.getDate("20040720","yyyyMMdd"));
            l_ifoFinTransactionParams.setFinTransactionTimestamp(WEB3DateUtility.getDate("20040716","yyyyMMdd"));
            l_ifoFinTransactionParams.setQuantity(2.0);
            
            long l_lngOrderUnitId = 1L;
            WEB3IfoContract[] l_ifoContractList = new WEB3IfoContract[1];
            l_ifoContractList[0] = new WEB3IfoContract();
            l_ifoContractList[0].contractId = 1001;
            l_ifoContractList[0].openDate = WEB3DateUtility.getDate("20040716","yyyyMMdd");
            l_ifoContractList[0].addIfoFinTransaction(l_ifoFinTransactionParams);
            
            
            Date l_datOpenDate = WEB3DateUtility.getDate("20040716","yyyyMMdd");
            Object[] obj = {new Long(l_lngOrderUnitId), l_ifoContractList, l_datOpenDate};
            
            Method method = WEB3IfoDepositCalc.class.getDeclaredMethod( ////被測方法@所在的類名
                    "getCurrentBizDateNetAmount",  //<------------- 被測方法@名
                    new Class[]{long.class, WEB3IfoContract[].class, Date.class});//< ------------ 方法@參數的類型
                
                method.setAccessible(true); //<-------------開關
                Double l_dblReturn =
                    (Double)method.invoke(depositCalc, obj);  //<---------return
                assertEquals(5, l_dblReturn.doubleValue(), 0);    
        }
        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage());
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    //先物OP保有建玉情報が存在多條時
    public void testGetCurrentBizDateNetAmount_C003()
    {
        String STR_METHOD_NAME = "testGetCurrentBizDateNetAmount_C003";
        log.entering(STR_METHOD_NAME);
        try
        {
            //トランザクション表
            TestDBUtility.deleteAll(IfoFinTransactionRow.TYPE);
            IfoFinTransactionParams l_ifoFinTransactionParams = TestDBUtility.getIfoFinTransactionRow();
            l_ifoFinTransactionParams.setOrderId(1L);
            l_ifoFinTransactionParams.setOrderUnitId(1L);
            l_ifoFinTransactionParams.setContractId(1001);
            l_ifoFinTransactionParams.setDeliveryDate(WEB3DateUtility.getDate("20040720","yyyyMMdd"));
            l_ifoFinTransactionParams.setFinTransactionTimestamp(WEB3DateUtility.getDate("20040716","yyyyMMdd"));
            l_ifoFinTransactionParams.setQuantity(2.0);
            
            IfoFinTransactionParams l_ifoFinTransactionParams1 = TestDBUtility.getIfoFinTransactionRow();
            l_ifoFinTransactionParams1.setOrderId(1L);
            l_ifoFinTransactionParams1.setOrderUnitId(1L);
            l_ifoFinTransactionParams1.setContractId(1001);
            l_ifoFinTransactionParams1.setDeliveryDate(WEB3DateUtility.getDate("20040720","yyyyMMdd"));
            l_ifoFinTransactionParams1.setFinTransactionTimestamp(WEB3DateUtility.getDate("20040716","yyyyMMdd"));
            l_ifoFinTransactionParams1.setQuantity(2.0);
            l_ifoFinTransactionParams1.setNetAmount(3);
            
            long l_lngOrderUnitId = 1L;
            WEB3IfoContract[] l_ifoContractList = new WEB3IfoContract[2];
            l_ifoContractList[0] = new WEB3IfoContract();
            l_ifoContractList[1] = new WEB3IfoContract();
            l_ifoContractList[0].contractId = 1001;
            l_ifoContractList[0].openDate = WEB3DateUtility.getDate("20040716","yyyyMMdd");
            l_ifoContractList[0].addIfoFinTransaction(l_ifoFinTransactionParams);
            
            l_ifoContractList[1].contractId = 1002;
            l_ifoContractList[1].openDate = WEB3DateUtility.getDate("20040716","yyyyMMdd");
            l_ifoContractList[1].addIfoFinTransaction(l_ifoFinTransactionParams1);
            
            
            Date l_datOpenDate = WEB3DateUtility.getDate("20040716","yyyyMMdd");
            Object[] obj = {new Long(l_lngOrderUnitId), l_ifoContractList, l_datOpenDate};
            
            Method method = WEB3IfoDepositCalc.class.getDeclaredMethod( ////被測方法@所在的類名
                    "getCurrentBizDateNetAmount",  //<------------- 被測方法@名
                    new Class[]{long.class, WEB3IfoContract[].class, Date.class});//< ------------ 方法@參數的類型
                
                method.setAccessible(true); //<-------------開關
                Double l_dblReturn =
                    (Double)method.invoke(depositCalc, obj);  //<---------return
                assertEquals(8, l_dblReturn.doubleValue(), 0);    
        }
        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage());
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    //SPAN使用不可の場合，返回-1
    public void testGetSPANIfoDeposit_C001()
    {
        String STR_METHOD_NAME = "testGetSPANIfoDeposit_C001";
        log.entering(STR_METHOD_NAME);
        try
        {
            WEB3IfoDepositCalcCondition l_ifoDepositCalcCondition = new WEB3IfoDepositCalcCondition();
            Date l_bizDate1 = WEB3DateUtility.getDate("20040715","yyyyMMdd");
            Date l_bizDate2 = WEB3DateUtility.getDate("20040716","yyyyMMdd");
            Date l_bizDate3 = WEB3DateUtility.getDate("20040719","yyyyMMdd");
            Date l_bizDate4 = WEB3DateUtility.getDate("20040720","yyyyMMdd");
            Date[] l_bizDates = {l_bizDate1, l_bizDate2, l_bizDate3, l_bizDate4};
            l_ifoDepositCalcCondition.setBizDates(l_bizDates);
            l_ifoDepositCalcCondition.setSimpleSPANCalcFlag(true);
            
            //輔助口座表
            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
            l_subAccountParams.setAccountId(111101111010L);
            l_subAccountParams.setSubAccountId(11110111101001L);
            TestDBUtility.insertWithDel(l_subAccountParams);
            WEB3GentradeSubAccount l_subAccount =  new WEB3GentradeSubAccount(l_subAccountParams);
            
            WEB3IfoSummaryContractPerProduct[] l_ifoSummaryContractPerProductList
                = new WEB3IfoSummaryContractPerProduct[1];
            l_ifoSummaryContractPerProductList[0] = new WEB3IfoSummaryContractPerProduct();
            
            int l_intReservedDate = 0;
            Object[] obj = {new Integer(l_intReservedDate)};
            Field field1 = WEB3IfoDepositCalc.class.getDeclaredField("ifoDepositCalcCondition");
            Field field2 = WEB3IfoDepositCalc.class.getDeclaredField("ifoSummaryContractPerProductList");
            Field field3 = WEB3IfoDepositCalc.class.getDeclaredField("subAccount");
            
            field1.setAccessible(true);
            field2.setAccessible(true);
            field3.setAccessible(true);
            field1.set(depositCalc, l_ifoDepositCalcCondition);
            field2.set(depositCalc, l_ifoSummaryContractPerProductList);
            field3.set(depositCalc, l_subAccount);
            Method method = WEB3IfoDepositCalc.class.getDeclaredMethod( ////被測方法@所在的類名
                    "getSPANIfoDeposit",  //<------------- 被測方法@名
                    new Class[]{int.class});//< ------------ 方法@參數的類型
                
            method.setAccessible(true); //<-------------開關
            method.invoke(depositCalc, obj);  //<---------return
            double l_return = depositCalc.getSPANIfoDeposit(l_intReservedDate);
            assertEquals(-1, l_return, 0);
        }
        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage());
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
//    指定日が0、1、2でない時，返回0
//    建玉が存在場合
    public void testGetSPANIfoDeposit_C002()
    {
        String STR_METHOD_NAME = "testGetSPANIfoDeposit_C002";
        log.entering(STR_METHOD_NAME);
        try
        {
            WEB3IfoDepositCalcCondition l_ifoDepositCalcCondition = new WEB3IfoDepositCalcCondition();
            Date l_bizDate1 = WEB3DateUtility.getDate("20040715","yyyyMMdd");
            Date l_bizDate2 = WEB3DateUtility.getDate("20040716","yyyyMMdd");
            Date l_bizDate3 = WEB3DateUtility.getDate("20040719","yyyyMMdd");
            Date l_bizDate4 = WEB3DateUtility.getDate("20040720","yyyyMMdd");
            Date[] l_bizDates = {l_bizDate1, l_bizDate2, l_bizDate3, l_bizDate4};
            l_ifoDepositCalcCondition.setBizDates(l_bizDates);
            l_ifoDepositCalcCondition.setSimpleSPANCalcFlag(false);
            l_ifoDepositCalcCondition.setSpanTroubleFlag(false);
            
            //輔助口座表
            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
            l_subAccountParams.setAccountId(111101111010L);
            l_subAccountParams.setSubAccountId(11110111101001L);
            TestDBUtility.insertWithDel(l_subAccountParams);
            WEB3GentradeSubAccount l_subAccount =  new WEB3GentradeSubAccount(l_subAccountParams);
            
            WEB3IfoSummaryContractPerProduct[] l_ifoSummaryContractPerProductList
                = new WEB3IfoSummaryContractPerProduct[1];
            l_ifoSummaryContractPerProductList[0] = new WEB3IfoSummaryContractPerProduct();
            l_ifoSummaryContractPerProductList[0].setBuyQuantity(1000);
            l_ifoSummaryContractPerProductList[0].setCurrentBizDateLiquidationPrice(20);
            
            int l_intReservedDate = 4;
            Object[] obj = {new Integer(l_intReservedDate)};
            Field field1 = WEB3IfoDepositCalc.class.getDeclaredField("ifoDepositCalcCondition");
            Field field2 = WEB3IfoDepositCalc.class.getDeclaredField("ifoSummaryContractPerProductList");
            Field field3 = WEB3IfoDepositCalc.class.getDeclaredField("subAccount");
            
            field1.setAccessible(true);
            field2.setAccessible(true);
            field3.setAccessible(true);
            field1.set(depositCalc, l_ifoDepositCalcCondition);
            field2.set(depositCalc, l_ifoSummaryContractPerProductList);
            field3.set(depositCalc, l_subAccount);
            Method method = WEB3IfoDepositCalc.class.getDeclaredMethod( ////被測方法@所在的類名
                    "getSPANIfoDeposit",  //<------------- 被測方法@名
                    new Class[]{int.class});//< ------------ 方法@參數的類型
                
            method.setAccessible(true); //<-------------開關
            method.invoke(depositCalc, obj);  //<---------return
            double l_return = depositCalc.getSPANIfoDeposit(l_intReservedDate);
            assertEquals(0, l_return, 0);
        }
        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage());
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
//    指定日が0、1、2でなあること時
//    建玉が存在しない場合，返回0
    public void testGetSPANIfoDeposit_C003()
    {
        String STR_METHOD_NAME = "testGetSPANIfoDeposit_C003";
        log.entering(STR_METHOD_NAME);
        try
        {
            WEB3IfoDepositCalcCondition l_ifoDepositCalcCondition = new WEB3IfoDepositCalcCondition();
            Date l_bizDate1 = WEB3DateUtility.getDate("20040715","yyyyMMdd");
            Date l_bizDate2 = WEB3DateUtility.getDate("20040716","yyyyMMdd");
            Date l_bizDate3 = WEB3DateUtility.getDate("20040719","yyyyMMdd");
            Date l_bizDate4 = WEB3DateUtility.getDate("20040720","yyyyMMdd");
            Date[] l_bizDates = {l_bizDate1, l_bizDate2, l_bizDate3, l_bizDate4};
            l_ifoDepositCalcCondition.setBizDates(l_bizDates);
            l_ifoDepositCalcCondition.setSimpleSPANCalcFlag(false);
            l_ifoDepositCalcCondition.setSpanTroubleFlag(false);
            
            //輔助口座表
            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
            l_subAccountParams.setAccountId(111101111010L);
            l_subAccountParams.setSubAccountId(11110111101001L);
            TestDBUtility.insertWithDel(l_subAccountParams);
            WEB3GentradeSubAccount l_subAccount =  new WEB3GentradeSubAccount(l_subAccountParams);
            
            WEB3IfoSummaryContractPerProduct[] l_ifoSummaryContractPerProductList = null;
            
            int l_intReservedDate = 2;
            Object[] obj = {new Integer(l_intReservedDate)};
            Field field1 = WEB3IfoDepositCalc.class.getDeclaredField("ifoDepositCalcCondition");
            Field field2 = WEB3IfoDepositCalc.class.getDeclaredField("ifoSummaryContractPerProductList");
            Field field3 = WEB3IfoDepositCalc.class.getDeclaredField("subAccount");
            
            field1.setAccessible(true);
            field2.setAccessible(true);
            field3.setAccessible(true);
            field1.set(depositCalc, l_ifoDepositCalcCondition);
            field2.set(depositCalc, l_ifoSummaryContractPerProductList);
            field3.set(depositCalc, l_subAccount);
            Method method = WEB3IfoDepositCalc.class.getDeclaredMethod( ////被測方法@所在的類名
                    "getSPANIfoDeposit",  //<------------- 被測方法@名
                    new Class[]{int.class});//< ------------ 方法@參數的類型
                
            method.setAccessible(true); //<-------------開關
            method.invoke(depositCalc, obj);  //<---------return
            double l_return = depositCalc.getSPANIfoDeposit(l_intReservedDate);
            assertEquals(0, l_return, 0);
        }
        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage());
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    //銘柄別先物OP建玉集計の要素数回LOOP処理(一條數據)
    public void testGetSPANIfoDeposit_C004()
    {
        String STR_METHOD_NAME = "testGetSPANIfoDeposit_C004";
        log.entering(STR_METHOD_NAME);
        try
        {
            WEB3IfoDepositCalcCondition l_ifoDepositCalcCondition = new WEB3IfoDepositCalcCondition();
            Date l_bizDate1 = WEB3DateUtility.getDate("20040715","yyyyMMdd");
            Date l_bizDate2 = WEB3DateUtility.getDate("20040716","yyyyMMdd");
            Date l_bizDate3 = WEB3DateUtility.getDate("20040719","yyyyMMdd");
            Date l_bizDate4 = WEB3DateUtility.getDate("20040720","yyyyMMdd");
            Date[] l_bizDates = {l_bizDate1, l_bizDate2, l_bizDate3, l_bizDate4};
            l_ifoDepositCalcCondition.setBizDates(l_bizDates);
            l_ifoDepositCalcCondition.setSimpleSPANCalcFlag(false);
            l_ifoDepositCalcCondition.setSpanTroubleFlag(false);
            
            //輔助口座表
            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
            l_subAccountParams.setAccountId(111101111010L);
            l_subAccountParams.setSubAccountId(11110111101001L);
            TestDBUtility.insertWithDel(l_subAccountParams);
            WEB3GentradeSubAccount l_subAccount =  new WEB3GentradeSubAccount(l_subAccountParams);
            
            WEB3IfoSummaryContractPerProduct[] l_ifoSummaryContractPerProductList
                = new WEB3IfoSummaryContractPerProduct[1];
            l_ifoSummaryContractPerProductList[0] = new WEB3IfoSummaryContractPerProduct();
            l_ifoSummaryContractPerProductList[0].setBuyQuantity(1000);
            l_ifoSummaryContractPerProductList[0].setCurrentBizDateLiquidationPrice(20);
            
            int l_intReservedDate = 2;
            Object[] obj = {new Integer(l_intReservedDate)};
            Field field1 = WEB3IfoDepositCalc.class.getDeclaredField("ifoDepositCalcCondition");
            Field field2 = WEB3IfoDepositCalc.class.getDeclaredField("ifoSummaryContractPerProductList");
            Field field3 = WEB3IfoDepositCalc.class.getDeclaredField("subAccount");
            
            field1.setAccessible(true);
            field2.setAccessible(true);
            field3.setAccessible(true);
            field1.set(depositCalc, l_ifoDepositCalcCondition);
            field2.set(depositCalc, l_ifoSummaryContractPerProductList);
            field3.set(depositCalc, l_subAccount);
            Method method = WEB3IfoDepositCalc.class.getDeclaredMethod( ////被測方法@所在的類名
                    "getSPANIfoDeposit",  //<------------- 被測方法@名
                    new Class[]{int.class});//< ------------ 方法@參數的類型
                
            method.setAccessible(true); //<-------------開關
            method.invoke(depositCalc, obj);  //<---------return
            double l_return = depositCalc.getSPANIfoDeposit(l_intReservedDate);
            assertEquals(0, l_return, 0);
        }
        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage());
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    //銘柄別先物OP建玉集計の要素数回LOOP処理(多條數據)
    public void testGetSPANIfoDeposit_C005()
    {
        String STR_METHOD_NAME = "testGetSPANIfoDeposit_C005";
        log.entering(STR_METHOD_NAME);
        try
        {
            WEB3IfoDepositCalcCondition l_ifoDepositCalcCondition = new WEB3IfoDepositCalcCondition();
            Date l_bizDate1 = WEB3DateUtility.getDate("20040715","yyyyMMdd");
            Date l_bizDate2 = WEB3DateUtility.getDate("20040716","yyyyMMdd");
            Date l_bizDate3 = WEB3DateUtility.getDate("20040719","yyyyMMdd");
            Date l_bizDate4 = WEB3DateUtility.getDate("20040720","yyyyMMdd");
            Date[] l_bizDates = {l_bizDate1, l_bizDate2, l_bizDate3, l_bizDate4};
            l_ifoDepositCalcCondition.setBizDates(l_bizDates);
            l_ifoDepositCalcCondition.setSimpleSPANCalcFlag(false);
            l_ifoDepositCalcCondition.setSpanTroubleFlag(false);
            
            //輔助口座表
            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
            l_subAccountParams.setAccountId(111101111010L);
            l_subAccountParams.setSubAccountId(11110111101001L);
            TestDBUtility.insertWithDel(l_subAccountParams);
            WEB3GentradeSubAccount l_subAccount =  new WEB3GentradeSubAccount(l_subAccountParams);
            
            WEB3IfoSummaryContractPerProduct[] l_ifoSummaryContractPerProductList
                = new WEB3IfoSummaryContractPerProduct[2];
            l_ifoSummaryContractPerProductList[0] = new WEB3IfoSummaryContractPerProduct();
            l_ifoSummaryContractPerProductList[1] = new WEB3IfoSummaryContractPerProduct();
            l_ifoSummaryContractPerProductList[0].setBuyQuantity(1000);
            l_ifoSummaryContractPerProductList[0].setCurrentBizDateLiquidationPrice(20);
            l_ifoSummaryContractPerProductList[1].setBuyQuantity(2000);
            l_ifoSummaryContractPerProductList[1].setCurrentBizDateLiquidationPrice(10);
            
            int l_intReservedDate = 2;
            Object[] obj = {new Integer(l_intReservedDate)};
            Field field1 = WEB3IfoDepositCalc.class.getDeclaredField("ifoDepositCalcCondition");
            Field field2 = WEB3IfoDepositCalc.class.getDeclaredField("ifoSummaryContractPerProductList");
            Field field3 = WEB3IfoDepositCalc.class.getDeclaredField("subAccount");
            
            field1.setAccessible(true);
            field2.setAccessible(true);
            field3.setAccessible(true);
            field1.set(depositCalc, l_ifoDepositCalcCondition);
            field2.set(depositCalc, l_ifoSummaryContractPerProductList);
            field3.set(depositCalc, l_subAccount);
            Method method = WEB3IfoDepositCalc.class.getDeclaredMethod( ////被測方法@所在的類名
                    "getSPANIfoDeposit",  //<------------- 被測方法@名
                    new Class[]{int.class});//< ------------ 方法@參數的類型
                
            method.setAccessible(true); //<-------------開關
            method.invoke(depositCalc, obj);  //<---------return
            double l_return = depositCalc.getSPANIfoDeposit(l_intReservedDate);
            assertEquals(0, l_return, 0);
        }
        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage());
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
//    銘柄別先物OP建玉集計の要素数回LOOP処理
//    指定日 = 0
    public void testGetSPANIfoDeposit_C006()
    {
        String STR_METHOD_NAME = "testGetSPANIfoDeposit_C006";
        log.entering(STR_METHOD_NAME);
        try
        {
            WEB3IfoDepositCalcCondition l_ifoDepositCalcCondition = new WEB3IfoDepositCalcCondition();
            Date l_bizDate1 = WEB3DateUtility.getDate("20040715","yyyyMMdd");
            Date l_bizDate2 = WEB3DateUtility.getDate("20040716","yyyyMMdd");
            Date l_bizDate3 = WEB3DateUtility.getDate("20040719","yyyyMMdd");
            Date l_bizDate4 = WEB3DateUtility.getDate("20040720","yyyyMMdd");
            Date[] l_bizDates = {l_bizDate1, l_bizDate2, l_bizDate3, l_bizDate4};
            l_ifoDepositCalcCondition.setBizDates(l_bizDates);
            l_ifoDepositCalcCondition.setSimpleSPANCalcFlag(false);
            l_ifoDepositCalcCondition.setSpanTroubleFlag(false);
            
            //輔助口座表
            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
            l_subAccountParams.setAccountId(111101111010L);
            l_subAccountParams.setSubAccountId(11110111101001L);
            TestDBUtility.insertWithDel(l_subAccountParams);
            WEB3GentradeSubAccount l_subAccount =  new WEB3GentradeSubAccount(l_subAccountParams);
            
            WEB3IfoSummaryContractPerProduct[] l_ifoSummaryContractPerProductList
                = new WEB3IfoSummaryContractPerProduct[2];
            l_ifoSummaryContractPerProductList[0] = new WEB3IfoSummaryContractPerProduct();
            l_ifoSummaryContractPerProductList[1] = new WEB3IfoSummaryContractPerProduct();
            l_ifoSummaryContractPerProductList[0].setBuyQuantity(1000);
            l_ifoSummaryContractPerProductList[0].setCurrentBizDateLiquidationPrice(20);
            l_ifoSummaryContractPerProductList[1].setBuyQuantity(2000);
            l_ifoSummaryContractPerProductList[1].setCurrentBizDateLiquidationPrice(10);
            
            int l_intReservedDate = 0;
            Object[] obj = {new Integer(l_intReservedDate)};
            Field field1 = WEB3IfoDepositCalc.class.getDeclaredField("ifoDepositCalcCondition");
            Field field2 = WEB3IfoDepositCalc.class.getDeclaredField("ifoSummaryContractPerProductList");
            Field field3 = WEB3IfoDepositCalc.class.getDeclaredField("subAccount");
            
            field1.setAccessible(true);
            field2.setAccessible(true);
            field3.setAccessible(true);
            field1.set(depositCalc, l_ifoDepositCalcCondition);
            field2.set(depositCalc, l_ifoSummaryContractPerProductList);
            field3.set(depositCalc, l_subAccount);
            Method method = WEB3IfoDepositCalc.class.getDeclaredMethod( ////被測方法@所在的類名
                    "getSPANIfoDeposit",  //<------------- 被測方法@名
                    new Class[]{int.class});//< ------------ 方法@參數的類型
                
            method.setAccessible(true); //<-------------開關
            method.invoke(depositCalc, obj);  //<---------return
            double l_return = depositCalc.getSPANIfoDeposit(l_intReservedDate);
            assertEquals(0, l_return, 0);
        }
        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage());
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    //正確的「当日請求額」を返却する
    public void testGetCurrentBizDateDemandAmount()
    {
        String STR_METHOD_NAME = "testGetCurrentBizDateDemandAmount";
        log.entering(STR_METHOD_NAME);
        try
        {
            WEB3IfoDepositCalcCondition l_ifoDepositCalcCondition = new WEB3IfoDepositCalcCondition();
            Date l_bizDate1 = WEB3DateUtility.getDate("20040715","yyyyMMdd");
            Date l_bizDate2 = WEB3DateUtility.getDate("20040716","yyyyMMdd");
            Date l_bizDate3 = WEB3DateUtility.getDate("20040719","yyyyMMdd");
            Date l_bizDate4 = WEB3DateUtility.getDate("20040720","yyyyMMdd");
            Date[] l_bizDates = {l_bizDate1, l_bizDate2, l_bizDate3, l_bizDate4};
            l_ifoDepositCalcCondition.setBizDates(l_bizDates);
            l_ifoDepositCalcCondition.setPreBizDateInfoDepositLackCharge(251);
            
            Field field = WEB3IfoDepositCalc.class.getDeclaredField("ifoDepositCalcCondition");
            field.setAccessible(true);
            field.set(depositCalc, l_ifoDepositCalcCondition);
            double l_dblReturn = depositCalc.getCurrentBizDateDemandAmount();
            assertEquals(251, l_dblReturn, 0);
        }
        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage());
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    //正確的「証拠金残高」を返却する
    public void testCalcIfoDepositBalance()
    {
        String STR_METHOD_NAME = "testCalcIfoDepositBalance";
        log.entering(STR_METHOD_NAME);
        try
        {
            WEB3IfoDepositCalcCondition l_ifoDepositCalcCondition = new WEB3IfoDepositCalcCondition();
            Date l_bizDate1 = WEB3DateUtility.getDate("20040715","yyyyMMdd");
            Date l_bizDate2 = WEB3DateUtility.getDate("20040716","yyyyMMdd");
            Date l_bizDate3 = WEB3DateUtility.getDate("20040719","yyyyMMdd");
            Date l_bizDate4 = WEB3DateUtility.getDate("20040720","yyyyMMdd");
            Date[] l_bizDates = {l_bizDate1, l_bizDate2, l_bizDate3, l_bizDate4};
            l_ifoDepositCalcCondition.setBizDates(l_bizDates);
            l_ifoDepositCalcCondition.setIfoDepositCalcBaseDate(0);
            
            WEB3IfoCustomerTransfer l_ifoCustomerTransfer = new WEB3IfoCustomerTransfer();
            double[] l_balances = {2, 5 ,6}; 
            l_ifoCustomerTransfer.setBalances(l_balances);
            l_ifoCustomerTransfer.setCurrentBizDateTransferAmount(3);
            
            Field field1 = WEB3IfoDepositCalc.class.getDeclaredField("ifoCustomerTransfer");
            field1.setAccessible(true);
            field1.set(depositCalc, l_ifoCustomerTransfer);
            
            Field field2 = WEB3IfoDepositCalc.class.getDeclaredField("ifoDepositCalcCondition");
            field2.setAccessible(true);
            field2.set(depositCalc, l_ifoDepositCalcCondition);
            
            double l_dblReturn = depositCalc.calcIfoDepositBalance();
            assertEquals(5, l_dblReturn, 0);
        }
        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage());
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    //0を返却する
    public void testCalcIfoDepositBalance_C001()
    {
        String STR_METHOD_NAME = "testCalcIfoDepositBalance_C001";
        log.entering(STR_METHOD_NAME);
        try
        {
            WEB3IfoDepositCalcCondition l_ifoDepositCalcCondition = new WEB3IfoDepositCalcCondition();
            Date l_bizDate1 = WEB3DateUtility.getDate("20040715","yyyyMMdd");
            Date l_bizDate2 = WEB3DateUtility.getDate("20040716","yyyyMMdd");
            Date l_bizDate3 = WEB3DateUtility.getDate("20040719","yyyyMMdd");
            Date l_bizDate4 = WEB3DateUtility.getDate("20040720","yyyyMMdd");
            Date[] l_bizDates = {l_bizDate1, l_bizDate2, l_bizDate3, l_bizDate4};
            l_ifoDepositCalcCondition.setBizDates(l_bizDates);
            l_ifoDepositCalcCondition.setIfoDepositCalcBaseDate(0);
            
            WEB3IfoCustomerTransfer l_ifoCustomerTransfer = new WEB3IfoCustomerTransfer();
            double[] l_balances = {2, 5 ,6}; 
            l_ifoCustomerTransfer.setBalances(l_balances);
            l_ifoCustomerTransfer.setCurrentBizDateTransferAmount(3);
            
            Field field1 = WEB3IfoDepositCalc.class.getDeclaredField("ifoCustomerTransfer");
            field1.setAccessible(true);
            field1.set(depositCalc, l_ifoCustomerTransfer);
            
            Field field2 = WEB3IfoDepositCalc.class.getDeclaredField("ifoDepositCalcCondition");
            field2.setAccessible(true);
            field2.set(depositCalc, l_ifoDepositCalcCondition);
            int l_intReservedDate= 4;
            double l_dblReturn = depositCalc.calcIfoDepositBalance(l_intReservedDate);
            assertEquals(0, l_dblReturn, 0); 
        }
        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage());
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    
    //指定日 = 2
    public void testCalcIfoDepositBalance_C002()
    {
        String STR_METHOD_NAME = "testCalcIfoDepositBalance_C002";
        log.entering(STR_METHOD_NAME);
        try
        {
            WEB3IfoDepositCalcCondition l_ifoDepositCalcCondition = new WEB3IfoDepositCalcCondition();
            Date l_bizDate1 = WEB3DateUtility.getDate("20040715","yyyyMMdd");
            Date l_bizDate2 = WEB3DateUtility.getDate("20040716","yyyyMMdd");
            Date l_bizDate3 = WEB3DateUtility.getDate("20040719","yyyyMMdd");
            Date l_bizDate4 = WEB3DateUtility.getDate("20040720","yyyyMMdd");
            Date[] l_bizDates = {l_bizDate1, l_bizDate2, l_bizDate3, l_bizDate4};
            l_ifoDepositCalcCondition.setBizDates(l_bizDates);
            l_ifoDepositCalcCondition.setIfoDepositCalcBaseDate(0);
            
            WEB3IfoCustomerTransfer l_ifoCustomerTransfer = new WEB3IfoCustomerTransfer();
            double[] l_balances = {2, 5 ,6}; 
            l_ifoCustomerTransfer.setBalances(l_balances);
            l_ifoCustomerTransfer.setCurrentBizDateTransferAmount(3);
            
            Field field1 = WEB3IfoDepositCalc.class.getDeclaredField("ifoCustomerTransfer");
            field1.setAccessible(true);
            field1.set(depositCalc, l_ifoCustomerTransfer);
            
            Field field2 = WEB3IfoDepositCalc.class.getDeclaredField("ifoDepositCalcCondition");
            field2.setAccessible(true);
            field2.set(depositCalc, l_ifoDepositCalcCondition);
            int l_intReservedDate= 2;
            double l_dblReturn = depositCalc.calcIfoDepositBalance(l_intReservedDate);
            assertEquals(9, l_dblReturn, 0); 
        }
        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage());
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    //正確的「受入証拠金残高」を返却する
    public void testCalcReceiptIfoDepositBalance()
    {
        String STR_METHOD_NAME = "testCalcReceiptIfoDepositBalance";
        log.entering(STR_METHOD_NAME);
        try
        {
            WEB3IfoDepositCalcCondition l_ifoDepositCalcCondition = new WEB3IfoDepositCalcCondition();
            Date l_bizDate1 = WEB3DateUtility.getDate("20040715","yyyyMMdd");
            Date l_bizDate2 = WEB3DateUtility.getDate("20040716","yyyyMMdd");
            Date l_bizDate3 = WEB3DateUtility.getDate("20040719","yyyyMMdd");
            Date l_bizDate4 = WEB3DateUtility.getDate("20040720","yyyyMMdd");
            Date[] l_bizDates = {l_bizDate1, l_bizDate2, l_bizDate3, l_bizDate4};
            l_ifoDepositCalcCondition.setBizDates(l_bizDates);
            //基準日
            l_ifoDepositCalcCondition.setIfoDepositCalcBaseDate(0);
            
            WEB3IfoCustomerTransfer l_ifoCustomerTransfer = new WEB3IfoCustomerTransfer();
            double[] l_balances = {2, 5 ,6}; 
            l_ifoCustomerTransfer.setBalances(l_balances);
            l_ifoCustomerTransfer.setCurrentBizDateTransferAmount(4);
            
            Field field1 = WEB3IfoDepositCalc.class.getDeclaredField("ifoCustomerTransfer");
            field1.setAccessible(true);
            field1.set(depositCalc, l_ifoCustomerTransfer);
            
            Field field2 = WEB3IfoDepositCalc.class.getDeclaredField("ifoDepositCalcCondition");
            field2.setAccessible(true);
            field2.set(depositCalc, l_ifoDepositCalcCondition);
            double l_dblReturn = depositCalc.calcReceiptIfoDepositBalance();
            assertEquals(6, l_dblReturn, 0); 
        }
        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage());
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    //0を返却する
    public void testCalcReceiptIfoDepositBalance_C001()
    {
        String STR_METHOD_NAME = "testCalcReceiptIfoDepositBalance_C001";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            WEB3IfoDepositCalcCondition l_ifoDepositCalcCondition = new WEB3IfoDepositCalcCondition();
            Date l_bizDate1 = WEB3DateUtility.getDate("20040715","yyyyMMdd");
            Date l_bizDate2 = WEB3DateUtility.getDate("20040716","yyyyMMdd");
            Date l_bizDate3 = WEB3DateUtility.getDate("20040719","yyyyMMdd");
            Date l_bizDate4 = WEB3DateUtility.getDate("20040720","yyyyMMdd");
            Date[] l_bizDates = {l_bizDate1, l_bizDate2, l_bizDate3, l_bizDate4};
            l_ifoDepositCalcCondition.setBizDates(l_bizDates);
            //基準日
            l_ifoDepositCalcCondition.setIfoDepositCalcBaseDate(0);
            
            WEB3IfoCustomerTransfer l_ifoCustomerTransfer = new WEB3IfoCustomerTransfer();
            double[] l_balances = {2, 5 ,6}; 
            l_ifoCustomerTransfer.setBalances(l_balances);

            Field field1 = WEB3IfoDepositCalc.class.getDeclaredField("ifoCustomerTransfer");
            field1.setAccessible(true);
            field1.set(depositCalc, l_ifoCustomerTransfer);
            
            Field field2 = WEB3IfoDepositCalc.class.getDeclaredField("ifoDepositCalcCondition");
            field2.setAccessible(true);
            field2.set(depositCalc, l_ifoDepositCalcCondition);
            
            int l_intReservedDate = 4;
            double l_dblReturn = depositCalc.calcReceiptIfoDepositBalance(l_intReservedDate);
            assertEquals(0, l_dblReturn, 0); 
        }
        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage());
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    
    //指定日 = 2
    public void testCalcReceiptIfoDepositBalance_C002()
    {
        String STR_METHOD_NAME = "testCalcReceiptIfoDepositBalance_C002";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            WEB3IfoDepositCalcCondition l_ifoDepositCalcCondition = new WEB3IfoDepositCalcCondition();
            Date l_bizDate1 = WEB3DateUtility.getDate("20040715","yyyyMMdd");
            Date l_bizDate2 = WEB3DateUtility.getDate("20040716","yyyyMMdd");
            Date l_bizDate3 = WEB3DateUtility.getDate("20040719","yyyyMMdd");
            Date l_bizDate4 = WEB3DateUtility.getDate("20040720","yyyyMMdd");
            Date[] l_bizDates = {l_bizDate1, l_bizDate2, l_bizDate3, l_bizDate4};
            l_ifoDepositCalcCondition.setBizDates(l_bizDates);
            //基準日
            l_ifoDepositCalcCondition.setIfoDepositCalcBaseDate(0);
            
            WEB3IfoCustomerTransfer l_ifoCustomerTransfer = new WEB3IfoCustomerTransfer();
            double[] l_balances = {2, 5 ,6}; 
            l_ifoCustomerTransfer.setBalances(l_balances);

            Field field1 = WEB3IfoDepositCalc.class.getDeclaredField("ifoCustomerTransfer");
            field1.setAccessible(true);
            field1.set(depositCalc, l_ifoCustomerTransfer);
            
            Field field2 = WEB3IfoDepositCalc.class.getDeclaredField("ifoDepositCalcCondition");
            field2.setAccessible(true);
            field2.set(depositCalc, l_ifoDepositCalcCondition);
            
            int l_intReservedDate = 2;
            double l_dblReturn = depositCalc.calcReceiptIfoDepositBalance(l_intReservedDate);
            assertEquals(6, l_dblReturn, 0); 
        }
        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage());
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testCalcIfoDepositRequiredAmount()
    {
        String STR_METHOD_NAME = "testCalcIfoDepositRequiredAmount";
        log.entering(STR_METHOD_NAME);
        try
        {
            WEB3IfoSummaryContractPerProduct[] ifoSummaryContractPerProductList =
                new WEB3IfoSummaryContractPerProduct[1];
            ifoSummaryContractPerProductList[0] = new WEB3IfoSummaryContractPerProduct();
            ifoSummaryContractPerProductList[0].setCurrentPrice(233);
            ifoSummaryContractPerProductList[0].setUnitSize(6.66);
            ifoSummaryContractPerProductList[0].setBuyQuantity(2);
            ifoSummaryContractPerProductList[0].setCurrentBizDateBuyOrderQuantity(4);
            ifoSummaryContractPerProductList[0].setIfoDerivativeType(IfoDerivativeTypeEnum.PUT_OPTIONS);
            ifoSummaryContractPerProductList[0].setSellQuantity(8);
            
            WEB3IfoDepositCalcCondition l_ifoDepositCalcCondition = new WEB3IfoDepositCalcCondition();
            Date l_bizDate1 = WEB3DateUtility.getDate("20040715","yyyyMMdd");
            Date l_bizDate2 = WEB3DateUtility.getDate("20040716","yyyyMMdd");
            Date l_bizDate3 = WEB3DateUtility.getDate("20040719","yyyyMMdd");
            Date l_bizDate4 = WEB3DateUtility.getDate("20040720","yyyyMMdd");
            Date[] l_bizDates = {l_bizDate1, l_bizDate2, l_bizDate3, l_bizDate4};
            l_ifoDepositCalcCondition.setBizDates(l_bizDates);
            //SPAN証拠金採用の時
            l_ifoDepositCalcCondition.setSimpleSPANCalcFlag(false);
            l_ifoDepositCalcCondition.setSpanTroubleFlag(false);
            //setSPAN係数レッド
            l_ifoDepositCalcCondition.setSPANFactor(5.3);
            //基準日
            l_ifoDepositCalcCondition.setIfoDepositCalcBaseDate(0);
            l_ifoDepositCalcCondition.setSPANFactorRed(2);

            Field field1 = WEB3IfoDepositCalc.class.getDeclaredField("ifoSummaryContractPerProductList");
            field1.setAccessible(true);
            field1.set(depositCalc, ifoSummaryContractPerProductList);
            
            Field field2 = WEB3IfoDepositCalc.class.getDeclaredField("ifoDepositCalcCondition");
            field2.setAccessible(true);
            field2.set(depositCalc, l_ifoDepositCalcCondition);
            double l_dblReturn = depositCalc.calcIfoDepositRequiredAmount();
            assertEquals(16446, l_dblReturn, 0);
        }
        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage());
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    //指定日が0、1、2以外の時,返回0
    public void testCalcIfoDepositRequiredAmount_C001()
    {
        String STR_METHOD_NAME = "testCalcIfoDepositRequiredAmount_C001";
        log.entering(STR_METHOD_NAME);
        try
        {
            WEB3IfoSummaryContractPerProduct[] ifoSummaryContractPerProductList =
                new WEB3IfoSummaryContractPerProduct[1];
            ifoSummaryContractPerProductList[0] = new WEB3IfoSummaryContractPerProduct();
            ifoSummaryContractPerProductList[0].setCurrentPrice(233);
            ifoSummaryContractPerProductList[0].setUnitSize(6.66);
            ifoSummaryContractPerProductList[0].setBuyQuantity(2);
            ifoSummaryContractPerProductList[0].setCurrentBizDateBuyOrderQuantity(4);
            ifoSummaryContractPerProductList[0].setIfoDerivativeType(IfoDerivativeTypeEnum.PUT_OPTIONS);
            ifoSummaryContractPerProductList[0].setSellQuantity(8);
            
            WEB3IfoDepositCalcCondition l_ifoDepositCalcCondition = new WEB3IfoDepositCalcCondition();
            Date l_bizDate1 = WEB3DateUtility.getDate("20040715","yyyyMMdd");
            Date l_bizDate2 = WEB3DateUtility.getDate("20040716","yyyyMMdd");
            Date l_bizDate3 = WEB3DateUtility.getDate("20040719","yyyyMMdd");
            Date l_bizDate4 = WEB3DateUtility.getDate("20040720","yyyyMMdd");
            Date[] l_bizDates = {l_bizDate1, l_bizDate2, l_bizDate3, l_bizDate4};
            l_ifoDepositCalcCondition.setBizDates(l_bizDates);
            //SPAN証拠金採用の時
            l_ifoDepositCalcCondition.setSimpleSPANCalcFlag(false);
            l_ifoDepositCalcCondition.setSpanTroubleFlag(false);
            //setSPAN係数レッド
            l_ifoDepositCalcCondition.setSPANFactor(5.3);
            //基準日
            l_ifoDepositCalcCondition.setIfoDepositCalcBaseDate(4);
            l_ifoDepositCalcCondition.setSPANFactorRed(2);

            Field field1 = WEB3IfoDepositCalc.class.getDeclaredField("ifoSummaryContractPerProductList");
            field1.setAccessible(true);
            field1.set(depositCalc, ifoSummaryContractPerProductList);
            
            Field field2 = WEB3IfoDepositCalc.class.getDeclaredField("ifoDepositCalcCondition");
            field2.setAccessible(true);
            field2.set(depositCalc, l_ifoDepositCalcCondition);
            int l_inValue = 4;
            double l_dblReturn = depositCalc.calcIfoDepositRequiredAmount(l_inValue);
            assertEquals(0, l_dblReturn, 0);
        }
        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage());
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    //SPAN証拠金不採用の時
    public void testCalcIfoDepositRequiredAmount_C002()
    {
        String STR_METHOD_NAME = "testCalcIfoDepositRequiredAmount_C002";
        log.entering(STR_METHOD_NAME);
        try
        {
            WEB3IfoSummaryContractPerProduct[] ifoSummaryContractPerProductList =
                new WEB3IfoSummaryContractPerProduct[1];
            ifoSummaryContractPerProductList[0] = new WEB3IfoSummaryContractPerProduct();
            ifoSummaryContractPerProductList[0].setCurrentPrice(233);
            ifoSummaryContractPerProductList[0].setUnitSize(6.66);
            ifoSummaryContractPerProductList[0].setBuyQuantity(2);
            ifoSummaryContractPerProductList[0].setCurrentBizDateBuyOrderQuantity(4);
            ifoSummaryContractPerProductList[0].setIfoDerivativeType(IfoDerivativeTypeEnum.PUT_OPTIONS);
            ifoSummaryContractPerProductList[0].setSellQuantity(8);
            
            WEB3IfoDepositCalcCondition l_ifoDepositCalcCondition = new WEB3IfoDepositCalcCondition();
            Date l_bizDate1 = WEB3DateUtility.getDate("20040715","yyyyMMdd");
            Date l_bizDate2 = WEB3DateUtility.getDate("20040716","yyyyMMdd");
            Date l_bizDate3 = WEB3DateUtility.getDate("20040719","yyyyMMdd");
            Date l_bizDate4 = WEB3DateUtility.getDate("20040720","yyyyMMdd");
            Date[] l_bizDates = {l_bizDate1, l_bizDate2, l_bizDate3, l_bizDate4};
            l_ifoDepositCalcCondition.setBizDates(l_bizDates);
            //SPAN証拠金採用の時
            l_ifoDepositCalcCondition.setSimpleSPANCalcFlag(true);
            l_ifoDepositCalcCondition.setSpanTroubleFlag(true);
            //setSPAN係数レッド
            l_ifoDepositCalcCondition.setSPANFactor(5.3);
            //基準日
            l_ifoDepositCalcCondition.setIfoDepositCalcBaseDate(4);
            l_ifoDepositCalcCondition.setSPANFactorRed(2);

            Field field1 = WEB3IfoDepositCalc.class.getDeclaredField("ifoSummaryContractPerProductList");
            field1.setAccessible(true);
            field1.set(depositCalc, ifoSummaryContractPerProductList);
            
            Field field2 = WEB3IfoDepositCalc.class.getDeclaredField("ifoDepositCalcCondition");
            field2.setAccessible(true);
            field2.set(depositCalc, l_ifoDepositCalcCondition);
            int l_inValue = 0;
            double l_dblReturn = depositCalc.calcIfoDepositRequiredAmount(l_inValue);
            assertEquals(3103, l_dblReturn, 0);
        }
        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage());
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    //指定日が2の時,程序正常?束
    public void testCalcIfoDepositRequiredAmount_C003()
    {
        String STR_METHOD_NAME = "testCalcIfoDepositRequiredAmount_C003";
        log.entering(STR_METHOD_NAME);
        try
        {
            WEB3IfoSummaryContractPerProduct[] ifoSummaryContractPerProductList =
                new WEB3IfoSummaryContractPerProduct[1];
            ifoSummaryContractPerProductList[0] = new WEB3IfoSummaryContractPerProduct();
            ifoSummaryContractPerProductList[0].setCurrentPrice(233);
            ifoSummaryContractPerProductList[0].setUnitSize(6.66);
            ifoSummaryContractPerProductList[0].setBuyQuantity(2);
            ifoSummaryContractPerProductList[0].setCurrentBizDateBuyOrderQuantity(4);
            ifoSummaryContractPerProductList[0].setIfoDerivativeType(IfoDerivativeTypeEnum.PUT_OPTIONS);
            ifoSummaryContractPerProductList[0].setSellQuantity(8);
            
            WEB3IfoDepositCalcCondition l_ifoDepositCalcCondition = new WEB3IfoDepositCalcCondition();
            Date l_bizDate1 = WEB3DateUtility.getDate("20040715","yyyyMMdd");
            Date l_bizDate2 = WEB3DateUtility.getDate("20040716","yyyyMMdd");
            Date l_bizDate3 = WEB3DateUtility.getDate("20040719","yyyyMMdd");
            Date l_bizDate4 = WEB3DateUtility.getDate("20040720","yyyyMMdd");
            Date[] l_bizDates = {l_bizDate1, l_bizDate2, l_bizDate3, l_bizDate4};
            l_ifoDepositCalcCondition.setBizDates(l_bizDates);
            //SPAN証拠金採用の時
            l_ifoDepositCalcCondition.setSimpleSPANCalcFlag(false);
            l_ifoDepositCalcCondition.setSpanTroubleFlag(false);
            //setSPAN係数レッド
            l_ifoDepositCalcCondition.setSPANFactor(5.3);
            //基準日
            l_ifoDepositCalcCondition.setIfoDepositCalcBaseDate(0);
            l_ifoDepositCalcCondition.setSPANFactorRed(2);

            Field field1 = WEB3IfoDepositCalc.class.getDeclaredField("ifoSummaryContractPerProductList");
            field1.setAccessible(true);
            field1.set(depositCalc, ifoSummaryContractPerProductList);
            
            Field field2 = WEB3IfoDepositCalc.class.getDeclaredField("ifoDepositCalcCondition");
            field2.setAccessible(true);
            field2.set(depositCalc, l_ifoDepositCalcCondition);
            int l_inValue = 2;
            double l_dblReturn = depositCalc.calcIfoDepositRequiredAmount(l_inValue);
            assertEquals(16446, l_dblReturn, 0);
        }
        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage());
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    
    //SPAN証拠金採用の時
    public void testCalcIfoDepositRequiredAmountRed_C001()
    {
        String STR_METHOD_NAME = "testCalcIfoDepositRequiredAmountRed_C001";
        log.entering(STR_METHOD_NAME);
        try
        {
            WEB3IfoSummaryContractPerProduct[] ifoSummaryContractPerProductList =
                new WEB3IfoSummaryContractPerProduct[1];
            ifoSummaryContractPerProductList[0] = new WEB3IfoSummaryContractPerProduct();
            ifoSummaryContractPerProductList[0].setCurrentPrice(233);
            ifoSummaryContractPerProductList[0].setUnitSize(6.66);
            ifoSummaryContractPerProductList[0].setBuyQuantity(2);
            ifoSummaryContractPerProductList[0].setCurrentBizDateBuyOrderQuantity(4);
            ifoSummaryContractPerProductList[0].setIfoDerivativeType(IfoDerivativeTypeEnum.PUT_OPTIONS);
            ifoSummaryContractPerProductList[0].setSellQuantity(8);
            
            WEB3IfoDepositCalcCondition l_ifoDepositCalcCondition = new WEB3IfoDepositCalcCondition();
            Date l_bizDate1 = WEB3DateUtility.getDate("20040715","yyyyMMdd");
            Date l_bizDate2 = WEB3DateUtility.getDate("20040716","yyyyMMdd");
            Date l_bizDate3 = WEB3DateUtility.getDate("20040719","yyyyMMdd");
            Date l_bizDate4 = WEB3DateUtility.getDate("20040720","yyyyMMdd");
            Date[] l_bizDates = {l_bizDate1, l_bizDate2, l_bizDate3, l_bizDate4};
            l_ifoDepositCalcCondition.setBizDates(l_bizDates);
            //SPAN証拠金採用の時
            l_ifoDepositCalcCondition.setSimpleSPANCalcFlag(true);
            l_ifoDepositCalcCondition.setSpanTroubleFlag(true);
            //setSPAN係数レッド
            l_ifoDepositCalcCondition.setSPANFactor(5.3);
            //基準日
            l_ifoDepositCalcCondition.setIfoDepositCalcBaseDate(4);
            l_ifoDepositCalcCondition.setSPANFactorRed(2);

            Field field1 = WEB3IfoDepositCalc.class.getDeclaredField("ifoSummaryContractPerProductList");
            field1.setAccessible(true);
            field1.set(depositCalc, ifoSummaryContractPerProductList);
            
            Field field2 = WEB3IfoDepositCalc.class.getDeclaredField("ifoDepositCalcCondition");
            field2.setAccessible(true);
            field2.set(depositCalc, l_ifoDepositCalcCondition);
            int l_intReservedDate = 4;
            double l_dblReturn = depositCalc.calcIfoDepositRequiredAmountRed(l_intReservedDate);
            assertEquals(0, l_dblReturn, 0);
        }
        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage());
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    //SPAN証拠金不採用の時
    public void testCalcIfoDepositRequiredAmountRed_C002()
    {
        String STR_METHOD_NAME = "testCalcIfoDepositRequiredAmountRed_C002";
        log.entering(STR_METHOD_NAME);
        try
        {
            WEB3IfoSummaryContractPerProduct[] ifoSummaryContractPerProductList =
                new WEB3IfoSummaryContractPerProduct[1];
            ifoSummaryContractPerProductList[0] = new WEB3IfoSummaryContractPerProduct();
            ifoSummaryContractPerProductList[0].setCurrentPrice(233);
            ifoSummaryContractPerProductList[0].setUnitSize(6.66);
            ifoSummaryContractPerProductList[0].setBuyQuantity(2);
            ifoSummaryContractPerProductList[0].setCurrentBizDateBuyOrderQuantity(4);
            ifoSummaryContractPerProductList[0].setIfoDerivativeType(IfoDerivativeTypeEnum.PUT_OPTIONS);
            ifoSummaryContractPerProductList[0].setSellQuantity(8);
            
            WEB3IfoDepositCalcCondition l_ifoDepositCalcCondition = new WEB3IfoDepositCalcCondition();
            Date l_bizDate1 = WEB3DateUtility.getDate("20040715","yyyyMMdd");
            Date l_bizDate2 = WEB3DateUtility.getDate("20040716","yyyyMMdd");
            Date l_bizDate3 = WEB3DateUtility.getDate("20040719","yyyyMMdd");
            Date l_bizDate4 = WEB3DateUtility.getDate("20040720","yyyyMMdd");
            Date[] l_bizDates = {l_bizDate1, l_bizDate2, l_bizDate3, l_bizDate4};
            l_ifoDepositCalcCondition.setBizDates(l_bizDates);
            //SPAN証拠金採用の時
            l_ifoDepositCalcCondition.setSimpleSPANCalcFlag(false);
            l_ifoDepositCalcCondition.setSpanTroubleFlag(false);
            //setSPAN係数レッド
            l_ifoDepositCalcCondition.setSPANFactor(5.3);
            //基準日
            l_ifoDepositCalcCondition.setIfoDepositCalcBaseDate(4);
            l_ifoDepositCalcCondition.setSPANFactorRed(2.2);

            Field field1 = WEB3IfoDepositCalc.class.getDeclaredField("ifoSummaryContractPerProductList");
            field1.setAccessible(true);
            field1.set(depositCalc, ifoSummaryContractPerProductList);
            
            Field field2 = WEB3IfoDepositCalc.class.getDeclaredField("ifoDepositCalcCondition");
            field2.setAccessible(true);
            field2.set(depositCalc, l_ifoDepositCalcCondition);
            int l_intReservedDate = 0;
            double l_dblReturn = depositCalc.calcIfoDepositRequiredAmountRed(l_intReservedDate);
            assertEquals(6827, l_dblReturn, 0);
        }
        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage());
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    //正確的「証拠金余力額」を返却する。
    public void testCalcIfoDepositRequiredAmountRed_C003()
    {
        String STR_METHOD_NAME = "testCalcIfoDepositRequiredAmountRed_C003";
        log.entering(STR_METHOD_NAME);
        try
        {
            WEB3IfoSummaryContractPerProduct[] ifoSummaryContractPerProductList =
                new WEB3IfoSummaryContractPerProduct[1];
            ifoSummaryContractPerProductList[0] = new WEB3IfoSummaryContractPerProduct();
            ifoSummaryContractPerProductList[0].setCurrentPrice(233);
            ifoSummaryContractPerProductList[0].setUnitSize(6.66);
            ifoSummaryContractPerProductList[0].setBuyQuantity(2);
            ifoSummaryContractPerProductList[0].setCurrentBizDateBuyOrderQuantity(4);
            ifoSummaryContractPerProductList[0].setIfoDerivativeType(IfoDerivativeTypeEnum.PUT_OPTIONS);
            ifoSummaryContractPerProductList[0].setSellQuantity(8);
            
            WEB3IfoDepositCalcCondition l_ifoDepositCalcCondition = new WEB3IfoDepositCalcCondition();
            Date l_bizDate1 = WEB3DateUtility.getDate("20040715","yyyyMMdd");
            Date l_bizDate2 = WEB3DateUtility.getDate("20040716","yyyyMMdd");
            Date l_bizDate3 = WEB3DateUtility.getDate("20040719","yyyyMMdd");
            Date l_bizDate4 = WEB3DateUtility.getDate("20040720","yyyyMMdd");
            Date[] l_bizDates = {l_bizDate1, l_bizDate2, l_bizDate3, l_bizDate4};
            l_ifoDepositCalcCondition.setBizDates(l_bizDates);
            //SPAN証拠金採用の時
            l_ifoDepositCalcCondition.setSimpleSPANCalcFlag(true);
            l_ifoDepositCalcCondition.setSpanTroubleFlag(true);
            //setSPAN係数レッド
            l_ifoDepositCalcCondition.setSPANFactor(5.3);
            //基準日
            l_ifoDepositCalcCondition.setIfoDepositCalcBaseDate(4);
            l_ifoDepositCalcCondition.setSPANFactorRed(2.2);

            Field field1 = WEB3IfoDepositCalc.class.getDeclaredField("ifoSummaryContractPerProductList");
            field1.setAccessible(true);
            field1.set(depositCalc, ifoSummaryContractPerProductList);
            
            Field field2 = WEB3IfoDepositCalc.class.getDeclaredField("ifoDepositCalcCondition");
            field2.setAccessible(true);
            field2.set(depositCalc, l_ifoDepositCalcCondition);
            int l_intReservedDate = 0;
            double l_dblReturn = depositCalc.calcIfoDepositRequiredAmountRed(l_intReservedDate);
            assertEquals(3103, l_dblReturn, 0);
        }
        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage());
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    //指定日が2の時,正確的「証拠金余力額」を返却する。
    public void testCalcIfoDepositRequiredAmountRed_C004()
    {
        String STR_METHOD_NAME = "testCalcIfoDepositRequiredAmountRed_C004";
        log.entering(STR_METHOD_NAME);
        try
        {
            WEB3IfoSummaryContractPerProduct[] ifoSummaryContractPerProductList =
                new WEB3IfoSummaryContractPerProduct[1];
            ifoSummaryContractPerProductList[0] = new WEB3IfoSummaryContractPerProduct();
            ifoSummaryContractPerProductList[0].setCurrentPrice(233);
            ifoSummaryContractPerProductList[0].setUnitSize(6.66);
            ifoSummaryContractPerProductList[0].setBuyQuantity(2);
            ifoSummaryContractPerProductList[0].setCurrentBizDateBuyOrderQuantity(4);
            ifoSummaryContractPerProductList[0].setIfoDerivativeType(IfoDerivativeTypeEnum.PUT_OPTIONS);
            ifoSummaryContractPerProductList[0].setSellQuantity(8);
            
            WEB3IfoDepositCalcCondition l_ifoDepositCalcCondition = new WEB3IfoDepositCalcCondition();
            Date l_bizDate1 = WEB3DateUtility.getDate("20040715","yyyyMMdd");
            Date l_bizDate2 = WEB3DateUtility.getDate("20040716","yyyyMMdd");
            Date l_bizDate3 = WEB3DateUtility.getDate("20040719","yyyyMMdd");
            Date l_bizDate4 = WEB3DateUtility.getDate("20040720","yyyyMMdd");
            Date[] l_bizDates = {l_bizDate1, l_bizDate2, l_bizDate3, l_bizDate4};
            l_ifoDepositCalcCondition.setBizDates(l_bizDates);
            //SPAN証拠金採用の時
            l_ifoDepositCalcCondition.setSimpleSPANCalcFlag(true);
            l_ifoDepositCalcCondition.setSpanTroubleFlag(true);
            //setSPAN係数レッド
            l_ifoDepositCalcCondition.setSPANFactor(5.3);
            //基準日
            l_ifoDepositCalcCondition.setIfoDepositCalcBaseDate(4);
            l_ifoDepositCalcCondition.setSPANFactorRed(2.2);

            Field field1 = WEB3IfoDepositCalc.class.getDeclaredField("ifoSummaryContractPerProductList");
            field1.setAccessible(true);
            field1.set(depositCalc, ifoSummaryContractPerProductList);
            
            Field field2 = WEB3IfoDepositCalc.class.getDeclaredField("ifoDepositCalcCondition");
            field2.setAccessible(true);
            field2.set(depositCalc, l_ifoDepositCalcCondition);
            int l_intReservedDate = 2;
            double l_dblReturn = depositCalc.calcIfoDepositRequiredAmountRed(l_intReservedDate);
            assertEquals(3103, l_dblReturn, 0);
        }
        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage());
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    //正確的「証拠金余力額」を返却する。
    public void testCalcIfoDepositTradingPowerAmount()
    {
        String STR_METHOD_NAME = "testCalcIfoDepositTradingPowerAmount";
        log.entering(STR_METHOD_NAME);
        try
        {
            WEB3IfoSummaryContractPerProduct[] ifoSummaryContractPerProductList =
                new WEB3IfoSummaryContractPerProduct[1];
            ifoSummaryContractPerProductList[0] = new WEB3IfoSummaryContractPerProduct();
            ifoSummaryContractPerProductList[0].setCurrentPrice(233);
            ifoSummaryContractPerProductList[0].setUnitSize(6.66);
            ifoSummaryContractPerProductList[0].setBuyQuantity(2);
            ifoSummaryContractPerProductList[0].setCurrentBizDateBuyOrderQuantity(4);
            ifoSummaryContractPerProductList[0].setIfoDerivativeType(IfoDerivativeTypeEnum.PUT_OPTIONS);
            ifoSummaryContractPerProductList[0].setSellQuantity(8);
            
            WEB3IfoDepositCalcCondition l_ifoDepositCalcCondition = new WEB3IfoDepositCalcCondition();
            Date l_bizDate1 = WEB3DateUtility.getDate("20040715","yyyyMMdd");
            Date l_bizDate2 = WEB3DateUtility.getDate("20040716","yyyyMMdd");
            Date l_bizDate3 = WEB3DateUtility.getDate("20040719","yyyyMMdd");
            Date l_bizDate4 = WEB3DateUtility.getDate("20040720","yyyyMMdd");
            Date[] l_bizDates = {l_bizDate1, l_bizDate2, l_bizDate3, l_bizDate4};
            l_ifoDepositCalcCondition.setBizDates(l_bizDates);
            //SPAN証拠金採用の時
            l_ifoDepositCalcCondition.setSimpleSPANCalcFlag(true);
            l_ifoDepositCalcCondition.setSpanTroubleFlag(true);
            //setSPAN係数レッド
            l_ifoDepositCalcCondition.setSPANFactor(5.3);
            //基準日
            l_ifoDepositCalcCondition.setIfoDepositCalcBaseDate(1);
            l_ifoDepositCalcCondition.setSPANFactorRed(2.2);
            //新規建余力可能 = true
            l_ifoDepositCalcCondition.setNewOpenTradingPowerAvailableFlag(true);
            //必要最低証拠金)
            l_ifoDepositCalcCondition.setMinIfoDeposit(10);
            //証拠金不足メール送信済フラグ
            l_ifoDepositCalcCondition.setIfoDepositMailFlag(true);
            l_ifoDepositCalcCondition.setLackChargeNonManagementFlag(true);
            l_ifoDepositCalcCondition.setCurrentBizIfoDepositLackCharge(100);
            
            WEB3IfoCustomerTransfer l_ifoCustomerTransfer = new WEB3IfoCustomerTransfer();
            double[] l_balances = {2, 5 ,6}; 
            l_ifoCustomerTransfer.setBalances(l_balances);
            l_ifoCustomerTransfer.setCurrentBizDateTransferAmount(100);
            l_ifoCustomerTransfer.addNextBizDateFuturesCloseProfitLoss(200);

            Field field1 = WEB3IfoDepositCalc.class.getDeclaredField("ifoSummaryContractPerProductList");
            field1.setAccessible(true);
            field1.set(depositCalc, ifoSummaryContractPerProductList);
            
            Field field2 = WEB3IfoDepositCalc.class.getDeclaredField("ifoDepositCalcCondition");
            field2.setAccessible(true);
            field2.set(depositCalc, l_ifoDepositCalcCondition);

            Field field3 = WEB3IfoDepositCalc.class.getDeclaredField("ifoCustomerTransfer");
            field3.setAccessible(true);
            field3.set(depositCalc, l_ifoCustomerTransfer);
            double l_dblReturn = depositCalc.calcIfoDepositTradingPowerAmount();
            assertEquals(-2798, l_dblReturn, 0);
        }
        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage());
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    //指定日が1、2 以外の時，返回0
    public void testCalcIfoDepositTradingPowerAmount_C001()
    {
        String STR_METHOD_NAME = "testCalcIfoDepositTradingPowerAmount_C001";
        log.entering(STR_METHOD_NAME);
        try
        {
            WEB3IfoSummaryContractPerProduct[] ifoSummaryContractPerProductList =
                new WEB3IfoSummaryContractPerProduct[1];
            ifoSummaryContractPerProductList[0] = new WEB3IfoSummaryContractPerProduct();
            ifoSummaryContractPerProductList[0].setCurrentPrice(233);
            ifoSummaryContractPerProductList[0].setUnitSize(6.66);
            ifoSummaryContractPerProductList[0].setBuyQuantity(2);
            ifoSummaryContractPerProductList[0].setCurrentBizDateBuyOrderQuantity(4);
            ifoSummaryContractPerProductList[0].setIfoDerivativeType(IfoDerivativeTypeEnum.PUT_OPTIONS);
            ifoSummaryContractPerProductList[0].setSellQuantity(8);
            
            WEB3IfoDepositCalcCondition l_ifoDepositCalcCondition = new WEB3IfoDepositCalcCondition();
            Date l_bizDate1 = WEB3DateUtility.getDate("20040715","yyyyMMdd");
            Date l_bizDate2 = WEB3DateUtility.getDate("20040716","yyyyMMdd");
            Date l_bizDate3 = WEB3DateUtility.getDate("20040719","yyyyMMdd");
            Date l_bizDate4 = WEB3DateUtility.getDate("20040720","yyyyMMdd");
            Date[] l_bizDates = {l_bizDate1, l_bizDate2, l_bizDate3, l_bizDate4};
            l_ifoDepositCalcCondition.setBizDates(l_bizDates);
            //SPAN証拠金採用の時
            l_ifoDepositCalcCondition.setSimpleSPANCalcFlag(true);
            l_ifoDepositCalcCondition.setSpanTroubleFlag(true);
            //setSPAN係数レッド
            l_ifoDepositCalcCondition.setSPANFactor(5.3);
            //基準日
            l_ifoDepositCalcCondition.setIfoDepositCalcBaseDate(1);
            l_ifoDepositCalcCondition.setSPANFactorRed(2.2);
            //新規建余力可能 = true
            l_ifoDepositCalcCondition.setNewOpenTradingPowerAvailableFlag(true);
            //必要最低証拠金)
            l_ifoDepositCalcCondition.setMinIfoDeposit(10);
            //証拠金不足メール送信済フラグ
            l_ifoDepositCalcCondition.setIfoDepositMailFlag(true);
            l_ifoDepositCalcCondition.setLackChargeNonManagementFlag(true);
            l_ifoDepositCalcCondition.setCurrentBizIfoDepositLackCharge(100);
            
            WEB3IfoCustomerTransfer l_ifoCustomerTransfer = new WEB3IfoCustomerTransfer();
            double[] l_balances = {2, 5 ,6}; 
            l_ifoCustomerTransfer.setBalances(l_balances);
            l_ifoCustomerTransfer.setCurrentBizDateTransferAmount(100);
            l_ifoCustomerTransfer.addNextBizDateFuturesCloseProfitLoss(200);

            Field field1 = WEB3IfoDepositCalc.class.getDeclaredField("ifoSummaryContractPerProductList");
            field1.setAccessible(true);
            field1.set(depositCalc, ifoSummaryContractPerProductList);
            
            Field field2 = WEB3IfoDepositCalc.class.getDeclaredField("ifoDepositCalcCondition");
            field2.setAccessible(true);
            field2.set(depositCalc, l_ifoDepositCalcCondition);

            Field field3 = WEB3IfoDepositCalc.class.getDeclaredField("ifoCustomerTransfer");
            field3.setAccessible(true);
            field3.set(depositCalc, l_ifoCustomerTransfer);
            int l_intReservedDate = 4;
            double l_dblReturn = depositCalc.calcIfoDepositTradingPowerAmount(l_intReservedDate);
            assertEquals(0, l_dblReturn, 0);
        }
        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage());
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    //is新規建余力可能() == false，返回0
    public void testCalcIfoDepositTradingPowerAmount_C002()
    {
        String STR_METHOD_NAME = "testCalcIfoDepositTradingPowerAmount_C002";
        log.entering(STR_METHOD_NAME);
        try
        {
            WEB3IfoSummaryContractPerProduct[] ifoSummaryContractPerProductList =
                new WEB3IfoSummaryContractPerProduct[1];
            ifoSummaryContractPerProductList[0] = new WEB3IfoSummaryContractPerProduct();
            ifoSummaryContractPerProductList[0].setCurrentPrice(233);
            ifoSummaryContractPerProductList[0].setUnitSize(6.66);
            ifoSummaryContractPerProductList[0].setBuyQuantity(2);
            ifoSummaryContractPerProductList[0].setCurrentBizDateBuyOrderQuantity(4);
            ifoSummaryContractPerProductList[0].setIfoDerivativeType(IfoDerivativeTypeEnum.PUT_OPTIONS);
            ifoSummaryContractPerProductList[0].setSellQuantity(8);
            
            WEB3IfoDepositCalcCondition l_ifoDepositCalcCondition = new WEB3IfoDepositCalcCondition();
            Date l_bizDate1 = WEB3DateUtility.getDate("20040715","yyyyMMdd");
            Date l_bizDate2 = WEB3DateUtility.getDate("20040716","yyyyMMdd");
            Date l_bizDate3 = WEB3DateUtility.getDate("20040719","yyyyMMdd");
            Date l_bizDate4 = WEB3DateUtility.getDate("20040720","yyyyMMdd");
            Date[] l_bizDates = {l_bizDate1, l_bizDate2, l_bizDate3, l_bizDate4};
            l_ifoDepositCalcCondition.setBizDates(l_bizDates);
            //SPAN証拠金採用の時
            l_ifoDepositCalcCondition.setSimpleSPANCalcFlag(true);
            l_ifoDepositCalcCondition.setSpanTroubleFlag(true);
            //setSPAN係数レッド
            l_ifoDepositCalcCondition.setSPANFactor(5.3);
            //基準日
            l_ifoDepositCalcCondition.setIfoDepositCalcBaseDate(1);
            l_ifoDepositCalcCondition.setSPANFactorRed(2.2);
            //新規建余力可能 = true
            l_ifoDepositCalcCondition.setNewOpenTradingPowerAvailableFlag(false);
            //必要最低証拠金)
            l_ifoDepositCalcCondition.setMinIfoDeposit(10);
            //証拠金不足メール送信済フラグ
            l_ifoDepositCalcCondition.setIfoDepositMailFlag(true);
            l_ifoDepositCalcCondition.setLackChargeNonManagementFlag(true);
            l_ifoDepositCalcCondition.setCurrentBizIfoDepositLackCharge(100);
            
            WEB3IfoCustomerTransfer l_ifoCustomerTransfer = new WEB3IfoCustomerTransfer();
            double[] l_balances = {2, 5 ,6}; 
            l_ifoCustomerTransfer.setBalances(l_balances);
            l_ifoCustomerTransfer.setCurrentBizDateTransferAmount(100);
            l_ifoCustomerTransfer.addNextBizDateFuturesCloseProfitLoss(200);

            Field field1 = WEB3IfoDepositCalc.class.getDeclaredField("ifoSummaryContractPerProductList");
            field1.setAccessible(true);
            field1.set(depositCalc, ifoSummaryContractPerProductList);
            
            Field field2 = WEB3IfoDepositCalc.class.getDeclaredField("ifoDepositCalcCondition");
            field2.setAccessible(true);
            field2.set(depositCalc, l_ifoDepositCalcCondition);

            Field field3 = WEB3IfoDepositCalc.class.getDeclaredField("ifoCustomerTransfer");
            field3.setAccessible(true);
            field3.set(depositCalc, l_ifoCustomerTransfer);
            int l_intReservedDate = 1;
            double l_dblReturn = depositCalc.calcIfoDepositTradingPowerAmount(l_intReservedDate);
            assertEquals(0, l_dblReturn, 0);
        }
        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage());
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    //受入証拠金残高(n) < 最低証拠金，返回0
    public void testCalcIfoDepositTradingPowerAmount_C003()
    {
        String STR_METHOD_NAME = "testCalcIfoDepositTradingPowerAmount_C003";
        log.entering(STR_METHOD_NAME);
        try
        {
            WEB3IfoSummaryContractPerProduct[] ifoSummaryContractPerProductList =
                new WEB3IfoSummaryContractPerProduct[1];
            ifoSummaryContractPerProductList[0] = new WEB3IfoSummaryContractPerProduct();
            ifoSummaryContractPerProductList[0].setCurrentPrice(233);
            ifoSummaryContractPerProductList[0].setUnitSize(6.66);
            ifoSummaryContractPerProductList[0].setBuyQuantity(2);
            ifoSummaryContractPerProductList[0].setCurrentBizDateBuyOrderQuantity(4);
            ifoSummaryContractPerProductList[0].setIfoDerivativeType(IfoDerivativeTypeEnum.PUT_OPTIONS);
            ifoSummaryContractPerProductList[0].setSellQuantity(8);
            
            WEB3IfoDepositCalcCondition l_ifoDepositCalcCondition = new WEB3IfoDepositCalcCondition();
            Date l_bizDate1 = WEB3DateUtility.getDate("20040715","yyyyMMdd");
            Date l_bizDate2 = WEB3DateUtility.getDate("20040716","yyyyMMdd");
            Date l_bizDate3 = WEB3DateUtility.getDate("20040719","yyyyMMdd");
            Date l_bizDate4 = WEB3DateUtility.getDate("20040720","yyyyMMdd");
            Date[] l_bizDates = {l_bizDate1, l_bizDate2, l_bizDate3, l_bizDate4};
            l_ifoDepositCalcCondition.setBizDates(l_bizDates);
            //SPAN証拠金採用の時
            l_ifoDepositCalcCondition.setSimpleSPANCalcFlag(true);
            l_ifoDepositCalcCondition.setSpanTroubleFlag(true);
            //setSPAN係数レッド
            l_ifoDepositCalcCondition.setSPANFactor(5.3);
            //基準日
            l_ifoDepositCalcCondition.setIfoDepositCalcBaseDate(1);
            l_ifoDepositCalcCondition.setSPANFactorRed(2.2);
            //新規建余力可能 = true
            l_ifoDepositCalcCondition.setNewOpenTradingPowerAvailableFlag(true);
            //必要最低証拠金)
            l_ifoDepositCalcCondition.setMinIfoDeposit(10000);
            //証拠金不足メール送信済フラグ
            l_ifoDepositCalcCondition.setIfoDepositMailFlag(true);
            l_ifoDepositCalcCondition.setLackChargeNonManagementFlag(true);
            l_ifoDepositCalcCondition.setCurrentBizIfoDepositLackCharge(100);
            
            WEB3IfoCustomerTransfer l_ifoCustomerTransfer = new WEB3IfoCustomerTransfer();
            double[] l_balances = {2, 5 ,6}; 
            l_ifoCustomerTransfer.setBalances(l_balances);
            l_ifoCustomerTransfer.setCurrentBizDateTransferAmount(100);
            l_ifoCustomerTransfer.addNextBizDateFuturesCloseProfitLoss(200);

            Field field1 = WEB3IfoDepositCalc.class.getDeclaredField("ifoSummaryContractPerProductList");
            field1.setAccessible(true);
            field1.set(depositCalc, ifoSummaryContractPerProductList);
            
            Field field2 = WEB3IfoDepositCalc.class.getDeclaredField("ifoDepositCalcCondition");
            field2.setAccessible(true);
            field2.set(depositCalc, l_ifoDepositCalcCondition);

            Field field3 = WEB3IfoDepositCalc.class.getDeclaredField("ifoCustomerTransfer");
            field3.setAccessible(true);
            field3.set(depositCalc, l_ifoCustomerTransfer);
            int l_intReservedDate = 1;
            double l_dblReturn = depositCalc.calcIfoDepositTradingPowerAmount(l_intReservedDate);
            assertEquals(0, l_dblReturn, 0);
        }
        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage());
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    //未入金額 > 0 の場合，返回0
    public void testCalcIfoDepositTradingPowerAmount_C004()
    {
        String STR_METHOD_NAME = "testCalcIfoDepositTradingPowerAmount_C004";
        log.entering(STR_METHOD_NAME);
        try
        {
            WEB3IfoSummaryContractPerProduct[] ifoSummaryContractPerProductList =
                new WEB3IfoSummaryContractPerProduct[1];
            ifoSummaryContractPerProductList[0] = new WEB3IfoSummaryContractPerProduct();
            ifoSummaryContractPerProductList[0].setCurrentPrice(233);
            ifoSummaryContractPerProductList[0].setUnitSize(6.66);
            ifoSummaryContractPerProductList[0].setBuyQuantity(2);
            ifoSummaryContractPerProductList[0].setCurrentBizDateBuyOrderQuantity(4);
            ifoSummaryContractPerProductList[0].setIfoDerivativeType(IfoDerivativeTypeEnum.PUT_OPTIONS);
            ifoSummaryContractPerProductList[0].setSellQuantity(8);
            
            WEB3IfoDepositCalcCondition l_ifoDepositCalcCondition = new WEB3IfoDepositCalcCondition();
            Date l_bizDate1 = WEB3DateUtility.getDate("20040715","yyyyMMdd");
            Date l_bizDate2 = WEB3DateUtility.getDate("20040716","yyyyMMdd");
            Date l_bizDate3 = WEB3DateUtility.getDate("20040719","yyyyMMdd");
            Date l_bizDate4 = WEB3DateUtility.getDate("20040720","yyyyMMdd");
            Date[] l_bizDates = {l_bizDate1, l_bizDate2, l_bizDate3, l_bizDate4};
            l_ifoDepositCalcCondition.setBizDates(l_bizDates);
            //SPAN証拠金採用の時
            l_ifoDepositCalcCondition.setSimpleSPANCalcFlag(true);
            l_ifoDepositCalcCondition.setSpanTroubleFlag(true);
            //setSPAN係数レッド
            l_ifoDepositCalcCondition.setSPANFactor(5.3);
            //基準日
            l_ifoDepositCalcCondition.setIfoDepositCalcBaseDate(1);
            l_ifoDepositCalcCondition.setSPANFactorRed(2.2);
            //新規建余力可能 = true
            l_ifoDepositCalcCondition.setNewOpenTradingPowerAvailableFlag(true);
            //必要最低証拠金)
            l_ifoDepositCalcCondition.setMinIfoDeposit(10);
            //証拠金不足メール送信済フラグ
            l_ifoDepositCalcCondition.setIfoDepositMailFlag(true);
            //l_ifoDepositCalcCondition.setLackChargeNonManagementFlag(true);
            l_ifoDepositCalcCondition.setCurrentBizIfoDepositLackCharge(100);
            
            WEB3IfoCustomerTransfer l_ifoCustomerTransfer = new WEB3IfoCustomerTransfer();
            double[] l_balances = {2, 5 ,6}; 
            l_ifoCustomerTransfer.setBalances(l_balances);
            l_ifoCustomerTransfer.setCurrentBizDateTransferAmount(100);
            l_ifoCustomerTransfer.addNextBizDateFuturesCloseProfitLoss(200);

            Field field1 = WEB3IfoDepositCalc.class.getDeclaredField("ifoSummaryContractPerProductList");
            field1.setAccessible(true);
            field1.set(depositCalc, ifoSummaryContractPerProductList);
            
            Field field2 = WEB3IfoDepositCalc.class.getDeclaredField("ifoDepositCalcCondition");
            field2.setAccessible(true);
            field2.set(depositCalc, l_ifoDepositCalcCondition);

            Field field3 = WEB3IfoDepositCalc.class.getDeclaredField("ifoCustomerTransfer");
            field3.setAccessible(true);
            field3.set(depositCalc, l_ifoCustomerTransfer);
            int l_intReservedDate = 1;
            double l_dblReturn = depositCalc.calcIfoDepositTradingPowerAmount(l_intReservedDate);
            assertEquals(0, l_dblReturn, 0);
        }
        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage());
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    //以外の場合
    //指定日が2の時
    public void testCalcIfoDepositTradingPowerAmount_C005()
    {
        String STR_METHOD_NAME = "testCalcIfoDepositTradingPowerAmount_C005";
        log.entering(STR_METHOD_NAME);
        try
        {
            WEB3IfoSummaryContractPerProduct[] ifoSummaryContractPerProductList =
                new WEB3IfoSummaryContractPerProduct[1];
            ifoSummaryContractPerProductList[0] = new WEB3IfoSummaryContractPerProduct();
            ifoSummaryContractPerProductList[0].setCurrentPrice(233);
            ifoSummaryContractPerProductList[0].setUnitSize(6.66);
            ifoSummaryContractPerProductList[0].setBuyQuantity(2);
            ifoSummaryContractPerProductList[0].setCurrentBizDateBuyOrderQuantity(4);
            ifoSummaryContractPerProductList[0].setIfoDerivativeType(IfoDerivativeTypeEnum.PUT_OPTIONS);
            ifoSummaryContractPerProductList[0].setSellQuantity(8);
            
            WEB3IfoDepositCalcCondition l_ifoDepositCalcCondition = new WEB3IfoDepositCalcCondition();
            Date l_bizDate1 = WEB3DateUtility.getDate("20040715","yyyyMMdd");
            Date l_bizDate2 = WEB3DateUtility.getDate("20040716","yyyyMMdd");
            Date l_bizDate3 = WEB3DateUtility.getDate("20040719","yyyyMMdd");
            Date l_bizDate4 = WEB3DateUtility.getDate("20040720","yyyyMMdd");
            Date[] l_bizDates = {l_bizDate1, l_bizDate2, l_bizDate3, l_bizDate4};
            l_ifoDepositCalcCondition.setBizDates(l_bizDates);
            //SPAN証拠金採用の時
            l_ifoDepositCalcCondition.setSimpleSPANCalcFlag(true);
            l_ifoDepositCalcCondition.setSpanTroubleFlag(true);
            //setSPAN係数レッド
            l_ifoDepositCalcCondition.setSPANFactor(5.3);
            //基準日
            l_ifoDepositCalcCondition.setIfoDepositCalcBaseDate(1);
            l_ifoDepositCalcCondition.setSPANFactorRed(2.2);
            //新規建余力可能 = true
            l_ifoDepositCalcCondition.setNewOpenTradingPowerAvailableFlag(true);
            //必要最低証拠金)
            l_ifoDepositCalcCondition.setMinIfoDeposit(10);
            //証拠金不足メール送信済フラグ
            l_ifoDepositCalcCondition.setIfoDepositMailFlag(true);
            l_ifoDepositCalcCondition.setLackChargeNonManagementFlag(true);
            l_ifoDepositCalcCondition.setCurrentBizIfoDepositLackCharge(100);
            
            WEB3IfoCustomerTransfer l_ifoCustomerTransfer = new WEB3IfoCustomerTransfer();
            double[] l_balances = {2, 5 ,6}; 
            l_ifoCustomerTransfer.setBalances(l_balances);
            l_ifoCustomerTransfer.setCurrentBizDateTransferAmount(100);
            l_ifoCustomerTransfer.addNextBizDateFuturesCloseProfitLoss(200);

            Field field1 = WEB3IfoDepositCalc.class.getDeclaredField("ifoSummaryContractPerProductList");
            field1.setAccessible(true);
            field1.set(depositCalc, ifoSummaryContractPerProductList);
            
            Field field2 = WEB3IfoDepositCalc.class.getDeclaredField("ifoDepositCalcCondition");
            field2.setAccessible(true);
            field2.set(depositCalc, l_ifoDepositCalcCondition);

            Field field3 = WEB3IfoDepositCalc.class.getDeclaredField("ifoCustomerTransfer");
            field3.setAccessible(true);
            field3.set(depositCalc, l_ifoCustomerTransfer);
            int l_intReservedDate = 2;
            double l_dblReturn = depositCalc.calcIfoDepositTradingPowerAmount(l_intReservedDate);
            assertEquals(-2797, l_dblReturn, 0);
        }
        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage());
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    //証拠金不足顧客の場合,返回0
    public void testCalcIfoDepositTransferableAmount_C001()
    {
        String STR_METHOD_NAME = "testCalcIfoDepositTransferableAmount_C001";
        log.entering(STR_METHOD_NAME);
        try
        {
            WEB3IfoSummaryContractPerProduct[] ifoSummaryContractPerProductList =
                new WEB3IfoSummaryContractPerProduct[1];
            ifoSummaryContractPerProductList[0] = new WEB3IfoSummaryContractPerProduct();
            ifoSummaryContractPerProductList[0].setCurrentPrice(233);
            ifoSummaryContractPerProductList[0].setUnitSize(6.66);
            ifoSummaryContractPerProductList[0].setBuyQuantity(2);
            ifoSummaryContractPerProductList[0].setCurrentBizDateBuyOrderQuantity(4);
            ifoSummaryContractPerProductList[0].setIfoDerivativeType(IfoDerivativeTypeEnum.PUT_OPTIONS);
            ifoSummaryContractPerProductList[0].setSellQuantity(8);
            
            WEB3IfoDepositCalcCondition l_ifoDepositCalcCondition = new WEB3IfoDepositCalcCondition();
            Date l_bizDate1 = WEB3DateUtility.getDate("20040715","yyyyMMdd");
            Date l_bizDate2 = WEB3DateUtility.getDate("20040716","yyyyMMdd");
            Date l_bizDate3 = WEB3DateUtility.getDate("20040719","yyyyMMdd");
            Date l_bizDate4 = WEB3DateUtility.getDate("20040720","yyyyMMdd");
            Date[] l_bizDates = {l_bizDate1, l_bizDate2, l_bizDate3, l_bizDate4};
            l_ifoDepositCalcCondition.setBizDates(l_bizDates);
            //SPAN証拠金採用の時
            l_ifoDepositCalcCondition.setSimpleSPANCalcFlag(true);
            l_ifoDepositCalcCondition.setSpanTroubleFlag(true);
            //setSPAN係数レッド
            l_ifoDepositCalcCondition.setSPANFactor(5.3);
            //基準日
            l_ifoDepositCalcCondition.setIfoDepositCalcBaseDate(1);
            l_ifoDepositCalcCondition.setSPANFactorRed(2.2);
            //新規建余力可能 = true
            l_ifoDepositCalcCondition.setNewOpenTradingPowerAvailableFlag(true);
            //必要最低証拠金)
            l_ifoDepositCalcCondition.setMinIfoDeposit(10);
            //証拠金不足メール送信済フラグ
            l_ifoDepositCalcCondition.setIfoDepositMailFlag(true);
            //l_ifoDepositCalcCondition.setLackChargeNonManagementFlag(true);
            l_ifoDepositCalcCondition.setCurrentBizIfoDepositLackCharge(100);
            
            WEB3IfoCustomerTransfer l_ifoCustomerTransfer = new WEB3IfoCustomerTransfer();
            double[] l_balances = {2, 5 ,6}; 
            l_ifoCustomerTransfer.setBalances(l_balances);
            l_ifoCustomerTransfer.setCurrentBizDateTransferAmount(100);
            l_ifoCustomerTransfer.addNextBizDateFuturesCloseProfitLoss(200);

            Field field1 = WEB3IfoDepositCalc.class.getDeclaredField("ifoSummaryContractPerProductList");
            field1.setAccessible(true);
            field1.set(depositCalc, ifoSummaryContractPerProductList);
            
            Field field2 = WEB3IfoDepositCalc.class.getDeclaredField("ifoDepositCalcCondition");
            field2.setAccessible(true);
            field2.set(depositCalc, l_ifoDepositCalcCondition);

            Field field3 = WEB3IfoDepositCalc.class.getDeclaredField("ifoCustomerTransfer");
            field3.setAccessible(true);
            field3.set(depositCalc, l_ifoCustomerTransfer);
            double l_dblReturn = depositCalc.calcIfoDepositTransferableAmount();
            assertEquals(0, l_dblReturn, 0);
        }
        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage());
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    //発注日 == 営業日[T+0]の場合
    public void testCalcIfoDepositTransferableAmount_C002()
    {
        String STR_METHOD_NAME = "testCalcIfoDepositTransferableAmount_C002";
        log.entering(STR_METHOD_NAME);
        try
        {
            //取引時間テーブル;
            TestDBUtility.deleteAll(TradingTimeRow.TYPE);
            TradingTimeParams l_TradingTimeParams1 = TestDBUtility.getTradingTimeRow();
            l_TradingTimeParams1.setInstitutionCode("0D");
            l_TradingTimeParams1.setBranchCode("123");
            l_TradingTimeParams1.setMarketCode("N1");
            l_TradingTimeParams1.setTradingTimeType("13");
            l_TradingTimeParams1.setProductCode("0");
            l_TradingTimeParams1.setBizDateType("1");
            TestDBUtility.insertWithDel(l_TradingTimeParams1);
            
            Calendar l_calendar = Calendar.getInstance();
            l_calendar.set(Calendar.YEAR,2004);
            l_calendar.set(Calendar.MONTH,6);
            l_calendar.set(Calendar.DAY_OF_MONTH,16);
            l_calendar.set(Calendar.HOUR_OF_DAY,10);
            l_calendar.set(Calendar.MINUTE,00);
            l_calendar.set(Calendar.SECOND,00);

            Timestamp l_tsBizDate = new Timestamp(l_calendar.getTimeInMillis());
            ThreadLocalSystemAttributesRegistry.setAttribute(
                    WEB3GentradeTradingTimeManagement.TIMESTAMP_TAG, l_tsBizDate);
                
                ThreadLocalSystemAttributesRegistry.setAttribute(
                    WEB3GentradeTradingTimeManagement.BASE_TIMESTAMP_FOR_ORDER_BIZ_DATE,l_tsBizDate);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.tc.gentrade.stdimpls.TradingSystemImpl",
                "getSystemTimestamp",
                new Class[] {},
                l_tsBizDate);
            
            WEB3IfoSummaryContractPerProduct[] ifoSummaryContractPerProductList =
                new WEB3IfoSummaryContractPerProduct[1];
            ifoSummaryContractPerProductList[0] = new WEB3IfoSummaryContractPerProduct();
            ifoSummaryContractPerProductList[0].setCurrentPrice(233);
            ifoSummaryContractPerProductList[0].setUnitSize(6.66);
            ifoSummaryContractPerProductList[0].setBuyQuantity(2);
            ifoSummaryContractPerProductList[0].setCurrentBizDateBuyOrderQuantity(4);
            ifoSummaryContractPerProductList[0].setIfoDerivativeType(IfoDerivativeTypeEnum.PUT_OPTIONS);
            ifoSummaryContractPerProductList[0].setSellQuantity(8);
            
            WEB3IfoDepositCalcCondition l_ifoDepositCalcCondition = new WEB3IfoDepositCalcCondition();
            Date l_bizDate1 = WEB3DateUtility.getDate("20040716","yyyyMMdd");
            Date l_bizDate2 = WEB3DateUtility.getDate("20040719","yyyyMMdd");
            Date l_bizDate3 = WEB3DateUtility.getDate("20040720","yyyyMMdd");
            Date l_bizDate4 = WEB3DateUtility.getDate("20040721","yyyyMMdd");
            Date[] l_bizDates = {l_bizDate1, l_bizDate2, l_bizDate3, l_bizDate4};
            l_ifoDepositCalcCondition.setBizDates(l_bizDates);
            //SPAN証拠金採用の時
            l_ifoDepositCalcCondition.setSimpleSPANCalcFlag(true);
            l_ifoDepositCalcCondition.setSpanTroubleFlag(true);
            //setSPAN係数レッド
            l_ifoDepositCalcCondition.setSPANFactor(5.3);
            //基準日
            l_ifoDepositCalcCondition.setIfoDepositCalcBaseDate(1);
 
            l_ifoDepositCalcCondition.setSPANFactorRed(2.2);
            //振替余力係数
            l_ifoDepositCalcCondition.setTransferPowerFactor(2);
            //新規建余力可能 = true
            l_ifoDepositCalcCondition.setNewOpenTradingPowerAvailableFlag(true);
            //必要最低証拠金)
            l_ifoDepositCalcCondition.setMinIfoDeposit(10);
            //証拠金不足メール送信済フラグ
            l_ifoDepositCalcCondition.setIfoDepositMailFlag(true);
            l_ifoDepositCalcCondition.setLackChargeNonManagementFlag(true);
            l_ifoDepositCalcCondition.setCurrentBizIfoDepositLackCharge(100);
            
            WEB3IfoCustomerTransfer l_ifoCustomerTransfer = new WEB3IfoCustomerTransfer();
            double[] l_balances = {2, 5 ,6}; 
            l_ifoCustomerTransfer.setBalances(l_balances);
            l_ifoCustomerTransfer.setCurrentBizDateTransferAmount(100);
            l_ifoCustomerTransfer.addNextBizDateFuturesCloseProfitLoss(200);

            Field field1 = WEB3IfoDepositCalc.class.getDeclaredField("ifoSummaryContractPerProductList");
            field1.setAccessible(true);
            field1.set(depositCalc, ifoSummaryContractPerProductList);
            
            Field field2 = WEB3IfoDepositCalc.class.getDeclaredField("ifoDepositCalcCondition");
            field2.setAccessible(true);
            field2.set(depositCalc, l_ifoDepositCalcCondition);

            Field field3 = WEB3IfoDepositCalc.class.getDeclaredField("ifoCustomerTransfer");
            field3.setAccessible(true);
            field3.set(depositCalc, l_ifoCustomerTransfer);
            double l_dblReturn = depositCalc.calcIfoDepositTransferableAmount();
            assertEquals(0, l_dblReturn, 0);
        }
        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage());
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    //発注日 != 営業日[T+0]の場合
    public void testCalcIfoDepositTransferableAmount_C003()
    {
        String STR_METHOD_NAME = "testCalcIfoDepositTransferableAmount_C003";
        log.entering(STR_METHOD_NAME);
        try
        {
            //取引時間テーブル;
            TestDBUtility.deleteAll(TradingTimeRow.TYPE);
            TradingTimeParams l_TradingTimeParams1 = TestDBUtility.getTradingTimeRow();
            l_TradingTimeParams1.setInstitutionCode("0D");
            l_TradingTimeParams1.setBranchCode("123");
            l_TradingTimeParams1.setMarketCode("N1");
            l_TradingTimeParams1.setTradingTimeType("13");
            l_TradingTimeParams1.setProductCode("0");
            l_TradingTimeParams1.setBizDateType("1");
            TestDBUtility.insertWithDel(l_TradingTimeParams1);
            
            Calendar l_calendar = Calendar.getInstance();
            l_calendar.set(Calendar.YEAR,2004);
            l_calendar.set(Calendar.MONTH,6);
            l_calendar.set(Calendar.DAY_OF_MONTH,16);
            l_calendar.set(Calendar.HOUR_OF_DAY,10);
            l_calendar.set(Calendar.MINUTE,00);
            l_calendar.set(Calendar.SECOND,00);

            Timestamp l_tsBizDate = new Timestamp(l_calendar.getTimeInMillis());

            ThreadLocalSystemAttributesRegistry.setAttribute(
                    WEB3GentradeTradingTimeManagement.TIMESTAMP_TAG, l_tsBizDate);
                
                ThreadLocalSystemAttributesRegistry.setAttribute(
                    WEB3GentradeTradingTimeManagement.BASE_TIMESTAMP_FOR_ORDER_BIZ_DATE,l_tsBizDate);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.tc.gentrade.stdimpls.TradingSystemImpl",
                "getSystemTimestamp",
                new Class[] {},
                l_tsBizDate);
            
            WEB3IfoSummaryContractPerProduct[] ifoSummaryContractPerProductList =
                new WEB3IfoSummaryContractPerProduct[1];
            ifoSummaryContractPerProductList[0] = new WEB3IfoSummaryContractPerProduct();
            ifoSummaryContractPerProductList[0].setCurrentPrice(233);
            ifoSummaryContractPerProductList[0].setUnitSize(6.66);
            ifoSummaryContractPerProductList[0].setBuyQuantity(2);
            ifoSummaryContractPerProductList[0].setCurrentBizDateBuyOrderQuantity(4);
            ifoSummaryContractPerProductList[0].setIfoDerivativeType(IfoDerivativeTypeEnum.PUT_OPTIONS);
            ifoSummaryContractPerProductList[0].setSellQuantity(8);
            
            WEB3IfoDepositCalcCondition l_ifoDepositCalcCondition = new WEB3IfoDepositCalcCondition();
            Date l_bizDate1 = WEB3DateUtility.getDate("20040715","yyyyMMdd");
            Date l_bizDate2 = WEB3DateUtility.getDate("20040716","yyyyMMdd");
            Date l_bizDate3 = WEB3DateUtility.getDate("20040719","yyyyMMdd");
            Date l_bizDate4 = WEB3DateUtility.getDate("20040720","yyyyMMdd");
            Date[] l_bizDates = {l_bizDate1, l_bizDate2, l_bizDate3, l_bizDate4};
            l_ifoDepositCalcCondition.setBizDates(l_bizDates);
            //SPAN証拠金採用の時
            l_ifoDepositCalcCondition.setSimpleSPANCalcFlag(true);
            l_ifoDepositCalcCondition.setSpanTroubleFlag(true);
            //setSPAN係数レッド
            l_ifoDepositCalcCondition.setSPANFactor(5.3);
            //基準日
            l_ifoDepositCalcCondition.setIfoDepositCalcBaseDate(1);
            l_ifoDepositCalcCondition.setSPANFactorRed(2.2);
            //新規建余力可能 = true
            l_ifoDepositCalcCondition.setNewOpenTradingPowerAvailableFlag(true);
            //必要最低証拠金)
            l_ifoDepositCalcCondition.setMinIfoDeposit(10);
            //証拠金不足メール送信済フラグ
            l_ifoDepositCalcCondition.setIfoDepositMailFlag(true);
            l_ifoDepositCalcCondition.setLackChargeNonManagementFlag(true);
            l_ifoDepositCalcCondition.setCurrentBizIfoDepositLackCharge(100);
            
            WEB3IfoCustomerTransfer l_ifoCustomerTransfer = new WEB3IfoCustomerTransfer();
            double[] l_balances = {2, 5 ,6}; 
            l_ifoCustomerTransfer.setBalances(l_balances);
            l_ifoCustomerTransfer.setCurrentBizDateTransferAmount(100);
            l_ifoCustomerTransfer.addNextBizDateFuturesCloseProfitLoss(200);

            Field field1 = WEB3IfoDepositCalc.class.getDeclaredField("ifoSummaryContractPerProductList");
            field1.setAccessible(true);
            field1.set(depositCalc, ifoSummaryContractPerProductList);
            
            Field field2 = WEB3IfoDepositCalc.class.getDeclaredField("ifoDepositCalcCondition");
            field2.setAccessible(true);
            field2.set(depositCalc, l_ifoDepositCalcCondition);

            Field field3 = WEB3IfoDepositCalc.class.getDeclaredField("ifoCustomerTransfer");
            field3.setAccessible(true);
            field3.set(depositCalc, l_ifoCustomerTransfer);
            double l_dblReturn = depositCalc.calcIfoDepositTransferableAmount();
            assertEquals(305, l_dblReturn, 0);
        }
        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage());
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    //指定日が範囲外の場合(nが0以上2以下でない場合)、0を返却する
    public void  testCalcIfoDepositTransferableAmount1()
    {
        String STR_METHOD_NAME = "testCalcIfoDepositTransferableAmount1";
        log.entering(STR_METHOD_NAME);
        try
        {
            
            //取引時間テーブル;
            TestDBUtility.deleteAll(TradingTimeRow.TYPE);
            TradingTimeParams l_TradingTimeParams1 = TestDBUtility.getTradingTimeRow();
            l_TradingTimeParams1.setInstitutionCode("0D");
            l_TradingTimeParams1.setBranchCode("123");
            l_TradingTimeParams1.setMarketCode("N1");
            l_TradingTimeParams1.setTradingTimeType("13");
            l_TradingTimeParams1.setProductCode("0");
            l_TradingTimeParams1.setBizDateType("1");
            TestDBUtility.insertWithDel(l_TradingTimeParams1);
            
            Calendar l_calendar = Calendar.getInstance();
            l_calendar.set(Calendar.YEAR,2004);
            l_calendar.set(Calendar.MONTH,6);
            l_calendar.set(Calendar.DAY_OF_MONTH,16);
            l_calendar.set(Calendar.HOUR_OF_DAY,10);
            l_calendar.set(Calendar.MINUTE,00);
            l_calendar.set(Calendar.SECOND,00);

            Timestamp l_tsBizDate = new Timestamp(l_calendar.getTimeInMillis());

            ThreadLocalSystemAttributesRegistry.setAttribute(
                    WEB3GentradeTradingTimeManagement.TIMESTAMP_TAG, l_tsBizDate);
                
                ThreadLocalSystemAttributesRegistry.setAttribute(
                    WEB3GentradeTradingTimeManagement.BASE_TIMESTAMP_FOR_ORDER_BIZ_DATE,l_tsBizDate);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.tc.gentrade.stdimpls.TradingSystemImpl",
                "getSystemTimestamp",
                new Class[] {},
                l_tsBizDate);
            
            WEB3IfoSummaryContractPerProduct[] ifoSummaryContractPerProductList =
                new WEB3IfoSummaryContractPerProduct[1];
            ifoSummaryContractPerProductList[0] = new WEB3IfoSummaryContractPerProduct();
            ifoSummaryContractPerProductList[0].setCurrentPrice(233);
            ifoSummaryContractPerProductList[0].setUnitSize(6.66);
            ifoSummaryContractPerProductList[0].setBuyQuantity(2);
            ifoSummaryContractPerProductList[0].setCurrentBizDateBuyOrderQuantity(4);
            ifoSummaryContractPerProductList[0].setIfoDerivativeType(IfoDerivativeTypeEnum.PUT_OPTIONS);
            ifoSummaryContractPerProductList[0].setSellQuantity(8);
            
            WEB3IfoDepositCalcCondition l_ifoDepositCalcCondition = new WEB3IfoDepositCalcCondition();
            Date l_bizDate1 = WEB3DateUtility.getDate("20040715","yyyyMMdd");
            Date l_bizDate2 = WEB3DateUtility.getDate("20040716","yyyyMMdd");
            Date l_bizDate3 = WEB3DateUtility.getDate("20040719","yyyyMMdd");
            Date l_bizDate4 = WEB3DateUtility.getDate("20040720","yyyyMMdd");
            Date[] l_bizDates = {l_bizDate1, l_bizDate2, l_bizDate3, l_bizDate4};
            l_ifoDepositCalcCondition.setBizDates(l_bizDates);
            //SPAN証拠金採用の時
            l_ifoDepositCalcCondition.setSimpleSPANCalcFlag(true);
            l_ifoDepositCalcCondition.setSpanTroubleFlag(true);
            //setSPAN係数レッド
            l_ifoDepositCalcCondition.setSPANFactor(5.3);
            //基準日
            l_ifoDepositCalcCondition.setIfoDepositCalcBaseDate(1);
            l_ifoDepositCalcCondition.setSPANFactorRed(2.2);
            //新規建余力可能 = true
            l_ifoDepositCalcCondition.setNewOpenTradingPowerAvailableFlag(true);
            //必要最低証拠金)
            l_ifoDepositCalcCondition.setMinIfoDeposit(10);
            //証拠金不足メール送信済フラグ
            l_ifoDepositCalcCondition.setIfoDepositMailFlag(true);
            l_ifoDepositCalcCondition.setLackChargeNonManagementFlag(true);
            l_ifoDepositCalcCondition.setCurrentBizIfoDepositLackCharge(100);
            
            WEB3IfoCustomerTransfer l_ifoCustomerTransfer = new WEB3IfoCustomerTransfer();
            double[] l_balances = {2, 5 ,6}; 
            l_ifoCustomerTransfer.setBalances(l_balances);
            l_ifoCustomerTransfer.setCurrentBizDateTransferAmount(100);
            l_ifoCustomerTransfer.addNextBizDateFuturesCloseProfitLoss(200);

            Field field1 = WEB3IfoDepositCalc.class.getDeclaredField("ifoSummaryContractPerProductList");
            field1.setAccessible(true);
            field1.set(depositCalc, ifoSummaryContractPerProductList);
            
            Field field2 = WEB3IfoDepositCalc.class.getDeclaredField("ifoDepositCalcCondition");
            field2.setAccessible(true);
            field2.set(depositCalc, l_ifoDepositCalcCondition);

            Field field3 = WEB3IfoDepositCalc.class.getDeclaredField("ifoCustomerTransfer");
            field3.setAccessible(true);
            field3.set(depositCalc, l_ifoCustomerTransfer);
            
            int l_intReservedDate = 4;
            double l_dblReturn = depositCalc.calcIfoDepositTransferableAmount(l_intReservedDate);
            assertEquals(0, l_dblReturn, 0);
        }
        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage());
            fail();
        }

    }
    
    //指定日が0
    public void  testCalcIfoDepositTransferableAmount2()
    {
        String STR_METHOD_NAME = "testCalcIfoDepositTransferableAmount2";
        log.entering(STR_METHOD_NAME);
        try
        {
            
            //取引時間テーブル;
            TestDBUtility.deleteAll(TradingTimeRow.TYPE);
            TradingTimeParams l_TradingTimeParams1 = TestDBUtility.getTradingTimeRow();
            l_TradingTimeParams1.setInstitutionCode("0D");
            l_TradingTimeParams1.setBranchCode("123");
            l_TradingTimeParams1.setMarketCode("N1");
            l_TradingTimeParams1.setTradingTimeType("13");
            l_TradingTimeParams1.setProductCode("0");
            l_TradingTimeParams1.setBizDateType("1");
            TestDBUtility.insertWithDel(l_TradingTimeParams1);
            
            Calendar l_calendar = Calendar.getInstance();
            l_calendar.set(Calendar.YEAR,2004);
            l_calendar.set(Calendar.MONTH,6);
            l_calendar.set(Calendar.DAY_OF_MONTH,16);
            l_calendar.set(Calendar.HOUR_OF_DAY,10);
            l_calendar.set(Calendar.MINUTE,00);
            l_calendar.set(Calendar.SECOND,00);

            Timestamp l_tsBizDate = new Timestamp(l_calendar.getTimeInMillis());

            ThreadLocalSystemAttributesRegistry.setAttribute(
                    WEB3GentradeTradingTimeManagement.TIMESTAMP_TAG, l_tsBizDate);
                
                ThreadLocalSystemAttributesRegistry.setAttribute(
                    WEB3GentradeTradingTimeManagement.BASE_TIMESTAMP_FOR_ORDER_BIZ_DATE,l_tsBizDate);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.tc.gentrade.stdimpls.TradingSystemImpl",
                "getSystemTimestamp",
                new Class[] {},
                l_tsBizDate);
            
            WEB3IfoSummaryContractPerProduct[] ifoSummaryContractPerProductList =
                new WEB3IfoSummaryContractPerProduct[1];
            ifoSummaryContractPerProductList[0] = new WEB3IfoSummaryContractPerProduct();
            ifoSummaryContractPerProductList[0].setCurrentPrice(233);
            ifoSummaryContractPerProductList[0].setUnitSize(6.66);
            ifoSummaryContractPerProductList[0].setBuyQuantity(2);
            ifoSummaryContractPerProductList[0].setCurrentBizDateBuyOrderQuantity(4);
            ifoSummaryContractPerProductList[0].setIfoDerivativeType(IfoDerivativeTypeEnum.PUT_OPTIONS);
            ifoSummaryContractPerProductList[0].setSellQuantity(8);
            
            WEB3IfoDepositCalcCondition l_ifoDepositCalcCondition = new WEB3IfoDepositCalcCondition();
            Date l_bizDate1 = WEB3DateUtility.getDate("20040715","yyyyMMdd");
            Date l_bizDate2 = WEB3DateUtility.getDate("20040716","yyyyMMdd");
            Date l_bizDate3 = WEB3DateUtility.getDate("20040719","yyyyMMdd");
            Date l_bizDate4 = WEB3DateUtility.getDate("20040720","yyyyMMdd");
            Date[] l_bizDates = {l_bizDate1, l_bizDate2, l_bizDate3, l_bizDate4};
            l_ifoDepositCalcCondition.setBizDates(l_bizDates);
            //SPAN証拠金採用の時
            l_ifoDepositCalcCondition.setSimpleSPANCalcFlag(true);
            l_ifoDepositCalcCondition.setSpanTroubleFlag(true);
            //setSPAN係数レッド
            l_ifoDepositCalcCondition.setSPANFactor(5.3);
            //基準日
            l_ifoDepositCalcCondition.setIfoDepositCalcBaseDate(1);
            l_ifoDepositCalcCondition.setSPANFactorRed(2.2);
            //新規建余力可能 = true
            l_ifoDepositCalcCondition.setNewOpenTradingPowerAvailableFlag(true);
            //必要最低証拠金)
            l_ifoDepositCalcCondition.setMinIfoDeposit(10);
            //証拠金不足メール送信済フラグ
            l_ifoDepositCalcCondition.setIfoDepositMailFlag(true);
            l_ifoDepositCalcCondition.setLackChargeNonManagementFlag(true);
            l_ifoDepositCalcCondition.setCurrentBizIfoDepositLackCharge(100);
            
            WEB3IfoCustomerTransfer l_ifoCustomerTransfer = new WEB3IfoCustomerTransfer();
            double[] l_balances = {2, 5 ,6}; 
            l_ifoCustomerTransfer.setBalances(l_balances);
            l_ifoCustomerTransfer.setCurrentBizDateTransferAmount(100);
            l_ifoCustomerTransfer.addNextBizDateFuturesCloseProfitLoss(200);

            Field field1 = WEB3IfoDepositCalc.class.getDeclaredField("ifoSummaryContractPerProductList");
            field1.setAccessible(true);
            field1.set(depositCalc, ifoSummaryContractPerProductList);
            
            Field field2 = WEB3IfoDepositCalc.class.getDeclaredField("ifoDepositCalcCondition");
            field2.setAccessible(true);
            field2.set(depositCalc, l_ifoDepositCalcCondition);

            Field field3 = WEB3IfoDepositCalc.class.getDeclaredField("ifoCustomerTransfer");
            field3.setAccessible(true);
            field3.set(depositCalc, l_ifoCustomerTransfer);
            
            int l_intReservedDate = 0;
            double l_dblReturn = depositCalc.calcIfoDepositTransferableAmount(l_intReservedDate);
            assertEquals(102, l_dblReturn, 0);
        }
        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage());
            fail();
        }

    }
    
    //指定日が2
    public void  testCalcIfoDepositTransferableAmount3()
    {
        String STR_METHOD_NAME = "testCalcIfoDepositTransferableAmount3";
        log.entering(STR_METHOD_NAME);
        try
        {
            
            //取引時間テーブル;
            TestDBUtility.deleteAll(TradingTimeRow.TYPE);
            TradingTimeParams l_TradingTimeParams1 = TestDBUtility.getTradingTimeRow();
            l_TradingTimeParams1.setInstitutionCode("0D");
            l_TradingTimeParams1.setBranchCode("123");
            l_TradingTimeParams1.setMarketCode("N1");
            l_TradingTimeParams1.setTradingTimeType("13");
            l_TradingTimeParams1.setProductCode("0");
            l_TradingTimeParams1.setBizDateType("1");
            TestDBUtility.insertWithDel(l_TradingTimeParams1);
            
            Calendar l_calendar = Calendar.getInstance();
            l_calendar.set(Calendar.YEAR,2004);
            l_calendar.set(Calendar.MONTH,6);
            l_calendar.set(Calendar.DAY_OF_MONTH,16);
            l_calendar.set(Calendar.HOUR_OF_DAY,10);
            l_calendar.set(Calendar.MINUTE,00);
            l_calendar.set(Calendar.SECOND,00);

            Timestamp l_tsBizDate = new Timestamp(l_calendar.getTimeInMillis());

            ThreadLocalSystemAttributesRegistry.setAttribute(
                    WEB3GentradeTradingTimeManagement.TIMESTAMP_TAG, l_tsBizDate);
                
                ThreadLocalSystemAttributesRegistry.setAttribute(
                    WEB3GentradeTradingTimeManagement.BASE_TIMESTAMP_FOR_ORDER_BIZ_DATE,l_tsBizDate);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.tc.gentrade.stdimpls.TradingSystemImpl",
                "getSystemTimestamp",
                new Class[] {},
                l_tsBizDate);
            
            WEB3IfoSummaryContractPerProduct[] ifoSummaryContractPerProductList =
                new WEB3IfoSummaryContractPerProduct[1];
            ifoSummaryContractPerProductList[0] = new WEB3IfoSummaryContractPerProduct();
            ifoSummaryContractPerProductList[0].setCurrentPrice(233);
            ifoSummaryContractPerProductList[0].setUnitSize(6.66);
            ifoSummaryContractPerProductList[0].setBuyQuantity(2);
            ifoSummaryContractPerProductList[0].setCurrentBizDateBuyOrderQuantity(4);
            ifoSummaryContractPerProductList[0].setIfoDerivativeType(IfoDerivativeTypeEnum.PUT_OPTIONS);
            ifoSummaryContractPerProductList[0].setSellQuantity(8);
            
            WEB3IfoDepositCalcCondition l_ifoDepositCalcCondition = new WEB3IfoDepositCalcCondition();
            Date l_bizDate1 = WEB3DateUtility.getDate("20040715","yyyyMMdd");
            Date l_bizDate2 = WEB3DateUtility.getDate("20040716","yyyyMMdd");
            Date l_bizDate3 = WEB3DateUtility.getDate("20040719","yyyyMMdd");
            Date l_bizDate4 = WEB3DateUtility.getDate("20040720","yyyyMMdd");
            Date[] l_bizDates = {l_bizDate1, l_bizDate2, l_bizDate3, l_bizDate4};
            l_ifoDepositCalcCondition.setBizDates(l_bizDates);
            //SPAN証拠金採用の時
            l_ifoDepositCalcCondition.setSimpleSPANCalcFlag(true);
            l_ifoDepositCalcCondition.setSpanTroubleFlag(true);
            //setSPAN係数レッド
            l_ifoDepositCalcCondition.setSPANFactor(5.3);
            //基準日
            l_ifoDepositCalcCondition.setIfoDepositCalcBaseDate(1);
            l_ifoDepositCalcCondition.setSPANFactorRed(2.2);
            //新規建余力可能 = true
            l_ifoDepositCalcCondition.setNewOpenTradingPowerAvailableFlag(true);
            //必要最低証拠金)
            l_ifoDepositCalcCondition.setMinIfoDeposit(10);
            //証拠金不足メール送信済フラグ
            l_ifoDepositCalcCondition.setIfoDepositMailFlag(true);
            l_ifoDepositCalcCondition.setLackChargeNonManagementFlag(true);
            l_ifoDepositCalcCondition.setCurrentBizIfoDepositLackCharge(100);
            
            WEB3IfoCustomerTransfer l_ifoCustomerTransfer = new WEB3IfoCustomerTransfer();
            double[] l_balances = {2, 5 ,6}; 
            l_ifoCustomerTransfer.setBalances(l_balances);
            l_ifoCustomerTransfer.setCurrentBizDateTransferAmount(100);
            l_ifoCustomerTransfer.addNextBizDateFuturesCloseProfitLoss(200);

            Field field1 = WEB3IfoDepositCalc.class.getDeclaredField("ifoSummaryContractPerProductList");
            field1.setAccessible(true);
            field1.set(depositCalc, ifoSummaryContractPerProductList);
            
            Field field2 = WEB3IfoDepositCalc.class.getDeclaredField("ifoDepositCalcCondition");
            field2.setAccessible(true);
            field2.set(depositCalc, l_ifoDepositCalcCondition);

            Field field3 = WEB3IfoDepositCalc.class.getDeclaredField("ifoCustomerTransfer");
            field3.setAccessible(true);
            field3.set(depositCalc, l_ifoCustomerTransfer);
            
            int l_intReservedDate = 2;
            double l_dblReturn = depositCalc.calcIfoDepositTransferableAmount(l_intReservedDate);
            assertEquals(306, l_dblReturn, 0);
        }
        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage());
            fail();
        }

    }
    
//    正確的買ポジション可能数量返回
//    0未満の場合、小数点以下切り上げとする
//    建玉/注文(*)が存在しない
    public void testcalcPossibleBuyQty()
    {
        String STR_METHOD_NAME = "testcalcPossibleBuyQty";
        log.entering(STR_METHOD_NAME);
        try
        {
            WEB3IfoSummaryContractPerIndex[] l_ifoSummaryContractPerIndexLists = null;
            WEB3IfoSummaryContractPerProduct[] ifoSummaryContractPerProductList =
                new WEB3IfoSummaryContractPerProduct[1];
            ifoSummaryContractPerProductList[0] = new WEB3IfoSummaryContractPerProduct();
            ifoSummaryContractPerProductList[0].setCurrentPrice(233);
            ifoSummaryContractPerProductList[0].setUnitSize(6.66);
            ifoSummaryContractPerProductList[0].setBuyQuantity(2);
            ifoSummaryContractPerProductList[0].setCurrentBizDateBuyOrderQuantity(4);
            ifoSummaryContractPerProductList[0].setIfoDerivativeType(IfoDerivativeTypeEnum.PUT_OPTIONS);
            ifoSummaryContractPerProductList[0].setSellQuantity(8);
            
            WEB3IfoDepositCalcCondition l_ifoDepositCalcCondition = new WEB3IfoDepositCalcCondition();
            Date l_bizDate1 = WEB3DateUtility.getDate("20040715","yyyyMMdd");
            Date l_bizDate2 = WEB3DateUtility.getDate("20040716","yyyyMMdd");
            Date l_bizDate3 = WEB3DateUtility.getDate("20040719","yyyyMMdd");
            Date l_bizDate4 = WEB3DateUtility.getDate("20040720","yyyyMMdd");
            Date[] l_bizDates = {l_bizDate1, l_bizDate2, l_bizDate3, l_bizDate4};
            l_ifoDepositCalcCondition.setBizDates(l_bizDates);
            //SPAN証拠金採用の時
            l_ifoDepositCalcCondition.setSimpleSPANCalcFlag(true);
            l_ifoDepositCalcCondition.setSpanTroubleFlag(true);
            //setSPAN係数レッド
            l_ifoDepositCalcCondition.setSPANFactor(5.3);
            //基準日
            l_ifoDepositCalcCondition.setIfoDepositCalcBaseDate(1);
            l_ifoDepositCalcCondition.setSPANFactorRed(2.2);
            //新規建余力可能 = true
            l_ifoDepositCalcCondition.setNewOpenTradingPowerAvailableFlag(true);
            //必要最低証拠金)
            l_ifoDepositCalcCondition.setMinIfoDeposit(10);
            //証拠金不足メール送信済フラグ
            l_ifoDepositCalcCondition.setIfoDepositMailFlag(true);
            l_ifoDepositCalcCondition.setLackChargeNonManagementFlag(true);
            l_ifoDepositCalcCondition.setCurrentBizIfoDepositLackCharge(100);
            
            WEB3IfoDepositCalcConditionPerIndex[] l_ifoDepositCalcConditionPerIndexList =
                new WEB3IfoDepositCalcConditionPerIndex[1];
            l_ifoDepositCalcConditionPerIndexList[0] = new WEB3IfoDepositCalcConditionPerIndex();
            l_ifoDepositCalcConditionPerIndexList[0].setUnderlyingProductCode("0005");
            l_ifoDepositCalcConditionPerIndexList[0].setIfoDepositPerUnit(2.3);
            l_ifoDepositCalcConditionPerIndexList[0].setIfoDepositPerUnitRed(11);
            l_ifoDepositCalcConditionPerIndexList[0].setIfoDepositPerUnitTemp(8);
            l_ifoDepositCalcCondition.setIfoDepositCalcPerIndexList(l_ifoDepositCalcConditionPerIndexList);
            
            WEB3IfoCustomerTransfer l_ifoCustomerTransfer = new WEB3IfoCustomerTransfer();
            double[] l_balances = {2, 5 ,6}; 
            l_ifoCustomerTransfer.setBalances(l_balances);
            l_ifoCustomerTransfer.setCurrentBizDateTransferAmount(100);
            l_ifoCustomerTransfer.addNextBizDateFuturesCloseProfitLoss(200);

            Field field1 = WEB3IfoDepositCalc.class.getDeclaredField("ifoSummaryContractPerProductList");
            field1.setAccessible(true);
            field1.set(depositCalc, ifoSummaryContractPerProductList);
            
            Field field2 = WEB3IfoDepositCalc.class.getDeclaredField("ifoDepositCalcCondition");
            field2.setAccessible(true);
            field2.set(depositCalc, l_ifoDepositCalcCondition);

            Field field3 = WEB3IfoDepositCalc.class.getDeclaredField("ifoCustomerTransfer");
            field3.setAccessible(true);
            field3.set(depositCalc, l_ifoCustomerTransfer);
            
            Field field4 = WEB3IfoDepositCalc.class.getDeclaredField("ifoSummaryContractPerIndexList");
            field4.setAccessible(true);
            field4.set(depositCalc, l_ifoSummaryContractPerIndexLists);
            
            String l_strUnderlyingProductCode = "0005";
            double l_dblReturn = depositCalc.calcPossibleBuyQty(l_strUnderlyingProductCode);
            assertEquals(-1217, l_dblReturn, 0);
        }
        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage());
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    //指定日が範囲外の場合(nが1以上2以下でない場合)、0を返却する。
    public void testcalcPossibleBuyQty_C001()
    {
        String STR_METHOD_NAME = "testcalcPossibleBuyQty_C001";
        log.entering(STR_METHOD_NAME);
        try
        {
            WEB3IfoSummaryContractPerIndex[] l_ifoSummaryContractPerIndexLists = null;
            WEB3IfoSummaryContractPerProduct[] ifoSummaryContractPerProductList =
                new WEB3IfoSummaryContractPerProduct[1];
            ifoSummaryContractPerProductList[0] = new WEB3IfoSummaryContractPerProduct();
            ifoSummaryContractPerProductList[0].setCurrentPrice(233);
            ifoSummaryContractPerProductList[0].setUnitSize(6.66);
            ifoSummaryContractPerProductList[0].setBuyQuantity(2);
            ifoSummaryContractPerProductList[0].setCurrentBizDateBuyOrderQuantity(4);
            ifoSummaryContractPerProductList[0].setIfoDerivativeType(IfoDerivativeTypeEnum.PUT_OPTIONS);
            ifoSummaryContractPerProductList[0].setSellQuantity(8);
            
            WEB3IfoDepositCalcCondition l_ifoDepositCalcCondition = new WEB3IfoDepositCalcCondition();
            Date l_bizDate1 = WEB3DateUtility.getDate("20040715","yyyyMMdd");
            Date l_bizDate2 = WEB3DateUtility.getDate("20040716","yyyyMMdd");
            Date l_bizDate3 = WEB3DateUtility.getDate("20040719","yyyyMMdd");
            Date l_bizDate4 = WEB3DateUtility.getDate("20040720","yyyyMMdd");
            Date[] l_bizDates = {l_bizDate1, l_bizDate2, l_bizDate3, l_bizDate4};
            l_ifoDepositCalcCondition.setBizDates(l_bizDates);
            //SPAN証拠金採用の時
            l_ifoDepositCalcCondition.setSimpleSPANCalcFlag(true);
            l_ifoDepositCalcCondition.setSpanTroubleFlag(true);
            //setSPAN係数レッド
            l_ifoDepositCalcCondition.setSPANFactor(5.3);
            //基準日
            l_ifoDepositCalcCondition.setIfoDepositCalcBaseDate(1);
            l_ifoDepositCalcCondition.setSPANFactorRed(2.2);
            //新規建余力可能 = true
            l_ifoDepositCalcCondition.setNewOpenTradingPowerAvailableFlag(true);
            //必要最低証拠金)
            l_ifoDepositCalcCondition.setMinIfoDeposit(10);
            //証拠金不足メール送信済フラグ
            l_ifoDepositCalcCondition.setIfoDepositMailFlag(true);
            l_ifoDepositCalcCondition.setLackChargeNonManagementFlag(true);
            l_ifoDepositCalcCondition.setCurrentBizIfoDepositLackCharge(100);
            
            WEB3IfoDepositCalcConditionPerIndex[] l_ifoDepositCalcConditionPerIndexList =
                new WEB3IfoDepositCalcConditionPerIndex[1];
            l_ifoDepositCalcConditionPerIndexList[0] = new WEB3IfoDepositCalcConditionPerIndex();
            l_ifoDepositCalcConditionPerIndexList[0].setUnderlyingProductCode("0005");
            l_ifoDepositCalcConditionPerIndexList[0].setIfoDepositPerUnit(2.3);
            l_ifoDepositCalcConditionPerIndexList[0].setIfoDepositPerUnitRed(11);
            l_ifoDepositCalcConditionPerIndexList[0].setIfoDepositPerUnitTemp(8);
            l_ifoDepositCalcCondition.setIfoDepositCalcPerIndexList(l_ifoDepositCalcConditionPerIndexList);
            
            WEB3IfoCustomerTransfer l_ifoCustomerTransfer = new WEB3IfoCustomerTransfer();
            double[] l_balances = {2, 5 ,6}; 
            l_ifoCustomerTransfer.setBalances(l_balances);
            l_ifoCustomerTransfer.setCurrentBizDateTransferAmount(100);
            l_ifoCustomerTransfer.addNextBizDateFuturesCloseProfitLoss(200);

            Field field1 = WEB3IfoDepositCalc.class.getDeclaredField("ifoSummaryContractPerProductList");
            field1.setAccessible(true);
            field1.set(depositCalc, ifoSummaryContractPerProductList);
            
            Field field2 = WEB3IfoDepositCalc.class.getDeclaredField("ifoDepositCalcCondition");
            field2.setAccessible(true);
            field2.set(depositCalc, l_ifoDepositCalcCondition);

            Field field3 = WEB3IfoDepositCalc.class.getDeclaredField("ifoCustomerTransfer");
            field3.setAccessible(true);
            field3.set(depositCalc, l_ifoCustomerTransfer);
            
            Field field4 = WEB3IfoDepositCalc.class.getDeclaredField("ifoSummaryContractPerIndexList");
            field4.setAccessible(true);
            field4.set(depositCalc, l_ifoSummaryContractPerIndexLists);
            
            String l_strUnderlyingProductCode = "0005";
            int l_intReservedDate = 4;
            double l_dblReturn = depositCalc.calcPossibleBuyQty(l_strUnderlyingProductCode, l_intReservedDate);
            assertEquals(0, l_dblReturn, 0);
        }
        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage());
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    //証拠金余力額がマイナスの場合
    public void testcalcPossibleBuyQty_C002()
    {
        String STR_METHOD_NAME = "testcalcPossibleBuyQty_C002";
        log.entering(STR_METHOD_NAME);
        try
        {
            WEB3IfoSummaryContractPerIndex[] l_ifoSummaryContractPerIndexLists =
                new WEB3IfoSummaryContractPerIndex[1];
            l_ifoSummaryContractPerIndexLists[0] = new WEB3IfoSummaryContractPerIndex();
            l_ifoSummaryContractPerIndexLists[0].setCurrentBizDateFuturesBuyOrderQuantity(10);
            l_ifoSummaryContractPerIndexLists[0].setIfoDepositPerUnit(1000);
            WEB3IfoSummaryContractPerProduct[] ifoSummaryContractPerProductList =
                new WEB3IfoSummaryContractPerProduct[1];
            ifoSummaryContractPerProductList[0] = new WEB3IfoSummaryContractPerProduct();
            ifoSummaryContractPerProductList[0].setCurrentPrice(233);
            ifoSummaryContractPerProductList[0].setUnitSize(6.66);
            ifoSummaryContractPerProductList[0].setBuyQuantity(2);
            ifoSummaryContractPerProductList[0].setCurrentBizDateBuyOrderQuantity(4);
            ifoSummaryContractPerProductList[0].setIfoDerivativeType(IfoDerivativeTypeEnum.PUT_OPTIONS);
            ifoSummaryContractPerProductList[0].setSellQuantity(8);
            
            WEB3IfoDepositCalcCondition l_ifoDepositCalcCondition = new WEB3IfoDepositCalcCondition();
            Date l_bizDate1 = WEB3DateUtility.getDate("20040715","yyyyMMdd");
            Date l_bizDate2 = WEB3DateUtility.getDate("20040716","yyyyMMdd");
            Date l_bizDate3 = WEB3DateUtility.getDate("20040719","yyyyMMdd");
            Date l_bizDate4 = WEB3DateUtility.getDate("20040720","yyyyMMdd");
            Date[] l_bizDates = {l_bizDate1, l_bizDate2, l_bizDate3, l_bizDate4};
            l_ifoDepositCalcCondition.setBizDates(l_bizDates);
            //SPAN証拠金採用の時
            l_ifoDepositCalcCondition.setSimpleSPANCalcFlag(true);
            l_ifoDepositCalcCondition.setSpanTroubleFlag(true);
            //setSPAN係数レッド
            l_ifoDepositCalcCondition.setSPANFactor(5.3);
            //基準日
            l_ifoDepositCalcCondition.setIfoDepositCalcBaseDate(1);
            l_ifoDepositCalcCondition.setSPANFactorRed(2.2);
            //新規建余力可能 = true
            l_ifoDepositCalcCondition.setNewOpenTradingPowerAvailableFlag(true);
            //必要最低証拠金)
            l_ifoDepositCalcCondition.setMinIfoDeposit(10);
            //証拠金不足メール送信済フラグ
            l_ifoDepositCalcCondition.setIfoDepositMailFlag(true);
            l_ifoDepositCalcCondition.setLackChargeNonManagementFlag(true);
            l_ifoDepositCalcCondition.setCurrentBizIfoDepositLackCharge(100);
            
            WEB3IfoDepositCalcConditionPerIndex[] l_ifoDepositCalcConditionPerIndexList =
                new WEB3IfoDepositCalcConditionPerIndex[1];
            l_ifoDepositCalcConditionPerIndexList[0] = new WEB3IfoDepositCalcConditionPerIndex();
            l_ifoDepositCalcConditionPerIndexList[0].setUnderlyingProductCode("0005");
            l_ifoDepositCalcConditionPerIndexList[0].setIfoDepositPerUnit(2.3);
            l_ifoDepositCalcConditionPerIndexList[0].setIfoDepositPerUnitRed(11);
            l_ifoDepositCalcConditionPerIndexList[0].setIfoDepositPerUnitTemp(8);
            l_ifoDepositCalcCondition.setIfoDepositCalcPerIndexList(l_ifoDepositCalcConditionPerIndexList);
            
            WEB3IfoCustomerTransfer l_ifoCustomerTransfer = new WEB3IfoCustomerTransfer();
            double[] l_balances = {2, 5 ,6}; 
            l_ifoCustomerTransfer.setBalances(l_balances);
            l_ifoCustomerTransfer.setCurrentBizDateTransferAmount(100);
            l_ifoCustomerTransfer.addNextBizDateFuturesCloseProfitLoss(200);

            Field field1 = WEB3IfoDepositCalc.class.getDeclaredField("ifoSummaryContractPerProductList");
            field1.setAccessible(true);
            field1.set(depositCalc, ifoSummaryContractPerProductList);
            
            Field field2 = WEB3IfoDepositCalc.class.getDeclaredField("ifoDepositCalcCondition");
            field2.setAccessible(true);
            field2.set(depositCalc, l_ifoDepositCalcCondition);

            Field field3 = WEB3IfoDepositCalc.class.getDeclaredField("ifoCustomerTransfer");
            field3.setAccessible(true);
            field3.set(depositCalc, l_ifoCustomerTransfer);
            
            Field field4 = WEB3IfoDepositCalc.class.getDeclaredField("ifoSummaryContractPerIndexList");
            field4.setAccessible(true);
            field4.set(depositCalc, l_ifoSummaryContractPerIndexLists);
            
            String l_strUnderlyingProductCode = "0005";
            int l_intReservedDate = 1;
            double l_dblReturn = depositCalc.calcPossibleBuyQty(l_strUnderlyingProductCode, l_intReservedDate);
            assertEquals(-5565, l_dblReturn, 0);
        }
        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage());
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
//    証拠金余力額が正?の場合
//    建玉/注文(*)が存在
//    正確的買ポジション可能数量返回
//    計算結果が0以上の場合、小数点以下切り捨て
    public void testcalcPossibleBuyQty_C003()
    {
        String STR_METHOD_NAME = "testcalcPossibleBuyQty_C003";
        log.entering(STR_METHOD_NAME);
        try
        {
            WEB3IfoSummaryContractPerIndex[] l_ifoSummaryContractPerIndexLists =
                new WEB3IfoSummaryContractPerIndex[1];
            l_ifoSummaryContractPerIndexLists[0] = new WEB3IfoSummaryContractPerIndex();
            l_ifoSummaryContractPerIndexLists[0].setCurrentBizDateFuturesBuyOrderQuantity(10);
            l_ifoSummaryContractPerIndexLists[0].setIfoDepositPerUnit(1000);
            WEB3IfoSummaryContractPerProduct[] ifoSummaryContractPerProductList =
                new WEB3IfoSummaryContractPerProduct[1];
            ifoSummaryContractPerProductList[0] = new WEB3IfoSummaryContractPerProduct();
            ifoSummaryContractPerProductList[0].setCurrentPrice(233);
            ifoSummaryContractPerProductList[0].setUnitSize(6.66);
            ifoSummaryContractPerProductList[0].setBuyQuantity(2);
            ifoSummaryContractPerProductList[0].setCurrentBizDateBuyOrderQuantity(4);
            ifoSummaryContractPerProductList[0].setIfoDerivativeType(IfoDerivativeTypeEnum.PUT_OPTIONS);
            ifoSummaryContractPerProductList[0].setSellQuantity(8);
            
            WEB3IfoDepositCalcCondition l_ifoDepositCalcCondition = new WEB3IfoDepositCalcCondition();
            Date l_bizDate1 = WEB3DateUtility.getDate("20040715","yyyyMMdd");
            Date l_bizDate2 = WEB3DateUtility.getDate("20040716","yyyyMMdd");
            Date l_bizDate3 = WEB3DateUtility.getDate("20040719","yyyyMMdd");
            Date l_bizDate4 = WEB3DateUtility.getDate("20040720","yyyyMMdd");
            Date[] l_bizDates = {l_bizDate1, l_bizDate2, l_bizDate3, l_bizDate4};
            l_ifoDepositCalcCondition.setBizDates(l_bizDates);
            //SPAN証拠金採用の時
            l_ifoDepositCalcCondition.setSimpleSPANCalcFlag(true);
            l_ifoDepositCalcCondition.setSpanTroubleFlag(true);
            //setSPAN係数レッド
            l_ifoDepositCalcCondition.setSPANFactor(5.3);
            //基準日
            l_ifoDepositCalcCondition.setIfoDepositCalcBaseDate(1);
            l_ifoDepositCalcCondition.setSPANFactorRed(2.2);
            //新規建余力可能 = true
            l_ifoDepositCalcCondition.setNewOpenTradingPowerAvailableFlag(true);
            //必要最低証拠金)
            l_ifoDepositCalcCondition.setMinIfoDeposit(10);
            //証拠金不足メール送信済フラグ
            l_ifoDepositCalcCondition.setIfoDepositMailFlag(true);
            l_ifoDepositCalcCondition.setLackChargeNonManagementFlag(true);
            l_ifoDepositCalcCondition.setCurrentBizIfoDepositLackCharge(100);
            
            WEB3IfoDepositCalcConditionPerIndex[] l_ifoDepositCalcConditionPerIndexList =
                new WEB3IfoDepositCalcConditionPerIndex[1];
            l_ifoDepositCalcConditionPerIndexList[0] = new WEB3IfoDepositCalcConditionPerIndex();
            l_ifoDepositCalcConditionPerIndexList[0].setUnderlyingProductCode("0005");
            l_ifoDepositCalcConditionPerIndexList[0].setIfoDepositPerUnit(2.3);
            l_ifoDepositCalcConditionPerIndexList[0].setIfoDepositPerUnitRed(11);
            l_ifoDepositCalcConditionPerIndexList[0].setIfoDepositPerUnitTemp(8);
            l_ifoDepositCalcCondition.setIfoDepositCalcPerIndexList(l_ifoDepositCalcConditionPerIndexList);
            
            WEB3IfoCustomerTransfer l_ifoCustomerTransfer = new WEB3IfoCustomerTransfer();
            double[] l_balances = {222222, 555555 ,666666}; 
            l_ifoCustomerTransfer.setBalances(l_balances);
            l_ifoCustomerTransfer.setCurrentBizDateTransferAmount(100000);
            l_ifoCustomerTransfer.addNextBizDateFuturesCloseProfitLoss(2000000);

            Field field1 = WEB3IfoDepositCalc.class.getDeclaredField("ifoSummaryContractPerProductList");
            field1.setAccessible(true);
            field1.set(depositCalc, ifoSummaryContractPerProductList);
            
            Field field2 = WEB3IfoDepositCalc.class.getDeclaredField("ifoDepositCalcCondition");
            field2.setAccessible(true);
            field2.set(depositCalc, l_ifoDepositCalcCondition);

            Field field3 = WEB3IfoDepositCalc.class.getDeclaredField("ifoCustomerTransfer");
            field3.setAccessible(true);
            field3.set(depositCalc, l_ifoCustomerTransfer);
            
            Field field4 = WEB3IfoDepositCalc.class.getDeclaredField("ifoSummaryContractPerIndexList");
            field4.setAccessible(true);
            field4.set(depositCalc, l_ifoSummaryContractPerIndexLists);
            
            String l_strUnderlyingProductCode = "0005";
            int l_intReservedDate = 1;
            double l_dblReturn = depositCalc.calcPossibleBuyQty(l_strUnderlyingProductCode, l_intReservedDate);
            assertEquals(1148892, l_dblReturn, 0);
        }
        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage());
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
//  正確的売ポジション可能数量返回
//  0未満の場合、小数点以下切り上げとする
//  建玉/注文(*)が存在しない
  public void testcalcPossibleSellQty()
  {
      String STR_METHOD_NAME = "testcalcPossibleSellQty";
      log.entering(STR_METHOD_NAME);
      try
      {
          WEB3IfoSummaryContractPerIndex[] l_ifoSummaryContractPerIndexLists = null;
          WEB3IfoSummaryContractPerProduct[] ifoSummaryContractPerProductList =
              new WEB3IfoSummaryContractPerProduct[1];
          ifoSummaryContractPerProductList[0] = new WEB3IfoSummaryContractPerProduct();
          ifoSummaryContractPerProductList[0].setCurrentPrice(233);
          ifoSummaryContractPerProductList[0].setUnitSize(6.66);
          ifoSummaryContractPerProductList[0].setBuyQuantity(2);
          ifoSummaryContractPerProductList[0].setCurrentBizDateBuyOrderQuantity(4);
          ifoSummaryContractPerProductList[0].setIfoDerivativeType(IfoDerivativeTypeEnum.PUT_OPTIONS);
          ifoSummaryContractPerProductList[0].setSellQuantity(8);
          
          WEB3IfoDepositCalcCondition l_ifoDepositCalcCondition = new WEB3IfoDepositCalcCondition();
          Date l_bizDate1 = WEB3DateUtility.getDate("20040715","yyyyMMdd");
          Date l_bizDate2 = WEB3DateUtility.getDate("20040716","yyyyMMdd");
          Date l_bizDate3 = WEB3DateUtility.getDate("20040719","yyyyMMdd");
          Date l_bizDate4 = WEB3DateUtility.getDate("20040720","yyyyMMdd");
          Date[] l_bizDates = {l_bizDate1, l_bizDate2, l_bizDate3, l_bizDate4};
          l_ifoDepositCalcCondition.setBizDates(l_bizDates);
          //SPAN証拠金採用の時
          l_ifoDepositCalcCondition.setSimpleSPANCalcFlag(true);
          l_ifoDepositCalcCondition.setSpanTroubleFlag(true);
          //setSPAN係数レッド
          l_ifoDepositCalcCondition.setSPANFactor(5.3);
          //基準日
          l_ifoDepositCalcCondition.setIfoDepositCalcBaseDate(1);
          l_ifoDepositCalcCondition.setSPANFactorRed(2.2);
          //新規建余力可能 = true
          l_ifoDepositCalcCondition.setNewOpenTradingPowerAvailableFlag(true);
          //必要最低証拠金)
          l_ifoDepositCalcCondition.setMinIfoDeposit(10);
          //証拠金不足メール送信済フラグ
          l_ifoDepositCalcCondition.setIfoDepositMailFlag(true);
          l_ifoDepositCalcCondition.setLackChargeNonManagementFlag(true);
          l_ifoDepositCalcCondition.setCurrentBizIfoDepositLackCharge(100);
          
          WEB3IfoDepositCalcConditionPerIndex[] l_ifoDepositCalcConditionPerIndexList =
              new WEB3IfoDepositCalcConditionPerIndex[1];
          l_ifoDepositCalcConditionPerIndexList[0] = new WEB3IfoDepositCalcConditionPerIndex();
          l_ifoDepositCalcConditionPerIndexList[0].setUnderlyingProductCode("0005");
          l_ifoDepositCalcConditionPerIndexList[0].setIfoDepositPerUnit(2.3);
          l_ifoDepositCalcConditionPerIndexList[0].setIfoDepositPerUnitRed(11);
          l_ifoDepositCalcConditionPerIndexList[0].setIfoDepositPerUnitTemp(8);
          l_ifoDepositCalcCondition.setIfoDepositCalcPerIndexList(l_ifoDepositCalcConditionPerIndexList);
          
          WEB3IfoCustomerTransfer l_ifoCustomerTransfer = new WEB3IfoCustomerTransfer();
          double[] l_balances = {2, 5 ,6}; 
          l_ifoCustomerTransfer.setBalances(l_balances);
          l_ifoCustomerTransfer.setCurrentBizDateTransferAmount(100);
          l_ifoCustomerTransfer.addNextBizDateFuturesCloseProfitLoss(200);

          Field field1 = WEB3IfoDepositCalc.class.getDeclaredField("ifoSummaryContractPerProductList");
          field1.setAccessible(true);
          field1.set(depositCalc, ifoSummaryContractPerProductList);
          
          Field field2 = WEB3IfoDepositCalc.class.getDeclaredField("ifoDepositCalcCondition");
          field2.setAccessible(true);
          field2.set(depositCalc, l_ifoDepositCalcCondition);

          Field field3 = WEB3IfoDepositCalc.class.getDeclaredField("ifoCustomerTransfer");
          field3.setAccessible(true);
          field3.set(depositCalc, l_ifoCustomerTransfer);
          
          Field field4 = WEB3IfoDepositCalc.class.getDeclaredField("ifoSummaryContractPerIndexList");
          field4.setAccessible(true);
          field4.set(depositCalc, l_ifoSummaryContractPerIndexLists);
          
          String l_strUnderlyingProductCode = "0005";
          double l_dblReturn = depositCalc.calcPossibleSellQty(l_strUnderlyingProductCode);
          assertEquals(-1217, l_dblReturn, 0);
      }
      catch(Exception l_ex)
      {
          log.error(l_ex.getMessage());
          fail();
      }
      log.exiting(STR_METHOD_NAME);
  }
  
  //指定日が範囲外の場合(nが1以上2以下でない場合)、0を返却する。
  public void testcalcPossibleSellQty_C001()
  {
      String STR_METHOD_NAME = "testcalcPossibleBuyQty_C001";
      log.entering(STR_METHOD_NAME);
      try
      {
          WEB3IfoSummaryContractPerIndex[] l_ifoSummaryContractPerIndexLists = null;
          WEB3IfoSummaryContractPerProduct[] ifoSummaryContractPerProductList =
              new WEB3IfoSummaryContractPerProduct[1];
          ifoSummaryContractPerProductList[0] = new WEB3IfoSummaryContractPerProduct();
          ifoSummaryContractPerProductList[0].setCurrentPrice(233);
          ifoSummaryContractPerProductList[0].setUnitSize(6.66);
          ifoSummaryContractPerProductList[0].setBuyQuantity(2);
          ifoSummaryContractPerProductList[0].setCurrentBizDateBuyOrderQuantity(4);
          ifoSummaryContractPerProductList[0].setIfoDerivativeType(IfoDerivativeTypeEnum.PUT_OPTIONS);
          ifoSummaryContractPerProductList[0].setSellQuantity(8);
          
          WEB3IfoDepositCalcCondition l_ifoDepositCalcCondition = new WEB3IfoDepositCalcCondition();
          Date l_bizDate1 = WEB3DateUtility.getDate("20040715","yyyyMMdd");
          Date l_bizDate2 = WEB3DateUtility.getDate("20040716","yyyyMMdd");
          Date l_bizDate3 = WEB3DateUtility.getDate("20040719","yyyyMMdd");
          Date l_bizDate4 = WEB3DateUtility.getDate("20040720","yyyyMMdd");
          Date[] l_bizDates = {l_bizDate1, l_bizDate2, l_bizDate3, l_bizDate4};
          l_ifoDepositCalcCondition.setBizDates(l_bizDates);
          //SPAN証拠金採用の時
          l_ifoDepositCalcCondition.setSimpleSPANCalcFlag(true);
          l_ifoDepositCalcCondition.setSpanTroubleFlag(true);
          //setSPAN係数レッド
          l_ifoDepositCalcCondition.setSPANFactor(5.3);
          //基準日
          l_ifoDepositCalcCondition.setIfoDepositCalcBaseDate(1);
          l_ifoDepositCalcCondition.setSPANFactorRed(2.2);
          //新規建余力可能 = true
          l_ifoDepositCalcCondition.setNewOpenTradingPowerAvailableFlag(true);
          //必要最低証拠金)
          l_ifoDepositCalcCondition.setMinIfoDeposit(10);
          //証拠金不足メール送信済フラグ
          l_ifoDepositCalcCondition.setIfoDepositMailFlag(true);
          l_ifoDepositCalcCondition.setLackChargeNonManagementFlag(true);
          l_ifoDepositCalcCondition.setCurrentBizIfoDepositLackCharge(100);
          
          WEB3IfoDepositCalcConditionPerIndex[] l_ifoDepositCalcConditionPerIndexList =
              new WEB3IfoDepositCalcConditionPerIndex[1];
          l_ifoDepositCalcConditionPerIndexList[0] = new WEB3IfoDepositCalcConditionPerIndex();
          l_ifoDepositCalcConditionPerIndexList[0].setUnderlyingProductCode("0005");
          l_ifoDepositCalcConditionPerIndexList[0].setIfoDepositPerUnit(2.3);
          l_ifoDepositCalcConditionPerIndexList[0].setIfoDepositPerUnitRed(11);
          l_ifoDepositCalcConditionPerIndexList[0].setIfoDepositPerUnitTemp(8);
          l_ifoDepositCalcCondition.setIfoDepositCalcPerIndexList(l_ifoDepositCalcConditionPerIndexList);
          
          WEB3IfoCustomerTransfer l_ifoCustomerTransfer = new WEB3IfoCustomerTransfer();
          double[] l_balances = {2, 5 ,6}; 
          l_ifoCustomerTransfer.setBalances(l_balances);
          l_ifoCustomerTransfer.setCurrentBizDateTransferAmount(100);
          l_ifoCustomerTransfer.addNextBizDateFuturesCloseProfitLoss(200);

          Field field1 = WEB3IfoDepositCalc.class.getDeclaredField("ifoSummaryContractPerProductList");
          field1.setAccessible(true);
          field1.set(depositCalc, ifoSummaryContractPerProductList);
          
          Field field2 = WEB3IfoDepositCalc.class.getDeclaredField("ifoDepositCalcCondition");
          field2.setAccessible(true);
          field2.set(depositCalc, l_ifoDepositCalcCondition);

          Field field3 = WEB3IfoDepositCalc.class.getDeclaredField("ifoCustomerTransfer");
          field3.setAccessible(true);
          field3.set(depositCalc, l_ifoCustomerTransfer);
          
          Field field4 = WEB3IfoDepositCalc.class.getDeclaredField("ifoSummaryContractPerIndexList");
          field4.setAccessible(true);
          field4.set(depositCalc, l_ifoSummaryContractPerIndexLists);
          
          String l_strUnderlyingProductCode = "0005";
          int l_intReservedDate = 4;
          double l_dblReturn = depositCalc.calcPossibleSellQty(l_strUnderlyingProductCode, l_intReservedDate);
          assertEquals(0, l_dblReturn, 0);
      }
      catch(Exception l_ex)
      {
          log.error(l_ex.getMessage());
          fail();
      }
      log.exiting(STR_METHOD_NAME);
  }
  //証拠金余力額がマイナスの場合
  public void testcalcPossibleSellQty_C002()
  {
      String STR_METHOD_NAME = "testcalcPossibleBuyQty_C002";
      log.entering(STR_METHOD_NAME);
      try
      {
          WEB3IfoSummaryContractPerIndex[] l_ifoSummaryContractPerIndexLists =
              new WEB3IfoSummaryContractPerIndex[1];
          l_ifoSummaryContractPerIndexLists[0] = new WEB3IfoSummaryContractPerIndex();
          l_ifoSummaryContractPerIndexLists[0].setCurrentBizDateFuturesBuyOrderQuantity(10);
          l_ifoSummaryContractPerIndexLists[0].setIfoDepositPerUnit(1000);
          WEB3IfoSummaryContractPerProduct[] ifoSummaryContractPerProductList =
              new WEB3IfoSummaryContractPerProduct[1];
          ifoSummaryContractPerProductList[0] = new WEB3IfoSummaryContractPerProduct();
          ifoSummaryContractPerProductList[0].setCurrentPrice(233);
          ifoSummaryContractPerProductList[0].setUnitSize(6.66);
          ifoSummaryContractPerProductList[0].setBuyQuantity(2);
          ifoSummaryContractPerProductList[0].setCurrentBizDateBuyOrderQuantity(4);
          ifoSummaryContractPerProductList[0].setIfoDerivativeType(IfoDerivativeTypeEnum.PUT_OPTIONS);
          ifoSummaryContractPerProductList[0].setSellQuantity(8);
          
          WEB3IfoDepositCalcCondition l_ifoDepositCalcCondition = new WEB3IfoDepositCalcCondition();
          Date l_bizDate1 = WEB3DateUtility.getDate("20040715","yyyyMMdd");
          Date l_bizDate2 = WEB3DateUtility.getDate("20040716","yyyyMMdd");
          Date l_bizDate3 = WEB3DateUtility.getDate("20040719","yyyyMMdd");
          Date l_bizDate4 = WEB3DateUtility.getDate("20040720","yyyyMMdd");
          Date[] l_bizDates = {l_bizDate1, l_bizDate2, l_bizDate3, l_bizDate4};
          l_ifoDepositCalcCondition.setBizDates(l_bizDates);
          //SPAN証拠金採用の時
          l_ifoDepositCalcCondition.setSimpleSPANCalcFlag(true);
          l_ifoDepositCalcCondition.setSpanTroubleFlag(true);
          //setSPAN係数レッド
          l_ifoDepositCalcCondition.setSPANFactor(5.3);
          //基準日
          l_ifoDepositCalcCondition.setIfoDepositCalcBaseDate(1);
          l_ifoDepositCalcCondition.setSPANFactorRed(2.2);
          //新規建余力可能 = true
          l_ifoDepositCalcCondition.setNewOpenTradingPowerAvailableFlag(true);
          //必要最低証拠金)
          l_ifoDepositCalcCondition.setMinIfoDeposit(10);
          //証拠金不足メール送信済フラグ
          l_ifoDepositCalcCondition.setIfoDepositMailFlag(true);
          l_ifoDepositCalcCondition.setLackChargeNonManagementFlag(true);
          l_ifoDepositCalcCondition.setCurrentBizIfoDepositLackCharge(100);
          
          WEB3IfoDepositCalcConditionPerIndex[] l_ifoDepositCalcConditionPerIndexList =
              new WEB3IfoDepositCalcConditionPerIndex[1];
          l_ifoDepositCalcConditionPerIndexList[0] = new WEB3IfoDepositCalcConditionPerIndex();
          l_ifoDepositCalcConditionPerIndexList[0].setUnderlyingProductCode("0005");
          l_ifoDepositCalcConditionPerIndexList[0].setIfoDepositPerUnit(2.3);
          l_ifoDepositCalcConditionPerIndexList[0].setIfoDepositPerUnitRed(11);
          l_ifoDepositCalcConditionPerIndexList[0].setIfoDepositPerUnitTemp(8);
          l_ifoDepositCalcCondition.setIfoDepositCalcPerIndexList(l_ifoDepositCalcConditionPerIndexList);
          
          WEB3IfoCustomerTransfer l_ifoCustomerTransfer = new WEB3IfoCustomerTransfer();
          double[] l_balances = {2, 5 ,6}; 
          l_ifoCustomerTransfer.setBalances(l_balances);
          l_ifoCustomerTransfer.setCurrentBizDateTransferAmount(100);
          l_ifoCustomerTransfer.addNextBizDateFuturesCloseProfitLoss(200);

          Field field1 = WEB3IfoDepositCalc.class.getDeclaredField("ifoSummaryContractPerProductList");
          field1.setAccessible(true);
          field1.set(depositCalc, ifoSummaryContractPerProductList);
          
          Field field2 = WEB3IfoDepositCalc.class.getDeclaredField("ifoDepositCalcCondition");
          field2.setAccessible(true);
          field2.set(depositCalc, l_ifoDepositCalcCondition);

          Field field3 = WEB3IfoDepositCalc.class.getDeclaredField("ifoCustomerTransfer");
          field3.setAccessible(true);
          field3.set(depositCalc, l_ifoCustomerTransfer);
          
          Field field4 = WEB3IfoDepositCalc.class.getDeclaredField("ifoSummaryContractPerIndexList");
          field4.setAccessible(true);
          field4.set(depositCalc, l_ifoSummaryContractPerIndexLists);
          
          String l_strUnderlyingProductCode = "0005";
          int l_intReservedDate = 1;
          double l_dblReturn = depositCalc.calcPossibleSellQty(l_strUnderlyingProductCode, l_intReservedDate);
          assertEquals(-5565, l_dblReturn, 0);
      }
      catch(Exception l_ex)
      {
          log.error(l_ex.getMessage());
          fail();
      }
      log.exiting(STR_METHOD_NAME);
  }
//  証拠金余力額が正?の場合
//  建玉/注文(*)が存在
//  正確的売ポジション可能数量返回
  public void testcalcPossibleSellQty_C003()
  {
      String STR_METHOD_NAME = "testcalcPossibleBuyQty_C003";
      log.entering(STR_METHOD_NAME);
      try
      {
          WEB3IfoSummaryContractPerIndex[] l_ifoSummaryContractPerIndexLists =
              new WEB3IfoSummaryContractPerIndex[1];
          l_ifoSummaryContractPerIndexLists[0] = new WEB3IfoSummaryContractPerIndex();
          l_ifoSummaryContractPerIndexLists[0].setCurrentBizDateFuturesBuyOrderQuantity(10);
          l_ifoSummaryContractPerIndexLists[0].setIfoDepositPerUnit(1000);
          WEB3IfoSummaryContractPerProduct[] ifoSummaryContractPerProductList =
              new WEB3IfoSummaryContractPerProduct[1];
          ifoSummaryContractPerProductList[0] = new WEB3IfoSummaryContractPerProduct();
          ifoSummaryContractPerProductList[0].setCurrentPrice(233);
          ifoSummaryContractPerProductList[0].setUnitSize(6.66);
          ifoSummaryContractPerProductList[0].setBuyQuantity(2);
          ifoSummaryContractPerProductList[0].setCurrentBizDateBuyOrderQuantity(4);
          ifoSummaryContractPerProductList[0].setIfoDerivativeType(IfoDerivativeTypeEnum.PUT_OPTIONS);
          ifoSummaryContractPerProductList[0].setSellQuantity(8);
          
          WEB3IfoDepositCalcCondition l_ifoDepositCalcCondition = new WEB3IfoDepositCalcCondition();
          Date l_bizDate1 = WEB3DateUtility.getDate("20040715","yyyyMMdd");
          Date l_bizDate2 = WEB3DateUtility.getDate("20040716","yyyyMMdd");
          Date l_bizDate3 = WEB3DateUtility.getDate("20040719","yyyyMMdd");
          Date l_bizDate4 = WEB3DateUtility.getDate("20040720","yyyyMMdd");
          Date[] l_bizDates = {l_bizDate1, l_bizDate2, l_bizDate3, l_bizDate4};
          l_ifoDepositCalcCondition.setBizDates(l_bizDates);
          //SPAN証拠金採用の時
          l_ifoDepositCalcCondition.setSimpleSPANCalcFlag(true);
          l_ifoDepositCalcCondition.setSpanTroubleFlag(true);
          //setSPAN係数レッド
          l_ifoDepositCalcCondition.setSPANFactor(5.3);
          //基準日
          l_ifoDepositCalcCondition.setIfoDepositCalcBaseDate(1);
          l_ifoDepositCalcCondition.setSPANFactorRed(2.2);
          //新規建余力可能 = true
          l_ifoDepositCalcCondition.setNewOpenTradingPowerAvailableFlag(true);
          //必要最低証拠金)
          l_ifoDepositCalcCondition.setMinIfoDeposit(10);
          //証拠金不足メール送信済フラグ
          l_ifoDepositCalcCondition.setIfoDepositMailFlag(true);
          l_ifoDepositCalcCondition.setLackChargeNonManagementFlag(true);
          l_ifoDepositCalcCondition.setCurrentBizIfoDepositLackCharge(100);
          
          WEB3IfoDepositCalcConditionPerIndex[] l_ifoDepositCalcConditionPerIndexList =
              new WEB3IfoDepositCalcConditionPerIndex[1];
          l_ifoDepositCalcConditionPerIndexList[0] = new WEB3IfoDepositCalcConditionPerIndex();
          l_ifoDepositCalcConditionPerIndexList[0].setUnderlyingProductCode("0005");
          l_ifoDepositCalcConditionPerIndexList[0].setIfoDepositPerUnit(2.6);
          l_ifoDepositCalcConditionPerIndexList[0].setIfoDepositPerUnitRed(11);
          l_ifoDepositCalcConditionPerIndexList[0].setIfoDepositPerUnitTemp(8);
          l_ifoDepositCalcCondition.setIfoDepositCalcPerIndexList(l_ifoDepositCalcConditionPerIndexList);
          
          WEB3IfoCustomerTransfer l_ifoCustomerTransfer = new WEB3IfoCustomerTransfer();
          double[] l_balances = {222222, 555555 ,666666}; 
          l_ifoCustomerTransfer.setBalances(l_balances);
          l_ifoCustomerTransfer.setCurrentBizDateTransferAmount(100000);
          l_ifoCustomerTransfer.addNextBizDateFuturesCloseProfitLoss(2000000);

          Field field1 = WEB3IfoDepositCalc.class.getDeclaredField("ifoSummaryContractPerProductList");
          field1.setAccessible(true);
          field1.set(depositCalc, ifoSummaryContractPerProductList);
          
          Field field2 = WEB3IfoDepositCalc.class.getDeclaredField("ifoDepositCalcCondition");
          field2.setAccessible(true);
          field2.set(depositCalc, l_ifoDepositCalcCondition);

          Field field3 = WEB3IfoDepositCalc.class.getDeclaredField("ifoCustomerTransfer");
          field3.setAccessible(true);
          field3.set(depositCalc, l_ifoCustomerTransfer);
          
          Field field4 = WEB3IfoDepositCalc.class.getDeclaredField("ifoSummaryContractPerIndexList");
          field4.setAccessible(true);
          field4.set(depositCalc, l_ifoSummaryContractPerIndexLists);
          
          String l_strUnderlyingProductCode = "0005";
          int l_intReservedDate = 1;
          double l_dblReturn = depositCalc.calcPossibleSellQty(l_strUnderlyingProductCode, l_intReservedDate);
          assertEquals(1020173, l_dblReturn, 0);
      }
      catch(Exception l_ex)
      {
          log.error(l_ex.getMessage());
          fail();
      }
      log.exiting(STR_METHOD_NAME);
  }
  
//  is証拠金不足額非管理　@==　@trueの場合
//  0を返却する。
  public void testCalcNonPayAmount_C001()
  {
      String STR_METHOD_NAME = "testCalcNonPayAmount_C001";
      log.entering(STR_METHOD_NAME);
      try
      {
          WEB3IfoDepositCalcCondition l_ifoDepositCalcCondition = new WEB3IfoDepositCalcCondition();
          Date l_bizDate1 = WEB3DateUtility.getDate("20040715","yyyyMMdd");
          Date l_bizDate2 = WEB3DateUtility.getDate("20040716","yyyyMMdd");
          Date l_bizDate3 = WEB3DateUtility.getDate("20040719","yyyyMMdd");
          Date l_bizDate4 = WEB3DateUtility.getDate("20040720","yyyyMMdd");
          Date[] l_bizDates = {l_bizDate1, l_bizDate2, l_bizDate3, l_bizDate4};
          l_ifoDepositCalcCondition.setBizDates(l_bizDates);
          //証拠金不足メール送信済フラグ
          l_ifoDepositCalcCondition.setIfoDepositMailFlag(true);
          l_ifoDepositCalcCondition.setLackChargeNonManagementFlag(true);
          l_ifoDepositCalcCondition.setCurrentBizIfoDepositLackCharge(100);
          
          WEB3IfoCustomerTransfer l_ifoCustomerTransfer = new WEB3IfoCustomerTransfer();
          double[] l_balances = {222222, 555555 ,666666}; 
          l_ifoCustomerTransfer.setBalances(l_balances);
          l_ifoCustomerTransfer.setCurrentBizDateTransferAmount(100000);
          l_ifoCustomerTransfer.addNextBizDateFuturesCloseProfitLoss(2000000);
          
          Field field2 = WEB3IfoDepositCalc.class.getDeclaredField("ifoDepositCalcCondition");
          field2.setAccessible(true);
          field2.set(depositCalc, l_ifoDepositCalcCondition);

          Field field3 = WEB3IfoDepositCalc.class.getDeclaredField("ifoCustomerTransfer");
          field3.setAccessible(true);
          field3.set(depositCalc, l_ifoCustomerTransfer);
          
          double l_dblReturn = depositCalc.calcNonPayAmount();
          assertEquals(0, l_dblReturn, 0);
      }
      catch(Exception l_ex)
      {
          log.error(l_ex.getMessage());
          fail();
      }
      log.exiting(STR_METHOD_NAME);
  }

//  is証拠金不足額非管理　@==　@false
//  is証拠金不足メール送信済 == true の場合
//  正しい「未入金額」を返却する。
  public void testCalcNonPayAmount_C002()
  {
      String STR_METHOD_NAME = "testCalcNonPayAmount_C002";
      log.entering(STR_METHOD_NAME);
      try
      {
          WEB3IfoDepositCalcCondition l_ifoDepositCalcCondition = new WEB3IfoDepositCalcCondition();
          Date l_bizDate1 = WEB3DateUtility.getDate("20040715","yyyyMMdd");
          Date l_bizDate2 = WEB3DateUtility.getDate("20040716","yyyyMMdd");
          Date l_bizDate3 = WEB3DateUtility.getDate("20040719","yyyyMMdd");
          Date l_bizDate4 = WEB3DateUtility.getDate("20040720","yyyyMMdd");
          Date[] l_bizDates = {l_bizDate1, l_bizDate2, l_bizDate3, l_bizDate4};
          l_ifoDepositCalcCondition.setBizDates(l_bizDates);
          //証拠金不足メール送信済フラグ
          l_ifoDepositCalcCondition.setIfoDepositMailFlag(true);
          l_ifoDepositCalcCondition.setLackChargeNonManagementFlag(false);
          l_ifoDepositCalcCondition.setCurrentBizIfoDepositLackCharge(100);
          
          
          WEB3IfoCustomerTransfer l_ifoCustomerTransfer = new WEB3IfoCustomerTransfer();
          double[] l_balances = {222222, 555555 ,666666}; 
          l_ifoCustomerTransfer.setBalances(l_balances);
          l_ifoCustomerTransfer.setCurrentBizDateTransferAmount(100000);
          l_ifoCustomerTransfer.addNextBizDateFuturesCloseProfitLoss(2000000);
          
          Field field2 = WEB3IfoDepositCalc.class.getDeclaredField("ifoDepositCalcCondition");
          field2.setAccessible(true);
          field2.set(depositCalc, l_ifoDepositCalcCondition);

          Field field3 = WEB3IfoDepositCalc.class.getDeclaredField("ifoCustomerTransfer");
          field3.setAccessible(true);
          field3.set(depositCalc, l_ifoCustomerTransfer);
          
          double l_dblReturn = depositCalc.calcNonPayAmount();
          assertEquals(100, l_dblReturn, 0);
      }
      catch(Exception l_ex)
      {
          log.error(l_ex.getMessage());
          fail();
      }
      log.exiting(STR_METHOD_NAME);
  }
  
  //is証拠金不足額非管理　@==　@false                                                                 
  //is証拠金不足メール送信済 == true の場合                                                         
  //正しい「未入金額」を返却する。                                                                  
  public void testCalcNonPayAmount_C003()                                                           
  {                                                                                                 
      String STR_METHOD_NAME = "testCalcNonPayAmount_C003";                                         
      log.entering(STR_METHOD_NAME);                                                                
      try                                                                                           
      {                                                                                             
          WEB3IfoDepositCalcCondition l_ifoDepositCalcCondition = new WEB3IfoDepositCalcCondition();
          Date l_bizDate1 = WEB3DateUtility.getDate("20040715","yyyyMMdd");                         
          Date l_bizDate2 = WEB3DateUtility.getDate("20040716","yyyyMMdd");                         
          Date l_bizDate3 = WEB3DateUtility.getDate("20040719","yyyyMMdd");                         
          Date l_bizDate4 = WEB3DateUtility.getDate("20040720","yyyyMMdd");                         
          Date[] l_bizDates = {l_bizDate1, l_bizDate2, l_bizDate3, l_bizDate4};                     
          l_ifoDepositCalcCondition.setBizDates(l_bizDates);                                        
          //証拠金不足メール送信済フラグ                                                            
          l_ifoDepositCalcCondition.setIfoDepositMailFlag(false);                                   
          l_ifoDepositCalcCondition.setLackChargeNonManagementFlag(false);                          
          l_ifoDepositCalcCondition.setQuickReportReceivedFlag(true);                               
          l_ifoDepositCalcCondition.setCurrentBizIfoDepositLackCharge(100);                         
                                                                                                    
                                                                                                    
          WEB3IfoCustomerTransfer l_ifoCustomerTransfer = new WEB3IfoCustomerTransfer();            
          double[] l_balances = {-2222, -5555 ,6666};                                               
          l_ifoCustomerTransfer.setBalances(l_balances);                                            
          l_ifoCustomerTransfer.setCurrentBizDateTransferAmount(1000);                              
          l_ifoCustomerTransfer.addNextBizDateFuturesCloseProfitLoss(-2000);                        
          l_ifoCustomerTransfer.addCashinAmount(                                                    
              OrderTypeEnum.FROM_DEPOSIT_AMOUNT_MARGIN,                                             
              50,                                                                                   
              WEB3DateUtility.getDate("20040716","yyyyMMdd"),                                       
              WEB3DateUtility.getDate("20040719","yyyyMMdd"));                                      
                                                                                                    
          Field field2 = WEB3IfoDepositCalc.class.getDeclaredField("ifoDepositCalcCondition");      
          field2.setAccessible(true);                                                               
          field2.set(depositCalc, l_ifoDepositCalcCondition);                                       
                                                                                                    
          Field field3 = WEB3IfoDepositCalc.class.getDeclaredField("ifoCustomerTransfer");          
          field3.setAccessible(true);                                                               
          field3.set(depositCalc, l_ifoCustomerTransfer);                                           
                                                                                                    
          double l_dblReturn = depositCalc.calcNonPayAmount();                                      
          assertEquals(6505, l_dblReturn, 0);                                                       
      }                                                                                             
      catch(Exception l_ex)                                                                         
      {                                                                                             
          log.error(l_ex.getMessage());                                                             
          fail();                                                                                   
      }                                                                                             
      log.exiting(STR_METHOD_NAME);                                                                 
  }   
  
//  当日の証拠金不足仮計算前時間帯の場合
//  正しい「未入金額」を返却する。                                                                 
  public void testCalcNonPayAmount_C004()                                                           
  {                                                                                                 
      String STR_METHOD_NAME = "testCalcNonPayAmount_C004";                                         
      log.entering(STR_METHOD_NAME);                                                                
      try                                                                                           
      {                                                                                             
          WEB3IfoDepositCalcCondition l_ifoDepositCalcCondition = new WEB3IfoDepositCalcCondition();
          Date l_bizDate1 = WEB3DateUtility.getDate("20040715","yyyyMMdd");                         
          Date l_bizDate2 = WEB3DateUtility.getDate("20040716","yyyyMMdd");                         
          Date l_bizDate3 = WEB3DateUtility.getDate("20040719","yyyyMMdd");                         
          Date l_bizDate4 = WEB3DateUtility.getDate("20040720","yyyyMMdd");                         
          Date[] l_bizDates = {l_bizDate1, l_bizDate2, l_bizDate3, l_bizDate4};                     
          l_ifoDepositCalcCondition.setBizDates(l_bizDates);                                        
          //証拠金不足メール送信済フラグ                                                            
          l_ifoDepositCalcCondition.setIfoDepositMailFlag(false);                                   
          l_ifoDepositCalcCondition.setLackChargeNonManagementFlag(false);                          
          l_ifoDepositCalcCondition.setQuickReportReceivedFlag(false);                               
          l_ifoDepositCalcCondition.setCurrentBizIfoDepositLackCharge(100);
          l_ifoDepositCalcCondition.setPreBizDateInfoDepositLackCharge(1500);
                                                                                                    
                                                                                                    
          WEB3IfoCustomerTransfer l_ifoCustomerTransfer = new WEB3IfoCustomerTransfer();            
          double[] l_balances = {-2222, -5555 ,6666};                                               
          l_ifoCustomerTransfer.setBalances(l_balances);                                            
          l_ifoCustomerTransfer.setCurrentBizDateTransferAmount(1000);                              
          l_ifoCustomerTransfer.addNextBizDateFuturesCloseProfitLoss(-2000);                        
          l_ifoCustomerTransfer.addCashinAmount(                                                    
              OrderTypeEnum.FROM_DEPOSIT_AMOUNT_MARGIN,                                             
              50,                                                                                   
              WEB3DateUtility.getDate("20040716","yyyyMMdd"),                                       
              WEB3DateUtility.getDate("20040716","yyyyMMdd"));                                      
                                                                                                    
          Field field1 = WEB3IfoDepositCalc.class.getDeclaredField("ifoDepositCalcCondition");      
          field1.setAccessible(true);                                                               
          field1.set(depositCalc, l_ifoDepositCalcCondition);                                       
                                                                                                    
          Field field2 = WEB3IfoDepositCalc.class.getDeclaredField("ifoCustomerTransfer");          
          field2.setAccessible(true);                                                               
          field2.set(depositCalc, l_ifoCustomerTransfer);                                           
                                                                                                    
          double l_dblReturn = depositCalc.calcNonPayAmount();                                      
          assertEquals(1450, l_dblReturn, 0);                                                       
      }                                                                                             
      catch(Exception l_ex)                                                                         
      {                                                                                             
          log.error(l_ex.getMessage());                                                             
          fail();                                                                                   
      }                                                                                             
      log.exiting(STR_METHOD_NAME);                                                                 
  }
  
//  当日証拠金不足額 > 0 の場合
//  翌日請求額 =  当日証拠金不足額
  public void testCalcNextBizDateDemandAmount_C0001()
  {
      String STR_METHOD_NAME = "testCalcNextBizDateDemandAmount_C0001";                                         
      log.entering(STR_METHOD_NAME);                                                                
      try                                                                                           
      {                                                                                             
          WEB3IfoDepositCalcCondition l_ifoDepositCalcCondition = new WEB3IfoDepositCalcCondition();
          Date l_bizDate1 = WEB3DateUtility.getDate("20040715","yyyyMMdd");                         
          Date l_bizDate2 = WEB3DateUtility.getDate("20040716","yyyyMMdd");                         
          Date l_bizDate3 = WEB3DateUtility.getDate("20040719","yyyyMMdd");                         
          Date l_bizDate4 = WEB3DateUtility.getDate("20040720","yyyyMMdd");                         
          Date[] l_bizDates = {l_bizDate1, l_bizDate2, l_bizDate3, l_bizDate4};                     
          l_ifoDepositCalcCondition.setBizDates(l_bizDates);                                        
          //証拠金不足メール送信済フラグ                                                            
          l_ifoDepositCalcCondition.setIfoDepositMailFlag(false);                                   
          l_ifoDepositCalcCondition.setLackChargeNonManagementFlag(false);                          
          l_ifoDepositCalcCondition.setQuickReportReceivedFlag(false);                               
          l_ifoDepositCalcCondition.setCurrentBizIfoDepositLackCharge(500);
          l_ifoDepositCalcCondition.setPreBizDateInfoDepositLackCharge(1500);
                                                                                                    
                                                                                                    
          WEB3IfoCustomerTransfer l_ifoCustomerTransfer = new WEB3IfoCustomerTransfer();            
          double[] l_balances = {-2222, -5555 ,6666};                                               
          l_ifoCustomerTransfer.setBalances(l_balances);                                            
          l_ifoCustomerTransfer.setCurrentBizDateTransferAmount(1000);                              
          l_ifoCustomerTransfer.addNextBizDateFuturesCloseProfitLoss(-2000);                        
          l_ifoCustomerTransfer.addCashinAmount(                                                    
              OrderTypeEnum.FROM_DEPOSIT_AMOUNT_MARGIN,                                             
              50,                                                                                   
              WEB3DateUtility.getDate("20040716","yyyyMMdd"),                                       
              WEB3DateUtility.getDate("20040716","yyyyMMdd"));                                      
                                                                                                    
          Field field1 = WEB3IfoDepositCalc.class.getDeclaredField("ifoDepositCalcCondition");      
          field1.setAccessible(true);                                                               
          field1.set(depositCalc, l_ifoDepositCalcCondition);                                       
                                                                                                    
          Field field2 = WEB3IfoDepositCalc.class.getDeclaredField("ifoCustomerTransfer");          
          field2.setAccessible(true);                                                               
          field2.set(depositCalc, l_ifoCustomerTransfer);                                           
                                                                                                    
          double l_dblReturn = depositCalc.calcNextBizDateDemandAmount();                                      
          assertEquals(500, l_dblReturn, 0);                                                       
      }                                                                                             
      catch(Exception l_ex)                                                                         
      {                                                                                             
          log.error(l_ex.getMessage());                                                             
          fail();                                                                                   
      }                                                                                             
      log.exiting(STR_METHOD_NAME); 
  }
  
//  is証拠金不足メール送信済　@==　@true　@かつ
//  当日証拠金不足額 == 0の場合
//  翌日請求額 = 0
  public void testCalcNextBizDateDemandAmount_C0002()
  {
      String STR_METHOD_NAME = "testCalcNextBizDateDemandAmount_C0002";                                         
      log.entering(STR_METHOD_NAME);                                                                
      try                                                                                           
      {                                                                                             
          WEB3IfoDepositCalcCondition l_ifoDepositCalcCondition = new WEB3IfoDepositCalcCondition();
          Date l_bizDate1 = WEB3DateUtility.getDate("20040715","yyyyMMdd");                         
          Date l_bizDate2 = WEB3DateUtility.getDate("20040716","yyyyMMdd");                         
          Date l_bizDate3 = WEB3DateUtility.getDate("20040719","yyyyMMdd");                         
          Date l_bizDate4 = WEB3DateUtility.getDate("20040720","yyyyMMdd");                         
          Date[] l_bizDates = {l_bizDate1, l_bizDate2, l_bizDate3, l_bizDate4};                     
          l_ifoDepositCalcCondition.setBizDates(l_bizDates);                                        
          //証拠金不足メール送信済フラグ                                                            
          l_ifoDepositCalcCondition.setIfoDepositMailFlag(true);                                   
          l_ifoDepositCalcCondition.setLackChargeNonManagementFlag(false);                          
          l_ifoDepositCalcCondition.setQuickReportReceivedFlag(false);                               
          l_ifoDepositCalcCondition.setCurrentBizIfoDepositLackCharge(0);
          l_ifoDepositCalcCondition.setPreBizDateInfoDepositLackCharge(1500);
                                                                                                    
                                                                                                    
          WEB3IfoCustomerTransfer l_ifoCustomerTransfer = new WEB3IfoCustomerTransfer();            
          double[] l_balances = {-2222, -5555 ,6666};                                               
          l_ifoCustomerTransfer.setBalances(l_balances);                                            
          l_ifoCustomerTransfer.setCurrentBizDateTransferAmount(1000);                              
          l_ifoCustomerTransfer.addNextBizDateFuturesCloseProfitLoss(-2000);                        
          l_ifoCustomerTransfer.addCashinAmount(                                                    
              OrderTypeEnum.FROM_DEPOSIT_AMOUNT_MARGIN,                                             
              50,                                                                                   
              WEB3DateUtility.getDate("20040716","yyyyMMdd"),                                       
              WEB3DateUtility.getDate("20040716","yyyyMMdd"));                                      
                                                                                                    
          Field field1 = WEB3IfoDepositCalc.class.getDeclaredField("ifoDepositCalcCondition");      
          field1.setAccessible(true);                                                               
          field1.set(depositCalc, l_ifoDepositCalcCondition);                                       
                                                                                                    
          Field field2 = WEB3IfoDepositCalc.class.getDeclaredField("ifoCustomerTransfer");          
          field2.setAccessible(true);                                                               
          field2.set(depositCalc, l_ifoCustomerTransfer);                                           
                                                                                                    
          double l_dblReturn = depositCalc.calcNextBizDateDemandAmount();                                      
          assertEquals(0, l_dblReturn, 0);                                                       
      }                                                                                             
      catch(Exception l_ex)                                                                         
      {                                                                                             
          log.error(l_ex.getMessage());                                                             
          fail();                                                                                   
      }                                                                                             
      log.exiting(STR_METHOD_NAME); 
  }

//  証拠金不足仮計算時間帯の場合
//  翌日請求額 =  証拠金不足額＜仮計算＞(T+1)　@
  public void testCalcNextBizDateDemandAmount_C0003()
  {
      String STR_METHOD_NAME = "testCalcNextBizDateDemandAmount_C0003";                                         
      log.entering(STR_METHOD_NAME);                                                                
      try                                                                                           
      {                                                                                             
          WEB3IfoDepositCalcCondition l_ifoDepositCalcCondition = new WEB3IfoDepositCalcCondition();
          Date l_bizDate1 = WEB3DateUtility.getDate("20040715","yyyyMMdd");                         
          Date l_bizDate2 = WEB3DateUtility.getDate("20040716","yyyyMMdd");                         
          Date l_bizDate3 = WEB3DateUtility.getDate("20040719","yyyyMMdd");                         
          Date l_bizDate4 = WEB3DateUtility.getDate("20040720","yyyyMMdd");                         
          Date[] l_bizDates = {l_bizDate1, l_bizDate2, l_bizDate3, l_bizDate4};                     
          l_ifoDepositCalcCondition.setBizDates(l_bizDates);                                        
          //証拠金不足メール送信済フラグ                                                            
          l_ifoDepositCalcCondition.setIfoDepositMailFlag(false);                                   
          l_ifoDepositCalcCondition.setLackChargeNonManagementFlag(false);                          
          l_ifoDepositCalcCondition.setQuickReportReceivedFlag(true);                               
          l_ifoDepositCalcCondition.setCurrentBizIfoDepositLackCharge(0);
          l_ifoDepositCalcCondition.setPreBizDateInfoDepositLackCharge(1500);
                                                                                                    
                                                                                                    
          WEB3IfoCustomerTransfer l_ifoCustomerTransfer = new WEB3IfoCustomerTransfer();            
          double[] l_balances = {-2222, -5555 ,6666};                                               
          l_ifoCustomerTransfer.setBalances(l_balances);                                            
          l_ifoCustomerTransfer.setCurrentBizDateTransferAmount(1000);                              
          l_ifoCustomerTransfer.addNextBizDateFuturesCloseProfitLoss(-2000);                        
          l_ifoCustomerTransfer.addCashinAmount(                                                    
              OrderTypeEnum.FROM_DEPOSIT_AMOUNT_MARGIN,                                             
              50,                                                                                   
              WEB3DateUtility.getDate("20040716","yyyyMMdd"),                                       
              WEB3DateUtility.getDate("20040716","yyyyMMdd"));                                      
                                                                                                    
          Field field1 = WEB3IfoDepositCalc.class.getDeclaredField("ifoDepositCalcCondition");      
          field1.setAccessible(true);                                                               
          field1.set(depositCalc, l_ifoDepositCalcCondition);                                       
                                                                                                    
          Field field2 = WEB3IfoDepositCalc.class.getDeclaredField("ifoCustomerTransfer");          
          field2.setAccessible(true);                                                               
          field2.set(depositCalc, l_ifoCustomerTransfer);                                           
                                                                                                    
          double l_dblReturn = depositCalc.calcNextBizDateDemandAmount();                                      
          assertEquals(6555, l_dblReturn, 0);                                                       
      }                                                                                             
      catch(Exception l_ex)                                                                         
      {                                                                                             
          log.error(l_ex.getMessage());                                                             
          fail();                                                                                   
      }                                                                                             
      log.exiting(STR_METHOD_NAME); 
  }
  
  //当日に計算値による証拠金不足が発生している場合
  public void testCalcNextBizDateDemandAmount_C0004()
  {
      String STR_METHOD_NAME = "testCalcNextBizDateDemandAmount_C0004";                                         
      log.entering(STR_METHOD_NAME);                                                                
      try                                                                                           
      {                                                                                             
          WEB3IfoDepositCalcCondition l_ifoDepositCalcCondition = new WEB3IfoDepositCalcCondition();
          Date l_bizDate1 = WEB3DateUtility.getDate("20040715","yyyyMMdd");                         
          Date l_bizDate2 = WEB3DateUtility.getDate("20040716","yyyyMMdd");                         
          Date l_bizDate3 = WEB3DateUtility.getDate("20040719","yyyyMMdd");                         
          Date l_bizDate4 = WEB3DateUtility.getDate("20040720","yyyyMMdd");                         
          Date[] l_bizDates = {l_bizDate1, l_bizDate2, l_bizDate3, l_bizDate4};                     
          l_ifoDepositCalcCondition.setBizDates(l_bizDates);                                        
          //証拠金不足メール送信済フラグ                                                            
          l_ifoDepositCalcCondition.setIfoDepositMailFlag(false);                                   
          l_ifoDepositCalcCondition.setLackChargeNonManagementFlag(false);                          
          l_ifoDepositCalcCondition.setQuickReportReceivedFlag(false);                               
          l_ifoDepositCalcCondition.setCurrentBizIfoDepositLackCharge(0);
          l_ifoDepositCalcCondition.setPreBizDateInfoDepositLackCharge(1500);
                                                                                                    
                                                                                                    
          WEB3IfoCustomerTransfer l_ifoCustomerTransfer = new WEB3IfoCustomerTransfer();            
          double[] l_balances = {-2222, -5555 ,6666};                                               
          l_ifoCustomerTransfer.setBalances(l_balances);                                            
          l_ifoCustomerTransfer.setCurrentBizDateTransferAmount(1000);                              
          l_ifoCustomerTransfer.addNextBizDateFuturesCloseProfitLoss(-2000);                        
          l_ifoCustomerTransfer.addCashinAmount(                                                    
              OrderTypeEnum.FROM_DEPOSIT_AMOUNT_MARGIN,                                             
              50,                                                                                   
              WEB3DateUtility.getDate("20040716","yyyyMMdd"),                                       
              WEB3DateUtility.getDate("20040716","yyyyMMdd"));                                      
                                                                                                    
          Field field1 = WEB3IfoDepositCalc.class.getDeclaredField("ifoDepositCalcCondition");      
          field1.setAccessible(true);                                                               
          field1.set(depositCalc, l_ifoDepositCalcCondition);                                       
                                                                                                    
          Field field2 = WEB3IfoDepositCalc.class.getDeclaredField("ifoCustomerTransfer");          
          field2.setAccessible(true);                                                               
          field2.set(depositCalc, l_ifoCustomerTransfer);                                           
                                                                                                    
          double l_dblReturn = depositCalc.calcNextBizDateDemandAmount();                                      
          assertEquals(6555, l_dblReturn, 0);                                                       
      }                                                                                             
      catch(Exception l_ex)                                                                         
      {                                                                                             
          log.error(l_ex.getMessage());                                                             
          fail();                                                                                   
      }                                                                                             
      log.exiting(STR_METHOD_NAME); 
  }
  
  //当日に計算値による証拠金不足が発生していない場合
  public void testCalcNextBizDateDemandAmount_C0005()
  {
      String STR_METHOD_NAME = "testCalcNextBizDateDemandAmount_C0005";                                         
      log.entering(STR_METHOD_NAME);                                                                
      try                                                                                           
      {                                                                                             
          WEB3IfoDepositCalcCondition l_ifoDepositCalcCondition = new WEB3IfoDepositCalcCondition();
          Date l_bizDate1 = WEB3DateUtility.getDate("20040715","yyyyMMdd");                         
          Date l_bizDate2 = WEB3DateUtility.getDate("20040716","yyyyMMdd");                         
          Date l_bizDate3 = WEB3DateUtility.getDate("20040719","yyyyMMdd");                         
          Date l_bizDate4 = WEB3DateUtility.getDate("20040720","yyyyMMdd");                         
          Date[] l_bizDates = {l_bizDate1, l_bizDate2, l_bizDate3, l_bizDate4};                     
          l_ifoDepositCalcCondition.setBizDates(l_bizDates);                                        
          //証拠金不足メール送信済フラグ                                                            
          l_ifoDepositCalcCondition.setIfoDepositMailFlag(false);                                   
          l_ifoDepositCalcCondition.setLackChargeNonManagementFlag(false);                          
          l_ifoDepositCalcCondition.setQuickReportReceivedFlag(false);                               
          l_ifoDepositCalcCondition.setCurrentBizIfoDepositLackCharge(0);
          l_ifoDepositCalcCondition.setPreBizDateInfoDepositLackCharge(1500);
                                                                                                    
                                                                                                    
          WEB3IfoCustomerTransfer l_ifoCustomerTransfer = new WEB3IfoCustomerTransfer();            
          double[] l_balances = {2222, 5555 ,6666};                                               
          l_ifoCustomerTransfer.setBalances(l_balances);                                            
          l_ifoCustomerTransfer.setCurrentBizDateTransferAmount(1000);                              
          l_ifoCustomerTransfer.addNextBizDateFuturesCloseProfitLoss(-2000);                        
          l_ifoCustomerTransfer.addCashinAmount(                                                    
              OrderTypeEnum.FROM_DEPOSIT_AMOUNT_MARGIN,                                             
              50,                                                                                   
              WEB3DateUtility.getDate("20040716","yyyyMMdd"),                                       
              WEB3DateUtility.getDate("20040716","yyyyMMdd"));                                      
                                                                                                    
          Field field1 = WEB3IfoDepositCalc.class.getDeclaredField("ifoDepositCalcCondition");      
          field1.setAccessible(true);                                                               
          field1.set(depositCalc, l_ifoDepositCalcCondition);                                       
                                                                                                    
          Field field2 = WEB3IfoDepositCalc.class.getDeclaredField("ifoCustomerTransfer");          
          field2.setAccessible(true);                                                               
          field2.set(depositCalc, l_ifoCustomerTransfer);                                           
                                                                                                    
          double l_dblReturn = depositCalc.calcNextBizDateDemandAmount();                                      
          assertEquals(0, l_dblReturn, 0);                                                       
      }                                                                                             
      catch(Exception l_ex)                                                                         
      {                                                                                             
          log.error(l_ex.getMessage());                                                             
          fail();                                                                                   
      }                                                                                             
      log.exiting(STR_METHOD_NAME); 
  }
  //返回正確的先物評価損益
  public void testCalcFuturesAppraisalProfitLoss()
  {
      String STR_METHOD_NAME = "testCalcFuturesAppraisalProfitLoss";
      log.entering(STR_METHOD_NAME);
      try
      {
          WEB3IfoSummaryContractPerProductContractPrice[] l_ifoSummaryontractPerProductContractPriceList =
              new WEB3IfoSummaryContractPerProductContractPrice[1];
          l_ifoSummaryontractPerProductContractPriceList[0] =
              new WEB3IfoSummaryContractPerProductContractPrice();
          l_ifoSummaryontractPerProductContractPriceList[0].setBuyQuantity(100);
          l_ifoSummaryontractPerProductContractPriceList[0].setContractPrice(20);
          l_ifoSummaryontractPerProductContractPriceList[0].setCurrentPrice(25);
          l_ifoSummaryontractPerProductContractPriceList[0].setIfoDerivativeType(IfoDerivativeTypeEnum.FUTURES);
          l_ifoSummaryontractPerProductContractPriceList[0].setUnitSize(100);
          l_ifoSummaryontractPerProductContractPriceList[0].setSellQuantity(200);
          
          Field field = WEB3IfoDepositCalc.class.getDeclaredField("ifoSummaryContractPerProductContractPriceList");
          field.setAccessible(true);
          field.set(depositCalc, l_ifoSummaryontractPerProductContractPriceList);
          double l_dblReturn = depositCalc.calcFuturesAppraisalProfitLoss(); 
          assertEquals(-50000, l_dblReturn, 0);
      }
      catch(Exception l_ex)
      {
          log.error(l_ex.getMessage());
          fail();
      }

      log.exiting(STR_METHOD_NAME);
  }
  
  //建玉が存在しない時,先物買建評価損益に0を代入する
  public void testCalcFuturesBuyAppraisalProfitLoss_C001()
  {
      String STR_METHOD_NAME = "testCalcFuturesBuyAppraisalProfitLoss_C001";
      log.entering(STR_METHOD_NAME);
      try
      {
          WEB3IfoSummaryContractPerProductContractPrice[] l_ifoSummaryontractPerProductContractPriceList = null;
          Field field = WEB3IfoDepositCalc.class.getDeclaredField("ifoSummaryContractPerProductContractPriceList");
          field.setAccessible(true);
          field.set(depositCalc, l_ifoSummaryontractPerProductContractPriceList);
          double l_dblReturn = depositCalc.calcFuturesBuyAppraisalProfitLoss(); 
          assertEquals(0, l_dblReturn, 0);
      }
      catch(Exception l_ex)
      {
          log.error(l_ex.getMessage());
          fail();
      }

      log.exiting(STR_METHOD_NAME);
      
  }
  
  //建玉が存在する時,正しい先物買建評価損益を返却する
  public void testCalcFuturesBuyAppraisalProfitLoss_C002()
  {
      String STR_METHOD_NAME = "testCalcFuturesBuyAppraisalProfitLoss_C002";
      log.entering(STR_METHOD_NAME);
      try
      {
          WEB3IfoSummaryContractPerProductContractPrice[] l_ifoSummaryontractPerProductContractPriceList =
              new WEB3IfoSummaryContractPerProductContractPrice[2];
          l_ifoSummaryontractPerProductContractPriceList[0] =
              new WEB3IfoSummaryContractPerProductContractPrice();
          l_ifoSummaryontractPerProductContractPriceList[0].setBuyQuantity(100);
          l_ifoSummaryontractPerProductContractPriceList[0].setContractPrice(20);
          l_ifoSummaryontractPerProductContractPriceList[0].setCurrentPrice(25);
          l_ifoSummaryontractPerProductContractPriceList[0].setIfoDerivativeType(IfoDerivativeTypeEnum.FUTURES);
          l_ifoSummaryontractPerProductContractPriceList[0].setUnitSize(100);
          l_ifoSummaryontractPerProductContractPriceList[0].setSellQuantity(50);
          
          l_ifoSummaryontractPerProductContractPriceList[1] =
              new WEB3IfoSummaryContractPerProductContractPrice();
          l_ifoSummaryontractPerProductContractPriceList[1].setBuyQuantity(200);
          l_ifoSummaryontractPerProductContractPriceList[1].setContractPrice(20);
          l_ifoSummaryontractPerProductContractPriceList[1].setCurrentPrice(30);
          l_ifoSummaryontractPerProductContractPriceList[1].setIfoDerivativeType(IfoDerivativeTypeEnum.FUTURES);
          l_ifoSummaryontractPerProductContractPriceList[1].setUnitSize(100);
          l_ifoSummaryontractPerProductContractPriceList[1].setSellQuantity(50);
          
          
          Field field = WEB3IfoDepositCalc.class.getDeclaredField("ifoSummaryContractPerProductContractPriceList");
          field.setAccessible(true);
          field.set(depositCalc, l_ifoSummaryontractPerProductContractPriceList);
          double l_dblReturn = depositCalc.calcFuturesBuyAppraisalProfitLoss(); 
          assertEquals(250000, l_dblReturn, 0);
      }
      catch(Exception l_ex)
      {
          log.error(l_ex.getMessage());
          fail();
      }

      log.exiting(STR_METHOD_NAME);
  }
  
  //建玉が存在しない場合，先物売建評価損益　@=　@0
  public void testCalcFuturesSellAppraisalProfitLoss_C001()
  {
      String STR_METHOD_NAME = "testCalcFuturesSellAppraisalProfitLoss_C001";
      log.entering(STR_METHOD_NAME);
      try
      {
          WEB3IfoSummaryContractPerProductContractPrice[] l_ifoSummaryontractPerProductContractPriceList = null;
          Field field = WEB3IfoDepositCalc.class.getDeclaredField("ifoSummaryContractPerProductContractPriceList");
          field.setAccessible(true);
          field.set(depositCalc, l_ifoSummaryontractPerProductContractPriceList);
          double l_dblReturn = depositCalc.calcFuturesSellAppraisalProfitLoss(); 
          assertEquals(0, l_dblReturn, 0);
      }
      catch(Exception l_ex)
      {
          log.error(l_ex.getMessage());
          fail();
      }

      log.exiting(STR_METHOD_NAME);
  }
  
  //建玉が存在する場合
  public void testCalcFuturesSellAppraisalProfitLoss_C002()
  {
      String STR_METHOD_NAME = "testCalcFuturesSellAppraisalProfitLoss_C002";
      log.entering(STR_METHOD_NAME);
      try
      {
          WEB3IfoSummaryContractPerProductContractPrice[] l_ifoSummaryontractPerProductContractPriceList =
              new WEB3IfoSummaryContractPerProductContractPrice[2];
          l_ifoSummaryontractPerProductContractPriceList[0] =
              new WEB3IfoSummaryContractPerProductContractPrice();
          l_ifoSummaryontractPerProductContractPriceList[0].setBuyQuantity(100);
          l_ifoSummaryontractPerProductContractPriceList[0].setContractPrice(20);
          l_ifoSummaryontractPerProductContractPriceList[0].setCurrentPrice(25);
          l_ifoSummaryontractPerProductContractPriceList[0].setIfoDerivativeType(IfoDerivativeTypeEnum.FUTURES);
          l_ifoSummaryontractPerProductContractPriceList[0].setUnitSize(100);
          l_ifoSummaryontractPerProductContractPriceList[0].setSellQuantity(50);
          
          l_ifoSummaryontractPerProductContractPriceList[1] =
              new WEB3IfoSummaryContractPerProductContractPrice();
          l_ifoSummaryontractPerProductContractPriceList[1].setBuyQuantity(200);
          l_ifoSummaryontractPerProductContractPriceList[1].setContractPrice(20);
          l_ifoSummaryontractPerProductContractPriceList[1].setCurrentPrice(30);
          l_ifoSummaryontractPerProductContractPriceList[1].setIfoDerivativeType(IfoDerivativeTypeEnum.FUTURES);
          l_ifoSummaryontractPerProductContractPriceList[1].setUnitSize(100);
          l_ifoSummaryontractPerProductContractPriceList[1].setSellQuantity(50);
          Field field = WEB3IfoDepositCalc.class.getDeclaredField("ifoSummaryContractPerProductContractPriceList");
          field.setAccessible(true);
          field.set(depositCalc, l_ifoSummaryontractPerProductContractPriceList);
          double l_dblReturn = depositCalc.calcFuturesSellAppraisalProfitLoss(); 
          assertEquals(-75000, l_dblReturn, 0);
      }
      catch(Exception l_ex)
      {
          log.error(l_ex.getMessage());
          fail();
      }

      log.exiting(STR_METHOD_NAME);
  }
  //正しいオプション評価損益を返却する
  public void testCalcOptionAppraisalProfitLoss()
  {
      String STR_METHOD_NAME = "testCalcFuturesAppraisalProfitLoss";
      log.entering(STR_METHOD_NAME);
      try
      {
          WEB3IfoSummaryContractPerProductContractPrice[] l_ifoSummaryontractPerProductContractPriceList =
              new WEB3IfoSummaryContractPerProductContractPrice[1];
          l_ifoSummaryontractPerProductContractPriceList[0] =
              new WEB3IfoSummaryContractPerProductContractPrice();
          l_ifoSummaryontractPerProductContractPriceList[0].setBuyQuantity(100);
          l_ifoSummaryontractPerProductContractPriceList[0].setContractPrice(20);
          l_ifoSummaryontractPerProductContractPriceList[0].setCurrentPrice(25);
          l_ifoSummaryontractPerProductContractPriceList[0].setIfoDerivativeType(IfoDerivativeTypeEnum.CALL_OPTIONS);
          l_ifoSummaryontractPerProductContractPriceList[0].setUnitSize(100);
          l_ifoSummaryontractPerProductContractPriceList[0].setSellQuantity(200);
          
          Field field = WEB3IfoDepositCalc.class.getDeclaredField("ifoSummaryContractPerProductContractPriceList");
          field.setAccessible(true);
          field.set(depositCalc, l_ifoSummaryontractPerProductContractPriceList);
          double l_dblReturn = depositCalc.calcOptionAppraisalProfitLoss(); 
          assertEquals(-50000, l_dblReturn, 0);
      }
      catch(Exception l_ex)
      {
          log.error(l_ex.getMessage());
          fail();
      }

      log.exiting(STR_METHOD_NAME);
  }
  
  //建玉が存在しない場合,オプション買建評価損益　@=　@0
  public void testCalcOptionBuyAppraisalProfitLoss_C001()
  {
      String STR_METHOD_NAME = "testCalcOptionBuyAppraisalProfitLoss_C001";
      log.entering(STR_METHOD_NAME);
      try
      {
          WEB3IfoSummaryContractPerProductContractPrice[] l_ifoSummaryontractPerProductContractPriceList = null;
          Field field = WEB3IfoDepositCalc.class.getDeclaredField("ifoSummaryContractPerProductContractPriceList");
          field.setAccessible(true);
          field.set(depositCalc, l_ifoSummaryontractPerProductContractPriceList);
          double l_dblReturn = depositCalc.calcOptionBuyAppraisalProfitLoss(); 
          assertEquals(0, l_dblReturn, 0);
      }
      catch(Exception l_ex)
      {
          log.error(l_ex.getMessage());
          fail();
      }

      log.exiting(STR_METHOD_NAME);
  }
  
  //建玉が存在する場合、
  public void testCalcOptionBuyAppraisalProfitLoss_C002()
  {
      String STR_METHOD_NAME = "testCalcOptionBuyAppraisalProfitLoss_C002";
      log.entering(STR_METHOD_NAME);
      try
      {
          WEB3IfoSummaryContractPerProductContractPrice[] l_ifoSummaryontractPerProductContractPriceList =
              new WEB3IfoSummaryContractPerProductContractPrice[2];
          l_ifoSummaryontractPerProductContractPriceList[0] =
              new WEB3IfoSummaryContractPerProductContractPrice();
          l_ifoSummaryontractPerProductContractPriceList[0].setBuyQuantity(100);
          l_ifoSummaryontractPerProductContractPriceList[0].setContractPrice(20);
          l_ifoSummaryontractPerProductContractPriceList[0].setCurrentPrice(25);
          l_ifoSummaryontractPerProductContractPriceList[0].setIfoDerivativeType(IfoDerivativeTypeEnum.CALL_OPTIONS);
          l_ifoSummaryontractPerProductContractPriceList[0].setUnitSize(100);
          l_ifoSummaryontractPerProductContractPriceList[0].setSellQuantity(50);
          
          l_ifoSummaryontractPerProductContractPriceList[1] =
              new WEB3IfoSummaryContractPerProductContractPrice();
          l_ifoSummaryontractPerProductContractPriceList[1].setBuyQuantity(200);
          l_ifoSummaryontractPerProductContractPriceList[1].setContractPrice(20);
          l_ifoSummaryontractPerProductContractPriceList[1].setCurrentPrice(30);
          l_ifoSummaryontractPerProductContractPriceList[1].setIfoDerivativeType(IfoDerivativeTypeEnum.CALL_OPTIONS);
          l_ifoSummaryontractPerProductContractPriceList[1].setUnitSize(100);
          l_ifoSummaryontractPerProductContractPriceList[1].setSellQuantity(50);
          Field field = WEB3IfoDepositCalc.class.getDeclaredField("ifoSummaryContractPerProductContractPriceList");
          field.setAccessible(true);
          field.set(depositCalc, l_ifoSummaryontractPerProductContractPriceList);
          double l_dblReturn = depositCalc.calcOptionBuyAppraisalProfitLoss(); 
          assertEquals(250000, l_dblReturn, 0);
      }
      catch(Exception l_ex)
      {
          log.error(l_ex.getMessage());
          fail();
      }

      log.exiting(STR_METHOD_NAME);
  }
  
  //建玉が存在しない場合,オプション売建評価損益　@=　@0
  public void testCalcOptionSellAppraisalProfitLoss_C001()
  {
      String STR_METHOD_NAME = "testCalcOptionSellAppraisalProfitLoss_C001";
      log.entering(STR_METHOD_NAME);
      try
      {
          WEB3IfoSummaryContractPerProductContractPrice[] l_ifoSummaryontractPerProductContractPriceList = null;
          Field field = WEB3IfoDepositCalc.class.getDeclaredField("ifoSummaryContractPerProductContractPriceList");
          field.setAccessible(true);
          field.set(depositCalc, l_ifoSummaryontractPerProductContractPriceList);
          double l_dblReturn = depositCalc.calcOptionSellAppraisalProfitLoss(); 
          assertEquals(0, l_dblReturn, 0);
      }
      catch(Exception l_ex)
      {
          log.error(l_ex.getMessage());
          fail();
      }

      log.exiting(STR_METHOD_NAME);
  }
  
  //
  public void testCalcOptionSellAppraisalProfitLoss_C002()
  {
      String STR_METHOD_NAME = "testCalcOptionSellAppraisalProfitLoss_C002";
      log.entering(STR_METHOD_NAME);
      try
      {
          WEB3IfoSummaryContractPerProductContractPrice[] l_ifoSummaryontractPerProductContractPriceList =
              new WEB3IfoSummaryContractPerProductContractPrice[2];
          l_ifoSummaryontractPerProductContractPriceList[0] =
              new WEB3IfoSummaryContractPerProductContractPrice();
          l_ifoSummaryontractPerProductContractPriceList[0].setBuyQuantity(100);
          l_ifoSummaryontractPerProductContractPriceList[0].setContractPrice(20);
          l_ifoSummaryontractPerProductContractPriceList[0].setCurrentPrice(25);
          l_ifoSummaryontractPerProductContractPriceList[0].setIfoDerivativeType(IfoDerivativeTypeEnum.CALL_OPTIONS);
          l_ifoSummaryontractPerProductContractPriceList[0].setUnitSize(100);
          l_ifoSummaryontractPerProductContractPriceList[0].setSellQuantity(50);
          
          l_ifoSummaryontractPerProductContractPriceList[1] =
              new WEB3IfoSummaryContractPerProductContractPrice();
          l_ifoSummaryontractPerProductContractPriceList[1].setBuyQuantity(200);
          l_ifoSummaryontractPerProductContractPriceList[1].setContractPrice(20);
          l_ifoSummaryontractPerProductContractPriceList[1].setCurrentPrice(30);
          l_ifoSummaryontractPerProductContractPriceList[1].setIfoDerivativeType(IfoDerivativeTypeEnum.CALL_OPTIONS);
          l_ifoSummaryontractPerProductContractPriceList[1].setUnitSize(100);
          l_ifoSummaryontractPerProductContractPriceList[1].setSellQuantity(50);
          Field field = WEB3IfoDepositCalc.class.getDeclaredField("ifoSummaryContractPerProductContractPriceList");
          field.setAccessible(true);
          field.set(depositCalc, l_ifoSummaryontractPerProductContractPriceList);
          double l_dblReturn = depositCalc.calcOptionSellAppraisalProfitLoss(); 
          assertEquals(-75000, l_dblReturn, 0);
      }
      catch(Exception l_ex)
      {
          log.error(l_ex.getMessage());
          fail();
      }

      log.exiting(STR_METHOD_NAME);
  }
  
  //指定日が0、1、2以外の時,nullを返却する。
  public void testCalcPositionBalance_C001()
  {
      String STR_MTHOD_NAME = "testCalcPositionBalance_C001";
      log.entering(STR_MTHOD_NAME);
      try
      {
          WEB3IfoSummaryContractPerIndex[] l_ifoSummaryContractPerIndexLists =
              new WEB3IfoSummaryContractPerIndex[1];
          l_ifoSummaryContractPerIndexLists[0] = new WEB3IfoSummaryContractPerIndex();
          l_ifoSummaryContractPerIndexLists[0].setCurrentBizDateFuturesBuyOrderQuantity(10);
          l_ifoSummaryContractPerIndexLists[0].setIfoDepositPerUnit(1000);
          l_ifoSummaryContractPerIndexLists[0].setFuturesBuyContractQuantity(100);
          l_ifoSummaryContractPerIndexLists[0].setCurrentBizDateFuturesBuyOrderQuantity(200);
          l_ifoSummaryContractPerIndexLists[0].setNextBizDateFuturesBuyOrderQuantity(50);
          
          l_ifoSummaryContractPerIndexLists[0] = new WEB3IfoSummaryContractPerIndex();
          l_ifoSummaryContractPerIndexLists[0].setCurrentBizDateFuturesSellOrderQuantity(10);
          l_ifoSummaryContractPerIndexLists[0].setIfoDepositPerUnit(1000);
          l_ifoSummaryContractPerIndexLists[0].setFuturesBuyContractQuantity(100);
          l_ifoSummaryContractPerIndexLists[0].setCurrentBizDateFuturesSellOrderQuantity(200);
          l_ifoSummaryContractPerIndexLists[0].setNextBizDateFuturesSellOrderQuantity(50);
          
          Field field = WEB3IfoDepositCalc.class.getDeclaredField("ifoSummaryContractPerIndexList");
          field.setAccessible(true);
          field.set(depositCalc, l_ifoSummaryContractPerIndexLists);
          
          int l_intReservedDate = 4;
          WEB3IfoPositionBalance  l_ifoPositionBalance =
              depositCalc.calcPositionBalance(l_intReservedDate);
          assertNull(l_ifoPositionBalance);
      }
      catch(Exception l_ex)
      {
          log.error(l_ex.getMessage());
          fail();
      }
      log.exiting(STR_MTHOD_NAME);
  }
  
  //指定日の買ポジション建玉、売ポジション建玉が0である場合,nullを返却する。
  public void testCalcPositionBalance_C002()
  {
      String STR_MTHOD_NAME = "testCalcPositionBalance_C002";
      log.entering(STR_MTHOD_NAME);
      try
      {
          
          int l_intReservedDate = 1;
          WEB3IfoPositionBalance  l_ifoPositionBalance =
              depositCalc.calcPositionBalance(l_intReservedDate);
          assertNull(l_ifoPositionBalance);
      }
      catch(Exception l_ex)
      {
          log.error(l_ex.getMessage());
          fail();
      }
      log.exiting(STR_MTHOD_NAME);
  }
  
  // 買超過の場合,正しい先物OPポジションバランス返却する
    // ポジションバランス(n) = 買ポジション建玉(n) ? 売ポジション建玉(n)
    // ポジションバランス区分(n) = “買超過”
    public void testCalcPositionBalance_C003()
    {
        String STR_MTHOD_NAME = "testCalcPositionBalance_C003";
        log.entering(STR_MTHOD_NAME);
        try
        {
            WEB3IfoSummaryContractPerIndex[] l_ifoSummaryContractPerIndexLists = new WEB3IfoSummaryContractPerIndex[1];
            l_ifoSummaryContractPerIndexLists[0] = new WEB3IfoSummaryContractPerIndex();
            l_ifoSummaryContractPerIndexLists[0].setCurrentBizDateFuturesBuyOrderQuantity(10);
            l_ifoSummaryContractPerIndexLists[0].setFuturesBuyContractQuantity(550);
            l_ifoSummaryContractPerIndexLists[0].setCurrentBizDateFuturesBuyOrderQuantity(200);
            l_ifoSummaryContractPerIndexLists[0].setNextBizDateFuturesBuyOrderQuantity(70);
            l_ifoSummaryContractPerIndexLists[0].setCurrentBizDateFuturesSellOrderQuantity(5);
            l_ifoSummaryContractPerIndexLists[0].setFuturesSellContractQuantity(50);
            l_ifoSummaryContractPerIndexLists[0].setCurrentBizDateFuturesSellOrderQuantity(10);
            l_ifoSummaryContractPerIndexLists[0].setNextBizDateFuturesSellOrderQuantity(50);
            l_ifoSummaryContractPerIndexLists[0].setTargetProductCode("0120");
            Field field = WEB3IfoDepositCalc.class.getDeclaredField("ifoSummaryContractPerIndexList");
            field.setAccessible(true);
            field.set(depositCalc, l_ifoSummaryContractPerIndexLists);

            int l_intReservedDate = 1;
            WEB3IfoPositionBalance l_ifoPositionBalance = depositCalc.calcPositionBalance(l_intReservedDate);
            assertEquals(WEB3IfoPositionBalanceTypeDef.BUY_OVER, l_ifoPositionBalance.positionBalanceType);
            assertEquals(710, l_ifoPositionBalance.positionBalance, 0);

        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage());
            fail();
        }
        log.exiting(STR_MTHOD_NAME);
    }

    // 売超過の場合,正しい先物OPポジションバランス返却する
    // ポジションバランス(n) = 売ポジション建玉(n) ? 買ポジション建玉(n)
    // ポジションバランス区分(n) = “売超過”
    public void testCalcPositionBalance_C004()
    {
        String STR_MTHOD_NAME = "testCalcPositionBalance_C004";
        log.entering(STR_MTHOD_NAME);
        try
        {
            WEB3IfoSummaryContractPerIndex[] l_ifoSummaryContractPerIndexLists = new WEB3IfoSummaryContractPerIndex[1];
            l_ifoSummaryContractPerIndexLists[0] = new WEB3IfoSummaryContractPerIndex();
            l_ifoSummaryContractPerIndexLists[0].setCurrentBizDateFuturesBuyOrderQuantity(1);
            l_ifoSummaryContractPerIndexLists[0].setIfoDepositPerUnit(1);
            l_ifoSummaryContractPerIndexLists[0].setFuturesBuyContractQuantity(1);
            l_ifoSummaryContractPerIndexLists[0].setCurrentBizDateFuturesBuyOrderQuantity(1);
            l_ifoSummaryContractPerIndexLists[0].setNextBizDateFuturesBuyOrderQuantity(10);
            l_ifoSummaryContractPerIndexLists[0].setCurrentBizDateFuturesSellOrderQuantity(5);
            l_ifoSummaryContractPerIndexLists[0].setIfoDepositPerUnit(100);
            l_ifoSummaryContractPerIndexLists[0].setFuturesBuyContractQuantity(50);
            l_ifoSummaryContractPerIndexLists[0].setCurrentBizDateFuturesSellOrderQuantity(200);
            l_ifoSummaryContractPerIndexLists[0].setNextBizDateFuturesSellOrderQuantity(50);
            l_ifoSummaryContractPerIndexLists[0].setTargetProductCode("0120");
            Field field = WEB3IfoDepositCalc.class.getDeclaredField("ifoSummaryContractPerIndexList");
            field.setAccessible(true);
            field.set(depositCalc, l_ifoSummaryContractPerIndexLists);

            int l_intReservedDate = 1;
            WEB3IfoPositionBalance l_ifoPositionBalance = depositCalc.calcPositionBalance(l_intReservedDate);
            assertEquals(WEB3IfoPositionBalanceTypeDef.SELL_OVER, l_ifoPositionBalance.positionBalanceType);
            assertEquals(189, l_ifoPositionBalance.positionBalance, 0);

        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage());
            fail();
        }
        log.exiting(STR_MTHOD_NAME);
    }

//    買ポジション建玉と売ポジション建玉が等しい場合
//    ポジションバランス(n)　@=　@０
//    ポジションバランス区分(n) = “ニュートラル”
    public void testCalcPositionBalance_C005()
    {
        String STR_MTHOD_NAME = "testCalcPositionBalance_C005";
        log.entering(STR_MTHOD_NAME);
        try
        {
            WEB3IfoSummaryContractPerIndex[] l_ifoSummaryContractPerIndexLists = new WEB3IfoSummaryContractPerIndex[1];
            l_ifoSummaryContractPerIndexLists[0] = new WEB3IfoSummaryContractPerIndex();
            l_ifoSummaryContractPerIndexLists[0].setCurrentBizDateFuturesSellOrderQuantity(5);
            l_ifoSummaryContractPerIndexLists[0].setIfoDepositPerUnit(100);
            l_ifoSummaryContractPerIndexLists[0].setFuturesBuyContractQuantity(250);
            l_ifoSummaryContractPerIndexLists[0].setCurrentBizDateFuturesSellOrderQuantity(200);
            l_ifoSummaryContractPerIndexLists[0].setNextBizDateFuturesSellOrderQuantity(50);
            l_ifoSummaryContractPerIndexLists[0].setTargetProductCode("0120");
            Field field = WEB3IfoDepositCalc.class.getDeclaredField("ifoSummaryContractPerIndexList");
            field.setAccessible(true);
            field.set(depositCalc, l_ifoSummaryContractPerIndexLists);

            int l_intReservedDate = 1;
            WEB3IfoPositionBalance l_ifoPositionBalance = depositCalc.calcPositionBalance(l_intReservedDate);
            assertEquals(WEB3IfoPositionBalanceTypeDef.NEUTRAL, l_ifoPositionBalance.positionBalanceType);
            assertEquals(0, l_ifoPositionBalance.positionBalance, 0);

        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage());
            fail();
        }
        log.exiting(STR_MTHOD_NAME);
    }
    
    // 指定日 = 0
    public void testCalcPositionBalance_C006()
    {
        String STR_MTHOD_NAME = "testCalcPositionBalance_C006";
        log.entering(STR_MTHOD_NAME);
        try
        {
            WEB3IfoSummaryContractPerIndex[] l_ifoSummaryContractPerIndexLists = new WEB3IfoSummaryContractPerIndex[1];
            l_ifoSummaryContractPerIndexLists[0] = new WEB3IfoSummaryContractPerIndex();
            l_ifoSummaryContractPerIndexLists[0].setCurrentBizDateFuturesBuyOrderQuantity(10);
            l_ifoSummaryContractPerIndexLists[0].setFuturesBuyContractQuantity(550);
            l_ifoSummaryContractPerIndexLists[0].setCurrentBizDateFuturesBuyOrderQuantity(200);
            l_ifoSummaryContractPerIndexLists[0].setNextBizDateFuturesBuyOrderQuantity(70);
            l_ifoSummaryContractPerIndexLists[0].setCurrentBizDateFuturesSellOrderQuantity(5);
            l_ifoSummaryContractPerIndexLists[0].setFuturesSellContractQuantity(50);
            l_ifoSummaryContractPerIndexLists[0].setCurrentBizDateFuturesSellOrderQuantity(10);
            l_ifoSummaryContractPerIndexLists[0].setNextBizDateFuturesSellOrderQuantity(50);
            l_ifoSummaryContractPerIndexLists[0].setTargetProductCode("0120");
            Field field = WEB3IfoDepositCalc.class.getDeclaredField("ifoSummaryContractPerIndexList");
            field.setAccessible(true);
            field.set(depositCalc, l_ifoSummaryContractPerIndexLists);

            int l_intReservedDate = 0;
            WEB3IfoPositionBalance l_ifoPositionBalance = depositCalc.calcPositionBalance(l_intReservedDate);
            assertEquals(WEB3IfoPositionBalanceTypeDef.BUY_OVER, l_ifoPositionBalance.positionBalanceType);
            assertEquals(690, l_ifoPositionBalance.positionBalance, 0);

        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage());
            fail();
        }
        log.exiting(STR_MTHOD_NAME);
    }
    
    // 指定日 =2
    public void testCalcPositionBalance_C007()
    {
        String STR_MTHOD_NAME = "testCalcPositionBalance_C007";
        log.entering(STR_MTHOD_NAME);
        try
        {
            WEB3IfoSummaryContractPerIndex[] l_ifoSummaryContractPerIndexLists = new WEB3IfoSummaryContractPerIndex[1];
            l_ifoSummaryContractPerIndexLists[0] = new WEB3IfoSummaryContractPerIndex();
            l_ifoSummaryContractPerIndexLists[0].setCurrentBizDateFuturesBuyOrderQuantity(10);
            l_ifoSummaryContractPerIndexLists[0].setFuturesBuyContractQuantity(550);
            l_ifoSummaryContractPerIndexLists[0].setCurrentBizDateFuturesBuyOrderQuantity(200);
            l_ifoSummaryContractPerIndexLists[0].setNextBizDateFuturesBuyOrderQuantity(70);
            l_ifoSummaryContractPerIndexLists[0].setCurrentBizDateFuturesSellOrderQuantity(5);
            l_ifoSummaryContractPerIndexLists[0].setFuturesSellContractQuantity(50);
            l_ifoSummaryContractPerIndexLists[0].setCurrentBizDateFuturesSellOrderQuantity(10);
            l_ifoSummaryContractPerIndexLists[0].setNextBizDateFuturesSellOrderQuantity(50);
            l_ifoSummaryContractPerIndexLists[0].setTargetProductCode("0120");
            Field field = WEB3IfoDepositCalc.class.getDeclaredField("ifoSummaryContractPerIndexList");
            field.setAccessible(true);
            field.set(depositCalc, l_ifoSummaryContractPerIndexLists);

            int l_intReservedDate = 2;
            WEB3IfoPositionBalance l_ifoPositionBalance = depositCalc.calcPositionBalance(l_intReservedDate);
            assertEquals(WEB3IfoPositionBalanceTypeDef.BUY_OVER, l_ifoPositionBalance.positionBalanceType);
            assertEquals(710, l_ifoPositionBalance.positionBalance, 0);

        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage());
            fail();
        }
        log.exiting(STR_MTHOD_NAME);
    }
    
    //指定日が0、1、2以外の時,0を返却する。
    public void testCalcNetOptionTotalAmount_C001()
    {
        String STR_MTHOD_NAME = "testCalcNetOptionTotalAmount_C001";
        log.entering(STR_MTHOD_NAME);
        
        int l_intReservedDate = 4;
        double l_dbReturn = depositCalc.calcNetOptionTotalAmount(l_intReservedDate);
        assertEquals(0, l_dbReturn, 0);
        log.exiting(STR_MTHOD_NAME);
    }
    
    //指定日が1
    public void testCalcNetOptionTotalAmount_C002()
    {
        String STR_MTHOD_NAME = "testCalcNetOptionTotalAmount_C002";
        log.entering(STR_MTHOD_NAME);
        try
        {
            WEB3IfoSummaryContractPerProduct[] ifoSummaryContractPerProductList =
                new WEB3IfoSummaryContractPerProduct[1];
            ifoSummaryContractPerProductList[0] = new WEB3IfoSummaryContractPerProduct();
            ifoSummaryContractPerProductList[0].setCurrentPrice(233);
            ifoSummaryContractPerProductList[0].setUnitSize(6.66);
            ifoSummaryContractPerProductList[0].setBuyQuantity(2);
            ifoSummaryContractPerProductList[0].setCurrentBizDateBuyOrderQuantity(4);
            ifoSummaryContractPerProductList[0].setIfoDerivativeType(IfoDerivativeTypeEnum.PUT_OPTIONS);
            ifoSummaryContractPerProductList[0].setSellQuantity(8);
            
            Field field = WEB3IfoDepositCalc.class.getDeclaredField("ifoSummaryContractPerProductList");
            field.setAccessible(true);
            field.set(depositCalc, ifoSummaryContractPerProductList);
            int l_intReservedDate = 1;
            double l_dbReturn = depositCalc.calcNetOptionTotalAmount(l_intReservedDate);
            assertEquals(-3103, l_dbReturn, 0);
        }
        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage());
            fail();
        }

        log.exiting(STR_MTHOD_NAME);
    }
    
    //指定日が0
    public void testCalcNetOptionTotalAmount_C003()
    {
        String STR_MTHOD_NAME = "testCalcNetOptionTotalAmount_C003";
        log.entering(STR_MTHOD_NAME);
        try
        {
            WEB3IfoSummaryContractPerProduct[] ifoSummaryContractPerProductList =
                new WEB3IfoSummaryContractPerProduct[1];
            ifoSummaryContractPerProductList[0] = new WEB3IfoSummaryContractPerProduct();
            ifoSummaryContractPerProductList[0].setCurrentPrice(233);
            ifoSummaryContractPerProductList[0].setUnitSize(6.66);
            ifoSummaryContractPerProductList[0].setBuyQuantity(2);
            ifoSummaryContractPerProductList[0].setCurrentBizDateBuyOrderQuantity(4);
            ifoSummaryContractPerProductList[0].setIfoDerivativeType(IfoDerivativeTypeEnum.PUT_OPTIONS);
            ifoSummaryContractPerProductList[0].setSellQuantity(8);
            
            Field field = WEB3IfoDepositCalc.class.getDeclaredField("ifoSummaryContractPerProductList");
            field.setAccessible(true);
            field.set(depositCalc, ifoSummaryContractPerProductList);
            int l_intReservedDate = 0;
            double l_dbReturn = depositCalc.calcNetOptionTotalAmount(l_intReservedDate);
            assertEquals(-3103, l_dbReturn, 0);
        }
        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage());
            fail();
        }

        log.exiting(STR_MTHOD_NAME);
    }
    
    //指定日が2
    public void testCalcNetOptionTotalAmount_C004()
    {
        String STR_MTHOD_NAME = "testCalcNetOptionTotalAmount_C004";
        log.entering(STR_MTHOD_NAME);
        try
        {
            WEB3IfoSummaryContractPerProduct[] ifoSummaryContractPerProductList =
                new WEB3IfoSummaryContractPerProduct[1];
            ifoSummaryContractPerProductList[0] = new WEB3IfoSummaryContractPerProduct();
            ifoSummaryContractPerProductList[0].setCurrentPrice(233);
            ifoSummaryContractPerProductList[0].setUnitSize(6.66);
            ifoSummaryContractPerProductList[0].setBuyQuantity(2);
            ifoSummaryContractPerProductList[0].setCurrentBizDateBuyOrderQuantity(4);
            ifoSummaryContractPerProductList[0].setIfoDerivativeType(IfoDerivativeTypeEnum.PUT_OPTIONS);
            ifoSummaryContractPerProductList[0].setSellQuantity(8);
            
            Field field = WEB3IfoDepositCalc.class.getDeclaredField("ifoSummaryContractPerProductList");
            field.setAccessible(true);
            field.set(depositCalc, ifoSummaryContractPerProductList);
            int l_intReservedDate = 2;
            double l_dbReturn = depositCalc.calcNetOptionTotalAmount(l_intReservedDate);
            assertEquals(-3103, l_dbReturn, 0);
        }
        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage());
            fail();
        }

        log.exiting(STR_MTHOD_NAME);
    }
    
    //指定日が範囲外の場合(nが0以上2以下でない場合)、0を返却する
    public void testCalcOptionBuyTotalAmount_C001()
    {
        String STR_MTHOD_NAME = "testCalcOptionBuyTotalAmount_C001";
        log.entering(STR_MTHOD_NAME);
        try
        {
            WEB3IfoSummaryContractPerProduct[] ifoSummaryContractPerProductList =
                new WEB3IfoSummaryContractPerProduct[1];
            ifoSummaryContractPerProductList[0] = new WEB3IfoSummaryContractPerProduct();
            ifoSummaryContractPerProductList[0].setCurrentPrice(233);
            ifoSummaryContractPerProductList[0].setUnitSize(6.66);
            ifoSummaryContractPerProductList[0].setBuyQuantity(2);
            ifoSummaryContractPerProductList[0].setCurrentBizDateBuyOrderQuantity(4);
            ifoSummaryContractPerProductList[0].setIfoDerivativeType(IfoDerivativeTypeEnum.PUT_OPTIONS);
            ifoSummaryContractPerProductList[0].setSellQuantity(8);
            
            Field field = WEB3IfoDepositCalc.class.getDeclaredField("ifoSummaryContractPerProductList");
            field.setAccessible(true);
            field.set(depositCalc, ifoSummaryContractPerProductList);
            int l_intReservedDate = 4;
            double l_dbReturn = depositCalc.calcOptionBuyTotalAmount(l_intReservedDate);
            assertEquals(0, l_dbReturn, 0);
        }
        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage());
            fail();
        }

        log.exiting(STR_MTHOD_NAME);
    }
    
    //指定日が範囲外の場合(nが0以上2以下でない場合)、0を返却する
    public void testCalcOptionBuyTotalAmount_C002()
    {
        String STR_MTHOD_NAME = "testCalcOptionBuyTotalAmount_C002";
        log.entering(STR_MTHOD_NAME);
        try
        {
            WEB3IfoSummaryContractPerProduct[] ifoSummaryContractPerProductList = null;
            
            Field field = WEB3IfoDepositCalc.class.getDeclaredField("ifoSummaryContractPerProductList");
            field.setAccessible(true);
            field.set(depositCalc, ifoSummaryContractPerProductList);
            int l_intReservedDate = 1;
            double l_dbReturn = depositCalc.calcOptionBuyTotalAmount(l_intReservedDate);
            assertEquals(0, l_dbReturn, 0);
        }
        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage());
            fail();
        }

        log.exiting(STR_MTHOD_NAME);
    }
    
    
    //指定日が範囲外の場合(nが0以上2以下でない場合)、0を返却する
    public void testCalcOptionBuyTotalAmount_C003()
    {
        String STR_MTHOD_NAME = "testCalcOptionBuyTotalAmount_C003";
        log.entering(STR_MTHOD_NAME);
        try
        {
            WEB3IfoSummaryContractPerProduct[] ifoSummaryContractPerProductList =
                new WEB3IfoSummaryContractPerProduct[2];
            ifoSummaryContractPerProductList[0] = new WEB3IfoSummaryContractPerProduct();
            ifoSummaryContractPerProductList[0].setCurrentPrice(233);
            ifoSummaryContractPerProductList[0].setUnitSize(6);
            ifoSummaryContractPerProductList[0].setBuyQuantity(10);
            ifoSummaryContractPerProductList[0].setCurrentBizDateBuyOrderQuantity(4);
            ifoSummaryContractPerProductList[0].setIfoDerivativeType(IfoDerivativeTypeEnum.PUT_OPTIONS);
            ifoSummaryContractPerProductList[0].setSellQuantity(8);
            
            
            ifoSummaryContractPerProductList[1] = new WEB3IfoSummaryContractPerProduct();
            ifoSummaryContractPerProductList[1].setCurrentPrice(233);
            ifoSummaryContractPerProductList[1].setUnitSize(6);
            ifoSummaryContractPerProductList[1].setBuyQuantity(12);
            ifoSummaryContractPerProductList[1].setCurrentBizDateBuyOrderQuantity(8);
            ifoSummaryContractPerProductList[1].setIfoDerivativeType(IfoDerivativeTypeEnum.PUT_OPTIONS);
            ifoSummaryContractPerProductList[1].setSellQuantity(8);
            
            Field field = WEB3IfoDepositCalc.class.getDeclaredField("ifoSummaryContractPerProductList");
            field.setAccessible(true);
            field.set(depositCalc, ifoSummaryContractPerProductList);
            int l_intReservedDate = 1;
            double l_dbReturn = depositCalc.calcOptionBuyTotalAmount(l_intReservedDate);
            assertEquals(25164, l_dbReturn, 0);
        }
        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage());
            fail();
        }

        log.exiting(STR_MTHOD_NAME);
    }
    //指定日が範囲外の場合(nが0以上2以下でない場合)、0を返却する
    public void testCalcOptionSellTotalAmount_C001()
    {
        String STR_MTHOD_NAME = "testCalcOptionSellTotalAmount_C001";
        log.entering(STR_MTHOD_NAME);
        try
        {
            WEB3IfoSummaryContractPerProduct[] ifoSummaryContractPerProductList =
                new WEB3IfoSummaryContractPerProduct[1];
            ifoSummaryContractPerProductList[0] = new WEB3IfoSummaryContractPerProduct();
            ifoSummaryContractPerProductList[0].setCurrentPrice(233);
            ifoSummaryContractPerProductList[0].setUnitSize(6.66);
            ifoSummaryContractPerProductList[0].setBuyQuantity(2);
            ifoSummaryContractPerProductList[0].setCurrentBizDateBuyOrderQuantity(4);
            ifoSummaryContractPerProductList[0].setIfoDerivativeType(IfoDerivativeTypeEnum.PUT_OPTIONS);
            ifoSummaryContractPerProductList[0].setSellQuantity(8);
            
            Field field = WEB3IfoDepositCalc.class.getDeclaredField("ifoSummaryContractPerProductList");
            field.setAccessible(true);
            field.set(depositCalc, ifoSummaryContractPerProductList);
            int l_intReservedDate = 4;
            double l_dbReturn = depositCalc.calcOptionSellTotalAmount(l_intReservedDate);
            assertEquals(0, l_dbReturn, 0);
        }
        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage());
            fail();
        }

        log.exiting(STR_MTHOD_NAME);
    }
    
    //指定日が範囲外の場合(nが0以上2以下でない場合)、0を返却する
    public void testCalcOptionSellTotalAmount_C002()
    {
        String STR_MTHOD_NAME = "testCalcOptionSellTotalAmount_C002";
        log.entering(STR_MTHOD_NAME);
        try
        {
            WEB3IfoSummaryContractPerProduct[] ifoSummaryContractPerProductList = null;
            
            Field field = WEB3IfoDepositCalc.class.getDeclaredField("ifoSummaryContractPerProductList");
            field.setAccessible(true);
            field.set(depositCalc, ifoSummaryContractPerProductList);
            int l_intReservedDate = 1;
            double l_dbReturn = depositCalc.calcOptionSellTotalAmount(l_intReservedDate);
            assertEquals(0, l_dbReturn, 0);
        }
        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage());
            fail();
        }

        log.exiting(STR_MTHOD_NAME);
    }
    
    
    //指定日が範囲外の場合(nが0以上2以下でない場合)、0を返却する
    public void testCalcOptionSellTotalAmount_C003()
    {
        String STR_MTHOD_NAME = "testCalcOptionSellTotalAmount_C003";
        log.entering(STR_MTHOD_NAME);
        try
        {
            WEB3IfoSummaryContractPerProduct[] ifoSummaryContractPerProductList =
                new WEB3IfoSummaryContractPerProduct[2];
            ifoSummaryContractPerProductList[0] = new WEB3IfoSummaryContractPerProduct();
            ifoSummaryContractPerProductList[0].setCurrentPrice(233);
            ifoSummaryContractPerProductList[0].setUnitSize(6);
            ifoSummaryContractPerProductList[0].setBuyQuantity(1);
            ifoSummaryContractPerProductList[0].setIfoDerivativeType(IfoDerivativeTypeEnum.PUT_OPTIONS);
            ifoSummaryContractPerProductList[0].setSellQuantity(8);
            
            
            ifoSummaryContractPerProductList[1] = new WEB3IfoSummaryContractPerProduct();
            ifoSummaryContractPerProductList[1].setCurrentPrice(233);
            ifoSummaryContractPerProductList[1].setUnitSize(6);
            ifoSummaryContractPerProductList[1].setBuyQuantity(2);
            ifoSummaryContractPerProductList[1].setIfoDerivativeType(IfoDerivativeTypeEnum.PUT_OPTIONS);
            ifoSummaryContractPerProductList[1].setSellQuantity(8);
            
            Field field = WEB3IfoDepositCalc.class.getDeclaredField("ifoSummaryContractPerProductList");
            field.setAccessible(true);
            field.set(depositCalc, ifoSummaryContractPerProductList);
            int l_intReservedDate = 1;
            double l_dbReturn = depositCalc.calcOptionSellTotalAmount(l_intReservedDate);
            assertEquals(18174, l_dbReturn, 0);
        }
        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage());
            fail();
        }

        log.exiting(STR_MTHOD_NAME);
    }
    
    //指定日が範囲外の場合(nが0以上2以下でない場合)、0を返却する。
    public void testCalcSimpleSPANIfoDeposit_C001()
    {
        String STR_METHOD_NAME = "testCalcSimpleSPANIfoDeposit_C001";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            WEB3IfoSummaryContractPerProduct[] ifoSummaryContractPerProductList =
                new WEB3IfoSummaryContractPerProduct[2];
            ifoSummaryContractPerProductList[0] = new WEB3IfoSummaryContractPerProduct();
            ifoSummaryContractPerProductList[0].setCurrentPrice(233);
            ifoSummaryContractPerProductList[0].setUnitSize(6);
            ifoSummaryContractPerProductList[0].setBuyQuantity(1);
            ifoSummaryContractPerProductList[0].setIfoDerivativeType(IfoDerivativeTypeEnum.PUT_OPTIONS);
            ifoSummaryContractPerProductList[0].setSellQuantity(8);
            
            Field field = WEB3IfoDepositCalc.class.getDeclaredField("ifoSummaryContractPerProductList");
            field.setAccessible(true);
            field.set(depositCalc, ifoSummaryContractPerProductList);
            int l_intReservedDate = 4;
            double l_dbReturn = depositCalc.calcSimpleSPANIfoDeposit(l_intReservedDate);
            assertEquals(0, l_dbReturn, 0);
        }
        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage());
            fail();
        }  
        log.exiting(STR_METHOD_NAME);
    }
    
    //建玉/注文(*)が存在しない場合,簡易SPAN証拠金(n)　@=　@0
    public void testCalcSimpleSPANIfoDeposit_C002()
    {
        String STR_METHOD_NAME = "testCalcSimpleSPANIfoDeposit_C002";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            WEB3IfoSummaryContractPerProduct[] ifoSummaryContractPerProductList = null;
            
            Field field = WEB3IfoDepositCalc.class.getDeclaredField("ifoSummaryContractPerProductList");
            field.setAccessible(true);
            field.set(depositCalc, ifoSummaryContractPerProductList);
            int l_intReservedDate = 1;
            double l_dbReturn = depositCalc.calcSimpleSPANIfoDeposit(l_intReservedDate);
            assertEquals(0, l_dbReturn, 0);
        }
        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage());
            fail();
        }  
        log.exiting(STR_METHOD_NAME);
    }
    
    //建玉/注文(*)が存在場合
    public void testCalcSimpleSPANIfoDeposit_C003()
    {
        String STR_METHOD_NAME = "testCalcSimpleSPANIfoDeposit_C003";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            WEB3IfoSummaryContractPerIndex[] l_ifoSummaryContractPerIndexLists =
                new WEB3IfoSummaryContractPerIndex[2];
            l_ifoSummaryContractPerIndexLists[0] = new WEB3IfoSummaryContractPerIndex();
            l_ifoSummaryContractPerIndexLists[0].setCurrentBizDateFuturesSellOrderQuantity(5);
            l_ifoSummaryContractPerIndexLists[0].setIfoDepositPerUnit(100);
            l_ifoSummaryContractPerIndexLists[0].setFuturesBuyContractQuantity(250);
            l_ifoSummaryContractPerIndexLists[0].setCurrentBizDateFuturesSellOrderQuantity(200);
            l_ifoSummaryContractPerIndexLists[0].setNextBizDateFuturesSellOrderQuantity(50);
            l_ifoSummaryContractPerIndexLists[0].setTargetProductCode("0120");
            
            l_ifoSummaryContractPerIndexLists[1] = new WEB3IfoSummaryContractPerIndex();
            l_ifoSummaryContractPerIndexLists[1].setCurrentBizDateFuturesSellOrderQuantity(10);
            l_ifoSummaryContractPerIndexLists[1].setIfoDepositPerUnit(100);
            l_ifoSummaryContractPerIndexLists[1].setFuturesBuyContractQuantity(300);
            l_ifoSummaryContractPerIndexLists[1].setCurrentBizDateFuturesSellOrderQuantity(100);
            l_ifoSummaryContractPerIndexLists[1].setNextBizDateFuturesSellOrderQuantity(20);
            l_ifoSummaryContractPerIndexLists[1].setTargetProductCode("0020");
            Field field = WEB3IfoDepositCalc.class.getDeclaredField("ifoSummaryContractPerIndexList");
            field.setAccessible(true);
            field.set(depositCalc, l_ifoSummaryContractPerIndexLists);

            int l_intReservedDate = 1;
            double l_dbReturn = depositCalc.calcSimpleSPANIfoDeposit(l_intReservedDate);
            assertEquals(55000, l_dbReturn, 0);
        }
        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage());
            fail();
        }  
        log.exiting(STR_METHOD_NAME);
    }
    
    
    //指定日 = 0
    public void testCalcSimpleSPANIfoDeposit_C004()
    {
        String STR_METHOD_NAME = "testCalcSimpleSPANIfoDeposit_C004";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            WEB3IfoSummaryContractPerIndex[] l_ifoSummaryContractPerIndexLists =
                new WEB3IfoSummaryContractPerIndex[2];
            l_ifoSummaryContractPerIndexLists[0] = new WEB3IfoSummaryContractPerIndex();
            l_ifoSummaryContractPerIndexLists[0].setCurrentBizDateFuturesSellOrderQuantity(5);
            l_ifoSummaryContractPerIndexLists[0].setIfoDepositPerUnit(100);
            l_ifoSummaryContractPerIndexLists[0].setFuturesBuyContractQuantity(250);
            l_ifoSummaryContractPerIndexLists[0].setCurrentBizDateFuturesSellOrderQuantity(200);
            l_ifoSummaryContractPerIndexLists[0].setNextBizDateFuturesSellOrderQuantity(50);
            l_ifoSummaryContractPerIndexLists[0].setTargetProductCode("0120");
            
            l_ifoSummaryContractPerIndexLists[1] = new WEB3IfoSummaryContractPerIndex();
            l_ifoSummaryContractPerIndexLists[1].setCurrentBizDateFuturesSellOrderQuantity(10);
            l_ifoSummaryContractPerIndexLists[1].setIfoDepositPerUnit(100);
            l_ifoSummaryContractPerIndexLists[1].setFuturesBuyContractQuantity(300);
            l_ifoSummaryContractPerIndexLists[1].setCurrentBizDateFuturesSellOrderQuantity(100);
            l_ifoSummaryContractPerIndexLists[1].setNextBizDateFuturesSellOrderQuantity(20);
            l_ifoSummaryContractPerIndexLists[1].setTargetProductCode("0020");
            Field field = WEB3IfoDepositCalc.class.getDeclaredField("ifoSummaryContractPerIndexList");
            field.setAccessible(true);
            field.set(depositCalc, l_ifoSummaryContractPerIndexLists);

            int l_intReservedDate = 0;
            double l_dbReturn = depositCalc.calcSimpleSPANIfoDeposit(l_intReservedDate);
            assertEquals(55000, l_dbReturn, 0);
        }
        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage());
            fail();
        }  
        log.exiting(STR_METHOD_NAME);
    }
    
    //指定日 = 2
    public void testCalcSimpleSPANIfoDeposit_C005()
    {
        String STR_METHOD_NAME = "testCalcSimpleSPANIfoDeposit_C005";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            WEB3IfoSummaryContractPerIndex[] l_ifoSummaryContractPerIndexLists =
                new WEB3IfoSummaryContractPerIndex[2];
            l_ifoSummaryContractPerIndexLists[0] = new WEB3IfoSummaryContractPerIndex();
            l_ifoSummaryContractPerIndexLists[0].setCurrentBizDateFuturesSellOrderQuantity(5);
            l_ifoSummaryContractPerIndexLists[0].setIfoDepositPerUnit(100);
            l_ifoSummaryContractPerIndexLists[0].setFuturesBuyContractQuantity(250);
            l_ifoSummaryContractPerIndexLists[0].setCurrentBizDateFuturesSellOrderQuantity(200);
            l_ifoSummaryContractPerIndexLists[0].setNextBizDateFuturesSellOrderQuantity(50);
            l_ifoSummaryContractPerIndexLists[0].setTargetProductCode("0120");
            
            l_ifoSummaryContractPerIndexLists[1] = new WEB3IfoSummaryContractPerIndex();
            l_ifoSummaryContractPerIndexLists[1].setCurrentBizDateFuturesSellOrderQuantity(10);
            l_ifoSummaryContractPerIndexLists[1].setIfoDepositPerUnit(100);
            l_ifoSummaryContractPerIndexLists[1].setFuturesBuyContractQuantity(300);
            l_ifoSummaryContractPerIndexLists[1].setCurrentBizDateFuturesSellOrderQuantity(100);
            l_ifoSummaryContractPerIndexLists[1].setNextBizDateFuturesSellOrderQuantity(20);
            l_ifoSummaryContractPerIndexLists[1].setTargetProductCode("0020");
            Field field = WEB3IfoDepositCalc.class.getDeclaredField("ifoSummaryContractPerIndexList");
            field.setAccessible(true);
            field.set(depositCalc, l_ifoSummaryContractPerIndexLists);

            int l_intReservedDate = 2;
            double l_dbReturn = depositCalc.calcSimpleSPANIfoDeposit(l_intReservedDate);
            assertEquals(55000, l_dbReturn, 0);
        }
        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage());
            fail();
        }  
        log.exiting(STR_METHOD_NAME);
    }
    
    //指定日が範囲外の場合(nが0以上2以下でない場合)、0を返却する。
    public void testCalcSimpleSPANIfoDepositRed_C001()
    {
        String STR_METHOD_NAME = "testCalcSimpleSPANIfoDepositRed_C001";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            WEB3IfoSummaryContractPerProduct[] ifoSummaryContractPerProductList =
                new WEB3IfoSummaryContractPerProduct[2];
            ifoSummaryContractPerProductList[0] = new WEB3IfoSummaryContractPerProduct();
            ifoSummaryContractPerProductList[0].setCurrentPrice(233);
            ifoSummaryContractPerProductList[0].setUnitSize(6);
            ifoSummaryContractPerProductList[0].setBuyQuantity(1);
            ifoSummaryContractPerProductList[0].setIfoDerivativeType(IfoDerivativeTypeEnum.PUT_OPTIONS);
            ifoSummaryContractPerProductList[0].setSellQuantity(8);
            
            Field field = WEB3IfoDepositCalc.class.getDeclaredField("ifoSummaryContractPerProductList");
            field.setAccessible(true);
            field.set(depositCalc, ifoSummaryContractPerProductList);
            int l_intReservedDate = 4;
            double l_dbReturn = depositCalc.calcSimpleSPANIfoDepositRed(l_intReservedDate);
            assertEquals(0, l_dbReturn, 0);
        }
        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage());
            fail();
        }  
        log.exiting(STR_METHOD_NAME);
    }
    
    //建玉/注文(*)が存在しない場合,簡易SPAN証拠金(n)　@=　@0
    public void testCalcSimpleSPANIfoDepositRed_C002()
    {
        String STR_METHOD_NAME = "testCalcSimpleSPANIfoDepositRed_C002";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            WEB3IfoSummaryContractPerProduct[] ifoSummaryContractPerProductList = null;
            
            Field field = WEB3IfoDepositCalc.class.getDeclaredField("ifoSummaryContractPerProductList");
            field.setAccessible(true);
            field.set(depositCalc, ifoSummaryContractPerProductList);
            int l_intReservedDate = 1;
            double l_dbReturn = depositCalc.calcSimpleSPANIfoDepositRed(l_intReservedDate);
            assertEquals(0, l_dbReturn, 0);
        }
        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage());
            fail();
        }  
        log.exiting(STR_METHOD_NAME);
    }
    
    //建玉/注文(*)が存在場合
    public void testCalcSimpleSPANIfoDepositRed_C003()
    {
        String STR_METHOD_NAME = "testCalcSimpleSPANIfoDepositRed_C003";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            WEB3IfoSummaryContractPerIndex[] l_ifoSummaryContractPerIndexLists =
                new WEB3IfoSummaryContractPerIndex[2];
            l_ifoSummaryContractPerIndexLists[0] = new WEB3IfoSummaryContractPerIndex();
            l_ifoSummaryContractPerIndexLists[0].setCurrentBizDateFuturesSellOrderQuantity(5);
            l_ifoSummaryContractPerIndexLists[0].setIfoDepositPerUnit(100);
            l_ifoSummaryContractPerIndexLists[0].setFuturesBuyContractQuantity(250);
            l_ifoSummaryContractPerIndexLists[0].setCurrentBizDateFuturesSellOrderQuantity(200);
            l_ifoSummaryContractPerIndexLists[0].setNextBizDateFuturesSellOrderQuantity(50);
            l_ifoSummaryContractPerIndexLists[0].setTargetProductCode("0120");
            l_ifoSummaryContractPerIndexLists[0].setIfoDepositPerUnitRed(10);
            
            l_ifoSummaryContractPerIndexLists[1] = new WEB3IfoSummaryContractPerIndex();
            l_ifoSummaryContractPerIndexLists[1].setCurrentBizDateFuturesSellOrderQuantity(10);
            l_ifoSummaryContractPerIndexLists[1].setIfoDepositPerUnit(100);
            l_ifoSummaryContractPerIndexLists[1].setFuturesBuyContractQuantity(300);
            l_ifoSummaryContractPerIndexLists[1].setCurrentBizDateFuturesSellOrderQuantity(100);
            l_ifoSummaryContractPerIndexLists[1].setNextBizDateFuturesSellOrderQuantity(20);
            l_ifoSummaryContractPerIndexLists[1].setTargetProductCode("0020");
            l_ifoSummaryContractPerIndexLists[1].setIfoDepositPerUnitRed(20);
            Field field = WEB3IfoDepositCalc.class.getDeclaredField("ifoSummaryContractPerIndexList");
            field.setAccessible(true);
            field.set(depositCalc, l_ifoSummaryContractPerIndexLists);

            int l_intReservedDate = 1;
            double l_dbReturn = depositCalc.calcSimpleSPANIfoDepositRed(l_intReservedDate);
            assertEquals(8500, l_dbReturn, 0);
        }
        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage());
            fail();
        }  
        log.exiting(STR_METHOD_NAME);
    }
    
    
    //指定日 = 0
    public void testCalcSimpleSPANIfoDepositRed_C004()
    {
        String STR_METHOD_NAME = "testCalcSimpleSPANIfoDepositRed_C004";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            WEB3IfoSummaryContractPerIndex[] l_ifoSummaryContractPerIndexLists =
                new WEB3IfoSummaryContractPerIndex[2];
            l_ifoSummaryContractPerIndexLists[0] = new WEB3IfoSummaryContractPerIndex();
            l_ifoSummaryContractPerIndexLists[0].setCurrentBizDateFuturesSellOrderQuantity(5);
            l_ifoSummaryContractPerIndexLists[0].setIfoDepositPerUnit(100);
            l_ifoSummaryContractPerIndexLists[0].setFuturesBuyContractQuantity(250);
            l_ifoSummaryContractPerIndexLists[0].setCurrentBizDateFuturesSellOrderQuantity(200);
            l_ifoSummaryContractPerIndexLists[0].setNextBizDateFuturesSellOrderQuantity(50);
            l_ifoSummaryContractPerIndexLists[0].setTargetProductCode("0120");
            l_ifoSummaryContractPerIndexLists[0].setIfoDepositPerUnitRed(10);
            
            l_ifoSummaryContractPerIndexLists[1] = new WEB3IfoSummaryContractPerIndex();
            l_ifoSummaryContractPerIndexLists[1].setCurrentBizDateFuturesSellOrderQuantity(10);
            l_ifoSummaryContractPerIndexLists[1].setIfoDepositPerUnit(100);
            l_ifoSummaryContractPerIndexLists[1].setFuturesBuyContractQuantity(300);
            l_ifoSummaryContractPerIndexLists[1].setCurrentBizDateFuturesSellOrderQuantity(100);
            l_ifoSummaryContractPerIndexLists[1].setNextBizDateFuturesSellOrderQuantity(20);
            l_ifoSummaryContractPerIndexLists[1].setTargetProductCode("0020");
            l_ifoSummaryContractPerIndexLists[1].setIfoDepositPerUnitRed(20);
            Field field = WEB3IfoDepositCalc.class.getDeclaredField("ifoSummaryContractPerIndexList");
            field.setAccessible(true);
            field.set(depositCalc, l_ifoSummaryContractPerIndexLists);

            int l_intReservedDate = 0;
            double l_dbReturn = depositCalc.calcSimpleSPANIfoDepositRed(l_intReservedDate);
            assertEquals(8500, l_dbReturn, 0);
        }
        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage());
            fail();
        }  
        log.exiting(STR_METHOD_NAME);
    }
    
    //指定日 = 2
    public void testCalcSimpleSPANIfoDepositRed_C005()
    {
        String STR_METHOD_NAME = "testCalcSimpleSPANIfoDepositRed_C005";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            WEB3IfoSummaryContractPerIndex[] l_ifoSummaryContractPerIndexLists =
                new WEB3IfoSummaryContractPerIndex[2];
            l_ifoSummaryContractPerIndexLists[0] = new WEB3IfoSummaryContractPerIndex();
            l_ifoSummaryContractPerIndexLists[0].setCurrentBizDateFuturesSellOrderQuantity(5);
            l_ifoSummaryContractPerIndexLists[0].setIfoDepositPerUnit(100);
            l_ifoSummaryContractPerIndexLists[0].setFuturesBuyContractQuantity(250);
            l_ifoSummaryContractPerIndexLists[0].setCurrentBizDateFuturesSellOrderQuantity(200);
            l_ifoSummaryContractPerIndexLists[0].setNextBizDateFuturesSellOrderQuantity(50);
            l_ifoSummaryContractPerIndexLists[0].setTargetProductCode("0120");
            l_ifoSummaryContractPerIndexLists[0].setIfoDepositPerUnitRed(10);
            
            l_ifoSummaryContractPerIndexLists[1] = new WEB3IfoSummaryContractPerIndex();
            l_ifoSummaryContractPerIndexLists[1].setCurrentBizDateFuturesSellOrderQuantity(10);
            l_ifoSummaryContractPerIndexLists[1].setIfoDepositPerUnit(100);
            l_ifoSummaryContractPerIndexLists[1].setFuturesBuyContractQuantity(300);
            l_ifoSummaryContractPerIndexLists[1].setCurrentBizDateFuturesSellOrderQuantity(100);
            l_ifoSummaryContractPerIndexLists[1].setNextBizDateFuturesSellOrderQuantity(20);
            l_ifoSummaryContractPerIndexLists[1].setTargetProductCode("0020");
            l_ifoSummaryContractPerIndexLists[1].setIfoDepositPerUnitRed(20);
            Field field = WEB3IfoDepositCalc.class.getDeclaredField("ifoSummaryContractPerIndexList");
            field.setAccessible(true);
            field.set(depositCalc, l_ifoSummaryContractPerIndexLists);

            int l_intReservedDate = 2;
            double l_dbReturn = depositCalc.calcSimpleSPANIfoDepositRed(l_intReservedDate);
            assertEquals(8500, l_dbReturn, 0);
        }
        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage());
            fail();
        }  
        log.exiting(STR_METHOD_NAME);
    }
    
    //指定日が範囲外の場合(nが0以上2以下でない場合)、0を返却する。
    public void testCalcBuyContractQty_C001()
    {
        String STR_METHOD_NAME = "testCalcBuyContractQty_C001";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            WEB3IfoSummaryContractPerProduct[] ifoSummaryContractPerProductList =
                new WEB3IfoSummaryContractPerProduct[2];
            ifoSummaryContractPerProductList[0] = new WEB3IfoSummaryContractPerProduct();
            ifoSummaryContractPerProductList[0].setCurrentPrice(233);
            ifoSummaryContractPerProductList[0].setUnitSize(6);
            ifoSummaryContractPerProductList[0].setBuyQuantity(1);
            ifoSummaryContractPerProductList[0].setIfoDerivativeType(IfoDerivativeTypeEnum.PUT_OPTIONS);
            ifoSummaryContractPerProductList[0].setSellQuantity(8);
            
            Field field = WEB3IfoDepositCalc.class.getDeclaredField("ifoSummaryContractPerProductList");
            field.setAccessible(true);
            field.set(depositCalc, ifoSummaryContractPerProductList);
            int l_intReservedDate = 4;
            double l_dbReturn = depositCalc.calcBuyContractQty(l_intReservedDate);
            assertEquals(0, l_dbReturn, 0);
        }
        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage());
            fail();
        }  
        log.exiting(STR_METHOD_NAME);
    }
    
    //建玉/注文(*)が存在しない場合,買ポジション建玉(n)　@=　@0
    public void testCalcBuyContractQty_C002()
    {
        String STR_METHOD_NAME = "testCalcBuyContractQty_C002";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            WEB3IfoSummaryContractPerProduct[] ifoSummaryContractPerProductList = null;
            
            Field field = WEB3IfoDepositCalc.class.getDeclaredField("ifoSummaryContractPerProductList");
            field.setAccessible(true);
            field.set(depositCalc, ifoSummaryContractPerProductList);
            int l_intReservedDate = 1;
            double l_dbReturn = depositCalc.calcBuyContractQty(l_intReservedDate);
            assertEquals(0, l_dbReturn, 0);
        }
        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage());
            fail();
        }  
        log.exiting(STR_METHOD_NAME);
    }
    
    //建玉/注文(*)が存在する場合,買ポジション建玉(n)　@=　@Sum(指数別買ポジション建玉(n))
    public void testCalcBuyContractQty_C003()
    {
        String STR_METHOD_NAME = "testCalcBuyContractQty_C003";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            WEB3IfoSummaryContractPerIndex[] l_ifoSummaryContractPerIndexLists =
                new WEB3IfoSummaryContractPerIndex[1];
            l_ifoSummaryContractPerIndexLists[0] = new WEB3IfoSummaryContractPerIndex();
            l_ifoSummaryContractPerIndexLists[0].setCurrentBizDateFuturesSellOrderQuantity(5);
            l_ifoSummaryContractPerIndexLists[0].setIfoDepositPerUnit(100);
            l_ifoSummaryContractPerIndexLists[0].setFuturesBuyContractQuantity(250);
            l_ifoSummaryContractPerIndexLists[0].setCurrentBizDateFuturesSellOrderQuantity(200);
            l_ifoSummaryContractPerIndexLists[0].setNextBizDateFuturesSellOrderQuantity(50);
            l_ifoSummaryContractPerIndexLists[0].setTargetProductCode("0120");
            l_ifoSummaryContractPerIndexLists[0].setIfoDepositPerUnitRed(10);

            Field field = WEB3IfoDepositCalc.class.getDeclaredField("ifoSummaryContractPerIndexList");
            field.setAccessible(true);
            field.set(depositCalc, l_ifoSummaryContractPerIndexLists);

            int l_intReservedDate = 1;
            double l_dbReturn = depositCalc.calcBuyContractQty(l_intReservedDate);
            assertEquals(250, l_dbReturn, 0);
        }
        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage());
            fail();
        }  
        log.exiting(STR_METHOD_NAME);
    }
    
    //建玉/注文(*)が存在する場合,買ポジション建玉(n)　@=　@Sum(指数別買ポジション建玉(n))
    public void testCalcBuyContractQty_C004()
    {
        String STR_METHOD_NAME = "testCalcBuyContractQty_C004";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            WEB3IfoSummaryContractPerIndex[] l_ifoSummaryContractPerIndexLists =
                new WEB3IfoSummaryContractPerIndex[2];
            l_ifoSummaryContractPerIndexLists[0] = new WEB3IfoSummaryContractPerIndex();
            l_ifoSummaryContractPerIndexLists[0].setCurrentBizDateFuturesSellOrderQuantity(5);
            l_ifoSummaryContractPerIndexLists[0].setIfoDepositPerUnit(100);
            l_ifoSummaryContractPerIndexLists[0].setFuturesBuyContractQuantity(250);
            l_ifoSummaryContractPerIndexLists[0].setCurrentBizDateFuturesSellOrderQuantity(200);
            l_ifoSummaryContractPerIndexLists[0].setNextBizDateFuturesSellOrderQuantity(50);
            l_ifoSummaryContractPerIndexLists[0].setTargetProductCode("0120");
            l_ifoSummaryContractPerIndexLists[0].setIfoDepositPerUnitRed(10);
            
            l_ifoSummaryContractPerIndexLists[1] = new WEB3IfoSummaryContractPerIndex();
            l_ifoSummaryContractPerIndexLists[1].setCurrentBizDateFuturesSellOrderQuantity(10);
            l_ifoSummaryContractPerIndexLists[1].setIfoDepositPerUnit(100);
            l_ifoSummaryContractPerIndexLists[1].setFuturesBuyContractQuantity(300);
            l_ifoSummaryContractPerIndexLists[1].setCurrentBizDateFuturesSellOrderQuantity(100);
            l_ifoSummaryContractPerIndexLists[1].setNextBizDateFuturesSellOrderQuantity(20);
            l_ifoSummaryContractPerIndexLists[1].setTargetProductCode("0020");
            l_ifoSummaryContractPerIndexLists[1].setIfoDepositPerUnitRed(20);
            Field field = WEB3IfoDepositCalc.class.getDeclaredField("ifoSummaryContractPerIndexList");
            field.setAccessible(true);
            field.set(depositCalc, l_ifoSummaryContractPerIndexLists);

            int l_intReservedDate = 1;
            double l_dbReturn = depositCalc.calcBuyContractQty(l_intReservedDate);
            assertEquals(550, l_dbReturn, 0);
        }
        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage());
            fail();
        }  
        log.exiting(STR_METHOD_NAME);
    }
    
    //指定日 = 0
    public void testCalcBuyContractQty_C005()
    {
        String STR_METHOD_NAME = "testCalcBuyContractQty_C005";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            WEB3IfoSummaryContractPerIndex[] l_ifoSummaryContractPerIndexLists =
                new WEB3IfoSummaryContractPerIndex[2];
            l_ifoSummaryContractPerIndexLists[0] = new WEB3IfoSummaryContractPerIndex();
            l_ifoSummaryContractPerIndexLists[0].setCurrentBizDateFuturesSellOrderQuantity(5);
            l_ifoSummaryContractPerIndexLists[0].setIfoDepositPerUnit(100);
            l_ifoSummaryContractPerIndexLists[0].setFuturesBuyContractQuantity(250);
            l_ifoSummaryContractPerIndexLists[0].setCurrentBizDateFuturesSellOrderQuantity(200);
            l_ifoSummaryContractPerIndexLists[0].setNextBizDateFuturesSellOrderQuantity(50);
            l_ifoSummaryContractPerIndexLists[0].setTargetProductCode("0120");
            l_ifoSummaryContractPerIndexLists[0].setIfoDepositPerUnitRed(10);
            
            l_ifoSummaryContractPerIndexLists[1] = new WEB3IfoSummaryContractPerIndex();
            l_ifoSummaryContractPerIndexLists[1].setCurrentBizDateFuturesSellOrderQuantity(10);
            l_ifoSummaryContractPerIndexLists[1].setIfoDepositPerUnit(100);
            l_ifoSummaryContractPerIndexLists[1].setFuturesBuyContractQuantity(300);
            l_ifoSummaryContractPerIndexLists[1].setCurrentBizDateFuturesSellOrderQuantity(100);
            l_ifoSummaryContractPerIndexLists[1].setNextBizDateFuturesSellOrderQuantity(20);
            l_ifoSummaryContractPerIndexLists[1].setTargetProductCode("0020");
            l_ifoSummaryContractPerIndexLists[1].setIfoDepositPerUnitRed(20);
            Field field = WEB3IfoDepositCalc.class.getDeclaredField("ifoSummaryContractPerIndexList");
            field.setAccessible(true);
            field.set(depositCalc, l_ifoSummaryContractPerIndexLists);

            int l_intReservedDate = 0;
            double l_dbReturn = depositCalc.calcBuyContractQty(l_intReservedDate);
            assertEquals(550, l_dbReturn, 0);
        }
        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage());
            fail();
        }  
        log.exiting(STR_METHOD_NAME);
    }
    
    //指定日 = 2
    public void testCalcBuyContractQty_C006()
    {
        String STR_METHOD_NAME = "testCalcBuyContractQty_C006";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            WEB3IfoSummaryContractPerIndex[] l_ifoSummaryContractPerIndexLists =
                new WEB3IfoSummaryContractPerIndex[2];
            l_ifoSummaryContractPerIndexLists[0] = new WEB3IfoSummaryContractPerIndex();
            l_ifoSummaryContractPerIndexLists[0].setCurrentBizDateFuturesSellOrderQuantity(5);
            l_ifoSummaryContractPerIndexLists[0].setIfoDepositPerUnit(100);
            l_ifoSummaryContractPerIndexLists[0].setFuturesBuyContractQuantity(250);
            l_ifoSummaryContractPerIndexLists[0].setCurrentBizDateFuturesSellOrderQuantity(200);
            l_ifoSummaryContractPerIndexLists[0].setNextBizDateFuturesSellOrderQuantity(50);
            l_ifoSummaryContractPerIndexLists[0].setTargetProductCode("0120");
            l_ifoSummaryContractPerIndexLists[0].setIfoDepositPerUnitRed(10);
            
            l_ifoSummaryContractPerIndexLists[1] = new WEB3IfoSummaryContractPerIndex();
            l_ifoSummaryContractPerIndexLists[1].setCurrentBizDateFuturesSellOrderQuantity(10);
            l_ifoSummaryContractPerIndexLists[1].setIfoDepositPerUnit(100);
            l_ifoSummaryContractPerIndexLists[1].setFuturesBuyContractQuantity(300);
            l_ifoSummaryContractPerIndexLists[1].setCurrentBizDateFuturesSellOrderQuantity(100);
            l_ifoSummaryContractPerIndexLists[1].setNextBizDateFuturesSellOrderQuantity(20);
            l_ifoSummaryContractPerIndexLists[1].setTargetProductCode("0020");
            l_ifoSummaryContractPerIndexLists[1].setIfoDepositPerUnitRed(20);
            Field field = WEB3IfoDepositCalc.class.getDeclaredField("ifoSummaryContractPerIndexList");
            field.setAccessible(true);
            field.set(depositCalc, l_ifoSummaryContractPerIndexLists);

            int l_intReservedDate = 2;
            double l_dbReturn = depositCalc.calcBuyContractQty(l_intReservedDate);
            assertEquals(550, l_dbReturn, 2);
        }
        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage());
            fail();
        }  
        log.exiting(STR_METHOD_NAME);
    }
    
    //指定日が範囲外の場合(nが0以上2以下でない場合)、0を返却する。
    public void testCalcSellContractQty_C001()
    {
        String STR_METHOD_NAME = "testCalcSellContractQty_C001";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            WEB3IfoSummaryContractPerProduct[] ifoSummaryContractPerProductList =
                new WEB3IfoSummaryContractPerProduct[2];
            ifoSummaryContractPerProductList[0] = new WEB3IfoSummaryContractPerProduct();
            ifoSummaryContractPerProductList[0].setCurrentPrice(233);
            ifoSummaryContractPerProductList[0].setUnitSize(6);
            ifoSummaryContractPerProductList[0].setBuyQuantity(1);
            ifoSummaryContractPerProductList[0].setIfoDerivativeType(IfoDerivativeTypeEnum.PUT_OPTIONS);
            ifoSummaryContractPerProductList[0].setSellQuantity(8);
            
            Field field = WEB3IfoDepositCalc.class.getDeclaredField("ifoSummaryContractPerProductList");
            field.setAccessible(true);
            field.set(depositCalc, ifoSummaryContractPerProductList);
            int l_intReservedDate = 4;
            double l_dbReturn = depositCalc.calcSellContractQty(l_intReservedDate);
            assertEquals(0, l_dbReturn, 0);
        }
        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage());
            fail();
        }  
        log.exiting(STR_METHOD_NAME);
    }
    
    //建玉/注文(*)が存在しない場合,買ポジション建玉(n)　@=　@0
    public void testCalcSellContractQty_C002()
    {
        String STR_METHOD_NAME = "testCalcSellContractQty_C002";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            WEB3IfoSummaryContractPerProduct[] ifoSummaryContractPerProductList = null;
            
            Field field = WEB3IfoDepositCalc.class.getDeclaredField("ifoSummaryContractPerProductList");
            field.setAccessible(true);
            field.set(depositCalc, ifoSummaryContractPerProductList);
            int l_intReservedDate = 1;
            double l_dbReturn = depositCalc.calcSellContractQty(l_intReservedDate);
            assertEquals(0, l_dbReturn, 0);
        }
        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage());
            fail();
        }  
        log.exiting(STR_METHOD_NAME);
    }
    
    //建玉/注文(*)が存在する場合,買ポジション建玉(n)　@=　@Sum(指数別買ポジション建玉(n))
    public void testCalcSellContractQty_C003()
    {
        String STR_METHOD_NAME = "testCalcSellContractQty_C003";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            WEB3IfoSummaryContractPerIndex[] l_ifoSummaryContractPerIndexLists =
                new WEB3IfoSummaryContractPerIndex[1];
            l_ifoSummaryContractPerIndexLists[0] = new WEB3IfoSummaryContractPerIndex();
            l_ifoSummaryContractPerIndexLists[0].setCurrentBizDateFuturesSellOrderQuantity(5);
            l_ifoSummaryContractPerIndexLists[0].setIfoDepositPerUnit(100);
            l_ifoSummaryContractPerIndexLists[0].setFuturesBuyContractQuantity(250);
            l_ifoSummaryContractPerIndexLists[0].setCurrentBizDateFuturesSellOrderQuantity(200);
            l_ifoSummaryContractPerIndexLists[0].setNextBizDateFuturesSellOrderQuantity(50);
            l_ifoSummaryContractPerIndexLists[0].setTargetProductCode("0120");
            l_ifoSummaryContractPerIndexLists[0].setIfoDepositPerUnitRed(10);

            Field field = WEB3IfoDepositCalc.class.getDeclaredField("ifoSummaryContractPerIndexList");
            field.setAccessible(true);
            field.set(depositCalc, l_ifoSummaryContractPerIndexLists);

            int l_intReservedDate = 1;
            double l_dbReturn = depositCalc.calcSellContractQty(l_intReservedDate);
            assertEquals(250, l_dbReturn, 0);
        }
        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage());
            fail();
        }  
        log.exiting(STR_METHOD_NAME);
    }
    
    //建玉/注文(*)が存在する場合,買ポジション建玉(n)　@=　@Sum(指数別買ポジション建玉(n))
    public void testCalcSellContractQty_C004()
    {
        String STR_METHOD_NAME = "testCalcSellContractQty_C004";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            WEB3IfoSummaryContractPerIndex[] l_ifoSummaryContractPerIndexLists =
                new WEB3IfoSummaryContractPerIndex[2];
            l_ifoSummaryContractPerIndexLists[0] = new WEB3IfoSummaryContractPerIndex();
            l_ifoSummaryContractPerIndexLists[0].setCurrentBizDateFuturesSellOrderQuantity(5);
            l_ifoSummaryContractPerIndexLists[0].setIfoDepositPerUnit(100);
            l_ifoSummaryContractPerIndexLists[0].setFuturesBuyContractQuantity(250);
            l_ifoSummaryContractPerIndexLists[0].setCurrentBizDateFuturesSellOrderQuantity(200);
            l_ifoSummaryContractPerIndexLists[0].setNextBizDateFuturesSellOrderQuantity(50);
            l_ifoSummaryContractPerIndexLists[0].setTargetProductCode("0120");
            l_ifoSummaryContractPerIndexLists[0].setIfoDepositPerUnitRed(10);
            
            l_ifoSummaryContractPerIndexLists[1] = new WEB3IfoSummaryContractPerIndex();
            l_ifoSummaryContractPerIndexLists[1].setCurrentBizDateFuturesSellOrderQuantity(10);
            l_ifoSummaryContractPerIndexLists[1].setIfoDepositPerUnit(100);
            l_ifoSummaryContractPerIndexLists[1].setFuturesBuyContractQuantity(300);
            l_ifoSummaryContractPerIndexLists[1].setCurrentBizDateFuturesSellOrderQuantity(100);
            l_ifoSummaryContractPerIndexLists[1].setNextBizDateFuturesSellOrderQuantity(20);
            l_ifoSummaryContractPerIndexLists[1].setTargetProductCode("0020");
            l_ifoSummaryContractPerIndexLists[1].setIfoDepositPerUnitRed(20);
            Field field = WEB3IfoDepositCalc.class.getDeclaredField("ifoSummaryContractPerIndexList");
            field.setAccessible(true);
            field.set(depositCalc, l_ifoSummaryContractPerIndexLists);

            int l_intReservedDate = 1;
            double l_dbReturn = depositCalc.calcSellContractQty(l_intReservedDate);
            assertEquals(370, l_dbReturn, 0);
        }
        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage());
            fail();
        }  
        log.exiting(STR_METHOD_NAME);
    }
    
    
    //指定日 = 0
    public void testCalcSellContractQty_C005()
    {
        String STR_METHOD_NAME = "testCalcSellContractQty_C005";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            WEB3IfoSummaryContractPerIndex[] l_ifoSummaryContractPerIndexLists =
                new WEB3IfoSummaryContractPerIndex[2];
            l_ifoSummaryContractPerIndexLists[0] = new WEB3IfoSummaryContractPerIndex();
            l_ifoSummaryContractPerIndexLists[0].setCurrentBizDateFuturesSellOrderQuantity(5);
            l_ifoSummaryContractPerIndexLists[0].setIfoDepositPerUnit(100);
            l_ifoSummaryContractPerIndexLists[0].setFuturesBuyContractQuantity(250);
            l_ifoSummaryContractPerIndexLists[0].setCurrentBizDateFuturesSellOrderQuantity(200);
            l_ifoSummaryContractPerIndexLists[0].setNextBizDateFuturesSellOrderQuantity(50);
            l_ifoSummaryContractPerIndexLists[0].setTargetProductCode("0120");
            l_ifoSummaryContractPerIndexLists[0].setIfoDepositPerUnitRed(10);
            
            l_ifoSummaryContractPerIndexLists[1] = new WEB3IfoSummaryContractPerIndex();
            l_ifoSummaryContractPerIndexLists[1].setCurrentBizDateFuturesSellOrderQuantity(10);
            l_ifoSummaryContractPerIndexLists[1].setIfoDepositPerUnit(100);
            l_ifoSummaryContractPerIndexLists[1].setFuturesBuyContractQuantity(300);
            l_ifoSummaryContractPerIndexLists[1].setCurrentBizDateFuturesSellOrderQuantity(100);
            l_ifoSummaryContractPerIndexLists[1].setNextBizDateFuturesSellOrderQuantity(20);
            l_ifoSummaryContractPerIndexLists[1].setTargetProductCode("0020");
            l_ifoSummaryContractPerIndexLists[1].setIfoDepositPerUnitRed(20);
            Field field = WEB3IfoDepositCalc.class.getDeclaredField("ifoSummaryContractPerIndexList");
            field.setAccessible(true);
            field.set(depositCalc, l_ifoSummaryContractPerIndexLists);

            int l_intReservedDate = 0;
            double l_dbReturn = depositCalc.calcSellContractQty(l_intReservedDate);
            assertEquals(300, l_dbReturn, 0);
        }
        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage());
            fail();
        }  
        log.exiting(STR_METHOD_NAME);
    }
    
    //指定日 = 2
    public void testCalcSellContractQty_C006()
    {
        String STR_METHOD_NAME = "testCalcSellContractQty_C006";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            WEB3IfoSummaryContractPerIndex[] l_ifoSummaryContractPerIndexLists =
                new WEB3IfoSummaryContractPerIndex[2];
            l_ifoSummaryContractPerIndexLists[0] = new WEB3IfoSummaryContractPerIndex();
            l_ifoSummaryContractPerIndexLists[0].setCurrentBizDateFuturesSellOrderQuantity(5);
            l_ifoSummaryContractPerIndexLists[0].setIfoDepositPerUnit(100);
            l_ifoSummaryContractPerIndexLists[0].setFuturesBuyContractQuantity(250);
            l_ifoSummaryContractPerIndexLists[0].setCurrentBizDateFuturesSellOrderQuantity(200);
            l_ifoSummaryContractPerIndexLists[0].setNextBizDateFuturesSellOrderQuantity(50);
            l_ifoSummaryContractPerIndexLists[0].setTargetProductCode("0120");
            l_ifoSummaryContractPerIndexLists[0].setIfoDepositPerUnitRed(10);
            
            l_ifoSummaryContractPerIndexLists[1] = new WEB3IfoSummaryContractPerIndex();
            l_ifoSummaryContractPerIndexLists[1].setCurrentBizDateFuturesSellOrderQuantity(10);
            l_ifoSummaryContractPerIndexLists[1].setIfoDepositPerUnit(100);
            l_ifoSummaryContractPerIndexLists[1].setFuturesBuyContractQuantity(300);
            l_ifoSummaryContractPerIndexLists[1].setCurrentBizDateFuturesSellOrderQuantity(100);
            l_ifoSummaryContractPerIndexLists[1].setNextBizDateFuturesSellOrderQuantity(20);
            l_ifoSummaryContractPerIndexLists[1].setTargetProductCode("0020");
            l_ifoSummaryContractPerIndexLists[1].setIfoDepositPerUnitRed(20);
            Field field = WEB3IfoDepositCalc.class.getDeclaredField("ifoSummaryContractPerIndexList");
            field.setAccessible(true);
            field.set(depositCalc, l_ifoSummaryContractPerIndexLists);

            int l_intReservedDate = 2;
            double l_dbReturn = depositCalc.calcSellContractQty(l_intReservedDate);
            assertEquals(370, l_dbReturn, 0);
        }
        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage());
            fail();
        }  
        log.exiting(STR_METHOD_NAME);
    }
    
    //指定日が範囲外の場合(nが0以上2以下でない場合)、0を返却する。
    public void testCalcBuyOrderQty_C001()
    {
        String STR_METHOD_NAME = "testCalcSellContractQty_C001";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            WEB3IfoSummaryContractPerProduct[] ifoSummaryContractPerProductList =
                new WEB3IfoSummaryContractPerProduct[2];
            ifoSummaryContractPerProductList[0] = new WEB3IfoSummaryContractPerProduct();
            ifoSummaryContractPerProductList[0].setCurrentPrice(233);
            ifoSummaryContractPerProductList[0].setUnitSize(6);
            ifoSummaryContractPerProductList[0].setBuyQuantity(1);
            ifoSummaryContractPerProductList[0].setIfoDerivativeType(IfoDerivativeTypeEnum.PUT_OPTIONS);
            ifoSummaryContractPerProductList[0].setSellQuantity(8);
            
            Field field = WEB3IfoDepositCalc.class.getDeclaredField("ifoSummaryContractPerProductList");
            field.setAccessible(true);
            field.set(depositCalc, ifoSummaryContractPerProductList);
            int l_intReservedDate = 4;
            double l_dbReturn = depositCalc.calcBuyOrderQty(l_intReservedDate);
            assertEquals(0, l_dbReturn, 0);
        }
        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage());
            fail();
        }  
        log.exiting(STR_METHOD_NAME);
    }
    
    //建玉/注文(*)が存在しない場合
    //注文中買ポジション建玉(n)　@=　@0
    public void testCalcBuyOrderQty_C002()
    {
        String STR_METHOD_NAME = "testCalcBuyOrderQty_C002";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            WEB3IfoSummaryContractPerProduct[] ifoSummaryContractPerProductList = null;
            
            Field field = WEB3IfoDepositCalc.class.getDeclaredField("ifoSummaryContractPerProductList");
            field.setAccessible(true);
            field.set(depositCalc, ifoSummaryContractPerProductList);
            int l_intReservedDate = 1;
            double l_dbReturn = depositCalc.calcBuyOrderQty(l_intReservedDate);
            assertEquals(0, l_dbReturn, 0);
        }
        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage());
            fail();
        }  
        log.exiting(STR_METHOD_NAME);
    }
    
    //(建玉/注文(*)が存在する場合、証拠金計算.指数別先物OP建玉集計一覧要素ごとのLoop処理を行い、総和を求める)
    public void testCalcBuyOrderQty_C003()
    {
        String STR_METHOD_NAME = "testCalcBuyOrderQty_C003";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            WEB3IfoSummaryContractPerIndex[] l_ifoSummaryContractPerIndexLists =
                new WEB3IfoSummaryContractPerIndex[1];
            l_ifoSummaryContractPerIndexLists[0] = new WEB3IfoSummaryContractPerIndex();
            l_ifoSummaryContractPerIndexLists[0].setCurrentBizDateFuturesSellOrderQuantity(5);
            l_ifoSummaryContractPerIndexLists[0].setIfoDepositPerUnit(100);
            l_ifoSummaryContractPerIndexLists[0].setFuturesBuyContractQuantity(250);
            l_ifoSummaryContractPerIndexLists[0].setCurrentBizDateFuturesSellOrderQuantity(200);
            l_ifoSummaryContractPerIndexLists[0].setNextBizDateFuturesSellOrderQuantity(50);
            l_ifoSummaryContractPerIndexLists[0].setTargetProductCode("0120");
            l_ifoSummaryContractPerIndexLists[0].setIfoDepositPerUnitRed(10);
            l_ifoSummaryContractPerIndexLists[0].setCurrentBizDateFuturesBuyOrderQuantity(50);
            l_ifoSummaryContractPerIndexLists[0].setNextBizDateFuturesBuyOrderQuantity(100);

            Field field = WEB3IfoDepositCalc.class.getDeclaredField("ifoSummaryContractPerIndexList");
            field.setAccessible(true);
            field.set(depositCalc, l_ifoSummaryContractPerIndexLists);

            int l_intReservedDate = 1;
            double l_dbReturn = depositCalc.calcBuyOrderQty(l_intReservedDate);
            assertEquals(150, l_dbReturn, 0);
        }
        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage());
            fail();
        }  
        log.exiting(STR_METHOD_NAME);
    }
    
    //(建玉/注文(*)が存在する場合、証拠金計算.指数別先物OP建玉集計一覧要素ごとのLoop処理を行い、総和を求める)
    public void testCalcBuyOrderQty_C004()
    {
        String STR_METHOD_NAME = "testCalcBuyOrderQty_C004";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            WEB3IfoSummaryContractPerIndex[] l_ifoSummaryContractPerIndexLists =
                new WEB3IfoSummaryContractPerIndex[2];
            l_ifoSummaryContractPerIndexLists[0] = new WEB3IfoSummaryContractPerIndex();
            l_ifoSummaryContractPerIndexLists[0].setCurrentBizDateFuturesSellOrderQuantity(5);
            l_ifoSummaryContractPerIndexLists[0].setIfoDepositPerUnit(100);
            l_ifoSummaryContractPerIndexLists[0].setFuturesBuyContractQuantity(250);
            l_ifoSummaryContractPerIndexLists[0].setCurrentBizDateFuturesSellOrderQuantity(200);
            l_ifoSummaryContractPerIndexLists[0].setNextBizDateFuturesSellOrderQuantity(50);
            l_ifoSummaryContractPerIndexLists[0].setTargetProductCode("0120");
            l_ifoSummaryContractPerIndexLists[0].setIfoDepositPerUnitRed(10);
            l_ifoSummaryContractPerIndexLists[0].setCurrentBizDateFuturesBuyOrderQuantity(50);
            l_ifoSummaryContractPerIndexLists[0].setNextBizDateFuturesBuyOrderQuantity(100);
            
            l_ifoSummaryContractPerIndexLists[1] = new WEB3IfoSummaryContractPerIndex();
            l_ifoSummaryContractPerIndexLists[1].setCurrentBizDateFuturesSellOrderQuantity(10);
            l_ifoSummaryContractPerIndexLists[1].setIfoDepositPerUnit(100);
            l_ifoSummaryContractPerIndexLists[1].setFuturesBuyContractQuantity(300);
            l_ifoSummaryContractPerIndexLists[1].setCurrentBizDateFuturesSellOrderQuantity(100);
            l_ifoSummaryContractPerIndexLists[1].setNextBizDateFuturesSellOrderQuantity(20);
            l_ifoSummaryContractPerIndexLists[1].setTargetProductCode("0020");
            l_ifoSummaryContractPerIndexLists[1].setIfoDepositPerUnitRed(20);
            l_ifoSummaryContractPerIndexLists[1].setCurrentBizDateFuturesBuyOrderQuantity(150);
            l_ifoSummaryContractPerIndexLists[1].setNextBizDateFuturesBuyOrderQuantity(22);
            
            Field field = WEB3IfoDepositCalc.class.getDeclaredField("ifoSummaryContractPerIndexList");
            field.setAccessible(true);
            field.set(depositCalc, l_ifoSummaryContractPerIndexLists);

            int l_intReservedDate = 1;
            double l_dbReturn = depositCalc.calcBuyOrderQty(l_intReservedDate);
            assertEquals(322, l_dbReturn, 0);
        }
        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage());
            fail();
        }  
        log.exiting(STR_METHOD_NAME);
    }
    
    //指定日 = 0
    public void testCalcBuyOrderQty_C005()
    {
        String STR_METHOD_NAME = "testCalcBuyOrderQty_C005";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            WEB3IfoSummaryContractPerIndex[] l_ifoSummaryContractPerIndexLists =
                new WEB3IfoSummaryContractPerIndex[2];
            l_ifoSummaryContractPerIndexLists[0] = new WEB3IfoSummaryContractPerIndex();
            l_ifoSummaryContractPerIndexLists[0].setCurrentBizDateFuturesSellOrderQuantity(5);
            l_ifoSummaryContractPerIndexLists[0].setIfoDepositPerUnit(100);
            l_ifoSummaryContractPerIndexLists[0].setFuturesBuyContractQuantity(250);
            l_ifoSummaryContractPerIndexLists[0].setCurrentBizDateFuturesSellOrderQuantity(200);
            l_ifoSummaryContractPerIndexLists[0].setNextBizDateFuturesSellOrderQuantity(50);
            l_ifoSummaryContractPerIndexLists[0].setTargetProductCode("0120");
            l_ifoSummaryContractPerIndexLists[0].setIfoDepositPerUnitRed(10);
            l_ifoSummaryContractPerIndexLists[0].setCurrentBizDateFuturesBuyOrderQuantity(50);
            l_ifoSummaryContractPerIndexLists[0].setNextBizDateFuturesBuyOrderQuantity(100);
            
            l_ifoSummaryContractPerIndexLists[1] = new WEB3IfoSummaryContractPerIndex();
            l_ifoSummaryContractPerIndexLists[1].setCurrentBizDateFuturesSellOrderQuantity(10);
            l_ifoSummaryContractPerIndexLists[1].setIfoDepositPerUnit(100);
            l_ifoSummaryContractPerIndexLists[1].setFuturesBuyContractQuantity(300);
            l_ifoSummaryContractPerIndexLists[1].setCurrentBizDateFuturesSellOrderQuantity(100);
            l_ifoSummaryContractPerIndexLists[1].setNextBizDateFuturesSellOrderQuantity(20);
            l_ifoSummaryContractPerIndexLists[1].setTargetProductCode("0020");
            l_ifoSummaryContractPerIndexLists[1].setIfoDepositPerUnitRed(20);
            l_ifoSummaryContractPerIndexLists[1].setCurrentBizDateFuturesBuyOrderQuantity(150);
            l_ifoSummaryContractPerIndexLists[1].setNextBizDateFuturesBuyOrderQuantity(22);
            
            Field field = WEB3IfoDepositCalc.class.getDeclaredField("ifoSummaryContractPerIndexList");
            field.setAccessible(true);
            field.set(depositCalc, l_ifoSummaryContractPerIndexLists);

            int l_intReservedDate = 0;
            double l_dbReturn = depositCalc.calcBuyOrderQty(l_intReservedDate);
            assertEquals(200, l_dbReturn, 0);
        }
        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage());
            fail();
        }  
        log.exiting(STR_METHOD_NAME);
    }
    
    //指定日 = 2
    public void testCalcBuyOrderQty_C006()
    {
        String STR_METHOD_NAME = "testCalcBuyOrderQty_C006";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            WEB3IfoSummaryContractPerIndex[] l_ifoSummaryContractPerIndexLists =
                new WEB3IfoSummaryContractPerIndex[2];
            l_ifoSummaryContractPerIndexLists[0] = new WEB3IfoSummaryContractPerIndex();
            l_ifoSummaryContractPerIndexLists[0].setCurrentBizDateFuturesSellOrderQuantity(5);
            l_ifoSummaryContractPerIndexLists[0].setIfoDepositPerUnit(100);
            l_ifoSummaryContractPerIndexLists[0].setFuturesBuyContractQuantity(250);
            l_ifoSummaryContractPerIndexLists[0].setCurrentBizDateFuturesSellOrderQuantity(200);
            l_ifoSummaryContractPerIndexLists[0].setNextBizDateFuturesSellOrderQuantity(50);
            l_ifoSummaryContractPerIndexLists[0].setTargetProductCode("0120");
            l_ifoSummaryContractPerIndexLists[0].setIfoDepositPerUnitRed(10);
            l_ifoSummaryContractPerIndexLists[0].setCurrentBizDateFuturesBuyOrderQuantity(50);
            l_ifoSummaryContractPerIndexLists[0].setNextBizDateFuturesBuyOrderQuantity(100);
            
            l_ifoSummaryContractPerIndexLists[1] = new WEB3IfoSummaryContractPerIndex();
            l_ifoSummaryContractPerIndexLists[1].setCurrentBizDateFuturesSellOrderQuantity(10);
            l_ifoSummaryContractPerIndexLists[1].setIfoDepositPerUnit(100);
            l_ifoSummaryContractPerIndexLists[1].setFuturesBuyContractQuantity(300);
            l_ifoSummaryContractPerIndexLists[1].setCurrentBizDateFuturesSellOrderQuantity(100);
            l_ifoSummaryContractPerIndexLists[1].setNextBizDateFuturesSellOrderQuantity(20);
            l_ifoSummaryContractPerIndexLists[1].setTargetProductCode("0020");
            l_ifoSummaryContractPerIndexLists[1].setIfoDepositPerUnitRed(20);
            l_ifoSummaryContractPerIndexLists[1].setCurrentBizDateFuturesBuyOrderQuantity(150);
            l_ifoSummaryContractPerIndexLists[1].setNextBizDateFuturesBuyOrderQuantity(22);
            
            Field field = WEB3IfoDepositCalc.class.getDeclaredField("ifoSummaryContractPerIndexList");
            field.setAccessible(true);
            field.set(depositCalc, l_ifoSummaryContractPerIndexLists);

            int l_intReservedDate = 2;
            double l_dbReturn = depositCalc.calcBuyOrderQty(l_intReservedDate);
            assertEquals(322, l_dbReturn, 0);
        }
        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage());
            fail();
        }  
        log.exiting(STR_METHOD_NAME);
    }
    
    //指定日が範囲外の場合(nが0以上2以下でない場合)、0を返却する。
    public void testCalcSellOrderQty_C001()
    {
        String STR_METHOD_NAME = "testCalcSellOrderQty_C001";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            WEB3IfoSummaryContractPerProduct[] ifoSummaryContractPerProductList =
                new WEB3IfoSummaryContractPerProduct[2];
            ifoSummaryContractPerProductList[0] = new WEB3IfoSummaryContractPerProduct();
            ifoSummaryContractPerProductList[0].setCurrentPrice(233);
            ifoSummaryContractPerProductList[0].setUnitSize(6);
            ifoSummaryContractPerProductList[0].setBuyQuantity(1);
            ifoSummaryContractPerProductList[0].setIfoDerivativeType(IfoDerivativeTypeEnum.PUT_OPTIONS);
            ifoSummaryContractPerProductList[0].setSellQuantity(8);
            
            Field field = WEB3IfoDepositCalc.class.getDeclaredField("ifoSummaryContractPerProductList");
            field.setAccessible(true);
            field.set(depositCalc, ifoSummaryContractPerProductList);
            int l_intReservedDate = 4;
            double l_dbReturn = depositCalc.calcSellOrderQty(l_intReservedDate);
            assertEquals(0, l_dbReturn, 0);
        }
        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage());
            fail();
        }  
        log.exiting(STR_METHOD_NAME);
    }
    
    //建玉/注文(*)が存在しない場合
    //注文中売ポジション建玉(n)　@=　@0
    public void testCalcSellOrderQty_C002()
    {
        String STR_METHOD_NAME = "testCalcSellOrderQty_C002";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            WEB3IfoSummaryContractPerProduct[] ifoSummaryContractPerProductList = null;
            
            Field field = WEB3IfoDepositCalc.class.getDeclaredField("ifoSummaryContractPerProductList");
            field.setAccessible(true);
            field.set(depositCalc, ifoSummaryContractPerProductList);
            int l_intReservedDate = 1;
            double l_dbReturn = depositCalc.calcSellOrderQty(l_intReservedDate);
            assertEquals(0, l_dbReturn, 0);
        }
        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage());
            fail();
        }  
        log.exiting(STR_METHOD_NAME);
    }
    
    //(建玉/注文(*)が存在する場合、証拠金計算.指数別先物OP建玉集計一覧要素ごとのLoop処理を行い、総和を求める)
    public void testCalcSellOrderQty_C003()
    {
        String STR_METHOD_NAME = "testCalcSellOrderQty_C003";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            WEB3IfoSummaryContractPerIndex[] l_ifoSummaryContractPerIndexLists =
                new WEB3IfoSummaryContractPerIndex[1];
            l_ifoSummaryContractPerIndexLists[0] = new WEB3IfoSummaryContractPerIndex();
            l_ifoSummaryContractPerIndexLists[0].setCurrentBizDateFuturesSellOrderQuantity(5);
            l_ifoSummaryContractPerIndexLists[0].setIfoDepositPerUnit(100);
            l_ifoSummaryContractPerIndexLists[0].setFuturesBuyContractQuantity(250);
            l_ifoSummaryContractPerIndexLists[0].setCurrentBizDateFuturesSellOrderQuantity(200);
            l_ifoSummaryContractPerIndexLists[0].setNextBizDateFuturesSellOrderQuantity(50);
            l_ifoSummaryContractPerIndexLists[0].setTargetProductCode("0120");
            l_ifoSummaryContractPerIndexLists[0].setIfoDepositPerUnitRed(10);
            l_ifoSummaryContractPerIndexLists[0].setCurrentBizDateFuturesSellOrderQuantity(50);
            l_ifoSummaryContractPerIndexLists[0].setNextBizDateFuturesSellOrderQuantity(100);

            Field field = WEB3IfoDepositCalc.class.getDeclaredField("ifoSummaryContractPerIndexList");
            field.setAccessible(true);
            field.set(depositCalc, l_ifoSummaryContractPerIndexLists);

            int l_intReservedDate = 1;
            double l_dbReturn = depositCalc.calcSellOrderQty(l_intReservedDate);
            assertEquals(150, l_dbReturn, 0);
        }
        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage());
            fail();
        }  
        log.exiting(STR_METHOD_NAME);
    }
    
    //(建玉/注文(*)が存在する場合、証拠金計算.指数別先物OP建玉集計一覧要素ごとのLoop処理を行い、総和を求める)
    public void testCalcSellOrderQty_C004()
    {
        String STR_METHOD_NAME = "testCalcSellOrderQty_C004";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            WEB3IfoSummaryContractPerIndex[] l_ifoSummaryContractPerIndexLists =
                new WEB3IfoSummaryContractPerIndex[2];
            l_ifoSummaryContractPerIndexLists[0] = new WEB3IfoSummaryContractPerIndex();
            l_ifoSummaryContractPerIndexLists[0].setCurrentBizDateFuturesSellOrderQuantity(5);
            l_ifoSummaryContractPerIndexLists[0].setIfoDepositPerUnit(100);
            l_ifoSummaryContractPerIndexLists[0].setFuturesBuyContractQuantity(250);
            l_ifoSummaryContractPerIndexLists[0].setCurrentBizDateFuturesSellOrderQuantity(200);
            l_ifoSummaryContractPerIndexLists[0].setNextBizDateFuturesSellOrderQuantity(50);
            l_ifoSummaryContractPerIndexLists[0].setTargetProductCode("0120");
            l_ifoSummaryContractPerIndexLists[0].setIfoDepositPerUnitRed(10);
            l_ifoSummaryContractPerIndexLists[0].setCurrentBizDateFuturesSellOrderQuantity(50);
            l_ifoSummaryContractPerIndexLists[0].setNextBizDateFuturesSellOrderQuantity(100);
            
            l_ifoSummaryContractPerIndexLists[1] = new WEB3IfoSummaryContractPerIndex();
            l_ifoSummaryContractPerIndexLists[1].setCurrentBizDateFuturesSellOrderQuantity(10);
            l_ifoSummaryContractPerIndexLists[1].setIfoDepositPerUnit(100);
            l_ifoSummaryContractPerIndexLists[1].setFuturesBuyContractQuantity(300);
            l_ifoSummaryContractPerIndexLists[1].setCurrentBizDateFuturesSellOrderQuantity(100);
            l_ifoSummaryContractPerIndexLists[1].setNextBizDateFuturesSellOrderQuantity(20);
            l_ifoSummaryContractPerIndexLists[1].setTargetProductCode("0020");
            l_ifoSummaryContractPerIndexLists[1].setIfoDepositPerUnitRed(20);
            l_ifoSummaryContractPerIndexLists[1].setCurrentBizDateFuturesSellOrderQuantity(150);
            l_ifoSummaryContractPerIndexLists[1].setNextBizDateFuturesSellOrderQuantity(22);
            
            Field field = WEB3IfoDepositCalc.class.getDeclaredField("ifoSummaryContractPerIndexList");
            field.setAccessible(true);
            field.set(depositCalc, l_ifoSummaryContractPerIndexLists);

            int l_intReservedDate = 1;
            double l_dbReturn = depositCalc.calcSellOrderQty(l_intReservedDate);
            assertEquals(322, l_dbReturn, 0);
        }
        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage());
            fail();
        }  
        log.exiting(STR_METHOD_NAME);
    }
    
    //指定日が0，正確的証拠金残高<証拠金不足仮確定>返回
    public void testcalcIfoDepositBalanceTemp_C001()
    {
        String STR_METHOD_NAME = "testcalcIfoDepositBalanceTemp_C001";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            WEB3IfoCustomerTransfer ifoCustomerTransfer = new WEB3IfoCustomerTransfer();
            ifoCustomerTransfer.setCurrentBizDateTransferAmount(1);
            ifoCustomerTransfer.setNextBizDateTransferAmount(2);
            double[] l_balances = {100, 200, 300};
            ifoCustomerTransfer.setBalances(l_balances);
            
            Field field = WEB3IfoDepositCalc.class.getDeclaredField("ifoCustomerTransfer");
            field.setAccessible(true);
            field.set(depositCalc, ifoCustomerTransfer);
            
            int  l_intReservedDate = 0;
            double l_dbReturn = depositCalc.calcIfoDepositBalanceTemp(l_intReservedDate);
            assertEquals(101, l_dbReturn, 0);
        }
        catch(Exception  l_ex)
        {
            log.error(l_ex.getMessage());
            fail();
            log.exiting(STR_METHOD_NAME);
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    //指定日が1，正確的証拠金残高<証拠金不足仮確定>返回
    public void testcalcIfoDepositBalanceTemp_C002()
    {
        String STR_METHOD_NAME = "testcalcIfoDepositBalanceTemp_C002";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            WEB3IfoCustomerTransfer ifoCustomerTransfer = new WEB3IfoCustomerTransfer();
            ifoCustomerTransfer.setCurrentBizDateTransferAmount(1);
            ifoCustomerTransfer.setNextBizDateTransferAmount(2);
            double[] l_balances = {100, 200, 300};
            ifoCustomerTransfer.setBalances(l_balances);
            
            Field field = WEB3IfoDepositCalc.class.getDeclaredField("ifoCustomerTransfer");
            field.setAccessible(true);
            field.set(depositCalc, ifoCustomerTransfer);
            
            int  l_intReservedDate = 1;
            double l_dbReturn = depositCalc.calcIfoDepositBalanceTemp(l_intReservedDate);
            assertEquals(201, l_dbReturn, 0);
        }
        catch(Exception  l_ex)
        {
            log.error(l_ex.getMessage());
            fail();
            log.exiting(STR_METHOD_NAME);
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    //指定日が2，正確的証拠金残高<証拠金不足仮確定>返回
    public void testcalcIfoDepositBalanceTemp_C003()
    {
        String STR_METHOD_NAME = "testcalcIfoDepositBalanceTemp_C003";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            WEB3IfoCustomerTransfer ifoCustomerTransfer = new WEB3IfoCustomerTransfer();
            ifoCustomerTransfer.setCurrentBizDateTransferAmount(1);
            ifoCustomerTransfer.setNextBizDateTransferAmount(2);
            double[] l_balances = {100, 200, 300};
            ifoCustomerTransfer.setBalances(l_balances);
            
            Field field = WEB3IfoDepositCalc.class.getDeclaredField("ifoCustomerTransfer");
            field.setAccessible(true);
            field.set(depositCalc, ifoCustomerTransfer);
            
            int  l_intReservedDate = 2;
            double l_dbReturn = depositCalc.calcIfoDepositBalanceTemp(l_intReservedDate);
            assertEquals(301, l_dbReturn, 0);
        }
        catch(Exception  l_ex)
        {
            log.error(l_ex.getMessage());
            fail();
            log.exiting(STR_METHOD_NAME);
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    //指定日が4，正確的証拠金残高<証拠金不足仮確定>返回
    public void testcalcIfoDepositBalanceTemp_C004()
    {
        String STR_METHOD_NAME = "testcalcIfoDepositBalanceTemp_C004";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            WEB3IfoCustomerTransfer ifoCustomerTransfer = new WEB3IfoCustomerTransfer();
            ifoCustomerTransfer.setCurrentBizDateTransferAmount(1);
            ifoCustomerTransfer.setNextBizDateTransferAmount(2);
            double[] l_balances = {100, 200, 300};
            ifoCustomerTransfer.setBalances(l_balances);
            
            Field field = WEB3IfoDepositCalc.class.getDeclaredField("ifoCustomerTransfer");
            field.setAccessible(true);
            field.set(depositCalc, ifoCustomerTransfer);
            
            int  l_intReservedDate = 3;
            double l_dbReturn = depositCalc.calcIfoDepositBalanceTemp(l_intReservedDate);
            assertEquals(0, l_dbReturn, 0);
        }
        catch(Exception  l_ex)
        {
            log.error(l_ex.getMessage());
            fail();
            log.exiting(STR_METHOD_NAME);
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    /*
     * 指定日が範囲外の場合(nが0以上2以下でない場合)
     * 指定日 = -1
     */
    public void testCalcBuyContractAmt_C0001()
    {
        final String STR_METHOD_NAME = "testCalcBuyContractAmt_C0001()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            int l_intReservedDate = -1;

            double l_dblContractAmt = depositCalc.calcBuyContractAmt(l_intReservedDate);

            assertTrue(GtlUtils.Double.isZero(l_dblContractAmt));
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    /*
     * 証拠金計算.指数別先物OP建玉集計一覧 == null の場合
     * (建玉/注文(*)が存在しない場合)
     */
    public void testCalcBuyContractAmt_C0002()
    {
        final String STR_METHOD_NAME = "testCalcBuyContractAmt_C0002()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            int l_intReservedDate = 1;

            double l_dblContractAmt = depositCalc.calcBuyContractAmt(l_intReservedDate);

            assertTrue(GtlUtils.Double.isZero(l_dblContractAmt));
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    /*
     * 以外の場合
     * (建玉/注文(*)が存在する場合、証拠金計算.指数別先物OP建玉集計一覧要素ごとのLoop処理を行い、総和を求める)
     */
    public void testCalcBuyContractAmt_C0003()
    {
        final String STR_METHOD_NAME = "testCalcBuyContractAmt_C0003()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            int l_intReservedDate = 0;

            WEB3IfoSummaryContractPerIndex ifoSummaryContractPerIndexList[] = new WEB3IfoSummaryContractPerIndex[3];
            for (int i = 0; i < 3; i++)
            {
                ifoSummaryContractPerIndexList[i] = new WEB3IfoSummaryContractPerIndex();
                ifoSummaryContractPerIndexList[i].setFuturesBuyContractQuantity(1);
                ifoSummaryContractPerIndexList[i].setCurrentBizDateFuturesBuyOrderQuantity(2);
                ifoSummaryContractPerIndexList[i].setNextBizDateFuturesBuyOrderQuantity(3);
                ifoSummaryContractPerIndexList[i].setOptionPutSellContractQuantity(4);
                ifoSummaryContractPerIndexList[i].setCurrentBizDateOptionPutSellOrderQuantity(5);
                ifoSummaryContractPerIndexList[i].setNextBizDateOptionPutSellOrderQuantity(6);
                ifoSummaryContractPerIndexList[i].setIfoDepositPerUnit(7);
            }

            Field l_field = WEB3IfoDepositCalc.class.getDeclaredField("ifoSummaryContractPerIndexList");
            l_field.setAccessible(true);
            l_field.set(depositCalc, ifoSummaryContractPerIndexList);

            double l_dblContractAmt = depositCalc.calcBuyContractAmt(l_intReservedDate);

            assertTrue(GtlUtils.Double.isEqual(252, l_dblContractAmt));
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    /*
     * 以外の場合
     * (建玉/注文(*)が存在する場合、証拠金計算.指数別先物OP建玉集計一覧要素ごとのLoop処理を行い、総和を求める)
     */
    public void testCalcBuyContractAmt_C0004()
    {
        final String STR_METHOD_NAME = "testCalcBuyContractAmt_C0004()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            int l_intReservedDate = 2;

            WEB3IfoSummaryContractPerIndex ifoSummaryContractPerIndexList[] = new WEB3IfoSummaryContractPerIndex[3];
            for (int i = 0; i < 3; i++)
            {
                ifoSummaryContractPerIndexList[i] = new WEB3IfoSummaryContractPerIndex();
                ifoSummaryContractPerIndexList[i].setFuturesBuyContractQuantity(1);
                ifoSummaryContractPerIndexList[i].setCurrentBizDateFuturesBuyOrderQuantity(2);
                ifoSummaryContractPerIndexList[i].setNextBizDateFuturesBuyOrderQuantity(3);
                ifoSummaryContractPerIndexList[i].setOptionPutSellContractQuantity(4);
                ifoSummaryContractPerIndexList[i].setCurrentBizDateOptionPutSellOrderQuantity(5);
                ifoSummaryContractPerIndexList[i].setNextBizDateOptionPutSellOrderQuantity(6);
                ifoSummaryContractPerIndexList[i].setIfoDepositPerUnit(7);
            }

            Field l_field = WEB3IfoDepositCalc.class.getDeclaredField("ifoSummaryContractPerIndexList");
            l_field.setAccessible(true);
            l_field.set(depositCalc, ifoSummaryContractPerIndexList);

            double l_dblContractAmt = depositCalc.calcBuyContractAmt(l_intReservedDate);

            assertTrue(GtlUtils.Double.isEqual(441, l_dblContractAmt));
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    /*
     * 指定日が範囲外の場合(nが0以上2以下でない場合)
     * 指定日 = -1
     */
    public void testCalcSellContractAmt_C0001()
    {
        final String STR_METHOD_NAME = "testCalcSellContractAmt_C0001()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            int l_intReservedDate = -1;

            double l_dblContractAmt = depositCalc.calcSellContractAmt(l_intReservedDate);

            assertTrue(GtlUtils.Double.isZero(l_dblContractAmt));
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    /*
     * 証拠金計算.指数別先物OP建玉集計一覧 == null の場合
     * (建玉/注文(*)が存在しない場合)
     */
    public void testCalcSellContractAmt_C0002()
    {
        final String STR_METHOD_NAME = "testCalcSellContractAmt_C0002()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            int l_intReservedDate = 1;

            double l_dblContractAmt = depositCalc.calcSellContractAmt(l_intReservedDate);

            assertTrue(GtlUtils.Double.isZero(l_dblContractAmt));
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    /*
     * 以外の場合
     * (建玉/注文(*)が存在する場合、証拠金計算.指数別先物OP建玉集計一覧要素ごとのLoop処理を行い、総和を求める)
     */
    public void testCalcSellContractAmt_C0003()
    {
        final String STR_METHOD_NAME = "testCalcSellContractAmt_C0003()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            int l_intReservedDate = 0;

            WEB3IfoSummaryContractPerIndex ifoSummaryContractPerIndexList[] = new WEB3IfoSummaryContractPerIndex[3];
            for (int i = 0; i < 3; i++)
            {
                ifoSummaryContractPerIndexList[i] = new WEB3IfoSummaryContractPerIndex();
                ifoSummaryContractPerIndexList[i].setFuturesSellContractQuantity(1);
                ifoSummaryContractPerIndexList[i].setCurrentBizDateFuturesSellOrderQuantity(2);
                ifoSummaryContractPerIndexList[i].setNextBizDateFuturesSellOrderQuantity(3);
                ifoSummaryContractPerIndexList[i].setOptionCallSellContractQuantity(4);
                ifoSummaryContractPerIndexList[i].setCurrentBizDateOptionCallSellOrderQuantity(5);
                ifoSummaryContractPerIndexList[i].setNextBizDateOptionCallSellOrderQuantity(6);
                ifoSummaryContractPerIndexList[i].setIfoDepositPerUnit(7);
            }

            Field l_field = WEB3IfoDepositCalc.class.getDeclaredField("ifoSummaryContractPerIndexList");
            l_field.setAccessible(true);
            l_field.set(depositCalc, ifoSummaryContractPerIndexList);

            double l_dblContractAmt = depositCalc.calcSellContractAmt(l_intReservedDate);

            assertTrue(GtlUtils.Double.isEqual(252, l_dblContractAmt));
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    /*
     * 以外の場合
     * (建玉/注文(*)が存在する場合、証拠金計算.指数別先物OP建玉集計一覧要素ごとのLoop処理を行い、総和を求める)
     */
    public void testCalcSellContractAmt_C0004()
    {
        final String STR_METHOD_NAME = "testCalcSellContractAmt_C0004()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            int l_intReservedDate = 2;

            WEB3IfoSummaryContractPerIndex ifoSummaryContractPerIndexList[] = new WEB3IfoSummaryContractPerIndex[3];
            for (int i = 0; i < 3; i++)
            {
                ifoSummaryContractPerIndexList[i] = new WEB3IfoSummaryContractPerIndex();
                ifoSummaryContractPerIndexList[i].setFuturesSellContractQuantity(1);
                ifoSummaryContractPerIndexList[i].setCurrentBizDateFuturesSellOrderQuantity(2);
                ifoSummaryContractPerIndexList[i].setNextBizDateFuturesSellOrderQuantity(3);
                ifoSummaryContractPerIndexList[i].setOptionCallSellContractQuantity(4);
                ifoSummaryContractPerIndexList[i].setCurrentBizDateOptionCallSellOrderQuantity(5);
                ifoSummaryContractPerIndexList[i].setNextBizDateOptionCallSellOrderQuantity(6);
                ifoSummaryContractPerIndexList[i].setIfoDepositPerUnit(7);
            }

            Field l_field = WEB3IfoDepositCalc.class.getDeclaredField("ifoSummaryContractPerIndexList");
            l_field.setAccessible(true);
            l_field.set(depositCalc, ifoSummaryContractPerIndexList);

            double l_dblContractAmt = depositCalc.calcSellContractAmt(l_intReservedDate);

            assertTrue(GtlUtils.Double.isEqual(441, l_dblContractAmt));
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    /*
     * 指定日が範囲外の場合(nが0以上2以下でない場合)
     * 指定日 = -1
     */
    public void testCalcBuyContractAmtRed_C0001()
    {
        final String STR_METHOD_NAME = "testCalcBuyContractAmtRed_C0001()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            int l_intReservedDate = -1;

            double l_dblContractAmt = depositCalc.calcBuyContractAmtRed(l_intReservedDate);

            assertTrue(GtlUtils.Double.isZero(l_dblContractAmt));
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    /*
     * 証拠金計算.指数別先物OP建玉集計一覧 == null の場合
     * (建玉/注文(*)が存在しない場合)
     */
    public void testCalcBuyContractAmtRed_C0002()
    {
        final String STR_METHOD_NAME = "testCalcBuyContractAmtRed_C0002()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            int l_intReservedDate = 1;

            double l_dblContractAmt = depositCalc.calcBuyContractAmtRed(l_intReservedDate);

            assertTrue(GtlUtils.Double.isZero(l_dblContractAmt));
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    /*
     * 以外の場合
     * (建玉/注文(*)が存在する場合、証拠金計算.指数別先物OP建玉集計一覧要素ごとのLoop処理を行い、総和を求める)
     */
    public void testCalcBuyContractAmtRed_C0003()
    {
        final String STR_METHOD_NAME = "testCalcBuyContractAmtRed_C0003()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            int l_intReservedDate = 0;

            WEB3IfoSummaryContractPerIndex ifoSummaryContractPerIndexList[] = new WEB3IfoSummaryContractPerIndex[3];
            for (int i = 0; i < 3; i++)
            {
                ifoSummaryContractPerIndexList[i] = new WEB3IfoSummaryContractPerIndex();
                ifoSummaryContractPerIndexList[i].setFuturesBuyContractQuantity(1);
                ifoSummaryContractPerIndexList[i].setCurrentBizDateFuturesBuyOrderQuantity(2);
                ifoSummaryContractPerIndexList[i].setNextBizDateFuturesBuyOrderQuantity(3);
                ifoSummaryContractPerIndexList[i].setOptionPutSellContractQuantity(4);
                ifoSummaryContractPerIndexList[i].setCurrentBizDateOptionPutSellOrderQuantity(5);
                ifoSummaryContractPerIndexList[i].setNextBizDateOptionPutSellOrderQuantity(6);
                ifoSummaryContractPerIndexList[i].setIfoDepositPerUnitRed(7);
            }

            Field l_field = WEB3IfoDepositCalc.class.getDeclaredField("ifoSummaryContractPerIndexList");
            l_field.setAccessible(true);
            l_field.set(depositCalc, ifoSummaryContractPerIndexList);

            double l_dblContractAmt = depositCalc.calcBuyContractAmtRed(l_intReservedDate);

            assertTrue(GtlUtils.Double.isEqual(252, l_dblContractAmt));
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    /*
     * 以外の場合
     * (建玉/注文(*)が存在する場合、証拠金計算.指数別先物OP建玉集計一覧要素ごとのLoop処理を行い、総和を求める)
     */
    public void testCalcBuyContractAmtRed_C0004()
    {
        final String STR_METHOD_NAME = "testCalcBuyContractAmtRed_C0004()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            int l_intReservedDate = 2;

            WEB3IfoSummaryContractPerIndex ifoSummaryContractPerIndexList[] = new WEB3IfoSummaryContractPerIndex[3];
            for (int i = 0; i < 3; i++)
            {
                ifoSummaryContractPerIndexList[i] = new WEB3IfoSummaryContractPerIndex();
                ifoSummaryContractPerIndexList[i].setFuturesBuyContractQuantity(1);
                ifoSummaryContractPerIndexList[i].setCurrentBizDateFuturesBuyOrderQuantity(2);
                ifoSummaryContractPerIndexList[i].setNextBizDateFuturesBuyOrderQuantity(3);
                ifoSummaryContractPerIndexList[i].setOptionPutSellContractQuantity(4);
                ifoSummaryContractPerIndexList[i].setCurrentBizDateOptionPutSellOrderQuantity(5);
                ifoSummaryContractPerIndexList[i].setNextBizDateOptionPutSellOrderQuantity(6);
                ifoSummaryContractPerIndexList[i].setIfoDepositPerUnitRed(7);
            }

            Field l_field = WEB3IfoDepositCalc.class.getDeclaredField("ifoSummaryContractPerIndexList");
            l_field.setAccessible(true);
            l_field.set(depositCalc, ifoSummaryContractPerIndexList);

            double l_dblContractAmt = depositCalc.calcBuyContractAmtRed(l_intReservedDate);

            assertTrue(GtlUtils.Double.isEqual(441, l_dblContractAmt));
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    /*
     * 指定日が範囲外の場合(nが0以上2以下でない場合)
     * 指定日 = -1
     */
    public void testCalcSellContractAmtRed_C0001()
    {
        final String STR_METHOD_NAME = "testCalcSellContractAmtRed_C0001()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            int l_intReservedDate = -1;

            double l_dblContractAmt = depositCalc.calcSellContractAmtRed(l_intReservedDate);

            assertTrue(GtlUtils.Double.isZero(l_dblContractAmt));
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    /*
     * 証拠金計算.指数別先物OP建玉集計一覧 == null の場合
     * (建玉/注文(*)が存在しない場合)
     */
    public void testCalcSellContractAmtRed_C0002()
    {
        final String STR_METHOD_NAME = "testCalcSellContractAmtRed_C0002()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            int l_intReservedDate = 1;

            double l_dblContractAmt = depositCalc.calcSellContractAmtRed(l_intReservedDate);

            assertTrue(GtlUtils.Double.isZero(l_dblContractAmt));
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    /*
     * 以外の場合
     * (建玉/注文(*)が存在する場合、証拠金計算.指数別先物OP建玉集計一覧要素ごとのLoop処理を行い、総和を求める)
     */
    public void testCalcSellContractAmtRed_C0003()
    {
        final String STR_METHOD_NAME = "testCalcSellContractAmtRed_C0003()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            int l_intReservedDate = 0;

            WEB3IfoSummaryContractPerIndex ifoSummaryContractPerIndexList[] = new WEB3IfoSummaryContractPerIndex[3];
            for (int i = 0; i < 3; i++)
            {
                ifoSummaryContractPerIndexList[i] = new WEB3IfoSummaryContractPerIndex();
                ifoSummaryContractPerIndexList[i].setFuturesSellContractQuantity(1);
                ifoSummaryContractPerIndexList[i].setCurrentBizDateFuturesSellOrderQuantity(2);
                ifoSummaryContractPerIndexList[i].setNextBizDateFuturesSellOrderQuantity(3);
                ifoSummaryContractPerIndexList[i].setOptionCallSellContractQuantity(4);
                ifoSummaryContractPerIndexList[i].setCurrentBizDateOptionCallSellOrderQuantity(5);
                ifoSummaryContractPerIndexList[i].setNextBizDateOptionCallSellOrderQuantity(6);
                ifoSummaryContractPerIndexList[i].setIfoDepositPerUnitRed(7);
            }

            Field l_field = WEB3IfoDepositCalc.class.getDeclaredField("ifoSummaryContractPerIndexList");
            l_field.setAccessible(true);
            l_field.set(depositCalc, ifoSummaryContractPerIndexList);

            double l_dblContractAmt = depositCalc.calcSellContractAmtRed(l_intReservedDate);

            assertTrue(GtlUtils.Double.isEqual(252, l_dblContractAmt));
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    /*
     * 以外の場合
     * (建玉/注文(*)が存在する場合、証拠金計算.指数別先物OP建玉集計一覧要素ごとのLoop処理を行い、総和を求める)
     */
    public void testCalcSellContractAmtRed_C0004()
    {
        final String STR_METHOD_NAME = "testCalcSellContractAmtRed_C0004()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            int l_intReservedDate = 2;

            WEB3IfoSummaryContractPerIndex ifoSummaryContractPerIndexList[] = new WEB3IfoSummaryContractPerIndex[3];
            for (int i = 0; i < 3; i++)
            {
                ifoSummaryContractPerIndexList[i] = new WEB3IfoSummaryContractPerIndex();
                ifoSummaryContractPerIndexList[i].setFuturesSellContractQuantity(1);
                ifoSummaryContractPerIndexList[i].setCurrentBizDateFuturesSellOrderQuantity(2);
                ifoSummaryContractPerIndexList[i].setNextBizDateFuturesSellOrderQuantity(3);
                ifoSummaryContractPerIndexList[i].setOptionCallSellContractQuantity(4);
                ifoSummaryContractPerIndexList[i].setCurrentBizDateOptionCallSellOrderQuantity(5);
                ifoSummaryContractPerIndexList[i].setNextBizDateOptionCallSellOrderQuantity(6);
                ifoSummaryContractPerIndexList[i].setIfoDepositPerUnitRed(7);
            }

            Field l_field = WEB3IfoDepositCalc.class.getDeclaredField("ifoSummaryContractPerIndexList");
            l_field.setAccessible(true);
            l_field.set(depositCalc, ifoSummaryContractPerIndexList);

            double l_dblContractAmt = depositCalc.calcSellContractAmtRed(l_intReservedDate);

            assertTrue(GtlUtils.Double.isEqual(441, l_dblContractAmt));
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    /*
     * ポジションバランス(n) = "-" ; ポジションバランス区分(n) = "-"
     * SPAN証拠金(n) = "-"
     */
    public void testPrintLog_C0001()
    {
        final String STR_METHOD_NAME = "testPrintLog_C0001()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            l_blPrint = true;
            WEB3IfoDepositCalc l_calcTest = new WEB3IfoDepositCalcForTestF();

            WEB3IfoDepositCalcCondition ifoDepositCalcCondition = new WEB3IfoDepositCalcCondition();
            Date[] bizDates = new Date[4];
            bizDates[0] = WEB3DateUtility.getDate("20080110", "yyyyMMdd");
            bizDates[1] = WEB3DateUtility.getDate("20080801", "yyyyMMdd");
            bizDates[2] = WEB3DateUtility.getDate("20080802", "yyyyMMdd");
            bizDates[3] = WEB3DateUtility.getDate("20080803", "yyyyMMdd");
            ifoDepositCalcCondition.setBizDates(bizDates);

            Field l_field1 = WEB3IfoDepositCalc.class.getDeclaredField("ifoDepositCalcCondition");
            l_field1.setAccessible(true);
            l_field1.set(l_calcTest, ifoDepositCalcCondition);

            WEB3IfoCustomerTransfer ifoCustomerTransfer = new WEB3IfoCustomerTransfer();
            ifoCustomerTransfer.setCurrentBizDateTransferAmount(1);
            ifoCustomerTransfer.setNextBizDateTransferAmount(2);
            ifoCustomerTransfer.addNextBizDateFuturesCloseProfitLoss(3);
            ifoCustomerTransfer.addNext2BizDateFuturesCloseProfitLoss(4);
            ifoCustomerTransfer.addNextBizDateOptionNetAmount(5);
            ifoCustomerTransfer.addNext2BizDateOptionNetAmount(6);
            ifoCustomerTransfer.addNextBizDateOptionBuyEstimatedNetAmount(7);
            ifoCustomerTransfer.addNext2BizDateOptionBuyEstimatedNetAmount(8);

            Field l_field2 = WEB3IfoDepositCalc.class.getDeclaredField("ifoCustomerTransfer");
            l_field2.setAccessible(true);
            l_field2.set(l_calcTest, ifoCustomerTransfer);

            

            Method l_method = WEB3IfoDepositCalc.class.getDeclaredMethod(
                "printLog",
                new Class[]{});
            l_method.setAccessible(true);
            Object[] l_obj = {};
            l_method.invoke(l_calcTest, l_obj);

            assertTrue(true);
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    /*
     * ポジションバランス(n) = "10" ; ポジションバランス区分(n) = "abc"
     * SPAN証拠金(n) = "11"
     */
    public void testPrintLog_C0002()
    {
        final String STR_METHOD_NAME = "testPrintLog_C0002()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            l_blPrint = false;
            WEB3IfoDepositCalc l_calcTest = new WEB3IfoDepositCalcForTestF();

            WEB3IfoDepositCalcCondition ifoDepositCalcCondition = new WEB3IfoDepositCalcCondition();
            Date[] bizDates = new Date[4];
            bizDates[0] = WEB3DateUtility.getDate("20080110", "yyyyMMdd");
            bizDates[1] = WEB3DateUtility.getDate("20080801", "yyyyMMdd");
            bizDates[2] = WEB3DateUtility.getDate("20080802", "yyyyMMdd");
            bizDates[3] = WEB3DateUtility.getDate("20080803", "yyyyMMdd");
            ifoDepositCalcCondition.setBizDates(bizDates);

            Field l_field1 = WEB3IfoDepositCalc.class.getDeclaredField("ifoDepositCalcCondition");
            l_field1.setAccessible(true);
            l_field1.set(l_calcTest, ifoDepositCalcCondition);

            WEB3IfoCustomerTransfer ifoCustomerTransfer = new WEB3IfoCustomerTransfer();
            ifoCustomerTransfer.setCurrentBizDateTransferAmount(2);
            ifoCustomerTransfer.setNextBizDateTransferAmount(3);
            ifoCustomerTransfer.addNextBizDateFuturesCloseProfitLoss(4);
            ifoCustomerTransfer.addNext2BizDateFuturesCloseProfitLoss(5);
            ifoCustomerTransfer.addNextBizDateOptionNetAmount(6);
            ifoCustomerTransfer.addNext2BizDateOptionNetAmount(7);
            ifoCustomerTransfer.addNextBizDateOptionBuyEstimatedNetAmount(8);
            ifoCustomerTransfer.addNext2BizDateOptionBuyEstimatedNetAmount(9);

            Field l_field2 = WEB3IfoDepositCalc.class.getDeclaredField("ifoCustomerTransfer");
            l_field2.setAccessible(true);
            l_field2.set(l_calcTest, ifoCustomerTransfer);

            

            Method l_method = WEB3IfoDepositCalc.class.getDeclaredMethod(
                "printLog",
                new Class[]{});
            l_method.setAccessible(true);
            Object[] l_obj = {};
            l_method.invoke(l_calcTest, l_obj);

            assertTrue(true);
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    /*
     * 引数.原資産銘柄コード == ミニ日経225の場合
     * 引数.原資産銘柄コード = "0019"
     */
    public void testCalcPositionQuantity_C0001()
    {
        final String STR_METHOD_NAME = "testCalcPositionQuantity_C0001()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            double l_dblQuantity = 3;
            String l_strUnderlyingProductCode = "0019";

            Method l_method = WEB3IfoDepositCalc.class.getDeclaredMethod(
                "calcPositionQuantity",
                new Class[]{double.class, String.class});
            l_method.setAccessible(true);
            Object[] l_obj = {new Double(l_dblQuantity), l_strUnderlyingProductCode};
            String l_strQuantity = l_method.invoke(depositCalc, l_obj).toString();

            assertEquals("0.3", l_strQuantity);
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    /*
     * 引数.原資産銘柄コード != ミニ日経225の場合
     * 引数.原資産銘柄コード = "0018"
     */
    public void testCalcPositionQuantity_C0002()
    {
        final String STR_METHOD_NAME = "testCalcPositionQuantity_C0002()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            double l_dblQuantity = 3;
            String l_strUnderlyingProductCode = "0018";

            Method l_method = WEB3IfoDepositCalc.class.getDeclaredMethod(
                "calcPositionQuantity",
                new Class[]{double.class, String.class});
            l_method.setAccessible(true);
            Object[] l_obj = {new Double(l_dblQuantity), l_strUnderlyingProductCode};
            String l_strQuantity = l_method.invoke(depositCalc, l_obj).toString();

            assertEquals("3.0", l_strQuantity);
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    /*
     * 引数.原資産銘柄コード = ミニ日経225の場合
     * BigDecimal.ROUND_UNNECESSARY(?出ArithmeticException)
     */
    public void testCalcPositionQuantity_C0003()
    {
        final String STR_METHOD_NAME = "testCalcPositionQuantity_C0003()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            double l_dblQuantity = 3.3 + 3.3;
            String l_strUnderlyingProductCode = "0019";

            Method l_method = WEB3IfoDepositCalc.class.getDeclaredMethod(
                "calcPositionQuantity",
                new Class[]{double.class, String.class});
            l_method.setAccessible(true);
            Object[] l_obj = {new Double(l_dblQuantity), l_strUnderlyingProductCode};
            l_method.invoke(depositCalc, l_obj).toString();

            fail();
        }
        catch (InvocationTargetException l_ex)
        {
            log.debug(TEST_END + STR_METHOD_NAME, l_ex);
            assertEquals(ArithmeticException.class, l_ex.getTargetException().getClass());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    /*
     * 指定日が範囲外の場合(nが0以上2以下でない場合)、0を返却
     */
    public void testCalcReceiptIfoDepositBalanceTemp_C0001()
    {
        final String STR_METHOD_NAME = "testCalcReceiptIfoDepositBalanceTemp_C0001()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            int l_intReservedDate = -1;

            double l_dblReceiptIfoDepositBalanceTemp = depositCalc.calcReceiptIfoDepositBalanceTemp(l_intReservedDate);

            assertTrue(GtlUtils.Double.isZero(l_dblReceiptIfoDepositBalanceTemp));
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    /*
     * normal case
     */
    public void testCalcReceiptIfoDepositBalanceTemp_C0002()
    {
        final String STR_METHOD_NAME = "testCalcReceiptIfoDepositBalanceTemp_C0002()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            int l_intReservedDate = 1;

            WEB3IfoDepositCalc l_calcTest = new WEB3IfoDepositCalcForTestA();
            double l_dblReceiptIfoDepositBalanceTemp = l_calcTest.calcReceiptIfoDepositBalanceTemp(l_intReservedDate);

            assertTrue(GtlUtils.Double.isEqual(35, l_dblReceiptIfoDepositBalanceTemp));
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    /*
     * 指定日が範囲外の場合(nが0以上2以下でない場合)、0を返却
     */
    public void testCalcIfoDepositRequiredAmountTemp_C0001()
    {
        final String STR_METHOD_NAME = "testCalcIfoDepositRequiredAmountTemp_C0001()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            int l_intReservedDate = -1;

            double l_dblIfoDepositRequiredAmountTemp = depositCalc.calcIfoDepositRequiredAmountTemp(l_intReservedDate);

            assertTrue(GtlUtils.Double.isZero(l_dblIfoDepositRequiredAmountTemp));
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    /*
     * [a.証拠金計算.証拠金計算条件.isSPAN使用可能() == true の場合]
     * （SPAN証拠金採用会社の場合）
     */
    public void testCalcIfoDepositRequiredAmountTemp_C0002()
    {
        final String STR_METHOD_NAME = "testCalcIfoDepositRequiredAmountTemp_C0002()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            int l_intReservedDate = 1;
            WEB3IfoDepositCalc l_calcTest = new WEB3IfoDepositCalcForTestB();
            
            WEB3IfoDepositCalcCondition ifoDepositCalcCondition = new WEB3IfoDepositCalcCondition();
            ifoDepositCalcCondition.setSimpleSPANCalcFlag(false);
            ifoDepositCalcCondition.setSpanTroubleFlag(false);
            
            double SPANFactor = 3;
            ifoDepositCalcCondition.setSPANFactor(SPANFactor);

            Field l_field = WEB3IfoDepositCalc.class.getDeclaredField("ifoDepositCalcCondition");
            l_field.setAccessible(true);
            l_field.set(l_calcTest, ifoDepositCalcCondition);

            double l_dblIfoDepositRequiredAmountTemp = l_calcTest.calcIfoDepositRequiredAmountTemp(l_intReservedDate);

            assertTrue(GtlUtils.Double.isEqual(44, l_dblIfoDepositRequiredAmountTemp));
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    /*
     * [b.証拠金計算.証拠金計算条件.isSPAN使用可能() == false の場合]
     * （SPAN証拠金非採用会社、または、SPAN採用会社でSPANがトラブルの場合）
     */
    public void testCalcIfoDepositRequiredAmountTemp_C0003()
    {
        final String STR_METHOD_NAME = "testCalcIfoDepositRequiredAmountTemp_C0003()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            int l_intReservedDate = 1;
            WEB3IfoDepositCalc l_calcTest = new WEB3IfoDepositCalcForTestC();
            
            WEB3IfoDepositCalcCondition ifoDepositCalcCondition = new WEB3IfoDepositCalcCondition();
            ifoDepositCalcCondition.setSimpleSPANCalcFlag(true);
            ifoDepositCalcCondition.setSpanTroubleFlag(true);
            
            double SPANFactor = 3;
            ifoDepositCalcCondition.setSPANFactor(SPANFactor);

            Field l_field = WEB3IfoDepositCalc.class.getDeclaredField("ifoDepositCalcCondition");
            l_field.setAccessible(true);
            l_field.set(l_calcTest, ifoDepositCalcCondition);

            double l_dblIfoDepositRequiredAmountTemp = l_calcTest.calcIfoDepositRequiredAmountTemp(l_intReservedDate);

            assertTrue(GtlUtils.Double.isEqual(19.5, l_dblIfoDepositRequiredAmountTemp));
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    /*
     * 指定日が範囲外の場合(nが0以上2以下でない場合)
     * 指定日 = -1
     */
    public void testCalcSimpleSPANIfoDepositTemp_C0001()
    {
        final String STR_METHOD_NAME = "testCalcSimpleSPANIfoDepositTemp_C0001()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            int l_intReservedDate = -1;

            double l_dblSimpleSPANTemp = depositCalc.calcSimpleSPANIfoDepositTemp(l_intReservedDate);

            assertTrue(GtlUtils.Double.isZero(l_dblSimpleSPANTemp));
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    /*
     * [a.証拠金計算.指数別先物OP建玉集計一覧 == null の場合]
     * (建玉/注文(*)が存在しない場合)
     */
    public void testCalcSimpleSPANIfoDepositTemp_C0002()
    {
        final String STR_METHOD_NAME = "testCalcSimpleSPANIfoDepositTemp_C0002()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            int l_intReservedDate = 1;

            double l_dblSimpleSPANTemp = depositCalc.calcSimpleSPANIfoDepositTemp(l_intReservedDate);

            assertTrue(GtlUtils.Double.isZero(l_dblSimpleSPANTemp));
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    /*
     * [b. 以外の場合]
     */
    public void testCalcSimpleSPANIfoDepositTemp_C0003()
    {
        final String STR_METHOD_NAME = "testCalcSimpleSPANIfoDepositTemp_C0003()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            int l_intReservedDate = 1;

            WEB3IfoDepositCalc l_calcTest = new WEB3IfoDepositCalcForTestD();
            WEB3IfoSummaryContractPerIndex ifoSummaryContractPerIndexList[] = new WEB3IfoSummaryContractPerIndex[3];

            Field l_field = WEB3IfoDepositCalc.class.getDeclaredField("ifoSummaryContractPerIndexList");
            l_field.setAccessible(true);
            l_field.set(l_calcTest, ifoSummaryContractPerIndexList);

            double l_dblSimpleSPANTemp = l_calcTest.calcSimpleSPANIfoDepositTemp(l_intReservedDate);

            assertTrue(GtlUtils.Double.isEqual(40, l_dblSimpleSPANTemp));
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    /*
     * normal case
     */
    public void testCalcFuturesAppraisalProfitLossTemp_C0001()
    {
        final String STR_METHOD_NAME = "testCalcFuturesAppraisalProfitLossTemp_C0001()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            WEB3IfoDepositCalc l_calcTest = new WEB3IfoDepositCalcForTestD();

            double l_dblProfitLossTemp = l_calcTest.calcFuturesAppraisalProfitLossTemp();

            assertTrue(GtlUtils.Double.isEqual(95, l_dblProfitLossTemp));
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    /*
     * [a.証拠金計算.銘柄建単価別先物OP建玉集計一覧 == null の場合]
     * (建玉が存在しない場合)
     */
    public void testCalcFuturesBuyAppraisalProfitLossTemp_C0001()
    {
        final String STR_METHOD_NAME = "testCalcFuturesBuyAppraisalProfitLossTemp_C0001()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            double l_dblBuyProfitLossTemp = depositCalc.calcFuturesBuyAppraisalProfitLossTemp();

            assertTrue(GtlUtils.Double.isZero(l_dblBuyProfitLossTemp));
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    /*
     * [b. 以外の場合]
     * (建玉が存在する場合、証拠金計算.銘柄建単価別先物OP建玉集計一覧要素ごとのLoop処理を行い、総和を求める)
     */
    public void testCalcFuturesBuyAppraisalProfitLossTemp_C0002()
    {
        final String STR_METHOD_NAME = "testCalcFuturesBuyAppraisalProfitLossTemp_C0002()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            WEB3IfoSummaryContractPerProductContractPrice ifoSummaryContractPerProductContractPriceList[] =
                new WEB3IfoSummaryContractPerProductContractPrice[3];
            for (int i = 0; i < 3; i++)
            {
                ifoSummaryContractPerProductContractPriceList[i] = new WEB3IfoSummaryContractPerProductContractPrice();
                ifoSummaryContractPerProductContractPriceList[i].setIfoDerivativeType(IfoDerivativeTypeEnum.FUTURES);
                ifoSummaryContractPerProductContractPriceList[i].setCurrentBizDateLiquidationPrice(10.5);
                ifoSummaryContractPerProductContractPriceList[i].setContractPrice(5);
                ifoSummaryContractPerProductContractPriceList[i].addQuantityTemp(true, 1);
                ifoSummaryContractPerProductContractPriceList[i].setUnitSize(10);
            }

            Field l_field = WEB3IfoDepositCalc.class.getDeclaredField("ifoSummaryContractPerProductContractPriceList");
            l_field.setAccessible(true);
            l_field.set(depositCalc, ifoSummaryContractPerProductContractPriceList);

            double l_dblBuyProfitLossTemp = depositCalc.calcFuturesBuyAppraisalProfitLossTemp();

            assertTrue(GtlUtils.Double.isEqual(165, l_dblBuyProfitLossTemp));
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    /*
     * [a.証拠金計算.銘柄建単価別先物OP建玉集計一覧 == null の場合]
     * (建玉が存在しない場合)
     */
    public void testCalcFuturesSellAppraisalProfitLossTemp_C0001()
    {
        final String STR_METHOD_NAME = "testCalcFuturesSellAppraisalProfitLossTemp_C0001()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            double l_dblSellProfitLossTemp = depositCalc.calcFuturesSellAppraisalProfitLossTemp();

            assertTrue(GtlUtils.Double.isZero(l_dblSellProfitLossTemp));
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    /*
     * [b. 以外の場合]
     * (建玉が存在する場合、証拠金計算.銘柄建単価別先物OP建玉集計一覧要素ごとのLoop処理を行い、総和を求める)
     */
    public void testCalcFuturesSellAppraisalProfitLossTemp_C0002()
    {
        final String STR_METHOD_NAME = "testCalcFuturesSellAppraisalProfitLossTemp_C0002()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            WEB3IfoSummaryContractPerProductContractPrice ifoSummaryContractPerProductContractPriceList[] =
                new WEB3IfoSummaryContractPerProductContractPrice[3];
            for (int i = 0; i < 3; i++)
            {
                ifoSummaryContractPerProductContractPriceList[i] = new WEB3IfoSummaryContractPerProductContractPrice();
                ifoSummaryContractPerProductContractPriceList[i].setIfoDerivativeType(IfoDerivativeTypeEnum.FUTURES);
                ifoSummaryContractPerProductContractPriceList[i].setCurrentBizDateLiquidationPrice(10.5);
                ifoSummaryContractPerProductContractPriceList[i].setContractPrice(5);
                ifoSummaryContractPerProductContractPriceList[i].addQuantityTemp(false, 1);
                ifoSummaryContractPerProductContractPriceList[i].setUnitSize(10);
            }

            Field l_field = WEB3IfoDepositCalc.class.getDeclaredField("ifoSummaryContractPerProductContractPriceList");
            l_field.setAccessible(true);
            l_field.set(depositCalc, ifoSummaryContractPerProductContractPriceList);

            double l_dblSellProfitLossTemp = depositCalc.calcFuturesSellAppraisalProfitLossTemp();

            assertTrue(GtlUtils.Double.isEqual(-165, l_dblSellProfitLossTemp));
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    /*
     * 指定日が範囲外の場合(nが0以上2以下でない場合)、0を返却
     */
    public void testCalcNetOptionTotalAmountTemp_C0001()
    {
        final String STR_METHOD_NAME = "testCalcNetOptionTotalAmountTemp_C0001()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            int l_intReservedDate = -1;

            double l_dblSellProfitLossTemp = depositCalc.calcNetOptionTotalAmountTemp(l_intReservedDate);

            assertTrue(GtlUtils.Double.isZero(l_dblSellProfitLossTemp));
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    /*
     * normal case
     */
    public void testCalcNetOptionTotalAmountTemp_C0002()
    {
        final String STR_METHOD_NAME = "testCalcNetOptionTotalAmountTemp_C0002()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            l_blTest = true;
            int l_intReservedDate = 0;

            WEB3IfoDepositCalc l_calcTest = new WEB3IfoDepositCalcForTestE();
            double l_dblSellProfitLossTemp = l_calcTest.calcNetOptionTotalAmountTemp(l_intReservedDate);

            assertTrue(GtlUtils.Double.isEqual(-1, l_dblSellProfitLossTemp));
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    /*
     * normal case
     */
    public void testCalcNetOptionTotalAmountTemp_C0003()
    {
        final String STR_METHOD_NAME = "testCalcNetOptionTotalAmountTemp_C0003()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            l_blTest = false;
            int l_intReservedDate = 2;

            WEB3IfoDepositCalc l_calcTest = new WEB3IfoDepositCalcForTestE();
            double l_dblSellProfitLossTemp = l_calcTest.calcNetOptionTotalAmountTemp(l_intReservedDate);

            assertTrue(GtlUtils.Double.isZero(l_dblSellProfitLossTemp));
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    /*
     * 指定日が範囲外の場合(nが0以上2以下でない場合)、0を返却
     */
    public void testCalcOptionBuyTotalAmountTemp_C0001()
    {
        final String STR_METHOD_NAME = "testCalcOptionBuyTotalAmountTemp_C0001()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            int l_intReservedDate = -1;

            double l_dblBuyNetOptionTemp = depositCalc.calcOptionBuyTotalAmountTemp(l_intReservedDate);

            assertTrue(GtlUtils.Double.isZero(l_dblBuyNetOptionTemp));
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    /*
     * 証拠金計算.銘柄別先物OP建玉集計一覧 == null の場合
     */
    public void testCalcOptionBuyTotalAmountTemp_C0002()
    {
        final String STR_METHOD_NAME = "testCalcOptionBuyTotalAmountTemp_C0002()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            int l_intReservedDate = 1;

            double l_dblBuyNetOptionTemp = depositCalc.calcOptionBuyTotalAmountTemp(l_intReservedDate);

            assertTrue(GtlUtils.Double.isZero(l_dblBuyNetOptionTemp));
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    /*
     * normal case
     */
    public void testCalcOptionBuyTotalAmountTemp_C0003()
    {
        final String STR_METHOD_NAME = "testCalcOptionBuyTotalAmountTemp_C0003()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            int l_intReservedDate = 0;

            WEB3IfoSummaryContractPerProduct ifoSummaryContractPerProductList[] =
                new WEB3IfoSummaryContractPerProduct[3];
            for (int i = 0; i < 3;i ++)
            {
                ifoSummaryContractPerProductList[i] = new WEB3IfoSummaryContractPerProduct();
                ifoSummaryContractPerProductList[i].setIfoDerivativeType(IfoDerivativeTypeEnum.OTHER);
                ifoSummaryContractPerProductList[i].addQuantityTemp(true, 1);
                ifoSummaryContractPerProductList[i].setCurrentBizDateLiquidationPrice(15);
                ifoSummaryContractPerProductList[i].setUnitSize(10);
            }
            
            Field l_field = WEB3IfoDepositCalc.class.getDeclaredField("ifoSummaryContractPerProductList");
            l_field.setAccessible(true);
            l_field.set(depositCalc, ifoSummaryContractPerProductList);
            
            double l_dblBuyNetOptionTemp = depositCalc.calcOptionBuyTotalAmountTemp(l_intReservedDate);

            assertTrue(GtlUtils.Double.isEqual(450, l_dblBuyNetOptionTemp));
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    /*
     * normal case
     */
    public void testCalcOptionBuyTotalAmountTemp_C0004()
    {
        final String STR_METHOD_NAME = "testCalcOptionBuyTotalAmountTemp_C0004()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            int l_intReservedDate = 2;

            WEB3IfoSummaryContractPerProduct ifoSummaryContractPerProductList[] =
                new WEB3IfoSummaryContractPerProduct[3];
            for (int i = 0; i < 3;i ++)
            {
                ifoSummaryContractPerProductList[i] = new WEB3IfoSummaryContractPerProduct();
                ifoSummaryContractPerProductList[i].setIfoDerivativeType(IfoDerivativeTypeEnum.OTHER);
                ifoSummaryContractPerProductList[i].addQuantityTemp(true, 1);
                ifoSummaryContractPerProductList[i].setCurrentBizDateLiquidationPrice(15);
                ifoSummaryContractPerProductList[i].setUnitSize(10);
            }
            
            Field l_field = WEB3IfoDepositCalc.class.getDeclaredField("ifoSummaryContractPerProductList");
            l_field.setAccessible(true);
            l_field.set(depositCalc, ifoSummaryContractPerProductList);
            
            double l_dblBuyNetOptionTemp = depositCalc.calcOptionBuyTotalAmountTemp(l_intReservedDate);

            assertTrue(GtlUtils.Double.isEqual(450, l_dblBuyNetOptionTemp));
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    /*
     * 指定日が範囲外の場合(nが0以上2以下でない場合)、0を返却
     */
    public void testCalcOptionSellTotalAmountTemp_C0001()
    {
        final String STR_METHOD_NAME = "testCalcOptionSellTotalAmountTemp_C0001()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            int l_intReservedDate = -1;

            double l_dblBuyNetOptionTemp = depositCalc.calcOptionSellTotalAmountTemp(l_intReservedDate);

            assertTrue(GtlUtils.Double.isZero(l_dblBuyNetOptionTemp));
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    /*
     * 証拠金計算.銘柄別先物OP建玉集計一覧 == null の場合
     */
    public void testCalcOptionSellTotalAmountTemp_C0002()
    {
        final String STR_METHOD_NAME = "testCalcOptionSellTotalAmountTemp_C0002()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            int l_intReservedDate = 1;

            double l_dblBuyNetOptionTemp = depositCalc.calcOptionSellTotalAmountTemp(l_intReservedDate);

            assertTrue(GtlUtils.Double.isZero(l_dblBuyNetOptionTemp));
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    /*
     * normal case
     */
    public void testCalcOptionSellTotalAmountTemp_C0003()
    {
        final String STR_METHOD_NAME = "testCalcOptionSellTotalAmountTemp_C0003()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            int l_intReservedDate = 0;

            WEB3IfoSummaryContractPerProduct ifoSummaryContractPerProductList[] =
                new WEB3IfoSummaryContractPerProduct[3];
            for (int i = 0; i < 3;i ++)
            {
                ifoSummaryContractPerProductList[i] = new WEB3IfoSummaryContractPerProduct();
                ifoSummaryContractPerProductList[i].setIfoDerivativeType(IfoDerivativeTypeEnum.OTHER);
                ifoSummaryContractPerProductList[i].addQuantityTemp(false, 1);
                ifoSummaryContractPerProductList[i].setCurrentBizDateLiquidationPrice(15);
                ifoSummaryContractPerProductList[i].setUnitSize(10);
            }
            
            Field l_field = WEB3IfoDepositCalc.class.getDeclaredField("ifoSummaryContractPerProductList");
            l_field.setAccessible(true);
            l_field.set(depositCalc, ifoSummaryContractPerProductList);
            
            double l_dblBuyNetOptionTemp = depositCalc.calcOptionSellTotalAmountTemp(l_intReservedDate);

            assertTrue(GtlUtils.Double.isEqual(450, l_dblBuyNetOptionTemp));
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    /*
     * normal case
     */
    public void testCalcOptionSellTotalAmountTemp_C0004()
    {
        final String STR_METHOD_NAME = "testCalcOptionSellTotalAmountTemp_C0004()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            int l_intReservedDate = 2;

            WEB3IfoSummaryContractPerProduct ifoSummaryContractPerProductList[] =
                new WEB3IfoSummaryContractPerProduct[3];
            for (int i = 0; i < 3;i ++)
            {
                ifoSummaryContractPerProductList[i] = new WEB3IfoSummaryContractPerProduct();
                ifoSummaryContractPerProductList[i].setIfoDerivativeType(IfoDerivativeTypeEnum.OTHER);
                ifoSummaryContractPerProductList[i].addQuantityTemp(false, 1);
                ifoSummaryContractPerProductList[i].setCurrentBizDateLiquidationPrice(15);
                ifoSummaryContractPerProductList[i].setUnitSize(10);
            }
            
            Field l_field = WEB3IfoDepositCalc.class.getDeclaredField("ifoSummaryContractPerProductList");
            l_field.setAccessible(true);
            l_field.set(depositCalc, ifoSummaryContractPerProductList);
            
            double l_dblBuyNetOptionTemp = depositCalc.calcOptionSellTotalAmountTemp(l_intReservedDate);

            assertTrue(GtlUtils.Double.isEqual(450, l_dblBuyNetOptionTemp));
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    /*
     * 指定日が範囲外の場合(nが0以上2以下でない場合)、0を返却
     */
    public void testCalcBuyContractAmtTemp_C0001()
    {
        final String STR_METHOD_NAME = "testCalcBuyContractAmtTemp_C0001()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            int l_intReservedDate = -1;

            double l_dblBuyNetOptionTemp = depositCalc.calcBuyContractAmtTemp(l_intReservedDate);

            assertTrue(GtlUtils.Double.isZero(l_dblBuyNetOptionTemp));
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    /*
     * 証拠金計算.指数別先物OP建玉集計一覧 == null の場合
     */
    public void testCalcBuyContractAmtTemp_C0002()
    {
        final String STR_METHOD_NAME = "testCalcBuyContractAmtTemp_C0002()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            int l_intReservedDate = 1;

            double l_dblBuyNetOptionTemp = depositCalc.calcBuyContractAmtTemp(l_intReservedDate);

            assertTrue(GtlUtils.Double.isZero(l_dblBuyNetOptionTemp));
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    /*
     * normal case
     */
    public void testCalcBuyContractAmtTemp_C0003()
    {
        final String STR_METHOD_NAME = "testCalcBuyContractAmtTemp_C0003()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            int l_intReservedDate = 0;

            WEB3IfoSummaryContractPerIndex ifoSummaryContractPerIndexList[] =
                new WEB3IfoSummaryContractPerIndex[3];
            for (int i = 0; i < 3;i ++)
            {
                ifoSummaryContractPerIndexList[i] = new WEB3IfoSummaryContractPerIndex();
                ifoSummaryContractPerIndexList[i].addContractQuantityTemp(IfoDerivativeTypeEnum.FUTURES, true, 1);
                ifoSummaryContractPerIndexList[i].setIfoDepositPerUnitTemp(5);
            }
            
            Field l_field = WEB3IfoDepositCalc.class.getDeclaredField("ifoSummaryContractPerIndexList");
            l_field.setAccessible(true);
            l_field.set(depositCalc, ifoSummaryContractPerIndexList);
            
            double l_dblBuyNetOptionTemp = depositCalc.calcBuyContractAmtTemp(l_intReservedDate);

            assertTrue(GtlUtils.Double.isEqual(15, l_dblBuyNetOptionTemp));
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    /*
     * normal case
     */
    public void testCalcBuyContractAmtTemp_C0004()
    {
        final String STR_METHOD_NAME = "testCalcBuyContractAmtTemp_C0004()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            int l_intReservedDate = 2;

            WEB3IfoSummaryContractPerIndex ifoSummaryContractPerIndexList[] =
                new WEB3IfoSummaryContractPerIndex[3];
            for (int i = 0; i < 3;i ++)
            {
                ifoSummaryContractPerIndexList[i] = new WEB3IfoSummaryContractPerIndex();
                ifoSummaryContractPerIndexList[i].addContractQuantityTemp(IfoDerivativeTypeEnum.FUTURES, true, 1);
                ifoSummaryContractPerIndexList[i].setIfoDepositPerUnitTemp(5);
            }
            
            Field l_field = WEB3IfoDepositCalc.class.getDeclaredField("ifoSummaryContractPerIndexList");
            l_field.setAccessible(true);
            l_field.set(depositCalc, ifoSummaryContractPerIndexList);
            
            double l_dblBuyNetOptionTemp = depositCalc.calcBuyContractAmtTemp(l_intReservedDate);

            assertTrue(GtlUtils.Double.isEqual(15, l_dblBuyNetOptionTemp));
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    /*
     * 指定日が範囲外の場合(nが0以上2以下でない場合)、0を返却
     */
    public void testCalcSellContractAmtTemp_C0001()
    {
        final String STR_METHOD_NAME = "testCalcSellContractAmtTemp_C0001()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            int l_intReservedDate = -1;

            double l_dblBuyNetOptionTemp = depositCalc.calcSellContractAmtTemp(l_intReservedDate);

            assertTrue(GtlUtils.Double.isZero(l_dblBuyNetOptionTemp));
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    /*
     * 証拠金計算.指数別先物OP建玉集計一覧 == null の場合
     */
    public void testCalcSellContractAmtTemp_C0002()
    {
        final String STR_METHOD_NAME = "testCalcSellContractAmtTemp_C0002()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            int l_intReservedDate = 1;

            double l_dblBuyNetOptionTemp = depositCalc.calcSellContractAmtTemp(l_intReservedDate);

            assertTrue(GtlUtils.Double.isZero(l_dblBuyNetOptionTemp));
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    /*
     * normal case
     */
    public void testCalcSellContractAmtTemp_C0003()
    {
        final String STR_METHOD_NAME = "testCalcSellContractAmtTemp_C0003()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            int l_intReservedDate = 0;

            WEB3IfoSummaryContractPerIndex ifoSummaryContractPerIndexList[] =
                new WEB3IfoSummaryContractPerIndex[3];
            for (int i = 0; i < 3;i ++)
            {
                ifoSummaryContractPerIndexList[i] = new WEB3IfoSummaryContractPerIndex();
                ifoSummaryContractPerIndexList[i].addContractQuantityTemp(IfoDerivativeTypeEnum.FUTURES, false, 1);
                ifoSummaryContractPerIndexList[i].setIfoDepositPerUnitTemp(5);
            }
            
            Field l_field = WEB3IfoDepositCalc.class.getDeclaredField("ifoSummaryContractPerIndexList");
            l_field.setAccessible(true);
            l_field.set(depositCalc, ifoSummaryContractPerIndexList);
            
            double l_dblBuyNetOptionTemp = depositCalc.calcSellContractAmtTemp(l_intReservedDate);

            assertTrue(GtlUtils.Double.isEqual(15, l_dblBuyNetOptionTemp));
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    /*
     * normal case
     */
    public void testCalcSellContractAmtTemp_C0004()
    {
        final String STR_METHOD_NAME = "testCalcSellContractAmtTemp_C0004()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            int l_intReservedDate = 2;

            WEB3IfoSummaryContractPerIndex ifoSummaryContractPerIndexList[] =
                new WEB3IfoSummaryContractPerIndex[3];
            for (int i = 0; i < 3;i ++)
            {
                ifoSummaryContractPerIndexList[i] = new WEB3IfoSummaryContractPerIndex();
                ifoSummaryContractPerIndexList[i].addContractQuantityTemp(IfoDerivativeTypeEnum.FUTURES, false, 1);
                ifoSummaryContractPerIndexList[i].setIfoDepositPerUnitTemp(5);
            }

            Field l_field = WEB3IfoDepositCalc.class.getDeclaredField("ifoSummaryContractPerIndexList");
            l_field.setAccessible(true);
            l_field.set(depositCalc, ifoSummaryContractPerIndexList);

            double l_dblBuyNetOptionTemp = depositCalc.calcSellContractAmtTemp(l_intReservedDate);

            assertTrue(GtlUtils.Double.isEqual(15, l_dblBuyNetOptionTemp));
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    /*
     * 指定日が範囲外の場合(nが0以上2以下でない場合)、0を返却
     */
    public void testCalcIfoDepositLackChargeTemp_C0001()
    {
        final String STR_METHOD_NAME = "testCalcIfoDepositLackChargeTemp_C0001()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            int l_intReservedDate = -1;

            double l_dblIfoDepositLackChargeTemp = depositCalc.calcIfoDepositLackChargeTemp(l_intReservedDate);

            assertTrue(GtlUtils.Double.isZero(l_dblIfoDepositLackChargeTemp));
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    /*
     * [a.証拠金計算.証拠金計算条件.is清算値速報受信済 == true かつ
     * 証拠金計算.証拠金計算条件.is証拠金不足メール送信済 == false の場合]
     * [a-1.　@受入証拠金残高＜証拠金不足仮確定＞(n)-証拠金所要額＜証拠金不足仮確定＞(n)）＜０　@の場合］
     */
    public void testCalcIfoDepositLackChargeTemp_C0002()
    {
        final String STR_METHOD_NAME = "testCalcIfoDepositLackChargeTemp_C0002()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            l_blTest = true;
            int l_intReservedDate = 1;
            WEB3IfoDepositCalc l_calcTest = new WEB3IfoDepositCalcForTestE();

            WEB3IfoDepositCalcCondition ifoDepositCalcCondition = new WEB3IfoDepositCalcCondition();
            ifoDepositCalcCondition.setQuickReportReceivedFlag(true);
            ifoDepositCalcCondition.setIfoDepositMailFlag(false);

            Field l_field = WEB3IfoDepositCalc.class.getDeclaredField("ifoDepositCalcCondition");
            l_field.setAccessible(true);
            l_field.set(l_calcTest, ifoDepositCalcCondition);

            double l_dblIfoDepositLackChargeTemp = l_calcTest.calcIfoDepositLackChargeTemp(l_intReservedDate);

            assertTrue(GtlUtils.Double.isEqual(5, l_dblIfoDepositLackChargeTemp));
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    /*
     * [a.証拠金計算.証拠金計算条件.is清算値速報受信済 == true かつ
     * 証拠金計算.証拠金計算条件.is証拠金不足メール送信済 == false の場合]
     * 証拠金不足額＜仮計算＞ = ０の場合
     */
    public void testCalcIfoDepositLackChargeTemp_C0003()
    {
        final String STR_METHOD_NAME = "testCalcIfoDepositLackChargeTemp_C0003()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            l_blTest = false;
            int l_intReservedDate = 0;
            WEB3IfoDepositCalc l_calcTest = new WEB3IfoDepositCalcForTestE();

            WEB3IfoDepositCalcCondition ifoDepositCalcCondition = new WEB3IfoDepositCalcCondition();
            ifoDepositCalcCondition.setQuickReportReceivedFlag(true);
            ifoDepositCalcCondition.setIfoDepositMailFlag(false);

            Field l_field = WEB3IfoDepositCalc.class.getDeclaredField("ifoDepositCalcCondition");
            l_field.setAccessible(true);
            l_field.set(l_calcTest, ifoDepositCalcCondition);

            double l_dblIfoDepositLackChargeTemp = l_calcTest.calcIfoDepositLackChargeTemp(l_intReservedDate);

            assertTrue(GtlUtils.Double.isZero(l_dblIfoDepositLackChargeTemp));
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    /*
     * ［b.　@a以外の場合］
     * 証拠金不足額＜仮計算＞ = ０
     */
    public void testCalcIfoDepositLackChargeTemp_C0004()
    {
        final String STR_METHOD_NAME = "testCalcIfoDepositLackChargeTemp_C0004()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            l_blTest = true;
            int l_intReservedDate = 2;
            WEB3IfoDepositCalc l_calcTest = new WEB3IfoDepositCalcForTestE();

            WEB3IfoDepositCalcCondition ifoDepositCalcCondition = new WEB3IfoDepositCalcCondition();
            ifoDepositCalcCondition.setQuickReportReceivedFlag(false);
            ifoDepositCalcCondition.setIfoDepositMailFlag(false);

            Field l_field = WEB3IfoDepositCalc.class.getDeclaredField("ifoDepositCalcCondition");
            l_field.setAccessible(true);
            l_field.set(l_calcTest, ifoDepositCalcCondition);

            double l_dblIfoDepositLackChargeTemp = l_calcTest.calcIfoDepositLackChargeTemp(l_intReservedDate);

            assertTrue(GtlUtils.Double.isZero(l_dblIfoDepositLackChargeTemp));
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    /*
     * ［a. 受入証拠金残高（T+2) - 証拠金所要額（T+2）＜０　@の場合］
     */
    public void testCalcNext2BizDateDemandAmount_C0001()
    {
        final String STR_METHOD_NAME = "testCalcIfoDepositLackChargeTemp_C0001()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            l_blTest = true;
            WEB3IfoDepositCalc l_calcTest = new WEB3IfoDepositCalcForTestE();

            double l_dblDemandAmount = l_calcTest.calcNext2BizDateDemandAmount();

            assertTrue(GtlUtils.Double.isEqual(5, l_dblDemandAmount));
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    /*
     * ［b. 以外の場合］
     */
    public void testCalcNext2BizDateDemandAmount_C0002()
    {
        final String STR_METHOD_NAME = "testCalcIfoDepositLackChargeTemp_C0002()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            l_blTest = false;
            WEB3IfoDepositCalc l_calcTest = new WEB3IfoDepositCalcForTestE();

            double l_dblDemandAmount = l_calcTest.calcNext2BizDateDemandAmount();

            assertTrue(GtlUtils.Double.isZero(l_dblDemandAmount));
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    /*
     * ［a. 受入証拠金残高（T+2) - 証拠金所要額（T+2）＜０　@の場合］
     */
    public void testCalcNextBizDateDemandAmountEvening_C0001()
    {
        final String STR_METHOD_NAME = "testCalcNextBizDateDemandAmountEvening_C0001()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            l_blTest = true;
            WEB3IfoDepositCalc l_calcTest = new WEB3IfoDepositCalcForTestE();

            double l_dblDemandAmount = l_calcTest.calcNextBizDateDemandAmountEvening();

            assertTrue(GtlUtils.Double.isEqual(5, l_dblDemandAmount));
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    /*
     * ［b. 以外の場合］
     */
    public void testCalcNextBizDateDemandAmountEvening_C0002()
    {
        final String STR_METHOD_NAME = "testCalcNextBizDateDemandAmountEvening_C0002()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            l_blTest = false;
            WEB3IfoDepositCalc l_calcTest = new WEB3IfoDepositCalcForTestE();

            double l_dblDemandAmount = l_calcTest.calcNextBizDateDemandAmountEvening();

            assertTrue(GtlUtils.Double.isZero(l_dblDemandAmount));
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    /**
     * WEB3IfoDepositCalcForTestA
     */
    private class WEB3IfoDepositCalcForTestA extends WEB3IfoDepositCalc
    {
        public double calcIfoDepositBalanceTemp(int l_intReservedDate)
        {
            return 15;
        }
        public double calcFuturesAppraisalProfitLossTemp()
        {   
            return 20;
        }
    }
    /**
     * WEB3IfoDepositCalcForTestB
     */
    private class WEB3IfoDepositCalcForTestB extends WEB3IfoDepositCalc
    {
        public double getSPANIfoDeposit(int l_intReservedDate)
        {
            return 25;
        }
        public double calcNetOptionTotalAmountTemp(int l_intReservedDate)
        {
            return 10.5;
        }
    }
    /**
     * WEB3IfoDepositCalcForTestC
     */
    private class WEB3IfoDepositCalcForTestC extends WEB3IfoDepositCalc
    {
        public double calcSimpleSPANIfoDepositTemp(int l_intReservedDate)
        {
            return 30;
        }
        public double calcNetOptionTotalAmountTemp(int l_intReservedDate)
        {
            return 10.5;
        }
    }
    /**
     * WEB3IfoDepositCalcForTestD
     */
    private class WEB3IfoDepositCalcForTestD extends WEB3IfoDepositCalc
    {
        public double calcBuyContractAmtTemp(int l_intReservedDate)
        {
            return 35;
        }
        public double calcSellContractAmtTemp(int l_intReservedDate)
        {
            return 40;
        }
        public double calcFuturesBuyAppraisalProfitLossTemp()
        {
            return 45;
        }
        public double calcFuturesSellAppraisalProfitLossTemp()
        {
            return 50;
        }
    }
    /**
     * WEB3IfoDepositCalcForTestE
     */
    private class WEB3IfoDepositCalcForTestE extends WEB3IfoDepositCalc
    {
        public double calcOptionBuyTotalAmountTemp(int l_intReservedDate)
        {
            double l_dblTemp = 0;
            if (l_blTest)
            {
                l_dblTemp = 5;
            }
            else
            {
                l_dblTemp = 6;
            }
            return l_dblTemp;
        }
        public double calcOptionSellTotalAmountTemp(int l_intReservedDate)
        {
            double l_dblTemp = 0;
            if (l_blTest)
            {
                l_dblTemp = 6;
            }
            else
            {
                l_dblTemp = 5;
            }
            return l_dblTemp;
        }
        public double calcReceiptIfoDepositBalanceTemp(int l_intReservedDate)
        {
            double l_dblTemp = 0;
            if (l_blTest)
            {
                l_dblTemp = 10;
            }
            else
            {
                l_dblTemp = 20;
            }
            return l_dblTemp;
        }
        public double calcIfoDepositRequiredAmountTemp(int l_intReservedDate)
        {
            double l_dblTemp = 0;
            if (l_blTest)
            {
                l_dblTemp = 15;
            }
            else
            {
                l_dblTemp = 10;
            }
            return l_dblTemp;
        }
        public double calcReceiptIfoDepositBalance(int l_intReservedDate)
        {
            double l_dblTemp = 0;
            if (l_blTest)
            {
                l_dblTemp = 10;
            }
            else
            {
                l_dblTemp = 20;
            }
            return l_dblTemp;
        }
        public double calcIfoDepositRequiredAmount(int l_intReservedDate)
        {
            double l_dblTemp = 0;
            if (l_blTest)
            {
                l_dblTemp = 15;
            }
            else
            {
                l_dblTemp = 10;
            }
            return l_dblTemp;
        }
    }
    /**
     * WEB3IfoDepositCalcForTestF
     */
    private class WEB3IfoDepositCalcForTestF extends WEB3IfoDepositCalc
    {
        public double calcIfoDepositBalance(int l_intReservedDate)
        {
            return 1;
        }
        public double calcFuturesAppraisalProfitLoss()
        {
            return 2;
        }
        public double calcFuturesBuyAppraisalProfitLoss()
        {
            return 3;
        }
        public double calcFuturesSellAppraisalProfitLoss()
        {
            return 4;
        }
        public double calcReceiptIfoDepositBalance(int l_intReservedDate)
        {
            return 5;
        }
        public double calcBuyContractQty(int l_intReservedDate)
        {
            return 6;
        }
        public double calcBuyOrderQty(int l_intReservedDate)
        {
            return 7;
        }
        public double calcSellContractQty(int l_intReservedDate)
        {
            return 8;
        }
        public double calcSellOrderQty(int l_intReservedDate)
        {
            return 9;
        }
        public WEB3IfoPositionBalance calcPositionBalance(int l_intReservedDate)
        {
            WEB3IfoPositionBalance l_positionBalance = null;
            if (l_blPrint)
            {
                l_positionBalance = null;
            }
            else
            {
                l_positionBalance = new WEB3IfoPositionBalance(10, "abc");
            }
            return l_positionBalance;
        }
        public double getSPANIfoDeposit(int l_intReservedDate)
        {
            double l_dblSpanIfoDep = 0;
            if (l_blPrint)
            {
                l_dblSpanIfoDep = -1;
            }
            else
            {
                l_dblSpanIfoDep = 11;
            }
            return l_dblSpanIfoDep;
        }
        public double calcSimpleSPANIfoDeposit(int l_intReservedDate)
        {
            return 12;
        }
        public double calcNetOptionTotalAmount(int l_intReservedDate)
        {
            return 13;
        }
        public double calcOptionBuyTotalAmount(int l_intReservedDate)
        {
            return 14;
        }
        public double calcOptionSellTotalAmount(int l_intReservedDate)
        {
            return 15;
        }
        public double calcIfoDepositRequiredAmount(int l_intReservedDate)
        {
            return 16;
        }
        public double calcIfoDepositTradingPowerAmount(int l_intReservedDate)
        {
            return 17;
        }
        public double calcIfoDepositTransferableAmount(int l_intReservedDate)
        {
            return 18;
        }
        public double calcIfoDepositTransferableAmount()
        {
            return 19;
        }
        public double calcOptionAppraisalProfitLoss()
        {
            return 20;
        }
        public double calcOptionBuyAppraisalProfitLoss()
        {
            return 21;
        }
        public double calcOptionSellAppraisalProfitLoss()
        {
            return 22;
        }
        public double getCurrentBizDateDemandAmount()
        {
            return 23;
        }
        public double calcNextBizDateDemandAmount()
        {
            return 24;
        }
        public double calcNonPayAmount()
        {
            return 25;
        }
        public double calcBuyContractAmt(int l_intReservedDate)
        {
            return 26;
        }
        public double calcSellContractAmt(int l_intReservedDate)
        {
            return 27;
        }
        public double calcFuturesAppraisalProfitLossTemp()
        {
            return 28;
        }
        public double calcFuturesBuyAppraisalProfitLossTemp()
        {
            return 29;
        }
        public double calcFuturesSellAppraisalProfitLossTemp()
        {
            return 30;
        }
        public double calcReceiptIfoDepositBalanceTemp(int l_intReservedDate)
        {
            return 31;
        }
        public double calcSimpleSPANIfoDepositTemp(int l_intReservedDate)
        {
            return 32;
        }
        public double calcNetOptionTotalAmountTemp(int l_intReservedDate)
        {
            return 33;
        }
        public double calcOptionBuyTotalAmountTemp(int l_intReservedDate)
        {
            return 34;
        }
        public double calcOptionSellTotalAmountTemp(int l_intReservedDate)
        {
            return 35;
        }
        public double calcIfoDepositRequiredAmountTemp(int l_intReservedDate)
        {
            return 36;
        }
        public double calcBuyContractAmtTemp(int l_intReservedDate)
        {
            return 37;
        }
        public double calcSellContractAmtTemp(int l_intReservedDate)
        {
            return 38;
        }
        public double calcNextBizDateDemandAmountEvening()
        {
            return 39;
        }
        public double calcNext2BizDateDemandAmount()
        {
            return 40;
        }
        public double calcIfoDepositLackChargeTemp(int l_intReservedDate)
        {
            return 41;
        }
    }
}
@
