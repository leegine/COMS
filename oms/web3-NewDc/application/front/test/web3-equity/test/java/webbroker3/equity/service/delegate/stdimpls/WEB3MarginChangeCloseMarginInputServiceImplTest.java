head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.00.54.51;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3MarginChangeCloseMarginInputServiceImplTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
 Copyright        : (äî)ëÂòaëçå§ èÿåîÉ\ÉäÉÖÅ[ÉVÉáÉìÉVÉXÉeÉÄëÊìÒïî
 File Name        : (WEB3MarginChangeCloseMarginInputServiceImplTest.java)
 Author Name      : Daiwa Institute of Research
 Revesion History : 2007/06/10  âΩï∂ïq(íÜêu)Å@@êVãKçÏê¨
 */
package webbroker3.equity.service.delegate.stdimpls;

import java.util.Calendar;
import java.util.Date;

import test.util.TestDBUtility;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.define.WEB3MarginTradingDivDef;
import webbroker3.equity.WEB3EquityProduct;
import webbroker3.equity.WEB3EquityProductQuote;
import webbroker3.equity.WEB3EquityTradedProduct;
import webbroker3.equity.message.WEB3MarginCloseMarginChangeInputRequest;
import webbroker3.equity.message.WEB3MarginCloseMarginChangeInputResponse;
import webbroker3.gentrade.WEB3GentradeAccountManager;
import webbroker3.gentrade.WEB3GentradeBranch;
import webbroker3.gentrade.WEB3GentradeMarket;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagementForMock;
import webbroker3.gentrade.data.EnableOrderConditionParams;
import webbroker3.gentrade.data.EnableOrderConditionRow;
import webbroker3.gentrade.data.TradingTimeParams;
import webbroker3.gentrade.data.TradingTimeRow;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3LogUtility;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeExecutionConditionType;
import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeOrderUnit;
import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeTradedProduct;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderUnitParams;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderUnitRow;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeProductParams;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeProductRow;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeTradedProductParams;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeTradedProductRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Branch;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderCategEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccountTypeEnum;
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

/**
 * @@author âΩï∂ïq(íÜêu)
 * @@version 1.0
 */
public class WEB3MarginChangeCloseMarginInputServiceImplTest extends TestBaseForMock
{
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3MarginChangeCloseMarginInputServiceImplTest.class);
    WEB3MarginChangeCloseMarginInputServiceImplForTest l_impl = new WEB3MarginChangeCloseMarginInputServiceImplForTest();
    public WEB3MarginChangeCloseMarginInputServiceImplTest(String arg0)
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

    public void testGetCloseMarginChangeInputScreen()
    {
        final String STR_METHOD_NAME = "testGetCloseMarginChangeInputScreen()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            TestDBUtility.deleteAll(InstitutionRow.TYPE);
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            l_institutionParams.setInstitutionId(33L);
            TestDBUtility.insertWithDel(l_institutionParams);
            
            TestDBUtility.deleteAll(EqtypeOrderUnitRow.TYPE);
            EqtypeOrderUnitParams l_eqtypeOrderUnitParams = TestDBUtility.getEqtypeOrderUnitRow();
            l_eqtypeOrderUnitParams.setExecutionConditionType(EqTypeExecutionConditionType.AT_MARKET_OPEN);
            l_eqtypeOrderUnitParams.setRepaymentType("1");
            l_eqtypeOrderUnitParams.setForcedSettleReasonType("2");
            l_eqtypeOrderUnitParams.setForcedExpireType("5");
            TestDBUtility.insertWithDel(l_eqtypeOrderUnitParams);
            
            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setAccountId(333812512246L);
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_MARGIN_SUB_ACCOUNT);
            TestDBUtility.insertWithDel(l_subAccountParams);
            
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            TestDBUtility.insertWithDel(l_mainAccountParams);
            
            TestDBUtility.deleteAll(ProductRow.TYPE);
            ProductParams l_productParams = TestDBUtility.getProductRow();
            l_productParams.setProductId(3304148080001L);
            l_productParams.setProductType(ProductTypeEnum.EQUITY);
            TestDBUtility.insertWithDel(l_productParams);
            
            TestDBUtility.deleteAll(MarketRow.TYPE);
            MarketParams l_marketParams = TestDBUtility.getMarketRow();
            l_marketParams.setMarketId(3303);
            TestDBUtility.insertWithDel(l_marketParams);
            
            TestDBUtility.deleteAll(BranchRow.TYPE);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            TestDBUtility.insertWithDel(l_branchParams);
            
            TestDBUtility.deleteAll(EqtypeTradedProductRow.TYPE);
            EqtypeTradedProductParams l_eqtypeTradedProductParams = TestDBUtility.getEqtypeTradedProductRow();
            TestDBUtility.insertWithDel(l_eqtypeTradedProductParams);
            
            TestDBUtility.deleteAll(EqtypeProductRow.TYPE);
            EqtypeProductParams l_eqtypeProductParams = TestDBUtility.getEqtypeProductRow();
            l_eqtypeProductParams.setProductId(3304148080001L);
            TestDBUtility.insertWithDel(l_eqtypeProductParams);
            
            TestDBUtility.deleteAll(EnableOrderConditionRow.TYPE);
            EnableOrderConditionParams l_enableOrderConditionParams = TestDBUtility.getEnableOrderConditionParamsRow();
            l_enableOrderConditionParams.setInstitutionCode("0D");
            l_enableOrderConditionParams.setProductType(ProductTypeEnum.EQUITY);
            l_enableOrderConditionParams.setFutureOptionDiv("0");
            l_enableOrderConditionParams.setMarginTradingDiv("0");
            l_enableOrderConditionParams.setMarketCode("SP");
            TestDBUtility.insertWithDel(l_enableOrderConditionParams);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                    "getAccountId",
                    new Class[] {},
                    new Long(333812512246L));
            
           TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.equity.WEB3EquityOrderManager",
                    "validateTradedProductForMarginTrading",
                    new Class[] {SubAccount.class,
                        WEB3EquityProduct.class, 
                        WEB3GentradeMarket.class, 
                        WEB3GentradeBranch.class, 
                        String.class, 
                        OrderCategEnum.class, 
                        boolean.class,
                        boolean.class},
                        null);
           
           TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.equity.WEB3EquityOrderManager",
                    "validateHandlingMarket",
                    new Class[] {WEB3GentradeBranch.class,
                            WEB3EquityTradedProduct.class,
                            String.class,
                            String.class,
                            double.class},
                            null);
           
           FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
           WEB3GentradeAccountManager l_accountMgr = (WEB3GentradeAccountManager)l_finApp.getAccountManager();

           WEB3GentradeBranch l_gentradeBranch = (WEB3GentradeBranch)l_accountMgr.getMainAccount(333812512246L).getBranch();
           String[] l_strExpectValue = new String[]{new String("1")};
           WEB3GentradeTradingTimeManagementForMock.mockGetTradeCloseMarket(
               l_gentradeBranch,
               ProductTypeEnum.EQUITY,
               WEB3MarginTradingDivDef.MARKET_MARGIN,
               l_strExpectValue);

           MOCK_MANAGER.setIsMockUsed(true);
           TestDBUtility.deleteAll(TradingTimeRow.TYPE);
           
           TradingTimeParams l_tradingTimeParams = new TradingTimeParams();

           l_tradingTimeParams.setInstitutionCode("0D");
           l_tradingTimeParams.setBranchCode("123");
           l_tradingTimeParams.setMarketCode("SP");
           l_tradingTimeParams.setTradingTimeType("01");
           l_tradingTimeParams.setProductCode("0");
           l_tradingTimeParams.setBizDateType("1");
           l_tradingTimeParams.setStartTime("000000");
           l_tradingTimeParams.setEndTime("235959");        
           l_tradingTimeParams.setSubmitMarketTrigger("0");
           l_tradingTimeParams.setEnableOrder("0");
           l_tradingTimeParams.setBizdateCalcParameter("0");
           l_tradingTimeParams.setCreatedTimestamp(Calendar.getInstance().getTime());
           l_tradingTimeParams.setLastUpdatedTimestamp(Calendar.getInstance().getTime());
           
           TestDBUtility.insertWithDel(l_tradingTimeParams);

           TestBaseForMock.MOCK_MANAGER.setMethodReturnValue("webbroker3.equity.WEB3EquityOrderManager",
                   "validateMarginOrder", new Class[]
                   { WEB3GentradeSubAccount.class, String.class },
                   null);
           
           TestBaseForMock.MOCK_MANAGER.setMethodReturnValue("webbroker3.equity.WEB3EquityOrderManager",
                   "validateMarginSpecialAccountOpen", new Class[]
                   { WEB3GentradeSubAccount.class, TaxTypeEnum.class, Date.class },
                   null);
           
           WEB3EquityProductQuote l_productQuote = new WEB3EquityProductQuote();
           TestBaseForMock.MOCK_MANAGER.setMethodReturnValue("webbroker3.equity.WEB3EquityProductManager",
                   "getDisplayEquityProductQuote", new Class[]
                   { EqTypeTradedProduct.class, WEB3GentradeSubAccount.class },
                   l_productQuote);
           
           TestBaseForMock.MOCK_MANAGER.setMethodReturnValue("webbroker3.equity.WEB3EquityOrderManager",
                   "getOrderPriceDivs", new Class[]
                   { Branch.class, EqTypeTradedProduct.class },
                   new String[]{"123"});
           
            WEB3MarginCloseMarginChangeInputRequest l_request = new WEB3MarginCloseMarginChangeInputRequest();
            l_request.id = "123456789";
            WEB3MarginCloseMarginChangeInputResponse l_response =
                l_impl.getCloseMarginChangeInputScreen(l_request);
            assertEquals("2", l_response.forcedSettleReason);
        }
        catch (Exception l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    public class WEB3MarginChangeCloseMarginInputServiceImplForTest extends WEB3MarginChangeCloseMarginInputServiceImpl
    {
        protected void validateOrderForChangeability(
                EqTypeOrderUnit l_orderUnit)
                throws WEB3BaseException
        {
        
        }
    }
}
@
