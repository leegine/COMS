head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.02.03.27;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3MarginCloseMarginRequestAdapterTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.equity.service.delegate;

import java.util.Date;

import com.fitechlabs.xtrade.plugin.security.oplogin.impl.LoginInfoImpl;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeContractParams;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeContractRow;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeProductParams;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeProductRow;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeTradedProductParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccountTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MarketParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MarketRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.ProductParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.ProductRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.SubAccountParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.TradedProductParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.TraderParams;

import test.util.TestDBUtility;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3ChannelDef;
import webbroker3.equity.message.WEB3MarginCloseMarginCompleteRequest;
import webbroker3.equity.message.WEB3MarginCloseMarginConfirmRequest;
import webbroker3.equity.message.WEB3MarginCloseMarginContractUnit;
import webbroker3.equity.message.WEB3MarginOpenMarginCompleteRequest;
import webbroker3.equity.message.WEB3MarginOpenMarginConfirmRequest;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagementForMock;
import webbroker3.gentrade.data.BranchMarketRepayDealtCondParams;
import webbroker3.gentrade.data.BranchPreferencesParams;
import webbroker3.gentrade.data.EnableOrderConditionParams;
import webbroker3.gentrade.data.TradingTimeParams;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

/**
 * ÅiWEB3MarginChangeOpenMarginRequestAdapterTestÅj
 * @@author âΩï∂ïq
 * @@version 1.0
 */
public class WEB3MarginCloseMarginRequestAdapterTest extends TestBaseForMock
{

    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3MarginChangeOpenMarginRequestAdapterTest.class);
    WEB3MarginCloseMarginConfirmRequest l_marginOpenMarginChangeConfirmRequest = new WEB3MarginCloseMarginConfirmRequest();
    WEB3MarginCloseMarginRequestAdapter l_adapter1 = WEB3MarginCloseMarginRequestAdapter.create(l_marginOpenMarginChangeConfirmRequest);
    WEB3MarginCloseMarginCompleteRequest l_marginOpenMarginChangeCompleteRequest = new WEB3MarginCloseMarginCompleteRequest();
    WEB3MarginCloseMarginRequestAdapter l_adapter2 = WEB3MarginCloseMarginRequestAdapter.create(l_marginOpenMarginChangeCompleteRequest);

    public WEB3MarginCloseMarginRequestAdapterTest(String arg0)
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
    
    public void testGetExpirationDate_case0001()
    {
        final String STR_METHOD_NAME = "testGetExpirationDate_case0001()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            WEB3GentradeTradingTimeManagementForMock.mockGetOrderBizDate(WEB3DateUtility.getDate("20070618", "yyyyMMdd"));
            
            Date l_datExpirationDate = l_adapter1.getExpirationDate();
            assertEquals("", WEB3DateUtility.formatDate(l_datExpirationDate, "yyyyMMdd"));
        }
        catch (Exception l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }

    public void testGetExpirationDate_case0002()
    {
        final String STR_METHOD_NAME = "testGetExpirationDate_case0002()";
        log.entering(STR_METHOD_NAME);
        
        try
        {   
            TestDBUtility.deleteAll(EqtypeContractRow.TYPE);
            EqtypeContractParams l_eqtypeContractParams = TestDBUtility.getEqtypeContractRow();
            l_eqtypeContractParams.setProductId(3304148080001L);
            l_eqtypeContractParams.setMarketId(3303L);
            TestDBUtility.insertWithDel(l_eqtypeContractParams);
            
            TestDBUtility.deleteAll(ProductRow.TYPE);
            ProductParams l_productParams = TestDBUtility.getProductRow();
            l_productParams.setProductId(3304148080001L);
            l_productParams.setProductType(ProductTypeEnum.EQUITY);
            TestDBUtility.insertWithDel(l_productParams);
            
            TestDBUtility.deleteAll(EqtypeProductRow.TYPE);
            EqtypeProductParams l_eqtypeProductParams = TestDBUtility.getEqtypeProductRow();
            l_eqtypeProductParams.setProductId(3304148080001L);
            l_eqtypeProductParams.setProductType(ProductTypeEnum.EQUITY);
            TestDBUtility.insertWithDel(l_eqtypeProductParams);
            
            TestDBUtility.deleteAll(MarketRow.TYPE);
            MarketParams l_marketParams = TestDBUtility.getMarketRow();
            l_marketParams.setMarketId(3303L);
            TestDBUtility.insertWithDel(l_marketParams);
            
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            TestDBUtility.insertWithDel(l_mainAccountParams);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getAccountId",
                new Class[] {},
                new Long(333812512246L));
            
            l_marginOpenMarginChangeCompleteRequest.expirationDate = WEB3DateUtility.getDate("20070628", "yyyyMMdd");
            WEB3MarginCloseMarginContractUnit l_unit = new WEB3MarginCloseMarginContractUnit();
            l_unit.id = "2134566345";
            l_marginOpenMarginChangeCompleteRequest.closeMarginContractUnits = new WEB3MarginCloseMarginContractUnit[]{l_unit};
            Date l_datExpirationDate = l_adapter2.getExpirationDate();
            assertEquals("20070628", WEB3DateUtility.formatDate(l_datExpirationDate, "yyyyMMdd"));
        }
        catch (Exception l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testGetExpirationDate_case0003()
    {
        final String STR_METHOD_NAME = "testGetExpirationDate_case0003()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            WEB3MarginOpenMarginConfirmRequest l_request = new WEB3MarginOpenMarginConfirmRequest();
            WEB3MarginChangeOpenMarginRequestAdapter l_adapter3 = WEB3MarginChangeOpenMarginRequestAdapter.create(l_request);
            l_adapter3.getExpirationDate();
            
        }
        catch (WEB3BaseException l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            assertEquals( WEB3ErrorCatalog.SYSTEM_ERROR_80018, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testGetExpirationDate_case0004()
    {
        final String STR_METHOD_NAME = "testGetExpirationDate_case0004()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            TestDBUtility.deleteAll(EqtypeContractRow.TYPE);
            EqtypeContractParams l_eqtypeContractParams = TestDBUtility.getEqtypeContractRow();
            TestDBUtility.insertWithDel(l_eqtypeContractParams);
            
            TestDBUtility.deleteAll(ProductRow.TYPE);
            ProductParams l_productParams = TestDBUtility.getProductRow();
            l_productParams.setProductId(3304148080001L);
            l_productParams.setProductType(ProductTypeEnum.EQUITY);
            TestDBUtility.insertWithDel(l_productParams);
            
            TestDBUtility.deleteAll(EqtypeProductRow.TYPE);
            EqtypeProductParams l_eqtypeProductParams = TestDBUtility.getEqtypeProductRow();
            l_eqtypeProductParams.setProductId(3304148080001L);
            l_eqtypeProductParams.setProductType(ProductTypeEnum.EQUITY);
            TestDBUtility.insertWithDel(l_eqtypeProductParams);
            
            TestDBUtility.deleteAll(MarketRow.TYPE);
            MarketParams l_marketParams = TestDBUtility.getMarketRow();
            l_marketParams.setMarketId(3303L);
            TestDBUtility.insertWithDel(l_marketParams);
            
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            TestDBUtility.insertWithDel(l_mainAccountParams);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getAccountId",
                new Class[] {},
                new Long(333812512246L));
            
            l_marginOpenMarginChangeCompleteRequest.expirationDate = WEB3DateUtility.getDate("20070628", "yyyyMMdd");
            WEB3MarginCloseMarginContractUnit l_unit = new WEB3MarginCloseMarginContractUnit();
            l_unit.id = "2134566345";
            l_marginOpenMarginChangeCompleteRequest.closeMarginContractUnits = new WEB3MarginCloseMarginContractUnit[]{l_unit};
            l_adapter2.getExpirationDate();
           
        }
        catch (WEB3BaseException l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            assertEquals( WEB3ErrorCatalog.SYSTEM_ERROR_80005, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }

    /**
     * 
     */
    public void test_getExpirationDate_C0001()
    {
        final String STR_METHOD_NAME = " test_getExpirationDate_C0001()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            WEB3MarginOpenMarginConfirmRequest l_confirmRequest = new WEB3MarginOpenMarginConfirmRequest();
            l_confirmRequest.expirationDate = null;
            Date l_datExpirationDate =
                WEB3MarginOpenMarginRequestAdapter.create(l_confirmRequest).getExpirationDate();
            assertNull(l_datExpirationDate);

            WEB3MarginOpenMarginCompleteRequest l_completeRequest = new WEB3MarginOpenMarginCompleteRequest();
            l_completeRequest.expirationDate = null;
            l_datExpirationDate =
                WEB3MarginOpenMarginRequestAdapter.create(l_completeRequest).getExpirationDate();
            assertNull(l_datExpirationDate);

            l_datExpirationDate =
                WEB3MarginOpenMarginRequestAdapter.create(null).getExpirationDate();
            fail();
        }
        catch (WEB3SystemLayerException l_web3SystemException)
        {
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80018,l_web3SystemException.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    /**
     * 
     */
    public void test_getExpirationDate_C0002()
    {
        final String STR_METHOD_NAME = " test_getExpirationDate_C0002()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                    "getAccountId",
                    new Class[] {},
                    new Long(1001L));

            LoginInfoImpl l_loginInfoImpl = new LoginInfoImpl();
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                    "getLoginInfo",
                    new Class[] {},
                    l_loginInfoImpl);

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.LoginInfoImpl",
                    "getLoginId",
                    new Class[] {},
                    new Long(1002L));

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                    "getSessionProperty",
                    new Class[] {String.class},
                    WEB3ChannelDef.BRANCH);

            TestDBUtility.deleteAll(BranchPreferencesParams.TYPE);
            BranchPreferencesParams l_branchPreferencesParams = TestDBUtility.getBranchPreferencesRow();
            l_branchPreferencesParams.setValue("0");
            l_branchPreferencesParams.setBranchId(33381);
            l_branchPreferencesParams.setName("eqtype.final.day.with.right");
            l_branchPreferencesParams.setNameSerialNo(1);
            TestDBUtility.insertWithDel(l_branchPreferencesParams);

            TestDBUtility.deleteAll(EqtypeTradedProductParams.TYPE);
            EqtypeTradedProductParams l_eqtypeTradedProductParams = TestDBUtility.getEqtypeTradedProductRow();
            l_eqtypeTradedProductParams.setTradedProductId(330304148080000L);
            TestDBUtility.insertWithDel(l_eqtypeTradedProductParams);

            TestDBUtility.deleteAll(TradedProductParams.TYPE);
            TradedProductParams l_tradedProductParams = TestDBUtility.getTradedProductRow();
            l_tradedProductParams.setInstitutionCode("0D");
            l_tradedProductParams.setProductType(ProductTypeEnum.EQUITY);
            TestDBUtility.insertWithDel(l_tradedProductParams);

            TestDBUtility.deleteAll(MarketParams.TYPE);
            MarketParams l_marketParams = TestDBUtility.getMarketRow();
            l_marketParams.setInstitutionCode("0D");
            l_marketParams.setMarketCode("1");
            TestDBUtility.insertWithDel(l_marketParams);

            TestDBUtility.deleteAll(SubAccountParams.TYPE);
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setAccountId(1001L);
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_MARGIN_SUB_ACCOUNT);
            TestDBUtility.insertWithDel(l_subAccountParams);

            TestDBUtility.deleteAll(MainAccountParams.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(1001L);
            TestDBUtility.insertWithDel(l_mainAccountParams);

            TestDBUtility.deleteAll(TraderParams.TYPE);
            TraderParams l_traderParams = TestDBUtility.getTraderRow();
            l_traderParams.setLoginId(1002L);
            l_traderParams.setBranchCode("3D");
            TestDBUtility.insertWithDel(l_traderParams);

            TestDBUtility.deleteAll(TradingTimeParams.TYPE);
            TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams.setInstitutionCode("0D");
            l_tradingTimeParams.setMarketCode("1");
            l_tradingTimeParams.setBranchCode("123");
            l_tradingTimeParams.setTradingTimeType("01");
            l_tradingTimeParams.setProductCode("0");
            l_tradingTimeParams.setBizDateType("1");
            TestDBUtility.insertWithDel(l_tradingTimeParams);

            TestDBUtility.deleteAll(ProductParams.TYPE);
            ProductParams l_productParams = TestDBUtility.getProductRow();
            l_productParams.setInstitutionCode("0D");
            TestDBUtility.insertWithDel(l_productParams);

            TestDBUtility.deleteAll(EqtypeProductParams.TYPE);
            EqtypeProductParams l_eqtypeProductParams = TestDBUtility.getEqtypeProductRow();
            l_eqtypeProductParams.setProductId(3304148080001L);
            l_eqtypeProductParams.setInstitutionCode("0D");
            l_eqtypeProductParams.setProductCode("0");
            TestDBUtility.insertWithDel(l_eqtypeProductParams);

            l_eqtypeProductParams.setProductId(3304148080000L);
            l_eqtypeProductParams.setInstitutionCode("0D");
            l_eqtypeProductParams.setProductCode("1");
            TestDBUtility.insertWithDel(l_eqtypeProductParams);

            TestDBUtility.deleteAll(BranchMarketRepayDealtCondParams.TYPE);
            BranchMarketRepayDealtCondParams l_branchMarketRepayDealtCondParams =
                TestDBUtility.getBranchMarketRepayDealCondRow();
            l_branchMarketRepayDealtCondParams.setInstitutionCode("0D");
            l_branchMarketRepayDealtCondParams.setBranchCode("381");
            l_branchMarketRepayDealtCondParams.setMarketCode("1");
            l_branchMarketRepayDealtCondParams.setRepaymentDiv("1");
            l_branchMarketRepayDealtCondParams.setRepaymentNum(1000);
            TestDBUtility.insertWithDel(l_branchMarketRepayDealtCondParams);

            TestDBUtility.deleteAll(EnableOrderConditionParams.TYPE);
            EnableOrderConditionParams l_enableOrderConditionParams =
                TestDBUtility.getEnableOrderConditionParamsRow();
            l_enableOrderConditionParams.setInstitutionCode("0D");
            l_enableOrderConditionParams.setProductType(ProductTypeEnum.EQUITY);
            l_enableOrderConditionParams.setFutureOptionDiv("0");
            l_enableOrderConditionParams.setMarginTradingDiv("0");
            l_enableOrderConditionParams.setMarketCode("1");
            TestDBUtility.insertWithDel(l_enableOrderConditionParams);

            WEB3MarginOpenMarginConfirmRequest l_confirmRequest = new WEB3MarginOpenMarginConfirmRequest();
            l_confirmRequest.expirationDate = WEB3DateUtility.getDate("20070620","yyyyMMdd");
            Date l_datExpirationDate =
                WEB3MarginOpenMarginRequestAdapter.create(l_confirmRequest).getExpirationDate();
            int l_intCompareToDay = WEB3DateUtility.compareToDay(
                    WEB3DateUtility.getDate("20070620","yyyyMMdd"), l_datExpirationDate);
            assertEquals(0, l_intCompareToDay);

            WEB3MarginOpenMarginCompleteRequest l_completeRequest = new WEB3MarginOpenMarginCompleteRequest();
            l_completeRequest.expirationDate = WEB3DateUtility.getDate("20070621","yyyyMMdd");
            l_datExpirationDate =
                WEB3MarginOpenMarginRequestAdapter.create(l_completeRequest).getExpirationDate();
            l_intCompareToDay = WEB3DateUtility.compareToDay(
                    WEB3DateUtility.getDate("20070620","yyyyMMdd"), l_datExpirationDate);
            assertEquals(-1, l_intCompareToDay);

            l_datExpirationDate =
                WEB3MarginOpenMarginRequestAdapter.create(null).getExpirationDate();
            fail();
        }
        catch (WEB3SystemLayerException l_web3SystemException)
        {
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80018,l_web3SystemException.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }
}
@
