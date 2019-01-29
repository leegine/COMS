head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.02.11.05;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3MutualBuyListServiceImplTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 投資信託買付一覧照会サービス実装テスト(WEB3MutualBuyListServiceImplTest.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/02/13 趙林鵬 (中訊) 新規作成
*/

package webbroker3.mf.service.delegate.stdimpls;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;

import com.fitechlabs.xtrade.kernel.error.ErrorManager;
import com.fitechlabs.xtrade.kernel.message.ErrorInfo;
import com.fitechlabs.xtrade.kernel.util.ThreadLocalSystemAttributesRegistry;
import com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProcessingResult;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccountTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TaxTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingSystem;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.BranchParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.BranchRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.InstitutionParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.InstitutionRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MarketCalendarParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MarketCalendarRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MarketParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MarketRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.ProductParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.ProductRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.SubAccountParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.SubAccountRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.TradedProductCalendarRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.TradedProductParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.TradedProductRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.OrderValidationResult;
import com.fitechlabs.xtrade.plugin.tc.gentrade.stdimpls.CalendarUtils;
import com.fitechlabs.xtrade.plugin.tc.xbmf.MutualFundTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.xbmf.data.MutualFundProductParams;
import com.fitechlabs.xtrade.plugin.tc.xbmf.data.MutualFundProductRow;
import com.fitechlabs.xtrade.plugin.tc.xbmf.data.MutualFundTradedProductParams;
import com.fitechlabs.xtrade.plugin.tc.xbmf.data.MutualFundTradedProductRow;

import test.util.TestDBUtility;

import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.gentrade.WEB3GentradeBizDate;
import webbroker3.gentrade.WEB3GentradeMainAccount;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagementForMock;
import webbroker3.gentrade.data.ExchangeRateParams;
import webbroker3.gentrade.data.ExchangeRateRow;
import webbroker3.gentrade.data.FrgnMmfExchangeRateParams;
import webbroker3.gentrade.data.FrgnMmfExchangeRateRow;
import webbroker3.gentrade.data.TradingTimeParams;
import webbroker3.gentrade.data.TradingTimeRow;
import webbroker3.mf.WEB3MutualFundProduct;
import webbroker3.mf.data.MfSubAssetRow;
import webbroker3.mf.message.WEB3MutualBuyListRequest;
import webbroker3.mf.message.WEB3MutualBuyListResponse;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

/**
 * （投資信託買付一覧照会サービス実装テスト）<BR>
 *
 * @@author 趙林鵬
 * @@version 1.0
 */

public class WEB3MutualBuyListServiceImplTest extends TestBaseForMock
{
    
    /**
     * ログユーティリティ<BR>
     */
    private static WEB3LogUtility log =
         WEB3LogUtility.getInstance(WEB3MutualBuyListServiceImplTest.class);

    public WEB3MutualBuyListServiceImplTest(String arg0)
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

    public void test_execute_C0001()
    {
        final String STR_METHOD_NAME = " test_execute_C0001";
        log.entering(TEST_START + STR_METHOD_NAME);

        WEB3MutualBuyListRequest l_request = null;
        this.deleteAll();
        
        WEB3MutualBuyListResponse l_response = null;
        
        try
        {
            MOCK_MANAGER.setIsMockUsed(true);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getAccountId",
                new Class[] {},new Long(101001010010L));
            
            Date l_datExpect = WEB3DateUtility.getDate("20040710","yyyyMMdd");
            WEB3GentradeTradingTimeManagementForMock.mockGetOrderBizDate(l_datExpect);
            
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            ProductParams l_productParams = TestDBUtility.getProductRow();
            MarketParams l_marketParams = TestDBUtility.getMarketRow();
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            TradedProductParams l_tradedProductParams = TestDBUtility.getTradedProductRow();
            MutualFundTradedProductParams l_mfTradedProductParams =
                TestDBUtility.getMutualFundTradedProductRow();
            TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
            
            Calendar ca =  Calendar.getInstance();
            
            ca.set(2004,7-1,6,9,11,23);
            
            Date date = ca.getTime();
            ThreadLocalSystemAttributesRegistry.setAttribute(
                    TradingSystem.SYSTEM_TIMESTAMP_ATTRIBUTE_NAME,
                    new Timestamp(WEB3DateUtility.getDate("20040706093030","yyyyMMddHHmmss").getTime()));
            
            
            MutualFundProductParams l_mfProductParams = TestDBUtility.getMutualFundProductRow();
            l_mfProductParams.setSystemHandlingDiv("1");
            l_mfProductParams.setBuyLimitDiv("0");
            l_mfProductParams.setBuyStartDate(date);
            l_mfProductParams.setBuyEndDate(WEB3DateUtility.getDate("20040707","yyyyMMdd"));
            l_mfProductParams.setRecruitStartDateSonar(date);
            l_mfProductParams.setRecruitEndDateSonar(WEB3DateUtility.getDate("20040707","yyyyMMdd"));
            l_mfProductParams.setSystemHandlingDiv("2");
            l_mfProductParams.setFundType(MutualFundTypeEnum.FOREIGN_MMF);
            
            MarketCalendarParams l_marketCalendarParams = new MarketCalendarParams();
            l_marketCalendarParams.setMarketId(3303L);
            l_marketCalendarParams.setTradeDate(CalendarUtils.clearTimeFields(GtlUtils.getSystemTimestamp()));
            l_marketCalendarParams.setHolidayFlag(BooleanEnum.TRUE);
            l_marketCalendarParams.setTradeOpenTime("090000");
            l_marketCalendarParams.setTradeCloseTime("150000");
            l_marketCalendarParams.setCreatedTimestamp(WEB3DateUtility.getDate("20040707","yyyyMMdd"));
            l_marketCalendarParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20040707","yyyyMMdd"));
            
            l_mainAccountParams.setAccountId(101001010010L);

            l_subAccountParams.setAccountId(101001010010L);
            l_subAccountParams.setSubAccountId(10100101001007L);
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
            
            l_productParams.setProductId(1006169090018L);
            l_productParams.setProductType(ProductTypeEnum.MUTUAL_FUND);

            l_marketParams.setMarketCode("0");

            l_tradedProductParams.setTradedProductId(100106139070605L);
            l_tradedProductParams.setProductId(1006169090018L);
            l_tradedProductParams.setProductType(ProductTypeEnum.MUTUAL_FUND);

            l_tradingTimeParams.setInstitutionCode("0D");
            l_tradingTimeParams.setBranchCode("123");
            l_tradingTimeParams.setTradingTimeType("01");
            l_tradingTimeParams.setBizDateType("1");
            l_tradingTimeParams.setMarketCode("N1");
            l_tradingTimeParams.setProductCode("0001000");

            l_mfTradedProductParams.setTradedProductId(100106139070605L);
            l_mfTradedProductParams.setProductId(1006169090018L);
            l_mfTradedProductParams.setMarketId(3303);
            l_mfTradedProductParams.setInstitutionCode("0D");
            
            l_mfProductParams.setProductId(1006169090018L);
            l_mfProductParams.setInstitutionCode("0D");
            l_mfProductParams.setProductCode("0001000");
            l_mfProductParams.setProductIssueCode("0");
            l_mfProductParams.setCurrencyCode("T0");
            l_mfProductParams.setFundType(MutualFundTypeEnum.FOREIGN_MMF);
            l_mfProductParams.setSystemHandlingDiv("1");
            l_mfProductParams.setBuyLimitDiv("0");

            TestDBUtility.insertWithDel(l_subAccountParams);
            TestDBUtility.insertWithDel(l_mainAccountParams);
            TestDBUtility.insertWithDel(l_productParams);
            TestDBUtility.insertWithDel(l_marketParams);
            TestDBUtility.insertWithDel(l_branchParams);
            TestDBUtility.insertWithDel(l_institutionParams);
            TestDBUtility.insertWithDel(l_tradedProductParams);
            TestDBUtility.insertWithDel(l_mfTradedProductParams);
            TestDBUtility.insertWithDel(l_tradingTimeParams);
            TestDBUtility.insertWithDel(l_mfProductParams);
            TestDBUtility.insertWithDel(l_marketCalendarParams);

            WEB3GentradeTradingTimeManagementForMock.mockSetTimestamp();
            
            l_request = new WEB3MutualBuyListRequest();
            
            l_request.pageIndex = "1";
            l_request.pageSize = "3";
            l_request.referenceType = "1";
            l_request.mutualFrgnMmfDisplayDiv = "1";
            

            WEB3MutualBuyListServiceImpl l_impl = new WEB3MutualBuyListServiceImpl();
            l_response = (WEB3MutualBuyListResponse)l_impl.execute(l_request);
            
            assertNull(l_response.buyProductGroups[0].yenConstantValue);
            assertNull(l_response.buyProductGroups[0].referenceRate);
            assertNull(l_response.buyProductGroups[0].referenceRateFixedDay);
            assertTrue(l_response.buyProductGroups[0].frgnMmfFlag);   
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
        log.exiting(TEST_END + STR_METHOD_NAME);      
    }
    
    public void test_execute_C0002()
    {
        final String STR_METHOD_NAME = " test_execute_C0002";
        log.entering(TEST_START + STR_METHOD_NAME);

        WEB3MutualBuyListRequest l_request = null;
        this.deleteAll();
        
        WEB3MutualBuyListResponse l_response = null;
        
        try
        {
            MOCK_MANAGER.setIsMockUsed(true);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getAccountId",
                new Class[] {},new Long(101001010010L));
            
            Date l_datExpect = WEB3DateUtility.getDate("20040710","yyyyMMdd");
            WEB3GentradeTradingTimeManagementForMock.mockGetOrderBizDate(l_datExpect);
            
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            ProductParams l_productParams = TestDBUtility.getProductRow();
            MarketParams l_marketParams = TestDBUtility.getMarketRow();
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            TradedProductParams l_tradedProductParams = TestDBUtility.getTradedProductRow();
            MutualFundTradedProductParams l_mfTradedProductParams =
                TestDBUtility.getMutualFundTradedProductRow();
            TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
            Calendar ca =  Calendar.getInstance();
            
            ca.set(2004,7-1,6,9,11,23);
            
            Date date = ca.getTime();
            ThreadLocalSystemAttributesRegistry.setAttribute(
                    TradingSystem.SYSTEM_TIMESTAMP_ATTRIBUTE_NAME,
                    new Timestamp(WEB3DateUtility.getDate("20040706093030","yyyyMMddHHmmss").getTime()));
            
            
            MutualFundProductParams l_mfProductParams = TestDBUtility.getMutualFundProductRow();
            l_mfProductParams.setSystemHandlingDiv("1");
            l_mfProductParams.setBuyLimitDiv("0");
            l_mfProductParams.setBuyStartDate(date);
            l_mfProductParams.setBuyEndDate(WEB3DateUtility.getDate("20040707","yyyyMMdd"));
            l_mfProductParams.setRecruitStartDateSonar(date);
            l_mfProductParams.setRecruitEndDateSonar(WEB3DateUtility.getDate("20040707","yyyyMMdd"));
            l_mfProductParams.setSystemHandlingDiv("2");
            l_mfProductParams.setFundType(MutualFundTypeEnum.FOREIGN_MMF);
            
            FrgnMmfExchangeRateParams l_frgnMmfExchangeRateParams = TestDBUtility.getFrgnMmfExchangeRateRow();
            ExchangeRateParams l_exchangeRateParams = TestDBUtility.getExchangeRateRow();;
            MarketCalendarParams l_marketCalendarParams = new MarketCalendarParams();
            l_marketCalendarParams.setMarketId(3303L);
            l_marketCalendarParams.setTradeDate(CalendarUtils.clearTimeFields(GtlUtils.getSystemTimestamp()));
            l_marketCalendarParams.setHolidayFlag(BooleanEnum.TRUE);
            l_marketCalendarParams.setTradeOpenTime("090000");
            l_marketCalendarParams.setTradeCloseTime("150000");
            l_marketCalendarParams.setCreatedTimestamp(WEB3DateUtility.getDate("20040707","yyyyMMdd"));
            l_marketCalendarParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20040707","yyyyMMdd"));
            
            l_mainAccountParams.setAccountId(101001010010L);

            l_subAccountParams.setAccountId(101001010010L);
            l_subAccountParams.setSubAccountId(10100101001007L);
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
            
            l_productParams.setProductId(1006169090018L);
            l_productParams.setProductType(ProductTypeEnum.MUTUAL_FUND);

            l_marketParams.setMarketCode("0");

            l_tradedProductParams.setTradedProductId(100106139070605L);
            l_tradedProductParams.setProductId(1006169090018L);
            l_tradedProductParams.setProductType(ProductTypeEnum.MUTUAL_FUND);

            l_tradingTimeParams.setInstitutionCode("0D");
            l_tradingTimeParams.setBranchCode("123");
            l_tradingTimeParams.setTradingTimeType("01");
            l_tradingTimeParams.setBizDateType("1");
            l_tradingTimeParams.setMarketCode("N1");
            l_tradingTimeParams.setProductCode("0001000");

            l_mfTradedProductParams.setTradedProductId(100106139070605L);
            l_mfTradedProductParams.setProductId(1006169090018L);
            l_mfTradedProductParams.setMarketId(3303);
            l_mfTradedProductParams.setInstitutionCode("0D");
            
            l_mfProductParams.setProductId(1006169090018L);
            l_mfProductParams.setInstitutionCode("0D");
            l_mfProductParams.setProductCode("0001000");
            l_mfProductParams.setProductIssueCode("0");
            l_mfProductParams.setCurrencyCode("A2");
            l_mfProductParams.setFundType(MutualFundTypeEnum.FOREIGN_MMF);
            l_mfProductParams.setSystemHandlingDiv("1");
            l_mfProductParams.setBuyLimitDiv("0");
            
            l_frgnMmfExchangeRateParams.setCurrencyCode("A2");
            l_frgnMmfExchangeRateParams.setExecTimestamp(WEB3DateUtility.getDate("20040716","yyyyMMdd"));
            
            l_exchangeRateParams.setCurrencyCode("A2");
            
            TestDBUtility.insertWithDel(l_subAccountParams);
            TestDBUtility.insertWithDel(l_mainAccountParams);
            TestDBUtility.insertWithDel(l_productParams);
            TestDBUtility.insertWithDel(l_marketParams);
            TestDBUtility.insertWithDel(l_branchParams);
            TestDBUtility.insertWithDel(l_institutionParams);
            TestDBUtility.insertWithDel(l_tradedProductParams);
            TestDBUtility.insertWithDel(l_mfTradedProductParams);
            TestDBUtility.insertWithDel(l_tradingTimeParams);
            TestDBUtility.insertWithDel(l_mfProductParams);
            TestDBUtility.insertWithDel(l_frgnMmfExchangeRateParams);
            TestDBUtility.insertWithDel(l_exchangeRateParams);
            TestDBUtility.insertWithDel(l_marketCalendarParams);
            
            WEB3GentradeTradingTimeManagementForMock.mockSetTimestamp();

            l_request = new WEB3MutualBuyListRequest();
            
            l_request.pageIndex = "1";
            l_request.pageSize = "3";
            l_request.referenceType = "1";
            l_request.mutualFrgnMmfDisplayDiv = "1";
            

            WEB3MutualBuyListServiceImpl l_impl = new WEB3MutualBuyListServiceImpl();
            l_response = (WEB3MutualBuyListResponse)l_impl.execute(l_request);
            
            assertNull(l_response.buyProductGroups[0].yenConstantValue);
            assertEquals("1", l_response.buyProductGroups[0].referenceRate);
            assertEquals(WEB3DateUtility.getDate("20040716","yyyyMMdd"), 
                l_response.buyProductGroups[0].referenceRateFixedDay);
            assertTrue(l_response.buyProductGroups[0].frgnMmfFlag);   
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
        log.exiting(TEST_END + STR_METHOD_NAME);      
    }
    
    public void test_execute_C0003()
    {
        final String STR_METHOD_NAME = " test_execute_C0003";
        log.entering(TEST_START + STR_METHOD_NAME);

        WEB3MutualBuyListRequest l_request = null;
        this.deleteAll();
        
        WEB3MutualBuyListResponse l_response = null;
        
        try
        {
            MOCK_MANAGER.setIsMockUsed(true);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getAccountId",
                new Class[] {},new Long(101001010010L));
            
            Date l_datExpect = WEB3DateUtility.getDate("20040710","yyyyMMdd");
            WEB3GentradeTradingTimeManagementForMock.mockGetOrderBizDate(l_datExpect);
            
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            ProductParams l_productParams = TestDBUtility.getProductRow();
            MarketParams l_marketParams = TestDBUtility.getMarketRow();
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            TradedProductParams l_tradedProductParams = TestDBUtility.getTradedProductRow();
            MutualFundTradedProductParams l_mfTradedProductParams =
                TestDBUtility.getMutualFundTradedProductRow();
            TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
            Calendar ca =  Calendar.getInstance();
            
            ca.set(2004,7-1,6,9,11,23);
            
            Date date = ca.getTime();
            ThreadLocalSystemAttributesRegistry.setAttribute(
                    TradingSystem.SYSTEM_TIMESTAMP_ATTRIBUTE_NAME,
                    new Timestamp(WEB3DateUtility.getDate("20040706093030","yyyyMMddHHmmss").getTime()));
            
            
            MutualFundProductParams l_mfProductParams = TestDBUtility.getMutualFundProductRow();
            l_mfProductParams.setSystemHandlingDiv("1");
            l_mfProductParams.setBuyLimitDiv("0");
            l_mfProductParams.setBuyStartDate(date);
            l_mfProductParams.setBuyEndDate(WEB3DateUtility.getDate("20040707","yyyyMMdd"));
            l_mfProductParams.setRecruitStartDateSonar(date);
            l_mfProductParams.setRecruitEndDateSonar(WEB3DateUtility.getDate("20040707","yyyyMMdd"));
            l_mfProductParams.setSystemHandlingDiv("2");
            l_mfProductParams.setFundType(MutualFundTypeEnum.FOREIGN_MMF);
            
            FrgnMmfExchangeRateParams l_frgnMmfExchangeRateParams = TestDBUtility.getFrgnMmfExchangeRateRow();
            ExchangeRateParams l_exchangeRateParams = TestDBUtility.getExchangeRateRow();;
            MarketCalendarParams l_marketCalendarParams = new MarketCalendarParams();
            l_marketCalendarParams.setMarketId(3303L);
            l_marketCalendarParams.setTradeDate(CalendarUtils.clearTimeFields(GtlUtils.getSystemTimestamp()));
            l_marketCalendarParams.setHolidayFlag(BooleanEnum.TRUE);
            l_marketCalendarParams.setTradeOpenTime("090000");
            l_marketCalendarParams.setTradeCloseTime("150000");
            l_marketCalendarParams.setCreatedTimestamp(WEB3DateUtility.getDate("20040707","yyyyMMdd"));
            l_marketCalendarParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20040707","yyyyMMdd"));
            
            l_mainAccountParams.setAccountId(101001010010L);

            l_subAccountParams.setAccountId(101001010010L);
            l_subAccountParams.setSubAccountId(10100101001007L);
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
            
            l_productParams.setProductId(1006169090018L);
            l_productParams.setProductType(ProductTypeEnum.MUTUAL_FUND);

            l_marketParams.setMarketCode("0");

            l_tradedProductParams.setTradedProductId(100106139070605L);
            l_tradedProductParams.setProductId(1006169090018L);
            l_tradedProductParams.setProductType(ProductTypeEnum.MUTUAL_FUND);

            l_tradingTimeParams.setInstitutionCode("0D");
            l_tradingTimeParams.setBranchCode("123");
            l_tradingTimeParams.setTradingTimeType("01");
            l_tradingTimeParams.setBizDateType("1");
            l_tradingTimeParams.setMarketCode("N1");
            l_tradingTimeParams.setProductCode("0001000");

            l_mfTradedProductParams.setTradedProductId(100106139070605L);
            l_mfTradedProductParams.setProductId(1006169090018L);
            l_mfTradedProductParams.setMarketId(3303);
            l_mfTradedProductParams.setInstitutionCode("0D");
            
            l_mfProductParams.setProductId(1006169090018L);
            l_mfProductParams.setInstitutionCode("0D");
            l_mfProductParams.setProductCode("0001000");
            l_mfProductParams.setProductIssueCode("0");
            l_mfProductParams.setCurrencyCode("A2");
            l_mfProductParams.setFundType(MutualFundTypeEnum.DOMESTIC);
            l_mfProductParams.setSystemHandlingDiv("1");
            l_mfProductParams.setBuyLimitDiv("0");
            
            l_frgnMmfExchangeRateParams.setCurrencyCode("A2");
            l_frgnMmfExchangeRateParams.setExecTimestamp(WEB3DateUtility.getDate("20040716","yyyyMMdd"));
            
            l_exchangeRateParams.setCurrencyCode("A2");
            l_exchangeRateParams.setExecTimestamp(WEB3DateUtility.getDate("20040716","yyyyMMdd"));
            
            TestDBUtility.insertWithDel(l_subAccountParams);
            TestDBUtility.insertWithDel(l_mainAccountParams);
            TestDBUtility.insertWithDel(l_productParams);
            TestDBUtility.insertWithDel(l_marketParams);
            TestDBUtility.insertWithDel(l_branchParams);
            TestDBUtility.insertWithDel(l_institutionParams);
            TestDBUtility.insertWithDel(l_tradedProductParams);
            TestDBUtility.insertWithDel(l_mfTradedProductParams);
            TestDBUtility.insertWithDel(l_tradingTimeParams);
            TestDBUtility.insertWithDel(l_mfProductParams);
            TestDBUtility.insertWithDel(l_frgnMmfExchangeRateParams);
            TestDBUtility.insertWithDel(l_exchangeRateParams);
            TestDBUtility.insertWithDel(l_marketCalendarParams);
            
            WEB3GentradeTradingTimeManagementForMock.mockSetTimestamp();

            l_request = new WEB3MutualBuyListRequest();
            
            l_request.pageIndex = "1";
            l_request.pageSize = "3";
            l_request.referenceType = "1";
            l_request.mutualFrgnMmfDisplayDiv = "0";
            

            WEB3MutualBuyListServiceImpl l_impl = new WEB3MutualBuyListServiceImpl();
            l_response = (WEB3MutualBuyListResponse)l_impl.execute(l_request);
            
            assertEquals("0", l_response.buyProductGroups[0].yenConstantValue);
            assertEquals("4.55", l_response.buyProductGroups[0].referenceRate);
            assertEquals(WEB3DateUtility.getDate("20040716","yyyyMMdd"), 
                l_response.buyProductGroups[0].referenceRateFixedDay);
            assertFalse(l_response.buyProductGroups[0].frgnMmfFlag);   
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
        log.exiting(TEST_END + STR_METHOD_NAME);      
    }
    
    public void test_execute_C0004()
    {
        final String STR_METHOD_NAME = " test_execute_C0004";
        log.entering(TEST_START + STR_METHOD_NAME);

        WEB3MutualBuyListRequest l_request = null;
        this.deleteAll();
        
        WEB3MutualBuyListResponse l_response = null;
        
        try
        {
            MOCK_MANAGER.setIsMockUsed(true);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getAccountId",
                new Class[] {},new Long(101001010010L));
            
            Date l_datExpect = WEB3DateUtility.getDate("20040710","yyyyMMdd");
            WEB3GentradeTradingTimeManagementForMock.mockGetOrderBizDate(l_datExpect);
            
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            ProductParams l_productParams = TestDBUtility.getProductRow();
            MarketParams l_marketParams = TestDBUtility.getMarketRow();
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            TradedProductParams l_tradedProductParams = TestDBUtility.getTradedProductRow();
            MutualFundTradedProductParams l_mfTradedProductParams =
                TestDBUtility.getMutualFundTradedProductRow();
            TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
            
            Calendar ca =  Calendar.getInstance();
            
            ca.set(2004,7-1,6,9,11,23);
            
            Date date = ca.getTime();
            ThreadLocalSystemAttributesRegistry.setAttribute(
                    TradingSystem.SYSTEM_TIMESTAMP_ATTRIBUTE_NAME,
                    new Timestamp(WEB3DateUtility.getDate("20040706093030","yyyyMMddHHmmss").getTime()));
            
            
            MutualFundProductParams l_mfProductParams = TestDBUtility.getMutualFundProductRow();
            l_mfProductParams.setSystemHandlingDiv("1");
            l_mfProductParams.setBuyLimitDiv("0");
            l_mfProductParams.setBuyStartDate(date);
            l_mfProductParams.setBuyEndDate(WEB3DateUtility.getDate("20040707","yyyyMMdd"));
            l_mfProductParams.setRecruitStartDateSonar(date);
            l_mfProductParams.setRecruitEndDateSonar(WEB3DateUtility.getDate("20040707","yyyyMMdd"));
            l_mfProductParams.setSystemHandlingDiv("2");
            l_mfProductParams.setFundType(MutualFundTypeEnum.FOREIGN_MMF);
            
            MarketCalendarParams l_marketCalendarParams = new MarketCalendarParams();
            l_marketCalendarParams.setMarketId(3303L);
            l_marketCalendarParams.setTradeDate(CalendarUtils.clearTimeFields(GtlUtils.getSystemTimestamp()));
            l_marketCalendarParams.setHolidayFlag(BooleanEnum.TRUE);
            l_marketCalendarParams.setTradeOpenTime("090000");
            l_marketCalendarParams.setTradeCloseTime("150000");
            l_marketCalendarParams.setCreatedTimestamp(WEB3DateUtility.getDate("20040707","yyyyMMdd"));
            l_marketCalendarParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20040707","yyyyMMdd"));
            
            l_mainAccountParams.setAccountId(101001010010L);

            l_subAccountParams.setAccountId(101001010010L);
            l_subAccountParams.setSubAccountId(10100101001007L);
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
            
            l_productParams.setProductId(1006169090018L);
            l_productParams.setProductType(ProductTypeEnum.MUTUAL_FUND);

            l_marketParams.setMarketCode("0");

            l_tradedProductParams.setTradedProductId(100106139070605L);
            l_tradedProductParams.setProductId(1006169090018L);
            l_tradedProductParams.setProductType(ProductTypeEnum.MUTUAL_FUND);

            l_tradingTimeParams.setInstitutionCode("0D");
            l_tradingTimeParams.setBranchCode("123");
            l_tradingTimeParams.setTradingTimeType("01");
            l_tradingTimeParams.setBizDateType("1");
            l_tradingTimeParams.setMarketCode("N1");
            l_tradingTimeParams.setProductCode("0001000");

            l_mfTradedProductParams.setTradedProductId(100106139070605L);
            l_mfTradedProductParams.setProductId(1006169090018L);
            l_mfTradedProductParams.setMarketId(3303);
            l_mfTradedProductParams.setInstitutionCode("0D");
            
            l_mfProductParams.setProductId(1006169090018L);
            l_mfProductParams.setInstitutionCode("0D");
            l_mfProductParams.setProductCode("0001000");
            l_mfProductParams.setProductIssueCode("0");
            l_mfProductParams.setCurrencyCode("T0");
            l_mfProductParams.setFundType(MutualFundTypeEnum.FOREIGN_MMF);
            l_mfProductParams.setSystemHandlingDiv("1");
            l_mfProductParams.setBuyLimitDiv("0");
            Date l_datBizDate = GtlUtils.getTradingSystem().getSystemTimestamp();
            Date l_recruitStartDateSonar = new WEB3GentradeBizDate(
                new Timestamp(l_datBizDate.getTime())).roll(-1);;
            l_mfProductParams.setRecruitStartDateSonar(l_recruitStartDateSonar);
            
            Date l_recruitEndDateSonar = new WEB3GentradeBizDate(
                new Timestamp(l_datBizDate.getTime())).roll(1);;
            l_mfProductParams.setRecruitEndDateSonar(l_recruitEndDateSonar);
                        
            TestDBUtility.insertWithDel(l_subAccountParams);
            TestDBUtility.insertWithDel(l_mainAccountParams);
            TestDBUtility.insertWithDel(l_productParams);
            TestDBUtility.insertWithDel(l_marketParams);
            TestDBUtility.insertWithDel(l_branchParams);
            TestDBUtility.insertWithDel(l_institutionParams);
            TestDBUtility.insertWithDel(l_tradedProductParams);
            TestDBUtility.insertWithDel(l_mfTradedProductParams);
            TestDBUtility.insertWithDel(l_tradingTimeParams);
            TestDBUtility.insertWithDel(l_mfProductParams);
            TestDBUtility.insertWithDel(l_marketCalendarParams);
            
            WEB3GentradeTradingTimeManagementForMock.mockSetTimestamp();

            l_request = new WEB3MutualBuyListRequest();
            
            l_request.pageIndex = "1";
            l_request.pageSize = "3";
            l_request.referenceType = "1";
            l_request.mutualFrgnMmfDisplayDiv = "1";
            

            WEB3MutualBuyListServiceImpl l_impl = new WEB3MutualBuyListServiceImpl();
            l_response = (WEB3MutualBuyListResponse)l_impl.execute(l_request);
            
            assertNull(l_response.buyProductGroups[0].yenConstantValue);
            assertNull(l_response.buyProductGroups[0].referenceRate);
            assertNull(l_response.buyProductGroups[0].referenceRateFixedDay);
            assertTrue(l_response.buyProductGroups[0].frgnMmfFlag);   
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
        log.exiting(TEST_END + STR_METHOD_NAME);      
    }
    
    public void test_execute_C0005()
    {
        final String STR_METHOD_NAME = " test_execute_C0005";
        log.entering(TEST_START + STR_METHOD_NAME);

        WEB3MutualBuyListRequest l_request = null;
        this.deleteAll();
        
        WEB3MutualBuyListResponse l_response = null;
        
        try
        {
            MOCK_MANAGER.setIsMockUsed(true);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getAccountId",
                new Class[] {},new Long(101001010010L));
            
            Date l_datExpect = WEB3DateUtility.getDate("20040710","yyyyMMdd");
            WEB3GentradeTradingTimeManagementForMock.mockGetOrderBizDate(l_datExpect);
            
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            ProductParams l_productParams = TestDBUtility.getProductRow();
            MarketParams l_marketParams = TestDBUtility.getMarketRow();
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            TradedProductParams l_tradedProductParams = TestDBUtility.getTradedProductRow();
            MutualFundTradedProductParams l_mfTradedProductParams =
                TestDBUtility.getMutualFundTradedProductRow();
            TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
            
            Calendar ca =  Calendar.getInstance();
            
            ca.set(2004,7-1,6,9,11,23);
            
            Date date = ca.getTime();
            ThreadLocalSystemAttributesRegistry.setAttribute(
                    TradingSystem.SYSTEM_TIMESTAMP_ATTRIBUTE_NAME,
                    new Timestamp(WEB3DateUtility.getDate("20040706093030","yyyyMMddHHmmss").getTime()));
            
            
            MutualFundProductParams l_mfProductParams = TestDBUtility.getMutualFundProductRow();
            l_mfProductParams.setSystemHandlingDiv("1");
            l_mfProductParams.setBuyLimitDiv("0");
            l_mfProductParams.setBuyStartDate(date);
            l_mfProductParams.setBuyEndDate(WEB3DateUtility.getDate("20040707","yyyyMMdd"));
            l_mfProductParams.setRecruitStartDateSonar(date);
            l_mfProductParams.setRecruitEndDateSonar(WEB3DateUtility.getDate("20040707","yyyyMMdd"));
            l_mfProductParams.setSystemHandlingDiv("2");
            l_mfProductParams.setFundType(MutualFundTypeEnum.FOREIGN_MMF);
            
            FrgnMmfExchangeRateParams l_frgnMmfExchangeRateParams = TestDBUtility.getFrgnMmfExchangeRateRow();
            

            ExchangeRateParams l_exchangeRateParams = TestDBUtility.getExchangeRateRow();;
            MarketCalendarParams l_marketCalendarParams = new MarketCalendarParams();
            l_marketCalendarParams.setMarketId(3303L);
            l_marketCalendarParams.setTradeDate(CalendarUtils.clearTimeFields(GtlUtils.getSystemTimestamp()));
            l_marketCalendarParams.setHolidayFlag(BooleanEnum.TRUE);
            l_marketCalendarParams.setTradeOpenTime("090000");
            l_marketCalendarParams.setTradeCloseTime("150000");
            l_marketCalendarParams.setCreatedTimestamp(WEB3DateUtility.getDate("20040707","yyyyMMdd"));
            l_marketCalendarParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20040707","yyyyMMdd"));
            
            l_mainAccountParams.setAccountId(101001010010L);

            l_subAccountParams.setAccountId(101001010010L);
            l_subAccountParams.setSubAccountId(10100101001007L);
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
            
            l_productParams.setProductId(1006169090018L);
            l_productParams.setProductType(ProductTypeEnum.MUTUAL_FUND);

            l_marketParams.setMarketCode("0");

            l_tradedProductParams.setTradedProductId(100106139070605L);
            l_tradedProductParams.setProductId(1006169090018L);
            l_tradedProductParams.setProductType(ProductTypeEnum.MUTUAL_FUND);

            l_tradingTimeParams.setInstitutionCode("0D");
            l_tradingTimeParams.setBranchCode("123");
            l_tradingTimeParams.setTradingTimeType("01");
            l_tradingTimeParams.setBizDateType("1");
            l_tradingTimeParams.setMarketCode("N1");
            l_tradingTimeParams.setProductCode("0001000");

            l_mfTradedProductParams.setTradedProductId(100106139070605L);
            l_mfTradedProductParams.setProductId(1006169090018L);
            l_mfTradedProductParams.setMarketId(3303);
            l_mfTradedProductParams.setInstitutionCode("0D");
            
            l_mfProductParams.setProductId(1006169090018L);
            l_mfProductParams.setInstitutionCode("0D");
            l_mfProductParams.setProductCode("0001000");
            l_mfProductParams.setProductIssueCode("0");
            l_mfProductParams.setCurrencyCode("A2");
            l_mfProductParams.setFundType(MutualFundTypeEnum.FOREIGN_MMF);
            l_mfProductParams.setSystemHandlingDiv("1");
            l_mfProductParams.setBuyLimitDiv("0");
            Date l_datBizDate = GtlUtils.getTradingSystem().getSystemTimestamp();
            Date l_recruitStartDateSonar = new WEB3GentradeBizDate(
                new Timestamp(l_datBizDate.getTime())).roll(-1);;
            l_mfProductParams.setRecruitStartDateSonar(l_recruitStartDateSonar);
            
            Date l_recruitEndDateSonar = new WEB3GentradeBizDate(
                new Timestamp(l_datBizDate.getTime())).roll(1);;
            l_mfProductParams.setRecruitEndDateSonar(l_recruitEndDateSonar);
            
            l_frgnMmfExchangeRateParams.setCurrencyCode("A2");
            l_frgnMmfExchangeRateParams.setExecTimestamp(WEB3DateUtility.getDate("20040716","yyyyMMdd"));
            
            l_exchangeRateParams.setCurrencyCode("A2");
            
            TestDBUtility.insertWithDel(l_subAccountParams);
            TestDBUtility.insertWithDel(l_mainAccountParams);
            TestDBUtility.insertWithDel(l_productParams);
            TestDBUtility.insertWithDel(l_marketParams);
            TestDBUtility.insertWithDel(l_branchParams);
            TestDBUtility.insertWithDel(l_institutionParams);
            TestDBUtility.insertWithDel(l_tradedProductParams);
            TestDBUtility.insertWithDel(l_mfTradedProductParams);
            TestDBUtility.insertWithDel(l_tradingTimeParams);
            TestDBUtility.insertWithDel(l_mfProductParams);
            TestDBUtility.insertWithDel(l_frgnMmfExchangeRateParams);
            TestDBUtility.insertWithDel(l_exchangeRateParams);
            TestDBUtility.insertWithDel(l_marketCalendarParams);
            
            WEB3GentradeTradingTimeManagementForMock.mockSetTimestamp();

            l_request = new WEB3MutualBuyListRequest();
            
            l_request.pageIndex = "1";
            l_request.pageSize = "3";
            l_request.referenceType = "1";
            l_request.mutualFrgnMmfDisplayDiv = "1";
            

            WEB3MutualBuyListServiceImpl l_impl = new WEB3MutualBuyListServiceImpl();
            l_response = (WEB3MutualBuyListResponse)l_impl.execute(l_request);
            
            assertNull(l_response.buyProductGroups[0].yenConstantValue);
            assertEquals("1", l_response.buyProductGroups[0].referenceRate);
            assertEquals(WEB3DateUtility.getDate("20040716","yyyyMMdd"), 
                l_response.buyProductGroups[0].referenceRateFixedDay);
            assertTrue(l_response.buyProductGroups[0].frgnMmfFlag);   
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
        log.exiting(TEST_END + STR_METHOD_NAME);      
    }
    
    public void test_execute_C0006()
    {
        final String STR_METHOD_NAME = " test_execute_C0006";
        log.entering(TEST_START + STR_METHOD_NAME);

        WEB3MutualBuyListRequest l_request = null;
        this.deleteAll();
        
        WEB3MutualBuyListResponse l_response = null;
        
        try
        {
            MOCK_MANAGER.setIsMockUsed(true);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getAccountId",
                new Class[] {},new Long(101001010010L));
            
            Date l_datExpect = WEB3DateUtility.getDate("20040710","yyyyMMdd");
            WEB3GentradeTradingTimeManagementForMock.mockGetOrderBizDate(l_datExpect);
            
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            ProductParams l_productParams = TestDBUtility.getProductRow();
            MarketParams l_marketParams = TestDBUtility.getMarketRow();
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            TradedProductParams l_tradedProductParams = TestDBUtility.getTradedProductRow();
            MutualFundTradedProductParams l_mfTradedProductParams =
                TestDBUtility.getMutualFundTradedProductRow();
            TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
            Calendar ca =  Calendar.getInstance();
            
            ca.set(2004,7-1,6,9,11,23);
            
            Date date = ca.getTime();
            ThreadLocalSystemAttributesRegistry.setAttribute(
                    TradingSystem.SYSTEM_TIMESTAMP_ATTRIBUTE_NAME,
                    new Timestamp(WEB3DateUtility.getDate("20040706093030","yyyyMMddHHmmss").getTime()));
            
            
            MutualFundProductParams l_mfProductParams = TestDBUtility.getMutualFundProductRow();
            l_mfProductParams.setSystemHandlingDiv("1");
            l_mfProductParams.setBuyLimitDiv("0");
            l_mfProductParams.setBuyStartDate(date);
            l_mfProductParams.setBuyEndDate(WEB3DateUtility.getDate("20040707","yyyyMMdd"));
            l_mfProductParams.setRecruitStartDateSonar(date);
            l_mfProductParams.setRecruitEndDateSonar(WEB3DateUtility.getDate("20040707","yyyyMMdd"));
            l_mfProductParams.setSystemHandlingDiv("2");
            l_mfProductParams.setFundType(MutualFundTypeEnum.FOREIGN_MMF);
            
            
            FrgnMmfExchangeRateParams l_frgnMmfExchangeRateParams = TestDBUtility.getFrgnMmfExchangeRateRow();
            ExchangeRateParams l_exchangeRateParams = TestDBUtility.getExchangeRateRow();;
            MarketCalendarParams l_marketCalendarParams = new MarketCalendarParams();
            l_marketCalendarParams.setMarketId(3303L);
            l_marketCalendarParams.setTradeDate(CalendarUtils.clearTimeFields(GtlUtils.getSystemTimestamp()));
            l_marketCalendarParams.setHolidayFlag(BooleanEnum.TRUE);
            l_marketCalendarParams.setTradeOpenTime("090000");
            l_marketCalendarParams.setTradeCloseTime("150000");
            l_marketCalendarParams.setCreatedTimestamp(WEB3DateUtility.getDate("20040707","yyyyMMdd"));
            l_marketCalendarParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20040707","yyyyMMdd"));
            
            l_mainAccountParams.setAccountId(101001010010L);

            l_subAccountParams.setAccountId(101001010010L);
            l_subAccountParams.setSubAccountId(10100101001007L);
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
            
            l_productParams.setProductId(1006169090018L);
            l_productParams.setProductType(ProductTypeEnum.MUTUAL_FUND);

            l_marketParams.setMarketCode("0");

            l_tradedProductParams.setTradedProductId(100106139070605L);
            l_tradedProductParams.setProductId(1006169090018L);
            l_tradedProductParams.setProductType(ProductTypeEnum.MUTUAL_FUND);

            l_tradingTimeParams.setInstitutionCode("0D");
            l_tradingTimeParams.setBranchCode("123");
            l_tradingTimeParams.setTradingTimeType("01");
            l_tradingTimeParams.setBizDateType("1");
            l_tradingTimeParams.setMarketCode("N1");
            l_tradingTimeParams.setProductCode("0001000");

            l_mfTradedProductParams.setTradedProductId(100106139070605L);
            l_mfTradedProductParams.setProductId(1006169090018L);
            l_mfTradedProductParams.setMarketId(3303);
            l_mfTradedProductParams.setInstitutionCode("0D");
            
            l_mfProductParams.setProductId(1006169090018L);
            l_mfProductParams.setInstitutionCode("0D");
            l_mfProductParams.setProductCode("0001000");
            l_mfProductParams.setProductIssueCode("0");
            l_mfProductParams.setCurrencyCode("A2");
            l_mfProductParams.setFundType(MutualFundTypeEnum.DOMESTIC);
            l_mfProductParams.setSystemHandlingDiv("1");
            l_mfProductParams.setBuyLimitDiv("0");
            Date l_datBizDate = GtlUtils.getTradingSystem().getSystemTimestamp();
            Date l_recruitStartDateSonar = new WEB3GentradeBizDate(
                new Timestamp(l_datBizDate.getTime())).roll(-1);;
            l_mfProductParams.setRecruitStartDateSonar(l_recruitStartDateSonar);
            
            Date l_recruitEndDateSonar = new WEB3GentradeBizDate(
                new Timestamp(l_datBizDate.getTime())).roll(1);;
            l_mfProductParams.setRecruitEndDateSonar(l_recruitEndDateSonar);
            
            l_frgnMmfExchangeRateParams.setCurrencyCode("A2");
            l_frgnMmfExchangeRateParams.setExecTimestamp(WEB3DateUtility.getDate("20040716","yyyyMMdd"));
            
            l_exchangeRateParams.setCurrencyCode("A2");
            l_exchangeRateParams.setExecTimestamp(WEB3DateUtility.getDate("20040716","yyyyMMdd"));
            
            TestDBUtility.insertWithDel(l_subAccountParams);
            TestDBUtility.insertWithDel(l_mainAccountParams);
            TestDBUtility.insertWithDel(l_productParams);
            TestDBUtility.insertWithDel(l_marketParams);
            TestDBUtility.insertWithDel(l_branchParams);
            TestDBUtility.insertWithDel(l_institutionParams);
            TestDBUtility.insertWithDel(l_tradedProductParams);
            TestDBUtility.insertWithDel(l_mfTradedProductParams);
            TestDBUtility.insertWithDel(l_tradingTimeParams);
            TestDBUtility.insertWithDel(l_mfProductParams);
            TestDBUtility.insertWithDel(l_frgnMmfExchangeRateParams);
            TestDBUtility.insertWithDel(l_exchangeRateParams);
            TestDBUtility.insertWithDel(l_marketCalendarParams);
            
            WEB3GentradeTradingTimeManagementForMock.mockSetTimestamp();

            l_request = new WEB3MutualBuyListRequest();
            
            l_request.pageIndex = "1";
            l_request.pageSize = "3";
            l_request.referenceType = "1";
            l_request.mutualFrgnMmfDisplayDiv = "0";
            

            WEB3MutualBuyListServiceImpl l_impl = new WEB3MutualBuyListServiceImpl();
            l_response = (WEB3MutualBuyListResponse)l_impl.execute(l_request);
            
            assertEquals("0", l_response.buyProductGroups[0].yenConstantValue);
            assertEquals("4.55", l_response.buyProductGroups[0].referenceRate);
            assertEquals(WEB3DateUtility.getDate("20040716","yyyyMMdd"), 
                l_response.buyProductGroups[0].referenceRateFixedDay);
            assertFalse(l_response.buyProductGroups[0].frgnMmfFlag);  
            this.deleteAll();
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
        log.exiting(TEST_END + STR_METHOD_NAME);      
    }
 
//    public void test_execute_C0007()
//    {
//        final String STR_METHOD_NAME = " test_execute_C0007";
//        log.entering(TEST_START + STR_METHOD_NAME);
//
//        WEB3MutualBuyListRequest l_request = null;
//        this.deleteAll();
//        
//        WEB3MutualBuyListResponse l_response = null;
//        
//        try
//        {
//            ErrorInfo info = new ErrorInfo();
//            info = WEB3ErrorCatalog.SYSTEM_ERROR_80003;
//            ProcessingResult processingResult =
//                ProcessingResult.newFailedResultInstance(info);
//            
//            OrderValidationResult l_validationResult = new OrderValidationResult(processingResult);
//            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
//                    "webbroker3.gentrade.WEB3GentradeOrderValidator", 
//                    "validateAccountForTrading",
//                    new Class[] {WEB3GentradeMainAccount.class, Timestamp.class},
//                    l_validationResult);
//            
//            MOCK_MANAGER.setIsMockUsed(true);
//            
//            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
//                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
//                "getAccountId",
//                new Class[] {},new Long(101001010010L));
//                        
//            Date l_datExpect = WEB3DateUtility.getDate("20040710","yyyyMMdd");
//            WEB3GentradeTradingTimeManagementForMock.mockGetOrderBizDate(l_datExpect);
//            
//            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
//            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
//            ProductParams l_productParams = TestDBUtility.getProductRow();
//            MarketParams l_marketParams = TestDBUtility.getMarketRow();
//            BranchParams l_branchParams = TestDBUtility.getBranchRow();
//            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
//            TradedProductParams l_tradedProductParams = TestDBUtility.getTradedProductRow();
//            MutualFundTradedProductParams l_mfTradedProductParams =
//                TestDBUtility.getMutualFundTradedProductRow();
//            TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
//            
//            Calendar ca =  Calendar.getInstance();
//            
//            ca.set(2004,7-1,6,9,11,23);
//            
//            Date date = ca.getTime();
//            ThreadLocalSystemAttributesRegistry.setAttribute(
//                    TradingSystem.SYSTEM_TIMESTAMP_ATTRIBUTE_NAME,
//                    new Timestamp(WEB3DateUtility.getDate("20040706093030","yyyyMMddHHmmss").getTime()));
//            
//            
//            MutualFundProductParams l_mfProductParams = TestDBUtility.getMutualFundProductRow();
//            l_mfProductParams.setSystemHandlingDiv("1");
//            l_mfProductParams.setBuyLimitDiv("0");
//            l_mfProductParams.setBuyStartDate(date);
//            l_mfProductParams.setBuyEndDate(WEB3DateUtility.getDate("20040707","yyyyMMdd"));
//            l_mfProductParams.setRecruitStartDateSonar(date);
//            l_mfProductParams.setRecruitEndDateSonar(WEB3DateUtility.getDate("20040707","yyyyMMdd"));
//            l_mfProductParams.setSystemHandlingDiv("2");
//            l_mfProductParams.setFundType(MutualFundTypeEnum.FOREIGN_MMF);
//            
//            FrgnMmfExchangeRateParams l_frgnMmfExchangeRateParams = TestDBUtility.getFrgnMmfExchangeRateRow();
//            ExchangeRateParams l_exchangeRateParams = TestDBUtility.getExchangeRateRow();;
//            MarketCalendarParams l_marketCalendarParams = new MarketCalendarParams();
//            l_marketCalendarParams.setMarketId(3303L);
//            l_marketCalendarParams.setTradeDate(CalendarUtils.clearTimeFields(GtlUtils.getSystemTimestamp()));
//            l_marketCalendarParams.setHolidayFlag(BooleanEnum.TRUE);
//            l_marketCalendarParams.setTradeOpenTime("090000");
//            l_marketCalendarParams.setTradeCloseTime("150000");
//            l_marketCalendarParams.setCreatedTimestamp(WEB3DateUtility.getDate("20040707","yyyyMMdd"));
//            l_marketCalendarParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20040707","yyyyMMdd"));
//            
//            l_mainAccountParams.setAccountId(101001010010L);
//
//            l_subAccountParams.setAccountId(101001010010L);
//            l_subAccountParams.setSubAccountId(10100101001007L);
//            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
//            
//            l_productParams.setProductId(1006169090018L);
//            l_productParams.setProductType(ProductTypeEnum.MUTUAL_FUND);
//
//            l_marketParams.setMarketCode("0");
//
//            l_tradedProductParams.setTradedProductId(100106139070605L);
//            l_tradedProductParams.setProductId(1006169090018L);
//            l_tradedProductParams.setProductType(ProductTypeEnum.MUTUAL_FUND);
//
//            l_tradingTimeParams.setInstitutionCode("0D");
//            l_tradingTimeParams.setBranchCode("123");
//            l_tradingTimeParams.setTradingTimeType("01");
//            l_tradingTimeParams.setBizDateType("1");
//            l_tradingTimeParams.setMarketCode("N1");
//            l_tradingTimeParams.setProductCode("0001000");
//
//            l_mfTradedProductParams.setTradedProductId(100106139070605L);
//            l_mfTradedProductParams.setProductId(1006169090018L);
//            l_mfTradedProductParams.setMarketId(3303);
//            l_mfTradedProductParams.setInstitutionCode("0D");
//            
//            l_mfProductParams.setProductId(1006169090018L);
//            l_mfProductParams.setInstitutionCode("0D");
//            l_mfProductParams.setProductCode("0001000");
//            l_mfProductParams.setProductIssueCode("0");
//            l_mfProductParams.setCurrencyCode("A2");
//            l_mfProductParams.setFundType(MutualFundTypeEnum.DOMESTIC);
//            l_mfProductParams.setSystemHandlingDiv("1");
//            l_mfProductParams.setBuyLimitDiv("0");
//            Date l_datBizDate = GtlUtils.getTradingSystem().getSystemTimestamp();
//            Date l_recruitStartDateSonar = new WEB3GentradeBizDate(
//                new Timestamp(l_datBizDate.getTime())).roll(-1);;
//            l_mfProductParams.setRecruitStartDateSonar(l_recruitStartDateSonar);
//            
//            Date l_recruitEndDateSonar = new WEB3GentradeBizDate(
//                new Timestamp(l_datBizDate.getTime())).roll(1);;
//            l_mfProductParams.setRecruitEndDateSonar(l_recruitEndDateSonar);
//            
//            l_frgnMmfExchangeRateParams.setCurrencyCode("A2");
//            l_frgnMmfExchangeRateParams.setExecTimestamp(WEB3DateUtility.getDate("20040716","yyyyMMdd"));
//            
//            l_exchangeRateParams.setCurrencyCode("A2");
//            l_exchangeRateParams.setExecTimestamp(WEB3DateUtility.getDate("20040716","yyyyMMdd"));
//            
//            TestDBUtility.insertWithDel(l_subAccountParams);
//            TestDBUtility.insertWithDel(l_mainAccountParams);
//            TestDBUtility.insertWithDel(l_productParams);
//            TestDBUtility.insertWithDel(l_marketParams);
//            TestDBUtility.insertWithDel(l_branchParams);
//            TestDBUtility.insertWithDel(l_institutionParams);
//            TestDBUtility.insertWithDel(l_tradedProductParams);
//            TestDBUtility.insertWithDel(l_mfTradedProductParams);
//            TestDBUtility.insertWithDel(l_tradingTimeParams);
//            TestDBUtility.insertWithDel(l_mfProductParams);
//            TestDBUtility.insertWithDel(l_frgnMmfExchangeRateParams);
//            TestDBUtility.insertWithDel(l_exchangeRateParams);
//            TestDBUtility.insertWithDel(l_marketCalendarParams);
//            
//            WEB3GentradeTradingTimeManagementForMock.mockSetTimestamp();
//
//            l_request = new WEB3MutualBuyListRequest();
//            
//            l_request.pageIndex = "1";
//            l_request.pageSize = "3";
//            l_request.referenceType = "1";
//            l_request.mutualFrgnMmfDisplayDiv = "0";
//            
//            WEB3MutualBuyListServiceImpl l_impl = new WEB3MutualBuyListServiceImpl();
//            l_response = (WEB3MutualBuyListResponse)l_impl.execute(l_request);
//            
//            this.deleteAll();
//            fail();
//        }
//        catch(WEB3BusinessLayerException l_ex)
//        {
//            log.error(l_ex.getMessage(), l_ex);
//            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80003, l_ex.getErrorInfo());
//        }
//        catch (Exception l_ex)
//        {
//            log.error(l_ex.getMessage(), l_ex);
//            fail();
//        }
//        log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
//        log.exiting(TEST_END + STR_METHOD_NAME);      
//    }
    
    public void test_execute_C0008()
    {
        final String STR_METHOD_NAME = " test_execute_C0008";
        log.entering(TEST_START + STR_METHOD_NAME);

        WEB3MutualBuyListRequest l_request = null;
        this.deleteAll();
        
        WEB3MutualBuyListResponse l_response = null;
        
        try
        {
            MOCK_MANAGER.setIsMockUsed(true);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getAccountId",
                new Class[] {},new Long(101001010010L));
            
            Date l_datExpect = WEB3DateUtility.getDate("20040710","yyyyMMdd");
            WEB3GentradeTradingTimeManagementForMock.mockGetOrderBizDate(l_datExpect);
            
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            ProductParams l_productParams = TestDBUtility.getProductRow();
            MarketParams l_marketParams = TestDBUtility.getMarketRow();
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            TradedProductParams l_tradedProductParams = TestDBUtility.getTradedProductRow();
            MutualFundTradedProductParams l_mfTradedProductParams =
                TestDBUtility.getMutualFundTradedProductRow();
            TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();

            Calendar ca =  Calendar.getInstance();
            
            ca.set(2004,7-1,6,9,11,23);
            
            Date date = ca.getTime();
            ThreadLocalSystemAttributesRegistry.setAttribute(
                    TradingSystem.SYSTEM_TIMESTAMP_ATTRIBUTE_NAME,
                    new Timestamp(WEB3DateUtility.getDate("20040706093030","yyyyMMddHHmmss").getTime()));
            
            
            MutualFundProductParams l_mfProductParams = TestDBUtility.getMutualFundProductRow();
            l_mfProductParams.setSystemHandlingDiv("1");
            l_mfProductParams.setBuyLimitDiv("0");
            l_mfProductParams.setBuyStartDate(date);
            l_mfProductParams.setBuyEndDate(WEB3DateUtility.getDate("20040707","yyyyMMdd"));
            l_mfProductParams.setRecruitStartDateSonar(date);
            l_mfProductParams.setRecruitEndDateSonar(WEB3DateUtility.getDate("20040707","yyyyMMdd"));
            l_mfProductParams.setSystemHandlingDiv("2");
            l_mfProductParams.setFundType(MutualFundTypeEnum.FOREIGN_MMF);
            
            MarketCalendarParams l_marketCalendarParams = new MarketCalendarParams();
            l_marketCalendarParams.setMarketId(3303L);
            l_marketCalendarParams.setTradeDate(CalendarUtils.clearTimeFields(GtlUtils.getSystemTimestamp()));
            l_marketCalendarParams.setHolidayFlag(BooleanEnum.TRUE);
            l_marketCalendarParams.setTradeOpenTime("090000");
            l_marketCalendarParams.setTradeCloseTime("150000");
            l_marketCalendarParams.setCreatedTimestamp(WEB3DateUtility.getDate("20040707","yyyyMMdd"));
            l_marketCalendarParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20040707","yyyyMMdd"));

            l_mainAccountParams.setAccountId(101001010010L);

            l_subAccountParams.setAccountId(101001010010L);
            l_subAccountParams.setSubAccountId(10100101001007L);
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
            
            l_productParams.setProductId(1006169090018L);
            l_productParams.setProductType(ProductTypeEnum.MUTUAL_FUND);

            l_marketParams.setMarketCode("0");

            l_tradedProductParams.setTradedProductId(100106139070605L);
            l_tradedProductParams.setProductId(1006169090018L);
            l_tradedProductParams.setProductType(ProductTypeEnum.MUTUAL_FUND);

            l_tradingTimeParams.setInstitutionCode("0D");
            l_tradingTimeParams.setBranchCode("123");
            l_tradingTimeParams.setTradingTimeType("01");
            l_tradingTimeParams.setBizDateType("1");
            l_tradingTimeParams.setMarketCode("N1");
            l_tradingTimeParams.setProductCode("0001000");

            l_mfTradedProductParams.setTradedProductId(100106139070605L);
            l_mfTradedProductParams.setProductId(1006169090018L);
            l_mfTradedProductParams.setMarketId(3303);
            l_mfTradedProductParams.setInstitutionCode("0D");
            
            l_mfProductParams.setProductId(1006169090018L);
            l_mfProductParams.setInstitutionCode("0D");
            l_mfProductParams.setProductCode("0001000");
            l_mfProductParams.setProductIssueCode("0");
            l_mfProductParams.setCurrencyCode("T0");
            l_mfProductParams.setFundType(MutualFundTypeEnum.FOREIGN_MMF);
            l_mfProductParams.setSystemHandlingDiv("1");
            l_mfProductParams.setBuyLimitDiv("0");
            l_mfProductParams.setFrgnNewBuyUnitAmt(new Long(12345));
            l_mfProductParams.setFrgnNewBuyMinAmt(new Long(45612));
            l_mfProductParams.setFrgnAddBuyUnitAmt(new Long(78945));
            l_mfProductParams.setFrgnAddBuyMinAmt(new Long(7410));

            TestDBUtility.insertWithDel(l_subAccountParams);
            TestDBUtility.insertWithDel(l_mainAccountParams);
            TestDBUtility.insertWithDel(l_productParams);
            TestDBUtility.insertWithDel(l_marketParams);
            TestDBUtility.insertWithDel(l_branchParams);
            TestDBUtility.insertWithDel(l_institutionParams);
            TestDBUtility.insertWithDel(l_tradedProductParams);
            TestDBUtility.insertWithDel(l_mfTradedProductParams);
            TestDBUtility.insertWithDel(l_tradingTimeParams);
            TestDBUtility.insertWithDel(l_mfProductParams);
            TestDBUtility.insertWithDel(l_marketCalendarParams);

            WEB3GentradeTradingTimeManagementForMock.mockSetTimestamp();
            
            l_request = new WEB3MutualBuyListRequest();
            
            l_request.pageIndex = "1";
            l_request.pageSize = "3";
            l_request.referenceType = "1";
            l_request.mutualFrgnMmfDisplayDiv = "1";
            

            WEB3MutualBuyListServiceImpl l_impl = new WEB3MutualBuyListServiceImpl();
            l_response = (WEB3MutualBuyListResponse)l_impl.execute(l_request);
            
            assertEquals("12345", l_response.buyProductGroups[0].newBuyFrgnUnitAmt);
            assertEquals("45612", l_response.buyProductGroups[0].newBuyFrgnMinAmt);
            assertEquals("78945", l_response.buyProductGroups[0].addBuyFrgnUnitAmt);
            assertEquals("7410", l_response.buyProductGroups[0].addBuyFrgnMinAmt);   
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
     * 
     * (システム取扱区分 = ? 
     * and ((買付制限区分 = ? and to_char(買付開始日, 'YYYYMMDDHH24MISS') <= ? 
     * and to_char(買付終了日, 'YYYYMMDD') >= ? ) 
     * or (to_char(募集開始日（SONAR）, 'YYYYMMDDHH24MISS') <= ?
     * and to_char(募集終了日（SONAR）, 'YYYYMMDD') >= ?))) 
     * or システム取扱区分 = 2 
     *
     */
    public void test_execute_C0011()
    {
        final String STR_METHOD_NAME = " test_execute_C0011";
        log.entering(TEST_START + STR_METHOD_NAME);

        WEB3MutualBuyListRequest l_request = null;
        this.deleteAll();
        
        WEB3MutualBuyListResponse l_response = null;
        
        try
        {
            MOCK_MANAGER.setIsMockUsed(true);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getAccountId",
                new Class[] {},new Long(101001010010L));
            
            Date l_datExpect = WEB3DateUtility.getDate("20040710","yyyyMMdd");
            WEB3GentradeTradingTimeManagementForMock.mockGetOrderBizDate(l_datExpect);
            
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            ProductParams l_productParams = TestDBUtility.getProductRow();
            MarketParams l_marketParams = TestDBUtility.getMarketRow();
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            TradedProductParams l_tradedProductParams = TestDBUtility.getTradedProductRow();
            MutualFundTradedProductParams l_mfTradedProductParams =
                TestDBUtility.getMutualFundTradedProductRow();
            TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();

            Calendar ca =  Calendar.getInstance();
            
            ca.set(2004,7-1,6,9,11,23);
            
            Date date = ca.getTime();
            ThreadLocalSystemAttributesRegistry.setAttribute(
                    TradingSystem.SYSTEM_TIMESTAMP_ATTRIBUTE_NAME,
                    new Timestamp(WEB3DateUtility.getDate("20040706093030","yyyyMMddHHmmss").getTime()));
            
            
            MutualFundProductParams l_mfProductParams = TestDBUtility.getMutualFundProductRow();
            //買付制限区分
            l_mfProductParams.setBuyLimitDiv("1");
            //買付開始日
            l_mfProductParams.setBuyStartDate(date);
            //買付終了日
            l_mfProductParams.setBuyEndDate(WEB3DateUtility.getDate("20050707","yyyyMMdd"));
            //募集開始日（SONAR）
            l_mfProductParams.setRecruitStartDateSonar(date);
            //募集終了日（SONAR）
            l_mfProductParams.setRecruitEndDateSonar(WEB3DateUtility.getDate("20050707","yyyyMMdd"));
            //システム取扱区分
            l_mfProductParams.setSystemHandlingDiv("2");
            l_mfProductParams.setFundType(MutualFundTypeEnum.FOREIGN_MMF);
            
            MarketCalendarParams l_marketCalendarParams = new MarketCalendarParams();
            l_marketCalendarParams.setMarketId(3303L);
            l_marketCalendarParams.setTradeDate(CalendarUtils.clearTimeFields(GtlUtils.getSystemTimestamp()));
            l_marketCalendarParams.setHolidayFlag(BooleanEnum.TRUE);
            l_marketCalendarParams.setTradeOpenTime("090000");
            l_marketCalendarParams.setTradeCloseTime("150000");
            l_marketCalendarParams.setCreatedTimestamp(WEB3DateUtility.getDate("20040707","yyyyMMdd"));
            l_marketCalendarParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20040707","yyyyMMdd"));

            l_mainAccountParams.setAccountId(101001010010L);

            l_subAccountParams.setAccountId(101001010010L);
            l_subAccountParams.setSubAccountId(10100101001007L);
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
            
            l_productParams.setProductId(1006169090018L);
            l_productParams.setProductType(ProductTypeEnum.MUTUAL_FUND);

            l_marketParams.setMarketCode("0");

            l_tradedProductParams.setTradedProductId(100106139070605L);
            l_tradedProductParams.setProductId(1006169090018L);
            l_tradedProductParams.setProductType(ProductTypeEnum.MUTUAL_FUND);

            l_tradingTimeParams.setInstitutionCode("0D");
            l_tradingTimeParams.setBranchCode("123");
            l_tradingTimeParams.setTradingTimeType("01");
            l_tradingTimeParams.setBizDateType("1");
            l_tradingTimeParams.setMarketCode("N1");
            l_tradingTimeParams.setProductCode("0001000");

            l_mfTradedProductParams.setTradedProductId(100106139070605L);
            l_mfTradedProductParams.setProductId(1006169090018L);
            l_mfTradedProductParams.setMarketId(3303);
            l_mfTradedProductParams.setInstitutionCode("0D");
            
            l_mfProductParams.setProductId(1006169090018L);
            l_mfProductParams.setInstitutionCode("0D");
            l_mfProductParams.setProductCode("0001000");
            l_mfProductParams.setProductIssueCode("0");
            l_mfProductParams.setCurrencyCode("T0");
            l_mfProductParams.setFundType(MutualFundTypeEnum.FOREIGN_MMF);
            l_mfProductParams.setSystemHandlingDiv("1");
            l_mfProductParams.setBuyLimitDiv("0");
            l_mfProductParams.setFrgnNewBuyUnitAmt(new Long(12345));
            l_mfProductParams.setFrgnNewBuyMinAmt(new Long(45612));
            l_mfProductParams.setFrgnAddBuyUnitAmt(new Long(78945));
            l_mfProductParams.setFrgnAddBuyMinAmt(new Long(7410));

            TestDBUtility.insertWithDel(l_subAccountParams);
            TestDBUtility.insertWithDel(l_mainAccountParams);
            TestDBUtility.insertWithDel(l_productParams);
            TestDBUtility.insertWithDel(l_marketParams);
            TestDBUtility.insertWithDel(l_branchParams);
            TestDBUtility.insertWithDel(l_institutionParams);
            TestDBUtility.insertWithDel(l_tradedProductParams);
            TestDBUtility.insertWithDel(l_mfTradedProductParams);
            TestDBUtility.insertWithDel(l_tradingTimeParams);
            TestDBUtility.insertWithDel(l_mfProductParams);
            TestDBUtility.insertWithDel(l_marketCalendarParams);

            WEB3GentradeTradingTimeManagementForMock.mockSetTimestamp();
            
            l_request = new WEB3MutualBuyListRequest();
            
            l_request.pageIndex = "1";
            l_request.pageSize = "3";
            l_request.referenceType = "1";
            l_request.mutualFrgnMmfDisplayDiv = "1";
            

            WEB3MutualBuyListServiceImpl l_impl = new WEB3MutualBuyListServiceImpl();
            l_response = (WEB3MutualBuyListResponse)l_impl.execute(l_request);
            
            assertEquals("12345", l_response.buyProductGroups[0].newBuyFrgnUnitAmt);
            assertEquals("45612", l_response.buyProductGroups[0].newBuyFrgnMinAmt);
            assertEquals("78945", l_response.buyProductGroups[0].addBuyFrgnUnitAmt);
            assertEquals("7410", l_response.buyProductGroups[0].addBuyFrgnMinAmt);   
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
     * 
     * (システム取扱区分 = 1 
     * and ((買付制限区分 = 0 and to_char(買付開始日, 'YYYYMMDDHH24MISS') <= 20040706093030 
     * and to_char(買付終了日, 'YYYYMMDD') >= 20040706093030 ) 
     * or (to_char(募集開始日（SONAR）, 'YYYYMMDDHH24MISS') <= ?
     * and to_char(募集終了日（SONAR）, 'YYYYMMDD') >= ?))) 
     * or システム取扱区分 = ？ 
     *
     */
    public void test_execute_C0012()
    {
        final String STR_METHOD_NAME = " test_execute_C0012";
        log.entering(TEST_START + STR_METHOD_NAME);

        WEB3MutualBuyListRequest l_request = null;
        this.deleteAll();
        
        WEB3MutualBuyListResponse l_response = null;
        
        try
        {
            MOCK_MANAGER.setIsMockUsed(true);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getAccountId",
                new Class[] {},new Long(101001010010L));
            
            Date l_datExpect = WEB3DateUtility.getDate("20040710","yyyyMMdd");
            WEB3GentradeTradingTimeManagementForMock.mockGetOrderBizDate(l_datExpect);
            
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            ProductParams l_productParams = TestDBUtility.getProductRow();
            MarketParams l_marketParams = TestDBUtility.getMarketRow();
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            TradedProductParams l_tradedProductParams = TestDBUtility.getTradedProductRow();
            MutualFundTradedProductParams l_mfTradedProductParams =
                TestDBUtility.getMutualFundTradedProductRow();
            TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();

            Calendar ca =  Calendar.getInstance();
            
            ca.set(2004,7-1,6,9,11,23);
            
            Date date = ca.getTime();
            ThreadLocalSystemAttributesRegistry.setAttribute(
                    TradingSystem.SYSTEM_TIMESTAMP_ATTRIBUTE_NAME,
                    new Timestamp(WEB3DateUtility.getDate("20040706093030","yyyyMMddHHmmss").getTime()));
            
            
            MutualFundProductParams l_mfProductParams = TestDBUtility.getMutualFundProductRow();
            //買付制限区分
            l_mfProductParams.setBuyLimitDiv("0");
            //買付開始日
            l_mfProductParams.setBuyStartDate(date);
            //買付終了日
            l_mfProductParams.setBuyEndDate(WEB3DateUtility.getDate("20040707","yyyyMMdd"));
            //募集開始日（SONAR）
            l_mfProductParams.setRecruitStartDateSonar(date);
            //募集終了日（SONAR）
            l_mfProductParams.setRecruitEndDateSonar(WEB3DateUtility.getDate("20010707","yyyyMMdd"));
            //システム取扱区分
            l_mfProductParams.setSystemHandlingDiv("1");
            l_mfProductParams.setFundType(MutualFundTypeEnum.FOREIGN_MMF);
            
            MarketCalendarParams l_marketCalendarParams = new MarketCalendarParams();
            l_marketCalendarParams.setMarketId(3303L);
            l_marketCalendarParams.setTradeDate(CalendarUtils.clearTimeFields(GtlUtils.getSystemTimestamp()));
            l_marketCalendarParams.setHolidayFlag(BooleanEnum.TRUE);
            l_marketCalendarParams.setTradeOpenTime("090000");
            l_marketCalendarParams.setTradeCloseTime("150000");
            l_marketCalendarParams.setCreatedTimestamp(WEB3DateUtility.getDate("20040707","yyyyMMdd"));
            l_marketCalendarParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20040707","yyyyMMdd"));

            l_mainAccountParams.setAccountId(101001010010L);

            l_subAccountParams.setAccountId(101001010010L);
            l_subAccountParams.setSubAccountId(10100101001007L);
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
            
            l_productParams.setProductId(1006169090018L);
            l_productParams.setProductType(ProductTypeEnum.MUTUAL_FUND);

            l_marketParams.setMarketCode("0");

            l_tradedProductParams.setTradedProductId(100106139070605L);
            l_tradedProductParams.setProductId(1006169090018L);
            l_tradedProductParams.setProductType(ProductTypeEnum.MUTUAL_FUND);

            l_tradingTimeParams.setInstitutionCode("0D");
            l_tradingTimeParams.setBranchCode("123");
            l_tradingTimeParams.setTradingTimeType("01");
            l_tradingTimeParams.setBizDateType("1");
            l_tradingTimeParams.setMarketCode("N1");
            l_tradingTimeParams.setProductCode("0001000");

            l_mfTradedProductParams.setTradedProductId(100106139070605L);
            l_mfTradedProductParams.setProductId(1006169090018L);
            l_mfTradedProductParams.setMarketId(3303);
            l_mfTradedProductParams.setInstitutionCode("0D");
            
            l_mfProductParams.setProductId(1006169090018L);
            l_mfProductParams.setInstitutionCode("0D");
            l_mfProductParams.setProductCode("0001000");
            l_mfProductParams.setProductIssueCode("0");
            l_mfProductParams.setCurrencyCode("T0");
            l_mfProductParams.setFundType(MutualFundTypeEnum.FOREIGN_MMF);
            l_mfProductParams.setSystemHandlingDiv("1");
            l_mfProductParams.setBuyLimitDiv("0");
            l_mfProductParams.setFrgnNewBuyUnitAmt(new Long(12345));
            l_mfProductParams.setFrgnNewBuyMinAmt(new Long(45612));
            l_mfProductParams.setFrgnAddBuyUnitAmt(new Long(78945));
            l_mfProductParams.setFrgnAddBuyMinAmt(new Long(7410));

            TestDBUtility.insertWithDel(l_subAccountParams);
            TestDBUtility.insertWithDel(l_mainAccountParams);
            TestDBUtility.insertWithDel(l_productParams);
            TestDBUtility.insertWithDel(l_marketParams);
            TestDBUtility.insertWithDel(l_branchParams);
            TestDBUtility.insertWithDel(l_institutionParams);
            TestDBUtility.insertWithDel(l_tradedProductParams);
            TestDBUtility.insertWithDel(l_mfTradedProductParams);
            TestDBUtility.insertWithDel(l_tradingTimeParams);
            TestDBUtility.insertWithDel(l_mfProductParams);
            TestDBUtility.insertWithDel(l_marketCalendarParams);

            WEB3GentradeTradingTimeManagementForMock.mockSetTimestamp();
            
            l_request = new WEB3MutualBuyListRequest();
            
            l_request.pageIndex = "1";
            l_request.pageSize = "3";
            l_request.referenceType = "1";
            l_request.mutualFrgnMmfDisplayDiv = "1";
            

            WEB3MutualBuyListServiceImpl l_impl = new WEB3MutualBuyListServiceImpl();
            l_response = (WEB3MutualBuyListResponse)l_impl.execute(l_request);
            
            assertEquals("12345", l_response.buyProductGroups[0].newBuyFrgnUnitAmt);
            assertEquals("45612", l_response.buyProductGroups[0].newBuyFrgnMinAmt);
            assertEquals("78945", l_response.buyProductGroups[0].addBuyFrgnUnitAmt);
            assertEquals("7410", l_response.buyProductGroups[0].addBuyFrgnMinAmt);   
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
     * 
     * (システム取扱区分 = 1 
     * and ((買付制限区分 = ? and to_char(買付開始日, 'YYYYMMDDHH24MISS') <= ? 
     * and to_char(買付終了日, 'YYYYMMDD') >= ? ) 
     * or (to_char(募集開始日（SONAR）, 'YYYYMMDDHH24MISS') <= 20040706093030
     * and to_char(募集終了日（SONAR）, 'YYYYMMDD') >= 20040706093030))) 
     * or システム取扱区分 = ？ 
     *
     */
    public void test_execute_C0013()
    {
        final String STR_METHOD_NAME = " test_execute_C0013";
        log.entering(TEST_START + STR_METHOD_NAME);

        WEB3MutualBuyListRequest l_request = null;
        this.deleteAll();
        
        WEB3MutualBuyListResponse l_response = null;
        
        try
        {
            MOCK_MANAGER.setIsMockUsed(true);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getAccountId",
                new Class[] {},new Long(101001010010L));
            
            Date l_datExpect = WEB3DateUtility.getDate("20040710","yyyyMMdd");
            WEB3GentradeTradingTimeManagementForMock.mockGetOrderBizDate(l_datExpect);
            
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            ProductParams l_productParams = TestDBUtility.getProductRow();
            MarketParams l_marketParams = TestDBUtility.getMarketRow();
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            TradedProductParams l_tradedProductParams = TestDBUtility.getTradedProductRow();
            MutualFundTradedProductParams l_mfTradedProductParams =
                TestDBUtility.getMutualFundTradedProductRow();
            TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();

            Calendar ca =  Calendar.getInstance();
            
            ca.set(2004,7-1,6,9,11,23);
            
            Date date = ca.getTime();
            ThreadLocalSystemAttributesRegistry.setAttribute(
                    TradingSystem.SYSTEM_TIMESTAMP_ATTRIBUTE_NAME,
                    new Timestamp(WEB3DateUtility.getDate("20040706093030","yyyyMMddHHmmss").getTime()));
            
            
            MutualFundProductParams l_mfProductParams = TestDBUtility.getMutualFundProductRow();
            //買付制限区分
            l_mfProductParams.setBuyLimitDiv("3");
            //買付開始日
            l_mfProductParams.setBuyStartDate(date);
            //買付終了日
            l_mfProductParams.setBuyEndDate(WEB3DateUtility.getDate("20010707","yyyyMMdd"));
            //募集開始日（SONAR）
            l_mfProductParams.setRecruitStartDateSonar(date);
            //募集終了日（SONAR）
            l_mfProductParams.setRecruitEndDateSonar(WEB3DateUtility.getDate("20040707","yyyyMMdd"));
            //システム取扱区分
            l_mfProductParams.setSystemHandlingDiv("1");
            l_mfProductParams.setFundType(MutualFundTypeEnum.FOREIGN_MMF);
            
            MarketCalendarParams l_marketCalendarParams = new MarketCalendarParams();
            l_marketCalendarParams.setMarketId(3303L);
            l_marketCalendarParams.setTradeDate(CalendarUtils.clearTimeFields(GtlUtils.getSystemTimestamp()));
            l_marketCalendarParams.setHolidayFlag(BooleanEnum.TRUE);
            l_marketCalendarParams.setTradeOpenTime("090000");
            l_marketCalendarParams.setTradeCloseTime("150000");
            l_marketCalendarParams.setCreatedTimestamp(WEB3DateUtility.getDate("20040707","yyyyMMdd"));
            l_marketCalendarParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20040707","yyyyMMdd"));

            l_mainAccountParams.setAccountId(101001010010L);

            l_subAccountParams.setAccountId(101001010010L);
            l_subAccountParams.setSubAccountId(10100101001007L);
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
            
            l_productParams.setProductId(1006169090018L);
            l_productParams.setProductType(ProductTypeEnum.MUTUAL_FUND);

            l_marketParams.setMarketCode("0");

            l_tradedProductParams.setTradedProductId(100106139070605L);
            l_tradedProductParams.setProductId(1006169090018L);
            l_tradedProductParams.setProductType(ProductTypeEnum.MUTUAL_FUND);

            l_tradingTimeParams.setInstitutionCode("0D");
            l_tradingTimeParams.setBranchCode("123");
            l_tradingTimeParams.setTradingTimeType("01");
            l_tradingTimeParams.setBizDateType("1");
            l_tradingTimeParams.setMarketCode("N1");
            l_tradingTimeParams.setProductCode("0001000");

            l_mfTradedProductParams.setTradedProductId(100106139070605L);
            l_mfTradedProductParams.setProductId(1006169090018L);
            l_mfTradedProductParams.setMarketId(3303);
            l_mfTradedProductParams.setInstitutionCode("0D");
            
            l_mfProductParams.setProductId(1006169090018L);
            l_mfProductParams.setInstitutionCode("0D");
            l_mfProductParams.setProductCode("0001000");
            l_mfProductParams.setProductIssueCode("0");
            l_mfProductParams.setCurrencyCode("T0");
            l_mfProductParams.setFundType(MutualFundTypeEnum.FOREIGN_MMF);
            l_mfProductParams.setSystemHandlingDiv("1");
            l_mfProductParams.setBuyLimitDiv("0");
            l_mfProductParams.setFrgnNewBuyUnitAmt(new Long(12345));
            l_mfProductParams.setFrgnNewBuyMinAmt(new Long(45612));
            l_mfProductParams.setFrgnAddBuyUnitAmt(new Long(78945));
            l_mfProductParams.setFrgnAddBuyMinAmt(new Long(7410));

            TestDBUtility.insertWithDel(l_subAccountParams);
            TestDBUtility.insertWithDel(l_mainAccountParams);
            TestDBUtility.insertWithDel(l_productParams);
            TestDBUtility.insertWithDel(l_marketParams);
            TestDBUtility.insertWithDel(l_branchParams);
            TestDBUtility.insertWithDel(l_institutionParams);
            TestDBUtility.insertWithDel(l_tradedProductParams);
            TestDBUtility.insertWithDel(l_mfTradedProductParams);
            TestDBUtility.insertWithDel(l_tradingTimeParams);
            TestDBUtility.insertWithDel(l_mfProductParams);
            TestDBUtility.insertWithDel(l_marketCalendarParams);

            WEB3GentradeTradingTimeManagementForMock.mockSetTimestamp();
            
            l_request = new WEB3MutualBuyListRequest();
            
            l_request.pageIndex = "1";
            l_request.pageSize = "3";
            l_request.referenceType = "1";
            l_request.mutualFrgnMmfDisplayDiv = "1";
            

            WEB3MutualBuyListServiceImpl l_impl = new WEB3MutualBuyListServiceImpl();
            l_response = (WEB3MutualBuyListResponse)l_impl.execute(l_request);
            
            assertEquals("12345", l_response.buyProductGroups[0].newBuyFrgnUnitAmt);
            assertEquals("45612", l_response.buyProductGroups[0].newBuyFrgnMinAmt);
            assertEquals("78945", l_response.buyProductGroups[0].addBuyFrgnUnitAmt);
            assertEquals("7410", l_response.buyProductGroups[0].addBuyFrgnMinAmt);   
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
     * 
     * (システム取扱区分 = 4 
     * and ((買付制限区分 = ? and to_char(買付開始日, 'YYYYMMDDHH24MISS') <= ? 
     * and to_char(買付終了日, 'YYYYMMDD') >= ? ) 
     * or (to_char(募集開始日（SONAR）, 'YYYYMMDDHH24MISS') <= 20040706093030
     * and to_char(募集終了日（SONAR）, 'YYYYMMDD') >= 20040706093030))) 
     * or システム取扱区分 = ？ 
     *
     */
    public void test_execute_C0014()
    {
        final String STR_METHOD_NAME = " test_execute_C0014";
        log.entering(TEST_START + STR_METHOD_NAME);

        WEB3MutualBuyListRequest l_request = null;
        this.deleteAll();
        
        WEB3MutualBuyListResponse l_response = null;
        
        try
        {
            MOCK_MANAGER.setIsMockUsed(true);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getAccountId",
                new Class[] {},new Long(101001010010L));
            
            Date l_datExpect = WEB3DateUtility.getDate("20040710","yyyyMMdd");
            WEB3GentradeTradingTimeManagementForMock.mockGetOrderBizDate(l_datExpect);
            
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            ProductParams l_productParams = TestDBUtility.getProductRow();
            MarketParams l_marketParams = TestDBUtility.getMarketRow();
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            TradedProductParams l_tradedProductParams = TestDBUtility.getTradedProductRow();
            MutualFundTradedProductParams l_mfTradedProductParams =
                TestDBUtility.getMutualFundTradedProductRow();
            TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();

            Calendar ca =  Calendar.getInstance();
            
            ca.set(2004,7-1,6,9,11,23);
            
            Date date = ca.getTime();
            ThreadLocalSystemAttributesRegistry.setAttribute(
                    TradingSystem.SYSTEM_TIMESTAMP_ATTRIBUTE_NAME,
                    new Timestamp(WEB3DateUtility.getDate("20040706093030","yyyyMMddHHmmss").getTime()));
            
            
            MutualFundProductParams l_mfProductParams = TestDBUtility.getMutualFundProductRow();
            //買付制限区分
            l_mfProductParams.setBuyLimitDiv("3");
            //買付開始日
            l_mfProductParams.setBuyStartDate(date);
            //買付終了日
            l_mfProductParams.setBuyEndDate(WEB3DateUtility.getDate("20010707","yyyyMMdd"));
            //募集開始日（SONAR）
            l_mfProductParams.setRecruitStartDateSonar(date);
            //募集終了日（SONAR）
            l_mfProductParams.setRecruitEndDateSonar(WEB3DateUtility.getDate("20040707","yyyyMMdd"));
            //システム取扱区分
            l_mfProductParams.setSystemHandlingDiv("4");
            l_mfProductParams.setFundType(MutualFundTypeEnum.FOREIGN_MMF);
            
            MarketCalendarParams l_marketCalendarParams = new MarketCalendarParams();
            l_marketCalendarParams.setMarketId(3303L);
            l_marketCalendarParams.setTradeDate(CalendarUtils.clearTimeFields(GtlUtils.getSystemTimestamp()));
            l_marketCalendarParams.setHolidayFlag(BooleanEnum.TRUE);
            l_marketCalendarParams.setTradeOpenTime("090000");
            l_marketCalendarParams.setTradeCloseTime("150000");
            l_marketCalendarParams.setCreatedTimestamp(WEB3DateUtility.getDate("20040707","yyyyMMdd"));
            l_marketCalendarParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20040707","yyyyMMdd"));

            l_mainAccountParams.setAccountId(101001010010L);

            l_subAccountParams.setAccountId(101001010010L);
            l_subAccountParams.setSubAccountId(10100101001007L);
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
            
            l_productParams.setProductId(1006169090018L);
            l_productParams.setProductType(ProductTypeEnum.MUTUAL_FUND);

            l_marketParams.setMarketCode("0");

            l_tradedProductParams.setTradedProductId(100106139070605L);
            l_tradedProductParams.setProductId(1006169090018L);
            l_tradedProductParams.setProductType(ProductTypeEnum.MUTUAL_FUND);

            l_tradingTimeParams.setInstitutionCode("0D");
            l_tradingTimeParams.setBranchCode("123");
            l_tradingTimeParams.setTradingTimeType("01");
            l_tradingTimeParams.setBizDateType("1");
            l_tradingTimeParams.setMarketCode("N1");
            l_tradingTimeParams.setProductCode("0001000");

            l_mfTradedProductParams.setTradedProductId(100106139070605L);
            l_mfTradedProductParams.setProductId(1006169090018L);
            l_mfTradedProductParams.setMarketId(3303);
            l_mfTradedProductParams.setInstitutionCode("0D");
            
            l_mfProductParams.setProductId(1006169090018L);
            l_mfProductParams.setInstitutionCode("0D");
            l_mfProductParams.setProductCode("0001000");
            l_mfProductParams.setProductIssueCode("0");
            l_mfProductParams.setCurrencyCode("T0");
            l_mfProductParams.setFundType(MutualFundTypeEnum.FOREIGN_MMF);
            l_mfProductParams.setSystemHandlingDiv("1");
            l_mfProductParams.setBuyLimitDiv("0");
            l_mfProductParams.setFrgnNewBuyUnitAmt(new Long(12345));
            l_mfProductParams.setFrgnNewBuyMinAmt(new Long(45612));
            l_mfProductParams.setFrgnAddBuyUnitAmt(new Long(78945));
            l_mfProductParams.setFrgnAddBuyMinAmt(new Long(7410));

            TestDBUtility.insertWithDel(l_subAccountParams);
            TestDBUtility.insertWithDel(l_mainAccountParams);
            TestDBUtility.insertWithDel(l_productParams);
            TestDBUtility.insertWithDel(l_marketParams);
            TestDBUtility.insertWithDel(l_branchParams);
            TestDBUtility.insertWithDel(l_institutionParams);
            TestDBUtility.insertWithDel(l_tradedProductParams);
            TestDBUtility.insertWithDel(l_mfTradedProductParams);
            TestDBUtility.insertWithDel(l_tradingTimeParams);
            TestDBUtility.insertWithDel(l_mfProductParams);
            TestDBUtility.insertWithDel(l_marketCalendarParams);

            WEB3GentradeTradingTimeManagementForMock.mockSetTimestamp();
            
            l_request = new WEB3MutualBuyListRequest();
            
            l_request.pageIndex = "1";
            l_request.pageSize = "3";
            l_request.referenceType = "1";
            l_request.mutualFrgnMmfDisplayDiv = "1";
            

            WEB3MutualBuyListServiceImpl l_impl = new WEB3MutualBuyListServiceImpl();
            l_response = (WEB3MutualBuyListResponse)l_impl.execute(l_request);
            
            assertEquals("12345", l_response.buyProductGroups[0].newBuyFrgnUnitAmt);
            assertEquals("45612", l_response.buyProductGroups[0].newBuyFrgnMinAmt);
            assertEquals("78945", l_response.buyProductGroups[0].addBuyFrgnUnitAmt);
            assertEquals("7410", l_response.buyProductGroups[0].addBuyFrgnMinAmt);   
        }
        catch (WEB3BusinessLayerException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00398,l_ex.getErrorInfo());
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
     * 
     * (システム取扱区分 = 1 
     * and ((買付制限区分 = 2 and to_char(買付開始日, 'YYYYMMDDHH24MISS') <= ? 
     * and to_char(買付終了日, 'YYYYMMDD') >= ? ) 
     * or (to_char(募集開始日（SONAR）, 'YYYYMMDDHH24MISS') <= ?
     * and to_char(募集終了日（SONAR）, 'YYYYMMDD') >= ?))) 
     * or システム取扱区分 = ？ 
     *
     */
    public void test_execute_C0015()
    {
        final String STR_METHOD_NAME = " test_execute_C0015";
        log.entering(TEST_START + STR_METHOD_NAME);

        WEB3MutualBuyListRequest l_request = null;
        this.deleteAll();
        
        WEB3MutualBuyListResponse l_response = null;
        
        try
        {
            MOCK_MANAGER.setIsMockUsed(true);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getAccountId",
                new Class[] {},new Long(101001010010L));
            
            Date l_datExpect = WEB3DateUtility.getDate("20040710","yyyyMMdd");
            WEB3GentradeTradingTimeManagementForMock.mockGetOrderBizDate(l_datExpect);
            
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            ProductParams l_productParams = TestDBUtility.getProductRow();
            MarketParams l_marketParams = TestDBUtility.getMarketRow();
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            TradedProductParams l_tradedProductParams = TestDBUtility.getTradedProductRow();
            MutualFundTradedProductParams l_mfTradedProductParams =
                TestDBUtility.getMutualFundTradedProductRow();
            TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();

            Calendar ca =  Calendar.getInstance();
            
            ca.set(2004,7-1,6,9,11,23);
            
            Date date = ca.getTime();
            ThreadLocalSystemAttributesRegistry.setAttribute(
                    TradingSystem.SYSTEM_TIMESTAMP_ATTRIBUTE_NAME,
                    new Timestamp(WEB3DateUtility.getDate("20040706093030","yyyyMMddHHmmss").getTime()));
            
            
            MutualFundProductParams l_mfProductParams = TestDBUtility.getMutualFundProductRow();
            //買付制限区分
            l_mfProductParams.setBuyLimitDiv("2");
            //買付開始日
            l_mfProductParams.setBuyStartDate(date);
            //買付終了日
            l_mfProductParams.setBuyEndDate(WEB3DateUtility.getDate("20010707","yyyyMMdd"));
            //募集開始日（SONAR）
            l_mfProductParams.setRecruitStartDateSonar(date);
            //募集終了日（SONAR）
            l_mfProductParams.setRecruitEndDateSonar(WEB3DateUtility.getDate("20010707","yyyyMMdd"));
            //システム取扱区分
            l_mfProductParams.setSystemHandlingDiv("1");
            l_mfProductParams.setFundType(MutualFundTypeEnum.FOREIGN_MMF);
            
            MarketCalendarParams l_marketCalendarParams = new MarketCalendarParams();
            l_marketCalendarParams.setMarketId(3303L);
            l_marketCalendarParams.setTradeDate(CalendarUtils.clearTimeFields(GtlUtils.getSystemTimestamp()));
            l_marketCalendarParams.setHolidayFlag(BooleanEnum.TRUE);
            l_marketCalendarParams.setTradeOpenTime("090000");
            l_marketCalendarParams.setTradeCloseTime("150000");
            l_marketCalendarParams.setCreatedTimestamp(WEB3DateUtility.getDate("20040707","yyyyMMdd"));
            l_marketCalendarParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20040707","yyyyMMdd"));

            l_mainAccountParams.setAccountId(101001010010L);

            l_subAccountParams.setAccountId(101001010010L);
            l_subAccountParams.setSubAccountId(10100101001007L);
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
            
            l_productParams.setProductId(1006169090018L);
            l_productParams.setProductType(ProductTypeEnum.MUTUAL_FUND);

            l_marketParams.setMarketCode("0");

            l_tradedProductParams.setTradedProductId(100106139070605L);
            l_tradedProductParams.setProductId(1006169090018L);
            l_tradedProductParams.setProductType(ProductTypeEnum.MUTUAL_FUND);

            l_tradingTimeParams.setInstitutionCode("0D");
            l_tradingTimeParams.setBranchCode("123");
            l_tradingTimeParams.setTradingTimeType("01");
            l_tradingTimeParams.setBizDateType("1");
            l_tradingTimeParams.setMarketCode("N1");
            l_tradingTimeParams.setProductCode("0001000");

            l_mfTradedProductParams.setTradedProductId(100106139070605L);
            l_mfTradedProductParams.setProductId(1006169090018L);
            l_mfTradedProductParams.setMarketId(3303);
            l_mfTradedProductParams.setInstitutionCode("0D");
            
            l_mfProductParams.setProductId(1006169090018L);
            l_mfProductParams.setInstitutionCode("0D");
            l_mfProductParams.setProductCode("0001000");
            l_mfProductParams.setProductIssueCode("0");
            l_mfProductParams.setCurrencyCode("T0");
            l_mfProductParams.setFundType(MutualFundTypeEnum.FOREIGN_MMF);
            l_mfProductParams.setSystemHandlingDiv("1");
            l_mfProductParams.setBuyLimitDiv("0");
            l_mfProductParams.setFrgnNewBuyUnitAmt(new Long(12345));
            l_mfProductParams.setFrgnNewBuyMinAmt(new Long(45612));
            l_mfProductParams.setFrgnAddBuyUnitAmt(new Long(78945));
            l_mfProductParams.setFrgnAddBuyMinAmt(new Long(7410));

            TestDBUtility.insertWithDel(l_subAccountParams);
            TestDBUtility.insertWithDel(l_mainAccountParams);
            TestDBUtility.insertWithDel(l_productParams);
            TestDBUtility.insertWithDel(l_marketParams);
            TestDBUtility.insertWithDel(l_branchParams);
            TestDBUtility.insertWithDel(l_institutionParams);
            TestDBUtility.insertWithDel(l_tradedProductParams);
            TestDBUtility.insertWithDel(l_mfTradedProductParams);
            TestDBUtility.insertWithDel(l_tradingTimeParams);
            TestDBUtility.insertWithDel(l_mfProductParams);
            TestDBUtility.insertWithDel(l_marketCalendarParams);

            WEB3GentradeTradingTimeManagementForMock.mockSetTimestamp();
            
            l_request = new WEB3MutualBuyListRequest();
            
            l_request.pageIndex = "1";
            l_request.pageSize = "3";
            l_request.referenceType = "1";
            l_request.mutualFrgnMmfDisplayDiv = "1";
            

            WEB3MutualBuyListServiceImpl l_impl = new WEB3MutualBuyListServiceImpl();
            l_response = (WEB3MutualBuyListResponse)l_impl.execute(l_request);
            
            assertEquals("12345", l_response.buyProductGroups[0].newBuyFrgnUnitAmt);
            assertEquals("45612", l_response.buyProductGroups[0].newBuyFrgnMinAmt);
            assertEquals("78945", l_response.buyProductGroups[0].addBuyFrgnUnitAmt);
            assertEquals("7410", l_response.buyProductGroups[0].addBuyFrgnMinAmt);   
        }
        catch (WEB3BusinessLayerException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00398,l_ex.getErrorInfo());
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
     * 
     * (システム取扱区分 = 1 
     * and ((買付制限区分 = 2 and to_char(買付開始日, 'YYYYMMDDHH24MISS') <= ? 
     * and to_char(買付終了日, 'YYYYMMDD') >= ? ) 
     * or (to_char(募集開始日（SONAR）, 'YYYYMMDDHH24MISS') <= ?
     * and to_char(募集終了日（SONAR）, 'YYYYMMDD') >= ?))) 
     * or システム取扱区分 = ？ 
     *
     */
    public void test_execute_C0016()
    {
        final String STR_METHOD_NAME = " test_execute_C0016";
        log.entering(TEST_START + STR_METHOD_NAME);

        WEB3MutualBuyListRequest l_request = null;
        this.deleteAll();
        
        WEB3MutualBuyListResponse l_response = null;
        
        try
        {
            MOCK_MANAGER.setIsMockUsed(true);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getAccountId",
                new Class[] {},new Long(101001010010L));
            
            Date l_datExpect = WEB3DateUtility.getDate("20040710","yyyyMMdd");
            WEB3GentradeTradingTimeManagementForMock.mockGetOrderBizDate(l_datExpect);
            
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            ProductParams l_productParams = TestDBUtility.getProductRow();
            MarketParams l_marketParams = TestDBUtility.getMarketRow();
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            TradedProductParams l_tradedProductParams = TestDBUtility.getTradedProductRow();
            MutualFundTradedProductParams l_mfTradedProductParams =
                TestDBUtility.getMutualFundTradedProductRow();
            TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();

            Calendar ca =  Calendar.getInstance();
            
            ca.set(2004,7-1,6,9,11,23);
            
            Date date = ca.getTime();
            ThreadLocalSystemAttributesRegistry.setAttribute(
                    TradingSystem.SYSTEM_TIMESTAMP_ATTRIBUTE_NAME,
                    new Timestamp(WEB3DateUtility.getDate("20040706093030","yyyyMMddHHmmss").getTime()));
            
            
            MutualFundProductParams l_mfProductParams = TestDBUtility.getMutualFundProductRow();
            //買付制限区分
            l_mfProductParams.setBuyLimitDiv("0");
            //買付開始日
            l_mfProductParams.setBuyStartDate(date);
            //買付終了日
            l_mfProductParams.setBuyEndDate(WEB3DateUtility.getDate("20010707","yyyyMMdd"));
            //募集開始日（SONAR）
            l_mfProductParams.setRecruitStartDateSonar(date);
            //募集終了日（SONAR）
            l_mfProductParams.setRecruitEndDateSonar(WEB3DateUtility.getDate("20010707","yyyyMMdd"));
            //システム取扱区分
            l_mfProductParams.setSystemHandlingDiv("1");
            l_mfProductParams.setFundType(MutualFundTypeEnum.FOREIGN_MMF);
            
            MarketCalendarParams l_marketCalendarParams = new MarketCalendarParams();
            l_marketCalendarParams.setMarketId(3303L);
            l_marketCalendarParams.setTradeDate(CalendarUtils.clearTimeFields(GtlUtils.getSystemTimestamp()));
            l_marketCalendarParams.setHolidayFlag(BooleanEnum.TRUE);
            l_marketCalendarParams.setTradeOpenTime("090000");
            l_marketCalendarParams.setTradeCloseTime("150000");
            l_marketCalendarParams.setCreatedTimestamp(WEB3DateUtility.getDate("20040707","yyyyMMdd"));
            l_marketCalendarParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20040707","yyyyMMdd"));

            l_mainAccountParams.setAccountId(101001010010L);

            l_subAccountParams.setAccountId(101001010010L);
            l_subAccountParams.setSubAccountId(10100101001007L);
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
            
            l_productParams.setProductId(1006169090018L);
            l_productParams.setProductType(ProductTypeEnum.MUTUAL_FUND);

            l_marketParams.setMarketCode("0");

            l_tradedProductParams.setTradedProductId(100106139070605L);
            l_tradedProductParams.setProductId(1006169090018L);
            l_tradedProductParams.setProductType(ProductTypeEnum.MUTUAL_FUND);

            l_tradingTimeParams.setInstitutionCode("0D");
            l_tradingTimeParams.setBranchCode("123");
            l_tradingTimeParams.setTradingTimeType("01");
            l_tradingTimeParams.setBizDateType("1");
            l_tradingTimeParams.setMarketCode("N1");
            l_tradingTimeParams.setProductCode("0001000");

            l_mfTradedProductParams.setTradedProductId(100106139070605L);
            l_mfTradedProductParams.setProductId(1006169090018L);
            l_mfTradedProductParams.setMarketId(3303);
            l_mfTradedProductParams.setInstitutionCode("0D");
            
            l_mfProductParams.setProductId(1006169090018L);
            l_mfProductParams.setInstitutionCode("0D");
            l_mfProductParams.setProductCode("0001000");
            l_mfProductParams.setProductIssueCode("0");
            l_mfProductParams.setCurrencyCode("T0");
            l_mfProductParams.setFundType(MutualFundTypeEnum.FOREIGN_MMF);
            l_mfProductParams.setSystemHandlingDiv("1");
            l_mfProductParams.setBuyLimitDiv("0");
            l_mfProductParams.setFrgnNewBuyUnitAmt(new Long(12345));
            l_mfProductParams.setFrgnNewBuyMinAmt(new Long(45612));
            l_mfProductParams.setFrgnAddBuyUnitAmt(new Long(78945));
            l_mfProductParams.setFrgnAddBuyMinAmt(new Long(7410));

            TestDBUtility.insertWithDel(l_subAccountParams);
            TestDBUtility.insertWithDel(l_mainAccountParams);
            TestDBUtility.insertWithDel(l_productParams);
            TestDBUtility.insertWithDel(l_marketParams);
            TestDBUtility.insertWithDel(l_branchParams);
            TestDBUtility.insertWithDel(l_institutionParams);
            TestDBUtility.insertWithDel(l_tradedProductParams);
            TestDBUtility.insertWithDel(l_mfTradedProductParams);
            TestDBUtility.insertWithDel(l_tradingTimeParams);
            TestDBUtility.insertWithDel(l_mfProductParams);
            TestDBUtility.insertWithDel(l_marketCalendarParams);

            WEB3GentradeTradingTimeManagementForMock.mockSetTimestamp();
            
            l_request = new WEB3MutualBuyListRequest();
            
            l_request.pageIndex = "1";
            l_request.pageSize = "3";
            l_request.referenceType = "1";
            l_request.mutualFrgnMmfDisplayDiv = "1";
            

            WEB3MutualBuyListServiceImpl l_impl = new WEB3MutualBuyListServiceImpl();
            l_response = (WEB3MutualBuyListResponse)l_impl.execute(l_request);
            
            assertEquals("12345", l_response.buyProductGroups[0].newBuyFrgnUnitAmt);
            assertEquals("45612", l_response.buyProductGroups[0].newBuyFrgnMinAmt);
            assertEquals("78945", l_response.buyProductGroups[0].addBuyFrgnUnitAmt);
            assertEquals("7410", l_response.buyProductGroups[0].addBuyFrgnMinAmt);   
        }
        catch (WEB3BusinessLayerException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00398,l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
        log.exiting(TEST_END + STR_METHOD_NAME);      
    }
    
    //モデル 594
    public void test_execute_C0017()
    {
    final String STR_METHOD_NAME = " test_execute_C0017";
    log.entering(TEST_START + STR_METHOD_NAME);

    WEB3MutualBuyListRequest l_request = null;
    this.deleteAll();
    
    WEB3MutualBuyListResponse l_response = null;
    
    try
    {
        MOCK_MANAGER.setIsMockUsed(true);
        
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
            "getAccountId",
            new Class[] {},new Long(101001010010L));
        
        Date l_datExpect = WEB3DateUtility.getDate("20040710","yyyyMMdd");
        WEB3GentradeTradingTimeManagementForMock.mockGetOrderBizDate(l_datExpect);
        
        MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
        SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
        ProductParams l_productParams = TestDBUtility.getProductRow();
        MarketParams l_marketParams = TestDBUtility.getMarketRow();
        BranchParams l_branchParams = TestDBUtility.getBranchRow();
        InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
        TradedProductParams l_tradedProductParams = TestDBUtility.getTradedProductRow();
        MutualFundTradedProductParams l_mfTradedProductParams =
            TestDBUtility.getMutualFundTradedProductRow();
        TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();

        Calendar ca =  Calendar.getInstance();
        
        ca.set(2004,7-1,6,9,11,23);
        
        Date date = ca.getTime();
        ThreadLocalSystemAttributesRegistry.setAttribute(
                TradingSystem.SYSTEM_TIMESTAMP_ATTRIBUTE_NAME,
                new Timestamp(WEB3DateUtility.getDate("20040706093030","yyyyMMddHHmmss").getTime()));
        
        
        MutualFundProductParams l_mfProductParams = TestDBUtility.getMutualFundProductRow();
        //買付制限区分
        l_mfProductParams.setBuyLimitDiv("0");
        //買付開始日
        l_mfProductParams.setBuyStartDate(date);
        //買付終了日
        l_mfProductParams.setBuyEndDate(WEB3DateUtility.getDate("20010707","yyyyMMdd"));
        //募集終了日（SONAR）
        l_mfProductParams.setRecruitEndDateSonar(WEB3DateUtility.getDate("20010707","yyyyMMdd"));
        //システム取扱区分
        l_mfProductParams.setSystemHandlingDiv("1");
        l_mfProductParams.setBuySpecityDiv("0");
        l_mfProductParams.setRecruitSpecityDiv("3");
        l_mfProductParams.setFundType(MutualFundTypeEnum.FOREIGN_MMF);
        
        MarketCalendarParams l_marketCalendarParams = new MarketCalendarParams();
        l_marketCalendarParams.setMarketId(3303L);
        l_marketCalendarParams.setTradeDate(CalendarUtils.clearTimeFields(GtlUtils.getSystemTimestamp()));
        l_marketCalendarParams.setHolidayFlag(BooleanEnum.TRUE);
        l_marketCalendarParams.setTradeOpenTime("090000");
        l_marketCalendarParams.setTradeCloseTime("150000");
        l_marketCalendarParams.setCreatedTimestamp(WEB3DateUtility.getDate("20040707","yyyyMMdd"));
        l_marketCalendarParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20040707","yyyyMMdd"));

        l_mainAccountParams.setAccountId(101001010010L);

        l_subAccountParams.setAccountId(101001010010L);
        l_subAccountParams.setSubAccountId(10100101001007L);
        l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
        
        l_productParams.setProductId(1006169090018L);
        l_productParams.setProductType(ProductTypeEnum.MUTUAL_FUND);

        l_marketParams.setMarketCode("0");

        l_tradedProductParams.setTradedProductId(100106139070605L);
        l_tradedProductParams.setProductId(1006169090018L);
        l_tradedProductParams.setProductType(ProductTypeEnum.MUTUAL_FUND);

        l_tradingTimeParams.setInstitutionCode("0D");
        l_tradingTimeParams.setBranchCode("123");
        l_tradingTimeParams.setTradingTimeType("01");
        l_tradingTimeParams.setBizDateType("1");
        l_tradingTimeParams.setMarketCode("N1");
        l_tradingTimeParams.setProductCode("0001000");

        l_mfTradedProductParams.setTradedProductId(100106139070605L);
        l_mfTradedProductParams.setProductId(1006169090018L);
        l_mfTradedProductParams.setMarketId(3303);
        l_mfTradedProductParams.setInstitutionCode("0D");
        
        l_mfProductParams.setProductId(1006169090018L);
        l_mfProductParams.setInstitutionCode("0D");
        l_mfProductParams.setProductCode("0001000");
        l_mfProductParams.setProductIssueCode("0");
        l_mfProductParams.setCurrencyCode("T0");
        l_mfProductParams.setFundType(MutualFundTypeEnum.FOREIGN_MMF);
        l_mfProductParams.setSystemHandlingDiv("1");
        l_mfProductParams.setBuyLimitDiv("0");
        l_mfProductParams.setFrgnNewBuyUnitAmt(new Long(12345));
        l_mfProductParams.setFrgnNewBuyMinAmt(new Long(45612));
        l_mfProductParams.setFrgnAddBuyUnitAmt(new Long(78945));
        l_mfProductParams.setFrgnAddBuyMinAmt(new Long(7410));

        TestDBUtility.insertWithDel(l_subAccountParams);
        TestDBUtility.insertWithDel(l_mainAccountParams);
        TestDBUtility.insertWithDel(l_productParams);
        TestDBUtility.insertWithDel(l_marketParams);
        TestDBUtility.insertWithDel(l_branchParams);
        TestDBUtility.insertWithDel(l_institutionParams);
        TestDBUtility.insertWithDel(l_tradedProductParams);
        TestDBUtility.insertWithDel(l_mfTradedProductParams);
        TestDBUtility.insertWithDel(l_tradingTimeParams);
        TestDBUtility.insertWithDel(l_mfProductParams);
        TestDBUtility.insertWithDel(l_marketCalendarParams);

        WEB3GentradeTradingTimeManagementForMock.mockSetTimestamp();
        
        l_request = new WEB3MutualBuyListRequest();
        
        l_request.pageIndex = "1";
        l_request.pageSize = "3";
        l_request.referenceType = "1";
        l_request.mutualFrgnMmfDisplayDiv = "1";
        

        WEB3MutualBuyListServiceImpl l_impl = new WEB3MutualBuyListServiceImpl();
        l_response = (WEB3MutualBuyListResponse)l_impl.execute(l_request);

        assertEquals("0", l_response.buyProductGroups[0].buySelectable);   
    }
    catch (WEB3BusinessLayerException l_ex)
    {
        log.error(l_ex.getMessage(), l_ex);
        assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00398,l_ex.getErrorInfo());
    }
    catch (Exception l_ex)
    {
        log.error(l_ex.getMessage(), l_ex);
        fail();
    }
    log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
    log.exiting(TEST_END + STR_METHOD_NAME);      
  }
//  モデル 594
    public void test_execute_C0018()
    {
    final String STR_METHOD_NAME = " test_execute_C0018";
    log.entering(TEST_START + STR_METHOD_NAME);

    WEB3MutualBuyListRequest l_request = null;
    this.deleteAll();
    
    WEB3MutualBuyListResponse l_response = null;
    
    try
    {
        MOCK_MANAGER.setIsMockUsed(true);
        
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
            "getAccountId",
            new Class[] {},new Long(101001010010L));
        
        Date l_datExpect = WEB3DateUtility.getDate("20040710","yyyyMMdd");
        WEB3GentradeTradingTimeManagementForMock.mockGetOrderBizDate(l_datExpect);
        
        MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
        SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
        ProductParams l_productParams = TestDBUtility.getProductRow();
        MarketParams l_marketParams = TestDBUtility.getMarketRow();
        BranchParams l_branchParams = TestDBUtility.getBranchRow();
        InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
        TradedProductParams l_tradedProductParams = TestDBUtility.getTradedProductRow();
        MutualFundTradedProductParams l_mfTradedProductParams =
            TestDBUtility.getMutualFundTradedProductRow();
        TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();

        Calendar ca =  Calendar.getInstance();
        
        ca.set(2004,7-1,6,9,11,23);
        
        Date date = ca.getTime();
        ThreadLocalSystemAttributesRegistry.setAttribute(
                TradingSystem.SYSTEM_TIMESTAMP_ATTRIBUTE_NAME,
                new Timestamp(WEB3DateUtility.getDate("20040706093030","yyyyMMddHHmmss").getTime()));
        
        
        MutualFundProductParams l_mfProductParams = TestDBUtility.getMutualFundProductRow();
        //買付制限区分
        l_mfProductParams.setBuyLimitDiv("0");
        //買付開始日
        l_mfProductParams.setBuyStartDate(date);
        //買付終了日
        l_mfProductParams.setBuyEndDate(WEB3DateUtility.getDate("20010707","yyyyMMdd"));
        //募集終了日（SONAR）
        l_mfProductParams.setRecruitEndDateSonar(WEB3DateUtility.getDate("20010707","yyyyMMdd"));
        l_mfProductParams.setRecruitStartDate(date);
        //システム取扱区分
        l_mfProductParams.setSystemHandlingDiv("1");
        l_mfProductParams.setBuySpecityDiv("0");
        l_mfProductParams.setRecruitSpecityDiv("3");
        l_mfProductParams.setFundType(MutualFundTypeEnum.FOREIGN_MMF);
        
        MarketCalendarParams l_marketCalendarParams = new MarketCalendarParams();
        l_marketCalendarParams.setMarketId(3303L);
        l_marketCalendarParams.setTradeDate(CalendarUtils.clearTimeFields(GtlUtils.getSystemTimestamp()));
        l_marketCalendarParams.setHolidayFlag(BooleanEnum.TRUE);
        l_marketCalendarParams.setTradeOpenTime("090000");
        l_marketCalendarParams.setTradeCloseTime("150000");
        l_marketCalendarParams.setCreatedTimestamp(WEB3DateUtility.getDate("20040707","yyyyMMdd"));
        l_marketCalendarParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20040707","yyyyMMdd"));

        l_mainAccountParams.setAccountId(101001010010L);

        l_subAccountParams.setAccountId(101001010010L);
        l_subAccountParams.setSubAccountId(10100101001007L);
        l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
        
        l_productParams.setProductId(1006169090018L);
        l_productParams.setProductType(ProductTypeEnum.MUTUAL_FUND);

        l_marketParams.setMarketCode("0");

        l_tradedProductParams.setTradedProductId(100106139070605L);
        l_tradedProductParams.setProductId(1006169090018L);
        l_tradedProductParams.setProductType(ProductTypeEnum.MUTUAL_FUND);

        l_tradingTimeParams.setInstitutionCode("0D");
        l_tradingTimeParams.setBranchCode("123");
        l_tradingTimeParams.setTradingTimeType("01");
        l_tradingTimeParams.setBizDateType("1");
        l_tradingTimeParams.setMarketCode("N1");
        l_tradingTimeParams.setProductCode("0001000");

        l_mfTradedProductParams.setTradedProductId(100106139070605L);
        l_mfTradedProductParams.setProductId(1006169090018L);
        l_mfTradedProductParams.setMarketId(3303);
        l_mfTradedProductParams.setInstitutionCode("0D");
        
        l_mfProductParams.setProductId(1006169090018L);
        l_mfProductParams.setInstitutionCode("0D");
        l_mfProductParams.setProductCode("0001000");
        l_mfProductParams.setProductIssueCode("0");
        l_mfProductParams.setCurrencyCode("T0");
        l_mfProductParams.setFundType(MutualFundTypeEnum.FOREIGN_MMF);
        l_mfProductParams.setSystemHandlingDiv("1");
        l_mfProductParams.setBuyLimitDiv("0");
        l_mfProductParams.setFrgnNewBuyUnitAmt(new Long(12345));
        l_mfProductParams.setFrgnNewBuyMinAmt(new Long(45612));
        l_mfProductParams.setFrgnAddBuyUnitAmt(new Long(78945));
        l_mfProductParams.setFrgnAddBuyMinAmt(new Long(7410));

        TestDBUtility.insertWithDel(l_subAccountParams);
        TestDBUtility.insertWithDel(l_mainAccountParams);
        TestDBUtility.insertWithDel(l_productParams);
        TestDBUtility.insertWithDel(l_marketParams);
        TestDBUtility.insertWithDel(l_branchParams);
        TestDBUtility.insertWithDel(l_institutionParams);
        TestDBUtility.insertWithDel(l_tradedProductParams);
        TestDBUtility.insertWithDel(l_mfTradedProductParams);
        TestDBUtility.insertWithDel(l_tradingTimeParams);
        TestDBUtility.insertWithDel(l_mfProductParams);
        TestDBUtility.insertWithDel(l_marketCalendarParams);

        WEB3GentradeTradingTimeManagementForMock.mockSetTimestamp();
        
        l_request = new WEB3MutualBuyListRequest();
        
        l_request.pageIndex = "1";
        l_request.pageSize = "3";
        l_request.referenceType = "1";
        l_request.mutualFrgnMmfDisplayDiv = "1";
        

        WEB3MutualBuyListServiceImpl l_impl = new WEB3MutualBuyListServiceImpl();
        l_response = (WEB3MutualBuyListResponse)l_impl.execute(l_request);

        assertEquals("3", l_response.buyProductGroups[0].buySelectable);   
    }
    catch (WEB3BusinessLayerException l_ex)
    {
        log.error(l_ex.getMessage(), l_ex);
        assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00398,l_ex.getErrorInfo());
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
            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            TestDBUtility.deleteAll(ProductRow.TYPE);
            TestDBUtility.deleteAll(MarketRow.TYPE);
            TestDBUtility.deleteAll(BranchRow.TYPE);
            TestDBUtility.deleteAll(InstitutionRow.TYPE);
            TestDBUtility.deleteAll(TradedProductRow.TYPE);
            TestDBUtility.deleteAll(TradedProductCalendarRow.TYPE);
            TestDBUtility.deleteAll(MutualFundProductRow.TYPE);
            TestDBUtility.deleteAll(MfSubAssetRow.TYPE);
            TestDBUtility.deleteAll(MutualFundTradedProductRow.TYPE);
            TestDBUtility.deleteAll(TradingTimeRow.TYPE);
            TestDBUtility.deleteAll(FrgnMmfExchangeRateRow.TYPE);
            TestDBUtility.deleteAll(ExchangeRateRow.TYPE);
            TestDBUtility.deleteAll(MarketCalendarRow.TYPE);
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
