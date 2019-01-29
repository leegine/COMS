head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.01.13.01;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3EquityTradedProductTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 取引銘柄テスト(WEB3EquityTradedProductTest.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/02/12 趙林鵬 (中訊) 新規作成
*/

package webbroker3.equity;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;

import test.util.TestDBUtility;
import webbroker3.common.define.WEB3ListTypeDef;
import webbroker3.gentrade.WEB3GentradeBizDate;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagementForMock;
import webbroker3.gentrade.data.LastClosingPriceParams;
import webbroker3.gentrade.data.LastClosingPriceRow;
import webbroker3.gentrade.data.TradingTimeParams;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeProductParams;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeProductRow;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeTradedProductParams;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeTradedProductRow;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeTradedProductUpdqParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccountTypeEnum;
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
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.TradedProductCalendarRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.TradedProductDao;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.TradedProductParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.TradedProductRow;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoContractRow;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoOrderRow;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoOrderUnitRow;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoProductRow;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoTradedProductRow;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoTradedProductUpdqRow;

/**
 * （取引銘柄テスト）<BR>
 *
 * @@author 趙林鵬
 * @@version 1.0
 */

public class WEB3EquityTradedProductTest extends TestBaseForMock
{
    /**
     * ログユーティリティ<BR>
     */
    private static WEB3LogUtility log =
         WEB3LogUtility.getInstance(WEB3EquityTradedProductTest.class);
    
    public WEB3EquityTradedProductTest(String arg0)
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

    public void test_getProductQuote_c0001()
    {
        final String STR_METHOD_NAME = " test_getProductQuote_c0001";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        try
        {
            MOCK_MANAGER.setIsMockUsed(true);
            
            boolean l_blnExpectValue = false;
            WEB3GentradeTradingTimeManagementForMock.mockIsTradeOpenTimeZone(l_blnExpectValue);
            
            Date l_datExpect = WEB3DateUtility.getDate("20040710","yyyyMMdd");
            WEB3GentradeTradingTimeManagementForMock.mockGetOrderBizDate(l_datExpect);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue("webbroker3.gentrade.WEB3GentradeTradingCalendarModelImpl",
                    "getCurrentBizDate",
                    new Class[] {long.class},
                    l_datExpect);
            
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            ProductParams l_productParams = TestDBUtility.getProductRow();
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            EqtypeTradedProductParams l_eqtypeTradedProductParams = TestDBUtility.getEqtypeTradedProductRow();
            l_eqtypeTradedProductParams.setValidUntilBizDate("20040709");
            EqtypeProductParams l_eqtypeProductParams = TestDBUtility.getEqtypeProductRow();
            TradedProductParams l_tradedProductParams = TestDBUtility.getTradedProductRow();
            MarketParams l_marketParams = TestDBUtility.getMarketRow();
            LastClosingPriceParams l_lastClosingPriceParams = TestDBUtility.getLastClosingPriceRow();
            
            l_mainAccountParams.setAccountId(101001010010L);
            
            l_subAccountParams.setAccountId(101001010010L);
            l_subAccountParams.setSubAccountId(10100101001007L);
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_MARGIN_SUB_ACCOUNT);
      
            l_productParams.setProductId(1006169090018L);
            l_productParams.setProductType(ProductTypeEnum.EQUITY);
            l_productParams.setPrimaryMarketId(3303L);
            
            l_eqtypeTradedProductParams.setProductId(1006169090018L);
            l_eqtypeTradedProductParams.setTradedProductId(100106139070605L);
            l_eqtypeTradedProductParams.setLastClosingPrice(10);
            
            l_eqtypeProductParams.setProductId(1006169090018L);
            l_eqtypeProductParams.setProductType(ProductTypeEnum.EQUITY);
            
            l_tradedProductParams.setProductId(1006169090018L);
            l_tradedProductParams.setTradedProductId(100106139070605L);
            l_tradedProductParams.setProductType(ProductTypeEnum.EQUITY);
            
            l_lastClosingPriceParams.setBaseDate(WEB3DateUtility.getDate("20040709","yyyyMMdd"));
            
            this.deleteAll();
            TestDBUtility.insertWithDel(l_mainAccountParams);
            TestDBUtility.insertWithDel(l_subAccountParams);
            TestDBUtility.insertWithDel(l_institutionParams);
            TestDBUtility.insertWithDel(l_productParams);
            TestDBUtility.insertWithDel(l_eqtypeTradedProductParams);
            TestDBUtility.insertWithDel(l_eqtypeProductParams);
            TestDBUtility.insertWithDel(l_tradedProductParams);
            TestDBUtility.insertWithDel(l_marketParams);
            TestDBUtility.insertWithDel(l_lastClosingPriceParams);
            TestDBUtility.deleteAll(EqtypeTradedProductUpdqParams.TYPE);

            EqtypeTradedProductUpdqParams l_eqtypeTradedProductUpdqParam = new EqtypeTradedProductUpdqParams();
            l_eqtypeTradedProductUpdqParam.setTradedProductId(100106139070605L);
            l_eqtypeTradedProductUpdqParam.setInstitutionCode("0D");
            l_eqtypeTradedProductUpdqParam.setProductId(1006169090018L);
            l_eqtypeTradedProductUpdqParam.setMarketId(3303);               
            l_eqtypeTradedProductUpdqParam.setValidUntilBizDate(WEB3DateUtility.formatDate(l_datExpect, "yyyyMMdd"));
            l_eqtypeTradedProductUpdqParam.setListFlag(BooleanEnum.FALSE);
            l_eqtypeTradedProductUpdqParam.setListType(WEB3ListTypeDef.FIRST_SECTION);
            l_eqtypeTradedProductUpdqParam.setNewListType("1");
            l_eqtypeTradedProductUpdqParam.setListedDate(Calendar.getInstance().getTime());
            l_eqtypeTradedProductUpdqParam.setMarginableFlag(BooleanEnum.TRUE);
            l_eqtypeTradedProductUpdqParam.setShortableFlag(BooleanEnum.TRUE);
            l_eqtypeTradedProductUpdqParam.setMiniStockCanDealt(BooleanEnum.TRUE);
            l_eqtypeTradedProductUpdqParam.setLotSize(12D);
            l_eqtypeTradedProductUpdqParam.setLastClosingPrice(12);
            l_eqtypeTradedProductUpdqParam.setMiniStockFlag(BooleanEnum.TRUE);
            l_eqtypeTradedProductUpdqParam.setBasePrice(123);
            l_eqtypeTradedProductUpdqParam.setCreatedTimestamp(Calendar.getInstance().getTime());
            l_eqtypeTradedProductUpdqParam.setLastUpdatedTimestamp(Calendar.getInstance().getTime());
            TestDBUtility.insertWithDel(l_eqtypeTradedProductUpdqParam);
            
            WEB3GentradeSubAccount l_subAccount = new WEB3GentradeSubAccount(101001010010L, 10100101001007L);
            
            TradedProductRow l_tradedProductRow = TradedProductDao.findRowByPk(100106139070605L);
            
            WEB3EquityTradedProduct l_equityTradedProduct = new WEB3EquityTradedProduct(l_tradedProductRow);
            
            WEB3EquityProductQuote l_equityProductQuote = l_equityTradedProduct.getProductQuote(l_subAccount);
            
            assertTrue(!Double.isNaN(l_equityProductQuote.getComparedPreviousDay()));
        
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
        log.exiting(TEST_END + STR_METHOD_NAME); 
    }
    
    public void test_getProductQuote_c0002()
    {
        final String STR_METHOD_NAME = " test_getProductQuote_c0002";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        try
        {
            MOCK_MANAGER.setIsMockUsed(true);
            
            boolean l_blnExpectValue = false;
            WEB3GentradeTradingTimeManagementForMock.mockIsTradeOpenTimeZone(l_blnExpectValue);
            
//            WEB3DateUtility.formatDate(Calendar.getInstance().getTime(),"yyyyMMdd");
            
            Date l_datExpect = WEB3DateUtility.getDate(WEB3DateUtility.formatDate(Calendar.getInstance().getTime(),"yyyyMMdd"),"yyyyMMdd");
            WEB3GentradeTradingTimeManagementForMock.mockGetOrderBizDate(l_datExpect);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue("webbroker3.gentrade.WEB3GentradeTradingCalendarModelImpl",
                    "getCurrentBizDate",
                    new Class[] {long.class},
                    l_datExpect);
            
//            WEB3GentradeTradingTimeManagementForMock
            
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            ProductParams l_productParams = TestDBUtility.getProductRow();
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            EqtypeTradedProductParams l_eqtypeTradedProductParams = TestDBUtility.getEqtypeTradedProductRow();
            EqtypeProductParams l_eqtypeProductParams = TestDBUtility.getEqtypeProductRow();
            TradedProductParams l_tradedProductParams = TestDBUtility.getTradedProductRow();
            MarketParams l_marketParams = TestDBUtility.getMarketRow();
            LastClosingPriceParams l_lastClosingPriceParams = TestDBUtility.getLastClosingPriceRow();
            
            l_mainAccountParams.setAccountId(101001010010L);
            
            l_subAccountParams.setAccountId(101001010010L);
            l_subAccountParams.setSubAccountId(10100101001007L);
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_MARGIN_SUB_ACCOUNT);
      
            l_productParams.setProductId(1006169090018L);
            l_productParams.setProductType(ProductTypeEnum.EQUITY);
            l_productParams.setPrimaryMarketId(3303L);
            
            l_eqtypeTradedProductParams.setProductId(1006169090018L);
            l_eqtypeTradedProductParams.setTradedProductId(100106139070605L);
            l_eqtypeTradedProductParams.setMarketId(3303L);
            l_eqtypeTradedProductParams.setValidUntilBizDate("20071116");
            
            l_eqtypeProductParams.setProductId(1006169090018L);
            l_eqtypeProductParams.setProductType(ProductTypeEnum.EQUITY);
            
            l_tradedProductParams.setProductId(1006169090018L);
            l_tradedProductParams.setTradedProductId(100106139070605L);
            l_tradedProductParams.setProductType(ProductTypeEnum.EQUITY);
            
            l_lastClosingPriceParams.setBaseDate(WEB3DateUtility.getDate("20071114","yyyyMMdd"));
            l_lastClosingPriceParams.setTokyoClosingPrice(0);
            l_lastClosingPriceParams.setOosakaClosingPrice(0);
            l_lastClosingPriceParams.setNagoyaClosingPrice(0);
            l_lastClosingPriceParams.setOtherClosingPrice(900);
            //
            l_lastClosingPriceParams.setPrimaryClosingPrice(10);
            
            this.deleteAll();
            TestDBUtility.insertWithDel(l_mainAccountParams);
            TestDBUtility.insertWithDel(l_subAccountParams);
            TestDBUtility.insertWithDel(l_institutionParams);
            TestDBUtility.insertWithDel(l_productParams);
            TestDBUtility.insertWithDel(l_eqtypeTradedProductParams);
            TestDBUtility.insertWithDel(l_eqtypeProductParams);
            TestDBUtility.insertWithDel(l_tradedProductParams);
            TestDBUtility.insertWithDel(l_marketParams);
            TestDBUtility.insertWithDel(l_lastClosingPriceParams);


            TestDBUtility.deleteAll(EqtypeTradedProductUpdqParams.TYPE);
            EqtypeTradedProductUpdqParams l_eqtypeTradedProductUpdqParam = new EqtypeTradedProductUpdqParams();
            l_eqtypeTradedProductUpdqParam.setTradedProductId(100106139070605L);
            l_eqtypeTradedProductUpdqParam.setInstitutionCode("0D");
            l_eqtypeTradedProductUpdqParam.setProductId(1006169090018L);
            l_eqtypeTradedProductUpdqParam.setMarketId(3303);
            l_eqtypeTradedProductUpdqParam.setValidUntilBizDate(WEB3DateUtility.formatDate(l_datExpect, "yyyyMMdd"));
            l_eqtypeTradedProductUpdqParam.setListFlag(BooleanEnum.FALSE);
            l_eqtypeTradedProductUpdqParam.setListType(WEB3ListTypeDef.FIRST_SECTION);
            l_eqtypeTradedProductUpdqParam.setNewListType("1");
            l_eqtypeTradedProductUpdqParam.setListedDate(Calendar.getInstance().getTime());
            l_eqtypeTradedProductUpdqParam.setMarginableFlag(BooleanEnum.TRUE);
            l_eqtypeTradedProductUpdqParam.setShortableFlag(BooleanEnum.TRUE);
            l_eqtypeTradedProductUpdqParam.setMiniStockCanDealt(BooleanEnum.TRUE);
            l_eqtypeTradedProductUpdqParam.setLotSize(12D);
            l_eqtypeTradedProductUpdqParam.setLastClosingPrice(12);
            l_eqtypeTradedProductUpdqParam.setMiniStockFlag(BooleanEnum.TRUE);
            l_eqtypeTradedProductUpdqParam.setBasePrice(123);
            l_eqtypeTradedProductUpdqParam.setCreatedTimestamp(Calendar.getInstance().getTime());
            l_eqtypeTradedProductUpdqParam.setLastUpdatedTimestamp(Calendar.getInstance().getTime());
            TestDBUtility.insertWithDel(l_eqtypeTradedProductUpdqParam);
            
            TestDBUtility.deleteAll(TradingTimeParams.TYPE);
            TradingTimeParams l_tradingTimeParams = new TradingTimeParams();

            l_tradingTimeParams.setInstitutionCode("0D");
            l_tradingTimeParams.setBranchCode("123");
            l_tradingTimeParams.setMarketCode("N1");
            l_tradingTimeParams.setTradingTimeType("01");
            l_tradingTimeParams.setProductCode("0");
            l_tradingTimeParams.setBizDateType("1");
            l_tradingTimeParams.setStartTime("000000");
            l_tradingTimeParams.setEndTime("00000");        
            l_tradingTimeParams.setSubmitMarketTrigger("1");
            l_tradingTimeParams.setEnableOrder("0");
            l_tradingTimeParams.setBizdateCalcParameter("0");
            l_tradingTimeParams.setCreatedTimestamp(Calendar.getInstance().getTime());
            l_tradingTimeParams.setLastUpdatedTimestamp(Calendar.getInstance().getTime());
            
            TestDBUtility.insertWithDel(l_tradingTimeParams);
            TestDBUtility.commit();
            
            WEB3GentradeSubAccount l_subAccount = new WEB3GentradeSubAccount(101001010010L, 10100101001007L);
            
            TradedProductRow l_tradedProductRow = TradedProductDao.findRowByPk(100106139070605L);
            
            WEB3EquityTradedProduct l_equityTradedProduct = new WEB3EquityTradedProduct(l_tradedProductRow);
            
            WEB3EquityProductQuote l_equityProductQuote = l_equityTradedProduct.getProductQuote(l_subAccount);
            
//            assertEquals("-990.0","" + l_equityProductQuote.getComparedPreviousDay());
            assertEquals("NaN","" + l_equityProductQuote.getComparedPreviousDay());
        
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
        log.exiting(TEST_END + STR_METHOD_NAME); 
    }
    
    public void test_getProductQuote_c0003()
    {
        final String STR_METHOD_NAME = " test_getProductQuote_c0003";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        try
        {
            MOCK_MANAGER.setIsMockUsed(true);
            
            boolean l_blnExpectValue = true;
            WEB3GentradeTradingTimeManagementForMock.mockIsTradeOpenTimeZone(l_blnExpectValue);
            
            Date l_datExpect = WEB3DateUtility.getDate("20040710","yyyyMMdd");
            WEB3GentradeTradingTimeManagementForMock.mockGetOrderBizDate(l_datExpect);
            
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            ProductParams l_productParams = TestDBUtility.getProductRow();
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            EqtypeTradedProductParams l_eqtypeTradedProductParams = TestDBUtility.getEqtypeTradedProductRow();
            EqtypeProductParams l_eqtypeProductParams = TestDBUtility.getEqtypeProductRow();
            TradedProductParams l_tradedProductParams = TestDBUtility.getTradedProductRow();
            MarketParams l_marketParams = TestDBUtility.getMarketRow();
            LastClosingPriceParams l_lastClosingPriceParams = TestDBUtility.getLastClosingPriceRow();
            
            l_mainAccountParams.setAccountId(101001010010L);
            
            l_subAccountParams.setAccountId(101001010010L);
            l_subAccountParams.setSubAccountId(10100101001007L);
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_MARGIN_SUB_ACCOUNT);
      
            l_productParams.setProductId(1006169090018L);
            l_productParams.setProductType(ProductTypeEnum.EQUITY);
            l_productParams.setPrimaryMarketId(3303L);
            
            l_eqtypeTradedProductParams.setProductId(1006169090018L);
            l_eqtypeTradedProductParams.setTradedProductId(100106139070605L);
            
            l_eqtypeProductParams.setProductId(1006169090018L);
            l_eqtypeProductParams.setProductType(ProductTypeEnum.EQUITY);
            
            l_tradedProductParams.setProductId(1006169090018L);
            l_tradedProductParams.setTradedProductId(100106139070605L);
            l_tradedProductParams.setProductType(ProductTypeEnum.EQUITY);
            
            l_lastClosingPriceParams.setBaseDate(WEB3DateUtility.getDate("20040709","yyyyMMdd"));
            l_lastClosingPriceParams.setTokyoClosingPrice(0);
            l_lastClosingPriceParams.setOosakaClosingPrice(0);
            l_lastClosingPriceParams.setNagoyaClosingPrice(0);
            l_lastClosingPriceParams.setOtherClosingPrice(900);
            l_lastClosingPriceParams.setPrimaryClosingPrice(0);
            
            this.deleteAll();
            TestDBUtility.insertWithDel(l_mainAccountParams);
            TestDBUtility.insertWithDel(l_subAccountParams);
            TestDBUtility.insertWithDel(l_institutionParams);
            TestDBUtility.insertWithDel(l_productParams);
            TestDBUtility.insertWithDel(l_eqtypeTradedProductParams);
            TestDBUtility.insertWithDel(l_eqtypeProductParams);
            TestDBUtility.insertWithDel(l_tradedProductParams);
            TestDBUtility.insertWithDel(l_marketParams);
            TestDBUtility.insertWithDel(l_lastClosingPriceParams);
            TestDBUtility.commit();
            WEB3GentradeSubAccount l_subAccount = new WEB3GentradeSubAccount(101001010010L, 10100101001007L);
            
            TradedProductRow l_tradedProductRow = TradedProductDao.findRowByPk(100106139070605L);
            
            WEB3EquityTradedProduct l_equityTradedProduct = new WEB3EquityTradedProduct(l_tradedProductRow);
            
            WEB3EquityProductQuote l_equityProductQuote = l_equityTradedProduct.getProductQuote(l_subAccount);
            
            assertTrue(Double.isNaN(l_equityProductQuote.getComparedPreviousDay()));
        
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
        log.exiting(TEST_END + STR_METHOD_NAME); 
    }
    
    public void deleteAll()
    {
        try
        {
            TestDBUtility.deleteAll(IfoOrderUnitRow.TYPE);
            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            TestDBUtility.deleteAll(IfoOrderRow.TYPE);
            TestDBUtility.deleteAll(ProductRow.TYPE);
            TestDBUtility.deleteAll(IfoProductRow.TYPE);
            TestDBUtility.deleteAll(MarketRow.TYPE);
            TestDBUtility.deleteAll(IfoContractRow.TYPE);
            TestDBUtility.deleteAll(IfoTradedProductRow.TYPE);
            TestDBUtility.deleteAll(BranchRow.TYPE);
            TestDBUtility.deleteAll(InstitutionRow.TYPE);
            TestDBUtility.deleteAll(TradedProductRow.TYPE);
            TestDBUtility.deleteAll(IfoTradedProductUpdqRow.TYPE);
            TestDBUtility.deleteAll(TradedProductCalendarRow.TYPE);
            TestDBUtility.deleteAll(EqtypeTradedProductRow.TYPE);
            TestDBUtility.deleteAll(EqtypeProductRow.TYPE);
            TestDBUtility.deleteAll(TradedProductRow.TYPE);
            TestDBUtility.deleteAll(LastClosingPriceRow.TYPE);
            
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.info("*******************deleteAll***************** !!");
    }
    
}
@
