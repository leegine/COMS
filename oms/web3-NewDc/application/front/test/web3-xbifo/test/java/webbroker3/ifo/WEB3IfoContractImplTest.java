head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.00.58.03;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3IfoContractImplTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 先物OP建玉Test(WEB3IfoContractImplTest.java)
Author Name      : Daiwa Institute of Research
Revision History : 2008/04/08 趙林鵬(中訊) 新規作成
Revision History : 2007/08/26 劉剣 (中訊) IFO小数点対応
*/

package webbroker3.ifo;

import java.sql.Timestamp;
import java.util.Date;

import com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ContractTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MarketParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MarketRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.ProductParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.ProductRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.TradedProductParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.TradedProductRow;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoContractParams;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoContractRow;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoFinTransactionParams;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoFinTransactionRow;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoProductParams;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoProductRow;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoTradedProductParams;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoTradedProductRow;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoTradedProductUpdqParams;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoTradedProductUpdqRow;

import test.util.TestDBUtility;

import webbroker3.gentrade.WEB3GentradeBizDate;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

public class WEB3IfoContractImplTest extends TestBaseForMock
{
    /**
     * ログ出力ユーティリティ。<BR>
     */
    private static WEB3LogUtility log = 
        WEB3LogUtility.getInstance(WEB3IfoContractImplTest.class);

    public WEB3IfoContractImplTest(String arg0)
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
    
    /**
     * get評価損益
     * 2)建区分が売建の場合
     *（getContractPrice() － パラメータ.返済単価） × パラメータ.数量　@×　@(*1) 指数乗数
     */
    public void testGetEvaluateIncomeCase0001()
    {
        final String STR_METHOD_NAME = "testGetEvaluateIncomeCase0001()";
        log.entering(STR_METHOD_NAME);

        try
        {
            //MarketParams
            TestDBUtility.deleteAll(MarketRow.TYPE);
            MarketParams l_marketParams = TestDBUtility.getMarketRow();
            l_marketParams.setSonarMarketCode("8");
            TestDBUtility.insertWithDel(l_marketParams);
            
            //ProductParams
            TestDBUtility.deleteAll(ProductRow.TYPE);
            ProductParams l_productParams = TestDBUtility.getProductRow();
            l_productParams.setProductId(1006169090018L);
            l_productParams.setProductType(ProductTypeEnum.IFO);
            TestDBUtility.insertWithDel(l_productParams);
            
            TestDBUtility.deleteAll(IfoProductRow.TYPE);
            IfoProductParams l_ifoProductParams = TestDBUtility.getIfoProductRow();
            l_ifoProductParams.setProductId(1006169090018L);
            TestDBUtility.insertWithDel(l_ifoProductParams);
            
            TestDBUtility.deleteAll(TradedProductRow.TYPE);
            TradedProductParams l_tradedProductParams = TestDBUtility.getTradedProductRow();
            l_tradedProductParams.setProductId(1006169090018L);
            l_tradedProductParams.setProductType(ProductTypeEnum.IFO);
            l_tradedProductParams.setMarketId(3303);
            TestDBUtility.insertWithDel(l_tradedProductParams);
            
            TestDBUtility.deleteAll(IfoTradedProductRow.TYPE);
            IfoTradedProductParams l_ifoTradedProductParams = TestDBUtility.getIfoTradedProductRow();
            l_ifoTradedProductParams.setProductId(1006169090018L);
            l_ifoTradedProductParams.setTradedProductId(l_tradedProductParams.getTradedProductId());
            l_ifoTradedProductParams.setMarketId(3303);
            l_ifoTradedProductParams.setValidForBizDate(null);
            l_ifoTradedProductParams.setUnitSize(3);
            TestDBUtility.insertWithDel(l_ifoTradedProductParams);
            
            TestDBUtility.deleteAll(IfoContractRow.TYPE);
            IfoContractParams l_ifoContractParams = TestDBUtility.getIfoContractRow();
            l_ifoContractParams.setMarketId(3303);
            l_ifoContractParams.setProductId(1006169090018L);
            l_ifoContractParams.setContractType(ContractTypeEnum.SHORT);
            l_ifoContractParams.setContractPrice(2.5);
            TestDBUtility.insertWithDel(l_ifoContractParams);
            
            TestDBUtility.deleteAll(IfoTradedProductUpdqRow.TYPE);
            IfoTradedProductUpdqParams l_ifoTradedProductUpdqParams = TestDBUtility.getIfoTradedProductUpdqRow();
            l_ifoTradedProductUpdqParams.setProductId(1006169090018L);
            Date l_datBizDate = GtlUtils.getTradingSystem().getSystemTimestamp();
            Date l_datpreBizDate = new WEB3GentradeBizDate(
                new Timestamp(l_datBizDate.getTime())).roll(1);
            String l_strCreateDate = WEB3DateUtility.formatDate(l_datpreBizDate, "yyyyMMdd");
            l_ifoTradedProductUpdqParams.setValidForBizDate(l_strCreateDate); 
            l_ifoTradedProductUpdqParams.setUnitSize(3);
            TestDBUtility.insertWithDel(l_ifoTradedProductUpdqParams);
            
            WEB3IfoTradedProductImpl l_tradedProduct =
                new WEB3IfoTradedProductImpl(l_tradedProductParams.getTradedProductId());
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.ifo.WEB3IfoProductManagerImpl", 
                    "getTradedProduct",
                    new Class[]{ long.class, long.class},
                    l_tradedProduct);
            
            WEB3IfoContractImpl l_impl = new WEB3IfoContractImpl(l_ifoContractParams);
            
            double l_dblSettlementUnitPrice = 1;
            double l_dblSettlementCnt = 3;
            double l_dblEvaluateIncome =
                l_impl.getEvaluateIncome(l_dblSettlementUnitPrice, l_dblSettlementCnt);
            
            assertEquals("13.5", l_dblEvaluateIncome + "");
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    /**
     * get評価損益
     * 建区分が買建の場合
     *（パラメータ.返済単価 － getContractPrice()） × パラメータ.数量　@×　@(*1) 指数乗数
     */
    public void testGetEvaluateIncomeCase0002()
    {
        final String STR_METHOD_NAME = "testGetEvaluateIncomeCase0002()";
        log.entering(STR_METHOD_NAME);

        try
        {
            //MarketParams
            TestDBUtility.deleteAll(MarketRow.TYPE);
            MarketParams l_marketParams = TestDBUtility.getMarketRow();
            l_marketParams.setSonarMarketCode("8");
            TestDBUtility.insertWithDel(l_marketParams);
            
            //ProductParams
            TestDBUtility.deleteAll(ProductRow.TYPE);
            ProductParams l_productParams = TestDBUtility.getProductRow();
            l_productParams.setProductId(1006169090018L);
            l_productParams.setProductType(ProductTypeEnum.IFO);
            TestDBUtility.insertWithDel(l_productParams);
            
            TestDBUtility.deleteAll(IfoProductRow.TYPE);
            IfoProductParams l_ifoProductParams = TestDBUtility.getIfoProductRow();
            l_ifoProductParams.setProductId(1006169090018L);
            TestDBUtility.insertWithDel(l_ifoProductParams);
            
            TestDBUtility.deleteAll(TradedProductRow.TYPE);
            TradedProductParams l_tradedProductParams = TestDBUtility.getTradedProductRow();
            l_tradedProductParams.setProductId(1006169090018L);
            l_tradedProductParams.setProductType(ProductTypeEnum.IFO);
            l_tradedProductParams.setMarketId(3303);
            TestDBUtility.insertWithDel(l_tradedProductParams);
            
            TestDBUtility.deleteAll(IfoTradedProductRow.TYPE);
            IfoTradedProductParams l_ifoTradedProductParams = TestDBUtility.getIfoTradedProductRow();
            l_ifoTradedProductParams.setProductId(1006169090018L);
            l_ifoTradedProductParams.setTradedProductId(l_tradedProductParams.getTradedProductId());
            l_ifoTradedProductParams.setMarketId(3303);
            l_ifoTradedProductParams.setUnitSize(3);
            l_ifoTradedProductParams.setValidForBizDate(null);
            TestDBUtility.insertWithDel(l_ifoTradedProductParams);
            
            TestDBUtility.deleteAll(IfoContractRow.TYPE);
            IfoContractParams l_ifoContractParams = TestDBUtility.getIfoContractRow();
            l_ifoContractParams.setMarketId(3303);
            l_ifoContractParams.setProductId(1006169090018L);
            l_ifoContractParams.setContractType(ContractTypeEnum.LONG);
            l_ifoContractParams.setContractPrice(2.5);
            TestDBUtility.insertWithDel(l_ifoContractParams);
            
            TestDBUtility.deleteAll(IfoTradedProductUpdqRow.TYPE);
            IfoTradedProductUpdqParams l_ifoTradedProductUpdqParams = TestDBUtility.getIfoTradedProductUpdqRow();
            l_ifoTradedProductUpdqParams.setProductId(1006169090018L);
            Date l_datBizDate = GtlUtils.getTradingSystem().getSystemTimestamp();
            Date l_datpreBizDate = new WEB3GentradeBizDate(
                new Timestamp(l_datBizDate.getTime())).roll(1);
            String l_strCreateDate = WEB3DateUtility.formatDate(l_datpreBizDate, "yyyyMMdd");
            l_ifoTradedProductUpdqParams.setValidForBizDate(l_strCreateDate); 
            l_ifoTradedProductUpdqParams.setUnitSize(3);
            TestDBUtility.insertWithDel(l_ifoTradedProductUpdqParams);
            
            WEB3IfoTradedProductImpl l_tradedProduct =
                new WEB3IfoTradedProductImpl(l_tradedProductParams.getTradedProductId());
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.ifo.WEB3IfoProductManagerImpl", 
                    "getTradedProduct",
                    new Class[]{ long.class, long.class},
                    l_tradedProduct);
            
            WEB3IfoContractImpl l_impl = new WEB3IfoContractImpl(l_ifoContractParams);
            
            double l_dblSettlementUnitPrice = 6;
            double l_dblSettlementCnt = 3;
            double l_dblEvaluateIncome =
                l_impl.getEvaluateIncome(l_dblSettlementUnitPrice, l_dblSettlementCnt);
            
            assertEquals("31.5", l_dblEvaluateIncome + "");
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    /**
     * get建約定代金
     * getContractPrice() × パラメータ.数量 × 指数乗数(*1)
     */
    public void testGetContractExecutedAmountCase0001()
    {
        final String STR_METHOD_NAME = "testGetContractExecutedAmountCase0001()";
        log.entering(STR_METHOD_NAME);

        try
        {
            //MarketParams
            TestDBUtility.deleteAll(MarketRow.TYPE);
            MarketParams l_marketParams = TestDBUtility.getMarketRow();
            l_marketParams.setSonarMarketCode("8");
            TestDBUtility.insertWithDel(l_marketParams);
            
            //ProductParams
            TestDBUtility.deleteAll(ProductRow.TYPE);
            ProductParams l_productParams = TestDBUtility.getProductRow();
            l_productParams.setProductId(1006169090018L);
            l_productParams.setProductType(ProductTypeEnum.IFO);
            TestDBUtility.insertWithDel(l_productParams);
            
            TestDBUtility.deleteAll(IfoProductRow.TYPE);
            IfoProductParams l_ifoProductParams = TestDBUtility.getIfoProductRow();
            l_ifoProductParams.setProductId(1006169090018L);
            TestDBUtility.insertWithDel(l_ifoProductParams);
            
            TestDBUtility.deleteAll(TradedProductRow.TYPE);
            TradedProductParams l_tradedProductParams = TestDBUtility.getTradedProductRow();
            l_tradedProductParams.setProductId(1006169090018L);
            l_tradedProductParams.setProductType(ProductTypeEnum.IFO);
            l_tradedProductParams.setMarketId(3303);
            TestDBUtility.insertWithDel(l_tradedProductParams);
            
            TestDBUtility.deleteAll(IfoTradedProductRow.TYPE);
            IfoTradedProductParams l_ifoTradedProductParams = TestDBUtility.getIfoTradedProductRow();
            l_ifoTradedProductParams.setProductId(1006169090018L);
            l_ifoTradedProductParams.setTradedProductId(l_tradedProductParams.getTradedProductId());
            l_ifoTradedProductParams.setMarketId(3303);
            l_ifoTradedProductParams.setUnitSize(3);
            l_ifoTradedProductParams.setValidForBizDate(null);
            TestDBUtility.insertWithDel(l_ifoTradedProductParams);
            
            TestDBUtility.deleteAll(IfoContractRow.TYPE);
            IfoContractParams l_ifoContractParams = TestDBUtility.getIfoContractRow();
            l_ifoContractParams.setMarketId(3303);
            l_ifoContractParams.setProductId(1006169090018L);
            l_ifoContractParams.setContractType(ContractTypeEnum.LONG);
            l_ifoContractParams.setContractPrice(2.5);
            TestDBUtility.insertWithDel(l_ifoContractParams);
            
            TestDBUtility.deleteAll(IfoTradedProductUpdqRow.TYPE);
            IfoTradedProductUpdqParams l_ifoTradedProductUpdqParams = TestDBUtility.getIfoTradedProductUpdqRow();
            l_ifoTradedProductUpdqParams.setProductId(1006169090018L);
            Date l_datBizDate = GtlUtils.getTradingSystem().getSystemTimestamp();
            Date l_datpreBizDate = new WEB3GentradeBizDate(
                new Timestamp(l_datBizDate.getTime())).roll(1);
            String l_strCreateDate = WEB3DateUtility.formatDate(l_datpreBizDate, "yyyyMMdd");
            l_ifoTradedProductUpdqParams.setValidForBizDate(l_strCreateDate); 
            l_ifoTradedProductUpdqParams.setUnitSize(3);
            TestDBUtility.insertWithDel(l_ifoTradedProductUpdqParams);
            
            WEB3IfoTradedProductImpl l_tradedProduct =
                new WEB3IfoTradedProductImpl(l_tradedProductParams.getTradedProductId());
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.ifo.WEB3IfoProductManagerImpl", 
                    "getTradedProduct",
                    new Class[]{ long.class, long.class},
                    l_tradedProduct);
            
            WEB3IfoContractImpl l_impl = new WEB3IfoContractImpl(l_ifoContractParams);
            
            double l_dblCount = 3;
            double l_dblContractExecutedAmount =
                l_impl.getContractExecutedAmount(l_dblCount);
            
            assertEquals("22.5", l_dblContractExecutedAmount + "");
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    /**
     * get建手数料
     * get建委託手数料() * (パラメータ.数量 / get建玉数量())
     * 小数点以下切り捨て
     */
    public void testGetContractCommissionCase0001()
    {
        final String STR_METHOD_NAME = "testGetContractCommissionCase0001()";
        log.entering(STR_METHOD_NAME);

        try
        {
            //ProductParams
            TestDBUtility.deleteAll(ProductRow.TYPE);
            TestDBUtility.deleteAll(IfoProductRow.TYPE);
            TestDBUtility.deleteAll(TradedProductRow.TYPE);
            TestDBUtility.deleteAll(IfoTradedProductRow.TYPE);
            
            TestDBUtility.deleteAll(IfoContractRow.TYPE);
            IfoContractParams l_ifoContractParams = TestDBUtility.getIfoContractRow();
            l_ifoContractParams.setSetupFee(10);
            l_ifoContractParams.setQuantity(22.5);
            TestDBUtility.insertWithDel(l_ifoContractParams);
            
            WEB3IfoContractImpl l_impl = new WEB3IfoContractImpl(l_ifoContractParams);
            
            double l_dblContractCnt = 30;
            double l_dblContractExecutedAmount =
                l_impl.getContractCommission(l_dblContractCnt);
            
            assertEquals("13.0", l_dblContractExecutedAmount + "");
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    //(get建委託手数料() * パラメータ.数量) / get建玉数量()の計算結果を返却する
    public void testGetContractCommissionCase0002()
    {
        final String STR_METHOD_NAME = "testGetContractCommissionCase0002()";
        log.entering(STR_METHOD_NAME);

        try
        {
            //ProductParams
            TestDBUtility.deleteAll(ProductRow.TYPE);
            TestDBUtility.deleteAll(IfoProductRow.TYPE);
            TestDBUtility.deleteAll(TradedProductRow.TYPE);
            TestDBUtility.deleteAll(IfoTradedProductRow.TYPE);
            
            TestDBUtility.deleteAll(IfoContractRow.TYPE);
            IfoContractParams l_ifoContractParams = TestDBUtility.getIfoContractRow();
            l_ifoContractParams.setSetupFee(3000);
            l_ifoContractParams.setQuantity(3);
            TestDBUtility.insertWithDel(l_ifoContractParams);
            
            WEB3IfoContractImpl l_impl = new WEB3IfoContractImpl(l_ifoContractParams);
            
            double l_dblContractCnt = 1;
            double l_dblContractCommission =
                l_impl.getContractCommission(l_dblContractCnt);
            
            assertEquals(1000, l_dblContractCommission, 0);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    /**
     * get建手数料消費税
     * get建委託手数料消費税() * (パラメータ.数量 / get建玉数量())
     * 小数点以下切り捨て
     */
    public void testGetContractCommissionConsumptionTaxCase0001()
    {
        final String STR_METHOD_NAME = "testGetContractCommissionConsumptionTaxCase0001()";
        log.entering(STR_METHOD_NAME);

        try
        {
            //ProductParams
            TestDBUtility.deleteAll(ProductRow.TYPE);
            TestDBUtility.deleteAll(IfoProductRow.TYPE);
            TestDBUtility.deleteAll(TradedProductRow.TYPE);
            TestDBUtility.deleteAll(IfoTradedProductRow.TYPE);
            
            TestDBUtility.deleteAll(IfoContractRow.TYPE);
            IfoContractParams l_ifoContractParams = TestDBUtility.getIfoContractRow();
            l_ifoContractParams.setSetupFee(10);
            l_ifoContractParams.setSetupFeeTax(20);
            l_ifoContractParams.setQuantity(22.5);
            TestDBUtility.insertWithDel(l_ifoContractParams);
            
            WEB3IfoContractImpl l_impl = new WEB3IfoContractImpl(l_ifoContractParams);
            
            double l_dblContractCnt = 30;
            double l_dblContractExecutedAmount =
                l_impl.getContractCommissionConsumptionTax(l_dblContractCnt);
            
            assertEquals("26.0", l_dblContractExecutedAmount + "");
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    /**
     * get建手数料消費税
     * (get建委託手数料消費税() * パラメータ.数量) / get建玉数量()
     * 小数点以下切り捨て
     */
    public void testGetContractCommissionConsumptionTaxCase0002()
    {
        final String STR_METHOD_NAME = "testGetContractCommissionConsumptionTaxCase0002()";
        log.entering(STR_METHOD_NAME);

        try
        {
            //ProductParams
            TestDBUtility.deleteAll(ProductRow.TYPE);
            TestDBUtility.deleteAll(IfoProductRow.TYPE);
            TestDBUtility.deleteAll(TradedProductRow.TYPE);
            TestDBUtility.deleteAll(IfoTradedProductRow.TYPE);
            
            TestDBUtility.deleteAll(IfoContractRow.TYPE);
            IfoContractParams l_ifoContractParams = TestDBUtility.getIfoContractRow();
            l_ifoContractParams.setSetupFee(10);
            l_ifoContractParams.setSetupFeeTax(3000);
            l_ifoContractParams.setQuantity(3);
            TestDBUtility.insertWithDel(l_ifoContractParams);
            
            WEB3IfoContractImpl l_impl = new WEB3IfoContractImpl(l_ifoContractParams);
            
            double l_dblContractCnt = 1;
            double l_dblContractCommissionConsumptionTax =
                l_impl.getContractCommissionConsumptionTax(l_dblContractCnt);
            
            assertEquals(1000, l_dblContractCommissionConsumptionTax, 0);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    /*
     * this.建玉ID = 1001
     * this.建玉数 = 22.5
     * 決済約定数量 = 20
     */
    public void testGetContractCommission_C0001()
    {
        final String STR_METHOD_NAME = "testGetContractCommission_C0001()";
        log.entering(STR_METHOD_NAME);

        try
        {
            TestDBUtility.deleteAll(IfoContractRow.TYPE);
            IfoContractParams l_ifoContractParams = TestDBUtility.getIfoContractRow();
            l_ifoContractParams.setSetupFee(10);
            l_ifoContractParams.setQuantity(22.5);
            l_ifoContractParams.setContractId(1002);
            TestDBUtility.insertWithDel(l_ifoContractParams);
            
            TestDBUtility.deleteAll(IfoFinTransactionRow.TYPE);
            IfoFinTransactionParams l_ifoFinTransactionParams = TestDBUtility.getIfoFinTransactionRow();
            l_ifoFinTransactionParams.setOrderUnitId(1001);
            l_ifoFinTransactionParams.setContractId(1002);
            l_ifoFinTransactionParams.setDeleteFlag(BooleanEnum.FALSE);
            l_ifoFinTransactionParams.setImportedSetupFee(30);
            TestDBUtility.insertWithDel(l_ifoFinTransactionParams);
            
            WEB3IfoContractImpl l_impl = new WEB3IfoContractImplForTest(l_ifoContractParams);
            double l_dblQuantity = 100;
            long l_lngOrderUnitId = 1001;
            
            double l_dblReturnValue = l_impl.getContractCommission(l_dblQuantity, l_lngOrderUnitId);
            
            assertEquals("65.0", l_dblReturnValue + "");
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    /*
     * this.建玉ID = 1001
     * this.建玉数 = 22.5
     * 決済約定数量 = 20
     */
    public void testGetContractCommissionConsumptionTax_C0001()
    {
        final String STR_METHOD_NAME = "testGetContractCommissionConsumptionTax_C0001()";
        log.entering(STR_METHOD_NAME);

        try
        {
            TestDBUtility.deleteAll(IfoContractRow.TYPE);
            IfoContractParams l_ifoContractParams = TestDBUtility.getIfoContractRow();
            l_ifoContractParams.setSetupFeeTax(5);
            l_ifoContractParams.setQuantity(22.5);
            l_ifoContractParams.setContractId(1002);
            TestDBUtility.insertWithDel(l_ifoContractParams);
            
            TestDBUtility.deleteAll(IfoFinTransactionRow.TYPE);
            IfoFinTransactionParams l_ifoFinTransactionParams = TestDBUtility.getIfoFinTransactionRow();
            l_ifoFinTransactionParams.setOrderUnitId(1001);
            l_ifoFinTransactionParams.setContractId(1002);
            l_ifoFinTransactionParams.setDeleteFlag(BooleanEnum.FALSE);
            l_ifoFinTransactionParams.setImportedSetupFeeTax(15);
            TestDBUtility.insertWithDel(l_ifoFinTransactionParams);
            
            WEB3IfoContractImpl l_impl = new WEB3IfoContractImplForTest(l_ifoContractParams);
            double l_dblQuantity = 100;
            long l_lngOrderUnitId = 1001;
            
            double l_dblReturnValue = l_impl.getContractCommissionConsumptionTax(l_dblQuantity, l_lngOrderUnitId);
            
            assertEquals("32.0", l_dblReturnValue + "");
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    private class WEB3IfoContractImplForTest extends WEB3IfoContractImpl
    {

        public WEB3IfoContractImplForTest(IfoContractRow l_row)
        {
            super(l_row);
            // TODO Auto-generated constructor stub
        }
        public double getClosingExecuteContractCnt(long l_lngOrderUnitId)
        {
            return 20;
            
        }
    }
}
@
