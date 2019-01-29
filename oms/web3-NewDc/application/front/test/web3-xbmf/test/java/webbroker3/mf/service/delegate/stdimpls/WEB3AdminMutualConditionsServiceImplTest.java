head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.02.09.46;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3AdminMutualConditionsServiceImplTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.mf.service.delegate.stdimpls;

import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Institution;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.InstitutionParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MarketParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.ProductParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.TradedProductParams;
import com.fitechlabs.xtrade.plugin.tc.xbmf.MutualFundTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.xbmf.data.MutualFundProductDao;
import com.fitechlabs.xtrade.plugin.tc.xbmf.data.MutualFundProductParams;
import com.fitechlabs.xtrade.plugin.tc.xbmf.data.MutualFundProductRow;
import com.fitechlabs.xtrade.plugin.tc.xbmf.data.MutualFundTradedProductParams;

import test.util.TestDBUtility;
import webbroker3.common.WEB3BaseException;
import webbroker3.gentrade.WEB3Administrator;
import webbroker3.gentrade.WEB3AdministratorForMock;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagementForMock;
import webbroker3.gentrade.data.AdministratorParams;
import webbroker3.gentrade.data.TradingTimeParams;
import webbroker3.mf.WEB3MutualFundProductCondSpec;
import webbroker3.mf.WEB3MutualFundTradedProduct;
import webbroker3.mf.WEB3MutualFundTradingTimeManagementForMock;
import webbroker3.mf.data.MutualFund2ndProductSonarParams;
import webbroker3.mf.message.WEB3AdminMutualConditionsCompleteRequest;
import webbroker3.mf.message.WEB3AdminMutualConditionsCompleteResponse;
import webbroker3.mf.message.WEB3AdminMutualConditionsConfirmRequest;
import webbroker3.mf.message.WEB3AdminMutualConditionsConfirmResponse;
import webbroker3.mf.message.WEB3AdminMutualConditionsInputRequest;
import webbroker3.mf.message.WEB3AdminMutualConditionsInputResponse;
import webbroker3.mf.message.WEB3MutualProductConditionsCommonInfo;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

public class WEB3AdminMutualConditionsServiceImplTest extends TestBaseForMock
{
    /**
     * ログ出力ユーティリティ。
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminMutualConditionsServiceImplTest.class);
    
    WEB3AdminMutualConditionsServiceImpl l_impl = new WEB3AdminMutualConditionsServiceImpl();
    
    public WEB3AdminMutualConditionsServiceImplTest(String arg0)
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
    
    public class WEB3AdminMutualConditionsCompleteRequestForMock extends WEB3AdminMutualConditionsCompleteRequest
    {
        public void validate()
        {
            final String STR_METHOD_NAME = "validate() ";
            log.entering(STR_METHOD_NAME);
            log.exiting(STR_METHOD_NAME);           
        }
    }
    
    public class WEB3AdminMutualConditionsConfirmRequestForMock extends WEB3AdminMutualConditionsConfirmRequest
    {
        public void validate()
        {
            final String STR_METHOD_NAME = "validate() ";
            log.entering(STR_METHOD_NAME);
            log.exiting(STR_METHOD_NAME);           
        }
    }
    
    public void testInputProductConditionsRegist01()
    {
        final String STR_METHOD_NAME = "testInputProductConditionsRegist01()"; 
        log.entering(STR_METHOD_NAME); 
        
        MOCK_MANAGER.setIsMockUsed(true);
        WEB3AdminMutualConditionsInputRequest l_request = new WEB3AdminMutualConditionsInputRequest();
        l_request.id = "3304148080000";
        
        try
        {
            
            //admin
            TestDBUtility.deleteAll(AdministratorParams.TYPE);
            AdministratorParams l_adminRow = TestDBUtility.getAdministratorRow();
            TestDBUtility.insertWithDel(l_adminRow);
            
            WEB3Administrator l_expectAdministrator = new WEB3Administrator(l_adminRow);
            
            WEB3AdministratorForMock.mockGetInstanceFromLoginInfo(l_expectAdministrator);
            
            WEB3AdministratorForMock.mockValidateAuthority(
                l_expectAdministrator, 
                "C0201", 
                true, 
                true);
            
            WEB3MutualFundTradingTimeManagementForMock.mockGetMutualOrderBizDate(GtlUtils.getSystemTimestamp());

            //InstitutionParams
            TestDBUtility.deleteAll(InstitutionParams.TYPE);
            InstitutionParams l_insParams = TestDBUtility.getInstitutionRow();
            TestDBUtility.insertWithDel(l_insParams);
            
            //MarketParams
            TestDBUtility.deleteAll(MarketParams.TYPE);
            MarketParams l_mktParams = TestDBUtility.getMarketRow();
            TestDBUtility.insertWithDel(l_mktParams);

            //ProductParams
            TestDBUtility.deleteAll(ProductParams.TYPE);
            ProductParams l_productParams = TestDBUtility.getProductRow();
            TestDBUtility.insertWithDel(l_productParams);
            
            //TradedProductParams
            TestDBUtility.deleteAll(TradedProductParams.TYPE);
            TradedProductParams l_tradedProductParams = TestDBUtility.getTradedProductRow();
            l_tradedProductParams.setTradedProductId(400003000100000L);
            TestDBUtility.insertWithDel(l_tradedProductParams);
            
            //TradingTimeParams
            TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams.setTradingTimeType("06");
            l_tradingTimeParams.setProductCode("0001000");
            l_tradingTimeParams.setBizDateType("1");
            l_tradingTimeParams.setSubmitMarketTrigger("0");
            l_tradingTimeParams.setEnableOrder("1");
            l_tradingTimeParams.setBizdateCalcParameter("1");
            TestDBUtility.insertWithDel(l_tradingTimeParams);
            
            //TradingTimeParams3
            TradingTimeParams l_tradingTimeParams3 = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams3.setTradingTimeType("06");
            l_tradingTimeParams3.setProductCode("0001000");
            l_tradingTimeParams3.setBizDateType("2");
            l_tradingTimeParams3.setSubmitMarketTrigger("0");
            l_tradingTimeParams3.setEnableOrder("1");
            l_tradingTimeParams3.setBizdateCalcParameter("1");
            TestDBUtility.insertWithDel(l_tradingTimeParams3);
            
            //TradingTimeParams2
            TradingTimeParams l_tradingTimeParams2 = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams2.setInstitutionCode("0D");
            l_tradingTimeParams2.setBranchCode("123");
            l_tradingTimeParams2.setMarketCode("N1");
            l_tradingTimeParams2.setTradingTimeType("01");
            l_tradingTimeParams2.setProductCode("0");
            l_tradingTimeParams2.setBizDateType("1");
            TestDBUtility.insertWithDel(l_tradingTimeParams2);
            
            //MutualFundProductParams
            TestDBUtility.deleteAll(MutualFundProductParams.TYPE);
            MutualFundProductParams l_mfProductParams = TestDBUtility.getMutualFundProductRow();
            l_mfProductParams.setProductId(3304148080000L);
            l_mfProductParams.setInstitutionCode("0D");
            l_mfProductParams.setProductCode("0001000");
            l_mfProductParams.setProductIssueCode("0");
            l_mfProductParams.setFundType(MutualFundTypeEnum.FOREIGN_MMF);
            l_mfProductParams.setCurrencyCode("0");
            TestDBUtility.insertWithDel(l_mfProductParams);
            
            //MutualFundTradedProductParams
            TestDBUtility.deleteAll(MutualFundTradedProductParams.TYPE);
            MutualFundTradedProductParams l_mfTradedProductParams = TestDBUtility.getMutualFundTradedProductRow();
            l_mfTradedProductParams.setInstitutionCode("0D");
            l_mfTradedProductParams.setTradedProductId(400003000100000L);
            l_mfTradedProductParams.setValidForBizDate("20070408");
            TestDBUtility.insertWithDel(l_mfTradedProductParams);
            
            //MutualFund2ndProductSonarParams
            TestDBUtility.deleteAll(MutualFund2ndProductSonarParams.TYPE);
            MutualFund2ndProductSonarParams l_2ndParams = 
                TestDBUtility.getMutualFund2ndProductSonarParams();
            TestDBUtility.insertWithDel(l_2ndParams);
            
            WEB3MutualFundTradedProduct l_web3TradeProduct = 
                new WEB3MutualFundTradedProduct(l_mfTradedProductParams);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.mf.WEB3MutualFundProductManager",
                "getMutualFundTradedProduct",
                new Class[] {Institution.class, String.class},
                l_web3TradeProduct);
            
            WEB3AdminMutualConditionsInputResponse l_response = 
                l_impl.inputProductConditionsRegist(l_request);
            
            assertEquals(true, l_response.frgnMmfFlag);
        }
        catch(WEB3BaseException l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            log.error(STR_METHOD_NAME, l_ex);
            l_ex.printStackTrace();
            fail();
        }
        catch(Exception l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            log.error(STR_METHOD_NAME, l_ex);
            l_ex.printStackTrace();
            fail();
        }
    }

    public void testInputProductConditionsRegist02()
    {
        final String STR_METHOD_NAME = "testInputProductConditionsRegist02()"; 
        log.entering(STR_METHOD_NAME); 
        
        MOCK_MANAGER.setIsMockUsed(true);
        WEB3AdminMutualConditionsInputRequest l_request = new WEB3AdminMutualConditionsInputRequest();
        l_request.id = "3304148080000";
        
        try
        {
            
            //admin
            TestDBUtility.deleteAll(AdministratorParams.TYPE);
            AdministratorParams l_adminRow = TestDBUtility.getAdministratorRow();
            TestDBUtility.insertWithDel(l_adminRow);
            
            WEB3Administrator l_expectAdministrator = new WEB3Administrator(l_adminRow);
            
            WEB3AdministratorForMock.mockGetInstanceFromLoginInfo(l_expectAdministrator);
            
            WEB3AdministratorForMock.mockValidateAuthority(
                l_expectAdministrator, 
                "C0201", 
                true, 
                true);
            
            WEB3MutualFundTradingTimeManagementForMock.mockGetMutualOrderBizDate(GtlUtils.getSystemTimestamp());

            //InstitutionParams
            TestDBUtility.deleteAll(InstitutionParams.TYPE);
            InstitutionParams l_insParams = TestDBUtility.getInstitutionRow();
            TestDBUtility.insertWithDel(l_insParams);
            
            //MarketParams
            TestDBUtility.deleteAll(MarketParams.TYPE);
            MarketParams l_mktParams = TestDBUtility.getMarketRow();
            TestDBUtility.insertWithDel(l_mktParams);

            //ProductParams
            TestDBUtility.deleteAll(ProductParams.TYPE);
            ProductParams l_productParams = TestDBUtility.getProductRow();
            TestDBUtility.insertWithDel(l_productParams);
            
            //TradedProductParams
            TestDBUtility.deleteAll(TradedProductParams.TYPE);
            TradedProductParams l_tradedProductParams = TestDBUtility.getTradedProductRow();
            l_tradedProductParams.setTradedProductId(400003000100000L);
            TestDBUtility.insertWithDel(l_tradedProductParams);
            
            //TradingTimeParams
            TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams.setTradingTimeType("06");
            l_tradingTimeParams.setProductCode("0001000");
            l_tradingTimeParams.setBizDateType("1");
            l_tradingTimeParams.setSubmitMarketTrigger("0");
            l_tradingTimeParams.setEnableOrder("1");
            l_tradingTimeParams.setBizdateCalcParameter("1");
            TestDBUtility.insertWithDel(l_tradingTimeParams);
            
            //TradingTimeParams3
            TradingTimeParams l_tradingTimeParams3 = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams3.setTradingTimeType("06");
            l_tradingTimeParams3.setProductCode("0001000");
            l_tradingTimeParams3.setBizDateType("2");
            l_tradingTimeParams3.setSubmitMarketTrigger("0");
            l_tradingTimeParams3.setEnableOrder("1");
            l_tradingTimeParams3.setBizdateCalcParameter("1");
            TestDBUtility.insertWithDel(l_tradingTimeParams3);
            
            //TradingTimeParams2
            TradingTimeParams l_tradingTimeParams2 = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams2.setInstitutionCode("0D");
            l_tradingTimeParams2.setBranchCode("123");
            l_tradingTimeParams2.setMarketCode("N1");
            l_tradingTimeParams2.setTradingTimeType("01");
            l_tradingTimeParams2.setProductCode("0");
            l_tradingTimeParams2.setBizDateType("1");
            TestDBUtility.insertWithDel(l_tradingTimeParams2);
            
            //MutualFundProductParams
            TestDBUtility.deleteAll(MutualFundProductParams.TYPE);
            MutualFundProductParams l_mfProductParams = TestDBUtility.getMutualFundProductRow();
            l_mfProductParams.setProductId(3304148080000L);
            l_mfProductParams.setInstitutionCode("0D");
            l_mfProductParams.setProductCode("0001000");
            l_mfProductParams.setProductIssueCode("0");
            l_mfProductParams.setFundType(MutualFundTypeEnum.FOREIGN_MMF);
            l_mfProductParams.setCurrencyCode("0");
            TestDBUtility.insertWithDel(l_mfProductParams);
            
            //MutualFundTradedProductParams
            TestDBUtility.deleteAll(MutualFundTradedProductParams.TYPE);
            MutualFundTradedProductParams l_mfTradedProductParams = TestDBUtility.getMutualFundTradedProductRow();
            l_mfTradedProductParams.setInstitutionCode("0D");
            l_mfTradedProductParams.setTradedProductId(400003000100000L);
            l_mfTradedProductParams.setValidForBizDate("20070408");
            TestDBUtility.insertWithDel(l_mfTradedProductParams);
            
            //MutualFund2ndProductSonarParams
            TestDBUtility.deleteAll(MutualFund2ndProductSonarParams.TYPE);
            MutualFund2ndProductSonarParams l_2ndParams = 
                TestDBUtility.getMutualFund2ndProductSonarParams();
            TestDBUtility.insertWithDel(l_2ndParams);
            
            WEB3MutualFundTradedProduct l_web3TradeProduct = 
                new WEB3MutualFundTradedProduct(l_mfTradedProductParams);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.mf.WEB3MutualFundProductManager",
                "getMutualFundTradedProduct",
                new Class[] {Institution.class, String.class},
                l_web3TradeProduct);
            
            WEB3AdminMutualConditionsInputResponse l_response = 
                l_impl.inputProductConditionsRegist(l_request);
            
            assertEquals("0", l_response.currencyCode);
        }
        catch(WEB3BaseException l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            log.error(STR_METHOD_NAME, l_ex);
            l_ex.printStackTrace();
            fail();
        }
        catch(Exception l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            log.error(STR_METHOD_NAME, l_ex);
            l_ex.printStackTrace();
            fail();
        }
    }

    public void testSubmitProductConditionsRegist01()
    {
        final String STR_METHOD_NAME = "testSubmitProductConditionsRegist01()"; 
        log.entering(STR_METHOD_NAME); 
        
        MOCK_MANAGER.setIsMockUsed(true);
        WEB3AdminMutualConditionsCompleteRequestForMock l_request = new WEB3AdminMutualConditionsCompleteRequestForMock();
        l_request.password = "pass";
        
        WEB3MutualProductConditionsCommonInfo l_info = new WEB3MutualProductConditionsCommonInfo();
        l_info.mutualProductCode = "0001000";
        l_info.operationDate = GtlUtils.getSystemTimestamp();
        l_info.buyStartDate = GtlUtils.getSystemTimestamp();
        l_info.buyEndDate = GtlUtils.getSystemTimestamp();
        l_info.sellSwitchingStartDate = GtlUtils.getSystemTimestamp();
        l_info.sellSwitchingEndDate = GtlUtils.getSystemTimestamp();
        l_info.buyClaimStartDate = GtlUtils.getSystemTimestamp();
        l_info.buyClaimEndDate = GtlUtils.getSystemTimestamp();
        l_info.applyAbleStartDate = GtlUtils.getSystemTimestamp();
        l_info.applyAbleEndDate = GtlUtils.getSystemTimestamp();
        l_info.buyClaimEndDate = GtlUtils.getSystemTimestamp();
        l_info.buySelectable = "1";
        
        l_request.mutualProductInfo = l_info;
        try
        {
            
            //admin
            TestDBUtility.deleteAll(AdministratorParams.TYPE);
            AdministratorParams l_adminRow = TestDBUtility.getAdministratorRow();
            TestDBUtility.insertWithDel(l_adminRow);
            
            WEB3Administrator l_expectAdministrator = new WEB3Administrator(l_adminRow);
            
            WEB3AdministratorForMock.mockGetInstanceFromLoginInfo(l_expectAdministrator);
            
            WEB3AdministratorForMock.mockValidateAuthority(
                l_expectAdministrator, 
                "C0201", 
                true, 
                true);
            
            WEB3AdministratorForMock.mockValidateTradingPassword(l_request.password, true);
            
            WEB3MutualFundTradingTimeManagementForMock.mockGetMutualOrderBizDate(GtlUtils.getSystemTimestamp());

            //InstitutionParams
            TestDBUtility.deleteAll(InstitutionParams.TYPE);
            InstitutionParams l_insParams = TestDBUtility.getInstitutionRow();
            TestDBUtility.insertWithDel(l_insParams);
            
            //MarketParams
            TestDBUtility.deleteAll(MarketParams.TYPE);
            MarketParams l_mktParams = TestDBUtility.getMarketRow();
            TestDBUtility.insertWithDel(l_mktParams);

            //ProductParams
            TestDBUtility.deleteAll(ProductParams.TYPE);
            ProductParams l_productParams = TestDBUtility.getProductRow();
            TestDBUtility.insertWithDel(l_productParams);
            
            //TradedProductParams
            TestDBUtility.deleteAll(TradedProductParams.TYPE);
            TradedProductParams l_tradedProductParams = TestDBUtility.getTradedProductRow();
            l_tradedProductParams.setTradedProductId(400003000100000L);
            TestDBUtility.insertWithDel(l_tradedProductParams);
            
            //TradingTimeParams
            TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams.setTradingTimeType("06");
            l_tradingTimeParams.setProductCode("0001000");
            l_tradingTimeParams.setBizDateType("1");
            l_tradingTimeParams.setSubmitMarketTrigger("0");
            l_tradingTimeParams.setEnableOrder("1");
            l_tradingTimeParams.setBizdateCalcParameter("1");
            TestDBUtility.insertWithDel(l_tradingTimeParams);
            
            //TradingTimeParams3
            TradingTimeParams l_tradingTimeParams3 = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams3.setTradingTimeType("06");
            l_tradingTimeParams3.setProductCode("0001000");
            l_tradingTimeParams3.setBizDateType("2");
            l_tradingTimeParams3.setSubmitMarketTrigger("0");
            l_tradingTimeParams3.setEnableOrder("1");
            l_tradingTimeParams3.setBizdateCalcParameter("1");
            TestDBUtility.insertWithDel(l_tradingTimeParams3);
            
            //TradingTimeParams2
            TradingTimeParams l_tradingTimeParams2 = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams2.setInstitutionCode("0D");
            l_tradingTimeParams2.setBranchCode("123");
            l_tradingTimeParams2.setMarketCode("N1");
            l_tradingTimeParams2.setTradingTimeType("01");
            l_tradingTimeParams2.setProductCode("0");
            l_tradingTimeParams2.setBizDateType("1");
            TestDBUtility.insertWithDel(l_tradingTimeParams2);
            
            //MutualFundProductParams
            TestDBUtility.deleteAll(MutualFundProductParams.TYPE);
            MutualFundProductParams l_mfProductParams = TestDBUtility.getMutualFundProductRow();
            l_mfProductParams.setProductId(3304148080000L);
            l_mfProductParams.setInstitutionCode("0D");
            l_mfProductParams.setProductCode("0001000");
            l_mfProductParams.setProductIssueCode("0");
            l_mfProductParams.setFundType(MutualFundTypeEnum.FOREIGN_MMF);
            l_mfProductParams.setCurrencyCode("0");
            TestDBUtility.insertWithDel(l_mfProductParams);
            
            //MutualFundTradedProductParams
            TestDBUtility.deleteAll(MutualFundTradedProductParams.TYPE);
            MutualFundTradedProductParams l_mfTradedProductParams = TestDBUtility.getMutualFundTradedProductRow();
            l_mfTradedProductParams.setInstitutionCode("0D");
            l_mfTradedProductParams.setTradedProductId(400003000100000L);
            l_mfTradedProductParams.setValidForBizDate(
                WEB3DateUtility.formatDate(
                    WEB3DateUtility.addDay(
                        GtlUtils.getSystemTimestamp(), 1), "yyyyMMdd"));
            TestDBUtility.insertWithDel(l_mfTradedProductParams);
            
            WEB3GentradeTradingTimeManagementForMock.mockGetOrderBizDate(WEB3DateUtility.addDay(
                GtlUtils.getSystemTimestamp(), 1));
            
            //MutualFund2ndProductSonarParams
            TestDBUtility.deleteAll(MutualFund2ndProductSonarParams.TYPE);
            MutualFund2ndProductSonarParams l_2ndParams = 
                TestDBUtility.getMutualFund2ndProductSonarParams();
            TestDBUtility.insertWithDel(l_2ndParams);
            
            WEB3MutualFundTradedProduct l_web3TradeProduct = 
                new WEB3MutualFundTradedProduct(l_mfTradedProductParams);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.mf.WEB3MutualFundProductManager",
                "validateProductCond",
                new Class[] {Institution.class, WEB3MutualFundProductCondSpec.class},
                null);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "webbroker3.mf.WEB3MutualFundProductManager",
            "getMutualFundTradedProduct",
            new Class[] {Institution.class, String.class},
            l_web3TradeProduct);
            
            WEB3AdminMutualConditionsCompleteResponse l_response = 
                l_impl.submitProductConditionsRegist(l_request);
            
                
            MutualFundProductRow l_mfProductRow = MutualFundProductDao.findRowByPk(3304148080000L);
            
            log.debug("OnlineUpdatedTimestamp === " + l_mfProductRow.getOnlineUpdatedTimestamp());
            log.debug("LastUpdater === " + l_mfProductRow.getLastUpdater());
            
            assertEquals(
                TestDBUtility.getAdministratorRow().getAdministratorCode(), 
                l_mfProductRow.getLastUpdater());

        }
        catch(WEB3BaseException l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            log.error(STR_METHOD_NAME, l_ex);
            l_ex.printStackTrace();
            fail();
        }
        catch(Exception l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            log.error(STR_METHOD_NAME, l_ex);
            l_ex.printStackTrace();
            fail();
        }
    }

    public void testValidateProductConditionsRegist01()
    {
        final String STR_METHOD_NAME = "testValidateProductConditionsRegist01()"; 
        log.entering(STR_METHOD_NAME); 
        
        MOCK_MANAGER.setIsMockUsed(true);
        WEB3AdminMutualConditionsConfirmRequestForMock l_request = new WEB3AdminMutualConditionsConfirmRequestForMock();
        
        WEB3MutualProductConditionsCommonInfo l_info = new WEB3MutualProductConditionsCommonInfo();
        l_info.mutualProductCode = "0001000";
        l_info.operationDate = GtlUtils.getSystemTimestamp();
        l_info.buyStartDate = GtlUtils.getSystemTimestamp();
        l_info.buyEndDate = GtlUtils.getSystemTimestamp();
        l_info.sellSwitchingStartDate = GtlUtils.getSystemTimestamp();
        l_info.sellSwitchingEndDate = GtlUtils.getSystemTimestamp();
        l_info.buyClaimStartDate = GtlUtils.getSystemTimestamp();
        l_info.buyClaimEndDate = GtlUtils.getSystemTimestamp();
        l_info.applyAbleStartDate = GtlUtils.getSystemTimestamp();
        l_info.applyAbleEndDate = GtlUtils.getSystemTimestamp();
        l_info.buyClaimEndDate = GtlUtils.getSystemTimestamp();
        l_info.buySelectable = "1";
        
        l_request.mutualProductInfo = l_info;
        try
        {
            
            //admin
            TestDBUtility.deleteAll(AdministratorParams.TYPE);
            AdministratorParams l_adminRow = TestDBUtility.getAdministratorRow();
            TestDBUtility.insertWithDel(l_adminRow);
            
            WEB3Administrator l_expectAdministrator = new WEB3Administrator(l_adminRow);
            
            WEB3AdministratorForMock.mockGetInstanceFromLoginInfo(l_expectAdministrator);
            
            WEB3AdministratorForMock.mockValidateAuthority(
                l_expectAdministrator, 
                "C0201", 
                true, 
                true);
            
            WEB3MutualFundTradingTimeManagementForMock.mockGetMutualOrderBizDate(GtlUtils.getSystemTimestamp());

            //InstitutionParams
            TestDBUtility.deleteAll(InstitutionParams.TYPE);
            InstitutionParams l_insParams = TestDBUtility.getInstitutionRow();
            TestDBUtility.insertWithDel(l_insParams);
            
            //MarketParams
            TestDBUtility.deleteAll(MarketParams.TYPE);
            MarketParams l_mktParams = TestDBUtility.getMarketRow();
            TestDBUtility.insertWithDel(l_mktParams);

            //ProductParams
            TestDBUtility.deleteAll(ProductParams.TYPE);
            ProductParams l_productParams = TestDBUtility.getProductRow();
            TestDBUtility.insertWithDel(l_productParams);
            
            //TradedProductParams
            TestDBUtility.deleteAll(TradedProductParams.TYPE);
            TradedProductParams l_tradedProductParams = TestDBUtility.getTradedProductRow();
            l_tradedProductParams.setTradedProductId(400003000100000L);
            TestDBUtility.insertWithDel(l_tradedProductParams);
            
            //TradingTimeParams
            TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams.setTradingTimeType("06");
            l_tradingTimeParams.setProductCode("0001000");
            l_tradingTimeParams.setBizDateType("1");
            l_tradingTimeParams.setSubmitMarketTrigger("0");
            l_tradingTimeParams.setEnableOrder("1");
            l_tradingTimeParams.setBizdateCalcParameter("1");
            TestDBUtility.insertWithDel(l_tradingTimeParams);
            
            //TradingTimeParams3
            TradingTimeParams l_tradingTimeParams3 = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams3.setTradingTimeType("06");
            l_tradingTimeParams3.setProductCode("0001000");
            l_tradingTimeParams3.setBizDateType("2");
            l_tradingTimeParams3.setSubmitMarketTrigger("0");
            l_tradingTimeParams3.setEnableOrder("1");
            l_tradingTimeParams3.setBizdateCalcParameter("1");
            TestDBUtility.insertWithDel(l_tradingTimeParams3);
            
            //TradingTimeParams2
            TradingTimeParams l_tradingTimeParams2 = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams2.setInstitutionCode("0D");
            l_tradingTimeParams2.setBranchCode("123");
            l_tradingTimeParams2.setMarketCode("N1");
            l_tradingTimeParams2.setTradingTimeType("01");
            l_tradingTimeParams2.setProductCode("0");
            l_tradingTimeParams2.setBizDateType("1");
            TestDBUtility.insertWithDel(l_tradingTimeParams2);
            
            //MutualFundProductParams
            TestDBUtility.deleteAll(MutualFundProductParams.TYPE);
            MutualFundProductParams l_mfProductParams = TestDBUtility.getMutualFundProductRow();
            l_mfProductParams.setProductId(3304148080000L);
            l_mfProductParams.setInstitutionCode("0D");
            l_mfProductParams.setProductCode("0001000");
            l_mfProductParams.setProductIssueCode("0");
            l_mfProductParams.setFundType(MutualFundTypeEnum.FOREIGN_MMF);
            l_mfProductParams.setCurrencyCode("0");
            TestDBUtility.insertWithDel(l_mfProductParams);
            
            //MutualFundTradedProductParams
            TestDBUtility.deleteAll(MutualFundTradedProductParams.TYPE);
            MutualFundTradedProductParams l_mfTradedProductParams = TestDBUtility.getMutualFundTradedProductRow();
            l_mfTradedProductParams.setInstitutionCode("0D");
            l_mfTradedProductParams.setTradedProductId(400003000100000L);
            l_mfTradedProductParams.setValidForBizDate(
                WEB3DateUtility.formatDate(
                    WEB3DateUtility.addDay(
                        GtlUtils.getSystemTimestamp(), 1), "yyyyMMdd"));
            
            TestDBUtility.insertWithDel(l_mfTradedProductParams);
            WEB3GentradeTradingTimeManagementForMock.mockGetOrderBizDate(WEB3DateUtility.addDay(
                    GtlUtils.getSystemTimestamp(), 1));
            //MutualFund2ndProductSonarParams
            TestDBUtility.deleteAll(MutualFund2ndProductSonarParams.TYPE);
            MutualFund2ndProductSonarParams l_2ndParams = 
                TestDBUtility.getMutualFund2ndProductSonarParams();
            TestDBUtility.insertWithDel(l_2ndParams);
            
            WEB3MutualFundTradedProduct l_web3TradeProduct = 
                new WEB3MutualFundTradedProduct(l_mfTradedProductParams);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.mf.WEB3MutualFundProductManager",
                "validateProductCond",
                new Class[] {Institution.class, WEB3MutualFundProductCondSpec.class},
                null);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "webbroker3.mf.WEB3MutualFundProductManager",
            "getMutualFundTradedProduct",
            new Class[] {Institution.class, String.class},
            l_web3TradeProduct);
            
            WEB3AdminMutualConditionsConfirmResponse l_response = 
                l_impl.validateProductConditionsRegist(l_request);
        }
        catch(WEB3BaseException l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            log.error(STR_METHOD_NAME, l_ex);
            l_ex.printStackTrace();
            fail();
        }
        catch(Exception l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            log.error(STR_METHOD_NAME, l_ex);
            l_ex.printStackTrace();
            fail();
        }
    }
    
    /**
     * 募集手数料区分＝リクエストデータ.銘柄情報.募集手数料区分
     * 
     * リクエストデータ.銘柄情報.募集手数料区分 != nullの場合
     * 投信銘柄マスタテーブルの更新する
     * 
     * リクエストデータ.銘柄情報.募集手数料区分 = "1"
     */
    public void testSubmitProductConditionsRegist02()
    {
        final String STR_METHOD_NAME = "testSubmitProductConditionsRegist02()"; 
        log.entering(STR_METHOD_NAME); 
        
        MOCK_MANAGER.setIsMockUsed(true);
        WEB3AdminMutualConditionsCompleteRequestForMock l_request = new WEB3AdminMutualConditionsCompleteRequestForMock();
        l_request.password = "pass";
        
        WEB3MutualProductConditionsCommonInfo l_info = new WEB3MutualProductConditionsCommonInfo();
        l_info.mutualProductCode = "0001000";
        l_info.operationDate = GtlUtils.getSystemTimestamp();        
        l_info.applyCommissionDiv = "1";

        l_request.mutualProductInfo = l_info;
        try
        {
            
            //admin
            TestDBUtility.deleteAll(AdministratorParams.TYPE);
            AdministratorParams l_adminRow = TestDBUtility.getAdministratorRow();
            TestDBUtility.insertWithDel(l_adminRow);
            
            WEB3Administrator l_expectAdministrator = new WEB3Administrator(l_adminRow);
            
            WEB3AdministratorForMock.mockGetInstanceFromLoginInfo(l_expectAdministrator);
            
            WEB3AdministratorForMock.mockValidateAuthority(
                l_expectAdministrator, 
                "C0201", 
                true, 
                true);
            
            WEB3AdministratorForMock.mockValidateTradingPassword(l_request.password, true);
            
            WEB3MutualFundTradingTimeManagementForMock.mockGetMutualOrderBizDate(GtlUtils.getSystemTimestamp());

            //InstitutionParams
            TestDBUtility.deleteAll(InstitutionParams.TYPE);
            InstitutionParams l_insParams = TestDBUtility.getInstitutionRow();
            TestDBUtility.insertWithDel(l_insParams);
            
            //MarketParams
            TestDBUtility.deleteAll(MarketParams.TYPE);
            MarketParams l_mktParams = TestDBUtility.getMarketRow();
            TestDBUtility.insertWithDel(l_mktParams);

            //ProductParams
            TestDBUtility.deleteAll(ProductParams.TYPE);
            ProductParams l_productParams = TestDBUtility.getProductRow();
            TestDBUtility.insertWithDel(l_productParams);
            
            //TradedProductParams
            TestDBUtility.deleteAll(TradedProductParams.TYPE);
            TradedProductParams l_tradedProductParams = TestDBUtility.getTradedProductRow();
            l_tradedProductParams.setTradedProductId(400003000100000L);
            TestDBUtility.insertWithDel(l_tradedProductParams);
            
            //TradingTimeParams
            TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams.setTradingTimeType("06");
            l_tradingTimeParams.setProductCode("0001000");
            l_tradingTimeParams.setBizDateType("1");
            l_tradingTimeParams.setSubmitMarketTrigger("0");
            l_tradingTimeParams.setEnableOrder("1");
            l_tradingTimeParams.setBizdateCalcParameter("1");
            TestDBUtility.insertWithDel(l_tradingTimeParams);
            
            //TradingTimeParams3
            TradingTimeParams l_tradingTimeParams3 = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams3.setTradingTimeType("06");
            l_tradingTimeParams3.setProductCode("0001000");
            l_tradingTimeParams3.setBizDateType("2");
            l_tradingTimeParams3.setSubmitMarketTrigger("0");
            l_tradingTimeParams3.setEnableOrder("1");
            l_tradingTimeParams3.setBizdateCalcParameter("1");
            TestDBUtility.insertWithDel(l_tradingTimeParams3);
            
            //TradingTimeParams2
            TradingTimeParams l_tradingTimeParams2 = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams2.setInstitutionCode("0D");
            l_tradingTimeParams2.setBranchCode("123");
            l_tradingTimeParams2.setMarketCode("N1");
            l_tradingTimeParams2.setTradingTimeType("01");
            l_tradingTimeParams2.setProductCode("0");
            l_tradingTimeParams2.setBizDateType("1");
            TestDBUtility.insertWithDel(l_tradingTimeParams2);
            
            //MutualFundProductParams
            TestDBUtility.deleteAll(MutualFundProductParams.TYPE);
            MutualFundProductParams l_mfProductParams = TestDBUtility.getMutualFundProductRow();
            l_mfProductParams.setProductId(3304148080000L);
            l_mfProductParams.setInstitutionCode("0D");
            l_mfProductParams.setProductCode("0001000");
            l_mfProductParams.setProductIssueCode("0");
            l_mfProductParams.setFundType(MutualFundTypeEnum.FOREIGN_MMF);
            l_mfProductParams.setCurrencyCode("0");
            TestDBUtility.insertWithDel(l_mfProductParams);
            
            //MutualFundTradedProductParams
            TestDBUtility.deleteAll(MutualFundTradedProductParams.TYPE);
            MutualFundTradedProductParams l_mfTradedProductParams = TestDBUtility.getMutualFundTradedProductRow();
            l_mfTradedProductParams.setInstitutionCode("0D");
            l_mfTradedProductParams.setTradedProductId(400003000100000L);
            l_mfTradedProductParams.setValidForBizDate(
                WEB3DateUtility.formatDate(
                    WEB3DateUtility.addDay(
                        GtlUtils.getSystemTimestamp(), 1), "yyyyMMdd"));
            TestDBUtility.insertWithDel(l_mfTradedProductParams);
            WEB3GentradeTradingTimeManagementForMock.mockGetOrderBizDate(WEB3DateUtility.addDay(
                    GtlUtils.getSystemTimestamp(), 1));
            //MutualFund2ndProductSonarParams
            TestDBUtility.deleteAll(MutualFund2ndProductSonarParams.TYPE);
            MutualFund2ndProductSonarParams l_2ndParams = 
                TestDBUtility.getMutualFund2ndProductSonarParams();
            TestDBUtility.insertWithDel(l_2ndParams);
            
            WEB3MutualFundTradedProduct l_web3TradeProduct = 
                new WEB3MutualFundTradedProduct(l_mfTradedProductParams);
            
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.mf.WEB3MutualFundProductManager",
                "validateProductCond",
                new Class[] {Institution.class, WEB3MutualFundProductCondSpec.class},
                null);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "webbroker3.mf.WEB3MutualFundProductManager",
            "getMutualFundTradedProduct",
            new Class[] {Institution.class, String.class},
            l_web3TradeProduct);
            
            WEB3AdminMutualConditionsCompleteResponse l_response = 
                l_impl.submitProductConditionsRegist(l_request);
            
                
            MutualFundProductRow l_mfProductRow = MutualFundProductDao.findRowByPk(3304148080000L);
            
            log.debug("OnlineUpdatedTimestamp === " + l_mfProductRow.getOnlineUpdatedTimestamp());
            log.debug("LastUpdater === " + l_mfProductRow.getLastUpdater());
            
            assertEquals(
                TestDBUtility.getAdministratorRow().getAdministratorCode(), 
                l_mfProductRow.getLastUpdater());

        }
        catch(WEB3BaseException l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        catch(Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
    }
}
@
