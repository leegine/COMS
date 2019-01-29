head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.01.45.30;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3BondDomesticApplyInputServiceImplTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.bd.service.delegate.stdimpls;

import java.util.Date;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.message.ErrorInfo;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderOpenStatusEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProcessingResult;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccountTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.BranchParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.InstitutionParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.ProductParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.SubAccountParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.OrderValidationResult;
import com.fitechlabs.xtrade.plugin.tc.xbbd.BondTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.xbbd.data.BondOrderUnitParams;
import com.fitechlabs.xtrade.plugin.tc.xbbd.data.BondProductParams;

import test.util.TestDBUtility;

import webbroker3.bd.data.BondBranchConditionParams;
import webbroker3.bd.data.BondBranchConditionRow;
import webbroker3.bd.data.BondBranchRecruitLimitParams;
import webbroker3.bd.data.BondBranchRecruitLimitRow;
import webbroker3.bd.message.WEB3BondCancelCompleteRequest;
import webbroker3.bd.message.WEB3BondDomesticApplyInputRequest;
import webbroker3.bd.message.WEB3BondDomesticApplyInputResponse;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3BondBranchRecruitLimitBranchCodeDef;
import webbroker3.common.define.WEB3BondTradeTypeDef;
import webbroker3.common.define.WEB3DutyTypeDef;
import webbroker3.gentrade.WEB3GentradeAccountManager;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagementForMock;
import webbroker3.gentrade.data.TaxRateTableParams;
import webbroker3.gentrade.data.TradingTimeRow;
import webbroker3.mock.TestBaseForMock;
import webbroker3.mock.util.WEB3MockObjectParamsValue;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

/**
 * (国内債券応募入力サービスImpl)<BR>
 * 国内債券応募入力サービスImpl<BR>
 * <BR>
 * @@author 何文敏
 * @@version 1.0
 */
public class WEB3BondDomesticApplyInputServiceImplTest extends TestBaseForMock
{
    public static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3BondDomesticApplyInputServiceImplTest.class);
    WEB3BondDomesticApplyInputServiceImplForTest l_impl =
        new WEB3BondDomesticApplyInputServiceImplForTest();
    public WEB3BondDomesticApplyInputServiceImplTest(String arg0)
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
    

    public void testExecute_case0001()
    {
        final String STR_METHOD_NAME = "testExecute_case0001()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            TestDBUtility.deleteAll(SubAccountParams.TYPE);
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setAccountId(123);
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.BOND_SUB_ACCOUNT);
            TestDBUtility.insertWithDel(l_subAccountParams);
            
            TestDBUtility.deleteAll(BondBranchConditionRow.TYPE);
            BondBranchConditionParams l_bondBranchConditionParams = TestDBUtility.getBondBranchConditionRow();
            l_bondBranchConditionParams.setBranchId(123);
            l_bondBranchConditionParams.setBranchRecruitLimitDiv("0");
            TestDBUtility.insertWithDel(l_bondBranchConditionParams);
            
            TestDBUtility.deleteAll(BondBranchRecruitLimitRow.TYPE);
            BondBranchRecruitLimitParams l_bondBranchRecruitLimitParams = new BondBranchRecruitLimitParams();
            l_bondBranchRecruitLimitParams.setBranchCode(WEB3BondBranchRecruitLimitBranchCodeDef.ALL_BRANCH);
            l_bondBranchRecruitLimitParams.setProductId(123);
            l_bondBranchRecruitLimitParams.setInstitutionCode("0D");
            l_bondBranchRecruitLimitParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070503", "yyyyMMdd"));
            l_bondBranchRecruitLimitParams.setRecruitLimit(10D);
            l_bondBranchRecruitLimitParams.setCreatedTimestamp(WEB3DateUtility.getDate("20070503", "yyyyMMdd"));
            l_bondBranchRecruitLimitParams.setRecruitLimit(10000000L);
            TestDBUtility.insertWithDel(l_bondBranchRecruitLimitParams);
            
            TestDBUtility.deleteAll(BondProductParams.TYPE);
            BondProductParams l_bondProductParams = TestDBUtility.getBondProductRow();
            l_bondProductParams.setProductId(123);
            l_bondProductParams.setTradeHandleDiv("2");
            l_bondProductParams.setTradeStartDate(WEB3DateUtility.getDate("20070501", "yyyyMMdd"));
            l_bondProductParams.setTradeEndDate(WEB3DateUtility.getDate("99990101", "yyyyMMdd"));
//            l_bondProductParams.setProspectusCheckDiv("1");
            l_bondProductParams.setTradeType(WEB3BondTradeTypeDef.RECRUIT);
            l_bondProductParams.setRecruitStartDate(WEB3DateUtility.getDate("20070503", "yyyyMMdd"));
            l_bondProductParams.setRecruitEndDate(WEB3DateUtility.getDate("20101201", "yyyyMMdd"));
            l_bondProductParams.setInstitutionCode("0D");
            l_bondProductParams.setTradeEndDate(WEB3DateUtility.getDate("20101201", "yyyyMMdd"));
            l_bondProductParams.setProductName("zhoumoyang");
            l_bondProductParams.setCoupon(0.01D);
            l_bondProductParams.setYearlyInterestPayments(3);
            l_bondProductParams.setBuyPrice(30D);
            TestDBUtility.insertWithDel(l_bondProductParams);
            
            TestDBUtility.deleteAll(ProductParams.TYPE);
            ProductParams l_productParams = TestDBUtility.getProductRow();
            l_productParams.setProductId(123);
            l_productParams.setProductType(ProductTypeEnum.BOND);
            TestDBUtility.insertWithDel(l_productParams);
            
            TestDBUtility.deleteAll(InstitutionParams.TYPE);
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            l_institutionParams.setInstitutionCode("0D");
            TestDBUtility.insertWithDel(l_institutionParams);
            
            TestDBUtility.deleteAll(BranchParams.TYPE);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setBranchId(123);
            TestDBUtility.insertWithDel(l_branchParams);
            
            TestDBUtility.deleteAll(MainAccountParams.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(123);
            l_mainAccountParams.setBranchId(123);
            l_mainAccountParams.setSex("1");
            TestDBUtility.insertWithDel(l_mainAccountParams);
            
            TestDBUtility.deleteAll(TaxRateTableParams.TYPE);
            TaxRateTableParams l_taxRateTableParams = new TaxRateTableParams();
            l_taxRateTableParams.setInstitutionCode("0D");
            l_taxRateTableParams.setTaxType(WEB3DutyTypeDef.DOMESTIC_BOND_WITHHOLDING_TAX);
            l_taxRateTableParams.setAppliEndDate(WEB3DateUtility.getDate("20111201", "yyyyMMdd"));
            l_taxRateTableParams.setAppliStartDate(WEB3DateUtility.getDate("20070101", "yyyyMMdd"));
            l_taxRateTableParams.setTaxRate(0.12D);
            TestDBUtility.insertWithDel(l_taxRateTableParams);
            
            TestDBUtility.deleteAll(BondOrderUnitParams.TYPE);
            BondOrderUnitParams l_bondOrderUnitParams = TestDBUtility.getBondOrderUnitRow();
            l_bondOrderUnitParams.setProductId(123);
            l_bondOrderUnitParams.setBranchId(123);
            l_bondOrderUnitParams.setOrderType(OrderTypeEnum.DOMESTIC_BOND_RECRUIT);
            l_bondOrderUnitParams.setOrderOpenStatus(OrderOpenStatusEnum.OPEN);
            TestDBUtility.insertWithDel(l_bondOrderUnitParams);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.tradingpower.WEB3TPTradingPowerServiceImpl",
                "getOtherTradingPower",
                new Class[] {WEB3GentradeSubAccount.class, Date.class},
                null);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.gentrade.service.delegate.stdimpls.WEB3GentradeBatoClientServiceImpl",
                "validateProspectus",
                new Class[] {String.class, String.class},
                null);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getSessionProperty",
                new Class[] {String.class},
                "2");
                    
            WEB3GentradeTradingTimeManagementForMock.mockValidateOrderAccept(true);
            WEB3BondDomesticApplyInputRequest l_request = new WEB3BondDomesticApplyInputRequest();
            l_request.productId = "123";
            l_request.batoCheckFlag = true;
            WEB3BondDomesticApplyInputResponse l_response =
                (WEB3BondDomesticApplyInputResponse)l_impl.execute(l_request);
            assertEquals("0", l_response.tradingPower);
            assertEquals("123", l_response.productId);
            assertEquals("zhoumoyang", l_response.productName);
            assertEquals("20070501", WEB3DateUtility.formatDate(l_response.recruitStartDate, "yyyyMMdd"));
            assertEquals("20101201", WEB3DateUtility.formatDate(l_response.recruitEndDate, "yyyyMMdd"));
            assertEquals("0.01", l_response.coupon);
            assertEquals("0.009988", l_response.couponAfterTax);
            assertEquals("3", l_response.yearlyInterestPayments);
            assertEquals(null, l_response.couponPaymentDate1);
            assertEquals(null, l_response.couponPaymentDate2);
            assertEquals("30", l_response.applyPrice);
            assertEquals("100", l_response.applyUnit);
            assertEquals("20040101", WEB3DateUtility.formatDate(l_response.issueDate, "yyyyMMdd"));
            assertEquals("20090701", WEB3DateUtility.formatDate(l_response.maturityDate, "yyyyMMdd"));
            assertEquals(null, l_response.prospectusResult);
        }
        catch (Exception l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
            
    }
    
    public void testExecute_case0002()
    {
        final String STR_METHOD_NAME = "testExecute_case0002()";
        log.entering(STR_METHOD_NAME);
        
        try
        {

            l_impl.execute(null);
        }
        catch (WEB3BaseException l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80017,l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
            
    }
    
    public void testExecute_case0003()
    {
        final String STR_METHOD_NAME = "testExecute_case0003()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            WEB3BondCancelCompleteRequest l_request = new WEB3BondCancelCompleteRequest();
            l_impl.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80018,l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
            
    }
    
    public void testExecute_case0004()
    {
        final String STR_METHOD_NAME = "testExecute_case0004()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            WEB3BondDomesticApplyInputRequest l_request = new WEB3BondDomesticApplyInputRequest();
            l_impl.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02229,l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
            
    }
    
    public void testExecute_case0005()
    {
        final String STR_METHOD_NAME = "testExecute_case0005()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            TestDBUtility.deleteAll(TradingTimeRow.TYPE);
            WEB3BondDomesticApplyInputRequest l_request = new WEB3BondDomesticApplyInputRequest();
            l_request.productId = "123";
            l_impl.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80005,l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
            
    }
    
    
    public void testExecute_case0006()
    {
        final String STR_METHOD_NAME = "testExecute_case0006()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            TestDBUtility.deleteAll(TradingTimeRow.TYPE);
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            
            TestDBUtility.deleteAll(BondProductParams.TYPE);
            BondProductParams l_bondProductParams = TestDBUtility.getBondProductRow();
            l_bondProductParams.setProductId(123);
            l_bondProductParams.setTradeHandleDiv("2");
            l_bondProductParams.setTradeStartDate(WEB3DateUtility.getDate("20070501", "yyyyMMdd"));
            l_bondProductParams.setTradeEndDate(WEB3DateUtility.getDate("99990101", "yyyyMMdd"));
//            l_bondProductParams.setProspectusCheckDiv("1");
            l_bondProductParams.setTradeType(WEB3BondTradeTypeDef.RECRUIT);
            l_bondProductParams.setRecruitStartDate(WEB3DateUtility.getDate("20070503", "yyyyMMdd"));
            l_bondProductParams.setRecruitEndDate(WEB3DateUtility.getDate("20071201", "yyyyMMdd"));
            l_bondProductParams.setInstitutionCode("0D");
            l_bondProductParams.setTradeEndDate(WEB3DateUtility.getDate("20071201", "yyyyMMdd"));
            l_bondProductParams.setProductName("zhoumoyang");
            l_bondProductParams.setCoupon(0.01D);
            l_bondProductParams.setYearlyInterestPayments(3);
            l_bondProductParams.setBuyPrice(30D);
            l_bondProductParams.setBondType(BondTypeEnum.INDIVIDUAL_GOVERNMENT_BOND);
            TestDBUtility.insertWithDel(l_bondProductParams);
            
            TestDBUtility.deleteAll(MainAccountParams.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(123);
            l_mainAccountParams.setBranchId(123);
            l_mainAccountParams.setSex("0");
            TestDBUtility.insertWithDel(l_mainAccountParams);
            
            TestDBUtility.deleteAll(SubAccountParams.TYPE);
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setAccountId(123);
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.BOND_SUB_ACCOUNT);
            TestDBUtility.insertWithDel(l_subAccountParams);
            
            TestDBUtility.deleteAll(ProductParams.TYPE);
            ProductParams l_productParams = TestDBUtility.getProductRow();
            l_productParams.setProductId(123);
            l_productParams.setProductType(ProductTypeEnum.BOND);
            TestDBUtility.insertWithDel(l_productParams);
            
            WEB3BondDomesticApplyInputRequest l_request = new WEB3BondDomesticApplyInputRequest();
            l_request.productId = "123";
            WEB3GentradeTradingTimeManagementForMock.mockValidateOrderAccept(true);
            l_impl.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            assertEquals(WEB3ErrorCatalog. BUSINESS_ERROR_02884,l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
            
    }
    
    public void testExecute_case0007()
    {
        final String STR_METHOD_NAME = "testExecute_case0007()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            TestDBUtility.deleteAll(TradingTimeRow.TYPE);
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            
            TestDBUtility.deleteAll(BondProductParams.TYPE);
            BondProductParams l_bondProductParams = TestDBUtility.getBondProductRow();
            l_bondProductParams.setProductId(123);
            l_bondProductParams.setTradeHandleDiv("2");
            l_bondProductParams.setTradeStartDate(WEB3DateUtility.getDate("20070501", "yyyyMMdd"));
            l_bondProductParams.setTradeEndDate(WEB3DateUtility.getDate("99990101", "yyyyMMdd"));
//            l_bondProductParams.setProspectusCheckDiv("1");
            l_bondProductParams.setTradeType(WEB3BondTradeTypeDef.BUY_SELL);
            l_bondProductParams.setRecruitStartDate(WEB3DateUtility.getDate("20070503", "yyyyMMdd"));
            l_bondProductParams.setRecruitEndDate(WEB3DateUtility.getDate("20071201", "yyyyMMdd"));
            l_bondProductParams.setInstitutionCode("0D");
            l_bondProductParams.setTradeEndDate(WEB3DateUtility.getDate("20101201", "yyyyMMdd"));
            l_bondProductParams.setProductName("zhoumoyang");
            l_bondProductParams.setCoupon(0.01D);
            l_bondProductParams.setYearlyInterestPayments(3);
            l_bondProductParams.setBuyPrice(30D);
            TestDBUtility.insertWithDel(l_bondProductParams);
            
            //SubAccountParams
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setAccountId(123);
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.BOND_SUB_ACCOUNT);
            TestDBUtility.insertWithDel(l_subAccountParams);
            
            TestDBUtility.deleteAll(MainAccountParams.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(123);
            l_mainAccountParams.setBranchId(123);
            l_mainAccountParams.setSex("1");
            TestDBUtility.insertWithDel(l_mainAccountParams);
            
            TestDBUtility.deleteAll(ProductParams.TYPE);
            ProductParams l_productParams = TestDBUtility.getProductRow();
            l_productParams.setProductId(123);
            l_productParams.setProductType(ProductTypeEnum.BOND);
            TestDBUtility.insertWithDel(l_productParams);
            
            WEB3BondDomesticApplyInputRequest l_request = new WEB3BondDomesticApplyInputRequest();
            l_request.productId = "123";
            WEB3GentradeTradingTimeManagementForMock.mockValidateOrderAccept(true);
            l_impl.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            assertEquals(WEB3ErrorCatalog. BUSINESS_ERROR_02612,l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
            
    }
    
    public void testExecute_case0008()
    {
        final String STR_METHOD_NAME = "testExecute_case0008()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            TestDBUtility.deleteAll(TradingTimeRow.TYPE);
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            TestDBUtility.deleteAll(BondBranchRecruitLimitRow.TYPE);
            
            TestDBUtility.deleteAll(BondProductParams.TYPE);
            BondProductParams l_bondProductParams = TestDBUtility.getBondProductRow();
            l_bondProductParams.setProductId(123);
            l_bondProductParams.setTradeHandleDiv("2");
            l_bondProductParams.setTradeStartDate(WEB3DateUtility.getDate("20070501", "yyyyMMdd"));
            l_bondProductParams.setTradeEndDate(WEB3DateUtility.getDate("99990101", "yyyyMMdd"));
//            l_bondProductParams.setProspectusCheckDiv("1");
            l_bondProductParams.setTradeType(WEB3BondTradeTypeDef.RECRUIT);
            l_bondProductParams.setRecruitStartDate(WEB3DateUtility.getDate("20070503", "yyyyMMdd"));
            l_bondProductParams.setRecruitEndDate(WEB3DateUtility.getDate("20071201", "yyyyMMdd"));
            l_bondProductParams.setInstitutionCode("0D");
            l_bondProductParams.setTradeEndDate(WEB3DateUtility.getDate("20111201", "yyyyMMdd"));
            l_bondProductParams.setProductName("zhoumoyang");
            l_bondProductParams.setCoupon(0.01D);
            l_bondProductParams.setYearlyInterestPayments(3);
            l_bondProductParams.setBuyPrice(30D);
            TestDBUtility.insertWithDel(l_bondProductParams);
            
            TestDBUtility.deleteAll(BondBranchRecruitLimitRow.TYPE);
            BondBranchRecruitLimitParams l_bondBranchRecruitLimitParams = new BondBranchRecruitLimitParams();
            l_bondBranchRecruitLimitParams.setBranchCode(WEB3BondBranchRecruitLimitBranchCodeDef.ALL_BRANCH);
            l_bondBranchRecruitLimitParams.setProductId(123);
            l_bondBranchRecruitLimitParams.setInstitutionCode("0D");
            l_bondBranchRecruitLimitParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070503", "yyyyMMdd"));
            l_bondBranchRecruitLimitParams.setRecruitLimit(10D);
            l_bondBranchRecruitLimitParams.setCreatedTimestamp(WEB3DateUtility.getDate("20070503", "yyyyMMdd"));
            TestDBUtility.insertWithDel(l_bondBranchRecruitLimitParams);
            
            ProcessingResult l_resulte = ProcessingResult.newSuccessResultInstance();
            OrderValidationResult l_result = new OrderValidationResult(l_resulte);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.gentrade.WEB3GentradeOrderValidator",
                    "validateSubAccountForTrading",
                    new Class[] {SubAccount.class},
                    l_result);
            
            TestDBUtility.deleteAll(BranchParams.TYPE);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setBranchId(123);
            TestDBUtility.insertWithDel(l_branchParams);
            
            TestDBUtility.deleteAll(MainAccountParams.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(123);
            l_mainAccountParams.setBranchId(123);
            l_mainAccountParams.setSex("1");
            TestDBUtility.insertWithDel(l_mainAccountParams);
            
            TestDBUtility.deleteAll(ProductParams.TYPE);
            ProductParams l_productParams = TestDBUtility.getProductRow();
            l_productParams.setProductId(123);
            l_productParams.setProductType(ProductTypeEnum.BOND);
            TestDBUtility.insertWithDel(l_productParams);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                    "getSessionProperty",
                    new Class[] {String.class},
                    "2");
            
            WEB3BondDomesticApplyInputRequest l_request = new WEB3BondDomesticApplyInputRequest();
            l_request.productId = "123";
            WEB3GentradeTradingTimeManagementForMock.mockValidateOrderAccept(true);
            l_impl.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            assertEquals(WEB3ErrorCatalog. BUSINESS_ERROR_02888,l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
            
    }
    
    public void testExecute_case0009()
    {
        final String STR_METHOD_NAME = "testExecute_case0009()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            TestDBUtility.deleteAll(TradingTimeRow.TYPE);
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            TestDBUtility.deleteAll(BondBranchRecruitLimitRow.TYPE);
            
            TestDBUtility.deleteAll(BondProductParams.TYPE);
            BondProductParams l_bondProductParams = TestDBUtility.getBondProductRow();
            l_bondProductParams.setProductId(123);
            l_bondProductParams.setTradeHandleDiv("2");
            l_bondProductParams.setTradeStartDate(WEB3DateUtility.getDate("20070501", "yyyyMMdd"));
            l_bondProductParams.setTradeEndDate(WEB3DateUtility.getDate("99990101", "yyyyMMdd"));
//            l_bondProductParams.setProspectusCheckDiv("1");
            l_bondProductParams.setTradeType(WEB3BondTradeTypeDef.RECRUIT);
            l_bondProductParams.setRecruitStartDate(WEB3DateUtility.getDate("20070503", "yyyyMMdd"));
            l_bondProductParams.setRecruitEndDate(WEB3DateUtility.getDate("20071201", "yyyyMMdd"));
            l_bondProductParams.setInstitutionCode("0D");
            l_bondProductParams.setTradeEndDate(WEB3DateUtility.getDate("20071201", "yyyyMMdd"));
            l_bondProductParams.setProductName("zhoumoyang");
            l_bondProductParams.setCoupon(0.01D);
            l_bondProductParams.setYearlyInterestPayments(3);
            l_bondProductParams.setBuyPrice(30D);
            TestDBUtility.insertWithDel(l_bondProductParams);
            
            TestDBUtility.deleteAll(BranchParams.TYPE);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setBranchId(123);
            TestDBUtility.insertWithDel(l_branchParams);

            TestDBUtility.deleteAll(MainAccountParams.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(123);
            l_mainAccountParams.setBranchId(123);
            l_mainAccountParams.setSex("1");
            TestDBUtility.insertWithDel(l_mainAccountParams);
            
            TestDBUtility.deleteAll(ProductParams.TYPE);
            ProductParams l_productParams = TestDBUtility.getProductRow();
            l_productParams.setProductId(123);
            l_productParams.setProductType(ProductTypeEnum.BOND);
            TestDBUtility.insertWithDel(l_productParams);
            
            ErrorInfo l_errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80005;
            ProcessingResult l_resulte = ProcessingResult.newFailedResultInstance(l_errorInfo);
            OrderValidationResult l_result = new OrderValidationResult(l_resulte);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.gentrade.WEB3GentradeOrderValidator",
                    "validateSubAccountForTrading",
                    new Class[] {SubAccount.class},
                    l_result);
            
            WEB3BondDomesticApplyInputRequest l_request = new WEB3BondDomesticApplyInputRequest();
            l_request.productId = "123";
            WEB3GentradeTradingTimeManagementForMock.mockValidateOrderAccept(true);
            l_impl.execute(l_request);
            WEB3MockObjectParamsValue l_paramsValue1 = TestBaseForMock.MOCK_MANAGER.getMethodParamsValue(
                    "webbroker3.gentrade.WEB3GentradeOrderValidator", 
                    "validateSubAccountForTrading", 
                    new Class[] {SubAccount.class});
            assertEquals(SubAccount.class, l_paramsValue1.getFirstCalled()[0].getClass());
        }
        catch (WEB3BaseException l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            assertEquals(WEB3ErrorCatalog. SYSTEM_ERROR_80005,l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
            
    }
    
    class WEB3BondDomesticApplyInputServiceImplForTest extends WEB3BondDomesticApplyInputServiceImpl
    {
        public SubAccount getSubAccount() throws WEB3BaseException 
        {
            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
            WEB3GentradeAccountManager l_accountManager = (WEB3GentradeAccountManager)l_finApp.getAccountManager();
            SubAccount  l_subAccount = null;
            try
            {
                l_subAccount = l_accountManager.getSubAccount(123,SubAccountTypeEnum.BOND_SUB_ACCOUNT);
            } catch (NotFoundException e)
            {
                e.printStackTrace();
            }
            return l_subAccount;
        }
    }
}
@
