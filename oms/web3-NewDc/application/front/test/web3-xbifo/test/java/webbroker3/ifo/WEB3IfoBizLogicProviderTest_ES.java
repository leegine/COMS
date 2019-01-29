head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.01.01.16;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3IfoBizLogicProviderTest_ES.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 先物OP計算サービス
Author Name      : Daiwa Institute of Research
Revesion History : 2007/06/14 孟亜南 (中訊)
*/
package webbroker3.ifo;

import java.sql.Timestamp;
import java.util.Calendar;

import com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ConsolidatedCommissionInfo;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Institution;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderCategEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderExpirationStatusEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderOpenStatusEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderStatusEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.QuantityTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SideEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TaxTypeEnum;
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
import com.fitechlabs.xtrade.plugin.tc.xbifo.IfoDerivativeTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.xbifo.IfoOrderExecutionConditionType;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoFinTransactionParams;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoFinTransactionRow;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoOrderExecutionParams;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoOrderUnitParams;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoOrderUnitRow;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoProductParams;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoTradedProductParams;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoTradedProductRow;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoTradedProductUpdqParams;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoTradedProductUpdqRow;

import test.util.TestDBUtility;

import webbroker3.common.WEB3BaseException;
import webbroker3.gentrade.WEB3GentradeBizDate;
import webbroker3.gentrade.WEB3GentradeCommission;
import webbroker3.gentrade.data.InstBranchProductParams;
import webbroker3.gentrade.data.InstBranchProductRow;
import webbroker3.ifo.marketadaptor.WEB3IfoMarketRequestSenderServiceImplTest;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

/**
 * 先物OP計算サービス
 * @@author 孟亜南
 */
public class WEB3IfoBizLogicProviderTest_ES extends TestBaseForMock
{
    /**
     * ログユーティリティ<BR>
     */
    private static WEB3LogUtility log =
         WEB3LogUtility.getInstance(
             WEB3IfoMarketRequestSenderServiceImplTest.class);
    

    public WEB3IfoBizLogicProviderTest_ES(String name)
    {
        super(name);
        MOCK_MANAGER.setIsMockUsed(false);
    }

    protected void tearDown() throws Exception
    {
        super.tearDown();
    }

    /**
     * create手数料
     *
     */
//    public void test_createCommission_0001()
//    {
//        final String STR_METHOD_NAME = "test_createCommission_0001()";
//        log.entering(TEST_START + STR_METHOD_NAME);
//        
//        WEB3IfoBizLogicProvider l_provider = new WEB3IfoBizLogicProvider();
//        try
//        {
//            ProductParams l_productParams = this.getProductRow();
//            IfoProductParams l_ifoProductParams = this.getIfoProductRow();
//            
//            TestDBUtility.deleteAll(IfoOrderUnitRow.TYPE);
//            IfoOrderUnitParams l_ifoOrderUnitParams = this.ifoOrderUnit();
//            
//            TestDBUtility.deleteAll(IfoProductRow.TYPE);
//            TestDBUtility.deleteAll(ProductRow.TYPE);
//            
//            TestDBUtility.insertWithDel(l_productParams);
//            TestDBUtility.insertWithDel(l_ifoProductParams);
//            
//            TestDBUtility.insertWithDel(l_ifoOrderUnitParams);
//            WEB3GentradeCommission l_commission = l_provider.createCommission(1001L);
//            assertEquals("2",l_commission.getDayTradeType());
//        }
//        catch (WEB3BaseException e)
//        {
//            log.error("", e);
//            log.exiting(TEST_END + STR_METHOD_NAME);
//            fail();
//        }
//    }
    
    /*
     * Test method for 'webbroker3.ifo.WEB3IfoBizLogicProvider.calcCommission(IfoFinTransactionRow[])'
     */
    public void testCalcCommissionIfoCase1()
    {
        final String STR_METHOD_NAME = "testCalcCommissionIfoCase1()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            TestDBUtility.deleteAll(InstitutionRow.TYPE);
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            TestDBUtility.insertWithDel(l_institutionParams);
            
            TestDBUtility.deleteAll(IfoOrderUnitRow.TYPE);
            IfoOrderUnitParams l_ifoOrderUnitParams = TestDBUtility.getIfoOrderUnitRow();
            l_ifoOrderUnitParams.setOrderUnitId(1001L);
            l_ifoOrderUnitParams.setAccountId(333812512203L);
            l_ifoOrderUnitParams.setSubAccountId(33381251220301L);
            l_ifoOrderUnitParams.setProductId(1006160060005L);
            l_ifoOrderUnitParams.setCommProductCode("10");
            TestDBUtility.insertWithDel(l_ifoOrderUnitParams);
            
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(333812512203L);
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            TestDBUtility.insertWithDel(l_mainAccountParams);
            
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setAccountId(333812512203L);
            l_subAccountParams.setSubAccountId(33381251220301L);
            
            MarketParams l_marketParams = TestDBUtility.getMarketRow();
            l_marketParams.setMarketId(3306L);
            
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setBranchId(33381);
            
            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            TestDBUtility.insertWithDel(l_subAccountParams);
            
            TestDBUtility.deleteAll(MarketRow.TYPE);
            TestDBUtility.insertWithDel(l_marketParams);
            
            TestDBUtility.deleteAll(BranchRow.TYPE);
            TestDBUtility.insertWithDel(l_branchParams);
            
            TestDBUtility.deleteAll(ProductRow.TYPE);
            ProductParams l_productParams =  TestDBUtility.getProductRow();
            l_productParams.setProductId(1006160060005L);
            TestDBUtility.insertWithDel(l_productParams);
            
            IfoProductParams l_ifoProductParams = TestDBUtility.getIfoProductRow();
            l_ifoProductParams.setProductId(1006160060005L);
            TestDBUtility.insertWithDel(l_ifoProductParams);
            
            TestDBUtility.deleteAll(TradedProductRow.TYPE);
            TradedProductParams l_tradedProductParams = TestDBUtility.getTradedProductRow();
            l_tradedProductParams.setTradedProductId(330304148080002L);
            l_tradedProductParams.setProductId(1006160060005L);
            l_tradedProductParams.setMarketId(3306L);
            TestDBUtility.insertWithDel(l_tradedProductParams);
            
            TestDBUtility.deleteAll(IfoTradedProductRow.TYPE);
            IfoTradedProductParams l_IfoTradedProductParams = TestDBUtility.getIfoTradedProductRow();
            l_IfoTradedProductParams.setProductId(1006160060005L);
            l_IfoTradedProductParams.setTradedProductId(330304148080002L);
            l_IfoTradedProductParams.setMarketId(3306L);
            l_IfoTradedProductParams.setUnitSize(1);//指数乗数は先物OP取引銘柄マスタ.１単位当り指数乗数
            l_IfoTradedProductParams.setValidForBizDate(null);
            TestDBUtility.insertWithDel(l_IfoTradedProductParams);
            
            TestDBUtility.deleteAll(IfoTradedProductUpdqRow.TYPE);
            IfoTradedProductUpdqParams l_ifoTradedProductUpdqParams = TestDBUtility.getIfoTradedProductUpdqRow();
            l_ifoTradedProductUpdqParams.setTradedProductId(330304148080002L);
            l_ifoTradedProductUpdqParams.setMarketId(3306L);
            l_ifoTradedProductUpdqParams.setValidForBizDate(
                WEB3DateUtility.formatDate(new WEB3GentradeBizDate(
                        new Timestamp(GtlUtils.getSystemTimestamp().getTime())).roll(1), "yyyyMMdd"));
            TestDBUtility.insertWithDel(l_ifoTradedProductUpdqParams);
            
            
            InstBranchProductParams l_InstBranchProductParams = new InstBranchProductParams();
            l_InstBranchProductParams.setBranchId(33381);
            l_InstBranchProductParams.setCommissionProductCode("10");
            TestDBUtility.deleteAll(InstBranchProductRow.TYPE);
            TestDBUtility.insertWithDel(l_InstBranchProductParams);
            
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        
        try
        {
            WEB3IfoTradedProductImpl l_tradedProduct = new WEB3IfoTradedProductImpl(330304148080002L);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.ifo.WEB3IfoProductManagerImpl",
                    "getIfoTradedProduct",
                    new Class[] { Institution.class, String.class, String.class, String.class,
                            IfoDerivativeTypeEnum.class, double.class, String.class, String.class },
                            l_tradedProduct);
                    
            IfoFinTransactionParams l_ifoFinTransactionParams0 = new IfoFinTransactionParams();
            l_ifoFinTransactionParams0.setProductId(1006160060005L);
            l_ifoFinTransactionParams0.setMarketId(3306L);
            l_ifoFinTransactionParams0.setPrice(10000);
            l_ifoFinTransactionParams0.setQuantity(10);
            l_ifoFinTransactionParams0.setAccountId(333812512203L);
            l_ifoFinTransactionParams0.setSubAccountId(33381251220301L);
            l_ifoFinTransactionParams0.setOrderUnitId(1001L);
            IfoFinTransactionParams l_ifoFinTransactionParams1 = new IfoFinTransactionParams();
            l_ifoFinTransactionParams1.setProductId(1006160060005L);
            l_ifoFinTransactionParams1.setMarketId(3306L);
            l_ifoFinTransactionParams1.setPrice(12000);
            l_ifoFinTransactionParams1.setQuantity(3);
            l_ifoFinTransactionParams1.setAccountId(333812512203L);
            l_ifoFinTransactionParams1.setSubAccountId(33381251220301L);
            l_ifoFinTransactionParams1.setOrderUnitId(1001L);
            
            IfoFinTransactionParams l_ifoFinTransactionParams2 = new IfoFinTransactionParams();
            l_ifoFinTransactionParams2.setProductId(1006160060005L);
            l_ifoFinTransactionParams2.setMarketId(3306L);
            l_ifoFinTransactionParams2.setPrice(10000);
            l_ifoFinTransactionParams2.setQuantity(10);
            l_ifoFinTransactionParams2.setAccountId(333812512203L);
            l_ifoFinTransactionParams2.setSubAccountId(33381251220301L);
            l_ifoFinTransactionParams2.setOrderUnitId(1001L);
            
            WEB3IfoBizLogicProviderForTest l_provider = new WEB3IfoBizLogicProviderForTest();
            IfoFinTransactionRow[] l_arrIfoFinTransactionRow = new IfoFinTransactionRow[3];
            l_arrIfoFinTransactionRow[0] = l_ifoFinTransactionParams0;
            l_arrIfoFinTransactionRow[1] = l_ifoFinTransactionParams1;
            l_arrIfoFinTransactionRow[2] = l_ifoFinTransactionParams2;
            
            ConsolidatedCommissionInfo l_ConsolidatedCommissionInfo =
                l_provider.calcCommission(l_arrIfoFinTransactionRow);
            
            assertEquals(l_ConsolidatedCommissionInfo.getTotalCommission() + "", "3000.0");
            assertEquals(l_ConsolidatedCommissionInfo.getTotalSalesTax() + "", "91.0");
            
        }
        catch (WEB3BaseException l_ex)
        {
            log.error("", l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        catch (Exception l_ex)
        {
            log.error("", l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    public void testCalcCommissionIfoCase2()
    {
        final String STR_METHOD_NAME = "testCalcCommissionIfoCase2()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            TestDBUtility.deleteAll(IfoOrderUnitRow.TYPE);
            IfoOrderUnitParams l_ifoOrderUnitParams = TestDBUtility.getIfoOrderUnitRow();
            l_ifoOrderUnitParams.setOrderUnitId(1001L);
            l_ifoOrderUnitParams.setAccountId(333812512203L);
            l_ifoOrderUnitParams.setSubAccountId(33381251220301L);
            l_ifoOrderUnitParams.setProductId(1006160060005L);
            l_ifoOrderUnitParams.setCommProductCode("10");
            TestDBUtility.insertWithDel(l_ifoOrderUnitParams);
            
//            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
//            l_mainAccountParams.setAccountId(333812512203L);
//            TestDBUtility.deleteAll(MainAccountRow.TYPE);
//            TestDBUtility.insertWithDel(l_mainAccountParams);
            
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setAccountId(333812512203L);
            l_subAccountParams.setSubAccountId(33381251220301L);
            
            MarketParams l_marketParams = TestDBUtility.getMarketRow();
            l_marketParams.setMarketId(3306L);
            
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setBranchId(33381);
            
            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            TestDBUtility.insertWithDel(l_subAccountParams);
            
            TestDBUtility.deleteAll(MarketRow.TYPE);
            TestDBUtility.insertWithDel(l_marketParams);
            
            TestDBUtility.deleteAll(BranchRow.TYPE);
            TestDBUtility.insertWithDel(l_branchParams);
            
            TestDBUtility.deleteAll(ProductRow.TYPE);
            ProductParams l_productParams =  TestDBUtility.getProductRow();
            l_productParams.setProductId(1006160060005L);
            TestDBUtility.insertWithDel(l_productParams);
            
            IfoProductParams l_ifoProductParams = TestDBUtility.getIfoProductRow();
            l_ifoProductParams.setProductId(1006160060005L);
            TestDBUtility.insertWithDel(l_ifoProductParams);
            
            TestDBUtility.deleteAll(TradedProductRow.TYPE);
            TradedProductParams l_tradedProductParams = TestDBUtility.getTradedProductRow();
            l_tradedProductParams.setTradedProductId(330304148080002L);
            l_tradedProductParams.setProductId(1006160060005L);
            l_tradedProductParams.setMarketId(3306L);
            TestDBUtility.insertWithDel(l_tradedProductParams);
            
            TestDBUtility.deleteAll(IfoTradedProductRow.TYPE);
            IfoTradedProductParams l_IfoTradedProductParams = TestDBUtility.getIfoTradedProductRow();
            l_IfoTradedProductParams.setProductId(1006160060005L);
            l_IfoTradedProductParams.setTradedProductId(330304148080002L);
            l_IfoTradedProductParams.setMarketId(3306L);
            l_IfoTradedProductParams.setUnitSize(1);//指数乗数は先物OP取引銘柄マスタ.１単位当り指数乗数
            l_IfoTradedProductParams.setValidForBizDate(null);
            TestDBUtility.insertWithDel(l_IfoTradedProductParams);
            
            TestDBUtility.deleteAll(IfoTradedProductUpdqRow.TYPE);
            IfoTradedProductUpdqParams l_ifoTradedProductUpdqParams = TestDBUtility.getIfoTradedProductUpdqRow();
            l_ifoTradedProductUpdqParams.setTradedProductId(330304148080002L);
            l_ifoTradedProductUpdqParams.setMarketId(3306L);
            l_ifoTradedProductUpdqParams.setValidForBizDate(
                WEB3DateUtility.formatDate(new WEB3GentradeBizDate(
                        new Timestamp(GtlUtils.getSystemTimestamp().getTime())).roll(1), "yyyyMMdd"));
            TestDBUtility.insertWithDel(l_ifoTradedProductUpdqParams);
            
//            
//            InstBranchProductParams l_InstBranchProductParams = new InstBranchProductParams();
//            l_InstBranchProductParams.setBranchId(33381);
//            l_InstBranchProductParams.setCommissionProductCode("10");
//            TestDBUtility.deleteAll(InstBranchProductRow.TYPE);
//            TestDBUtility.insertWithDel(l_InstBranchProductParams);
            WEB3IfoTradedProductImpl l_tradedProduct = new WEB3IfoTradedProductImpl(330304148080002L);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.ifo.WEB3IfoProductManagerImpl",
                    "getIfoTradedProduct",
                    new Class[] { Institution.class, String.class, String.class, String.class,
                            IfoDerivativeTypeEnum.class, double.class, String.class, String.class },
                            l_tradedProduct);
            
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        
        try
        {
            IfoFinTransactionParams l_ifoFinTransactionParams0 = new IfoFinTransactionParams();
            l_ifoFinTransactionParams0.setProductId(1006160060005L);
            l_ifoFinTransactionParams0.setMarketId(3306L);
            l_ifoFinTransactionParams0.setPrice(10000);
            l_ifoFinTransactionParams0.setQuantity(10);
            l_ifoFinTransactionParams0.setAccountId(333812512203L);
            l_ifoFinTransactionParams0.setSubAccountId(33381251220301L);
            l_ifoFinTransactionParams0.setOrderUnitId(1001L);
            
            IfoFinTransactionParams l_ifoFinTransactionParams1 = new IfoFinTransactionParams();
            l_ifoFinTransactionParams1.setProductId(1006160060005L);
            l_ifoFinTransactionParams1.setMarketId(3306L);
            l_ifoFinTransactionParams1.setPrice(12000);//約定単価
            l_ifoFinTransactionParams1.setQuantity(3);//約定数量
            l_ifoFinTransactionParams1.setAccountId(333812512203L);
            l_ifoFinTransactionParams1.setSubAccountId(33381251220301L);
            l_ifoFinTransactionParams1.setOrderUnitId(1001L);
            
            IfoFinTransactionParams l_ifoFinTransactionParams2 = new IfoFinTransactionParams();
            l_ifoFinTransactionParams2.setProductId(1006160060005L);
            l_ifoFinTransactionParams2.setMarketId(3306L);
            l_ifoFinTransactionParams2.setPrice(10000);
            l_ifoFinTransactionParams2.setQuantity(10);
            l_ifoFinTransactionParams2.setAccountId(333812512203L);
            l_ifoFinTransactionParams2.setSubAccountId(33381251220301L);
            l_ifoFinTransactionParams2.setOrderUnitId(1001L);
            
            WEB3IfoBizLogicProviderForTest1 l_provider = new WEB3IfoBizLogicProviderForTest1();
            IfoFinTransactionRow[] l_arrIfoFinTransactionRow = new IfoFinTransactionRow[3];
            l_arrIfoFinTransactionRow[0] = l_ifoFinTransactionParams0;
            l_arrIfoFinTransactionRow[1] = l_ifoFinTransactionParams1;
            l_arrIfoFinTransactionRow[2] = l_ifoFinTransactionParams2;
            
            ConsolidatedCommissionInfo l_ConsolidatedCommissionInfo =
                l_provider.calcCommission(l_arrIfoFinTransactionRow);
            
            assertEquals(l_ConsolidatedCommissionInfo.getTotalCommission() + "", "15000.0");
            assertEquals(l_ConsolidatedCommissionInfo.getTotalSalesTax() + "", "450.0");
        }
        catch (WEB3BaseException l_ex)
        {
            log.error("", l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    public class WEB3IfoBizLogicProviderForTest extends WEB3IfoBizLogicProvider
    {
        public ConsolidatedCommissionInfo calcCommissionAmount(
            IfoFinTransactionRow[] l_arrIfoFinTransactionRows,
            WEB3GentradeCommission l_commission,
            double[] l_dblAmounts) throws WEB3BaseException
        {
            double[] l_dbCommissions = new double[3];
            double[] l_dbSalesTaxs = new double[3];
            ConsolidatedCommissionInfo l_ConsolidatedCommissionInfo =
                new ConsolidatedCommissionInfo(
                    l_dbCommissions,
                    3000,
                    l_dbSalesTaxs,
                    91);
            return l_ConsolidatedCommissionInfo;
        }
        
        public ConsolidatedCommissionInfo calcCommissionQuantity(
            IfoFinTransactionRow[] l_arrIfoFinTransactionRows,
            WEB3GentradeCommission l_commission,
            double[] l_dblQuantitys) throws WEB3BaseException
        {
            double[] l_dbCommissions = new double[3];
            double[] l_dbSalesTaxs = new double[3];
            ConsolidatedCommissionInfo l_ConsolidatedCommissionInfo =
                new ConsolidatedCommissionInfo(
                    l_dbCommissions,
                    15000,
                    l_dbSalesTaxs,
                    450);
            return l_ConsolidatedCommissionInfo;
        }
        
        public void calcCommission(WEB3GentradeCommission l_commission, SubAccount l_subAccount) 
        throws WEB3BaseException
        {
            l_commission.setCommission(10000);
            l_commission.setCommitionPerUnit(0);
        }
        public double calcSalesTax(double l_dblAmount, Timestamp l_tsBaseDate, SubAccount l_subAccount) 
        throws WEB3BaseException
        {
                return 100;
        }
    }
    public class WEB3IfoBizLogicProviderForTest1 extends WEB3IfoBizLogicProvider
    {
        public ConsolidatedCommissionInfo calcCommissionAmount(
            IfoFinTransactionRow[] l_arrIfoFinTransactionRows,
            WEB3GentradeCommission l_commission,
            double[] l_dblAmounts) throws WEB3BaseException
        {
            double[] l_dbCommissions = new double[3];
            double[] l_dbSalesTaxs = new double[3];
            ConsolidatedCommissionInfo l_ConsolidatedCommissionInfo =
                new ConsolidatedCommissionInfo(
                    l_dbCommissions,
                    3000,
                    l_dbSalesTaxs,
                    91);
            return l_ConsolidatedCommissionInfo;
        }
        
        public ConsolidatedCommissionInfo calcCommissionQuantity(
            IfoFinTransactionRow[] l_arrIfoFinTransactionRows,
            WEB3GentradeCommission l_commission,
            double[] l_dblQuantitys) throws WEB3BaseException
        {
            double[] l_dbCommissions = new double[3];
            double[] l_dbSalesTaxs = new double[3];
            ConsolidatedCommissionInfo l_ConsolidatedCommissionInfo =
                new ConsolidatedCommissionInfo(
                    l_dbCommissions,
                    15000,
                    l_dbSalesTaxs,
                    450);
            return l_ConsolidatedCommissionInfo;
        }
        
        public void calcCommission(WEB3GentradeCommission l_commission, SubAccount l_subAccount) 
        throws WEB3BaseException
        {
            l_commission.setCommission(10000);
            l_commission.setCommitionPerUnit(10);
        }
        public double calcSalesTax(double l_dblAmount, Timestamp l_tsBaseDate, SubAccount l_subAccount) 
        throws WEB3BaseException
        {
                return 100;
        }
    }
    
    /**
     * calc手数料（枚数按分）
     * 
     */
    public void test_calcCommissionQuantity_0001()
    {
        final String STR_METHOD_NAME = "test_calcCommissionQuantity_0001()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        WEB3IfoBizLogicProvider l_provider = new WEB3IfoBizLogicProvider();
        
        //トランザクション（取引勘定明細）
        IfoFinTransactionParams l_params1 = new IfoFinTransactionParams();
//        l_params1.setQuantity(45);
        l_params1.setOrderExecutionId(100);
        IfoFinTransactionParams l_params2 = new IfoFinTransactionParams();
//        l_params2.setQuantity(56);
        l_params2.setOrderExecutionId(200);
        IfoFinTransactionParams l_params3 = new IfoFinTransactionParams();
//        l_params3.setQuantity(56);
        l_params3.setOrderExecutionId(300);
        
        IfoFinTransactionRow[] l_arrIfoFinTransactionRows = {l_params1, l_params2, l_params3};
        double[] l_dblQuantitys = {45D, 56D, 56D};
        WEB3GentradeCommission l_commission = new WEB3GentradeCommission();
        //［合　@ 計］委託手数料
        l_commission.setCommission(345000);
        //［合　@計］約定数量
        l_commission.setQuantity(157);
        //［合　@ 計］消費税
        l_commission.setConsumptionTax(10350);
        
        try
        {
            TestDBUtility.deleteAll(IfoOrderExecutionParams.TYPE);
            //1
            IfoOrderExecutionParams l_ifoOrderExecutionParams1 = new IfoOrderExecutionParams();
            l_ifoOrderExecutionParams1.setOrderExecutionId(100);
            l_ifoOrderExecutionParams1.setAccountId(1);
            l_ifoOrderExecutionParams1.setSubAccountId(11);
            l_ifoOrderExecutionParams1.setBranchId(111);
            l_ifoOrderExecutionParams1.setOrderId(1111);
            l_ifoOrderExecutionParams1.setOrderUnitId(11111);
            l_ifoOrderExecutionParams1.setOrderType(OrderTypeEnum.ASSET_IN);
            l_ifoOrderExecutionParams1.setProductType(ProductTypeEnum.IFO);
            l_ifoOrderExecutionParams1.setDeliveryDate(Calendar.getInstance().getTime());
            //約定順番号
            l_ifoOrderExecutionParams1.setExecSerialNo(1);
            //約定数量
            l_ifoOrderExecutionParams1.setExecQuantity(45);
            
            l_ifoOrderExecutionParams1.setExecTimestamp(Calendar.getInstance().getTime());
            l_ifoOrderExecutionParams1.setDeleteFlag(BooleanEnum.TRUE);
            l_ifoOrderExecutionParams1.setBizDate("20071012");
            l_ifoOrderExecutionParams1.setProductId(1111111);
            l_ifoOrderExecutionParams1.setCreatedTimestamp(Calendar.getInstance().getTime());
            l_ifoOrderExecutionParams1.setLastUpdatedTimestamp(Calendar.getInstance().getTime());
            TestDBUtility.insertWithDel(l_ifoOrderExecutionParams1);
            
            //2
            IfoOrderExecutionParams l_ifoOrderExecutionParams2 = new IfoOrderExecutionParams();
            l_ifoOrderExecutionParams2.setOrderExecutionId(200);
            l_ifoOrderExecutionParams2.setAccountId(2);
            l_ifoOrderExecutionParams2.setSubAccountId(22);
            l_ifoOrderExecutionParams2.setBranchId(222);
            l_ifoOrderExecutionParams2.setOrderId(2222);
            l_ifoOrderExecutionParams2.setOrderUnitId(22222);
            l_ifoOrderExecutionParams2.setOrderType(OrderTypeEnum.ASSET_IN);
            l_ifoOrderExecutionParams2.setProductType(ProductTypeEnum.IFO);
            l_ifoOrderExecutionParams2.setDeliveryDate(Calendar.getInstance().getTime());
            //約定順番号
            l_ifoOrderExecutionParams2.setExecSerialNo(2);
            //約定数量
            l_ifoOrderExecutionParams2.setExecQuantity(56);
            
            l_ifoOrderExecutionParams2.setExecTimestamp(Calendar.getInstance().getTime());
            l_ifoOrderExecutionParams2.setDeleteFlag(BooleanEnum.TRUE);
            l_ifoOrderExecutionParams2.setBizDate("20071012");
            l_ifoOrderExecutionParams2.setProductId(222222);
            l_ifoOrderExecutionParams2.setCreatedTimestamp(Calendar.getInstance().getTime());
            l_ifoOrderExecutionParams2.setLastUpdatedTimestamp(Calendar.getInstance().getTime());
            TestDBUtility.insertWithDel(l_ifoOrderExecutionParams2);
            
            //3
            IfoOrderExecutionParams l_ifoOrderExecutionParams3 = new IfoOrderExecutionParams();
            l_ifoOrderExecutionParams3.setOrderExecutionId(300);
            l_ifoOrderExecutionParams3.setAccountId(3);
            l_ifoOrderExecutionParams3.setSubAccountId(33);
            l_ifoOrderExecutionParams3.setBranchId(333);
            l_ifoOrderExecutionParams3.setOrderId(3333);
            l_ifoOrderExecutionParams3.setOrderUnitId(33333);
            l_ifoOrderExecutionParams3.setOrderType(OrderTypeEnum.ASSET_IN);
            l_ifoOrderExecutionParams3.setProductType(ProductTypeEnum.IFO);
            l_ifoOrderExecutionParams3.setDeliveryDate(Calendar.getInstance().getTime());
            //約定順番号
            l_ifoOrderExecutionParams3.setExecSerialNo(3);
            //約定数量
            l_ifoOrderExecutionParams3.setExecQuantity(56);
            
            l_ifoOrderExecutionParams3.setExecTimestamp(Calendar.getInstance().getTime());
            l_ifoOrderExecutionParams3.setDeleteFlag(BooleanEnum.TRUE);
            l_ifoOrderExecutionParams3.setBizDate("20071012");
            l_ifoOrderExecutionParams3.setProductId(333333);
            l_ifoOrderExecutionParams3.setCreatedTimestamp(Calendar.getInstance().getTime());
            l_ifoOrderExecutionParams3.setLastUpdatedTimestamp(Calendar.getInstance().getTime());
            TestDBUtility.insertWithDel(l_ifoOrderExecutionParams3);
            
            ConsolidatedCommissionInfo l_info =
                l_provider.calcCommissionQuantity(l_arrIfoFinTransactionRows, l_commission, l_dblQuantitys);
            
            assertEquals("98885.0" , "" + l_info.getCommission(0));
            assertEquals("123057.0" , "" + l_info.getCommission(1));
            assertEquals("123058.0" , "" + l_info.getCommission(2));
            
            assertEquals("2966.0" , "" + l_info.getSalesTax(0));
            assertEquals("3691.0" , "" + l_info.getSalesTax(1));
            assertEquals("3693.0" , "" + l_info.getSalesTax(2));
            
        }
        catch (WEB3BaseException e)
        {
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    /**
     * calc手数料（代金按分）
     * 
     */
    public void test_calcCommissionAmount_0001()
    {
        final String STR_METHOD_NAME = "test_calcCommissionAmount_0001()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        WEB3IfoBizLogicProvider l_provider = new WEB3IfoBizLogicProvider();
        
        //トランザクション（取引勘定明細）
        IfoFinTransactionParams l_params1 = new IfoFinTransactionParams();
        l_params1.setOrderExecutionId(100);
        l_params1.setPrice(10);
        IfoFinTransactionParams l_params2 = new IfoFinTransactionParams();
        l_params2.setOrderExecutionId(200);
        l_params2.setPrice(11);
        IfoFinTransactionParams l_params3 = new IfoFinTransactionParams();
        l_params3.setOrderExecutionId(300);
        l_params3.setPrice(12);
        IfoFinTransactionParams l_params4 = new IfoFinTransactionParams();
        l_params4.setOrderExecutionId(400);
        l_params4.setPrice(12);
        
        IfoFinTransactionRow[] l_arrIfoFinTransactionRows = {l_params1, l_params2, l_params3, l_params4};
        double[] l_dblQuantitys = {13000D, 14000D, 16000D, 16000D};
        WEB3GentradeCommission l_commission = new WEB3GentradeCommission();
        //［合　@ 計］委託手数料
        l_commission.setCommission(1380);
        //［合　@計］約定数量
        l_commission.setExpensesCalcAmount(59000);
        //［合　@ 計］消費税
        l_commission.setConsumptionTax(51);
        
        try
        {
            TestDBUtility.deleteAll(IfoOrderExecutionParams.TYPE);
            //1
            IfoOrderExecutionParams l_ifoOrderExecutionParams1 = new IfoOrderExecutionParams();
            l_ifoOrderExecutionParams1.setOrderExecutionId(100);
            l_ifoOrderExecutionParams1.setAccountId(1);
            l_ifoOrderExecutionParams1.setSubAccountId(11);
            l_ifoOrderExecutionParams1.setBranchId(111);
            l_ifoOrderExecutionParams1.setOrderId(1111);
            l_ifoOrderExecutionParams1.setOrderUnitId(11111);
            l_ifoOrderExecutionParams1.setOrderType(OrderTypeEnum.ASSET_IN);
            l_ifoOrderExecutionParams1.setProductType(ProductTypeEnum.IFO);
            l_ifoOrderExecutionParams1.setDeliveryDate(Calendar.getInstance().getTime());
            //約定順番号
            l_ifoOrderExecutionParams1.setExecSerialNo(1);
            //約定数量
            l_ifoOrderExecutionParams1.setExecQuantity(56);
            
            l_ifoOrderExecutionParams1.setExecTimestamp(Calendar.getInstance().getTime());
            l_ifoOrderExecutionParams1.setDeleteFlag(BooleanEnum.TRUE);
            l_ifoOrderExecutionParams1.setBizDate("20071012");
            l_ifoOrderExecutionParams1.setProductId(1111111);
            l_ifoOrderExecutionParams1.setCreatedTimestamp(Calendar.getInstance().getTime());
            l_ifoOrderExecutionParams1.setLastUpdatedTimestamp(Calendar.getInstance().getTime());
            TestDBUtility.insertWithDel(l_ifoOrderExecutionParams1);
            
            //2
            IfoOrderExecutionParams l_ifoOrderExecutionParams2 = new IfoOrderExecutionParams();
            l_ifoOrderExecutionParams2.setOrderExecutionId(200);
            l_ifoOrderExecutionParams2.setAccountId(2);
            l_ifoOrderExecutionParams2.setSubAccountId(22);
            l_ifoOrderExecutionParams2.setBranchId(222);
            l_ifoOrderExecutionParams2.setOrderId(2222);
            l_ifoOrderExecutionParams2.setOrderUnitId(22222);
            l_ifoOrderExecutionParams2.setOrderType(OrderTypeEnum.ASSET_IN);
            l_ifoOrderExecutionParams2.setProductType(ProductTypeEnum.IFO);
            l_ifoOrderExecutionParams2.setDeliveryDate(Calendar.getInstance().getTime());
            //約定順番号
            l_ifoOrderExecutionParams2.setExecSerialNo(2);
            //約定数量
            l_ifoOrderExecutionParams2.setExecQuantity(45);
            
            l_ifoOrderExecutionParams2.setExecTimestamp(Calendar.getInstance().getTime());
            l_ifoOrderExecutionParams2.setDeleteFlag(BooleanEnum.TRUE);
            l_ifoOrderExecutionParams2.setBizDate("20071012");
            l_ifoOrderExecutionParams2.setProductId(222222);
            l_ifoOrderExecutionParams2.setCreatedTimestamp(Calendar.getInstance().getTime());
            l_ifoOrderExecutionParams2.setLastUpdatedTimestamp(Calendar.getInstance().getTime());
            TestDBUtility.insertWithDel(l_ifoOrderExecutionParams2);
            
            //3
            IfoOrderExecutionParams l_ifoOrderExecutionParams3 = new IfoOrderExecutionParams();
            l_ifoOrderExecutionParams3.setOrderExecutionId(300);
            l_ifoOrderExecutionParams3.setAccountId(3);
            l_ifoOrderExecutionParams3.setSubAccountId(33);
            l_ifoOrderExecutionParams3.setBranchId(333);
            l_ifoOrderExecutionParams3.setOrderId(3333);
            l_ifoOrderExecutionParams3.setOrderUnitId(33333);
            l_ifoOrderExecutionParams3.setOrderType(OrderTypeEnum.ASSET_IN);
            l_ifoOrderExecutionParams3.setProductType(ProductTypeEnum.IFO);
            l_ifoOrderExecutionParams3.setDeliveryDate(Calendar.getInstance().getTime());
            //約定順番号
            l_ifoOrderExecutionParams3.setExecSerialNo(3);
            //約定数量
            l_ifoOrderExecutionParams3.setExecQuantity(56);
            
            l_ifoOrderExecutionParams3.setExecTimestamp(Calendar.getInstance().getTime());
            l_ifoOrderExecutionParams3.setDeleteFlag(BooleanEnum.TRUE);
            l_ifoOrderExecutionParams3.setBizDate("20071012");
            l_ifoOrderExecutionParams3.setProductId(333333);
            l_ifoOrderExecutionParams3.setCreatedTimestamp(Calendar.getInstance().getTime());
            l_ifoOrderExecutionParams3.setLastUpdatedTimestamp(Calendar.getInstance().getTime());
            TestDBUtility.insertWithDel(l_ifoOrderExecutionParams3);
            
            //4
            IfoOrderExecutionParams l_ifoOrderExecutionParams4 = new IfoOrderExecutionParams();
            l_ifoOrderExecutionParams4.setOrderExecutionId(400);
            l_ifoOrderExecutionParams4.setAccountId(4);
            l_ifoOrderExecutionParams4.setSubAccountId(44);
            l_ifoOrderExecutionParams4.setBranchId(444);
            l_ifoOrderExecutionParams4.setOrderId(4444);
            l_ifoOrderExecutionParams4.setOrderUnitId(44444);
            l_ifoOrderExecutionParams4.setOrderType(OrderTypeEnum.ASSET_IN);
            l_ifoOrderExecutionParams4.setProductType(ProductTypeEnum.IFO);
            l_ifoOrderExecutionParams4.setDeliveryDate(Calendar.getInstance().getTime());
            //約定順番号
            l_ifoOrderExecutionParams4.setExecSerialNo(4);
            //約定数量
            l_ifoOrderExecutionParams4.setExecQuantity(56);
            
            l_ifoOrderExecutionParams4.setExecTimestamp(Calendar.getInstance().getTime());
            l_ifoOrderExecutionParams4.setDeleteFlag(BooleanEnum.TRUE);
            l_ifoOrderExecutionParams4.setBizDate("20071012");
            l_ifoOrderExecutionParams4.setProductId(444444);
            l_ifoOrderExecutionParams4.setCreatedTimestamp(Calendar.getInstance().getTime());
            l_ifoOrderExecutionParams4.setLastUpdatedTimestamp(Calendar.getInstance().getTime());
            TestDBUtility.insertWithDel(l_ifoOrderExecutionParams4);
            
            ConsolidatedCommissionInfo l_info =
                l_provider.calcCommissionAmount(l_arrIfoFinTransactionRows, l_commission, l_dblQuantitys);
            
            assertEquals("304.0" , "" + l_info.getCommission(0));
            assertEquals("327.0" , "" + l_info.getCommission(1));
            assertEquals("374.0" , "" + l_info.getCommission(2));
            assertEquals("375.0" , "" + l_info.getCommission(3));
            
            assertEquals("11.0" , "" + l_info.getSalesTax(0));
            assertEquals("12.0" , "" + l_info.getSalesTax(1));
            assertEquals("13.0" , "" + l_info.getSalesTax(2));
            assertEquals("15.0" , "" + l_info.getSalesTax(3));
            
        }
        catch (WEB3BaseException e)
        {
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    public IfoOrderUnitParams ifoOrderUnit()
    {
        IfoOrderUnitParams l_params = new IfoOrderUnitParams();
        l_params.setOrderUnitId(1001);
        l_params.setAccountId(101001010010L);
        l_params.setSubAccountId(10100101001007L);
        l_params.setBranchId(33381);
        l_params.setTraderId(null);
        l_params.setOrderId(1001);
        l_params.setOrderType(OrderTypeEnum.IDX_FUTURES_BUY_TO_OPEN);
        l_params.setOrderCateg(OrderCategEnum.IDX_FUTURES_OPEN);
        l_params.setLastOrderActionSerialNo(1);
        l_params.setLastExecutionSerialNo(0);
        l_params.setProductType(ProductTypeEnum.IFO);
        l_params.setFutureOptionDiv("1");
        l_params.setMarketId(1002);
        l_params.setQuantity(100);
        l_params.setLimitPrice(0);
        l_params.setExecutionConditionType(IfoOrderExecutionConditionType.NONE);
        l_params.setOrderConditionType("2");
        l_params.setOrderCondOperator(null);
        l_params.setStopPriceType(null);
        l_params.setStopOrderPrice(null);
        l_params.setWLimitPrice(null);
        l_params.setDeliveryDate(WEB3DateUtility.getDate("20040101", "yyyyMMdd"));
        l_params.setOrderStatus(OrderStatusEnum.ACCEPTED);
        l_params.setOrderOpenStatus(OrderOpenStatusEnum.OPEN);
        l_params.setExpirationStatus(OrderExpirationStatusEnum.EXPIRED);
        l_params.setTaxType(TaxTypeEnum.NORMAL);
        l_params.setBizDate("20040101");
        l_params.setProductId(1006169090018L);
        l_params.setCreatedTimestamp(WEB3DateUtility.getDate("20040101", "yyyyMMdd"));
        l_params.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20040101", "yyyyMMdd"));
        l_params.setOrderRequestNumber("000003006");
        l_params.setConfirmedOrderRev("2");
        l_params.setOrderRev("1");
        l_params.setDayTradeType("2");
        
        l_params.setConfirmedExecConditionType(IfoOrderExecutionConditionType.NONE);
        l_params.setConfirmedQuantity(100);
        return l_params;
    }
    
    /**
     * 銘柄Rowを作成.<BR>
     */
    public static ProductParams getProductRow()
    {
        ProductParams l_productParams = new ProductParams();

        l_productParams.setProductId(1006169090018L);
        l_productParams.setInstitutionCode("0D");
        l_productParams.setProductType(ProductTypeEnum.IFO);
        l_productParams.setStandardName("シンセンテルス");
        l_productParams.setLotSize(0.000000D);
        l_productParams.setCalcSize(1.000000D);
        l_productParams.setEstimationPrice(0.000000D);
        l_productParams.setMarginRatio(0.000000D);
        l_productParams.setSecuritiesEstimationRatio(0.000000D);
        l_productParams.setCreatedTimestamp(Calendar.getInstance().getTime());
        l_productParams.setLastUpdatedTimestamp(Calendar.getInstance().getTime());

        return l_productParams;
    }
    
    /**
     * 先物OP銘柄テーブル (ifo_product)
     */
    public static IfoProductParams getIfoProductRow()
    {
        IfoProductParams l_ifoProductParams = new IfoProductParams();
        l_ifoProductParams.setProductId(1006169090018L);
        l_ifoProductParams.setInstitutionCode("10");
        l_ifoProductParams.setProductCode("160030005");
        l_ifoProductParams.setUnderlyingProductCode("0005");
        l_ifoProductParams.setDerivativeType(IfoDerivativeTypeEnum.FUTURES);
        l_ifoProductParams.setStrikePrice(0);
        l_ifoProductParams.setMonthOfDelivery("200503");
        l_ifoProductParams.setExerciseDate(WEB3DateUtility.getDate("20070112","yyyyMMdd"));
        l_ifoProductParams.setCreatedTimestamp(Calendar.getInstance().getTime());
        l_ifoProductParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070101","yyyyMMdd"));
        return l_ifoProductParams;
    }
    
  public void testCalcDeliveryAmount_C0001()
  {
      final String STR_METHOD_NAME = "testCalcDeliveryAmount_C0001()";
      log.entering(TEST_START + STR_METHOD_NAME);
      
      WEB3IfoBizLogicProvider l_provider = new WEB3IfoBizLogicProvider();
      try
      {
          SideEnum l_dealing = SideEnum.BUY;
          double l_dblExpenseCalculationAmount = 2.2d;
          double l_dblConsignmentCommission = 2.2d;
          double l_dblCommConsumptionTax = 2.2d;
          
          double l_dblReturnValue = 
              l_provider.calcDeliveryAmount(
                  l_dealing,
                  l_dblExpenseCalculationAmount,
                  l_dblConsignmentCommission,
                  l_dblCommConsumptionTax);

          assertEquals(6.6, l_dblReturnValue, 0);
      }
      catch (Exception l_ex)
      {
          log.error(l_ex.getMessage(), l_ex);
          fail();
      }
      
      log.exiting(TEST_END + STR_METHOD_NAME);
  }
  
  public void testCalcDeliveryAmount_C0002()
  {
      final String STR_METHOD_NAME = "testCalcDeliveryAmount_C0002()";
      log.entering(TEST_START + STR_METHOD_NAME);
      
      WEB3IfoBizLogicProvider l_provider = new WEB3IfoBizLogicProvider();
      try
      {
          SideEnum l_dealing = SideEnum.SELL;
          double l_dblExpenseCalculationAmount = 2.4d;
          double l_dblConsignmentCommission = 0.6d;
          double l_dblCommConsumptionTax = 0.5d;
          
          double l_dblReturnValue = 
              l_provider.calcDeliveryAmount(
                  l_dealing,
                  l_dblExpenseCalculationAmount,
                  l_dblConsignmentCommission,
                  l_dblCommConsumptionTax);

          assertEquals(1.3, l_dblReturnValue, 0);
      }
      catch (Exception l_ex)
      {
          log.error(l_ex.getMessage(), l_ex);
          fail();
      }
      
      log.exiting(TEST_END + STR_METHOD_NAME);
  }
  
  public void testCalcTurnOver_C0001()
  {
      final String STR_METHOD_NAME = "testCalcTurnOver_C0001()";
      log.entering(TEST_START + STR_METHOD_NAME);
      
      WEB3IfoBizLogicProvider l_provider = new WEB3IfoBizLogicProvider();
      
      try
      {
          TestDBUtility.deleteAll(TradedProductParams.TYPE);
          TestDBUtility.deleteAll(IfoTradedProductParams.TYPE);
          TestDBUtility.deleteAll(ProductParams.TYPE);
          TestDBUtility.deleteAll(IfoProductParams.TYPE);
          
          TradedProductParams l_tradedProductParams = TestDBUtility.getTradedProductRow();
          
          IfoTradedProductParams l_ifoTradedProductParams = TestDBUtility.getIfoTradedProductRow();
          l_ifoTradedProductParams.setUnitSize(1.1d);
          
          ProductParams l_productParams = TestDBUtility.getProductRow();
          l_productParams.setProductId(1006139070605L);
          
          IfoProductParams l_ifoProductParams = TestDBUtility.getIfoProductRow();
          l_ifoProductParams.setProductId(1006139070605L);

          TestDBUtility.insertWithDel(l_tradedProductParams);
          TestDBUtility.insertWithDel(l_ifoTradedProductParams);
          TestDBUtility.insertWithDel(l_productParams);
          TestDBUtility.insertWithDel(l_ifoProductParams);
          
          double l_dblQuantity = 3d;
          double l_dblCalcUnitPrice = 2d;
          WEB3IfoTradedProductImpl l_ifoTradedProduct =
              new WEB3IfoTradedProductImpl(1006160060005L);
          
          double l_dblReturnValue =
              l_provider.calcTurnOver(
                  l_dblQuantity,
                  l_dblCalcUnitPrice,
                  l_ifoTradedProduct);

          assertEquals(6, l_dblReturnValue, 0);
      }
      catch (Exception l_ex)
      {
          log.error(l_ex.getMessage(), l_ex);
          fail();
      }
      
      log.exiting(TEST_END + STR_METHOD_NAME);
  }
  
  public void testCalcExecutionAmount_C0001()
  {
      final String STR_METHOD_NAME = "testCalcExecutionAmount_C0001()";
      log.entering(TEST_START + STR_METHOD_NAME);
      
      WEB3IfoBizLogicProvider l_provider = new WEB3IfoBizLogicProvider();
      
      
      try
      {
          TestDBUtility.deleteAll(TradedProductParams.TYPE);
          TestDBUtility.deleteAll(IfoTradedProductParams.TYPE);
          TestDBUtility.deleteAll(ProductParams.TYPE);
          TestDBUtility.deleteAll(IfoProductParams.TYPE);
          TestDBUtility.deleteAll(MarketParams.TYPE);
          
          TradedProductParams l_tradedProductParams = TestDBUtility.getTradedProductRow();
          l_tradedProductParams.setProductId(1006139070605L);
          
          IfoTradedProductParams l_ifoTradedProductParams = TestDBUtility.getIfoTradedProductRow();
          l_ifoTradedProductParams.setUnitSize(2.0d);
          l_ifoTradedProductParams.setMarketId(3303L);
          l_ifoTradedProductParams.setProductId(1006139070605L);
          
          ProductParams l_productParams = TestDBUtility.getProductRow();
          l_productParams.setProductId(1006139070605L);
          
          IfoProductParams l_ifoProductParams = TestDBUtility.getIfoProductRow();
          l_ifoProductParams.setProductId(1006139070605L);
          l_ifoProductParams.setTargetMarketId(3303L);
          
          MarketParams l_marketParams = TestDBUtility.getMarketRow();
          l_marketParams.setMarketId(3303L);

          TestDBUtility.insertWithDel(l_tradedProductParams);
          TestDBUtility.insertWithDel(l_ifoTradedProductParams);
          TestDBUtility.insertWithDel(l_productParams);
          TestDBUtility.insertWithDel(l_ifoProductParams);
          TestDBUtility.insertWithDel(l_marketParams);
          
          ProductTypeEnum l_productType = null;
          long l_lngProductId = 1006139070605L;
          double l_dblPrice = 3.0d;
          double l_dblQuantity = 1.1d;
          QuantityTypeEnum l_quantityType = null;
          
          double l_dblReturnValue = l_provider.calcExecutionAmount(
              l_productType, 
              l_lngProductId, 
              l_dblPrice, 
              l_dblQuantity, 
              l_quantityType);

          assertEquals(6.6, l_dblReturnValue, 0);
      }
      catch (Exception l_ex)
      {
          log.error(l_ex.getMessage(), l_ex);
          fail();
      }
      
      log.exiting(TEST_END + STR_METHOD_NAME);
  }
  
  public void testCalcRestraintTurnOverCase1()
  {
      final String STR_METHOD_NAME = "test_calcCommissionAmount_0001()";
      log.entering(TEST_START + STR_METHOD_NAME);
      try
      {
          TestDBUtility.deleteAll(InstitutionRow.TYPE);
          InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
          TestDBUtility.insertWithDel(l_institutionParams);
          
          TestDBUtility.deleteAll(IfoOrderUnitRow.TYPE);
          IfoOrderUnitParams l_ifoOrderUnitParams = TestDBUtility.getIfoOrderUnitRow();
          l_ifoOrderUnitParams.setOrderUnitId(1001L);
          l_ifoOrderUnitParams.setAccountId(333812512203L);
          l_ifoOrderUnitParams.setSubAccountId(33381251220301L);
          l_ifoOrderUnitParams.setProductId(1006160060005L);
          l_ifoOrderUnitParams.setCommProductCode("10");
          TestDBUtility.insertWithDel(l_ifoOrderUnitParams);
          
          MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
          l_mainAccountParams.setAccountId(333812512203L);
          TestDBUtility.deleteAll(MainAccountRow.TYPE);
          TestDBUtility.insertWithDel(l_mainAccountParams);
          
          SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
          l_subAccountParams.setAccountId(333812512203L);
          l_subAccountParams.setSubAccountId(33381251220301L);
          
          MarketParams l_marketParams = TestDBUtility.getMarketRow();
          l_marketParams.setMarketId(3306L);
          
          BranchParams l_branchParams = TestDBUtility.getBranchRow();
          l_branchParams.setBranchId(33381);
          
          TestDBUtility.deleteAll(SubAccountRow.TYPE);
          TestDBUtility.insertWithDel(l_subAccountParams);
          
          TestDBUtility.deleteAll(MarketRow.TYPE);
          TestDBUtility.insertWithDel(l_marketParams);
          
          TestDBUtility.deleteAll(BranchRow.TYPE);
          TestDBUtility.insertWithDel(l_branchParams);
          
          TestDBUtility.deleteAll(ProductRow.TYPE);
          ProductParams l_productParams =  TestDBUtility.getProductRow();
          l_productParams.setProductId(1006160060005L);
          TestDBUtility.insertWithDel(l_productParams);
          
          IfoProductParams l_ifoProductParams = TestDBUtility.getIfoProductRow();
          l_ifoProductParams.setProductId(1006160060005L);
          TestDBUtility.insertWithDel(l_ifoProductParams);
          
          TestDBUtility.deleteAll(TradedProductRow.TYPE);
          TradedProductParams l_tradedProductParams = TestDBUtility.getTradedProductRow();
          l_tradedProductParams.setTradedProductId(330304148080002L);
          l_tradedProductParams.setProductId(1006160060005L);
          l_tradedProductParams.setMarketId(3306L);
          TestDBUtility.insertWithDel(l_tradedProductParams);
          
          TestDBUtility.deleteAll(IfoTradedProductRow.TYPE);
          IfoTradedProductParams l_IfoTradedProductParams = TestDBUtility.getIfoTradedProductRow();
          l_IfoTradedProductParams.setProductId(1006160060005L);
          l_IfoTradedProductParams.setTradedProductId(330304148080002L);
          l_IfoTradedProductParams.setMarketId(3306L);
          l_IfoTradedProductParams.setUnitSize(2);//指数乗数は先物OP取引銘柄マスタ.１単位当り指数乗数
          l_IfoTradedProductParams.setValidForBizDate(null);
          TestDBUtility.insertWithDel(l_IfoTradedProductParams);
          
          TestDBUtility.deleteAll(IfoTradedProductUpdqRow.TYPE);
          IfoTradedProductUpdqParams l_ifoTradedProductUpdqParams = TestDBUtility.getIfoTradedProductUpdqRow();
          l_ifoTradedProductUpdqParams.setTradedProductId(330304148080002L);
          l_ifoTradedProductUpdqParams.setMarketId(3306L);
          l_ifoTradedProductUpdqParams.setValidForBizDate(
              WEB3DateUtility.formatDate(new WEB3GentradeBizDate(
                      new Timestamp(GtlUtils.getSystemTimestamp().getTime())).roll(1), "yyyyMMdd"));
          TestDBUtility.insertWithDel(l_ifoTradedProductUpdqParams);
          
          
          InstBranchProductParams l_InstBranchProductParams = new InstBranchProductParams();
          l_InstBranchProductParams.setBranchId(33381);
          l_InstBranchProductParams.setCommissionProductCode("10");
          TestDBUtility.deleteAll(InstBranchProductRow.TYPE);
          TestDBUtility.insertWithDel(l_InstBranchProductParams);
          

          WEB3IfoTradedProductImpl l_tradedProduct = new WEB3IfoTradedProductImpl(330304148080002L);
          
          WEB3IfoBizLogicProvider l_ifoBizLogicProvider = new WEB3IfoBizLogicProvider();
//          double l_dblQuantity,
//          double l_dblUnitPrice,
//          long l_lngBranchId,
//          String l_strCommissionProductCode,
//          boolean l_blnIsLimitPrice
          double l_dblCalcRestraintTurnOver = l_ifoBizLogicProvider.calcRestraintTurnOver(
                  100, 1.1225, 33381, "10", true, l_tradedProduct);
          
          assertEquals("224.0", l_dblCalcRestraintTurnOver + "");
      }
      catch (Exception e)
      {
          log.exiting(TEST_END + STR_METHOD_NAME);
          fail();
      }
      
      log.exiting(TEST_END + STR_METHOD_NAME);
  }
  
  public void testCalcRestraintTurnOverCase2()
  {
      final String STR_METHOD_NAME = "test_calcCommissionAmount_0002()";
      log.entering(TEST_START + STR_METHOD_NAME);
      try
      {
          TestDBUtility.deleteAll(InstitutionRow.TYPE);
          InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
          TestDBUtility.insertWithDel(l_institutionParams);
          
          TestDBUtility.deleteAll(IfoOrderUnitRow.TYPE);
          IfoOrderUnitParams l_ifoOrderUnitParams = TestDBUtility.getIfoOrderUnitRow();
          l_ifoOrderUnitParams.setOrderUnitId(1001L);
          l_ifoOrderUnitParams.setAccountId(333812512203L);
          l_ifoOrderUnitParams.setSubAccountId(33381251220301L);
          l_ifoOrderUnitParams.setProductId(1006160060005L);
          l_ifoOrderUnitParams.setCommProductCode("10");
          TestDBUtility.insertWithDel(l_ifoOrderUnitParams);
          
          MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
          l_mainAccountParams.setAccountId(333812512203L);
          TestDBUtility.deleteAll(MainAccountRow.TYPE);
          TestDBUtility.insertWithDel(l_mainAccountParams);
          
          SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
          l_subAccountParams.setAccountId(333812512203L);
          l_subAccountParams.setSubAccountId(33381251220301L);
          
          MarketParams l_marketParams = TestDBUtility.getMarketRow();
          l_marketParams.setMarketId(3306L);
          
          BranchParams l_branchParams = TestDBUtility.getBranchRow();
          l_branchParams.setBranchId(33381);
          
          TestDBUtility.deleteAll(SubAccountRow.TYPE);
          TestDBUtility.insertWithDel(l_subAccountParams);
          
          TestDBUtility.deleteAll(MarketRow.TYPE);
          TestDBUtility.insertWithDel(l_marketParams);
          
          TestDBUtility.deleteAll(BranchRow.TYPE);
          TestDBUtility.insertWithDel(l_branchParams);
          
          TestDBUtility.deleteAll(ProductRow.TYPE);
          ProductParams l_productParams =  TestDBUtility.getProductRow();
          l_productParams.setProductId(1006160060005L);
          TestDBUtility.insertWithDel(l_productParams);
          
          IfoProductParams l_ifoProductParams = TestDBUtility.getIfoProductRow();
          l_ifoProductParams.setProductId(1006160060005L);
          TestDBUtility.insertWithDel(l_ifoProductParams);
          
          TestDBUtility.deleteAll(TradedProductRow.TYPE);
          TradedProductParams l_tradedProductParams = TestDBUtility.getTradedProductRow();
          l_tradedProductParams.setTradedProductId(330304148080002L);
          l_tradedProductParams.setProductId(1006160060005L);
          l_tradedProductParams.setMarketId(3306L);
          TestDBUtility.insertWithDel(l_tradedProductParams);
          
          TestDBUtility.deleteAll(IfoTradedProductRow.TYPE);
          IfoTradedProductParams l_IfoTradedProductParams = TestDBUtility.getIfoTradedProductRow();
          l_IfoTradedProductParams.setProductId(1006160060005L);
          l_IfoTradedProductParams.setTradedProductId(330304148080002L);
          l_IfoTradedProductParams.setMarketId(3306L);
          l_IfoTradedProductParams.setUnitSize(1000);//指数乗数は先物OP取引銘柄マスタ.１単位当り指数乗数
          l_IfoTradedProductParams.setValidForBizDate(null);
          TestDBUtility.insertWithDel(l_IfoTradedProductParams);
          
          TestDBUtility.deleteAll(IfoTradedProductUpdqRow.TYPE);
          IfoTradedProductUpdqParams l_ifoTradedProductUpdqParams = TestDBUtility.getIfoTradedProductUpdqRow();
          l_ifoTradedProductUpdqParams.setTradedProductId(330304148080002L);
          l_ifoTradedProductUpdqParams.setMarketId(3306L);
          l_ifoTradedProductUpdqParams.setValidForBizDate(
              WEB3DateUtility.formatDate(new WEB3GentradeBizDate(
                      new Timestamp(GtlUtils.getSystemTimestamp().getTime())).roll(1), "yyyyMMdd"));
          TestDBUtility.insertWithDel(l_ifoTradedProductUpdqParams);
          
          
          InstBranchProductParams l_InstBranchProductParams = new InstBranchProductParams();
          l_InstBranchProductParams.setBranchId(33381);
          l_InstBranchProductParams.setCommissionProductCode("10");
          l_InstBranchProductParams.setPremiumRestraintRate(8.2-7);
          TestDBUtility.deleteAll(InstBranchProductRow.TYPE);
          TestDBUtility.insertWithDel(l_InstBranchProductParams);
          

          WEB3IfoTradedProductImpl l_tradedProduct = new WEB3IfoTradedProductImpl(330304148080002L);
          
          WEB3IfoBizLogicProvider l_ifoBizLogicProvider = new WEB3IfoBizLogicProvider();
//          double l_dblQuantity,
//          double l_dblUnitPrice,
//          long l_lngBranchId,
//          String l_strCommissionProductCode,
//          boolean l_blnIsLimitPrice
          double l_dblCalcRestraintTurnOver = l_ifoBizLogicProvider.calcRestraintTurnOver(
                  2, 5, 33381, "10", false, l_tradedProduct);
          
          assertEquals("12000.0", l_dblCalcRestraintTurnOver + "");
      }
      catch (Exception e)
      {
          log.exiting(TEST_END + STR_METHOD_NAME);
          fail();
      }
      
      log.exiting(TEST_END + STR_METHOD_NAME);
  }
  
}
@
