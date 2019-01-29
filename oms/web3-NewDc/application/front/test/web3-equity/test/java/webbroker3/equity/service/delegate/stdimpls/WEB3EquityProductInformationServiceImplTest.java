head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.00.55.08;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3EquityProductInformationServiceImplTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 株式銘柄情報表示サービスImplTest(WEB3EquityProductInformationServiceImplTest.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/12/28 趙林鵬(中訊) 新規作成
*/

package webbroker3.equity.service.delegate.stdimpls;

import java.sql.Timestamp;
import java.util.Date;

import com.fitechlabs.xtrade.kernel.util.ThreadLocalSystemAttributesRegistry;
import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeProduct;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeProductParams;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeProductRow;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeTradedProductParams;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeTradedProductUpdqParams;
import com.fitechlabs.xtrade.plugin.tc.eqtype.stdimpls.EqTypeTradedProductImpl;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Market;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccountTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradedProduct;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.BranchParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.BranchRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.InstitutionParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.InstitutionRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MarketParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MarketPreferencesParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MarketPreferencesRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MarketRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.ProductParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.ProductRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.SubAccountParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.SubAccountRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.TradedProductParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.TradedProductUpdqParams;

import test.util.TestDBUtility;

import webbroker3.equity.WEB3EquityTradedProduct;
import webbroker3.equity.data.EquityLimitPriceRangeMstParams;
import webbroker3.equity.data.EquityLimitPriceRangeMstRow;
import webbroker3.equity.data.EquityTickValuesMstParams;
import webbroker3.equity.data.EquityTickValuesMstRow;
import webbroker3.equity.message.WEB3EquityProductInformationRequest;
import webbroker3.equity.message.WEB3EquityProductInformationResponse;
import webbroker3.gentrade.WEB3GentradeBizDate;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagementForMock;
import webbroker3.gentrade.data.TradingTimeParams;
import webbroker3.gentrade.data.TradingTimeRow;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

public class WEB3EquityProductInformationServiceImplTest extends TestBaseForMock
{
    /**
     * ログ出力ユーティリティ。<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3EquityProductInformationServiceImplTest.class);

    public WEB3EquityProductInformationServiceImplTest(String arg0)
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
    
    /**
     * execute
     * リクエスト.市場コード ≠ null の場合
     * PTS市場でない場合（isPTS市場() == false）
     * 値幅チェック対象銘柄の場合
     */
    public void testExecuteCase0001()
    {
        final String STR_METHOD_NAME = "testExecuteCase0001()";
        log.entering(STR_METHOD_NAME);
        MOCK_MANAGER.setIsMockUsed(true);
        try
        {
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                    "getAccountId",
                    new Class[] {},
                    new Long(101001010010L));

            TestDBUtility.deleteAll(TradingTimeRow.TYPE);
            WEB3GentradeTradingTimeManagementForMock.mockValidateOrderAccept(true);
            
            Date l_datBizDate = WEB3DateUtility.getDate("20071227", "yyyyMMdd");
            WEB3GentradeTradingTimeManagementForMock.mockGetOrderBizDate(l_datBizDate);
            //MainAccountParams
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(101001010010L);
            TestDBUtility.insertWithDel(l_mainAccountParams);
            
            //SubAccountParams
            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setAccountId(101001010010L);
            l_subAccountParams.setSubAccountId(10100101001007L);
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
            TestDBUtility.insertWithDel(l_subAccountParams);

            //BranchParams
            TestDBUtility.deleteAll(BranchRow.TYPE);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setCloseWorngEquityMargin(1200);
            TestDBUtility.insertWithDel(l_branchParams);
            
            //InstitutionParams
            TestDBUtility.deleteAll(InstitutionRow.TYPE);
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            TestDBUtility.insertWithDel(l_institutionParams);
            
            TestDBUtility.deleteAll(MarketRow.TYPE);
            MarketParams l_marketRow = TestDBUtility.getMarketRow();
            l_marketRow.setMarketCode("1");
            TestDBUtility.insertWithDel(l_marketRow);
            
            //ProductParams
            TestDBUtility.deleteAll(ProductRow.TYPE);
            ProductParams l_productParams = TestDBUtility.getProductRow();
            l_productParams.setProductId(1006169090018L);
            l_productParams.setProductType(ProductTypeEnum.EQUITY);
            TestDBUtility.insertWithDel(l_productParams);
            
            TestDBUtility.deleteAll(EqtypeProductRow.TYPE);
            EqtypeProductParams l_EqtypeProductParams = TestDBUtility.getEqtypeProductRow();
            l_EqtypeProductParams.setProductId(1006169090018L);
            l_EqtypeProductParams.setProductCode("0");
            l_EqtypeProductParams.setYearlyBooksClosingDate(WEB3DateUtility.getDate("20080101", "yyyyMMdd"));
            TestDBUtility.insertWithDel(l_EqtypeProductParams);
            
            TestDBUtility.deleteAll(EqtypeTradedProductParams.TYPE);
            EqtypeTradedProductParams l_eqtypeTradedProductParams = TestDBUtility.getEqtypeTradedProductRow();
            l_eqtypeTradedProductParams.setProductId(1006169090018L);
            l_eqtypeTradedProductParams.setMarketId(3303);
            l_eqtypeTradedProductParams.setPriceRangeType("1");
            Date l_datSystemTimestamp = GtlUtils.getTradingSystem().getSystemTimestamp();
            Date l_datpreBizDate = new WEB3GentradeBizDate(
                new Timestamp(l_datSystemTimestamp.getTime())).roll(1);
            l_eqtypeTradedProductParams.setValidUntilBizDate(null);
            TestDBUtility.insertWithDel(l_eqtypeTradedProductParams);
            
            TestDBUtility.deleteAll(TradedProductParams.TYPE);
            TradedProductParams l_tradedProductParams = TestDBUtility.getTradedProductRow();
            l_tradedProductParams.setProductId(1006169090018L);
            l_tradedProductParams.setMarketId(3303);
            l_tradedProductParams.setProductType(ProductTypeEnum.EQUITY);
            TestDBUtility.insertWithDel(l_tradedProductParams);
            
            TradedProduct tradeProduct = new WEB3EquityTradedProduct(l_tradedProductParams);
            //WEB3EquityPTSOrderManagerForMock
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.equity.WEB3EquityOrderManager",
                    "validateTradedProduct",
                    new Class[] {EqTypeProduct.class,Market.class},
                    tradeProduct);
            
            TestDBUtility.deleteAll(MarketPreferencesRow.TYPE);
            
            TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams.setInstitutionCode("0D");
            l_tradingTimeParams.setBranchCode("123");
            l_tradingTimeParams.setBizDateType("1");
            l_tradingTimeParams.setMarketCode("1");
            l_tradingTimeParams.setProductCode("0");
            l_tradingTimeParams.setTradingTimeType("01");
            TestDBUtility.insertWithDel(l_tradingTimeParams);
            
            TradingTimeParams l_tradingTimeParams1 = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams1.setInstitutionCode("0D");
            l_tradingTimeParams1.setBranchCode("123");
            l_tradingTimeParams1.setBizDateType("1");
            l_tradingTimeParams1.setMarketCode("0");
            l_tradingTimeParams1.setProductCode("0");
            l_tradingTimeParams1.setTradingTimeType("05");
            TestDBUtility.insertWithDel(l_tradingTimeParams1);
            
            TradingTimeParams l_tradingTimeParams2 = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams2.setInstitutionCode("0D");
            l_tradingTimeParams2.setBranchCode("123");
            l_tradingTimeParams2.setBizDateType("1");
            l_tradingTimeParams2.setMarketCode("1");
            l_tradingTimeParams2.setProductCode("0");
            l_tradingTimeParams2.setTradingTimeType("19");
            TestDBUtility.insertWithDel(l_tradingTimeParams2);
            
            TestDBUtility.deleteAll(EquityLimitPriceRangeMstRow.TYPE);
            EquityLimitPriceRangeMstParams l_params = TestDBUtility.getEquityLimitPriceRangeMstRow();
            l_params.setLowPrice(0);
            l_params.setCapPrice(100);
            TestDBUtility.insertWithDel(l_params);
            
            TestDBUtility.deleteAll(EquityTickValuesMstRow.TYPE);
            EquityTickValuesMstParams l_equityTickValuesMstParams = TestDBUtility.getEquityTickValuesMstRow();
            l_equityTickValuesMstParams.setCapPrice(200);
            l_equityTickValuesMstParams.setLowPrice(0);
            TestDBUtility.insertWithDel(l_equityTickValuesMstParams);
            TestDBUtility.commit();
            WEB3EquityProductInformationRequest l_request =
                new WEB3EquityProductInformationRequest();
            
            l_request.marketCode = "1";
            l_request.productCode = "0";

            WEB3EquityProductInformationServiceImpl l_impl = new WEB3EquityProductInformationServiceImpl();
            WEB3EquityProductInformationResponse l_response =
                (WEB3EquityProductInformationResponse)l_impl.execute(l_request);
            assertEquals(l_response.upperPriceRange, "200");
            assertEquals(l_response.lowerPriceRange, "1");

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
     * execute
     * リクエスト.市場コード ≠ null の場合
     * PTS市場の場合（isPTS市場() == true）
     * 値幅チェック対象銘柄の場合
     */
    public void testExecuteCase0002()
    {
        final String STR_METHOD_NAME = "testExecuteCase0002()";
        log.entering(STR_METHOD_NAME);
        MOCK_MANAGER.setIsMockUsed(true);
        try
        {
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                    "getAccountId",
                    new Class[] {},
                    new Long(101001010010L));
            
            TestDBUtility.deleteAll(TradingTimeRow.TYPE);
            WEB3GentradeTradingTimeManagementForMock.mockValidateOrderAccept(true);
            
            Date l_datBizDate = WEB3DateUtility.getDate("20071227", "yyyyMMdd");
            WEB3GentradeTradingTimeManagementForMock.mockGetOrderBizDate(l_datBizDate);
            //MainAccountParams
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(101001010010L);
            TestDBUtility.insertWithDel(l_mainAccountParams);
            
            //SubAccountParams
            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setAccountId(101001010010L);
            l_subAccountParams.setSubAccountId(10100101001007L);
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
            TestDBUtility.insertWithDel(l_subAccountParams);

            //BranchParams
            TestDBUtility.deleteAll(BranchRow.TYPE);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setCloseWorngEquityMargin(1200);
            TestDBUtility.insertWithDel(l_branchParams);
            
            //InstitutionParams
            TestDBUtility.deleteAll(InstitutionRow.TYPE);
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            TestDBUtility.insertWithDel(l_institutionParams);
            
            TestDBUtility.deleteAll(MarketRow.TYPE);
            MarketParams l_marketRow = TestDBUtility.getMarketRow();
            l_marketRow.setMarketCode("1");
            TestDBUtility.insertWithDel(l_marketRow);
            
            //ProductParams
            TestDBUtility.deleteAll(ProductRow.TYPE);
            ProductParams l_productParams = TestDBUtility.getProductRow();
            l_productParams.setProductId(1006169090018L);
            l_productParams.setProductType(ProductTypeEnum.EQUITY);
            TestDBUtility.insertWithDel(l_productParams);
            
            TestDBUtility.deleteAll(EqtypeProductRow.TYPE);
            EqtypeProductParams l_EqtypeProductParams = TestDBUtility.getEqtypeProductRow();
            l_EqtypeProductParams.setProductId(1006169090018L);
            l_EqtypeProductParams.setProductCode("0");
            TestDBUtility.insertWithDel(l_EqtypeProductParams);
            
            TestDBUtility.deleteAll(EqtypeTradedProductParams.TYPE);
            EqtypeTradedProductParams l_eqtypeTradedProductParams = TestDBUtility.getEqtypeTradedProductRow();
            l_eqtypeTradedProductParams.setLastClosingPrice(10);
            l_eqtypeTradedProductParams.setProductId(1006169090018L);
            l_eqtypeTradedProductParams.setMarketId(3303);
            l_eqtypeTradedProductParams.setPriceRangeType("1");
            TestDBUtility.insertWithDel(l_eqtypeTradedProductParams);
            
            TestDBUtility.deleteAll(TradedProductParams.TYPE);
            TradedProductParams l_tradedProductParams = TestDBUtility.getTradedProductRow();
            l_tradedProductParams.setProductId(1006169090018L);
            l_tradedProductParams.setMarketId(3303);
            l_tradedProductParams.setProductType(ProductTypeEnum.EQUITY);
            TestDBUtility.insertWithDel(l_tradedProductParams);
            
            TestDBUtility.deleteAll(MarketPreferencesRow.TYPE);
            MarketPreferencesParams l_MarketPreferencesRow = TestDBUtility.getMarketPreferencesRow();
            l_MarketPreferencesRow.setName("equity.pts.market.div");
            l_MarketPreferencesRow.setValue("1");
            TestDBUtility.insertWithDel(l_MarketPreferencesRow);

            TradingTimeParams l_tradingTimeParams1 = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams1.setInstitutionCode("0D");
            l_tradingTimeParams1.setBranchCode("123");
            l_tradingTimeParams1.setBizDateType("1");
            l_tradingTimeParams1.setMarketCode("0");
            l_tradingTimeParams1.setProductCode("0");
            l_tradingTimeParams1.setTradingTimeType("05");
            TestDBUtility.insertWithDel(l_tradingTimeParams1);
            
            TradingTimeParams l_tradingTimeParams2 = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams2.setInstitutionCode("0D");
            l_tradingTimeParams2.setBranchCode("123");
            l_tradingTimeParams2.setBizDateType("4");
            l_tradingTimeParams2.setMarketCode("1");
            l_tradingTimeParams2.setProductCode("0");
            l_tradingTimeParams2.setTradingTimeType("01");
            TestDBUtility.insertWithDel(l_tradingTimeParams2);
            
            TestDBUtility.deleteAll(EquityLimitPriceRangeMstRow.TYPE);
            EquityLimitPriceRangeMstParams l_params = TestDBUtility.getEquityLimitPriceRangeMstRow();
            l_params.setLowPrice(0);
            l_params.setCapPrice(100);
            TestDBUtility.insertWithDel(l_params);
            
            TestDBUtility.deleteAll(EquityTickValuesMstRow.TYPE);
            EquityTickValuesMstParams l_equityTickValuesMstParams = TestDBUtility.getEquityTickValuesMstRow();
            l_equityTickValuesMstParams.setCapPrice(200);
            l_equityTickValuesMstParams.setLowPrice(0);
            TestDBUtility.insertWithDel(l_equityTickValuesMstParams);
 
            WEB3EquityProductInformationRequest l_request =
                new WEB3EquityProductInformationRequest();
            
            l_request.marketCode = "1";
            l_request.productCode = "0";

            WEB3EquityProductInformationServiceImpl l_impl = new WEB3EquityProductInformationServiceImpl();
            WEB3EquityProductInformationResponse l_response =
                (WEB3EquityProductInformationResponse)l_impl.execute(l_request);
            
            assertEquals(l_response.upperPriceRange, "200");
            assertEquals(l_response.lowerPriceRange, "1");

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
     * execute
     * リクエスト.市場コード ≠ null の場合
     * PTS市場の場合（isPTS市場() == true）
     * 値幅チェック対象銘柄の以外場合
     */
    public void testExecuteCase0003()
    {
        final String STR_METHOD_NAME = "testExecuteCase0003()";
        log.entering(STR_METHOD_NAME);
        MOCK_MANAGER.setIsMockUsed(true);
        try
        {
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                    "getAccountId",
                    new Class[] {},
                    new Long(101001010010L));
            
            TestDBUtility.deleteAll(TradingTimeRow.TYPE);
            WEB3GentradeTradingTimeManagementForMock.mockValidateOrderAccept(true);
            
            Date l_datBizDate = WEB3DateUtility.getDate("20071227", "yyyyMMdd");
            WEB3GentradeTradingTimeManagementForMock.mockGetOrderBizDate(l_datBizDate);
            //MainAccountParams
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(101001010010L);
            TestDBUtility.insertWithDel(l_mainAccountParams);
            
            //SubAccountParams
            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setAccountId(101001010010L);
            l_subAccountParams.setSubAccountId(10100101001007L);
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
            TestDBUtility.insertWithDel(l_subAccountParams);

            //BranchParams
            TestDBUtility.deleteAll(BranchRow.TYPE);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setCloseWorngEquityMargin(1200);
            TestDBUtility.insertWithDel(l_branchParams);
            
            //InstitutionParams
            TestDBUtility.deleteAll(InstitutionRow.TYPE);
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            TestDBUtility.insertWithDel(l_institutionParams);
            
            TestDBUtility.deleteAll(MarketRow.TYPE);
            MarketParams l_marketRow = TestDBUtility.getMarketRow();
            l_marketRow.setMarketCode("1");
            TestDBUtility.insertWithDel(l_marketRow);
            
            //ProductParams
            TestDBUtility.deleteAll(ProductRow.TYPE);
            ProductParams l_productParams = TestDBUtility.getProductRow();
            l_productParams.setProductId(1006169090018L);
            l_productParams.setProductType(ProductTypeEnum.EQUITY);
            TestDBUtility.insertWithDel(l_productParams);
            
            TestDBUtility.deleteAll(EqtypeProductRow.TYPE);
            EqtypeProductParams l_EqtypeProductParams = TestDBUtility.getEqtypeProductRow();
            l_EqtypeProductParams.setProductId(1006169090018L);
            l_EqtypeProductParams.setProductCode("0");
            TestDBUtility.insertWithDel(l_EqtypeProductParams);
            
            TestDBUtility.deleteAll(EqtypeTradedProductParams.TYPE);
            EqtypeTradedProductParams l_eqtypeTradedProductParams = TestDBUtility.getEqtypeTradedProductRow();
            l_eqtypeTradedProductParams.setProductId(1006169090018L);
            l_eqtypeTradedProductParams.setMarketId(3303);
            l_eqtypeTradedProductParams.setPriceRangeType("0");
            TestDBUtility.insertWithDel(l_eqtypeTradedProductParams);
            
            TestDBUtility.deleteAll(TradedProductParams.TYPE);
            TradedProductParams l_tradedProductParams = TestDBUtility.getTradedProductRow();
            l_tradedProductParams.setProductId(1006169090018L);
            l_tradedProductParams.setMarketId(3303);
            l_tradedProductParams.setProductType(ProductTypeEnum.EQUITY);
            TestDBUtility.insertWithDel(l_tradedProductParams);
            
            TestDBUtility.deleteAll(MarketPreferencesRow.TYPE);
            MarketPreferencesParams l_MarketPreferencesRow = TestDBUtility.getMarketPreferencesRow();
            l_MarketPreferencesRow.setName("equity.pts.market.div");
            l_MarketPreferencesRow.setValue("1");
            TestDBUtility.insertWithDel(l_MarketPreferencesRow);

            TradingTimeParams l_tradingTimeParams1 = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams1.setInstitutionCode("0D");
            l_tradingTimeParams1.setBranchCode("123");
            l_tradingTimeParams1.setBizDateType("1");
            l_tradingTimeParams1.setMarketCode("0");
            l_tradingTimeParams1.setProductCode("0");
            l_tradingTimeParams1.setTradingTimeType("05");
            TestDBUtility.insertWithDel(l_tradingTimeParams1);
            
            TradingTimeParams l_tradingTimeParams2 = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams2.setInstitutionCode("0D");
            l_tradingTimeParams2.setBranchCode("123");
            l_tradingTimeParams2.setBizDateType("4");
            l_tradingTimeParams2.setMarketCode("1");
            l_tradingTimeParams2.setProductCode("0");
            l_tradingTimeParams2.setTradingTimeType("01");
            TestDBUtility.insertWithDel(l_tradingTimeParams2);
            
            TestDBUtility.deleteAll(EquityLimitPriceRangeMstRow.TYPE);
            EquityLimitPriceRangeMstParams l_params = TestDBUtility.getEquityLimitPriceRangeMstRow();
            l_params.setLowPrice(0);
            l_params.setCapPrice(100);
            TestDBUtility.insertWithDel(l_params);
            
            TestDBUtility.deleteAll(EquityTickValuesMstRow.TYPE);
            EquityTickValuesMstParams l_equityTickValuesMstParams = TestDBUtility.getEquityTickValuesMstRow();
            l_equityTickValuesMstParams.setCapPrice(200);
            l_equityTickValuesMstParams.setLowPrice(0);
            TestDBUtility.insertWithDel(l_equityTickValuesMstParams);
 
            WEB3EquityProductInformationRequest l_request =
                new WEB3EquityProductInformationRequest();
            
            l_request.marketCode = "1";
            l_request.productCode = "0";

            WEB3EquityProductInformationServiceImpl l_impl = new WEB3EquityProductInformationServiceImpl();
            WEB3EquityProductInformationResponse l_response =
                (WEB3EquityProductInformationResponse)l_impl.execute(l_request);
            
            assertNull(l_response.upperPriceRange);
            assertNull(l_response.lowerPriceRange);

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
     * execute
     * リクエスト.市場コード == null の場合
     * 値幅チェック対象銘柄の場合
     */
    public void testExecuteCase0004()
    {
        final String STR_METHOD_NAME = "testExecuteCase0004()";
        log.entering(STR_METHOD_NAME);
        MOCK_MANAGER.setIsMockUsed(true);
        try
        {
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                    "getAccountId",
                    new Class[] {},
                    new Long(101001010010L));
            
            TestDBUtility.deleteAll(TradingTimeRow.TYPE);
            WEB3GentradeTradingTimeManagementForMock.mockValidateOrderAccept(true);
            
            Date l_datBizDate = WEB3DateUtility.getDate("20071227", "yyyyMMdd");
            WEB3GentradeTradingTimeManagementForMock.mockGetOrderBizDate(l_datBizDate);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue("webbroker3.gentrade.WEB3GentradeTradingCalendarModelImpl",
                    "getCurrentBizDate",
                    new Class[] {long.class},
                    l_datBizDate);
            
            //MainAccountParams
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(101001010010L);
            TestDBUtility.insertWithDel(l_mainAccountParams);
            
            //SubAccountParams
            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setAccountId(101001010010L);
            l_subAccountParams.setSubAccountId(10100101001007L);
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
            TestDBUtility.insertWithDel(l_subAccountParams);

            //BranchParams
            TestDBUtility.deleteAll(BranchRow.TYPE);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setCloseWorngEquityMargin(1200);
            TestDBUtility.insertWithDel(l_branchParams);
            
            //InstitutionParams
            TestDBUtility.deleteAll(InstitutionRow.TYPE);
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            TestDBUtility.insertWithDel(l_institutionParams);
            
            TestDBUtility.deleteAll(MarketRow.TYPE);
            MarketParams l_marketRow = TestDBUtility.getMarketRow();
            l_marketRow.setMarketCode("1");
            TestDBUtility.insertWithDel(l_marketRow);
            
            //ProductParams
            TestDBUtility.deleteAll(ProductRow.TYPE);
            ProductParams l_productParams = TestDBUtility.getProductRow();
            l_productParams.setProductId(1006169090018L);
            l_productParams.setProductType(ProductTypeEnum.EQUITY);
            l_productParams.setPrimaryMarketId(3303L);
            TestDBUtility.insertWithDel(l_productParams);
            
            TestDBUtility.deleteAll(EqtypeProductRow.TYPE);
            EqtypeProductParams l_EqtypeProductParams = TestDBUtility.getEqtypeProductRow();
            l_EqtypeProductParams.setProductId(1006169090018L);
            l_EqtypeProductParams.setProductCode("0");
            TestDBUtility.insertWithDel(l_EqtypeProductParams);
            
            TestDBUtility.deleteAll(EqtypeTradedProductParams.TYPE);
            EqtypeTradedProductParams l_eqtypeTradedProductParams = TestDBUtility.getEqtypeTradedProductRow();
            l_eqtypeTradedProductParams.setProductId(1006169090018L);
            l_eqtypeTradedProductParams.setMarketId(3303);
            l_eqtypeTradedProductParams.setPriceRangeType("1");           
            l_eqtypeTradedProductParams.setValidUntilBizDate(
                WEB3DateUtility.formatDate(l_datBizDate,"yyyyMMdd"));
            TestDBUtility.insertWithDel(l_eqtypeTradedProductParams);
            
            EqtypeTradedProductUpdqParams l_eqtypeTradedProductUpdqParams =  TestDBUtility.getEqtypeTradedProductUpdqRow();
            l_eqtypeTradedProductUpdqParams.setProductId(1006169090018L);
            l_eqtypeTradedProductUpdqParams.setMarketId(3303);
            l_eqtypeTradedProductUpdqParams.setPriceRangeType("1");
            l_eqtypeTradedProductUpdqParams.setValidUntilBizDate(WEB3DateUtility.formatDate(l_datBizDate,"yyyyMMdd"));
            TestDBUtility.insertWithDel(l_eqtypeTradedProductUpdqParams);
            
            TestDBUtility.deleteAll(TradedProductParams.TYPE);
            TradedProductParams l_tradedProductParams = TestDBUtility.getTradedProductRow();
            l_tradedProductParams.setProductId(1006169090018L);
            l_tradedProductParams.setMarketId(3303);
            l_tradedProductParams.setProductType(ProductTypeEnum.EQUITY);
            TestDBUtility.insertWithDel(l_tradedProductParams);
            
            TestDBUtility.deleteAll(MarketPreferencesRow.TYPE);

            TradingTimeParams l_tradingTimeParams1 = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams1.setInstitutionCode("0D");
            l_tradingTimeParams1.setBranchCode("123");
            l_tradingTimeParams1.setBizDateType("1");
            l_tradingTimeParams1.setMarketCode("0");
            l_tradingTimeParams1.setProductCode("0");
            l_tradingTimeParams1.setTradingTimeType("05");
            TestDBUtility.insertWithDel(l_tradingTimeParams1);
            
            TradingTimeParams l_tradingTimeParams2 = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams2.setInstitutionCode("0D");
            l_tradingTimeParams2.setBranchCode("123");
            l_tradingTimeParams2.setBizDateType("1");
            l_tradingTimeParams2.setMarketCode("1");
            l_tradingTimeParams2.setProductCode("0");
            l_tradingTimeParams2.setTradingTimeType("01");
            TestDBUtility.insertWithDel(l_tradingTimeParams2);
            
            TestDBUtility.deleteAll(EquityLimitPriceRangeMstRow.TYPE);
            EquityLimitPriceRangeMstParams l_params = TestDBUtility.getEquityLimitPriceRangeMstRow();
            l_params.setLowPrice(0);
            l_params.setCapPrice(100);
            TestDBUtility.insertWithDel(l_params);
            
            TestDBUtility.deleteAll(EquityTickValuesMstRow.TYPE);
            EquityTickValuesMstParams l_equityTickValuesMstParams = TestDBUtility.getEquityTickValuesMstRow();
            l_equityTickValuesMstParams.setCapPrice(200);
            l_equityTickValuesMstParams.setLowPrice(0);
            TestDBUtility.insertWithDel(l_equityTickValuesMstParams);
            TestDBUtility.commit();
            WEB3EquityProductInformationRequest l_request =
                new WEB3EquityProductInformationRequest();
            
            l_request.marketCode = null;
            l_request.productCode = "0";

            WEB3EquityProductInformationServiceImpl l_impl = new WEB3EquityProductInformationServiceImpl();
            WEB3EquityProductInformationResponse l_response =
                (WEB3EquityProductInformationResponse)l_impl.execute(l_request);
            
            assertEquals(l_response.upperPriceRange, "200");
            assertEquals(l_response.lowerPriceRange, "1");

        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    //取引銘柄(*1).値幅チェック区分=1
    //is権利落ち日( )　@==　@trueかつ翌日注文時間帯（*6）かつプロセス管理テーブルに基準値受信データが存在しない場合
    public void testExecuteCase0005()
    {
        final String STR_METHOD_NAME = "testExecuteCase0005()";
        log.entering(STR_METHOD_NAME);
        MOCK_MANAGER.setIsMockUsed(true);
        try
        {
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                    "getAccountId",
                    new Class[] {},
                    new Long(101001010010L));
            

            
            TestDBUtility.deleteAll(TradingTimeRow.TYPE);
            WEB3GentradeTradingTimeManagementForMock.mockValidateOrderAccept(true);
            
            Date l_datBizDate = WEB3DateUtility.getDate("20071227", "yyyyMMdd");
            WEB3GentradeTradingTimeManagementForMock.mockGetOrderBizDate(l_datBizDate);
            //MainAccountParams
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(101001010010L);
            TestDBUtility.insertWithDel(l_mainAccountParams);
            
            //SubAccountParams
            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setAccountId(101001010010L);
            l_subAccountParams.setSubAccountId(10100101001007L);
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
            TestDBUtility.insertWithDel(l_subAccountParams);

            //BranchParams
            TestDBUtility.deleteAll(BranchRow.TYPE);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setCloseWorngEquityMargin(1200);
            TestDBUtility.insertWithDel(l_branchParams);
            
            //InstitutionParams
            TestDBUtility.deleteAll(InstitutionRow.TYPE);
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            TestDBUtility.insertWithDel(l_institutionParams);
            
            TestDBUtility.deleteAll(MarketRow.TYPE);
            MarketParams l_marketRow = TestDBUtility.getMarketRow();
            l_marketRow.setMarketCode("1");
            TestDBUtility.insertWithDel(l_marketRow);
            TestDBUtility.deleteAll(MarketPreferencesParams.TYPE);
            MarketPreferencesParams l_marketPreferencesRow = TestDBUtility.getMarketPreferencesRow();
            l_marketPreferencesRow.setMarketId(3303);
            l_marketPreferencesRow.setName("equity.pts.market.div");
            l_marketPreferencesRow.setNameSerialNo(1);
            TestDBUtility.insertWithDel(l_marketPreferencesRow);
            
            //ProductParams
            TestDBUtility.deleteAll(ProductRow.TYPE);
            ProductParams l_productParams = TestDBUtility.getProductRow();
            l_productParams.setProductId(1006169090018L);
            l_productParams.setProductType(ProductTypeEnum.EQUITY);
            l_productParams.setPrimaryMarketId(3303L);
            TestDBUtility.insertWithDel(l_productParams);
            
            TestDBUtility.deleteAll(EqtypeProductRow.TYPE);
            EqtypeProductParams l_EqtypeProductParams = TestDBUtility.getEqtypeProductRow();
            l_EqtypeProductParams.setProductId(1006169090018L);
            l_EqtypeProductParams.setProductCode("0");
            TestDBUtility.insertWithDel(l_EqtypeProductParams);
            
            TestDBUtility.deleteAll(EqtypeTradedProductParams.TYPE);
            EqtypeTradedProductParams l_eqtypeTradedProductParams = TestDBUtility.getEqtypeTradedProductRow();
            l_eqtypeTradedProductParams.setProductId(1006169090018L);
            l_eqtypeTradedProductParams.setMarketId(3303);
            l_eqtypeTradedProductParams.setPriceRangeType("1");
            Date l_datSystemTimestamp = GtlUtils.getTradingSystem().getSystemTimestamp();
//            Date l_datpreBizDate = new WEB3GentradeBizDate(
//                new Timestamp(l_datSystemTimestamp.getTime())).roll(1);
            l_eqtypeTradedProductParams.setValidUntilBizDate("20091012");
            TestDBUtility.insertWithDel(l_eqtypeTradedProductParams);
            
            TestDBUtility.deleteAll(EqtypeTradedProductUpdqParams.TYPE);
            EqtypeTradedProductUpdqParams l_eqtypeTradedProductUpdqParams = TestDBUtility.getEqtypeTradedProductUpdqRow();
            l_eqtypeTradedProductUpdqParams.setProductId(1006169090018L);
            l_eqtypeTradedProductUpdqParams.setMarketId(3303);
            l_eqtypeTradedProductUpdqParams.setPriceRangeType("1");
            l_eqtypeTradedProductUpdqParams.setValidUntilBizDate("20071228");
            TestDBUtility.insertWithDel(l_eqtypeTradedProductUpdqParams);
            
            TestDBUtility.deleteAll(TradedProductParams.TYPE);
            TradedProductParams l_tradedProductParams = TestDBUtility.getTradedProductRow();
            l_tradedProductParams.setProductId(1006169090018L);
            l_tradedProductParams.setMarketId(3303);
            l_tradedProductParams.setProductType(ProductTypeEnum.EQUITY);
            l_tradedProductParams.setValidUntilBizDate("20091012");
            TestDBUtility.insertWithDel(l_tradedProductParams);
            
            TestDBUtility.deleteAll(TradedProductUpdqParams.TYPE);
            TradedProductUpdqParams l_tradedProductUpdqParams = TestDBUtility.getTradedProductUpdqRow();
            l_tradedProductUpdqParams.setProductId(1006169090018L);
            l_tradedProductUpdqParams.setMarketId(3303);
            l_tradedProductUpdqParams.setValidUntilBizDate("20071228");
            TestDBUtility.insertWithDel(l_tradedProductUpdqParams);
            
            TestDBUtility.deleteAll(MarketPreferencesRow.TYPE);
            
            TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams.setInstitutionCode("0D");
            l_tradingTimeParams.setBranchCode("123");
            l_tradingTimeParams.setBizDateType("1");
            l_tradingTimeParams.setMarketCode("1");
            l_tradingTimeParams.setProductCode("0");
            l_tradingTimeParams.setTradingTimeType("01");
            TestDBUtility.insertWithDel(l_tradingTimeParams);
            
            TradingTimeParams l_tradingTimeParams1 = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams1.setInstitutionCode("0D");
            l_tradingTimeParams1.setBranchCode("123");
            l_tradingTimeParams1.setBizDateType("1");
            l_tradingTimeParams1.setMarketCode("0");
            l_tradingTimeParams1.setProductCode("0");
            l_tradingTimeParams1.setTradingTimeType("05");
            TestDBUtility.insertWithDel(l_tradingTimeParams1);
            TradingTimeParams l_tradingTimeParams2 = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams2.setInstitutionCode("0D");
            l_tradingTimeParams2.setBranchCode("123");
            l_tradingTimeParams2.setBizDateType("1");
            l_tradingTimeParams2.setMarketCode("1");
            l_tradingTimeParams2.setProductCode("0");
            l_tradingTimeParams2.setTradingTimeType("19");
            TestDBUtility.insertWithDel(l_tradingTimeParams2);
            
            TestDBUtility.deleteAll(EquityLimitPriceRangeMstRow.TYPE);
            EquityLimitPriceRangeMstParams l_params = TestDBUtility.getEquityLimitPriceRangeMstRow();
            l_params.setLowPrice(0);
            l_params.setCapPrice(100);
            TestDBUtility.insertWithDel(l_params);
            
            TestDBUtility.deleteAll(EquityTickValuesMstRow.TYPE);
            EquityTickValuesMstParams l_equityTickValuesMstParams = TestDBUtility.getEquityTickValuesMstRow();
            l_equityTickValuesMstParams.setCapPrice(200);
            l_equityTickValuesMstParams.setLowPrice(0);
            TestDBUtility.insertWithDel(l_equityTickValuesMstParams);
            TestDBUtility.commit();
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.equity.WEB3EquityOrderManager",
                    "validateTradedProduct",
                    new Class[] {EqTypeProduct.class, Market.class},
                        null);
//            WEB3GentradeTradingTimeManagementForMock.mockValidateOrderAccept(true);
//            ThreadLocalSystemAttributesRegistry.setAttribute(
//                    WEB3GentradeTradingTimeManagement.TIMESTAMP_TAG,  new String("20071228"));
            
            WEB3EquityProductInformationRequest l_request =
                new WEB3EquityProductInformationRequest();
            
            l_request.marketCode = "1";
            l_request.productCode = "0";

            WEB3EquityProductInformationServiceImpl l_impl = new WEB3EquityProductInformationServiceImpl();
            WEB3EquityProductInformationResponse l_response =
                (WEB3EquityProductInformationResponse)l_impl.execute(l_request);
            
            assertEquals(l_response.upperPriceRange, "200");
            assertEquals(l_response.lowerPriceRange, "1");

        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    

    public void testExecuteCase0006()
    {
        final String STR_METHOD_NAME = "testExecuteCase0006()";
        log.entering(STR_METHOD_NAME);
        MOCK_MANAGER.setIsMockUsed(true);
        try
        {
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                    "getAccountId",
                    new Class[] {},
                    new Long(101001010010L));
            

            
            TestDBUtility.deleteAll(TradingTimeRow.TYPE);
            WEB3GentradeTradingTimeManagementForMock.mockValidateOrderAccept(true);
            
            Date l_datBizDate = WEB3DateUtility.getDate("20071227", "yyyyMMdd");
            WEB3GentradeTradingTimeManagementForMock.mockGetOrderBizDate(l_datBizDate);
            //MainAccountParams
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(101001010010L);
            TestDBUtility.insertWithDel(l_mainAccountParams);
            
            //SubAccountParams
            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setAccountId(101001010010L);
            l_subAccountParams.setSubAccountId(10100101001007L);
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
            TestDBUtility.insertWithDel(l_subAccountParams);

            //BranchParams
            TestDBUtility.deleteAll(BranchRow.TYPE);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setCloseWorngEquityMargin(1200);
            TestDBUtility.insertWithDel(l_branchParams);
            
            //InstitutionParams
            TestDBUtility.deleteAll(InstitutionRow.TYPE);
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            TestDBUtility.insertWithDel(l_institutionParams);
            
            TestDBUtility.deleteAll(MarketRow.TYPE);
            MarketParams l_marketRow = TestDBUtility.getMarketRow();
            l_marketRow.setMarketCode("1");
            TestDBUtility.insertWithDel(l_marketRow);
            TestDBUtility.deleteAll(MarketPreferencesParams.TYPE);
            MarketPreferencesParams l_marketPreferencesRow = TestDBUtility.getMarketPreferencesRow();
            l_marketPreferencesRow.setMarketId(3303);
            l_marketPreferencesRow.setName("equity.pts.market.div");
            l_marketPreferencesRow.setNameSerialNo(1);
            TestDBUtility.insertWithDel(l_marketPreferencesRow);
            
            //ProductParams
            TestDBUtility.deleteAll(ProductRow.TYPE);
            ProductParams l_productParams = TestDBUtility.getProductRow();
            l_productParams.setProductId(1006169090018L);
            l_productParams.setProductType(ProductTypeEnum.EQUITY);
            l_productParams.setPrimaryMarketId(3303L);
            TestDBUtility.insertWithDel(l_productParams);
            
            TestDBUtility.deleteAll(EqtypeProductRow.TYPE);
            EqtypeProductParams l_EqtypeProductParams = TestDBUtility.getEqtypeProductRow();
            l_EqtypeProductParams.setProductId(1006169090018L);
            l_EqtypeProductParams.setProductCode("0");
            TestDBUtility.insertWithDel(l_EqtypeProductParams);
            
            TestDBUtility.deleteAll(EqtypeTradedProductParams.TYPE);
            EqtypeTradedProductParams l_eqtypeTradedProductParams = TestDBUtility.getEqtypeTradedProductRow();
            l_eqtypeTradedProductParams.setProductId(1006169090018L);
            l_eqtypeTradedProductParams.setMarketId(3303);
            l_eqtypeTradedProductParams.setPriceRangeType("1");
            Date l_datSystemTimestamp = GtlUtils.getTradingSystem().getSystemTimestamp();
//            Date l_datpreBizDate = new WEB3GentradeBizDate(
//                new Timestamp(l_datSystemTimestamp.getTime())).roll(1);
            l_eqtypeTradedProductParams.setValidUntilBizDate("20091012");
            TestDBUtility.insertWithDel(l_eqtypeTradedProductParams);
            
            TestDBUtility.deleteAll(EqtypeTradedProductUpdqParams.TYPE);
            EqtypeTradedProductUpdqParams l_eqtypeTradedProductUpdqParams = TestDBUtility.getEqtypeTradedProductUpdqRow();
            l_eqtypeTradedProductUpdqParams.setProductId(1006169090018L);
            l_eqtypeTradedProductUpdqParams.setMarketId(3303);
            l_eqtypeTradedProductUpdqParams.setPriceRangeType("1");
            l_eqtypeTradedProductUpdqParams.setValidUntilBizDate("20071228");
            TestDBUtility.insertWithDel(l_eqtypeTradedProductUpdqParams);
            
            TestDBUtility.deleteAll(TradedProductParams.TYPE);
            TradedProductParams l_tradedProductParams = TestDBUtility.getTradedProductRow();
            l_tradedProductParams.setProductId(1006169090018L);
            l_tradedProductParams.setMarketId(3303);
            l_tradedProductParams.setProductType(ProductTypeEnum.EQUITY);
            l_tradedProductParams.setValidUntilBizDate("20091012");
            TestDBUtility.insertWithDel(l_tradedProductParams);
            
            TestDBUtility.deleteAll(TradedProductUpdqParams.TYPE);
            TradedProductUpdqParams l_tradedProductUpdqParams = TestDBUtility.getTradedProductUpdqRow();
            l_tradedProductUpdqParams.setProductId(1006169090018L);
            l_tradedProductUpdqParams.setMarketId(3303);
            l_tradedProductUpdqParams.setValidUntilBizDate("20071228");
            TestDBUtility.insertWithDel(l_tradedProductUpdqParams);
            
            TestDBUtility.deleteAll(MarketPreferencesRow.TYPE);
            
            TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams.setInstitutionCode("0D");
            l_tradingTimeParams.setBranchCode("123");
            l_tradingTimeParams.setBizDateType("1");
            l_tradingTimeParams.setMarketCode("1");
            l_tradingTimeParams.setProductCode("0");
            l_tradingTimeParams.setTradingTimeType("01");
            TestDBUtility.insertWithDel(l_tradingTimeParams);
            
            TradingTimeParams l_tradingTimeParams1 = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams1.setInstitutionCode("0D");
            l_tradingTimeParams1.setBranchCode("123");
            l_tradingTimeParams1.setBizDateType("1");
            l_tradingTimeParams1.setMarketCode("0");
            l_tradingTimeParams1.setProductCode("0");
            l_tradingTimeParams1.setTradingTimeType("05");
            TestDBUtility.insertWithDel(l_tradingTimeParams1);
            TradingTimeParams l_tradingTimeParams2 = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams2.setInstitutionCode("0D");
            l_tradingTimeParams2.setBranchCode("123");
            l_tradingTimeParams2.setBizDateType("1");
            l_tradingTimeParams2.setMarketCode("1");
            l_tradingTimeParams2.setProductCode("0");
            l_tradingTimeParams2.setTradingTimeType("19");
            TestDBUtility.insertWithDel(l_tradingTimeParams2);
            
            TestDBUtility.deleteAll(EquityLimitPriceRangeMstRow.TYPE);
            EquityLimitPriceRangeMstParams l_params = TestDBUtility.getEquityLimitPriceRangeMstRow();
            l_params.setLowPrice(0);
            l_params.setCapPrice(100);
            TestDBUtility.insertWithDel(l_params);
            
            TestDBUtility.deleteAll(EquityTickValuesMstRow.TYPE);
            EquityTickValuesMstParams l_equityTickValuesMstParams = TestDBUtility.getEquityTickValuesMstRow();
            l_equityTickValuesMstParams.setCapPrice(200);
            l_equityTickValuesMstParams.setLowPrice(0);
            TestDBUtility.insertWithDel(l_equityTickValuesMstParams);
            TestDBUtility.commit();
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.equity.WEB3EquityOrderManager",
                    "validateTradedProduct",
                    new Class[] {EqTypeProduct.class, Market.class},
                        null);
//            WEB3GentradeTradingTimeManagementForMock.mockValidateOrderAccept(true);
//            ThreadLocalSystemAttributesRegistry.setAttribute(
//                    WEB3GentradeTradingTimeManagement.TIMESTAMP_TAG,  new String("20071228"));
            
            WEB3EquityProductInformationRequest l_request =
                new WEB3EquityProductInformationRequest();
            
            l_request.marketCode = "1";
            l_request.productCode = "0";

            WEB3EquityProductInformationServiceImpl l_impl = new WEB3EquityProductInformationServiceImpl();
            WEB3EquityProductInformationResponse l_response =
                (WEB3EquityProductInformationResponse)l_impl.execute(l_request);
            
            assertEquals(l_response.upperPriceRange, "200");
            assertEquals(l_response.lowerPriceRange, "1");

        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    public void testExecuteCase0007()
    {
        final String STR_METHOD_NAME = "testExecuteCase0007()";
        log.entering(STR_METHOD_NAME);
        MOCK_MANAGER.setIsMockUsed(true);
        try
        {
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                    "getAccountId",
                    new Class[] {},
                    new Long(101001010010L));
            

            
            TestDBUtility.deleteAll(TradingTimeRow.TYPE);
            WEB3GentradeTradingTimeManagementForMock.mockValidateOrderAccept(true);
            
            Date l_datBizDate = WEB3DateUtility.getDate("20071227", "yyyyMMdd");
            WEB3GentradeTradingTimeManagementForMock.mockGetOrderBizDate(l_datBizDate);
            //MainAccountParams
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(101001010010L);
            TestDBUtility.insertWithDel(l_mainAccountParams);
            
            //SubAccountParams
            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setAccountId(101001010010L);
            l_subAccountParams.setSubAccountId(10100101001007L);
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
            TestDBUtility.insertWithDel(l_subAccountParams);

            //BranchParams
            TestDBUtility.deleteAll(BranchRow.TYPE);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setCloseWorngEquityMargin(1200);
            TestDBUtility.insertWithDel(l_branchParams);
            
            //InstitutionParams
            TestDBUtility.deleteAll(InstitutionRow.TYPE);
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            TestDBUtility.insertWithDel(l_institutionParams);
            
            TestDBUtility.deleteAll(MarketRow.TYPE);
            MarketParams l_marketRow = TestDBUtility.getMarketRow();
            l_marketRow.setMarketCode("1");
            TestDBUtility.insertWithDel(l_marketRow);
            TestDBUtility.deleteAll(MarketPreferencesParams.TYPE);
            MarketPreferencesParams l_marketPreferencesRow = TestDBUtility.getMarketPreferencesRow();
            l_marketPreferencesRow.setMarketId(3303);
            l_marketPreferencesRow.setName("equity.pts.market.div");
            l_marketPreferencesRow.setNameSerialNo(1);
            TestDBUtility.insertWithDel(l_marketPreferencesRow);
            
            //ProductParams
            TestDBUtility.deleteAll(ProductRow.TYPE);
            ProductParams l_productParams = TestDBUtility.getProductRow();
            l_productParams.setProductId(1006169090018L);
            l_productParams.setProductType(ProductTypeEnum.EQUITY);
            l_productParams.setPrimaryMarketId(3303L);
            TestDBUtility.insertWithDel(l_productParams);
            
            TestDBUtility.deleteAll(EqtypeProductRow.TYPE);
            EqtypeProductParams l_EqtypeProductParams = TestDBUtility.getEqtypeProductRow();
            l_EqtypeProductParams.setProductId(1006169090018L);
            l_EqtypeProductParams.setProductCode("0");
            TestDBUtility.insertWithDel(l_EqtypeProductParams);
            
            TestDBUtility.deleteAll(EqtypeTradedProductParams.TYPE);
            EqtypeTradedProductParams l_eqtypeTradedProductParams = TestDBUtility.getEqtypeTradedProductRow();
            l_eqtypeTradedProductParams.setProductId(1006169090018L);
            l_eqtypeTradedProductParams.setMarketId(3303);
            l_eqtypeTradedProductParams.setPriceRangeType("1");
            Date l_datSystemTimestamp = GtlUtils.getTradingSystem().getSystemTimestamp();
//            Date l_datpreBizDate = new WEB3GentradeBizDate(
//                new Timestamp(l_datSystemTimestamp.getTime())).roll(1);
            l_eqtypeTradedProductParams.setValidUntilBizDate("20091012");
            TestDBUtility.insertWithDel(l_eqtypeTradedProductParams);
            
            TestDBUtility.deleteAll(EqtypeTradedProductUpdqParams.TYPE);
            EqtypeTradedProductUpdqParams l_eqtypeTradedProductUpdqParams = TestDBUtility.getEqtypeTradedProductUpdqRow();
            l_eqtypeTradedProductUpdqParams.setProductId(1006169090018L);
            l_eqtypeTradedProductUpdqParams.setMarketId(3303);
            l_eqtypeTradedProductUpdqParams.setPriceRangeType("1");
            l_eqtypeTradedProductUpdqParams.setValidUntilBizDate("20071228");
            TestDBUtility.insertWithDel(l_eqtypeTradedProductUpdqParams);
            
            TestDBUtility.deleteAll(TradedProductParams.TYPE);
            TradedProductParams l_tradedProductParams = TestDBUtility.getTradedProductRow();
            l_tradedProductParams.setProductId(1006169090018L);
            l_tradedProductParams.setMarketId(3303);
            l_tradedProductParams.setProductType(ProductTypeEnum.EQUITY);
            l_tradedProductParams.setValidUntilBizDate("20091012");
            TestDBUtility.insertWithDel(l_tradedProductParams);
            
            TestDBUtility.deleteAll(TradedProductUpdqParams.TYPE);
            TradedProductUpdqParams l_tradedProductUpdqParams = TestDBUtility.getTradedProductUpdqRow();
            l_tradedProductUpdqParams.setProductId(1006169090018L);
            l_tradedProductUpdqParams.setMarketId(3303);
            l_tradedProductUpdqParams.setValidUntilBizDate("20071228");
            TestDBUtility.insertWithDel(l_tradedProductUpdqParams);
            
            TestDBUtility.deleteAll(MarketPreferencesRow.TYPE);
            
            TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams.setInstitutionCode("0D");
            l_tradingTimeParams.setBranchCode("123");
            l_tradingTimeParams.setBizDateType("1");
            l_tradingTimeParams.setMarketCode("1");
            l_tradingTimeParams.setProductCode("0");
            l_tradingTimeParams.setTradingTimeType("01");
            TestDBUtility.insertWithDel(l_tradingTimeParams);
            
            TradingTimeParams l_tradingTimeParams1 = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams1.setInstitutionCode("0D");
            l_tradingTimeParams1.setBranchCode("123");
            l_tradingTimeParams1.setBizDateType("1");
            l_tradingTimeParams1.setMarketCode("0");
            l_tradingTimeParams1.setProductCode("0");
            l_tradingTimeParams1.setTradingTimeType("05");
            TestDBUtility.insertWithDel(l_tradingTimeParams1);
            TradingTimeParams l_tradingTimeParams2 = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams2.setInstitutionCode("0D");
            l_tradingTimeParams2.setBranchCode("123");
            l_tradingTimeParams2.setBizDateType("1");
            l_tradingTimeParams2.setMarketCode("1");
            l_tradingTimeParams2.setProductCode("0");
            l_tradingTimeParams2.setTradingTimeType("19");
            TestDBUtility.insertWithDel(l_tradingTimeParams2);
            
            TestDBUtility.deleteAll(EquityLimitPriceRangeMstRow.TYPE);
            EquityLimitPriceRangeMstParams l_params = TestDBUtility.getEquityLimitPriceRangeMstRow();
            l_params.setLowPrice(0);
            l_params.setCapPrice(100);
            TestDBUtility.insertWithDel(l_params);
            
            TestDBUtility.deleteAll(EquityTickValuesMstRow.TYPE);
            EquityTickValuesMstParams l_equityTickValuesMstParams = TestDBUtility.getEquityTickValuesMstRow();
            l_equityTickValuesMstParams.setCapPrice(200);
            l_equityTickValuesMstParams.setLowPrice(0);
            TestDBUtility.insertWithDel(l_equityTickValuesMstParams);
            TestDBUtility.commit();
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.equity.WEB3EquityOrderManager",
                    "validateTradedProduct",
                    new Class[] {EqTypeProduct.class, Market.class},
                        null);
//            WEB3GentradeTradingTimeManagementForMock.mockValidateOrderAccept(true);
//            ThreadLocalSystemAttributesRegistry.setAttribute(
//                    WEB3GentradeTradingTimeManagement.TIMESTAMP_TAG,  new String("20071228"));
            
            WEB3EquityProductInformationRequest l_request =
                new WEB3EquityProductInformationRequest();
            
            l_request.marketCode = "1";
            l_request.productCode = "0";

            WEB3EquityProductInformationServiceImpl l_impl = new WEB3EquityProductInformationServiceImpl();
            WEB3EquityProductInformationResponse l_response =
                (WEB3EquityProductInformationResponse)l_impl.execute(l_request);
            
            assertEquals(l_response.upperPriceRange, "200");
            assertEquals(l_response.lowerPriceRange, "1");

        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
}
@
