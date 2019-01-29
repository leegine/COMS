head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.01.12.37;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3EquityTypeOrderManagerReusableValidationsTest070619.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright           : (株)大和総研 証券ソリューションシステム第二部
File Name           : 株式発注審査個別チェックテスト(WEB3EquityTypeOrderManagerReusableValidationsTest070619.java)
Author Name         : Daiwa Institute of Research
Revision History    : 2007/06/19 武波(中訊) 新規作成
*/
package webbroker3.equity;

import java.util.Date;

import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeExecutionConditionType;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeProductParams;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeTradedProductParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.BranchParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.InstitutionParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MarketParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.ProductParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.SubAccountParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.TradedProductParams;

import test.util.TestDBUtility;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3CarriedOrderLapseDateSpecDef;
import webbroker3.common.define.WEB3FuturesOptionDivDef;
import webbroker3.common.define.WEB3MarginTradingDivDef;
import webbroker3.common.define.WEB3OrderingConditionDef;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.gentrade.data.BranchPreferencesParams;
import webbroker3.gentrade.data.EnableOrderConditionParams;
import webbroker3.gentrade.define.WEB3GentradePriceCondDef;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

/**
 * (株式発注審査個別チェックテスト)<BR>
 * 株式発注審査個別チェックテスト
 *
 * @@author 武波
 * @@version 1.0
 */
public class WEB3EquityTypeOrderManagerReusableValidationsTest070619
    extends TestBaseForMock
{

    /**
     * ログユーティリティ<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3EquityTypeOrderManagerReusableValidationsTest070619.class);
    public WEB3EquityTypeOrderManagerReusableValidationsTest070619(String name) {
        super(name);
        // TODO Auto-generated constructor stub
    }

    /**
     * setUp<BR>
     * @@param l_strName
     */
    protected void setUp() throws Exception
    {
        super.setUp();
    }

    /**
     * tearDown<BR>
     * @@param l_strName
     */
    protected void tearDown() throws Exception
    {
        super.tearDown();
    }

    /**
     * 
     */
    public void test_validateOrderCondition_C0001()
    {
        final String STR_METHOD_NAME = " test_validateOrderCondition_C0001()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            TestDBUtility.deleteAll(SubAccountParams.TYPE);
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setInstitutionId(1L);
            l_subAccountParams.setInstitutionCode("0D");
            TestDBUtility.insertWithDel(l_subAccountParams);

            TestDBUtility.deleteAll(SubAccountParams.TYPE);
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            l_institutionParams.setInstitutionId(33l);
            l_institutionParams.setInstitutionCode("0D");
            TestDBUtility.insertWithDel(l_institutionParams);

            TestDBUtility.deleteAll(EnableOrderConditionParams.TYPE);
            EnableOrderConditionParams l_enableOrderConditionParams =
                TestDBUtility.getEnableOrderConditionParamsRow();
            l_enableOrderConditionParams.setInstitutionCode("0D");
            l_enableOrderConditionParams.setProductType(ProductTypeEnum.EQUITY);
            l_enableOrderConditionParams.setFutureOptionDiv(WEB3FuturesOptionDivDef.DEFAULT);
            l_enableOrderConditionParams.setMarginTradingDiv(WEB3MarginTradingDivDef.DEFAULT);
            l_enableOrderConditionParams.setMarketCode("3");
            TestDBUtility.insertWithDel(l_enableOrderConditionParams);

            TestDBUtility.deleteAll(MarketParams.TYPE);
            MarketParams l_marketParams = TestDBUtility.getMarketRow();
            l_marketParams.setMarketId(3303l);
            TestDBUtility.insertWithDel(l_marketParams);

            TestDBUtility.deleteAll(EqtypeProductParams.TYPE);
            EqtypeProductParams l_eqtypeProductParams = TestDBUtility.getEqtypeProductRow();
            l_eqtypeProductParams.setProductType(ProductTypeEnum.EQUITY);
            l_eqtypeProductParams.setProductId(1001L);
            TestDBUtility.insertWithDel(l_eqtypeProductParams);

            TestDBUtility.deleteAll(ProductParams.TYPE);
            ProductParams l_productParams = TestDBUtility.getProductRow();
            l_productParams.setProductId(1001L);
            l_productParams.setProductType(ProductTypeEnum.EQUITY);
            TestDBUtility.insertWithDel(l_productParams);
            
            TestDBUtility.deleteAll(BranchPreferencesParams.TYPE);
            BranchPreferencesParams l_branchPreferencesParams = TestDBUtility.getBranchPreferencesRow();
            l_branchPreferencesParams.setValue("1");
            l_branchPreferencesParams.setBranchId(33381);
            l_branchPreferencesParams.setName("eqtype.final.day.with.right");
            l_branchPreferencesParams.setNameSerialNo(1);
            TestDBUtility.insertWithDel(l_branchPreferencesParams);

            TestDBUtility.deleteAll(MainAccountParams.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(333812512203L);
            TestDBUtility.insertWithDel(l_mainAccountParams);

            TestDBUtility.deleteAll(SubAccountParams.TYPE);
            SubAccountParams l_subAccountRow = TestDBUtility.getSubAccountRow();
            TestDBUtility.insertWithDel(l_subAccountRow);

            TestDBUtility.deleteAll(TradedProductParams.TYPE);
            TradedProductParams l_tradedProductParams = TestDBUtility.getTradedProductRow();
            l_tradedProductParams.setProductId(1001L);
            l_tradedProductParams.setTradedProductId(1001L);
            l_tradedProductParams.setMarketId(3303L);
            l_tradedProductParams.setInstitutionCode("0D");
            TestDBUtility.insertWithDel(l_tradedProductParams);

            TestDBUtility.deleteAll(EqtypeTradedProductParams.TYPE);
            EqtypeTradedProductParams l_eqtypeTradedProductParams = TestDBUtility.getEqtypeTradedProductRow();
            l_eqtypeTradedProductParams.setTradedProductId(1001L);
            l_eqtypeTradedProductParams.setProductId(1001L);
            l_eqtypeTradedProductParams.setMarketId(3303);
            TestDBUtility.insertWithDel(l_eqtypeTradedProductParams);

            WEB3GentradeSubAccount l_subAccount = new WEB3GentradeSubAccount(l_subAccountRow);
            long l_lngOrderUnitId = 1L;
            WEB3EquityTradedProduct l_tradedProduct = new WEB3EquityTradedProduct(l_tradedProductParams);
            Date l_datOrderBizDate = WEB3DateUtility.getDate("20070619","yyyyMMdd");
            Date l_datExpirationDate = WEB3DateUtility.getDate("20070619","yyyyMMdd");
            String l_strOrderConditionType = WEB3OrderingConditionDef.DEFAULT;
            EqTypeExecutionConditionType l_executionCondition = EqTypeExecutionConditionType.AT_MARKET_OPEN;
            boolean l_isCarriedOrder = false;
            String l_strMarginTradeType = null;
            String l_strPriceConditionType = WEB3GentradePriceCondDef.DEFAULT;
            String l_strMarketCode = "3";
            WEB3EquityTypeOrderManagerReusableValidations l_equityTypeOrderManagerReusableValidations =
                new WEB3EquityTypeOrderManagerReusableValidations();
            l_equityTypeOrderManagerReusableValidations.validateOrderCondition(        
                    l_subAccount, l_lngOrderUnitId, l_tradedProduct, l_datOrderBizDate,
                    l_datExpirationDate, l_strOrderConditionType, l_executionCondition, l_isCarriedOrder,
                    l_strMarginTradeType, l_strPriceConditionType, l_strMarketCode);
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
    public void test_validateOrderCondition_C0002()
    {
        final String STR_METHOD_NAME = " test_validateOrderCondition_C0002()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            TestDBUtility.deleteAll(BranchParams.TYPE);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setBranchId(33381);
            TestDBUtility.insertWithDel(l_branchParams);

            TestDBUtility.deleteAll(SubAccountParams.TYPE);
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setInstitutionId(1L);
            l_subAccountParams.setInstitutionCode("0D");
            TestDBUtility.insertWithDel(l_subAccountParams);

            TestDBUtility.deleteAll(SubAccountParams.TYPE);
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            l_institutionParams.setInstitutionId(33l);
            l_institutionParams.setInstitutionCode("0D");
            TestDBUtility.insertWithDel(l_institutionParams);

            TestDBUtility.deleteAll(EnableOrderConditionParams.TYPE);
            EnableOrderConditionParams l_enableOrderConditionParams =
                TestDBUtility.getEnableOrderConditionParamsRow();
            l_enableOrderConditionParams.setInstitutionCode("0D");
            l_enableOrderConditionParams.setProductType(ProductTypeEnum.EQUITY);
            l_enableOrderConditionParams.setFutureOptionDiv(WEB3FuturesOptionDivDef.DEFAULT);
            l_enableOrderConditionParams.setMarginTradingDiv(WEB3MarginTradingDivDef.DEFAULT);
            l_enableOrderConditionParams.setMarketCode("3");
            TestDBUtility.insertWithDel(l_enableOrderConditionParams);

            TestDBUtility.deleteAll(MarketParams.TYPE);
            MarketParams l_marketParams = TestDBUtility.getMarketRow();
            l_marketParams.setMarketId(3303l);
            TestDBUtility.insertWithDel(l_marketParams);

            TestDBUtility.deleteAll(EqtypeProductParams.TYPE);
            EqtypeProductParams l_eqtypeProductParams = TestDBUtility.getEqtypeProductRow();
            l_eqtypeProductParams.setProductType(ProductTypeEnum.EQUITY);
            l_eqtypeProductParams.setProductId(1001L);
            TestDBUtility.insertWithDel(l_eqtypeProductParams);

            TestDBUtility.deleteAll(ProductParams.TYPE);
            ProductParams l_productParams = TestDBUtility.getProductRow();
            l_productParams.setProductId(1001L);
            l_productParams.setProductType(ProductTypeEnum.EQUITY);
            TestDBUtility.insertWithDel(l_productParams);
            
            TestDBUtility.deleteAll(BranchPreferencesParams.TYPE);
            BranchPreferencesParams l_branchPreferencesParams = TestDBUtility.getBranchPreferencesRow();
            l_branchPreferencesParams.setValue("1");
            l_branchPreferencesParams.setBranchId(33381);
            l_branchPreferencesParams.setName("eqtype.final.day.with.right");
            l_branchPreferencesParams.setNameSerialNo(1);
            TestDBUtility.insertWithDel(l_branchPreferencesParams);

            TestDBUtility.deleteAll(MainAccountParams.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(333812512203L);
            TestDBUtility.insertWithDel(l_mainAccountParams);

            TestDBUtility.deleteAll(SubAccountParams.TYPE);
            SubAccountParams l_subAccountRow = TestDBUtility.getSubAccountRow();
            TestDBUtility.insertWithDel(l_subAccountRow);

            TestDBUtility.deleteAll(TradedProductParams.TYPE);
            TradedProductParams l_tradedProductParams = TestDBUtility.getTradedProductRow();
            l_tradedProductParams.setProductId(1001L);
            l_tradedProductParams.setTradedProductId(1001L);
            l_tradedProductParams.setMarketId(3303L);
            l_tradedProductParams.setInstitutionCode("0D");
            TestDBUtility.insertWithDel(l_tradedProductParams);

            TestDBUtility.deleteAll(EqtypeTradedProductParams.TYPE);
            EqtypeTradedProductParams l_eqtypeTradedProductParams = TestDBUtility.getEqtypeTradedProductRow();
            l_eqtypeTradedProductParams.setTradedProductId(1001L);
            l_eqtypeTradedProductParams.setProductId(1001L);
            l_eqtypeTradedProductParams.setMarketId(3303);
            TestDBUtility.insertWithDel(l_eqtypeTradedProductParams);

            WEB3GentradeSubAccount l_subAccount = new WEB3GentradeSubAccount(l_subAccountRow);
            long l_lngOrderUnitId = 1L;
            WEB3EquityTradedProduct l_tradedProduct = new WEB3EquityTradedProduct(l_tradedProductParams);
            Date l_datOrderBizDate = WEB3DateUtility.getDate("20070619","yyyyMMdd");
            Date l_datExpirationDate = WEB3DateUtility.getDate("20070619","yyyyMMdd");
            String l_strOrderConditionType = WEB3OrderingConditionDef.DEFAULT;
            EqTypeExecutionConditionType l_executionCondition = EqTypeExecutionConditionType.AT_MARKET_OPEN;
            boolean l_isCarriedOrder = false;
            String l_strMarginTradeType = null;
            String l_strPriceConditionType = WEB3GentradePriceCondDef.DEFAULT;
            String l_strMarketCode = "3";
            WEB3EquityTypeOrderManagerReusableValidations l_equityTypeOrderManagerReusableValidations =
                new WEB3EquityTypeOrderManagerReusableValidations();
            l_equityTypeOrderManagerReusableValidations.validateOrderCondition(        
                    l_subAccount, l_lngOrderUnitId, l_tradedProduct, l_datOrderBizDate,
                    l_datExpirationDate, l_strOrderConditionType, l_executionCondition, l_isCarriedOrder,
                    l_strMarginTradeType, l_strPriceConditionType, l_strMarketCode);
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
   public void test_validateOrderCondition_C0003()
   {
       final String STR_METHOD_NAME = " test_validateOrderCondition_C0003()";
       log.entering(TEST_START + STR_METHOD_NAME);

       try
       {
           TestDBUtility.deleteAll(BranchParams.TYPE);
           BranchParams l_branchParams = TestDBUtility.getBranchRow();
           l_branchParams.setBranchId(33381);
           TestDBUtility.insertWithDel(l_branchParams);

           TestDBUtility.deleteAll(SubAccountParams.TYPE);
           SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
           l_subAccountParams.setInstitutionId(1L);
           l_subAccountParams.setInstitutionCode("0D");
           TestDBUtility.insertWithDel(l_subAccountParams);

           TestDBUtility.deleteAll(SubAccountParams.TYPE);
           InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
           l_institutionParams.setInstitutionId(33l);
           l_institutionParams.setInstitutionCode("0D");
           TestDBUtility.insertWithDel(l_institutionParams);

           TestDBUtility.deleteAll(EnableOrderConditionParams.TYPE);
           EnableOrderConditionParams l_enableOrderConditionParams =
               TestDBUtility.getEnableOrderConditionParamsRow();
           l_enableOrderConditionParams.setInstitutionCode("0D");
           l_enableOrderConditionParams.setProductType(ProductTypeEnum.EQUITY);
           l_enableOrderConditionParams.setFutureOptionDiv(WEB3FuturesOptionDivDef.DEFAULT);
           l_enableOrderConditionParams.setMarginTradingDiv(WEB3MarginTradingDivDef.DEFAULT);
           l_enableOrderConditionParams.setMarketCode("3");
           l_enableOrderConditionParams.setCarriedOrderLapseDateSpec(
               WEB3CarriedOrderLapseDateSpecDef.FINAL_DATE_DESIGNATA);
           TestDBUtility.insertWithDel(l_enableOrderConditionParams);

           TestDBUtility.deleteAll(MarketParams.TYPE);
           MarketParams l_marketParams = TestDBUtility.getMarketRow();
           l_marketParams.setMarketId(3303l);
           TestDBUtility.insertWithDel(l_marketParams);

           TestDBUtility.deleteAll(EqtypeProductParams.TYPE);
           EqtypeProductParams l_eqtypeProductParams = TestDBUtility.getEqtypeProductRow();
           l_eqtypeProductParams.setProductType(ProductTypeEnum.EQUITY);
           l_eqtypeProductParams.setProductId(1001L);
           l_eqtypeProductParams.setYearlyBooksClosingDate(WEB3DateUtility.getDate("20070618","yyyyMMdd"));
           TestDBUtility.insertWithDel(l_eqtypeProductParams);

           TestDBUtility.deleteAll(ProductParams.TYPE);
           ProductParams l_productParams = TestDBUtility.getProductRow();
           l_productParams.setProductId(1001L);
           l_productParams.setProductType(ProductTypeEnum.EQUITY);
           TestDBUtility.insertWithDel(l_productParams);
           
           TestDBUtility.deleteAll(BranchPreferencesParams.TYPE);
           BranchPreferencesParams l_branchPreferencesParams = TestDBUtility.getBranchPreferencesRow();
           l_branchPreferencesParams.setValue("1");
           l_branchPreferencesParams.setBranchId(33381);
           l_branchPreferencesParams.setName("eqtype.final.day.with.right");
           l_branchPreferencesParams.setNameSerialNo(1);
           TestDBUtility.insertWithDel(l_branchPreferencesParams);

           TestDBUtility.deleteAll(MainAccountParams.TYPE);
           MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
           l_mainAccountParams.setAccountId(333812512203L);
           TestDBUtility.insertWithDel(l_mainAccountParams);

           TestDBUtility.deleteAll(SubAccountParams.TYPE);
           SubAccountParams l_subAccountRow = TestDBUtility.getSubAccountRow();
           TestDBUtility.insertWithDel(l_subAccountRow);

           TestDBUtility.deleteAll(TradedProductParams.TYPE);
           TradedProductParams l_tradedProductParams = TestDBUtility.getTradedProductRow();
           l_tradedProductParams.setProductId(1001L);
           l_tradedProductParams.setTradedProductId(1001L);
           l_tradedProductParams.setMarketId(3303L);
           l_tradedProductParams.setInstitutionCode("0D");
           TestDBUtility.insertWithDel(l_tradedProductParams);

           TestDBUtility.deleteAll(EqtypeTradedProductParams.TYPE);
           EqtypeTradedProductParams l_eqtypeTradedProductParams = TestDBUtility.getEqtypeTradedProductRow();
           l_eqtypeTradedProductParams.setTradedProductId(1001L);
           l_eqtypeTradedProductParams.setProductId(1001L);
           l_eqtypeTradedProductParams.setMarketId(3303);
           TestDBUtility.insertWithDel(l_eqtypeTradedProductParams);

           WEB3GentradeSubAccount l_subAccount = new WEB3GentradeSubAccount(l_subAccountRow);
           long l_lngOrderUnitId = 1L;
           WEB3EquityTradedProduct l_tradedProduct = new WEB3EquityTradedProduct(l_tradedProductParams);
           Date l_datOrderBizDate = WEB3DateUtility.getDate("20070611","yyyyMMdd");
           Date l_datExpirationDate = WEB3DateUtility.getDate("20070622","yyyyMMdd");
           String l_strOrderConditionType = WEB3OrderingConditionDef.DEFAULT;
           EqTypeExecutionConditionType l_executionCondition = EqTypeExecutionConditionType.AT_MARKET_OPEN;
           boolean l_isCarriedOrder = true;
           String l_strMarginTradeType = null;
           String l_strPriceConditionType = WEB3GentradePriceCondDef.DEFAULT;
           String l_strMarketCode = "3";
           WEB3EquityTypeOrderManagerReusableValidations l_equityTypeOrderManagerReusableValidations =
               new WEB3EquityTypeOrderManagerReusableValidations();
           l_equityTypeOrderManagerReusableValidations.validateOrderCondition(        
                   l_subAccount, l_lngOrderUnitId, l_tradedProduct, l_datOrderBizDate,
                   l_datExpirationDate, l_strOrderConditionType, l_executionCondition, l_isCarriedOrder,
                   l_strMarginTradeType, l_strPriceConditionType, l_strMarketCode);
           fail();
       }
       catch (WEB3BusinessLayerException l_web3SystemException)
       {
           assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02836,l_web3SystemException.getErrorInfo());
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
  public void test_validateOrderCondition_C0004()
  {
      final String STR_METHOD_NAME = " test_validateOrderCondition_C0004()";
      log.entering(TEST_START + STR_METHOD_NAME);

      try
      {
          TestDBUtility.deleteAll(BranchParams.TYPE);
          BranchParams l_branchParams = TestDBUtility.getBranchRow();
          l_branchParams.setBranchId(33381);
          TestDBUtility.insertWithDel(l_branchParams);

          TestDBUtility.deleteAll(SubAccountParams.TYPE);
          SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
          l_subAccountParams.setInstitutionId(1L);
          l_subAccountParams.setInstitutionCode("0D");
          TestDBUtility.insertWithDel(l_subAccountParams);

          TestDBUtility.deleteAll(SubAccountParams.TYPE);
          InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
          l_institutionParams.setInstitutionId(33l);
          l_institutionParams.setInstitutionCode("0D");
          TestDBUtility.insertWithDel(l_institutionParams);

          TestDBUtility.deleteAll(EnableOrderConditionParams.TYPE);
          EnableOrderConditionParams l_enableOrderConditionParams =
              TestDBUtility.getEnableOrderConditionParamsRow();
          l_enableOrderConditionParams.setInstitutionCode("0D");
          l_enableOrderConditionParams.setProductType(ProductTypeEnum.EQUITY);
          l_enableOrderConditionParams.setFutureOptionDiv(WEB3FuturesOptionDivDef.DEFAULT);
          l_enableOrderConditionParams.setMarginTradingDiv(WEB3MarginTradingDivDef.DEFAULT);
          l_enableOrderConditionParams.setMarketCode("3");
          l_enableOrderConditionParams.setCarriedOrderLapseDateSpec(
              WEB3CarriedOrderLapseDateSpecDef.EXPIRATION_DATE_USER_DES);
          TestDBUtility.insertWithDel(l_enableOrderConditionParams);

          TestDBUtility.deleteAll(MarketParams.TYPE);
          MarketParams l_marketParams = TestDBUtility.getMarketRow();
          l_marketParams.setMarketId(3303l);
          TestDBUtility.insertWithDel(l_marketParams);

          TestDBUtility.deleteAll(EqtypeProductParams.TYPE);
          EqtypeProductParams l_eqtypeProductParams = TestDBUtility.getEqtypeProductRow();
          l_eqtypeProductParams.setProductType(ProductTypeEnum.EQUITY);
          l_eqtypeProductParams.setProductId(1001L);
          TestDBUtility.insertWithDel(l_eqtypeProductParams);

          TestDBUtility.deleteAll(ProductParams.TYPE);
          ProductParams l_productParams = TestDBUtility.getProductRow();
          l_productParams.setProductId(1001L);
          l_productParams.setProductType(ProductTypeEnum.EQUITY);
          TestDBUtility.insertWithDel(l_productParams);
          
          TestDBUtility.deleteAll(BranchPreferencesParams.TYPE);
          BranchPreferencesParams l_branchPreferencesParams = TestDBUtility.getBranchPreferencesRow();
          l_branchPreferencesParams.setValue("1");
          l_branchPreferencesParams.setBranchId(33381);
          l_branchPreferencesParams.setName("eqtype.final.day.with.right");
          l_branchPreferencesParams.setNameSerialNo(1);
          TestDBUtility.insertWithDel(l_branchPreferencesParams);

          TestDBUtility.deleteAll(MainAccountParams.TYPE);
          MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
          l_mainAccountParams.setAccountId(333812512203L);
          TestDBUtility.insertWithDel(l_mainAccountParams);

          TestDBUtility.deleteAll(SubAccountParams.TYPE);
          SubAccountParams l_subAccountRow = TestDBUtility.getSubAccountRow();
          TestDBUtility.insertWithDel(l_subAccountRow);

          TestDBUtility.deleteAll(TradedProductParams.TYPE);
          TradedProductParams l_tradedProductParams = TestDBUtility.getTradedProductRow();
          l_tradedProductParams.setProductId(1001L);
          l_tradedProductParams.setTradedProductId(1001L);
          l_tradedProductParams.setMarketId(3303L);
          l_tradedProductParams.setInstitutionCode("0D");
          TestDBUtility.insertWithDel(l_tradedProductParams);

          TestDBUtility.deleteAll(EqtypeTradedProductParams.TYPE);
          EqtypeTradedProductParams l_eqtypeTradedProductParams = TestDBUtility.getEqtypeTradedProductRow();
          l_eqtypeTradedProductParams.setTradedProductId(1001L);
          l_eqtypeTradedProductParams.setProductId(1001L);
          l_eqtypeTradedProductParams.setMarketId(3303);
          TestDBUtility.insertWithDel(l_eqtypeTradedProductParams);

          WEB3GentradeSubAccount l_subAccount = new WEB3GentradeSubAccount(l_subAccountRow);
          long l_lngOrderUnitId = 1L;
          WEB3EquityTradedProduct l_tradedProduct = new WEB3EquityTradedProduct(l_tradedProductParams);
          Date l_datOrderBizDate = WEB3DateUtility.getDate("20070613","yyyyMMdd");
          Date l_datExpirationDate = WEB3DateUtility.getDate("20070613","yyyyMMdd");
          String l_strOrderConditionType = WEB3OrderingConditionDef.DEFAULT;
          EqTypeExecutionConditionType l_executionCondition = EqTypeExecutionConditionType.AT_MARKET_OPEN;
          boolean l_isCarriedOrder = true;
          String l_strMarginTradeType = null;
          String l_strPriceConditionType = WEB3GentradePriceCondDef.DEFAULT;
          String l_strMarketCode = "3";
          WEB3EquityTypeOrderManagerReusableValidations l_equityTypeOrderManagerReusableValidations =
              new WEB3EquityTypeOrderManagerReusableValidations();
          l_equityTypeOrderManagerReusableValidations.validateOrderCondition(        
                  l_subAccount, l_lngOrderUnitId, l_tradedProduct, l_datOrderBizDate,
                  l_datExpirationDate, l_strOrderConditionType, l_executionCondition, l_isCarriedOrder,
                  l_strMarginTradeType, l_strPriceConditionType, l_strMarketCode);
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
