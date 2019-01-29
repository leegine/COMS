head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.01.02.05;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3BondBizLogicProviderTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.bd;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.InstitutionParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.ProductParams;
import com.fitechlabs.xtrade.plugin.tc.xbbd.data.BondProductParams;
import com.fitechlabs.xtrade.plugin.tc.xbbd.data.BondProductRow;

import test.util.TestDBUtility;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.gentrade.data.TaxRateTableParams;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

/**
 * 債券計算サービス
 * @@author 謝旋
 * @@version 1.0
 */
public class WEB3BondBizLogicProviderTest extends TestBaseForMock
{
    /**
     * ログ出力ユーティリティ。
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3BondBizLogicProviderTest.class);

    /*
     * calc売買代金
     */
    public static List calcTradingPriceList = new ArrayList();
    
    /*
     * calc初回利子調整額（債券受渡代金計算結果, 債券銘柄, 債券約定日情報）
     */
    public static List calcInitialInterestAdjustAmountList = new ArrayList();
    public static boolean calcInitialInterestAdjustAmountFlag = true;
    
    /*
     * calc経過利子
     */
    public static List calcAccruedInterestList = new ArrayList();

    /*
     * calc受渡代金
     */
    public static List calcEstimatedPriceList = new ArrayList();
    
    /*
     * is個人向け国債
     */
    public static boolean isIndividualGovernmentBondFlag = true;
    
    /*
     * calc利払日をコールし
     */
    public static List calcInterestPaymentDayList = new ArrayList();
    
    /*
     * calc日付調整
     */
    public static List calcDateAdjustmentList = new ArrayList();
    
    public WEB3BondBizLogicProviderTest(String arg0)
    {
        super(arg0);
    }

    protected void setUp() throws Exception
    {
        super.setUp();
    }

    protected void tearDown() throws Exception
    {
        this.calcTradingPriceList.clear();
        this.calcAccruedInterestList.clear();
        this.calcEstimatedPriceList.clear();
        this.calcInitialInterestAdjustAmountList.clear();
        this.calcInterestPaymentDayList.clear();
        this.calcDateAdjustmentList.clear();
        super.tearDown();
    }
    
    public void testCalcBondDomesticEstimatedPrice_0001()
    {
        final String STR_METHOD_NAME = "testCalcBondDomesticEstimatedPrice_0001()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        try
        {
            ProductParams l_productParams = TestDBUtility.getProductRow();
            TestDBUtility.deleteAll(ProductParams.TYPE);
            TestDBUtility.insertWithDel(l_productParams);
            
            BondProductParams l_bondProductParams = TestDBUtility.getBondProductRow();
            l_bondProductParams.setBuyPrice(12.32D);
            l_bondProductParams.setProductId(3304148080000L);
            
            WEB3BondProductForTest l_bondProductForTest = new WEB3BondProductForTest(l_bondProductParams);
            
            WEB3BondOrderTypeJudge l_bondOrderTypeJudge = new WEB3BondOrderTypeJudge();
            
            WEB3BondExecuteDateInfo l_bondExecuteDateInfo = new WEB3BondExecuteDateInfo();
            
            BigDecimal l_dbQuantity = new BigDecimal("1234.111");
            
            this.isIndividualGovernmentBondFlag = true;
            
            WEB3BondBizLogicProviderForTest l_bondBizLogicProvider = new WEB3BondBizLogicProviderForTest();
            WEB3BondEstimatedPriceCalcResult l_bondEstimatedPriceCalcResult =
                l_bondBizLogicProvider.calcBondDomesticEstimatedPrice(
                    l_bondOrderTypeJudge,
                    l_dbQuantity,
                    l_bondProductForTest,
                    l_bondExecuteDateInfo);
            
            assertEquals(l_dbQuantity, l_bondEstimatedPriceCalcResult.getQuantity());
            assertEquals(new BigDecimal(String.valueOf(12.32D)), l_bondEstimatedPriceCalcResult.getPrice());
            assertEquals(l_bondOrderTypeJudge.getClass(), l_bondEstimatedPriceCalcResult.getBondOrderTypeJudge().getClass());
            
            //this.calc売買代金(債券受渡代金計算結果, 債券銘柄)を呼ぶ
            assertEquals(2, this.calcTradingPriceList.size());
            assertEquals(WEB3BondEstimatedPriceCalcResult.class, this.calcTradingPriceList.get(0).getClass());
            assertEquals(WEB3BondProductForTest.class, this.calcTradingPriceList.get(1).getClass());
            
            //this.calc初回利子調整額（債券受渡代金計算結果, 債券銘柄, 債券約定日情報）を呼ぶ
            assertEquals(3, this.calcInitialInterestAdjustAmountList.size());
            assertEquals(WEB3BondEstimatedPriceCalcResult.class, this.calcInitialInterestAdjustAmountList.get(0).getClass());
            assertEquals(WEB3BondProductForTest.class, this.calcInitialInterestAdjustAmountList.get(1).getClass());
            assertEquals(l_bondExecuteDateInfo.getClass(), this.calcInitialInterestAdjustAmountList.get(2).getClass());

            //this.calc経過利子（債券受渡代金計算結果, 債券銘柄, 債券約定日情報）を呼ぶ
            assertEquals(0, this.calcAccruedInterestList.size());
            
            //this.calc受渡代金(債券受渡代金計算結果, 債券銘柄)を呼ぶ
            assertEquals(2, this.calcEstimatedPriceList.size());
            assertEquals(WEB3BondEstimatedPriceCalcResult.class, this.calcEstimatedPriceList.get(0).getClass());
            assertEquals(WEB3BondProductForTest.class, this.calcEstimatedPriceList.get(1).getClass());
            
        }
        catch (Exception l_ex)
        {
            log.error(TEST_END + STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    public void testCalcBondDomesticEstimatedPrice_0002()
    {
        final String STR_METHOD_NAME = "testCalcBondDomesticEstimatedPrice_0002()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        try
        {
            ProductParams l_productParams = TestDBUtility.getProductRow();
            TestDBUtility.deleteAll(ProductParams.TYPE);
            TestDBUtility.insertWithDel(l_productParams);
            
            BondProductParams l_bondProductParams = TestDBUtility.getBondProductRow();
            l_bondProductParams.setBuyPrice(12.32D);
            l_bondProductParams.setProductId(3304148080000L);
            
            WEB3BondProductForTest l_bondProductForTest = new WEB3BondProductForTest(l_bondProductParams);
            
            WEB3BondOrderTypeJudge l_bondOrderTypeJudge = new WEB3BondOrderTypeJudge();
            
            WEB3BondExecuteDateInfo l_bondExecuteDateInfo = new WEB3BondExecuteDateInfo();
            
            BigDecimal l_dbQuantity = new BigDecimal("1234.111");
            
            this.isIndividualGovernmentBondFlag = false;
            
            WEB3BondBizLogicProviderForTest l_bondBizLogicProvider = new WEB3BondBizLogicProviderForTest();
            WEB3BondEstimatedPriceCalcResult l_bondEstimatedPriceCalcResult =
                l_bondBizLogicProvider.calcBondDomesticEstimatedPrice(
                    l_bondOrderTypeJudge,
                    l_dbQuantity,
                    l_bondProductForTest,
                    l_bondExecuteDateInfo);
            
            assertEquals(l_dbQuantity, l_bondEstimatedPriceCalcResult.getQuantity());
            assertEquals(new BigDecimal(String.valueOf(12.32D)), l_bondEstimatedPriceCalcResult.getPrice());
            assertEquals(l_bondOrderTypeJudge.getClass(), l_bondEstimatedPriceCalcResult.getBondOrderTypeJudge().getClass());
            
            //this.calc売買代金(債券受渡代金計算結果, 債券銘柄)を呼ぶ
            assertEquals(2, this.calcTradingPriceList.size());
            assertEquals(WEB3BondEstimatedPriceCalcResult.class, this.calcTradingPriceList.get(0).getClass());
            assertEquals(WEB3BondProductForTest.class, this.calcTradingPriceList.get(1).getClass());
            
            //this.calc初回利子調整額（債券受渡代金計算結果, 債券銘柄, 債券約定日情報）を呼ぶ
            assertEquals(0, this.calcInitialInterestAdjustAmountList.size());

            //this.calc経過利子（債券受渡代金計算結果, 債券銘柄, 債券約定日情報）を呼ぶ
            assertEquals(3, this.calcAccruedInterestList.size());
            assertEquals(WEB3BondEstimatedPriceCalcResult.class, this.calcAccruedInterestList.get(0).getClass());
            assertEquals(WEB3BondProductForTest.class, this.calcAccruedInterestList.get(1).getClass());
            assertEquals(l_bondExecuteDateInfo.getClass(), this.calcAccruedInterestList.get(2).getClass());
            
            //this.calc受渡代金(債券受渡代金計算結果, 債券銘柄)を呼ぶ
            assertEquals(2, this.calcEstimatedPriceList.size());
            assertEquals(WEB3BondEstimatedPriceCalcResult.class, this.calcEstimatedPriceList.get(0).getClass());
            assertEquals(WEB3BondProductForTest.class, this.calcEstimatedPriceList.get(1).getClass());
            
        }
        catch (Exception l_ex)
        {
            log.error(TEST_END + STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    public void testCalcBondDomesticEstimatedPrice_0003()
    {
        final String STR_METHOD_NAME = "testCalcInitialInterestAdjustAmount_0001()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            WEB3BondBizLogicProvider l_bondBizLogicProvider = new WEB3BondBizLogicProvider();
            WEB3BondEstimatedPriceCalcResult l_bondEstimatedPriceCalcResult =
                l_bondBizLogicProvider.calcBondDomesticEstimatedPrice(
                    null,
                    null,
                    null,
                    null);
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(TEST_END + STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80017, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(TEST_END + STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    public void testCalcInitialInterestAdjustAmount_0001()
    {
        final String STR_METHOD_NAME = "testCalcInitialInterestAdjustAmount_0001()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        try
        {
            WEB3BondBizLogicProvider l_bondBizLogicProvider = new WEB3BondBizLogicProvider();
            l_bondBizLogicProvider.calcInitialInterestAdjustAmount(null, null ,null);
        }
        catch (WEB3BaseException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80017, l_ex.getErrorInfo());
            log.error(TEST_END + STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
        }
        catch (Exception l_ex)
        {
            log.error(TEST_END + STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    public void testCalcInitialInterestAdjustAmount_0002()
    {
        final String STR_METHOD_NAME = "testCalcInitialInterestAdjustAmount_0002()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        try
        {
            WEB3BondEstimatedPriceCalcResult l_result = new WEB3BondEstimatedPriceCalcResult();
            
            WEB3BondBizLogicProvider l_bondBizLogicProvider = new WEB3BondBizLogicProvider();
            l_bondBizLogicProvider.calcInitialInterestAdjustAmount(l_result, null ,null);
        }
        catch (WEB3BaseException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80017, l_ex.getErrorInfo());
            log.error(TEST_END + STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
        }
        catch (Exception l_ex)
        {
            log.error(TEST_END + STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    public void testCalcInitialInterestAdjustAmount_0003()
    {
        final String STR_METHOD_NAME = "testCalcInitialInterestAdjustAmount_0003()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        try
        {
            WEB3BondEstimatedPriceCalcResult l_result = new WEB3BondEstimatedPriceCalcResult();
            
            BondProductParams l_bondProductParams = TestDBUtility.getBondProductRow();
            l_bondProductParams.setBuyPrice(12.32D);
            l_bondProductParams.setProductId(3304148080000L);
            
            WEB3BondProduct l_bondProduct = new WEB3BondProduct(l_bondProductParams);
            
            WEB3BondBizLogicProvider l_bondBizLogicProvider = new WEB3BondBizLogicProvider();
            l_bondBizLogicProvider.calcInitialInterestAdjustAmount(l_result, l_bondProduct ,null);
        }
        catch (WEB3BaseException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80017, l_ex.getErrorInfo());
            log.error(TEST_END + STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
        }
        catch (Exception l_ex)
        {
            log.error(TEST_END + STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    public void testCalcInitialInterestAdjustAmount_0004()
    {
        final String STR_METHOD_NAME = "testCalcInitialInterestAdjustAmount_0004()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            ProductParams l_productParams = TestDBUtility.getProductRow();
            TestDBUtility.deleteAll(ProductParams.TYPE);
            TestDBUtility.insertWithDel(l_productParams);
            
            BondProductParams l_bondProductParams = TestDBUtility.getBondProductRow();
            l_bondProductParams.setBuyPrice(12.32D);
            l_bondProductParams.setProductId(3304148080000L);
            l_bondProductParams.setCoupon(8D);
            l_bondProductParams.setIssueDate(WEB3DateUtility.getDate("2007/03/11", "yyyy/MM/dd"));
            
            WEB3BondProduct l_bondProduct = new WEB3BondProduct(l_bondProductParams);
            
            TaxRateTableParams l_taxRateTableParams = this.getTaxRateTableRow();
            TestDBUtility.deleteAll(l_taxRateTableParams.getRowType());
            TestDBUtility.insertWithDel(l_taxRateTableParams);
            
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            TestDBUtility.deleteAll(l_institutionParams.getRowType());
            TestDBUtility.insertWithDel(l_institutionParams);
            
            WEB3BondEstimatedPriceCalcResult l_result = new WEB3BondEstimatedPriceCalcResult();
            l_result.setQuantity(new BigDecimal("10000"));
            
            WEB3BondExecuteDateInfo l_info = new WEB3BondExecuteDateInfo();
            l_info.setBizDate(WEB3DateUtility.getDate("20070101", "yyyyMMdd"));
            
            this.calcInitialInterestAdjustAmountFlag = false;
            
            WEB3BondBizLogicProviderForTest l_bondBizLogicProvider = new WEB3BondBizLogicProviderForTest();
            l_bondBizLogicProvider.calcInitialInterestAdjustAmount(l_result, l_bondProduct , l_info);
            
            //calc利払日をコールし
            assertEquals(WEB3BondEstimatedPriceCalcResult.class, this.calcInterestPaymentDayList.get(0).getClass());
            assertEquals(l_bondProduct.getClass(), this.calcInterestPaymentDayList.get(1).getClass());
            assertEquals(WEB3DateUtility.getDate("2007/03/11", "yyyy/MM/dd").toLocaleString(),
                ((Timestamp)this.calcInterestPaymentDayList.get(2)).toLocaleString());
            
            //calc日付調整()
            assertEquals("20031103", this.calcDateAdjustmentList.get(0));
            assertEquals(l_bondProduct.getClass(), this.calcDateAdjustmentList.get(1).getClass());
            
            assertEquals(new BigDecimal("7"), l_result.getAccruedInterest());
        }
        catch (Exception l_ex)
        {
            log.error(TEST_END + STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        
        
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    private TaxRateTableParams getTaxRateTableRow()
    {
        TaxRateTableParams l_taxRateTableParams = new TaxRateTableParams();
        //証券会社コード
        l_taxRateTableParams.setInstitutionCode("0D");
        //税種類
        l_taxRateTableParams.setTaxType("70");
        //適用開始年月日
        l_taxRateTableParams.setAppliStartDate(WEB3DateUtility.getDate("20050101", "yyyyMMdd"));
        //適用終了年月日
        l_taxRateTableParams.setAppliEndDate(WEB3DateUtility.getDate("30010101", "yyyyMMdd"));
        //税率
        l_taxRateTableParams.setTaxRate(50L);
        //作成日付
        l_taxRateTableParams.setCreatedTimestamp(new Date());
        //更新日付
        l_taxRateTableParams.setLastUpdatedTimestamp(new Date());
        
        return l_taxRateTableParams;
    }
    
    public class WEB3BondBizLogicProviderForTest extends WEB3BondBizLogicProvider
    {
        protected void calcTradingPrice(WEB3BondEstimatedPriceCalcResult l_bondPriceCalcResult, 
            WEB3BondProduct l_bondProduct) 
            throws WEB3BaseException 
        {
            WEB3BondBizLogicProviderTest.calcTradingPriceList.add(l_bondPriceCalcResult);
            WEB3BondBizLogicProviderTest.calcTradingPriceList.add(l_bondProduct);
        }
        
        protected void calcAccruedInterest(WEB3BondEstimatedPriceCalcResult l_bondPriceCalcResult, 
            WEB3BondProduct l_bondProduct, 
            WEB3BondExecuteDateInfo l_bondExecuteDateInfo) throws WEB3BaseException 
        {
            WEB3BondBizLogicProviderTest.calcAccruedInterestList.add(l_bondPriceCalcResult);
            WEB3BondBizLogicProviderTest.calcAccruedInterestList.add(l_bondProduct);
            WEB3BondBizLogicProviderTest.calcAccruedInterestList.add(l_bondExecuteDateInfo);
        }
        
        protected void calcEstimatedPrice(WEB3BondEstimatedPriceCalcResult l_bondPriceCalcResult, 
            WEB3BondProduct l_bondProduct) throws WEB3BaseException 
        {
            WEB3BondBizLogicProviderTest.calcEstimatedPriceList.add(l_bondPriceCalcResult);
            WEB3BondBizLogicProviderTest.calcEstimatedPriceList.add(l_bondProduct);
        }
        
        protected void calcInitialInterestAdjustAmount(
            WEB3BondEstimatedPriceCalcResult l_calcResult,
            WEB3BondProduct l_product,
            WEB3BondExecuteDateInfo l_executeDateInfo) throws WEB3BaseException
        {
            if (WEB3BondBizLogicProviderTest.calcInitialInterestAdjustAmountFlag)
            {
                WEB3BondBizLogicProviderTest.calcInitialInterestAdjustAmountList.add(l_calcResult);
                WEB3BondBizLogicProviderTest.calcInitialInterestAdjustAmountList.add(l_product);
                WEB3BondBizLogicProviderTest.calcInitialInterestAdjustAmountList.add(l_executeDateInfo);
            }
            else
            {
                super.calcInitialInterestAdjustAmount(l_calcResult, l_product, l_executeDateInfo);
            }
        }
        
        protected String calcDateAdjustment(String l_strDate, WEB3BondProduct l_bondProduct) 
            throws WEB3BaseException 
        {
            WEB3BondBizLogicProviderTest.calcDateAdjustmentList.add(l_strDate);
            WEB3BondBizLogicProviderTest.calcDateAdjustmentList.add(l_bondProduct);
            return "20070304";
        }
        
        protected WEB3BondInterestPaymentDay calcInterestPaymentDay(
            WEB3BondEstimatedPriceCalcResult l_bondPriceCalcResult, 
            WEB3BondProduct l_bondProduct, 
            Date l_datBaseDate) throws WEB3BaseException 
        {
            WEB3BondBizLogicProviderTest.calcInterestPaymentDayList.add(l_bondPriceCalcResult);
            WEB3BondBizLogicProviderTest.calcInterestPaymentDayList.add(l_bondProduct);
            WEB3BondBizLogicProviderTest.calcInterestPaymentDayList.add(l_datBaseDate);
            WEB3BondInterestPaymentDay l_bondInterestPaymentDay = new WEB3BondInterestPaymentDay();
            l_bondInterestPaymentDay.setFirstInterestPaymentDay(WEB3DateUtility.getDate("20040503", "yyyyMMdd"));
            return l_bondInterestPaymentDay;
        }
    }
    
    public class WEB3BondProductForTest extends WEB3BondProduct
    {
        public WEB3BondProductForTest(BondProductRow l_bondProductRow) throws DataQueryException, DataNetworkException
        {
            super(l_bondProductRow);
        }

        public boolean isIndividualGovernmentBond()
        {
            if (WEB3BondBizLogicProviderTest.isIndividualGovernmentBondFlag)
            {
                return true;
            }
            
            return false;
        }
    }

}
@
