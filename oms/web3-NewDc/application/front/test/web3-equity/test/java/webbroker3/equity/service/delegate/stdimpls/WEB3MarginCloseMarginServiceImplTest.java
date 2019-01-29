head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.00.55.40;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3MarginCloseMarginServiceImplTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
 Copyright        : (äî)ëÂòaëçå§ èÿåîÉ\ÉäÉÖÅ[ÉVÉáÉìÉVÉXÉeÉÄëÊìÒïî
 File Name        : (WEB3MarginCloseMarginServiceImplTest.java)
 Author Name      : Daiwa Institute of Research
 Revesion History : 2007/06/10  âΩï∂ïq(íÜêu)Å@@êVãKçÏê¨
 */
package webbroker3.equity.service.delegate.stdimpls;

import java.util.Calendar;
import java.util.Date;

import test.util.TestDBUtility;
import webbroker3.common.WEB3BaseException;
import webbroker3.equity.WEB3EquityContract;
import webbroker3.equity.WEB3EquityRealizedProfitAndLossPrice;
import webbroker3.equity.WEB3EquityTradedProduct;
import webbroker3.equity.WEB3MarginCloseMarginUpdateInterceptor;
import webbroker3.equity.WEB3MarginSettleContractOrderSpec;
import webbroker3.equity.message.WEB3MarginCloseMarginCompleteRequest;
import webbroker3.equity.message.WEB3MarginCloseMarginCompleteResponse;
import webbroker3.equity.message.WEB3MarginCloseMarginConfirmRequest;
import webbroker3.equity.message.WEB3MarginCloseMarginConfirmResponse;
import webbroker3.equity.message.WEB3MarginCloseMarginContractUnit;
import webbroker3.equity.message.WEB3MarginContractUnit;
import webbroker3.equity.service.delegate.WEB3MarginCloseMarginRequestAdapter;
import webbroker3.gentrade.WEB3GentradeCommission;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagementForMock;
import webbroker3.gentrade.data.BranchMarketRepayDealtCondParams;
import webbroker3.gentrade.data.BranchMarketRepayDealtCondRow;
import webbroker3.gentrade.data.TaxRateTableParams;
import webbroker3.gentrade.data.TaxRateTableRow;
import webbroker3.gentrade.data.TradingTimeParams;
import webbroker3.gentrade.data.TradingTimeRow;
import webbroker3.mock.TestBaseForMock;
import webbroker3.tradingpower.WEB3TPAttentionObjection;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

import com.fitechlabs.xtrade.plugin.security.oplogin.LoginInfo;
import com.fitechlabs.xtrade.plugin.security.oplogin.impl.LoginInfoImpl;
import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeOrderUnit;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeContractParams;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeContractRow;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeProductParams;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeTradedProductParams;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeTradedProductRow;
import com.fitechlabs.xtrade.plugin.tc.eqtype.ordersubmitter.io.EqTypeNewOrderValidationResult;
import com.fitechlabs.xtrade.plugin.tc.eqtype.ordersubmitter.io.EqTypeSettleContractOrderEntry;
import com.fitechlabs.xtrade.plugin.tc.eqtype.ordersubmitter.io.EqTypeSettleContractOrderSpec;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderCategEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProcessingResult;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccount;
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
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.TraderParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.TraderRow;

/**
 * @@author âΩï∂ïq(íÜêu)
 * @@version 1.0
 */
public class WEB3MarginCloseMarginServiceImplTest extends TestBaseForMock
{
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3MarginCloseMarginServiceImplTest.class);
    WEB3MarginCloseMarginServiceImplForTest l_impl =
        new WEB3MarginCloseMarginServiceImplForTest();

    public WEB3MarginCloseMarginServiceImplTest(String arg0)
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
    
    public void testValidateOrder()
    {
        final String STR_METHOD_NAME = "testValidateOrder()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            WEB3MarginCloseMarginConfirmRequest l_request =
                new WEB3MarginCloseMarginConfirmRequest();
            WEB3MarginCloseMarginContractUnit l_unit =
                new WEB3MarginCloseMarginContractUnit();
            l_unit.id = "123456";
            l_unit.orderQuantity = "100";
            l_request.orderPriceDiv = "0";
            l_unit.settlePriority = "123";
            l_request.orderCondType = "0";
            l_request.priceCondType = "0";
            l_request.expirationDateType = "1";
            l_request.orderCondType = "1";
            l_request.execCondType = "1";
            l_request.stopOrderCondPrice = "15";
            l_request.stopOrderCondOperator = "1";
            l_request.manualForcedSettleFlag = false;
            
            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setAccountId(333812512246L);
            TestDBUtility.insertWithDel(l_subAccountParams);
            
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            TestDBUtility.insertWithDel(l_mainAccountParams);
            
            TestDBUtility.deleteAll(TraderRow.TYPE);
            TraderParams l_traderParams = TestDBUtility.getTraderRow();
            TestDBUtility.insertWithDel(l_traderParams);
            
            TestDBUtility.deleteAll(EqtypeContractRow.TYPE);
            EqtypeContractParams l_eqtypeContractParams = TestDBUtility.getEqtypeContractRow();
            l_eqtypeContractParams.setContractId(123);
            l_eqtypeContractParams.setCloseDate(WEB3DateUtility.getDate("20400924","yyyyMMdd"));
            l_eqtypeContractParams.setProductId(12342L);
            l_eqtypeContractParams.setProductType(ProductTypeEnum.EQUITY);
            l_eqtypeContractParams.setRepaymentType("0");
            TestDBUtility.insertWithDel(l_eqtypeContractParams);

            l_eqtypeContractParams.setContractId(123456);
            l_eqtypeContractParams.setCloseDate(WEB3DateUtility.getDate("20400924","yyyyMMdd"));
            l_eqtypeContractParams.setProductId(12342L);
            l_eqtypeContractParams.setProductType(ProductTypeEnum.EQUITY);
            l_eqtypeContractParams.setRepaymentType("0");
            TestDBUtility.insertWithDel(l_eqtypeContractParams);

            TestDBUtility.deleteAll(MarketRow.TYPE);
            MarketParams l_marketParams = TestDBUtility.getMarketRow();
            l_marketParams.setMarketId(33381);
            TestDBUtility.insertWithDel(l_marketParams);
            
            TestDBUtility.deleteAll(ProductRow.TYPE);
            ProductParams l_producttParams = TestDBUtility.getProductRow();
            l_producttParams.setProductId(12342);
            l_producttParams.setProductType(ProductTypeEnum.EQUITY);
            TestDBUtility.insertWithDel(l_producttParams);
            
            //EqtypeProductParams
            TestDBUtility.deleteAll(EqtypeProductParams.TYPE);
            EqtypeProductParams l_eqtypeProductParams =
                TestDBUtility.getEqtypeProductRow();
            l_eqtypeProductParams.setProductId(12342);
            TestDBUtility.insertWithDel(l_eqtypeProductParams);
            
            TestDBUtility.deleteAll(TradedProductRow.TYPE);
            TradedProductParams l_tradedProductParams = TestDBUtility.getTradedProductRow();
            l_tradedProductParams.setProductType(ProductTypeEnum.EQUITY);
            l_tradedProductParams.setProductId(12342L);
            l_tradedProductParams.setMarketId(33381L);
            l_tradedProductParams.setTradedProductId(330304148080000L);
            TestDBUtility.insertWithDel(l_tradedProductParams);
            
            TestDBUtility.deleteAll(EqtypeTradedProductRow.TYPE);
            EqtypeTradedProductParams l_eqtypeTradedProductParams = TestDBUtility.getEqtypeTradedProductRow();
            l_eqtypeTradedProductParams.setTradedProductId(330304148080000L);
            l_eqtypeTradedProductParams.setProductId(12342L);
            l_eqtypeTradedProductParams.setMarketId(33381L);
            TestDBUtility.insertWithDel(l_eqtypeTradedProductParams);
            
            TestDBUtility.deleteAll(BranchMarketRepayDealtCondRow.TYPE);
            BranchMarketRepayDealtCondParams l_branchMarketRepayDealtCondParams = TestDBUtility.getBranchMarketRepayDealCondRow();
            l_branchMarketRepayDealtCondParams.setInstitutionCode("0D");
            l_branchMarketRepayDealtCondParams.setBranchCode("381");
            l_branchMarketRepayDealtCondParams.setMarketCode("SP");
            l_branchMarketRepayDealtCondParams.setRepaymentNum(0);
            l_branchMarketRepayDealtCondParams.setRepaymentDiv("0");
            TestDBUtility.insertWithDel(l_branchMarketRepayDealtCondParams);
            
            TestDBUtility.deleteAll(TradingTimeRow.TYPE);
            TradingTimeParams l_tradingTimeparams = TestDBUtility.getTradingTimeRow();
            l_tradingTimeparams.setMarketCode("SP");
            l_tradingTimeparams.setBranchCode("123");
            l_tradingTimeparams.setBizDateType("1");
            l_tradingTimeparams.setTradingTimeType("01");
            TestDBUtility.insertWithDel(l_tradingTimeparams);

            TestDBUtility.deleteAll(TaxRateTableRow.TYPE);
            TaxRateTableParams l_taxRateTableParams = new TaxRateTableParams();
            l_taxRateTableParams.setTaxType("10");
            l_taxRateTableParams.setAppliEndDate(WEB3DateUtility.getDate("21111212", "yyyyMMdd"));
            l_taxRateTableParams.setAppliStartDate(WEB3DateUtility.getDate("20070203", "yyyyMMdd"));
            l_taxRateTableParams.setCreatedTimestamp(Calendar.getInstance().getTime());
            l_taxRateTableParams.setInstitutionCode("0D");
            l_taxRateTableParams.setLastUpdatedTimestamp(Calendar.getInstance().getTime());
            l_taxRateTableParams.setTaxRate(0.10D);
            TestDBUtility.insertWithDel(l_taxRateTableParams);
            
            WEB3GentradeCommission l_commission = new WEB3GentradeCommission();
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.equity.WEB3EquityBizLogicProvider",
                "createCommission",
                new Class[]{WEB3GentradeSubAccount.class,
                    String.class,Date.class,String.class,
                    String.class,double.class,OrderCategEnum.class},
                l_commission);

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getAccountId",
                new Class[] {},
                new Long(333812512246L));

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getLoginId",
                new Class[] {},
                new Long(3338111123L));
            
            LoginInfo l_info = new LoginInfoImpl();
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl", "getLoginInfo",
                new Class[] {},
                l_info);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl", "getSessionProperty",
                new Class[] {String.class},
                "123");
                    
            l_request.closeMarginContractUnits = new WEB3MarginCloseMarginContractUnit[]{l_unit};
            l_request.closingOrder = "0";

            EqTypeNewOrderValidationResult l_eqTypeNewOrderValidationResult =
                new EqTypeNewOrderValidationResult(ProcessingResult.SUCCESS_RESULT);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.equity.WEB3EquityOrderManager",
                "validateSettleContractOrder",
                new Class[] {SubAccount.class, EqTypeSettleContractOrderSpec.class, WEB3EquityContract.class},
                l_eqTypeNewOrderValidationResult);

            WEB3GentradeTradingTimeManagementForMock.mockGetOrderBizDate(Calendar.getInstance().getTime());
            WEB3MarginCloseMarginConfirmResponse l_response = (WEB3MarginCloseMarginConfirmResponse)l_impl.validateOrder(l_request);
            log.debug("***************** expirationDate = " + l_response.expirationDate);
            assertEquals("", WEB3DateUtility.formatDate(l_response.expirationDate, "yyyyMMdd"));
        }
        catch (Exception l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testValidateOrder_Case0001()
    {
        final String STR_METHOD_NAME = "testValidateOrder_Case0001()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            WEB3MarginCloseMarginConfirmRequest l_request =
                new WEB3MarginCloseMarginConfirmRequest();
            WEB3MarginCloseMarginContractUnit l_unit =
                new WEB3MarginCloseMarginContractUnit();
            l_unit.id = "123456";
            l_unit.orderQuantity = "100";
            l_request.orderPriceDiv = "0";
            l_unit.settlePriority = "123";
            l_request.orderCondType = "0";
            l_request.priceCondType = "0";
            l_request.expirationDateType = "2";
            l_request.expirationDate = WEB3DateUtility.getDate("20060507", "yyyyMMdd");
            l_request.orderCondType = "1";
            l_request.execCondType = "1";
            l_request.stopOrderCondPrice = "15";
            l_request.stopOrderCondOperator = "1";
            l_request.manualForcedSettleFlag = false;
            
            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setAccountId(333812512246L);
            TestDBUtility.insertWithDel(l_subAccountParams);
            
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            TestDBUtility.insertWithDel(l_mainAccountParams);
            
            TestDBUtility.deleteAll(TraderRow.TYPE);
            TraderParams l_traderParams = TestDBUtility.getTraderRow();
            TestDBUtility.insertWithDel(l_traderParams);
            
            TestDBUtility.deleteAll(EqtypeContractRow.TYPE);
            EqtypeContractParams l_eqtypeContractParams = TestDBUtility.getEqtypeContractRow();
            l_eqtypeContractParams.setContractId(123);
            l_eqtypeContractParams.setCloseDate(WEB3DateUtility.getDate("20400924","yyyyMMdd"));
            l_eqtypeContractParams.setProductId(12342L);
            l_eqtypeContractParams.setProductType(ProductTypeEnum.EQUITY);
            l_eqtypeContractParams.setRepaymentType("0");
            TestDBUtility.insertWithDel(l_eqtypeContractParams);

            l_eqtypeContractParams.setContractId(123456);
            l_eqtypeContractParams.setCloseDate(WEB3DateUtility.getDate("20400924","yyyyMMdd"));
            l_eqtypeContractParams.setProductId(12342L);
            l_eqtypeContractParams.setProductType(ProductTypeEnum.EQUITY);
            l_eqtypeContractParams.setRepaymentType("0");
            TestDBUtility.insertWithDel(l_eqtypeContractParams);

            TestDBUtility.deleteAll(MarketRow.TYPE);
            MarketParams l_marketParams = TestDBUtility.getMarketRow();
            l_marketParams.setMarketId(33381);
            TestDBUtility.insertWithDel(l_marketParams);
            
            TestDBUtility.deleteAll(ProductRow.TYPE);
            ProductParams l_producttParams = TestDBUtility.getProductRow();
            l_producttParams.setProductId(12342);
            l_producttParams.setProductType(ProductTypeEnum.EQUITY);
            TestDBUtility.insertWithDel(l_producttParams);
            
            //EqtypeProductParams
            TestDBUtility.deleteAll(EqtypeProductParams.TYPE);
            EqtypeProductParams l_eqtypeProductParams =
                TestDBUtility.getEqtypeProductRow();
            l_eqtypeProductParams.setProductId(12342);
            TestDBUtility.insertWithDel(l_eqtypeProductParams);
            
            TestDBUtility.deleteAll(TradedProductRow.TYPE);
            TradedProductParams l_tradedProductParams = TestDBUtility.getTradedProductRow();
            l_tradedProductParams.setProductType(ProductTypeEnum.EQUITY);
            l_tradedProductParams.setProductId(12342L);
            l_tradedProductParams.setMarketId(33381L);
            l_tradedProductParams.setTradedProductId(330304148080000L);
            TestDBUtility.insertWithDel(l_tradedProductParams);
            
            TestDBUtility.deleteAll(EqtypeTradedProductRow.TYPE);
            EqtypeTradedProductParams l_eqtypeTradedProductParams = TestDBUtility.getEqtypeTradedProductRow();
            l_eqtypeTradedProductParams.setTradedProductId(330304148080000L);
            l_eqtypeTradedProductParams.setProductId(12342L);
            l_eqtypeTradedProductParams.setMarketId(33381L);
            TestDBUtility.insertWithDel(l_eqtypeTradedProductParams);
            
            TestDBUtility.deleteAll(BranchMarketRepayDealtCondRow.TYPE);
            BranchMarketRepayDealtCondParams l_branchMarketRepayDealtCondParams = TestDBUtility.getBranchMarketRepayDealCondRow();
            l_branchMarketRepayDealtCondParams.setInstitutionCode("0D");
            l_branchMarketRepayDealtCondParams.setBranchCode("381");
            l_branchMarketRepayDealtCondParams.setMarketCode("SP");
            l_branchMarketRepayDealtCondParams.setRepaymentNum(0);
            l_branchMarketRepayDealtCondParams.setRepaymentDiv("0");
            TestDBUtility.insertWithDel(l_branchMarketRepayDealtCondParams);
            
            TestDBUtility.deleteAll(TradingTimeRow.TYPE);
            TradingTimeParams l_tradingTimeparams = TestDBUtility.getTradingTimeRow();
            l_tradingTimeparams.setMarketCode("SP");
            l_tradingTimeparams.setBranchCode("123");
            l_tradingTimeparams.setBizDateType("1");
            l_tradingTimeparams.setTradingTimeType("01");
            TestDBUtility.insertWithDel(l_tradingTimeparams);

            TestDBUtility.deleteAll(TaxRateTableRow.TYPE);
            TaxRateTableParams l_taxRateTableParams = new TaxRateTableParams();
            l_taxRateTableParams.setTaxType("10");
            l_taxRateTableParams.setAppliEndDate(WEB3DateUtility.getDate("21111212", "yyyyMMdd"));
            l_taxRateTableParams.setAppliStartDate(WEB3DateUtility.getDate("20070203", "yyyyMMdd"));
            l_taxRateTableParams.setCreatedTimestamp(Calendar.getInstance().getTime());
            l_taxRateTableParams.setInstitutionCode("0D");
            l_taxRateTableParams.setLastUpdatedTimestamp(Calendar.getInstance().getTime());
            l_taxRateTableParams.setTaxRate(0.10D);
            TestDBUtility.insertWithDel(l_taxRateTableParams);
            
            WEB3GentradeCommission l_commission = new WEB3GentradeCommission();
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.equity.WEB3EquityBizLogicProvider",
                "createCommission",
                new Class[]{WEB3GentradeSubAccount.class,
                        String.class,Date.class,String.class,
                        String.class,double.class,OrderCategEnum.class},
                l_commission);

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getAccountId",
                new Class[] {},
                new Long(333812512246L));

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getLoginId",
                new Class[] {},
                new Long(3338111123L));
            
            LoginInfo l_info = new LoginInfoImpl();
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl", "getLoginInfo",
                new Class[] {},
                l_info);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getSessionProperty",
                new Class[] {String.class},
                "123");

            EqTypeNewOrderValidationResult l_eqTypeNewOrderValidationResult =
                new EqTypeNewOrderValidationResult(ProcessingResult.SUCCESS_RESULT);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.equity.WEB3EquityOrderManager",
                "validateSettleContractOrder",
                new Class[] {SubAccount.class, EqTypeSettleContractOrderSpec.class, WEB3EquityContract.class},
                l_eqTypeNewOrderValidationResult);

            l_request.closeMarginContractUnits = new WEB3MarginCloseMarginContractUnit[]{l_unit};
            l_request.closingOrder = "0";
            
            
            WEB3GentradeTradingTimeManagementForMock.mockGetOrderBizDate(Calendar.getInstance().getTime());
            WEB3MarginCloseMarginConfirmResponse l_response = (WEB3MarginCloseMarginConfirmResponse)l_impl.validateOrder(l_request);
            log.debug("***************** expirationDate = " + l_response.expirationDate);
            assertEquals("20060507", WEB3DateUtility.formatDate(l_response.expirationDate, "yyyyMMdd"));
        }
        catch (Exception l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testSubmitOrder()
    {
        final String STR_METHOD_NAME = "testSubmitOrder()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            WEB3MarginCloseMarginCompleteRequest l_request =
                new WEB3MarginCloseMarginCompleteRequest();
            WEB3MarginCloseMarginContractUnit l_unit =
                new WEB3MarginCloseMarginContractUnit();
            l_unit.id = "123456";
            l_unit.orderQuantity = "100";
            l_request.orderPriceDiv = "0";
            l_unit.settlePriority = "123";
            l_request.orderCondType = "0";
            l_request.priceCondType = "0";
            l_request.expirationDateType = "1";
            l_request.orderCondType = "1";
            l_request.execCondType = "1";
            l_request.stopOrderCondPrice = "15";
            l_request.stopOrderCondOperator = "1";
            l_request.manualForcedSettleFlag = false;
            
            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setAccountId(333812512246L);
            TestDBUtility.insertWithDel(l_subAccountParams);
            
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            TestDBUtility.insertWithDel(l_mainAccountParams);
            
            TestDBUtility.deleteAll(TraderRow.TYPE);
            TraderParams l_traderParams = TestDBUtility.getTraderRow();
            TestDBUtility.insertWithDel(l_traderParams);
            
            TestDBUtility.deleteAll(EqtypeContractRow.TYPE);
            EqtypeContractParams l_eqtypeContractParams = TestDBUtility.getEqtypeContractRow();
            l_eqtypeContractParams.setContractId(123);
            l_eqtypeContractParams.setCloseDate(WEB3DateUtility.getDate("20400924","yyyyMMdd"));
            l_eqtypeContractParams.setProductId(12342L);
            l_eqtypeContractParams.setProductType(ProductTypeEnum.EQUITY);
            l_eqtypeContractParams.setRepaymentType("0");
            TestDBUtility.insertWithDel(l_eqtypeContractParams);

            l_eqtypeContractParams.setContractId(123456);
            l_eqtypeContractParams.setCloseDate(WEB3DateUtility.getDate("20400924","yyyyMMdd"));
            l_eqtypeContractParams.setProductId(12342L);
            l_eqtypeContractParams.setProductType(ProductTypeEnum.EQUITY);
            l_eqtypeContractParams.setRepaymentType("0");
            TestDBUtility.insertWithDel(l_eqtypeContractParams);

            TestDBUtility.deleteAll(MarketRow.TYPE);
            MarketParams l_marketParams = TestDBUtility.getMarketRow();
            l_marketParams.setMarketId(33381);
            TestDBUtility.insertWithDel(l_marketParams);
            
            TestDBUtility.deleteAll(ProductRow.TYPE);
            ProductParams l_producttParams = TestDBUtility.getProductRow();
            l_producttParams.setProductId(12342);
            l_producttParams.setProductType(ProductTypeEnum.EQUITY);
            TestDBUtility.insertWithDel(l_producttParams);
            
            //EqtypeProductParams
            TestDBUtility.deleteAll(EqtypeProductParams.TYPE);
            EqtypeProductParams l_eqtypeProductParams =
                TestDBUtility.getEqtypeProductRow();
            l_eqtypeProductParams.setProductId(12342);
            TestDBUtility.insertWithDel(l_eqtypeProductParams);
            
            TestDBUtility.deleteAll(TradedProductRow.TYPE);
            TradedProductParams l_tradedProductParams = TestDBUtility.getTradedProductRow();
            l_tradedProductParams.setProductType(ProductTypeEnum.EQUITY);
            l_tradedProductParams.setProductId(12342L);
            l_tradedProductParams.setMarketId(33381L);
            l_tradedProductParams.setTradedProductId(330304148080000L);
            l_tradedProductParams.setProductType(ProductTypeEnum.EQUITY);
            TestDBUtility.insertWithDel(l_tradedProductParams);
            
            TestDBUtility.deleteAll(EqtypeTradedProductRow.TYPE);
            EqtypeTradedProductParams l_eqtypeTradedProductParams = TestDBUtility.getEqtypeTradedProductRow();
            l_eqtypeTradedProductParams.setTradedProductId(330304148080000L);
            l_eqtypeTradedProductParams.setProductId(12342L);
            l_eqtypeTradedProductParams.setMarketId(33381L);
            TestDBUtility.insertWithDel(l_eqtypeTradedProductParams);
            
            TestDBUtility.deleteAll(BranchMarketRepayDealtCondRow.TYPE);
            BranchMarketRepayDealtCondParams l_branchMarketRepayDealtCondParams = TestDBUtility.getBranchMarketRepayDealCondRow();
            l_branchMarketRepayDealtCondParams.setInstitutionCode("0D");
            l_branchMarketRepayDealtCondParams.setBranchCode("381");
            l_branchMarketRepayDealtCondParams.setMarketCode("SP");
            l_branchMarketRepayDealtCondParams.setRepaymentNum(0);
            l_branchMarketRepayDealtCondParams.setRepaymentDiv("0");
            TestDBUtility.insertWithDel(l_branchMarketRepayDealtCondParams);
            
            TestDBUtility.deleteAll(TradingTimeRow.TYPE);
            TradingTimeParams l_tradingTimeparams = TestDBUtility.getTradingTimeRow();
            l_tradingTimeparams.setMarketCode("SP");
            l_tradingTimeparams.setBranchCode("123");
            l_tradingTimeparams.setBizDateType("1");
            l_tradingTimeparams.setTradingTimeType("01");
            TestDBUtility.insertWithDel(l_tradingTimeparams);

            TestDBUtility.deleteAll(TaxRateTableRow.TYPE);
            TaxRateTableParams l_taxRateTableParams = new TaxRateTableParams();
            l_taxRateTableParams.setTaxType("10");
            l_taxRateTableParams.setAppliEndDate(WEB3DateUtility.getDate("21111212", "yyyyMMdd"));
            l_taxRateTableParams.setAppliStartDate(WEB3DateUtility.getDate("20070203", "yyyyMMdd"));
            l_taxRateTableParams.setCreatedTimestamp(Calendar.getInstance().getTime());
            l_taxRateTableParams.setInstitutionCode("0D");
            l_taxRateTableParams.setLastUpdatedTimestamp(Calendar.getInstance().getTime());
            l_taxRateTableParams.setTaxRate(0.10D);
            TestDBUtility.insertWithDel(l_taxRateTableParams);
            
            WEB3GentradeCommission l_commission = new WEB3GentradeCommission();
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.equity.WEB3EquityBizLogicProvider",
                "createCommission",
                new Class[]{WEB3GentradeSubAccount.class,
                        String.class,Date.class,String.class,
                        String.class,double.class,OrderCategEnum.class},
                l_commission);

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getAccountId",
                new Class[] {},
                new Long(333812512246L));

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getLoginId",
                new Class[] {},
                new Long(3338111123L));
            
            LoginInfo l_info = new LoginInfoImpl();
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl", "getLoginInfo",
                new Class[] {},
                l_info);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getSessionProperty",
                new Class[] {String.class},
                "123");

            EqTypeNewOrderValidationResult l_eqTypeNewOrderValidationResult =
                new EqTypeNewOrderValidationResult(ProcessingResult.SUCCESS_RESULT);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.equity.WEB3EquityOrderManager",
                "validateSettleContractOrder",
                new Class[] {SubAccount.class, EqTypeSettleContractOrderSpec.class, WEB3EquityContract.class},
                l_eqTypeNewOrderValidationResult);

            l_request.closeMarginContractUnits = new WEB3MarginCloseMarginContractUnit[]{l_unit};
            l_request.closingOrder = "0";

            WEB3GentradeTradingTimeManagementForMock.mockGetOrderBizDate(Calendar.getInstance().getTime());
            WEB3MarginCloseMarginCompleteResponse l_response = l_impl.submitOrder(l_request);
            log.debug("***************** expirationDate = " + l_response.expirationDate);
            assertEquals("", WEB3DateUtility.formatDate(l_response.expirationDate, "yyyyMMdd"));
        }
        catch (Exception l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testSubmitOrder_Case0001()
    {
        final String STR_METHOD_NAME = "testSubmitOrder_Case0001()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            WEB3MarginCloseMarginCompleteRequest l_request =
                new WEB3MarginCloseMarginCompleteRequest();
            WEB3MarginCloseMarginContractUnit l_unit =
                new WEB3MarginCloseMarginContractUnit();
            l_unit.id = "123456";
            l_unit.orderQuantity = "100";
            l_request.orderPriceDiv = "0";
            l_unit.settlePriority = "123";
            l_request.orderCondType = "0";
            l_request.priceCondType = "0";
            l_request.expirationDateType = "2";
            l_request.expirationDate = WEB3DateUtility.getDate("20060507", "yyyyMMdd");
            l_request.orderCondType = "1";
            l_request.execCondType = "1";
            l_request.stopOrderCondPrice = "15";
            l_request.stopOrderCondOperator = "1";
            l_request.manualForcedSettleFlag = false;
            
            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setAccountId(333812512246L);
            TestDBUtility.insertWithDel(l_subAccountParams);
            
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            TestDBUtility.insertWithDel(l_mainAccountParams);
            
            TestDBUtility.deleteAll(TraderRow.TYPE);
            TraderParams l_traderParams = TestDBUtility.getTraderRow();
            TestDBUtility.insertWithDel(l_traderParams);
            
            TestDBUtility.deleteAll(EqtypeContractRow.TYPE);
            EqtypeContractParams l_eqtypeContractParams = TestDBUtility.getEqtypeContractRow();
            l_eqtypeContractParams.setContractId(123);
            l_eqtypeContractParams.setCloseDate(WEB3DateUtility.getDate("20400924","yyyyMMdd"));
            l_eqtypeContractParams.setProductId(12342L);
            l_eqtypeContractParams.setProductType(ProductTypeEnum.EQUITY);
            l_eqtypeContractParams.setRepaymentType("0");
            TestDBUtility.insertWithDel(l_eqtypeContractParams);

            l_eqtypeContractParams.setContractId(123456);
            l_eqtypeContractParams.setCloseDate(WEB3DateUtility.getDate("20400924","yyyyMMdd"));
            l_eqtypeContractParams.setProductId(12342L);
            l_eqtypeContractParams.setProductType(ProductTypeEnum.EQUITY);
            l_eqtypeContractParams.setRepaymentType("0");
            TestDBUtility.insertWithDel(l_eqtypeContractParams);

            TestDBUtility.deleteAll(MarketRow.TYPE);
            MarketParams l_marketParams = TestDBUtility.getMarketRow();
            l_marketParams.setMarketId(33381);
            TestDBUtility.insertWithDel(l_marketParams);
            
            TestDBUtility.deleteAll(ProductRow.TYPE);
            ProductParams l_producttParams = TestDBUtility.getProductRow();
            l_producttParams.setProductId(12342);
            l_producttParams.setProductType(ProductTypeEnum.EQUITY);
            TestDBUtility.insertWithDel(l_producttParams);
            
            //EqtypeProductParams
            TestDBUtility.deleteAll(EqtypeProductParams.TYPE);
            EqtypeProductParams l_eqtypeProductParams =
                TestDBUtility.getEqtypeProductRow();
            l_eqtypeProductParams.setProductId(12342);
            TestDBUtility.insertWithDel(l_eqtypeProductParams);
            
            TestDBUtility.deleteAll(TradedProductRow.TYPE);
            TradedProductParams l_tradedProductParams = TestDBUtility.getTradedProductRow();
            l_tradedProductParams.setProductType(ProductTypeEnum.EQUITY);
            l_tradedProductParams.setProductId(12342L);
            l_tradedProductParams.setMarketId(33381L);
            l_tradedProductParams.setTradedProductId(330304148080000L);
            l_tradedProductParams.setProductType(ProductTypeEnum.EQUITY);
            TestDBUtility.insertWithDel(l_tradedProductParams);
            
            TestDBUtility.deleteAll(EqtypeTradedProductRow.TYPE);
            EqtypeTradedProductParams l_eqtypeTradedProductParams = TestDBUtility.getEqtypeTradedProductRow();
            l_eqtypeTradedProductParams.setTradedProductId(330304148080000L);
            l_eqtypeTradedProductParams.setProductId(12342L);
            l_eqtypeTradedProductParams.setMarketId(33381L);
            TestDBUtility.insertWithDel(l_eqtypeTradedProductParams);
            
            TestDBUtility.deleteAll(BranchMarketRepayDealtCondRow.TYPE);
            BranchMarketRepayDealtCondParams l_branchMarketRepayDealtCondParams = TestDBUtility.getBranchMarketRepayDealCondRow();
            l_branchMarketRepayDealtCondParams.setInstitutionCode("0D");
            l_branchMarketRepayDealtCondParams.setBranchCode("381");
            l_branchMarketRepayDealtCondParams.setMarketCode("SP");
            l_branchMarketRepayDealtCondParams.setRepaymentNum(0);
            l_branchMarketRepayDealtCondParams.setRepaymentDiv("0");
            TestDBUtility.insertWithDel(l_branchMarketRepayDealtCondParams);
            
            TestDBUtility.deleteAll(TradingTimeRow.TYPE);
            TradingTimeParams l_tradingTimeparams = TestDBUtility.getTradingTimeRow();
            l_tradingTimeparams.setMarketCode("SP");
            l_tradingTimeparams.setBranchCode("123");
            l_tradingTimeparams.setBizDateType("1");
            l_tradingTimeparams.setTradingTimeType("01");
            TestDBUtility.insertWithDel(l_tradingTimeparams);

            TestDBUtility.deleteAll(TaxRateTableRow.TYPE);
            TaxRateTableParams l_taxRateTableParams = new TaxRateTableParams();
            l_taxRateTableParams.setTaxType("10");
            l_taxRateTableParams.setAppliEndDate(WEB3DateUtility.getDate("20071212", "yyyyMMdd"));
            l_taxRateTableParams.setAppliStartDate(WEB3DateUtility.getDate("20070203", "yyyyMMdd"));
            l_taxRateTableParams.setCreatedTimestamp(Calendar.getInstance().getTime());
            l_taxRateTableParams.setInstitutionCode("0D");
            l_taxRateTableParams.setLastUpdatedTimestamp(Calendar.getInstance().getTime());
            l_taxRateTableParams.setTaxRate(0.10D);
            TestDBUtility.insertWithDel(l_taxRateTableParams);
            
            WEB3GentradeCommission l_commission = new WEB3GentradeCommission();
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.equity.WEB3EquityBizLogicProvider",
                "createCommission",
                new Class[]{WEB3GentradeSubAccount.class,
                        String.class,Date.class,String.class,
                        String.class,double.class,OrderCategEnum.class},
                l_commission);

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getAccountId",
                new Class[] {},
                new Long(333812512246L));

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getLoginId",
                new Class[] {},
                new Long(3338111123L));
            
            LoginInfo l_info = new LoginInfoImpl();
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl", "getLoginInfo",
                new Class[] {},
                l_info);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl", "getSessionProperty",
                new Class[] {String.class},
                "123");

            EqTypeNewOrderValidationResult l_eqTypeNewOrderValidationResult =
                new EqTypeNewOrderValidationResult(ProcessingResult.SUCCESS_RESULT);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.equity.WEB3EquityOrderManager",
                "validateSettleContractOrder",
                new Class[] {SubAccount.class, EqTypeSettleContractOrderSpec.class, WEB3EquityContract.class},
                l_eqTypeNewOrderValidationResult);

            l_request.closeMarginContractUnits = new WEB3MarginCloseMarginContractUnit[]{l_unit};
            l_request.closingOrder = "0";
            
            
            WEB3GentradeTradingTimeManagementForMock.mockGetOrderBizDate(Calendar.getInstance().getTime());
            WEB3MarginCloseMarginCompleteResponse l_response = l_impl.submitOrder(l_request);
            log.debug("***************** expirationDate = " + l_response.expirationDate);
            assertEquals("20060507", WEB3DateUtility.formatDate(l_response.expirationDate, "yyyyMMdd"));
            System.out.println(0X101);
        }
        catch (Exception l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    public class WEB3MarginCloseMarginServiceImplForTest extends WEB3MarginCloseMarginServiceImpl
    {
        protected EqTypeSettleContractOrderEntry[] createClosingContractEntry(
                WEB3MarginCloseMarginContractUnit[] l_closeMarginContractUnits,
                WEB3MarginCloseMarginRequestAdapter l_requestAdaptor)
                throws WEB3BaseException
            {
                EqTypeSettleContractOrderEntry l_result =
                    new EqTypeSettleContractOrderEntry(123, 100);
                return new EqTypeSettleContractOrderEntry[]{l_result};
            }
        
//        protected void validateSettleContractOrder(
//                WEB3GentradeSubAccount l_subAccount,
//                EqTypeSettleContractOrderSpec l_orderSpec,
//                WEB3MarginCloseMarginRequestAdapter l_requestAdaptor)
//                throws WEB3BaseException
//        {
//        
//        }
        
        protected WEB3EquityRealizedProfitAndLossPrice getEstimatedRealizedProfitAndLossAmount(
                WEB3GentradeCommission l_genCommission,
                double l_dblLimitPrice,
                SubAccount l_subAccount,
                WEB3EquityTradedProduct l_equityTradedProduct,
                EqTypeSettleContractOrderEntry[] l_settleContractOrderEntrys,
                double l_dblQuantity,
                EqTypeOrderUnit l_orderUnit,
                double l_dblNowExecQuantity,
                double l_dblNowExecPrice,
                boolean l_isSkipAmountRange,
                WEB3MarginCloseMarginRequestAdapter l_requestAdaptor)
                throws WEB3BaseException
            {
                WEB3EquityRealizedProfitAndLossPrice l_price =
                    new WEB3EquityRealizedProfitAndLossPrice();
                return l_price;
            }
        
        protected WEB3MarginContractUnit[] createMarginContractUnitList(
                EqTypeSettleContractOrderEntry[] l_settleContractOrderEntrys,
                double l_dblUnitPrice,
                WEB3MarginCloseMarginRequestAdapter l_requestAdaptor)
                throws WEB3BaseException
            {
                return null;
            }
        
        protected WEB3TPAttentionObjection createCloseMarginAttentionWording(
                WEB3GentradeSubAccount l_subAccount,
                WEB3MarginCloseMarginUpdateInterceptor l_updateInterceptor,
                WEB3MarginSettleContractOrderSpec l_orderSpec)
                throws WEB3BaseException
            {
                WEB3TPAttentionObjection l_objection = new WEB3TPAttentionObjection();
                return null;
            }
        
        protected void submitSettleContractOrder(
                WEB3GentradeSubAccount l_subAccount,
                WEB3MarginSettleContractOrderSpec l_orderSpec,
                long l_lngOrderId,
                String l_strTradingPassword,
                WEB3GentradeCommission l_commission,
                WEB3EquityRealizedProfitAndLossPrice l_profitAndLossCalcResult,
                WEB3MarginCloseMarginRequestAdapter l_requestAdaptor)
                throws WEB3BaseException
            {
            
            }
        
        protected void execReCalcTradingPower(WEB3GentradeSubAccount l_subAccount) throws WEB3BaseException
        {
        
        }
    }

}
@
