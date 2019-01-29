head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.02.10.21;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3MutualBalanceReferenceServiceImplTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 投信残高照会サービス実装テストクラス(WEB3MutualBalanceReferenceServiceImplTest.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/02/14 齊珂 (中訊) 新規作成
*/

package webbroker3.mf.service.delegate.stdimpls;

import java.util.ArrayList;
import java.util.List;

import com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Institution;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccountTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TaxTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.AssetParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.AssetRow;
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
import com.fitechlabs.xtrade.plugin.tc.gentrade.stdimpls.CalendarUtils;
import com.fitechlabs.xtrade.plugin.tc.xbmf.MutualFundTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.xbmf.data.MutualFundProductParams;
import com.fitechlabs.xtrade.plugin.tc.xbmf.data.MutualFundTradedProductParams;
import com.fitechlabs.xtrade.plugin.tc.xbmf.stdimpls.MutualFundAssetImpl;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.TradedProductParams;
import test.util.TestDBUtility;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.define.WEB3SystemHandlingDivDef;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagementForMock;
import webbroker3.gentrade.data.TradingTimeParams;
import webbroker3.gentrade.data.TradingTimeRow;
import webbroker3.mf.WEB3MutualFundProduct;
import webbroker3.mf.WEB3MutualFundTradedProduct;
import webbroker3.mf.WEB3MutualFundTradingTimeManagementForMock;
import webbroker3.mf.message.WEB3MutualBalanceReferenceRequest;
import webbroker3.mf.message.WEB3MutualBalanceReferenceResponse;
import webbroker3.mf.message.WEB3MutualBalanceReferenceTotalRequest;
import webbroker3.mf.message.WEB3MutualBalanceReferenceTotalResponse;
import webbroker3.mf.message.WEB3MutualSortKey;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

public class WEB3MutualBalanceReferenceServiceImplTest extends TestBaseForMock
{
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3MutualBalanceReferenceServiceImplTest.class);
    
    public WEB3MutualBalanceReferenceServiceImplTest(String arg0)
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
    
    public class WEB3MutualBalanceReferenceRequestForMock 
        extends WEB3MutualBalanceReferenceRequest
    {
        public void validate() throws WEB3BaseException 
        {
            final String STR_METHOD_NAME = "WEB3MutualBalanceReferenceRequestForMock + validate()";
            log.entering(STR_METHOD_NAME);
                      
            log.exiting(STR_METHOD_NAME);
        }
    }
    
    public class WEB3MutualBalanceReferenceTotalRequestForMock 
    extends WEB3MutualBalanceReferenceTotalRequest
    {
        public void validate() throws WEB3BaseException 
        {
            final String STR_METHOD_NAME = "WEB3MutualBalanceReferenceTotalRequestForMock + validate()";
            log.entering(STR_METHOD_NAME);
                      
            log.exiting(STR_METHOD_NAME);
        }
    }
    
    public void testGetBalanceReference01()
    {
        final String STR_METHOD_NAME = "testGetBalanceReference01()";
        log.entering(STR_METHOD_NAME);
        WEB3MutualBalanceReferenceResponse l_response = null;
        
        MOCK_MANAGER.setIsMockUsed(true);
        


        SubAccountParams l_subAccountParams =
            TestDBUtility.getSubAccountRow();
        l_subAccountParams.setInstitutionCode("6D");
        l_subAccountParams.setInstitutionId(63);
        l_subAccountParams.setSubAccountId(333812512203L);
        l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
        
        
        MainAccountParams l_mainAccountParams =
            TestDBUtility.getMainAccountRow();
        l_mainAccountParams.setAccountId(333812512203L);
        l_mainAccountParams.setInstitutionId(63);

        InstitutionParams l_institutionParams =
            TestDBUtility.getInstitutionRow();
        l_institutionParams.setInstitutionCode("0D");
        l_institutionParams.setInstitutionId(63);
        
        ProductParams l_productParams =
            TestDBUtility.getProductRow();
        l_productParams.setProductId(3304148080001L);
        l_productParams.setInstitutionCode("0");
        l_productParams.setProductType(ProductTypeEnum.EQUITY);

        MarketParams l_marketParams =
            TestDBUtility.getMarketRow();
        l_marketParams.setMarketCode("N1");
        l_marketParams.setMarketId(3303L);
        l_marketParams.setInstitutionCode("6D");

        BranchParams l_branchParams =
            TestDBUtility.getBranchRow();
        l_branchParams.setInstitutionCode("6D");
        l_branchParams.setInstitutionId(63);
        
        
        MutualFundTradedProductParams l_mfTradedProductParams =
            TestDBUtility.getMutualFundTradedProductRow();
        

        TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
        l_tradingTimeParams.setInstitutionCode("0D");
        l_tradingTimeParams.setBranchCode("123");
        l_tradingTimeParams.setTradingTimeType("01");
        l_tradingTimeParams.setBizDateType("1");
        l_tradingTimeParams.setMarketCode("N1");
        l_tradingTimeParams.setProductCode("0001000");
        
        MOCK_MANAGER.setIsMockUsed(true);
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
            "getAccountId",
            new Class[] {},new Long(333812512203L));
       
        List l_lis = new ArrayList();
        AssetParams l_assertParams = TestDBUtility.getAssetRow();
        l_assertParams.setTaxType(TaxTypeEnum.NORMAL);
        MutualFundAssetImpl l_assert = new MutualFundAssetImpl(l_assertParams);
        l_lis.add(l_assert);
        
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "webbroker3.mf.WEB3MutualFundPositionManager",
            "getAssets",
            new Class[] {SubAccount.class, String.class},
            l_lis);

        Double l_dblQty = new Double("2.0");
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "webbroker3.mf.WEB3MutualFundPositionManager",
            "calcSellPossiblePositionQty",
            new Class[] {SubAccount.class, WEB3MutualFundProduct.class, String.class},
            l_dblQty);
        
        Double l_dblValue = new Double("2.0");
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "webbroker3.mf.WEB3MutualFundPositionManager",
            "calcMarketValue",
            new Class[] {SubAccount.class, WEB3MutualFundProduct.class, String.class},
            l_dblValue);
        
        Double l_dblProfitLoss = new Double("2.0");
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "webbroker3.mf.WEB3MutualFundPositionManager",
            "calcAppraisalProfitLoss",
            new Class[] {SubAccount.class, WEB3MutualFundProduct.class, String.class},
            l_dblProfitLoss);

        Long l_lngDist = new Long("2");
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "webbroker3.mf.WEB3MutualFundPositionManager",
            "getUnreceiveDist",
            new Class[] {String.class, WEB3MutualFundProduct.class},
            l_lngDist);
        
        
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "webbroker3.mf.WEB3MutualFundProduct",
            "isAcquiredDeregExistence",
            new Class[] {},
            Boolean.TRUE);
        
        
        MutualFundProductParams l_mfProductParams = TestDBUtility.getMutualFundProductRow();
        l_mfProductParams.setProductId(1006169090018L);
        l_mfProductParams.setInstitutionCode("0D");
        l_mfProductParams.setProductCode("0001000");
        l_mfProductParams.setProductIssueCode("0");
        l_mfProductParams.setCurrencyCode("T0");
        l_mfProductParams.setFundType(MutualFundTypeEnum.FOREIGN_MMF);
        ProductParams l_product = TestDBUtility.getProductRow();
        l_product.setProductId(1006169090018L);
        try
        {
            MarketCalendarParams l_marketCalendarParams = new MarketCalendarParams();
            l_marketCalendarParams.setMarketId(3303L);
            l_marketCalendarParams.setTradeDate(CalendarUtils.clearTimeFields(GtlUtils.getSystemTimestamp()));
            l_marketCalendarParams.setHolidayFlag(BooleanEnum.TRUE);
            l_marketCalendarParams.setTradeOpenTime("090000");
            l_marketCalendarParams.setTradeCloseTime("150000");
            l_marketCalendarParams.setCreatedTimestamp(WEB3DateUtility.getDate("20040707","yyyyMMdd"));
            l_marketCalendarParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20040707","yyyyMMdd"));
            TestDBUtility.deleteAll(MarketCalendarRow.TYPE);
            TestDBUtility.insertWithDel(l_marketCalendarParams);
            
            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            TestDBUtility.deleteAll(InstitutionRow.TYPE);
            TestDBUtility.deleteAll(ProductRow.TYPE);
            TestDBUtility.deleteAll(MarketRow.TYPE);
            TestDBUtility.deleteAll(BranchRow.TYPE);
            TestDBUtility.deleteAll(TradingTimeRow.TYPE);


            TestDBUtility.insertWithDel(l_subAccountParams);
            TestDBUtility.insertWithDel(l_mainAccountParams);
            TestDBUtility.insertWithDel(l_institutionParams);
            TestDBUtility.insertWithDel(l_productParams);
            TestDBUtility.insertWithDel(l_marketParams);
            TestDBUtility.insertWithDel(l_branchParams);

            TestDBUtility.insertWithDel(l_mfProductParams);
            TestDBUtility.insertWithDel(l_product);
            TestDBUtility.insertWithDel(l_tradingTimeParams);
            TestDBUtility.insertWithDel(l_mfTradedProductParams);
            
            WEB3GentradeTradingTimeManagementForMock.mockSetTimestamp();
            
            String l_strDate = "174120";
            WEB3MutualFundTradingTimeManagementForMock.mockGetOrderCloseTime(l_strDate);
            
            WEB3MutualBalanceReferenceRequestForMock l_request = 
                new WEB3MutualBalanceReferenceRequestForMock();
            
            WEB3MutualSortKey[] l_keys = new WEB3MutualSortKey[1];
            l_keys[0] = new WEB3MutualSortKey();
            l_keys[0].keyItem = "taxType";
            l_keys[0].ascDesc = "A";
            l_request.sortKeys = l_keys;
            l_request.pageIndex = "1";
            l_request.pageSize = "1";
            
            WEB3MutualBalanceReferenceServiceImpl l_impl = 
                new WEB3MutualBalanceReferenceServiceImpl();
            l_response = l_impl.getBalanceReference(l_request);
            assertEquals("0", l_response.balanceReference[0].taxType);
            assertNull(l_response.balanceReference[0].indivPrincipal);
            assertNull(l_response.balanceReference[0].indivPrincipal);
            assertNull(l_response.balanceReference[0].appraisalProfitLoss);
            assertNull(l_response.balanceReference[0].yenConstantValue);
            assertNull(l_response.balanceReference[0].referenceRate);
            assertNull(l_response.balanceReference[0].referenceRateFixedDay);
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        } 
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testGetBalanceReference02()
    {
        final String STR_METHOD_NAME = "testGetBalanceReference02()";
        log.entering(STR_METHOD_NAME);
        WEB3MutualBalanceReferenceResponse l_response = null;
        
        MOCK_MANAGER.setIsMockUsed(true);
        


        SubAccountParams l_subAccountParams =
            TestDBUtility.getSubAccountRow();
        l_subAccountParams.setInstitutionCode("6D");
        l_subAccountParams.setInstitutionId(63);
        l_subAccountParams.setSubAccountId(333812512203L);
        l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
        
        
        MainAccountParams l_mainAccountParams =
            TestDBUtility.getMainAccountRow();
        l_mainAccountParams.setAccountId(333812512203L);
        l_mainAccountParams.setInstitutionId(63);

        InstitutionParams l_institutionParams =
            TestDBUtility.getInstitutionRow();
        l_institutionParams.setInstitutionCode("0D");
        l_institutionParams.setInstitutionId(63);
        
        ProductParams l_productParams =
            TestDBUtility.getProductRow();
        l_productParams.setProductId(3304148080001L);
        l_productParams.setInstitutionCode("0");
        l_productParams.setProductType(ProductTypeEnum.EQUITY);

        MarketParams l_marketParams =
            TestDBUtility.getMarketRow();
        l_marketParams.setMarketCode("N1");
        l_marketParams.setMarketId(3303L);
        l_marketParams.setInstitutionCode("6D");

        BranchParams l_branchParams =
            TestDBUtility.getBranchRow();
        l_branchParams.setInstitutionCode("6D");
        l_branchParams.setInstitutionId(63);
        
        
        MutualFundTradedProductParams l_mfTradedProductParams =
            TestDBUtility.getMutualFundTradedProductRow();
        

        TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
        l_tradingTimeParams.setInstitutionCode("0D");
        l_tradingTimeParams.setBranchCode("123");
        l_tradingTimeParams.setTradingTimeType("01");
        l_tradingTimeParams.setBizDateType("1");
        l_tradingTimeParams.setMarketCode("N1");
        l_tradingTimeParams.setProductCode("0001000");
        
        MOCK_MANAGER.setIsMockUsed(true);
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
            "getAccountId",
            new Class[] {},new Long(333812512203L));
       
        List l_lis = new ArrayList();
        AssetParams l_assertParams = TestDBUtility.getAssetRow();
        l_assertParams.setTaxType(TaxTypeEnum.SPECIAL);
        MutualFundAssetImpl l_assert = new MutualFundAssetImpl(l_assertParams);
        l_lis.add(l_assert);
        
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "webbroker3.mf.WEB3MutualFundPositionManager",
            "getAssets",
            new Class[] {SubAccount.class, String.class},
            l_lis);

        Double l_dblQty = new Double("2.0");
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "webbroker3.mf.WEB3MutualFundPositionManager",
            "calcSellPossiblePositionQty",
            new Class[] {SubAccount.class, WEB3MutualFundProduct.class, String.class},
            l_dblQty);
        
        Double l_dblValue = new Double("2.0");
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "webbroker3.mf.WEB3MutualFundPositionManager",
            "calcMarketValue",
            new Class[] {SubAccount.class, WEB3MutualFundProduct.class, String.class},
            l_dblValue);
        
        Double l_dblProfitLoss = new Double("2.0");
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "webbroker3.mf.WEB3MutualFundPositionManager",
            "calcAppraisalProfitLoss",
            new Class[] {SubAccount.class, WEB3MutualFundProduct.class, String.class},
            l_dblProfitLoss);

        Long l_lngDist = new Long("2");
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "webbroker3.mf.WEB3MutualFundPositionManager",
            "getUnreceiveDist",
            new Class[] {String.class, WEB3MutualFundProduct.class},
            l_lngDist);
        
        
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "webbroker3.mf.WEB3MutualFundProduct",
            "isAcquiredDeregExistence",
            new Class[] {},
            Boolean.TRUE);
        
        
        MutualFundProductParams l_mfProductParams = TestDBUtility.getMutualFundProductRow();
        l_mfProductParams.setProductId(1006169090018L);
        l_mfProductParams.setInstitutionCode("0D");
        l_mfProductParams.setProductCode("0001000");
        l_mfProductParams.setProductIssueCode("0");
        l_mfProductParams.setCurrencyCode("T0");
        l_mfProductParams.setFundType(MutualFundTypeEnum.FOREIGN_MMF);
        ProductParams l_product = TestDBUtility.getProductRow();
        l_product.setProductId(1006169090018L);
        try
        {
            MarketCalendarParams l_marketCalendarParams = new MarketCalendarParams();
            l_marketCalendarParams.setMarketId(3303L);
            l_marketCalendarParams.setTradeDate(CalendarUtils.clearTimeFields(GtlUtils.getSystemTimestamp()));
            l_marketCalendarParams.setHolidayFlag(BooleanEnum.TRUE);
            l_marketCalendarParams.setTradeOpenTime("090000");
            l_marketCalendarParams.setTradeCloseTime("150000");
            l_marketCalendarParams.setCreatedTimestamp(WEB3DateUtility.getDate("20040707","yyyyMMdd"));
            l_marketCalendarParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20040707","yyyyMMdd"));
            TestDBUtility.deleteAll(MarketCalendarRow.TYPE);
            TestDBUtility.insertWithDel(l_marketCalendarParams);
            
            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            TestDBUtility.deleteAll(InstitutionRow.TYPE);
            TestDBUtility.deleteAll(ProductRow.TYPE);
            TestDBUtility.deleteAll(MarketRow.TYPE);
            TestDBUtility.deleteAll(BranchRow.TYPE);
            TestDBUtility.deleteAll(TradingTimeRow.TYPE);


            TestDBUtility.insertWithDel(l_subAccountParams);
            TestDBUtility.insertWithDel(l_mainAccountParams);
            TestDBUtility.insertWithDel(l_institutionParams);
            TestDBUtility.insertWithDel(l_productParams);
            TestDBUtility.insertWithDel(l_marketParams);
            TestDBUtility.insertWithDel(l_branchParams);

            TestDBUtility.insertWithDel(l_mfProductParams);
            TestDBUtility.insertWithDel(l_product);
            TestDBUtility.insertWithDel(l_tradingTimeParams);
            TestDBUtility.insertWithDel(l_mfTradedProductParams);
            
            WEB3GentradeTradingTimeManagementForMock.mockSetTimestamp();
            
            String l_strDate = "174120";
            WEB3MutualFundTradingTimeManagementForMock.mockGetOrderCloseTime(l_strDate);
            
            WEB3MutualBalanceReferenceRequestForMock l_request = 
                new WEB3MutualBalanceReferenceRequestForMock();
            
            WEB3MutualSortKey[] l_keys = new WEB3MutualSortKey[1];
            l_keys[0] = new WEB3MutualSortKey();
            l_keys[0].keyItem = "taxType";
            l_keys[0].ascDesc = "A";
            l_request.sortKeys = l_keys;
            l_request.pageIndex = "1";
            l_request.pageSize = "1";
            
            WEB3MutualBalanceReferenceServiceImpl l_impl = 
                new WEB3MutualBalanceReferenceServiceImpl();
            l_response = l_impl.getBalanceReference(l_request);
            assertEquals("1", l_response.balanceReference[0].taxType);
            assertNull(l_response.balanceReference[0].indivPrincipal);
            assertNull(l_response.balanceReference[0].appraisalProfitLoss);
            assertNull(l_response.balanceReference[0].yenConstantValue);
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        } 
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testGetBalanceReference03()
    {
        final String STR_METHOD_NAME = "testGetBalanceReference03()";
        log.entering(STR_METHOD_NAME);
        WEB3MutualBalanceReferenceResponse l_response = null;
        
        MOCK_MANAGER.setIsMockUsed(true);
        


        SubAccountParams l_subAccountParams =
            TestDBUtility.getSubAccountRow();
        l_subAccountParams.setInstitutionCode("6D");
        l_subAccountParams.setInstitutionId(63);
        l_subAccountParams.setSubAccountId(333812512203L);
        l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
        
        
        MainAccountParams l_mainAccountParams =
            TestDBUtility.getMainAccountRow();
        l_mainAccountParams.setAccountId(333812512203L);
        l_mainAccountParams.setInstitutionId(63);

        InstitutionParams l_institutionParams =
            TestDBUtility.getInstitutionRow();
        l_institutionParams.setInstitutionCode("0D");
        l_institutionParams.setInstitutionId(63);
        
        ProductParams l_productParams =
            TestDBUtility.getProductRow();
        l_productParams.setProductId(3304148080001L);
        l_productParams.setInstitutionCode("0");
        l_productParams.setProductType(ProductTypeEnum.EQUITY);

        MarketParams l_marketParams =
            TestDBUtility.getMarketRow();
        l_marketParams.setMarketCode("N1");
        l_marketParams.setMarketId(3303L);
        l_marketParams.setInstitutionCode("6D");

        BranchParams l_branchParams =
            TestDBUtility.getBranchRow();
        l_branchParams.setInstitutionCode("6D");
        l_branchParams.setInstitutionId(63);
        
        
        MutualFundTradedProductParams l_mfTradedProductParams =
            TestDBUtility.getMutualFundTradedProductRow();
        

        TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
        l_tradingTimeParams.setInstitutionCode("0D");
        l_tradingTimeParams.setBranchCode("123");
        l_tradingTimeParams.setTradingTimeType("01");
        l_tradingTimeParams.setBizDateType("1");
        l_tradingTimeParams.setMarketCode("N1");
        l_tradingTimeParams.setProductCode("0001000");
        
        MOCK_MANAGER.setIsMockUsed(true);
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
            "getAccountId",
            new Class[] {},new Long(333812512203L));
       
        List l_lis = new ArrayList();
        AssetParams l_assertParams = TestDBUtility.getAssetRow();
        l_assertParams.setTaxType(TaxTypeEnum.SPECIAL_WITHHOLD);
        MutualFundAssetImpl l_assert = new MutualFundAssetImpl(l_assertParams);
        l_lis.add(l_assert);
        
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "webbroker3.mf.WEB3MutualFundPositionManager",
            "getAssets",
            new Class[] {SubAccount.class, String.class},
            l_lis);

        Double l_dblQty = new Double("2.0");
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "webbroker3.mf.WEB3MutualFundPositionManager",
            "calcSellPossiblePositionQty",
            new Class[] {SubAccount.class, WEB3MutualFundProduct.class, String.class},
            l_dblQty);
        
        Double l_dblValue = new Double("2.0");
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "webbroker3.mf.WEB3MutualFundPositionManager",
            "calcMarketValue",
            new Class[] {SubAccount.class, WEB3MutualFundProduct.class, String.class},
            l_dblValue);
        
        Double l_dblProfitLoss = new Double("2.0");
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "webbroker3.mf.WEB3MutualFundPositionManager",
            "calcAppraisalProfitLoss",
            new Class[] {SubAccount.class, WEB3MutualFundProduct.class, String.class},
            l_dblProfitLoss);

        Long l_lngDist = new Long("2");
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "webbroker3.mf.WEB3MutualFundPositionManager",
            "getUnreceiveDist",
            new Class[] {String.class, WEB3MutualFundProduct.class},
            l_lngDist);
        
        
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "webbroker3.mf.WEB3MutualFundProduct",
            "isAcquiredDeregExistence",
            new Class[] {},
            Boolean.TRUE);
        
        
        MutualFundProductParams l_mfProductParams = TestDBUtility.getMutualFundProductRow();
        l_mfProductParams.setProductId(1006169090018L);
        l_mfProductParams.setInstitutionCode("0D");
        l_mfProductParams.setProductCode("0001000");
        l_mfProductParams.setProductIssueCode("0");
        l_mfProductParams.setCurrencyCode("T0");
        l_mfProductParams.setFundType(MutualFundTypeEnum.FOREIGN_MMF);
        ProductParams l_product = TestDBUtility.getProductRow();
        l_product.setProductId(1006169090018L);
        try
        {
            MarketCalendarParams l_marketCalendarParams = new MarketCalendarParams();
            l_marketCalendarParams.setMarketId(3303L);
            l_marketCalendarParams.setTradeDate(CalendarUtils.clearTimeFields(GtlUtils.getSystemTimestamp()));
            l_marketCalendarParams.setHolidayFlag(BooleanEnum.TRUE);
            l_marketCalendarParams.setTradeOpenTime("090000");
            l_marketCalendarParams.setTradeCloseTime("150000");
            l_marketCalendarParams.setCreatedTimestamp(WEB3DateUtility.getDate("20040707","yyyyMMdd"));
            l_marketCalendarParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20040707","yyyyMMdd"));
            TestDBUtility.deleteAll(MarketCalendarRow.TYPE);
            TestDBUtility.insertWithDel(l_marketCalendarParams);
            
            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            TestDBUtility.deleteAll(InstitutionRow.TYPE);
            TestDBUtility.deleteAll(ProductRow.TYPE);
            TestDBUtility.deleteAll(MarketRow.TYPE);
            TestDBUtility.deleteAll(BranchRow.TYPE);
            TestDBUtility.deleteAll(TradingTimeRow.TYPE);


            TestDBUtility.insertWithDel(l_subAccountParams);
            TestDBUtility.insertWithDel(l_mainAccountParams);
            TestDBUtility.insertWithDel(l_institutionParams);
            TestDBUtility.insertWithDel(l_productParams);
            TestDBUtility.insertWithDel(l_marketParams);
            TestDBUtility.insertWithDel(l_branchParams);

            TestDBUtility.insertWithDel(l_mfProductParams);
            TestDBUtility.insertWithDel(l_product);
            TestDBUtility.insertWithDel(l_tradingTimeParams);
            TestDBUtility.insertWithDel(l_mfTradedProductParams);
            
            WEB3GentradeTradingTimeManagementForMock.mockSetTimestamp();
            
            String l_strDate = "174120";
            WEB3MutualFundTradingTimeManagementForMock.mockGetOrderCloseTime(l_strDate);
            
            WEB3MutualBalanceReferenceRequestForMock l_request = 
                new WEB3MutualBalanceReferenceRequestForMock();
            
            WEB3MutualSortKey[] l_keys = new WEB3MutualSortKey[1];
            l_keys[0] = new WEB3MutualSortKey();
            l_keys[0].keyItem = "taxType";
            l_keys[0].ascDesc = "A";
            l_request.sortKeys = l_keys;
            l_request.pageIndex = "1";
            l_request.pageSize = "1";
            
            WEB3MutualBalanceReferenceServiceImpl l_impl = 
                new WEB3MutualBalanceReferenceServiceImpl();
            l_response = l_impl.getBalanceReference(l_request);
            assertEquals("1", l_response.balanceReference[0].taxType);
            assertEquals("2", l_response.balanceReference[0].unreceivedDistribution);   
            assertNull("2", l_response.balanceReference[0].appraisalProfitLoss);
            assertNull("2", l_response.balanceReference[0].yenConstantValue);

        }
        catch (WEB3BaseException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        } 
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
 
    public void testGetBalanceReference04()
    {
        final String STR_METHOD_NAME = "testGetBalanceReference04()";
        log.entering(STR_METHOD_NAME);
        WEB3MutualBalanceReferenceResponse l_response = null;
        
        MOCK_MANAGER.setIsMockUsed(true);
        


        SubAccountParams l_subAccountParams =
            TestDBUtility.getSubAccountRow();
        l_subAccountParams.setInstitutionCode("6D");
        l_subAccountParams.setInstitutionId(63);
        l_subAccountParams.setSubAccountId(333812512203L);
        l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
        
        
        MainAccountParams l_mainAccountParams =
            TestDBUtility.getMainAccountRow();
        l_mainAccountParams.setAccountId(333812512203L);
        l_mainAccountParams.setInstitutionId(63);

        InstitutionParams l_institutionParams =
            TestDBUtility.getInstitutionRow();
        l_institutionParams.setInstitutionCode("0D");
        l_institutionParams.setInstitutionId(63);
        
        ProductParams l_productParams =
            TestDBUtility.getProductRow();
        l_productParams.setProductId(3304148080001L);
        l_productParams.setInstitutionCode("0");
        l_productParams.setProductType(ProductTypeEnum.EQUITY);

        MarketParams l_marketParams =
            TestDBUtility.getMarketRow();
        l_marketParams.setMarketCode("N1");
        l_marketParams.setMarketId(3303L);
        l_marketParams.setInstitutionCode("6D");

        BranchParams l_branchParams =
            TestDBUtility.getBranchRow();
        l_branchParams.setInstitutionCode("6D");
        l_branchParams.setInstitutionId(63);
        
        
        MutualFundTradedProductParams l_mfTradedProductParams =
            TestDBUtility.getMutualFundTradedProductRow();
        

        TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
        l_tradingTimeParams.setInstitutionCode("0D");
        l_tradingTimeParams.setBranchCode("123");
        l_tradingTimeParams.setTradingTimeType("01");
        l_tradingTimeParams.setBizDateType("1");
        l_tradingTimeParams.setMarketCode("N1");
        l_tradingTimeParams.setProductCode("0001000");
        
        MOCK_MANAGER.setIsMockUsed(true);
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
            "getAccountId",
            new Class[] {},new Long(333812512203L));
       
        List l_lis = new ArrayList();
        AssetParams l_assertParams = TestDBUtility.getAssetRow();
        l_assertParams.setTaxType(TaxTypeEnum.SPECIAL_WITHHOLD);
        MutualFundAssetImpl l_assert = new MutualFundAssetImpl(l_assertParams);
        l_lis.add(l_assert);
        
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "webbroker3.mf.WEB3MutualFundPositionManager",
            "getAssets",
            new Class[] {SubAccount.class, String.class},
            l_lis);

        Double l_dblQty = new Double("2.0");
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "webbroker3.mf.WEB3MutualFundPositionManager",
            "calcSellPossiblePositionQty",
            new Class[] {SubAccount.class, WEB3MutualFundProduct.class, String.class},
            l_dblQty);
        
        Double l_dblValue = new Double("2.0");
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "webbroker3.mf.WEB3MutualFundPositionManager",
            "calcMarketValue",
            new Class[] {SubAccount.class, WEB3MutualFundProduct.class, String.class},
            l_dblValue);
        
        Double l_dblProfitLoss = new Double("2.0");
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "webbroker3.mf.WEB3MutualFundPositionManager",
            "calcAppraisalProfitLoss",
            new Class[] {SubAccount.class, WEB3MutualFundProduct.class, String.class},
            l_dblProfitLoss);

        Long l_lngDist = new Long("2");
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "webbroker3.mf.WEB3MutualFundPositionManager",
            "getUnreceiveDist",
            new Class[] {String.class, WEB3MutualFundProduct.class},
            l_lngDist);
        
        
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "webbroker3.mf.WEB3MutualFundProduct",
            "isAcquiredDeregExistence",
            new Class[] {},
            Boolean.TRUE);
        
        
        MutualFundProductParams l_mfProductParams = TestDBUtility.getMutualFundProductRow();
        l_mfProductParams.setProductId(1006169090018L);
        l_mfProductParams.setInstitutionCode("0D");
        l_mfProductParams.setProductCode("0001000");
        l_mfProductParams.setProductIssueCode("0");
        l_mfProductParams.setCurrencyCode("T0");
        l_mfProductParams.setFundType(MutualFundTypeEnum.FOREIGN_MMF);
        ProductParams l_product = TestDBUtility.getProductRow();
        l_product.setProductId(1006169090018L);
        try
        {
            MarketCalendarParams l_marketCalendarParams = new MarketCalendarParams();
            l_marketCalendarParams.setMarketId(3303L);
            l_marketCalendarParams.setTradeDate(CalendarUtils.clearTimeFields(GtlUtils.getSystemTimestamp()));
            l_marketCalendarParams.setHolidayFlag(BooleanEnum.TRUE);
            l_marketCalendarParams.setTradeOpenTime("090000");
            l_marketCalendarParams.setTradeCloseTime("150000");
            l_marketCalendarParams.setCreatedTimestamp(WEB3DateUtility.getDate("20040707","yyyyMMdd"));
            l_marketCalendarParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20040707","yyyyMMdd"));
            TestDBUtility.deleteAll(MarketCalendarRow.TYPE);
            TestDBUtility.insertWithDel(l_marketCalendarParams);
            
            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            TestDBUtility.deleteAll(InstitutionRow.TYPE);
            TestDBUtility.deleteAll(ProductRow.TYPE);
            TestDBUtility.deleteAll(MarketRow.TYPE);
            TestDBUtility.deleteAll(BranchRow.TYPE);
            TestDBUtility.deleteAll(TradingTimeRow.TYPE);


            TestDBUtility.insertWithDel(l_subAccountParams);
            TestDBUtility.insertWithDel(l_mainAccountParams);
            TestDBUtility.insertWithDel(l_institutionParams);
            TestDBUtility.insertWithDel(l_productParams);
            TestDBUtility.insertWithDel(l_marketParams);
            TestDBUtility.insertWithDel(l_branchParams);

            TestDBUtility.insertWithDel(l_mfProductParams);
            TestDBUtility.insertWithDel(l_product);
            TestDBUtility.insertWithDel(l_tradingTimeParams);
            TestDBUtility.insertWithDel(l_mfTradedProductParams);
            
            WEB3GentradeTradingTimeManagementForMock.mockSetTimestamp();
            
            String l_strDate = "174120";
            WEB3MutualFundTradingTimeManagementForMock.mockGetOrderCloseTime(l_strDate);
            
            WEB3MutualBalanceReferenceRequestForMock l_request = 
                new WEB3MutualBalanceReferenceRequestForMock();
            
            WEB3MutualSortKey[] l_keys = new WEB3MutualSortKey[1];
            l_keys[0] = new WEB3MutualSortKey();
            l_keys[0].keyItem = "taxType";
            l_keys[0].ascDesc = "A";
            l_request.sortKeys = l_keys;
            l_request.pageIndex = "1";
            l_request.pageSize = "1";
            
            WEB3MutualBalanceReferenceServiceImpl l_impl = 
                new WEB3MutualBalanceReferenceServiceImpl();
            l_response = l_impl.getBalanceReference(l_request);
            assertEquals("1", l_response.balanceReference[0].taxType);
            assertEquals("2", l_response.balanceReference[0].unreceivedDistribution);   
            assertNull("2", l_response.balanceReference[0].appraisalProfitLoss);
            assertNull("2", l_response.balanceReference[0].yenConstantValue);

        }
        catch (WEB3BaseException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        } 
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testGetBalanceReference05()
    {
        final String STR_METHOD_NAME = "testGetBalanceReference05()";
        log.entering(STR_METHOD_NAME);
        WEB3MutualBalanceReferenceResponse l_response = null;
        
        MOCK_MANAGER.setIsMockUsed(true);
        


        SubAccountParams l_subAccountParams =
            TestDBUtility.getSubAccountRow();
        l_subAccountParams.setInstitutionCode("6D");
        l_subAccountParams.setInstitutionId(63);
        l_subAccountParams.setSubAccountId(333812512203L);
        l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
        
        
        MainAccountParams l_mainAccountParams =
            TestDBUtility.getMainAccountRow();
        l_mainAccountParams.setAccountId(333812512203L);
        l_mainAccountParams.setInstitutionId(63);

        InstitutionParams l_institutionParams =
            TestDBUtility.getInstitutionRow();
        l_institutionParams.setInstitutionCode("0D");
        l_institutionParams.setInstitutionId(63);
        
        ProductParams l_productParams =
            TestDBUtility.getProductRow();
        l_productParams.setProductId(3304148080001L);
        l_productParams.setInstitutionCode("0");
        l_productParams.setProductType(ProductTypeEnum.EQUITY);

        MarketParams l_marketParams =
            TestDBUtility.getMarketRow();
        l_marketParams.setMarketCode("N1");
        l_marketParams.setMarketId(3303L);
        l_marketParams.setInstitutionCode("6D");

        BranchParams l_branchParams =
            TestDBUtility.getBranchRow();
        l_branchParams.setInstitutionCode("6D");
        l_branchParams.setInstitutionId(63);
        
        
        MutualFundTradedProductParams l_mfTradedProductParams =
            TestDBUtility.getMutualFundTradedProductRow();
        

        TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
        l_tradingTimeParams.setInstitutionCode("0D");
        l_tradingTimeParams.setBranchCode("123");
        l_tradingTimeParams.setTradingTimeType("01");
        l_tradingTimeParams.setBizDateType("1");
        l_tradingTimeParams.setMarketCode("N1");
        l_tradingTimeParams.setProductCode("0001000");
        
        MOCK_MANAGER.setIsMockUsed(true);
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
            "getAccountId",
            new Class[] {},new Long(333812512203L));
       
        List l_lis = new ArrayList();
        AssetParams l_assertParams = TestDBUtility.getAssetRow();
        l_assertParams.setTaxType(TaxTypeEnum.SPECIAL_WITHHOLD);
        MutualFundAssetImpl l_assert = new MutualFundAssetImpl(l_assertParams);
        l_lis.add(l_assert);
        
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "webbroker3.mf.WEB3MutualFundPositionManager",
            "getAssets",
            new Class[] {SubAccount.class, String.class},
            l_lis);

        Double l_dblQty = new Double("2.0");
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "webbroker3.mf.WEB3MutualFundPositionManager",
            "calcSellPossiblePositionQty",
            new Class[] {SubAccount.class, WEB3MutualFundProduct.class, String.class},
            l_dblQty);
        
        Double l_dblValue = new Double("2.0");
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "webbroker3.mf.WEB3MutualFundPositionManager",
            "calcMarketValue",
            new Class[] {SubAccount.class, WEB3MutualFundProduct.class, String.class},
            l_dblValue);
        
        Double l_dblProfitLoss = new Double("2.0");
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "webbroker3.mf.WEB3MutualFundPositionManager",
            "calcAppraisalProfitLoss",
            new Class[] {SubAccount.class, WEB3MutualFundProduct.class, String.class},
            l_dblProfitLoss);

        Long l_lngDist = new Long("2");
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "webbroker3.mf.WEB3MutualFundPositionManager",
            "getUnreceiveDist",
            new Class[] {String.class, WEB3MutualFundProduct.class},
            l_lngDist);
        
        
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "webbroker3.mf.WEB3MutualFundProduct",
            "isAcquiredDeregExistence",
            new Class[] {},
            Boolean.TRUE);
        
        
        MutualFundProductParams l_mfProductParams = TestDBUtility.getMutualFundProductRow();
        l_mfProductParams.setProductId(1006169090018L);
        l_mfProductParams.setInstitutionCode("0D");
        l_mfProductParams.setProductCode("0001000");
        l_mfProductParams.setProductIssueCode("0");
        l_mfProductParams.setCurrencyCode("T0");
        l_mfProductParams.setFundType(MutualFundTypeEnum.DOMESTIC);
        ProductParams l_product = TestDBUtility.getProductRow();
        l_product.setProductId(1006169090018L);
        try
        {
            MarketCalendarParams l_marketCalendarParams = new MarketCalendarParams();
            l_marketCalendarParams.setMarketId(3303L);
            l_marketCalendarParams.setTradeDate(CalendarUtils.clearTimeFields(GtlUtils.getSystemTimestamp()));
            l_marketCalendarParams.setHolidayFlag(BooleanEnum.TRUE);
            l_marketCalendarParams.setTradeOpenTime("090000");
            l_marketCalendarParams.setTradeCloseTime("150000");
            l_marketCalendarParams.setCreatedTimestamp(WEB3DateUtility.getDate("20040707","yyyyMMdd"));
            l_marketCalendarParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20040707","yyyyMMdd"));
            TestDBUtility.deleteAll(MarketCalendarRow.TYPE);
            TestDBUtility.insertWithDel(l_marketCalendarParams);
            
            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            TestDBUtility.deleteAll(InstitutionRow.TYPE);
            TestDBUtility.deleteAll(ProductRow.TYPE);
            TestDBUtility.deleteAll(MarketRow.TYPE);
            TestDBUtility.deleteAll(BranchRow.TYPE);
            TestDBUtility.deleteAll(TradingTimeRow.TYPE);


            TestDBUtility.insertWithDel(l_subAccountParams);
            TestDBUtility.insertWithDel(l_mainAccountParams);
            TestDBUtility.insertWithDel(l_institutionParams);
            TestDBUtility.insertWithDel(l_productParams);
            TestDBUtility.insertWithDel(l_marketParams);
            TestDBUtility.insertWithDel(l_branchParams);

            TestDBUtility.insertWithDel(l_mfProductParams);
            TestDBUtility.insertWithDel(l_product);
            TestDBUtility.insertWithDel(l_tradingTimeParams);
            TestDBUtility.insertWithDel(l_mfTradedProductParams);
            
            WEB3GentradeTradingTimeManagementForMock.mockSetTimestamp();
            
            String l_strDate = "174120";
            WEB3MutualFundTradingTimeManagementForMock.mockGetOrderCloseTime(l_strDate);
            
            WEB3MutualBalanceReferenceRequestForMock l_request = 
                new WEB3MutualBalanceReferenceRequestForMock();
            
            WEB3MutualSortKey[] l_keys = new WEB3MutualSortKey[1];
            l_keys[0] = new WEB3MutualSortKey();
            l_keys[0].keyItem = "taxType";
            l_keys[0].ascDesc = "A";
            l_request.sortKeys = l_keys;
            l_request.pageIndex = "1";
            l_request.pageSize = "1";
            
            WEB3MutualBalanceReferenceServiceImpl l_impl = 
                new WEB3MutualBalanceReferenceServiceImpl();
            l_response = l_impl.getBalanceReference(l_request);
            assertNull(l_response.balanceReference[0].unreceivedDistribution);   

        }
        catch (WEB3BaseException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        } 
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testGetBalanceReferenceTotal01()
    {
          final String STR_METHOD_NAME = "testGetBalanceReferenceTotal01()";
          log.entering(STR_METHOD_NAME);
          WEB3MutualBalanceReferenceTotalResponse l_response = null;
          
          MOCK_MANAGER.setIsMockUsed(true);
          
        
        
          SubAccountParams l_subAccountParams =
              TestDBUtility.getSubAccountRow();
          l_subAccountParams.setInstitutionCode("6D");
          l_subAccountParams.setInstitutionId(63);
          l_subAccountParams.setSubAccountId(333812512203L);
          l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
          
          
          MainAccountParams l_mainAccountParams =
              TestDBUtility.getMainAccountRow();
          l_mainAccountParams.setAccountId(333812512203L);
          l_mainAccountParams.setInstitutionId(63);
        
          InstitutionParams l_institutionParams =
              TestDBUtility.getInstitutionRow();
          l_institutionParams.setInstitutionCode("0D");
          l_institutionParams.setInstitutionId(63);
          
          ProductParams l_productParams =
              TestDBUtility.getProductRow();
          l_productParams.setProductId(3304148080001L);
          l_productParams.setInstitutionCode("0");
          l_productParams.setProductType(ProductTypeEnum.EQUITY);
        
          MarketParams l_marketParams =
              TestDBUtility.getMarketRow();
          l_marketParams.setMarketCode("N1");
          l_marketParams.setMarketId(3303L);
          l_marketParams.setInstitutionCode("6D");
        
          BranchParams l_branchParams =
              TestDBUtility.getBranchRow();
          l_branchParams.setInstitutionCode("6D");
          l_branchParams.setInstitutionId(63);
          
          
          MutualFundTradedProductParams l_mfTradedProductParams =
              TestDBUtility.getMutualFundTradedProductRow();
          
        
          TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
          l_tradingTimeParams.setInstitutionCode("0D");
          l_tradingTimeParams.setBranchCode("123");
          l_tradingTimeParams.setTradingTimeType("01");
          l_tradingTimeParams.setBizDateType("1");
          l_tradingTimeParams.setMarketCode("N1");
          l_tradingTimeParams.setProductCode("0001000");
          
          MOCK_MANAGER.setIsMockUsed(true);
          TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
              "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
          "getAccountId",
              new Class[] {},new Long(333812512203L));
         
          List l_lis = new ArrayList();
          AssetParams l_assertParams = TestDBUtility.getAssetRow();
          l_assertParams.setTaxType(TaxTypeEnum.SPECIAL_WITHHOLD);
          MutualFundAssetImpl l_assert = new MutualFundAssetImpl(l_assertParams);
          l_lis.add(l_assert);
          
          TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
              "webbroker3.mf.WEB3MutualFundPositionManager",
          "getAssets",
              new Class[] {SubAccount.class, String.class},
              l_lis);
        
          Double l_dblQty = new Double("2.0");
          TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
              "webbroker3.mf.WEB3MutualFundPositionManager",
          "calcSellPossiblePositionQty",
              new Class[] {SubAccount.class, WEB3MutualFundProduct.class, String.class},
              l_dblQty);
          
          Double l_dblValue = new Double("2.0");
          TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
              "webbroker3.mf.WEB3MutualFundPositionManager",
          "calcMarketValue",
              new Class[] {SubAccount.class, WEB3MutualFundProduct.class, String.class},
              l_dblValue);
          
          Double l_dblProfitLoss = new Double("2.0");
          TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
              "webbroker3.mf.WEB3MutualFundPositionManager",
          "calcAppraisalProfitLoss",
              new Class[] {SubAccount.class, WEB3MutualFundProduct.class, String.class},
              l_dblProfitLoss);

          MutualFundProductParams l_mfProductParams = TestDBUtility.getMutualFundProductRow();
          l_mfProductParams.setProductId(1006169090018L);
          l_mfProductParams.setInstitutionCode("0D");
          l_mfProductParams.setProductCode("0001000");
          l_mfProductParams.setProductIssueCode("0");
          l_mfProductParams.setCurrencyCode("T0");
          l_mfProductParams.setFundType(MutualFundTypeEnum.FOREIGN_MMF);
          ProductParams l_product = TestDBUtility.getProductRow();
          l_product.setProductId(1006169090018L);
          try
          {
              MarketCalendarParams l_marketCalendarParams = new MarketCalendarParams();
              l_marketCalendarParams.setMarketId(3303L);
              l_marketCalendarParams.setTradeDate(CalendarUtils.clearTimeFields(GtlUtils.getSystemTimestamp()));
              l_marketCalendarParams.setHolidayFlag(BooleanEnum.TRUE);
              l_marketCalendarParams.setTradeOpenTime("090000");
              l_marketCalendarParams.setTradeCloseTime("150000");
              l_marketCalendarParams.setCreatedTimestamp(WEB3DateUtility.getDate("20040707","yyyyMMdd"));
              l_marketCalendarParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20040707","yyyyMMdd"));
              TestDBUtility.deleteAll(MarketCalendarRow.TYPE);
              TestDBUtility.insertWithDel(l_marketCalendarParams);
              
              TestDBUtility.deleteAll(SubAccountRow.TYPE);
              TestDBUtility.deleteAll(MainAccountRow.TYPE);
              TestDBUtility.deleteAll(InstitutionRow.TYPE);
              TestDBUtility.deleteAll(ProductRow.TYPE);
              TestDBUtility.deleteAll(MarketRow.TYPE);
              TestDBUtility.deleteAll(BranchRow.TYPE);
              TestDBUtility.deleteAll(TradingTimeRow.TYPE);
        
        
              TestDBUtility.insertWithDel(l_subAccountParams);
              TestDBUtility.insertWithDel(l_mainAccountParams);
              TestDBUtility.insertWithDel(l_institutionParams);
              TestDBUtility.insertWithDel(l_productParams);
              TestDBUtility.insertWithDel(l_marketParams);
              TestDBUtility.insertWithDel(l_branchParams);
        
              TestDBUtility.insertWithDel(l_mfProductParams);
              TestDBUtility.insertWithDel(l_product);
              TestDBUtility.insertWithDel(l_tradingTimeParams);
              TestDBUtility.insertWithDel(l_mfTradedProductParams);
              
              WEB3GentradeTradingTimeManagementForMock.mockSetTimestamp();
              
              WEB3MutualBalanceReferenceTotalRequestForMock l_request = 
                  new WEB3MutualBalanceReferenceTotalRequestForMock();
              
              WEB3MutualSortKey[] l_keys = new WEB3MutualSortKey[1];
              l_keys[0] = new WEB3MutualSortKey();
              l_keys[0].keyItem = "taxType";
              l_keys[0].ascDesc = "A";
              
              WEB3MutualBalanceReferenceServiceImpl l_impl = 
                  new WEB3MutualBalanceReferenceServiceImpl();
              l_response = l_impl.getBalanceReferenceTotal(l_request);
          
              assertEquals("2", l_response.frgnMmfTotalAsset);
          }
          catch (WEB3BaseException l_ex)
          {
              log.error(l_ex.getMessage(), l_ex);
              fail();
          } 
          catch (Exception l_ex)
          {
              log.error(l_ex.getMessage(), l_ex);
              fail();
          }
          
          log.exiting(STR_METHOD_NAME);
    } 
    
    
    public void testGetBalanceReferenceTotal02()
    {
          final String STR_METHOD_NAME = "testGetBalanceReferenceTotal02()";
          log.entering(STR_METHOD_NAME);
          WEB3MutualBalanceReferenceTotalResponse l_response = null;
          
          MOCK_MANAGER.setIsMockUsed(true);
          
        
        
          SubAccountParams l_subAccountParams =
              TestDBUtility.getSubAccountRow();
          l_subAccountParams.setInstitutionCode("6D");
          l_subAccountParams.setInstitutionId(63);
          l_subAccountParams.setSubAccountId(333812512203L);
          l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
          
          
          MainAccountParams l_mainAccountParams =
              TestDBUtility.getMainAccountRow();
          l_mainAccountParams.setAccountId(333812512203L);
          l_mainAccountParams.setInstitutionId(63);
        
          InstitutionParams l_institutionParams =
              TestDBUtility.getInstitutionRow();
          l_institutionParams.setInstitutionCode("0D");
          l_institutionParams.setInstitutionId(63);
          
          ProductParams l_productParams =
              TestDBUtility.getProductRow();
          l_productParams.setProductId(3304148080001L);
          l_productParams.setInstitutionCode("0");
          l_productParams.setProductType(ProductTypeEnum.EQUITY);
        
          MarketParams l_marketParams =
              TestDBUtility.getMarketRow();
          l_marketParams.setMarketCode("N1");
          l_marketParams.setMarketId(3303L);
          l_marketParams.setInstitutionCode("6D");
        
          BranchParams l_branchParams =
              TestDBUtility.getBranchRow();
          l_branchParams.setInstitutionCode("6D");
          l_branchParams.setInstitutionId(63);
          
          
          MutualFundTradedProductParams l_mfTradedProductParams =
              TestDBUtility.getMutualFundTradedProductRow();
          
        
          TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
          l_tradingTimeParams.setInstitutionCode("0D");
          l_tradingTimeParams.setBranchCode("123");
          l_tradingTimeParams.setTradingTimeType("01");
          l_tradingTimeParams.setBizDateType("1");
          l_tradingTimeParams.setMarketCode("N1");
          l_tradingTimeParams.setProductCode("0001000");
          
          MOCK_MANAGER.setIsMockUsed(true);
          TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
              "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
          "getAccountId",
              new Class[] {},new Long(333812512203L));
         
          List l_lis = new ArrayList();
          AssetParams l_assertParams = TestDBUtility.getAssetRow();
          l_assertParams.setTaxType(TaxTypeEnum.SPECIAL);
          MutualFundAssetImpl l_assert = new MutualFundAssetImpl(l_assertParams);
          l_lis.add(l_assert);
          
          TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
              "webbroker3.mf.WEB3MutualFundPositionManager",
          "getAssets",
              new Class[] {SubAccount.class, String.class},
              l_lis);
        
          Double l_dblQty = new Double("2.0");
          TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
              "webbroker3.mf.WEB3MutualFundPositionManager",
          "calcSellPossiblePositionQty",
              new Class[] {SubAccount.class, WEB3MutualFundProduct.class, String.class},
              l_dblQty);
          
          Double l_dblValue = new Double("2.0");
          TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
              "webbroker3.mf.WEB3MutualFundPositionManager",
          "calcMarketValue",
              new Class[] {SubAccount.class, WEB3MutualFundProduct.class, String.class},
              l_dblValue);
          
          Double l_dblProfitLoss = new Double("2.0");
          TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
              "webbroker3.mf.WEB3MutualFundPositionManager",
          "calcAppraisalProfitLoss",
              new Class[] {SubAccount.class, WEB3MutualFundProduct.class, String.class},
              l_dblProfitLoss);

          MutualFundProductParams l_mfProductParams = TestDBUtility.getMutualFundProductRow();
          l_mfProductParams.setProductId(1006169090018L);
          l_mfProductParams.setInstitutionCode("0D");
          l_mfProductParams.setProductCode("0001000");
          l_mfProductParams.setProductIssueCode("0");
          l_mfProductParams.setCurrencyCode("T0");
          l_mfProductParams.setFundType(MutualFundTypeEnum.FOREIGN);
          ProductParams l_product = TestDBUtility.getProductRow();
          l_product.setProductId(1006169090018L);
          try
          {
              MarketCalendarParams l_marketCalendarParams = new MarketCalendarParams();
              l_marketCalendarParams.setMarketId(3303L);
              l_marketCalendarParams.setTradeDate(CalendarUtils.clearTimeFields(GtlUtils.getSystemTimestamp()));
              l_marketCalendarParams.setHolidayFlag(BooleanEnum.TRUE);
              l_marketCalendarParams.setTradeOpenTime("090000");
              l_marketCalendarParams.setTradeCloseTime("150000");
              l_marketCalendarParams.setCreatedTimestamp(WEB3DateUtility.getDate("20040707","yyyyMMdd"));
              l_marketCalendarParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20040707","yyyyMMdd"));
              TestDBUtility.deleteAll(MarketCalendarRow.TYPE);
              TestDBUtility.insertWithDel(l_marketCalendarParams);
              
              TestDBUtility.deleteAll(SubAccountRow.TYPE);
              TestDBUtility.deleteAll(MainAccountRow.TYPE);
              TestDBUtility.deleteAll(InstitutionRow.TYPE);
              TestDBUtility.deleteAll(ProductRow.TYPE);
              TestDBUtility.deleteAll(MarketRow.TYPE);
              TestDBUtility.deleteAll(BranchRow.TYPE);
              TestDBUtility.deleteAll(TradingTimeRow.TYPE);
        
        
              TestDBUtility.insertWithDel(l_subAccountParams);
              TestDBUtility.insertWithDel(l_mainAccountParams);
              TestDBUtility.insertWithDel(l_institutionParams);
              TestDBUtility.insertWithDel(l_productParams);
              TestDBUtility.insertWithDel(l_marketParams);
              TestDBUtility.insertWithDel(l_branchParams);
        
              TestDBUtility.insertWithDel(l_mfProductParams);
              TestDBUtility.insertWithDel(l_product);
              TestDBUtility.insertWithDel(l_tradingTimeParams);
              TestDBUtility.insertWithDel(l_mfTradedProductParams);
              
              WEB3GentradeTradingTimeManagementForMock.mockSetTimestamp();
              
              WEB3MutualBalanceReferenceTotalRequestForMock l_request = 
                  new WEB3MutualBalanceReferenceTotalRequestForMock();
              
              WEB3MutualSortKey[] l_keys = new WEB3MutualSortKey[1];
              l_keys[0] = new WEB3MutualSortKey();
              l_keys[0].keyItem = "taxType";
              l_keys[0].ascDesc = "A";
              
              WEB3MutualBalanceReferenceServiceImpl l_impl = 
                  new WEB3MutualBalanceReferenceServiceImpl();
              l_response = l_impl.getBalanceReferenceTotal(l_request);
          
              assertEquals("0", l_response.frgnMmfTotalAsset);
          }
          catch (WEB3BaseException l_ex)
          {
              log.error(l_ex.getMessage(), l_ex);
              fail();
          } 
          catch (Exception l_ex)
          {
              log.error(l_ex.getMessage(), l_ex);
              fail();
          }
          
          log.exiting(STR_METHOD_NAME);
    }
    
    public void testGetBalanceReferenceTotal03()
    {
          final String STR_METHOD_NAME = "testGetBalanceReferenceTotal03()";
          log.entering(STR_METHOD_NAME);
          WEB3MutualBalanceReferenceTotalResponse l_response = null;
          
          MOCK_MANAGER.setIsMockUsed(true);
          
        
        
          SubAccountParams l_subAccountParams =
              TestDBUtility.getSubAccountRow();
          l_subAccountParams.setInstitutionCode("6D");
          l_subAccountParams.setInstitutionId(63);
          l_subAccountParams.setSubAccountId(333812512203L);
          l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
          
          
          MainAccountParams l_mainAccountParams =
              TestDBUtility.getMainAccountRow();
          l_mainAccountParams.setAccountId(333812512203L);
          l_mainAccountParams.setInstitutionId(63);
        
          InstitutionParams l_institutionParams =
              TestDBUtility.getInstitutionRow();
          l_institutionParams.setInstitutionCode("0D");
          l_institutionParams.setInstitutionId(63);
          
          ProductParams l_productParams =
              TestDBUtility.getProductRow();
          l_productParams.setProductId(3304148080001L);
          l_productParams.setInstitutionCode("0");
          l_productParams.setProductType(ProductTypeEnum.EQUITY);
        
          MarketParams l_marketParams =
              TestDBUtility.getMarketRow();
          l_marketParams.setMarketCode("N1");
          l_marketParams.setMarketId(3303L);
          l_marketParams.setInstitutionCode("6D");
        
          BranchParams l_branchParams =
              TestDBUtility.getBranchRow();
          l_branchParams.setInstitutionCode("6D");
          l_branchParams.setInstitutionId(63);
          
          
          MutualFundTradedProductParams l_mfTradedProductParams =
              TestDBUtility.getMutualFundTradedProductRow();
          
        
          TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
          l_tradingTimeParams.setInstitutionCode("0D");
          l_tradingTimeParams.setBranchCode("123");
          l_tradingTimeParams.setTradingTimeType("01");
          l_tradingTimeParams.setBizDateType("1");
          l_tradingTimeParams.setMarketCode("N1");
          l_tradingTimeParams.setProductCode("0001000");
          
          MOCK_MANAGER.setIsMockUsed(true);
          TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
              "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
          "getAccountId",
              new Class[] {},new Long(333812512203L));
         
          List l_lis = new ArrayList();
          AssetParams l_assertParams = TestDBUtility.getAssetRow();
          l_assertParams.setTaxType(TaxTypeEnum.SPECIAL);
          MutualFundAssetImpl l_assert = new MutualFundAssetImpl(l_assertParams);
          l_lis.add(l_assert);
          
          TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
              "webbroker3.mf.WEB3MutualFundPositionManager",
          "getAssets",
              new Class[] {SubAccount.class, String.class},
              l_lis);
        
          Double l_dblQty = new Double("2.0");
          TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
              "webbroker3.mf.WEB3MutualFundPositionManager",
          "calcSellPossiblePositionQty",
              new Class[] {SubAccount.class, WEB3MutualFundProduct.class, String.class},
              l_dblQty);
          
          Double l_dblValue = new Double("2.0");
          TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
              "webbroker3.mf.WEB3MutualFundPositionManager",
          "calcMarketValue",
              new Class[] {SubAccount.class, WEB3MutualFundProduct.class, String.class},
              l_dblValue);
          
          Double l_dblProfitLoss = new Double("2.0");
          TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
              "webbroker3.mf.WEB3MutualFundPositionManager",
          "calcAppraisalProfitLoss",
              new Class[] {SubAccount.class, WEB3MutualFundProduct.class, String.class},
              l_dblProfitLoss);

          MutualFundProductParams l_mfProductParams = TestDBUtility.getMutualFundProductRow();
          l_mfProductParams.setProductId(1006169090018L);
          l_mfProductParams.setInstitutionCode("0D");
          l_mfProductParams.setProductCode("0001000");
          l_mfProductParams.setProductIssueCode("0");
          l_mfProductParams.setCurrencyCode("T0");
          l_mfProductParams.setFundType(MutualFundTypeEnum.FOREIGN);
          ProductParams l_product = TestDBUtility.getProductRow();
          l_product.setProductId(1006169090018L);
          try
          {
              MarketCalendarParams l_marketCalendarParams = new MarketCalendarParams();
              l_marketCalendarParams.setMarketId(3303L);
              l_marketCalendarParams.setTradeDate(CalendarUtils.clearTimeFields(GtlUtils.getSystemTimestamp()));
              l_marketCalendarParams.setHolidayFlag(BooleanEnum.TRUE);
              l_marketCalendarParams.setTradeOpenTime("090000");
              l_marketCalendarParams.setTradeCloseTime("150000");
              l_marketCalendarParams.setCreatedTimestamp(WEB3DateUtility.getDate("20040707","yyyyMMdd"));
              l_marketCalendarParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20040707","yyyyMMdd"));
              TestDBUtility.deleteAll(MarketCalendarRow.TYPE);
              TestDBUtility.insertWithDel(l_marketCalendarParams);
              
              TestDBUtility.deleteAll(SubAccountRow.TYPE);
              TestDBUtility.deleteAll(MainAccountRow.TYPE);
              TestDBUtility.deleteAll(InstitutionRow.TYPE);
              TestDBUtility.deleteAll(ProductRow.TYPE);
              TestDBUtility.deleteAll(MarketRow.TYPE);
              TestDBUtility.deleteAll(BranchRow.TYPE);
              TestDBUtility.deleteAll(TradingTimeRow.TYPE);
        
        
              TestDBUtility.insertWithDel(l_subAccountParams);
              TestDBUtility.insertWithDel(l_mainAccountParams);
              TestDBUtility.insertWithDel(l_institutionParams);
              TestDBUtility.insertWithDel(l_productParams);
              TestDBUtility.insertWithDel(l_marketParams);
              TestDBUtility.insertWithDel(l_branchParams);
        
              TestDBUtility.insertWithDel(l_mfProductParams);
              TestDBUtility.insertWithDel(l_product);
              TestDBUtility.insertWithDel(l_tradingTimeParams);
              TestDBUtility.insertWithDel(l_mfTradedProductParams);
              
              WEB3GentradeTradingTimeManagementForMock.mockSetTimestamp();
              
              WEB3MutualBalanceReferenceTotalRequestForMock l_request = 
                  new WEB3MutualBalanceReferenceTotalRequestForMock();
              
              WEB3MutualSortKey[] l_keys = new WEB3MutualSortKey[1];
              l_keys[0] = new WEB3MutualSortKey();
              l_keys[0].keyItem = "taxType";
              l_keys[0].ascDesc = "A";
              
              WEB3MutualBalanceReferenceServiceImpl l_impl = 
                  new WEB3MutualBalanceReferenceServiceImpl();
              l_response = l_impl.getBalanceReferenceTotal(l_request);
          
              assertEquals("0", l_response.frgnMmfTotalAsset);
          }
          catch (WEB3BaseException l_ex)
          {
              log.error(l_ex.getMessage(), l_ex);
              fail();
          } 
          catch (Exception l_ex)
          {
              log.error(l_ex.getMessage(), l_ex);
              fail();
          }
          log.exiting(STR_METHOD_NAME);
    }
    
    
    //モデル552，U02995
    public void testGetBalanceReferenceC1()
    {
        final String STR_METHOD_NAME = "testGetBalanceReferenceC1()";
        log.entering(STR_METHOD_NAME);
        WEB3MutualBalanceReferenceResponse l_response = null;
        
        MOCK_MANAGER.setIsMockUsed(true);
        


        SubAccountParams l_subAccountParams =
            TestDBUtility.getSubAccountRow();
        l_subAccountParams.setInstitutionCode("6D");
        l_subAccountParams.setInstitutionId(63);
        l_subAccountParams.setSubAccountId(333812512203L);
        l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
        
        
        MainAccountParams l_mainAccountParams =
            TestDBUtility.getMainAccountRow();
        l_mainAccountParams.setAccountId(333812512203L);
        l_mainAccountParams.setInstitutionId(63);

        InstitutionParams l_institutionParams =
            TestDBUtility.getInstitutionRow();
        l_institutionParams.setInstitutionCode("0D");
        l_institutionParams.setInstitutionId(63);
        
        ProductParams l_productParams =
            TestDBUtility.getProductRow();
        l_productParams.setProductId(3304148080001L);
        l_productParams.setInstitutionCode("0");
        l_productParams.setProductType(ProductTypeEnum.EQUITY);

        MarketParams l_marketParams =
            TestDBUtility.getMarketRow();
        l_marketParams.setMarketCode("N1");
        l_marketParams.setMarketId(3303L);
        l_marketParams.setInstitutionCode("6D");

        BranchParams l_branchParams =
            TestDBUtility.getBranchRow();
        l_branchParams.setInstitutionCode("6D");
        l_branchParams.setInstitutionId(63);
        
        
        MutualFundTradedProductParams l_mfTradedProductParams =
            TestDBUtility.getMutualFundTradedProductRow();
        

        TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
        l_tradingTimeParams.setInstitutionCode("0D");
        l_tradingTimeParams.setBranchCode("123");
        l_tradingTimeParams.setTradingTimeType("01");
        l_tradingTimeParams.setBizDateType("1");
        l_tradingTimeParams.setMarketCode("N1");
        l_tradingTimeParams.setProductCode("0001000");
        
        MOCK_MANAGER.setIsMockUsed(true);
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
            "getAccountId",
            new Class[] {},new Long(333812512203L));
       
        List l_lis = new ArrayList();
        AssetParams l_assetParams = TestDBUtility.getAssetRow();
        l_assetParams.setTaxType(TaxTypeEnum.NORMAL);
        l_assetParams.setAccountId(333812512203L);
        l_assetParams.setSubAccountId(333812512203L);
        
        MutualFundAssetImpl l_assert = new MutualFundAssetImpl(l_assetParams);
        l_lis.add(l_assert);
        
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "webbroker3.mf.WEB3MutualFundPositionManager",
            "getAssets",
            new Class[] {SubAccount.class, String.class},
            l_lis);

        Double l_dblQty = new Double("2.0");
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "webbroker3.mf.WEB3MutualFundPositionManager",
            "calcSellPossiblePositionQty",
            new Class[] {SubAccount.class, WEB3MutualFundProduct.class, String.class},
            l_dblQty);
        
        Double l_dblValue = new Double("2.0");
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "webbroker3.mf.WEB3MutualFundPositionManager",
            "calcMarketValue",
            new Class[] {SubAccount.class, WEB3MutualFundProduct.class, String.class},
            l_dblValue);
        
        Double l_dblProfitLoss = new Double("2.0");
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "webbroker3.mf.WEB3MutualFundPositionManager",
            "calcAppraisalProfitLoss",
            new Class[] {SubAccount.class, WEB3MutualFundProduct.class, String.class},
            l_dblProfitLoss);

        Long l_lngDist = new Long("2");
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "webbroker3.mf.WEB3MutualFundPositionManager",
            "getUnreceiveDist",
            new Class[] {String.class, WEB3MutualFundProduct.class},
            l_lngDist);
        
        
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "webbroker3.mf.WEB3MutualFundProduct",
            "isAcquiredDeregExistence",
            new Class[] {},
            Boolean.TRUE);
        
        
        MutualFundProductParams l_mfProductParams = TestDBUtility.getMutualFundProductRow();
        l_mfProductParams.setProductId(1006169090018L);
        l_mfProductParams.setInstitutionCode("0D");
        l_mfProductParams.setProductCode("0001000");
        l_mfProductParams.setProductIssueCode("0");
        l_mfProductParams.setCurrencyCode("T0");
        l_mfProductParams.setFundType(MutualFundTypeEnum.FOREIGN);//投信銘柄.is外貨MMF()==falseの場合
        l_mfProductParams.setSellConstantValue(1111);
        l_mfProductParams.setSwtUnitQty(2222);
        l_mfProductParams.setSwtMinQty(3333);
        l_mfProductParams.setSwtUnitAmt(4444);
        l_mfProductParams.setSwtMinAmt(5555);
        ProductParams l_product = TestDBUtility.getProductRow();
        l_product.setProductId(1006169090018L);
        try
        {
            MarketCalendarParams l_marketCalendarParams = new MarketCalendarParams();
            l_marketCalendarParams.setMarketId(3303L);
            l_marketCalendarParams.setTradeDate(CalendarUtils.clearTimeFields(GtlUtils.getSystemTimestamp()));
            l_marketCalendarParams.setHolidayFlag(BooleanEnum.TRUE);
            l_marketCalendarParams.setTradeOpenTime("090000");
            l_marketCalendarParams.setTradeCloseTime("150000");
            l_marketCalendarParams.setCreatedTimestamp(WEB3DateUtility.getDate("20040707","yyyyMMdd"));
            l_marketCalendarParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20040707","yyyyMMdd"));
            TestDBUtility.deleteAll(MarketCalendarRow.TYPE);
            TestDBUtility.insertWithDel(l_marketCalendarParams);
            
            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            TestDBUtility.deleteAll(InstitutionRow.TYPE);
            TestDBUtility.deleteAll(ProductRow.TYPE);
            TestDBUtility.deleteAll(MarketRow.TYPE);
            TestDBUtility.deleteAll(BranchRow.TYPE);
            TestDBUtility.deleteAll(TradingTimeRow.TYPE);
            TestDBUtility.deleteAll(AssetRow.TYPE);

            TestDBUtility.insertWithDel(l_subAccountParams);
            TestDBUtility.insertWithDel(l_mainAccountParams);
            TestDBUtility.insertWithDel(l_institutionParams);
            TestDBUtility.insertWithDel(l_productParams);
            TestDBUtility.insertWithDel(l_marketParams);
            TestDBUtility.insertWithDel(l_branchParams);
            TestDBUtility.insertWithDel(l_assetParams);

            TestDBUtility.insertWithDel(l_mfProductParams);
            TestDBUtility.insertWithDel(l_product);
            TestDBUtility.insertWithDel(l_tradingTimeParams);
            TestDBUtility.insertWithDel(l_mfTradedProductParams);
            
            WEB3GentradeTradingTimeManagementForMock.mockSetTimestamp();
            
            String l_strDate = "174120";
            WEB3MutualFundTradingTimeManagementForMock.mockGetOrderCloseTime(l_strDate);
            
            WEB3MutualBalanceReferenceRequestForMock l_request = 
                new WEB3MutualBalanceReferenceRequestForMock();
            
            WEB3MutualSortKey[] l_keys = new WEB3MutualSortKey[1];
            l_keys[0] = new WEB3MutualSortKey();
            l_keys[0].keyItem = "taxType";
            l_keys[0].ascDesc = "A";
            l_request.sortKeys = l_keys;
            l_request.pageIndex = "1";
            l_request.pageSize = "1";
            
            WEB3MutualBalanceReferenceServiceImpl l_impl = 
                new WEB3MutualBalanceReferenceServiceImpl();
            l_response = l_impl.getBalanceReference(l_request);
          
            assertEquals("1111", l_response.balanceReference[0].constantValue);
            assertEquals("2222", l_response.balanceReference[0].switchingUnitQty);   
            assertEquals("3333", l_response.balanceReference[0].switchingMinQty);
            assertEquals("4444", l_response.balanceReference[0].switchingUnitAmt);
            assertEquals("5555", l_response.balanceReference[0].switchingMinAmt);
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        } 
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testGetBalanceReferenceC2()
    {
        final String STR_METHOD_NAME = "testGetBalanceReferenceC2()";
        log.entering(STR_METHOD_NAME);
        WEB3MutualBalanceReferenceResponse l_response = null;
        
        MOCK_MANAGER.setIsMockUsed(true);
        


        SubAccountParams l_subAccountParams =
            TestDBUtility.getSubAccountRow();
        l_subAccountParams.setInstitutionCode("6D");
        l_subAccountParams.setInstitutionId(63);
        l_subAccountParams.setSubAccountId(333812512203L);
        l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
        
        
        MainAccountParams l_mainAccountParams =
            TestDBUtility.getMainAccountRow();
        l_mainAccountParams.setAccountId(333812512203L);
        l_mainAccountParams.setInstitutionId(63);

        InstitutionParams l_institutionParams =
            TestDBUtility.getInstitutionRow();
        l_institutionParams.setInstitutionCode("0D");
        l_institutionParams.setInstitutionId(63);
        
        ProductParams l_productParams =
            TestDBUtility.getProductRow();
        l_productParams.setProductId(3304148080001L);
        l_productParams.setInstitutionCode("0");
        l_productParams.setProductType(ProductTypeEnum.EQUITY);

        MarketParams l_marketParams =
            TestDBUtility.getMarketRow();
        l_marketParams.setMarketCode("N1");
        l_marketParams.setMarketId(3303L);
        l_marketParams.setInstitutionCode("6D");

        BranchParams l_branchParams =
            TestDBUtility.getBranchRow();
        l_branchParams.setInstitutionCode("6D");
        l_branchParams.setInstitutionId(63);
        
        
        MutualFundTradedProductParams l_mfTradedProductParams =
            TestDBUtility.getMutualFundTradedProductRow();
        

        TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
        l_tradingTimeParams.setInstitutionCode("0D");
        l_tradingTimeParams.setBranchCode("123");
        l_tradingTimeParams.setTradingTimeType("01");
        l_tradingTimeParams.setBizDateType("1");
        l_tradingTimeParams.setMarketCode("N1");
        l_tradingTimeParams.setProductCode("0001000");
        
        MOCK_MANAGER.setIsMockUsed(true);
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
            "getAccountId",
            new Class[] {},new Long(333812512203L));
       
        List l_lis = new ArrayList();
        AssetParams l_assetParams = TestDBUtility.getAssetRow();
        l_assetParams.setTaxType(TaxTypeEnum.NORMAL);
        l_assetParams.setAccountId(333812512203L);
        l_assetParams.setSubAccountId(333812512203L);
        
        MutualFundAssetImpl l_assert = new MutualFundAssetImpl(l_assetParams);
        l_lis.add(l_assert);
        
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "webbroker3.mf.WEB3MutualFundPositionManager",
            "getAssets",
            new Class[] {SubAccount.class, String.class},
            l_lis);

        Double l_dblQty = new Double("2.0");
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "webbroker3.mf.WEB3MutualFundPositionManager",
            "calcSellPossiblePositionQty",
            new Class[] {SubAccount.class, WEB3MutualFundProduct.class, String.class},
            l_dblQty);
        
        Double l_dblValue = new Double("2.0");
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "webbroker3.mf.WEB3MutualFundPositionManager",
            "calcMarketValue",
            new Class[] {SubAccount.class, WEB3MutualFundProduct.class, String.class},
            l_dblValue);
        
        Double l_dblProfitLoss = new Double("2.0");
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "webbroker3.mf.WEB3MutualFundPositionManager",
            "calcAppraisalProfitLoss",
            new Class[] {SubAccount.class, WEB3MutualFundProduct.class, String.class},
            l_dblProfitLoss);

        Long l_lngDist = new Long("2");
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "webbroker3.mf.WEB3MutualFundPositionManager",
            "getUnreceiveDist",
            new Class[] {String.class, WEB3MutualFundProduct.class},
            l_lngDist);
        
        
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "webbroker3.mf.WEB3MutualFundProduct",
            "isAcquiredDeregExistence",
            new Class[] {},
            Boolean.TRUE);
        
        
        MutualFundProductParams l_mfProductParams = TestDBUtility.getMutualFundProductRow();
        l_mfProductParams.setProductId(1006169090018L);
        l_mfProductParams.setInstitutionCode("0D");
        l_mfProductParams.setProductCode("0001000");
        l_mfProductParams.setProductIssueCode("0");
        l_mfProductParams.setCurrencyCode("T0");
        l_mfProductParams.setFundType(MutualFundTypeEnum.FOREIGN_MMF);//投信銘柄.is外貨MMF()==trueの場合
        l_mfProductParams.setSellConstantValue(1111);
        l_mfProductParams.setSwtUnitQty(2222);
        l_mfProductParams.setSwtMinQty(3333);
        l_mfProductParams.setSwtUnitAmt(4444);
        l_mfProductParams.setSwtMinAmt(5555);
        l_mfProductParams.setSellMinAmt(100);
        ProductParams l_product = TestDBUtility.getProductRow();
        l_product.setProductId(1006169090018L);
        try
        {
            MarketCalendarParams l_marketCalendarParams = new MarketCalendarParams();
            l_marketCalendarParams.setMarketId(3303L);
            l_marketCalendarParams.setTradeDate(CalendarUtils.clearTimeFields(GtlUtils.getSystemTimestamp()));
            l_marketCalendarParams.setHolidayFlag(BooleanEnum.TRUE);
            l_marketCalendarParams.setTradeOpenTime("090000");
            l_marketCalendarParams.setTradeCloseTime("150000");
            l_marketCalendarParams.setCreatedTimestamp(WEB3DateUtility.getDate("20040707","yyyyMMdd"));
            l_marketCalendarParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20040707","yyyyMMdd"));
            TestDBUtility.deleteAll(MarketCalendarRow.TYPE);
            TestDBUtility.insertWithDel(l_marketCalendarParams);
            
            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            TestDBUtility.deleteAll(InstitutionRow.TYPE);
            TestDBUtility.deleteAll(ProductRow.TYPE);
            TestDBUtility.deleteAll(MarketRow.TYPE);
            TestDBUtility.deleteAll(BranchRow.TYPE);
            TestDBUtility.deleteAll(TradingTimeRow.TYPE);
            TestDBUtility.deleteAll(AssetRow.TYPE);

            TestDBUtility.insertWithDel(l_subAccountParams);
            TestDBUtility.insertWithDel(l_mainAccountParams);
            TestDBUtility.insertWithDel(l_institutionParams);
            TestDBUtility.insertWithDel(l_productParams);
            TestDBUtility.insertWithDel(l_marketParams);
            TestDBUtility.insertWithDel(l_branchParams);
            TestDBUtility.insertWithDel(l_assetParams);

            TestDBUtility.insertWithDel(l_mfProductParams);
            TestDBUtility.insertWithDel(l_product);
            TestDBUtility.insertWithDel(l_tradingTimeParams);
            TestDBUtility.insertWithDel(l_mfTradedProductParams);
            
            WEB3GentradeTradingTimeManagementForMock.mockSetTimestamp();
            
            String l_strDate = "174120";
            WEB3MutualFundTradingTimeManagementForMock.mockGetOrderCloseTime(l_strDate);
            
            WEB3MutualBalanceReferenceRequestForMock l_request = 
                new WEB3MutualBalanceReferenceRequestForMock();
            
            WEB3MutualSortKey[] l_keys = new WEB3MutualSortKey[1];
            l_keys[0] = new WEB3MutualSortKey();
            l_keys[0].keyItem = "taxType";
            l_keys[0].ascDesc = "A";
            l_request.sortKeys = l_keys;
            l_request.pageIndex = "1";
            l_request.pageSize = "1";
            
            WEB3MutualBalanceReferenceServiceImpl l_impl = 
                new WEB3MutualBalanceReferenceServiceImpl();
            l_response = l_impl.getBalanceReference(l_request);
            assertEquals("100", l_response.balanceReference[0].sellMinAmt);
            assertNull(l_response.balanceReference[0].constantValue);
            assertNull(l_response.balanceReference[0].switchingUnitQty);   
            assertNull(l_response.balanceReference[0].switchingMinQty);
            assertNull(l_response.balanceReference[0].switchingUnitAmt);
            assertNull(l_response.balanceReference[0].switchingMinAmt);
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        } 
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testGetBalanceReferenceC3()
    {
        final String STR_METHOD_NAME = "testGetBalanceReferenceC3()";
        log.entering(STR_METHOD_NAME);
        WEB3MutualBalanceReferenceResponse l_response = null;
        
        MOCK_MANAGER.setIsMockUsed(true);
        


        SubAccountParams l_subAccountParams =
            TestDBUtility.getSubAccountRow();
        l_subAccountParams.setInstitutionCode("6D");
        l_subAccountParams.setInstitutionId(63);
        l_subAccountParams.setSubAccountId(333812512203L);
        l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
        
        
        MainAccountParams l_mainAccountParams =
            TestDBUtility.getMainAccountRow();
        l_mainAccountParams.setAccountId(333812512203L);
        l_mainAccountParams.setInstitutionId(63);

        InstitutionParams l_institutionParams =
            TestDBUtility.getInstitutionRow();
        l_institutionParams.setInstitutionCode("0D");
        l_institutionParams.setInstitutionId(63);
        
        ProductParams l_productParams =
            TestDBUtility.getProductRow();
        l_productParams.setProductId(3304148080001L);
        l_productParams.setInstitutionCode("0");
        l_productParams.setProductType(ProductTypeEnum.EQUITY);

        MarketParams l_marketParams =
            TestDBUtility.getMarketRow();
        l_marketParams.setMarketCode("N1");
        l_marketParams.setMarketId(3303L);
        l_marketParams.setInstitutionCode("6D");

        BranchParams l_branchParams =
            TestDBUtility.getBranchRow();
        l_branchParams.setInstitutionCode("6D");
        l_branchParams.setInstitutionId(63);
        
        
        MutualFundTradedProductParams l_mfTradedProductParams =
            TestDBUtility.getMutualFundTradedProductRow();
        

        TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
        l_tradingTimeParams.setInstitutionCode("0D");
        l_tradingTimeParams.setBranchCode("123");
        l_tradingTimeParams.setTradingTimeType("01");
        l_tradingTimeParams.setBizDateType("1");
        l_tradingTimeParams.setMarketCode("N1");
        l_tradingTimeParams.setProductCode("0001000");
        
        MOCK_MANAGER.setIsMockUsed(true);
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
            "getAccountId",
            new Class[] {},new Long(333812512203L));
       
        List l_lis = new ArrayList();
        AssetParams l_assetParams = TestDBUtility.getAssetRow();
        l_assetParams.setTaxType(TaxTypeEnum.NORMAL);
        l_assetParams.setAccountId(333812512203L);
        l_assetParams.setSubAccountId(333812512203L);
        
        MutualFundAssetImpl l_assert = new MutualFundAssetImpl(l_assetParams);
        l_lis.add(l_assert);
        
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "webbroker3.mf.WEB3MutualFundPositionManager",
            "getAssets",
            new Class[] {SubAccount.class, String.class},
            l_lis);

        Double l_dblQty = new Double("2.0");
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "webbroker3.mf.WEB3MutualFundPositionManager",
            "calcSellPossiblePositionQty",
            new Class[] {SubAccount.class, WEB3MutualFundProduct.class, String.class},
            l_dblQty);
        
        Double l_dblValue = new Double("2.0");
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "webbroker3.mf.WEB3MutualFundPositionManager",
            "calcMarketValue",
            new Class[] {SubAccount.class, WEB3MutualFundProduct.class, String.class},
            l_dblValue);
        
        Double l_dblProfitLoss = new Double("2.0");
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "webbroker3.mf.WEB3MutualFundPositionManager",
            "calcAppraisalProfitLoss",
            new Class[] {SubAccount.class, WEB3MutualFundProduct.class, String.class},
            l_dblProfitLoss);

        Long l_lngDist = new Long("2");
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "webbroker3.mf.WEB3MutualFundPositionManager",
            "getUnreceiveDist",
            new Class[] {String.class, WEB3MutualFundProduct.class},
            l_lngDist);
        
        
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "webbroker3.mf.WEB3MutualFundProduct",
            "isAcquiredDeregExistence",
            new Class[] {},
            Boolean.TRUE);
        
        
        MutualFundProductParams l_mfProductParams = TestDBUtility.getMutualFundProductRow();
        l_mfProductParams.setProductId(1006169090018L);
        l_mfProductParams.setInstitutionCode("0D");
        l_mfProductParams.setProductCode("0001000");
        l_mfProductParams.setProductIssueCode("0");
        l_mfProductParams.setCurrencyCode("T0");
        l_mfProductParams.setFundType(MutualFundTypeEnum.FOREIGN);//拡張投信銘柄.is外国投信()
        l_mfProductParams.setSellConstantValue(1111);
        l_mfProductParams.setSwtUnitQty(2222);
        l_mfProductParams.setSwtMinQty(3333);
        l_mfProductParams.setSwtUnitAmt(4444);
        l_mfProductParams.setSwtMinAmt(5555);
        l_mfProductParams.setSellMinAmt(100);
        l_mfProductParams.setSellSwtStartDate(WEB3DateUtility.getDate("20070101","yyyyMMdd"));
        l_mfProductParams.setSellSwtEndDate(WEB3DateUtility.getDate("20070501","yyyyMMdd"));
        l_mfProductParams.setSystemHandlingDiv(WEB3SystemHandlingDivDef.WEBBROKER3_TREAT_IT_IN);
        ProductParams l_product = TestDBUtility.getProductRow();
        l_product.setProductId(1006169090018L);
        try
        {
            MarketCalendarParams l_marketCalendarParams = new MarketCalendarParams();
            l_marketCalendarParams.setMarketId(3303L);
            l_marketCalendarParams.setTradeDate(CalendarUtils.clearTimeFields(GtlUtils.getSystemTimestamp()));
            l_marketCalendarParams.setHolidayFlag(BooleanEnum.TRUE);
            l_marketCalendarParams.setTradeOpenTime("090000");
            l_marketCalendarParams.setTradeCloseTime("150000");
            l_marketCalendarParams.setCreatedTimestamp(WEB3DateUtility.getDate("20040707","yyyyMMdd"));
            l_marketCalendarParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20040707","yyyyMMdd"));
            TestDBUtility.deleteAll(MarketCalendarRow.TYPE);
            TestDBUtility.insertWithDel(l_marketCalendarParams);
            
            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            TestDBUtility.deleteAll(InstitutionRow.TYPE);
            TestDBUtility.deleteAll(ProductRow.TYPE);
            TestDBUtility.deleteAll(MarketRow.TYPE);
            TestDBUtility.deleteAll(BranchRow.TYPE);
            TestDBUtility.deleteAll(TradingTimeRow.TYPE);
            TestDBUtility.deleteAll(AssetRow.TYPE);

            TestDBUtility.insertWithDel(l_subAccountParams);
            TestDBUtility.insertWithDel(l_mainAccountParams);
            TestDBUtility.insertWithDel(l_institutionParams);
            TestDBUtility.insertWithDel(l_productParams);
            TestDBUtility.insertWithDel(l_marketParams);
            TestDBUtility.insertWithDel(l_branchParams);
            TestDBUtility.insertWithDel(l_assetParams);

            TestDBUtility.insertWithDel(l_mfProductParams);
            TestDBUtility.insertWithDel(l_product);
            TestDBUtility.insertWithDel(l_tradingTimeParams);
            TestDBUtility.insertWithDel(l_mfTradedProductParams);
            
            WEB3GentradeTradingTimeManagementForMock.mockSetTimestamp();
            
            String l_strDate = "174120";
            WEB3MutualFundTradingTimeManagementForMock.mockGetOrderCloseTime(l_strDate);
            
            WEB3MutualBalanceReferenceRequestForMock l_request = 
                new WEB3MutualBalanceReferenceRequestForMock();
            
            WEB3MutualSortKey[] l_keys = new WEB3MutualSortKey[1];
            l_keys[0] = new WEB3MutualSortKey();
            l_keys[0].keyItem = "taxType";
            l_keys[0].ascDesc = "A";
            l_request.sortKeys = l_keys;
            l_request.pageIndex = "1";
            l_request.pageSize = "1";
            
            WEB3MutualBalanceReferenceServiceImpl l_impl = 
                new WEB3MutualBalanceReferenceServiceImpl();
            l_response = l_impl.getBalanceReference(l_request);
            assertEquals("3", l_response.balanceReference[0].sellPossType);
            assertEquals("3",l_response.balanceReference[0].buyPossType);
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        } 
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testGetBalanceReferenceC4()
    {
        final String STR_METHOD_NAME = "testGetBalanceReferenceC4()";
        log.entering(STR_METHOD_NAME);
        WEB3MutualBalanceReferenceResponse l_response = null;
        
        MOCK_MANAGER.setIsMockUsed(true);
        


        SubAccountParams l_subAccountParams =
            TestDBUtility.getSubAccountRow();
        l_subAccountParams.setInstitutionCode("6D");
        l_subAccountParams.setInstitutionId(63);
        l_subAccountParams.setSubAccountId(333812512203L);
        l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
        
        
        MainAccountParams l_mainAccountParams =
            TestDBUtility.getMainAccountRow();
        l_mainAccountParams.setAccountId(333812512203L);
        l_mainAccountParams.setInstitutionId(63);

        InstitutionParams l_institutionParams =
            TestDBUtility.getInstitutionRow();
        l_institutionParams.setInstitutionCode("0D");
        l_institutionParams.setInstitutionId(63);
        
        ProductParams l_productParams =
            TestDBUtility.getProductRow();
        l_productParams.setProductId(3304148080001L);
        l_productParams.setInstitutionCode("0");
        l_productParams.setProductType(ProductTypeEnum.EQUITY);

        MarketParams l_marketParams =
            TestDBUtility.getMarketRow();
        l_marketParams.setMarketCode("N1");
        l_marketParams.setMarketId(3303L);
        l_marketParams.setInstitutionCode("6D");

        BranchParams l_branchParams =
            TestDBUtility.getBranchRow();
        l_branchParams.setInstitutionCode("6D");
        l_branchParams.setInstitutionId(63);
        
        
        MutualFundTradedProductParams l_mfTradedProductParams =
            TestDBUtility.getMutualFundTradedProductRow();
        

        TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
        l_tradingTimeParams.setInstitutionCode("0D");
        l_tradingTimeParams.setBranchCode("123");
        l_tradingTimeParams.setTradingTimeType("01");
        l_tradingTimeParams.setBizDateType("1");
        l_tradingTimeParams.setMarketCode("N1");
        l_tradingTimeParams.setProductCode("0001000");
        
        MOCK_MANAGER.setIsMockUsed(true);
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
            "getAccountId",
            new Class[] {},new Long(333812512203L));
       
        List l_lis = new ArrayList();
        AssetParams l_assetParams = TestDBUtility.getAssetRow();
        l_assetParams.setTaxType(TaxTypeEnum.NORMAL);
        l_assetParams.setAccountId(333812512203L);
        l_assetParams.setSubAccountId(333812512203L);
        
        MutualFundAssetImpl l_assert = new MutualFundAssetImpl(l_assetParams);
        l_lis.add(l_assert);
        
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "webbroker3.mf.WEB3MutualFundPositionManager",
            "getAssets",
            new Class[] {SubAccount.class, String.class},
            l_lis);

        Double l_dblQty = new Double("2.0");
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "webbroker3.mf.WEB3MutualFundPositionManager",
            "calcSellPossiblePositionQty",
            new Class[] {SubAccount.class, WEB3MutualFundProduct.class, String.class},
            l_dblQty);
        
        Double l_dblValue = new Double("2.0");
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "webbroker3.mf.WEB3MutualFundPositionManager",
            "calcMarketValue",
            new Class[] {SubAccount.class, WEB3MutualFundProduct.class, String.class},
            l_dblValue);
        
        Double l_dblProfitLoss = new Double("2.0");
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "webbroker3.mf.WEB3MutualFundPositionManager",
            "calcAppraisalProfitLoss",
            new Class[] {SubAccount.class, WEB3MutualFundProduct.class, String.class},
            l_dblProfitLoss);

        Long l_lngDist = new Long("2");
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "webbroker3.mf.WEB3MutualFundPositionManager",
            "getUnreceiveDist",
            new Class[] {String.class, WEB3MutualFundProduct.class},
            l_lngDist);
        
        
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "webbroker3.mf.WEB3MutualFundProduct",
            "isAcquiredDeregExistence",
            new Class[] {},
            Boolean.TRUE);
        
        
        MutualFundProductParams l_mfProductParams = TestDBUtility.getMutualFundProductRow();
        l_mfProductParams.setProductId(1006169090018L);
        l_mfProductParams.setInstitutionCode("0D");
        l_mfProductParams.setProductCode("0001000");
        l_mfProductParams.setProductIssueCode("0");
        l_mfProductParams.setCurrencyCode("T0");
        l_mfProductParams.setFundType(MutualFundTypeEnum.OTHER);//拡張投信銘柄.isFWF()
        l_mfProductParams.setSellConstantValue(1111);
        l_mfProductParams.setSwtUnitQty(2222);
        l_mfProductParams.setSwtMinQty(3333);
        l_mfProductParams.setSwtUnitAmt(4444);
        l_mfProductParams.setSwtMinAmt(5555);
        l_mfProductParams.setSellMinAmt(100);
        l_mfProductParams.setSellSwtStartDate(WEB3DateUtility.getDate("20070101","yyyyMMdd"));
        l_mfProductParams.setSellSwtEndDate(WEB3DateUtility.getDate("20070501","yyyyMMdd"));
        l_mfProductParams.setSystemHandlingDiv(WEB3SystemHandlingDivDef.WEBBROKER3_TREAT_IT_IN);
        ProductParams l_product = TestDBUtility.getProductRow();
        l_product.setProductId(1006169090018L);
        try
        {
            MarketCalendarParams l_marketCalendarParams = new MarketCalendarParams();
            l_marketCalendarParams.setMarketId(3303L);
            l_marketCalendarParams.setTradeDate(CalendarUtils.clearTimeFields(GtlUtils.getSystemTimestamp()));
            l_marketCalendarParams.setHolidayFlag(BooleanEnum.TRUE);
            l_marketCalendarParams.setTradeOpenTime("090000");
            l_marketCalendarParams.setTradeCloseTime("150000");
            l_marketCalendarParams.setCreatedTimestamp(WEB3DateUtility.getDate("20040707","yyyyMMdd"));
            l_marketCalendarParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20040707","yyyyMMdd"));
            TestDBUtility.deleteAll(MarketCalendarRow.TYPE);
            TestDBUtility.insertWithDel(l_marketCalendarParams);
            
            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            TestDBUtility.deleteAll(InstitutionRow.TYPE);
            TestDBUtility.deleteAll(ProductRow.TYPE);
            TestDBUtility.deleteAll(MarketRow.TYPE);
            TestDBUtility.deleteAll(BranchRow.TYPE);
            TestDBUtility.deleteAll(TradingTimeRow.TYPE);
            TestDBUtility.deleteAll(AssetRow.TYPE);

            TestDBUtility.insertWithDel(l_subAccountParams);
            TestDBUtility.insertWithDel(l_mainAccountParams);
            TestDBUtility.insertWithDel(l_institutionParams);
            TestDBUtility.insertWithDel(l_productParams);
            TestDBUtility.insertWithDel(l_marketParams);
            TestDBUtility.insertWithDel(l_branchParams);
            TestDBUtility.insertWithDel(l_assetParams);

            TestDBUtility.insertWithDel(l_mfProductParams);
            TestDBUtility.insertWithDel(l_product);
            TestDBUtility.insertWithDel(l_tradingTimeParams);
            TestDBUtility.insertWithDel(l_mfTradedProductParams);
            
            WEB3GentradeTradingTimeManagementForMock.mockSetTimestamp();
            
            String l_strDate = "174120";
            WEB3MutualFundTradingTimeManagementForMock.mockGetOrderCloseTime(l_strDate);
            
            WEB3MutualBalanceReferenceRequestForMock l_request = 
                new WEB3MutualBalanceReferenceRequestForMock();
            
            WEB3MutualSortKey[] l_keys = new WEB3MutualSortKey[1];
            l_keys[0] = new WEB3MutualSortKey();
            l_keys[0].keyItem = "taxType";
            l_keys[0].ascDesc = "A";
            l_request.sortKeys = l_keys;
            l_request.pageIndex = "1";
            l_request.pageSize = "1";
            
            WEB3MutualBalanceReferenceServiceImpl l_impl = 
                new WEB3MutualBalanceReferenceServiceImpl();
            l_response = l_impl.getBalanceReference(l_request);
            assertEquals("3", l_response.balanceReference[0].sellPossType);
            assertEquals("3",l_response.balanceReference[0].buyPossType);
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        } 
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testGetBalanceReferenceC5()
    {
        final String STR_METHOD_NAME = "testGetBalanceReferenceC5()";
        log.entering(STR_METHOD_NAME);
        WEB3MutualBalanceReferenceResponse l_response = null;
        
        MOCK_MANAGER.setIsMockUsed(true);
        


        SubAccountParams l_subAccountParams =
            TestDBUtility.getSubAccountRow();
        l_subAccountParams.setInstitutionCode("6D");
        l_subAccountParams.setInstitutionId(63);
        l_subAccountParams.setSubAccountId(333812512203L);
        l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
        
        
        MainAccountParams l_mainAccountParams =
            TestDBUtility.getMainAccountRow();
        l_mainAccountParams.setAccountId(333812512203L);
        l_mainAccountParams.setInstitutionId(63);

        InstitutionParams l_institutionParams =
            TestDBUtility.getInstitutionRow();
        l_institutionParams.setInstitutionCode("0D");
        l_institutionParams.setInstitutionId(63);
        
        ProductParams l_productParams =
            TestDBUtility.getProductRow();
        l_productParams.setProductId(3304148080001L);
        l_productParams.setInstitutionCode("0");
        l_productParams.setProductType(ProductTypeEnum.EQUITY);

        MarketParams l_marketParams =
            TestDBUtility.getMarketRow();
        l_marketParams.setMarketCode("N1");
        l_marketParams.setMarketId(3303L);
        l_marketParams.setInstitutionCode("6D");

        BranchParams l_branchParams =
            TestDBUtility.getBranchRow();
        l_branchParams.setInstitutionCode("6D");
        l_branchParams.setInstitutionId(63);
        
        
        MutualFundTradedProductParams l_mfTradedProductParams =
            TestDBUtility.getMutualFundTradedProductRow();
        

        TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
        l_tradingTimeParams.setInstitutionCode("0D");
        l_tradingTimeParams.setBranchCode("123");
        l_tradingTimeParams.setTradingTimeType("01");
        l_tradingTimeParams.setBizDateType("1");
        l_tradingTimeParams.setMarketCode("N1");
        l_tradingTimeParams.setProductCode("0001000");
        
        MOCK_MANAGER.setIsMockUsed(true);
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
            "getAccountId",
            new Class[] {},new Long(333812512203L));
       
        List l_lis = new ArrayList();
        AssetParams l_assetParams = TestDBUtility.getAssetRow();
        l_assetParams.setTaxType(TaxTypeEnum.NORMAL);
        l_assetParams.setAccountId(333812512203L);
        l_assetParams.setSubAccountId(333812512203L);
        
        MutualFundAssetImpl l_assert = new MutualFundAssetImpl(l_assetParams);
        l_lis.add(l_assert);
        
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "webbroker3.mf.WEB3MutualFundPositionManager",
            "getAssets",
            new Class[] {SubAccount.class, String.class},
            l_lis);

        Double l_dblQty = new Double("2.0");
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "webbroker3.mf.WEB3MutualFundPositionManager",
            "calcSellPossiblePositionQty",
            new Class[] {SubAccount.class, WEB3MutualFundProduct.class, String.class},
            l_dblQty);
        
        Double l_dblValue = new Double("2.0");
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "webbroker3.mf.WEB3MutualFundPositionManager",
            "calcMarketValue",
            new Class[] {SubAccount.class, WEB3MutualFundProduct.class, String.class},
            l_dblValue);
        
        Double l_dblProfitLoss = new Double("2.0");
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "webbroker3.mf.WEB3MutualFundPositionManager",
            "calcAppraisalProfitLoss",
            new Class[] {SubAccount.class, WEB3MutualFundProduct.class, String.class},
            l_dblProfitLoss);

        Long l_lngDist = new Long("2");
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "webbroker3.mf.WEB3MutualFundPositionManager",
            "getUnreceiveDist",
            new Class[] {String.class, WEB3MutualFundProduct.class},
            l_lngDist);
        
        
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "webbroker3.mf.WEB3MutualFundProduct",
            "isAcquiredDeregExistence",
            new Class[] {},
            Boolean.TRUE);
        
        
        MutualFundProductParams l_mfProductParams = TestDBUtility.getMutualFundProductRow();
        l_mfProductParams.setProductId(1006169090018L);
        l_mfProductParams.setInstitutionCode("0D");
        l_mfProductParams.setProductCode("0001000");
        l_mfProductParams.setProductIssueCode("0");
        l_mfProductParams.setCurrencyCode("T0");
        l_mfProductParams.setFundType(MutualFundTypeEnum.FOREIGN_MMF);//拡張投信銘柄.is外貨MMF()の戻り値 == true
        l_mfProductParams.setSellConstantValue(1111);
        l_mfProductParams.setSwtUnitQty(2222);
        l_mfProductParams.setSwtMinQty(3333);
        l_mfProductParams.setSwtUnitAmt(4444);
        l_mfProductParams.setSwtMinAmt(5555);
        l_mfProductParams.setSellMinAmt(100);
        l_mfProductParams.setSellSwtStartDate(WEB3DateUtility.getDate("20070101","yyyyMMdd"));
        l_mfProductParams.setSellSwtEndDate(WEB3DateUtility.getDate("20070501","yyyyMMdd"));
        l_mfProductParams.setSystemHandlingDiv(WEB3SystemHandlingDivDef.WEBBROKER3_TREAT_IT_IN);
        ProductParams l_product = TestDBUtility.getProductRow();
        l_product.setProductId(1006169090018L);
        try
        {
            MarketCalendarParams l_marketCalendarParams = new MarketCalendarParams();
            l_marketCalendarParams.setMarketId(3303L);
            l_marketCalendarParams.setTradeDate(CalendarUtils.clearTimeFields(GtlUtils.getSystemTimestamp()));
            l_marketCalendarParams.setHolidayFlag(BooleanEnum.TRUE);
            l_marketCalendarParams.setTradeOpenTime("090000");
            l_marketCalendarParams.setTradeCloseTime("150000");
            l_marketCalendarParams.setCreatedTimestamp(WEB3DateUtility.getDate("20040707","yyyyMMdd"));
            l_marketCalendarParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20040707","yyyyMMdd"));
            TestDBUtility.deleteAll(MarketCalendarRow.TYPE);
            TestDBUtility.insertWithDel(l_marketCalendarParams);
            
            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            TestDBUtility.deleteAll(InstitutionRow.TYPE);
            TestDBUtility.deleteAll(ProductRow.TYPE);
            TestDBUtility.deleteAll(MarketRow.TYPE);
            TestDBUtility.deleteAll(BranchRow.TYPE);
            TestDBUtility.deleteAll(TradingTimeRow.TYPE);
            TestDBUtility.deleteAll(AssetRow.TYPE);

            TestDBUtility.insertWithDel(l_subAccountParams);
            TestDBUtility.insertWithDel(l_mainAccountParams);
            TestDBUtility.insertWithDel(l_institutionParams);
            TestDBUtility.insertWithDel(l_productParams);
            TestDBUtility.insertWithDel(l_marketParams);
            TestDBUtility.insertWithDel(l_branchParams);
            TestDBUtility.insertWithDel(l_assetParams);

            TestDBUtility.insertWithDel(l_mfProductParams);
            TestDBUtility.insertWithDel(l_product);
            TestDBUtility.insertWithDel(l_tradingTimeParams);
            TestDBUtility.insertWithDel(l_mfTradedProductParams);
            
            WEB3GentradeTradingTimeManagementForMock.mockSetTimestamp();
            
            String l_strDate = "174120";
            WEB3MutualFundTradingTimeManagementForMock.mockGetOrderCloseTime(l_strDate);
            
            WEB3MutualBalanceReferenceRequestForMock l_request = 
                new WEB3MutualBalanceReferenceRequestForMock();
            
            WEB3MutualSortKey[] l_keys = new WEB3MutualSortKey[1];
            l_keys[0] = new WEB3MutualSortKey();
            l_keys[0].keyItem = "taxType";
            l_keys[0].ascDesc = "A";
            l_request.sortKeys = l_keys;
            l_request.pageIndex = "1";
            l_request.pageSize = "1";
            
            WEB3MutualBalanceReferenceServiceImpl l_impl = 
                new WEB3MutualBalanceReferenceServiceImpl();
            l_response = l_impl.getBalanceReference(l_request);
            assertEquals("3", l_response.balanceReference[0].buyPossType);
            assertEquals("3",l_response.balanceReference[0].sellPossType);
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        } 
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testGetBalanceReferenceC6()
    {
        final String STR_METHOD_NAME = "testGetBalanceReferenceC6()";
        log.entering(STR_METHOD_NAME);
        WEB3MutualBalanceReferenceResponse l_response = null;
        
        MOCK_MANAGER.setIsMockUsed(true);
        


        SubAccountParams l_subAccountParams =
            TestDBUtility.getSubAccountRow();
        l_subAccountParams.setInstitutionCode("6D");
        l_subAccountParams.setInstitutionId(63);
        l_subAccountParams.setSubAccountId(333812512203L);
        l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
        
        
        MainAccountParams l_mainAccountParams =
            TestDBUtility.getMainAccountRow();
        l_mainAccountParams.setAccountId(333812512203L);
        l_mainAccountParams.setInstitutionId(63);

        InstitutionParams l_institutionParams =
            TestDBUtility.getInstitutionRow();
        l_institutionParams.setInstitutionCode("0D");
        l_institutionParams.setInstitutionId(63);
        
        ProductParams l_productParams =
            TestDBUtility.getProductRow();
        l_productParams.setProductId(3304148080001L);
        l_productParams.setInstitutionCode("0");
        l_productParams.setProductType(ProductTypeEnum.EQUITY);

        MarketParams l_marketParams =
            TestDBUtility.getMarketRow();
        l_marketParams.setMarketCode("N1");
        l_marketParams.setMarketId(3303L);
        l_marketParams.setInstitutionCode("6D");

        BranchParams l_branchParams =
            TestDBUtility.getBranchRow();
        l_branchParams.setInstitutionCode("6D");
        l_branchParams.setInstitutionId(63);
        
        
        MutualFundTradedProductParams l_mfTradedProductParams =
            TestDBUtility.getMutualFundTradedProductRow();
        

        TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
        l_tradingTimeParams.setInstitutionCode("0D");
        l_tradingTimeParams.setBranchCode("123");
        l_tradingTimeParams.setTradingTimeType("01");
        l_tradingTimeParams.setBizDateType("1");
        l_tradingTimeParams.setMarketCode("N1");
        l_tradingTimeParams.setProductCode("0001000");
        
        MOCK_MANAGER.setIsMockUsed(true);
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
            "getAccountId",
            new Class[] {},new Long(333812512203L));
       
        List l_lis = new ArrayList();
        AssetParams l_assetParams = TestDBUtility.getAssetRow();
        l_assetParams.setTaxType(TaxTypeEnum.NORMAL);
        l_assetParams.setAccountId(333812512203L);
        l_assetParams.setSubAccountId(333812512203L);
        
        MutualFundAssetImpl l_assert = new MutualFundAssetImpl(l_assetParams);
        l_lis.add(l_assert);
        
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "webbroker3.mf.WEB3MutualFundPositionManager",
            "getAssets",
            new Class[] {SubAccount.class, String.class},
            l_lis);

        Double l_dblQty = new Double("2.0");
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "webbroker3.mf.WEB3MutualFundPositionManager",
            "calcSellPossiblePositionQty",
            new Class[] {SubAccount.class, WEB3MutualFundProduct.class, String.class},
            l_dblQty);
        
        Double l_dblValue = new Double("2.0");
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "webbroker3.mf.WEB3MutualFundPositionManager",
            "calcMarketValue",
            new Class[] {SubAccount.class, WEB3MutualFundProduct.class, String.class},
            l_dblValue);
        
        Double l_dblProfitLoss = new Double("2.0");
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "webbroker3.mf.WEB3MutualFundPositionManager",
            "calcAppraisalProfitLoss",
            new Class[] {SubAccount.class, WEB3MutualFundProduct.class, String.class},
            l_dblProfitLoss);

        Long l_lngDist = new Long("2");
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "webbroker3.mf.WEB3MutualFundPositionManager",
            "getUnreceiveDist",
            new Class[] {String.class, WEB3MutualFundProduct.class},
            l_lngDist);
        
        
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "webbroker3.mf.WEB3MutualFundProduct",
            "isAcquiredDeregExistence",
            new Class[] {},
            Boolean.TRUE);
        
        
        MutualFundProductParams l_mfProductParams = TestDBUtility.getMutualFundProductRow();
        l_mfProductParams.setProductId(1006169090018L);
        l_mfProductParams.setInstitutionCode("0D");
        l_mfProductParams.setProductCode("0001000");
        l_mfProductParams.setProductIssueCode("0");
        l_mfProductParams.setCurrencyCode("T0");
        l_mfProductParams.setFundType(MutualFundTypeEnum.DOMESTIC);//(*8) ,(*9) 以外 is国内投信()    
        l_mfProductParams.setSellConstantValue(1111);
        l_mfProductParams.setSwtUnitQty(2222);
        l_mfProductParams.setSwtMinQty(3333);
        l_mfProductParams.setSwtUnitAmt(4444);
        l_mfProductParams.setSwtMinAmt(5555);
        l_mfProductParams.setSellMinAmt(100);
        l_mfProductParams.setSellSwtStartDate(WEB3DateUtility.getDate("20070101","yyyyMMdd"));
        l_mfProductParams.setSellSwtEndDate(WEB3DateUtility.getDate("20070501","yyyyMMdd"));
        l_mfProductParams.setSystemHandlingDiv(WEB3SystemHandlingDivDef.WEBBROKER3_TREAT_IT_IN);
        ProductParams l_product = TestDBUtility.getProductRow();
        l_product.setProductId(1006169090018L);
        try
        {
            MarketCalendarParams l_marketCalendarParams = new MarketCalendarParams();
            l_marketCalendarParams.setMarketId(3303L);
            l_marketCalendarParams.setTradeDate(CalendarUtils.clearTimeFields(GtlUtils.getSystemTimestamp()));
            l_marketCalendarParams.setHolidayFlag(BooleanEnum.TRUE);
            l_marketCalendarParams.setTradeOpenTime("090000");
            l_marketCalendarParams.setTradeCloseTime("150000");
            l_marketCalendarParams.setCreatedTimestamp(WEB3DateUtility.getDate("20040707","yyyyMMdd"));
            l_marketCalendarParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20040707","yyyyMMdd"));
            TestDBUtility.deleteAll(MarketCalendarRow.TYPE);
            TestDBUtility.insertWithDel(l_marketCalendarParams);
            
            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            TestDBUtility.deleteAll(InstitutionRow.TYPE);
            TestDBUtility.deleteAll(ProductRow.TYPE);
            TestDBUtility.deleteAll(MarketRow.TYPE);
            TestDBUtility.deleteAll(BranchRow.TYPE);
            TestDBUtility.deleteAll(TradingTimeRow.TYPE);
            TestDBUtility.deleteAll(AssetRow.TYPE);

            TestDBUtility.insertWithDel(l_subAccountParams);
            TestDBUtility.insertWithDel(l_mainAccountParams);
            TestDBUtility.insertWithDel(l_institutionParams);
            TestDBUtility.insertWithDel(l_productParams);
            TestDBUtility.insertWithDel(l_marketParams);
            TestDBUtility.insertWithDel(l_branchParams);
            TestDBUtility.insertWithDel(l_assetParams);

            TestDBUtility.insertWithDel(l_mfProductParams);
            TestDBUtility.insertWithDel(l_product);
            TestDBUtility.insertWithDel(l_tradingTimeParams);
            TestDBUtility.insertWithDel(l_mfTradedProductParams);
            
            WEB3GentradeTradingTimeManagementForMock.mockSetTimestamp();
            
            String l_strDate = "174120";
            WEB3MutualFundTradingTimeManagementForMock.mockGetOrderCloseTime(l_strDate);
            
            WEB3MutualBalanceReferenceRequestForMock l_request = 
                new WEB3MutualBalanceReferenceRequestForMock();
            
            WEB3MutualSortKey[] l_keys = new WEB3MutualSortKey[1];
            l_keys[0] = new WEB3MutualSortKey();
            l_keys[0].keyItem = "taxType";
            l_keys[0].ascDesc = "A";
            l_request.sortKeys = l_keys;
            l_request.pageIndex = "1";
            l_request.pageSize = "1";
            
            WEB3MutualBalanceReferenceServiceImpl l_impl = 
                new WEB3MutualBalanceReferenceServiceImpl();
            l_response = l_impl.getBalanceReference(l_request);
            assertEquals("3", l_response.balanceReference[0].buyPossType);
            assertEquals("3",l_response.balanceReference[0].sellPossType);
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        } 
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    //【投資信託】静銀TM対応（外貨建MMF）（管理者機@能） 2007/04/12
    public void testGetBalanceReferenceCase1()
    {
        final String STR_METHOD_NAME = "testGetBalanceReferenceCase1()";
        log.entering(STR_METHOD_NAME);
        WEB3MutualBalanceReferenceResponse l_response = null;
        
        MOCK_MANAGER.setIsMockUsed(true);
        


        SubAccountParams l_subAccountParams =
            TestDBUtility.getSubAccountRow();
        l_subAccountParams.setInstitutionCode("6D");
        l_subAccountParams.setInstitutionId(63);
        l_subAccountParams.setSubAccountId(333812512203L);
        l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
        
        
        MainAccountParams l_mainAccountParams =
            TestDBUtility.getMainAccountRow();
        l_mainAccountParams.setAccountId(333812512203L);
        l_mainAccountParams.setInstitutionId(63);

        InstitutionParams l_institutionParams =
            TestDBUtility.getInstitutionRow();
        l_institutionParams.setInstitutionCode("0D");
        l_institutionParams.setInstitutionId(63);
        
        ProductParams l_productParams =
            TestDBUtility.getProductRow();
        l_productParams.setProductId(3304148080001L);
        l_productParams.setInstitutionCode("0");
        l_productParams.setProductType(ProductTypeEnum.EQUITY);

        MarketParams l_marketParams =
            TestDBUtility.getMarketRow();
        l_marketParams.setMarketCode("N1");
        l_marketParams.setMarketId(3303L);
        l_marketParams.setInstitutionCode("6D");

        BranchParams l_branchParams =
            TestDBUtility.getBranchRow();
        l_branchParams.setInstitutionCode("6D");
        l_branchParams.setInstitutionId(63);
        
        
        MutualFundTradedProductParams l_mfTradedProductParams =
            TestDBUtility.getMutualFundTradedProductRow();
        

        TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
        l_tradingTimeParams.setInstitutionCode("0D");
        l_tradingTimeParams.setBranchCode("123");
        l_tradingTimeParams.setTradingTimeType("01");
        l_tradingTimeParams.setBizDateType("1");
        l_tradingTimeParams.setMarketCode("N1");
        l_tradingTimeParams.setProductCode("0001000");
        
        MOCK_MANAGER.setIsMockUsed(true);
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
            "getAccountId",
            new Class[] {},new Long(333812512203L));
       
        List l_lis = new ArrayList();
        AssetParams l_assetParams = TestDBUtility.getAssetRow();
        l_assetParams.setTaxType(TaxTypeEnum.NORMAL);
        l_assetParams.setAccountId(333812512203L);
        l_assetParams.setSubAccountId(333812512203L);
        
        MutualFundAssetImpl l_assert = new MutualFundAssetImpl(l_assetParams);
        l_lis.add(l_assert);
        
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "webbroker3.mf.WEB3MutualFundPositionManager",
            "getAssets",
            new Class[] {SubAccount.class, String.class},
            l_lis);

        Double l_dblQty = new Double("2.0");
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "webbroker3.mf.WEB3MutualFundPositionManager",
            "calcSellPossiblePositionQty",
            new Class[] {SubAccount.class, WEB3MutualFundProduct.class, String.class},
            l_dblQty);
        
        Double l_dblValue = new Double("2.0");
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "webbroker3.mf.WEB3MutualFundPositionManager",
            "calcMarketValue",
            new Class[] {SubAccount.class, WEB3MutualFundProduct.class, String.class},
            l_dblValue);
        
        Double l_dblProfitLoss = new Double("2.0");
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "webbroker3.mf.WEB3MutualFundPositionManager",
            "calcAppraisalProfitLoss",
            new Class[] {SubAccount.class, WEB3MutualFundProduct.class, String.class},
            l_dblProfitLoss);

        Long l_lngDist = new Long("2");
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "webbroker3.mf.WEB3MutualFundPositionManager",
            "getUnreceiveDist",
            new Class[] {String.class, WEB3MutualFundProduct.class},
            l_lngDist);
        
        
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "webbroker3.mf.WEB3MutualFundProduct",
            "isAcquiredDeregExistence",
            new Class[] {},
            Boolean.TRUE);
        
        
        MutualFundProductParams l_mfProductParams = TestDBUtility.getMutualFundProductRow();
        l_mfProductParams.setProductId(1006169090018L);
        l_mfProductParams.setInstitutionCode("0D");
        l_mfProductParams.setProductCode("0001000");
        l_mfProductParams.setProductIssueCode("0");
        l_mfProductParams.setCurrencyCode("T0");
        l_mfProductParams.setFundType(MutualFundTypeEnum.DOMESTIC);//(*8) ,(*9) 以外 is国内投信()    
        l_mfProductParams.setSellConstantValue(1111);
        l_mfProductParams.setSwtUnitQty(2222);
        l_mfProductParams.setSwtMinQty(3333);
        l_mfProductParams.setSwtUnitAmt(4444);
        l_mfProductParams.setSwtMinAmt(5555);
        l_mfProductParams.setSellMinAmt(100);
        l_mfProductParams.setSellSwtStartDate(WEB3DateUtility.getDate("20070101","yyyyMMdd"));
        l_mfProductParams.setSellSwtEndDate(WEB3DateUtility.getDate("20070501","yyyyMMdd"));
        l_mfProductParams.setSystemHandlingDiv(WEB3SystemHandlingDivDef.WEBBROKER3_TREAT_IT_IN);
        l_mfProductParams.setFrgnSellUnitAmt(1234);
        l_mfProductParams.setFrgnSellMinAmt(5678);
        
        ProductParams l_product = TestDBUtility.getProductRow();
        l_product.setProductId(1006169090018L);
        try
        {
            MarketCalendarParams l_marketCalendarParams = new MarketCalendarParams();
            l_marketCalendarParams.setMarketId(3303L);
            l_marketCalendarParams.setTradeDate(CalendarUtils.clearTimeFields(GtlUtils.getSystemTimestamp()));
            l_marketCalendarParams.setHolidayFlag(BooleanEnum.TRUE);
            l_marketCalendarParams.setTradeOpenTime("090000");
            l_marketCalendarParams.setTradeCloseTime("150000");
            l_marketCalendarParams.setCreatedTimestamp(WEB3DateUtility.getDate("20040707","yyyyMMdd"));
            l_marketCalendarParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20040707","yyyyMMdd"));
            TestDBUtility.deleteAll(MarketCalendarRow.TYPE);
            TestDBUtility.insertWithDel(l_marketCalendarParams);
            
            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            TestDBUtility.deleteAll(InstitutionRow.TYPE);
            TestDBUtility.deleteAll(ProductRow.TYPE);
            TestDBUtility.deleteAll(MarketRow.TYPE);
            TestDBUtility.deleteAll(BranchRow.TYPE);
            TestDBUtility.deleteAll(TradingTimeRow.TYPE);
            TestDBUtility.deleteAll(AssetRow.TYPE);

            TestDBUtility.insertWithDel(l_subAccountParams);
            TestDBUtility.insertWithDel(l_mainAccountParams);
            TestDBUtility.insertWithDel(l_institutionParams);
            TestDBUtility.insertWithDel(l_productParams);
            TestDBUtility.insertWithDel(l_marketParams);
            TestDBUtility.insertWithDel(l_branchParams);
            TestDBUtility.insertWithDel(l_assetParams);

            TestDBUtility.insertWithDel(l_mfProductParams);
            TestDBUtility.insertWithDel(l_product);
            TestDBUtility.insertWithDel(l_tradingTimeParams);
            TestDBUtility.insertWithDel(l_mfTradedProductParams);
            
            WEB3GentradeTradingTimeManagementForMock.mockSetTimestamp();
            
            String l_strDate = "174120";
            WEB3MutualFundTradingTimeManagementForMock.mockGetOrderCloseTime(l_strDate);
            
            WEB3MutualBalanceReferenceRequestForMock l_request = 
                new WEB3MutualBalanceReferenceRequestForMock();
            
            WEB3MutualSortKey[] l_keys = new WEB3MutualSortKey[1];
            l_keys[0] = new WEB3MutualSortKey();
            l_keys[0].keyItem = "taxType";
            l_keys[0].ascDesc = "A";
            l_request.sortKeys = l_keys;
            l_request.pageIndex = "1";
            l_request.pageSize = "1";
            
            WEB3MutualBalanceReferenceServiceImpl l_impl = 
                new WEB3MutualBalanceReferenceServiceImpl();
            l_response = l_impl.getBalanceReference(l_request);
            
            assertEquals("1234", l_response.balanceReference[0].sellFrgnUnitAmt);
            assertEquals("5678", l_response.balanceReference[0].sellFrgnMinAmt);
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        } 
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    //仕様変更・モデル595
    public void testGetBalanceReferenceC0001()
    {
        final String STR_METHOD_NAME = "testGetBalanceReferenceC0001()";
        log.entering(STR_METHOD_NAME);
        WEB3MutualBalanceReferenceResponse l_response = null;
        
        MOCK_MANAGER.setIsMockUsed(true);
        


        SubAccountParams l_subAccountParams =
            TestDBUtility.getSubAccountRow();
        l_subAccountParams.setInstitutionCode("6D");
        l_subAccountParams.setInstitutionId(63);
        l_subAccountParams.setSubAccountId(333812512203L);
        l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
        
        
        MainAccountParams l_mainAccountParams =
            TestDBUtility.getMainAccountRow();
        l_mainAccountParams.setAccountId(333812512203L);
        l_mainAccountParams.setInstitutionId(63);

        InstitutionParams l_institutionParams =
            TestDBUtility.getInstitutionRow();
        l_institutionParams.setInstitutionCode("0D");
        l_institutionParams.setInstitutionId(63);
        
        ProductParams l_productParams =
            TestDBUtility.getProductRow();
        l_productParams.setProductId(3304148080001L);
        l_productParams.setInstitutionCode("0");
        l_productParams.setProductType(ProductTypeEnum.EQUITY);

        MarketParams l_marketParams =
            TestDBUtility.getMarketRow();
        l_marketParams.setMarketCode("N1");
        l_marketParams.setMarketId(3303L);
        l_marketParams.setInstitutionCode("6D");

        BranchParams l_branchParams =
            TestDBUtility.getBranchRow();
        l_branchParams.setInstitutionCode("6D");
        l_branchParams.setInstitutionId(63);
        
        
        MutualFundTradedProductParams l_mfTradedProductParams =
            TestDBUtility.getMutualFundTradedProductRow();
        

        TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
        l_tradingTimeParams.setInstitutionCode("0D");
        l_tradingTimeParams.setBranchCode("123");
        l_tradingTimeParams.setTradingTimeType("01");
        l_tradingTimeParams.setBizDateType("1");
        l_tradingTimeParams.setMarketCode("N1");
        l_tradingTimeParams.setProductCode("0001000");
        
        MOCK_MANAGER.setIsMockUsed(true);
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
            "getAccountId",
            new Class[] {},new Long(333812512203L));
       
        List l_lis = new ArrayList();
        AssetParams l_assetParams = TestDBUtility.getAssetRow();
        l_assetParams.setTaxType(TaxTypeEnum.NORMAL);
        l_assetParams.setAccountId(333812512203L);
        l_assetParams.setSubAccountId(333812512203L);
        
        MutualFundAssetImpl l_assert = new MutualFundAssetImpl(l_assetParams);
        l_lis.add(l_assert);
        
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "webbroker3.mf.WEB3MutualFundPositionManager",
            "getAssets",
            new Class[] {SubAccount.class, String.class},
            l_lis);

        Double l_dblQty = new Double("2.0");
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "webbroker3.mf.WEB3MutualFundPositionManager",
            "calcSellPossiblePositionQty",
            new Class[] {SubAccount.class, WEB3MutualFundProduct.class, String.class},
            l_dblQty);
        
        Double l_dblValue = new Double("2.0");
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "webbroker3.mf.WEB3MutualFundPositionManager",
            "calcMarketValue",
            new Class[] {SubAccount.class, WEB3MutualFundProduct.class, String.class},
            l_dblValue);
        
        Double l_dblProfitLoss = new Double("2.0");
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "webbroker3.mf.WEB3MutualFundPositionManager",
            "calcAppraisalProfitLoss",
            new Class[] {SubAccount.class, WEB3MutualFundProduct.class, String.class},
            l_dblProfitLoss);

        Long l_lngDist = new Long("2");
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "webbroker3.mf.WEB3MutualFundPositionManager",
            "getUnreceiveDist",
            new Class[] {String.class, WEB3MutualFundProduct.class},
            l_lngDist);
        
        
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "webbroker3.mf.WEB3MutualFundProduct",
            "isAcquiredDeregExistence",
            new Class[] {},
            Boolean.TRUE);

        MutualFundProductParams l_mfProductParams = TestDBUtility.getMutualFundProductRow();
        l_mfProductParams.setProductId(1006169090018L);
        l_mfProductParams.setInstitutionCode("0D");
        l_mfProductParams.setProductCode("0001000");
        l_mfProductParams.setProductIssueCode("0");
        l_mfProductParams.setCurrencyCode("T0");
        l_mfProductParams.setFundType(MutualFundTypeEnum.DOMESTIC);//(*8) ,(*9) 以外 is国内投信()    
        l_mfProductParams.setSellConstantValue(1111);
        l_mfProductParams.setSwtUnitQty(2222);
        l_mfProductParams.setSwtMinQty(3333);
        l_mfProductParams.setSwtUnitAmt(4444);
        l_mfProductParams.setSwtMinAmt(5555);
        l_mfProductParams.setSellMinAmt(100);
        l_mfProductParams.setSwtSpecifyDiv("0");
        l_mfProductParams.setSellSpecifyDiv("3");
        l_mfProductParams.setSellSwtStartDate(WEB3DateUtility.getDate("20070101","yyyyMMdd"));
        l_mfProductParams.setSellSwtEndDate(WEB3DateUtility.getDate("20070501","yyyyMMdd"));
        l_mfProductParams.setSystemHandlingDiv(WEB3SystemHandlingDivDef.WEBBROKER3_TREAT_IT_IN);
        l_mfProductParams.setFrgnSellUnitAmt(1234);
        l_mfProductParams.setFrgnSellMinAmt(5678);

        ProductParams l_product = TestDBUtility.getProductRow();
        l_product.setProductId(1006169090018L);
        try
        {
            TradedProductParams l_tradedProductParams = TestDBUtility.getTradedProductRow();
            l_tradedProductParams.setProductId(l_mfTradedProductParams.getTradedProductId());
            TestDBUtility.insertWithDel(l_tradedProductParams);
            
            WEB3MutualFundTradedProduct l_mutualFundTradedProduct = 
                new WEB3MutualFundTradedProduct(l_mfTradedProductParams);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.mf.WEB3MutualFundProductManager",
                "getMutualFundTradedProduct",
                new Class[] {Institution.class, String.class},
                l_mutualFundTradedProduct);
            
            MarketCalendarParams l_marketCalendarParams = new MarketCalendarParams();
            l_marketCalendarParams.setMarketId(3303L);
            l_marketCalendarParams.setTradeDate(CalendarUtils.clearTimeFields(GtlUtils.getSystemTimestamp()));
            l_marketCalendarParams.setHolidayFlag(BooleanEnum.TRUE);
            l_marketCalendarParams.setTradeOpenTime("090000");
            l_marketCalendarParams.setTradeCloseTime("150000");
            l_marketCalendarParams.setCreatedTimestamp(WEB3DateUtility.getDate("20040707","yyyyMMdd"));
            l_marketCalendarParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20040707","yyyyMMdd"));
            TestDBUtility.deleteAll(MarketCalendarRow.TYPE);
            TestDBUtility.insertWithDel(l_marketCalendarParams);
            

            
            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            TestDBUtility.deleteAll(InstitutionRow.TYPE);
            TestDBUtility.deleteAll(ProductRow.TYPE);
            TestDBUtility.deleteAll(MarketRow.TYPE);
            TestDBUtility.deleteAll(BranchRow.TYPE);
            TestDBUtility.deleteAll(TradingTimeRow.TYPE);
            TestDBUtility.deleteAll(AssetRow.TYPE);
            TestDBUtility.deleteAll(MutualFundTradedProductParams.TYPE);

            TestDBUtility.insertWithDel(l_subAccountParams);
            TestDBUtility.insertWithDel(l_mainAccountParams);
            TestDBUtility.insertWithDel(l_institutionParams);
            TestDBUtility.insertWithDel(l_productParams);
            TestDBUtility.insertWithDel(l_marketParams);
            TestDBUtility.insertWithDel(l_branchParams);
            TestDBUtility.insertWithDel(l_assetParams);
            TestDBUtility.insertWithDel(l_mfTradedProductParams);

            TestDBUtility.insertWithDel(l_mfProductParams);
            TestDBUtility.insertWithDel(l_product);
            TestDBUtility.insertWithDel(l_tradingTimeParams);
            TestDBUtility.insertWithDel(l_tradedProductParams);
            
            WEB3GentradeTradingTimeManagementForMock.mockSetTimestamp();
            
            String l_strDate = "174120";
            WEB3MutualFundTradingTimeManagementForMock.mockGetOrderCloseTime(l_strDate);
            
            WEB3MutualBalanceReferenceRequestForMock l_request = 
                new WEB3MutualBalanceReferenceRequestForMock();
            
            WEB3MutualSortKey[] l_keys = new WEB3MutualSortKey[1];
            l_keys[0] = new WEB3MutualSortKey();
            l_keys[0].keyItem = "taxType";
            l_keys[0].ascDesc = "A";
            l_request.sortKeys = l_keys;
            l_request.pageIndex = "1";
            l_request.pageSize = "1";
            
            WEB3MutualBalanceReferenceServiceImpl l_impl = 
                new WEB3MutualBalanceReferenceServiceImpl();
            l_response = l_impl.getBalanceReference(l_request);
            
            assertEquals("0", l_response.balanceReference[0].switchingSelectable);
            assertEquals("3", l_response.balanceReference[0].sellSelectable);
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        } 
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
}
@
