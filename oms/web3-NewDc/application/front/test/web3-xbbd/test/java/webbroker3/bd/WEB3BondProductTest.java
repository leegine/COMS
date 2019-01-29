head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.01.01.51;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3BondProductTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
 Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 債券銘柄テスト(WEB3BondProductTest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2007/02/09 関博 (中訊) 新規作成
*/

package webbroker3.bd;

import java.util.Calendar;
import java.util.Date;

import com.fitechlabs.xtrade.plugin.tc.gentrade.data.BranchParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.ProductParams;
import com.fitechlabs.xtrade.plugin.tc.xbbd.BondTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.xbbd.CouponTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.xbbd.data.BondOrderUnitParams;
import com.fitechlabs.xtrade.plugin.tc.xbbd.data.BondProductParams;

import test.util.TestDBUtility;

import webbroker3.bd.data.BondBranchConditionParams;
import webbroker3.gentrade.WEB3GentradeBranch;
import webbroker3.mock.TestBaseForMock;
import webbroker3.mock.util.WEB3MockObjectParamsValue;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

public class WEB3BondProductTest extends TestBaseForMock
{
    /**
     * ログ出力ユーティリティ。
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3BondProductTest.class);
    
    public WEB3BondProductTest(String arg0)
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

    public void testGetPaymentDate_C0001()
    {
        final String STR_METHOD_NAME = " testGetPaymentDate_C0001";
        log.entering(STR_METHOD_NAME);

        try
        {
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setBranchId(123123);
            TestDBUtility.insertWithDel(l_branchParams);
            
            BondOrderUnitParams l_bondOrderUnitParams = TestDBUtility.getBondOrderUnitRow();
            l_bondOrderUnitParams.setBranchId(123123);
            l_bondOrderUnitParams.setProductId(10001);
            TestDBUtility.insertWithDel(l_bondOrderUnitParams);
            
            ProductParams l_productParams = TestDBUtility.getProductRow();
            l_productParams.setProductId(10001);
            TestDBUtility.insertWithDel(l_productParams);

            BondBranchConditionParams l_bondBranchConditionParams = TestDBUtility.getBondBranchConditionRow();
            l_bondBranchConditionParams.setBranchId(123123);
            l_bondBranchConditionParams.setPaymentDateSetDiv("1");
            TestDBUtility.insertWithDel(l_bondBranchConditionParams);
            
            BondProductParams l_bondProductParams = TestDBUtility.getBondProductRow();
            l_bondProductParams.setProductId(10001);
            l_bondProductParams.setInstitutionCode("20");
            l_bondProductParams.setProductCode("0126");
            l_bondProductParams.setProductName("あいう");
            l_bondProductParams.setProductIssueCode("213");
            l_bondProductParams.setBondType(BondTypeEnum.DOMESTIC_BOND);
            l_bondProductParams.setHostProductCode("6666");
            l_bondProductParams.setProductIssueCode("8888");
            l_bondProductParams.setIssueDate(WEB3DateUtility.getDate("2007/01/08", "yyyy/MM/dd"));
            l_bondProductParams.setIssuePrice(20.5);
            l_bondProductParams.setParValue(2.1);
            l_bondProductParams.setMaturityDate(WEB3DateUtility.getDate("2007/02/08", "yyyy/MM/dd"));
            l_bondProductParams.setRedemptionPrice(10.5);
            l_bondProductParams.setCouponType(CouponTypeEnum.FLOATING_COUPON);
            l_bondProductParams.setCoupon(0.5);
            l_bondProductParams.setYearlyInterestPayments(9);
            l_bondProductParams.setTradeHandleDiv("0");
            l_bondProductParams.setTradeUnit(20.3);
            l_bondProductParams.setMinFaceAmount(10.21);
            l_bondProductParams.setAutoExecDiv("1");
            l_bondProductParams.setHostProductIssueCode("2");
            l_bondProductParams.setCreatedTimestamp(Calendar.getInstance().getTime());
            l_bondProductParams.setLastUpdatedTimestamp(Calendar.getInstance().getTime());
            TestDBUtility.insertWithDel(l_bondProductParams);

            WEB3BondProduct l_impl =
                new WEB3BondProduct(l_bondProductParams);

            WEB3BondOrderTypeJudge l_bondOrderTypeJudge = new WEB3BondOrderTypeJudgeForTest();
            
            String l_strSettlementDiv = "1";
            
            WEB3GentradeBranch l_branch = new WEB3GentradeBranch(123123);
            
            Date l_datExecDate = WEB3DateUtility.getDate("20070201", "yyyyMMdd");

            assertEquals(l_datExecDate,l_impl.getPaymentDate(l_datExecDate,l_bondOrderTypeJudge,l_strSettlementDiv,l_branch));
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME,l_ex);
            fail();
        }
    }
    
    public void testGetPaymentDate_C0002()
    {
        final String STR_METHOD_NAME = " testGetPaymentDate_C0002";
        log.entering(STR_METHOD_NAME);

        try
        {
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setBranchId(123123);
            TestDBUtility.insertWithDel(l_branchParams);
            
            BondOrderUnitParams l_bondOrderUnitParams = TestDBUtility.getBondOrderUnitRow();
            l_bondOrderUnitParams.setBranchId(123123);
            l_bondOrderUnitParams.setProductId(10001);
            TestDBUtility.insertWithDel(l_bondOrderUnitParams);
            
            ProductParams l_productParams = TestDBUtility.getProductRow();
            l_productParams.setProductId(10001);
            TestDBUtility.insertWithDel(l_productParams);

            BondBranchConditionParams l_bondBranchConditionParams = TestDBUtility.getBondBranchConditionRow();
            l_bondBranchConditionParams.setBranchId(123123);
            l_bondBranchConditionParams.setPaymentDateSetDiv("1");
            TestDBUtility.insertWithDel(l_bondBranchConditionParams);
            
            BondProductParams l_bondProductParams = TestDBUtility.getBondProductRow();
            l_bondProductParams.setProductId(10001);
            l_bondProductParams.setInstitutionCode("20");
            l_bondProductParams.setProductCode("0126");
            l_bondProductParams.setProductName("あいう");
            l_bondProductParams.setProductIssueCode("213");
            l_bondProductParams.setBondType(BondTypeEnum.DOMESTIC_BOND);
            l_bondProductParams.setHostProductCode("6666");
            l_bondProductParams.setProductIssueCode("8888");
            l_bondProductParams.setIssueDate(WEB3DateUtility.getDate("2007/02/02", "yyyy/MM/dd"));
            l_bondProductParams.setIssuePrice(20.5);
            l_bondProductParams.setParValue(2.1);
            l_bondProductParams.setMaturityDate(WEB3DateUtility.getDate("2007/02/08", "yyyy/MM/dd"));
            l_bondProductParams.setRedemptionPrice(10.5);
            l_bondProductParams.setCouponType(CouponTypeEnum.FLOATING_COUPON);
            l_bondProductParams.setCoupon(0.5);
            l_bondProductParams.setYearlyInterestPayments(9);
            l_bondProductParams.setTradeHandleDiv("0");
            l_bondProductParams.setTradeUnit(20.3);
            l_bondProductParams.setMinFaceAmount(10.21);
            l_bondProductParams.setAutoExecDiv("1");
            l_bondProductParams.setHostProductIssueCode("2");
            l_bondProductParams.setCreatedTimestamp(Calendar.getInstance().getTime());
            l_bondProductParams.setLastUpdatedTimestamp(Calendar.getInstance().getTime());
            TestDBUtility.insertWithDel(l_bondProductParams);

            WEB3BondProduct l_impl =
                new WEB3BondProduct(l_bondProductParams);

            WEB3BondOrderTypeJudge l_bondOrderTypeJudge = new WEB3BondOrderTypeJudgeForTest();
            
            String l_strSettlementDiv = "1";
            
            WEB3GentradeBranch l_branch = new WEB3GentradeBranch(123123);
            
            Date l_datExecDate = WEB3DateUtility.getDate("20070201", "yyyyMMdd");

            assertEquals(WEB3DateUtility.getDate("20070202", "yyyyMMdd"),l_impl.getPaymentDate(l_datExecDate,l_bondOrderTypeJudge,l_strSettlementDiv,l_branch));
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME,l_ex);
            fail();
        }
    }

    public void testGetPaymentDate_C0003()
    {
        final String STR_METHOD_NAME = " testGetPaymentDate_C0003";
        log.entering(STR_METHOD_NAME);

        try
        {
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setBranchId(123123);
            TestDBUtility.insertWithDel(l_branchParams);
            
            BondOrderUnitParams l_bondOrderUnitParams = TestDBUtility.getBondOrderUnitRow();
            l_bondOrderUnitParams.setBranchId(123123);
            l_bondOrderUnitParams.setProductId(10001);
            TestDBUtility.insertWithDel(l_bondOrderUnitParams);
            
            ProductParams l_productParams = TestDBUtility.getProductRow();
            l_productParams.setProductId(10001);
            TestDBUtility.insertWithDel(l_productParams);

            BondBranchConditionParams l_bondBranchConditionParams = TestDBUtility.getBondBranchConditionRow();
            l_bondBranchConditionParams.setBranchId(123123);
            l_bondBranchConditionParams.setPaymentDateSetDiv("2");
            TestDBUtility.insertWithDel(l_bondBranchConditionParams);
            
            BondProductParams l_bondProductParams = TestDBUtility.getBondProductRow();
            l_bondProductParams.setProductId(10001);
            l_bondProductParams.setInstitutionCode("20");
            l_bondProductParams.setProductCode("0126");
            l_bondProductParams.setProductName("あいう");
            l_bondProductParams.setProductIssueCode("213");
            l_bondProductParams.setBondType(BondTypeEnum.DOMESTIC_BOND);
            l_bondProductParams.setHostProductCode("6666");
            l_bondProductParams.setProductIssueCode("8888");
            l_bondProductParams.setIssueDate(WEB3DateUtility.getDate("2007/01/08", "yyyy/MM/dd"));
            l_bondProductParams.setIssuePrice(20.5);
            l_bondProductParams.setParValue(2.1);
            l_bondProductParams.setMaturityDate(WEB3DateUtility.getDate("2007/02/08", "yyyy/MM/dd"));
            l_bondProductParams.setRedemptionPrice(10.5);
            l_bondProductParams.setCouponType(CouponTypeEnum.FLOATING_COUPON);
            l_bondProductParams.setCoupon(0.5);
            l_bondProductParams.setYearlyInterestPayments(9);
            l_bondProductParams.setTradeHandleDiv("0");
            l_bondProductParams.setTradeUnit(20.3);
            l_bondProductParams.setMinFaceAmount(10.21);
            l_bondProductParams.setAutoExecDiv("1");
            l_bondProductParams.setHostProductIssueCode("2");
            l_bondProductParams.setCreatedTimestamp(Calendar.getInstance().getTime());
            l_bondProductParams.setLastUpdatedTimestamp(Calendar.getInstance().getTime());
            TestDBUtility.insertWithDel(l_bondProductParams);

            WEB3BondProduct l_impl =
                new WEB3BondProduct(l_bondProductParams);

            WEB3BondOrderTypeJudge l_bondOrderTypeJudge = new WEB3BondOrderTypeJudgeForTest();
            
            String l_strSettlementDiv = "1";
            
            WEB3GentradeBranch l_branch = new WEB3GentradeBranch(123123);
            
            Date l_datExecDate = WEB3DateUtility.getDate("20070201", "yyyyMMdd");

            assertEquals(l_datExecDate,l_impl.getPaymentDate(l_datExecDate,l_bondOrderTypeJudge,l_strSettlementDiv,l_branch));
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME,l_ex);
            fail();
        }
    }
    
    public void testIsIndividualGovernmentBond_case0001()
    {
        final String STR_METHOD_NAME = "testIsIndividualGovernmentBond_case0001()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            BondProductParams l_bondProductParams = TestDBUtility.getBondProductRow();
            l_bondProductParams.setProductId(10001);
            l_bondProductParams.setInstitutionCode("20");
            l_bondProductParams.setProductCode("0126");
            l_bondProductParams.setProductName("あいう");
            l_bondProductParams.setProductIssueCode("213");
            l_bondProductParams.setBondType(BondTypeEnum.INDIVIDUAL_GOVERNMENT_BOND);
            l_bondProductParams.setHostProductCode("6666");
            l_bondProductParams.setProductIssueCode("8888");
            l_bondProductParams.setIssueDate(WEB3DateUtility.getDate("2007/02/02", "yyyy/MM/dd"));
            l_bondProductParams.setIssuePrice(20.5);
            l_bondProductParams.setParValue(2.1);
            l_bondProductParams.setMaturityDate(WEB3DateUtility.getDate("2007/02/08", "yyyy/MM/dd"));
            l_bondProductParams.setRedemptionPrice(10.5);
            l_bondProductParams.setCouponType(CouponTypeEnum.FLOATING_COUPON);
            l_bondProductParams.setCoupon(0.5);
            l_bondProductParams.setYearlyInterestPayments(9);
            l_bondProductParams.setTradeHandleDiv("0");
            l_bondProductParams.setTradeUnit(20.3);
            l_bondProductParams.setMinFaceAmount(10.21);
            l_bondProductParams.setAutoExecDiv("1");
            l_bondProductParams.setHostProductIssueCode("2");
            l_bondProductParams.setCreatedTimestamp(Calendar.getInstance().getTime());
            l_bondProductParams.setLastUpdatedTimestamp(Calendar.getInstance().getTime());
            TestDBUtility.insertWithDel(l_bondProductParams);

            WEB3BondProduct l_bondProduct =
                new WEB3BondProduct(l_bondProductParams);
            boolean l_bln = l_bondProduct.isIndividualGovernmentBond();
            assertTrue(l_bln);
        }
        catch (Exception l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testIsIndividualGovernmentBond_case0002()
    {
        final String STR_METHOD_NAME = "testIsIndividualGovernmentBond_case0002()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            BondProductParams l_bondProductParams = TestDBUtility.getBondProductRow();
            l_bondProductParams.setProductId(10001);
            l_bondProductParams.setInstitutionCode("20");
            l_bondProductParams.setProductCode("0126");
            l_bondProductParams.setProductName("あいう");
            l_bondProductParams.setProductIssueCode("213");
            l_bondProductParams.setBondType(BondTypeEnum.DOMESTIC_BOND);
            l_bondProductParams.setHostProductCode("6666");
            l_bondProductParams.setProductIssueCode("8888");
            l_bondProductParams.setIssueDate(WEB3DateUtility.getDate("2007/02/02", "yyyy/MM/dd"));
            l_bondProductParams.setIssuePrice(20.5);
            l_bondProductParams.setParValue(2.1);
            l_bondProductParams.setMaturityDate(WEB3DateUtility.getDate("2007/02/08", "yyyy/MM/dd"));
            l_bondProductParams.setRedemptionPrice(10.5);
            l_bondProductParams.setCouponType(CouponTypeEnum.FLOATING_COUPON);
            l_bondProductParams.setCoupon(0.5);
            l_bondProductParams.setYearlyInterestPayments(9);
            l_bondProductParams.setTradeHandleDiv("0");
            l_bondProductParams.setTradeUnit(20.3);
            l_bondProductParams.setMinFaceAmount(10.21);
            l_bondProductParams.setAutoExecDiv("1");
            l_bondProductParams.setHostProductIssueCode("2");
            l_bondProductParams.setCreatedTimestamp(Calendar.getInstance().getTime());
            l_bondProductParams.setLastUpdatedTimestamp(Calendar.getInstance().getTime());
            TestDBUtility.insertWithDel(l_bondProductParams);

            WEB3BondProduct l_bondProduct =
                new WEB3BondProduct(l_bondProductParams);
            boolean l_bln = l_bondProduct.isIndividualGovernmentBond();
            assertFalse(l_bln);
        }
        catch (Exception l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testGetBondDomesticBizDate()
    {
        final String STR_METHOD_NAME = "testGetBondDomesticBizDate()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            BondProductParams l_bondProductParams = TestDBUtility.getBondProductRow();
            l_bondProductParams.setProductId(10001);
            l_bondProductParams.setInstitutionCode("20");
            l_bondProductParams.setProductCode("0126");
            l_bondProductParams.setProductName("あいう");
            l_bondProductParams.setProductIssueCode("213");
            l_bondProductParams.setBondType(BondTypeEnum.DOMESTIC_BOND);
            l_bondProductParams.setHostProductCode("6666");
            l_bondProductParams.setProductIssueCode("8888");
            l_bondProductParams.setIssueDate(WEB3DateUtility.getDate("2007/02/02", "yyyy/MM/dd"));
            l_bondProductParams.setIssuePrice(20.5);
            l_bondProductParams.setParValue(2.1);
            l_bondProductParams.setMaturityDate(WEB3DateUtility.getDate("2007/02/08", "yyyy/MM/dd"));
            l_bondProductParams.setRedemptionPrice(10.5);
            l_bondProductParams.setCouponType(CouponTypeEnum.FLOATING_COUPON);
            l_bondProductParams.setCoupon(0.5);
            l_bondProductParams.setYearlyInterestPayments(9);
            l_bondProductParams.setTradeHandleDiv("0");
            l_bondProductParams.setTradeUnit(20.3);
            l_bondProductParams.setMinFaceAmount(10.21);
            l_bondProductParams.setAutoExecDiv("1");
            l_bondProductParams.setHostProductIssueCode("2");

            l_bondProductParams.setCreatedTimestamp(Calendar.getInstance().getTime());
            l_bondProductParams.setTradeEndDate(WEB3DateUtility.getDate("20070208", "yyyyMMdd"));
            l_bondProductParams.setLastUpdatedTimestamp(Calendar.getInstance().getTime());
            TestDBUtility.insertWithDel(l_bondProductParams);

            WEB3BondProduct l_bondProduct =
                new WEB3BondProduct(l_bondProductParams);
            Date l_dat = l_bondProduct.getBondDomesticBizDate();
            assertEquals("20070208", WEB3DateUtility.formatDate(l_dat,"yyyyMMdd"));
            
        }
        catch (Exception l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testIsBondDomesticApplyPossible_case0001()
    {
        final String STR_METHOD_NAME = "testIsBondDomesticApplyPossible_case0001()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            BondProductParams l_bondProductParams = TestDBUtility.getBondProductRow();
            l_bondProductParams.setProductId(10001);
            l_bondProductParams.setInstitutionCode("20");
            l_bondProductParams.setProductCode("0126");
            l_bondProductParams.setProductName("あいう");
            l_bondProductParams.setProductIssueCode("213");
            l_bondProductParams.setBondType(BondTypeEnum.DOMESTIC_BOND);
            l_bondProductParams.setHostProductCode("6666");
            l_bondProductParams.setProductIssueCode("8888");
            l_bondProductParams.setIssueDate(WEB3DateUtility.getDate("2007/02/02", "yyyy/MM/dd"));
            l_bondProductParams.setIssuePrice(20.5);
            l_bondProductParams.setParValue(2.1);
            l_bondProductParams.setMaturityDate(WEB3DateUtility.getDate("2007/02/08", "yyyy/MM/dd"));
            l_bondProductParams.setRedemptionPrice(10.5);
            l_bondProductParams.setCouponType(CouponTypeEnum.FLOATING_COUPON);
            l_bondProductParams.setCoupon(0.5);
            l_bondProductParams.setYearlyInterestPayments(9);
            l_bondProductParams.setTradeHandleDiv("0");
            l_bondProductParams.setTradeUnit(20.3);
            l_bondProductParams.setMinFaceAmount(10.21);
            l_bondProductParams.setAutoExecDiv("1");
            l_bondProductParams.setHostProductIssueCode("2");
            l_bondProductParams.setTradeType("2");
            l_bondProductParams.setCreatedTimestamp(Calendar.getInstance().getTime());
            l_bondProductParams.setTradeEndDate(WEB3DateUtility.getDate("20070208", "yyyyMMdd"));
            l_bondProductParams.setLastUpdatedTimestamp(Calendar.getInstance().getTime());
            TestDBUtility.insertWithDel(l_bondProductParams);

            WEB3BondProduct l_bondProduct =
                new WEB3BondProduct(l_bondProductParams);
            boolean l_bln = l_bondProduct.isBondDomesticApplyPossible();
            assertFalse(l_bln);
        }
        catch (Exception l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }
  
    public void testIsBondDomesticApplyPossible_case0002()
    {
        final String STR_METHOD_NAME = "testIsBondDomesticApplyPossible_case0002()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            BondProductParams l_bondProductParams = TestDBUtility.getBondProductRow();
            l_bondProductParams.setProductId(10001);
            l_bondProductParams.setInstitutionCode("20");
            l_bondProductParams.setProductCode("0126");
            l_bondProductParams.setProductName("あいう");
            l_bondProductParams.setProductIssueCode("213");
            l_bondProductParams.setBondType(BondTypeEnum.DOMESTIC_BOND);
            l_bondProductParams.setHostProductCode("6666");
            l_bondProductParams.setProductIssueCode("8888");
            l_bondProductParams.setIssueDate(WEB3DateUtility.getDate("2007/02/02", "yyyy/MM/dd"));
            l_bondProductParams.setIssuePrice(20.5);
            l_bondProductParams.setParValue(2.1);
            l_bondProductParams.setMaturityDate(WEB3DateUtility.getDate("2007/02/08", "yyyy/MM/dd"));
            l_bondProductParams.setRedemptionPrice(10.5);
            l_bondProductParams.setCouponType(CouponTypeEnum.FLOATING_COUPON);
            l_bondProductParams.setCoupon(0.5);
            l_bondProductParams.setYearlyInterestPayments(9);
            l_bondProductParams.setTradeHandleDiv("0");
            l_bondProductParams.setTradeUnit(20.3);
            l_bondProductParams.setMinFaceAmount(10.21);
            l_bondProductParams.setAutoExecDiv("1");
            l_bondProductParams.setHostProductIssueCode("2");
            l_bondProductParams.setTradeType("3");
            l_bondProductParams.setCreatedTimestamp(Calendar.getInstance().getTime());
            l_bondProductParams.setTradeEndDate(WEB3DateUtility.getDate("20070208", "yyyyMMdd"));
            l_bondProductParams.setLastUpdatedTimestamp(Calendar.getInstance().getTime());
            TestDBUtility.insertWithDel(l_bondProductParams);

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                    "getSessionProperty",
                    new Class[] {String.class},
                    "2");
            
            WEB3BondProduct l_bondProduct =
                new WEB3BondProduct(l_bondProductParams);
            boolean l_bln = l_bondProduct.isBondDomesticApplyPossible();
            WEB3MockObjectParamsValue l_paramsValue1 = TestBaseForMock.MOCK_MANAGER.getMethodParamsValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl", 
                    "getSessionProperty", 
                    new Class[] { String.class});
            assertEquals("orderChannel",(String)(l_paramsValue1.getFirstCalled()[0]));
            
            assertFalse(l_bln);
        }
        catch (Exception l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testIsBondDomesticApplyPossible_case0003()
    {
        final String STR_METHOD_NAME = "testIsBondDomesticApplyPossible_case0003()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            BondProductParams l_bondProductParams = TestDBUtility.getBondProductRow();
            l_bondProductParams.setProductId(10001);
            l_bondProductParams.setInstitutionCode("20");
            l_bondProductParams.setProductCode("0126");
            l_bondProductParams.setProductName("あいう");
            l_bondProductParams.setProductIssueCode("213");
            l_bondProductParams.setBondType(BondTypeEnum.DOMESTIC_BOND);
            l_bondProductParams.setHostProductCode("6666");
            l_bondProductParams.setProductIssueCode("8888");
            l_bondProductParams.setIssueDate(WEB3DateUtility.getDate("2007/02/02", "yyyy/MM/dd"));
            l_bondProductParams.setIssuePrice(20.5);
            l_bondProductParams.setParValue(2.1);
            l_bondProductParams.setMaturityDate(WEB3DateUtility.getDate("2007/02/08", "yyyy/MM/dd"));
            l_bondProductParams.setRedemptionPrice(10.5);
            l_bondProductParams.setCouponType(CouponTypeEnum.FLOATING_COUPON);
            l_bondProductParams.setCoupon(0.5);
            l_bondProductParams.setYearlyInterestPayments(9);
            l_bondProductParams.setTradeHandleDiv("0");
            l_bondProductParams.setTradeUnit(20.3);
            l_bondProductParams.setMinFaceAmount(10.21);
            l_bondProductParams.setAutoExecDiv("1");
            l_bondProductParams.setHostProductIssueCode("2");
            l_bondProductParams.setTradeType("3");
            l_bondProductParams.setCreatedTimestamp(Calendar.getInstance().getTime());
            l_bondProductParams.setTradeStartDate(WEB3DateUtility.getDate("20070208", "yyyyMMdd"));
            l_bondProductParams.setLastUpdatedTimestamp(Calendar.getInstance().getTime());
            TestDBUtility.insertWithDel(l_bondProductParams);

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl", "getSessionProperty",
                    new Class[] {String.class},
                    "0");
            
            WEB3BondProduct l_bondProduct =
                new WEB3BondProduct(l_bondProductParams);
            boolean l_bln = l_bondProduct.isBondDomesticApplyPossible();
            WEB3MockObjectParamsValue l_paramsValue1 = TestBaseForMock.MOCK_MANAGER.getMethodParamsValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl", 
                    "getSessionProperty", 
                    new Class[] { String.class});
            assertEquals("orderChannel",(String)l_paramsValue1.getFirstCalled()[0]);
            assertFalse(l_bln);
        }
        catch (Exception l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testIsBondDomesticApplyPossible_case0004()
    {
        final String STR_METHOD_NAME = "testIsBondDomesticApplyPossible_case0004()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            BondProductParams l_bondProductParams = TestDBUtility.getBondProductRow();
            l_bondProductParams.setProductId(10001);
            l_bondProductParams.setInstitutionCode("20");
            l_bondProductParams.setProductCode("0126");
            l_bondProductParams.setProductName("あいう");
            l_bondProductParams.setProductIssueCode("213");
            l_bondProductParams.setBondType(BondTypeEnum.DOMESTIC_BOND);
            l_bondProductParams.setHostProductCode("6666");
            l_bondProductParams.setProductIssueCode("8888");
            l_bondProductParams.setIssueDate(WEB3DateUtility.getDate("2007/02/02", "yyyy/MM/dd"));
            l_bondProductParams.setIssuePrice(20.5);
            l_bondProductParams.setParValue(2.1);
            l_bondProductParams.setMaturityDate(WEB3DateUtility.getDate("2007/02/08", "yyyy/MM/dd"));
            l_bondProductParams.setRedemptionPrice(10.5);
            l_bondProductParams.setCouponType(CouponTypeEnum.FLOATING_COUPON);
            l_bondProductParams.setCoupon(0.5);
            l_bondProductParams.setYearlyInterestPayments(9);
            l_bondProductParams.setTradeHandleDiv("0");
            l_bondProductParams.setTradeUnit(20.3);
            l_bondProductParams.setMinFaceAmount(10.21);
            l_bondProductParams.setAutoExecDiv("1");
            l_bondProductParams.setHostProductIssueCode("2");
            l_bondProductParams.setTradeType("3");
            l_bondProductParams.setCreatedTimestamp(Calendar.getInstance().getTime());
            l_bondProductParams.setTradeStartDate(WEB3DateUtility.getDate("20071208", "yyyyMMdd"));
            l_bondProductParams.setTradeEndDate(WEB3DateUtility.getDate("20071208", "yyyyMMdd"));
            l_bondProductParams.setLastUpdatedTimestamp(Calendar.getInstance().getTime());
            TestDBUtility.insertWithDel(l_bondProductParams);

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl", "getSessionProperty",
                    new Class[] {String.class},
                    "0");
            
            WEB3BondProduct l_bondProduct =
                new WEB3BondProduct(l_bondProductParams);
            boolean l_bln = l_bondProduct.isBondDomesticApplyPossible();
            WEB3MockObjectParamsValue l_paramsValue1 = TestBaseForMock.MOCK_MANAGER.getMethodParamsValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl", 
                    "getSessionProperty", 
                    new Class[] { String.class});
            assertEquals("orderChannel",(String)l_paramsValue1.getFirstCalled()[0]);
            assertFalse(l_bln);
        }
        catch (Exception l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testIsBondDomesticApplyPossible_case0005()
    {
        final String STR_METHOD_NAME = "testIsBondDomesticApplyPossible_case0005()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            BondProductParams l_bondProductParams = TestDBUtility.getBondProductRow();
            l_bondProductParams.setProductId(10001);
            l_bondProductParams.setInstitutionCode("20");
            l_bondProductParams.setProductCode("0126");
            l_bondProductParams.setProductName("あいう");
            l_bondProductParams.setProductIssueCode("213");
            l_bondProductParams.setBondType(BondTypeEnum.DOMESTIC_BOND);
            l_bondProductParams.setHostProductCode("6666");
            l_bondProductParams.setProductIssueCode("8888");
            l_bondProductParams.setIssueDate(WEB3DateUtility.getDate("2007/02/02", "yyyy/MM/dd"));
            l_bondProductParams.setIssuePrice(20.5);
            l_bondProductParams.setParValue(2.1);
            l_bondProductParams.setMaturityDate(WEB3DateUtility.getDate("2007/02/08", "yyyy/MM/dd"));
            l_bondProductParams.setRedemptionPrice(10.5);
            l_bondProductParams.setCouponType(CouponTypeEnum.FLOATING_COUPON);
            l_bondProductParams.setCoupon(0.5);
            l_bondProductParams.setYearlyInterestPayments(9);
            l_bondProductParams.setTradeHandleDiv("0");
            l_bondProductParams.setTradeUnit(20.3);
            l_bondProductParams.setMinFaceAmount(10.21);
            l_bondProductParams.setAutoExecDiv("1");
            l_bondProductParams.setHostProductIssueCode("2");
            l_bondProductParams.setTradeType("3");
            l_bondProductParams.setCreatedTimestamp(Calendar.getInstance().getTime());
            l_bondProductParams.setTradeStartDate(WEB3DateUtility.getDate("20070208", "yyyyMMdd"));
            l_bondProductParams.setTradeEndDate(WEB3DateUtility.getDate("20070208", "yyyyMMdd"));
            l_bondProductParams.setLastUpdatedTimestamp(Calendar.getInstance().getTime());
            TestDBUtility.insertWithDel(l_bondProductParams);

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl", "getSessionProperty",
                    new Class[] {String.class},
                    "0");
            
            WEB3BondProduct l_bondProduct =
                new WEB3BondProduct(l_bondProductParams);
            boolean l_bln = l_bondProduct.isBondDomesticApplyPossible();
            WEB3MockObjectParamsValue l_paramsValue1 = TestBaseForMock.MOCK_MANAGER.getMethodParamsValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl", 
                    "getSessionProperty", 
                    new Class[] { String.class});
            assertEquals("orderChannel",(String)l_paramsValue1.getFirstCalled()[0]);
            assertFalse(l_bln);
        }
        catch (Exception l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testIsBondDomesticApplyPossible_case0006()
    {
        final String STR_METHOD_NAME = "testIsBondDomesticApplyPossible_case0006()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            //ProductParams
            ProductParams l_productParams = TestDBUtility.getProductRow();
            l_productParams.setProductId(10001);
            TestDBUtility.insertWithDel(l_productParams);
            
            BondProductParams l_bondProductParams = TestDBUtility.getBondProductRow();
            l_bondProductParams.setProductId(10001);
            l_bondProductParams.setInstitutionCode("20");
            l_bondProductParams.setProductCode("0126");
            l_bondProductParams.setProductName("あいう");
            l_bondProductParams.setProductIssueCode("213");
            l_bondProductParams.setBondType(BondTypeEnum.DOMESTIC_BOND);
            l_bondProductParams.setHostProductCode("6666");
            l_bondProductParams.setProductIssueCode("8888");
            l_bondProductParams.setIssueDate(WEB3DateUtility.getDate("2007/02/02", "yyyy/MM/dd"));
            l_bondProductParams.setIssuePrice(20.5);
            l_bondProductParams.setParValue(2.1);
            l_bondProductParams.setMaturityDate(WEB3DateUtility.getDate("2007/02/08", "yyyy/MM/dd"));
            l_bondProductParams.setRedemptionPrice(10.5);
            l_bondProductParams.setCouponType(CouponTypeEnum.FLOATING_COUPON);
            l_bondProductParams.setCoupon(0.5);
            l_bondProductParams.setYearlyInterestPayments(9);
            l_bondProductParams.setTradeHandleDiv("0");
            l_bondProductParams.setTradeUnit(20.3);
            l_bondProductParams.setMinFaceAmount(10.21);
            l_bondProductParams.setAutoExecDiv("1");
            l_bondProductParams.setHostProductIssueCode("2");
            l_bondProductParams.setTradeType("3");
            l_bondProductParams.setCreatedTimestamp(Calendar.getInstance().getTime());
            l_bondProductParams.setTradeStartDate(WEB3DateUtility.getDate("20070208", "yyyyMMdd"));
            l_bondProductParams.setTradeEndDate(WEB3DateUtility.getDate("20101208", "yyyyMMdd"));
            l_bondProductParams.setLastUpdatedTimestamp(Calendar.getInstance().getTime());
            TestDBUtility.insertWithDel(l_bondProductParams);

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl", "getSessionProperty",
                    new Class[] {String.class},
                    "0");
            
            WEB3BondProduct l_bondProduct =
                new WEB3BondProduct(l_bondProductParams);
            boolean l_bln = l_bondProduct.isBondDomesticApplyPossible();
            WEB3MockObjectParamsValue l_paramsValue1 = TestBaseForMock.MOCK_MANAGER.getMethodParamsValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl", 
                    "getSessionProperty", 
                    new Class[] { String.class});
            assertEquals("orderChannel",(String)l_paramsValue1.getFirstCalled()[0]);
            assertTrue(l_bln);
        }
        catch (Exception l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testIsBondDomesticApplyPossible_case0007()
    {
        final String STR_METHOD_NAME = "testIsBondDomesticApplyPossible_case0007()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            BondProductParams l_bondProductParams = TestDBUtility.getBondProductRow();
            l_bondProductParams.setProductId(10001);
            l_bondProductParams.setInstitutionCode("20");
            l_bondProductParams.setProductCode("0126");
            l_bondProductParams.setProductName("あいう");
            l_bondProductParams.setProductIssueCode("213");
            l_bondProductParams.setBondType(BondTypeEnum.DOMESTIC_BOND);
            l_bondProductParams.setHostProductCode("6666");
            l_bondProductParams.setProductIssueCode("8888");
            l_bondProductParams.setIssueDate(WEB3DateUtility.getDate("2007/02/02", "yyyy/MM/dd"));
            l_bondProductParams.setIssuePrice(20.5);
            l_bondProductParams.setParValue(2.1);
            l_bondProductParams.setMaturityDate(WEB3DateUtility.getDate("2007/02/08", "yyyy/MM/dd"));
            l_bondProductParams.setRedemptionPrice(10.5);
            l_bondProductParams.setCouponType(CouponTypeEnum.FLOATING_COUPON);
            l_bondProductParams.setCoupon(0.5);
            l_bondProductParams.setYearlyInterestPayments(9);
            l_bondProductParams.setTradeHandleDiv("0");
            l_bondProductParams.setTradeUnit(20.3);
            l_bondProductParams.setMinFaceAmount(10.21);
            l_bondProductParams.setAutoExecDiv("1");
            l_bondProductParams.setHostProductIssueCode("2");
            l_bondProductParams.setTradeType("3");
            l_bondProductParams.setCreatedTimestamp(Calendar.getInstance().getTime());
            l_bondProductParams.setRecruitStartDate(WEB3DateUtility.getDate("20070208", "yyyyMMdd"));
            l_bondProductParams.setLastUpdatedTimestamp(Calendar.getInstance().getTime());
            TestDBUtility.insertWithDel(l_bondProductParams);

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl", "getSessionProperty",
                    new Class[] {String.class},
                    "1");
            
            WEB3BondProduct l_bondProduct =
                new WEB3BondProduct(l_bondProductParams);
            boolean l_bln = l_bondProduct.isBondDomesticApplyPossible();
            WEB3MockObjectParamsValue l_paramsValue1 = TestBaseForMock.MOCK_MANAGER.getMethodParamsValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl", 
                    "getSessionProperty", 
                    new Class[] { String.class});
            assertEquals("orderChannel",(String)l_paramsValue1.getFirstCalled()[0]);
            assertFalse(l_bln);
        }
        catch (Exception l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testIsBondDomesticApplyPossible_case0008()
    {
        final String STR_METHOD_NAME = "testIsBondDomesticApplyPossible_case0008()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            BondProductParams l_bondProductParams = TestDBUtility.getBondProductRow();
            l_bondProductParams.setProductId(10001);
            l_bondProductParams.setInstitutionCode("20");
            l_bondProductParams.setProductCode("0126");
            l_bondProductParams.setProductName("あいう");
            l_bondProductParams.setProductIssueCode("213");
            l_bondProductParams.setBondType(BondTypeEnum.DOMESTIC_BOND);
            l_bondProductParams.setHostProductCode("6666");
            l_bondProductParams.setProductIssueCode("8888");
            l_bondProductParams.setIssueDate(WEB3DateUtility.getDate("2007/02/02", "yyyy/MM/dd"));
            l_bondProductParams.setIssuePrice(20.5);
            l_bondProductParams.setParValue(2.1);
            l_bondProductParams.setMaturityDate(WEB3DateUtility.getDate("2007/02/08", "yyyy/MM/dd"));
            l_bondProductParams.setRedemptionPrice(10.5);
            l_bondProductParams.setCouponType(CouponTypeEnum.FLOATING_COUPON);
            l_bondProductParams.setCoupon(0.5);
            l_bondProductParams.setYearlyInterestPayments(9);
            l_bondProductParams.setTradeHandleDiv("0");
            l_bondProductParams.setTradeUnit(20.3);
            l_bondProductParams.setMinFaceAmount(10.21);
            l_bondProductParams.setAutoExecDiv("1");
            l_bondProductParams.setHostProductIssueCode("2");
            l_bondProductParams.setTradeType("3");
            l_bondProductParams.setCreatedTimestamp(Calendar.getInstance().getTime());
            l_bondProductParams.setRecruitEndDate(WEB3DateUtility.getDate("20070208", "yyyyMMdd"));
            l_bondProductParams.setLastUpdatedTimestamp(Calendar.getInstance().getTime());
            TestDBUtility.insertWithDel(l_bondProductParams);

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl", "getSessionProperty",
                    new Class[] {String.class},
                    "1");
            
            WEB3BondProduct l_bondProduct =
                new WEB3BondProduct(l_bondProductParams);
            boolean l_bln = l_bondProduct.isBondDomesticApplyPossible();
            WEB3MockObjectParamsValue l_paramsValue1 = TestBaseForMock.MOCK_MANAGER.getMethodParamsValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl", 
                    "getSessionProperty", 
                    new Class[] { String.class});
            assertEquals("orderChannel",(String)l_paramsValue1.getFirstCalled()[0]);
            assertFalse(l_bln);
        }
        catch (Exception l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testIsBondDomesticApplyPossible_case0009()
    {
        final String STR_METHOD_NAME = "testIsBondDomesticApplyPossible_case0009()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            BondProductParams l_bondProductParams = TestDBUtility.getBondProductRow();
            l_bondProductParams.setProductId(10001);
            l_bondProductParams.setInstitutionCode("20");
            l_bondProductParams.setProductCode("0126");
            l_bondProductParams.setProductName("あいう");
            l_bondProductParams.setProductIssueCode("213");
            l_bondProductParams.setBondType(BondTypeEnum.DOMESTIC_BOND);
            l_bondProductParams.setHostProductCode("6666");
            l_bondProductParams.setProductIssueCode("8888");
            l_bondProductParams.setIssueDate(WEB3DateUtility.getDate("2007/02/02", "yyyy/MM/dd"));
            l_bondProductParams.setIssuePrice(20.5);
            l_bondProductParams.setParValue(2.1);
            l_bondProductParams.setMaturityDate(WEB3DateUtility.getDate("2007/02/08", "yyyy/MM/dd"));
            l_bondProductParams.setRedemptionPrice(10.5);
            l_bondProductParams.setCouponType(CouponTypeEnum.FLOATING_COUPON);
            l_bondProductParams.setCoupon(0.5);
            l_bondProductParams.setYearlyInterestPayments(9);
            l_bondProductParams.setTradeHandleDiv("0");
            l_bondProductParams.setTradeUnit(20.3);
            l_bondProductParams.setMinFaceAmount(10.21);
            l_bondProductParams.setAutoExecDiv("1");
            l_bondProductParams.setHostProductIssueCode("2");
            l_bondProductParams.setTradeType("3");
            l_bondProductParams.setCreatedTimestamp(Calendar.getInstance().getTime());
            l_bondProductParams.setRecruitStartDate(WEB3DateUtility.getDate("20070208", "yyyyMMdd"));
            l_bondProductParams.setRecruitEndDate(WEB3DateUtility.getDate("20070208", "yyyyMMdd"));
            l_bondProductParams.setLastUpdatedTimestamp(Calendar.getInstance().getTime());
            TestDBUtility.insertWithDel(l_bondProductParams);

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl", "getSessionProperty",
                    new Class[] {String.class},
                    "1");
            
            WEB3BondProduct l_bondProduct =
                new WEB3BondProduct(l_bondProductParams);
            boolean l_bln = l_bondProduct.isBondDomesticApplyPossible();
            WEB3MockObjectParamsValue l_paramsValue1 = TestBaseForMock.MOCK_MANAGER.getMethodParamsValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl", 
                    "getSessionProperty", 
                    new Class[] { String.class});
            assertEquals("orderChannel",(String)l_paramsValue1.getFirstCalled()[0]);
            assertFalse(l_bln);
        }
        catch (Exception l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testIsBondDomesticApplyPossible_case0010()
    {
        final String STR_METHOD_NAME = "testIsBondDomesticApplyPossible_case0010()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            BondProductParams l_bondProductParams = TestDBUtility.getBondProductRow();
            l_bondProductParams.setProductId(10001);
            l_bondProductParams.setInstitutionCode("20");
            l_bondProductParams.setProductCode("0126");
            l_bondProductParams.setProductName("あいう");
            l_bondProductParams.setProductIssueCode("213");
            l_bondProductParams.setBondType(BondTypeEnum.DOMESTIC_BOND);
            l_bondProductParams.setHostProductCode("6666");
            l_bondProductParams.setProductIssueCode("8888");
            l_bondProductParams.setIssueDate(WEB3DateUtility.getDate("2007/02/02", "yyyy/MM/dd"));
            l_bondProductParams.setIssuePrice(20.5);
            l_bondProductParams.setParValue(2.1);
            l_bondProductParams.setMaturityDate(WEB3DateUtility.getDate("2007/02/08", "yyyy/MM/dd"));
            l_bondProductParams.setRedemptionPrice(10.5);
            l_bondProductParams.setCouponType(CouponTypeEnum.FLOATING_COUPON);
            l_bondProductParams.setCoupon(0.5);
            l_bondProductParams.setYearlyInterestPayments(9);
            l_bondProductParams.setTradeHandleDiv("0");
            l_bondProductParams.setTradeUnit(20.3);
            l_bondProductParams.setMinFaceAmount(10.21);
            l_bondProductParams.setAutoExecDiv("1");
            l_bondProductParams.setHostProductIssueCode("2");
            l_bondProductParams.setTradeType("3");
            l_bondProductParams.setCreatedTimestamp(Calendar.getInstance().getTime());
            l_bondProductParams.setRecruitStartDate(WEB3DateUtility.getDate("20071208", "yyyyMMdd"));
            l_bondProductParams.setRecruitEndDate(WEB3DateUtility.getDate("20071208", "yyyyMMdd"));
            l_bondProductParams.setLastUpdatedTimestamp(Calendar.getInstance().getTime());
            TestDBUtility.insertWithDel(l_bondProductParams);

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl", "getSessionProperty",
                    new Class[] {String.class},
                    "1");
            
            WEB3BondProduct l_bondProduct =
                new WEB3BondProduct(l_bondProductParams);
            boolean l_bln = l_bondProduct.isBondDomesticApplyPossible();
            WEB3MockObjectParamsValue l_paramsValue1 = TestBaseForMock.MOCK_MANAGER.getMethodParamsValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl", 
                    "getSessionProperty", 
                    new Class[] { String.class});
            assertEquals("orderChannel",(String)l_paramsValue1.getFirstCalled()[0]);
            assertFalse(l_bln);
        }
        catch (Exception l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testIsBondDomesticApplyPossible_case0011()
    {
        final String STR_METHOD_NAME = "testIsBondDomesticApplyPossible_case0011()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            BondProductParams l_bondProductParams = TestDBUtility.getBondProductRow();
            l_bondProductParams.setProductId(10001);
            l_bondProductParams.setInstitutionCode("20");
            l_bondProductParams.setProductCode("0126");
            l_bondProductParams.setProductName("あいう");
            l_bondProductParams.setProductIssueCode("213");
            l_bondProductParams.setBondType(BondTypeEnum.DOMESTIC_BOND);
            l_bondProductParams.setHostProductCode("6666");
            l_bondProductParams.setProductIssueCode("8888");
            l_bondProductParams.setIssueDate(WEB3DateUtility.getDate("2007/02/02", "yyyy/MM/dd"));
            l_bondProductParams.setIssuePrice(20.5);
            l_bondProductParams.setParValue(2.1);
            l_bondProductParams.setMaturityDate(WEB3DateUtility.getDate("2007/02/08", "yyyy/MM/dd"));
            l_bondProductParams.setRedemptionPrice(10.5);
            l_bondProductParams.setCouponType(CouponTypeEnum.FLOATING_COUPON);
            l_bondProductParams.setCoupon(0.5);
            l_bondProductParams.setYearlyInterestPayments(9);
            l_bondProductParams.setTradeHandleDiv("0");
            l_bondProductParams.setTradeUnit(20.3);
            l_bondProductParams.setMinFaceAmount(10.21);
            l_bondProductParams.setAutoExecDiv("1");
            l_bondProductParams.setHostProductIssueCode("2");
            l_bondProductParams.setTradeType("3");
            l_bondProductParams.setCreatedTimestamp(Calendar.getInstance().getTime());
            l_bondProductParams.setRecruitStartDate(WEB3DateUtility.getDate("20070208", "yyyyMMdd"));
            l_bondProductParams.setRecruitEndDate(WEB3DateUtility.getDate("20101218", "yyyyMMdd"));
            l_bondProductParams.setLastUpdatedTimestamp(Calendar.getInstance().getTime());
            TestDBUtility.insertWithDel(l_bondProductParams);

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl", "getSessionProperty",
                    new Class[] {String.class},
                    "1");
            
            WEB3BondProduct l_bondProduct =
                new WEB3BondProduct(l_bondProductParams);
            boolean l_bln = l_bondProduct.isBondDomesticApplyPossible();
            WEB3MockObjectParamsValue l_paramsValue1 = TestBaseForMock.MOCK_MANAGER.getMethodParamsValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl", 
                    "getSessionProperty", 
                    new Class[] { String.class});
            assertEquals("orderChannel",(String)l_paramsValue1.getFirstCalled()[0]);
            assertTrue(l_bln);
        }
        catch (Exception l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    private class WEB3BondOrderTypeJudgeForTest extends WEB3BondOrderTypeJudge
    {
        public boolean isRecruitOrder()
        {
            return true;
        }
    }
}
@
